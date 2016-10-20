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
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraPagosBancariosMasivos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosMasivosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.procesoCCCCargarPagosBancariosMasivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCCargarPagosBancariosMasivosServiceImpl extends BaseService implements ProcesoCCCCargarPagosBancariosMasivosService {
	
	@Resource(name = "spusicc.procesoCCCCargarPagosBancariosMasivosDAO")
	ProcesoCCCCargarPagosBancariosMasivosDAO procesoCCCCargarPagosBancariosMasivosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosMasivosService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return procesoCCCCargarPagosBancariosMasivosDAO.obtenerPathUpload(datos);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosMasivosService#deleteTablasCargaCargosAbonosMasivos()
	 */
	public void deleteTablasCargaPagosBancariosMasivos() {
		this.procesoCCCCargarPagosBancariosMasivosDAO.deleteTablasCargaPagosBancariosMasivos();		
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
		procesoCCCCargarPagosBancariosMasivosDAO.executeValidarPagosBancariosMasivos(criteria);			

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
			EstructuraPagosBancariosMasivos estructura = new EstructuraPagosBancariosMasivos();
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
					if (((String)mapRow.get("2")).length() > 1 ){
						String s = (String)mapRow.get("2");
						s = s.replace(",", ".");		
						estructura.setImportePago(((String)mapRow.get("2")).substring(0,((String)mapRow.get("2")).length()));	
						
						try {						
							estructura.setImportePago(String.valueOf(Double.parseDouble(s)));							
						}
						catch (Exception ex) {					    
							estructura.setImportePago("0.00");							
						}
					}
					else estructura.setImportePago((String)mapRow.get("2"));
				else estructura.setImportePago(null);
																
				estructura.setFila(fila);
		
				//insertamos en BD la estructura PagosBancarios Masivos recuperada del Excel
				procesoCCCCargarPagosBancariosMasivosDAO.insertEstructuraPagosBancariosMasivos(estructura);
			
		}
	
	excelUtil.cerrar();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargaPagosBancariosMasivos(){
		return procesoCCCCargarPagosBancariosMasivosDAO.getErroresCargaPagosBancariosMasivos();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosMasivosService#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeProcesarPagosBancariosMasivos(Map datos){		
		procesoCCCCargarPagosBancariosMasivosDAO.executeProcesarPagosBancariosMasivos(datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosMasivosService#getParamCargaArchivosBanco(java.util.Map)
	 */
	public List getParamCargaArchivosBanco(Map criteria){
		return procesoCCCCargarPagosBancariosMasivosDAO.getParamCargaArchivosBanco(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosMasivosService#insertCargaArchivosBanco(java.util.Map)
	 */
	public void insertCargaArchivosBanco(Map datos){
		procesoCCCCargarPagosBancariosMasivosDAO.insertCargaArchivosBanco(datos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosMasivosService#executeValidarCargaArchivosBanco()
	 */
	public void executeValidarCargaArchivosBanco(){
		procesoCCCCargarPagosBancariosMasivosDAO.executeValidarCargaArchivosBanco();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosMasivosService#deleteTablaCargaArchivosBanco()
	 */
	public void deleteTablaCargaArchivosBanco(){
		procesoCCCCargarPagosBancariosMasivosDAO.deleteTablaCargaArchivosBanco();
	}
	
}
