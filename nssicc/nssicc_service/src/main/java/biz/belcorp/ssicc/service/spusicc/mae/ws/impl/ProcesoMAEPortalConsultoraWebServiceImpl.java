/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws.impl;

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
import org.springframework.context.MessageSource;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.ProcesoMAEPortalConsultoraWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ConsultoraMAEWebService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoMAEWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sbuchelli@belcorp.biz"> sbuchelli </a>
 */
public class ProcesoMAEPortalConsultoraWebServiceImpl extends ServletEndpointSupport implements
	ProcesoMAEPortalConsultoraWebService{

    Log log = LogFactory.getLog(ProcesoMAEPortalConsultoraWebServiceImpl.class);
    
    MantenimientoMAEClienteService clienteService;
    ReporteService reporteService;
    MessageSource messageSource;

    /**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @param clienteService the clienteService to set
	 */
	public void setClienteService(MantenimientoMAEClienteService clienteService) {
		this.clienteService = clienteService;
	}

	/**
	 * @param reporteService the reporteService to set
	 */
	public void setReporteService(ReporteService reporteService) {
		this.reporteService = reporteService;
	}

	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	clienteService = (MantenimientoMAEClienteService)getWebApplicationContext().getBean("spusicc.mantenimientoMAEClienteService");
    	reporteService = (ReporteService)getWebApplicationContext().getBean("scsicc.reporteService");
    	
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
	 * valida que el dato ingresado sea numercio
	 * @param val
	 * @return
	 */
	private String validarDatoNumerico(String val) {
		String mensaje ="";
		if(StringUtils.isNotEmpty(val)){
			try{
			   new Double(val);
			}catch(Exception e){
			  log.debug("error en valor numerico ");
			  mensaje=e.getMessage();
			}			
		}
		return mensaje;
	}


	/**
	 * Si tipoFormato =1 se valida que lo q s ehay ingresado sea ddmmyyyy
	 * si es fomato=2 se valida que el formato sea ddmmyyyy:hh:mi:ss
	 * @param fecha
	 * @param tipoFormato
	 * @return
	 */
	private String validarFormatoFecha(String fecha, String tipoFormato) {
		String mensaje ="";
		if(StringUtils.isNotEmpty(fecha)){
		
			if(tipoFormato.equals(Constants.NUMERO_UNO)){
				if(fecha.length() != 8){
					mensaje= String.valueOf(fecha.length());
				}
			}else{//formato 2
				String separator=":";
				String [] str =StringUtils.split(fecha, separator);
				if(str.length == 4){
					String fechaAux = str[0];
					String hora = str[1];
					String minuto = str[2];
					String segundo = str[3];
					if(fechaAux.length() != 8 ||
					 	hora.length() != 2	||
					 	minuto.length() != 2 ||
					 	segundo.length() != 2){
						
						mensaje=String.valueOf(fecha.length());
					}
					
				}else{
					mensaje=String.valueOf(fecha.length());
				}
			}
		}
		return mensaje;
	}


	/**
	 * Valida que el codigo de cliente exista
	 * @param codigoCliente
	 * @param codigoIsoIdioma
	 * @return
	 */
	private String validarCliente(String codigoCliente,String codigoIsoIdioma) {				 
		   String mensajeError="";	
		   log.debug("validando cliente");
		   String oid=getObtenerOidCliente(codigoCliente);
		   if(StringUtils.isEmpty(oid))
		     mensajeError =this.messageSource.
		     		getMessage("procesoMAEWebService.msg.validarCodigoCliente",null,getLocaleIdioma(codigoIsoIdioma));				
		return mensajeError;
	}
	
	private String getObtenerOidCliente(String codigoCliente){
		Map criteria = new HashMap();
		String oidConsultora="";
		criteria.put("codigoCliente", codigoCliente);
		try{
		    oidConsultora = reporteService.getOidString("getOidClienteByCodigoCliente",
				 									criteria);
		}catch(Exception e){
			e.printStackTrace();
			log.debug("client no exist ");
			oidConsultora="";
		}		
		return oidConsultora;		
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
            log.debug("converting '" + strDate + "' to date with mask '"
                     + aMask + "'");
        try {
        	df.setLenient(false); //deshabilita el modo permisivo
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.web.ws.ProcesoMAEPortalConsultoraWebService#actualizacionDatos(java.lang.String, biz.belcorp.ssicc.spusicc.mae.web.ws.beans.ConsultoraMAEWebService)
	 */
	public String actualizacionDatos(String codigoPais,
			ConsultoraMAEWebService consultora) throws RemoteException {
		String retorno = Constants.NRO_UNO;
		Map map= new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("codigoConsultora", consultora.getCodigoConsultora());
		map.put("email", consultora.getEmail());
		map.put("telefonoFijo", consultora.getTelefonoFijo());
		map.put("telefonoCelular", consultora.getTelefonoCelular());
		
		clienteService.saveActualizacionDatosPortal(map);
		
		String mensajeError =  (String)map.get("mensajeError");
		if(StringUtils.isNotEmpty(mensajeError)) retorno = Constants.NUMERO_CERO;
		log.debug("actualizacionDatos "+retorno);
		return retorno;
	}
    
}
