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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.ws.ConsultaPERControlAsistenciaWebService;
import biz.belcorp.ssicc.service.scsicc.ws.beans.ConsultaPERWebServiceResultado;
import biz.belcorp.ssicc.service.scsicc.ws.beans.ControlPERAsistenciaWebService;
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
public class ConsultaPERControlAsistenciaWebServiceImpl extends ServletEndpointSupport implements
		ConsultaPERControlAsistenciaWebService	 {

    private static final String PER_SELECCION_TODAS = "T";
	private static final String PER_SELECCION_ACTIVA = "A";
	private static final String LOCALE_ES = "es";
	Log log = LogFactory.getLog(ConsultaPERControlAsistenciaWebServiceImpl.class);
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


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.ws.ConsultaPERControlAsistenciaWebService#getControlAsistencia(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultaPERWebServiceResultado getControlAsistencia(
			String codigoPais, String codigoMarca, String codigoCanal,
			String codigoPeriodo, String codigoRegion, String codigoZona,
			String seleccion) throws RemoteException {
		Map params = new HashMap();
		ConsultaPERWebServiceResultado consultaPERWebServiceResultado = new ConsultaPERWebServiceResultado();		
		try{	
			seleccion = seleccion.toUpperCase();
			if (StringUtils.isBlank(codigoPais)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoEDUWebService.msg.validarPais",null,getLocaleIdioma(LOCALE_ES));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(codigoMarca)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaPERControlAsistenciaWebService.msg.validarCodigoMarca",null,getLocaleIdioma(LOCALE_ES));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(codigoCanal)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaPERControlAsistenciaWebService.msg.validarCodigoCanal",null,getLocaleIdioma(LOCALE_ES));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(codigoPeriodo)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaPERControlAsistenciaWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(LOCALE_ES));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(codigoRegion)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaPERControlAsistenciaWebService.msg.validarCodigoRegion",null,getLocaleIdioma(LOCALE_ES));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(codigoZona)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaPERControlAsistenciaWebService.msg.validarCodigoZona",null,getLocaleIdioma(LOCALE_ES));
				throw  new Exception(mensajeError);				
			}
			
			if (StringUtils.isBlank(seleccion)){
				String mensajeError = getWebApplicationContext().
					getMessage("consultaPERControlAsistenciaWebService.msg.validarCodigoSeleccion",null,getLocaleIdioma(LOCALE_ES));
				throw  new Exception(mensajeError);				
			}else{
				if(!PER_SELECCION_ACTIVA.equals(seleccion) && !PER_SELECCION_TODAS.equals(seleccion)){
					String mensajeError = getWebApplicationContext().
							getMessage("consultaPERControlAsistenciaWebService.msg.validarCodigoSeleccion",null,getLocaleIdioma(LOCALE_ES));
						throw  new Exception(mensajeError);
				}
			}
			/*PARMAS*/
			params.put("codigoPais", codigoPais);
			params.put("codigoMarca", codigoMarca);
			params.put("codigoCanal", codigoCanal);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("codigoRegion", codigoRegion);
			params.put("codigoZona", codigoZona);
			params.put("seleccion", seleccion);
			reporteService.executeControlAsistencia(params);
			log.debug("oidProceso "+params.get("oidProceso"));
			List list = reporteService.getControlAsistencia(params);
			log.debug("retorna list "+list.size());
			consultaPERWebServiceResultado.setListControlAsistenciaWebService(getControlAsistencia(list));
		}catch(Exception e){
			//e.printStackTrace();
			consultaPERWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_ERROR);
			consultaPERWebServiceResultado.setMensaje(e.getMessage());
			consultaPERWebServiceResultado.setListControlAsistenciaWebService(null);
			return consultaPERWebServiceResultado;
		}
		
		consultaPERWebServiceResultado.setCodigo(Constants.OCR_WEBSERVICE_RESULTADO_OK);
		consultaPERWebServiceResultado.setMensaje("");
		return consultaPERWebServiceResultado;
	}


	/**
	 * @param list
	 * @return
	 */
	private ControlPERAsistenciaWebService[] getControlAsistencia(
			List list) {
		ControlPERAsistenciaWebService [] arrControl = new ControlPERAsistenciaWebService[list.size()];
		Iterator it = list.iterator();
		int i=0;
		while(it.hasNext()){
			Map map = (Map)it.next();
			ControlPERAsistenciaWebService bean = new ControlPERAsistenciaWebService();
			bean.setCodigoCliente((String)map.get("codigoCliente"));
			bean.setCodigoSeccion((String)map.get("codigoSeccion"));
			bean.setCodigoTerritorio((String)map.get("codigoTerritorio"));
			bean.setCodigoZona((String)map.get("codigoZona"));
			bean.setFechaFacturacion((String)map.get("fechaFacturacion"));
			bean.setNombreCliente((String)map.get("nombreCliente"));
			bean.setNomGere((String)map.get("nomGere"));
			bean.setSaldo((String)map.get("saldo"));
			bean.setTelefonoMovil((String)map.get("telefonoMovil"));
			bean.setValFamiliaProtegida((String)map.get("valFamiliaProtegida"));
			bean.setValObservacion((String)map.get("valObservacion"));

			arrControl[i] = bean;
			i++;
		}
		
		return arrControl;
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
