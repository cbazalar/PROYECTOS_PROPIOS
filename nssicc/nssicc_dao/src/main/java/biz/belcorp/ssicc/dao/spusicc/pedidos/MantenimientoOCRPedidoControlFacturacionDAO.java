package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.LabelPedidosConsoDetalValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoAnuladoFacturado;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProcesoOCRActualizarUnidadesMaximas;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;

public interface MantenimientoOCRPedidoControlFacturacionDAO extends DAO {
	
	
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
    
    public List getDeudaPedidosMasivaByCriteria(Map criteria);
    
    public List getPedidosFacturadosAnuladosByCriteria(Map criteria);    
    
    public List getBloqueoPedidosMasivoByCriteria(Map criteria);    
    
    public void deleteTemporalDeudaMasiva();
    
    public void deleteTemporalBloqueoMasivo();    
    
	public void insertTemporalDeudaMasiva(Map criteria) ;
	
	public void insertTemporalBloqueoMasivo(Map criteria) ;	
	
	/**
	 * Metodo que graba los pedidos anulados
	 * @param criteria
	 */
	public void grabarPedidoFacturadoAnulado(Map criteria) ;	
	
    public List getBolsaPedidosByCriteria(Map criteria);
    
    public List getOCREnviaOCSList(Map criteria);    
    
    public List getOCRConsultorasInactivasList(Map criteria);    
    
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
    
    public void executeProcesoOCRActualizaGP2(Map params);    
    
    public void actualizaOCSHistoricoCabecera(Map params);
    
    public void actualizaOCSHistoricoDetalle(Map params);    
    
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
    public void insertControlFacturacion(PedidoControlFacturacion controlFacturacion, Usuario usuario);
    
    public void insertProductoPedidoMinimo(ProductoAgregacion productoAgregacion, Usuario usuario);    

    /**
     * Actualiza un Archivo Control Facturacion
     * @param
     */
    public void updateControlFacturacion(PedidoControlFacturacion controlFacturacion, Usuario usuario);
    
    public void updateDeuda(SolicitudConsolidadoCabecera cabecera, Usuario usuario);    
    
    public void updateBolsaPedidosBloqueoIndividual(SolicitudConsolidadoCabecera cabecera, Usuario usuario);    
    
    public void updateDeudaGeneral(SolicitudConsolidadoCabecera cabecera, Usuario usuario);    
    
    public void updatePedidoAnuladoFacturado(PedidoAnuladoFacturado anuladoFacturado, Usuario usuario);    
    
    public void updatePedidosAnuladoConsultora(PedidoAnuladoFacturado anuladoFacturado, Usuario usuario);
    
    public void updatePedidosAnulaciones(PedidoAnuladoFacturado anuladoFacturado, Usuario usuario);    
    
    public void updateDeudaGeneralMasiva(SolicitudConsolidadoCabecera cabecera, Usuario usuario);    
    
    public void updateBloqueoGeneral(SolicitudConsolidadoCabecera cabecera, Usuario usuario);    
    
    public void updateBolsaPedidosBloqueo(SolicitudConsolidadoCabecera cabecera, Usuario usuario);    
    
    /**
     * Elimina un Archivo Control Facturacion
     * @param
     */
    public void deleteControlFacturacion(PedidoControlFacturacion controlFacturacion);
    
    public void cerrarCampanaControlFacturacion(PedidoControlFacturacion controlFacturacion);    
    
    public void executeActualizaNumeroLote(Map params);    
    
    public void desactivarProductoPedidoMinimo(ProductoAgregacion  productoAgregacion);    
    
	public String getPedidosSiguienteCampanha(Map params) ;
	
	public String getPedidosNSiguienteCampanha(Map params) ;	

	/**
	 * @param method
	 * @param params
	 * @return
	 */
	public void executePedido(String method, Map params);

	public void updateCargaPedidosMasivoAnulados(Map params);

	public void deleteCargaPedidosAnulados();
	
	public void insertPedidoMasivoFacturadoAnulado(Map criteria, Usuario usuario) ;
	
    public List getConsultoraPedidoCabeceraByCriteria(Map criteria);	
    
    public String getNumLotes(Map params);
    
    public String getNumeroLote(Map params);
    
    public String getNumeroUnidades(Map params);
	
    public LabelPedidosConsoDetalValue getDetalleById(Map params);
    
    public void updateUnidades(Map params);
    
    /**
     * Actualiza todo un bloque de Unidades Administrativas
     * @param bean
     * @return
     */
    public boolean updateUnidadesTotal(ProcesoOCRActualizarUnidadesMaximas bean);
    
    public void executeProcesoRECCargaTablaBoletaRecojoEspecial(Map params);
    
    public List getCampanhasActivasByCriteria(Map criteria);
    
    public List getCampanhasActivasById(Map criteria);
    
    public List getOCRDetalladoAptasList(Map criteria);
    
    public List getOCRPedidosDuplicadosList(Map criteria);
    
    public BigDecimal getCodigoCuponInicioRango(Map params);
    
    public BigDecimal getCodigoCuponFinRango(Map params);
    
    public ProductoAgregacion getDetallePedidoCupon(Map criteria);
    
    public String getEstadoConsultora(Map params);
    
    public String getExistePedidoConsultora(Map params);
    
    public ProductoAgregacion getDetalleProductoById(Map criteria);
    
