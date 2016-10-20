package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.model.ConexionExterna;
import biz.belcorp.ssicc.dao.edu.model.ControlFacturacion;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictado;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictadoZona;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenericoDefinicion;
import biz.belcorp.ssicc.dao.edu.model.EquivalenciaMatrizServicio;
import biz.belcorp.ssicc.dao.edu.model.EstadoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.Local;
import biz.belcorp.ssicc.dao.edu.model.MaestroCliente;
import biz.belcorp.ssicc.dao.edu.model.MaestroInstructora;
import biz.belcorp.ssicc.dao.edu.model.MensajeEducacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroClasificacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroGenerico;
import biz.belcorp.ssicc.dao.edu.model.ParametroRegistroClasificacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroReporte;
import biz.belcorp.ssicc.dao.edu.model.Sala;
import biz.belcorp.ssicc.dao.edu.model.ServicioCapacitacion;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * @author peextrvela
 *
 */
@Repository("edu.mantenimientoEDUGenericoDAO")
public class MantenimientoEDUGenericoDAOiBatis extends
		BaseDAOiBatis implements MantenimientoEDUGenericoDAO{

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getMaxMaestroInstructora(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora)
	 */
	public String getMaxMaestroInstructora(MaestroInstructora maestroInstructora) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getMaxMaestroInstructora",
				maestroInstructora);
		if (codigo==null) codigo="";
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getMaxParametroClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion)
	 */
	public String getMaxParametroClasificacion(ParametroClasificacion parametroClasificacion) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getMaxParametroClasificacion",
				parametroClasificacion);
		if (codigo==null) codigo="";
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public ParametroCursoCapacitacion getParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion) {
		return (ParametroCursoCapacitacion) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getParametroCurso",
				parametroCursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateParametroCurso",
				parametroCursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getAmbitoDictados(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getAmbitoDictados(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getAmbitoDictados",
				parametroGenerico);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getFrecuenciaCalificaciones(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getFrecuenciaCalificaciones(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getFrecuenciaCalificaciones",
				parametroGenerico);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getNivelVentas(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getNivelVentas(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getNivelVentas",
				parametroGenerico);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getSecuenciaPedidos(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getSecuenciaPedidos(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getSecuenciaPedidos",
				parametroGenerico);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getTipoDespachos(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getTipoDespachos(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getTipoDespachos",
				parametroGenerico);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getTipoCostoCurso(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getTipoCostoCurso(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getTipoCostoCurso",
				parametroGenerico);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getTipoIndicadorDespachos(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getTipoIndicadorDespachos(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getTipoIndicadorDespachos",
				parametroGenerico);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getEmpresasComercializadorasByPais(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora)
	 */
	public List getEmpresasComercializadorasByPais(EmpresaComercializadora empresaComercializadora) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getEmpresasComercializadorasByPais",
				empresaComercializadora);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getMaestroClientes(biz.belcorp.ssicc.edu.dao.model.MaestroCliente)
	 */
	public List getMaestroClientes(MaestroCliente maestroClientes) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getMaestroClientes",
				maestroClientes);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getMaestroInstructoraByCriteria(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora)
	 */
	public List getMaestroInstructoraByCriteria(MaestroInstructora maestroInstructora) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getMaestroInstructoraByCriteria",
				maestroInstructora);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#getParametroClasificacionByCriteria(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion)
	 */
	public List getParametroClasificacionByCriteria(ParametroClasificacion parametroClasificacion) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getParametroClasificacionByCriteria",
				parametroClasificacion);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertMaestroInstructora(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertMaestroInstructora",
				maestroInstructora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#insertParametroClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertParametroClasificacion",
				parametroClasificacion);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertServicioCapacitacion(biz.belcorp.ssicc.edu.dao.model.ServicioCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertServicioCapacitacion", bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#removeServicioCapacitacion(biz.belcorp.ssicc.edu.dao.model.ServicioCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUGenericoSQL.removeServicioCapacitacion", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeMaestroInstructora(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRemoveMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateRemoveMaestroInstructora",
				maestroInstructora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#removeParametroClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRemoveParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.removeParametroClasificacion",
				parametroClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateMaestroInstructora(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateMaestroInstructora",
				maestroInstructora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUCursoCapacitacionDAO#updateParametroClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateParametroClasificacion",
				parametroClasificacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertParametroEquivalenciaClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroEquivalenciaClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertParametroEquivalenciaClasificacion",
				parametroClasificacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateParametroEquivalenciaClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroEquivalenciaClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateParametroEquivalenciaClasificacion",
				parametroClasificacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getExisteParametroEquivalenciaClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion)
	 */
	public Integer getExisteParametroEquivalenciaClasificacion(ParametroClasificacion parametroClasificacion) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getExisteParametroEquivalenciaClasificacion",
				parametroClasificacion);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getTipoAsistencia(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getTipoAsistencia(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getTipoAsistencia",
				parametroGenerico);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getEstadoNivel(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getEstadoNivel(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getEstadoNivel",
				parametroGenerico);
		return resultado;
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getControlFacturacionById(java.util.Map)
	 */
	public ControlFacturacion getControlFacturacionById(Map criteria) {
		// TODO Auto-generated method stub
        return (ControlFacturacion)getSqlMapClientTemplate().queryForObject("edu.MantenimientoEDUGenericoSQL.getControlFacturacionByCriteria",
				criteria);
	}
	
	public List getControlFacturacionByCriteria(Map criteria) {
		// TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("edu.MantenimientoEDUGenericoSQL.getControlFacturacionByCriteria",
				criteria);
	}

	public void updateCerrarCampanaControlFacturacion(ControlFacturacion controlFacturacion, Usuario usuario) {
		// TODO Auto-generated method stub
        getSqlMapClientTemplate().update(
                "edu.MantenimientoEDUGenericoSQL.updateCerrarCampanaControlFacturacion", controlFacturacion);
	}
	
	public void updateDesactivarEquivalenciaMatriz(EquivalenciaMatrizServicio equivalenciaMatrizServicio, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate()
				.update("edu.MantenimientoEDUGenericoSQL.updateDesactivarEquivalenciaMatriz",
						equivalenciaMatrizServicio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getMaxParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion)
	 */
	public String getMaxParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getMaxParametroCurso",
				parametroCursoCapacitacion);
		if (codigo==null) codigo="";
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertParametroCurso",
				parametroCursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateEstadoParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoParametroCurso(ParametroCursoCapacitacion parametroCurso, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateEstadoParametroCurso", parametroCurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getParametroCursoByCriteria(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion)
	 */
	public List getParametroCursoByCriteria(ParametroCursoCapacitacion parametroCursoCapacitacion) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getParametroCursoByCriteria",
				parametroCursoCapacitacion);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getNroLoteActualInterfazDatamart(java.util.Map)
	 */
	public String getNroLoteActualInterfazDatamart(Map criteria) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getNroLoteActualInterfazDatamart",	criteria);
		return codigo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getNroLoteSiguienteInterfazDatamart(java.util.Map)
	 */
	public String getNroLoteSiguienteInterfazDatamart(Map criteria) {
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getNroLoteSiguienteInterfazDatamart",	criteria);
		return codigo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateNroLoteInterfazDatamart(java.util.Map)
	 */
	public void updateNroLoteInterfazDatamart(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.executeActualizarInterfazDatamart", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#deleteTemporalInterfazDatamart(java.util.Map)
	 */
	public void deleteTemporalInterfazDatamart(Map criteria) {
		getSqlMapClientTemplate().delete(
				"edu.MantenimientoEDUGenericoSQL.deleteTemporalInterfazDatamart", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#executePrevioDevuelveObjetivoAsistencia(java.util.Map)
	 */
	public void executePrevioDevuelveObjetivoAsistencia(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.executePrevioDevuelveObjetivoAsistencia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getObjetivoAsistenciaByCriteria(java.util.Map)
	 */
	public List getObjetivoAsistenciaByCriteria(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getObjetivoAsistenciaByCriteria",
				criteria);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#executeActualizaObjetivoAsistencia(java.util.Map)
	 */
	public void executeActualizaObjetivoAsistencia(Map criteria) {
		getSqlMapClientTemplate().update(
					"edu.MantenimientoEDUGenericoSQL.executeActualizaObjetivoAsistencia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion)
	 */
	public List getMensajeEducacion(MensajeEducacion mensajeEducacion) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getMensajeEducacion",
				mensajeEducacion);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getMensajeEducacionById(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion)
	 */
	public MensajeEducacion getMensajeEducacionById(MensajeEducacion mensajeEducacion) {
		MensajeEducacion resultado = (MensajeEducacion) getSqlMapClientTemplate().queryForObject(
					"edu.MantenimientoEDUGenericoSQL.getMensajeEducacionById",
					mensajeEducacion);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getEstadoCapacitacionByCriteria(java.util.Map)
	 */
	public List getEstadoCapacitacionByCriteria(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getEstadoCapacitacionByCriteria",
				criteria);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateEstadoMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateEstadoMensajeEducacion", mensajeEducacion);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateEstadoMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateMensajeEducacion", mensajeEducacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) {
		//inserta en EDU_PARAM_MENSA
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertMensajeEducacion", mensajeEducacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getSiguienteCodigoMensaje(java.util.Map)
	 */
	public String getSiguienteCodigoMensaje(Map criteria) {
		String prefijo = (String) criteria.get("prefijoCodigoMensaje");
		String retorno = new String();
		Integer codigo = (Integer)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getMaxCodigoMensaje",
				criteria);
		if (codigo==null || codigo.intValue() <= 0) 
			retorno = prefijo + "001";
		else {
			codigo = new Integer(codigo.intValue() + 1);
			retorno = prefijo + StringUtils.leftPad(codigo.toString().trim(), 3, "0");
		}	
		return retorno;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateEstadoEmpresaComercializadora(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateEstadoEmpresaComercializadora", empresa);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateEmpresaComercializadora(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateEmpresaComercializadora", empresa);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertEmpresaComercializadora(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertEmpresaComercializadora", empresa);
	}
	
	
	
	public List getParametroRegistroClasificacionByCriteria(ParametroRegistroClasificacion parametroRegistroClasificacion) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getParametroRegistroClasificacionByCriteria",
				parametroRegistroClasificacion);
		return resultado;
	}

	
	public void updateParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateParametroRegistroClasificacion",
				parametroRegistroClasificacion);
	}
	
	public void insertParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertParametroRegistroClasificacion",
				parametroRegistroClasificacion);
	}
//sb
	public List getListEntidadMultiple() {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getListEntidadMultiple");
		return resultado;
	}

	public List getListTipoEntidad() {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getListTipoEntidad");
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getEstadoCapacitacion(biz.belcorp.ssicc.edu.dao.model.EstadoCapacitacion)
	 */
	public EstadoCapacitacion getEstadoCapacitacion(EstadoCapacitacion estadoCapac) {
		EstadoCapacitacion resultado = (EstadoCapacitacion) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getEstadoCapacitacion",
				estadoCapac);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getDevuelveCampanna(java.util.Map)
	 */
	public String getDevuelveCampanna(Map criteria) {
		String retorno = (String) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getDevuelveCampanna", criteria);
		return retorno;
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado)
     */
    public List getCronogramaDictado(CronogramaDictado cronogramaDictado) {
    	List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCronogramaDictado", cronogramaDictado);
    	return resultado;
    }
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getCronogramaDictadoEnvioByCriteria(java.util.Map)
     */
    public List getCronogramaDictadoEnvioByCriteria(Map criteria) {
    	List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCronogramaDictadoEnvioByCriteria", criteria);
    	return resultado;
    }
    
	
    public CronogramaDictado getCronogramaDictadoById(CronogramaDictado cronogramaDictado) {
    	return (CronogramaDictado) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getCronogramaDictadoById", cronogramaDictado);
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateEliminarCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEliminarCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.updateEliminarCronogramaDictado", cronogramaDictado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.updateCronogramaDictado", 
				cronogramaDictado);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) {
		getSqlMapClientTemplate().insert("edu.MantenimientoEDUGenericoSQL.insertCronogramaDictado", 
				cronogramaDictado);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getDevuelveIdSgteCronogramaDictado()
	 */
	public Long getDevuelveIdSgteCronogramaDictado() {
		Long id = (Long)getSqlMapClientTemplate().
			queryForObject("edu.MantenimientoEDUGenericoSQL.getDevuelveIdSgteCronogramaDictado");
		return id;
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertCronogramaDictadoZona(biz.belcorp.ssicc.edu.dao.model.CronogramaDictadoZona, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertCronogramaDictadoZona(CronogramaDictadoZona cronogramaDictadoZona, Usuario usuario) {
    	getSqlMapClientTemplate().insert("edu.MantenimientoEDUGenericoSQL.insertCronogramaDictadoZona", 
				cronogramaDictadoZona);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#deleteCronogramaDictadoZona(biz.belcorp.ssicc.edu.dao.model.CronogramaDictadoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteCronogramaDictadoZona(CronogramaDictadoZona cronogramaDictadoZona, Usuario usuario) {
		getSqlMapClientTemplate().delete("edu.MantenimientoEDUGenericoSQL.deleteCronogramaDictadoZona", 
				cronogramaDictadoZona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getLocalByCriteria(java.util.Map)
	 */
	public List getLocalByCriteria(Map criteria) {
    	List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getLocalByCriteria", criteria);
    	return resultado;
    }
	
	public List getCronogramaDictadoZonaById(CronogramaDictadoZona cronogramaDictadoZona) {
		return  getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCronogramaDictadoZonaById", cronogramaDictadoZona);
	}
	
	public EntidadGenerico getEntidadEstadoCapacitacion(EntidadGenerico entidadGenerico) {
		return (EntidadGenerico) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getEntidadEstadoCapacitacion", entidadGenerico);
	}

	public EntidadGenerico getEntidadFrecuenciaCali(EntidadGenerico entidadGenerico) {
		return (EntidadGenerico) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getEntidadFrecuenciaCali", entidadGenerico);
	}

	public List getCronogramaDictadoByZona(CronogramaDictado cronogramaDictado) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCronogramaDictadoByZona", cronogramaDictado);
    	return resultado;
	}

	public List getLocales(Local local) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getLocales", local);
    	return resultado;
	}

	public void updateEstadoLocal(Local local, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.updateEstadoLocal", 
				local);
	}

	public void updateLocal(Local local, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.updateLocal", 
				local);
		
	}

	public void insertLocal(Local local, Usuario usuario) {
		getSqlMapClientTemplate().insert("edu.MantenimientoEDUGenericoSQL.insertLocal", 
				local);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#executeGenerarReporteCronogramaDictado(java.util.Map)
	 */
	public void executeGenerarReporteCronogramaDictado(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeGenerarReporteCronogramaDictado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getTipoEjecutivas()
	 */
	public List getTipoEjecutivas() {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getTipoEjecutivas");
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getSiguienteCodigoLocal()
	 */
	public String getSiguienteCodigoLocal(Local local) {
		String retorno="";
		Integer codigo = (Integer)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getSiguienteCodigoLocal",local);
		codigo = new Integer(codigo.intValue() + 1);
		retorno = StringUtils.leftPad(codigo.toString().trim(), 3,"0");
		return retorno;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getSalas(biz.belcorp.ssicc.edu.dao.model.Sala)
	 */
	public List getSalas(Sala sala) {
		List resultado = getSqlMapClientTemplate().queryForList(
		"edu.MantenimientoEDUGenericoSQL.getSalas",sala);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getSiguienteCodigoSala(biz.belcorp.ssicc.edu.dao.model.Sala)
	 */
	public String getSiguienteCodigoSala(Sala sala) {
		String retorno="";
		Integer codigo = (Integer)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getSiguienteCodigoSala",sala);
		codigo = new Integer(codigo.intValue() + 1);
		retorno = StringUtils.leftPad(codigo.toString().trim(), 3,"0");
		return retorno;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#deleteSala(biz.belcorp.ssicc.edu.dao.model.Sala)
	 */
	public void deleteSala(Sala sala) {
		getSqlMapClientTemplate().delete("edu.MantenimientoEDUGenericoSQL.deleteSala", 
				sala);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertSala(biz.belcorp.ssicc.edu.dao.model.Sala, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSala(Sala sala, Usuario usuario) {
		getSqlMapClientTemplate().insert("edu.MantenimientoEDUGenericoSQL.insertSala", 
				sala);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateSala(biz.belcorp.ssicc.edu.dao.model.Sala, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateSala(Sala sala, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.updateSala", 
				sala);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getConexionExternaByCriteria(java.util.Map)
	 */
	public ConexionExterna getConexionExternaByCriteria(Map criteria)  {
		ConexionExterna conexion = new ConexionExterna();
		conexion = (ConexionExterna) getSqlMapClientTemplate().queryForObject("edu.MantenimientoEDUGenericoSQL.getConexionExternaByCriteria", 
				criteria);
		return conexion;
	}

	public List getParamConexionExterna(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getParamConexionExterna",
				params);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte)
	 */
	public ParametroReporte getParametroReporte(ParametroReporte parametro) {
		return (ParametroReporte) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getParametroReporte",
				parametro);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#getParametroReporteLista(biz.belcorp.ssicc.edu.dao.model.ParametroReporte)
	 */
	public List getParametroReporteLista(ParametroReporte parametro) {
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getParametroReporte",
				parametro);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#updateParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroReporte(ParametroReporte parametro, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateParametroReporte",
				parametro);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#insertParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroReporte(ParametroReporte parametro, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertParametroReporte",
				parametro);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.MantenimientoEDUGenericoDAO#deleteParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteParametroReporte(ParametroReporte parametro, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateParametroReporte",
				parametro);
	}

	public String getLongitudCodCliente(String codigoPais) {
		Map map= new HashMap();
		map.put("codigoPais",codigoPais);
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getLongitudCodCliente",map);
		return codigo;
	}

	public void executeGenerarResumenProgramadasPlanilla(Map params) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeGenerarResumenProgramadasPlanilla", params);
		
	}

	public String getLongitudCodInstructora(String codigoPais) {
		Map map= new HashMap();
		map.put("codigoPais",codigoPais);
		String codigo = (String)getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getLongitudCodInstructora",map);
		return codigo;
	}
	
       public String getNumPeriodo(Map criteria) {
		return "1";
	}

	public List getIndicadorSeleccion() {
		  List resultado = getSqlMapClientTemplate().queryForList(
					"edu.MantenimientoEDUGenericoSQL.getIndicadorSeleccion");
		  return resultado;
	}

	public List getLocalesByInstructora(Map params) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getLocalesByInstructora", params);
    	return resultado;
	}

	public List getListaTipoEntidadGenerico(Map params){
		List resultado = getSqlMapClientTemplate().queryForList("edu.MantenimientoEDUGenericoSQL.getListaTipoEntidadGenerico", params);
    	return resultado;
	}

	public List getCamposEntidadGenerico(String nombreEntidad) {
		List resultado = getSqlMapClientTemplate().queryForList("edu.MantenimientoEDUGenericoSQL.getCamposEntidadGenerico", nombreEntidad);
    	return resultado;
	}
	
	public EntidadGenericoDefinicion getDefinicionEntidadGenerico(String nombreEntidad){
		return (EntidadGenericoDefinicion)getSqlMapClientTemplate().queryForObject("edu.MantenimientoEDUGenericoSQL.getDefinicionEntidadGenerico", nombreEntidad);
	}

	public String getMensajeEducacionEquiv(MensajeEducacion mensaje) {
		String codigoMensaEquiv=(String)getSqlMapClientTemplate().queryForObject("edu.MantenimientoEDUGenericoSQL.getMensajeEducacionEquiv",mensaje);
		return codigoMensaEquiv;
	}

	public void updateMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario) {
		//Actualiza Tabla EQUIVALENTE EDU_MENSA_EQUIV
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateMensajeEducacionEquiv", mensaje);
		
	}

	public void insertMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario) {
		//inserta Tabla EQUIVALENTE EDU_MENSA_EQUIV
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertMensajeEducacionEquiv", mensaje);
		
	}

	public void updateEstadoMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.updateEstadoMensajeEducacionEquiv", mensaje);
		
	} 

	public List getCodigoEquivalenteClasificacion(String codigoTipoClasEquiv) {
		List resultado = getSqlMapClientTemplate().queryForList(
				 "edu.MantenimientoEDUGenericoSQL.getCodigoEquivalenteClasificacion", codigoTipoClasEquiv);
    	return resultado;
	} 

	public String getDevuelveSiguienteNivel(Map criteria) {
		String nivel = (String) getSqlMapClientTemplate().queryForObject(
				"edu.MantenimientoEDUGenericoSQL.getDevuelveSiguienteNivel", criteria);
		return nivel;
	} 

	/*ws para reporte cronograma dictado*/
	public List getSubReporteCronogramaLocalWs(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				 "edu.MantenimientoEDUGenericoSQL.getSubReporteCronogramaLocalWs", criteria);
		return resultado;
	}

	public List getSubReporteCronogramaCursoWs(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				 "edu.MantenimientoEDUGenericoSQL.getSubReporteCronogramaCursoWs", criteria);
		return resultado;
	}

	public List getReporteCronogramaDictadoWs(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				 "edu.MantenimientoEDUGenericoSQL.getReporteCronogramaDictadoWs", criteria);
		return resultado;
	} 
	
	public List getListaDocumentos(Map criteria){
		return getSqlMapClientTemplate().queryForList("edu.MantenimientoEDUGenericoSQL.getListaDocumentos", criteria);
	}	
	
	public List getCronogramaDictadoProgramadoByZona(CronogramaDictado cronogramaDictado) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCronogramaDictadoProgramadoByZona", cronogramaDictado);
    	return resultado;
	}

	public List getTipoAsistenciaCapacitadas(ParametroGenerico parametroGenerico) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getTipoAsistenciaCapacitadas",
				parametroGenerico);
		return resultado;
	}

	public List getMatrizClasificacionByCriteria(Map criteria){		
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getMatrizClasificacionByCriteria",
				criteria);
	}
	
	public List getCampanhasCriteria(Map criteria){		
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCampanhasCriteria",
				criteria);
	}

	public List getCampanhasActivar(Map criteria){		
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCampanhasActivar",
				criteria);
	}

	public List getCampanhasDesactivar(Map criteria){		
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCampanhasDesactivar",
				criteria);
	}
	
	public List getCampanhasTodas(Map criteria){		
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getCampanhasTodas",
				criteria);
	}

	public void insertClasificacion(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertClasificacion",
				criteria);
	}

	public void desactivarClasificaciones(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.desactivarClasificaciones", criteria);
		
	}

	public void activarClasificaciones(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"edu.MantenimientoEDUGenericoSQL.activarClasificaciones", criteria);
		
	}
						
	public void insertNuevaClasificacion(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"edu.MantenimientoEDUGenericoSQL.insertNuevaClasificacion",
				criteria);
	}

	/**
	 * Retorna el listado de cronograms pro zona
	 * @author sbuchelli
	 *  @param map
	 * */
	public List getZonasCronogramaByRegion(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getZonasCronogramaByRegion",
				map);
	}

	/**
	 * Retorna el listado de clasificaciones
	 * @author sbuchelli
	 *  @param map
	 * */
	public List getListClasificaciones(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"edu.MantenimientoEDUGenericoSQL.getListClasificaciones",
				map);
	}
}
