package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazAPERecepcionarAnaquelesServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Service("sisicc.interfazAPERecepcionarAnaquelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAPERecepcionarAnaquelesServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;
	
	@Resource(name="sisicc.interfazAPEDAO")
	protected InterfazAPEDAO interfazAPEDAO;   
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
     */
    protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		interfazAPEDAO.executeInterfazAPERecepcionarAnaqueles(map);	
		String periodoProceso = interfazAPEDAO.getPeriodoProcesoEnvioAnaqueles();
		// Si el periodo de proceso es diferente de 200000 (no hay registros cargados)
		if(!periodoProceso.equals(Constants.APE_PERIODO_ERROR)){
			map.put("codigoPeriodo", periodoProceso);
			executeEnvioMail(map);
		}
	}
    
    /**
     * @param criteria
     * Envio de correos de confirmacion
     */
    public void executeEnvioMail(Map criteria){
    	Map params = new HashMap();
		params = (Map)(interfazAPEDAO.getEnvioMailsAPEParams(criteria).get(0));
    	//------Parametros Generales------		
		String correoOrigen     = params.get("mailOrigen").toString();
		String correoDestino    = params.get("mailDestino").toString();
		String plantilla        = params.get("plantilla").toString();
		String subject          = params.get("subject").toString();
		
		List listaProductos = interfazAPEDAO.getListaProductosSinIndicador(criteria);
		criteria.put("cantidadSinIndicador", new Integer(listaProductos.size()));
		criteria.put("listaProductos", listaProductos);
		
		mailUtil.enviarMail(correoOrigen,     //correoOrigen
							correoDestino,    //correo Destino 
							subject,          //Subject  
							plantilla,        //plantilla 
				            criteria);
		
    }
}
