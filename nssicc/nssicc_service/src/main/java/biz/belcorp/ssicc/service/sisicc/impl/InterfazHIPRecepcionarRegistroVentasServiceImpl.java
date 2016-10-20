/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazHIPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * 
 * <p>
 * <a href="InterfazHIPRecepcionarRegistroVentasServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
@Service("sisicc.interfazHIPRecepcionarRegistroVentasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazHIPRecepcionarRegistroVentasServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazHIPDAO")
	InterfazHIPDAO interfazHIPDAO;
		
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#readData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
	 */
	protected int readData(InterfazParams interfazParams, File tempFile,
			List data) throws InterfazException {
		int cantidad = 0;
		
		// Obtenemos los datos del archivo (nombre y directorio)
		//String nombreArchivo = tempFile.getName();
		String directorio = tempFile.getParent();
		
		// Aadimos los valores obtenidos en el map
		Map params = interfazParams.getQueryParams();
		params.put("nombreArchivo", interfazParams.getTempName());
		params.put("directorio", directorio);
		
		// Invocamos al procedimiento que genera el archivo
		interfazHIPDAO.executeRecepcionRegistroVentas(params);
		
		String cantidadRegistros = (String)params.get("cantidadRegistros");
		if(StringUtils.isNotEmpty(cantidadRegistros))
			cantidad = new Integer(cantidadRegistros);
		
		return cantidad;
	}
	



}