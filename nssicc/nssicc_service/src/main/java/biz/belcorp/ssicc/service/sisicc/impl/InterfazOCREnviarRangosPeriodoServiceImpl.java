/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarInscritasServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazOCREnviarMatrizServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 */
@Service("sisicc.interfazOCREnviarRangosPeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCREnviarRangosPeriodoServiceImpl extends
		BaseInterfazSalidaAbstractService {

	protected List readData(Map params) throws InterfazException {
		List listRangosPeriodo = interfazSiCCDAO.getInterfazOCREnviarRangosPeriodo(params);
		
        return listRangosPeriodo;
	}

}
