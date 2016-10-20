/*
 * Created on 08/11/2005 17:43:03 AM
 * biz.belcorp.ssicc.service.impl.RolServiceImpl
 */
package biz.belcorp.ssicc.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.AccesoDAO;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.RolDAO;
import biz.belcorp.ssicc.dao.model.Acceso;
import biz.belcorp.ssicc.dao.model.AccesoPK;
import biz.belcorp.ssicc.dao.model.OpcionMenuRol;
import biz.belcorp.ssicc.dao.model.Rol;
import biz.belcorp.ssicc.dao.model.RolPK;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.RolService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.util.StringUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RolServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("rolService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class RolServiceImpl extends BaseService implements RolService {

	@Resource(name="rolDAO")
    private RolDAO rolDAO;

	@Resource(name="accesoDAO")
    private AccesoDAO accesoDAO;

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.RolService#getRoles(biz.belcorp.ssicc.model.Rol)
     */
    public List getRoles(Rol rol) {
        return this.rolDAO.getRoles(rol);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.RolService#getRolesByCriteria(java.util.Map)
     */
    public List getRolesByCriteria(Map criteria) {
        return this.rolDAO.getRolesByCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.RolService#getRol(java.lang.String,java.lang.String,
     *      java.lang.String)
     */
    public Rol getRol(String codigoPais, String codigo, String codigoIdioma) {
        // Obtenemos el objeto rol de la base de datos
        Rol rol = this.rolDAO.getRol(new RolPK(codigoPais, codigo), codigoIdioma);

        // Obtenemos los accesos del rol
        List accesos = this.accesoDAO.getAccesosByRol(codigoPais, codigo, codigoIdioma);
        rol.setAccesos(accesos);

        return rol;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.RolService#insertRol(biz.belcorp.ssicc.model.Rol,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertRol(Rol rol, Usuario usuario) {
        // Seteamos los valores por defecto
        rol.setEstado(Constants.ESTADO_ACTIVO);
        this.rolDAO.insertRol(rol, usuario);

        // Insertamos los accesos seleccionados
        List accesosRol = rol.getAccesos();
        if (accesosRol != null) {
            Iterator i = accesosRol.iterator();
            while (i.hasNext()) {
                Acceso acceso = (Acceso) i.next();
                // Seteamos los valores por defecto
                acceso.setCodigoPais(rol.getCodigoPais());
                acceso.setCodigoRol(rol.getCodigo());
                this.accesoDAO.insertAcceso(acceso, usuario);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.RolService#updateRol(biz.belcorp.ssicc.model.Rol,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateRol(Rol rol, Usuario usuario) {
        // Actualizamos el rol...
        this.rolDAO.updateRol(rol, usuario);

        // Insertamos o acualizamos los accesos seleccionados
        List accesosRol = rol.getAccesos();
        if (accesosRol != null) {
            Iterator i = accesosRol.iterator();
            while (i.hasNext()) {
                Acceso acceso = (Acceso) i.next();
                // Seteamos los valores por defecto
                acceso.setCodigoPais(rol.getCodigoPais());
                acceso.setCodigoRol(rol.getCodigo());

                // Buscamos el acceso por la llave primaria
                try {
                    Acceso obj = this.accesoDAO.getAcceso(new AccesoPK(acceso.getCodigoMenu(), acceso.getCodigoPais(),
                            acceso.getCodigoRol()));
                    obj.setEstado(acceso.getEstado());
                    this.accesoDAO.updateAcceso(obj, usuario);
                }
                catch (ObjectRetrievalFailureException orfe) {
                    this.accesoDAO.insertAcceso(acceso, usuario);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.RolService#removeRol(java.lang.String,java.lang.String,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeRol(String codigoPais, String codigo, Usuario usuario) {
        // Actualizamos el estado del Rol
        try {
            Rol rol = this.rolDAO.getRol(new RolPK(codigoPais, codigo), usuario.getIdioma().getCodigo());
            rol.setEstado(Constants.ESTADO_INACTIVO);
            // Actualizamos el rol
            this.rolDAO.updateRol(rol, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            this.log.warn(orfe.getMessage());
        }

    }
    
    
    public List getOpcionMenuRolesByCriteria(OpcionMenuRol  opcionMenuRol){
    	return   this.rolDAO.getOpcionMenuRolesByCriteria(opcionMenuRol);
         
    
    	
    }
    
    public void insertOpcionMenuRoles(OpcionMenuRol  opcionMenuRol){
    	this.rolDAO.insertOpcionMenuRoles(opcionMenuRol);
    }
    
    public void removeOpcionMenuRoles(OpcionMenuRol  opcionMenuRol){
    	this.rolDAO.removeOpcionMenuRoles(opcionMenuRol);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.RolService#getAccesosAsignados(java.lang.String,
     *      java.lang.String)
     */
    public List getAccesosAsignados(String codigoRol, String codigoLenguaje) {
        return this.accesoDAO.getAccesosMap(codigoRol, codigoLenguaje);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.RolService#updateAccesosAsignados(java.util.List, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateAccesosAsignados(List accesos, Usuario usuario) {
    	HashMap map=null;
    	Acceso obj =null;
        try {
	    	if (accesos != null) {
        	List listaAcceso=(List)accesos.get(0);
	        	Acceso acceso = (Acceso) accesos.get(1);
	        	this.accesoDAO.removeOpcioMenuRol(new AccesoPK("",acceso.getCodigoPais() ,acceso.getCodigoRol()));
            Iterator i = listaAcceso.iterator();
            while (i.hasNext()) {
            	map= new HashMap();
            	map=(HashMap)i.next();
	        		        		        
            	String[] activos=map.get("activos")!=null?String.valueOf(map.get("activos")).split(","):null;
            	if(activos!=null)
            	{
            		for(int x=0;x<activos.length;x++)
	            	{            		
	        			obj= new Acceso();
            			obj.setCodigoMenu(String.valueOf(map.get("codigoMenu")));
	        			obj.setCodigoPais(acceso.getCodigoPais());
	        			obj.setCodigoRol(acceso.getCodigoRol());
            			obj.getMenu().setCodigoOpciones(String.valueOf(activos[x]));
	        			this.accesoDAO.insertOpcioMenuRol(obj, usuario);
	            	}
	    	    }
	        	}
        	}
            } catch(Exception e) {
                this.log.error(e.getMessage(), e);
                e.printStackTrace();
            }
    }	
            
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.RolService#getRolesActivos(biz.belcorp.ssicc.model.Rol)
     */
    public List getRolesActivos(Rol rol) {
        return this.rolDAO.getRolesActivos(rol);
    }
    
}
