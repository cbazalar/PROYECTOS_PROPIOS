/*
 * 
 */
package biz.belcorp.ssicc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.EntidadGenericoDefinicion;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.LabelSolicitudesCreditoValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.DatosConsultoraBasico;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.LabelDatosProductoValue;



/**
 * Service para ser accedido mediante AJAX. Los metodos deben ser configurados
 * en dwr.xml
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface AjaxService {

	public String deleteDetallePedidoConsultora(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numLote,
			String codigoVenta);

	public String devolverMensajexAccion(String accion, String codigoPais);

	/**
	 * Calcula el promedio Final de venta
	 * 
	 * @param varCodperiFinalPromVenta
	 * @param varNumCampPromedio
	 * @return
	 */
	public String execCalculoPeriIniPromVentas(String codperiFinalPromVenta,
			String numCampPromedio);

	/**
	 * Verifica si existe registros con Valor Critico al emitir el Reporte de
	 * Proyeccion Faltante en GP3
	 * 
	 * @param codigoPais
	 * @param fechaFacturacion
	 * @param numeroVersion
	 * @return
	 */
	public Integer existeValorCriticoReporteProyeccionFaltante(
			String codigoPais, String fechaFacturacion, String numeroVersion);

	public LabelValue[] getAccesoByCanal(String centro);

	/**
	 * Metodo que devuelve las acciones segun el documento y el usuario
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @param usuario
	 * @return
	 */
	public LabelValue[] getAccionesByDocumentoByUsuario(String codigoPais,
			String tipoDocumento, String usuario);
	
	/**
	 * Obtiene una lista Acciones de Cobranza en base a los parametros enviados.
	 * 
	 * @param codigoEtapaDeuda
	 *            Codigo Etapa Deuda Pais.
	 * 
	 * @param tipoAccion
	 *            Tipo de Accion de Cobranza.
	 * 
	 * @return Lista de Acciones de Cobranza.
	 */
	public LabelValue[] getAccionesCobranzaByEtapaTipoAccion(
			String codigoEtapaDeuda, String tipoAccion);

	public LabelValue[] getAccionesCobranzaByPaisSociedadEtapa(
			String codigoPais, String codigoEtapaDeuda);

	public LabelValue[] getAccionesCobranzaByPaisSociedadEtapaTipoAccion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String tipoAccion);

	/**
	 * Devuelve 1 si la consultora tiene un bloqueo o excepcion
	 * 
	 * @param codigoPais
	 * @param codigoCliente
	 * @param codigoPeriodo
	 * @return
	 */
	public Integer getBloqueoOnline(String codigoPais, String codigoCliente,
			String codigoPeriodo);

	public LabelDatosConsultoraValue[] getCabeceraConsultora(String codigoPais,
			String codigoConsultora);

	/**
	 * @param codigoPais
	 * @param codigoConsultora
	 * @return Devuelve la informacion de la consultora para la cabecera de
	 *         digitacion de pedidos simplificada
	 */
	public LabelDatosConsultoraValue[] getCabeceraConsultoraSimple(
			String codigoPais, String codigoConsultora);

	public LabelDatosProductoValue getCabeceraProducto(String codigoPais,
			String codigoProducto, String codigoPrograma, String codigoAnho);

	public LabelValue[] getCampanhasCriteria(String codigoPais,
			String codigoEmpresa, String codigoClasificacion, String anho,
			String indicador);

	/**
	 * Retonar n campanhas siguientes
	 * 
	 * @param campanhaProceso
	 * @param numCampana
	 * @return
	 */
	public String getCampanhaSiguientes(String campanhaProceso,
			String numCampana);

	public String getCampannaActualEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa);

	// EDU
	public String getCampannaEDUByPaisEmpresaCampanha(String codigoPais,
			String codigoEmpresa, String numeroCampanha);

	public String getCampannaSgteEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa);

	/**
	 * Metodo que verifica si el rango de campaas esta cerrada
	 * 
	 * @param codigoPeriodoInicio
	 * @param codigoPeriodoFin
	 * @param codigoMarca
	 * @param codigoCanal
	 * @return
	 */
	public String getCampCerrada(String codigoPais, String codigoPeriodoInicio,
			String codigoPeriodoFin, String codigoMarca, String codigoCanal);

	/**
	 * Devuelve la cantidad de Documento asignados en un rango especifico
	 * @param codigoPais
	 * @param tipoDocumentoContable
	 * @param codigoSubacceso
	 * @param ejercicio
	 * @param serieDocLegal
	 * @return
	 */
	public String[] getCantDocImpr(String codigoPais, String tipoDocumentoContable,
			String codigoSubacceso, String ejercicio, String serieDocLegal,
			String rangoDesdeDocInterno, String rangoHastaDocInterno);

	public String getCantidadClientesByPK(final String codigoCliente);

	/**
	 * Devuelve la cantidad de cruces de rangos de logros
	 * 
	 * @param codigoCliente
	 * @param campanaInicio
	 * @param campanaFin
	 * @return
	 */
	public String getCantidadCruceRangoLogro(String codigoCliente,
			String campanaInicio, String campanaFin);

	public String getCantidadPeriodosByPK(final String codigoPeriodo);

	/**
	 * Devuelve la cantidad de Territorios sin Secuencia
	 * 
	 * @param codigoRegion
	 * @return
	 */
	public String getCantidadTerritoriosSinSecuencia(String[] codigoRegion);

	/**
	 * Devuelve la cantidad de Zonas sin Secuencia
	 * 
	 * @param codigoRegion
	 * @return
	 */
	public String getCantidadZonasSinSecuencia(String[] codigoRegion);

	public LabelValue[] getCarterasByPaisSociedadEtapaDeudaPeriodoRegion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion);

	public LabelValue[] getCarterasByPaisSociedadEtapaDeudaPeriodoRegionZona(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion, String codigoZona);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de periodos.
	 */
	public LabelValue[] getCentroDistribucionByPais(final String codigoPais);

	/**
	 * @param oidTipoClasificacion
	 * @return
	 */
	public LabelValue[] getClasificacionByOidTipoClasificacion(
			String oidTipoClasificacion);

	/**
	 * @param oid
	 * @return
	 */
	public LabelValue[] getClasificacionByOidTipoClasificacionList(String oid);

	public LabelValue[] getClasificacionesByCodISOIdiomaTClienteSubTClienteTClasificacion(
			final String codigoIdiomaISO, final String codigoTipoCliente,
			final String codigoSubTipoCliente,
			final String codigoTipoClasificacion);

	public LabelValue[] getClasificacionesByCriteriaMultiple(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion);

	public LabelValue[] getClasificacionesByCriteriaMultipleOID(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion);

	public LabelValue[] getClasificacionesByCriteriaMultipleCodigo(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion);

	public LabelValue[] getClasificacionesByTipoClasificacion(
			String oidTipoClasificacion, String codigoIdiomaISO);

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeuda(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda);

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeudaPeriodo(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo);

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion, String codigoZona);

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZonaCartera(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion, String codigoZona,
			String codigoCartera);

	public LabelValue[] getCobradoresMultiplesByPaisSociedadEtapaDeudaPeriodoRegion(
			final String codigoPais, final String codigoSociedad,
			final String condigoEtapaDeuda, final String codigoPeriodo,
			final ArrayList codigoRegiones, String condicionTodos);

	public String getCodigoCicloByAnhoTipoCiclo(final String codigoAnho,
			final String tipoCiclo);

	public LabelValue[] getCodigoCiclosByPaisProgAnho(final String codigoPais,
			final String codigoPrograma, final String codigoAnho,
			final String tipoCiclo);

	/**
	 * Devuelve el codigo de cliente en base al numero de documento de identidad
	 * 
	 * @param numeroDocumentoIdentidad
	 * @return
	 */
	public String getCodigoClienteByDocumentoIdentidad(
			String numeroDocumentoIdentidad);

	public String getCodigoCursoEmpresa(String codigoPais, String codigoEmpresa);

	public LabelValue[] getCodigoEquivalenteClasificacion(
			String codigoTipoClasEquiv);

	String getCodigoInstructora(String codigoPais, String codigoEmpresa,
			String codigoUsuario);

	/**
	 * Retorna el codigo de mensaje
	 * 
	 * @param codigoModulo
	 * @return
	 */
	public String getCodigoMensajeByModulo(String codigoModulo,
			String oidPeriodoCorpo, String campanhaProceso, List listMensajePatron);

	/**
	 * Devulve la descripcion si es que existe premio en la matriz de
	 * facturacion
	 * 
	 * @param codigoPremio
	 * @param codigoPeriodo
	 * @return
	 */
	public LabelValue getCodigoPremio(String codigoPremio, String codigoPeriodo);

	public String getCodigoProductoByCodigoVenta(String codigoPeriodo,
			String codigoVenta);

	/**
	 * Metodo que obtiene el codigo de Resultado de Chequeo de un pedido
	 * 
	 * @param numeroPedido
	 * @return
	 */
	public String getCodigoResultadoChequeo(String numeroPedido);

	/**
	 * Devuelve cadena de codigos de cliente invalidos
	 * 
	 * @param codigosConsultoras
	 * @param codigoPais
	 * @return
	 */
	public String getCodigosErrados(String codigosConsultoras, String codigoPais);

	/**
	 * @param codigoVenta
	 * @param codigoPeriodo
	 * @return
	 */
	public LabelValueCUV getCodigoVentaPrecio(String codigoVenta,
			String codigoPeriodo);

	/**
	 * 
	 * Devuelve la descripcion del reeeplazado si existe el codigo para
	 * reemplazo
	 * 
	 * @param codigoVenta
	 * @param codigoPeriodo
	 * @return
	 */
	public LabelValue getCodigoVentaReemplazado(String codigoVenta,
			String codigoPeriodo);

	/**
	 * devuelve desscrion de reempazlo si existe reeemplazo para el codigo d
	 * eventa
	 * 
	 * @param codigoVenta
	 * @param codigoReemplazo
	 * @param codigoPeriodo
	 * @return
	 */
	public LabelValue getCodigoVentaReemplazo(String codigoVenta,
			String codigoReemplazo, String codigoPeriodo);

	/**
	 * Devuelve -1 si se trata de un solo premio, caso contraio devulve la fila
	 * que pasa como parametro
	 * 
	 * @param oidLotePremio
	 * @param fila
	 * @return
	 */
	public Integer getCodigoVentasFicticio(String oidLotePremio, String fila);

	/**
	 * Devuelve el Codigo Linea Armado por Defecto
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public String getCodLineaArmadoDefault(String codPais, String codCentro);

	/**
	 * @param codMapaCD
	 * @return Cod Mapa Default
	 */
	public String getCodMapaZonaDefault(String codMapaCD);

	public LabelValue[] getComisiones(String codigoPeriodo);

	public LabelValue[] getComisionesCompletas();

	public LabelValue[] getComisionesTodas(String codigoPeriodo);

	public LabelValue[] getComprobantesByPaisBancoCuentaCorriente(
			final String codigoPais, final String codigoBanco,
			final String codigoCuentaCorriente);

	/**
	 * Se Recupera los concursos de acuerdo al pais, marca y canal pasado por
	 * parametro
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getConcursos2ByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos);

	/**
	 * Retorna la lista de Concursos para Bloqueo de Premios
	 * 
	 * @param codigoPeriodo
	 * @param regiones
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getConcursosBloqueoPremios(String codigoPeriodo,
			String regiones, String condicionTodos);

	/**
	 * Obtiene lista de concursos de Bonificacion
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public LabelValue[] getConcursosBonificacion(String codigoPeriodo);

	public LabelValue[] getConcursosByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos);

	/**
	 * Permite lista los Concursos vigentes y no vigentes
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param condicionTodos
	 * @param baseCalculo
	 * @param anhio
	 * @return
	 */
	public LabelValue[] getConcursosByPaisMarcaCanalAnhioTodos(
			String codigoPais, String codigoMarca, String codigoCanal,
			String condicionTodos, String baseCalculo, String anhio);

	public LabelValue[] getConcursosByPaisMarcaCanalDetalle(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos,
			String baseCalculo);

	/**
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param condicionTodos
	 * @param baseCalculo
	 * @return
	 */
	public LabelValue[] getConcursosByPaisMarcaCanalDetallePremiosElec(
			String codigoPais, String codigoMarca, String codigoCanal,
			String condicionTodos, String baseCalculo);

	public LabelValue[] getConcursosByPaisMarcaCanalPeriodo(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoPeriodo);

	/**
	 * Se Recupera solo los concursos vigentes de acuerdo al pais, marca y canal
	 * pasado por parametro
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getConcursosVigentesByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos);

	/**
	 * Metodo que obtiene la cantidad de ocurrencias de un consolidado
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoCliente
	 * @return
	 */
	public String getConsolidadoCabeceraByPK(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numeroLote);

	public String[] getConsultoraByCriteria(final String codigoPais,
			final String codigoConsultora);

	public String getConsultoraExistenteByCriteria(final String codigoPais,
			final String periodo, final String codigoConsultora,
			final String fechaFacturacion);

	/**
	 * @param numeroBoleta
	 * @param codigoPais
	 * @return
	 */
	public LabelValueCDR getConsultoraReclamoByCodigo(String numeroBoleta,
			String codigoPais, String codigoPeriodoActivo);

	/**
	 * Retorna el correlativo por documento y seccion
	 * 
	 * @param codigoDocumento
	 * @param codigoSeccion
	 * @return
	 */
	public String getCorrelativoDocumentoSecccion(String codigoModulo,
			String codigoSeccion, String oidPatron, List listMensajePatron);

	/**
	 * Mtodo que valida si el periodo existe o no
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public String getCronogramaBRValidarPeriodoExistente(String codigoPeriodo);

	public LabelValue[] getCuentasCorrientesPorPaisSociedad(
			final String codigoPais, final String codigoSociedad);

	public LabelValue[] getCuentasCorrientesPorPaisSociedadBanco(
			final String codigoPais, final String codigoSociedad,
			final String codigoBanco);

	public LabelValue[] getCuponesByNivel(final String codigoPais,
			final String codigoPrograma, final String codigoNivel,
			final String condicionTodos);

	public LabelValue[] getCuponesFaltantesByNivel(final String codigoPais,
			final String codigoPrograma, final String codigoNivel,
			final String condicionTodos);

	public LabelValue[] getCuponesFaltantesPeriodoByNivel(
			final String codigoPais, final String codigoPrograma,
			final String codigoNivel, final String codigoPeriodo,
			final String condicionTodos);

	/**
	 * Metodo que devuelve la lista de cupones no homologados en la matriz de
	 * cupones
	 * 
	 * @param codigoPais
	 * @param codigoPrograma
	 * @param codigoPeriodo
	 * @return
	 */
	public String getCuponesNoHomologados(String codigoPais,
			String codigoPrograma, String codigoPeriodo);

	public LabelValue[] getCuponesPeriodoByNivel(final String codigoPais,
			final String codigoPrograma, final String codigoNivel,
			final String codigoPeriodo, final String condicionTodos);

	public LabelValue[] getCursosByPaisEmpresa(String codigoPais,
			String codigoEmpresa);

	public LabelValue[] getCursosByPaisEmpresaSinVacio(String codigoPais,
			String codigoEmpresa);

	public String getDatosCliente(String codigoPais, String codigoEmpresa,
			String codigoCliente);

	public LabelValue[] getDatosComisionByTipoComisionista(String codigoPais,
			String tipoComisionista, String flagTodosEnTipo);

	/**
	 * devuelve los datos del producto entre ellos el CUV por codigo SAP para
	 * luego ser modificado
	 * 
	 * @param codigoSAP
	 * @return
	 */
	public LabelValueCUV getDatosCUVByCodigoSAP(String codigoSAP);

	public String getDatosInstructora(String codigoPais, String codigoEmpresa,
			String codigoInstructora);

	EntidadGenericoDefinicion getDefinicionEntidadGenerico(
			String codigoEntidad, String flagTodosEnTipo);

	public LabelPedidosValue[] getDescPrecio(String codigoPais,
			String codigoPeriodo, String codigoCliente, String fechaSolicitud,
			String codigoVenta);

	/**
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoCliente
	 * @param numLote
	 * @param codigoVenta
	 * @return
	 */
	public LabelPedidosValue[] getDescPrecio2(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numLote,
			String codigoVenta);

	public LabelValue[] getDescripcionByProducto(String producto);

	/**
	 * Devuelve la descripcion del Centro de Distribucion
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public String getDescripcionCentroDistribucion(String codPais,
			String codCentro);

	/**
	 * Devuelve descripcion Producto
	 * 
	 * @param codigoSap
	 * @return
	 */
	public String getDescripcionInternacionalizableProducto(String codigoSap);

	public String getDescripcionProductoByCodigoVenta(String codigoPeriodo,
			String codigoVenta);

	/**
	 * Obtiene la lista de Zonas por Region
	 * 
	 * @param codigoPais
	 * @param codigoCanal
	 * @param codigoMarca
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getDesZonasByPaisMarcaCanalRegion(
			final String codigoPais, final String codigoCanal,
			final String codigoMarca, final String codigoRegion);

	/**
	 * Devulve el oid de la matriz de facturacion
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public Integer getDevuelveOidMatrizFacturacion(String codigoPeriodo);

	/**
	 * Devulve el numero de identidad principal
	 * 
	 * @param oidCliente
	 * @return
	 */
	public String getDocumentoIdentidad(String oidCliente);

	public LabelValue[] getDocumentos(String codigoPais, String codTipo);

	/**
	 * Devuelve los documentos contables
	 * 
	 * @return
	 */
	public LabelValue[] getDocumentosContables();

	public String getEsInstructora(String codigoPais, String codigoEmpresa,
			String usuarioLogin);

	public LabelValue[] getEstadosPedidos();

	/**
	 * Metodo que devuelve la lista de estados en base al tipo de documento
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @return
	 */
	public LabelValue[] getEstadosSTOByTipoDocumento(String codigoPais,
			String tipoDocumento);

	public LabelValue[] getEtapasDeudaByPaisSociedad(String codigoPais,
			String codigoSociedad);

	public LabelValue[] getEtapasDeudaByPaisSociedadCobrador(String Cobrador);

	/**
	 * @param codBarra
	 * @param codPais
	 * @return
	 */
	public String[] getEtiquetayListadoPicadoByCodBarrayOidPais(
			String codBarra, String codPais, String usuario);

	public String getExisteCodigoCliente(String codigoPais, String codigoCliente);

	public String getExisteComisionComercializacion(final String fechaDesde,
			final String fechaHasta, final String codigoComision);

	public String getExisteComisionIngreso(final String codigoPeriodo,
			final String codigoComision);

	public String getExisteComisionRecuperacion(final String codigoPeriodo,
			final String codigoComision);

	/**
	 * Verifica si existe el calculo para dicha comision antes de ser calculada
	 * @param codigoPeriodo
	 * @param codigoComision
	 * @param codigoTipoComision
	 * @return
	 */
	public Integer getExisteComisionRecuperacion(final String codigoPeriodo,
			final String codigoComision, final String codigoTipoComision);

	/**
	 * Devuelve 1 si existe poliza activa para consultora, caso contario 0
	 * 
	 * @param oidLotePremio
	 * @param fila
	 * @return
	 */
	public Integer getExisteConsultoraPolizaActiva(String codigoCliente);

	/**
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param periodo
	 * @param numeroLote
	 * @return
	 */
	public String getExistePedidoConsultora(String codigoPais,
			String codigoEmpresa, String periodo, String numeroLote);

	public String getExisteProyeccionFaltanteDia(final String codigoPais,
			final String fechaFacturacion);

	/**
	 * @param codigoPais
	 * @param numDocu
	 * @param codigoPeriodo
	 * @param codigoCliente
	 * @return
	 */
	public String getExisteReclamo(String codigoPais, String numDocu,
			String codigoPeriodo, String codigoCliente);

	public LabelValue[] getFacturacion(String centro);

	/**
	 * Retorna la fecha de Actividad asociada a la zona y campanha de actividad
	 * 
	 * @param campanhaActividad
	 * @param codigoRegion
	 * @param codigoZona
	 * @param oidActividad
	 * @return
	 */
	public String getFechaActividad(String campanhaActividad,
			String codigoRegion, String codigoZona, String oidActividad);

	public String getFechaFacturacionActualEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa);

	public String getFechaFinalPeriodoByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoPeriodo);

	public String getFechaInicioPeriodoByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoPeriodo);

	public LabelValue[] getHistoricoCursoDictado(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanha,
			String codigoInstructora, String editable);

	public LabelValue[] getHistoricoCursoDictadoCapacitadas(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanhaInicial,
			String campanhaFinal, String codigoCliente,
			String codigoInstructora, String region[]);

	public LabelValue[] getHistoricoPlanillaCapacitadas(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanhaInicial,
			String campanhaFinal, String codigoCliente,
			String codigoInstructora, String codigoDictado, String region[]);

	public LabelValue[] getHistoricoPlanillaProgramadas(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanhaInicial,
			String campanhaFinal, String codigoCliente, String region[]);

	/**
	 * Devuelve el Indicador de Cambio de Direccion de acuerdo a la
	 * clasificacion
	 * 
	 * @param codigoEstadoEntrega
	 * @param codigoNovedad
	 * @param calificacion
	 * @return
	 */
	public String getIndCambioDireccionCalificacion(String codigoEstadoEntrega,
			String codigoNovedad, String calificacion);

	/**
	 * Devuelve el indicador de centro de servicio
	 * 
	 * @param codigoVenta
	 * @return
	 */
	public LabelValue getIndicadorCentroServicio(String codigoVenta);

	/**
	 * Devuelve la informacion del CUV en el periodo
	 * 
	 * @param codigoPeriodo
	 * @param codigoVenta
	 * @param indicadorPrincipal
	 * @return
	 */
	public ProductoAgregacion getInformacionCUV(String codigoPeriodo,
			String codigoVenta, String indicadorPrincipal);

	public LabelValue[] getInterfaces(String codigoPais, String codigoSistema,
			String codigoInterfaz);

	public LabelValue[] getInterfacesBySistema(final String codigoPais,
			final String codigoSistema);

	/**
	 * Obtiene la fecha inicio y fecha fin, de un periodo de facturacion.
	 * 
	 * @param codigoPeriodo
	 *            Codigo del periodo.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @param codigoAcceso
	 *            Codigo del acceso.
	 * @return
	 */

	public String[] getIntervalosDocumentosAnulados(final String codigoPeriodo,
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoAcceso);

	/**
	 * Obtiene la fecha inicio y fecha fin, de un periodo de facturacion.
	 * 
	 * @param codigoPeriodo
	 *            Codigo del periodo.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @return
	 */
	public String[] getIntervalosFechaComplementoFacturasVentaDirecta(
			final String codigoPeriodo, final String codigoPais,
			final String codigoMarca, final String codigoCanal);

	/**
	 * Obtiene la fecha inicio y fecha fin, de un periodo de facturacion.
	 * 
	 * @param codigoPeriodo
	 *            Codigo del periodo.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @param codigoAcceso
	 *            Codigo del acceso.
	 * @return
	 */
	public String[] getIntervalosFechaFacturasCabecera(
			final String codigoPeriodo, final String codigoPais,
			final String codigoMarca, final String codigoCanal,
			final String codigoAcceso);

	/**
	 * Obtiene la fecha inicio y fecha fin, de un periodo de facturacion.
	 * 
	 * @param codigoPeriodo
	 *            Codigo del periodo.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @param codigoAcceso
	 *            Codigo del acceso.
	 * @return
	 */
	public String[] getIntervalosFechaFacturasVentaDirecta(
			final String codigoPeriodo, final String codigoPais,
			final String codigoMarca, final String codigoCanal,
			final String codigoAcceso);

	/**
	 * Se lista los periodos que existan en un concurso seleccionado
	 * 
	 * @param codigoPais
	 * @param codigoConcurso
	 * @return
	 */
	public String getLIDValidaPeriodosConcurso(String codigoPais,
			String codigoConcurso);

	public LabelValue[] getLinea(final String codigoPais);

	/**
	 * Devuelve la lista de Linea de Armado por Centro de Distribucion de un
	 * Pais
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public LabelValue[] getLineaArmadobyOidCentro(String codPais,
			String codCentro);

	/**
	 * Devuelve los Codigos de Linea de Armado de un Centro de Distribucin
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public LabelValue[] getLineaArmadoComboList(String codPais, String codCentro);

	/**
	 * Devuelve la lista de Linea de Armado
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public LabelValue[] getLineaArmadoListar(String codPais, String codCentro);

	/**
	 * Obtiene la lista de Linea de Armado por el mapa de centro de distribucin
	 * 
	 * @param codMapaCentro
	 * @return
	 */
	public LabelValue[] getLineaMapaOrdenComboList(String codMapaCentro);

	/**
	 * Obtiene las lineas de Armado por Mapa Centro y Mapa Zona
	 * 
	 * @param codMapaCentro
	 * @param codMapaZona
	 * @param codCentro
	 * @return
	 */
	public LabelValue[] getLineaMapaOrdenZonaList(String codMapaCentro,
			String codMapaZona, String codCentro);

	public LabelPedidosValue[] getList(String codigo, String descripcion,
			String precioCat, String unidades, String total);

	public LabelSolicitudesCreditoValue[] getListadoPedidoZona(
			String codigozona, String numLote, String unidades);

	public LabelValue[] getListaPeriodosByBasCtrlFact(String codigoPais,
			String staCamp, String condicionTodos);

	public LabelValue[] getListaPeriodosByPaisCanal(String codigoPais,
			String codigoCanal);

	public ArrayList getListaPeriodosInicial(Map params);

	public LabelValue[] getListaPeriodosPaisCanalAnioRango(String codigoPais,
			String codigoCanal, String codigoAnio, String codigoRango);

	/**
	 * Devuelve la lista opciones de roles, pero solo los que tienen acceso
	 * 
	 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
	 * @param codigoRol
	 * @return
	 */
	public String getListaRolSICCSeleccionados(String codigoRol);

	/**
	 * Devuelve ls lista roles de usuarios, pero solo los que tienen acceso
	 * 
	 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
	 * @param codigoUsuario
	 * @return
	 */
	public String getListaUsuarioSICCSeleccionados(String codigoUsuario);

	public LabelValue[] getLocalesEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa);

	public LabelValue[] getLocalesEDUByPaisEmpresaRegion(String codigoPais,
			String codigoEmpresa, String codigoRegion);

	public String getLongitudCodCliente(String codigoPais);

	public String getLongitudCodInstructora(String codigoPais);

	public String getLongitudTipoDocumento(String oidPais,
			String oidTipoDocumento);

	/**
	 * Obtine la longitud del tipo documento
	 * 
	 * @param oidPais
	 * @param codigoTipoDocumento
	 * @return
	 */
	public String getLongitudTipoDocumentoByCodigo(String oidPais,
			String codigoTipoDocumento);

	public LabelValue[] getMapaByCentro(String centro);

	/**
	 * Devuele la lista de Mapa Centro de Distribucion por Linea de Armado
	 * 
	 * @param codigoCentro
	 * @param codigoLinea
	 * @return
	 */
	public LabelValue[] getMapaCentroDistbyLineaList(String codigoCentro,
			String codigoLinea);

	/**
	 * Se recupera los mapas distribucion relacionados a un centro de
	 * Distribucin
	 * 
	 * @param codigoPais
	 * @param codigoCentroDistribucion
	 * @param codigoIdiomaISO
	 * @return
	 */
	public LabelValue[] getMapaCentroDistribucion(String codigoPais,
			String codigoCentroDistribucion, String codigoIdiomaISO);

	/**
	 * Devuelve los Mapas del Centro de Distribucion
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public LabelValue[] getMapaCentroDistribucionComboList(String codPais,
			String codCentro);

	/**
	 * Devuelve la lista de Mapa de Distribucin por Centro de Distribuicin
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public LabelValue[] getMapaCentroDistribucionListar(String codPais,
			String codCentro);

	/**
	 * Devuelve la lista de Orden de Anaqueles
	 * 
	 * @param codMapaZona
	 * @return
	 */
	public LabelValue[] getMapaOrdenComboList(String codMapaZona);

	/**
	 * Recupera las mapas de Zona relacionados a un mapa de centro de
	 * Distribucin
	 * 
	 * @param oidMapaCD
	 * @param codigoIdiomaISO
	 * @return
	 */
	public LabelValue[] getMapaZonaCentroDistribucion(String oidMapaCD,
			String codigoIdiomaISO);

	/**
	 * Devuelve la lista de Mapa Zonas de un Mapa de Centro de Distribucion
	 * 
	 * @param codMapaCentro
	 * @return
	 */
	public LabelValue[] getMapaZonaComboList(String codMapaCentro,
			String codCentro);

	/**
	 * @param codPais
	 * @param codCentro
	 * @param codMapaCD
	 * @return Lista de Mapa de Zona por Oid Mapa CD
	 */
	public LabelValue[] getMapaZonaListar(String codPais, String codCentro,
			String codMapaCD);

	public LabelValue[] getMapaZonasByCentro(String codigoPais, String centro);

	/**
	 * Devuelve el mensaje de acuerdo a la clasificacion
	 * 
	 * @param codigoEstadoEntrega
	 * @param codigoNovedad
	 * @param calificacion
	 * @return
	 */
	public String getMensajeCalificacion(String codigoEstadoEntrega,
			String codigoNovedad, String calificacion);

	/**
	 * Devuelve data en caso haya un detalle del reporte que no cumpla con la
	 * condicion
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @return
	 */
	public String getMensajeReporteGuiaProductos(String codigoPais,
			String codigoPeriodo);

	/**
	 * Devuelve data en caso haya un detalle del reporte que no cumpla con la
	 * condicion
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @return
	 */
	public String getMensajeReporteRecuperaciones(String codigoPais,
			String codigoPeriodo);

	/**
	 * Devuelve data en caso haya un detalle del reporte que no cumpla con la
	 * condicion
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @return
	 */
	public String getMensajeReporteReemplazos(String codigoPais,
			String codigoPeriodo);

	/**
	 * Devuelve si encuentra registros mayores que el maximo valor de oferta
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @return
	 */
	public String getMensajexValorMaximoOferta(String codigoPais,
			String codigoPeriodo);

	/**
	 * Obtiene el codigo y la descripcion de un menu base al codigo enviado.
	 * 
	 * @param codigoMenu
	 *            codigo del menu
	 * @param codigoIdioma
	 *            codigo del idioma usado para obtener la descripcion
	 * @return Arreglo de String conteniendo dos valores, el codigo y la
	 *         descripcion del menu.
	 */
	public String[] getMenu(final String codigoMenu, final String codigoIdioma);

	/**
	 * Devuelve la cadena del rango de la fecha de Acitivada
	 * 
	 * @param campanhaActividad
	 * @param fechaActividad
	 * @return
	 */
	public String getMessageRangoFechaActividad(String campanhaActividad,
			String fechaActividad);

	/**
	 * Devuelve la cadena 
	 * 
	 * @param campanhaActividad
	 * @param fechaActividad
	 * @return
	 */
	public String getMessageZonasAprobadasNoProcesadas(String campanhaActividad,
			String fechaActividad);

	/**
	 * Metodo que devuelve los motivos de rechazo por documento de STO
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @return
	 */
	public LabelValue[] getMotivosRechazo(String codigoPais,
			String tipoDocumento);

	public String getNextPeriodoByCupProgPerio(final String codigoPais,
			final String codigoPrograma);

	/**
	 * Retorna el nivel actual de la consultora si no hay nivel retorna vacio
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param tipoComisionista
	 * @param anioInicTramo
	 * @param codTramo
	 * @param codigoNivel
	 * @param codigoCliente
	 * @return
	 */
	public String getNivelActualEjecutiva(String codigoPais,
			String codigoMarca, String codigoCanal, String tipoComisionista,
			String anioInicTramo, String codTramo, String codigoCliente);

	public LabelValue[] getNivelesByConcurso(final String numeroConcurso,
			String condicionTodos);

	/**
	 * Retorna la lista de niveles asociado al Concurso
	 * 
	 * @param codigoPais
	 * @param numeroConcurso
	 * @return
	 */
	public LabelValue[] getNivelesConcurso(String codigoPais,
			String numeroConcurso);

	/**
	 * Metodo que obtiene el nombre del cliente
	 * 
	 * @param codigoCliente
	 * @return
	 */
	public String getNombreCliente(String codigoCliente);

	/**
	 * Devuelve nombre proceso
	 * 
	 * @param codigoProceso
	 * @return
	 */
	public String getNombreProgramaProceso(String codigoProceso);

	/**
	 * Obtiene el numero de documento de identidad del cliente
	 * 
	 * @param codigoCliente
	 * @param codigoTipoDocu
	 * @return
	 */
	public String getNumDocumentoIdentByCodigoCliente(String codigoCliente,
			String codigoTipoDocu);

	public LabelValue[] getNumeroConcursoList(String codPais, String codMarca,
			String periodo);

	/**
	 * Metodo que obtiene la cantidad de pedidos para el numero de lote
	 * 
	 * @return
	 */
	public LabelValue getNumeroPedidosByLote(String id);

	/**
	 * Devuelve el valor por defecto para mostrar las lneas del listado
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @return
	 */
	public String getNumeroRegistrosSTO(String codigoPais,
			String tipoDocumento, String tipoLinea);

	public LabelValue[] getNumeroVersionesFaltanteDia(final String codigoPais,
			final String fechaFacturacion);

	public String getNumPeriodoEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa, String codigoCurso);

	/**
	 * Obtiene el Cdigo de Nivel Agrupacion Olas y la Descripcin
	 * 
	 * @param codPais
	 * @param codCentro
	 * @return
	 */
	public String[] getObtenerNivelAgrpOlas(String codPais, String codCentro);

	public LabelValue[] getOpcionesMenuAsignadas(String codigoMenu,
			String opcionVacia);

	public LabelValue[] getOpcionesMenuFaltantes(String codigoMenu,
			String opcionVacia);

	public LabelValue[] getOpcionMensaje(String codigoPais,
			String codigoEmpresa, String codigoCurso);

	/**
	 * Retorna las operciones del directorio
	 * 
	 * @param codigoCliente
	 * @return
	 */
	public LabelValue[] getOperacionesDirectorio(String codigoCliente,
			String oidCliente);

	/**
	 * Retorna las operaciones que estan en el directorio asociado a una region
	 * 
	 * @param codigoRegiones
	 * @return
	 */
	public LabelValue[] getOperacionesRegionesDirectorio(String[] codigoRegiones);

	/**
	 * Recupera los orden de anaqueles relacionados a un mapa de Zonas
	 * 
	 * @param oidMapaZonaCD
	 * @param codigoIdiomaISO
	 * @return
	 */
	public LabelValue[] getOrdenAnaqueles(String oidMapaZonaCD,
			String codigoIdiomaISO);

	/**
	 * @param codMapaZona
	 * @return Lista de Orden Anaqueles por Cod Mapa Zona
	 */
	public LabelValue[] getOrdenAnaquelListar(String codMapaZona);

	/**
	 * Metodo que devuelve la lista de origenes en base al tipo de documento
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @return
	 */
	public LabelValue[] getOrigenSTOByTipoDocumento(String codigoPais,
			String tipoDocumento);

	public LabelValue[] getParametroClasificacionByPaisEmpresaCursoTipoCurso(
			String codigoPais, String codigoEmpresa, String codigoCurso,
			String tipoCurso, String indicadorVacio);

	public LabelValue[] getParametroClasificacionByPaisEmpresaTipo(
			String codigoPais, String codigoEmpresa, String tipoClasificacion);

	/**
	 * Devuelve el parametro de rechazo de documento de STO
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @return
	 */
	public String getParametroRechazoByDocumento(String codigoPais,
			String tipoDocumento);

	public List getParametroRegistroClasificacion(String codigoPais,
			String codigoEmpresa, String codigoProceso);

	public String getPeriodoDefaultByPaisCanal(String codigoPais,
			String codigoCanal);

	public String getPeriodoDefaultByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal);

	public String getPeriodoDefaultByPaisMarcaCanalAcceso(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoAcceso);

	/**
	 * Obtiene lafecha y el periodo de la campaa activa
	 */
	public String[] getPeriodoFechaCampanyaActiva();

	public String[] getPeriodoFechaCampanyaActivaSF(String codigoPais, String conExterna);
	
	public String getCargoBaseById(String codigoCargo);

	public String getPeriodoFin(String codigoRango);

	public String getPeriodoInicio(String codigoRango);

	/**
	 * Recupera el periodo de inicio de un registro de la entidad Nivel Despacho
	 * Diferido
	 * 
	 * @param codigoPais
	 * @param numeroConcurso
	 * @param nivel
	 * @return
	 */
	public String getPeriodoNivelDespachoDiferido(String codigoPais,
			String numeroConcurso, String nivel);

	/**
	 * @param codigo
	 * @param periodoActivo
	 * @return
	 */
	public String getPeriodoReclamo(String codigo, String periodoActivo);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de periodos.
	 */
	public LabelValue[] getPeriodosByPaisMarcaCanal(final String codigoPais,
			final String codigoMarca, final String codigoCanal);

	public LabelValue[] getPeriodosByPaisMarcaCanalAcceso(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoAcceso);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de periodos donde la fecha fin es menor o igual a la del
	 *         sistema ordenados descendientemente.
	 */
	public LabelValue[] getPeriodosByPaisMarcaCanalOrderFechaFin(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de periodos donde la fecha fin es menor o igual a la del
	 *         sistema ordenados descendientemente.
	 */
	public LabelValue[] getPeriodosByPaisMarcaCanalRangos(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String rangoInicial,
			final String rangoFinal);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @return Arreglo de String conteniendo los codigos de los periodos.
	 */
	public LabelValue[] getPeriodosComplementoFacturasVentaDirecta(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal);

	/**
	 * Obtiene los codigos de los periodos en base al codigo de pais, marca,
	 * canal y con la condicion adicional de que la fecha del sistema se
	 * encuentre entre la fecha de inicio y fin de estos periodos.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * @param codigoMarca
	 *            Codigo de marca.
	 * @param codigoCanal
	 *            Codigo de canal.
	 * @return Lista de periodos.
	 */
	public LabelValue[] getPeriodosDefaultByPaisMarcaCanal(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal);

	public LabelValue[] getPeriodosDefaultByPMC(final String codigoPais,
			final String codigoMarca, final String codigoCanal);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @param codigoAcceso
	 *            Codigo del acceso.
	 * 
	 * @return Arreglo de String conteniendo los codigos de los periodos.
	 */
	public LabelValue[] getPeriodosDocumentosAnulados(final String codigoPais,
			final String codigoMarca, final String codigoCanal);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @param codigoAcceso
	 *            Codigo del acceso.
	 * 
	 * @return Arreglo de String conteniendo los codigos de los periodos.
	 */
	public LabelValue[] getPeriodosDocumentosAnuladosAcceso(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoAcceso);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @param codigoAcceso
	 *            Codigo del acceso.
	 * 
	 * @return Arreglo de String conteniendo los codigos de los periodos.
	 */
	public LabelValue[] getPeriodosFacturasCabecera(final String codigoPais,
			final String codigoMarca, final String codigoCanal,
			final String codigoAcceso);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @param codigoAcceso
	 *            Codigo del acceso.
	 * 
	 * @return Arreglo de String conteniendo los codigos de los periodos.
	 */
	public LabelValue[] getPeriodosFacturasVentaDirecta(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoAcceso);

	/**
	 * Obtiene los Periodos de los productos no atendidos de la Bolsa de
	 * Faltantes
	 * 
	 * @param codigoPais
	 * @param oidConcurso
	 * @return
	 */
	public LabelValue[] getPeriodosProductosNoAtendidos(String codigoPais,
			String oidConcurso);

	/**
	 * Obtiene los codigos de los periodos en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo del pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de la marca.
	 * 
	 * @param codigoCanal
	 *            Codigo del canal.
	 * 
	 * @return Arreglo de String conteniendo los codigos de los periodos.
	 */
	public LabelValue[] getPeriodosSABRentabilidadPorZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal);

	public LabelValue[] getPlanillaInstructora(String codigoPais,
			String codigoEmpresa, String codigoCurso, String codigoRegion,
			String codigoInstructora, String campanhaInicio);

	/**
	 * Lista de Planillas por instructoras con su respectiva zona univoca
	 * 
	 * @author sbuchelli
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param codigoCurso
	 * @param codigoRegion
	 * @param codigoInstructora
	 * @param campanhaInicio
	 * @return Lista de Planillas por instructoras con su respectiva zona
	 *         univoca
	 * 
	 * */
	public LabelValue[] getPlanillaInstructoraZona(String codigoPais,
			String codigoEmpresa, String codigoCurso, String codigoRegion,
			String codigoInstructora, String campanhaInicio);

	/**
	 * Lista de Planillas Procesadas por instructoras con su respectiva zona
	 * univoca de su curso de dictado
	 * 
	 * @author sbuchelli
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param codigoCurso
	 * @param codigoRegion
	 * @param codigoInstructora
	 * @param campanhaInicio
	 * @param codigoCursoDictado
	 * @param verZonas
	 * @return Lista de Planillas Procesadas por instructoras con su respectiva
	 *         zona univoca de su curso de dictado
	 * 
	 * */
	public LabelValue[] getPlanillaInstructoraZonaProcesadas(String codigoPais,
			String codigoEmpresa, String codigoCurso, String codigoRegion,
			String codigoInstructora, String campanhaInicio,
			String codigoCursoDictado, String verZonas);

	public LabelPedidosValue[] getPrecioByCodigoVenta(String codigoPais,
			String codigoProducto, String codigoPeriodo, String codigoVenta);

	/**
	 * Retorna la lista de premios de un determinado Concurso
	 * 
	 * @param codigoConcurso
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getPremiosxConcurso(String codigoConcurso,
			String condicionTodos);

	public LabelValue[] getProcesoBatchBySistema(final String codigoPais,
			final String codigoSistema);

	/**
	 * Obtiene el Cdigo , Pais , Linea
	 * 
	 * @param codSap
	 * @param codPais
	 * @param codLinea
	 * @return
	 */
	public String[] getProductoByCodSapyOidPais(String codSap, String oidPais,
			String codLinea);

	/**
	 * Obtiene los Productos no atendidos de la Bolsa de Faltantes
	 * 
	 * @param codigoPais
	 * @param oidConcurso
	 * @param oidPeriodo
	 * @return
	 */
	public LabelValue[] getProductosNoAtendidos(String codigoPais,
			String oidConcurso, String oidPeriodo);

	/**
	 * @param codigoPais
	 * @param codigoPrograma
	 * @param codigoPeriodo
	 * @return
	 */
	public String getProgramaPeriodoById(String codigoPais,
			String codigoPrograma, String codigoPeriodo);

	public LabelValue[] getRegionByInstructora(String codigoPais,
			String codigoEmpresa, String instructora, String flagTodos);

	/**
	 * 
	 * @param codigoPais
	 * @param codigoCanal
	 * @param codigoMarca
	 * @param codigoSubGerencia
	 * @return
	 */
	public LabelValue[] getRegionByPaisMarcaCanalSubGerencia(
			final String codigoPais, final String codigoCanal,
			final String codigoMarca, final String codigoSubGerencia);

	/**
	 * Metodo que obtiene la lista de regiones cerradas y activas por medio del
	 * codigo de pais y periodo
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoMarca
	 * @param codigoCanal
	 * @return
	 */
	public LabelValue[] getRegionesActivasCerradasByPeriodo(String codigoPais,
			String codigoPeriodo, String codigoMarca, String codigoCanal);

	/**
	 * Devulve Todas las regiones activas del titular segun indicador
	 * 
	 * @param indicadorTitular
	 * @return
	 */
	public LabelValue[] getRegionesAllDirectorio(String indicadorTitular);

	/**
	 * Devulve Todas las regiones activas del titular segun indicador
	 * 
	 * @param indicadorTitular
	 * @return
	 */
	public LabelValue[] getRegionesAllDirectorioMantenimientoZON(String codigoPais, String codigoConexionExterna, String indicadorTitular, String cargo);

	public LabelValue[] getRegionesByPais(final String codigoPais);

	/**
	 * Obtiene el status de la existencia o no del territorio, si existe o no el
	 * territorio en la zona y si el territorio ingresado esta en el distrito
	 * del ubigeo.
	 * 
	 * @param codigoPais
	 * @param codigoTerritorio
	 * @param codigoZona
	 * @param orden1
	 * @param orden2
	 * @param orden3
	 * @param codigoCliente
	 * 
	 * @return Vector con 3 valores 0/1, para cada caso.
	 */
	public LabelValue[] getRegionesByPaisCanal(final String codigoPais,
			final String codigoCanal);

	/**
	 * Devuelve todas las regiones por pais, activos e inactivos
	 * 
	 * @param codigoPais
	 * @return
	 */
	public LabelValue[] getRegionesByPaisLet(String codigoPais);

	/**
	 * Obtiene una lista de Regiones en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de Regiones.
	 */

	public LabelValue[] getRegionesByPaisMarcaCanal(final String codigoPais,
			final String codigoMarca, final String codigoCanal);

	/**
	 * Obtiene una lista de Regiones sin cierre de facturacin
	 * 
	 * @param codigoPais	Codigo de pais
	 * @param codigoMarca	Codigo de marca
	 * @param codigoCanal	Codigo de canal
	 * @param periodo		periodo
	 * 
	 * @return	Lista de regiones
	 */
	
	public LabelValue[] getRegionesByPaisMarcaCanalPeriodo(final String codigoPais,
			final String codigoMarca, final String codigoCanal, final String periodo);

	public LabelValue[] getRegionesByPaisMarcaCanalDetalle(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String tipoDetalle);

	public LabelValue[] getRegionesByPaisSociedadEtapaDeuda(String codigoPais,
			String codigoSociedad, String codigoEtapaDeuda);

	public LabelValue[] getRegionesByPaisSociedadEtapaDeudaPeriodo(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo);

	public LabelValue[] getRegionesByPaisSubGerencia(final String codigoPais,
			final String codigoMarca, final String codigoCanal,
			final String condicionSubGerencia, String condicionTodos);

	public LabelValue[] getRegionesByPaisSubGerenciaMultiple(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String[] codigoSubGerencia,
			String condicionTodos);

	public LabelValue[] getRegionesByPeriodoBasCtrlFact(String codigoPais,
			String codigoPeriodo, String condicionTodos);

	public LabelValue[] getRegionesByPeriodoIntEviPerioRegio(String codigoPais,
			String codigoPeriodo, String condicionTodos);

	public Integer getRegionesCerradas(final ArrayList codigoRegion,
			String codigoPeriodo);

	/**
	 * Devulve las regiones activas del titular segun indicador
	 * 
	 * @param indicadorTitular
	 * @return
	 */
	public LabelValue[] getRegionesDirectorio(String indicadorTitular);

	public LabelValue[] getRegionesEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa);

	public LabelValue[] getRegionesEDUByPaisEmpresaInstructora(
			String codigoPais, String codigoEmpresa, String codigoInstructora);

	public LabelValue[] getRegionesSinAsignarByPaisSociedadEtapaDeudaPeriodo(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo);

	/**
	 * @param codigoPais
	 * @param campanhaProceso
	 * @param codigoRegiones
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getRegionMultipleByCerrar(final String codigoPais,
			final String campanhaProceso, String condicionTodos,
			String indicadorEdit);

	/**
	 * @param periodo
	 * @param tipoSolicitud
	 * @param fecha
	 * @param grupoProceso
	 * @return
	 */
	public String getRegistroByPeriodoTipoSoliFecha(String periodo,
			String tipoSolicitud, String fecha, String grupoProceso);

	/**
	 * @param codigoVenta
	 * @param codigoPeriodo
	 * @return
	 */
	public String getRegistrosProcesar(String codigoVenta, String codigoPeriodo, String codigoConsultora);

	/**
	 * Metodo que obtiene la lista de reportes
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @return
	 */
	public LabelValue[] getReportesByDocumento(String codigoPais,
			String tipoDocumento);

	public LabelValue[] getSalaEDUByPaisEmpresaLocal(String codigoPais,
			String codigoEmpresa, String codigoLocal, String llenarVacio);

	public LabelValue[] getSeccionesByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoRegion,
			final String codigoZona);

	public LabelValue[] getSeccionesTrasvaseEnActUniAdm(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoZona,
			String ordinal);

	public LabelValue[] getSeccionMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos);

	public LabelValue[] getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos);

	public LabelValue[] getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona1(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos);

	public String getServicioCapacitacionByPaisEmpresaCurso(String codigoPais,
			String codigoEmpresa, String codigoCurso);

	/**
	 * Devuelve el numero de registros del reporte
	 * 
	 * @param fecha
	 * @param tipoCierre
	 * @return
	 */
	public Integer getSizeEstadoPremiosDespacho(String fecha, String tipoCierre);

	/**
	 * Devuelve la cantidad de solicitudes asociadas
	 * 
	 * @param periodo
	 * @param tipoSolicitud
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public String getSolicitudesGP2ByPeriodoTipoSoliFecha(String periodo,
			ArrayList tipoSolicitud, String fechaInicio, String fechaFin);

	public String getStatusIndicadorNombreCompleto(String codigoPais,
			String codigoEmpresa);

	/**
	 * Obtiene el status de la existencia o no de la zona para un cliente
	 * especifico.
	 * 
	 * @param codigoPais
	 * @param codigoTerritorio
	 * @param codigoZona
	 * @param orden1
	 * @param orden2
	 * @param orden3
	 * @param codigoCliente
	 * 
	 * @return Vector con 3 valores 0/1, para cada caso.
	 */
	public int[] getStatusTerritorioByCliente(final String codigoPais,
			final String codigoTerritorio, final String codigoZona,
			final String orden1, final String orden2, final String orden3,
			final String codigoCliente);

	/**
	 * Obtiene el status de existencia o no de la zona para un cliente.
	 * 
	 * @param codigoZona
	 * @param codigoCliente
	 * 
	 * @return 1 si la zona existe, 0 si no existe.
	 */
	public int[] getStatusZonaByCliente(final String codigoPais,
			final String codigoZona, final String codigoCliente);

	public LabelValue[] getSubaccesoByAcceso(String acceso);

	/**
	 * Obtiene una lista de Zonas en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de Regiones.
	 */
	public LabelValue[] getSubGerenciasByPaisMarcaCanal(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal);

	/**
	 * Obtiene una lista de Sub Gerencias en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoMarca
	 *            Codigo de marca.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @return Lista de Regiones.
	 */
	public LabelValue[] getSubGerenciaxPaisMarcaCanal(final String codigoPais,
			final String codigoMarca, final String codigoCanal);

	/**
	 * @param codLineaArmado
	 * @return Lista de Sub Lineas de Armado por Linea de Armado
	 */
	public LabelValue[] getSubLineaArmadoListar(String codLineaArmado);

	public LabelValue[] getSublineaByLinea(String linea);

	public LabelValue[] getSubProcesoByProceso(final String codProceso);

	/**
	 * @param oid
	 * @return
	 */
	public LabelValue[] getSubTiClienteList(String oid);

	/**
	 * @param oidTipoCliente
	 * @return
	 */
	public LabelValue[] getSubTipoClienteByOidTipoCliente(String oidTipoCliente);

	public LabelValue[] getSubTiposClientesPorPaisTipoCliente(
			final String codigoPais, final String codigoTipoCliente);

	public LabelValue[] getSubTiposClientesPorPaisTipoClientes(
			final String codigoPais, final ArrayList codigoTipoClientes);

	public LabelValue[] getSubTiposClientesPorPaisTipoClientesOID(
			String codigoPais, ArrayList codigoTipoClientes);

	public LabelValue[] getSubTiposClientesPorPaisTipoClientesCodigo(
			String codigoPais, ArrayList codigoTipoClientes);

	public LabelValue[] getTerritoriosByPaisMarcaCanalRegionZonaSeccion(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoRegion,
			final String codigoZona, final String codigoSeccion);

	public LabelValue[] getProgramasPorCampanaProceso(
			String codigoPais, String programa);

	/**
	 * Retorn a una lista de territorios en funcion a Pais, Marca, Canal y Zona
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoZona
	 * @return
	 */
	public LabelValue[] getTerritoriosByPaisMarcaCanalZona(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoZona);

	public LabelValue[] getTerritoriosByPeriodoRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoRegion, final String codigoZona,
			String condicionTodos);

	public LabelValue[] getTerritoriosMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final List codigoRegiones,
			final List codigoZonas, String condicionTodos);

	public LabelValue[] getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			final ArrayList codigoZonas, final ArrayList codigoSeccion,
			String condicionTodos);

	public LabelValue[] getTerritoriosTrasvaseEnActUniAdm(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoZona,
			String codigoSeccion, String ordinal);

	public LabelValue[] getTipoAsistencia();

	public LabelValue[] getTipoAsistenciaCapacitadas();

	/**
	 * @param oidSubTipoCliente
	 * @return
	 */
	public LabelValue[] getTipoClasificacionByOidSubTipoCliente(
			String oidSubTipoCliente);

	/**
	 * @param oid
	 * @return
	 */
	public LabelValue[] getTipoClasificacionByOidSubTipoClienteList(String oid);

	/**
	 * Devuelve Tipo de Curso (02:Costo, 01:Sin costo , 03:Mixto)
	 * 
	 * @author sbuchelli
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param codigoCurso
	 * @return Tipo de Curso (02:Costo, 01:Sin costo , 03:Mixto)
	 **/
	public String getTipoCursoCosto(String codigoPais, String codigoEmpresa,
			String codigoCurso);

	public LabelValue[] getTipoDespachos(String indicadorDespacho,
			String indicadorInvitacion, String indicadorRegalo);

	// sb
	public LabelValue[] getTipoEntidad();

	/* Mtodos AJAX para Educacin */
	public LabelValue[] getTipoIndicadorDespachos(final String tipoIndicador);

	/**
	 * @param tipoProducto
	 * @param tipoAtencion
	 * @return La Lista de Tipos de Operacin
	 */
	public LabelValue[] getTipoOperacionList(String tipoProducto,
			String tipoAtencion);

	/**
	 * Obtiene la lista de tipos de anaquel por Sublinea que tiene como funcin
	 * de distribucin una diferente a "Matching"
	 * 
	 * @param sublinea
	 * @return
	 */
	public List getTiposAnaquelAsignacion(String sublinea);

	public LabelValue[] getTiposClasificacionesByCodISOIdiomaTClienteSubTCliente(
			final String codigoIdiomaISO, final String codigoTipoCliente,
			final String codigoSubTipoCliente);

	public LabelValue[] getTiposClasificacionesByCriteriaMultiple(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente);

	public LabelValue[] getTiposClasificacionesByCriteriaMultipleOID(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente);
	
	public LabelValue[] getTiposClasificacionesByCriteriaMultipleOIDParamPais(
			String codigoPais,
			String codigoIdiomaISO, 
			String codigoTipoCliente,			
			ArrayList codigoSubTipoCliente,
			String indicadorTipoClasificacionPais);

	public LabelValue[] getTiposClasificacionesByCriteriaMultipleCodigo(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente);
	

	public LabelValue[] getTipoSol(final String codigoPais, final String periodo);

	public LabelValue[] getTiposOperaByOpera(final String codigoPais,
			final String codigoOperacion, String condicionTodos);

	/**
	 * @param codigoPais
	 * @param codigoOperacion
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getTiposOperaByOperaByReporte(final String codigoPais,
			final String codigoOperacion, String condicionTodos);

	/**
	 * Recupera los tipos de Operacion
	 * 
	 * @param codigoPais
	 * @param codigoOperacion
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getTiposOperaByOperaDes(final String codigoPais,
			final String codigoOperacion, String condicionTodos);

	/**
	 * Devuelve los tipos de operadoras por codigo operadora y descripcion de
	 * operadora
	 * 
	 * @param codigoPais
	 * @param codigoOperacion
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getTiposOperaByOperaDesList(final String codigoPais,
			final ArrayList codigoOperacion, String condicionTodos);

	public LabelValue[] getTiposOperaMultipleByOpera(final String codigoPais,
			ArrayList codigoRegiones, String condicionTodos);

	public LabelValue[] getUnidadesGeograficas(String oidPais,
			String codigoUnidadGeografica);

	/**
	 * @param codPais
	 *            , codMagnitud
	 * @return Lista de Unidades de Medida
	 */
	public LabelValue[] getUnidadesMedidaListar(String codPais,
			String codMagnitud);

	public LabelValue[] getValidacionesByDocumento(String codigoPais,
			String tipoDocumento);

	/**
	 * @param oidConcurso
	 * @return Valor 0 si concurso no tiene Premios, 1 en caso contrario.
	 */
	public String getValidaEntregaPremios(String oidConcurso);

	/**
	 * @param oidConcurso
	 * @return Cantidad de registros con premios
	 */
	public String getValidaExistePremios(String oidConcurso);

	/**
	 * @param codPais
	 * @param oidLineaArmado
	 * @param oidAAFP
	 * @param codRegion
	 * @param codZona
	 * @param codSeccion
	 * @return 1 si en caso exista una Unidad Administrativa por Linea y 0 si no
	 *         existe.
	 */
	public String getValidaExisteUnidadAdmxLinea(String codPais,
			String codLineaArmado, String oidAAFP, String codRegion,
			String codZona, String codSeccion);

	/**
	 * Valida el monto del logro
	 * 
	 * @param codigoTipoLogro
	 * @param montoLogro
	 * @return
	 */
	public LabelValue getValidaMontoLogro(String codigoTipoLogro,
			String montoLogro);

	/**
	 * Valida si existe patron
	 * 
	 * @param codigoPeriodo
	 * @param codigoDocumento
	 * @return
	 */
	public Integer getValidaPatron(String codigoPeriodo, String codigoDocumento);

	/**
	 * Valida el periodo ingresado buscando en la serg_perio_corpo
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public String getValidaPeriodo(String codigoPeriodo);

	/**
	 * Mtodo que valida si existe el Cod. Periodo en la tabla
	 * LET_PARAM_CONCU_LIDER. Si se devuelve mayor a 0, entonces existe el Cod.
	 * Periodo
	 * 
	 * @param codigoPeriodo
	 * @param codigoPais
	 * @param codigoConcurso
	 * @return
	 */
	public String getValidaPeriodoExisteLET(String codigoPeriodo,
			String codigoPais, String codigoConcurso);

	/**
	 * Valida que un Concurso y Campaa no se repitan el Mantenimiento Premios
	 * por Campaa
	 * 
	 * @param codigoPais
	 * @param codigoConcurso
	 * @param codigoPeriodo
	 * @return
	 */
	public String getValidaPremioCampaniaExiste(String codigoPais,
			String codigoConcurso, String codigoPeriodo);

	/**
	 * Valida que un Concurso y Campaa no se repitan el Mantenimiento Premios
	 * por Concurso
	 * 
	 * @param codigoPais
	 * @param codigoConcurso
	 * @param codigoPeriodo
	 * @return
	 */
	public String getValidaPremioConcursoExiste(String codigoPais,
			String codigoConcurso, String codigoPeriodo);

	/**
	 * Mtodo que valida si ha ejecutado el proceso para la misma Regin. Al
	 * cierre de Regin
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoRegion
	 * @return
	 */
	public String getValidaProcesoCierreRegionLET(String codigoPais,
			String codigoPeriodo, String codigoRegion);

	/**
	 * Se valida que la lider cumpla una condicin antes de ser asignada
	 * 
	 * @param codigoUA
	 * @param codigoLider
	 * @param condicion
	 * @return
	 */
	public String getValidarAsignarLider(String codigoUA, String codigoLider,
			String condicion);

	/**
	 * Validar si existen nulos de Documento Legal
	 * 
	 * @param tipoDocumento
	 * @param serie
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param subacceso
	 * @param canal
	 * @param acceso
	 * @return
	 */
	public String getValidarAsignarNulosDocLeg(String tipoDocumento,
			String serie, String fechaDesde, String fechaHasta,
			String subacceso, String canal, String acceso);

	/**
	 * @param codigoPais
	 * @param codigoPeriodoProceso
	 * @return La validacin de Codigo Proceso si es mayor o igual a Codigo
	 *         Proceso Actual
	 */
	public String getValidarCampanhiaProceso(String codigoPais,
			String codigoPeriodoProceso);

	/**
	 * @param oidProducto
	 * @return Un numero mayor a 0 que indica que faltan valores de
	 *         configuracion en el producto, 0 en el caso de tener valores.
	 */
	public String getValoresCubicaje(String[] cadItems);

	/**
	 * @param oidOlaxDia
	 * @return El Estado de OLA por Dia
	 */
	public String[] getValorEstadoOLADia(String oidOlaxDia);

	public String getValorNivel(String pais, String programa, String nivel,
			String periodo);

	/**
	 * Devuelve la cantidad de unidades de premios electivos por nivel
	 * 
	 * @param pais
	 * @param programa
	 * @param nivel
	 * @param periodo
	 * @return
	 */
	public String getValorNivelPremiosElectivos(String pais, String programa,
			String nivel, String periodo);

	/**
	 * 
	 * Evalua si el cliente esta en status retirado (1/0)
	 * 
	 * @param oidCliente
	 * @return
	 */
	public String getValorStatus(String oidCliente);

	public String getValorUnitarioByCodigoVenta(String codigoPeriodo,
			String codigoVenta);

	/**
	 * Devuelve la versin Activa de Asignacin Productos Anaquel
	 * 
	 * @param codMapaCD
	 * @param codPeriodo
	 * @param codMarca
	 * @param codCanal
	 * @return Versin Activa
	 */
	public String getVersionActiva(String codLineArmado, String codMapaCD,
			String codPeriodo, String codMarca, String codCanal);

	public LabelValue[] getVersionByMapa(String mapa, String codigoPeriodo);

	public LabelValue[] getVersionByMapaAnt(String mapa, String codigoPeriodo);

	/**
	 * Obtiene la lista de Versiones segun el periodo y el centro de
	 * distribucion
	 * 
	 * @param codCentro
	 * @param codMarca
	 * @param codCanal
	 * @param codPeriodo
	 * @return
	 */
	public LabelValue[] getVersionesbyPeriodoList(String codCentro,
			String codMarca, String codCanal, String codPeriodo);

	public LabelValue[] getVersionSinP(String mapa);

	/**
	 * Metodo que devuelve la lista de Zonas de arribo en base al tipo de
	 * documento
	 * 
	 * @param codigoPais
	 * @param tipoDocumento
	 * @return
	 */
	public LabelValue[] getZonaArriboSTOByTipoDocumento(String codigoPais,
			String tipoDocumento);

	public LabelValue[] getZonaByPaisEmpresaRegion(final String codigoPais,
			final String codigoEmpresa, final String codigoRegion);

	public LabelValue[] getZonaByRegion(final String codigoPais,
			final String codigoEmpresa, final String[] codigoRegion);

	public LabelValue[] getZonaByRegionSelected(final String codigoPais,
			final String codigoEmpresa, final String[] codigoRegion,
			final String flagTodos);

	/**
	 * Devuelve Todas las zonas activas del titular segun indicador
	 * 
	 * @param indicadorTitular
	 * @param codigoRegiones
	 * @return
	 */
	public LabelValueCDR[] getZonasAllDirectorio(String indicadorTitular,
			String[] codigoRegiones, String indice);

	/**
	 * Devuelve Todas las zonas activas del titular segun indicador para Asignar Cargos
	 * 
	 * @param indicadorTitular
	 * @param codigoRegiones
	 * @param indice
	 * @param cargo
	 * @return
	 */
	public LabelValueCDR[] getZonasAllDirectorioAsignarCargo(String codigoPais, String codigoConexionExterna, String indicadorTitular, String[] codigoRegiones, String indice, String cargo);

	public LabelValue[] getZonasByMultiplePeriodoBasCtrlFact(
			final String codigoPais, final String codigoPeriodo,
			final String[] codigoRegion, String condicionTodos);

	public LabelValue[] getZonasByPais(String codigoPais);

	/**
	 * Obtiene una lista de Zonas tanto activas como No Activas
	 * 
	 * @param codigoPais
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonasByPaisActivasNoActivas(final String codigoPais,
			final String codigoRegion);

	/**
	 * Obtiene una lista de Zonas en base a los parametros enviados.
	 * 
	 * @param codigoPais
	 *            Codigo de pais.
	 * 
	 * @param codigoCanal
	 *            Codigo de canal.
	 * 
	 * @param codigoRegion
	 *            Codigo de region.
	 * 
	 * @return Lista de Regiones.
	 */
	public LabelValue[] getZonasByPaisCanalRegion(final String codigoPais,
			final String codigoCanal, final String regionCanal);

	/**
	 * 
	 * @param codigoPais
	 * @param codigoCanal
	 * @param codigoMarca
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonasByPaisMarcaCanalRegion(final String codigoPais,
			final String codigoCanal, final String codigoMarca,
			final String codigoRegion);

	public LabelValue[] getZonasByPaisRegion(final String codigoPais,
			final String codigoRegion);

	/**
	 * Devuelve todas las zonas por pais y region seleccionada, activos e
	 * inactivos
	 * 
	 * @param codigoPais
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonasByPaisRegionLet(final String codigoPais,
			final String codigoRegion);

	public LabelValue[] getZonasByPaisSociedadEtapaDeudaPeriodoRegion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion);

	public LabelValue[] getZonasByPaisSociedadEtapaDeudaRegion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoRegion);

	public LabelValue[] getZonasByPeriodoBasCtrlFact(final String codigoPais,
			final String codigoPeriodo, final String codigoRegion,
			String condicionTodos);

	public LabelValue[] getZonasByPeriodoIntEviPerioRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoRegion, String condicionTodos);

	/**
	 * Devuelve las zonas activas del titular segun indicador
	 * 
	 * @param indicadorTitular
	 * @param codigoRegiones
	 * @return
	 */
	public LabelValueCDR[] getZonasDirectorio(String indicadorTitular,
			String[] codigoRegiones, String indice);

	/**
	 * Retorna las zonas que hay pro cerrar que aun no han sido programadas para
	 * la campanha d eproceso
	 * 
	 * @param codigoPais
	 * @param campanhaProceso
	 * @param codigoRegiones
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getZonasMultipleByCerrar(final String codigoPais,
			final String campanhaProceso, final String[] codigoRegiones,// final
																		// ArrayList
																		// codigoRegiones,
			String indicadorEdit, String condicionTodos);

	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones,// final
																		// ArrayList
																		// codigoRegiones,
			String condicionTodos);

	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionDetalle(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			String condicionTodos, final String tipoDetalle);

	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionOid(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			String condicionTodos);

	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionSubGerencia(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final String codigoSubGerencia, String condicionTodos);

	public LabelValue[] getZonasMultipleByPeriodoIntEviPerioRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoMarca, final String codigoCanal,
			final ArrayList codigoRegiones, String condicionTodos);

	public String mostrarCodigoVenta(String codigoPais);
	
	/**
	 * @param oidPais
	 * @param oidConsolidado
	 * @param oidSoli
	 * @param codListaPicado
	 * @param numCaja
	 * @param usuario
	 * @return
	 */
	public String updateEtiqueta(String oidPais, String oidConsolidado,
			String oidSoli, String codListaPicado, String numCaja,
			String usuario);

	/**
	 * Valida los territorios de las zonas a re-secuenciar
	 * 
	 * @param codigoZona
	 * @return
	 */
	public String validaCantidadTerritoriosSecuenciacion(String codigoZona);

	/**
	 * Valida que el codigo de consultora exista en el maestro de clientes
	 * 
	 * @param codigoCliente
	 * @return
	 */
	public String validaCodigoConsultora(String codigoCliente, String codigoPais);

	/**
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoCliente
	 * @param numLote
	 * @param codigoVenta
	 * @return Valida el codigo de venta
	 */
	public LabelPedidosValue validaCodigoVenta(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numLote,
			String codigoVenta);

	/**
	 * Valida que el cdigo de venta se valido en BBDD del programa LET
	 * 
	 * @param descripcionGeneral
	 * @return
	 */
	public String validaCodigoVentaLET(String descripcionGeneral,
			String codigoPeriodoDespacho, String indicadorConcursoCampania);

	/**
	 * Valida si existe la lista que esta activa para la facturacion
	 * 
	 * @param codigoCD
	 * @param codigoLinea
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoPeriodo
	 * @param codigoMapaCD
	 * @return
	 */
	public String validaExisteFacturadosAlarma(String codigoCD,
			String codigoLinea, String codigoMarca, String codigoCanal,
			String codigoPeriodo, String codigoMapaCD);

	/**
	 * VAlida si ya se a generado la estimacion para la campaa y linea
	 * seleccionadas
	 * 
	 * @param codLinea
	 * @param codPeriodo
	 * @param codMarca
	 * @param codCanal
	 * @return
	 */
	public String validaExisteGeneraEstimado(String codLinea,
			String codPeriodo, String codMarca, String codCanal);

	/**
	 * Valida si existe un Mapa de Anaquel por Defecto
	 * 
	 * @param codMapaZona
	 * @return
	 */
	public String validaExisteMapaAnaquelDefault(String codMapaZona);

	/**
	 * Valida que el periodo exista
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public String validaExistePeriodo(String codigoPeriodo);

	/**
	 * Valida el bloqueo de digitacion de pedidos
	 * 
	 * @param codigoCliente
	 * @return
	 */
	public String validarBloqueoDigitacionPedidos(String codigoCliente);

	/**
	 * Metodo que valida el codigo de cliente
	 * 
	 * @param oidPais
	 * @return
	 */
	public String validarCodigoCliente(String oidPais, String codigoCliente);

	/**
	 * Metodo que valida el codigo SAP
	 * 
	 * @param codigoPais
	 * @param codigoSAP
	 * @return
	 */
	public String validarCodigoSAP(String codigoPais, String codigoSAP);

	/**
	 * Valida el codigo de venta con el producto
	 * 
	 * @param descripcion
	 * @param codigoVenta
	 * @param codigoSAP
	 * @return
	 */
	public String validarCodigosVentaProducto(String descripcion,
			String codigoVenta, String codigoSAP, String numeroCruce);

	/**
	 * Valida el codigo de venta con el producto matriz
	 * 
	 * @param descripcion
	 * @param codigoVenta
	 * @param codigoSAP
	 * @param matriz
	 * @return
	 */
	public String validarCodigosVentaProductoMatriz(String descripcion,
			String codigoVenta, String codigoSAP, String matriz,
			String numeroCruce);

	public String validarCodigoVenta(String codigoPais, String codigoVenta,
			String periodo);

	public int ValidarCodigoVentaPeriodo(String codigoPais, String codigoVenta,
			String codigoPeriodo);

	/**
	 * Validamos que el Codigo de Venta debe cumplir con que existe en matriz,
	 * debe ser no digitable, no imprimible y debe ser Individual
	 * 
	 * @param oidPeriodo
	 * @param codigoVenta
	 * @return
	 */
	public String validarCodigoVentaRxP(String codigoPeriodo, String codigoVenta);

	/**
	 * Valida la existencia de un CUV en la Matriz de precios y/o premios
	 * 
	 * @param codigoVenta
	 * @param indicadorMatriz
	 * @param numeroBoleta
	 * @return
	 */
	public LabelValue validarCUVMatrizPreciosPremios(String codigoVenta,
			String indicadorMatriz, String numeroBoleta);

	/**
	 * Valida que los detalles a agregar no se encuentren en la cabecera
	 * 
	 * @param codigoPais
	 * @param codigoVenta
	 * @param periodo
	 * @param codcliente
	 * @param numlote
	 * @return
	 */
	public String validarDetalle(String codigoPais, String codigoVenta,
			String periodo, String codcliente, String numlote);

	/**
	 * Valida que las regiones seleccionadas no esten cerradas
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param regionesList
	 * @return
	 */
	public String validaRegionCerrada(String codigoPais, String codigoPeriodo,
			final ArrayList regionesList);

	/**
	 * valida que haya ingrsado consideracion, y que el mensaje transformado sea
	 * un xml valido
	 * 
	 * @return
	 */
	public String getValidarConsideracion(String xml, String tipoMensaje, List listConsideraciones, List listRestricciones);

	/**
	 * Valida si es que ya sido ingresado la lista
	 * 
	 * @param codigoConsideracion
	 * @return
	 */
	public String getValidarConsRest(String codigoConsideracion,
			String tipoConsideracion, List listConsideracion, List listRestriccion);

	/**
	 * valida que hay ingresado por lo menos un mensaje al patron no eliminado
	 * 
	 * @return
	 */
	public String getValidarMensajesPatron(List list);

	/**
	 * Obtiene el codigo y el nombre de un proceso de FDV en base al pais, ao y
	 * el periodo
	 * 
	 * @param codigoPais
	 * @param anyoProceso
	 * @param periodoAnyoProceso
	 * @return
	 */
	public LabelValue[] getProcesosFDVByPaisAnyoPeriodo(
			final String codigoPais, final String anyoProceso,
			final String periodoAnyoProceso);

	/**
	 * Obtiene las regiones de un proceso de FDV
	 * 
	 * @param codigoProceso
	 * @return
	 */
	public LabelValue[] getRegionesFDVByProceso(final String codigoProceso);

	/**
	 * Obtiene las zonas de una region de un proceso de FDV
	 * 
	 * @param codigoRegion
	 * @param codigoProceso
	 * @return
	 */
	public LabelValue[] getZonasFDVByProcesoRegion(final String codigoRegion,
			final String codigoProceso);

	/* INI SA PER-SICC-2011-0803 */
	/**
	 * Obtiene las regiones que pertenecen a una subgerencia de Ventas
	 * 
	 * @param oidSubGerencia
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getRegionesByOidSubGerencia(String oidSubGerencia,
			String condicionTodos);

	/**
	 * Obtiene las zonas que pertenece a una region
	 * 
	 * @param oidRegion
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getZonasByMultipleOidRegiones(ArrayList oidRegion,
			String condicionTodos);

	/**
	 * Obtiene datos relacionados a un tipo de Concurso como: base de Calculo,
	 * tipo de programa, concurso ivr, plantilla, etc.
	 * 
	 * @param oidTipoConcurso
	 * @return
	 */
	public String getDatosTipoConcurso(String oidTipoConcurso);

	/* FIN SA PER-SICC-2011-0803 */

	/**
	 * Obtiene el indicador express de un codigo de operacion
	 * 
	 * @param codOper
	 * @return
	 */
	public String getIndExpress(String codOper);

	/**
	 * Obtiene la lista de regiones disponibles para el programa nuevas
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @return
	 */
	public LabelValue[] getloadRegionesDisponibles(String codigoPais,
			String codigoPeriodo, String periodoInicial, String periodoFinal,
			String regionesSeleccionadas, String codigoPrograma);

	/**
	 * Obtiene la lista de zonas disponibles para el programa nuevas
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param oidRegion
	 * @return
	 */
	public LabelValue[] getZonasDisponiblesRegion(String codigoPais,
			String codigoPeriodo, ArrayList oidRegion, String listaZonas,
			String periodoInicial, String periodoFinal);

	/* INI SA PER-SiCC-2012-0459 */
	/**
	 * Obtiene la lista de ciudades relacionados a una region
	 * 
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getCiudadesByRegion(String codigoRegion);

	/* FIN SA PER-SiCC-2012-0459 */

	/**
	 * Obtiene la lista de ciudades relacionados a una region
	 * 
	 * @param codigoRegion
	 * @return
	 */
	public Integer validaInsertarUnidadesAdministrativas(String codigoPais,
			String codigoPeriodo, ArrayList oidRegion, String listaZonas,
			String periodoInicial, String periodoFinal);

	public Integer getSaldoProducto(String numeroPedido, String codigoVenta,
			String codigoPais);

	public LabelValue[] getTiposOperaMultipleByOperaxCodigoOid(
			final String codigoPais, ArrayList codigoRegiones,
			String condicionTodos);

	/**
	 * retorna la lista de mensajes para convertilos a html
	 * 
	 * @param id
	 * @param campanha
	 * @return
	 */
	public String[] getObtenerMsgHtml(String id, String campanha, StringBuffer msg, List list);

	/**
	 * valida si los mensajes tan parseads correctamente
	 * 
	 * @param msgHtml
	 * @return
	 */
	public String validaMsgHtml(String[] msgHtml);

	/* Ini PER-SiCC-2012-0558 */
	public LabelValue getPeriodoCDR(String codigo, String indicadorExpress);

	public LabelValue getCodigoOperacionCorrecto(String datoCompuesto,
			String codigoMotivo, String indicadorExpress, String codigoPais,
			String numeroPedido, String varOidPeriCDR, String usuario, String indicadorExcluye);

	public String getValExcepProduGanador(String idPeriodoCDR,
			String codigoVenta, String codigoOperacionSic,
			String tipoOperacionSic, String oidSoliPosi, String codigoMotivo, String codigoPais, String valorParametro, String indicadorExcluye);

	/**
	 * Valida Codigo Producto Desea en CDR
	 * @param idPeriodoCDR
	 * @param codigoVenta
	 * @param codigoOperacionSic
	 * @param tipoOperacionSic
	 * @param oidSoliPosi
	 * @param codigoMotivo
	 * @return
	 */
	public String getValExcepProduDesea(String idPeriodoCDR,
			String codigoVenta, String codigoOperacionSic,
			String tipoOperacionSic, String oidSoliPosi, String codigoMotivo, String indicadorExcluye, String numeroBoletaDespacho);
	

	public String getValUnidadReclamo(String codigopais, String codoCliente,
			String idSoliPosi, String unidadesReclamar, String codOperSICC,
			String tipoOperSICC, String indicadorExcluye);

	/**
	 * Valida Unidades Desea en CDR.
	 *
	 * @param codigopais
	 * @param codoCliente
	 * @param idSoliPosi
	 * @param unidadesDesea
	 * @param codOperSICC
	 * @param tipoOperSICC
	 * @param indicadorExcluye
	 * @return String
	 */
	public String getValUnidadDesea(String codigopais, String codoCliente,
			String idSoliPosi, String unidadesDesea, String codOperSICC,
			String tipoOperSICC, String indicadorExcluye);

	/**
	 * Retorna Datos de la consultora apartir del documento de identidad
	 * 
	 * @param codigoPais
	 * @param numeroDocumento
	 * @param tipoDocumento
	 * @return
	 */
	public DatosConsultoraBasico[] getCabeceraConsultoraByDocIdentidad(
			String codigoPais, String numeroDocumento, String tipoDocumento,
			String codigoConsultora);

	/**
	 * Retorna las sucuarsales del banco para el pago de Cheques
	 * 
	 * @param codigoBanco
	 * @return
	 */
	public LabelValue[] getSucursalBancoCheque(String codigoBanco);
	/* Fin PER-SiCC-2012-0558 */
	
	/* INI SA VEN-SiCC-2012-0101 */
	/**
	 * Retorna la lista de Premios Faltantes
	 * @return
	 */
	public LabelValue[] getListPremiosFaltantes(String numeroConcurso);

	/**
	 * Retorna la lista de Premios Reemplazos Vigentes
	 * @return
	 */
	public LabelValue[] getListPremiosReemplazos(String numeroConcurso);
	/* FIN SA VEN-SiCC-2012-0101 */
	
	public LabelValue[] getSubTipoClienteMultipleByOidTipoCliente(ArrayList oidTipoCliente, String condicionTodos);
	
	public LabelValue[] getTipoClasificacionMultipleByOidSubTipoCliente(ArrayList oidSubTipoCliente, String condicionTodos);
	
	public LabelValue[] getClasificacionMultipleByOidTipoClasificacion(ArrayList oidTipoClasificacion, String condicionTodos);
	
	public String getValidarExistenciaCuv(String codigoVenta, String codigoCampanha);

	/* INI SA PER-SiCC-2012-0900 */
	/**
	 * Obtener las zonas que estan programadas a cerrarse para una determinada region
	 * 
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonasACerrar(String codigoPeriodo, String fechaFacturacion, String codigoRegion);
	/* FIN SA PER-SiCC-2012-0900 */
	
	public String getValidacionDocumentoIdentidad(String codigoPais,
			String tipoDocumento, String numeroDocumento);

	/* INI PER-SiCC-2012-1021 */
	/**
	 * Obtener datos relacionados a una actividad MAV
	 * 
	 * @param oidActividad
	 * @return
	 */
	public String getDatosActividad(String oidActividad);
	
	/**
	 * Obtener los tipos de ofertas relacionadas a una actividad MAV
	 * 
	 * @param oidActividad
	 * @return
	 */
	public LabelValue[] getTiposOfertaByActividad(String oidActividad);
	
	/**
	 * Valida si es que ya sido ingresado la lista
	 * 
	 * @param codigoConsideracion
	 * @return
	 */
	public String getValidarConsRestMAV(String codigoConsideracion, String tipoConsideracion, List listConsideracion, List listRestriccion);
	/* FIN SA PER-SiCC-2012-1021 */
	
	public LabelValue[] getComisionesByTipo(String codigoTipoComision);
	
	public String getUltimoCodigoZona(String codigoPais, String codigoRegion);

	public String getMensajeValidaPedido(String numeroPedido);
		
	/* INI DA PER-SiCC-2012-0970 */
	/**
	 * Retorna los tipos de operaciones segn la operacin seleccionada
	 * @param operaciones
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getTiposOperacionesByOperacion(final String[] operaciones);
	/* FIN DA PER-SiCC-2012-0970 */

	public LabelValue[] getProvinciasPorDepartamento(String codigoDepartamento, String tipoCombo);
	
	public LabelValue[] getDistritosPorProvincia(String codigoProvincia, String codigoDepartamento, String tipoCombo);
	
	public LabelValue[] getCentrosPobladosPorDistrito(String codigoDistrito, String codigoProvincia, String codigoDepartamento, String tipoCombo);

	public String obtenerDatosConsultora(String codigoPais, String codigoConexionExterna, String codigoConsultora);
	
	public String obtenerDatosConsultoraAsignarCargo(String codigoPais, String codigoConexionExterna, String codigoConsultora);
	
	public String getVerificarCargoTitular(String codigoCargo);
	
	public String[] obtenerDatosConsultoraReemplazo(String codigoPais, String codigoConexionExterna, String codigoConsultora);

	public String getValidacionConsultoraAptoLicencia(String consultora);

