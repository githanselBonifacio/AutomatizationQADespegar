package co.com.sofka.utils;

public class ToolText {

    public static Integer FormatToInt(String cadena){
        return Integer.parseInt(cadena.replaceAll("[-+.^:,$]",""));
    }
}
