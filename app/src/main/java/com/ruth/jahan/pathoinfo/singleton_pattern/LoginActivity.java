package com.ruth.jahan.pathoinfo.singleton_pattern;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.ruth.jahan.pathoinfo.AddTestActivity;
import com.ruth.jahan.pathoinfo.ICallbackFromActivity;
import com.ruth.jahan.pathoinfo.MainActivity;
import com.ruth.jahan.pathoinfo.R;
import com.ruth.jahan.pathoinfo.adapter_pattern.SignupActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ICallbackFromActivity {

    private Button loginbutton, signupbutton;
    private EditText signinemailedittext, signinpassedittext;
    private FirebaseAuth mAuth;
    private UserLoginHelper userLoginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Log In");
        mAuth = FirebaseAuth.getInstance();

        signinemailedittext = findViewById(R.id.loginemailid);
        signinpassedittext = findViewById(R.id.loginpasswordid);
        loginbutton = findViewById(R.id.loginbuttonid);
        signupbutton = findViewById(R.id.signupbuttonid);

        loginbutton.setOnClickListener(this);
        signupbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbuttonid:
                userlogin();
                break;

            case R.id.signupbuttonid:
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void userlogin() {
        String email = signinemailedittext.getText().toString().trim();
        String password = signinpassedittext.getText().toString().trim();

        userLoginHelper = UserLoginHelper.mGetUserLoginHelperInstance();

        if (email.equals("Admin") && password.equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, AddTestActivity.class);
            startActivity(intent);

            signinemailedittext.setText("");
            signinpassedittext.setText("");
        } else {
//        assert userLoginHelper != null;
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                userLoginHelper.setEmail(email);
                userLoginHelper.setPass(password);
                userLoginHelper.setiCallbackFromActivity(this);
                userLoginHelper.mUserCredentials();
            } else
                Toast.makeText(getApplicationContext(), "Error: Empty field detected!!", Toast.LENGTH_SHORT).show();

        }
    }

        @Override
        public void mCallBack ( boolean t){
            if (t) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Log in unsuccessful", Toast.LENGTH_SHORT).show();
        }

}