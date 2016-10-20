package biz.belcorp.ssicc.service.spusicc.sto.framework.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * Implementacion de ProcesoSTOExcecutionServiceImpl que utiliza un Map con las
 * implementaciones especificas de los Documentos STO SiCC inyectados mediante
 * Spring. Nuevos documentos requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOValidateAutoExecutorServiceImpl extends ProcesoSTOValidateExecutorServiceImpl {
	
	
	private ProcesoBatchService procesoBatchService;
	
	
	/**
	 * @param procesoBatchService the procesoBatchService to set
	 */
	public void setProcesoBatchService(ProcesoBatchService procesoBatchService) {
		this.procesoBatchService = procesoBatchService;
	}

	protected static final String STO_IND_EJEC_VALID_AUTO = "1";

		
	/**
	 * @return the indicadorEjecutar
	 */
	protected boolean isProcesoActivo(DocumentoSTOParams documentoSTOParams) {
		String indValiAuto = documentoSTOParams.getTipoDocumentoDigitado().getIndValiAuto();
		if (STO_IND_EJEC_VALID_AUTO.equals(indValiAuto)) return true;
		return false;
	}
	
	/**
	 * @param documentoSTOParams
	 * @return
	 */
	protected void beforeExecute(DocumentoSTOParams documentoSTOParams) throws Exception{
		
		Map params = documentoSTOParams.getQueryParams();
		
		String indValiAuto = documentoSTOParams.getTipoDocumentoDigitado().getIndValiAuto();
		if (STO_IND_EJEC_VALID_AUTO.equals(indValiAuto)) 
			{
				String errorInterfaz = new String("");
				String codigoProcesoBatch = (String)params.get("codigoProcesoBatch");
				errorInterfaz = (String)params.get("errorInterfaz");
				log.debug("errorInterfaz=" + errorInterfaz);
				log.debug("descripcionEtapaProceso=" + params.get("descripcionEtapaProceso"));
				
				if (StringUtils.isBlank(errorInterfaz) || Constants.NO.equals(errorInterfaz))
					params.put("descripcionEtapaProceso", Constants.DESCRIPCION_STO_VALIDACION_PROCESO);
				
				log.debug("descripcionEtapaProceso=" + params.get("descripcionEtapaProceso"));
				Usuario usuario = (Usuario)params.get("usuario");
				if (StringUtils.isNotBlank(codigoProcesoBatch)) {
					procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
				}
				
			}	
		
		
	}

}
