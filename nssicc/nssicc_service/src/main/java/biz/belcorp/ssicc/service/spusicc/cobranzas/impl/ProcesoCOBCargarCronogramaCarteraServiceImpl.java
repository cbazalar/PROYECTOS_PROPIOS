package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBCargarCronogramaCarteraDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCronogramaCartera;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargarCronogramaCarteraService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge FLorencio Arias</a>
 */
@Service("spusicc.procesoCOBCargarCronogramaCarteraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBCargarCronogramaCarteraServiceImpl extends BaseService implements ProcesoCOBCargarCronogramaCarteraService {
	
	@Resource(name="spusicc.procesoCOBCargarCronogramaCarteraDAO")
	ProcesoCOBCargarCronogramaCarteraDAO procesoCOBCargarCronogramaCarteraDAO;

			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoCOBCargarCronogramaCarteraService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return procesoCOBCargarCronogramaCarteraDAO.obtenerPathUpload(datos);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoCOBCargarCronogramaCarteraService#deleteTablasCargaPreEmprendedoras(java.util.Map)
	 */
	public void deleteTablasCargaCronogramaCartera(Map datos) {
		this.procesoCOBCargarCronogramaCarteraDAO.deleteTablasCargaCronogramaCartera(datos);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoCOBCargarCronogramaCarteraService#executeValidarPreEmprendedoras(java.util.Map)
	 */
	public void executeValidarCronogramaCartera(Map datos) throws Exception {
		String directorioTemporal = (String)datos.get("directorioTemporal");
		String nombreArchivo = (String)datos.get("nombreArchivo");
		String extensionArchivo = (String)datos.get("extensionArchivo");	
		
		log.debug("JFA Incia Procesar Archivo Excel");
		
		if ((extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL) || extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL_2007) ))
			procesarArchivoExcel(directorioTemporal, nombreArchivo,datos);
		
		log.debug("JFA Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoCOBCargarCronogramaCarteraDAO.executeValidarCronogramaCartera(datos);			

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
			EstructuraCronogramaCartera estructura = new EstructuraCronogramaCartera();
			
			fila = fila + 1;
			
			log.debug("mapRow--- "+fila+" - "+mapRow.toString());

				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 1 )
						estructura.setCodigoEtapaDeuda(((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length()));	
					else estructura.setCodigoEtapaDeuda((String)mapRow.get("0"));
				else estructura.setCodigoEtapaDeuda(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("1")) )
					if (((String)mapRow.get("1")).length() > 1 )
						estructura.setCodigoPeriodo(((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length()));	
					else estructura.setCodigoPeriodo((String)mapRow.get("1"));
				else estructura.setCodigoPeriodo(null);
														
				if (StringUtils.isNotEmpty((String)mapRow.get("2")) )
					if (((String)mapRow.get("2")).length() > 1 )
						estructura.setCodigoRegion(((String)mapRow.get("2")).substring(0,((String)mapRow.get("2")).length()));	
					else estructura.setCodigoRegion((String)mapRow.get("2"));
				else estructura.setCodigoRegion(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("3")) )
					if (((String)mapRow.get("3")).length() > 1 )
						estructura.setCodigoZona(((String)mapRow.get("3")).substring(0,((String)mapRow.get("3")).length()));	
					else estructura.setCodigoZona((String)mapRow.get("3"));
				else estructura.setCodigoZona(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("4")) )
					if (((String)mapRow.get("4")).length() > 1 )
						estructura.setFechaInicialGeneracionCartera(((String)mapRow.get("4")).substring(0,((String)mapRow.get("4")).length()));	
					else estructura.setFechaInicialGeneracionCartera((String)mapRow.get("4"));
				else estructura.setFechaInicialGeneracionCartera(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("5")) )
					if (((String)mapRow.get("5")).length() > 1 )
						estructura.setFechaFinalGeneracionCartera(((String)mapRow.get("5")).substring(0,((String)mapRow.get("5")).length()));	
					else estructura.setFechaFinalGeneracionCartera((String)mapRow.get("5"));
				else estructura.setFechaFinalGeneracionCartera(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("6")) )
					if (((String)mapRow.get("6")).length() > 1 )
						estructura.setFechaCierreCartera(((String)mapRow.get("6")).substring(0,((String)mapRow.get("6")).length()));	
					else estructura.setFechaCierreCartera((String)mapRow.get("6"));
				else estructura.setFechaCierreCartera(null);
				
				
				estructura.setNumeroLote((String)(datos.get("numeroLote")));
				estructura.setCodigoUsuario((String)(datos.get("codigoUsuario")));
				estructura.setFila(fila);
				
				//insertamos en BD la estructura del cronograma de cartera recuperada del Excel
				procesoCOBCargarCronogramaCarteraDAO.insertEstructuraCronogramaCartera(estructura);				
			
		}
	
	excelUtil.cerrar();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargarCronogramaCartera(){
		return procesoCOBCargarCronogramaCarteraDAO.getErroresCargaCronogramaCartera();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADMasivosService#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeProcesarCronogramaCartera(Map datos){		
		procesoCOBCargarCronogramaCarteraDAO.executeProcesarCronogramaCartera(datos);
	}
	
}