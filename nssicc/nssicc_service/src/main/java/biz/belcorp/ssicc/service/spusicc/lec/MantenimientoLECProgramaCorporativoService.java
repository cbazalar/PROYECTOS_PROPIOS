package biz.belcorp.ssicc.service.spusicc.lec;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
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
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoLECProgramaCorporativoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="yrivas@sigcomt.com">Yahir Rivas L.</a>
 *         
 */
public interface MantenimientoLECProgramaCorporativoService extends Service{
	
	/**
	 * Devuelve el siguiente codigo del Programa Corporativo
	 * @return
	 */
	public String getNextCodigoProgramaCorporativo();
	
	/**
	 * Mtodo que inserta Programa Corporativo
	 * @param criteria
	 */
	public void insertProgramaCorporativo(ProgramaCorporativo programaCorporativo);
	/**
	 * Mtodo que lista los Programas Corporativos
	 * @param criteria
	 * @return
	 */
	public List getProgramaCorporativoList(Map criteria);
	

	/**
	 * Mtodo que lista los tipos de desempeo
	 * @param criteria
	 * @return
	 */
	public List getTipoDesempenioList(Map criteria);
	
	/**
	 * Mtodo que lista los tipos de nivel
     * @param criteria
	 * @return
	 */
	public List getTipoNivelList(Map criteria);
	/**
	 * Mtodo que lista los tipos de Medicion
     * @param criteria
	 * @return
	 */
	public List getTipoMediList(Map criteria);
	/**
	 * Mtodo que lista los tipos de Medicion Cobranza
     * @param criteria
	 * @return
	 */
	public List getTipoMediCobList(Map criteria);
	/**
	 * Mtodo que inserta Programa Nivel
	 * @param criteria
	 */	
	public void insertProgramaNivel(Nivel nivel);
	/**
	 * Mtodo obtiene la secuencia de Tramo
	 */
	public String getNextCodTramo() ;
	/**
	 * Mtodo obtiene la secuencia de Ambito Geografico
	 */
	public String getNextCodAmbitGeogr() ;
	/**
	 * Mtodo que inserta Cobranza tramo
	 * @param criteria
	 */
	public void insertCobranzaTramo(CobranzaTramo obj) ;
	/**
	 * Mtodo que inserta Ambito Geografico
	 * @param criteria
	 */
	
	public void insertAmbitoGeografico(AmbitoGeografico obj) ;
	/**
	 * Mtodo que inserta Objetivo Cobranza
	 * @param criteria
	 */
	public void insertObjetivoCobranza(ObjetivoCobranza obj) ;
	/**
	 * Mtodo que inserta Tramo Objetivo Cobranza
	 * @param criteria
	 */
	public void insertTramoObjetivoCobranza(TramoObjetivoCobranza obj) ;
	/**
	 * Mtodo que inserta Bono Campana
	 * @param criteria
	 */
	public void insertBonoCampana(BonoCampana obj) ;
	/**
	 * Mtodo que inserta Bono Lanzamiento
	 * @param criteria
	 */
	public void insertBonoLanzamiento(BonoLanzamiento obj);
	/**
	 * Mtodo que inserta Bono Lanzamiento Producto
	 * @param criteria
	 */
	public void insertBonoLanzamientoProducto(BonoLanzamientoProducto obj) ;
	/**
	 * Mtodo que inserta Bono Nivel
	 * @param criteria
	 */
	public void insertBonoNivel(BonoNivel obj);
	/**
	 * Devuelve el siguiente codigo BonoLanzaProdu
	 * @return
	 */
	public String getSecBonoLanzaProdu();
	/**
	 * Devuelve el siguiente codigo BonoNivel
	 * @return
	 */
	public String getSecBonoNivel();
	/**
	 * Mtodo que lista los tipos de comisiones
     * @param criteria
	 * @return
	 */	
	public List getTipoComision(Map criteria) ;
	/**
	 * Mtodo que lista programa de canastas
     * @param criteria
	 * @return
	 */	
	public List getProgCanasta(Map criteria) ;
	/**
	 * Mtodo inserta ProgramaCanastaPremi
     * @param ProgramaCanastaPremi
	 * @return
	 */	
	public void insertProgramaCanastaPremi(ProgramaCanastaPremi obj) ;
	/**
	 * Mtodo devuelve secuencia de Programa Canasta Premi
     * @param 
	 * @return
	 */	
	public String getProgramaCanastaPremi();
	/**
	 * Mtodo inserta Programa Etapa Campana
     * @param ProgramaEtapaCampana
	 * @return
	 */	
	public void insertProgramaEtapaCampana(ProgramaEtapaCampana obj) ;
	/**
	 * Mtodo inserta Programa Desempenio
     * @param ProgramaDesempenio
	 * @return
	 */	
	public void insertProgramaDesempenio(ProgramaDesempenio obj) ;
	/**
	 * Mtodo que lista Etapa Campana de Evaluacion
     * @param criteria
	 * @return
	 */	
	public List getEtapCampEval(Map criteria);
	
