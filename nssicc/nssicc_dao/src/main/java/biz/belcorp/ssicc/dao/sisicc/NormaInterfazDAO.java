/*
 * Created on 06-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfazPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="NormaInterfazDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface NormaInterfazDAO extends DAO {

	/**
	 * Obtiene los datos de la norma que se aplica a una interfaz.
	 * 
	 * @param pk
	 * Llave primaria de la norma.
	 * 
	 * @return
	 * Objeto de tipo NormaInterfaz, poblado.
	 */
	public NormaInterfaz getNormaInterfaz(NormaInterfazPK pk);
	
	/**
	 * Obtiene los datos de la norma que se aplica a una interfaz, en base a un criterio.
	 * 
	 * @param criteria
	 * Criterio de busqueda.
	 * 
	 * @return
	 * Objeto de tipo NormaInterfaz, poblado.
	 */
	public NormaInterfaz getNormaInterfazByCriteria(NormaInterfaz criteria);
	
	/**
	 * Obtiene todas las normas disponibles, en base a un criterio.
	 * 
	 * @param criteria
	 * Criterio de busqueda.
	 * 
	 * @return
	 * Lista de objetos de tipo NormaInterfaz, poblados.
	 */
	public List getNormasByCriteria(Map criteria);

	/**
	 * Inserta una nueva Norma.
	 * 
	 * @param norma
	 * Norma a insertar.
	 * 
	 * @param usuario
	 * Usuario quien hace la invocacion.
	 */
	public void insertNormaInterfaz(NormaInterfaz norma, Usuario usuario);
	
	/**
	 * Actualiza una norma existente.
	 * 
	 * @param norma
	 * Norma a insertar.
	 * 
	 * @param usuario
	 * Usuario quien hace la invocacion.
	 */
	public void updateNormaInterfaz(NormaInterfaz norma, Usuario usuario);
	
	/**
	 * Elimina una norma de la base de datos, en base a su llave primaria.
	 *   
	 * @param primaryKey
	 * Llave primaria de la norma.
	 */
	public void removeNormaInterfaz(NormaInterfazPK primaryKey);
	
	/**
	 * Obtiene un codigo para una norma, en base a una llave primaria.
	 * 
	 * @param primaryKey
	 * Llave primaria
	 * 
	 * @return
	 * Siguiente codigo.
	 */
	public String getSiguienteCodigo(NormaInterfazPK primaryKey);
}