/* INI SA PER-SiCC-2012-1065 */
	/**
	 * Retorna las consideraciones
	 * 
	 * @param indicadorDirigido
	 * @return
	 */
	public LabelValue[] getConsideraciones(String indicadorDirigido, String indicadorUnidad);
	
	/**
	 * Retorna las restricciones
	 * 
	 * @param indicadorDirigido
	 * @return
	 */
	public LabelValue[] getRestricciones(String indicadorDirigido, String indicadorUnidad);
	/* FIN SA PER-SiCC-2012-1065 */

	/* INI DA PER-SiCC-2012-1148 */
	/**
	 * Retorna las validaciones segun el tipo de documento
	 * @param params
	 * @return
	 */
	public LabelValue[] getValidacionesExcepcionByDocumento(String codigoPais,
			String tipoDocumento);
	/* FIN DA PER-SiCC-2012-1148 */

	public LabelValue[] getBasesComision(String codigoTipoComisionista);
	
	public String getCierreBrByNumeroBR(String numeroBR);
	
	public String insertProcesoBoletaTemporal(String numeroBoletaRecojo,
			String numeroRecojo, String codigoCliente, String flagProceso, List listaBoletas);
	
	public String deleteBoletaTemporal(String numeroBoletaRecojo,
			String numeroRecojo, String codigoCliente, List listaBoletas);	
	
	public String validaComisionObjetivoVenta(String codigoPeriodo,String codigoComision);
	
	public String validaProductosKitSegundoPedido(String codigoPais, String codigoPrograma, String codigoPeriodo, String codigoVenta);
	
