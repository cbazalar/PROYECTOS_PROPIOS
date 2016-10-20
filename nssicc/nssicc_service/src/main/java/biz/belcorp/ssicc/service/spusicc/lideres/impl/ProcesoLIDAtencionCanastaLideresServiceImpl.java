package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDAtencionCanastaLideresDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que permite despachar los productos que forman la canasta de Lideres
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLIDAtencionCanastaLideresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDAtencionCanastaLideresServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLIDAtencionCanastaLideresDAO")
	private ProcesoLIDAtencionCanastaLideresDAO procesoLIDAtencionCanastaLideresDAO;

	protected void executeStoreProcedure(Map params) {
		procesoLIDAtencionCanastaLideresDAO.executeAtencionCanastaLideres(params);
	}
	

	
}