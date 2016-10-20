/**
 *
 */
package biz.belcorp.ssicc.service.spncd;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.DespachoProducto;
import biz.belcorp.ssicc.dao.spncd.model.EquivalenciaMatriz;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaCupon;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaPeriodo;
import biz.belcorp.ssicc.dao.spncd.model.SuscripcionCabeceraConsultora;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoCUPProgDsctoService.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 * 
 */
public interface MantenimientoCUPProgDsctoService extends Service {
	/**
	 * Obtiene un listado de todos los programas de cupones de descuentos los
	 * cuales son enviados a travs de un Map.
	 * 
	 * @param criteria
	 *            Objeto Map cuyos atributos son usados como criterios de
	 *            bsqueda.
	 * 
	 * @return Lista de objetos Map, poblados.
	 */
	public List getProgramasDsctoByCriteria(Map criteria);

	public void executeProcesoCUPDespachoProductos(Map params);

	public void executeProcesoCUPCierreFacturacion(Map params);

	public List getDespachoProductosByCriteria(Map criteria);

	public DespachoProducto getOfertaDetalleById(Map criteria);

	public DespachoProducto getDespachoProductosById(Map criteria);

	public void insertDespachoProductos(DespachoProducto despachoProducto,
			Usuario usuario);

	public List getProgramasDescuentosbyPais(Map criteria);

	public List getNivelbyPais(Map criteria);

	public void desactivarDespachoProducto(DespachoProducto despachoProducto);

	public BigDecimal getCodVentaDespachoProductoById(Map params);

	public BigDecimal getDetOfertaEstrategiaCompuestaFijaById(Map params);

	/**
	 * Obtiene la informacion de un Programa de Dscto en base a su llave
	 * primaria. La excepcion ObjectRetrievalFailureException Runtime Exception
	 * es lanzada si no es encontrada.
	 * 
	 * @param criteria
	 *            contiene la PK de la tabla
	 * @return Objeto de tipo ProgCupon, poblado.
	 */
	public ProgramaCupon getProgramaDsctoById(Map criteria);

	/**
	 * Obtiene un listado del detalle del programa seleccionado los cuales son
	 * enviados a travs de un Map.
	 * 
	 * @param criteria
	 *            Objeto Map cuyos atributos son usados como criterios de
	 *            bsqueda.
	 * 
	 * @return Lista de objetos Map, poblados.
	 */
	public List getTablaEquivalByCriteria(Map criteria);

	/**
	 * Inserta un Programa de Cupon
	 * 
	 * @param
	 */
	public void insertProgramaDscto(ProgramaCupon cupon, Usuario usuario);

	/**
	 * Actualiza un Programa de Cupon
	 * 
	 * @param
	 */
	public void updateProgramaDscto(ProgramaCupon cupon, Usuario usuario);

	/**
	 * Elimina un Programa de Cupon
	 * 
	 * @param
	 */
	public void deleteProgramaDscto(ProgramaCupon cupon);

