/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="GenerarLiderDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jamartinez@belcorp.biz">Jose Martinez</a>
 * 
 */

public interface GenerarLiderDAO extends DAO {

	/**
	 * Obtiene un listado de todas los lideres generados filtradas por un map en
	 * el que se setean los criterios de busqueda.
	 * 
	 * @param criteria
	 *            Objeto Map cuyos atributos son usados como criterios de
	 *            busqueda.
	 * 
	 * @return Lista de objetos poblados.
	 */
	public List getLideresGeneradosByCriteria(Map criteria);

	/**
	 * Registra la informacin de los nuevos lideres identificados.
	 * 
	 * @param criteria
	 *            Map conteniendo informacion que servira para filtrar.
	 * 
	 * @param usuario
	 *            Usuario que esta insertando las lideres identificadas.
	 */
	public void insertLideresGenerados(Map criteria, Usuario usuario);

}
