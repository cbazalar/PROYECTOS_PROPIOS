package biz.belcorp.ssicc.service.sisicc.impl;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.HistoricoDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service de la Interfaz Envio SAP-FI Corporativo
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
@Service("sisicc.interfazSAPFIReprocesoCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAPFIReprocesoCorporativoServiceImpl extends BaseInterfazProcesoAbstractService {
	
	@Resource(name="sisicc.interfazSAFDAO")
	protected InterfazSAFDAO interfazSAFDAO; 
	
	@Resource(name="sisicc.historicoDAO")
	private HistoricoDAO historicoDAO;

	/**
	 * Devuelve el Map de parametros del query. Sobreescrito con la finalidad de
	 * obtener los 'Parametros de Interaz' configurados en el mantenimiento de
	 * la interfaz
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Map con parametros del query
	 */
	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled()) {
		    log.debug("Dentro del metodo 'prepareQueryParams'");
		}
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		
		String codigoPais = (String) queryParams.get("codigoPais");
		String codigoSistema = "IMP";
		String codigoInterfaz = "IMP-P3";
		
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		String numeroLote = interfazService.getNumeroLote(interfazEjecucionPK);
		queryParams.put("numeroLote", numeroLote);
		/*
		String numeroLote = (String) queryParams.get("numeroLote");
		if (StringUtils.isEmpty(numeroLote)) {
			numeroLote = interfazService.getNumeroLote(interfazEjecucionPK);
			queryParams.put("numeroLote", numeroLote);
		}*/
		
		return queryParams;
}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'beforeProcessInterfaz' method");
		Map queryParams = super.prepareQueryParams(interfazParams);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		log.debug("inicio InterfazSAPFIReprocesoCorporativoServiceImpl"	+ params);
		String listaAsientos[] = (String[]) params.get("asientoList");
		Usuario usuario = (Usuario)params.get("usuario");
		AuditInfo auditInfo = (AuditInfo)params.get("auditInfo");
		try {
			//llamar store
			interfazSAFDAO.executeInterfazSAFReprocesoEnviarSAPFICorporativo(params);
			//registrar imp-3
			Historico historico = new Historico();
			historico.setCodigoPais((String)params.get("codigoPais"));
			historico.setCodigoSistema("IMP");
			historico.setNumeroLote((String)params.get("numeroLote"));
			historico.setCodigoInterfaz("IMP-P3");
			historico.setFechaInicioProceso(new Timestamp(System.currentTimeMillis()));
			historico.setFechaFinProceso(new Timestamp(System.currentTimeMillis()));
			historico.setFlagError(Constants.SI);
			historico.setRegistrosProcesados(new Long(0));
			historico.setRegistrosErroneos(Constants.REGISTROS_ERRONEOS_ZERO);
			historico.setObservaciones("");
			historico.setUsuarioProceso((String)params.get("codigoUsuario"));
			historico.setAuditInfo(auditInfo);
			historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
			historico.setDescripcionError("");
			historico.setNombreArchivo("");
			historico.setHistoricoFileName("");
			historico.setIdProcesoBatch(new Long(0));
			historico.setNivelHilo(new Long(1));
			historicoDAO.insertHistorico(historico, usuario);
			//ingreso asientos?
			if(listaAsientos!=null){
				if(listaAsientos.length!=0){
					interfazSAFDAO.deleteSAFReprocesoEnviarSAPFICorporativo(params);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}