package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSICConcursoDuplaCyzone;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoSICConcursoDuplaCyzoneDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoSICConcursoDuplaCyzoneService;

@Service("spusicc.mantenimientoSICConcursoDuplaCyzoneService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSICConcursoDuplaCyzoneServiceImpl extends	BaseService implements MantenimientoSICConcursoDuplaCyzoneService {

	@Resource(name="spusicc.mantenimientoSICConcursoDuplaCyzoneDAO")
	MantenimientoSICConcursoDuplaCyzoneDAO mantenimientoDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
	InterfazSiCCDAO interfazSiCCDAO;

	public List getConcursosMantenenimientoDuplaCyzoneList(Map criteria) {		
		return mantenimientoDAO.getConcursosMantenenimientoDuplaCyzoneList(criteria);
	}

	public InterfazSICConcursoDuplaCyzone getConcursoDuplaCyzone(String codigoConcurso) {
		return mantenimientoDAO.getConcursoDuplaCyzone(codigoConcurso);
	}

	public void updateConcursoDuplaCyzone(InterfazSICConcursoDuplaCyzone concursoDuplaCyzone) {
		mantenimientoDAO.updateConcursoDuplaCyzone(concursoDuplaCyzone);
	}

	public void insertConcursoDuplaCyzone(InterfazSICConcursoDuplaCyzone concursoDuplaCyzone) {
		mantenimientoDAO.insertConcursoDuplaCyzone(concursoDuplaCyzone);		
	}

	public String getNextConcursoDuplaCyzone() {
		return  mantenimientoDAO.getNextConcursoDuplaCyzone();
	}

}
