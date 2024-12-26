package com.example.dataproctection;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2;
    Button encryptButton, decrypButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        encryptButton = findViewById(R.id.encryptButton);
        decrypButton = findViewById(R.id.decrypButton);


    }

    private String encryptionData(String plantext, String password) throws Exception {

        String planText = plantext;
        byte[] planTextByte = planText.getBytes("UTF-8");

        String pass = password; //"jhuyiok78ijogfye"; 16 bit password
        byte[] passwordByte = pass.getBytes("UTF-8");

        SecretKeySpec serectKey = new SecretKeySpec(passwordByte, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, serectKey);
        byte[] secretText = cipher.doFinal(planTextByte);

        String finalText = Base64.encodeToString(secretText, Base64.DEFAULT);
        return finalText;

    }

    private String decryptinData(String encodedText, String password) throws Exception {
        String encodedString = encodedText; //"pf+dXv49jplUDO+c6ZkpgQ==";
        byte[] decodedByte = Base64.decode(encodedString, Base64.DEFAULT);

        String pass = password; //"jhuyiok78ijogfye"; 16 bit password
        byte[] passwordByte = password.getBytes("UTF-8");

        SecretKeySpec secretKeySpec = new SecretKeySpec(passwordByte, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedbyte = cipher.doFinal(decodedByte);

        String finalDecodedText = new String(decodedbyte, "UTF-8");
        return finalDecodedText;

    }
}

