package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.ocr.gen.GenericoOCRComercialFacadeService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * Service de la recepcion del Consolidado OCS Detalle de la Interfaz OCR.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma G.</a>
 */
@Service("sisicc.interfazOCRRecepcionarConsolidadoOCSDetalleCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarConsolidadoOCSDetalleCorporativoServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="ocr.genericoOCRComercialFacadeService")
	GenericoOCRComercialFacadeService genericoOCRComercialFacadeService;
	
	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService")
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	@Resource(name="spusicc.procesoPEDDAO")
	private ProcesoPEDDAO procesoPEDDAO;

	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled()) log.debug("Entering 'beforeReadData' method");
		
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());		
		map.put("usuario",interfazParams.getUsuario());
		
		interfazSiCCDAO.executeInterfazOCRRecepcionarConsolidadoOCSDetalleCorporativo(map);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		Map map = interfazParams.getQueryParams();
		map.put("usuario", interfazParams.getUsuario());
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

		String codigoPais = (String) map.get("codigoPais");
		Map criteria2 = new HashMap();
		criteria2.put("codigoPais", codigoPais);
		// Indica Campanha Activa
		criteria2.put("estadoCampanha", Constants.NUMERO_CERO);
		// Indica Campanha activa que se procesa actualmente
		criteria2.put("indicadorActiva", Constants.ESTADO_ACTIVO); 
		
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria2);

		/* Invocando OCR Comercial SQL Server */
		String indicadorOCRComercial = (String) map.get("indicadorOCRComercial");
		if (Constants.SI.equals(indicadorOCRComercial)) {
			try {
				/* Obteniendo Fecha de Facturacion */

				map.put("fechaArchivoFacturacion", controlFacturacion.getFechaProceso());
				Date fechaOCR = DateUtil.convertStringToDate("dd/mm/yyyy",controlFacturacion.getFechaProceso());
				String fechaArchivoOCR = DateUtil.convertDateToString("yyyymmdd", fechaOCR);
				map.put("fechaArchivoOCR", fechaArchivoOCR);

				/* Invocando al OCR Comercial */
				ConexionOCRWrapper conexion = interfazOCRDAO.getDevuelveConexionOCR(codigoPais);
				List lista = genericoOCRComercialFacadeService.getListProcesoCarga(conexion, map);
				if (lista != null) {
					if (lista.size() > 0) {
						log.debug("Lista OCRComercial: " + lista.size());
						interfazOCRDAO.deleteOCRCUVErradosScaneo(map);
						interfazOCRDAO.insertOCRCUVErradosScaneo(lista, map,interfazParams.getUsuario());
					}
				}

			} catch (Exception e) {
				log.error("Error en afterProcessInterfaz " + e.getMessage());
			}
		}
		
	}
	  
	
}