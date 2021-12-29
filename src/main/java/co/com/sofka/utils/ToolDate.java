package co.com.sofka.utils;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class ToolDate {

    public static int minusDateActual(LocalDate fecha){
        LocalDate fechaActual = LocalDate.now();
            return minusDates(fechaActual,fecha);
    }
    public static int minusDates(LocalDate inicio, LocalDate fin){
        long restaMes =MONTHS.between(inicio,fin);
        return (int) restaMes;
    }
    public static int PressNextMonth(LocalDate inicio, LocalDate fin){
        long difDias = DAYS.between(inicio,fin );
        if((inicio.getDayOfMonth()+difDias)>30){
            return 1;
        }else{
            return 0;
        }
    }
    public static int PressNextMonthInit(LocalDate fecha){
        LocalDate fechaActual = LocalDate.now();
        return PressNextMonth(fechaActual,fecha);
    }
    public static String CreateLocatorDate(int day){
        return "//*[@id=\"component-modals\"]/div[1]/div[1]/div[2]/div[1]/div[3]/div["+day+"]/div[1]";
    }
}
