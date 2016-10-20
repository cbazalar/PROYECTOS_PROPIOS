package biz.belcorp.ssicc.service.aco.ws.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoOCRControlFacturacionCrearWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoOCRControlFacturacionCrearWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoOCRControlFacturacionCrearWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoOCRControlFacturacionCrearWebService {

	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
		
	private static final String CODIGO_SISTEMA = Constants.OCR_CODIGO_SISTEMA;
	private static final String CODIGO_PROCESO_BATCH = "05";
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoOCRControlFacturacionCrearWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais,
			String fechaFacturacion, String montoMinimoDeuda,
			String unidadesMaximo, String codigoUsuario) throws RemoteException {
		
		ACOWebServiceResponse aCOWebServiceResponse = new ACOWebServiceResponse();

		String mensajeError = "";
		String mensajeEXito = "";
		
		String codigoSistema = CODIGO_SISTEMA;
		String codigoProcesoBatch = CODIGO_PROCESO_BATCH;
		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
		    
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		    
		    PedidoControlFacturacion controlFacturacion = new PedidoControlFacturacion();
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoSistema", codigoSistema);
			params.put("codigoProcesoBatch", codigoProcesoBatch);
			params.put("codigoUsuario", codigoUsuario);
			params.put("usuario", usuario);

		    String periodoSiguiente = mantenimientoOCRPedidoControlFacturacionService.getPedidosSiguienteCampanha(params);
		    String numeroLote = mantenimientoOCRPedidoControlFacturacionService.getNumeroLote(params);

			if(StringUtils.isEmpty(fechaFacturacion) || StringUtils.isBlank(fechaFacturacion)){
				fechaFacturacion = DateUtil.convertDateToString(new Date());
			}
			
			if(StringUtils.isEmpty(montoMinimoDeuda) || StringUtils.isBlank(montoMinimoDeuda)){
				montoMinimoDeuda = Constants.NUMERO_CERO;
			}
			
			if(StringUtils.isEmpty(unidadesMaximo) || StringUtils.isBlank(unidadesMaximo)){
				unidadesMaximo = Constants.NUMERO_CERO;
			}
						
			controlFacturacion.setCodigoPais(codigoPais);
			controlFacturacion.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			controlFacturacion.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			controlFacturacion.setCodigoPeriodo(periodoSiguiente);
			controlFacturacion.setNumeroLote(numeroLote);
			controlFacturacion.setFechaProceso(fechaFacturacion);
			controlFacturacion.setMontoMinimoDeuda(new BigDecimal(montoMinimoDeuda));
			controlFacturacion.setUnidadesMaximo(Integer.parseInt(unidadesMaximo));
			controlFacturacion.setEstadoCampanha(Constants.NUMERO_CERO);
			controlFacturacion.setMontoMinimoFact(new BigDecimal(0));
			controlFacturacion.setMontoMinimoAcept(new BigDecimal(0));
			controlFacturacion.setMontoMaximo(new BigDecimal(0));
			
			this.mantenimientoOCRPedidoControlFacturacionService.insertControlFacturacion(controlFacturacion, usuario);
			
			mensajeEXito =  getWebApplicationContext().getMessage("campana.added", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
		        aCOWebServiceResponse.setEjecucionExitosa(true);
	                aCOWebServiceResponse.setMensaje(mensajeEXito);

		} catch (Exception e) {
			mensajeError = e.getMessage();
			aCOWebServiceResponse.setEjecucionExitosa(false);
			aCOWebServiceResponse.setMensaje(mensajeError);
		}

		return aCOWebServiceResponse;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {
		return params;
	}

}
