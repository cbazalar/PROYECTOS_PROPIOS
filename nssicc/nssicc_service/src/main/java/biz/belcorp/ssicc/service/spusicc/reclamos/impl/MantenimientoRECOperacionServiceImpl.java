package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECOperacionDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.Reclamos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;

@Service("spusicc.mantenimientoRECOperacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECOperacionServiceImpl extends BaseService implements MantenimientoRECOperacionService {
	
	@Resource(name="spusicc.reclamos.mantenimientoRECOperacionDAO")
    MantenimientoRECOperacionDAO mantenimientoRECOperacionDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
    InterfazSiCCDAO interfazSiCCDAO;

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#getOperacionesHomologadasByCodigoPais(java.util.Map)
     */
    public List getOperacionesHomologadasByCodigoPais(Map criteria) {
        log.debug("Entering method 'getOperacionesHomologadasByCodigoPais'");

        return mantenimientoRECOperacionDAO.getOperacionesHomologadasByCodigoPais(criteria);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#deleteInterfazRETRecepcionarVentasRetailDetDet(java.util.Map)
     */
    public void deleteInterfazRETRecepcionarVentasRetailDetDet() {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.deleteInterfazRETRecepcionarVentasRetailDetDet();
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#insertRECProductosList(java.util.Map)
     */
    public void insertRECProductosList(Map criteria) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.insertRECProductosList(criteria);
    }
    

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#getOperacionesHomologadasByCriteria(java.util.Map)
     */
    public List getOperacionesHomologadasByCriteria(Map criteria) {
        log.debug("Entering method 'getOperacionesHomologadasByCriteria'");

        return mantenimientoRECOperacionDAO.getOperacionesHomologadasByCriteria(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#getOperacionesByOperacionHomologada(java.util.Map)
     */
    public List getOperacionesByOperacionHomologada(Map criteria) {
        log.debug("Entering method 'getOperacionesByOperacionHomologada'");

        return mantenimientoRECOperacionDAO.getOperacionesByOperacionHomologada(criteria);
    }
    
    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#getOperacionesHomologadasById(java.util.Map)
     */
    public Reclamos getOperacionesHomologadasById(Map criteria) {
        log.debug("Entering method 'getOperacionesHomologadasByCriteria'");

        return mantenimientoRECOperacionDAO.getOperacionesHomologadasById(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#insertOperacionesHomologadas(biz.belcorp.ssicc.spusicc.reclamos.model.Reclamos, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertOperacionesHomologadas(Reclamos reclamos, Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.insertOperacionesHomologadas(reclamos,
            usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#deleteOperacionesHomologadas(biz.belcorp.ssicc.spusicc.reclamos.model.Reclamos)
     */
    public void deleteOperacionesHomologadas(Reclamos reclamos) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.deleteOperacionesHomologadas(reclamos);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getControlFacturacionByCriteria(java.util.Map)
     */
    public List getControlFacturacionByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getControlFacturacionByCriteria(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getProductoAgregacionByCriteria(java.util.Map)
     */
    public List getProductoAgregacionByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getProductoAgregacionByCriteria(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getSolicitudCabeceraByCriteria(java.util.Map)
     */
    public List getDeudaPedidosByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getDeudaPedidosByCriteria(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getBolsaPedidosByCriteria(java.util.Map)
     */
    public List getBolsaPedidosByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getBolsaPedidosByCriteria(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getSolicitudCabeceraById(java.util.Map)
     */
    public SolicitudConsolidadoCabecera getDeudaPedidosById(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getDeudaPedidosById(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getBolsaPedidosById(java.util.Map)
     */
    public SolicitudConsolidadoCabecera getBolsaPedidosById(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getBolsaPedidosById(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getProductoAgregacionById(java.util.Map)
     */
    public ProductoAgregacion getProductoAgregacionById(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getProductoAgregacionById(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getOfertaDetalleById(java.util.Map)
     */
    public ProductoAgregacion getOfertaDetalleById(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getOfertaDetalleById(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getOfertaDetalleByPeriodoCodigoVenta(java.util.Map)
     */
    public BigDecimal getOfertaDetalleByPeriodoCodigoVenta(Map map) {
        // TODO Auto-generated method stub
        return mantenimientoRECOperacionDAO.getOfertaDetalleByPeriodoCodigoVenta(map);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getMatrizFacturacionByPeriodo(java.util.Map)
     */
    public BigDecimal getMatrizFacturacionByPeriodo(Map map) {
        // TODO Auto-generated method stub
        return mantenimientoRECOperacionDAO.getMatrizFacturacionByPeriodo(map);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getDetOfertaIndicaDigitableById(java.util.Map)
     */
    public BigDecimal getDetOfertaIndicaDigitableById(Map map) {
        // TODO Auto-generated method stub
        return mantenimientoRECOperacionDAO.getDetOfertaIndicaDigitableById(map);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getDetOfertaPrecioCatalogoById(java.util.Map)
     */
    public BigDecimal getDetOfertaPrecioCatalogoById(Map map) {
        // TODO Auto-generated method stub
        return mantenimientoRECOperacionDAO.getDetOfertaPrecioCatalogoById(map);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getDetOfertaEstrategiaIndividualById(java.util.Map)
     */
    public BigDecimal getDetOfertaEstrategiaIndividualById(Map map) {
        // TODO Auto-generated method stub
        return mantenimientoRECOperacionDAO.getDetOfertaEstrategiaIndividualById(map);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getCodVentaAgregacById(java.util.Map)
     */
    public BigDecimal getCodVentaAgregacById(Map map) {
        // TODO Auto-generated method stub
        return mantenimientoRECOperacionDAO.getCodVentaAgregacById(map);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#executeActualizaPrioridad(java.util.Map)
     */
    public void executeActualizaPrioridad(Map params) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.executeActualizaPrioridad(params);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#executeProcesoOCRProcesosEspecialesOCS(java.util.Map)
     */
    public void executeProcesoOCRProcesosEspecialesOCS(Map params) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.executeProcesoOCRProcesosEspecialesOCS(params);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getControlFacturacionById(java.util.Map)
     */
    public PedidoControlFacturacion getControlFacturacionById(Map criteria) {
        // TODO Auto-generated method stub
        return this.mantenimientoRECOperacionDAO.getControlFacturacionById(criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#insertControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertControlFacturacion(
        PedidoControlFacturacion controlFacturacion, Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.insertControlFacturacion(controlFacturacion,
            usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#insertProductoPedidoMinimo(biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertProductoPedidoMinimo(
        ProductoAgregacion productoAgregacion, Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.insertProductoPedidoMinimo(productoAgregacion,
            usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#updateControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateControlFacturacion(
        PedidoControlFacturacion controlFacturacion, Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.updateControlFacturacion(controlFacturacion,
            usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#updateDeuda(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateDeuda(SolicitudConsolidadoCabecera cabecera,
        Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.updateDeuda(cabecera, usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#updateBolsaPedidosBloqueoIndividual(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateBolsaPedidosBloqueoIndividual(
        SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.updateBolsaPedidosBloqueoIndividual(cabecera,
            usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#updateDeudaGeneral(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateDeudaGeneral(SolicitudConsolidadoCabecera cabecera,
        Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.updateDeudaGeneral(cabecera, usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#updateBolsaPedidosBloqueo(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateBolsaPedidosBloqueo(
        SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.updateBolsaPedidosBloqueo(cabecera, usuario);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#deleteControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
     */
    public void deleteControlFacturacion(
        PedidoControlFacturacion controlFacturacion) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.deleteControlFacturacion(controlFacturacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#cerrarCampanaControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
     */
    public void cerrarCampanaControlFacturacion(
        PedidoControlFacturacion controlFacturacion) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.cerrarCampanaControlFacturacion(controlFacturacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#desactivarProductoPedidoMinimo(biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion)
     */
    public void desactivarProductoPedidoMinimo(
        ProductoAgregacion productoAgregacion) {
        // TODO Auto-generated method stub
        mantenimientoRECOperacionDAO.desactivarProductoPedidoMinimo(productoAgregacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPedidoControlFacturacionService#getPedidosSiguienteCampanha(java.util.Map)
     */
    public String getPedidosSiguienteCampanha(Map map) {
        // TODO Auto-generated method stub
        return mantenimientoRECOperacionDAO.getPedidosSiguienteCampanha(map);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#getNumeroSecuenciaUsuario()
     */
    public String getNumeroSecuenciaUsuario(){
    	return mantenimientoRECOperacionDAO.getNumeroSecuenciaUsuario();
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#deleteInterfazRETProductosReclamados(java.util.Map)
     */
    public void deleteInterfazRETProductosReclamados(Map criteria) {
        mantenimientoRECOperacionDAO.deleteInterfazRETProductosReclamados(criteria);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionService#executeProcesoRECActualizaUnidadesDevueltas(java.util.Map)
     */
    public void executeProcesoRECActualizaUnidadesDevueltas(Map params) {
    	interfazSiCCDAO.executeProcesoRECActualizaUnidadesDevueltas(params);
	}
	
}
