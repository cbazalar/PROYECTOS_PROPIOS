/**
 * 
 */
package biz.belcorp.ssicc.dao.scsicc.ibatis;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.scsicc.bean.InformeOCRPedidoBean;
import biz.belcorp.ssicc.dao.sisicc.model.BaseBlob;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 
 * <p>
 * <a href="ReporteDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Marco Antonio Agurto Jimenez</a>
 * 
 */
@Repository("scsicc.reporteDAO")
public class ReporteDAOiBatis extends BaseDAOiBatis implements ReporteDAO {
	public List getMarcas() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getMarcas", null);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCanalesByCodigoISO(java.lang.String)
	 */
	public List getCanalesByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCanalesByCodigoISO", codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.dao.InterfazSiCCDAO#getCanalesRolByCodigoISO(java.lang.String)
	 */
	public List getCanalesRolByCodigoISO(String codigo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getCanalesRolByCodigoISO", codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTiposVinculosByPais(java.lang.String)
	 */
	public List getTiposVinculosByPais(String codigoPais) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getTiposVinculosByPais", codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getRangosPeriodo()
	 */
	public List getRangosPeriodo() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getRangosPeriodo", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeFacturaPendienteSeccion(java.util.Map)
	 */
	public void executeFacturaPendienteSeccion(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeFacturaPendienteSeccion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCuadreSAPFI(java.util.Map)
	 */
	public void executeCuadreSAPFI(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeCuadreSAPFI", criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeControlAsistencia(java.util.Map)
	 */
	public void executeControlAsistencia(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeControlAsistencia", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeControlAsistenciaTriunfadoras(java.util.Map)
	 */
	public void executeControlAsistenciaTriunfadoras(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeControlAsistenciaTriunfadoras", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeEstadoCtaCteVendedora(java.util.Map)
	 */
	public void executeEstadoCtaCteVendedora(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeEstadoCtaCteVendedora", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeDetalleCtaCte(java.util.Map)
	 */
	public void executeDetalleCtaCte(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeDetalleCtaCte", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeFacturaPendienteCampana(java.util.Map)
	 */
	public void executeFacturaPendienteCampana(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeFacturaPendienteCampana", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteTaleReporte(java.lang.String,
	 *      java.util.Map)
	 */
	public void deleteTaleReporte(String method, Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL." + method,
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertTableReportesSql(java.lang.String,
	 *      java.util.Map)
	 */
	public void insertTableReportesSql(String method, Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL." + method,
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeBaseRecuperacionCampana(java.util.Map)
	 */
	public void executeBaseRecuperacionCampana(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeBaseRecuperacionCampana", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeVentaVariable(java.util.Map)
	 */
	public void executeVentaVariable(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeVentaVariable", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getListaPeriodosByBasCtrlFact(java.util.Map)
	 */
	public List getListaPeriodosByBasCtrlFact(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getListaPeriodosByBasCtrlFact", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getListaGenerico(java.lang.String,
	 *      java.util.Map)
	 */
	public List getListaGenerico(String getLista, Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL." + getLista, criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getListaReporte(java.lang.String,
	 *      java.util.Map)
	 */
	public List getListaReporte(String getLista, Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL." + getLista, criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteSQL(java.lang.String,
	 *      java.util.Map)
	 */
	public void executeReporteSQL(String procedure, Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL." + procedure,
				criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeComisionIngreso(java.util.Map)
	 */
	public void executeComisionIngreso(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeComisionIngreso", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeComisionRecuperacion(java.util.Map)
	 */
	public void executeComisionRecuperacion(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeComisionRecuperacion", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeComisionComercializacion(java.util.Map)
	 */
	public void executeComisionComercializacion(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeComisionComercializacion", criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getDatosConcursosByNumeroConcurso(java.util.Map)
	 */
	public Map getDatosConcursosByNumeroConcurso(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL.getDatosConcursosByNumeroConcurso",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getParametrosReporteGeneral(java.util.Map)
	 */
	public Map getParametrosReporteGeneral(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getParametrosReporteGeneral", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getParametrosReporte(java.util.Map)
	 */
	public Map getParametrosReporte(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getParametrosReporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteLogReporteDisco(java.util.Map)
	 */
	public void deleteLogReporteDisco(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteLogReporteDisco", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getLogReporteDisco(java.util.Map)
	 */
	public List getLogReporteDisco(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getLogReporteDisco", criteria);
	}
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertLogReporteDisco(java.util.Map)
     */
    public void insertLogReporteDisco(Map criteria) {
    	getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertLogReporteDisco", criteria);
    }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidString(java.lang.String,
	 *      java.util.Map)
	 */
	public String getOidString(String string, Map criteria) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL." + string, criteria);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidConcurso(java.lang.String,
	 *      java.util.Map)
	 */
	public String getOidConcurso(String string, Map criteria) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL." + string, criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getStringGenerico(java.lang.String, java.util.Map)
	 */
	public String getStringGenerico(String string, Map criteriaOperacion) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.GenericoSQL." + string, criteriaOperacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeComisionRecuperacionGZona(java.util.Map)
	 */
	public void executeComisionRecuperacionGZona (Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeComisionRecuperacionGZona", criteria);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#devuelvePorcentajeTramoComision(java.util.Map)
	 */
	public void devuelvePorcentajeTramoComision (Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.devuelvePorcentajeTramoComision", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getConsultaResponsablesUA(java.util.Map)
	 */
	public List getConsultaResponsablesUA (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getConsultaResponsablesUA", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executePrevioReporteControlCliente(java.util.Map)
	 */
	public void executePrevioReporteControlCliente (Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.executePrevioReporteControlCliente", criteria);
	}
	
	public BaseBlob getLogoEmpresaEducacion(Map criteria) {
		return (BaseBlob) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getLogoEmpresaEducacion", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeAnalisisArea(java.util.Map)
	 */
	public void executeAnalisisArea(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeAnalisisArea", criteria);
	}
	
	public String getSecuenciaNextValue() {
		
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getSecuenciaNextValue",null);
	}

	public List getPeriodosCorporativosPorTipo(String tipoPeriodo) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodosCorporativosPorTipo", tipoPeriodo);
	}

	public void executeSeguimientoCalificacionCampana (Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeSeguimientoCalificacionCampana", criteria);
	}

	public List getCampanasTramo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getCampanasTramo", criteria);
	}
	
	public List getPeriodosSeguimientoCalificacionCampana(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getPeriodosSeguimientoCalificacionCampana", criteria);
	}
	
	public void executeResumenEvaluacionSeccion (Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeResumenEvaluacionSeccion", criteria);
	}
	public void executeResumenEvaluacionZona (Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeResumenEvaluacionZona", criteria);
	}

	public void executePrevioReporteControlClienteLbel (Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.executePrevioReporteControlClienteLbel", criteria);
	}
	public List devuelvePeriodosporFechas(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.devuelvePeriodosporFechas", criteria);
	}
	
	public List getUnidadNegocio(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getUnidadNegocio");
	}
	public Map getCabeceraSACFaltante(Map params){
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getCabeceraSACFaltante", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCodUsuarios()
	 */
	public List getCodUsuarios(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getCodUsuarios");
	}
	
	public List getResultadoList(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getResultadoList");
	}

	public List getMotivoList(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getMotivoList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeIndicadorGestionIncentivosCDR(java.util.Map)
	 */
	public void executeIndicadorGestionIncentivosCDR (Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeIndicadorGestionIncentivosCDR", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTiposPrograma(java.util.Map)
	 */
	public List getTiposPrograma(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTiposPrograma", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executePremiosEntregados(java.util.Map)
	 */
	public void executePremiosEntregados (Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executePremiosEntregados", criteria);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getZonasFacturanHoy (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getZonasFacturanHoy", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoOfertas()
	 */
	public List getTipoOfertas() {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoOfertasList",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getParamReporGener(java.util.HashMap)
	 */
	public String getParamReporGener(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getParamReporGener",criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getIndicadoresSAC(java.util.Map)
	 */
	public List getIndicadoresSAC(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getIndicadoresSAC",null);
	}	

	/**
	 * @param method
	 * @return
	 */
	public String getSecuenciaNextValue(String method) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL." + method,null);
	}
	
	/**
	 * @param criteria
	 */
	public void executeIndicadoresSAC (Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeIndicadoresSAC", criteria);
	}

	
	/**
	 * @param criteria
	 * Metodo que carga la tabla para generar el reporte de FNA Soles
	 */
	public void executeCargaReporteFNASoles (Map criteria) {		
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargaReporteFNASoles", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getPeriodosResultadosEvaluacionEjecutiva(java.util.Map)
	 */
	public Map getPeriodosResultadosEvaluacionEjecutiva(
			Map<String, String> params) {
		return (Map)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOMSQL.getPeriodosResultadosEvaluacionEjecutiva",params);
		
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getIndicadorCajaBolsaProducto (Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getIndicadorCajaBolsaProducto", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getMensajeReporteRecuperaciones(java.util.Map)
	 */
	public String getMensajeReporteRecuperaciones(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getMensajeReporteRecuperaciones", criteria);
	}
	
	public String getMensajeReporteReemplazos(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getMensajeReporteReemplazos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getMensajeReporteGuiaProductos(java.util.Map)
	 */
	public String getMensajeReporteGuiaProductos(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getMensajeReporteGuiaProductos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getListaZonasReporteSACAsistencia()
	 */
	public List getListaZonasReporteSACAsistencia(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getListaZonasReporteSACAsistenciaCompartamosEsika",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getListaZonasReporteSACActivasSaldo()
	 */
	public List getListaZonasReporteSACActivasSaldo(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getListaZonasReporteSACActivasSaldo",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getEstadoEquivalenciaCliente()
	 */
	public List getEstadoEquivalenciaCliente(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getEstadoEquivalenciaCliente",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getContadorListaRegionesZonas()
	 */
	public String getContadorListaRegionesZonas(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getContadorListaRegionesZonas", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTiposCDR()
	 */
	public List getTiposCDR(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTiposCDR",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTiposAtencion()
	 */
	public List getTiposAtencion(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTiposAtencion",null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getRangoFechaPeriodo(java.lang.String)
	 */
	public List getRangoFechaPeriodo(String codPeriodo) {
		return getSqlMapClientTemplate().
		queryForList("sisicc.ReportesSQL.getRangoFechaPeriodo",codPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getMaximoValorOferta(java.util.Map)
	 */
	public String getMaximoValorOferta(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getMaximoValorOferta", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getMensajexValorMaximoOferta(java.util.Map)
	 */
	public String getMensajexValorMaximoOferta(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getMensajexValorMaximoOferta", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeSTOConsolidadoAtencionCDR(java.util.Map)
	 */
	public void executeSTOConsolidadoAtencionCDR (Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeSTOConsolidadoAtencionCDR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidClasificacion()
	 */
	public String getOidClasificacion(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidClasificacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReportePremioConcurso(java.util.Map)
	 */
	public void executeReporteProyeccionesPremioConcurso(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteProyeccionesPremioConcurso",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertGloblanTemporaryForNumNivel(java.util.Map)
	 */
	public void insertGloblanTemporaryForNumNivel(Map params) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertGloblanTemporaryForNumNivel", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getEstadoAtencionesMAV(java.lang.String)
	 */
	public List getEstadoAtencionesMAV(String oidPeriodo) {
		return getSqlMapClientTemplate().
		queryForList("sisicc.ReportesSQL.getEstadoAtencionesMAV",oidPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getNoPasaronPedidosMAV(java.util.Map)
	 */
	public List getNoPasaronPedidosMAV(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("sisicc.ReportesSQL.getNoPasaronPedidosMAV",criteria);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidFacturacionTipoDocumento(java.util.Map)
	 */
	public List getOidFacturacionTipoDocumento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getOidFacturacionTipoDocumento",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeSICPedidosAfectadosDetFNA(java.util.Map)
	 */
	public void executeSICPedidosAfectadosDetFNA (Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeSICPedidosAfectadosDetFNA", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTiposSolicitudSTO()
	 */
	public List getTiposSolicitudSTO(){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTiposSolicitudSTO",null);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidConcursoByNumConc(java.lang.String)
	 */
	public String getOidConcursoByNumConc(String codigoConcurso) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getOidConcursoByNumConc",codigoConcurso);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getResultadoChequeoConsultora(java.util.Map)
	 */
	public List getResultadoChequeoConsultora(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getResultadoChequeoConsultora", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getDetalleResultadoChequeo(java.util.Map)
	 */
	public List getDetalleResultadoChequeo(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getDetalleResultadoChequeo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getConsultaInformeAvancePedido(java.util.Map)
	 */
	public List getConsultaInformeAvancePedido(Map criteria) {
		List consolidadoInforme=new ArrayList();
		List informe1 = getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getConsultaInformeAvancePedido1", criteria);
		List informe2 = getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getConsultaInformeAvancePedido2", criteria);
		List informe3 = getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getConsultaInformeAvancePedido3", criteria);
		List informe4 = getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getConsultaInformeAvancePedido4", criteria);
		int index=0;
		Iterator it =informe1.iterator();
		while(it.hasNext()){
			InformeOCRPedidoBean pedidoBean=(InformeOCRPedidoBean)it.next();
			InformeOCRPedidoBean pedidoBean2=(InformeOCRPedidoBean) informe2.get(index);
			InformeOCRPedidoBean pedidoBean3=(InformeOCRPedidoBean) informe3.get(index);
			InformeOCRPedidoBean pedidoBean4=(InformeOCRPedidoBean) informe4.get(index);
			
			pedidoBean.setEstado(pedidoBean2.getEstado());
			pedidoBean.setTelefono1(pedidoBean2.getTelefono1());
			pedidoBean.setTelefono2(pedidoBean2.getTelefono2());
			pedidoBean.setDireccion(pedidoBean2.getDireccion());
			pedidoBean.setIndicadorRecepcionDigiDist(pedidoBean2.getIndicadorRecepcionDigiDist());
			pedidoBean.setIndicadorRecepcionWeb(pedidoBean2.getIndicadorRecepcionWeb());
			pedidoBean.setIndicadorRecepcionOcr(pedidoBean2.getIndicadorRecepcionOcr());
			pedidoBean.setIndicadorErrorDeuda(pedidoBean2.getIndicadorErrorDeuda());
			pedidoBean.setSaldoDeudor(pedidoBean2.getSaldoDeudor());
			pedidoBean.setIndicadorAdmiCartera(pedidoBean3.getIndicadorAdmiCartera());
			pedidoBean.setObservacionPrueba(pedidoBean3.getObservacionPrueba());			
			pedidoBean.setTipoOrden(pedidoBean3.getTipoOrden());
			pedidoBean.setCodigoErrados(pedidoBean3.getCodigoErrados());
			pedidoBean.setMontoPedido(pedidoBean3.getMontoPedido());
			pedidoBean.setMontoPedidoSinFa(pedidoBean3.getMontoPedidoSinFa());
			pedidoBean.setMontoOcr(pedidoBean3.getMontoOcr());
			pedidoBean.setMontoWeb(pedidoBean3.getMontoWeb());
			pedidoBean.setMontoDigitacionDistr(pedidoBean3.getMontoDigitacionDistr());
			pedidoBean.setNumeroFa(pedidoBean4.getNumeroFa());
			pedidoBean.setFacturado(pedidoBean4.getFacturado());
			pedidoBean.setErrorMontoMinimo(pedidoBean4.getErrorMontoMinimo());
			pedidoBean.setErrorMontoMaximo(pedidoBean4.getErrorMontoMaximo());
			pedidoBean.setMontoPedidoBloq(pedidoBean4.getMontoPedidoBloq());
			pedidoBean.setMontoMinimo(pedidoBean4.getMontoMinimo());
			pedidoBean.setMontoMaximo(pedidoBean4.getMontoMaximo());
			pedidoBean.setPedidosEstimados(pedidoBean4.getPedidosEstimados());
						
			consolidadoInforme.add(pedidoBean);
			index++;
		}
		
		return consolidadoInforme;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getConsultorasActivasSinPedido(java.util.Map)
	 */
	public List getConsultorasActivasSinPedido(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getConsultorasActivasSinPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getFechaUltimaActualizacionZona(java.util.Map)
	 */
	public String getFechaUltimaActualizacionZona(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getFechaUltimaActualizacionZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getNumeroRegistros(java.util.Map)
	 */
	public Integer getNumeroRegistros(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getNumeroRegistros", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getInformeAvancePedido(java.util.Map)
	 */
	public List getInformeAvancePedido(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getInformeAvancePedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCodigoVentasRechazados(java.util.Map)
	 */
	public List getCodigoVentasRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getCodigoVentasRechazados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getDetallePedidoFacturado(java.util.Map)
	 */
	public List getDetallePedidoFacturado(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getDetallePedidoFacturado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getFaltantesAnunciados(java.util.Map)
	 */
	public List getFaltantesAnunciados(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getFaltantesAnunciados", criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVSunatTXT(java.util.Map)
     */
    public Map generarReporteRUVSunatTXT(Map criteria) {
	   getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeGenerarReporteRUVSunatTXT", criteria);
	   return criteria;
    }
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVSunatCSV(java.util.Map)
     */
    public Map generarReporteRUVSunatCSV(Map criteria) {
    	getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeGenerarReporteRUVSunatCSV", criteria);
	   return criteria;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoBloqueos()
	 */
	public List getTipoBloqueos() {
		if (log.isDebugEnabled()) log.info("Entro a ReporteDAOiBatis - getTipoBloqueos()");
		List lista = getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoBloqueos", "");
		if (log.isDebugEnabled()) log.info("Salio a ReporteDAOiBatis - getTipoBloqueos() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVSunatErrorCSV(java.util.Map)
	 */
	public Map generarReporteRUVSunatErrorCSV(Map criteria) {
		if (log.isDebugEnabled()) log.info("Entro a ReporteDAOiBatis - generarReporteRUVSunatErrorCSV(Map)");
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteRUVSunatErrorCSV", criteria);
		if (log.isDebugEnabled()) log.info("Salio a ReporteDAOiBatis - generarReporteRUVSunatErrorCSV(Map)");
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVSunatErrorTXT(java.util.Map)
	 */
	public Map generarReporteRUVSunatErrorTXT(Map criteria) {
		if (log.isDebugEnabled()) log.info("Entro a ReporteDAOiBatis - generarReporteRUVSunatErrorTXT(Map)");
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteRUVSunatErrorTXT", criteria);
		if (log.isDebugEnabled()) log.info("Salio a ReporteDAOiBatis - generarReporteRUVSunatErrorTXT(Map)");
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getReporteOCRPedidosGP1SinError(java.util.Map)
	 */
	public List getReporteOCRPedidosGP1SinError(Map criteria) {				
		if (log.isDebugEnabled())log.info("Entro a ReporteDAOiBatis - getReporteOCRPedidosGP1SinError(Map)");		
		String tipoSolicitud =  (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getTipoSolicitud", criteria);
		criteria.put("tipoSolicitud", tipoSolicitud);
		if (log.isDebugEnabled()) log.info("getReporteOCRPedidosGP1SinError - tipoSolicitud: " +tipoSolicitud );
		List lista = getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getReporteOCRPedidosGP1SinError", criteria);
		if (log.isDebugEnabled()) log.info("Salio a ReporteDAOiBatis - getReporteOCRPedidosGP1SinError(Map) - Resultado:"+lista.size());
		return lista;
	}
    
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVSunatExcel(java.util.Map)
	 */
	public Map generarReporteRUVSunatExcel(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteRUVSunatExcel", criteria);
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getReportePRIGenerarSolicitudesPrivilege(java.util.Map)
	 */
	public List getReportePRIGenerarSolicitudesPrivilege(Map criteria) {
		if (log.isDebugEnabled()) log.info("Entro a ReporteDAOiBatis - getReportePRIGenerarSolicitudesPrivilege(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getReportePRIGenerarSolicitudesPrivilege", criteria);
		if (log.isDebugEnabled()) log.info("Salio a ReporteDAOiBatis - getReportePRIGenerarSolicitudesPrivilege(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempMapaAnaquel(java.util.Map)
	 */
	public String getObtenerSecuenciaTempMapaAnaquel(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempMapaAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteMapaAnaquelBalanceoDiario(java.util.Map)
	 */
	public void executeGenerarReporteMapaAnaquelBalanceoDiario(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteMapaAnaqBD", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTempReporteMapaAnaq(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteMapaAnaq(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalReporteMapaAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempBalanceoLinea(java.util.Map)
	 */
	public String getObtenerSecuenciaTempBalanceoLinea(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempBalanceoLinea", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteBalanceoLinea(java.util.Map)
	 */
	public void executeGenerarReporteBalanceoLinea(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteBalanceoLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTemporalReporteBalanceoLinea(java.util.Map)
	 */
	public void eliminarRegistrosTablaTemporalReporteBalanceoLinea(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalReporteBalanceoLinea", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCampanhaActivaByZona(java.util.Map)
	 */
	public String getCampanhaActivaByZona(Map criteriaPeriodo) {
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getCampanhaActivaByZona", criteriaPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidRegionByPaisMarcaCanal(java.util.Map)
	 */
	public Integer getOidRegionByPaisMarcaCanal(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getOidRegionByPaisMarcaCanal", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeRetencionPedidos(java.util.Map)
	 */
	public void executeRetencionPedidos(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeRetencionPedidos", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getRetencionPedidos(java.util.Map)
	 */
	public List getRetencionPedidos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getRetencionPedidos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executePedidosDigitados(java.util.Map)
	 */
	public void executePedidosDigitados(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executePedidosDigitados", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getPedidosDigitados(java.util.Map)
	 */
	public List getPedidosDigitados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getPedidosDigitados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidZonaByPaisMarcaCanalRegion(java.util.Map)
	 */
	public Integer getOidZonaByPaisMarcaCanalRegion(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getOidZonaByPaisMarcaCanalRegion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidSeccionByPaisMarcaCanalZona(java.util.Map)
	 */
	public Integer getOidSeccionByPaisMarcaCanalZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getOidSeccionByPaisMarcaCanalZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getZonasGrupo(java.lang.String)
	 */
	public List getZonasGrupo(String grupoFacturacion) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getZonasGrupo", grupoFacturacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getActividad(java.util.Map)
	 */
	public List getActividad(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getActividad", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getGrupoFacturacion(java.util.Map)
	 */
	public List getGrupoFacturacion(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getGrupoFacturacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeActualizarTipoPedido()
	 */
	public void executeActualizarTipoPedido() {
		log.info("Entro a ReporteDAOiBatis - executeActualizarTipoPedido");
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeActualizarTipoPedido", "");
		log.info("Salio a ReporteDAOiBatis - executeActualizarTipoPedido");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getConsultorasActivasSinPedidoAct(java.util.Map)
	 */
	public List getConsultorasActivasSinPedidoAct(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getConsultorasActivasSinPedidoAct", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getInformeAvancePedidoAct(java.util.Map)
	 */
	public List getInformeAvancePedidoAct(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getInformeAvancePedidoAct", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTiposSolicitudReclamos()
	 */
	public List getTiposSolicitudReclamos() {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getTiposSolicitudReclamos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidPeriodo(java.util.Map)
	 */
	public int getOidPeriodo(Map criteria) {
		log.info("Entro a ReporteDAOiBatis - getOidPeriodo(java.util.Map)");
		int resultado = ((Integer)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidPeriodo", criteria)).intValue();
		log.info("Salio a ReporteDAOiBatis - getOidPeriodo(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getIndicadoresCajaBolsa()
	 */
	public List getIndicadoresCajaBolsa() {
		log.info("Entro a ReporteDAOiBatis - getIndicadoresCajaBolsa()");
		List lista = getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getIndicadoresCajaBolsa");
		log.info("Salio a ReporteDAOiBatis - getIndicadoresCajaBolsa() - Resultado:" + lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidActividad(java.util.Map)
	 */
	public String getOidActividad(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidActividad", criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertTemporalSTOReporteRechazadas(java.util.Map)
     */
    public void insertTemporalSTOReporteRechazadas(Map params){    	
		log.info("Entro a ReporteDAOiBatis - Insert Temporal Reporte Rechazadas");
    	getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertTemporalSTOReporteRechazadas", params);
		log.info("Salio a ReporteDAOiBatis - Insert Temporal Reporte Rechazadas");
	}
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempSTORechazadas(java.util.Map)
	 */
	public String getObtenerSecuenciaTempSTORechazadas(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempSTORechazadas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTempSTORechazadas(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempSTORechazadas(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalSTORechazadas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getValidarMontoTope(java.util.Map)
	 */
	public Double getValidarMontoTope(Map criteria) {
		log.info("Entro a ReporteDAOiBatis - getValidarMontoTope(java.util.Map)");
		Double resultado = (Double)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getValidarMontoTope", criteria);
		log.info("Salio a ReporteDAOiBatis - getValidarMontoTope(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempCOSCierreCostoVentas(java.util.Map)
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoVentas(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempCOSCierreCostoVentas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteCierreCostoVentas(java.util.Map)
	 */
	public void executeGenerarReporteCierreCostoVentas(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteCierreCostoVentas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTempReporteCierreCostoVentas(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoVentas(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteTempCierreCostoVentas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempCOSCierreCostoDevoluciones(java.util.Map)
	 */
	public String getObtenerSecuenciaTempCOSCierreCostoDevoluciones(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempCOSCierreCostoDevoluciones", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteCierreCostoDevoluciones(java.util.Map)
	 */
	public void executeGenerarReporteCierreCostoDevoluciones(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteCierreCostoDevoluciones", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteCierreCostoDevoluciones(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteTempCierreCostoDevoluciones", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getProcesoInformeAvancePedido(java.util.Map)
	 */
	public List getProcesoInformeAvancePedido(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getProcesoInformeAvancePedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getProcesoInformeAvancePedidoAct(java.util.Map)
	 */
	public List getProcesoInformeAvancePedidoAct(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getProcesoInformeAvancePedidoAct", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVLibroVentasMensualCSV(java.util.Map)
	 */
	public Map generarReporteRUVLibroVentasMensualCSV(Map criteria) {
		
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteRUVLibroVentasMensualCSV",criteria);
		
		return criteria;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteVENDetalleNCMarcaUNCSV(java.util.Map)
	 */
	public Map generarReporteVENDetalleNCMarcaUNCSV(Map criteria) {		
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteVENDetalleNCMarcaUNCSV",criteria);		
		return criteria;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#generarReporteVENDetalleNDebitoMarcaUNCSV(java.util.Map)
	 */
	public Map generarReporteVENDetalleNDebitoMarcaUNCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteVENDetalleNDebitoMarcaUNCSV",criteria);		
		return criteria;
	}	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteVENCabecerasFacturasAnuladasCSV(java.util.Map)
	 */
	public Map generarReporteVENCabecerasFacturasAnuladasCSV(Map criteria) {
		
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteVENCabecerasFacturasAnuladasCSV",criteria);
		
		return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteVENDetalleProductosAtendidosUNCSV(java.util.Map)
	 */
	public Map generarReporteVENDetalleProductosAtendidosUNCSV(Map criteria) {
		
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteVENDetalleProductosAtendidosUNCSV",criteria);
		
		return criteria;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVLibroVentasMensualCSV(java.util.Map)
	 */
	public Map generarReportePEDBonificacionCSV(Map criteria) {
		
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReportePEDBonificacionCSV",criteria);
		
		return criteria;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTituloReportePaisMarca(java.util.Map)
	 */
	public String getTituloReportePaisMarca(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getTituloReportePaisMarca", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getLIDRegion(java.util.Map)
	 */
	public List getLIDRegion(Map criteria) {
		log.info("Entro a ReporteDAOiBatis - getLIDRegion(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getLIDRegion", criteria);
		log.info("Salio a ReporteDAOiBatis - getLIDRegion(java.util.Map) - Resultado:" + lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getLIDSeccion(java.util.Map)
	 */
	public List getLIDSeccion(Map criteria) {
		log.info("Entro a ReporteDAOiBatis - getLIDSeccion(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getLIDSeccion", criteria);
		log.info("Salio a ReporteDAOiBatis - getLIDSeccion(java.util.Map) - Resultado:" + lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getLIDValidaPeriodosConcurso(java.util.Map)
	 */
	public List getLIDValidaPeriodosConcurso(Map criteria) {
		log.info("Entro a ReporteDAOiBatis - getLIDValidaPeriodosConcurso(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getLIDValidaPeriodosConcurso", criteria);
		log.info("Salio a ReporteDAOiBatis - getLIDValidaPeriodosConcurso(java.util.Map) - Resultado:" + lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getLIDZona(java.util.Map)
	 */
	public List getLIDZona(Map criteria) {
		log.info("Entro a ReporteDAOiBatis - getLIDZona(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getLIDZona", criteria);
		log.info("Salio a ReporteDAOiBatis - getLIDZona(java.util.Map) - Resultado:" + lista);
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getParametroGenericoSistemaMail(java.util.Map)
	 */
	public List getParametroGenericoSistemaMail(Map criteriaParam) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getParametroGenericoSistemaMail", criteriaParam);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoDocumentoList()
	 */
	public List getTipoDocumentoList() {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoDocumentoList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidTipoDocumento()
	 */
	public String getOidTipoDocumento(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidTipoDocumento",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempDesctoComercial(java.util.Map)
	 */
	public String getObtenerSecuenciaTempDesctoComercial(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempDsctoComercial", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteSAPFiDescuentoComercial(java.util.Map)
	 */
	public void executeGenerarReporteSAPFiDescuentoComercial(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteSAPFiDescuentoComercial", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTempReporteDescuentoComercial(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoComercial(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalReporteDescuentoComercial", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecTempDsctoVol(java.util.Map)
	 */
	public String getObtenerSecTempDsctoVol(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempDsctoVol", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteSAPFiDescuentoVolumen(java.util.Map)
	 */
	public void executeGenerarReporteSAPFiDescuentoVolumen(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteSAPFiDescuentoVolumen", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTempReporteDescuentoVolumen(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteDescuentoVolumen(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalReporteDescuentoVolumen", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempVentaLinea(java.util.Map)
	 */
	public String getObtenerSecuenciaTempVentaLinea(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempVentaLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteSAPFiVentaLinea(java.util.Map)
	 */
	public void executeGenerarReporteSAPFiVentaLinea(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteSAPFiVentaLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTempReporteVentaLinea(java.util.Map)
	 */
	public void eliminarRegistrosTablaTempReporteVentaLinea(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalReporteVentaLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempOperaReclam(java.util.Map)
	 */
	public String getObtenerSecuenciaTempOperaReclam(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempOperaReclam", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteRECOperaReclaPedidos(java.util.Map)
	 */
	public void executeGenerarReporteRECOperaReclaPedidos(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteRECOperaReclaPedidos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTemporalReporOperaReclaPedidos(java.util.Map)
	 */
	public void eliminarRegistrosTablaTemporalReporOperaReclaPedidos(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalReporOperaReclaPedidos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoReporteList()
	 */
	public List getTipoReporteList(){
		return this.getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoReporteList");
	}
	
	public List getTipoReporteList(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoReporteList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteMAEConsultoraBloquedasDesbloq(java.util.Map)
	 */
	public void executeGenerarReporteMAEConsultoraBloquedasDesbloq(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteMAEConsultoraBloquedasDesbloq", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getObtenerSecuenciaTempConsultBloqDesb()
	 */
	public String getObtenerSecuenciaTempConsultBloqDesb(){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getObtenerSecuenciaTempConsultBloqDesb");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTemporalReporConsultBloqDesb()
	 */
	public void eliminarRegistrosTablaTemporalReporConsultBloqDesb(){
		this.getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalReporConsultBloqDesb");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoPlantilla(java.util.Map)
	 */
	public String getTipoPlantilla(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getTipoPlantilla", criteria);
	}
	
	public List getListaAlmacen(Map criteria) {
		return getSqlMapClientTemplate()
				.queryForList("sisicc.ReportesSQL.getListaAlmacen",criteria);
	}
	
	public List getOrigenSTOByTipoDocumento(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getOrigenSTOByTipoDocumento", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTiposOperaMultipleByOperaxCodigoOid(java.util.Map)
	 */
	public List getTiposOperaMultipleByOperaxCodigoOid(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTiposOperaMultipleByOperaxCodigoOid", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getControlAsistencia(java.util.Map)
	 */
	public List getControlAsistencia(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getControlAsistencia", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidProcesoControlAsistencia()
	 */
	public BigDecimal getOidProcesoControlAsistencia() {
		return (BigDecimal)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidProcesoControlAsistencia");
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidProcesoControlAsistenciaTriunfadoras()
	 */
	public BigDecimal getOidProcesoControlAsistenciaTriunfadoras() {
		return (BigDecimal)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidProcesoControlAsistenciaTriunfadoras");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getOidPaisByCodigoPais(java.util.Map)
	 */
	public String getOidPaisByCodigoPais(Map params) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getOidPaisByCodigoPais", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteMAEConsultorasPedidos(java.util.Map)
	 */
	public void executeGenerarReporteMAEConsultorasPedidos(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteMAEConsultorasPedidos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getDetallePedidoNoFacturado(java.util.Map)
	 */
	public List getDetallePedidoNoFacturado(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ReportesSQL.getDetallePedidoNoFacturado", criteria);
	}

	/* INI SA RCR PER-SiCC-2013-0003 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getDiferenciaPeriodos(java.util.Map)
	 */
	public Integer getDiferenciaPeriodos(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
					"sisicc.ReportesSQL.getDiferenciaPeriodos", criteria);		
	}
	/* FIN SA RCR PER-SiCC-2013-0003 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeComisionesCalculadasPorRegion(java.util.Map)
	 */
	public void executeComisionesCalculadasPorRegion(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeComisionesCalculadasPorRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCalcularComisionVentaNetaEfectiva(java.util.Map)
	 */
	public void executeCalcularComisionVentaNetaEfectiva(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeCalcularComisionVentaNetaEfectiva", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCalcularVentaZona(java.util.Map)
	 */
	public void executeCalcularVentaZona(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeCalcularVentaZona", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoReporte()
	 */
	public List getTipoReporte() {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoReporte", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoMav()
	 */
	public List getTipoMav() {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoMav", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getActividadByTipoMAV(java.lang.String)
	 */
	public List getActividadByTipoMAV(String codigoTipoMav) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getActividadByTipoMAV", codigoTipoMav);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoOfertaByTipoMAVActividad(java.util.Map)
	 */
	public List getTipoOfertaByTipoMAVActividad(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoOfertaByTipoMAVActividad", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoOferta()
	 */
	public List getTipoOferta() {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getTipoOferta", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(java.util.Map)
	 */
	public void executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargarTablasReporteCCCDeudorasConMasUnaCampanha", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(java.util.Map)
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(java.util.Map)
	 */
	public void executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray", criteria);
	}
	
	public void executeReporteCCCPrimPedDeud(Map map) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCPrimPedDeud", map);
	}
	
	public void executeReporteCCCPrimSegPedDeud(Map map) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCPrimSegPedDeud", map);
	}
	
	public void deleteTableReporteCCCPrimPedDeud(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTableReporteCCCPrimPedDeudTempoPrime", criteria);
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTableReporteCCCPrimPedDeudTempoSaldo", criteria);
	}	
	
	public void deleteTableReporteCCCPrimSegPedDeud(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTableReporteCCCPrimSegPedDeudTempoPrime", criteria);
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTableReporteCCCPrimSegPedDeudTempoSaldo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCargaTemporalReportePEDConsultorasChequear(java.util.Map)
	 */
	public void executeCargaTemporalReportePEDConsultorasChequear(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargaTemporalReportePEDConsultorasChequear", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeLimpiarTablaTemporalReportePEDConsultorasChequear(java.util.Map)
	 */
	public void executeLimpiarTablaTemporalReportePEDConsultorasChequear(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.executeLimpiarTablaTemporalReportePEDConsultorasChequear", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCargarTablasReporteCCCDiasCartera(java.util.Map)
	 */
	public void executeCargarTablasReporteCCCDiasCartera(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargarTablasReporteCCCDiasCartera", params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteTablasReporteCCCDiasCartera(java.util.Map)
	 */
	public void deleteTablasReporteCCCDiasCartera(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablasReporteCCCDiasCartera", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCargaTemporalReporteMAEConsultorasPedidos(java.util.Map)
	 */
	public void executeCargaTemporalReporteMAEConsultorasPedidos(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargaTemporalReporteMAEConsultorasPedidos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAEConsultorasPedidosCSV(java.util.Map)
	 */
	public Map generarReporteMAEConsultorasPedidosCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteMAEConsultorasPedidosCSV",criteria);
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteTemporalReporteMAEConsultorasPedidos(java.util.Map)
	 */
	public void deleteTemporalReporteMAEConsultorasPedidos(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.deleteTemporalReporteMAEConsultorasPedidos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteTemporalReporteRECCuadreSAP(java.util.Map)
	 */
	public void deleteTemporalReporteRECCuadreSAP(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.deleteTemporalReporteRECCuadreSAP", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertBatchReporteRECCuadreSAP(java.util.List)
	 */
	public void insertBatchReporteRECCuadreSAP(final List list) throws Exception {
		try{	
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			
			public Object doInSqlMapClient(SqlMapExecutor executor)
				throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsewrt " + dataInsert);
					getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteRECCuadreSAP", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out.println("rows afftected by insertBatchReporteRECCuadreSAP: " + rowsaffected);
				return null;
			}
		});
			
		}catch(Exception e){
			throw new Exception(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeConsolidacionReporteRECCuadreSAP(java.util.Map)
	 */
	public void executeConsolidacionReporteRECCuadreSAP(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeConsolidacionReporteRECCuadreSAP", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReportePEDExportarDemandaAnticipada(java.util.Map)
	 */
	public void executeReportePEDExportarDemandaAnticipada(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePEDExportarDemandaAnticipada", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getZonasRegionPEJ(java.util.Map)
	 */
	public List getZonasRegionPEJ(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getZonasRegionPEJ", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getSeccionesZonaPEJ(java.util.Map)
	 */
	public List getSeccionesZonaPEJ(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getSeccionesZonaPEJ", params);
	}
	
	public List getSeccionesZonaPEJTodos(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getSeccionesZonaPEJTodos", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCargaReporteCentroAcopioFacturado(java.util.Map)
	 */
	public void executeCargaReporteCentroAcopioFacturado(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargaReporteCentroAcopioFacturado", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReportePedidosEnviados(java.util.Map)
	 */
	public void executeReportePedidosEnviados(Map params){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePedidosEnviados", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReportePosiblesCandidatas(java.util.Map)
	 */
	public void executeReportePosiblesCandidatas(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePosiblesCandidatas", params);	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteMAVAtencionCampanha(java.util.Map)
	 */
	public void executeReporteMAVAtencionCampanha(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeReporteAtencionFechaCampanha", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#esPrimerDiaFacturacion(java.lang.String)
	 */
	public boolean esPrimerDiaFacturacion(String codigoPeriodo) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"sisicc.ReportesSQL.getEsPrimerDiaFacturacion", codigoPeriodo);
        
        if(result != null)
        	return true;
        else
        	return false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getPedidosAcumulados(java.lang.String)
	 */
	public String getPedidosAcumulados(String codigoPeriodo) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getPedidosAcumulados", codigoPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCargaReporteSimulacionFaltantes(java.util.Map)
	 */
	public void executeCargaReporteSimulacionFaltantes(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargaReporteSimulacionFaltantes", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReportePedidosRecibidos(java.util.Map)
	 */
	public void executeReportePedidosRecibidos(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePedidosRecibidos", params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteGttFacturaDetalle(java.util.Map)
	 */
	public void insertReporteGttFacturaDetalle(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteGttFacturaDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCountRegionByCodZona(java.util.Map)
	 */
	public int getCountRegionByCodZona(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getCountRegionByCodZona", criteria);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteFacturaDetalle1(java.util.Map)
	 */
	public void executeReporteFacturaDetalle1(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteFacturaDetalle1", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteFacturaDetalle2(java.util.Map)
	 */
	public void executeReporteFacturaDetalle2(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteFacturaDetalle2", params);
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteRUVSunatCSV(java.util.Map)
     */
    public Map generarReporteZONUnidadesGeograficasCSV(Map criteria) {
    	getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeGenerarReporteZONUnidadesGeograficasCSV", criteria);
	   return criteria;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteGISDireccionConsulta(java.util.Map)
     */
	public void executeReporteGISDireccionConsulta(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteGISDireccionConsulta", criteria);
		
	}

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeConsultaDireccionConsulta(java.util.Map)
     */
	public void insertConsultaDireccionConsulta(Map criteria) {
		 getSqlMapClientTemplate().insert(
				"sisicc.ReportesSQL.insertConsultaDireccionConsulta", criteria);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteTaleReporte(java.lang.String,
	 *      java.util.Map)
	 */
	public void deleteConsultaDireccionConsulta() {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteConsultaDireccionConsulta", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertGloblalTemporaryForRegionZonaCapacitadora(java.util.Map)
	 */
	public void insertGloblalTemporaryForRegionZonaCapacitadora(Map params) {
		 getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertGloblalTemporaryForRegionZonaCapacitadora", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteGISEnviarDireccionConsultorasCSV(java.util.Map)
	 */
	public Map generarReporteGISEnviarDireccionConsultorasCSV(Map criteria) {
    	getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeGenerarReporteGISEnviarDireccionConsultorasCSV", criteria);
	   return criteria;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getListaZonasReporteMAENovedadesZona()
	 */
	public List getListaZonasReporteMAENovedadesZona() {
		
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getListaZonasReporteMAENovedadesZona");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.dao.ProcesoSACGenerarReporteDAO#updateReporteMAENovedadesZona(java.util.Map)
	 */
	public void updateReporteMAENovedadesZona(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.updateReporteMAENovedadesZona",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getTipoConcurso(java.util.Map)
	 */
	public String getTipoConcurso(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getTipoConcurso", criteria);
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(java.util.Map)
     */
    public Map generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(Map criteria) {
    	getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteConFechaCSV", criteria);
	   return criteria;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(java.util.Map)
     */
    public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(Map criteria) {
    	getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteConFechaCSV", criteria);
	   return criteria;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(java.util.Map)
     */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteDetalleConSinFechaTipoCargoCSV", criteria);
	   return criteria;
	}

	 /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(java.util.Map)
     */
	public Map generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteDetalleConSinFechaCSV", criteria);
	   return criteria;
	}

	 /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(java.util.Map)
     */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteSinFechaRegionCSV", criteria);
	   return criteria;
	}
	
	 /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(java.util.Map)
     */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteSinFechaRegionZonaCSV", criteria);
	   return criteria;
	}
	
	 /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(java.util.Map)
     */
	public Map generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteSinFechaCSV", criteria);
	   return criteria;
	}
	
	 /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteMAVEnviosFechaCampConSinFechaCSV(java.util.Map)
     */
	public Map generarReporteMAVEnviosFechaCampConSinFechaCSV(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVEnviosConSinFechaCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionConsultoraConFechaCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionConsultoraConFechaRegionCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionZonaCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionConsultoraConFechaRegionZonaCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraDetalleConSinFechaCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionConsultoraDetalleConSinFechaCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionConsultoraSinFechaCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionConsultoraSinFechaRegionCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionZonaCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionConsultoraSinFechaRegionZonaCSV", criteria);
	   return criteria;
	}

	public Map generarReporteMAVAtenFechaCampPorGerenteConFechaRegionCSV(
			Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeReporteMAVAtencionGerenteConFechaRegionCSV", criteria);
	   return criteria;
	}

	 /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCodigoPeriodoASD(java.util.Map)
     */
	public String getCodigoPeriodoASD(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getCodigoPeriodoASD",criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeComisionesVentaRetail(java.util.Map)
	 */
	public void executeComisionesVentaRetail(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOMSQL.executeComisionesVentaRetail", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeINCProgramaReconocimiento(java.util.Map)
	 */
	public void executeINCProgramaReconocimiento(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeINCProgramaReconocimiento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeINCProgramaReconocimiento2(java.util.Map)
	 */
	public void executeINCProgramaReconocimiento2(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeINCProgramaReconocimiento2", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteGestionarConsultoraCSV(java.util.Map)
	 */
	public Map generarReporteGestionarConsultoraCSV(Map criteria) {
    	getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeGenerarReporteGestionarConsultoraCSV", criteria);
	   return criteria;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCRAZonasFacturaFecha(java.util.Map)
	 */
	public void executeReporteCRAZonasFacturaFecha(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCRAZonasFacturaFecha", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteIMPPorceDesviPedido(java.util.Map)
	 */
	public void executeReporteIMPPorceDesviPedido(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteIMPPorceDesviPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteINCCuponesElectronicos(java.util.Map)
	 */
	public void executeReporteINCCuponesElectronicos(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCCuponesElectronicos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteINCCuponesElectronicosCSV(java.util.Map)
	 */
	public Map generarReporteINCCuponesElectronicosCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteINCCuponesElectronicosCSV", criteria);
	   return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteCUPNuevaUnidadAtendidaCSV(java.util.Map)
	 */
	public Map generarReporteCUPNuevaUnidadAtendidaCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCUPNuevaUnidadAtendidaCSV", criteria);
	   return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeEliminarComisionesGerenteRetiradas(java.util.Map)
	 */
	public void executeEliminarComisionesGerenteRetiradas(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeEliminarComisionesGerenteRetiradas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeAuditoriaSaldoCuentasPorCobrar()
	 */
	public void executeAuditoriaSaldoCuentasPorCobrar() {
		getSqlMapClientTemplate().update(
				"sisicc.ReportesSQL.executeAuditoriaSaldoCuentasPorCobrar");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertTablaRegionZona(java.util.Map)
	 */
	public void insertTablaRegionZona(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertTablaRegionZona", criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteMgpedxdiaCsv1(java.util.Map)
	 */
	public Map executeReporteMgpedxdiaCsv1(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteMgpedxdiaCsv1", params);
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteTablaRegionZona()
	 */
	public void deleteTablaRegionZona(){
		this.getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaRegionZona");
	}	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteZonasDistribucionFacturacionReal()
	 */
	public void deleteZonasDistribucionFacturacionReal() {
		getSqlMapClientTemplate().insert("spusicc.ape.ProcesosAPESQL.deleteZonasDistribucionFacturacionReal", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertZonasDistribucionFacturacionReal(java.util.Map)
	 */
	public void insertZonasDistribucionFacturacionReal(Map params) {
		getSqlMapClientTemplate().insert("spusicc.ape.ProcesosAPESQL.insertZonasDistribucionFacturacionReal", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#deleteZonasReporteInventarioCampo()
	 */
	@Override
	public void deleteZonasReporteInventarioCampo() {
		getSqlMapClientTemplate().delete("spusicc.ape.ProcesosAPESQL.deleteZonasReporteInventarioCampo", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertZonasReporteInventarioCampo(java.util.Map)
	 */
	@Override
	public void insertZonasReporteInventarioCampo(Map params) {
		getSqlMapClientTemplate().insert("spusicc.ape.ProcesosAPESQL.insertZonasReporteInventarioCampo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeDeleteInsertZonasDistribucionFacturacionReal(java.util.Map)
	 */
	public void executeDeleteInsertZonasDistribucionFacturacionReal(Map params) {
		getSqlMapClientTemplate().update("spusicc.ape.ProcesosAPESQL.executeDeleteInsertZonasDistribucionFacturacionReal", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeDeleteInsertZonasInventarioCampoResumen(java.util.Map)
	 */
	@Override
	public void executeDeleteInsertZonasInventarioCampoResumen(Map params) {
		getSqlMapClientTemplate().update("spusicc.ape.ProcesosAPESQL.executeDeleteInsertZonasInventarioCampoResumen", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeDeleteInsertZonasInventarioCampoDetalle(java.util.Map)
	 */
	@Override
	public void executeDeleteInsertZonasInventarioCampoDetalle(Map params) {
		getSqlMapClientTemplate().update("spusicc.ape.ProcesosAPESQL.executeDeleteInsertZonasInventarioCampoDetalle", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteAPEDistribucionCdrsFacturacionReal(java.util.Map)
	 */
	public Map executeReporteAPEDistribucionCdrsFacturacionReal(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteAPEDistribucionCdrsFacturacionReal", params);
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteAPEIventarioCampoResumen(java.util.Map)
	 */
	@Override
	public Map executeReporteAPEIventarioCampoResumen(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteAPEIventarioCampoResumen", params);
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteAPEIventarioCampoDetalle(java.util.Map)
	 */
	@Override
	public Map executeReporteAPEIventarioCampoDetalle(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteAPEIventarioCampoDetalle", params);
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteSACFacturacionDetalle(java.util.Map)
	 */
	public Map executeReporteSACFacturacionDetalle(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteSACFacturacionDetalle", params);
		return params;
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReporteSICDetalleUnidadesAtendidasFaltantes(java.util.Map)
     */
    public void deleteReporteSICDetalleUnidadesAtendidasFaltantes(Map criteria) {
    	getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteSICDetalleUnidadesAtendidasFaltantes", criteria);
    }
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteSICDetalleUnidadesAtendidasFaltantes(java.util.Map)
     */
    public void insertReporteSICDetalleUnidadesAtendidasFaltantes(Map criteria) {
    	getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteSICDetalleUnidadesAtendidasFaltantes", criteria);
    	
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(java.util.Map)
     */
    public void executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(Map criteria) {
    	getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteSICDetalleUnidadesAtendidasFaltantesCSV", criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReporteMAEConsejerasBloqueadasDesbloqueadas(java.util.Map)
     */
    public void deleteReporteMAEConsejerasBloqueadasDesbloqueadas(Map criteria) {
    	getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteMAEConsejerasBloqueadasDesbloqueadas", criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteMAEConsejerasBloqueadasDesbloqueadas(java.util.Map)
	 */
	public void insertReporteMAEConsejerasBloqueadasDesbloqueadas(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteMAEConsejerasBloqueadasDesbloqueadas", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(java.util.Map)
	 */
	public void executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV", criteria);
	}
    
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(java.util.Map)
	 */
	public void executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBDetalladoRecuperacionCarteraCobradorCSV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBCargaMasivaGestionCSV(java.util.Map)
	 */
	public void executeReporteCOBCargaMasivaGestionCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBCargaMasivaGestionCSV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReporteINCPuntObtenidosBolsaFaltantesInc(java.util.Map)
	 */
	public void deleteReporteINCPuntObtenidosBolsaFaltantesInc(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteINCPuntObtenidosBolsaFaltantesInc", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteINCPuntObtenidosBolsaFaltantesInc(java.util.Map)
	 */
	public void insertReporteINCPuntObtenidosBolsaFaltantesInc(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteINCPuntObtenidosBolsaFaltantesInc", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(java.util.Map)
	 */
	public void executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCPuntObtenidosBolsaFaltantesIncCSV", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCOBDetalladoRecuperacionCarteraCobrador(java.util.Map)
	 */
	public void deleteReporteCOBDetalladCobranza31dias(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.deleteReporteCOBDetalladCobranza31dias", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCOBDetalladoRecuperacionCarteraCobrador(java.util.Map)
	 */
	public void insertReporteCOBDetalladCobranza31dias(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCOBDetalladCobranza31dias", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReportePEDSeguimientoConsultora(java.util.Map)
	 */
	public void deleteReportePEDSeguimientoConsultora(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReportePEDSeguimientoConsultora", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#deleteReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(java.util.Map)
	 */
	public void deleteReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReportePEDAvanceFacturadoProgramaReconocimientoVZConsu", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(java.util.Map)
	 */
	public void insertReportePEDAvanceFacturadoProgramaReconocimientoVZConsu(Map criteria) {
		 getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReportePEDAvanceFacturadoProgramaReconocimientoVZConsu", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReportePEDAvanceFacturadoProgramaReconocimientoVZ(java.util.Map)
	 */
	public void executeReportePEDAvanceFacturadoProgramaReconocimientoVZ(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePEDAvanceFacturadoProgramaReconocimientoVZ", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReportePEDSeguimientoConsultora(java.util.Map)
	 */
	public void insertReportePEDSeguimientoConsultora(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReportePEDSeguimientoConsultora", criteria);
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBDetalladCobranza31diasCSV(java.util.Map)
	 */
	public void executeReporteCOBDetalladCobranza31diasCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBDetalladCobranza31diasCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReportePEDSeguimientoConsultoraCSV(java.util.Map)
	 */
	public void executeReportePEDSeguimientoConsultoraCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePEDSeguimientoConsultoraCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReporteCOBSaldosPendientes(java.util.Map)
	 */
	public void deleteReporteCOBSaldosPendientes(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.deleteReporteCOBSaldosPendientes", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCOBSaldosPendientes(java.util.Map)
	 */
	public void insertReporteCOBSaldosPendientes(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCOBSaldosPendientes", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBSaldosPendientesCSV(java.util.Map)
	 */
	public void executeReporteCOBSaldosPendientesCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBSaldosPendientesCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCCCLiquidacionCobranzasCSV(java.util.Map)
	 */
	public void executeReporteCCCLiquidacionCobranzasCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCLiquidacionCobranzasCSV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarHistoricoOperacionCrediticiaTXT(java.util.Map)
	 */
	public Map generarHistoricoOperacionCrediticiaTXT(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarHistoricoOperacionCrediticiaTXT", criteria);
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarHistoricoOperacionCrediticia(java.util.Map)
	 */
	public void executeGenerarHistoricoOperacionCrediticia(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarHistoricoOperacionCrediticia", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReporteCOBDetalladoMovRecuperacionIncobrable(java.util.Map)
	 */
	public void deleteReporteCOBDetalladoMovRecuperacionIncobrable(Map criteria) {
    	getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCOBDetalladoMovRecuperacionIncobrable", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCOBDetalladoMovRecuperacionIncobrable(java.util.Map)
	 */
	public void insertReporteCOBDetalladoMovRecuperacionIncobrable(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCOBDetalladoMovRecuperacionIncobrable", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBDetalladoMovRecuperacionIncobrableCSV(java.util.Map)
	 */
	public void executeReporteCOBDetalladoMovRecuperacionIncobrableCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBDetalladoMovRecuperacionIncobrableCSV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReporteCCCArchivosMediosMagneticos(java.util.Map)
	 */
	public void deleteReporteCCCArchivosMediosMagneticos(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCCCArchivosMediosMagneticos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCCCArchivosMediosMagneticos(java.util.Map)
	 */
	public void insertReporteCCCArchivosMediosMagneticos(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCCCArchivosMediosMagneticos", criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGeneraReporteCCCArchivosMediosMagneticosCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGeneraReporteCCCArchivosMediosMagneticosCSV", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#deleteReporteMAEVinculosCliente(java.util.Map)
	 */
	public void deleteReporteMAEVinculosCliente(Map criteria) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteMAEVinculosCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteMAEVinculosCliente(java.util.Map)
	 */
	public void insertReporteMAEVinculosCliente(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteMAEVinculosCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteMAEVinculosClienteCSV(java.util.Map)
	 */
	public void executeReporteMAEVinculosClienteCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteMAEVinculosClienteCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCCCDetalleCuentaCorrienteContableCSV(java.util.Map)
	 */
	public void executeReporteCCCDetalleCuentaCorrienteContableCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalleCuentaCorrienteContableCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCCCDetalladoPagoxRegularizarCSV(java.util.Map)
	 */
	public void executeReporteCCCDetalladoPagoxRegularizarCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalladoPagoxRegularizarCSV", criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeBeforeReporteINCPuntObtenidosPuntFaltantes(java.util.Map)
	 */
	public void executeBeforeReporteINCPuntObtenidosPuntFaltantes(Map criteria) {
		String tipoReporte = (String) criteria.get("tipoReporte");
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteBeforeReporteINCPuntajeFaltantes", criteria);
		if (tipoReporte.equals(("1")))
			getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertBeforeReporteINCPuntajeFaltantes", criteria);
		else
			getSqlMapClientTemplate().insert("sisicc.ReportesSQL.inserteBeforeReporteINCPuntajeFaltantesExigidos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteINCPuntObtenidosPuntFaltantesCSV(java.util.Map)
	 */
	public void executeReporteINCPuntObtenidosPuntFaltantesCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCPuntObtenidosPuntFaltantesCSV", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(java.util.Map)
	 */
	public void executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteDetalleIFCCSV(java.util.Map)
	 */
	public void executeReporteDetalleIFCCSV(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalleIFCCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO#getDevuelveIdSgteCodHistoricoReporte()
	 */
	public Long getDevuelveIdSgteCodHistoricoReporte(){
		Long id = (Long)getSqlMapClientTemplate().
				queryForObject("sisicc.ReportesSQL.getDevuelveIdSgteCodHistoricoReporte");		
		return id;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO#insertHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */	
	public void insertHistoricoReporte(HistoricoReporte historicoReporte) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.insertHistoricoReporte", historicoReporte);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO#updateHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */	
	public void updateHistoricoReporte(HistoricoReporte historicoReporte) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.updateHistoricoReporte", historicoReporte);	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteMAEClasificacionXClienteCSV(java.util.Map)
	 */
	public void executeReporteMAEClasificacionXClienteCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteMAEClasificacionXClienteCSV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getExisteListaProcesoRECEnviarCDRRecepcionados(java.util.Map)
	 */
	public List getExisteListaProcesoRECEnviarCDRRecepcionados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getExisteListaProcesoRECEnviarCDRRecepcionados", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getListaProcesoRECEnviarCDRRecepcionados(java.util.Map)
	 */
	public List getListaProcesoRECEnviarCDRRecepcionados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getListaProcesoRECEnviarCDRRecepcionados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeCargaTemporalReporteZONMovimientoTerritorio(java.util.Map)
	 */
	public void executeCargaTemporalReporteZONMovimientoTerritorio(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeCargaTemporalReporteZONMovimientoTerritorio", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#generarReporteZONMovimientoTerritorioCSV(java.util.Map)
	 */
	public Map generarReporteZONMovimientoTerritorioCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.generarReporteZONMovimientoTerritorioCSV",criteria);
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCCCGastoCuponCSV(java.util.Map)
	 */
	public void executeReporteCCCGastoCuponCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCGastoCuponCSV", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#actualizarRegistroAuditoriaReinicioServidor()
	 */
	public void actualizarRegistroAuditoriaReinicioServidor() {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.actualizarRegistroAuditoriaReinicioServidor");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteSACRecallTrazabilidad(java.util.Map)
	 */
	public Map executeReporteSACRecallTrazabilidad(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteSACRecallTrazabilidad", criteria);
		return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeBeforeReporteINCPuntosConsultora(java.util.Map)
	 */
	public void executeBeforeReporteINCPuntosConsultora(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteBeforeReporteINCPuntosConsultora", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertBeforeReporteINCPuntosConsultora", params);		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteINCPuntosConsultoraCSV(java.util.Map)
	 */
	public void executeReporteINCPuntosConsultoraCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCPuntosConsultoraCSV", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeBeforeReporteINCPuntosCampania(java.util.Map)
	 */
	public void executeBeforeReporteINCPuntosCampania(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteBeforeReporteINCPuntosCampania", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertBeforeReporteINCPuntosCampania", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteINCPuntosCampaniaCSV(java.util.Map)
	 */
	public void executeReporteINCPuntosCampaniaCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCPuntosCampaniaCSV", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBRetiradasSinDeuda(java.util.Map)
	 */
	public void executeReporteCOBRetiradasSinDeuda(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBRetiradasSinDeuda", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeEgresadasSinDeuda(java.util.Map)
	 */
	public void executeEgresadasSinDeuda(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeEgresadasSinDeuda", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBPrimerPedidoConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPrimerPedidoConDeuda(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBPrimerPedidoConDeuda", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBPedidosFacturadosConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPedidosFacturadosConDeuda(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBPedidosFacturadosConDeuda", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBPrimerosSegundoPedidosConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPrimerosSegundoPedidosConDeuda(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBPrimerosSegundoPedidosConDeuda", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBPrimerSegundoTercerPedidoConDeuda(java.util.Map)
	 */
	public void executeReporteCOBPrimerSegundoTercerPedidoConDeuda(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBPrimerSegundoTercerPedidoConDeuda", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCCCLiquidacionCobranzasCZ(java.util.Map)
	 */
	public void insertReporteCCCLiquidacionCobranzasCZ(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCCCLiquidacionCobranzas", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCCCLiquidacionCobranzasCZ", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCCCLiquidacionCobranzasDZ(java.util.Map)
	 */
	public void insertReporteCCCLiquidacionCobranzasDZ(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCCCLiquidacionCobranzas", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCCCLiquidacionCobranzasDZ", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getIndicadorClienteCedula(java.util.Map)
	 */
	public String getIndicadorClienteCedula(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getIndicadorClienteCedula", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeBeforeReporteMAENuevasCupones(java.util.Map)
	 */
	public void executeBeforeReporteCUPNuevasCupones(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeBeforeReporteCUPNuevasCupones", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteMAENuevasCuponesCSV(java.util.Map)
	 */
	public void executeReporteCUPNuevasCuponesCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCUPNuevasCuponesCSV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteSACControlFacturacionEntregaPedido(java.util.Map)
	 */
	public Map executeReporteSACControlFacturacionEntregaPedido(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteSACControlFacturacionEntregaPedido", criteria);
		return criteria;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteLETResultado(java.util.Map)
	 */
	public Map executeReporteLETResultado(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteLETResultado", criteria);
		return criteria;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteSGRSolicitudesTod(java.util.Map)
	 */
	public Map executeReporteSGRSolicitudesTod(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteSGRSolicitudesTod", criteria);
		return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertRegionReporteLETResultado(java.util.Map)
	 */
	public void insertRegionReporteLETResultado(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertRegionReporteLETResultado",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getSecuenciaIndicadorControlEntregaNextValue()
	 */
	public String getSecuenciaIndicadorControlEntregaNextValue() {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getSecuenciaIndicadorControlEntregaNextValue",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(java.util.Map)
	 */
	public void insertReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(
			Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual", criteria);		
	}
	
	public void deleteReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual(){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensual");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV(
			Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGeneraReporteCCCArchivosMediosMagneticosCuentaCorrienteBimensualCSV", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#insertReporteCCCAntiguedadSaldos(java.util.Map)
	 */
	public void insertReporteCCCAntiguedadSaldos(
			Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCCCAntiguedadSaldos", criteria);		
	}
	
	public void deleteReporteCCCAntiguedadSaldos(){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCCCAntiguedadSaldos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGeneraReporteCCCAntiguedadSaldosCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCAntiguedadSaldosCSV(
			Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGeneraReporteCCCAntiguedadSaldosCSV", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteLECEnviarReporteProyecion(java.util.Map)
	 */
	public void executeReporteLECEnviarReporteProyecion(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteLECEnviarReporteProyecion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getRegionesReporteLECEnviarReporteProyecion(java.util.Map)
	 */
	public List getRegionesReporteLECEnviarReporteProyecion(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getRegionesReporteLECEnviarReporteProyecion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getZonasReporteLECEnviarReporteProyecion(java.util.Map)
	 */
	public List getZonasReporteLECEnviarReporteProyecion(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ReportesSQL.getZonasReporteLECEnviarReporteProyecion", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCampanyaBonoRegion(java.util.Map)
	 */
	public String getCampanyaBonoRegion(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
									"sisicc.ReportesSQL.getCampanyaBonoRegion",criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCampanyaBonoZona(java.util.Map)
	 */
	public String getCampanyaBonoZona(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
									"sisicc.ReportesSQL.getCampanyaBonoZona",criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCampanyaRecaudoRegion(java.util.Map)
	 */
	public String getCampanyaRecaudoRegion(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
									"sisicc.ReportesSQL.getCampanyaRecaudoRegion",criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCampanyaRecaudoZona(java.util.Map)
	 */
	public String getCampanyaRecaudoZona(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
									"sisicc.ReportesSQL.getCampanyaRecaudoZona",criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#getCodigoPeriodoAnterior(java.lang.String)
	 */
	public String getCodigoPeriodoAnterior(String codigoPeriodo) {
		HashMap criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getDesPeriodoByCodigoPeriodoAnterior",criteria));
	}

	public void executeReporteCCCDetalladoConsultorasIncobrable(Map criteria) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalladoConsultorasIncobrable", criteria);
	}

	public Map executeReporteCCCDetalladoConsultorasIncobrableCSV(Map criteria) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalladoConsultorasIncobrableCSV", criteria);
		return criteria;
	}

	public void deleteReporteCCCDetalladoConsultorasIncobrable(Map criteria) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCCCDetalladoConsultorasIncobrable");
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalladoConsultorasIncobrable", criteria);
	}

	public Map executeReporteCCCDetalladoProvisionIncobrableCSV(Map criteria) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalladoProvisionIncobrableConsultoraCSV", criteria);
		return criteria;
	}
	
	public Map executeReporteCCCDetalladoProvisionIncobrableMovCSV(Map criteria) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCDetalladoProvisionIncobrableMovCSV", criteria);
		return criteria;
	}

	public void executeReporteCCCConsolidadoRecaudoCampana(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCConsolidadoRecaudoCampana", params);
	}

	public void deleteReporteCCCConsolidadoRecaudoCampana(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCCCConsolidadoRecaudoCampana");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteZONTerritorioUnidadGeograficaCSV(java.util.Map)
	 */
	public void executeReporteZONTerritorioUnidadGeograficaCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteZONTerritorioUnidadGeograficaCSV", params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(java.util.Map)
	 */
	public void executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executePrepararReporteCOBRecuperacionCobranzaFFVVTXT", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(java.util.Map)
	 */
	public void executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGeneraReporteCCCRegistroVentasBoliviaCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCRegistroVentasBoliviaCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGeneraReporteCCCRegistroVentasBoliviaCSV", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeGeneraReporteCCCRegistroAbonosBoliviaCSV(java.util.Map)
	 */
	public void executeGeneraReporteCCCRegistroAbonosBoliviaCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGeneraReporteCCCRegistroAbonosBoliviaCSV", criteria);
	}
	
	public Map executeReporteCCCBuroCreditoCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCBuroCreditoCSV", criteria);
		return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#eliminarRegistrosTablaTemporalReporProyeccion(java.util.Map)
	 */
	public void eliminarRegistrosTablaTemporalReporProyeccion(Map criteria){
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalLecProyecPpreg", criteria);
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteTablaTemporalRerLecProye", criteria);
	}
	
	
	/* NSSICC */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeReporteCOBDetalladCobranza31diasCSV(java.util.Map)
	 */
	public void executeReporteCOBDetalladCobranza31diasXLSX(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBDetalladCobranza31diasXLSX", criteria);
	}
	
	@Override
	public void executeReporteAntigNotasCredi(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteAntigNotasCredi", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteCOBPrimerSegundoTercerCuartoPedidoConDeuda(java.util.Map)
	 */
	@Override
	public void executeReporteCOBPrimerSegundoTercerCuartoPedidoConDeuda(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBPrimerSegundoTercerCuartoPedidoConDeuda", params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteSACTIMImpositivoAduana(java.util.Map)
	 */
	public void executeReporteSACTIMImpositivoAduana(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteSACTIMImpositivoAduana", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertarReporteSACAtendidoxFechaConsolidado(java.util.Map)
	 */
	public void insertarReporteSACAtendidoxFechaConsolidado(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.deleteReporteSACAtendidoxFechaConsolidado", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertarReporteSACAtendidoxFechaConsolidado", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertarReporteSACAtendidoxFechaDetallado(java.util.Map)
	 */
	public void insertarReporteSACAtendidoxFechaDetallado(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.deleteReporteSACAtendidoxFechaDetallado", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertarReporteSACAtendidoxFechaDetallado", params);
	}

	@Override
	public String getCampanyaRecaudoRegionZona(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
				"sisicc.ReportesSQL.getCampanyaRecaudoRegionZona",criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#getExisteCUV(java.util.Map)
	 */
	public int getExisteCUV(Map criteria) {
		return (Integer)(getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getExisteCUV",criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteConsultoraPuntajeUbicacion(java.util.Map)
	 */
	public void executeReporteConsultoraPuntajeUbicacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteConsultoraPuntajeUbicacion", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteCodigosInexistentes(java.util.Map)
	 */
	public void executeReporteCodigosInexistentes(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCodigosInexistentes", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertarReporteRECListadoDeudaPendPeriodoConsolidado(java.util.Map)
	 */
	public void insertarReporteRECListadoDeudaPendPeriodoConsolidado(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteRECListadoDeudaPendPeriodoConsolidado", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertarReporteRECListadoDeudaPendPeriodoConsolidado", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertarReporteRECListadoDeudaPendPeriodoDetallado(java.util.Map)
	 */
	public void insertarReporteRECListadoDeudaPendPeriodoDetallado(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteRECListadoDeudaPendPeriodoDetallado", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertarReporteRECListadoDeudaPendPeriodoDetallado", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#deleteCodigoSAPReportePEDDetallePedidosFacturados()
	 */
	@Override
	public void deleteCodigoSAPReportePEDDetallePedidosFacturados() {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteCodigoSAPReportePEDDetallePedidosFacturados", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertCodigoSAPReportePEDDetallePedidosFacturados(java.util.Map)
	 */
	@Override
	public void insertCodigoSAPReportePEDDetallePedidosFacturados(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertCodigoSAPReportePEDDetallePedidosFacturados",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#insertReportePEDDetallePedidosFacturadosPorCodigoSAP(java.util.Map)
	 */
	@Override
	public void insertReportePEDDetallePedidosFacturadosPorCodigoSAP(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReportePEDDetallePedidosFacturadosPorCodigoSAP", params);
		getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReportePEDDetallePedidosFacturadosPorCodigoSAP", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteVENFacturaAuditoriaConso(java.util.Map)
	 */
	public void executeReporteVENFacturaAuditoriaConso(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteVENFacturaAuditoriaConso", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteVENFacturaAuditoriaDetal(java.util.Map)
	 */
	public void executeReporteVENFacturaAuditoriaDetal(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteVENFacturaAuditoriaDetal", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteCCCPRDetalleCuentaCorriente(java.util.Map)
	 */
	@Override
	public void executeReporteCCCPRDetalleCuentaCorriente(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCPRDetalleCuentaCorriente", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteConfiguracionOfertasConcurso(java.util.Map)
	 */
	public void executeReporteConfiguracionOfertasConcurso(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteConfiguracionOfertasConcurso", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteConfiguracionOfertasN(java.util.Map)
	 */
	public void executeReporteConfiguracionOfertasN(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteConfiguracionOfertasN", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteCOBNumeroPagosCampanna(java.util.Map)
	 */
	public void executeReporteCOBNumeroPagosCampanna(Map params) {
		String tipoReporte = (String) params.get("tipoReporte");
		getSqlMapClientTemplate().delete("sisicc.ReportesSQL.deleteReporteCOBNumeroPagosCampanna", params);
		if (tipoReporte.equals("C")) 
			getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCOBNumeroPagosCampannaConsultora", params);
		else
			getSqlMapClientTemplate().insert("sisicc.ReportesSQL.insertReporteCOBNumeroPagosCampannaBanco", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteCOBNumeroPagosCampannaCSV(java.util.Map)
	 */
	public void executeReporteCOBNumeroPagosCampannaCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCOBNumeroPagosCampannaCSV", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.scsicc.ReporteDAO#executeReporteCCCInteresCCorrienteCSV(java.util.Map)
	 */
	public Map executeReporteCCCInteresCCorrienteCSV(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteCCCInteresCCorrienteCSV", criteria);
		return criteria;
	}
	
	public void executeGenerarReporteCCCInteresCCorriente(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeGenerarReporteCCCInteresCCorriente", params);
	}
	
	public void executeReporteINCDetallePuntosRegionZonaByNacionalCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCDetallePuntosRegionZonaByNacionalCSV", params);
	}
	
	public void executeReporteINCDetallePuntosRegionZonaByRegionCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCDetallePuntosRegionZonaByRegionCSV", params);
	}
	
	public void executeReporteINCDetallePuntosRegionZonaByZonaCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCDetallePuntosRegionZonaByZonaCSV", params);
	}
	
	public void executeReporteINCDetallePuntosRegionZonaByConsultoraCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCDetallePuntosRegionZonaByConsultoraCSV", params);
	}
	
	public void executeReporteINCDetallePuntosRegionZonaByCampaniaCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCDetallePuntosRegionZonaByCampaniaCSV", params);
	}
	
	public void executeReporteINCProvisionContableIngresosCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCProvisionContableIngresosCSV", params);
	}
	
	public void executeReporteINCProvisionContableGastosCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReporteINCProvisionContableGastosCSV", params);
	}
	
	public Map executeReportePERResumenDiarioPercepcionesSunatCSV(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePERResumenDiarioPercepcionesSunatCSV", params);
		return params;		
	}
	
	public Map executeReportePERResumenDiarioPercepcionesSunatTXT(Map params) {
		getSqlMapClientTemplate().update("sisicc.ReportesSQL.executeReportePERResumenDiarioPercepcionesSunatTXT", params);
		return params;		
	}		
}