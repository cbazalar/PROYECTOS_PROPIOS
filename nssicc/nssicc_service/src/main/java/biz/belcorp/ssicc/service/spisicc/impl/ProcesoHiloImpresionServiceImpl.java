/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;
import biz.belcorp.ssicc.service.spisicc.ProcesoHiloImpresionService;

/**
 * <p>
 * <a href="ProcesoImpresionLaserServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma </a>
 */
@Service("spisicc.ProcesoHiloImpresionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoHiloImpresionServiceImpl implements ProcesoHiloImpresionService {
	
	protected final Log log = LogFactory.getLog(getClass());

	@Resource(name="spisicc.procesoImpresionDAO")
	protected ProcesoImpresionDAO procesoImpresionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolDetalleFactura(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolDetalleFactura(ProcesoSpool proceso) {
		procesoImpresionDAO.executeProcesoIMPSpoolDetalleFactura(proceso);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#deleteProcesoIMPSpoolDetalleFactura()
	 */
	public void deleteProcesoIMPSpoolDetalleFactura() {
		procesoImpresionDAO.deleteProcesoIMPSpoolDetalleFactura();
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#getListaRegionesActivasSpool()
	 */
	public List getListaRegionesActivasSpool() {
		return procesoImpresionDAO.getListaRegionesActivasSpool();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#getListaZonasActivasSpool()
	 */
	public List getListaZonasActivasSpool() {
		return procesoImpresionDAO.getListaZonasActivasSpool();
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolCupones(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolCupones(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolCupones(proceso);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#deleteProcesoIMPSpoolCupones()
	 */
	public void deleteProcesoIMPSpoolCupones() {
		procesoImpresionDAO.deleteProcesoIMPSpoolCupones();
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolUltimasNoticias(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolUltimasNoticias(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolUltimasNoticias(proceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolBoletaDespacho(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolBoletaDespacho(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolBoletaDespacho(proceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolDetalleFacturaZona(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolDetalleFacturaZona(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolDetalleFacturaZona(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolEstadoCtaCteService(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolEstadoCtaCte(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolEstadoCtaCte(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolOrdenCompra(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolOrdenCompra(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolOrdenCompra(proceso);		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolCalculaConsolidado(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolCalculaConsolidado(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolCalculaConsolidado(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolCompaginacionFinal(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolCompaginacionFinal(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolCompaginacionFinal(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraBoletaMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraBoletaMatricial(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraBoletaMatricial(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraBoletaPremioMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraBoletaPremioMatricial(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraBoletaPremioMatricial(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraCtaCteService(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraCtaCteService(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraCtaCteService(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraCuponMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraCuponMatricial(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraCuponMatricial(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraDocumentosLegales(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraDocumentosLegales(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraDocumentosLegales(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraFacturaMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraFacturaMatricial(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraFacturaMatricial(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraGuiaRemisionMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraGuiaRemisionMatricial(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraGuiaRemisionMatricial(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraNotaCreditoMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraNotaCreditoMatricial(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraNotaCreditoMatricial(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraNotaDebitoMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraNotaDebitoMatricial(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraNotaDebitoMatricial(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraRuv(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraRuv(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraRuv(proceso);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGeneraBoletasElectronicas(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGeneraBoletasElectronicas(ProcesoSpool proceso) {
		procesoImpresionDAO.executeBoletaElectronicas(proceso);		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolFacturaLaserMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolFacturaLaserMultihilo(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolFacturaLaserMultihilo(proceso);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolNotaCreditoLaserMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolNotaCreditoLaserMultihilo(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolNotaCreditoLaserMultihilo(proceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolNotaDebitoLaserMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolNotaDebitoLaserMultihilo(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolNotaDebitoLaserMultihilo(proceso);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolDetalleFactura3(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolDetalleFactura3(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolDetalleFactura3(proceso);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolDetalleFactura4(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolDetalleFactura4(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolDetalleFactura4(proceso);
				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeSpoolGenerarCtaCorriente(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeSpoolGenerarCtaCorriente(ProcesoSpool proceso) {
		procesoImpresionDAO.executeSpoolGeneraCtaCorriente(null);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoHiloImpresionService#executeGeneraFechaRepartoMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeGeneraFechaRepartoMultihilo(ProcesoSpool proceso) {
		procesoImpresionDAO.executeGeneraFechaRepartoMultihilo(proceso);
	}
	
}
