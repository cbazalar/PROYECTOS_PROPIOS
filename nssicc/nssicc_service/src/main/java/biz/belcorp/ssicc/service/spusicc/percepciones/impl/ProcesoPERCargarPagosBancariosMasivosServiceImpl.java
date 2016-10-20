/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.percepciones.impl;

  
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.percepciones.ProcesoPERCargarPagosBancariosMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.percepciones.model.EstructuraPercepcionesPagosBancariosMasivos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.percepciones.ProcesoPERCargarPagosBancariosMasivosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.procesoPERCargarPagosBancariosMasivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERCargarPagosBancariosMasivosServiceImpl extends BaseService implements ProcesoPERCargarPagosBancariosMasivosService {
	
	@Resource(name="spusicc.procesoPERCargarPagosBancariosMasivosDAO")
	ProcesoPERCargarPagosBancariosMasivosDAO procesoPERCargarPagosBancariosMasivosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.procesoPERCargarPagosBancariosMasivosService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return procesoPERCargarPagosBancariosMasivosDAO.obtenerPathUpload(datos);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.procesoPERCargarPagosBancariosMasivosService#deleteTablasCargaCargosAbonosMasivos()
	 */
	public void deleteTablasCargaPagosBancariosMasivos() {
		this.procesoPERCargarPagosBancariosMasivosDAO.deleteTablasCargaPagosBancariosMasivos();		
	}
	
	
	public void executeValidarPagosBancariosMasivos(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String extensionArchivo = (String)criteria.get("extensionArchivo");
		
		log.debug("JFA Incia Procesar Archivo Excel");
		
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
			procesarArchivoExcel(directorioTemporal, nombreArchivo);
		
		log.debug("JFA Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoPERCargarPagosBancariosMasivosDAO.executeValidarPagosBancariosMasivos(criteria);			

	}	
	
	/**
	 *Metodo que procesa el archivo excel
	 * @param directorioTemporal
	 * @param nombreArchivo
	 * @throws Exception
	 */
	private void procesarArchivoExcel(String directorioTemporal, String nombreArchivo) throws Exception {
	//Abrimos el archivo Excel para su procesamiento		
	ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
	
	//nos colocamos en la primera hoja del documento Excel
	excelUtil.initSheet(0);
	
	//nos pasamos a la segunda fila, ya que el primero se encuentra la cabecera
	excelUtil.next();
	int fila = 0;
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			EstructuraPercepcionesPagosBancariosMasivos estructura = new EstructuraPercepcionesPagosBancariosMasivos();
			fila = fila + 1;
			
			log.debug("mapRow--- "+fila+" - "+mapRow.toString());

				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 1 )
						estructura.setCodigoConsultora(((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length()));	
					else estructura.setCodigoConsultora((String)mapRow.get("0"));
				else estructura.setCodigoConsultora(null);
				
				
				if (StringUtils.isNotEmpty((String)mapRow.get("1")) )										   
					if (((String)mapRow.get("1")).length() > 1 )
						estructura.setFechaPago(((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length()));													
					else estructura.setFechaPago((String)mapRow.get("1"));
				else estructura.setFechaPago(null);
																								
				if (StringUtils.isNotEmpty((String)mapRow.get("2")) )										   
					if (((String)mapRow.get("2")).length() > 1 ) {
																								
						String s = (String)mapRow.get("2");
						s = s.replace(",", ".");						
						estructura.setImportePagoValidacion(((String)mapRow.get("2")).substring(0,((String)mapRow.get("2")).length()));
						//estructura.setImporteMovimiento(Double.parseDouble(s));
						
						try {						
							estructura.setImportePago(Double.parseDouble(s));							
						}
						catch (Exception ex) {					    
							estructura.setImportePago(0.00);							
						};
					}
				
					else estructura.setImportePago(Double.parseDouble((String)(mapRow.get("2"))));
				else estructura.setImportePago(null);
																
				estructura.setFila(fila);
								
				//insertamos en BD la estructura PagosBancarios Masivos recuperada del Excel
				procesoPERCargarPagosBancariosMasivosDAO.insertEstructuraPagosBancariosMasivos(estructura);
			
		}
	
	excelUtil.cerrar();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargaPagosBancariosMasivos(){
		return procesoPERCargarPagosBancariosMasivosDAO.getErroresCargaPagosBancariosMasivos();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.procesoPERCargarPagosBancariosMasivosService#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeProcesarPagosBancariosMasivos(Map datos){		
		procesoPERCargarPagosBancariosMasivosDAO.executeProcesarPagosBancariosMasivos(datos);
	}
}
