package com.teamteam.blueboi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText et_password, et_email;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.username_log_in_button);
        et_email = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor dspEditor = dsp.edit();
                dspEditor.putString("login", "yes");
                dspEditor.commit();

                Intent i;
                String user_email = et_email.getText().toString();
                if (user_email.equals("worker")) {
                    i = new Intent(LoginActivity.this, HomeActivityWorker.class);
                } else {
                    i = new Intent(LoginActivity.this, HomeActivity.class);
                }
                startActivity(i);
                finish();
            }
        });
    }
}
