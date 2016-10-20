package biz.belcorp.ssicc.service.edu.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUMultiEntidadDAO;
import biz.belcorp.ssicc.dao.edu.model.ConexionExterna;
import biz.belcorp.ssicc.dao.edu.model.ControlFacturacion;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictado;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictadoZona;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author peextrvela
 *
 */
/**
 * @author peextcbazalar
 *
 */
@Service("edu.mantenimientoEDUGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoEDUGenericoServiceImpl extends BaseService implements MantenimientoEDUGenericoService {

	@Resource(name="edu.mantenimientoEDUGenericoDAO")
	MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO;
	
	@Resource(name="edu.procesoEDUComercialService")
	ProcesoEDUComercialService	procesoEDUComercialService;
	
	@Resource(name="edu.mantenimientoEDUMultiEntidadDAO")
	MantenimientoEDUMultiEntidadDAO mantenimientoEDUMultiEntidadDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public ParametroCursoCapacitacion getParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getParametroCurso(parametroCursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateParametroCurso(parametroCursoCapacitacion,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getAmbitoDictados(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getAmbitoDictados(ParametroGenerico parametroGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getAmbitoDictados(parametroGenerico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getFrecuenciaCalificaciones(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getFrecuenciaCalificaciones(ParametroGenerico parametroGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getFrecuenciaCalificaciones(parametroGenerico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getNivelVentas(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getNivelVentas(ParametroGenerico parametroGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getNivelVentas(parametroGenerico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getSecuenciaPedidos(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getSecuenciaPedidos(ParametroGenerico parametroGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getSecuenciaPedidos(parametroGenerico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getTipoDespachos(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getTipoDespachos(ParametroGenerico parametroGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getTipoDespachos(parametroGenerico);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getTipoCostoCurso(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getTipoCostoCurso(ParametroGenerico parametroGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getTipoCostoCurso(parametroGenerico);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getTipoIndicadorDespachos(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getTipoIndicadorDespachos(ParametroGenerico parametroGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getTipoIndicadorDespachos(parametroGenerico);
	}

	/**
	 * @return Returns the mantenimientoEDUGenericoDAO.
	 */
	public MantenimientoEDUGenericoDAO getMantenimientoEDUGenericoDAO() {
		return mantenimientoEDUGenericoDAO;
	}

	/**
	 * @param mantenimientoEDUGenericoDAO The mantenimientoEDUGenericoDAO to set.
	 */
	public void setMantenimientoEDUGenericoDAO(
			MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO) {
		this.mantenimientoEDUGenericoDAO = mantenimientoEDUGenericoDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getEmpresasComercializadorasByPais(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora)
	 */
	public List getEmpresasComercializadorasByPais(EmpresaComercializadora empresaComercializadora) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getEmpresasComercializadorasByPais(empresaComercializadora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getMaestroClientes(biz.belcorp.ssicc.edu.dao.model.MaestroCliente)
	 */
	public List getMaestroClientes(MaestroCliente maestroClientes) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getMaestroClientes(maestroClientes);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getMaestroInstructoraByCriteria(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora)
	 */
	public List getMaestroInstructoraByCriteria(MaestroInstructora maestroInstructora) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getMaestroInstructoraByCriteria(maestroInstructora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getParametroClasificacionByCriteria(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion)
	 */
	public List getParametroClasificacionByCriteria(ParametroClasificacion parametroClasificacion) {
		return mantenimientoEDUGenericoDAO.getParametroClasificacionByCriteria(parametroClasificacion);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertMaestroInstructora(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario) throws Exception {
		try{
		mantenimientoEDUGenericoDAO.insertMaestroInstructora(maestroInstructora,usuario);
		}catch(Exception e){
			String mensajeError = this.getKeyMessage("mantenimientoEDUMaestroInstructora.error.existeInstructora", usuario);
			throw new Exception(mensajeError);                                               
		}
		 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertParametroClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) throws Exception {
		//Inserta en la tabla de parmetros de beneficio
		mantenimientoEDUGenericoDAO.insertParametroClasificacion(parametroClasificacion,usuario);
		
		//Indicador si usa la tabla de equivalencia conexion :Oracle 
		if (parametroClasificacion.isMostrarClasificacionEquivalencia()) {
			Integer contador = mantenimientoEDUGenericoDAO.getExisteParametroEquivalenciaClasificacion(parametroClasificacion);
			if (contador.intValue() > 0) 
				mantenimientoEDUGenericoDAO.updateParametroEquivalenciaClasificacion(parametroClasificacion, usuario);
			else
				mantenimientoEDUGenericoDAO.insertParametroEquivalenciaClasificacion(parametroClasificacion, usuario);
		}
		Map params = BeanUtils.describe(parametroClasificacion);
		//Va al sistema Comercial 
		if (verificaConexionExternaSistemaComercial(parametroClasificacion.getCodigoPais(),parametroClasificacion.getCodigoEmpresa())) {
			procesoEDUComercialService.executeInsertMantenimientoClasificacion(parametroClasificacion.getCodigoPais(), params);	
		}	
		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeMaestroInstructora(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRemoveMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateRemoveMaestroInstructora(maestroInstructora,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeParametroClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRemoveParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateRemoveParametroClasificacion(parametroClasificacion,usuario);
		log.debug(" parametroClasificacion.isMostrarClasificacionEquivalencia() " + parametroClasificacion.isMostrarClasificacionEquivalencia());
//		Indicador si usa la tabla de equivalencia conexion :Oracle 
		if (parametroClasificacion.isMostrarClasificacionEquivalencia()) 
				mantenimientoEDUGenericoDAO.updateParametroEquivalenciaClasificacion(parametroClasificacion, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateMaestroInstructora(biz.belcorp.ssicc.edu.dao.model.MaestroInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMaestroInstructora(MaestroInstructora maestroInstructora, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateMaestroInstructora(maestroInstructora,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateParametroClasificacion(biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroClasificacion(ParametroClasificacion parametroClasificacion, Usuario usuario) throws Exception {
		//	Update en la tabla de parmetros de beneficio
		mantenimientoEDUGenericoDAO.updateParametroClasificacion(parametroClasificacion,usuario);
		
		//Indicador si usa la tabla de equivalencia conexion :Oracle 
		if (parametroClasificacion.isMostrarClasificacionEquivalencia()) {
			Integer contador = mantenimientoEDUGenericoDAO.getExisteParametroEquivalenciaClasificacion(parametroClasificacion);
			if (contador.intValue() > 0) 
				mantenimientoEDUGenericoDAO.updateParametroEquivalenciaClasificacion(parametroClasificacion, usuario);
			else
				mantenimientoEDUGenericoDAO.insertParametroEquivalenciaClasificacion(parametroClasificacion, usuario);
		}
		Map params = BeanUtils.describe(parametroClasificacion);
		
		if (verificaConexionExternaSistemaComercial(parametroClasificacion.getCodigoPais(),parametroClasificacion.getCodigoEmpresa())) {
			procesoEDUComercialService.executeInsertUpdateMantenimientoClasificacionValida(parametroClasificacion.getCodigoPais(),params);	
		}			
		
	}

	public String getMaxMaestroInstructora(MaestroInstructora maestroInstructora) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getMaxMaestroInstructora(maestroInstructora);
	}

	public String getMaxParametroClasificacion(ParametroClasificacion parametroClasificacion) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getMaxParametroClasificacion(parametroClasificacion);
	}

	public List getTipoAsistencia(ParametroGenerico parametroGenerico) {
		return mantenimientoEDUGenericoDAO.getTipoAsistencia(parametroGenerico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getEstadoNivel(biz.belcorp.ssicc.edu.dao.model.ParametroGenerico)
	 */
	public List getEstadoNivel(ParametroGenerico parametroGenerico) {
		return mantenimientoEDUGenericoDAO.getEstadoNivel(parametroGenerico);	}
	
	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getControlFacturacionById(java.util.Map)
	 */
	public ControlFacturacion getControlFacturacionById(Map criteria) {
		// TODO Auto-generated method stub
		return this.mantenimientoEDUGenericoDAO.getControlFacturacionById(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#insertServicioCapacitacion(biz.belcorp.ssicc.edu.dao.model.ServicioCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario) {
		this.mantenimientoEDUGenericoDAO.insertServicioCapacitacion(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#removeServicioCapacitacion(biz.belcorp.ssicc.edu.dao.model.ServicioCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeServicioCapacitacion(ServicioCapacitacion bean, Usuario usuario) {
		this.mantenimientoEDUGenericoDAO.removeServicioCapacitacion(bean, usuario);
	}

	public List getControlFacturacionByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		return this.mantenimientoEDUGenericoDAO.getControlFacturacionByCriteria(criteria);
	}
	
	public void updateCerrarCampanaControlFacturacion(ControlFacturacion controlFacturacion, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoEDUGenericoDAO.updateCerrarCampanaControlFacturacion(controlFacturacion, usuario);		
	}
	
	public void updateDesactivarEquivalenciaMatriz(EquivalenciaMatrizServicio  equivalenciaMatrizServicio, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoEDUGenericoDAO.updateDesactivarEquivalenciaMatriz(equivalenciaMatrizServicio, usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getMaxParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion)
	 */
	public String getMaxParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getMaxParametroCurso(parametroCursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#insertParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion, Usuario usuario) {
		mantenimientoEDUGenericoDAO.insertParametroCurso(parametroCursoCapacitacion,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateEstadoParametroCurso(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoParametroCurso(ParametroCursoCapacitacion parametroCurso, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoEDUGenericoDAO.updateEstadoParametroCurso(parametroCurso,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getParametroCursoByCriteria(biz.belcorp.ssicc.edu.dao.model.ParametroCursoCapacitacion)
	 */
	public List getParametroCursoByCriteria(ParametroCursoCapacitacion parametroCursoCapacitacion) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getParametroCursoByCriteria(parametroCursoCapacitacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getNroLoteActualInterfazDatamart(java.util.Map)
	 */
	public String getNroLoteActualInterfazDatamart(Map criteria) {
		return mantenimientoEDUGenericoDAO.getNroLoteActualInterfazDatamart(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getNroLoteSiguienteInterfazDatamart(java.util.Map)
	 */
	public String getNroLoteSiguienteInterfazDatamart(Map criteria) {
		return mantenimientoEDUGenericoDAO.getNroLoteSiguienteInterfazDatamart(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateNroLoteInterfazDatamart(java.util.Map)
	 */
	public void updateNroLoteInterfazDatamart(Map criteria) {
		mantenimientoEDUGenericoDAO.updateNroLoteInterfazDatamart(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#deleteTemporalInterfazDatamart(java.util.Map)
	 */
	public void deleteTemporalInterfazDatamart(Map criteria) {
		mantenimientoEDUGenericoDAO.deleteTemporalInterfazDatamart(criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#executePrevioDevuelveObjetivoAsistencia(java.util.Map)
	 */
	public void executePrevioDevuelveObjetivoAsistencia(Map criteria) {
		mantenimientoEDUGenericoDAO.executePrevioDevuelveObjetivoAsistencia(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getObjetivoAsistenciaByCriteria(java.util.Map)
	 */
	public List getObjetivoAsistenciaByCriteria(Map criteria) {
		return mantenimientoEDUGenericoDAO.getObjetivoAsistenciaByCriteria(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#executeActualizaObjetivoAsistencia(java.util.Map)
	 */
	public void executeActualizaObjetivoAsistencia(Map criteria) {
		String tipoMante = (String) criteria.get("tipoMante");
		String[] listaCampa01 = (String[]) criteria.get("listaPorcentajeCampa01");
		String[] listaCampa02 = (String[]) criteria.get("listaPorcentajeCampa02");
		String[] listaCampa03 = (String[]) criteria.get("listaPorcentajeCampa03");
		String[] listaCampa04 = (String[]) criteria.get("listaPorcentajeCampa04");
		String[] listaCampa05 = (String[]) criteria.get("listaPorcentajeCampa05");
		String[] listaCampa06 = (String[]) criteria.get("listaPorcentajeCampa06");
		String[] listaCampa07 = (String[]) criteria.get("listaPorcentajeCampa07");
		String[] listaCampa08 = (String[]) criteria.get("listaPorcentajeCampa08");
		String[] listaCampa09 = (String[]) criteria.get("listaPorcentajeCampa09");
		String[] listaCampa10 = (String[]) criteria.get("listaPorcentajeCampa10");
		String[] listaCampa11 = (String[]) criteria.get("listaPorcentajeCampa11");
		String[] listaCampa12 = (String[]) criteria.get("listaPorcentajeCampa12");
		String[] listaCampa13 = (String[]) criteria.get("listaPorcentajeCampa13");
		String[] listaCampa14 = (String[]) criteria.get("listaPorcentajeCampa14");
		String[] listaCampa15 = (String[]) criteria.get("listaPorcentajeCampa15");
		String[] listaCampa16 = (String[]) criteria.get("listaPorcentajeCampa16");
		String[] listaCampa17 = (String[]) criteria.get("listaPorcentajeCampa17");
		String[] listaCampa18 = (String[]) criteria.get("listaPorcentajeCampa18");
		String[] listaCampaTotal = (String[]) criteria.get("listaPorcentajeTotal");
		
		if (Constants.EDU_OBJETIVO_ASISTENCIA_REGION.equals(tipoMante)) {
			criteria.put("porcentajeCampanna01", listaCampa01[0]);
			criteria.put("porcentajeCampanna02", listaCampa02[0]);
			criteria.put("porcentajeCampanna03", listaCampa03[0]);
			criteria.put("porcentajeCampanna04", listaCampa04[0]);
			criteria.put("porcentajeCampanna05", listaCampa05[0]);
			criteria.put("porcentajeCampanna06", listaCampa06[0]);
			criteria.put("porcentajeCampanna07", listaCampa07[0]);
			criteria.put("porcentajeCampanna08", listaCampa08[0]);
			criteria.put("porcentajeCampanna09", listaCampa09[0]);
			criteria.put("porcentajeCampanna10", listaCampa10[0]);
			criteria.put("porcentajeCampanna11", listaCampa11[0]);
			criteria.put("porcentajeCampanna12", listaCampa12[0]);
			criteria.put("porcentajeCampanna13", listaCampa13[0]);
			criteria.put("porcentajeCampanna14", listaCampa14[0]);
			criteria.put("porcentajeCampanna15", listaCampa15[0]);
			criteria.put("porcentajeCampanna16", listaCampa16[0]);
			criteria.put("porcentajeCampanna17", listaCampa17[0]);
			criteria.put("porcentajeCampanna18", listaCampa18[0]);
			criteria.put("porcentajeTotal", listaCampaTotal[0]);
			criteria.put("codigoZona", null);
			mantenimientoEDUGenericoDAO.executeActualizaObjetivoAsistencia(criteria);
		}
		else {
			String[] listaZonas = (String[]) criteria.get("listacodigoZona");
			for(int i=0; i < listaZonas.length; i++ ) {
				criteria.put("porcentajeCampanna01", listaCampa01[i]);
				criteria.put("porcentajeCampanna02", listaCampa02[i]);
				criteria.put("porcentajeCampanna03", listaCampa03[i]);
				criteria.put("porcentajeCampanna04", listaCampa04[i]);
				criteria.put("porcentajeCampanna05", listaCampa05[i]);
				criteria.put("porcentajeCampanna06", listaCampa06[i]);
				criteria.put("porcentajeCampanna07", listaCampa07[i]);
				criteria.put("porcentajeCampanna08", listaCampa08[i]);
				criteria.put("porcentajeCampanna09", listaCampa09[i]);
				criteria.put("porcentajeCampanna10", listaCampa10[i]);
				criteria.put("porcentajeCampanna11", listaCampa11[i]);
				criteria.put("porcentajeCampanna12", listaCampa12[i]);
				criteria.put("porcentajeCampanna13", listaCampa13[i]);
				criteria.put("porcentajeCampanna14", listaCampa14[i]);
				criteria.put("porcentajeCampanna15", listaCampa15[i]);
				criteria.put("porcentajeCampanna16", listaCampa16[i]);
				criteria.put("porcentajeCampanna17", listaCampa17[i]);
				criteria.put("porcentajeCampanna18", listaCampa18[i]);
				criteria.put("porcentajeTotal", listaCampaTotal[i]);
				criteria.put("codigoZona", listaZonas[i]);
				mantenimientoEDUGenericoDAO.executeActualizaObjetivoAsistencia(criteria);
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion)
	 */
	public List getMensajeEducacion(MensajeEducacion mensajeEducacion) {
		return mantenimientoEDUGenericoDAO.getMensajeEducacion(mensajeEducacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getMensajeEducacionById(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion)
	 */
	public MensajeEducacion getMensajeEducacionById(MensajeEducacion mensajeEducacion) {
		return mantenimientoEDUGenericoDAO.getMensajeEducacionById(mensajeEducacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getEstadoCapacitacionByCriteria(java.util.Map)
	 */
	public List getEstadoCapacitacionByCriteria(Map criteria) {
		return mantenimientoEDUGenericoDAO.getEstadoCapacitacionByCriteria(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateEstadoEmpresaComercializadora(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) 
			throws Exception {
		String codigoPais = empresa.getCodigoPais();
		Map criteria = BeanUtils.describe(empresa);
		
		/* Actualizando registro */
		this.mantenimientoEDUGenericoDAO.updateEstadoEmpresaComercializadora(empresa, usuario);
		
		/* Eliminando registro en el Sistema Comercial */
		if (verificaConexionExternaSistemaComercial(codigoPais, empresa.getCodigoEmpresa())) {
			Integer contador = procesoEDUComercialService.getExisteEmpresaComercializadora(codigoPais, criteria);
			if (contador != null)
				if (contador.intValue() > 0) 
					procesoEDUComercialService.deleteEmpresaComercializadora(codigoPais, criteria);
		}	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateEmpresaComercializadora(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) throws Exception {
		String codigoPais = empresa.getCodigoPais();
		Map criteria = BeanUtils.describe(empresa);
		
		/* Grabando registro */
		this.mantenimientoEDUGenericoDAO.updateEmpresaComercializadora(empresa, usuario);
		
		/* Grabando registro en el Sistema Comercial */
		if (verificaConexionExternaSistemaComercial(codigoPais, empresa.getCodigoEmpresa())) {
			Integer contador = procesoEDUComercialService.getExisteEmpresaComercializadora(codigoPais, criteria);
			if (contador != null) {
				if (contador.intValue() > 0) 
						procesoEDUComercialService.updateEmpresaComercializadora(codigoPais, criteria);
					else
						procesoEDUComercialService.insertEmpresaComercializadora(codigoPais, criteria);
			}
		}	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#insertEmpresaComercializadora(biz.belcorp.ssicc.edu.dao.model.EmpresaComercializadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEmpresaComercializadora(EmpresaComercializadora empresa, Usuario usuario) throws Exception {
		String codigoPais = empresa.getCodigoPais();
		
		Map criteria = BeanUtils.describe(empresa);
		
		/* Grabando registro */
		this.mantenimientoEDUGenericoDAO.insertEmpresaComercializadora(empresa, usuario);
		
		/* Grabando registro en el Sistema Comercial */
		if (verificaConexionExternaSistemaComercial(codigoPais, empresa.getCodigoEmpresa())) {
			Integer contador = procesoEDUComercialService.getExisteEmpresaComercializadora(codigoPais, criteria);
			if (contador != null) {
				if (contador.intValue() > 0) 
					procesoEDUComercialService.updateEmpresaComercializadora(codigoPais, criteria);
				else
					procesoEDUComercialService.insertEmpresaComercializadora(codigoPais, criteria);
			}
		}	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateEstadoMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) throws Exception {
		String codigoPais = mensajeEducacion.getCodigoPais();
		Map criteria = BeanUtils.describe(mensajeEducacion);
		
		/* Actualizando registro */
		this.mantenimientoEDUGenericoDAO.updateEstadoMensajeEducacion(mensajeEducacion, usuario);
		
		/*Actualizando equivalencia*/
		if(mensajeEducacion.isMensajeEquivalente()){
			String codigoMensajeEquiv = mantenimientoEDUGenericoDAO.getMensajeEducacionEquiv(mensajeEducacion);
			log.debug("equiv " + codigoMensajeEquiv);
			mensajeEducacion.setCodigoMensajeEquivalencia(codigoMensajeEquiv);
			mantenimientoEDUGenericoDAO.updateEstadoMensajeEducacionEquiv(mensajeEducacion,usuario);
		}	
		
		/* Eliminando registro en el Sistema Comercial */
		if (verificaConexionExternaSistemaComercial(codigoPais, mensajeEducacion.getCodigoEmpresa())) {
			Integer contador = procesoEDUComercialService.getExisteMensajeEducacion(codigoPais, criteria);
			if (contador != null) {
				if (contador.intValue() > 0) 
					procesoEDUComercialService.deleteMensajeEducacion(codigoPais, criteria);
			}
		}	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) throws Exception {
		String codigoPais = mensajeEducacion.getCodigoPais();
		
		/* validaciones previas */
		if (Constants.EDU_MENSAJE_TIPO_MENSAJE_ESPECIFICO.equals(mensajeEducacion.getTipoMensaje())) {
			MensajeEducacion temp = new MensajeEducacion();
			temp.setCodigoPais(mensajeEducacion.getCodigoPais());
			temp.setCodigoEmpresa(mensajeEducacion.getCodigoEmpresa());
			temp.setCodigoCurso(mensajeEducacion.getCodigoCurso());
			temp.setTipoMensaje(mensajeEducacion.getTipoMensaje());
			temp.setCodigoEstadoCapacitacion(mensajeEducacion.getCodigoEstadoCapacitacion());
			temp.setOpcionMensaje(mensajeEducacion.getOpcionMensaje());
			List listaTemp = this.getMensajeEducacion(temp);
			if (listaTemp != null && listaTemp.size() > 0) {
				temp = (MensajeEducacion) listaTemp.get(0);
				if (!temp.getCodigoMensaje().equals(mensajeEducacion.getCodigoMensaje())) {
					String mensajeError = this.getKeyMessage("mantenimientoEDUMensajeForm.error.key", usuario);
					throw new Exception(mensajeError);
				}	
			}
		}
		else {
			mensajeEducacion.setCodigoEstadoCapacitacion(null);
		}
		
		/* Grabando registro */
		this.mantenimientoEDUGenericoDAO.updateMensajeEducacion(mensajeEducacion, usuario);
		
		/* Grabando  en tabla Equivalente */
		grabarMensajeEquivalente(mensajeEducacion, usuario);
		/* Grabando registro en el Sistema Comercial */
		this.grabarMensajeEducacionComercial(mensajeEducacion, codigoPais);
			
	}
	
	/**
	 * @param mensajeEducacion
	 * @param usuario
	 */
	private void grabarMensajeEquivalente(MensajeEducacion mensajeEducacion, Usuario usuario) throws Exception{
	 try{	
		if(mensajeEducacion.isMensajeEquivalente()){
			String codigoMensajeEquiv =mensajeEducacion.getCodigoEquivalenciaAnt(); 
				//mantenimientoEDUGenericoDAO.getMensajeEducacionEquiv(mensajeEducacion);
			log.debug("codigo equiv mensaje " + codigoMensajeEquiv );
			if(StringUtils.isNotEmpty(codigoMensajeEquiv)){
				mantenimientoEDUGenericoDAO.updateMensajeEducacionEquiv(mensajeEducacion, usuario);
			}	
			else //viene null cuando es nuevo
				mantenimientoEDUGenericoDAO.insertMensajeEducacionEquiv(mensajeEducacion, usuario);
		}
	 }catch(Exception e){
		 String mensajeError = this.getKeyMessage("mantenimientoEDUMensajeForm.error.keyEquiv", usuario);
		throw new Exception(mensajeError);
	 }
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#insertMensajeEducacion(biz.belcorp.ssicc.edu.dao.model.MensajeEducacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMensajeEducacion(MensajeEducacion mensajeEducacion, Usuario usuario) throws Exception {
		String codigo = new String();
		String codigoPais = mensajeEducacion.getCodigoPais();
		String prefijo = mensajeEducacion.getTipoMensaje();
		
		Map criteria = BeanUtils.describe(mensajeEducacion);
		MensajeEducacion temp = new MensajeEducacion();
		temp.setCodigoPais(mensajeEducacion.getCodigoPais());
		temp.setCodigoEmpresa(mensajeEducacion.getCodigoEmpresa());
		temp.setCodigoCurso(mensajeEducacion.getCodigoCurso());
		temp.setTipoMensaje(prefijo);
		
		/* Obteniendo codigo de mensaje */
		if (Constants.EDU_MENSAJE_TIPO_MENSAJE_GENERAL.equals(prefijo)) {
			criteria.put("prefijoCodigoMensaje", Constants.EDU_MENSAJE_PREFIJO_GENERAL);
			mensajeEducacion.setCodigoEstadoCapacitacion(null);
			mensajeEducacion.setOpcionMensaje(null);
		}	
		else {
			criteria.put("prefijoCodigoMensaje", Constants.EDU_MENSAJE_PREFIJO_ESPECIFICO);
			temp.setCodigoEstadoCapacitacion(mensajeEducacion.getCodigoEstadoCapacitacion());
			temp.setOpcionMensaje(mensajeEducacion.getOpcionMensaje());
			temp.setIndicadorCursoCosto(mensajeEducacion.getIndicadorCursoCosto());//nueva cracteristica del mensaje
		}	

		/* Validaciones previas */
		List listaTemp = this.getMensajeEducacion(temp);
		if (listaTemp != null && listaTemp.size() > 0) {
			String mensajeError = this.getKeyMessage("mantenimientoEDUMensajeForm.error.key", usuario);
			throw new Exception(mensajeError);
		}
		
		/* obteniendo codigo */
		codigo = mantenimientoEDUGenericoDAO.getSiguienteCodigoMensaje(criteria);
		mensajeEducacion.setCodigoMensaje(codigo);
		mensajeEducacion.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		
		/* Grabando registro */
		this.mantenimientoEDUGenericoDAO.insertMensajeEducacion(mensajeEducacion, usuario);
		
		/* Grabando  en tabla Equivalente */
		grabarMensajeEquivalente(mensajeEducacion, usuario);
		/* Grabando registro en el Sistema Comercial */
		this.grabarMensajeEducacionComercial(mensajeEducacion, codigoPais);	
	}

	/**
	 * Grabar Mensaje de Educacion en el Sistema comercial
	 * @param mensajeEducacion
	 * @param codigoPais
	 * @param criteria
	 * @throws Exception
	 */
	private void grabarMensajeEducacionComercial(MensajeEducacion mensajeEducacion, String codigoPais) throws Exception {
		if (verificaConexionExternaSistemaComercial(codigoPais, mensajeEducacion.getCodigoEmpresa())) {
			if (Constants.EDU_MENSAJE_TIPO_MENSAJE_GENERAL.equals(mensajeEducacion.getTipoMensaje())) {
				mensajeEducacion.setCodigoEstadoCapacitacion(" ");
				mensajeEducacion.setOpcionMensaje(new Integer(0));
			}else{
				//es mensaje especifico verificamos si es Capacitada 
				if(mensajeEducacion.getCodigoEstadoCapacitacion().equals(Constants.ESTADO_CAPACITADA_CAPACITADA)){
					mensajeEducacion.setOpcionMensaje(new Integer(0));
				}else{//otro estado de capacitacion 
					Integer op=mensajeEducacion.getOpcionMensaje();
					if(op==null) mensajeEducacion.setOpcionMensaje(new Integer(0));
					
				}
			}
			Map criteria = BeanUtils.describe(mensajeEducacion);
			Integer contador = procesoEDUComercialService.getExisteMensajeEducacion(codigoPais, criteria);
			if (contador != null) {
				if (contador.intValue() > 0) 
					procesoEDUComercialService.updateMensajeEducacion(codigoPais, criteria);
				else
					procesoEDUComercialService.insertMensajeEducacion(codigoPais, criteria);
			}
		}
	}
	
	
	public List getParametroRegistroClasificacionByCriteria(ParametroRegistroClasificacion parametroRegistroClasificacion) {
		return mantenimientoEDUGenericoDAO.getParametroRegistroClasificacionByCriteria(parametroRegistroClasificacion);	
	}

	public void updateParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateParametroRegistroClasificacion(parametroRegistroClasificacion,usuario);
	}
	
	public void insertParametroRegistroClasificacion(ParametroRegistroClasificacion parametroRegistroClasificacion, Usuario usuario) {
		mantenimientoEDUGenericoDAO.insertParametroRegistroClasificacion(parametroRegistroClasificacion,usuario);
	}

	/**
	 * @return Returns the procesoEDUComercialService.
	 */
	public ProcesoEDUComercialService getProcesoEDUComercialService() {
		return procesoEDUComercialService;
	}

	/**
	 * @param procesoEDUComercialService The procesoEDUComercialService to set.
	 */
	public void setProcesoEDUComercialService(
			ProcesoEDUComercialService procesoEDUComercialService) {
		this.procesoEDUComercialService = procesoEDUComercialService;
	}
	
	
	
	public List getListEntidadMultiple() {
		return mantenimientoEDUGenericoDAO.getListEntidadMultiple();
	}

	public List getMultiEntidadByCriteria(EntidadGenerico entidadGenerico) {
		entidadGenerico.setCampoCodigo(null);
		entidadGenerico.setCampoCodigoTipo(null);
		entidadGenerico.setCampoDescripcion(null);
		
		List listaCampos = mantenimientoEDUGenericoDAO.getCamposEntidadGenerico(entidadGenerico.getNombreEntidad());
		if(listaCampos != null && listaCampos.size()>0){
			entidadGenerico.setCampoCodigo((String)listaCampos.get(0));
			entidadGenerico.setCampoDescripcion((String)listaCampos.get(1));
			if(listaCampos.size()>2){
				entidadGenerico.setCampoCodigoTipo((String)listaCampos.get(2));
			}
		}
		
		return mantenimientoEDUMultiEntidadDAO.getMultiEntidadByCriteria(entidadGenerico);
	}

	public void updateEstadoMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario) {
		entidadGenerico.setCampoCodigo(null);
		entidadGenerico.setCampoCodigoTipo(null);
		entidadGenerico.setCampoDescripcion(null);
		
		List listaCampos = mantenimientoEDUGenericoDAO.getCamposEntidadGenerico(entidadGenerico.getNombreEntidad());
		if(listaCampos != null && listaCampos.size()>0){
			entidadGenerico.setCampoCodigo((String)listaCampos.get(0));
			entidadGenerico.setCampoDescripcion((String)listaCampos.get(1));
			if(listaCampos.size()>2){
				entidadGenerico.setCampoCodigoTipo((String)listaCampos.get(2));
			}
		}
		mantenimientoEDUMultiEntidadDAO.updateEstadoMultiEntidad(entidadGenerico,usuario); 
	}

	public List getListTipoEntidad() {
		return mantenimientoEDUGenericoDAO.getListTipoEntidad();
		
	}

	//devuelve la cadena de error-debido a que se trabajo con jdbc por ser un caso particular
	public String updateMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario) {
		entidadGenerico.setCampoCodigo(null);
		entidadGenerico.setCampoCodigoTipo(null);
		entidadGenerico.setCampoDescripcion(null);
		
		List listaCampos = mantenimientoEDUGenericoDAO.getCamposEntidadGenerico(entidadGenerico.getNombreEntidad());
		if(listaCampos != null && listaCampos.size()>0){
			entidadGenerico.setCampoCodigo((String)listaCampos.get(0));
			entidadGenerico.setCampoDescripcion((String)listaCampos.get(1));
			if(listaCampos.size()>2){
				entidadGenerico.setCampoCodigoTipo((String)listaCampos.get(2));
			}
		}
		return mantenimientoEDUMultiEntidadDAO.updateMultiEntidad(entidadGenerico,usuario); 
		
	}
    //devuelve la cadena de error
	public String insertMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario) {
		entidadGenerico.setCampoCodigo(null);
		entidadGenerico.setCampoCodigoTipo(null);
		entidadGenerico.setCampoDescripcion(null);
		
		List listaCampos = mantenimientoEDUGenericoDAO.getCamposEntidadGenerico(entidadGenerico.getNombreEntidad());
		if(listaCampos != null && listaCampos.size()>0){
			entidadGenerico.setCampoCodigo((String)listaCampos.get(0));
			entidadGenerico.setCampoDescripcion((String)listaCampos.get(1));
			if(listaCampos.size()>2){
				entidadGenerico.setCampoCodigoTipo((String)listaCampos.get(2));
			}
		}
		 return mantenimientoEDUMultiEntidadDAO.insertMultiEntidad(entidadGenerico,usuario);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#verificaConexionExternaSistemaComercial(java.lang.String, java.lang.String)
	 */
	public boolean verificaConexionExternaSistemaComercial(String codigoPais, String codigoEmpresa) {
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoEmpresa(codigoEmpresa);
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro = this.getParametroCurso(parametro);
		
		if (parametro != null) {
			String conexionExterna = parametro.getIndicadorConexionExterna();
			if (StringUtils.isNotBlank(conexionExterna) && (Constants.EDU_CONEXION_EXTERNA_ACTIVADO.equals(conexionExterna))) {
				return true;
			}
		}
		return false;
	}

	public EstadoCapacitacion getEstadoCapacitacion(EstadoCapacitacion estadoCapac) {
		return mantenimientoEDUGenericoDAO.getEstadoCapacitacion(estadoCapac);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getDevuelveCampanna(java.util.Map)
	 */
	public String getDevuelveCampanna(Map criteria) {
		return mantenimientoEDUGenericoDAO.getDevuelveCampanna(criteria); 
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado)
	 */
	public List getCronogramaDictado(CronogramaDictado cronogramaDictado) {
		return mantenimientoEDUGenericoDAO.getCronogramaDictado(cronogramaDictado); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getCronogramaDictadoById(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado)
	 */
	public CronogramaDictado getCronogramaDictadoById(CronogramaDictado cronogramaDictado) {
		return mantenimientoEDUGenericoDAO.getCronogramaDictadoById(cronogramaDictado); 
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateEliminarCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEliminarCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) throws Exception {
		mantenimientoEDUGenericoDAO.updateEliminarCronogramaDictado(cronogramaDictado, usuario);
		String codigoPais = cronogramaDictado.getCodigoPais();
		if (verificaConexionExternaSistemaComercial(cronogramaDictado.getCodigoPais(), cronogramaDictado.getCodigoEmpresa())) {
			Map params = BeanUtils.describe(cronogramaDictado);
			procesoEDUComercialService.deleteCronogramaDictado(codigoPais, params);
		}	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) throws Exception {
		/* Actualizando Cronograma Dictado */
		mantenimientoEDUGenericoDAO.updateCronogramaDictado(cronogramaDictado, usuario);
		
		/* Insertando en Cronograma Dictado Zona */
		CronogramaDictadoZona cronogramaDictadoZona = new CronogramaDictadoZona();
		this.setearCronogramaDictadoZona(cronogramaDictado, cronogramaDictadoZona);
		mantenimientoEDUGenericoDAO.deleteCronogramaDictadoZona(cronogramaDictadoZona, usuario);
		String[] listaZonas = cronogramaDictado.getListaZonas();
		for (int i=0; i < listaZonas.length; i++) {
			String codigoZona = listaZonas[i];
			cronogramaDictadoZona.setCodigoZona(codigoZona);
			mantenimientoEDUGenericoDAO.insertCronogramaDictadoZona(cronogramaDictadoZona, usuario);
		}
		
		/* Grabando en el Sistema Comercial */
		this.grabarCronogramaDictadoComercial(cronogramaDictado, listaZonas);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#insertCronogramaDictado(biz.belcorp.ssicc.edu.dao.model.CronogramaDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCronogramaDictado(CronogramaDictado cronogramaDictado, Usuario usuario) throws Exception {
		Long id = mantenimientoEDUGenericoDAO.getDevuelveIdSgteCronogramaDictado();
		cronogramaDictado.setOidCronograma(id);
		
		/* Insertando en Cronograma Dictado */
		mantenimientoEDUGenericoDAO.insertCronogramaDictado(cronogramaDictado, usuario);
		
		/* Insertando en Cronograma Dictado Zona */
		CronogramaDictadoZona cronogramaDictadoZona = new CronogramaDictadoZona(); 
		this.setearCronogramaDictadoZona(cronogramaDictado, cronogramaDictadoZona);
		String[] listaZonas = cronogramaDictado.getListaZonas();
		for (int i=0; i < listaZonas.length; i++) {
			String codigoZona = listaZonas[i];
			cronogramaDictadoZona.setCodigoZona(codigoZona);
			mantenimientoEDUGenericoDAO.insertCronogramaDictadoZona(cronogramaDictadoZona, usuario);
		}
		
		/* Grabando en el Sistema Comercial */
		this.grabarCronogramaDictadoComercial(cronogramaDictado, listaZonas);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#enviarCronogramaDictado(java.util.Map)
	 */
	public void enviarCronogramaDictado(Map criteria) throws Exception {
		List lista = mantenimientoEDUGenericoDAO.getCronogramaDictadoEnvioByCriteria(criteria);
		this.grabarEnvioCronogramaDictadoComercial(criteria, lista);
	}

	/**
	 * @param cronogramaDictado
	 * @param codigoPais
	 * @param listaZonas
	 * @throws Exception
	 */
	private void grabarEnvioCronogramaDictadoComercial(Map criteria, List lista) 
			throws Exception {
		String codigoPais = (String) criteria.get("codigoPais");
		String codigoEmpresa = (String) criteria.get("codigoEmpresa");
		
		if (verificaConexionExternaSistemaComercial(codigoPais, codigoEmpresa)) {
			procesoEDUComercialService.insertEnvioCronogramaDictado(codigoPais, criteria, lista);
		}
	}
	
	/**
	 * @param cronogramaDictado
	 * @param codigoPais
	 * @param listaZonas
	 * @throws Exception
	 */
	private void grabarCronogramaDictadoComercial(CronogramaDictado cronogramaDictado, String[] listaZonas) 
			throws Exception {
		String codigoPais = cronogramaDictado.getCodigoPais();
		if (verificaConexionExternaSistemaComercial(cronogramaDictado.getCodigoPais(), cronogramaDictado.getCodigoEmpresa())) {
			log.debug("crono fecha dictado "+cronogramaDictado.getFechaDictado());
			String fechaDictado=DateUtil.convertDateToString(cronogramaDictado.getFechaDictado());
			Map params = BeanUtils.describe(cronogramaDictado);
			params.put("listaZonas",listaZonas);
			params.put("fechaDictado",fechaDictado);
			log.debug("params "+params );
			
			Integer contador = procesoEDUComercialService.getExisteCronogramaDictado(codigoPais, params);
			if (contador != null) {
				if (contador.intValue() > 0) 
					procesoEDUComercialService.updateCronogramaDictado(codigoPais, params);
				else
					procesoEDUComercialService.insertCronogramaDictado(codigoPais, params);
			}
		}
	}

	/**
	 * Seteando Bean de Cronograma Dictado Zona
	 * @param cronogramaDictado
	 * @param cronogramaDictadoZona
	 */
	private void setearCronogramaDictadoZona(CronogramaDictado cronogramaDictado, CronogramaDictadoZona cronogramaDictadoZona) {
		cronogramaDictadoZona.setOidCronograma(cronogramaDictado.getOidCronograma());
		cronogramaDictadoZona.setCodigoPais(cronogramaDictado.getCodigoPais());
		cronogramaDictadoZona.setCodigoEmpresa(cronogramaDictado.getCodigoEmpresa());
		cronogramaDictadoZona.setCodigoRegion(cronogramaDictado.getCodigoRegion());
		cronogramaDictadoZona.setEstadoRegistro(Constants.ACTIVO);
	}

	
	public EntidadGenerico getEntidadEstadoCapacitacion(EntidadGenerico entidadGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getEntidadEstadoCapacitacion(entidadGenerico);
	}

	public EntidadGenerico getEntidadFrecuenciaCali(EntidadGenerico entidadGenerico) {
		// TODO Auto-generated method stub
		return mantenimientoEDUGenericoDAO.getEntidadFrecuenciaCali(entidadGenerico);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getCronogramaDictadoZonaById(biz.belcorp.ssicc.edu.dao.model.CronogramaDictadoZona)
	 */
	public List getCronogramaDictadoZonaById(CronogramaDictadoZona cronogramaDictadoZona) {
		return mantenimientoEDUGenericoDAO.getCronogramaDictadoZonaById(cronogramaDictadoZona);
	}
	
	public List getCronogramaDictadoByZona(CronogramaDictado cronogramaDictado) {
		return mantenimientoEDUGenericoDAO.getCronogramaDictadoByZona(cronogramaDictado);
	}

	public List getLocales(Local local) {
		return mantenimientoEDUGenericoDAO.getLocales(local);
	}

	public void updateEstadoLocal(Local local, Usuario usuario) {
		 mantenimientoEDUGenericoDAO.updateEstadoLocal(local,usuario);
		
	}

	public void updateLocal(Local local, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateLocal(local,usuario);
	}

	public void insertLocal(Local local, Usuario usuario) {
		mantenimientoEDUGenericoDAO.insertLocal(local,usuario);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#executeGenerarReporteCronogramaDictado(java.util.Map)
	 */
	public void executeGenerarReporteCronogramaDictado(Map criteria) {
		mantenimientoEDUGenericoDAO.executeGenerarReporteCronogramaDictado(criteria);
	}

	public List getTipoEjecutivas() {
		return mantenimientoEDUGenericoDAO.getTipoEjecutivas();
	}

	public String getSiguienteCodigoLocal(Local local) {
		return mantenimientoEDUGenericoDAO.getSiguienteCodigoLocal(local);
	}

	public String getSiguienteCodigoSala(Sala sala) {
		return mantenimientoEDUGenericoDAO.getSiguienteCodigoSala(sala);
	}

	public List getSalas(Sala sala) {
		return mantenimientoEDUGenericoDAO.getSalas(sala);
	}

	public void deleteSala(Sala sala) {
		mantenimientoEDUGenericoDAO.deleteSala(sala);
		
	}

	public void insertSala(Sala sala, Usuario usuario) {
		mantenimientoEDUGenericoDAO.insertSala(sala,usuario);
		
	}

	public void updateSala(Sala sala, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateSala(sala,usuario);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte)
	 */
	public ParametroReporte getParametroReporte(ParametroReporte parametro) {
		return mantenimientoEDUGenericoDAO.getParametroReporte(parametro);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#getParametroReporteLista(biz.belcorp.ssicc.edu.dao.model.ParametroReporte)
	 */
	public List getParametroReporteLista(ParametroReporte parametro) {
		return mantenimientoEDUGenericoDAO.getParametroReporteLista(parametro);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#updateParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroReporte(ParametroReporte parametro, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateParametroReporte(parametro, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#insertParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroReporte(ParametroReporte parametro, Usuario usuario) {
		mantenimientoEDUGenericoDAO.insertParametroReporte(parametro, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUGenericoService#deleteParametroReporte(biz.belcorp.ssicc.edu.dao.model.ParametroReporte, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteParametroReporte(ParametroReporte parametro, Usuario usuario) {
		mantenimientoEDUGenericoDAO.deleteParametroReporte(parametro, usuario);
	}

	public void executeGenerarResumenProgramadasPlanilla(Map params) {
		mantenimientoEDUGenericoDAO.executeGenerarResumenProgramadasPlanilla(params);
		
	}

	

	public List getLocalByCriteria(Map map) {
		return mantenimientoEDUGenericoDAO.getLocalByCriteria(map);
	}

	public List getIndicadorSeleccion() {
		return mantenimientoEDUGenericoDAO.getIndicadorSeleccion();
		
	}

	public List getLocalesByInstructora(Map params) {
		return mantenimientoEDUGenericoDAO.getLocalesByInstructora(params);
	}

	public String getMensajeEducacionEquiv(MensajeEducacion mensaje) {
		return mantenimientoEDUGenericoDAO.getMensajeEducacionEquiv(mensaje);
	}

	public void updateMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateMensajeEducacionEquiv(mensaje,usuario);
		
	}

	public void insertMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario) {
		mantenimientoEDUGenericoDAO.insertMensajeEducacionEquiv(mensaje,usuario);
		
	}
	
	public void updateEstadoMensajeEducacionEquiv(MensajeEducacion mensaje, Usuario usuario) {
		mantenimientoEDUGenericoDAO.updateEstadoMensajeEducacionEquiv(mensaje,usuario);
		
	}

	public String getDevuelveSiguienteNivel(Map criteria) {
		return mantenimientoEDUGenericoDAO.getDevuelveSiguienteNivel(criteria); 
		
	}

	public ConexionExterna getConexionExternaByCriteria(Map criteria){
		return  mantenimientoEDUGenericoDAO.getConexionExternaByCriteria(criteria);
	}
	
	public List getSubReporteCronogramaLocalWs(Map criteria) {
		return mantenimientoEDUGenericoDAO.getSubReporteCronogramaLocalWs(criteria);
	}

	public List getSubReporteCronogramaCursoWs(Map criteria) {
		return mantenimientoEDUGenericoDAO.getSubReporteCronogramaCursoWs(criteria);
	}

	public List getReporteCronogramaDictadoWs(Map criteria) {
		return mantenimientoEDUGenericoDAO.getReporteCronogramaDictadoWs(criteria);
	}
	
	public List getListaDocumentos(Map criteria){
		return  mantenimientoEDUGenericoDAO.getListaDocumentos(criteria);
	}
	
	public List getCronogramaDictadoProgramadoByZona(CronogramaDictado cronogramaDictado) {
		return mantenimientoEDUGenericoDAO.getCronogramaDictadoProgramadoByZona(cronogramaDictado);
	}
	
	public List getMatrizClasificacionByCriteria(Map criteria){		
		return  mantenimientoEDUGenericoDAO.getMatrizClasificacionByCriteria(criteria);
	}
		
	public List getCampanhasActivar(Map criteria){		
		return  mantenimientoEDUGenericoDAO.getCampanhasActivar(criteria);
	}
		
	public List getCampanhasDesactivar(Map criteria){		
		return  mantenimientoEDUGenericoDAO.getCampanhasDesactivar(criteria);
	}
	
	public List getCampanhasTodas(Map criteria){		
		return  mantenimientoEDUGenericoDAO.getCampanhasTodas(criteria);
	}
	
	public void insertClasificacion(Map criteria, Usuario usuario) {
		
		String[] listaForm = (String[])criteria.get("listaCampanhas");
		List listaNuevos = new ArrayList();
		
		criteria.put("usuario",usuario.getLogin());
		
		// Primero desactivo todos
		mantenimientoEDUGenericoDAO.desactivarClasificaciones(criteria,usuario);
		log.debug("desactivando todos ");
		// Obtengo lista de los que deben ser activados
		List listaActivar = mantenimientoEDUGenericoDAO.getCampanhasActivar(criteria);
		
		String[] listaAct = null;
		if(listaActivar.size()!=0){
			listaAct = new String[listaActivar.size()];
			for (int i = 0; i < listaActivar.size(); i++) {
				listaAct[i]=((Base)listaActivar.get(i)).getDescripcion();
			}		
		}
		// Si hay para activar, las activo en BD
		
		if(listaAct != null){
			criteria.put("listaCampanhas",listaAct);
			mantenimientoEDUGenericoDAO.activarClasificaciones(criteria,usuario);
		}
		
		//Obtengo las que son nuevas
		List listaTodas = mantenimientoEDUGenericoDAO.getCampanhasTodas(criteria);
		
		if(listaForm!=null){
		for (int i = 0; i < listaForm.length; i++) {
			String campForm = listaForm[i];
			boolean flag = false;
			for (int j = 0; j < listaTodas.size(); j++) {
				Base aux = (Base)listaTodas.get(j);
				if(aux.getDescripcion().equals(campForm)){
					flag = true;
					break;
				}
			}
			if(!flag)
				listaNuevos.add(campForm);
		}
		}
		for (int i = 0; i < listaNuevos.size(); i++) {
			criteria.put("campanaProceso",(String)listaNuevos.get(i));
			mantenimientoEDUGenericoDAO.insertNuevaClasificacion(criteria,usuario);
		}
	}
	/**
	 * Retorna el listado de cronograms pro zona
	 * @author sbuchelli
	 *  @param map
	 * */
	public List getZonasCronogramaByRegion(Map map) {
			return mantenimientoEDUGenericoDAO.getZonasCronogramaByRegion(map);
	}

	/**
	 * @author sbuchelli
	 * Retorna el listado de clasificaciones 
	 * */
	public List getListClasificaciones(Map map) {
		return mantenimientoEDUGenericoDAO.getListClasificaciones(map);
	}

}
