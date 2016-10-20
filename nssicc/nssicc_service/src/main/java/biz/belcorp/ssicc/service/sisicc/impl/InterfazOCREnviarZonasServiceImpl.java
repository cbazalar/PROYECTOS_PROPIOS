package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;



/**
 * Service de la Interfaz OCR_Enviar Zonas.
 *
 * @author  <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 *
 */
@Service("sisicc.interfazOCREnviarZonasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCREnviarZonasServiceImpl
    extends BaseInterfazSalidaAbstractService {

    /**
     * Obtiene la data para la Interfaz OCR_Enviar Zonas
     *
     * @param params
     *            Map con los parametros del query
     * @return List con Maps de las filas retornadas
     *
     * @throws InterfazException
     */
    public List readData(Map params) throws InterfazException {
    	
		List listZonas = interfazSiCCDAO.getInterfazOCREnviarZonas(params);
		
        return listZonas;
    }
    
}
