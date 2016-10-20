package biz.belcorp.ssicc.service.sisicc.impl;

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

@Service("sisicc.interfazADARecepcionarMovimientosDescuentosPersonalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazADARecepcionarMovimientosDescuentosPersonalServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="spusicc.mantenimientoPERMovimientosBancariosDAO")
	private MantenimientoPERMovimientosBancariosDAO mantenimientoPERMovimientoBancarioDAO;

	protected void addLine(List data, Map row) {
		data.add(row);
	}
	
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}
		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
		
		HashMap criteria = null;
		boolean error    = false;
		MovimientosBancariosCabecera cabecera;
		Interfaz interfaz = interfazParams.getInterfaz();
		Usuario  usuario  = interfazParams.getUsuario();
		String   codigoConsultora;
		String   codigoPlanilla;
		String   numeroLoteInterno;
		Integer  contador;
		int		 correlativo;
		Long     idPais;
		
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
		/* Obteniendo la Fecha de Generacion */
		numeroLoteInterno  = interfazParams.getNumeroLote();
				 
		/* Seteando valores para la cabecera */
		try {
			criteria = (HashMap) data.get(0);
			criteria.put("codigoPais", interfaz.getCodigoPais());
			idPais = interfazSiCCDAO.getDevuelveIdPais(interfaz.getCodigoPais());
			
			cabecera = new MovimientosBancariosCabecera();
			try {
				BeanUtils.copyProperties(cabecera, criteria);
			} catch (Exception e) {
				throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
			}
			
			/* Verificando datos respectivos */			
			Map paramValida = new HashMap();
			paramValida.put("idPais", idPais);
			
			/* Verificando cuenta corriente bancaria */
			String codigoBanco      = "10DEPE";
			paramValida.put("codigoBancoSicc", codigoBanco);
			contador = interfazSiCCDAO.getExisteCuentaCorrienteBancaria(paramValida);
			if (contador.intValue() <= 0) { 
				interfazParams.appendLog("Registro Nro: 1. El Codigo de Banco " + codigoBanco + " no existe");
				return;
			}
			/* Obteniendo Codigo de Sociedad */
			String codigoSociedad  = interfazSiCCDAO.getCodigoSociedadbyCuentaCorrienteBancaria(paramValida);
			cabecera.setCodigoBancoSicc(codigoBanco);
			cabecera.setCodigoSociedad(codigoSociedad);
			cabecera.setCodigoTipoOrigenDatos(Constants.RECAUDO_DESCUENTO_PERSONAL);
			cabecera.setNumeroLoteInterno(numeroLoteInterno);
			cabecera.setEstado(Constants.ESTADO_ACTIVO);
			cabecera.setStatusLote(Constants.PERCEPCIONES_MOVIMIENTO_PROCESADO);
			cabecera.setFechaProceso(new Date());
			
			/* Verificando tipo de Transaccion */
			String tipoTransaccion  = "TBEFE";
			paramValida.put("tipoTransaccion", tipoTransaccion);
			contador = interfazSiCCDAO.getExisteTipoTransaccionBancaria(paramValida);
			if (contador.intValue() <= 0) { 
				interfazParams.appendLog("No se encontr Tipo de Transaccin: " + criteria.toString());
				return;
			}
			
			/* Insertando Cabecera de Movimientos Bancarios */			
			log.info("insertando movimientos bancarios cabecera");
			interfazSiCCDAO.insertPercepcionesCabecera(cabecera, usuario);
			
			/* Insertando Detalle correspondiente */
			log.info("insertando movimientos bancarios detalle");
			correlativo = 0;
			for (int i = 0; i < data.size(); i++) {
				error = false;
				HashMap criteriaDetalle = (HashMap) data.get(i);  
				criteriaDetalle.put("codigoPais", interfaz.getCodigoPais());
				
				MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
				try {
					BeanUtils.copyProperties(detalle, criteriaDetalle);
				} catch (Exception e) {
					throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
				}
				
				detalle.setStatusMovimiento(Constants.PERCEPCIONES_MOVIMIENTO_PROCESADO);
				detalle.setTipoTransaccion(tipoTransaccion);
				Date   fechaPagoD = new Date();
				String fechaPago  = DateUtil.convertDateToString(fechaPagoD);
				fechaPagoD = DateUtil.convertStringToDate("dd/MM/yyyy", fechaPago);
				detalle.setFechaPago(fechaPagoD);
				
				detalle.setCodigoTipoOrigenDatos(Constants.RECAUDO_DESCUENTO_PERSONAL);
				detalle.setNumeroLoteInterno(numeroLoteInterno);
				
				double importePago = new Double(detalle.getImportePago()).doubleValue();
				detalle.setImportePagoAplicado(0.0);
				detalle.setImportePagoPendiente(importePago);
				detalle.setImportePercepcion(0.0);
				detalle.setImporteRecaudoGenerado(0.0);
				detalle.setEstado(Constants.ESTADO_ACTIVO);
				
				/* Verificando codigo de Consultora */
				codigoPlanilla   = detalle.getCodigoPlanilla();
				if (StringUtils.isNotBlank(codigoPlanilla)) {
					paramValida.put("codigoPlanilla", codigoPlanilla);
					codigoConsultora = interfazSiCCDAO.getCodigoSociedadbyCodigoPlanilla(paramValida);
					detalle.setCodigoConsultora(codigoConsultora);
					if (StringUtils.isBlank(codigoConsultora)) {
						error = true;
						interfazParams.appendLog("Registro Nro: " + (i + 1) +
								". Cdigo de Planilla " +  codigoPlanilla + " no existe");
					}	
				}
				else {
					error = true;
					interfazParams.appendLog("Registro Nro: " + (i + 1) +
							". Cdigo de Planilla " +  codigoPlanilla + " no existe");
				}
				
				/* Insertando en el detalle */ 
				if (!error) {
					correlativo = correlativo + 1;
					detalle.setConsecutivo(correlativo);
				    interfazSiCCDAO.insertPercepcionesDetalle(detalle, usuario);
				    registroProcesado(interfazParams);
				} else {
					registroErroneo(interfazParams);
				}
			}
			
			/* En caso no se graben detalle tampoco debe grabar cabecera */
			Long registrosProcesados = (Long) interfazParams.getQueryParams().get("registrosProcesados");
			if (registrosProcesados.longValue() == 0) {
				MovimientosBancariosCabecera cabeceraBD = new MovimientosBancariosCabecera();
				cabeceraBD.setCodigoPais(cabecera.getCodigoPais());
				cabeceraBD.setNumeroLoteInterno(cabecera.getNumeroLoteInterno());			
				cabeceraBD.setCodigoTipoOrigenDatos(cabecera.getCodigoTipoOrigenDatos());
				this.mantenimientoPERMovimientoBancarioDAO.removeMovimientosBancariosCabecera(cabecera, usuario);
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
