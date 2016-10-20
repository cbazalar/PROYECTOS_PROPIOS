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
import biz.belcorp.ssicc.dao.scsicc.bean.DetallePedidoFacturadoBean;
import biz.belcorp.ssicc.dao.scsicc.bean.DetallePedidoNoFacturadoBean;
import biz.belcorp.ssicc.dao.scsicc.bean.FaltantesAnunciadosBean;
import biz.belcorp.ssicc.dao.scsicc.bean.VentasRechazadasBean;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.ws.ConsultaOCRAvancePedidoWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultaOCRWebServiceResultado;
import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultorasActivasSinPedidoWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.DetallePedidoFacturadoWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.DetallePedidoNoFacturadoWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.FaltantesAnunciadosWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.VentasRechazadasWebService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaInformeOCRAvancePedidoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sbuchelli@belcorp.biz"> sbuchelli </a>
 */
public class ConsultaOCRAvancePedidoWebServiceImpl extends ServletEndpointSupport implements
		ConsultaOCRAvancePedidoWebService	 {

    Log log = LogFactory.getLog(ConsultaOCRAvancePedidoWebServiceImpl.class);
    private static final String PAIS_DEFAULT = "PE";
    ReporteService reporteService;    
    MantenimientoOCRPedidoControlFacturacionService service;
    MantenimientoSTOBloqueoControlService stoService ;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	reporteService = (ReporteService)getWebApplicationContext().getBean("scsicc.reporteService");
    	service = (MantenimientoOCRPedidoControlFacturacionService) 
    	               getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
    	stoService = (MantenimientoSTOBloqueoControlService)getWebApplicationContext().getBean("spusicc.mantenimientoSTOBloqueoControlService");
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
			   String codigoZona, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();		
		ConsultorasActivasSinPedidoWebService [] consultorasSinPedido = null;
	try{	
//		if (StringUtils.isBlank(codigoPeriodo)){
//			String mensajeError = getWebApplicationContext().
//				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
//			throw  new Exception(mensajeError);				
//		}
		
		if (StringUtils.isBlank(codigoZona)){
			String mensajeError = getWebApplicationContext().
				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoZona",null,getLocaleIdioma(codigoIsoIdioma));
			throw  new Exception(mensajeError);				
		}
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",getCampanhaActivaByZona(codigoZona));
		criteria.put("codigoZona", codigoZona);
		List listConsultorasActivasSinPedido = reporteService.getConsultorasActivasSinPedidoAct(criteria);
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
			  String codigoZona, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();
		String fechaUltimaActZona="";
	try{	
//		if (StringUtils.isBlank(codigoPeriodo)){
//			String mensajeError = getWebApplicationContext().
//				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
//			throw  new Exception(mensajeError);				
//		}
		
		if (StringUtils.isBlank(codigoZona)){
			String mensajeError = getWebApplicationContext().
				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoZona",null,getLocaleIdioma(codigoIsoIdioma));
			throw  new Exception(mensajeError);				
		}
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",getCampanhaActivaByZona(codigoZona));
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
			String codigoZona, String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();
		int numeroRegistros=0;
	try{	
//		if (StringUtils.isBlank(codigoPeriodo)){
//			String mensajeError = getWebApplicationContext().
//				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
//			throw  new Exception(mensajeError);				
//		}
		
		if (StringUtils.isBlank(codigoZona)){
			String mensajeError = getWebApplicationContext().
				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoZona",null,getLocaleIdioma(codigoIsoIdioma));
			throw  new Exception(mensajeError);				
		}
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",getCampanhaActivaByZona(codigoZona));
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
	public String [] getInformeAvancePedido(String codigoZona, String codigoIsoIdioma) throws RemoteException {
		String [] arrInforme = null;
	try{	
//		if (StringUtils.isBlank(codigoPeriodo)){
//			String mensajeError = getWebApplicationContext().
//				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
//			throw  new Exception(mensajeError);				
//		}
		
		if (StringUtils.isBlank(codigoZona)){
			String mensajeError = getWebApplicationContext().
				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoZona",null,getLocaleIdioma(codigoIsoIdioma));
			throw  new Exception(mensajeError);				
		}
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo",getCampanhaActivaByZona(codigoZona));
		criteria.put("codigoZona", codigoZona);
		//List listInforme = reporteService.getInformeAvancePedidoAct(criteria);
		
		
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", PAIS_DEFAULT);
		criteriaParam.put("codigoSistema", "OCR");
		criteriaParam.put("nombreParametro", "indWebServiceOCRAct");
	      //criteria.put("valorParametro", value);
	    String indicadorWebServiceOcr = stoService.getParametroGenericoSistema(criteriaParam);
	    log.debug("indicadorWebServiceOcr "+indicadorWebServiceOcr);
	    List listInforme = null;
		if(Constants.NRO_CERO.equals(indicadorWebServiceOcr)){
			listInforme = reporteService.getInformeAvancePedidoAct(criteria);
		}else{
		 if(Constants.NRO_UNO.equals(indicadorWebServiceOcr)){
			criteria.put("codigoPais", PAIS_DEFAULT);
			listInforme = reporteService.getProcesoInformeAvancePedidoAct(criteria);
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getCodigoVentasRechazados(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaOCRWebServiceResultado getCodigoVentasRechazados(
			 String codigoCliente, String codigoZona,String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();		
		VentasRechazadasWebService [] codigoVentasRechazados = null;
		try{	
//			if (StringUtils.isBlank(codigoPeriodo)){
//				String mensajeError = getWebApplicationContext().
//					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
//				throw  new Exception(mensajeError);				
//			}
			
			if (StringUtils.isBlank(codigoCliente)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoCliente",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo",getCampanhaActivaByZona(codigoZona));
			criteria.put("codigoCliente", codigoCliente);
			List list = reporteService.getCodigoVentasRechazados(criteria);
			log.debug("list getCodigoVentasRechazados  " +list.size());
			codigoVentasRechazados = completarListCodigoVentaRechazado(list);
				
			consultaOCRWebServiceResultado.setListVentaRechazadas(codigoVentasRechazados);
			consultaOCRWebServiceResultado.setListDetallePedidoFacturado(null);
			consultaOCRWebServiceResultado.setListFaltantesAnunciado(null);			
			consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
			consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
			consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		}catch(Exception e){
			e.printStackTrace();
			consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
			consultaOCRWebServiceResultado.setMensaje(e.getMessage());
			consultaOCRWebServiceResultado.setListVentaRechazadas(null);
			consultaOCRWebServiceResultado.setListDetallePedidoFacturado(null);
			consultaOCRWebServiceResultado.setListFaltantesAnunciado(null);			
			consultaOCRWebServiceResultado.setListInformeOCRWebService(null);
			consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
			consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
			return consultaOCRWebServiceResultado;
		}
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
		consultaOCRWebServiceResultado.setMensaje(""+codigoVentasRechazados.length);
		return consultaOCRWebServiceResultado;
	}

	
	/**
	 * Completa el arreglo de codigo ventas rachazados por consultoras 
	 * @param list
	 * @return
	 */
	private VentasRechazadasWebService[] completarListCodigoVentaRechazado (
			List list) throws Exception {
		VentasRechazadasWebService[] listResult;
		VentasRechazadasBean bean;
		listResult= new VentasRechazadasWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 bean=(VentasRechazadasBean)it.next();
			 VentasRechazadasWebService obj=new VentasRechazadasWebService();
			 BeanUtils.copyProperties(obj, bean);
			 listResult[i++]=obj;
		 }
		return listResult;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getDetallePedidoFacturado(java.lang.Integer, java.lang.String)
	 */
	public ConsultaOCRWebServiceResultado getDetallePedidoFacturado(
			Integer oidPedido, String codigoIsoIdioma) throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();		
		DetallePedidoFacturadoWebService [] detallePedido = null;
	try{	
		if (oidPedido==null){
			String mensajeError = getWebApplicationContext().
				getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarPedido",null,getLocaleIdioma(codigoIsoIdioma));
			throw  new Exception(mensajeError);				
		}
		
		Map criteria = new HashMap();
		criteria.put("oidPedido",oidPedido);		
		List list = reporteService.getDetallePedidoFacturado(criteria);
		log.debug("list getDetallePedidoFacturado  " +list.size());
		detallePedido = completarListDetallePedidoFacturado(list);

		consultaOCRWebServiceResultado.setListDetallePedidoFacturado(detallePedido);
		consultaOCRWebServiceResultado.setListVentaRechazadas(null);		
		consultaOCRWebServiceResultado.setListFaltantesAnunciado(null);		
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
	}catch(Exception e){
		e.printStackTrace();
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
		consultaOCRWebServiceResultado.setMensaje(e.getMessage());
		consultaOCRWebServiceResultado.setListDetallePedidoFacturado(null);
		consultaOCRWebServiceResultado.setListVentaRechazadas(null);		
		consultaOCRWebServiceResultado.setListFaltantesAnunciado(null);		
		consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
		consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
		consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		return consultaOCRWebServiceResultado;
	}
	consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
	consultaOCRWebServiceResultado.setMensaje(""+detallePedido.length);
	return consultaOCRWebServiceResultado;
	}

	
	/**
	 * Completa el arreglo de detalle pedido facturado 
	 * @param list
	 * @return
	 */
	private DetallePedidoFacturadoWebService[] completarListDetallePedidoFacturado (
			List list) throws Exception {
		DetallePedidoFacturadoWebService[] listResult;
		DetallePedidoFacturadoBean bean;
		listResult= new DetallePedidoFacturadoWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 bean=(DetallePedidoFacturadoBean)it.next();
			 DetallePedidoFacturadoWebService obj=new DetallePedidoFacturadoWebService();
			 BeanUtils.copyProperties(obj, bean);
			 listResult[i++]=obj;
		 }
		return listResult;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getFaltantesAnunciados(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaOCRWebServiceResultado getFaltantesAnunciados(
		   String codigoCliente, String codigoZona,String codigoIsoIdioma)
			throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();		
		FaltantesAnunciadosWebService [] faltantesAnunciados = null;
		try{	
//			if (StringUtils.isBlank(codigoPeriodo)){
//				String mensajeError = getWebApplicationContext().
//					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(codigoIsoIdioma));
//				throw  new Exception(mensajeError);				
//			}
			
			if (StringUtils.isBlank(codigoCliente)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoCliente",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
			}
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo",getCampanhaActivaByZona(codigoZona));
			criteria.put("codigoCliente", codigoCliente);
			List list = reporteService.getFaltantesAnunciados(criteria);
			log.debug("list getFaltantesAnunciados  " +list.size());
			faltantesAnunciados = completarListFaltantesAnunciados(list);

			consultaOCRWebServiceResultado.setListFaltantesAnunciado(faltantesAnunciados);
			consultaOCRWebServiceResultado.setListVentaRechazadas(null);
			consultaOCRWebServiceResultado.setListDetallePedidoFacturado(null);						
			consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
			consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
			consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		}catch(Exception e){
			e.printStackTrace();
			consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
			consultaOCRWebServiceResultado.setMensaje(e.getMessage());
			consultaOCRWebServiceResultado.setListFaltantesAnunciado(faltantesAnunciados);
			consultaOCRWebServiceResultado.setListVentaRechazadas(null);
			consultaOCRWebServiceResultado.setListDetallePedidoFacturado(null);						
			consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
			consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
			consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
			return consultaOCRWebServiceResultado;
		}
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
		consultaOCRWebServiceResultado.setMensaje(""+faltantesAnunciados.length);
		return consultaOCRWebServiceResultado;
	
	}
	
	/**
	 * Completa el arreglo de faltantes anunciados po consultora 
	 * @param list
	 * @return
	 */
	private FaltantesAnunciadosWebService[] completarListFaltantesAnunciados (
			List list) throws Exception {
		FaltantesAnunciadosWebService[] listResult;
		FaltantesAnunciadosBean bean;
		listResult= new FaltantesAnunciadosWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 bean=(FaltantesAnunciadosBean)it.next();
			 FaltantesAnunciadosWebService obj=new FaltantesAnunciadosWebService();
			 BeanUtils.copyProperties(obj, bean);
			 listResult[i++]=obj;
		 }
		return listResult;
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaInformeOCRAvancePedidoWebService#getCampanhaActivaByZona(java.lang.String, java.lang.String)
	 */
	public String getCampanhaActivaByZona(String codigoZona)
			throws RemoteException {
		 
		 Map criteriaPeriodo = new HashMap();
		 criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		 criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente	   
		 criteriaPeriodo.put("codigoZona", codigoZona);
		 
		 String  periodo = reporteService.getCampanhaActivaByZona(criteriaPeriodo);
		 	
		 return (periodo!=null ? 
				 periodo:"");
	}


	public ConsultaOCRWebServiceResultado getDetallePedidoNoFacturado(
			String codigoCliente) throws RemoteException {
		ConsultaOCRWebServiceResultado consultaOCRWebServiceResultado = new ConsultaOCRWebServiceResultado();		
		DetallePedidoNoFacturadoWebService [] detallePedidoNoFacturado = null;
		try{	
			if (StringUtils.isBlank(codigoCliente)){				
				String mensajeError = getWebApplicationContext().
					getMessage("consultaInformeOCRAvancePedidoWebService.msg.validarCodigoCliente",null,getLocaleIdioma(null));
				throw  new Exception(mensajeError);				
			}
			Map criteria = new HashMap();
			criteria.put("codigoCliente", codigoCliente);
			List list = reporteService.getDetallePedidoNoFacturado(criteria);
			log.debug("list getDetallePedidoNoFacturado  " +list.size());
			detallePedidoNoFacturado = completarListDetallePedidoNoFacturado(list);

			consultaOCRWebServiceResultado.setListDetallePedidoNoFacturado(detallePedidoNoFacturado);
			consultaOCRWebServiceResultado.setListFaltantesAnunciado(null);
			consultaOCRWebServiceResultado.setListVentaRechazadas(null);
			consultaOCRWebServiceResultado.setListDetallePedidoFacturado(null);						
			consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
			consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
			consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
		}catch(Exception e){
			e.printStackTrace();
			consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
			consultaOCRWebServiceResultado.setMensaje(e.getMessage());
			consultaOCRWebServiceResultado.setListDetallePedidoNoFacturado(detallePedidoNoFacturado);
			consultaOCRWebServiceResultado.setListFaltantesAnunciado(null);
			consultaOCRWebServiceResultado.setListVentaRechazadas(null);
			consultaOCRWebServiceResultado.setListDetallePedidoFacturado(null);						
			consultaOCRWebServiceResultado.setListInformeOCRWebService(null);		
			consultaOCRWebServiceResultado.setListConsultorasActivasSinPedidoWebService(null);
			consultaOCRWebServiceResultado.setFechaUltimaActualizacionZona("");
			return consultaOCRWebServiceResultado;
		}
		consultaOCRWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
		consultaOCRWebServiceResultado.setMensaje(""+detallePedidoNoFacturado.length);
		return consultaOCRWebServiceResultado;
	}

	/**
	 * Completa el arreglo de detalle pedido facturado 
	 * @param list
	 * @return
	 */
	private DetallePedidoNoFacturadoWebService[] completarListDetallePedidoNoFacturado (
			List list) throws Exception {
		DetallePedidoNoFacturadoWebService[] listResult;
		DetallePedidoNoFacturadoBean bean;
		listResult= new DetallePedidoNoFacturadoWebService [list.size()];
		Iterator it=list.iterator();
		 int i=0;
		 while(it.hasNext()){
			 bean=(DetallePedidoNoFacturadoBean)it.next();
			 DetallePedidoNoFacturadoWebService obj=new DetallePedidoNoFacturadoWebService();
			 BeanUtils.copyProperties(obj, bean);
			 listResult[i++]=obj;
		 }
		return listResult;
	}
}
