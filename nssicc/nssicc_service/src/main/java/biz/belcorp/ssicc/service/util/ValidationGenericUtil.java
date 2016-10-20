package biz.belcorp.ssicc.service.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class ValidationGenericUtil {

	/**
     * Validate the string is not null, if string is null return null.
     * 
     * @param  object
     * @return string
     * @author frank.ayala
     */
    public static String validateNullString(String cadena){
    	
    	if(StringUtils.isBlank(cadena) || "null".equalsIgnoreCase(cadena)){
    		return null;
    	}else{
    		return cadena;
    	}
    }
    
    /**
     * Valida que los valores del map tengan algun valor, si todos los valores del map
     * estan en null o en "0", entonces se dara return false; se esta pasando tambien
     * una lista de los campos que no debemos de considerar en la evaluacion
     * 
     * @param map
     * 			map con los valores a evaluar
     * @param listFieldNoConsidered
     * 			lista de campos que no debemos considerar
     * @return boolean
     * 			retornara true si encuentra por lo menos un 
     * 			valor no nulo ni cero en todo el map
     * @author frank.ayala
     */
    public static boolean validateValuesMapNotNull(Map map, List listFieldNoConsidered){
    	
    	if(map != null){
    		
    		try{
    		
    		Set keySet = map.keySet();
    		List listSet = new ArrayList(keySet);
    		
    		for (int i = 0; i < listSet.size(); i++) {
    			
    			if(!listFieldNoConsidered.contains(listSet.get(i))){
    				
    				Object obj = map.get(listSet.get(i));        			
        			if(obj instanceof String){
        				if(obj != null){
            				return true;
            			}
        			}else if(obj instanceof BigDecimal){
        				if(obj != null && obj != BigDecimal.ZERO){
            				return true;
            			}
        			}else if(obj instanceof Long){
        				if(obj != null && ((Long)obj).intValue() != 0){
            				return true;
            			}
        			}else{
        				
        				// Agregar mas tipos de datos de ser necesarios    				
        				if(obj != null){
            				return true;
            			}
        			} 
    			}   			  			    			
			}
    		
    		}catch(Exception e){
    			return false;
    		}
    	}
    	
    	return false;
    }
}
