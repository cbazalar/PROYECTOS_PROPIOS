package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.Reclamos;
import biz.belcorp.ssicc.service.framework.Service;


public interface MantenimientoRECOperacionService extends Service {
    /**
     * Obtiene un listado de todos las operaciones homologadas
     *
     * @param criteria
     *            Map con los parametros del query
     * @return Lista de objetos Base, poblados
     * @author Marco Silva
     */
    public List getOperacionesHomologadasByCodigoPais(Map criteria);
    
    public List getOperacionesByOperacionHomologada(Map criteria);

    public List getOperacionesHomologadasByCriteria(Map criteria);

    public Reclamos getOperacionesHomologadasById(Map criteria);

    public void insertOperacionesHomologadas(Reclamos reclamos, Usuario usuario);

    public void deleteOperacionesHomologadas(Reclamos reclamos);

    public void deleteInterfazRETRecepcionarVentasRetailDetDet();
    
	/**
	 * Se inserta en la tabla temporal los registros 
	 * @param criteria
	 */
	public void insertRECProductosList(Map criteria) ;    

    /**
    * Obtiene un listado de todos los  Archivos de Control de Facturacion
    * los cuales son enviados a travs de un Map.
    *
    * @param criteria
    *            Objeto Map cuyos atributos son usados como criterios de
    *            bsqueda.
    *
    * @return Lista de objetos Map, poblados.
    */
    public List getControlFacturacionByCriteria(Map criteria);

    public List getProductoAgregacionByCriteria(Map criteria);

    public List getDeudaPedidosByCriteria(Map criteria);

    public List getBolsaPedidosByCriteria(Map criteria);

    public SolicitudConsolidadoCabecera getDeudaPedidosById(Map criteria);

    public SolicitudConsolidadoCabecera getBolsaPedidosById(Map criteria);

    public ProductoAgregacion getProductoAgregacionById(Map criteria);

    public ProductoAgregacion getOfertaDetalleById(Map criteria);

    public BigDecimal getOfertaDetalleByPeriodoCodigoVenta(Map criteria);

    public BigDecimal getMatrizFacturacionByPeriodo(Map criteria);

    public BigDecimal getDetOfertaIndicaDigitableById(Map criteria);

    public BigDecimal getDetOfertaPrecioCatalogoById(Map criteria);

    public BigDecimal getDetOfertaEstrategiaIndividualById(Map criteria);

    public BigDecimal getCodVentaAgregacById(Map criteria);

    public void executeActualizaPrioridad(Map params);

    public void executeProcesoOCRProcesosEspecialesOCS(Map params);

    /**
     * Obtiene la informacion de un Control de Facturacion en base a su llave primaria. La
     * excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si
     * no es encontrada.
     *
     * @param criteria contiene la PK de la tabla
     * @return Objeto de tipo PedidoControlFacturacion, poblado.
     */
    public PedidoControlFacturacion getControlFacturacionById(Map criteria);

    /**
     * Inserta un Archivo Control Facturacion
     * @param
     */
    public void insertControlFacturacion(
        PedidoControlFacturacion controlFacturacion, Usuario usuario);

    public void insertProductoPedidoMinimo(
        ProductoAgregacion productoAgregacion, Usuario usuario);

    /**
     * Actualiza un Archivo Control Facturacion
     * @param
     */
    public void updateControlFacturacion(
        PedidoControlFacturacion controlFacturacion, Usuario usuario);

    public void updateDeuda(SolicitudConsolidadoCabecera cabecera,
        Usuario usuario);

    public void updateBolsaPedidosBloqueoIndividual(
        SolicitudConsolidadoCabecera cabecera, Usuario usuario);

    public void updateDeudaGeneral(SolicitudConsolidadoCabecera cabecera,
        Usuario usuario);

    public void updateBolsaPedidosBloqueo(
        SolicitudConsolidadoCabecera cabecera, Usuario usuario);

    /**
     * Elimina un Archivo Control Facturacion
     * @param
     */
    public void deleteControlFacturacion(
        PedidoControlFacturacion controlFacturacion);

    public void cerrarCampanaControlFacturacion(
        PedidoControlFacturacion controlFacturacion);

    public void desactivarProductoPedidoMinimo(
        ProductoAgregacion productoAgregacion);

    public String getPedidosSiguienteCampanha(Map params);
    
    /**
     * Retorna el numero de secuencia del usuario en la sesin 
     * @return
     */
    public String getNumeroSecuenciaUsuario();
    
    /**
     * Borrar los registros de la tabla temporal con el numero de secuencia del usuario
     * @param criteria
     */
    public void deleteInterfazRETProductosReclamados(Map criteria);
    
    /**
     * Actualiza las unidades devueltas en SICC
     * @param params
     */
    public void executeProcesoRECActualizaUnidadesDevueltas(Map params);
}
