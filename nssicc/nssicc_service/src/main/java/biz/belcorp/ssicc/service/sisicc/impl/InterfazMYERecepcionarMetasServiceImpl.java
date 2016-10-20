package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.ArrayList;
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
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("sisicc.interfazMYERecepcionarMetasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYERecepcionarMetasServiceImpl extends	BaseInterfazEntradaAbstractService {

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
		
		Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
		Pais pais = (Pais)interfazParams.getQueryParams().get("pais");
		
		Map criteria = new HashMap();
		int indNuevasLogro = interfazMYEDAO.getIndicadorNuevasLogro(criteria);
		
		boolean error = false;
		
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
				 
		try {
			/* Validando las consultoras ingresadas */
			log.info("Validando los datos ingresados");
			boolean flag = false;
			
			/*for (int i = 0; i < data.size(); i++) {
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
			}*/
			
			
			for (int i = 0; i < data.size(); i++) {
				error = false;
				HashMap criteriaDetalle = (HashMap) data.get(i);
				criteriaDetalle.put("codigoPais", pais.getCodigo());
				criteriaDetalle.put("codigoUsuario", usuario.getLogin());

				if (interfazMYEDAO.getExisteTipoLogro(criteriaDetalle) == 0) {
					error = true;
					interfazParams.appendLog("Registro Nro.: " + (i + 1) + ". Tipo de Logro "+MapUtils.getString(criteriaDetalle, "tipoMeta")+" no existe");
				}
				
				if (interfazMYEDAO.getExisteConsultoraMaestro(criteriaDetalle) == 0) {
					error = true;
					interfazParams.appendLog("Registro Nro.: " + (i + 1) + ". Consultora "+MapUtils.getString(criteriaDetalle, "codigoConsultora")+" no existe");
				}
				
				List listCampañasConsultora = new ArrayList();
				if(indNuevasLogro == 1){
					listCampañasConsultora = interfazMYEDAO.getCampañaPrimerUltimoPedidoConsultora(criteriaDetalle);
					if(listCampañasConsultora == null || listCampañasConsultora.size() <= 0){
						error = true;
						interfazParams.appendLog("Registro Nro.: " + (i + 1) + ". Consultora "+MapUtils.getString(criteriaDetalle, "codigoConsultora")+" no tiene dato de primer pedido");
					}
				}
								
				if (!error) {
					try {
						String activo = MapUtils.getString(criteriaDetalle, "activo", "");
						String campPrimerPedido = "";
						String campUltimoPedido = "";
						
						if(!StringUtils.equals(activo, Constants.NUMERO_UNO)){
							activo = Constants.ESTADO_INACTIVO;
							criteriaDetalle.put("activo", activo);
						}
						
						//Si Consultoras son Nuevas, Si Indicador Nuevas = 1
						if(indNuevasLogro == 1){
							Map mapCampañasConsultora = (Map) listCampañasConsultora.get(0);
							campPrimerPedido = MapUtils.getString(mapCampañasConsultora, "camPrimerPedido");
							campUltimoPedido = MapUtils.getString(mapCampañasConsultora, "camUltimoPedido");
						}else{
							campPrimerPedido = MapUtils.getString(criteriaDetalle, "campanaInicio");
							campUltimoPedido = MapUtils.getString(criteriaDetalle, "campanaFin");
						}
						
						//Verifica si Consultora tiene logros registrados
						List listCampañasIniFin = interfazMYEDAO.getCampañasInicioFinLogroConsultora(criteriaDetalle);
						if(listCampañasIniFin != null && listCampañasIniFin.size() > 0){
							
							//Verifica si hay traslape de campañas con la Campaña Primer Pedido 
							criteriaDetalle.put("campPrimerPedido", campPrimerPedido);
							List listValidaTraslape = interfazMYEDAO.getCampañasInicioFinLogroConsultora(criteriaDetalle);
							
							if(listValidaTraslape.size() > 0){
								for (int j = 0; j < listCampañasIniFin.size(); j++) {
									Map mapCampañasIniFin = (Map) listCampañasIniFin.get(j);
									
									if(StringUtils.equals(campPrimerPedido, MapUtils.getString(mapCampañasIniFin, "campanaInicio")) && 
										StringUtils.equals(campUltimoPedido, MapUtils.getString(mapCampañasIniFin, "campanaFin"))){
										
										criteriaDetalle.put("campanaInicio", campPrimerPedido);
										criteriaDetalle.put("campanaFin", campUltimoPedido);
										interfazMYEDAO.updateInterfazMYERecepcionarMetas(criteriaDetalle);
									}else{
										error = true;
										interfazParams.appendLog("Registro Nro.: " + (i + 1) + ". Consultora "+MapUtils.getString(criteriaDetalle, "codigoConsultora")+" tiene traslape con sus campañas");
										
										registroErroneo(interfazParams);
									}
								}
							}
						}
						
						if(!error){
							interfazMYEDAO.insertInterfazMYERecepcionarMetas(criteriaDetalle);
							
							registroProcesado(interfazParams);
						}
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
		}catch (Exception e) {
			e.printStackTrace();
			InterfazException er = new InterfazException(e.getMessage());
			er.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS);
			throw er;
		}	
	}
	
	private void registroProcesado(InterfazParams interfazParams) {
		Long registrosProcesados = (Long) interfazParams.getQueryParams().get("registrosProcesados");
		registrosProcesados = new Long(registrosProcesados.longValue() + 1);
		
		interfazParams.getQueryParams().put("registrosProcesados", registrosProcesados);
	}

	private void registroErroneo(InterfazParams interfazParams) {
		Long registrosErroneos = (Long) interfazParams.getQueryParams().get("registrosErroneos");
		registrosErroneos = new Long(registrosErroneos.longValue() + 1);
		
		interfazParams.getQueryParams().put("registrosErroneos", registrosErroneos);
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
			historico.setRegistrosProcesados((Long) interfazParams.getQueryParams().get("registrosProcesados"));
			historico.setRegistrosErroneos((Long) interfazParams.getQueryParams().get("registrosErroneos"));
			historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);
			historico.setDescripcionError(log);
			historico.setDescripcionError("Hubo problema en validar registros del archivo. Revise el archivo de log de errores ");
		}
	}
}