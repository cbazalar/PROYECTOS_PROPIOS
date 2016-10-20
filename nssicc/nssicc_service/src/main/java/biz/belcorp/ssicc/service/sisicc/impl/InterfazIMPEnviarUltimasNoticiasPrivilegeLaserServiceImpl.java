/*
 * Created on 18/05/2010 04:41:16 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazIMPEnviarUltimasNoticiasPrivilegeLaserServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosLaserDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Clase que genera el archivo de Ultimas Noticias Privilege laser, se
 * sobrescribe el metodo writeData para reutilizar el framework de interfaces
 * pero el archivo generado en realidad es un spool de impresion laser.
 * <p>
 * <a href="InterfazIMPEnviarUltimasNoticiasPrivilegeLaserServiceImpl"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazIMPEnviarUltimasNoticiasPrivilegeLaserService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarUltimasNoticiasPrivilegeLaserServiceImpl extends
		BaseInterfazSalidaAbstractService {

	@Resource(name="spisicc.procesoIMPGeneracionDocumentosLaserDAO")
	ProcesoIMPGeneracionDocumentosLaserDAO procesoIMPGeneracionDocumentosLaserDAO;

	
	@Override
	protected List readData(Map params) throws InterfazException {
		// Retornamos una lista vacia ya que el archivo se generara
		// a traves de un store procedure
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService
	 * #
	 * writeData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams
	 * , java.io.File, java.util.List)
	 */
	@Override
	protected int writeData(InterfazParams interfazParams, File tempFile,
			List data) throws InterfazException {

		int cantidad = 0;
		
		// Obtenemos los datos del archivo (nombre y directorio)
		String nombreArchivo = tempFile.getName();
		String directorio = tempFile.getParent();
		
		// Aadimos los valores obtenidos en el map
		Map params = interfazParams.getQueryParams();
		params.put("nombreArchivo", nombreArchivo);
		params.put("directorio", directorio);
		
		// Invocamos al proceso que reemplaza los caracteres especiales
		procesoIMPGeneracionDocumentosLaserDAO.executeReemplazaCaracteresUltimasNoticiasPrivilegeLaser(params);
		// Invocamos al procedimiento que genera el archivo
		procesoIMPGeneracionDocumentosLaserDAO.executeGeneraUltimasNoticiasPrivilegeLaser(params);
		
		cantidad = procesoIMPGeneracionDocumentosLaserDAO.getCantidadUltimasNoticiasPrivilegeLaser(params);
		
		return cantidad;
	}

}
