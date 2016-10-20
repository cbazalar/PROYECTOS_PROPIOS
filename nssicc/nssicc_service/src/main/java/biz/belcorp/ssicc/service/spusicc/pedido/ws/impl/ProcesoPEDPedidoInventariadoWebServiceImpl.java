package biz.belcorp.ssicc.service.spusicc.pedido.ws.impl;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.ProcesoPEDPedidoInventariadoWebService;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.NovedadWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoInventariadoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoPEDPedidoInventariadoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalarlarosa@gmail.com">Carlos Bazalar</a>
 */
public class ProcesoPEDPedidoInventariadoWebServiceImpl extends ServletEndpointSupport implements ProcesoPEDPedidoInventariadoWebService {

	Log log = LogFactory.getLog(ProcesoPEDPedidoInventariadoWebServiceImpl.class);
	private static String TIPO_ENTREGA_01 = "01";
	private static String TIPO_ENTREGA_02 = "02";
	private static String TIPO_ENTREGA_03 = "03";
	
	private static String PEDIDO_INVENTARIADO_01 = "01";
	private static String PEDIDO_INVENTARIADO_02 = "02";
	private static String PEDIDO_INVENTARIADO_00 = "00";
	
	
	ProcesoPEDService procesoPEDService;
	
	
    /* (non-Javadoc)
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	procesoPEDService = (ProcesoPEDService)getWebApplicationContext().getBean("spusicc.procesoPEDService");
    }
	
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedido.ws.ProcesoPEDPedidoInventariadoWebService#ejecutarProcesoPEDPedidoInventariado(biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.NovedadWebServiceParameter)
	 */
	public ProcesoPEDPedidoInventariadoWebServiceResponse ejecutarProcesoPEDPedidoInventariado(
			NovedadWebServiceParameter beanNovedad) throws RemoteException {
		String mensajeError = "";
		boolean estado = false;
		ProcesoPEDPedidoInventariadoWebServiceResponse objetoRespuesta = new ProcesoPEDPedidoInventariadoWebServiceResponse();

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoPEDPedidoInventariado");
		}

		try {
			//1) Validamos los parametros recibidos del WebService
			/*
			String codigoConsultora = beanNovedad.getCodigoConsultora();
			if (StringUtils.isBlank(codigoConsultora)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.codigoConsultora", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String codigoPlataforma = beanNovedad.getCodigoPlataforma();
			if (StringUtils.isBlank(codigoPlataforma)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.codigoPlataforma", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String fecha = beanNovedad.getFecha();
			if (StringUtils.isBlank(fecha)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.fecha", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			try {
				Date dFecha = DateUtil.convertStringToDate("YYYY-MM-dd HH:mm:ss", fecha);
			}
			catch (Exception ex) {
				try {
					Date dFecha = DateUtil.convertStringToDate("YYYY-MM-dd", fecha);
				}
				catch (Exception ex1) {
					mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.errorFormatoFecha", null, 
															getLocaleIdioma(null));
					throw new Exception(mensajeError);
				}
			}
			
			Long identificadorEntrega = beanNovedad.getIdentificadorEntrega();
			if (identificadorEntrega == null) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.identificadorEntrega", null, 
						getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String latitud = beanNovedad.getLatitud();                               
			if (StringUtils.isBlank(latitud)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.latitud", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String longitud = beanNovedad.getLongitud();                               
			if (StringUtils.isBlank(longitud)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.longitud", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String novedad = beanNovedad.getNovedad();                               
			if (StringUtils.isBlank(novedad)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.novedad", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String numeroPedido = beanNovedad.getNumeroPedido();                               
			if (StringUtils.isBlank(numeroPedido)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.numeroPedido", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			
			String observacion = beanNovedad.getObservacion();                               
			if (StringUtils.isBlank(observacion)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.observacion", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			
			String paisISO = beanNovedad.getPaisISO();                               
			if (StringUtils.isBlank(paisISO)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.paisISO", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String tipoEntrega = beanNovedad.getTipoEntrega();                               
			if (StringUtils.isBlank(tipoEntrega)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.tipoEntrega", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			if (StringUtils.equals(tipoEntrega, TIPO_ENTREGA_01) || 
				StringUtils.equals(tipoEntrega, TIPO_ENTREGA_02) || 
				StringUtils.equals(tipoEntrega, TIPO_ENTREGA_03)) {
				
			}
			else {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.tipoTipoEntrega", null, 
						getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			String pedidoInventariado = beanNovedad.getPedidoInventariado();                               
			if (StringUtils.isBlank(pedidoInventariado)) {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.pedidoInventariado", null, 
														getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			
			if (StringUtils.equals(pedidoInventariado, PEDIDO_INVENTARIADO_01) || 
				StringUtils.equals(pedidoInventariado, PEDIDO_INVENTARIADO_02) || 
				StringUtils.equals(pedidoInventariado, PEDIDO_INVENTARIADO_00)) {
				
			}
			else {
				mensajeError = getWebApplicationContext().getMessage("procesoPEDPedidoInventariadoWebServiceResponse.msg.tipoPedidoInventariado", null, 
						getLocaleIdioma(null));
				throw new Exception(mensajeError);
			}
			*/

			Map criteria = new HashMap();
			criteria = BeanUtils.describe(beanNovedad);
			if ( beanNovedad.getIdentificadorEntrega() != null)
				criteria.put("identificadorEntrega", beanNovedad.getIdentificadorEntrega().toString().trim());
			
			procesoPEDService.executeProcesoPEDPedidoInventariado(criteria);
			
			//6)Respuesta al WebService
			objetoRespuesta.setMensaje("");
			estado = true;
			objetoRespuesta.setEjecucionExitosa(estado);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setEjecucionExitosa(estado);
		} finally {
			log.debug("Estado del servicio: " + estado);
			if (estado) {
				log.info("Se ejecuto el servicio con exito.");
			} else {
				log.error("Excepcion en el servicio.");
			}
		}
		return objetoRespuesta;
	}
	
	
	/**
	 * @param codigoIsoIdioma
	 * @return
	 */
	private Locale getLocaleIdioma(String codigoIsoIdioma) {
		if (StringUtils.isNotEmpty(codigoIsoIdioma)) {
			if (Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma.toLowerCase()))
				return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
			else {
				log.debug("codigoIsoIdioma " + codigoIsoIdioma);
				return new Locale(codigoIsoIdioma.toLowerCase());
			}
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}

}
