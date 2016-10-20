/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.SubprocesoImpresion;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionAbstractService;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionLaserServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("spisicc.procesoImpresionLaserService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoImpresionLaserServiceImpl extends BaseProcesoImpresionAbstractService {

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService#createArchivosSalida(biz.belcorp.ssicc.model.ProcesoImpresion, java.util.Map)
     */
    protected File[] createArchivosSalida(ProcesoImpresion proceso, Map params) {
        log.debug("Dentro del mtodo 'createArchivosSalida'");

        File[] archivosSalida = new File[0];
        List archivosSalidaList = new ArrayList();
        File[] archivosProceso = getArchivosProceso(proceso, params);
        
        // Ordenamos los archivos en base al tamao
        if(archivosProceso != null && archivosProceso.length > 0) {
        	Arrays.sort(archivosProceso, new FileSizeComparator());
        }
        
        List subprocesos = proceso.getSubprocesosImpresion();
        if(subprocesos == null) {
            throw new ServiceException("No existe ningun subproceso asociado al proceso de impresion laser");
        }
        SubprocesoImpresion subproceso = (SubprocesoImpresion)subprocesos.get(0);
        String nombreArchivo = subproceso.getNombreArchivo();
        
        // Indicador de Generacion de Boleta de Despacho en un archivo independiente
        String indicadorBoletaIndependiente = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "indicadorBoletaIndependiente");

        // Valor del nombre del segundo archivo en caso el parametro anterior sea S
        String nombreArchivoBoletaIndependiente = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "nombreArchivoBoletaIndependiente");
        
        // En caso de generarse la boleta en un archivo independiente
        // validamos que existan los otros parametros necesarios
        if(StringUtils.equals(indicadorBoletaIndependiente, Constants.SI)) {
            if(StringUtils.isBlank(nombreArchivoBoletaIndependiente)) {
                throw new ServiceException(
                        "No existe el parametro 'nombreArchivoBoletaIndependiente', " +
                        "configure este parametro si se va a generar la boleta de despacho en forma independiente");
            }
            if(StringUtils.equals(nombreArchivo, nombreArchivoBoletaIndependiente)) {
                throw new ServiceException(
                        "El nombre del archivo del subproceso no puede tener el mismo valor que el parametro de impresion 'nombreArchivoBoletaIndependiente'");
            }
        }

        // Indicador de Generacion de Boleta de Recojo en un archivo independiente
        String indicadorBoletaRecojoIndependiente = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "indicadorBoletaRecojoIndependiente");

        // Valor del nombre del segundo archivo en caso el parametro anterior sea S
        String nombreArchivoBoletaRecojoIndependiente = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "nombreArchivoBoletaRecojoIndependiente");
        
        // En caso de generarse la boleta de recojo en un archivo independiente
        // validamos que existan los otros parametros necesarios
        if(StringUtils.equals(indicadorBoletaRecojoIndependiente, Constants.SI)) {
            if(StringUtils.isBlank(nombreArchivoBoletaRecojoIndependiente)) {
                throw new ServiceException(
                        "No existe el parametro 'nombreArchivoBoletaRecojoIndependiente', " +
                        "configure este parametro si se va a generar la boleta de recojo en forma independiente");
            }
            if(StringUtils.equals(nombreArchivo, nombreArchivoBoletaRecojoIndependiente)) {
                throw new ServiceException(
                        "El nombre del archivo del subproceso no puede tener el mismo valor que el parametro de impresion 'nombreArchivoBoletaRecojoIndependiente'");
            }
            if(StringUtils.equals(nombreArchivoBoletaIndependiente, nombreArchivoBoletaRecojoIndependiente)) {
                throw new ServiceException(
                        "El nombre del parametro 'nombreArchivoBoletaIndependiente' no puede tener el mismo valor que el parametro de impresion 'nombreArchivoBoletaRecojoIndependiente'");
            }
        }

        // Valor del nombre del segundo archivo en caso el parametro anterior sea S
        String nombreArchivoHojaPicado = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "nombreArchivoHojaPicado");
        
        // Si no existe el parametro usamos el valor de la constante
        if(StringUtils.isBlank(nombreArchivoHojaPicado)) {
        	nombreArchivoHojaPicado = Constants.PROCESO_IMPRESION_NOMBRE_HOJA_PICADO;
        }
        
        // Valor del parametro que indica el numero de archivos a generar 
		String numeroArchivosSalida = procesoImpresionDAO
				.getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
						"numeroArchivosSalida");
		
		// Si no existe el parametro o no es un numero valido, le colocamos el valor 1 por defecto
		if(StringUtils.isBlank(numeroArchivosSalida) || !StringUtils.isNumeric(numeroArchivosSalida)) {
			numeroArchivosSalida = Constants.PROCESO_IMPRESION_NRO_ARCHIVOS_SALIDA;
		}
    	
        // Indicador de carga de paquete desde la base de datos
        String indicadorCargaPaqdocBaseDatos = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "indicadorCargaPaqdocBaseDatos");
        // Indicador de carga de paquete desde la base de datos
        String indicadorGenerarCuponLaser = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "indicadorGenerarCuponLaser");
        try {

        	// Como paso inicial, eliminamos la informacion de procesos anteriores
        	procesoImpresionDAO.executeEliminarPaquetesDocumentarios(null);
        	
        	// Si el parametro de carga desde base de datos esta activo . . .
        	if(StringUtils.equals(indicadorCargaPaqdocBaseDatos, Constants.SI)) {
        		// Cargamos el paquete documentario desde la misma base de datos
        		procesoImpresionDAO.executeCargarBlobPaqueteDocumentarioSiCC(params);
        		
        		// Si el parametro de generacion de cupones esta activo...
        		if(StringUtils.equals(indicadorGenerarCuponLaser, Constants.SI)) {
            		procesoImpresionDAO.executeGenerarCuponPago(params);
        		}
        	}
        	// en caso contrario leemos los archivos
        	else {
	            // Iteramos sobre los archivos a cargar / compaginar
	            boolean paqdocSiCC = false;
	            for(int i = 0; i < archivosProceso.length; i++) {
	            	File archivoProceso = archivosProceso[i];
	            	
	            	// Si el nombre del archivo iterado contiene el valor del parametro
	            	if(StringUtils.contains(archivoProceso.getName(), nombreArchivoHojaPicado)) {
	            		// Cargamos el archivo de Hoja de Picado
	            		if(log.isDebugEnabled()) {
	            			log.debug("Archivo de Hoja de Picado");
	                        log.debug(archivoProceso.getName());
	                        log.debug(archivoProceso.getParent());
	            		}
	            		// Si el archivo enviado esta comprimido
	            		if(StringUtils.containsIgnoreCase(archivoProceso.getName(), ".ZIP")) {
	            			
	                		if(log.isDebugEnabled()) {
	                			log.debug("El archivo esta comprimido, procediendo a descomprimir...");
	                		}
	            			// Descomprimimos el archivo en la misma carpeta
							ZipUtil.unzipFile(archivoProceso.getAbsolutePath(),
									StringUtils.substringBeforeLast(archivoProceso
											.getAbsolutePath(), archivoProceso
											.getName()));
	            			
	            			// Se asume que el nombre del archivo descomprimido coincide en el nombre
							String nombreArchivoUnzip = StringUtils.substringBeforeLast(StringUtils.upperCase(archivoProceso.getName()), ".ZIP") + ".TXT";
	            			File archivoUnzip = new File(archivoProceso.getParent(), nombreArchivoUnzip);
	            			
	            			if(log.isDebugEnabled()) {
	            				log.debug("nombreArchivoUnzip: " + nombreArchivoUnzip);
	            				log.debug("archivoUnzip: " + archivoUnzip.getAbsolutePath());
	            			}
	            			// Validamos la existencia del archivo descomprimido
	            			if(!archivoUnzip.exists()) {
	                            throw new ServiceException(
	                            "El nombre del archivo comprimido de hoja de picado no cumple con las reglas establecidas.");
	            			}
	            			
	            			// Cargamos el archivo de hoja de picado
	                        params.put("nombreArchivo", nombreArchivoUnzip);
	                        params.put("directorio", archivoProceso.getParent());
	                		procesoImpresionDAO.executeCargarHojaPicado(params);
	                		
	                		// Eliminamos el archivo descomprimido
	                		try {
	                    		if(archivoUnzip.exists()) {
	                        		archivoUnzip.delete();
	                    		}
	                		}
	                		catch(SecurityException se) {
	                			log.error(se.getMessage(), se);
	                            throw new ServiceException(
	                            "No es posible eliminar el archivo descomprimido de hoja de picado, revisar los permisos sobre el sistema de archivo");
	                		}
	            		}
	            		else {
	                        params.put("nombreArchivo", archivoProceso.getName());
	                        params.put("directorio", archivoProceso.getParent());
	                		procesoImpresionDAO.executeCargarHojaPicado(params);
	            		}
	            	}
	            	// En caso contrario es uno de los archivos PAQDOC
	            	// Se considera que el primer archivo corresponde al 
	            	// paquete documentario SiCC
	            	else {
	            		if(!paqdocSiCC) {
	                        // Cargamos el contenido del archivo en la base de datos
	                        // Para ello guardamos en el map los parametros a enviar
	                        if(log.isDebugEnabled()) {
	                            log.debug("Archivo de Paquete Documentario:");
	                            log.debug(archivoProceso.getName());
	                            log.debug(archivoProceso.getParent());
	                        }
	                        // Cargamos el archivo del paquete documentario generado por SiCC
	                        params.put("nombreArchivo", archivoProceso.getName());
	                        params.put("directorio", archivoProceso.getParent());
	                        procesoImpresionDAO.executeCargarPaqueteDocumentarioSiCC(params);
	                        paqdocSiCC = true;
	            		}
	            		else {
	                        if(log.isDebugEnabled()) {
	                            log.debug("Archivo de Cupon:");
	                            log.debug(archivoProceso.getName());
	                            log.debug(archivoProceso.getParent());
	                        }
	                        params.put("nombreArchivo", archivoProceso.getName());
	                        params.put("directorio", archivoProceso.getParent());
	                        // Cargamos el archivo del paquete documentario de cupones
	                        procesoImpresionDAO.executeCargarCuponPago(params);
	            		}
	            	}
	            }
        	}
            
            // Ejecutamos el proceso de compaginacion
            procesoImpresionDAO.executeFusionarPaqueteDocumentario(params);
            
            // Colocamos los parametros para generar el archivo (TXT)
            params.put("nombreArchivo", nombreArchivo);
            params.put("directorio", proceso.getDirectorioDestino());
            params.put("numeroDocumento", Constants.PROCESO_IMPRESION_NRO_PAQUETE_NORMAL);
            params.put("numeroArchivosSalida", numeroArchivosSalida);
            procesoImpresionDAO.executeGenerarPaqueteDocumentario(params);
            
            // Agregamos el(los) archivo(s) de salida principal(es) a la lista
            archivosSalidaList.addAll(getFilesArchivosSalida(nombreArchivo,
					proceso.getDirectorioDestino(), numeroArchivosSalida));
            
            // Validamos si es necesario generar el archivo de boletas de despacho independiente
            // en base al valor del Parametro de Impresion.
            if(StringUtils.equals(indicadorBoletaIndependiente, Constants.SI)) {
                // Generamos el segundo archivo de impresion laser
                params.put("nombreArchivo", nombreArchivoBoletaIndependiente);
                params.put("directorio", proceso.getDirectorioDestino());
                params.put("numeroDocumento", Constants.PROCESO_IMPRESION_NRO_PAQUETE_BOLETA_DESPACHO);
                params.put("numeroArchivosSalida", numeroArchivosSalida);
                procesoImpresionDAO.executeGenerarPaqueteDocumentario(params);
                
                // Agregamos el(los) archivo(s) de boleta de despacho a la lista
				archivosSalidaList.addAll(getFilesArchivosSalida(
						nombreArchivoBoletaIndependiente, proceso
								.getDirectorioDestino(), numeroArchivosSalida));
            }
            
            // Validamos si es necesario generar el archivo de boletas de recojo independiente
            // en base al valor del Parametro de Impresion.
            if(StringUtils.equals(indicadorBoletaRecojoIndependiente, Constants.SI)) {
                // Generamos el segundo archivo de impresion laser
                params.put("nombreArchivo", nombreArchivoBoletaRecojoIndependiente);
                params.put("directorio", proceso.getDirectorioDestino());
                params.put("numeroDocumento", Constants.PROCESO_IMPRESION_NRO_PAQUETE_BOLETA_RECOJO);
                // Para el caso de boletas de recojo, solo generamos un archivo
                params.put("numeroArchivosSalida", Constants.PROCESO_IMPRESION_NRO_ARCHIVOS_SALIDA);
                procesoImpresionDAO.executeGenerarPaqueteDocumentario(params);
                
                // Agregamos el archivo de boleta de despacho a la lista
                File archivoSalidaBoletaRecojo = new File(proceso
                        .getDirectorioDestino(),
                        nombreArchivoBoletaRecojoIndependiente);
                archivosSalidaList.add(archivoSalidaBoletaRecojo);
            }
            
            // Hacemos la conversion de list a array
            archivosSalida = (File[])archivosSalidaList.toArray(new File[0]);
            
            // Actualizamos los indicadores de impresion de las notas de credito y debito laser
            procesoImpresionDAO.executeActualizarIndicadorImpresionNotasCredito(params);
            procesoImpresionDAO.executeActualizarIndicadorImpresionNotasDebito(params);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        
        return archivosSalida;
    }
    
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService#validateArchivosOrigen(java.io.File[])
	 */
	@Override
	protected void validateArchivosOrigen(File[] archivosOrigen) {
        // Indicador de carga de paquete desde la base de datos
        String indicadorCargaPaqdocBaseDatos = procesoImpresionDAO
                .getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,
                        "indicadorCargaPaqdocBaseDatos");
        
        // Si el parametro no esta activo se valida que existan archivos a cargar
        if (!StringUtils.equals(indicadorCargaPaqdocBaseDatos, Constants.SI)) {
            if (archivosOrigen == null || archivosOrigen.length == 0) {
                throw new ServiceException(
                        "No existen archivos de impresion a procesar.");
            }
        }
	}

	protected List getFilesArchivosSalida(String nombreArchivo, String nombreDirectorio, String numeroArchivosSalida) {
    	List filesArchivosSalida = new ArrayList();
    	int contador = Integer.parseInt(numeroArchivosSalida);
    	String separador = ".";
    	
    	if(contador > 1) {
    		String nombre = StringUtils.substringBeforeLast(nombreArchivo, separador);
    		String extension = StringUtils.substringAfterLast(nombreArchivo, separador);
    		if(log.isDebugEnabled()) {
    			log.debug("nombre: " + nombre);
    			log.debug("extension: " + extension);
    		}
    		
    		for(int i = 1; i <= contador; i++) {
    			String nombreCompleto = nombre + "_" + i + separador + extension;
    			if(log.isDebugEnabled()) {
    				log.debug("nombreCompleto: " + nombreCompleto);
    			}
    			File file = new File(nombreDirectorio, nombreCompleto);
    			filesArchivosSalida.add(file);
    		}
    	}
    	else {
    		File file = new File(nombreDirectorio, nombreArchivo);
    		filesArchivosSalida.add(file);
    	}
    	
    	return filesArchivosSalida;
    }

}

/**
 * 
 * Comparator usado para ordenar los objetos File de un array en base a la fecha
 * de ultima modificacion de los mismos.
 * <p>
 * <a href="FileLastModifiedComparator.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
class FileLastModifiedComparator implements Comparator {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
        return (int) (((File) o1).lastModified() - ((File) o2).lastModified());
    }

}

/**
 * 
 * Comparator usado para ordenar los objetos File de un array en base al tamao de los archivos
 * en orden descendente.
 * <p>
 * <a href="FileLastModifiedComparator.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
class FileSizeComparator implements Comparator {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
    	File f1 = (File) o1;
    	File f2 = (File) o2;
    	
    	long l1 = 0;
    	long l2 = 0;
    	
    	if (f1.isFile()) {
    		l1 = f1.length();
    	}
    	if (f2.isFile()) {
    		l2 = f2.length();
    	}
    	
        return (int) (l2 - l1);
    }

}
