package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAcumulacionPuntosProgramaPuntosDAO;

/**
 * Service que va a generar puntos para programas de puntos
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("spusicc.procesoINCAcumulacionPuntosProgramaPuntosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCAcumulacionPuntosProgramaPuntosServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoINCAcumulacionPuntosProgramaPuntosDAO")
	private ProcesoINCAcumulacionPuntosProgramaPuntosDAO procesoINCAcumulacionPuntosProgramaPuntosDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCAcumulacionPuntosProgramaPuntosDAO.executeAcumulacionPuntosProgramaPuntos(params);
	}
	
	
}
