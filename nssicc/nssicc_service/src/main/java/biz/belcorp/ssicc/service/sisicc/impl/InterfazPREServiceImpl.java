package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazPREDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazPREService;

@Service("sisicc.interfazPREService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPREServiceImpl extends BaseService implements InterfazPREService
{
	@Resource(name="sisicc.interfazPREDAO")
	private InterfazPREDAO interfazPREDAO;

	@Override
	public void executeValidacionMatrizPlanit(Map params) {
		interfazPREDAO.executeValidacionMatrizPlanit(params);
		
	}

	@Override
	public void envioCorreo(Map params) {
		interfazPREDAO.envioCorreo(params);
	}

	@Override
	public Integer verificarErrorMatrizPlanit(Map params) {
		// TODO Auto-generated method stub
		return interfazPREDAO.verificarErrorMatrizPlanit(params);
	}

	@Override
	public void executeInterfazPrCargaMatrizPlan(Map params) {
		// TODO Auto-generated method stub
		interfazPREDAO.executeInterfazPrCargaMatrizPlan(params);
	}
	

}
