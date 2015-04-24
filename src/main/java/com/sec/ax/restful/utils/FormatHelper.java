package com.sec.ax.restful.utils;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author heesik.jeon
 *
 */

public class FormatHelper {
    
    private static final Logger logger = Logger.getLogger(FormatHelper.class);
    
    /**
     * @param object
     * @return
     */
    public static String printPretty(Object object) {
        
        logger.debug("..");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(object);
        
        return json;
        
    }
    
    /**
     * @param base (Numeral System)
     * @param decimal
     * @return
     */
    public static String convertNumeral(int base, int decimal) {
        
        StringBuffer stack = new StringBuffer();
        
        while (decimal >= base) {
            
            char c;
            
            if (decimal % base >= 10) {
                
                if (decimal % base >= 36) { // a
                    c = (char) (decimal % base + '\u0041' - 4);
                } else { // A
                    c = (char) (decimal % base + '\u0041' - 10);
                }
                
            } else {
                c = (char) (decimal % base + '\u0030');
            }

            stack.append(c);
            
            decimal = decimal / base;
            
        }
        
        if (decimal >= 0) {
            
            char c;
            
            if (decimal % base >= 10) {
                
                if (decimal % base >= 36) { // a
                    c = (char) (decimal % base + '\u0041' - 4);
                } else { // A
                    c = (char) (decimal % base + '\u0041' - 10);
                }
                
            } else {
                c = (char) (decimal % base + '\u0030');
            }

            stack.append(c);

        }

        return stack.reverse().toString();
        
    }

    /**
     * @param base (Numeral System)
     * @param number
     * @return
     */
    public static long invertNumeral(int base, String number) {
        
        long decimal = 0;
        char[] c = number.toCharArray();

        for (int i = 0; i < c.length; i++) {
            
            if ((c[i] >= '\u0030') && (c[i] <= '\u0039')) {    // 0-9
                decimal = decimal * base + c[i] - '\u0030';
            } else if (c[i] >= '\u0041' && c[i] <= '\u005A') { // A-Z
                decimal = decimal * base + c[i] - '\u0041' + 10;
            } else if (c[i] >= '\u0061' && c[i] <= '\u007A') { // a-z
                decimal = decimal * base + c[i] - '\u0041' + 4;
            }
            
        }

        return decimal;
        
    }

}
