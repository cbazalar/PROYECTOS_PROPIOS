/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.JavaUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPERRecepcionarConsolidadosOtrosCanalesServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPERRecepcionarConsolidadosOtrosCanalesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPERRecepcionarConsolidadosOtrosCanalesServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	private Logger logger = Logger.getLogger(InterfazPERRecepcionarConsolidadosOtrosCanalesServiceImpl.class.getName());	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	protected void addLine(List data, Map row) {
		//SE ANHADE REGISTROS LEIDOS DEL ARCHIVO
		data.add(row);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.util.List)
	 */
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
				
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
		String mensajeError ="";
		
		String codigoPais =	(String)interfazParams.getQueryParams().get("codigoPais");	 
		try {
			Iterator it = data.iterator();			
			/* Insertando Recomendantes - Recomendadas */
			log.info("insertando los registros leidos");
			int lineCount=0;
			
			while(it.hasNext()) {
				HashMap row = (HashMap)it.next();  
				lineCount++;
				row.put("codigoPais", codigoPais);
				 String codCliente = (String)row.get("codigoCliente");
					log.debug("codCliente:"+codCliente);
					String mensajeRegistroNumeroError="";
					try {
						Usuario usuario = interfazParams.getUsuario();
						//
						
						//obteneinedo el display del mensaje si hay error
						mensajeRegistroNumeroError =  this.messageSource.
						getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.mensajeRegistroNumeroError",
						null, getLocale(usuario))+" ";
								
						InterfazPERActualizarPercepcionesConsolidado consolidado = new InterfazPERActualizarPercepcionesConsolidado();
						String fechaEmisionCom = (String) row.get("fechaEmisionComprobantePercepcion");
						String fechaEmisionDoc = (String) row.get("fechaEmisionDocumentoLegal");
						SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy");
		
						row.remove("fechaEmisionComprobantePercepcion");
						row.remove("fechaEmisionDocumentoLegal");						
						row.put("fechaEmisionComprobantePercepcion", formatter.parse( convierteFormatoFecha(fechaEmisionCom)));
						row.put("fechaEmisionDocumentoLegal",formatter.parseObject(convierteFormatoFecha(fechaEmisionDoc)));
					    BeanUtils.copyProperties(consolidado, row);
					    
					    /* Insertando en la Base de Datos */
					    String correlativo = "0"; //existe un triger que obtine e correlativo
					    //String correlativo = interfazSiCCDAO.getPercepcionesConsolidadoCorrelativoSiguiente();
					    consolidado.setCorrelativoComprobantePercepcion(correlativo);
						consolidado.setEstado(Constants.ESTADO_ACTIVO);
						log.debug("consolidado: Apunto de insertar");			
						log.debug(consolidado.getCodigoPais()+" "+consolidado.getSerieComprobantePercepcion());
						
						/* Efectuando validaciones */
						Map criteria = new HashMap();
						String codigoSociedad = consolidado.getCodigoSociedad();
						codigoPais = consolidado.getCodigoPais();
						criteria.put("codigoPais", codigoPais);
						criteria.put("codigoSociedad", codigoSociedad);
						
						
						boolean errorRegistro = false;
						if (!Constants.ESTADO_FLAG_CANCELACION_OTROS_CANALES_A.equals(consolidado.getFlagCancelacion()) && 
							!Constants.ESTADO_FLAG_CANCELACION_OTROS_CANALES_1.equals(consolidado.getFlagCancelacion())) {
							if (StringUtils.isBlank(consolidado.getNumeroDocumentoIdentidad())) {
								errorRegistro = true;
								mensajeError =  this.messageSource.
								getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.documento.identidad",
								null, getLocale(usuario));	
							}
							if (!errorRegistro) {
								if (StringUtils.isBlank(consolidado.getTipoDocumentoIdentidad())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.tipo.documento.identidad",
									null, getLocale(usuario));	
								}
							}
							
							if (!errorRegistro) {
								if (StringUtils.isBlank(consolidado.getSerieComprobantePercepcion())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.serie.percepcion",
									null, getLocale(usuario));
								}
							}
							
							if (!errorRegistro) {
								if (StringUtils.isBlank(consolidado.getNumeroComprobantePercepcion())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.comprobante.percepcion",
									null, getLocale(usuario));
								}
							}
							
							if (!errorRegistro) {
								if (StringUtils.isBlank(consolidado.getSecuenciaComprobantePercepcion())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.secuencia.comprobante.percepcion",
									null, getLocale(usuario));
								}
							}
							
							if (!errorRegistro) {
								if (StringUtils.isBlank(consolidado.getTipoDocumentoLegal())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.tipo.documento.legal",
									null, getLocale(usuario));
								}
							}
							
							if (!errorRegistro) {
								if (StringUtils.isBlank(consolidado.getSerieDocumentoLegal())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.serie.documento.legal",
									null, getLocale(usuario));
								}
							}
							if (!errorRegistro) {
								if (StringUtils.isBlank(consolidado.getNumeroDocumentoLegal())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.numero.documento.legal",
									null, getLocale(usuario));
								}
							}
							if (!errorRegistro) {
								if (consolidado.getFechaEmisionDocumentoLegal()== null) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.fecha.emision.documento",
									null, getLocale(usuario));
								}
							}
							if (!errorRegistro) {
								if (consolidado.getFechaEmisionComprobantePercepcion()== null) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.fecha.emision.comprobante",
									null, getLocale(usuario));
								}
							}
							if (!errorRegistro) {
								if ("0".equals(consolidado.getMontoPago())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.monto.pago",
									null, getLocale(usuario));
								}					
							}
							if (!errorRegistro) {
								if ("0".equals(consolidado.getMontoTotalDocumentoLegal())) {
									errorRegistro = true;
									mensajeError =  this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.monto.total",
									null, getLocale(usuario));
								}					
							}
							
							//si hay error 
							if(errorRegistro){
								interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount)+ ", "+mensajeError);
								//throw new InterfazException(mensajeRegistroNumeroError + (lineCount)+mensajeError);
								registroErroneo(interfazParams);
								continue;
							}
							
						}
						
						
						/* Validando el Nro de Documento */
						String tipoDocum = consolidado.getTipoDocumentoIdentidad();
						if ("01".equals(tipoDocum)) {
							if (StringUtils.isNotBlank(consolidado.getNumeroDocumentoIdentidad())) {
								int tamano = consolidado.getNumeroDocumentoIdentidad().length();
								if (tamano < 8) 
									consolidado.setNumeroDocumentoIdentidad(StringUtils.leftPad(consolidado.getNumeroDocumentoIdentidad(), 8, '0'));						
								else 
									if (tamano > 8) 
										consolidado.setNumeroDocumentoIdentidad(StringUtils.right(consolidado.getNumeroDocumentoIdentidad(), 8));	
							}
						}

						String notExiste=this.messageSource.
						getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.not.existe",
								null, getLocale(usuario));
						/* Validaciones del Codigo de Sociedad */
						if (StringUtils.isNotBlank(codigoSociedad)) {
							Base sociedad = interfazSiCCDAO.getSociedadByCodigo(criteria);
							if (sociedad == null)  {
								interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+
										this.messageSource.
										getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoSociedad",
										null, getLocale(usuario))+							
										" " + consolidado.getCodigoSociedad() +" "+notExiste);
								registroErroneo(interfazParams);
								continue;
							}
						}
						else {
							interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+ 
									this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoSociedad",
									null, getLocale(usuario))+							
									" " + consolidado.getCodigoSociedad() +" "+notExiste);
							registroErroneo(interfazParams);
							continue;

							
						}
						
						/* Validando codigo de cliente */
						Map paramValida = new HashMap();
						Integer contador;
						if ("01".equals(consolidado.getTipoDocumentoIdentidad())) {
							paramValida.put("codigoPais", codigoPais);
							paramValida.put("tipoDocumentoIdentidad", consolidado.getTipoDocumentoIdentidad());
							paramValida.put("numeroDocumentoIdentidad", consolidado.getNumeroDocumentoIdentidad());
							paramValida.put("codigoCliente", consolidado.getCodigoCliente());
							if (StringUtils.isNotBlank(consolidado.getCodigoCliente())) {				
								contador = interfazSiCCDAO.getExisteConsultoraByCodigoPais(paramValida);
								if (contador.intValue() <= 0) {
									String codigoCliente = interfazSiCCDAO.getConsultoraByNumeroDocumento(paramValida);
									if (StringUtils.isNotBlank(codigoCliente)) 
										consolidado.setCodigoCliente(codigoCliente);
								}
							}
							else {
								String codigoCliente = interfazSiCCDAO.getConsultoraByNumeroDocumento(paramValida);
								if (StringUtils.isNotBlank(codigoCliente)) 
									consolidado.setCodigoCliente(codigoCliente);
							}
						}
						
						/* Validaciones del Codigo de Canal */
						String codigoCanal = consolidado.getCodigoCanal();
						String codigoISO   = usuario.getIdioma().getCodigoISO();
						criteria.put("codigoCanal", codigoCanal);
						criteria.put("codigoISO", codigoISO);
						if (StringUtils.isNotBlank(codigoCanal)) {
							Base canal = interfazSiCCDAO.getCanalByCodigo(criteria);
							if (canal == null)  {
								interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+ 
										this.messageSource.
										getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoCanal",
										null, getLocale(usuario))+
										 " " + consolidado.getCodigoSubacceso() +" "+notExiste);
								registroErroneo(interfazParams);
								continue;
								
							}
						}
						else {
							interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+
									this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoCanal",
									null, getLocale(usuario))+
									 " " + consolidado.getCodigoSubacceso() +" "+notExiste);
							registroErroneo(interfazParams);
							continue;
							
						}
						
						/* Validaciones del Codigo de Acceso */
						String codigoAcceso = consolidado.getCodigoAcceso();
						criteria.put("codigoAcceso", codigoAcceso);
						if (StringUtils.isNotBlank(codigoAcceso)) {
							Base acceso = interfazSiCCDAO.getAccesosByCodigo(criteria);
							if (acceso == null)  {
								interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+
										        this.messageSource.
										        getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoAcceso",
												null, getLocale(usuario))+" "+
										        consolidado.getCodigoAcceso() +" "+notExiste);
								registroErroneo(interfazParams);
								continue;
								
							}
						}
						else {
							interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+
									this.messageSource.
							        getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoAcceso",
									null, getLocale(usuario))+" "+
									consolidado.getCodigoAcceso() +" "+notExiste);
							registroErroneo(interfazParams);
							continue;
							
						}
						
						/* Validaciones del Codigo de SubAcceso */
						String codigoSAcceso = consolidado.getCodigoSubacceso();
						criteria.put("codigoSubAcceso", codigoSAcceso);
						if (StringUtils.isNotBlank(codigoSAcceso)) {
							Base sacceso  = interfazSiCCDAO.getSubaccesosByCodigo(criteria);
							if (sacceso == null) {
								interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+ 
										this.messageSource.
										getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoSubAcceso",
										null, getLocale(usuario))+
										 " " + consolidado.getCodigoSubacceso() +" "+notExiste);
								registroErroneo(interfazParams);
								continue;
								
							}
						}
						else {
							interfazParams.appendLog(mensajeRegistroNumeroError + (lineCount) + ", "+
									this.messageSource.
									getMessage("InterfazPERRecepcionarConsolidadosOtrosCanalesForm.error.codigoSubAcceso",
									null, getLocale(usuario))+
									 " " + consolidado.getCodigoSubacceso() +" "+notExiste);
							registroErroneo(interfazParams);
							continue;
							
						}
						
						//-- Registrando
						int cantidad = 	Integer.valueOf(interfazSiCCDAO.getInterfazPERActualizarPercepcionesConsolidadoCantidad(consolidado)).intValue();
						if (cantidad == 0){
							logger.debug("insert");
						interfazSiCCDAO.insertInterfazPERActualizarPercepcionesConsolidado(consolidado, usuario);
						}	
						else{
							logger.debug("update");
							interfazSiCCDAO.updateInterfazPERActualizarPercepcionesConsolidado(consolidado, usuario);
						}
						
						registroProcesado(interfazParams);
						
					} catch (Exception e) {
						e.printStackTrace();
						log.debug("mensajeRegistroNumeroError "+ mensajeRegistroNumeroError);
						interfazParams.appendLog(mensajeRegistroNumeroError + lineCount + ", " + e.getMessage());
						registroErroneo(interfazParams);
						//continue;
					}
					
				}//fin del while						
		}	
		catch (Exception e) {
			e.printStackTrace();
			InterfazException er = new InterfazException(e.getMessage());
			er.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS);
			throw er;
		}
		
	}	
	/**
	 * Convierte del formato <i>yyyyMMdd</i> al formato <i>dd/MM/yyyy</i>.
	 * <br />
	 * Ej: 20061231 a 31/12/2006
	 * 
	 * @param fechaProceso
	 *            String con el formato <i>yyyyMMdd</i>
	 * @return String con el formato <i>dd/MM/yyyy</i>
	 */
	public static String convierteFormatoFecha(String fecha) {
		String resultado = "";
		resultado = fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/"
				+ fecha.substring(0, 4);
		return resultado;
	}

	
	
	/**
	 * Actualiza elr egistro procesado
	 * @param interfazParams
	 */
	private void registroProcesado(InterfazParams interfazParams) {
		Long registrosProcesados = (Long) interfazParams.getQueryParams().get(
				"registrosProcesados");
		registrosProcesados = new Long(registrosProcesados.longValue() + 1);
		interfazParams.getQueryParams().put("registrosProcesados",
				registrosProcesados);
	}

	/**
	 * Procesa el registro erroneo
	 * @param interfazParams
	 */
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
			interfazResult.setCompletado(false);
			historico.setFlagError(Constants.SI);
			historico.setRegistrosProcesados((Long) interfazParams.getQueryParams()
					.get("registrosProcesados"));
			historico.setRegistrosErroneos((Long) interfazParams.getQueryParams()
					.get("registrosErroneos"));
			historico
					.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
			historico.setDescripcionError(log);
			
			historico.setDescripcionError("Proceso Termino con Errores: "+log);
		}
	}
	
}
