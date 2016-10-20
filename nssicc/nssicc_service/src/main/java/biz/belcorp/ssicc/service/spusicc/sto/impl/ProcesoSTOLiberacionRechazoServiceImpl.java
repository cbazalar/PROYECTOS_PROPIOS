package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOLiberacionRechazoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLiberacionRechazoService;

/**
 * Service con metodos para las consultas invocados por la pantalla de Liberacion de Rechazos
 * 
 * <p>
 * <a href="ProcesoSTOLiberacionRechazoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Diego Torres Loyola</a>
 * 
 */
@Service("spusicc.procesoSTOLiberacionRechazoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOLiberacionRechazoServiceImpl extends BaseService implements ProcesoSTOLiberacionRechazoService {

	@Resource(name="spusicc.procesoSTOLiberacionRechazoDAO")
	ProcesoSTOLiberacionRechazoDAO procesoSTOLiberacionRechazoDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOLiberacionRechazoService#getProcesosEjecutadosRechazoDocumentoByCriteria(java.util.Map)
	 */
	public List getProcesosEjecutadosRechazoDocumentoByCriteria(Map params) {
		return procesoSTOLiberacionRechazoDAO.getProcesosEjecutadosRechazoDocumentoByCriteria(params);
	}


}