package co.com.sofka.models;

import co.com.sofka.utils.ClaseViaje;
import co.com.sofka.utils.Edades;
import co.com.sofka.utils.Modalidad;
import co.com.sofka.utils.Ubicacion;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReservaVuelo {
    private Modalidad modalidad;
    private Ubicacion origen;
    private Ubicacion destino;
    private LocalDate fechaIda;
    private LocalDate fechaVuelta;
    private Integer noAdultos;
    private Map<Integer,Integer> menoresEdad =  new HashMap<>();
    private ClaseViaje claseViaje;

    public void AgregarMenorEdad(Edades edad){
            if (menoresEdad.isEmpty()){
                menoresEdad.put(1,edad.getValue());
            }else{
                Set<Integer> claves = menoresEdad.keySet();
                int siguienteClave = claves.size() + 1;
                menoresEdad.put(siguienteClave,edad.getValue());
            }

    }
    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Ubicacion getOrigen() {
        return origen;
    }

    public void setOrigen(Ubicacion origen) {
        this.origen = origen;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    public LocalDate getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(LocalDate fechaIda) {
        this.fechaIda = fechaIda;
    }

    public LocalDate getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(LocalDate fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public Integer getNoAdultos() {
        return noAdultos;
    }

    public void setNoAdultos(Integer noAdultos) {
        this.noAdultos = noAdultos;
    }

    public Map<Integer, Integer> getMenoresEdad() {
        return menoresEdad;
    }

    public ClaseViaje getClaseViaje() {
        return claseViaje;
    }

    public void setClaseViaje(ClaseViaje claseViaje) {
        this.claseViaje = claseViaje;
    }

    @Override
    public String toString() {
        return "ReservaVuelo{" +
                "modalidad=" + modalidad +
                ", origen=" + origen +
                ", destino=" + destino +
                ", fechaIda=" + fechaIda +
                ", fechaVuelta=" + fechaVuelta +
                ", noAdultos=" + noAdultos +
                ", menoresEdad=" + menoresEdad +
                ", claseViaje=" + claseViaje +
                '}';
    }
}