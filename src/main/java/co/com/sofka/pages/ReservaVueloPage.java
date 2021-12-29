package co.com.sofka.pages;

import co.com.sofka.models.ReservaVuelo;
import co.com.sofka.pages.common.CommonActionOnpages;
import co.com.sofka.utils.ToolDate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.List;

public class ReservaVueloPage extends CommonActionOnpages {

    private final ReservaVuelo reservaVuelo;

    //localizadores para ingresar datos

    private final By origen = By.xpath("//*[@id=\"searchbox-sbox-box-flights\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[1]/div[1]/div/input");
    private final By destino = By.xpath("//*[@id=\"searchbox-sbox-box-flights\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[2]/div/div/input");
    private final By campoFechaIda = By.xpath("//*[@id=\"searchbox-sbox-box-flights\"]/div/div[2]/div[1]/div[1]/div[2]/div/div[1]/div/div/div");
    private final By btnAplicarFechas = By.xpath("//*[@id=\"component-modals\"]/div[1]/div[2]/div[1]/button");
    private  final By moverMesDerecha = By.cssSelector("#component-modals > div.sbox5-floating-tooltip.sbox5-floating-tooltip-opened > div.sbox5-3-floating-tooltip-datepicker-wrapper > div.calendar-container > a.calendar-arrow-right > svg");
    private final By campoPasajerosClase = By.xpath("//*[@id=\"searchbox-sbox-box-flights\"]/div/div[2]/div[1]/div[1]/div[4]/div/div/div/div/input");
    private final By btnAgregarAdulto = By.xpath("//*[@id=\"component-modals\"]/div[3]/div/div/div[1]/div[1]/div[2]/div/button[2]");
    private final By btnAgregarMenor = By.xpath("//*[@id=\"component-modals\"]/div[3]/div/div/div[1]/div[2]/div[2]/div/button[2]");
    private final By campoClaseViaje = By.xpath("//*[@id=\"component-modals\"]/div[3]/div/div/div[2]/div[2]/div/div/div/select");
    private final By opcionEconomica = By.xpath("//option[@value=\"YC\"]");
    private final By opcionPremiumEconomy = By.xpath("//option[@value=\"PE\"]");
    private final By opcionEjecutiva = By.xpath("//option[@value=\"C\"]");
    private final By opcionPrimeraClase = By.xpath("//option[@value=\"F\"]");
    private final By btnAplicarformularioPasajeros = By.xpath("//*[@id=\"component-modals\"]/div[3]/div/div/div[3]/a");
    private final By btnBuscarViaje = By.xpath("//*[@id=\"searchbox-sbox-box-flights\"]/div/div[2]/div[3]/button");
    private final By autocompletarUbicacion = By.xpath("/html/body/div[4]/div/div[1]/ul/li[1]");

    //localizadores para salidas de datos a pruebas
    private final By detallePasajeros = By.xpath("//div[@class=\"fare-detail-items\"]");

    private static  final Logger logger = LogManager.getLogger(ReservaVueloPage.class);
    //construtor
    public ReservaVueloPage(WebDriver driver,ReservaVuelo reservaVuelo) {
        super(driver);
        this.reservaVuelo = reservaVuelo;
    }
    public void llenarFormularioReservaVuelo(){
        Click(destino);
        TypeInto(destino,reservaVuelo.getDestino().toString());
        AddWaitByVisibility(autocompletarUbicacion,5);
        Click(autocompletarUbicacion);

        Click(origen);
        TypeInto(origen,reservaVuelo.getOrigen().toString());
        AddWaitByVisibility(autocompletarUbicacion,5);
        Click(autocompletarUbicacion);

        Click(campoFechaIda);
        //ingresar fechas
        ScrollTo(moverMesDerecha);
        //Buscar mes y año
        int diferenciaMesActual = ToolDate.minusDateActual(reservaVuelo.getFechaIda());
        int difenciaMesIdaVuelta = ToolDate.minusDates(reservaVuelo.getFechaIda(),reservaVuelo.getFechaVuelta());
        AddWaitByVisibility(moverMesDerecha,8);

        //posicionar mes inicial
        if (diferenciaMesActual==0){
            RepeatClick(moverMesDerecha,ToolDate.PressNextMonthInit(reservaVuelo.getFechaIda()));
        }else{
            RepeatClick(moverMesDerecha,diferenciaMesActual+1);
        }
        //ingresar dias
        String locatorStringDiaIda = ToolDate.CreateLocatorDate(reservaVuelo.getFechaIda().getDayOfMonth());
        Click(By.xpath(locatorStringDiaIda));//dia de ida

        if (difenciaMesIdaVuelta==0){
            RepeatClick(moverMesDerecha,ToolDate.PressNextMonth(reservaVuelo.getFechaIda(),reservaVuelo.getFechaVuelta()));
        }
        else {
            RepeatClick(moverMesDerecha,difenciaMesIdaVuelta+1);
        }
        String locatorStringDiaVuelta = ToolDate.CreateLocatorDate(reservaVuelo.getFechaVuelta().getDayOfMonth());
        Click(By.xpath(locatorStringDiaVuelta));//dia de vuelta

        ScrollTo(btnAplicarFechas);
        AddWaitByVisibility(btnAplicarFechas,3);
        Click(btnAplicarFechas);

        //numero de pasajeros
        ScrollTo(campoPasajerosClase);
        Click(campoPasajerosClase);
        AddWaitByVisibility(btnAgregarAdulto,5);
        RepeatClick(btnAgregarAdulto,(reservaVuelo.getNoAdultos()-1));
        int noMenores = reservaVuelo.getMenoresEdad().size();
        RepeatClick(btnAgregarMenor,noMenores);

        for (int i = 1;i<(noMenores+1);i++) {
            int index = i + 2;
            String locatorString = "//*[@id=\"component-modals\"]/div[3]/div/div/div[1]/div["+ index +"]/div[2]/div/div/select";
            HashMap<Integer, Integer> menores = (HashMap<Integer, Integer>) reservaVuelo.getMenoresEdad();
            By locator = By.xpath(locatorString);
            Click(By.xpath(locatorString));
            TypeInto(locator, String.valueOf(menores.get(i)));
            PressEnter(locator);
        }
        Click(campoClaseViaje);
        int clase = reservaVuelo.getClaseViaje().getValue();

        switch (clase){
            case 1:
                Click(opcionEconomica);

                break;
            case 2:
                Click(opcionPremiumEconomy);

                break;
            case 3:
                Click(opcionEjecutiva);

                break;
            case 4:
                Click(opcionPrimeraClase);
                break;
            default: logger.info("Las opción: "+clase+" no es valida. Verificar opciones en el enumrable ClaseViaje");
        }
        PressScope(campoClaseViaje);
        Click(btnAplicarformularioPasajeros);
        Click(btnBuscarViaje);
    }

    public List<String[]> listDetailPassengers(){

        try{
            AddWaitByVisibility(detallePasajeros,15);

        }catch (Exception e){
            logger.warn("No existe vuelo concidente con ese rango de fechas, elemento buscado no visible"+e);
            throw  e;
        }
        return  getTextElements(detallePasajeros);
        }

}
