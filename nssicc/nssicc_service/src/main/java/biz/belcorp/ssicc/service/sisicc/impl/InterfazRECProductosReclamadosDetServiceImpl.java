package biz.belcorp.ssicc.service.sisicc.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;


/**
 * Service de la Interfaz REC-3 - Productos Reclamados Detalle (Operacion Reclamo).
 *
 * @author  <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 *
 */
@Service("sisicc.interfazRECProductosReclamadosDetService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRECProductosReclamadosDetServiceImpl
    extends BaseInterfazSalidaStoredProcedureAbstractService {
   

    /**
     * Obtiene la data para la Interfaz REC-3 - Productos Reclamados Detalle (Operacion Reclamo).
     *
     * @param params
     *            Map con los parametros del query
     * @return List con Maps de las filas retornadas
     *
     * @throws InterfazException
     */
/*    
    public List readData(Map params) throws InterfazException {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'readData' method ");
        }

        List listProductosDetReclamados = null;

        try {
            listProductosDetReclamados = this.interfazSiCCDAO.getRECProductosDetList(params);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error en readData " + e.getMessage());
        }
        return listProductosDetReclamados;
    }
*/
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazRECProductosReclamadosDet(params);
	}

    protected Map prepareQueryParams(InterfazParams interfazParams)  throws InterfazException  {
        // TODO Auto-generated method stub
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime() +
                (timestamp.getNanos() / 1000000));

        Map mapQueryParams = super.prepareQueryParams(interfazParams);
        mapQueryParams.put("numeroLote", interfazParams.getNumeroLote());
        mapQueryParams.put("fechaEnvio",
            DateUtil.getDateTime(Constants.PATRON_FECHA_AAAAMMDD, date));

        if (log.isDebugEnabled()) {
            log.debug("mapQueryParams en RecService=" + mapQueryParams);
        }

        return mapQueryParams;
    }

    
}