	/**
	 * @param criteria
	 */
	public void actualizarCuponNivel(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getProgramasPeriodoByCriteria(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public ProgramaPeriodo getProgramaPeriodoById(Map criteria);

	/**
	 * @param cupon
	 * @param usuario
	 */
	public void insertProgramaPeriodo(ProgramaPeriodo cupon, Usuario usuario);

	/**
	 * @param cuponBean
	 */
	public void deletePeriodoPrograma(ProgramaPeriodo cuponBean);

	/**
	 * @param cupon
	 * @param usuario
	 */
	public void updateProgramaPeriodo(ProgramaPeriodo cupon, Usuario usuario);

	/**
	 * @param criteria
	 * @return
	 */
	public List getEquivalenciaMatrizByCriteria(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public EquivalenciaMatriz getEquivalenciaMatrizById(Map criteria);

	/**
	 * @param cupon
	 * @param usuario
	 */
	public void updateEquivalenciaMatriz(EquivalenciaMatriz cupon,
			Usuario usuario);

	/**
	 * @param cupon
	 * @param usuario
	 */
	public void insertEquivalenciaMatriz(EquivalenciaMatriz cupon,
			Usuario usuario);

	/**
	 * @param cuponBean
	 */
	public void deleteEquivalenciaMatriz(EquivalenciaMatriz cuponBean);

	/**
	 * @param string
	 * @return
	 */
	public String getNextCodigoPrograma(String string);

	/**
	 * @param codigo
	 * @return
	 */
	public String getProgramaActivo(String codigo);

	/**
	 * @param criteria
	 * @return
	 */
	public String getCodigoCuponInicial(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public String getCodigoCuponFinal(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public String getCodigoPeriodoInicial(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public String getCodigoPeriodoFinal(Map criteria);

	/**
	 * Metodo que devuelve la cantidad de cupones con la misma prioridad en un nivel
	 * @param criteria
	 * @return
	 */
	public String getPeriodoDefaultByPrograma(Map criteria);
	
	/**
	 * Metodo que actualiza la cantidad de cupones a pedir en un nivel
	 * @param EquivalenciaMatriz
	 * @return
	 */
	public String validaPrioridad(EquivalenciaMatriz cupon);

	/**
	 *  Metodo que devuelve la cantidad de cupones a pedir en un nivel
	 * @param criteria
	 * @return
	 */
	public void updateValorUnidadNivel(Map criteria);

	/**
	 * Metodo que devuelve el listado de cupones utilizados en campaas anteriores dentro de un grupo de cupones no asignados
	 * @param criteria
	 * @return
	 */
	public List getCupNoAsignadosUtilizados(Map criteria);

	/**
	 * Metodo que obtiene los productos de Matriz de Facturacion
	 * @param criteria
	 * @return
	 */
	public List getProductosMatrizByCriteria(Map criteria);

	/**
	 * Metodo que obtiene la cabecera de la consultora de suscripcion
	 * @param criteria
	 * @return
	 */
	public SuscripcionCabeceraConsultora getCabecConsultorasSuscripcion(Map criteria);

	/**
	 * Metodo que obtiene los niveles de suscripcion para una consultora
	 * @param criteria
	 * @return
	 */
	public List getNivelesSuscripcion(Map criteria);

	/**
	 * Metodo que obtiene los niveles de suscripcion de edicion para una consultora
	 * @param criteria
	 * @return
	 */
	public List getNivelesSuscripcionEdit(Map criteria);

	/**
	 * Metodo que registra la consultoras de suscripcion
	 * @param criteria
	 */
	public void saveSuscripcionConsultoras(Map criteria);
	
	/**
	 * Metodo que se ejecuta al cierre de campaa.
	 * Reversa del programa los pedidos que se subieron y no se facturaron
	 * y actualiza la constancia en la tabla cup_consu_factu
	 * @param map
	 */
	public void executeCierreCampanha(Map map);

	/**
	 * @param cupon
	 * Almacena cupon LOVE por defecto
	 */
	public void guardarCuponLoveDefault(EquivalenciaMatriz cupon);
	
	/**
	 * @param cupon
	 * Almacena equivalencia de cupon LOVE
	 */
	public void guardarEquivalenciaCuponLove(EquivalenciaMatriz cupon);

	/**
	 * @param cupon
	 * Elimina equivalencia LOVE
	 */
	public void eliminarEquivalenciaLove(EquivalenciaMatriz cupon);
	
	/**
	 * @param cupon
	 * Elimina Default LOVE
	 */
	public void eliminarDefaultLove(EquivalenciaMatriz cupon);

	/**
	 * @param usuario
	 * @param cupon
	 * @param updateFlag
	 * Almacena la equivalencia de cupones
	 */
	public void almacenarEquivalencia(Usuario usuario, EquivalenciaMatriz cupon, boolean updateFlag);

	/**
	 * @param criteria
	 * @return
	 * Devuelve el listado de excepciones configuradas para el programa de nuevas
	 */
	public List getExcepciones(Map criteria);
	
	/**
	 * @param criteria
	 * Almacena las excepciones
	 */
	public void insertExcepciones(Map criteria);
	
	/**
	 * @param listaEliminar
	 * Elimina las excepciones registradas
	 */
	public void deleteExcepciones(String[] listaEliminar);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el listado de cupones en un periodo determinado
	 */
	public String getCuponesPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el listado de cuvs en un periodo determinado
	 */
	public String getCodigoVentaPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve listado de CUVs de la matriz
	 */
	public EquivalenciaMatriz getEquivalenciaMatrizByIdLove(Map criteria);
	
	/**
	 * Devuelve el listado de productos configurados para primer pedido
	 * @param criteria
	 * @return
	 */
	public List getProdutoPrimerPedidoList(Map criteria);
	
	/**
	 * Actualiza los registros en la tabla cup_prod_solic
	 * @param criteria
	 */
	public void updateProdutoPrimerPedido(Map criteria);
	
	/**
	 * Metodo que elimina logicamente los registros de la tabla cup_prod_solic
	 * @param items
	 * @param usuario
	 */
	public void deleteProdutosPrimerPedido(String[] items, String usuario);
	
	/**
	 * Metodo que devuelve el indicador digitable de un codigo de venta
	 * @param criteria
	 * @return
	 */
	public List getIndicadorDigitable(Map criteria);
	
	/**
	 * Inserta registros en la tabla cup_prod_solic
	 * @param criteria
	 */
	public void insertProductoPrimerPedido(Map criteria);
	
	/**
	 * Verifica si existe translape de campanas
	 * @param criteria
	 * @return
	 */
	public String verificaCruce(Map criteria);
	
	/**
	 * Obtiene el periodo de inicio de un cupon
	 * @param criteria
	 * @return
	 */
	public String getPeriodoInicioByCupon(Map criteria);
		
	/**
	 * Obtiene el periodo final de un cupon
	 * @param criteria
	 * @return
	 */
	public String getPeriodoFinByCupon(Map criteria);
	
	/**
	 * Devuelve la descripcion de la region
	 * @param criteria
	 * @return
	 */
	public String devuelveDescripcionRegion(Map criteria);
	
	/**
	 * Devuelve la descripcion de la region
	 * @param criteria
	 * @return
	 */
	public String devuelveDescripcionZona(Map criteria);
	
	/**
	 * Verifiaca si existen consultoras asociadas al programa
	 * @param criteria
	 * @return
	 */
	public String verificaConsultoraPrograma(Map criteria);
	
	/**
	 * Actualiza un Programa de Cupon
	 * @param criteria
	 */
	public void updateProgramaDscto2(Map criteria);

	/**
	 * Actualiza las uniddades administrativas del programa
	 * @param criteria
	 */
	public void updateUnidadAdministrativa(Map criteria);
	
	/**
	 * Inserta las uniddades administrativas del programa
	 * @param cupon
	 */
	public void insertUnidadAdministrativa(ProgramaCupon cupon);
	
	/**
	 * Devuelve los periodos del programa
	 * @param criteria
	 * @return
	 */
	public String getPeriodosPrograma(Map criteria);
	
	/**
	 * Devuelve el periodo siguiente o anterior segun parametro.
	 * @param criteria
	 * @return
	 */
	public String getPeriodoSiguiente(Map criteria);
	
	/**
	 * VAlida si existe matriz de facturacion para el periodo seleccionado
	 * @param criteria
	 * @return
	 */
	public String validarMatrizFacturacion(Map criteria);
	
	/**
	 * Obtiene los detalles del CUV
	 * @param criteria
	 * @return
	 */
	public List getDetalleProductoByIdOferta(Map criteria);
	
	/**
	 * Inserta un Regalo x Pedido
	 * @param criteria
	 */
	public void insertaRegaloxPedido(Map criteria);

	/**
	 * Obtiene el parametro de nro de niveles permitidos
	 * @param criteria
	 * @return
	 */
	public String getNumeroNiveles(Map criteria);

	/**
	 * Obtiene la lista con los noveles permitidos
	 * @param criteria
	 * @return
	 */
	public List getNivelesPermitidos(Map criteria);
	
	/* INI SA PER-SiCC--2012-0467 */
	/**
	 * Verifica si el programa esta activado el flag de Primer Pedido
	 * @param criteria
	 * @return
	 */
	public boolean verificaProgramaPrimerPedido(Map criteria);
	
	/**
	 * Verifica si hay cupones con Indicador Kit Activo
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean existenCuponesConIndicadorKit(Map criteria);
	
	/**
	 * Verifica si hay despachos con Indicador Kit Activo
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean existenDespachosConIndicadorKit(Map criteria);
	
	/**
	 * Verifica si hay cruce por UAS
	 * @param criteria
	 * @return
	 */
	public void verificaCruceProgramaUA(Map criteria);
	/* FIN SA PER-SiCC--2012-0467 */

	public String getPeriodo(Map criteria);
	
	public void insertPeriodo(Map criteria);
	
	public String getParametroPrograma(String parametro);
	
	public int validarPeriodoCampahnaFin(Map criteria);

	public String getRegaloPorPedido(Map criteria);
	
	public void eliminarDespachoProducto(DespachoProducto despachoProducto);
	
	public int validaTipoOferta(Map criteria);
	
	public int validaTipoOfertaDuplicado(Map criteria);
	
	public int getCantZonasxRegion(Map criteria);
	
	public List getZonasDisponiblesRegion(Map criteria);
	
	/**
	 * Consulta SOA de Pruebas correspondiente a Programas Nuevas
	 * @param criteria
	 * @return
	 */
	public List getConsultaSOAProgramaNuevas(Map criteria);
	
	/**
	 * Consulta SOA de Pruebas correspondiente a Incentivos
	 * @param criteria
	 * @return
	 */
	public List getConsultaSOAIncentivos(Map criteria);
	
	/**
	 * Devuelve el oid del Concurso
	 * @param criteria
	 * @return
	 */
	public Long getDevuelveOidConcurso(Map criteria);
	
	public boolean existenDespachoConIndicadorKitSegundoPedido(Map criteria);
	
	/**
	 * Devuelve el Tipo de Pedidos
	 * 
	 * @return Lista de objetos
	 */
	public List getTipoPedidoCUP();
	
	/**
	 * Devuelve si existe descuentos en ese nivel para un programa
	 * @param criteria
	 * @return
	 */
	public String getExisteDescuento(Map criteria);
	
	/**
	 * Inserta descuento
	 * @param descuento
	 */
	public void insertDescuentos(ProgramaCupon cupon);
	
	/**
	 * Actualiza Descuento
	 * @param criteria
	 */
	public void updateDescuentos(Map criteria);
	
	
	/**
	 * Devuelve Indicador de Cupon Reutilizable
	 * @param criteria
	 * @return
	 */
	public String getIndicadorCuponReutilizable(Map criteria);

}