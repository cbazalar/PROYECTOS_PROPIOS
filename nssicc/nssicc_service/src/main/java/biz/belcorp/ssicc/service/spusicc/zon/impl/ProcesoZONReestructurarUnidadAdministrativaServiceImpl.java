package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso Zon de Reestructuracin de Unidades Admininistrativas
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
@Service("spusicc.procesoZONReestructurarUnidadAdministrativaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoZONReestructurarUnidadAdministrativaServiceImpl extends
	BaseInterfazProcesoAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeReestructurarUnidadAdministrativa(params);
	}
	
}
