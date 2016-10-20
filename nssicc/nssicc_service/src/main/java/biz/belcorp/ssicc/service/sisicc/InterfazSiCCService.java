/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.EstimadoProductos;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSiCCService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

/**
 * @author peextrromero
 *
 */
public interface InterfazSiCCService extends Service {

	/**
	 * Genera Lista en base al Maps y la lista enviada
	 * @param tipoLista
	 * @param params
	 * @return
	 */
	public List getLista(String tipoLista, Map params);	
	
	/**
	 * Obtiene un listado de todas las marcas disponibles en la tabla
	 * SEG_MARCA@SICC
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getMarcas();

	/**
	 * Obtiene un listado de todas los rangos de perodos disponibles en la
	 * tabla CRA_RANGO_PERIO@SICC
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getRangosPeriodo();
	
	public List getRegion();

	/**
	 * Obtiene un listado de todos los canales disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getCanalesByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los accesos disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */

	public List getCanalesRolByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los accesos disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */

	public List getEstadosRolByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los accesos disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */

	public List getAccesosByCodigoISO(String codigo);
	
	/**
	 * Obtiene un listado de todos los accesos disponibles en base al canal y el
	 * codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos AccesoCanal, poblados.
	 */
	public List getAccesosByCanalByCodigoISO(String codigo);

	public List getInterfazSATEnviarBoletaDespacho(Map criteria);
	/**
	 * Obtiene un listado de todos los accesos disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */

	public List getAccesosTodosByCanalByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los subaccesos disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Subacceso, poblados.
	 */
	public List getSubaccesosByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los subaccesos disponibles en base al codigo
	 * ISO y codigo de Acceso.
	 * 
	 * @param criteria
	 *            filtro de criterios
	 * 
	 * @return Lista de objetos Subacceso, poblados.
	 */
	public List getSubaccesosByCriteria(Map criteria);
	
	/**
	 * Obtiene un listado de todos los tipos de clientes disponibles en base al
	 * codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getTiposClientesByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los sub-tipos de clientes disponibles en base
	 * al codigo ISO.
	 * 
	 * @param criteria
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getSubTiposClientesByCriteria(Map criteria);

	/**
	 * Obtiene la lista de periodos en base al tipo.
	 * 
	 * @param tipo
	 *            Tipo de periodo.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getSublineaxLinea(Map criteria);
	
	/**
	 * Obtiene la lista de periodos en base al tipo, por Pais, Marca y Canal.
	 * 
	 * @param tipo
	 *            Tipo de periodo.
	 * 
	 * @return Lista de objetos Periodo, poblados.
	 */
	public List getPeriodosByTipoPMC(String tipo);

	/**
	 * Obtiene la lista de actividades disponibles, en base al codigo ISO y al
	 * codigo de marca.
	 * 
	 * @param codigoIsoIdioma
	 *            Codigo ISO del idioma.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getActividades(String codigoIsoIdioma, String codigoMarca);

	/**
	 * Obtiene la lista de actividades disponibles, en base al codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO.
	 * 
	 * @return Lista de objetos Actividad, poblados.
	 */
	public List getActividadesByCodigoISO(String codigo);

	/**
	 * Obtiene la data para la Interfaz MyEbelCronogramaFacturacion, en base a
	 * un conunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazMyEbelCronogramaFacturacion, poblados
	 */
	public List getInterfazMyEbelCronogramaFacturacion(Map params);

	/**
	 * Obtiene la data para la Interfaz BELUbicacionesGeograficas, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELUbicacionesGeograficas, poblados
	 */
	public List getInterfazBELUbicacionesGeograficas(Map params);

	/**
	 * Obtiene la data para la Interfaz SABIncentivosConsultoras, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSABIncentivosConsultoras, poblados
	 */
	public List getInterfazSABEnviarIncentivosConsultoras(Map params);

	/**
	 * Obtiene la data para la Interfaz BELDireccionClientes, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELDireccionClientes, poblados
	 */
	public List getInterfazBELDireccionClientes(Map params);

