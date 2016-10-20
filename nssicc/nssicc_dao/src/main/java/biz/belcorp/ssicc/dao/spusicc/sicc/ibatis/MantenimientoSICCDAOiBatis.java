/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sicc.MantenimientoSICCDAO;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.RolSICC;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.UsuarioSICC;


/**
 * 
 * <p>
 * <a href="MantenimientoSICCDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Carlos Bazalar</a>
 * 
 */

@Repository("sicc.mantenimientoSICCDAO")
public class MantenimientoSICCDAOiBatis extends BaseDAOiBatis implements MantenimientoSICCDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sicc.dao.MantenimientoSICCDAO#getListaRolSICCByCriteria(java.util.Map)
	 */
	public List getListaRolSICCByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sicc.MantenimientoSICCSQL.getListaRolSICCByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#getRolSICCByCriteria(java.util.Map)
	 */
	public RolSICC getRolSICCByCriteria(Map criteria) {
		return (RolSICC)getSqlMapClientTemplate().queryForObject(
				"sicc.MantenimientoSICCSQL.getRolSICCByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#insertRolSICCByCriteria(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRolSICCByCriteria(Map criteria, Usuario usuario) {
		Long sequencial = (Long)getSqlMapClientTemplate().queryForObject(
				"sicc.MantenimientoSICCSQL.getSecuenciaRolSICCByCriteria", criteria);
		criteria.put("oid", sequencial);
		getSqlMapClientTemplate().insert("sicc.MantenimientoSICCSQL.insertRolSICC", criteria);
		getSqlMapClientTemplate().insert("sicc.MantenimientoSICCSQL.insertRolMemberSICC", criteria);
		List listaIndicadorOpcion = (List)criteria.get("listaIndicadorOpcion");
		List listaAccesoOpcionOid = (List)criteria.get("listaAccesoOpcionOid");
		
		/* Insertando Opciones del Rol */
		for(int i = 0; i < listaIndicadorOpcion.size(); i++) {
			String estado = (String)listaIndicadorOpcion.get(i);
			Long oidOpcion = (Long) listaAccesoOpcionOid.get(i);
            if(Constants.ESTADO_ACTIVO.equals(estado)) {
            	criteria.put("oidOpcion", oidOpcion.toString().trim());
            	getSqlMapClientTemplate().insert("sicc.MantenimientoSICCSQL.insertOpcionRolSICC", criteria);
            }
        }
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#updateRolSICCByCriteria(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRolSICCByCriteria(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("sicc.MantenimientoSICCSQL.updateRolSICC", criteria);
		List listaIndicadorOpcion = (List)criteria.get("listaIndicadorOpcion");
		List listaAccesoOpcionOid = (List)criteria.get("listaAccesoOpcionOid");
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteOpcionRolSICC", criteria);
		
		/* Insertando Opciones del Rol */
        for(int i = 0; i < listaIndicadorOpcion.size(); i++) {
        	String estado = (String)listaIndicadorOpcion.get(i);
        	Long oidOpcion = (Long) listaAccesoOpcionOid.get(i);
            if(Constants.ESTADO_ACTIVO.equals(estado)) {
            	criteria.put("oidOpcion", oidOpcion.toString().trim());
            	getSqlMapClientTemplate().insert("sicc.MantenimientoSICCSQL.insertOpcionRolSICC", criteria);
            }
        }
	}
	
	public void deleteRolSICCByCriteria(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteOpcionRolSICC", criteria);
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteRolMemberSICC", criteria);
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteRolSICC", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#getListaOpcionesSICCByCriteria(java.util.Map)
	 */
	public List getListaOpcionesSICCByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sicc.MantenimientoSICCSQL.getListaOpcionesSICCByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#getListaOpcionesRolSICCByCriteria(java.util.Map)
	 */
	public List getListaOpcionesRolSICCByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sicc.MantenimientoSICCSQL.getListaOpcionesRolSICCByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#getListaSICCUsuarioByCriteria(java.util.Map)
	 */
	public List getListaSICCUsuarioByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sicc.MantenimientoSICCSQL.getListaSICCUsuarioByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#getListaSICCRolUsuarioByCriteria(java.util.Map)
	 */
	public List getListaSICCRolUsuarioByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sicc.MantenimientoSICCSQL.getListaSICCRolUsuarioByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#deleteSICCUsuario(java.util.Map)
	 */
	public void deleteSICCUsuario(Map criteria) {
		
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioRoles", criteria);
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioDatosSecundarios", criteria);
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioBloqueado", criteria);
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioRECAutor", criteria);
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioCreadenciales", criteria);
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioUsuarios", criteria);		 
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioDatosPrincipales", criteria);
		 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#deleteSICCUsuario(java.util.Map)
	 */
	public void deleteSICCUsuarioRoles(UsuarioSICC usuario) {
		Map criteria = new HashMap();
		criteria.put("oid", usuario.getOid());
		 getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.deleteSICCUsuarioRoles", criteria);
		 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#insertSICCUsuario(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC)
	 */
	public void insertSICCUsuario(UsuarioSICC usuario) {
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.insertSICCUsuario", usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#updateSICCUsuario(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC)
	 */
	public void updateSICCUsuario(UsuarioSICC usuario) {
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.updateSICCUsuario", usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#insertSICCRolUsuario(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC, java.util.List)
	 */
	public void insertSICCRolUsuario(UsuarioSICC usuario, List lista) {
		
		Map criteria;
		String login = usuario.getLogin();
		
		/* Insertando Opciones del Rol */
        for(int i = 0; i < lista.size(); i++) {
        	criteria = (Map)lista.get(i);
           	criteria.put("login", login);
           	getSqlMapClientTemplate().insert("sicc.MantenimientoSICCSQL.insertSICCRolUsuario", criteria);
           
        }
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#updateSICCUsuarioDatosMasivos(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC)
	 */
	public void updateSICCUsuarioDatosMasivos(UsuarioSICC usuario) {
		
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.updateSICCUsuarioDatosMasivos", usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#updateSICCUsuarioResetClave(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC)
	 */
	public void updateSICCUsuarioResetClave(UsuarioSICC usuario) {
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.updateSICCUsuarioResetClave", usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#getListaSICCUsuariosRolByCriteria(java.util.Map)
	 */
	public List getListaSICCUsuariosRolByCriteria(Map criteria) {
		log.info("Entro a MantenimientoSICCDAOiBatis - getListaSICCUsuariosRolByCriteria(Map)");
		List lista=getSqlMapClientTemplate().queryForList("sicc.MantenimientoSICCSQL.getListaSICCUsuariosRolByCriteria", criteria);
		log.info("Salio a MantenimientoSICCDAOiBatis - getListaSICCUsuariosRolByCriteria(Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.dao.MantenimientoSICCDAO#updateSICCUsuarioBlocked(java.util.Map)
	 */
	public void updateSICCUsuarioBlocked(Map criteria) {
		log.info("Entro a MantenimientoSICCDAOiBatis - updateSICCUsuarioBlocked(Map)");
		getSqlMapClientTemplate().delete("sicc.MantenimientoSICCSQL.updateSICCUsuarioBlocked", criteria);
		log.info("Salio a MantenimientoSICCDAOiBatis - updateSICCUsuarioBlocked(Map)");
	}
	
}