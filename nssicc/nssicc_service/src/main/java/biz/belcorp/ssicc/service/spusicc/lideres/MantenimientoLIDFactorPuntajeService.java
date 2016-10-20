package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.lideres.model.FactorPuntaje;
import biz.belcorp.ssicc.service.framework.Service;



public interface MantenimientoLIDFactorPuntajeService  extends Service{

	/**
	 * Devuelve la lista de factor puntaje
	 * @param criteria
	 * @return
	 */
	public List getFactorPuntajeList(Map criteria);

	/**
	 * Retorna la lista de Tipo Asignacion
	 * @return
	 */
	public List getTipoAsignacionPuntajeList();

	/**
	 * Actualiza Factor Puntaje
	 * @param factorPuntaje
	 */
	public void updateFactorPuntaje(FactorPuntaje factorPuntaje);

	/**
	 * Inserta Salvar Puntaje
	 * @param factorPuntaje
	 */
	public void saveFactorPuntaje(FactorPuntaje factorPuntaje);

	

}
