/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.SistemaDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.sisicc.model.SistemaPK;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="SistemaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

@Service("sisicc.sistemaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class SistemaServiceImpl extends BaseService implements SistemaService {

	@Resource(name="sisicc.sistemaDAO")
	private SistemaDAO sistemaDAO;
		
	
	/**
	 * @see biz.belcorp.ssicc.service.SistemaService#getSistemas(biz.belcorp.ssicc.dao.sisicc.model.model.Sistema)
	 */
	public List getSistemas(Sistema sistema) {
		return this.sistemaDAO.getSistemas(sistema);
	}

	/** 
	 * @see biz.belcorp.ssicc.service.SistemaService#getSistemasByCriteria(java.util.Map)
	 */
	public List getSistemasByCriteria(Map criteria) {
		return this.sistemaDAO.getSistemasByCriteria(criteria);
	}
	
	public List getSistemasByCriteria2(Map criteria) {
		return this.sistemaDAO.getSistemasByCriteria2(criteria);
	}
	
	
	/** 
	 * @see biz.belcorp.ssicc.service.SistemaService#getSistema(biz.belcorp.ssicc.dao.sisicc.model.model.SistemaPK)
	 */
	public Sistema getSistema(SistemaPK primaryKey) {
		return this.sistemaDAO.getSistema(primaryKey);
	}

	/** 
	 * @see biz.belcorp.ssicc.service.SistemaService#insertSistema(biz.belcorp.ssicc.dao.sisicc.model.model.Sistema, biz.belcorp.ssicc.dao.model.model.Usuario)
	 */
	public void insertSistema(Sistema sistema, Usuario usuario) {
		try{
			// Verificamos que no exista un sistema con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion ObjectRetrievalFailureException			
			this.sistemaDAO.getSistema(new SistemaPK(sistema.getCodigoPais(), sistema.getCodigo()));
			
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(Sistema.class, sistema.getCodigo());
		}
		catch(ObjectRetrievalFailureException orfe){

            // Verificamos que no exista un sistema con la misma descripcin, para el mismo pais
            // Creamos el bean que nos servir como criterio de busqueda
			Sistema criteria = new Sistema();
			criteria.setCodigoPais(sistema.getCodigoPais());
			criteria.setDescripcion(sistema.getDescripcion());
			
			List sistemas = this.sistemaDAO.getSistemas(criteria);
			
			if(sistemas != null && sistemas.size() > 0){
				throw new InvalidDescriptionException(Sistema.class, sistema.getDescripcion());	
			}
			
			// Seteamos los valores por defecto
			sistema.setEstado(Constants.ESTADO_ACTIVO);
			
			// Insertamos el nuevo sistema
			this.sistemaDAO.insertSistema(sistema, usuario);
		}
	}

	/** 
	 * @see biz.belcorp.ssicc.service.SistemaService#updateSistema(biz.belcorp.ssicc.dao.sisicc.model.model.Sistema, biz.belcorp.ssicc.dao.model.model.Usuario)
	 */
	public void updateSistema(Sistema sistema, Usuario usuario) {
        // Verificamos que no exista un sistema con la misma descripcin, para el mismo pais
        // Creamos el bean que nos servir como criterio de busqueda
        Sistema criteria = new Sistema();
        criteria.setCodigoPais(sistema.getCodigoPais());
        criteria.setDescripcion(sistema.getDescripcion());
        
        List sistemas = sistemaDAO.getSistemas(criteria);
        
        if (sistemas != null && sistemas.size() > 0) {
            for (int i = 0; i < sistemas.size(); i++) {
                Sistema o = (Sistema) sistemas.get(i);
                if (!(o.getCodigo().equals(sistema.getCodigo()) && o.getCodigoPais().equals(sistema.getCodigoPais()))) {
                    throw new InvalidDescriptionException(Sistema.class, sistema.getDescripcion());
                }
            }
        }

        // Si no existe un sistema con la misma descripcin realizamos la actualizacin
        this.sistemaDAO.updateSistema(sistema, usuario);		
	}

	/** 
	 * @see biz.belcorp.ssicc.service.SistemaService#removeSistema(biz.belcorp.ssicc.dao.sisicc.model.model.SistemaPK, biz.belcorp.ssicc.dao.model.model.Usuario)
	 */
	public void removeSistema(SistemaPK primaryKey, Usuario usuario) {
        // Actualizamos el estado del Sistema
        try {
            Sistema sistema = this.sistemaDAO.getSistema(primaryKey);
            sistema.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el sistema
            this.sistemaDAO.updateSistema(sistema, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }		
	}
}
