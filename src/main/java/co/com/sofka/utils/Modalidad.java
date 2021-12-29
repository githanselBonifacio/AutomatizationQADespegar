package co.com.sofka.utils;

public enum Modalidad {

    IDA_Y_VUELTA("idaVuelta"),
    SOLO_IDA("soloIda"),
    MULTIDESTINO("multidestino");

    private final String value;

    public String getValue() {
        return value;
    }

    Modalidad(String value) {
        this.value = value;
    }

}
