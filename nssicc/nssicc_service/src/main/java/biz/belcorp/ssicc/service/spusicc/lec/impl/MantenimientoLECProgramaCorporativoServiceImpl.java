package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;

/**
 * <p>
 * <a href="MantenimientoLECProgramaCorporativoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="yrivas@sigcomt.com">Yahir Rivas L.</a>
 *         
 */
@Service("spusicc.mantenimientoLECProgramaCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLECProgramaCorporativoServiceImpl extends BaseService implements MantenimientoLECProgramaCorporativoService {

	@Resource(name="spusicc.mantenimientoLECProgramaCorporativoDAO")
	private MantenimientoLECProgramaCorporativoDAO mantenimientoLECProgramaCorporativoDAO;
	
	public String getNextCodigoProgramaCorporativo() {
		
		return mantenimientoLECProgramaCorporativoDAO.getNextCodigoProgramaCorporativo();
	}
	

	public void insertProgramaCorporativo(
			ProgramaCorporativo programaCorporativo) {
		mantenimientoLECProgramaCorporativoDAO.insertProgramaCorporativo(programaCorporativo);
		
	}

	public List getProgramaCorporativoList(Map criteria) {
	
		return mantenimientoLECProgramaCorporativoDAO.getProgramaCorporativoList(criteria);
	}

	public List getTipoNivelList(Map criteria) {
		
		return mantenimientoLECProgramaCorporativoDAO.getTipoNivelList(criteria);
	}
	
	public List getTipoMediList(Map criteria) {
		
		return mantenimientoLECProgramaCorporativoDAO.getTipoMediList(criteria);
	}
	
	public List getTipoMediCobList(Map criteria){
		
		return mantenimientoLECProgramaCorporativoDAO.getTipoMediCobList(criteria);
	}	

	public void insertProgramaNivel(Nivel nivel) {
		mantenimientoLECProgramaCorporativoDAO.insertProgramaNivel(nivel);
	}

	public List getTipoDesempenioList(Map criteria) {
		
		return mantenimientoLECProgramaCorporativoDAO.getTipoDesempenioList(criteria);
	}
	public String getNextCodTramo() {
		return mantenimientoLECProgramaCorporativoDAO.getNextCodTramo();		
	}
	public String getNextCodAmbitGeogr() {
		return mantenimientoLECProgramaCorporativoDAO.getNextCodAmbitGeogr();		
	}
	public void insertCobranzaTramo(CobranzaTramo obj){
		 mantenimientoLECProgramaCorporativoDAO.insertCobranzaTramo(obj);		
	}
	public void insertAmbitoGeografico(AmbitoGeografico obj) {
		 mantenimientoLECProgramaCorporativoDAO.insertAmbitoGeografico(obj);
	}
	public void insertObjetivoCobranza(ObjetivoCobranza obj) {
		 mantenimientoLECProgramaCorporativoDAO.insertObjetivoCobranza(obj);
	}
	public void insertTramoObjetivoCobranza(TramoObjetivoCobranza obj) {
		 mantenimientoLECProgramaCorporativoDAO.insertTramoObjetivoCobranza(obj);
	}
	public void insertBonoCampana(BonoCampana obj){
		 mantenimientoLECProgramaCorporativoDAO.insertBonoCampana(obj);
	}
	public void insertBonoLanzamiento(BonoLanzamiento obj){
		 mantenimientoLECProgramaCorporativoDAO.insertBonoLanzamiento(obj);
	}
	public void insertBonoLanzamientoProducto(BonoLanzamientoProducto obj){
		 mantenimientoLECProgramaCorporativoDAO.insertBonoLanzamientoProducto(obj);
	}
	public void insertBonoNivel(BonoNivel obj){
		 mantenimientoLECProgramaCorporativoDAO.insertBonoNivel(obj);
	}
	
	public String getSecBonoLanzaProdu() {		
		return mantenimientoLECProgramaCorporativoDAO.getSecBonoLanzaProdu();
	}
	public String getSecBonoNivel() {		
		return mantenimientoLECProgramaCorporativoDAO.getSecBonoNivel();
	}
	
	public List getTipoComision(Map criteria){		
		return mantenimientoLECProgramaCorporativoDAO.getTipoComision(criteria);
	}
	
	public List getProgCanasta(Map criteria) {		
		return mantenimientoLECProgramaCorporativoDAO.getProgCanasta(criteria);
	}
	
	public void insertProgramaCanastaPremi(ProgramaCanastaPremi obj){
		 mantenimientoLECProgramaCorporativoDAO.insertProgramaCanastaPremi(obj);
	}
	
	public String getProgramaCanastaPremi(){
		return mantenimientoLECProgramaCorporativoDAO.getProgramaCanastaPremi();
	}	
		
	public void insertProgramaEtapaCampana(ProgramaEtapaCampana obj){
			 mantenimientoLECProgramaCorporativoDAO.insertProgramaEtapaCampana(obj);
	} 
	
	public void insertProgramaDesempenio(ProgramaDesempenio obj){
		 mantenimientoLECProgramaCorporativoDAO.insertProgramaDesempenio(obj);
	}	
	
	public List getEtapCampEval(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getEtapCampEval(criteria);
	}
	
	public List getTipoGrupoRegion(Map criteria) {		
		return mantenimientoLECProgramaCorporativoDAO.getTipoGrupoRegion(criteria);
	}
	public List getProgramaNivelList(Map criteria){		
		return mantenimientoLECProgramaCorporativoDAO.getProgramaNivelList(criteria);
	}

/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getEncontrarProgramaLetCorporativo(java.lang.String)
	 */
	public Map getEncontrarProgramaLecCorporativo(String periodoActual) {
		return mantenimientoLECProgramaCorporativoDAO.getEncontrarProgramaLecCorporativo(periodoActual);
	}
	
	public List getProgramaCobranzaTramoList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getProgramaCobranzaTramoList(criteria);
	}
	public List getObjetivoCobranzaList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getObjetivoCobranzaList(criteria);
	}
	
	public List getTramoObjetivoCobranzaList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getTramoObjetivoCobranzaList(criteria);
	}
	
	public List getAmbitoGeograficoList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getAmbitoGeograficoList(criteria);
	}
	
	public List getProgramaCanastaPremiList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getProgramaCanastaPremiList(criteria);
	}
	
	public List getProgramaDesempenioList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getProgramaDesempenioList(criteria);
	}
	
	public List getProgramaEtapaCampanaList(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getProgramaEtapaCampanaList(criteria);
	}
	
	
	public List getBonoCampanaList(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getBonoCampanaList(criteria);
	}
	public List getBonoLanzamientoList(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getBonoLanzamientoList(criteria);
	}
	
	public List getBonoNivelList(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getBonoNivelList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getTipoOfertaList()
	 */
	public List getTipoOfertaList() {
		return mantenimientoLECProgramaCorporativoDAO.getTipoOfertaList();
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertCanasta(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void insertCanasta(Canasta obj) {
		mantenimientoLECProgramaCorporativoDAO.insertCanasta(obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getCanastaList(java.util.Map)
	 */
	public List getCanastaList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getCanastaList(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#insertCanastaDetalle(biz.belcorp.ssicc.spusicc.lec.model.CanastaDetalle)
	 */
	public void insertCanastaDetalle(CanastaDetalle obj){
		mantenimientoLECProgramaCorporativoDAO.insertCanastaDetalle(obj);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getCanastaDetalleList(java.util.Map)
	 */
	public List getCanastaDetalleList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getCanastaDetalleList(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECProgramaCorporativoDAO#getNumeroSecuenciaCanasta()
	 */
	public String getNumeroSecuenciaCanasta(){
		return mantenimientoLECProgramaCorporativoDAO.getNumeroSecuenciaCanasta();
	}
	
	public List getRegionByTipoGrupoRegion(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getRegionByTipoGrupoRegion(criteria);
	}
	public Integer verificarProgramaCruce(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.verificarProgramaCruce(criteria);
	}
	public void insertCampanaExigencia(CampanaExigencia obj){
		mantenimientoLECProgramaCorporativoDAO.insertCampanaExigencia(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteCampanaExigencia(biz.belcorp.ssicc.spusicc.lec.model.CampanaExigencia)
	 */
	public void deleteCampanaExigencia(CampanaExigencia obj){
		mantenimientoLECProgramaCorporativoDAO.deleteCampanaExigencia(obj);
	}
	
	public List getCampanaExigenciaList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getCampanaExigenciaList(criteria);
	}
	public void updateProgramaCorporativo(ProgramaCorporativo programaCorporativo){
		mantenimientoLECProgramaCorporativoDAO.updateProgramaCorporativo(programaCorporativo);
	}
	public void updateProgramaNivel(Nivel nivel) {
		mantenimientoLECProgramaCorporativoDAO.updateProgramaNivel(nivel);
	}
	public void updateCobranzaTramo(CobranzaTramo cobranzaTramo) {
		mantenimientoLECProgramaCorporativoDAO.updateCobranzaTramo(cobranzaTramo);
	}
	public void deleteCobranzaTramo(CobranzaTramo cobranzaTramo){
		mantenimientoLECProgramaCorporativoDAO.deleteCobranzaTramo(cobranzaTramo);
	}
	public void deleteAmbitoGeografico(AmbitoGeografico ambitoGeografico) {
		mantenimientoLECProgramaCorporativoDAO.deleteAmbitoGeografico(ambitoGeografico);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteAmbitoGeograficoFisico(biz.belcorp.ssicc.spusicc.lec.model.AmbitoGeografico)
	 */
	public void deleteAmbitoGeograficoFisico(AmbitoGeografico ambitoGeografico) {
		mantenimientoLECProgramaCorporativoDAO.deleteAmbitoGeograficoFisico(ambitoGeografico);
	}
	
	public void deleteProgramaNivel(Nivel nivel) {
		mantenimientoLECProgramaCorporativoDAO.deleteProgramaNivel(nivel);
	}
	public List getBonoLanzamientoProductoList(Map criteria){
		return mantenimientoLECProgramaCorporativoDAO.getBonoLanzamientoProductoList(criteria);
	}
	public void insertNivelTramo(NivelTramo obj) {
		mantenimientoLECProgramaCorporativoDAO.insertNivelTramo(obj);
	}
	
	public List getNivelTramoList(Map criteria) {
	 return	mantenimientoLECProgramaCorporativoDAO.getNivelTramoList(criteria);
	}
	public void updateAmbitoGeografico(AmbitoGeografico obj){
		mantenimientoLECProgramaCorporativoDAO.updateAmbitoGeografico(obj);
	}
	public void updateCampanaExigencia(CampanaExigencia obj){
		mantenimientoLECProgramaCorporativoDAO.updateCampanaExigencia(obj);
	}
	public void deleteObjetivoCobranza(ObjetivoCobranza obj){
	mantenimientoLECProgramaCorporativoDAO.deleteObjetivoCobranza(obj);
	}
	
	public void updateObjetivoCobranza(ObjetivoCobranza obj) {
		mantenimientoLECProgramaCorporativoDAO.updateObjetivoCobranza(obj);
	}
	public void deleteTramoObjetivoCobranza(TramoObjetivoCobranza obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteTramoObjetivoCobranza(obj);
	}
	public void updateTramoObjetivoCobranza(TramoObjetivoCobranza obj){
		mantenimientoLECProgramaCorporativoDAO.updateTramoObjetivoCobranza(obj);
	}
	public void deleteBonoCampana(BonoCampana obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteBonoCampana(obj);
		}
	public void updateBonoCampana(BonoCampana obj) {
		mantenimientoLECProgramaCorporativoDAO.updateBonoCampana(obj);
		}
	public void deleteBonoLanzamiento(BonoLanzamiento obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteBonoLanzamiento(obj);
		}
	public void updateBonoLanzamiento(BonoLanzamiento obj) {
		mantenimientoLECProgramaCorporativoDAO.updateBonoLanzamiento(obj);
		}
	public void deleteBonoLanzamientoProducto(BonoLanzamientoProducto obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteBonoLanzamientoProducto(obj);
		}
	public void updateBonoLanzamientoProducto(BonoLanzamientoProducto obj) {
		mantenimientoLECProgramaCorporativoDAO.updateBonoLanzamientoProducto(obj);
		}
	public void deleteBonoNivel(BonoNivel obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteBonoNivel(obj);
		}
	public void updateBonoNivel(BonoNivel obj) {
		mantenimientoLECProgramaCorporativoDAO.updateBonoNivel(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getRegionByZona(java.util.Map)
	 */
	public Map getRegionByZona(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getRegionByZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getBonoLanzamientoMaxNroLanzamiento(java.util.Map)
	 */
	public Integer getBonoLanzamientoMaxNroLanzamiento(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getBonoLanzamientoMaxNroLanzamiento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getExisteBonoLanzamiento(java.util.Map)
	 */
	public Integer getExisteBonoLanzamiento(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getExisteBonoLanzamiento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getTipoTramo(java.util.Map)
	 */
	public List getTipoTramo(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getTipoTramo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#updateNivelTramo(biz.belcorp.ssicc.spusicc.lec.model.NivelTramo)
	 */
	public void updateNivelTramo(NivelTramo obj) {
		mantenimientoLECProgramaCorporativoDAO.updateNivelTramo(obj);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteNivelTramo(biz.belcorp.ssicc.spusicc.lec.model.NivelTramo)
	 */
	public void deleteNivelTramo(NivelTramo obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteNivelTramo(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#updateCanasta(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void updateCanasta(Canasta obj)  {
		mantenimientoLECProgramaCorporativoDAO.updateCanasta(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#updateCanastaDetalle(biz.belcorp.ssicc.spusicc.lec.model.CanastaDetalle)
	 */
	public void updateCanastaDetalle(CanastaDetalle obj){
		mantenimientoLECProgramaCorporativoDAO.updateCanastaDetalle(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteCanastaDetalle(biz.belcorp.ssicc.spusicc.lec.model.CanastaDetalle)
	 */
	public void deleteCanastaDetalle(CanastaDetalle obj){
		mantenimientoLECProgramaCorporativoDAO.deleteCanastaDetalle(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteCanasta(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void deleteCanasta(Canasta obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteCanasta(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#updateCanastaNumProductos(biz.belcorp.ssicc.spusicc.lec.model.Canasta)
	 */
	public void updateCanastaNumProductos(Canasta obj) {
		mantenimientoLECProgramaCorporativoDAO.updateCanastaNumProductos(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getMenorPesoNivel(java.util.Map)
	 */
	public Integer getMenorPesoNivel(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getMenorPesoNivel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getPesoNivel(java.util.Map)
	 */
	public Integer getPesoNivel(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getPesoNivel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteProgramaCanastaPremi(biz.belcorp.ssicc.spusicc.lec.model.ProgramaCanastaPremi)
	 */
	public void deleteProgramaCanastaPremi(ProgramaCanastaPremi obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteProgramaCanastaPremi(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getTipoRanking()
	 */
	public List getTipoRanking() {
		return mantenimientoLECProgramaCorporativoDAO.getTipoRanking();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getNivelesRanking(java.util.Map)
	 */
	public List getNivelesRanking(Map params) {
		return mantenimientoLECProgramaCorporativoDAO.getNivelesRanking(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getNumeroSecuenciaRanking()
	 */
	public String getNumeroSecuenciaRanking() {
		return mantenimientoLECProgramaCorporativoDAO.getNumeroSecuenciaRanking();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getRankingList(java.util.Map)
	 */
	public List getRankingList(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getRankingList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#insertRanking(biz.belcorp.ssicc.spusicc.lec.model.Ranking, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRanking(Ranking ranking, Usuario usuario) {
		mantenimientoLECProgramaCorporativoDAO.insertRanking(ranking, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#updateRanking(biz.belcorp.ssicc.spusicc.lec.model.Ranking, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRanking(Ranking ranking, Usuario usuario) {
		mantenimientoLECProgramaCorporativoDAO.updateRanking(ranking, usuario);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#insertNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNivelRanking(RankingNivel rankingNivel, Usuario usuario) {
		mantenimientoLECProgramaCorporativoDAO.insertNivelRanking(rankingNivel, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#updateNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNivelRanking(RankingNivel rankingNivel, Usuario usuario) {
		mantenimientoLECProgramaCorporativoDAO.updateNivelRanking(rankingNivel, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel)
	 */
	public RankingNivel getNivelRanking(RankingNivel rankingNivel) {
		return mantenimientoLECProgramaCorporativoDAO.getNivelRanking(rankingNivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteNivelRanking(biz.belcorp.ssicc.spusicc.lec.model.RankingNivel, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteNivelRanking(RankingNivel rankingNivel, Usuario usuario) {
		mantenimientoLECProgramaCorporativoDAO.deleteNivelRanking(rankingNivel, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteRanking(biz.belcorp.ssicc.spusicc.lec.model.Ranking, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteRanking(Ranking ranking, Usuario usuario) {
		mantenimientoLECProgramaCorporativoDAO.deleteRanking(ranking, usuario);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteProgramaEtapaCampana(biz.belcorp.ssicc.spusicc.lec.model.ProgramaEtapaCampana)
	 */
	public void deleteProgramaEtapaCampana(ProgramaEtapaCampana obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteProgramaEtapaCampana(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteProgramaDesempenio(biz.belcorp.ssicc.spusicc.lec.model.ProgramaDesempenio)
	 */
	public void deleteProgramaDesempenio(ProgramaDesempenio obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteProgramaDesempenio(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#updateProgramaDesempenio(biz.belcorp.ssicc.spusicc.lec.model.ProgramaDesempenio)
	 */
	public void updateProgramaDesempenio(ProgramaDesempenio obj) {
		mantenimientoLECProgramaCorporativoDAO.updateProgramaDesempenio(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getListaProgramasActivosCampanna(java.util.Map)
	 */
	public Integer getListaProgramasActivosCampanna(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getListaProgramasActivosCampanna(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getVerificarEliminarPrograma(java.util.Map)
	 */
	public Integer getVerificarEliminarPrograma(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getVerificarEliminarPrograma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#executeEliminarPrograma(java.util.Map)
	 */
	public void executeEliminarPrograma(Map criteria) {
		mantenimientoLECProgramaCorporativoDAO.executeEliminarPrograma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#insertBonoLEC(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void insertBonoLEC(BonoNivel obj) {
		mantenimientoLECProgramaCorporativoDAO.insertBonoLEC(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteBonoLEC(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void deleteBonoLEC(BonoNivel obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteBonoLEC(obj);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getBonoLECList(java.util.Map)
	 */
	public List getBonoLECList(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getBonoLECList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getGruposPago(java.util.Map)
	 */
	public List getGruposPago(Map map) {
		return mantenimientoLECProgramaCorporativoDAO.getGruposPago(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteBonoNivelRango(biz.belcorp.ssicc.spusicc.lec.model.BonoNivel)
	 */
	public void deleteBonoNivelRango(BonoNivel obj) {
		mantenimientoLECProgramaCorporativoDAO.deleteBonoNivelRango(obj);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteCobranzaObjetivoIndicador(java.util.Map)
	 */
	public void deleteCobranzaObjetivoIndicador(Map criteria) {
		mantenimientoLECProgramaCorporativoDAO.deleteCobranzaObjetivoIndicador(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteCobranzaObjetivoTramoIndicador(java.util.Map)
	 */
	public void deleteCobranzaObjetivoTramoIndicador(Map criteria) {
		mantenimientoLECProgramaCorporativoDAO.deleteCobranzaObjetivoTramoIndicador(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#deleteAmbitoGeograficoIndicador(java.util.Map)
	 */
	public void deleteAmbitoGeograficoIndicador(Map criteria) {
		mantenimientoLECProgramaCorporativoDAO.deleteAmbitoGeograficoIndicador(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getTiposBono(java.util.Map)
	 */
	public List getTiposBono(Map params) {
		return mantenimientoLECProgramaCorporativoDAO.getTiposBono(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getTiposPremiacion()
	 */
	public List getTiposPremiacion() {
		return mantenimientoLECProgramaCorporativoDAO.getTiposPremiacion();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.MantenimientoLECProgramaCorporativoService#getTiposCanasta()
	 */
	public List getTiposCanasta() {
		return mantenimientoLECProgramaCorporativoDAO.getTiposCanasta();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService#getLECEstadoPagosList(java.util.Map)
	 */
	public List getLECEstadoPagosList(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getLECEstadoPagosList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService#getNivelRangoIncentivos(java.util.Map)
	 */
	public List getNivelRangoIncentivos(Map criteria) {
		return mantenimientoLECProgramaCorporativoDAO.getNivelRangoIncentivos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService#obtieneNivelCodigoRango(java.lang.String)
	 */
	public String obtieneNivelCodigoRango(String codigo) {
		return mantenimientoLECProgramaCorporativoDAO.obtieneNivelCodigoRango(codigo);
	}	
	
	

}