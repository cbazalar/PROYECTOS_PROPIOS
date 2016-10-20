package biz.belcorp.ssicc.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.MenuDAO;
import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.bas.MantenimientoBASParametroPaisDAO;
import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCalificacionAptasDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDURegistroAsistenciaDAO;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenericoDefinicion;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPlanillaInstructora;
import biz.belcorp.ssicc.dao.edu.model.MaestroCliente;
import biz.belcorp.ssicc.dao.edu.model.MaestroInstructora;
import biz.belcorp.ssicc.dao.edu.model.ParametroClasificacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ParametroGenerico;
import biz.belcorp.ssicc.dao.edu.model.ParametroRegistroClasificacion;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.Sala;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.LabelSolicitudesCreditoValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuOpciones;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.scsicc.ConsultaHIPDatosClienteDAO;
import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.FuenteVentasDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.EstimadoProductos;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spisicc.MantenimientoIMPProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosLaserDAO;
import biz.belcorp.ssicc.dao.spncd.MantenimientoCUPLogrosDAO;
import biz.belcorp.ssicc.dao.spncd.MantenimientoCUPProgDsctoDAO;
import biz.belcorp.ssicc.dao.spncd.model.DespachoProducto;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaCupon;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPRYProyeccionFaltanteDiaDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAsignarVersionesProductosDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionTextosVariablesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEControlBalanceoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEControlEnvioInterfacesWCSDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPECopiarOrdenAnaquelesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEmitirAlarmaDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEErroresEnCubicajeDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEstimadoProductoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEFactoresConversionDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEGenerarEstimadoProductoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPELineaArmadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEMapaZonaLineaArmadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEOrdenAnaquelesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEProcesoEmbalajeTerminadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPESubLineaArmadoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETiposAnaquelesMapaCDDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEUnidadesAdministrativasDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPEImportarOrdenAnaquelesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstadoOlaWCS;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstimadoProducto;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EtiquetayListadoPicado;
import biz.belcorp.ssicc.dao.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisiones;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMCalificacionComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCalculoCalificacionTramoDAO;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAActividadDAO;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRACalendarioDAO;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRACronogramaFase1DAO;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAGrupoZonaDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ConsultaCCCGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDiferenciaPreciosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosChequesDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.DatosConsultoraBasico;
import biz.belcorp.ssicc.dao.spusicc.fac.MantenimientoFACGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.fac.ProcesoFACProcesarCierresDAO;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVClusterizacionDAO;
import biz.belcorp.ssicc.dao.spusicc.fdv.model.GenericBean;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCBloqueoPremiosDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCCampanaDespachoDiferidaDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionFaltanteDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCPremiosElectivosDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaDespachoConcursoRxPDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaPremiosExcelDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDarPorAtendidoBolsaFaltantesDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarReporteIncentivosDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCReemplazoPremioBolsaFaltantesDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECProgramaCorporativoDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECTarjetaPagoDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCargaDatosExcelDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarPagosDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaPago;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETLideresDAO;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETParametroConcursoDAO;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETPremioCampaniaDAO;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETPremioConcursoDAO;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETProgramaCorporativoDAO;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCargaPedidosObjetivosExcelDAO;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO;
import biz.belcorp.ssicc.dao.spusicc.let.model.Tramos;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDFactorPuntajeDAO;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDLideresDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEEntidadGenericaDAO;
import biz.belcorp.ssicc.dao.spusicc.mav.MantenimientoMAVConfiguracionDAO;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENIngresoGerenteZonalesDAO;
import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCapturaPedidoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCargaPedidoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRReemplazosDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDGestionStockDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDNumerosFacturacionDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRActualizacionGruposProcesoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRFechaProgramadaFacturacionDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDEjecutarGPDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDModificacionCUVMaterialesDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDReasignacionDocumentosLegalesDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetalleCuvExistencia;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pej.ConsultaPEJProgramaEjecutivasDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.MantenimientoPEJProgramaEjecutivasDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizAlternativosDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizRecuperacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoOperacionCDRUsuarioDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECCronogramaBRDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECDigitacionCDRDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECIngresoAtencionesDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECIngresoMasivoOperacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECProductosFFNNEEDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECCargarTablaBoletaRecojoEspecialDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECCierreBRDAO;
import biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVDocumentosContablesDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSECicloDeProgramaDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoDeProgramaSSEDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProductoPeriodoDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.CicloDePrograma;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.LabelDatosProductoValue;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoDeProgramaSessionExperte;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProductoPeriodo;
import biz.belcorp.ssicc.dao.spusicc.sgr.MantenimientoSGRGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.sicc.MantenimientoSICCDAO;
import biz.belcorp.ssicc.dao.spusicc.sicc.model.OpcionesSICC;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CodigoVentaMatriz;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.SubProceso;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONDirectorioDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONUnidadAdministrativaDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONUnidadGeograficaDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaService;
import biz.belcorp.ssicc.service.util.StringUtil;
//import javax.servlet.http.HttpServletRequestProces;
//import javax.servlet.http.HttpSession;
//import org.directwebremoting.WebContext;
//import org.directwebremoting.WebContextFactory;

/**
 * Implementacion de AjaxService.
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * @version <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 */
@Service("ajaxService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class AjaxServiceImpl extends BaseService implements AjaxService {
	
	@Resource(name="spusicc.procesoOCRActualizacionGruposProcesoDAO")
	private ProcesoOCRActualizacionGruposProcesoDAO actualizacionGruposProcesoDAO;
	
	@Resource(name="scsicc.consultaHIPDatosClienteDAO")
	private ConsultaHIPDatosClienteDAO consultaHIPDatosClienteDAO;
	
	@Resource(name="spusicc.procesoOCRFechaProgramadaFacturacionDAO")
	private ProcesoOCRFechaProgramadaFacturacionDAO fechaProgramadaFacturacionDAO;

	@Resource(name="sisicc.fuenteVentasDAO")
	private FuenteVentasDAO fuenteVentasDAO;
	
	@Resource(name="sisicc.interfazDAO")
	private InterfazDAO interfazDAO;

	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;

	@Resource(name="spusicc.mantenimientoAPEAsignarVersionesProductosDAO")
	private MantenimientoAPEAsignarVersionesProductosDAO mantenimientoAPEAsignarVersionesProductosDAO;

	@Resource(name="spusicc.mantenimientoAPEConfiguracionTextosVariablesDAO")
	private MantenimientoAPEConfiguracionTextosVariablesDAO mantenimientoAPEConfiguracionTextosVariablesDAO;

	@Resource(name="spusicc.mantenimientoAPEControlBalanceoDAO")
	private MantenimientoAPEControlBalanceoDAO mantenimientoAPEControlBalanceoDAO;

	@Resource(name="spusicc.mantenimientoAPEControlEnvioInterfacesWCSDAO")
	private MantenimientoAPEControlEnvioInterfacesWCSDAO mantenimientoAPEControlEnvioInterfacesWCSDAO;

	@Resource(name="spusicc.mantenimientoAPECopiarOrdenAnaquelesDAO")
	private MantenimientoAPECopiarOrdenAnaquelesDAO mantenimientoAPECopiarOrdenAnaquelesDAO;

	@Resource(name="spusicc.mantenimientoAPEEmitirAlarmaDAO")
	private MantenimientoAPEEmitirAlarmaDAO mantenimientoAPEEmitirAlarmaDAO;

	@Resource(name="spusicc.mantenimientoAPEErroresEnCubicajeDAO")
	private MantenimientoAPEErroresEnCubicajeDAO mantenimientoAPEErroresEnCubicajeDAO;

	@Resource(name="spusicc.mantenimientoAPEEstimadoProductoDAO")
	private MantenimientoAPEEstimadoProductoDAO mantenimientoAPEEstimadoProductoDAO;

	@Resource(name="spusicc.mantenimientoAPEFactoresConversionDAO")
	private MantenimientoAPEFactoresConversionDAO mantenimientoAPEFactoresConversionDAO;

	@Resource(name="spusicc.mantenimientoAPEGenerarEstimadoProductoDAO")
	private MantenimientoAPEGenerarEstimadoProductoDAO mantenimientoAPEGenerarEstimadoProductoDAO;

	@Resource(name="spusicc.mantenimientoAPELineaArmadoDAO")
	private MantenimientoAPELineaArmadoDAO mantenimientoAPELineaArmadoDAO;

	@Resource(name="spusicc.mantenimientoAPEMapaZonaLineaArmadoDAO")
	private MantenimientoAPEMapaZonaLineaArmadoDAO mantenimientoAPEMapaZonaLineaArmadoDAO;

	@Resource(name="spusicc.mantenimientoAPEOrdenAnaquelesDAO")
	private MantenimientoAPEOrdenAnaquelesDAO mantenimientoAPEOrdenAnaquelesDAO;

	@Resource(name="spusicc.mantenimientoAPEProcesoEmbalajeTerminadoDAO")
	private MantenimientoAPEProcesoEmbalajeTerminadoDAO mantenimientoAPEProcesoEmbalajeTerminadoDAO;

	@Resource(name="spusicc.mantenimientoAPESubLineaArmadoDAO")
	private MantenimientoAPESubLineaArmadoDAO mantenimientoAPESubLineaArmadoDAO;

	@Resource(name="spusicc.mantenimientoAPETiposAnaquelesMapaCDDAO")
	private MantenimientoAPETiposAnaquelesMapaCDDAO mantenimientoAPETiposAnaquelesMapaCDDAO;

	@Resource(name="spusicc.mantenimientoAPEUnidadesAdministrativasDAO")
	private MantenimientoAPEUnidadesAdministrativasDAO mantenimientoAPEUnidadesAdministrativasDAO;

	@Resource(name="spusicc.mantenimientoCCCDiferenciaPreciosDAO")
	private MantenimientoCCCDiferenciaPreciosDAO mantenimientoCCCDiferenciaPreciosDAO;

	@Resource(name="spusicc.mantenimientoCOMCalificacionComisionDAO")
	private MantenimientoCOMCalificacionComisionDAO mantenimientoCOMCalificacionComisionDAO;
					
	@Resource(name="spncd.mantenimientoCUPLogrosDAO")
	private MantenimientoCUPLogrosDAO mantenimientoCUPLogrosDAO;

	@Resource(name="spncd.mantenimientoCUPProgDsctoDAO")
	private MantenimientoCUPProgDsctoDAO mantenimientoDAO;

	@Resource(name="edu.mantenimientoEDUCursoCapacitacionDAO")
	private MantenimientoEDUCursoCapacitacionDAO mantenimientoEDUCursoCapacitacionDAO;

	@Resource(name="spusicc.mantenimientoFACGenericoDAO")
	private MantenimientoFACGenericoDAO mantenimientoFACGenericoDAO;

	@Resource(name="spusicc.mantenimientoINCBloqueoPremiosDAO")
	private MantenimientoINCBloqueoPremiosDAO mantenimientoINCBloqueoPremiosDAO;

	@Resource(name="spusicc.mantenimientoINCCampanaDespachoDiferidaDAO")
	private MantenimientoINCCampanaDespachoDiferidaDAO mantenimientoINCCampanaDespachoDiferidaDAO;

	@Resource(name="spusicc.mantenimientoINCPremiosElectivosDAO")
	private MantenimientoINCPremiosElectivosDAO mantenimientoINCPremiosElectivosDAO;

	@Resource(name="spusicc.mantenimientoLETLideresDAO")
	private MantenimientoLETLideresDAO mantenimientoLETLideresDAO;

	@Resource(name="spusicc.mantenimientoLETParametroConcursoDAO")
	private MantenimientoLETParametroConcursoDAO mantenimientoLETParametroConcursoDAO;

	@Resource(name="spusicc.mantenimientoLETPremioCampaniaDAO")
	private MantenimientoLETPremioCampaniaDAO mantenimientoLETPremioCampaniaDAO;

	@Resource(name="spusicc.mantenimientoLETPremioConcursoDAO")
	private MantenimientoLETPremioConcursoDAO mantenimientoLETPremioConcursoDAO;
	
	@Resource(name="spusicc.mantenimientoLETProgramaCorporativoDAO")
	private MantenimientoLETProgramaCorporativoDAO mantenimientoLETProgramaCorporativoDAO;

	@Resource(name="spusicc.mantenimientoLIDFactorPuntajeDAO")
	private MantenimientoLIDFactorPuntajeDAO mantenimientoLIDFactorPuntajeDAO;

	@Resource(name="spusicc.mantenimientoLIDLideresDAO")
	private MantenimientoLIDLideresDAO mantenimientoLIDLideresDAO;

	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;

	@Resource(name="spusicc.mantenimientoMENGenericoDAO")
	private MantenimientoMENGenericoDAO mantenimientoMENGenericoDAO;

	@Resource(name="spusicc.mantenimientoMENIngresoGerenteZonalesDAO")
	private MantenimientoMENIngresoGerenteZonalesDAO mantenimientoMENIngresoGerenteZonalesDAO;

	@Resource(name="spusicc.pedidos.mantenimientoOCRCapturaPedidoDAO")
	private MantenimientoOCRCapturaPedidoDAO mantenimientoOCRCapturaPedidoDAO;

	@Resource(name="spusicc.pedidos.mantenimientoOCRCargaPedidoDAO")
	private MantenimientoOCRCargaPedidoDAO mantenimientoOCRCargaPedidoDAO;

	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionDAO")
	private MantenimientoOCRPedidoControlFacturacionDAO mantenimientoOCRPedidoControlFacturacionDAO;

	@Resource(name="spusicc.pedidos.mantenimientoOCRReemplazosDAO")
	private MantenimientoOCRReemplazosDAO mantenimientoOCRReemplazosDAO;

	@Resource(name="spusicc.pedidos.mantenimientoPEDClasificacionesChequeoDAO")
	private MantenimientoPEDClasificacionesChequeoDAO mantenimientoPEDClasificacionesChequeoDAO;
	
	@Resource(name="spusicc.mantenimientoRECCronogramaBRDAO")
	private MantenimientoRECCronogramaBRDAO mantenimientoRECCronogramaBRDAO;
	
	@Resource(name="spusicc.mantenimientoRECDigitacionCDRDAO")
	private MantenimientoRECDigitacionCDRDAO mantenimientoRECDigitacionCDRDAO;

	@Resource(name="spusicc.mantenimientoRECIngresoAtencionesDAO")
	private MantenimientoRECIngresoAtencionesDAO mantenimientoRECIngresoAtencionesDAO;

	@Resource(name="spusicc.mantenimientoRECProductosFFNNEEDAO")
	private MantenimientoRECProductosFFNNEEDAO mantenimientoRECProductosFFNNEEDAO;

	@Resource(name="spusicc.mantenimientoRUVDocumentosContablesDAO")
	private MantenimientoRUVDocumentosContablesDAO mantenimientoRUVDocumentosContablesDAO;

	@Resource(name="spusicc.mantenimientoSGRGenericoDAO")
	private MantenimientoSGRGenericoDAO mantenimientoSGRGenericoDAO;

	@Resource(name="sicc.mantenimientoSICCDAO")
	private MantenimientoSICCDAO mantenimientoSICCDAO;

	@Resource(name="spusicc.sessionexperte.mantenimientoSSECicloDeProgramaDAO")
	private MantenimientoSSECicloDeProgramaDAO mantenimientoSSECicloDeProgramaDAO;

	@Resource(name="spusicc.sessionexperte.mantenimientoSSEProductoDeProgramaSSEDAO")
	private MantenimientoSSEProductoDeProgramaSSEDAO mantenimientoSSEProductoDeProgramaSSEDAO;

	@Resource(name="spusicc.sessionexperte.mantenimientoSSEProductoPeriodoDAO")
	private MantenimientoSSEProductoPeriodoDAO mantenimientoSSEProductoPeriodoDAO;

	@Resource(name="spusicc.mantenimientoZONDirectorioDAO")
	private MantenimientoZONDirectorioDAO mantenimientoZONDirectorioDAO;

	@Resource(name="menuDAO")
	private MenuDAO menuDAO;

	@Resource(name="edu.mantenimientoEDUGenericoDAO")
	private MantenimientoEDUGenericoDAO parametroEDUGenericoDAO;
	
	@Resource(name="spusicc.procesoAPEImportarOrdenAnaquelesDAO")
	private ProcesoAPEImportarOrdenAnaquelesDAO procesoAPEImportarOrdenAnaquelesDAO;

	@Resource(name="spusicc.procesoAPPSecuenciarZonaTerritorioDAO")
	private ProcesoAPPSecuenciarZonaTerritorioDAO procesoAPPSecuenciarZonaTerritorioDAO;

	@Resource(name="spusicc.procesoCOBDAO")
	private ProcesoCOBDAO procesoCOBDAO;

	@Resource(name="edu.procesoEDUCalificacionAptasDAO")
	private ProcesoEDUCalificacionAptasDAO procesoEDUCalificacionAptasDAO;

	@Resource(name="edu.procesoEDURegistroAsistenciaDAO")
	private ProcesoEDURegistroAsistenciaDAO procesoEDURegistroAsistenciaDAO;

	@Resource(name="spisicc.procesoIMPGeneracionDocumentosLaserDAO")
	private ProcesoIMPGeneracionDocumentosLaserDAO procesoIMPGeneracionDocumentosLaserDAO;

	@Resource(name="spusicc.procesoINCCargaDespachoConcursoRxPDAO")
	private ProcesoINCCargaDespachoConcursoRxPDAO procesoINCCargaDespachoConcursoRxPDAO;

	@Resource(name="spusicc.procesoINCCargaPremiosExcelDAO")
	private ProcesoINCCargaPremiosExcelDAO procesoINCCargaPremiosExcelDAO;

	@Resource(name="spusicc.procesoINCDarPorAtendidoBolsaFaltantesDAO")
	private ProcesoINCDarPorAtendidoBolsaFaltantesDAO procesoINCDarPorAtendidoBolsaFaltantesDAO;

	@Resource(name="spusicc.procesoINCGenerarReporteIncentivosDAO")
	private ProcesoINCGenerarReporteIncentivosDAO procesoINCGenerarReporteDAO;

	@Resource(name="spusicc.procesoLETCargaPedidosObjetivosExcelDAO")
	private ProcesoLETCargaPedidosObjetivosExcelDAO procesoLETCargaPedidosObjetivosExcelDAO;

	@Resource(name="spusicc.procesoLETResultadoSeccionCampaniaCierreCampaniaDAO")
	private ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO procesoLETResultadoSeccionCampaniaCierreCampaniaDAO;

	@Resource(name="spusicc.procesoPEDModificacionCUVMaterialesDAO")
	private ProcesoPEDModificacionCUVMaterialesDAO procesoPEDModificacionCUVMaterialesDAO;

	@Resource(name="spusicc.procesoPEDReasignacionDocumentosLegalesDAO")
	private ProcesoPEDReasignacionDocumentosLegalesDAO procesoPEDReasignacionDocumentosLegalesDAO;

	@Resource(name="spusicc.procesoPRYProyeccionFaltanteDiaDAO")
	private ProcesoPRYProyeccionFaltanteDiaDAO procesoPRYProyeccionFaltanteDiaDAO;

	@Resource(name="spusicc.procesoRECCargarTablaBoletaRecojoEspecialDAO")
	private ProcesoRECCargarTablaBoletaRecojoEspecialDAO procesoRECCargarTablaBoletaRecojoEspecialDAO;

	@Resource(name="spusicc.procesoSTODAO")
	private ProcesoSTODAO procesoSTODAO;

	@Resource(name="spusicc.procesoSTOEjecucionValidacionesDAO")
	private ProcesoSTOEjecucionValidacionesDAO procesoSTOEjecucionValidacionesDAO;

	@Resource(name="spusicc.procesoSTOLevantamientoErroresValidacionDAO")
	private ProcesoSTOLevantamientoErroresValidacionDAO procesoSTOLevantamientoErroresValidacionDAO;

	@Resource(name="scsicc.reporteDAO")
	private ReporteDAO reporteDAO;

	@Resource(name="spusicc.procesoFDVClusterizacionDAO")
	private ProcesoFDVClusterizacionDAO procesoFDVClusterizacionDAO;

	@Resource(name="spusicc.mantenimientoINCConfiguracionConcursoDAO")
	private MantenimientoINCConfiguracionConcursoDAO mantenimientoINCConfiguracionConcursoDAO;

	@Resource(name="spusicc.mantenimientoRECIngresoMasivoOperacionesDAO")
	private MantenimientoRECIngresoMasivoOperacionesDAO mantenimientoRECIngresoMasivoOperacionesDAO;

	@Resource(name="spusicc.mantenimientoCCCDigitacionPagosChequesDAO")
	private MantenimientoCCCDigitacionPagosChequesDAO mantenimientoCCCDigitacionPagosChequesDAO;

	@Resource(name="spusicc.procesoINCReemplazoPremioBolsaFaltantesDAO")
	private ProcesoINCReemplazoPremioBolsaFaltantesDAO procesoINCReemplazoPremioBolsaFaltantesDAO;

	@Resource(name="spusicc.mantenimientoPEDGestionStockDAO")
	private MantenimientoPEDGestionStockDAO mantenimientoPEDGestionStockDAO;

	@Resource(name="spusicc.ProcesoFACProcesarCierresDAO")
	private ProcesoFACProcesarCierresDAO procesoFACProcesarCierresDAO;

	@Resource(name="spusicc.mantenimientoMAEClienteService")
	private MantenimientoMAEClienteService clienteService ;

	@Resource(name="spusicc.mantenimientoMAVConfiguracionDAO")
	private MantenimientoMAVConfiguracionDAO mantenimientoMAVConfiguracionDAO;
	
	@Resource(name="spusicc.procesoCOMCalculoCalificacionTramoDAO")
	private ProcesoCOMCalculoCalificacionTramoDAO procesoCOMCalculoCalificacionTramoDAO; 
	
	@Resource(name="spusicc.mantenimientoZONUnidadAdministrativaDAO")
	private MantenimientoZONUnidadAdministrativaDAO mantenimientoZONUnidadAdministrativaDAO;

	@Resource(name="spusicc.mantenimientoOperacionCDRUsuarioDAO")
	private MantenimientoOperacionCDRUsuarioDAO mantenimientoOperacionCDRUsuarioDAO;

	@Resource(name="spusicc.mantenimientoZONUnidadGeograficaDAO")
	private MantenimientoZONUnidadGeograficaDAO mantenimientoZONUnidadGeograficaDAO;
	
	@Resource(name="spusicc.mantenimientoCOMComisionDAO")
	private MantenimientoCOMComisionDAO mantenimientoCOMComisionDAO;

	@Resource(name="spusicc.procesoRECCierreBRDAO")
	private ProcesoRECCierreBRDAO procesoRECCierreBRDAO;
	
	@Resource(name="genericoDAO")
	private GenericoDAO genericoDAO;
		
	@Resource(name="spusicc.consultaPEJProgramaEjecutivasDAO")
	private ConsultaPEJProgramaEjecutivasDAO consultaPEJProgramaEjecutivasDAO;
	
	@Resource(name="spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;
	
	@Resource(name="spusicc.mantenimientoPEJProgramaEjecutivasDAO")
	private MantenimientoPEJProgramaEjecutivasDAO mantenimientoPEJProgramaEjecutivasDAO;
	
	@Resource(name="spusicc.mantenimientoCRAActividadDAO")
	private MantenimientoCRAActividadDAO mantenimientoCRAActividadDAO;

	@Resource(name="spusicc.cronograma.mantenimientoCRACronogramaFase1DAO")
	private MantenimientoCRACronogramaFase1DAO mantenimientoCRACronogramaFase1DAO;
	
	@Resource(name="spusicc.mantenimientoCRAGrupoZonaDAO")
	private MantenimientoCRAGrupoZonaDAO  mantenimientoCRAGrupoZonaDAO;

	@Resource(name="spusicc.mantenimientoINCConfiguracionFaltanteDAO")
    private MantenimientoINCConfiguracionFaltanteDAO mantenimientoINCConfiguracionFaltanteDAO;
	
	@Resource(name="spusicc.mantenimientoCRACalendarioDAO")
	private MantenimientoCRACalendarioDAO mantenimientoCRACalendarioDAO;	
	
	@Resource(name="paisDAO")
	private PaisDAO paisDAO;	
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Resource(name="spusicc.procesoLECCargaDatosExcelDAO")
	private ProcesoLECCargaDatosExcelDAO procesoLECCargaDatosExcelDAO;
	
	@Resource(name="spusicc.mantenimientoLECProgramaCorporativoDAO")
	private MantenimientoLECProgramaCorporativoDAO mantenimientoLECProgramaCorporativoDAO;
	
	@Resource(name="spusicc.procesoLECGenerarPagosDAO")
	private ProcesoLECGenerarPagosDAO procesoLECGenerarPagosDAO; 
	
	@Resource(name="spusicc.consultaCCCGenericoDAO")
	private ConsultaCCCGenericoDAO consultaCCCGenericoDAO;
	
	@Resource(name="spusicc.mantenimientoPRECambioCodigoVentaDAO")
	private MantenimientoPRECambioCodigoVentaDAO mantenimientoPRECambioCodigoVentaDAO;
	
	@Resource(name="bas.mantenimientoBASParametroPaisDAO")
	private MantenimientoBASParametroPaisDAO mantenimientoBASParametroPaisDAO;
	
	@Resource(name="spusicc.procesoPEDEjecutarGPDAO")
	private ProcesoPEDEjecutarGPDAO procesoPEDEjecutarGPDAO; 
	
	@Resource(name="spusicc.mantenimientoPEDNumerosFacturacionDAO")
	private MantenimientoPEDNumerosFacturacionDAO mantenimientoPEDNumerosFacturacionDAO;
	
	@Resource(name="spusicc.mantenimientoLECTarjetaPagoDAO")
	private MantenimientoLECTarjetaPagoDAO mantenimientoLECTarjetaPagoDAO;
	
	@Resource(name="spusicc.mantenimientoPEDConfiguracionOfertaService")
	private MantenimientoPEDConfiguracionOfertaService mantenimientoPEDConfiguracionOfertaService;
	
	@Resource(name="spusicc.mantenimientoPREMatrizAlternativosDAO")
	private MantenimientoPREMatrizAlternativosDAO mantenimientoPREMatrizAlternativosDAO;
	
	@Resource(name="spusicc.mantenimientoMAEEntidadGenericaDAO")
	private MantenimientoMAEEntidadGenericaDAO mantenimientoMAEEntidadGenericaDAO;
	
	@Resource(name="spusicc.mantenimientoPREMatrizRecuperacionesDAO")
	private MantenimientoPREMatrizRecuperacionesDAO mantenimientoPREMatrizRecuperacionesDAO;
	
	@Resource(name="imp.mantenimientoIMPProcesoImpresionDAO")
	private MantenimientoIMPProcesoImpresionDAO mantenimientoIMPProcesoImpresionDAO;
	
	@Resource(name="sisicc.interfazOCRDAO")
	private InterfazOCRDAO interfazOCRDAO;
	
    /**
	 * @param clienteService the clienteService to set
	 */
	public void setClienteService(MantenimientoMAEClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public String deleteDetallePedidoConsultora(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numLote,
			String codigoVenta) {
		String result = "NO";
		String separators = "|";

		Map map = new HashMap();
		map.put("periodo", codigoPeriodo);
		map.put("pais", codigoPais);
		numLote = mantenimientoOCRPedidoControlFacturacionDAO.getNumLotes(map);

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("numLote", numLote);

		log.debug("Ejecutando AjaxServiceImpl.deleteDetallePedidoConsultora");

		StringTokenizer st = new StringTokenizer(codigoVenta, separators);
		for (int j = 0; st.hasMoreTokens(); j++) {
			criteria.put("codigoVenta", st.nextToken());
			mantenimientoOCRCargaPedidoDAO.deleteDetallePedido(criteria);
			result = "OK";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#devolverMensajexAccion(java.lang
	 * .String, java.lang.String)
	 */
	public String devolverMensajexAccion(String codigoAccion, String codigoPais) {
		String mensajeAccion = new String();
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoAccion", codigoAccion);
		try {

			mensajeAccion = procesoSTOLevantamientoErroresValidacionDAO
					.getMensajesxAccionSTO(criteria);

		} catch (DataAccessException ignore) {

		}

		return mensajeAccion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#execCalculoPeriIniPromVentas(java
	 * .lang.String, java.lang.String)
	 */
	public String execCalculoPeriIniPromVentas(String codperiFinalPromVenta,
			String numCampPromedio) {
		String varReturn = null;
		Map criteria = new HashMap();
		criteria.put("codperiFinalPromVenta", codperiFinalPromVenta);
		criteria.put("numCampPromedio", numCampPromedio);
		try {
			varReturn = consultaHIPDatosClienteDAO
					.execCalculoPeriIniPromVentas(criteria);
			log.debug("varReturn: " + varReturn);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return varReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * existeValorCriticoReporteProyeccionFaltante(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Integer existeValorCriticoReporteProyeccionFaltante(
			String codigoPais, String fechaFacturacion, String numeroVersion) {
		Integer result = new Integer(0);
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("fechaFacturacion", fechaFacturacion);
		criteria.put("numeroVersion", numeroVersion);
		try {
			Integer resultAux = procesoPRYProyeccionFaltanteDiaDAO
					.existeIndicadorValorCriticoVersion2(criteria);
			if (resultAux != null) {
				result = resultAux;
			} else {
				result = new Integer(0);
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getAccesoByCanal(java.lang.String)
	 */
	public LabelValue[] getAccesoByCanal(String centro) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("canal", centro);
		try {
			List periodos = interfazSiCCDAO.getAccesoByCanal(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getAccionesByDocumentoByUsuario
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getAccionesByDocumentoByUsuario(String codigoPais,
			String tipoDocumento, String usuario) {

		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);
		criteria.put("usuario", usuario);

		List accionesList = procesoSTOLevantamientoErroresValidacionDAO
				.getAccionesGestionSTO(criteria);
		try {
			if (accionesList != null && accionesList.size() > 0) {

				result = new LabelValue[accionesList.size() + 1];
				result[0] = new LabelValue("", "");
				for (int i = 0; i < accionesList.size(); i++) {
					Base accion = (Base) accionesList.get(i);
					LabelValue lv = new LabelValue(accion.getDescripcion(),
							accion.getCodigo());
					result[i + 1] = lv;
				}
			}
		}

		catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getAccionesCobranzaByEtapaTipoAccion
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getAccionesCobranzaByEtapaTipoAccion(
			String codigoEtapaDeuda, String tipoAccion) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("tipoAccion", tipoAccion);

		List accionesList = procesoCOBDAO.getLista(
				"getAccionesCobranzaByEtapaTipoAccion", criteria);
		// List regionesList =
		// procesoCOBAsignacionCarteraDAO.getRegionesByPaisSociedadEtapaDeuda(criteria);
		log.debug("lista en ajax : " + accionesList.size());

		if (accionesList != null && accionesList.size() > 0) {
			result = new LabelValue[accionesList.size()];
			for (int i = 0; i < accionesList.size(); i++) {
				Base accion = (Base) accionesList.get(i);
				LabelValue lv = new LabelValue(accion.getDescripcion(),
						accion.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}

	public LabelValue[] getAccionesCobranzaByPaisSociedadEtapa(
			String codigoPais, String codigoEtapaDeuda) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);

		List accionesList = procesoCOBDAO.getLista(
				"getAccionesCobranzaByPaisSociedadEtapa", criteria);
		// List regionesList =
		// procesoCOBAsignacionCarteraDAO.getRegionesByPaisSociedadEtapaDeuda(criteria);
		log.debug("lista en ajax : " + accionesList.size());

		if (accionesList != null && accionesList.size() > 0) {
			result = new LabelValue[accionesList.size()];
			for (int i = 0; i < accionesList.size(); i++) {
				Base accion = (Base) accionesList.get(i);
				LabelValue lv = new LabelValue(accion.getDescripcion(),
						accion.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}

	public LabelValue[] getAccionesCobranzaByPaisSociedadEtapaTipoAccion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String tipoAccion) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("tipoAccion", tipoAccion);

		List accionesList = procesoCOBDAO.getLista(
				"getAccionesCobranzaByPaisSociedadEtapaTipoAccion", criteria);
		// List regionesList =
		// procesoCOBAsignacionCarteraDAO.getRegionesByPaisSociedadEtapaDeuda(criteria);
		log.debug("lista en ajax : " + accionesList.size());

		if (accionesList != null && accionesList.size() > 0) {
			result = new LabelValue[accionesList.size()];
			for (int i = 0; i < accionesList.size(); i++) {
				Base accion = (Base) accionesList.get(i);
				LabelValue lv = new LabelValue(accion.getDescripcion(),
						accion.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getBloqueoOnline(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Integer getBloqueoOnline(String codigoPais, String codigoCliente,
			String codigoPeriodo) {
		if (codigoCliente == null) {
			return 0;
		}

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);

		return mantenimientoOCRCapturaPedidoDAO.getBloqueoOnline(criteria);
	}

	public LabelDatosConsultoraValue[] getCabeceraConsultora(String codigoPais,
			String codigoConsultora) {
		int cont = 1;
		LabelDatosConsultoraValue[] result = null;
		LabelDatosConsultoraValue consultora = null;
		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigo", codigoConsultora);

		log.debug("Ejecutando AjaxServiceImpl.getCabeceraConsultora");
		try {
			List resultado = mantenimientoOCRPedidoControlFacturacionDAO
					.getConsultoraPedidoCabeceraByCriteria(criteria);
			if (resultado.size() == 1) {
				consultora = (LabelDatosConsultoraValue) resultado.get(0);
				result = new LabelDatosConsultoraValue[++cont];
				result[0] = consultora;
				log.debug("ajax getCabeceraConsultora " + result);
			}
		} catch (Exception e) {
			log.debug("ajax Error getCabeceraConsultora " + result);
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCabeceraConsultoraSimple(java
	 * .lang.String, java.lang.String)
	 */
	public LabelDatosConsultoraValue[] getCabeceraConsultoraSimple(
			String codigoPais, String codigoConsultora) {
		int cont = 1;
		LabelDatosConsultoraValue[] result = null;
		LabelDatosConsultoraValue consultora = null;
		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigo", codigoConsultora);

		log.debug("Ejecutando AjaxServiceImpl.getCabeceraConsultora");
		try {
			List resultado = mantenimientoOCRPedidoControlFacturacionDAO
					.getConsultoraCabeceraSimpleByCriteria(criteria);
			if (resultado.size() == 1) {
				consultora = (LabelDatosConsultoraValue) resultado.get(0);
				result = new LabelDatosConsultoraValue[++cont];
				result[0] = consultora;
				log.debug("ajax getCabeceraConsultora " + result);
			}
		} catch (Exception e) {
			log.debug("ajax Error getCabeceraConsultora " + result);
		}
		return result;

	}

	public LabelDatosProductoValue getCabeceraProducto(String codigoPais,
			String codigoProducto, String codigoPrograma, String codigoAnho) {
		LabelDatosProductoValue producto = null;

		ProductoDeProgramaSessionExperte prodSSE = new ProductoDeProgramaSessionExperte();
		prodSSE.setCodigoPais(codigoPais);
		prodSSE.setCodigoProducto(codigoProducto);
		prodSSE.setCodigoPrograma(codigoPrograma);
		prodSSE.setCodigoAnho(Integer.parseInt(codigoAnho));

		try {
			producto = mantenimientoSSEProductoDeProgramaSSEDAO
					.getDatosProductoByCriteria(prodSSE);
		} catch (Exception e) {
			log.debug("ajax Error getCabeceraProducto " + producto
					+ ". Error Message [" + e.getMessage() + "]");
			e.printStackTrace();
		}
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCampanhasCriteria(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue[] getCampanhasCriteria(String codigoPais,
			String codigoEmpresa, String codigoClasificacion, String anho,
			String indicador) {
		LabelValue[] result = null;
		List campanhasList = new ArrayList();

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		criteria.put("codigoClasificacion", codigoClasificacion);
		criteria.put("anho", anho);

		campanhasList = parametroEDUGenericoDAO.getCampanhasCriteria(criteria);

		if (indicador.equals("0")) {
			result = new LabelValue[campanhasList.size()];

			for (int i = 0; i < campanhasList.size(); i++) {
				Base base = (Base) campanhasList.get(i);
				LabelValue lv = new LabelValue(base.getDescripcion(),
						base.getCodigo());
				result[i] = lv;
			}
		}
		if (indicador.equals("1")) {
			if (!anho.equals("")) {
				result = new LabelValue[18 - campanhasList.size()];
				int cont = 0;
				for (int i = 1; i <= 18; i++) {
					boolean flag = false;
					for (int j = 0; j < campanhasList.size(); j++) {
						// log.debug("i="+i);
						// log.debug("j="+j);

						Base base = (Base) campanhasList.get(j);
						// log.debug(base);
						if (i < 10) {
							if (base.getDescripcion().equals(anho + "0" + i)) {
								flag = true;
							}
						} else {
							if (base.getDescripcion().equals(anho + i)) {
								flag = true;
							}
						}
					}
					if (flag == false) {
						if (i < 10) {
							LabelValue lv = new LabelValue(anho + "0" + i, /*
																			 * codigoClasificacion
																			 * +
																			 * "-"
																			 * +
																			 */
							anho + "0" + i);
							result[cont] = lv;
							cont++;
						} else {
							LabelValue lv = new LabelValue(anho + i, /*
																	 * codigoClasificacion
																	 * +"-"+
																	 */
							anho + i);
							result[cont] = lv;
							cont++;
						}
					}
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCampanhaSiguientes(java.lang
	 * .String, java.lang.String)
	 */
	public String getCampanhaSiguientes(String campanhaProceso,
			String numCampana) {
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", campanhaProceso);
		criteria.put("campanias", numCampana);
		String desPeriodoSiguiente = reporteDAO.getOidString(
				"getDesPeriodoByCodigoPeriodoX", criteria);
		log.debug("desPeriodoSiguiente " + desPeriodoSiguiente);
		return desPeriodoSiguiente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCampannaActualEDUByPaisEmpresa
	 * (java.lang.String, java.lang.String)
	 */
	public String getCampannaActualEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = procesoEDUCalificacionAptasDAO
				.getCampannaActualProceso(criteria);
		if (StringUtils.isBlank(codigoPeriodo)) {
			return "";
		}
		return codigoPeriodo;
	}

	public String getCampannaEDUByPaisEmpresaCampanha(String codigoPais,
			String codigoEmpresa, String numeroCampanha) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = procesoEDUCalificacionAptasDAO
				.getCampannaActualProceso(criteria);
		if (codigoPeriodo != null) {
			criteria.put("campannaProceso", codigoPeriodo);
			criteria.put("numeroCampanna", new Integer(numeroCampanha));
			String campannaSgte = parametroEDUGenericoDAO
					.getDevuelveCampanna(criteria);
			return campannaSgte;
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCampannaSgteEDUByPaisEmpresa
	 * (java.lang.String, java.lang.String)
	 */
	public String getCampannaSgteEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = procesoEDUCalificacionAptasDAO
				.getCampannaActualProceso(criteria);
		if (codigoPeriodo != null) {
			criteria.put("campannaProceso", codigoPeriodo);
			criteria.put("numeroCampanna", new Integer("1"));
			String campannaSgte = parametroEDUGenericoDAO
					.getDevuelveCampanna(criteria);
			return campannaSgte;
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCampaaCerrada(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getCampCerrada(String codigoPais, String codigoPeriodoInicio,
			String codigoPeriodoFin, String codigoMarca, String codigoCanal) {

		Map datos = new HashMap();
		datos.put("codigoPais", codigoPais);
		datos.put("codigoPeriodoInicio", codigoPeriodoInicio);
		datos.put("codigoPeriodoFin", codigoPeriodoFin);
		datos.put("codigoMarca", "T");
		datos.put("codigoCanal", "VD");

		String campCerrada = fuenteVentasDAO.verificaCampCerrada(datos);
		log.debug("campCerrada" + campCerrada);

		return campCerrada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCantDocImpr(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public String[] getCantDocImpr(String codigoPais,
			String tipoDocumentoContable, String codigoSubacceso,
			String ejercicio, String serieDocLegal,
			String rangoDesdeDocInterno, String rangoHastaDocInterno) {

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSistema", Constants.CODIGO_SISTEMA_PED);
		criteria.put("codigoParametro", Constants.CODIGO_PARAMETRO_SOPORTE_CARACTERES);
		String indSoporteCaracteres = procesoPEDReasignacionDocumentosLegalesDAO.getIndSoporteCaracteres(criteria);

		Map parametros = new HashMap();
		parametros.put("tipoDocumentoContable", Integer.valueOf(tipoDocumentoContable));
		parametros.put("codigoSubacceso", codigoSubacceso);
		parametros.put("ejercicio", ejercicio);
		
		if(StringUtils.equalsIgnoreCase(indSoporteCaracteres, "0")){
		parametros.put("serieDocLegal", Integer.valueOf(serieDocLegal));
		}

		if(StringUtils.equalsIgnoreCase(indSoporteCaracteres, "1")){
			parametros.put("serieDocLegal", serieDocLegal);
		}
		
		parametros.put("rangoDesdeDocInterno", rangoDesdeDocInterno);
		parametros.put("rangoHastaDocInterno", rangoHastaDocInterno);
		parametros.put("indSoporteCaracteres", indSoporteCaracteres);
		Map resultado = procesoPEDReasignacionDocumentosLegalesDAO.getCantDocImpr(parametros);

		String[] result = new String[2];
		result[0] = String.valueOf(resultado.get("cantDocImp"));
		result[1] = String.valueOf(resultado.get("numRegNoNulo"));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCantidadClientesByPK(java.lang
	 * .String)
	 */
	public String getCantidadClientesByPK(final String codigoCliente) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigoCliente);
		try {
			result = interfazSiCCDAO.getCantidadClientesByPK(criteria);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCantidadCruceRangoLogro(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public String getCantidadCruceRangoLogro(String codigoCliente,
			String campanaInicio, String campanaFin) {
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("campanaInicio", campanaInicio);
		criteria.put("campanaFin", campanaFin);
		return mantenimientoCUPLogrosDAO.getCantidadCruceRangoLogro(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCantidadPeriodosByPK(java.lang
	 * .String)
	 */
	public String getCantidadPeriodosByPK(final String codigoPeriodo) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		try {
			result = interfazSiCCDAO.getCantidadPeriodosByPK(criteria);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCantidadTerritoriosSinSecuencia
	 * (java.lang.String[])
	 */
	public String getCantidadTerritoriosSinSecuencia(String[] codigoRegion) {
		Map criteria = new HashMap();
		criteria.put("codigoRegion", Arrays.asList(codigoRegion));
		return procesoAPPSecuenciarZonaTerritorioDAO
				.getCantidadTerritoriosSinSecuencia(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCantidadZonasSinSecuencia(java
	 * .lang.String[])
	 */
	public String getCantidadZonasSinSecuencia(String[] codigoRegion) {
		Map criteria = new HashMap();
		criteria.put("codigoRegion", Arrays.asList(codigoRegion));
		return procesoAPPSecuenciarZonaTerritorioDAO
				.getCantidadZonasSinSecuencia(criteria);
	}

	public LabelValue[] getCarterasByPaisSociedadEtapaDeudaPeriodoRegion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);

		log.debug("Vamos a cargar el DAO: ");
		List carteraList = procesoCOBDAO.getLista(
				"getCarterasByPaisSociedadEtapaDeudaPeriodoRegion", criteria);
		// List zonasList =
		// procesoCOBAsignacionCarteraDAO.getZonasByPaisSociedadEtapaDeudaRegion(criteria);
		log.debug("lista en ajax : " + carteraList.size());

		if (carteraList != null && carteraList.size() > 0) {
			result = new LabelValue[carteraList.size()];
			for (int i = 0; i < carteraList.size(); i++) {
				Base cartera = (Base) carteraList.get(i);
				LabelValue lv = new LabelValue(cartera.getDescripcion(),
						cartera.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}

	public LabelValue[] getCarterasByPaisSociedadEtapaDeudaPeriodoRegionZona(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion, String codigoZona) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoZona", codigoZona);

		log.debug("Vamos a cargar el DAO: ");
		List carteraList = procesoCOBDAO.getLista(
				"getCarterasByPaisSociedadEtapaDeudaPeriodoRegionZona",
				criteria);
		log.debug("lista en ajax : " + carteraList.size());

		if (carteraList != null && carteraList.size() > 0) {
			result = new LabelValue[carteraList.size()];
			for (int i = 0; i < carteraList.size(); i++) {
				Base cartera = (Base) carteraList.get(i);
				LabelValue lv = new LabelValue(cartera.getDescripcion(),
						cartera.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCentroDByPaisMarcaCanal(java
	 * .lang.String)
	 */
	public LabelValue[] getCentroDistribucionByPais(String codigoPais) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		try {
			List periodos = interfazSiCCDAO
					.getCentroDistribucionByPais(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getClasificacionByOidTipoClasificacion
	 * (java.lang.String)
	 */
	public LabelValue[] getClasificacionByOidTipoClasificacion(
			String oidTipoClasificacion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(oidTipoClasificacion)
				&& !oidTipoClasificacion.equals("Todos")) {
			try {

				List clasificacion = mantenimientoPEDClasificacionesChequeoDAO
						.getClasificacionByOidTipoClasificacion(oidTipoClasificacion);

				if (clasificacion != null && clasificacion.size() > 0) {
					result = new LabelValue[clasificacion.size()];

					for (int i = 0; i < clasificacion.size(); i++) {
						BaseOID baseOID = (BaseOID) clasificacion.get(i);

						result[i] = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getTipoClasificacionByOidSubTipoClienteList(java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue[] getClasificacionByOidTipoClasificacionList(String oid) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(oid) && !oid.equals("Todos")) {
			try {
				criteria.put("oid", oid);
				List lineaArmado = mantenimientoAPEConfiguracionTextosVariablesDAO
						.getClasificacionByOidTipoClasificacionList(criteria);
				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];
					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);
						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getClasificacionesByCodISOIdiomaTClienteSubTClienteTClasificacion
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getClasificacionesByCodISOIdiomaTClienteSubTClienteTClasificacion(
			String codigoIdiomaISO, String codigoTipoCliente,
			String codigoSubTipoCliente, String codigoTipoClasificacion) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getClasificacionesByCriteria(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base clasificacion = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(),
							clasificacion.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getClasificacionesByCriteriaMultiple
	 * (java.lang.String, java.lang.String, java.util.ArrayList,
	 * java.util.ArrayList)
	 */
	public LabelValue[] getClasificacionesByCriteriaMultiple(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getClasificacionesByCriteriaMultiple(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base clasificacion = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(),
							clasificacion.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getClasificacionesByCriteriaMultipleOID
	 * (java.lang.String, java.lang.String, java.util.ArrayList,
	 * java.util.ArrayList)
	 */
	public LabelValue[] getClasificacionesByCriteriaMultipleOID(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getClasificacionesByCriteriaMultipleOID(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					BaseOID clasificacion = (BaseOID) tiposClasificaciones
							.get(i);
					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(), clasificacion
									.getOid().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getClasificacionesByCriteriaMultipleCodigo
	 * (java.lang.String, java.lang.String, java.util.ArrayList,
	 * java.util.ArrayList)
	 */
	public LabelValue[] getClasificacionesByCriteriaMultipleCodigo(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getClasificacionesByCriteriaMultipleCodigo(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base clasificacion = (Base) tiposClasificaciones
							.get(i);
					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(), clasificacion
									.getCodigo().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getClasificacionesByTipoClasificacion
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getClasificacionesByTipoClasificacion(
			String oidTipoClasificacion, String codigoIdiomaISO) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		criteria.put("codigoISO", codigoIdiomaISO);

		List listaClasificaciones = mantenimientoMAEClienteDAO
				.getClasificaciones(criteria);

		try {
			if (listaClasificaciones != null && listaClasificaciones.size() > 0) {

				result = new LabelValue[listaClasificaciones.size()];

				for (int i = 0; i < listaClasificaciones.size(); i++) {
					Base clasificacion = (Base) listaClasificaciones.get(i);

					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(),
							clasificacion.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeuda(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);

		log.debug("Vamos a cargar el DAO: ");
		List cobradorList = procesoCOBDAO.getLista(
				"getCobradoresByPaisSociedadEtapaDeuda", criteria);

		log.debug("lista en ajax : " + cobradorList.size());

		if (cobradorList != null && cobradorList.size() > 0) {
			result = new LabelValue[cobradorList.size()];
			for (int i = 0; i < cobradorList.size(); i++) {
				Base cobrador = (Base) cobradorList.get(i);
				LabelValue lv = new LabelValue(cobrador.getDescripcion(),
						cobrador.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeudaPeriodo(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);

		log.debug("Vamos a cargar el DAO: ");
		List cobradorList = procesoCOBDAO.getLista(
				"getCobradoresByPaisSociedadEtapaDeudaPeriodo", criteria);

		log.debug("lista en ajax : " + cobradorList.size());

		if (cobradorList != null && cobradorList.size() > 0) {
			result = new LabelValue[cobradorList.size()];
			for (int i = 0; i < cobradorList.size(); i++) {
				Base cobrador = (Base) cobradorList.get(i);
				LabelValue lv = new LabelValue(cobrador.getDescripcion(),
						cobrador.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion, String codigoZona) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoZona", codigoZona);

		log.debug("Vamos a cargar el DAO: ");
		List cobradorList = procesoCOBDAO.getLista(
				"getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona",
				criteria);
		log.debug("lista en ajax : " + cobradorList.size());

		if (cobradorList != null && cobradorList.size() > 0) {
			result = new LabelValue[cobradorList.size()];
			for (int i = 0; i < cobradorList.size(); i++) {
				Base cobrador = (Base) cobradorList.get(i);
				LabelValue lv = new LabelValue(cobrador.getDescripcion(),
						cobrador.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	public LabelValue[] getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZonaCartera(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion, String codigoZona,
			String codigoCartera) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoZona", codigoZona);
		criteria.put("codigoCartera", codigoCartera);

		log.debug("Vamos a cargar el DAO: ");
		List cobradorList = procesoCOBDAO
				.getLista(
						"getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZonaCartera",
						criteria);
		log.debug("lista en ajax : " + cobradorList.size());

		if (cobradorList != null && cobradorList.size() > 0) {
			result = new LabelValue[cobradorList.size()];
			for (int i = 0; i < cobradorList.size(); i++) {
				Base cobrador = (Base) cobradorList.get(i);
				LabelValue lv = new LabelValue(cobrador.getDescripcion(),
						cobrador.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	public LabelValue[] getCobradoresMultiplesByPaisSociedadEtapaDeudaPeriodoRegion(
			final String codigoPais, final String codigoSociedad,
			final String codigoEtapaDeuda, final String codigoPeriodo,
			final ArrayList codigoRegiones, String condicionTodos) {

		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);

			if (StringUtils.isNotBlank(codigoSociedad)
					&& StringUtils.isNotBlank(codigoSociedad)) {
				criteria.put("codigoSociedad", codigoSociedad);
			} else {
				return result;
			}

			if (StringUtils.isNotBlank(codigoEtapaDeuda)
					&& StringUtils.isNotBlank(codigoEtapaDeuda)) {
				criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
			} else {
				return result;
			}

			if (StringUtils.isNotBlank(codigoPeriodo)
					&& StringUtils.isNotBlank(codigoPeriodo)) {
				criteria.put("codigoPeriodo", codigoPeriodo);
			} else {
				return result;
			}

			criteria.put("codigoRegion", codigoRegiones);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				log.debug("Llamando a getCobradoresMultiplesByPaisSociedadEtapaDeudaPeriodoRegion ");
				List cobradoresList = procesoCOBDAO
						.getLista(
								"getCobradoresMultiplesByPaisSociedadEtapaDeudaPeriodoRegion",
								criteria);

				log.debug("lista en ajax : " + cobradoresList.size());

				if (cobradoresList != null && cobradoresList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[cobradoresList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < cobradoresList.size(); i++) {
							Base periodo = (Base) cobradoresList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[cobradoresList.size()];
						for (int i = 0; i < cobradoresList.size(); i++) {
							Base concurso = (Base) cobradoresList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

	}

	public String getCodigoCicloByAnhoTipoCiclo(final String codigoAnho,
			final String tipoCiclo) {
		String codigoCiclo = null;

		if (StringUtils.isNotBlank(codigoAnho)
				&& StringUtils.isNotBlank(tipoCiclo)) {

			CicloDePrograma ciclo = new CicloDePrograma();
			ciclo.setCodigoAnho(Integer.parseInt(codigoAnho));
			ciclo.setTipoCiclo(tipoCiclo);

			try {
				log.debug("ajaxService getCodigoAnho " + ciclo.getCodigoAnho()
						+ " getTipoCiclo " + ciclo.getTipoCiclo());
				String num = mantenimientoSSECicloDeProgramaDAO
						.getCodigoCicloByAnhoTipoCiclo(ciclo);
				log.debug("num: " + num);

				String nunSiguiente = "";

				if (num == null) {
					nunSiguiente = "1";
				} else if (!num.equals("9")) {
					nunSiguiente = (Integer.parseInt(num.trim()) + 1) + "";
				} else {
					return null;
				}

				codigoCiclo = codigoAnho + tipoCiclo + nunSiguiente + "";
				log.debug(" ajaxService codigoCiclo : " + codigoCiclo);
			} catch (DataAccessException ignore) {

				log.warn(ignore.getMessage());

			}
		}
		return codigoCiclo;
	}

	public LabelValue[] getCodigoCiclosByPaisProgAnho(final String codigoPais,
			final String codigoPrograma, final String codigoAnho,
			final String tipoCiclo) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPrograma)
				&& StringUtils.isNotBlank(codigoAnho)) {
			CicloDePrograma ciclo = new CicloDePrograma();

			ciclo.setCodigoPais(codigoPais);
			ciclo.setCodigoPrograma(codigoPrograma);
			ciclo.setCodigoAnho(Integer.parseInt(codigoAnho));
			ciclo.setTipoCiclo(tipoCiclo);
			try {
				List codigoCiclos = mantenimientoSSECicloDeProgramaDAO
						.getCodigoCiclosByPaisProgAnho(ciclo);
				log.debug("codigoCiclos.size()" + codigoCiclos.size());
				if (codigoCiclos != null && codigoCiclos.size() > 0) {
					result = new LabelValue[codigoCiclos.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < codigoCiclos.size(); i++) {
						Base codigoCiclo = (Base) codigoCiclos.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								codigoCiclo.getDescripcion(),
								codigoCiclo.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoClienteByDocumentoIdentidad
	 * (java.lang.String)
	 */
	public String getCodigoClienteByDocumentoIdentidad(
			String numeroDocumentoIdentidad) {
		Map criteria = new HashMap();
		criteria.put("numeroDocumentoIdentidad", numeroDocumentoIdentidad);
		return procesoSTOEjecucionValidacionesDAO
				.getCodigoClienteByDocumentoIdentidad(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoCursoEmpresa(java.lang
	 * .String, java.lang.String)
	 */
	public String getCodigoCursoEmpresa(String codigoPais, String codigoEmpresa) {
		CursoCapacitacion cabecera = new CursoCapacitacion();
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoEmpresa(codigoEmpresa);
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro = parametroEDUGenericoDAO.getParametroCurso(parametro);
		if (parametro == null) {
			return "";
		}
		if (StringUtils.isEmpty(parametro.getValorIncremento())) {
			parametro.setValorIncremento("01");
		}
		if (StringUtils.isEmpty(parametro.getValorInicial())) {
			parametro.setValorInicial("01");
		}

		/* Seteamos el Formulario */
		cabecera.setCodigoPais(codigoPais);
		cabecera.setCodigoEmpresa(codigoEmpresa);
		String lastCodigo = mantenimientoEDUCursoCapacitacionDAO
				.getMaxCursoCapacitacion(cabecera);
		if (StringUtils.isEmpty(lastCodigo)) {
			{
				Integer valor = new Integer(parametro.getValorInicial());
				// Integer incremento = new
				// Integer(parametro.getValorIncremento());
				// valor = new Integer(valor.intValue() +
				// incremento.intValue());
				valor = new Integer(valor.intValue());
				lastCodigo = StringUtils.leftPad(valor.toString().trim(), 2,
						'0');
			}
		} else {
			Integer valor = new Integer(lastCodigo);
			Integer incremento = new Integer(parametro.getValorIncremento());
			valor = new Integer(valor.intValue() + incremento.intValue());
			lastCodigo = StringUtils.leftPad(valor.toString().trim(), 2, '0');
		}
		return lastCodigo;
	}

	// sb 09/07/08
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoEquivalenteClasificacion
	 * (java.lang.String)
	 */
	public LabelValue[] getCodigoEquivalenteClasificacion(
			String codigoTipoClasEquiv) {
		LabelValue[] listaCodEquivClasi = null;
		try {

			List lista = parametroEDUGenericoDAO
					.getCodigoEquivalenteClasificacion(codigoTipoClasEquiv);

			if (lista != null && lista.size() > 0) {
				listaCodEquivClasi = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					Base base = (Base) lista.get(i);
					LabelValue lv = new LabelValue(base.getDescripcion(),
							base.getCodigo());
					listaCodEquivClasi[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaCodEquivClasi = new LabelValue[1];
				listaCodEquivClasi[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.debug(ignore.getMessage());
		}
		return listaCodEquivClasi;

	}

	public String getCodigoInstructora(String codigoPais, String codigoEmpresa,
			String codigoUsuario) {
		MaestroInstructora instructora = new MaestroInstructora();
		instructora.setCodigoPais(codigoPais);
		instructora.setCodigoEmpresa(codigoEmpresa);
		instructora.setCodigoUsuario(codigoUsuario.toUpperCase());
		List instructoras = parametroEDUGenericoDAO
				.getMaestroInstructoraByCriteria(instructora);
		if (instructoras != null && instructoras.size() > 0) {
			return ((MaestroInstructora) instructoras.get(0))
					.getCodigoInstructora();
		} else {
			return null;
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoMensajeByModulo(java.lang
	 * .String)
	 */
	public String getCodigoMensajeByModulo(String codigoModulo,
			String oidPeriodoCorpo, String campanhaProceso, List listMensajePatron) {
		String codigoMensaje = "";
		log.debug("oidModulo " + codigoModulo);
		Integer longitud = codigoModulo.length();
		Map map = new HashMap();
		map.put("oidModulo", codigoModulo);// viene oid en codigo
		map.put("longitud", longitud);
		map.put("oidPeriodoCorpo", oidPeriodoCorpo);

		List listModulos = mantenimientoMENGenericoDAO.getModulos(map);
		String abrevModulo = "";
		if (listModulos.size() > 0) {
			Map base = (Map) listModulos.get(0);
			abrevModulo = (String) base.get("descripcion");
		}
		map.put("abrevModulo", abrevModulo);
		map.put("campanhaProceso", campanhaProceso);
		codigoMensaje = mantenimientoMENGenericoDAO
				.getCodigoMensajeByModulo(map);
		log.debug("mensaje " + codigoMensaje);

		int aux = Integer
				.parseInt(StringUtils.isNotEmpty(codigoMensaje) ? codigoMensaje
						: "1");
		int aux2 = 0;
		
		
		//List listMensajePatron = (List) session.getAttribute(Constants.MEN_MENSAJE_PATRON_LIST);
		if (listMensajePatron != null) {
			for (int i = listMensajePatron.size() - 1; i >= 0; i--) {
				Map bean = (Map) listMensajePatron.get(i);
				String codigoMens = (String) bean.get("codigoMensaje");
				if (codigoMens.substring(0, 3).equals(abrevModulo)) {
					int sgte = Integer.parseInt(codigoMens.substring(3, 5)) + 1;
					codigoMensaje = StringUtils.leftPad("" + sgte, 2, "0");
					aux2 = Integer.parseInt(StringUtils
							.isNotEmpty(codigoMensaje) ? codigoMensaje : "1");
					break;
				}

			}
		}
		
		if (aux > aux2) {
			codigoMensaje = StringUtils.leftPad("" + aux, 2, "0");
		}

		return codigoMensaje == null ? abrevModulo + "01" : abrevModulo
				+ codigoMensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoPremio(java.lang.String)
	 */
	public LabelValue getCodigoPremio(String codigoPremio, String codigoPeriodo) {
		LabelValue result = new LabelValue();
		if (StringUtils.isNotBlank(codigoPremio)) {
			Map criteria = new HashMap();
			criteria.put("codigoPremio", codigoPremio);
			criteria.put("codigoPeriodo", codigoPeriodo);
			result = mantenimientoMENGenericoDAO.getCodigoPremio(criteria);
			if (result.getLabel() == null) {
				result = null;
			}
		}
		return result;
	}

	public String getCodigoProductoByCodigoVenta(String codigoPeriodo,
			String codigoVenta) {
		Map map = new HashMap();
		map.put("codigoVenta", codigoVenta);
		map.put("codigoPeriodo", codigoPeriodo);
		BigDecimal oidOferta = mantenimientoOCRPedidoControlFacturacionDAO
				.getOfertaDetalleByPeriodoCodigoVenta(map);
		map.put("idDetalleOferta", oidOferta);
		DespachoProducto despachoProducto = mantenimientoDAO
				.getOfertaDetalleById(map);
		if (despachoProducto != null) {
			return despachoProducto.getCodigoProducto();
		} else {
			return "";
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoResultadoChequeo(java.
	 * lang.String)
	 */
	public String getCodigoResultadoChequeo(String numeroPedido) {
		log.debug("__getCodigoResultadoChequeo");
		Map criteria = new HashMap();
		criteria.put("numeroPedido", numeroPedido);
		return mantenimientoRECDigitacionCDRDAO
				.getCodigoResultadoChequeo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigosErrados(java.lang.String,
	 * java.lang.String)
	 */
	public String getCodigosErrados(String codigosConsultoras, String codigoPais) {
		String result = "";
		String[] listaClientes = new String[0];
		if (codigosConsultoras.length() > 0) {
			listaClientes = codigosConsultoras.split(",+");
		}
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		for (int i = 0; i < listaClientes.length; i++) {
			criteria.put("codigo", listaClientes[i]);
			if (mantenimientoOCRPedidoControlFacturacionDAO
					.getConsultoraCabeceraSimpleByCriteria(criteria).size() == 0) {
				if (i != 0) {
					result = result + ", " + listaClientes[i];
				} else {
					result = result + listaClientes[i];
				}
		}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoVentaPrecio(java.lang.
	 * String, java.lang.String)
	 */
	public LabelValueCUV getCodigoVentaPrecio(String codigoVenta,
			String codigoPeriodo) {
		log.debug("getCodigoVentaPrecio");
		LabelValueCUV result = new LabelValueCUV();
		if (StringUtils.isNotBlank(codigoVenta)) {
			Map criteria = new HashMap();
			criteria.put("codigoVenta", codigoVenta);
			criteria.put("codigoPeriodo", codigoPeriodo);
			result = mantenimientoCCCDiferenciaPreciosDAO
					.getCodigoVentaPrecio(criteria);
			if (result.getLabel() == null) {
				result = null;
		}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoVentaReemplazado(java.
	 * lang.String, java.lang.String)
	 */
	public LabelValue getCodigoVentaReemplazado(String codigoVenta,
			String codigoPeriodo) {
		LabelValue result = new LabelValue();
		if (StringUtils.isNotBlank(codigoVenta)) {
			Map criteria = new HashMap();
			criteria.put("codigoVenta", codigoVenta);
			criteria.put("codigoPeriodo", codigoPeriodo);
			result = mantenimientoMENGenericoDAO
					.getCodigoVentaReemplazado(criteria);
			if (result.getLabel() == null) {
				result = null;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoVentaReemplazo(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public LabelValue getCodigoVentaReemplazo(String codigoVenta,
			String codigoReemplazo, String codigoPeriodo) {
		LabelValue result = new LabelValue();
		if (StringUtils.isNotEmpty(codigoVenta)
				&& StringUtils.isNotEmpty(codigoReemplazo)) {
			Map criteria = new HashMap();
			criteria.put("codigoVenta", codigoVenta);
			criteria.put("codigoReemplazo", codigoReemplazo);
			criteria.put("codigoPeriodo", codigoPeriodo);
			result = mantenimientoMENGenericoDAO
					.getCodigoVentaReemplazo(criteria);
			if (result.getLabel() == null) {
				result = null;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoVentasFicticio(java.lang
	 * .String, java.lang.String)
	 */
	public Integer getCodigoVentasFicticio(String oidLotePremio, String fila) {
		Map map = new HashMap();
		map.put("oidLotePremioArticulo", oidLotePremio);
		log.debug("oidLotePremioArticulo " + oidLotePremio);
		List listCodVentas = mantenimientoINCPremiosElectivosDAO
				.getListCodigoVentas(map);
		// return Integer.valueOf(fila);
		return (listCodVentas.size() <= 1 ? -1 : Integer.valueOf(fila));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodLineaArmadoDefault(java.lang
	 * .String, java.lang.String)
	 */
	public String getCodLineaArmadoDefault(String codPais, String codCentro) {

		String result = "";
		Map criteria = new HashMap();
		String oidCentro = "";
		String resultado = "";

		criteria.put("codigoPais", codPais);
		criteria.put("codCentro", codCentro);
		criteria.put("oidPais",
				reporteDAO.getOidString("getOidPaisByCodigoPais", criteria));
		oidCentro = mantenimientoAPEUnidadesAdministrativasDAO
				.getOidCentroDistribucionPais(criteria);
		criteria.put("oidCentro", oidCentro);
		resultado = mantenimientoAPEUnidadesAdministrativasDAO
				.getCodLineaArmadaDefecto(criteria);

		result = resultado;

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodMapaZonaDefault(java.lang
	 * .String)
	 */
	public String getCodMapaZonaDefault(String codMapaCD) {

		String result = "";
		Map criteria = new HashMap();
		String oidMapaCD = "";
		String resultado = "";
		criteria.put("codMapCentrDist", codMapaCD);
		oidMapaCD = mantenimientoAPETiposAnaquelesMapaCDDAO
				.getOidMapaCentroDistribucion(criteria);
		criteria.put("oidMapaCD", oidMapaCD);
		resultado = mantenimientoAPECopiarOrdenAnaquelesDAO
				.getMapaZonaDefault(criteria);
		result = resultado;

		return result;

	}

	public LabelValue[] getComisiones(String codigoPeriodo) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPeriodo)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", codigoPeriodo);

			try {
				List periodos = interfazSiCCDAO
						.getListaComisionesByPeriodo(criteria);
				if (periodos != null && periodos.size() > 0) {
					result = new LabelValue[periodos.size() + 1];

					result[0] = new LabelValue("", "");

					for (int i = 0; i < periodos.size(); i++) {
						Base periodo = (Base) periodos.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getComisionesCompletas() {
		LabelValue[] result = new LabelValue[1];
		result[0] = new LabelValue();
		Map criteria = new HashMap();
		try {
			List periodos = interfazSiCCDAO
					.getListaComisionesByPeriodo(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			}

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	public LabelValue[] getComisionesTodas(String codigoPeriodo) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPeriodo)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", codigoPeriodo);

			try {
				List periodos = interfazSiCCDAO
						.getListaComisionesByPeriodo(criteria);
				if (periodos != null && periodos.size() > 0) {
					result = new LabelValue[periodos.size()];

					for (int i = 0; i < periodos.size(); i++) {
						Base periodo = (Base) periodos.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}
		
	public LabelValue[] getComprobantesByPaisBancoCuentaCorriente(
			final String codigoPais, final String codigoBanco,
			final String codigoCuentaCorriente) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoBanco", codigoBanco);
			criteria.put("codigoCuentaCorriente", codigoCuentaCorriente);
			List comprobantes = interfazSiCCDAO
					.getComprobantesByPaisBancoCuentaCorriente(criteria);
			if (comprobantes != null && comprobantes.size() > 0) {
				result = new LabelValue[comprobantes.size()];
				for (int i = 0; i < comprobantes.size(); i++) {
					Base comprobante = (Base) comprobantes.get(i);
					LabelValue lv = new LabelValue(
							comprobante.getDescripcion(),
							comprobante.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConcursos2ByPaisMarcaCanal(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getConcursos2ByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		try {
			result = new LabelValue[1];
			result[0] = new LabelValue("Todos", "");

			List concursosList = new ArrayList();
			;
			try {
				concursosList = interfazSiCCDAO.getLista(
						"getConcursos2ByPaisMarcaCanal", criteria);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (concursosList != null && concursosList.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursosList.size() + 1];
					result[0] = new LabelValue("Todos", "Todos");
					for (int i = 0; i < concursosList.size(); i++) {
						Base periodo = (Base) concursosList.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursosList.size()];
					for (int i = 0; i < concursosList.size(); i++) {
						Base concurso = (Base) concursosList.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConcursosBloqueoPremios(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getConcursosBloqueoPremios(String codigoPeriodo,
			String regiones, String condicionTodos) {

		log.debug("Entro a getConcursosBloqueoPremios");
		log.debug("codigoPeriodo: " + codigoPeriodo);
		log.debug("regiones: " + regiones);
		log.debug("condicionTodos: " + condicionTodos);

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("regiones", regiones);

			List concursos = mantenimientoINCBloqueoPremiosDAO
					.getConcursosBloqueoPremios(criteria);
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("", "");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConcursosBonificacion(java.lang
	 * .String)
	 */
	public LabelValue[] getConcursosBonificacion(String codigoPeriodo) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", codigoPeriodo);

			List concursos = procesoIMPGeneracionDocumentosLaserDAO
					.getConcursosBonificacion(criteria);
			if (concursos != null && concursos.size() > 0) {
				result = new LabelValue[concursos.size()];
				for (int i = 0; i < concursos.size(); i++) {
					Base concurso = (Base) concursos.get(i);
					LabelValue lv = new LabelValue(concurso.getDescripcion(),
							concurso.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getConcursosByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoPeriodo", null);
			List concursos = interfazSiCCDAO
					.getConcursosByPaisMarcaCanal(criteria);
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("Todos", "Todos");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "Todos");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConcursosByPaisMarcaCanalAnhioTodos
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getConcursosByPaisMarcaCanalAnhioTodos(
			String codigoPais, String codigoMarca, String codigoCanal,
			String condicionTodos, String baseCalculo, String anhio) {

		log.debug("Entro a getConcursosByPaisMarcaCanalAnhioTodos");
		log.debug("codigoPais: " + codigoPais);
		log.debug("codigoMarca: " + codigoMarca);
		log.debug("codigoCanal: " + codigoCanal);
		log.debug("baseCalculo: " + baseCalculo);
		log.debug("anhio: " + anhio);

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoPeriodo", null);
			criteria.put("baseCalculo", baseCalculo);
			criteria.put("anhio", anhio);
			List concursos = interfazSiCCDAO
					.getConcursosByPaisMarcaCanalAnhioTodos(criteria);
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("Todos", "Todos");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "Todos");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getConcursosByPaisMarcaCanalDetalle(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos,
			String baseCalculo) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoPeriodo", null);
			criteria.put("baseCalculo", baseCalculo);
			List concursos = interfazSiCCDAO
					.getConcursosByPaisMarcaCanalDellate(criteria);
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("Todos", "Todos");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "Todos");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getConcursosByPaisMarcaCanalDetallePremiosElec(
			String codigoPais, String codigoMarca, String codigoCanal,
			String condicionTodos, String baseCalculo) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoPeriodo", null);
			criteria.put("baseCalculo", baseCalculo);
			List concursos = interfazSiCCDAO
					.getConcursosByPaisMarcaCanalDetallePremiosElec(criteria);
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("Todos", "Todos");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "Todos");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConcursosByPaisMarcaCanalPeriodo
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getConcursosByPaisMarcaCanalPeriodo(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoPeriodo) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			// para cuando le pasemos vacio a la funcion javaScript le pase nulo
			// y no lo filtre por esa condicion
			if ("".equals(codigoPeriodo)) {
				codigoPeriodo = null;
			}
			criteria.put("codigoPeriodo", codigoPeriodo);
			List concursos = interfazSiCCDAO
					.getConcursosByPaisMarcaCanalPeriodo(criteria);
			if (concursos != null && concursos.size() > 0) {
				result = new LabelValue[concursos.size()];
				for (int i = 0; i < concursos.size(); i++) {
					Base concurso = (Base) concursos.get(i);
					LabelValue lv = new LabelValue(concurso.getDescripcion(),
							concurso.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConcursosVigentesByPaisMarcaCanal
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getConcursosVigentesByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		try {
			result = new LabelValue[1];
			result[0] = new LabelValue("Todos", "");

			List concursosList = new ArrayList();
			;
			try {
				concursosList = interfazSiCCDAO.getLista(
						"getConcursosVigentesByPaisMarcaCanal", criteria);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (concursosList != null && concursosList.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursosList.size() + 1];
					result[0] = new LabelValue("Todos", "Todos");
					for (int i = 0; i < concursosList.size(); i++) {
						Base periodo = (Base) concursosList.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursosList.size()];
					for (int i = 0; i < concursosList.size(); i++) {
						Base concurso = (Base) concursosList.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConsolidadoCabeceraByPK(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public String getConsolidadoCabeceraByPK(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numeroLote) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("numeroLote", numeroLote);

		return mantenimientoOCRPedidoControlFacturacionDAO
				.getConsolidadoCabeceraByPK(criteria).get(0).toString();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getConsultoraByCriteria(java.lang
	 * .String, java.lang.String)
	 */
	public String[] getConsultoraByCriteria(final String codigoPais,
			final String codigoConsultora) {
		String[] result = null;
		Cliente consultora = null;
		Map criteria = new HashMap();
		String descripcion = "";
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigo", codigoConsultora);
		log.debug("Ejecutando AjaxServiceImpl.getConsultoraByCriteria");
		try {
			List resultado = interfazSiCCDAO.getConsultorasByCriteria(criteria);
			if (resultado.size() == 1) {
				consultora = (Cliente) resultado.get(0);
				descripcion = consultora.getApellidoPaterno() + " "
						+ consultora.getApellidoMaterno() + ", "
						+ consultora.getNombre();
				result = new String[] { consultora.getCodigo(), descripcion,
						consultora.getApellidoPaterno(),
						consultora.getApellidoMaterno(),
						consultora.getNombre() };
			} else {
				descripcion = "";
			}
		} catch (Exception e) {
			descripcion = "";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#(java.lang
	 * .String, java.lang.String)
	 */
	public String[] getConsultoraByCriteria2(String codigoPais,
			String codigoConsultora, String codigoConexionExterna) {
		String[] result = null;
		Cliente consultora = null;
		Map criteria = new HashMap();
		String descripcion = "";
		criteria.put("codigoPais", codigoPais);
		
		log.debug("Ejecutando AjaxServiceImpl.getConsultoraByCriteria");
		try {
			if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX)){
			criteria.put("codigoCliente", codigoConsultora);
			List resultado = consultaHIPDatosClienteDAO.getClientesByCriteriaFOX(criteria);
				if (resultado.size() == 1) {
			    Map map = (Map) resultado.get(0);
			    String nombre = ObjectUtils.defaultIfNull(map.get("nombre1"),"").toString();
			    String apellidoPaterno = ObjectUtils.defaultIfNull(map.get("apellido1"),"").toString();
			    String apellidoMaterno = ObjectUtils.defaultIfNull(map.get("apellido2"),"").toString();
			    String codigo = ObjectUtils.defaultIfNull(map.get("codigoCliente"),"").toString();
			    
			    descripcion = apellidoPaterno + " "+  apellidoMaterno + ", "+  nombre;
			    result = new String[] { codigo, descripcion,
			    		apellidoPaterno,
			    		apellidoMaterno,
			    		nombre };
				} else {
					descripcion = "";
				}
			
			}else{
		    criteria.put("codigo", codigoConsultora);
			List resultado = interfazSiCCDAO.getConsultorasByCriteria(criteria);
				if (resultado.size() == 1) {
				consultora = (Cliente) resultado.get(0);
				descripcion = consultora.getApellidoPaterno() + " "
						+ consultora.getApellidoMaterno() + ", "
						+ consultora.getNombre();
				result = new String[] { consultora.getCodigo(), descripcion,
						consultora.getApellidoPaterno(),
						consultora.getApellidoMaterno(),
						consultora.getNombre() };
				} else {
					descripcion = "";
				}
		  }
		} catch (Exception e) {
			descripcion = "";
		}
		return result;
	}

	public String getConsultoraExistenteByCriteria(final String codigoPais,
			final String periodo, final String codigoConsultora,
			final String fechaFacturacion) {
		String result = "";
		try {
			Map criteria = new HashMap();
			// String descripcion = "";
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", periodo);
			criteria.put("codigoConsultora", codigoConsultora);
			criteria.put("fechaFacturacion", fechaFacturacion);

			result = interfazSiCCDAO.getConsultoraExistenteByCriteria(criteria);
			log.debug("__AJAX ::: getConsultoraExistenteByCriteria = " + result);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValueCDR getConsultoraReclamoByCodigo(String numeroBoleta,
			String codigoPais, String codigoPeriodoActivo) {
		log.debug("__getConsultoraReclamoByCodigo");
		LabelValueCDR result = new LabelValueCDR();
		if (StringUtils.isNotBlank(numeroBoleta)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("numeroBoleta", numeroBoleta);
			// Ini PER-SiCC-2012-0642
			criteria.put("codPara", Constants.VAL_IND_CDR_MIGR);
			String parametro = mantenimientoRECDigitacionCDRDAO
					.getValorParam(criteria);
			String valorParametro = StringUtils.isNotEmpty(parametro) ? parametro
					: Constants.INACTIVO;
			String valorCeros = numeroBoleta.substring(0, 2);
			String varBusca = "";
			String nuevoNumeroBoleta = "";
			String nuevoNumeroPedido = "";

			if (StringUtils.equals(valorParametro, Constants.ACTIVO)
					&& StringUtils.equals(valorCeros, Constants.VAL_CEROS)) {
				nuevoNumeroBoleta = numeroBoleta.substring(2,
						numeroBoleta.length());

				varBusca = "%"
						+ nuevoNumeroBoleta.substring(0, 2)
						+ "%"
						+ nuevoNumeroBoleta.substring(2,
								nuevoNumeroBoleta.length());
				criteria.put("varBusca", varBusca);
				nuevoNumeroPedido = mantenimientoRECDigitacionCDRDAO
						.getNuevoNumeroPedido(criteria);

				if (StringUtils.isNotEmpty(nuevoNumeroPedido)) {
					criteria.put("numeroBoleta",
							StringUtils.leftPad(nuevoNumeroPedido, 10, '0'));
				}
			}

			// Fin PER-SiCC-2012-0642

			result = mantenimientoRECDigitacionCDRDAO
					.getConsultoraReclamoByCodigo(criteria);
			result.setLista(mantenimientoRECDigitacionCDRDAO
					.getListaCodigosVentaCDR(criteria));

			if (result.getLabel() == null) {
				result = null;
			} else {
				if (StringUtils.isNotBlank(nuevoNumeroPedido)) {
					result.setNuevoNumeroBoleta(StringUtils.leftPad(
							nuevoNumeroPedido, 10, '0'));
				}

				// Se obtiene el monto del pedido y el monto de devolucion
				Map map = mantenimientoRECDigitacionCDRDAO
						.getMontoPedidoDevolucion(criteria);
				result.setMontoDevolucion(map.get("montoDevolucion").toString());
				result.setMontoPedido(map.get("montoPedido").toString());
				
				// Se obtiene el monto de Faltantes
				Map mapFaltantes = mantenimientoRECDigitacionCDRDAO
						.getMontoPedidoFaltantes(criteria);
				result.setMontoFaltantes(mapFaltantes.get("montoDevolucion").toString());
				
				// Se obtiene el monto de Cambio
				Map mapCambios = mantenimientoRECDigitacionCDRDAO
						.getMontoPedidoCambios(criteria);
				result.setMontoCambios(mapCambios.get("montoDevolucion").toString());
				
				// Se obtiene la ubicacion del consultora
				criteria.put("codigoCliente", result.getLabel());
				Map mapConsultora = mantenimientoRECDigitacionCDRDAO
						.getUbicacionConsultora(criteria);

				result.setDireccionDomicilio(mapConsultora.get("direccion")
						.toString());
				result.setUbicacionGeografica(mapConsultora.get("ubicacion")
						.toString());
				// Se obtiene el porcentaje

				criteria.put("codigoPeriodo",
						getPeriodoReclamo(numeroBoleta, codigoPeriodoActivo));

				result.setPorcentajeDevolucion(mantenimientoRECDigitacionCDRDAO
						.getPorcentajeMontoDevolucion(criteria));
				
				//se obtiene el porcentaje de faltantes y cambios
				result.setPorcentajeFaltantes(mantenimientoRECDigitacionCDRDAO
						.getPorcentajeMontoFaltantes(criteria));
				
				result.setPorcentajeCambios(mantenimientoRECDigitacionCDRDAO
						.getPorcentajeMontoCambios(criteria));
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCorrelativoDocumentoSecccion
	 * (java.lang.String, java.lang.String)
	 */
	public String getCorrelativoDocumentoSecccion(String codigoModulo,
			String codigoSeccion, String oidPatron, List listMensajePatron) {
		/*
		 * Map map = new HashMap(); map.put("codigoDocumento", codigoDocumento);
		 * map.put("codigoSeccion", codigoSeccion); map.put("oidPatron",
		 * oidPatron);
		 * 
		 * corr=
		 * mantenimientoMENGenericoDAO.getCorrelativoDocumentoSecccion(map);
		 */

		log.debug("getCorrelativoDocumentoSecccion inicio " + codigoSeccion);
		Integer longitud = codigoModulo.length();
		Map map = new HashMap();
		map.put("oidModulo", codigoModulo);// viene oid en codigo
		map.put("longitud", longitud);
		// map.put("oidPeriodoCorpo", oidPeriodoCorpo);

		List listModulos = mantenimientoMENGenericoDAO.getModulos(map);
		String abrevModulo = "";
		if (listModulos.size() > 0) {
			Map base = (Map) listModulos.get(0);
			abrevModulo = (String) base.get("descripcion");
		}
		log.debug("abrevModulo " + abrevModulo);
		
		int cont = 0;
		//List listMensajePatron = (List) session.getAttribute(Constants.MEN_MENSAJE_PATRON_LIST);
		if (listMensajePatron != null) {
			// contar cunatos mensajes hay documento /seccion
			for (int i = listMensajePatron.size() - 1; i >= 0; i--) {
				Map bean = (Map) listMensajePatron.get(i);
				// String codigoMens = (String) bean.get("codigoMensaje");
				String codigoSecc = String.valueOf(bean.get("codigoSeccion"));
				if (codigoSecc.equals(codigoSeccion)) {
					cont++;
				}

			}
		}
		log.debug("getCorrelativoDocumentoSecccion fin " + (cont + 1));
		return "" + (cont + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCronogramaBRValidarPeriodoExistente
	 * (java.lang.String)
	 */
	public String getCronogramaBRValidarPeriodoExistente(String codigoPeriodo) {
		log.info("Entro a AjaxServiceImpl - getCronogramaBRValidarPeriodoExistente(java.lang.String)");

		// -- Pojo
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);

		// -- Logica
		int resultado = mantenimientoRECCronogramaBRDAO
				.getCronogramaBRValidarPeriodoExistente(criteria);

		log.info("Salio a AjaxServiceImpl - getCronogramaBRValidarPeriodoExistente(java.lang.String)");
		return Integer.toString(resultado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCuentasCorrientesPorPaisSociedad
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getCuentasCorrientesPorPaisSociedad(String codigoPais,
			String codigoSociedad) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoSociedad", codigoSociedad);
			List cuentasCorrientes = interfazSiCCDAO
					.getCuentasCorrientesPorPaisSociedad(criteria);
			if (cuentasCorrientes != null && cuentasCorrientes.size() > 0) {
				result = new LabelValue[cuentasCorrientes.size()];
				for (int i = 0; i < cuentasCorrientes.size(); i++) {
					Base cuenta = (Base) cuentasCorrientes.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getCuentasCorrientesPorPaisSociedadBanco(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getCuentasCorrientesPorPaisSociedadBanco(
			String codigoPais, String codigoSociedad, String codigoBanco) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoSociedad", codigoSociedad);
			criteria.put("codigoBanco", codigoBanco);
			List cuentasCorrientes = interfazSiCCDAO
					.getCuentasCorrientesByPaisBancoSociedad(criteria);
			if (cuentasCorrientes != null && cuentasCorrientes.size() > 0) {
				result = new LabelValue[cuentasCorrientes.size()];
				for (int i = 0; i < cuentasCorrientes.size(); i++) {
					Base cuenta = (Base) cuentasCorrientes.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/**
	 * Metodo que obtiene los cupones asignados por nivel
	 */
	public LabelValue[] getCuponesByNivel(final String codigoPais,
			final String codigoPrograma, final String codigoNivel,
			final String condicionTodos) {

		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPrograma)
				&& StringUtils.isNotBlank(codigoNivel)) {
			Map criteria = new HashMap();
			criteria.put("codigoPrograma", codigoPrograma);
			criteria.put("codigoNivel", codigoNivel);
			criteria.put("codigoPais", codigoPais);

			try {
				List cupones = interfazSiCCDAO.getLista("getCuponesByNivel",
						criteria);
				if (cupones != null && cupones.size() > 0) {
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[cupones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < cupones.size(); i++) {
							Base cupon = (Base) cupones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									cupon.getDescripcion(), cupon.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[cupones.size()];
						for (int i = 0; i < cupones.size(); i++) {
							Base cupon = (Base) cupones.get(i);
							LabelValue lv = new LabelValue(
									cupon.getDescripcion(), cupon.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/**
	 * Metodo que obtiene los cupones faltantes por nivel
	 */
	public LabelValue[] getCuponesFaltantesByNivel(final String codigoPais,
			final String codigoPrograma, final String codigoNivel,
			final String condicionTodos) {

		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPrograma)
				&& StringUtils.isNotBlank(codigoNivel)) {
			Map criteria = new HashMap();
			criteria.put("codigoPrograma", codigoPrograma);
			criteria.put("codigoNivel", codigoNivel);
			criteria.put("codigoPais", codigoPais);

			try {
				List cupones = interfazSiCCDAO.getLista(
						"getCuponesAsignadosByPrograma", criteria);

				ProgramaCupon cuponBean = mantenimientoDAO
						.getProgramaDsctoById(criteria);

				if (StringUtils.equals("T", condicionTodos)) {

					BigDecimal inicio = new BigDecimal(
							cuponBean.getCodigoVentCupIni());
					BigDecimal fin = new BigDecimal(
							cuponBean.getCodigoVentCupFin());
					int i = 1;
					int j = 0;
					result = new LabelValue[(int) (fin.doubleValue()
							- inicio.doubleValue() - cupones.size() + 2)];
					result[0] = new LabelValue("Todos", "");
					while (inicio.compareTo(fin) == -1
							|| inicio.compareTo(fin) == 0) {
						if (cupones.size() > 0) {
							if (j < cupones.size()) {
								Base cupon = (Base) cupones.get(j);
								// Construimos la descripcion
								if (StringUtils.equals(inicio.toString(),
										cupon.getCodigo())) {
									j++;
								} else {
									LabelValue lv = new LabelValue(
											String.valueOf(inicio),
											String.valueOf(inicio));
									result[i] = lv;
									i++;
								}
							} else {
								LabelValue lv = new LabelValue(
										String.valueOf(inicio),
										String.valueOf(inicio));
								result[i] = lv;
								i++;
							}
						} else {
							LabelValue lv = new LabelValue(
									String.valueOf(inicio),
									String.valueOf(inicio));
							result[i] = lv;
							i++;
						}
						inicio = new BigDecimal(inicio.doubleValue() + 1);
					}
				} else {
					BigDecimal inicio = new BigDecimal(
							cuponBean.getCodigoVentCupIni());
					BigDecimal fin = new BigDecimal(
							cuponBean.getCodigoVentCupFin());
					int i = 0;
					int j = 0;
					result = new LabelValue[(int) (fin.doubleValue()
							- inicio.doubleValue() - cupones.size()) + 1];
					while (inicio.compareTo(fin) == -1
							|| inicio.compareTo(fin) == 0) {
						if (cupones.size() > 0 && j < cupones.size()) {
							Base cupon = (Base) cupones.get(j);
							// Construimos la descripcion
							if (StringUtils.equals(inicio.toString(),
									cupon.getCodigo())) {
								j++;
							} else {
								LabelValue lv = new LabelValue(
										String.valueOf(inicio),
										String.valueOf(inicio));
								result[i] = lv;
								i++;
							}
						} else {
							LabelValue lv = new LabelValue(
									String.valueOf(inicio),
									String.valueOf(inicio));
							result[i] = lv;
							i++;
						}
						inicio = new BigDecimal(inicio.doubleValue() + 1);
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCuponesFaltantesPeriodoByNivel
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getCuponesFaltantesPeriodoByNivel(String codigoPais,
			String codigoPrograma, String codigoNivel, String codigoPeriodo,
			String condicionTodos) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPrograma)
				&& StringUtils.isNotBlank(codigoNivel)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPrograma", codigoPrograma);
			criteria.put("codigoNivel", codigoNivel);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);

			try {
				List cupones = interfazSiCCDAO.getLista(
						"getCuponesFaltantesPeriodoByNivel", criteria);
				if (cupones != null && cupones.size() > 0) {
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[cupones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < cupones.size(); i++) {
							Base cupon = (Base) cupones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									cupon.getDescripcion(), cupon.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[cupones.size()];
						for (int i = 0; i < cupones.size(); i++) {
							Base cupon = (Base) cupones.get(i);
							LabelValue lv = new LabelValue(
									cupon.getDescripcion(), cupon.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCuponesNoHomologados(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public String getCuponesNoHomologados(String codigoPais,
			String codigoPrograma, String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", codigoPrograma);
		criteria.put("codigoPeriodo", codigoPeriodo);
		List listaCupones = mantenimientoDAO.getCuponesNoHomologados(criteria);
		String strCupones = "";
		for (int i = 0; i < listaCupones.size(); i++) {
			if (i > 0) {
				strCupones += " - ";
			}
			strCupones += listaCupones.get(i);
		}
		return strCupones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCuponesPeriodoByNivel(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getCuponesPeriodoByNivel(String codigoPais,
			String codigoPrograma, String codigoNivel, String codigoPeriodo,
			String condicionTodos) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPrograma)
				&& StringUtils.isNotBlank(codigoNivel)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPrograma", codigoPrograma);
			criteria.put("codigoNivel", codigoNivel);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);

			try {
				List cupones = interfazSiCCDAO.getLista(
						"getCuponesPeriodoByNivel", criteria);
				if (cupones != null && cupones.size() > 0) {
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[cupones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < cupones.size(); i++) {
							Base cupon = (Base) cupones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									cupon.getDescripcion(), cupon.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[cupones.size()];
						for (int i = 0; i < cupones.size(); i++) {
							Base cupon = (Base) cupones.get(i);
							LabelValue lv = new LabelValue(
									cupon.getDescripcion(), cupon.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	private CursoCapacitacion getCursoCapacitacion(List lista,
			String codigoCurso) {
		CursoCapacitacion cursoCapacitacion = null;
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			cursoCapacitacion = (CursoCapacitacion) it.next();
			if (codigoCurso.equals(cursoCapacitacion.getCodigoCurso())) {
				return cursoCapacitacion;
		}
		}
		return cursoCapacitacion;
	}

	public LabelValue[] getCursosByPaisEmpresa(String codigoPais,
			String codigoEmpresa) {
		LabelValue[] listaCursos = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			List lista = mantenimientoEDUCursoCapacitacionDAO
					.getCursosCapacitacionByCriteria(criteria);
			if (lista != null && lista.size() > 0) {
				listaCursos = new LabelValue[lista.size() + 1];
				listaCursos[0] = new LabelValue("", "");
				for (int i = 0; i < lista.size(); i++) {
					CursoCapacitacion cursoCapacitacion = (CursoCapacitacion) lista
							.get(i);
					LabelValue lv = new LabelValue(
							cursoCapacitacion.getNombreCurso(),
							cursoCapacitacion.getCodigoCurso());
					listaCursos[i + 1] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaCursos = new LabelValue[1];
				listaCursos[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaCursos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCursosByPaisEmpresaSinVacio(
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getCursosByPaisEmpresaSinVacio(String codigoPais,
			String codigoEmpresa) {
		LabelValue[] listaCursos = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			List lista = mantenimientoEDUCursoCapacitacionDAO
					.getCursosCapacitacionByCriteria(criteria);
			if (lista != null && lista.size() > 0) {
				listaCursos = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					CursoCapacitacion cursoCapacitacion = (CursoCapacitacion) lista
							.get(i);
					LabelValue lv = new LabelValue(
							cursoCapacitacion.getNombreCurso(),
							cursoCapacitacion.getCodigoCurso());
					listaCursos[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaCursos = new LabelValue[1];
				listaCursos[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaCursos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDatosCliente(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String getDatosCliente(String codigoPais, String codigoEmpresa,
			String codigoCliente) {
		String datosCliente = "";
		MaestroCliente maestroCliente = new MaestroCliente();
		maestroCliente.setCodigoPais(codigoPais);
		maestroCliente.setCodigoEmpresa(codigoEmpresa);

		String longitudCliente = getLongitudCodCliente(codigoPais);
		log.debug("long cod cliente " + longitudCliente);
		codigoCliente = StringUtils.leftPad(codigoCliente.trim(),
				Integer.parseInt(longitudCliente), "0");

		maestroCliente.setCodigoCliente(codigoCliente.trim());
		List listaCliente = parametroEDUGenericoDAO
				.getMaestroClientes(maestroCliente);

		if (listaCliente != null && listaCliente.size() > 0) {
			maestroCliente = (MaestroCliente) listaCliente.get(0);
			datosCliente = readField(maestroCliente.getDescripcionCliente());
			// se pregunta por el indicador de nombre completo
			if (isIndicadorNombreCompleto(codigoPais, codigoEmpresa)
					&& StringUtils.isEmpty(datosCliente)) {
				datosCliente = new String("   ");// se manda esto por que en
													// el javascript manda q no
													// encuentra
				// codigo cliente y no es q no exista solo q no tiene nombre
				// completo por el indicador
			}
		}

		return datosCliente;
	}

	public LabelValue[] getDatosComisionByTipoComisionista(String codigoPais,
			String tipoComisionista, String flagTodosEnTipo) {
		HashMap params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("tipoComisionista", tipoComisionista);

		List lista = mantenimientoCOMCalificacionComisionDAO
				.getDatosComisionList(params);

		LabelValue[] listaSalida = null;
		if (lista != null && lista.size() > 0) {
			int posInicial = 0;
			if ("S".equals(flagTodosEnTipo)) {
				listaSalida = new LabelValue[lista.size() + 1];
				listaSalida[posInicial++] = new LabelValue("", "");
			} else {
				listaSalida = new LabelValue[lista.size()];
			}
			for (int i = 0; i < lista.size(); i++) {
				Base elemento = (Base) lista.get(i);
				LabelValue lv = new LabelValue(elemento.getDescripcion(),
						elemento.getCodigo());
				listaSalida[posInicial + i] = lv;
			}
		} else {
			listaSalida = new LabelValue[] { new LabelValue("", "") };
		}

		return listaSalida;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDatosCUVByCodigoSAP(java.lang
	 * .String)
	 */
	public LabelValueCUV getDatosCUVByCodigoSAP(String codigoSAP) {
		return procesoPEDModificacionCUVMaterialesDAO
				.getDatosCUVByCodigoSAP(codigoSAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDatosCliente(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String getDatosInstructora(String codigoPais, String codigoEmpresa,
			String codigoInstructora) {
		String datosCliente = "";
		MaestroInstructora maestroInstructora = new MaestroInstructora();
		maestroInstructora.setCodigoPais(codigoPais);
		maestroInstructora.setCodigoEmpresa(codigoEmpresa);

		String len = parametroEDUGenericoDAO
				.getLongitudCodInstructora(codigoPais);
		String codConsultoraAux = StringUtils.leftPad(codigoInstructora,
				Integer.parseInt(len), "0");

		maestroInstructora.setCodigoInstructora(codConsultoraAux);
		List listaInstructora = parametroEDUGenericoDAO
				.getMaestroInstructoraByCriteria(maestroInstructora);
		if (listaInstructora != null && listaInstructora.size() > 0) {
			maestroInstructora = (MaestroInstructora) listaInstructora.get(0);
			datosCliente = readField(maestroInstructora.getPrimerNombre())
					+ " " + readField(maestroInstructora.getSegundoNombre())
					+ " " + readField(maestroInstructora.getApellidoPaterno())
					+ " " + readField(maestroInstructora.getApellidoMaterno());
		}
		return datosCliente;
	}

	public EntidadGenericoDefinicion getDefinicionEntidadGenerico(
			String codigoEntidad, String flagTodosEnTipo) {

		EntidadGenericoDefinicion definicion = parametroEDUGenericoDAO
				.getDefinicionEntidadGenerico(codigoEntidad);

		if (definicion.getCodigoEntidadTipo() != null
				&& definicion.getCodigoEntidadTipo().trim().length() > 0) {
			List camposEnEntidadTipo = parametroEDUGenericoDAO
					.getCamposEntidadGenerico(definicion.getCodigoEntidadTipo());
			Map mapGetTipos = new HashMap();
			mapGetTipos.put("entidadTipo", definicion.getCodigoEntidadTipo());
			mapGetTipos.put("campoCodigo", camposEnEntidadTipo.get(0));
			mapGetTipos.put("campoDescripcion", camposEnEntidadTipo.get(1));
			List lista = parametroEDUGenericoDAO
					.getListaTipoEntidadGenerico(mapGetTipos);

			LabelValue[] listaTipos = null;
			if (lista != null && lista.size() > 0) {
				int posInicial = 0;
				if ("S".equals(flagTodosEnTipo)) {
					listaTipos = new LabelValue[lista.size() + 1];
					listaTipos[posInicial++] = new LabelValue("", "");
				} else {
					listaTipos = new LabelValue[lista.size()];
				}
				for (int i = 0; i < lista.size(); i++) {
					Base periodo = (Base) lista.get(i);
					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					listaTipos[posInicial + i] = lv;
				}
			} else {
				listaTipos = new LabelValue[] { new LabelValue("", "") };
			}
			definicion.setListaTipos(listaTipos);
		}

		return definicion;
	}

	public LabelPedidosValue[] getDescPrecio(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numLote,
			String codigoVenta) {
		LabelPedidosValue[] result = null;
		ProductoAgregacion productoAgregacion = null;
		LabelPedidosValue pedidoValue = null;
		List list = null;
		int cont = 1;
		Map criteria = new HashMap();
		String unidades = "0";
		String total = "0";
		String indicador = "0";

		log.debug("__codigoPeriodo = " + codigoPeriodo);

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("numLote", numLote);

		criteria.put("codCupon", codigoVenta);
		String codHomologado = mantenimientoOCRCargaPedidoDAO
				.getCuponEquivalente(criteria);
		criteria.put("codigoVenta", codHomologado);

		log.debug("Ejecutando AjaxServiceImpl.getDescPrecio");

		try {
			list = mantenimientoOCRCargaPedidoDAO
					.getDetallePedidosConsultoraByCriteria(criteria);
			if (list != null) {
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					pedidoValue = (LabelPedidosValue) iter.next();
				}
			}
			if (pedidoValue != null) {
				unidades = pedidoValue.getUnidades();
				total = pedidoValue.getTotal();
				indicador = pedidoValue.getIndicadorOCS();
			}

			// *****************************************
			Long cuv = new Long(0);
			try {
				cuv = Long.valueOf(codigoVenta);
			} catch (Exception e) {
				// TODO: handle exception
			}

			BigDecimal inicioRango = mantenimientoOCRPedidoControlFacturacionDAO
					.getCodigoCuponInicioRango(criteria);
			BigDecimal finRango = mantenimientoOCRPedidoControlFacturacionDAO
					.getCodigoCuponFinRango(criteria);

			if (cuv.longValue() >= inicioRango.longValue()
					&& cuv.longValue() <= finRango.longValue()) {
				log.debug("Esta entre " + inicioRango + " y " + finRango);
				try {
					productoAgregacion = mantenimientoOCRPedidoControlFacturacionDAO
							.getDetallePedidoCupon(criteria);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			// *****************************************
			else {
				BigDecimal oidOferta = mantenimientoOCRPedidoControlFacturacionDAO
						.getOfertaDetalleByPeriodoCodigoVenta(criteria);
				criteria.put("idDetalleOferta", oidOferta);

				BigDecimal indicaDigit = mantenimientoOCRPedidoControlFacturacionDAO
						.getDetOfertaIndicaDigitableById(criteria);

				// Validamos solo los CUVs digitables
				if (indicaDigit.intValue() <= 0) {
					productoAgregacion = mantenimientoOCRPedidoControlFacturacionDAO
							.getOfertaDetalleById(criteria);
				}
			}

			if (productoAgregacion != null) {
				result = new LabelPedidosValue[++cont];
				result[0] = new LabelPedidosValue(
						productoAgregacion.getCodigoVenta(),
						productoAgregacion.getDescripcionProducto(),
						productoAgregacion.getValorUnitario(), unidades, total,
						indicador);
				log.debug("ajax getDescPrecio " + result);
			}
		} catch (Exception e) {
			log.debug("ajax Error getDescPrecio " + result);
		}
		return result;

	}

	public LabelPedidosValue[] getDescPrecio2(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numLote,
			String codigoVenta) {
		LabelPedidosValue[] result = null;
		ProductoAgregacion productoAgregacion = null;
		LabelPedidosValue pedidoValue = null;
		List list = null;
		int cont = 1;
		Map criteria = new HashMap();
		String unidades = "0";
		String total = "0";
		String indicador = "0";

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("numLote", numLote);

		criteria.put("codCupon", codigoVenta);

		// Modificacion - ya no homologa xq coje el valor de la matriz de ssicc
		/*
		 * String codHomologado = mantenimientoOCRCargaPedidoDAO
		 * .getCuponEquivalente(criteria); criteria.put("codigoVenta",
		 * codHomologado);
		 */
		criteria.put("codigoVenta", codigoVenta);
		// -------------------------------------------------------------------------

		log.debug("Ejecutando AjaxServiceImpl.getDescPrecio2");

		try {
			list = mantenimientoOCRCargaPedidoDAO
					.getDetallePedidosConsultoraByCriteria(criteria);
			if (list != null) {
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					pedidoValue = (LabelPedidosValue) iter.next();
				}
			}
			if (pedidoValue != null) {
				unidades = pedidoValue.getUnidades();
				total = pedidoValue.getTotal();
				indicador = pedidoValue.getIndicadorOCS();
			}
			productoAgregacion = mantenimientoOCRPedidoControlFacturacionDAO
					.getDetalleProductoById(criteria);
			if (productoAgregacion != null) {
				result = new LabelPedidosValue[++cont];
				result[0] = new LabelPedidosValue(
						productoAgregacion.getCodigoVenta(),
						productoAgregacion.getDescripcionProducto(),
						productoAgregacion.getValorUnitario(), unidades, total,
						indicador);
				log.debug("ajax getDescPrecio " + result);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDescripcionByProducto(java.lang
	 * .String)
	 */
	public LabelValue[] getDescripcionByProducto(String producto) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("producto", producto);
		try {
			List periodos = interfazSiCCDAO.getDescripcionByProducto(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDescripcionCentroDistribucion
	 * (java.lang.String, java.lang.String)
	 */
	public String getDescripcionCentroDistribucion(String codPais,
			String codCentro) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("codigoPais", codPais);
		criteria.put("oidPais",
				reporteDAO.getOidString("getOidPaisByCodigoPais", criteria));
		criteria.put("oidCentro", codCentro);
		criteria.put("nombreTablaCentroDistribucion",
				Constants.TABLA_CENTRO_DISTRIBUCION);

		result = mantenimientoAPEOrdenAnaquelesDAO
				.getDescripcionCentroDistribucion(criteria);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getDescripcionInternacionalizableProducto(java.lang.String)
	 */
	public String getDescripcionInternacionalizableProducto(String codigoSap) {
		return mantenimientoLIDLideresDAO
				.getDescripcionInternacionalizableProducto(codigoSap);
	}

	public String getDescripcionProductoByCodigoVenta(String codigoPeriodo,
			String codigoVenta) {
		Map map = new HashMap();
		map.put("codigoVenta", codigoVenta);
		map.put("codigoPeriodo", codigoPeriodo);
		BigDecimal oidOferta = mantenimientoOCRPedidoControlFacturacionDAO
				.getOfertaDetalleByPeriodoCodigoVenta(map);
		map.put("idDetalleOferta", oidOferta);
		DespachoProducto despachoProducto = mantenimientoDAO
				.getOfertaDetalleById(map);
		if (despachoProducto != null) {
			return despachoProducto.getDescripcionProducto();
		} else {
			return "";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDesZonasByPaisMarcaCanalRegion
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getDesZonasByPaisMarcaCanalRegion(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)
				&& StringUtils.isNotBlank(codigoRegion)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoRegion", codigoRegion);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List regiones = interfazSiCCDAO
						.getDesZonasByPaisMarcaCanalRegion(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDevuelveOidMatrizFacturacion
	 * (java.lang.String)
	 */
	public Integer getDevuelveOidMatrizFacturacion(String codigoPeriodo) {
		Map map = new HashMap();
		map.put("codigoPeriodo", codigoPeriodo);
		map.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		map.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		Integer oid = mantenimientoLIDLideresDAO
				.getDevuelveOidMatrizFacturacion(map);
		return (oid != null ? oid : null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDocumentoIdentidad(java.lang
	 * .String)
	 */
	public String getDocumentoIdentidad(String oidCliente) {
		return mantenimientoMAEClienteDAO.getDocumentoIdentidad(oidCliente);
	}

	public LabelValue[] getDocumentos(String codigoPais, String codigoTipo) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (codigoPais != null && codigoTipo != null) {
			if (StringUtils.isNotBlank(codigoPais)
					&& StringUtils.isNotBlank(codigoPais)) {
				criteria = new HashMap();

				criteria.put("codigoPais", codigoPais);
				criteria.put("codigoTipo", codigoTipo);

				try {

					List listPaquete = procesoSTOEjecucionValidacionesDAO
							.getComponentesDocumentos(criteria);

					result = new LabelValue[listPaquete.size()];
					for (int i = 0; i < listPaquete.size(); i++) {
						Base documento = (Base) listPaquete.get(i);
						result[i] = new LabelValue(documento.getDescripcion(),
								documento.getCodigo());
					}

				} catch (DataAccessException ignore) {
					log.warn(ignore.getMessage());
				}
			}
		}
		log.debug("result=" + result);
		return result;
	}

	/**
	 * Devuelve los documentos contables
	 * 
	 * @return
	 */
	public LabelValue[] getDocumentosContables() {

		LabelValue[] result = null;

		Map criteria = new HashMap();

		log.debug("listo para llamar al DAO : ");
		List documentosList = procesoCOBDAO.getLista("getDocumentosContables",
				criteria);

		log.debug("lista en ajax : " + documentosList.size());

		if (documentosList != null && documentosList.size() > 0) {
			result = new LabelValue[documentosList.size()];
			for (int i = 0; i < documentosList.size(); i++) {
				Base documento = (Base) documentosList.get(i);
				LabelValue lv = new LabelValue(documento.getDescripcion(),
						documento.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	private MaestroInstructora getEjecutiva(String codigoPais,
			String codigoEmpresa, String codigoInstructora) {
		MaestroInstructora instructora = new MaestroInstructora();

		instructora.setCodigoPais(codigoPais);
		instructora.setCodigoEmpresa(codigoEmpresa);
		instructora.setCodigoInstructora(codigoInstructora);
		List listaInstructora = parametroEDUGenericoDAO
				.getMaestroInstructoraByCriteria(instructora);
		if ((listaInstructora != null) && (listaInstructora.size() > 0)) {
			instructora = (MaestroInstructora) listaInstructora.get(0);
			return instructora;
		}
		return instructora;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getEsInstructora(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String getEsInstructora(String codigoPais, String codigoEmpresa,
			String usuarioLogin) {
		MaestroInstructora instructora = new MaestroInstructora();
		instructora.setCodigoPais(codigoPais);
		instructora.setCodigoEmpresa(codigoEmpresa);
		instructora.setCodigoUsuario(usuarioLogin);
		instructora.setTipoEjecutiva(Constants.EDU_TIPO_EJECUTIVA_EDUCACION);
		List listaInstructora = parametroEDUGenericoDAO
				.getMaestroInstructoraByCriteria(instructora);
		if (listaInstructora != null && listaInstructora.size() > 0) {
			instructora = (MaestroInstructora) listaInstructora.get(0);
			return instructora.getCodigoInstructora();
		}
		return Constants.NO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCentroDByPaisMarcaCanal(java
	 * .lang.String)
	 */
	public LabelValue[] getEstadosPedidos() {
		LabelValue[] result = null;

		try {
			List periodos = interfazSiCCDAO.getEstadosPedidos();
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		// for(int i=0; i<result.length; i++){
		// System.out.println("MAPA "+result[i].toString()+" mapa "+mapa);
		// }
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getEstadosSTOByTipoDocumento(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getEstadosSTOByTipoDocumento(String codigoPais,
			String tipoDocumento) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);
		List estadosList = procesoSTOLevantamientoErroresValidacionDAO
				.getEstadosSTOByTipoDocumento(criteria);
		try {
			if (estadosList != null && estadosList.size() > 0) {
				result = new LabelValue[estadosList.size()];
				for (int i = 0; i < estadosList.size(); i++) {
					Base estado = (Base) estadosList.get(i);
					LabelValue lv = new LabelValue(estado.getDescripcion(),
							estado.getCodigo());
					result[i] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getEtapasDeudaByPaisSociedad(String codigoPais,
			String codigoSociedad) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);

		log.debug("listo para llamar al DAO : ");
		List etapasList = procesoCOBDAO.getLista(
				"getEtapasDeudaByPaisSociedad", criteria);
		// List etapasList =
		// procesoCOBAsignacionCarteraDAO.getEtapasDeudaByPaisSociedad(criteria);
		log.debug("lista en ajax : " + etapasList.size());

		if (etapasList != null && etapasList.size() > 0) {
			result = new LabelValue[etapasList.size()];
			for (int i = 0; i < etapasList.size(); i++) {
				Base etapa = (Base) etapasList.get(i);
				LabelValue lv = new LabelValue(etapa.getDescripcion(),
						etapa.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	public LabelValue[] getEtapasDeudaByPaisSociedadCobrador(String codigoCobrador) {

		LabelValue[] result = null;

		Map criteria = new HashMap();		
		criteria.put("codigoCobrador", codigoCobrador);

		log.debug("listo para llamar al DAO : ");
		List etapasList = procesoCOBDAO.getLista("getEtapasDeudaByPaisSociedadCobrador", criteria);

		log.debug("lista en ajax : " + etapasList.size());

		if (etapasList != null && etapasList.size() > 0) {
			result = new LabelValue[etapasList.size()];
			for (int i = 0; i < etapasList.size(); i++) {
				Base etapa = (Base) etapasList.get(i);
				LabelValue lv = new LabelValue(etapa.getDescripcion(),
						etapa.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	public String[] getEtiquetayListadoPicadoByCodBarrayOidPais(
			final String codBarra, final String codPais, final String usuario) {
		String[] result = null;
		Map criteria = new HashMap();
		String descripcion = "";
		criteria.put("codBarra", codBarra);
		criteria.put("codPais", codPais);
		criteria.put("usuario", usuario);

		log.debug("Ejecutando AjaxServiceImpl.getEtiquetayListadoPicadoByCodBarrayOidPaisObject");
		try {
			EtiquetayListadoPicado productos = null;
			productos = mantenimientoAPEProcesoEmbalajeTerminadoDAO
					.getEtiquetayListadoPicadoByCodBarrayOidPaisObject(criteria);
			result = new String[] { productos.getOidPais(),
					productos.getOidConsolidado(), productos.getOidSoli(),
					productos.getCodListaPicado(), productos.getNumCaja(),
					productos.getUsuario() };

		} catch (Exception e) {
			descripcion = "";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getExisteCodigoCliente(java.lang
	 * .String, java.lang.String)
	 */
	public String getExisteCodigoCliente(String oidPais, String codigoCliente) {
		Map criteria = new HashMap();

		criteria.put("oidPais", oidPais);
		criteria.put("codigoCliente", codigoCliente);
		String existe = "";
		try {
			if (mantenimientoMAEClienteDAO
					.getExisteCodigoClienteByCodPais(criteria) != null) {
				biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente cliente = mantenimientoMAEClienteDAO
						.getExisteCodigoClienteByCodPais(criteria);
				existe = cliente.getCodigo()
						+ "|"
						+ (cliente.getApellido1() != null ? cliente
								.getApellido1() : " ")
						+ " "
						+ (cliente.getApellido2() != null ? cliente
								.getApellido2() : " ")
						+ " "
						+ (cliente.getNombre1() != null ? cliente.getNombre1()
								: " ")
						+ " "
						+ (cliente.getNombre2() != null ? cliente.getNombre2()
								: " ") + "|" + cliente.getOid();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		log.debug("::::::::::::::  existe = " + existe);
		if (existe == null)
			return "";
		return existe;
	}

	public String getExisteComisionComercializacion(final String fechaDesde,
			final String fechaHasta, final String codigoComision) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("fechaDesde", fechaDesde);
		criteria.put("fechaHasta", fechaHasta);
		criteria.put("codigoComision", codigoComision);
		try {
			result = interfazSiCCDAO
					.getExisteComisionComercializacion(criteria);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getExisteComisionIngreso(java.lang
	 * .String, java.lang.String)
	 */
	public String getExisteComisionIngreso(final String codigoPeriodo,
			final String codigoComision) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoComision", codigoComision);
		criteria.put("indicadorComision", "01");
		try {
			result = interfazSiCCDAO.getExisteComisionIngreso(criteria);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getExisteComisionRecuperacion(java.lang.String, java.lang.String)
	 */
	public String getExisteComisionRecuperacion(final String codigoPeriodo,
			final String codigoComision) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoComision", codigoComision);
		criteria.put("indicadorComision", "02");
		try {
			result = interfazSiCCDAO.getExisteComisionRecuperacion(criteria);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getExisteComisionRecuperacion(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer getExisteComisionRecuperacion(final String codigoPeriodo,
			final String codigoComision, final String codigoTipoComision) {
		Integer result = new Integer(0);
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoComision", codigoComision);
		criteria.put("indicadorComision", "02");
		try {
			if (codigoTipoComision.equals("01")) 
				result = interfazSiCCDAO.getExisteComisionRecuperacionRegion(criteria);
			else if (codigoTipoComision.equals("02"))
			    result = interfazSiCCDAO.getExisteComisionRecuperacionZona(criteria);
			else if (codigoTipoComision.equals("03"))
				result = interfazSiCCDAO.getExisteComisionRecuperacionEjecutiva(criteria);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getExisteConsultoraPolizaActiva
	 * (java.lang.String)
	 */
	public Integer getExisteConsultoraPolizaActiva(String codigoCliente) {
		return mantenimientoSGRGenericoDAO
				.getExisteConsultoraPolizaActiva(codigoCliente);
	}

	public String getExistePedidoConsultora(String codigoPais,
			String codigoEmpresa, String periodo, String numeroLote) {
		log.debug("getExistePedidoConsultora");
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", periodo);
		criteria.put("codigoConsultora", codigoEmpresa);
		criteria.put("numeroLote", numeroLote);
		log.debug(criteria);
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getExistePedidoConsultora(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getExisteProyeccionFaltanteDia(
	 * java.lang.String, java.lang.String)
	 */
	public String getExisteProyeccionFaltanteDia(final String codigoPais,
			final String fechaFacturacion) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("fechaFacturacion", fechaFacturacion);
		try {
			result = interfazSiCCDAO.getExisteProyeccionFaltanteDia(criteria);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public String getExisteReclamo(String codigoPais, String numDocu,
			String codigoPeriodo, String codigoCliente) {
		log.debug("__getExisteReclamo");
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("numeroDocumento", numDocu);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoCliente", codigoCliente);
		return mantenimientoRECDigitacionCDRDAO.getExisteReclamo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getFacturacion(java.lang.String)
	 */
	public LabelValue[] getFacturacion(String centro) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("fechaFacturacion", centro);
		try {
			List periodos = interfazSiCCDAO.getFacturacion(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getFechaActividad(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String getFechaActividad(String campanhaActividad,
			String codigoRegion, String codigoZona, String oidActividad) {
		Map map = new HashMap();
		map.put("campanhaActividad", campanhaActividad);
		map.put("codigoZona", codigoZona);
		map.put("codigoRegion", codigoRegion);
		map.put("oidActividad", oidActividad);
		String fechaActividad = mantenimientoMENIngresoGerenteZonalesDAO
				.getFechaActividad(map);
		log.debug("fecha Actividad " + fechaActividad);
		return fechaActividad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getFechaFacturacionActualEDUByPaisEmpresa(java.lang.String,
	 * java.lang.String)
	 */
	public String getFechaFacturacionActualEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String fecha = procesoEDUCalificacionAptasDAO
				.getFechaProcesoFacturacion(criteria);
		if (StringUtils.isBlank(fecha)) {
			return "";
		}
		return fecha;
	}

	public String getFechaFinalPeriodoByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoPeriodo) {
		String result = "";
		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoPeriodo)
					&& StringUtils.isNotBlank(codigoPeriodo)) {
				criteria.put("codigoPeriodo", codigoPeriodo);
			} else {
				return result;
			}
			try {
				List aux = interfazSiCCDAO.getLista(
						"getFechaFinalPeriodoByPaisMarcaCanal", criteria);

				if (aux.size() > 0) {
					Base base = (Base) aux.get(0);
					result = base.getCodigo();
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}

		return result;
	}

	public String getFechaInicioPeriodoByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoPeriodo) {
		String result = "";
		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoPeriodo)
					&& StringUtils.isNotBlank(codigoPeriodo)) {
				criteria.put("codigoPeriodo", codigoPeriodo);
			} else {
				return result;
			}
			try {
				List aux = interfazSiCCDAO.getLista(
						"getFechaInicioPeriodoByPaisMarcaCanal", criteria);

				if (aux.size() > 0) {
					Base base = (Base) aux.get(0);
					result = base.getCodigo();
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getHistoricoCursoDictado(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getHistoricoCursoDictado(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanha,
			String codigoInstructora, String editable) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			HistoricoCursoDictado cabecera = new HistoricoCursoDictado();
			cabecera.setCodigoPais(codigoPais);
			cabecera.setCodigoEmpresa(codigoEmpresa);
			cabecera.setCodigoCurso(codigoCurso);
			cabecera.setCampanhaInicio(campanha);
			cabecera.setCodigoInstructora(codigoInstructora);
			if ("true".equals(editable)) {
				cabecera.setEstadoCursoDictado(Constants.ESTADO_CURSO_VIGENTE);
			}
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
				List listaCursos = procesoEDURegistroAsistenciaDAO
						.getHistoricoCursoDictado(cabecera);
				if (listaCursos != null && listaCursos.size() > 0) {
					result = new LabelValue[listaCursos.size()];
					for (int i = 0; i < listaCursos.size(); i++) {
						Base cursoDictado = new Base();
						HistoricoCursoDictado historicoCursoDictado = new HistoricoCursoDictado();
						historicoCursoDictado = (HistoricoCursoDictado) listaCursos
								.get(i);
						cursoDictado.setCodigo(historicoCursoDictado
								.getCodigoCursoDictado());
						cursoDictado
								.setDescripcion(historicoCursoDictado
										.getCodigoCursoDictado()
										+ " - "
										+ (DateUtil.getDateTime("dd/MM/yyyy",
												historicoCursoDictado
														.getFechaInicio())));
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								cursoDictado.getDescripcion(),
								cursoDictado.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getHistoricoCursoDictadoCapacitadas
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getHistoricoCursoDictadoCapacitadas(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanhaInicial,
			String campanhaFinal, String codigoCliente,
			String codigoInstructora, String region[]) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map cabecera = new HashMap();

			cabecera.put("codigoPais", codigoPais);
			cabecera.put("codigoEmpresa", codigoEmpresa);
			cabecera.put("codigoCurso", codigoCurso);
			cabecera.put("campanhaInicial", campanhaInicial);
			cabecera.put("campanhaFinal", campanhaFinal);
			cabecera.put("codigoCliente", codigoCliente);
			cabecera.put("codigoInstructora", codigoInstructora);
			cabecera.put("codigoRegion", region);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
				List lista = procesoEDURegistroAsistenciaDAO
						.getHistoricoCursoDictadoCapacitadas(cabecera);
				if (lista != null && lista.size() > 0) {
					result = new LabelValue[lista.size()];
					for (int i = 0; i < lista.size(); i++) {
						Base bean = (Base) lista.get(i);

						// Construimos la descripcion
						LabelValue lv = new LabelValue(bean.getDescripcion(),
								bean.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getHistoricoPlanillaCapacitadas
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getHistoricoPlanillaCapacitadas(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanhaInicial,
			String campanhaFinal, String codigoCliente,
			String codigoInstructora, String codigoDictado, String region[]) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map cabecera = new HashMap();

			cabecera.put("codigoPais", codigoPais);
			cabecera.put("codigoEmpresa", codigoEmpresa);
			cabecera.put("codigoCurso", codigoCurso);
			cabecera.put("campanhaInicial", campanhaInicial);
			cabecera.put("campanhaFinal", campanhaFinal);
			cabecera.put("codigoCliente", codigoCliente);
			cabecera.put("codigoDictado", codigoDictado);
			cabecera.put("codigoInstructora", codigoInstructora);
			cabecera.put("codigoRegion", region);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
				List lista = procesoEDURegistroAsistenciaDAO
						.getHistoricoPlanillaCapacitadas(cabecera);
				if (lista != null && lista.size() > 0) {
					result = new LabelValue[lista.size()];
					for (int i = 0; i < lista.size(); i++) {
						Base bean = (Base) lista.get(i);

						// Construimos la descripcion
						LabelValue lv = new LabelValue(bean.getDescripcion(),
								bean.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getHistoricoPlanillaProgramadas
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getHistoricoPlanillaProgramadas(String codigoPais,
			String codigoEmpresa, String codigoCurso, String campanhaInicial,
			String campanhaFinal, String codigoCliente, String region[]) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map cabecera = new HashMap();

			cabecera.put("codigoPais", codigoPais);
			cabecera.put("codigoEmpresa", codigoEmpresa);
			cabecera.put("codigoCurso", codigoCurso);
			cabecera.put("campanhaInicial", campanhaInicial);
			cabecera.put("campanhaFinal", campanhaFinal);
			cabecera.put("codigoCliente", codigoCliente);
			cabecera.put("estadoCapacitacion",
					Constants.EDU_CURSO_SITUACION_PROGRAMADA);
			cabecera.put("codigoRegion", region);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
				List lista = procesoEDURegistroAsistenciaDAO
						.getHistoricoPlanillaProgramadas(cabecera);
				if (lista != null && lista.size() > 0) {
					result = new LabelValue[lista.size()];
					for (int i = 0; i < lista.size(); i++) {
						Base bean = (Base) lista.get(i);

						// Construimos la descripcion
						LabelValue lv = new LabelValue(bean.getDescripcion(),
								bean.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getIndCambioDireccionCalificacion
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getIndCambioDireccionCalificacion(String codigoEstadoEntrega,
			String codigoNovedad, String calificacion) {
		Map criteria = new HashMap();
		String s = "";
		criteria.put("codigoEstadoEntrega", codigoEstadoEntrega);
		criteria.put("codigoNovedad", codigoNovedad);
		criteria.put("calificacion", calificacion);
		List l = procesoSTOEjecucionValidacionesDAO
				.getNovedadesAccionesOrdenTransporte(criteria);
		if (l.size() != 0) {
			try {
				s = ((Map) l.get(0)).get("indicadorCambioDireccion").toString();
			} catch (Exception e) {
			}
		}
		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getIndicadorCentroServicio(java
	 * .lang.String)
	 */
	public LabelValue getIndicadorCentroServicio(String codigoVenta) {
		log.debug("getIndicadorCentroServicio");
		LabelValue result = new LabelValue();
		if (StringUtils.isNotBlank(codigoVenta)) {
			Map criteria = new HashMap();
			criteria.put("codigoVentaFicticio", codigoVenta);
			List l = new ArrayList();
			l = mantenimientoRECDigitacionCDRDAO
					.getIndicadorCentroServicio(criteria);
			result.setValue(((Base) l.get(0)).getCodigo());
			result.setLabel(((Base) l.get(0)).getDescripcion());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getInformacionCUV(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public ProductoAgregacion getInformacionCUV(String codigoPeriodo,
			String codigoVenta, String indicadorPrincipal) {
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoVenta", codigoVenta);
		ProductoAgregacion pa = new ProductoAgregacion();
		log.debug("Ejecutando AjaxServiceImpl.getInformacionCUV");
		try {
			List resultado = mantenimientoOCRReemplazosDAO
					.getInformacionCUV(criteria);
			if (resultado.size() == 1) {
				pa = (ProductoAgregacion) resultado.get(0);
			} else {
				pa = null;
			}
			if (indicadorPrincipal.equals("P")) {
				pa.setIndicadorExisteReemplazo(mantenimientoOCRReemplazosDAO
						.validaCUVPrincipal(criteria));
			}
		} catch (Exception e) {
			log.debug("ajax Error getCabeceraConsultora ");
		}

		return pa;
	}

	public LabelValue[] getInterfaces(String codigoPais, String codigoSistema,
			String codigoInterfaz) {
		LabelValue[] result = null;
		InterfazPK interfazPK = new InterfazPK(codigoPais, codigoSistema,
				codigoInterfaz);
		Interfaz interfaz = interfazDAO.getInterfaz(interfazPK);

		if (interfaz.isTipoUnitaria()) {
			result = new LabelValue[1];
			result[0] = new LabelValue(interfaz.getDescripcion(),
					interfaz.getCodigo());
		} else if (interfaz.isTipoPaquete()) {
			List listPaquete = interfazDAO
					.getComponentesInterfazPaquete(interfazPK);
			result = new LabelValue[listPaquete.size()];
			for (int i = 0; i < listPaquete.size(); i++) {
				Interfaz interfazEmpaquetada = (Interfaz) listPaquete.get(i);
				result[i] = new LabelValue(
						interfazEmpaquetada.getDescripcion(),
						interfazEmpaquetada.getCodigo());
			}
		}
		log.debug("result=" + result);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getInterfacesBySistema(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getInterfacesBySistema(String codigoPais,
			String codigoSistema) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoSistema", codigoSistema);
			List interfaces = interfazDAO.getInterfacesBySistema(criteria);
			if (interfaces != null && interfaces.size() > 0) {
				result = new LabelValue[interfaces.size()];
				for (int i = 0; i < interfaces.size(); i++) {
					LabelValue lv = (LabelValue) interfaces.get(i);
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}


	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getIntervalosDocumentosAnulados
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public String[] getIntervalosDocumentosAnulados(String codigoPeriodo,
			String codigoPais, String codigoMarca, String codigoCanal,
			String codigoAcceso) {
		return getIntervalosFechaComplementoFacturasVentaDirecta(codigoPeriodo,
				codigoPais, codigoMarca, codigoCanal);
	}

	/*
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getIntervalosFechaComplementoFacturasVentaDirecta(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String[] getIntervalosFechaComplementoFacturasVentaDirecta(
			String codigoPeriodo, String codigoPais, String codigoMarca,
			String codigoCanal) {
		String[] result = null;

		if (StringUtils.isNotBlank(codigoPeriodo)
				&& StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);

			try {
				Base intervalo = null;
				intervalo = interfazSiCCDAO
						.getIntervalosFechaComplementoFacturasVentaDirecta(criteria);
				if (intervalo != null) {
					result = new String[] { intervalo.getCodigo(),
							intervalo.getDescripcion() };
				}
			} catch (ObjectRetrievalFailureException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getIntervalosFechaFacturasCabecera
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public String[] getIntervalosFechaFacturasCabecera(String codigoPeriodo,
			String codigoPais, String codigoMarca, String codigoCanal,
			String codigoAcceso) {
		return getIntervalosFechaFacturasVentaDirecta(codigoPeriodo,
				codigoPais, codigoMarca, codigoCanal, codigoAcceso);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getIntervalosFechaFacturasVentaDirecta
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public String[] getIntervalosFechaFacturasVentaDirecta(
			String codigoPeriodo, String codigoPais, String codigoMarca,
			String codigoCanal, String codigoAcceso) {

		String[] result = null;

		if (StringUtils.isNotBlank(codigoPeriodo)
				&& StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoAcceso", codigoAcceso);

			try {
				Base intervalo = null;
				intervalo = interfazSiCCDAO
						.getIntervalosFechaFacturasVentaDirecta(criteria);
				if (intervalo != null) {
					result = new String[] { intervalo.getCodigo(),
							intervalo.getDescripcion() };
				}
			} catch (ObjectRetrievalFailureException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLIDValidaPeriodosConcurso(java
	 * .lang.String, java.lang.String)
	 */
	public String getLIDValidaPeriodosConcurso(String codigoPais,
			String codigoConcurso) {
		log.info("Entro a AjaxServiceImpl - getLIDValidaPeriodosConcurso(java.lang.String, java.lang.String)");

		// -- Variables
		String mensaje = "";

		// -- Recuperar Periodos validos del concurso seleccionado
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoConcurso", codigoConcurso);

		criteria = (HashMap) reporteDAO.getLIDValidaPeriodosConcurso(criteria)
				.get(0);
		mensaje = criteria.get("periodoDesde") + ","
				+ criteria.get("periodoHasta");

		log.info("Salio a AjaxServiceImpl - getLIDValidaPeriodosConcurso(java.lang.String, java.lang.String) - Resultado :"
				+ mensaje);
		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCentroDByPaisMarcaCanal(java
	 * .lang.String)
	 */
	public LabelValue[] getLinea(String codigoPais) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoCentro", codigoPais);
		try {
			List periodos = interfazSiCCDAO.getLinea(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLineaArmadobyOidCentro(java.
	 * lang.String, java.lang.String)
	 */
	public LabelValue[] getLineaArmadobyOidCentro(String codPais,
			String codCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codCentro) && !codCentro.equals("Todos")) {
			try {

				criteria.put("codigoPais", codPais);
				criteria.put("oidPais", reporteDAO.getOidString(
						"getOidPaisByCodigoPais", criteria));
				criteria.put("oidCentro", codCentro);
				criteria.put("nombreTabla2", Constants.TABLA_LINEA_ARMADO);
				List lineaArmado = mantenimientoAPELineaArmadoDAO
						.getLineaArmadobyOidCentro(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);

						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLineaArmadoComboList(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getLineaArmadoComboList(String codPais, String codCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codCentro) && !codCentro.equals("Todos")) {
			try {

				criteria.put("codigoPais", codPais);
				criteria.put("oidPais", reporteDAO.getOidString(
						"getOidPaisByCodigoPais", criteria));
				criteria.put("oidCentro", codCentro);
				criteria.put("nombreTabla2", Constants.TABLA_LINEA_ARMADO);
				List lineaArmado = mantenimientoAPESubLineaArmadoDAO
						.getLineaArmadoComboList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);

						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLineaArmadoListar(java.lang.
	 * String, java.lang.String)
	 */
	public LabelValue[] getLineaArmadoListar(String codPais, String codCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		String oidCentro = "";

		if (StringUtils.isNotBlank(codCentro) && !codCentro.equals("Todos")) {
			try {
				log.debug("Ingreso a valida si no es blanco CodCentro");
				criteria.put("codigoPais", codPais);
				criteria.put("codCentro", codCentro);
				criteria.put("oidPais", reporteDAO.getOidString(
						"getOidPaisByCodigoPais", criteria));
				oidCentro = mantenimientoAPEUnidadesAdministrativasDAO
						.getOidCentroDistribucionPais(criteria);
				criteria.put("oidCentro", oidCentro);
				criteria.put("nombreTabla2", Constants.TABLA_LINEA_ARMADO);
				List lineaArmado = mantenimientoAPESubLineaArmadoDAO
						.getLineaArmadoComboList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base armado = (Base) lineaArmado.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(armado.getDescripcion(),
								armado.getCodigo());
						result[i] = lv;
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLineaMapaOrdenComboList(java
	 * .lang.String)
	 */
	public LabelValue[] getLineaMapaOrdenComboList(String codMapaCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codMapaCentro)
				&& !codMapaCentro.equals("Todos")) {
			try {
				criteria.put("nombreTablaMapaZonaCab",
						Constants.TABLA_MAPA_ZONA_CAB);
				// Obteniendo el oid del Mapa Zona
				criteria.put("codMapCentrDist", codMapaCentro);
				criteria.put("oidMapaCentro",
						mantenimientoAPETiposAnaquelesMapaCDDAO
								.getOidMapaCentroDistribucion(criteria));
				criteria.put("nombreTablaLineaArmado",
						Constants.TABLA_LINEA_ARMADO);
				List lineaArmado = mantenimientoAPEOrdenAnaquelesDAO
						.getLineaMapaOrdenComboList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);

						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLineaMapaOrdenZonaList(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getLineaMapaOrdenZonaList(String codMapaCentro,
			String codMapaZona, String codCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codMapaCentro)
				&& !codMapaCentro.equals("Todos")
				&& StringUtils.isNotBlank(codMapaZona)
				&& StringUtils.isNotBlank(codCentro)
				&& !codCentro.equals("Todos")) {
			try {
				criteria.put("nombreTablaMapaZonaCab",
						Constants.TABLA_MAPA_ZONA_CAB);
				// Obteniendo el oid del Mapa Centro
				criteria.put("oidCentro", codCentro);
				criteria.put("codMapCentrDist", codMapaCentro);
				criteria.put("oidMapaCentro",
						mantenimientoAPEMapaZonaLineaArmadoDAO
								.getOidMapaCDxOidCentro(criteria));
				criteria.put("nombreTablaLineaArmado",
						Constants.TABLA_LINEA_ARMADO);
				// Obteniendo el oid del Mapa Zona
				criteria.put("codMapaZona", codMapaZona);
				criteria.put("oidMapaZona", mantenimientoAPEOrdenAnaquelesDAO
						.getOidMapaZonaByMapaCentroZona(criteria));

				List lineaArmado = mantenimientoAPEOrdenAnaquelesDAO
						.getLineaMapaOrdenZonaList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);

						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public LabelPedidosValue[] getList(String codigo, String descripcion,
			String precioCat, String unidades, String total) {

		LabelPedidosValue[] lb = null;
		int cont = 1;
		if (StringUtils.isEmpty(codigo)) {
			lb = new LabelPedidosValue[cont];
		} else {
			lb = new LabelPedidosValue[++cont];
			lb[0] = new LabelPedidosValue(codigo, descripcion, precioCat,
					unidades, total);
		}
		lb[cont - 1] = new LabelPedidosValue("", "", "", "", "");
		return lb;
	}

	public LabelSolicitudesCreditoValue[] getListadoPedidoZona(
			String codigozona, String fechaFacturacion, String unidades) {

		LabelSolicitudesCreditoValue[] lb = null;
		int cont = 1;
		if (StringUtils.isEmpty(codigozona)) {
			lb = new LabelSolicitudesCreditoValue[cont];
		} else {
			lb = new LabelSolicitudesCreditoValue[++cont];
			lb[0] = new LabelSolicitudesCreditoValue(codigozona,
					fechaFacturacion, "", unidades);
		}
		lb[cont - 1] = new LabelSolicitudesCreditoValue("", "", "", "");
		return lb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getListaEstimados(java.lang.String,
	 * java.lang.String)
	 */
	public String getListaEstimados(String periodo, String linea) {

		EstimadoProductos cab = new EstimadoProductos();
		Map criteria = new HashMap();
		System.out.println("Holaaaaaaaaaaaaaaaa " + periodo + linea);
		criteria.put("linea", linea);
		criteria.put("periodo", periodo);
		cab.setCompania("A");
		// service.setInterfazSATRecepcionarEstimadoProductos(cab,null,
		// criteria);
		try {
			File f = new File("esto.txt");
			BufferedReader entrada = new BufferedReader(new FileReader(f));
			if (f.exists()) {
				String archivoA = entrada.readLine();
				System.out.println(archivoA);
			}
			criteria.put("oidProducto", "200051315");
			List periodos = interfazSiCCDAO.getListaEstimados(criteria);
			if (periodos == null) {
				return "false";
			} else {
				return "true";
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "true";
	}

	// BAS_CTRL_FACT
	public LabelValue[] getListaPeriodosByBasCtrlFact(String codigoPais,
			String staCamp, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(staCamp)) {
				criteria.put("staCamp", staCamp);
			}

			try {
				List periodos = interfazSiCCDAO
						.getListaPeriodosByBasCtrlFact(criteria);

				if (periodos != null && periodos.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[periodos.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < periodos.size(); i++) {
							Base periodo = (Base) periodos.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[periodos.size()];
						for (int i = 0; i < periodos.size(); i++) {
							Base concurso = (Base) periodos.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getPeriodosComplementoFacturasVentaDirecta(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getListaPeriodosByPaisCanal(String codigoPais,
			String codigoCanal) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoCanal", codigoCanal);

			try {
				List periodos = interfazSiCCDAO
						.getListaPeriodosByPaisCanal(criteria);
				if (periodos != null && periodos.size() > 0) {
					result = new LabelValue[periodos.size()];

					for (int i = 0; i < periodos.size(); i++) {
						Base periodo = (Base) periodos.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public ArrayList getListaPeriodosInicial(Map params) {
		ArrayList result = new ArrayList();
		try {
			result = new ArrayList(
					interfazSiCCDAO.getListaPeriodosInicial(params));
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getListaPeriodosPaisCanalAnioRango(String codigoPais,
			String codigoCanal, String codigoAnio, String codigoRango) {

		Map criteria = new HashMap();
		LabelValue[] result = null;

		String periodoMenor = codigoAnio + getPeriodoInicio(codigoRango);
		String periodoMayor = codigoAnio + getPeriodoFin(codigoRango);

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("periodoMayor", periodoMayor);
		criteria.put("periodoMenor", periodoMenor);

		ArrayList listaPeriodos = getListaPeriodosInicial(criteria);

		try {
			if (listaPeriodos != null && listaPeriodos.size() > 0) {

				result = new LabelValue[listaPeriodos.size()];

				for (int i = 0; i < listaPeriodos.size(); i++) {
					Base periodo = (Base) listaPeriodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getListaRolSICCSeleccionados(java
	 * .lang.String)
	 */
	public String getListaRolSICCSeleccionados(String codigoRol) {
		log.info("Entro a AjaxServiceImpl - getListaRolSICCSeleccionados(String)");

		// -- Variables
		String resultado = new String();

		// -- Mapeo
		Map criteria = new HashMap();
		criteria.put("oid", codigoRol);

		// -- Logica
		ArrayList lista = (ArrayList) mantenimientoSICCDAO
				.getListaOpcionesRolSICCByCriteria(criteria);
		OpcionesSICC dto = null;

		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			dto = (OpcionesSICC) iterator.next();
			if (Integer.valueOf(dto.getValorAcceso()) == 1) {
				resultado += Integer.toString(dto.getOid().intValue()).concat(
						";");
		}
		}

		log.info("Salio a AjaxServiceImpl - getListaRolSICCSeleccionados(String)");
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getListaUsuarioSICCSeleccionados
	 * (java.lang.String)
	 */
	public String getListaUsuarioSICCSeleccionados(String codigoUsuario) {
		log.info("Entro a AjaxServiceImpl - getListaUsuarioSICCSeleccionados(String)");

		// -- Variables
		String resultado = new String();

		// -- Mapeo
		Map criteria = new HashMap();
		criteria.put("oid", codigoUsuario);

		// -- Logica
		ArrayList rolesUsuarioList = (ArrayList) mantenimientoSICCDAO
				.getListaSICCRolUsuarioByCriteria(criteria);
		HashMap arrMap = null;

		for (Iterator iterator = rolesUsuarioList.iterator(); iterator
				.hasNext();) {
			arrMap = (HashMap) iterator.next();
			if (Integer.valueOf(arrMap.get("indPerfilAsignado").toString()) == 1) {
				resultado += arrMap.get("oidRol").toString().concat(";");
		}
		}

		log.info("Entro a AjaxServiceImpl - getListaUsuarioSICCSeleccionados(String)");
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLocalesEDUByPaisEmpresa(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getLocalesEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa) {
		LabelValue[] listaLocales = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("estadoActividad", Constants.ESTADO_ENTIDAD_ACTIVO);
			List lista = parametroEDUGenericoDAO.getLocalByCriteria(criteria);

			if (lista != null && lista.size() > 0) {
				listaLocales = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					Base base = (Base) lista.get(i);
					LabelValue lv = new LabelValue(base.getDescripcion(),
							base.getCodigo());
					listaLocales[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaLocales = new LabelValue[1];
				listaLocales[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaLocales;
	}

	public LabelValue[] getLocalesEDUByPaisEmpresaRegion(String codigoPais,
			String codigoEmpresa, String codigoRegion) {
		LabelValue[] listaLocales = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("estadoActividad", Constants.ESTADO_ENTIDAD_ACTIVO);
			List lista = parametroEDUGenericoDAO.getLocalByCriteria(criteria);

			if (lista != null && lista.size() > 0) {
				listaLocales = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					Base base = (Base) lista.get(i);
					LabelValue lv = new LabelValue(base.getDescripcion(),
							base.getCodigo());
					listaLocales[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaLocales = new LabelValue[1];
				listaLocales[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaLocales;
	}

	public String getLongitudCodCliente(String codigoPais) {
		log.debug("getLongitudCodCliente ");
		String lon = parametroEDUGenericoDAO.getLongitudCodCliente(codigoPais);
		return lon == null ? "0" : lon;
	}

	public String getLongitudCodInstructora(String codigoPais) {
		log.debug("getLongitudCodInstructora ");
		String lon = parametroEDUGenericoDAO
				.getLongitudCodInstructora(codigoPais);
		return lon == null ? "0" : lon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLongitudTipoDocumento(java.lang
	 * .String, java.lang.String)
	 */
	public String getLongitudTipoDocumento(String oidPais,
			String oidTipoDocumento) {
		return mantenimientoMAEClienteDAO.getLongitudTipoDocumento(oidPais,
				oidTipoDocumento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getLongitudTipoDocumentoByCodigo
	 * (java.lang.String, java.lang.String)
	 */
	public String getLongitudTipoDocumentoByCodigo(String oidPais,
			String codigoTipoDocumento) {
		return mantenimientoSGRGenericoDAO.getLongitudTipoDocumentoByCodigo(
				oidPais, codigoTipoDocumento);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaByCentro(java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue[] getMapaByCentro(String centro) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("centro", centro);
		try {
			List periodos = interfazSiCCDAO.getMapaByCentro(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaCentroDistbyLineaList(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getMapaCentroDistbyLineaList(String codigoCentro,
			String codigoLinea) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codigoCentro)
				&& !codigoCentro.equals("Todos")) {
			try {
				criteria.put("oidCentro", codigoCentro);
				criteria.put("codLinea", codigoLinea);
				criteria.put("oidLinea", mantenimientoAPESubLineaArmadoDAO
						.getOidLineaArmadobyCodigo(criteria));
				criteria.put("nombreTablaMapaCentroDistribucionCabec",
						Constants.TABLA_MAPA_CENTRO_DISTRIBUCION_CAB);

				List lineaArmado = mantenimientoAPEControlBalanceoDAO
						.getMapaCentroDistbyLineaList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];
					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);
						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaCentroDistribucion(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getMapaCentroDistribucion(String codigoPais,
			String codigoCentroDistribucion, String codigoIdiomaISO) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoCentroDistribucion", codigoCentroDistribucion);
		criteria.put("codigoIdiomaISO", codigoIdiomaISO);

		try {

			List mapaList = procesoAPEImportarOrdenAnaquelesDAO
					.getMapaCentroDistribucion(criteria);

			if (mapaList != null && mapaList.size() > 0) {
				result = new LabelValue[mapaList.size()];
				for (int i = 0; i < mapaList.size(); i++) {
					Base mapa = (Base) mapaList.get(i);
					LabelValue lv = new LabelValue(mapa.getDescripcion(),
							mapa.getCodigo());
					result[i] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaCentroDistribucionComboList
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getMapaCentroDistribucionComboList(String codPais,
			String codCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codCentro) && !codCentro.equals("Todos")) {
			try {

				criteria.put("codigoPais", codPais);
				criteria.put("oidPais", reporteDAO.getOidString(
						"getOidPaisByCodigoPais", criteria));
				criteria.put("oidCentro", codCentro);
				criteria.put("nombreTablaMapaCentroDistribucionCab",
						Constants.TABLA_MAPA_CENTRO_DISTRIBUCION_CAB);
				List lineaArmado = mantenimientoAPEOrdenAnaquelesDAO
						.getMapaCentroDistribucionComboList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);

						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaCentroDistribucionListar
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getMapaCentroDistribucionListar(String codPais,
			String codCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		String oidCentro = "";

		if (StringUtils.isNotBlank(codCentro) && !codCentro.equals("Todos")) {
			try {
				log.debug("Ingreso a valida si no es blanco CodCentro");
				criteria.put("codigoPais", codPais);
				criteria.put("codCentro", codCentro);
				criteria.put("oidPais", reporteDAO.getOidString(
						"getOidPaisByCodigoPais", criteria));
				oidCentro = mantenimientoAPEUnidadesAdministrativasDAO
						.getOidCentroDistribucionPais(criteria);
				criteria.put("oidCentro", oidCentro);
				List mapaCDlistar = mantenimientoAPETiposAnaquelesMapaCDDAO
						.getMapaCentroDistribucionList(criteria);

				if (mapaCDlistar != null && mapaCDlistar.size() > 0) {
					result = new LabelValue[mapaCDlistar.size()];

					for (int i = 0; i < mapaCDlistar.size(); i++) {
						Base mapacd = (Base) mapaCDlistar.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(mapacd.getDescripcion(),
								mapacd.getCodigo());
						result[i] = lv;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaOrdenComboList(java.lang
	 * .String)
	 */
	public LabelValue[] getMapaOrdenComboList(String codMapaZona) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codMapaZona) && !codMapaZona.equals("Todos")) {
			try {
				criteria.put("nombreTablaOrdenAnaquelesCab",
						Constants.TABLA_ORDEN_ANAQUEL_CAB);
				// Obteniendo el oid del Mapa Zona
				criteria.put("codMapaZona", codMapaZona);
				criteria.put("oidMapaZona", mantenimientoAPEOrdenAnaquelesDAO
						.getOidMapaZonaByCodMapaZona(criteria));
				List lineaArmado = mantenimientoAPEOrdenAnaquelesDAO
						.getMapaOrdenComboList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);

						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaZonaCentroDistribucion(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getMapaZonaCentroDistribucion(String oidMapaCD,
			String codigoIdiomaISO) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("oidMapaCD", oidMapaCD);
		criteria.put("codigoIdiomaISO", codigoIdiomaISO);

		try {

			List mapaList = procesoAPEImportarOrdenAnaquelesDAO
					.getMapaZonaCentroDistribucion(criteria);

			if (mapaList != null && mapaList.size() > 0) {
				result = new LabelValue[mapaList.size()];
				for (int i = 0; i < mapaList.size(); i++) {
					Base mapa = (Base) mapaList.get(i);
					LabelValue lv = new LabelValue(mapa.getDescripcion(),
							mapa.getCodigo());
					result[i] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaZonaListComboList(java.lang
	 * .String)
	 */
	public LabelValue[] getMapaZonaComboList(String codMapaCentro,
			String codCentro) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(codMapaCentro)
				&& !codMapaCentro.equals("Todos")
				&& StringUtils.isNotBlank(codCentro)
				&& !codCentro.equals("Todos")) {
			try {
				criteria.put("nombreTablaMapaZonaCab",
						Constants.TABLA_MAPA_ZONA_CAB);
				// Obteniendo el oid del Mapa Zona
				criteria.put("oidCentro", codCentro);
				criteria.put("codMapCentrDist", codMapaCentro);
				criteria.put("oidMapaCentro",
						mantenimientoAPEMapaZonaLineaArmadoDAO
								.getOidMapaCDxOidCentro(criteria));
				List lineaArmado = mantenimientoAPEOrdenAnaquelesDAO
						.getMapaZonaComboList(criteria);

				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];

					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);

						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaZonaListar(java.lang.String)
	 */
	public LabelValue[] getMapaZonaListar(String codPais, String codCentro,
			String codMapaCD) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		String oidMapaCD = "";

		if (StringUtils.isNotBlank(codMapaCD) && !codMapaCD.equals("")) {
			try {
				log.debug("Ingreso a valida si no es blanco codMapaCD");
				criteria.put("codMapCentrDist", codMapaCD);
				oidMapaCD = mantenimientoAPETiposAnaquelesMapaCDDAO
						.getOidMapaCentroDistribucion(criteria);
				criteria.put("oidMapaCD", oidMapaCD);

				List mapaZonaList = mantenimientoAPEMapaZonaLineaArmadoDAO
						.getMapaZonaxOidMapaCD(criteria);

				if (mapaZonaList != null && mapaZonaList.size() > 0) {
					result = new LabelValue[mapaZonaList.size()];

					for (int i = 0; i < mapaZonaList.size(); i++) {
						Base mapaZona = (Base) mapaZonaList.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								mapaZona.getDescripcion(), mapaZona.getCodigo());
						result[i] = lv;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMapaZonasByCentro(java.lang.
	 * String, java.lang.String)
	 */
	public LabelValue[] getMapaZonasByCentro(String codigoPais, String centro) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("centro", centro);
		try {
			List periodos = interfazSiCCDAO.getMapaZonasByCentro(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMensajeCalificacion(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public String getMensajeCalificacion(String codigoEstadoEntrega,
			String codigoNovedad, String calificacion) {
		Map criteria = new HashMap();
		String s = null;
		criteria.put("codigoEstadoEntrega", codigoEstadoEntrega);
		criteria.put("codigoNovedad", codigoNovedad);
		criteria.put("calificacion", calificacion);
		List l = procesoSTOEjecucionValidacionesDAO
				.getNovedadesAccionesOrdenTransporte(criteria);
		if (l.size() != 0) {
			try {
				s = ((Map) l.get(0)).get("mensaje").toString();
			} catch (Exception e) {
			}
		}
		return s;
	}

	public String getMensajeReporteGuiaProductos(String codigoPais,
			String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		String data = reporteDAO.getMensajeReporteGuiaProductos(criteria);
		if (data.equals(Constants.NUMERO_CERO)) {
			return null;
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMensajeReporteRecuperaciones
	 * (java.lang.String, java.lang.String)
	 */
	public String getMensajeReporteRecuperaciones(String codigoPais,
			String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		String data = reporteDAO.getMensajeReporteRecuperaciones(criteria);
		if (data.equals(Constants.NUMERO_CERO)) {
			return null;
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMensajeReporteReemplazos(java
	 * .lang.String, java.lang.String)
	 */
	public String getMensajeReporteReemplazos(String codigoPais,
			String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		String data = reporteDAO.getMensajeReporteReemplazos(criteria);
		if (data.equals(Constants.NUMERO_CERO)) {
			return null;
		}
		return data;
	}

	public String getMensajexValorMaximoOferta(String codigoPais,
			String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		String data = reporteDAO.getMensajexValorMaximoOferta(criteria);
		if (data.equals(Constants.NUMERO_CERO)) {
			return null;
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.privilege.service.AjaxService#getMenu(java.lang.String,
	 * java.lang.String)
	 */
	public String[] getMenu(String codigoMenu, String codigoIdioma) {
		String[] result = null;

		Menu menu = null;

		try {
			menu = menuDAO.getMenu(codigoMenu, codigoIdioma);
			if (menu != null) {
				result = new String[] { menu.getCodigo(), menu.getDescripcion() };
			}
		} catch (ObjectRetrievalFailureException ignore) {
		}

		return result;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getMessageRangoFechaActividad(java
	 * .lang.String, java.lang.String)
	 */
	public String getMessageRangoFechaActividad(String campanhaActividad,
			String fechaActividad) {
		String message = "";
		String fechaInicial = "";
		String fechaFinal = "";
		Map map = new HashMap();
		map.put("campanhaActividad", campanhaActividad);

		List listRango = mantenimientoMENIngresoGerenteZonalesDAO
				.getRangoFechaActividad(map);
		if (listRango.size() > 0) {
			Map m = (Map) listRango.get(0);
			fechaInicial = (String) m.get("fechaInicial");
			fechaFinal = (String) m.get("fechaFinal");
		} else {
			message = getKeyMessage("mantenimientoMENIngresoGerenteZonalesForm.no.existe.fechas");
			log.debug("message no fechas " + message);
			return message;
		}
		map.put("fechaActividad", fechaActividad);
		// vemos si existe la fecha en el ranfo
		listRango = mantenimientoMENIngresoGerenteZonalesDAO
				.getRangoFechaActividad(map);
		if (listRango.size() == 0) {
			// no existe armo el mensaje
			String[] fechas = new String[] { fechaActividad, fechaInicial,
					fechaFinal };
			message = messageSource
					.getMessage(
							"mantenimientoMENIngresoGerenteZonalesForm.no.rango.fechas",
							fechas, new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
		}
		log.debug("message " + message);
		return message;
	}

	/**
	 * @return Returns the messageSource.
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public String getMessageZonasAprobadasNoProcesadas(String campanhaActividad,
			String fechaActividad){
		String message = "";
		String fechaInicial = "";
		String fechaFinal = "";
		Map map = new HashMap();
		map.put("campanhaActividad", campanhaActividad);
		map.put("fechaActividad", fechaActividad);
		
		Integer cantidad = mantenimientoMENIngresoGerenteZonalesDAO
				.getVerificaZonasAprobadasNoProcesadas(map);
		if (cantidad!=null && cantidad > 0) {
			message = getKeyMessage("mantenimientoMENIngresoGerenteZonalesForm.zonas.aprobadas.no.procesadas");
			log.debug("message " + message);
			return message;
		}		
		return message;
	}

	public LabelValue[] getMotivosRechazo(String codigoPais,
			String tipoDocumento) {

		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);

		List motivosRechazoList = procesoSTOLevantamientoErroresValidacionDAO
				.getMotivosRechazo(criteria);
		try {
			if (motivosRechazoList != null && motivosRechazoList.size() > 0) {

				result = new LabelValue[motivosRechazoList.size() + 1];
				result[0] = new LabelValue("", "");
				for (int i = 0; i < motivosRechazoList.size(); i++) {
					Base motivRechazo = (Base) motivosRechazoList.get(i);
					LabelValue lv = new LabelValue(
							motivRechazo.getDescripcion(),
							motivRechazo.getCodigo());
					result[i + 1] = lv;
				}
			}
		}

		catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNextPeriodoByCupProgPerio(java
	 * .lang.String, java.lang.String)
	 */
	public String getNextPeriodoByCupProgPerio(String codigoPais,
			String codigoPrograma) {
		// TODO Auto-generated method stub
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", codigoPrograma);
		return mantenimientoDAO.getNextPeriodoByCupProgPerio(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNivelActualEjecutiva(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getNivelActualEjecutiva(String codigoPais,
			String codigoMarca, String codigoCanal, String tipoComisionista,
			String anioInicTramo, String codTramo, String codigoCliente) {
		String nivelActual = "";
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoMarca", codigoMarca);
		params.put("codigoCanal", codigoCanal);
		params.put("tipoComisionista", tipoComisionista);
		params.put("anioInicTramo", anioInicTramo);
		params.put("codTramo", codTramo);
		params.put("codigoClienteBuscar", codigoCliente);
		nivelActual = mantenimientoCOMCalificacionComisionDAO
				.getNivelActualEjecutiva(params);
		log.debug("nivel actual " + nivelActual);
		return nivelActual != null ? nivelActual : " ";
	}

	public LabelValue[] getNivelesByConcurso(final String numeroConcurso,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(numeroConcurso)
				&& StringUtils.isNotBlank(numeroConcurso)) {
			Map criteria = new HashMap();

			criteria.put("numeroConcurso", numeroConcurso);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				List concursoList = interfazSiCCDAO.getLista(
						"getNivelesByConcurso", criteria);
				if (concursoList != null && concursoList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[concursoList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < concursoList.size(); i++) {
							Base periodo = (Base) concursoList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[concursoList.size()];
						for (int i = 0; i < concursoList.size(); i++) {
							Base concurso = (Base) concursoList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNivelesConcurso(java.lang.String
	 * , java.lang.String)
	 */
	public LabelValue[] getNivelesConcurso(String codigoPais,
			String numeroConcurso) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		try {
			log.debug("Ingreso getNivelesConcurso");
			criteria.put("numeroConcurso", numeroConcurso);
			criteria.put("codigoPais", codigoPais);

			List versionesList = mantenimientoINCCampanaDespachoDiferidaDAO
					.getNivelesConcurso(criteria);

			if (versionesList != null && versionesList.size() > 0) {
				result = new LabelValue[versionesList.size() + 1];
				result[0] = new LabelValue("", "");

				for (int i = 0; i < versionesList.size(); i++) {
					Base verisones = (Base) versionesList.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(verisones.getDescripcion(),
							verisones.getCodigo());
					result[i + 1] = lv;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNombreCliente(java.lang.String)
	 */
	public String getNombreCliente(String codigoCliente) {

		String nombreCliente = new String();
		Map criteria = new HashMap();

		criteria.put("codCliente", codigoCliente);

		try {

			if (procesoSTOEjecucionValidacionesDAO
					.getNombreConsultora(criteria) != null) {
				nombreCliente = procesoSTOEjecucionValidacionesDAO
						.getNombreConsultora(criteria);
			} else {
				nombreCliente = null;
			}
		} catch (DataAccessException ignore) {

		}

		return nombreCliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNombreProgramaProceso(java.lang
	 * .String)
	 */
	public String getNombreProgramaProceso(String codigoProceso) {
		return mantenimientoMENIngresoGerenteZonalesDAO
				.getNombreProgramaProceso(codigoProceso);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNumDocumentoIdentByCodigoCliente
	 * (java.lang.String, java.lang.String)
	 */
	public String getNumDocumentoIdentByCodigoCliente(String codigoCliente,
			String codigoTipoDocu) {
		return mantenimientoSGRGenericoDAO.getNumDocumentoIdentByCodigoCliente(
				codigoCliente, codigoTipoDocu);
	}

	public LabelValue[] getNumeroConcursoList(String codPais, String codMarca,
			String periodo) {
		LabelValue[] lv = null;
		int i = 0;
		Map criteria = new HashMap();
		criteria.put("codPais", codPais);
		criteria.put("codMarca", codMarca);
		criteria.put("periodo", periodo);
		criteria.put("valObservacion", Constants.VAL_OBSE_PROGRAMA_LIDERES);
		criteria.put("indicadorActividad", Constants.IND_ACTI_UNO);
		List NumeroConcursoList = mantenimientoLIDFactorPuntajeDAO
				.getNumeroConcursoList(criteria);
		log.debug("lista en ajax : " + NumeroConcursoList.size());
		if (NumeroConcursoList != null && NumeroConcursoList.size() > 0) {
			lv = new LabelValue[NumeroConcursoList.size()];
			for (Iterator iter = NumeroConcursoList.iterator(); iter.hasNext();) {
				LabelValue labelValue = (LabelValue) iter.next();
				labelValue.setValue(labelValue.getLabel() + "  "
						+ labelValue.getValue());
				lv[i++] = labelValue;
			}
		}

		return lv;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNumeroPedidosByLote(java.lang
	 * .String)
	 */
	public LabelValue getNumeroPedidosByLote(String id) {
		LabelValue result = new LabelValue();
		if (StringUtils.isNotBlank(id)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", StringUtils.split(id, "-")[0]);
			criteria.put("codigoPeriodo", StringUtils.split(id, "-")[1]);
			result.setValue(mantenimientoOCRPedidoControlFacturacionDAO
					.getNumeroPedidosByLote(criteria));
			result.setLabel(mantenimientoOCRPedidoControlFacturacionDAO
					.getNumeroLoteByPk(criteria));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNumeroRegistrosSTO(java.lang
	 * .String, java.lang.String)
	 */
	public String getNumeroRegistrosSTO(String codigoPais,
			String tipoDocumento, String tipoLinea) {
		log.info("Entro a AjaxServiceImpl - getNumeroRegistrosSTO(java.lang.String, java.lang.String, java.lang.String)");

		// -- pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);

		// -- Logica
		String resultado = procesoSTOLevantamientoErroresValidacionDAO
				.getNumeroRegistroMinMaxSTO(criteria);
		if (tipoLinea.equals("1")) {
			resultado = resultado.split(",")[0];
		} else {
			resultado = resultado.split(",")[1];
		}

		log.info("Salio a AjaxServiceImpl - getNumeroRegistrosSTO(java.lang.String, java.lang.String, java.lang.String) - Resultado:"
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getNumeroVersionesFaltanteDia(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getNumeroVersionesFaltanteDia(final String codigoPais,
			final String fechaFacturacion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(fechaFacturacion)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("fechaFacturacion", fechaFacturacion);

			try {
				List lista = interfazSiCCDAO
						.getNumeroVersionesFaltanteDia(criteria);
				if (lista != null && lista.size() > 0) {
					result = new LabelValue[lista.size()];
					for (int i = 0; i < lista.size(); i++) {
						Base region = (Base) lista.get(i);

						// Construimos la descripcion
						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("1", "1");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public String getNumPeriodoEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa, String codigoCurso) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		criteria.put("codigoCurso", codigoCurso);
		String numPeriodo = parametroEDUGenericoDAO.getNumPeriodo(criteria);
		return StringUtils.isEmpty(numPeriodo) ? "0" : numPeriodo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getObtenerNivelAgrpOlas(java.lang
	 * .String, java.lang.String)
	 */
	public String[] getObtenerNivelAgrpOlas(String codPais, String codCentro) {

		String[] result = new String[2];
		String oidCentro = "";
		Base base = null;

		Map criteria = new HashMap();

		criteria.put("codigoPais", codPais);
		criteria.put("codCentro", codCentro);
		criteria.put("oidPais",
				reporteDAO.getOidString("getOidPaisByCodigoPais", criteria));
		oidCentro = mantenimientoAPEUnidadesAdministrativasDAO
				.getOidCentroDistribucionPais(criteria);
		criteria.put("oidCentro", oidCentro);

		List lista = mantenimientoAPEUnidadesAdministrativasDAO
				.getObtenerNivelOlas(criteria);

		if (lista.size() > 0) {
			base = (Base) lista.get(0);
		} else {
			base = new Base();
			base.setCodigo("2");
			base.setDescripcion("Region");
		}

		result[0] = base.getCodigo();
		result[1] = base.getDescripcion();

		return result;
	}

	public LabelValue[] getOpcionesMenuAsignadas(String codigoMenu,
			String opcionVacia) {
		LabelValue[] result = null;
		if (StringUtils.isNotBlank(codigoMenu)) {
			try {
				List opcionesAsignadas = menuDAO
						.getMenuOpcionesAsignadas(codigoMenu);
				if (opcionesAsignadas != null && opcionesAsignadas.size() > 0) {
					result = new LabelValue[opcionesAsignadas.size()];

					// Creamos una primera opcin vaca
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < opcionesAsignadas.size(); i++) {
						MenuOpciones menuOpciones = (MenuOpciones) opcionesAsignadas
								.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								menuOpciones.getDesMenuOpciones(),
								menuOpciones.getCodigoOpciones());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					if (!opcionVacia.equalsIgnoreCase("")) {
						result = new LabelValue[1];
						result[0] = new LabelValue("", "");
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

	}

	public LabelValue[] getOpcionesMenuFaltantes(String codigoMenu,
			String opcionVacia) {
		LabelValue[] result = null;
		if (codigoMenu == null) {
			codigoMenu = "";
		}
		try {
			List opcionesFaltantes = menuDAO
					.getMenuOpcionesFaltantes(codigoMenu);
			if (opcionesFaltantes != null && opcionesFaltantes.size() > 0) {
				result = new LabelValue[opcionesFaltantes.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < opcionesFaltantes.size(); i++) {
					MenuOpciones menuOpciones = (MenuOpciones) opcionesFaltantes
							.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(
							menuOpciones.getDesMenuOpciones(),
							menuOpciones.getCodigoOpciones());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				if (!opcionVacia.equalsIgnoreCase("")) {
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getOpcionMensaje(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getOpcionMensaje(String codigoPais,
			String codigoEmpresa, String codigoCurso) {
		LabelValue[] listaOpciones = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("codigo", codigoCurso);
			List lista = mantenimientoEDUCursoCapacitacionDAO
					.getCursosCapacitacionByCriteria(criteria);
			if (lista != null && lista.size() > 0) {

				CursoCapacitacion cursoCapacitacion = getCursoCapacitacion(
						lista, codigoCurso);

				int numeroInvi = new Integer(
						cursoCapacitacion.getNumeroCampanhaInvitacion())
						.intValue();
				if (numeroInvi > 0) {
					listaOpciones = new LabelValue[numeroInvi];
					for (int i = 0; i < numeroInvi; i++) {
						String label = getKeyMessage("mantenimientoEDUMensajeForm.opcion."
								+ (i + 1));
						listaOpciones[i] = new LabelValue(
								new Integer(i + 1).toString() + label,
								new Integer(i + 1).toString());
					}
				} else {
					listaOpciones = new LabelValue[1];
					listaOpciones[0] = new LabelValue("", "");
				}

			} else {
				// Creamos una primera opcin vaca
				listaOpciones = new LabelValue[1];
				listaOpciones[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaOpciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getOperacionesDirectorio(java.lang
	 * .String)
	 */
	public LabelValue[] getOperacionesDirectorio(String codigoCliente,
			String oidCliente) {
		LabelValue[] result = null;
		LabelValue[] resultAux = null;
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("oidCliente", oidCliente);
		int pos = 0;
		try {
			List operaciones = mantenimientoZONDirectorioDAO
					.getDirectorioVentas(criteria);
			if (operaciones != null && operaciones.size() > 0) {
				resultAux = new LabelValue[operaciones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				boolean add = false;
				for (int i = operaciones.size() - 1; i >= 0; i--) {
					Map map = (Map) operaciones.get(i);
					// Construimos la descripcion
					String codigoOperacion = (String) map
							.get("codigoOperacion");
					String nombreOperacion = (String) map
							.get("nombreOperacion");
					add = true;
					log.debug("codigoOperacion " + codigoOperacion);
					for (int k = 0; k < pos; k++) {
						LabelValue lv = resultAux[k];
						if (codigoOperacion.equals(lv.getValue())) {
							add = false;
							break;
						}
					}

					if (add) {
						LabelValue lv = new LabelValue(nombreOperacion,
								codigoOperacion);
						resultAux[pos] = lv;
						pos++;
					}

				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}

			result = new LabelValue[pos];
			for (int i = 0; i < pos; i++) {
				result[i] = resultAux[i];
			}

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	public LabelValue[] getOperacionesRegionesDirectorio(String[] codigoRegion) {
		LabelValue[] result = null;
		LabelValue[] resultAux = null;
		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegion[0]);
		int pos = 0;
		try {
			List operaciones = mantenimientoZONDirectorioDAO
					.getDirectorioVentasDetalle(criteria);
			if (operaciones != null && operaciones.size() > 0) {
				resultAux = new LabelValue[operaciones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				boolean add = false;
				for (int i = operaciones.size() - 1; i >= 0; i--) {
					Map map = (Map) operaciones.get(i);
					// Construimos la descripcion
					String codigoOperacion = (String) map.get("codigoCargo");
					String nombreOperacion = (String) map.get("nombreCargo");
					add = true;
					log.debug("codigoOperacion " + codigoOperacion);
					for (int k = 0; k < pos; k++) {
						LabelValue lv = resultAux[k];
						if (codigoOperacion.equals(lv.getValue())) {
							add = false;
							break;
						}
					}

					if (add) {
						LabelValue lv = new LabelValue(nombreOperacion,
								codigoOperacion);
						resultAux[pos] = lv;
						pos++;
					}

				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}

			result = new LabelValue[pos];
			for (int i = 0; i < pos; i++) {
				result[i] = resultAux[i];
			}

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getOrdenAnaqueles(java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue[] getOrdenAnaqueles(String oidMapaZonaCD,
			String codigoIdiomaISO) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("oidMapaZonaCD", oidMapaZonaCD);
		criteria.put("codigoIdiomaISO", codigoIdiomaISO);

		try {

			List mapaList = procesoAPEImportarOrdenAnaquelesDAO
					.getOrdenAnaqueles(criteria);

			if (mapaList != null && mapaList.size() > 0) {
				result = new LabelValue[mapaList.size()];
				for (int i = 0; i < mapaList.size(); i++) {
					Base mapa = (Base) mapaList.get(i);
					LabelValue lv = new LabelValue(mapa.getDescripcion(),
							mapa.getCodigo());
					result[i] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getOrdenAnaquelListar(java.lang
	 * .String)
	 */
	public LabelValue[] getOrdenAnaquelListar(String codMapaZona) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		String oidMapaZona = "";

		if (StringUtils.isNotBlank(codMapaZona) && !codMapaZona.equals("")) {
			try {
				log.debug("Ingreso a valida si no es blanco codMapaCD");
				criteria.put("codMapaZona", codMapaZona);
				oidMapaZona = mantenimientoAPETiposAnaquelesMapaCDDAO
						.getOidMapaZona(criteria);
				criteria.put("oidMapaZona", oidMapaZona);

				List ordenAnaquelList = mantenimientoAPECopiarOrdenAnaquelesDAO
						.getOrdenAnaquelList(criteria);

				if (ordenAnaquelList != null && ordenAnaquelList.size() > 0) {
					result = new LabelValue[ordenAnaquelList.size()];

					for (int i = 0; i < ordenAnaquelList.size(); i++) {
						Base ordenAnaquel = (Base) ordenAnaquelList.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								ordenAnaquel.getDescripcion(),
								ordenAnaquel.getCodigo());
						result[i] = lv;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getOrigenSTOByTipoDocumento(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getOrigenSTOByTipoDocumento(String codigoPais,
			String tipoDocumento) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);
		List origenesList = procesoSTODAO.getOrigenSTOByTipoDocumento(criteria);

		try {
			if (origenesList != null && origenesList.size() > 0) {
				result = new LabelValue[origenesList.size() + 1];
				result[0] = new LabelValue("Todos", "");
				for (int i = 0; i < origenesList.size(); i++) {
					Base estado = (Base) origenesList.get(i);
					LabelValue lv = new LabelValue(estado.getDescripcion(),
							estado.getCodigo());
					result[i + 1] = lv;
				}
			} else {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getParametroClasificacionByPaisEmpresaCursoTipoCurso(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getParametroClasificacionByPaisEmpresaCursoTipoCurso(
			String codigoPais, String codigoEmpresa, String codigoCurso,
			String tipoCurso, String indicadorVacio) {
		LabelValue[] result = null;
		ParametroClasificacion parametroClasificacion = new ParametroClasificacion();
		parametroClasificacion.setCodigoPais(codigoPais);
		parametroClasificacion.setCodigoEmpresa(codigoEmpresa);
		parametroClasificacion.setNivelCapacitacion(codigoCurso);
		parametroClasificacion.setTipoCurso(tipoCurso);

		List listParametroClasificacion = parametroEDUGenericoDAO
				.getParametroClasificacionByCriteria(parametroClasificacion);
		int tam = listParametroClasificacion.size();
		int i = 0;
		if (listParametroClasificacion != null && tam > 0) {
			Iterator it = listParametroClasificacion.iterator();
			if (Constants.SI.equals(indicadorVacio)) {
				result = new LabelValue[tam + 1];
				result[i] = new LabelValue("", "");
				while (it.hasNext()) {
					parametroClasificacion = (ParametroClasificacion) it.next();
					result[++i] = new LabelValue(
							parametroClasificacion.getNombreClasificacion(),
							parametroClasificacion.getCodigoClasificacion());
				}
			} else {
				result = new LabelValue[tam];
				while (it.hasNext()) {
					parametroClasificacion = (ParametroClasificacion) it.next();
					result[i++] = new LabelValue(
							parametroClasificacion.getNombreClasificacion(),
							parametroClasificacion.getCodigoClasificacion());
				}
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService
	 * #getParametroClasificacionByCriteria
	 * (biz.belcorp.ssicc.edu.dao.model.ParametroClasificacion)
	 */
	public LabelValue[] getParametroClasificacionByPaisEmpresaTipo(
			String codigoPais, String codigoEmpresa, String tipoClasificacion) {
		LabelValue[] result = null;
		ParametroClasificacion parametroClasificacion = new ParametroClasificacion();
		parametroClasificacion.setCodigoPais(codigoPais);
		parametroClasificacion.setCodigoEmpresa(codigoEmpresa);
		parametroClasificacion.setTipoClasificacion(tipoClasificacion);
		List listParametroClasificacion = parametroEDUGenericoDAO
				.getParametroClasificacionByCriteria(parametroClasificacion);
		int tam = listParametroClasificacion.size();
		int i = 0;
		if (listParametroClasificacion != null && tam > 0) {
			Iterator it = listParametroClasificacion.iterator();
			result = new LabelValue[tam];
			// result[i]=new LabelValue("","");
			while (it.hasNext()) {
				parametroClasificacion = (ParametroClasificacion) it.next();
				result[i++] = new LabelValue(
						parametroClasificacion.getCodigoClasificacion()
								+ " - "
								+ parametroClasificacion
										.getNombreClasificacion(),
						parametroClasificacion.getCodigoClasificacion());

			}
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getParametroRechazoByDocumento(
	 * java.lang.String, java.lang.String)
	 */
	public String getParametroRechazoByDocumento(String codigoPais,
			String tipoDocumento) {

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);

		String parametro = procesoSTOLevantamientoErroresValidacionDAO
				.getParametroRechazoByDocumento(criteria);

		return parametro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getParametroRegistroClasificacion
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getParametroRegistroClasificacion(String codigoPais,
			String codigoEmpresa, String codigoProceso) {
		List lista = new ArrayList();
		// LabelValue[] listaClasificacion = null;
		try {
			ParametroRegistroClasificacion parametros = new ParametroRegistroClasificacion();
			parametros.setCodigoPais(codigoPais);
			parametros.setCodigoEmpresa(codigoEmpresa);
			parametros.setCampanaProceso(codigoProceso);
			lista = parametroEDUGenericoDAO
					.getParametroRegistroClasificacionByCriteria(parametros);
			/*
			 * listaClasificacion = new LabelValue[lista.size()]; for (int i =
			 * 0; i < lista.size(); i++) { ParametroRegistroClasificacion base =
			 * (ParametroRegistroClasificacion) lista.get(i); LabelValue lv =
			 * new LabelValue(base.getNombreClasificacion(),
			 * base.getCodigoClasificacion()); listaClasificacion[i] = lv; }
			 */

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return lista;
	}

	public String getPeriodoDefaultByPaisCanal(String codigoPais,
			String codigoCanal) {
		String result = "";
		try {
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoCanal", codigoCanal);

			result = interfazSiCCDAO.getPeriodoDefaultByPaisCanal(params);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public String getPeriodoDefaultByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal) {
		String result = "";
		try {
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoMarca", codigoMarca);
			params.put("codigoCanal", codigoCanal);

			result = interfazSiCCDAO.getPeriodoDefaultByPaisMarcaCanal(params);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public String getPeriodoDefaultByPaisMarcaCanalAcceso(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoAcceso) {
		String result = "";
		try {
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoMarca", codigoMarca);
			params.put("codigoCanal", codigoCanal);
			params.put("codigoAcceso", codigoAcceso);

			result = interfazSiCCDAO
					.getPeriodoDefaultByPaisMarcaCanalAcceso(params);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodoFechaCampanyaActiva()
	 */
	public String[] getPeriodoFechaCampanyaActiva() {
		String[] result = null;
		Map datos = interfazSiCCDAO.getPeriodoFechaCampanyaActiva();

		if (datos != null) {
			result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
		} else {
			result = new String[] { "", "" };
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodoFechaCampanyaActivaSF()
	 */
	public String[] getPeriodoFechaCampanyaActivaSF(String codigoPais, String conExterna) {
		String[] result = null;
		String valid = StringUtils.EMPTY;
		Map criteria = new HashMap();
		criteria.put("codigoPais", (StringUtils.isBlank(codigoPais) ? StringUtils.EMPTY : codigoPais));
		criteria.put("codigoConexionExterna", (StringUtils.isBlank(conExterna) ? StringUtils.EMPTY : conExterna));
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		Map datos = interfazSiCCDAO.getPeriodoFechaCampanyaActivaSF(criteria);

		if (datos != null) {
			result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
		} else {
			result = new String[] { "", "" };
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCargoBaseById(String codigoCargo)
	 */
	public String getCargoBaseById(String codigoCargo){
		String result = null;
		String valid = StringUtils.EMPTY;
		Map criteria = new HashMap();
		criteria.put("codigoCargo", (StringUtils.isBlank(codigoCargo) ? StringUtils.EMPTY : codigoCargo));
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		
		Map datos = interfazSiCCDAO.getCargoBaseById(criteria);
		
		if(StringUtils.isBlank(MapUtils.getString(datos, "codigo", ""))){
			result = Constants.NO;
		}else
			result = Constants.SI;
		
		return result;
	}
	

	public String getPeriodoFin(String codigoRango) {
		String result = "";
		try {
			result = interfazSiCCDAO.getPeriodoFin(codigoRango);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosByPaisMarcaCanal(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */

	public String getPeriodoInicio(String codigoRango) {
		String result = "";
		try {
			result = interfazSiCCDAO.getPeriodoInicio(codigoRango);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodoNivelDespachoDiferido
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getPeriodoNivelDespachoDiferido(String codigoPais,
			String numeroConcurso, String nivel) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("numeroConcurso", numeroConcurso);
		criteria.put("nivel", nivel);

		Map result = mantenimientoINCCampanaDespachoDiferidaDAO
				.getPeriodoNivelDespachoDiferido(criteria);
		String periodoInicial = "";
		if (result.get("periodoInicial") != null) {
			periodoInicial = result.get("periodoInicial").toString();
		}

		String resultado = periodoInicial + "__"
				+ result.get("indicadorHabilitado").toString() + "__"
				+ result.get("periodoDespacho").toString() + "__"
				+ result.get("periodoDespachoSiguiente").toString();

		return resultado;
	}

	public String getPeriodoReclamo(String codigo, String periodoActivo) {
		log.debug("__getPeriodoReclamo");
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigo);
		criteria.put("codigoPeriodo", periodoActivo);
		return mantenimientoRECDigitacionCDRDAO.getPeriodoReclamo(criteria);
	}

	public LabelValue[] getPeriodosByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		try {
			List periodos = interfazSiCCDAO
					.getPeriodosByPaisMarcaCanal(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getPeriodosByPaisMarcaCanalAcceso(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoAcceso) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("codigoAcceso", codigoAcceso);

		try {
			List periodos = interfazSiCCDAO
					.getPeriodosByPaisMarcaCanalAcceso(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getPeriodosByPaisMarcaCanalOrderFechaFin(
			String codigoPais, String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		try {
			List periodos = interfazSiCCDAO
					.getPeriodosByPaisMarcaCanalOrderFechaFin(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getPeriodosByPaisMarcaCanalRangos(String codigoPais,
			String codigoMarca, String codigoCanal, String rangoInicial,
			String rangoFinal) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("rangoInicial", rangoInicial);
		criteria.put("rangoFinal", rangoFinal);

		try {
			List periodos = interfazSiCCDAO
					.getPeriodosByPaisMarcaCanalRangos(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = (LabelValue[]) periodos.toArray(new LabelValue[0]);
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getPeriodosComplementoFacturasVentaDirecta(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosComplementoFacturasVentaDirecta(
			String codigoPais, String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);

			try {
				List periodos = interfazSiCCDAO
						.getPeriodosComplementoFacturasVentaDirecta(criteria);
				if (periodos != null && periodos.size() > 0) {
					result = new LabelValue[periodos.size()];

					// Creamos una primera opcin vaca
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < periodos.size(); i++) {
						Base periodo = (Base) periodos.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosDefaultByPaisMarcaCanal
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosDefaultByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		try {
			List periodos = interfazSiCCDAO.getPeriodosDefaultByPMC(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getPeriodosDefaultByPMC(String codigoPais,
			String codigoMarca, String codigoCanal) {

		Map criteria = new HashMap();
		LabelValue[] result = null;

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		List listaPeriodos = interfazSiCCDAO.getPeriodosDefaultByPMC(criteria);

		try {
			if (listaPeriodos != null && listaPeriodos.size() > 0) {

				result = new LabelValue[listaPeriodos.size()];

				for (int i = 0; i < listaPeriodos.size(); i++) {
					Base periodo = (Base) listaPeriodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosDocumentosAnulados(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosDocumentosAnulados(String codigoPais,
			String codigoMarca, String codigoCanal) {
		return getPeriodosByPaisMarcaCanal(codigoPais, codigoMarca, codigoCanal);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosDocumentosAnulados(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosDocumentosAnuladosAcceso(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoAcceso) {
		return getPeriodosFacturasVentaDirecta(codigoPais, codigoMarca,
				codigoCanal, codigoAcceso);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosFacturasCabecera(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosFacturasCabecera(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoAcceso) {
		return getPeriodosFacturasVentaDirecta(codigoPais, codigoMarca,
				codigoCanal, codigoAcceso);
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosFacturasVentaDirecta
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosFacturasVentaDirecta(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoAcceso) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("codigoAcceso", codigoAcceso);

		try {
			List periodos = interfazSiCCDAO
					.getPeriodosFacturasVentaDirecta(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosProductosNoAtendidos
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosProductosNoAtendidos(String codigoPais,
			String oidConcurso) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("oidConcurso", oidConcurso);

		List origenesList = procesoINCDarPorAtendidoBolsaFaltantesDAO
				.getPeriodosProductosNoAtendidos(criteria);

		try {
			if (origenesList != null && origenesList.size() > 0) {
				result = new LabelValue[origenesList.size() + 1];
				result[0] = new LabelValue("Todos", "Todos");
				for (int i = 0; i < origenesList.size(); i++) {
					Base estado = (Base) origenesList.get(i);
					LabelValue lv = new LabelValue(estado.getDescripcion(),
							estado.getCodigo());
					result[i + 1] = lv;
				}
			}
			/*
			 * else { result = new LabelValue[1]; result[0] = new
			 * LabelValue("Todos", "Todos"); }
			 */
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodosSABRentabilidadPorZona
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosSABRentabilidadPorZona(String codigoPais,
			String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		try {
			List periodos = interfazSiCCDAO
					.getPeriodosSABRentabilidadPorZona(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getPlanillaInstructora(String codigoPais,
			String codigoEmpresa, String codigoCurso, String codigoRegion,
			String codigoInstructora, String campanhaInicio) {

		LabelValue[] result = null;
		try {

			HistoricoPlanillaInstructora historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
			historicoPlanillaInstructora.setCodigoEmpresa(codigoEmpresa);
			historicoPlanillaInstructora.setCodigoPais(codigoPais);
			historicoPlanillaInstructora.setCodigoCurso(codigoCurso);
			historicoPlanillaInstructora.setCodigoRegion(codigoRegion);
			historicoPlanillaInstructora
					.setCodigoInstructora(codigoInstructora);
			// campnhanha una anterior
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("campannaProceso", campanhaInicio);
			criteria.put("numeroCampanna", new Integer("-1"));
			String campanhaAnterior = parametroEDUGenericoDAO
					.getDevuelveCampanna(criteria);

			log.debug("campanha anterior " + campanhaAnterior
					+ " campanhaInicio " + campanhaInicio);
			historicoPlanillaInstructora.setCampanhaInicio(campanhaAnterior);// dictado

			historicoPlanillaInstructora
					.setSituacionPlanilla(Constants.SITUACION_PLANILLA_ACTIVA);
			List listaPlanillaCursos = procesoEDURegistroAsistenciaDAO
					.getHistoricoPlanillaInstructora(historicoPlanillaInstructora);

			result = new LabelValue[1];
			result[0] = new LabelValue("", "");

			if (listaPlanillaCursos != null && listaPlanillaCursos.size() > 0) {
				result = new LabelValue[listaPlanillaCursos.size()];
				for (int i = 0; i < listaPlanillaCursos.size(); i++) {
					Base cursoDictado = new Base();
					historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
					historicoPlanillaInstructora = (HistoricoPlanillaInstructora) listaPlanillaCursos
							.get(i);
					cursoDictado.setCodigo(historicoPlanillaInstructora
							.getCodigoPlanilla());
					cursoDictado.setDescripcion(historicoPlanillaInstructora
							.getCodigoPlanilla());
					// Construimos la descripcion
					LabelValue lv = new LabelValue(cursoDictado.getCodigo(),
							cursoDictado.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/**
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
			String codigoInstructora, String campanhaInicio) {

		LabelValue[] result = null;
		try {

			HistoricoPlanillaInstructora historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
			historicoPlanillaInstructora.setCodigoEmpresa(codigoEmpresa);
			historicoPlanillaInstructora.setCodigoPais(codigoPais);
			historicoPlanillaInstructora.setCodigoCurso(codigoCurso);
			historicoPlanillaInstructora.setCodigoRegion(codigoRegion);
			historicoPlanillaInstructora
					.setCodigoInstructora(codigoInstructora);
			// campnhanha una anterior
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("campannaProceso", campanhaInicio);
			criteria.put("numeroCampanna", new Integer("-1"));
			String campanhaAnterior = parametroEDUGenericoDAO
					.getDevuelveCampanna(criteria);

			log.debug("campanha anterior " + campanhaAnterior
					+ " campanhaInicio " + campanhaInicio);
			historicoPlanillaInstructora.setCampanhaInicio(campanhaAnterior);// dictado

			historicoPlanillaInstructora
					.setSituacionPlanilla(Constants.SITUACION_PLANILLA_ACTIVA);
			List listaPlanillaCursos = procesoEDURegistroAsistenciaDAO
					.getHistoricoPlanillaInstructoraZona(historicoPlanillaInstructora);

			result = new LabelValue[1];
			result[0] = new LabelValue("", "");

			if (listaPlanillaCursos != null && listaPlanillaCursos.size() > 0) {
				result = new LabelValue[listaPlanillaCursos.size()];
				for (int i = 0; i < listaPlanillaCursos.size(); i++) {
					Base cursoDictado = new Base();
					historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
					historicoPlanillaInstructora = (HistoricoPlanillaInstructora) listaPlanillaCursos
							.get(i);
					cursoDictado.setCodigo(historicoPlanillaInstructora
							.getCodigoPlanilla());
					cursoDictado.setDescripcion(historicoPlanillaInstructora
							.getCodigoZona());// se le manda en descripcion su
												// zona
					// correspondiente de la planilla
					// Construimos la descripcion
					LabelValue lv = new LabelValue(
							cursoDictado.getDescripcion(),
							cursoDictado.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/**
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
			String codigoCursoDictado, String verZonas) {

		LabelValue[] result = null;
		try {

			HistoricoPlanillaInstructora historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
			historicoPlanillaInstructora.setCodigoEmpresa(codigoEmpresa);
			historicoPlanillaInstructora.setCodigoPais(codigoPais);
			historicoPlanillaInstructora.setCodigoCurso(codigoCurso);
			historicoPlanillaInstructora.setCodigoRegion(codigoRegion);
			historicoPlanillaInstructora
					.setCodigoInstructora(codigoInstructora);
			// campnhanha una anterior
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("campannaProceso", campanhaInicio);
			criteria.put("numeroCampanna", new Integer("-1"));
			String campanhaAnterior = parametroEDUGenericoDAO
					.getDevuelveCampanna(criteria);

			log.debug("campanha anterior " + campanhaAnterior
					+ " campanhaInicio " + campanhaInicio);
			historicoPlanillaInstructora.setCampanhaInicio(campanhaAnterior);// dictado

			historicoPlanillaInstructora
					.setSituacionPlanilla(Constants.SITUACION_PLANILLA_PROCESADA);
			historicoPlanillaInstructora
					.setCodigoCursoDictado(codigoCursoDictado);
			List listaPlanillaCursos = procesoEDURegistroAsistenciaDAO
					.getHistoricoPlanillaInstructoraZona(historicoPlanillaInstructora);

			result = new LabelValue[1];
			result[0] = new LabelValue("", "");

			if (listaPlanillaCursos != null && listaPlanillaCursos.size() > 0) {
				result = new LabelValue[listaPlanillaCursos.size()];
				for (int i = 0; i < listaPlanillaCursos.size(); i++) {
					Base cursoDictado = new Base();
					historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
					historicoPlanillaInstructora = (HistoricoPlanillaInstructora) listaPlanillaCursos
							.get(i);
					cursoDictado.setCodigo(historicoPlanillaInstructora
							.getCodigoPlanilla());
					if (verZonas.equals(Constants.ACTIVO)) {
						cursoDictado
								.setDescripcion(historicoPlanillaInstructora
										.getCodigoZona());// se le manda en
															// descripcion su
															// zona
						// correspondiente de la planilla
					} else {
						cursoDictado
								.setDescripcion(historicoPlanillaInstructora
										.getCodigoPlanilla());
					}

					// Construimos la descripcion
					LabelValue lv = new LabelValue(
							cursoDictado.getDescripcion(),
							cursoDictado.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelPedidosValue[] getPrecioByCodigoVenta(String codigoPais,
			String codigoProducto, String codigoPeriodo, String codigoVenta) {
		LabelPedidosValue[] result = null;

		ProductoPeriodo producto = new ProductoPeriodo();
		int cont = 1;
		String unidades = "0";
		String total = "0";

		producto.setCodigoPais(codigoPais);
		producto.setCodigoProducto(codigoProducto);
		producto.setCodigoPeriodo(codigoPeriodo);
		producto.setCodigoVenta(codigoVenta);

		log.debug("Ejecutando AjaxServiceImpl.getPrecioByCodigoVenta");

		try {

			producto = mantenimientoSSEProductoPeriodoDAO
					.getCodigoVentaProductoByCriteria(producto);

			if (producto != null) {
				result = new LabelPedidosValue[++cont];
				result[0] = new LabelPedidosValue(producto.getCodigoVenta(),
						producto.getDescripcionProducto(),
						producto.getPrecioProducto(), unidades, total);
				log.debug("ajax getPrecioByCodigoVenta " + result);
			}
		} catch (Exception e) {
			log.debug("ajax Error getPrecioByCodigoVenta " + result);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPremiosxConcurso(java.lang.String
	 * , java.lang.String)
	 */
	public LabelValue[] getPremiosxConcurso(String codigoConcurso,
			String condicionTodos) {

		log.debug("Entro a getPremiosxConcurso");
		log.debug("codigoConcurso: " + codigoConcurso);
		log.debug("condicionTodos: " + condicionTodos);

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoConcurso", codigoConcurso);

			List concursos = mantenimientoINCBloqueoPremiosDAO
					.getPremiosxConcurso(criteria);
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("", "");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getProcesoBatchBySistema(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getProcesoBatchBySistema(String codigoPais,
			String codigoSistema) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoSistema", codigoSistema);
			List interfaces = interfazDAO.getProcesoBatchBySistema(criteria);
			if (interfaces != null && interfaces.size() > 0) {
				result = new LabelValue[interfaces.size()];
				for (int i = 0; i < interfaces.size(); i++) {
					LabelValue lv = (LabelValue) interfaces.get(i);
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getProductoByCodSapyOidPais(java
	 * .lang.String, java.lang.String)
	 */
	public String[] getProductoByCodSapyOidPais(final String codSap,
			final String codPais, final String codLinea) {
		String[] result = null;
		Map criteria = new HashMap();
		criteria.put("codSap", codSap);
		criteria.put("codPais", codPais);
		criteria.put("codLinea", codLinea);

		log.debug("Ejecutando AjaxServiceImpl.getProductoByCodSapyOidPaisObject");
		try {
			EstimadoProducto productos = null;
			productos = mantenimientoAPEEstimadoProductoDAO
					.getProductoByCodSapyOidPaisObject(criteria);
			result = new String[] { productos.getCodSap(),
					productos.getDesSap(), productos.getFlagFuePed(),
					productos.getOidSap(), productos.getFlagLinAfp() };
		} catch (Exception e) {
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getProductosNoAtendidos(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getProductosNoAtendidos(String codigoPais,
			String oidConcurso, String oidPeriodo) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("oidConcurso", oidConcurso);

		if (!oidPeriodo.equalsIgnoreCase("Todos")) {
			criteria.put("oidPeriodo", oidPeriodo);
		}

		List origenesList = procesoINCDarPorAtendidoBolsaFaltantesDAO
				.getProductosNoAtendidos(criteria);

		try {
			if (origenesList != null && origenesList.size() > 0) {
				result = new LabelValue[origenesList.size() + 1];
				result[0] = new LabelValue("Todos", "Todos");
				for (int i = 0; i < origenesList.size(); i++) {
					Base estado = (Base) origenesList.get(i);
					LabelValue lv = new LabelValue(estado.getDescripcion(),
							estado.getCodigo());
					result[i + 1] = lv;
				}
			}
			/*
			 * else { result = new LabelValue[1]; result[0] = new
			 * LabelValue("Todos", "Todos"); }
			 */
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getProgramaPeriodoById(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public String getProgramaPeriodoById(String codigoPais,
			String codigoPrograma, String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", codigoPrograma);
		criteria.put("codigoPeriodo", codigoPeriodo);
		if (mantenimientoDAO.getProgramaPeriodoById(criteria) != null) {
			return Constants.NUMERO_UNO;
		} else {
			return Constants.NUMERO_CERO;
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCentroDByPaisMarcaCanal(java
	 * .lang.String)
	 */
	public LabelValue[] getRegion() {
		LabelValue[] result = null;

		// criteria.put("codigoPais", codigoPais);
		//
		// criteria.put("codigoPeriodo", "%"+periodoS);
		try {
			List periodos = interfazSiCCDAO.getRegion();
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];
				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesEDUByPaisEmpresaInstructora
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionByInstructora(String codigoPais,
			String codigoEmpresa, String instructora, final String flagTodos) {
		// TODO Auto-generated method stub
		LabelValue[] listaRegiones = null;
		try {

			RegionCursoCapacitacion regionCursoCapacitacion = new RegionCursoCapacitacion();
			regionCursoCapacitacion.setCodigoPais(codigoPais);
			regionCursoCapacitacion.setCodigoEmpresa(codigoEmpresa);
			regionCursoCapacitacion.setCodigoInstructora(instructora);
			List lista = mantenimientoEDUCursoCapacitacionDAO
					.getRegionByInstructora(regionCursoCapacitacion);
			if (lista != null && lista.size() > 0) {
				if (flagTodos
						.equals(Constants.EDU_FLAG_AJAX_COMBO_DESCRIPCION_TODOS)) {
					listaRegiones = new LabelValue[lista.size() + 1];
					listaRegiones[0] = new LabelValue("Todos", "");
				} else {
					listaRegiones = new LabelValue[lista.size()];
				}

				for (int i = 0; i < lista.size(); i++) {
					regionCursoCapacitacion = new RegionCursoCapacitacion();
					regionCursoCapacitacion = (RegionCursoCapacitacion) lista
							.get(i);
					LabelValue lv = new LabelValue(
							regionCursoCapacitacion.getCodigoRegion()
									+ "-"
									+ regionCursoCapacitacion
											.getDescripcionRegion(),
							regionCursoCapacitacion.getCodigoRegion());
					if (flagTodos
							.equals(Constants.EDU_FLAG_AJAX_COMBO_DESCRIPCION_TODOS)) {
						listaRegiones[i + 1] = lv;
					} else {
						listaRegiones[i] = lv;
				}
				}
			} else {
				// Creamos una primera opcin vaca
				listaRegiones = new LabelValue[1];
				listaRegiones[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaRegiones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionByPaisMarcaCanalSubGerencia
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionByPaisMarcaCanalSubGerencia(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoSubGerencia) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)
				&& StringUtils.isNotBlank(codigoSubGerencia)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoSubGerencia", codigoSubGerencia);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List subgerencia = interfazSiCCDAO
						.getRegionByPaisMarcaCanalSubGerencia(criteria);
				if (subgerencia != null && subgerencia.size() > 0) {
					result = new LabelValue[subgerencia.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < subgerencia.size(); i++) {
						Base sub = (Base) subgerencia.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(sub.getDescripcion(),
								sub.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesActivasCerradasByPeriodo
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesActivasCerradasByPeriodo(String codigoPais,
			String codigoPeriodo, String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);

			try {

				List regiones = interfazSiCCDAO
						.getRegionesActivasCerradasByPeriodo(criteria);

				log.debug("Tamao de las regiones 1:" + regiones.size());
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];

					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {

					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		log.debug("Tamao de las regiones 2:" + result.length);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesAllDirectorio(java.lang
	 * .String)
	 */
	public LabelValue[] getRegionesAllDirectorio(String indicadorTitular) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("indicadorTitular", indicadorTitular);
		criteria.put("indicadorActivo", Constants.ESTADO_ACTIVO);
		
		try {
			List regiones = mantenimientoMAEClienteDAO
					.getRegionesDirectorio(criteria);
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValue[regiones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(region.getDescripcion(),
							region.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getRegionesAllDirectorioMantenimientoZON(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesAllDirectorioMantenimientoZON(
			String codigoPais, String codigoConexionExterna,
			String indicadorTitular, String cargo) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		String flag = "0";
		if(StringUtils.equals(cargo, "GR") || StringUtils.equals(cargo, "GZ")){
			flag = "1";
		}
		criteria.put("codigoPais", codigoPais);
		criteria.put("indicadorTitular", indicadorTitular);
		criteria.put("indicadorActivo", Constants.ESTADO_ACTIVO);
		criteria.put("flag", flag);

		try {
			List regiones = new ArrayList();
			if(StringUtils.equals(cargo, "GZ")){
				
				if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					regiones = mantenimientoMAEClienteDAO.getRegionesDirectorioCargoGZFOX(criteria);
				else					
				regiones = mantenimientoMAEClienteDAO.getRegionesDirectorioCargoGZ(criteria);
				
			}else{
				
				if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					regiones = mantenimientoMAEClienteDAO.getRegionesDirectorioCargoFOX(criteria);
				else					
				regiones = mantenimientoMAEClienteDAO.getRegionesDirectorioCargo(criteria);				
			}
			
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValue[regiones.size()];

				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion
					String codigoTmp = region.getCodigo();
					String descripcion = region.getCodigo() + " - " + region.getDescripcion();
					
					LabelValue lv = new LabelValue(descripcion, codigoTmp);
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByPais(java.lang.String)
	 */
	public LabelValue[] getRegionesByPais(String codigoPais) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);

			try {

				List regiones = interfazSiCCDAO.getRegionesByPais(criteria);

				// Creamos una primera opcin vaca

				log.debug("Tamao de las regiones 3:" + regiones.size());
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		log.debug("Tamao de las regiones 3:" + result.length);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByPaisCanal(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getRegionesByPaisCanal(String codigoPais,
			String codigoCanal) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoCanal", codigoCanal);

			try {

				List regiones = interfazSiCCDAO
						.getRegionesByPaisCanal(criteria);

				// Creamos una primera opcin vaca

				log.debug("Tamao de las regiones 1:" + regiones.size());
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		log.debug("Tamao de las regiones 2:" + result.length);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByPaisLet(java.lang.
	 * String)
	 */
	public LabelValue[] getRegionesByPaisLet(String codigoPais) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);

			try {

				List regiones = interfazSiCCDAO.getRegionesByPaisLet(criteria);

				log.debug("Tamao de las regiones 1:" + regiones.size());
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];

					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {

					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		log.debug("Tamao de las regiones 2:" + result.length);

		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByPaisMarcaCanal(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("indicadorActivo", Constants.ESTADO_ACTIVO);

			try {
				Pais pais = paisDAO.getPais(codigoPais);
				
				List regiones = null;
				
				if(StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX))
					regiones = mantenimientoMAEClienteDAO.getRegionesDirectorioCargoFOX(criteria);
				else
					regiones = interfazSiCCDAO.getRegionesByPaisMarcaCanal(criteria);
								
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];

					// Creamos una primera opcin vaca
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getRegionesByPaisMarcaCanalPeriodo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesByPaisMarcaCanalPeriodo(String codigoPais,
			String codigoMarca, String codigoCanal, String periodo) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)
				&& StringUtils.isNotBlank(periodo)) {
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("periodo", periodo);

			try {
				List regiones = interfazSiCCDAO
						.getRegionesByPaisMarcaCanalPeriodo(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];

					// Creamos una primera opcin vaca
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByPaisMarcaCanal(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesByPaisMarcaCanalDetalle(String codigoPais,
			String codigoMarca, String codigoCanal, String tipoDetalle) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);

			try {
				List regiones = interfazSiCCDAO
						.getRegionesByPaisMarcaCanal(criteria);
				if (regiones != null && regiones.size() > 0
						&& !tipoDetalle.equalsIgnoreCase("Concurso")) {
					result = new LabelValue[regiones.size()];

					// Creamos una primera opcin vaca
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getRegionesByPaisSociedadEtapaDeuda(String codigoPais,
			String codigoSociedad, String codigoEtapaDeuda) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);

		List regionesList = procesoCOBDAO.getLista(
				"getRegionesByPaisSociedadEtapaDeuda", criteria);
		// List regionesList =
		// procesoCOBAsignacionCarteraDAO.getRegionesByPaisSociedadEtapaDeuda(criteria);
		log.debug("lista en ajax : " + regionesList.size());

		if (regionesList != null && regionesList.size() > 0) {
			result = new LabelValue[regionesList.size()];
			for (int i = 0; i < regionesList.size(); i++) {
				Base region = (Base) regionesList.get(i);
				LabelValue lv = new LabelValue(region.getDescripcion(),
						region.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	public LabelValue[] getRegionesByPaisSociedadEtapaDeudaPeriodo(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);

		log.debug("Vamos a cargar el DAO: ");
		List regionesList = procesoCOBDAO.getLista(
				"getRegionesByPaisSociedadEtapaDeudaPeriodo", criteria);
		// List zonasList =
		// procesoCOBAsignacionCarteraDAO.getZonasByPaisSociedadEtapaDeudaRegion(criteria);
		log.debug("lista en ajax : " + regionesList.size());

		if (regionesList != null && regionesList.size() > 0) {
			result = new LabelValue[regionesList.size()];
			for (int i = 0; i < regionesList.size(); i++) {
				Base region = (Base) regionesList.get(i);
				LabelValue lv = new LabelValue(region.getDescripcion(),
						region.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}

	public LabelValue[] getRegionesByPaisSubGerencia(final String codigoPais,
			final String codigoMarca, final String codigoCanal,
			final String codigoSubGerencia, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoSubGerencia)
					&& StringUtils.isNotBlank(codigoSubGerencia)) {
				criteria.put("codigoSubGerencia", codigoSubGerencia);
			} else {
				return result;
			}

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				List regionList = interfazSiCCDAO.getLista(
						"getRegionesByPaisSubGerencia", criteria);
				if (regionList != null && regionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regionList.size(); i++) {
							Base periodo = (Base) regionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regionList.size()];
						for (int i = 0; i < regionList.size(); i++) {
							Base concurso = (Base) regionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}

	public LabelValue[] getRegionesByPaisSubGerenciaMultiple(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String[] codigoSubGerencia,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			if (codigoSubGerencia.length > 0) {
				criteria.put("subGerenciaList", codigoSubGerencia);
			} else {
				return result;
			}

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				List regionList = interfazSiCCDAO.getLista(
						"getRegionesByPaisSubGerenciaMultiple", criteria);
				if (regionList != null && regionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regionList.size(); i++) {
							Base periodo = (Base) regionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regionList.size()];
						for (int i = 0; i < regionList.size(); i++) {
							Base concurso = (Base) regionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByPeriodoBasCtrlFact
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesByPeriodoBasCtrlFact(String codigoPais,
			String codigoPeriodo, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);

			try {
				List regiones = interfazSiCCDAO
						.getRegionesByPeriodoBasCtrlFact(criteria);
				if (regiones != null && regiones.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regiones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regiones.size(); i++) {
							Base periodo = (Base) regiones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base concurso = (Base) regiones.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByPeriodoIntEviPerioRegio
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesByPeriodoIntEviPerioRegio(String codigoPais,
			String codigoPeriodo, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);

			try {
				List regiones = interfazSiCCDAO
						.getRegionesByPeriodoIntEviPerioRegio(criteria);
				if (regiones != null && regiones.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regiones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regiones.size(); i++) {
							Base periodo = (Base) regiones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base concurso = (Base) regiones.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesCerradas(java.lang.String
	 * )
	 */
	public Integer getRegionesCerradas(final ArrayList codigoRegion,
			String codigoPeriodo) {
		Integer result = new Integer(0);

		if (StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();

			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoPeriodo", codigoPeriodo);

			try {

				Integer resultAux = interfazSiCCDAO
						.getNumeroRegionesCerradas(criteria);
				if (resultAux != null) {

					result = resultAux;

				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesDirectorio(java.lang
	 * .String)
	 */
	public LabelValue[] getRegionesDirectorio(String indicadorTitular) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("indicadorTitular", indicadorTitular);
		criteria.put("indicadorActivo", Constants.NUMERO_UNO);
		try {
			List regiones = mantenimientoMAEClienteDAO
					.getRegionesDirectorio(criteria);
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValue[regiones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(region.getDescripcion(),
							region.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesEDUByPaisEmpresa(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa) {
		LabelValue[] listaRegiones = null;
		try {
			RegionCursoCapacitacion region = new RegionCursoCapacitacion();
			region.setCodigoPais(codigoPais);
			region.setCodigoEmpresa(codigoEmpresa);
			List lista = mantenimientoEDUCursoCapacitacionDAO.getRegion(region);
			if (lista != null && lista.size() > 0) {
				listaRegiones = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					RegionCursoCapacitacion regionCursoCapacitacion = (RegionCursoCapacitacion) lista
							.get(i);
					LabelValue lv = new LabelValue(
							regionCursoCapacitacion.getCodigoRegion()
									+ "-"
									+ regionCursoCapacitacion
											.getDescripcionRegion(),
							regionCursoCapacitacion.getCodigoRegion());
					listaRegiones[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaRegiones = new LabelValue[1];
				listaRegiones[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaRegiones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesEDUByPaisEmpresaInstructora
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesEDUByPaisEmpresaInstructora(
			String codigoPais, String codigoEmpresa, String codigoInstructora) {
		LabelValue[] listaRegiones = null;
		try {
			MaestroInstructora instructora = getEjecutiva(codigoPais,
					codigoEmpresa, codigoInstructora);
			if (isJefaEducacion(instructora)) {
				listaRegiones = getRegionesEDUByPaisEmpresa(codigoPais,
						codigoEmpresa);
				return listaRegiones;
			}

			RegionCursoCapacitacion region = new RegionCursoCapacitacion();
			region.setCodigoPais(codigoPais);
			region.setCodigoEmpresa(codigoEmpresa);
			region.setCodigoInstructora(codigoInstructora);
			List lista = mantenimientoEDUCursoCapacitacionDAO.getRegion(region);
			if (lista != null && lista.size() > 0) {
				listaRegiones = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					RegionCursoCapacitacion regionCursoCapacitacion = (RegionCursoCapacitacion) lista
							.get(i);
					LabelValue lv = new LabelValue(
							regionCursoCapacitacion.getCodigoRegion()
									+ "-"
									+ regionCursoCapacitacion
											.getDescripcionRegion(),
							regionCursoCapacitacion.getCodigoRegion());
					listaRegiones[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaRegiones = new LabelValue[1];
				listaRegiones[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaRegiones;
	}

	public LabelValue[] getRegionesSinAsignarByPaisSociedadEtapaDeudaPeriodo(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);

		log.debug("Vamos a cargar el DAO: ");
		List regionesList = procesoCOBDAO.getLista(
				"getRegionesSinAsignarByPaisSociedadEtapaDeudaPeriodo",
				criteria);
		// List zonasList =
		// procesoCOBAsignacionCarteraDAO.getZonasByPaisSociedadEtapaDeudaRegion(criteria);
		log.debug("lista en ajax : " + regionesList.size());

		if (regionesList != null && regionesList.size() > 0) {
			result = new LabelValue[regionesList.size()];
			for (int i = 0; i < regionesList.size(); i++) {
				Base region = (Base) regionesList.get(i);
				LabelValue lv = new LabelValue(region.getDescripcion(),
						region.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionMultipleByCerrar(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionMultipleByCerrar(final String codigoPais,
			final String campanhaProceso, String condicionTodos,
			String indicadorEdit) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("campanhaProceso", campanhaProceso);
		criteria.put("indicadorEdit",
				Constants.NRO_UNO.equals(indicadorEdit) ? indicadorEdit : null);

		try {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");

			List regionList = mantenimientoFACGenericoDAO
					.getRegionesPorCerrar(criteria);
			if (regionList != null && regionList.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[regionList.size() + 1];
					result[0] = new LabelValue("Todos", "");
					for (int i = 0; i < regionList.size(); i++) {
						Map bean = (Map) regionList.get(i);
						// Construimos la descripcion
						String codigo = (String) bean.get("value");
						String descripcion = (String) bean.get("label");
						LabelValue lv = new LabelValue(descripcion, codigo);
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[regionList.size()];
					for (int i = 0; i < regionList.size(); i++) {
						Map bean = (Map) regionList.get(i);
						String codigo = (String) bean.get("value");
						String descripcion = (String) bean.get("label");
						LabelValue lv = new LabelValue(descripcion, codigo);
						result[i] = lv;
					}
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegistroByPeriodoTipoSoliFecha
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getRegistroByPeriodoTipoSoliFecha(String periodo,
			String tipoSolicitud, String fecha, String grupoProceso) {

		log.debug("Entro al getRegistroByPeriodoTipoSoliFecha");

		return String.valueOf(fechaProgramadaFacturacionDAO
				.getRegistroByPeriodoTipoSoliFecha(periodo, tipoSolicitud,
						fecha, grupoProceso));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegistrosProcesar(java.lang.
	 * String, java.lang.String)
	 */
	public String getRegistrosProcesar(String codigoVenta, String codigoPeriodo, String codigoConsultora) {
		Map criteria = new HashMap();
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoConsultora", codigoConsultora);

		List lista = mantenimientoCCCDiferenciaPreciosDAO
				.getRegistrosProcesar(criteria);
		if (lista.size() == 0) {
			return null;
		} else {
			return Integer.toString(lista.size());
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getReportesByDocumento(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getReportesByDocumento(String codigoPais,
			String tipoDocumento) {

		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);

		List ReportesList = procesoSTOLevantamientoErroresValidacionDAO
				.getReporteSTO(criteria);

		try {
			if (ReportesList != null && ReportesList.size() > 0) {
				result = new LabelValue[ReportesList.size()];
				for (int i = 0; i < ReportesList.size(); i++) {
					Base reporte = (Base) ReportesList.get(i);
					LabelValue lv = new LabelValue(reporte.getDescripcion(),
							reporte.getCodigo());
					result[i] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSalaEDUByPaisEmpresaLocal(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getSalaEDUByPaisEmpresaLocal(String codigoPais,
			String codigoEmpresa, String codigoLocal, String llenarVacio) {
		LabelValue[] listaSala = null;
		try {
			Sala sala = new Sala();
			sala.setCodigoPais(codigoPais);
			sala.setCodigoEmpresa(codigoEmpresa);
			sala.setCodigoLocal(codigoLocal);
			sala.setEstadoActividad(Constants.ESTADO_ENTIDAD_ACTIVO);

			List lista = parametroEDUGenericoDAO.getSalas(sala);
			if (lista != null && lista.size() > 0) {
				if (Constants.SI.equals(llenarVacio)) {
					listaSala = new LabelValue[lista.size() + 1];
					listaSala[0] = new LabelValue("", "");
				} else {
					listaSala = new LabelValue[lista.size()];
				}
				for (int i = 0; i < lista.size(); i++) {
					Sala base = (Sala) lista.get(i);
					LabelValue lv = new LabelValue(base.getDescripcionSala(),
							base.getCodigoSala());
					if (Constants.SI.equals(llenarVacio)) {
						listaSala[i + 1] = lv;
					} else {
						listaSala[i] = lv;
				}
				}
			} else {
				// Creamos una primera opcin vaca
				if (Constants.SI.equals(llenarVacio)) {
					listaSala = new LabelValue[1];
					listaSala[0] = new LabelValue("", "");
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaSala;
	}

	public LabelValue[] getSeccionesByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoRegion,
			final String codigoZona) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)
				&& StringUtils.isNotBlank(codigoRegion)
				&& StringUtils.isNotBlank(codigoZona)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List secciones = interfazSiCCDAO
						.getSeccionesByPaisMarcaCanalRegionZona(criteria);
				if (secciones != null && secciones.size() > 0) {
					result = new LabelValue[secciones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < secciones.size(); i++) {
						Base seccion = (Base) secciones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								seccion.getDescripcion(), seccion.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getSeccionesTrasvaseEnActUniAdm(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoZona,
			String ordinal) {
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoMarca", codigoMarca);
		params.put("codigoCanal", codigoCanal);
		params.put("codigoZona", codigoZona);
		// params.put("ordinal", ordinal);

		LabelValue[] result = null;

		List lista = interfazSiCCDAO
				.obtenerSeccionesTrasvaseActualizacionUnidadAdministrativa(params);

		if (lista != null && lista.size() > 0) {
			result = new LabelValue[lista.size() + 1];
			result[0] = new LabelValue(ordinal, ordinal);
			for (int i = 0; i < lista.size(); i++) {
				Base b = (Base) lista.get(i);
				result[i + 1] = new LabelValue(b.getDescripcion(),
						b.getCodigo());
			}
		}

		return result;
	}

	public LabelValue[] getSeccionMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO.getLista(
						"getSeccionMultipleByPaisMarcaCanalRegionZona",
						criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO.getLista(
						"getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona",
						criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona1(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO.getLista(
						"getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona1",
						criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public String getServicioCapacitacionByPaisEmpresaCurso(String codigoPais,
			String codigoEmpresa, String codigoCurso) {
		String codigoServicio = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("codigoCurso", codigoCurso);
			List lista = mantenimientoEDUCursoCapacitacionDAO
					.getServicioCapacitacionByCriteria(criteria);
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				codigoServicio = element;
			}
			log.debug("codigoServicio" + codigoServicio);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return codigoServicio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSizeEstadoPremiosDespacho(java
	 * .lang.String, java.lang.String)
	 */
	public Integer getSizeEstadoPremiosDespacho(String fecha, String tipoCierre) {
		Map map = new HashMap();
		map.put("tipoCierre", tipoCierre);
		map.put("fechaProceso", fecha);
		Integer size = procesoINCGenerarReporteDAO
				.getSizeEstadoPremioDespacho(map);
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSolicitudesGP2ByPeriodoTipoSoliFecha
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getSolicitudesGP2ByPeriodoTipoSoliFecha(String periodo,
			ArrayList tipoSolicitud, String fechaInicio, String fechaFin) {

		return String.valueOf(actualizacionGruposProcesoDAO
				.getSolicitudesGP2ByPeriodoTipoSoliFecha(periodo,
						tipoSolicitud, fechaInicio, fechaFin));
	}

	public String getStatusIndicadorNombreCompleto(String codigoPais,
			String codigoEmpresa) {
		String indicadorNombreCompleto = "";
		ParametroCursoCapacitacion parametroCursoCapacitacion = new ParametroCursoCapacitacion();
		parametroCursoCapacitacion.setCodigoPais(codigoPais);
		parametroCursoCapacitacion.setCodigoEmpresa(codigoEmpresa);
		parametroCursoCapacitacion
				.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);

		parametroCursoCapacitacion = parametroEDUGenericoDAO
				.getParametroCurso(parametroCursoCapacitacion);

		if (parametroCursoCapacitacion != null) {
			indicadorNombreCompleto = parametroCursoCapacitacion
					.getIndicadorNombreCompleto();
			return indicadorNombreCompleto.toUpperCase().trim();
		}

		return indicadorNombreCompleto;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getStatusTerritorioByCliente(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public int[] getStatusTerritorioByCliente(String codigoPais,
			String codigoTerritorio, String codigoZona, String orden1,
			String orden2, String orden3, String codigoCliente) {

		int status[] = { 0, 0, 0 };
		log.debug(codigoPais);
		log.debug(codigoTerritorio);
		log.debug(codigoZona);
		log.debug(orden1);
		log.debug(orden2);
		log.debug(orden3);
		log.debug(codigoCliente);

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoTerritorio)
				&& StringUtils.isNotBlank(codigoZona) &&
				/*
				 * StringUtils.isNotBlank(orden1) &&
				 * StringUtils.isNotBlank(orden2) &&
				 * StringUtils.isNotBlank(orden3) &&
				 */
				StringUtils.isNotBlank(codigoCliente)) {
			log.debug("Entro 2");
			try {
				Map criteria = new HashMap();
				criteria.put("codigoPais", codigoPais);
				criteria.put("codigoTerritorio", codigoTerritorio);
				criteria.put("codigoZona", codigoZona);
				criteria.put("orden1", orden1);
				criteria.put("orden2", orden2);
				criteria.put("orden3", orden3);
				criteria.put("codigoCliente", codigoCliente);

				status[0] = interfazSiCCDAO.getCountTerritoriosByCriteria(
						criteria).intValue();
				status[1] = interfazSiCCDAO.getCountTerritorioByZona(criteria)
						.intValue();
				status[2] = interfazSiCCDAO
						.getCountUbigeoByTerritorio(criteria).intValue();
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}

		return status;
	}

	/*
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getStatusZonaByCliente(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public int[] getStatusZonaByCliente(String codigoPais, String codigoZona,
			String codigoCliente) {

		int status[] = { 0, 0 };

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoZona)
				&& StringUtils.isNotBlank(codigoCliente)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoZona", codigoZona);
			criteria.put("codigoCliente", codigoCliente);
			try {
				status[0] = interfazSiCCDAO.getCountZonasByCriteria(criteria)[0]
						.intValue();
				status[1] = interfazSiCCDAO.getCountZonasByCriteria(criteria)[1]
						.intValue();
				log.debug(status);
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSubaccesoByAcceso(java.lang.
	 * String)
	 */
	public LabelValue[] getSubaccesoByAcceso(String acceso) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("acceso", acceso);
		try {
			List periodos = interfazSiCCDAO.getSubaccesoByAcceso(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	public LabelValue[] getSubGerenciasByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);
		try {
			List subGerencias = interfazSiCCDAO
					.getSubGerenciasByPaisMarcaCanal(criteria);
			if (subGerencias != null && subGerencias.size() > 0) {
				result = new LabelValue[subGerencias.size()];
				for (int i = 0; i < subGerencias.size(); i++) {
					Base subGerencia = (Base) subGerencias.get(i);
					LabelValue lv = new LabelValue(
							subGerencia.getDescripcion(), subGerencia
									.getCodigo().toString());
					result[i] = lv;
				}
			}

			else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getSubGerenciaxPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);
		try {
			List subGerencias = interfazSiCCDAO
					.getSubGerenciaxPaisMarcaCanal(criteria);
			if (subGerencias != null && subGerencias.size() > 0) {
				result = new LabelValue[subGerencias.size()];
				for (int i = 0; i < subGerencias.size(); i++) {
					Base subGerencia = (Base) subGerencias.get(i);
					LabelValue lv = new LabelValue(
							subGerencia.getDescripcion(), subGerencia
									.getCodigo().toString());
					result[i] = lv;
				}
			}

			else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSubLineaArmadoListar(java.lang
	 * .String)
	 */
	public LabelValue[] getSubLineaArmadoListar(String codLineaArmado) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		String oidLineArmado = "";

		if (StringUtils.isNotBlank(codLineaArmado)
				&& !codLineaArmado.equals("Todos")) {
			try {

				criteria.put("codLinea", codLineaArmado);
				oidLineArmado = mantenimientoAPESubLineaArmadoDAO
						.getOidLineaArmadobyCodigo(criteria);
				criteria.put("oidLinArmado", oidLineArmado);

				List sublineaArmadoList = mantenimientoAPETiposAnaquelesMapaCDDAO
						.getSubLineasArmadoList(criteria);

				if (sublineaArmadoList != null && sublineaArmadoList.size() > 0) {
					result = new LabelValue[sublineaArmadoList.size()];

					for (int i = 0; i < sublineaArmadoList.size(); i++) {
						Base sublinea = (Base) sublineaArmadoList.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								sublinea.getDescripcion(), sublinea.getCodigo());
						result[i] = lv;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public LabelValue[] getSublineaByLinea(String linea) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		LabelValue[] result = null;
		System.out.println("LINEA   ---------------- " + linea);
		Map criteria = new HashMap();
		criteria.put("codigoLinea", linea);
		try {
			List periodos = interfazSiCCDAO.getSublineaByLinea(criteria);
			int tamanio = periodos.size();
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[tamanio];
				// int j=0;
				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		// for(int i=0; i<result.length; i++){
		// System.out.println(result[i].toString());
		// }

		return result;
	}

	/**
	 * Metodo que obtiene el subproceso
	 */
	public LabelValue[] getSubProcesoByProceso(final String codProceso) {

		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codProceso)) {
			Map criteria = new HashMap();
			criteria.put("codProceso", codProceso);

			try {
				List subproceso = interfazSiCCDAO
						.getSubProcesoByProceso(criteria);

				if (subproceso != null && subproceso.size() > 0) {
					result = new LabelValue[subproceso.size()];

					// Creamos una primera opcin vaca
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < subproceso.size(); i++) {
						SubProceso subp = (SubProceso) subproceso.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(subp.getDescripcion(),
								subp.getCodSubProceso());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSubTiClienteList(java.lang.String
	 * , java.lang.String)
	 */
	public LabelValue[] getSubTiClienteList(String oid) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(oid) && !oid.equals("Todos")) {
			try {
				criteria.put("oid", oid);
				List lineaArmado = mantenimientoAPEConfiguracionTextosVariablesDAO
						.getSubTiClienteList(criteria);
				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];
					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);
						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSubTipoClienteByOidTipoCliente
	 * (java.lang.String)
	 */
	public LabelValue[] getSubTipoClienteByOidTipoCliente(String oidTipoCliente) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(oidTipoCliente)
				&& !oidTipoCliente.equals("Todos")) {
			try {

				List subTipoCliente = mantenimientoPEDClasificacionesChequeoDAO
						.getSubTipoClienteByOidTipoCliente(oidTipoCliente);

				if (subTipoCliente != null && subTipoCliente.size() > 0) {
					result = new LabelValue[subTipoCliente.size()];

					for (int i = 0; i < subTipoCliente.size(); i++) {
						BaseOID baseOID = (BaseOID) subTipoCliente.get(i);

						result[i] = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSubTiposClientesPorPaisTipoCliente
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getSubTiposClientesPorPaisTipoCliente(
			String codigoPais, String codigoTipoCliente) {
		log.debug("Entering 'getSubTiposClientesPorPaisTipoCliente'");
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			// criteria.put("codigoPais", codigoPais);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			List subTiposCliente = interfazSiCCDAO
					.getSubTiposClientesByCriteria(criteria);
			log.debug("Getting subTiposCliente.size=" + subTiposCliente.size());
			if (subTiposCliente != null && subTiposCliente.size() > 0) {
				result = new LabelValue[subTiposCliente.size()];
				for (int i = 0; i < subTiposCliente.size(); i++) {
					Base cuenta = (Base) subTiposCliente.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSubTiposClientesPorPaisTipoClientes
	 * (java.lang.String, java.util.ArrayList)
	 */
	public LabelValue[] getSubTiposClientesPorPaisTipoClientes(
			String codigoPais, ArrayList codigoTipoClientes) {
		log.debug("Entering 'getSubTiposClientesPorPaisTipoClientes'");
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			// criteria.put("codigoPais", codigoPais);
			criteria.put("codigoTipoClientes", codigoTipoClientes);
			List subTiposCliente = interfazSiCCDAO
					.getSubTiposClientesMultipleByCriteria(criteria);
			log.debug("Getting subTiposClientes.size=" + subTiposCliente.size());
			if (subTiposCliente != null && subTiposCliente.size() > 0) {
				result = new LabelValue[subTiposCliente.size()];
				for (int i = 0; i < subTiposCliente.size(); i++) {
					Base cuenta = (Base) subTiposCliente.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getSubTiposClientesPorPaisTipoClientesOID(java.lang.String,
	 * java.util.ArrayList)
	 */
	public LabelValue[] getSubTiposClientesPorPaisTipoClientesOID(
			String codigoIdiomaISO, ArrayList codigoTipoClientes) {
		log.debug("Entering 'getSubTiposClientesPorPaisTipoClientes'");
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoIdiomaISO", codigoIdiomaISO);
			criteria.put("codigoTipoClientes", codigoTipoClientes);
			List subTiposCliente = interfazSiCCDAO
					.getSubTiposClientesMultipleByCriteriaOID(criteria);
			log.debug("Getting subTiposClientes.size=" + subTiposCliente.size());
			if (subTiposCliente != null && subTiposCliente.size() > 0) {
				result = new LabelValue[subTiposCliente.size()];
				for (int i = 0; i < subTiposCliente.size(); i++) {
					BaseOID cuenta = (BaseOID) subTiposCliente.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getOid().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcion vacia
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getSubTiposClientesPorPaisTipoClientesCodigo(java.lang.String,
	 * java.util.ArrayList)
	 */
	public LabelValue[] getSubTiposClientesPorPaisTipoClientesCodigo(
			String codigoIdiomaISO, ArrayList codigoTipoClientes) {
		log.debug("Entering 'getSubTiposClientesPorPaisTipoClientes'");
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoIdiomaISO", codigoIdiomaISO);
			criteria.put("codTipclie", codigoTipoClientes);
			List subTiposCliente = interfazSiCCDAO
					.getSubTiposClientesMultipleByCriteriaCodigo(criteria);
			log.debug("Getting subTiposClientes.size=" + subTiposCliente.size());
			if (subTiposCliente != null && subTiposCliente.size() > 0) {
				result = new LabelValue[subTiposCliente.size()];
				for (int i = 0; i < subTiposCliente.size(); i++) {
					Base cuenta = (Base) subTiposCliente.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getCodigo().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcion vacia
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
	}

	public LabelValue[] getProgramasPorCampanaProceso(
			String codigoPais, String campana){
		log.debug("Entering 'getProgramasPorCampanaProceso'");
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			 criteria.put("codigoPais", codigoPais);
			criteria.put("campana", campana);
			List subTiposCliente =  procesoLECCargaDatosExcelDAO.getPrograma(criteria);
			
			
			log.debug("Getting subTiposClientes.size=" + subTiposCliente.size());
			if (subTiposCliente != null && subTiposCliente.size() > 0) {
				result = new LabelValue[subTiposCliente.size()];
				for (int i = 0; i < subTiposCliente.size(); i++) {
					Base cuenta = (Base) subTiposCliente.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
		
	}

	public LabelValue[] getTerritoriosByPaisMarcaCanalRegionZonaSeccion(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String codigoRegion,
			final String codigoZona, final String codigoSeccion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)
				&& StringUtils.isNotBlank(codigoRegion)
				&& StringUtils.isNotBlank(codigoZona)
				&& StringUtils.isNotBlank(codigoSeccion)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);
			criteria.put("codigoSeccion", codigoSeccion);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List territorios = interfazSiCCDAO
						.getTerritoriosByPaisMarcaCanalRegionZonaSeccion(criteria);
				if (territorios != null && territorios.size() > 0) {
					result = new LabelValue[territorios.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < territorios.size(); i++) {
						Base territorio = (Base) territorios.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								territorio.getDescripcion(),
								territorio.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTerritoriosByPaisMarcaCanalZona
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getTerritoriosByPaisMarcaCanalZona(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoZona) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("codigoZona", codigoZona);
		List listTerritorios = mantenimientoMAEClienteDAO
				.getTerritoriosByPaisMarcaCanalZona(criteria);
		result = new LabelValue[listTerritorios.size() + 1];
		Iterator it = listTerritorios.iterator();
		int cont = 0;
		result[cont++] = new LabelValue("", "");// en blanco
		while (it.hasNext()) {
			Base base = (Base) it.next();
			LabelValue label = new LabelValue();
			label.setValue(base.getCodigo());
			label.setLabel(base.getDescripcion());
			result[cont++] = label;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTerritoriosByPeriodoRegioZona
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue[] getTerritoriosByPeriodoRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoRegion, final String codigoZona,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				if (StringUtils.isEmpty(codigoZona)) {
					return result;
				}

				List territorios = interfazSiCCDAO
						.getTerritoriosByPeriodoRegioZona(criteria);
				if (territorios != null && territorios.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territorios.size() + 1];
						result[0] = new LabelValue("", "");
						for (int i = 0; i < territorios.size(); i++) {
							Base periodo = (Base) territorios.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territorios.size()];
						for (int i = 0; i < territorios.size(); i++) {
							Base concurso = (Base) territorios.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getTerritoriosMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final List codigoRegiones,
			final List codigoZonas, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)
					&& StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegiones);
			criteria.put("codigoZona", codigoZonas);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO.getLista(
						"getTerritoriosMultipleByPaisMarcaCanalRegionZona",
						criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			final ArrayList codigoZonas, final ArrayList codigoSeccion,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)
					&& StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegiones);
			criteria.put("codigoZona", codigoZonas);
			criteria.put("codigoSeccion", codigoSeccion);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO
						.getLista(
								"getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion",
								criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getTerritoriosTrasvaseEnActUniAdm(String codigoPais,
			String codigoMarca, String codigoCanal, String codigoZona,
			String codigoSeccion, String ordinal) {
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoMarca", codigoMarca);
		params.put("codigoCanal", codigoCanal);
		params.put("codigoZona", codigoZona);
		params.put("codigoSeccion", codigoSeccion);
		// params.put("ordinal", ordinal);

		LabelValue[] result = null;

		List lista = interfazSiCCDAO
				.obtenerTerritoriosTrasvaseActualizacionUnidadAdministrativa(params);

		if (lista != null && lista.size() > 0) {
			result = new LabelValue[lista.size() + 1];
			result[0] = new LabelValue(ordinal, ordinal);
			for (int i = 0; i < lista.size(); i++) {
				Base b = (Base) lista.get(i);
				result[i + 1] = new LabelValue(b.getDescripcion(),
						b.getCodigo());
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#getTipoAsistencia()
	 */
	public LabelValue[] getTipoAsistencia() {
		LabelValue[] result = null;
		ParametroGenerico parametroGenerico = new ParametroGenerico();
		List tipoAsistenciaList = new ArrayList();

		tipoAsistenciaList = parametroEDUGenericoDAO
				.getTipoAsistencia(parametroGenerico);

		result = new LabelValue[tipoAsistenciaList.size()];

		for (int i = 0; i < tipoAsistenciaList.size(); i++) {
			ParametroGenerico tipo = (ParametroGenerico) tipoAsistenciaList
					.get(i);
			LabelValue lv = new LabelValue(tipo.getDescripcion(),
					tipo.getCodigo());
			result[i] = lv;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#getTipoAsistenciaCapacitadas()
	 */
	public LabelValue[] getTipoAsistenciaCapacitadas() {
		LabelValue[] result = null;
		ParametroGenerico parametroGenerico = new ParametroGenerico();
		List tipoAsistenciaList = new ArrayList();

		tipoAsistenciaList = parametroEDUGenericoDAO
				.getTipoAsistenciaCapacitadas(parametroGenerico);

		result = new LabelValue[tipoAsistenciaList.size()];

		for (int i = 0; i < tipoAsistenciaList.size(); i++) {
			ParametroGenerico tipo = (ParametroGenerico) tipoAsistenciaList
					.get(i);
			LabelValue lv = new LabelValue(tipo.getDescripcion(),
					tipo.getCodigo());
			result[i] = lv;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTipoClasificacionByOidSubTipoCliente
	 * (java.lang.String)
	 */
	public LabelValue[] getTipoClasificacionByOidSubTipoCliente(
			String oidSubTipoCliente) {
		LabelValue[] result = null;

		log.debug("Entering 'getTipoClasificacionByOidSubTipoCliente'");

		if (StringUtils.isNotBlank(oidSubTipoCliente)
				&& !oidSubTipoCliente.equals("Todos")) {
			try {

				List tipoClasificacion = mantenimientoPEDClasificacionesChequeoDAO
						.getTipoClasificacionByOidSubTipoCliente(oidSubTipoCliente);

				if (tipoClasificacion != null && tipoClasificacion.size() > 0) {
					result = new LabelValue[tipoClasificacion.size()];
					for (int i = 0; i < tipoClasificacion.size(); i++) {
						BaseOID baseOID = (BaseOID) tipoClasificacion.get(i);

						result[i] = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getTipoClasificacionByOidSubTipoClienteList(java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue[] getTipoClasificacionByOidSubTipoClienteList(String oid) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		if (StringUtils.isNotBlank(oid) && !oid.equals("Todos")) {
			try {
				criteria.put("oid", oid);
				List lineaArmado = mantenimientoAPEConfiguracionTextosVariablesDAO
						.getTipoClasificacionByOidSubTipoClienteList(criteria);
				if (lineaArmado != null && lineaArmado.size() > 0) {
					result = new LabelValue[lineaArmado.size()];
					for (int i = 0; i < lineaArmado.size(); i++) {
						Base base = (Base) lineaArmado.get(i);
						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @author sbuchelli
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param codigogoCurso
	 * @return Tipo de Curso (02:Costo, 01:Sin costo , 03:Mixto)
	 **/
	public String getTipoCursoCosto(String codigoPais, String codigoEmpresa,
			String codigoCurso) {
		String tipoCursoCosto = Constants.EDU_TIPO_CURSO_SIN_COSTO;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		criteria.put("codigoCurso", codigoCurso);
		criteria.put("estadoRegistro", Constants.NUMERO_UNO);
		List lista = mantenimientoEDUCursoCapacitacionDAO
				.getCursosCapacitacionByCriteria(criteria);
		if (lista.size() > 0) {
			CursoCapacitacion curso = (CursoCapacitacion) lista.get(0);
			tipoCursoCosto = curso.getTipoCostoCurso();
		}

		return tipoCursoCosto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTipoDespachos(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getTipoDespachos(String indicadorDespacho,
			String indicadorInvitacion, String indicadorRegalo) {
		LabelValue[] listaTipoDespacho = null;

		try {
			ParametroGenerico parametro = new ParametroGenerico();
			List lista = parametroEDUGenericoDAO.getTipoDespachos(parametro);
			if (lista != null && lista.size() > 0) {
				/* Primero Filtramos o Eliminamos */
				int ii = 0;
				for (int i = 0; i < lista.size(); i++) {
					parametro = new ParametroGenerico();
					parametro = (ParametroGenerico) lista.get(i);
					if (parametro.getCodigo().equals(
							Constants.EDU_PARAMETROS_TIPO_DESPACHO_COSTO)) {
						if ((!indicadorDespacho.equals(Constants.SI))) {
							ii++;
						}

					}
					if (parametro.getCodigo().equals(
							Constants.EDU_PARAMETROS_TIPO_DESPACHO_INVITACION)) {
						if ((!indicadorInvitacion.equals(Constants.SI))) {
							ii++;
						}

					}
					if (parametro.getCodigo().equals(
							Constants.EDU_PARAMETROS_TIPO_DESPACHO_REGALO)) {
						if ((!indicadorRegalo.equals(Constants.SI))) {
							ii++;
						}
					}
				}
				/* Pasamos solo la lista filtrada */
				listaTipoDespacho = new LabelValue[lista.size() - ii];
				ii = 0;
				for (int i = 0; i < lista.size(); i++) {
					parametro = new ParametroGenerico();
					parametro = (ParametroGenerico) lista.get(i);

					if (Constants.EDU_PARAMETROS_TIPO_DESPACHO_COSTO
							.equals(parametro.getCodigo())
							&& Constants.SI.equals(indicadorDespacho)) {
						ParametroGenerico cuenta = (ParametroGenerico) lista
								.get(i);
						LabelValue lv = new LabelValue(cuenta.getDescripcion(),
								cuenta.getCodigo());
						listaTipoDespacho[ii] = lv;
						ii++;
					}
					if (Constants.EDU_PARAMETROS_TIPO_DESPACHO_INVITACION
							.equals(parametro.getCodigo())
							&& Constants.SI.equals(indicadorInvitacion)) {
						ParametroGenerico cuenta = (ParametroGenerico) lista
								.get(i);
						LabelValue lv = new LabelValue(cuenta.getDescripcion(),
								cuenta.getCodigo());
						listaTipoDespacho[ii] = lv;
						ii++;
					}
					if (Constants.EDU_PARAMETROS_TIPO_DESPACHO_REGALO
							.equals(parametro.getCodigo())
							&& Constants.SI.equals(indicadorRegalo)) {
						ParametroGenerico cuenta = (ParametroGenerico) lista
								.get(i);
						LabelValue lv = new LabelValue(cuenta.getDescripcion(),
								cuenta.getCodigo());
						listaTipoDespacho[ii] = lv;
						ii++;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				listaTipoDespacho = new LabelValue[1];
				listaTipoDespacho[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaTipoDespacho;
	}

	// SB
	public LabelValue[] getTipoEntidad() {
		LabelValue[] result = null;
		List listTipoEntidad = parametroEDUGenericoDAO.getListTipoEntidad();
		int tam = listTipoEntidad.size();
		int i = 0;
		if (listTipoEntidad != null && tam > 0) {
			Iterator it = listTipoEntidad.iterator();
			result = new LabelValue[tam + 1];
			result[i] = new LabelValue("", "");
			while (it.hasNext()) {
				EntidadGenerico entidadGenerico = (EntidadGenerico) it.next();
				result[++i] = new LabelValue(entidadGenerico.getDescripcion(),
						entidadGenerico.getCodigoTipo());

			}
		}
		return result;
	}

	/*
	 * Mtodo AJAX para el Mdulo de Educacin
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTipoIndicadorDespachos(java.
	 * lang.String)
	 */
	public LabelValue[] getTipoIndicadorDespachos(String tipoIndicador) {
		LabelValue[] listaTipoIndicador = null;
		try {
			ParametroGenerico parametro = new ParametroGenerico();
			parametro.setTipo(tipoIndicador);
			List lista = parametroEDUGenericoDAO
					.getTipoIndicadorDespachos(parametro);
			if (lista != null && lista.size() > 0) {
				listaTipoIndicador = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					ParametroGenerico cuenta = (ParametroGenerico) lista.get(i);
					LabelValue lv = new LabelValue(cuenta.getDescripcion(),
							cuenta.getCodigo());
					listaTipoIndicador[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaTipoIndicador = new LabelValue[1];
				listaTipoIndicador[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaTipoIndicador;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTipoOperacionList(java.lang.
	 * String, java.lang.String)
	 */
	public LabelValue[] getTipoOperacionList(String tipoProducto,
			String tipoAtencion) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		String varCodOper = "";
		List listaTipoOperacion = null;

		if (StringUtils.isNotBlank(tipoProducto)
				&& StringUtils.isNotBlank(tipoAtencion)) {
			try {

				List parametrosTipoOperacion = mantenimientoRECIngresoAtencionesDAO
						.getCodigosParamIngreAtenc(criteria);

				if (parametrosTipoOperacion.size() > 0) {

					Map valores = (Map) parametrosTipoOperacion.get(0);

					if (tipoProducto.equals("matriz")) {
						if (tipoAtencion.equals("normal")) {
							varCodOper = valores.get("codOperEnvMatNrmSinRef")
									.toString();
						} else {
							varCodOper = valores.get("codOperEnvMatExpConRef")
									.toString();
						}
					} else if(tipoProducto.equals("premio")){
						if (tipoAtencion.equals("normal")) {
							varCodOper = valores.get("codOperEnvPreNrm")
									.toString();
						} else if (valores.get("codOperEnvPreExp") == null) {
							log.debug("codOperEnvPreExp --> Es nulo!");
							varCodOper = "";
						} else {
							varCodOper = valores.get("codOperEnvPreExp")
									.toString();
						}
					} else{
						if (tipoAtencion.equals("normal")) {
							varCodOper = valores.get("codOperEnvMatNrmConPed")
									.toString();
						} else {
							varCodOper = valores.get("codOperEnvMatExpConPed")
									.toString();
						}
					}
				}

				criteria.put("codOper", varCodOper);
				listaTipoOperacion = mantenimientoRECIngresoAtencionesDAO
						.getTipoOperacionList(criteria);

				if (listaTipoOperacion != null && listaTipoOperacion.size() > 0) {
					result = new LabelValue[listaTipoOperacion.size()];
					for (int i = 0; i < listaTipoOperacion.size(); i++) {
						Base base = (Base) listaTipoOperacion.get(i);
						result[i] = new LabelValue(base.getDescripcion(), base
								.getCodigo().toString());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTiposAnaquelAsignacion(java.
	 * lang.String)
	 */
	public List getTiposAnaquelAsignacion(String sublinea) {
		Map criteria = new HashMap();
		criteria.put("codSubLiArmado", sublinea);
		String oidSublinea = mantenimientoAPETiposAnaquelesMapaCDDAO
				.getOidSubLineaArmado(criteria);
		criteria.put("oidSublinea", oidSublinea);
		String oidFuncionDist = mantenimientoAPETiposAnaquelesMapaCDDAO
				.getOidFuncionDistribucionPorOidSublinea(oidSublinea);

		if (!oidFuncionDist.equals(Constants.NUMERO_UNO)) {
			return mantenimientoAPETiposAnaquelesMapaCDDAO
					.getTipoAnaquelList(null);
		}

		return new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getTiposClasificacionesByCodISOIdiomaTClienteSubTCliente
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getTiposClasificacionesByCodISOIdiomaTClienteSubTCliente(
			String codigoIdiomaISO, String codigoTipoCliente,
			String codigoSubTipoCliente) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getTiposClasificacionesByCriteria(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base tipoClasif = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(tipoClasif.getDescripcion(),
							tipoClasif.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getTiposClasificacionesByCriteriaMultiple(java.lang.String,
	 * java.lang.String, java.util.ArrayList)
	 */
	public LabelValue[] getTiposClasificacionesByCriteriaMultiple(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getTiposClasificacionesByCriteriaMultiple(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base tipoClasif = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(tipoClasif.getDescripcion(),
							tipoClasif.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getTiposClasificacionesByCriteriaMultipleOID(java.lang.String,
	 * java.lang.String, java.util.ArrayList)
	 */
	public LabelValue[] getTiposClasificacionesByCriteriaMultipleOID(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente) {

		log.debug("Entering 'getTiposClasificacionesByCriteriaMultipleOID'");

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getTiposClasificacionesByCriteriaMultipleOID(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					BaseOID tipoClasif = (BaseOID) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(tipoClasif.getDescripcion(),
							tipoClasif.getOid().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTiposClasificacionesByCriteriaMultipleOIDParamPais(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList)
	 */
	public LabelValue[] getTiposClasificacionesByCriteriaMultipleOIDParamPais(
			String codigoPais, String codigoIdiomaISO,
			String codigoTipoCliente,
			ArrayList codigoSubTipoCliente,
			String indicadorTipoClasificacionPais) {

		log.debug("Entering 'getTiposClasificacionesByCriteriaMultipleOIDParamPais'");

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
						
			if(StringUtils.equals(indicadorTipoClasificacionPais, Constants.ESTADO_ACTIVO)){			
				criteria.put("indicadorTipoClasificacionPais", Constants.ESTADO_ACTIVO);
			}		
			
			List tiposClasificaciones = interfazSiCCDAO.getTiposClasificacionesByCriteriaMultipleOID(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					BaseOID tipoClasif = (BaseOID) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(tipoClasif.getDescripcion(),
							tipoClasif.getOid().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;

	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getTiposClasificacionesByCriteriaMultipleOID(java.lang.String,
	 * java.lang.String, java.util.ArrayList)
	 */
	public LabelValue[] getTiposClasificacionesByCriteriaMultipleCodigo(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente) {

		log.debug("Entering 'getTiposClasificacionesByCriteriaMultipleCodigo'");

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getTiposClasificacionesByCriteriaMultipleCodigo(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base tipoClasif = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(tipoClasif.getDescripcion(),
							tipoClasif.getCodigo().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getTiposClasificacionesByCriteriaMultipleOID(java.lang.String,
	 * java.lang.String, java.util.ArrayList)
	 */
	public LabelValue[] getTiposClasificacionesByCriteriaMultipleOID2(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente) {

		log.debug("Entering 'getTiposClasificacionesByCriteriaMultipleOID'");

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			List tiposClasificaciones = interfazSiCCDAO
					.getTiposClasificacionesByCriteriaMultipleOID(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size() +  1];
				result[0] = new LabelValue("Todos","T");
				
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					BaseOID tipoClasif = (BaseOID) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(tipoClasif.getDescripcion(),
							tipoClasif.getOid().toString());
					result[i+1] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCentroDByPaisMarcaCanal(java
	 * .lang.String)
	 */
	public LabelValue[] getTipoSol(String codigoPais, String periodoS) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);

		criteria.put("codigoPeriodo", "%" + periodoS);
		try {
			List periodos = interfazSiCCDAO.getTipoSol(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];
				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getTiposOperaByOpera(final String codigoPais,
			final String codigoOperacion, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoOperacion", codigoOperacion);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (StringUtils.isEmpty(codigoOperacion)) {
					return result;
				}

				List tipoOperacionList = interfazSiCCDAO.getLista(
						"getTiposOperaByOpera", criteria);
				if (tipoOperacionList != null && tipoOperacionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[tipoOperacionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base periodo = (Base) tipoOperacionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[tipoOperacionList.size()];
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base concurso = (Base) tipoOperacionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getTiposOperaByOperaByReporte(final String codigoPais,
			final String codigoOperacion, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoOperacion", codigoOperacion);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "T");

				if (StringUtils.isEmpty(codigoOperacion)) {
					return result;
				}

				List tipoOperacionList = interfazSiCCDAO.getLista(
						"getTiposOperaByOperaByReporte", criteria);
				if (tipoOperacionList != null && tipoOperacionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[tipoOperacionList.size() + 1];
						result[0] = new LabelValue("Todos", "T");
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base periodo = (Base) tipoOperacionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[tipoOperacionList.size()];
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base concurso = (Base) tipoOperacionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTiposOperaByOperaDes(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getTiposOperaByOperaDes(final String codigoPais,
			final String codigoOperacion, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoOperacion", codigoOperacion);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (StringUtils.isEmpty(codigoOperacion)) {
					return result;
				}

				List tipoOperacionList = interfazSiCCDAO.getLista(
						"getTiposOperaByOperaDes", criteria);
				if (tipoOperacionList != null && tipoOperacionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[tipoOperacionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base periodo = (Base) tipoOperacionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[tipoOperacionList.size()];
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base concurso = (Base) tipoOperacionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTiposOperaByOperaDesList(java
	 * .lang.String, java.util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getTiposOperaByOperaDesList(final String codigoPais,
			final ArrayList codigoOperacion, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoOperacion", codigoOperacion);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");
				/*
				 * if (StringUtils.isEmpty(codigoOperacion)) { return result; }
				 */
				List tipoOperacionList = interfazSiCCDAO.getLista(
						"getTiposOperaByOperaDesList", criteria);
				if (tipoOperacionList != null && tipoOperacionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[tipoOperacionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base periodo = (Base) tipoOperacionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[tipoOperacionList.size()];
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base concurso = (Base) tipoOperacionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getTiposOperaMultipleByOpera(final String codigoPais,
			ArrayList codigoOperaciones, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoOperacion", codigoOperaciones);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (codigoOperaciones.size() == 0) {
					return result;
				}

				List tipoOperacionList = interfazSiCCDAO.getLista(
						"getTiposOperaMultipleByOpera", criteria);
				if (tipoOperacionList != null && tipoOperacionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[tipoOperacionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base periodo = (Base) tipoOperacionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[tipoOperacionList.size()];
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base concurso = (Base) tipoOperacionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(), concurso
											.getCodigo().toString());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getUnidadesGeograficas(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getUnidadesGeograficas(String oidPais,
			String codigoUnidadGeografica) {
		LabelValue[] result = null;

		int nivel = (codigoUnidadGeografica.length() / 6) + 1;
		String orden1 = null;
		String orden2 = null;
		String orden3 = null;
		String orden4 = null;
		String orden5 = null;
		String orden6 = null;

		if (nivel >= 2) {
			orden1 = codigoUnidadGeografica.substring(0, 6);
		}
		if (nivel >= 3) {
			orden2 = codigoUnidadGeografica.substring(6, 12);
		}
		if (nivel >= 4) {
			orden3 = codigoUnidadGeografica.substring(12, 18);
		}
		if (nivel >= 5) {
			orden4 = codigoUnidadGeografica.substring(18, 24);
		}
		if (nivel >= 6) {
			orden5 = codigoUnidadGeografica.substring(24, 30);
		}

		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("nivel", String.valueOf(nivel));
		criteria.put("orden1", orden1);
		criteria.put("orden2", orden2);
		criteria.put("orden3", orden3);
		criteria.put("orden4", orden4);
		criteria.put("orden5", orden5);
		criteria.put("orden6", orden6);

		List listaUnidadesGeograficas = mantenimientoMAEClienteDAO
				.getUnidadesGeograficas(criteria);

		try {
			if (listaUnidadesGeograficas != null
					&& listaUnidadesGeograficas.size() > 0) {

				result = new LabelValue[listaUnidadesGeograficas.size()];

				for (int i = 0; i < listaUnidadesGeograficas.size(); i++) {
					Base unidadGeografica = (Base) listaUnidadesGeograficas
							.get(i);

					LabelValue lv = new LabelValue(
							unidadGeografica.getDescripcion(),
							unidadGeografica.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getUnidadesMedidaListar(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getUnidadesMedidaListar(String codPais,
			String codMagnitud) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		log.debug("Ingreso a Unidad de Medida Ajax Service");
		if (StringUtils.isNotBlank(codMagnitud) && !codMagnitud.equals("")) {
			try {
				criteria.put("codigoPais", codPais);
				criteria.put("oidPais", reporteDAO.getOidString(
						"getOidPaisByCodigoPais", criteria));

				criteria.put("codMagn", codMagnitud);
				String OidMagnitud = mantenimientoAPEFactoresConversionDAO
						.getObtenerOidMagnitud(criteria);
				criteria.put("oidMagn", OidMagnitud);

				List unidadMedidaList = mantenimientoAPEFactoresConversionDAO
						.getUnidadMedidaList(criteria);

				if (unidadMedidaList != null && unidadMedidaList.size() > 0) {
					result = new LabelValue[unidadMedidaList.size()];

					for (int i = 0; i < unidadMedidaList.size(); i++) {
						Base unidadMedida = (Base) unidadMedidaList.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								unidadMedida.getDescripcion(),
								unidadMedida.getCodigo());
						result[i] = lv;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidacionesByDocumento(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getValidacionesByDocumento(String codigoPais,
			String tipoDocumento) {

		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);

		List ValidacionesList = procesoSTOLevantamientoErroresValidacionDAO
				.getValidacionesByDocumento(criteria);

		try {
			if (ValidacionesList != null && ValidacionesList.size() > 0) {
				result = new LabelValue[ValidacionesList.size() + 1];
				result[0] = new LabelValue("Todos", "");
				for (int i = 0; i < ValidacionesList.size(); i++) {
					Base validacion = (Base) ValidacionesList.get(i);
					LabelValue lv = new LabelValue(validacion.getDescripcion(),
							validacion.getCodigo());
					result[i + 1] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaEntregaPremios(java.lang
	 * .String)
	 */
	public String getValidaEntregaPremios(String oidConcurso) {
		String varValida = null;
		Map criteria = new HashMap();
		criteria.put("oidConcurso", oidConcurso);

		log.debug("oid Concurso: " + oidConcurso);
		try {
			varValida = procesoINCCargaPremiosExcelDAO
					.getValidaEntregaPremios(criteria);
			log.debug("varValida: " + varValida);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return varValida;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaExistePremios(java.lang
	 * .String)
	 */
	public String getValidaExistePremios(String oidConcurso) {
		String varExiste = null;
		Map criteria = new HashMap();
		log.debug("oid Concurso: " + oidConcurso);
		criteria.put("oidConcurso", oidConcurso);

		try {
			varExiste = procesoINCCargaPremiosExcelDAO
					.getValidaExistePremios(criteria);
			log.debug("varExiste: " + varExiste);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return varExiste;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaExisteUnidadAdmxLinea(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String getValidaExisteUnidadAdmxLinea(String codPais,
			String codLineaArmado, String oidAAFP, String codRegion,
			String codZona, String codSeccion) {

		String result = "0";
		Map criteria = new HashMap();
		String oidLineArmado = "";

		criteria.put("codigoPais", codPais);
		criteria.put("codLinea", codLineaArmado);
		oidLineArmado = mantenimientoAPESubLineaArmadoDAO
				.getOidLineaArmadobyCodigo(criteria);
		criteria.put("oidLineaArmado", oidLineArmado);
		criteria.put("oidAAFP", oidAAFP);
		criteria.put("codigoRegion", codRegion);
		criteria.put("codigoZona", codZona);
		criteria.put("codigoSeccion", codSeccion);
		result = mantenimientoAPEUnidadesAdministrativasDAO
				.getValidaExisteUadmLineaAPE(criteria);

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaMontoLogro(java.lang.String
	 * , java.lang.String)
	 */
	public LabelValue getValidaMontoLogro(String codigoTipoLogro,
			String montoLogro) {
		Map criteria = new HashMap();
		criteria.put("codigoTipoLogro", codigoTipoLogro);
		criteria.put("montoLogro", montoLogro);
		return mantenimientoCUPLogrosDAO.getValidaMontoLogro(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaPatron(java.lang.String,
	 * java.lang.String)
	 */
	public Integer getValidaPatron(String codigoPeriodo, String codigoDocumento) {
		Map criteria = new HashMap();
		criteria.put("codigoDocumento", codigoDocumento);
		criteria.put("codigoPeriodo", codigoPeriodo);
		int data = mantenimientoMENGenericoDAO.getValidaPatron(criteria);
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaPeriodo(java.lang.String)
	 */
	public String getValidaPeriodo(String codigoPeriodo) {
		Integer oidPeriodo = null;

		try {
			oidPeriodo = mantenimientoLETLideresDAO
					.getOidPeriodoByCodigoPeriodo(codigoPeriodo);
		} catch (Exception e) {

		}

		if (oidPeriodo != null) {
			return String.valueOf(oidPeriodo);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaPeriodoExisteLET(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidaPeriodoExisteLET(String codigoPeriodo,
			String codigoPais, String codigoConcurso) {
		log.info("Entro a AjaxServiceImpl - getValidaPeriodoExisteLET(java.lang.String, java.lang.String, java.lang.String)");

		// -- mapeo
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoConcurso", codigoConcurso);

		// -- logica
		String resultado = mantenimientoLETParametroConcursoDAO
				.getValidaPeriodoExiste(criteria);

		log.info("Salio a AjaxServiceImpl - getValidaPeriodoExisteLET(java.lang.String, java.lang.String, java.lang.String) - Resultado:"
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaPremioCampaniaExiste(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidaPremioCampaniaExiste(String codigoPais,
			String codigoConcurso, String codigoPeriodo) {
		log.info("Entro a AjaxServiceImpl - getValidaPremioCampaniaExiste(java.lang.String, java.lang.String, java.lang.String)");

		// -- pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoConcurso", codigoConcurso);
		criteria.put("codigoPeriodo", codigoPeriodo);

		// -- logica
		String resultado = mantenimientoLETPremioCampaniaDAO
				.getValidaPremioCampaniaExiste(criteria);

		log.info("Entro a AjaxServiceImpl - getValidaPremioCampaniaExiste(java.lang.String, java.lang.String, java.lang.String) - Resultado:"
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaPremioConcursoExiste(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidaPremioConcursoExiste(String codigoPais,
			String codigoConcurso, String codigoPeriodo) {
		log.info("Entro a AjaxServiceImpl - getValidaPremioConcursoExiste(java.lang.String, java.lang.String, java.lang.String)");

		// -- pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoConcurso", codigoConcurso);
		criteria.put("codigoPeriodo", codigoPeriodo);

		// -- logica
		String resultado = mantenimientoLETPremioConcursoDAO
				.getValidaPremioConcursoExiste(criteria);

		log.info("Entro a AjaxServiceImpl - getValidaPremioConcursoExiste(java.lang.String, java.lang.String, java.lang.String) - Resultado:"
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidaProcesoCierreRegionLET
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidaProcesoCierreRegionLET(String codigoPais,
			String codigoPeriodo, String codigoRegion) {
		log.info("Entro a AjaxServiceImpl - getValidaProcesoCierreRegionLET(java.lang.String, java.lang.String, java.lang.String)");

		// -- pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);

		// -- logica
		String resultado = procesoLETResultadoSeccionCampaniaCierreCampaniaDAO
				.getValidaProcesoCierreRegionLET(criteria);

		log.info("Salio a AjaxServiceImpl - getValidaProcesoCierreRegionLET(java.lang.String, java.lang.String, java.lang.String) - Resultado:"
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidarAsignarLider(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public String getValidarAsignarLider(String codigoUA, String codigoLider,
			String condicion) {
		log.info("Entro a AjaxServiceImpl - getValidarAsignarLider(java.lang.String, java.lang.String, java.lang.String)");

		// -- Variables
		String resultado = "";
		String parametro = "";

		// -- Recuperar indicador de AsignarLider --------------------
		Map criteria = new HashMap();
		criteria.put("codigoPais", condicion);
		criteria.put("codigoSistema", "LET");
		criteria.put("codigoParametro", "002");
		parametro = mantenimientoLETLideresDAO
				.getIndicadorAsignarLider(criteria);

		if (Integer.valueOf(parametro) == 1 && codigoLider.trim().length() > 0) {

			// -- Crear pojo
			criteria = null;
			criteria = new HashMap();
			criteria.put("codigoUA", codigoUA);
			criteria.put("codigoLider", codigoLider);

			// -- Logica
			resultado = mantenimientoLETLideresDAO
					.getValidarAsignarLider(criteria);
		} else {
			resultado = "si;";
		}

		log.info("Salio a AjaxServiceImpl - getValidarAsignarLider(java.lang.String, java.lang.String, java.lang.String) - Resultado:"
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidarAsignarNulosDocLeg(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public String getValidarAsignarNulosDocLeg(String tipoDocumento,
			String serie, String fechaDesde, String fechaHasta,
			String subacceso, String canal, String acceso) {
		log.info("Entro a AjaxServiceImpl - getValidarAsignarNulosDocLeg");

		// -- Crear pojo
		Map criteria = new HashMap();
		criteria.put("oidTipoDocumento", tipoDocumento);
		criteria.put("serie", serie);
		criteria.put("fechaDesde", fechaDesde);
		criteria.put("fechaHasta", fechaHasta);
		criteria.put("codCanal", canal);
		criteria.put("oidCanal", mantenimientoRUVDocumentosContablesDAO
				.getOidCanalxCod(criteria));
		criteria.put("codigoAcceso", acceso);
		criteria.put("oidAcceso", mantenimientoRUVDocumentosContablesDAO
				.getOidSegAcceso(criteria));
		criteria.put("codigoSubAcceso", subacceso);
		criteria.put("oidSubAcceso", mantenimientoRUVDocumentosContablesDAO
				.getOidSegSubAcceso(criteria));

		// -- Logica
		String resultado = mantenimientoRUVDocumentosContablesDAO
				.getValidarAsignarNulosDocLeg(criteria);

		log.info("Salio a AjaxServiceImpl - getValidarAsignarNulosDocLeg - Resultado: "
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidarCampanhiaProceso(java
	 * .lang.String, java.lang.String)
	 */
	public String getValidarCampanhiaProceso(String codigoPais,
			String codigoPeriodoProceso) {
		log.info("Entro a AjaxServiceImpl - getValidarCampanhiaProceso(java.lang.String, java.lang.String)");

		// -- Variables
		String resultado = "";
		String codigoProcesoActual = "";

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);

		codigoProcesoActual = mantenimientoRECIngresoAtencionesDAO
				.getObtenerCampahniaActual(criteria);

		if (codigoProcesoActual != null || codigoProcesoActual != "") {
			if (Integer.parseInt(codigoPeriodoProceso) < Integer
					.parseInt(codigoProcesoActual)) {
				resultado = "1";
			} else {
				resultado = "0";
			}
		} else {
			resultado = "0";
		}

		log.info("Salio a AjaxServiceImpl - getValidarCampanhiaProceso(java.lang.String, java.lang.String) - Resultado:"
				+ resultado);
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValoresCubicaje(java.lang.String
	 * [])
	 */
	public String getValoresCubicaje(String[] cadItems) {
		Map criteria = new HashMap();
		int valorVolumen = 0;
		int valorUndMed = 0;
		int valorAlto = 0;
		int valorAncho = 0;
		int valorLargo = 0;
		String flag = "false";

		for (int i = 0; i < cadItems.length; i++) {
			String[] cad = StringUtils.split(cadItems[i], "|");

			criteria.put("oidProducto", cad[1]);
			valorVolumen = Integer
					.parseInt(mantenimientoAPEErroresEnCubicajeDAO
							.getValidaValoresProducto(criteria));
			valorUndMed = Integer.parseInt(mantenimientoAPEErroresEnCubicajeDAO
					.getValidaValorUndMedVolProducto(criteria));
			valorAlto = Integer.parseInt(mantenimientoAPEErroresEnCubicajeDAO
					.getValidaValorAltoProducto(criteria));
			valorLargo = Integer.parseInt(mantenimientoAPEErroresEnCubicajeDAO
					.getValidaValorLargoProducto(criteria));
			valorAncho = Integer.parseInt(mantenimientoAPEErroresEnCubicajeDAO
					.getValidaValorAnchoProducto(criteria));

			if (valorVolumen > 0) {
				flag = "true";
			}

			if (valorUndMed > 0) {
				flag = "true";
			}

			if (valorAlto > 0) {
				flag = "true";
			}

			if (valorLargo > 0) {
				flag = "true";
			}

			if (valorAncho > 0) {
				flag = "true";
			}
		}

		return flag;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValorEstadoOLADia(java.lang.
	 * String)
	 */
	public String[] getValorEstadoOLADia(String oidOlaxDia) {
		String[] valorCad = new String[2];
		EstadoOlaWCS bean = null;
		Map params = new HashMap();

		params.put("oidOlasxDia", oidOlaxDia);

		bean = mantenimientoAPEControlEnvioInterfacesWCSDAO
				.getValEstadoOla(params);
		log.debug("oidOlasxDia " + oidOlaxDia);

		valorCad[0] = bean.getEstado();
		valorCad[1] = bean.getNumeroOla();

		return valorCad;
	}

	public String getValorNivel(String pais, String programa, String nivel,
			String periodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais);
		criteria.put("codigoPrograma", programa);
		criteria.put("codigoNivel", nivel);
		criteria.put("codigoPeriodo", periodo);
		String valorNivel = mantenimientoDAO.getValorNivel(criteria);
		if (valorNivel != null) {
			return valorNivel;
		} else {
			return "";
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValorNivelPremiosElectivos(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValorNivelPremiosElectivos(String pais, String programa,
			String nivel, String periodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais);
		criteria.put("codigoPrograma", programa);
		criteria.put("codigoNivel", nivel);
		criteria.put("codigoPeriodo", periodo);
		String valorNivel = mantenimientoDAO
				.getValorNivelPremiosElectivos(criteria);
		if (valorNivel != null) {
			return valorNivel;
		} else {
			return "";
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValorStatus(java.lang.String)
	 */
	public String getValorStatus(String oidCliente) {
		String varStatus = null;
		Map criteria = new HashMap();
		criteria.put("oidCliente", oidCliente);
		log.debug("criteria: " + criteria);
		try {
			varStatus = consultaHIPDatosClienteDAO.getValorStatus(criteria);
			log.debug("varStatus: " + varStatus);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return varStatus;
	}

	public String getValorUnitarioByCodigoVenta(String codigoPeriodo,
			String codigoVenta) {
		Map map = new HashMap();
		map.put("codigoVenta", codigoVenta);
		map.put("codigoPeriodo", codigoPeriodo);
		BigDecimal oidOferta = mantenimientoOCRPedidoControlFacturacionDAO
				.getOfertaDetalleByPeriodoCodigoVenta(map);
		map.put("idDetalleOferta", oidOferta);
		DespachoProducto despachoProducto = mantenimientoDAO
				.getOfertaDetalleById(map);
		if (despachoProducto != null) {
			return despachoProducto.getValorUnitario();
		} else {
			return "";
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getVersionActiva(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getVersionActiva(String codLineArmado, String codMapaCD,
			String codPeriodo, String codMarca, String codCanal) {

		String result = "";
		Map criteria = new HashMap();
		String oidMapaCD = "";
		String oidLineaArmado = "";

		log.debug("codLineArmado ---> " + codLineArmado);
		log.debug("codMapaCD ---> " + codMapaCD);
		log.debug("codPeriodo ---> " + codPeriodo);
		log.debug("codMarca ---> " + codMarca);
		log.debug("codCanal ---> " + codCanal);

		if (StringUtils.isNotBlank(codMapaCD)
				&& StringUtils.isNotBlank(codMapaCD)
				&& StringUtils.isNotBlank(codMarca)
				&& StringUtils.isNotBlank(codCanal)
				&& StringUtils.isNotBlank(codPeriodo)) {

			criteria.put("codPeriodo", codPeriodo);
			criteria.put("codigoMarca", codMarca);
			criteria.put("codigoCanal", codCanal);
			criteria.put("codMapCentrDist", codMapaCD);

			criteria.put("codLinea", codLineArmado);
			oidLineaArmado = mantenimientoAPESubLineaArmadoDAO
					.getOidLineaArmadobyCodigo(criteria);
			criteria.put("oidLinArmado", oidLineaArmado);

			oidMapaCD = mantenimientoAPETiposAnaquelesMapaCDDAO
					.getOidMapaCentroDistribucion(criteria);
			criteria.put("oidMapaCD", oidMapaCD);

			int nExisteOidPeriodo = mantenimientoAPEAsignarVersionesProductosDAO
					.getExisteOidPeriodoCanalMarca(criteria);

			if (nExisteOidPeriodo > 0) {
				criteria.put("oidPeriodo",
						mantenimientoAPEAsignarVersionesProductosDAO
								.getOidPeriodobyMarcaCanal(criteria));
				result = mantenimientoAPEAsignarVersionesProductosDAO
						.getVersionActiva(criteria);
			} else {
				result = "";
			}
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCentroDByPaisMarcaCanal(java
	 * .lang.String)
	 */
	public LabelValue[] getVersionByMapa(String mapa, String codigoPeriodo) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("mapa", mapa);
		criteria.put("periodo", '%' + codigoPeriodo);
		try {
			List periodos = interfazSiCCDAO.getVersionByMapa(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out
					.println("MAPA " + result[i].toString() + " mapa " + mapa);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCentroDByPaisMarcaCanal(java
	 * .lang.String)
	 */
	public LabelValue[] getVersionByMapaAnt(String mapa, String codigoPeriodo) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("mapa", mapa);
		criteria.put("periodo",
				'%' + new Integer(new Integer(codigoPeriodo).intValue() - 1)
						.toString());
		try {
			List periodos = interfazSiCCDAO.getVersionByMapa(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out
					.println("MAPA " + result[i].toString() + " mapa " + mapa);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getVersionesbyPeriodoList(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getVersionesbyPeriodoList(String codCentro,
			String codMarca, String codCanal, String codPeriodo) {
		LabelValue[] result = null;
		Map criteria = new HashMap();

		String oidPeriodo = "";

		if (StringUtils.isNotBlank(codCentro)
				&& StringUtils.isNotBlank(codMarca)
				&& StringUtils.isNotBlank(codCanal)
				&& StringUtils.isNotBlank(codPeriodo)) {
			try {
				log.debug("Ingreso getVersionesbyPeriodoList");
				criteria.put("oidCentro", codCentro);
				criteria.put("codPeriodo", codPeriodo);
				criteria.put("codigoMarca", codMarca);
				criteria.put("codigoCanal", codCanal);
				oidPeriodo = mantenimientoAPEAsignarVersionesProductosDAO
						.getOidPeriodobyMarcaCanal(criteria);
				criteria.put("oidPeriodo", oidPeriodo);

				List versionesList = mantenimientoAPEAsignarVersionesProductosDAO
						.getVersionesbyPeriodoList(criteria);

				if (versionesList != null && versionesList.size() > 0) {
					result = new LabelValue[versionesList.size()];

					for (int i = 0; i < versionesList.size(); i++) {
						Base verisones = (Base) versionesList.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(
								verisones.getDescripcion(),
								verisones.getCodigo());
						result[i] = lv;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getVersionSinP(java.lang.String)
	 */
	public LabelValue[] getVersionSinP(String mapa) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("mapa", mapa);
		try {
			List periodos = interfazSiCCDAO.getVersionSinP(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out
					.println("MAPA " + result[i].toString() + " mapa " + mapa);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonaArriboSTOByTipoDocumento
	 * (java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonaArriboSTOByTipoDocumento(String codigoPais,
			String tipoDocumento) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);
		List origenesList = procesoSTODAO
				.getZonaArriboSTOByTipoDocumento(criteria);

		try {
			if (origenesList != null && origenesList.size() > 0) {
				result = new LabelValue[origenesList.size() + 1];
				result[0] = new LabelValue("Todos", "");
				for (int i = 0; i < origenesList.size(); i++) {
					Base estado = (Base) origenesList.get(i);
					LabelValue lv = new LabelValue(estado.getDescripcion(),
							estado.getCodigo());
					result[i + 1] = lv;
				}
			} else {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public LabelValue[] getZonaByPaisEmpresaRegion(final String codigoPais,
			final String codigoEmpresa, final String codigoRegion) {
		String[] listaRegion = new String[1];
		listaRegion[0] = codigoRegion;
		return getZonaByRegion(codigoPais, codigoEmpresa, listaRegion);
	}

	public LabelValue[] getZonaByRegion(final String codigoPais,
			final String codigoEmpresa, final String[] codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoEmpresa", codigoEmpresa);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				if (codigoRegion.length == 0) {
					return result;
				}
				List listaZona = mantenimientoEDUCursoCapacitacionDAO
						.getZonaByRegionSelected(criteria);
				if (listaZona != null && listaZona.size() > 0) {
					result = new LabelValue[listaZona.size()];
					for (int i = 0; i < listaZona.size(); i++) {
						Base zona = new Base();
						ZonaCursoCapacitacion detalle = new ZonaCursoCapacitacion();
						detalle = (ZonaCursoCapacitacion) listaZona.get(i);
						zona.setCodigo(detalle.getCodigoZona());
						zona.setDescripcion(detalle.getDescripcionZona());
						// Construimos la descripcion
						LabelValue lv = new LabelValue(zona.getCodigo() + " - "
								+ zona.getDescripcion(), zona.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getZonaByRegionSelected(final String codigoPais,
			final String codigoEmpresa, final String[] codigoRegion,
			final String flagTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoEmpresa", codigoEmpresa);
			try {
				result = new LabelValue[1];
				if (flagTodos
						.equals(Constants.EDU_FLAG_AJAX_COMBO_DESCRIPCION_TODOS)) {
					result[0] = new LabelValue("Todos", "00");
				} else {
					result[0] = new LabelValue("", "");
				}

				if (codigoRegion.length == 0) {
					return result;
				}
				List listaZona = mantenimientoEDUCursoCapacitacionDAO
						.getZonaByRegionSelected(criteria);
				if (listaZona != null && listaZona.size() > 0) {
					if (flagTodos
							.equals(Constants.EDU_FLAG_AJAX_COMBO_DESCRIPCION_TODOS)) {
						result = new LabelValue[listaZona.size() + 1];
						result[0] = new LabelValue("Todos", "00");
					} else {
						result = new LabelValue[listaZona.size()];
					}
					for (int i = 0; i < listaZona.size(); i++) {
						Base zona = new Base();
						ZonaCursoCapacitacion detalle = new ZonaCursoCapacitacion();
						detalle = (ZonaCursoCapacitacion) listaZona.get(i);
						if (flagTodos
								.equals(Constants.EDU_FLAG_AJAX_COMBO_CODIGO_NOTODOS)) {
							// Construimos la descripcion
							zona.setCodigo(detalle.getCodigoRegion()
									+ detalle.getCodigoZona());
							zona.setDescripcion(detalle.getCodigoZona() + " - "
									+ detalle.getDescripcionZona());
							LabelValue lv = new LabelValue(
									zona.getDescripcion(), zona.getCodigo());
							result[i] = lv;
						} else if (flagTodos
								.equals(Constants.EDU_FLAG_AJAX_COMBO_CODIGO_SEPARATED)) {
							zona.setCodigo(detalle.getCodigoRegion() + "|"
									+ detalle.getCodigoZona());
							zona.setDescripcion(detalle.getCodigoZona() + " - "
									+ detalle.getDescripcionZona());
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									zona.getDescripcion(), zona.getCodigo());
							result[i] = lv;
						} else {
							// Construimos la descripcion
							zona.setCodigo(detalle.getCodigoRegion()
									+ detalle.getCodigoZona());
							zona.setDescripcion(detalle.getCodigoZona() + " - "
									+ detalle.getDescripcionZona());
							LabelValue lv = new LabelValue(
									zona.getDescripcion(), zona.getCodigo());
							result[i + 1] = lv;
						}
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					if (flagTodos.equals(Constants.INDICADOR_ASISTENCIA_SI)) {
						result[0] = new LabelValue("Todos", "00");
					} else {
						result[0] = new LabelValue("Todos", "00");
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasAllDirectorio(java.lang
	 * .String, java.lang.String[], java.lang.String)
	 */
	public LabelValueCDR[] getZonasAllDirectorio(String indicadorTitular,
			String[] codigoRegiones, String indiceZona) {
		LabelValueCDR[] result = null;
		Map criteria = new HashMap();
		log.debug("indiceZona " + indiceZona);
		criteria.put("indicadorTitular", indicadorTitular);
		criteria.put("indicadorActivo", Constants.ESTADO_ACTIVO);
		criteria.put("codigoRegion", Arrays.asList(codigoRegiones));
		log.debug("criteria ade " + criteria);
		try {
			List regiones = mantenimientoMAEClienteDAO
					.getZonasDirectorio(criteria);
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValueCDR[regiones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion

					LabelValueCDR lv = new LabelValueCDR(
							region.getDescripcion(), region.getCodigo(), null,
							indiceZona);
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValueCDR[1];
				result[0] = new LabelValueCDR("", "", null, indiceZona);
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		// colocamos el indice
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getZonasAllDirectorioAsignarCargo(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.String)
	 */
	public LabelValueCDR[] getZonasAllDirectorioAsignarCargo(String codigoPais,
			String codigoConexionExterna, String indicadorTitular,
			String[] codigoRegiones, String indice, String cargo) {

		LabelValueCDR[] result = null;
		Map criteria = new HashMap();
		
		String flag = "0";
		if(StringUtils.equals(cargo, "GZ")){
			flag = "1";
		}
		
		log.debug("indiceZona " + indice);
		criteria.put("indicadorTitular", indicadorTitular);
		criteria.put("indicadorActivo", Constants.ESTADO_ACTIVO);
		criteria.put("codigoRegion", Arrays.asList(codigoRegiones));
		criteria.put("flag", flag);
		criteria.put("codigoPais", codigoPais);
		
		log.debug("criteria ade " + criteria);
		try {
			
			List regiones = null;			
			if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
				regiones = mantenimientoMAEClienteDAO.getZonasDirectorioCargoFOX(criteria);
			else
				regiones = mantenimientoMAEClienteDAO.getZonasDirectorioCargo(criteria);
				
						
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValueCDR[regiones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion

					LabelValueCDR lv = new LabelValueCDR(
							region.getDescripcion(), region.getCodigo(), null,
							indice);
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValueCDR[1];
				result[0] = new LabelValueCDR("", "", null, indice);
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		// colocamos el indice
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByMultiplePeriodoBasCtrlFact
	 * (java.lang.String, java.lang.String, java.lang.String[],
	 * java.lang.String)
	 */
	public LabelValue[] getZonasByMultiplePeriodoBasCtrlFact(
			final String codigoPais, final String codigoPeriodo,
			final String[] codigoRegion, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoRegion", codigoRegion);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (codigoRegion == null || codigoRegion.length == 0) {
					return result;
				}

				List regiones = interfazSiCCDAO.getLista(
						"getZonasByMultiplePeriodoBasCtrlFact", criteria);
				if (regiones != null && regiones.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regiones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regiones.size(); i++) {
							Base periodo = (Base) regiones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base concurso = (Base) regiones.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByPais(java.lang.String)
	 */
	public LabelValue[] getZonasByPais(String codigoPais) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		try {
			List periodos = interfazSiCCDAO.getZonasByPais(criteria);
			if (periodos != null && periodos.size() > 0) {
				result = new LabelValue[periodos.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < periodos.size(); i++) {
					Base periodo = (Base) periodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i].toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByPaisActivasNoActivas(
	 * java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasByPaisActivasNoActivas(final String codigoPais,
			final String codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoRegion", codigoRegion);

			try {
				List regiones = interfazSiCCDAO
						.getZonasByPaisActivasNoActivas(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByPaisCanalRegion(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasByPaisCanalRegion(final String codigoPais,
			final String codigoCanal, final String codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoRegion", codigoRegion);

			try {
				List regiones = interfazSiCCDAO
						.getZonasByPaisCanalRegion(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByPaisMarcaCanalRegion(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasByPaisMarcaCanalRegion(final String codigoPais,
			final String codigoMarca, final String codigoCanal,
			final String codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)
				&& StringUtils.isNotBlank(codigoRegion)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoRegion", codigoRegion);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List regiones = interfazSiCCDAO
						.getZonasByPaisMarcaCanalRegion(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getZonasByPaisRegion(final String codigoPais,
			final String codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoRegion)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoRegion", codigoRegion);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List regiones = interfazSiCCDAO
						.getZonasByPaisMarcaCanalRegion(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByPaisRegionLet(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getZonasByPaisRegionLet(final String codigoPais,
			final String codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoRegion", codigoRegion);

			try {

				List regiones = interfazSiCCDAO
						.getZonasByPaisRegionLet(criteria);

				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];

					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {

					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getZonasByPaisSociedadEtapaDeudaPeriodoRegion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoPeriodo, String codigoRegion) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoRegion", codigoRegion);

		log.debug("Vamos a cargar el DAO: ");
		List zonasList = procesoCOBDAO.getLista(
				"getZonasByPaisSociedadEtapaDeudaPeriodoRegion", criteria);
		// List zonasList =
		// procesoCOBAsignacionCarteraDAO.getZonasByPaisSociedadEtapaDeudaRegion(criteria);
		log.debug("lista en ajax : " + zonasList.size());

		if (zonasList != null && zonasList.size() > 0) {
			result = new LabelValue[zonasList.size()];
			for (int i = 0; i < zonasList.size(); i++) {
				Base zona = (Base) zonasList.get(i);
				LabelValue lv = new LabelValue(zona.getDescripcion(),
						zona.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}

	public LabelValue[] getZonasByPaisSociedadEtapaDeudaRegion(
			String codigoPais, String codigoSociedad, String codigoEtapaDeuda,
			String codigoRegion) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", codigoSociedad);
		criteria.put("codigoEtapaDeuda", codigoEtapaDeuda);
		criteria.put("codigoRegion", codigoRegion);

		List zonasList = procesoCOBDAO.getLista(
				"getZonasByPaisSociedadEtapaDeudaRegion", criteria);

		// List zonasList =
		// procesoCOBAsignacionCarteraDAO.getZonasByPaisSociedadEtapaDeudaRegion(criteria);
		log.debug("lista en ajax : " + zonasList.size());

		if (zonasList != null && zonasList.size() > 0) {
			result = new LabelValue[zonasList.size()];
			for (int i = 0; i < zonasList.size(); i++) {
				Base zona = (Base) zonasList.get(i);
				LabelValue lv = new LabelValue(zona.getDescripcion(),
						zona.getCodigo());
				result[i] = lv;
			}
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByPeriodoBasCtrlFact(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasByPeriodoBasCtrlFact(final String codigoPais,
			final String codigoPeriodo, final String codigoRegion,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoRegion", codigoRegion);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				if (StringUtils.isEmpty(codigoRegion)) {
					return result;
				}

				List regiones = interfazSiCCDAO
						.getZonasByPeriodoBasCtrlFact(criteria);
				if (regiones != null && regiones.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regiones.size() + 1];
						result[0] = new LabelValue("", "");
						for (int i = 0; i < regiones.size(); i++) {
							Base periodo = (Base) regiones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base concurso = (Base) regiones.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByPeriodoIntEviPerioRegioZona
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasByPeriodoIntEviPerioRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoRegion, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoRegion", codigoRegion);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				if (StringUtils.isEmpty(codigoRegion)) {
					return result;
				}

				List regiones = interfazSiCCDAO
						.getZonasByPeriodoIntEviPerioRegioZona(criteria);
				if (regiones != null && regiones.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regiones.size() + 1];
						result[0] = new LabelValue("", "");
						for (int i = 0; i < regiones.size(); i++) {
							Base periodo = (Base) regiones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base concurso = (Base) regiones.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("Todos", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasDirectorio(java.lang.String
	 * , java.lang.String[])
	 */
	public synchronized LabelValueCDR[] getZonasDirectorio(
			String indicadorTitular, String[] codigoRegiones, String indiceZona) {
		LabelValueCDR[] result = null;
		Map criteria = new HashMap();
		log.debug("indiceZona " + indiceZona);
		criteria.put("indicadorTitular", indicadorTitular);
		criteria.put("indicadorActivo", Constants.NUMERO_UNO);
		criteria.put("codigoRegion", Arrays.asList(codigoRegiones));
		log.debug("criteria ade " + criteria);
		try {
			List regiones = mantenimientoMAEClienteDAO
					.getZonasDirectorio(criteria);
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValueCDR[regiones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion

					LabelValueCDR lv = new LabelValueCDR(
							region.getDescripcion(), region.getCodigo(), null,
							indiceZona);
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValueCDR[1];
				result[0] = new LabelValueCDR("", "", null, indiceZona);
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		// colocamos el indice
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasMultipleByCerrar(java.lang
	 * .String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	public LabelValue[] getZonasMultipleByCerrar(final String codigoPais,
			final String campanhaProceso, final String[] codigoRegiones,// final
																		// ArrayList
																		// codigoRegiones,
			String condicionTodos, String indicadorEdit) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("campanhaProceso", campanhaProceso);
		criteria.put("codigoRegion", codigoRegiones);
		criteria.put("indicadorEdit",
				Constants.NRO_UNO.equals(indicadorEdit) ? indicadorEdit : null);

		try {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");

			List zonasList = mantenimientoFACGenericoDAO
					.getZonasPorCerrar(criteria);
			if (zonasList != null && zonasList.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[zonasList.size() + 1];
					result[0] = new LabelValue("Todos", "");
					for (int i = 0; i < zonasList.size(); i++) {
						Map bean = (Map) zonasList.get(i);
						// Construimos la descripcion
						String codigo = (String) bean.get("value");
						String descripcion = (String) bean.get("label");
						LabelValue lv = new LabelValue(descripcion, codigo);
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[zonasList.size()];
					for (int i = 0; i < zonasList.size(); i++) {
						Map bean = (Map) zonasList.get(i);
						String codigo = (String) bean.get("value");
						String descripcion = (String) bean.get("label");
						LabelValue lv = new LabelValue(descripcion, codigo);
						result[i] = lv;
					}
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones,// final
																		// ArrayList
																		// codigoRegiones,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)
					&& StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			/*
			 * if (codigoRegiones != null && codigoRegiones.length > 0) {
			 * ArrayList list = new ArrayList(codigoRegiones.length); for (int
			 * i=0; i <codigoRegiones.length;i++)
			 * list.add(i,codigoRegiones[i].getLabel());
			 * criteria.put("codigoRegion", list); }
			 */
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = null;
				Pais pais = paisDAO.getPais(codigoPais);
				
				criteria.put("indicadorActivo", Constants.ESTADO_ACTIVO);
								
				if(StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX))
					zonasList = mantenimientoMAEClienteDAO.getZonasDirectorioActivasFOX(criteria);
				else
					zonasList = interfazSiCCDAO.getLista("getZonasMultipleByPaisMarcaCanalRegion", criteria);
											
				if (zonasList != null && zonasList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos) || StringUtils.equals("", condicionTodos) || StringUtils.equals(" ", condicionTodos)  ) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}

	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionDetalle(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			String condicionTodos, String tipoDetalle) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)
					&& StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			/*
			 * if (codigoRegiones != null && codigoRegiones.length > 0) {
			 * ArrayList list = new ArrayList(codigoRegiones.length); for (int
			 * i=0; i <codigoRegiones.length;i++)
			 * list.add(i,codigoRegiones[i].getLabel());
			 * criteria.put("codigoRegion", list); }
			 */
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = interfazSiCCDAO.getLista(
						"getZonasMultipleByPaisMarcaCanalRegion", criteria);
				if (zonasList != null
						&& zonasList.size() > 0
						&& (tipoDetalle.equalsIgnoreCase("Zona")
								|| tipoDetalle.equalsIgnoreCase("Consultora") || tipoDetalle
									.equalsIgnoreCase("Premio"))) {
					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getZonasMultipleByPaisMarcaCanalRegionOid(java.lang.String,
	 * java.lang.String, java.lang.String, java.util.ArrayList,
	 * java.lang.String)
	 */
	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionOid(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)
					&& StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			/*
			 * if (codigoRegiones != null && codigoRegiones.length > 0) {
			 * ArrayList list = new ArrayList(codigoRegiones.length); for (int
			 * i=0; i <codigoRegiones.length;i++)
			 * list.add(i,codigoRegiones[i].getLabel());
			 * criteria.put("codigoRegion", list); }
			 */
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = new ArrayList();
				;
				try {
					zonasList = interfazSiCCDAO.getLista(
							"getZonasMultipleByPaisMarcaCanalRegionOid",
							criteria);
				} catch (Exception e) {
					// TODO: handle exception
				}

				if (zonasList != null && zonasList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}

	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionSubGerencia(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final String codigoSubGerencia, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoSubGerencia)
					&& StringUtils.isNotBlank(codigoSubGerencia)) {
				criteria.put("codigoSubGerencia", codigoSubGerencia);
			} else {
				return result;
			}
			if (codigoRegion.size() > 0) {
				criteria.put("codigoRegion", codigoRegion);
			} else {
				return result;
			}

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");

				List regionList = interfazSiCCDAO.getLista(
						"getZonasMultipleByPaisMarcaCanalRegionSubGerencia",
						criteria);
				if (regionList != null && regionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regionList.size(); i++) {
							Base periodo = (Base) regionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regionList.size()];
						for (int i = 0; i < regionList.size(); i++) {
							Base concurso = (Base) regionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
		//
	}

	public LabelValue[] getTiposAnulacionByMotivo(final String motivo) {
		LabelValue[] result = null;

			Map criteria = new HashMap();
			criteria.put("varOperAnul", motivo);
			
			try {
				List tiposAnulacion = interfazSiCCDAO.getLista(
						"getTiposOperacionIngresoAnulaciones",
						criteria);
				if (tiposAnulacion != null && tiposAnulacion.size() > 0) { 
						result = new LabelValue[tiposAnulacion.size()];
						for (int i = 0; i < tiposAnulacion.size(); i++) {
							Base concurso = (Base) tiposAnulacion.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		return result;
	}	
	
	public LabelValue[] getZonasMultipleByPeriodoIntEviPerioRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoMarca, final String codigoCanal,
			final ArrayList codigoRegiones, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (codigoRegiones.size() == 0) {
					return result;
				}
				if (StringUtils.isEmpty(codigoPeriodo)) {
					return result;
				}
				if (StringUtils.isEmpty(codigoMarca)) {
					return result;
				}
				if (StringUtils.isEmpty(codigoCanal)) {
					return result;
				}
				List regiones = interfazSiCCDAO.getLista(
						"getZonasMultipleByPeriodoIntEviPerioRegioZona",
						criteria);
				if (regiones != null && regiones.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regiones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regiones.size(); i++) {
							Base periodo = (Base) regiones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base concurso = (Base) regiones.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}
	
	private boolean isIndicadorNombreCompleto(String codigoPais,
			String codigoEmpresa) {
		String indicador = getStatusIndicadorNombreCompleto(codigoPais,
				codigoEmpresa);
		log.debug("indicador " + indicador);
		return (Constants.EDU_INDICADOR_NOMBRE_COMPLETO.equals(indicador));
	}

	private boolean isJefaEducacion(MaestroInstructora instructora) {
		if (Constants.EDU_TIPO_EJECUTIVA_EDUCACION.equals(instructora
				.getTipoEjecutiva())) {
			return false;
		}
		// ingresas como administradr
		return true;
	}

	public String mostrarCodigoVenta(String codigoPais) {
		String codigoCliente = new String();
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		try {

			codigoCliente = procesoSTOEjecucionValidacionesDAO
					.executeGenerarCodigoClienteSTO(criteria);
			procesoSTOEjecucionValidacionesDAO.updateNumeroSolicitud(criteria);
		} catch (DataAccessException ignore) {

		}

		return codigoCliente;
	}

	private String readField(String fieldValue) {
		if (fieldValue != null) {
			return fieldValue;
		} else {
			return "";
		}
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#updateEtiqueta(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public String updateEtiqueta(String oidPais, String oidConsolidado,
			String oidSoli, String codListaPicado, String numCaja,
			String usuario) {
		String resultado = new String();
		Map criteria = new HashMap();
		resultado = "N";
		criteria.put("oidPais", oidPais);
		criteria.put("oidSoli", oidSoli);
		criteria.put("oidConsolidado", oidConsolidado);
		criteria.put("codListaPicado", codListaPicado);
		criteria.put("numCaja", numCaja);
		criteria.put("usuario", usuario);
		criteria.put("nroHito", Constants.NRO_HITO_INCIO_DESPACHO);

		// Indicador de Terminado -> 1 = VERDADERO / 0 = FALSO
		criteria.put("indicadorTerminado", new Integer(1));

		try {
			mantenimientoAPEProcesoEmbalajeTerminadoDAO
					.updateEtiqueta(criteria);
			mantenimientoAPEProcesoEmbalajeTerminadoDAO
					.updateListadoPicado(criteria);
			mantenimientoAPEProcesoEmbalajeTerminadoDAO
					.actualizarSeguimientoPedidos(criteria);
			resultado = "S";
		} catch (DataAccessException ignore) {
		}
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaCantidadTerritoriosSecuenciacion
	 * (java.lang.String)
	 */
	public String validaCantidadTerritoriosSecuenciacion(String codigoZona) {
		Map criteria = new HashMap();

		List zonasList = procesoAPPSecuenciarZonaTerritorioDAO
				.getSecuenciasZonasList(criteria);
		List zonasHomologadasList = procesoAPPSecuenciarZonaTerritorioDAO
				.getSecuenciasZonasHomologadasList(criteria);

		List erroresList = new ArrayList();

		for (int i = 0; i < zonasHomologadasList.size(); i++) {
			Map homologado = (Map) zonasHomologadasList.get(i);
			boolean flag = true;
			for (int j = 0; j < zonasList.size(); j++) {
				Map zona = (Map) zonasList.get(j);
				if (homologado.get("codigoZona").toString()
						.equals(zona.get("codigoZona").toString())) {
					flag = false;
					if (!homologado.get("cantidad").toString()
							.equals(zona.get("cantidad").toString())) {
						erroresList
								.add(homologado.get("codigoZona").toString());
					}
					break;
				}
			}
			if (flag) {
				erroresList.add(homologado.get("codigoZona").toString());
			}
		}

		if (erroresList.size() != 0) {
			String error = getKeyMessage("territorios.zonas") + " ";
			for (int i = 0; i < erroresList.size(); i++) {
				error = error + erroresList.get(i).toString() + " ";
			}
			error += getKeyMessage("territotios.zonas.no.coinciden.resecuenciar");
			return error;
		}
		return "0";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaCodigoConsultora(java.lang
	 * .String)
	 */
	public String validaCodigoConsultora(String codigoCliente, String codigoPais) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoCliente", codigoCliente);
		return interfazSiCCDAO.getExisteConsultoraByCodigoPais(criteria)
				.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaCodigoVenta(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelPedidosValue validaCodigoVenta(String codigoPais,
			String codigoPeriodo, String codigoCliente, String numLote,
			String codigoVenta) {
		LabelPedidosValue pedidoValue = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("numLote", numLote);
		criteria.put("codigoVenta", codigoVenta);
		try {
			pedidoValue = mantenimientoOCRCargaPedidoDAO
					.validaCodigoVenta(criteria);
		} catch (Exception e) {
		}
		return pedidoValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaCodigoVentaLET(java.lang.
	 * String, java.lang.String)
	 */
	public String validaCodigoVentaLET(String descripcionGeneral,
			String codigoPeriodoDespacho, String indicadorConcursoCampania) {
		log.info("Entro a AjaxServiceImpl)");

		String strFila[] = descripcionGeneral.split(";");
		String strColumna[];
		Map criteria = new HashMap();
		int valida = 0;
		StringBuffer cadenaRetorno = new StringBuffer();

		String codigoVenta = "";

		// -- Logica validaciones
		for (int i = 0; i < strFila.length; i++) {

			// -- Pojo
			strColumna = strFila[i].split("-");
			criteria.put("codigoPeriodoDespacho", codigoPeriodoDespacho);
			criteria.put("codigoVenta", strColumna[2]);
			codigoVenta = strColumna[2];
			/* INI JJ PER-SiCC-2012-0201 */
			if (indicadorConcursoCampania.equals("0")) {

				if (!StringUtils.equals(codigoVenta, "0")) {/*
															 * Ini
															 * PER-SiCC-2012-
															 * 0519 para
															 * concurso
															 */
					// -- Logica
					valida = mantenimientoLETPremioCampaniaDAO
							.getValidaCodigoVenta(criteria);

					if (valida == 0) {
						cadenaRetorno
								.append("No existe el Cdigo Venta ingresado ")
								.append("en el rango " + strColumna[0])
								.append(" nivel " + strColumna[1] + "<br>");
					}

				}

			} else {
				if (!StringUtils.equals(codigoVenta, "0")) { /*
															 * Ini
															 * PER-SiCC-2012-
															 * 0519 para campaa
															 */
					// -- Logica
					valida = mantenimientoLETPremioCampaniaDAO
							.getValidaCodigoVenta(criteria);
					List descripcionNivelesCampaniaList = mantenimientoLETParametroConcursoDAO
							.getDescripcionNivelesCampaniaList();
					String descripcionNivelesCampaniaString = "";
					for (int x = 0; x < descripcionNivelesCampaniaList.size(); x++) {
						Map descripcionNivelesCampania = (HashMap) descripcionNivelesCampaniaList
								.get(x);
						String nivel = String.valueOf(i + 1);
						if (descripcionNivelesCampania.get("nivelCampana")
								.toString().equals(strColumna[1])) {
							descripcionNivelesCampaniaString = descripcionNivelesCampania
									.get("descripcionNivelCampana").toString();
						}
					}

					if (valida == 0) {
						cadenaRetorno
								.append("No existe el Cdigo Venta ingresado ")
								.append("en el rango " + strColumna[0])
								.append(" nivel "
										+ descripcionNivelesCampaniaString
										+ "<br>");
					}

				}
			}
			/* FIN JJ PER-SiCC-2012-0201 */
		}

		log.info("Salio a AjaxServiceImpl - validaCodigoVentaLET(java.lang.String, java.lang.String) - Resultado:"
				+ cadenaRetorno.toString());
		return cadenaRetorno.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaExisteFacturadosAlarma(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String validaExisteFacturadosAlarma(String codigoCD,
			String codigoLinea, String codigoMarca, String codigoCanal,
			String codigoPeriodo, String codigoMapaCD) {
		String result = "";
		Map criteria = new HashMap();
		criteria.put("oidCentro", codigoCD);
		criteria.put("codMapCentrDist", codigoMapaCD);
		criteria.put("oidMapCentrDist",
				mantenimientoAPEAsignarVersionesProductosDAO
						.getOidMapaCDbyCod(criteria));
		criteria.put("codPeriodo", codigoPeriodo);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		int nExisteOidPeriodo = mantenimientoAPEAsignarVersionesProductosDAO
				.getExisteOidPeriodoCanalMarca(criteria);

		if (nExisteOidPeriodo == 0) {
			result = "-2";
		} else {
			criteria.put("oidPeriodo",
					mantenimientoAPEAsignarVersionesProductosDAO
							.getOidPeriodobyMarcaCanal(criteria));
			criteria.put("codLinea", codigoLinea);
			criteria.put("oidLinea", mantenimientoAPESubLineaArmadoDAO
					.getOidLineaArmadobyCodigo(criteria));

			String sDesCD = mantenimientoAPEEmitirAlarmaDAO
					.getDescMapaCentroCabec(criteria);
			String sDesLinea = mantenimientoAPEEmitirAlarmaDAO
					.getDescLineaArmado(criteria);
			String sDesMapaCD = mantenimientoAPEEmitirAlarmaDAO
					.getDescMapaCentroCabec(criteria);

			result = mantenimientoAPEEmitirAlarmaDAO
					.validaExisteFacturadosAlarma(criteria);

			if (result.equals(Constants.NUMERO_CERO)) {
				result = "No hay Asignacin de Productos Anaquel Activo para Facturacin para el CD: "
						+ sDesCD
						+ " , Lnea: "
						+ sDesLinea
						+ " , Marca: "
						+ codigoMarca
						+ " , Canal: "
						+ codigoCanal
						+ " , Perodo: "
						+ codigoPeriodo
						+ " , Mapa CD: "
						+ sDesMapaCD;

			} else {
				result = "1";
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaExisteGeneraEstimado(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validaExisteGeneraEstimado(String codLinea,
			String codPeriodo, String codMarca, String codCanal) {

		String result = "0";
		Map criteria = new HashMap();

		criteria.put("codLinea", codLinea);
		criteria.put("oidLinea", mantenimientoAPESubLineaArmadoDAO
				.getOidLineaArmadobyCodigo(criteria));
		criteria.put("codPeriodo", codPeriodo);
		criteria.put("codigoMarca", codMarca);
		criteria.put("codigoCanal", codCanal);

		int nExisteOidPeriodo = mantenimientoAPEAsignarVersionesProductosDAO
				.getExisteOidPeriodoCanalMarca(criteria);

		if (nExisteOidPeriodo > 0) {
			criteria.put("oidPeriodo",
					mantenimientoAPEAsignarVersionesProductosDAO
							.getOidPeriodobyMarcaCanal(criteria));

			result = mantenimientoAPEGenerarEstimadoProductoDAO
					.validaExisteGeneraEstimado(criteria);
		} else {
			result = "-1";
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaExisteMapaAnaquelDefault(
	 * java.lang.String)
	 */
	public String validaExisteMapaAnaquelDefault(String codMapaZona) {
		String result = "0";
		Map criteria = new HashMap();

		criteria.put("codMapaZona", codMapaZona);
		criteria.put("oidMapaZona", mantenimientoAPEOrdenAnaquelesDAO
				.getOidMapaZonaByCodMapaZona(criteria));

		result = mantenimientoAPEOrdenAnaquelesDAO
				.validaExisteMapaAnaquelDefault(criteria);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaExistePeriodo(java.lang.String
	 * )
	 */
	public String validaExistePeriodo(String codigoPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codPeriodo", codigoPeriodo);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		return mantenimientoAPEAsignarVersionesProductosDAO
				.getExisteOidPeriodoCanalMarca(criteria) + "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarBloqueoDigitacionPedidos
	 * (java.lang.String)
	 */
	public String validarBloqueoDigitacionPedidos(String codigoCliente) {
		Map criteria = new HashMap();
		criteria.put("codCliente", codigoCliente);
		return mantenimientoOCRCapturaPedidoDAO
				.validarBloqueoDigitacionPedidos(criteria);
	}

	public String validarCodigoCliente(String oidPais, String codigoCliente) {

		String error = new String();
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("codigoCliente", codigoCliente);

		try {

			if (procesoSTOEjecucionValidacionesDAO.getCodigoCliente(criteria) == null) {
				error = "N";
			} else {
				error = "S";
			}
		} catch (DataAccessException ignore) {

		}

		return error;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarCodigoSAP(java.lang.String,
	 * java.lang.String)
	 */
	public String validarCodigoSAP(String codigoPais, String codigoSAP) {

		String oidProducto;
		Map map = new HashMap();
		map.put("codigoSAP", codigoSAP);

		if (mantenimientoRECProductosFFNNEEDAO.getOidProducto(map) != null) {
			oidProducto = mantenimientoRECProductosFFNNEEDAO
					.getOidProducto(map);
		} else {
			oidProducto = null;
		}
		return oidProducto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarCodigosVentaProducto(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validarCodigosVentaProducto(String descripcion,
			String codigoVenta, String codigoSAP, String numeroCruce) {

		String size;
		Map map = new HashMap();
		map.put("descripcion", descripcion);
		map.put("codigoVenta", codigoVenta);
		map.put("codigoSAP", codigoSAP);
		map.put("numeroCruce", numeroCruce);

		if (procesoSTOEjecucionValidacionesDAO.getCodigoVentaPedidoList(map) != null) {
			size = String.valueOf(procesoSTOEjecucionValidacionesDAO
					.getCodigoVentaPedidoList(map).size());
		} else {
			size = "0";
		}
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarCodigosVentaProductoMatriz
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public String validarCodigosVentaProductoMatriz(String descripcion,
			String codigoVenta, String codigoSAP, String matriz,
			String numeroCruce) {

		String size;
		Map map = new HashMap();
		map.put("descripcion", descripcion);
		map.put("codigoVenta", codigoVenta);
		map.put("codigoSAP", codigoSAP);
		map.put("numeroCruce", numeroCruce);

		if (matriz == null) {
			if (procesoSTOEjecucionValidacionesDAO
					.getCodigoVentaMatrizList(map) != null) {
				size = String.valueOf(procesoSTOEjecucionValidacionesDAO
						.getCodigoVentaMatrizList(map).size());
			} else {
				size = "0";
			}
		} else {
			if (matriz.compareToIgnoreCase("1") == 0) {
				if (procesoSTOEjecucionValidacionesDAO
						.getCodigoVentaMatrizPrecioList(map) != null) {
					size = String.valueOf(procesoSTOEjecucionValidacionesDAO
							.getCodigoVentaMatrizPrecioList(map).size());
				} else {
					size = "0";
				}
			} else {
				if (procesoSTOEjecucionValidacionesDAO
						.getCodigoVentaMatrizIncentivoList(map) != null) {
					size = String.valueOf(procesoSTOEjecucionValidacionesDAO
							.getCodigoVentaMatrizIncentivoList(map).size());
				} else {
					size = "0";
			}
			}

		}
		return size;
	}

	public String validarCodigoVenta(String codigoPais, String codigoVenta,
			String periodo) {
		String error = new String();

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("periodo", periodo);

		try {

			if (ValidarCodigoVentaPeriodo(codigoPais, codigoVenta, periodo) == 1) {
				List listPaquete = procesoRECCargarTablaBoletaRecojoEspecialDAO
						.executeVerificacionTablaBoletaRecojoEspecial(criteria);

				if (listPaquete.size() > 0) {
					error = "F";
				} else {
					error = "T";
				}
			} else {
				error = "M";
			}
		} catch (DataAccessException ignore) {

		}

		return error;

	}

	public int ValidarCodigoVentaPeriodo(String codigoPais, String codigoVenta,
			String codigoPeriodo) {
		int indicador = 0;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("codigoPeriodo", codigoPeriodo);
		try {
			BigDecimal oidOferta = mantenimientoOCRPedidoControlFacturacionDAO
					.getOfertaDetalleByPeriodoCodigoVenta(criteria);
			if (oidOferta.intValue() == -1) {
				indicador = 0;
			} else {
				indicador = 1;
			}
		} catch (DataAccessException ignore) {
		}

		return indicador;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarCodigoVentaRxP(java.lang
	 * .String, java.lang.String)
	 */
	public String validarCodigoVentaRxP(String codigoPeriodo, String codigoVenta) {
		String error = "";
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoVenta", codigoVenta);

		String oidPeriodo = String.valueOf(reporteDAO.getOidPeriodo(criteria));
		criteria.put("oidPeriodo", oidPeriodo);

		List resultado = procesoINCCargaDespachoConcursoRxPDAO
				.validarCodigoVentaRxP(criteria);

		if (resultado.size() == 0) {
			error = getKeyMessage("procesoINCCargaDespachoConcursoRxPForm.msg.codigoVentaNoExiste");
		} else {
			Map detalle = (Map) resultado.get(0);
			String digitable = detalle.get("digitable").toString();
			String imprimible = detalle.get("imprimible").toString();
			String estrategia = detalle.get("estrategia").toString();

			if (digitable.equals(Constants.NUMERO_UNO)) {
				error = getKeyMessage("procesoINCCargaDespachoConcursoRxPForm.msg.codigoVentaDigitable");
			} else if (imprimible.equals(Constants.NUMERO_UNO)) {
				error = getKeyMessage("procesoINCCargaDespachoConcursoRxPForm.msg.codigoVentaImprimible");
			} else if (!estrategia.equals(Constants.INC_ESTRATEGIA_INDIVIDUAL)) {
				error = getKeyMessage("procesoINCCargaDespachoConcursoRxPForm.msg.codigoVentaNoIndividual");
			}
		}

		return error;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarCUVMatrizPreciosPremios(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue validarCUVMatrizPreciosPremios(String codigoVenta,
			String indicadorMatriz, String numeroBoleta) {
		Map criteria = new HashMap();
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("numeroCruce", numeroBoleta);
		LabelValue lv = new LabelValue();
		if (indicadorMatriz.equals(Constants.NUMERO_UNO)) {
			if (procesoSTOEjecucionValidacionesDAO
					.getCodigoVentaMatrizPrecioList(criteria) != null) {
				try {
					List l = procesoSTOEjecucionValidacionesDAO
							.getCodigoVentaMatrizPrecioList(criteria);
					CodigoVentaMatriz codigoVentaMatriz = new CodigoVentaMatriz();
					codigoVentaMatriz = (CodigoVentaMatriz) (l.get(0));
					lv.setLabel(codigoVentaMatriz.getDescripcion());
					lv.setValue(codigoVentaMatriz.getPrecio());
				} catch (Exception e) {
					lv = null;
				}
			} else {
				lv = null;
			}
		} else {
			if (procesoSTOEjecucionValidacionesDAO
					.getCodigoVentaMatrizIncentivoList(criteria) != null) {
				try {
					List l = procesoSTOEjecucionValidacionesDAO
							.getCodigoVentaMatrizIncentivoList(criteria);
					CodigoVentaMatriz codigoVentaMatriz = new CodigoVentaMatriz();
					codigoVentaMatriz = (CodigoVentaMatriz) (l.get(0));
					lv.setLabel(codigoVentaMatriz.getDescripcion());
					lv.setValue(codigoVentaMatriz.getPrecio());
				} catch (Exception e) {
					lv = null;
				}
			} else {
				lv = null;
			}
		}
		return lv;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarDetalle(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validarDetalle(String codigoPais, String codigoVenta,
			String periodo, String codcliente, String numlote) {

		String error = new String();

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("periodo", periodo);
		criteria.put("codCliente", codcliente);
		criteria.put("numLote", numlote);

		try {

			if (ValidarCodigoVentaPeriodo(codigoPais, codigoVenta, periodo) == 1) {
				List listPaquete = procesoSTOEjecucionValidacionesDAO
						.verificacionDetalles(criteria);

				if (listPaquete.size() > 0) {
					error = "F";
				} else {
					error = "T";
				}
			} else {
				error = "M";
			}
		} catch (DataAccessException ignore) {

		}

		return error;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaRegionCerrada(java.lang.String
	 * , java.lang.String, java.util.ArrayList)
	 */
	public String validaRegionCerrada(String codigoPais, String codigoPeriodo,
			ArrayList regionesList) {

		String mensaje = null;
		Map criteria;

		for (int i = 0; i < regionesList.size(); i++) {
			criteria = new HashMap();
			criteria.put("codigoRegion", regionesList.get(i));
			criteria.put("codigoPeriodo", codigoPeriodo);
			if (procesoLETCargaPedidosObjetivosExcelDAO
					.validaRegionCerrada(criteria) > 0) {
				mensaje = getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeAlertaRegionesInvalidas");
				break;
			}
		}

		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarConsideracion()
	 */
	public String getValidarConsideracion(String textoXml, String tipoMensaje, List listConsideraciones, List listRestricciones) {
		// validamos xml xerox SI ES GRUPO 2 :FIJO 1:VARIABLE
		log.debug("tipoMnesaje::: " + tipoMensaje);
		if (Constants.NRO_UNO.equals(tipoMensaje)) {
			Map map = new HashMap();
			map.put("indValid", Constants.SI);
			List listVariablesValidas = mantenimientoMENGenericoDAO
					.getVariables(map);
			Iterator it = listVariablesValidas.iterator();
			while (it.hasNext()) {
				String variable = (String) it.next();
				textoXml = textoXml.replaceAll(variable, " ");// X CADENA VACIA
			}

			log.debug("XML SIN VARIABLES >>> " + textoXml);
			String mensajeError = StringUtil.validaXml(textoXml);
			log.debug("msg " + mensajeError);
			if (mensajeError != null && !mensajeError.equals("")) {
				return "-1_" + mensajeError;
			}
		} else {
			log.debug("XML >>> " + textoXml);
			String mensajeError = StringUtil.validaXml(textoXml);
			if (mensajeError != null && !mensajeError.equals("")) {
				return "-1_" + mensajeError;
			}
		}
		
		/* INI SA PER-SiCC-2012-0998 */
		// si es fijo no se valida consideraciones y restricciones
		/*if (Constants.NUMERO_DOS.equals(tipoMensaje))
			return "0_0";*/
		/* FIN SA PER-SiCC-2012-0998 */

		
		//List listConsideraciones = (List) session.getAttribute("msgPatronConsideracionList");
		//List listRestricciones = (List) session.getAttribute("msgPatronRestriccionList");

		if (listConsideraciones != null && listConsideraciones.size() > 0) {
			// puede ser que se tenga una lista de eliminadas se busca los no
			// eliminados
			int contConsideracion = 0;
			for (int i = 0; i < listConsideraciones.size(); i++) {
				Map bean = (Map) listConsideraciones.get(i);
				String indicadorAccion = (String) bean.get("indicadorAccion");
				if (!StringUtils.equals(indicadorAccion, Constants.NUMERO_DOS))
					contConsideracion++;
			}
			if (contConsideracion > 0)
				return "0_" + contConsideracion;
		}

		if (listRestricciones != null && listRestricciones.size() > 0) {
			// puede ser que se tenga una lista de eliminadas se busca los no
			// eliminados
			int cont = 0;
			for (int i = 0; i < listRestricciones.size(); i++) {
				Map bean = (Map) listRestricciones.get(i);
				String indicadorAccion = (String) bean.get("indicadorAccion");
				if (!StringUtils.equals(indicadorAccion, Constants.NUMERO_DOS))
					cont++;
			}
			if (cont > 0)
				return "0_" + cont;
		}

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidarConsRest(java.lang.String
	 * )
	 */
	public String getValidarConsRest(String codigoConsideracion,
			String tipoConsideracion, List listConsideracion, List listRestriccion) {
		log.debug("iniico getValidarConsRest " + codigoConsideracion + " tipo "
				+ tipoConsideracion);
		

		Map map = new HashMap();
		map.put("codigo", codigoConsideracion);

		List listC = null;
		List listR = null;
		String abrevConRes = "";
		if (tipoConsideracion.equals(Constants.MEN_TIPO_CONSIDERACION)) {
			listC = mantenimientoMENGenericoDAO.getConsideracion(map);
			Map bean = (Map) listC.get(0);
			abrevConRes = (String) bean.get("abrevConRes");
		} else {
			listR = mantenimientoMENGenericoDAO.getRestriccion(map);
			Map bean = (Map) listR.get(0);
			abrevConRes = (String) bean.get("abrevConRes");
		}

		log.debug("abrevConRes " + abrevConRes);

		// if(tipoConsideracion.equals(Constants.MEN_TIPO_CONSIDERACION)){
		//List listConsideracion = (List) session.getAttribute("msgPatronConsideracionList");
		Iterator it = listConsideracion.iterator();
		while (it.hasNext()) {
			Map beanC = (Map) it.next();
			String abrev = (String) beanC.get("abrev");
			if (StringUtils.equals(abrev, abrevConRes)) {
				return codigoConsideracion;
			}
		}
		// }else{
		//List listRestriccion = (List) session.getAttribute("msgPatronRestriccionList");
		it = listRestriccion.iterator();
		while (it.hasNext()) {
			Map beanR = (Map) it.next();
			String abrev = (String) beanR.get("abrev");
			if (StringUtils.equals(abrev, abrevConRes)) {
				return codigoConsideracion;
			}
		}
		// }

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarMensajesPatron()
	 */
	public String getValidarMensajesPatron(List list) {
		//List list = (List) session.getAttribute("msgMensajePatronList");
		Iterator it = list.iterator();
		int cont = 0;
		while (it.hasNext()) {
			Map bean = (Map) it.next();
			String indicadorAccion = (String) bean.get("indicadorAccion");
			if (!StringUtils.equals(indicadorAccion, Constants.NUMERO_DOS)) {
				cont++;
			}
		}
		log.debug("cont " + cont);
		return (cont == 0 ? "" : "" + cont);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getProcesosFDVByPaisAnyoPeriodo
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getProcesosFDVByPaisAnyoPeriodo(String codigoPais,
			String anyoProceso, String periodoAnyoProceso) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("periodoProceso", periodoAnyoProceso);
		criteria.put("anyoPeriodoProceso", anyoProceso);
		criteria.put("statusProceso",
				Constants.PROCESO_CLUSTER_COD_DIST_REALIZADA);

		List listaProcesos = procesoFDVClusterizacionDAO
				.getProcesosClusterByCriteria(criteria);

		if (listaProcesos != null && listaProcesos.size() > 0) {
			result = new LabelValue[listaProcesos.size()];
			for (int i = 0; i < listaProcesos.size(); i++) {
				ProcesoFDVClusterizacion proceso = (ProcesoFDVClusterizacion) listaProcesos
						.get(i);
				LabelValue lv = new LabelValue(String.format(
						Constants.FORMATO_NOMBRE_PROCESO_LARGO_FDV,
						proceso.getAnyoPerProc(), proceso.getPerProc(),
						proceso.getVersionProceso(), proceso.getNomProc()),
						proceso.getCodProc());
				result[i] = lv;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesFDVByProceso(java.lang
	 * .String, java.lang.String)
	 */
	public LabelValue[] getRegionesFDVByProceso(String codigoProceso) {

		LabelValue[] result = null;

		List regiones = procesoFDVClusterizacionDAO
				.getRegionesByProceso(codigoProceso);

		if (regiones != null && regiones.size() > 0) {
			result = new LabelValue[regiones.size()];
			for (int i = 0; i < regiones.size(); i++) {
				GenericBean accion = (GenericBean) regiones.get(i);
				LabelValue lv = new LabelValue(accion.getDescripcion(),
						accion.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasFDVByProcesoRegion(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasFDVByProcesoRegion(String codigoRegion,
			String codigoProceso) {

		LabelValue[] result = null;

		List zonas = procesoFDVClusterizacionDAO.getZonasByProcesoRegion(
				codigoRegion, codigoProceso);

		if (zonas != null && zonas.size() > 0) {
			result = new LabelValue[zonas.size()];
			for (int i = 0; i < zonas.size(); i++) {
				GenericBean accion = (GenericBean) zonas.get(i);
				LabelValue lv = new LabelValue(accion.getDescripcion(),
						accion.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	/* INI SA PER-SICC-2011-0803 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesByOidSubGerencia(java
	 * .lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesByOidSubGerencia(String oidSubGerencia,
			String condicionTodos) {

		log.debug("Entro a getRegionesByOidSubGerencia");
		log.debug("oidSubGerencia: " + oidSubGerencia);
		log.debug("condicionTodos: " + condicionTodos);

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("oidSubGerencia", oidSubGerencia);

			List regiones = mantenimientoINCConfiguracionConcursoDAO
					.getRegionesByOidSubGerencia(criteria);
			if (regiones != null && regiones.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[regiones.size() + 1];
					result[0] = new LabelValue("Todos", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base periodo = (Base) regiones.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[regiones.size()];
					for (int i = 0; i < regiones.size(); i++) {
						Base concurso = (Base) regiones.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasByMultipleOidRegiones(java
	 * .util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getZonasByMultipleOidRegiones(ArrayList oidRegion,
			String condicionTodos) {

		log.debug("Entro a getZonasByMultipleOidRegiones");
		log.debug("oidRegion: " + oidRegion);
		log.debug("condicionTodos: " + condicionTodos);

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("oidRegion", oidRegion);

			List zonas = mantenimientoINCConfiguracionConcursoDAO
					.getZonasByMultipleOidRegiones(criteria);
			if (zonas != null && zonas.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[zonas.size() + 1];
					result[0] = new LabelValue("Todos", "");
					for (int i = 0; i < zonas.size(); i++) {
						Base periodo = (Base) zonas.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[zonas.size()];
					for (int i = 0; i < zonas.size(); i++) {
						Base concurso = (Base) zonas.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getDatosTipoConcurso(java.lang.
	 * String)
	 */
	public String getDatosTipoConcurso(String oidTipoConcurso) {
		Map criteria = new HashMap();
		criteria.put("oidTipoConcurso", oidTipoConcurso);
		Map datos = mantenimientoINCConfiguracionConcursoDAO
				.getDatosTipoConcurso(oidTipoConcurso);

		String resultado = datos.get("oidBaseCalculo").toString();
		if(datos.get("oidTipoPrograma")!=null)
		resultado = resultado + "__" + datos.get("oidTipoPrograma").toString();
		else
			resultado = resultado + "__ ";
			
		resultado = resultado + "__" + datos.get("oidPlantilla").toString();

		if (datos.get("oidConcursoIVR") != null)
			resultado = resultado + "__"
					+ datos.get("oidConcursoIVR").toString();
		else
			resultado = resultado + "__";

		return resultado;
	}

	/* INI SA PER-SICC-2011-0803 */


	public String getIndExpress(String codOper) {
		HashMap criteria = new HashMap();
		criteria.put("codigoOperacion", codOper);
		return mantenimientoRECIngresoMasivoOperacionesDAO
				.getIndExpress(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getloadRegionesDisponibles(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue[] getloadRegionesDisponibles(String codigoPais,
			String codigoPeriodo, String periodoInicial, String periodoFinal,
			String regionesSeleccionadas, String codigoPrograma) {

		log.debug("Entro a getloadRegionesDisponibles");
		log.debug("codigoPais: " + codigoPais);
		log.debug("codigoPeriodo: " + codigoPeriodo);
		log.debug("periodoInicial: " + periodoInicial);
		log.debug("periodoFinal: " + periodoFinal);
		log.debug("regionesSeleccionadas: " + regionesSeleccionadas);
		log.debug("codigoPrograma: " + codigoPrograma);

		LabelValue[] result = null;
		LabelValue[] resultReturn = null;

		String[] seleccionadas = {};
		int indice = 0;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("periodoInicial", periodoInicial);
			criteria.put("periodoFinal", periodoFinal);
			criteria.put("codigoPrograma", codigoPrograma);

			List regiones = mantenimientoDAO
					.getloadRegionesDisponibles(criteria);
			// List uasGuardadas =
			// mantenimientoDAO.getListUnidadesAdministrativas(criteria);

			if (!StringUtils.isBlank(regionesSeleccionadas)) {
				seleccionadas = StringUtils.split(regionesSeleccionadas, "|");

			}

			if (regiones != null && regiones.size() > 0) {

				result = new LabelValue[regiones.size() + 1];
				result[0] = new LabelValue("Todos", "Todos");

				for (int i = 0; i < regiones.size(); i++) {
					Base periodo = (Base) regiones.get(i);

					if (!verificarSeleccionRegion(seleccionadas, periodo)) {
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[indice + 1] = lv;
						indice++;
					}
				}

				if (result != null) {
					resultReturn = new LabelValue[indice+1];
					System.arraycopy(result, 0, resultReturn, 0, indice+1);
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return resultReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getZonasDisponiblesRegion(java.
	 * lang.String, java.lang.String, java.util.ArrayList)
	 */
	public LabelValue[] getZonasDisponiblesRegion(String codigoPais,
			String codigoPeriodo, ArrayList oidRegion, String listaZonas,
			String periodoInicial, String periodoFinal) {
		log.debug("Entro a getZonasDisponiblesRegion");
		log.debug("codigoPais: " + codigoPais);
		log.debug("codigoPeriodo: " + codigoPeriodo);
		log.debug("oidRegion: " + oidRegion);
		log.debug("listaZonas: " + listaZonas);

		LabelValue[] result = null;
		String[] datosZona = listaZonas.split("_");
		String zona = "";

		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("oidRegion", oidRegion);
			criteria.put("periodoInicial", periodoInicial);
			criteria.put("periodoFinal", periodoFinal);

			List zonas = mantenimientoDAO.getZonasDisponiblesRegion(criteria);
			List zonaAux = new ArrayList();
			boolean agrega = true;

			if (zonas != null && zonas.size() > 0) {
				for (int j = 0; j < zonas.size(); j++) {
					Base periodo = (Base) zonas.get(j);
					log.debug("zona1: " + periodo.getCodigo());
					if (periodo.getCodigo() != null) {
						for (int t = 0; t < datosZona.length; t++) {
							zona = datosZona[t];
							log.debug("zona2: " + zona);

							if (periodo.getCodigo().equals(zona)) {
								log.debug("Elimina zona: " + zona);
								agrega = false;
								break;
							}
						}
						if (agrega) {
							log.debug("Agrega zona: " + zona);
							zonaAux.add(periodo);
						}
						agrega = true;
					}
				}
			}

			if (zonaAux != null && zonaAux.size() > 0) {
				result = new LabelValue[zonaAux.size() + 1];
				result[0] = new LabelValue("Todos", "Todos");

				for (int i = 0; i < zonaAux.size(); i++) {
					Base periodo = (Base) zonaAux.get(i);

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i + 1] = lv;
				}
			}

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/* INI SA PER-SiCC-2012-0459 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCiudadesByRegion(java.lang.String
	 * )
	 */
	public LabelValue[] getCiudadesByRegion(String codigoRegion) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegion);

		List listaCiudades = mantenimientoMAEClienteDAO.getCiudades(criteria);

		try {
			if (listaCiudades != null && listaCiudades.size() > 0) {

				result = new LabelValue[listaCiudades.size()];

				for (int i = 0; i < listaCiudades.size(); i++) {
					Base clasificacion = (Base) listaCiudades.get(i);

					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(),
							clasificacion.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/* FIN SA PER-SiCC-2012-0459 */

	/**
	 * Verifica si una region ya fue seleccionada
	 * 
	 * @param seleccionadas
	 * @param region
	 * @return
	 */
	private boolean verificarSeleccionRegion(String[] seleccionadas, Base region) {
		boolean ret = false;

		if (seleccionadas != null && seleccionadas.length > 0) {
			for (int i = 0; i < seleccionadas.length; i++) {
				if (StringUtils.equals(seleccionadas[i], region.getCodigo())) {
					ret = true;
					break;
				}
			}
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaInsertarUnidadesAdministrativas
	 * (java.lang.String, java.lang.String, java.util.ArrayList,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public Integer validaInsertarUnidadesAdministrativas(String codigoPais,
			String codigoPeriodo, ArrayList oidRegion, String listaZonas,
			String periodoInicial, String periodoFinal) {

		log.debug("entro a validaInsertarUnidadesAdministrativas");
		log.debug("codigoPais: " + codigoPais);
		log.debug("codigoPeriodo: " + codigoPeriodo);
		log.debug("periodoInicial: " + periodoInicial);
		log.debug("periodoFinal: " + periodoFinal);
		log.debug("oidRegion: " + oidRegion);
		Integer result = 0;
		try {

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("periodoInicial", periodoInicial);
			criteria.put("periodoFinal", periodoFinal);
			criteria.put("oidRegion", oidRegion);

			List zonas = mantenimientoDAO.getZonasDisponiblesRegion(criteria);
			int cantZonasTotal = mantenimientoDAO.getCantZonasxRegion(criteria);
			log.debug(zonas.size() + " != " + cantZonasTotal);
			if (zonas.size() != cantZonasTotal) {
				result = 1;
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}

		return result.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSaldoProducto(java.lang.String,
	 * java.lang.String)
	 */
	public Integer getSaldoProducto(String numeroPedido, String codigoVenta, String codigoPais) {

		Map criteria = new HashMap();
		String valorParametro = Constants.NO;
		int valorProducto = 1;
		try {
			
			if(StringUtils.isNotBlank(codigoVenta))				
			{
			criteria.put("numeroPedido", numeroPedido);
			criteria.put("codigoVenta", codigoVenta);
			criteria.put("codigoPais", codigoPais);

				valorParametro = mantenimientoRECDigitacionCDRDAO.getIndicadorValorStock(criteria);

			if (valorParametro.equals(Constants.SI)) {
					valorProducto = mantenimientoRECDigitacionCDRDAO.getSaldoProducto(criteria);
				}
			}

		} catch (Exception e) {
			log.warn(e.getMessage());
		}

		if (log.isDebugEnabled())
			log.debug("resultado getSaldoProducto: " + valorProducto);
		
		return valorProducto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getTiposOperaMultipleByOperaxCodigoOid
	 * (java.lang.String, java.util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getTiposOperaMultipleByOperaxCodigoOid(
			String codigoPais, ArrayList codigoOperaciones,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoOperacion", codigoOperaciones);

			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (codigoOperaciones.size() == 0) {
					return result;
				}

				List tipoOperacionList = reporteDAO
						.getTiposOperaMultipleByOperaxCodigoOid(criteria);

				if (tipoOperacionList != null && tipoOperacionList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[tipoOperacionList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base periodo = (Base) tipoOperacionList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[tipoOperacionList.size()];
						for (int i = 0; i < tipoOperacionList.size(); i++) {
							Base concurso = (Base) tipoOperacionList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(), concurso
											.getCodigo().toString());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getObtenerMsgHtml(java.lang.String,
	 * java.lang.String)
	 */
	public String[] getObtenerMsgHtml(String id, String campanha, StringBuffer msg, List list) {
		
		String[] result = null;
		List listTxt = new ArrayList();
		msg = new StringBuffer();
		log.debug("id>>>:_::: " + id);
		if (id != null) {
			try {

				//List list = (List) session.getAttribute(Constants.MEN_PATRON_LIST);
				Map map = (Map) list.get(Integer.parseInt(id) - 1);
				//
				// armamos la lista para quea pintado como arbol d etres niveles
				// campanhaProceso
				Map criteria = new HashMap();
				criteria.put("campanhaProceso", map.get("campanhaProceso"));
				criteria.put("codigoDocumento", map.get("codigoDocumento"));
				List listDocumentos = mantenimientoMENGenericoDAO
						.getDocumentosPatron(criteria);// todos los documens de
														// la campanha
				List listSecciones = null;
				Iterator it = listDocumentos.iterator();
				int cont = 0;
				while (it.hasNext()) {
					Map m = (Map) it.next();
					// resultado.add(m);//anhadimos el documento nivel 0
					String codigoDocumento = String.valueOf(m.get("oid"));
					criteria.put("codigoDocumento", codigoDocumento);
					//
					String texto = (String) m.get("descripcion");
					msg.append("<strong>Documento:</strong> " + texto
							+ "<br />\n");
					// por cada documento anhadimo sus secciones
					listSecciones = mantenimientoMENGenericoDAO
							.getSeccionDocumentoPatron(criteria);// todos los
																	// secciones
																	// del
																	// documento
																	// y
																	// campanha
					Iterator it2 = listSecciones.iterator();
					while (it2.hasNext()) {
						Map beanSecc = (Map) it2.next();
						// resultado.add(beanSecc);//anhadimos la seccion nivel
						// 1
						String oidSeccion = String.valueOf(beanSecc.get("oid"));
						criteria.put("oidSeccion", oidSeccion);

						texto = (String) beanSecc.get("descripcion");
						msg.append("<strong>Seccion :</strong> " + texto
								+ "<br />\n");

						List listMensajes = mantenimientoMENGenericoDAO
								.getMensajesSeccionDocumentoPatron(criteria);
						Iterator it3 = listMensajes.iterator();
						while (it3.hasNext()) {
							Map beanMsg = (Map) it3.next();
							String oidMsg = String.valueOf(beanMsg.get("oid"));
							criteria.put("oid", oidMsg);
							List listMsg = mantenimientoMENGenericoDAO
									.getMensajeByOid(criteria);
							Map mensaje = (Map) listMsg.get(0);
							String vtexto = (String) mensaje.get("valorTexto");
							String vhtexto = (String) mensaje
									.get("valorTextoHtml");
							if (StringUtils.isNotEmpty(vhtexto)) {
								msg.append(vhtexto + "<br />\n");
							} else {
								listTxt.add(cont + "__" + vtexto);
								msg.append(cont + "__MSG" + "<br/>\n");// TEXTO
																		// A
																		// REEMPLZAR
								cont++;
							}
						}
						// resultado.addAll(listMensajes);
					}
				}

			} catch (Exception e) {
				log.debug("error " + e.getMessage());
			}
			result = new String[listTxt.size()];
			for (int i = 0; i < listTxt.size(); i++) {
				result[i] = (String) listTxt.get(i);
			}
			//session.setAttribute("msgHtml", msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaMsgHtml(java.lang.String[])
	 */
	public String validaMsgHtml(String[] msgHtml) {
		
		// log.debug("msgHtml "+msgHtml);
		// List list = Arrays.asList(msgHtml);
		// log.debug(list.size());
		//session.setAttribute("arrMsgHtml", msgHtml);
		return Constants.NUMERO_UNO;
	}

	/* Ini PER-SiCC-2012-0558 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getPeriodoCDR(java.lang.String,
	 * java.lang.String)
	 */
	public LabelValue getPeriodoCDR(String codigo, String indicadorExpress) {
		log.debug("__getPeriodoCDR");
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigo);
		criteria.put("indicadorExpress", indicadorExpress);
		String oidPeriodo = mantenimientoRECDigitacionCDRDAO
				.getPeriodoCDR(criteria);
		log.debug("resultado " + oidPeriodo);

		String descPeriodo = null;

		if (StringUtils.isNotEmpty(oidPeriodo)
				&& !StringUtils.equals(oidPeriodo, "0")) {
			criteria.put("oidPeriodo", oidPeriodo);
			descPeriodo = mantenimientoRECDigitacionCDRDAO
					.getDescripcionPeriodo(criteria);
		} else {
			descPeriodo = "0";
		}

		LabelValue result = new LabelValue(oidPeriodo, descPeriodo);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCodigoOperacionCorrecto(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue getCodigoOperacionCorrecto(String datoCompuesto,
			String codigoMotivo, String indicadorExpress, String codigoPais,
			String numeroPedido, String varOidPeriCDR, String usuario, String indicadorExcluye) {
		
		if(StringUtils.equalsIgnoreCase(indicadorExcluye, Constants.SI))
			return null;

		String[] datos = StringUtils.split(datoCompuesto, '|');
		String codigoOperacion = datos[0];
		String tipoOperacionSsicc = datos[1];
		String codigoOperacionSsicc = datos[2];

		Map criteria = new HashMap();
		criteria.put("codigoOperacion", codigoOperacionSsicc);
		criteria.put("tipoOperacion", tipoOperacionSsicc);
		criteria.put("codigoMotivo", codigoMotivo);
		criteria.put("indicadorExpress", indicadorExpress);
		criteria.put("codigoPais", codigoPais);
		criteria.put("numeroPedido", numeroPedido);
		criteria.put("idPeriodoCDR", Integer.parseInt(varOidPeriCDR));
		criteria.put("usuario", usuario);

		mantenimientoRECDigitacionCDRDAO.getCodigoOperacionCorrecto(criteria);

		log.debug("codigoOperacionCorrecto >>>>>>>>> "
				+ criteria.get("codOperCorrecto"));
		log.debug("mensaje >>>>>>>>> " + criteria.get("mensaje"));

		LabelValue result = null;

		if (StringUtils.isNotBlank(MapUtils.getString(criteria, "mensaje", ""))) {
			result = new LabelValue(MapUtils.getString(criteria,
					"codOperCorrecto", ""), MapUtils.getString(criteria,
					"mensaje", ""));
		} else {
			result = new LabelValue(MapUtils.getString(criteria,
					"codOperCorrecto", ""), null);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValExcepProduGanador(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String getValExcepProduGanador(String idPeriodoCDR,
			String codigoVenta, String codigoOperacionSic,
			String tipoOperacionSic, String oidSoliPosi, String codigoMotivo, String codigoPais, String valorParametro, String indicadorExcluye) {
		
		
		Map criteria = new HashMap();
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("codigoOperacionSic", codigoOperacionSic);
		criteria.put("tipoOperacionSic", tipoOperacionSic);
		criteria.put("oidSoliPosi", Long.parseLong(oidSoliPosi));
		criteria.put("idPeriodoCDR", Integer.parseInt(idPeriodoCDR));
		criteria.put("codigoMotivo", codigoMotivo);
		
		String mensaje = null;
		
		if(!StringUtils.equalsIgnoreCase(indicadorExcluye, Constants.SI))
			mensaje = mantenimientoRECDigitacionCDRDAO.getValExcepProduGanador(criteria);
		
		//Obteniendo el dato de parametro STO_IND_MOT_ACEP_CDR
		Map criteria1 = new HashMap();
		criteria1.put("codigoPais", codigoPais);

		String valor = valorParametro;
		
		String reemplazar = "0";
		
		if(StringUtils.equalsIgnoreCase(valor, Constants.NUMERO_UNO)){
			String varAcepCDR = mantenimientoRECDigitacionCDRDAO.getValiAceptaCDR(criteria);
			log.debug("Indicador STO_IND_MOT_ACEP_CDR esta activo");
			if(varAcepCDR!= null)
			{
				//se debe reemplazar la operacion y el codigo de motivo
				criteria1.put("codigoParametro", Constants.STO_IND_MOT_ACEP_CDR);
				valor = this.interfazOCRDAO.getSTOParametroGeneralOCR(criteria1);
				reemplazar = "1|"+valor;
				log.debug("varAcepCDR es "+ varAcepCDR+", diferente de NULO debe reemplazar operacion y motivo");
			}else{
				String verifica = mantenimientoRECDigitacionCDRDAO.getVerificaOperacion(criteria);
				if(verifica!=null && StringUtils.isNotBlank(verifica)&& !StringUtils.equalsIgnoreCase(verifica, Constants.NUMERO_CERO))
				{
					//se debe reemplazar solo la operacion
					reemplazar = "2";
					log.debug("varAcepCDR es nullo pero verifica es: "+verifica+", se debe reemplazar SOLO operacion");
				}
			}
		}

		return mensaje+"|"+reemplazar;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValExcepProduDesea(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValExcepProduDesea(String codPeriodo,
			String codigoVenta, String codigoOperacionSic,
			String tipoOperacionSic, String oidSoliPosi, String codigoMotivo, String indicadorExcluye, String numeroBoletaDespacho) {
		
		if(StringUtils.equalsIgnoreCase(indicadorExcluye, Constants.SI))
			return null;
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codPeriodo);
		int idPeriodoCDR = this.reporteService.getOidPeriodo(criteria);
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("codigoOperacionSic", codigoOperacionSic);
		criteria.put("tipoOperacionSic", tipoOperacionSic);
		criteria.put("idPeriodoCDR", idPeriodoCDR);
		criteria.put("codigoMotivo", codigoMotivo); 
		criteria.put("numeroBoletaDespacho", numeroBoletaDespacho);
		
		try {
			criteria.put("oidSoliPosi", Long.parseLong(oidSoliPosi));
		}
		catch(Exception e) {
			criteria.put("oidSoliPosi", new Long(0));
		}
		
		String mensaje = mantenimientoRECDigitacionCDRDAO.getValExcepProduDesea(criteria);
		
		return mensaje;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValUnidadReclamo(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValUnidadReclamo(String codigopais, String codoCliente,
			String idSoliPosi, String unidadesReclamar, String codOperSICC,
			String tipoOperSICC, String indicadorExcluye) {
		if(StringUtils.equalsIgnoreCase(indicadorExcluye, Constants.SI))
			return null;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigopais);
		criteria.put("codigoCliente", codoCliente);
		criteria.put("oidSoliPosi", Long.parseLong(idSoliPosi));
		criteria.put("unidadReclamo", Long.parseLong(unidadesReclamar));
		// Ini PER-SiCC-2012-0642
		criteria.put("codOperSICC", codOperSICC);
		criteria.put("tipoOperSICC", tipoOperSICC);
		// Fin PER-SiCC-2012-0642
		String mensaje = mantenimientoRECDigitacionCDRDAO
				.getValUnidadReclamo(criteria);
		// log.debug("mensaje >>>>>>>>> "+criteria.get("mensaje"));

		

		return mensaje;

	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValUnidadDesea(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValUnidadDesea(String codigopais, String codoCliente,
			String idSoliPosi, String unidadesDesea, String codOperSICC,
			String tipoOperSICC, String indicadorExcluye) {
		
		if(StringUtils.equalsIgnoreCase(indicadorExcluye, Constants.SI))
			return null;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigopais);
		criteria.put("codigoCliente", codoCliente);
		criteria.put("unidadesDesea", Long.parseLong(unidadesDesea));
		// Ini PER-SiCC-2012-0642
		criteria.put("codOperSICC", codOperSICC);
		criteria.put("tipoOperSICC", tipoOperSICC);

		try {
			criteria.put("oidSoliPosi", Long.parseLong(idSoliPosi));
		}
		catch(Exception e) {
			criteria.put("oidSoliPosi", new Long(0));
		}
		String mensaje = mantenimientoRECDigitacionCDRDAO.getValUnidadDesea(criteria);
		
		return mensaje;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCabeceraConsultoraByDocIdentidad
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public DatosConsultoraBasico[] getCabeceraConsultoraByDocIdentidad(
			String codigoPais, String numeroDocumento, String tipoDocumento,
			String codigoConsultora) {
		int cont = 1;
		DatosConsultoraBasico[] result = null;
		DatosConsultoraBasico datosConsultoraBasico = null;
		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);
		criteria.put("numeroDocumentoIdentidad", numeroDocumento);
		criteria.put("codigoConsultora", codigoConsultora);

		log.debug("Ejecutando AjaxServiceImpl.getCabeceraConsultoraByDocIdentidad");
		try {
			List resultado = mantenimientoCCCDigitacionPagosChequesDAO
					.getCabeceraConsultoraByDocIdentidad(criteria);
			log.debug("-->>>>" + resultado.toString());
			if (resultado.size() == 1) {
				datosConsultoraBasico = (DatosConsultoraBasico) resultado
						.get(0);
				result = new DatosConsultoraBasico[++cont];
				result[0] = datosConsultoraBasico;
				log.debug("ajax getCabeceraConsultoraByDocIdentidad " + result);
			}
		} catch (Exception e) {
			log.debug("ajax Error getCabeceraConsultoraByDocIdentidad "
					+ result);
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getSucursalBancoCheque(java.lang
	 * .String)
	 */
	public LabelValue[] getSucursalBancoCheque(String codigoBanco) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoBanco", codigoBanco);

		List sucursales = mantenimientoCCCDigitacionPagosChequesDAO
				.getSucursalesCheques(criteria);

		if (sucursales != null && sucursales.size() > 0) {
			result = new LabelValue[sucursales.size()];
			for (int i = 0; i < sucursales.size(); i++) {
				BaseOID baseOID = (BaseOID) sucursales.get(i);

				result[i] = new LabelValue(baseOID.getDescripcion(), baseOID
						.getOid().toString());
			}
		}
		return result;
	}
	/* Fin PER-SiCC-2012-0558 */

	/* INI SA VEN-SiCC-2012-0101 */
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getListPremiosFaltantes(java.lang.String)
	 */
	public LabelValue[] getListPremiosFaltantes(String numeroConcurso) {
		LabelValue[] result = null;
		
		List premios = procesoINCReemplazoPremioBolsaFaltantesDAO.getListPremiosFaltantes(numeroConcurso);

		if (premios != null && premios.size() > 0) {
			result = new LabelValue[premios.size()+1];
			
			result[0] = new LabelValue("", "");
			for (int i = 0; i < premios.size(); i++) {
				Base base = (Base) premios.get(i);

				result[i+1] = new LabelValue(base.getDescripcion(), base.getCodigo());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getListPremiosReemplazos(java.lang.String)
	 */
	public LabelValue[] getListPremiosReemplazos(String numeroConcurso) {
		LabelValue[] result = null;
		
		List premios = procesoINCReemplazoPremioBolsaFaltantesDAO.getListPremiosReemplazos(numeroConcurso);

		if (premios != null && premios.size() > 0) {
			result = new LabelValue[premios.size()+1];
			
			result[0] = new LabelValue("", "");
			for (int i = 0; i < premios.size(); i++) {
				Base base = (Base) premios.get(i);

				result[i+1] = new LabelValue(base.getDescripcion(), base.getCodigo());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}

		return result;
	}
	/* FIN SA VEN-SiCC-2012-0101 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getSubTipoClienteMultipleByOidTipoCliente(java.util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getSubTipoClienteMultipleByOidTipoCliente(
			ArrayList oidTipoCliente, String condicionTodos) {

		log.debug("Entering 'getSubTipoClienteMultipleByOidTipoCliente'");
		
		LabelValue[] result = null;
		
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		
		try {	
			result = new LabelValue[1];
			result[0] = new LabelValue("Todos", "");
		
			if (oidTipoCliente.size()==0) {
				return result;
			}
		
			List subTipoCliente = interfazSiCCDAO.getLista(
					"getSubTipoClienteMultipleByOidTipoCliente", criteria);
		
			if (subTipoCliente != null && subTipoCliente.size() > 0) {
				
				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[subTipoCliente.size() + 1 ];
					result[0] = new LabelValue("Todos", "");
					for (int i = 0; i < subTipoCliente.size(); i++) {
						BaseOID baseOID = (BaseOID) subTipoCliente.get(i);
						
						LabelValue lv  = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
						result[i + 1] = lv;
					}
					
				}else {
					result = new LabelValue[subTipoCliente.size()];

					for (int i = 0; i < subTipoCliente.size(); i++) {
						BaseOID baseOID = (BaseOID) subTipoCliente.get(i);

						result[i] = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
					}
				}
				
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
			ignore.printStackTrace();
		}
		return result;
	
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTipoClasificacionMultipleByOidSubTipoCliente(java.util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getTipoClasificacionMultipleByOidSubTipoCliente(
			ArrayList oidSubTipoCliente, String condicionTodos) {

		log.debug("Entering 'getTipoClasificacionMultipleByOidSubTipoCliente'");
		
		LabelValue[] result = null;
		
		Map criteria = new HashMap();
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		
		try {
			
			result = new LabelValue[1];
			result[0] = new LabelValue("Todos", "");
		
			if (oidSubTipoCliente.size()==0) {
				return result;
			}
			
			List tipoClasificacion = interfazSiCCDAO.getLista(
					"getTipoClasificacionMultipleByOidSubTipoCliente", criteria);
			
			if (tipoClasificacion != null && tipoClasificacion.size() > 0) {
				
				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[tipoClasificacion.size() + 1 ];
					result[0] = new LabelValue("Todos", "");
					
					for (int i = 0; i < tipoClasificacion.size(); i++) {
						BaseOID baseOID = (BaseOID) tipoClasificacion.get(i);
						LabelValue lv = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
						result[i + 1] = lv;
					}
					
				}else {

					result = new LabelValue[tipoClasificacion.size()];
					for (int i = 0; i < tipoClasificacion.size(); i++) {
						BaseOID baseOID = (BaseOID) tipoClasificacion.get(i);

						result[i] = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
					}
				
				}
	
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
			ignore.printStackTrace();
		}
		return result;
	
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getClasificacionMultipleByOidTipoClasificacion(java.util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getClasificacionMultipleByOidTipoClasificacion(
			ArrayList oidTipoClasificacion, String condicionTodos) {

		log.debug("Entering 'getClasificacionMultipleByOidTipoClasificacion'");
		
		LabelValue[] result = null;
		
		Map criteria = new HashMap();
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		
		try {
			result = new LabelValue[1];
			result[0] = new LabelValue("Todos", "");
			
			if (oidTipoClasificacion.size() == 0) {
				return result;
			}
			
			List clasificacion = interfazSiCCDAO.getLista(
					"getClasificacionMultipleByOidTipoClasificacion", criteria);
		
			if (clasificacion != null && clasificacion.size() > 0) {
				
				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[clasificacion.size() + 1 ];
					result[0] = new LabelValue("Todos", "");
					
					for (int i = 0; i < clasificacion.size(); i++) {
						BaseOID baseOID = (BaseOID) clasificacion.get(i);
						LabelValue lv = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
						result[i + 1] = lv;
					}
				}else {
					result = new LabelValue[clasificacion.size()];
					
					for (int i = 0; i < clasificacion.size(); i++) {
						BaseOID baseOID = (BaseOID) clasificacion.get(i);

						result[i] = new LabelValue(baseOID.getDescripcion(),
								baseOID.getOid().toString());
					}
				}
				
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
			ignore.printStackTrace();
		}
		return result;
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarExistenciaCuv(java.lang.String)
	 */
	public String getValidarExistenciaCuv(String codigoVenta, String codigoCampanha) {
		String resultado = "";
		log.debug("Entro al metodo AJAX - 'getValidarExistenciaCuv'");
		try {
			List lista = null;
			Map criteria = new HashMap();
			criteria.put("codigoCampanha", codigoCampanha);
			criteria.put("codigoVenta", codigoVenta);
			
			lista = mantenimientoPEDGestionStockDAO.getValidarCuvExistencia(criteria);
			
			if (lista != null && lista.size() > 0) {

				for (int i = 0; i < lista.size(); i++) {
					DetalleCuvExistencia detCuv= (DetalleCuvExistencia)lista.get(i);
					resultado = detCuv.getCodigoSap() + " " +detCuv.getDescripcion();
					break;
				}
				
			}else {
				resultado = "No se encuentra Codigo de Venta";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/* INI SA PER-SiCC-2012-0900 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getZonasACerrar(java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasACerrar(String codigoPeriodo,
			String fechaFacturacion, String codigoRegion) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("fechaFacturacion", fechaFacturacion);
		criteria.put("codigoRegion", codigoRegion);

		List zonas = procesoFACProcesarCierresDAO.getZonasACerrar(criteria);

		if (zonas != null && zonas.size() > 0) {
			result = new LabelValue[zonas.size()];

			for (int i = 0; i < zonas.size(); i++) {
				Map base = (Map) zonas.get(i);

				result[i] = new LabelValue(base.get("label").toString(),
						base.get("value").toString());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}

		return result;
	}

	/* FIN SA PER-SiCC-2012-0900 */

	public String getValidacionDocumentoIdentidad(String codigoPais,
			String tipoDocumento, String numeroDocumento) {
		
		if(log.isDebugEnabled()){
			log.debug("codigoPais: " + codigoPais);
			log.debug("tipoDocumento: " + tipoDocumento);
			log.debug("numeroDocumento: " + numeroDocumento);
		}
			
		Map criteria = new HashMap();

		criteria.put("codigoPais",codigoPais);
		criteria.put("oidPais",
				reporteDAO.getOidString("getOidPaisByCodigoPais", criteria));
		 criteria.put("tipoDocumentoIdentidad", tipoDocumento);
		criteria.put("numeroDocumentoIdentidad", numeroDocumento);
		
		String mensaje = mantenimientoMAEClienteDAO
				.getValidacionDocumentoIdentidad(numeroDocumento);
		
		if(StringUtils.isNotBlank(mensaje)){
			mensaje = "Número de Documento se encuentra Registrado";
		}else{
			
			String documento = clienteService
					.validarDocumentoIdentidad(criteria);
			
			if(log.isDebugEnabled()){
				log.debug("mensaje documento: " + documento);
			}
			

			if (documento != null && documento.trim().length()>0) {
				
				if(log.isDebugEnabled()){
					log.debug("documento: " + documento+ " tipo documento: " + tipoDocumento + "codigo pais: "+codigoPais );
				}
				
				if(log.isDebugEnabled()){
					log.debug("tipo documento constante: " + Constants.MAE_TIPO_DOCUMENTO_CI);
					log.debug("codigo pais constante: "+Constants.PAIS_BOE );
				}
				
				if(StringUtils.equals(tipoDocumento, Constants.MAE_TIPO_DOCUMENTO_CI) && StringUtils.equals(codigoPais, Constants.PAIS_BOE)){
					mensaje=documento;
					
					if(log.isDebugEnabled()){
						log.debug("mensaje: " + mensaje);
					}
				}else{
				if(documento.equals("Modulo10")) {
					mensaje ="Número Documento No válido";
				} else if(documento.equals("Modulo11V")) {
					mensaje ="Número Documento No válido";			
				} else {
					mensaje ="Número Documento Valido";
						if(log.isDebugEnabled()){
							log.debug("mensaje 1: " + mensaje);
						}
				}	
				}

			}else{
				mensaje ="Número Documento válido";
				if(log.isDebugEnabled()){
					log.debug("mensaje 2: " + mensaje);
				}
			}
		}
		return mensaje;
	}

	/* INI PER-SiCC-2012-1021 */
	
	public String getDatosActividad(String oidActividad) {
		String resultado = "";
		
		try {
			List lista = mantenimientoMAVConfiguracionDAO
					.getDatosActividad(oidActividad);
			Map datos = (Map)lista.get(0);
			
			resultado = datos.get("oidActividad").toString() + "__"
					+ datos.get("oidTipoCliente").toString() + "__"
					+ datos.get("codigoTipoCliente").toString() + "__"
					+ datos.get("descripcionTipoCliente").toString() + "__"
					+ datos.get("oidFormaPago").toString() + "__"
					+ datos.get("descripcionFormaPago").toString() + "__"
					+ datos.get("oidFormaCobro").toString() + "__"
					+ datos.get("descripcionFormaCobro").toString() + "__"
					+ datos.get("oidActividadTipoDespacho").toString() + "__"
					+ datos.get("oidTipoDespacho").toString() + "__"
					+ datos.get("descripcionTipoDespacho").toString() + "__";
			
					if(datos.get("oidEnvioSolicitud") != null) {
				resultado = resultado
						+ datos.get("oidEnvioSolicitud").toString() + "__"
						+ datos.get("descripcionEnvioSolicitud").toString();
					} else {
						resultado = resultado + "" + "__" + "";
					}
			
			/* INI SA PER-SiCC-2013-0432 */
			if (datos.get("descripcionAlmacen") != null) {
				resultado = resultado + "__" 
						+ datos.get("descripcionAlmacen").toString();
			} else {
				resultado = resultado + "__" + "";
			}
			/* FIN SA PER-SiCC-2013-0432 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	/**
	 * Obtener los tipos de ofertas relacionadas a una actividad MAV
	 * 
	 * @param oidActividad
	 * @return
	 */
	public LabelValue[] getTiposOfertaByActividad(String oidActividad) {
		LabelValue[] result = null;

		List tipos = mantenimientoMAVConfiguracionDAO.getTiposOferta(oidActividad);

		if (tipos != null && tipos.size() > 0) {
			result = new LabelValue[tipos.size()+1];
			result[0] = new LabelValue("", "");
			
			for (int i = 0; i < tipos.size(); i++) {
				Base base = (Base) tipos.get(i);

				result[i+1] = new LabelValue(base.getDescripcion(),
						base.getCodigo());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}

		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidarConsRestMAV(java.lang
	 * .String )
	 */
	public String getValidarConsRestMAV(String codigoConsideracion,
			String tipoConsideracion, List listConsideracion, List listRestriccion) {
		log.debug("iniico getValidarConsRest " + codigoConsideracion + " tipo "
				+ tipoConsideracion);
		

		Map map = new HashMap();
		map.put("codigo", codigoConsideracion);

		List listC = null;
		List listR = null;
		String abrevConRes = "";
		if (tipoConsideracion.equals(Constants.MAV_TIPO_CONSIDERACION)) {
			listC = mantenimientoMAVConfiguracionDAO.getConsideracion(map);
			Map bean = (Map) listC.get(0);
			abrevConRes = (String) bean.get("abrevConRes");
		} else {
			listR = mantenimientoMAVConfiguracionDAO.getRestriccion(map);
			Map bean = (Map) listR.get(0);
			abrevConRes = (String) bean.get("abrevConRes");
		}

		log.debug("abrevConRes " + abrevConRes);

		//List listConsideracion = (List) session.getAttribute("mavConfiguracionConsideracionList");
		Iterator it = listConsideracion.iterator();
		while (it.hasNext()) {
			Map beanC = (Map) it.next();
			String abrev = (String) beanC.get("abrev");
			String indicadorAccion = (String)beanC.get("indicadorAccion");
			
			if (StringUtils.equals(abrev, abrevConRes) &&
					(indicadorAccion.equals(Constants.NUMERO_CERO) || indicadorAccion.equals(Constants.NUMERO_UNO))) {
				return codigoConsideracion;
			}
		}

		//List listRestriccion = (List) session.getAttribute("mavConfiguracionRestriccionList");
		it = listRestriccion.iterator();
		while (it.hasNext()) {
			Map beanR = (Map) it.next();
			String abrev = (String) beanR.get("abrev");
			String indicadorAccion = (String) beanR.get("indicadorAccion");
			
			if (StringUtils.equals(abrev, abrevConRes) &&
					(indicadorAccion.equals(Constants.NUMERO_CERO) || indicadorAccion.equals(Constants.NUMERO_UNO))) {
				return codigoConsideracion;
			}
		}

		return "";
	}

	/* FIN SA PER-SiCC-2012-1021 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getComisionesByTipo(java.lang.String)
	 */
	public LabelValue[] getComisionesByTipo(String codigoTipoComision) {
		LabelValue[] result = new LabelValue[1];
		result[0] = new LabelValue("", "");
		if (StringUtils.isNotBlank(codigoTipoComision)) {
			Map criteria = new HashMap();
			criteria.put("codigoTipoComision", codigoTipoComision);
			List comisiones = procesoCOMCalculoCalificacionTramoDAO.getComisionByTipo(criteria);

			if (comisiones != null && comisiones.size() > 0) {
				result = new LabelValue[comisiones.size()+1];
				
				result[0] = new LabelValue("", "");
				for (int i = 0; i < comisiones.size(); i++) {
					Base base = (Base) comisiones.get(i);
					result[i+1] = new LabelValue(base.getDescripcion(), base.getCodigo());
				}
			} else {
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getUltimoCodigoZona(java.lang.String, java.lang.String)
	 */
	public String getUltimoCodigoZona(String codigoPais, String codigoRegion) {
		int codigoZona = 0;
		String codigo;
		Map criteria = new HashMap ();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoRegion", codigoRegion);
		codigoZona = mantenimientoZONUnidadAdministrativaDAO.getUltimoCodigoZona(criteria);
		codigoZona = codigoZona+1;
		
		if (codigoZona == 0 ) {
			codigo = StringUtils.leftPad(codigoZona+"", 4);
		}else {
			codigo = codigoZona +"";
		}
		return codigo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getMensajeValidaPedido(java.lang.String)
	 */
	public String getMensajeValidaPedido(String numeroPedido) {
		
		Map criteria = new HashMap();
		criteria.put("numeroPedido", numeroPedido);
		String mensaje = null;
		String tipoMensaje = null;
		mantenimientoRECDigitacionCDRDAO.getMensajeValidaPedido(criteria);
		mensaje = MapUtils.getString(criteria, "mensaje");
		
		
		 log.debug("mensaje >>>>>>>>> "+criteria.get("mensaje"));
		
		 
		return mensaje;
	}

	/* INI DA PER-SiCC-2012-0970 */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTiposOperacionesByOperacion(java.lang.String[], java.lang.String)
	 */
	public LabelValue[] getTiposOperacionesByOperacion(String[] operaciones) {	
		
		LabelValue[] result = null;
				
		if ((operaciones == null) || (operaciones!=null && operaciones.length == 0)) {
			return result;
		
		}else{
			Map criteria = new HashMap();
			criteria.put("operaciones", operaciones);
			
			List tipoOperacionesList = this.mantenimientoOperacionCDRUsuarioDAO.getTipoOperacionesByOperacion(criteria);
						
			if(tipoOperacionesList != null && tipoOperacionesList.size()>0 ){
				result = new LabelValue[tipoOperacionesList.size()];
				for(int i =0; i<tipoOperacionesList.size(); i++){
					Base tipoOperacion = (Base) tipoOperacionesList.get(i);
					LabelValue lv = new LabelValue(
							tipoOperacion.getDescripcion(),
							tipoOperacion.getCodigo());
					result[i] = lv;
				}
			}
		}
		
		return result;
	}	
	/* FIN DA PER-SiCC-2012-0970 */

	public LabelValue[] getProvinciasPorDepartamento(String codigoDepartamento, String tipoCombo) {
		log.debug("Entering 'getProvinciasPorDepartamento'");
		
		LabelValue[] result = null;
		List provincias = new ArrayList();
		try {
			Map criteria = new HashMap();
			
			if (tipoCombo.equals("Depa")) {
				criteria.put("indicadorDepa", tipoCombo);
				criteria.put("orde1", codigoDepartamento);
				provincias = mantenimientoZONUnidadGeograficaDAO.getUnidadesGeograficasList(criteria);
			}
						
			log.debug("Getting provincias.size=" + provincias.size());
			
			if (provincias != null && provincias.size() > 0) {
				result = new LabelValue[provincias.size()];
				
				for (int i = 0; i < provincias.size(); i++) {					
					Base provincia = new Base();
					UnidadGeografica detalle = new UnidadGeografica();
					detalle = (UnidadGeografica) provincias.get(i);
					provincia.setCodigo(detalle.getNivel2());
					provincia.setDescripcion(detalle.getDescripcion());
					// Construimos la descripcion
					LabelValue lv = new LabelValue(provincia.getDescripcion(), provincia.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
	}

	public LabelValue[] getDistritosPorProvincia(String codigoProvincia, String codigoDepartamento, String tipoCombo) {
		log.debug("Entering 'getDistritosPorProvincia'");
		
		LabelValue[] result = null;
		List distritos = new ArrayList();
		try {
			Map criteria = new HashMap();
			
			if (tipoCombo.equals("Prov")) {
				criteria.put("indicadorProv", tipoCombo);
				criteria.put("orde1", codigoDepartamento);
				criteria.put("orde2", codigoProvincia);
				distritos = mantenimientoZONUnidadGeograficaDAO.getUnidadesGeograficasList(criteria);
			}
						
			log.debug("Getting distritos.size=" + distritos.size());
			
			if (distritos != null && distritos.size() > 0) {
				result = new LabelValue[distritos.size()];
				
				for (int i = 0; i < distritos.size(); i++) {
					Base distrito = new Base();
					UnidadGeografica detalle = new UnidadGeografica();
					detalle = (UnidadGeografica) distritos.get(i);
					distrito.setCodigo(detalle.getNivel3());
					distrito.setDescripcion(detalle.getDescripcion());
					// Construimos la descripcion
					LabelValue lv = new LabelValue(distrito.getDescripcion(), distrito.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
	}

	public LabelValue[] getCentrosPobladosPorDistrito(String codigoDistrito, String codigoProvincia, String codigoDepartamento, String tipoCombo) {
		log.debug("Entering 'getCentrosPobladosPorDistrito'");
		
		LabelValue[] result = null;
		List centroPoblados = new ArrayList();
		try {
			Map criteria = new HashMap();
			
			if (tipoCombo.equals("Dist")) {
				criteria.put("indicadorDist", tipoCombo);
				criteria.put("orde1", codigoDepartamento);
				criteria.put("orde2", codigoProvincia);
				criteria.put("orde3", codigoDistrito);
				centroPoblados = mantenimientoZONUnidadGeograficaDAO.getUnidadesGeograficasList(criteria);
			}
						
			log.debug("Getting centroPoblados.size=" + centroPoblados.size());
			
			if (centroPoblados != null && centroPoblados.size() > 0) {
				result = new LabelValue[centroPoblados.size()];
				
				for (int i = 0; i < centroPoblados.size(); i++) {
					Base centroPoblado = new Base();
					UnidadGeografica detalle = new UnidadGeografica();
					detalle = (UnidadGeografica) centroPoblados.get(i);
					centroPoblado.setCodigo(detalle.getNivel4());
					centroPoblado.setDescripcion(detalle.getDescripcion());
					// Construimos la descripcion
					LabelValue lv = new LabelValue(centroPoblado.getDescripcion(), centroPoblado.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		log.debug("Returning result.length=" + result.length);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#obtenerDatosConsultora(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String obtenerDatosConsultora(String codigoPais, String codigoConexionExterna, String codigoConsultora) {
		String resultado = "";
		if (StringUtils.isNotBlank(codigoConsultora) ) {
			
			Map criteria = new HashMap();
			criteria.put("codigoConsultora", codigoConsultora);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoConexionExterna", codigoConexionExterna);
			
			resultado = mantenimientoZONDirectorioDAO.obtenerDatosConsultora(criteria);
			
			if (StringUtils.isBlank(resultado)) {
				resultado = "1";
			}
		}
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#obtenerDatosConsultoraAsignarCargo(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String obtenerDatosConsultoraAsignarCargo(String codigoPais,
			String codigoConexionExterna, String codigoConsultora) {
		String resultado = "";
		if (StringUtils.isNotBlank(codigoConsultora)) {

			Map criteria = new HashMap();
			criteria.put("codigoConsultora", codigoConsultora);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoConexionExterna", codigoConexionExterna);
			
			resultado = mantenimientoZONDirectorioDAO.obtenerDatosConsultoraAsignarCargo(criteria);

			if (StringUtils.isBlank(resultado)) {
				resultado = "1";
			}
		}
		return resultado;
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getVerificarCargoTitular(java.lang.String)
	 */
	public String getVerificarCargoTitular(String codigoCargo) {
		String resultado = "0";
		if (StringUtils.isNotBlank(codigoCargo)) {
			
			Map criteria = new HashMap();
			criteria.put("codigoCargo", codigoCargo);
			//Envia '1' indica que No titular  y cantidad de unidades administrativas a cargo es mayor  1
			resultado = mantenimientoZONDirectorioDAO.getVerificarCargoTitular(criteria);
			
			String[] valores = StringUtils.split(resultado, "|");
			
			String cantidadUA = valores[0];
			String titular = valores[1];
			String tipoUA = valores[2];
			
			if(StringUtils.equals(cantidadUA, "V") && StringUtils.equals(titular, "0"))
				resultado = "1|" + tipoUA;
			else				
				resultado = "0|" + tipoUA;
		}else {
			resultado = "0|0";
		}
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#obtenerDatosConsultoraReemplazo(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String[] obtenerDatosConsultoraReemplazo(String codigoPais, String codigoConexionExterna, String codigoConsultora) {
		String[] resultado = new String[1];
		resultado[0] = "1";
		
		String listaUas = "";
		String cargoFinal = "";
		String correlativoCabecera = "";
		String consultoraReemplazo = "";
		
		if (StringUtils.isNotBlank(codigoConsultora)) {
			
			//Validar si el cliente esta activo.(Para ORA y FOX)
			Map criteria = new HashMap();
			criteria.put("codigoConsultora", codigoConsultora);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoConexionExterna", codigoConexionExterna);

			consultoraReemplazo = mantenimientoZONDirectorioDAO.getDatosConsultoraReemplazo(criteria);
			
			if (StringUtils.isNotBlank(consultoraReemplazo)) {
					//Datos de Unidades Administrativas
				//criteria.put("considerarTodosEstadosCargos", Constants.ESTADO_ACTIVO); 
				//primero solo buscamos a las genretes
				//criteria.put("considerarSoloGerentes", Constants.ESTADO_ACTIVO);
				List datosUnidadesAdministrativasList = mantenimientoZONDirectorioDAO.getDatosUnidaddesAdministrativasConsultora(criteria);
					
				/*if(datosUnidadesAdministrativasList == null || datosUnidadesAdministrativasList.size() == 0)
				{
					//Buscamos Alternas
					criteria.put("considerarSoloGerentes", null);
					criteria.put("considerarSoloAlternas", Constants.ESTADO_ACTIVO);
					datosUnidadesAdministrativasList = mantenimientoZONDirectorioDAO.getDatosUnidaddesAdministrativasConsultora(criteria);
					
					if(datosUnidadesAdministrativasList == null || datosUnidadesAdministrativasList.size() == 0)
					{
						//Buscamos capacitadoras
						criteria.put("considerarSoloGerentes", null);
						criteria.put("considerarSoloAlternas", null);
						criteria.put("considerarSoloCapacitadoras", Constants.ESTADO_ACTIVO);
						datosUnidadesAdministrativasList = mantenimientoZONDirectorioDAO.getDatosUnidaddesAdministrativasConsultora(criteria);
					}					
				}*/

				if (datosUnidadesAdministrativasList != null && datosUnidadesAdministrativasList.size() > 0) {
					resultado = null;
					resultado = new String[datosUnidadesAdministrativasList.size()];
				
				for (int i = 0; i < datosUnidadesAdministrativasList.size(); i++) {
					listaUas = StringUtils.EMPTY;	
						
					Map mapUA = (Map) datosUnidadesAdministrativasList.get(i);
						
						String desZona = (String)mapUA.get("desZona");
						String desRegion = (String)mapUA.get("desRegion");
						String codigoCargo = (String) mapUA.get("codigoCargo");
						String cargo = (String)mapUA.get("descripcionCargo");
					correlativoCabecera = ((BigDecimal) mapUA.get("correlativoCabecera")).toString();
					
						cargoFinal = codigoCargo.concat(" - ").concat(cargo);
						
						if(StringUtils.isBlank(desZona))
							listaUas = listaUas + String.format("%s<br/>", desRegion);
						else
							listaUas = listaUas + String.format("%s - %s<br/>", desRegion, desZona);
				
			/*
			 * resultado.split("_"); resultado [0] = NOMBRES COMPLETOS DEL
			 * CLIENTE resultado [1] = NUMERO DE DOCUMENTO DE IDENTIDAD
			 * resultado [2] = LISTA DE UNIDADES ADMINISTRATIVAS A SU CARGO
				 * resultado [3] = correlativoCabecera
			*/
					resultado[i] = consultoraReemplazo + "_" + cargoFinal + "_" + listaUas + "_" + correlativoCabecera;
				}
			}
			}
		   
		} 
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getDataCargo(java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getDataCargo(String codigoPais, String codigoConexionExterna, String codigoConsultora) {
		log.debug("Entering 'getDataCargo'");
       
		LabelValue[] result = null;
		try {
			
			Map criteria = new HashMap();
			criteria.put("codigoConsultora", codigoConsultora);
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoConexionExterna", codigoConexionExterna);

			List datosUnidadesAdministrativasList = mantenimientoZONDirectorioDAO.getDatosUnidaddesAdministrativasConsultora(criteria);
			
			if (datosUnidadesAdministrativasList != null && datosUnidadesAdministrativasList.size() > 0) {
				result = new LabelValue[datosUnidadesAdministrativasList.size()];
				for (int i = 0; i < datosUnidadesAdministrativasList.size(); i++) {
					
					Map mapUA = (Map) datosUnidadesAdministrativasList.get(i);

					String desZona = (String) mapUA.get("desZona");
					String desRegion = (String) mapUA.get("desRegion");
					String codigoCargo = (String) mapUA.get("codigoCargo");
					String cargo = (String) mapUA.get("descripcionCargo");
					String correlativoCabecera = ((BigDecimal) mapUA.get("correlativoCabecera")).toString();
					String listaUas ="";
					String cargoFinal = codigoCargo.concat(" - ").concat(cargo);
					
					if (StringUtils.isBlank(desZona))
						listaUas = desRegion;
			else
						listaUas = String.format("%s - %s", desRegion, desZona);
					
					LabelValue lv = new LabelValue(cargoFinal.concat(" - ").concat(listaUas),correlativoCabecera);
					result[i] = lv;
			}
		}else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}

		  
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getValidacionConsultoraAptoLicencia
	 * (java.lang.String)
	 */
	public String getValidacionConsultoraAptoLicencia(String consultora) {
		String resultado = "";
		
		if (StringUtils.isNotBlank(consultora)) {
			
			Map criteria = new HashMap();
			
			criteria.put("codigoConsultora", consultora);
			resultado = mantenimientoZONDirectorioDAO.getValidacionConsultoraAptoLicencia(criteria);
		}
		return resultado;
	}

	
	/* INI SA PER-SiCC-2012-1065 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getConsideraciones(java.lang.String)
	 */
	public LabelValue[] getConsideraciones(String indicadorDirigido, String indicadorUnidad) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("indicadorDirigido", indicadorDirigido);
		criteria.put("indicadorUnidad", indicadorUnidad);

		List tipos = mantenimientoMAVConfiguracionDAO.getConsideracion(criteria);

		if (tipos != null && tipos.size() > 0) {
			result = new LabelValue[tipos.size()+1];
			result[0] = new LabelValue("", "");
			
			for (int i = 0; i < tipos.size(); i++) {
				Map base = (Map) tipos.get(i);

				result[i+1] = new LabelValue(base.get("descripcionConRes").toString(),
						base.get("codigoConRes").toString());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getRestricciones(java.lang.String)
	 */
	public LabelValue[] getRestricciones(String indicadorDirigido, String indicadorUnidad) {
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("indicadorDirigido", indicadorDirigido);
		criteria.put("indicadorUnidad", indicadorUnidad);

		List tipos = mantenimientoMAVConfiguracionDAO.getRestriccion(criteria);

		if (tipos != null && tipos.size() > 0) {
			result = new LabelValue[tipos.size()+1];
			result[0] = new LabelValue("", "");
			
			for (int i = 0; i < tipos.size(); i++) {
				Map base = (Map) tipos.get(i);

				result[i+1] = new LabelValue(base.get("descripcionConRes").toString(),
						base.get("codigoConRes").toString());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}

		return result;
	}
	/* FIN SA PER-SiCC-2012-1065 */

	/* INI DA PER-SiCC-2012-1148 */
	public LabelValue[] getValidacionesExcepcionByDocumento(String codigoPais,
			String tipoDocumento) {
		
		LabelValue[] result = null;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);

		List ValidacionesList = procesoSTOLevantamientoErroresValidacionDAO
				.getValidacionesExcepcionByDocumento(criteria);				

		try {
			if (ValidacionesList != null && ValidacionesList.size() > 0) {
				result = new LabelValue[ValidacionesList.size() + 1];
				result[0] = new LabelValue("Todos", "");
				for (int i = 0; i < ValidacionesList.size(); i++) {
					Base validacion = (Base) ValidacionesList.get(i);
					LabelValue lv = new LabelValue(validacion.getDescripcion(),
							validacion.getCodigo());
					result[i + 1] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
		
	}
	/* FIN DA PER-SiCC-2012-1148 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getBasesComision(java.lang.String)
	 */
	public LabelValue[] getBasesComision(String codigoTipoComisionista) {
		List lista = new ArrayList();
		LabelValue[] result = null;
		
		if(StringUtils.isNotBlank(codigoTipoComisionista)){
			Map criteria = new HashMap();
			criteria.put("codigoTipoComisionista", codigoTipoComisionista);
			
			try {
				lista = mantenimientoCOMComisionDAO.getBasesComision(criteria);
				
				if (lista != null && lista.size() > 0) {
					result = new LabelValue[lista.size()];
					
					for (int i = 0; i < lista.size(); i++) {
						Base validacion = (Base) lista.get(i);
						LabelValue lv = new LabelValue(validacion.getDescripcion(), validacion.getCodigo());
						result[i] = lv;
					}
				}else{
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		else
		{
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getCierreBrByNumeroBR(java.lang.String)
	 */
	public String getCierreBrByNumeroBR(String numeroBR) {
		String resultado = "";
		log.debug("Entering my method AJAX-Impl getCierreBrByNumeroBR");
		if (!StringUtils.isBlank(numeroBR.trim())) {
			Map criteria = new HashMap();
			criteria.put("numeroBR", Long.parseLong(numeroBR));
			criteria.put("mensaje", "");
			criteria.put("nroRecojo", "");
			criteria.put("codigoCliente", "");
			criteria.put("nombreCliente", "");
			
			procesoRECCierreBRDAO.executeValidaRelacionBoletaRecojo(criteria);
			
			if(StringUtils.isNotEmpty((String)criteria.get("codigoCliente"))){
				String mensaje = (String)criteria.get("mensaje");
				if (StringUtils.isNotEmpty(mensaje)) {
					resultado = mensaje;
					return resultado;
				}else {
					String nroRecojo = (String)criteria.get("nroRecojo");
					String codigoCliente = (String)criteria.get("codigoCliente");
					String nombreCliente = (String)criteria.get("nombreCliente");
					resultado = nroRecojo + "_" + codigoCliente + "_" + nombreCliente+"_"+"0";
				}
			}else {
				resultado = "No se encontraron resultados con el numero de BR "+ numeroBR.trim() ;
			}
		}else {
			resultado = "Se necesita el Nmero de BR para continuar.";
		}
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#insertProcesoBoletaTemporal(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String insertProcesoBoletaTemporal(String numeroBoletaRecojo,
			String numeroRecojo, String codigoCliente, String flagProceso, List listaBoletas) {
		
		Map criteria = new HashMap();
		criteria.put("numeroBoletaRecojo", numeroBoletaRecojo);
		criteria.put("numeroRecojo", numeroRecojo);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("flagProceso", flagProceso);
		
		/*Insertamos los valores en una lista de la session del usuario*/
		
		
		
		//List listaBoletas = (List)session.getAttribute(Constants.REC_LISTA_RELACION_BOLETAS_RECOJO_CORRECTAS_TEMPORAL_LIST);
		
		if(listaBoletas == null)
			listaBoletas = new ArrayList();
		
		listaBoletas.add(criteria);
		
		//session.setAttribute(Constants.REC_LISTA_RELACION_BOLETAS_RECOJO_CORRECTAS_TEMPORAL_LIST, listaBoletas);

		log.debug("Se inserto el registro de Boleta Recojo a la Tabla Temporal");
		
		return "Registro Satisfactorio";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#deleteBoletaTemporal(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	public String deleteBoletaTemporal(String numeroBoletaRecojo,
			String numeroRecojo, String codigoCliente, List listaBoletas) {
		
		Map criteria = new HashMap();
		criteria.put("numeroBoletaRecojo", Integer.parseInt(numeroBoletaRecojo));
		criteria.put("numeroRecojo", numeroRecojo);
		criteria.put("codigoCliente", codigoCliente);
		
		procesoRECCierreBRDAO.removeBoletaTemporal(criteria);
		
		//Eliminamos de la lista en session
		//List listaBoletas = (List) session.getAttribute(Constants.REC_LISTA_RELACION_BOLETAS_RECOJO_CORRECTAS_TEMPORAL_LIST);
		
		List listaBoletasActualizada = new ArrayList();
		if(listaBoletas != null && listaBoletas.size() > 0)
		{
			for(int i=0; i<listaBoletas.size(); i++)
			{
				Map boleta = (Map)listaBoletas.get(i);				
				if(!(StringUtils.equals(numeroBoletaRecojo, MapUtils.getString(boleta, "numeroBoletaRecojo")) &&
						StringUtils.equals(numeroRecojo, MapUtils.getString(boleta, "numeroRecojo")) &&
						StringUtils.equals(codigoCliente, MapUtils.getString(boleta, "codigoCliente"))))
						{
							listaBoletasActualizada.add(boleta);
						}				
			}
		}
		
		//session.setAttribute(Constants.REC_LISTA_RELACION_BOLETAS_RECOJO_CORRECTAS_TEMPORAL_LIST, listaBoletasActualizada);
		//
		listaBoletas = new ArrayList();
		listaBoletas.add(listaBoletasActualizada);
		
		
		log.debug("Se elimino el registro de Boleta Recojo a la Tabla Temporal");
		
		return "Eliminacion Satisfactorio";
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validaComisionObjetivoVenta(java.lang.String)
	 */
	public String validaComisionObjetivoVenta(String codigoPeriodo, String codigoComision) {
		
			Map map = new HashMap();
			map.put("codigoPeriodo", codigoPeriodo);
			map.put("codigoComision", codigoComision);
			List lstComision = mantenimientoCOMComisionDAO.getComisionCalcuRegionList(map);
			String mensaje = null;
			if(CollectionUtils.isNotEmpty(lstComision)){
				mensaje = "Comisin ya se encuentra calculada, Desea continuar o no con el proceso?";
			}
			log.debug(">>>>>> "+mensaje);
			
			return mensaje;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaSegundoPedido(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validaProductosKitSegundoPedido(String codigoPais,
			String codigoPrograma, String codigoPeriodo, String codigoVenta) {
		log.debug("INIC method -AJAX Impl - validaSegundoPedido");
		String resultado = "0";
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", codigoPrograma);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoVenta", codigoVenta);
		
		int cantidad = mantenimientoDAO.validaProductosKitSegundoPedido(criteria);
		if (cantidad != 0) {
			resultado = "1";
		} else {			
			List lstPrgDscto = mantenimientoDAO.getProgramasDsctoByCriteria(criteria);
			String lsProgOblig = ((ProgramaCupon) lstPrgDscto.get(0)).getIndicadorProgramaObligatorio();
			if (lsProgOblig.equalsIgnoreCase("1")) {
				criteria.put("difCodigoVenta", "");
				cantidad = mantenimientoDAO.validaProductosKitSegundoPedido(criteria);
				if (cantidad == 0) {
					resultado = "2";
				}
			}
		}

		
		log.debug("FIN method -AJAX Impl - validaSegundoPedido con resultado = "+resultado);
		return resultado;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validaComisionCalculada(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public String validaComisionCalculada(String codigoPais,
			String codigoPeriodo, String codigoComision) {
		
		String comisionCalculada = Constants.NO;

		Map map = new HashMap();
		map.put("codigoPeriodo", codigoPeriodo);
		map.put("codigoComision", codigoComision);

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema(Constants.COM_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.COM_CODIGO_PARAMETRO_TIPO_PROCESO_COMISION);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		
		List lstParametros = genericoDAO.getParametrosPais(parametroPais);
		
		ParametroPais parametro = null;
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
		}
		
		//Verificamos los parametros para realizar las validaciones
		DatosComisiones comision = mantenimientoCOMComisionDAO.getDatosComisionRecuperacion(map);
		
		if(comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_01) && 
				StringUtils.equals(parametro.getValorParametro(), "2")){
			List lstComision = mantenimientoCOMComisionDAO.getComisionVentaNetaEfectivaList(map);
			if(CollectionUtils.isNotEmpty(lstComision)){
				comisionCalculada = Constants.SI;
			}
		}		
		else if((comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_01) || 
				 comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_02) || 
				 comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_04))  &&
				 StringUtils.equals(parametro.getValorParametro(), "1")){
			//Comision pro recuperacion
			Integer result = getExisteComisionRecuperacion(codigoPeriodo, codigoComision, comision.getCodigoTipoComisionista());
			if(result.intValue() > 0){
				comisionCalculada = Constants.SI;
			}
		}		
		else if(comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_03) ){
			List lstComision = mantenimientoCOMComisionDAO.getComisionCalcuRegionList(map);
			if(CollectionUtils.isNotEmpty(lstComision)){
				comisionCalculada = Constants.SI;
			}			
		}

		if(log.isDebugEnabled())
			log.debug("comisionCalculada: " + comisionCalculada);
		
		return comisionCalculada;
	}
	
	public LabelValue[] getZonasNoAsignadas( final String[] codigoRegiones) {
		
		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegiones);
		try {
			result = new LabelValue[1];

			List zonasList = mantenimientoCRAGrupoZonaDAO.getGrupoZonaNoAsignadas(criteria);
			if (zonasList != null && zonasList.size() > 0) {
					
				result = new LabelValue[zonasList.size()];
				for (int i = 0; i < zonasList.size(); i++) {
					Base zona = (Base) zonasList.get(i);
					LabelValue lv = new LabelValue(zona.getDescripcion(),zona.getCodigo());
					result[i] = lv;
				}
			}
				
		} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
		}
		return result;
	}
	
	public LabelValue[] getZonasGrupo(  final String[] oidGruposZona) {
		
		LabelValue[] result = null;
		
		Map criteria = new HashMap();
		criteria.put("grupoZonaList", oidGruposZona);
		
		try {
			result = new LabelValue[1];

			List zonasList = mantenimientoCRAGrupoZonaDAO.getZonaAsignadasGrupo(criteria);
			if (zonasList != null && zonasList.size() > 0) {
					
				result = new LabelValue[zonasList.size()];
				for (int i = 0; i < zonasList.size(); i++) {
					Base zona = (Base) zonasList.get(i);
					LabelValue lv = new LabelValue(zona.getDescripcion(),zona.getCodigo());
					result[i] = lv;
				}
			}
			else{
				result[0] = new LabelValue("Todos","T");
			}
				
		} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
		}
		return result;
	}


	public LabelValue[] getZonasGrupoRegenerar(String oidGruposZona, String zonaReferencia) {

		LabelValue[] result = null;

		Map criteria = new HashMap();
		criteria.put("oidGruposZona", oidGruposZona);
		criteria.put("oidZonaReferencia", zonaReferencia);

		try {
			result = new LabelValue[1];

			List zonasList = mantenimientoCRAGrupoZonaDAO
					.getZonasGrupoRegenerar(criteria);
			if (zonasList != null && zonasList.size() > 0) {

				result = new LabelValue[zonasList.size()];
				for (int i = 0; i < zonasList.size(); i++) {
					Base zona = (Base) zonasList.get(i);
					LabelValue lv = new LabelValue(zona.getDescripcion(),
							zona.getCodigo());
					result[i] = lv;
				}
			} else {
				result[0] = new LabelValue("Todos", "T");
			}

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}	

	public String deleteTablaTemporal(){
		log.debug("AJAX Method - deleteTablaTemporal");
		procesoRECCierreBRDAO.deleteTablaTemporal();
		return "Se elimin la tabla temporal";
	}
		
	public String validarDesvinculo(String pais, String codigoRegion,
			String codigoZona, String codigoSeccion, String tipo, String codigoCliente,
			String indicadorLider) {
		Map criteria = new HashMap();

		if(StringUtils.isNotBlank(codigoZona)){
			//Adquirir el codigo de Region
			criteria.put("codigoZona", codigoZona);
			List listaRegion = interfazSiCCDAO.getLista("getRegionByZona",
					criteria);
			if (listaRegion != null && listaRegion.size() > 0) {
				Base base = (Base) listaRegion.get(0);
				codigoRegion = base.getCodigo() ;
			}
		}
		
        criteria.put("tipo", tipo);
		criteria.put("codigoPais", pais);
		Map obtenerFechaPeriodoProceso = (HashMap) interfazSiCCDAO
				.getPeriodoFechaProcesoActual(criteria).get(0);
		String codigoPeriodoProceso = (String) obtenerFechaPeriodoProceso
				.get("cod_peri");
		String fechaProceso = (String) obtenerFechaPeriodoProceso
				.get("fec_proc");
        
        criteria.put("codigoPeriodoActual", codigoPeriodoProceso);
        criteria.put("fechaProceso", fechaProceso);
        criteria.put("codigoCliente", codigoCliente);
        criteria.put("codigoRegion",codigoRegion);
		criteria.put("codigoSeccion", codigoSeccion);
        criteria.put("subger","01");
        
        int indicadorAsignarLider = Integer.valueOf(indicadorLider).intValue();
		String codigoPeriodoProcesoSgte = consultaPEJProgramaEjecutivasDAO
				.getObtienePeriodo(pais, codigoPeriodoProceso, 1);
        
		String periodoAsignarLider = (indicadorAsignarLider == 0) ? codigoPeriodoProceso
				: codigoPeriodoProcesoSgte;
        
        criteria.put("periodoAsignarLiderSgte", periodoAsignarLider);
        
		String mensaje = mantenimientoLETLideresDAO
				.validarDesvinculacion(criteria);
        
		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getClasificacionComision(java.lang
	 * .String)
	 */
	public LabelValue[] getClasificacionComision(String oidTipoClasificacion) {
		LabelValue[] result = null;

		try {
			result = new LabelValue[1];
			Map criteria = new HashMap();
			criteria.put("oidTipoClasificacion", Integer.parseInt(oidTipoClasificacion));
			
			List clasificacinoList = mantenimientoCOMComisionDAO.getClasificacionComisionList(criteria);
			if (clasificacinoList != null && clasificacinoList.size() > 0) {

				result = new LabelValue[clasificacinoList.size()];
				for (int i = 0; i < clasificacinoList.size(); i++) {
					Base clasificacion = (Base) clasificacinoList.get(i);
					LabelValue lv = new LabelValue(clasificacion.getDescripcion(), clasificacion.getCodigo());
					result[i] = lv;
				}
			}

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}
	
	/*public Map ofertaDevolucion(String oidsoliPosi, String cantidad) {
		
		if (log.isDebugEnabled()) {
			log.debug("oidsoliPosi = " + oidsoliPosi);
			log.debug("cantidad = " + cantidad);
		}
		
		Map resultado = new HashMap();
		
		
		Map criteria = new HashMap();
		criteria.put("oidSoliPosi", Long.parseLong(oidsoliPosi));
		criteria.put("cantidad", Long.parseLong(cantidad));
		
		mantenimientoRECDigitacionCDRDAO.ofertaDevolucion(criteria);
		
		//Ponemos a session los valores para ejecutarlo en el metodo de validacion con los mismos parametros
		
		//
		
		String mensajeError = mantenimientoRECDigitacionCDRDAO.mensajeError();
		String mensajeOferta = mantenimientoRECDigitacionCDRDAO.mensajeOferta();
		
		if(log.isDebugEnabled())
			log.debug(String.format("%s|%s", mensajeOferta, StringUtils.isBlank(mensajeError)?"":mensajeError));
		
		//ponemos en session la lista de todos los productos
		Map criteria2 = new HashMap();
		criteria2.put("unidad", Integer.parseInt("0"));
		
		List listaProductos = mantenimientoRECDigitacionCDRDAO.getGttOferta(criteria2);
		
		//session.setAttribute(Constants.MANTENIMIENTO_REC_DIGITACION_CDR_LISTA_OFERTAS, listaProductos);
		
		if(log.isDebugEnabled())
		{
			if (listaProductos != null && listaProductos.size() > 0) {
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA SUBIDA A SESSION, TAMAÑO" + listaProductos.size());
			}
			else
			{
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA VACIA!!!!!!");
			}
		}		
		//
		
		String valor = String.format("%s|%s", mensajeOferta, StringUtils.isBlank(mensajeError)?"":mensajeError);
		
		resultado.put("valor", valor);
		resultado.put("mantenimientoRecDigitacionCdrOfertaParametro", criteria);
		resultado.put("mantenimientoRecDigitacionCdrListaOfertas", listaProductos);
		return resultado;
	}*/
	
	public String ofertaDevolucion(String oidsoliPosi, String cantidad) {
		
		if (log.isDebugEnabled()) {
			log.debug("oidsoliPosi = " + oidsoliPosi);
			log.debug("cantidad = " + cantidad);
		}
		
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		HttpSession session = request.getSession();
		
		Map criteria = new HashMap();
		criteria.put("oidSoliPosi", Long.parseLong(oidsoliPosi));
		criteria.put("cantidad", Long.parseLong(cantidad));

		mantenimientoRECDigitacionCDRDAO.ofertaDevolucion(criteria);

		//Ponemos a session los valores para ejecutarlo en el metodo de validacion con los mismos parametros
		session.setAttribute(Constants.MANTENIMIENTO_REC_DIGITACION_CDR_OFERTA_PARAMETRO, criteria);
		//
		
		String mensajeError = mantenimientoRECDigitacionCDRDAO.mensajeError();
		String mensajeOferta = mantenimientoRECDigitacionCDRDAO.mensajeOferta();

		if (log.isDebugEnabled())
			log.debug(String.format("%s|%s", mensajeOferta, StringUtils.isBlank(mensajeError) ? "" : mensajeError));

		// ponemos en session la lista de todos los productos
		Map criteria2 = new HashMap();
		criteria2.put("unidad", Integer.parseInt("0"));

		List listaProductos = mantenimientoRECDigitacionCDRDAO.getGttOferta(criteria2);

		session.setAttribute(Constants.MANTENIMIENTO_REC_DIGITACION_CDR_LISTA_OFERTAS, listaProductos);
		
		if(log.isDebugEnabled())
		{
			if (listaProductos != null && listaProductos.size() > 0) {
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA SUBIDA A SESSION, TAMAÑO " + listaProductos.size());
			}
			else
			{
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA VACIA!!!!!!");
			}
		}		
		//

		return String.format("%s|%s", mensajeOferta, StringUtils.isBlank(mensajeError) ? "" : mensajeError);
	}
	
	public String getOfertaMensaje(){
		String mensajeOferta = mantenimientoRECDigitacionCDRDAO.mensajeOferta(); 
		
		return mensajeOferta;
	}

	/**
	 * Validar productos oferta.
	 *
	 * @param codigoPais the codigo pais
	 * @param codigoConsejera the codigo consejera
	 * @param cantidades the cantidades
	 * @param porcentajeDevolucionConsultora the porcentaje devolucion consultora
	 * @param montoDevolucionActual the monto devolucion actual
	 * @param montoDevolucion the monto devolucion
	 * @param montoPedido the monto pedido
	 * @param oidPeriodoCDR the oid periodo cdr
	 * @param codigoOperacionSICC the codigo operacion sicc
	 * @param tipoOperacionSICC the tipo operacion sicc
	 * @param indCamVali the ind cam vali
	 * @param codigoMotivo the codigo motivo
	 * @param mantenimientoRecDigitacionCdrListaOfertas the mantenimiento rec digitacion cdr lista ofertas
	 * @param mantenimientoRecDigitacionCdrOfertaParametro the mantenimiento rec digitacion cdr oferta parametro
	 * @return the string
	 */
	public String validarProductosOferta(String codigoPais,
			String codigoConsejera, String cantidades,
			String porcentajeDevolucionConsultora,
			String montoDevolucionActual, String montoDevolucion,
			String montoPedido, String oidPeriodoCDR,
			String codigoOperacionSICC, String tipoOperacionSICC,
			String indCamVali, String codigoMotivo) {

		if (log.isDebugEnabled()) {
			log.debug("codigoPais = " + codigoPais);
			log.debug("codigoConsejera = " + codigoConsejera);
			log.debug("cantidades = " + cantidades);
			log.debug("porcentajeDevolucionConsultora = " + porcentajeDevolucionConsultora);
			log.debug("montoDevolucionActual = " + montoDevolucionActual);
			log.debug("montoDevolucion = " + montoDevolucion);
			log.debug("montoPedido = " + montoPedido);
			log.debug("oidPeriodoCDR = " + oidPeriodoCDR);
			log.debug("codigoOperacionSICC = " + codigoOperacionSICC);
			log.debug("tipoOperacionSICC = " + tipoOperacionSICC);
			log.debug("indCamVali = " + indCamVali);
			log.debug("codigoMotivo = " + codigoMotivo);
		}

		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		HttpSession session = request.getSession();

		String estado = Constants.OK;
		double pdc = 0;
		double montoDevolucionActualAcumulado = 0;

		try {
			pdc = Double.parseDouble(porcentajeDevolucionConsultora);
		} catch (Exception ex) {
			log.warn(ex.getMessage());
		}

		try {
			montoDevolucionActualAcumulado = Double.parseDouble(montoDevolucionActual);
		} catch (Exception ex) {
			log.warn(ex.getMessage());
		}

		// Los codigos y cantidades acumuladas de los proudctos vienen separados
		// por | y ;
		Map cantidadesProductos = new HashMap();
		String[] cantidadesAcc = StringUtils.splitPreserveAllTokens(cantidades, ";");

		if (StringUtils.isNotBlank(cantidades)) {
			for (int i = 0; i < cantidadesAcc.length; i++) {
				String valorCompuesto = cantidadesAcc[i];
				String valores[] = StringUtils.splitPreserveAllTokens(valorCompuesto, "|");

				cantidadesProductos.put(valores[0], valores[1]);
			}
		}
		//

		List listaProductosGTT = (List) session.getAttribute(Constants.MANTENIMIENTO_REC_DIGITACION_CDR_LISTA_OFERTAS);
		
		if(log.isDebugEnabled())
		{
			if (listaProductosGTT != null && listaProductosGTT.size() > 0) {
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA OBTENIDA DESDE SESSION, TAMAÑO " + listaProductosGTT.size());
			}
			else
			{
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>NO SE HA OBTENIDO LA LISTA DESDE SESSION");
			}
		}
		
		//Volvemos a realizar la validacion del metodo ofertaDevolucion
		//Se hace esto ya que el valor de la GTT se pierde
		Map params = (Map)session.getAttribute(Constants.MANTENIMIENTO_REC_DIGITACION_CDR_OFERTA_PARAMETRO);
		if(params != null)
		{
			if(log.isDebugEnabled())
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>VOLVIENDO A EJECUTAR LA VALIDACION");
			
			mantenimientoRECDigitacionCDRDAO.ofertaDevolucion(params);
		}
		else
		{
			if(log.isDebugEnabled())
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>NO SE HA EJECUTADO LA VALIDACION");
		}
		//
		
		StringBuilder sbPosBus = new StringBuilder();
		StringBuilder sbPosOfe = new StringBuilder();
		StringBuilder sbCUVs = new StringBuilder();
		StringBuilder sbCantidades = new StringBuilder();
		StringBuilder sbDescripciones = new StringBuilder();
		StringBuilder sbPrecios = new StringBuilder();

		if (listaProductosGTT != null && listaProductosGTT.size() > 0) {
			for (int i = 0; i < listaProductosGTT.size(); i++) {
				Map producto = (Map) listaProductosGTT.get(i);

				// Realizamos la validacion de producto ganador
				String codiVent = MapUtils.getString(producto, "codiVent", "");
				String posOfer = MapUtils.getString(producto, "posOfer", "");
				String posBusc = MapUtils.getString(producto, "posBusc", "");
				String indBusc = MapUtils.getString(producto, "indBusc", "");
				String unidADevo = MapUtils.getString(producto, "unidADevo", "");
				String precProd = MapUtils.getString(producto, "precProd", "");
				String descProd = MapUtils.getString(producto, "descProd", "");
				String factRepe = MapUtils.getString(producto, "factRepe", "");
				
				String mensaje = "";

				Map criteria = new HashMap();
				criteria.put("codiVent", codiVent);
				criteria.put("posOfer", posOfer);

				if (StringUtils.isNotBlank(codigoOperacionSICC)&& StringUtils.equals(indCamVali,Constants.CDR_INDICADOR_VALIDA_PERIODO_EN_PEDIDO)) {
					mensaje = getValExcepProduGanador(oidPeriodoCDR, codiVent, codigoOperacionSICC, tipoOperacionSICC, posOfer, codigoMotivo,codigoPais,"","");
					// Actualizamos el valor en la GTT
					mensaje = StringUtils.split(mensaje, "|")[0];
					if(StringUtils.equalsIgnoreCase(mensaje, "null"))
							mensaje = null;
					criteria.put("mensaje", mensaje);
					mantenimientoRECDigitacionCDRDAO.updateGttOferta(criteria);

					if (StringUtils.isNotBlank(mensaje))
					{
						estado = Constants.ERROR;
						continue;
					}						
				}
				//

				// Realizamos la validacion de unidades reclamadas
				String cantidadAcumulada = MapUtils.getString(cantidadesProductos, codiVent, "");

				int unidadesReclamar = 0;
				if (StringUtils.isNotBlank(cantidadAcumulada)) {
					unidadesReclamar = unidadesReclamar + Integer.parseInt(cantidadAcumulada);// + Integer.parseInt(unidADevo);
				} else {
					unidadesReclamar = unidadesReclamar + Integer.parseInt(unidADevo);
				}
				cantidadesProductos.put(codiVent, String.valueOf(unidadesReclamar));

				mensaje = "";
				if (StringUtils.equals(indCamVali, Constants.CDR_INDICADOR_VALIDA_PERIODO_EN_PEDIDO)) {
					mensaje = getValUnidadReclamo(codigoPais, codigoConsejera, posOfer, String.valueOf(unidadesReclamar), codigoOperacionSICC, tipoOperacionSICC,"");

					// Actualizamos el valor en la GTT
					criteria.put("mensaje", mensaje);
					mantenimientoRECDigitacionCDRDAO.updateGttOferta(criteria);

					if (StringUtils.isNotBlank(mensaje))
					{
						estado = Constants.ERROR;
						continue;
					}						
				}
				//

				// Realizamos la validacion de monto de porcentaje de
				// devolucion, pero sin incluir el valor del producto que se ha digitado				
				if(StringUtils.equals(indBusc, Constants.NUMERO_CERO))
				{
					montoDevolucionActualAcumulado = (new BigDecimal(montoDevolucionActualAcumulado + (Double.parseDouble(precProd) * Double.parseDouble(unidADevo)))).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
					double porcentajeDevolucion = (new BigDecimal(((montoDevolucionActualAcumulado + Double.parseDouble(montoDevolucion)) / Double.parseDouble(montoPedido)) * 100)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
					
					if(log.isDebugEnabled())
					{
						log.debug("codiVent = " + codiVent);
						log.debug("precProd = " + precProd);
						log.debug("unidADevo = " + unidADevo);

						log.debug("montoDevolucionActualAcumulado = " + montoDevolucionActualAcumulado);
						log.debug("porcentajeDevolucion = " + porcentajeDevolucion);
					}
					
					mensaje = "";
					if (porcentajeDevolucion > pdc) {
						mensaje = "El monto de devolucion supera el %s%% (MP: %s / MD: %s)";
						
						criteria.put("mensaje", String.format(mensaje, porcentajeDevolucionConsultora, montoPedido, (new BigDecimal(montoDevolucionActualAcumulado)).setScale(2, RoundingMode.HALF_EVEN).toString()));
						mantenimientoRECDigitacionCDRDAO.updateGttOferta(criteria);

						estado = Constants.ERROR;

						break;
					}
					//

					// Todo esta OK
					sbPosBus.append(posBusc).append(";");
					sbPosOfe.append(posOfer).append(";");
					sbCUVs.append(codiVent).append(";");
					sbCantidades.append(unidADevo).append(";");
					sbDescripciones.append(descProd).append(";");
					sbPrecios.append(precProd).append(";");
				}
				//				
			}
		}

		// Recargamos la lista en session
		Map criteria = new HashMap();
		criteria.put("unidad", Integer.parseInt("0"));

		List listaProductos = mantenimientoRECDigitacionCDRDAO.getGttOferta(criteria);
		session.setAttribute(Constants.MANTENIMIENTO_REC_DIGITACION_CDR_LISTA_OFERTAS, listaProductos);
		//

		if(log.isDebugEnabled())
		{
			if (listaProductos != null && listaProductos.size() > 0) {
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA SUBIDA A SESSION, TAMAÑO " + listaProductos.size());
			}
			else
			{
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA VACIA!!!!!!");
			}
		}		
		
		if (!StringUtils.equals(estado, Constants.ERROR)) {
			estado = String.format("%s|%s|%s|%s|%s|%s", sbPosBus.toString(),
					sbPosOfe.toString(), sbCUVs.toString(),
					sbCantidades.toString(),
					StringUtils.remove(sbDescripciones.toString(), "'"),
					sbPrecios.toString());
		}

		return estado;
	}
	
	/*public String validarProductosOferta(String codigoPais,
			String codigoConsejera, String cantidades,
			String porcentajeDevolucionConsultora,
			String montoDevolucionActual, String montoDevolucion,
			String montoPedido, String oidPeriodoCDR,
			String codigoOperacionSICC, String tipoOperacionSICC,
			String indCamVali, String codigoMotivo,
			List mantenimientoRecDigitacionCdrListaOfertas,
			Map mantenimientoRecDigitacionCdrOfertaParametro) {
		
		if (log.isDebugEnabled()) {
			log.debug("codigoPais = " + codigoPais);
			log.debug("codigoConsejera = " + codigoConsejera);
			log.debug("cantidades = " + cantidades);
			log.debug("porcentajeDevolucionConsultora = " + porcentajeDevolucionConsultora);
			log.debug("montoDevolucionActual = " + montoDevolucionActual);
			log.debug("montoDevolucion = " + montoDevolucion);
			log.debug("montoPedido = " + montoPedido);
			log.debug("oidPeriodoCDR = " + oidPeriodoCDR);
			log.debug("codigoOperacionSICC = " + codigoOperacionSICC);
			log.debug("tipoOperacionSICC = " + tipoOperacionSICC);
			log.debug("indCamVali = " + indCamVali);
			log.debug("codigoMotivo = " + codigoMotivo);
		}
		
		String estado = Constants.OK;
		double pdc = 0;
		double montoDevolucionActualAcumulado = 0;
		
		try {
			pdc = Double.parseDouble(porcentajeDevolucionConsultora);
		} catch (Exception ex) {
			log.warn(ex.getMessage());
		}

		try {
			montoDevolucionActualAcumulado = Double.parseDouble(montoDevolucionActual);
		} catch (Exception ex) {
			log.warn(ex.getMessage());
		}
		
		// Los codigos y cantidades acumuladas de los proudctos vienen separados
		// por | y ;
		Map cantidadesProductos = new HashMap();
		String []cantidadesAcc = StringUtils.splitPreserveAllTokens(cantidades, ";");
		
		if (StringUtils.isNotBlank(cantidades)) {
			for (int i = 0; i < cantidadesAcc.length; i++) {
				String valorCompuesto = cantidadesAcc[i];
				String valores[] = StringUtils.splitPreserveAllTokens(valorCompuesto, "|");
				
				cantidadesProductos.put(valores[0], valores[1]);
			}
		}
		//
		
		List listaProductosGTT = mantenimientoRecDigitacionCdrListaOfertas;
		
		if(log.isDebugEnabled())
		{
			if (listaProductosGTT != null && listaProductosGTT.size() > 0) {
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA OBTENIDA DESDE SESSION, TAMAÑO" + listaProductosGTT.size());
			}
			else
			{
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>NO SE HA OBTENIDO LA LISTA DESDE SESSION");
			}
		}
		
		//Volvemos a realizar la validacion del metodo ofertaDevolucion
		//Se hace esto ya que el valor de la GTT se pierde
		Map params = mantenimientoRecDigitacionCdrOfertaParametro;
		if(params != null)
		{
			if(log.isDebugEnabled())
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>VOLVIENDO A EJECUTAR LA VALIDACION");
			
			mantenimientoRECDigitacionCDRDAO.ofertaDevolucion(params);
		}
		else
		{
			if(log.isDebugEnabled())
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>NO SE HA EJECUTADO LA VALIDACION");
		}
		//
		
		StringBuilder sbPosBus = new StringBuilder();
		StringBuilder sbPosOfe = new StringBuilder();
		StringBuilder sbCUVs = new StringBuilder();
		StringBuilder sbCantidades = new StringBuilder();
		StringBuilder sbDescripciones = new StringBuilder();
		StringBuilder sbPrecios = new StringBuilder();
		
		if (listaProductosGTT != null && listaProductosGTT.size() > 0) {
			for (int i = 0; i < listaProductosGTT.size(); i++) {
				Map producto = (Map)listaProductosGTT.get(i);
				
				//Realizamos la validacion de producto ganador
				String codiVent = MapUtils.getString(producto, "codiVent", "");
				String posOfer = MapUtils.getString(producto, "posOfer", "");
				String posBusc = MapUtils.getString(producto, "posBusc", "");
				String indBusc = MapUtils.getString(producto, "indBusc", "");
				String unidADevo = MapUtils.getString(producto, "unidADevo", "");				
				String precProd = MapUtils.getString(producto, "precProd", "");
				String descProd = MapUtils.getString(producto, "descProd", "");
				String factRepe = MapUtils.getString(producto, "factRepe", "");
				
				String mensaje = "";
				
				Map criteria = new HashMap();				
				criteria.put("codiVent", codiVent);
				criteria.put("posOfer", posOfer);
								
				if (StringUtils.isNotBlank(codigoOperacionSICC)&& StringUtils.equals(indCamVali,Constants.CDR_INDICADOR_VALIDA_PERIODO_EN_PEDIDO)) {
					mensaje = getValExcepProduGanador(oidPeriodoCDR, codiVent, codigoOperacionSICC, tipoOperacionSICC, posOfer, codigoMotivo,codigoPais,"","");
					//Actualizamos el valor en la GTT
					mensaje = StringUtils.split(mensaje, "|")[0];
					if(StringUtils.equalsIgnoreCase(mensaje, "null"))
							mensaje = null;
					criteria.put("mensaje", mensaje);
					mantenimientoRECDigitacionCDRDAO.updateGttOferta(criteria);

					if(StringUtils.isNotBlank(mensaje))
					{
						estado = Constants.ERROR;
						continue;
					}						
				}
				//
				
				//Realizamos la validacion de unidades reclamadas
				String cantidadAcumulada = MapUtils.getString(cantidadesProductos, codiVent, "");
				
				int unidadesReclamar = 0;
				if (StringUtils.isNotBlank(cantidadAcumulada)) {
					unidadesReclamar = unidadesReclamar + Integer.parseInt(cantidadAcumulada);// + Integer.parseInt(unidADevo);
				} else {
					unidadesReclamar = unidadesReclamar + Integer.parseInt(unidADevo);
				}
				cantidadesProductos.put(codiVent, String.valueOf(unidadesReclamar));
								
				mensaje = "";
				if (StringUtils.equals(indCamVali, Constants.CDR_INDICADOR_VALIDA_PERIODO_EN_PEDIDO)) {
					mensaje = getValUnidadReclamo(codigoPais, codigoConsejera, posOfer, String.valueOf(unidadesReclamar), codigoOperacionSICC, tipoOperacionSICC,"");

					//Actualizamos el valor en la GTT
					criteria.put("mensaje", mensaje);
					mantenimientoRECDigitacionCDRDAO.updateGttOferta(criteria);

					if(StringUtils.isNotBlank(mensaje))
					{
						estado = Constants.ERROR;
						continue;
					}						
				}
				//
				
				// Realizamos la validacion de monto de porcentaje de
				// devolucion, pero sin incluir el valor del producto que se ha digitado				
				if(StringUtils.equals(indBusc, Constants.NUMERO_CERO))
				{
					montoDevolucionActualAcumulado = (new BigDecimal(montoDevolucionActualAcumulado + (Double.parseDouble(precProd) * Double.parseDouble(unidADevo)))).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
					double porcentajeDevolucion = (new BigDecimal(((montoDevolucionActualAcumulado + Double.parseDouble(montoDevolucion)) / Double.parseDouble(montoPedido)) * 100)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
					
					if(log.isDebugEnabled())
					{
						log.debug("codiVent = " + codiVent);
						log.debug("precProd = " + precProd);
						log.debug("unidADevo = " + unidADevo);

						log.debug("montoDevolucionActualAcumulado = " + montoDevolucionActualAcumulado);
						log.debug("porcentajeDevolucion = " + porcentajeDevolucion);
					}
					
				mensaje = "";	
					if (porcentajeDevolucion > pdc) {
						mensaje = "El monto de devolucion supera el %s%% (MP: %s / MD: %s)";
						
						criteria.put("mensaje", String.format(mensaje, porcentajeDevolucionConsultora, montoPedido, (new BigDecimal(montoDevolucionActualAcumulado)).setScale(2, RoundingMode.HALF_EVEN).toString()));
					mantenimientoRECDigitacionCDRDAO.updateGttOferta(criteria);
					
					estado = Constants.ERROR;
					
					break;
				}
				//
				
				//Todo esta OK
					sbPosBus.append(posBusc).append(";");
					sbPosOfe.append(posOfer).append(";");
					sbCUVs.append(codiVent).append(";");
					sbCantidades.append(unidADevo).append(";");
					sbDescripciones.append(descProd).append(";");
					sbPrecios.append(precProd).append(";");
				}
				//
			}
		}

		//Recargamos la lista en session
		Map criteria = new HashMap();
		criteria.put("unidad", Integer.parseInt("0"));
		
		List listaProductos = mantenimientoRECDigitacionCDRDAO.getGttOferta(criteria);		
		//session.setAttribute(Constants.MANTENIMIENTO_REC_DIGITACION_CDR_LISTA_OFERTAS, listaProductos);
		//
		
		if(log.isDebugEnabled())
		{
			if (listaProductos != null && listaProductos.size() > 0) {
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA SUBIDA A SESSION, TAMAÑO " + listaProductos.size());
			}
			else
			{
				log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTA VACIA!!!!!!");
			}
		}		
		
		if (!StringUtils.equals(estado, Constants.ERROR)) {
			estado = String.format("%s|%s|%s|%s|%s|%s", sbPosBus.toString(),
					sbPosOfe.toString(), sbCUVs.toString(),
					sbCantidades.toString(),
					StringUtils.remove(sbDescripciones.toString(), "'"),
					sbPrecios.toString());
		}
		
		return estado;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#validarAsignarLideres(java.lang
	 * .String)
	 */
	public String validarAsignarLideres(String codigoPais, 
			String codigoMarca, String codigoCanal, String codigoZona) {
		String resultado = "";
		String indicadorValidaCierreRegion = "";
		boolean esRegionCerrada = false;
		try {
			
			// -- Obtenemos los datos de pais, marca y canal
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);

			// -- obtenemos la zona de la seccion
			if(codigoZona.length() == 9){
				//criteria.put("codigoZona", codigoZona);
				
				String codigoRegion = codigoZona.substring(2, 4);
				criteria.put("codigoRegion", codigoRegion);
				
				Map result = new HashMap();
				result = mantenimientoPEJProgramaEjecutivasDAO.getPeriodoDefault();
				String codigoPeriodoActual = (String) result.get("codigoPeriodo");
				
				criteria.put("codigoPeriodoActual", codigoPeriodoActual);
			
			//-- Recuperar indicador de AsignarLider --------------------
			Map criteriaParametrosPais = new HashMap();
			criteriaParametrosPais.put("codigoPais", codigoPais);
			criteriaParametrosPais.put("codigoSistema", Constants.LET_CODIGO_SISTEMA);
			criteriaParametrosPais.put("codigoParametro", Constants.LET_CODIGO_PARAM_005);
			
				indicadorValidaCierreRegion = mantenimientoLETLideresDAO.getIndicadorAsignarLider(criteriaParametrosPais); 
			
				esRegionCerrada = clienteService.esRegionCerradaxSeccion(criteria);
			}
			
			if(esRegionCerrada && indicadorValidaCierreRegion.equals(Constants.NRO_UNO)) {
				resultado = "Ya se realiz el cierre de regin para la campaa actual y no se puede realizar la asignacin.";
			} else{
				resultado = "1"; //Permite setear el ingreso de Codigo de Cliente
				//f.setPermitirIngresoCodigoCliente(true);
			}
			
			log.info("Salio MantenimientoLETLideresAction - asignar");
			
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validarDocumentoIdentidadSTO(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validarDocumentoIdentidadSTO(String codigoPais, String tipoDocumento, String numeroDocumento) {
		String message = "";
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("oidPais", reporteDAO.getOidString("getOidPaisByCodigoPais", criteria));
		criteria.put("tipoDocumento", tipoDocumento);
		String oidTipoDocumento = mantenimientoMAEClienteDAO.getOidTipoDocumento(criteria);
		criteria.put("tipoDocumentoIdentidad", oidTipoDocumento);
		criteria.put("numeroDocumentoIdentidad", numeroDocumento);
		
		String documento = clienteService.validarDocumentoIdentidad(criteria);
		
		if(documento != null) {
			
			if(documento.equals("Modulo10")) 
			{
				message = getKeyMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValido");			
			} 
			else if(documento.equals("Modulo11V")) 
			{
				message = getKeyMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValidoRUT");
			} 
			/*else 
			{
				StringTokenizer st = new StringTokenizer(documento, "-");
				String saldo = st.nextToken();
				
				if(saldo.equals(" "))
					message = getKeyMessage("mantenimientoMAEClienteForm.msg.ClienteExiste");
				else
					message = this.getKeyMessage("mantenimientoMAEClienteForm.msg.ClienteTieneCuentasCastigadas", new String[]{saldo}, new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
			}*/	
		}
		
		return message;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validarCUVFaltanteAnunciado(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validarCUVFaltanteAnunciado(String codigoVenta, String codigoPeriodo, String codigoCliente) {
		String message = "";
		
		//Obtiene Oid de Territorio Administrativo
		String oidTerraAdmin = mantenimientoOCRCargaPedidoDAO.getOidTerritorioAdministrativo(codigoCliente);
		
		//Obtiene Oid Oferta
		Map map = new HashMap();
		map.put("codigoVenta", codigoVenta);
		map.put("codigoPeriodo", codigoPeriodo);
		BigDecimal oidOferta = mantenimientoOCRPedidoControlFacturacionDAO.getOfertaDetalleByPeriodoCodigoVenta(map);
		
		Map criteria = new HashMap();
		criteria.put("oidCliente", Integer.parseInt(mantenimientoLETLideresDAO.getOidClienteByCodigoCliente(codigoCliente)));
		criteria.put("oidTerraAdmin", Integer.parseInt(oidTerraAdmin));
		criteria.put("oidOfertaDetal", Integer.parseInt(oidOferta+""));
		
		int resultado = mantenimientoOCRCargaPedidoDAO.validarCUVFaltanteAnunciado(criteria);
		
		if(resultado > 0){
			message = "No se puede ingresar el CUV " + codigoVenta + " por que esta como Faltante Anunciado";
		}else{
			message = "False";
		}
		
		return message;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validarCUVCompuesta(java.lang.String, java.lang.String)
	 */
	public String validarCUVCompuesta(String codigoVenta, String codigoPeriodo) {
		String message = "";
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		String oidPeriodo = reporteDAO.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		
		Map params = new HashMap();
		params.put("oidPeriodo", Integer.parseInt(oidPeriodo));
		params.put("codigoVenta", codigoVenta);
		
		String resultado = mantenimientoOCRCargaPedidoDAO.validarCUVCompuesta(params);
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validarCUVLimiteVenta(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validarCUVLimiteVenta(String codigoVenta, String codigoPeriodo, String codigoCliente, String numeroUnidades) {
String message = "";
		
		//Obtiene Oid de Territorio Administrativo
		String oidTerraAdmin = mantenimientoOCRCargaPedidoDAO.getOidTerritorioAdministrativo(codigoCliente);
		
		//Obtiene Oid Oferta
		Map map = new HashMap();
		map.put("codigoVenta", codigoVenta);
		map.put("codigoPeriodo", codigoPeriodo);
		BigDecimal oidOferta = mantenimientoOCRPedidoControlFacturacionDAO.getOfertaDetalleByPeriodoCodigoVenta(map);
		
		Map criteria = new HashMap();
		criteria.put("oidCliente", Integer.parseInt(mantenimientoLETLideresDAO.getOidClienteByCodigoCliente(codigoCliente)));
		criteria.put("oidTerraAdmin", Integer.parseInt(oidTerraAdmin));
		criteria.put("oidOfertaDetal", Integer.parseInt(oidOferta+""));
		
		Integer resultado = mantenimientoOCRCargaPedidoDAO.validarCUVLimiteVenta(criteria);
		
	     if (resultado==null) {
	    	 message = "False";
	    	 return message;
	     }	
		
		 if(resultado > 0){
			if(Integer.parseInt(numeroUnidades) > resultado){
				message = "El CUV " + codigoVenta + " solo se puede solicitar un máximo de " + resultado + " unidades, por que tiene limite de venta";
			}else{
				message = "False";
			}
		 }else if(resultado == 0){
			 message = "El CUV ".concat(codigoVenta).concat(" está agotado, por favor ingrese otro CUV").concat("|");
		}
		
		
		return message;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getMontoMinimoConsultora(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getMontoMinimoConsultora(String codigoCliente) {
		//Obtiene Oid de Territorio Administrativo
		String oidTerraAdmin = mantenimientoOCRCargaPedidoDAO.getOidTerritorioAdministrativo(codigoCliente);
		
		BigDecimal montoMinimo = new BigDecimal(0);
		
		if(!StringUtils.isBlank(oidTerraAdmin)){
			Map criteria = new HashMap();
			criteria.put("oidCliente", Integer.parseInt(mantenimientoLETLideresDAO.getOidClienteByCodigoCliente(codigoCliente)));
			criteria.put("oidTerraAdmin", Integer.parseInt(oidTerraAdmin));
			criteria.put("nominal", null);
			criteria.put("nivel", null);
			
			mantenimientoOCRCargaPedidoDAO.executeGetMontoMinimoConsultora(criteria);
			
			montoMinimo = (BigDecimal) criteria.get("nivel");
			
			if(montoMinimo == null)
				montoMinimo =  new BigDecimal(0);
		}
		
		return montoMinimo.toString();
	}

	/**
	 * @param listaArchivos
	 * @param fechaProceso
	 * @return
	 */
	public String getMensajeArchivosBoletasVenta(String listaArchivos, String fechaProceso) {

		String mensajeValidacion = "";
		boolean validacionArchivos = true;
					
		if(listaArchivos != null && listaArchivos.length()>0){
			
			String[] archivos = listaArchivos.split("--");
			
			String[] nombre = new String[archivos.length];
			String[] lineas = new String[archivos.length];			
			
			for(int i=0; i < archivos.length; i++){
				String[] param = archivos[i].split("___");
				String nombreArch = param[0];				
				nombreArch = nombreArch.substring(0, nombreArch.lastIndexOf("."));
				nombre[i] = nombreArch;
				lineas[i] = param[1];
			}
						
			for(int i=0; i < archivos.length; i++){
				Map criteria = new HashMap();					
				criteria.put("nombreArchivo", nombre[i]);
				criteria.put("fechaProceso", fechaProceso);
				Integer contRegAsoc = interfazSiCCDAO.getContRegistrosAsociadosCargaArchivosBolVent(criteria);					
				if(contRegAsoc == 0){
					validacionArchivos = false;											
				}
			}
			
			if(!validacionArchivos){
				
				mensajeValidacion = getKeyMessage("interfazXRXBoletaVentaElectronica.error.archivo");

				List archivosPendientesList = new ArrayList();
				
				Map criteria = new HashMap();
				criteria.put("fechaProceso", fechaProceso);
				
				archivosPendientesList = interfazSiCCDAO.getArchivosPendientesBolVent(criteria);
				
				if(archivosPendientesList!=null && archivosPendientesList.size()>0){
					String listaArchivosMess = "";
					for(int i=0; i<archivosPendientesList.size(); i++){
						Map archivoPendiente = (Map)archivosPendientesList.get(i);
						listaArchivosMess +=  " - "+archivoPendiente.get("nombreArchivo")+"\t"+archivoPendiente.get("totalRegistros")+" registro(s) \n";
					}							
					mensajeValidacion += this.getKeyMessage("interfazXRXBoletaVentaElectronica.error.existe.pendiente", new String[]{fechaProceso});						
					mensajeValidacion += listaArchivosMess;
				}else{
					mensajeValidacion += this.getKeyMessage("interfazXRXBoletaVentaElectronica.error.noexiste.pendiente", new String[]{fechaProceso});					
				}
				
			}
			
		}
		
		return mensajeValidacion;
	}

	/**
	 * @param listaArchivos
	 * @param fechaProceso
	 * @return
	 */
	public String getMensajeArchivosNotasCredito(String listaArchivos, String fechaProceso) {
		String mensajeValidacion = "";
		boolean validacionArchivos = true;
					
		if(listaArchivos != null && listaArchivos.length()>0){
			
			String[] archivos = listaArchivos.split("--");
			
			String[] nombre = new String[archivos.length];
			String[] lineas = new String[archivos.length];			
			
			for(int i=0; i < archivos.length; i++){
				String[] param = archivos[i].split("___");
				String nombreArch = param[0];				
				nombreArch = nombreArch.substring(0, nombreArch.lastIndexOf("."));
				nombre[i] = nombreArch;
				lineas[i] = param[1];
			}
						
			for(int i=0; i < archivos.length; i++){
				Map criteria = new HashMap();					
				criteria.put("nombreArchivo", nombre[i]);
				criteria.put("fechaProceso", fechaProceso);
				Integer contRegAsoc = interfazSiCCDAO.getContRegistrosAsociadosCargaArchivosNotasCred(criteria);					
				if(contRegAsoc == 0){
					validacionArchivos = false;											
				}
			}
			
			if(!validacionArchivos){
				
				mensajeValidacion = getKeyMessage("interfazXRXNotaCreditoElectronica.error.archivo");

				List archivosPendientesList = new ArrayList();
				
				Map criteria = new HashMap();
				criteria.put("fechaProceso", fechaProceso);
				
				archivosPendientesList = interfazSiCCDAO.getArchivosPendientesNotasCred(criteria);
								
				if(archivosPendientesList!=null && archivosPendientesList.size()>0){
					String listaArchivosMess = "";
					for(int i=0; i<archivosPendientesList.size(); i++){
						Map archivoPendiente = (Map)archivosPendientesList.get(i);
						listaArchivosMess +=  " - "+archivoPendiente.get("nombreArchivo")+"\t"+archivoPendiente.get("totalRegistros")+" registro(s) \n";
					}							
					mensajeValidacion += this.getKeyMessage("interfazXRXNotaCreditoElectronica.error.existe.pendiente", new String[]{fechaProceso});						
					mensajeValidacion += listaArchivosMess;
				}else{
					mensajeValidacion += this.getKeyMessage("interfazXRXNotaCreditoElectronica.error.noexiste.pendiente", new String[]{fechaProceso});					
				}
			}
			
		}
		
		return mensajeValidacion;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarCodigoVenta(java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValueCUV getValidarCodigoVenta(String codigoPais, String codigoPeriodo, String codigoCUV) {
		log.debug("getValidarCodigoVenta");
		LabelValueCUV result = new LabelValueCUV();
		String mensajeValidacion = "";
		if (StringUtils.isNotBlank(codigoCUV)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			
			/*PER-SiCC-2015-0172*/
			//Integer intCodigoPeriodoSiguiente = Integer.parseInt(codigoPeriodo);
			//intCodigoPeriodoSiguiente=intCodigoPeriodoSiguiente.intValue()+1;
			//criteria.put("codigoPeriodoSiguiente", intCodigoPeriodoSiguiente.intValue());
			/*PER-SiCC-2015-0172*/
			
			criteria.put("numeroCampanna", 1);
			criteria.put("oidPais", mantenimientoLETLideresDAO.getOidPaisByCodigoPaisLET(criteria));
			String codigoPeriodoSiguiente =  procesoMENGenerarMensajesDAO.getDevuelveCodigoCampanha(criteria);
			criteria.put("codigoPeriodoSiguiente", codigoPeriodoSiguiente);

			
			criteria.put("codigoCUV", codigoCUV);
			
			result.setValue(codigoCUV);
			
			Map producto = new HashMap();
			producto = this.procesoMENGenerarMensajesDAO.getDatosProductoCUVPeriodo(criteria);
			if (producto == null) {
				mensajeValidacion = this.getKeyMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVNoExiste");
				result.setError(mensajeValidacion);
			}	
			else {
				BigDecimal precio = (BigDecimal)producto.get("precio");
				BigDecimal pagina =  (BigDecimal)producto.get("pagina");
				String descripcionProducto = (String)producto.get("descripcionProducto");
				String descripcionCatalogo = (String)producto.get("descripcionCatalogo");
				result.setLabel(descripcionProducto);
				result.setCatalogo(descripcionCatalogo);
				result.setPrecio(precio.toString().trim());
				result.setPagina(pagina.toString().trim());
			}
			
		}	
		return result;
	}

	public String getCodigoActividadExistente(final String codigoPais,
			final String codigoActividad) {
	
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoActividad", codigoActividad);

			int result = mantenimientoCRAActividadDAO.getCodigoActividadExistente(criteria);
	
		return Integer.toString(result);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getConsideracionMensajes(java.lang.String, java.lang.String)
	 */
	public LabelValue[] getConsideracionMensajes(final String codigoPais, final String tipoMensaje) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("tipoMensaje", tipoMensaje);

			try {
				List lista = mantenimientoMENGenericoDAO.getConsideracion(criteria);
				if (lista != null && lista.size() > 0) {
					result = new LabelValue[lista.size()];
					for (int i = 0; i < lista.size(); i++) {
						Map base = (Map) lista.get(i);
						// Construimos la descripcion
						BigDecimal codigoTemp =  (BigDecimal) base.get("codigoConRes");
						String codigo = codigoTemp.toString().trim();
						String descripcion = (String) base.get("descripcionConRes");
						LabelValue lv = new LabelValue(descripcion, codigo);
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getSeccionMultiple2ByPaisMarcaCanalRegionZona(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getSeccionMultiple2ByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final ArrayList codigoRegion,
			final ArrayList codigoZona, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO.getLista(
						"getSeccionMultiple2ByPaisMarcaCanalRegionZona",
						criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTerritoriosMultiple2ByPaisMarcaCanalRegionZonaSeccion(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.lang.String)
	 */
	public LabelValue[] getTerritoriosMultiple2ByPaisMarcaCanalRegionZonaSeccion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final ArrayList codigoRegiones,
			final ArrayList codigoZonas, final ArrayList codigoSeccion,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)
					&& StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegiones);
			criteria.put("codigoZona", codigoZonas);
			criteria.put("codigoSeccion", codigoSeccion);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO
						.getLista(
								"getTerritoriosMultiple2ByPaisMarcaCanalRegionZonaSeccion",
								criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}
	
	public String getActuaFechaFase1(String codigoPais,String oidCrono,  String oidGrupoZona, String diasDesplazamiento){
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("oidGrupoZona", oidGrupoZona);
		criteria.put("diasDesplazamiento", diasDesplazamiento);
		criteria.put("oidCrono", oidCrono);
		
		return mantenimientoCRACronogramaFase1DAO.getActuaFechaFase1(criteria);
	}
	
	public String getActuaFechaFase2(String codigoPais,String oidCrono,  String oidGrupoZona, String diasDesplazamiento){
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("oidGrupoZona", oidGrupoZona);
		criteria.put("diasDesplazamiento", diasDesplazamiento);
		criteria.put("oidCrono", oidCrono);
		
		return mantenimientoCRACronogramaFase1DAO.getActuaFechaFase2(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getActividadByTipoMAV(java.lang.String)
	 */
	public LabelValue[] getActividadByTipoMAV(String codigoTipoMav) {
		
		LabelValue[] result = null;
		log.debug("Vamos a cargar la lista de Actividades: ");
		
		List actividadesList = reporteDAO.getActividadByTipoMAV(codigoTipoMav);
		log.debug("lista en ajax : " + actividadesList.size());

		if (actividadesList != null && actividadesList.size() > 0) {
			result = new LabelValue[actividadesList.size()];
			for (int i = 0; i < actividadesList.size(); i++) {
				Base actividad = (Base) actividadesList.get(i);
				LabelValue lv = new LabelValue(actividad.getDescripcion(), actividad.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTipoOfertaByTipoMAVActividad(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTipoOfertaByTipoMAVActividad(java.lang.String, java.lang.String)
	 */
	public LabelValue[] getTipoOfertaByTipoMAVActividad(String codigoActividad, String codigoTipoMav) {
		
		LabelValue[] result = null;
		log.debug("Vamos a cargar la lista de Ofertas por tipo Mav y Actividad: ");
        if(StringUtils.isEmpty(codigoActividad) || StringUtils.isBlank(codigoActividad)){
        	codigoActividad = "";
        }
		Map criteria = new HashMap();
		criteria.put("codigoActividad", codigoActividad);
		criteria.put("codigoTipoMav", codigoTipoMav);
		
		List ofertasList = reporteDAO.getTipoOfertaByTipoMAVActividad(criteria);
		
		log.debug("lista en ajax : " + ofertasList.size());

		if (ofertasList != null && ofertasList.size() > 0) {
			result = new LabelValue[ofertasList.size()];
			for (int i = 0; i < ofertasList.size(); i++) {
				Base oferta = (Base) ofertasList.get(i);
				LabelValue lv = new LabelValue(oferta.getDescripcion(), oferta.getCodigo());
				result[i] = lv;
			}
		}

		return result;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidacionRegistroMeta(java.lang.String, java.lang.String)
	 */
	public String getValidacionRegistroMeta(String codigoPais, String codigoConsultora) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoConsultora", codigoConsultora);
		
		String resultado = mantenimientoCUPLogrosDAO.getValidacionRegistroMeta(criteria);
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getPeriodoIngresoConsultora(java.lang.String, java.lang.String)
	 */
	public String getPeriodoIngresoConsultora(String codigoPais, String codigoConsultora) {
		String resultado = "";
		String periodoActual = "";
		String periodoIngreso = mantenimientoCUPLogrosDAO.getPeriodoIngresoConsultora(codigoConsultora);
		String codigoPeriodoFin = "";
		Map criteria = new HashMap();
		
		if(!StringUtils.isBlank(periodoIngreso)){
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", periodoIngreso);
			criteria.put("numeroPeriodo", Constants.NUMERO_TRES);
			
			codigoPeriodoFin = mantenimientoCUPLogrosDAO.getPeriodoSiguiente(criteria);
			
			resultado = periodoIngreso + "," + codigoPeriodoFin;
		}else{
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO);
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO);
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionDAO.getControlFacturacionById(criteria);
			periodoActual = controlFacturacion.getCodigoPeriodo();
			
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", periodoActual);
			criteria.put("numeroPeriodo", Constants.NUMERO_TRES);
			codigoPeriodoFin = mantenimientoCUPLogrosDAO.getPeriodoSiguiente(criteria);
	
			resultado = periodoActual + "," + codigoPeriodoFin;
		}
		
		return resultado;
	}
	
	/**
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @return
	 */
	public String getCronogramaFase1Existente(String codigoPais,String codigoPeriodo ){

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);

		// -- Logica
		int resultado = mantenimientoCRACronogramaFase1DAO.getCronogramaFase1Existente(criteria);

		return Integer.toString(resultado);
	}
	
	
	/**
	 * @param mantenimientoINCConfiguracionFaltanteDAO the mantenimientoINCConfiguracionFaltanteDAO to set
	 */
	public void setMantenimientoINCConfiguracionFaltanteDAO(
			MantenimientoINCConfiguracionFaltanteDAO mantenimientoINCConfiguracionFaltanteDAO) {
		this.mantenimientoINCConfiguracionFaltanteDAO = mantenimientoINCConfiguracionFaltanteDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getPremiosFaltante(java.lang.String[], java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPremiosFaltante(String[] oidConcurso, String oidPeriodo, String condicionTodos) {

		log.debug("Entro a getPremiosFaltante");
		log.debug("oidConcurso: " + oidConcurso);
		log.debug("oidPeriodo: " + oidPeriodo);
		log.debug("condicionTodos: " + condicionTodos);

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("oidConcurso", oidConcurso);
			criteria.put("oidPeriodo", oidPeriodo);

			List concursos = mantenimientoINCConfiguracionFaltanteDAO.getPremiosFaltante(criteria);
			
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("Todos", "");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} /*else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}*/
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getZonasFaltante(java.lang.String[], java.lang.String, java.lang.String)
	 */
	public LabelValue[] getZonasFaltante(String[] oidRegion, String oidPeriodo, String condicionTodos) {

		log.debug("Entro a getZonasFaltante");
		log.debug("oidRegion: " + oidRegion);
		log.debug("oidPeriodo: " + oidPeriodo);
		log.debug("condicionTodos: " + condicionTodos);

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("oidRegion", oidRegion);
			criteria.put("oidPeriodo", oidPeriodo);
			
			List concursos = mantenimientoINCConfiguracionFaltanteDAO.getZonasFaltante(criteria);
			
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("Todos", "");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} /*else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}*/
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getZonasAllDirectorioActivas(java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	public LabelValueCDR[] getZonasAllDirectorioActivas(String codigoPais,
			String codigoConexionExterna, String[] codigoRegiones, String indiceZona) {
		LabelValueCDR[] result = null;
		Map criteria = new HashMap();
		log.debug("indiceZona " + indiceZona);
		criteria.put("indicadorActivo", Constants.ESTADO_ACTIVO);
		criteria.put("codigoRegion", Arrays.asList(codigoRegiones));
		criteria.put("codigoPais", codigoPais);
		
		log.debug("criteria ade " + criteria);
		try {
			List regiones = null;
			
			if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
				regiones = mantenimientoMAEClienteDAO.getZonasDirectorioActivasFOX(criteria);
			else
				regiones = mantenimientoMAEClienteDAO.getZonasDirectorioActivas(criteria);
			
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValueCDR[regiones.size()];

				// Creamos una primera opcin vaca
				// result[0] = new LabelValue("", "");

				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion

					LabelValueCDR lv = new LabelValueCDR(
							region.getDescripcion(), region.getCodigo(), null,
							indiceZona);
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValueCDR[1];
				result[0] = new LabelValueCDR("", "", null, indiceZona);
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}
	
	/**
	 * @return the mantenimientoCRACalendarioDAO
	 */
	public MantenimientoCRACalendarioDAO getMantenimientoCRACalendarioDAO() {
		return mantenimientoCRACalendarioDAO;
	}

	/**
	 * @param mantenimientoCRACalendarioDAO the mantenimientoCRACalendarioDAO to set
	 */
	public void setMantenimientoCRACalendarioDAO(
			MantenimientoCRACalendarioDAO mantenimientoCRACalendarioDAO) {
		this.mantenimientoCRACalendarioDAO = mantenimientoCRACalendarioDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#updateIndCalendario(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void updateIndCalendario(String anhio,String codigoActividad, String fecha, String indNoLaborable, String indTransporte, String codigoPais, String usuario){
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("anhio", anhio);
		criteria.put("codigoActividad", codigoActividad);
		criteria.put("indNoLaborable", indNoLaborable);
		criteria.put("indTransporte", indTransporte);
		criteria.put("fecha", fecha);
		criteria.put("usuario", usuario);
		
		this.mantenimientoCRACalendarioDAO.updateIndCalendario(criteria);
		
	}
	
	public String getFeriadoValidoAnhio(String fecha,String anhio, String codigoActividad){

		Map criteria = new HashMap();
		criteria.put("fecha", fecha);
		criteria.put("anhio", anhio);
		criteria.put("codigoActividad", codigoActividad);

		// -- Logica
		int resultado = mantenimientoCRACalendarioDAO.getFeriadoValidoAnhio(criteria);

		return Integer.toString(resultado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getConcursosFaltantesByPaisMarcaCanalDetalle(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getConcursosFaltantesByPaisMarcaCanalDetalle(String codigoPais,
			String codigoMarca, String codigoCanal, String condicionTodos,
			String baseCalculo) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoPeriodo", null);
			criteria.put("baseCalculo", baseCalculo);
			List concursos = interfazSiCCDAO
					.getConcursosFaltantesByPaisMarcaCanalDetalle(criteria);
			if (concursos != null && concursos.size() > 0) {

				if (StringUtils.equals("T", condicionTodos)) {
					result = new LabelValue[concursos.size() + 1];
					result[0] = new LabelValue("Todos", "Todos");
					for (int i = 0; i < concursos.size(); i++) {
						Base periodo = (Base) concursos.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								periodo.getDescripcion(), periodo.getCodigo());
						result[i + 1] = lv;
					}
				} else {
					result = new LabelValue[concursos.size()];
					for (int i = 0; i < concursos.size(); i++) {
						Base concurso = (Base) concursos.get(i);
						LabelValue lv = new LabelValue(
								concurso.getDescripcion(), concurso.getCodigo());
						result[i] = lv;
					}
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "Todos");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarAsignarLiderIndicadorPrograma(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidarAsignarLiderIndicadorPrograma(String codigoUA, String codigoLider, String codigoPais) {
		
		String periodo = "";
		String periodoProceso = "";
		String periodoProcesoSgte = "";
		String periodoProcesoAnterior = "";
		Integer oidPeriodoProcesoAnterior ;
		int indicadorAsignarLider = 0;
		String resultado = "";
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		
		Map obtenerFechaPeriodoProceso = (HashMap) interfazSiCCDAO.getPeriodoFechaProcesoActual(criteria).get(0);
		periodoProceso = (String) obtenerFechaPeriodoProceso.get("cod_peri");
		periodoProcesoSgte = consultaPEJProgramaEjecutivasDAO.getObtienePeriodo(codigoPais, periodoProceso, 1);
		
		ParametroPais paramPais = new ParametroPais();
		paramPais.setCodigoPais(codigoPais);
		paramPais.setCodigoSistema(Constants.LET_CODIGO_SISTEMA);
		paramPais.setCodigoParametro(Constants.LET_CODIGO_PARAM_001);
		paramPais.setNombreParametro(Constants.LET_NOMBRE_PARAM_001);
	
		List lstParametrosLET = genericoDAO.getParametrosPais(paramPais);
		
		if (lstParametrosLET != null && lstParametrosLET.size()  > 0) {
			ParametroPais result = (ParametroPais)lstParametrosLET.get(0);
			indicadorAsignarLider = Integer.valueOf(result.getValorParametro()).intValue();
		}else{
			indicadorAsignarLider = 0;
		}
	
		if(indicadorAsignarLider == 0){
			periodo = periodoProceso;
        }else if(indicadorAsignarLider == 1){
        	periodo = periodoProcesoSgte;
        }else if(indicadorAsignarLider == 2){
        	boolean esRegionCerrada = false;
        	
        	Map criteriaRegionCerrada = new HashMap();
        	criteriaRegionCerrada.put("codigoPeriodoActual", periodoProceso);
        	criteriaRegionCerrada.put("codigoRegion", codigoUA.substring(2, 4));
        	
        	esRegionCerrada = clienteService.esRegionCerradaxSeccion(criteriaRegionCerrada);
        	
        	if(esRegionCerrada){
        		periodo = periodoProcesoSgte;
        	}else{
        		periodo = periodoProceso;
        	}
        }
		
		String codigoPeriodoQuiebre18 = consultaPEJProgramaEjecutivasDAO.getObtienePeriodo(codigoPais, periodo, -18);
		Map criteriaOidPeriodo = new HashMap();
		criteriaOidPeriodo.put("codigoPeriodo", codigoPeriodoQuiebre18);
		int oidPeriodoQuiebre18 = reporteDAO.getOidPeriodo(criteriaOidPeriodo);
		
		int indicadorProgramaLider = mantenimientoLETLideresDAO.getIndicadorProgramaLet(codigoPais);
		
		if(indicadorProgramaLider == 3){
			List lista = mantenimientoLETLideresDAO.getMaximoPeriodoHastaLider(codigoLider);
			
			if(lista != null && lista.size() > 0){
				Map mapMaxPeriodoHastaLider = (HashMap) lista.get(0);
				String maxOidPeriodoHasta = MapUtils.getString(mapMaxPeriodoHastaLider, "maxOidPeriodoHasta");
				String indDesvAuto = MapUtils.getString(mapMaxPeriodoHastaLider, "indDesvAuto");
				
				if(maxOidPeriodoHasta != null && Integer.parseInt(maxOidPeriodoHasta) >= oidPeriodoQuiebre18){
					if(Integer.parseInt(indDesvAuto) > 0){
						String descDesvinculacion = mantenimientoLETLideresDAO.getDescripcionDesvinculacion(indDesvAuto);
						
						resultado = "Lder desvinculada por " + descDesvinculacion + ". Desea continuar con asignacin?";
					}else{
						resultado = "1";
					}
				}else{
					resultado = "1";
				}
			}else{
				resultado = "1";
			}
		}else{
			resultado = "1";
		}
		
		if(indicadorProgramaLider == 4){
			resultado = "1";
			Map result = new HashMap();
			result = mantenimientoLECProgramaCorporativoDAO.getEncontrarProgramaLecCorporativo(periodoProceso);
			Integer iplide = new Integer(0);
			try {
				iplide = Integer.parseInt(result.get("indPosiLide").toString());
			}
			catch (Exception e) {
			}
			if (iplide.intValue() == 1) {
			
				Map criteriaVerificar = new HashMap();
				criteriaVerificar.put("codigoPais", codigoPais);
				criteriaVerificar.put("codigoLider", codigoLider);
				
				Integer verificar = mantenimientoLECProgramaCorporativoDAO.getLECPosibleLider(criteriaVerificar);
				if (verificar.intValue() == 0) 
				{
					
				   periodoProcesoAnterior = consultaPEJProgramaEjecutivasDAO.getObtienePeriodo(codigoPais, periodoProceso, -1);
				   oidPeriodoProcesoAnterior = mantenimientoLETLideresDAO.getOidPeriodoByCodigoPeriodo(periodoProcesoAnterior);
				   criteriaVerificar.put("oidPeriodoHasta", oidPeriodoProcesoAnterior);
				   
				   verificar = mantenimientoLETLideresDAO.getVerificarLiderSeccionHistoGeren(criteriaVerificar);
				   if (verificar != null) 
				   {					  
					   if (verificar.intValue() == 1 || verificar.intValue() == 2) 
					   {
						   //codigoRetorno = 4, IndicadorNombramiento = 4
						   resultado ="44;Lìder fue dada de Baja por Desempeño en última campaña, Desea Continuar?";
				      }
					   else
					   {
						   //codigoRetorno = 4, IndicadorNombramiento = 2
						   resultado = "42;";
				   }
				   }
				   else  
				   {
					   //codigoRetorno = 4, IndicadorNombramiento = 1
					   resultado = "43;Lìder no se encuentra en Lista de Posibles Lìderes, Desea Continuar?"; 
				   }
				   }
				else
				{
					resultado = "41;";
				}
			}
		}
		
		if(log.isDebugEnabled())
			log.debug(resultado);
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getEstadosConsultoraByReporte(java.lang.String)
	 */
	public LabelValue[] getEstadosConsultoraByReporte(String codigoReporte) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoReporte)) {
			
			Map criteria = new HashMap();
			criteria.put("codigoReporte", codigoReporte);

			try {
				List estados = interfazSiCCDAO
						.getEstadosConsultoraByReporte(criteria);
				if (estados != null && estados.size() > 0) {
					result = new LabelValue[estados.size()];

					// Creamos una primera opcin vaca
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < estados.size(); i++) {
						Base estado = (Base) estados.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(estado.getDescripcion(),
								estado.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}

		return result;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getZonasRegionPEJ(java.lang.String)
	 */
	public LabelValue[] getZonasRegionPEJ(String codigoRegion) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoRegion)) {
			Map criteria = new HashMap();

			criteria.put("codigoRegion", codigoRegion);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List regiones = reporteDAO.getZonasRegionPEJ(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getZonasRegionPEJTodos(java.lang.String)
	 */
	public LabelValue[] getZonasRegionPEJTodos(String codigoRegion, String codigoPeriodo) {
		LabelValue[] result = null;

		Map criteria = new HashMap();
		
		if (StringUtils.isNotBlank(codigoRegion)) 
			criteria.put("codigoRegion", codigoRegion);
		
		if (StringUtils.isNotBlank(codigoPeriodo)) {
			criteria.put("codigoPeriodo", codigoPeriodo);
			String oidPeriodo = String.valueOf(reporteDAO.getOidPeriodo(criteria));
			criteria.put("oidPeriodo", oidPeriodo);
		}	
		
		log.debug("LA CRITERIA : " + criteria);
		try {
			List regiones = reporteDAO.getZonasRegionPEJ(criteria);
			if (regiones != null && regiones.size() > 0) {
				result = new LabelValue[regiones.size()];
				for (int i = 0; i < regiones.size(); i++) {
					Base region = (Base) regiones.get(i);
					// Construimos la descripcion
					LabelValue lv = new LabelValue(region.getDescripcion(),
							region.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getSeccionesZonaPEJ(java.lang.String)
	 */
	public LabelValue[] getSeccionesZonaPEJ(String codigoZona) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoZona)) {
			Map criteria = new HashMap();

			criteria.put("codigoZona", codigoZona);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List secciones = reporteDAO.getSeccionesZonaPEJ(criteria);
				if (secciones != null && secciones.size() > 0) {
					result = new LabelValue[secciones.size()];
					for (int i = 0; i < secciones.size(); i++) {
						Base seccion = (Base) secciones.get(i);
						// Construimos la descripcion
						LabelValue lv = new LabelValue(
								seccion.getDescripcion(), seccion.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opcin vaca
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getSeccionesZonaPEJTodos(java.lang.String)
	 */
	public LabelValue[] getSeccionesZonaPEJTodos(String codigoRegion, String codigoZona) {
		LabelValue[] result = null;
	
		Map criteria = new HashMap();
		if (StringUtils.isNotBlank(codigoRegion)) 
			criteria.put("codigoRegion", codigoRegion);
		if (StringUtils.isNotBlank(codigoZona)) 
			criteria.put("codigoZona", codigoZona);
		log.debug("LA CRITERIA : " + criteria);
		try {
			List secciones = reporteDAO.getSeccionesZonaPEJTodos(criteria);
			if (secciones != null && secciones.size() > 0) {
				result = new LabelValue[secciones.size()];
				for (int i = 0; i < secciones.size(); i++) {
					Base seccion = (Base) secciones.get(i);
					// Construimos la descripcion
					LabelValue lv = new LabelValue(
							seccion.getDescripcion(), seccion.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarCorreoLider(java.lang.String, java.lang.String)
	 */
	public String getValidarCorreoLider(String codigoLider, String codigoPais) {
		String resultado = "";
		String oidCliente = "";
		int existeCorreo = 0;
		int indicadorProgramaLet = mantenimientoLETLideresDAO.getIndicadorProgramaLet(codigoPais);
		
		oidCliente = mantenimientoLETLideresDAO.getOidClienteByCodigoCliente(codigoLider);
		existeCorreo = mantenimientoLETLideresDAO.getIndicadorClienteCorreo(oidCliente);
		
		if(indicadorProgramaLet == 3){
		if(existeCorreo <= 0){
			resultado = "Lder no tiene correo Asignado. Solicitar su ingreso a SAC";
		}else{
			resultado = "1";
		}
		
		}else
			resultado = "1";
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validaProgramaTramoCampanaCerrada(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validaProgramaTramoCampanaCerrada(String codigoPeriodo, String tipoCarga, String codigoPais) {
		String mensaje = null;
		int campanaCerrada = 0;
		Map mapConcursoTramo = new HashMap();
		
		//INI Obtener Indicador de Validar Carga Objetivo
		String indValidaCargaObjetivo = null;
		
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema("LET");
		parametroPais.setNombreParametro("IndValidaCierreCampania");
		
		List lstParametros = genericoDAO.getParametrosPais(parametroPais);
		
		if(lstParametros != null && lstParametros.size() > 0){
			ParametroPais ps = (ParametroPais)lstParametros.get(0);
			indValidaCargaObjetivo = ps.getValorParametro();
		}
		//FIN Obtener Indicador de Validar Carga Objetivo
				
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		
		if(StringUtils.equals(indValidaCargaObjetivo, Constants.NUMERO_UNO)){
			campanaCerrada = mantenimientoLETLideresDAO.getCampanaCerrada(codigoPeriodo);
			
			if(campanaCerrada == 0){
				if(StringUtils.equals(tipoCarga, Constants.NUMERO_UNO)){
		mapConcursoTramo = mantenimientoLETLideresDAO.getConcursoTramoPrograma(criteria);
		
		if(mapConcursoTramo != null){
				mensaje = null;
			}else {
						mensaje = "No existe Tramo registrado para la campaa seleccionada";
					}
				}else{
					mensaje = null;
				}
			}else {
				mensaje = "No se permite la carga, Campaa est cerrada";
			}
		}

		return mensaje;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getLongitudDefaultTipoDocumento(java.lang.String)
	 */
	public String getLongitudDefaultTipoDocumento(String oidPais) {

		return mantenimientoSGRGenericoDAO.getLongitudDefaultTipoDocumento(oidPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getNombreConsultoraOrdenCompraSTO(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getNombreConsultoraOrdenCompraSTO(String codigoPais, String codigoCliente, String oidIdioma,
			String numeroDocumentoIdentidad, String indicadorValidaDocIdentidad) {
		
		String resultadoNombre = "";
		
		Map criterios = new HashMap();
		criterios.put("codigoPais", codigoPais);
		criterios.put("oidIdioma", oidIdioma);
		
		List resultado = new ArrayList();
		
		if(StringUtils.equals(indicadorValidaDocIdentidad, Constants.SI)){
			criterios.put("codigoCliente", codigoCliente);
			criterios.put("numeroDocIdentidad", "");
			
			resultado = consultaHIPDatosClienteDAO.getClientesByCriteria(criterios);
			
			if(resultado == null || resultado.size() < 1){
				criterios.put("codigoCliente", "");
				criterios.put("numeroDocIdentidad", numeroDocumentoIdentidad);
				
				resultado = consultaHIPDatosClienteDAO.getClientesByCriteria(criterios);
			}
		}else{
			criterios.put("codigoCliente", codigoCliente);
			criterios.put("numeroDocIdentidad", "");
			
			resultado = consultaHIPDatosClienteDAO.getClientesByCriteria(criterios);
		}		
		
		boolean esClienteUnico = true;
		String codigoClienteObtenido = "";
		
		if(resultado != null && resultado.size() > 0){
			Map mapCliente = (Map)resultado.get(0);
			codigoClienteObtenido = (String)mapCliente.get("codigoCliente");
		}
		
		/*if(resultado.size() > 1) {
			mapCliente = (Map)resultado.get(0);
			codigoClienteObtenido = (String)mapCliente.get("codigoCliente");
			
			for(int i = 1; i < resultado.size(); i ++) {
				mapCliente = (Map)resultado.get(i);
				String codigoClienteAux = (String)mapCliente.get("codigoCliente");
				
				if(!codigoClienteObtenido.equalsIgnoreCase(codigoClienteAux)) {
					esClienteUnico = false;
					break;
				}
			}
		}*/
		
		Map criteria = new HashMap();
		criteria.put("codCliente", codigoClienteObtenido);
		String nombreConsultora = procesoSTOEjecucionValidacionesDAO.getNombreConsultora(criteria);
		
		if(!StringUtils.isBlank(nombreConsultora)){
			resultadoNombre = nombreConsultora + "-" + codigoClienteObtenido;
		}else{
			resultadoNombre = "";
		}
		
		return resultadoNombre;
	}
	
	public String getDiferenciaPeriodos(String codigoPais, String periodoInicio, String periodoFin) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodoInicio", periodoInicio);
		criteria.put("codigoPeriodoFin", periodoFin);
		Integer diferencia = reporteService.getDiferenciaPeriodos(criteria);     
		return diferencia.toString();
	}
	
	 public String getCodigoNumeroTipoDocumentoConsultoraPorCodigo(String codigoCliente) {

			String result = new String();

			Map criteria = new HashMap();

			criteria.put("codigoCliente", codigoCliente);

			 try {
				 if(procesoSTOEjecucionValidacionesDAO.getCodigoNumeroTipoDocumentoConsultoraPorCodigo(criteria)!=null){
					 result = procesoSTOEjecucionValidacionesDAO.getCodigoNumeroTipoDocumentoConsultoraPorCodigo(criteria);
				 }else{
					 result=null;
				 }
				
			} catch (DataAccessException ignore) {

			}
			return result;
		} 
		 
		 public String getCodigoNumeroTipoDocumentoConsultoraPorDocumento(String numeroDocumentoIdentidad) {

			 String result = new String();

				Map criteria = new HashMap();

				criteria.put("numeroDocumentoIdentidad", numeroDocumentoIdentidad);

				 try {
					 if(procesoSTOEjecucionValidacionesDAO.getCodigoNumeroTipoDocumentoConsultoraPorDocumento(criteria)!=null){
						 result = procesoSTOEjecucionValidacionesDAO.getCodigoNumeroTipoDocumentoConsultoraPorDocumento(criteria);
					 }else{
						 result=null;
					 }
					
				} catch (DataAccessException ignore) {

				}
				return result;
			}
		 
		 
		 
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.service.AjaxService#getloadRegionesDisponibles2(java.lang.String, java.lang.String)
		 */
		public LabelValue[] getloadRegionesDisponibles2(String codigoPais, String regionesSeleccionadas) {
				log.debug("Entro getloadRegionesDisponibles2");
				log.debug("codigoPais: " + codigoPais);
				log.debug("regionesSeleccionadas: " + regionesSeleccionadas);
				
				LabelValue[] result = null;
				LabelValue[] resultReturn = null;

				String[] seleccionadas = {};
				int indice = 0;
				try {
					LabelValue[] regiones = this.getRegionByPaisMarcaCanalSubGerencia(codigoPais, 
							Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, "01");
		
					if (!StringUtils.isBlank(regionesSeleccionadas)) {
						seleccionadas = StringUtils.split(regionesSeleccionadas, "|");
				    }
					result = new LabelValue[regiones.length];
					for (int i = 0; i < regiones.length; i++) {
						LabelValue regionLabel = regiones[i];
						Base region = new Base();
						region.setCodigo(regionLabel.getValue());
						region.setDescripcion(regionLabel.getLabel());
						if (!verificarSeleccionRegion(seleccionadas, region)) {
							LabelValue lv = new LabelValue(region.getDescripcion(), region.getCodigo());
							result[indice] = lv;
							indice++;
						}
					}

					if (result != null) {
						resultReturn = new LabelValue[indice];
						System.arraycopy(result, 0, resultReturn, 0, indice);
					}
		
				} catch (DataAccessException ignore) {
					log.warn(ignore.getMessage());
				}
				
				return resultReturn;
		}
		
		
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.service.AjaxService#getloadZonasDisponibles2(java.lang.String, java.lang.String[], java.lang.String, java.lang.String)
		 */
		public LabelValue[] getloadZonasDisponibles2(
				final String codigoPais, final String[] codigoRegiones, 
				final String zonasSeleccionadas,
				String condicionTodos) {
			
			log.debug("Entro getloadZonasDisponibles2");
			log.debug("codigoPais: " + codigoPais);
			log.debug("ZonasSeleccionadas: " + zonasSeleccionadas);
			
			LabelValue[] result = null;
			LabelValue[] resultReturn = null;
			
			String[] seleccionadas = {};
			int indice = 0;
			try {
				LabelValue[] Zonas = this.getZonasMultipleByPaisMarcaCanalRegion(codigoPais, Constants.CODIGO_MARCA_DEFAULT, 
						Constants.CODIGO_CANAL_DEFAULT, codigoRegiones, condicionTodos);
	
				if (!StringUtils.isBlank(zonasSeleccionadas)) {
					seleccionadas = StringUtils.split(zonasSeleccionadas, "|");
			    }
				result = new LabelValue[Zonas.length];
				for (int i = 0; i < Zonas.length; i++) {
					LabelValue zonaLabel = Zonas[i];
					Base zonaBase = new Base();
					zonaBase.setCodigo(zonaLabel.getValue());
					zonaBase.setDescripcion(zonaLabel.getLabel());
					if (!verificarSeleccionRegion(seleccionadas, zonaBase)) {
						LabelValue lv = new LabelValue(zonaBase.getDescripcion(), zonaBase.getCodigo());
						result[indice] = lv;
						indice++;
					}
				}

				if (result != null) {
					resultReturn = new LabelValue[indice];
					System.arraycopy(result, 0, resultReturn, 0, indice);
				}
	
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}

			return resultReturn;
					
		}
		
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * biz.belcorp.ssicc.service.AjaxService#getPeriodoFechaAndCampanyaActivaCad()
		 */
		public String getPeriodoFechaAndCampanyaActivaCad() {
			String result = null;
			Map datos = interfazSiCCDAO.getPeriodoFechaCampanyaActiva();

			if (datos != null) {
				result = MapUtils.getString(datos, "periodo", "").concat(",").concat(
						MapUtils.getString(datos, "fecha", ""));
			} else {
				result = "";
			}
			return result;
		}
		 
			/* (non-Javadoc)
			 * @see biz.belcorp.ssicc.service.AjaxService#getTipoOrigenBanco(java.lang.String)
			 */
		public LabelValue[]  getTipoOrigenBanco(String oidBanco){
				LabelValue[] result = null;

				Map criteria = new HashMap();
				
				if (StringUtils.isNotBlank(oidBanco)) 
					criteria.put("codBanco", oidBanco);
				log.debug("LA CRITERIA : " + criteria);
				try {
					List tipoOrigenes = consultaCCCGenericoDAO.getTipoOrigenBanco(criteria);
					if (tipoOrigenes != null && tipoOrigenes.size() > 0) {
						result = new LabelValue[tipoOrigenes.size()];
						for (int i = 0; i < tipoOrigenes.size(); i++) {
							Base tipoOrigen = (Base) tipoOrigenes.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(tipoOrigen.getDescripcion(),
									tipoOrigen.getCodigo());
							result[i] = lv;
						}
					} else {
						// Creamos una primera opcin vaca
						result = new LabelValue[1];
						result[0] = new LabelValue("", "");
					}
				} catch (DataAccessException ignore) {
					log.warn(ignore.getMessage());
				}
				
				return result;
			}			
		 

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getPromedioVenta(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getPromedioVenta(String codigoPais, String codigoMarca, String codigoCanal, String codigoRegion, String codigoCliente, String numeroPedido) {
		String resultado = "";
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("numeroPedidos", numeroPedido);
		
		resultado = consultaHIPDatosClienteDAO.getPromedioVentasxNumeroPedidos(criteria);
		
		return resultado;
	}
	
	public String getParametroMostrarFechas(String codigoPais) {
		String mostrarFechas = null;
		
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema("ZON");
		parametroPais.setNombreParametro("indDirectorioCorpo");
		parametroPais.setIndicadorActivo(Constants.UNO);
		
		List lstParametros = genericoDAO.getParametrosPais(parametroPais);
		
		if(lstParametros != null && lstParametros.size() > 0){
			ParametroPais ps = (ParametroPais)lstParametros.get(0);
			mostrarFechas = ps.getValorParametro();
		}
		
		return mostrarFechas;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getConsultoraCuponExistenteByCriteria(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getPedidoCuponExistenteByCriteria(final String codigoPais,
			final String periodo, final String codigoConsultora,
			final String fechaFacturacion, final String valorPagado) {
		String result = "";
		try {
			Map criteria = new HashMap();
			// String descripcion = "";
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", periodo);
			criteria.put("codigoConsultora", codigoConsultora);
			criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_CUP);
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("valorPagado", valorPagado);
			result = procesoSTODAO.getPedidoCuponExistenteByCriteria(criteria);
			log.debug("__AJAX ::: getPedidoCuponExistenteByCriteria = " + result);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#buscarCUV(java.lang.String, java.lang.String)
	 */
	public String buscarCUV(String codigoCUV, String codigoPeriodo) {
		String resultado = "";
		
		if (StringUtils.isNotBlank(codigoCUV)) {
			Map criteria = new HashMap();
			criteria.put("codigoCUV", codigoCUV);
			criteria.put("codigoPeriodo", codigoPeriodo);
			
			resultado = mantenimientoLETProgramaCorporativoDAO.getBuscarCUV(criteria);
		}else{
			resultado = null;
		}
		
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getExisteZonaUA(java.lang.String)
	 */
	public String getExisteZonaUA(String codigoZona) {
		String resultado = "";
		
		boolean existe = mantenimientoMAVConfiguracionDAO.existeZona(codigoZona);
		
		if(existe){
			resultado = "1";
		}else{
			resultado = "0";
		}
		
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.GenericoService#getPeriodoNSiguiente(Map)
	 */
	public String getPeriodoNSiguiente(String codigoPais, String codigoPeriodo, String numeroPeriodo) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("numeroPeriodo", numeroPeriodo);
		return genericoDAO.getPeriodoNSiguiente(criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.GenericoService#getPeriodoByFecha(Map)
	 */
	public String getPeriodoByFecha(String codigoPais, String codigoConexionExterna, String fecha) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoConexionExterna", codigoConexionExterna);
		criteria.put("fecha", fecha);
		return genericoDAO.getPeriodoByFecha(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getEstructGeopoByUA(java.lang.String,java.lang.String)
	 */
	public String getEstructGeopoByUA(String codigoPais, String unidadAdmin) {
		String result = procesoSTOEjecucionValidacionesDAO.getEstructGeopoByUA(codigoPais, unidadAdmin);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getVigenciaCuponPorNivel(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getVigenciaCuponPorNivel(String codigoPais, String codigoPrograma, String codigoNivel) {
		return mantenimientoDAO.getVigenciaCuponPorNivel(codigoPais, codigoPrograma, codigoNivel);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getFechaCronoFase2(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getFechaCronoFase2(String codigoPais, String codigoPeriodo, String codigoActiv, String codigoZona){
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoActiv", codigoActiv);
		criteria.put("codigoZona", codigoZona);
		return mantenimientoCRACronogramaFase1DAO.getFechaCronoFase2(criteria);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTramos(java.lang.String, java.lang.String)
	 */
	public LabelValue[] getTramos(String codigoPais, String codigoPrograma){
		LabelValue[] result = null;
	
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", codigoPrograma);
		List list = mantenimientoLETProgramaCorporativoDAO.getTramosLET(criteria);
		
		if (list != null && list.size() > 0) {
			result = new LabelValue[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Tramos tramo = (Tramos) list.get(i);
				LabelValue lv = new LabelValue(tramo.getPeriodoInicioTramo(),
						tramo.getPeriodoInicioTramo());
				result[i] = lv;
			}
		} else {
			// Creamos una primera opcin vaca
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}
		
		return result;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getCodigoBaseComision(java.lang.String)
	 */
	public String getCodigoBaseComision(String codigoComision) {
		String result = null;

		if (StringUtils.isNotBlank(codigoComision)) {
			Map criteria = new HashMap();
			criteria.put("codigoComision", codigoComision);

			try {
				result = mantenimientoCOMComisionDAO.getCodigoBaseComision(criteria);				

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		} 
		return result;
	}
	
	public String getValidarFechaIngreso(String tipoCargo,String codigoPais, String fechaIngreso,String codigoRegion,String codigoZona,String conExterna) {
		String valid=StringUtils.EMPTY;
			
			Map criteria = new HashMap();
			criteria.put("tipoCargo", (StringUtils.isBlank(tipoCargo) ? StringUtils.EMPTY : tipoCargo));
			criteria.put("codigoPais", (StringUtils.isBlank(codigoPais) ? StringUtils.EMPTY : codigoPais));
			criteria.put("fechaIngreso", (StringUtils.isBlank(fechaIngreso) ? StringUtils.EMPTY : fechaIngreso));
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", (StringUtils.isBlank(codigoZona) ? null : codigoZona));
			
		
		if (conExterna.equals(Constants.CONEXION_EXTERNA_FOX)) {
			valid = mantenimientoZONDirectorioDAO.getValidarFechaIngresoFOX(criteria);
		}else{	
			valid = mantenimientoZONDirectorioDAO.getValidarFechaIngreso(criteria);
		}

		return valid;
	}

	
	public String getValidarCruceFechaGeren(String codigoPais,
			String fechaIngresoInicio, String fechaIngresoFin,
			String codigoRegion, String codigoZona,String tipoCargo, String tipoOperacion,String conExterna) {
		
		String valid=StringUtils.EMPTY;
		
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("fechaIngresoInicio",  fechaIngresoInicio);
			criteria.put("fechaIngresoFin", (StringUtils.isBlank(fechaIngresoFin) ? null : fechaIngresoFin));
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", (StringUtils.isBlank(codigoZona) ? null : codigoZona));
			criteria.put("tipoCargo", tipoCargo);
			criteria.put("tipoOperacion", (StringUtils.isBlank(tipoOperacion) ? "OP" : tipoOperacion));
			
			valid = mantenimientoZONDirectorioDAO.getValidarCruceFechaGeren(criteria);
	

		return valid;
	}

	public LabelValue[] getCargosByFlagFuturas(String flagFuturas) {

		if(log.isDebugEnabled())
			log.debug("flagFuturas: " + flagFuturas);
		
		LabelValue []result = null;
		
		Map criteria = new HashMap();
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		
		if(StringUtils.equals(flagFuturas, Constants.NUMERO_UNO)){
			criteria.put("indCargoFuturo", Constants.NUMERO_UNO); // carga solo futuros
		}else if(StringUtils.equals(flagFuturas, Constants.NUMERO_CERO)){
			criteria.put("indCargoNoFuturo", Constants.NUMERO_UNO); //Carga solo los no futuros - Cargos Base
		}else{
			criteria.put("indCargoFuturo", null); // en null para que traiga todos lo cargos
			criteria.put("indCargoNoFuturo", null); // en null para que traiga todos lo cargos
		}
		
		List cargosList = mantenimientoZONDirectorioDAO.getTipoCargo(criteria);
			
		if (cargosList != null && cargosList.size() > 0) {
			result = new LabelValue[cargosList.size()];
			for (int i = 0; i < cargosList.size(); i++) {
				Map cargo = (Map) cargosList.get(i);
				LabelValue lv = new LabelValue(MapUtils.getString(cargo, "descripcion", ""), MapUtils.getString(cargo, "codigoCargo", ""));
				result[i] = lv;
			}
		}
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRolPerfil(java.lang.
	 * String)
	 */
	public String getRolPerfil(String codigoCargo) {
		List lista = null;
		Map criteria = new HashMap();

		criteria.put("codigoCargo", codigoCargo);

		lista = mantenimientoZONDirectorioDAO.mostrarRolPerfil(criteria);
		
		String result = "";
		String rol = "";
		String perfil = "";
		if(lista != null && lista.size() == 1){
			for(int i=0; i<lista.size();i++){
				Map map = (Map) lista.get(i);
				rol = (String) map.get("rol");
				perfil = (String) map.get("perfil");
				break;
			}
			
			result = rol.concat("|").concat(perfil);
		
		}else{
			rol="No existe";
			perfil="No existe";
			result = rol.concat("|").concat(perfil);
		}
			
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getDescripcionMensaje(java.lang.String, java.lang.String)
	 */
	public String getDescripcionMensaje(String codigoPais, String codigoMensaje) {
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("codigoMensaje", codigoMensaje);
		
		String descripcion = mantenimientoMENGenericoDAO.getDescripcionMensaje(map);
		
		return descripcion;
	}

	public LabelValue[] getTipoDesempenio() {
		
		if(log.isDebugEnabled())
			log.debug("getTipoDesempenio");
		
		LabelValue []result = null;

		List tipoDesemList = mantenimientoLECProgramaCorporativoDAO.getTipoDesempenioList(null);
			
		if (tipoDesemList != null && tipoDesemList.size() > 0) {
			result = new LabelValue[tipoDesemList.size()];
			for (int i = 0; i < tipoDesemList.size(); i++) {
				Base tipoDesem = (Base) tipoDesemList.get(i);
				LabelValue lv = new LabelValue(tipoDesem.getDescripcion(), tipoDesem.getCodigo());
				result[i] = lv;
			}
		}
		
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getRegionesACerrar(java.lang.String)
	 */
	public LabelValue[] getRegionesACerrar(String codigoPais) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			

			try {
				List regiones = null;
				regiones = interfazSiCCDAO.getRegionesACerrar(criteria);
								
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
 	                for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	public LabelValue[] getTipoPago(String codigo) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigo)) {
			Map criteria = new HashMap();
			criteria.put("indPago", codigo);
			

		try {
				List tipoPagos = null;
				if(codigo!=null && codigo.compareTo("1")==0)
				tipoPagos =procesoLECGenerarPagosDAO.getTipoPago(criteria);
				if(codigo!=null && codigo.compareTo("0")==0){
					criteria.put("indGrup", "A");
					tipoPagos =procesoLECCargaDatosExcelDAO.getTipoCarga(criteria);				
				}
				if (tipoPagos != null && tipoPagos.size() > 0) {
					result = new LabelValue[tipoPagos.size()];
 	                for (int i = 0; i < tipoPagos.size(); i++) {
						Base tipopago = (Base) tipoPagos.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(tipopago.getDescripcion(),
								tipopago.getCodigo());
						result[i] = lv;
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}
	public LabelValue[] getTipoGanancia(String codigo) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigo)) {
			Map criteria = new HashMap();
			criteria.put("codigo", codigo);
			

			try {
				List tiposGanancias = null;
				tiposGanancias =procesoLECGenerarPagosDAO.getTipoGanancia(criteria);
								
				if (tiposGanancias != null && tiposGanancias.size() > 0) {
					result = new LabelValue[tiposGanancias.size()];
 	                for (int i = 0; i < tiposGanancias.size(); i++) {
						Base tipogana = (Base) tiposGanancias.get(i);
						// Construimos la descripcion
				
						LabelValue lv = new LabelValue(tipogana.getDescripcion(),
								tipogana.getCodigo());
						result[i] = lv;
				}
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarRegularizacion(java.lang.String, java.lang.String)
	 */
	public Integer getValidarRegularizacion(String codigoCliente, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("codigoUsuario", codigoUsuario);
		return mantenimientoMAEClienteDAO.getValidarRegularizacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#updateRegularizacion(java.lang.String, java.lang.Integer)
	 */
	public String updateRegularizacion(String codigoCliente, Integer oidBloqueo) {
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("oidBloqueo", oidBloqueo);
		criteria.put("codigoRetorno", "");
		mantenimientoMAEClienteDAO.updateRegularizacion(criteria);
		
		return MapUtils.getString(criteria, "codigoRetorno");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validarNumeroRucModulo11(java.lang.String)
	 */
	public Integer validarNumeroRucModulo11(String numeroDocumentoIdentidad) {
		return mantenimientoMAEClienteDAO.validarNumeroRucModulo11(numeroDocumentoIdentidad);
	}
	/*
 * (non-Javadoc)
 * 
 * @see
 * biz.belcorp.ssicc.service.AjaxService#getClasificacionesByCriteriaMultipleOID2
 * (java.lang.String, java.lang.String, java.util.ArrayList,
 * java.util.ArrayList)
 */
	public LabelValue[] getClasificacionesByCriteriaMultipleOID2(
			String codigoIdiomaISO, String codigoTipoCliente,
			ArrayList codigoSubTipoCliente, ArrayList codigoTipoClasificacion) {

		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();

			if (codigoTipoClasificacion.get(0).equals("T")) {

				criteria.put("codigoTipoClasificacion", new ArrayList());
				criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
				criteria.put("codigoTipoCliente", codigoTipoCliente);
				criteria.put("codigoISO", codigoIdiomaISO);
			}else{

			criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
			criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			criteria.put("codigoISO", codigoIdiomaISO);
			}

			List tiposClasificaciones = interfazSiCCDAO
					.getClasificacionesByCriteriaMultipleOID(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size() + 1];
				result[0] = new LabelValue("Todos","T");
				
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					BaseOID clasificacion = (BaseOID) tiposClasificaciones
							.get(i);
					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(), clasificacion
									.getOid().toString());
					result[i+1] = lv;
	                
				}
			} else {
			// Creamos una primera opcin vaca
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}
		
	} catch (DataAccessException ignore) {
		log.warn(ignore.getMessage());
	}
	return result;
}
	
	public String getExisteOidDetaOferta(String codigoCUV, String codigoPeriodo) {
	
		String flagOidDetaOfer = new String();
		Map criteria = new HashMap();
	
		criteria.put("codigoCUV", codigoCUV);
		criteria.put("codigoPeriodo", codigoPeriodo);
	
		try {
	
			if (procesoSTOEjecucionValidacionesDAO
					.getExisteOidDetaOferta(criteria) != null) {
				flagOidDetaOfer = Constants.SI;
			} else {
				flagOidDetaOfer = null;
			}
		} catch (DataAccessException ignore) {
	
		}
	
		return flagOidDetaOfer;
	}
	
   /* (non-Javadoc)
 * @see biz.belcorp.ssicc.service.AjaxService#getDevuelveProductoRecepcionCDR(java.lang.String)
 */
public ProductoAgregacion getDevuelveProductoRecepcionCDR (String codigoSAP) {
		ProductoAgregacion result = null;
		Map criteria = new HashMap();
		criteria.put("codigoSAP", codigoSAP);
		log.debug("Ejecutando getDevuelveProductoRecepcionCDR");

		try {
			result = mantenimientoRECDigitacionCDRDAO.getDevuelveProductoRecepcionCDR(criteria);	
			if (result == null) {
				result = new ProductoAgregacion();
				result.setCodigoProducto("-1");
			}
		} 
		catch (Exception e) {
			log.debug("ajax Error getDevuelveProductoRecepcionCDR " + result);
		}
		return result;
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.AjaxService#getValidarDATFechaFacturacion(java.lang.String)
     */
    public String getValidarDATFechaFacturacion(String codigpPeriodo) {
    	String retorno = Constants.SI;
    	try {
    		Map criteriaProgramacionCierre = new HashMap();
			criteriaProgramacionCierre.put("tipoCierre", Constants.LET_TIPO_CIERRE_CAMPANHA);
			criteriaProgramacionCierre.put("estadoCierre", Constants.DAT_ESTADO_CIERRE); 
			criteriaProgramacionCierre.put("campanhaProceso", codigpPeriodo); 
			List listCierreCampanha = mantenimientoFACGenericoDAO.getCierreFacturacion(criteriaProgramacionCierre);
			if (listCierreCampanha != null && listCierreCampanha.size() > 0)
				retorno = Constants.NO;
    		
    	}
    	catch (Exception e) {
			log.debug("ajax Error getValidarDATFechaFacturacion " + e.getMessage());
		}
    	return retorno;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#executeProcesoAnulacionBR(java.lang.String)
	 */
	public Integer executeProcesoAnulacionBR(String usuario, 
			 List listTablaTemporal, Integer totalIncorrectas, 
			 String numeroLote
			 ) {
		
		//List listTablaTemporal = (List)session.getAttribute(Constants.REC_ANULA_MASIVA_BOLETA_RECOJO_LIST);
		Integer resultado = null;
		
		if(listTablaTemporal.size() > 0){
			//Integer totalIncorrectas = (Integer)session.getAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_INCORRECTAS);
			if(totalIncorrectas.intValue() > 0){
				resultado = new Integer("1");
			}else{
				
				log.debug("Inicio Anulando Boleta de Recojo listTablaTemporal.size()");
				procesoRECCierreBRDAO.deleteTablaTemporal();
				
			    for(int i=0; i<listTablaTemporal.size(); i++){
			    	Map map = (HashMap)listTablaTemporal.get(i);
			    		procesoRECCierreBRDAO.insertProcesoBoletaRecojoTemporal(map);
			    	}
				
				numeroLote = procesoRECCierreBRDAO.getNumeroLoteBoletasRecojo();
				Map criteria = new HashMap();
				criteria.put("numeroLote", numeroLote);
				criteria.put("codigoResultadoBR", "NE");
				criteria.put("usuarioLogin", usuario);
				procesoRECCierreBRDAO.executeValidarProcesoBoleta(criteria);
				
				for(int i=0;i<listTablaTemporal.size();i++){
					HashMap map = (HashMap)listTablaTemporal.get(i);
					map.put("flag", "1");
					map.put("observacion", "OK");
				}
				
				//session.setAttribute("numeroLote", numeroLote);
				//session.setAttribute(Constants.REC_ANULA_MASIVA_BOLETA_RECOJO_LIST, listTablaTemporal);
	    	    
				//session.setAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD, listTablaTemporal.size());
	    	    //session.setAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_INCORRECTAS, new Integer(0));
	    	    //session.setAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_CORRECTAS, listTablaTemporal.size());
			    resultado = new Integer("2");
			    log.debug("Fin Anulando Boleta de Recojo ");
			}
		}else{
			resultado = new Integer("3");
		}
		
		return resultado;
	}
	
	public LabelValue[] getRegionByTipoGrupoRegion(String codigoPais,
			String codigoTipoGrupoRegion) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
            criteria.put("codigoPais", codigoPais);
            criteria.put("codigoTipoGroup", codigoTipoGrupoRegion);
			List tiposClasificaciones = mantenimientoLECProgramaCorporativoDAO.getRegionByTipoGrupoRegion(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base clasificacion = (Base) tiposClasificaciones
							.get(i);
					LabelValue lv = new LabelValue(clasificacion.getDescripcion(), clasificacion.getCodigo());
					result[i] = lv;
	                
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
			
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
	  return result;	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getLoadTipoBonoLanzamiento(java.lang.String, java.lang.String)
	 */
	public LabelValue[] getLoadTipoBonoLanzamiento(String codigoPais, String codigoTipoMedicion) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
            criteria.put("codigoPais", codigoPais);
            criteria.put("codigoTipoMedicion", codigoTipoMedicion);
			List tiposClasificaciones = mantenimientoLECProgramaCorporativoDAO.getTipoBonoLanzamiento(criteria);
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base clasificacion = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(clasificacion.getDescripcion(), clasificacion.getCodigo());
					result[i] = lv;
	                
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
			
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
	   }
		
	   return result;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getPeriodosDefaultByPMCF(java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getPeriodosDefaultByPMCF(String codigoPais, String codigoMarca, String codigoCanal) {
		Map criteria = new HashMap();
		LabelValue[] result = null;

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		List listaPeriodos = interfazSiCCDAO.getPeriodosDefaultByPMCF(criteria);

		try {
			if (listaPeriodos != null && listaPeriodos.size() > 0) {

				result = new LabelValue[listaPeriodos.size()];

				for (int i = 0; i < listaPeriodos.size(); i++) {
					Base periodo = (Base) listaPeriodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(),
							periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
		return result;
	}
	
		/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarCampanhiaActiva(java.lang.String, java.lang.String)
	 */
	public String getValidarCampanhiaActiva(String codigoPais, String codigoPeriodoReferencia) {
		log.info("Entro a AjaxServiceImpl - getValidarCampanhiaActiva(java.lang.String, java.lang.String)");

		// -- Variables
		String resultado = "";
		String codigoProcesoActual = "";

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);
		criteria.put("indicadorActiva", Constants.NUMERO_UNO);

		codigoProcesoActual = mantenimientoRECIngresoAtencionesDAO.getObtenerCampahniaActiva(criteria);

		if (codigoProcesoActual != null || codigoProcesoActual != "") {
			if (Integer.parseInt(codigoPeriodoReferencia) > Integer.parseInt(codigoProcesoActual)) {
				resultado = "1";
			} else {
				resultado = "0";
			}
		} else {
			resultado = "0";
		}

		log.info("Salio a AjaxServiceImpl - getValidarCampanhiaActiva(java.lang.String, java.lang.String) - Resultado:" + resultado);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarEnvioValidacionesGP3(java.lang.String)
	 */
	public String getValidarEnvioValidacionesGP3(String codigoPais) {
		BASParametroPais baspp = new BASParametroPais();
		baspp.setCodigoPais(codigoPais);
		baspp.setCodigoSistema(Constants.STO_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.STO_CODIGO_PARAMETRO_IND_ENVIO_VALIDACIONES);
		List list = mantenimientoBASParametroPaisDAO.getParametroPais(baspp);
		BASParametroPais baspp1 = (BASParametroPais) list.get(0);
		
		if(StringUtils.equalsIgnoreCase(baspp1.getValorParametro(), Constants.NUMERO_UNO)){
			Integer indEnvValid = procesoPEDEjecutarGPDAO.getIndicadorEnvioValidaciones();
			if(indEnvValid.intValue() > 0){
				return "1";
			}else{
				return "0";
			}
		}else{
			return "0";
		}
	}
	
	public String getGeneraRFCFiscal(String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento) {
		log.info("Entro a AjaxServiceImpl - getGeneraRFCFiscal(java.lang.String, java.lang.String)");

		// -- Variables
		String generado = "";
		
		//inicio algoritmo
		if(fechaNacimiento!=null && StringUtils.isNotBlank(fechaNacimiento)&& primerNombre!=null && StringUtils.isNotBlank(primerNombre) && apellidoPaterno!=null && StringUtils.isNotBlank(apellidoPaterno))
		{
			
			//El nombre completo quedara formado por lo ingresado en los campos primer nombre y segundo nombre
			primerNombre = primerNombre+" "+segundoNombre;
			
			if(esCompuesto(primerNombre)){
				String nombre= primerNombre.split(" ")[0];
				if(!esReservada(primerNombre.split(" ")[0])){
					String nombres[] = {Constants.MAE_CLIENTE_COMPUESTO_MARIA,Constants.MAE_CLIENTE_COMPUESTO_MA,Constants.MAE_CLIENTE_COMPUESTO_MA_PUNTO, Constants.MAE_CLIENTE_COMPUESTO_JOSE, Constants.MAE_CLIENTE_COMPUESTO_J, Constants.MAE_CLIENTE_COMPUESTO_J_PUNTO};
					boolean flag=false;
					for (int i=0;i<nombres.length;i++){
						if(nombres[i].equalsIgnoreCase(nombre))
							flag=true;
							break;
					}
					if(flag)
						primerNombre = primerNombre.split(" ")[1];
					else
						primerNombre = nombre;
				}
				else
					primerNombre = primerNombre.split(" ")[1];
			}
			
			if(StringUtils.isNotBlank(segundoNombre)&&esCompuesto(segundoNombre)){
				if(!esReservada(segundoNombre.split(" ")[0]))
					segundoNombre = segundoNombre.split(" ")[0];
				else
					segundoNombre = segundoNombre.split(" ")[1];
			}
			
			if(esCompuesto(apellidoPaterno)){
				if(!esReservada(apellidoPaterno.split(" ")[0]))
					apellidoPaterno = apellidoPaterno.split(" ")[0];
				else
					apellidoPaterno = apellidoPaterno.split(" ")[1];
			}
			
			if(StringUtils.isNotBlank(apellidoMaterno)&&esCompuesto(apellidoMaterno)){
				if(!esReservada(apellidoMaterno.split(" ")[0]))
					apellidoMaterno = apellidoMaterno.split(" ")[0];
				else
					apellidoMaterno = apellidoMaterno.split(" ")[1];
			}
			
			//validamos si es compuesto
			
			
			//validacion
			//1.	Si la letra inicial de alguno de los apellidos es Ñ, el sistema le asignará una "X" en su lugar
			if(apellidoPaterno.substring(0,1).equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_ENIE)){
				apellidoPaterno=apellidoPaterno.replaceFirst(Constants.MAE_CLIENTE_CARACTER_ENIE, Constants.MAE_CLIENTE_CARACTER_X);
			}
			if(StringUtils.isNotBlank(apellidoMaterno)&&apellidoMaterno.substring(0,1).equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_ENIE)){
				apellidoMaterno=apellidoMaterno.replaceFirst(Constants.MAE_CLIENTE_CARACTER_ENIE, Constants.MAE_CLIENTE_CARACTER_X);
			}	
			
			
			
			//4.	La primera letra es la inicial del primer apellido
			//validamos ademas que no sea (/)(-)(.)
			
			String letra=apellidoPaterno.substring(0,1);
			if(letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_BARRA) || letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_GUION) ||letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_PUNTO))
				generado = Constants.MAE_CLIENTE_CARACTER_X;
			else
				generado = letra;
			//5.	La segunda letra es la primera vocal interna del apellido paterno, 
			//(En caso de que el apellido paterno inicie con vocal no considerarla como vocal interna)
			boolean encontro = false;
			for(int i=0; i<apellidoPaterno.length();i++)
			{
				letra = apellidoPaterno.substring(i,i+1).toUpperCase();
				
				//validamos la existencia de caracter especial
				if((letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_BARRA) || 
						letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_GUION) ||
							letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_PUNTO))){
					generado=generado+Constants.MAE_CLIENTE_CARACTER_X;
					encontro = true;
					break;
				}
				//validamos las vocales
				if(letra.equalsIgnoreCase(Constants.MAE_CLIENTE_VOCAL_A) || letra.equalsIgnoreCase(Constants.MAE_CLIENTE_VOCAL_E) ||
						letra.equalsIgnoreCase(Constants.MAE_CLIENTE_VOCAL_I) || letra.equalsIgnoreCase(Constants.MAE_CLIENTE_VOCAL_O) || letra.equalsIgnoreCase(Constants.MAE_CLIENTE_VOCAL_U))
				{
					if(i!=0 && !encontro){
						generado=generado+letra.toUpperCase();
						encontro = true;
						break;
					}
				}
			}
			if(!encontro){
				generado=generado+Constants.MAE_CLIENTE_CARACTER_X;
			}
			//6.	La tercera letra es la primera letra del segundo apellido, de no existir un apellido materno se utiliza una (X).
			if(apellidoMaterno!=null && !apellidoMaterno.trim().equalsIgnoreCase("")){
				letra= apellidoMaterno.substring(0, 1);
				if(!(letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_BARRA) || letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_GUION) ||letra.equalsIgnoreCase(Constants.MAE_CLIENTE_CARACTER_PUNTO)))
					generado=generado+letra;
			}	
			else
				generado=generado+Constants.MAE_CLIENTE_CARACTER_X;
			
			//7.	La ultima letra es la inicial del primer nombre
			generado=generado+primerNombre.substring(0, 1);
			
			if(esAltisonante(generado)){
				char tmp[]=generado.toCharArray();
				tmp[1]=Constants.MAE_CLIENTE_CARACTER_X.charAt(0);
				generado= new String(tmp);
			}
			
			//8.	Los seis numero siguientes son la fecha de nacimiento empezando con él año a dos dígitos, mes, día
			String fecha[] = fechaNacimiento.split(Constants.MAE_CLIENTE_CARACTER_BARRA);
			generado=generado+fecha[2].substring(2)+fecha[1]+fecha[0];
		}
		//fin algoritmo
		log.info("Salio a AjaxServiceImpl - getGeneraRFCFiscal(java.lang.String, java.lang.String) - Resultado:" + generado);
		return generado;
	}
	
	public boolean esReservada(String palabra){
		List reservadas = mantenimientoMAEClienteDAO.getPalabrasCompuestasList(null);
		boolean reservada=false;
		for(int i=0; i<reservadas.size(); i++){
			if(palabra.equalsIgnoreCase(((Base)reservadas.get(i)).getDescripcion()))
			{
				reservada=true;
				break;
			}
		}
		return reservada;
	}
	
	public boolean esAltisonante(String palabra){
		List altisonantes = mantenimientoMAEClienteDAO.getPalabrasAltisonantesList(null);
		boolean altisonante=false;
		for(int i=0; i<altisonantes.size(); i++){
			if(palabra.equalsIgnoreCase(((Base)altisonantes.get(i)).getDescripcion()))
			{
				altisonante=true;
				break;
			}
		}
		return altisonante;
	}
	
	public boolean esCompuesto(String palabra){
		String partes[] = palabra.split(" ");
		if(partes.length>1)
			return true;
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarNumerosFacturacion(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidarNumerosFacturacion(String oidPais, String oidTipoDocumento, String oidSociedad) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("oidTipoDocumento", oidTipoDocumento);
		criteria.put("oidSociedad", oidSociedad);
		
		return mantenimientoPEDNumerosFacturacionDAO.getValidarNumerosFacturacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#insertNumerosFacturacion(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String insertNumerosFacturacion(String oidDocumentoSubacceso,
			String oidSociedad, String oidTipoDocumento, String numeroInterno,
			String limiteNumero, String annio, String serieInterno,
			String oidPais, String numeroAutorizacion, String llave,
			String fechaFin, String observaciones, String usuario, String valida) {
		
		Map criteria = new HashMap();
		criteria.put("oidDocumentoSubacceso", oidDocumentoSubacceso);
		criteria.put("oidSociedad", oidSociedad);
		criteria.put("oidTipoDocumento", oidTipoDocumento);
		criteria.put("numeroInterno", numeroInterno);
		criteria.put("limiteNumero", limiteNumero);
		criteria.put("annio", annio);
		criteria.put("serieInterno", serieInterno);
		criteria.put("oidPais", oidPais);
		criteria.put("numeroAutorizacion", numeroAutorizacion);
		criteria.put("llave", llave);
		criteria.put("fechaFin", fechaFin);
		criteria.put("observaciones", observaciones);
		criteria.put("usuario", usuario);
		
		try {
			if (!StringUtils.equalsIgnoreCase(valida, Constants.NUMERO_UNO)) { //--Modificar
				
				//Guardamos el registro original en una tabla historica identica
				mantenimientoPEDNumerosFacturacionDAO.insertHistoricoNumerosFacturacion(criteria);
				
				//Actualizamos los datos de auditoria en la tabla historica identica
				//mantenimientoPEDNumerosFacturacionDAO.updateHistoricoNumerosFacturacion(criteria);
				
				//Actualizamos el registro con los datos ingresados
				mantenimientoPEDNumerosFacturacionDAO.updateNumerosFacturacion(criteria);
				
			}else{ //--Insertar
				
				//Guardamos los datos ingresados
				mantenimientoPEDNumerosFacturacionDAO.insertNumerosFacturacion(criteria);
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
		return "1";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValidarEnvioValidacionesSTOGP3(java.lang.String)
	 */
	public String getValidarEnvioValidacionesSTOGP3(String codigoPais) {
		BASParametroPais baspp = new BASParametroPais();
		baspp.setCodigoPais(codigoPais);
		baspp.setCodigoSistema(Constants.STO_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.STO_CODIGO_PARAMETRO_IND_ENVIO_VALIDACIONES);
		List list = mantenimientoBASParametroPaisDAO.getParametroPais(baspp);
		BASParametroPais baspp1 = (BASParametroPais) list.get(0);
		
		if(StringUtils.equalsIgnoreCase(baspp1.getValorParametro(), Constants.NUMERO_UNO)){
			Map criteria = new HashMap();
			criteria.put("codigoSistema", Constants.STO_CODIGO_SISTEMA);
			criteria.put("codigoProcesoBatch", Constants.STO_CODIGO_PROCESO_BATCH_EN_EJECUCION);
			Integer indEnvValid = procesoPEDEjecutarGPDAO.getIndicadorEnvioValidacionesSTO(criteria);
			if(indEnvValid.intValue() > 0){
				return "1";
			}else{
				return "0";
			}
		}else{
			return "0";
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getNombreLider(java.lang.String)
	 */
	public String getNombreLider(String codigoLider) {
		return mantenimientoLECTarjetaPagoDAO
				.getNombreLider(codigoLider);
	}
	
	/*
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validaLiderTarjetaAsociada(java.lang.String)
	 */
	public String validaLiderTarjetaAsociada(String codigoLider) {
		return mantenimientoLECTarjetaPagoDAO
				.validaLiderTarjetaAsociada(codigoLider);
	}
	
	public String obtenerTarjetaPago(String numeroTarjeta){
		String retorno=null;
		TarjetaPago tarjetaPago = mantenimientoLECTarjetaPagoDAO.getTarjetaPagoByNumeroTarjeta(numeroTarjeta);
		
		if(tarjetaPago!=null){
			retorno = tarjetaPago.getCodigoTarjeta();
		}
		
		return retorno;

	}
	
	
	/*
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validaTarjetaPagoAsociada(java.lang.String)
	 */
	public String validaTarjetaPagoAsociada(String numeroTarjeta) {
		return mantenimientoLECTarjetaPagoDAO
				.validaTarjetaPagoAsociada(numeroTarjeta);
	}
	
	/**
	 * Obtener los tipos de ofertas relacionadas a una actividad MAV
	 * 
	 * @param oidActividad
	 * @return
	 */
	public LabelValue[] getTiposOfertaByActividadId(String oidActividad) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		List tipos = mantenimientoMAVConfiguracionDAO
				.getTiposOfertaId(oidActividad);

		if (tipos != null && tipos.size() > 0) {
			result = new LabelValue[tipos.size() + 1];
			result[0] = new LabelValue("", "");

			for (int i = 0; i < tipos.size(); i++) {
				Base base = (Base) tipos.get(i);

				result[i + 1] = new LabelValue(base.getDescripcion(),
						base.getCodigo());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}

		return result;

	}

	public LabelValue[] getActividadesByTipoCliente(String oidTipoActividad) {
		// TODO Auto-generated method stub
		LabelValue[] result = null;

		List tipos = mantenimientoMAVConfiguracionDAO
				.getActividadesbyTipoCliente(oidTipoActividad);

		if (tipos != null && tipos.size() > 0) {
			result = new LabelValue[tipos.size() + 1];
			result[0] = new LabelValue("", "");

			for (int i = 0; i < tipos.size(); i++) {
				Base base = (Base) tipos.get(i);

				result[i + 1] = new LabelValue(base.getDescripcion(),
						base.getCodigo());
			}
		} else {
			result = new LabelValue[1];
			result[0] = new LabelValue("", "");
		}

		return result;

	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getOidSubaccesosByOidAcceso(java.lang.String)
	 */
	public LabelValue[] getOidSubaccesosByOidAcceso(String oidAcceso) {
		LabelValue[] result = null;

		try {
			
			List subaccesos = mantenimientoPEDConfiguracionOfertaService.getOidSubaccesosByOidAcceso(oidAcceso);
			
			if (subaccesos != null && subaccesos.size() > 0) {
				result = new LabelValue[subaccesos.size()];

				for (int i = 0; i < subaccesos.size(); i++) {
					BaseOID sa = (BaseOID) subaccesos.get(i);
					// Construimos la descripcion
					LabelValue lv = new LabelValue(sa.getDescripcion(), sa.getOid().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validarTipoOferta(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String validarTipoOferta(String oidTipoOferta, String codigoProducto, String oidEstrategia, String precioCatalogo, String precioPosicionamiento) {
		
		precioCatalogo = StringUtils.isBlank(precioCatalogo) ? Constants.NUMERO_CERO : precioCatalogo;
		precioPosicionamiento = StringUtils.isBlank(precioPosicionamiento) ? Constants.NUMERO_CERO : precioPosicionamiento;
		
		String mensaje = mantenimientoPEDConfiguracionOfertaService.getValidarTipoOferta(oidTipoOferta, codigoProducto, oidEstrategia, precioCatalogo, precioPosicionamiento);
		
		if(mensaje == null)
			mensaje = "";
		
		return mensaje;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getOidTipoEstrategia(java.lang.String)
	 */
	public String getOidTipoEstrategia(String oidEstrategia) {
		String oid = mantenimientoPEDConfiguracionOfertaService.getOidTipoEstrategia(oidEstrategia);
		if(oid == null)
			oid = "";
		
		return oid;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTiposCuadre(java.lang.String)
	 */
	public LabelValue[] getTiposCuadre(String numeroGrupos) {
		LabelValue[] result = null;

		try {
			
			List tipos = mantenimientoPEDConfiguracionOfertaService.getTiposCuadre(numeroGrupos);
			
			if (tipos != null && tipos.size() > 0) {
				result = new LabelValue[tipos.size()];

				for (int i = 0; i < tipos.size(); i++) {
					BaseOID sa = (BaseOID) tipos.get(i);
					// Construimos la descripcion
					LabelValue lv = new LabelValue(sa.getDescripcion(), sa.getOid().toString());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}

		return result;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getProductoPREMatrizRecuperaciones(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ProductoMatriz getProductoPREMatrizRecuperaciones(String codigoPeriodo, String cuv, String flagRecuperacion) {
		return mantenimientoPREMatrizRecuperacionesDAO.getProductoPREMatrizRecuperaciones(codigoPeriodo, cuv, flagRecuperacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getProductoPREMatrizAlternativo(java.lang.String, java.lang.String)
	 */
	public ProductoMatriz getProductoPREMatrizAlternativo(String codigoPeriodo,	String cuv) {
		return mantenimientoPREMatrizAlternativosDAO.getProductoPREMatrizAlternativo(codigoPeriodo, cuv);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getRegionesZonasByTipoGrupo(java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesZonasByTipoGrupo(String codigoPais, String codigoGrupoPago) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
            criteria.put("codigoPais", codigoPais);
            criteria.put("codigoGrupoPago", codigoGrupoPago);
            
            String tipoGrupo = mantenimientoLECProgramaCorporativoDAO.getTipoGrupo(criteria);
            
            List tiposClasificaciones = null;
            
            if(StringUtils.equals(tipoGrupo, Constants.ZON_TIPO_UA_REGION))
            {
            	tiposClasificaciones = mantenimientoLECProgramaCorporativoDAO.getRegionesByTipoGrupo(criteria);
            }
            else if(StringUtils.equals(tipoGrupo, Constants.ZON_TIPO_UA_ZONA))
            {
            	tiposClasificaciones = mantenimientoLECProgramaCorporativoDAO.getZonasByTipoGrupo(criteria);
            }
						
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base clasificacion = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(clasificacion.getDescripcion(), clasificacion.getCodigo());
					result[i] = lv;	                
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
			
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
	  return result;	
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getRegionesCodigoByPais(java.lang.String)
	 */
	public LabelValue[] getRegionesCodigoByPais(String codigoPais) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);

			try {
	
				List regiones = interfazSiCCDAO.getRegionesByPais(criteria);

				// Creamos una primera opción vacía

				log.debug("Tamaño de las regiones 3:" + regiones.size());
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getCodigo()+" "+region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		log.debug("Tamaño de las regiones 3:" + result.length);

		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getConcursoProgramaPuntos(java.lang.String, java.lang.String)
	 */
	public LabelValue getConcursoProgramaPuntos(String codigoPais, String codigoConcurso) {
		
		Map criteria = new HashMap();
		criteria.put("numeroConcurso", codigoConcurso);
		
		List lista = mantenimientoINCConfiguracionConcursoDAO.getConcursosByCriteria(criteria);
		
		LabelValue concurso = null;
		if(lista != null && lista.size() == 1)
		{
			Map c = (Map)lista.get(0);
			String indicadorProgramaPuntos = MapUtils.getString(c, "indicadorProgramaPuntos");
			
			if(StringUtils.equals(indicadorProgramaPuntos, Constants.NUMERO_UNO))
			{
			concurso = new LabelValue(MapUtils.getString(c, "nombreConcurso"), MapUtils.getString(c, "oidConcurso"));
		}
			else
			{
				concurso = new LabelValue(Constants.NUMERO_CERO, Constants.NUMERO_CERO); //NO ES PROGRAMA DE CONCURSO DE PUNTOS
			}			
		}
		
		return concurso;
	}

	
	public LabelValue[] getDesAccionesByProcesosBloqueo(final String oidProcesoBloqueo) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(oidProcesoBloqueo)) {
			Map criteria = new HashMap();

			criteria.put("oidProcesoBloqueo", oidProcesoBloqueo);
			log.debug("LA CRITERIA : " + criteria);
			try {
				List acciones = mantenimientoMAEEntidadGenericaDAO.getAccionBloqueoList(criteria);
				if (acciones != null && acciones.size() > 0) {
					result = new LabelValue[acciones.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < acciones.size(); i++) {
						Base accion = (Base) acciones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(accion.getDescripcion(),
								accion.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getVerificarCierreCampanha(java.lang.String)
	 */
	public String getVerificarCierreCampanha(String codigoPeriodo) {

		String retorno = Constants.SI;
    	try {
    		Map criteriaProgramacionCierre = new HashMap();
			criteriaProgramacionCierre.put("tipoCierre", Constants.LET_TIPO_CIERRE_CAMPANHA);
			criteriaProgramacionCierre.put("estadoCierre", Constants.STATUS_PREMIO_ANULADO);
			criteriaProgramacionCierre.put("campanhaProceso", codigoPeriodo); 
			
			List listCierreCampanha = mantenimientoFACGenericoDAO.getCierreFacturacion(criteriaProgramacionCierre);
			if (listCierreCampanha != null && listCierreCampanha.size() > 0)
				retorno = Constants.NO;
    		
    	}
    	catch (Exception e) {
			log.debug("ajax Error getVerificarCierreCampanha " + e.getMessage());
		}
    	return retorno;

		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getClasificacionConsultoraByDocIdentidad(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getClasificacionConsultoraByDocIdentidad(String codigoPais,
			String numeroDocumento, String tipoDocumento,
			String codigoConsultora) {
		String resultado= Constants.SI;
		Integer existencia =new Integer(0);
		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);
		criteria.put("tipoDocumento", tipoDocumento);
		criteria.put("numeroDocumentoIdentidad", numeroDocumento);
		criteria.put("codigoConsultora", codigoConsultora);
		log.debug("Ejecutando AjaxServiceImpl.getClasificacionConsultoraByDocIdentidad");
		
		try {
			existencia = mantenimientoCCCDigitacionPagosChequesDAO.getClasificacionConsultoraByDocIdentidad(criteria);
			log.debug("-->>>>" + resultado);
			if (existencia == null || existencia == 0) {
				resultado = Constants.NO;
				log.debug("ajax getClasificacionConsultoraByDocIdentidad " + existencia);
			}
		} catch (Exception e) {
			log.debug("ajax Error getClasificacionConsultoraByDocIdentidad "
					+ existencia);
		}
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getActivaConsultoraByDocIdentidad(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getActivaConsultoraByDocIdentidad(String codigoPais,
			String numeroDocumento, String tipoDocumento,
			String codigoConsultora, String tipoEntrada) {
		String resultado= Constants.SI;
		Integer existencia =new Integer(0);
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		String codigoPeriodo = new String();
		codigoPeriodo = mantenimientoPRECambioCodigoVentaDAO.getPeriodoActivo(map);
		
		Map criteriaConsultora = new HashMap();
		criteriaConsultora.put("tipoDocumento", tipoDocumento);
		criteriaConsultora.put("numeroDocumentoIdentidad", numeroDocumento);
		
		if(StringUtils.equals(tipoEntrada, Constants.NUMERO_UNO))
		{
			codigoConsultora =  mantenimientoCCCDigitacionPagosChequesDAO.getConsultoraByDocIdentidad(criteriaConsultora);
		}
		
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoConsultora", codigoConsultora);
		log.debug("Ejecutando AjaxServiceImpl.getActivaConsultoraByDocIdentidad");
		
		try {
			existencia = mantenimientoCCCDigitacionPagosChequesDAO.getActivaConsultoraByDocIdentidad(criteria);
			log.debug("-->>>>" + resultado);
			if (existencia == null || existencia == 0) {
				resultado = Constants.NO;
				log.debug("ajax getActivaConsultoraByDocIdentidad " + existencia);
			}
		} catch (Exception e) {
			log.debug("ajax Error getActivaConsultoraByDocIdentidad "
					+ existencia);
		}
		return resultado;
	}

	
	public LabelValue[] getMotivosRechazoSTOList(final String codigoTipoDocumentoDigi,final String codigoPais) {
		LabelValue[] result = null;
	
		if (StringUtils.isNotBlank(codigoTipoDocumentoDigi)) {
			Map criteria = new HashMap();
			criteria.put("codigoTipoDocumento", codigoTipoDocumentoDigi);
			criteria.put("codigoPais", codigoPais);

			try {

				List motivosRechazo = procesoSTOEjecucionValidacionesDAO.getMotivosRechazoSTOList(criteria);
	
				// Creamos una primera opción vacía

				log.debug("Tamaño de las regiones 3:" + motivosRechazo.size());
				if (motivosRechazo != null && motivosRechazo.size() > 0) {
					result = new LabelValue[motivosRechazo.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < motivosRechazo.size(); i++) {
						Base motivoRechazo = (Base) motivosRechazo.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(motivoRechazo.getCodigo()+" "+motivoRechazo.getDescripcion(),
								motivoRechazo.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		log.debug("Tamaño de las regiones 3:" + result.length);

		return result;
	}
	
	public String getCodigoValidacion(String codigoTipoDocumentoDigi) {
		
		String codigoValidacion = "";

		try {
			codigoValidacion = procesoSTOEjecucionValidacionesDAO.getCodigoValidacion(codigoTipoDocumentoDigi);
		} catch (Exception e) {
			log.debug("ajax Error getCodigoValidacion "
					+ e);
		}
		return codigoValidacion;
	}
	
	public LabelValue[] getValidaciones(final String codigoTipoDocumentoDigi,final String codigoPais) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoTipoDocumentoDigi)) {
			Map criteria = new HashMap();
			criteria.put("codigoTipoDocumento", codigoTipoDocumentoDigi);
			criteria.put("codigoPais", codigoPais);

			try {

				List motivosRechazo = procesoSTOEjecucionValidacionesDAO.getValidacionesSTOList(criteria);

				// Creamos una primera opción vacía

				log.debug("Tamaño de las regiones 3:" + motivosRechazo.size());
				if (motivosRechazo != null && motivosRechazo.size() > 0) {
					result = new LabelValue[motivosRechazo.size()];
					// result[0] = new LabelValue("", "");
					for (int i = 0; i < motivosRechazo.size(); i++) {
						Base motivoRechazo = (Base) motivosRechazo.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(motivoRechazo.getCodigo()+" "+motivoRechazo.getDescripcion(),
								motivoRechazo.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		log.debug("Tamaño de las regiones 3:" + result.length);

		return result;
	}

	public String getCodigoMensaje(String codigoValidacion) {
		
		String codigoMensaje = "";
	
		try {
			codigoMensaje = procesoSTOEjecucionValidacionesDAO.getCodigoMensaje(codigoValidacion);
		} catch (Exception e) {
			log.debug("ajax Error getCodigoMensaje "
					+ e);
		}
		return codigoMensaje;
	}
	
	public LabelValue[] getClasificacionByOidTipoClasificacionIMP(String oidTipoClasificacion) {
		LabelValue[] result = null;

		try {
			result = new LabelValue[1];
			Map criteria = new HashMap();
			criteria.put("oidTipoClasificacion",
					oidTipoClasificacion);

			List clasificacinoList = mantenimientoIMPProcesoImpresionDAO.getClasificacionList(criteria);
			if (clasificacinoList != null && clasificacinoList.size() > 0) {

				result = new LabelValue[clasificacinoList.size()];
				for (int i = 0; i < clasificacinoList.size(); i++) {
					Base clasificacion = (Base) clasificacinoList.get(i);
					LabelValue lv = new LabelValue(
							clasificacion.getDescripcion(),
							clasificacion.getCodigo());
					result[i] = lv;
				}
			}else {
				// Creamos una primera opción vacķa
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}

		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getRegionesZonasByTiposGrupo(java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesZonasByTiposGrupo(String codigoPais, String[] codigoGrupoPago) {
		LabelValue[] result = null;
		try {
			Map criteria = new HashMap();
            criteria.put("codigoPais", codigoPais);
            
            criteria.put("codigoGrupoPago",
                    (codigoGrupoPago == null) ? new ArrayList()
                                              : Arrays.asList(codigoGrupoPago));
            
            String tipoGrupo = mantenimientoLECProgramaCorporativoDAO.getTiposGrupo(criteria);
            
            List tiposClasificaciones = null;
            
            if(StringUtils.equals(tipoGrupo, Constants.ZON_TIPO_UA_REGION))
            {
            	tiposClasificaciones = mantenimientoLECProgramaCorporativoDAO.getRegionesByTiposGrupo(criteria);
            }
            else if(StringUtils.equals(tipoGrupo, Constants.ZON_TIPO_UA_ZONA))
            {
            	tiposClasificaciones = mantenimientoLECProgramaCorporativoDAO.getZonasByTiposGrupo(criteria);
            }
						
			if (tiposClasificaciones != null && tiposClasificaciones.size() > 0) {
				result = new LabelValue[tiposClasificaciones.size()];
				
				for (int i = 0; i < tiposClasificaciones.size(); i++) {
					Base clasificacion = (Base) tiposClasificaciones.get(i);
					LabelValue lv = new LabelValue(clasificacion.getDescripcion(), clasificacion.getCodigo());
					result[i] = lv;	                
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
			
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
	  return result;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#validarNumeroCarnetIdentidad(java.lang.String)
	 */
	public String validarNumeroCarnetIdentidad(String numeroDocumentoIdentidad) {
		return mantenimientoMAEClienteDAO.validarNumeroCarnetIdentidad(numeroDocumentoIdentidad);
	}
	
	public String getIndicadorCuponReutilizable(String pais, String programa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais);
		criteria.put("codigoPrograma", programa);
		return mantenimientoDAO.getIndicadorCuponReutilizable(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getValExcepMotivo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getValExcepMotivo(String idPeriodoCDR, String codigoVenta, String codigoOperacionSic, String tipoOperacionSic, String oidSoliPosi, String codigoMotivo) {
		
		Map criteria = new HashMap();
		criteria.put("idPeriodoCDR", Integer.parseInt(idPeriodoCDR));
		criteria.put("codigoVenta", codigoVenta);
		criteria.put("codigoOperacionSic", codigoOperacionSic);
		criteria.put("tipoOperacionSic", tipoOperacionSic);
		criteria.put("oidSoliPosi", StringUtils.isBlank(oidSoliPosi)? Long.parseLong("0"): Long.parseLong(oidSoliPosi));
		criteria.put("codigoMotivo", codigoMotivo);
		
		String mensaje = mantenimientoRECDigitacionCDRDAO.getValExcepMotivo(criteria);
		
		return mensaje;
		
	}

	/* INI NSSICC */
	public LabelValue[] getSeccionMultipleByPaisMarcaCanalRegionZona(
			final String codigoPais, final String codigoMarca,
			final String codigoCanal, final String[] codigoRegion,
			final String[] codigoZona, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(codigoCanal)
					&& StringUtils.isNotBlank(codigoCanal)) {
				criteria.put("codigoCanal", codigoCanal);
			} else {
				return result;
			}
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoZona", codigoZona);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List territoriosList = interfazSiCCDAO.getLista(
						"getSeccionMultipleByPaisMarcaCanalRegionZona",
						criteria);
				if (territoriosList != null && territoriosList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[territoriosList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < territoriosList.size(); i++) {
							Base periodo = (Base) territoriosList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[territoriosList.size()];
						for (int i = 0; i < territoriosList.size(); i++) {
							Base concurso = (Base) territoriosList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#
	 * getZonasMultipleByPaisMarcaCanalRegionOid(java.lang.String,
	 * java.lang.String, java.lang.String, java.util.ArrayList,
	 * java.lang.String)
	 */
	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionOid(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			/*
			 * if (codigoRegiones != null && codigoRegiones.length > 0) {
			 * ArrayList list = new ArrayList(codigoRegiones.length); for (int
			 * i=0; i <codigoRegiones.length;i++)
			 * list.add(i,codigoRegiones[i].getLabel());
			 * criteria.put("codigoRegion", list); }
			 */
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = new ArrayList();
				;
				try {
					zonasList = interfazSiCCDAO.getLista("getZonasMultipleByPaisMarcaCanalRegionOid", criteria);
				} catch (Exception e) {
					// TODO: handle exception
				}

				if (zonasList != null && zonasList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}
	
	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegionDetalle(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones, //final ArrayList codigoRegiones,
			String condicionTodos, String tipoDetalle) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			/*
			 * if (codigoRegiones != null && codigoRegiones.length > 0) {
			 * ArrayList list = new ArrayList(codigoRegiones.length); for (int
			 * i=0; i <codigoRegiones.length;i++)
			 * list.add(i,codigoRegiones[i].getLabel());
			 * criteria.put("codigoRegion", list); }
			 */
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = interfazSiCCDAO.getLista("getZonasMultipleByPaisMarcaCanalRegion", criteria);
				if (zonasList != null
						&& zonasList.size() > 0
						&& (tipoDetalle.equalsIgnoreCase("Zona")
								|| tipoDetalle.equalsIgnoreCase("Consultora") || tipoDetalle
									.equalsIgnoreCase("Premio"))) {
					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}
	
	public LabelValue[] getZonasMultipleByPeriodoIntEviPerioRegioZona(
			final String codigoPais, final String codigoPeriodo,
			final String codigoMarca, final String codigoCanal,
			final String[] codigoRegiones, String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPeriodo)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				if (codigoRegiones.length == 0) {
					return result;
				}
				if (StringUtils.isEmpty(codigoPeriodo)) {
					return result;
				}
				if (StringUtils.isEmpty(codigoMarca)) {
					return result;
				}
				if (StringUtils.isEmpty(codigoCanal)) {
					return result;
				}
				List regiones = interfazSiCCDAO.getLista(
						"getZonasMultipleByPeriodoIntEviPerioRegioZona",
						criteria);
				if (regiones != null && regiones.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[regiones.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < regiones.size(); i++) {
							Base periodo = (Base) regiones.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base concurso = (Base) regiones.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}
	
	public String getDescripcionProgramaAdicionar(String oidTipoConcurso) {
		String resultado="";
		if (oidTipoConcurso!=null){
			resultado = mantenimientoINCConfiguracionConcursoDAO.getDescripcionProgramaAdicionar(oidTipoConcurso);
		}
		return resultado;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#getTiposDespachoCPP(java.lang.String)
	 */
	public LabelValue[] getTiposDespachoCPP(String tipoLista) {
		LabelValue[] result = null;
		
		if(StringUtils.equals(tipoLista, Constants.NUMERO_UNO))
		{
			result = new LabelValue[2];
			
			result[0] = new LabelValue(Constants.INC_TIPO_DESPACHO_CPP_DESPACHA_PREMIO_NOMBRE, Constants.INC_TIPO_DESPACHO_CPP_DESPACHA_PREMIO_CODIGO);
			result[1] = new LabelValue(Constants.INC_TIPO_DESPACHO_CPP_SIN_DESPACHO_PREMIO_NOMBRE, Constants.INC_TIPO_DESPACHO_CPP_SIN_DESPACHO_PREMIO_CODIGO);			
		}
		else if(StringUtils.equals(tipoLista, Constants.NUMERO_DOS))
		{
			result = new LabelValue[3];
			
			result[0] = new LabelValue(Constants.INC_TIPO_DESPACHO_CPP_PREMIACION_UNICA_NOMBRE, Constants.INC_TIPO_DESPACHO_CPP_PREMIACION_UNICA_CODIGO);
			result[1] = new LabelValue(Constants.INC_TIPO_DESPACHO_CPP_PREMIACIONES_VARIAS_NOMBRE, Constants.INC_TIPO_DESPACHO_CPP_PREMIACIONES_VARIAS_CODIGO);
			result[2] = new LabelValue(Constants.INC_TIPO_DESPACHO_CPP_SIN_DESPACHO_PREMIO_NOMBRE, Constants.INC_TIPO_DESPACHO_CPP_SIN_DESPACHO_PREMIO_CODIGO);			
		}
		
		return result;
	}
	
	
	public String deleteBoletaTemporal1(String numeroBoletaRecojo,
			String numeroRecojo, String codigoCliente, List listaBoletas,List temp) {
		
		Map criteria = new HashMap();
		criteria.put("numeroBoletaRecojo", Integer.parseInt(numeroBoletaRecojo));
		criteria.put("numeroRecojo", numeroRecojo);
		criteria.put("codigoCliente", codigoCliente);
		
		procesoRECCierreBRDAO.removeBoletaTemporal(criteria);
		
		//Eliminamos de la lista en session
		//List listaBoletas = (List) session.getAttribute(Constants.REC_LISTA_RELACION_BOLETAS_RECOJO_CORRECTAS_TEMPORAL_LIST);
		
		
		if(listaBoletas != null && listaBoletas.size() > 0)
		{
			for(int i=0; i<listaBoletas.size(); i++)
			{
				Map boleta = (Map)listaBoletas.get(i);				
				if(!(StringUtils.equals(numeroBoletaRecojo, MapUtils.getString(boleta, "numeroBoletaRecojo")) &&
						StringUtils.equals(numeroRecojo, MapUtils.getString(boleta, "numeroRecojo")) &&
						StringUtils.equals(codigoCliente, MapUtils.getString(boleta, "codigoCliente"))))
						{
							temp.add(boleta);
						}				
			}
		}
		
		//session.setAttribute(Constants.REC_LISTA_RELACION_BOLETAS_RECOJO_CORRECTAS_TEMPORAL_LIST, listaBoletasActualizada);
		//
		
		
		log.debug("Se elimino el registro de Boleta Recojo a la Tabla Temporal");
		
		return "Eliminacion Satisfactorio";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService#executeProcesoAnulacionBR(java.lang.String)
	 */
	public Map executeProcesoAnulacionBR1(String usuario, 
			 List listTablaTemporal, Integer totalIncorrectas
			 ) {
		
		//List listTablaTemporal = (List)session.getAttribute(Constants.REC_ANULA_MASIVA_BOLETA_RECOJO_LIST);
		Map res = new HashMap();
		Integer resultado = null;
		String numeroLote = null;
		
		if(listTablaTemporal.size() > 0){
			//Integer totalIncorrectas = (Integer)session.getAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_INCORRECTAS);
			if(totalIncorrectas.intValue() > 0){
				resultado = new Integer("1");
			}else{
				
				log.debug("Inicio Anulando Boleta de Recojo listTablaTemporal.size()");
				procesoRECCierreBRDAO.deleteTablaTemporal();
				
			    for(int i=0; i<listTablaTemporal.size(); i++){
			    	Map map = (HashMap)listTablaTemporal.get(i);
			    		procesoRECCierreBRDAO.insertProcesoBoletaRecojoTemporal(map);
			    	}
				
				numeroLote = procesoRECCierreBRDAO.getNumeroLoteBoletasRecojo();
				Map criteria = new HashMap();
				criteria.put("numeroLote", numeroLote);
				criteria.put("codigoResultadoBR", "NE");
				criteria.put("usuarioLogin", usuario);
				procesoRECCierreBRDAO.executeValidarProcesoBoleta(criteria);
				
				for(int i=0;i<listTablaTemporal.size();i++){
					HashMap map = (HashMap)listTablaTemporal.get(i);
					map.put("flag", "1");
					map.put("observacion", "OK");
				}
				
				//session.setAttribute("numeroLote", numeroLote);
				//session.setAttribute(Constants.REC_ANULA_MASIVA_BOLETA_RECOJO_LIST, listTablaTemporal);
	    	    
				//session.setAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD, listTablaTemporal.size());
	    	    //session.setAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_INCORRECTAS, new Integer(0));
	    	    //session.setAttribute(Constants.REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_CORRECTAS, listTablaTemporal.size());
			    resultado = new Integer("2");
			    log.debug("Fin Anulando Boleta de Recojo ");
			}
		}else{
			resultado = new Integer("3");
		}
		res.put("resultado", resultado);
		res.put("numeroLote",numeroLote);
		
		return res;
	}
	
	public LabelValue[] getCampaniasIniFinByConcursoTipoProgramaPuntos(
			String numeroConcurso, String codigoPais) {

		Map data = mantenimientoINCConfiguracionConcursoDAO
				.getCampaniasIniFinByConcursoTipoProgramaPuntos(numeroConcurso);
		String periodoDesde = data.get("periodoDesde").toString();

		Map dataCampania = new HashMap();
	    dataCampania.put("codigoPais", codigoPais);
		dataCampania.put("codigoPeriodoInicio", periodoDesde);
		dataCampania.put("codigoPeriodoFin", data.get("periodoHasta").toString());
		int cantCampanias = reporteDAO.getDiferenciaPeriodos(dataCampania);

		LabelValue[] result = new LabelValue[cantCampanias];

		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("numeroPeriodo", -1);
		map.put("codigoPeriodo", periodoDesde);
		String periodo = genericoDAO.getPeriodoNSiguiente(map);
		map.put("numeroPeriodo", 1);

		for (int i = 0; i < cantCampanias; i++) {
			
			map.put("codigoPeriodo", periodo);
			periodo = genericoDAO.getPeriodoNSiguiente(map);

			Map oidPeriodoMap = new HashMap();
	        oidPeriodoMap.put("codigoPeriodo", periodo);
			String oidPeriodo = consultaHIPDatosClienteDAO.getOidPeriodo(oidPeriodoMap).toString();

			LabelValue lv = new LabelValue(periodo, oidPeriodo);
			result[i] = lv;
		}

		return result;
	}

	
	
	
	
	/* FIN NSSICC */

}
