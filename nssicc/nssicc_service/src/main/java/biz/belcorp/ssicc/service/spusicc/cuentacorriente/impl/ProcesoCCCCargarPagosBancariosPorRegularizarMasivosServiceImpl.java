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
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraPagosBancariosPorRegularizarMasivos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.procesoCCCCargarPagosBancariosPorRegularizarMasivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCCargarPagosBancariosPorRegularizarMasivosServiceImpl extends BaseService implements ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService {
	
	@Resource(name = "spusicc.procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO")
	ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO.obtenerPathUpload(datos);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService#deleteTablasCargaCargosAbonosMasivos()
	 */
	public void deleteTablasCargaPagosBancariosPorRegularizarMasivos() {
		this.procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO.deleteTablasCargaPagosBancariosPorRegularizarMasivos();		
	}
	
	
	public void executeValidarPagosBancariosPorRegularizarMasivos(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String extensionArchivo = (String)criteria.get("extensionArchivo");
		
		log.debug("JFA Incia Procesar Archivo Excel");
		
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
			procesarArchivoExcel(directorioTemporal, nombreArchivo);
		
		log.debug("JFA Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO.executeValidarPagosBancariosPorRegularizarMasivos(criteria);			

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
			EstructuraPagosBancariosPorRegularizarMasivos estructura = new EstructuraPagosBancariosPorRegularizarMasivos();
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
					if (((String)mapRow.get("2")).length() > 1 )
						estructura.setImportePago(((String)mapRow.get("2")).substring(0,((String)mapRow.get("2")).length()));													
					else estructura.setImportePago((String)mapRow.get("2"));
				else estructura.setImportePago(null);
																
				estructura.setFila(fila);
		
				//insertamos en BD la estructura PagosBancariosPorRegularizar Masivos recuperada del Excel
				procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO.insertEstructuraPagosBancariosPorRegularizarMasivos(estructura);
			
		}
	
	excelUtil.cerrar();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargaPagosBancariosPorRegularizarMasivos(){
		return procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO.getErroresCargaPagosBancariosPorRegularizarMasivos();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeProcesarPagosBancariosPorRegularizarMasivos(Map datos){		
		procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO.executeProcesarPagosBancariosPorRegularizarMasivos(datos);
	}
}
