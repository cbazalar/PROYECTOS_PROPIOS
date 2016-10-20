package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEnvioSolucionesCentroAcopioAutomaticoService;
import biz.belcorp.ssicc.service.spusicc.sto.ReporteSTOEnvioSolucionesCentroAcopioAutomaticoService;


/**
 * Service que controla el envio de Soluciones a Centro de acopio
 *  
 * <p>
 * <a href="ProcesoSTOEnvioSolucionesCentroAcopioAutomaticoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">JFA</a>
 * 
 */
@Service("spusicc.procesoSTOEnvioSolucionesCentroAcopioAutomaticoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOEnvioSolucionesCentroAcopioAutomaticoServiceImpl extends BaseService implements ProcesoSTOEnvioSolucionesCentroAcopioAutomaticoService {

	private static final String USUARIO_QUARTZ = "USUQUARTZ";
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	@Resource(name="sisicc.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;
	
	@Resource(name="genericoDAO")
	private GenericoDAO genericoDAO;
	
	@Resource(name="spusicc.procesoSTOEjecucionValidacionesDAO")
	private ProcesoSTOEjecucionValidacionesDAO procesoSTOEjecucionValidacionesDAO;
	
	@Resource(name="spusicc.reporteSTOEnvioSolucionesCentroAcopioAutomaticoService")
	private ReporteSTOEnvioSolucionesCentroAcopioAutomaticoService service;
	
	@Resource(name="paisDAO")
	private PaisDAO paisDAO;

	private String obtenerParametroPais(String codigoPais, String codigoSistema, String codigoParametro) {
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema(codigoSistema);
		parametroPais.setCodigoParametro(codigoParametro);
		
		ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
		String valorParametro = pPais.getValorParametro();
		
		return valorParametro;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEnvioSolucionesCentroAcopioAutomaticoService#executeEnvioSolucionesCentroAcopioAutomaticoJob()
	 */
	public void executeEnvioSolucionesCentroAcopioAutomaticoJob() {
		log.debug("Iniciando ejecucion de tarea executeEnvioSolucionesCentroAcopioAutomaticoJob" );
		
		Map cobPrams = new HashMap();
		//Recuperamos el codigo Pais
		String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);
		cobPrams.put("codigoPais", codigoPais);
		
		if(isEjecucionProceso(cobPrams,Constants.STO_PROCESO_BATCH_ENVIO_SOLUCIONES_CENTRO_ACOPIO)){
			//preparamos el param
			Map params = prepareParamsBeforeExecute(cobPrams);
			//sobreescribimos 
			params.put("codigoProcesoBatch", Constants.STO_PROCESO_BATCH_ENVIO_SOLUCIONES_CENTRO_ACOPIO);
			params.put("codigoModulo", Constants.SISTEMA_STO);
			//insertamos el proceos batch
			Usuario usuario = (Usuario) params.get("usuario");			
			
			procesoBatchService.deleteProcesoBatchActu(params, usuario);
			procesoBatchService.insertProcesoBatchActu(params, usuario);
			//executamos proceso
			try{
				
				log.debug("EJECUTANDO PROCESO...");
				List listaParametros = procesoSTOEjecucionValidacionesDAO.getCentrosDeAcopioSolucionesCentroAcopioAutomatico();
				
				if(listaParametros!= null && listaParametros.size() > 0)
				{
					params.put("usuarioTemp", usuario);
					
					log.info("Nro de reportes a generar : " + listaParametros.size());
					
					for(int i=0; i<listaParametros.size(); i++)
					{
						Map map = (Map)listaParametros.get(i);
						
						params.put("nomCentroAcopio", MapUtils.getString(map, "nomCentroAcopio", ""));
						params.put("ciaTransporte",   MapUtils.getString(map, "ciaTransporte", ""));
						params.put("codigoCiaTransporte", MapUtils.getString(map, "codCiaTransporte", ""));
						params.put("codigoCentroAcopio", MapUtils.getString(map, "codCentroAcopio", ""));
						params.put("emailCentroAcopio", MapUtils.getString(map, "emailCentroAcopio", ""));
							
						service.grabarReporte(params);

					}
				}				
				else
				{
					log.info("No se generaron reportes..");
				}
				
				procesoSTOEjecucionValidacionesDAO.updateIndicadorEnvio(null);
				
			}catch(Exception e){
				updateInterfazRegistroProcesoBatchError(params, usuario, e);
				log.debug("Finalizando ejecucion de tarea executeEnvioSolucionesCentroAcopioAutomaticoJob ERROR" );
				return;
			}
			updateInterfazRegistroProcesoBatchOK(params,usuario);
		}
		
		log.debug("Finalizando ejecucion de tarea executeEnvioSolucionesCentroAcopioAutomaticoJob OK" );
	}

	
	/**
	 * @param cobPrams
	 * @return
	 */
	protected Map prepareParamsBeforeExecute(Map cobPrams) {
		String codigoPais = (String)cobPrams.get("codigoPais");
		Pais pais = paisDAO.getPais(codigoPais);
		
		String codigoPeriodo = interfazSiCCService.getPeriodoDefaultByPaisCanal(codigoPais,Constants.CODIGO_CANAL_DEFAULT);
		String codigoSistema = Constants.SISTEMA_STO;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
		
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("fechaFacturacion",fechaFacturacion);
		params.put("numeroLote", "");
		params.put("codigoSistema", codigoSistema);
		params.put("codigoInterfaz", "");	
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		//el usuario QUARTZ		
		Usuario usuario = new Usuario();
		usuario.setLogin(USUARIO_QUARTZ);
		Idioma idioma = new Idioma();
		idioma.setCodigoISO(Constants.EDU_IDIOMA_DEFAULT_ES);
		usuario.setIdioma(idioma);
	    params.put("usuario",usuario);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("descripcionPais", pais.getDescripcion());
		params.put("pais", pais);
		
		
		try
		{
			URL r = this.getClass().getResource("/");
			String decoded = URLDecoder.decode(r.getFile(), "UTF-8");
            log.debug("decode "+decoded);
//			if (decoded.startsWith("/")) {
//			    decoded = decoded.replaceFirst("/", "");
//			}
			
			decoded = StringUtils.remove(decoded, "WEB-INF/classes/");
			
			params.put("rutaPath", decoded);
		}
		catch(Exception ex)
		{
			params.put("rutaPath", "/");
		}
		
		return params;
	}
	
	/**
	 * Se encarga de verificar si ya esta en ejecucion
	 * @param micPrams
	 * @param codigoProcesoBatch 
	 * @return
	 */
	private boolean isEjecucionProceso(Map cobPrams, String codigoProcesoBatch) {
		Map params = new HashMap();
		String codPais = (String)cobPrams.get("codigoPais");
		String codigoSistema = Constants.SISTEMA_STO; 
		params.put("codigoPais", codPais);
		params.put("codigoSistema", codigoSistema);
		params.put("codigoProcesoBatch", codigoProcesoBatch);
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		
		/* Verificando que no se encuentre en Ejecucion */
		List listaProcesoBatch = procesoBatchService.getProcesoBatchActuByCriteria(params);
		if (listaProcesoBatch.size() > 0) {
			ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
			if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param params
	 * @param usuario
	 */
	private void updateInterfazRegistroProcesoBatchOK(Map params,
			Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
		params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
			
		procesoBatchService.updateProcesoBatchActu(params, usuario);		
	}
	
	/**
	 * @param params
	 * @param usuario
	 * @param e
	 */
	private void updateInterfazRegistroProcesoBatchError(Map params,
			Usuario usuario, Exception e) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR); 
		
		try{
			String descripcionLog = (e!=null?e.getMessage():"null");
		
			if (descripcionLog.length() >= 1000) {
				descripcionLog = descripcionLog.substring(1,999);
			}
		
			params.put("descripcionLog", descripcionLog);
			procesoBatchService.updateProcesoBatchActu(params, usuario);
		}catch(Exception ex){
			params.put("descripcionLog", ex.getMessage());
			procesoBatchService.updateProcesoBatchActu(params, usuario);
		}		
	}		
}
