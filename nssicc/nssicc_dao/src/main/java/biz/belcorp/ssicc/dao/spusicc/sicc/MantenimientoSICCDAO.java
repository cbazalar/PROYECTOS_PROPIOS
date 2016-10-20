package biz.belcorp.ssicc.dao.spusicc.sicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.RolSICC;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.UsuarioSICC;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ReporteDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Carlos Bazalar</a>
 * 
 */

public interface MantenimientoSICCDAO extends DAO {

	/**
	 * Devuelve lista de Roles SICC en base al map enviado
	 * @param criteria
	 * @return
	 */
	public List getListaRolSICCByCriteria(Map criteria);
	
	/**
	 * Obtiene Rol SSiCC en base al parametro ingresado
	 * @param criteria
	 * @return
	 */
	public RolSICC getRolSICCByCriteria(Map criteria);
	
	/**
	 * Inserta Rol SICC
	 * @param criteria
	 */
	public void insertRolSICCByCriteria(Map criteria, Usuario usuario);
	
	
	/**
	 * Actualiza Rol SICC
	 * @param criteria
	 * @param usuario
	 */
	public void updateRolSICCByCriteria(Map criteria, Usuario usuario);
	
	
	/**
	 * Elimina Rol SICC
	 * @param criteria
	 * @param usuario
	 */
	public void deleteRolSICCByCriteria(Map criteria, Usuario usuario);
	
	/**
	 * Devuelve lista de Opciones SICC en base al map enviado
	 * @param criteria
	 * @return
	 */
	public List getListaOpcionesSICCByCriteria(Map criteria);
	
	/**
	 * Devuelve lista de Opciones SICC en base al Rol respectivo
	 * @param criteria
	 * @return
	 */
	public List getListaOpcionesRolSICCByCriteria(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getListaSICCUsuarioByCriteria(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getListaSICCRolUsuarioByCriteria(Map criteria);

	/**
	 * @param criteria
	 */
	public void deleteSICCUsuario(Map criteria);

	/**
	 * @param criteria
	 */
	public void deleteSICCUsuarioRoles(UsuarioSICC usuario);
	/**
	 * @param usuario
	 */
	public void insertSICCUsuario(UsuarioSICC usuario);

	/**
	 * @param usuario
	 */
	public void updateSICCUsuario(UsuarioSICC usuario);
	
	/**
	 * @param usuario
	 */
	public void insertSICCRolUsuario(UsuarioSICC usuario, List lista);

	/**
	 * @param usuario
	 */
	public void updateSICCUsuarioDatosMasivos(UsuarioSICC usuario);

	/**
	 * @param usuario
	 */
	public void updateSICCUsuarioResetClave(UsuarioSICC usuario);
	
	/**
	 * Devuelva una lista de los usuarios que contiene un Perfil seleccionado
	 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
	 * @param criteria
	 * @return
	 */
	public List getListaSICCUsuariosRolByCriteria(Map criteria);

	/**
	 * Actualiza el bloqueo / desbloqueo de un usuario en especifico
	 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
	 * @param criteria
	 */
	public void updateSICCUsuarioBlocked(Map criteria);

}