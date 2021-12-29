package co.com.sofka.utils;

public enum ClaseViaje {

    ECONOMICA(1),
    PREMIUM_ECONOMY(2),
    EJECUTIVA(3),
    PRIMERA_CLASE(4);
    private final int value;

    public int getValue() {
        return value;
    }
    ClaseViaje(int value) {
        this.value = value;
    }
}