public String validaComisionCalculada(String codigoPais, String codigoPeriodo,String codigoComision);
	
	public LabelValue[] getZonasNoAsignadas( final String[] codigoRegiones);
	
	public LabelValue[] getZonasGrupo( final String[] codigoGruposZona);
	
	public String deleteTablaTemporal();
	
	public String validarDesvinculo(String pais, String codigoRegion, String codigoZona, String codigoSeccion, String tipo, String codigoCliente,String indicadorLider);
	
	public LabelValue[] getClasificacionComision(final String oidTipoClasificacion);
	
	public String ofertaDevolucion(String oidsoliPosi, String cantidad);//cdr
	
	public String getOfertaMensaje();//cdr

	public String validarProductosOferta(
			String codigoPais,
			String codigoConsejera,
			String cantidades,
			String porcentajeDevolucionConsultora, 
			String montoDevolucionActual,
			String montoDevolucion,
			String montoPedido,
			String oidPeriodoCDR,
			String codigoOperacionSICC,
			String tipoOperacionSICC,
			String indCamVali, 
			String codigoMotivo);
	
	/*public String validarProductosOferta(String codigoPais,
			String codigoConsejera, String cantidades,
			String porcentajeDevolucionConsultora,
			String montoDevolucionActual, String montoDevolucion,
			String montoPedido, String oidPeriodoCDR,
			String codigoOperacionSICC, String tipoOperacionSICC,
			String indCamVali, String codigoMotivo,
			List mantenimientoRecDigitacionCdrListaOfertas,
			Map mantenimientoRecDigitacionCdrOfertaParametro);*/
	
	
         public String validarAsignarLideres(String codigoPais,String codigoMarca, String codigoCanal, String codigoZona);
	
	public String validarDocumentoIdentidadSTO(String codigoPais, String tipoDocumento, String numeroDocumento);
	
	public String validarCUVFaltanteAnunciado(String codigoVenta, String codigoPeriodo, String codigoCliente);
	

	/**
	 * Obtiene el monto de una oferta compuesta; devuleve cero si no es una compuesta
	 * @param codigoVenta
	 * @param codigoPeriodo
	 * @return
	 */
	public String validarCUVCompuesta(String codigoVenta, String codigoPeriodo);
	
	public String validarCUVLimiteVenta(String codigoVenta, String codigoPeriodo, String codigoCliente, String numeroUnidades);
	
	public String getMontoMinimoConsultora(String codigoCliente);
	
	/**
	 * @param fechaProceso
	 * @return
	 */
	public String getMensajeArchivosBoletasVenta(String listaArchivos, String fechaProceso);
	
	/**
	 * @param fechaProceso
	 * @return
	 */
	public String getMensajeArchivosNotasCredito(String listaArchivos, String fechaProceso);
	
	
	/**
	 * Valida que exista el Codigo de Venta en la matriz
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoCUV
	 * @return
	 */
	public LabelValueCUV getValidarCodigoVenta(String codigoPais, String codigoPeriodo, String codigoCUV);
	
	/**
	 * @param codigoPais
	 * @param codigoActividad
	 * @return
	 */
	public String getCodigoActividadExistente(final String codigoPais,final String codigoActividad);
	
	/**
	 * Devuelve Lista de Consideraciones de Mensajes
	 * @param codigoPais
	 * @param tipoMensaje
	 * @return
	 */
	public LabelValue[] getConsideracionMensajes(final String codigoPais, final String tipoMensaje);
	
	/**
	 * @param oidGruposZona
	 * @param zonaReferencia
	 * @return
	 */
	public LabelValue[] getZonasGrupoRegenerar(String oidGruposZona, String zonaReferencia);

	/**
	 * Obtiene la lista de secciones, pero con el caso particular que en el campo codigo
	 * va concatenado codigo zona + codigo seccion
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoRegion
	 * @param codigoZona
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getSeccionMultiple2ByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos);

	/**
	 * Obtiene la lista de territorios, pero con el caso particular que en el campo filtro
	 * x seccion, va contatenado codigo zona + codigo seccion
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param condigoCanal
	 * @param codigoRegiones
	 * @param codigoZonas
	 * @param codigoSeccion
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getTerritoriosMultiple2ByPaisMarcaCanalRegionZonaSeccion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			final ArrayList codigoZonas, final ArrayList codigoSeccion,
			String condicionTodos);

	/**
	 * @param codigoPais
	 * @param oidCrono
	 * @param oidGrupoZona
	 * @param diasDesplazamiento
	 * @return
	 */
	public String getActuaFechaFase1(String codigoPais,
			String oidCrono,  String oidGrupoZona, String diasDesplazamiento);

	/**
	 * @param codigoPais
	 * @param oidCrono
	 * @param diasDesplazamiento
	 * @return
	 */
	public String getActuaFechaFase2(String codigoPais,String oidCrono,String oidGrupoZona, String diasDesplazamiento);
	
	/**
	 * @param codigoTipoMav
	 * @return
	 */
	public LabelValue[] getActividadByTipoMAV(String codigoTipoMav);
	
	/**
	 * @param codigoActividad
	 * @param codigoTipoMav
	 * @return
	 */
	public LabelValue[] getTipoOfertaByTipoMAVActividad(String codigoActividad, String codigoTipoMav);
	
	/**
	 * Corre las validaciones del registro de una nueva meta
	 * @param codigoPais
	 * @param codigoConsultora
	 * @return
	 */
	public String getValidacionRegistroMeta(String codigoPais, String codigoConsultora);
	
	/**
	 * Obtiene el periodo de ingreso de la consultora
	 * @param codigoPais
	 * @param codigoConsultora
	 * @return
	 */
	public String getPeriodoIngresoConsultora(String codigoPais, String codigoConsultora);
	
	/**
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @return
	 */
	public String getCronogramaFase1Existente(String codigoPais,String codigoPeriodo );

	/**
	 * Retorna la lista de premios de Concursos Faltantes de Premios
	 * 
	 * @param oidConcurso
	 * @param oidPeriodo
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getPremiosFaltante(String[] oidConcurso, String oidPeriodo, String condicionTodos);
	
	/**
	 * Retorna la lista de zonas para Concursos Faltantes de Premios y regiones
	 * 
	 * @param oidRegion
	 * @param oidPeriodo
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getZonasFaltante(String[] oidRegion, String oidPeriodo, String condicionTodos);

	/**
	 * Devuelve Todas las zonas activas de la region en el Historico
	 * 
	 * @param codigoRegiones
	 * @return
	 */
	public LabelValueCDR[] getZonasAllDirectorioActivas(String codigoPais, String codigoConexionExterna, String[] codigoRegiones, String indice);
	
	/**
	 * @param fecha
	 * @param indNoLaborable
	 * @param indTransporte
	 * @param codigoPais
	 * @param usuario
	 */
	public void updateIndCalendario(String anhio,String codigoActividad, String fecha, String indNoLaborable, String indTransporte, String codigoPais, String usuario);
	
	/**
	 * @param fecha
	 * @param anhio
	 * @return
	 */
	public String getFeriadoValidoAnhio(String fecha,String anhio, String codigoActividad);
	
	/**
	 * Obtiene lista de Concursos Faltantes por Marca y/o Canal
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param condicionTodos
	 * @param baseCalculo
	 * @return
	 */
	public LabelValue[] getConcursosFaltantesByPaisMarcaCanalDetalle(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos,
			String baseCalculo);
	
	
	/**
	 * Se valida que la lider cumpla una condicin antes de ser asignada
	 *
	 * @param codigoUA
	 * @param codigoLider
	 * @param codigoPais
	 * @return
	 */
	public String getValidarAsignarLiderIndicadorPrograma(String codigoUA, String codigoLider, String codigoPais);
	
	/**
	 * Obtiene lista de Consultoras por Reporte
	 * @param codigoReporte
	 * @return
	 */
	public LabelValue[] getEstadosConsultoraByReporte(final String codigoReporte);
	
	/**
	 * @author Sebastian Guerra
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonasRegionPEJ(String codigoRegion);
	
	/**
	 * @author Sebastian Guerra
	 * @param codigoZona
	 * @return
	 */
	public LabelValue[] getSeccionesZonaPEJ(String codigoZona);

	
	/**
	 * Obtiene Zonas en base a la Region Seleccionada, puede colcoar Todos devuelve todas las Zonas
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonasRegionPEJTodos(String codigoRegion, String codigoPeriodo);
	
	
	/**
	 * Obtiene Secciones en base a la Zona Seleccionada, puede colcoar Todos devuelve todas las Secciones
	 * @param codigoRegion
	 * @param codigoZona
	 * @return
	 */
	public LabelValue[] getSeccionesZonaPEJTodos(String codigoRegion, String codigoZona);
	
	/**
	 * Valida si es que la Lider tiene Correo
	 * @param codigoLider
	 * @param codigoPais
	 * @return
	 */
	public String getValidarCorreoLider(String codigoLider, String codigoPais);
	
	/**
	 * Valida que exista un Tramo y que la campaa seleccionada no este cerrada
	 * @param codigoPeriodo
	 * @param tipoCarga
	 * @param codigoPais
	 * @return
	 */
	public String validaProgramaTramoCampanaCerrada(String codigoPeriodo, String tipoCarga, String codigoPais);
	
	/**
	 * Obtiene la longitud por defecto del tipo de documento.
	 * @param oidPais
	 * @return
	 */
	public String getLongitudDefaultTipoDocumento(String oidPais);
	
	/**
	 * Obtiene el nombre de consultora de acuerdo al Codigo o Documento de la consultora
	 * @param codigoPais
	 * @param codigoCliente
	 * @param oidIdioma
	 * @param numeroDocumentoIdentidad
	 * @param indicadorValidaDocIdentidad
	 * @return
	 */
	public String getNombreConsultoraOrdenCompraSTO(String codigoPais, String codigoCliente,
			String oidIdioma, String numeroDocumentoIdentidad, String indicadorValidaDocIdentidad);
	
	/**
	 * Retorna diferencia de periodos de un determinado rango
	 * @param codigoPais
	 * @param periodoInicio
	 * @param periodoFin
	 * @return
	 */
	public String getDiferenciaPeriodos(String codigoPais, String periodoInicio, String periodoFin);
	
	/**
	 * @param codigoCliente
	 * @return
	 */
	public String getCodigoNumeroTipoDocumentoConsultoraPorCodigo(String codigoCliente);
	
	/**
	 * @param numeroDocumentoIdentidad
	 * @return
	 */
	public String getCodigoNumeroTipoDocumentoConsultoraPorDocumento(String numeroDocumentoIdentidad);
	
	
	
	/**
	 * Devuelve Lista de Regiones quitando las regiones Seleccionadas del parametro
	 * @param codigoPais
	 * @param regionesSeleccionadas
	 * @return
	 */
	public LabelValue[] getloadRegionesDisponibles2(String codigoPais, String regionesSeleccionadas);
	
	
	/**
	 * Devuelve Lista de Zonas quitando las regiones Seleccionadas del parametro
	 * @param codigoPais
	 * @param codigoRegiones
	 * @param zonasSeleccionadas
	 * @param condicionTodos
	 * @return
	 */
	public LabelValue[] getloadZonasDisponibles2(final String codigoPais, final String[] codigoRegiones, 
			final String zonasSeleccionadas,
			String condicionTodos) ;
	
	/**
	 * Obtiene lafecha y el periodo de la campaa activa
	 */
	public String getPeriodoFechaAndCampanyaActivaCad();
	
	/**
	 * @param oidBanco
	 * @return
	 */
	public LabelValue[]  getTipoOrigenBanco(String oidBanco);
	
	/**
	 * Devuelve el Promedio de Venta de una consultora de acuerdo a su numero de pedido
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoRegion
	 * @param codigoCliente
	 * @param numeroPedido
	 * @return
	 */
	public String getPromedioVenta(String codigoPais, String codigoMarca, String codigoCanal, String codigoRegion, String codigoCliente, String numeroPedido);
	
	/**
	 * Valida que exista un Tramo y que la campaa seleccionada no este cerrada
	 * @param codigoPais
	 * @return
	 */
	public String getParametroMostrarFechas(String codigoPais);
	
	/**
	 * Verifica si se ha ingresado cupon para la consultora
	 * @param criteria
	 * @return
	 */ 
	public String getPedidoCuponExistenteByCriteria(final String codigoPais,
			final String periodo, final String codigoConsultora,
			final String fechaFacturacion, final String valorPagado);
	
	public String buscarCUV(String codigoCUV, String codigoPeriodo);
	
	public String getExisteZonaUA(String codigoZona);
	
	/**
	 * Devuelve el Periodo +n o -n consiguiente.
	 * @param criteria
	 * @return
	 */ 
	public String getPeriodoNSiguiente(String codigoPais, String codigoPeriodo, String numeroPeriodo);
	

	/**
	 * Devuelve el periodo(la campania) dada una fecha.
	 * @param criteria
	 * @return
	 */ 
	public String getPeriodoByFecha(String codigoPais, String codigoConexionExterna, String fecha);
	
	/**
	 * Obtiene la estructura geopolitica por Unidad Administrativa
	 * @param codigoPais
	 * @param unidadAdmin
	 * @return
	 */
	public String getEstructGeopoByUA(String codigoPais,String unidadAdmin);
	
	/**
	 * Obtienen la vigencia del programa por el nivel
	 * @param codigoPais
	 * @param codigoPrograma
	 * @param codigoNivel
	 * @return
	 */
	public String getVigenciaCuponPorNivel(String codigoPais, String codigoPrograma, String codigoNivel);
	
	/**
	 * Obtiene la fecha calculada de Facturacion o ReFacturacion. codigoActiv: FA|RF
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoActiv
	 * @param codigoZona
	 */
	public String getFechaCronoFase2(String codigoPais, String codigoPeriodo, String codigoActiv, String codigoZona);
	

	/**
	 * Obtienen todos los tramos
	 * @param codigoPais
	 * @param codigoPrograma
	 * @return
	 */
	public LabelValue[] getTramos(String codigoPais, String codigoPrograma);
	
	/**
	 * Obtiene Base de Comision en base al codigo de comision ingresado como parametro
	 * @param codigoComision
	 * @return
	 */
	public String getCodigoBaseComision(String codigoComision);
	
	/**
	 * Valida la fecha de ingreso por tipo de cargo (futuros).
	 * @param tipoCargo,codigoPais,fechaIngreso,codigoRegion,codigoZona,conExterna
	 * @return
	 */
	 public String getValidarFechaIngreso(String tipoCargo,String codigoPais, String fechaIngreso,String codigoRegion,String codigoZona,String conExterna);
	
	
	 /**
		 * Valida la fecha de ingreso por tipo de cargo (futuros).
		 * @param codigoPais,fechaIngresoInicio,fechaIngresoFin,codigoRegion,codigoZona,conExterna
		 * @return
     */
	 public String getValidarCruceFechaGeren(String codigoPais, String fechaIngresoInicio,String fechaIngresoFin,String codigoRegion,String codigoZona,String tipoCargo,String tipoOperacion,String conExterna);
			
	 /**
		 * Carga los cargos.
		 * @param codigoPais,codigoConexionExterna,codigoConsultora
		 * @return
       */
	 public LabelValue[] getDataCargo(String codigoPais, String codigoConexionExterna, String codigoConsultora);
		
	 /**
	  * Obtiene la lista de cargos en base al flag de futuras.
	  * 
	  * @param flagFuturas
	  * @return
	  */
	 public LabelValue[] getCargosByFlagFuturas(String flagFuturas);
		
	 
	 /**
	  * Obtiene rol y perfil dado el codigo del cargo.
	  * 
	  * @param codigoCargo
	  * @return
	  */
	 public String getRolPerfil(String codigoCargo);
	 
	 /**
	  * Obtiene los datos del cliente (FOX Y SSICC)
	  * 
	  * @param codigoPais
	  * @param codigoConsultora
	  * @param codigoConexionExterna
	  * @return
	  */
	 public String[] getConsultoraByCriteria2(String codigoPais,
				String codigoConsultora, String codigoConexionExterna);
	 
	 /**
	  * Obtiene la descripcion de un mensaje
	  * 
	 * @param codigoPais
	 * @param codigoMensaje
	 * @return
	 */
	public String getDescripcionMensaje(String codigoPais, String codigoMensaje);
	 
	/**
	  * Obtiene la lista de tipo de desempeo.
	  * @return
	  */
	 public LabelValue[] getTipoDesempenio();
	
	 
	 /**
	 * Devuelve Lista de Regiones a Cerrar
	 * @param codigoPais
	 * @return
	 */
	public LabelValue[] getRegionesACerrar(String codigoPais);
	
	public LabelValue[] getTipoGanancia(String codigo);
	
	public LabelValue[] getTipoPago(String codigo);
	
	/**
	 * Devuelve el resultado de las validaciones por regularizacion
	 * @param codigoCliente
	 * @param codigoUsuario
	 * @return
	 */
	public Integer getValidarRegularizacion(String codigoCliente, String codigoUsuario);
	
	/**
	 * Actualiza la regularizacin del cliente
	 * @param codigoCliente
	 * @param oidBloqueo
	 */
	public String updateRegularizacion(String codigoCliente, Integer oidBloqueo);
	
	/**
	 * Valida si el numero de ruc cumple con el modulo 11
	 * @param codigoCliente
	 * @param codigoUsuario
	 * @return
	 */
	public Integer validarNumeroRucModulo11(String numeroDocumentoIdentidad);
	
	public LabelValue[] getClasificacionesByCriteriaMultipleOID2(String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion);

	public String getExisteOidDetaOferta(String codigoCUV, String codigoPeriodo);

	
	/**
	 * Devuelve datos del Producto Ingresado en Recepcion de CDRs
	 * @param codigoSAP
	 * @return
	 */
	public ProductoAgregacion getDevuelveProductoRecepcionCDR (String codigoSAP);
	
	/**
	 * Verifica la validacion coorespondiente a la Fecha de Facturacion para la Interfaz DATAMART
	 * @param codigpPeriodo
	 * @return 
	 */
	public String getValidarDATFechaFacturacion(String codigpPeriodo);
	
	/**
	 * Devuelve lista  de tipos de anulacion
	 * @param motivo
	 * @return
	 */
	public LabelValue[] getTiposAnulacionByMotivo(final String motivo);
	
	public LabelValue[] getTiposClasificacionesByCriteriaMultipleOID2(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente);
	
	/**
	 * Ejecuta el proceso de anulacin masiva de boletas de recojo
	 * @param usuarioLogin
	 * @return
	 */
	public Integer executeProcesoAnulacionBR(String usuario,
			List listTablaTemporal, Integer totalIncorrectas, 
			String numeroLote);
	
	public LabelValue[] getRegionByTipoGrupoRegion(String codigoPais, String codigoTipoGrupoRegion);

	
	/**
	 * Devuelve Lista de Tipo de Bonos Lanzamiento para Programa LEC
	 * @param codigoPais
	 * @param regionesSeleccionadas
	 * @return
	 */
	public LabelValue[] getLoadTipoBonoLanzamiento(String codigoPais, String codigoTipoMedicion);

	/**
	 * Obtiene la lista de periodos que envuelven a fecha de facturacion
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @return
	 */
	public LabelValue[] getPeriodosDefaultByPMCF(String codigoPais, String codigoMarca, String codigoCanal);
	
	/**
	 * @param codigoPais
	 * @param codigoPeriodoReferencia
	 * @return la validación de codigo de referencia si es mayor o igual a codigo proceso actual activo
	 */
	public String getValidarCampanhiaActiva(String codigoPais, String codigoPeriodoReferencia);
	
	/**
	 * Controla la concurrencia del envío a Gp3 con las validaciones
	 * @return
	 */
	public String getValidarEnvioValidacionesGP3(String codigoPais);
	
	/**
	 * @param primerNombre
	 * @param segundoNombre
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param fechaNacimiento
	 * @return
	 */
	public String getGeneraRFCFiscal(String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento);

	/**
	 * Evalua si la palabra (Nombre/Apellido) es reservada
	 * @param palabra
	 * @return
	 */
	public boolean esReservada(String palabra);
	/**
	 * Evalua si la palabra generada es altisonante
	 * @param palabra
	 * @return
	 */
	public boolean esAltisonante(String palabra);
	/**
	 * Evalua si la palabra (Nombre/Apellido) es compuesta
	 * @param palabra
	 * @return
	 */
	public boolean esCompuesto(String palabra);
	
	/**
	 * Devuelve la descripcion del programa
	 * @param oidTipoConcurso
	 * @return
	 */
	 public String getDescripcionProgramaAdicionar(String oidTipoConcurso);
	 
	/**
	 * Valida si ya se encuentra registrado el número de facturación
	 * @param oidPais
	 * @param oidTipoDocumento
	 * @param oidSociedad
	 * @return
	 */
	public String getValidarNumerosFacturacion(String oidPais, String oidTipoDocumento, String oidSociedad);
	
	/**
	 * Inserta los datos del número de facturación capturados en el popup
	 * @param oidDocumentoSubacceso
	 * @param oidSociedad
	 * @param oidTipoDocumento
	 * @param numeroInterno
	 * @param limiteNumero
	 * @param annio
	 * @param serieInterno
	 * @param oidPais
	 * @param numeroAutorizacion
	 * @param llave
	 * @param fechaFin
	 * @param observaciones
	 * @param usuario
	 * @param valida
	 * @return
	 */
	public String insertNumerosFacturacion(String oidDocumentoSubacceso, String oidSociedad, String oidTipoDocumento, 
																     String numeroInterno, String limiteNumero, String annio, String serieInterno, 
																     String oidPais, String numeroAutorizacion, String llave, String fechaFin, 
																     String observaciones, String usuario, String valida);

	/**
	 * Controla la concurrencia del envío a Gp3 con las validaciones STO
	 * @param codigoPais
	 * @return
	 */
	public String getValidarEnvioValidacionesSTOGP3(String codigoPais);

	/**
	 * Obtiene el nombre del lider atravez del codigo
	 * @param codigoLider
	 * @return
	 */
	public String getNombreLider(String codigoLider);
	
	/**
	 * Devuelve estado de la tarjeta de pago
	 * @param codigoLider
	 * @return
	 */
	public String validaLiderTarjetaAsociada(String codigoLider);
	
	/**
	 * Devuelve codigo de la tarjeta de pago
	 * @param numeroTarjeta
	 * @return
	 */
	public String obtenerTarjetaPago(String numeroTarjeta);
	
	/**
	 * Valida tarjeta pago asociada
	 * @param numeroTarjeta
	 * @return
	 */
	public String validaTarjetaPagoAsociada(String numeroTarjeta) ;
	
	/**
	 * Obtener los tipos de ofertas relacionadas a una actividad MAV
	 * 
	 * @param oidActividad
	 * @return
	 */
	public LabelValue[] getTiposOfertaByActividadId(String oidActividad);
	
	/**
	 * Obtener los tipos de ofertas relacionadas a una actividad MAV
	 * 
	 * @param oidActividad
	 * @return
	 */
	public LabelValue[] getActividadesByTipoCliente(String oidTipoActividad);
	
	/**
	 * Devuelve la lista de accesos por oid de acceso
	 * 
	 * @param oidAcceso
	 * @return
	 */
	public LabelValue[] getOidSubaccesosByOidAcceso(String oidAcceso);
	
	/**
	 * Devuelve el mensaje de la validacion del tipo de oferta, vacio si todo esta ok
	 * 
	 * @param oidTipoOferta
	 * @param codigoProducto
	 * @param oidEstrategia
	 * @param precioCatalogo
	 * @param precioPosicionamiento
	 * @return
	 */
	public String validarTipoOferta(String oidTipoOferta, String codigoProducto, String oidEstrategia, String precioCatalogo, String precioPosicionamiento);
	
	/**
	 * 
	 * @param oidEstrategia
	 * @return
	 */
	public String getOidTipoEstrategia(String oidEstrategia);
	
	/**
	 * Obtiene el listado de tipos de cuadre en base al numero de grupos
	 *  
	 * @param numeroGrupos
	 * @return
	 */
	public LabelValue[] getTiposCuadre(String numeroGrupos);
	
	/**
	 * 
	 * @param codigoPeriodo
	 * @param cuv
	 * @param flagRecuperacion
	 * @return
	 */
	public ProductoMatriz getProductoPREMatrizRecuperaciones(String codigoPeriodo, String cuv, String flagRecuperacion);
	
	/**
	 * 
	 * @param codigoPeriodo
	 * @param cuv
	 * @return
	 */
	public ProductoMatriz getProductoPREMatrizAlternativo(String codigoPeriodo, String cuv);
	
	/**
	 * 
	 * @param codigoPais
	 * @param codigoGrupoPago
	 * @return
	 */
	public LabelValue[] getRegionesZonasByTipoGrupo(String codigoPais, String codigoGrupoPago);
	
	public LabelValue[] getRegionesCodigoByPais(final String codigoPais);
	
	/**
	 * 
	 * @param codigoPais
	 * @param codigoConcurso
	 * @param estado
	 * @param indicadorProgramaPuntos
	 * @return
	 */
	public LabelValue getConcursoProgramaPuntos(final String codigoPais, final String codigoConcurso);
	
	/**
	 * 
	 * @param tipoLista
	 * @return
	 */
	public LabelValue[] getTiposDespachoCPP(final String tipoLista);

        /**
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	public String getVerificarCierreCampanha(String codigoPeriodo);
	
	/**
	 * 
	 * @param codigoPais
	 * @param numeroDocumento
	 * @param tipoDocumento
	 * @param codigoConsultora
	 * @return
	 */
	public String getClasificacionConsultoraByDocIdentidad(String codigoPais, String numeroDocumento, String tipoDocumento, String codigoConsultora);

	/**
	 * 
	 * @param codigoPais
	 * @param numeroDocumento
	 * @param tipoDocumento
	 * @param codigoConsultora
	 * @param tipoEntrada
	 * @return
	 */
	public String getActivaConsultoraByDocIdentidad(String codigoPais, String numeroDocumento, String tipoDocumento, String codigoConsultora, String tipoEntrada );
	
		/**
	 * @param oidProcesoBloqueo
	 * @return
	 */
	public LabelValue[] getDesAccionesByProcesosBloqueo(final String oidProcesoBloqueo);
	
	/**
	 * 
	 * @param tipoDocumentoDigi
	 * @param codigoPais
	 * @return
	 */
	public LabelValue[] getMotivosRechazoSTOList(final String codigoTipoDocumentoDigi, final String codigoPais);
	
	/**
	 * 
	 * @param codigoTipoDocumentoDigi
	 * @return
	 */
	public String getCodigoValidacion(String codigoTipoDocumentoDigi);

	/**
	 * 
	 * @param tipoDocumentoDigi
	 * @param codigoPais
	 * @return
	 */
	public LabelValue[] getValidaciones(final String codigoTipoDocumentoDigi, final String codigoPais);
	
	/**
	 * 
	 * @param codigoValidacion
	 * @return
	 */
	public String getCodigoMensaje(String codigoValidacion);
	
	/**
	 * @param oidTipoClasificacion
	 * @return
	 */
	public LabelValue[] getClasificacionByOidTipoClasificacionIMP(String oidTipoClasificacion);
	
	/**
	 * 
	 * @param codigoPais
	 * @param codigoGrupoPago
	 * @return
	 */
	public LabelValue[] getRegionesZonasByTiposGrupo(String codigoPais, String[] codigoGrupoPago);
	
	/**
	 * @param pais
	 * @param programa
	 * @return
	 */
	public String getIndicadorCuponReutilizable(String pais, String programa);
	
	/**
	 * @param numeroDocumentoIdentidad	
	 * @return
	 */
	public String validarNumeroCarnetIdentidad(String numeroDocumentoIdentidad);


	/**
	 * 
	 * @param codigoVenta
	 * @param codigoOperacionSic
	 * @param tipoOperacionSic
	 * @param oidSoliPosi
	 * @param codigoMotivo
	 * @return
	 */
	public String getValExcepMotivo(String idPeriodoCDR, String codigoVenta, String codigoOperacionSic, String tipoOperacionSic, String oidSoliPosi, String codigoMotivo);
	
        /* INI NSSICC */
	public LabelValue[] getSeccionMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String[] codigoRegion,
			final String[] codigoZona, String condicionTodos);
	
	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionOid(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones,
			String condicionTodos);
	
	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionDetalle(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones, //final ArrayList codigoRegiones,
			String condicionTodos, String tipoDetalle);
	
	public LabelValue[] getZonasMultipleByPeriodoIntEviPerioRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoMarca, final String codigoCanal,
			final String[] codigoRegiones, String condicionTodos);
	
	
	public String deleteBoletaTemporal1(String numeroBoletaRecojo,
			String numeroRecojo, String codigoCliente, List listaBoletas,List temp);
	
	public Map executeProcesoAnulacionBR1(String usuario, 
			 List listTablaTemporal, Integer totalIncorrectas
			 ) ;
	
	public LabelValue[] getCampaniasIniFinByConcursoTipoProgramaPuntos(String numeroConcurso, String codigoPais);
	
	
	
	
	
	/* FIN NSSICC */
}
