package biz.belcorp.ssicc.service.sisicc.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 */
@Service("sisicc.interfazMYERecepcionarClientesIPUnicaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYERecepcionarClientesIPUnicaServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazMYEDAO")
	InterfazMYEDAO interfazMYEDAO;
	
	protected void addLine(List data, Map row) {
		data.add(row);
	}
	
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}
		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
		
		Usuario usuario= (Usuario)interfazParams.getQueryParams().get("usuario");
		Pais pais= (Pais)interfazParams.getQueryParams().get("pais");
		
		boolean error = false;
		
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
				 
		try {
			
			/* Validando las consultoras ingresadas */
			log.info("Validando las consultoras ingresadas");
			boolean flag = false;
			for (int i = 0; i < data.size(); i++) {
				HashMap criteriaDetalle = (HashMap) data.get(i);
				criteriaDetalle.put("codigoPais", pais.getCodigo());
				criteriaDetalle.put("codigoUsuario", usuario.getLogin());
				
				if (interfazMYEDAO.getExisteMaestroClientes(criteriaDetalle)>0) {
					flag = true;
					break;
				}
			}
			
			if(flag){
				HashMap criteriaDetalle = new HashMap();
				criteriaDetalle.put("codigoPais", pais.getCodigo());
				criteriaDetalle.put("codigoUsuario", usuario.getLogin());
				interfazMYEDAO.updateInterfazMYERecepcionarClientesIPUnica(criteriaDetalle);
			}
			
			
			for (int i = 0; i < data.size(); i++) {
				error = false;
				HashMap criteriaDetalle = (HashMap) data.get(i);
				criteriaDetalle.put("codigoPais", pais.getCodigo());
				criteriaDetalle.put("codigoUsuario", usuario.getLogin());

				if (interfazMYEDAO.getExisteMaestroClientes(criteriaDetalle)==0) {
					error = true;
					interfazParams.appendLog("Registro Nro.: " + (i + 1) + ". Consultora "+MapUtils.getString(criteriaDetalle, "codigoCliente")+" no existe en Maestro Cliente");
				}
				
				/* Actualizando en la entidad Cliente Datos Adicionales*/ 
				if (!error) {
					try {
						interfazMYEDAO.updateInterfazMYERecepcionarClientesIPUnica2(criteriaDetalle);	
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
				    
				} else {
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
			interfazResult.setCompletado(false);
			historico.setFlagError(Constants.SI);
			historico.setRegistrosProcesados((Long) interfazParams.getQueryParams()
					.get("registrosProcesados"));
			historico.setRegistrosErroneos((Long) interfazParams.getQueryParams()
					.get("registrosErroneos"));
			historico
					.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
			historico.setDescripcionError(log);
			
			historico.setDescripcionError("Hubo problema en validar registros del archivo. Revise el archivo de log de errores ");
		}
	}

}