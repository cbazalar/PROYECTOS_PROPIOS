package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.axis.MessageContext;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENEliminarBuzonMensajeParteDosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoGENEliminarBuzonMensajeParteDosWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoGENEliminarBuzonMensajeParteDosWebService{

	ProcesoGENProcesarCierreService procesoGENProcesarCierreService;
	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	MantenimientoFACGenericoService mantenimientoFACGenericoService;
	MantenimientoLECProgramaCorporativoService mantenimientoLECProgramaCorporativoService;
	MantenimientoCUPProgDsctoService mantenimientoCUPProgDsctoService;
	MantenimientoSTOBloqueoControlService mantenimientoSTOBloqueoControlService;
	MantenimientoEDUGenericoService mantenimientoEDUGenericoService;
	ProcesoEDUComercialService procesoEDUComercialService; 
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.procesoGENProcesarCierreService = (ProcesoGENProcesarCierreService)getWebApplicationContext().getBean("spusicc.procesoGENProcesarCierreService");
		this.mantenimientoLECProgramaCorporativoService = (MantenimientoLECProgramaCorporativoService)getWebApplicationContext().getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		this.mantenimientoFACGenericoService = (MantenimientoFACGenericoService)getWebApplicationContext().getBean("spusicc.mantenimientoFACGenericoService");
		this.mantenimientoCUPProgDsctoService = (MantenimientoCUPProgDsctoService)getWebApplicationContext().getBean("spncd.mantenimientoCUPProgDsctoService");
		this.mantenimientoSTOBloqueoControlService = (MantenimientoSTOBloqueoControlService)getWebApplicationContext().getBean("spusicc.mantenimientoSTOBloqueoControlService");
		this.mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getWebApplicationContext().getBean("edu.mantenimientoEDUGenericoService");
		this.procesoEDUComercialService = (ProcesoEDUComercialService)getWebApplicationContext().getBean("edu.procesoEDUComercialService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoGENEliminarBuzonMensaje(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.GEN_CODIGO_PROCESO_BATCH_ELIMINAR_MENSAJE_BUZON;
		final String CODIGO_INTERFAZ = Constants.GEN_CODIGO_INTERFAZ_ELIMINAR_MENSAJE_BUZON_PARTE_DOS;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_GEN;
		final String FRECUENCIA_SGR = "1";
		final String IND_TIP_VALID = "1";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz cierre proceso diario de facturacion");
		}
		try{			
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			String conexionExterna = pais.getCodigoConexionExterna();
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		    
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoConexionExterna", conexionExterna);
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuarioTemp", usuario);
			
			if (StringUtils.equals(conexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
				PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
				if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
		        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
				}
				if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
		        	fechaFacturacion = controlFacturacion.getFechaProceso();
		        }
				criteria.put("fechaProceso", fechaFacturacion);
				criteria.put("codigoPeriodoProceso", codigoPeriodo);
				criteria.put("periodoProceso", codigoPeriodo);
	        }else{
	        	Map datos = interfazSiCCService.getPeriodoFechaCampanyaActivaSF(criteria);
	        	String[] result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
	        	criteria.put("fechaProceso", result[1]);
	        	criteria.put("codigoPeriodoProceso", result[0]);
	        	criteria.put("periodoProceso", result[0]);
	        }			
	        
			criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("frecuenciaSGR", FRECUENCIA_SGR);
	        criteria.put("indTipoValid", IND_TIP_VALID);
	        
	        criteria.put("indTipoPrev", "N");
	        
	        MessageContext msgCtx = MessageContext.getCurrentContext();
	        HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
	        	    getProperty("transport.http.servletRequest");  
	        criteria.put("nombreServidor", httpServletRequest.getServerName());
	        criteria.put("entorno", httpServletRequest.getContextPath());
	        
	    	//OBTENIENDO CODIGO DE PROGRAMA 
	      	Map maplec = mantenimientoLECProgramaCorporativoService.getEncontrarProgramaLecCorporativo(codigoPeriodo);
	      	criteria.put("codigoPrograma",(String) maplec.get("codigoPrograma"));
	       // criteria.put("codigoPrograma", codigoPrograma);
	        List lista = interfazSiCCService.getListaProcesosCierreFacturacion(criteria);
			
        	String indicadorModEducacion = procesoGENProcesarCierreService.getIndicadorModEducacion(pais.getCodigo());
        	criteria.put("indicadorModEducacion", indicadorModEducacion);
        	if(indicadorModEducacion.equals(Constants.NUMERO_UNO)){
    			cargarArchivoControlFacturacion(criteria, pais);    				  				
        	}
        	
        	Map map = new HashMap();
    		map.put("codigoPais", codigoPais);
    		criteria.put(Constants.CUP_PROGRAMAS_LIST, mantenimientoCUPProgDsctoService.getProgramasDescuentosbyPais(map));
    		criteria.put(Constants.LEC_GRUPO_REGION_LIST, mantenimientoLECProgramaCorporativoService.getTipoGrupoRegion(map));
    		
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
	
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		//Usuario usuario = this.usuarioService.getUsuarioByUsername((String)params.get("codigoUsuario"));
		Usuario usuario = this.obtenerUsuarioByDefault((String)params.get("codigoUsuario"));
		
		params.put("login", usuario.getLogin());
		params.put("codigoPais", pais.getCodigo());
		params.put("codigoConexionExterna", pais.getCodigoConexionExterna());
		
		String indicadorValMae = getIndicadorValMae(pais);
		params.put("indicadorValMae", indicadorValMae!= null? indicadorValMae:Constants.NRO_CERO);
		/* INI JJ PER-SiCC-2012-0167 */
	    params.put("fechaFacturacion", (String)params.get("fechaProceso")); 
	    /* FIN JJ PER-SiCC-2012-0167 */
		params.put("codigoPeriodo", (String)params.get("codigoPeriodoProceso"));
		params.put("periodo", (String)params.get("codigoPeriodoProceso"));
		params.put("codigoUsuario", usuario.getLogin());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		/* INI JJ  PER-SiCC-2012-0361 */
//		params.put("indTipoValid", f.getIndTipoValid());
		/* FIN JJ  PER-SiCC-2012-0361 */
//		String servidor = request.getServerName();
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		log.debug(exporter.DEFAULT_BASE_ADDRESS);
		
		Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
		Matcher matcher = pattern.matcher(exporter.DEFAULT_BASE_ADDRESS);

		matcher.find();			
		String servidor = matcher.group(2);
		params.put("nombreServidor", servidor);
		
		if(((String)params.get("indicadorModEducacion")).equals(Constants.NUMERO_UNO)){
			
			
			EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
			empresaComercializadora.setCodigoPais(pais.getCodigo());
			empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
			
			params.put("codigoEmpresa", ((EmpresaComercializadora)mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(empresaComercializadora).get(0)).getCodigoEmpresa());
			
			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(pais.getCodigo());
			parametro.setCodigoEmpresa((String)params.get("codigoEmpresa"));
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			parametro =	mantenimientoEDUGenericoService.getParametroCurso(parametro);
			params.put("indicadorEnvioCronograma", parametro.getIndicadorEnvioCronograma());
		}
		
		//proceso de Reactivacion de licencias
		
		/*obtiene ultima periodo Activo*/
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

		String conexionExterna = pais.getCodigoConexionExterna();
		criteria.put("codigoConexionExterna", conexionExterna);
		
		if (StringUtils.equals(conexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			String periodoActual = controlFacturacion.getCodigoPeriodo();
			
			params.put("periodoActivo", periodoActual);
        }
        else {
        	Map datos = interfazSiCCService.getPeriodoFechaCampanyaActivaSF(criteria);
        	String[] result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
        	
        	String periodoActual = result[0];
			params.put("periodoActivo", periodoActual);
        }
		
		//params.put("rutaPath", getWebApplicationContext().getServletContext().getRealPath("/"));
		 MessageContext msgCtx = MessageContext.getCurrentContext();
	        HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
	        	    getProperty("transport.http.servletRequest"); 
		params.put("rutaPath", FileUtil.formatDirectory(httpServletRequest.getRealPath("/")));
		params.put("usuarioTemp", usuario);	
		params.put("tipoProceso", "D");
		params.put("codigoRegion", "");
		params.put("codigoGrupoRegion", "");
		params.put("codigoGrupoPago", "");
		// PROGRAMA LEC - Campa�a Recaudo
		params.put("codigoPeriodoRecaudo", "");
		return super.prepareParamsBeforeExecute(params, pais);
	}

	
	/**
	 * @param f
	 * @param session
	 * @throws Exception
	 */
	private void cargarArchivoControlFacturacion(Map params, Pais pais) throws Exception{
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		
		EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
		empresaComercializadora.setCodigoPais(pais.getCodigo());
		empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		/* Cargando archivo de control de Facturacion */
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoEmpresa",((EmpresaComercializadora)mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(empresaComercializadora).get(0)).getCodigoEmpresa());
		criteria.put("usuario", usuario);
		criteria.put("noCopiarArchivos", Constants.SI);
		procesoEDUComercialService.executeProcesoEDUCargarControlFacturacion(pais.getCodigo(), criteria);		
	}
	
	/**
	 * Obtiene indicador de Validacion de Informacion MAE
	 * @return indicadorValMae
	 */
	private String getIndicadorValMae(Pais pais) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "MAE");
		criteriaParam.put("nombreParametro", "indValidacionMAE");
		return mantenimientoSTOBloqueoControlService.getParametroGenericoSistema(criteriaParam);
	}
}
