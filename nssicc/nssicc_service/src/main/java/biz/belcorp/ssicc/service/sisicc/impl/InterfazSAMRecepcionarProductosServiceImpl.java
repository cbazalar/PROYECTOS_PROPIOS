/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * @author Danny Amaro
 *
 */
@Service("sisicc.interfazSAMRecepcionarProductosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMRecepcionarProductosServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			
			
			row.put("altostring", (String.valueOf(row.get("alto")) != null)?String.valueOf(row.get("alto")):"0");
			row.put("largostring", (String.valueOf(row.get("largo")) != null)?String.valueOf(row.get("largo")):"0");
			row.put("anchostring", (String.valueOf(row.get("ancho")) != null)?String.valueOf(row.get("ancho")):"0");
			
			row.put("volumenstring", (String.valueOf(row.get("volumen")) != null)?String.valueOf(row.get("volumen")):"0");
			row.put("pesoBrutostring", (String.valueOf(row.get("pesoBruto")) != null)?String.valueOf(row.get("pesoBruto")):"0");
			
			row.put("costeEstandarstring", (String.valueOf(row.get("costeEstandar")) != null)?String.valueOf(row.get("costeEstandar")):"0");
			
			interfazSAMDAO.executeRecepcionProducto(row);
		    
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
		
	}

}
