/**
 *
 */
package biz.belcorp.ssicc.dao.spncd;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.DescuentoCupon;
import biz.belcorp.ssicc.dao.spncd.model.DespachoProducto;
import biz.belcorp.ssicc.dao.spncd.model.EquivalenciaMatriz;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaCupon;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaPeriodo;
import biz.belcorp.ssicc.dao.spncd.model.SuscripcionCabeceraConsultora;
import biz.belcorp.ssicc.dao.spncd.model.SuscripcionDetalleConsultora;
import biz.belcorp.ssicc.dao.spncd.model.SuscripcionNivelConsultora;
import biz.belcorp.ssicc.dao.spncd.model.UnidadAdministrativaCupon;


/**
 * TODO Include class description here.
 *
 * <p>
 * <a href="MantenimientoCUPProgDsctoDAO.java.html"> <i>View Source</i> </a>
 * </p>
 *
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 *
 */
public interface MantenimientoCUPProgDsctoDAO extends DAO {
    /**
     * Obtiene un listado de todos los programas de cupones de descuentos en base a ciertos criterios
     * los cuales son enviados a travs de un Map.
     *
     * @param criteria
     *            Objeto Map cuyos atributos son usados como criterios de
     *            bsqueda.
     *
     * @return Lista de objetos Map, poblados.
     */
    public List getProgramasDsctoByCriteria(Map criteria);
    
    public List getDespachoProductosByCriteria(Map criteria);
    
    public void executeProcesoCUPDespachoProductos(Map params);    
    
    public void executeProcesoCUPCierreFacturacion(Map params);    
    
    public DespachoProducto getDespachoProductosById(Map criteria);    
    
    public DespachoProducto getOfertaDetalleById(Map criteria);   
    
    public void insertDespachoProductos(DespachoProducto despachoProducto, Usuario usuario);
    
	public void desactivarDespachoProducto(DespachoProducto  despachoProducto) ;
	
	public BigDecimal getCodVentaDespachoProductoById(Map params) ;	
	
	public BigDecimal getDetOfertaEstrategiaCompuestaFijaById(Map params) ;	

    /**
     * Obtiene la informacion de un Programa de Dscto en base a su llave primaria. La
     * excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si
     * no es encontrada.
     *
     * @param criteria contiene la PK de la tabla
     * @return Objeto de tipo ProgCupon, poblado.
     */
    public ProgramaCupon getProgramaDsctoById(Map criteria);

    /**
     * Obtiene un listado del detalle del programa seleccionado
     * los cuales son enviados a travs de un Map.
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
     * @param
     */
    public void insertProgramaDscto(ProgramaCupon cupon, Usuario usuario);
    
    public List getProgramasDescuentosbyPais(Map criteria);
    
    public List getNivelbyPais(Map criteria);    

    /**
     * Actualiza un Programa de Cupon
     * @param
     */
    public void updateProgramaDscto(ProgramaCupon cupon, Usuario usuario);

    /**
     * Elimina un Programa de Cupon
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
	 * @param criteria
	 * @return
	 */
	public String getNextPeriodoByCupProgPerio(Map criteria);

	/**
	 * @param cupon
	 * @param usuario
	 * @return
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
	public void updateEquivalenciaMatriz(EquivalenciaMatriz cupon, Usuario usuario);

	/**
	 * @param cupon
	 * @param usuario
	 */
	public void insertEquivalenciaMatriz(EquivalenciaMatriz cupon, Usuario usuario);

	/**
	 * @param cuponBean
	 */
	public void deleteEquivalenciaMatriz(EquivalenciaMatriz cuponBean);

	/**
	 * @param pais
	 * @return
	 */
	public String getNextCodigoPrograma(String pais);

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
	 * @param criteria
	 * @return
	 */
	public String getPeriodoDefaultByPrograma(Map criteria);
	
	/**
	 * Metodo que devuelve la cantidad de cupones con la misma prioridad en un nivel
	 * @param EquivalenciaMatriz
	 * @return
	 */
	public String validaPrioridad(EquivalenciaMatriz cupon);
	
	/**
	 * Metodo que actualiza la cantidad de cupones a pedir en un nivel
	 * @param criteria
	 * @return
	 */
	public void updateValorUnidadNivel(Map criteria);
	
	/**
	 * Metodo que devuelve la cantidad de cupones a pedir en un nivel
	 * @param criteria 
	 * @return
	 */
	public String getValorNivel(Map criteria);

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
	 * Metodo que registra la suscripcion de consultora
	 * @param consultora
	 * @param usuario
	 */
	public void saveSuscripcionConsultoras(
			SuscripcionCabeceraConsultora consultora, Usuario usuario);

	/**
	 * Metodo que eliminar el detalle de la suscripcion de consultora
	 * @param consultora
	 * @param usuario
	 */
	public void deleteSuscripcionConsultorasDetalle(
			SuscripcionCabeceraConsultora consultora);

	/**
	 * Metodo que registra el nivel de la suscripcion de consultora
	 * @param nivel
	 * @param usuario
	 */
	public void saveSuscripcionConsultorasNivel(
			SuscripcionNivelConsultora nivel, Usuario usuario);

