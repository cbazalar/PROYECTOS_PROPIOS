package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoEDUGenerarPlanillaProgramacionWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUGenerarPlanillaProgramacionService;

public class ACOProcesoEDUGenerarPlanillaProgramacionWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoEDUGenerarPlanillaProgramacionWebService {

	MantenimientoEDUGenericoService mantenimientoEDUGenericoService;
	AjaxService ajaxService;
	ProcesoEDUGenerarPlanillaProgramacionService procesoEDUGenerarPlanillaProgramacionService;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoEDUGenerarPlanillaProgramacionWebService#ejecutarProcesoEDUGenerarPlanillaProgramacion(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getWebApplicationContext().getBean("edu.mantenimientoEDUGenericoService");
		ajaxService = (AjaxService)getWebApplicationContext().getBean("ajaxService");
		procesoEDUGenerarPlanillaProgramacionService = (ProcesoEDUGenerarPlanillaProgramacionService)getWebApplicationContext().getBean("edu.procesoEDUGenerarPlanillaProgramacionService");
	}

	public ACOWebServiceResponse ejecutarProcesoEDUGenerarPlanillaProgramacion(
			String codigoPais,
			String codigoUsuario,
			String codigoPeriodo,				 
			String codigoEmpresa, 
			String []regiones, 
			String tipoProceso) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.EDU_CODIGO_PROCESO_BATCH_GENERAR_PLANILLA_PROGRAMACION;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_EDU;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoEDUGenerarPlanillaProgramacion");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
						
	        /**
			 * Validar codigoEmpresa
			 */
			 
			EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
			parametroEmpresa.setCodigoPais(pais.getCodigo());
			parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
			
			List empresas = mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(parametroEmpresa);
			
			if(StringUtils.isBlank(codigoEmpresa) || StringUtils.isEmpty(codigoEmpresa)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoEmpresa", null,
						getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoEmpresa);
				BeanPredicate beanPredicate = new BeanPredicate("codigoEmpresa", nameEqlPredicate);					
				if(empresas.size()!=0){
					if(!CollectionUtils.exists(empresas, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoEmpresa",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo de empresa vacio.");
				}
			}
			
			/**
			 * Validar regiones 
			 */			
			if(regiones == null || regiones.length == 0){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarRegiones",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
			}else{
				LabelValue[] regionesPaisEmpresa = ajaxService.getRegionesEDUByPaisEmpresa(codigoPais, codigoEmpresa);				
				LabelValue labelValue = new LabelValue("Todos", "T");
				List regionesPaisEmpresaList = new ArrayList(); 
				regionesPaisEmpresaList.add(labelValue);
				regionesPaisEmpresaList.addAll(Arrays.asList(regionesPaisEmpresa));
				for(int i=0; i<regiones.length; i++){
					//if(!StringUtils.equalsIgnoreCase("T", regiones[i]))	{
						EqualPredicate nameEqlPredicate = new EqualPredicate(regiones[i]);
						BeanPredicate beanPredicate = new BeanPredicate("value", nameEqlPredicate);					
						if(regionesPaisEmpresaList.size() != 0){
							if(!CollectionUtils.exists(regionesPaisEmpresaList, beanPredicate)){
								mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoRegionEnEmpresa",new String[]{regiones[i], codigoEmpresa},getLocaleIdioma(pais.getCodigoIdiomaIso()));
								throw new Exception(mensajeError);
							}
						}else{
							log.debug("Listado de codigo de region vacio.");
						}
					//}
				}				
			}
			
		    /**
		     * Valida tipoProceso
		     */
		    if(StringUtils.isBlank(tipoProceso)|| StringUtils.isEmpty(tipoProceso)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarTipoProceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
		    else
		    {
		    	String []tiposProceso = new String[]{Constants.EDU_TIPO_PROCESO_NORMAL, Constants.EDU_TIPO_PROCESO_REPROCESO, Constants.EDU_TIPO_PROCESO_REENVIO};
		    	
		    	if(!ArrayUtils.contains(tiposProceso, tipoProceso))
		    	{
			    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoProceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		        	throw new Exception(mensajeError);
		    	}
		    }

		    /**
		     * Valida codigoPeriodo
		     */
		    if(StringUtils.isBlank(codigoPeriodo)|| StringUtils.isEmpty(codigoPeriodo)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }

		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

		    // Parametros
			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(codigoPais);
			parametro.setCodigoEmpresa(codigoEmpresa);
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			
			parametro =	mantenimientoEDUGenericoService.getParametroCurso(parametro);
			
			Map criteria = new HashMap();
	        criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("regionList", Arrays.asList(regiones));
			criteria.put("tipoProceso", tipoProceso);
		    criteria.put("codigoPeriodo", codigoPeriodo);
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
		    criteria.put("usuarioLogin",usuario.getLogin());
		    
		    criteria.put("nivelUnidadAdm", parametro.getNivelUnidadAdm());
		    criteria.put("indicadorEnvioProgramadas", parametro.getIndicadorEnvioProgramadas());
		    criteria.put("indicadorEnvioResumen", parametro.getIndicadorEnvioResumen());
		    criteria.put("indicadorConsultoraRezagada", parametro.getIndicadorConsultoraRezagada());
		    
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoProceso", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        	        	        	        
    		this.executeProceso(criteria);
    		
			mensajeError = MapUtils.getString(criteria, "mensajeError");
    		
			if(StringUtils.isNotBlank(mensajeError))
				throw new Exception(mensajeError);
			
			estado = true;
			objetoRespuesta.setMensaje("Se ejecuto el servicio con exito.");
			objetoRespuesta.setEjecucionExitosa(estado);			
			
	     
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

        // Invocando al Proceso de Actualizaci�n de Planillas Programaci�n  
		//Esto simrpe y cuando el parametro de Niv Administartivo este activado
		
		String nivelUnidadAdm = MapUtils.getString(params, "nivelUnidadAdm");
		
		if(!StringUtils.equals(nivelUnidadAdm, Constants.EDU_PARAM_CURSO_NO_GENERA_PLANILLA))
		{
			procesoEDUGenerarPlanillaProgramacionService.executeGenerarPlanillaProgramacion(params);
		}
		
		return params;
	}

}
