/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.scsicc.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.bean.PedidosDigitadosBean;
import biz.belcorp.ssicc.dao.scsicc.bean.RetencionPedidosBean;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.ws.ConsultaPedidosMYEWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultaPedidosMYEWebServiceResultado;
import biz.belcorp.ssicc.service.scsicc.ws.beans.PedidosDigitadosMYEWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.RetencionPedidosMYEWebService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaPedidosMYEWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sbuchelli@belcorp.biz"> sbuchelli </a>
 */
public class ConsultaPedidosMYEWebServiceImpl extends ServletEndpointSupport implements
	ConsultaPedidosMYEWebService {

    Log log = LogFactory.getLog(ConsultaPedidosMYEWebServiceImpl.class);
    
    ReporteService reporteService;    

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	reporteService = (ReporteService)getWebApplicationContext().getBean("scsicc.reporteService");
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaPedidosMYEWebService#getPedidosDigitados(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaPedidosMYEWebServiceResultado getPedidosDigitados(
			String codigoPais, String codigoPeriodo, String codigoRegion,
			String codigoZona, String codigoIsoIdioma) throws RemoteException {
		ConsultaPedidosMYEWebServiceResultado consultaPedidosMYEWebServiceResultado = new ConsultaPedidosMYEWebServiceResultado();		
		PedidosDigitadosMYEWebService [] arrPedidos = null;
		try{	
			
    		if(StringUtils.isEmpty(codigoPais)){
    			String mensajeError = getWebApplicationContext().
					getMessage("procesoRETWebService.msg.validarPais",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}
    		
			if (StringUtils.isBlank(codigoPeriodo)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}

			if (StringUtils.isBlank(codigoZona)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoZona",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(codigoRegion)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoEDUWebService.msg.validarRegion",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}
			
															
			Map criteria = new HashMap();
			criteria.put("codigoPais",codigoPais);
			criteria.put("codigoPeriodo",codigoPeriodo);
			criteria.put("codigoRegion",codigoRegion);
			criteria.put("codigoZona",codigoZona);						

			List list = reporteService.getPedidosDigitados(criteria);
			log.debug("list retencion  " +list.size());
			arrPedidos = completarListPedidosDigitados(list);
				
			consultaPedidosMYEWebServiceResultado.setListRetencionPedidosMYEWebService(null);
			consultaPedidosMYEWebServiceResultado.setListPedidosDigitadosMYEWebService(arrPedidos);

		}catch(Exception e){
			e.printStackTrace();
			consultaPedidosMYEWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
			consultaPedidosMYEWebServiceResultado.setMensaje(e.getMessage());
			consultaPedidosMYEWebServiceResultado.setListPedidosDigitadosMYEWebService(null);
			consultaPedidosMYEWebServiceResultado.setListRetencionPedidosMYEWebService(null);
			return consultaPedidosMYEWebServiceResultado;
		}
		consultaPedidosMYEWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
		consultaPedidosMYEWebServiceResultado.setMensaje(""+arrPedidos.length);
		return consultaPedidosMYEWebServiceResultado;
	}

	/**
	 * Devuelve lista d epedidos digitados
	 * @param list
	 * @return
	 */
	private PedidosDigitadosMYEWebService[] completarListPedidosDigitados (
			List list) throws Exception{
		PedidosDigitadosMYEWebService [] listResult;
		PedidosDigitadosBean bean;
		listResult= new PedidosDigitadosMYEWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 bean=(PedidosDigitadosBean)it.next();
			 PedidosDigitadosMYEWebService obj=new PedidosDigitadosMYEWebService();
			 BeanUtils.copyProperties(obj, bean);
			 listResult[i++]=obj;
		 }
		return listResult;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaPedidosMYEWebService#getRetencionPedidos(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaPedidosMYEWebServiceResultado getRetencionPedidos(
			String codigoPais, String codigoPeriodo, String codigoRegion,
			String codigoZona, String numeroRetencion, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaPedidosMYEWebServiceResultado consultaPedidosMYEWebServiceResultado = new ConsultaPedidosMYEWebServiceResultado();		
		RetencionPedidosMYEWebService [] arrRetencion = null;
		try{	
			
    		if(StringUtils.isEmpty(codigoPais)){
    			String mensajeError = getWebApplicationContext().
					getMessage("procesoRETWebService.msg.validarPais",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}
    		
			if (StringUtils.isBlank(codigoPeriodo)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}

			if (StringUtils.isBlank(codigoZona)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoZona",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(codigoRegion)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoEDUWebService.msg.validarRegion",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(numeroRetencion)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.numeroRetencion",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}else{
			  //se valida que este entre 2 y 5 
				int numRetencion = new Integer(numeroRetencion);
				if(numRetencion < 2 || 
						numRetencion >5){
					String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.rango.numeroRetencion",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);			
				}
			}
				
										
			Map criteria = new HashMap();
			criteria.put("codigoPais",codigoPais);
			criteria.put("codigoPeriodo",codigoPeriodo);
			criteria.put("codigoRegion",codigoRegion);
			criteria.put("codigoZona",codigoZona);						
			criteria.put("numeroRetencion",numeroRetencion );
			
			List list = reporteService.getRetencionPedidos(criteria);
			log.debug("list retencion  " +list.size());
			arrRetencion = completarListRetencion(list);
				
			consultaPedidosMYEWebServiceResultado.setListRetencionPedidosMYEWebService(arrRetencion);
			consultaPedidosMYEWebServiceResultado.setListPedidosDigitadosMYEWebService(null);

		}catch(Exception e){
			e.printStackTrace();
			consultaPedidosMYEWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
			consultaPedidosMYEWebServiceResultado.setMensaje(e.getMessage());
			consultaPedidosMYEWebServiceResultado.setListPedidosDigitadosMYEWebService(null);
			return consultaPedidosMYEWebServiceResultado;
		}
		consultaPedidosMYEWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
		consultaPedidosMYEWebServiceResultado.setMensaje(""+arrRetencion.length);
		return consultaPedidosMYEWebServiceResultado;
	}

	/**
	 * Completa el arreglo de faltantes anunciados po consultora 
	 * @param list
	 * @return
	 */
	private RetencionPedidosMYEWebService[] completarListRetencion (
			List list) throws Exception {
		RetencionPedidosMYEWebService [] listResult;
		RetencionPedidosBean bean;
		listResult= new RetencionPedidosMYEWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 bean=(RetencionPedidosBean)it.next();
			 RetencionPedidosMYEWebService obj=new RetencionPedidosMYEWebService();
			 BeanUtils.copyProperties(obj, bean);
			 listResult[i++]=obj;
		 }
		return listResult;
	}

	
	/**
	 * Retorna la descripcion por idioma
	 * @param codigoIsoIdioma
	 * @return
	 */
	protected Locale getLocaleIdioma(String codigoIsoIdioma) {
	    //mantenimientoEDUGenericoService.getIdiomaISO(codigoPais);
		if(StringUtils.isNotEmpty(codigoIsoIdioma)){
		    if(Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma.toLowerCase()))
			  return  new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
		    else{
		    	log.debug("codigoIsoIdioma " + codigoIsoIdioma);		
		      return new Locale(codigoIsoIdioma.toLowerCase());
		    }
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);		
	}

}
