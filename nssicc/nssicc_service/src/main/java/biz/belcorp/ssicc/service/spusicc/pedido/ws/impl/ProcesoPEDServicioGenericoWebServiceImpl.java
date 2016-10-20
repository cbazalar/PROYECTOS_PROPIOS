package biz.belcorp.ssicc.service.spusicc.pedido.ws.impl;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
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
import biz.belcorp.ssicc.service.spusicc.pedido.ws.ProcesoPEDServicioGenericoWebService;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.NovedadWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDPedidoInventariadoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ProcesoPEDServicioGenericoWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ServicioGenericoWebServiceParameter;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoPEDServicioGenericoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 */
public class ProcesoPEDServicioGenericoWebServiceImpl extends ServletEndpointSupport implements ProcesoPEDServicioGenericoWebService {

	Log log = LogFactory.getLog(ProcesoPEDServicioGenericoWebServiceImpl.class);
	
	
	ProcesoPEDService procesoPEDService;
	
	
    /* (non-Javadoc)
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	procesoPEDService = (ProcesoPEDService)getWebApplicationContext().getBean("spusicc.procesoPEDService");
    }
	
    
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedido.ws.ProcesoPEDServicioGenericoWebService#ejecutarProcesoPEDServicioGenerico(biz.belcorp.ssicc.service.spusicc.pedido.ws.beans.ServicioGenericoWebServiceParameter)
	 */
	public ProcesoPEDServicioGenericoWebServiceResponse ejecutarProcesoPEDServicioGenerico(
			ServicioGenericoWebServiceParameter beanServicio) throws RemoteException {
		boolean estado = false;
		ProcesoPEDServicioGenericoWebServiceResponse objetoRespuesta = new ProcesoPEDServicioGenericoWebServiceResponse();

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoPEDServicioGenerico");
		}

		try {

			Map criteria = new HashMap();
			criteria = BeanUtils.describe(beanServicio);
			procesoPEDService.executeProcesoPEDServicioGenerico(criteria);
			
			objetoRespuesta.setCodigoRetorno(MapUtils.getString(criteria, "codigoRetorno"));
			objetoRespuesta.setValorRetorno(MapUtils.getString(criteria, "valorRetorno"));
			objetoRespuesta.setValorRetorno2(MapUtils.getString(criteria, "valorRetorno2"));
			objetoRespuesta.setValorRetorno3(MapUtils.getString(criteria, "valorRetorno3"));
			objetoRespuesta.setValorRetorno4(MapUtils.getString(criteria, "valorRetorno4"));
			estado = true;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			
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
