/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarCADMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraCADMasivos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarCADMasivosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.procesoCCCCargarCADMasivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCCargarCADMasivosServiceImpl extends BaseService implements ProcesoCCCCargarCADMasivosService {
	
	@Resource(name = "spusicc.procesoCCCCargarCADMasivosDAO")
	ProcesoCCCCargarCADMasivosDAO procesoCCCCargarCADMasivosDAO;


	public String obtenerPathUpload(Map datos) {
		return procesoCCCCargarCADMasivosDAO.obtenerPathUpload(datos);
	}
	
	
	
	public void deleteTablasCargaCargosAbonosMasivos(Map datos) {
		this.procesoCCCCargarCADMasivosDAO.deleteTablasCargaCargosAbonosMasivos(datos);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#executeValidarCargaFuenteVentaPrevista(java.util.Map)
	 */
	public void executeValidarCargosAbonosMasivos(Map datos) throws Exception {
		String directorioTemporal = (String)datos.get("directorioTemporal");
		String nombreArchivo = (String)datos.get("nombreArchivo");
		String extensionArchivo = (String)datos.get("extensionArchivo");	
		
		log.debug("JFA Incia Procesar Archivo Excel");
		
		if ((extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL) || extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL_2007) ))
			procesarArchivoExcel(directorioTemporal, nombreArchivo,datos);
		
		log.debug("JFA Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoCCCCargarCADMasivosDAO.executeValidarCargosAbonosMasivos(datos);			

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
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			EstructuraCADMasivos estructura = new EstructuraCADMasivos();
			
			fila = fila + 1;
			
			log.debug("mapRow--- "+fila+" - "+mapRow.toString());

				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 1 )
						estructura.setCodigoConsultora(((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length()));	
					else estructura.setCodigoConsultora((String)mapRow.get("0"));
				else estructura.setCodigoConsultora(null);
				
														
				if (StringUtils.isNotEmpty((String)mapRow.get("1")) )										   
					if (((String)mapRow.get("1")).length() > 1 ) {
																								
						String s = (String)mapRow.get("1");
						s = s.replace(",", ".");						
						estructura.setImporteMovimientoValidacion(((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length()));
						//estructura.setImporteMovimiento(Double.parseDouble(s));
						
						try {						
							estructura.setImporteMovimiento(Double.parseDouble(s));							
						}
						catch (Exception ex) {					    
							estructura.setImporteMovimiento(0.00);							
						};
					}
				
					else {
						
						estructura.setImporteMovimientoValidacion(((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length()));
																							
					}
								
				else estructura.setImporteMovimiento(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("2")) )
					if (((String)mapRow.get("2")).length() > 1 )
						estructura.setObservacion(((String)mapRow.get("2")).substring(0,((String)mapRow.get("2")).length()));	
					else estructura.setObservacion((String)mapRow.get("2"));
				else estructura.setObservacion(null);
				
				
				estructura.setNumeroLote((String)(datos.get("numeroLote")));												
				estructura.setCodigoUsuario((String)(datos.get("codigoUsuario")));
				estructura.setFila(fila);
		
				//insertamos en BD la estructura CAD Masivos recuperada del Excel
				procesoCCCCargarCADMasivosDAO.insertEstructuraCADMasivos(estructura);
			
		}
	
	excelUtil.cerrar();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargaCargosAbonosMasivos(Map datos){
		return procesoCCCCargarCADMasivosDAO.getErroresCargaCargosAbonosMasivos(datos);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADMasivosService#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeProcesarCargosAbonosMasivos(Map datos){		
		procesoCCCCargarCADMasivosDAO.executeProcesarCargosAbonosMasivos(datos);
	}
}
