package model;

public class Util {

    public static String generateCode() {

        int random = (int) (Math.random() * 1000000);
        return String.format("%06d", random);
    }
   public static boolean isEmailValid(String email){
      return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
   }

     public static boolean isPasswordValid(String password){
      return password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
   }
     public static boolean isCodeValid(String code) {
        return code.matches("^\\d{4,5}$");
    }
}
