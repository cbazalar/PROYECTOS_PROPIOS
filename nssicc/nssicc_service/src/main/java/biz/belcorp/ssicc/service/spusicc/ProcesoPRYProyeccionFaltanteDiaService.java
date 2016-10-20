/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.proyeccion.model.PorcentajeFaltante;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoPRYProyeccionFaltanteServiceDia"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public interface ProcesoPRYProyeccionFaltanteDiaService extends Service {

	public void executeProyeccionFaltante(Map criteria);
	
	public Integer existeIndicadorValorCritico(Map criteria);
	
	public List getProyeccionFaltanteGrupo(Map criteria);
	
	public List getProyeccionFaltanteProducto(Map criteria); 
	
	public List getProyeccionFaltanteProductoTodos(Map criteria);
	
	public String getPeriodoActual(Map criteria);
	
	public Integer getTotalOC(Map criteria);

	public String getMaximoVersionProyeccionFaltanteDia(Map criteria);

	/**
	 * Obtiene registros de la Entidad [Porcentaje Faltante]
	 * 
	 * @param porcentaje
	 * @return
	 */
	public List getPorcentajeFaltante(PorcentajeFaltante porcentaje);
	
	/**
	 * Actualiza un registro de la Entidad [Porcentaje Faltante]
	 * 
	 * @param porcentaje
	 * @param usuario
	 */
	public void updatePorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario);
	
	public void insertPorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario);
	
	public void deletePorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario);
	
	public String getDevuelveDescripcionPeriodo(Map criteria);
	
}