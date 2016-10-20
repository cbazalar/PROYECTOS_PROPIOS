 package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazLETDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/**
 * @author ghuertasa
 * 
 */
@Service("sisicc.interfazIMPPrint3Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPPrint3ServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.InterfazLETDAO")
	private InterfazLETDAO interfazLETDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)
	throws InterfazException {
    	if (log.isDebugEnabled())
			log.debug("Entering 'beforeReadData' method");
//	    	String indicadorReproceso= (String)interfazParams.getQueryParams().get("indicadorReproceso");
	    	

	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	protected void addLine(List data, Map row) {
		//SE ANHADE REGISTROS LEIDOS DEL ARCHIVO
		data.add(row);
	}
	
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
				
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
		String codigoPais =	(String)interfazParams.getQueryParams().get("codigoPais");
		String codigoUsuario=	(String)interfazParams.getQueryParams().get("codigoUsuario");
		Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
		String mensajeError = "";
		
		try {
			interfazLETDAO.deleteInterfazLETRecepcionarCursos(null);//elimina todos los registros	
			
			Iterator it = data.iterator();			
			/* Insertando Recomendantes - Recomendadas */
			log.info("insertando los registros leidos");
			int i=0;
			
			while(it.hasNext()) {
				HashMap row = (HashMap)it.next();  
				row.put("codigoPais", codigoPais);
				row.put("codigoUsuario", codigoUsuario);//auditoria
				
				/* Insertando en la cabecera */ 
					try {							 
						interfazLETDAO.insertInterfazLETRecepcionarCursos(row);//inserta cada registro	

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
		}
	}
	
}
