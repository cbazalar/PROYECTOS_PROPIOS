package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAcumulacionPuntosProgramaConstanciaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service que va a generar puntos para programas de constancia
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("spusicc.procesoINCAcumulacionPuntosProgramaConstanciaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCAcumulacionPuntosProgramaConstanciaServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoINCAcumulacionPuntosProgramaConstanciaDAO")
	private ProcesoINCAcumulacionPuntosProgramaConstanciaDAO procesoINCAcumulacionPuntosProgramaConstanciaDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCAcumulacionPuntosProgramaConstanciaDAO.executeAcumulacionPuntosProgramaConstancia(params);
	}

	
		
}
