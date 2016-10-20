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
import biz.belcorp.ssicc.dao.sisicc.InterfazAVIDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * @author sbuchelli
 * 
 */
@Service("sisicc.interfazAVIRecepcionarLogrosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAVIRecepcionarLogrosServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazAVIDAO")
	private InterfazAVIDAO interfazAVIDAO;
	
	

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
			Iterator it = data.iterator();			
			/* Insertando Recomendantes - Recomendadas */
			log.info("insertando los registros leidos");
			int i=0;
			
			/* INI SA PER-SiCC-2012-0344 */
			List listaTiposVisita = interfazAVIDAO.getListTiposVisita();
			Map mapTiposVista = new HashMap();
			if(listaTiposVisita != null) {
				for(int k=0; k<listaTiposVisita.size(); k++) {
					String tipoVisita = (String)listaTiposVisita.get(k);
					mapTiposVista.put(tipoVisita, tipoVisita);
				}
			}		
			/* FIN SA PER-SiCC-2012-0344 */
			
			while(it.hasNext()) {
				HashMap row = (HashMap)it.next();  
				row.put("codigoPais", codigoPais);
				row.put("codigoUsuario", codigoUsuario);//auditoria
				/* Insertando en la cabecera */ 
					try {	
						row.put("codigoError",null);
						row.put("campanhaInicio", null);
						row.put("campanhaFin", null);//seran obtenidos en el proceos de validacion
						row.put("tipoRecepcion", Constants.NRO_CERO);
						interfazAVIDAO.executeValidacionRecepcionAsistenteVirtual(row);//tipoRecepcion 0:Logros 1: Datos telefonicos
						
						int codigoError = Integer.parseInt((String)row.get("codigoError"));
						 
						switch (codigoError) {
						case 0://no existe tipo logro
							mensajeError = messageSource.getMessage("interfazAVIRecepcionarLogrosForm.error.noexiste.tipologro",null,getLocale(usuario));
							throw new InterfazException(mensajeError);

						case 1:	//no existe consultora					
							//mensajeError = messageSource.getMessage("interfazAVIRecepcionarLogrosForm.error.noexiste.consultora",null,getLocale(usuario));
							//throw new InterfazException(mensajeError);
							continue;

						case 2://existe logro registrado							
							mensajeError = messageSource.getMessage("interfazAVIRecepcionarLogrosForm.error.existe.logroconsultora",null,getLocale(usuario));
							//throw new InterfazException(mensajeError);							
							
						}
						
						/* INI SA PER-SiCC-2012-0344 */
						String activo = (String)row.get("activo");
						if(!"1".equals(activo))
							row.put("activo", "9");
						
						String codigoTipoVisita = (String)row.get("codigoTipoVisita"); 
						if(mapTiposVista.get(codigoTipoVisita) == null) {
							row.put("codigoTipoVisita", "");
						}
						
						/* FIN SA PER-SiCC-2012-0344 */	
							
						
						if(codigoError == 2){
						  interfazAVIDAO.updateInterfazAVIRecepcionarLogros(row);//inserta cada registro	
						}else{
						  interfazAVIDAO.insertInterfazAVIRecepcionarLogros(row);//inserta cada registro
						}
						
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
