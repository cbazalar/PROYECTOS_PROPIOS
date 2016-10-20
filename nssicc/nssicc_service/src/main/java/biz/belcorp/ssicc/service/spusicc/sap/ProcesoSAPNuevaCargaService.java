package biz.belcorp.ssicc.service.spusicc.sap;

import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.framework.Service;

/**
* @author <a href="mailto:croman@belcorp.biz">Christian Roman</a>
**/

public interface ProcesoSAPNuevaCargaService extends Service {

   
    /**
     * Metodo que obtiene las cantidades de registros aptos y enviados
     * @param criteria
     * @return
     */
    public Base getCantidadRegistros(Map criteria);


    /**
     * Metodo que realiza la modificacion de registros de la tabla 
     * @param params
     */
    public void executeUpdateRegistros(Map params);

}
