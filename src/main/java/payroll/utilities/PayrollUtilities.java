/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JDM
 */
public class PayrollUtilities {

    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyy");
        return df.format(date);
    }

    public static Date stringToDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyy");
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(PayrollUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
