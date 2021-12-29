package co.com.sofka.utils;

public class Ubicacion {
    private String ciudad;
    private String region;
    private String pais;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Ubicacion(String ciudad, String region, String pais) {
        this.ciudad = ciudad;
        this.region = region;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "  "+ciudad + ", " + region + ", " + pais;
    }
}
