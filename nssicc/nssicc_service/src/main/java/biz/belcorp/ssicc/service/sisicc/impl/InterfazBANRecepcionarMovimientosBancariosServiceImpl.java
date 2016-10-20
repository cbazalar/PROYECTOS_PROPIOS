/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPERMovimientosBancariosDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazBANRecepcionarMovimientosBancariosServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazBANRecepcionarMovimientosBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazBANRecepcionarMovimientosBancariosServiceImpl extends
		BaseInterfazEntradaAbstractService {

	@Resource(name="spusicc.mantenimientoPERMovimientosBancariosDAO")
	private MantenimientoPERMovimientosBancariosDAO mantenimientoPERMovimientoBancarioDAO;

	

	protected void addLine(List data, Map row) {
		data.add(row);
	}

	protected void processData(InterfazParams interfazParams, List data)
			throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}
		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Usuario usuario = interfazParams.getUsuario();
		Interfaz interfaz = interfazParams.getInterfaz();
		Map params = interfazParams.getQueryParams();
		HashMap criteria = null;
		Date fechaPagoDate;
		Date fechaProcesoDate;
		Date fechaGenerarDate;
		String fechaGenerar;
		String codigoConsultora;
		boolean error = false;
		Integer contador;
		Long idPais;

		if (log.isInfoEnabled()) {
			log.info("cargando Movimientos Bancarios");
		}
		String numeroLoteInterno = interfazParams.getNumeroLote();
		/* Seteando valores para la cabecera */
		try {
			criteria = (HashMap) data.get(0);
			criteria.put("codigoPais", interfaz.getCodigoPais());
			idPais = interfazSiCCDAO
					.getDevuelveIdPais(interfaz.getCodigoPais());
			String fechaProceso = (String) criteria.get("fechaProceso");
			String fechaPago = (String) criteria.get("fechaPago");
			fechaPagoDate = dateFormat.parse(fechaPago);
			fechaProcesoDate = dateFormat.parse(fechaProceso);
			
			String fechaProcesoAux = fechaProceso;
			String fechaPagoAux = fechaPago;
			
			fechaPago = DateUtil.convertDateToString(fechaPagoDate);
			fechaProceso = DateUtil.convertDateToString(fechaProcesoDate);
			criteria.remove("fechaProceso");
			criteria.remove("fechaPago");
			criteria.put("fechaProceso",fechaProcesoDate);
			criteria.put("fechaPago", fechaPagoDate);
			
			MovimientosBancariosCabecera cabecera = new MovimientosBancariosCabecera();
			try {
				BeanUtils.copyProperties(cabecera, criteria);
				
				criteria.put("fechaProceso",fechaProcesoAux);
				criteria.put("fechaPago", fechaPagoAux);
			} catch (Exception e) {
				e.printStackTrace();
				throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
			}

			/*
			 * Verificando que la fecha de Pago sea menor a la fecha de
			 * Generacion
			 */
			fechaGenerar = (String) params.get("fechaGenerar");
			fechaGenerarDate = DateUtil.convertStringToDate("dd/MM/yyyy",
					fechaGenerar);			
			
			/* Verificando datos respectivos */
			Map paramValida = new HashMap();
			paramValida.put("idPais", idPais);
			String codigoBanco = (String) criteria.get("codigoBancoSicc");
			if (StringUtils.isNotBlank(codigoBanco)) {
				paramValida.put("codigoBancoSicc", codigoBanco);
				contador = interfazSiCCDAO
						.getExisteCuentaCorrienteBancaria(paramValida);
				if (contador.intValue() <= 0) {
					interfazParams
							.appendLog("Registro Nro: 1. El Codigo de Banco " + codigoBanco + " no existe");
					return;
				}
				String codigoSociedad = interfazSiCCDAO
						.getCodigoSociedadbyCuentaCorrienteBancaria(paramValida);
				cabecera.setCodigoBancoSicc(codigoBanco);
				cabecera.setCodigoSociedad(codigoSociedad);
			}

			/*
			 * Verificando si el Lote ha sido registrado previamente en el
			 * Sistema
			 */
			log.info("metodo cargarMovimientoBancarios");
			MovimientosBancariosDetalle detalleBD = new MovimientosBancariosDetalle();
			MovimientosBancariosCabecera cabeceraBD = new MovimientosBancariosCabecera();
			cabeceraBD.setCodigoPais(cabecera.getCodigoPais());
			cabeceraBD.setCodigoTipoOrigenDatos(Constants.RECAUDO_BANCARIO_AUTOMATICO);
			cabeceraBD.setNumeroLoteExterno(cabecera.getNumeroLoteExterno());			
			cabeceraBD = this.mantenimientoPERMovimientoBancarioDAO
					.getBeanMovimientosBancariosCabecera(cabeceraBD);
			if (cabeceraBD != null) {
				if (cabeceraBD.getStatusLote().equals(
						Constants.PERCEPCIONES_MOVIMIENTO_CONFIRMADO)) {
					throw new InterfazException(
							Constants.INTERFAZ_PER_LOTE_CONFIRMADO);
				}
				detalleBD.setCodigoPais(cabeceraBD.getCodigoPais());
				detalleBD.setCodigoTipoOrigenDatos(Constants.RECAUDO_BANCARIO_AUTOMATICO);
				detalleBD.setNumeroLoteInterno(cabeceraBD.getNumeroLoteInterno());
				log.info("Removiendo Movimientos bancarios Cabecera y Detalle");
				this.mantenimientoPERMovimientoBancarioDAO
						.removeMovimientosBancariosDetalle(detalleBD, usuario);
				this.mantenimientoPERMovimientoBancarioDAO
						.removeMovimientosBancariosCabecera(cabeceraBD, usuario);
			}

			/* Insertando Cabecera de Movimientos Bancarios */
			cabecera.setNumeroLoteInterno(numeroLoteInterno);
			cabecera.setEstado(Constants.ESTADO_ACTIVO);
			cabecera.setCodigoTipoOrigenDatos(Constants.RECAUDO_BANCARIO_AUTOMATICO);
			cabecera.setStatusLote(Constants.PERCEPCIONES_MOVIMIENTO_PROCESADO);
			log.info("insertando movimientos bancarios cabecera");
			interfazSiCCDAO.insertPercepcionesCabecera(cabecera, usuario);
			
			/* Insertando Detalle correspondiente */
			log.info("insertando movimientos bancarios detalle");
			String fechaProcesoD;
			String fechaPagoD;
			for (int i = 0; i < data.size(); i++) {
				error = false;
				HashMap criteriaDetalle = (HashMap) data.get(i);
				criteriaDetalle.put("codigoPais", interfaz.getCodigoPais());
				
				fechaProcesoD = (String) criteriaDetalle.get("fechaProceso");
				fechaPagoD    = (String) criteriaDetalle.get("fechaPago");				
				//if (i != 0) {
					fechaPagoDate = dateFormat.parse(fechaPagoD);
					fechaProcesoDate = dateFormat.parse(fechaProcesoD);
					fechaPagoD = DateUtil.convierteFormatoFecha(fechaPagoD);
					fechaProcesoD = DateUtil.convierteFormatoFecha(fechaProcesoD);
					criteriaDetalle.remove("fechaProceso");
					criteriaDetalle.remove("fechaPago");
					criteriaDetalle.put("fechaProceso",fechaProcesoDate);
					criteriaDetalle.put("fechaPago", fechaPagoDate );
				/*}
				else {
					fechaPagoDate = DateUtil.convertStringToDate("dd/MM/yyyy",fechaPagoD);
					fechaProcesoDate = DateUtil.convertStringToDate("dd/MM/yyyy",fechaProcesoD);
				}*/
				MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
				try {
					BeanUtils.copyProperties(detalle, criteriaDetalle);
				} catch (Exception e) {
					e.printStackTrace();
					throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
				}

			    /* Verificando que la fecha de Pago sea menor a la fecha de Generacion */
				if (fechaPagoDate.compareTo(fechaGenerarDate) > 0) {
					error = true;
					interfazParams
							.appendLog("Registro Nro: " + (i + 1) + 
									". La Fecha de Pago es mayor a " + fechaGenerar);
				}

				/* Verificando validaciones de Tipo de Transaccion */
				if (!error) {
				String tipoTransaccion = (String) criteriaDetalle
						.get("tipoTransaccion");
				if (StringUtils.isNotBlank(tipoTransaccion)) {
					paramValida.put("tipoTransaccion", tipoTransaccion);
					contador = interfazSiCCDAO
							.getExisteTipoTransaccionBancaria(paramValida);
					if (contador.intValue() <= 0) {
						error = true;
						interfazParams
								.appendLog("Registro Nro: " + (i + 1) + 
										   ". El Tipo de Transaccin " + tipoTransaccion + " no existe");
					}
					detalle.setTipoTransaccion(tipoTransaccion);
				}
				}	
				
				/* Verificando que el importe de pago sea mayor a cero */
				if (!error) {
					Double importePago = new Double(detalle.getImportePago());
					if (importePago.doubleValue() == 0.0) {
						error = true;
						interfazParams
								.appendLog("Registro Nro: " + (i + 1) + 
										   ". Importe de Pago igual a cero");
					}
				}	

				/* Verificando validaciones de Consultora */
				if (!error) {
				codigoConsultora = (String) criteriaDetalle
						.get("codigoConsultora");
				if (StringUtils.isNotBlank(codigoConsultora)) {
					paramValida.put("codigoConsultora", codigoConsultora);
					detalle.setStatusMovimiento("P");
					contador = interfazSiCCDAO.getExisteConsultora(paramValida);
					if (contador.intValue() <= 0) {
						detalle.setStatusMovimiento("C");
						detalle.setImportePagoAplicado(new Double(detalle
								.getImportePago()).doubleValue());
						detalle.setImportePagoPendiente(0.0);
						detalle.setImporteRecaudoGenerado(new Double(detalle
								.getImportePago()).doubleValue());
						detalle.setImportePercepcion(0.00);
					}
					else {
						detalle.setImportePagoAplicado(0.0);
						detalle.setImporteRecaudoGenerado(0.0);
						detalle.setImportePagoPendiente(new Double(detalle
								.getImportePago()).doubleValue());
						detalle.setImportePercepcion(0.00);
					}
				} 
				else {
					detalle.setStatusMovimiento("C");
					detalle.setImportePagoAplicado(new Double(detalle
							.getImportePago()).doubleValue());
					detalle.setImportePagoPendiente(0.0);
					detalle.setImporteRecaudoGenerado(new Double(detalle
							.getImportePago()).doubleValue());
					detalle.setImportePercepcion(0.00);
				}

				/* Seteando valores en el detalle */
				detalle.setCodigoTipoOrigenDatos(Constants.RECAUDO_BANCARIO_AUTOMATICO);
				detalle.setNumeroLoteInterno(numeroLoteInterno);
				detalle.setEstado(Constants.ESTADO_ACTIVO);
				}

				/* Insertando en el detalle */
				if (!error) {
					interfazSiCCDAO.insertPercepcionesDetalle(detalle, usuario);
					registroProcesado(interfazParams);
				} else {
					registroErroneo(interfazParams);
				}
			}
			
			/* En caso no se graben detalle tampoco debe grabar cabecera */
			Long registrosProcesados = (Long) interfazParams.getQueryParams().get("registrosProcesados");
			if (registrosProcesados.longValue() == 0) {
				cabeceraBD = new MovimientosBancariosCabecera();
				cabeceraBD.setCodigoPais(cabecera.getCodigoPais());
				cabeceraBD.setNumeroLoteInterno(cabecera.getNumeroLoteInterno());			
				cabeceraBD.setCodigoTipoOrigenDatos(cabecera.getCodigoTipoOrigenDatos());
				this.mantenimientoPERMovimientoBancarioDAO.removeMovimientosBancariosCabecera(cabecera, usuario);
			}	
			
		} catch (Exception e) {
			log.debug("" + e);
			throw new InterfazException(e.getMessage());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazAbstractService#beforeSaveHistorico(biz.belcorp.ssicc.sisicc.service.beans.InterfazParams)
	 */
	protected void onFinally(InterfazParams interfazParams,
			InterfazResult interfazResult) {
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
		}	
	}

}
