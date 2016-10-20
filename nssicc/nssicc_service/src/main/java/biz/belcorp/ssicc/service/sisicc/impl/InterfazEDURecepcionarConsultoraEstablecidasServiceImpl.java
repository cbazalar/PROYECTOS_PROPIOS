package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazEDUDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para la recepcin de Consultoras Establecidas de la Interfaz EDU.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("sisicc.interfazEDURecepcionarConsultoraEstablecidasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEDURecepcionarConsultoraEstablecidasServiceImpl extends
        BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazEDUDAO")
    private InterfazEDUDAO interfazEDUDAO;

    protected void beforeProcessInterfaz(InterfazParams interfazParams)throws InterfazException {
    	if (log.isDebugEnabled())
		log.debug("Entering 'beforeProcessInterfaz' method");
    	interfazEDUDAO.deleteInterfazEDURecepcionarConsultoraEstablecidas(interfazParams.getQueryParams());
    }
    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        row.put("codigoPais", interfazParams.getQueryParams().get(
                        "codigoPais"));
        row.put("codigoEmpresa", interfazParams.getQueryParams().get(
        				"codigoEmpresa"));
        row.put("usuarioLogin",interfazParams.getUsuario().getLogin());
        row.put("estado",Constants.ESTADO_ACTIVO);
        log.debug("row=" + row);
      
        interfazEDUDAO.insertInterfazEDURecepcionarConsultoraEstablecidas(row);
    }

    protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'afterProcessInterfaz' method");
	
		 interfazEDUDAO.executeProcessRegistroAsistencia(interfazParams.getQueryParams());
		 //interfazEDUDAO.deleteInterfazEDURecepcionarConsultoraEstablecidas(interfazParams.getQueryParams());
    }

	
}
