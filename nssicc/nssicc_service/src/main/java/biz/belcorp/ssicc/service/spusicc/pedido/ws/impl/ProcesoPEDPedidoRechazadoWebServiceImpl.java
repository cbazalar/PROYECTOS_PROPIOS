package biz.belcorp.ssicc.service.spusicc.pedido.ws.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.ProcesoPEDPedidoRechazadoWebService;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.PedidoAlternativosWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.PedidoDetalleWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.PedidoWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoDetalleRechazadoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoErroresRechazadoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoMensajesRechazadoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoRechazadoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoPEDPedidoRechazadoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public class ProcesoPEDPedidoRechazadoWebServiceImpl extends ServletEndpointSupport implements ProcesoPEDPedidoRechazadoWebService {

	Log log = LogFactory.getLog(ProcesoPEDPedidoRechazadoWebServiceImpl.class);
	
	ProcesoPEDService procesoPEDService;
	
    /* (non-Javadoc)
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	procesoPEDService = (ProcesoPEDService)getWebApplicationContext().getBean("spusicc.procesoPEDService");
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedido.ws.ProcesoPEDPedidoRechazadoWebService#ejecutarProcesoPEDPedidoRechazado(biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.PedidoWebServiceParameter)
	 */
	@Override
	public ProcesoPEDPedidoRechazadoWebServiceResponse ejecutarProcesoPEDPedidoRechazado(PedidoWebServiceParameter beanPedido) throws RemoteException {
		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoPEDPedidoRechazado");
		}
		
		ProcesoPEDPedidoRechazadoWebServiceResponse objetoRespuesta = new ProcesoPEDPedidoRechazadoWebServiceResponse();

		try {
			String correlativo = procesoPEDService.getNextCorrelativoPedidoRechazadas();
			
			Map criteria = new HashMap();
			
			PedidoDetalleWebServiceParameter pedidoDWSP = new PedidoDetalleWebServiceParameter();
			PedidoDetalleWebServiceParameter[] listDetalle;
			Map criteriaDetalle = new HashMap();
			
			PedidoAlternativosWebServiceParameter pedidoAWSP = new PedidoAlternativosWebServiceParameter();
			PedidoAlternativosWebServiceParameter[] listAlternativos;
			Map criteriaAlternativos = new HashMap();
			
			criteria = BeanUtils.describe(beanPedido);
			criteria.put("correlativo", correlativo);
			procesoPEDService.insertaCabeceraEntradaPedidoRechazado(criteria);
			
			listDetalle = beanPedido.getDetalle();
			listAlternativos = beanPedido.getAlternativos();
			
			if(listDetalle != null && listDetalle.length > 0){
				for (int i = 0; i < listDetalle.length; i++) {
					pedidoDWSP = listDetalle[i];
					
					criteriaDetalle = BeanUtils.describe(pedidoDWSP);
					criteriaDetalle.put("correlativo", correlativo);
					
					procesoPEDService.insertaDetalleEntradaPedidoRechazado(criteriaDetalle);
				}
			}
			
			if(listAlternativos != null && listAlternativos.length > 0){
				for (int i = 0; i < listAlternativos.length; i++) {
					pedidoAWSP = listAlternativos[i];
										
					criteriaAlternativos = BeanUtils.describe(pedidoAWSP);
					criteriaAlternativos.put("correlativo", correlativo);
					
					procesoPEDService.insertaAlternativosEntradaPedidoRechazado(criteriaAlternativos);
				}
			}
			
			//Llamada al procedimiento
			procesoPEDService.executeProcesoPEDPedidoRechazado(criteria);
			
			List listCabeceraSalida = procesoPEDService.getPedidosCabeceraRechazados(criteria);
			if(listCabeceraSalida != null && listCabeceraSalida.size() > 0){
				Map mapResponse = (Map) listCabeceraSalida.get(0);
				BeanUtils.copyProperties(objetoRespuesta, mapResponse);
			}
			
			List listDetalleSalida = procesoPEDService.getPedidosDetalleRechazados(criteria);
			if(listDetalleSalida != null && listDetalleSalida.size() > 0){
				ProcesoPEDPedidoDetalleRechazadoWebServiceResponse objetoDetalleSalida[] = new ProcesoPEDPedidoDetalleRechazadoWebServiceResponse[listDetalleSalida.size()];
				
				for (int i = 0; i < listDetalleSalida.size(); i++) {
					Map mapResponseDetalle = new HashMap();
					mapResponseDetalle = (Map) listDetalleSalida.get(i);
					ProcesoPEDPedidoDetalleRechazadoWebServiceResponse detalle = new ProcesoPEDPedidoDetalleRechazadoWebServiceResponse();
					BeanUtils.copyProperties(detalle, mapResponseDetalle);
					
					objetoDetalleSalida[i] = detalle;
				}
				objetoRespuesta.setDetalle(objetoDetalleSalida);
			}
			
			List listAlternativosSalida = procesoPEDService.getPedidosAlternativosRechazados(criteria);
			if(listAlternativosSalida != null && listAlternativosSalida.size() > 0){
				ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse objetoAlternativosSalida[] = new ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse[listAlternativosSalida.size()];
				
				for (int i = 0; i < listAlternativosSalida.size(); i++) {
					Map mapResponseAlternativo = new HashMap();
					mapResponseAlternativo = (Map) listAlternativosSalida.get(i);
					ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse alternativos = new ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse();
					BeanUtils.copyProperties(alternativos, mapResponseAlternativo);
					
					objetoAlternativosSalida[i] = alternativos;
				}
				objetoRespuesta.setAlternativos(objetoAlternativosSalida);
			}
			
			List listErroresSalida = procesoPEDService.getPedidosErroresRechazados(criteria);
			if(listErroresSalida != null && listErroresSalida.size() > 0){
				ProcesoPEDPedidoErroresRechazadoWebServiceResponse objetoErroresSalida[] = new ProcesoPEDPedidoErroresRechazadoWebServiceResponse[listErroresSalida.size()];
				
				for (int i = 0; i < listErroresSalida.size(); i++) {
					Map mapResponseError = new HashMap();
					mapResponseError = (Map) listErroresSalida.get(i);
					ProcesoPEDPedidoErroresRechazadoWebServiceResponse error = new ProcesoPEDPedidoErroresRechazadoWebServiceResponse();
					BeanUtils.copyProperties(error, mapResponseError);
					
					objetoErroresSalida[i] = error;
				}
				objetoRespuesta.setErrores(objetoErroresSalida);
			}
			
			List listMensajesSalida = procesoPEDService.getPedidosMensajesRechazados(criteria);
			if(listMensajesSalida != null && listMensajesSalida.size() > 0){
				ProcesoPEDPedidoMensajesRechazadoWebServiceResponse objetoMensajesSalida[] = new ProcesoPEDPedidoMensajesRechazadoWebServiceResponse[listMensajesSalida.size()];
				
				for (int i = 0; i < listMensajesSalida.size(); i++) {
					Map mapResponseMensaje = new HashMap();
					mapResponseMensaje = (Map) listMensajesSalida.get(i);
					ProcesoPEDPedidoMensajesRechazadoWebServiceResponse mensaje = new ProcesoPEDPedidoMensajesRechazadoWebServiceResponse();
					BeanUtils.copyProperties(mensaje, mapResponseMensaje);
					
					objetoMensajesSalida[i] = mensaje;
				}
				objetoRespuesta.setMensajes(objetoMensajesSalida);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensajeError(e.getMessage());
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