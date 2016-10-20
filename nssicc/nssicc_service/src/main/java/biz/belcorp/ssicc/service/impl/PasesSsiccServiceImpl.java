/*
 * Created on 08/11/2005 17:43:03 AM
 * biz.belcorp.ssicc.service.impl.RolServiceImpl
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.PasesSsiccDAO;
import biz.belcorp.ssicc.service.PasesSsiccService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PasesSsiccServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("pasesSsiccService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PasesSsiccServiceImpl extends BaseService implements PasesSsiccService {

	@Resource(name="pasesSsiccDAO")
    private PasesSsiccDAO pasesSsiccDAO;
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.PasesSsiccService#getListaPaisMarca()
     */
    public List getListaPaisMarca() {
        return this.pasesSsiccDAO.getListaPaisMarca();
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.PasesSsiccService#getResultadoLogErrores(java.util.Map)
     */
    public List getResultadoLogErrores(Map criteria){
        return this.pasesSsiccDAO.getResultadoLogErrores(criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.PasesSsiccService#getResultadoEjecucion(java.util.Map)
     */
    public List getResultadoEjecucion(Map criteria){
        return this.pasesSsiccDAO.getResultadoEjecucion(criteria);
    }
}
