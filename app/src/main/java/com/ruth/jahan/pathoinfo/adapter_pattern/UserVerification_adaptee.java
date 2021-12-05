package com.ruth.jahan.pathoinfo.adapter_pattern;

import android.text.TextUtils;

public class UserVerification_adaptee {

    public boolean mVerify(String email, String pass) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            return true;
        } else
            return false;
    }
}

