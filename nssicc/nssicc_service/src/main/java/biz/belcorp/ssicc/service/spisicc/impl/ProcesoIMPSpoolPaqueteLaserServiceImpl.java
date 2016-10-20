package biz.belcorp.ssicc.service.spisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPSpoolDocumentoInternoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Service("spisicc.procesoIMPSpoolPaqueteLaserService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPSpoolPaqueteLaserServiceImpl extends BaseInterfazSalidaAbstractService{

	@Resource(name="spisicc.procesoIMPSpoolDocumentoInternoDAO")
	private ProcesoIMPSpoolDocumentoInternoDAO procesoIMPSpoolDocumentoInternoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
	}

	@Override
	protected List readData(Map params) throws InterfazException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param interfazParams
	 * @param tempFile
	 * @param data
	 * @return
	 * @throws InterfazException
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
		procesoIMPSpoolDocumentoInternoDAO.executeGeneraPaqueteLaser(params);
		
		//cantidad = procesoIMPGeneracionDocumentosLaserDAO.getCantidadNotaDebitoLaser(params);
		
		return cantidad;
	}

}