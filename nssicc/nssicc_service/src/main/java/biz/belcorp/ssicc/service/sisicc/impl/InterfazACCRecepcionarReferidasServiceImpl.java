/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

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
import biz.belcorp.ssicc.dao.sisicc.InterfazACCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

@Service("sisicc.interfazACCRecepcionarReferidasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazACCRecepcionarReferidasServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazACCDAO")
	private InterfazACCDAO interfazACCDAO;

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
			
			/* Insertando Recomendantes - Recomendadas */
			log.info("insertando Recomendantes - Recomendadas");
			for (int i = 0; i < data.size(); i++) {
				HashMap criteriaDetalle = (HashMap) data.get(i);  
				
				String codigoPais = (String)criteriaDetalle.get("codigoPais");

				/* Insertando en el detalle */ 
				log.debug("ejecutando el insert a la tabla de Referidas");
				//Captura el correlativo maximo
				int correlativo = interfazACCDAO.getCorrelativoACCReferidas();

				try {
					
					if (!StringUtils.equals(codigoPais.trim(), usuario.getCodigoPais())) {
						throw new Exception("Codigo de Pais " + codigoPais.trim() + " no corresponde");
					}
					
					criteriaDetalle.put("codigoCorrelativo", correlativo);
					criteriaDetalle.put("numeroDocumentoIdentidadRecomendada", criteriaDetalle.get("numDocIdenRecomendada"));
					criteriaDetalle.put("numeroDocumentoIdentidadRecomendante", criteriaDetalle.get("numDocIdenRecomendante"));
					criteriaDetalle.put("indicadorRegistro", Constants.ACC_INDICADOR_PROCESO_REGISTRADO);
					criteriaDetalle.put("usuarioLogin", usuario.getLogin());
					//Se registra en la tabla de Referidas
					interfazACCDAO.insertReferidas(criteriaDetalle);

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
	
	
	
	
}
