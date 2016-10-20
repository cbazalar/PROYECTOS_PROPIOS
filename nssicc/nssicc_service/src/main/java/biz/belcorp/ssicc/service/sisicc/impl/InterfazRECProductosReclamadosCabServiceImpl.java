package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;


/**
 * Service de la Interfaz REC-2 - Productos Reclamados Cabecera (Cabecera Reclamo).
 *
 * @author  <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 *
 */
@Service("sisicc.interfazRECProductosReclamadosCabService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRECProductosReclamadosCabServiceImpl
    extends BaseInterfazSalidaStoredProcedureAbstractService {
    
    /**
     * Obtiene la data para la Interfaz REC-2 - Productos Reclamados Cabecera (Cabecera Reclamo).
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
        List listProductosCabReclamados = null;

        try {
            listProductosCabReclamados = this.interfazSiCCDAO.getRECProductosCabList(params);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error en readData " + e.getMessage());
        }

        return listProductosCabReclamados;
    }
*/
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazRECProductosReclamadosCab(params);
	}

    public InterfazSiCCDAO getInterfazSiCCDAO() {
        return interfazSiCCDAO;
    }

    public void setInterfazSiCCDAO(InterfazSiCCDAO interfazSiCCDAO) {
        this.interfazSiCCDAO = interfazSiCCDAO;
    }
}
