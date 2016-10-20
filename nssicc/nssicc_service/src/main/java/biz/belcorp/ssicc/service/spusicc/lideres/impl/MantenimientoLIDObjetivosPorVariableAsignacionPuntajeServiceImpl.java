package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService;

/**
 * 
 * <p>
 * <a href="MantenimientoLIDObjetivosPorVariableAsignacionPuntajeServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:etovar@csigcomt.com">Eder Tovar
 *         </a>
 */
@Service("spusicc.mantenimientoLIDObjetivosPorVariableAsignacionPuntajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeServiceImpl extends BaseService 
	implements MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService
{

	@Resource(name="spusicc.mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO")
	private MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO;
	
	/**
	 * Metodo que obtiene la lista de tipos de asignacion.
	 * @return Lista de tipo de asignacion
	 */
	public List getTipoAsignacionPuntajeList() {
		return mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO.getTipoAsignacionList();
	}
	/**
	 * Metodo que obtiente la lista de los objetivos de asignacin puntaje que coinciden
	 * con los parametros de bsqueda
	 * @param criteria
	 * @return Lista de ObjetivosAsignacionPuntaje
	 */
	public List getObjetivoAsignacionPuntajeList(Map criteria){
		return mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO.getObjetivoAsignacionPuntajeList(criteria);
	}
	/**
	 * Registra un nuevo Objetivo de asginacion Puntaje
	 * @param criteria
	 */
	public boolean insertObjetivoAsignacionPuntaje(Map criteria){
		if ( periodoValido(criteria) ){
			mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO.insertObjetivoAsignacionPuntaje(criteria);
			return true;
		}
		return false;
	}
	/**
	 * Actualiza un Objetivo de asginacion Puntaje
	 * @param criteria
	 */
	public boolean udpateObjetivoAsignacionPuntaje(Map criteria){
		if ( periodoValido(criteria) ){
			mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO.udpateObjetivoAsignacionPuntaje(criteria);
			return true;
		}
		return false;
	}
	/**
	 * Mtodo que obtiene la cantidad de cruces que existe entre el periodo ingresado por parametro 
	 * y los rangos de periodos ya registrados en el sistema.
	 * @param criteria
	 */
	public Long getCantCrucesRangoPeriodo(Map criteria){
		return mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO.getCantCrucesRangoPeriodo(criteria);
	}
	/*
	 * Mtodo que comprueba si el rango de periodo ingresado es vlido.
	 * El criterio para comprobar que el periodo sea vlido es comprobar que el rango de periodo ingresado no se cruce
	 * con ninguno de los rangos de periodo que se encuentren registrados en la BD
	 * @return "True" si el periodo es vlido
	 */
	private boolean periodoValido(Map criteria){
		
		if ( getCantCrucesRangoPeriodo(criteria).compareTo(new Long(0)) > 0 ){
			return false;
		}
		return true;
	}
	/**
	 * Elimina un Objetivo de asginacion Puntaje
	 * @param criteria
	 */
	public boolean deleteObjetivoAsignacionPuntaje(Map criteria){
		return mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO.deleteObjetivoAsignacionPuntaje(criteria);
	}
	
	

	
}
