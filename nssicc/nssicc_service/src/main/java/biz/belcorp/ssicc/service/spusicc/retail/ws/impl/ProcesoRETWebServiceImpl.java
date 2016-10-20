/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.ParametroInterfazService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.spusicc.comision.retail.ProcesoRETAsignacionVentasRetailService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsolidadoRetailRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.LocalCabecRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.LocalDetalRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ProcesoRETWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentasRetaiDetalleRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentasRetailCabecRETWebService;
import biz.belcorp.ssicc.service.util.impl.MailUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRETWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sbuchelli@belcorp.biz"> sbuchelli </a>
 */
public class ProcesoRETWebServiceImpl extends ServletEndpointSupport implements
			ProcesoRETWebService{

    Log log = LogFactory.getLog(ProcesoRETWebServiceImpl.class);
    
    ProcesoRETAsignacionVentasRetailService procesoRETService;
    ReporteService reporteService;
    /* INI JJ PER-SiCC-2012-0348 */
    GenericoService genericoService;
    
    ParametroInterfazService parametroInterfazService;
    
    MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
    
    InterfazSiCCService interfazSiCCService;
    
    private GenericoDAO genericoDAO;
    
    /* FIN JJ PER-SiCC-2012-0348 */
    
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	procesoRETService = (ProcesoRETAsignacionVentasRetailService)getWebApplicationContext().getBean("spusicc.procesoRETAsignacionVentasRetailService");
    	
    	reporteService = (ReporteService) getWebApplicationContext().getBean("scsicc.reporteService");
    	/* INI JJ PER-SiCC-2012-0348 */
    	
    	genericoService = (GenericoService) getWebApplicationContext().getBean("genericoService");
    	
    	parametroInterfazService = (ParametroInterfazService) getWebApplicationContext().getBean("sisicc.parametroInterfazService");
    	
    	mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
    	
    	interfazSiCCService = (InterfazSiCCService)  getWebApplicationContext().getBean("sisicc.interfazSiCCService");
    	
    	genericoDAO = (GenericoDAO)getWebApplicationContext().getBean("genericoDAO");
    	
    	/* FIN JJ PER-SiCC-2012-0348 */
    }


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.retail.web.ws.ProcesoRETWebService#saveVentaRetail(java.lang.String, java.lang.String, biz.belcorp.ssicc.spusicc.retail.web.ws.beans.VentasRetailCabecRETWebService, biz.belcorp.ssicc.spusicc.retail.web.ws.beans.VentasRetaiDetalleRETWebService, biz.belcorp.ssicc.spusicc.retail.web.ws.beans.ConsolidadoRetailRETWebService, java.lang.String)
	 */
	public ProcesoRETWebServiceResultado saveVentaRetail(String codigoPais,
			String fechaDocumentos,
			VentasRetailCabecRETWebService [] retailCabecera,
			VentasRetaiDetalleRETWebService [] retailDetalle,
			ConsolidadoRetailRETWebService [] retailConsolidado,
			String usuario,
			String codigoIsoIdioma,
			String tipoProceso) throws RemoteException {
		
		log.debug(" inicio saveVentaRetail ");
		log.debug(" codigoPais: " +codigoPais);
		log.debug(" fechaDocumentos: " + fechaDocumentos);
		log.debug(" usuario " + usuario);
		log.debug(" codigoIsoIdioma " + codigoIsoIdioma);
		log.debug(" tipoProceso " + tipoProceso);
		
		ProcesoRETWebServiceResultado procesoRETWebServiceResultado =new ProcesoRETWebServiceResultado();
		LocalCabecRETWebService localCabecRETWebService[] = null;
		LocalDetalRETWebService localDetalRETWebService[] = null;
		String mensajeError="";
		String filaError="";
		List listRetailCabecera= new ArrayList();
		List listRetailDetalle = new ArrayList();
		List listRetailConsolidado = new ArrayList();
    	try{
    		log.debug(" inicio saveVentaRetail ");
    		
    		//validacion Pais
    		if(StringUtils.isEmpty(codigoPais)){
    			mensajeError = getWebApplicationContext().
					getMessage("procesoRETWebService.msg.validarPais",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}
    		//Validacion  Fecha
    		mensajeError= validarFormatoFecha(fechaDocumentos,Constants.NUMERO_UNO);
    		if(StringUtils.isNotEmpty(mensajeError)){
    			mensajeError = getWebApplicationContext().
						getMessage("procesoRETWebService.msg.validarFecha",null,getLocaleIdioma(codigoIsoIdioma));
    			throw  new Exception(mensajeError);
    		}
    		//validacion usuario
    		if(StringUtils.isEmpty(usuario)){
    			//se utiliza el usuario generico de retail
    			usuario = Constants.RET_USUARIO_GENERICO;				
    		}    		
    		
    		//Validacion Cabecera
    		if(retailCabecera==null || (retailCabecera!=null && retailCabecera.length==0 )) {
    			mensajeError = getWebApplicationContext().
						getMessage("procesoRETWebService.msg.validarRetail",null,getLocaleIdioma(codigoIsoIdioma));
    			throw  new Exception(mensajeError);
    		}
    		localCabecRETWebService = new LocalCabecRETWebService[retailCabecera.length];
    		for(int i=0; i<retailCabecera.length;i++){
    			localCabecRETWebService[i] = new LocalCabecRETWebService();
	    		mensajeError= validarRetailCabecera(retailCabecera[i],codigoIsoIdioma,tipoProceso);
	    		if(StringUtils.isNotEmpty(mensajeError)){
	        		filaError = getWebApplicationContext().
					getMessage("procesoRETWebService.msg.filaError",new String[]{""+(i+1)},getLocaleIdioma(codigoIsoIdioma));
	    			throw  new Exception(filaError+mensajeError);
	    		}
	    		Map mapCabecera =BeanUtils.describe(retailCabecera[i]);
	    		mapCabecera.put("fechaEnvio", StringUtils.isEmpty(retailCabecera[i].getFechaEnvio())?fechaDocumentos:retailCabecera[i].getFechaEnvio());
	    		mapCabecera.put("usuario", usuario);
	    		mapCabecera.put("codigoIsoIdioma", codigoIsoIdioma);
	    		listRetailCabecera.add(mapCabecera);
	    		
	    		localCabecRETWebService[i].setCampanha(retailCabecera[i].getCamProc());
	    		localCabecRETWebService[i].setCodigoLocal(retailCabecera[i].getCodigoLocal());
	    		localCabecRETWebService[i].setFecha(DateUtil.convertDateToString("dd/MM/yyyy", convertStringToDate("ddMMyyyy", retailCabecera[i].getFechaEnvio())));
	    		localCabecRETWebService[i].setTotalTrans(retailCabecera[i].getTransRetail());
    		}
    		//fin cabecera
    		
    		//Validacion Detalle
    		if(retailDetalle==null || (retailDetalle!=null && retailDetalle.length==0)){
    			mensajeError = getWebApplicationContext().
				getMessage("procesoRETWebService.msg.validarRetailDetalle",null,getLocaleIdioma(codigoIsoIdioma));
    			throw  new Exception(mensajeError);
    		}
    		localDetalRETWebService = new LocalDetalRETWebService[retailDetalle.length];
    		for(int i=0;i<retailDetalle.length;i++){
    			localDetalRETWebService[i] = new LocalDetalRETWebService();
    			mensajeError= validarRetailDetalle(retailDetalle[i],codigoIsoIdioma);
        		if(StringUtils.isNotEmpty(mensajeError)){
        			filaError = getWebApplicationContext().
					getMessage("procesoRETWebService.msg.filaError",new String[]{""+(i+1)},getLocaleIdioma(codigoIsoIdioma));
	    			throw  new Exception(filaError+mensajeError);
        		}
        		Map mapDetalle =BeanUtils.describe(retailDetalle[i]);
        		mapDetalle.put("fechaEnvio", StringUtils.isEmpty(retailDetalle[i].getFechaEnvio())?fechaDocumentos:retailDetalle[i].getFechaEnvio());
        		mapDetalle.put("usuario", usuario);
        		mapDetalle.put("codigoIsoIdioma", codigoIsoIdioma);
        		listRetailDetalle.add(mapDetalle);        		
        		
        		localDetalRETWebService[i].setCampanha(retailDetalle[i].getCampanha());
        		localDetalRETWebService[i].setCodigoLocal(retailDetalle[i].getCodigoLocal());
        		localDetalRETWebService[i].setFecha(DateUtil.convertDateToString("dd/MM/yyyy", convertStringToDate("ddMMyyyy", retailDetalle[i].getFechaEnvio())));
    		}//fin validacion detalle
    	  //realizamos al carga de consolidado
    		log.debug("carga consolidado");
    		if(retailConsolidado != null && retailConsolidado.length>0){
    		  for(int i=0;i<retailConsolidado.length;i++){
      			mensajeError= validarRetailConsolidado(retailConsolidado[i],codigoIsoIdioma);
        		if(StringUtils.isNotEmpty(mensajeError)){
        			filaError = getWebApplicationContext().
					getMessage("procesoRETWebService.msg.filaError",new String[]{""+(i+1)},getLocaleIdioma(codigoIsoIdioma));
	    			throw  new Exception(filaError+mensajeError);
        		}    			  
    			  Map mapConsolidado =BeanUtils.describe(retailConsolidado[i]);
    			 
    			  mapConsolidado.put("fecha", StringUtils.isEmpty(retailConsolidado[i].getFecha())?fechaDocumentos:retailConsolidado[i].getFecha());
    			  mapConsolidado.put("usuario", usuario);
    			  mapConsolidado.put("codigoIsoIdioma", codigoIsoIdioma);
    			  listRetailConsolidado.add(mapConsolidado);
    		  }
    		  
    		}
    	   //Iniciamos con el proceso de carga	
    	   //realizamos la carga de cabecera y detalle    		
    		procesoRETService.saveVentaRetail(listRetailCabecera,listRetailDetalle,tipoProceso);
    	   //insertamos consolidado
    		procesoRETService.saveConsolidadoRetail(listRetailConsolidado);
    	   String msgExito =  getWebApplicationContext().
				getMessage("procesoRETWebService.msg.exito",
						    new String[]{""+listRetailCabecera.size(),
						                 ""+listRetailDetalle.size(),
						                 ""+listRetailConsolidado.size()},
						    getLocaleIdioma(codigoIsoIdioma));	
    	   procesoRETWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_OK);
    	   procesoRETWebServiceResultado.setMensaje(msgExito);
    	   procesoRETWebServiceResultado.setLocalCabecRETWebServices(localCabecRETWebService);
    	   procesoRETWebServiceResultado.setLocalDetalRETWebServices(localDetalRETWebService);
    	   Map criteria = new HashMap();
   		   criteria.put("codigoPais",codigoPais);
   		   criteria.put("nombreReporte","procesoRETVentaRetailResultado"); //nombre del proceso sirve para buscar en la tabla generica de envios de correo
   		   Map paramReporte =reporteService.getParametrosReporte(criteria);
   		   if(paramReporte!=null){			
			String enviarCorreo = (String) paramReporte.get("enviarCorreo");						
			if (Constants.SI.equals(enviarCorreo)) 
    	        sendEnviarCorreoCorrecto(codigoPais,usuario,criteria,paramReporte);			
   		   }
    	}catch(Exception e){
    		log.debug("error en save  " +e.getMessage());
   		    procesoRETWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
    		procesoRETWebServiceResultado.setMensaje(e.getMessage());
    		procesoRETWebServiceResultado.setLocalCabecRETWebServices(null);
    		procesoRETWebServiceResultado.setLocalDetalRETWebServices(null);
    		//enviando correo electronico 
    		try{
      		  sendEnviarCorreo(codigoPais,e.getMessage());
      		}catch(Exception f){
      			log.debug("error en send mail  " +f.getMessage());
      		}
    	}
	   return procesoRETWebServiceResultado;
	}

	/**
	 * envia correo si todo es correcto
	 * @param codigoPais
	 * @param msgError 
	 */
	private void sendEnviarCorreoCorrecto(String codigoPais,String usuario,Map criteria, Map paramReporte) {
		Map param = new HashMap();		
		String campana="";
		String fechaProceso="";		
		List listCampana=procesoRETService.listaCampanaFechaProceso();
		Iterator it = listCampana.iterator();
		while(it.hasNext()){
			Map map2 = (Map)it.next();
			fechaProceso= (String) map2.get("fechaProceso");
			campana= (String) map2.get("campana");  
		}		
		param.put("fechaProceso",fechaProceso);
		param.put("campana",campana);
		List listCabecera=procesoRETService.listaRetailCabecera(param);
		List listDetalle=procesoRETService.listaRetailDetalle(param);
		BigDecimal totalCabecera=new BigDecimal("0.0");
		Iterator itcabe = listCabecera.iterator();
		while(itcabe.hasNext()){
			Map map = (Map)itcabe.next();
			totalCabecera= (BigDecimal) map.get("totalTransacciones");  
			listCabecera.remove(map);
			break;
		}
		BigDecimal totalDetalle= new BigDecimal("0.0");
		Iterator itdet = listDetalle.iterator();
		while(itdet.hasNext()){
			Map map1 = (Map)itdet.next();
			totalDetalle= (BigDecimal) map1.get("totalTransacciones");  
			listDetalle.remove(map1);
			break;
		}
		
		
		List listPais=procesoRETService.listaPais(criteria);
		Iterator it1 = listPais.iterator();
		String descPais="";
		while(it1.hasNext()){
			Map map3 = (Map)it1.next();
			descPais=(String) map3.get("pais");  	
		}		
		paramReporte.put("correosDestinos",(String) paramReporte.get("correoDefault"));
		String subject=(String) paramReporte.get("subject");
		subject=subject+" - "+fechaProceso+" - "+descPais+" - "+campana;
		paramReporte.put("subject",subject);		
		MailParams mailParams = new MailParams();
		criteria.put("totalCabecera",totalCabecera);
		criteria.put("totalDetalle",totalDetalle);	
		criteria.put("campana",campana);
		criteria.put("fechaProceso",fechaProceso);	
		criteria.put("descPais",descPais);	
		criteria.put("listCabecera",listCabecera);
		criteria.put("listDetalle",listDetalle);
		criteria.put("usuarioIngreso",usuario);
		paramReporte.put("parameterMap",criteria);								
		paramReporte.put("listCabecera",listCabecera);
		paramReporte.put("listDetalle",listDetalle);
		paramReporte.put("fechaProceso",fechaProceso);
		paramReporte.put("campana",campana);
		paramReporte.put("usuarioIngreso",usuario);
		paramReporte.put("descPais",descPais);	
		paramReporte.put("totalCabecera",totalCabecera);
		paramReporte.put("totalDetalle",totalDetalle);	
		
		//mailParams.setPais();
		String body = (String) paramReporte.get("bodyHtml");
		String bodyTxt = (String) paramReporte.get("bodyTxt");
		mailParams.setQueryParams(paramReporte);
		
		BaseMailService mailService = (BaseMailService) getWebApplicationContext().getBean(this.getMailService()); 
		//mailService.setPlantillaBodyTxt(body);
		criteria.put("bodyHtml", body);
		criteria.put("bodyTxt", bodyTxt);
		mailService.enviarMail(mailParams);	
	}
	

	/**
	 * Valida elemnetos del consolidado
	 * @param retailConsolidado
	 * @param codigoIsoIdioma
	 * @return
	 */
	private String validarRetailConsolidado(
			ConsolidadoRetailRETWebService retailConsolidado,
			String codigoIsoIdioma) {
		String msgError="";
		if(retailConsolidado!=null){	
			if(StringUtils.isEmpty(retailConsolidado.getCodigoPais())){
			  return  getWebApplicationContext().
					        getMessage("procesoRETWebService.msg.validarPaisRetailConso",null,getLocaleIdioma(codigoIsoIdioma));						
			}		

			if(StringUtils.isEmpty(retailConsolidado.getTipoDocu())){
				  return  getWebApplicationContext().
						        getMessage("procesoRETWebService.msg.validarTipoDocConso",null,getLocaleIdioma(codigoIsoIdioma));						
				}	
			
		  }else{
			  msgError = getWebApplicationContext().
				getMessage("procesoRETWebService.msg.validarConsolidado",null,getLocaleIdioma(codigoIsoIdioma));
		  }
			  		
			return msgError;
	}


	/**
	 * envia correo si ocurre error
	 * @param codigoPais
	 * @param msgError 
	 */
	private void sendEnviarCorreo(String codigoPais, String msgError) {
		Map criteria = new HashMap();
		criteria.put("codigoPais",codigoPais);
		criteria.put("nombreReporte","procesoRETVentaRetail"); //nombre del proceso sirve para buscar en la tabla generica de envios de correo
				
		Map paramReporte = reporteService.getParametrosReporte(criteria);
		
		if(paramReporte!=null){
			paramReporte.put("correosDestinos",(String) paramReporte.get("correoDefault"));			
			String enviarCorreo = (String) paramReporte.get("enviarCorreo");						
			if (Constants.SI.equals(enviarCorreo)) {
								MailParams mailParams = new MailParams();
								criteria.put("msgError",msgError);
								paramReporte.put("parameterMap",criteria);								
								paramReporte.put("msgError",msgError);
								//mailParams.setUsuario(f.get);
								//mailParams.setPais();
								String body = (String) paramReporte.get("bodyTxt");
								mailParams.setQueryParams(paramReporte);
								BaseMailService mailService = (BaseMailService) getWebApplicationContext().getBean(this.getMailService());
								//MailUtil mailService = (MailUtil) getWebApplicationContext().getBean(this.getMailService()); 
								//mailService.setPlantillaBodyTxt(body);
								criteria.put("bodyTxt", body);
								mailService.enviarMail(mailParams);	
				
			} 				
		}
		
	}

	/**
	 * Devuelve Service a trabajar para el envio de correo del reporte
	 * Dicho metodo debe ser sobreescrito para que devuelva el Service correspondiente al reporte en
	 * ejecuci�n	
	 * @return
	 */
	private String getMailService () {
		String service = "sisicc.mailUtil";// "sisicc.baseMailAbstractService";		
		return service;
	}

	/**
	 * Retorna un mensaje de error si no pasa alguna validacion del detalle
	 * @param retailDetalle
	 * @return
	 */
	private String validarRetailDetalle(
			VentasRetaiDetalleRETWebService retailDetalle,String codigoIsoIdioma) {
		String msgError="";
		
		  if(retailDetalle!=null){	
			if(StringUtils.isEmpty(retailDetalle.getCodigoPais())){
			  return  getWebApplicationContext().
					        getMessage("procesoRETWebService.msg.validarPaisRetailDetalle",null,getLocaleIdioma(codigoIsoIdioma));						
			}		

			if(StringUtils.isEmpty(retailDetalle.getCodigoCanal())){
				  return  getWebApplicationContext().
					         getMessage("procesoRETWebService.msg.validarCanalDetalle",null,getLocaleIdioma(codigoIsoIdioma));						
			}
			
			if(StringUtils.isEmpty(retailDetalle.getCodigoAcceso())){
				  return  getWebApplicationContext().
					        getMessage("procesoRETWebService.msg.validarAccesoDetalle",null,getLocaleIdioma(codigoIsoIdioma));						
			}
			
			if(StringUtils.isEmpty(retailDetalle.getCodigoSubAcceso())){
				  return  getWebApplicationContext().
						    getMessage("procesoRETWebService.msg.validarSubAccesoDetalle",null,getLocaleIdioma(codigoIsoIdioma));						
			}
			
			if(StringUtils.isEmpty(retailDetalle.getCodigoLocal())){
				  return  getWebApplicationContext().
					        getMessage("procesoRETWebService.msg.validarLocalDetalle",null,getLocaleIdioma(codigoIsoIdioma));						
			}
			
//			msgError= validarFormatoFecha(retailDetalle.getFechaEnvio(),Constants.NUMERO_UNO);
//			if(StringUtils.isNotEmpty(msgError)){
//				return  getWebApplicationContext().
//			  			   getMessage("procesoRETWebService.msg.validarFechaRetailDetalle",null,getLocaleIdioma(codigoIsoIdioma));
//			}
					
		  }else{
			  msgError = getWebApplicationContext().
				getMessage("procesoRETWebService.msg.validarRetailDetalle",null,getLocaleIdioma(codigoIsoIdioma));
		  }
			  		
			return msgError;
	}


	/**
	 * Retorna un mensaje de error si no pasa alguna validacion de cabecera
	 * @param retailCabecera
	 * @return
	 */
	private String validarRetailCabecera(
			VentasRetailCabecRETWebService retailCabecera,String codigoIsoIdioma,String tipoProceso) {
		String msgError="";
		
	  if(retailCabecera!=null){	
		if(StringUtils.isEmpty(retailCabecera.getCodigoPais())){
		  return  getWebApplicationContext().
				        getMessage("procesoRETWebService.msg.validarPaisRetail",null,getLocaleIdioma(codigoIsoIdioma));						
		}		

		if(StringUtils.isEmpty(retailCabecera.getCodigoCanal())){
			  return  getWebApplicationContext().
				         getMessage("procesoRETWebService.msg.validarCanal",null,getLocaleIdioma(codigoIsoIdioma));						
		}
		
		if(StringUtils.isEmpty(retailCabecera.getCodigoAcceso())){
			  return  getWebApplicationContext().
				        getMessage("procesoRETWebService.msg.validarAcceso",null,getLocaleIdioma(codigoIsoIdioma));						
		}
		
		if(StringUtils.isEmpty(retailCabecera.getCodigoSubAcceso())){
			  return  getWebApplicationContext().
					    getMessage("procesoRETWebService.msg.validarSubAcceso",null,getLocaleIdioma(codigoIsoIdioma));						
		}
		
		if(StringUtils.isEmpty(retailCabecera.getCodigoLocal())){
			  return  getWebApplicationContext().
				        getMessage("procesoRETWebService.msg.validarLocal",null,getLocaleIdioma(codigoIsoIdioma));						
		}
		
//		msgError= validarFormatoFecha(retailCabecera.getFechaEnvio(),Constants.NUMERO_UNO);
//		if(StringUtils.isNotEmpty(msgError)){
//			return  getWebApplicationContext().
//		  			   getMessage("procesoRETWebService.msg.validarFechaRetail",null,getLocaleIdioma(codigoIsoIdioma));
//		}
		
		msgError= validarCampanha(retailCabecera.getCamProc());
		if(StringUtils.isNotEmpty(msgError)){
			return  getWebApplicationContext().
					   getMessage("procesoRETWebService.msg.validarCampanhaRetail",null,getLocaleIdioma(codigoIsoIdioma));
		}
		
		if (StringUtils.equals(StringUtils.trim(tipoProceso), Constants.PARAMETRO_TIPO_PROCESO_1)) {
			/* INI JJ PER-SiCC-2012-0348 */
				String codigoPeriodoRecepcion = retailCabecera.getCamProc();
		
				Map retailCabeceraMap = new HashMap();
        
				try {
					retailCabeceraMap = BeanUtils.describe(retailCabecera);
				} catch (Exception e) {
					e.printStackTrace();
				} 
		
				boolean campCerrada= validarCampanhaCerrada(retailCabeceraMap,codigoPeriodoRecepcion);
				if(campCerrada){
			
					String valores[] = {codigoPeriodoRecepcion,retailCabecera.getFechaEnvio()};
			
					return  getWebApplicationContext().getMessage("procesoRETWebService.msg.validarCampanhaCerradaRetail",valores,getLocaleIdioma(codigoIsoIdioma));
				}
		}
		/* FIN JJ PER-SiCC-2012-0348 */
	  }else{
		  msgError = getWebApplicationContext().
			getMessage("procesoRETWebService.msg.validarRetail",null,getLocaleIdioma(codigoIsoIdioma));
	  }
		  		
		return msgError;
	}


	/* INI JJ PER-SiCC-2012-0348 */
	
	/**
	 * @param retailCabeceraMap
	 * @param codigoPeriodoActivo
	 * @return
	 */
	private boolean validarCampanhaCerrada(Map retailCabeceraMap,String codigoPeriodoRecepcion) {
			
		boolean flag = false;
		String IndReproRet = "";
		
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais((String)retailCabeceraMap.get("codigoPais"));
		parametroPais.setCodigoSistema(Constants.INT_RETAIL);
		parametroPais.setNombreParametro("IndReproRet");				
		parametroPais.setIndicadorActivo("1");
		
		List parametros = genericoDAO.getParametrosPais(parametroPais);
		if(parametros.size()>0) {
			ParametroPais pPais = (ParametroPais)parametros.get(0);
			IndReproRet = pPais.getValorParametro();
		}
		
		if(IndReproRet.equals(Constants.NRO_UNO)){
			
			Integer cierrePeriodo = interfazSiCCService.getContCierreCampaByCodigoPeriodo(codigoPeriodoRecepcion);
	        //si es mayor a 0 es porq  ya cerro la capa�a
	        if(cierrePeriodo > 0){
	        	flag = true;
	        }else{
	        	
				procesoRETService.deleteVentaRetailDetalle(retailCabeceraMap);
				procesoRETService.deleteVentaRetailCabecera(retailCabeceraMap);
	        }
		}
		
		return flag;
	}
	/* FIN JJ PER-SiCC-2012-0348 */

	/**
	 * Si tipoFormato =1 se valida que lo q s ehay ingresado sea yyyymmdd
	 * si es fomato=2 se valida que el formato sea ddmmyyyy:hh:mi:ss
	 * @param fecha
	 * @param tipoFormato
	 * @return
	 */
	private String validarFormatoFecha(String fecha, String tipoFormato) {
		String mensaje ="";
		if(StringUtils.isNotEmpty(fecha)){
		
			try {
				convertStringToDate("ddMMyyyy",fecha);
			} catch (ParseException e) {
				//e.printStackTrace();
				mensaje = Constants.ERROR_MESSAGE;
			}
			
		}else{
			mensaje = Constants.ERROR_MESSAGE;
		}
		return mensaje;
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
	
	/**
	 * Valida que el formato d ela campanha sea la correcta yyyymm donde mm va de 1 a 18
	 * @param campanha
	 * @return
	 */
	private String validarCampanha(String campanha) {
		String mensaje ="";
		if(StringUtils.isEmpty(campanha)) return mensaje;
		if(campanha.length() != 6) return campanha;
		//
		int anho = Integer.parseInt(campanha.substring(0,4)); 
		int camp = Integer.parseInt(campanha.substring(4));
		log.debug("campanha validada " + anho + " - " + camp);
		if(anho >= 1900 && 
		   camp >= 1 && camp <=18) return mensaje;
		
		return campanha;
	}
	
	
    /**
     * Retorna la fecha en un Date si esta fecha es correcta
     * @param aMask
     * @param strDate
     * @return
     * @throws ParseException
     */
    private Date convertStringToDate(String aMask, String strDate)
      throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
            log.debug("converting xx'" + strDate + "' to date with mask '"
                     + aMask + "'");
        try {
        	df.setLenient(false); //deshabilita el modo permisivo
            date = df.parse(strDate);
            //log.debug("date " +date);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }
}
