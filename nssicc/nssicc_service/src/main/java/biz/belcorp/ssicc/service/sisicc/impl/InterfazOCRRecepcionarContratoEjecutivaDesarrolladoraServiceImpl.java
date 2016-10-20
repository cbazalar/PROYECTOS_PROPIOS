package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * Service de la recepcion Contrato ejecutiva OCR.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("sisicc.interfazOCRRecepcionarContratoEjecutivaDesarrolladoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarContratoEjecutivaDesarrolladoraServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazOCRDAO")
	protected InterfazOCRDAO interfazOCRDAO;
	
	@Resource(name="spusicc.procesoSTOExecutionService")
	ProcesoSTOExecutionService procesoSTOExecutionService;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		interfazOCRDAO.executeInterfazOCRContratoEjecutiva(map);
			
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		
		String codTipoDocu = Constants.STO_TIPO_DOCUMENTO_CED;
		
		Map map = interfazParams.getQueryParams();
		map.put("usuario.login",interfazParams.getUsuario().getLogin());
		map.put("usuario",interfazParams.getUsuario());
		map.put("codigoDocumento", codTipoDocu);
		
		String codigoProcesoBatch = (String)interfazParams.getQueryParams().get("codigoProcesoBatch");
		String codigoPais= (String)map.get("codigoPais");
		try {
			interfazOCRDAO.executeInterfazOCRProcesarConsolidadoContratoEjecutiva(map);		
		} catch (Exception e) {
			throw new InterfazException("Error al procesar executeInterfazOCRProcesarConsolidadoEjecutiva: " + e.getMessage());
		}	
		
		map.put("codigoSistema", interfazParams.getInterfaz().getCodigoSistema());
		map.put("codigoProcesoBatch", codigoProcesoBatch);
		
		try {
			String	indValidacionSTO = (String)map.get("indValidacionSTO");
			log.debug("====>Ingreso a execute Validacion Automatica<==== ::: "+indValidacionSTO);
		    if ( StringUtils.isEmpty(indValidacionSTO) || Constants.SI.equals(indValidacionSTO)){
		    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,codTipoDocu,Constants.STO_ACCI_VALI_AUTO);
		    	procesoSTOExecutionService.execute(accionTipoDocumento, map, null);
		    	interfazParams.setQueryParams(map);
		    }
		} catch (Exception e) {
			throw new InterfazException("Error al procesar executeValidacionAutomaticaDocumentoSTO: "+ e.getMessage());
		}
	}

	

			
}