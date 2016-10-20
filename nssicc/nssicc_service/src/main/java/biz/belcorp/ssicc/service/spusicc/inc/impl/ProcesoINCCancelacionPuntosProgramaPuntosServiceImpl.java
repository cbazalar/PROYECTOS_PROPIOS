package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCancelacionPuntosProgramaPuntosDAO;

/**
 * Service que va a cancelar los puntos para programas de puntos
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("spusicc.procesoINCCancelacionPuntosProgramaPuntosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCancelacionPuntosProgramaPuntosServiceImpl extends
	BaseInterfazProcesoAbstractService {
	        
	@Resource(name="spusicc.procesoINCCancelacionPuntosProgramaPuntosDAO")
	private ProcesoINCCancelacionPuntosProgramaPuntosDAO procesoINCCancelacionPuntosProgramaPuntosDAO;

	protected void executeStoreProcedure(Map params) {
		
		String[] codigoRegiones = (String[])params.get("codigoRegionList");
		
		if(codigoRegiones != null && codigoRegiones.length > 0)
		{
			for(int i=0; i<codigoRegiones.length; i++){				
				params.put("codigoRegion", codigoRegiones[i]);
				procesoINCCancelacionPuntosProgramaPuntosDAO.executeCancelacionPuntosProgramaPuntos(params);							
			}
		}
		else
		{
			log.info("No existen regiones a procesar");
		}		
	}
	
}
