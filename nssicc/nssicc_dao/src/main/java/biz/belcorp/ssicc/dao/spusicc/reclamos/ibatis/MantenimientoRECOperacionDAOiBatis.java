package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECOperacionDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.Reclamos;

@Repository("spusicc.reclamos.mantenimientoRECOperacionDAO")
public class MantenimientoRECOperacionDAOiBatis extends BaseDAOiBatis
    implements MantenimientoRECOperacionDAO {
    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#getOperacionesHomologadasByCodigoPais(java.util.Map)
     */
    public List getOperacionesHomologadasByCodigoPais(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getOperacionesHomologadasByCodigoPais",
            criteria);
    }

	public void deleteInterfazRETRecepcionarVentasRetailDetDet() {
		getSqlMapClientTemplate().delete(
				"spusicc.reclamos.ReclamosSQL.deleteInterfazRETRecepcionarVentasRetailDetDet",
				null);
	}
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#insertRECProductosList(java.util.Map)
	 */
	public void insertRECProductosList(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.reclamos.ReclamosSQL.insertRECProductosList", criteria);
	}
	

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#getOperacionesHomologadasByCriteria(java.util.Map)
     */
    public List getOperacionesHomologadasByCriteria(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getOperacionesHomologadasByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#getOperacionesByOperacionHomologada(java.util.Map)
     */
    public List getOperacionesByOperacionHomologada(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getOperacionesByOperacionHomologada",
            criteria);
    }
    
    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#getOperacionesHomologadasById(java.util.Map)
     */
    public Reclamos getOperacionesHomologadasById(Map criteria) {
        return (Reclamos) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getOperacionesHomologadasByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#insertOperacionesHomologadas(biz.belcorp.ssicc.spusicc.reclamos.model.Reclamos, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertOperacionesHomologadas(Reclamos reclamos, Usuario usuario) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertOperacionesHomologadas",
            reclamos);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#deleteOperacionesHomologadas(biz.belcorp.ssicc.spusicc.reclamos.model.Reclamos)
     */
    public void deleteOperacionesHomologadas(Reclamos reclamos) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteOperacionHomologada",
            reclamos);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getControlFacturacionByCriteria(java.util.Map)
     */
    public List getControlFacturacionByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getControlFacturacionByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getProductoAgregacionByCriteria(java.util.Map)
     */
    public List getProductoAgregacionByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductoAgregacionByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getSolicitudCabeceraByCriteria(java.util.Map)
     */
    public List getDeudaPedidosByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDeudaPedidosByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getBolsaPedidosByCriteria(java.util.Map)
     */
    public List getBolsaPedidosByCriteria(Map criteria) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getBolsaPedidosByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getSolicitudCabeceraById(java.util.Map)
     */
    public SolicitudConsolidadoCabecera getDeudaPedidosById(Map criteria) {
        // TODO Auto-generated method stub
        return (SolicitudConsolidadoCabecera) getSqlMapClientTemplate()
                                                  .queryForObject("spusicc.pedidos.PedidosSQL.getSolicitudCabeceraById",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getBolsaPedidosById(java.util.Map)
     */
    public SolicitudConsolidadoCabecera getBolsaPedidosById(Map criteria) {
        // TODO Auto-generated method stub
        return (SolicitudConsolidadoCabecera) getSqlMapClientTemplate()
                                                  .queryForObject("spusicc.pedidos.PedidosSQL.getSolicitudCabeceraById",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getProductoAgregacionById(java.util.Map)
     */
    public ProductoAgregacion getProductoAgregacionById(Map criteria) {
        // TODO Auto-generated method stub
        return (ProductoAgregacion) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getProductoAgregacionByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getOfertaDetalleById(java.util.Map)
     */
    public ProductoAgregacion getOfertaDetalleById(Map criteria) {
        // TODO Auto-generated method stub
        return (ProductoAgregacion) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOfertaDetalleById",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getOfertaDetalleByPeriodoCodigoVenta(java.util.Map)
     */
    public BigDecimal getOfertaDetalleByPeriodoCodigoVenta(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method 'getOfertaDetalleByPeriodoCodigoVenta'");
        }

        log.debug("params=" + params);

        BigDecimal decimal = new BigDecimal(0);

        // Obtenemos los clientes nuevos a actualizar
        List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOfertaDetalleByPeriodoCodigoVenta",
                params);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            decimal = (BigDecimal) iter.next();
        }

        return decimal;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getMatrizFacturacionByPeriodo(java.util.Map)
     */
    public BigDecimal getMatrizFacturacionByPeriodo(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method 'getMatrizFacturacionByPeriodo'");
        }

        log.debug("params=" + params);

        BigDecimal decimal = new BigDecimal(0);

        // Obtenemos los clientes nuevos a actualizar
        List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getMatrizFacturacionByPeriodo",
                params);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            decimal = (BigDecimal) iter.next();
        }

        return decimal;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getDetOfertaIndicaDigitableById(java.util.Map)
     */
    public BigDecimal getDetOfertaIndicaDigitableById(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method 'getDetOfertaIndicaDigitableById'");
        }

        log.debug("params=" + params);

        BigDecimal decimal = new BigDecimal(0);

        // Obtenemos los clientes nuevos a actualizar
        List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetOfertaIndicaDigitableById",
                params);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            decimal = (BigDecimal) iter.next();
        }

        return decimal;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getDetOfertaPrecioCatalogoById(java.util.Map)
     */
    public BigDecimal getDetOfertaPrecioCatalogoById(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method 'getDetOfertaPrecioCatalogoById'");
        }

        log.debug("params=" + params);

        BigDecimal decimal = new BigDecimal(0);

        // Obtenemos los clientes nuevos a actualizar
        List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetOfertaPrecioCatalogoById",
                params);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            decimal = (BigDecimal) iter.next();
        }

        return decimal;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getDetOfertaEstrategiaIndividualById(java.util.Map)
     */
    public BigDecimal getDetOfertaEstrategiaIndividualById(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method 'getDetOfertaEstrategiaIndividualById'");
        }

        log.debug("params=" + params);

        BigDecimal decimal = new BigDecimal(0);

        // Obtenemos los clientes nuevos a actualizar
        List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetOfertaEstrategiaIndividualById",
                params);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            decimal = (BigDecimal) iter.next();
        }

        return decimal;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getCodVentaAgregacById(java.util.Map)
     */
    public BigDecimal getCodVentaAgregacById(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method 'getCodVentaAgregacById'");
        }

        log.debug("params=" + params);

        BigDecimal decimal = new BigDecimal(0);

        // Obtenemos los clientes nuevos a actualizar
        List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodVentaAgregacById",
                params);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            decimal = (BigDecimal) iter.next();
        }

        return decimal;
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeActualizaPrioridad(java.util.Map)
     */
    public void executeActualizaPrioridad(Map queryParams) {
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeActualizaPrioridad",
            queryParams);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#executeProcesoOCRProcesosEspecialesOCS(java.util.Map)
     */
    public void executeProcesoOCRProcesosEspecialesOCS(Map queryParams) {
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeProcesoOCRProcesosEspecialesOCS",
            queryParams);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getControlFacturacionById(java.util.Map)
     */
    public PedidoControlFacturacion getControlFacturacionById(Map criteria) {
        // TODO Auto-generated method stub
        return (PedidoControlFacturacion) getSqlMapClientTemplate()
                                              .queryForObject("spusicc.pedidos.PedidosSQL.getControlFacturacionByCriteria",
            criteria);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#insertControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertControlFacturacion(
        PedidoControlFacturacion controlFacturacion, Usuario usuario) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertControlFacturacion",
            controlFacturacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#insertProductoPedidoMinimo(biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertProductoPedidoMinimo(
        ProductoAgregacion productoAgregacion, Usuario usuario) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertProductoPedidoMinimo",
            productoAgregacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateControlFacturacion(
        PedidoControlFacturacion controlFacturacion, Usuario usuario) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateControlFacturacion",
            controlFacturacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateDeuda(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateDeuda(SolicitudConsolidadoCabecera cabecera,
        Usuario usuario) {
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateDeuda",
            cabecera);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateBolsaPedidosBloqueoIndividual(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateBolsaPedidosBloqueoIndividual(
        SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateBolsaPedidosBloqueoIndividual",
            cabecera);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateDeudaGeneral(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateDeudaGeneral(SolicitudConsolidadoCabecera cabecera,
        Usuario usuario) {
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateDeudaGeneral",
            cabecera);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#updateBolsaPedidosBloqueo(biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateBolsaPedidosBloqueo(
        SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateBolsaPedidosBloqueo",
            cabecera);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#deleteControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
     */
    public void deleteControlFacturacion(
        PedidoControlFacturacion controlFacturacion) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteControlFacturacion",
            controlFacturacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#cerrarCampanaControlFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
     */
    public void cerrarCampanaControlFacturacion(
        PedidoControlFacturacion controlFacturacion) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.cerrarCampanaControlFacturacion",
            controlFacturacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#desactivarProductoPedidoMinimo(biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion)
     */
    public void desactivarProductoPedidoMinimo(
        ProductoAgregacion productoAgregacion) {
        // TODO Auto-generated method stub
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.desactivarProductoPedidoMinimo",
            productoAgregacion);
    }

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPedidoControlFacturacionDAO#getPedidosSiguienteCampanha(java.util.Map)
     */
    public String getPedidosSiguienteCampanha(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method 'getPedidosSiguiente'");
        }

        log.debug("params=" + params);

        String campanha = null;

        // Obtenemos los clientes nuevos a actualizar
        List list = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getPedidosSiguienteCampanha",
                params);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            campanha = (String) iter.next();
        }

        return campanha;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#getNumeroSecuenciaUsuario()
     */
    public String getNumeroSecuenciaUsuario(){
    	return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNumeroSecuenciaUsuario");
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionDAO#deleteInterfazRETProductosReclamados(java.util.Map)
     */
    public void deleteInterfazRETProductosReclamados(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteInterfazRETProductosReclamados",criteria);
	}
}