	/**
	 * Obtiene la data para la Interfaz BELUnidadesAtendidas, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELUnidadesAtendidas, poblados
	 */
	public List getInterfazBELUnidadesAtendidas(Map params);

	/**
	 * Obtiene la data para la Interfaz BELPorcentajesReferencias, en base a un
	 * conjunto de parametros que le son pasados en un Map.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELPorcentajesReferencias, poblados
	 */
	public List getInterfazBELPorcentajesReferencia(Map params);

	/**
	 * 
	 * @param codigoPais
	 * @param codigoCliente
	 * @param fechaDocumentoDesde
	 * @param fechaDocumentoHasta
	 * @param fechaVencimientoDesde
	 * @param fechaVencimientoHasta
	 * @return
	 */
	public Double getSaldoCtaCteCliente(String codigoPais,
			String codigoCliente, String fechaDocumentoDesde,
			String fechaDocumentoHasta, String fechaVencimientoDesde,
			String fechaVencimientoHasta);

	/**
	 * Obtiene la data para la Interfaz RetailMatrizCampanya, en base a un
	 * conunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRetailMatrizCampanya, poblados.
	 */
	public List getInterfazRETEnviarMatrizCampanya(Map params);

	/**
	 * Obtiene la data para la Interfaz MovimientosCuentaCorriente, en base a un
	 * conunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazMovimientosCuentaCorriente, poblados.
	 */
	public List getInterfazMyEbelMovimientosCuentaCorriente(Map params);

	/**
	 * Obtiene la data para la Interfaz RetailFacturasVentaDirecta, en base a un
	 * conunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRetailFacturasVentaDirecta, poblados.
	 */
	public List getInterfazRETEnviarFacturasVentaDirecta(Map params);

	/**
	 * Obtiene la data para la Interfaz RetailComplementoFacturasVentaDirecta,
	 * en base a un conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRetailComplementoFacturasVentaDirecta,
	 *         poblados.
	 */
	public List getInterfazRETEnviarComplementoFacturasVentaDirecta(Map params);

	/**
	 * Obtiene la data para la Interfaz ReutilizacionDocumentosAnulados, en base
	 * a un conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazRetailDocumentosAnulados, poblados.
	 */
	public List getInterfazReutilizacionDocumentosAnulados(Map params);

	/**
	 * Obtiene la data para la Interfaz OCRRegiones, en base a un conjunto de
	 * parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazOCRRegiones, poblados.
	 */
	public List getInterfazOCREnviarRegiones(Map params);

	/**
	 * Obtiene la data para la Interfaz OCRZonas, en base a un conjunto de
	 * parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazOCRZonas, poblados.
	 */
	public List getInterfazOCREnviarZonas(Map params);

	/**
	 * Obtiene la data para la Interfaz SAM Inicializacion de Stocks, en base a
	 * un conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSAMInicializacionStocks, poblados.
	 */
	public List getInterfazSAMEnviarInicializacionStocks(Map params);

	/**
	 * Setea la data para la Interfaz COM Recepcin Cdigos de Planilla.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazCOMRecopla, poblados.
	 */
	public List setInterfazCOMRecopla(List listaAhorros);

	/**
	 * Obtiene un listado de todos los almacenes disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getAlmacenesByCodigoISO(String codigo);

	/**
	 * Obtiene un listado de todos los almacenes disponibles en base al codigo
	 * ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getAlmacenesByCodigoISOPais(Map params);

	/**
	 * Obtiene un listado de todos los estados de las mercaderias disponibles en
	 * base al codigo ISO.
	 * 
	 * @param codigo
	 *            Codigo ISO
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getEstadosMercaderiaByCodigoISO(String codigo);

	/**
	 * Obtiene la data para la Interfaz SAB Rentabilidad Por Zona, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSABRentabilidadPorZona, poblados.
	 */
	public List getInterfazSABEnviarRentabilidadPorZona(Map params);

