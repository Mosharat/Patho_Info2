package com.ruth.jahan.pathoinfo.adapter_pattern;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.ruth.jahan.pathoinfo.ICallbackFromActivity;
import com.ruth.jahan.pathoinfo.MainActivity;
import com.ruth.jahan.pathoinfo.R;
import com.ruth.jahan.pathoinfo.singleton_pattern.LoginActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, ICallbackFromActivity {

    private Button signupbutton;
    private TextView signintextview;
    private EditText signupemailedittext, signuppassedittext;
    public static String newuser;
    private FirebaseAuth mAuth;
    private UserRegHelper_adapter userRegHelper_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.setTitle("Sign Up");
        mAuth = FirebaseAuth.getInstance();

        signupemailedittext = findViewById(R.id.emailid);
        signuppassedittext = findViewById(R.id.signuppasswordid);
        signintextview = findViewById(R.id.signintextviewid);
        signupbutton = findViewById(R.id.finalsignupbuttonid);

        signupbutton.setOnClickListener(this);
        signintextview.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finalsignupbuttonid:
                userRegister();
                break;

            case R.id.signintextviewid:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void userRegister() {
        String email= signupemailedittext.getText().toString().trim();
        String password= signuppassedittext.getText().toString().trim();
        UserVerification_adaptee userVerification_adaptee = new UserVerification_adaptee();

        userRegHelper_adapter = new UserRegHelper_adapter(userVerification_adaptee, email, password, this, this);
        userRegHelper_adapter.mUserCredentials();
    }

    @Override
    public void mCallBack(boolean t) {
        if (t){
            Toast.makeText(getApplicationContext(),"Welcome to Patho Info",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(SignupActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}