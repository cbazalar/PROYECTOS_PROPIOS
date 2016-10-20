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
 * Service para la recepcin de Consultoras a Demanda de la Interfaz EDU.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("sisicc.interfazEDURecepcionarConsultoraDemandaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEDURecepcionarConsultoraDemandaServiceImpl extends
        BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazEDUDAO")
    private InterfazEDUDAO interfazEDUDAO;

    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        row.put("codigoPais", interfazParams.getQueryParams().get(
                        "codigoPais"));
        row.put("codigoEmpresa", interfazParams.getQueryParams().get(
        				"codigoEmpresa"));
        row.put("codigoCurso", interfazParams.getQueryParams().get(
						"codigoCurso"));
        row.put("campanha",interfazParams.getQueryParams().get(
						"campanha"));
        row.put("estado",Constants.ESTADO_ACTIVO);
        
        log.debug("row=" + row);
        interfazEDUDAO.insertInterfazEDURecepcionarConsultoraDemanda(row);
    }

  
}