	/**
	 * Mtodo que lista grupoRegion
     * @param criteria
	 * @return
	 */	
	public List getTipoGrupoRegion(Map criteria);
	/**
	 * Mtodo que lista programa nivel
     * @param criteria
	 * @return
	 */	
	public List getProgramaNivelList(Map criteria);
	
	
	public Map getEncontrarProgramaLecCorporativo(String periodoActual);
	
	/**
	 * Mtodo que lista Programa Cobranza Tramo
     * @param criteria
	 * @return
	 */	
	public List getProgramaCobranzaTramoList(Map criteria);
	/**
	 * Mtodo que lista Objetivo Cobranza
     * @param criteria
	 * @return
	 */	
	public List getObjetivoCobranzaList(Map criteria);
	/**
	 * Mtodo que lista Tramo Objetivo Cobranza
     * @param criteria
	 * @return
	 */	
	public List getTramoObjetivoCobranzaList(Map criteria);
	/**
	 * Mtodo que lista Ambito Geografico
     * @param criteria
	 * @return
	 */	
	public List getAmbitoGeograficoList(Map criteria);
	/**
	 * Mtodo que lista Programa Canasta Premi
     * @param criteria
	 * @return
	 */	
	public List getProgramaCanastaPremiList(Map criteria);
	/**
	 * Mtodo que lista Programa Desempenio
     * @param criteria
	 * @return
	 */	
	public List getProgramaDesempenioList(Map criteria) ;
	/**
	 * Mtodo que lista Programa Etapa Campana
     * @param criteria
	 * @return
	 */	
	public List getProgramaEtapaCampanaList(Map criteria) ;
	/**
	 * Mtodo que lista Bono Campana
     * @param criteria
	 * @return
	 */	
	public List getBonoCampanaList(Map criteria);
	/**
	 * Mtodo que lista Bono Lanzamiento
     * @param criteria
	 * @return
	 */	
	public List getBonoLanzamientoList(Map criteria);
	/**
	 * Mtodo que lista Bono Nivel
     * @param criteria
	 * @return
	 */	
	public List getBonoNivelList(Map criteria);

	/**
	 * Metodo para obtener los tipos de oferta disponibles
	 * @return
	 */
	public List getTipoOfertaList();
	public void insertCanasta(Canasta obj) ;
	/**
	 * Mtodo que lista canastas
     * @param criteria
	 * @return
	 */	
	public List getCanastaList(Map criteria);
	/**
	 * Mtodo inserta Canasta Detalle
     * @param Canasta Detalle
	 * @return
	 */	
	public void insertCanastaDetalle(CanastaDetalle obj) ;
	/**
	 * Mtodo que lista Canasta Detalle
     * @param criteria
	 * @return
	 */	
	public List getCanastaDetalleList(Map criteria);
	/**
	 * Devuelve el siguiente numero de la canasta
	 * @return
	 */
	public String getNumeroSecuenciaCanasta();
	
	public List getRegionByTipoGrupoRegion(Map criteria);
	
	public Integer verificarProgramaCruce(Map criteria);
	
	public void insertCampanaExigencia(CampanaExigencia obj);
	
	/**
	 * Elimina Registro de Campaa Exigencia
	 * @param obj
	 */
	public void deleteCampanaExigencia(CampanaExigencia obj);
	
	public List getCampanaExigenciaList(Map criteria);
	
	public void updateProgramaCorporativo(ProgramaCorporativo programaCorporativo);
	
	public void updateProgramaNivel(Nivel nivel);
	
	public void updateCobranzaTramo(CobranzaTramo cobranzaTramo);
	
	public void deleteCobranzaTramo(CobranzaTramo cobranzaTramo);
	
	public void deleteAmbitoGeografico(AmbitoGeografico ambitoGeografico);
	
