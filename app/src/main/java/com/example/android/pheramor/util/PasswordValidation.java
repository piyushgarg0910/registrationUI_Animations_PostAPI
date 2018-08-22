package com.example.android.pheramor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {

    public boolean isPasswordValid(CharSequence charSequence)
    {
        String regExpn = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()])(?=.{8,})";//((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20}

        String regEx = "(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^a*()_]).{8,16}";


        Pattern pattern = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(charSequence);

        return (matcher.matches());
    }
}
