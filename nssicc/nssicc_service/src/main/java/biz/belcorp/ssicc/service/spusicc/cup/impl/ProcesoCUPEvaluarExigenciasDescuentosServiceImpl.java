package biz.belcorp.ssicc.service.spusicc.cup.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso de evaluacion  de exigencias de descuentos
 * 
 * @author <a href="mailto:ghuertasa@sigcomt.com">Gonzalo Huertas</a>
 */
@Service("spusicc.procesoCUPEvaluarExigenciasDescuentosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCUPEvaluarExigenciasDescuentosServiceImpl extends
	BaseInterfazProcesoAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeEvaluarExigenciasDescuentos(params);
	}
	
}
