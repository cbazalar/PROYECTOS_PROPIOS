/*
 * Created on 22-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.HistoricoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="HistoricoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.historicoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class HistoricoServiceImpl extends BaseService implements
		HistoricoService {

	@Resource(name="sisicc.historicoDAO")
	private HistoricoDAO historicoDAO;
		
		
	/* 
	 * @see biz.belcorp.ssicc.service.HistoricoService#insertHistorico(biz.belcorp.ssicc.model.Historico, biz.belcorp.ssicc.model.Usuario)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void insertHistorico(Historico historico, Usuario usuario) {
		this.historicoDAO.insertHistorico(historico, usuario);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.HistoricoService#updateHistorico(biz.belcorp.ssicc.model.Historico, biz.belcorp.ssicc.model.Usuario)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateHistorico(Historico historico, Usuario usuario) {
		this.historicoDAO.updateHistorico(historico, usuario);
	}
	/* 
	 * @see biz.belcorp.ssicc.service.HistoricoService#getHistoricosByCriteria(java.util.Map)
	 */
	public List getHistoricosByCriteria(Map criteria) {
		String codigoInterfazTexto = (String)criteria.get("codigoInterfazTexto");
		if (StringUtils.isNotBlank(codigoInterfazTexto)) {
			codigoInterfazTexto = codigoInterfazTexto + "%";
			criteria.put("codigoInterfazTexto", codigoInterfazTexto);
		}
		return this.historicoDAO.getHistoricosByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.HistoricoService#getHistoricosLotesMultiHilo(java.util.Map)
	 */
	public List<Historico> getHistoricosLotesMultiHilo(Map criteria) {
		return this.historicoDAO.getHistoricosLotesMultiHilo(criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.HistoricoService#getUltimoHistoricoByCodInterfaz(java.util.Map)
     */
    public List getUltimoHistoricoByCodInterfaz(Map criteria) {
        // TODO Auto-generated method stub
    	return this.historicoDAO.getUltimoHistoricoByCodInterfaz(criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.HistoricoService#getDevuelveDescripcionErrorInterfaz(java.util.Map)
     */
    public String getDevuelveDescripcionErrorInterfaz(Map criteria) {
    	return this.historicoDAO.getDevuelveDescripcionErrorInterfaz(criteria);
    }
    
    
    /* INI FRAMEWORK NSSICC PRUEBAS TRANSACCION */
    public void insertHistorico2(Historico historico, Usuario usuario) {
		this.historicoDAO.insertHistorico(historico, usuario);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.HistoricoService#updateHistorico(biz.belcorp.ssicc.model.Historico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistorico2(Historico historico, Usuario usuario) {
		this.historicoDAO.updateHistorico(historico, usuario);
	}
	 /* FiN FRAMEWORK NSSICC PRUEBAS TRANSACCION */
	
	
	/* INI FRAMEWORK NSSICC */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateHistoricoIniProcesoAnteriorInterfaz(Historico historico, Usuario usuario) {
    	this.historicoDAO.updateHistoricoIniProcesoAnteriorInterfaz(historico, usuario);
	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateHistoricoFinProcesoAnteriorInterfaz(Historico historico, Usuario usuario) {
    	this.historicoDAO.updateHistoricoFinProcesoAnteriorInterfaz(historico, usuario);
	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateHistoricoIniInterfaz(Historico historico, Usuario usuario) {
    	this.historicoDAO.updateHistoricoIniInterfaz(historico, usuario);
	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateHistoricoFinInterfaz(Historico historico, Usuario usuario) {
    	this.historicoDAO.updateHistoricoFinInterfaz(historico, usuario);
	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateHistoricoIniProcesoPosteriorInterfaz(Historico historico, Usuario usuario) {
    	this.historicoDAO.updateHistoricoIniProcesoPosteriorInterfaz(historico, usuario);
	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateHistoricoFinProcesoPosteriorInterfaz(Historico historico, Usuario usuario) {
    	this.historicoDAO.updateHistoricoFinProcesoPosteriorInterfaz(historico, usuario);
	}
   
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.HistoricoService#getHistoricosLotesMultiHiloLote(java.util.Map)
     */
    public List<Historico> getHistoricosLotesMultiHiloLote(Map criteria) {
    	return this.historicoDAO.getHistoricosLotesMultiHiloLote(criteria);
    }
    /* FIN FRAMEWORK NSSICC */
    
}
