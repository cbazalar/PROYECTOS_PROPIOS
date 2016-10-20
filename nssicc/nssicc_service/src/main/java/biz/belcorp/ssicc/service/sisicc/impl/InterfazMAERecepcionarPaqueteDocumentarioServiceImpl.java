package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMAERecepcionarPaqueteDocumentarioServiceImpl"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("sisicc.interfazMAERecepcionarPaqueteDocumentarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMAERecepcionarPaqueteDocumentarioServiceImpl extends BaseInterfazEntradaAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	protected void addLine(List data, Map row) {
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
		String codigoUsuario=	(String)interfazParams.getQueryParams().get("codigoUsuario");
		String mensajeError = "";
		
		try {
			Iterator it = data.iterator();			
			log.info("insertando los registros leidos");
			int i=0;
			
			while(it.hasNext()) {
				HashMap row = (HashMap)it.next();
				String codigoCliente = (String)row.get("codigoCliente");
				String indicadorImpresion = (String)row.get("indicadorImpresion");
				
				Map params = new HashMap();
				params.put("codigoUsuario", codigoUsuario);
				params.put("codigoCliente", codigoCliente);
				
				if("0".equals(indicadorImpresion))
					params.put("indicadorImpresion", "N");
				else
					params.put("indicadorImpresion", null);
				
				String oidCliente = interfazSiCCDAO.getOidClienteByCodigoCliente(params);
				if(oidCliente == null) {
					interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  "Consultora " + codigoCliente + " no existe en Maestro Cliente");
					registroErroneo(interfazParams);
				} else {
					params.put("oidCliente", oidCliente);
					interfazSiCCDAO.updateIndicadorPaqueteDocumentario(params);//actualiza cada registro	
					registroProcesado(interfazParams);
				}	
				
				i++;
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
			historico.setDescripcionError("Solicitar a OPERACIONES enviar correo a Soporte Extranet con copia a Fuerza de Ventas "
					+"informando sobre el error presentado. NO detiene ningun proceso o interfaz, continuar con los siguientes pasos."  + log); 
		}
		
	}
	
}
