package biz.belcorp.ssicc.service.scsicc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseBlob;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteServiceImpl.
 *
 * @author Belcorp
 * @version 1.0
 * 06:42:27 PM
 */
/**
 * @author Sigcomt
 *
 */

@Service("scsicc.reporteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteServiceImpl extends BaseService implements ReporteService {

	/** The reporte dao. */
	@Resource(name="scsicc.reporteDAO")
	private ReporteDAO reporteDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getMarcas()
	 */
	public List getMarcas() {
		return this.reporteDAO.getMarcas();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getRangosPeriodo()
	 */
	public List getRangosPeriodo() {
		return this.reporteDAO.getRangosPeriodo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getCanalesByCodigoISO(java.lang.String)
	 */
	public List getCanalesByCodigoISO(String codigo) {
		return this.reporteDAO.getCanalesByCodigoISO(codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getTiposVinculosByPais(java.lang.String)
	 */
	public List getTiposVinculosByPais(String codigoPais) {
		return this.reporteDAO.getTiposVinculosByPais(codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getReporteDAO()
	 */
	public ReporteDAO getReporteDAO() {
		return reporteDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#setReporteDAO(biz.belcorp.ssicc.dao.scsicc.ReporteDAO)
	 */
	public void setReporteDAO(ReporteDAO reporteDAO) {
		this.reporteDAO = reporteDAO;
	}
	
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeFacturaPendienteSeccion(java.util.Map)
	 */
	public void executeFacturaPendienteSeccion(Map criteria) {
		this.reporteDAO.executeFacturaPendienteSeccion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeFacturaPendienteSeccion(java.util.Map)
	 */
	public void executeCuadreSAPFI(Map criteria) {
		this.reporteDAO.executeCuadreSAPFI(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeControlAsistencia(java.util.Map)
	 */
	public void executeControlAsistencia(Map criteria) {
		//se obtiene el oidProceso
		BigDecimal oidProceso = reporteDAO.getOidProcesoControlAsistencia();
		criteria.put("oidProceso", oidProceso);
		this.reporteDAO.executeControlAsistencia(criteria);
	}
	
	/**
	 * Execute control asistencia triunfadoras.
	 *
	 * @param criteria the criteria
	 */
	public void executeControlAsistenciaTriunfadoras(Map criteria) {
		//se obtiene el oidProceso
		BigDecimal oidProceso = reporteDAO.getOidProcesoControlAsistenciaTriunfadoras();
		criteria.put("oidProceso", oidProceso);
		this.reporteDAO.executeControlAsistenciaTriunfadoras(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeEstadoCtaCteVendedora(java.util.Map)
	 */
	public void executeEstadoCtaCteVendedora(Map criteria) {
		this.reporteDAO.executeEstadoCtaCteVendedora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeDetalleCtaCte(java.util.Map)
	 */
	public void executeDetalleCtaCte(Map criteria) {
		this.reporteDAO.executeDetalleCtaCte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeVentaVariable(java.util.Map)
	 */
	public void executeVentaVariable(Map criteria){
		this.reporteDAO.executeVentaVariable(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeFacturaPendienteCampana(java.util.Map)
	 */
	public void executeFacturaPendienteCampana(Map criteria) {
		this.reporteDAO.executeFacturaPendienteCampana(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeBaseRecuperacionCampana(java.util.Map)
	 */
	public void executeBaseRecuperacionCampana(Map criteria) {
		this.reporteDAO.executeBaseRecuperacionCampana(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getListaPeriodosByBasCtrlFact(java.lang.String, java.lang.String)
	 */
	public List getListaPeriodosByBasCtrlFact(String criteria, String actualHistorio) {
		Map map = new HashMap ();
		map.put("codigoPais", criteria);
		if (StringUtils.isNotEmpty(actualHistorio))
			map.put("staCamp", actualHistorio);
		return this.reporteDAO.getListaPeriodosByBasCtrlFact(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getListaGenerico(java.lang.String, java.util.Map)
	 */
	public List getListaGenerico(String getLista,Map criteria){
		return this.reporteDAO.getListaGenerico( getLista,criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getListaReporte(java.lang.String, java.util.Map)
	 */
	public List getListaReporte(String getLista,Map criteria){
		return this.reporteDAO.getListaReporte( getLista,criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#insertTableReportesSQL(java.lang.String, java.util.Map)
	 */
	public void insertTableReportesSQL(String method,Map criteria) {
		this.reporteDAO.insertTableReportesSql(method,criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#deleteTableReportesSQL(java.lang.String, java.util.Map)
	 */
	public void deleteTableReportesSQL(String method,Map criteria) {
		this.reporteDAO.deleteTaleReporte(method, criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getLogReporteDisco(java.util.Map)
	 */
	public List getLogReporteDisco(Map criteria) {
		return reporteDAO.getLogReporteDisco(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteSQL(java.lang.String, java.util.Map)
	 */
	public void executeReporteSQL(String procedure,Map criteria) {
		this.reporteDAO.executeReporteSQL(procedure,criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeComisionIngreso(java.util.Map)
	 */
	public void executeComisionIngreso(Map criteria) {
		this.reporteDAO.executeComisionIngreso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeComisionRecuperacion(java.util.Map)
	 */
	public void executeComisionRecuperacion(Map criteria) {
		this.reporteDAO.executeComisionRecuperacion(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeComisionComercializacion(java.util.Map)
	 */
	public void executeComisionComercializacion(Map criteria) {
		this.reporteDAO.executeComisionComercializacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getDatosConcursosByNumeroConcurso(java.util.Map)
	 */
	public Map getDatosConcursosByNumeroConcurso(Map criteria){		
		return this.reporteDAO.getDatosConcursosByNumeroConcurso(criteria);
	}

     /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getParametrosReporte(java.util.Map)
	 */
	public Map getParametrosReporte(Map criteria){		
		return this.reporteDAO.getParametrosReporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getParametrosReporteGeneral(java.util.Map)
	 */
	public Map getParametrosReporteGeneral(Map criteria){		
		return this.reporteDAO.getParametrosReporteGeneral(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#deleteLogReporteDisco(java.util.Map)
	 */
	public void deleteLogReporteDisco(Map criteria) {
		this.reporteDAO.deleteLogReporteDisco(criteria);
	}
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.service.ReporteService#insertLogReporteDisco(java.util.Map)
     */
    public void insertLogReporteDisco(Map criteria) {
    	this.reporteDAO.insertLogReporteDisco(criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidString(java.lang.String, java.util.Map)
	 */
	public String getOidString(String string, Map criteria) {
		// TODO Auto-generated method stub
		return this.reporteDAO.getOidString(string, criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidConcurso(java.lang.String, java.util.Map)
	 */
	public String getOidConcurso(String string, Map criteria) {
		// TODO Auto-generated method stub
		return this.reporteDAO.getOidConcurso(string, criteria);
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getStringGenerico(java.lang.String, java.util.Map)
	 */
	public String getStringGenerico(String string, Map criteriaOperacion) {
		// TODO Auto-generated method stub
		return this.reporteDAO.getStringGenerico(string, criteriaOperacion);
	}
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeComisionRecuperacionGZona(java.util.Map)
	 */
	public void executeComisionRecuperacionGZona (Map criteria) {
		this.reporteDAO.executeComisionRecuperacionGZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#devuelvePorcentajeTramoComision(java.util.Map)
	 */
	public void devuelvePorcentajeTramoComision (Map criteria) {
		this.reporteDAO.devuelvePorcentajeTramoComision(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getConsultaResponsablesUA(java.util.Map)
	 */
	public List getConsultaResponsablesUA (Map criteria) {
		return this.reporteDAO.getConsultaResponsablesUA(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executePrevioReporteControlCliente(java.util.Map)
	 */
	public void executePrevioReporteControlCliente (Map criteria) {
		this.reporteDAO.executePrevioReporteControlCliente(criteria);
		//this.reporteDAO.executePrevioReporteControlClienteLbel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getLogoEmpresaEducacion(java.util.Map)
	 */
	public BaseBlob getLogoEmpresaEducacion(Map criteria) {
		return this.reporteDAO.getLogoEmpresaEducacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeAnalisisArea(java.util.Map)
	 */
	public void executeAnalisisArea(Map criteria){
		this.reporteDAO.executeAnalisisArea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getSecuenciaNextValue()
	 */
	public String getSecuenciaNextValue() {
		return this.reporteDAO.getSecuenciaNextValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#metodosReporte(java.util.Map)
	 */
	public void metodosReporte(Map criteria){
		/* Primero Insertamos Todos Los niveles del concurso */
		this.deleteTableReportesSQL("deleteReporteINCProyeccionPremiosConcursos",criteria);
	
		this.insertTableReportesSQL("insertReporteINCProyeccionPremiosConcursosTodosNiveles",criteria);
		
		/*
		 * Segundo insertamos los clientes que esten en el nivel seleccionado
		 * por el sistema
		 */
	
		this.deleteTableReportesSQL("deleteReporteINCProyeccionPremiosClientes",criteria);
		this.insertTableReportesSQL("insertReporteINCProyeccionPremiosClientes",criteria);
		
		/*
		 * Insetamos los nivels a trabajar en la tabla temporal de CONCURSO
		 */
		this.deleteTableReportesSQL("deleteReporteINCProyeccionPremiosConcursos",criteria);
		this.insertTableReportesSQL("insertReporteINCProyeccionPremiosConcursos",criteria);
		
		/*
		 * Borramos la tabbla Temporal donde se almacenan a las ganadoras con
		 * sus respectivos premios.
		 */
		this.insertTableReportesSQL("deleteReporteINCProyeccionPremios",criteria);
		
		/*
		 * Invocamos al Store que determinara finalmente que consultoras son las
		 * ganadoras y que premios reciben
		 */
		
		this.executeReporteSQL("executeReporteINCProyeccionPremios",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getPeriodosCorporativosPorTipo(java.lang.String)
	 */
	public List getPeriodosCorporativosPorTipo(String tipoPeriodo) {
		return this.reporteDAO.getPeriodosCorporativosPorTipo(tipoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeSeguimientoCalificacionCampana(java.util.Map)
	 */
	public void executeSeguimientoCalificacionCampana (Map criteria) {
		this.reporteDAO.executeSeguimientoCalificacionCampana(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getPeriodosSeguimientoCalificacionCampana(java.util.Map)
	 */
	public Map getPeriodosSeguimientoCalificacionCampana(Map criteria) {
		Map mapPeriodos = new HashMap();
		
		List campanasTramo = this.reporteDAO.getCampanasTramo(criteria);
		Base baseCampana = (Base)campanasTramo.get(0);
		String campanaInicio = baseCampana.getCodigo();
		String campanaFin = baseCampana.getDescripcion();
	
		String anioInicial = (String)criteria.get("anioInicial");
		if(campanaInicio.compareTo(campanaFin) > 0) {
			campanaInicio = anioInicial + campanaInicio;
			campanaFin = String.valueOf(Integer.parseInt(anioInicial)+1) + campanaFin;
		} else {
			campanaInicio = anioInicial + campanaInicio;
			campanaFin = anioInicial + campanaFin;
		}	
		
		criteria.put("campanaInicio", campanaInicio);
		criteria.put("campanaFin", campanaFin);
		List periodos = this.reporteDAO.getPeriodosSeguimientoCalificacionCampana(criteria);
		
		for(int i=0; i<periodos.size(); i++) {
			Base basePeriodo = (Base)periodos.get(i);
			
			if (i==0)
				mapPeriodos.put("CampanaAnt", basePeriodo.getDescripcion().substring(4,6));
			else
				mapPeriodos.put("Campana" + i, basePeriodo.getDescripcion().substring(4,6));
		}
		
		return mapPeriodos;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getTramosEvaluacionResumenPorSeccion(java.util.Map)
	 */
	public Map getTramosEvaluacionResumenPorSeccion(Map criteria) {
		Map mapTramos = new HashMap();
		
		String codigoTramo = (String)criteria.get("codigoTramo");
		criteria.put("codigoTramoMayor", codigoTramo);
		
		List campanasTramo = this.reporteDAO.getCampanasTramo(criteria);
		
		for(int i=0; i<campanasTramo.size(); i++) {
			Base baseCampana = (Base)campanasTramo.get(i);
			String campanaInicio = baseCampana.getCodigo();
			String campanaFin = baseCampana.getDescripcion();

			String anioInicial = (String)criteria.get("anioInicial");
			if(campanaInicio.compareTo(campanaFin) > 0) {
				anioInicial = String.valueOf(Integer.parseInt(anioInicial)+1);
			}	
			
			mapTramos.put("Tramo" + (i+1), "C-" + baseCampana.getDescripcion() + "/" + anioInicial);
		}
		
		return mapTramos;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeResumenEvaluacionSeccion(java.util.Map)
	 */
	public void executeResumenEvaluacionSeccion (Map criteria) {
		this.reporteDAO.executeResumenEvaluacionSeccion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeResumenEvaluacionZona(java.util.Map)
	 */
	public void executeResumenEvaluacionZona (Map criteria) {
		this.reporteDAO.executeResumenEvaluacionZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#devuelvePeriodosporFechas(java.util.Map)
	 */
	public List devuelvePeriodosporFechas(Map criteria){
		return this.reporteDAO.devuelvePeriodosporFechas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getUnidadNegocio()
	 */
	public List getUnidadNegocio(){
		return this.reporteDAO.getUnidadNegocio();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getCabeceraSACFaltante(java.util.Map)
	 */
	public Map getCabeceraSACFaltante(Map params) {
		return this.reporteDAO.getCabeceraSACFaltante(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCodUsuarios()
	 */
	public List getCodUsuarios(){
		return this.reporteDAO.getCodUsuarios();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getResultadoList()
	 */
	public List getResultadoList(){
		return this.reporteDAO.getResultadoList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getMotivoList()
	 */
	public List getMotivoList(){
		return this.reporteDAO.getMotivoList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeIndicadorGestionIncentivosCDR(java.util.Map)
	 */
	public void executeIndicadorGestionIncentivosCDR (Map criteria) {
		this.reporteDAO.executeIndicadorGestionIncentivosCDR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTiposPrograma(java.util.Map)
	 */
	public List getTiposPrograma(Map criteria) {
		List tiposProgramaList = this.reporteDAO.getTiposPrograma(criteria);
		
		if (tiposProgramaList != null && tiposProgramaList.size() > 0) {
			String condicionTodos = (String)criteria.get("condicionTodos");
			
			if (StringUtils.equals("T", condicionTodos)) {
				Base base = new Base();
				base.setCodigo("Todos");
				base.setDescripcion("Todos");

				tiposProgramaList.add(0, base);
			}
		}
		
		return tiposProgramaList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executePremiosEntregados(java.util.Map)
	 */
	public void executePremiosEntregados (Map criteria) {
		this.reporteDAO.executePremiosEntregados(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getZonasFacturanHoy(java.util.Map)
	 */
	public List getZonasFacturanHoy (Map criteria) {
		return this.reporteDAO.getZonasFacturanHoy(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTipoOfertas()
	 */
	public List getTipoOfertas(){
		return reporteDAO.getTipoOfertas();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getParamReporGener(java.util.HashMap)
	 */
	public String getParamReporGener(Map criteria){
		return reporteDAO.getParamReporGener(criteria);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresSAC(Map criteria) {
		return reporteDAO.getIndicadoresSAC(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getSecuenciaNextValue(java.lang.String)
	 */
	public String getSecuenciaNextValue(String method) {
		return reporteDAO.getSecuenciaNextValue(method);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeIndicadoresSAC(java.util.Map)
	 */
	public void executeIndicadoresSAC (Map criteria) {
		reporteDAO.executeIndicadoresSAC(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getPeriodosResultadosEvaluacionEjecutiva(java.util.Map)
	 */
	public Map getPeriodosResultadosEvaluacionEjecutiva(Map <String,String>params) { 
		Map <String,String> map= new HashMap<String, String>();
		String campanaInicioAnt="";
		String campanaFinAnt="";
		//se obtiene tramo anterior, anho anterior, rango de campanhas actuales(ini-fin)
		//codigoTramoAnterior,anhoAnteriorTramo,	campanaInicioAct,  	campanaFinAct
		Map criteria = this.reporteDAO.getPeriodosResultadosEvaluacionEjecutiva(params);
		
		//obtenemos tramo anterior
		map.put("codigoPais", params.get("codigoPais"));
		map.put("codigoTramo", String.valueOf(criteria.get("codigoTramoAnterior")));
		List campanasTramo = this.reporteDAO.getCampanasTramo(map);
		log.debug("campanasTramo anterior " +campanasTramo.size());
		for(int i=0; i<campanasTramo.size(); i++) {
			Base baseCampana = (Base)campanasTramo.get(i);
			campanaInicioAnt = baseCampana.getCodigo();
			campanaFinAnt = baseCampana.getDescripcion();
		}
		String campanaInicioAct=(String)criteria.get("campanaInicioAct");
		String campanaFinAct=(String)criteria.get("campanaFinAct");
		
		criteria.put("campanaInicioAnt", campanaInicioAnt);
		criteria.put("campanaFinAnt", campanaFinAnt);
		criteria.put("nombreColumnaAnterior","EV"+"_"+campanaInicioAnt+"_"+campanaFinAnt );
		criteria.put("nombreColumnaActual","EV"+"_"+campanaInicioAct+"_"+campanaFinAct );	
		return criteria;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeCargaReporteFNASoles(java.util.Map)
	 */
	public void executeCargaReporteFNASoles (Map criteria){
		reporteDAO.executeCargaReporteFNASoles(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getIndicadorCajaBolsaProducto(java.util.Map)
	 */
	public List getIndicadorCajaBolsaProducto (Map criteria) {
		return this.reporteDAO.getIndicadorCajaBolsaProducto(criteria);
	}
			
        /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getListaZonasReporteSACAsistencia()
	 */
	public List getListaZonasReporteSACAsistencia(){
		return this.reporteDAO.getListaZonasReporteSACAsistencia();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getListaZonasReporteSACActivasSaldo()
	 */
	public List getListaZonasReporteSACActivasSaldo(){
		return this.reporteDAO.getListaZonasReporteSACActivasSaldo();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getEstadoEquivalenciaCliente()
	 */
	public List getEstadoEquivalenciaCliente(){
		return this.reporteDAO.getEstadoEquivalenciaCliente();
	}
	 
        /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getContadorListaRegiones()
	 */
	public String getContadorListaRegionesZonas(Map criteria){
		return this.reporteDAO.getContadorListaRegionesZonas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTiposCDR()
	 */
	public List getTiposCDR(){
		return reporteDAO.getTiposCDR();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTiposAtencion()
	 */
	public List getTiposAtencion(){
		return reporteDAO.getTiposAtencion();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getRangoFechaPeriodo(java.lang.String)
	 */
	public List getRangoFechaPeriodo(String codPeriodo){
		return reporteDAO.getRangoFechaPeriodo(codPeriodo);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getMaximoValorOferta(java.util.Map)
	 */
	public String getMaximoValorOferta(Map criteria){
		return reporteDAO.getMaximoValorOferta(criteria);
        }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeSTOConsolidadoAtencionCDR(java.util.Map)
	 */
	public void executeSTOConsolidadoAtencionCDR (Map criteria) {
		reporteDAO.executeSTOConsolidadoAtencionCDR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidClasificacion(java.util.Map)
	 */
	public String getOidClasificacion(Map criteria){
		return reporteDAO.getOidClasificacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReportePremioConcurso(java.util.Map)
	 */
	public void executeReporteProyeccionesPremioConcurso(Map params) {
		reporteDAO.executeReporteProyeccionesPremioConcurso(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#insertGloblanTemporaryForNumNivel(java.util.Map)
	 */
	public void insertGloblanTemporaryForNumNivel(Map params){
		reporteDAO.insertGloblanTemporaryForNumNivel(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getEstadoAtencionesMAV(java.lang.String)
	 */
	public List getEstadoAtencionesMAV(String oidPeriodo){
		return reporteDAO.getEstadoAtencionesMAV(oidPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getNoPasaronPedidosMAV(java.util.Map)
	 */
	public List getNoPasaronPedidosMAV(Map criteria){
		return reporteDAO.getNoPasaronPedidosMAV(criteria);
	}
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidFacturacionTipoDocumento(java.util.Map)
	 */
	public List getOidFacturacionTipoDocumento(Map criteria) {
		return reporteDAO.getOidFacturacionTipoDocumento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeSICPedidosAfectadosDetFNA(java.util.Map)
	 */
	public void executeSICPedidosAfectadosDetFNA (Map criteria) {
		reporteDAO.executeSICPedidosAfectadosDetFNA(criteria);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTiposSolicitudSTO()
	 */
	public List getTiposSolicitudSTO(){
		return reporteDAO.getTiposSolicitudSTO();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidConcursoByNumConc(java.lang.String)
	 */
	public String getOidConcursoByNumConc(String codigoConcurso) {
		return reporteDAO.getOidConcursoByNumConc(codigoConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getResultadoChequeoConsultora()
	 */
	public List getResultadoChequeoConsultora(Map criteria) {
		return this.reporteDAO.getResultadoChequeoConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getDetalleResultadoChequeo(java.util.Map)
	 */
	public List getDetalleResultadoChequeo(Map criteria) {
		return this.reporteDAO.getDetalleResultadoChequeo(criteria);
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getConsultaInformeAvancePedido(java.util.Map)
	 */
	public List getConsultaInformeAvancePedido(Map criteria) {
		return reporteDAO.getConsultaInformeAvancePedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getConsultorasActivasSinPedido(java.util.Map)
	 */
	public List getConsultorasActivasSinPedido(Map criteria) {
		return reporteDAO.getConsultorasActivasSinPedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getFechaUltimaActualizacionZona(java.util.Map)
	 */
	public String getFechaUltimaActualizacionZona(Map criteria) {
		return reporteDAO.getFechaUltimaActualizacionZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getNumeroRegistros(java.util.Map)
	 */
	public Integer getNumeroRegistros(Map criteria) {
		return reporteDAO.getNumeroRegistros(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getInformeAvancePedido(java.util.Map)
	 */
	public List getInformeAvancePedido(Map criteria) {
		return reporteDAO.getInformeAvancePedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCodigoVentasRechazados(java.util.Map)
	 */
	public List getCodigoVentasRechazados(Map criteria){
		return reporteDAO.getCodigoVentasRechazados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getDetallePedidoFacturado(java.util.Map)
	 */
	public List getDetallePedidoFacturado(Map criteria)  {
		return reporteDAO.getDetallePedidoFacturado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getFaltantesAnunciados(java.util.Map)
	 */
	public List getFaltantesAnunciados(Map criteria)  {
		return reporteDAO.getFaltantesAnunciados(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#generarReporteRUVSunatTXT(java.util.Map)
	 */
	public Map generarReporteRUVSunatTXT(Map criteria) {
		return reporteDAO.generarReporteRUVSunatTXT(criteria);
	}
	  
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#generarReporteRUVSunatCSV(java.util.Map)
	 */
	public Map generarReporteRUVSunatCSV(Map criteria) {
		return reporteDAO.generarReporteRUVSunatCSV(criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTipoBloqueos()
	 */
	public List getTipoBloqueos() {
		log.info("Entro a ReporteServiceImpl - getTipoBloqueos()");
		List lista = reporteDAO.getTipoBloqueos();
		log.info("Salio a ReporteServiceImpl - getTipoBloqueos() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#generarReporteRUVSunatErrorCSV(java.util.Map)
	 */
	public Map generarReporteRUVSunatErrorCSV(Map criteria) {
		log.info("Entro a ReporteServiceImpl - generarReporteRUVSunatErrorCSV(Map)");
		Map resultado = reporteDAO.generarReporteRUVSunatErrorCSV(criteria);
		log.info("Salio a ReporteServiceImpl - generarReporteRUVSunatErrorCSV(Map)");
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#generarReporteRUVSunatErrorTXT(java.util.Map)
	 */
	public Map generarReporteRUVSunatErrorTXT(Map criteria) {
		log.info("Entro a ReporteServiceImpl - generarReporteRUVSunatErrorTXT(Map)");
		Map resultado = reporteDAO.generarReporteRUVSunatErrorTXT(criteria);
		log.info("Salio a ReporteServiceImpl - generarReporteRUVSunatErrorTXT(Map)");
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getReporteOCRPedidosGP1SinError(java.util.Map)
	 */
	public List getReporteOCRPedidosGP1SinError(Map criteria) {
		log.info("Entro a ReporteServiceImpl - getReporteOCRPedidosGP1SinError(Map)");
		List lista = reporteDAO.getReporteOCRPedidosGP1SinError(criteria);
		log.info("Salio a ReporteServiceImpl - getReporteOCRPedidosGP1SinError(Map) - Resultado:"+lista.size());
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#generarReporteRUVSunatExcel(java.util.Map)
	 */
	public Map generarReporteRUVSunatExcel(Map criteria) {
		Map resultado = reporteDAO.generarReporteRUVSunatExcel(criteria);
		return resultado;
	}		
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getReportePRIGenerarSolicitudesPrivilege(java.util.Map)
	 */
	public List getReportePRIGenerarSolicitudesPrivilege(Map criteria) {
		log.info("Entro a ReporteServiceImpl - getReportePRIGenerarSolicitudesPrivilege(java.util.Map)");
		List lista = reporteDAO.getReportePRIGenerarSolicitudesPrivilege(criteria);
		log.info("Salio a ReporteServiceImpl - getReportePRIGenerarSolicitudesPrivilege(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempMapaAnaquel(java.util.Map)
	 */
	public String getObtenerSecuenciaTempMapaAnaquel(Map criteria) {
		return this.reporteDAO.getObtenerSecuenciaTempMapaAnaquel(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteMapaAnaquelBalanceoDiario(java.util.Map)
	 */
	public void executeGenerarReporteMapaAnaquelBalanceoDiario(Map criteria) {
		this.reporteDAO.executeGenerarReporteMapaAnaquelBalanceoDiario(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTempReporteMapaAnaq(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteMapaAnaq(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTempReporteMapaAnaq(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempBalanceoLinea(java.util.Map)
	 */
	public String getObtenerSecuenciaTempBalanceoLinea(Map criteria) {
		return this.reporteDAO.getObtenerSecuenciaTempBalanceoLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteBalanceoLinea(java.util.Map)
	 */
	public void executeGenerarReporteBalanceoLinea(Map criteria) {
		this.reporteDAO.executeGenerarReporteBalanceoLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTemporalReporteBalanceoLinea(java.util.Map)
	 */
	public void eliminarRegistrosTablaTemporalReporteBalanceoLinea(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTemporalReporteBalanceoLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCampanhaActivaByZona(java.util.Map)
	 */
	public String getCampanhaActivaByZona(Map criteriaPeriodo) {
		return reporteDAO.getCampanhaActivaByZona(criteriaPeriodo);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidRegionByPaisMarcaCanal(java.util.Map)
	 */
	public Integer getOidRegionByPaisMarcaCanal(Map criteria) {
		return reporteDAO.getOidRegionByPaisMarcaCanal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidZonaByPaisMarcaCanalRegion(java.util.Map)
	 */
	public Integer getOidZonaByPaisMarcaCanalRegion(Map criteria) {
		return reporteDAO.getOidZonaByPaisMarcaCanalRegion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidSeccionByPaisMarcaCanalZona(java.util.Map)
	 */
	public Integer getOidSeccionByPaisMarcaCanalZona(Map criteria) {
		return reporteDAO.getOidSeccionByPaisMarcaCanalZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getRetencionPedidos(java.util.Map)
	 */
	public List getRetencionPedidos(Map criteria) {
		//ejecutamos el proceso antes
		reporteDAO.executeRetencionPedidos(criteria);
		return reporteDAO.getRetencionPedidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getPedidosDigitados(java.util.Map)
	 */
	public List getPedidosDigitados(Map criteria) {
		//ejecutamos el proceso antes
		reporteDAO.executePedidosDigitados(criteria);
		return reporteDAO.getPedidosDigitados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getZonasGrupo(java.lang.String)
	 */
	public List getZonasGrupo(String grupoFacturacion) {
		return reporteDAO.getZonasGrupo(grupoFacturacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getActividad(java.util.Map)
	 */
	public List getActividad(Map params) {
		return reporteDAO.getActividad(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getGrupoFacturacion(java.util.Map)
	 */
	public List getGrupoFacturacion(Map params) {
		return reporteDAO.getGrupoFacturacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeActualizarTipoPedido()
	 */
	public void executeActualizarTipoPedido() {
		log.info("Entro a ReporteServiceImpl - executeActualizarTipoPedido");
		reporteDAO.executeActualizarTipoPedido();
		log.info("Salio a ReporteServiceImpl - executeActualizarTipoPedido");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getConsultorasActivasSinPedidoAct(java.util.Map)
	 */
	public List getConsultorasActivasSinPedidoAct(Map criteria) {
		return reporteDAO.getConsultorasActivasSinPedidoAct(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getInformeAvancePedidoAct(java.util.Map)
	 */
	public List getInformeAvancePedidoAct(Map criteria) {
		return reporteDAO.getInformeAvancePedidoAct(criteria);
	}			

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTiposSolicitudReclamos()
	 */
	public List getTiposSolicitudReclamos() {
		return reporteDAO.getTiposSolicitudReclamos();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidPeriodo(java.util.Map)
	 */
	public int getOidPeriodo(Map criteria) {
		log.info("Entro a ReporteServiceImpl - getOidPeriodo(java.util.Map)");
		int resultado = reporteDAO.getOidPeriodo(criteria);
		log.info("Salio a ReporteServiceImpl - getOidPeriodo(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getIndicadoresCajaBolsa()
	 */
	public List getIndicadoresCajaBolsa() {
		log.info("Entro a ReporteServiceImpl - getIndicadoresCajaBolsa()");
		List lista = reporteDAO.getIndicadoresCajaBolsa();
		log.info("Salio a ReporteServiceImpl - getIndicadoresCajaBolsa() - Resultado:" + lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidActividad(java.util.Map)
	 */
	public String getOidActividad(Map criteria) {
		return reporteDAO.getOidActividad(criteria);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#insertTemporalSTOReporteRechazadas(java.util.Map)
	 */
	public void insertTemporalSTOReporteRechazadas(Map criteria) {
		this.reporteDAO.insertTemporalSTOReporteRechazadas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTempSTORechazadas(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempSTORechazadas(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTempSTORechazadas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempSTORechazadas(java.util.Map)
	 */
	public String getObtenerSecuenciaTempSTORechazadas(Map criteria) {
		return (String)this.reporteDAO.getObtenerSecuenciaTempSTORechazadas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getValidarMontoTope(java.util.Map)
	 */
	public Double getValidarMontoTope(Map criteria) {
		log.info("Entro a ReporteServiceImpl - getValidarMontoTope(java.util.Map)");
		Double resultado = this.reporteDAO.getValidarMontoTope(criteria);
		log.info("Salio a ReporteServiceImpl - getValidarMontoTope(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTempReporteCierreCostoVentas(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoVentas(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTempReporteCierreCostoVentas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteCierreCostoVentas(java.util.Map)
	 */
	public void executeGenerarReporteCierreCostoVentas(Map criteria) {
		this.reporteDAO.executeGenerarReporteCierreCostoVentas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempCOSCierreCostoVentas(java.util.Map)
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoVentas(Map criteria) {
		return this.reporteDAO.getObtenerSecuenciaTempCOSCierreCostoVentas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteCierreCostoDevoluciones(java.util.Map)
	 */
	public void executeGenerarReporteCierreCostoDevoluciones(Map criteria) {
		this.reporteDAO.executeGenerarReporteCierreCostoDevoluciones(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempCOSCierreCostoDevoluciones(java.util.Map)
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoDevoluciones(Map criteria) {
		return this.reporteDAO.getObtenerSecuenciaTempCOSCierreCostoDevoluciones(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getProcesoInformeAvancePedido(java.util.Map)
	 */
	public List getProcesoInformeAvancePedido(Map criteria) {
		return reporteDAO.getProcesoInformeAvancePedido(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getProcesoInformeAvancePedidoAct(java.util.Map)
	 */
	public List getProcesoInformeAvancePedidoAct(Map criteria) {
		return reporteDAO.getProcesoInformeAvancePedidoAct(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#generarReporteRUVLibroVentasMensualCSV(java.util.Map)
	 */
	public Map generarReporteRUVLibroVentasMensualCSV(Map criteria) {
		return reporteDAO.generarReporteRUVLibroVentasMensualCSV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#generarReportePEDBonificacionCSV(java.util.Map)
	 */
	public Map generarReportePEDBonificacionCSV(Map criteria) {
		return reporteDAO.generarReportePEDBonificacionCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTituloReportePaisMarca(java.util.Map)
	 */
	public String getTituloReportePaisMarca(Map criteria) {
		return reporteDAO.getTituloReportePaisMarca(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTipoDocumentoList()
	 */
	public List getTipoDocumentoList() {
		return reporteDAO.getTipoDocumentoList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getOidTipoDocumento()
	 */
	public String getOidTipoDocumento(Map criteria) {
		return reporteDAO.getOidTipoDocumento(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempDesctoComercial(java.util.Map)
	 */
	public String getObtenerSecuenciaTempDesctoComercial(Map criteria) {
		return this.reporteDAO.getObtenerSecuenciaTempDesctoComercial(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteSAPFiDescuentoComercial(java.util.Map)
	 */
	public void executeGenerarReporteSAPFiDescuentoComercial(Map criteria) {
		this.reporteDAO.executeGenerarReporteSAPFiDescuentoComercial(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTempReporteDescuentoComercial(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoComercial(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTempReporteDescuentoComercial(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecTempDsctoVol(java.util.Map)
	 */
	public String getObtenerSecTempDsctoVol(Map criteria) {
		return this.reporteDAO.getObtenerSecTempDsctoVol(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTempReporteDescuentoVolumen(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoVolumen(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTempReporteDescuentoVolumen(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteSAPFiDescuentoVolumen(java.util.Map)
	 */
	public void executeGenerarReporteSAPFiDescuentoVolumen(Map criteria) {
		this.reporteDAO.executeGenerarReporteSAPFiDescuentoVolumen(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTempReporteVentaLinea(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteVentaLinea(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTempReporteVentaLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteSAPFiVentaLinea(java.util.Map)
	 */
	public void executeGenerarReporteSAPFiVentaLinea(Map criteria) {
		this.reporteDAO.executeGenerarReporteSAPFiVentaLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempVentaLinea(java.util.Map)
	 */
	public String getObtenerSecuenciaTempVentaLinea(Map criteria) {
		return this.reporteDAO.getObtenerSecuenciaTempVentaLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTemporalReporOperaReclaPedidos(java.util.Map)
	 */
	public void eliminarRegistrosTablaTemporalReporOperaReclaPedidos(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTemporalReporOperaReclaPedidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteRECOperaReclaPedidos(java.util.Map)
	 */
	public void executeGenerarReporteRECOperaReclaPedidos(Map criteria) {
		this.reporteDAO.executeGenerarReporteRECOperaReclaPedidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempOperaReclam(java.util.Map)
	 */
	public String getObtenerSecuenciaTempOperaReclam(Map criteria) {
		return this.reporteDAO.getObtenerSecuenciaTempOperaReclam(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTipoReporteList()
	 */
	public List getTipoReporteList(){
		return this.reporteDAO.getTipoReporteList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getTipoReporteList(java.util.Map)
	 */
	public List getTipoReporteList(Map criteria){
		return this.reporteDAO.getTipoReporteList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTemporalReporConsultBloqDesb()
	 */
	public void eliminarRegistrosTablaTemporalReporConsultBloqDesb() {
		this.reporteDAO.eliminarRegistrosTablaTemporalReporConsultBloqDesb();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteMAEConsultoraBloquedasDesbloq(java.util.Map)
	 */
	public void executeGenerarReporteMAEConsultoraBloquedasDesbloq(Map criteria) {
		this.reporteDAO.executeGenerarReporteMAEConsultoraBloquedasDesbloq(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getObtenerSecuenciaTempConsultBloqDesb()
	 */
	public String getObtenerSecuenciaTempConsultBloqDesb() {
		return this.reporteDAO.getObtenerSecuenciaTempConsultBloqDesb();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTipoPlantilla(java.util.Map)
	 */
	public String getTipoPlantilla(Map criteria) {
		return this.reporteDAO.getTipoPlantilla(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getListaAlmacen(java.util.Map)
	 */
	public List getListaAlmacen(Map criteria) {
		return this.reporteDAO.getListaAlmacen(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getOrigenSTOByTipoDocumento(java.util.Map)
	 */
	public List getOrigenSTOByTipoDocumento(Map params) {
		return this.reporteDAO.getOrigenSTOByTipoDocumento(params);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getControlAsistencia(java.util.Map)
	 */
	public List getControlAsistencia(Map params) {
		return reporteDAO.getControlAsistencia(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteMAEConsultorasPedidos(java.util.Map)
	 */
	public void executeGenerarReporteMAEConsultorasPedidos(Map criteria) {
		reporteDAO.executeGenerarReporteMAEConsultorasPedidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getDetallePedidoNoFacturado(java.util.Map)
	 */
	public List getDetallePedidoNoFacturado(Map criteria) {
		return reporteDAO.getDetallePedidoNoFacturado(criteria);
	}
	
	/* INI SA RCR PER-SiCC-2013-0003 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getDiferenciaPeriodos(java.util.Map)
	 */
	public Integer getDiferenciaPeriodos(Map criteria) {
		return reporteDAO.getDiferenciaPeriodos(criteria);
	}
	/* FIN SA RCR PER-SiCC-2013-0003 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeComisionesCalculadasPorRegion(java.util.Map)
	 */
	public void executeComisionesCalculadasPorRegion(Map criteria) {
		 reporteDAO.executeComisionesCalculadasPorRegion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeCalcularComisionVentaNetaEfectiva(java.util.Map)
	 */
	public void executeCalcularComisionVentaNetaEfectiva(Map criteria) {
		reporteDAO.executeCalcularComisionVentaNetaEfectiva(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeCalcularVentaZona(java.util.Map)
	 */
	public void executeCalcularVentaZona(Map criteria) {
		reporteDAO.executeCalcularVentaZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTipoReporte()
	 */
	public List getTipoReporte() {
		return reporteDAO.getTipoReporte();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBDetalladCobranza31diasXLSX(java.util.Map)
	 */
	public void executeReporteCOBDetalladCobranza31diasXLSX(Map criteria){
		reporteDAO.executeReporteCOBDetalladCobranza31diasXLSX(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getTipoMav()
	 */
	public List getTipoMav() {
		return reporteDAO.getTipoMav();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getTipoOferta()
	 */
	public List getTipoOferta() {
		return reporteDAO.getTipoOferta();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(java.util.Map)
	 */
	public void executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(Map criteria) {
		reporteDAO.executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(java.util.Map)
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(Map criteria) {
		reporteDAO.executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(java.util.Map)
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(Map criteria) {
		reporteDAO.executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCPrimPedDeud(java.util.Map)
	 */
	public void executeReporteCCCPrimPedDeud(Map map) {
		reporteDAO.executeReporteCCCPrimPedDeud(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCPrimSegPedDeud(java.util.Map)
	 */
	public void executeReporteCCCPrimSegPedDeud(Map map) {
		reporteDAO.executeReporteCCCPrimSegPedDeud(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#deleteTableReporteCCCPrimPedDeud(java.util.Map)
	 */
	public void deleteTableReporteCCCPrimPedDeud(Map criteria) {
		reporteDAO.deleteTableReporteCCCPrimPedDeud(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#deleteTableReporteCCCPrimSegPedDeud(java.util.Map)
	 */
	public void deleteTableReporteCCCPrimSegPedDeud(Map criteria) {
		reporteDAO.deleteTableReporteCCCPrimSegPedDeud(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargaTemporalReportePEDConsultorasChequear(java.util.Map)
	 */
	public void executeCargaTemporalReportePEDConsultorasChequear(Map criteria) {
		reporteDAO.executeCargaTemporalReportePEDConsultorasChequear(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeLimpiarTablaTemporalReportePEDConsultorasChequear(java.util.Map)
	 */
	public void executeLimpiarTablaTemporalReportePEDConsultorasChequear(Map criteria) {
		reporteDAO.executeLimpiarTablaTemporalReportePEDConsultorasChequear(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargarTablasReporteCCCDiasCartera(java.util.Map)
	 */
	public void executeCargarTablasReporteCCCDiasCartera(Map params) {
		reporteDAO.executeCargarTablasReporteCCCDiasCartera(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#deleteTablasReporteCCCDiasCartera(java.util.Map)
	 */
	public void deleteTablasReporteCCCDiasCartera(Map params) {
		reporteDAO.deleteTablasReporteCCCDiasCartera(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargaTemporalReporteMAEConsultorasPedidos(java.util.Map)
	 */
	public void executeCargaTemporalReporteMAEConsultorasPedidos(Map criteria) {
		reporteDAO.executeCargaTemporalReporteMAEConsultorasPedidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAEConsultorasPedidosCSV(java.util.Map)
	 */
	public Map generarReporteMAEConsultorasPedidosCSV(Map criteria) {
		return reporteDAO.generarReporteMAEConsultorasPedidosCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#deleteTemporalReporteMAEConsultorasPedidos(java.util.Map)
	 */
	public void deleteTemporalReporteMAEConsultorasPedidos(Map criteria) {
		reporteDAO.deleteTemporalReporteMAEConsultorasPedidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargaReporteRECCuadreSAP(java.util.Map)
	 */
	public void executeCargaReporteRECCuadreSAP(Map params) throws Exception {
		reporteDAO.deleteTemporalReporteRECCuadreSAP(params);
		
		List items = (List)params.get("items");
		reporteDAO.insertBatchReporteRECCuadreSAP(items);
		
		reporteDAO.executeConsolidacionReporteRECCuadreSAP(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReportePEDExportarDemandaAnticipada(java.util.Map)
	 */
	public void executeReportePEDExportarDemandaAnticipada(Map params) {
		reporteDAO.executeReportePEDExportarDemandaAnticipada(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargaReporteCentroAcopioFacturado(java.util.Map)
	 */
	public void executeCargaReporteCentroAcopioFacturado(Map params) {
		reporteDAO.executeCargaReporteCentroAcopioFacturado(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReportePedidosEnviados(java.util.Map)
	 */
	public void executeReportePedidosEnviados(Map params) {
		reporteDAO.executeReportePedidosEnviados(params);	
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReportePosiblesCandidatas(java.util.Map)
	 */
	public void executeReportePosiblesCandidatas(Map params) {
		reporteDAO.executeReportePosiblesCandidatas(params);	
				}
				
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteMAVAtencionCampanha(java.util.Map)
	 */
	public void executeReporteMAVAtencionCampanha(Map criteria){
		
		//llenamos los codigo sap
		this.reporteDAO.deleteTaleReporte("deleteReporteMAVAtencionCampanhaSapTemporal", criteria);
		
		String [] listCodigoSAP = (String [])criteria.get("listCodigoSAP");
		
		if(listCodigoSAP != null && listCodigoSAP.length > 0){
			for(int i = 0; i<listCodigoSAP.length;i++){
				criteria.put("codigoSAP", listCodigoSAP[i]);
				this.reporteDAO.insertTableReportesSql("insertReporteMAVAtencionCampanhaSapTemporal", criteria);	
			}
		}
			
		//Enviamos a una tabla temporal las regiones, zonas y capacitadoras
			
		String[] regiones = (String[])MapUtils.getObject(criteria, "regionListMultiple");
		String[] zonas = (String[])MapUtils.getObject(criteria, "zonaListMultiple");
		
		insertRegionZonaGlobalTemporary(criteria, regiones, zonas);
		
		this.reporteDAO.executeReporteMAVAtencionCampanha(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#esPrimerDiaFacturacion(java.lang.String)
	 */
	public boolean esPrimerDiaFacturacion(String codigoPeriodo) {
		return reporteDAO.esPrimerDiaFacturacion(codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getPedidosAcumulados(java.lang.String)
	 */
	public String getPedidosAcumulados(String codigoPeriodo) {
		return reporteDAO.getPedidosAcumulados(codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargaReporteSimulacionFaltantes(java.util.Map)
	 */
	public void executeCargaReporteSimulacionFaltantes(Map params) {
		reporteDAO.executeCargaReporteSimulacionFaltantes(params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReportePedidosEnviados(java.util.Map)
	 */
	public void executeReportePedidosRecibidos(Map params) {
		reporteDAO.executeReportePedidosRecibidos(params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReportePosiblesCandidatas(java.util.Map)
	 */
	public void executeReporteFacturaDetalle(Map criteria) {
		
	   Map map1=  (Map) criteria.get("parameterMap");
		
	   String[] codRegiones = (String[]) map1.get("codigoRegion");
	   String[] codZonas = (String[]) map1.get("codigoZona");
	   ProcesoMENCargaMasivaInformacionMensajesService service =  (ProcesoMENCargaMasivaInformacionMensajesService) criteria.get("serviceCMIMS");
	  
	     if (codRegiones!=null) {
		    if (codRegiones.length <= 0) {
		    	 codRegiones = null;
			}else{
				if (StringUtils.isEmpty(codRegiones[0])) {
					codRegiones = null;
				 }
			}
	     }if (codZonas!=null) {
	    	 if (codZonas.length <= 0) {
	    		 codZonas = null;
			 }else{
				 if (StringUtils.isEmpty(codZonas[0])) {
					 codZonas = null;
				 }
			 }
		 }
	   
	   Map map  = new HashMap();
	   map.put("repeat", null);
	   
	   Map critera1 = new HashMap();
	   critera1.put("codigoPeriodoOid", String.valueOf(map1.get("oidPeriodo")));
	   critera1.put("fechaInicio", String.valueOf(map1.get("fechaInicio")));
	   critera1.put("fechaFin", String.valueOf(map1.get("fechaFin")));
	   critera1.put("origen", String.valueOf(map1.get("origen")));
	  
	  if (codZonas == null && codRegiones != null) {
	
		  for (int i = 0; i < codRegiones.length; i++) {
			   map.put("codigoRegion", codRegiones[i]);
			   map.put("codigoZona", null);
			   reporteDAO.insertReporteGttFacturaDetalle(map);
		   }
		  
		  reporteDAO.executeReporteFacturaDetalle2(critera1);
	  }else if(codZonas == null && codRegiones == null){
		  reporteDAO.executeReporteFacturaDetalle2(critera1);
	  }
	  else{

	   for (int i = 0; i < codZonas.length; i++) {
		   
		    map.put("codigoZona", codZonas[i]);
			String region = service.getDevuelveCodigoRegionZona(map);
			map.put("codigoRegion", region);
		    reporteDAO.insertReporteGttFacturaDetalle(map);
	   }
	   
	   for (int i = 0; i < codRegiones.length; i++) {
		   map.put("codigoRegion", codRegiones[i]);
		   map.put("codigoZona", null);
		   int countRegiones = reporteDAO.getCountRegionByCodZona(map);
		   if (countRegiones > 0) {
			   map.put("repeat", "R");
			   reporteDAO.insertReporteGttFacturaDetalle(map);
		   }else{
			   reporteDAO.insertReporteGttFacturaDetalle(map);
		   }
	   }
	      reporteDAO.executeReporteFacturaDetalle1(critera1);  
	  } 

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteZONUnidadesGeograficasCSV(java.util.Map)
	 */
	public Map generarReporteZONUnidadesGeograficasCSV(Map criteria) {
		return reporteDAO.generarReporteZONUnidadesGeograficasCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteGISDireccionConsulta(java.util.Map)
	 */
	public void executeReporteGISDireccionConsulta(Map criteria) {
		reporteDAO.executeReporteGISDireccionConsulta(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertConsultaDireccionConsulta(java.util.Map)
	 */
	public void insertConsultaDireccionConsulta(Map criteria) {
		
		reporteDAO.insertConsultaDireccionConsulta(criteria);
	}

	public void deleteConsultaDireccionConsulta() {
		reporteDAO.deleteConsultaDireccionConsulta();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteGISEnviarDireccionConsultorasCSV(java.util.Map)
	 */
	public Map generarReporteGISEnviarDireccionConsultorasCSV(Map criteria) {
		return reporteDAO.generarReporteGISEnviarDireccionConsultorasCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getListaZonasReporteMAENovedadesZona()
	 */
	public List getListaZonasReporteMAENovedadesZona() {

		return reporteDAO.getListaZonasReporteMAENovedadesZona();
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#updateReporteMAENovedadesZona(java.util.Map)
	 */
	public void updateReporteMAENovedadesZona(Map criteria) {
		reporteDAO.updateReporteMAENovedadesZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getTipoConcurso(java.util.Map)
	 */
	public String getTipoConcurso(Map criteria) {
		return this.reporteDAO.getTipoConcurso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(java.util.Map)
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(java.util.Map)
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(
			Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(java.util.Map)
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(
			Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(java.util.Map)
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(
			Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(java.util.Map)
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(
			Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(java.util.Map)
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(
			Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(java.util.Map)
	 */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteMAVEnviosFechaCampConSinFechaCSV(java.util.Map)
	 */
	public Map generarReporteMAVEnviosFechaCampConSinFechaCSV(Map criteria) {
	
		return reporteDAO.generarReporteMAVEnviosFechaCampConSinFechaCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaCSV(
			Map criteria) {
	
		return reporteDAO.generarReporteMAVAtenFechaCampPorConsultoraConFechaCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionCSV(
			Map criteria) {
	
		return reporteDAO.generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionZonaCSV(
			Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionZonaCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraDetalleConSinFechaCSV(
			Map criteria) {
		
		return reporteDAO.generarReporteMAVAtenFechaCampPorConsultoraDetalleConSinFechaCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaCSV(
			Map criteria) {
	
		return reporteDAO.generarReporteMAVAtenFechaCampPorConsultoraSinFechaCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionCSV(
			Map criteria) {
	
		return reporteDAO.generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionZonaCSV(
			Map criteria) {
	
		return reporteDAO.generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionZonaCSV(criteria);
	}

	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionCSV(
			Map criteria) {

		return reporteDAO.generarReporteMAVAtenFechaCampPorGerenteConFechaRegionCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getCodigoPeriodoASD(java.util.Map)
	 */
	public String getCodigoPeriodoASD(Map criteria) {		
		
		return reporteDAO.getCodigoPeriodoASD(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getCodigoPeriodoASD(java.util.Map)
	 */
	public void executeComisionesVentaRetail(Map criteria) {
		 reporteDAO.executeComisionesVentaRetail(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeINCProgramaReconocimiento(java.util.Map)
	 */
	public void executeINCProgramaReconocimiento(Map criteria) {
		reporteDAO.executeINCProgramaReconocimiento(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeINCProgramaReconocimiento2(java.util.Map)
	 */
	public void executeINCProgramaReconocimiento2(Map criteria) {
		reporteDAO.executeINCProgramaReconocimiento2(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteGestionarConsultoraCSV(java.util.Map)
	 */
	public Map generarReporteGestionarConsultoraCSV(Map criteria) {
		return reporteDAO.generarReporteGestionarConsultoraCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCRAZonasFacturaFecha(java.util.Map)
	 */
	public void executeReporteCRAZonasFacturaFecha(Map criteria) {
		reporteDAO.executeReporteCRAZonasFacturaFecha(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteIMPPorceDesviPedido(java.util.Map)
	 */
	public void executeReporteIMPPorceDesviPedido(Map criteria) {
		 reporteDAO.executeReporteIMPPorceDesviPedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCCuponesElectronicos(java.util.Map)
	 */
	public void executeReporteINCCuponesElectronicos(Map criteria) {
		//Enviamos a una tabla temporal las regiones, zonas		
		String[] regiones = (String[])MapUtils.getObject(criteria, "regionList");
		String[] zonas = (String[])MapUtils.getObject(criteria, "zonaList");
				
		List regionesSinZona = new ArrayList();
		
		if(zonas!=null){
			if(zonas.length==1){
				if(StringUtils.isBlank(zonas[0].toString()))
					zonas=null;
			}
		}
		
		if(regiones != null && regiones.length > 0)
		{
			for(int i=0; i<regiones.length; i++)
			{
				boolean regionTieneZona = false;
				String codigoRegion = regiones[i];
				
				if(StringUtils.isNotBlank(codigoRegion)){
					if(zonas != null && zonas.length > 0)
					{
						for(int j=0; j<zonas.length; j++)
						{
							String codigoZona = zonas[j];
							
							if(StringUtils.isNotBlank(codigoZona)){
								if(codigoZona.startsWith(codigoRegion))
								{
									regionTieneZona = true;
									break;
								}
							}
						}
					}
					
					if(!regionTieneZona)
					{
						regionesSinZona.add(codigoRegion);
					}
				}
				
			}
		}
		
		if(regionesSinZona.size() > 0)
		{
			Map params = new HashMap();
			params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoRegion", regionesSinZona.toArray());
			
			List zonasRegionesSolas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", params);
			
			if(zonasRegionesSolas != null && zonasRegionesSolas.size() > 0)
			{
				for(int i=0; i<zonasRegionesSolas.size(); i++)
				{
					Base zona = (Base)zonasRegionesSolas.get(i);
					params.put("codigo", zona.getCodigo());
					params.put("tipoRegionZonaCapacitadora", Constants.MAE_TIPO_REGISTRO_REGION_ZONA);
					reporteDAO.insertGloblalTemporaryForRegionZonaCapacitadora(params);
				}
			}
		}
		
		if(zonas != null && zonas.length > 0)
		{
			for(int j=0; j<zonas.length; j++)
			{
				Map params = new HashMap();
				params.put("codigo", zonas[j]);
				params.put("tipoRegionZonaCapacitadora", Constants.MAE_TIPO_REGISTRO_REGION_ZONA);
				reporteDAO.insertGloblalTemporaryForRegionZonaCapacitadora(params);
			}
		}
		
		this.reporteDAO.executeReporteINCCuponesElectronicos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteINCCuponesElectronicosCSV(java.util.Map)
	 */
	public Map generarReporteINCCuponesElectronicosCSV(Map criteria) {
		return reporteDAO.generarReporteINCCuponesElectronicosCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteCUPNuevaUnidadAtendidaCSV(java.util.Map)
	 */
	public Map generarReporteCUPNuevaUnidadAtendidaCSV(Map criteria) {
		return reporteDAO.generarReporteCUPNuevaUnidadAtendidaCSV(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeEliminarComisionesGerenteRetiradas(java.util.Map)
	 */
	public void executeEliminarComisionesGerenteRetiradas(Map criteria) {
		this.reporteDAO.executeEliminarComisionesGerenteRetiradas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeAuditoriaSaldoCuentasPorCobrar()
	 */
	public void executeAuditoriaSaldoCuentasPorCobrar() {
		this.reporteDAO.executeAuditoriaSaldoCuentasPorCobrar();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteMgpedxdiaCsv1(java.util.Map)
	 */
	public Map executeReporteMgpedxdiaCsv1(Map criteria) {
		
		//Enviamos a una tabla temporal las zonas
		Map result;
		
		String[] regiones = (String[])MapUtils.getObject(criteria, "codigoRegion");
		String[] zonas = (String[])MapUtils.getObject(criteria, "codigoZona");
		
		reporteDAO.deleteTablaRegionZona();
		
		List regionesSinZona = new ArrayList();
		
		if(regiones != null && regiones.length > 0)
		{
			for(int i=0; i<regiones.length; i++)
			{
				boolean regionTieneZona = false;
				String codigoRegion = regiones[i];
				
				if(zonas != null && zonas.length > 0)
				{
					for(int j=0; j<zonas.length; j++)
					{
						String codigoZona = zonas[j];
						if(codigoZona.startsWith(codigoRegion))
						{
							regionTieneZona = true;
							break;
						}
					}
				}
				
				if(!regionTieneZona)
				{
					regionesSinZona.add(codigoRegion);
				}
			}
		}
		
		if(regionesSinZona.size() > 0)
		{
			Map params = new HashMap();
			params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoRegion", regionesSinZona.toArray());
			
			List zonasRegionesSolas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", params);
			
			if(zonasRegionesSolas != null && zonasRegionesSolas.size() > 0)
			{
				for(int i=0; i<zonasRegionesSolas.size(); i++)
				{
					Base zona = (Base)zonasRegionesSolas.get(i);
					params.put("codigoZona", zona.getCodigo());
					reporteDAO.insertTablaRegionZona(params);
				}
			}
		}
		
		if(zonas != null && zonas.length > 0)
		{
			for(int j=0; j<zonas.length; j++)
			{
				Map params = new HashMap();
				params.put("codigoZona", zonas[j]);
				reporteDAO.insertTablaRegionZona(params);
			}
		}
		
		return reporteDAO.executeReporteMgpedxdiaCsv1(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeDeleteInsertZonasDistribucionFacturacionReal(java.util.Map)
	 */
	public void executeDeleteInsertZonasDistribucionFacturacionReal(Map criteria) {
		//Enviamos a una tabla temporal las regiones y zonas
		
		String[] regiones = (String[])MapUtils.getObject(criteria, "regionListMultiple");
		String[] zonas = (String[])MapUtils.getObject(criteria, "zonaListMultiple");
		
		reporteDAO.deleteZonasDistribucionFacturacionReal();
		
		List regionesSinZona = new ArrayList();
		
		if(regiones != null && regiones.length > 0){
			for(int i=0; i<regiones.length; i++){
				boolean regionTieneZona = false;
				String codigoRegion = regiones[i];
				
				if(zonas != null && zonas.length > 0){
					for(int j=0; j<zonas.length; j++){
						String codigoZona = zonas[j];
						
						if(codigoZona.startsWith(codigoRegion)){
							regionTieneZona = true;
							break;
						}
					}
				}
				
				if(!regionTieneZona){
					regionesSinZona.add(codigoRegion);
				}
			}
		}
		
		if(regionesSinZona.size() > 0){
			Map params = new HashMap();
			params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoRegion", regionesSinZona.toArray());
			
			List zonasRegionesSolas = new ArrayList();
			
			if(zonas == null){
				zonasRegionesSolas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", params);
			}else{
				zonasRegionesSolas = null;
			}
			
			if(zonasRegionesSolas != null && zonasRegionesSolas.size() > 0){
				for(int i=0; i<zonasRegionesSolas.size(); i++){
					Base zona = (Base)zonasRegionesSolas.get(i);
					params.put("codigoZona", zona.getCodigo());
					reporteDAO.insertZonasDistribucionFacturacionReal(params);
				}
			}
		}
		
		if(zonas != null && zonas.length > 0){
			for(int j=0; j<zonas.length; j++){
				Map params = new HashMap();
				params.put("codigoZona", zonas[j]);
				reporteDAO.insertZonasDistribucionFacturacionReal(params);
			}
		}
		
		reporteDAO.executeDeleteInsertZonasDistribucionFacturacionReal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteAPEDistribucionCdrsFacturacionReal(java.util.Map)
	 */
	public Map executeReporteAPEDistribucionCdrsFacturacionReal(Map criteria) {
		/*
		//Enviamos a una tabla temporal las zonas
		Map result;
		
		String[] regiones = (String[])MapUtils.getObject(criteria, "codigoRegion");
		String[] zonas = (String[])MapUtils.getObject(criteria, "codigoZona");
		
		reporteDAO.deleteTablaRegionZona();
		
		List regionesSinZona = new ArrayList();
		
		if(regiones != null && regiones.length > 0)
		{
			for(int i=0; i<regiones.length; i++)
			{
				boolean regionTieneZona = false;
				String codigoRegion = regiones[i];
				
				if(zonas != null && zonas.length > 0)
				{
					for(int j=0; j<zonas.length; j++)
					{
						String codigoZona = zonas[j];
						if(codigoZona.startsWith(codigoRegion))
						{
							regionTieneZona = true;
							break;
						}
					}
				}
				
				if(!regionTieneZona)
				{
					regionesSinZona.add(codigoRegion);
				}
			}
		}
		
		if(regionesSinZona.size() > 0)
		{
			Map params = new HashMap();
			params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoRegion", regionesSinZona.toArray());
			
			List zonasRegionesSolas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", params);
			
			if(zonasRegionesSolas != null && zonasRegionesSolas.size() > 0)
			{
				for(int i=0; i<zonasRegionesSolas.size(); i++)
				{
					Base zona = (Base)zonasRegionesSolas.get(i);
					params.put("codigoZona", zona.getCodigo());
					reporteDAO.insertTablaRegionZona(params);
				}
			}
		}
		
		if(zonas != null && zonas.length > 0)
		{
			for(int j=0; j<zonas.length; j++)
			{
				Map params = new HashMap();
				params.put("codigoZona", zonas[j]);
				reporteDAO.insertTablaRegionZona(params);
			}
		}
		*/
		return reporteDAO.executeReporteAPEDistribucionCdrsFacturacionReal(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteAPEIventarioCampo(java.util.Map)
	 */
	@Override
	public Map executeReporteAPEIventarioCampo(Map criteria) {
		String tipoReporte = (String)MapUtils.getObject(criteria, "tipoReporte");
			if(StringUtils.equalsIgnoreCase(tipoReporte, "Resumen")){
				return reporteDAO.executeReporteAPEIventarioCampoResumen(criteria);
			}else{
				return reporteDAO.executeReporteAPEIventarioCampoDetalle(criteria);
			}
		
	}

/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteSACFacturacionDetalle(java.util.Map)
	 */
	public Map executeReporteSACFacturacionDetalle(Map criteria){
		
		//Enviamos a una tabla temporal las zonas
		Map result;
		
		String[] regiones = (String[])MapUtils.getObject(criteria, "codigoRegion");
		String[] zonas = (String[])MapUtils.getObject(criteria, "codigoZona");
		
		reporteDAO.deleteTablaRegionZona();
		
		List regionesSinZona = new ArrayList();
		
		if(regiones != null && regiones.length > 0)
		{
			for(int i=0; i<regiones.length; i++)
			{
				boolean regionTieneZona = false;
				String codigoRegion = regiones[i];
				
				if(zonas != null && zonas.length > 0)
				{
					for(int j=0; j<zonas.length; j++)
					{
						String codigoZona = zonas[j];
						if(codigoZona.startsWith(codigoRegion))
						{
							regionTieneZona = true;
							break;
						}
					}
				}
				
				if(!regionTieneZona)
				{
					regionesSinZona.add(codigoRegion);
				}
			}
		}
		
		if(regionesSinZona.size() > 0)
		{
			Map params = new HashMap();
			params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoRegion", regionesSinZona.toArray());
			
			List zonasRegionesSolas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", params);
			
			if(zonasRegionesSolas != null && zonasRegionesSolas.size() > 0)
			{
				for(int i=0; i<zonasRegionesSolas.size(); i++)
				{
					Base zona = (Base)zonasRegionesSolas.get(i);
					params.put("codigoZona", zona.getCodigo());
					reporteDAO.insertTablaRegionZona(params);
				}
			}
		}
		
		if(zonas != null && zonas.length > 0)
		{
			for(int j=0; j<zonas.length; j++)
			{
				Map params = new HashMap();
				params.put("codigoZona", zonas[j]);
				reporteDAO.insertTablaRegionZona(params);
			}
		}
		
		return reporteDAO.executeReporteSACFacturacionDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteSICDetalleUnidadesAtendidasFaltantes(java.util.Map)
	 */
	public void executeBeforeReporteSICDetalleUnidadesAtendidasFaltantes(Map criteria) {
		reporteDAO.deleteReporteSICDetalleUnidadesAtendidasFaltantes(criteria);
		reporteDAO.insertReporteSICDetalleUnidadesAtendidasFaltantes(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(java.util.Map)
	 */
	public void executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(Map criteria) {
		reporteDAO.executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteMAEConsejerasBloqueadasDesbloqueadas(java.util.Map)
	 */
	public void executeBeforeReporteMAEConsejerasBloqueadasDesbloqueadas(Map criteria) {
		reporteDAO.deleteReporteMAEConsejerasBloqueadasDesbloqueadas(criteria);
		reporteDAO.insertReporteMAEConsejerasBloqueadasDesbloqueadas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(java.util.Map)
	 */
	public void executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(Map criteria) {
		reporteDAO.executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(java.util.Map)
	 */
	public void executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(Map criteria) {
		reporteDAO.executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteCOBCargaMasivaGestionCSV(java.util.Map)
	 */
	public void executeReporteCOBCargaMasivaGestionCSV(Map criteria) {
		reporteDAO.executeReporteCOBCargaMasivaGestionCSV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeBeforeReporteCOBDetalladoRecuperacionCarteraCobrador(java.util.Map)
	 */
	public void insertReporteCOBDetalladCobranza31dias(Map criteria) {
		reporteDAO.deleteReporteCOBDetalladCobranza31dias(criteria);
		reporteDAO.insertReporteCOBDetalladCobranza31dias(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#insertReportePEDSeguimientoConsultora(java.util.Map)
	 */
	public void insertReportePEDSeguimientoConsultora(Map criteria) {
		reporteDAO.deleteReportePEDSeguimientoConsultora(criteria);
		reporteDAO.insertReportePEDSeguimientoConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertReporteAvanceFacturadoProgramaReconocimientoVZ(java.util.Map)
	 */
	public void insertReporteAvanceFacturadoProgramaReconocimientoVZ(Map criteria) {
		reporteDAO.deleteReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(criteria);
		
		String codigoConsultoras[] = (String [])criteria.get("codigoConsultoras");
		if(codigoConsultoras!=null && codigoConsultoras.length>0){
			for(int i=0;i<codigoConsultoras.length;i++){
				criteria.put("codigoCliente", codigoConsultoras[i]);
				reporteDAO.insertReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(criteria);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReportePEDAvanceFacturadoProgramaReconocimientoVZ(java.util.Map)
	 */
	public void executeReportePEDAvanceFacturadoProgramaReconocimientoVZ(Map criteria) {
		reporteDAO.executeReportePEDAvanceFacturadoProgramaReconocimientoVZ(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteCOBDetalladCobranza31diasCSV(java.util.Map)
	 */
	public void executeReporteCOBDetalladCobranza31diasCSV(Map criteria) {
		reporteDAO.executeReporteCOBDetalladCobranza31diasCSV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReportePEDSeguimientoConsultoraCSV(java.util.Map)
	 */
	public void executeReportePEDSeguimientoConsultoraCSV(Map criteria) {
		reporteDAO.executeReportePEDSeguimientoConsultoraCSV(criteria);
	}				
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeBeforeReporteINCPuntObtenidosBolsaFaltantesInc(java.util.Map)
	 */
	public void executeBeforeReporteINCPuntObtenidosBolsaFaltantesInc(Map criteria) {
		reporteDAO.deleteReporteINCPuntObtenidosBolsaFaltantesInc(criteria);
		reporteDAO.insertReporteINCPuntObtenidosBolsaFaltantesInc(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(java.util.Map)
	 */
	public void executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(Map criteria) {
		reporteDAO.executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(criteria);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertReporteCOBSaldosPendientes(java.util.Map)
	 */
	public void insertReporteCOBSaldosPendientes(Map criteria) {
		reporteDAO.deleteReporteCOBSaldosPendientes(criteria);
		reporteDAO.insertReporteCOBSaldosPendientes(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBSaldosPendientesCSV(java.util.Map)
	 */
	public void executeReporteCOBSaldosPendientesCSV(Map criteria) {
		reporteDAO.executeReporteCOBSaldosPendientesCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCLiquidacionCobranzasCSV(java.util.Map)
	 */
	public void executeReporteCCCLiquidacionCobranzasCSV(Map criteria) {
		reporteDAO.executeReporteCCCLiquidacionCobranzasCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarHistoricoOperacionCrediticiaTXT(java.util.Map)
	 */
	public Map generarHistoricoOperacionCrediticiaTXT(Map criteria) {
		return reporteDAO.generarHistoricoOperacionCrediticiaTXT(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeGenerarHistoricoOperacionCrediticia(java.util.Map)
	 */
	public void executeGenerarHistoricoOperacionCrediticia(Map criteria) {
		reporteDAO.executeGenerarHistoricoOperacionCrediticia(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteCOBDetalladoMovRecuperacionIncobrable(java.util.Map)
	 */
	public void executeBeforeReporteCOBDetalladoMovRecuperacionIncobrable(Map criteria) {
		reporteDAO.deleteReporteCOBDetalladoMovRecuperacionIncobrable(criteria);
		reporteDAO.insertReporteCOBDetalladoMovRecuperacionIncobrable(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBDetalladoMovRecuperacionIncobrableCSV(java.util.Map)
	 */
	public void executeReporteCOBDetalladoMovRecuperacionIncobrableCSV(Map criteria) {
		reporteDAO.executeReporteCOBDetalladoMovRecuperacionIncobrableCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteCCCArchivosMediosMagneticos(java.util.Map)
	 */
	public void executeBeforeReporteCCCArchivosMediosMagneticos(Map criteria) {
		reporteDAO.deleteReporteCCCArchivosMediosMagneticos(criteria);
		reporteDAO.insertReporteCCCArchivosMediosMagneticos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeGeneraReporteCCCArchivosMediosMagneticosCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCSV(Map criteria) {
		reporteDAO.executeGeneraReporteCCCArchivosMediosMagneticosCSV(criteria);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteMAEVinculosCliente(java.util.Map)
	 */
	public void executeBeforeReporteMAEVinculosCliente(Map criteria) {
		reporteDAO.deleteReporteMAEVinculosCliente(criteria);
		reporteDAO.insertReporteMAEVinculosCliente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteMAEVinculosClienteCSV(java.util.Map)
	 */
	public void executeReporteMAEVinculosClienteCSV(Map criteria) {
		reporteDAO.executeReporteMAEVinculosClienteCSV(criteria);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCDetalleCuentaCorrienteContableCSV(java.util.Map)
	 */
	public void executeReporteCCCDetalleCuentaCorrienteContableCSV(Map criteria) {
		reporteDAO.executeReporteCCCDetalleCuentaCorrienteContableCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCDetalladoPagoxRegularizarCSV(java.util.Map)
	 */
	public void executeReporteCCCDetalladoPagoxRegularizarCSV(Map criteria) {
		reporteDAO.executeReporteCCCDetalladoPagoxRegularizarCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteINCPuntObtenidosPuntFaltantes(java.util.Map)
	 */
	public void executeBeforeReporteINCPuntObtenidosPuntFaltantes(Map criteria) {
		reporteDAO.executeBeforeReporteINCPuntObtenidosPuntFaltantes(criteria);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCPuntObtenidosPuntFaltantesCSV(java.util.Map)
	 */
	public void executeReporteINCPuntObtenidosPuntFaltantesCSV(Map criteria) {
		reporteDAO.executeReporteINCPuntObtenidosPuntFaltantesCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(java.util.Map)
	 */
	public void executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(Map criteria) {
		reporteDAO.executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteDetalleIFCCSV(java.util.Map)
	 */
	public void executeReporteDetalleIFCCSV(Map criteria) {
		reporteDAO.executeReporteDetalleIFCCSV(criteria);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */
	public void insertHistoricoReporte(HistoricoReporte historicoReporte) {
		reporteDAO.insertHistoricoReporte(historicoReporte);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#updateHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */
	public void updateHistoricoReporte(HistoricoReporte historicoReporte) {
		reporteDAO.updateHistoricoReporte(historicoReporte);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteMAEClasificacionXClienteCSV(java.util.Map)
	 */
	public void executeReporteMAEClasificacionXClienteCSV(Map criteria) {
		reporteDAO.executeReporteMAEClasificacionXClienteCSV(criteria);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getExisteListaProcesoRECEnviarCDRRecepcionados(java.util.Map)
	 */
	public List getExisteListaProcesoRECEnviarCDRRecepcionados(Map criteria) {
		return reporteDAO.getExisteListaProcesoRECEnviarCDRRecepcionados(criteria);
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getListaProcesoRECEnviarCDRRecepcionados(java.util.Map)
	 */
	public List getListaProcesoRECEnviarCDRRecepcionados(Map criteria) {
		return reporteDAO.getListaProcesoRECEnviarCDRRecepcionados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeCargaTemporalReporteZONMovimientoTerritorio(java.util.Map)
	 */
	public void executeCargaTemporalReporteZONMovimientoTerritorio(Map criteria) {
		reporteDAO.executeCargaTemporalReporteZONMovimientoTerritorio(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteZONMovimientoTerritorioCSV(java.util.Map)
	 */
	public Map generarReporteZONMovimientoTerritorioCSV(Map criteria) {
		return reporteDAO.generarReporteZONMovimientoTerritorioCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCGastoCuponCSV(java.util.Map)
	 */
	public void executeReporteCCCGastoCuponCSV(Map criteria) {
		reporteDAO.executeReporteCCCGastoCuponCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#actualizarRegistroAuditoriaReinicioServidor()
	 */
	public void actualizarRegistroAuditoriaReinicioServidor() {
		this.reporteDAO.actualizarRegistroAuditoriaReinicioServidor();
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteSACRecallTrazabilidad(java.util.Map)
	 */
	public Map executeReporteSACRecallTrazabilidad(Map criteria) {
		return reporteDAO.executeReporteSACRecallTrazabilidad(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteINCPuntosConsultora(java.util.Map)
	 */
	public void executeBeforeReporteINCPuntosConsultora(Map params) {
		reporteDAO.executeBeforeReporteINCPuntosConsultora(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCPuntosConsultoraCSV(java.util.Map)
	 */
	public void executeReporteINCPuntosConsultoraCSV(Map params) {
		reporteDAO.executeReporteINCPuntosConsultoraCSV(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteINCPuntosCampania(java.util.Map)
	 */
	public void executeBeforeReporteINCPuntosCampania(Map params) {
		reporteDAO.executeBeforeReporteINCPuntosCampania(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCPuntosCampaniaCSV(java.util.Map)
	 */
	public void executeReporteINCPuntosCampaniaCSV(Map params) {
		reporteDAO.executeReporteINCPuntosCampaniaCSV(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBRetiradasSinDeuda(java.util.Map)
	 */
	public void executeReporteCOBRetiradasSinDeuda(Map params) {
		reporteDAO.executeReporteCOBRetiradasSinDeuda(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeEgresadasSinDeuda(java.util.Map)
	 */
	public void executeEgresadasSinDeuda(Map params) {
		reporteDAO.executeEgresadasSinDeuda(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBPrimerPedidoConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPrimerPedidoConDeuda(Map params) {
		reporteDAO.executeReporteCOBPrimerPedidoConDeuda(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBPedidosFacturadosConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPedidosFacturadosConDeuda(Map params) {
		reporteDAO.executeReporteCOBPedidosFacturadosConDeuda(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBPrimerosSegundoPedidosConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPrimerosSegundoPedidosConDeuda(Map params) {
		reporteDAO.executeReporteCOBPrimerosSegundoPedidosConDeuda(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBPrimerSegundoTercerPedidoConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPrimerSegundoTercerPedidoConDeuda(Map params) {
		reporteDAO.executeReporteCOBPrimerSegundoTercerPedidoConDeuda(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertReporteCCCLiquidacionCobranzasCZ(java.util.Map)
	 */
	public void insertReporteCCCLiquidacionCobranzasCZ(Map params) {
		reporteDAO.insertReporteCCCLiquidacionCobranzasCZ(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertReporteCCCLiquidacionCobranzasDZ(java.util.Map)
	 */
	public void insertReporteCCCLiquidacionCobranzasDZ(Map params) {
		reporteDAO.insertReporteCCCLiquidacionCobranzasDZ(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getIndicadorClienteCedula(java.util.Map)
	 */
	public String getIndicadorClienteCedula(Map criteria) {
		return reporteDAO.getIndicadorClienteCedula(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeBeforeReporteCUPNuevasCupones(java.util.Map)
	 */
	public void executeBeforeReporteCUPNuevasCupones(Map criteria) {
		reporteDAO.executeBeforeReporteCUPNuevasCupones(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCUPNuevasCuponesCSV(java.util.Map)
	 */
	public void executeReporteCUPNuevasCuponesCSV(Map criteria) {
		reporteDAO.executeReporteCUPNuevasCuponesCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getDevuelveIdSgteCodHistoricoReporte()
	 */
	public Long getDevuelveIdSgteCodHistoricoReporte() {
		return reporteDAO.getDevuelveIdSgteCodHistoricoReporte();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteSACControlFacturacionEntregaPedido(java.util.Map)
	 */
	public Map executeReporteSACControlFacturacionEntregaPedido(Map criteria) {
		return reporteDAO.executeReporteSACControlFacturacionEntregaPedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteLETResultado(java.util.Map)
	 */
	public Map executeReporteLETResultado(Map criteria) {
		String regiones[] = (String[])criteria.get("regionList");
		if(regiones!=null && regiones.length>0){
			for(int i=0; i<regiones.length;i++){
				
				if(StringUtils.isNotEmpty(regiones[i]))
				{
					criteria.put("codigoRegion",regiones[i]);
					reporteDAO.insertRegionReporteLETResultado(criteria);
				}
			}
		}
		return reporteDAO.executeReporteLETResultado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteSGRSolicitudesTod(java.util.Map)
	 */
	public Map executeReporteSGRSolicitudesTod(Map criteria) {
		return reporteDAO.executeReporteSGRSolicitudesTod(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getSecuenciaIndicadorControlEntregaNextValue()
	 */
	public String getSecuenciaIndicadorControlEntregaNextValue() {
		return this.reporteDAO.getSecuenciaIndicadorControlEntregaNextValue();
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteLECEnviarReporteProyecion(java.util.Map)
	 */
	public void executeReporteLECEnviarReporteProyecion(Map params) {
				
		String[] regiones = (String[])MapUtils.getObject(params, "codigoRegionList");
		String[] zonas = (String[])MapUtils.getObject(params, "codigoZonaList");
		
		insertRegionZonaGlobalTemporary(params, regiones, zonas);
		
		//Ejecutamos el stored para generar la data (Usa la tabla temporal para filtrar la data de las regiones)
		this.reporteDAO.executeReporteLECEnviarReporteProyecion(params);
		//
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getRegionesReporteLECEnviarReporteProyecion(java.util.Map)
	 */
	public List getRegionesReporteLECEnviarReporteProyecion(Map params) {
		return this.reporteDAO.getRegionesReporteLECEnviarReporteProyecion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getZonasReporteLECEnviarReporteProyecion(java.util.Map)
	 */
	public List getZonasReporteLECEnviarReporteProyecion(Map params) {
		return this.reporteDAO.getZonasReporteLECEnviarReporteProyecion(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeBeforeReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(java.util.Map)
	 */
	public void executeBeforeReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(
			Map criteria) {
		reporteDAO.deleteReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual();
		reporteDAO.insertReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(
			Map criteria) {
		reporteDAO.executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(criteria);		
}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeBeforeReporteCCCAntiguedadSaldos(java.util.Map)
	 */
	public void executeBeforeReporteCCCAntiguedadSaldos(
			Map criteria) {
		reporteDAO.deleteReporteCCCAntiguedadSaldos();
		reporteDAO.insertReporteCCCAntiguedadSaldos(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGeneraReporteCCCAntiguedadSaldosCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCAntiguedadSaldosCSV(
			Map criteria) {
		reporteDAO.executeGeneraReporteCCCAntiguedadSaldosCSV(criteria);		
	}
	
	/**
	 * Inserta todas las zonas de las regiones y/zonas para luego ser usadas en un stored
	 * 
	 * @param criteria
	 * @param regiones
	 * @param zonas
	 */
	private void insertRegionZonaGlobalTemporary(Map criteria, String[] regiones, String[] zonas) {
		List regionesSinZona = new ArrayList();
		
		if(regiones != null && regiones.length > 0)
		{
			for(int i=0; i<regiones.length; i++)
			{
				boolean regionTieneZona = false;
				String codigoRegion = regiones[i];
				
				if(zonas != null && zonas.length > 0)
				{
					for(int j=0; j<zonas.length; j++)
					{
						String codigoZona = zonas[j];
						if(codigoZona.startsWith(codigoRegion))
						{
							regionTieneZona = true;
							break;
						}
					}
				}
				
				if(!regionTieneZona)
				{
					regionesSinZona.add(codigoRegion);
				}
			}
		}
		
		if(regionesSinZona.size() > 0)
		{
			Map params = new HashMap();
			params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			params.put("codigoRegion", regionesSinZona.toArray());
			
			List zonasRegionesSolas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", params);
			
			if(zonasRegionesSolas != null && zonasRegionesSolas.size() > 0)
			{
				for(int i=0; i<zonasRegionesSolas.size(); i++)
				{
					Base zona = (Base)zonasRegionesSolas.get(i);
					params.put("codigo", zona.getCodigo());
					params.put("tipoRegionZonaCapacitadora", Constants.MAE_TIPO_REGISTRO_REGION_ZONA);
					reporteDAO.insertGloblalTemporaryForRegionZonaCapacitadora(params);
				}
			}
		}
		
		if(zonas != null && zonas.length > 0)
		{
			for(int j=0; j<zonas.length; j++)
			{
				Map params = new HashMap();
				params.put("codigo", zonas[j]);
				params.put("tipoRegionZonaCapacitadora", Constants.MAE_TIPO_REGISTRO_REGION_ZONA);
				reporteDAO.insertGloblalTemporaryForRegionZonaCapacitadora(params);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCampanyaBonoRegion(java.util.Map)
	 */
	public String getCampanyaBonoRegion(Map criteria) {
		return reporteDAO.getCampanyaBonoRegion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCampanyaBonoZona(java.util.Map)
	 */
	public String getCampanyaBonoZona(Map criteria) {
		return reporteDAO.getCampanyaBonoZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCampanyaRecaudoRegion(java.util.Map)
	 */
	public String getCampanyaRecaudoRegion(Map criteria) {
		return reporteDAO.getCampanyaRecaudoRegion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCampanyaRecaudoZona(java.util.Map)
	 */
	public String getCampanyaRecaudoZona(Map criteria) {
		return reporteDAO.getCampanyaRecaudoZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#getCodigoPeriodoAnterior(java.lang.String)
	 */
	public String getCodigoPeriodoAnterior(String codigoPeriodo) {
		return reporteDAO.getCodigoPeriodoAnterior(codigoPeriodo);
	}

	public void executeReporteCCCDetalladoConsultorasIncobrable(Map criteria) {
		// TODO Auto-generated method stub
		this.reporteDAO.executeReporteCCCDetalladoConsultorasIncobrable(criteria);
	}

	public Map executeReporteCCCDetalladoConsultorasIncobrableCSV(Map criteria) {
		// TODO Auto-generated method stub
		return reporteDAO.executeReporteCCCDetalladoConsultorasIncobrableCSV(criteria);
	}

	public void executeDeleteInsertCCCDetalladoConsultorasIncobrable(Map params) {
		// TODO Auto-generated method stub
		this.reporteDAO.deleteReporteCCCDetalladoConsultorasIncobrable(params);
		
	}

	public Map executeReporteCCCDetalladoProvisionIncobrableCSV(Map criteria) {
		// TODO Auto-generated method stub
		return reporteDAO.executeReporteCCCDetalladoProvisionIncobrableCSV(criteria);
	}

	public Map executeReporteCCCDetalladoProvisionIncobrableMovCSV(Map criteria) {
		// TODO Auto-generated method stub
		return reporteDAO.executeReporteCCCDetalladoProvisionIncobrableMovCSV(criteria);
	}

	public void executeReporteCCCConsolidadoRecaudoCampana(Map params) {
		// TODO Auto-generated method stub
		reporteDAO.executeReporteCCCConsolidadoRecaudoCampana(params);
	}

	public void deleteReporteCCCConsolidadoRecaudoCampana(Map params) {
		// TODO Auto-generated method stub
		reporteDAO.deleteReporteCCCConsolidadoRecaudoCampana(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeReporteZONTerritorioUnidadGeograficaCSV(java.util.Map)
	 */
	public void executeReporteZONTerritorioUnidadGeograficaCSV(Map params) {
		reporteDAO.executeReporteZONTerritorioUnidadGeograficaCSV(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(java.util.Map)
	 */
	public void executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(Map params) {
		reporteDAO.executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(java.util.Map)
	 */
	public void executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(Map params) {
		reporteDAO.executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGeneraReporteCCCRegistroVentasBoliviaCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCRegistroVentasBoliviaCSV(Map criteria) {
		reporteDAO.executeGeneraReporteCCCRegistroVentasBoliviaCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeGeneraReporteCCCRegistroAbonosBoliviaCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCRegistroAbonosBoliviaCSV(Map criteria) {
		reporteDAO.executeGeneraReporteCCCRegistroAbonosBoliviaCSV(criteria);
	}
	
	
	public Map executeReporteCCCBuroCreditoCSV(Map criteria) {
		return reporteDAO.executeReporteCCCBuroCreditoCSV(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#eliminarRegistrosTablaTemporalReporProyeccion(java.util.Map)
	 */
	public void eliminarRegistrosTablaTemporalReporProyeccion(Map criteria) {
		this.reporteDAO.eliminarRegistrosTablaTemporalReporProyeccion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteVENCabecerasFacturasAnuladasCSV(java.util.Map)
	 */
	@Override
	public Map generarReporteVENCabecerasFacturasAnuladasCSV(Map criteria) {
		return reporteDAO.generarReporteVENCabecerasFacturasAnuladasCSV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteVENDetalleNCMarcaUNCSV(java.util.Map)
	 */
	@Override
	public Map generarReporteVENDetalleNCMarcaUNCSV(Map criteria) {
		return reporteDAO.generarReporteVENDetalleNCMarcaUNCSV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteVENDetalleNDebitoMarcaUNCSV(java.util.Map)
	 */
	@Override
	public Map generarReporteVENDetalleNDebitoMarcaUNCSV(Map criteria) {
		return reporteDAO.generarReporteVENDetalleNDebitoMarcaUNCSV(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#generarReporteVENDetalleProductosAtendidosUNCSV(java.util.Map)
	 */
	@Override
	public Map generarReporteVENDetalleProductosAtendidosUNCSV(Map criteria) {
		return reporteDAO.generarReporteVENDetalleProductosAtendidosUNCSV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteAntigNotasCredi(java.util.Map)
	 */
	@Override
	public void executeReporteAntigNotasCredi(Map criteria) {
		reporteDAO.executeReporteAntigNotasCredi(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBPrimerSegundoTercerCuartoPedidoConDeuda(java.util.Map)
	 */
	@Override
	public void executeReporteCOBPrimerSegundoTercerCuartoPedidoConDeuda(Map params) {
		reporteDAO.executeReporteCOBPrimerSegundoTercerCuartoPedidoConDeuda(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteSACTIMImpositivoAduana(java.util.Map)
	 */
	public void executeReporteSACTIMImpositivoAduana(Map params) {
		reporteDAO.executeReporteSACTIMImpositivoAduana(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertarReporteSACAtendidoxFechaConsolidado(java.util.Map)
	 */
	public void insertarReporteSACAtendidoxFechaConsolidado(Map params) {
		reporteDAO.insertarReporteSACAtendidoxFechaConsolidado(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertarReporteSACAtendidoxFechaDetallado(java.util.Map)
	 */
	public void insertarReporteSACAtendidoxFechaDetallado(Map params) {
		reporteDAO.insertarReporteSACAtendidoxFechaDetallado(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getCampanyaRecaudoRegionZona(java.util.Map)
	 */
	@Override
	public String getCampanyaRecaudoRegionZona(Map criteria) {
		return reporteDAO.getCampanyaRecaudoRegionZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#getExisteCUV(java.util.Map)
	 */
	public int getExisteCUV(Map criteria){
		return reporteDAO.getExisteCUV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteConsultoraPuntajeUbicacion(java.util.Map)
	 */
	public void executeReporteConsultoraPuntajeUbicacion(Map params) {
		reporteDAO.executeReporteConsultoraPuntajeUbicacion(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCodigosInexistentes(java.util.Map)
	 */
	public void executeReporteCodigosInexistentes(Map params) {
		reporteDAO.executeReporteCodigosInexistentes(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertarReporteRECListadoDeudaPendPeriodoConsolidado(java.util.Map)
	 */
	public void insertarReporteRECListadoDeudaPendPeriodoConsolidado(Map params) {
		reporteDAO.insertarReporteRECListadoDeudaPendPeriodoConsolidado(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertarReporteRECListadoDeudaPendPeriodoDetallado(java.util.Map)
	 */
	public void insertarReporteRECListadoDeudaPendPeriodoDetallado(Map params) {
		reporteDAO.insertarReporteRECListadoDeudaPendPeriodoDetallado(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#insertReportePEDDetallePedidosFacturadosPorCodigoSAP(java.util.Map)
	 */
	@Override
	public void insertReportePEDDetallePedidosFacturadosPorCodigoSAP(Map params) {
		reporteDAO.deleteCodigoSAPReportePEDDetallePedidosFacturados();
		
		String listCodigoSAP[] = (String[])params.get("listCodigoSAP");
		if(listCodigoSAP!=null && listCodigoSAP.length>0){
			for(int i = 0; i < listCodigoSAP.length; i++){
				
				if(StringUtils.isNotEmpty(listCodigoSAP[i])){
					params.put("codigosSAP",listCodigoSAP[i]);
					reporteDAO.insertCodigoSAPReportePEDDetallePedidosFacturados(params);
				}
			}
		}
		
		String codigoSAP = MapUtils.getString(params, "condicionSAP2");
		if(StringUtils.isNotBlank(codigoSAP)){
			params.put("codigosSAP", codigoSAP);
			reporteDAO.insertCodigoSAPReportePEDDetallePedidosFacturados(params);
		}
		
		reporteDAO.insertReportePEDDetallePedidosFacturadosPorCodigoSAP(params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteVENFacturaAuditoriaConso(java.util.Map)
	 */
	public void executeReporteVENFacturaAuditoriaConso(Map params) {
		reporteDAO.executeReporteVENFacturaAuditoriaConso(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteVENFacturaAuditoriaDetal(java.util.Map)
	 */
	public void executeReporteVENFacturaAuditoriaDetal(Map params) {
		reporteDAO.executeReporteVENFacturaAuditoriaDetal(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCPRDetalleCuentaCorriente(java.util.Map)
	 */
	@Override
	public void executeReporteCCCPRDetalleCuentaCorriente(Map criteria) {
		reporteDAO.executeReporteCCCPRDetalleCuentaCorriente(criteria);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteConfiguracionOfertasConcurso(java.util.Map)
	 */
	public void executeReporteConfiguracionOfertasConcurso(Map params) {
		reporteDAO.executeReporteConfiguracionOfertasConcurso(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteConfiguracionOfertasN(java.util.Map)
	 */
	public void executeReporteConfiguracionOfertasN(Map params) {
		reporteDAO.executeReporteConfiguracionOfertasN(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBNumeroPagosCampanna(java.util.Map)
	 */
	public void executeReporteCOBNumeroPagosCampanna(Map params) {
		reporteDAO.executeReporteCOBNumeroPagosCampanna(params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCOBNumeroPagosCampannaCSV(java.util.Map)
	 */
	public void executeReporteCOBNumeroPagosCampannaCSV(Map params) {
		reporteDAO.executeReporteCOBNumeroPagosCampannaCSV(params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteCCCInteresCCorrienteCSV(java.util.Map)
	 */
	public Map executeReporteCCCInteresCCorrienteCSV(Map criteria) {
		return reporteDAO.executeReporteCCCInteresCCorrienteCSV(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeGenerarReporteCCCInteresCCorriente(java.util.Map)
	 */
	public void executeGenerarReporteCCCInteresCCorriente(Map params) {
		reporteDAO.executeGenerarReporteCCCInteresCCorriente(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCDetallePuntosRegionZonaByNacionalCSV(java.util.Map)
	 */
	public void executeReporteINCDetallePuntosRegionZonaByNacionalCSV(Map params) {
		reporteDAO.executeReporteINCDetallePuntosRegionZonaByNacionalCSV(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCDetallePuntosRegionZonaByRegionCSV(java.util.Map)
	 */
	public void executeReporteINCDetallePuntosRegionZonaByRegionCSV(Map params) {
		reporteDAO.executeReporteINCDetallePuntosRegionZonaByRegionCSV(params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCDetallePuntosRegionZonaByZonaCSV(java.util.Map)
	 */
	public void executeReporteINCDetallePuntosRegionZonaByZonaCSV(Map params) {
		reporteDAO.executeReporteINCDetallePuntosRegionZonaByZonaCSV(params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCDetallePuntosRegionZonaByConsultoraCSV(java.util.Map)
	 */
	public void executeReporteINCDetallePuntosRegionZonaByConsultoraCSV(Map params) {
		reporteDAO.executeReporteINCDetallePuntosRegionZonaByConsultoraCSV(params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCDetallePuntosRegionZonaByCampaniaCSV(java.util.Map)
	 */
	public void executeReporteINCDetallePuntosRegionZonaByCampaniaCSV(Map params) {
		reporteDAO.executeReporteINCDetallePuntosRegionZonaByCampaniaCSV(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCProvisionContableIngresosCSV(java.util.Map)
	 */
	public void executeReporteINCProvisionContableIngresosCSV(Map params) {
		reporteDAO.executeReporteINCProvisionContableIngresosCSV(params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReporteINCProvisionContableGastosCSV(java.util.Map)
	 */
	public void executeReporteINCProvisionContableGastosCSV(Map params) {
		reporteDAO.executeReporteINCProvisionContableGastosCSV(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeDeleteInsertZonasInventarioCampo(java.util.Map)
	 */
	@Override
	public void executeDeleteInsertZonasInventarioCampo(Map criteria) {
		//Enviamos a una tabla temporal las regiones y zonas
		
				String[] regiones = (String[])MapUtils.getObject(criteria, "regionListMultiple");
				String[] zonas = (String[])MapUtils.getObject(criteria, "zonaListMultiple");
				
				reporteDAO.deleteZonasReporteInventarioCampo();
				
				List regionesSinZona = new ArrayList();
				
				if(regiones != null && regiones.length > 0){
					for(int i=0; i<regiones.length; i++){
						boolean regionTieneZona = false;
						String codigoRegion = regiones[i];
						
						if(zonas != null && zonas.length > 0){
							for(int j=0; j<zonas.length; j++){
								String codigoZona = zonas[j];
								
								if(codigoZona.startsWith(codigoRegion)){
									regionTieneZona = true;
									break;
								}
							}
						}
						
						if(!regionTieneZona){
							regionesSinZona.add(codigoRegion);
						}
					}
				}
				
				if(regionesSinZona.size() > 0){
					Map params = new HashMap();
					params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
					params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					params.put("codigoRegion", regionesSinZona.toArray());
					
					List zonasRegionesSolas = new ArrayList();
					
					if(zonas == null){
						zonasRegionesSolas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", params);
					}else{
						zonasRegionesSolas = null;
					}
					
					if(zonasRegionesSolas != null && zonasRegionesSolas.size() > 0){
						for(int i=0; i<zonasRegionesSolas.size(); i++){
							Base zona = (Base)zonasRegionesSolas.get(i);
							params.put("codigoZona", zona.getCodigo());
							reporteDAO.insertZonasReporteInventarioCampo(params);
						}
					}
				}
				
				if(zonas != null && zonas.length > 0){
					for(int j=0; j<zonas.length; j++){
						Map params = new HashMap();
						params.put("codigoZona", zonas[j]);
						reporteDAO.insertZonasReporteInventarioCampo(params);
					}
				}
				
				
				String tipoReporte = (String)MapUtils.getObject(criteria, "tipoReporte");
				
				if(StringUtils.isNotEmpty(tipoReporte)&&StringUtils.isNotBlank(tipoReporte)){
					if(StringUtils.equalsIgnoreCase(tipoReporte, "Resumen")){
						reporteDAO.executeDeleteInsertZonasInventarioCampoResumen(criteria);
					}else{
						reporteDAO.executeDeleteInsertZonasInventarioCampoDetalle(criteria);
					}
				}
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReportePERResumenDiarioPercepcionesSunatCSV(java.util.Map)
	 */
	public Map executeReportePERResumenDiarioPercepcionesSunatCSV(Map params) {
		return reporteDAO.executeReportePERResumenDiarioPercepcionesSunatCSV(params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ReporteService#executeReportePERResumenDiarioPercepcionesSunatTXT(java.util.Map)
	 */	
	public Map executeReportePERResumenDiarioPercepcionesSunatTXT(Map params) {
		return reporteDAO.executeReportePERResumenDiarioPercepcionesSunatTXT(params);		
	}	
	
}