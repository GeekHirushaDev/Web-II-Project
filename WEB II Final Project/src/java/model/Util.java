package model;

public class Util {

    public static String generateCode() {
        int r = (int) (Math.random() * 1000000);
        String code = String.format("%06d", r);
        return code;
    }
}
