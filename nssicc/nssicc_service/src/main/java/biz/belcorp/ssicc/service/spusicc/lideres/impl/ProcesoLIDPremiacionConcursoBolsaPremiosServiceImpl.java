package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.math3.analysis.function.Constant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDPremiacionConcursoBolsaPremiosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que Genera Solicitudes de premiacion de los concursos con tipo
 * de premiacion Bolsa de Premios
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoLIDPremiacionConcursoBolsaPremiosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDPremiacionConcursoBolsaPremiosServiceImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoLIDPremiacionConcursoBolsaPremiosDAO")
	private ProcesoLIDPremiacionConcursoBolsaPremiosDAO procesoLIDPremiacionConcursoBolsaPremiosDAO;

	protected void executeStoreProcedure(Map params) {
		params.put("indicadorProceso", Constants.PROCESO_GP4);
		procesoLIDPremiacionConcursoBolsaPremiosDAO.executePremiacionConcursoBolsaPremios(params);
	}
	

}