package biz.belcorp.ssicc.service.spusicc.cup.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso de Eliminar Despachos Consultoras No Constantes
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("spusicc.procesoCUPEliminarDespachosConsultorasNoConstantesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCUPEliminarDespachosConsultorasNoConstantesServiceImpl extends BaseInterfazProcesoAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeEliminarDespachosConsultorasNoConstantes(params);
	}	
}