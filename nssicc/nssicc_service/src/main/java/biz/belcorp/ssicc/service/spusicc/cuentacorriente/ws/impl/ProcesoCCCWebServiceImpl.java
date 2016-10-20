package biz.belcorp.ssicc.service.spusicc.cuentacorriente.ws.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionPagosPorRegularizarService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ws.ProcesoCCCWebService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ws.beans.ProcesoCCCWebServiceResultado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCCCWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cbazalar@belcorp.biz">Carlos Bazalar/a>
 */
public class ProcesoCCCWebServiceImpl extends ServletEndpointSupport implements ProcesoCCCWebService {

    Log log = LogFactory.getLog(ProcesoCCCWebServiceImpl.class);
    private ProcesoCCCDepuracionPagosPorRegularizarService procesoCCCDepuracionPagosPorRegularizarService;
    

    /* (non-Javadoc)
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	procesoCCCDepuracionPagosPorRegularizarService = (ProcesoCCCDepuracionPagosPorRegularizarService) getWebApplicationContext().getBean("spusicc.procesoCCCDepuracionPagosPorRegularizarService");
    }
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.cuentacorriente.web.ws.ProcesoCCCWebService#insertarPago(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public ProcesoCCCWebServiceResultado insertarPago(
    		String codigoBanco, 
    		String numeroOperacion, 
    		String consultora, 
    		String fechaPago,
    		String montoPago) throws RemoteException {
		ProcesoCCCWebServiceResultado procesoCCCWebServiceResultado = new ProcesoCCCWebServiceResultado();
		String indicadorEjecucion = new String("1");
		try {
			if (StringUtils.isBlank(codigoBanco)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarCodigoBanco",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			if (StringUtils.isBlank(numeroOperacion)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarNumeroOperacion",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			if (StringUtils.isBlank(consultora)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarConsultora",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			if (StringUtils.isBlank(fechaPago)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarFechaPago",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			if (StringUtils.isBlank(montoPago)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarMontoPago",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			
			//Validamos la Fecha de Pago
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(StringUtils.isNotEmpty(fechaPago)) {
				try {
					sdf.parse(fechaPago);
				}catch(Exception ex) {
					String mensajeError = getWebApplicationContext().
							getMessage("procesoCCCWebService.msg.validarFechaPagoInvalido",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
					throw  new Exception(mensajeError);	
				}
			}	
			
			//Validamos Monto de Pago
			try {
				BigDecimal monto = new BigDecimal(montoPago);
			}
			catch(Exception e) {
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarMontoPagoInvalido",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			
			Map criteria = new HashMap();
			criteria.put("codigoBanco", codigoBanco);
			criteria.put("numeroOperacion", numeroOperacion);
			criteria.put("consultora", consultora);
			criteria.put("fechaPago", fechaPago);
			criteria.put("montoPago", montoPago);
			criteria.put("indicadorEjecucion", indicadorEjecucion);
			
			this.procesoCCCDepuracionPagosPorRegularizarService.executeInsertarPago(criteria);
			indicadorEjecucion = (String) criteria.get("indicadorEjecucion");
			procesoCCCWebServiceResultado.setCodigoMensaje(indicadorEjecucion);
			
		} catch (Exception e) {
			procesoCCCWebServiceResultado.setCodigoMensaje("0");
			procesoCCCWebServiceResultado.setMensaje(e.getMessage());
		}
		
		return procesoCCCWebServiceResultado;
	}
	
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.cuentacorriente.web.ws.ProcesoCCCWebService#reversarPago(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public ProcesoCCCWebServiceResultado reversarPago(
    		String codigoBanco, 
    		String numeroOperacion, 
    		String consultora, 
    		String fechaPago) throws RemoteException {
		ProcesoCCCWebServiceResultado procesoCCCWebServiceResultado = new ProcesoCCCWebServiceResultado();
		String indicadorEjecucion = new String("1");
		try {
			if (StringUtils.isBlank(codigoBanco)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarCodigoBanco",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			if (StringUtils.isBlank(numeroOperacion)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarNumeroOperacion",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			if (StringUtils.isBlank(consultora)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarConsultora",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			if (StringUtils.isBlank(fechaPago)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarFechaPago",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}
			
			//Validamos la Fecha de Pago
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(StringUtils.isNotEmpty(fechaPago)) {
				try {
					sdf.parse(fechaPago);
				}catch(Exception ex) {
					String mensajeError = getWebApplicationContext().
							getMessage("procesoCCCWebService.msg.validarFechaPagoInvalido",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
					throw  new Exception(mensajeError);	
				}
			}	
			
			Map criteria = new HashMap();
			criteria.put("codigoBanco", codigoBanco);
			criteria.put("numeroOperacion", numeroOperacion);
			criteria.put("consultora", consultora);
			criteria.put("fechaPago", fechaPago);
			criteria.put("indicadorEjecucion", indicadorEjecucion);
			
			this.procesoCCCDepuracionPagosPorRegularizarService.executeRevertirPago(criteria);
			indicadorEjecucion = (String) criteria.get("indicadorEjecucion");
			procesoCCCWebServiceResultado.setCodigoMensaje(indicadorEjecucion);
			
		} catch (Exception e) {
			procesoCCCWebServiceResultado.setCodigoMensaje("0");
			procesoCCCWebServiceResultado.setMensaje(e.getMessage());
		}
		
		return procesoCCCWebServiceResultado;
	}
    
   
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.cuentacorriente.web.ws.ProcesoCCCWebService#consultarSaldo(java.lang.String)
     */
    public ProcesoCCCWebServiceResultado consultarSaldo(String consultora) throws RemoteException {
		ProcesoCCCWebServiceResultado procesoCCCWebServiceResultado = new ProcesoCCCWebServiceResultado();
		String indicadorEjecucion = new String("1");
		try {
			if (StringUtils.isBlank(consultora)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoCCCWebService.msg.validarConsultora",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}

			Map criteria = new HashMap();
			criteria.put("consultora", consultora);
			criteria.put("indicadorEjecucion", indicadorEjecucion);
			
			this.procesoCCCDepuracionPagosPorRegularizarService.executeConsultarPago(criteria);
			indicadorEjecucion = (String) criteria.get("indicadorEjecucion");
			procesoCCCWebServiceResultado.setCodigoMensaje(indicadorEjecucion);
			
		} catch (Exception e) {
			procesoCCCWebServiceResultado.setCodigoMensaje("0");
			procesoCCCWebServiceResultado.setMensaje(e.getMessage());
		}
		
		return procesoCCCWebServiceResultado;
	}
	
}
