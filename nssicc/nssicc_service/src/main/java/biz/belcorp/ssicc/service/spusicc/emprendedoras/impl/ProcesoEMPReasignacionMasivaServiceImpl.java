package biz.belcorp.ssicc.service.spusicc.emprendedoras.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.ProcesoEMPReasignacionMasivaDAO;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.model.EstructuraEMPReasignacionMasiva;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.ProcesoEMPReasignacionMasivaService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Service("spusicc.procesoEMPReasignacionMasivaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEMPReasignacionMasivaServiceImpl extends BaseService implements ProcesoEMPReasignacionMasivaService {
	
	@Resource(name = "spusicc.procesoEMPReasignacionMasivaDAO")
	ProcesoEMPReasignacionMasivaDAO procesoEMPReasignacionMasivaDAO;

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoEMPReasignacionMasivaService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return procesoEMPReasignacionMasivaDAO.obtenerPathUpload(datos);
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoEMPReasignacionMasivaService#executeProcesarReasignacionMasiva(java.util.Map)
	 */
	public void executeProcesarReasignacionMasiva(Map datos) throws Exception {
		String directorioTemporal = (String)datos.get("directorioTemporal");
		String nombreArchivo = (String)datos.get("nombreArchivo");
		String extensionArchivo = (String)datos.get("extensionArchivo");	
		
		log.debug(" Incia Procesar Archivo Excel");
		
		if ((extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL) || extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL_2007) ))
			procesarArchivoExcel(directorioTemporal, nombreArchivo,datos);
		
		log.debug("  Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados y las reasignaciones respectivas
		procesoEMPReasignacionMasivaDAO.executeValidarReasignacionMasiva(datos);			

	}
		
	/**
	 *Metodo que procesa el archivo excel
	 * @param directorioTemporal
	 * @param nombreArchivo
	 * @throws Exception
	 */
	private void procesarArchivoExcel(String directorioTemporal, String nombreArchivo, Map datos) throws Exception {
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		
		//nos colocamos en la primera hoja del documento Excel
		excelUtil.initSheet(0);
		
		//nos pasamos a la segunda fila, ya que el primero se encuentra la cabecera
		excelUtil.next();
		int fila = 0;
			
		    // Borramos la informacion de la tabla temporal de carga
		    procesoEMPReasignacionMasivaDAO.deleteEstructuraEMPReasignacionMasiva();
		
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				EstructuraEMPReasignacionMasiva estructura = new EstructuraEMPReasignacionMasiva();
				
				fila = fila + 1;
				
				log.debug("mapRow--- "+fila+" - "+mapRow.toString());
	
					if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
						if (((String)mapRow.get("0")).length() > 1 )
							estructura.setCodigoConsultora(((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length()));	
						else estructura.setCodigoConsultora((String)mapRow.get("0"));
					else estructura.setCodigoConsultora(null);
					log.debug(" CodigoConsultora ---> "+estructura.getCodigoConsultora());
					
					if (StringUtils.isNotEmpty((String)mapRow.get("1")) )
						if (((String)mapRow.get("1")).length() > 1 )
							estructura.setCodigoNuevaEmprendedora(((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length()));	
						else estructura.setCodigoNuevaEmprendedora((String)mapRow.get("1"));
					else estructura.setCodigoNuevaEmprendedora(null);
					log.debug(" CodigoNuevaEmprendedora ---> "+estructura.getCodigoNuevaEmprendedora());
					
																	
					estructura.setCodigoUsuario((String)(datos.get("codigoUsuario")));
					estructura.setFila(fila);
					
					//insertamos en BD la estructura de reasignacion masiva recuperada del Excel
					procesoEMPReasignacionMasivaDAO.insertEstructuraEMPReasignacionMasiva(estructura);								
			}
		
		excelUtil.cerrar();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresReasignacionMasiva(){
		return procesoEMPReasignacionMasivaDAO.getErroresReasignacionMasiva();
	}
	
	
}