	/**
	 * @param ambitoGeografico
	 */
	public void deleteAmbitoGeograficoFisico(AmbitoGeografico ambitoGeografico);
	
	public void deleteProgramaNivel(Nivel nivel) ;
	
	public List getBonoLanzamientoProductoList(Map criteria);
	
	public void insertNivelTramo(NivelTramo obj);
	
	public List getNivelTramoList(Map criteria);
	
	public void updateAmbitoGeografico(AmbitoGeografico obj);
	
	public void updateCampanaExigencia(CampanaExigencia obj);
	
	public void deleteObjetivoCobranza(ObjetivoCobranza obj);
	
	public void updateObjetivoCobranza(ObjetivoCobranza obj) ;
	
	public void deleteTramoObjetivoCobranza(TramoObjetivoCobranza obj);
	
	public void updateTramoObjetivoCobranza(TramoObjetivoCobranza obj) ;
	
	public void deleteBonoCampana(BonoCampana obj);
	
	public void updateBonoCampana(BonoCampana obj) ;
	
	public void deleteBonoLanzamiento(BonoLanzamiento obj) ;
	
	public void updateBonoLanzamiento(BonoLanzamiento obj);
	
	public void deleteBonoLanzamientoProducto(BonoLanzamientoProducto obj);
	
	public void updateBonoLanzamientoProducto(BonoLanzamientoProducto obj);
	
	public void deleteBonoNivel(BonoNivel obj) ;
	
	public void updateBonoNivel(BonoNivel obj) ;
	
	/**
	 * Obtiene datos de la Region de la zona Seleccionada
	 * @param criteria
	 * @return
	 */
	public Map getRegionByZona(Map criteria);
	
	/**
	 * Devuelve el Maximo Nro de Lanzamiento x Programa
	 * @param criteria
	 * @return
	 */
	public Integer getBonoLanzamientoMaxNroLanzamiento(Map criteria);
	
	/**
	 * Verifica si existe Registro en Bono Lanzamiento 
	 * @param criteria
	 * @return
	 */
	public Integer getExisteBonoLanzamiento(Map criteria);
	
	/**
	 * Obtienen la lista de tipos de tramos
	 * @return
	 */
	public List getTipoTramo(Map criteria);
	
	/**
	 * Mtodo que actualiza Nivel Tramo
	 * @param criteria
	 */
	public void updateNivelTramo(NivelTramo obj);
	
	/**
	 * Mtodo que Elimina Registro en Nivel Tramo
	 * @param criteria
	 */
	public void deleteNivelTramo(NivelTramo obj);
	
	/**
	 * Actualiza datos del Registro de Canasta
	 * @param obj
	 */
	public void updateCanasta(Canasta obj) ;
	
	/**
	 * Actualiza datos del Registro de Canasta detalle
	 * @param obj
	 */
	public void updateCanastaDetalle(CanastaDetalle obj) ;
	
	/**
	 * Elimina datos del Registro de Canasta detalle
	 * @param obj
	 */
	public void deleteCanastaDetalle(CanastaDetalle obj) ;
	
	/**
	 * Elimina datos del Registro de Canasta
	 * @param obj
	 */
	public void deleteCanasta(Canasta obj) ;
	
	/**
	 * Actualiza Nro de Productos en el Registro de Canasta
	 * @param obj
	 */
	public void updateCanastaNumProductos(Canasta obj) ;
	
	/**
	 * Devvuelve el Menor Peso de Nivel
	 * @param obj
	 */
	public Integer getMenorPesoNivel(Map criteria) ;
	
	/**
	 * Devvuelve el Peso de Nivel
	 * @param obj
	 */
	public Integer getPesoNivel(Map criteria) ;
	
	/**
	 * Elimina datos del Registro de Canasta Premios
	 * @param obj
	 */
	public void deleteProgramaCanastaPremi(ProgramaCanastaPremi obj) ;
	
	/**
	 * Obtiene todos los tipos de ranking
	 * @return
	 */
	public List getTipoRanking();

	/**
	 * Obtiene los niveles por tipo de ranking
	 * 
	 * @param params
	 * @return
	 */
	public List getNivelesRanking(Map params);

	/**
	 * Obtiene el numero de secuencia de ranking
	 * 
	 * @return
	 */
	public String getNumeroSecuenciaRanking();

