/*
 * Created on 09/10/2006 04:27:06 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarRegionesServiceImpl
 */
package biz.belcorp.ssicc.service.spusicc.sicc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sicc.MantenimientoSICCDAO;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.OpcionesSICC;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.RolSICC;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.UsuarioSICC;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sicc.MantenimientoSICCService;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarCapacitacionProgramadaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 */
@Service("sicc.mantenimientoSICCService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSICCServiceImpl extends BaseService implements MantenimientoSICCService	 {
	
	@Resource(name="sicc.mantenimientoSICCDAO")
	private MantenimientoSICCDAO mantenimientoSICCDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sicc.MantenimientoSICCService#getListaRolSICCByCriteria(java.util.Map)
	 */
	public List getListaRolSICCByCriteria(Map criteria) {
		return mantenimientoSICCDAO.getListaRolSICCByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getRolSICCByCriteria(java.util.Map)
	 */
	public RolSICC getRolSICCByCriteria(Map criteria) {
		return mantenimientoSICCDAO.getRolSICCByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#insertRolSICCByCriteria(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRolSICCByCriteria(Map criteria, Usuario usuario) {
		mantenimientoSICCDAO.insertRolSICCByCriteria(criteria, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#updateRolSICCByCriteria(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRolSICCByCriteria(Map criteria, Usuario usuario) {
		mantenimientoSICCDAO.updateRolSICCByCriteria(criteria, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#deleteRolSICCByCriteria(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteRolSICCByCriteria(Map criteria, Usuario usuario) {
		mantenimientoSICCDAO.deleteRolSICCByCriteria(criteria, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getListaOpcionesSICCByCriteria(java.util.Map)
	 */
	public List getListaOpcionesSICCByCriteria(Map criteria) {
		return mantenimientoSICCDAO.getListaOpcionesSICCByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getListaOpcionesRolSICCByCriteria(java.util.Map)
	 */
	public List getListaOpcionesRolSICCByCriteria(Map criteria) {
		return mantenimientoSICCDAO.getListaOpcionesRolSICCByCriteria(criteria);
	}
	

	
	/**
	 * @return the mantenimientoSICCDAO
	 */
	public MantenimientoSICCDAO getMantenimientoSICCDAO() {
		return mantenimientoSICCDAO;
	}


	/**
	 * @param mantenimientoSICCDAO the mantenimientoSICCDAO to set
	 */
	public void setMantenimientoSICCDAO(MantenimientoSICCDAO mantenimientoSICCDAO) {
		this.mantenimientoSICCDAO = mantenimientoSICCDAO;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getListaSICCUsuarioByCriteria(java.util.Map)
	 */
	public List getListaSICCUsuarioByCriteria(Map criteria) {
		return mantenimientoSICCDAO.getListaSICCUsuarioByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getListaSICCRolUsuarioByCriteria(java.util.Map)
	 */
	public List getListaSICCRolUsuarioByCriteria(Map criteria) {
		return mantenimientoSICCDAO.getListaSICCRolUsuarioByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#deleteSICCUsuario(java.util.Map)
	 */
	public void deleteSICCUsuario(Map criteria) {
		mantenimientoSICCDAO.deleteSICCUsuario(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#insertSICCUsuario(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC, java.util.List)
	 */
	public void insertSICCUsuario(UsuarioSICC usuario, List lista) {
		mantenimientoSICCDAO.insertSICCUsuario(usuario);
		mantenimientoSICCDAO.insertSICCRolUsuario(usuario, lista);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#updateSICCUsuario(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC, java.util.List)
	 */
	public void updateSICCUsuario(UsuarioSICC usuario, List lista) {
		mantenimientoSICCDAO.updateSICCUsuario(usuario);
		mantenimientoSICCDAO.deleteSICCUsuarioRoles(usuario);
		mantenimientoSICCDAO.insertSICCRolUsuario(usuario, lista);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#updateSICCUsuarioDatosMasivos(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC)
	 */
	public void updateSICCUsuarioDatosMasivos(UsuarioSICC usuario) {
		mantenimientoSICCDAO.updateSICCUsuarioDatosMasivos(usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#updateSICCUsuarioResetClave(biz.belcorp.ssicc.spusicc.sicc.model.UsuarioSICC)
	 */
	public void updateSICCUsuarioResetClave(UsuarioSICC usuario) {
		mantenimientoSICCDAO.updateSICCUsuarioResetClave(usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getListaOpcionesRolPermisoSICCByCriteria(java.util.Map)
	 */
	public List getListaOpcionesRolPermisoSICCByCriteria(Map criteria) {
		log.info("Entro a MantenimientoSICCServiceImpl - getListaOpcionesRolPermisoSICCByCriteria(Map)");

		//-- Variables
		List lista=this.getListaOpcionesRolSICCByCriteria(criteria);
		List l_final=new ArrayList();
		OpcionesSICC bean=null;
		
		//-- Logica
		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			bean = (OpcionesSICC) iterator.next();
			if(Integer.valueOf(bean.getValorAcceso())==1)
				l_final.add(bean);
		}
		
		log.info("Salio a MantenimientoSICCServiceImpl - getListaOpcionesRolPermisoSICCByCriteria(Map) - Resultado:"+l_final.size());
		return l_final;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getListaSICCUsuariosRolByCriteria(java.util.Map)
	 */
	public List getListaSICCUsuariosRolByCriteria(Map criteria) {
		log.info("Entro a MantenimientoSICCServiceImpl - getListaSICCUsuariosRolByCriteria(Map)");
		List lista=mantenimientoSICCDAO.getListaSICCUsuariosRolByCriteria(criteria);
		log.info("Salio a MantenimientoSICCServiceImpl - getListaSICCUsuariosRolByCriteria(Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#updateSICCUsuarioBlocked(java.util.Map)
	 */
	public void updateSICCUsuarioBlocked(Map criteria) {
		log.info("Entro a MantenimientoSICCServiceImpl - updateSICCUsuarioBlocked(Map)");
		mantenimientoSICCDAO.updateSICCUsuarioBlocked(criteria);
		log.info("Salio a MantenimientoSICCServiceImpl - updateSICCUsuarioBlocked(Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sicc.service.MantenimientoSICCService#getListaSICCRolUsuarioPermisosByCriteria(java.util.Map)
	 */
	public List getListaSICCRolUsuarioPermisosByCriteria(Map criteria) {
		log.info("Entro a MantenimientoSICCServiceImpl - getListaSICCRolUsuarioPermisosByCriteria(Map)");
		
		//-- Variables
		List lista=this.getListaSICCRolUsuarioByCriteria(criteria);
		List l_final=new ArrayList();
		HashMap bean=null;
		
		//-- Logica
		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			bean = (HashMap) iterator.next();
			if(Integer.valueOf(bean.get("indPerfilAsignado").toString())==1)
				l_final.add(bean);
		}
		
		log.info("Salio a MantenimientoSICCServiceImpl - getListaSICCRolUsuarioPermisosByCriteria(Map) - Resultado:"+l_final.size());
		return l_final;
	}
	        
}
