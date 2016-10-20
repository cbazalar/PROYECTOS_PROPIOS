package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.directory.InvalidAttributeValueException;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECProgramaCorporativoDAO;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETLideresDAO;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETActualizacionClasificacionLideresDAO;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCargaPedidosObjetivosExcelDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.ConsultaPEJProgramaEjecutivasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="MantenimientoLETLideresServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.mantenimientoLETLideresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLETLideresServiceImpl extends BaseService implements MantenimientoLETLideresService {
	
	@Resource(name="spusicc.mantenimientoLETLideresDAO")
	private MantenimientoLETLideresDAO mantenimientoLETLideresDAO; 
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	

	@Resource(name="spusicc.procesoLETCargaPedidosObjetivosExcelDAO")
	private ProcesoLETCargaPedidosObjetivosExcelDAO procesoLETCargaPedidosObjetivosExcelDAO;
	
	@Resource(name="genericoDAO")
	private GenericoDAO genericoDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;
	
	@Resource(name="spusicc.mantenimientoLECProgramaCorporativoDAO")
	private MantenimientoLECProgramaCorporativoDAO mantenimientoLECProgramaCorporativoDAO;
		

	/* INI JJ PER-SiCC-2012-0201 */
	@Resource(name="spusicc.procesoLETActualizacionClasificacionLideresDAO")
	private ProcesoLETActualizacionClasificacionLideresDAO procesoLETActualizacionClasificacionLideresDAO;
	
	
	/* FIN JJ PER-SiCC-2012-0201 */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getSeccionesByCriteria(java.util.Map)
	 */
	public List getSeccionesByCriteria(Map criteria) throws Exception {
		log.info("Entro a MantenimientoLETLideresServiceImpl - getSeccionesByCriteria(java.util.Map)");
		
		//-- Obtenemos la ultima campaa de cierre de region de la zona de la lider
		Base baseUltimoPeriodo = null;
		String codigoZona = (String)criteria.get("codigoZona");
		if(StringUtils.isNotBlank(codigoZona)){
			baseUltimoPeriodo = mantenimientoLETLideresDAO.getUltimaCampanaCierreRegionxZona(criteria);
			log.debug("Ultima campana :" + baseUltimoPeriodo);
		}
		
		if(baseUltimoPeriodo == null) { 
			//throw new Exception("No se encontro ultima campaa de cierre de region de la region seleccionada");
			criteria.put("oidUltimoPeriodoCR", "-1");			
		} else {
			
			//-- obtememos el anterior periodo
			/*Map criteriaAux = new HashMap();
			criteriaAux.put("oidPeriodo", baseUltimoPeriodo.getCodigo());
			Base basePenultimoPeriodo = mantenimientoMAEClienteDAO.getPeriodoAnterior(criteriaAux);*/

			criteria.put("oidUltimoPeriodoCR", baseUltimoPeriodo.getCodigo());
		}
		
		/* INI JJ PER-SiCC-2012-0201  */
		criteria.put("oidPeriodoSiguiente", mantenimientoLETLideresDAO.getOidPeriodoSiguienteByCodigoPeriodo(criteria));
		/* FIN JJ PER-SiCC-2012-0201  */
		
		//-- listado Final
		List lista = mantenimientoLETLideresDAO.getSeccionesByCriteria(criteria);
		
		log.info("Salio a MantenimientoLETLideresServiceImpl - getSeccionesByCriteria(java.util.Map) - Resultado:" + lista.size());
		return lista;
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#validarAsignacionLiderSeccion(java.util.Map)
	 */
	public String validarAsignacionLiderSeccion(Map params) {
		//log.info("Entro a MantenimientoLETLideresServiceImpl - validarAsignacionLiderSeccion(java.util.Map)");
		//String resultado = mantenimientoLETLideresDAO.validarAsignacionLiderSeccion(params); 
		//log.info("Salio a MantenimientoLETLideresServiceImpl - validarAsignacionLiderSeccion(java.util.Map) - Resultado:" + resultado);
		
		return mantenimientoLECProgramaCorporativoDAO.validarAsignacionLiderSeccion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#execAsignacionLiderSeccion(java.util.Map)
	 */
	public void execAsignacionLiderSeccion(Map criteria) throws InvalidAttributeValueException {
		log.info("Entro a MantenimientoLETLideresServiceImpl - execAsignacionLiderSeccion(java.util.Map)");
		
		//-- Variables
		String indOrigRegi = MapUtils.getString(criteria, "indOrigRegi", "");
		String codigoCliente = (String)criteria.get("codigoCliente");
		int codigoPeriodoProceso = Integer.valueOf((String)criteria.get("codigoPeriodoProceso")).intValue();
		int periodoAsignarLider = Integer.valueOf((String)criteria.get("periodoAsignarLider")).intValue();
		boolean flagPerAsiLidIgualPerPro = false;
		/* INI JJ PER-SiCC-2012-0201  */
		Integer indicadorProgramaLet = (Integer)criteria.get("indicadorProgramaLet");
		/* FIN JJ PER-SiCC-2012-0201  */
		String codigoResponsableAnterior = null;
		
		//-- Validar que periodo validacion Asignar Lider sea igual a periodo proceso
		if ( periodoAsignarLider == codigoPeriodoProceso )
			flagPerAsiLidIgualPerPro = true;
		
		String periodoLider = (String)criteria.get("periodoAsignarLider");
		Integer oidPeriodoHasta = this.getOidPeriodoByCodigoPeriodo(periodoLider);
		criteria.put("oidPeriodoHasta", oidPeriodoHasta);
		String realizarEliminacionClasificacion = (String)criteria.get("realizarEliminacionClasificacion");
				
		if(flagPerAsiLidIgualPerPro){

			//-- Verifico si la consultora tiene clasificacion lider ------------------------------
			String indicadorClasificacionLider = mantenimientoLETLideresDAO.getConsultoraClasificacionLider(criteria);
				
			//-- Si no tiene clasificacion lider, se inserta en la tabla MAE_CLIEN_CLASI
			String oidLider = criteria.get("oidCliente").toString();
				
			if (indicadorClasificacionLider.equals("0") && (!oidLider.equals(""))){
				try {
					mantenimientoLETLideresDAO.insertClasificacionConsultoraLider(criteria);
				} catch (Exception e) {
					//Capturo la excepcion para identificarla y lanzar el mensaje adecuado
					throw new InvalidAttributeValueException();
				}
			}
			
			//-- obtenemos la ultima responsable de la seccion ------------------------------------
			Map mapUltimoResponsable = mantenimientoLETLideresDAO.getUltimoResponsableSeccion(criteria);
			
			
			if(mapUltimoResponsable != null) {
				codigoResponsableAnterior = (String)mapUltimoResponsable.get("codigoResponsable");
				String oidHistoricoGerente = (String)mapUltimoResponsable.get("oidHistoricoGerente");
				String codigoPeriodoDesde = (String)mapUltimoResponsable.get("codigoPeriodoDesde");			
				criteria.put("oidHistoricoGerente", oidHistoricoGerente);
				criteria.put("codigoPeriodoDesde", codigoPeriodoDesde);
				
				String codigoPeridoActual = (String)criteria.get("codigoPeriodoActual");
							
				criteria.put("codigoPeriodo", codigoPeridoActual);
				
				if(codigoPeriodoDesde.equals(codigoPeridoActual))
					oidPeriodoHasta = this.getOidPeriodoByCodigoPeriodo(codigoPeridoActual);
				else
					oidPeriodoHasta = this.getOidPeriodoAnteriorByCodigoPeriodo(criteria);
				
				criteria.put("oidPeriodoHasta", oidPeriodoHasta);
				
				String codigoPeridoHasta = this.getCodigoPeriodoByOidPeriodo(oidPeriodoHasta);
				
				if(codigoPeridoHasta.equals(codigoPeriodoDesde)){
					if(codigoPeridoActual.equals(codigoPeriodoDesde)){
						this.deleteHistoricoGerente(Integer.valueOf(oidHistoricoGerente));
					}else{
						//-- actualizamos la fecha hasta del ultimo responsable de la seccion
						mantenimientoLETLideresDAO.updateFechaHastaHistoricoGerente(criteria);
					}
				}else{
					//-- actualizamos la fecha hasta del ultimo responsable de la seccion
					mantenimientoLETLideresDAO.updateFechaHastaHistoricoGerente(criteria);
				}
				
				/* INI JJ PER-SiCC-2012-0201 */
				if(indicadorProgramaLet.intValue() == 2){
					Map params = new HashMap();
					
					params.put("codigoPais", (String)criteria.get("codigoPais"));
					params.put("tipoTransaccion", "2");
					params.put("tipoOperacion", "1");
					params.put("codigoPeriodo", String.valueOf(periodoAsignarLider));
					params.put("codigoCliente", codigoResponsableAnterior);
					params.put("codigoUsuario", (String)criteria.get("codigoUsuario"));
					
					procesoLETActualizacionClasificacionLideresDAO.executeProcesoLETActualizacionClasificacionLideres(params);
				}
				/* FIN JJ PER-SiCC-2012-0201 */
				
				//-- Verifica si el responsable anterior tiene la clasificacion de lider 
				//-- Si existe, la borra de MAE_CLIEN_CLASI
				if (Constants.SI.equals(realizarEliminacionClasificacion)) {
					Map criteriaClasificacion = new HashMap();
					criteriaClasificacion.put("codigoLider", codigoResponsableAnterior);
					
					mantenimientoLETLideresDAO.deleteClasificacionLiderConsultoraAnterior(criteriaClasificacion);
				}
			}

			//-- Se realiza la asignacion o desasignacion del lider -------------------------------
			mantenimientoLETLideresDAO.updateLiderSeccion(criteria);
			
		}else{
			
			//-- obtenemos la ultima responsable de la seccion ------------------------------------
			Map mapUltimoResponsable = mantenimientoLETLideresDAO.getUltimoResponsableSeccionValAsi(criteria);

			if(mapUltimoResponsable != null) {
				
				//-- Variables
				codigoResponsableAnterior = (String)mapUltimoResponsable.get("codigoResponsable");
				String oidHistoricoGerente = (String)mapUltimoResponsable.get("oidHistoricoGerente");
				String codigoPeriodoDesde = (String)mapUltimoResponsable.get("codigoPeriodoDesde");			
				boolean flagActualizar = true;
				
				
				//-- Actualizar POJO
				criteria.put("oidHistoricoGerente", oidHistoricoGerente);
				criteria.put("codigoPeriodoDesde", codigoPeriodoDesde);
				
				//-- Elimninar historico
				if(codigoPeriodoDesde!=null)
					if(codigoPeriodoDesde.equals(String.valueOf(periodoAsignarLider))){
						flagActualizar = false;
						this.deleteHistoricoGerente(Integer.valueOf(oidHistoricoGerente));
					}
				
				//-- Actualizar historico
				oidPeriodoHasta = this.getOidPeriodoByCodigoPeriodo(String.valueOf(codigoPeriodoProceso));
				criteria.put("oidPeriodoHasta", oidPeriodoHasta);
				if(flagActualizar){
					mantenimientoLETLideresDAO.updateHistoricoGerenteValAsi(criteria);
				}
				
				//-- Verifica si el responsable anterior tiene la clasificacion de lider 
				//-- Si existe, la borra de MAE_CLIEN_CLASI
				if (Constants.SI.equals(realizarEliminacionClasificacion)) {
					mapUltimoResponsable = mantenimientoLETLideresDAO.getUltimoResponsableSeccionValAsi(criteria);
					if(mapUltimoResponsable != null) {
						String codigoResponsableClasi = (String)mapUltimoResponsable.get("codigoResponsable");
					    Map criteriaClasificacion = new HashMap();
						criteriaClasificacion.put("codigoLider", codigoResponsableClasi);
						mantenimientoLETLideresDAO.deleteClasificacionLiderConsultoraAnterior(criteriaClasificacion);
					}
				}
			}
			
		}
			
		//-- Se inserta un registro en la entidad Historico Gerente para el nuevo responsable
		if(!codigoCliente.equals("")) {
			String codigoSeccionInicial = (String)criteria.get("codigoSeccionInicial");
			String codigoSeccion = (String)criteria.get("codigoSeccion");
			boolean modificarSeccion = false;
			if (StringUtils.isNotBlank(codigoSeccionInicial)) {
				if (!codigoSeccion.equals(codigoSeccionInicial)) {
					criteria.put("codigoSeccion", codigoSeccionInicial);
					modificarSeccion = true;
				}
			}
			mantenimientoLETLideresDAO.insertHistoricoGerente(criteria);
			if (modificarSeccion) {
				criteria.put("codigoSeccion", codigoSeccion);
			}
			
			/* INI JJ PER-SiCC-2012-0201 */
			if(flagPerAsiLidIgualPerPro && indicadorProgramaLet.intValue() == 2){
				Map params = new HashMap();
				
				params.put("codigoPais", (String)criteria.get("codigoPais"));
				params.put("tipoTransaccion", "1");
				params.put("tipoOperacion", "1");
				params.put("codigoPeriodo", String.valueOf(periodoAsignarLider));
				params.put("codigoCliente", codigoCliente);
				params.put("codigoUsuario", (String)criteria.get("codigoUsuario"));
				
				procesoLETActualizacionClasificacionLideresDAO.executeProcesoLETActualizacionClasificacionLideres(params);			
			}
			/* FIN JJ PER-SiCC-2012-0201 */
			
			/* INI AO PER-SiCC-2013-0666 */
			
			if((flagPerAsiLidIgualPerPro && indicadorProgramaLet.intValue() == 3)){
				Map params = new HashMap();
				
				String codigoPais = (String) criteria.get("codigoPais");
				String periodo = String.valueOf(periodoAsignarLider);
				//String periodoAnterior = consultaPEJProgramaEjecutivasDAO.getObtienePeriodo(codigoPais, periodo, -1);
				/*Map mapPeriodoAnterior = new HashMap();
				mapPeriodoAnterior.put("codigoPais", codigoPais);
				mapPeriodoAnterior.put("codigoPeriodo", periodo);
				mapPeriodoAnterior.put("numeroPeriodo", -1);
				String periodoAnterior = "";
				periodoAnterior = genericoDAO.getPeriodoNSiguiente(mapPeriodoAnterior);
				*/
				params.put("codigoPais", codigoPais);
				params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
				params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
				params.put("tipoEvaluacion", "1");
				params.put("codigoCliente", codigoCliente);
				//params.put("codigoPeriodo", periodoAnterior);//modificado por el periodoProceso
				params.put("codigoPeriodo", periodo);
				params.put("codigoUsuario", (StringUtils.equals(indOrigRegi, Constants.ZON_ORIGEN_WEB))? new String("") : (String)criteria.get("codigoUsuario"));
				
				procesoLETActualizacionClasificacionLideresDAO.executeProcesoLETProcesarClasificacionLideres(params);
			}
			/* FIN AO PER-SiCC-2013-0666 */
			
			if(flagPerAsiLidIgualPerPro && indicadorProgramaLet.intValue() == 4){
				Map params = new HashMap();
				
				String codigoPais = (String) criteria.get("codigoPais");
				String periodo = String.valueOf(periodoAsignarLider);
				params.put("codigoPais", codigoPais);
				params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
				params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
				params.put("tipoEvaluacion", "1");
				params.put("codigoCliente", codigoCliente);
				params.put("codigoPeriodo", periodo);
				params.put("codigoUsuario", (StringUtils.equals(indOrigRegi, Constants.ZON_ORIGEN_WEB))? new String("") : (String)criteria.get("codigoUsuario"));
								
				mantenimientoLECProgramaCorporativoDAO.executeProcesoLECProcesarClasificacionLideres(params);
			}
		}
		
		
		log.info("Salio a MantenimientoLETLideresServiceImpl - execAsignacionLiderSeccion(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getOidCanalByCodigoCanalGenericoLET(java.util.Map)
	 */
	public String getOidCanalByCodigoCanalGenericoLET(Map criteria) {
		log.info("Entro a MantenimientoLETLideresServiceImpl - getOidCanalByCodigoCanalGenericoLET(java.util.Map)");
		String resultado = mantenimientoLETLideresDAO.getOidCanalByCodigoCanalGenericoLET(criteria);
		log.info("Salio a MantenimientoLETLideresServiceImpl - getOidCanalByCodigoCanalGenericoLET(java.util.Map) - Resultado: " + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getOidMarcaByCodigoMarcaGenericoLET(java.util.Map)
	 */
	public String getOidMarcaByCodigoMarcaGenericoLET(Map criteria) {
		log.info("Entro a MantenimientoLETLideresServiceImpl - getOidMarcaByCodigoMarcaGenericoLET(java.util.Map)");
		String resultado = mantenimientoLETLideresDAO.getOidMarcaByCodigoMarcaGenericoLET(criteria);
		log.info("Salio a MantenimientoLETLideresServiceImpl - getOidMarcaByCodigoMarcaGenericoLET(java.util.Map) - Resultado: " + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getOidPaisByCodigoPaisLET(java.util.Map)
	 */
	public String getOidPaisByCodigoPaisLET(Map criteria) {
		log.info("Entro a MantenimientoLETLideresServiceImpl - getOidPaisByCodigoPaisLET(java.util.Map)");
		String resultado = mantenimientoLETLideresDAO.getOidPaisByCodigoPaisLET(criteria);
		log.info("Salio a MantenimientoLETLideresServiceImpl - getOidPaisByCodigoPaisLET(java.util.Map) - Resultado: " + resultado);
		return resultado;
	}

	/**
	 * @return the mantenimientoLETLideresDAO
	 */
	public MantenimientoLETLideresDAO getMantenimientoLETLideresDAO() {
		return mantenimientoLETLideresDAO;
	}

	/**
	 * @param mantenimientoLETLideresDAO the mantenimientoLETLideresDAO to set
	 */
	public void setMantenimientoLETLideresDAO(
			MantenimientoLETLideresDAO mantenimientoLETLideresDAO) {
		this.mantenimientoLETLideresDAO = mantenimientoLETLideresDAO;
	}

	/**
	 * @return the mantenimientoMAEClienteDAO
	 */
	public MantenimientoMAEClienteDAO getMantenimientoMAEClienteDAO() {
		return mantenimientoMAEClienteDAO;
	}

	/**
	 * @param mantenimientoMAEClienteDAO the mantenimientoMAEClienteDAO to set
	 */
	public void setMantenimientoMAEClienteDAO(
			MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO) {
		this.mantenimientoMAEClienteDAO = mantenimientoMAEClienteDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getIndicadorProgramaLet(java.lang.String)
	 */
	public Integer getIndicadorProgramaLet(String codigoPais) {
		return mantenimientoLETLideresDAO.getIndicadorProgramaLet(codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getEncontrarConcursoLet(java.lang.String)
	 */
	public Integer getEncontrarConcursoLet(String periodoActual) {
		return mantenimientoLETLideresDAO.getEncontrarConcursoLet(periodoActual);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getOidPeriodoByCodigoPeriodo(java.lang.String)
	 */
	public Integer getOidPeriodoByCodigoPeriodo(String codigoPerido) {
		return mantenimientoLETLideresDAO.getOidPeriodoByCodigoPeriodo(codigoPerido);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getOidPeriodoAnteriorByCodigoPeriodo(java.util.Map)
	 */
	public Integer getOidPeriodoAnteriorByCodigoPeriodo(Map criteria) {
		return mantenimientoLETLideresDAO.getOidPeriodoAnteriorByCodigoPeriodo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getCodigoPeriodoByOidPeriodo(java.lang.Integer)
	 */
	public String getCodigoPeriodoByOidPeriodo(Integer oidPeriodo) {
		return mantenimientoLETLideresDAO.getCodigoPeriodoByOidPeriodo(oidPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#deleteHistoricoGerente(java.lang.Integer)
	 */
	public void deleteHistoricoGerente(Integer oidHistoricoGerente) {
		mantenimientoLETLideresDAO.deleteHistoricoGerente(oidHistoricoGerente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getIndicadorAsignarLider(java.util.Map)
	 */
	public String getIndicadorAsignarLider(Map criteria) {
		log.info("Entro a MantenimientoLETLideresServiceImpl - getIndicadorAsignarLider(java.util.Map)");
		String resultado = mantenimientoLETLideresDAO.getIndicadorAsignarLider(criteria);
		log.info("Salio a MantenimientoLETLideresServiceImpl - getIndicadorAsignarLider(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
	/* INI JJ PER-SiCC-2012-0201 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getOidPeriodoSiguienteByCodigoPeriodo(java.util.Map)
	 */
	public Integer getOidPeriodoSiguienteByCodigoPeriodo(Map criteria) {
		return mantenimientoLETLideresDAO.getOidPeriodoSiguienteByCodigoPeriodo(criteria);
	}

	/**
	 * @return
	 */
	public ProcesoLETActualizacionClasificacionLideresDAO getProcesoLETActualizacionClasificacionLideresDAO() {
		return procesoLETActualizacionClasificacionLideresDAO;
	}

	/**
	 * @param procesoLETActualizacionClasificacionLideresDAO
	 */
	public void setProcesoLETActualizacionClasificacionLideresDAO(ProcesoLETActualizacionClasificacionLideresDAO procesoLETActualizacionClasificacionLideresDAO) {
		this.procesoLETActualizacionClasificacionLideresDAO = procesoLETActualizacionClasificacionLideresDAO;
	}
	/* FIN JJ PER-SiCC-2012-0201 */
	
	/* INI SA PER-SiCC-2012-0476  */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getDatosLider(java.lang.String)
	 */
	public Map getDatosLider(String codigoConsultora) {
		Map resultado = new HashMap();
		String oidCliente = null;
		String codigoError = "";
		
		oidCliente = mantenimientoLETLideresDAO.getOidClienteByCodigoCliente(codigoConsultora);		
		
		if(oidCliente != null) {
			String oidLider = mantenimientoLETLideresDAO.getOidLider(oidCliente);
			
			if(oidLider != null) {
				resultado = mantenimientoLETLideresDAO.getDatosLider(oidLider);
			} else {
				codigoError = "02";
			}
		} else {
			codigoError = "01";
		}
		
		resultado.put("codigoError", codigoError);
		
		return resultado;
	}
	/* FIN SA PER-SiCC-2012-0476  */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#executeValidarAsignacionLiderRegionZona(java.util.Map)
	 */
	public void executeValidarAsignacionLiderRegionZona(Map params) {
		mantenimientoLETLideresDAO.executeValidarAsignacionLiderRegionZona(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#executeProcesoDesvinculacion(java.util.Map)
	 */
	public void executeProcesoDesvinculacion(Map criteria) {
		mantenimientoLETLideresDAO.executeProcesoDesvinculacion(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#validarDesvinculacion(java.util.Map)
	 */
	public String validarDesvinculacion(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoLETLideresDAO.validarDesvinculacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getLideres(java.lang.String)
	 */
	public List getLideres(String codigoPais) {
		return mantenimientoLETLideresDAO.getLideres(codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getEncontrarProgramaLetCorporativo(java.lang.String)
	 */
	public Integer getEncontrarProgramaLetCorporativo(String periodoActual) {
		return mantenimientoLETLideresDAO.getEncontrarProgramaLetCorporativo(periodoActual);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getConcursoTramoPrograma(java.util.Map)
	 */
	public Map getConcursoTramoPrograma(Map criteria) {
		return mantenimientoLETLideresDAO.getConcursoTramoPrograma(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#loadfileExcel(java.util.Map, java.util.List, java.util.List, java.lang.Boolean)
	 */
	public boolean loadfileExcel(Map criteria, List objetivosList, List errorObjetivosListList, Boolean flagErrorGlobal) throws Exception {
		boolean flagError = false;
		boolean flagErrorGeneral = false;
		boolean flagErrorSalida = false;
		
		String  directorioTemporal = (String)criteria.get("directorioTemporal");
		String  nombreArchivo = (String)criteria.get("nombreArchivo");
		String  codigoPais = (String)criteria.get("codigoPais");
		String  codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String  codigoUsuario = (String)criteria.get("login");
	//	String[] regionList = (String[])criteria.get("regionList");
		
		String codigoConcurso = "";
		String campanaInicioConcurso = "";
		String campanaFinConcurso = "";
		String codigoTramo = "";
		String campanaInicioTramo = "";
		String campanaFinTramo = "";
		
		String tipoCarga = (String)criteria.get("tipoCarga");
		//String indicadorValidaLiderSeccion = (String)criteria.get("indicadorValidaLiderSeccion");
			
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		//nos pasamos a la segunda fila, ya que el primero se encuentra la cabecera
		excelUtil.next();
		
		
		int fila = 1;
		
		Map objetivos = null;
		String mensajeError = null;
		
		while(excelUtil.hasNext()){
			//flagError = false;
			Map mapRow = excelUtil.next();
			log.debug("La fila tiene: "+mapRow.size()+" columnas.");
			
			for(int i=0;i<mapRow.size();i++){
				if(mapRow.get(String.valueOf(i)).equals("")){
					flagErrorGeneral = true;
					log.debug("Las celda NO tiene contenido.");
					break;
				}else{
					flagErrorGeneral = false;
					log.debug(i+") Las celda SI tiene contenido.");
					break;
				}
			}
			
			if(!flagErrorGeneral){
					mensajeError = "";
					objetivos = new HashMap();
					
					fila = fila + 1;
	
					log.debug("Carga Pedidos Objetivos mapRow--- " + fila + " - " + mapRow.toString());
					
					objetivos.put("fila", fila);
					objetivos.put("codigoPais", ((String)mapRow.get("0")).toUpperCase().trim());
					objetivos.put("codigoPeriodo", Integer.valueOf((((Long)new DecimalFormat("000000").parse((String)mapRow.get("1"))).intValue())));
					objetivos.put("codigoUsuario", codigoUsuario);
					
					//Obtengo los valores del tramo correspondiente para cada registro del excel
					Map mapConsursoTramo = new HashMap();
					mapConsursoTramo = getConcursoTramoPrograma(objetivos);
					
					if(mapConsursoTramo != null){
						codigoConcurso = (String)mapConsursoTramo.get("codigoPrograma");
						campanaInicioConcurso = (String)mapConsursoTramo.get("campanaInicioProgr");
						campanaFinConcurso = (String)mapConsursoTramo.get("campanaFinProgr");
						codigoTramo = (String)mapConsursoTramo.get("codigoTramo");
						campanaInicioTramo = (String)mapConsursoTramo.get("campanaInicioTramo");
						campanaFinTramo = (String)mapConsursoTramo.get("campanaFinTramo");
						
						objetivos.put("codigoTramo", codigoTramo);
						
						if(!StringUtils.equals(tipoCarga, "CO2")){
							objetivos.put("codigoRegion", ((String)mapRow.get("2")).toUpperCase().trim());
							objetivos.put("codigoZona", ((String)mapRow.get("3")).toUpperCase().trim());
							objetivos.put("codigoSeccion", ((String)mapRow.get("4")).toUpperCase().trim());
							objetivos.put("numeroPeridosObjetivos", Integer.valueOf(((Long)new DecimalFormat("0000").parse((String)mapRow.get("5"))).intValue()));
							objetivos.put("codigoConcurso", codigoConcurso);
						}else{
							objetivos.put("codigoLider", ((String)mapRow.get("2")).toUpperCase().trim());
							objetivos.put("numeroPeridosObjetivos", Integer.valueOf(((Long)new DecimalFormat("0000").parse((String)mapRow.get("3"))).intValue()));
							objetivos.put("codigoConcurso", codigoConcurso);
						}
						
						//Valida que la campaa de Excel no sea mayor a la campaa final del tramo  
						if(StringUtils.equals(tipoCarga, "CO1")){
							if(((Integer) objetivos.get("codigoPeriodo") > Integer.valueOf(campanaFinTramo))){
								mensajeError = mensajeError+getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorPeriodoDiferenteProceso");
								flagError = true;
							
							}
						}
						
						//Valida que exista el Codigo Lider en el Maestro Clientes
						if(StringUtils.equals(tipoCarga, "CO2")){
							Map params = new HashMap();
							params.put("codigoPais", MapUtils.getString(objetivos, "codigoPais"));
							params.put("codigoCliente", MapUtils.getString(objetivos, "codigoLider"));
							params.put("codigoPeriodo", MapUtils.getString(objetivos, "codigoPeriodo"));
				
							 //Validar si el lider esta registrado en ZON_HISTO_GEREN.
							 // registrado : 1 = existe , 0 = No existe
							 String registrado = mantenimientoLETLideresDAO.validarCodigoLiderRegistrado(params);
							 if (StringUtils.equals(registrado,"0")) {
								mensajeError = mensajeError.concat(getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorLiderNoExisteMaestroCliente"));
								flagError = true;
							 }
						}
						
						
						if(!StringUtils.equals(tipoCarga, "CO2")){
							//boolean isEncontro = false;
							
							/*for(int i=0;i<regionList.length;i++){
								if(((String)objetivos.get("codigoRegion")).equals(regionList[i])){
									isEncontro = true;
									break;
								}
							}*/
							/*
							if(!isEncontro){
								mensajeError = mensajeError+getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorRegionProcesadaDiferenteProceso");
								flagError = true;
							
							}*/
							
							criteria.put("codigoZona", objetivos.get("codigoZona"));
							criteria.put("codigoRegion", objetivos.get("codigoRegion"));
							criteria.put("codigoSeccion", objetivos.get("codigoSeccion"));
							
							if(procesoLETCargaPedidosObjetivosExcelDAO.getExisteZona(criteria).intValue() == 0){
								mensajeError = mensajeError+getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorZonaNoExisteNoActiva");
								flagError = true;
								
							}
							
							if(procesoLETCargaPedidosObjetivosExcelDAO.getExisteZona(criteria).intValue() > 0){
								if(procesoLETCargaPedidosObjetivosExcelDAO.getExisteSeccionByZona(criteria).intValue() == 0){
									mensajeError = mensajeError+getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorSeccionNoExisteNoActiva");
									flagError = true;
						
								}
							}
						}
						
					}else{
						//No hay valores al consultar tramo/programa para el periodo correspondiente del excel
						mensajeError = "No existe registros de programa/tramo para le periodo: "+MapUtils.getString(objetivos, "codigoPeriodo");
						flagError = true;
						
					}
					
				
					//Valida que exista Pais
					if(!((String)objetivos.get("codigoPais")).equals(codigoPais)){
						mensajeError = getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorPaisNoExiste");
						flagError = true;
						
					}
				
					//Valida que la campaa de Excel este comprendida dentro del Programa LET
					String periodoActual = MapUtils.getString(objetivos, "codigoPeriodo");
					if(mantenimientoLETLideresDAO.getEncontrarProgramaLetCorporativo(periodoActual).intValue() == 0){
						mensajeError = getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorCampanaExcelNoCorrespondePrograma");
						flagError = true;
						
					}
					
					//Valida que la campaa de Excel sea mayor a la campaa seleccionada
					if(((Integer) objetivos.get("codigoPeriodo") < Integer.valueOf(codigoPeriodo))){
						mensajeError = mensajeError+getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorPeriodoMenorAlProcesado");
						flagError = true;
					}
					
					 int numPerioObjts = MapUtils.getIntValue(objetivos, "numeroPeridosObjetivos");
					
					 if (numPerioObjts == 0) {
						 mensajeError = mensajeError.concat(getKeyMessage("procesoLETCargaObjetivosExcelForm.mensajeErrorObjetivoZero"));
				         flagError = true;
					  }
					
					if(flagError){
						objetivos.put("descripcionError", mensajeError);
						errorObjetivosListList.add(objetivos);
						flagError = false;
						flagErrorSalida = true;
					}else{
						objetivosList.add(objetivos);
					} 
				
			}else{
				//Se termino de recorrer las filas del archivo excel.
				break;
			}	
			
		}
		
		return flagErrorSalida;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#executeCargaObjetivos(java.util.List)
	 */
	public void executeCargaObjetivos(List objetivosList) {
		List objetivosAll = mantenimientoLETLideresDAO.getObjetivosAll();
		boolean isEncontro = false;
		Map temp;
		Map pedidosObjetivos;
		
		Map params;
		
		for(int i = 0; i < objetivosList.size(); i ++){
			temp = (Map)objetivosList.get(i);
			isEncontro = false;
			
			for(int j = 0; j < objetivosAll.size(); j ++){
				pedidosObjetivos = (Map)objetivosAll.get(j);
				
				if(((String)temp.get("codigoPais")).equals((String)pedidosObjetivos.get("codigoPais")) &&
				   (String.valueOf((Integer)temp.get("codigoPeriodo"))).equals((String)pedidosObjetivos.get("campanaProceso")) &&
				   ((String)temp.get("codigoConcurso")).equals((String)pedidosObjetivos.get("codigoConcurso")) &&
				   ((String)temp.get("codigoRegion")).equals((String)pedidosObjetivos.get("codigoRegion")) &&
				   ((String)temp.get("codigoZona")).equals((String)pedidosObjetivos.get("codigoZona")) &&
				   ((String)temp.get("codigoSeccion")).equals((String)pedidosObjetivos.get("codigoSeccion"))
				  ){
					isEncontro = true;
				}
			}
			
			if(isEncontro){
				mantenimientoLETLideresDAO.updateObjetivo(temp);
			}else{
				mantenimientoLETLideresDAO.insertObjetivo(temp);
			}
		}
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#executeCargaObjetivosRetencion22(java.util.List)
	 */
	public void executeCargaObjetivosRetencion22(List objetivosList) {
		List objetivosRetencion22All = mantenimientoLETLideresDAO.getObjetivosRetencion22All();
		boolean isEncontro = false;
		Map temp;
		Map objetivosRetencion22;
		
		Map params;
		
		for(int i = 0; i < objetivosList.size(); i ++){
			temp = (Map)objetivosList.get(i);
			isEncontro = false;
			
			for(int j = 0; j < objetivosRetencion22All.size(); j ++){
				objetivosRetencion22 = (Map)objetivosRetencion22All.get(j);
				
				if(((String)temp.get("codigoLider")).equals((String)objetivosRetencion22.get("codigoLider")) &&
				   (String.valueOf((Integer)temp.get("codigoPeriodo"))).equals((String)objetivosRetencion22.get("campanaProceso"))){
					isEncontro = true;
				}
			}
			
			if(isEncontro){
				mantenimientoLETLideresDAO.updateLiderObjetivo(temp);
			}else{
				mantenimientoLETLideresDAO.insertLiderObjetivo(temp);
			}
		}
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#executeCargaObjetivosRetencion3344(java.util.List, java.lang.String)
	 */
	public void executeCargaObjetivosRetencion3344(List objetivosList, String tipoCarga) {
		
		Map temp;
		boolean insert = false;
		for(int i = 0; i < objetivosList.size(); i ++){
			temp = (Map)objetivosList.get(i);

			Map criteria = new HashMap();
			criteria.put("codigoPais", String.valueOf(temp.get("codigoPais")));
			criteria.put("codigoRegion", String.valueOf(temp.get("codigoRegion")));
			criteria.put("codigoConcurso", String.valueOf(temp.get("codigoConcurso")));
			criteria.put("codigoPeriodo", String.valueOf(temp.get("codigoPeriodo")));
			criteria.put("codigoZona", String.valueOf(temp.get("codigoZona")));	
			criteria.put("codigoSeccion", String.valueOf(temp.get("codigoSeccion")));
			
			String objetivos = mantenimientoLETLideresDAO.getObjetivosRetencion3344(criteria);
			// 1 = si existe , 0 = no existe
			boolean existe = StringUtils.equals(objetivos,"1");
			if(StringUtils.equals(tipoCarga, "CO3")){
				temp.put("numIngRetencion33", MapUtils.getString(temp, "numeroPeridosObjetivos"));
				temp.put("indOrigCalc33", "X");
				if (!existe) {
					temp.put("numIngRetencion44", "0");
					insert = true;
				}else{
					mantenimientoLETLideresDAO.updateSeccionObjetivo(temp);
				}
			}else{
				
				temp.put("numIngRetencion44", MapUtils.getString(temp, "numeroPeridosObjetivos"));
				temp.put("indOrigCalc44", "X");
				if (!existe){
					temp.put("numIngRetencion33", "0");
				    insert = true;
				}else{
					mantenimientoLETLideresDAO.updateSeccionObjetivo44(temp);
				}
			}
		
			if(insert){
				mantenimientoLETLideresDAO.insertSeccionObjetivo(temp);
				insert = false;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getCampanaCerrada(java.lang.String)
	 */
	public Integer getCampanaCerrada(String codigoPeriodo){
		return mantenimientoLETLideresDAO.getCampanaCerrada(codigoPeriodo);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getOidCliente(java.util.Map)
	 */
	public String getOidCliente(Map criteria){
		return mantenimientoLETLideresDAO.getOidCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getEncontrarProgramaLetCorporativo(java.lang.String)
	 */
	public Map getEncontrarProgramaLecCorporativo(String periodoActual) {
		return mantenimientoLECProgramaCorporativoDAO.getEncontrarProgramaLecCorporativo(periodoActual);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getIndicadorPosibleLider(java.util.Map)
	 */
	public Integer getIndicadorPosibleLider(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getIndicadorPosibleLider(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getIndicadorPosibleLiderHisto(java.util.Map)
	 */
	public Integer getIndicadorPosibleLiderHisto(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getIndicadorPosibleLiderHisto(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getMaximoPeriodoHastaLider(java.lang.String)
	 */
	public List getMaximoPeriodoHastaLider(String codigoCliente){
		return mantenimientoLETLideresDAO.getMaximoPeriodoHastaLider(codigoCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#deleteClasificacionLiderConsultoraAnterior(java.util.Map)
	 */
	public void deleteClasificacionLiderConsultoraAnterior(Map criteria) {
		mantenimientoLETLideresDAO.deleteClasificacionLiderConsultoraAnterior(criteria);
	}
}