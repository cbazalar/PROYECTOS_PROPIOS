package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service de la Interfaz Enviar Control de Cierres del DATAMART
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius</a>
 */
@Service("sisicc.interfazDATEnviarControlCierresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarControlCierresServiceImpl extends
		BaseInterfazSalidaAbstractService {
	
	/**
	 * Obtiene la data para la Interfaz Enviar Control de Cierres del DATAMART.
	 * 
	 * @param queryParams
	 *            parametros del query.
	 * @return List con Maps de las filas resultado.
	 * @throws InterfazException
	 */
	public List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List listControlCierres = new ArrayList();
		try {			
			
			/* Obtengo listado de control de cierres */

			String[] codigoRegion = (String[]) queryParams.get("codigoRegion");
			if (codigoRegion != null && codigoRegion.length > 0) {
				String codigoTipoCierre = queryParams.get("codigoTipoCierre")
						.toString();
				if (codigoTipoCierre
						.equals(Constants.CODIGO_TIPO_CIERRE_REGION)
						|| codigoTipoCierre
								.equals(Constants.CODIGO_TIPO_CIERRE_ZONA)) {
					listControlCierres = this.interfazSiCCDAO
							.getInterfazDATEnviarControlCierresZonasRegiones(queryParams);
			} else { //caso de periodos
					listControlCierres = this.interfazSiCCDAO
							.getInterfazDATEnviarControlCierresPeriodos(queryParams);
			}			
						
			if ( listControlCierres.size() > 0) {
						
				Map controlCierre = new HashMap();
				String textoArchivoLog = "";
				String codigosError = "";
				boolean todoOk = true;		
				
				Iterator it = listControlCierres.iterator();						
				while (it.hasNext()) {
					Map row = (Map) it.next();
						/*
						 * si existen resultados <>OK se obtiene la relacion de
						 * esos resultados
						 */
						if (!(row.get("resultado").toString())
								.equals(Constants.OK)) {

							/*
							 * si se trata de zonas y regiones, se obtiene la
							 * relacion de zonas o regiones <> OK
							 */
							if (codigoTipoCierre
									.equals(Constants.CODIGO_TIPO_CIERRE_REGION)
									|| codigoTipoCierre
											.equals(Constants.CODIGO_TIPO_CIERRE_ZONA)) {
								if (codigoTipoCierre
										.equals(Constants.CODIGO_TIPO_CIERRE_REGION)) {
									codigosError += row.get("codigoRegion")
											.toString()
											+ ",";
							}else {
									codigosError += row.get("codigoZona")
											.toString()
											+ ",";
							}
						} 
						todoOk = false;					
						
					} else {					
						//si llegue a un resultado OK salgo del bucle
						break;
					}
				}
				
					/*
					 * a trabajar el texto del archivo de log en el caso de
					 * resultados <> OK
					 */
				if (!todoOk) {					
						textoArchivoLog = construyeTextoErrorProcesosArchivoLog(
								queryParams, codigosError);
					if ( textoArchivoLog.length() > 0) {
							log
									.error("Error: se encontraron procesos que no terminaron OK");
							InterfazException ie = new InterfazException(
									textoArchivoLog);
							ie
									.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
						throw ie;
					}
					
				/* para el caso que todo esta OK */
				} else {
						/*
						 * solo para tipo de cierre Periodo se obtiene la
						 * relacion total de zonas y regiones activas y no
						 * eliminadas obtengo el nuevo listControlCierres
						 */
						if (codigoTipoCierre
								.equals(Constants.CODIGO_TIPO_CIERRE_PERIODO)) {
						listControlCierres = this.interfazSiCCDAO
								.getInterfazDATEnviarControlCierresPeriodosSalida(queryParams);
					}		
				}				
			
			/* en caso no se encuentren registros */	
			} else {
					/*
					 * se colocara un 'registro' para que sea colocado en el
					 * archivo de interfaz de salida
					 */
					log
							.error("No se encontraron registros, se procedera a escribir un log de error.");
					/*
					 * texto de salida en caso no se encuentren archivos:
					 * XXXXXXTbbbTbbbbbbTbTb donde 'T' son tabs y 'b' son
					 * espacios en blanco
					 */
				Map registro = new HashMap();
				registro.put("codigoPeriodo", "XXXXXX");
				registro.put("codigoRegion", "   ");
				registro.put("codigoZona", "      ");
				registro.put("flagImpresion", " ");
				registro.put("flagStatusFacturacion", " ");				
				listControlCierres.add(registro); 
				
			}				
			} else {

				/*
				 * se colocara un 'registro' para que sea colocado en el archivo
				 * de interfaz de salida
				 */
				log
						.error("No se encontraron registros, se procedera a escribir un log de error.");
				/*
				 * texto de salida en caso no se encuentren archivos:
				 * XXXXXXTbbbTbbbbbbTbTb donde 'T' son tabs y 'b' son espacios
				 * en blanco
				 */
				Map registro = new HashMap();
				registro.put("codigoPeriodo", "XXXXXX");
				registro.put("codigoRegion", "   ");
				registro.put("codigoZona", "      ");
				registro.put("flagImpresion", " ");
				registro.put("flagStatusFacturacion", " ");
				listControlCierres.add(registro);
			}
		} catch (InterfazException ie) {
			log.error(ie.getMessage());
			throw ie;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return listControlCierres;
	}

	/**
	 *  
	 * 
	 * @param Error
	 *            Excepcion a mostrar
	 * @return Retorna el texto para el log en caso hayan ocurrencia de procesos
	 *         con resultado <> OK
	 */
	public String construyeTextoErrorProcesosArchivoLog(Map queryParams,
			String codigosError) {
		if (log.isDebugEnabled())
			log
					.debug("Entering 'construyeTextoErrorProcesosArchivoLog' method");

		List listControlCierresProcError = null;
		String codigoTipoCierre = queryParams.get("codigoTipoCierre")
				.toString();
		String textoArchivoLog = "";
		if (codigoTipoCierre.equals(Constants.CODIGO_TIPO_CIERRE_REGION)
				|| codigoTipoCierre.equals(Constants.CODIGO_TIPO_CIERRE_ZONA)) {
		
			codigosError = StringUtils.substring(codigosError, 0, (codigosError
					.length() - 1)); // codigos separados por comas
			String[] listaCodigosError = StringUtils.split(codigosError, ",") ;  

			queryParams.put("listaCodigosError", listaCodigosError);
			listControlCierresProcError = this.interfazSiCCDAO
					.getInterfazDATEnviarControlCierresErrorZonasRegiones(queryParams);
			//a trabajar el texto del log de error
			Iterator it2 = listControlCierresProcError.iterator();						
			while (it2.hasNext()) {
				String line ="";
				Map row = (Map) it2.next();
				if (codigoTipoCierre
						.equals(Constants.CODIGO_TIPO_CIERRE_REGION)) {
					line = row.get("codigoRegion").toString() + "\t";
				}else {
					line = row.get("codigoZona").toString() + "\t";
				}
				line +=row.get("mensaje").toString() + "\t";
				line +=row.get("fechaCierre").toString() + "\t";
				line += row.get("secuencialCierre").toString()
						+ System.getProperty("line.separator");
				textoArchivoLog += line;

			}
			
		} else {	//si se trata de tipo de cierre por Periodos				

			listControlCierresProcError = this.interfazSiCCDAO
			.getInterfazDATEnviarControlCierresErrorCierrePeriodo(queryParams);	
			//a trabajar el texto del log de error
			Iterator it2 = listControlCierresProcError.iterator();						
			while (it2.hasNext()) {
				String line ="";
				Map row = (Map) it2.next();
				line = row.get("codigoPeriodo").toString() + "\t";
				line +=row.get("mensaje").toString()+ "\t";
				line +=row.get("fechaCierre").toString()+ "\t";
				line += row.get("secuencialCierre").toString()
						+ System.getProperty("line.separator");
				textoArchivoLog += line;
			}			
		}
		
		return textoArchivoLog;
	}	
	
	/**
	 * Metodo sobreescrito para generar un contenido personalizado del Log de
	 * Errores.
	 * 
	 * @param Error
	 *            Excepcion a mostrar
	 * @return Retorna contenido del Log a ser grabado en el archivo log
	 */
	public StringBuffer generarContenidoLog(InterfazException interfazException) {
		if (log.isDebugEnabled())
			log.debug("Entering 'generarContenidoLog' method");
		log.error("Logging e.getMessage=" + interfazException.getMessage());
		StringBuffer strlog = new StringBuffer();
		String separator = System.getProperty("line.separator");
		
		if ((interfazException.getCodigoError() != null)
				&& (interfazException.getCodigoError()
						.equals(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO))) {
		
			/*
			 * Mensaje generado para el caso de encontrar procesos con resltado <>
			 * OK
			 */
			if (interfazException.getMessage() != null) {
				strlog.append(interfazException.getMessage()+ separator);
			}			
		}else {
		
			/* Mensaje general a mostrar en el Log */
			strlog.append("TRAZA DEL ERROR:" + separator);
			strlog.append("================" + separator);			
			if (interfazException.getMessage() != null)
				strlog.append("Mensaje: " + interfazException.getMessage()
						+ separator);
			if (interfazException.getCause() != null) {
				strlog.append("Causa: " + separator);
				strlog.append(interfazException.getCause().toString()
						+ separator);
			}			
		}
			
		return strlog;
	}	
	
	
}