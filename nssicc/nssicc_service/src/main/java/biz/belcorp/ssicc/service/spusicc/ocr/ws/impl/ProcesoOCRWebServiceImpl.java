/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.ocr.ws.impl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.ProcesoOCRWebService;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.beans.ProcesoOCRWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRETWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sbuchelli@belcorp.biz"> sbuchelli </a>
 */
public class ProcesoOCRWebServiceImpl extends ServletEndpointSupport implements
			ProcesoOCRWebService{

    Log log = LogFactory.getLog(ProcesoOCRWebServiceImpl.class);
    
    MantenimientoMAEClienteService mantenimientoMAEService;
    MantenimientoOCRCargaPedidoService mantenimientoOCRCargaPedidoService;
   
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	mantenimientoMAEService = (MantenimientoMAEClienteService)getWebApplicationContext().
    																 getBean("spusicc.mantenimientoMAEClienteService");

    	mantenimientoOCRCargaPedidoService = (MantenimientoOCRCargaPedidoService)getWebApplicationContext().
		 														     getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");
    	
    	
    	
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.retail.web.ws.ProcesoOCRWebService#getValidarConsultora(java.lang.String, java.lang.String)
	 */
	public ProcesoOCRWebServiceResultado getValidarConsultora(
			String codigoConsultora, String numeroDocumento)
			throws RemoteException {
		ProcesoOCRWebServiceResultado procesoOCRWebServiceResultado =new ProcesoOCRWebServiceResultado();
		String mensajeError="";
		String codigoIsoIdioma = "";
		try{
    		log.debug(" inicio getValidarConsultora ");
    		//validacion Pais
    		if(StringUtils.isEmpty(codigoConsultora)
    				&& StringUtils.isEmpty(numeroDocumento)){
    			mensajeError = getWebApplicationContext().
					getMessage("procesoOCRWebService.msg.validarConsultoraDocumento",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}
   
    		Map map = new HashMap();
    		map.put("codigoConsultora", StringUtils.isNotEmpty(codigoConsultora)?codigoConsultora.trim():null);
    		map.put("numeroDocumento", StringUtils.isNotEmpty(numeroDocumento)?numeroDocumento.trim():null);
    		
    		Map resultado= mantenimientoMAEService.getValidarOCRConsultora(map);
    		   		    
    		if(resultado !=null){
    			log.debug("resultado "+resultado);
	    		procesoOCRWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_OK);
	    		procesoOCRWebServiceResultado.setMensaje(Constants.MAE_WEBSERVICE_RESULTADO_OK);
	    		
	    		procesoOCRWebServiceResultado.setCodigoConsultora((String)resultado.get("CODIGOCONSULTORA"));
	    		procesoOCRWebServiceResultado.setDocumentoIdentidad((String)resultado.get("DOCUMENTOIDENTIDAD"));
	    		procesoOCRWebServiceResultado.setDescripcionRegion((String)resultado.get("DESCRIPCIONREGION"));
	    		procesoOCRWebServiceResultado.setCodigoRegion((String)resultado.get("CODIGOREGION"));
	    		procesoOCRWebServiceResultado.setDescripcionZona((String)resultado.get("DESCRIPCIONZONA"));
	    		procesoOCRWebServiceResultado.setCodigoZona((String)resultado.get("CODIGOZONA"));
	    		procesoOCRWebServiceResultado.setNombre1((String)resultado.get("NOMBRE1"));
	    		procesoOCRWebServiceResultado.setNombre2((String)resultado.get("NOMBRE2"));
	    		procesoOCRWebServiceResultado.setApellido1((String)resultado.get("APELLIDO1"));
	    		procesoOCRWebServiceResultado.setApellido2((String)resultado.get("APELLIDO2"));}
    		else{
    			mensajeError = getWebApplicationContext().
						getMessage("procesoOCRWebService.msg.consultora.no.existe",null,getLocaleIdioma(codigoIsoIdioma));    			
        		procesoOCRWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
        		procesoOCRWebServiceResultado.setMensaje(mensajeError);
    		}
    		
    		
    	}catch(Exception e){
    		log.debug("error en getValidarConsultora  " +e.getMessage());
    		procesoOCRWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
    		procesoOCRWebServiceResultado.setMensaje(e.getMessage());
    	
    	}
	   return procesoOCRWebServiceResultado;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.retail.web.ws.ProcesoOCRWebService#saveSolicitudesTransferidas(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ProcesoOCRWebServiceResultado saveSolicitudesTransferidas(
			String codigoConsultora, String fechaTransferencia,String codigoUsuario)
			throws RemoteException {
		ProcesoOCRWebServiceResultado procesoOCRWebServiceResultado =new ProcesoOCRWebServiceResultado();
		String mensajeError="";
		String codigoIsoIdioma = "";
		try{
    		log.debug(" inicio saveSolicitudesTransferidas ");
    		//validacion Pais
    		if(StringUtils.isEmpty(codigoConsultora)){
    			mensajeError = getWebApplicationContext().
					getMessage("procesoOCRWebService.msg.validarConsultora",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}

       		if(StringUtils.isEmpty(fechaTransferencia)){
    			mensajeError = getWebApplicationContext().
					getMessage("procesoOCRWebService.msg.validarFechaTransferencia",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}
       		String valido=validarFormatoFecha(fechaTransferencia,Constants.NUMERO_UNO);
      		if(StringUtils.isNotEmpty(valido)){
    			mensajeError = getWebApplicationContext().
					getMessage("procesoOCRWebService.msg.validarFormatoFecha",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}
       		
      		if(StringUtils.isEmpty(codigoUsuario)){
    			mensajeError = getWebApplicationContext().
					getMessage("procesoOCRWebService.msg.validarLogin",null,getLocaleIdioma(codigoIsoIdioma));
				throw  new Exception(mensajeError);				
    		}
   
    		Map map = new HashMap();
    		map.put("codigoConsultora", StringUtils.isNotEmpty(codigoConsultora)?codigoConsultora.trim():null);
    		map.put("fechaTransferencia", StringUtils.isNotEmpty(fechaTransferencia)?fechaTransferencia.trim():null);
    		map.put("login", codigoUsuario);
    		mantenimientoOCRCargaPedidoService.saveSolicitudesTransferidas(map);
    		   		    
    		procesoOCRWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_OK);
    		procesoOCRWebServiceResultado.setMensaje(Constants.MAE_WEBSERVICE_RESULTADO_OK);
    		    		    		
    		
    	}catch(Exception e){
    		log.debug("error en saveSolicitudesTransferidas  " +e.getMessage());
    		procesoOCRWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
    		procesoOCRWebServiceResultado.setMensaje(e.getMessage());
    	
    	}
	   return procesoOCRWebServiceResultado;
	}


	/**
	 * Si tipoFormato =1 se valida que lo q s ehay ingresado sea dd/MM/yyyy
	 * si es fomato=2 se valida que el formato sea ddmmyyyy:hh:mi:ss
	 * @param fecha
	 * @param tipoFormato
	 * @return
	 */
	private String validarFormatoFecha(String fecha, String tipoFormato) {
		String mensaje ="";
		if(StringUtils.isNotEmpty(fecha)){
		
			try {
				convertStringToDate("dd/MM/yyyy",fecha);
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
