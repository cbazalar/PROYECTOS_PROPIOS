package biz.belcorp.ssicc.dao.scsicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scsicc.ConsultaHIPDatosClienteDAO;


/**
 * Implementacion de consultas del modulo de HiperConsulta
 * 
 * <p>
 * <a href="ConsultaHIPDatosClienteDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Sergio Apaza</a>
 * 
 */

@Repository("scsicc.consultaHIPDatosClienteDAO")
public class ConsultaHIPDatosClienteDAOiBatis extends BaseDAOiBatis implements ConsultaHIPDatosClienteDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosGenerales(java.util.Map)
	 */
	public Map getDatosGenerales(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getDatosGenerales", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getMotivoBloqueo(java.util.Map)
	 */
	public String getMotivoBloqueo(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getMotivoBloqueo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDireccionDomicilio(java.util.Map)
	 */
	public Map getDireccionDomicilio(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getDireccionDomicilio", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDireccionDespacho(java.util.Map)
	 */
	public Map getDireccionDespacho(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getDireccionDespacho", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getUltimoPedido(java.util.Map)
	 */
	public Map getUltimoPedido(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getUltimoPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getSaldoUnico(java.util.Map)
	 */
	public String getSaldoUnico(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getSaldoUnico", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getSaldoCampana(java.util.Map)
	 */
	public String getSaldoCampana(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getSaldoCampana", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getUnidadesAdministrativas(java.util.Map)
	 */
	public List getUnidadesAdministrativas (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getUnidadesAdministrativas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getTipificacionCliente(java.util.Map)
	 */
	public List getTipificacionCliente (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getTipificacionCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getOpcionesPermitidas(java.util.Map)
	 */
	public List getOpcionesPermitidas(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getOpcionesPermitidas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#validarOpcionSecundaria(java.util.Map)
	 */
	public boolean validarOpcionSecundaria(Map criteria) {
		String contador = (String) getSqlMapClientTemplate().queryForObject(
							"sisicc.ConsultaHIPSQL.validarOpcionSecundaria", criteria);
		
		if(contador.equals("0"))
			return false;
		else
			return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getCabeceraReclamos(java.util.Map)
	 */
	public List getCabeceraReclamos (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getCabeceraReclamos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDetalleReclamo(java.util.Map)
	 */
	public List getDetalleReclamo (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDetalleReclamo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getBoletasRecojo(java.util.Map)
	 */
	public List getBoletasRecojo (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getBoletasRecojo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getClientesByCriteria(java.util.Map)
	 */
	public List getClientesByCriteria (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getClientesByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getTiposDocumento(java.util.Map)
	 */
	public List getTiposDocumento(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getTiposDocumento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getConcursos(java.util.Map)
	 */
	public List getConcursos(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getConcursos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPuntajeDetalleConcurso(java.util.Map)
	 */
	public List getPuntajeDetalleConcurso(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPuntajeDetalleConcurso", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPuntajeResumenConcurso(java.util.Map)
	 */
	public List getPuntajeResumenConcurso(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPuntajeResumenConcurso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDuplaAsociada(java.util.Map)
	 */
	public List getDuplaAsociada(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDuplaAsociada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPremiosByNivel(java.util.Map)
	 */
	public List getPremiosByNivel(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPremiosByNivel", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getBolsaFaltantes(java.util.Map)
	 */
	public List getBolsaFaltantes(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getBolsaFaltantes", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getSaldoPagar(java.util.Map)
	 */
	public String getSaldoPagar(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getSaldoPagar", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#executaCronogramaActividadesList(java.util.Map)
	 */
	public List executaCronogramaActividadesList(Map criteria) {
		//getSqlMapClientTemplate().update("sisicc.ConsultaHIPSQL.executaCronogramaActividadesList", criteria);
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getCronogramaActividadesList", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getGerenteZonaList(java.util.Map)
	 */
	public List getGerenteZonaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getGerenteZonaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getGerenteRegionList(java.util.Map)
	 */
	public List getGerenteRegionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getGerenteRegionList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#executaEnviosPreferencialesList(java.util.Map)
	 */
	public List executaEnviosPreferencialesList(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ConsultaHIPSQL.executaEnviosPreferencialesList", criteria);
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getEnviosPreferencialesList", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#executaSuscribirEnviosPreferenciales(java.util.Map)
	 */
	public void executaSuscribirEnviosPreferenciales(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ConsultaHIPSQL.executaSuscribirEnviosPreferenciales", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#executaFinSuscribirEnviosPreferenciales(java.util.Map)
	 */
	public void executaFinSuscribirEnviosPreferenciales(Map params) {
		getSqlMapClientTemplate().delete("sisicc.ConsultaHIPSQL.executaFinSuscribirEnviosPreferenciales", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getResponsableSeccionList(java.util.Map)
	 */
	public List getResponsableSeccionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getResponsableSeccionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getHistoriaBloqueoList(java.util.Map)
	 */
	public List getHistoriaBloqueoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getHistoriaBloqueoList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getEduConsultorasList(java.util.Map)
	 */
	public List getEduConsultorasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getEduConsultorasList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getEduCursosList(java.util.Map)
	 */
	public List getEduCursosList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getEduCursosList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getTipoCertificacionList(java.util.Map)
	 */
	public List getTipoCertificacionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getTipoCertificacionList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getValorStatus(java.util.Map)
	 */
	public String getValorStatus(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getValorStatus", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#execCalculoPeriIniPromVentas(java.util.Map)
	 */
	public String execCalculoPeriIniPromVentas(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.execCalculoPeriIniPromVentas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPremiosClienteList(java.util.Map)
	 */
	public List getPremiosClienteList(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPremiosClienteList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFecMesAnno(java.util.Map)
	 */
	public String getFecMesAnno(Map paramsOidCli){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getFecMesAnno", paramsOidCli);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#execGeneraCertificacion(java.util.Map)
	 */
	public void execGeneraCertificacion(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ConsultaHIPSQL.execGeneraCertificacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getOidMsj(java.util.Map)
	 */
	public String getOidMsj(Map paramsTemp) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getOidMsj", paramsTemp);
	}	

	/**
	 * @param criteria
	 * @return
	 */
	public String getPromedioVentas(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getPromedioVentas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getProductosDespachadosAutomaticamente(java.util.Map)
	 */
	public List getProductosDespachadosAutomaticamente(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getProductosDespachadosAutomaticamente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getProductosSolicitadosConsultoras(java.util.Map)
	 */
	public List getProductosSolicitadosConsultoras(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getProductosSolicitadosConsultoras", criteria);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map getDuplaActual(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getDuplaActual", criteria);
	}

	/**
	 * @param criteria
	 */
	public void insertDocumentoDigitacion(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ConsultaHIPSQL.insertDocumentoDigitacion", criteria);
	}
	
	/**
	 * @param criteria
	 */
	public void insertSolicitudDuplaCyzone(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ConsultaHIPSQL.insertSolicitudDuplaCyzone", criteria);
	}

	/**
	 * @param criteria
	 */
	public void insertSolicitudActualizacionDatos(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ConsultaHIPSQL.insertSolicitudActualizacionDatos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPedidosConsultora(java.util.Map)
	 */
	public List getPedidosConsultora(Map criteria) {
		return  this.getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPedidosConsultora", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDetallePedido(java.util.Map)
	 */
	public List getDetallePedido(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDetallePedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getConsultaCdrRechazados(java.util.Map)
	 */
	public List getConsultaCdrRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getConsultaCdrRechazados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDetalleCdrRechazados(java.util.Map)
	 */
	public List getDetalleCdrRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDetalleCdrRechazados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListaTipoPremios(java.util.Map)
	 */
	public List getListaTipoPremios(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListaTipoPremios", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getImpedidaPasarCdr(java.util.Map)
	 */
	public String getImpedidaPasarCdr(Map criterios) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getImpedidaPasarCdr", criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getImpedidaPasarPedido(java.util.Map)
	 */
	public String getImpedidaPasarPedido(Map criterios) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getImpedidaPasarPedido", criterios);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getVinculosReferencias(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getVinculosReferencias", criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public String getPromedioVentasxCampanhas(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getPromedioVentasxCampanhas", criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getMediosComunicacion (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getMediosComunicacion", criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getLiderSeccionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getLiderSeccionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getClasificacionLove(java.util.Map)
	 */
	public String getClasificacionLove(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getClasificacionLove", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPremiosAtendidosFaltantes(java.util.Map)
	 */
	public List getPremiosAtendidosFaltantes(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPremiosAtendidosFaltantes", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getRecomendaciones(java.util.Map)
	 */
	public List getRecomendaciones(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getRecomendaciones", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getTiposVinculos(java.util.Map)
	 */
	public List getTiposVinculos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getTiposVinculos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getHistoricoVinculos(java.util.Map)
	 */
	public List getHistoricoVinculos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getHistoricoVinculos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFechaCastigada(java.util.Map)
	 */
	public String getFechaCastigada(Map criterios) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getFechaCastigada", criterios);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getLimiteCredito(java.util.Map)
	 */
	public String getLimiteCredito(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getLimiteCredito", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getSaldoVencidos(java.util.Map)
	 */
	public Map getSaldoVencidos(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getSaldoVencidos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getEstadoSolicitudPoliza(java.util.Map)
	 */
	public String getEstadoSolicitudPoliza(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getEstadoSolicitudPoliza", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFechasCoberturaPoliza(java.util.Map)
	 */
	public List getFechasCoberturaPoliza(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getFechasCoberturaPoliza", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getSolicitudesPoliza(java.util.Map)
	 */
	public List getSolicitudesPoliza(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getSolicitudesPoliza", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getHistoricoCargosPoliza(java.util.Map)
	 */
	public List getHistoricoCargosPoliza(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getHistoricoCargosPoliza", criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getObtenerMontoPlanSuperacion(java.util.Map)
	 */
	public String getObtenerMontoPlanSuperacion(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getObtenerMontoPlanSuperacion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#validarImagenesEscaneoSC(java.lang.String)
	 */
	public boolean validarImagenesEscaneoSC(String codigoCliente) {
		String contador = (String) getSqlMapClientTemplate().queryForObject(
							"sisicc.ConsultaHIPSQL.validarImagenesEscaneoSC", codigoCliente);
		
		if(contador.equals("0"))
			return false;
		else
			return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosClienteEscaneoSC(java.util.Map)
	 */
	public Map getDatosClienteEscaneoSC(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getDatosClienteEscaneoSC", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosTodosClienteEscaneoSC(java.util.Map)
	 */
	public List getDatosTodosClienteEscaneoSC(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDatosTodosClienteEscaneoSC", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#deleteImagenesEscaneoSC(java.lang.String)
	 */
	public void deleteImagenesEscaneoSC(String codigoCliente){
		 getSqlMapClientTemplate().delete("sisicc.ConsultaHIPSQL.deleteImagenesEscaneoSC", codigoCliente);
	}

	/* INI SA PER-SiCC-2012-0385 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosCodigoVentaPrincipal(java.util.Map)
	 */
	public Map getDatosCodigoVentaPrincipal(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
					"sisicc.ConsultaHIPSQL.getDatosCodigoVentaPrincipal", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListCodigoVentaRelacionados(java.util.Map)
	 */
	public List getListCodigoVentaRelacionados (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListCodigoVentaRelacionados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListVentaExclusiva(java.util.Map)
	 */
	public List getListVentaExclusiva (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListVentaExclusiva", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListLimiteVenta(java.util.Map)
	 */
	public List getListLimiteVenta (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListLimiteVenta", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListControlStock(java.util.Map)
	 */
	public List getListControlStock (Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListControlStock", criteria);
	}
	/* FIN SA PER-SiCC-2012-0385 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getTipoConsultora(java.util.Map)
	 */
	public String getTipoConsultora(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getTipoConsultora", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListCoberturaCentroAcopio(java.util.Map)
	 */
	public List getListCoberturaCentroAcopio(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListCoberturaCentroAcopio", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getAdicionalesCabeceraReclamo(java.util.Map)
	 */
	public List getAdicionalesCabeceraReclamo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getAdicionalesCabeceraReclamo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosCabeceraDetallePedidoSolicitado(java.lang.String)
	 */
	public List getDatosCabeceraDetallePedidoSolicitado(String secuencia) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDatosCabeceraDetallePedidoSolicitado", secuencia);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosPoliza(java.util.Map)
	 */
	public Map getDatosPoliza(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getDatosPoliza", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getHistorialCobrosSeguro(java.util.Map)
	 */
	public List getHistorialCobrosSeguro(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getHistorialCobrosSeguro", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosMetas(java.util.Map)
	 */
	public Map getDatosMetas(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getDatosMetas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFacturacionAdicionalList(java.lang.String)
	 */
	public List getFacturacionAdicionalList(String codigoCliente) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getFacturacionAdicionalList", codigoCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListPeriodoRetail(java.util.Map)
	 */
	public List getListPeriodoRetail(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListPeriodoRetail", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListCabeceraRetail(java.util.Map)
	 */
	public List getListCabeceraRetail(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListCabeceraRetail", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListDetalleRetail(java.util.Map)
	 */
	public List getListDetalleRetail(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListDetalleRetail", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getClientesByCriteriaFOX(java.util.Map)
	 */
	public List getClientesByCriteriaFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getClientesByCriteriaFOX", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#executeActualizarDatosCliente(java.util.Map)
	 */
	public void executeActualizarDatosCliente(Map params) {
		getSqlMapClientTemplate().insert("sisicc.ConsultaHIPSQL.executeActualizarDatosCliente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosEjecutiva(java.util.Map)
	 */
	public Map getDatosEjecutiva(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getDatosEjecutiva", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDetalleEvaluacionEjecutiva(java.util.Map)
	 */
	public List getDetalleEvaluacionEjecutiva(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDetalleEvaluacionEjecutiva", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFaseActivaPrograma(java.util.Map)
	 */
	public String getFaseActivaPrograma(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getFaseActivaPrograma", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getEtapasEjecutivas(java.util.Map)
	 */
	public List getEtapasEjecutivas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getEtapasEjecutivas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getBloqueoActivo(java.lang.String)
	 */
	public String getBloqueoActivo(String codigoCliente) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getBloqueoActivo", codigoCliente);		
	}

	public Double getSaldoCupon3(Map criteria) {
		return  (Double) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getSaldoCupon3", criteria);
	
	}
	
	public Integer getOidPeriodo(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getOidPeriodo", criteria);
	}
	
	public Integer getOidCliente(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getOidCliente", criteria);
	}
	
	public Double getMontoMinimo(Map criteria) {
		return  (Double) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getMontoMinimo", criteria);
	
	}
	
	public String getDesPeriodoByCodigoPeriodoX(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getDesPeriodoByCodigoPeriodoX", criteria);
	}
	
	public Map getAplicaMontoMinimo(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getAplicaMontoMinimo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPromedioVentasxNumeroPedidos(java.util.Map)
	 */
	public String getPromedioVentasxNumeroPedidos(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getPromedioVentasxNumeroPedidos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getConsultaDetallePedidoSolicitado(java.util.Map)
	 */
	public List getConsultaDetallePedidoSolicitado(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getConsultaDetallePedidoSolicitado", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getCabeceraConsultoraCastigada(java.util.Map)
	 */
	public List getCabeceraConsultoraCastigada(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getCabeceraConsultoraCastigada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDetalleConsultoraCastigada(java.util.Map)
	 */
	public List getDetalleConsultoraCastigada(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDetalleConsultoraCastigada", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getIndicadorBasparampais(java.util.Map)
	 */
	public String getIndicadorBasparampais(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getIndicadorBasparampais", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getEtapaDeCobro(java.util.Map)
	 */
	public String getEtapaDeCobro(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getEtapaDeCobro", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getCobrador(java.util.Map)
	 */
	public String getCobrador(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getCobrador", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getCuentaCorrienteConsultoraCastigada(java.util.Map)
	 */
	public List getCuentaCorrienteConsultoraCastigada(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getCuentaCorrienteConsultoraCastigada", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getSaldoACampana(java.util.Map)
	 */
	public String getSaldoACampana(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getSaldoACampana", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPedidoBaseLineaCredito(java.util.Map)
	 */
	public List getPedidoBaseLineaCredito(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPedidoBaseLineaCredito", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getListDetallePuntosConcursoRetail(java.util.Map)
	 */
	public List getListDetallePuntosConcursoRetail(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getListDetallePuntosConcursoRetail", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDetalleRecuperacionAnulacion(java.util.Map)
	 */
	public List getDetalleRecuperacionAnulacion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getDetalleRecuperacionAnulacion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#insertRecuperacionAnulacionAudit(java.util.Map)
	 */
	public void insertRecuperacionAnulacionAudit(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.ConsultaHIPSQL.insertRecuperacionAnulacionAudit", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#deleteRecuperacionAnulacion(java.lang.String)
	 */
	public void deleteRecuperacionAnulacion(String oidSoliPosi) {
		getSqlMapClientTemplate().delete("sisicc.ConsultaHIPSQL.deleteRecuperacionAnulacion", oidSoliPosi);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getExisteIntegridadCabecera(java.lang.String)
	 */
	public String getExisteIntegridadCabecera(String oidSoliCabe) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getExisteIntegridadCabecera", oidSoliCabe);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#deleteRecuperacionAnulacionCabecera(java.lang.String)
	 */
	public void deleteRecuperacionAnulacionCabecera(String oidSoliCabe) {
		getSqlMapClientTemplate().delete("sisicc.ConsultaHIPSQL.deleteRecuperacionAnulacionCabecera", oidSoliCabe);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPremiosRechazados(java.util.Map)
	 */
	public List getPremiosRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPremiosRechazados", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getEtapasCobro(java.util.Map)
	 */
	public List getEtapasCobro(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getEtapasCobro", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDeshabilitarZonaTerritorio(java.util.Map)
	 */
	public String getDeshabilitarZonaTerritorio(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getDeshabilitarZonaTerritorio", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPremiosElegidos(java.util.Map)
	 */
	public List getPremiosElegidos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getPremiosElegidos", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getCodigoProgramaLET(java.lang.String)
	 */
	public String getCodigoProgramaLET(String campanyaProceso) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getCodigoProgramaLET", campanyaProceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getDatosSociaEmpresaria(java.util.Map)
	 */
	public Map getDatosSociaEmpresaria(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getDatosSociaEmpresaria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getHistorialSociaEmpresaria(java.util.Map)
	 */
	public List getHistorialSociaEmpresaria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getHistorialSociaEmpresaria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getResultadosSociaEmpresaria(java.util.Map)
	 */
	public List getResultadosSociaEmpresaria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getResultadosSociaEmpresaria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getEstatusCliente(java.util.Map)
	 */
	public String getEstatusCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getEstatusCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFechaCampanaActivacionCliente(java.util.Map)
	 */
	public Map getFechaCampanaActivacionCliente(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getFechaCampanaActivacionCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFechaCancelacionCliente(java.util.Map)
	 */
	public String getFechaCancelacionCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getFechaCancelacionCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getFechaComunicacionCliente(java.util.Map)
	 */
	public String getFechaComunicacionCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getFechaComunicacionCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getMotivoCancelacionCliente(java.util.Map)
	 */
	public String getMotivoCancelacionCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getMotivoCancelacionCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPeriodoComunicacionCliente(java.util.Map)
	 */
	public String getPeriodoComunicacionCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getPeriodoComunicacionCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getPeriodoNumeroPedido(java.util.Map)
	 */
	public Map getPeriodoNumeroPedido(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getPeriodoNumeroPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getSituacionFlexipago(java.lang.String, java.lang.String)
	 */
	public String getSituacionFlexipago(String codigoPais, String codigoCliente, String campanhaProceso) {
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoCliente", codigoCliente);
		params.put("campanhaProceso", campanhaProceso);
		
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getSituacionFlexipago", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getOidSubtipoCLiente(java.util.Map)
	 */
	public String getOidSubtipoCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getOidSubtipoCliente", criteria);
	}
	
	/* NSSiCC /
   /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaHIPDatosClienteDAO#getAdicionalesCabeceraReclamo(java.lang.String)
	 */
	public List getAdicionalesCabeceraReclamo(String oidCabecera) {
		return getSqlMapClientTemplate().queryForList("sisicc.ConsultaHIPSQL.getAdicionalesCabeceraReclamoNSSiCC", oidCabecera);
	}
	
	public String getObtieAbonoPendi(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getObtieAbonoPendi", criteria);
	}


	public String getTipoPersonaxOidCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getTipoPersonaxOidCliente", criteria);
	}

	
	public String getOrigenIngresosxOidCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ConsultaHIPSQL.getOrigenIngresosxOidCliente", criteria);
	}

	@Override
	public String getOrigenxCodCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConsultaHIPSQL.getOrigenxCodCliente", criteria);
	}
	
	
	
	
}