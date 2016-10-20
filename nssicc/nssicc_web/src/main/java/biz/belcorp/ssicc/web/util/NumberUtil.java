package biz.belcorp.ssicc.web.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase Utilitaria para Numeros y derivados.
 * @author Sigcomt_PC03
 *
 */

public class NumberUtil {

	/**
	 * Metodo que se encarga de convertir el numero ingresado en un formato decimal definido
	 * @param valor
	 * @return
	 */
	 public static String NumberToString(double valor){
	    	DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
			simbolos.setDecimalSeparator('.');		
			NumberFormat formateador = new DecimalFormat("#.00",simbolos);
	    	return formateador.format(valor);
	    }

}