	/**
	 * Obtiene la data para la Interfaz SAB Fuente Ventas Prevista, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazSABFuenteVentasPrevista, poblados.
	 */
	public List getInterfazSABEnviarFuenteVentasPrevista(Map params);

	/**
	 * Obtiene un listado de todas las sociedades disponibles en base al codigo
	 * del pais.
	 * 
	 * @param codigo
	 *            Codigo del pais.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getSociedadesByCodigoPais(String codigo);

	/**
	 * Obtiene la data para la Interfaz BEL Facturas Cabecera, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELFacturasCabecera, poblados.
	 */
	public List getInterfazBELFacturasCabecera(Map params);

	/**
	 * Obtiene la data para la Interfaz BEL Facturas Detalle, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazBELFacturasDetalle, poblados.
	 */
	public List getInterfazBELFacturasDetalle(Map params);

	/**
	 * Obtiene la data para la Interfaz REU Matriz Campanya, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazREUMatrizCampanya, poblados.
	 */
	public List getInterfazReutilizacionMatrizCampanya(Map params);

	/**
	 * 
	 * @param codigoPeriodo
	 * @param codigoTipoCliente
	 * @param codigoSubtipoCliente
	 * @param codigoTipoOferta
	 * @param codigoNegocio
	 * @param codigoUnidadNegocio
	 * @param codigoMarcaProducto
	 * @param exclusionTipoOferta
	 * @return
	 */
	public String getDescuentoVarios(String codigoPeriodo,
			String codigoTipoCliente, String codigoSubtipoCliente,
			String codigoTipoOferta, String codigoNegocio,
			String codigoUnidadNegocio, String codigoMarcaProducto,
			String exclusionTipoOferta);

	/**
	 * 
	 * @param idCabecera
	 * @return
	 */
	public String getDescuentoEspecifico(int idCabecera);

	/**
	 * Obtiene la data para la Interfaz GIS Direccion Consultoras, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazGISEnviarDireccionConsultoras, poblados.
	 */
	public List getInterfazGISEnviarDireccionConsultoras(Map params);

	/**
	 * Obtiene la data para la Interfaz BEL Perdos Facturacin, en base a un
	 * conjunto de parametros.
	 * 
	 * @param params
	 *            Parametros para obtener la data de la interfaz.
	 * 
	 * @return Lista de objetos InterfazREUPeriodosFacturacion, poblados.
	 */

	public List getInterfazREUPeriodosFacturacion(Map params);

	/**
	 * Obtiene una lista de los clientes que cumplan el los criterios
	 * establecidos.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return Lista de objetos de tipo Cliente, poblados.
	 */
	public List getClientesSICCByCriteria(Map criteria);

	/**
	 * Obtiene los datos de un cliente en base a su codigo.
	 * 
	 * @param codigo
	 *            Codigo del cliente.
	 * 
	 * @return Objeto de tipo Cliente, poblado
	 */
	public Cliente getClienteSICCByCodigo(Map criteria);

	/**
	 * Obtiene la lista de clientes por actualizar UA, en base a un criterio de
	 * busqueda.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return Lista de objetos de tipo ClientePendiente, poblados.
	 */
	public List getClientesPendientesActualizarUA(Map criteria);

	/**
	 * Obtiene la lista de clientes por actualizar UA, en base a un criterio de
	 * busqueda.
	 * 
	 * @param criteria
	 *            Criterios de busqueda.
	 * 
	 * @return Lista de objetos de tipo ClientePendiente, poblados.
	 */
	public List getClientesPendientesActualizarXLSUA(Map criteria);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */
	public byte[] getBytesReporteClientesPendientes(Map params,
			Usuario usuario, Pais pais);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */
	public List getReporteConsultorasRecomendadasPDF(Map criteria);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */

	public byte[] getBytesReporteConsultorasRecomendadasPDF(Map params,
			Usuario usuario, Pais pais);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */

	public List getReporteConsultorasRecomendadasXLS(Map criteria);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */

