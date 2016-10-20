/*
 * Created on 11/11/2005 06:09:38 PM
 *
 * biz.belcorp.ssicc.service.impl.IdiomaServiceImpl
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
import biz.belcorp.ssicc.dao.IdiomaDAO;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IdiomaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a> 
 */
@Service("idiomaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class IdiomaServiceImpl extends BaseService implements IdiomaService {
    
	@Resource(name="idiomaDAO")
    private IdiomaDAO idiomaDAO;

    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.IdiomaService#getIdiomas(biz.belcorp.ssicc.model.Idioma)
     */
    public List getIdiomas(Idioma idioma) {
        return idiomaDAO.getIdiomas(idioma);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.IdiomaService#getIdiomasByCriteria(java.util.Map)
     */
    public List getIdiomasByCriteria(Map criteria) {
        return idiomaDAO.getIdiomasByCriteria(criteria);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.IdiomaService#getIdioma(java.lang.String)
     */
    public Idioma getIdioma(String codigo) {
        return idiomaDAO.getIdioma(codigo);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.IdiomaService#getIdiomaByCodigoISO(java.lang.String)
     */
    public Idioma getIdiomaByCodigoISO(String codigo) {
        return idiomaDAO.getIdiomaByCodigoISO(codigo);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.IdiomaService#insertIdioma(biz.belcorp.ssicc.model.Idioma, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertIdioma(Idioma idioma, Usuario usuario) {
        // Asignamos los valores por defecto
        idioma.setEstado(Constants.ESTADO_ACTIVO);
        
        idiomaDAO.insertIdioma(idioma, usuario);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.IdiomaService#updateIdioma(biz.belcorp.ssicc.model.Idioma, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateIdioma(Idioma idioma, Usuario usuario) {
        idiomaDAO.updateIdioma(idioma, usuario);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.IdiomaService#removeIdioma(java.lang.String, biz.belcorp.ssicc.model.Usuario)
     */
    public void removeIdioma(String codigo, Usuario usuario) {
        // Actualizamos el estado del Idioma
        try {
            Idioma idioma = idiomaDAO.getIdioma(codigo);
            idioma.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el idioma
            idiomaDAO.updateIdioma(idioma, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }
    }

}