	/**Ejecuat el proceso para los pedidos que se quedaron por monto minimo o maximo
	 * @param criteria
	 */
	public void executeProcesoMontoMinimoMaximo(Map criteria);
	
	/**Revierte los cambios para pasar de gp1 a gp3 sin validaciones de monto minimo y maximo
	 * @param criteria
	 */
	public void executeProcesoRevertirCambiosGP1STO(Map criteria);
		
	/**
	 * @param params
	 * @return
	 * Devuelve el Maximo de unidades de la tabla de parametria
	 */
	public String getMaximoUnidades(Map params);
	
	/**
	 * @param params
	 * @return
	 * Devuelve la longitud Maxima de unidades de la tabla de parametria
	 */
	public String getLongitudMaximoUnidades(Map params);
	
	/**
	 * Metodo que actualiza los indicadores del archivo de control en forma contraria
	 * @param criteria
	 */
	public void executeActualizarContrarioIndicadores(Map criteria);

	/**
	 * Metodo que trae los pedidos
	 * @param map
	 * @return
	 */
	public List getPedidosByCriteria(Map map);

	/**
	 * Metodo que anula el pedido
	 * @param map
	 */
	public void anularPedidoFacturado(Map map);

	/**
	 * Metodo que elimina pedidos
	 * @param criteria
	 */
	public void eliminarPedidoDigitado(Map criteria);

	/**
	 * Metodo que graba en historico la eliminacion de pedidos digitados
	 * @param criteria
	 */
	public void grabarEliminacionPedidoDigitado(Map criteria);

	/**
	 * Metodo que devuelve el listado de pedidos a eliminar
	 * @param map
	 * @return
	 */
	public List getPedidosAEliminarByCriteria(Map map);

	/**
	 * @param criteria
	 * @return
	 * Devuelve la informacion de la consultora para la cabecera de 
	 * digitacion de pedidos simplificada
	 */
	public List getConsultoraCabeceraSimpleByCriteria(Map criteria);

	/**
	 * @param map
	 * Anula pedidos Marcando flags en INT_SOLIC_CONSO_CABEC y
	 * en sto_docum_digit
	 */
	public void anularPedidoFacturadoSTO(Map map);

	/**
	 * Elimina las clasificaciones de la tabla MAE_CLIEN_CLASI 
	 * @param map
	 */
	public void deleteOCSClasificaciones(Map map);
	
	/**
	 * Metodo que devuelve la lista de codigos de plantillas
	 * @param map
	 * @return
	 */
	public List getListaCodigoPlantilla(Map map);
	
	/**
	 * Metodo que devuelve la lista de plantillas
	 * @param map
	 * @return
	 */
	public List getListaPlantillas(Map map);
	
	/**
	 * Metodo que inserta plantillas en la tabla PED_PLANT_MONIT
	 * @param map
	 */
	public void insertPlantillas (Map map);
	
	/**
	 * Proceso que regenera plantillas
	 * @param map
	 */
	public void executeRegeneraPlantillas(Map map);
	

	/**
	 * Metodo que devuelve la lista de solicitudes
	 * @param map
	 */
	public List getListaSolicitudes(Map map);	

	/**
	 * Metodo que pasa la informacion de Pedidos de la campanha a tablas historicas.
	 * @param params
	 */
	public void executeOCSHistorico(Map params);

	
	/**
	 * Metodo que obtiene la cantidad de pedidos para el numero de lote 
	 * @return
	 */
	public String getNumeroPedidosByLote(Map criteria);
	
	/**
	 * Metodo que obtiene la cantidad de pedidos para el numero de lote 
	 * @return
	 */
	public String getNumeroLoteByPk(Map criteria);

	/**
	 * Metodo que inserta el historico de pedidos antes de cerrar la campaa
	 */
	public void saveHistoricoPedidos();
	
	/**
	 * Metodo que obtiene la cantidad de ocurrencias de un consolidado
	 * @param criteria
	 * @return
	 */
	public List getConsolidadoCabeceraByPK(Map criteria);
	
	/**
	 * Metodo que Levanta/Retorna el indicador de Deuda 
	 * @param criteria
	 */
	public void updateLevantarRetornarDeuda(Map criteria);

	/**
	 * Metodo que valida si el pedido esta de GP2 en adelante
	 * @param params
	 * @return
	 */
	public String validarPedidoMayorGP1(Map params);

	/**
	 * @param map
	 */
	public void cerrarPeriodo(Map map);
	
	/**
	 * Devuelve el indicador si se crea la nueva campana como activa
	 * @param criteria
	 * @return
	 */
	public String getIndicadorActivaNuevaCampana(Map criteria);
	
	/**
	 * Devuelve uno si existe campana activa
	 * @param criteria
	 * @return
	 */
	public String getExistePeriodoActivo(Map criteria);
	
	/**
	 * obtiene el periodo de inicio
	 * @param criteria
	 * @return
	 */
	public String getPeriodoInicioHistorico(Map criteria);
	
	/**
	 * Obtiene la lista de periodos a pasar a historicos
	 * @param criteria
	 * @return
	 */
	public List getListaPeriodosHistoricos(Map criteria);

	/**
	 * Procesos de Facturacion Adicional
	 * @param map
	 */
	public void executeFacturacionAdicional(Map params);
}
