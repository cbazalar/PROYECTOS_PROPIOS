/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;


import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * <p>
 * <a href="ProcesoIMPSpoolGeneraArchivoNotaCreditoMatricialServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Sergio Buchelli</a>
 */
@Service("spisicc.procesoIMPSpoolGeneraArchivoNotaCreditoMatricialService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPSpoolGeneraArchivoNotaCreditoMatricialServiceImpl extends BaseInterfazSalidaAbstractService {
	
	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;
	
	/**
	 * Metodo ejecutado antes de 'processInterfaz'. Este mtodo no tiene
	 * implementacin, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional antes del procesamiento de la Interfaz.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
	}

	@Override
	protected List readData(Map params) throws InterfazException {
		// TODO Auto-generated method stub
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
	protected int writeData(InterfazParams interfazParams, File tempFile, List data) throws InterfazException {

		int cantidad = 0;
		
		// Obtenemos los datos del archivo (nombre y directorio)
		String nombreArchivo = tempFile.getName();
		String directorio = tempFile.getParent();
		
		// Aadimos los valores obtenidos en el map
		Map params = interfazParams.getQueryParams();
		params.put("nombreArchivo", nombreArchivo);
		params.put("directorio", directorio);
		
		// Invocamos al procedimiento que genera el archivo
		procesoImpresionDAO.executeSpoolGeneraArchivoNotaCreditoMatricial(params);
		
		//cantidad = procesoIMPGeneracionDocumentosLaserDAO.getCantidadNotaDebitoLaser(params);
		
		return cantidad;
	}


}