/*
 * Created on 06/03/2006 04:46:46 PM
 * biz.belcorp.ssicc.scdf.service.impl.ControlFacturacionServiceImpl
 */
package biz.belcorp.ssicc.service.scdf.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ControlFacturacionDAO;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.ControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlFacturacionServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class ControlFacturacionServiceImpl extends BaseService implements
        ControlFacturacionService {
    
	
    private ControlFacturacionDAO controlFacturacionDAO;

   

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.ControlFacturacionService#getControlesFacturacionByCriteria(java.util.Map)
     */
    public List getControlesFacturacionByCriteria(Map criteria) {
        return controlFacturacionDAO.getControlesFacturacionByCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlFacturacionService#getControlFacturacion(java.lang.String)
     */
    public ControlFacturacion getControlFacturacion(String codigoPais) {
        return controlFacturacionDAO.getControlFacturacion(codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlFacturacionService#insertControlFacturacion(biz.belcorp.ssicc.scdf.model.ControlFacturacion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertControlFacturacion(ControlFacturacion controlFacturacion,
            Usuario usuario) {
        controlFacturacionDAO.insertControlFacturacion(controlFacturacion, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlFacturacionService#updateControlFacturacion(biz.belcorp.ssicc.scdf.model.ControlFacturacion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateControlFacturacion(ControlFacturacion controlFacturacion,
            Usuario usuario) {
        controlFacturacionDAO.updateControlFacturacion(controlFacturacion, usuario);
    }

}
