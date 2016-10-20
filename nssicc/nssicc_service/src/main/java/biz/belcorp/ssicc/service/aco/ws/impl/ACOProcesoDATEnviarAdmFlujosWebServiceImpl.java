package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoDATEnviarAdmFlujosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazDATService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoDATEnviarAdmFlujosWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoDATEnviarAdmFlujosWebService {

	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	MantenimientoFACGenericoService mantenimientoFACGenericoService;
	InterfazDATService interfazDATService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.mantenimientoFACGenericoService = (MantenimientoFACGenericoService)getWebApplicationContext().getBean("spusicc.mantenimientoFACGenericoService");
		this.interfazDATService = (InterfazDATService)getWebApplicationContext().getBean("sisicc.interfazDATService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoDATEnviarAdmFlujos(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoAcceso,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = "04";
		final String CODIGO_INTERFAZ = "DAT-P4";
		final String CODIGO_SISTEMA = "DAT";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoDATEnviarAdmFlujos");
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
			
			String conexionExterna = pais.getCodigoConexionExterna();
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.DAT_PARAM_STA_CAMP_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.DAT_PARAM_IND_CAMP_ACT_UNO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	    	criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("codigoConexionExterna",conexionExterna);
			if (StringUtils.equals(conexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
				List siccMarcaList = this.interfazSiCCService.getMarcas();
				List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
				List siccAccesoList = this.interfazSiCCService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());				
				
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
					codigoCanal = Constants.CODIGO_CANAL_DEFAULT;				
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
				
				/**
				 * Si acceso es vacio o en blanco, traer el de por defecto
				 */
				if(StringUtils.isBlank(codigoAcceso) || StringUtils.isEmpty(codigoAcceso)){
					codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;				
				}else{
					EqualPredicate nameEqlPredicate = new EqualPredicate(codigoAcceso);
					BeanPredicate beanPredicate = new BeanPredicate("codigoAcceso", nameEqlPredicate);					
					if(siccAccesoList.size()!=0){
						if(!CollectionUtils.exists(siccAccesoList,beanPredicate)){
							mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteAcceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
							throw new Exception(mensajeError);
						}
					}else{
						log.debug("Listado de acceso vacio.");
					}
				}
				
				PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
				if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
		        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
				}
				if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
		        	fechaFacturacion = controlFacturacion.getFechaProceso();
		        }
				
				criteria.put("tipoCierre", Constants.LET_TIPO_CIERRE_CAMPANHA);
				criteria.put("estadoCierre", Constants.DAT_ESTADO_CIERRE); 
				criteria.put("campanhaProceso", codigoPeriodo);
				
				List listCierreCampanha = this.mantenimientoFACGenericoService.getCierreFacturacion(criteria);
				if (listCierreCampanha != null && listCierreCampanha.size() > 0){
					criteria.put("validarFechaFacturacion", Constants.NO);
				}
			}else{
				Map datos = this.interfazSiCCService.getPeriodoFechaCampanyaActivaSF(criteria);
				String[] result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
				codigoPeriodo = result[0];
				fechaFacturacion = result[1];
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
				codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
				codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
			}
				
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoAcceso", codigoAcceso);
	        
			//
			String vccp = interfazDATService.getValidacionCierreCampanyaPendiente(criteria);
        	if(StringUtils.equals(vccp, Constants.ESTADO_ACTIVO))
        	{
        		mensajeError = getWebApplicationContext().getMessage("interfazDATEnviarAdministracionFlujosForm.message.validacion.ccp.ws",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
        		throw new Exception(mensajeError);
        	}			
			//			
			
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(this.mensajeError);
				log.debug(objetoRespuesta.getMensaje());
				
				List<ParametroACOWebService> respuestaWebService =  new ArrayList<ParametroACOWebService>();				
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroRegistros");
				parametroACOWebService.setValor(String.valueOf(interfazResult.getRegistrosProcesados()));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("nombreArchivoEntradaSalida");
				parametroACOWebService.setValor(getNombreArchivoEntradaSalida(interfazResult));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroLote");
				parametroACOWebService.setValor(interfazResult.getNumeroLote());
				respuestaWebService.add(parametroACOWebService);
				
				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));				
			}
	     
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
	
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		String codigoConexionExterna = (String) params.get("codigoConexionExterna");
    	if (StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
    		interfazDATService.executeCargarTablaInterfaz(params);
    	}
		return super.executeProcessBeforeInterfaz(params);
	}


	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		return super.prepareParamsBeforeExecute(params, pais);
	}
	
}
