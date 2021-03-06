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
 * Service para la recepcion de Servicios Postventas Accion.
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 */
@Service("sisicc.interfazOCRRecepcionarServiciosPostventasDetalleAccionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarServiciosPostventasDetalleAccionServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;
	
	@Resource(name="spusicc.procesoSTOExecutionService")
	ProcesoSTOExecutionService procesoSTOExecutionService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		map.put("usuario",interfazParams.getUsuario());
		
		try {
			interfazOCRDAO.executeInterfazOCRRecepcionarServiciosPostventasDetalAccion(map);			
		} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla : "+ e.getMessage());
		}
	}
  
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		
		log.debug("===>afterProcessInterfaz<===");
		String codTipoDocu = Constants.STO_TIPO_DOCUMENTO_SPVC;
		super.beforeProcessInterfaz(interfazParams);
		
		Map map = interfazParams.getQueryParams();
		
		map.put("codigoDocumento", codTipoDocu);
		map.put("usuario.login",interfazParams.getUsuario().getLogin());
		map.put("usuario",interfazParams.getUsuario());
		log.debug("usuario.login ====>"+interfazParams.getUsuario().getLogin());
		
		String codigoProcesoBatch = (String)interfazParams.getQueryParams().get("codigoProcesoBatch");
		
		try {
			interfazOCRDAO.executeConsolidadoPostVenta(map);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar executeInterfazOCRProcesarConsolidadoPostventaDetalle: "	+ e.getMessage());
		}
		
		String codigoPais = (String) map.get("codigoPais");
		log.debug("Codigo Pais ===>"+codigoPais);
		
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