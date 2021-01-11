package com.example.transactional;

public class StringUtils extends org.springframework.util.StringUtils {

    public static String surroundWithQuotes(String str) {
        if (str == null) return null;
        return "\"" + str + "\"";
    }

    public static Character getLastCharacter(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str.charAt(str.length() - 1);
    }

    public static String getLastCharacters(String str, int nbCharacters) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() <= nbCharacters) {
            return str;
        }
        return str.substring(str.length() - nbCharacters);
    }

}
