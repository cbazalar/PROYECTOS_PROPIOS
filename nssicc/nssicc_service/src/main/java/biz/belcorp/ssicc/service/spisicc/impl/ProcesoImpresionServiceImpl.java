/*
 * Created on 24/01/2007 11:47:51 AM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("spisicc.procesoImpresionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoImpresionServiceImpl extends BaseService implements ProcesoImpresionService {

	@Resource(name="spisicc.procesoImpresionDAO")
    private ProcesoImpresionDAO procesoImpresionDAO;

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.ProcesoImpresionService#getProcesosImpresion(biz.belcorp.ssicc.spisicc.model.ProcesoImpresion)
     */
    public List getProcesosImpresion(ProcesoImpresion procesoImpresion) {
        return procesoImpresionDAO.getProcesosImpresion(procesoImpresion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.ProcesoImpresionService#getProcesosImpresionByCriteria(java.util.Map)
     */
    public List getProcesosImpresionByCriteria(Map criteria) {
        return procesoImpresionDAO.getProcesosImpresionByCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.ProcesoImpresionService#getProcesoImpresion(java.lang.String)
     */
    public ProcesoImpresion getProcesoImpresion(String codigo) {
        return procesoImpresionDAO.getProcesoImpresion(codigo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.ProcesoImpresionService#insertProcesoImpresion(biz.belcorp.ssicc.spisicc.model.ProcesoImpresion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertProcesoImpresion(ProcesoImpresion procesoImpresion,
            Usuario usuario) {
        procesoImpresionDAO.insertProcesoImpresion(procesoImpresion, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.ProcesoImpresionService#updateProcesoImpresion(biz.belcorp.ssicc.spisicc.model.ProcesoImpresion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateProcesoImpresion(ProcesoImpresion procesoImpresion,
            Usuario usuario) {
        procesoImpresionDAO.updateProcesoImpresion(procesoImpresion, usuario);
    }
    
	public void executeCalculoInterMora() {
		procesoImpresionDAO.executeCalculoInterMora();		
	}

	public boolean validacionLimiteTiempoEjecucionProceso() {
		return procesoImpresionDAO.validacionLimiteTiempoEjecucionProceso();
	}


}