	public byte[] getBytesReporteConsultorasRecomendadasXLS(Map params,
			Usuario usuario, Pais pais);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */

	public List getPeriodosByPMC(Map criteria);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */

	public List getPeriodosDefaultByPMC(Map criteria);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */

	public String getPeriodoDefaultByPMC(Map criteria);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */

	public List getInterfazCOMEnviarLibretaAhorros(Map params);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */
	public List getInterfazCOMEnviarPagoLideres(Map params);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */
	public List getInterfazCOMEnviarLideresNuevas(Map params);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getSociedadByCodigo(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getAlmacenByCodigo(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getCanalByCodigo(Map criteria);

	/**
	 * 
	 * @param periodo
	 * @return
	 */
	public Base getRangoPeriodoByCodigo(String periodo);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getRegionByCodigo(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getZonaByCodigo(Map criteria);

	/**
	 * 
	 * @param codigoMarca
	 * @return
	 */
	public Base getMarcaByCodigo(String codigoMarca);

	/**
	 * Obtiene un listado de todas las cuentas corrientes en base al codigo del
	 * pais.
	 * 
	 * @param codigo
	 *            Codigo del pais.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getCuentasCorrientesByCodigoPais(String codigo);

	/**
	 * Obtiene un listado de todas los tipos de transacciones en base al codigo
	 * del pais.
	 * 
	 * @param codigo
	 *            Codigo del pais.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getTiposTransaccionesByCodigoPais(String codigo);

	/**
	 * Obtiene un listado de todas los horarios.
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public List getHorarios();

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERListaGenerica(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */

	public List getReportePERListaControlCliente(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */

	public List getReportePERListaCargosFacturacion(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERListaCargosDirectos(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERListaAbonosPorNotaCredito(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERListaAbonosDirectos(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERListaAbonosDirectosPercepciones(Map criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List getReportePERListaAbonosPorCobranza(Map criteria);

	/**
	 * Obtiene un arreglo de bytes del reporte.
	 * 
	 * @param params
	 *            Parametros para extraer el reporte.
	 * 
	 * @param usuario
	 *            Usuario quein ejecuta la accion.
	 * 
	 * @param pais
	 *            Pais al que pertenece el usuario.
	 * 
	 * @return Array de tipo byte.
	 */
	public byte[] getBytesReporteCuentasCorrientes(Map params, Usuario usuario,
			Pais pais);

	public byte[] getBytesReporteControlCliente(Map params, Usuario usuario,
			Pais pais);

	public byte[] getBytesReporteLibroPercepciones(Map params, Usuario usuario,
			Pais pais);

	public byte[] getBytesAntiguedadDeudasMeses(Map params, Usuario usuario,
			Pais pais);

	public byte[] getBytesAntiguedadDeudasPeriodos(Map params, Usuario usuario,
			Pais pais);

	public List getReportePERAntiguedadDeudasMesesHaceDosAnhos(Map criteria);

	public List getReportePERAntiguedadDeudasMesesHaceUnAnho(Map criteria);

	public List getReportePERAntiguedadDeudasMesesActual(Map criteria);

	public List getReportePERAntiguedadDeudasPeriodosHaceDosAnhos(Map criteria);

	public List getReportePERAntiguedadDeudasPeriodosHaceUnAnho(Map criteria);

	public List getReportePERAntiguedadDeudasPeriodosActual(Map criteria);

	public List getInterfazMYEPercepcionesVentaDirectaCabecera(Map params);

	public List getInterfazMYEPercepcionesVentaDirectaDetalle(Map params);

	public List getInterfazMYECodigoAutorizacionSunat(Map params);

	public List getConsultorasByCriteria(Map criteria);
	
	public List getProductosByCriteria(Map criteria);

	public List getTipoDocumentosByCodigoISO(Map criteria);

	public List getTipoComprobantesPago();

	/**
	 * Obtiene la data de la cabecera para el consolidado de OCS de la Interfaz
	 * OCR.
	 * 
	 * @param params
	 *            Map con los parametros del query
	 * @return List con Maps de las filas del consolidado
	 * @author Lennon Shimokawa
	 */
	public List getInterfazOCRConsolidadoOCSCabecera(Map params);

	/**
	 * Obtiene la data del detalle para el consolidado de OCS de la Interfaz
	 * OCR.
	 * 
	 * @param params
	 *            Map con los parametros del query
	 * @return List con Maps de las filas del consolidado
	 * @author Lennon Shimokawa
	 */
	public List getInterfazOCRConsolidadoOCSDetalle(Map params);

	/**
	 * Obtiene los subaccesos de Gerente de Zona seguido de los subaccesos de
	 * los Bellcenters.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return List con Maps con los codigos y descripciones de los subaccesos
	 * @author Lennon Shimokawa
	 */
	public List getInterfazRECProductosReclamadosSubAccesos(Map params);

	/**
	 * Obtiene un listado de todos los tipos de ingreso disponibles en base al
	 * codigo ISO.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return Lista de objetos Base, poblados
	 * @author Lennon Shimokawa
	 */
	public List getTiposIngresoByCodigoISO(Map criteria);

	/**
	 * Obtiene un listado de las Cuentas Corrientes que pueden ser filtradas por
	 * Pais, Banco y/o Sociedad
	 * 
	 * @param criteria
	 *            Map con los codigos de Pais, Sociedad o Banco
	 * @return Lista de objetos Base poblados.
	 */
	public List getCuentasCorrientesByPaisBancoSociedad(Map criteria);

	/**
	 * Obtiene un List de los Bancos segun un determinado Pais
	 * 
	 * @param criteria
	 *            Map con el codigo del Pais
	 * @return Lista de objetos Base poblados.
	 */
	public List getBancosByPais(Map criteria);

	/**
	 * Obtiene un listado de todos las operaciones disponibles en base al codigo
	 * ISO.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return Lista de objetos Base, poblados
	 * @author Marco Silva
	 */
	public List getOperacionesByCodigoPais(Map criteria);

	/**
	 * Obtiene tipo de Recepcion MICA y OCS (de los paquetes)
	 * @param criteria
	 * @return
	 */
	public List getTiposRecepcionMICAyOCS();	

	/**
	 * Obtiene un listado de todos los productos reclamados en base al codigo
	 * ISO.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return Lista de objetos Base, poblados
	 * @author Marco Silva
	 */
	public List getRECProductosList(Map criteria);

	/**
	 * Consulta de todos los productos reclamados en base al codigo ISO.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return Lista de objetos Base, poblados
	 * @author Marco Silva
	 */
	public List getConsultaRECProductosList(Map criteria);

	/**
	 * Obtiene un listado de todos los productos reclamados xLote en base al
	 * codigo ISO.
	 * 
	 * @param criteria
	 *            Map con los parametros del query
	 * @return Lista de objetos Base, poblados
	 * @author Marco Silva
	 */
	public List getRECProductosListxLote(Map criteria);

	/**
	 * Obtiene un listado de los Tipos de Origen de Datos
	 * 
	 * @return Lista con los Tipos de Origen de Datos
	 */
	public List getTipoOrigenDatos();
	
	/**
	 * Obtiene un listado de los Tipos de Origen de Datos
	 * 
	 * @return Lista con los Tipos de Origen de Datos
	 */
	public List getTipoOrigenDatos2(Map criteria);
	
	/**
	 * Obtiene un listado de los Tipos de Origen de Datos para Recaudos Bancarios
	 * 
	 * @return Lista con los Tipos de Origen de Datos para Recaudos Bancarios.
	 */
	public List getTipoOrigenDatosRecaudosBancarios();

	/**
	 * Obtiene un listado de los Tipos de Cierre
	 * 
	 * @return Lista con los Tipos de Cierre
	 */
	public List getTiposCierres();
	
	
	/**
	 * Obtiene un listado de los Tipos de Grupo de Procesos
	 * 
	 * @return Lista con los Tipos de Grupo de Procesos
	 */
	public List getGrupoProceso();

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public byte[] getBytesReportePERLiquidacionCobranza(Map criteria);

	public List getComprobantesByPaisBancoCuentaCorriente(Map criteria);

	public List getConcursosByPaisMarcaCanalPeriodo(Map criteria);

	public List getTiposClasificacionesByCriteria(Map criteria);

	public List getClasificacionesByCriteria(Map criteria);

	public List getTiposVinculosByPais(String codigoPais);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public byte[] getBytesReportePERBaseRecuperacionCampanhasPais(Map params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public byte[] getBytesReportePERBaseRecuperacionCampanhasRegion(Map params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public byte[] getBytesReportePERBaseRecuperacionCampanhasZona(Map params);

	
	public List getConcursos(Map criteria);

	public List  getPlantillasConcursos();
	
	/**
	 * Obtiene el Periodo que esta corriendo actualmente
	 * @param codigoPais
	 * @param codigoCanal
	 * @return
	 */
	public String getPeriodoDefaultByPaisCanal(String codigoPais,
			String codigoCanal);

	/**
	 *
	 * @param criteria
	 * @return
	 */
	public byte[] getBytesReportePERFacturasPendientesSeccion(Map criteria);

	/**
	 *
	 * @param criteria
	 * @return
	 */
	public byte[] getBytesReporteEVIMicaRecepcionPedidosZona(Map criteria);
	
	/**
	 *
	 * @param criteria
	 * @return
	 */
	public byte[] getBytesReporteEVIMicaRecepcionPedidosRegion(Map criteria);
	
	
	public List getZonaByPaisZona(Map params);
	
	
	/**
	 * Obtiene un listado de todas las comisiones
	 * 
	 * @return Lista de objetos Base.
	 */
	public List getComision();

	public String getNumeroLoteIntHistoLotes(Map params, String interfaz);
    
    /**
     * Actualiza el flag de transferencia de clientes, para que no sean enviados
     * por la interfaz la proxima vez que se ejecute.
     */
    public void updateInterfazREUIndicadorTransferenciaClientes();

	public List getTiposClientesByCodigoISOOID(String codigoISO);
	
	public List getTiposSolicitudPais(String codigoISO);

	public void deleteTablaControlIVR();

	public void executeGenerarBoletasRecojo(Map params);

	/**
	 * Genera el Archivo para la impresion de Boletas de Recojo
	 * @param params	  
	 * 
	 */
	public void executeGenerarXMLBoletasRecojo(Map params);

	public void executeGenerarXMLBoletasRecojoORA(Map params);

	public String executeExportarMatrizLBEL(Map params);
	
	public void executeCambioCodigoVenta(Map params);
	
	public List getNumeroLoteByFact(Map params);
	
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getFacturacion(Map params);
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getAccesoByCanal(Map params);
	
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getSubaccesoByAcceso(Map params);
	
	
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getZonasByPais(Map params);
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getDescripcionByProducto(Map params);
	
	
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getCentroDistribucionByPais(Map params);
	
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getMapaByCentro(Map params);
	
	public List getVersionByMapa(Map params);
	
	public List getVersionSinP(Map params);
	
	public List getVersionByMapaAnt(Map params);
	/**
	 * Obtiene una lista de centro de distribucion en base a criterios de busqueda.
	 * 
	 * @param params
	 *            Parametros para la consulta, codigoPais.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getTipoSol(Map params);
	
	public List getEstadosPedidos();
	
	/**
	 * Obtiene una lista de lineas de armado dependiendo delcentro de distribucion .
	 * 
	 * @param params
	 *            Parametros para la consulta, centroDistribucion.
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getLinea(Map params);

	public List getRECConsolidadoUnidadesAlmacenVirtual(Map criteria);
	
	public List getConsolidadoTransferenciaBoletasRecojoSinAnulacion(Map criteria);

	public List getConsolidadoTransferenciaBoletasRecojoConAnulacion(Map criteria);
	
	/**
	 * @param codigo
	 * @return
	 */
	public List getListCodComision(String codigo);

	public void setInterfazSATRecepcionarEstimadoProductos(EstimadoProductos estimados, Usuario usuario, Map criteria);
	
	public EstimadoProductos getListaEstimados(Map params);
	public void setInterfazSATRecepcionarEstimadoProductos(EstimadoProductos estimados, Usuario usuario);

	/**
	 * @param codigoPais
	 * @return
	 */
	public List getEstadosBoletasRecojo(String codigoPais);
	
	/**
	 * @param codigoPais
	 * @return
	 */
	public List getResultadosBoletasRecojo(String codigoPais);

	/**
	 * @param codigoPais
	 * @return
	 */
	public List getMotivosNoRecojoBoletasRecojo(String codigoPais);
	
	public String getCodigoDocumento(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public String getNumLoteSTO(Map params);
	
	 
	 /**
     * 
     * @param criteria
     * @return
     */
    public List getProcesoBatchByInterfaz(Map criteria) ;

	/**
	 * Actualiza el codigo de interfaz en la tabla de procesos Batch
	 * @param queryParams
	 */
	public void updateInterfazProcesoBatch(Map queryParams);
	
	/**
	 * Retorna la lista de Tipo de Intefaz para el Modulo SMS
	 * @return
	 */
	public List getTiposInterfazSMS();
	
	/**
	 * Retorna el oid del campo solicitado
	 * @param string
	 * @param mapQueryParams
	 * @return
	 */
	public String getOidString(String string, Map mapQueryParams);
	
	/**
	 * Capturar Periodo y Fecha Proceso. Activos.
	 * @param criteria
	 * @return
	 */
	public List getPeriodoFechaProcesoActual(Map criteria);
	
	/**
	 * Obtiene la lista de los procesos de Incentivos
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosIncentivos(Map criteria);

	/**
	 * Obtiene la lista de los procesos de Cierre de Facturacion
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosCierreFacturacion(Map criteria);
	
	/**
	 * Obtiene la lista de los procesos de let
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosLet(Map criteria);
	
	/**
	 * Obtiene la descripcion de la sociedad SAP
	 * @param params
	 * @return
	 */
	public String getSociedadEquivalenciaSAP(Map params);

	/**
	 * Obtiene la lista de los procesos de Cierre de Campaa
	 * @param criteria
	 * @return
	 */
	public List getListaProcesosGenCierreCampania(Map criteria);

	/**
	 * Obtiene el contador mayor a 0 si la campaa ingresada a sido cerrada
	 * @param codigoPeriodo
	 * @return
	 */
	public Integer getContCierreCampaByCodigoPeriodo(String codigoPeriodo);
	
	public List getListaAlmacen(Map criteria);
	
	public List getTiposRecepcionXRX(String codigoInterfaz1, String codigoInterfaz2);
	
	/* INI JJ  PER-SiCC-2012-0388 */
	/**
	 * Obtiene el contador de cierre de Zona por campaa y oid de zona,si es mayor a 0 la zona cerro sino no cerro
	 * @param codigoPeriodo
	 * @param oidZona
	 * @return
	 */
	public Integer getContCierreZonaByCodigoPeriodoOidZona(String codigoPeriodo,Integer oidZona);
	/* FIN JJ  PER-SiCC-2012-0388 */
	
	/* INI SA PER-SiCC-2012-1120 */
	/**
	 * Obtiene el contador de cierre de Region por campaa y oid de region,si es
	 * mayor a 0 la region cerro sino no cerro
	 * 
	 * @param codigoPeriodo
	 * @param oidRegion
	 * @return
	 */
	public Integer getContCierreRegionByCodigoPeriodoOidRegion(String codigoPeriodo, Integer oidRegion);
	/* FIN AS PER-SiCC-2012-1120 */
	
	/**
	 * Obtiene el numero de registros asociados al archivo a cargar(Boletas de Venta).
	 * @param criteria
	 * @return
	 */
	public Integer getContRegistrosAsociadosCargaArchivosBolVent(Map criteria);
	
	/**
	 * Obtiene los archivos pendientes de procesar(Boletas de Venta).
	 * @param criteria
	 * @return
	 */
	public List getArchivosPendientesBolVent(Map criteria);
	
	/**
	 * Obtiene el numero de registros asociados al archivo a cargar(Notas de Credito).
	 * @param criteria
	 * @return
	 */
	public Integer getContRegistrosAsociadosCargaArchivosNotasCred(Map criteria);
	
	/**
	 * Obtiene los archivos pendientes de procesar(Notas de Credito).
	 * @param criteria
	 * @return
	 */
	public List getArchivosPendientesNotasCred(Map criteria);
	
	/**
	 * Obtiene la maxima fecha de la tabla temporal IMP_TMP_FELEC_CABEC
	 * @author sguerra
	 * @return
	 */
	public String getFechaFacturacion();
	
	/**
	 *
	 * @return
	 */
	public List getListaEstadosIncentivos();

        /**
	 * Devuelve Periodo y Fecha
	 * @param criteria
	 * @return
	 */
	public List getPeriodoFechaProceso(Map criteria);
	
	/**
	 * Devuelve Periodo y Fecha Activa dependiendo de la Conexion Externa
	 * @param params
	 * @return
	 */
	public Map getPeriodoFechaCampanyaActivaSF(Map params) ;

        /**
	 * Devuelve Lista de Asientos
	 * @return
	 */
	public List getListaAsientos();

	/**
	 * Actualiza parametros
	 * @param params
	 */
	public void updateInterfazCCCCargasDeudasWeb(Map params);
        /**
	 * Devuelve Lista de Pagos Lec
	 * @return
	 */
	public List getListaTipoPagoLec();

	/**
	 * Devuelve un listado de los tipos de reemplazo
	 * @return
	 */
	public List getTipoReemplazo();

	/**
	 * Obtiene los tipos de documentos de pago
	 * @return
	 */
	public List getTipoDocumentosPago();

	/**
	 * Obtiene el numero de solicitudes de cabecera de la tabla int_tmp_gener_solic_cabec
	 * @return
	 */
	public String getExisteSolicitudesCabecera();
	
	/**
	 * Obtiene la lista de periodos que envuelven a fecha de facturacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPeriodosDefaultByPMCF(Map criteria);
	
	public String getDescripcionTipoClienteByCodigoTipoClienteCodigoIdioma(Map criteria);
	public String getDescripcionSubTipoClienteByCriteria(Map criteria); 
	public String getDescripcionTipoClasificacionByCriteria(Map criteria);
	public String getClasificacionByCriteria(Map criteria);
	public List getTiposClientesByCodigoISO01(String codigo);
	
	
	/**
	 * Envia informacion SMS para el envio de la primera boleta de recojo
	 * @param params
	 */
	public void executeEnviarSMSBoletaPrimerRecojo(Map params);
	
	/**
	 * Envia informacion SMS para el envio de la segunda boleta de recojo
	 * @param params
	 */
	public void executeEnviarSMSBoletaSegundoRecojo(Map params);
	
	/**
	 * Envia informacion SMS para el envio de la segunda boleta de recojo no exitoso
	 * @param params
	 */
	public void executeEnviarSMSBoletaSegundoRecojoNoExitoso(Map params);

	/* INI NSSICC */
	/**
	 * @param params
	 */
	public void executeProcesoRECActualizaUnidadesDevueltas02(Map params);
	
	/* FIN NSSICC */
	
	/**
	 * Genera Informacion de Atenciones Inteligentes
	 * 
	 * @param params
	 */
	public void executeGenerarAtencionesInteligentes(Map params);
	
	/**
	 * Elimina Informacion de Atenciones Inteligentes
	 * 
	 * @param params
	 */
	public void executeEliminarAtencionesInteligentes(Map params);
	
}
