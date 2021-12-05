package com.ruth.jahan.pathoinfo.adapter_pattern;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.ruth.jahan.pathoinfo.ICallbackFromActivity;

public class UserRegHelper_adapter implements IUserCredentials {
    private UserVerification_adaptee userVerification_adaptee;
    private String email;
    private String pass;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Context context;
    private ICallbackFromActivity iCallbackFromActivity;

    public UserRegHelper_adapter(UserVerification_adaptee userVerification_adaptee, String email, String pass,
                                 ICallbackFromActivity iCallbackFromActivity, Context context)
    {
        this.userVerification_adaptee = userVerification_adaptee;
        this.email = email;
        this.pass = pass;
        this.context = context;
        this.iCallbackFromActivity = iCallbackFromActivity;
    }
    @Override
    public void mUserCredentials() {
        boolean isVerified = userVerification_adaptee.mVerify(email, pass);
        if (isVerified)
        {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Registered", Toast.LENGTH_SHORT).show();
                        iCallbackFromActivity.mCallBack(true);
                    }
                    else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(context,"User is already registered",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }

                }
            });
        } else{
            Toast.makeText(context, "Empty field detected!!", Toast.LENGTH_SHORT).show();

        }
    }
}
