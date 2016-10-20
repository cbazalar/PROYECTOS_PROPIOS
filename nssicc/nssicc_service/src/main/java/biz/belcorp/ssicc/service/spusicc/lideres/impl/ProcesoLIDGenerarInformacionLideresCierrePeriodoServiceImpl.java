package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDGenerarInformacionLideresCierrePeriodoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarInformacionLideresCierrePeriodoService;

@Service("spusicc.ProcesoLIDGenerarInformacionLideresCierrePeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDGenerarInformacionLideresCierrePeriodoServiceImpl extends BaseService
		implements ProcesoLIDGenerarInformacionLideresCierrePeriodoService {
	
	@Resource(name="spusicc.procesoLIDGenerarInformacionLideresCierrePeriodoDAO")
	public ProcesoLIDGenerarInformacionLideresCierrePeriodoDAO procesoLIDGenerarInformacionLideresCierrePeriodoDAO;

	public void executeGenerarInformacionLideresCierrePeriodo(Map params) {
		procesoLIDGenerarInformacionLideresCierrePeriodoDAO.executeGenerarInformacionLideresCierrePeriodo(params);
		
	}

	
}
