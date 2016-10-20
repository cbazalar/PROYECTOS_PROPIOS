package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * Service de la recepcion del Consolidado OCS Detalle de la Interfaz OCR.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazOCRRecepcionarConsolidadoOCSDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarConsolidadoOCSDetalleServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService")
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	

	@Resource(name="spusicc.procesoPEDDAO")
	private ProcesoPEDDAO procesoPEDDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService#beforeExecuteInterfaz(java.util.Map)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void beforeExecuteInterfaz(Map params) throws Exception {
		super.beforeExecuteInterfaz(params);
		/* Inicializando valores */
		String exito = "-1";
		params.put("exito", exito);
		/* Invocando al metodo del proceso de cargo y abono */
		mantenimientoOCRPedidoControlFacturacionService.executePedido("executeCalculoDeuda", params);
		exito = "1";
		params.put("exito", exito);
	}	


	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());		
		
		interfazSiCCDAO.executeInterfazOCRRecepcionarConsolidadoOCSDetalle(map);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		Map map = interfazParams.getQueryParams();
		map.put("usuario",interfazParams.getUsuario());
		map.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		String codigoProcesoBatch = (String)map.get("codigoProcesoBatch");
		
		log.debug("Entering 'afterProcessInterfaz' method"); 
		
		/* Consolida informacion y ejecuta procesos de Programas Nuevas / SSE */ 
		try {
			procesoPEDDAO.executeConsolidadoPedidos(map);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar procesaSolicitudConsolCabecera: "+ e.getMessage());
		}
		
		interfazSiCCDAO.executeActualizaNumeroLoteMica(map);
		
	}

}