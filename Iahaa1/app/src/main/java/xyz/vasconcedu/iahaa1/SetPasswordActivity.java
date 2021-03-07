package xyz.vasconcedu.iahaa1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        Button setPasswordButton = findViewById(R.id.set_password_activity_set_password_button);
        setPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText passwordEdiText = findViewById(R.id.set_password_activity_set_password_edit_text);
                String password = passwordEdiText.getText().toString();
                Log.i("[Iahaa1]", "Setting password to '" + password + "', don't tell anyone");
                storePassword(password);
            }
        });
    }

    private void storePassword(String password) {

        String path = getExternalFilesDir(null) + "/Iahaa1_ep.txt";
        File passwordFile = new File(path);
        password = password.trim();
        String encryptedPassword = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(passwordFile);
            fileOutputStream.write(encryptedPassword.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Stored complex as heck password", Toast.LENGTH_SHORT).show();
            finish();
        } catch (FileNotFoundException fileNotFoundException) {
            Log.i("[Iahaa1]", fileNotFoundException.getMessage());
        } catch (IOException iOException) {
            Log.i("[Iahaa1]", iOException.getMessage());
        }
    }
}
