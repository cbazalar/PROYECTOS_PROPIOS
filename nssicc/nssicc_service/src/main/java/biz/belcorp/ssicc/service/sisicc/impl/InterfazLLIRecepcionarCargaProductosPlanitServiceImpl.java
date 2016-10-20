package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/**
 *  @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */

@Service("sisicc.interfazLLIRecepcionarCargaProductosPlanitService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLLIRecepcionarCargaProductosPlanitServiceImpl extends BaseInterfazEntradaAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		try {
			super.beforeReadData(interfazParams);
			
			interfazSiCCDAO.deleteInterfazLLIRecepcionarCargaProductosPlanit();
			
		} catch (Exception e) { 
			throw new InterfazException("Error al borrar los registros de la tabla temporal: "+ e.getMessage()); 
		} 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount, Map row) throws InterfazException {
		String vlflag = null;

		try {
			Usuario usuario = interfazParams.getUsuario();
			
			String codCent = (String) row.get("codCent");
			String codSap = (String) row.get("codSap");
			String desCome = (String) row.get("desCome");
			
			log.debug("codCent: " + codCent);
			log.debug("codSap: " + codSap);
			log.debug("desCome: " + desCome);
	
			Map criterio = new HashMap();
			criterio.put("codCent", codCent);
			criterio.put("codSap", codSap);
			criterio.put("desCome", desCome);
			
			if (StringUtils.isNotBlank(codCent)) {
				interfazSiCCDAO.insertInterfazLLIRecepcionarCargaProductosPlanit(criterio);
			}
	
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());				
		}
	}
}