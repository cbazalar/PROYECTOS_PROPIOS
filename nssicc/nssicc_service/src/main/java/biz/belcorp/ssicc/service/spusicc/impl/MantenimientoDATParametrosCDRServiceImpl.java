package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.MantenimientoDATParametrosCDRDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.ParametroCDR;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoDATParametrosCDRService;

/**
 * <p>
 * <a href="MantenimientoDATParametrosCDRServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma
 *         </a>
 **/
@Service("spusicc.mantenimientoDATParametrosCDRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDATParametrosCDRServiceImpl extends BaseService implements MantenimientoDATParametrosCDRService {

	@Resource(name="spusicc.mantenimientoDATParametrosCDRDAO")
	MantenimientoDATParametrosCDRDAO mantenimientoDATParametrosCDRDAO;

	public List getParametrosCDRByPais(ParametroCDR parametroCDR) {

		return mantenimientoDATParametrosCDRDAO
				.getParametrosCDR(parametroCDR);

	}

	public ParametroCDR getParametroCDRById(ParametroCDR parametroCDR) {

		return mantenimientoDATParametrosCDRDAO
				.getParametroCDRById(parametroCDR);
	}

	public void insertParametrosCDR(ParametroCDR parametroCDR) {
		mantenimientoDATParametrosCDRDAO.insertParametroCDR(parametroCDR);

	}

	public void updateParametrosCDR(ParametroCDR parametroCDR) {

		mantenimientoDATParametrosCDRDAO.updateParametroCDR(parametroCDR);
	}

	public void deleteParametrosCDR(ParametroCDR parametroCDR) {
		mantenimientoDATParametrosCDRDAO.deleteParametroCDR(parametroCDR);

	}

	public MantenimientoDATParametrosCDRDAO getMantenimientoDATParametrosCDRDAO() {
		return mantenimientoDATParametrosCDRDAO;
	}

	public void setMantenimientoDATParametrosCDRDAO(
			MantenimientoDATParametrosCDRDAO mantenimientoDATParametrosCDRDAO) {
		this.mantenimientoDATParametrosCDRDAO = mantenimientoDATParametrosCDRDAO;
	}

}
