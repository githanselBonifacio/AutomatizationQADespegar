package co.com.sofka.utils;

public enum IndicesResumenPasajeros {
    NO_ADULTOS(0),
    PRECIO_ADULTOS(1),
    NO_NINOS(2),
    PRECIO_NO_NINOS(3),
    STRING_IMPUESTO(4),
    PRECIO_IMPUESTO(5),
    STRING_PRECIO_FINAL(6),
    PRECIO_FINAL(7);

    private final int value;

    public int getValue() {
        return value;
    }
    IndicesResumenPasajeros(int value) {
        this.value = value;
    }
}
