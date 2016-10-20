package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazAPERecepcionarClienteRepartoCatalogoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Diego Torres Loyola</a>
 */
@Service("sisicc.interfazDATRecepcionarClienteRepartoCatalogoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATRecepcionarClienteRepartoCatalogoServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazDATDAO")
	protected InterfazDATDAO interfazDATDAO;
    
	protected void addLine(List data, Map row) {
		data.add(row);
	}
	
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}
		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
		
		Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
		
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
				 
		try {
			
			/*5.a. Obtiene el tipo de cliente y el subtipo de cliente a utilizar en el registro de la clasificaci�n. 
			 * El sistema los ubicar� en la entidad Par�metros Interface, (codTipoClien y codSubtiClien respectivamente) 
			 * filtrando por el c�digo de la interface (DAT-171). */
			log.info("Obtiene parametros codTipoClien y codSubtiClien de la interfaz");				
			String strParamCodigoTipocliente = (String)interfazParams.getQueryParams().get("codTipoClien");
			String strParamCodigoSubTipoCliente = (String)interfazParams.getQueryParams().get("codSubtiClien");
			
			String []arrayCodigoTipocliente = StringUtils.split(strParamCodigoTipocliente, ",");
			String []arraycodigoSubTipoCliente = StringUtils.split(strParamCodigoSubTipoCliente, ",");
			
			
			/*5.c. Obtiene el c�digo de Clasificaci�n que representa a Reparto Cat�logo, en la entidad 
			 * Par�metros Interface (codClasi) filtrando por el c�digo de la interface (DAT-171)*/
			log.info("Obtiene parametro codClasi de la interfaz");	
			String strParamCodigoClasificacion = (String)interfazParams.getQueryParams().get("codClasi");
			String []arrayCodigoClasificacion = StringUtils.split(strParamCodigoClasificacion, ",");
			
			log.info("Obtiene parametro codTipoClasi de la interfaz");	
			String strParamCodigoTipoClasificacion = (String)interfazParams.getQueryParams().get("codTipoClasi");
			
			log.info("Obtiene parametro destLogErrores de la interfaz");	
			String strParamDestinoLogErrores = (String)interfazParams.getQueryParams().get("destLogErrores");
			

			/*El sistema detecta que no existen valores configurados para los campos 
			 * codTipoClien, codSubtiClien, codTipoClasi, codClasi o destLogErrores 
			 * Crea un nuevo registro en el Log de Errores, con la siguiente 
			 * descripci�n: �No existe valor para el par�metro� + Nombre del par�metro.*/
			if(StringUtils.isEmpty(strParamCodigoTipocliente)){
				log.debug("No existe valor para el par�metro codTipoClien");
				throw new InterfazException("No existe valor para el par�metro codTipoClien");
			}
			if(StringUtils.isEmpty(strParamCodigoSubTipoCliente)){
				log.debug("No existe valor para el par�metro codSubtiClien");
				throw new InterfazException("No existe valor para el par�metro codSubtiClien");
			}
			if(StringUtils.isEmpty(strParamCodigoClasificacion)){
				//Flujo alternativo 5.c
				log.debug("No existe valor para el par�metro codClasi");
				throw new InterfazException("No existe valor para el par�metro codClasi");
			}
			if(StringUtils.isEmpty(strParamCodigoTipoClasificacion)){
				log.debug("No existe valor para el par�metro codTipoClasi");
				throw new InterfazException("No existe valor para el par�metro codTipoClasi");
			}
			if(StringUtils.isEmpty(strParamDestinoLogErrores)){
				log.debug("No existe valor para el par�metro destLogErrores");
				throw new InterfazException("No existe valor para el par�metro destLogErrores");
			}
			
			
			/*5.e. El sistema eliminar� de la entidad Clasificaci�n Cliente TODOS los registros que cuenten con la clasificaci�n 
			 * obtenida en el paso 5c. De esta manera ning�na registro de dicha entidad que cuente con la(s) clasificaci�n(es)
			 *  amacenadas en la entidad Par�metros Interfaces.*/
			
			log.debug("Borrando registro de Clasificacion cliente con el codigo de clasificacion");
			
			HashMap paramsDelete = new HashMap();
			paramsDelete.put("itemOidClasificacion", Arrays.asList(arrayCodigoClasificacion));
			interfazDATDAO.deleteClasificacionClientePorOidClasificacion(paramsDelete);		
			
			/* Actualizando Clasificion Cliente - Clasificion Cliente */
			log.info("Actualizando Clasificion Cliente - Clasificion Cliente");
			for (int i = 0; i < data.size(); i++) {
			

				//Lineas de Archivo de Texto
				HashMap criteriaDetalle = (HashMap) data.get(i);  
				String codigoConsultora = (String)criteriaDetalle.get("codigoConsultora");
				String tipoClasificacion = (String)criteriaDetalle.get("tipoClasificacion");
				String anioCampana = (String)criteriaDetalle.get("anioCampana"); 

				try {
					
					
					//Flujo alternativo 5.b
					log.debug("Valida existencia tipo clasificacion");
					criteriaDetalle.put("paramCodigoTipoClasificacion", strParamCodigoTipoClasificacion);
					if(!interfazDATDAO.validarExistenciaTipoClasificacion(criteriaDetalle)){
						log.debug("Flujo alternativo 5.b");
						throw new Exception("Tipo Clasificaci�n" + strParamCodigoTipoClasificacion +  "no existe en Maestro de Tipos Clasificaci�n" );
					}
					
					
					
					/*5.b. Obtiene los ID�s del tipo de clasificaci�n que representa a �Reparto Cat�logo�. Esto lo realiza 
					 * encontrando el valor del par�metro  codTipoClasi en la entidad Par�metros Interface. Posteriormente 
					 * utiliza el c�digo para encontrar los ID�S del tipo mencionado, que existan en la entidad Tipo 
					 * Clasificaci�n. Se deben almacenar los ID�S encontrados pues cada ID estar� relacionado a un subtipo de cliente distinto.*/

					
					/*5.d. El sistema buscar� en la entidad Clasificaci�n, la clasificaci�n  Reparto Cat�logos Lbel 
					 * (c�digo en la parametr�a codClasi) y obtendr� el ID respectivo dependiendo de los Tipos de Clasificaci�n 
					 * almacenados en el par�metro codTipoClasi del punto 5b.*/
					
					log.debug("Obteniendo los oids Tipo clasificacion y clasificacion");
					
					HashMap params = new HashMap();
					
					params.put("itemsCodigoTipoCliente", Arrays.asList(arrayCodigoTipocliente));
					params.put("itemsCodigoSubtipoCliente", Arrays.asList(arraycodigoSubTipoCliente));
					params.put("paramCodigoTipoClasificacion", strParamCodigoTipoClasificacion);
					params.put("paramCodigoClasificacion", strParamCodigoClasificacion);
					
					String strOidTipoClasificacion = null;
					String strOidClasificacion =null;
					
					Map mapOids = interfazDATDAO.getOidsClasificacionTipoClasificacionPorParametrosInterfaz(params);
					if(mapOids != null) {
						strOidTipoClasificacion=reemplazarNulo(mapOids.get("oidTipoClasificacion"));
						strOidClasificacion=reemplazarNulo(mapOids.get("oidClasificacion"));											
					}	

					
					//Verifica que el c�digo de consultora exista en la entidad Maestro de Clientes
					log.debug("Verifica que el c�digo de consultora exista en la entidad Maestro de Clientes");
					if(!interfazDATDAO.validarCodigoConsultora(criteriaDetalle)){
						throw new Exception("Consultora " + codigoConsultora + " no existe en Maestro de Clientes.");
					}

					
					/*Obtiene los c�digos y los ID�s del tipo y subtipo de la consultora. Entidades Tipo Cliente y SubTipo Cliente*/ 
					log.debug("Obtiene codigos y ID's Tipo Cliente y SubTipo Cliente de la consultora");
					
					String strCodigoTipoCliente=null;
					String strCodigoSubTipocliente=null;
					String strOidTipoCliente=null;
					String strOidSubTipoCliente=null;
					Map map = interfazDATDAO.getTipoClienteSubTipoClientePorConsultora(criteriaDetalle);
					if(map != null) {
						strCodigoTipoCliente=reemplazarNulo(map.get("codigoTipoCliente"));
						strCodigoSubTipocliente=reemplazarNulo(map.get("codigoSubTipocliente"));
						strOidTipoCliente=reemplazarNulo(map.get("oidTipoCliente"));
						
						/*En la entidad Cliente Tipo Subtipo , el sistema obtendr� el ID_CLIENTE_TIPO_SUBTIPO por cada cliente*/
						strOidSubTipoCliente=reemplazarNulo(map.get("oidSubTipoCliente"));												
					}	
					
					
					/*Verifica que el c�digo de Tipo de Clasificaci�n de cada fila exista en la la entidad 
					 * Par�metros Interface (codTipoClasi) filtrando por el c�digo de la interface (DAT-171)*/
					log.debug("Verfica existencia de codigo 'Tipo de Clasificacion' en parametro codTipoclasi");
					if(!strParamCodigoTipoClasificacion.equals(tipoClasificacion)){
						
						/*El sistema detecta que no existe el C�digo de Tipo Clasificaci�n 
						 * de un registro del archivo en la entidad Parametro Interface 
						 * y crea un nuevo registro en el Log de Errores, con la siguiente descripci�n: 
						 * �Registro Nro.: �+ Nro. L�nea + �. Tipo Clasificaci�n� + C�digo Tipo clasificaci�n + � no existe en la parametr�a de la interface�.*/
						log.debug("Codigo tipo clasificacion no coincide con parametro de interface");
						throw new Exception("Tipo Clasificaci�n " + tipoClasificacion + " no existe en la parametr�a de la interface.");
					}		
					
					
					/*En la entidad Campa�a, el sistema obtiene el ID_PERI correspondiente 
					 * al c�digo de campa�a recibido en cada fila*/
					log.debug("obtiene el ID_PERI correspondiente al c�digo de campa�a");
					Integer oidCampanha = interfazDATDAO.validarCodigoCampanha(criteriaDetalle);
					if(oidCampanha==null){
						throw new Exception("Campa�a " + anioCampana + " no existe.");
					}
					
					
					
					/*El sistema verifica que el cliente no cuente, en la entidad Clasificaci�n Cliente, 
					 *con registro del tipo clasificaci�n cuyo c�digo se ubica en la entidad Par�metros Interface 
					 *(codTipoClasi) Si lo hubiese, se omite el registro. Caso contrario, el sistema registra 
					 *estos datos en la entidad Clasificaci�n Cliente.*/
					log.debug("verificacion tipo de clasificacion asignado a consultora");					
					
					//INI PER-SiCC-2015-0192_03
					
//					if(interfazDATDAO.validarRegistroRLRepetidoClasificacionCliente(criteriaDetalle)){
//						throw new Exception("Tipo Clasificaci�n ya existe para la consultora "+ codigoConsultora );
//					}
					
					 //Obtener el OID Tipo Subtipo de cada cliente
					log.debug("Obtener el OID Tipo Subtipo de cada cliente - getOidTipoSubTipoClientePorCodigoCliente");	
					Integer oidTipoSubtipoCliente = interfazDATDAO.getOidTipoSubTipoClientePorCodigoCliente(criteriaDetalle);
					//Obtener el OID del tipo Clasificaci�n �RL� de acuerdo a la combinaci�n de tipo y subtipo cliente establecida en la parametr�a.
					log.debug("Obtener el OID del tipo Clasificaci�n �RL� de acuerdo a la combinaci�n de tipo y subtipo cliente establecida en la parametr�a - getOidTipoClasificacionPorParametriaInterfaz");
					Integer oidTipoClasificacion = interfazDATDAO.getOidTipoClasificacionPorParametriaInterfaz(params);
					//Verificar si existe registro en la tabla MAE_CLIEN_CLASI filtrando por el OID del tipo Clasificaci�n �RL� y por el OID Tipo Subtipo del cliente
					HashMap paramNew = new HashMap();
					paramNew.put("nuevoParametroOidClienteTipoSubTipo", oidTipoSubtipoCliente.intValue());
					paramNew.put("nuevoParametroOidTipoClasificacion", oidTipoClasificacion.intValue());
					if(interfazDATDAO.validarExistenciaClienteClasificacion(paramNew)){
						log.debug("registro encontrado");		
						throw new Exception("Tipo Clasificaci�n ya existe para la consultora "+ codigoConsultora );
					}
					
					
					//FIN PER-SiCC-2015-0192_03

					//Captura el correlativo maximo
					int correlativo = interfazDATDAO.getCorrelativoDATClienClasi();
										

					//Se actualiza en la tabla Cliente Clasificacion
					criteriaDetalle.put("oidTipoSubTipo", strOidSubTipoCliente);
					criteriaDetalle.put("correlativo", correlativo);		
					criteriaDetalle.put("oidTipoClasificacion", strOidTipoClasificacion);		
					criteriaDetalle.put("oidClasificacion", strOidClasificacion);
					criteriaDetalle.put("oidPeriodo", oidCampanha.intValue());					
					criteriaDetalle.put("usuario", usuario.getAuditInfo().getUpdatedBy());
			
					interfazDATDAO.insertDATClienClasi(criteriaDetalle);

					registroProcesado(interfazParams);
				}catch(Exception ex) {
					if(ex.getCause()!=null) {
						Throwable tex = ex.getCause();

						if(tex.getCause()!=null)
							interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  tex.getCause().getMessage());
						else
							interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  tex.getMessage());
					} else {
						interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  ex.getMessage());
					}

					registroErroneo(interfazParams);
				}	

			}
		}	
		catch (Exception e) {
			e.printStackTrace();
			InterfazException er = new InterfazException(e.getMessage());
			er.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS);
			throw er;
		}
		
	}

	private void registroProcesado(InterfazParams interfazParams) {
		Long registrosProcesados = (Long) interfazParams.getQueryParams().get(
				"registrosProcesados");
		registrosProcesados = new Long(registrosProcesados.longValue() + 1);
		interfazParams.getQueryParams().put("registrosProcesados",
				registrosProcesados);
	}

	private void registroErroneo(InterfazParams interfazParams) {
		Long registrosErroneos = (Long) interfazParams.getQueryParams().get(
				"registrosErroneos");
		registrosErroneos = new Long(registrosErroneos.longValue() + 1);
		interfazParams.getQueryParams().put("registrosErroneos",
				registrosErroneos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazAbstractService#beforeSaveHistorico(biz.belcorp.ssicc.sisicc.service.beans.InterfazParams)
	 */
	protected void onFinally(InterfazParams interfazParams, InterfazResult interfazResult) {
		String log = interfazParams.getLog();
		if (StringUtils.isNotBlank(log)) {
			Historico historico = interfazParams.getHistorico();
			interfazResult.setCompletado(true);
			historico.setFlagError(Constants.SI);
			historico.setRegistrosProcesados((Long) interfazParams.getQueryParams()
					.get("registrosProcesados"));
			historico.setRegistrosErroneos((Long) interfazParams.getQueryParams()
					.get("registrosErroneos"));
			historico
					.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
			historico.setObservaciones("Algunos registros no fueron cargados. Revise el archivo de log de errores.");
			historico.setDescripcionError(log);
		}
	}
	
	public Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
				if (log.isDebugEnabled()) {
				    log.debug("Dentro del metodo 'prepareQueryParams'");
				}
				// Obtenemos los parametros por defecto de la clase padre
				Map queryParams = super.prepareQueryParams(interfazParams);
				InterfazFormat interfazFormat = interfazFormatServiceFacade.getInterfazFormat(interfazParams.getInterfaz());
				Interfaz interfaz = interfazFormat.getInterfaz();

				
				
				queryParams.put("correoOrigen", interfaz.getCorreoOrigen());	
				queryParams.put("correoDefault", interfaz.getCorreoDestinos());	
				queryParams.put("subject", "Interfaz "+interfaz.getCodigo()+" "+interfaz.getDescripcion());	
				queryParams.put("directorioCabecera", interfaz.getDirectorioLog());				
				queryParams.put("extension", interfaz.getExtensionLogErroresDescripcion());
				
				queryParams.put("nombreLogArchivo", interfaz.getNombreArchivoEntradaSalida());	
				
				return queryParams;
			}
	
	/**
	 * metodo auxiliar que me permite recuperar en cadena el valor de un objeto
	 * 
	 * @param obj
	 * @return
	 */
	private String reemplazarNulo(Object obj) {
		if(obj == null)
			return "";
		else
			return (String)obj;
	}

}
