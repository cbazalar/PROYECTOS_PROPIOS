package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOHistoricoDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoValidacion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOHistoricoService;

/**
 * Service que Guarda Historicos de STO
 *  
 * <p>
 * <a href="ProcesoSTOServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
/**
 * @author PEJCAIRAMPOMA
 *
 */
@Service("spusicc.procesoSTOHistoricoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOHistoricoServiceImpl extends BaseService implements ProcesoSTOHistoricoService {

	@Resource(name="spusicc.procesoSTOHistoricoDAO")
	private ProcesoSTOHistoricoDAO procesoSTOHistoricoDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#getNumeroProceso(biz.belcorp.ssicc.spusicc.sto.model.TipoDocumentoDigitadoPK)
	 */
	public String getNumeroProceso(AccionTipoDocumento accionTipoDocumento) {
		return procesoSTOHistoricoDAO.getNumeroProceso(accionTipoDocumento);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#insertHistoricoProceso(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento, biz.belcorp.ssicc.model.Usuario)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void insertHistoricoProceso(HistoricoTipoDocumento historico,Usuario usuario) {
		procesoSTOHistoricoDAO.insertHistoricoProceso(historico, usuario);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#updateHistoricoProceso(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento, biz.belcorp.ssicc.model.Usuario)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateFinProceso(HistoricoTipoDocumento historico, Usuario usuario) {
		procesoSTOHistoricoDAO.updateFinProceso(historico, usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#getProcesoValidacionEjecucionByDocumento(java.util.Map)
	 */
	public List getProcesoValidacionEjecucionByDocumento(Map params) {
		
		return procesoSTOHistoricoDAO.getProcesoValidacionEjecucionByDocumento(params);	
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#getProcesoEjecucionByDocumento(java.util.Map)
	 */
	public List getProcesoEjecucionByDocumento(Map params) {
		
		return procesoSTOHistoricoDAO.getProcesoEjecucionByDocumento(params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#getListaProcesosHijos(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public List getListaProcesosHijos(HistoricoTipoDocumento historico) {
		return procesoSTOHistoricoDAO.getListaProcesosHijos(historico);	
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#UpdateInicioProceso(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void UpdateInicioProceso(HistoricoTipoDocumento historico) {
		procesoSTOHistoricoDAO.UpdateInicioProceso(historico);			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#insertHistoricoValidaciones(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public void insertHistoricoValidaciones(HistoricoTipoDocumento historico) {
		procesoSTOHistoricoDAO.insertHistoricoValidaciones(historico);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#insertHistoricoValidacionesOnline(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public void insertHistoricoValidacionesOnline(HistoricoTipoDocumento historico) {
		procesoSTOHistoricoDAO.insertHistoricoValidacionesOnline(historico);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#updateInicioValidacion(biz.belcorp.ssicc.spusicc.sto.model.HistoricoValidacion)
	 */
	public void updateInicioValidacion(HistoricoValidacion historicoValidacion) {
		procesoSTOHistoricoDAO.updateInicioValidacion(historicoValidacion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOHistoricoService#updateFinValidacion(biz.belcorp.ssicc.spusicc.sto.model.HistoricoValidacion)
	 */
	public void updateFinValidacion(HistoricoValidacion historicoValidacion) {
		procesoSTOHistoricoDAO.updateFinValidacion(historicoValidacion);
		
	}


}