	/**
	 * Metodo que registra el detalle de la suscripcion de consultora
	 * @param detalle
	 * @param usuario
	 */
	public void saveSuscripcionConsultorasDetalle(
			SuscripcionDetalleConsultora detalle, Usuario usuario);

	/**
	 * Metodo que modifica la cantidad de unidad demandada
	 * @param detalle
	 * @param usuario
	 */
	public void updateSuscripcionConsultorasDetalle(
			SuscripcionDetalleConsultora detalle, Usuario usuario);
	
	/**
	 * Metodo que elimina el detalle de la suscripcion de consultora
	 * @param detalle
	 */
	public void deleteSuscripcionDetalle(
			SuscripcionDetalleConsultora detalle);

	/**
	 * Metodo que trae el detalle de suscripcion de consultora
	 * @param detalle
	 * @return
	 */
	public SuscripcionDetalleConsultora getDetalleSuscripcionConsultora(SuscripcionDetalleConsultora detalle);
	
	/**
	 * Metodo que devuelve los cupones que no han sido homologados en la matriz de cupones
	 * @param criteria
	 * @return
	 */
	public List getCuponesNoHomologados(Map criteria);
	
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
	 * Elimina cupon LOVE por defecto
	 */
	public void deleteCuponLoveDefault(EquivalenciaMatriz cupon);
	
	/**
	 * @param cupon
	 * Elimina equivalencia cupon LOVE
	 */
	public void deleteLoveEquivalenciaMatriz(EquivalenciaMatriz cupon);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el listado de excepciones configuradas en el programa de nuevas
	 */
	public List getExcepciones(Map criteria);
	
	/**
	 * @param criteria
	 * Almacena las excepciones
	 */
	public void insertExcepciones(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina las excepciones registradas
	 */
	public void deleteExcepciones(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el listado de cupones para un periodo determinado
	 */
	public String getCuponesPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el listado de cuvs para un periodo determinado
	 */
	public String getCodigoVentaPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el listado de cuvs de la matriz
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
	 * Devuelve la cantidad de unidades de premios electivos por nivel
	 * @param criteria
	 * @return
	 */
	public String getValorNivelPremiosElectivos(Map criteria);
	
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
	 * Obtiene la lista de regiones disponibles para 
	 * el programa nuevas
	 * @param criteria
	 * @return
	 */
	public List getloadRegionesDisponibles(Map criteria);
	
	/**
	 * Obtiene la lista de zonas disponibles para 
	 * el programa nuevas
	 * @param criteria
	 * @return
	 */
	public List getZonasDisponiblesRegion(Map criteria);
	
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
	 * Inserta Unidad Administrativa
	 * @param unidadAdministrativa
	 */
	public void insertUnidadAdministrativa(UnidadAdministrativaCupon unidadAdministrativa);
	
	/**
	 * Inserta Descuento
	 * @param descuentoCupon
	 */
	public void insertDescuentos(DescuentoCupon descuentoCupon);
	
	/**
	 * Obtiene el siguiente secuencial de la UAS
	 * @return
	 */
	public String getNextOidUAS();

	/**
	 * Devuelve la Uas de un programa
	 * @param criteria
	 * @return
	 */
	public List getListUnidadesAdministrativas(Map criteria);

	/**
	 * Devuelve los Descuentos de un programa
	 * @param criteria
	 * @return
	 */
	public List getListDescuentos(Map criteria);

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
	 * Devuelve los periodos siguientes del programa
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
	
	/* INI JR PER-SiCC-2012-0362 */
	/**
	 * Realiza la entrega de Pedidos x Regalo
	 * @param criteria
	 */
	public void executeEntregaRxP(Map criteria);
	 /* FIN JR PER-SiCC-2012-0362 */
	
	/* INI SA PER-SiCC--2012-0467 */
	/**
	 * Verifica si el programa esta activado el flag de Primer Pedido
	 * 
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
	
	public void eliminarDespachoProducto(DespachoProducto  despachoProducto) ;
	
	public int validaTipoOferta(Map criteria);
	
	public int validaTipoOfertaDuplicado(Map criteria);
	
	public int getCantZonasxRegion(Map criteria);
	
	
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
	
	public Integer validaProductosKitSegundoPedido(Map criteria);
	
	/**
	 * Devuelve el Tipo de Pedidos
	 * @return Lista de objetos
	 */
	public List getTipoPedidoCUP();

	/**
	 * Obtiene la vigencia de un cupon por nivel
	 * @param codigoPais
	 * @param codigoPrograma
	 * @param codigoNivel
	 * @return
	 */
	public String getVigenciaCuponPorNivel(String codigoPais, String codigoPrograma, String codigoNivel);
	
	/**
	 * Devuelve si existe descuentos en ese nivel para un programa
	 * @param criteria
	 * @return
	 */
	public String getExisteDescuento(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getExisteDescuentoTotal(Map criteria);
	
	/**
	 * Actualiza descuentos
	 * @param criteria
	 */
	public void updateDescuentos(Map criteria);
	
	public void deleteDescuentos(Map criteria);

	public Map getCuponLoveDefault(EquivalenciaMatriz cupon);
	
	
	/**
	 * Devuelve Indicador de Cupon Reutilizable
	 * @param criteria
	 * @return
	 */
	public String getIndicadorCuponReutilizable(Map criteria);
	

}