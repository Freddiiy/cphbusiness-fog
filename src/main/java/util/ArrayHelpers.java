package util;

import java.util.List;

public class ArrayHelpers {

    public static boolean stringInArray(String s, String[] arr) {
        for (String str : arr) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
