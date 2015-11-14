/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Adriano_2
 */
public class Format {
    public static String getString(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }
    
    public static boolean getBoolean(Object value) {
        try {
            boolean v = (Boolean) value;
            return v;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public static int getInt(String value, int defval) {
        try {
            return Integer.parseInt(value);
        }
        catch (Exception e) {
            return defval;
        }
    }
    
    public static int getInt(Object value, int defval) {
        try{
            return getInt((String) value, defval);
        }
        catch (Exception e) {
            return defval;
        }
    }
    
    public static int getInt(String value) {
        return getInt(value, 0);
    }
    
    public static int getInt(Object value) {
        return getInt(value, 0);
    }
}
