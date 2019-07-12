package com.poc.app.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by developers kathirvel 01-10-18
 */
public class ValidateHelper {
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//    public static final String NAME_REGEX = "^[_A-Za-z0-9-\\+]";
    private static final String NAME_REGEX="^[A-Za-z0-9\\s]{1,}[\\.]{0,1}[A-Za-z0-9\\s]{0,}$";
    private static final String CHAR_REGEX=".*[a-zA-Z]+.*";
    private static final String ONLY_CHAR_REGEX="^[a-zA-Z ]*$";
    private static final String MOBILE_REGEX="\\d{10}";
    private static final String PINCODE_REGEX="^([1-9])([0-9]){5}$";
    private static final String EMOG_REGEX = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";


    public static boolean isValidEmail(String email){
        if(email.matches(EMAIL_REGEX))
            return true;
        else
            return false;
    }

    public static boolean isValidMobile(String mobile){

        if(mobile.matches(MOBILE_REGEX))
            return true;
        else
            return false;
    }

    public static boolean isValidPincode(String pincode){
        if(pincode.matches(PINCODE_REGEX))
            return true;
        else
            return false;
    }

    public static boolean isValidName(String name){
        if(name.matches(NAME_REGEX) && name.matches(CHAR_REGEX))
            return true;
        else
            return false;
    }

    public static boolean isValidAddress(String name){
        if(name.matches(NAME_REGEX))
            return true;
        else
            return false;
    }

    public static boolean isOnlyChars(String data){
        if(data.matches(ONLY_CHAR_REGEX))
            return true;
        else
            return false;
    }
    public static boolean isContainsEmogi(String emogi){
        boolean isEmoji=false;
        Matcher matchEmo = Pattern.compile(EMOG_REGEX).matcher(emogi);
        while (matchEmo.find()) {
            isEmoji=true;
            break;
        }
        return isEmoji;
    }

}
