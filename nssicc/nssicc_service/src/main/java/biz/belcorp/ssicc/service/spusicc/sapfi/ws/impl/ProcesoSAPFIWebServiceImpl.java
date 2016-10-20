/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.sapfi.ws.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.sapfi.ws.ProcesoSAPFIWebService;
import biz.belcorp.ssicc.service.spusicc.sapfi.ws.beans.ResultadoSAPFIWebService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoSAPFIWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class ProcesoSAPFIWebServiceImpl extends ServletEndpointSupport implements ProcesoSAPFIWebService{

    Log log = LogFactory.getLog(ProcesoSAPFIWebServiceImpl.class);
    
    MantenimientoMAEClienteService clienteService;
   	
  
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	this.clienteService = (MantenimientoMAEClienteService)getWebApplicationContext().getBean("spusicc.mantenimientoMAEClienteService");
    	
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
	 * @see biz.belcorp.ssicc.spusicc.sapfi.web.ws.ProcesoSAPFIWebService#contabilizarPago(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultadoSAPFIWebService contabilizarPago (
			 String codigoPais,
			 String nroSecuencial,
			 String codigoProveedor,
			 String montoNeto,
			 String estadoSAP
			) {
		ResultadoSAPFIWebService resultadoSAFIWebService = new ResultadoSAPFIWebService();
    	try{
			log.debug("inicio contabilizarPago");
			String mensajeError = "";
			
			//Validaciones
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.codigoPais", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(nroSecuencial)) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.nroSecuencial", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(codigoProveedor)) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.codigoProveedor", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}			
			if (StringUtils.isBlank(estadoSAP)) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.estSAP", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			if(StringUtils.equals(estadoSAP, "1")){
				if (StringUtils.isBlank(montoNeto)) {
					mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.montoNeto", null, getLocaleIdioma("es"));
					throw new Exception(mensajeError);
				}
			}
			
			BigDecimal dmontoNeto = new BigDecimal(0);
			try {
				if(StringUtils.equals(estadoSAP, "1"))
					dmontoNeto = new BigDecimal(montoNeto);
			}
			catch(Exception e) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.montoNetoValorNoNumerico", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			String indprocesoPago="";
			if(StringUtils.equalsIgnoreCase(estadoSAP.trim(), Constants.NUMERO_UNO))
				indprocesoPago="3";
			else
				indprocesoPago="4";
				
			/* LLenando Valores */
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("nroSecuencial", nroSecuencial);
			criteria.put("codigoProveedor", codigoProveedor);
			criteria.put("montoNeto", dmontoNeto);	
			criteria.put("indProcesoPago", indprocesoPago);	
			
			Integer verificar = this.clienteService.getExistePagoLider(criteria);
			if (verificar == null || verificar.intValue() <= 0) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.pagoLiderNoExiste", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			//Insertando Pago Lider
			if(StringUtils.equals(estadoSAP, "1"))
				this.clienteService.updatePagoLiderContabilizarPago(criteria);
			else
				this.clienteService.updatePagoLiderContabilizarPagoSinMonto(criteria);
			
			
			resultadoSAFIWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_OK);
			resultadoSAFIWebService.setMensaje("");
			resultadoSAFIWebService.setCodLider(codigoProveedor);
				
		}catch(Exception e){
			log.error("error en contabilizarPago: " +e.getMessage());
			resultadoSAFIWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
			resultadoSAFIWebService.setMensaje(e.getMessage());
		}

		return resultadoSAFIWebService;
		
		
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sapfi.web.ws.ProcesoSAPFIWebService#registrarLider(java.lang.String, java.lang.String, java.lang.String)
     */
    public ResultadoSAPFIWebService registrarLider(
		    String codigoPais,
		    String nroDocumento,
		    String codigoProveedor,
		    String estadoSap
     		) {
    	
    	ResultadoSAPFIWebService resultadoSAFIWebService = new ResultadoSAPFIWebService();
    	try{
			log.debug("inicio registrarLider");
			String mensajeError = "";
			
			//Validaciones
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.codigoPais", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(nroDocumento)) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.nroDocumento", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			if(StringUtils.equals(estadoSap, "1")){
				if (StringUtils.isBlank(codigoProveedor)) {
					mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.codigoProveedor", null, getLocaleIdioma("es"));
					throw new Exception(mensajeError);
				}
			}
			
			Map map = new HashMap();
			map.put("codigoPais", codigoPais);
			String oidPais = clienteService.getOidPais(map);
			
				
			/* LLenando Valores */
			Map criteria = new HashMap();
			String tipoDoc = "01";
			criteria.put("codigoPais", codigoPais);
			criteria.put("oidPais", oidPais);
			criteria.put("tipoDocumento", tipoDoc);
			criteria.put("numeroDocumento", nroDocumento);		
			
			
			List lista = this.clienteService.getObtenerBuscarConsultora(criteria);
			
			if (lista == null || lista.size() == 0 ) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.consultoraNoExiste", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			if (lista.size() > 1 ) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.existeMasDeUnaConsultora", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			Map criteriaCliente = (Map) lista.get(0);
			String codigoLider = (String) criteriaCliente.get("codConsultora");
			Integer verificar = this.clienteService.getExisteLider(criteriaCliente);
			if (verificar.intValue() > 0) {
				mensajeError = getWebApplicationContext().getMessage("procesoSAPFIWebService.msg.existeLider", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			//Insertando Lider
			criteriaCliente.put("codigoPais", codigoPais);
			criteriaCliente.put("nroDocumento", nroDocumento);	
			criteriaCliente.put("codigoProveedor", codigoProveedor);
			criteriaCliente.put("codigoUsuario", "WS_ADMIN");
			criteriaCliente.put("codigoCliente", codigoLider);
			
			if(StringUtils.equals(estadoSap, Constants.NUMERO_UNO)){				
				this.clienteService.updateClienteLider(criteriaCliente);
				this.clienteService.insertLider(criteriaCliente);
			}
			
			resultadoSAFIWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_OK);
			resultadoSAFIWebService.setMensaje("");
			resultadoSAFIWebService.setCodLider(codigoLider);
			
			if(StringUtils.equals(estadoSap, Constants.NUMERO_CERO)){
				resultadoSAFIWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
				resultadoSAFIWebService.setMensaje("Creación de código SAP rechazada para la consulttora con código "+codigoLider);
			}
				
		}catch(Exception e){
			log.error("error en registrarLider: " +e.getMessage());
			resultadoSAFIWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
			resultadoSAFIWebService.setMensaje(e.getMessage());
		}

		return resultadoSAFIWebService;
	}


	

}
