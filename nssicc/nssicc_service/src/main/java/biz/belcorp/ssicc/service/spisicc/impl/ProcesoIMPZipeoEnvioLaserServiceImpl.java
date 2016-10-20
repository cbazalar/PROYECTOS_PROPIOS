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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoIMPZipeoEnvioLaserServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
@Service("spisicc.procesoIMPZipeoEnvioLaserService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPZipeoEnvioLaserServiceImpl extends BaseInterfazSalidaAbstractService{
	
		
	@Resource(name = "spisicc.procesoImpresionTransporteService")
	BaseProcesoImpresionService baseProcesoImpresion;
	
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
		
		Map params = interfazParams.getQueryParams();
		Usuario usuario =(Usuario)params.get("usuario");
		params.put("codigoProcesoImpresion", Constants.PROCESO_IMPRESION_LASER);//LAS
		// Invocamos al procedimiento que genera el archivo
		baseProcesoImpresion.executeProcesoImpresion(params, usuario);
		
		//cantidad = procesoIMPGeneracionDocumentosLaserDAO.getCantidadNotaDebitoLaser(params);
		
		return cantidad;
	}

	

}
