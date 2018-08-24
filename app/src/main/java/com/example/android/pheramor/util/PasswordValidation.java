package com.example.android.pheramor.util;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {

    public boolean isPasswordValid(CharSequence charSequence)
    {
        String regEx = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^a*()_]).{8,16}";

        Pattern pattern = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(charSequence);

        return (matcher.matches());
    }
}
