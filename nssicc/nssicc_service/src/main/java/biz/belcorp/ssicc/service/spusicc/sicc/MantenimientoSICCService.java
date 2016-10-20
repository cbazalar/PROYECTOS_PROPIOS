package biz.belcorp.ssicc.service.spusicc.sicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.RolSICC;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.UsuarioSICC;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author carlos bazalar
 *
 */

public interface MantenimientoSICCService extends Service {

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
	 * @param cabecera
	 * @param lista
	 */
	public void updateSICCUsuario(UsuarioSICC usuario, List lista);

	/**
	 * @param cabecera
	 * @param lista
	 */
	public void insertSICCUsuario(UsuarioSICC usuario, List lista);

	/**
	 * @param usuario
	 */
	public void updateSICCUsuarioResetClave(UsuarioSICC usuario);

	/**
	 * @param usuario
	 */
	public void updateSICCUsuarioDatosMasivos(UsuarioSICC usuario);
	
	/**
	 * Devuelve lista de Opciones SICC con Permiso en base al Rol respectivo
	 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
	 * @param criteria
	 * @return
	 */
	public List getListaOpcionesRolPermisoSICCByCriteria(Map criteria);
	
	/** Devuelva una lista de los usuarios que contiene un Perfil seleccionado
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
	/**
	 * Devuelve lista de Roles SICC con Permiso en base al Usuario respectivo
	 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
	 * @param criteria
	 * @return
	 */
	public List getListaSICCRolUsuarioPermisosByCriteria(Map criteria);
}
