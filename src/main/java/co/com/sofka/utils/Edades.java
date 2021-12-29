package co.com.sofka.utils;

public enum Edades {
    HASTA_UN_AGNIO(0),
    UN_AGNIO(1),
    DOS_AGNIO(2),
    TRES_AGNIO(3),
    CUATRO_AGNIO(4),
    CINCO_AGNIO(5),
    SEIS_AGNIO(6),
    SIETE_AGNIO(7),
    OCHO_AGNIO(8),
    NUEVE_AGNIO(9),
    DIEZ_AGNIO(10),
    ONCE_AGNIO(11),
    DOCE_AGNIO(12),
    TRECE_AGNIO(13),
    CATORCE_AGNIO(14),
    QUINCE_AGNIO(15),
    DIEZ_Y_SEIS_AGNIO(16),
    DIEZ_Y_SIETE_AGNIO(17);

    private final int value;

    public int getValue() {
        return value;
    }
    Edades(int value) {
        this.value = value;
    }
}
