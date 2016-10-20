package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalculaPuntajeMultiMarcaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que calcula puntaje para pedidos multimarca
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCCalculaPuntajeMultiMarcaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCalculaPuntajeMultiMarcaServiceImpl extends
	BaseInterfazProcesoAbstractService {
	       
	@Resource(name="spusicc.procesoINCCalculaPuntajeMultiMarcaDAO")
	private ProcesoINCCalculaPuntajeMultiMarcaDAO procesoINCCalculaPuntajeMultiMarcaDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCCalculaPuntajeMultiMarcaDAO.executeCalculaPuntajeMultiMarca(params);
	}

}
