package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoAmbitoGeografico;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLote;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLoteDescuento;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoBonificacionPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoClasificacionParticipante;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoDespachoPremios;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoEstatusVenta;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoLotePremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoMontoVentas;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoNivelPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoObtencionPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosConsultoras;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosGenerales;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPeriodoDespacho;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosBonificados;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExcluidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExigidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosValidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPuntajeExigido;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRecomendadaPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRequisitoPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoVersion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.NuevaConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.RangoConstanciaProgramaPuntos;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCConfiguracionConcursoDAO extends DAO {	

	/**
	 * retorna la lista de Clasificaciones de Concurso
	 * 
	 * @return
	 */
	public List getClasificacionesConcurso();

	/**
	 *  retorna la lista de Concursos de acuerdo a determinados criterios
	 *  
	 * @param params
	 * @return
	 */
	public List getConcursosByCriteria(Map criteria);
	
	/**
	 * Elimina registro de la entidad Concurso
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteConcurso(Map params);

	/**
	 * Elimina registro de la entidad Version Concurso
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteConcursoVersion(Map params);
	
	/**
	 * retorna la lista de programa de Concurso
	 * 
	 * @return
	 */
	public List getTiposPrograma();

	/**
	 * retorna la lista de Base de Calculo
	 * 
	 * @return
	 */
	public List getBaseCalculo();

	/**
	 * retorna la lista de a quienes esta dirigida el Concurso
	 * 
	 * @return
	 */
	public List getDirigidos();

	/**
	 * retorna la lista de Tipo de Venta
	 * 
	 * @return
	 */
	public List getTipoVenta();

	/**
	 * retorna la lista de Tipo de Exigencia
	 * 
	 * @return
	 */
	public List getTipoExigencia();

	/**
	 * retorna la lista de Concursos Recomendadas
	 * 
	 * @return
	 */
	public List getConcursosRecomendadas();

	/**
	 * retorna la lista de Estatus de Cliente
	 * 
	 * @return
	 */
	public List getEstatusCliente();

	/**
	 * retorna la lista de Clasificaciones de Participantes
	 * 
	 * @return
	 */
	public List getClasificacionesParticipante();

	/**
	 * retorna la lista de Tipos de Premiacion
	 * 
	 * @return
	 */
	public List getTiposPremiacion();
	
	/**
	 * retorna la lista de Tipos de Eleccion
	 * 
	 * @return
	 */
	public List getTiposEleccion();

	/**
	 * retorna la lista de Centros de Servicio
	 * 
	 * @return
	 */
	public List getCentrosServicio();
	
	/**
	 * retorna la lista de Tipos de Productos
	 * 
	 * @return
	 */
	public List getTiposProducto();

	/**
	 * retorna la lista de Tipos de Oferta
	 * 
	 * @return
	 */
	public List getTiposOferta();
	
	/**
	 * retorna la lista de Tipos de Agrupacion
	 * 
	 * @return
	 */
	public List getTiposAgrupacion();

	/**
	 * retorna la lista de Unidades de Negocio
	 * 
	 * @return
	 */
	public List getUnidadesNegocio();

	/**
	 * retorna la lista de SuperGenericos filtrado por criterio de busqueda
	 * 
	 * @return
	 */
	public List getSuperGenericosByCriteria(Map criteria);

	/**
	 * retorna la lista de Genericos filtrado por criterio de busqueda
	 * 
	 * @return
	 */
	public List getGenericosByCriteria(Map criteria);
	
	/**
	 * retorna la lista de Ciclos de Vida
	 * 
	 * @return
	 */
	public List getCiclosVida();	
	
	/**
	 * retorna la lista de Negocios
	 * 
	 * @return
	 */
	public List getNegocios();		

	/**
	 * retorna la lista de Marca de Productos
	 * 
	 * @return
	 */
	public List getMarcaProductos();		

	/**
	 * Obtiene la secuencia para insertar en la entidad Concurso Parametros Generales
	 * 
	 * @return
	 */
	public Long getSecuenciaConcursoParametrosGenerales();
	
	/**
	 * Obtiene la secuencia para insertar en la entidad Concurso Parametros Premiacion
	 * 
	 * @return
	 */
	public Long getSecuenciaConcursoParametrosPremiacion();	

	/**
	 * Obtiene la secuencia para insertar en la entidad Concurso Niveles Premiacion
	 * 
	 * @return
	 */
	public Long getSecuenciaConcursoNivelPremiacion();	

	/**
	 * Obtiene la secuencia para insertar en la entidad Concurso Premio Articulo
	 * 
	 * @return
	 */
	public Long getSecuenciaConcursoPremioArticulo();	

	/**
	 * Obtiene la secuencia para insertar en la entidad Concurso LotePremio Articulo
	 * 
	 * @return
	 */
	public Long getSecuenciaConcursoLotePremioArticulo();
	
	/**
	 * Obtiene la secuencia para insertar en la entidad Concurso Productos
	 * 
	 * @return
	 */
	public Long getSecuenciaConcursoProductos();
	
	/**
	 * Inserta un registro en la entidad Concurso Parametros Generales
	 * 
	 * @param concursoParametrosGenerales
	 * @param usuario
	 */
	public void insertConcursoParametrosGenerales(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Version
	 * 
	 * @param concursoVersion
	 * @param usuario
	 */
	public void insertConcursoVersion(ConcursoVersion concursoVersion, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Obtencion Puntos
	 * 
	 * @param concursoObtencionPuntos
	 * @param usuario
	 */
	public void insertConcursoObtencionPuntos(ConcursoObtencionPuntos concursoObtencionPuntos, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Despacho Premios
	 * 
	 * @param concursoDespachoPremios
	 */
	public void insertConcursoDespachoPremios(ConcursoDespachoPremios concursoDespachoPremios, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Ambito Geografico
	 * 
	 * @param concursoAmbitoGeografico
	 * @param usuario
	 */
	public void insertConcursoAmbitoGeografico(ConcursoAmbitoGeografico concursoAmbitoGeografico, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Parametros Consultoras
	 * 
	 * @param concursoParametrosConsultoras
	 * @param usuario
	 */
	public void insertConcursoParametrosConsultoras(ConcursoParametrosConsultoras concursoParametrosConsultoras, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Monto Ventas
	 * 
	 * @param concursoMontoVentas
	 * @param usuario
	 */
	public void insertConcursoMontoVentas(ConcursoMontoVentas concursoMontoVentas, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Estatus Venta
	 * 
	 * @param concursoEstatusVenta
	 * @param usuario
	 */
	public void insertConcursoEstatusVenta(ConcursoEstatusVenta concursoEstatusVenta, Usuario usuario);
	
	/**
	 * Inserta un registro en la entidad Concurso Clasificacion Participante
	 * 
	 * @param concursoClasificacionParticipante
	 * @param usuario
	 */
	public void insertConcursoClasificacionParticipante(ConcursoClasificacionParticipante concursoClasificacionParticipante, Usuario usuario);
	
	/**
	 * Inserta un registro en la entidad Concurso Requisito Premiacion
	 * 
	 * @param concursoRequisitoPremiacion
	 * @param usuario
	 */
	public void insertConcursoRequisitoPremiacion(ConcursoRequisitoPremiacion concursoRequisitoPremiacion, Usuario usuario);	
	
	/**
	 * Inserta un registro en la entidad Concurso Parametros Premiacion
	 * 
	 * @param concursoParametrosPremiacion
	 * @param usuario
	 */
	public void insertConcursoParametrosPremiacion(ConcursoParametrosPremiacion concursoParametrosPremiacion, Usuario usuario);	

	/**
	 * Inserta un registro en la entidad Concurso Nivel Premiacion
	 * 
	 * @param concursoNivelPremiacion
	 * @param usuario
	 */
	public void insertConcursoNivelPremiacion(ConcursoNivelPremiacion concursoNivelPremiacion, Usuario usuario);	

	/**
	 * Inserta un registro en la entidad Concurso Premio Articulo
	 * 
	 * @param concursoPremioArticulo
	 * @param usuario
	 */
	public void insertConcursoPremioArticulo(ConcursoPremioArticulo concursoPremioArticulo, Usuario usuario);	
	
	/**
	 * Inserta un registro en la entidad Concurso LotePremio Articulo
	 * 
	 * @param concursoLotePremioArticulo
	 * @param usuario
	 */
	public void insertConcursoLotePremioArticulo(ConcursoLotePremioArticulo concursoLotePremioArticulo, Usuario usuario);
	
	/**
	 * Inserta un registro en la entidad Concurso Articulo Lote
	 * 
	 * @param concursoArticuloLote
	 * @param usuario
	 */
	public void insertConcursoArticuloLote(ConcursoArticuloLote concursoArticuloLote, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Productos
	 * 
	 * @param concursoProductos
	 * @param usuario
	 */
	public void insertConcursoProductos(ConcursoProductos concursoProductos, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Productos Validos
	 * 
	 * @param concursoProductosValidos
	 * @param usuario
	 */
	public void insertConcursoProductosValidos(ConcursoProductosValidos concursoProductosValidos, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Productos Bonificados
	 * 
	 * @param concursoProductosBonificados
	 * @param usuario
	 */
	public void insertConcursoProductosBonificados(ConcursoProductosBonificados concursoProductosBonificados, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Productos Excluidos
	 * 
	 * @param concursoProductosExcluidos
	 * @param usuario
	 */
	public void insertConcursoProductosExcluidos(ConcursoProductosExcluidos concursoProductosExcluidos, Usuario usuario);

	/**
	 * Inserta un registro en la entidad Concurso Productos Exigidos
	 * 
	 * @param concursoProductosExigidos
	 * @param usuario
	 */
	public void insertConcursoProductosExigidos(ConcursoProductosExigidos concursoProductosExigidos, Usuario usuario);

	/**
	 * Obtiene un registro en la entidad Concurso Parametros Generales
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoParametrosGenerales getConcursoParametrosGenerales(String oidConcurso);
	
	/**
	 * Obtiene un registro en la entidad Concurso Version
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoVersion getConcursoVersion(String oidConcurso);
	
	/**
	 * Obtiene un registro en la entidad Concurso Obtencion Puntos
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoObtencionPuntos getConcursoObtencionPuntos(String oidConcurso);

	/**
	 * Obtiene un registro en la entidad Concurso Despacho Premios
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoDespachoPremios getConcursoDespachoPremios(String oidConcurso);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Ambito Geografico
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public List getListConcursoAmbitoGeografico(String oidConcurso);

	/**
	 * Obtiene un registro en la entidad Concurso Parametros Consultoras
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoParametrosConsultoras getConcursoParametrosConsultoras(String oidConcurso);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Monto Ventas
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public List getListConcursoMontoVentas(String oidConcurso);
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso Estatus Venta
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public List getListConcursoEstatusVenta(String oidConcurso);
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso Clasificacion Participantes
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public List getListConcursoClasificacionParticipante(String oidConcurso);
	
	/**
	 * Obtiene un registro en la entidad Concurso Requisitos Premiacion
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoRequisitoPremiacion getConcursoRequisitoPremiacion(String oidConcurso);
	
	/**
	 * Obtiene un registro en la entidad Concurso Parametros Premiacion
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoParametrosPremiacion getConcursoParametrosPremiacion(String oidConcurso);
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso Niveles Premiacion
	 * 
	 * @param oidPremiacion
	 * @return
	 */
	public List getListConcursoNivelPremiacion(String oidPremiacion);
	
	/**
	 * Obtiene un registro en la entidad Concurso Premio Articulo
	 * 
	 * @param oidNivelPremiacion
	 * @return
	 */
	public ConcursoPremioArticulo getConcursoPremioArticulo(String oidNivelPremiacion);
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso LotePremio Articulo
	 * 
	 * @param oidLotePremioArticulo
	 * @return
	 */
	public List getListConcursoLotePremioArticulo(String oidLotePremioArticulo);
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso Articulo Lote
	 * 
	 * @param oidPremioArticulo
	 * @return
	 */
	public List getListConcursoArticuloLote(String oidPremioArticulo);
	
	/**
	 * Obtiene un registro en la entidad Concurso Productos
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public ConcursoProductos getConcursoProductos(String oidConcurso);	
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso Productos Validos
	 * 
	 * @param oidProductos
	 * @return
	 */
	public List getListConcursoProductosValidos(String oidProductos);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Productos Bonificados
	 * 
	 * @param oidProductos
	 * @return
	 */
	public List getListConcursoProductosBonificados(String oidProductos);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Productos Excluidos
	 * 
	 * @param oidProductos
	 * @return
	 */
	public List getListConcursoProductosExcluidos(String oidProductos);
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso Productos Exigidos
	 * 
	 * @param oidProductos
	 * @return
	 */
	public List getListConcursoProductosExigidos(String oidProductos);	
	
	/**
	 * Actualiza un registro en la entidad Concurso Parametros Generales
	 * 
	 * @param concursoParametrosGenerales
	 * @param usuario
	 */
	public void updateConcursoParametrosGenerales(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Version
	 * 
	 * @param concursoVersion
	 * @param usuario
	 */
	public void updateConcursoVersion(ConcursoVersion concursoVersion, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Obtencion Puntos
	 * 
	 * @param concursoObtencionPuntos
	 * @param usuario
	 */
	public void updateConcursoObtencionPuntos(ConcursoObtencionPuntos concursoObtencionPuntos, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Despacho Premios
	 * 
	 * @param concursoDespachoPremios
	 * @param usuario
	 */
	public void updateConcursoDespachoPremios(ConcursoDespachoPremios concursoDespachoPremios, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Ambito Geografico
	 * 
	 * @param concursoAmbitoGeografico
	 * @param usuario
	 */
	public void updateConcursoAmbitoGeografico(ConcursoAmbitoGeografico concursoAmbitoGeografico, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Parametros Consultoras
	 * 
	 * @param concursoParametrosConsultoras
	 * @param usuario
	 */
	public void updateConcursoParametrosConsultoras(ConcursoParametrosConsultoras concursoParametrosConsultoras, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Monto Ventas
	 * 
	 * @param concursoMontoVentas
	 * @param usuario
	 */
	public void updateConcursoMontoVentas(ConcursoMontoVentas concursoMontoVentas, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Estatus Venta
	 * 
	 * @param concursoEstatusVenta
	 * @param usuario
	 */
	public void updateConcursoEstatusVenta(ConcursoEstatusVenta concursoEstatusVenta, Usuario usuario);
	
	/**
	 * Actualiza un registro en la entidad Concurso Clasificacion Participante
	 * 
	 * @param concursoClasificacionParticipante
	 * @param usuario
	 */
	public void updateConcursoClasificacionParticipante(ConcursoClasificacionParticipante concursoClasificacionParticipante, Usuario usuario);
	
	/**
	 * Actualiza un registro en la entidad Concurso Requisito Premiacion
	 * 
	 * @param concursoRequisitoPremiacion
	 * @param usuario
	 */
	public void updateConcursoRequisitoPremiacion(ConcursoRequisitoPremiacion concursoRequisitoPremiacion, Usuario usuario);	
	
	/**
	 * Actualiza un registro en la entidad Concurso Parametros Premiacion
	 * 
	 * @param concursoParametrosPremiacion
	 */
	public void updateConcursoParametrosPremiacion(ConcursoParametrosPremiacion concursoParametrosPremiacion, Usuario usuario);	

	/**
	 * Actualiza un registro en la entidad Concurso Nivel Premiacion
	 * 
	 * @param concursoNivelPremiacion
	 * @param usuario
	 */
	public void updateConcursoNivelPremiacion(ConcursoNivelPremiacion concursoNivelPremiacion, Usuario usuario);	

	/**
	 * Actualiza un registro en la entidad Concurso Premio Articulo
	 * 
	 * @param concursoPremioArticulo
	 * @param usuario
	 */
	public void updateConcursoPremioArticulo(ConcursoPremioArticulo concursoPremioArticulo, Usuario usuario);	
	
	/**
	 * Actualiza un registro en la entidad Concurso LotePremio Articulo
	 * 
	 * @param concursoLotePremioArticulo
	 * @param usuario
	 */
	public void updateConcursoLotePremioArticulo(ConcursoLotePremioArticulo concursoLotePremioArticulo, Usuario usuario);
	
	/**
	 * Actualiza un registro en la entidad Concurso Articulo Lote
	 * 
	 * @param concursoArticuloLote
	 * @param usuario
	 */
	public void updateConcursoArticuloLote(ConcursoArticuloLote concursoArticuloLote, Usuario usuario);
	
	/**
	 * Actualiza un registro en la entidad Concurso Productos 
	 * 
	 * @param concursoProductos
	 * @param usuario
	 */
	public void updateConcursoProductos(ConcursoProductos concursoProductos, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Productos Validos
	 * 
	 * @param concursoProductosValidos
	 * @param usuario
	 */
	public void updateConcursoProductosValidos(ConcursoProductosValidos concursoProductosValidos, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Productos Bonificados
	 * 
	 * @param concursoProductosBonificados
	 * @param usuario
	 */
	public void updateConcursoProductosBonificados(ConcursoProductosBonificados concursoProductosBonificados, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Productos Excluidos
	 * 
	 * @param concursoProductosExcluidos
	 * @param usuario
	 */
	public void updateConcursoProductosExcluidos(ConcursoProductosExcluidos concursoProductosExcluidos, Usuario usuario);

	/**
	 * Actualiza un registro en la entidad Concurso Productos Exigidos
	 * 
	 * @param concursoProductosExigidos
	 * @param usuario
	 */
	public void updateConcursoProductosExigidos(ConcursoProductosExigidos concursoProductosExigidos, Usuario usuario);

	/**
	 * Eliminamos lista de Ambitos Geografico de un Concurso
	 * 
	 * @param oidConcurso
	 */
	public void deleteConcursoAmbitoGeografico(String oidConcurso);

	/**
	 * Eliminamos lista de Estatus Venta de un Concurso
	 * 
	 * @param oidConcurso
	 */
	public void deleteConcursoEstatusVenta(String oidConcurso);

	/**
	 * Eliminamos lista de Clasificacion Participantes de un Concurso
	 * 
	 * @param oidConcurso
	 */
	public void deleteConcursoClasificacionParticipante(String oidConcurso);

	/**
	 * Eliminamos lista de Productos Validos de un Concurso
	 * 
	 * @param oidProductos
	 */
	public void deleteConcursoProductosValidos(String oidProductos);	

	/**
	 * Eliminamos lista de Productos Bonificados de un Concurso
	 * 
	 * @param oidProductos
	 */
	public void deleteConcursoProductosBonificados(String oidProductos);	
	
	/**
	 * Eliminamos lista de Productos Excluidos de un Concurso
	 * 
	 * @param oidProductos
	 */
	public void deleteConcursoProductosExcluidos(String oidProductos);		

	/**
	 * Eliminamos lista de Productos Exigidos de un Concurso
	 * 
	 * @param oidProductos
	 */
	public void deleteConcursoProductosExigidos(String oidProductos);		
	
	/**
	 * Obtiene las regiones que pertenecen a una subgerencia de Ventas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getRegionesByOidSubGerencia(Map criteria);
	
	/**
	 * Obtiene las zonas que pertenece a una region
	 * 
	 * @param criteria
	 * @return
	 */
	public List getZonasByMultipleOidRegiones(Map criteria);

	/**
	 * Recupera el ultimo Numero de Concurso
	 * 
	 * @return
	 */
	public String getUltimoNumeroConcurso();

	/**
	 * Ejecuta el proceso de replicacion de Configuracion de concurso
	 * @param params
	 */
	public void executeReplicarConfiguracionConcurso(Map params);

	/**
	 * Ejecuta el proceso de eliminar niveles de premiacion con sus lotes y premios de articulo
	 * @param params
	 */
	public void executeEliminarNivelesPremiacion(Map params);

	/**
	 * Recupera el ultimo contador de articulos de premio
	 * 
	 * @return
	 */
	public String getUltimoContadorArticulo();

	/**
	 * Obtiene datos relacionados a un tipo de Concurso como: base de Calculo, tipo de programa,
	 * concurso ivr, plantilla, etc.
	 * 
	 * @param oidTipoConcurso
	 * @return
	 */
	public Map getDatosTipoConcurso(String oidTipoConcurso);

	/**
	 * Valida si el concurso ha realizado despacho de Premio
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public boolean validaDespachoPremios(String oidConcurso);
	
	/**
	 * Valida si el concurso ha generado Puntaje
	 * 
	 * @param oidConcurso
	 * @return
	 */
	public boolean validaPuntajeConcurso(String oidConcurso);

	/**
	 * Recupera el codigo SAP asignado para Concursos de Recomendacion
	 * 
	 * @return
	 */
	public String getCodigoSAPRecomendacion();

	/**
	 * retorna la lista de estados de los Concursos
	 * 
	 * @return
	 */
	public List getEstados();
	
	/**
	 * retorna la lista de vigencias de los Concursos
	 * 
	 * @return
	 */
	public List getVigencias();

	/**
	 * retorna la lista de Indicadores de Tipo de Recomendada
	 * 
	 * @return
	 */
	public List getIndicadoresTipoRecomendada();

	/**
	 * Inserta un registro en la entidad Concurso Recomendada Periodo
	 * 
	 * @param concursoRecomendadaPeriodo
	 * @param usuario
	 */
	public void insertConcursoRecomendadaPeriodo(ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo, Usuario usuario);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Recomendada Periodo
	 * 
	 * @param numeroConcurso
	 * @return
	 */
	public List getListConcursoRecomendadaPeriodo(String numeroConcurso);
	
	/**
	 * Actualiza un registro en la entidad Concurso Recomendada Periodo
	 * 
	 * @param concursoRecomendadaPeriodo
	 * @param usuario
	 */
	public void updateConcursoRecomendadaPeriodo(ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo, Usuario usuario);

	/**
	 * Eliminamos lista de Recomendada Periodo de un Concurso
	 * 
	 * @param numeroConcurso
	 */
	public void deleteConcursoRecomendadaPeriodo(String numeroConcurso);

	/**
	 * Inserta un registro en la entidad Concurso Bonificacion Periodo
	 * 
	 * @param concursoBonificacionPeriodo
	 * @param usuario
	 */
	public void insertConcursoBonificacionPeriodo(ConcursoBonificacionPeriodo concursoBonificacionPeriodo, Usuario usuario);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Bonificacion Periodo
	 * 
	 * @param numeroConcurso
	 * @return
	 */
	public List getListConcursoBonificacionPeriodo(String numeroConcurso);
	
	/**
	 * Actualiza un registro en la entidad Concurso Bonificacion Periodo
	 * 
	 * @param concursoBonificacionPeriodo
	 * @param usuario
	 */
	public void updateConcursoBonificacionPeriodo(ConcursoBonificacionPeriodo concursoBonificacionPeriodo, Usuario usuario);

	/**
	 * Eliminamos lista de Bonificacion Periodo de un Concurso
	 * 
	 * @param numeroConcurso
	 */
	public void deleteConcursoBonificacionPeriodo(String numeroConcurso);

	/**
	 * Inserta un registro en la entidad Concurso Periodo Despacho
	 * 
	 * @param concursoPeriodoDespacho
	 * @param usuario
	 */
	public void insertConcursoPeriodoDespacho(ConcursoPeriodoDespacho concursoPeriodoDespacho, Usuario usuario);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Periodo Despacho
	 * 
	 * @param numeroConcurso
	 * @return
	 */
	public List getListConcursoPeriodoDespacho(String numeroConcurso);
	
	/**
	 * Actualiza un registro en la entidad Concurso Periodo Despacho
	 * 
	 * @param concursoPeriodoDespacho
	 * @param usuario
	 */
	public void updateConcursoPeriodoDespacho(ConcursoPeriodoDespacho concursoPeriodoDespacho, Usuario usuario);

	/**
	 * Eliminamos lista de Periodo Despacho de un Concurso
	 * 
	 * @param numeroConcurso
	 */
	public void deleteConcursoPeriodoDespacho(String numeroConcurso);
	
	/**
	 * Inserta un registro en la entidad Parmetros Nivel Premio Puntaje Exigido
	 * 
	 * @param concursoPeriodoDespacho
	 * @param usuario
	 */
	public void insertConcursoPuntajeExigido(ConcursoPuntajeExigido concursoPuntajeExigido, Usuario usuario);
	
	/**
	 * Elimina registro en la entidad Parmetros Nivel Premio Puntaje Exigido
	 * @param concursoPuntajeExigido
	 * @param usuario
	 */
	public void deleteConcursoPuntajeExigido(ConcursoPuntajeExigido concursoPuntajeExigido, Usuario usuario) ;
	
	/**
	 * Obtiene Lista de entidad Parmetros Nivel Premio Puntaje Exigido
	 * @param oidConcurso
	 * @return
	 */
	public List getListConcursoPuntajeExigido(String oidConcurso);
	
	
	/**
	 * Elimina data de tabla inc_ultim_actua_concu
	 * @param params
	 */
	public void deleteUltimaActualizacionConcurso(Map params);
	
	/**
	 * Inserta data de tabla inc_ultim_actua_concu
	 * @param params
	 */
	public void insertUltimaActualizacionConcurso(Map params);

	/**
	 * Elimina registros de la entidad Concurso LotePremio Articulo
	 * 
	 * @param concursoLotePremioArticulo
	 * @param usuario
	 */
	public void deleteLotesPremioArticulo(Map params);
	
	/**
	 * Elimina registros de la entidad Concurso Articulo Lote que correspondan a Lotes Eliminados
	 * 
	 * @param concursoLotePremioArticulo
	 * @param usuario
	 */
	public void deleteArticulosLoteEliminado(Map params);
	
	/**
	 * Elimina registros de la entidad Concurso Articulo Lote
	 * 
	 * @param concursoArticuloLote
	 * @param usuario
	 */
	public void deleteArticulosLote(Map params);

	/**
	 * Obtiene el Detalle de la Oferta relacionado al CUV
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getDetalleOfertaCUV(Map criteria);

	/**
	 *  retorna la lista de faltantes pendientes de un determinado concurso
	 *  
	 * @param criteria
	 * @return
	 */
	public List getFaltantesPendientesConcurso(Map criteria);
	
	/**
	 *  retorna la lista de premios de un determinado concurso
	 *  
	 * @param criteria
	 * @return
	 */
	public List getPremiosConcurso(Map criteria);
	
	/**
	 *  retorna la lista de faltantes de un determinado concurso
	 *  
	 * @param criteria
	 * @return
	 */
	public List getFaltantesConcurso(Map criteria);
	
	/**
	 * Obtiene la secuencia para insertar en la entidad Reemplazo Premio
	 * 
	 * @return
	 */
	public Long getSecuenciaReemplazoPremio();
	
	/**
	 * Inserta un registro en la entidad Reemplazo Premio
	 * 
	 * @param params
	 */
	public void insertReemplazoPremio(Map params);
	
	/**
	 * Actualiza un registro en la entidad Reemplazo Premio
	 * 
	 * @param params
	 */
	public void updateReemplazoPremio(Map params);
	
	/**
	 * Elimina un registro de la entidad Reemplazo Premio
	 * 
	 * @param oidArticuloLote
	 */
	public void deleteReemplazoPremio(String oidArticuloLote);
	
	/**
	 * Inserta un registro en la entidad Reemplazo Premio Ambito Geografico
	 * 
	 * @param params
	 */
	public void insertReemplazoPremioAmbito(Map params);
	
	/**
	 * Elimina registros de la entidad Reemplazo Premio Ambito Geografico
	 * 
	 * @param oidArticuloLote
	 */
	public void deleteReemplazoPremioAmbito(String oidArticuloLote);

	/**
	 * Obtiene la lista de Criterios de Reemplazos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListCriterioDeReemplazo(Map criteria);

	/**
	 * Valida si ha realizado la ejecucion de un proceso batch de SICC
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validaEjecucionProcesoBatch(Map criteria);

	/**
	 * Obtiene una lista de registros en la entidad Concurso Reemplazo Ambito
	 * 
	 * @param oidReemplazo
	 * @return
	 */
	public List getListReemplazoPremioAmbito(String oidReemplazo);

	/**
	 * Valida si ha realizado la atencion de un premio 
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validaPremioAtendido(Map criteria);

	/**
	 * retorna la lista de Concursos Vigentes para Reporte Puntos
	 * 
	 * @return
	 */
	public List getListConcursosVigentesPuntos();

	/**
	 * Elimina un registro de la entidad Reemplazo Premio Compuesto
	 * 
	 * @param oidArticuloLote
	 */
	public void deleteReemplazoPremioCompuesto(String oidArticuloLote);

	/**
	 * Actualiza un registro en la entidad Reemplazo Premio Compuesto
	 * 
	 * @param params
	 */
	public void updateReemplazoPremioCompuesto(Map params);
	
	/**
	 * Devuelve la descripcion del programa en opcion NUEVO
	 * @param oidTipoConcurso
	 * @return
	 */
	public String getDescripcionProgramaAdicionar(String oidTipoConcurso);
	
	/**
	 * Devuelve la descripcion del programa en opcion EDITAR
	 * @param criteria
	 * @return
	 */
	public String getDescripcionProgramaEditar(String oidConcurso);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getProgramasConstanciaProgramaPuntosByCriteria(Map params);

	/**
	 * 
	 * @param cpp
	 * @return
	 */
	public ConstanciaProgramaPuntos getConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp);
	
	/**
	 * 
	 * @param cpp
	 * @param usuario
	 */
	public void insertConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario);
	
	/**
	 * 
	 * @param cpp
	 * @param usuario
	 */
	public void updateConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario);
	
	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void insertRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getRangoConstanciaProgramaPuntosList(Map params);

	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void deleteRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario);	
	
	/**
	 * Devuelve cero si no existen premios descuento.
	 * @return
	 */
	public Integer getExistePremiosDescuento(Map criteria);
	
	/**
	 * Devuelve cero si no existen premios articulos
	 * @return
	 */
	public Integer getExistePremiosArticulo(Map criteria);
	
	/**
	 * Obtiene una lista de registros en la entidad Concurso Articulo Lote Descuento
	 * 
	 * @param oidPremioArticulo
	 * @return
	 */
	public List getListConcursoArticuloLoteDescuento(String oidPremioArticulo);
	
	public String getcodigoProductoDescuento();
	
	public void deleteArticulosLoteDescuento(Map params);
	
	/**
	 * Inserta un registro en la entidad Concurso Articulo Lote Descuento
	 * 
	 * @param concursoArticuloLote
	 * @param usuario
	 */
	public void insertConcursoArticuloLoteDescuento(ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento, Usuario usuario);
	
	
	/**
	 * Actualiza un registro en la entidad Concurso Articulo Lote Descuento
	 * 
	 * @param concursoArticuloLote
	 * @param usuario
	 */
	public void updateConcursoArticuloLoteDescuento(ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento, Usuario usuario);
	
	public Integer getNivelSelectivo(Map criteria);
	
	/**
	 * Devuelve Lista de la tabla INC_PARTI_CONCU_CABEC
	 * @param programa
	 * @return
	 */
	public List getClasificacionParaINCByCriteria(Map params);
	
	/**
	 * Devuelve registro ClasificacionParaINC
	 * @param params
	 * @return
	 */
	public ClasificacionParaINC getClasificacionParaINC(Map params);
	
	
	/**
	 * Inserta en tabla INC_PARTI_CONCU_CABEC
	 * @param bean
	 * @param usuario
	 */
	public void insertClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario);
	
	/**
	 * Actualiza en tabla INC_PARTI_CONCU_CABEC 
	 * @param bean
	 * @param usuario
	 */
	public void updateClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario);
	
	/**
	 * Elimina registro en tabla INC_PARTI_CONCU_CABEC
	 * @param bean
	 * @param usuario
	 */
	public void deleteClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario);
	
	/**
	 * Inserta en tabla INC_PARTI_CONCU_DETAL
	 * @param bean
	 * @param usuario
	 */
	public void insertClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario);
	
			
	/**
	 * Actualiza en tabla INC_PARTI_CONCU_DETAL
	 * @param bean
	 * @param usuario
	 */
	public void updateClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario);
	
	/**
	 * Elimina registro en tabla INC_PARTI_CONCU_DETAL
	 * @param bean
	 * @param usuario
	 */
	public void deleteClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario);
	
	/**
	 * Obtiene el siguiente OID de la secuencia para la tabla INC_PARTI_CONCU_CABEC 
	 * @param params
	 * @return
	 */
	public Integer getNextOidClasificacionParaINC(Map params) ;
	
	/**
	 * Obtiene el siguiente OID de la secuencia para la tabla INC_PARTI_CONCU_DETAL
	 * @param params
	 * @return
	 */
	public Integer getNextOidClasificacionParaINCDetalle(Map params);
	
	/**
	 * Devuelve Lista de la tabla INC_PARTI_CONCU_DETAL
	 * @param programa
	 * @return
	 */
	public List getClasificacionParaINCDetalleByCriteria(Map params);
	
	/**
	 * Devuelve registro ClasificacionParaINCDetalle
	 * @param params
	 * @return
	 */
	public ClasificacionParaINCDetalle getClasificacionParaINCDetalle(Map params);
	
	/**
	 * Devuelve Lista de Tipos de Clientes
	 * @param params
	 * @return
	 */
	public List getTipoClienteList(Map params);
	
	/**
	 * Devuelve Lista de Subtipos de Clientes
	 * @param params
	 * @return
	 */
	public List getSubTipoClienteList(Map params);
	
	/**
	 * Devuelve Lista de Tipo de Clasificaciones
	 * @param params
	 * @return
	 */
	public List getTipoClasificacionList(Map params);
	
	/**
	 * Devuelve Lista de Clasificaciones
	 * @param params
	 * @return
	 */
	public List getClasificacionList(Map params);
	
	/**
	 * Verifica si existe registro de Cabecera INC en la tabla INC_CLASI_PARTI_CONCU
	 * @param params
	 * @return
	 */
	public Integer getExisteRegistroClasificacionPartiConcu(Map params);
	
	/**
	 * Verifica si existe registro de Cabecera INC en la tabla INC_PARTI_CONCU_DETAL
	 * @param params
	 * @return
	 */
	public Integer getExisteRegistroClasificacionDetalle(Map params);
	
	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void insertNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos rango, Usuario usuario);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public List getNuevaConstanciaProgramaPuntosList(Map params);

	/**
	 * 
	 * @param rango
	 * @param usuario
	 */
	public void deleteNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos rango, Usuario usuario);	
	
	/**
	 * retorna la lista de Concursos Tipo de Programa Puntos
	 * 
	 * @return
	 */
	public List getListConcursosTipoProgramaPuntos();
	
	/**
	 * retorna el oid periodo desde y el oid periodo hasta
	 * 
	 * @return
	 */
	public Map getCampaniasIniFinByConcursoTipoProgramaPuntos(String numeroConcurso);
	
}
