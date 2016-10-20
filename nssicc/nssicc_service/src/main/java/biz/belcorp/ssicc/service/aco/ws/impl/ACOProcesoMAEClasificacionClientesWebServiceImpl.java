package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoMAEClasificacionClientesWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.ProcesoMAEClasificacionClientesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

public class ACOProcesoMAEClasificacionClientesWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoMAEClasificacionClientesWebService {

	ProcesoMAEClasificacionClientesService procesoMAEClasificacionClientesService;
	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.procesoMAEClasificacionClientesService = (ProcesoMAEClasificacionClientesService) getWebApplicationContext().getBean("spusicc.procesoMAEClasificacionClientesService");
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	}
	
	public ACOWebServiceResponse ejecutarProcesoMAEUpdateClasificacionCliente(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String codigoTipoCliente,			
			String nroCampaniasEvaluar) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		
		String mensajeError = "";
		String CODIGO_BATCH = "08";
		String CODIGO_SISTEMA = "MAE";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutarProcesoMAEEjecutarProcesos");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);			
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
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
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccMarcaList, codigoMarca)){
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
				codigoCanal = Constants.CODIGO_MARCA_DEFAULT;				
			}else{
				if(siccCanalList.size()!=0){
					if(!existeCodigoEnLista(siccCanalList, codigoCanal)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCanal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("codigoMarca", codigoMarca);
	        criteria.put("codigoCanal", codigoCanal);
	        List periodos = interfazSiCCService.getPeriodosDefaultByPMC(criteria);
	        if (StringUtils.isBlank(codigoPeriodo)) {
	        	if(periodos != null && periodos.size() > 0) {
	                Base base = (Base)periodos.get(0);
	                codigoPeriodo = base.getCodigo();
	            }
			}

	        //Usuario user = this.usuarioService.getUsuarioByUsername(codigoUsuario);
	        Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
	        
	        
	        List tipoCliente = interfazSiCCService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
	        
	        /**
			 * Si tipo cliente es vacio o en blanco, traer el de por defecto
			 */	        
	        if(StringUtils.isBlank(codigoTipoCliente) || StringUtils.isEmpty(codigoTipoCliente)){
	        	if(tipoCliente.size()!=0){
					if(!existeCodigoEnLista(tipoCliente, codigoTipoCliente)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoCliente",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de tipo cliente vacio.");
				}
	        }
	        
	        /**
			 * Si nrocampania es vacio o en blanco, traer el de por defecto
			 */
	        if(StringUtils.isBlank(nroCampaniasEvaluar) || StringUtils.isEmpty(nroCampaniasEvaluar)){
	        	nroCampaniasEvaluar = "4";
	        }
	        
	        criteria.put("codigoPeriodo", codigoPeriodo);
	        criteria.put("codigoTipoCliente", codigoTipoCliente);
	        criteria.put("nroCampaniasEvaluar", nroCampaniasEvaluar);
	        criteria.put("codigoUsuario", codigoUsuario);
	        criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
		    // Invocando a la calificaci�n y Envio de Aptas automatica
		    this.executeProceso(criteria);
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

		
	protected Map executeProcess(Map params) throws Exception {
		procesoMAEClasificacionClientesService.executeProcesarClasificacion(params);
		return params;
	}
}
