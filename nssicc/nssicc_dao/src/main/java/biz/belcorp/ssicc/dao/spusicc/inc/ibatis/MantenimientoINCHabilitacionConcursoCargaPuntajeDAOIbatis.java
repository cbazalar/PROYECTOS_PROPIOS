package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.ProcesarINCConfiguracionConcursoCuponElectronico;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.CargaPuntaje;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeDAO")
public class MantenimientoINCHabilitacionConcursoCargaPuntajeDAOIbatis extends BaseDAOiBatis implements MantenimientoINCHabilitacionConcursoCargaPuntajeDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getListParametrosConcursosActivos()
	 */
	public List getListParametrosConcursosActivos() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListParametrosConcursosActivos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#insertConcursoHabilitado(java.util.Map)
	 */
	public void insertConcursoHabilitado(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertConcursoHabilitado",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getListConcursoHabilitado()
	 */
	public List getListConcursoHabilitado() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursoHabilitado");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#insertCargaPuntaje(java.util.List, java.lang.String)
	 */
	public void insertCargaPuntaje(final List listConcursoSeleccionados, String login) {
		
			Iterator it = listConcursoSeleccionados.iterator();					
			while (it.hasNext()) {
				String oidConcurso = (String)it.next();
				CargaPuntaje cargaPuntaje = new CargaPuntaje();
				cargaPuntaje.setOid(oidConcurso);
				cargaPuntaje.setCodigoUsuario(login);
				insertCargaPuntaje(cargaPuntaje);										
			}					
			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#deleteCargaPuntaje(java.lang.String)
	 */
	public void deleteCargaPuntaje(String oidConcurso) {
		getSqlMapClientTemplate()
		.delete(
				"spusicc.incentivos.MantenimientoINCSQL.deleteCargaPuntaje",
				oidConcurso);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#insertCargaPuntaje(biz.belcorp.ssicc.spusicc.inc.dao.model.CargaPuntaje)
	 */
	public void insertCargaPuntaje(CargaPuntaje cargaPuntaje) {
		getSqlMapClientTemplate()
		.insert(
				"spusicc.incentivos.MantenimientoINCSQL.insertCargaPuntaje",
				cargaPuntaje);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getListMotivoConcursos()
	 */
	public List getListMotivoConcursos() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListMotivoConcursos");
	}

	public void deleteConcursoHabilitado() {
		getSqlMapClientTemplate()
		.delete(
				"spusicc.incentivos.MantenimientoINCSQL.deleteConcursoHabilitado");
		
	}

	public List getListConcursosActivosObs(String valObservacion) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursosActivosObs",
						valObservacion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getListConcursosVigentesCerrados()
	 */
	public List getListConcursosVigentesCerrados() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursosVigentesCerrados");
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getListConcursosVigentesCerradosGanadoras()
	 */
	public List getListConcursosVigentesCerradosGanadoras() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursosVigentesCerradosGanadoras");
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getListConcursoCyzo()
	 */
	public List getListConcursoCyzo() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursoCyzo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getListConcursoReconocido()
	 */
	public List getListConcursoReconocido() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursoReconocido");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getIndicadorProdExig(java.lang.Integer)
	 */
	public int getIndicadorProdExig(Integer oidConcurso) {
		
		int result = 0;
		
		Integer resultado = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getIndicadorProdExig", oidConcurso);
		
		if(resultado == null)
			result = 0;
		else
			result = resultado.intValue();
			
		return result;
	}

	@Override
	public List getListaConcursosCEActivosByConcursoPeriodo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListaConcursosCEActivosByConcursoPeriodo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getDatosConcursoCuponElectronico(java.util.Map)
	 */
	public Map getDatosConcursoCuponElectronico(Map params) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getDatosConcursoCuponElectronico", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#getValidarExisteConcursoCuponElectronico(java.util.Map)
	 */
	public String getValidarExisteConcursoCuponElectronico(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.MantenimientoINCSQL.getValidarExisteConcursoCuponElectronico",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#insertMantenimientoINCConfiguracionConcursoCE(biz.belcorp.ssicc.model.ProcesarINCConfiguracionConcursoCuponElectronico)
	 */
	public void insertMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico) {
		getSqlMapClientTemplate().insert("spusicc.incentivos.MantenimientoINCSQL.insertMantenimientoINCConfiguracionConcursoCE", procesarINCConfiguracionConcursoCuponElectronico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO#updateMantenimientoINCConfiguracionConcursoCE(biz.belcorp.ssicc.model.ProcesarINCConfiguracionConcursoCuponElectronico)
	 */
	public void updateMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico) {
		getSqlMapClientTemplate().update("spusicc.incentivos.MantenimientoINCSQL.updateMantenimientoINCConfiguracionConcursoCE", procesarINCConfiguracionConcursoCuponElectronico);
	}
		
	}
