package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazACCDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service para el envio de Novedades de la Interfaz ACC.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("sisicc.InterfazACCEnviarConcursoPremioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazACCEnviarConcursoPremioServiceImpl extends
		BaseInterfazSalidaAbstractService {

	@Resource(name="sisicc.interfazACCDAO")
	private InterfazACCDAO interfazACCDAO;

	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List listConcurso = null;
		try {
			String codigoPais = (String)queryParams.get("codigoPais");
			String codigoCompania = this.interfazACCDAO.getIVRCompania(codigoPais);
			
			queryParams.put("codigoCompania", codigoCompania);
			listConcurso = this.interfazACCDAO.getInterfazACCConcursoPremio(queryParams);

		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return listConcurso;
	}

	

}

