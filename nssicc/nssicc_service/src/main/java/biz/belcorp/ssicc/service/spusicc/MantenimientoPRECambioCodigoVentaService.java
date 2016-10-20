package biz.belcorp.ssicc.service.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.model.CodigoVentaMod;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * interface de codigo de venta opera.
 * <p>
 * <a href="MantenimientoPRECambioCodigoVentaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoPRECambioCodigoVentaService extends Service {

	/**
	 * Metodo que devuelve la lista de tipos de ofertas
	 * @param map
	 * @return
	 */
	public List getTipoOfertaList(Map map);
	
	/**
	 * Metodo que devuelve la lista de tipos de variantes
	 * @param map
	 * @return
	 */
	public List getTipoVarianteList(Map map);
	
	/**
	 * Metodo que devuelve la lista de forma de pagos
	 * @param map
	 * @return
	 */
	public List getFormaPagoList(Map map);
	
	/**
	 * Metodo que devuelve la lista de Tipo de Cliente
	 * @param map
	 * @return
	 */
	public List getTipoClienteList(Map map);
	
	/**
	 * Metodo que devuelve la lista de Sub Tipo de Cliente
	 * @param map
	 * @return
	 */
	public List getSubTipoClienteList(Map map);
	
	/**
	 * Metodo que devuelve la lista de Tipo de Clasificacion
	 * @param map
	 * @return
	 */
	public List getTipoClasificacionList(Map map);
	
	/**
	 * Metodo que devuelve la lista de Clasificacion
	 * @param map
	 * @return
	 */
	public List getClasificacionList(Map map);
	
	/**
	 * Metodo que devuelve la lista de Estatus Cliente
	 * @param map
	 * @return
	 */
	public List getEstatusClienteList(Map map);
	
	/**
	 * Metodo que devuelve el oid del codigo de variante
	 * @param map
	 * @return
	 */
	public String getOidVariante(Map map);
	
	/**
	 * Metodo que devuelve el codigo de forma de pago
	 * @param map
	 * @return
	 */
	public String getCodFormaPago(Map map);	
	
	/**
	 * Metodo para devolver la lista de codigos de venta
	 * @param map
	 * @return
	 */
	public List getCodigoVentaList(Map map);
	
	/**
	 * Metodo que elimina una oferta
	 * @param map
	 */
	public void executeEliminarOferta(Map map);
	
	/**
	 * Metodo que elimina un CUV
	 * @param map
	 */
	public void executeEliminarCUV(Map map);
	
	/**
	 * Metodo que obtiene los datos de un Codigo de Venta
	 * @param map
	 * @return
	 */
	public CodigoVentaMod getCodigoVentaObject(Map map);
	
	/**
	 * Metodo que modifica los datos de un CUV
	 * @param map
	 */
	public void executeModificarDatosCUV(Map map);
	
	/**
	 * Metodo que modifica los datos de una oferta
	 * @param map
	 */
	public void executeModificarDatosOferta(Map map);
	
	/**
	 * Metodo que inserta la Venta Exclusiva
	 * @param map
	 */
	public void executeInsertVentaExclusiva(Map map);
	
	/**
	 * Metodo que elimina una Venta Exclusiva
	 * @param map
	 */
	public void deleteVentaExclusiva(Map map);
	
	/**
	 * Metodo que copia una oferta
	 * @param map
	 */
	public void executeCopiarOferta(Map map);
	
	/**
	 * Metodo que borra la tabla temporal de la
	 * lista de los CUVs a modificar
	 * @param map
	 */
	public void deleteCUVTemporal(Map map); 
	
	/**
	 * Metodo que inserta en la tabla temporal la 
	 * lista de CUV a modificar
	 * @param map
	 */
	public void insertaCUVTemporal(Map map);
	
	/**
	 * Metodo que obtiene la lista de CUVs a modificar
	 * @param map
	 * @return
	 */
	public List getCodigoVentaModificarList(Map map);
	
	/**
	 * Metodo que verifica si un CUV ya existe en la tabla
	 * @param params
	 * @return
	 */
	public Integer getExisteCUV(Map params);
	
	
	/**
	 * Metodo que verifica si un CUV ya esta Facturado
	 * @param map
	 * @return
	 */
	public Integer getExisteCUVFacturado(Map map);
	
	/**
     * Retorna el numero de secuencia del usuario en la sesin 
     * @return
     */
    public String getNumeroSecuenciaUsuario();
    
    /**
     * Devuelve la lista de Estrategias
     * @param map
     * @return
     */
    public List getEstrategiaList(Map map);
    
    /**
     * Devuelve el periodo activo
     * @param map
     * @return
     */
    public String getPeriodoActivo(Map map);
    
    /**
     * Valida si existen Ofertas Facturados
     * @param map
     * @return
     */
    public String getExisteOfertasFacturados(Map map);
    
    
    /**
     * Devuelve lista de seccion grupos
     * @param map
     * @return
     */
    public Map getListGrupo(Map map);
    
    /**
     * Devuelve lista de indicador de cuadre
     * @param map
     * @return
     */
    public List getIndicadorCuadreList(Map map);
    
    /**
	 * Actualiza registro factor de cuadre
	 * 
	 * @param params
	 */
	public void updateFactorCuadre(Map params);
	
	/**
	 * Actualiza registro factor e indicador de cuadre
	 * 
	 * @param params
	 */
	public void updateFactorIndicadorCuadre(Map params);
	/**
     * Devuelve lista de secciones
     * @param map
     * @return
     */
    public Map getSeccionesList(Map map);
    
    /**
     * Devuelve lista de tipos de cuadre
     * @param map
     * @return
     */
    public List getTipoCuadreList(Map map);
    
    /**
     * Devuelve lista de catalogos
     * @param map
     * @return
     */
    public List getCatalogoList(Map map);
    
    /**
     * Devuelve lista de criterios
     * @param map
     * @return
     */
    public List getCriteriosList(Map map);
    /**
	 * Metodo que inserta criterios
	 * @param map
	 */
	public void insertaCriterio(Map map);
	
	/**
	 * Metodo que elimina un criterio
	 * @param map
	 */
	public void deleteCriterio(Map map);
	
	/**
	 * @param map
	 */
	public void deletePromo(Map map);
	
	/**
	 * @param map
	 */
	public void insertaPromo(Map map);
	
	/**
	 * Devuelve Lista de Componentes
	 * @param map
	 * @return
	 */
	public List getComponentesList(Map map);
	
	/**
	 * Metodo que devuelve el OID DEL PRODUCTO
	 * @param map
	 * @return
	 */
	public String getProductoBySAP(Map map);
	
	/**
	 * @param map
	 */
	public void insertHistoricoCUV(Map map);
	
	/**
	 * @param params
	 */
	public void updateFactorCuadreCondicion(Map params);
	
	/**
	 * Devuelve criterio por id
	 * @param map
	 * @return
	 */
	public Map getCriterioById(Map map);
	
	/**
	 * Devuelve Lista de Grupos a mostrar
	 * @param map
	 * @return
	 */
	public List getPanelGrupos(Map map);
	
	/**
	 * Metodo que elimina una Producto de un grupo
	 * @param map
	 */
	public void deleteProductoGrupoOfertaDetalle(Map map);
	
	/**
	 * Metodo que inserta una Producto de un grupo
	 * @param map
	 */
	public void insertProductoGrupoOfertaDetalle(Map map);
	
	/**
	 * Metodo que actualiza una Producto de un grupo
	 * @param map
	 */
	public void updateProductoGrupoOfertaDetalle(Map map);
	
	/**
	 * Metodo que devuelve el oid del tipo de oferta
	 * @param map
	 * @return
	 */
	public List getOidTipoOferta(Map map);
	
	/**
	 * Metodo que actualiza un Producto Principal o Asociado de una Oferta
	 * @param map
	 */
	public void updateProductoPrincipalAsociadoOferta(Map map);
	
	/**
	 * Metodo que inserta un Producto Principal o Asociado de una Oferta
	 * @param map
	 */
	public void insertProductoPrincipalAsociadoOferta(Map map);
	
	/**
	 * Metodo que devuelve la venta exclusiva por oferta
	 * @param map
	 * @return
	 */
	public List getVentaExclusivaOferta(Map map);
	
	/**
	 * Metodo que elimina una Venta Exclusiva
	 * @param map
	 */
	public void deleteVentaExclusivaPorOidVentaExclusiva(Map map);
	
	/**
	 * Metodo que elimina la Oferta Individual
	 * @param map
	 */
	public void deleteOfertaIndividual(Map map);
	
	/**
	 * @param map
	 */
	public void insertPromoProduCompoTemporal(Map map);
	
	/**
	 * @param map
	 */
	public void deletePromoProduCompoTemporal(Map map);
	
	/**
	 * Metodo que devuelve los componentes de una Oferta según criterios
	 * @param map
	 * @return
	 */
	public List getPromoProduCompoTemporalList(Map map);

}