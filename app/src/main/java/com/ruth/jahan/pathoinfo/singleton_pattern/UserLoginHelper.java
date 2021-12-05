package com.ruth.jahan.pathoinfo.singleton_pattern;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ruth.jahan.pathoinfo.ICallbackFromActivity;
import com.ruth.jahan.pathoinfo.adapter_pattern.IUserCredentials;

public class UserLoginHelper implements IUserCredentials {
    public String email;
    public String pass;
    private static UserLoginHelper userLoginHelper;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ICallbackFromActivity iCallbackFromActivity;

    //Singleton
    public static UserLoginHelper mGetUserLoginHelperInstance() {
        if (userLoginHelper == null) {
            userLoginHelper = new UserLoginHelper();
            return userLoginHelper;
        } else
            return null;
    }//end

    @Override
    public void mUserCredentials() {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                    iCallbackFromActivity.mCallBack(true);
                } else {
                   iCallbackFromActivity.mCallBack(false);
                }
            }

        });

    }


    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public void setiCallbackFromActivity(ICallbackFromActivity iCallbackFromActivity)
    {
        this.iCallbackFromActivity = iCallbackFromActivity;
    }

}
