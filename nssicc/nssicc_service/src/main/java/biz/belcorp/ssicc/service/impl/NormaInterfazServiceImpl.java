/*
 * Created on 06-dic-2005
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
import biz.belcorp.ssicc.dao.sisicc.NormaInterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfazPK;
import biz.belcorp.ssicc.service.NormaInterfazService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="NormaInterfazServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.normaInterfazService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class NormaInterfazServiceImpl extends BaseService implements NormaInterfazService {

	@Resource(name="sisicc.normaInterfazDAO")
	private NormaInterfazDAO normaInterfazDAO;
	
	/* 
	 * @see biz.belcorp.ssicc.service.NormaInterfazService#getNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfazPK)
	 */
	public NormaInterfaz getNormaInterfaz(NormaInterfazPK pk) {
		// TODO Auto-generated method stub
		return this.normaInterfazDAO.getNormaInterfaz(pk);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.NormaInterfazService#getNormaInterfazByCriteria(biz.belcorp.ssicc.model.NormaInterfaz)
	 */
	public NormaInterfaz getNormaInterfazByCriteria(NormaInterfaz criteria) {
		// TODO Auto-generated method stub
		return this.normaInterfazDAO.getNormaInterfazByCriteria(criteria);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.NormaInterfazService#getNormasByCriteria(java.util.Map)
	 */
	public List getNormasByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		return this.normaInterfazDAO.getNormasByCriteria(criteria);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.NormaInterfazService#insertNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNormaInterfaz(NormaInterfaz norma, Usuario usuario) {
		try{
			// Verificamos que no exista una norma con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion ObjectRetrievalFailureException
			norma.setCodigo(this.getSiguienteCodigo(new NormaInterfazPK(norma.getCodigoPais(), norma.getCodigo())));
			this.normaInterfazDAO.getNormaInterfaz(new NormaInterfazPK(norma.getCodigoPais(), norma.getCodigo()));
			
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(NormaInterfaz.class, norma.getCodigo());
			
		}
		catch(ObjectRetrievalFailureException orfe){
			// Seteamos los valores por defecto
			norma.setEstado(Constants.ESTADO_ACTIVO);
			
			// Insertamos la nueva norma
			this.normaInterfazDAO.insertNormaInterfaz(norma, usuario);
		}
	}
	/* 
	 * @see biz.belcorp.ssicc.service.NormaInterfazService#removeNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfazPK, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeNormaInterfaz(NormaInterfazPK primaryKey, Usuario usuario) {
        // Actualizamos el estado de la norma
        try {
            NormaInterfaz norma = this.normaInterfazDAO.getNormaInterfaz(primaryKey);
            norma.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos la norma
            this.normaInterfazDAO.updateNormaInterfaz(norma, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }
	}
	/* 
	 * @see biz.belcorp.ssicc.service.NormaInterfazService#updateNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNormaInterfaz(NormaInterfaz norma, Usuario usuario) {
		this.normaInterfazDAO.updateNormaInterfaz(norma, usuario);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.NormaInterfazService#getSiguienteCodigo(biz.belcorp.ssicc.model.NormaInterfazPK)
	 */
	public String getSiguienteCodigo(NormaInterfazPK primaryKey) {
		return this.normaInterfazDAO.getSiguienteCodigo(primaryKey);
	}
}