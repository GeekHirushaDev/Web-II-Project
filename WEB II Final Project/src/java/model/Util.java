package model;

public class Util {

    public static String generateCode() {
        int r = (int) (Math.random() * 1000000);
        String code = String.format("%06d", r);
        return code;
    }
    
    public static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }
    
    public static boolean isPasswordValid(String password){
        return password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    }
}
