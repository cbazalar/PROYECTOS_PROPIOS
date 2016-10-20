/*
 * Created on 18/05/2006 12:24:18 PM
 * biz.belcorp.ssicc.scdf.service.impl.ControlProcesoServiceImpl
 */
package biz.belcorp.ssicc.service.scdf.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scdf.ControlProcesoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.ControlProcesoService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlProcesoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("scdf.controlProcesoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ControlProcesoServiceImpl extends BaseService implements
        ControlProcesoService {

	@Resource(name="scdf.controlProcesoDAO")
    private ControlProcesoDAO controlProcesoDAO;

   
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlProcesoService#executeCierreProcesosDiarios(java.lang.String)
     */
    public void executeCierreProcesosDiarios(String codigoPais) {
        // Ejecutamos el procedimiento encargado de hacer las actualizaciones
        // correspondientes
        controlProcesoDAO.executeCierreProcesosDiarios(codigoPais);
    }

}
