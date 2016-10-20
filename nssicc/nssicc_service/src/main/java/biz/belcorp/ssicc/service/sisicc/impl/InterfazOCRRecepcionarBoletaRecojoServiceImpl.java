package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * 
 * @author <a href="itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */
@Service("sisicc.interfazOCRRecepcionarBoletaRecojoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarBoletaRecojoServiceImpl extends BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;	
	
	@Resource(name="spusicc.procesoSTOExecutionService")
	ProcesoSTOExecutionService procesoSTOExecutionService;
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService")
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;

	/**
	 * @param interfazOCRDAO the interfazOCRDAO to set
	 */
	public void setInterfazOCRDAO(InterfazOCRDAO interfazOCRDAO) {
		this.interfazOCRDAO = interfazOCRDAO;
	}

	/**
	 * @param procesoSTOExecutionService the procesoSTOExecutionService to set
	 */
	public void setProcesoSTOExecutionService(
			ProcesoSTOExecutionService procesoSTOExecutionService) {
		this.procesoSTOExecutionService = procesoSTOExecutionService;
	}

	/**
	 * @param mantenimientoOCRPedidoControlFacturacionService the mantenimientoOCRPedidoControlFacturacionService to set
	 */
	public void setMantenimientoOCRPedidoControlFacturacionService(
			MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService) {
		this.mantenimientoOCRPedidoControlFacturacionService = mantenimientoOCRPedidoControlFacturacionService;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		
		try { 
				interfazOCRDAO.executeInterfazOCRRecepcionarBoletaRecojo(map);
		} catch (Exception e) {
			
				throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: "+ e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
    	super.afterProcessInterfaz(interfazParams);
    	/*Invoca la ejscucion de Validaciones Automaticas*/		
    	Map map = interfazParams.getQueryParams();
    	map.put("usuario",interfazParams.getUsuario());
		map.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_OT);
		
		interfazOCRDAO.executeInterfazOCRProcesarConsolidadoOrdenTransporte(map);
		
		String codigoPais = (String) map.get("codigoPais");
		String codTipoDocu = Constants.STO_TIPO_DOCUMENTO_OT;
		
		Map criteria2 = new HashMap();
		criteria2.put("codigoPais", interfazParams.getQueryParams().get("codigoPais"));
		criteria2.put("estadoCampanha", Constants.NUMERO_CERO);
		criteria2.put("indicadorActiva", Constants.ESTADO_ACTIVO); 
		
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria2);
		
		map.put("usuario", interfazParams.getUsuario());
		map.put("codigoSistema", interfazParams.getInterfaz().getCodigoSistema());
		map.put("codigoProcesoBatch", interfazParams.getQueryParams().get("codigoProcesoBatch"));	
		
		try {
			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,codTipoDocu,Constants.STO_ACCI_VALI_AUTO);
			procesoSTOExecutionService.execute(accionTipoDocumento, map, null);
			interfazParams.setQueryParams(map);
			
		} catch (Exception e) {
			throw new InterfazException("Error al procesar executeValidacionAutomaticaDocumentoSTO: "+ e.getMessage());
		}
	}
}