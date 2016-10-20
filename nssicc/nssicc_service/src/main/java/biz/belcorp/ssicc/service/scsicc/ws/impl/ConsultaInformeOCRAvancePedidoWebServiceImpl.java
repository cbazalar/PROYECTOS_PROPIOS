/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.scsicc.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
import biz.belcorp.ssicc.dao.scsicc.bean.ConsultorasActivasSinPedidoBean;
import biz.belcorp.ssicc.dao.scsicc.bean.InformeOCRPedidoBean;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.ws.ConsultaInformeOCRAvancePedidoWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultaOCRWebServiceResultado;
import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultorasActivasSinPedidoWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.InformeOCRPedidoWebService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaInformeOCRAvancePedidoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sbuchelli@belcorp.biz"> sbuchelli </a>
 */
public class ConsultaInformeOCRAvancePedidoWebServiceImpl extends ServletEndpointSupport implements
		ConsultaInformeOCRAvancePedidoWebService	 {

    private static final String PAIS_DEFAULT = "PE";

	Log log = LogFactory.getLog(ConsultaInformeOCRAvancePedidoWebServiceImpl.class);
    
    ReporteService reporteService;
    MantenimientoSTOBloqueoControlService stoService ;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	reporteService = (ReporteService)getWebApplicationContext().getBean("scsicc.reporteService");
    	stoService = (MantenimientoSTOBloqueoControlService)getWebApplicationContext().getBean("spusicc.mantenimientoSTOBloqueoControlService");
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getConsultaInformeAvancePedido(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaOCRWebServiceResultado getConsultaInformeAvancePedido(
			String codigoPeriodo, String codigoZona, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();
		InformeOCRPedidoWebService [] informePedido= null;		
	try{	
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
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",codigoPeriodo);
		criteria.put("codigoZona", codigoZona);
		List listInforme = reporteService.getConsultaInformeAvancePedido(criteria);
		log.debug("list informe ocr " +listInforme.size());
		
		informePedido = completarListInformePedido(listInforme);
				
		consultaOCRWebServiceResultado.setListInformeOCRWebService(informePedido);
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
	}catch(Exception e){
		e.printStackTrace();
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
		consultaOCRWebServiceResultado.setMensaje(e.getMessage());
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		return consultaOCRWebServiceResultado;
	}
	consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
	consultaOCRWebServiceResultado.setMensaje(""+informePedido.length);
	return consultaOCRWebServiceResultado;
	
	}
	
	/**
	 * Completa el arreglo de consultoras sin pedido para ser enviado
	 * @param list
	 * @return
	 */
	private ConsultorasActivasSinPedidoWebService[] completarListConsultoraSinPedido (
			List list) throws Exception {
		ConsultorasActivasSinPedidoWebService[] listConsSinPedido;
		ConsultorasActivasSinPedidoBean pedidoBean;
		listConsSinPedido= new ConsultorasActivasSinPedidoWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 pedidoBean=(ConsultorasActivasSinPedidoBean)it.next();
			 ConsultorasActivasSinPedidoWebService consSinPedido=new ConsultorasActivasSinPedidoWebService();
			 BeanUtils.copyProperties(consSinPedido, pedidoBean);
			 listConsSinPedido[i++]=consSinPedido;
		 }
		return listConsSinPedido;
	}

	/**
	 * Completa el arreglo de informe de avance de pedido para ser enviado
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private InformeOCRPedidoWebService[] completarListInformePedido(List list) throws Exception {
		InformeOCRPedidoWebService[] listInformePedido;
		InformeOCRPedidoBean pedidoBean;
		listInformePedido= new InformeOCRPedidoWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 pedidoBean=(InformeOCRPedidoBean)it.next();
			 InformeOCRPedidoWebService pedido=new InformeOCRPedidoWebService();
			 BeanUtils.copyProperties(pedido, pedidoBean);
			 listInformePedido[i++]=pedido;
		 }
		return listInformePedido;
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

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getConsultorasActivasSinPedido(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaOCRWebServiceResultado getConsultorasActivasSinPedido(
			String codigoPeriodo, String codigoZona, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();		
		ConsultorasActivasSinPedidoWebService [] consultorasSinPedido = null;
	try{	
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
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",codigoPeriodo);
		criteria.put("codigoZona", codigoZona);
		List listConsultorasActivasSinPedido = reporteService.getConsultorasActivasSinPedido(criteria);
		log.debug("list cons sin pedidos  " +listConsultorasActivasSinPedido.size());
		consultorasSinPedido = completarListConsultoraSinPedido(listConsultorasActivasSinPedido);
			
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(consultorasSinPedido);
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
	}catch(Exception e){
		e.printStackTrace();
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
		consultaOCRWebServiceResultado.setMensaje(e.getMessage());
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		return consultaOCRWebServiceResultado;
	}
	consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
	consultaOCRWebServiceResultado.setMensaje(""+consultorasSinPedido.length);
	return consultaOCRWebServiceResultado;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getFechaUltimaActualizacionZona(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaOCRWebServiceResultado getFechaUltimaActualizacionZona(
			String codigoPeriodo, String codigoZona, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();
		String fechaUltimaActZona="";
	try{	
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
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",codigoPeriodo);
		criteria.put("codigoZona", codigoZona);
		
		 fechaUltimaActZona = reporteService.getFechaUltimaActualizacionZona(criteria);
		
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona(fechaUltimaActZona);
	}catch(Exception e){
		e.printStackTrace();
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
		consultaOCRWebServiceResultado.setMensaje(e.getMessage());
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		return consultaOCRWebServiceResultado;
	}
	consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
	consultaOCRWebServiceResultado.setMensaje(fechaUltimaActZona);
	return consultaOCRWebServiceResultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getNumeroRegistros(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaOCRWebServiceResultado getNumeroRegistros(
			String codigoPeriodo, String codigoZona, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();
		int numeroRegistros=0;
	try{	
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
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",codigoPeriodo);
		criteria.put("codigoZona", codigoZona);
		
		numeroRegistros = reporteService.getNumeroRegistros(criteria);
		
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setNumeroRegistros(String.valueOf(numeroRegistros));
	}catch(Exception e){
		e.printStackTrace();
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
		consultaOCRWebServiceResultado.setMensaje(e.getMessage());
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		consultaOCRWebServiceResultado.setNumeroRegistros("0");
		return consultaOCRWebServiceResultado;
	}
	consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
	consultaOCRWebServiceResultado.setMensaje(String.valueOf(numeroRegistros));
	return consultaOCRWebServiceResultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getInformeAvancePedido(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String [] getInformeAvancePedido(String codigoPeriodo,
			String codigoZona, String codigoIsoIdioma) throws RemoteException {
		String [] arrInforme = null;
	try{	
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
		
		//HttpServletRequest request = (HttpServletRequest)MessageContext.getCurrentContext().getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
		//log.debug("ip >> "+ request.getRemoteAddr());
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",codigoPeriodo);
		criteria.put("codigoZona", codigoZona);
		
		
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", PAIS_DEFAULT);
		criteriaParam.put("codigoSistema", "OCR");
		criteriaParam.put("nombreParametro", "indWebServiceOCR");
	      //criteria.put("valorParametro", value);
	    String indicadorWebServiceOcr = stoService.getParametroGenericoSistema(criteriaParam);
	    log.debug("indicadorWebServiceOcr "+indicadorWebServiceOcr);
	    List listInforme = null;
		if(Constants.NRO_CERO.equals(indicadorWebServiceOcr)){
			listInforme = reporteService.getInformeAvancePedido(criteria);
		}else{
		 if(Constants.NRO_UNO.equals(indicadorWebServiceOcr)){
			criteria.put("codigoPais", PAIS_DEFAULT);
			listInforme = reporteService.getProcesoInformeAvancePedido(criteria);
		 }else{
			 listInforme = new ArrayList(); 
		 }
		}
		
		log.debug("list informe ocr " +listInforme.size());
		Iterator it = listInforme.iterator();
		arrInforme = new String[listInforme.size()];
		int i=0;
		while(it.hasNext()){
			String informe =(String)it.next();
			arrInforme[i++] = informe;
		}
				
	}catch(Exception e){
		e.printStackTrace();
		arrInforme=null;
		return arrInforme;
	}

	return arrInforme;
 }
    
}