	/**
	 * Inserta un objeto en la tabla ranking
	 * 
	 * @param ranking
	 * @param usuario
	 */
	public void insertRanking(Ranking ranking, Usuario usuario);

	/**
	 * Actualiza un objeto en la tabla ranking
	 * 
	 * @param ranking
	 * @param usuario
	 */
	public void updateRanking(Ranking ranking, Usuario usuario);

	/**
	 * Inserta un objeto en la tabla de niveles por ranking
	 * 
	 * @param rankingNivel
	 * @param usuario
	 */
	public void insertNivelRanking(RankingNivel rankingNivel, Usuario usuario);

	/**
	 * Actualiza un objeto en la tabla de niveles por ranking
	 * 
	 * @param rankingNivel
	 * @param usuario
	 */
	public void updateNivelRanking(RankingNivel rankingNivel, Usuario usuario);
	
	/**
	 * Obtiene el listado de ranking 
	 * @param criteria
	 * @return
	 */
	public List getRankingList(Map criteria);

	/**
	 * 
	 * elimina ranking y sus nivelkes a inactivo
	 * 
	 * @param ranking
	 * @param usuario
	 */
	public void deleteRanking(Ranking ranking, Usuario usuario);

	/**
	 * 
	 * elimina ranking y sus nivelkes a inactivo
	 * 
	 * @param rankingNivel
	 * @param usuario
	 */
	public void deleteNivelRanking(RankingNivel rankingNivel, Usuario usuario);
	
	/**
	 * @param rankingNivel
	 * @return
	 */
	public RankingNivel getNivelRanking(RankingNivel rankingNivel);
	
	/**
	 * Elimina datos del Registro de Programa Etapa Campaa
	 * @param obj
	 */
	public void deleteProgramaEtapaCampana(ProgramaEtapaCampana obj) ;
	
	/**
	 * Elimina datos del Registro de Programa Desempeo
	 * @param obj
	 */
	public void deleteProgramaDesempenio(ProgramaDesempenio obj) ;
	
	/**
	 * Actualiza datos del Registro de Programa Desempeo
	 * @param obj
	 */
	public void updateProgramaDesempenio(ProgramaDesempenio obj);
	
	/**
	 * Mtodo que lista los programas activos mayo igual a la campaa indicada
     * @param criteria
	 * @return
	 */
	public Integer getListaProgramasActivosCampanna(Map criteria);
	
	/**
	 * Mtodo que VERIFICA si es posible eliminar un Programa
     * @param criteria
	 * @return
	 */
	public Integer getVerificarEliminarPrograma(Map criteria);
	
	/**
	 * Mtodo que Elimina Programa
     * @param criteria
	 * @return
	 */
	public void executeEliminarPrograma(Map criteria);
	
	/**
	 * Mtodo que inserta Bono 
	 * @param criteria
	 */
	public void insertBonoLEC(BonoNivel obj);
	
	/**
	 * Mtodo que borra Bono 
	 * @param criteria
	 */
	public void deleteBonoLEC(BonoNivel obj);
	
	/**
	 * Mtodo que lista Bono 
     * @param criteria
	 * @return
	 */	
	public List getBonoLECList(Map criteria);

	/**
	 * Obtiene la lista de tipos de grupo de pago
	 * @param map
	 * @return
	 */
	public List getGruposPago(Map map);

	/**
	 * @param obj
	 */
	public void deleteBonoNivelRango(BonoNivel obj);
	
	/**
	 * Cambia el estado a inactivo del registro
	 * @param criteria
	 */
	public void deleteCobranzaObjetivoIndicador(Map criteria);
	
	/**
	 * Cambia el estado a inactivo del registro
	 * @param criteria
	 */
	public void deleteCobranzaObjetivoTramoIndicador(Map criteria);
	
	/**
	 * Cambia el estado a inactivo del registro
	 * @param criteria
	 */
	public void deleteAmbitoGeograficoIndicador(Map criteria);
	

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getTiposBono(Map params);

	/**
	 * 
	 * @return
	 */
	public List getTiposPremiacion();

	/**
	 * 
	 * @return
	 */
	public List getTiposCanasta();


	/**
	 * Mtodo que lista los tipos Estado de Pagos LEC
	 * @param criteria
	 * @return
	 */
	public List getLECEstadoPagosList(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getNivelRangoIncentivos(Map criteria) ;
	
	/**
	 * @param codigo
	 * @return
	 */
	public String obtieneNivelCodigoRango(String codigo) ;

	
}