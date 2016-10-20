package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECProgramaCorporativoDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.model.AmbitoGeografico;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoCampana;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoLanzamiento;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoLanzamientoProducto;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoNivel;
import biz.belcorp.ssicc.dao.spusicc.lec.model.CampanaExigencia;
import biz.belcorp.ssicc.dao.spusicc.lec.model.Canasta;
import biz.belcorp.ssicc.dao.spusicc.lec.model.CanastaDetalle;
import biz.belcorp.ssicc.dao.spusicc.lec.model.CobranzaTramo;
import biz.belcorp.ssicc.dao.spusicc.lec.model.Nivel;
import biz.belcorp.ssicc.dao.spusicc.lec.model.NivelTramo;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ObjetivoCobranza;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaCanastaPremi;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaCorporativo;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaDesempenio;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaEtapaCampana;
import biz.belcorp.ssicc.dao.spusicc.lec.model.Ranking;
import biz.belcorp.ssicc.dao.spusicc.lec.model.RankingNivel;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TramoObjetivoCobranza;


/**
 * <p>
 * <a href="MantenimientoLECProgramaCorporativoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="yrivas@sigcomt.com">Yahir Rivas L.</a>
 *         
 */
@Repository("spusicc.mantenimientoLECProgramaCorporativoDAO")
public class MantenimientoLECProgramaCorporativoDAOIbatis extends BaseDAOiBatis implements MantenimientoLECProgramaCorporativoDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgramaCorporativoList(java.util.Map)
	 */
	public List getProgramaCorporativoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getProgramaCorporativoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNextCodigoProgramaCorporativo()
	 */
	public String getNextCodigoProgramaCorporativo() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNextCodigoProgramaCorporativo", null);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertProgramaCorporativo(biz.belcorp.ssicc.spusicc.lec.model.ProgramaCorporativo)
	 */
	public void insertProgramaCorporativo(ProgramaCorporativo programaCorporativo) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertProgramaCorporativo", programaCorporativo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoDesempenioList(java.util.Map)
	 */
	public List getTipoDesempenioList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoDesempenioList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoNivelList(java.util.Map)
	 */
	public List getTipoNivelList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoNivelList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoMediList(java.util.Map)
	 */
	public List getTipoMediList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoMediList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoMediCobList(java.util.Map)
	 */
	public List getTipoMediCobList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoMediCobList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertProgramaNivel(biz.belcorp.ssicc.spusicc.lec.model.Nivel)
	 */
	public void insertProgramaNivel(Nivel nivel) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertProgramaNivel", nivel);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNextCodTramo()
	 */
	public String getNextCodTramo() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNextCodTramo", null);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNextCodAmbitGeogr()
	 */
	public String getNextCodAmbitGeogr() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNextCodAmbitGeogr", null);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertAmbitoGeografico(biz.belcorp.ssicc.spusicc.lec.model.CobranzaTramo)
	 */
	public void insertCobranzaTramo(CobranzaTramo obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertCobranzaTramo", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertAmbitoGeografico(biz.belcorp.ssicc.spusicc.lec.model.AmbitoGeografico)
	 */
	public void insertAmbitoGeografico(AmbitoGeografico obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertAmbitoGeografico", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertObjetivoPrograma(biz.belcorp.ssicc.spusicc.lec.model.ObjetivoCobranza)
	 */
	public void insertObjetivoCobranza(ObjetivoCobranza obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertObjetivoCobranza", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertTramoObjetivoCobranza(biz.belcorp.ssicc.spusicc.lec.model.TramoObjetivoCobranza)
	 */
	public void insertTramoObjetivoCobranza(TramoObjetivoCobranza obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertTramoObjetivoCobranza", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertBonoCampana(biz.belcorp.ssicc.spusicc.lec.model.BonoCampana)
	 */
	public void insertBonoCampana(BonoCampana obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertBonoCampana", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertBonoLanzamiento(biz.belcorp.ssicc.spusicc.lec.model.BonoLanzamiento)
	 */
	public void insertBonoLanzamiento(BonoLanzamiento obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertBonoLanzamiento", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertBonoLanzamientoProducto(biz.belcorp.ssicc.spusicc.lec.model.BonoLanzamientoProducto)
	 */
	public void insertBonoLanzamientoProducto(BonoLanzamientoProducto obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertBonoLanzamientoProducto", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertBonoNivel(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void insertBonoNivel(BonoNivel obj) {
		if(StringUtils.isBlank(obj.getNroCampMax()))
				obj.setNroCampMax(null);
		if(StringUtils.isBlank(obj.getNroMinIncPed()))
			obj.setNroMinIncPed(null);
	//	if(StringUtils.isBlank(obj.getNroCampMax()))
		//	obj.setNroCampMax(null);
		
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertBonoNivel", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getSecBonoLanzaProdu()
	 */
	public String getSecBonoLanzaProdu(){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getSecBonoLanzaProdu", null);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getSecBonoNivel()
	 */
	public String getSecBonoNivel(){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getSecBonoNivel", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getEncontrarProgramaLecCorporativo(java.lang.String)
	 */
	public Map getEncontrarProgramaLecCorporativo(String periodoActual) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getEncontrarProgramaLecCorporativo", periodoActual);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getIndicadorPosibleLider(java.util.Map)
	 */
	public Integer getIndicadorPosibleLider(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getIndicadorPosibleLider", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getIndicadorPosibleLiderHisto(java.util.Map)
	 */
	public Integer getIndicadorPosibleLiderHisto(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getIndicadorPosibleLiderHisto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#validarAsignacionLiderSeccion(java.util.Map)
	 */
	public String validarAsignacionLiderSeccion(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.validarAsignacionLiderSeccion", params); 
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoComision(java.util.Map)
	 */
	public List getTipoComision(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoComision", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgCanasta(java.util.Map)
	 */
	public List getProgCanasta(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getProgCanasta", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertProgramaCanastaPremi(java.util.Map)
	 */
	public void insertProgramaCanastaPremi(ProgramaCanastaPremi obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertProgramaCanastaPremi", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgramaCanastaPremi()
	 */
	public String getProgramaCanastaPremi(){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getProgramaCanastaPremi", null);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertProgramaEtapaCampana(java.util.Map)
	 */
	public void insertProgramaEtapaCampana(ProgramaEtapaCampana obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertProgramaEtapaCampana", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertProgramaDesempenio(java.util.Map)
	 */
	public void insertProgramaDesempenio(ProgramaDesempenio obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertProgramaDesempenio", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getEtapCampEval(java.util.Map)
	 */
	public List getEtapCampEval(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getEtapCampEval", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoGrupoRegion(java.util.Map)
	 */
	public List getTipoGrupoRegion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoGrupoRegion", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgramaNivelList(java.util.Map)
	 */
	public List getProgramaNivelList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getProgramaNivelList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgramaCobranzaTramoList(java.util.Map)
	 */
	public List getProgramaCobranzaTramoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getProgramaCobranzaTramoList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getObjetivoCobranzaList(java.util.Map)
	 */
	public List getObjetivoCobranzaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getObjetivoCobranzaList", criteria);
	}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTramoObjetivoCobranzaList(java.util.Map)
	 */
	public List getTramoObjetivoCobranzaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTramoObjetivoCobranzaList", criteria);
	}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getAmbitoGeograficoList(java.util.Map)
	 */
	public List getAmbitoGeograficoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getAmbitoGeograficoList", criteria);
	}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgramaCanastaPremiList(java.util.Map)
	 */
	public List getProgramaCanastaPremiList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getProgramaCanastaPremiList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgramaDesempenioList(java.util.Map)
	 */
	public List getProgramaDesempenioList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getProgramaDesempenioList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getProgramaEtapaCampanaList(java.util.Map)
	 */
	public List getProgramaEtapaCampanaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getProgramaEtapaCampanaList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getBonoCampanaList(java.util.Map)
	 */
	public List getBonoCampanaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getBonoCampanaList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getBonoLanzamientoList(java.util.Map)
	 */
	public List getBonoLanzamientoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getBonoLanzamientoList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getBonoNivelList(java.util.Map)
	 */
	public List getBonoNivelList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getBonoNivelList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#executeProcesoLECProcesarClasificacionLideres(java.util.Map)
	 */
	public void executeProcesoLECProcesarClasificacionLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.executeProcesoLECProcesarClasificacionLideres", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoOfertaList()
	 */
	public List getTipoOfertaList() {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoOfertaList");
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertCanasta(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void insertCanasta(Canasta obj) {
		 getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertCanasta",obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getCanastaList(java.util.Map)
	 */
	public List getCanastaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getCanastaLecList",criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertCanastaDetalle(biz.belcorp.ssicc.spusicc.lec.model.CanastaDetalle)
	 */
	public void insertCanastaDetalle(CanastaDetalle obj){
		 getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertCanastaDetalle",obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getCanastaDetalleList(java.util.Map)
	 */
	public List getCanastaDetalleList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getCanastaDetalleList",criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNumeroSecuenciaCanasta()
	 */
	public String getNumeroSecuenciaCanasta(){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNumeroSecuenciaCanasta",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getRegionByTipoGrupoRegion(java.util.Map)
	 */
	public List getRegionByTipoGrupoRegion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getRegionByTipoGrupoRegion", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNumeroSecuenciaCanasta(java.util.Map)
	 */
	public Integer verificarProgramaCruce(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.verificarProgramaCruce",criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertCampanaExigencia(biz.belcorp.ssicc.spusicc.lec.model.CampanaExigencia)
	 */
	public void insertCampanaExigencia(CampanaExigencia obj){
		 getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertCampanaExigencia",obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteCampanaExigencia(biz.belcorp.ssicc.spusicc.lec.model.CampanaExigencia)
	 */
	public void deleteCampanaExigencia(CampanaExigencia obj){
		 getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.deleteCampanaExigencia",obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getCampanaExigenciaList(java.util.Map)
	 */
	public List getCampanaExigenciaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getCampanaExigenciaList", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateProgramaCorporativo(biz.belcorp.ssicc.spusicc.lec.model.ProgramaCorporativo)
	 */
	public void updateProgramaCorporativo(ProgramaCorporativo programaCorporativo) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateProgramaCorporativo", programaCorporativo);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateProgramaNivel(biz.belcorp.ssicc.spusicc.lec.model.Nivel)
	 */
	public void updateProgramaNivel(Nivel nivel) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateProgramaNivel", nivel);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateCobranzaTramo(biz.belcorp.ssicc.spusicc.lec.model.CobranzaTramo)
	 */
	public void updateCobranzaTramo(CobranzaTramo cobranzaTramo) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateCobranzaTramo", cobranzaTramo);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteCobranzaTramo(biz.belcorp.ssicc.spusicc.lec.model.CobranzaTramo)
	 */
	public void deleteCobranzaTramo(CobranzaTramo cobranzaTramo) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteCobranzaTramo", cobranzaTramo);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteAmbitoGeografico(biz.belcorp.ssicc.spusicc.lec.model.AmbitoGeografico)
	 */
	public void deleteAmbitoGeografico(AmbitoGeografico ambitoGeografico) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteAmbitoGeografico", ambitoGeografico);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteAmbitoGeograficoFisico(biz.belcorp.ssicc.spusicc.lec.model.AmbitoGeografico)
	 */
	public void deleteAmbitoGeograficoFisico(AmbitoGeografico ambitoGeografico) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.deleteAmbitoGeograficoFisico", ambitoGeografico);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteProgramaNivel(biz.belcorp.ssicc.spusicc.lec.model.Nivel)
	 */
	public void deleteProgramaNivel(Nivel nivel) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteProgramaNivel", nivel);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getBonoLanzamientoProductoList(java.util.Map)
	 */
	public List getBonoLanzamientoProductoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getBonoLanzamientoProductoList",criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertNivelTramo(biz.belcorp.ssicc.spusicc.lec.model.NivelTramo)
	 */
	public void insertNivelTramo(NivelTramo nivelTramo) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.insertNivelTramo", nivelTramo);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNivelTramoList(java.util.Map)
	 */
	public List getNivelTramoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getNivelTramoList",criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#udateAmbitoGeografico(biz.belcorp.ssicc.spusicc.lec.model.AmbitoGeografico)
	 */
	public void updateAmbitoGeografico(AmbitoGeografico obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateAmbitoGeografico", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateCampanaExigencia(biz.belcorp.ssicc.spusicc.lec.model.CampanaExigencia)
	 */
	public void updateCampanaExigencia(CampanaExigencia obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateCampanaExigencia", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteObjetivoCobranza(biz.belcorp.ssicc.spusicc.lec.model.ObjetivoCobranza)
	 */
	public void deleteObjetivoCobranza(ObjetivoCobranza obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteObjetivoCobranza", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateObjetivoCobranza(biz.belcorp.ssicc.spusicc.lec.model.ObjetivoCobranza)
	 */
	public void updateObjetivoCobranza(ObjetivoCobranza obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateObjetivoCobranza", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteTramoObjetivoCobranza(biz.belcorp.ssicc.spusicc.lec.model.TramoObjetivoCobranza)
	 */
	public void deleteTramoObjetivoCobranza(TramoObjetivoCobranza obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteTramoObjetivoCobranza", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateTramoObjetivoCobranza(biz.belcorp.ssicc.spusicc.lec.model.TramoObjetivoCobranza)
	 */
	public void updateTramoObjetivoCobranza(TramoObjetivoCobranza obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateTramoObjetivoCobranza", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteBonoCampana(biz.belcorp.ssicc.spusicc.lec.model.BonoCampana)
	 */
	public void deleteBonoCampana(BonoCampana obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteBonoCampana", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateBonoCampana(biz.belcorp.ssicc.spusicc.lec.model.BonoCampana)
	 */
	public void updateBonoCampana(BonoCampana obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateBonoCampana", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteBonoLanzamiento(biz.belcorp.ssicc.spusicc.lec.model.BonoLanzamiento)
	 */
	public void deleteBonoLanzamiento(BonoLanzamiento obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteBonoLanzamiento", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateBonoLanzamiento(biz.belcorp.ssicc.spusicc.lec.model.BonoLanzamiento)
	 */
	public void updateBonoLanzamiento(BonoLanzamiento obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateBonoLanzamiento", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteBonoLanzamientoProducto(biz.belcorp.ssicc.spusicc.lec.model.BonoLanzamientoProducto)
	 */
	public void deleteBonoLanzamientoProducto(BonoLanzamientoProducto obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteBonoLanzamientoProducto", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateBonoLanzamientoProducto(biz.belcorp.ssicc.spusicc.lec.model.BonoLanzamientoProducto)
	 */
	public void updateBonoLanzamientoProducto(BonoLanzamientoProducto obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateBonoLanzamientoProducto", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteTramoObjetivoCobranza(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void deleteBonoNivel(BonoNivel obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteBonoNivel", obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateBonoNivel(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void updateBonoNivel(BonoNivel obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateBonoNivel", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getRegionByZona(java.util.Map)
	 */
	public Map getRegionByZona(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getRegionByZona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getBonoLanzamientoMaxNroLanzamiento(java.util.Map)
	 */
	public Integer getBonoLanzamientoMaxNroLanzamiento(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getBonoLanzamientoMaxNroLanzamiento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoBonoLanzamiento(java.util.Map)
	 */
	public List getTipoBonoLanzamiento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoBonoLanzamiento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getExisteBonoLanzamiento(java.util.Map)
	 */
	public Integer getExisteBonoLanzamiento(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getExisteBonoLanzamiento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoTramo(java.util.Map)
	 */
	public List getTipoTramo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoTramo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateNivelTramo(biz.belcorp.ssicc.spusicc.lec.model.NivelTramo)
	 */
	public void updateNivelTramo(NivelTramo obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateNivelTramo", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteNivelTramo(biz.belcorp.ssicc.spusicc.lec.model.NivelTramo)
	 */
	public void deleteNivelTramo(NivelTramo obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteNivelTramo", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateCanasta(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void updateCanasta(Canasta obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateCanasta", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateCanastaDetalle(biz.belcorp.ssicc.spusicc.lec.model.CanastaDetalle)
	 */
	public void updateCanastaDetalle(CanastaDetalle obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateCanastaDetalle", obj);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteCanastaDetalle(biz.belcorp.ssicc.spusicc.lec.model.CanastaDetalle)
	 */
	public void deleteCanastaDetalle(CanastaDetalle obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteCanastaDetalle", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteCanasta(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void deleteCanasta(Canasta obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteCanasta", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateCanastaNumProductos(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void updateCanastaNumProductos(Canasta obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateCanastaNumProductos", obj);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getMenorPesoNivel(java.util.Map)
	 */
	public Integer getMenorPesoNivel(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getMenorPesoNivel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getPesoNivel(java.util.Map)
	 */
	public Integer getPesoNivel(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getPesoNivel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteProgramaCanastaPremi(biz.belcorp.ssicc.spusicc.lec.model.ProgramaCanastaPremi)
	 */
	public void deleteProgramaCanastaPremi(ProgramaCanastaPremi obj) {
		
		try
		{
			getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.removeProgramaCanastaPremi", obj);
		}
		catch(Exception ex)
		{
			getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteProgramaCanastaPremi", obj);
		}		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoRanking()
	 */
	public List getTipoRanking() {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoRanking");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNivelesRanking(java.util.Map)
	 */
	public List getNivelesRanking(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getNivelesRanking", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNumeroSecuenciaRanking()
	 */
	public String getNumeroSecuenciaRanking() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNumeroSecuenciaRanking",null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getRankingList(java.util.Map)
	 */
	public List getRankingList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getRankingList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertRanking(biz.belcorp.ssicc.spusicc.lec.model.Ranking, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRanking(Ranking ranking, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertRanking", ranking);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateRanking(biz.belcorp.ssicc.spusicc.lec.model.Ranking, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRanking(Ranking ranking, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateRanking", ranking);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNivelRanking(RankingNivel rankingNivel, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertNivelRanking", rankingNivel);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNivelRanking(RankingNivel rankingNivel, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateNivelRanking", rankingNivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel)
	 */
	public RankingNivel getNivelRanking(RankingNivel rankingNivel) {
		return (RankingNivel)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNivelRanking", rankingNivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteNivelRanking(RankingNivel rankingNivel, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteNivelRanking", rankingNivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteRanking(biz.belcorp.ssicc.spusicc.lec.model.Ranking, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteRanking(Ranking ranking, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteRanking", ranking);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteProgramaEtapaCampana(biz.belcorp.ssicc.spusicc.lec.model.ProgramaEtapaCampana)
	 */
	public void deleteProgramaEtapaCampana(ProgramaEtapaCampana obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteProgramaEtapaCampana", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteProgramaDesempenio(biz.belcorp.ssicc.spusicc.lec.model.ProgramaDesempenio)
	 */
	public void deleteProgramaDesempenio(ProgramaDesempenio obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteProgramaDesempenio", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#updateProgramaDesempenio(biz.belcorp.ssicc.spusicc.lec.model.ProgramaDesempenio)
	 */
	public void updateProgramaDesempenio(ProgramaDesempenio obj) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateProgramaDesempenio", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getListaProgramasActivosCampanna(java.util.Map)
	 */
	public Integer getListaProgramasActivosCampanna(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getListaProgramasActivosCampanna", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getVerificarEliminarPrograma(java.util.Map)
	 */
	public Integer getVerificarEliminarPrograma(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getVerificarEliminarPrograma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#executeEliminarPrograma(java.util.Map)
	 */
	public void executeEliminarPrograma(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.executeEliminarPrograma", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getLECPosibleLider(java.util.Map)
	 */
	public Integer getLECPosibleLider(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getLECPosibleLider", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertBonoLEC(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void insertBonoLEC(BonoNivel obj) {
		getSqlMapClientTemplate().insert("spusicc.lec.MantenimientoLECSQL.insertBonoLEC", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteBonoLEC(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void deleteBonoLEC(BonoNivel obj) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteBonoLEC", obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getBonoLECList(java.util.Map)
	 */
	public List getBonoLECList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getBonoLECList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getGruposPago(java.util.Map)
	 */
	public List getGruposPago(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getGruposPago", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getRegionesByTipoGrupo(java.util.Map)
	 */
	public List getRegionesByTipoGrupo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getRegionesByTipoGrupo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getZonasByTipoGrupo(java.util.Map)
	 */
	public List getZonasByTipoGrupo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getZonasByTipoGrupo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTipoGrupo(java.util.Map)
	 */
	public String getTipoGrupo(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getTipoGrupo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteBonoNivelRango(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
    public void deleteBonoNivelRango(BonoNivel obj) {
		
		try
		{
			getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.removeBonoNivelRango", obj);
		}
		catch(Exception ex)
		{
			getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteBonoNivelRango", obj);
		}
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteCobranzaObjetivoIndicador(java.util.Map)
	 */
	public void deleteCobranzaObjetivoIndicador(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteCobranzaObjetivoIndicador", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteCobranzaObjetivoTramoIndicador(java.util.Map)
	 */
	public void deleteCobranzaObjetivoTramoIndicador(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteCobranzaObjetivoTramoIndicador", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#deleteAmbitoGeograficoIndicador(java.util.Map)
	 */
	public void deleteAmbitoGeograficoIndicador(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.lec.MantenimientoLECSQL.deleteAmbitoGeograficoIndicador", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTiposBono(java.util.Map)
	 */
	public List getTiposBono(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTiposBono", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTiposPremiacion()
	 */
	public List getTiposPremiacion() {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTiposPremiacion");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTiposCanasta()
	 */
	public List getTiposCanasta() {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTiposCanasta");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getRegionesByTiposGrupo(java.util.Map)
	 */
	public List getRegionesByTiposGrupo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getRegionesByTiposGrupo", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getZonasByTiposGrupo(java.util.Map)
	 */
	public List getZonasByTiposGrupo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getZonasByTiposGrupo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getTiposGrupo(java.util.Map)
	 */
	public String getTiposGrupo(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getTiposGrupo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECProgramaCorporativoDAO#getLECEstadoPagosList(java.util.Map)
	 */
	public List getLECEstadoPagosList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getLECEstadoPagosList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECProgramaCorporativoDAO#getNivelRangoIncentivos(java.util.Map)
	 */
	public List getNivelRangoIncentivos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getNivelRangoIncentivos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECProgramaCorporativoDAO#obtieneNivelCodigoRango(java.lang.String)
	 */
	public String obtieneNivelCodigoRango(String codigo) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.obtieneNivelCodigoRango", codigo);
	}
	
	
	
}