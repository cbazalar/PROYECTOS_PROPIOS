/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.FuenteVentasService;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSABEnviarFuenteVentasPrevistaServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazSABEnviarFuenteVentasPrevistaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSABEnviarFuenteVentasPrevistaServiceImpl extends
		BaseInterfazSalidaAbstractService {
	
	@Resource(name="sisicc.fuenteVentasService")
	protected FuenteVentasService fuenteVentasService;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		List list = null;
		try {
			
			String periodoSel = (String)queryParams.get("codigoPeriodo");
			String periodoAbierto = fuenteVentasService.getPeriodoAbierto(periodoSel);
			if (periodoAbierto.equalsIgnoreCase("N")) {
				list = interfazSiCCDAO.getInterfazSABEnviarFuenteVentasPrevista(queryParams);
			} else {
				list = new ArrayList();
				throw new InterfazException("El Periodo seleccionado se encuentra <b>Cerrado</b>.");
			}
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return list;
	}
}
