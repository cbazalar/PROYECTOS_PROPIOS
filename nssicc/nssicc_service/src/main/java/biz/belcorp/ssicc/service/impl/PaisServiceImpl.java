/*
 * Created on 15/03/2005 11:26:35 AM
 * biz.belcorp.ssicc.service.impl.PaisServiceImpl
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PaisServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("paisService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaisServiceImpl extends BaseService implements PaisService {
    
	@Resource(name="paisDAO")
	private PaisDAO paisDAO;

    
    /**
     * @see biz.belcorp.ssicc.service.PaisService#getPaises(biz.belcorp.ssicc.dao.model.model.Pais)
     */
    public List getPaises(final Pais pais) {
        return paisDAO.getPaises(pais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.PaisService#getPaisesByCriteria(java.util.Map)
     */
    public List getPaisesByCriteria(Map criteria) {
        return paisDAO.getPaisesByCriteria(criteria);
    }

    /**
     * @see biz.belcorp.ssicc.service.PaisService#getPais(java.lang.String)
     */
    public Pais getPais(final String codigo) {
        return paisDAO.getPais(codigo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.PaisService#getPaisByCodigoISO(java.lang.String)
     */
    public Pais getPaisByCodigoISO(String codigo) {
        if(StringUtils.isBlank(codigo)) {
            throw new IllegalArgumentException("Codigo ISO no puede ser nulo.");
        }
        return paisDAO.getPaisByCodigoISO(codigo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.PaisService#insertPais(biz.belcorp.ssicc.model.Pais,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertPais(Pais pais, Usuario usuario) {
        // Verificamos que no exista un pais con el mismo codigo
        try {
            paisDAO.getPais(pais.getCodigo());
            throw new InvalidIdentifierException(Pais.class, pais.getCodigo());
        }
        catch (ObjectRetrievalFailureException orfe) {
            // ignore
        }
        // Verificamos que no exista un pais con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
        Pais criteria = new Pais();
        criteria.setDescripcion(pais.getDescripcion());
        List paises = paisDAO.getPaises(criteria);
        if (paises != null && paises.size() > 0) {
            throw new InvalidDescriptionException(Pais.class, pais.getDescripcion());
        }
        // Seteamos los valores por defecto
        pais.setEstado(Constants.ESTADO_ACTIVO);
        paisDAO.insertPais(pais, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.PaisService#updatePais(biz.belcorp.ssicc.model.Pais,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updatePais(Pais pais, Usuario usuario) {
        // Verificamos que no exista un pais con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
        Pais criteria = new Pais();
        criteria.setDescripcion(pais.getDescripcion());
        List paises = paisDAO.getPaises(criteria);
        if (paises != null && paises.size() > 0) {
            for (int i = 0; i < paises.size(); i++) {
                Pais o = (Pais) paises.get(i);
                if (!o.getCodigo().equals(pais.getCodigo())) {
                    throw new InvalidDescriptionException(Pais.class, pais.getDescripcion());
                }
            }
        }

        paisDAO.updatePais(pais, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.PaisService#removePais(java.lang.String,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removePais(String codigo, Usuario usuario) {
        // Actualizamos el estado del Pais
        try {
            Pais pais = paisDAO.getPais(codigo);
            pais.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el pais
            paisDAO.updatePais(pais, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.PaisService#getParametrosSegmentacionByPais(java.lang.String)
     */
    public List getParametrosSegmentacionByPais(String codigoPais) {
    	return paisDAO.getParametrosSegmentacionByPais(codigoPais);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.PaisService#insertParametrosSegmentacion(java.util.Map)
     */
    public void insertParametrosSegmentacion(Map criteria) {
    	paisDAO.insertParametrosSegmentacion(criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.PaisService#updateParametrosSegmentacion(java.util.Map)
	 */
	public void updateParametrosSegmentacion(Map criteria) {
		paisDAO.updateParametrosSegmentacion(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.PaisService#getPaisByContextRoot(java.lang.String)
	 */
	public Pais getPaisByContextRoot(String contextRoot) {
		Pais pais = paisDAO.getPaisByContextRoot(contextRoot);
		return pais;
	}

}