package xyz.vasconcedu.iahaa1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setPasswordButton = findViewById(R.id.main_activity_set_password_button);
        setPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckPasswordActivity.class);
                startActivityForResult(intent, 42);
            }
        });

        Button fetchLifeSecretsButton = findViewById(R.id.main_activity_fetch_life_secrets_button);
        fetchLifeSecretsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckPasswordActivity.class);
                startActivityForResult(intent, 43);
            }
        });

        Button tellSecretButton = findViewById(R.id.main_activity_tell_secret_button);
        tellSecretButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText insertionEditText = findViewById(R.id.main_activity_tell_secret_edit_text);
                String insertion = insertionEditText.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("secret", insertion);
                getContentResolver().insert(Uri.parse("content://xyz.vasconcedu.iahaa1.lifesecrets"), contentValues);
                Toast.makeText(getApplicationContext(), "I'll keep it a secret", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {

            if (requestCode == 42) {
                Intent intent = new Intent(getApplicationContext(), SetPasswordActivity.class);
                startActivity(intent);
            } else if (requestCode == 43) {
                EditText selectionEditText = findViewById(R.id.main_activity_fetch_life_secrets_edit_text);
                String selection = selectionEditText.getText().toString();
                Cursor cursor = getContentResolver().query(Uri.parse("content://xyz.vasconcedu.iahaa1.lifesecrets"), null, selection, null, null);
                String secrets = "";
                while(cursor.moveToNext()) {
                    secrets += cursor.getString(0) + "\n";
                }
                if (secrets == "") {
                    secrets = "Don't ask difficult questions";
                }
                Toast.makeText(getApplicationContext(), secrets, Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == 2) {
            Intent intent = new Intent(getApplicationContext(), SetPasswordActivity.class);
            startActivity(intent);
        }
    }
}
