/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.spusicc.fac.MantenimientoFACGenericoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.util.FTPUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazFACRecepcionarBoletaElectronicaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("sisicc.interfazFACRecepcionarBoletaElectronicaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFACRecepcionarBoletaElectronicaServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.mantenimientoFACGenericoDAO")
	MantenimientoFACGenericoDAO mantenimientoFACGenericoDAO;
	
	private List archivosProcesar;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		
		try
		{
			Interfaz interfaz = interfazParams.getInterfaz();
			
			FTPUtil ftpUtil = new FTPUtil();
			ftpUtil.loguearFTP(interfaz);
			
			//Sacamos la lista de archivos del directorio de E/S
			archivosProcesar = ftpUtil.buscarListaArchivo(interfaz.getDirectorioEntradaSalida(), interfaz.getNombreArchivoEntradaSalida(), interfaz.getExtensionArchivoDescripcion(), interfaz.getTipoNombreArchivo());
			
			//Movemos los archivos al directorio de trabajo
			if(archivosProcesar != null && archivosProcesar.size() > 0){
				for(int i=0; i<archivosProcesar.size(); i++){
					try
					{
						String archivo =(String)archivosProcesar.get(i);
						ftpUtil.moverArchivo(interfaz.getDirectorioEntradaSalida(), archivo, interfaz.getDirectorioProc());
					}
					catch(Exception iex)
					{
						log.error(iex.getMessage());
					}
				}
			}
			ftpUtil.cerrarFTP();
			//			
		}
		catch(Exception ex)
		{
			throw new InterfazException(ex.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		
		// Procesa los archivos 
		if(archivosProcesar != null && archivosProcesar.size() > 0){
			
			for(int i=0; i<archivosProcesar.size(); i++){
				String archivo = (String)archivosProcesar.get(i);
				String[] split1 = StringUtils.split(archivo, "_");
				String[] split2 = StringUtils.split(split1[2], "-");
				
				String nombreCarpeta = Constants.FAC_NOMBRE_CARPETA_BOLETAS;
				String serieDocumento = split2[0]+"%";
				String numeroDocumento = split2[1];
				String fechaProceso = StringUtils.trim(split1[3])+" "+StringUtils.left(split1[4], 2)+":"+StringUtils.right(split1[4], 2)+":00";
				
				Map criteria = new HashMap();
				criteria.put("nombreCarpeta", nombreCarpeta);
				criteria.put("serieDocumento", serieDocumento.toUpperCase());
				criteria.put("numeroDocumento", numeroDocumento);
				criteria.put("fechaProceso", fechaProceso);
				
				String oidTipoDocumento = mantenimientoFACGenericoDAO.getTipoDocumento(criteria);
				serieDocumento = split2[0];
				criteria.put("serieDocumento", serieDocumento.toUpperCase());
				criteria.put("oidTipoDocumento", oidTipoDocumento);
				
				mantenimientoFACGenericoDAO.insertDocumentoElectronico(criteria);
				mantenimientoFACGenericoDAO.updateIndicadorFacturacionElectronica(criteria);
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		//Mover los archivos del Directorio de proceso al direcotrio historico

		Interfaz interfaz = interfazParams.getInterfaz();
		
		FTPUtil ftpUtil = new FTPUtil();
		ftpUtil.loguearFTP(interfaz);

		if(archivosProcesar != null && archivosProcesar.size() > 0){
			
			for(int i=0; i<archivosProcesar.size(); i++){
				try
				{
					String archivo =(String)archivosProcesar.get(i);
					ftpUtil.moverArchivo(interfaz.getDirectorioProc(), archivo, interfaz.getDirectorioHistorico());
				}
				catch(Exception iex)
				{
					log.error(iex.getMessage());
				}
			}
		}
		
		ftpUtil.cerrarFTP();
		
	}
	
}
