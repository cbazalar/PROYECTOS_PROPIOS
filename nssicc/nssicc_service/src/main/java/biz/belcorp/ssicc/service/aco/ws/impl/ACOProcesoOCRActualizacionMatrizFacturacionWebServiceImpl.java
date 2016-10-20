/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoOCRActualizacionMatrizFacturacionWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRActualizacionMatrizFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoOCRActualizacionMatrizFacturacionWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoOCRActualizacionMatrizFacturacionWebService {

	InterfazSiCCService interfazSiCCService;
	ProcesoOCRActualizacionMatrizFacturacionService procesoOCRActualizacionMatrizFacturacionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.procesoOCRActualizacionMatrizFacturacionService = (ProcesoOCRActualizacionMatrizFacturacionService)getWebApplicationContext().getBean("spusicc.procesoOCRActualizacionMatrizFacturacionService");
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoOCRActualizacionMatrizFacturacionWebService#ejecutarProcesoOCRActualizacionMatrizFacturacion(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoOCRActualizacionMatrizFacturacion(String codigoPais, String codigoMarca, String codigoCanal, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoOCRActualizacionMatrizFacturacion");
		}
		
		boolean estado = false;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.OCR_CODIGO_PROCESO_BATCH_ACTUALIZAR_MATRIZ_FACTURACION;
		String CODIGO_SISTEMA = Constants.SISTEMA_OCR;

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
					    
		    List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
		    
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoMarca) || StringUtils.isEmpty(codigoMarca)){
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoMarca);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);
				if(siccMarcaList.size()!=0){
					if(!CollectionUtils.exists(siccMarcaList, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de marca vacio.");
				}
			}
			
			/**
			 * Si canal es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoCanal) || StringUtils.isEmpty(codigoCanal)){
				codigoCanal = Constants.CODIGO_CANAL_DEFAULT;				
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoCanal);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);
				if(siccCanalList.size()!=0){
					if(!CollectionUtils.exists(siccCanalList, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCanal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}
		    
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoProcesoBatch", CODIGO_BATCH);
			params.put("codigoSistema", CODIGO_SISTEMA);			
			params.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			params.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			params.put("codigoCanal", codigoCanal);
			params.put("codigoMarca", codigoMarca);			
			params.put("codigoUsuario", codigoUsuario);
			
			this.executeProceso(params);
		    objetoRespuesta.setEjecucionExitosa(true);

		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);			
			objetoRespuesta.setEjecucionExitosa(estado);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	

		return objetoRespuesta;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {
		
		this.procesoOCRActualizacionMatrizFacturacionService.executeOCRActualizacionMatrizFacturacion(params);
		
		return params;
	}



}
