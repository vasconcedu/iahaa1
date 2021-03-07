package xyz.vasconcedu.iahaa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CheckPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);

        String path = getExternalFilesDir(null) + "/Iahaa1_ep.txt";
        final File passwordFile = new File(path);

        if (!passwordFile.exists()) {
            setResult(2, getIntent());
            finish();
        }

        Button setPasswordButton = findViewById(R.id.check_password_activity_check_password_button);
        setPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText passwordEdiText = findViewById(R.id.check_password_activity_check_password_edit_text);
                String password = passwordEdiText.getText().toString();
                if (checkPassword(password, passwordFile)) {
                    setResult(1, getIntent());
                    finish();
                } else {
                    setResult(0, getIntent());
                    finish();
                }
            }
        });
    }

    private boolean checkPassword(String password, File passwordFile) {

        password = password.trim();
        String encryptedPassword = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);

        try {

            FileInputStream fileInputStream = new FileInputStream(passwordFile);
            byte[] storedPassword = new byte[1024];
            int storedPasswordLength = fileInputStream.read(storedPassword);
            fileInputStream.close();

            if (encryptedPassword.equals(new String(storedPassword).substring(0, storedPasswordLength))) {
                return true;
            }

        } catch (FileNotFoundException fileNotFoundException) {
            Log.i("[Iahaa1]", fileNotFoundException.getMessage());
        } catch (IOException iOException) {
            Log.i("[Iahaa1]", iOException.getMessage());
        }
        Toast.makeText(getApplicationContext(), "Get outta here H4CK3R!", Toast.LENGTH_SHORT).show();
        return false;
    }
}
