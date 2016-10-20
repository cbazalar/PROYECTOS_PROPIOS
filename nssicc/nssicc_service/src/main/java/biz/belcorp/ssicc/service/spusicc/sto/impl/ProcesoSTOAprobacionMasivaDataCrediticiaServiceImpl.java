package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOAprobacionMasivaDataCrediticiaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOAprobacionMasivaDataCrediticiaService;

@Service("spusicc.procesoSTOAprobacionMasivaDataCrediticiaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOAprobacionMasivaDataCrediticiaServiceImpl extends BaseService implements ProcesoSTOAprobacionMasivaDataCrediticiaService{
	
	@Resource(name="spusicc.procesoSTOAprobacionMasivaDataCrediticiaDAO")
	ProcesoSTOAprobacionMasivaDataCrediticiaDAO procesoSTOAprobacionMasivaDataCrediticiaDAO;
	
	@Override
	public Integer getValidaSolicCodigoConsultora(String value) {
		return procesoSTOAprobacionMasivaDataCrediticiaDAO.getValidaSolicCodigoConsultora(value);
	}

	@Override
	public void updateSolicCodigoConsultora(Map criteria) {
		procesoSTOAprobacionMasivaDataCrediticiaDAO.updateSolicCodigoConsultora(criteria);
		
	}

}
