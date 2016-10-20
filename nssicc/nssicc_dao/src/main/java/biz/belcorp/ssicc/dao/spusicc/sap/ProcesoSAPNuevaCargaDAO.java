package biz.belcorp.ssicc.dao.spusicc.sap;

import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.Base;

/**
* @author <a href="mailto:croman@belcorp.biz">Christian Roman</a>
**/
 
public interface ProcesoSAPNuevaCargaDAO {

	 /**
     * Metodo que obtiene las cantidades de registros aptos y enviados
     * @param criteria
     * @return
     */
    public Base getCantidadRegistros(Map criteria);


    /**
     * Metodo que realiza la modificacion de registros de la tabla FAC_DOCUM_CONTA_CABEC
     * @param params
     */
    public void executeUpdateRegistros(Map params);

}