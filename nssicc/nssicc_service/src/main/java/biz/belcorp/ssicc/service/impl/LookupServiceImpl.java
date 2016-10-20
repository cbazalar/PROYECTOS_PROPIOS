package biz.belcorp.ssicc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.LookupDAO;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * Implementacion de la interface LookupService.
 * <p>
 * <a href="LookupServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado </a>
 */
@Service("lookupService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class LookupServiceImpl extends BaseService implements LookupService {

	@Resource(name="lookupDAO")
    private LookupDAO lookupDAO;

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.LookupService#getAllIdiomas()
     */
    public List getAllIdiomas() {
        return lookupDAO.getIdiomas();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.LookupService#getAllPaises()
     */
    public List getAllPaises() {
        return lookupDAO.getPaises();
    }

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.LookupService#getAllTiposAcciones()
	 */
	public List getAllTiposAcciones() {
		return lookupDAO.getAllTiposAcciones();
	}

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.LookupService#getAllTiposBloqueoUsuario()
	 */
	public List getAllTiposBloqueoUsuario() {
		return lookupDAO.getAllTiposBloqueoUsuario();
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.LookupService#getExtensionesArchivo()
     */
    public List getExtensionesArchivo() {
        return lookupDAO.getExtensionesArchivo();
    }

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.LookupService#getExtensionesLog()
     */
    public List getExtensionesLog() {
        return lookupDAO.getExtensionesLog();
    }

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.LookupService#updateProcesosNoTerminados()
	 */
	public void updateProcesosNoTerminados() {
		lookupDAO.updateProcesosNoTerminados();		
	}

      /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.LookupService#getPoliticasSeguridad()
	 */
	public List getAllPoliticasSeguridadContrasenia(){
		return lookupDAO.getAllPoliticasSeguridadContrasenia();
	}

}