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
import biz.belcorp.ssicc.dao.sisicc.BloqueoLiderDAO;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLider;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLiderPK;
import biz.belcorp.ssicc.service.BloqueoLiderService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BloqueoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.bloqueoLiderService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BloqueoLiderServiceImpl extends BaseService implements BloqueoLiderService {

	@Resource(name="sisicc.bloqueoLiderDAO")
	private BloqueoLiderDAO bloqueoLiderDAO;



	/**
	 * @see biz.belcorp.ssicc.service.BloqueoLiderService#getBloqueos(biz.belcorp.ssicc.model.Bloqueo)
	 */
	public List getBloqueoLiders(BloqueoLider BloqueoLider) {
		return this.bloqueoLiderDAO.getBloqueoLiders(BloqueoLider);
	}

	/** 
	 * @see biz.belcorp.ssicc.service.BloqueoLiderService#getBloqueoLidersByCriteria(java.util.Map)
	 */
	public List getBloqueoLidersByCriteria(Map criteria) {
		return this.bloqueoLiderDAO.getBloqueoLidersByCriteria(criteria);
	}
	
	/** 
	 * @see biz.belcorp.ssicc.service.BloqueoLiderService#getBloqueoLider(biz.belcorp.ssicc.model.BloqueoLiderPK)
	 */
	public BloqueoLider getBloqueoLider(BloqueoLiderPK primaryKey) {
		return this.bloqueoLiderDAO.getBloqueoLider(primaryKey);
	}

	/** 
	 * @see biz.belcorp.ssicc.service.BloqueoService#insertBloqueo(biz.belcorp.ssicc.model.Bloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertBloqueoLider(BloqueoLider bloqueoLider, Usuario usuario) {
		try{
			// Verificamos que no exista un Bloqueo con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion ObjectRetrievalFailureException			
			this.bloqueoLiderDAO.getBloqueoLider(new BloqueoLiderPK(bloqueoLider.getCodigoPais(), bloqueoLider.getCodigo()));
			
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(BloqueoLider.class, bloqueoLider.getCodigo());
		}
		catch(ObjectRetrievalFailureException orfe){
            /*
            // Verificamos que no exista un Bloqueo con la misma descripcin, para el mismo pais
            // Creamos el bean que nos servir como criterio de busqueda
			BloqueoLider criteria = new BloqueoLider();
			criteria.setCodigoPais(bloqueoLider.getCodigoPais());
			criteria.setDescripcion(bloqueoLider.getDescripcion());
			
			List Bloqueos = this.bloqueoLiderDAO.getBloqueos(criteria);
			
			if(Bloqueos != null && Bloqueos.size() > 0){
				throw new InvalidDescriptionException(Bloqueo.class, Bloqueo.getDescripcion());	
			}
			*/
			// Seteamos los valores por defecto
			bloqueoLider.setEstado(Constants.ESTADO_ACTIVO);
			
			// Insertamos el nuevo Bloqueo
			this.bloqueoLiderDAO.insertBloqueoLider(bloqueoLider, usuario);
		}
	}

	/** 
	 * @see biz.belcorp.ssicc.service.BloqueoService#updateBloqueo(biz.belcorp.ssicc.model.Bloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateBloqueoLider(BloqueoLider bloqueoLider, Usuario usuario) {
        // Verificamos que no exista un Bloqueo con la misma descripcin, para el mismo pais
        // Creamos el bean que nos servir como criterio de busqueda
        /*
        BloqueoLider criteria = new BloqueoLider();
        criteria.setCodigoPais(bloqueoLider.getCodigoPais());
        criteria.setDescripcion(bloqueoLider.getDescripcion());
        
        List Bloqueos = BloqueoDAO.getBloqueos(criteria);
        
        if (Bloqueos != null && Bloqueos.size() > 0) {
            for (int i = 0; i < Bloqueos.size(); i++) {
                BloqueoLider o = (BloqueoLider) Bloqueos.get(i);
                if (!(o.getCodigo().equals(Bloqueo.getCodigo()) && o.getCodigoPais().equals(Bloqueo.getCodigoPais()))) {
                    throw new InvalidDescriptionException(BloqueoLider.class, Bloqueo.getDescripcion());
                }
            }
        }
        */
        // Si no existe un Bloqueo con la misma descripcin realizamos la actualizacin
        this.bloqueoLiderDAO.updateBloqueoLider(bloqueoLider, usuario);		
	}

	/** 
	 * @see biz.belcorp.ssicc.service.BloqueoService#removeBloqueo(biz.belcorp.ssicc.model.BloqueoPK, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeBloqueoLider(BloqueoLiderPK primaryKey, Usuario usuario) {
        // Actualizamos el estado del Bloqueo
        try {
            BloqueoLider Bloqueo = this.bloqueoLiderDAO.getBloqueoLider(primaryKey);
            Bloqueo.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el Bloqueo
            this.bloqueoLiderDAO.updateBloqueoLider(Bloqueo, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }		
	}
}
