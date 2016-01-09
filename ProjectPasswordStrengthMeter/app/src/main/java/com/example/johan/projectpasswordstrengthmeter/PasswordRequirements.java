package com.example.johan.projectpasswordstrengthmeter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by johan on 2016-01-05.
 */
public class PasswordRequirements {
    private int minCharacters;

    public PasswordRequirements(){
        minCharacters = 5;
    }

    public boolean containsMinimum(String s){
        if ( s.length()>= minCharacters){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean containsUpper(String s){
        for(int i=0; i < s.length(); i++){
            if(Character.isUpperCase(s.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public boolean containsLower(String s){
        for(int i=0; i < s.length(); i++){
            if(Character.isLowerCase(s.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public boolean containsNumber(String s){
        for(int i=0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public boolean containsSpecial(String s){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        boolean b = m.find();

        if (b){
            return true;
        }
        else {
            return false;
        }
    }

    public int passwordStrengthLevel(String s){
        int strengthLevel = 0;
        if(containsMinimum(s)){
            strengthLevel++;
        }

        if(containsUpper(s)){
            strengthLevel++;
        }

        if(containsLower(s)){
            strengthLevel++;
        }

        if(containsNumber(s)){
            strengthLevel++;
        }

        if(containsSpecial(s)){
            strengthLevel++;
        }

        return strengthLevel;
    }
}
