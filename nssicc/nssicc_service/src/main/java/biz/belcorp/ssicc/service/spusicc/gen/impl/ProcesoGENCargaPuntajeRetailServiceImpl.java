package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaPuntajeRetailDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoGENCargaPuntajeRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENCargaPuntajeRetailServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name = "spusicc.procesoINCCargaPuntajeRetailDAO")
	private ProcesoINCCargaPuntajeRetailDAO procesoINCCargaPuntajeRetailDAO;
	
	/**
	 * @param procesoINCCargaPuntajeRetailDAO the procesoINCCargaPuntajeRetailDAO to set
	 */
	public void setProcesoINCCargaPuntajeRetailDAO(ProcesoINCCargaPuntajeRetailDAO procesoINCCargaPuntajeRetailDAO) {
		this.procesoINCCargaPuntajeRetailDAO = procesoINCCargaPuntajeRetailDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCCargaPuntajeRetailDAO.executeCargaPuntajeRetail(params);
	}
}