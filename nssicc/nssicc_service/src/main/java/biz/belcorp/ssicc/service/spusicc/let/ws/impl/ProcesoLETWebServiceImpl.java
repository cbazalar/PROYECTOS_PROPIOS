package biz.belcorp.ssicc.service.spusicc.let.ws.impl;

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
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.let.ws.ProcesoLETWebService;
import biz.belcorp.ssicc.service.spusicc.let.ws.beans.LiderLETWebService;
import biz.belcorp.ssicc.service.spusicc.let.ws.beans.ProcesoLETWebServiceResultado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoLETWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sapaza@belcorp.biz">Sergio Apaza </a>
 */
public class ProcesoLETWebServiceImpl extends ServletEndpointSupport implements
		ProcesoLETWebService {

    Log log = LogFactory.getLog(ProcesoLETWebServiceImpl.class);
    
    private MantenimientoLETLideresService mantenimientoLETLideresService;
    
    private PaisService paisService;

    protected void onInit() throws ServiceException {
    	mantenimientoLETLideresService = (MantenimientoLETLideresService) 
    									getWebApplicationContext().getBean("spusicc.mantenimientoLETLideresService");
    	
    	paisService =(PaisService) getWebApplicationContext().getBean("paisService");
    }
    
	public ProcesoLETWebServiceResultado consultarLider(String codigoConsultora) throws RemoteException {
		ProcesoLETWebServiceResultado procesoLETWebServiceResultado = new ProcesoLETWebServiceResultado();
		
		try {
			log.debug("consultarLider - codigoConsultora : " + codigoConsultora);
			
			if (StringUtils.isBlank(codigoConsultora)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoLETWebService.msg.validarCodigoConsultora",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw  new Exception(mensajeError);				
			}

			LiderLETWebService consultoraLider = new LiderLETWebService();

			Map params = mantenimientoLETLideresService.getDatosLider(codigoConsultora);
			String codigoError = (String)params.get("codigoError");
			
			if(codigoError.equalsIgnoreCase("")) {
				consultoraLider.setCodigoLider((String)params.get("codigoLider"));
				consultoraLider.setNombreLider((String)params.get("nombreLider"));
				consultoraLider.setCodigoSeccion((String)params.get("codigoSeccion"));
				consultoraLider.setCodigoZona((String)params.get("codigoZona"));
				consultoraLider.setCodigoRegion((String)params.get("codigoRegion"));
				consultoraLider.setCodigoPais((String)params.get("codigoPais"));
				
			} else {
				if(codigoError.equals("01")) {
					String mensajeError = getWebApplicationContext().
							getMessage("procesoLETWebService.msg.validarExisteConsultora",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
						throw  new Exception(mensajeError);
				}
				if(codigoError.equals("02")) {
					String mensajeError = getWebApplicationContext().
							getMessage("procesoLETWebService.msg.validarExisteLider",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
						throw  new Exception(mensajeError);
				}	
			}
			
			log.debug("consultarLider - codigoError : " + codigoError);
			
			procesoLETWebServiceResultado.setConsultoraLider(consultoraLider);
			procesoLETWebServiceResultado.setMensaje("1");
			
		} catch (Exception e) {
			procesoLETWebServiceResultado.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_ERROR);
			procesoLETWebServiceResultado.setMensaje(e.getMessage());
			procesoLETWebServiceResultado.setConsultoraLider((null));
			
			return procesoLETWebServiceResultado;
		}
		
		procesoLETWebServiceResultado.setCodigo(Constants.EDU_WEBSERVICE_RESULTADO_OK);
		
		return procesoLETWebServiceResultado;
	}
	
	public ProcesoLETWebServiceResultado obtenerLideres(String codigoPais)
			throws RemoteException {

		ProcesoLETWebServiceResultado procesoLETWebServiceResultado = new ProcesoLETWebServiceResultado();
		
		try{
			
			if (StringUtils.isBlank(codigoPais)){
				String mensajeError = getWebApplicationContext().
					getMessage("procesoLETWebService.msg.validarCodigoPais.error.codigoPaisBlanco",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw new Exception(mensajeError);
			}
			
			Pais pais = paisService.getPais(codigoPais);
			
			List lideres = mantenimientoLETLideresService.getLideres(pais.getCodigo());
			
			if(lideres.size() > 0){
				biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService[] arrLideres = castLideres(lideres);
				procesoLETWebServiceResultado.setListaConsultoraLider(arrLideres);
				procesoLETWebServiceResultado.setCodigo(Constants.LET_WEBSERVICE_RESULTADO_OK);
				procesoLETWebServiceResultado.setMensaje(String.valueOf(arrLideres.length));
				
			}else{
				String mensajeError = getWebApplicationContext().
						getMessage("procesoLETWebService.msg.validarCodigoPais.error.codigoSinResultados",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
				throw new Exception(mensajeError);
			}
			
		}catch(ObjectRetrievalFailureException ex){
			procesoLETWebServiceResultado.setCodigo(Constants.LET_WEBSERVICE_RESULTADO_ERROR);
			procesoLETWebServiceResultado.setMensaje(getWebApplicationContext().getMessage("procesoLETWebService.msg.validarCodigoPais.error.codigoPaisInvalido",null,new Locale(Constants.EDU_IDIOMA_DEFAULT_ES)));
			procesoLETWebServiceResultado.setListaConsultoraLider(null);
		}
		
		catch(Exception e){
			procesoLETWebServiceResultado.setCodigo(Constants.LET_WEBSERVICE_RESULTADO_ERROR);
			procesoLETWebServiceResultado.setMensaje(e.getMessage());
			procesoLETWebServiceResultado.setListaConsultoraLider(null);
		}
		
		return procesoLETWebServiceResultado;
	}
	
	private biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService[] castLideres(List lideres)
	{
		biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService[] listLideres;
		Map liderMap;
		listLideres = new biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService[lideres.size()];
		Iterator it = lideres.iterator();
		int i=0;
		
		try
		{
			while(it.hasNext()){
				liderMap = (HashMap)it.next();
				biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService lider = new biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService();
				BeanUtils.copyProperties(lider, liderMap);
				listLideres[i++] = lider;
			}
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage());
		}
		
		return listLideres;	
	}
	
}
