package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoAmbitoGeografico;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLote;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLoteDescuento;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoBonificacionPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoClasificacionParticipante;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoDespachoPremios;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoEstatusVenta;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoLotePremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoMontoVentas;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoNivelPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoObtencionPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosConsultoras;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosGenerales;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPeriodoDespacho;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosBonificados;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExcluidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExigidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosValidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPuntajeExigido;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRecomendadaPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRequisitoPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoVersion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.NuevaConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.RangoConstanciaProgramaPuntos;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoINCConfiguracionConcursoDAO")
public class MantenimientoINCConfiguracionConcursoDAOIbatis extends BaseDAOiBatis implements
	MantenimientoINCConfiguracionConcursoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getClasificacionesConcurso()
	 */
	public List getClasificacionesConcurso() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getClasificacionesConcurso");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursosByCriteria(java.util.Map)
	 */
	public List getConcursosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getConcursosByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcurso(java.util.Map)
	 */
	public void deleteConcurso(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.deleteConcurso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoVersion(java.util.Map)
	 */
	public void deleteConcursoVersion(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.deleteConcursoVersion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTiposPrograma()
	 */
	public List getTiposPrograma() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getTiposPrograma");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getBaseCalculo()
	 */
	public List getBaseCalculo() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getBaseCalculo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getDirigidos()
	 */
	public List getDirigidos() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getDirigidos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTipoVenta()
	 */
	public List getTipoVenta() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getTipoVenta");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTipoExigencia()
	 */
	public List getTipoExigencia() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getTipoExigencia");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursosRecomendadas()
	 */
	public List getConcursosRecomendadas() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getConcursosRecomendadas");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getEstatusCliente()
	 */
	public List getEstatusCliente() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getEstatusCliente");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getClasificacionesParticipante()
	 */
	public List getClasificacionesParticipante() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getClasificacionesParticipante");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTiposPremiacion()
	 */
	public List getTiposPremiacion() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getTiposPremiacion");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTiposEleccion()
	 */
	public List getTiposEleccion() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getTiposEleccion");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getCentrosServicio()
	 */
	public List getCentrosServicio() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getCentrosServicio");
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTiposProducto()
	 */
	public List getTiposProducto() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getTiposProducto");
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTiposOferta()
	 */
	public List getTiposOferta() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getTiposOferta");
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getTiposAgrupacion()
	 */
	public List getTiposAgrupacion() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getTiposAgrupacion");
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getUnidadesNegocio()
	 */
	public List getUnidadesNegocio() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getUnidadesNegocio");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSuperGenericosByCriteria(java.util.Map)
	 */
	public List getSuperGenericosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getSuperGenericosByCriteria", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getGenericosByCriteria(java.util.Map)
	 */
	public List getGenericosByCriteria(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getGenericosByCriteria", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getCiclosVida()
	 */
	public List getCiclosVida() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getCiclosVida");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getNegocios()
	 */
	public List getNegocios() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getNegocios");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getMarcaProductos()
	 */
	public List getMarcaProductos() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getMarcaProductos");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSecuenciaConcursoParametrosGenerales()
	 */
	public Long getSecuenciaConcursoParametrosGenerales() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getSecuenciaConcursoParametrosGenerales");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSecuenciaConcursoParametrosPremiacion()
	 */
	public Long getSecuenciaConcursoParametrosPremiacion() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getSecuenciaConcursoParametrosPremiacion");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSecuenciaConcursoNivelPremiacion()
	 */
	public Long getSecuenciaConcursoNivelPremiacion() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getSecuenciaConcursoNivelPremiacion");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSecuenciaConcursoPremioArticulo()
	 */
	public Long getSecuenciaConcursoPremioArticulo() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getSecuenciaConcursoPremioArticulo");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSecuenciaConcursoLotePremioArticulo()
	 */
	public Long getSecuenciaConcursoLotePremioArticulo() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getSecuenciaConcursoLotePremioArticulo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSecuenciaConcursoProductos()
	 */
	public Long getSecuenciaConcursoProductos() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getSecuenciaConcursoProductos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoParametrosGenerales(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosGenerales, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoParametrosGenerales(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoParametrosGenerales", 
					concursoParametrosGenerales);
		
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoAcceso", 
				concursoParametrosGenerales);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoVersion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoVersion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoVersion(ConcursoVersion concursoVersion, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoVersion", concursoVersion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoObtencionPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoObtencionPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoObtencionPuntos(ConcursoObtencionPuntos concursoObtencionPuntos, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoObtencionPuntos", 
					concursoObtencionPuntos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoDespachoPremios(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoDespachoPremios, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoDespachoPremios(ConcursoDespachoPremios concursoDespachoPremios, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoDespachoPremios", 
				concursoDespachoPremios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoAmbitoGeografico(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoAmbitoGeografico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoAmbitoGeografico(ConcursoAmbitoGeografico concursoAmbitoGeografico, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoAmbitoGeografico", 
				concursoAmbitoGeografico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoParametrosConsultoras(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosConsultoras, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoParametrosConsultoras(ConcursoParametrosConsultoras concursoParametrosConsultoras, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoParametrosConsultoras", 
				concursoParametrosConsultoras);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoMontoVentas(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoMontoVentas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoMontoVentas(ConcursoMontoVentas concursoMontoVentas, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoMontoVentas", 
				concursoMontoVentas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoEstatusVenta(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoEstatusVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoEstatusVenta(ConcursoEstatusVenta concursoEstatusVenta, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoEstatusVenta", 
				concursoEstatusVenta);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoClasificacionParticipante(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoClasificacionParticipante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoClasificacionParticipante(ConcursoClasificacionParticipante concursoClasificacionParticipante, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoClasificacionParticipante", 
				concursoClasificacionParticipante);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoRequisitoPremiacion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoRequisitoPremiacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoRequisitoPremiacion(ConcursoRequisitoPremiacion concursoRequisitoPremiacion, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoRequisitoPremiacion", 
				concursoRequisitoPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoParametrosPremiacion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosPremiacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoParametrosPremiacion(ConcursoParametrosPremiacion concursoParametrosPremiacion, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoParametrosPremiacion", 
				concursoParametrosPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoNivelPremiacion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoNivelPremiacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoNivelPremiacion(ConcursoNivelPremiacion concursoNivelPremiacion, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoNivelPremiacion", 
				concursoNivelPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoPremioArticulo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoPremioArticulo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoPremioArticulo(ConcursoPremioArticulo concursoPremioArticulo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoPremioArticulo", 
				concursoPremioArticulo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoLotePremioArticulo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoLotePremioArticulo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoLotePremioArticulo(ConcursoLotePremioArticulo concursoLotePremioArticulo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoLotePremioArticulo", 
				concursoLotePremioArticulo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoProductos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoProductos(ConcursoProductos concursoProductos, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoProductos", 
				concursoProductos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoProductosValidos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosValidos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoProductosValidos(ConcursoProductosValidos concursoProductosValidos, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoProductosValidos", 
				concursoProductosValidos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoProductosBonificados(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosBonificados, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoProductosBonificados(ConcursoProductosBonificados concursoProductosBonificados, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoProductosBonificados", 
				concursoProductosBonificados);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoProductosExcluidos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosExcluidos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoProductosExcluidos(ConcursoProductosExcluidos concursoProductosExcluidos, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoProductosExcluidos", 
				concursoProductosExcluidos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoProductosExigidos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosExigidos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoProductosExigidos(ConcursoProductosExigidos concursoProductosExigidos, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoProductosExigidos", 
				concursoProductosExigidos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoArticuloLote(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoArticuloLote, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoArticuloLote(ConcursoArticuloLote concursoArticuloLote, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoArticuloLote", 
				concursoArticuloLote);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoParametrosGenerales(java.lang.String)
	 */
	public ConcursoParametrosGenerales getConcursoParametrosGenerales(String oidConcurso) {
		return (ConcursoParametrosGenerales) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoParametrosGenerales", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoVersion(java.lang.String)
	 */
	public ConcursoVersion getConcursoVersion(String oidConcurso) {
		return (ConcursoVersion) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoVersion", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoObtencionPuntos(java.lang.String)
	 */
	public ConcursoObtencionPuntos getConcursoObtencionPuntos(String oidConcurso) {
		return (ConcursoObtencionPuntos) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoObtencionPuntos", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoDespachoPremios(java.lang.String)
	 */
	public ConcursoDespachoPremios getConcursoDespachoPremios(String oidConcurso) {
		return (ConcursoDespachoPremios) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoDespachoPremios", oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoAmbitoGeografico(java.lang.String)
	 */
	public List getListConcursoAmbitoGeografico(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoAmbitoGeografico", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoParametrosConsultoras(java.lang.String)
	 */
	public ConcursoParametrosConsultoras getConcursoParametrosConsultoras(String oidConcurso) {
		return (ConcursoParametrosConsultoras) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoParametrosConsultoras", oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoMontoVentas(java.lang.String)
	 */
	public List getListConcursoMontoVentas(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoMontoVentas", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoEstatusVenta(java.lang.String)
	 */
	public List getListConcursoEstatusVenta(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoEstatusVenta", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoClasificacionParticipante(java.lang.String)
	 */
	public List getListConcursoClasificacionParticipante(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoClasificacionParticipante", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoRequisitoPremiacion(java.lang.String)
	 */
	public ConcursoRequisitoPremiacion getConcursoRequisitoPremiacion(String oidConcurso) {
		return (ConcursoRequisitoPremiacion) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoRequisitoPremiacion", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoParametrosPremiacion(java.lang.String)
	 */
	public ConcursoParametrosPremiacion getConcursoParametrosPremiacion(String oidConcurso) {
		return (ConcursoParametrosPremiacion) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoParametrosPremiacion", oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoNivelPremiacion(java.lang.String)
	 */
	public List getListConcursoNivelPremiacion(String oidPremiacion) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoNivelPremiacion", oidPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoPremioArticulo(java.lang.String)
	 */
	public ConcursoPremioArticulo getConcursoPremioArticulo(String oidNivelPremiacion) {
		return (ConcursoPremioArticulo) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoPremioArticulo", oidNivelPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoLotePremioArticulo(java.lang.String)
	 */
	public List getListConcursoLotePremioArticulo(String oidLotePremioArticulo) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoLotePremioArticulo", oidLotePremioArticulo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoArticuloLote(java.lang.String)
	 */
	public List getListConcursoArticuloLote(String oidPremioArticulo) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoArticuloLote", oidPremioArticulo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConcursoProductos(java.lang.String)
	 */
	public ConcursoProductos getConcursoProductos(String oidConcurso) {
		return (ConcursoProductos) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getConcursoProductos", oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoProductosValidos(java.lang.String)
	 */
	public List getListConcursoProductosValidos(String oidProductos) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoProductosValidos", oidProductos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoProductosBonificados(java.lang.String)
	 */
	public List getListConcursoProductosBonificados(String oidProductos) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoProductosBonificados", oidProductos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoProductosExcluidos(java.lang.String)
	 */
	public List getListConcursoProductosExcluidos(String oidProductos) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoProductosExcluidos", oidProductos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoProductosExigidos(java.lang.String)
	 */
	public List getListConcursoProductosExigidos(String oidProductos) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoProductosExigidos", oidProductos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoParametrosGenerales(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosGenerales, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoParametrosGenerales(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoParametrosGenerales", 
					concursoParametrosGenerales);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoVersion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoVersion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoVersion(ConcursoVersion concursoVersion, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoVersion", concursoVersion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoObtencionPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoObtencionPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoObtencionPuntos(ConcursoObtencionPuntos concursoObtencionPuntos, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoObtencionPuntos", 
				concursoObtencionPuntos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoDespachoPremios(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoDespachoPremios, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoDespachoPremios(ConcursoDespachoPremios concursoDespachoPremios, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoDespachoPremios", 
				concursoDespachoPremios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoAmbitoGeografico(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoAmbitoGeografico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoAmbitoGeografico(ConcursoAmbitoGeografico concursoAmbitoGeografico, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoAmbitoGeografico", 
				concursoAmbitoGeografico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoParametrosConsultoras(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosConsultoras, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoParametrosConsultoras(ConcursoParametrosConsultoras concursoParametrosConsultoras, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoParametrosConsultoras", 
				concursoParametrosConsultoras);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoMontoVentas(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoMontoVentas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoMontoVentas(ConcursoMontoVentas concursoMontoVentas, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoMontoVentas", 
				concursoMontoVentas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoEstatusVenta(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoEstatusVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoEstatusVenta(ConcursoEstatusVenta concursoEstatusVenta, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoEstatusVenta", 
				concursoEstatusVenta);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoClasificacionParticipante(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoClasificacionParticipante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoClasificacionParticipante(ConcursoClasificacionParticipante concursoClasificacionParticipante, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoClasificacionParticipante", 
				concursoClasificacionParticipante);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoRequisitoPremiacion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoRequisitoPremiacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoRequisitoPremiacion(ConcursoRequisitoPremiacion concursoRequisitoPremiacion, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoRequisitoPremiacion", 
				concursoRequisitoPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoParametrosPremiacion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosPremiacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoParametrosPremiacion(ConcursoParametrosPremiacion concursoParametrosPremiacion, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoParametrosPremiacion", 
				concursoParametrosPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoNivelPremiacion(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoNivelPremiacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoNivelPremiacion(ConcursoNivelPremiacion concursoNivelPremiacion, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoNivelPremiacion", 
				concursoNivelPremiacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoPremioArticulo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoPremioArticulo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoPremioArticulo(ConcursoPremioArticulo concursoPremioArticulo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoPremioArticulo", 
				concursoPremioArticulo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoLotePremioArticulo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoLotePremioArticulo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoLotePremioArticulo(ConcursoLotePremioArticulo concursoLotePremioArticulo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoLotePremioArticulo", 
				concursoLotePremioArticulo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoArticuloLote(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoArticuloLote, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoArticuloLote(ConcursoArticuloLote concursoArticuloLote, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoArticuloLote", 
				concursoArticuloLote);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoProductos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoProductos(ConcursoProductos concursoProductos, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoProductos", 
				concursoProductos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoProductosValidos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosValidos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoProductosValidos(ConcursoProductosValidos concursoProductosValidos, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoProductosValidos", 
				concursoProductosValidos);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoProductosBonificados(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosBonificados, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoProductosBonificados(ConcursoProductosBonificados concursoProductosBonificados, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoProductosBonificados", 
				concursoProductosBonificados);
	}		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoProductosExcluidos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosExcluidos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoProductosExcluidos(ConcursoProductosExcluidos concursoProductosExcluidos, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoProductosExcluidos", 
				concursoProductosExcluidos);
	}		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoProductosExigidos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoProductosExigidos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoProductosExigidos(ConcursoProductosExigidos concursoProductosExigidos, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoProductosExigidos", 
				concursoProductosExigidos);
	}		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoAmbitoGeografico(java.lang.String)
	 */
	public void deleteConcursoAmbitoGeografico(String oidConcurso) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoAmbitoGeografico", 
				oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoEstatusVenta(java.lang.String)
	 */
	public void deleteConcursoEstatusVenta(String oidConcurso) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoEstatusVenta", 
				oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoClasificacionParticipante(java.lang.String)
	 */
	public void deleteConcursoClasificacionParticipante(String oidConcurso) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoClasificacionParticipante", 
				oidConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoProductosValidos(java.lang.String)
	 */
	public void deleteConcursoProductosValidos(String oidProductos) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoProductosValidos", 
				oidProductos);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoProductosBonificados(java.lang.String)
	 */
	public void deleteConcursoProductosBonificados(String oidProductos) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoProductosBonificados", 
				oidProductos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoProductosExcluidos(java.lang.String)
	 */
	public void deleteConcursoProductosExcluidos(String oidProductos) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoProductosExcluidos", 
				oidProductos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoProductosExigidos(java.lang.String)
	 */
	public void deleteConcursoProductosExigidos(String oidProductos) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoProductosExigidos", 
				oidProductos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getRegionesByOidSubGerencia(java.util.Map)
	 */
	public List getRegionesByOidSubGerencia(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getRegionesByOidSubGerencia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getZonasByMultipleOidRegiones(java.util.Map)
	 */
	public List getZonasByMultipleOidRegiones(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getZonasByMultipleOidRegiones", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getUltimoNumeroConcurso()
	 */
	public String getUltimoNumeroConcurso() {
		return (String) getSqlMapClientTemplate().
					queryForObject("spusicc.incentivos.MantenimientoINCSQL.getUltimoNumeroConcurso");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#executeReplicarConfiguracionConcurso(java.util.Map)
	 */
	public void executeReplicarConfiguracionConcurso(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeReplicarConfiguracionConcurso",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#executeEliminarNivelesPremiacion(java.util.Map)
	 */
	public void executeEliminarNivelesPremiacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeEliminarNivelesPremiacion",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getUltimoContadorArticulo()
	 */
	public String getUltimoContadorArticulo() {
		Map params = new HashMap();
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.executeObtenerUltimoContadorArticulo",params);
		
		return (String)params.get("ultimoContador");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getDatosTipoConcurso(java.lang.String)
	 */
	public Map getDatosTipoConcurso(String oidTipoConcurso) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getDatosTipoConcurso", oidTipoConcurso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#validaDespachoPremios(java.lang.String)
	 */
	public boolean validaDespachoPremios(String oidConcurso) {
    	String result = (String) getSqlMapClientTemplate().
    						queryForObject("spusicc.incentivos.MantenimientoINCSQL.getExisteDespachoPremios", oidConcurso);

    	if(Integer.parseInt(result)>0)
    		return true;
    	else
    		return false;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#validaPuntajeConcurso(java.lang.String)
	 */
	public boolean validaPuntajeConcurso(String oidConcurso) {
    	String result = (String) getSqlMapClientTemplate().
    						queryForObject("spusicc.incentivos.MantenimientoINCSQL.getExistePuntajeConcurso", oidConcurso);

    	if(Integer.parseInt(result)>0)
    		return true;
    	else
    		return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getCodigoSAPRecomendacion()
	 */
	public String getCodigoSAPRecomendacion() {
		return (String) getSqlMapClientTemplate().
					queryForObject("spusicc.incentivos.MantenimientoINCSQL.getCodigoSAPRecomendacion");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getEstados()
	 */
	public List getEstados() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getEstados");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getVigencias()
	 */
	public List getVigencias() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getVigencias");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getIndicadoresTipoRecomendada()
	 */
	public List getIndicadoresTipoRecomendada() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getIndicadoresTipoRecomendada");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoRecomendadaPeriodo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoRecomendadaPeriodo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoRecomendadaPeriodo(ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoRecomendadaPeriodo", 
				concursoRecomendadaPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoRecomendadaPeriodo(java.lang.String)
	 */
	public List getListConcursoRecomendadaPeriodo(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoRecomendadaPeriodo", oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoRecomendadaPeriodo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoRecomendadaPeriodo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoRecomendadaPeriodo(ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoRecomendadaPeriodo", 
				concursoRecomendadaPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoRecomendadaPeriodo(java.lang.String)
	 */
	public void deleteConcursoRecomendadaPeriodo(String oidConcurso) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoRecomendadaPeriodo", 
				oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoBonificacionPeriodo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoBonificacionPeriodo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoBonificacionPeriodo(ConcursoBonificacionPeriodo concursoBonificacionPeriodo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoBonificacionPeriodo", 
				concursoBonificacionPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoBonificacionPeriodo(java.lang.String)
	 */
	public List getListConcursoBonificacionPeriodo(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoBonificacionPeriodo", oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoBonificacionPeriodo(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoBonificacionPeriodo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoBonificacionPeriodo(ConcursoBonificacionPeriodo concursoBonificacionPeriodo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoBonificacionPeriodo", 
				concursoBonificacionPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoBonificacionPeriodo(java.lang.String)
	 */
	public void deleteConcursoBonificacionPeriodo(String oidConcurso) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoBonificacionPeriodo", 
				oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoPeriodoDespacho(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoPeriodoDespacho, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoPeriodoDespacho(ConcursoPeriodoDespacho concursoPeriodoDespacho, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoPeriodoDespacho", 
				concursoPeriodoDespacho);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoPeriodoDespacho(java.lang.String)
	 */
	public List getListConcursoPeriodoDespacho(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoPeriodoDespacho", oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConcursoPeriodoDespacho(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoPeriodoDespacho, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConcursoPeriodoDespacho(ConcursoPeriodoDespacho concursoPeriodoDespacho, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoPeriodoDespacho", 
				concursoPeriodoDespacho);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoPeriodoDespacho(java.lang.String)
	 */
	public void deleteConcursoPeriodoDespacho(String oidConcurso) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoPeriodoDespacho", 
				oidConcurso);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConcursoPuntajeExigido(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoPuntajeExigido, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoPuntajeExigido(ConcursoPuntajeExigido concursoPuntajeExigido, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoPuntajeExigido", 
				concursoPuntajeExigido);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteConcursoPuntajeExigido(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoPuntajeExigido, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteConcursoPuntajeExigido(ConcursoPuntajeExigido concursoPuntajeExigido, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteConcursoPuntajeExigido", 
				concursoPuntajeExigido);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListConcursoPuntajeExigido(java.lang.String)
	 */
	public List getListConcursoPuntajeExigido(String oidConcurso) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoPuntajeExigido", oidConcurso);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteUltimaActualizacionConcurso(java.util.Map)
	 */
	public void deleteUltimaActualizacionConcurso(Map params) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteUltimaActualizacionConcurso", 
				params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertUltimaActualizacionConcurso(java.util.Map)
	 */
	public void insertUltimaActualizacionConcurso(Map params) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertUltimaActualizacionConcurso", 
				params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteLotesPremioArticulo(java.util.Map)
	 */
	public void deleteLotesPremioArticulo(Map params) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteLotesPremioArticulo", 
				params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteArticulosLoteEliminado(java.util.Map)
	 */
	public void deleteArticulosLoteEliminado(Map params) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteArticulosLoteEliminado", 
				params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteArticulosLote(java.util.Map)
	 */
	public void deleteArticulosLote(Map params) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteArticulosLote", 
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getDetalleOfertaCUV(java.util.Map)
	 */
	public Map getDetalleOfertaCUV(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getDetalleOfertaCUV", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getFaltantesPendientesConcurso(java.util.Map)
	 */
	public List getFaltantesPendientesConcurso(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getFaltantesPendientesConcurso", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getPremiosConcurso(java.util.Map)
	 */
	public List getPremiosConcurso(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getPremiosConcurso", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getFaltantesConcurso(java.util.Map)
	 */
	public List getFaltantesConcurso(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getFaltantesConcurso", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getSecuenciaReemplazoPremio()
	 */
	public Long getSecuenciaReemplazoPremio() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getSecuenciaReemplazoPremio");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertReemplazoPremio(java.util.Map)
	 */
	public void insertReemplazoPremio(Map params) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertReemplazoPremio", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateReemplazoPremio(java.util.Map)
	 */
	public void updateReemplazoPremio(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateReemplazoPremio", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteReemplazoPremio(java.lang.String)
	 */
	public void deleteReemplazoPremio(String oidArticuloLote) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteReemplazoPremio", oidArticuloLote);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertReemplazoPremioAmbito(java.util.Map)
	 */
	public void insertReemplazoPremioAmbito(Map params) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertReemplazoPremioAmbito", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteReemplazoPremioAmbito(java.lang.String)
	 */
	public void deleteReemplazoPremioAmbito(String oidArticuloLote) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteReemplazoPremioAmbito", oidArticuloLote);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListCriterioDeReemplazo(java.util.Map)
	 */
	public List getListCriterioDeReemplazo(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListCriterioDeReemplazo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#validaEjecucionProcesoBatch(java.util.Map)
	 */
	public boolean validaEjecucionProcesoBatch(Map criteria) {
		String result = (String) getSqlMapClientTemplate().
				queryForObject("spusicc.incentivos.MantenimientoINCSQL.getValidaEjecucionProcesoBatch", criteria);

		if(Integer.parseInt(result)>0)
			return true;
		else
			return false;		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getListReemplazoPremioAmbito(java.lang.String)
	 */
	public List getListReemplazoPremioAmbito(String oidReemplazo) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListReemplazoPremioAmbito", oidReemplazo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#validaPremioAtendido(java.util.Map)
	 */
	public boolean validaPremioAtendido(Map criteria) {
		String result = (String) getSqlMapClientTemplate().
				queryForObject("spusicc.incentivos.MantenimientoINCSQL.getValidaPremioAtendido", criteria);

		if(Integer.parseInt(result)>0)
			return true;
		else
			return false;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getListConcursosVigentesPuntos() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursosVigentesPuntos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteReemplazoPremioCompuesto(java.lang.String)
	 */
	public void deleteReemplazoPremioCompuesto(String oidArticuloLote) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteReemplazoPremioCompuesto", oidArticuloLote);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateReemplazoPremioCompuesto(java.util.Map)
	 */
	public void updateReemplazoPremioCompuesto(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateReemplazoPremioCompuesto", params);		
	}
	
	public String getDescripcionProgramaAdicionar(String oidTipoConcurso) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.incentivos.MantenimientoINCSQL.getDescripcionProgramaAdicionar", oidTipoConcurso);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getDescripcionProgramaEditar(java.util.Map)
	 */
	public String getDescripcionProgramaEditar(String oidConcurso) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.incentivos.MantenimientoINCSQL.getDescripcionProgramaEditar", oidConcurso);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getProgramasConstanciaProgramaPuntosByCriteria(java.util.Map)
	 */
	public List getProgramasConstanciaProgramaPuntosByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getProgramasConstanciaProgramaPuntosByCriteria", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConstanciaProgramaPuntos)
	 */
	public ConstanciaProgramaPuntos getConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp) {
        return (ConstanciaProgramaPuntos) getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getConstanciaProgramaPuntos", cpp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConstanciaProgramaPuntos", cpp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#updateConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConstanciaProgramaPuntos", cpp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertRangoConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.RangoConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertRangoConstanciaProgramaPuntos", rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getRangoConstanciaProgramaPuntosList(java.util.Map)
	 */
	public List getRangoConstanciaProgramaPuntosList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getRangoConstanciaProgramaPuntosList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteRangoConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.RangoConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteRangoConstanciaProgramaPuntos", rango);
	}
	
	public Integer getExistePremiosDescuento(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getExistePremiosDescuento", criteria);
	}
	
	public Integer getExistePremiosArticulo(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getExistePremiosArticulo", criteria);
	}

	
	public List getListConcursoArticuloLoteDescuento(String oidPremioArticulo) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursoArticuloLoteDescuento", oidPremioArticulo);
	}

	@Override
	public String getcodigoProductoDescuento() {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().
				queryForObject("spusicc.incentivos.MantenimientoINCSQL.getcodigoProductoDescuento");
	}

	@Override
	public void deleteArticulosLoteDescuento(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteArticulosLoteDescuento",params);
	}

	@Override
	public void insertConcursoArticuloLoteDescuento(ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoArticuloLoteDescuento", 
				concursoArticuloLoteDescuento);		
	}

	@Override
	public void updateConcursoArticuloLoteDescuento(
			ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateConcursoArticuloLoteDescuento", 
				concursoArticuloLoteDescuento);		
	}

	@Override
	public Integer getNivelSelectivo(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getNivelSelectivo", criteria);

	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getClasificacionParaINCByCriteria(java.util.Map)
	 */
	public List getClasificacionParaINCByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getClasificacionParaINCByCriteria", params);

	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getClasificacionParaINC(java.util.Map)
	 */
	public ClasificacionParaINC getClasificacionParaINC(Map params) {
		return (ClasificacionParaINC)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getClasificacionParaINC", params);

	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#insertClasificacionParaINC(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario) {
		Map params = new HashMap();
		Integer secuencial = this.getNextOidClasificacionParaINC(params);
		bean.setSecuencial(secuencial);
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertClasificacionParaINC", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#updateClasificacionParaINC(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateClasificacionParaINC", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#deleteClasificacionParaINC(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteClasificacionParaINC", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#insertClasificacionParaINCDetalle(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario) {
		Map params = new HashMap();
		Integer secuencialDetalle = this.getNextOidClasificacionParaINCDetalle(params);
		bean.setSecuencialDetalle(secuencialDetalle);
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertClasificacionParaINCDetalle", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#updateClasificacionParaINCDetalle(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateClasificacionParaINCDetalle", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#deleteClasificacionParaINCDetalle(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteClasificacionParaINCDetalle", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getNextOidClasificacionParaINC(java.util.Map)
	 */
	public Integer getNextOidClasificacionParaINC(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getNextOidClasificacionParaINC", params);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getNextOidClasificacionParaINCDetalle(java.util.Map)
	 */
	public Integer getNextOidClasificacionParaINCDetalle(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getNextOidClasificacionParaINCDetalle", params);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getClasificacionParaINCDetalleByCriteria(java.util.Map)
	 */
	public List getClasificacionParaINCDetalleByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getClasificacionParaINCDetalleByCriteria", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getClasificacionParaINCDetalle(java.util.Map)
	 */
	public ClasificacionParaINCDetalle getClasificacionParaINCDetalle(Map params) {
		return (ClasificacionParaINCDetalle)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getClasificacionParaINCDetalle", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getTipoClienteList(java.util.Map)
	 */
	public List getTipoClienteList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getTipoClienteList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getSubTipoClienteList(java.util.Map)
	 */
	public List getSubTipoClienteList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getSubTipoClienteList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getTipoClasificacionList(java.util.Map)
	 */
	public List getTipoClasificacionList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getTipoClasificacionList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getClasificacionList(java.util.Map)
	 */
	public List getClasificacionList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getClasificacionList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getExisteRegistroClasificacionPartiConcu(java.util.Map)
	 */
	public Integer getExisteRegistroClasificacionPartiConcu(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getExisteRegistroClasificacionPartiConcu", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO#getExisteRegistroClasificacionDetalle(java.util.Map)
	 */
	public Integer getExisteRegistroClasificacionDetalle(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getExisteRegistroClasificacionDetalle", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#insertNuevaConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.NuevaConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos rango, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertNuevaConstanciaProgramaPuntos", rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#getNuevaConstanciaProgramaPuntosList(java.util.Map)
	 */
	public List getNuevaConstanciaProgramaPuntosList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getNuevaConstanciaProgramaPuntosList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCConfiguracionConcursoDAO#deleteNuevaConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.NuevaConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos rango, Usuario usuario) {
		getSqlMapClientTemplate().delete("spusicc.incentivos.MantenimientoINCSQL.deleteNuevaConstanciaProgramaPuntos", rango);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getListConcursosTipoProgramaPuntos() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.incentivos.MantenimientoINCSQL.getListConcursosTipoProgramaPuntos");
	}
	
	public Map getCampaniasIniFinByConcursoTipoProgramaPuntos(String numeroConcurso) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getCampaniasIniFinByConcursoTipoProgramaPuntos", numeroConcurso);		
	}
	
}
