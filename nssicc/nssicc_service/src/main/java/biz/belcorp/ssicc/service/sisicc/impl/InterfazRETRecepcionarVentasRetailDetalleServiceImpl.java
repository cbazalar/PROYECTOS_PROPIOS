 package biz.belcorp.ssicc.service.sisicc.impl;

import java.math.BigDecimal;
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
import biz.belcorp.ssicc.dao.sisicc.InterfazRETDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

@Service("sisicc.interfazRETRecepcionarVentasRetailDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETRecepcionarVentasRetailDetalleServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazRETDAO")
	private InterfazRETDAO interfazRETDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	protected void addLine(List data, Map row) {
		//SE ANHADE REGISTROS LEIDOS DEL ARCHIVO detalle
		//psando a formato numerico la cadena q viene en formato ...000xx.xx
		row.put("montoDscto",row.get("montoDscto")!=null? new BigDecimal(String.valueOf(row.get("montoDscto"))):null);
		row.put("montoUnidadesVendidas",row.get("montoUnidadesVendidas")!=null? new BigDecimal(String.valueOf(row.get("montoUnidadesVendidas"))):null);
		row.put("montoUnidadesDevueltas",row.get("montoUnidadesDevueltas")!=null? new BigDecimal(String.valueOf(row.get("montoUnidadesDevueltas"))):null);
		row.put("montoImpuestos",row.get("montoImpuestos")!=null?new BigDecimal(String.valueOf(row.get("montoImpuestos"))):null);
		//log.debug("row " +row);
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
		try {
			Iterator it = data.iterator();			
			/* Insertando Recomendantes - Recomendadas */
			log.info("insertando los registros leidos detalles");
			int i=0;
			while(it.hasNext()) {
				HashMap row = (HashMap)it.next();  
				row.put("codigoPais", codigoPais);				
				/*se valida si existe en cabecera */
				Integer count= interfazRETDAO.getExisteCabeceraVentasRetail(row);
				if(count.intValue()==0){//si no existe
					interfazParams.appendLog("Registro Nro: " + (i + 1) + ". No existe en cabcera de Ventas Retail" );
					registroErroneo(interfazParams);
					i++;
					continue;
				}
				/* Insertando en el detalle */ 
					try {					
						interfazRETDAO.insertInterfazRETRecepcionarVentasRetailDetalle(row);//inserta cada registro
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
