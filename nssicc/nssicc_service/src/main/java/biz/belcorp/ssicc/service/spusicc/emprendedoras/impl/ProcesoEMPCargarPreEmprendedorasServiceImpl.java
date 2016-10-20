package biz.belcorp.ssicc.service.spusicc.emprendedoras.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.ProcesoEMPCargarPreEmprendedorasDAO;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.model.EstructuraEMPPreEmprendedora;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.ProcesoEMPCargarPreEmprendedorasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */
@Service("spusicc.procesoEMPCargarPreEmprendedorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEMPCargarPreEmprendedorasServiceImpl extends BaseService implements ProcesoEMPCargarPreEmprendedorasService {
	
	@Resource(name = "spusicc.procesoEMPCargarPreEmprendedorasDAO")
	ProcesoEMPCargarPreEmprendedorasDAO procesoEMPCargarPreEmprendedorasDAO;


	
	public List getProgramas(){
		return procesoEMPCargarPreEmprendedorasDAO.getProgramas();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoEMPCargarPreEmprendedorasService#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return procesoEMPCargarPreEmprendedorasDAO.obtenerPathUpload(datos);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoEMPCargarPreEmprendedorasService#deleteTablasCargaPreEmprendedoras(java.util.Map)
	 */
	public void deleteTablasCargaPreEmprendedoras() {
		this.procesoEMPCargarPreEmprendedorasDAO.deleteTablasCargaPreEmprendedoras();		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.service.ProcesoEMPCargarPreEmprendedorasService#executeValidarPreEmprendedoras(java.util.Map)
	 */
	public void executeValidarPreEmprendedoras(Map datos) throws Exception {
		String directorioTemporal = (String)datos.get("directorioTemporal");
		String nombreArchivo = (String)datos.get("nombreArchivo");
		String extensionArchivo = (String)datos.get("extensionArchivo");	
		
		log.debug("JFA Incia Procesar Archivo Excel");
		
		if ((extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL) || extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL_2007) ))
			procesarArchivoExcel(directorioTemporal, nombreArchivo,datos);
		
		log.debug("JFA Finalizando Procesar Archivo Excel");
		
		//Realizamos el proceso de Validacion de los datos ingresados
		procesoEMPCargarPreEmprendedorasDAO.executeValidarPreEmprendedoras(datos);			

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
			EstructuraEMPPreEmprendedora estructura = new EstructuraEMPPreEmprendedora();
			
			fila = fila + 1;
			
			log.debug("mapRow--- "+fila+" - "+mapRow.toString());

				if (StringUtils.isNotEmpty((String)mapRow.get("0")) )
					if (((String)mapRow.get("0")).length() > 1 )
						estructura.setPeriodoInicio(((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length()));	
					else estructura.setPeriodoInicio((String)mapRow.get("0"));
				else estructura.setPeriodoInicio(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("1")) )
					if (((String)mapRow.get("1")).length() > 1 )
						estructura.setCodigoConsultora(((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length()));	
					else estructura.setCodigoConsultora((String)mapRow.get("1"));
				else estructura.setCodigoConsultora(null);
														
				if (StringUtils.isNotEmpty((String)mapRow.get("2")) )
					if (((String)mapRow.get("2")).length() > 1 )
						estructura.setCodigoZona(((String)mapRow.get("2")).substring(0,((String)mapRow.get("2")).length()));	
					else estructura.setCodigoZona((String)mapRow.get("2"));
				else estructura.setCodigoZona(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("3")) )
					if (((String)mapRow.get("3")).length() > 1 )
						estructura.setCodigoSeccion(((String)mapRow.get("3")).substring(0,((String)mapRow.get("3")).length()));	
					else estructura.setCodigoSeccion((String)mapRow.get("3"));
				else estructura.setCodigoSeccion(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("4")) )										   
					if (((String)mapRow.get("4")).length() > 1 ) {
																								
						String s = (String)mapRow.get("4");
						estructura.setPedidosBaseGDValidacion(((String)mapRow.get("4")).substring(0,((String)mapRow.get("4")).length()));
						
						try {						
							estructura.setPedidosBaseGD(Integer.parseInt(s));							
						}
						catch (Exception ex) {					    
							estructura.setPedidosBaseGD(0);							
						}
					}				
					else 						
						estructura.setPedidosBaseGDValidacion(((String)mapRow.get("4")).substring(0,((String)mapRow.get("4")).length()));																																		
				else estructura.setPedidosBaseGD(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("5")) )										   
					if (((String)mapRow.get("5")).length() > 1 ) {						
						String s = (String)mapRow.get("5");
						s = s.replace(",", ".");						
						estructura.setVentaBaseGDValidacion(((String)mapRow.get("5")).substring(0,((String)mapRow.get("5")).length()));
					
						try {						
							estructura.setVentaBaseGD(Double.parseDouble(s));							
						}
						catch (Exception ex) {					    
							estructura.setVentaBaseGD(0.00);							
						}
					}				
					else 
						estructura.setVentaBaseGDValidacion(((String)mapRow.get("5")).substring(0,((String)mapRow.get("5")).length()));												
				else estructura.setVentaBaseGD(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("6")) )										   
					if (((String)mapRow.get("6")).length() > 1 ) {
																								
						String s = (String)mapRow.get("6");
						estructura.setActivasBaseGDValidacion(((String)mapRow.get("6")).substring(0,((String)mapRow.get("6")).length()));
						
						try {						
							estructura.setActivasBaseGD(Integer.parseInt(s));							
						}
						catch (Exception ex) {					    
							estructura.setActivasBaseGD(0);							
						}
					}				
					else 						
						estructura.setActivasBaseGDValidacion(((String)mapRow.get("6")).substring(0,((String)mapRow.get("6")).length()));																																		
				else estructura.setActivasBaseGD(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("7")) )
					if (((String)mapRow.get("7")).length() > 1 )
						estructura.setNivelEjecutiva(((String)mapRow.get("7")).substring(0,((String)mapRow.get("7")).length()));	
					else estructura.setNivelEjecutiva((String)mapRow.get("7"));
				else estructura.setNivelEjecutiva(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("8")) )										   
					if (((String)mapRow.get("8")).length() > 1 ) {						
						String s = (String)mapRow.get("8");
						s = s.replace(",", ".");						
						estructura.setPorcentajeComisionValidacion(((String)mapRow.get("8")).substring(0,((String)mapRow.get("8")).length()));
					
						try {						
							estructura.setPorcentajeComision(Double.parseDouble(s));							
						}
						catch (Exception ex) {					    
							estructura.setPorcentajeComision(0.00);							
						}
					}				
					else 
						estructura.setPorcentajeComisionValidacion(((String)mapRow.get("8")).substring(0,((String)mapRow.get("8")).length()));												
				else estructura.setPorcentajeComision(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("9")) )										   
					if (((String)mapRow.get("9")).length() > 1 ) {						
						String s = (String)mapRow.get("9");
						s = s.replace(",", ".");						
						estructura.setPspValidacion(((String)mapRow.get("9")).substring(0,((String)mapRow.get("9")).length()));
					
						try {						
							estructura.setPsp(Double.parseDouble(s));							
						}
						catch (Exception ex) {					    
							estructura.setPsp(0.00);							
						}
					}				
					else 
						estructura.setPspValidacion(((String)mapRow.get("9")).substring(0,((String)mapRow.get("9")).length()));												
				else estructura.setPsp(null);
				
				if (StringUtils.isNotEmpty((String)mapRow.get("10")) )										   
					if (((String)mapRow.get("10")).length() > 1 ) {						
						String s = (String)mapRow.get("10");
						s = s.replace(",", ".");						
						estructura.setVentaBaseIncentivosValidacion(((String)mapRow.get("10")).substring(0,((String)mapRow.get("10")).length()));
					
						try {						
							estructura.setVentaBaseIncentivos(Double.parseDouble(s));							
						}
						catch (Exception ex) {					    
							estructura.setVentaBaseIncentivos(0.00);							
						}
					}				
					else 
						estructura.setVentaBaseIncentivosValidacion(((String)mapRow.get("10")).substring(0,((String)mapRow.get("10")).length()));												
				else estructura.setVentaBaseIncentivos(null);
												
				estructura.setCodigoUsuario((String)(datos.get("codigoUsuario")));
				estructura.setFila(fila);
				
				//insertamos en BD la estructura pre emprendedora recuperada del Excel
				procesoEMPCargarPreEmprendedorasDAO.insertEstructuraEMPPreEmprendedora(estructura);				
			
		}
	
	excelUtil.cerrar();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.FuenteVentasService#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargaPreEmprendedoras(){
		return procesoEMPCargarPreEmprendedorasDAO.getErroresCargaPreEmprendedoras();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarCADMasivosService#executeProcesarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeProcesarPreEmprendedoras(Map datos){		
		procesoEMPCargarPreEmprendedorasDAO.executeProcesarPreEmprendedoras(datos);
	}
	
}