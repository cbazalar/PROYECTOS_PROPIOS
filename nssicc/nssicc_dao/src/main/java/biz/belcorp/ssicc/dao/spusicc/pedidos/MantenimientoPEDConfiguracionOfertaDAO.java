/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CondicionOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetalleOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.GrupoOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Oferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.RangoPromocion;

/**
 * @author Ivan Tocto
 *
 */
public interface MantenimientoPEDConfiguracionOfertaDAO extends DAO {

	/**
	 * Obtiene los periodos disponibles de matriz de facturacion
	 * @return
	 */
	List getPeriodosMatrizFacturacion();

	/**
	 * Obtiene la lista de estimados de matriz de facturacion
	 * @param params
	 * @return
	 */
	List getEstimadosMatrizFacturacion(Map params);

	/**
	 * Inserta un registro en la matriz
	 * 
	 * @param matriz
	 * @param usuario
	 */
	void insertMatrizFacturacion(MatrizFacturacion matriz, Usuario usuario);

	/**
	 * Actualiza un registro en la matriz
	 * 
	 * @param matriz
	 * @param usuario
	 */
	void updateMatrizFacturacion(MatrizFacturacion matriz, Usuario usuario);

	/**
	 * Ejecuta el proceso de asignacion de codigos de venta
	 * 
	 * @param params
	 */
	void executeAsignarCodigoVentaAction(Map params);

	/**
	 * Obtiene el listado de estrategias
	 * 
	 * @return
	 */
	List getEstrategias(Map params);

	/**
	 * Obtiene la lista de accesos
	 * 
	 * @return
	 */
	List getOidAccesosByCodigoISO(String codigoIso);

	/**
	 * Obtiene el lista de subaccesos por oidAcceso
	 * 
	 * @param oidAcceso
	 * @return
	 */
	List getOidSubaccesosByOidAcceso(String oidAcceso);

	/**
	 * 
	 * @param params
	 * @return
	 */
	List getProductosAsociadosByCriteria1(Map params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	List getProductosAsociadosByCriteria2(Map params);

	/**
	 * 
	 * @param oidTipoOferta
	 * @param codigoProducto
	 * @param oidEstrategia
	 * @param precioCatalogo
	 * @param precioPosicionamiento
	 * @return
	 */
	String getValidarTipoOferta(String oidTipoOferta, String codigoProducto, String oidEstrategia, String precioCatalogo, String precioPosicionamiento);

	/**
	 * Obtiene el listado de tipos de cuadre en base al numero de grupos
	 * 
	 * @param numeroGrupos
	 * @return
	 */
	List getTiposCuadre(String numeroGrupos);

	/**
	 * Obtiene el listado de tipos de cuadre de promociones
	 * 
	 * @return
	 */
	List getCondicionPromocionTiposCuadre();

	/**
	 * 
	 * @param oferta
	 * @param usuario
	 */
	void insertOferta(Oferta oferta, Usuario usuario);

	/**
	 * 
	 * @param grupoOferta
	 * @param usuario
	 */
	void insertGrupoOferta(GrupoOferta grupoOferta, Usuario usuario);

	/**
	 * 
	 * @param detalleOferta
	 * @param usuario
	 */
	void insertDetalleOferta(DetalleOferta detalleOferta, Usuario usuario);

	/**
	 * 
	 * @param condicion
	 * @param usuario
	 */
	void insertCondicionOferta(CondicionOferta condicion, Usuario usuario);

	/**
	 * 
	 * @param rangoPromocion
	 * @param usuario
	 */
	void insertRangoPromocion(RangoPromocion rangoPromocion, Usuario usuario);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	List getProductosAsociadosParaGrupo(Map params);
}
