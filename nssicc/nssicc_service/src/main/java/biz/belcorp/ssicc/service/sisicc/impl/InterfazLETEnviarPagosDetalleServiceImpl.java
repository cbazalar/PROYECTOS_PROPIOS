package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazLETDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/**
 * Service InterfazLETEnviarPagosDetalleServiceImpl.
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres L.</a>
 */
@Service("sisicc.interfazLETEnviarPagosDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLETEnviarPagosDetalleServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.InterfazLETDAO")
	private InterfazLETDAO interfazLETDAO;
	
	public Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
				if (log.isDebugEnabled()) {
				    log.debug("Dentro del metodo 'prepareQueryParams'");
				}
				// Obtenemos los parametros por defecto de la clase padre
				Map queryParams = super.prepareQueryParams(interfazParams);
				InterfazFormat interfazFormat = interfazFormatServiceFacade.getInterfazFormat(interfazParams.getInterfaz());
				Interfaz interfaz = interfazFormat.getInterfaz();
				String directorioDetalle =interfaz.getDirectorioEntradaSalida();
				
				
				queryParams.put("directorioDetalle", directorioDetalle);
				queryParams.put("extension", interfaz.getExtensionArchivoDescripcion());
				
				return queryParams;
			}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		this.interfazLETDAO.executeInterfazLETEnviarPagosDetalle(params);
		params.put("nombreArchivoDetalle", MapUtils.getString(params, "nombreArchivo"));
	}	


}