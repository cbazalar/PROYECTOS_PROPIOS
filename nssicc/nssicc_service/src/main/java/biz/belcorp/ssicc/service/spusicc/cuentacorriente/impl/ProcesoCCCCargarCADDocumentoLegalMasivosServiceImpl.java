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
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarCADDocumentoLegalMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraCADDocumentoLegalMasivos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarCADDocumentoLegalMasivosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.procesoCCCCargarCADDocumentoLegalMasivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCCargarCADDocumentoLegalMasivosServiceImpl extends BaseService implements ProcesoCCCCargarCADDocumentoLegalMasivosService {
	
	@Resource(name = "spusicc.procesoCCCCargarCADDocumentoLegalMasivosDAO")
	ProcesoCCCCargarCADDocumentoLegalMasivosDAO procesoCCCCargarCADDocumentoLegalMasivosDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADDocumentoLegalMasivosService#obtenerPathUploadCADDocumentoLegalMasivos(java.util.Map)
	 */
	public String obtenerPathUploadCADDocumentoLegalMasivos(Map datos) {
		return procesoCCCCargarCADDocumentoLegalMasivosDAO.obtenerPathUploadCADDocumentoLegalMasivos(datos);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADDocumentoLegalMasivosService#deleteTablasCargaCADDocumentoLegalMasivos(java.util.Map)
	 */
	public void deleteTablasCargaCADDocumentoLegalMasivos(Map datos) {
		this.procesoCCCCargarCADDocumentoLegalMasivosDAO.deleteTablasCargaCADDocumentoLegalMasivos(datos);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADDocumentoLegalMasivosService#executeValidarCADDocumentoLegalMasivos(java.util.Map)
	 */
	public void executeValidarCADDocumentoLegalMasivos(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String extensionArchivo = (String)criteria.get("extensionArchivo");
		
		log.debug("JFA Inicia Procesar Archivo Excel");				
		
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
			procesarArchivoExcel(directorioTemporal, nombreArchivo, criteria);
		
		log.debug("JFA Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoCCCCargarCADDocumentoLegalMasivosDAO.executeValidarCADDocumentoLegalMasivos(criteria);			

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
			EstructuraCADDocumentoLegalMasivos estructura = new EstructuraCADDocumentoLegalMasivos();
			fila = fila + 1;
			
			log.debug("Carga Documento Legal Masivo mapRow--- "+fila+" - "+mapRow.toString());

				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 1 )
						estructura.setCodigoConsultora(((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length()));	
					else estructura.setCodigoConsultora((String)mapRow.get("0"));
				else estructura.setCodigoConsultora(null);
				
				
				if (StringUtils.isNotEmpty((String)mapRow.get("1")) )
					if (((String)mapRow.get("1")).length() > 1 )
						estructura.setNumeroBoletaDespachoReferencia(((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length()));	
					else estructura.setNumeroBoletaDespachoReferencia((String)mapRow.get("1"));
				else estructura.setNumeroBoletaDespachoReferencia(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("2")) )
					if (((String)mapRow.get("2")).length() > 1 )
						estructura.setCodigoProducto(((String)mapRow.get("2")).substring(0,((String)mapRow.get("2")).length()));	
					else estructura.setCodigoProducto((String)mapRow.get("2"));
				else estructura.setCodigoProducto(null);
				
				
				if (StringUtils.isNotEmpty((String)mapRow.get("3")) )										   
					if (((String)mapRow.get("3")).length() > 1 ) {
																								
						String s = (String)mapRow.get("3");
						s = s.replace(",", ".");						
						estructura.setImporteMovimientoValidacion(((String)mapRow.get("3")).substring(0,((String)mapRow.get("3")).length()));
						//estructura.setImporteMovimiento(Double.parseDouble(s));
						
						try {						
							estructura.setImporteMovimiento(Double.parseDouble(s));							
						}
						catch (Exception ex) {					    
							estructura.setImporteMovimiento(0.00);							
						};
					}
				
					else estructura.setImporteMovimiento(Double.parseDouble((String)(mapRow.get("3"))));
				else estructura.setImporteMovimiento(null);
				
				
				
				estructura.setNumeroLote((String)(datos.get("numeroLote")));												
				estructura.setCodigoUsuario((String)(datos.get("codigoUsuario")));
				estructura.setFila(fila);
																												
				//insertamos en BD la estructura CAD Masivos recuperada del Excel
				procesoCCCCargarCADDocumentoLegalMasivosDAO.insertEstructuraCADDocumentoLegalMasivos(estructura);
			
		}
	
	excelUtil.cerrar();
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADDocumentoLegalMasivosService#getErroresCargaCADDocumentoLegalMasivos()
	 */
	public List getErroresCargaCADDocumentoLegalMasivos(Map datos){
		return procesoCCCCargarCADDocumentoLegalMasivosDAO.getErroresCargaCADDocumentoLegalMasivos(datos);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADDocumentoLegalMasivosService#executeProcesarCADDocumentoLegalMasivos(java.util.Map)
	 */
	public void executeProcesarCADDocumentoLegalMasivos(Map datos){		
		procesoCCCCargarCADDocumentoLegalMasivosDAO.executeProcesarCADDocumentoLegalMasivos(datos);
	}
}
