/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazXRXDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * @author Sigcomt
 *
 */

@Service("sisicc.interfazXRXRecepcionarNotaCreditoElectronicaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazXRXRecepcionarNotaCreditoElectronicaServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazXRXDAO")
	private InterfazXRXDAO interfazXRXDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	protected void addLine(List data, Map row) {
		data.add(row);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.util.List)
	 */
	protected void processData(InterfazParams interfazParams, List data)
			throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
				
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
		Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
		String mensajeError = "";
		try {
			Iterator it = data.iterator();			
			/* Actualizando registros */
			log.info("insertando los registros leidos");
			String nombreArchivo = "";
			int i = 0;
			String nombreArchivoSinExtension = "";
			while(it.hasNext()) {
				nombreArchivo = (String)interfazParams.getQueryParams().get("nombreArchivo");
				HashMap row = (HashMap)it.next();  
				String []sp2  = StringUtils.split(nombreArchivo, ".");
				row.put("nombreArchivo", sp2[0]);
				nombreArchivoSinExtension = sp2[0];
				try {
					String codigoConsecutivo = (String)row.get("codigoConsecutivo");
					String folioFiscal = (String)row.get("folioFiscal");
					String codigoBidimensional = (String)row.get("codigoBidimensional");
					boolean error = false;
					
					if(StringUtils.isBlank(folioFiscal))
					{
						error = true;
						mensajeError = messageSource.getMessage("interfazXRXNotaCreditoElectronicaForm.error.noexiste.folio",new String[]{codigoConsecutivo},getLocale(usuario));						
					}
					
					if(error)
						throw new InterfazException(mensajeError);
					
					interfazXRXDAO.updateInterfazXRXRecepcionarNotaCreditoElectronica(row);
					
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
			
			int cantidadRegistros = 0;
			cantidadRegistros = interfazXRXDAO.getCantidadNotasCredito(nombreArchivoSinExtension);
			
			if (data.size() != cantidadRegistros) {
				throw new InterfazException(messageSource.getMessage("interfazXRXBoletaVentaElectronicaForm.error.folio.nulo",new String[]{},getLocale(usuario)));
			}
			
			Long registrosErroneos = (Long) interfazParams.getQueryParams().get(
					"registrosErroneos");
			
			if( registrosErroneos > 0){
				interfazParams.appendLog(messageSource.getMessage("interfazXRXNotaCreditoElectronicaForm.error.folio.nulo",new String[]{},getLocale(usuario)));
				throw new InterfazException(messageSource.getMessage("interfazXRXNotaCreditoElectronicaForm.error.folio.nulo",new String[]{},getLocale(usuario)));
			}		
			
		}
		catch (InterfazException e) {
			InterfazException er = new InterfazException(e.getMessage());
			er.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
			throw er;
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
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#onFinally(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazResult)
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
			
			historico.setDescripcionError("Proceso Termino con Errores: "+log);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		super.afterProcessInterfaz(interfazParams);
		Long registrosErroneos = (Long) interfazParams.getQueryParams().get(
				"registrosErroneos");
		if( registrosErroneos == 0){
			String nombreArchivo = (String)interfazParams.getQueryParams().get("nombreArchivo");
			String []sp2 = StringUtils.split(nombreArchivo, ".");
			Map params = new HashMap();
			params.put("nombreArchivoSinExtension",sp2[0]);
			log.debug(sp2[0]);
			interfazXRXDAO.executeNotaCreditoAHistorico(params);
		}
		
	}
}
