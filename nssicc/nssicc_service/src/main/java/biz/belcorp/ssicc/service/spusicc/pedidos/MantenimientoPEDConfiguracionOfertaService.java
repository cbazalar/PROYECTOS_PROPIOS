/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Oferta;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Ivan Tocto
 *
 */
public interface MantenimientoPEDConfiguracionOfertaService extends Service{

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
	 * Obtiene un registro de la matriz
	 * 
	 * @param oidMatriz
	 * @return
	 */
	MatrizFacturacion getMatrizFacturacion(String oidMatriz);

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
	 * Obtine la lista de accesos
	 * 
	 * @return
	 */
	List getOidAccesosByCodigoISO(String codigoIso);
	
	/**
	 * Obtiene el lista de subaccesos por oidAcceso
	 * @param oidAcceso
	 * @return
	 */
	List getOidSubaccesosByOidAcceso(String oidAcceso);

	/**
	 * Obtiene la lista de productos
	 * @param params
	 * @return
	 */
	List getProductosAsociadosByCriteria(Map params);

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
	 * 
	 * @param oidEstrategia
	 * @return
	 */
	String getOidTipoEstrategia(String oidEstrategia);

	/**
	 * Obtiene los tipos de cuadre en base al numero de grupos
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
}
