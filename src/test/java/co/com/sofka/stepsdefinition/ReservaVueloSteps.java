package co.com.sofka.stepsdefinition;

import co.com.sofka.models.ReservaVuelo;
import co.com.sofka.pages.ReservaVueloPage;
import co.com.sofka.setup.ConfiWebDriver;
import co.com.sofka.utils.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaVueloSteps extends ConfiWebDriver {
    private ReservaVuelo reservaVueloEsc1;
    private ReservaVuelo reservaVueloEsc2;
    private static  final Logger logger = LogManager.getLogger(ReservaVueloSteps.class);
    //consultar vuelo
    @Given("el cliente está en el formulario de consulta de vuelos")
    public void elClienteEstaEnElFormularioDeConsultaDeVuelos() {
        try {
            generalSetup();

        } catch (Exception e) {
            quiteDriver();
        }
    }

    @When("el cliente llene los campos del formulario con fechas disponibles y lo envíe")
    public void elClienteLleneLosCamposDelFormularioConFechasDisponiblesYLoEnvie() {
        try {
            generarReservaEsc1();
            ReservaVueloPage reservaVueloPage = new ReservaVueloPage(driver, reservaVueloEsc1);
            reservaVueloPage.llenarFormularioReservaVuelo();


        } catch (Exception e) {
            quiteDriver();
            logger.warn("error en los datos requeridos",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @When("el cliente ingrese los datos en los campos del formulario con fechas disponibles y lo envíe")
    public void elClienteIngreseLosDatosEnLosCamposDelFormularioConFechasDisponiblesYLoEnvie() {
        try {
            generarReservaEsc2();
            ReservaVueloPage reservaVueloPage = new ReservaVueloPage(driver, reservaVueloEsc2);
            reservaVueloPage.llenarFormularioReservaVuelo();


        } catch (Exception e) {
            quiteDriver();
            logger.warn("error en los datos requeridos",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @Then("se deberá mostrar en el detalle de cada opción de compra el número de asientos de adultos y niños según edad")
    public void seDeberaMostrarEnElDetalleDeCadaOpcionDeCompraElNumeroDeAsientosDeAdultosYNinosSegunEdad() {
        try{
            ReservaVueloPage reservaVueloPage = new ReservaVueloPage(driver, reservaVueloEsc1);
            //se obtiene la lista de elementos de la pagina
            List<String[]> listaCamposPasajeros = reservaVueloPage.listDetailPassengers();
            int indiceNoAdultos = IndicesResumenPasajeros.NO_ADULTOS.getValue();
            int indiceNoANinos = IndicesResumenPasajeros.NO_NINOS.getValue();
            //se extrae en una nueva lista solo los campos de la cantidad de asientos de adultos y niños
            List<String[]> listaComparar = new ArrayList<>();
            for (String[] listaCamposPasajero : listaCamposPasajeros) {
                String[] lista = new String[2];
                lista[0] = listaCamposPasajero[indiceNoAdultos];
                lista[1] = listaCamposPasajero[indiceNoANinos];
                listaComparar.add(lista);
            }
            //evaluar todas los detalles que se muestran en pantalla contra los datos suministrados
            String []  resumenReservaEsperado = resumenReserva(reservaVueloEsc1);
            int i=0;
            for(String [] respuesta: listaComparar){
                Assertions.assertArrayEquals(resumenReservaEsperado,respuesta);
                i++;
                logger.info("calculo de asientos según edad: "+
                     "<esperado><"+resumenReservaEsperado[0]+","+resumenReservaEsperado[1]+">"+
                     "<obtenido><"+respuesta[0]+","+respuesta[1]+">");
            }

            logger.info("total evaluado: "+i);
            quiteDriver();
        }catch (Exception e){
            quiteDriver();
            logger.warn("error en la validación del número asientos de adultos y niños",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @Then("se deberá mostrar en el detalle de cada opción de compra el precio final de los tiquetes")
    public void seDeberaMostrarEnElDetalleDeCadaOpcionDeCompraElPrecioFinalDeLosTiquetes() {
        try{
            ReservaVueloPage reservaVueloPage = new ReservaVueloPage(driver, reservaVueloEsc2);
            //se obtiene la lista de elementos de la pagina
            List<String[]> listaCamposPasajeros = reservaVueloPage.listDetailPassengers();
            int indicePrecioAdultos = IndicesResumenPasajeros.PRECIO_ADULTOS.getValue();
            int indicePrecioNinos = IndicesResumenPasajeros.PRECIO_NO_NINOS.getValue();
            int indicePrecioImpuestos=IndicesResumenPasajeros.PRECIO_IMPUESTO.getValue();
            int indicePrecioFinal=IndicesResumenPasajeros.PRECIO_FINAL.getValue();
            //se extrae en una nueva lista solo los campos relacionados a los precios
            List<String[]> listaComparar = new ArrayList<>();
            for (String[] listaCamposPasajero : listaCamposPasajeros) {
                String[] lista = new String[4];
                lista[0] = listaCamposPasajero[indicePrecioAdultos];
                lista[1] = listaCamposPasajero[indicePrecioNinos];
                lista[2] = listaCamposPasajero[indicePrecioImpuestos];
                lista[3] = listaCamposPasajero[indicePrecioFinal];
                listaComparar.add(lista);
            }
            //evaluar todas los detalles que se muestran en pantalla contra los datos suministrados
            int i=0;
            for(String [] respuesta: listaComparar){
                int precioFinalComparar= ToolText.FormatToInt(respuesta[0])
                        +ToolText.FormatToInt(respuesta[1])
                        +ToolText.FormatToInt(respuesta[2]);
                Assertions.assertEquals(precioFinalComparar,ToolText.FormatToInt(respuesta[3]));
                i++;
                logger.info("Calculo de precio final: "+
                        "<esperado><"+precioFinalComparar+">"+
                        "<obtenido><"+ToolText.FormatToInt(respuesta[3])+">");
            }
            quiteDriver();
            logger.info("total evaluado: "+i);
        }catch (Exception e){
            quiteDriver();
            logger.warn("error en la verificación de precio final de tiquetes",e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    private void generarReservaEsc1(){
        reservaVueloEsc1 = new ReservaVuelo();
        reservaVueloEsc1.setModalidad(Modalidad.IDA_Y_VUELTA);
        reservaVueloEsc1.setOrigen(new Ubicacion("Barranquilla","Atlántico","Colombia"));
        reservaVueloEsc1.setDestino(new Ubicacion("Bogotá","Bogotá D.C.","Colombia"));
        reservaVueloEsc1.setFechaIda(LocalDate.of(2022,1,15));
        reservaVueloEsc1.setFechaVuelta(LocalDate.of(2022,1,25));
        reservaVueloEsc1.setNoAdultos(2);
        reservaVueloEsc1.setClaseViaje(ClaseViaje.ECONOMICA);
        reservaVueloEsc1.AgregarMenorEdad(Edades.OCHO_AGNIO);
        reservaVueloEsc1.AgregarMenorEdad(Edades.QUINCE_AGNIO);

    }
    private void generarReservaEsc2(){
        reservaVueloEsc2 = new ReservaVuelo();
        reservaVueloEsc2.setModalidad(Modalidad.IDA_Y_VUELTA);
        reservaVueloEsc2.setOrigen(new Ubicacion("Bogotá","Bogotá D.C.","Colombia"));
        reservaVueloEsc2.setDestino(new Ubicacion("Cartagena de Indias","Bolívar","Colombia"));
        reservaVueloEsc2.setFechaIda(LocalDate.of(2022,3,15));
        reservaVueloEsc2.setFechaVuelta(LocalDate.of(2022,3,20));
        reservaVueloEsc2.setNoAdultos(1);
        reservaVueloEsc2.setClaseViaje(ClaseViaje.ECONOMICA);
        reservaVueloEsc2.AgregarMenorEdad(Edades.OCHO_AGNIO);
        reservaVueloEsc2.AgregarMenorEdad(Edades.DIEZ_Y_SIETE_AGNIO);
    }

    //Datos suministrados para la prueba
    private String[]  resumenReserva(ReservaVuelo reservaVuelo) {
        String[] detalle = new String[2];
        int cont = 0;
        for (int i = 0; i < reservaVuelo.getMenoresEdad().size(); i++) {
            if (reservaVuelo.getMenoresEdad().get(i + 1) >= 12) {
                cont++;
            }
        }
            int noAdultos = reservaVuelo.getNoAdultos() + cont;
            int noNinos = reservaVuelo.getMenoresEdad().size() - cont;

            String  StringAdulto = noAdultos>1 ?"Adultos":"Adulto";
            String  StringNino = noNinos>1 ?"Niños":"Niño";
            detalle[0] = noAdultos +" "+ StringAdulto;
            detalle[1] = noNinos +" "+ StringNino;
            return detalle;
    }
}

