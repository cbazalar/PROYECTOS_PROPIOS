package biz.belcorp.ssicc.dao.spusicc.sap;

import java.util.Map;

/**
* @author <a href="mailto:croman@belcorp.biz">Christian Roman</a>
**/
 
public interface ReporteSAPUnidadesDespachoCodigosDAO {
    
    /**
	 * Genera la data para el Reporte de Unidades a despachar de codigos SAP
	 * @param map
	 */
    public void executeRegistrosUnidadesDespachoCodigosSAP(Map params); 
}