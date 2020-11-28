package com.ceiba.laboratorio.commonCalendar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilCalendar {

    public static String formatearCalendar(Calendar c) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        return df.format(c.getTime());
    }

    public static boolean isDomingo(Calendar c) {
        int dia = c.get(Calendar.DAY_OF_WEEK);
        return dia == Calendar.SUNDAY;
    }

    // formato de fecha:  13 de diciembre de 2020
    public static String agregarQuinceDias(Calendar c) {
        c.add(Calendar.DAY_OF_YEAR, 15);
        return formatearCalendar(c);
    }

    public static Date agregarQuinceDiasDate(Calendar c) {
        c.add(Calendar.DAY_OF_YEAR, 15);
        return c.getTime();
    }
}
