package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.MantenimientoIMPParametroProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ParametroProImpresion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPParametroProcesoImpresionService;

/**
 * @author yrivas
 *
 */
@Service("spisicc.mantenimientoIMPParametroProcesoImpresionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoIMPParametroProcesoImpresionServiceImpl extends BaseService implements MantenimientoIMPParametroProcesoImpresionService {

	@Resource(name="imp.mantenimientoIMPParametroProcesoImpresionDAO")
	MantenimientoIMPParametroProcesoImpresionDAO mantenimientoIMPParametroProcesoImpresionDAO;
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#getParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
     */
	public List getParametroProImp(ParametroProImpresion parametroProImpresion){
    	return mantenimientoIMPParametroProcesoImpresionDAO.getParametroProImp(parametroProImpresion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#insertParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
	 */
	public void insertParametroProImp(ParametroProImpresion parametroProImpresion) {
		mantenimientoIMPParametroProcesoImpresionDAO.insertParametroProImp(parametroProImpresion);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#updateParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
	 */
	public void updateParametroProImp(ParametroProImpresion parametroProImpresion) {
		mantenimientoIMPParametroProcesoImpresionDAO.updateParametroProImp(parametroProImpresion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.bas.dao.MantenimientoParametroProcesoImpresionDAO#deleteParametroProImp(biz.belcorp.ssicc.bas.model.ParametroProImpresion)
	 */
	public void deleteParametroProImp(ParametroProImpresion parametroProImpresion) {
		mantenimientoIMPParametroProcesoImpresionDAO.insertHistoricoParametroProImp(parametroProImpresion);
		mantenimientoIMPParametroProcesoImpresionDAO.deleteParametroProImp(parametroProImpresion);
	}

	
	
   

}
