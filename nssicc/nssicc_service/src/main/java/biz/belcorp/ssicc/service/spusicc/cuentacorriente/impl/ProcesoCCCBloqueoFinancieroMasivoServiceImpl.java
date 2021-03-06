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
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCBloqueoFinancieroMasivoDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraBloqueoFinancieroMasivo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCBloqueoFinancieroMasivoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author pejflorencio
 *
 */
@Service("spusicc.procesoCCCBloqueoFinancieroMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCBloqueoFinancieroMasivoServiceImpl extends BaseService implements ProcesoCCCBloqueoFinancieroMasivoService {
	
	@Resource(name = "spusicc.procesoCCCBloqueoFinancieroMasivoDAO")
	ProcesoCCCBloqueoFinancieroMasivoDAO procesoCCCBloqueoFinancieroMasivoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCBloqueoFinancieroMasivoService#obtenerPathUploadBloqueoFinancieroMasivo(java.util.Map)
	 */
	public String obtenerPathUploadBloqueoFinancieroMasivo(Map datos) {
		return procesoCCCBloqueoFinancieroMasivoDAO.obtenerPathUploadBloqueoFinancieroMasivo(datos);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCBloqueoFinancieroMasivoService#deleteTablasBloqueoFinancieroMasivo(java.util.Map)
	 */
	public void deleteTablasBloqueoFinancieroMasivo(Map datos) {
		this.procesoCCCBloqueoFinancieroMasivoDAO.deleteTablasBloqueoFinancieroMasivo(datos);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarBloqueoFinancieroMasivoService#executeValidarBloqueoFinancieroMasivo(java.util.Map)
	 */
	public void executeValidarBloqueoFinancieroMasivo(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String extensionArchivo = (String)criteria.get("extensionArchivo");
		
		log.debug("JFA Incia Procesar Archivo Excel");				
		
		if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
			procesarArchivoExcel(directorioTemporal, nombreArchivo, criteria);
		
		log.debug("JFA Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoCCCBloqueoFinancieroMasivoDAO.executeValidarBloqueoFinancieroMasivo(criteria);			

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
			EstructuraBloqueoFinancieroMasivo estructura = new EstructuraBloqueoFinancieroMasivo();
			fila = fila + 1;
			
			log.debug("Carga Documento Legal Masivo mapRow--- "+fila+" - "+mapRow.toString());

				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 1 )
						estructura.setCodigoConsultora(((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length()));	
					else estructura.setCodigoConsultora((String)mapRow.get("0"));
				else estructura.setCodigoConsultora(null);
				
				estructura.setCodigoTipoAccion((String)(datos.get("tipoAccion")));	
				estructura.setCodigoTipoBloqueo((String)(datos.get("tipoBloqueo")));
				estructura.setValorMotivoBloqueo((String)(datos.get("motivoBloqueo")));
				estructura.setNumeroLote((String)(datos.get("numeroLote")));												
				estructura.setCodigoUsuario((String)(datos.get("codigoUsuario")));
				estructura.setFila(fila);
																												
				//insertamos en BD la estructura CAD Masivos recuperada del Excel
				procesoCCCBloqueoFinancieroMasivoDAO.insertEstructuraBloqueoFinancieroMasivo(estructura);
			
		}
	
	excelUtil.cerrar();
	}
			
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCBloqueoFinancieroMasivoService#getErroresBloqueoFinancieroMasivo(java.util.Map)
	 */
	public List getErroresBloqueoFinancieroMasivo(Map datos){
		return procesoCCCBloqueoFinancieroMasivoDAO.getErroresBloqueoFinancieroMasivo(datos);
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCBloqueoFinancieroMasivoService#executeProcesarBloqueoFinancieroMasivo(java.util.Map)
	 */
	public void executeProcesarBloqueoFinancieroMasivo(Map datos){		
		procesoCCCBloqueoFinancieroMasivoDAO.executeProcesarBloqueoFinancieroMasivo(datos);
	}
}
