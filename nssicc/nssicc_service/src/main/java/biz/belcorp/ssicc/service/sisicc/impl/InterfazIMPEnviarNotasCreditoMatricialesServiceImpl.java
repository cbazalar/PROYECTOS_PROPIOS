/*
 * Created on 29/05/2009 11:36:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazIMPEnviarNotasCreditoMatricialesServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosMatricialesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Clase que genera el archivo de documento matricial de notas de credito, 
 * se sobrescribe el metodo writeData para reutilizar el framework 
 * de interfaces pero el archivo generado en realidad es un spool
 * de impresion matricial.
 * <p>
 * <a href="InterfazIMPEnviarNotasCreditoMatricialesServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazIMPEnviarNotasCreditoMatricialesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarNotasCreditoMatricialesServiceImpl extends
		BaseInterfazSalidaAbstractService {

	@Resource(name="spisicc.procesoIMPGeneracionDocumentosMatricialesDAO")
	ProcesoIMPGeneracionDocumentosMatricialesDAO procesoIMPGeneracionDocumentosMatricialesDAO;

	
	
	protected List readData(Map params) throws InterfazException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#writeData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
	 */
	protected int writeData(InterfazParams interfazParams, File tempFile,
			List data) throws InterfazException {
		
		int cantidadNotasCredito = 0;
		
		// Obtenemos los datos del archivo (nombre y directorio)
		String nombreArchivo = tempFile.getName();
		String directorio = tempFile.getParent();
		
		// Aadimos los valores obtenidos en el map
		Map params = interfazParams.getQueryParams();
		params.put("nombreArchivo", nombreArchivo);
		params.put("directorio", directorio);
		
		// Invocamos al procedimiento que genera el archivo
		procesoIMPGeneracionDocumentosMatricialesDAO.executeProcesaNotasCreditoMatriciales(params);
		procesoIMPGeneracionDocumentosMatricialesDAO.executeGeneraNotasCreditoMatriciales(params);
		
		cantidadNotasCredito = procesoIMPGeneracionDocumentosMatricialesDAO.getCantidadDocumentosMatriciales(params);
		
		return cantidadNotasCredito;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		Map params = interfazParams.getQueryParams();
		procesoIMPGeneracionDocumentosMatricialesDAO.executeActualizaCorrelativoDocumentosMatriciales(params);
	}
	
}
