 package biz.belcorp.ssicc.service.sisicc.impl;

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
import biz.belcorp.ssicc.dao.sisicc.InterfazACCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazACCRecepcionarRecomendantePremio;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

@Service("sisicc.InterfazACCRecepcionarRecomendantesRecomendadasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazACCRecepcionarRecomendantesRecomendadasServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	public static final String ACC_INDICADOR_NO_PROCESADO = "0";
	public static final String ACC_PAIS_PERU = "PE";
	public static final String ACC_TIPO_DOCUMENTO_DNI = "01";
	
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
		
		boolean error = false;
		
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
				 
		try {
			
			/* Insertando Recomendantes - Recomendadas */
			log.info("insertando Recomendantes - Recomendadas");
			for (int i = 0; i < data.size(); i++) {
				error = false;
				HashMap criteriaDetalle = (HashMap) data.get(i);  
				
				InterfazACCRecepcionarRecomendantePremio detalle = new InterfazACCRecepcionarRecomendantePremio();
				try {
					BeanUtils.copyProperties(detalle, criteriaDetalle);
				} catch (Exception e) {
					throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
				}
				
				/*Validando codigoRecomendante, tipo de documento, numero y version de concurso, campaa, numero de premio */
				interfazACCDAO.validarACCInterfazRecomendantePremio(criteriaDetalle);
				
				String resultado = (String)criteriaDetalle.get("resultado");
				String mensajeResultado = (String)criteriaDetalle.get("mensajeResultado");

				if (!resultado.equals("OK")) {
					error = true;
					interfazParams.appendLog("Registro Nro: " + (i + 1) + ". " +  mensajeResultado);
				}
				
				/* Insertando en el detalle */ 
				if (!error) {
					detalle.setIndicadorProcesado(ACC_INDICADOR_NO_PROCESADO);
					
					String longituDocumento = interfazACCDAO.getACCLongitudDocumento(detalle.getCodigoPais(), detalle.getTipoDocIdentidad());
					if(longituDocumento != null) {
						
				        StringBuffer cadenaNumeroDocIdentidad = new StringBuffer("" + detalle.getNumeroDocIdentidad());

				        //Le agregamos ceros a la izquierda hasta completar el numCaracteres exigido 
				        int cantCeros = Integer.parseInt(longituDocumento) - cadenaNumeroDocIdentidad.length();

				        for (int j = 0; j < cantCeros ; j++) {
				        	cadenaNumeroDocIdentidad.insert(0, "0");
				        }
				        
				        detalle.setNumeroDocIdentidad(cadenaNumeroDocIdentidad.toString());
					}
					
					try {
						HashMap parametroMap = new HashMap();
							parametroMap.put("codigoPais", detalle.getCodigoPais());
							parametroMap.put("codigoCompania", detalle.getCodigoCompania());
							parametroMap.put("codigoRecomendante", detalle.getCodigoRecomendante());
							parametroMap.put("tipoDocIdentidad", detalle.getTipoDocIdentidad());
							parametroMap.put("numeroDocIdentidad", detalle.getNumeroDocIdentidad());						
							parametroMap.put("numeroConcurso", detalle.getNumeroConcurso());
							parametroMap.put("VersionConcurso", detalle.getVersionConcurso().toString());
							parametroMap.put("numeroPremio", detalle.getNumeroPremio().toString());
							parametroMap.put("nivel", detalle.getNivel().toString());
							parametroMap.put("campanaSolicitud", detalle.getCampanaSolicitud());
							parametroMap.put("indicadorAsignacionPremio", detalle.getIndicadorAsignacionPremio());
							parametroMap.put("codigoRecomendada", detalle.getCodigoRecomendada());
							parametroMap.put("fecha", detalle.getFecha());
							parametroMap.put("hora", detalle.getHora());
							parametroMap.put("usuario", detalle.getUsuario());
							parametroMap.put("indicadorProcesado", detalle.getIndicadorProcesado());

						//interfazACCDAO.insertInterfazACCRecepcionarRecomendantePremio(detalle);
						interfazACCDAO.executeInterfazACCRecepcionarRecomendantePremio(parametroMap);

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
			
			Map params = interfazParams.getQueryParams();
			String codigoPais	   = (String)params.get("codigoPais");
			String codigoPeriodo	   = (String)params.get("codigoPeriodo");
			
		    Map criteriax = new HashMap();
		    criteriax.put("codigoPais", codigoPais);
		    criteriax.put("codigoPeriodo", codigoPeriodo);	    
		    criteriax.put("psResultado", "-");
		    criteriax.put("psMensajeResultado", "-");
		
			interfazACCDAO.procesarInterfazACCActualizaConcursoRecomendacion(criteriax);
			
		}	
		catch (Exception e) {
			e.printStackTrace();
			InterfazException er = new InterfazException(e.getMessage());
			er.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS);
			throw er;
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
/*	
	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		// TODO Auto-generated method stub
		super.beforeReadData(interfazParams);
		
		Map params = interfazParams.getQueryParams();
		String codigoPais	   = (String)params.get("codigoPais");
		String codigoPeriodo	   = (String)params.get("codigoPeriodo");
		
	    Map criteriax = new HashMap();
	    criteriax.put("codigoPais", codigoPais);
	    criteriax.put("codigoPeriodo", codigoPeriodo);	    
	    criteriax.put("psResultado", "-");
	    criteriax.put("psMensajeResultado", "-");
	
		interfazACCDAO.procesarInterfazACCActualizaConcursoRecomendacion(criteriax);
	}
*/
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
