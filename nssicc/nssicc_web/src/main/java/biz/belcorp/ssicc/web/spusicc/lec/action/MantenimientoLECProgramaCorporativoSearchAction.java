package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;
import biz.belcorp.ssicc.dao.spusicc.lec.model.AmbitoGeografico;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoCampana;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoLanzamiento;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoLanzamientoProducto;
import biz.belcorp.ssicc.dao.spusicc.lec.model.BonoNivel;
import biz.belcorp.ssicc.dao.spusicc.lec.model.CampanaExigencia;
import biz.belcorp.ssicc.dao.spusicc.lec.model.Canasta;
import biz.belcorp.ssicc.dao.spusicc.lec.model.CanastaDetalle;
import biz.belcorp.ssicc.dao.spusicc.lec.model.CobranzaTramo;
import biz.belcorp.ssicc.dao.spusicc.lec.model.Nivel;
import biz.belcorp.ssicc.dao.spusicc.lec.model.NivelTramo;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ObjetivoCobranza;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaCanastaPremi;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaCorporativo;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaDesempenio;
import biz.belcorp.ssicc.dao.spusicc.lec.model.ProgramaEtapaCampana;
import biz.belcorp.ssicc.dao.spusicc.lec.model.Ranking;
import biz.belcorp.ssicc.dao.spusicc.lec.model.RankingNivel;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TramoObjetivoCobranza;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.action.ReporteLETConfiguracionProgramaAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.MantenimientoLECProgramaCorporativoForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.MantenimientoLECProgramaCorporativoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoLECProgramaCorporativoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -6479611841366717050L;

	private List lecProgramaCorporativoList;
	private List lecTipoComiList;
	private List lecTipoNivelList;
	private String idCodigoPrograma = "";
	private String codigoPeriodoValidarIncentivo;

	// Nivel
	private List lecNivelCorporativoList;
	private List lecProgramaCorporativoNivelList;
	private List lecRetencionList;
	private List lecIndicadorTipoMetaList;
	private DataTableModel lecProgramaCorporativoNivelListDataModel;
	private Object beanLecProgramaCorporativoNivel;
	private String indicadorActivoNivel;
	private String metaPedido = Constants.LEC_INDICADOR_TIPO_META_PEDIDO;
	private String metaVenta = Constants.LEC_INDICADOR_TIPO_META_VENTA;

	// Cobranza
	private LabelValue[] siccZonaList;
	private List lecProgramaCorporativoObjCobTablasAuxiliaresList;
	private List siccRegionList;
	private List lecTipoMedicionObjetivoList;
	private List lecSelectTramoList;
	private List lecAmbGeoList;
	private List lecTramoList;
	private List lecProgramaCorporativoObjCobList;
	private DataTableModel lecProgramaCorporativoObjCobDataModel;
	private Object beanLecProgramaCorporativoObjCob;
	private boolean showRegionCobranza;
	private boolean showZonaCobranza;
	private boolean showDiasCobranza;
	private String indicadorActivoCob;

	// Bonos -Lanzamiento
	private LabelValue[] lecBonoNivelCorporativoList;
	private List lecCampLanzaNivelListModificacion;
	private List lecCampLanzaList;
	private List lecTipoMedicionList;
	private List lecCampLanzaNivelList;
	private DataTableModel lecCampLanzaNivelListDataModel;
	private Object[] beanLecCampLanzaNivelList;
	private String indicadorActivoBonNivel;
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	@ManagedProperty(value = "#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearch;

	// Bonos -Ciclo Vida
	private List lecTipoBonoList;
	private List lecTipoPremiacionList;
	private DataTableModel lecRetencionDataModel;
	private Object[] beanlecRetencion;
	private String indicadorActivoBonCicloVida22;
	private String premioCanasta = Constants.LEC_CODIGO_TIPO_PREMIACION_CANASTA;
	private boolean habilitaMontoPremio;

	// Bonos -Nivel acelerado
	private List lecCambNivelAvan;
	private DataTableModel lecCambNivelAvanDataModel;
	private Object[] beanlecCambNivelAvan;
	private String indicadorActivoBonNivelAce;

	// Bonos -Gracia Nueva
	private List lecPeriodoGracia;
	private DataTableModel lecPeriodoGraciaDataModel;
	private Object[] beanlecPeriodoGracia;

	// Incentivos--1 parte
	private List lecIncTipoComiList;
	private DataTableModel lecIncTipoComiDataModel;
	private Object[] beanlecIncTipoComiList;
	private String indicadorActivoIncNivel;
	private List lecNivelRangoIncenList;

	// Incentivos--2 parte
	private List lecIncMontoRecuList;
	private DataTableModel lecIncMontoRecuDataModel;
	private Object beanlecIncMontoRecu;
	// Incentivos--3 parte
	private List lecCanastaList;
	private List lecProductosCanastaList;
	private List lecCanasProdList;
	private List lecIncCanasProd;
	private List lecTipoCanastaList;
	private DataTableModel lecIncCanasProdDataModel;
	private Object beanlecIncCanasProd;
	private boolean indIncCanasProd;
	private String indicadorActivoIncCanasta;

	// Canasta
	private List lecTiposOfertaProductoCanasta;
	private DataTableModel lecProductosCanastaDataModel;
	private Object beanlecProductosCanasta;
	private DataTableModel lecCanastaDataModel;
	private Object beanlecCanasta;
	private String indicadorActivoCanasta;
	private static final String POPUP_PRODUCTO = "POPUP_PRODUCTO";
	private boolean mostrarPopupProducto;

	// Gestion Dese.
	private List lecListaTipoDesem;
	// private DualListModel<Base> listaCampanias;
	private List campanaEvaluacionLec;
	private List selecCampanaEvaluarLec;
	private List lecProgramaCorporativoGestDesemList;
	private DataTableModel lecProgramaCorporativoGestDesemDataModel;
	private Object beanlecProgramaCorporativoGestDesem;
	private String indicadorActivoGestDesem;
	private boolean indConsiderarNuevas;
	private boolean showExitosasDesempenio;

	// RCR PER-SICC-2015-0429
	private Boolean indConsiderarMetasIngresos;
	// PER-SiCC-2015-0548
	private Boolean considerarIngresoCapi;

	// Ranking
	private List lecTipoRankingList;
	private List lecRankingNivelesList;
	private DataTableModel lecRankingNivelesDataModel;
	private List lecRankingList;
	private DataTableModel lecRankingDataModel;
	private Object beanlecRanking;
	private String primerTipoMedi = Constants.LEC_RANKING_CODIGO_TIPO_MEDICION_CUMPLIMIENTO_PEDIDOS;
	private String segundoTipoMedi = Constants.LEC_RANKING_CODIGO_TIPO_MEDICION_SOBRECUMPLIMIENTO_PEDIDOS;
	private String primerDescriTipoMedi = Constants.LEC_RANKING_DESCRI_TIPO_MEDICION_CUMPLIMIENTO_PEDIDOS;
	private String segundoDescriTipoMedi = Constants.LEC_RANKING_DESCRI_TIPO_MEDICION_SOBRECUMPLIMIENTO_PEDIDOS;
	private String codigoTipoRanking;
	private Object[] valores;
	private List listAux01;
	private List listAux02;
	private List listAux03;

	// Popup--Nivel Exito
	private List lecNivelAmbGeoList;
	private List ambitoNivelSeleccion;
	private DataTableModel lecNivelAmbGeoDataModel;
	private LabelValue[] siccZonaNivelList;
	private boolean indExcluirNivel;
	private String nivelShowRegionZona;
	private String indicadorActivoAmbGeoNivel;

	// Popup--Pedido Lider
	private List lecPedLidAmbGeoList;
	private List ambitoPedidoSeleccion;
	private DataTableModel lecPedLidAmbGeoDataModel;
	private LabelValue[] siccZonaPedidoList;
	private String pedidoShowRegionZona;
	private String indicadorActivoAmbGeoPedidoLider;
	private boolean indExcluir;

	private boolean indActCobranza;
	private boolean indProgramaRecono;
	private boolean indIndicadorFeriado;
	private boolean indInsPortalFFVV;
	private boolean indInsSistCome;
	private boolean indInsPosiLider;
	private boolean indPedidoWeb;
	private boolean indExigPedPersona;
	private boolean indExigCursoCamb;
	private boolean consultar;
	private boolean showTipoComi;
	private boolean showExCurso;

	private String mostrarPrimerMensaje;
	private Boolean mostrarPrimerMensajeBoolean;

	private List listaEliminaCobranzaTemp;

	// Reporte
	@ManagedProperty(value = "#{reporteLETConfiguracionProgramaAction}")
	private ReporteLETConfiguracionProgramaAction reporte;

	@Override
	protected String getSalirForward() {
		return "mantenimientoLECProgramaCorporativoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoLECProgramaCorporativoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoLECProgramaCorporativoSearchForm searchForm = new MantenimientoLECProgramaCorporativoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoLECProgramaCorporativoSearchForm f = (MantenimientoLECProgramaCorporativoSearchForm) this.formBusqueda;
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		criteria.put("descripcionPrograma",
				f.getDescripcionPrograma().concat("%"));

		List programaCorporativoList = service
				.getProgramaCorporativoList(criteria);
		return programaCorporativoList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map elemento = (Map) this.beanRegistroSeleccionado;
		String codigoPrograma = elemento.get("codigoPrograma").toString();

		if (codigoPrograma != null) {
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
			MantenimientoOCRPedidoControlFacturacionService servicecontrolFacturacion = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", codigoPrograma);
			criteria.put("usuario", usuario.getLogin());

			Map criteriaCampanya = new HashMap();
			criteriaCampanya.put("codigoPais", pais.getCodigo());
			criteriaCampanya.put("estadoCampanha", "0");
			criteriaCampanya.put("indicadorActiva", "1");
			List listaCampanyas = servicecontrolFacturacion
					.getCampanhasActivasByCriteria(criteriaCampanya);
			if (listaCampanyas.size() == 1) {
				String codigoPeriodo = (String) listaCampanyas.get(0);
				criteria.put("codigoPeriodo", codigoPeriodo);

				Integer validar = service
						.getVerificarEliminarPrograma(criteria);
				if (validar.intValue() > 0) {
					String mensajeerror = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.eliminarProgramaPeriodo");
					throw new Exception(mensajeerror);
				}
			}
			service.executeEliminarPrograma(criteria);
			return true;
		}
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

		MantenimientoPEJProgramaEjecutivasService servicepej = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		Map result = servicepej.getPeriodoDefault();

		String codigoPeriodo = "";
		try {
			codigoPeriodo = (String) result.get("codigoPeriodo");
		} catch (Exception e) {
			throw new Exception(
					this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.errorNoExisteCampanaDefaut"));
		}

		// Obtener valor de los check
		// Activar Cobranza
		if (this.indActCobranza)
			f.setActCobranza(Constants.IND_CHECK_ON);
		else
			f.setActCobranza(Constants.IND_CHECK_OFF);
		// Incluir Consultoras Programa Reconocimiento
		if (this.indProgramaRecono)
			f.setProgramaRecono(Constants.IND_CHECK_ON);
		else
			f.setProgramaRecono(Constants.IND_CHECK_OFF);
		// Indicador Feriado
		if (this.indIndicadorFeriado)
			f.setIndicadorFeriado(Constants.IND_CHECK_ON);
		else
			f.setIndicadorFeriado(Constants.IND_CHECK_OFF);
		// Activar Inscripcion Portal FFVV
		if (this.indInsPortalFFVV)
			f.setInsPortalFFVV(Constants.IND_CHECK_ON);
		else
			f.setInsPortalFFVV(Constants.IND_CHECK_OFF);
		// Activar Inscripcion sistema Comercial
		if (this.indInsSistCome)
			f.setInsSistCome(Constants.IND_CHECK_ON);
		else
			f.setInsSistCome(Constants.IND_CHECK_OFF);
		// Solo Posible Lideres
		if (this.indInsPosiLider)
			f.setInsPosiLider(Constants.IND_CHECK_ON);
		else
			f.setInsPosiLider(Constants.IND_CHECK_OFF);
		// Exigencia Pedidos WEB
		if (this.indPedidoWeb)
			f.setPedidoWeb(Constants.STO_ORIGEN_WEB);
		else
			f.setPedidoWeb(null);
		// pedido personal
		if (this.indExigPedPersona)
			f.setExigPedPersonaTmp(Constants.IND_CHECK_ON);
		else
			f.setExigPedPersonaTmp(Constants.IND_CHECK_OFF);
		// Curso Cambio Nivel
		if (this.indExigCursoCamb)
			f.setExigCursoCambTmp(Constants.IND_CHECK_ON);
		else
			f.setExigCursoCambTmp(Constants.IND_CHECK_OFF);

		// Gestion Desempeño
		if (this.indConsiderarNuevas)
			f.setIndicadorConsiderarNuevas(Constants.NUMERO_UNO);
		else
			f.setIndicadorConsiderarNuevas(Constants.NUMERO_CERO);

		if (this.indConsiderarMetasIngresos)
			f.setIndicadorConsiderarMetasIngresos(Constants.NUMERO_UNO);
		else
			f.setIndicadorConsiderarMetasIngresos(Constants.NUMERO_CERO);

		if (this.considerarIngresoCapi)
			f.setIndicadorconsiderarCapitalizacion(Constants.NUMERO_UNO);
		else
			f.setIndicadorconsiderarCapitalizacion(Constants.NUMERO_CERO);

		String indicadorGanaciaDePedido = "";
		if (StringUtils.equals(f.getCodTipoComi(),
				Constants.LEC_CODIGO_TIPO_COMISION_MONTO_FIJO))
			indicadorGanaciaDePedido = Constants.NUMERO_UNO;
		else
			indicadorGanaciaDePedido = Constants.NUMERO_CERO;

		ProgramaCorporativo pc = new ProgramaCorporativo();
		BeanUtils.copyProperties(pc, f);
		pc.setProgramaRecono(f.getProgramaRecono());
		pc.setPedidoWeb(f.getPedidoWeb());
		pc.setInsPosiLider(f.getInsPosiLider());
		pc.setActCobranza(f.getActCobranza());
		pc.setInsPortalFFVV(f.getInsPortalFFVV());
		pc.setInsSistCome(f.getInsSistCome());
		pc.setExigCursoCamb(f.getExigCursoCambTmp());
		pc.setExigPedPersona(f.getExigPedPersonaTmp());
		pc.setAuditInfo(usuario.getAuditInfo());
		pc.setNumCampEval(f.getNroAmedirEtapa());
		pc.setIndicadorDespachoCanasta(getIndicadorDespachoCanasta());
		pc.setIndicadorGanaciaDePedido(indicadorGanaciaDePedido);

		// String indicadorActivoNivel =this.indicadorActivoNivel;
		// String indicadorActivoAmbGeoNivel = (String)
		// session.getAttribute("indicadorActivoAmbGeoNivel");
		// String indicadorActivoCobTramo = (String)
		// session.getAttribute("indicadorActivoCobTramo");
		// String indicadorActivoCobAmbGeo = (String)
		// session.getAttribute("indicadorActivoCobAmbGeo");
		// String indicadorActivoCob = (String)
		// session.getAttribute("indicadorActivoCob");
		// String indicadorActivoIncAmbGeo=(String)
		// session.getAttribute("indicadorActivoIncAmbGeo");
		// String indicadorActivoIncNivel = (String)
		// session.getAttribute("indicadorActivoIncNivel");
		// String indicadorActivoIncCanasta = (String)
		// session.getAttribute("indicadorActivoIncCanasta");
		// String indicadorActivoGestDesem = (String)
		// session.getAttribute("indicadorActivoGestDesem");
		// String indicadorActivoBonCampDesem = (String)
		// session.getAttribute("indicadorActivoBonCampDesem");
		// String indicadorActivoBonProduc = (String)
		// session.getAttribute("indicadorActivoBonProduc");
		// String indicadorActivoBonNivel = (String)
		// session.getAttribute("indicadorActivoBonNivel");
		// String indicadorActivoBonCicloVida22 = (String)
		// session.getAttribute("indicadorActivoBonCicloVida22");
		// String indicadorActivoBonCicloVida33 = (String)
		// session.getAttribute("indicadorActivoBonCicloVida33");
		// String indicadorActivoBonCicloVida44 = (String)
		// session.getAttribute("indicadorActivoBonCicloVida44");
		// String indicadorActivoBonNivelAce = (String)
		// session.getAttribute("indicadorActivoBonNivelAce");
		// String indicadorActivoCanasta = (String)
		// session.getAttribute("indicadorActivoCanasta");

		if (f.isNewRecord()) {
			Map criteria = new HashMap();
			criteria.put("periodoInicio", f.getPeriodoInicio());
			criteria.put("periodoFin", null);
			if (!StringUtils.isBlank(f.getPeriodoFin()))
				criteria.put("periodoFin", f.getPeriodoFin());

			Integer indVerificarCruce = service
					.verificarProgramaCruce(criteria);
			if (indVerificarCruce == null || indVerificarCruce == 0) {

				// INSERTAR PROGRAMA CORPORATIVO
				String codigoPrograma = service
						.getNextCodigoProgramaCorporativo();
				pc.setCodigoPrograma(codigoPrograma);
				this.setValidar(pais, pc, f, service);
				service.insertProgramaCorporativo(pc);

				// INSERTAR PROGRAMA NIVEL
				this.agregarNiveles(pais, usuario, pc, f, service);

				if (!StringUtils.isBlank(f.getPeriodoInicioNivel()))
					this.agregarCampanaExigencia(pais, usuario, f, pc, service,
							"C");
				else
					this.deleteCampanaExigencia(pais, usuario, f, pc, service,
							"C");

				this.agregarNivAmbitoGeografico(pais, usuario, codigoPeriodo,
						pc, f, service);

				if (!StringUtils.isBlank(f.getCampannaInicialNivel()))
					this.agregarCampanaExigenciaNivel(pais, usuario, f, pc,
							service, "V");
				else
					this.deleteCampanaExigencia(pais, usuario, f, pc, service,
							"V");

				// INSERTAR OBJETIVO COBRANZA
				// INSERTAR lec_progr_cobra_tramo
				this.agregarCobTramo(pais, usuario, pc, f, service);
				// INSERTAR lec_progr_ambit_geogr Y LEC_PROGR_COBRA_OBJET
				this.agregarCobAmbGeo(pais, usuario, codigoPeriodo, f, pc,
						service);
				// INSERTAR LEC_PROGR_COBRA_OBJET_TRAMO
				this.agregarCobObjetivoCobranza(pais, usuario, f, pc, service);

				// INSERTAR OBJETIVO BONO
				// INSERTAR bono campana INSERTAR bono lanzamiento
				this.agregarBonCampDesem(pais, usuario, pc, f, service);

				// INSERTAR BONO LANZA PRODUCTO
				this.agregarBonProduc(pais, usuario, pc, f, service);

				// INSERTAR bono nivel
				this.agregarBonNivel(pais, usuario, pc, f, service);

				// INSERTAR bono nivel retencion
				this.agregarBonNivelRetencion(pais, usuario, pc, f, service);

				// INSERTAR bono nivel acelerado
				this.agregarBonNivelAce(pais, usuario, f, pc, service);

				if (!StringUtils.isBlank(f.getCampannaInicialNivelAcelerado()))
					this.agregarCampanaExigenciaNivelAcelerado(pais, usuario,
							f, pc, service, "N");
				else
					this.deleteCampanaExigencia(pais, usuario, f, pc, service,
							"N");

				// INSERTAR Bono Periodo Gracia
				this.agregarPeriodoGracia(pais, usuario, f, pc, service);

				// INCENTIVOS
				// ambito geografico
				this.agregarIncAmbGeo(pais, usuario, codigoPeriodo, pc, f,
						service);
				// incentico-% comision
				this.agregarIncMontoRecup(pais, usuario, pc, f, service);
				// gestion Desempeńo
				this.agregarGestDesem(pais, usuario, pc, f, service);

				// CANASTA CABECERA Y DETALLE
				this.agregarCanasta(pais, usuario, pc, f, service);

				// INSERTAR lecIncCanasProd
				this.agregarIncCanasta(pais, usuario, pc, f, service);

				// RANKING Y NIVELES
				if (f.isIndicadorEdicionRanking())
					this.agregarRanking(pais, usuario, pc, f, service);
			} else
				throw new Exception(
						this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.errorcampana"));

		} else { // MODIFICAR
			this.setValidar(pais, pc, f, service);

			service.updateProgramaCorporativo(pc);
			this.agregarNiveles(pais, usuario, pc, f, service);

			if (!StringUtils.isBlank(f.getPeriodoInicioNivel())) {
				this.agregarCampanaExigencia(pais, usuario, f, pc, service, "C");
			} else
				this.deleteCampanaExigencia(pais, usuario, f, pc, service, "C");

			this.agregarNivAmbitoGeografico(pais, usuario, codigoPeriodo, pc,
					f, service);
			this.agregarIncAmbGeo(pais, usuario, codigoPeriodo, pc, f, service);

			if (!StringUtils.isBlank(f.getCampannaInicialNivel())) {
				this.agregarCampanaExigenciaNivel(pais, usuario, f, pc,
						service, "V");
			} else
				this.deleteCampanaExigencia(pais, usuario, f, pc, service, "V");

			// OBJETIVO COBRANZA
			// INSERTAR lec_progr_cobra_tramo
			this.agregarCobTramo(pais, usuario, pc, f, service);

			// INSERTAR lec_progr_ambit_geogr Y LEC_PROGR_COBRA_OBJET
			this.agregarCobAmbGeo(pais, usuario, codigoPeriodo, f, pc, service);

			// INSERTAR LEC_PROGR_COBRA_OBJET_TRAMO
			this.agregarCobObjetivoCobranza(pais, usuario, f, pc, service);

			// BONO
			// INSERTAR bono campana INSERTAR bono lanzamiento
			this.agregarBonCampDesem(pais, usuario, pc, f, service);

			// INSERTAR BONO LANZA PRODUCTO
			this.agregarBonProduc(pais, usuario, pc, f, service);

			// INSERTAR bono nivel
			this.agregarBonNivel(pais, usuario, pc, f, service);

			// INSERTAR bono nivel retencion
			this.agregarBonNivelRetencion(pais, usuario, pc, f, service);

			// INSERTAR bono nivel acelerado
			this.agregarBonNivelAce(pais, usuario, f, pc, service);

			if (!StringUtils.isBlank(f.getCampannaInicialNivelAcelerado()))
				this.agregarCampanaExigenciaNivelAcelerado(pais, usuario, f,
						pc, service, "N");
			else
				this.deleteCampanaExigencia(pais, usuario, f, pc, service, "N");

			// INSERTAR Bono Periodo Gracia
			this.agregarPeriodoGracia(pais, usuario, f, pc, service);

			// INCENTIVOS -% comision
			this.agregarIncMontoRecup(pais, usuario, pc, f, service);

			// CANASTA CABECERA Y DETALLE
			this.agregarCanasta(pais, usuario, pc, f, service);

			// INSERTAR lecIncCanasProd
			this.agregarIncCanasta(pais, usuario, pc, f, service);

			// gestion Desempeńo
			if (f.isIndicadorEdicionGestion())
				this.agregarGestDesem(pais, usuario, pc, f, service);

			// RANKING Y NIVELES
			if (f.isIndicadorEdicionRanking())
				this.agregarRanking(pais, usuario, pc, f, service);
		}

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", f.getCodigoPrograma());

		List programaCorporativoList = service
				.getProgramaCorporativoList(criteria);
		this.lecProgramaCorporativoList = programaCorporativoList;
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map elemento = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoLECProgramaCorporativoForm f = new MantenimientoLECProgramaCorporativoForm();
		f.reset();
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		f.setCodigoPais(pais.getCodigo());
		iniciaValores(f);

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			this.mostrarPrimerMensaje = "";
			String id = elemento.get("codigoPrograma").toString();
			this.idCodigoPrograma = id;
			String codigoPais = pais.getCodigo();

			if (id != null && codigoPais != null) {
				this.obtenerRegistro(id, f);
				this.mostrarBotonSave = true;
				GenericoService genericoService = (GenericoService) getBean("genericoService");
				boolean valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indDatosGenerales");
				f.setIndicadorEdicionCabecera(valorIndicador);
				valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indNivel");
				f.setIndicadorEdicionNiveles(valorIndicador);
				valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indCobranza");
				f.setIndicadorEdicionCobranza(valorIndicador);
				valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indBono");
				f.setIndicadorEdicionBono(valorIndicador);
				valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indIncentivo");
				f.setIndicadorEdicionIncentivos(valorIndicador);
				valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indCanasta");
				f.setIndicadorEdicionCanasta(valorIndicador);
				valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indGestion");
				f.setIndicadorEdicionGestion(valorIndicador);
				valorIndicador = this.obtenerIndicadorEdicion(f,
						genericoService, "indRanking");
				f.setIndicadorEdicionRanking(valorIndicador);

				f.setIndicadorMostrarGrabar(true);
				if (!f.isIndicadorEdicionCabecera()
						&& !f.isIndicadorEdicionNiveles()
						&& !f.isIndicadorEdicionCobranza()
						&& !f.isIndicadorEdicionBono()
						&& !f.isIndicadorEdicionIncentivos()
						&& !f.isIndicadorEdicionCanasta()
						&& !f.isIndicadorEdicionGestion()
						&& !f.isIndicadorEdicionRanking())
					f.setIndicadorMostrarGrabar(false);

				// --RANKING --
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma", id);
				limpiarFormRanking(f, service);
				this.lecRankingList = service.getRankingList(criteria);
				this.lecNivelRangoIncenList = service.getNivelRangoIncentivos(criteria);
				this.mostrarData(f);
				f.setNewRecord(false);
			}
		}

		if (StringUtils.equals(this.accion, this.ACCION_CONSULTAR)) {
			this.consultar = true;

			this.mostrarBotonSave = false;
			f.setIndicadorMostrarGrabar(false);
			f.setIndicadorEdicionCabecera(false);
			f.setIndicadorEdicionNiveles(false);
			f.setIndicadorEdicionCobranza(false);
			f.setIndicadorEdicionBono(false);
			f.setIndicadorEdicionIncentivos(false);
			f.setIndicadorEdicionCanasta(false);
			f.setIndicadorEdicionGestion(false);
			f.setIndicadorEdicionRanking(false);
		}

		if (StringUtils.isBlank(this.mostrarPrimerMensaje)) {
			this.mostrarPrimerMensajeBoolean = false;
		} else {
			this.mostrarPrimerMensajeBoolean = true;
		}
		
		MantenimientoPEJProgramaEjecutivasService mantenimientoPEJProgramaEjecutivasService = (MantenimientoPEJProgramaEjecutivasService)getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		Map result = mantenimientoPEJProgramaEjecutivasService.getPeriodoDefault();
		this.codigoPeriodoValidarIncentivo = (String) result.get("codigoPeriodo");
		return f;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoLECProgramaCorporativoSearchForm f = (MantenimientoLECProgramaCorporativoSearchForm) this.formBusqueda;
		f.setCodigoPrograma("");
		f.setDescripcionPrograma("");
		f.setCodigoPais(pais.getCodigo());
		this.find();
	}

	// Inicializa los valores al cargar la pantalla Mant. Programa Corporativo
	// II
	private void iniciaValores(MantenimientoLECProgramaCorporativoForm f)
			throws Exception {
		try {
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
			MantenimientoOCRPedidoControlFacturacionService servicecontrolFacturacion = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			f.setCodigoPrograma("");
			this.consultar = false;
			this.indActCobranza = false;
			this.indProgramaRecono = false;
			this.indIndicadorFeriado = false;
			this.indInsPortalFFVV = false;
			this.indInsSistCome = false;
			this.indInsPosiLider = false;
			this.indPedidoWeb = false;
			this.indExigPedPersona = false;
			this.indExigCursoCamb = false;
			this.showTipoComi = false;
			this.showExCurso = false;
			this.mostrarPrimerMensajeBoolean = false;
			this.showDiasCobranza = false;

			// List listCampania =
			// serviceCra.getPeriodoCronogramaList(DateFormatUtils.format(System.currentTimeMillis(),
			// "yyyy"));
			// session.setAttribute(Constants.LEC_CAMPANIA_LIST, listCampania);
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			String codigoPais = f.getCodigoPais();
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);

			//
			this.lecTipoComiList = service.getTipoComision(criteria);

			// Nivel
			this.lecProgramaCorporativoNivelList = new ArrayList();
			this.lecTipoNivelList = service.getTipoNivelList(criteria);
			this.lecNivelCorporativoList = new ArrayList();
			this.lecRetencionList = new ArrayList();
			this.lecProgramaCorporativoNivelListDataModel = new DataTableModel();
			this.cargarIndicadoresTipoMeta();
			// cobranza
			this.siccRegionList = reporteService.getListaGenerico(
					"getRegionesByPais", criteria);
			this.siccZonaList = null;
			this.lecTipoMedicionObjetivoList = service
					.getTipoMediCobList(criteria);
			this.lecSelectTramoList = service.getTipoTramo(criteria);
			this.lecProgramaCorporativoObjCobList = new ArrayList();
			this.lecAmbGeoList = new ArrayList();
			this.lecTramoList = new ArrayList();
			this.lecProgramaCorporativoObjCobTablasAuxiliaresList = new ArrayList();
			this.lecProgramaCorporativoObjCobDataModel = new DataTableModel();
			// Bonos
			this.lecTipoMedicionList = service.getTipoMediList(criteria);
			this.lecBonoNivelCorporativoList = null;
			this.lecCambNivelAvan = new ArrayList();
			this.lecPeriodoGracia = new ArrayList();
			this.lecCampLanzaNivelList = new ArrayList();
			this.lecCampLanzaList = new ArrayList();
			this.lecCampLanzaNivelListModificacion = new ArrayList();
			this.lecCampLanzaNivelListDataModel = new DataTableModel();
			this.lecRetencionDataModel = new DataTableModel();
			this.lecCambNivelAvanDataModel = new DataTableModel();
			this.lecPeriodoGraciaDataModel = new DataTableModel();
			this.cargarTiposBono(service);
			this.cargarTiposPremiacion(service);
			this.lecBonoNivelCorporativoList = ajax.getLoadTipoBonoLanzamiento(
					f.getCodigoPais(), "01");

			// Incentivos
			this.lecIncTipoComiList = new ArrayList();
			this.lecIncCanasProd = new ArrayList();
			this.lecCanasProdList = new ArrayList();
			this.lecCanastaList = new ArrayList();
			this.lecProductosCanastaList = new ArrayList();
			this.lecIncMontoRecuList = new ArrayList();
			this.lecIncTipoComiDataModel = new DataTableModel();
			this.lecIncMontoRecuDataModel = new DataTableModel();
			this.lecIncCanasProdDataModel = new DataTableModel();
			this.cargarTiposCanasta(service);
			this.lecNivelRangoIncenList = new ArrayList();

			// Canasta
			List tiposOferta = service.getTipoOfertaList();
			this.lecTiposOfertaProductoCanasta = tiposOferta;
			f.setCodigoFormaPagoProductoCanasta(Constants.LEC_CODIGO_FORMA_PAGO_PRODUCTO_CANASTA);
			Map criteriaCampanya = new HashMap();
			criteriaCampanya.put("codigoPais", codigoPais);
			criteriaCampanya.put("estadoCampanha", "0");
			criteriaCampanya.put("indicadorActiva", "1");
			List listaCampanyas = servicecontrolFacturacion
					.getCampanhasActivasByCriteria(criteriaCampanya);
			if (listaCampanyas.size() == 1) {
				f.setCampanyaRegistro((String) listaCampanyas.get(0));
				f.setCampanyaActivacionCanasta((String) listaCampanyas.get(0));
				f.setCampanyaActivacionCanastaIncentivos((String) listaCampanyas
						.get(0));
			}
			this.lecProductosCanastaDataModel = new DataTableModel();
			this.lecCanastaDataModel = new DataTableModel();

			// Gestion D
			this.lecListaTipoDesem = service.getTipoDesempenioList(criteria);
			this.campanaEvaluacionLec = service.getEtapCampEval(criteria);
			this.selecCampanaEvaluarLec = new ArrayList();
			// this.listaCampanias = new DualListModel<Base>(new ArrayList(),
			// new ArrayList()); //(*/this.campanaEvaluacionLec,
			// this.selecCampanaEvaluarLec);
			this.lecProgramaCorporativoGestDesemList = new ArrayList();
			this.lecProgramaCorporativoGestDesemDataModel = new DataTableModel();
			f.setIndicadorConsiderarNuevas(Constants.NUMERO_CERO);
			this.indConsiderarNuevas = false;
			this.showExitosasDesempenio = false;

			f.setIndicadorConsiderarMetasIngresos(Constants.NUMERO_CERO);
			this.indConsiderarMetasIngresos = false;
			this.considerarIngresoCapi = false;

			// Ranking
			limpiarFormRanking(f, service);
			f.setCodCamPerGrac(Constants.NUMERO_CERO);
			f.setIndExigenciaPedidoWeb(Constants.NUMERO_CERO);
			f.setIndGananciaLiderNueva(Constants.NUMERO_CERO);
			f.setIndicadorFeriado(Constants.NUMERO_CERO);

			// Ambito Nivel--Popup
			this.lecNivelAmbGeoList = new ArrayList();
			this.lecNivelAmbGeoDataModel = new DataTableModel();

			// Ambito Pedido--Popup
			this.lecPedLidAmbGeoList = new ArrayList();
			this.lecPedLidAmbGeoDataModel = new DataTableModel();

			// Setear Valores
			f.setIndicadorEdicionCabecera(true);
			f.setIndicadorEdicionNiveles(true);
			f.setIndicadorEdicionCobranza(true);
			f.setIndicadorEdicionBono(true);
			f.setIndicadorEdicionIncentivos(true);
			f.setIndicadorEdicionCanasta(true);
			f.setIndicadorEdicionGestion(true);
			f.setIndicadorEdicionRanking(true);
			f.setCodCamPerGrac(Constants.NUMERO_CERO);

			f.setMostrarBonoCicloVida(Constants.NUMERO_CERO);
			f.setMostrarBonoLanzamiento(Constants.NUMERO_CERO);
			f.setMostrarCambioNivelAcelerado(Constants.NUMERO_CERO);
			f.setMostrarPeriodoGraciaNueva(Constants.NUMERO_CERO);

			this.nivlimpiarFormNivel(f);
			this.nivlimpiarFormCobranza(f);
			this.nivlimpiarFormBono(f);
			this.nivlimpiarFormIncentivos(f);
			this.nivlimpiarFormGDesempenno(f);

			f.setCodigoTipoGeo("P");
			f.setProgramaReconoTmp("");
			f.setPedidoWebTmp("");
			f.setInsPosiLiderTmp("");
			f.setActCobranzaTmp("");
			f.setInsPortalFFVVTmp("");
			f.setInsSistComeTmp("");
			f.setExigPedPersonaTmp("");
			f.setExigCursoCambTmp("");
			f.setPeriodoInicioNivel("");
			f.setPeriodoFinNivel("");

			f.setExigCursoCamb("");
			f.setCodTipoComi("");
			f.setCampannaInicialNivelAcelerado("");
			f.setCampannaFinalNivelAcelerado("");
			f.setCampannaInicialNivel("");
			f.setCampannaFinalNivel("");
			this.mostrarPrimerMensaje = "";

			f.setIndicadorMostrarGrabar(true);
			Map criteriaVerificar = new HashMap();
			criteriaVerificar.put("codigoPais", codigoPais);
			this.mostrarBotonSave = true;
			Integer verificarProgramaActivo = service
					.getListaProgramasActivosCampanna(criteriaVerificar);
			if (verificarProgramaActivo.intValue() > 0) {
				this.mostrarBotonSave = false;
				f.setIndicadorMostrarGrabar(false);
				// throw new Exception()
				String mensaje = this
						.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.programaActivoCampannaFinNulo");
				this.mostrarPrimerMensaje = mensaje;
				this.addError(
						"Error : ",
						this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.programaActivoCampannaFinNulo"));
			}
			this.indicadorActivoCob = Constants.NUMERO_CERO;
			Integer menorPesoNivel = service.getMenorPesoNivel(null);
			if (menorPesoNivel == null)
				menorPesoNivel = new Integer(0);
			f.setMenorPesoNivel(menorPesoNivel);

			// --RANKING --
			limpiarFormRanking(f, service);
			// -- --
			f.setCodCamPerGrac(Constants.NUMERO_CERO);

			cargarTiposBono(service);
			cargarTiposPremiacion(service);
			cargarTiposCanasta(service);
			cargarIndicadoresTipoMeta();
			this.listaEliminaCobranzaTemp = new ArrayList();

			//
			// String[] valorVacio = {};
			// f.setListaGrillaNivelCampo01(valorVacio);
			// f.setListaGrillaNivelCampo02(valorVacio);
			// f.setListaGrillaNivelCampo03(valorVacio);
			// f.setListaGrillaNivelCampo04(valorVacio);
			// f.setListaGrillaNivelCampo05(valorVacio);
			//
			// f.setListaGrillaCobranzaCampo01(valorVacio);
			// f.setListaGrillaCobranzaCampo02(valorVacio);
			// f.setListaGrillaCobranzaCampo03(valorVacio);
			// f.setListaGrillaCobranzaCampo04(valorVacio);
			//
			// f.setListaGrillaBonoRetencion01(valorVacio);
			// f.setListaGrillaBonoRetencion02(valorVacio);
			// f.setListaGrillaBonoRetencion03(valorVacio);
			// f.setListaGrillaNivelAceleradoCampo01(valorVacio);
			// f.setListaGrillaNivelAceleradoCampo02(valorVacio);
			// f.setListaGrillaNivelAceleradoCampo03(valorVacio);
			//
			// f.setListaGrillaNBonoLanzamientoCampo01(valorVacio);
			// f.setListaGrillaNBonoLanzamientoCampo02(valorVacio);
			//
			// f.setListaGrillaIncentivo1Campo01(valorVacio);
			// f.setListaGrillaIncentivo1Campo02(valorVacio);
			// f.setListaGrillaIncentivo1Campo03(valorVacio);
			// f.setListaGrillaIncentivo3Campo01(valorVacio);
			// f.setListaGrillaIncentivo3Campo02(valorVacio);
			// f.setListaGrillaIncentivo3Campo03(valorVacio);
			//
			// f.setListaGrillaDesempenioCampo01(valorVacio);
			// f.setListaGrillaDesempenioCampo02(valorVacio);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Inserta registro a la grilla -tab NIVEL
	public void guardarAmbitoNivel(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			List list = this.lecProgramaCorporativoNivelList;
			List list1 = this.lecNivelCorporativoList;
			if (validarAmbitoNivel(f))
				return;

			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			Nivel nivel = new Nivel();
			BeanUtils.copyProperties(nivel, f);

			String verificar = verificarListaNivel(list, nivel);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));

			Base b = new Base();
			list.add(nivel);
			b.setCodigo(nivel.getCodigoNivel());
			b.setDescripcion(nivel.getNivel());
			list1.add(b);
			this.indicadorActivoNivel = Constants.NUMERO_UNO;

			validarMontoVentaNivel(list, true);
			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));

			this.lecProgramaCorporativoNivelList = list;
			this.lecProgramaCorporativoNivelListDataModel = new DataTableModel(
					this.lecProgramaCorporativoNivelList);
			this.lecNivelCorporativoList = list1;
			nivlimpiarFormNivel(f);
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			this.addError("Error: ", error);
		}
	}

	// Eliminar registro de la grilla -tab Nivel
	public void eliminarAmbitoNivel(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (this.beanLecProgramaCorporativoNivel == null) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}
			Nivel beanNivel = (Nivel) this.beanLecProgramaCorporativoNivel;
			String codigo = beanNivel.getCodigoNivel();
			int index = -1;
			for (int i = 0; i < this.lecProgramaCorporativoNivelList.size(); i++) {
				Nivel listaNivel = new Nivel();
				listaNivel = (Nivel) this.lecProgramaCorporativoNivelList
						.get(i);
				if (StringUtils.equals(listaNivel.getCodigoNivel(), codigo)) {
					index = i;
					break;
				}
			}

			List list = this.lecProgramaCorporativoNivelList;
			Nivel nivel = (Nivel) list.get(index);
			String mensajeKey = verificarNivelUsado(nivel.getNivel());

			if (StringUtils.isNotBlank(mensajeKey))
				throw new Exception(this.getResourceMessage(mensajeKey));

			List nuevaLista = this.eliminarObjetosSeleccionados(index, list);
			this.lecProgramaCorporativoNivelList = nuevaLista;
			this.lecProgramaCorporativoNivelListDataModel = new DataTableModel(
					this.lecProgramaCorporativoNivelList);
			nivlimpiarFormNivel(f);
			this.indicadorActivoNivel = Constants.NUMERO_UNO;

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Realiza Validaciones previas antes de insertar datos a la grilla -Tab
	// Niveles
	private Boolean validarAmbitoNivel(MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isNotBlank(f.getCampannaInicialNivel())
				&& StringUtils.isNotBlank(f.getCampannaFinalNivel())) {
			int codperini = Integer.parseInt(f.getCampannaInicialNivel());
			int codperfin = Integer.parseInt(f.getCampannaFinalNivel());
			if (codperfin < codperini) {
				this.setMensajeAlertaDefault("Campaña Final Niveles debe ser mayor o igual a la Campaña Inicio Niveles");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			}
		}
		if (StringUtils.isBlank(f.getCodigoNivel())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}
		if (StringUtils.isBlank(f.getNroPedidoInicial())) {
			this.setMensajeAlertaDefault("Número Pedido Inicial: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}
		if (StringUtils.isBlank(f.getNroPedidoFinal())) {
			this.setMensajeAlertaDefault("Número Pedido Final: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}
		if (StringUtils.isBlank(f.getTolePedido())) {
			this.setMensajeAlertaDefault("Número Pedido Tolerancia: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		// RCR PER-SICC-2015-0429
		if (StringUtils.isBlank(f.getIngresoMeta())) {
			this.setMensajeAlertaDefault(this
					.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.ingresoMeta"));
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		} else {
			int ingresoMeta = Integer.parseInt(f.getIngresoMeta());
			if (ingresoMeta < 0) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.ingresoMeta"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			}
		}

		// PER-SiCC-2015-0548
		if (StringUtils.isBlank(f.getMetaCapitalizacion())) {
			this.setMensajeAlertaDefault(this
					.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.metaCapitalizacion"));
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		} else {
			int metaCapitalizacion = Integer
					.parseInt(f.getMetaCapitalizacion());
			if (metaCapitalizacion < 0) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.metaCapitalizacion"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			} else {
				if (metaCapitalizacion > 0) {
					String condCapitalizacion = f.getCondCapitalizacion();
					if (condCapitalizacion.equals("0")) {
						this.setMensajeAlertaDefault(this
								.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.condCapitalizacion"));
						String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						return true;
					}
				}
			}
		}

		for (int i = 0; i < this.lecTipoNivelList.size(); i++) {
			Base baseBuscar = new Base();
			baseBuscar = (Base) this.lecTipoNivelList.get(i);
			if (StringUtils.equals(baseBuscar.getCodigo(), f.getCodigoNivel())) {
				f.setNivel(baseBuscar.getDescripcion());
				break;
			}
		}
		return false;

	}

	// Metodos utilizados por el pickList
	public void ordenarDerecha(ActionEvent event) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		String[] val = f.getListaCampanias();
		List listNoAsignados = this.campanaEvaluacionLec;
		List listAsignados = this.selecCampanaEvaluarLec;
		List aux1 = new ArrayList();
		for (int i = 0; i < listNoAsignados.size(); i++) {
			Base aux = (Base) listNoAsignados.get(i);
			for (int j = 0; j < val.length; j++) {
				if (val[j].equalsIgnoreCase(aux.getCodigo())) {
					Base b = new Base();
					b.setCodigo(aux.getCodigo());
					b.setDescripcion(aux.getDescripcion());
					listAsignados.add(b);
				}
			}
		}

		for (int i = 0; i < listNoAsignados.size(); i++) {
			Base aux = (Base) listNoAsignados.get(i);
			for (int j = 0; j < val.length; j++) {
				if (val[j].equalsIgnoreCase(aux.getCodigo())) {
					aux1.add(aux);
				}
			}
		}

		this.campanaEvaluacionLec.removeAll(aux1);
	}

	public void ordenarIzquierda(ActionEvent event) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		String[] val = f.getListaCampaniaEvaluar();
		List listNoAsignados = this.campanaEvaluacionLec;
		List listAsignados = this.selecCampanaEvaluarLec;
		List aux1 = new ArrayList();
		for (int i = 0; i < listAsignados.size(); i++) {
			Base aux = (Base) listAsignados.get(i);
			for (int j = 0; j < val.length; j++) {
				if (val[j].equalsIgnoreCase(aux.getCodigo())) {
					Base b = new Base();
					b.setCodigo(aux.getCodigo());
					b.setDescripcion(aux.getDescripcion());
					listNoAsignados.add(b);
				}
			}
		}

		for (int i = 0; i < listAsignados.size(); i++) {
			Base aux = (Base) listAsignados.get(i);
			for (int j = 0; j < val.length; j++) {
				if (val[j].equalsIgnoreCase(aux.getCodigo())) {
					aux1.add(aux);
				}
			}
		}

		this.selecCampanaEvaluarLec.removeAll(aux1);
	}

	// FIN Metodos utilizados por el pickList

	// Elimina objetos en base a sus indices
	private List eliminarObjetosSeleccionados(int indexEliminar,
			List listaFuente) {
		List listaRetorno = new ArrayList();
		if (listaFuente != null) {
			listaFuente.set(indexEliminar, null);

			for (int i = 0; i < listaFuente.size(); i++) {
				if (listaFuente.get(i) != null)
					listaRetorno.add(listaFuente.get(i));
			}
		}

		return listaRetorno;
	}

	private void validarMontoVentaNivel(List list, boolean eliminarErroneo)
			throws Exception {
		if (list == null)
			return;

		boolean flagPermitirCeros = true;

		for (int i = 0; i < list.size(); i++) {
			Nivel nivel = (Nivel) list.get(i);

			// Solo se permiten ceros hasta que uno de ellos sea diferente de
			// cero de ahi para adelante ya no se permiten zeros
			double maximo = Double
					.parseDouble(StringUtils.isBlank(nivel
							.getMontoVentaMaximo()) ? "0" : nivel
							.getMontoVentaMaximo());
			double minimo = Double
					.parseDouble(StringUtils.isBlank(nivel
							.getMontoVentaMinimo()) ? "0" : nivel
							.getMontoVentaMinimo());

			if (!flagPermitirCeros && (maximo == 0 || minimo == 0)) {
				String mensaje = "";

				if (eliminarErroneo) {
					list.remove(i);
					mensaje = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.montoCero");
				} else
					mensaje = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro")
							+ " "
							+ (i + 1)
							+ ": "
							+ this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.montoCero");
				throw new Exception(mensaje);
			}
			if (maximo > 0 || minimo > 0)
				flagPermitirCeros = false;
		}

	}

	// Verifica si el Nivel se encuentra en la Lista
	private String verificarListaNivel(List lista, Nivel nivel) {
		for (int i = 0; i < lista.size(); i++) {
			Nivel p = (Nivel) lista.get(i);
			if (p.getCodigoNivel().equals(nivel.getCodigoNivel())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelYaExiste";
				return proceso;
			}
		}
		return null;
	}

	// Actualiza la lista del Tipo Tab nivel -tab Nivel
	// private String nivActualizarNivel(List lista, Nivel nivel, String codigo)
	// {
	// for (int i = 0; i < lista.size(); i++) {
	// Nivel p = (Nivel) lista.get(i);
	// if (i == (int) Integer.parseInt(codigo)) {
	// if (!p.getCodigoNivel().equals(nivel.getCodigoNivel())) {
	// String verificar = verificarListaNivel(lista, nivel);
	// if (StringUtils.isNotBlank(verificar))
	// return verificar;
	// }
	// lista.set(i, nivel);
	// break;
	// }
	// }
	// return null;
	// }

	// Limpia los cambios del -Tab Nivel
	private void nivlimpiarFormNivel(MantenimientoLECProgramaCorporativoForm f) {
		f.setNroPedidoInicial("");
		f.setNroPedidoFinal("");
		f.setTolePedido("");
		f.setNivel("");
		f.setMontoVentaMinimo("");
		f.setMontoVentaMaximo("");
		f.setIndicadorTipoMeta("");
		f.setMontoVentaTolerancia("");
		this.cargarIndicadoresTipoMeta();
		f.setSelectedItemsNivel(new String[] {});
	}

	// Inserta registros en la grilla -Tab COBRANZA
	public void guardarAmbitoCobranza(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoCobranza(f))
				return;

			this.guardarMantenerTramos();
			this.guardarMantenerAmbitGeogr();

			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

			List list = this.lecProgramaCorporativoObjCobList;
			List list2 = this.lecAmbGeoList;

			// this.actualizaListaGrillaCobranza(request, f, list);

			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList();
			}
			ObjetivoCobranza oc = new ObjetivoCobranza();
			BeanUtils.copyProperties(oc, f);

			String temp;
			AmbitoGeografico ag;
			for (int i = 0; i < f.getSelectedItemsAmbGeo().length; i++) {
				oc = new ObjetivoCobranza();
				temp = f.getSelectedItemsAmbGeo()[i];
				for (int j = 0; j < list2.size(); j++) {
					ag = new AmbitoGeografico();
					ag = (AmbitoGeografico) list2.get(j);
					if (ag.getCodigoAmbGeo().compareTo(temp) == 0) {
						oc.setNumAmbGeo(ag.getCodigoAmbGeo());
						oc.setCodigoPais(ag.getCodigoPais());
						oc.setCodRegion(ag.getCodigoRegion());
						oc.setCodZona(ag.getCodigoZona());
						if (StringUtils.isBlank(ag.getRegion())
								&& StringUtils.isBlank(ag.getZona())) {
							oc.setAmbitoGeogra(ag.getPais());
							oc.setEstado("P"); // pais
						}
						if (!StringUtils.isBlank(ag.getRegion())
								&& StringUtils.isBlank(ag.getZona())) {
							oc.setAmbitoGeogra(ag.getRegion());
							oc.setEstado("R"); // region
						}
						if (!StringUtils.isBlank(ag.getZona())) {
							oc.setAmbitoGeogra(ag.getZona());
							oc.setEstado("Z"); // region
						}
					}
				}
				oc.setDescTipoMedi(f.getCodigoTipoMedi() + " . "
						+ f.getTipoMedicion());
				oc.setCodTipoMedi(f.getCodigoTipoMedi());
				oc.setNroTramo(f.getCodigoTramo());
				oc.setNroDias(f.getNroDias());
				oc.setNroDiasExtras(f.getNroDiasExtra());
				oc.setPorcCob(f.getPorcCob());
				oc.setProcMinCob(f.getProcMinCob());

				String verificar = this
						.verificarListaObjetivoCobranza(list, oc);
				if (StringUtils.isNotBlank(verificar))
					throw new Exception(this.getResourceMessage(verificar));
				list.add(oc);
			}
			this.addInfo(
					"Info:",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			this.lecProgramaCorporativoObjCobList = list;
			this.lecProgramaCorporativoObjCobDataModel = new DataTableModel(
					this.lecProgramaCorporativoObjCobList);
			this.indicadorActivoCob = Constants.NUMERO_UNO;
			this.nivlimpiarFormCobranza(f);
			f.setCodigoTipoGeo("P");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina registros en la grilla -Tab COBRANZA
	public void eliminarAmbitoCobranza(ActionEvent actionEvent) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

			String ids[] = f.getSelectedItemsObjCob();
			List list = this.lecProgramaCorporativoObjCobList;

			ObjetivoCobranza beanObjCobra = (ObjetivoCobranza) this.beanLecProgramaCorporativoObjCob;
			this.listaEliminaCobranzaTemp.add(beanObjCobra);
			int index = -1;
			for (int i = 0; i < list.size(); i++) {
				ObjetivoCobranza listaObjeto = new ObjetivoCobranza();
				listaObjeto = (ObjetivoCobranza) list.get(i);
				if (StringUtils.equals(listaObjeto.getNroTramo(),
						beanObjCobra.getNroTramo())
						&& StringUtils.equals(listaObjeto.getCodTipoMedi(),
								beanObjCobra.getCodTipoMedi())
						&& StringUtils.equals(listaObjeto.getCodRegion(),
								beanObjCobra.getCodRegion())
						&& StringUtils.equals(listaObjeto.getCodZona(),
								beanObjCobra.getCodZona())) {
					index = i;
					break;
				}
			}
			// this.actualizaListaGrillaCobranza(request, f, list);

			Map criteria = new HashMap();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ObjetivoCobranza oc = (ObjetivoCobranza) list.get(i);
					criteria.put("codigoPais", f.getCodigoPais());
					criteria.put("codigoPrograma", f.getCodigoPrograma());
					criteria.put("codTipoMedi", oc.getCodTipoMedi());
					List listCobObj = service.getObjetivoCobranzaList(criteria);
					List listCobObjTramo = service
							.getTramoObjetivoCobranzaList(criteria);
				}
			}
			List nuevaLista = this.eliminarObjetosSeleccionados(index, list);
			this.lecProgramaCorporativoObjCobList = nuevaLista;
			this.lecProgramaCorporativoObjCobDataModel = new DataTableModel(
					this.lecProgramaCorporativoObjCobList);

			this.objcEliminarAlcance();
			this.objcEliminarTramo();
			this.indicadorActivoCob = Constants.NUMERO_UNO;
			this.nivlimpiarFormCobranza(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/** * Elimina registros de Lista de Tramos */

	public void objcEliminarTramo() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'objcEliminarTramo'");
		}
		List listaFuente = this.lecTramoList;
		List listaCobranza = this.lecProgramaCorporativoObjCobList;

		List listaRetorno = new ArrayList();
		for (int j = 0; j < listaFuente.size(); j++) {
			CobranzaTramo ct = (CobranzaTramo) listaFuente.get(j);
			for (int i = 0; i < listaCobranza.size(); i++) {
				ObjetivoCobranza objetivoCobranza = (ObjetivoCobranza) listaCobranza
						.get(i);
				if (ct.getCodTramo().equals(objetivoCobranza.getNroTramo())) {
					boolean insertar = true;

					for (int x = 0; x < listaRetorno.size(); x++) {
						CobranzaTramo temp = (CobranzaTramo) listaFuente.get(x);
						if (temp.getCodTramo().equals(ct.getCodTramo())) {
							insertar = false;
						}
					}
					if (insertar)
						listaRetorno.add(ct);

				}
			}
		}
		this.lecTramoList = listaRetorno;
		return;
	}

	public void showRegionZonaCobranza(ValueChangeEvent val) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		this.showRegionCobranza = false;
		this.showZonaCobranza = false;
		String valor = val.getNewValue().toString();
		if (StringUtils.isNotBlank(valor)) {
			if (StringUtils.equals(valor, "P")) {
				this.showRegionCobranza = false;
				this.showZonaCobranza = false;
				f.setRegionObjCob(new String[] {});
				f.setZonaObjCob(new String[] {});
			}
			if (StringUtils.equals(valor, "R")) {
				this.showRegionCobranza = true;
				this.showZonaCobranza = false;
				f.setZonaObjCob(new String[] {});
				this.siccZonaList = null;
			}
			if (StringUtils.equals(valor, "Z")) {
				this.showRegionCobranza = true;
				this.showZonaCobranza = true;
			}

		}
	}

	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas ");
		log.debug("val: " + val.getNewValue().toString());

		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajax.getloadZonasDisponibles2(
					f.getCodigoPais(), regiones, "", "T");
			f.setZonaObjCob(new String[] {});
		} else {
			this.siccZonaList = null;
			f.setZonaObjCob(new String[] {});
		}
	}

	public void showDiasCobranza(ValueChangeEvent val) {
		this.showDiasCobranza = false;
		String valor = val.getNewValue().toString();
		if (StringUtils.isNotBlank(valor)) {
			if (StringUtils.equals(valor, "02"))
				this.showDiasCobranza = true;
			else
				this.showDiasCobranza = false;

		}
	}

	public void guardarMantenerAmbitGeogr() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'guardarMantenerAmbitGeogr'");
		}
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;

		List listAmbitoGeografico = new ArrayList();
		List list = this.lecAmbGeoList;

		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList();
		}
		AmbitoGeografico toc;
		String[] seleccionadas = {};
		String[] object = {};
		String[] subobject0 = {};
		String[] subobject1 = {};
		if (f.getCodigoTipoGeo().compareTo("R") == 0) {
			seleccionadas = StringUtils.split(f.getRegion(), ";");
			for (int i = 0; i < seleccionadas.length; i++) {
				toc = new AmbitoGeografico();
				BeanUtils.copyProperties(toc, f);
				object = StringUtils.split(seleccionadas[i], ",");
				toc.setPais(f.getCodigoPais());
				toc.setCodigoRegion(object[0]);
				toc.setRegion(object[1]);
				toc.setPais(f.getCodigoPais());
				toc.setZona("");
				toc.setCodigoZona("");
				toc.setIndPais("0");
				toc.setCodigoAmbGeo(object[0]);
				listAmbitoGeografico.add(toc);
				String verificar = this.verificarListaAmbitoCobranza(list, toc);
				if (StringUtils.isBlank(verificar)) {
					list.add(toc);
				}

			}
		}
		if (f.getCodigoTipoGeo().compareTo("Z") == 0) {
			seleccionadas = StringUtils.split(f.getZona(), ";");
			for (int i = 0; i < seleccionadas.length; i++) {

				toc = new AmbitoGeografico();
				toc.setPais(f.getCodigoPais());
				BeanUtils.copyProperties(toc, f);

				object = StringUtils.split(seleccionadas[i], ",");

				toc.setCodigoZona(object[0]);
				toc.setZona(object[1]);
				toc.setPais(f.getCodigoPais());
				toc.setIndPais("0");
				toc.setCodigoAmbGeo(object[0]);

				Map criteriaZona = new HashMap();
				criteriaZona.put("codigoZona", object[0]);
				Map resultado = service.getRegionByZona(criteriaZona);
				String codigoRegion = (String) resultado.get("codigoRegion");
				String descripcionRegion = (String) resultado
						.get("descripcionRegion");
				toc.setCodigoRegion(codigoRegion);
				toc.setRegion(descripcionRegion);
				listAmbitoGeografico.add(toc);
				String verificar = this.verificarListaAmbitoCobranza(list, toc);
				if (StringUtils.isBlank(verificar)) {
					list.add(toc);
				}
			}
		}
		if (f.getCodigoTipoGeo().compareTo("P") == 0) {
			toc = new AmbitoGeografico();
			BeanUtils.copyProperties(toc, f);
			toc.setPais(f.getCodigoPais());
			toc.setCodigoRegion("");
			toc.setRegion("");
			toc.setPais(f.getCodigoPais());
			toc.setZona("");
			toc.setIndPais("1");
			toc.setCodigoAmbGeo(f.getCodigoPais());
			toc.setCodigoZona("");
			listAmbitoGeografico.add(toc);
			String verificar = this.verificarListaAmbitoCobranza(list, toc);
			if (StringUtils.isBlank(verificar)) {
				list.add(toc);
			}

		}

		String[] selectedItemsAmbGeo = new String[listAmbitoGeografico.size()];
		for (int i = 0; i < listAmbitoGeografico.size(); i++) {
			AmbitoGeografico toc1 = (AmbitoGeografico) listAmbitoGeografico
					.get(i);
			selectedItemsAmbGeo[i] = toc1.getCodigoAmbGeo();
		}
		f.setSelectedItemsAmbGeo(selectedItemsAmbGeo);
		this.lecAmbGeoList = list;
		return;
	}

	/**
	 * Grabando en la lista de Tramos de Cobranza
	 */

	public void guardarMantenerTramos() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'guardarMantenerTramos'");
		}
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		List list = this.lecTramoList;
		String codigoTramo = f.getCodigoTramo();

		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList();
			CobranzaTramo ct = new CobranzaTramo();
			ct.setNumTramo(codigoTramo);
			ct.setCodTramo(codigoTramo);
			ct.setDescripcion("TRAMO " + codigoTramo);
			list.add(ct);
		} else {
			boolean encontrado = false;
			for (int i = 0; i < list.size(); i++) {
				CobranzaTramo temp = (CobranzaTramo) list.get(i);
				if (temp.getCodTramo().equals(codigoTramo)) {
					encontrado = true;
					break;
				}
			}
			if (!encontrado) {
				CobranzaTramo ct = new CobranzaTramo();
				ct.setNumTramo(codigoTramo);
				ct.setCodTramo(codigoTramo);
				ct.setDescripcion("TRAMO " + codigoTramo);
				ct.setNroDias(f.getNroDias());
				ct.setDiasExtra(f.getNroDiasExtra());
				list.add(ct);
			}
		}
		this.lecTramoList = list;
		return;
	}

	/** * Verifica si esta repetido en Lista de Objetivo Cobranza */

	private String verificarListaObjetivoCobranza(List lista,
			ObjetivoCobranza objetivoCobranza) {
		for (int i = 0; i < lista.size(); i++) {
			ObjetivoCobranza p = (ObjetivoCobranza) lista.get(i);
			if (p.getNumAmbGeo().equals(objetivoCobranza.getNumAmbGeo())
					&& p.getCodTipoMedi().equals(
							objetivoCobranza.getCodTipoMedi())
					&& p.getNroTramo().equals(objetivoCobranza.getNroTramo())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.objetivoCobranzaYaExiste";
				return proceso;
			}
			if (!(p.getCodTipoMedi().equals(objetivoCobranza.getCodTipoMedi()))) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.objetivoCobranzaTipoMedicion";
				return proceso;
			}
			if (p.getCodTipoMedi().equals("01")
					&& !(objetivoCobranza.getNroTramo().equals("1"))) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.objetivoCobranzaTipoMedicionCierreRegion";
				return proceso;
			}
			/* Verificando el Ambito */
			String estadoAmbito = "P";
			if (StringUtils.isBlank(p.getCodRegion())
					&& StringUtils.isBlank(p.getCodZona())) {
				estadoAmbito = "P";
			} else if (!StringUtils.isBlank(p.getCodRegion())
					&& StringUtils.isBlank(p.getCodZona())) {
				estadoAmbito = "R";
			}
			if (!StringUtils.isBlank(p.getCodZona())) {
				estadoAmbito = "Z";
			}
			/*
			 * if ("P".equals(objetivoCobranza.getEstado())) { if
			 * (StringUtils.equals(objetivoCobranza.getEstado(), p.getEstado()))
			 * { String proceso =
			 * "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaPais"
			 * ; return proceso; } }
			 */

			if ("R".equals(objetivoCobranza.getEstado())) {
				if (estadoAmbito.equals("P")) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaPaisRegion";
					return proceso;
				}
				if (estadoAmbito.equals("Z")
						&& p.getCodRegion().equals(
								objetivoCobranza.getCodRegion())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaRegion";
					return proceso;
				}
			}
			if ("Z".equals(objetivoCobranza.getEstado())) {
				if (estadoAmbito.equals("P")) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaPaisZona";
					return proceso;
				}
				if (estadoAmbito.equals("R")
						&& p.getCodRegion().equals(
								objetivoCobranza.getCodRegion())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaZona";
					return proceso;
				}
			}
		}
		return null;
	}

	/** * Verifica si esta repetido Lista de Ambito */

	private String verificarListaAmbitoCobranza(List lista,
			AmbitoGeografico ambitoGeografico) {
		for (int i = 0; i < lista.size(); i++) {
			AmbitoGeografico p = (AmbitoGeografico) lista.get(i);
			if (p.getCodigoAmbGeo().equals(ambitoGeografico.getCodigoAmbGeo())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.ambitoYaExiste";
				return proceso;
			}
		}
		return null;
	}

	public void objcEliminarAlcance() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'objcEliminarAlcance'");
		}
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		List listaFuente = this.lecAmbGeoList;
		List listaCobranza = this.lecProgramaCorporativoObjCobList;
		List listaRetorno = new ArrayList();
		for (int j = 0; j < listaFuente.size(); j++) {
			AmbitoGeografico ct = (AmbitoGeografico) listaFuente.get(j);
			for (int i = 0; i < listaCobranza.size(); i++) {
				ObjetivoCobranza objetivoCobranza = (ObjetivoCobranza) listaCobranza
						.get(i);
				if (ct.getCodigoAmbGeo()
						.equals(objetivoCobranza.getNumAmbGeo())) {
					boolean insertar = true;

					for (int x = 0; x < listaRetorno.size(); x++) {
						AmbitoGeografico temp = (AmbitoGeografico) listaFuente
								.get(x);
						if (temp.getCodigoAmbGeo().equals(ct.getCodigoAmbGeo())) {
							insertar = false;
						}
					}
					if (insertar)
						listaRetorno.add(ct);
				}
			}
		}
		this.lecAmbGeoList = listaRetorno;
		return;
	}

	private Boolean validarAmbitoCobranza(
			MantenimientoLECProgramaCorporativoForm f) {
		int pivot = 0;
		String tmpregion = "";
		String tmpzona = "";
		List regionlist = this.siccRegionList;
		if (StringUtils.equals(f.getCodigoTipoGeo(), "R")) {
			for (int i = 0; i < f.getRegionObjCob().length; i++) {
				if (StringUtils.isNotBlank(f.getRegionObjCob()[i])) {
					for (int j = 0; j < this.siccRegionList.size(); j++) {
						Base base = new Base();
						base = (Base) this.siccRegionList.get(j);
						if (StringUtils.equals(f.getRegionObjCob()[i],
								base.getCodigo())) {
							tmpregion = tmpregion + f.getRegionObjCob()[i]
									+ "," + base.getDescripcion() + ";";
							pivot = 1;
							break;
						}
					}

				}
			}
			f.setRegion(tmpregion);
		} else if (StringUtils.equals(f.getCodigoTipoGeo(), "Z")) {
			for (int k = 0; k < f.getZonaObjCob().length; k++) {
				if (StringUtils.isNotBlank(f.getZonaObjCob()[k])) {
					for (int i = 0; i < this.siccZonaList.length; i++) {
						if (StringUtils.equals(f.getZonaObjCob()[k],
								this.siccZonaList[i].getValue())) {
							tmpzona = tmpzona + f.getZonaObjCob()[k] + ","
									+ this.siccZonaList[i].getLabel() + ";";
							pivot = 1;
							break;
						}
					}

				}
			}
			f.setZona(tmpzona);
		} else
			pivot = 1;

		if (pivot == 0) {
			this.setMensajeAlertaDefault("Ambito Geográfico: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.equals(f.getCodigoTipoMedi(), "02")) {
			if (StringUtils.isBlank(f.getNroDias())) {
				this.setMensajeAlertaDefault("Nro de Dias: Valor Requerido");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			}
		} else {
			if (StringUtils.isBlank(f.getNroDiasExtra())) {
				this.setMensajeAlertaDefault("Nro de Dias Extra: Valor Requerido");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			}
		}

		if (StringUtils.isBlank(f.getPorcCob())) {
			this.setMensajeAlertaDefault("% Normal: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getProcMinCob())) {
			this.setMensajeAlertaDefault("% Mínimo: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}
		float dporCob = Float.parseFloat(f.getPorcCob());
		float dprocMinCob = Float.parseFloat(f.getProcMinCob());
		if (dporCob < dprocMinCob) {
			this.setMensajeAlertaDefault("Porcentaje Mínimo debe ser menor al Porcentaje Normal");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}
		for (int i = 0; i < this.lecTipoMedicionObjetivoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecTipoMedicionObjetivoList.get(i);
			if (StringUtils.equals(f.getCodigoTipoMedi(), base.getCodigo())) {
				f.setTipoMedicion(base.getDescripcion());
				break;
			}
		}

		return false;
	}

	// Limpia los campos -Tab Cobranzas
	private void nivlimpiarFormCobranza(
			MantenimientoLECProgramaCorporativoForm f) {
		f.setNroDias("");
		f.setNroDiasExtra("");
		f.setPorcCob("");
		f.setProcMinCob("");
		f.setIdModificarNivelTramo(0);
		f.setIndicadorModificarNivelTramo(Constants.NO);
		f.setCodigoTipoGeo("P");
		f.setSelectedItemsTramos(new String[] {});
		f.setSelectedItemsAmbGeo(new String[] {});
		f.setSelectedItemsObjCob(new String[] {});
		f.setRegionObjCob(new String[] {});
		f.setZonaObjCob(new String[] {});
		// this.showDiasCobranza = false;
		this.showRegionCobranza = false;
		this.showZonaCobranza = false;
	}

	// Insertar registros a la grilla tab BONOS--lanzamiento
	public void guardarAmbitoBonosLanzamiento(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoBonoLanzamiento(f))
				return;

			List list = this.lecCampLanzaNivelList;
			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			BonoNivel bononivel;
			bononivel = new BonoNivel();
			bononivel.setCampanaLanzamiento(f.getCampLanzamiento());
			bononivel.setCodTipoMedi(f.getCodTipoMedicion());
			bononivel.setCodigoProducto(f.getCodigoSAProductoLanza());
			bononivel.setCodBonoNivel(f.getCodBonoNivel());
			bononivel.setCodNivel(f.getCodNivelLanzamiento());
			bononivel.setExigencia(f.getExigencia());
			bononivel.setMonPrem(f.getMonPrem());
			bononivel.setNivel(f.getNivelLanzamiento());
			bononivel.setTipoMedi(f.getTipoMedicion());
			bononivel.setBonoNivel(f.getBononivel());
			bononivel.setNroLanzamiento(f.getNroLanzamiento());
			String verificar = this.verificarListaBonoLanzamientoNivel(list,
					bononivel);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));
			list.add(bononivel);

			this.addInfo(
					"Info:",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			this.lecCampLanzaNivelList = list;
			this.lecCampLanzaNivelListDataModel = new DataTableModel(
					this.lecCampLanzaNivelList);
			this.indicadorActivoBonNivel = Constants.NUMERO_UNO;
			this.nivlimpiarFormBono(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Eliminar registros multiples de la Grilla tab BONOS--lanzamiento
	public void eliminarAmbitoBonoLanzamiento(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			List list = this.lecCampLanzaNivelList;
			String[] indexes = new String[beanLecCampLanzaNivelList.length];
			for (int j = 0; j < this.beanLecCampLanzaNivelList.length; j++) {
				BonoNivel beanBonoNivel = (BonoNivel) this.beanLecCampLanzaNivelList[j];
				for (int i = 0; i < list.size(); i++) {
					BonoNivel listaObjeto = new BonoNivel();
					listaObjeto = (BonoNivel) list.get(i);
					if (StringUtils.equals(listaObjeto.getCampanaLanzamiento(),
							beanBonoNivel.getCampanaLanzamiento())
							&& StringUtils.equals(
									listaObjeto.getCodBonoNivel(),
									beanBonoNivel.getCodBonoNivel())
							&& StringUtils.equals(listaObjeto.getCodNivel(),
									beanBonoNivel.getCodNivel())
							&& StringUtils.equals(listaObjeto.getCodTipoMedi(),
									beanBonoNivel.getCodTipoMedi())
							&& StringUtils.equals(
									listaObjeto.getCodigoProducto(),
									beanBonoNivel.getCodigoProducto())) {
						indexes[j] = Integer.toString(i);
						break;
					}
				}
			}
			List nuevaLista = this.eliminarObjetosSeleMultiples(indexes, list);
			this.lecCampLanzaNivelList = nuevaLista;
			this.lecCampLanzaNivelListDataModel = new DataTableModel(
					this.lecCampLanzaNivelList);
			this.nivlimpiarFormBono(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Llena el tipo de Bono por ajax -Tab Bonos Lanzamiento
	public void loadTipoBonoLanzamiento(ValueChangeEvent val) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		String tipo = val.getNewValue().toString();
		if (!val.equals(null)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.lecBonoNivelCorporativoList = ajax.getLoadTipoBonoLanzamiento(
					f.getCodigoPais(), tipo);
			f.setCodBonoNivel(null);

		} else {
			this.lecBonoNivelCorporativoList = null;
			f.setCodBonoNivel(null);
		}
	}

	// Limpia los campos del tab Bonos
	private void nivlimpiarFormBono(MantenimientoLECProgramaCorporativoForm f) {
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		f.setSelerete(new String[] {});
		f.setSeleretenivelAce(new String[] {});
		f.setSeleretenivel(new String[] {});
		f.setSelectedPeriodoGracia(new String[] {});
		f.setNroMinIng("");
		f.setNroMaxIng("");
		f.setPorRetExig("");
		f.setNroCampMax("");
		f.setNroMinIncPed("");
		f.setCampLanzamiento("");
		f.setCodigoSAProductoLanza("");
		f.setExigencia("");
		f.setSobExigencia("");
		f.setNroCampMax("");
		f.setNroMinIncPed("");
		f.setDescripcionProductoLanza("");
		f.setMonPrem("");
		f.setMontoPremioCicloVida("");
		f.setMontoPremioNivelAcelerado("");
		f.setMontoPeriodoGracia("");
		f.setNroMinRete("");
		f.setNroMaxRete("");
		f.setNroMaxIng("");
		this.cargarTiposBono(service);
		this.cargarTiposPremiacion(service);

		if (StringUtils.equals(f.getCodigoTipoPremiacion(),
				Constants.LEC_CODIGO_TIPO_PREMIACION_CANASTA))
			this.habilitaMontoPremio = true;
		else
			this.habilitaMontoPremio = false;

	}

	// popupBonos-Lanzamiento
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.mostrarPopupCliente = true;
		}
		if (accion.equals(this.POPUP_PRODUCTO)) {
			this.mostrarPopupProducto = true;
		}

	}

	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.mostrarPopupProducto = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaProductoSearch.verificarRegistro(event);
			if (this.busquedaProductoSearch.isSeleccionoRegistro()) {
				Map productoBuscar = (Map) this.busquedaProductoSearch
						.getBeanRegistroSeleccionado();
				MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
				String codigo = productoBuscar.get("codigoSap").toString();
				String descri = productoBuscar.get("descripcionCorta")
						.toString();
				f.setCodigoSAProductoLanza(codigo);
				f.setDescripcionProductoLanza(descri);
				this.busquedaProductoSearch.setBeanRegistroSeleccionado(null);
				this.formMantenimiento = f;
			}
		}

		if (accion.equals(this.POPUP_PRODUCTO)) {
			this.busquedaProductoSearch.verificarRegistro(event);
			if (this.busquedaProductoSearch.isSeleccionoRegistro()) {
				Map productoBuscar = (Map) this.busquedaProductoSearch
						.getBeanRegistroSeleccionado();
				MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
				String codigo = productoBuscar.get("codigoSap").toString();
				String descri = productoBuscar.get("descripcionCorta")
						.toString();
				f.setCodigoSAPProductoCanasta(codigo);
				f.setDescripcionProductoCanasta(descri);
				this.busquedaProductoSearch.setBeanRegistroSeleccionado(null);
				this.formMantenimiento = f;
			}
		}

	}

	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaProductoSearch.setBeanRegistroSeleccionado(null);
		if (accion.equals(this.POPUP_PRODUCTO)) {
			this.mostrarPopupProducto = false;
		}

	}

	// Verifica si existe Bono Lanzamiento Nivel en la lista
	private String verificarListaBonoLanzamientoNivel(List lista,
			BonoNivel bonoLanzamiento) throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = new BonoNivel();
			BeanUtils.copyProperties(p, lista.get(i));
			if (StringUtils.equals(p.getCampanaLanzamiento(),
					bonoLanzamiento.getCampanaLanzamiento())
					&& StringUtils.equals(p.getCodBonoNivel(),
							bonoLanzamiento.getCodBonoNivel())
					&& StringUtils.equals(p.getCodNivel(),
							bonoLanzamiento.getCodNivel())
					&& StringUtils.equals(p.getCodTipoMedi(),
							bonoLanzamiento.getCodTipoMedi())
					&& StringUtils.equals(p.getCodigoProducto(),
							bonoLanzamiento.getCodigoProducto())
					&& StringUtils.equals(p.getNroLanzamiento(),
							bonoLanzamiento.getNroLanzamiento())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.bonoLanzamientoNivelYaExiste";
				return proceso;
			}
		}
		return null;
	}

	// Valida los campos antes de insertarlos -Tab Bonos Lanzamiento
	private Boolean validarAmbitoBonoLanzamiento(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCampLanzamiento())) {
			this.setMensajeAlertaDefault("Campaña Lanzamiento: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;

		}

		if (StringUtils.isBlank(f.getPeriodoInicio())) {
			this.setMensajeAlertaDefault("Periodo Inicio: Valores Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;

		}

		int codperini = Integer.parseInt(f.getPeriodoInicio());
		int codpercampa = Integer.parseInt(f.getCampLanzamiento());
		if (codpercampa < codperini) {
			this.setMensajeAlertaDefault("Campaña Lanzamiento debe ser mayor o igual a la Campaña Inicio");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getCodigoSAProductoLanza())) {
			this.setMensajeAlertaDefault("Producto: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getCodBonoNivel())) {
			this.setMensajeAlertaDefault("Bono: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getCodNivelLanzamiento())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getExigencia())) {
			this.setMensajeAlertaDefault("% Exigencia: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getMonPrem())) {
			this.setMensajeAlertaDefault("Monto: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		for (int i = 0; i < this.lecNivelCorporativoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelCorporativoList.get(i);
			if (StringUtils
					.equals(base.getCodigo(), f.getCodNivelLanzamiento())) {
				f.setNivelLanzamiento(base.getDescripcion());
				break;
			}
		}

		for (int i = 0; i < this.lecTipoMedicionList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecTipoMedicionList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getCodTipoMedicion())) {
				f.setTipoMedicion(base.getDescripcion());
				break;
			}
		}

		for (int i = 0; i < this.lecBonoNivelCorporativoList.length; i++) {
			String value = this.lecBonoNivelCorporativoList[i].getValue();
			if (StringUtils.equals(value, f.getCodBonoNivel())) {
				String label = this.lecBonoNivelCorporativoList[i].getLabel();
				f.setBononivel(label);
				break;
			}
		}
		return false;
	}

	// Inserta los campos en la grilla -Tab Bonos-Ciclo Vida
	public void guardarAmbitoBonosCicloVida(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoBonoCicloVida(f))
				return;

			List list = this.lecRetencionList;
			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			BonoNivel bononivel;
			bononivel = new BonoNivel();
			BeanUtils.copyProperties(bononivel, f);
			bononivel.setCodTipoBono(f.getCodTipoBono());
			bononivel.setCodTipoPrem(f.getCodigoTipoPremiacion());
			bononivel.setCodNivel(f.getCodNivelRete());
			bononivel.setNivel(f.getNivelRete());
			bononivel.setNroMinIng(f.getNroMinIng());
			bononivel.setNroMaxIng(f.getNroMaxIng());
			bononivel.setNroMinRete(f.getNroMinRete());
			bononivel.setNroMaxRete(f.getNroMaxRete());
			bononivel.setPorRetExig(f.getPorRetExig());
			bononivel.setMonPrem(f.getMontoPremioCicloVida());
			bononivel.setTipoBono(this.getDescripcionTipoBono(f
					.getCodTipoBono()));
			bononivel.setTipoPrem(f.getTipoPrem());

			String verificar = this.verificarListaBonoNivelTraslapen(list,
					bononivel);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));
			list.add(bononivel);

			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			this.lecRetencionList = list;
			this.lecRetencionDataModel = new DataTableModel(
					this.lecRetencionList);
			this.indicadorActivoBonCicloVida22 = Constants.NUMERO_UNO;
			this.nivlimpiarFormBono(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Eliminar una fila de la grilla- Tab Bono ciclo de Vida
	public void eliminarAmbitoBonoCicloVida(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			List list = this.lecRetencionList;
			String[] indexes = new String[this.beanlecRetencion.length];
			for (int j = 0; j < this.beanlecRetencion.length; j++) {
				BonoNivel beanBonoNivel = (BonoNivel) this.beanlecRetencion[j];
				for (int i = 0; i < list.size(); i++) {
					BonoNivel listaObjeto = new BonoNivel();
					listaObjeto = (BonoNivel) list.get(i);
					if (StringUtils.equals(beanBonoNivel.getCodTipoBono(),
							listaObjeto.getCodTipoBono())
							&& StringUtils.equals(
									beanBonoNivel.getCodTipoPrem(),
									listaObjeto.getCodTipoPrem())
							&& StringUtils.equals(beanBonoNivel.getCodNivel(),
									listaObjeto.getCodNivel())
							&& StringUtils.equals(beanBonoNivel.getNroMinIng(),
									listaObjeto.getNroMinIng())
							&& StringUtils.equals(beanBonoNivel.getNroMaxIng(),
									listaObjeto.getNroMaxIng())
							&& StringUtils.equals(
									beanBonoNivel.getNroMinRete(),
									listaObjeto.getNroMinRete())
							&& StringUtils.equals(
									beanBonoNivel.getNroMaxRete(),
									listaObjeto.getNroMaxRete())
							&& StringUtils.equals(beanBonoNivel.getMonPrem(),
									listaObjeto.getMonPrem())) {
						indexes[j] = Integer.toString(i);
						break;
					}
				}
			}

			List nuevaLista = this.eliminarObjetosSeleMultiples(indexes, list);
			this.lecRetencionList = nuevaLista;
			this.lecRetencionDataModel = new DataTableModel(
					this.lecRetencionList);
			this.nivlimpiarFormBono(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// verifica los datos antes de ser insertados- BonoNivel
	private String verificarListaBonoNivelTraslapen(List lista, BonoNivel nivel) {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);

			// Para un mismo Tipo de Bono y Nivel, validar que no se igrese
			// diferentes Tipos de Premiación
			if (StringUtils.equals(p.getCodTipoBono(), nivel.getCodTipoBono())
					&& StringUtils.equals(p.getCodNivel(), nivel.getCodNivel())
					&& !StringUtils.equals(p.getCodTipoPrem(),
							nivel.getCodTipoPrem())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.bonoNivelTipoPremiacionNoPuedeSerDiferente";
				return proceso;
			}

			// Para un mismo Tipo de Bono y Nivel, validar que no haya traslapes
			// con los nros de ingresos y reteciones,
			// esto al momento de ingresar un nuevo Bono Nivel y al momento de
			// guardar.

			if (p.getCodTipoBono().equals(nivel.getCodTipoBono())
					&& p.getCodNivel().equals(nivel.getCodNivel())) {
				// Ingresos
				// (nMin = aMin && nMax =aMax) || (nMin > aMax && nMax > aMax)
				// || (nMin < aMin && nMax < aMin) <--- VALIDA TRASLAPES

				int nMin = Integer.parseInt(nivel.getNroMinIng());
				int nMax = Integer.parseInt(nivel.getNroMaxIng());

				int aMin = Integer.parseInt(p.getNroMinIng());
				int aMax = Integer.parseInt(p.getNroMaxIng());

				if (!((nMin == aMin && nMax == aMax)
						|| (nMin > aMax && nMax > aMax) || (nMin < aMin && nMax < aMin))) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelRangoYaExiste.ingresos";
					return proceso;
				}

				if (nMin == aMin && nMax == aMax) {
					// Retenciones
					// (nMin > aMax && nMax > aMax) || (nMin < aMin && nMax <
					// aMin) <--- VALIDA TRASLAPES
					nMin = Integer.parseInt(nivel.getNroMinRete());
					nMax = Integer.parseInt(nivel.getNroMaxRete());

					aMin = Integer.parseInt(p.getNroMinRete());
					aMax = Integer.parseInt(p.getNroMaxRete());

					if (!((nMin > aMax && nMax > aMax) || (nMin < aMin && nMax < aMin))) {
						String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelRangoYaExiste.retenciones";
						return proceso;
					}
					//
				}
			}
		}
		return null;
	}

	// Metodo para eliminar multiples elementos en base a sus index
	private List eliminarObjetosSeleMultiples(String[] seleccionados,
			List listaFuente) {
		List listaRetorno = new ArrayList();
		if (seleccionados != null && listaFuente != null) {
			for (int i = 0; i < seleccionados.length; i++) {
				int indexEliminar = Integer.parseInt(seleccionados[i]);
				listaFuente.set(indexEliminar, null);
			}

			for (int i = 0; i < listaFuente.size(); i++) {
				if (listaFuente.get(i) != null)
					listaRetorno.add(listaFuente.get(i));
			}
		}
		return listaRetorno;
	}

	// Realiza todas las validaciones antes de insertar los campos a la grilla-
	// Tab Bonos-Ciclo de Vida
	private Boolean validarAmbitoBonoCicloVida(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCodNivelRete())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNroMinIng())) {
			this.setMensajeAlertaDefault("Nro Mínimo Ingreso: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNroMinRete())) {
			this.setMensajeAlertaDefault("Nro Mínimo Retenciones: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNroMaxRete())) {
			this.setMensajeAlertaDefault("Nro Máximo Retenciones: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (Integer.parseInt(f.getNroMaxRete()) < Integer.parseInt(f
				.getNroMinRete())) {
			this.setMensajeAlertaDefault("Nro Mínimo Retenciones debe ser menor o igual a Nro Máximo Retenciones");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getMontoPremioCicloVida())) {
			if (StringUtils.equals(f.getCodigoTipoPremiacion(),
					Constants.LEC_CODIGO_TIPO_PREMIACION_CANASTA)) {
				f.setMontoPremioCicloVida("");
				this.habilitaMontoPremio = true;
			} else {
				this.setMensajeAlertaDefault("Monto Bono: Valor Requerido");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			}
		} else {
			if (Float.parseFloat(f.getMontoPremioCicloVida()) == 0.0) {
				this.setMensajeAlertaDefault("Monto Bono debe ser mayor a 0");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			}
		}

		for (int i = 0; i < this.lecNivelCorporativoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelCorporativoList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getCodNivelRete())) {
				f.setNivelRete(base.getDescripcion());
				break;
			}
		}

		for (int i = 0; i < this.lecTipoPremiacionList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecTipoPremiacionList.get(i);
			if (StringUtils.equals(base.getCodigo(),
					f.getCodigoTipoPremiacion())) {
				f.setTipoPrem(base.getDescripcion());
				break;
			}
		}

		return false;
	}

	// Insertar datos a la grilla Tab Bonos-Nivel Acelerado
	public void guardarAmbitoBonosAcelerado(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoBonoAcelerado(f))
				return;
			List list = this.lecCambNivelAvan;

			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			BonoNivel bononivel;
			bononivel = new BonoNivel();
			BeanUtils.copyProperties(bononivel, f);
			bononivel.setCodNivel(f.getCodNivelCamAce());
			bononivel.setNivel(f.getNivelCamAce());
			bononivel.setNroCampMax(f.getNroCampMax());
			bononivel.setNroMinIncPed(f.getNroMinIncPed());
			bononivel.setMonPrem(f.getMontoPremioNivelAcelerado());
			bononivel.setCodTipoBono("08"); // tipo bono nivel acelerado
			bononivel.setCodTipoPrem("01");

			String verificar = this.verificarListaBonoNivel(list, bononivel);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));
			list.add(bononivel);

			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			this.lecCambNivelAvan = list;
			this.lecCambNivelAvanDataModel = new DataTableModel(
					this.lecCambNivelAvan);
			this.indicadorActivoBonNivelAce = Constants.NUMERO_UNO;
			this.nivlimpiarFormBono(f);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina los datos seleccioandos de la grilla Tab Bonos- Nivel acelerado
	public void eliminarAmbitoBonoAcelerado(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;

			List list = this.lecCambNivelAvan;
			String[] indexes = new String[this.beanlecCambNivelAvan.length];
			for (int j = 0; j < this.beanlecCambNivelAvan.length; j++) {
				BonoNivel beanBonoNivel = (BonoNivel) this.beanlecCambNivelAvan[j];
				for (int i = 0; i < list.size(); i++) {
					BonoNivel listaObjeto = new BonoNivel();
					listaObjeto = (BonoNivel) list.get(i);
					if (StringUtils.equals(beanBonoNivel.getCodTipoBono(),
							listaObjeto.getCodTipoBono())
							&& StringUtils.equals(beanBonoNivel.getCodNivel(),
									listaObjeto.getCodNivel())
							&& StringUtils.equals(
									beanBonoNivel.getNroCampMax(),
									listaObjeto.getNroCampMax())
							&& StringUtils.equals(
									beanBonoNivel.getNroMinIncPed(),
									listaObjeto.getNroMinIncPed())) {
						indexes[j] = Integer.toString(i);
						break;
					}
				}
			}
			List nuevaLista = this.eliminarObjetosSeleMultiples(indexes, list);
			this.lecCambNivelAvan = nuevaLista;
			this.lecCambNivelAvanDataModel = new DataTableModel(
					this.lecCambNivelAvan);
			this.nivlimpiarFormBono(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Verifica si el Bono Nivel se encuentra en la Lista
	private String verificarListaBonoNivel(List lista, BonoNivel nivel) {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);

			if (StringUtils.equals(p.getCodTipoBono(), nivel.getCodTipoBono())
					&& StringUtils.equals(p.getCodNivel(), nivel.getCodNivel())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelYaExiste";
				return proceso;
			}
		}
		return null;
	}

	// Valida los campos antes de insertarlos -Tab Bono Acelerado
	private Boolean validarAmbitoBonoAcelerado(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCampannaInicialNivelAcelerado())) {
			this.setMensajeAlertaDefault("Campaña Inicio Nivel Acelerado: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPeriodoInicio())) {
			this.setMensajeAlertaDefault("Periodo Inicio: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		int codperini = Integer.parseInt(f.getPeriodoInicio());
		int codpenivel = Integer.parseInt(f.getCampannaInicialNivelAcelerado());
		if (codpenivel < codperini) {
			this.setMensajeAlertaDefault("Campaña Inicio Nivel Acelerado debe ser mayor o igual a la Campaña Inicio");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isNotBlank(f.getCampannaFinalNivelAcelerado())) {
			int codperfin = Integer
					.parseInt(f.getCampannaFinalNivelAcelerado());
			if (codperfin < codperini) {
				this.setMensajeAlertaDefault("Campaña Final Nivel Acelerado debe ser mayor o igual a la Campaña Inicio Nivel Acelerado");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;
			}
		}

		if (StringUtils.isBlank(f.getCodNivelCamAce())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNroCampMax())) {
			this.setMensajeAlertaDefault("Nro Campañas: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNroMinIncPed())) {
			this.setMensajeAlertaDefault("Nro Mínimo Incremento Pedido: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		for (int i = 0; i < this.lecNivelCorporativoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelCorporativoList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getCodNivelCamAce())) {
				f.setNivelCamAce(base.getDescripcion());
				break;
			}
		}
		return false;
	}

	// //Inserta registros de la grilla -Tab Bono Gracia Nueva
	public void guardarAmbitoBonosGraciaNueva(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoBonoGraciaNueva(f))
				return;

			List list = this.lecPeriodoGracia;

			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			BonoNivel bononivel;
			bononivel = new BonoNivel();
			BeanUtils.copyProperties(bononivel, f);
			bononivel.setCodNivel(f.getCodigoNivelPeriodoGracia());
			bononivel.setNivel(f.getNivelPeriodoGracia());
			bononivel.setMonPrem(f.getMontoPeriodoGracia());
			bononivel.setCodTipoBono("09"); // tipo bono nivel acelerado
			bononivel.setCodTipoPrem("01");

			String verificar = this.verificarListaBonoNivel(list, bononivel);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));
			list.add(bononivel);

			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));

			this.lecPeriodoGracia = list;
			this.lecPeriodoGraciaDataModel = new DataTableModel(
					this.lecPeriodoGracia);
			this.nivlimpiarFormBono(f);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina registros de la grilla -Tab Bono Gracia Nueva
	public void eliminarAmbitoBonoGraciaNueva(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			List list = this.lecPeriodoGracia;
			String[] indexes = new String[this.beanlecPeriodoGracia.length];
			for (int j = 0; j < this.beanlecPeriodoGracia.length; j++) {
				BonoNivel beanBonoNivel = (BonoNivel) this.beanlecPeriodoGracia[j];
				for (int i = 0; i < list.size(); i++) {
					BonoNivel listaObjeto = new BonoNivel();
					listaObjeto = (BonoNivel) list.get(i);
					if (StringUtils.equals(beanBonoNivel.getCodTipoBono(),
							listaObjeto.getCodTipoBono())
							&& StringUtils.equals(beanBonoNivel.getCodNivel(),
									listaObjeto.getCodNivel())
							&& StringUtils.equals(beanBonoNivel.getMonPrem(),
									listaObjeto.getMonPrem())) {
						indexes[j] = Integer.toString(i);
						break;
					}
				}
			}
			List nuevaLista = this.eliminarObjetosSeleMultiples(indexes, list);
			this.lecPeriodoGracia = nuevaLista;
			this.lecPeriodoGraciaDataModel = new DataTableModel(
					this.lecPeriodoGracia);
			this.nivlimpiarFormBono(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Valida campos antes de insertar a la grilla -Tab Bonos Gracia Nueva
	private Boolean validarAmbitoBonoGraciaNueva(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCodigoNivelPeriodoGracia())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getMontoPeriodoGracia())) {
			this.setMensajeAlertaDefault("Monto: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		for (int i = 0; i < this.lecNivelCorporativoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelCorporativoList.get(i);
			if (StringUtils.equals(base.getCodigo(),
					f.getCodigoNivelPeriodoGracia())) {
				f.setNivelPeriodoGracia(base.getDescripcion());
				break;
			}
		}
		return false;
	}

	// Insertar los registros a la Grilla -Tab INCENTIVOS-1ra grilla
	public void guardarAmbitoIncentivoPrimero(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoIncentivoPrimero(f))
				return;

			List list = this.lecIncTipoComiList;

			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			Nivel oc = new Nivel();
			BeanUtils.copyProperties(oc, f);
			oc.setNivel(f.getNiveltipoComi());
			oc.setMontPediCons(f.getPagPedConsecu());
			oc.setMontPediNCon(f.getPagPedNoConsecu());
			oc.setMontTole(f.getPagTolePedi());
			oc.setCodigoNivel(f.getIncCodNivelTipCom());

			String verificar = this.verificarListaNivel(list, oc);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));
			list.add(oc);

			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));

			this.lecIncTipoComiList = list;
			this.lecIncTipoComiDataModel = new DataTableModel(
					this.lecIncTipoComiList);
			this.indicadorActivoIncNivel = Constants.NUMERO_UNO;
			this.nivlimpiarFormIncentivos(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Eliminar multiple registros de la Grilla -Tab Incentivos Primera parte
	public void eliminarAmbitoIncentivoPrimero(ActionEvent actionEvent) {
		try {
			List listaFuente = this.lecIncTipoComiList;
			List listaRetorno = new ArrayList();
			for (int j = 0; j < this.beanlecIncTipoComiList.length; j++) {
				Nivel beanNivel = (Nivel) this.beanlecIncTipoComiList[j];
				for (int i = 0; i < listaFuente.size(); i++) {
					Nivel ct = (Nivel) listaFuente.get(i);
					if (StringUtils.equals(ct.getCodigoNivel(),
							beanNivel.getCodigoNivel())) {
						listaFuente.set(i, null);
						break;
					}
				}
			}

			for (int i = 0; i < listaFuente.size(); i++) {
				if (listaFuente.get(i) != null)
					listaRetorno.add(listaFuente.get(i));
			}

			this.lecIncTipoComiList = listaRetorno;
			this.lecIncTipoComiDataModel = new DataTableModel(
					this.lecIncTipoComiList);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Valida los campos antes de insertarlos -Tab Incentivo 1 parte
	private Boolean validarAmbitoIncentivoPrimero(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getIncCodNivelTipCom())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPagPedConsecu())) {
			this.setMensajeAlertaDefault("Pago Pedido Consecutivo: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPagPedNoConsecu())) {
			this.setMensajeAlertaDefault("Pago Pedido No Consecutivo: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPagTolePedi())) {
			this.setMensajeAlertaDefault("Pago Tolerancia Pedido: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		for (int i = 0; i < this.lecNivelCorporativoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelCorporativoList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getIncCodNivelTipCom())) {
				f.setNiveltipoComi(base.getDescripcion());
				break;
			}
		}

		return false;
	}

	// Limpia los campos del Tab Incentivos
	private void nivlimpiarFormIncentivos(
			MantenimientoLECProgramaCorporativoForm f) {
		f.setPagPedConsecu("");
		f.setPagPedNoConsecu("");
		f.setPagTolePedi("");
		f.setPorPedConsecu("");
		f.setPorPedNoConsecu("");
		f.setPorTolePedi("");
		f.setPagPedConsecu("");
		f.setPagPedNoConsecu("");
		f.setPagTolePedi("");
	}

	// Insertar registros a la grilla - Tab INCENTIVOS-2da grilla
	public void guardarAmbitoIncentivoSegunda(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoIncentivoSegunda(f))
				return;

			List list = this.lecIncMontoRecuList;
			List listramos = this.lecProgramaCorporativoObjCobList;

			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			NivelTramo oc = new NivelTramo();
			BeanUtils.copyProperties(oc, f);
			oc.setNivel(f.getNivelCanast());
			oc.setTramo(f.getTramo());
			oc.setCodigoNivel(f.getIncCodNivel());
			oc.setCodigoTramo(f.getIncCodTramo());
			oc.setValPorComiPediCons(f.getPorPedConsecu());
			oc.setValPorComiPediNCon(f.getPorPedNoConsecu());
			oc.setValPorComiTole(f.getPorTolePedi());

			String verificar = this.verificarListaNivelTramo(list, oc);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));

			ObjetivoCobranza cob = new ObjetivoCobranza();
			cob.setNroTramo(f.getIncCodTramo());
			verificar = this
					.verificarListaObjetivoCobranzaTramo(listramos, cob);
			if (StringUtils.isBlank(verificar)) {
				verificar = "mantenimientoLECProgramaCorporativoForm.error.tramoNoExisteCobranza";
				throw new Exception(this.getResourceMessage(verificar));
			}

			list.add(oc);
			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));

			this.nivlimpiarFormIncentivos(f);
			this.lecIncMontoRecuList = list;
			this.lecIncMontoRecuDataModel = new DataTableModel(
					this.lecIncMontoRecuList);
			this.indicadorActivoIncNivel = Constants.NUMERO_UNO;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Eliminar los registros del tab Incentivos - 2 parte
	public void eliminarAmbitoIncentivoSegundo(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (this.beanlecIncMontoRecu == null) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			List list = this.lecIncMontoRecuList;
			this.actualizaListaGrillaIncentivo3(f, list);
			String codNivel = "";
			String codTramo = "";
			String valporComision = "";
			if (this.beanlecIncMontoRecu instanceof NivelTramo) {
				NivelTramo beanBonoNivel = (NivelTramo) this.beanlecIncMontoRecu;
				codNivel = beanBonoNivel.getCodigoNivel();
				codTramo = beanBonoNivel.getCodigoTramo();
				valporComision = beanBonoNivel.getValPorComiPediCons();
			}
			if (this.beanlecIncMontoRecu instanceof HashMap) {
				Map beanBonoNivel = (Map) this.beanlecIncMontoRecu;
				codNivel = beanBonoNivel.get("codigoNivel").toString();
				codTramo = beanBonoNivel.get("codigoTramo").toString();
				valporComision = beanBonoNivel.get("valPorComiPediCons")
						.toString();
			}

			int index = -1;
			for (int i = 0; i < list.size(); i++) {
				NivelTramo listaObjeto = new NivelTramo();
				listaObjeto = (NivelTramo) list.get(i);
				if (StringUtils.equals(listaObjeto.getCodigoNivel(), codNivel)
						&& StringUtils.equals(listaObjeto.getCodigoTramo(),
								codTramo)
						&& StringUtils.equals(
								listaObjeto.getValPorComiPediCons(),
								valporComision)) {
					index = i;
					break;
				}
			}

			List nuevaLista = this.eliminarObjetosSeleccionados(index, list);
			this.lecIncMontoRecuList = nuevaLista;
			this.lecIncMontoRecuDataModel = new DataTableModel(
					this.lecIncMontoRecuList);
			this.indicadorActivoIncNivel = Constants.NUMERO_UNO;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param lista
	 * @param nivel
	 */
	private String verificarListaNivelTramo(List lista, NivelTramo nivel)
			throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			NivelTramo p = new NivelTramo();
			BeanUtils.copyProperties(p, lista.get(i));

			if (StringUtils.equals(p.getCodigoNivel(), nivel.getCodigoNivel())
					&& StringUtils.equals(p.getCodigoTramo(),
							nivel.getCodigoTramo()) && StringUtils.equals(p.getCodigoRangoComision(), nivel.getCodigoRangoComision())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelTramoYaExiste";
				return proceso;
			}
		}
		return null;
	}

	/**
	 * @param lista
	 * @param objetivoCobranza
	 * @return
	 */
	private String verificarListaObjetivoCobranzaTramo(List lista,
			ObjetivoCobranza objetivoCobranza) {
		for (int i = 0; i < lista.size(); i++) {
			ObjetivoCobranza p = (ObjetivoCobranza) lista.get(i);

			if (StringUtils.equals(p.getNroTramo(),
					objetivoCobranza.getNroTramo())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.objetivoCobranzaYaExiste";
				return proceso;
			}
		}
		return null;
	}

	// Valida los campos antes de insertarlos a la Grilla -tab Incentivos 2
	// parte
	private Boolean validarAmbitoIncentivoSegunda(
			MantenimientoLECProgramaCorporativoForm f) {
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		if (StringUtils.isBlank(f.getIncCodNivel())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getIncCodTramo())) {
			this.setMensajeAlertaDefault("Tramo: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPorPedConsecu())) {
			this.setMensajeAlertaDefault("% Comisión Pedidos Consecutivos: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPorPedNoConsecu())) {
			this.setMensajeAlertaDefault("% Comisión Pedidos No Consecutivos: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPorTolePedi())) {
			this.setMensajeAlertaDefault("% Comisión Tolerancia Pedidos: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		for (int i = 0; i < this.lecNivelCorporativoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelCorporativoList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getIncCodNivel())) {
				f.setNivelCanast(base.getDescripcion());
				break;
			}
		}

		for (int i = 0; i < this.lecSelectTramoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecSelectTramoList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getIncCodTramo())) {
				f.setTramo(base.getDescripcion());
				break;
			}
		}

		for (int i = 0; i < this.lecNivelRangoIncenList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelRangoIncenList.get(i);
			if (StringUtils
					.equals(base.getCodigo(), f.getCodigoRangoComision())) {
				f.setDescriRangoComision(base.getDescripcion());
				break;
			}
		}
		// Obtiene el Nivel del Rango
		String nivelRango = service.obtieneNivelCodigoRango(f
				.getCodigoRangoComision());
		f.setCodigoNivelRango(nivelRango);

		return false;
	}

	// Inserta registros a la Grilla tab INCENTIVOS-3ra parte
	public void guardarAmbitoIncentivoTercero(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoIncentivoTercero(f))
				return;

			List list = this.lecIncCanasProd;
			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			ProgramaCanastaPremi oc = new ProgramaCanastaPremi();
			BeanUtils.copyProperties(oc, f);
			String[] result = f.getCanasta().split("-");
			oc.setNivel(f.getNivelCanast());
			oc.setNomCanasta(result[0]);
			oc.setCodCanasta(f.getIncCanasta());
			oc.setCostoCanasta(result[1]);
			oc.setSecCanasta(f.getIncCanasta());
			oc.setCodigoNivel(f.getIncCodNivelCanast());
			oc.setCodigoTipoCanasta(f.getCodigoTipoCanasta());
			oc.setTramo(" ");
			if (!StringUtils.isBlank(f.getTramo())) {
				oc.setTramo(f.getTramo());
				oc.setCodTramo(f.getIncCodTramo());
			}
			oc.setCodigoPeriodo(f.getCampanyaActivacionCanastaIncentivos());
			oc.setNombreTipoCanasta(getNombreTipoCanasta(f
					.getCodigoTipoCanasta()));

			String verificar = this.verificarListaProgramaCanasta(list, oc);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));

			Map criteria = BeanUtils.describe(oc);
			criteria.remove("auditInfo");
			list.add(criteria);
			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));

			this.lecIncCanasProd = list;
			this.lecIncCanasProdDataModel = new DataTableModel(
					this.lecIncCanasProd);
			this.indicadorActivoIncCanasta = Constants.NUMERO_UNO;
			// ver
			String actCobranza = StringUtils.isBlank(f.getActCobranza()) ? Constants.IND_CHECK_OFF
					: Constants.IND_CHECK_ON;
			String actCobranza1 = StringUtils.isBlank(f.getIncExigTramo()) ? Constants.IND_CHECK_OFF
					: Constants.IND_CHECK_ON;
			System.out.println("" + actCobranza);
			System.out.println("" + actCobranza1);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina registro de la Grilla -Tab Incentivos - 3 parte
	public void eliminarAmbitoIncentivoTercero(ActionEvent actionEvent) {
		try {
			List list = this.lecIncCanasProd;
			Map beanCanasta = (Map) this.beanlecIncCanasProd;
			String beancod = beanCanasta.get("codigoNivel").toString();
			String beansec = beanCanasta.get("secCanasta").toString();

			int index = -1;
			for (int i = 0; i < list.size(); i++) {
				Map listaObjeto = (Map) list.get(i);
				String cod = listaObjeto.get("codigoNivel").toString();
				String sec = listaObjeto.get("secCanasta").toString();

				if (StringUtils.equals(beancod, cod)
						&& StringUtils.equals(beansec, sec)) {
					index = i;
					break;
				}
			}

			List nuevaLista = eliminarObjetosSeleccionados(index, list);
			this.lecIncCanasProd = nuevaLista;
			this.lecIncCanasProdDataModel = new DataTableModel(
					this.lecIncCanasProd);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Verifica si existe Programa Canasta en la lista
	private String verificarListaProgramaCanasta(List lista,
			ProgramaCanastaPremi programaCanastaPremi) throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			ProgramaCanastaPremi p = new ProgramaCanastaPremi();
			Map registro = (Map) lista.get(i);
			BeanUtils.copyProperties(p, registro);

			if (StringUtils.equals(p.getCodigoPeriodo(),
					programaCanastaPremi.getCodigoPeriodo())
					&& p.getCodigoNivel().equals(
							programaCanastaPremi.getCodigoNivel())
					&& StringUtils.equals(p.getCodigoTipoCanasta(),
							programaCanastaPremi.getCodigoTipoCanasta())
					&& StringUtils.equals(p.getSecCanasta(),
							programaCanastaPremi.getSecCanasta())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.programaCanastaYaExiste";
				return proceso;
			}
		}

		return null;
	}

	// Buscar las Canastas Tab Incentivos -3 parte -- Canastas 1 parte
	public void filtrarCanastas(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

			// Pantalla= I--Incenitivos C--Canasta
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			String pantalla = externalContext.getRequestParameterMap()
					.get("parametroAccion").toString();

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPrograma", f.getCodigoPrograma());

			if (StringUtils.equals(pantalla, "C")) {
				// Fue invocado desde la pantalla de Campaña
				if (StringUtils.isBlank(f.getCampanyaActivacionCanasta())) {
					this.setMensajeAlertaDefault("Campaña Activación: Valor Requerido");
					String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;
				}
				criteria.put("campanyaActivacionCanasta",
						f.getCampanyaActivacionCanasta());
				f.setCampanyaActivacionCanastaIncentivos(f
						.getCampanyaActivacionCanasta());
				f.setTabSeleccion(Constants.LEC_TAB_PROGRAMA_CORPORATIVO_CANASTA);
			} else if (StringUtils.equals(pantalla, "I")) {
				// Fue invocado desde la pantalla de Incentivos
				if (StringUtils.isBlank(f
						.getCampanyaActivacionCanastaIncentivos())) {
					this.setMensajeAlertaDefault("Campaña Activación: Valor Requerido");
					String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;
				}
				criteria.put("campanyaActivacionCanasta",
						f.getCampanyaActivacionCanastaIncentivos());
				f.setCampanyaActivacionCanasta(f
						.getCampanyaActivacionCanastaIncentivos());
				f.setTabSeleccion(Constants.LEC_TAB_PROGRAMA_CORPORATIVO_INCENTIVO);
			}

			List lecCanastaList = service.getCanastaList(criteria);
			List lecCanastaDetalleList = service
					.getCanastaDetalleList(criteria);
			List listCanas = new ArrayList();
			Base tmo;
			Canasta can;

			// COMBO CANASTA
			for (int k = 0; k < lecCanastaList.size(); k++) {
				tmo = new Base();
				can = new Canasta();
				BeanUtils.copyProperties(can, lecCanastaList.get(k));
				tmo.setCodigo(can.getSecuencia());
				tmo.setDescripcion(can.getNombre() + "-" + can.getValor());
				listCanas.add(tmo);
			}
			this.lecCanasProdList = listCanas;
			this.lecCanastaList = lecCanastaList;
			this.lecCanastaDataModel = new DataTableModel(this.lecCanastaList);
			this.lecProductosCanastaList = lecCanastaDetalleList;
			this.lecProductosCanastaDataModel = new DataTableModel(
					this.lecProductosCanastaList);
			List lisinc2 = service.getProgramaCanastaPremiList(criteria);
			this.lecIncCanasProd = lisinc2;
			this.lecIncCanasProdDataModel = new DataTableModel(
					this.lecIncCanasProd);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// valida los campos antes de insertarlos a la grilla -Tab Incentivos -3
	// parte
	private Boolean validarAmbitoIncentivoTercero(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getIncCodNivelCanast())) {
			this.setMensajeAlertaDefault("Nivel: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getIncCanasta())) {
			this.setMensajeAlertaDefault("Canasta: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		for (int i = 0; i < this.lecNivelCorporativoList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecNivelCorporativoList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getIncCodNivelCanast())) {
				f.setNivelCanast(base.getDescripcion());
				break;
			}
		}

		List listCanas = new ArrayList();
		for (int k = 0; k < this.lecCanastaList.size(); k++) {
			Base tmo = new Base();
			Map can = new HashMap();
			can = (Map) this.lecCanastaList.get(k);
			String secuencia = can.get("secuencia").toString();
			String nombre = can.get("nombre").toString();
			String valor = can.get("valor").toString();
			tmo.setCodigo(secuencia);
			tmo.setDescripcion(nombre + "-" + valor);
			listCanas.add(tmo);
		}
		this.lecCanasProdList = listCanas;

		for (int i = 0; i < this.lecCanasProdList.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecCanasProdList.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getIncCanasta())) {
				f.setCanasta(base.getDescripcion());
				break;
			}
		}
		return false;
	}

	// Insertar registros a la grilla -tab CANASTA--1 parte
	public void guardarAmbitoCanasta(ActionEvent actionEvent) {
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoCanasta(f))
				return;

			List lecCanastaList = this.lecCanastaList;

			if (lecCanastaList == null)
				lecCanastaList = new ArrayList();

			String descripcion = StringUtils.trim(f.getNombreCanasta()) + "-"
					+ StringUtils.trim(f.getValorCanasta());
			Map canasta = new HashMap();
			canasta.put("codigoPais", f.getCodigoPais());
			canasta.put("codigoPrograma", f.getCodigoPrograma());
			canasta.put("nombre", f.getNombreCanasta());
			canasta.put("valor", f.getValorCanasta());
			canasta.put("secuencia", Constants.NUMERO_CERO);
			canasta.put("campanyaRegistro", f.getCampanyaRegistro());
			canasta.put("usuarioRegistro", usuario.getLogin());
			canasta.put("campanyaActivacionCanasta",
					f.getCampanyaActivacionCanasta());
			canasta.put("descripcion", descripcion);

			String verificar = this.verificarListaCanastaCabecera(
					lecCanastaList, canasta);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));

			lecCanastaList.add(canasta);
			this.lecCanastaList = lecCanastaList;
			this.lecCanastaDataModel = new DataTableModel(this.lecCanastaList);
			this.indicadorActivoCanasta = Constants.NUMERO_UNO;
			this.limpiarFormCanastaCabecera(f);
			f.setMostrarIngresoCanasta("0");
			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina los registros de la Grilla -tab Canasta
	public void eliminarAmbitoCanasta(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;

			List lecCanastaList = this.lecCanastaList;
			List lecProductosCanastaList = this.lecProductosCanastaList;
			Map beanCanasta = (Map) this.beanlecCanasta;
			String nombreBean = (String) beanCanasta.get("nombre");

			int index = -1;
			for (int i = 0; i < lecCanastaList.size(); i++) {
				Map reCanasta = (Map) lecCanastaList.get(i);
				String nombreRegistro = (String) reCanasta.get("nombre");
				if (StringUtils.equals(nombreRegistro, nombreBean)) {
					index = i;
					break;
				}
			}
			boolean eliminar = true;
			for (int j = 0; j < lecProductosCanastaList.size(); j++) {
				Map registro = (Map) lecProductosCanastaList.get(j);
				String nombreDetalle = (String) registro.get("nombreCanasta");
				if (StringUtils.equals(nombreDetalle, nombreBean)) {
					eliminar = false;
					break;
				}
			}
			if (!eliminar)
				throw new Exception(
						this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.registroExisteDetalleCanasta"));

			List nuevaLista = this.eliminarObjetosSeleccionados(index,
					lecCanastaList);
			this.lecCanastaList = nuevaLista;
			this.lecCanastaDataModel = new DataTableModel(this.lecCanastaList);
			f.setSelectedItemsCanasta(new String[] {});
			f.setSelectedItemsProductoCanasta(new String[] {});
			this.limpiarFormCanastaCabecera(f);
			this.limpiarFormCanastaDetalle(f);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Limpiar campos del tab Canasta grilla 1
	private void limpiarFormCanastaCabecera(
			MantenimientoLECProgramaCorporativoForm f) {
		f.setNombreCanasta("");
		f.setValorCanasta("");
		f.setNumeroProductosCanasta("");
	}

	// Limpiar campos del tab Canasta grilla 2
	private void limpiarFormCanastaDetalle(
			MantenimientoLECProgramaCorporativoForm f) {
		f.setCodigoTipoOfertaProductoCanasta("");
		f.setCodigoSAPProductoCanasta("");
		f.setDescripcionProductoCanasta("");
		f.setPrecioProductoCanasta("");
	}

	// Verifica si la Canasta Cabecera se encuentra en la Lista
	private String verificarListaCanastaCabecera(List lista, Map canasta)
			throws Exception {
		String nombre = (String) canasta.get("nombre");
		for (int i = 0; i < lista.size(); i++) {
			Map registro = (Map) lista.get(i);
			String nombreRegistro = (String) registro.get("nombre");

			if (StringUtils.equals(nombreRegistro, nombre)) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.canastaYaExiste";
				return proceso;
			}
		}
		return null;
	}

	// Valida los campos antes de insertarlos a la grilla -Tab Canasta
	private Boolean validarAmbitoCanasta(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCampanyaActivacionCanasta())) {
			this.setMensajeAlertaDefault("Campaña Activación: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNombreCanasta())) {
			this.setMensajeAlertaDefault("Nombre Canasta: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getValorCanasta())) {
			this.setMensajeAlertaDefault("Valor Canasta: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		return false;
	}

	// Inserta registros a la grilla -Tab CANASTA ---2 parte
	public void guardarAmbitoCanastaSegunda(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoCanastaSegundo(f))
				return;

			List lecCanastaList = this.lecCanastaList;
			List lecProductosCanastaList = this.lecProductosCanastaList;
			if (lecProductosCanastaList == null)
				lecProductosCanastaList = new ArrayList();

			Map beanCanasta = (Map) this.beanlecCanasta;

			if (this.beanlecCanasta == null)
				throw new Exception(
						this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.seleccionarCanasta"));

			// if (listaCanasta.length > 1) {
			// throw new
			// Exception(this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.seleccionarUnaCanasta"));
			// }

			List tiposOferta = this.lecTiposOfertaProductoCanasta;
			Map productoCanasta = new HashMap();
			productoCanasta.put("codigoPais", f.getCodigoPais());
			productoCanasta.put("codigoPrograma", f.getCodigoPrograma());

			Map canastaCabecera = (Map) this.beanlecCanasta;
			String nombreCanasta = (String) canastaCabecera.get("nombre");
			String secuenciaCanasta = (String) canastaCabecera.get("secuencia");

			productoCanasta.put("nombreCanasta", nombreCanasta);
			productoCanasta.put("secuenciaCanasta", secuenciaCanasta);
			productoCanasta.put("codigoTipoOferta",
					f.getCodigoTipoOfertaProductoCanasta());
			productoCanasta.put(
					"nombreTipoOferta",
					getNombreTipoOferta(tiposOferta,
							f.getCodigoTipoOfertaProductoCanasta()));
			productoCanasta.put("codigoSap", f.getCodigoSAPProductoCanasta());
			productoCanasta.put(
					"nombreProducto",
					f.getCodigoSAPProductoCanasta() + " - "
							+ f.getDescripcionProductoCanasta());
			productoCanasta.put("precio", f.getPrecioProductoCanasta());
			productoCanasta.put("formaPago",
					f.getCodigoFormaPagoProductoCanasta());
			String verificar = this.verificarListaCanastaDetalle(
					lecProductosCanastaList, productoCanasta);
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));

			this.limpiarFormCanastaDetalle(f);
			lecProductosCanastaList.add(productoCanasta);

			this.lecProductosCanastaList = lecProductosCanastaList;
			this.lecProductosCanastaDataModel = new DataTableModel(
					this.lecProductosCanastaList);
			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina registros a la grilla -Tab CANASTA ---2 parte
	public void eliminarAmbitoCanastaSegunda(ActionEvent actionEvent) {
		try {
			List lecProductosCanastaList = this.lecProductosCanastaList;

			Map beanCanasta = (Map) this.beanlecProductosCanasta;
			String nombreCanasta = (String) beanCanasta.get("nombreCanasta");
			String codigoSap = (String) beanCanasta.get("codigoSap");

			int index = -1;
			for (int i = 0; i < lecProductosCanastaList.size(); i++) {
				Map registro = (Map) lecProductosCanastaList.get(i);
				String nombreCanastaRegistro = (String) registro
						.get("nombreCanasta");
				String codigoSapRegistro = (String) registro.get("codigoSap");

				if (StringUtils.equals(nombreCanastaRegistro, nombreCanasta)
						&& StringUtils.equals(codigoSapRegistro, codigoSap)) {
					index = i;
					break;
				}
			}
			List nuevaLista = this.eliminarObjetosSeleccionados(index,
					lecProductosCanastaList);
			this.lecProductosCanastaList = nuevaLista;
			this.lecProductosCanastaDataModel = new DataTableModel(
					this.lecProductosCanastaList);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Verifica si la Canasta Detalle se encuentra en la Lista
	private String verificarListaCanastaDetalle(List lista, Map canasta)
			throws Exception {
		String nombreCanasta = (String) canasta.get("nombreCanasta");
		String codigoSap = (String) canasta.get("codigoSap");
		for (int i = 0; i < lista.size(); i++) {
			Map registro = (Map) lista.get(i);
			String nombreCanastaRegistro = (String) registro
					.get("nombreCanasta");
			String codigoSapRegistro = (String) registro.get("codigoSap");

			if (StringUtils.equals(nombreCanastaRegistro, nombreCanasta)
					&& StringUtils.equals(codigoSapRegistro, codigoSap)) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.canastaDetalleYaExiste";
				return proceso;
			}
		}
		return null;
	}

	// Obtiene el nombre de una oferta a partir de la lista de ofertas cargada
	// en memoria
	private String getNombreTipoOferta(List tiposOferta, String codigoTipoOferta) {
		String ret = "";
		if (tiposOferta != null) {
			for (int i = 0; i < tiposOferta.size(); i++) {
				Base oferta = (Base) tiposOferta.get(i);
				if (StringUtils.equals(oferta.getCodigo(), codigoTipoOferta)) {
					ret = oferta.getDescripcion();
					break;
				}
			}
		}
		return ret;
	}

	// Valida los campos antes de insertarlos a la grilla -Tab Canasta 2 parte
	private Boolean validarAmbitoCanastaSegundo(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCodigoTipoOfertaProductoCanasta())) {
			this.setMensajeAlertaDefault("Tipo Oferta: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getCodigoSAPProductoCanasta())) {
			this.setMensajeAlertaDefault("Producto: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getPrecioProductoCanasta())) {
			this.setMensajeAlertaDefault("Precio: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		return false;
	}

	// Inserta registros a la Grilla -Tab GESTION DESEMPEÑO
	public void guardarAmbitoGestionDese(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoGestionDese(f))
				return;

			List list = this.lecProgramaCorporativoGestDesemList;
			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			ProgramaDesempenio oc = new ProgramaDesempenio();
			BeanUtils.copyProperties(oc, f);
			oc.setCodigoTipoDesemp(f.getCodTipoDesem());
			oc.setRanInicio(f.getNroVecsExitosaIni());
			oc.setRanFin(f.getNroVecsExitosaFin());

			if (new Integer(f.getNroVecsExitosaIni()).intValue() > new Integer(
					f.getNroVecsExitosaFin()).intValue())
				throw new Exception(
						this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.ranInicioMayor"));

			oc.setDescripcionTipoDesemp(f.getDescripTipoDesem());
			String verificar = this.verificarListaProgramaDesempenio(list,
					f.getCodTipoDesem());
			if (StringUtils.isNotBlank(verificar))
				throw new Exception(this.getResourceMessage(verificar));
			String verificarTraslapen = this
					.verificarListaGestionDesempeñoTraslapen(list, oc);
			if (StringUtils.isNotBlank(verificarTraslapen))
				throw new Exception(this.getResourceMessage(verificarTraslapen));

			Map criteria = BeanUtils.describe(oc);
			criteria.remove("auditInfo");
			list.add(criteria);

			List listCamp = new ArrayList();
			// if (this.listaCampanias.getTarget() != null
			// && this.listaCampanias.getTarget().size() > 0) {
			// for (int i = 0; i < listaCampanias.getTarget().size(); i++) {
			// Base baseSeleccion = new Base();
			// baseSeleccion = (Base) listaCampanias.getTarget().get(i);
			// listCamp.add(baseSeleccion);
			// }
			// }
			// if (this.selecCampanaEvaluarLec != null
			// && this.selecCampanaEvaluarLec.size() > 0) {
			// for (int i = 0; i < this.selecCampanaEvaluarLec.size(); i++) {
			// Base baseSeleccion = new Base();
			// baseSeleccion = (Base) selecCampanaEvaluarLec.get(i);
			// listCamp.add(baseSeleccion);
			// }
			// }

			this.lecProgramaCorporativoGestDesemList = list;
			this.lecProgramaCorporativoGestDesemDataModel = new DataTableModel(
					this.lecProgramaCorporativoGestDesemList);
			// this.selecCampanaEvaluarLec = listCamp;
			this.indicadorActivoGestDesem = Constants.NUMERO_UNO;
			this.addInfo(
					"Info:",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			this.nivlimpiarFormGDesempenno(f);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina registro a la Grilla -Tab GESTION DESEMPEÑO
	public void eliminarAmbitoGestionDese(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;

			List lista = this.lecProgramaCorporativoGestDesemList;
			Map beanCanasta = (Map) this.beanlecProgramaCorporativoGestDesem;
			String beanCodigo = (String) beanCanasta.get("codigoTipoDesemp");

			int index = -1;
			for (int i = 0; i < lista.size(); i++) {
				Map registro = (Map) lista.get(i);
				String regCodigo = (String) registro.get("codigoTipoDesemp");
				if (StringUtils.equals(regCodigo, beanCodigo)) {
					index = i;
					break;
				}
			}
			List nuevaLista = eliminarObjetosSeleccionados(index, lista);
			this.lecProgramaCorporativoGestDesemList = nuevaLista;
			this.lecProgramaCorporativoGestDesemDataModel = new DataTableModel(
					this.lecProgramaCorporativoGestDesemList);
			this.nivlimpiarFormGDesempenno(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Verifica la Lista antes de ser insertada -Tab Gestion Desempeño
	private String verificarListaProgramaDesempenio(List lista,
			String codigoTipoDesemp) throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			Map criteria = (Map) lista.get(i);
			String regCodigo = (String) criteria.get("codigoTipoDesemp");

			if (StringUtils.equals(regCodigo, codigoTipoDesemp)) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.programaDesempenioYaExiste";
				return proceso;
			}
		}
		return null;
	}

	// Limpia los campos del Tab Gestin Desempeño
	private void nivlimpiarFormGDesempenno(
			MantenimientoLECProgramaCorporativoForm f) {
		f.setNroVecsExitosaIni("");
		f.setNroVecsExitosaFin("");
		f.setSelectedGestionDesemp(new String[] {});
	}

	// Valida los campos antes de insertarlos a la grilla -tab Gestion Desempeño
	private Boolean validarAmbitoGestionDese(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCodTipoDesem())) {
			this.setMensajeAlertaDefault("Tipo Desempeño: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNroVecsExitosaIni())) {
			this.setMensajeAlertaDefault("Nro. Veces Exitosa - Inicio: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNroVecsExitosaFin())) {
			this.setMensajeAlertaDefault("Nro. Veces Exitosa - Fin: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		for (int i = 0; i < this.lecListaTipoDesem.size(); i++) {
			Base base = new Base();
			base = (Base) this.lecListaTipoDesem.get(i);
			if (StringUtils.equals(base.getCodigo(), f.getCodTipoDesem())) {
				f.setDescripTipoDesem(base.getDescripcion());
				break;
			}
		}
		return false;
	}

	// Inserta registros a la grilla -Tab RANKING
	public void guardarAmbitoRanking(ActionEvent actionEvent) {
		try {

			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (validarAmbitoRanking(f))
				return;

			List lecRankingList = this.lecRankingList;

			if (lecRankingList == null)
				lecRankingList = new ArrayList();

			// Validamos si ya existe el tipo, solo debe haber uno de cada uno
			boolean existe = false;
			for (int i = 0; i < lecRankingList.size(); i++) {
				Ranking rkn = (Ranking) lecRankingList.get(i);
				if (StringUtils.equals(f.getCodigoTipoRanking(),
						rkn.getCodigoTipoRanking())) {
					existe = true;
					break;
				}
			}

			if (!existe) {
				List tiposRanking = this.lecTipoRankingList;

				Ranking ranking = new Ranking();
				ranking.setCodigoPais(f.getCodigoPais());
				ranking.setCodigoPrograma(f.getCodigoPrograma());
				ranking.setCodigoTipoRanking(f.getCodigoTipoRanking());
				ranking.setDescripcionTipoRanking(getDescripcionTipoRanking(
						f.getCodigoTipoRanking(), tiposRanking));
				ranking.setCampanyaInicio(f.getCampanyaInicio());
				ranking.setCampanyaFin(f.getCampanyaFin());
				ranking.setNumeroCampanyasCumplimentoPedido(f
						.getNumeroCampanyasCumplimentoPedido());
				ranking.setCodigoTipoMedicion(f.getCodigoTipoMedicion());
				ranking.setIndicadorActivo(Constants.ESTADO_ACTIVO);
				ranking.setNumeroSecuencia(Constants.NUMERO_CERO);
				lecRankingList.add(ranking);
				this.limpiarFormRanking(f, null);
				this.lecRankingList = lecRankingList;
				this.lecRankingDataModel = new DataTableModel(
						this.lecRankingList);
				this.addInfo(
						"Info:",
						this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			} else
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.ranking.alreadyExists"));

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina los registros a la grilla -Tab RANKING
	public void eliminarAmbitoRanking(ActionEvent actionEvent) {
		try {
			List lecRankingList = this.lecRankingList;
			Ranking beanRanking = (Ranking) this.beanlecRanking;

			for (int i = 0; i < lecRankingList.size(); i++) {
				Ranking listaObjeto = new Ranking();
				listaObjeto = (Ranking) lecRankingList.get(i);
				if (StringUtils.equals(listaObjeto.getCodigoTipoRanking(),
						beanRanking.getCodigoTipoRanking())) {
					lecRankingList.remove(i);
					break;
				}
			}
			this.lecRankingList = lecRankingList;
			this.lecRankingDataModel = new DataTableModel(this.lecRankingList);
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			this.addError("Error: ", error);
		}
	}

	// Setea los campos del Tab Ranking
	private void limpiarFormRanking(MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service) {
		f.setCodigoTipoRanking("");
		f.setCampanyaInicio("");
		f.setCampanyaFin("");
		f.setNumeroCampanyasCumplimentoPedido("");
		f.setCodigoTipoMedicion("");
		f.setSelectedItemsRanking(new String[] {});

		this.lecRankingList = new ArrayList();
		this.lecRankingNivelesList = new ArrayList();
		this.lecRankingDataModel = new DataTableModel();
		this.lecRankingNivelesDataModel = new DataTableModel();
		this.codigoTipoRanking = "";
		this.valores = new String[5];
		this.listAux01 = new ArrayList();
		this.listAux02 = new ArrayList();
		this.listAux03 = new ArrayList();

		if (service != null) {
			List tiposRanking = service.getTipoRanking();
			this.lecTipoRankingList = tiposRanking;

			// Limpiamos las listas que usa el tab
			if (tiposRanking != null) {
				for (int i = 0; i < tiposRanking.size(); i++) {
					// session.removeAttribute(Constants.LEC_RANKING_NIVELES_LIST
					// + "_" + ((Base)tiposRanking.get(i)).getCodigo());
				}
			}
			//
		}
	}

	// Obtiene la descripcion del tipo ranking con resopecto a su codigo
	private String getDescripcionTipoRanking(String codigoTipoRanking,
			List tiposRanking) {
		String descripcion = "";
		if (tiposRanking != null) {
			for (int i = 0; i < tiposRanking.size(); i++) {
				if (StringUtils.equals(codigoTipoRanking,
						((Base) tiposRanking.get(i)).getCodigo())) {
					descripcion = ((Base) tiposRanking.get(i)).getDescripcion();
					break;
				}
			}
		}
		return descripcion;
	}

	// Valida los campos antes de insertarlos -Tab Ranking
	private Boolean validarAmbitoRanking(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCodigoTipoRanking())) {
			this.setMensajeAlertaDefault("Tipo Ranking: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getCampanyaInicio())) {
			this.setMensajeAlertaDefault("Campaña Inicio: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getCampanyaFin())) {
			this.setMensajeAlertaDefault("Campaña Fin: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getNumeroCampanyasCumplimentoPedido())) {
			this.setMensajeAlertaDefault("Nro de Campañas de Cumplimiento de Pedido: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (StringUtils.isBlank(f.getCodigoTipoMedicion())) {
			this.setMensajeAlertaDefault("Tipo Medición: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		int codperini = Integer.parseInt(f.getCampanyaInicio());
		int codperfin = Integer.parseInt(f.getCampanyaFin());
		if (codperfin < codperini) {
			this.setMensajeAlertaDefault("Campaña Final debe ser mayor o igual a la Campaña Inicio");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}
		return false;
	}

	// Cargar la lista de Niveles ranking -tab Ranking
	public void cargarNivelesRanking(SelectEvent event) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;

			String codigoRanking = ((Ranking) event.getObject())
					.getCodigoTipoRanking();
			actualizaListaGrillaRanking(f);
			this.codigoTipoRanking = codigoRanking;

			List listaActual = new ArrayList();
			if (StringUtils.equals(codigoRanking, "01"))
				listaActual = this.listAux01;
			if (StringUtils.equals(codigoRanking, "02"))
				listaActual = this.listAux02;
			if (StringUtils.equals(codigoRanking, "03"))
				listaActual = this.listAux03;

			if (listaActual.size() > 0 && listaActual != null) {
				actualizarNivelesSeleccionados(f, listaActual);
				this.lecRankingNivelesList = listaActual;
			} else
				cargarNiveles(f, codigoRanking);

			this.lecRankingNivelesDataModel = new DataTableModel(
					this.lecRankingNivelesList);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// carga las listas auxiliares- lista ranking Niveles
	private void cargarNiveles(MantenimientoLECProgramaCorporativoForm f,
			String codigoTipoRanking) {

		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		List tiposRanking = this.lecTipoRankingList;

		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPrograma", f.getCodigoPrograma());
		params.put("codigoTipoRanking", codigoTipoRanking);
		params.put("descripcionTipoRanking",
				this.getDescripcionTipoRanking(codigoTipoRanking, tiposRanking));

		List lecRankingNivelList = service.getNivelesRanking(params);

		if (StringUtils.equals(codigoTipoRanking, "01"))
			this.listAux01 = lecRankingNivelList;
		if (StringUtils.equals(codigoTipoRanking, "02"))
			this.listAux02 = lecRankingNivelList;
		if (StringUtils.equals(codigoTipoRanking, "03"))
			this.listAux03 = lecRankingNivelList;

		actualizarNivelesSeleccionados(f, lecRankingNivelList);
		this.lecRankingNivelesList = lecRankingNivelList;

	}

	// Actualiza la listas seleccioanadas(2 grilla) segun el nivel
	// seleccionado(1 grilla)
	private void actualizarNivelesSeleccionados(
			MantenimientoLECProgramaCorporativoForm f, List lecRankingNivelList) {
		// Seteamos los seleccionados
		if (lecRankingNivelList != null) {
			String[] selectedItemsRankingNivel = new String[0];
			List seleccionadosList = new ArrayList();
			this.valores = new Object[5];
			for (int i = 0; i < lecRankingNivelList.size(); i++) {
				Map r = (Map) lecRankingNivelList.get(i);
				if (StringUtils.equals(Constants.ESTADO_ACTIVO,
						MapUtils.getString(r, "nivelSeleccionado"))) {
					seleccionadosList.add(MapUtils.getString(r, "codigoNivel"));
					this.valores[i] = lecRankingNivelList.get(i);
				}
			}
			selectedItemsRankingNivel = (String[]) seleccionadosList
					.toArray(selectedItemsRankingNivel);
			f.setSelectedItemsRankingNivel(selectedItemsRankingNivel);
		}
	}

	// Mostrar los Niveles ranking segun la lista seleccionada
	private void actualizaListaGrillaRanking(
			MantenimientoLECProgramaCorporativoForm f) {
		// Actualizamos los niveles
		if (this.valores != null && this.valores.length > 0) {
			String[] params = new String[5];
			for (int i = 0; i < valores.length; i++) {
				Map r = (Map) this.valores[i];
				String codigo = r.get("codigoNivel").toString();
				params[i] = codigo;
			}
			f.setSelectedItemsRankingNivel(params);
		}
		List nuevaRankingNivelList = new ArrayList();
		if (StringUtils.isNotBlank(this.codigoTipoRanking)) {
			if (StringUtils.equals(this.codigoTipoRanking, "01"))
				nuevaRankingNivelList = this.listAux01;
			if (StringUtils.equals(this.codigoTipoRanking, "02"))
				nuevaRankingNivelList = this.listAux02;
			if (StringUtils.equals(this.codigoTipoRanking, "03"))
				nuevaRankingNivelList = this.listAux03;
		}
		if (nuevaRankingNivelList != null && nuevaRankingNivelList.size() > 0) {
			String[] selectedItemsRankingNivel = f
					.getSelectedItemsRankingNivel();

			for (int i = 0; i < nuevaRankingNivelList.size(); i++) {
				Map nivel = (Map) nuevaRankingNivelList.get(i);
				String codigoNivel = MapUtils.getString(nivel, "codigoNivel");
				boolean existe = false;
				for (int j = 0; j < selectedItemsRankingNivel.length; j++) {
					if (StringUtils.equals(codigoNivel,
							selectedItemsRankingNivel[j])) {
						existe = true;
						break;
					}
				}

				if (existe)
					nivel.put("nivelSeleccionado", Constants.ESTADO_ACTIVO);
				else
					nivel.put("nivelSeleccionado", Constants.ESTADO_INACTIVO);

				nuevaRankingNivelList.set(i, nivel);
			}

			if (StringUtils.isNotBlank(this.codigoTipoRanking)) {
				if (StringUtils.equals(this.codigoTipoRanking, "01"))
					this.listAux01 = nuevaRankingNivelList;
				if (StringUtils.equals(this.codigoTipoRanking, "02"))
					this.listAux02 = nuevaRankingNivelList;
				if (StringUtils.equals(this.codigoTipoRanking, "03"))
					this.listAux03 = nuevaRankingNivelList;
			}
		}
	}

	// popup--Ambito Nivel
	// Muestras las Zonas -Popup Ambito Nivel
	public void loadZonasAmbitoNivel(ValueChangeEvent val) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaNivelList = ajax.getloadZonasDisponibles2(
					f.getCodigoPais(), regiones, "", "T");
			f.setZonaNiv(new String[] {});
		} else {
			this.siccZonaNivelList = null;
			f.setZonaNiv(new String[] {});
		}
	}

	// mostrar las regiones o Zonas depende de la seleccion.
	public void showRegionZonaNivel(ValueChangeEvent val) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		this.nivelShowRegionZona = "P";
		f.setRegionNiv(new String[] {});
		f.setZonaNiv(new String[] {});
		this.siccZonaNivelList = null;
		String valor = val.getNewValue().toString();
		if (StringUtils.isNotBlank(valor)) {
			if (StringUtils.equals(valor, "R"))
				this.nivelShowRegionZona = "R";
			if (StringUtils.equals(valor, "Z"))
				this.nivelShowRegionZona = "Z";
		} else
			this.nivelShowRegionZona = "P";
	}

	// Carga los datos, redirecciona al Popup Nivel Exito
	public void ambitoNivelExito(ActionEvent actionEvent) {
		try {
			this.nivelShowRegionZona = "P";
			this.indExcluirNivel = false;
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
			f.setCodigoIncTipoGeo("P");
			f.setCodigoTipoGeoNivel("P");

			List lecNivelAmbGeoList = this.lecNivelAmbGeoList;
			f.setExcluirNivelTmp(Constants.NUMERO_CERO);
			if (lecNivelAmbGeoList != null && lecNivelAmbGeoList.size() > 0) {
				AmbitoGeografico ag = (AmbitoGeografico) lecNivelAmbGeoList
						.get(0);
				f.setExcluirNivelTmp(ag.getIndicadorExclu());
			}

			if (StringUtils
					.equals(f.getExcluirNivelTmp(), Constants.NUMERO_UNO))
				this.indExcluirNivel = true;

			// Abrir PopUp
			String ventana = "PF('dialogAmbitoNivelExito').show()";
			this.getRequestContext().execute(ventana);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void guardarMantenerNivAmbitGeogr(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
			if (validarMantenerNivAmbitGeogr(f))
				return;

			List list = this.lecNivelAmbGeoList;
			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			AmbitoGeografico toc;
			String[] seleccionadas = {};
			String[] object = {};
			String[] subobject0 = {};
			String[] subobject1 = {};

			if (StringUtils.equals(f.getCodigoTipoGeoNivel(), "R")) {
				seleccionadas = StringUtils.split(f.getRegion(), ";");
				for (int i = 0; i < seleccionadas.length; i++) {
					toc = new AmbitoGeografico();
					BeanUtils.copyProperties(toc, f);
					object = StringUtils.split(seleccionadas[i], ",");
					toc.setPais(f.getCodigoPais());
					toc.setCodigoRegion(object[0]);
					toc.setRegion(object[1]);
					toc.setPais(f.getCodigoPais());
					toc.setZona("");
					toc.setCodigoZona("");
					toc.setIndPais("0");
					toc.setIndicadorExclu(f.getExcluirNivelTmp());
					toc.setCodigoAmbGeo(object[0]);

					String verificar = this.verificarListaAmbito(list, toc);
					if (StringUtils.isNotBlank(verificar))
						throw new Exception(this.getResourceMessage(verificar));
					list.add(toc);
				}
			}

			if (StringUtils.equals(f.getCodigoTipoGeoNivel(), "Z")) {
				seleccionadas = StringUtils.split(f.getZona(), ";");
				for (int i = 0; i < seleccionadas.length; i++) {

					toc = new AmbitoGeografico();
					toc.setPais(f.getCodigoPais());
					BeanUtils.copyProperties(toc, f);

					object = StringUtils.split(seleccionadas[i], ",");

					toc.setCodigoZona(object[0]);
					toc.setZona(object[1]);
					toc.setPais(f.getCodigoPais());
					toc.setIndPais("0");
					toc.setCodigoAmbGeo(object[0]);
					toc.setIndicadorExclu(f.getExcluirNivelTmp());

					Map criteriaZona = new HashMap();
					criteriaZona.put("codigoZona", object[0]);
					Map resultado = service.getRegionByZona(criteriaZona);
					String codigoRegion = (String) resultado
							.get("codigoRegion");
					String descripcionRegion = (String) resultado
							.get("descripcionRegion");
					toc.setCodigoRegion(codigoRegion);
					toc.setRegion(descripcionRegion);

					String verificar = this.verificarListaAmbito(list, toc);
					if (StringUtils.isNotBlank(verificar))
						throw new Exception(this.getResourceMessage(verificar));
					list.add(toc);
				}
			}

			if (StringUtils.equals(f.getCodigoTipoGeoNivel(), "P")) {
				toc = new AmbitoGeografico();
				BeanUtils.copyProperties(toc, f);
				toc.setPais(f.getCodigoPais());
				toc.setCodigoRegion("");
				toc.setRegion("");
				toc.setPais(f.getCodigoPais());
				toc.setZona("");
				toc.setIndPais("1");
				toc.setCodigoAmbGeo(f.getCodigoPais());
				toc.setCodigoZona("");
				toc.setIndicadorExclu(f.getExcluirNivelTmp());

				String verificar = this.verificarListaAmbito(list, toc);
				if (StringUtils.isNotBlank(verificar))
					throw new Exception(this.getResourceMessage(verificar));
				list.add(toc);
			}

			this.lecNivelAmbGeoList = list;
			this.lecNivelAmbGeoDataModel = new DataTableModel(
					this.lecNivelAmbGeoList);
			this.indicadorActivoAmbGeoNivel = Constants.NUMERO_UNO;
			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			f.setCodigoTipoGeoNivel("P");
			this.nivelShowRegionZona = "P";
			f.setRegionNiv(new String[] {});
			f.setZonaNiv(new String[] {});
			this.siccZonaNivelList = null;
			this.indExcluirNivel = false;

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void eliminarMantenerNivAmbitGeogr(ActionEvent actionEvent) {
		try {
			List eliminar = this.ambitoNivelSeleccion;
			String seleccionados[] = new String[eliminar.size()];
			for (int i = 0; i < eliminar.size(); i++) {
				AmbitoGeografico toc = new AmbitoGeografico();
				toc = (AmbitoGeografico) eliminar.get(i);
				String codigo = toc.getCodigoAmbGeo();
				seleccionados[i] = codigo;
			}

			List listaFuente = this.lecNivelAmbGeoList;
			List listaRetorno = new ArrayList();
			if (seleccionados != null && listaFuente != null) {
				for (int j = 0; j < seleccionados.length; j++) {
					for (int i = 0; i < listaFuente.size(); i++) {
						AmbitoGeografico ct = (AmbitoGeografico) listaFuente
								.get(i);
						if (ct != null) {
							if (StringUtils.equals(ct.getCodigoAmbGeo(),
									seleccionados[j])) {
								listaFuente.set(i, null);
								break;
							}
						}

					}
				}

				for (int i = 0; i < listaFuente.size(); i++) {
					if (listaFuente.get(i) != null)
						listaRetorno.add(listaFuente.get(i));
				}
			}

			this.lecNivelAmbGeoList = listaRetorno;
			this.lecNivelAmbGeoDataModel = new DataTableModel(
					this.lecNivelAmbGeoList);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Salir del Popup -Ambito Nivel
	public void salirPopupAmbitoNivel(ActionEvent event) {
		try {
			String ventana = "PF('dialogAmbitoNivelExito').hide()";
			this.getRequestContext().execute(ventana);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	private String verificarListaAmbito(List lista,
			AmbitoGeografico ambitoGeografico) {
		String estadoAmbitoBean = "P";
		if (StringUtils.isBlank(ambitoGeografico.getCodigoRegion())
				&& StringUtils.isBlank(ambitoGeografico.getCodigoZona())) {
			estadoAmbitoBean = "P";
		} else if (!StringUtils.isBlank(ambitoGeografico.getCodigoRegion())
				&& StringUtils.isBlank(ambitoGeografico.getCodigoZona())) {
			estadoAmbitoBean = "R";
		}
		if (!StringUtils.isBlank(ambitoGeografico.getCodigoZona())) {
			estadoAmbitoBean = "Z";
		}

		for (int i = 0; i < lista.size(); i++) {
			AmbitoGeografico p = (AmbitoGeografico) lista.get(i);
			if (StringUtils.equals(p.getCodigoAmbGeo(),
					ambitoGeografico.getCodigoAmbGeo())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.ambitoYaExiste";
				return proceso;
			}
			/* Verificando el Ambito */
			String estadoAmbitoLista = "P";
			if (StringUtils.isBlank(p.getCodigoRegion())
					&& StringUtils.isBlank(p.getCodigoZona())) {
				estadoAmbitoLista = "P";
			} else if (!StringUtils.isBlank(p.getCodigoRegion())
					&& StringUtils.isBlank(p.getCodigoZona())) {
				estadoAmbitoLista = "R";
			}
			if (!StringUtils.isBlank(p.getCodigoZona())) {
				estadoAmbitoLista = "Z";
			}

			if ("P".equals(estadoAmbitoBean)) {
				if (StringUtils.equals(estadoAmbitoBean, estadoAmbitoLista)) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaPais";
					return proceso;
				}
			}

			if ("R".equals(estadoAmbitoBean)) {
				if (estadoAmbitoLista.equals("P")) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaPaisRegion";
					return proceso;
				}
				if (estadoAmbitoLista.equals("Z")
						&& p.getCodigoRegion().equals(
								ambitoGeografico.getCodigoRegion())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaRegion";
					return proceso;
				}
			}
			if ("Z".equals(estadoAmbitoBean)) {
				if (estadoAmbitoLista.equals("P")) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaPaisZona";
					return proceso;
				}
				if (estadoAmbitoLista.equals("R")
						&& p.getCodigoRegion().equals(
								ambitoGeografico.getCodigoRegion())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelAlcanceCobranzaZona";
					return proceso;
				}
			}

		}
		return null;
	}

	private Boolean validarMantenerNivAmbitGeogr(
			MantenimientoLECProgramaCorporativoForm f) {
		int pivot = 0;
		String tmpregion = "";
		String tmpzona = "";
		List regionlist = this.siccRegionList;

		if (StringUtils.equals(f.getCodigoTipoGeoNivel(), "R")) {
			for (int i = 0; i < f.getRegionNiv().length; i++) {
				if (StringUtils.isNotBlank(f.getRegionNiv()[i])) {
					for (int j = 0; j < this.siccRegionList.size(); j++) {
						Base base = new Base();
						base = (Base) this.siccRegionList.get(j);
						if (StringUtils.equals(f.getRegionNiv()[i],
								base.getCodigo())) {
							tmpregion = tmpregion + f.getRegionNiv()[i] + ","
									+ base.getDescripcion() + ";";
							pivot = 1;
							break;
						}
					}

				}
			}
			f.setRegion(tmpregion);
		} else if (StringUtils.equals(f.getCodigoTipoGeoNivel(), "Z")) {
			for (int k = 0; k < f.getZonaNiv().length; k++) {
				if (StringUtils.isNotBlank(f.getZonaNiv()[k])) {
					for (int i = 0; i < this.siccZonaNivelList.length; i++) {
						if (StringUtils.equals(f.getZonaNiv()[k],
								this.siccZonaNivelList[i].getValue())) {
							tmpzona = tmpzona + f.getZonaNiv()[k] + ","
									+ this.siccZonaNivelList[i].getLabel()
									+ ";";
							pivot = 1;
							break;
						}
					}
				}
			}
			f.setZona(tmpzona);
		} else
			pivot = 1;

		if (pivot == 0) {
			this.setMensajeAlertaDefault("Ambito Geográfico: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (this.indExcluirNivel)
			f.setExcluirNivelTmp(Constants.NUMERO_UNO);
		else
			f.setExcluirNivelTmp(Constants.NUMERO_CERO);

		return false;
	}

	// Popup--Pedido Lider
	// Muestra las Zonas -Ambito Nivel pedido
	public void loadZonasAmbitoPedido(ValueChangeEvent val) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaPedidoList = ajax.getloadZonasDisponibles2(
					f.getCodigoPais(), regiones, "", "T");
			f.setListaIncZon(new String[] {});
		} else {
			this.siccZonaPedidoList = null;
			f.setListaIncZon(new String[] {});
		}
	}

	// mostrar las regiones o Zonas -Nivel pedido
	public void showRegionZonaPedido(ValueChangeEvent val) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		this.pedidoShowRegionZona = "P";
		f.setRegionNiv(new String[] {});
		f.setZonaNiv(new String[] {});
		this.siccZonaPedidoList = null;
		String valor = val.getNewValue().toString();
		if (StringUtils.isNotBlank(valor)) {
			if (StringUtils.equals(valor, "R"))
				this.pedidoShowRegionZona = "R";
			if (StringUtils.equals(valor, "Z"))
				this.pedidoShowRegionZona = "Z";
		} else
			this.pedidoShowRegionZona = "P";

	}

	// Inserta registros en la poup Nivel Pedido Lider
	public void guardarMantenerPedidoLider(ActionEvent actionEvent) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
			if (validarMantenerPedidoLider(f))
				return;

			List list = this.lecPedLidAmbGeoList;

			if (CollectionUtils.isEmpty(list))
				list = new ArrayList();

			AmbitoGeografico toc;
			String[] seleccionadas = {};
			String[] object = {};
			String[] subobject0 = {};
			String[] subobject1 = {};

			if (StringUtils.equals(f.getCodigoIncTipoGeo(), "R")) {
				seleccionadas = StringUtils.split(f.getTmpRegionSele(), ";");
				for (int i = 0; i < seleccionadas.length; i++) {
					toc = new AmbitoGeografico();
					BeanUtils.copyProperties(toc, f);
					object = StringUtils.split(seleccionadas[i], ",");
					toc.setPais(f.getCodigoPais());
					toc.setCodigoRegion(object[0]);
					toc.setRegion(object[1]);
					toc.setPais(f.getCodigoPais());
					toc.setZona("");
					toc.setCodigoZona("");
					toc.setIndPais("0");
					toc.setCodigoAmbGeo(object[0]);
					toc.setIndicadorExclu(f.getExcluirTmp());

					String verificar = this.verificarListaAmbito(list, toc);
					if (StringUtils.isNotBlank(verificar))
						throw new Exception(this.getResourceMessage(verificar));
					list.add(toc);
				}
			}

			if (StringUtils.equals(f.getCodigoIncTipoGeo(), "Z")) {
				seleccionadas = StringUtils.split(f.getTmpZonaSele(), ";");
				for (int i = 0; i < seleccionadas.length; i++) {
					toc = new AmbitoGeografico();
					toc.setPais(f.getCodigoPais());
					BeanUtils.copyProperties(toc, f);

					object = StringUtils.split(seleccionadas[i], ",");

					toc.setCodigoZona(object[0]);
					toc.setZona(object[1]);
					toc.setPais(f.getCodigoPais());
					toc.setIndPais("0");
					toc.setCodigoAmbGeo(object[0]);
					toc.setIndicadorExclu(f.getExcluirTmp());

					Map criteriaZona = new HashMap();
					criteriaZona.put("codigoZona", object[0]);
					Map resultado = service.getRegionByZona(criteriaZona);
					String codigoRegion = (String) resultado
							.get("codigoRegion");
					String descripcionRegion = (String) resultado
							.get("descripcionRegion");
					toc.setCodigoRegion(codigoRegion);
					toc.setRegion(descripcionRegion);

					String verificar = this.verificarListaAmbito(list, toc);
					if (StringUtils.isNotBlank(verificar))
						throw new Exception(this.getResourceMessage(verificar));
					list.add(toc);
				}
			}

			if (StringUtils.equals(f.getCodigoIncTipoGeo(), "P")) {
				toc = new AmbitoGeografico();

				BeanUtils.copyProperties(toc, f);
				toc.setPais(f.getCodigoPais());
				toc.setCodigoRegion("");
				toc.setRegion("");
				toc.setPais(f.getCodigoPais());
				toc.setZona("");
				toc.setIndPais("1");
				toc.setCodigoAmbGeo(f.getCodigoPais());
				toc.setCodigoZona("");
				toc.setIndicadorExclu(f.getExcluirTmp());

				String verificar = this.verificarListaAmbito(list, toc);
				if (StringUtils.isNotBlank(verificar))
					throw new Exception(this.getResourceMessage(verificar));
				list.add(toc);
			}

			this.lecPedLidAmbGeoList = list;
			this.lecPedLidAmbGeoDataModel = new DataTableModel(
					this.lecPedLidAmbGeoList);
			this.indicadorActivoAmbGeoPedidoLider = Constants.NUMERO_UNO;
			this.addInfo(
					"Info: ",
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.datos.insert"));
			f.setCodigoIncTipoGeo("P");
			this.pedidoShowRegionZona = "P";
			f.setListaIncReg(new String[] {});
			f.setListaIncZon(new String[] {});
			this.siccZonaPedidoList = null;
			this.indExcluir = false;

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Elimina registros en la poup Nivel Pedido Lider
	public void eliminarPedidoLider(ActionEvent actionEvent) {
		try {
			List eliminar = this.ambitoPedidoSeleccion;
			String seleccionados[] = new String[eliminar.size()];
			for (int i = 0; i < eliminar.size(); i++) {
				AmbitoGeografico toc = new AmbitoGeografico();
				toc = (AmbitoGeografico) eliminar.get(i);
				String codigo = toc.getCodigoAmbGeo();
				seleccionados[i] = codigo;
			}

			List listaFuente = this.lecPedLidAmbGeoList;
			List listaRetorno = new ArrayList();
			if (seleccionados != null && listaFuente != null) {
				for (int j = 0; j < seleccionados.length; j++) {
					for (int i = 0; i < listaFuente.size(); i++) {
						AmbitoGeografico ct = (AmbitoGeografico) listaFuente
								.get(i);
						if (ct != null) {
							if (StringUtils.equals(ct.getCodigoAmbGeo(),
									seleccionados[j]))
								listaFuente.set(i, null);
						}
					}
				}
				for (int i = 0; i < listaFuente.size(); i++) {
					if (listaFuente.get(i) != null)
						listaRetorno.add(listaFuente.get(i));
				}
			}

			this.lecPedLidAmbGeoList = listaRetorno;
			this.lecPedLidAmbGeoDataModel = new DataTableModel(
					this.lecPedLidAmbGeoList);
			this.indicadorActivoAmbGeoPedidoLider = Constants.NUMERO_UNO;

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Valida los campos antes de insertarlos a la grilla -popup pedidoLider
	private Boolean validarMantenerPedidoLider(
			MantenimientoLECProgramaCorporativoForm f) {
		int pivot = 0;
		String tmpregion = "";
		String tmpzona = "";
		List regionlist = this.siccRegionList;

		if (StringUtils.equals(f.getCodigoIncTipoGeo(), "R")) {
			for (int i = 0; i < f.getListaIncReg().length; i++) {
				if (StringUtils.isNotBlank(f.getListaIncReg()[i])) {
					for (int j = 0; j < this.siccRegionList.size(); j++) {
						Base base = new Base();
						base = (Base) this.siccRegionList.get(j);
						if (StringUtils.equals(f.getListaIncReg()[i],
								base.getCodigo())) {
							tmpregion = tmpregion + f.getListaIncReg()[i] + ","
									+ base.getDescripcion() + ";";
							pivot = 1;
							break;
						}
					}

				}
			}
			f.setTmpRegionSele(tmpregion);
		} else if (StringUtils.equals(f.getCodigoIncTipoGeo(), "Z")) {
			for (int k = 0; k < f.getListaIncZon().length; k++) {
				if (StringUtils.isNotBlank(f.getListaIncZon()[k])) {
					for (int i = 0; i < this.siccZonaPedidoList.length; i++) {
						if (StringUtils.equals(f.getListaIncZon()[k],
								this.siccZonaPedidoList[i].getValue())) {
							tmpzona = tmpzona + f.getListaIncZon()[k] + ","
									+ this.siccZonaPedidoList[i].getLabel()
									+ ";";
							pivot = 1;
							break;
						}
					}
				}
			}
			f.setTmpZonaSele(tmpzona);
		} else
			pivot = 1;

		if (pivot == 0) {
			this.setMensajeAlertaDefault("Ambito Geográfico: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}

		if (this.indExcluir)
			f.setExcluirTmp(Constants.NUMERO_UNO);
		else
			f.setExcluirTmp(Constants.NUMERO_CERO);
		return false;
	}

	public void ambitoPedidoLider(ActionEvent actionEvent) {
		try {
			this.pedidoShowRegionZona = "P";
			this.indExcluir = false;
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
			f.setCodigoIncTipoGeo("P");

			List lecPedLidAmbGeoList = this.lecPedLidAmbGeoList;
			f.setExcluirTmp(Constants.NUMERO_CERO);
			if (lecPedLidAmbGeoList != null && lecPedLidAmbGeoList.size() > 0) {
				AmbitoGeografico ag = (AmbitoGeografico) lecPedLidAmbGeoList
						.get(0);
				f.setExcluirTmp(ag.getIndicadorExclu());
			}

			if (StringUtils.equals(f.getExcluirTmp(), Constants.NUMERO_UNO))
				this.indExcluir = true;

			String ventana = "PF('dialogAmbitoPedidoLider').show()";
			this.getRequestContext().execute(ventana);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Oculta el Popup Ambito pedido Lider
	public void salirPopupAmbitoPedido(ActionEvent event) {
		try {
			String ventana = "PF('dialogAmbitoPedidoLider').hide()";
			this.getRequestContext().execute(ventana);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Guardar programa Corporativo

	private void setValidar(Pais pais, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {

		Map criteriaVerificar = new HashMap();
		criteriaVerificar.put("codigoPais", pais.getCodigo());
		criteriaVerificar.put("codigoPrograma", pc.getCodigoPrograma());
		criteriaVerificar.put("codigoPeriodo", f.getPeriodoInicio());

		if (!f.isNewRecord()) {
			criteriaVerificar.put("codigoPeriodoFin", f.getPeriodoFin());
		}
		Integer verificarProgramaActivo = service
				.getListaProgramasActivosCampanna(criteriaVerificar);
		if (verificarProgramaActivo.intValue() > 0)
			throw new Exception(
					this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.programaActivoYaExiste"));

		List list = this.lecProgramaCorporativoNivelList;

		// List lecIncTipoComiList = this.lecIncTipoComiList;
		// this.actualizaListaGrillaIncentivo1(f,lecIncTipoComiList);

		// Validando Niveles
		if (list != null && list.size() > 0) {

			Integer pesoNivel = f.getMenorPesoNivel();
			for (int i = 0; i < list.size(); i++) {
				Nivel nivel = (Nivel) list.get(i);
				Map criteriaNivel = new HashMap();
				criteriaNivel.put("codigoNivel", nivel.getCodigoNivel());
				Integer pesoNivelRegistro = service.getPesoNivel(criteriaNivel);
				if (pesoNivelRegistro.intValue() != pesoNivel.intValue()) {
					String mensaje = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro")
							+ " "
							+ (i + 1)
							+ ": "
							+ this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.valorPesoNivelNoCorrespondeRegistro");
					throw new Exception(mensaje);
				}
				Integer nroPedidoInicial = new Integer(
						nivel.getNroPedidoInicial());
				Integer nroPedidoFinal = new Integer(nivel.getNroPedidoFinal());
				if (nroPedidoInicial.intValue() > nroPedidoFinal.intValue()) {
					String mensaje = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro")
							+ " "
							+ (i + 1)
							+ ": "
							+ this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.pedidoInicialMenorPedidoFinalNivel");
					throw new Exception(mensaje);
				}

				if (StringUtils.isBlank(nivel.getMontoVentaMinimo())
						&& StringUtils.isNotBlank(nivel.getMontoVentaMaximo())) {
					String mensaje = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro")
							+ " "
							+ (i + 1)
							+ ": "
							+ this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.montoVentaMinimonoVacioNivel");
					throw new Exception(mensaje);
				}

				if (StringUtils.isNotBlank(nivel.getMontoVentaMinimo())
						&& StringUtils.isNotBlank(nivel.getMontoVentaMaximo())) {
					BigDecimal montoVentaMinimo = new BigDecimal(
							nivel.getMontoVentaMinimo());
					BigDecimal montoVentaMaximo = new BigDecimal(
							nivel.getMontoVentaMaximo());
					if (montoVentaMinimo.doubleValue() > montoVentaMaximo
							.doubleValue()) {
						String mensaje = this
								.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro")
								+ " "
								+ (i + 1)
								+ ": "
								+ this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.montoVentaMinimoMenorMontoVentaMaximoNivel");
						throw new Exception(mensaje);
					}
				}

				pesoNivel = pesoNivel + 1;
			}

			if (list.size() > 1) {
				Nivel nivelBase = (Nivel) list.get(0);
				Integer nroPedidoFinal = new Integer(
						nivelBase.getNroPedidoFinal());
				String montoVentaMaximo = nivelBase.getMontoVentaMaximo();
				for (int i = 1; i < list.size(); i++) {
					Nivel nivel = (Nivel) list.get(i);
					Integer nroPedidoInicial = new Integer(
							nivel.getNroPedidoInicial());
					int validarPedido = nroPedidoFinal.intValue() + 1;
					if (validarPedido != nroPedidoInicial.intValue()) {
						String mensaje = this
								.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro")
								+ " "
								+ (i + 1)
								+ ": "
								+ this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.pedidoInicialdebeserConsecutivo");
						throw new Exception(mensaje);
					}

					/*
					 * if (StringUtils.isBlank(montoVentaMaximo) &&
					 * StringUtils.isNotBlank(nivel.getMontoVentaMinimo())) {
					 * String mensaje = messageResources.getMessage(
					 * "mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro"
					 * ) + " " +(i+1) +": " + messageResources.getMessage(
					 * "mantenimientoLECProgramaCorporativoNivelForm.error.montoVentaMinimodebeserConsecutivo"
					 * ); throw new Exception(mensaje); } if
					 * (StringUtils.isNotBlank(montoVentaMaximo) &&
					 * StringUtils.isBlank(nivel.getMontoVentaMinimo())) {
					 * String mensaje = messageResources.getMessage(
					 * "mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro"
					 * ) + " " +(i+1) +": " + messageResources.getMessage(
					 * "mantenimientoLECProgramaCorporativoNivelForm.error.montoVentaMinimodebeserConsecutivo"
					 * ); throw new Exception(mensaje); }
					 */

					if (StringUtils.isNotBlank(montoVentaMaximo)
							&& StringUtils.isNotBlank(nivel
									.getMontoVentaMinimo())) {
						BigDecimal dmontoVentaMinimo = new BigDecimal(
								nivel.getMontoVentaMinimo());
						BigDecimal dmontoVentaMaximo = new BigDecimal(
								montoVentaMaximo);

						if (dmontoVentaMinimo.doubleValue() == 0)
							dmontoVentaMinimo = new BigDecimal(1);

						if (dmontoVentaMaximo.longValue() > 0) {
							double validarMonto = dmontoVentaMaximo
									.doubleValue() + 1;
							if (validarMonto != dmontoVentaMinimo.doubleValue()) {
								String mensaje = this
										.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.NroRegistro")
										+ " "
										+ (i + 1)
										+ ": "
										+ this.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.montoVentaMinimodebeserConsecutivo");
								throw new Exception(mensaje);
							}
						}
					}

					if (StringUtils.isNotBlank(nivel.getMontoVentaMinimo())) {
						BigDecimal bdMontoVentaMinimo = new BigDecimal(
								nivel.getMontoVentaMinimo());
						if (bdMontoVentaMinimo.longValue() > 0
								&& StringUtils.isBlank(f
										.getCampannaInicialNivel())) {
							String mensaje = this
									.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.campanyaIincioNivel");
							throw new Exception(mensaje);
						}
					}
					nivelBase = nivel;
					nroPedidoFinal = new Integer(nivelBase.getNroPedidoFinal());
					montoVentaMaximo = nivelBase.getMontoVentaMaximo();
				}
			}

			// RCR PER-SICC-2015-0429
			for (int j = 0; j < list.size(); j++) {
				Nivel nivel = (Nivel) list.get(j);
				if (StringUtils.isBlank(nivel.getIngresoMeta())) {
					String mensaje = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.ingresoMeta");
					throw new Exception(mensaje);
				} else {
					int ingresoMeta = Integer.parseInt(nivel.getIngresoMeta());
					if (ingresoMeta < 0) {
						String mensaje = this
								.getResourceMessage("mantenimientoLECProgramaCorporativoNivelForm.error.ingresoMeta");
						throw new Exception(mensaje);
					}
				}

				// PER-SiCC-2015-0548
				if (StringUtils.isBlank(nivel.getMetaCapitalizacion())) {
					String mensaje = this
							.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.metaCapitalizacion");
					throw new Exception(mensaje);
				} else {
					int metaCapitalizacion = Integer.parseInt(nivel
							.getMetaCapitalizacion());
					if (metaCapitalizacion < 0) {
						String mensaje = this
								.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.metaCapitalizacion");
						throw new Exception(mensaje);
					} else {
						if (metaCapitalizacion > 0) {
							String condCapitalizacion = nivel
									.getCondCapitalizacion();
							if (condCapitalizacion.equals("0")) {
								String mensaje = this
										.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.condCapitalizacion");
								throw new Exception(mensaje);
							}
						}
					}
				}
			}
		}
	}

	private void agregarNiveles(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {

		List list = this.lecProgramaCorporativoNivelList;
		List lecIncTipoComiList = this.lecIncTipoComiList;

		validarMontoVentaNivel(list, false);

		// INSERTAR PROGRAMA NIVEL
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Nivel nivel = (Nivel) list.get(i);
				nivel.setCodigoPais(pais.getCodigo());
				nivel.setCodigoTipoComi("1");
				nivel.setAuditInfo(usuario.getAuditInfo());
				nivel.setCodigoPrograma(pc.getCodigoPrograma());
				if (StringUtils.isBlank(nivel.getMontoVentaMaximo()))
					nivel.setMontoVentaMaximo("0");
				if (StringUtils.isBlank(nivel.getMontoVentaMinimo()))
					nivel.setMontoVentaMinimo("0");

				// INTEGRADO CON LA LISTA DE INCENTIVOS
				if (lecIncTipoComiList != null && lecIncTipoComiList.size() > 0) {
					for (int i1 = 0; i1 < lecIncTipoComiList.size(); i1++) {
						Nivel nivel1 = (Nivel) lecIncTipoComiList.get(i1);
						if (nivel.getCodigoNivel().compareTo(
								nivel1.getCodigoNivel()) == 0) {
							nivel.setMontPediCons(nivel1.getMontPediCons());
							nivel.setMontPediNCon(nivel1.getMontPediNCon());
							nivel.setMontTole(nivel1.getMontTole());
							break;
						}

					}
				}
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma", pc.getCodigoPrograma());
				criteria.put("codigoNivel", nivel.getCodigoNivel());
				List listTmp = service.getProgramaNivelList(criteria);
				if (listTmp != null && listTmp.size() > 0)
					service.updateProgramaNivel(nivel);
				else
					service.insertProgramaNivel(nivel);
			}
		}

		// Eliminaciones
		if (f.isNewRecord())
			return;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		List listTmp = service.getProgramaNivelList(criteria);
		for (int i = 0; i < listTmp.size(); i++) {
			Nivel tmp = new Nivel();
			BeanUtils.copyProperties(tmp, listTmp.get(i));
			tmp.setCodigoPais(pais.getCodigo());
			tmp.setCodigoPrograma(pc.getCodigoPrograma());

			String verificar = this.verificarListaNivel(list, tmp);
			if (StringUtils.isBlank(verificar))
				service.deleteProgramaNivel(tmp);

		}
	}

	private void agregarCampanaExigencia(Pais pais, Usuario usuario,
			MantenimientoLECProgramaCorporativoForm f, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service,
			String indicadorTipoExigencia) throws Exception {
		CampanaExigencia ce = new CampanaExigencia();
		ce.setCodigoPais(pais.getCodigo());
		ce.setCodigoPrograma(pc.getCodigoPrograma());
		ce.setAuditInfo(usuario.getAuditInfo());
		ce.setTipoExigencia(indicadorTipoExigencia);
		ce.setCampanaInicio(f.getPeriodoInicioNivel());
		ce.setCampanaFin(f.getPeriodoFinNivel());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		criteria.put("tipoExigencia", indicadorTipoExigencia);
		List listTmp = service.getCampanaExigenciaList(criteria);
		if (listTmp != null && listTmp.size() > 0)
			service.updateCampanaExigencia(ce);
		else
			service.insertCampanaExigencia(ce);
	}

	private void deleteCampanaExigencia(Pais pais, Usuario usuario,
			MantenimientoLECProgramaCorporativoForm f, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service,
			String indicadorTipoExigencia) throws Exception {
		CampanaExigencia ce = new CampanaExigencia();
		ce.setCodigoPais(pais.getCodigo());
		ce.setCodigoPrograma(pc.getCodigoPrograma());
		ce.setTipoExigencia(indicadorTipoExigencia);
		service.deleteCampanaExigencia(ce);
	}

	private void agregarNivAmbitoGeografico(Pais pais, Usuario usuario,
			String codigoPeriodo, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		AmbitoGeografico agniv;
		List listniv = this.lecNivelAmbGeoList;
		if (!f.isNewRecord()) {
			agniv = new AmbitoGeografico();
			agniv.setCodigoPais(pais.getCodigo());
			agniv.setCodigoPrograma(pc.getCodigoPrograma());
			agniv.setCodTipoUso("02");
			service.deleteAmbitoGeograficoFisico(agniv);
		}

		if (listniv != null) {
			for (int j = 0; j < listniv.size(); j++) {
				agniv = (AmbitoGeografico) listniv.get(j);
				agniv.setCodigoPais(pais.getCodigo());
				agniv.setCodigoPrograma(pc.getCodigoPrograma());
				agniv.setAuditInfo(usuario.getAuditInfo());

				agniv.setCampanaProceso(codigoPeriodo);
				agniv.setCodTipoUso("02");

				agniv.setSecuencia(service.getNextCodAmbitGeogr());
				service.insertAmbitoGeografico(agniv);
			}
		}
	}

	private void agregarCampanaExigenciaNivel(Pais pais, Usuario usuario,
			MantenimientoLECProgramaCorporativoForm f, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service,
			String indicadorTipoExigencia) throws Exception {
		CampanaExigencia ce = new CampanaExigencia();
		ce.setCodigoPais(pais.getCodigo());
		ce.setCodigoPrograma(pc.getCodigoPrograma());
		ce.setAuditInfo(usuario.getAuditInfo());
		ce.setTipoExigencia(indicadorTipoExigencia);
		ce.setCampanaInicio(f.getCampannaInicialNivel());
		ce.setCampanaFin(f.getCampannaFinalNivel());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		criteria.put("tipoExigencia", indicadorTipoExigencia);
		List listTmp = service.getCampanaExigenciaList(criteria);
		if (listTmp != null && listTmp.size() > 0)
			service.updateCampanaExigencia(ce);
		else
			service.insertCampanaExigencia(ce);
	}

	private void agregarCobTramo(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		List listramos = this.lecTramoList;

		for (int i1 = 0; i1 < listramos.size(); i1++) {
			CobranzaTramo ct = (CobranzaTramo) listramos.get(i1);
			ct.setCodigoPais(pais.getCodigo());
			ct.setCodigoPrograma(pc.getCodigoPrograma());
			ct.setAuditInfo(usuario.getAuditInfo());

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", pc.getCodigoPrograma());
			criteria.put("codTramo", ct.getCodTramo());
			List listTmp = service.getProgramaCobranzaTramoList(criteria);
			if (listTmp != null && listTmp.size() > 0) {
				service.updateCobranzaTramo(ct);
			} else {
				ct.setNumTramo(String.format("%02d",
						Integer.parseInt(ct.getCodTramo())));
				service.insertCobranzaTramo(ct);
			}

		}
		List listTablasAuxiliares = this.lecProgramaCorporativoObjCobTablasAuxiliaresList;

		if (listTablasAuxiliares != null && listTablasAuxiliares.size() > 0) {
			for (int i = 0; i < listTablasAuxiliares.size(); i++) {
				Map criteria = (Map) listTablasAuxiliares.get(i);
				service.deleteCobranzaObjetivoIndicador(criteria);
				service.deleteCobranzaObjetivoTramoIndicador(criteria);
				service.deleteAmbitoGeograficoIndicador(criteria);
			}
		}

	}

	private void agregarCobAmbGeo(Pais pais, Usuario usuario,
			String codigoPeriodo, MantenimientoLECProgramaCorporativoForm f,
			ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {

		AmbitoGeografico ag;
		List list1 = this.lecAmbGeoList;

		for (int j = 0; j < list1.size(); j++) {
			ag = (AmbitoGeografico) list1.get(j);
			ag.setCodigoPais(pais.getCodigo());
			ag.setCodigoPrograma(pc.getCodigoPrograma());
			ag.setAuditInfo(usuario.getAuditInfo());
			ag.setCampanaProceso(codigoPeriodo);
			ag.setCodTipoUso("01");
			if (StringUtils.isBlank(ag.getSecuencia())) {
				ag.setSecuencia(service.getNextCodAmbitGeogr());
				service.insertAmbitoGeografico(ag);
			} else {
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma", pc.getCodigoPrograma());
				criteria.put("codTipoUso", "01");
				criteria.put("secuencia", ag.getSecuencia());
				List listTmp = service.getAmbitoGeograficoList(criteria);
				if (listTmp != null && listTmp.size() > 0)
					service.updateAmbitoGeografico(ag);
				else
					service.insertAmbitoGeografico(ag);

			}
		}
	}

	private void agregarCobObjetivoCobranza(Pais pais, Usuario usuario,
			MantenimientoLECProgramaCorporativoForm f, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		AmbitoGeografico ag;
		TramoObjetivoCobranza toj;
		CobranzaTramo ct;
		List listramos = this.lecTramoList;
		List list1 = this.lecAmbGeoList;
		List list2 = this.lecProgramaCorporativoObjCobList;
		List listCabecera = new ArrayList();

		for (int k = 0; k < list2.size(); k++) {
			ObjetivoCobranza oc = (ObjetivoCobranza) list2.get(k);
			oc.setCodigoPais(pais.getCodigo());
			oc.setCodigoPrograma(pc.getCodigoPrograma());
		}

		// this.actualizaListaGrillaCobranza(request, f, list2);

		/* Parte Cabecera */
		for (int k = 0; k < list2.size(); k++) {
			ObjetivoCobranza oc = new ObjetivoCobranza();
			oc = (ObjetivoCobranza) list2.get(k);
			if (StringUtils.isBlank(oc.getSecuencia()))
				for (int k1 = 0; k1 < list1.size(); k1++) {
					ag = (AmbitoGeografico) list1.get(k1);
					if (oc.getNumAmbGeo() != null) {
						if (oc.getNumAmbGeo().compareTo(ag.getCodigoAmbGeo()) == 0)
							oc.setSecuencia(ag.getSecuencia());
					} else {
						if (oc.getAmbitoGeogra()
								.compareTo(ag.getCodigoAmbGeo()) == 0) {
							oc.setSecuencia(ag.getSecuencia());
						}
					}
				}
			String verificar = this.verificarListaObjetivoCobranzaSinTramo(
					listCabecera, oc);
			if (StringUtils.isBlank(verificar))
				listCabecera.add(oc);
		}

		for (int k = 0; k < listCabecera.size(); k++) {
			ObjetivoCobranza oc = (ObjetivoCobranza) listCabecera.get(k);
			oc.setAuditInfo(usuario.getAuditInfo());

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", pc.getCodigoPrograma());
			criteria.put("secuencia", oc.getSecuencia());
			criteria.put("codTipoMedi", oc.getCodTipoMedi());

			List listTmp = service.getObjetivoCobranzaList(criteria);
			if (listTmp != null && listTmp.size() > 0)
				service.updateObjetivoCobranza(oc);
			else
				service.insertObjetivoCobranza(oc);
		}

		/* Parte Detalle */
		for (int k = 0; k < list2.size(); k++) {
			ObjetivoCobranza oc = (ObjetivoCobranza) list2.get(k);
			toj = new TramoObjetivoCobranza();
			toj.setCodigoPais(pais.getCodigo());
			toj.setCodigoPrograma(pc.getCodigoPrograma());
			toj.setAuditInfo(usuario.getAuditInfo());
			toj.setCodTipoMedi(oc.getCodTipoMedi());
			toj.setNroDias(oc.getNroDias());
			toj.setNroDiasExtras(oc.getNroDiasExtras());
			toj.setPorcCob(oc.getPorcCob());
			toj.setProcMinCob(oc.getProcMinCob());
			toj.setCodTramo(oc.getNroTramo());
			toj.setSecuencia(oc.getSecuencia());
			toj.setDporcCob(new BigDecimal(oc.getPorcCob()));
			toj.setDprocMinCob(new BigDecimal(oc.getProcMinCob()));

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", pc.getCodigoPrograma());
			criteria.put("codTramo", toj.getCodTramo());
			criteria.put("secuencia", toj.getSecuencia());
			criteria.put("codigoTipoMedi", toj.getCodTipoMedi());

			List listTmp = service.getTramoObjetivoCobranzaList(criteria);
			if (listTmp != null && listTmp.size() > 0)
				service.updateTramoObjetivoCobranza(toj);
			else
				service.insertTramoObjetivoCobranza(toj);
		}

		// ELIMINACIONES
		if (f.isNewRecord())
			return;

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());

		// Eliminacion Objetivo Cobranza Tramo
		List lisTramoObjCobranza = service
				.getTramoObjetivoCobranzaList(criteria);

		//
		String valor1 = "";
		String valor2 = "";
		boolean eliminar = false;
		if (!this.listaEliminaCobranzaTemp.isEmpty()) {
			for (int i = 0; i < this.listaEliminaCobranzaTemp.size(); i++) {
				ObjetivoCobranza prueba = new ObjetivoCobranza();
				BeanUtils.copyProperties(prueba,
						this.listaEliminaCobranzaTemp.get(i));
				valor1 = prueba.getNroTramo();
				eliminar = false;
				for (int j = 0; j < lisTramoObjCobranza.size(); j++) {
					ObjetivoCobranza original = new ObjetivoCobranza();
					BeanUtils.copyProperties(original,
							lisTramoObjCobranza.get(j));
					valor2 = original.getNroTramo();
					if (StringUtils.equals(prueba.getNroTramo(),
							original.getNroTramo())
							&& StringUtils.equals(prueba.getCodTipoMedi(),
									original.getCodTipoMedi())
							&& StringUtils.equals(prueba.getCodRegion(),
									original.getCodRegion())
							&& StringUtils.equals(prueba.getCodZona(),
									original.getCodZona())) {
						eliminar = true;
						break;
					}

				}
				if (eliminar) {
					toj = new TramoObjetivoCobranza();
					toj.getAuditInfo().setUpdatedBy(
							this.mPantallaPrincipalBean.getCurrentUser()
									.getLogin());
					toj.setCodigoPais(pais.getCodigo());
					toj.setCodigoPrograma(pc.getCodigoPrograma());
					toj.setCodTipoMedi(prueba.getCodTipoMedi());
					toj.setCodTramo(prueba.getNroTramo());
					toj.setSecuencia(prueba.getSecuencia());
					service.deleteTramoObjetivoCobranza(toj);
				}

			}
		}

		// Eliminacion Objetivo Cobranza
		List listObjCobranza = service.getObjetivoCobranzaList(criteria);
		for (int k = 0; k < listObjCobranza.size(); k++) {
			ObjetivoCobranza tmp = new ObjetivoCobranza();
			BeanUtils.copyProperties(tmp, listObjCobranza.get(k));
			if (tmp.getIndPais().compareTo("0") == 0) {
				if (StringUtils.isBlank(tmp.getCodZona()))
					tmp.setAmbitoGeogra(tmp.getCodRegion());
				else
					tmp.setAmbitoGeogra(tmp.getCodZona());
			} else
				tmp.setAmbitoGeogra(tmp.getCodigoPais());

			tmp.setNumAmbGeo(tmp.getAmbitoGeogra());

			String verificar = this.verificarListaObjetivoCobranzaSinTramo(
					listCabecera, tmp);
			if (StringUtils.isBlank(verificar)) {
				tmp.getAuditInfo()
						.setUpdatedBy(
								this.mPantallaPrincipalBean.getCurrentUser()
										.getLogin());
				service.deleteObjetivoCobranza(tmp);

				/*
				 * TramoObjetivoCobranza tmp1= new TramoObjetivoCobranza();
				 * tmp1.getAuditInfo().setUpdatedBy(this.mPantallaPrincipalBean.
				 * getCurrentUser().getLogin());
				 * tmp1.setCodigoPais(tmp.getCodigoPais());
				 * tmp1.setCodigoPrograma(tmp.getCodigoPrograma());
				 * tmp1.setSecuencia(tmp.getSecuencia());
				 * tmp1.setCodTipoMedi(tmp.getCodTipoMedi());
				 * tmp1.setCodTramo(tmp.getNroTramo());
				 * 
				 * Map criteriaTemp = new HashMap();
				 * criteriaTemp.put("codigoPais", tmp1.getCodigoPais());
				 * criteriaTemp.put("codigoPrograma", tmp1.getCodigoPrograma());
				 * criteriaTemp.put("estadoRegistro", "1");
				 * criteriaTemp.put("secuencia", tmp1.getSecuencia());
				 * 
				 * List lisTramoObjCobranzaTemp =
				 * service.getTramoObjetivoCobranzaList(criteriaTemp);
				 * tmp1.setCodTramo
				 * (MapUtils.getString((HashMap)lisTramoObjCobranzaTemp.get(0),
				 * "codTramo") );
				 * 
				 * 
				 * service.deleteTramoObjetivoCobranza(tmp1);
				 */

				AmbitoGeografico tmp2 = new AmbitoGeografico();

				tmp2.getAuditInfo()
						.setUpdatedBy(
								this.mPantallaPrincipalBean.getCurrentUser()
										.getLogin());
				tmp2.setCodigoPais(tmp.getCodigoPais());
				tmp2.setCodigoPrograma(tmp.getCodigoPrograma());
				tmp2.setSecuencia(tmp.getSecuencia());
				tmp2.setCodTipoUso("01");

				service.deleteAmbitoGeografico(tmp2);
			}
		}

		// Eliminacion Ambito Geografico
		criteria.put("codTipoUso", "01");
		List listAmbito = service.getAmbitoGeograficoList(criteria);
		for (int j = 0; j < listAmbito.size(); j++) {
			AmbitoGeografico tmp = new AmbitoGeografico();
			BeanUtils.copyProperties(tmp, listAmbito.get(j));
			if (tmp.getIndPais().compareTo("0") == 0) {
				if (StringUtils.isBlank(tmp.getCodigoZona()))
					tmp.setCodigoAmbGeo(tmp.getCodigoRegion());
				else
					tmp.setCodigoAmbGeo(tmp.getCodigoZona());
			} else {
				tmp.setCodigoAmbGeo(tmp.getCodigoPais());
			}

			String verificar = this.verificarListaAmbito(list1, tmp);
			if (StringUtils.isBlank(verificar)) {
				tmp.getAuditInfo()
						.setUpdatedBy(
								this.mPantallaPrincipalBean.getCurrentUser()
										.getLogin());
				service.deleteAmbitoGeografico(tmp);
			}

		}

		// Eliminacion Tramos
		criteria.put("estadoRegistro", Constants.ESTADO_ACTIVO);
		List listTmp = service.getProgramaCobranzaTramoList(criteria);
		for (int i = 0; i < listTmp.size(); i++) {
			ct = new CobranzaTramo();
			BeanUtils.copyProperties(ct, listTmp.get(i));
			String verificar = this.verificarListaTramo(listramos, ct);
			if (StringUtils.isBlank(verificar)) {
				service.deleteCobranzaTramo(ct);
			}

		}

		return;
	}

	private void eliminarDataCobranza(Pais pais, Usuario usuario,
			MantenimientoLECProgramaCorporativoForm f, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		AmbitoGeografico ag;
		TramoObjetivoCobranza toj;
		CobranzaTramo ct;
		List listramos = this.lecTramoList;
		List list1 = this.lecAmbGeoList;
		List list2 = this.lecProgramaCorporativoObjCobList;
		List listCabecera = new ArrayList();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());

		// Eliminacion Objetivo Cobranza Tramo
		List lisTramoObjCobranza = service
				.getTramoObjetivoCobranzaList(criteria);
		for (int k = 0; k < lisTramoObjCobranza.size(); k++) {
			ObjetivoCobranza tmp = new ObjetivoCobranza();
			BeanUtils.copyProperties(tmp, lisTramoObjCobranza.get(k));
			if (tmp.getIndPais().compareTo("0") == 0) {
				if (StringUtils.isBlank(tmp.getCodZona()))
					tmp.setAmbitoGeogra(tmp.getCodRegion());
				else
					tmp.setAmbitoGeogra(tmp.getCodZona());
			} else
				tmp.setAmbitoGeogra(tmp.getCodigoPais());

			tmp.setNumAmbGeo(tmp.getAmbitoGeogra());
			// String verificar =
			// this.verificarListaObjetivoCobranzaEliminar(list2, tmp);
			// if (StringUtils.isBlank(verificar)) {
			toj = new TramoObjetivoCobranza();
			toj.getAuditInfo().setUpdatedBy(
					this.mPantallaPrincipalBean.getCurrentUser().getLogin());
			toj.setCodigoPais(pais.getCodigo());
			toj.setCodigoPrograma(pc.getCodigoPrograma());
			toj.setCodTipoMedi(tmp.getCodTipoMedi());
			toj.setCodTramo(tmp.getNroTramo());
			toj.setSecuencia(tmp.getSecuencia());
			service.deleteTramoObjetivoCobranza(toj);
			// }
		}
		// Eliminacion Objetivo Cobranza

	}

	private String verificarListaTramo(List lista, CobranzaTramo cobranzaTramo) {
		for (int i = 0; i < lista.size(); i++) {
			CobranzaTramo p = (CobranzaTramo) lista.get(i);

			if (StringUtils
					.equals(p.getCodTramo(), cobranzaTramo.getCodTramo())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.tramoYaExiste";
				return proceso;
			}

		}
		return null;
	}

	// Verifica si esta repetido en Lista de Objetivo Cobranza sin
	// considerarTramo
	private String verificarListaObjetivoCobranzaSinTramo(List lista,
			ObjetivoCobranza objetivoCobranza) {
		for (int i = 0; i < lista.size(); i++) {
			ObjetivoCobranza p = (ObjetivoCobranza) lista.get(i);
			if (StringUtils.equals(p.getNumAmbGeo(),
					objetivoCobranza.getNumAmbGeo())
					&& StringUtils.equals(p.getCodTipoMedi(),
							objetivoCobranza.getCodTipoMedi())
					&& StringUtils.equals(p.getSecuencia(),
							objetivoCobranza.getSecuencia())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.objetivoCobranzaYaExiste";
				return proceso;
			}
		}
		return null;
	}

	private void agregarBonCampDesem(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		List lecCampLanzaNivelList = this.lecCampLanzaNivelList;

		// Bono Campana
		for (int j = 0; j < lecCampLanzaNivelList.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn, lecCampLanzaNivelList.get(j));
			BonoCampana bc = new BonoCampana();
			bc.setCodigoPais(pais.getCodigo());
			bc.setCodigoPrograma(pc.getCodigoPrograma());
			bc.setAuditInfo(usuario.getAuditInfo());
			bc.setCampanaLanzamiento(bn.getCampanaLanzamiento());

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", pc.getCodigoPrograma());
			criteria.put("campanaLanzamiento", bc.getCampanaLanzamiento());
			List listTmp = service.getBonoCampanaList(criteria);
			if (listTmp == null || listTmp.size() == 0) {
				service.insertBonoCampana(bc);
			}
		}

		// Bono Lanzamiento
		Map criteriaMax = new HashMap();
		criteriaMax.put("codigoPais", pais.getCodigo());
		criteriaMax.put("codigoPrograma", pc.getCodigoPrograma());
		Integer maximoNroLanzamiento = service
				.getBonoLanzamientoMaxNroLanzamiento(criteriaMax);
		if (maximoNroLanzamiento == null)
			maximoNroLanzamiento = new Integer(0);

		for (int j = 0; j < lecCampLanzaNivelList.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn, lecCampLanzaNivelList.get(j));
			BonoLanzamiento bl = new BonoLanzamiento();
			bl.setCodigoPais(pais.getCodigo());
			bl.setCodigoPrograma(pc.getCodigoPrograma());
			bl.setAuditInfo(usuario.getAuditInfo());
			bl.setCampanaLanzamiento(bn.getCampanaLanzamiento());
			bl.setCodTipoMedicion(bn.getCodTipoMedi());
			bl.setNroLanzamiento(bn.getNroLanzamiento());

			criteriaMax.put("campanaLanzamiento", bn.getCampanaLanzamiento());
			criteriaMax.put("nroLanzamiento", bn.getNroLanzamiento());
			Integer cantidad = service.getExisteBonoLanzamiento(criteriaMax);

			if (cantidad == null || cantidad.intValue() == 0)
				service.insertBonoLanzamiento(bl);
		}
	}

	private void agregarBonProduc(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		List lecCampLanzaNivelList = this.lecCampLanzaNivelList;

		for (int j = 0; j < lecCampLanzaNivelList.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn, lecCampLanzaNivelList.get(j));
			BonoLanzamientoProducto bc = new BonoLanzamientoProducto();
			bc.setCodigoPais(pais.getCodigo());
			bc.setCodigoPrograma(pc.getCodigoPrograma());
			bc.setCampanaLanzamiento(bn.getCampanaLanzamiento());
			bc.setNroLanzamiento(bn.getNroLanzamiento());
			bc.setCodSap(bn.getCodigoProducto());
			bc.setAuditInfo(usuario.getAuditInfo());

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", pc.getCodigoPrograma());
			criteria.put("campanaLanzamiento", bc.getCampanaLanzamiento());
			criteria.put("nroLanzamiento", bc.getNroLanzamiento());
			criteria.put("codSap", bc.getCodSap());
			List listTmp = service.getBonoLanzamientoProductoList(criteria);
			if (listTmp == null || listTmp.size() == 0) {
				bc.setNroSecuenciaProducto(service.getSecBonoLanzaProdu());
				service.insertBonoLanzamientoProducto(bc);
			}
		}
	}

	private void agregarBonNivel(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		List lecCampLanzaList = this.lecCampLanzaList;
		List lecCampLanzaNivelList = this.lecCampLanzaNivelList;

		for (int j = 0; j < lecCampLanzaNivelList.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn, lecCampLanzaNivelList.get(j));
			bn.setCodigoPais(pais.getCodigo());
			bn.setCodigoPrograma(pc.getCodigoPrograma());
			bn.setAuditInfo(usuario.getAuditInfo());
			bn.setCodTipoBono(bn.getCodBonoNivel());
			bn.setValorPorcObj(bn.getExigencia());
			bn.setCodTipoPrem("01");

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", bn.getCodigoPrograma());
			criteria.put("campanaLanzamiento", bn.getCampanaLanzamiento());
			criteria.put("nroLanzamiento", bn.getNroLanzamiento());
			criteria.put("codTipoBono", bn.getCodTipoBono());
			criteria.put("codNivel", bn.getCodNivel());
			List listTmp = service.getBonoNivelList(criteria);
			if (listTmp == null || listTmp.size() == 0) {
				bn.setSecuenciaBonoNivel(service.getSecBonoNivel());
				service.insertBonoNivel(bn);
			} else
				service.updateBonoNivel(bn);

			List listTmp2 = service.getBonoLECList(criteria);
			if (listTmp2 == null || listTmp2.size() == 0)
				service.insertBonoLEC(bn);
		}

		// ELIMINACIONES
		if (f.isNewRecord())
			return;
		List lecCampLanzaNivelListModificacion = this.lecCampLanzaNivelListModificacion;
		// Bono nivel
		for (int j = 0; j < lecCampLanzaNivelListModificacion.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn,
					lecCampLanzaNivelListModificacion.get(j));
			bn.setCodigoPais(pais.getCodigo());
			bn.setCodigoPrograma(pc.getCodigoPrograma());
			bn.setCodTipoBono(bn.getCodBonoNivel());

			String mensaje = this
					.verificarListaBonoNivelEliminacionLanzamiento(
							lecCampLanzaNivelList, bn);
			if (StringUtils.isBlank(mensaje)) {
				service.deleteBonoNivel(bn);
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma", bn.getCodigoPrograma());
				criteria.put("codTipoBono", bn.getCodTipoBono());
				List listTmp2 = service.getBonoLECList(criteria);
				if (listTmp2 != null && listTmp2.size() > 0)
					service.deleteBonoLEC(bn);
			}
		}

		// Bono Lanzamiento Producto
		for (int j = 0; j < lecCampLanzaNivelListModificacion.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn,
					lecCampLanzaNivelListModificacion.get(j));
			BonoLanzamientoProducto bc = new BonoLanzamientoProducto();
			bc.setCodigoPais(pais.getCodigo());
			bc.setCodigoPrograma(pc.getCodigoPrograma());
			bc.setCampanaLanzamiento(bn.getCampanaLanzamiento());
			bc.setNroLanzamiento(bn.getNroLanzamiento());
			bc.setCodSap(bn.getCodigoProducto());

			String mensaje = this.verificarListaBonoLanzamientoProducto(
					lecCampLanzaNivelList, bn);
			if (StringUtils.isBlank(mensaje))
				service.deleteBonoLanzamientoProducto(bc);
		}

		// Bono Lanzamiento
		for (int j = 0; j < lecCampLanzaNivelListModificacion.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn,
					lecCampLanzaNivelListModificacion.get(j));
			bn.setCodigoPais(pais.getCodigo());
			bn.setCodigoPrograma(pc.getCodigoPrograma());

			BonoLanzamiento bl = new BonoLanzamiento();
			bl.setCodigoPais(pais.getCodigo());
			bl.setCodigoPrograma(pc.getCodigoPrograma());
			bl.setCampanaLanzamiento(bn.getCampanaLanzamiento());
			bl.setNroLanzamiento(bn.getNroLanzamiento());

			String mensaje = this.verificarListaBonoLanzamiento(
					lecCampLanzaNivelList, bn);
			if (StringUtils.isBlank(mensaje))
				service.deleteBonoLanzamiento(bl);
		}

		// Bono Campańa
		for (int j = 0; j < lecCampLanzaNivelListModificacion.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn,
					lecCampLanzaNivelListModificacion.get(j));
			BonoCampana bc = new BonoCampana();
			bc.setCodigoPais(pais.getCodigo());
			bc.setCodigoPrograma(pc.getCodigoPrograma());
			bc.setCampanaLanzamiento(bn.getCampanaLanzamiento());

			String mensaje = this.verificarListaBonoCampanna(
					lecCampLanzaNivelList, bn);
			if (StringUtils.isBlank(mensaje))
				service.deleteBonoCampana(bc);
		}

	}

	private String verificarListaBonoNivelEliminacionLanzamiento(List lista,
			BonoNivel nivel) {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);
			if (StringUtils.isNotBlank(p.getNroLanzamiento())) {
				if (StringUtils.equals(p.getCodBonoNivel(),
						nivel.getCodBonoNivel())
						&& StringUtils.equals(p.getCodNivel(),
								nivel.getCodNivel())
						&& StringUtils.equals(p.getCampanaLanzamiento(),
								nivel.getCampanaLanzamiento())
						&& StringUtils.equals(p.getNroLanzamiento(),
								nivel.getNroLanzamiento())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelYaExiste";
					return proceso;
				}
			}
		}
		return null;
	}

	private String verificarListaBonoCampanna(List lista, BonoNivel nivel)
			throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn, lista.get(i));

			if (StringUtils.equals(bn.getCampanaLanzamiento(),
					nivel.getCampanaLanzamiento())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelYaExiste";
				return proceso;
			}
		}
		return null;
	}

	private String verificarListaBonoLanzamiento(List lista, BonoNivel nivel)
			throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn, lista.get(i));
			if (StringUtils.isNotBlank(bn.getNroLanzamiento())) {
				if (StringUtils.equals(bn.getCampanaLanzamiento(),
						nivel.getCampanaLanzamiento())
						&& StringUtils.equals(bn.getNroLanzamiento(),
								nivel.getNroLanzamiento())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelYaExiste";
					return proceso;
				}
			}
		}
		return null;
	}

	private String verificarListaBonoLanzamientoProducto(List lista,
			BonoNivel nivel) throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel bn = new BonoNivel();
			BeanUtils.copyProperties(bn, lista.get(i));
			if (StringUtils.isNotBlank(bn.getNroLanzamiento())) {
				if (StringUtils.equals(bn.getCampanaLanzamiento(),
						nivel.getCampanaLanzamiento())
						&& StringUtils.equals(bn.getNroLanzamiento(),
								nivel.getNroLanzamiento())
						&& StringUtils.equals(bn.getCodigoProducto(),
								nivel.getCodigoProducto())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelYaExiste";
					return proceso;
				}
			}
		}
		return null;
	}

	private void agregarBonNivelRetencion(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		BonoNivel bn;
		List lecCampLanzaNivelList = this.lecRetencionList;

		String validar = this.validarListaBonoNivel(lecCampLanzaNivelList);
		if (StringUtils.isNotBlank(validar))
			throw new Exception(this.getResourceMessage(validar));

		for (int j = 0; j < lecCampLanzaNivelList.size(); j++) {
			bn = (BonoNivel) lecCampLanzaNivelList.get(j);
			bn.setCodigoPais(pais.getCodigo());
			bn.setCodigoPrograma(pc.getCodigoPrograma());
			bn.setAuditInfo(usuario.getAuditInfo());
			bn.setCodTipoPrem("01");

			Map criteria = BeanUtils.describe(bn);

			if (StringUtils.isEmpty(bn.getSecuenciaBonoNivel())) {
				bn.setSecuenciaBonoNivel(service.getSecBonoNivel());
				service.insertBonoNivel(bn);
			} else
				service.updateBonoNivel(bn);

			List listTmp2 = service.getBonoLECList(criteria);
			if (listTmp2 == null || listTmp2.size() == 0)
				service.insertBonoLEC(bn);
		}

		// ELIMINACIONES
		if (f.isNewRecord())
			return;

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		criteria.put("codTipoBono", "05");

		// Eliminacion Nivel Retencion
		List list05 = service.getBonoNivelList(criteria);
		criteria.put("codTipoBono", "06");
		List list06 = service.getBonoNivelList(criteria);
		criteria.put("codTipoBono", "07");
		List list07 = service.getBonoNivelList(criteria);

		List list = ListUtils.sum(list05, list06);
		list = ListUtils.sum(list, list07);

		for (int k = 0; k < list.size(); k++) {
			BonoNivel tmp = new BonoNivel();
			BeanUtils.copyProperties(tmp, list.get(k));
			String verificar = this.verificarListaBonoNivelEliminacion(
					lecCampLanzaNivelList, tmp);
			if (StringUtils.isBlank(verificar)) {
				BonoNivel toj = new BonoNivel();
				toj.setCodigoPais(pais.getCodigo());
				toj.setCodigoPrograma(pc.getCodigoPrograma());
				toj.setCodTipoBono(tmp.getCodTipoBono());
				toj.setCodNivel(tmp.getCodNivel());
				toj.setSecuenciaBonoNivel(tmp.getSecuenciaBonoNivel());
				service.deleteBonoNivelRango(toj);

				Map criteria2 = new HashMap();
				criteria2.put("codigoPais", pais.getCodigo());
				criteria2.put("codigoPrograma", toj.getCodigoPrograma());
				criteria2.put("codTipoBono", toj.getCodTipoBono());
				List listTmp2 = service.getBonoLECList(criteria2);
				if (listTmp2 != null && listTmp2.size() > 0)
					service.deleteBonoLEC(toj);
			}
		}

	}

	private String validarListaBonoNivel(List lista) {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);

			if (StringUtils.isEmpty(p.getNroMinIng())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.ciclovida.minimoIngresoVacio";
				return proceso;
			}
			if (StringUtils.isEmpty(p.getNroMaxIng())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.ciclovida.maximoIngresoVacio";
				return proceso;
			}
			if (StringUtils.isEmpty(p.getNroMinRete())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.ciclovida.minimoRetencionVacio";
				return proceso;
			}
			if (StringUtils.isEmpty(p.getNroMaxRete())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.ciclovida.maximoRetencionVacio";
				return proceso;
			}
			if (!StringUtils.equals(p.getCodTipoPrem(),
					Constants.LEC_CODIGO_TIPO_PREMIACION_CANASTA)) {

				if (StringUtils.isEmpty(p.getMonPrem())) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.ciclovida.montoBonoVacio";
					return proceso;
				}

				if (Double.parseDouble(p.getMonPrem()) == 0.0) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.ciclovida.montoBonoCero";
					return proceso;
				}
			}

			if (Integer.parseInt(p.getNroMinRete()) > Integer.parseInt(p
					.getNroMaxRete())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.ciclovida.rangoValido";
				return proceso;
			}

		}

		// Para un mismo Tipo de Bono y Nivel, validar que no se igrese
		// diferentes Tipos de Premiación
		Map mapBonoNivel = new HashMap();
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);
			String llave = p.getCodTipoBono() + "-" + p.getCodNivel();
			List listTipoPremiacion = null;

			if (mapBonoNivel.get(llave) != null) {
				listTipoPremiacion = (List) mapBonoNivel.get(llave);
			} else {
				listTipoPremiacion = new ArrayList();
			}

			for (int k = 0; k < listTipoPremiacion.size(); k++) {
				String codigoTipoPremiacion = (String) listTipoPremiacion
						.get(k);

				if (!StringUtils.equals(p.getCodTipoPrem(),
						codigoTipoPremiacion)) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.bonoNivelTipoPremiacionNoPuedeSerDiferente";
					return proceso;
				}
			}

			listTipoPremiacion.add(p.getCodTipoPrem());
			mapBonoNivel.put(llave, listTipoPremiacion);
		}

		// VALIDAMOS SI HAN SIDO DESACOPLADOS LOS RANGOS DE RETENCIONES
		Map mapNiveles = new HashMap();

		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);
			String llave = p.getCodTipoBono() + "-" + p.getCodNivel() + "-"
					+ p.getNroMinIng() + "-" + p.getNroMaxIng();
			List listRangos = null;

			if (mapNiveles.get(llave) != null) {
				listRangos = (List) mapNiveles.get(llave);
			} else {
				listRangos = new ArrayList();
			}

			for (int k = 0; k < listRangos.size(); k++) {
				// (nMin > aMax && nMax > aMax) || (nMin < aMin && nMax < aMin)
				// <--- VALIDA TRASLAPES

				String[] rangos = (String[]) listRangos.get(k);

				int nMin = Integer.parseInt(p.getNroMinRete());
				int nMax = Integer.parseInt(p.getNroMaxRete());

				int aMin = Integer.parseInt(rangos[0]);
				int aMax = Integer.parseInt(rangos[1]);

				// Retenciones
				if (!((nMin > aMax && nMax > aMax) || (nMin < aMin && nMax < aMin))) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelRangoYaExiste.retenciones";
					return proceso;
				}
				//

			}

			String[] rangos2 = new String[2];
			rangos2[0] = p.getNroMinRete();
			rangos2[1] = p.getNroMaxRete();
			listRangos.add(rangos2);

			mapNiveles.put(llave, listRangos);
		}

		// VALIDAMOS SI HAN SIDO DESACOPLADOS LOS RANGOS DE INGRESOS
		Map mapNivelesIngresos = new HashMap();

		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);
			String llaveIngresos = p.getCodTipoBono() + "-" + p.getCodNivel();
			List listRangosIngresos = null;

			if (mapNivelesIngresos.get(llaveIngresos) != null) {
				listRangosIngresos = (List) mapNivelesIngresos
						.get(llaveIngresos);
			} else {
				listRangosIngresos = new ArrayList();
			}

			for (int k = 0; k < listRangosIngresos.size(); k++) {
				// (nMin = aMin && nMax =aMax) || (nMin > aMax && nMax > aMax)
				// || (nMin < aMin && nMax < aMin) <--- VALIDA TRASLAPES

				String[] rangosIngresos = (String[]) listRangosIngresos.get(k);

				// Ingresos
				int nMin = Integer.parseInt(p.getNroMinIng());
				int nMax = Integer.parseInt(p.getNroMaxIng());

				int aMin = Integer.parseInt(rangosIngresos[0]);
				int aMax = Integer.parseInt(rangosIngresos[1]);

				if (!((nMin == aMin && nMax == aMax)
						|| (nMin > aMax && nMax > aMax) || (nMin < aMin && nMax < aMin))) {
					String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelRangoYaExiste.ingresos";
					return proceso;
				}

			}

			String[] rangosIngresos2 = new String[2];
			rangosIngresos2[0] = p.getNroMinIng();
			rangosIngresos2[1] = p.getNroMaxIng();
			listRangosIngresos.add(rangosIngresos2);

			mapNivelesIngresos.put(llaveIngresos, listRangosIngresos);
		}

		return null;
	}

	private String verificarListaBonoNivelEliminacion(List lista,
			BonoNivel nivel) {
		for (int i = 0; i < lista.size(); i++) {
			BonoNivel p = (BonoNivel) lista.get(i);

			if (StringUtils.equals(p.getCodTipoBono(), nivel.getCodTipoBono())
					&& StringUtils.equals(p.getCodNivel(), nivel.getCodNivel())
					&& StringUtils.equals(p.getNroMinRete(),
							nivel.getNroMinRete())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.nivelYaExiste";
				return proceso;
			}
		}
		return null;
	}

	private void agregarBonNivelAce(Pais pais, Usuario usuario,
			MantenimientoLECProgramaCorporativoForm f, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		BonoNivel bnAce;
		List lecCampLanzaNivelListAce = this.lecCambNivelAvan;

		for (int j = 0; j < lecCampLanzaNivelListAce.size(); j++) {
			bnAce = (BonoNivel) lecCampLanzaNivelListAce.get(j);
			bnAce.setCodigoPais(pais.getCodigo());
			bnAce.setCodigoPrograma(pc.getCodigoPrograma());
			bnAce.setAuditInfo(usuario.getAuditInfo());
			bnAce.setCodTipoBono("08"); // tipo bono nivel acelerado
			bnAce.setCodTipoPrem("01");

			Map criteria = BeanUtils.describe(bnAce);
			List lecTemp = service.getBonoNivelList(criteria);

			if (lecTemp == null || lecTemp.size() == 0) {
				bnAce.setSecuenciaBonoNivel(service.getSecBonoNivel());
				service.insertBonoNivel(bnAce);
			} else
				service.updateBonoNivel(bnAce);

			List listTmp2 = service.getBonoLECList(criteria);
			if (listTmp2 == null || listTmp2.size() == 0)
				service.insertBonoLEC(bnAce);
		}

		// ELIMINACIONES
		if (f.isNewRecord())
			return;

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		criteria.put("codTipoBono", "08");

		// Eliminacion Nivel Aceleracion
		List list = service.getBonoNivelList(criteria);

		for (int k = 0; k < list.size(); k++) {
			BonoNivel tmp = new BonoNivel();
			BeanUtils.copyProperties(tmp, list.get(k));
			String verificar = this.verificarListaBonoNivel(
					lecCampLanzaNivelListAce, tmp);
			if (StringUtils.isBlank(verificar)) {
				BonoNivel toj = new BonoNivel();
				toj.setCodigoPais(pais.getCodigo());
				toj.setCodigoPrograma(pc.getCodigoPrograma());
				toj.setCodTipoBono(tmp.getCodTipoBono());
				toj.setCodNivel(tmp.getCodNivel());
				service.deleteBonoNivel(toj);

				Map criteria2 = new HashMap();
				criteria2.put("codigoPais", pais.getCodigo());
				criteria2.put("codigoPrograma", toj.getCodigoPrograma());
				criteria2.put("codTipoBono", toj.getCodTipoBono());
				List listTmp2 = service.getBonoLECList(criteria2);
				if (listTmp2 != null && listTmp2.size() > 0)
					service.deleteBonoLEC(toj);
			}
		}
	}

	private void agregarCampanaExigenciaNivelAcelerado(Pais pais,
			Usuario usuario, MantenimientoLECProgramaCorporativoForm f,
			ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service,
			String indicadorTipoExigencia) throws Exception {
		CampanaExigencia ce = new CampanaExigencia();
		ce.setCodigoPais(pais.getCodigo());
		ce.setCodigoPrograma(pc.getCodigoPrograma());
		ce.setAuditInfo(usuario.getAuditInfo());
		ce.setTipoExigencia(indicadorTipoExigencia);
		ce.setCampanaInicio(f.getCampannaInicialNivelAcelerado());
		ce.setCampanaFin(f.getCampannaFinalNivelAcelerado());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		criteria.put("tipoExigencia", indicadorTipoExigencia);
		List listTmp = service.getCampanaExigenciaList(criteria);
		if (listTmp != null && listTmp.size() > 0)
			service.updateCampanaExigencia(ce);
		else
			service.insertCampanaExigencia(ce);
	}

	private void agregarPeriodoGracia(Pais pais, Usuario usuario,
			MantenimientoLECProgramaCorporativoForm f, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		BonoNivel bnAce;
		List lecPeriodoGracia = this.lecPeriodoGracia;

		for (int j = 0; j < lecPeriodoGracia.size(); j++) {
			bnAce = (BonoNivel) lecPeriodoGracia.get(j);
			bnAce.setCodigoPais(pais.getCodigo());
			bnAce.setCodigoPrograma(pc.getCodigoPrograma());
			bnAce.setAuditInfo(usuario.getAuditInfo());
			bnAce.setCodTipoBono("09"); // tipo bono periodo Gracia
			bnAce.setCodTipoPrem("01");

			Map criteria = BeanUtils.describe(bnAce);
			List lecTemp = service.getBonoNivelList(criteria);

			if (lecTemp == null || lecTemp.size() == 0) {
				bnAce.setSecuenciaBonoNivel(service.getSecBonoNivel());
				service.insertBonoNivel(bnAce);
			} else
				service.updateBonoNivel(bnAce);

			List listTmp2 = service.getBonoLECList(criteria);
			if (listTmp2 == null || listTmp2.size() == 0)
				service.insertBonoLEC(bnAce);
		}

		// ELIMINACIONES
		if (f.isNewRecord())
			return;

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		criteria.put("codTipoBono", "09");

		// Eliminacion Nivel Aceleracion
		List list = service.getBonoNivelList(criteria);

		for (int k = 0; k < list.size(); k++) {
			BonoNivel tmp = new BonoNivel();
			BeanUtils.copyProperties(tmp, list.get(k));
			String verificar = this.verificarListaBonoNivel(lecPeriodoGracia,
					tmp);
			if (StringUtils.isBlank(verificar)) {
				BonoNivel toj = new BonoNivel();
				toj.setCodigoPais(pais.getCodigo());
				toj.setCodigoPrograma(pc.getCodigoPrograma());
				toj.setCodTipoBono(tmp.getCodTipoBono());
				toj.setCodNivel(tmp.getCodNivel());
				service.deleteBonoNivel(toj);

				Map criteria2 = new HashMap();
				criteria2.put("codigoPais", pais.getCodigo());
				criteria2.put("codigoPrograma", toj.getCodigoPrograma());
				criteria2.put("codTipoBono", toj.getCodTipoBono());
				List listTmp2 = service.getBonoLECList(criteria2);
				if (listTmp2 != null && listTmp2.size() > 0)
					service.deleteBonoLEC(toj);
			}
		}
	}

	private void agregarIncAmbGeo(Pais pais, Usuario usuario,
			String codigoPeriodo, ProgramaCorporativo pc,
			MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		AmbitoGeografico agniv;
		List listniv = this.lecPedLidAmbGeoList;
		if (!f.isNewRecord()) {
			agniv = new AmbitoGeografico();
			agniv.setCodigoPais(pais.getCodigo());
			agniv.setCodigoPrograma(pc.getCodigoPrograma());
			agniv.setCodTipoUso("03");
			service.deleteAmbitoGeograficoFisico(agniv);
		}
		if (listniv != null) {
			for (int j = 0; j < listniv.size(); j++) {
				agniv = (AmbitoGeografico) listniv.get(j);
				agniv.setCodigoPais(pais.getCodigo());
				agniv.setCodigoPrograma(pc.getCodigoPrograma());
				agniv.setAuditInfo(usuario.getAuditInfo());
				agniv.setCampanaProceso(codigoPeriodo);
				agniv.setCodTipoUso("03");
				agniv.setSecuencia(service.getNextCodAmbitGeogr());
				service.insertAmbitoGeografico(agniv);
			}
		}
	}

	private void agregarIncMontoRecup(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		// INSERTAR PROGRAMA NIVEL
		List list = this.lecIncMontoRecuList;
		this.actualizaListaGrillaIncentivo3(f, list);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				NivelTramo nivel = (NivelTramo) list.get(i);
				nivel.setCodigoPais(pais.getCodigo());
				nivel.setAuditInfo(usuario.getAuditInfo());
				nivel.setCodigoPrograma(pc.getCodigoPrograma());

				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma", pc.getCodigoPrograma());
				criteria.put("codigoNivel", nivel.getCodigoNivel());
				criteria.put("codigoTramo", nivel.getCodigoTramo());
				criteria.put("codigoRango", nivel.getCodigoRangoComision());
				List listTmp = service.getNivelTramoList(criteria);

				if (listTmp != null && listTmp.size() > 0)
					service.updateNivelTramo(nivel);
				else
					service.insertNivelTramo(nivel);
			}
		}
		// Eliminaciones
		if (f.isNewRecord())
			return;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		List listTmp = service.getNivelTramoList(criteria);
		for (int i = 0; i < listTmp.size(); i++) {
			NivelTramo tmp = new NivelTramo();
			BeanUtils.copyProperties(tmp, listTmp.get(i));
			tmp.setCodigoPais(pais.getCodigo());
			tmp.setCodigoPrograma(pc.getCodigoPrograma());
			String verificar = this.verificarListaNivelTramo(list, tmp);
			if (StringUtils.isBlank(verificar))
				service.deleteNivelTramo(tmp);
		}
	}

	private void actualizaListaGrillaIncentivo3(
			MantenimientoLECProgramaCorporativoForm f, List list)
			throws Exception {
		if (list == null)
			return;
		for (int k = 0; k < list.size(); k++) {

			NivelTramo oc = new NivelTramo();
			BeanUtils.copyProperties(oc, list.get(k));
			// String valPorComiPediCons =
			// f.getListaGrillaIncentivo3Campo01()[k];
			// String valPorComiPediNCon =
			// f.getListaGrillaIncentivo3Campo02()[k];
			// String valPorComiTole = f.getListaGrillaIncentivo3Campo03()[k];
			//
			// oc.setValPorComiPediCons(valPorComiPediCons);
			// oc.setValPorComiPediNCon(valPorComiPediNCon);
			// oc.setValPorComiTole(valPorComiTole);
			list.set(k, oc);

		}
	}

	private void agregarGestDesem(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		// String[] result = f.getSelecCampEvaluar().split(",");
		// List listCamp = new ArrayList();
		// Base b;
		// for(int i=0;i<result.length;i++){
		// b= new Base();
		// b.setCodigo(result[i]);
		// b.setDescripcion(result[i]);
		// if (StringUtils.isNotBlank(result[i]))
		// listCamp.add(b);
		// }
		// this.selecCampanaEvaluarLec= listCamp;
		List listGestDesem = this.lecProgramaCorporativoGestDesemList;
		// this.actualizaListaGrillaDesempenio(request, f, listGestDesem);
		if (listGestDesem != null) {
			for (int j = 0; j < listGestDesem.size(); j++) {
				Map p = (Map) listGestDesem.get(j);
				ProgramaDesempenio pd = new ProgramaDesempenio();
				BeanUtils.copyProperties(pd, p);
				pd.setCodigoPais(pais.getCodigo());
				pd.setCodigoPrograma(pc.getCodigoPrograma());
				pd.setAuditInfo(usuario.getAuditInfo());

				Map registro = new HashMap();
				registro.put("codigoPais", pais.getCodigo());
				registro.put("codigoPrograma", pc.getCodigoPrograma());
				registro.put("codigoTipoDesemp", pd.getCodigoTipoDesemp());
				List listtmp = service.getProgramaDesempenioList(registro);
				if (listtmp == null || listtmp.size() == 0)
					service.insertProgramaDesempenio(pd);
				else
					service.updateProgramaDesempenio(pd);
			}
		}

		// Programa Etapa Campana
		ProgramaEtapaCampana ec;
		Base temp;
		List listCampEva = this.selecCampanaEvaluarLec;
		if (listCampEva != null) {
			for (int j = 0; j < listCampEva.size(); j++) {
				temp = (Base) listCampEva.get(j);
				ec = new ProgramaEtapaCampana();
				ec.setCamEval(temp.getDescripcion());
				ec.setCodigoPais(pais.getCodigo());
				ec.setCodigoPrograma(pc.getCodigoPrograma());
				ec.setAuditInfo(usuario.getAuditInfo());

				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma", pc.getCodigoPrograma());
				criteria.put("camEval", ec.getCamEval());

				List listtmp = service.getProgramaEtapaCampanaList(criteria);
				if (listtmp == null || listtmp.size() == 0)
					service.insertProgramaEtapaCampana(ec);
			}
		}

		// Eliminaciones
		if (f.isNewRecord())
			return;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		List listtmp = service.getProgramaEtapaCampanaList(criteria);
		for (int j = 0; j < listtmp.size(); j++) {
			Map registro = (Map) listtmp.get(j);
			String camEval = (String) registro.get("camEval");
			String verificar = this.verificarListaProgramaEtapaCanasta(
					listCampEva, camEval);
			if (StringUtils.isBlank(verificar)) {
				ec = new ProgramaEtapaCampana();
				ec.setCodigoPais(pais.getCodigo());
				ec.setCodigoPrograma(pc.getCodigoPrograma());
				ec.setCamEval(camEval);
				service.deleteProgramaEtapaCampana(ec);
			}

		}

		listtmp = service.getProgramaDesempenioList(criteria);
		for (int j = 0; j < listtmp.size(); j++) {
			Map registro = (Map) listtmp.get(j);
			String codigoTipoDesemp = (String) registro.get("codigoTipoDesemp");
			String verificar = this.verificarListaProgramaDesempenio(
					listGestDesem, codigoTipoDesemp);
			if (StringUtils.isBlank(verificar)) {
				ProgramaDesempenio p = new ProgramaDesempenio();
				p.setCodigoPais(pais.getCodigo());
				p.setCodigoPrograma(pc.getCodigoPrograma());
				p.setCodigoTipoDesemp(codigoTipoDesemp);
				service.deleteProgramaDesempenio(p);
			}

		}
	}

	private String verificarListaProgramaEtapaCanasta(List lista, String camEval)
			throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			Base p = (Base) lista.get(i);
			if (StringUtils.equals(p.getDescripcion(), camEval)) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.programaCanastaYaExiste";
				return proceso;
			}
		}
		return null;
	}

	private void agregarCanasta(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {
		List lecCanastaList = this.lecCanastaList;
		List lecProductosCanastaList = this.lecProductosCanastaList;
		// this.actualizaListaGrillaCobranzaCabecera(f, lecCanastaList);
		// this.actualizaListaGrillaCobranzaDetalle(f, lecProductosCanastaList);

		Canasta tempCanas;
		if (lecCanastaList != null && lecCanastaList.size() > 0) {
			for (int j = 0; j < lecCanastaList.size(); j++) {
				tempCanas = new Canasta();
				Map p = (Map) lecCanastaList.get(j);
				BeanUtils.copyProperties(tempCanas, p);
				tempCanas.setCodigoPais(pais.getCodigo());
				tempCanas.setCodigoPrograma(pc.getCodigoPrograma());
				tempCanas.setAuditInfo(usuario.getAuditInfo());
				String secuencia = (String) p.get("secuencia");
				if (secuencia.equals(Constants.NUMERO_CERO)) {
					tempCanas.setSecuencia(service.getNumeroSecuenciaCanasta());
					p.put("secuencia", tempCanas.getSecuencia());
					lecCanastaList.set(j, p);
					service.insertCanasta(tempCanas);
				} else
					service.updateCanasta(tempCanas);

			}
		}

		CanastaDetalle tempCanasDet;
		for (int j = 0; j < lecProductosCanastaList.size(); j++) {
			tempCanasDet = new CanastaDetalle();
			Map p = (Map) lecProductosCanastaList.get(j);
			BeanUtils.copyProperties(tempCanasDet, p);
			tempCanasDet.setCodigoPais(pais.getCodigo());
			tempCanasDet.setCodigoPrograma(pc.getCodigoPrograma());
			tempCanasDet.setAuditInfo(usuario.getAuditInfo());

			String nombreCanasta = (String) p.get("nombreCanasta");
			int index = 0;
			for (int x = 0; x < lecCanastaList.size(); x++) {
				Map cab = (Map) lecCanastaList.get(x);
				String nombre = (String) cab.get("nombre");
				if (nombre.equals(nombreCanasta)) {
					index = x;
					break;
				}
			}
			Map registroCabecera = (Map) lecCanastaList.get(index);
			String secuencia = (String) registroCabecera.get("secuencia");
			tempCanasDet.setSecuenciaCanasta(secuencia);
			p.put("secuenciaCanasta", tempCanasDet.getSecuenciaCanasta());
			lecProductosCanastaList.set(j, p);

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", pc.getCodigoPrograma());
			criteria.put("secuenciaCanasta", tempCanasDet.getSecuenciaCanasta());
			criteria.put("codigoSap", tempCanasDet.getCodigoSap());

			List listTmp = service.getCanastaDetalleList(criteria);
			if (listTmp == null || listTmp.size() == 0) {
				service.insertCanastaDetalle(tempCanasDet);
			} else
				service.updateCanastaDetalle(tempCanasDet);
		}

		// CONTEO DE PRODUCTOS
		if (lecCanastaList != null && lecCanastaList.size() > 0) {
			for (int j = 0; j < lecCanastaList.size(); j++) {
				tempCanas = new Canasta();
				Map p = (Map) lecCanastaList.get(j);
				BeanUtils.copyProperties(tempCanas, p);
				tempCanas.setCodigoPais(pais.getCodigo());
				tempCanas.setCodigoPrograma(pc.getCodigoPrograma());
				String secuencia = tempCanas.getSecuencia();

				int numProductos = 0;
				for (int k = 0; k < lecProductosCanastaList.size(); k++) {
					Map pdet = (Map) lecProductosCanastaList.get(k);
					String secuenciaDetalle = (String) pdet
							.get("secuenciaCanasta");
					if (secuenciaDetalle.equals(secuencia))
						numProductos++;
				}
				tempCanas.setNumeroProductos(new Integer(numProductos)
						.toString());
				service.updateCanastaNumProductos(tempCanas);
			}
		}

		// ELIMINACIONES
		if (f.isNewRecord())
			return;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());
		// campanyaActivacionCanasta
		log.debug(">>> campanhaa activacion "
				+ f.getCampanyaActivacionCanasta());
		criteria.put("campanyaActivacionCanasta",
				f.getCampanyaActivacionCanasta());

		List listTmp = service.getCanastaDetalleList(criteria);
		for (int k = 0; k < listTmp.size(); k++) {
			CanastaDetalle temp = new CanastaDetalle();
			Map p = (Map) listTmp.get(k);
			BeanUtils.copyProperties(temp, p);

			String verificar = this.verificarListaCanastaDetalle(
					lecProductosCanastaList, p);
			if (StringUtils.isBlank(verificar))
				service.deleteCanastaDetalle(temp);
		}

		listTmp = service.getCanastaList(criteria);
		for (int k = 0; k < listTmp.size(); k++) {
			Canasta temp = new Canasta();
			Map p = (Map) listTmp.get(k);
			BeanUtils.copyProperties(temp, p);

			String verificar = this.verificarListaCanastaCabecera(
					lecCanastaList, p);
			if (StringUtils.isBlank(verificar))
				service.deleteCanasta(temp);
		}
	}

	private void agregarIncCanasta(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {

		List lecIncCanasProdList = this.lecIncCanasProd;
		if (lecIncCanasProdList != null) {
			for (int j = 0; j < lecIncCanasProdList.size(); j++) {
				ProgramaCanastaPremi pcp = new ProgramaCanastaPremi();
				Map registro = (Map) lecIncCanasProdList.get(j);
				BeanUtils.copyProperties(pcp, registro);
				pcp.setCodigoPais(pais.getCodigo());
				pcp.setCodigoPrograma(pc.getCodigoPrograma());
				pcp.setAuditInfo(usuario.getAuditInfo());

				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma", pc.getCodigoPrograma());
				criteria.put("secCanasta", pcp.getSecCanasta());
				criteria.put("codigoNivel", pcp.getCodigoNivel());
				criteria.put("codigoTipoCanasta", pcp.getCodigoTipoCanasta());
				criteria.put("campanyaActivacionCanasta",
						f.getCampanyaActivacionCanasta());
				List listtmp = service.getProgramaCanastaPremiList(criteria);
				List listaCamp1 = service.getCanastaList(criteria);
				if (listtmp == null || listtmp.size() == 0) {
					String secuencia = "";
					String descripcion = registro.get("nomCanasta").toString();
					for (int i = 0; i < listaCamp1.size(); i++) {
						Map parametro = (Map) listaCamp1.get(i);
						String descri = parametro.get("nombre").toString();
						if (StringUtils.equals(descri, descripcion)) {
							secuencia = parametro.get("secuencia").toString();
							pcp.setSecCanasta(secuencia);
							pcp.setSecuencia(service.getProgramaCanastaPremi());
							service.insertProgramaCanastaPremi(pcp);
							break;
						}
					}
				}
			}

			if (f.isNewRecord())
				return;
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPrograma", pc.getCodigoPrograma());
			criteria.put("campanyaActivacionCanasta",
					f.getCampanyaActivacionCanasta());
			List listtmp = service.getProgramaCanastaPremiList(criteria);
			for (int j = 0; j < listtmp.size(); j++) {
				ProgramaCanastaPremi temp = new ProgramaCanastaPremi();
				Map p = (Map) listtmp.get(j);
				BeanUtils.copyProperties(temp, p);

				String verificar = this.verificarListaProgramaCanasta2(
						lecIncCanasProdList, temp);
				if (StringUtils.isBlank(verificar))
					service.deleteProgramaCanastaPremi(temp);
			}
		}
	}

	/**
	 * @param programaCanastaPremi
	 */
	private String verificarListaProgramaCanasta2(List lista,
			ProgramaCanastaPremi programaCanastaPremi) throws Exception {
		for (int i = 0; i < lista.size(); i++) {
			ProgramaCanastaPremi p = new ProgramaCanastaPremi();
			Map registro = (Map) lista.get(i);
			BeanUtils.copyProperties(p, registro);

			if (StringUtils.equals(p.getCodigoNivel(),
					programaCanastaPremi.getCodigoNivel())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.programaCanastaYaExiste";
				return proceso;
			}
		}
		return null;
	}

	private void agregarRanking(Pais pais, Usuario usuario,
			ProgramaCorporativo pc, MantenimientoLECProgramaCorporativoForm f,
			MantenimientoLECProgramaCorporativoService service)
			throws Exception {

		ReporteService reportService = (ReporteService) getBean("scsicc.reporteService");
		List lecRankingList = this.lecRankingList;
		// this.actualizaListaGrillaRanking(session, f);

		if (lecRankingList != null && lecRankingList.size() > 0) {
			Ranking ranking = null;
			for (int i = 0; i < lecRankingList.size(); i++) {
				ranking = (Ranking) lecRankingList.get(i);
				ranking.setCodigoPais(pais.getCodigo());
				ranking.setCodigoPrograma(pc.getCodigoPrograma());

				// validamos las campańas
				String campanya = ranking.getCampanyaInicio();
				try {
					Map criteria = new HashMap();
					criteria.put("codigoPeriodo", campanya);
					String oidPeriodo = reportService.getOidString(
							"getOidPeriodoByCodigoPeriodo", criteria);

					if (log.isDebugEnabled())
						log.debug("Campańa Inicio OK " + campanya);

					campanya = ranking.getCampanyaFin();
					criteria.put("codigoPeriodo", campanya);
					oidPeriodo = reportService.getOidString(
							"getOidPeriodoByCodigoPeriodo", criteria);

					if (log.isDebugEnabled())
						log.debug("Campańa Fin OK " + campanya);

				} catch (Exception ex) {
					String mensaje = this
							.getResourceMessage(
									"mantenimientoLECProgramaCorporativoForm.ranking.campanya.error",
									new String[] { campanya,
											ranking.getDescripcionTipoRanking() });
					f.setTabSeleccion(Constants.LEC_TAB_PROGRAMA_CORPORATIVO_RANKING);
					throw new Exception(mensaje);
				}

				if (Integer.parseInt(ranking.getCampanyaFin()) < Integer
						.parseInt(ranking.getCampanyaInicio())) {
					String mensaje = this
							.getResourceMessage(
									"mantenimientoLECProgramaCorporativoForm.ranking.campanyafin.eror",
									new String[] { ranking
											.getDescripcionTipoRanking() });
					f.setTabSeleccion(Constants.LEC_TAB_PROGRAMA_CORPORATIVO_RANKING);
					throw new Exception(mensaje);
				}
				//

				if (StringUtils.equals(ranking.getNumeroSecuencia(),
						Constants.NUMERO_CERO)) {
					ranking.setNumeroSecuencia(service
							.getNumeroSecuenciaRanking());
					service.insertRanking(ranking, usuario);
				} else
					service.updateRanking(ranking, usuario);

				// Niveles
				List lecRankingNivelList = new ArrayList();
				if (StringUtils.isNotBlank(ranking.getCodigoTipoRanking())) {
					if (StringUtils
							.equals(ranking.getCodigoTipoRanking(), "01"))
						lecRankingNivelList = this.listAux01;
					if (StringUtils
							.equals(ranking.getCodigoTipoRanking(), "02"))
						lecRankingNivelList = this.listAux02;
					if (StringUtils
							.equals(ranking.getCodigoTipoRanking(), "03"))
						lecRankingNivelList = this.listAux03;
				}

				if (lecRankingNivelList != null) {
					for (int j = 0; j < lecRankingNivelList.size(); j++) {
						Map rknNivel = (Map) lecRankingNivelList.get(j);
						RankingNivel rankingNivel = new RankingNivel();

						rankingNivel.setCodigoPais(ranking.getCodigoPais());
						rankingNivel.setCodigoPrograma(ranking
								.getCodigoPrograma());
						rankingNivel.setCodigoTipoRanking(ranking
								.getCodigoTipoRanking());
						rankingNivel.setNumeroSecuencia(ranking
								.getNumeroSecuencia());
						rankingNivel.setCodigoNivel(MapUtils.getString(
								rknNivel, "codigoNivel"));
						rankingNivel
								.setIndicadorActivo(Constants.ESTADO_ACTIVO);

						String nivelSeleccionado = MapUtils.getString(rknNivel,
								"nivelSeleccionado");
						if (StringUtils.equals(nivelSeleccionado,
								Constants.ESTADO_ACTIVO)) {
							RankingNivel rnObj = service
									.getNivelRanking(rankingNivel);
							if (rnObj == null)
								service.insertNivelRanking(rankingNivel,
										usuario);
							else
								service.updateNivelRanking(rankingNivel,
										usuario);
						} else
							service.deleteNivelRanking(rankingNivel, usuario);
					}
				}
			}
		}

		// ELIMINACIONES
		if (f.isNewRecord())
			return;

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", pc.getCodigoPrograma());

		List listTmp = service.getRankingList(criteria);

		for (int k = 0; k < listTmp.size(); k++) {
			Ranking ranking = (Ranking) listTmp.get(k);

			String verificar = this.verificarListaRanking(lecRankingList,
					ranking);
			if (StringUtils.isBlank(verificar)) {
				service.deleteRanking(ranking, usuario);

				RankingNivel rankingNivel = new RankingNivel();
				BeanUtils.copyProperties(rankingNivel, ranking);
				service.deleteNivelRanking(rankingNivel, usuario);
			}
		}
	}

	private String verificarListaRanking(List lecRankingList, Ranking ranking) {
		for (int i = 0; i < lecRankingList.size(); i++) {
			Ranking registro = (Ranking) lecRankingList.get(i);
			if (StringUtils.equals(registro.getCodigoTipoRanking(),
					ranking.getCodigoTipoRanking())) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.rankingYaExiste";
				return proceso;
			}
		}
		return null;
	}

	private void obtenerRegistro(String id,
			MantenimientoLECProgramaCorporativoForm f) throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

		Map criteria = BeanUtils.describe(f);
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", id);

		f.setCodigoPrograma(id);
		f.setPeriodoInicioNivel("");
		f.setPeriodoFinNivel("");
		f.setCampannaInicialNivelAcelerado("");
		f.setCampannaFinalNivelAcelerado("");
		f.setCampannaInicialNivel("");
		f.setCampannaFinalNivel("");

		List listProgramaCorporativo = service
				.getProgramaCorporativoList(criteria);

		Map criteriaMedi = new HashMap();
		List listTipoMedi = service.getTipoMediList(criteriaMedi);
		this.lecTipoMedicionList = listTipoMedi;

		if (listProgramaCorporativo != null
				&& listProgramaCorporativo.size() > 0) {
			for (int i = 0; i < listProgramaCorporativo.size(); i++) {
				Map pc = (Map) listProgramaCorporativo.get(i);
				BeanUtils.copyProperties(f, listProgramaCorporativo.get(i));
				f.setProgramaReconoTmp((String) pc.get("indProgReco"));
				f.setPedidoWebTmp((String) pc.get("pedidoWeb"));
				f.setInsPosiLiderTmp((String) pc.get("insPosiLider"));
				f.setActCobranzaTmp((String) pc.get("actCobranza"));
				f.setInsPortalFFVVTmp((String) pc.get("insPortalFFVV"));
				f.setInsSistComeTmp((String) pc.get("insSistCome"));
				f.setExigPedPersonaTmp((String) pc.get("exigPedPersona"));
				f.setExigCursoCambTmp((String) pc.get("exigCursoCamb"));
				f.setNroAmedirEtapa((String) pc.get("numCampEval"));
				f.setIndicadorFeriado((String) pc.get("indicadorFeriado"));

			}
		}
		f.setProgramaRecono(f.getProgramaReconoTmp());

		// Pestańa nivel
		criteria.remove("codigoNivel");
		List listNivel = service.getProgramaNivelList(criteria);
		List listNivel2 = new ArrayList();
		List lecIncTipoComiList = new ArrayList();
		Base b;
		List listNivelCorp = new ArrayList();

		if (listNivel != null && listNivel.size() > 0) {
			for (int i = 0; i < listNivel.size(); i++) {
				Nivel nivel = new Nivel();
				BeanUtils.copyProperties(nivel, listNivel.get(i));
				nivel.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
				listNivel2.add(nivel);

				// INTEGRADO CON LA LISTA DE INCENTIVOS
				if (!StringUtils.isBlank(nivel.getMontPediCons()))
					lecIncTipoComiList.add(nivel);
				b = new Base();
				b.setCodigo(nivel.getCodigoNivel());
				b.setDescripcion(nivel.getNivel());
				listNivelCorp.add(b);
			}
		}

		criteria.put("codTipoUso", "02");
		List lisNivAmb = service.getAmbitoGeograficoList(criteria);
		List lisAmbNiv = new ArrayList();
		if (lisNivAmb != null && lisNivAmb.size() > 0) {
			for (int i = 0; i < lisNivAmb.size(); i++) {
				AmbitoGeografico tmp0 = new AmbitoGeografico();
				BeanUtils.copyProperties(tmp0, lisNivAmb.get(i));
				if (tmp0.getIndPais().compareTo("0") == 0) {
					if (StringUtils.isBlank(tmp0.getCodigoZona())) {
						tmp0.setCodigoAmbGeo(tmp0.getCodigoRegion());
						tmp0.setPais(tmp0.getCodigoPais());
						tmp0.setZona("");
					} else {
						tmp0.setCodigoAmbGeo(tmp0.getCodigoZona());
						tmp0.setPais(tmp0.getCodigoPais());
					}
				} else {
					tmp0.setCodigoAmbGeo(tmp0.getCodigoPais());
					tmp0.setPais(tmp0.getCodigoPais());
					tmp0.setRegion("");
					tmp0.setZona("");
				}
				lisAmbNiv.add(tmp0);
			}
		}

		criteria.put("codTipoUso", "01");
		criteria.put("tipoExigencia", "C");
		List lecExigenciaList = service.getCampanaExigenciaList(criteria);
		if (lecExigenciaList != null && lecExigenciaList.size() > 0) {
			CampanaExigencia cexig = new CampanaExigencia();
			BeanUtils.copyProperties(cexig, lecExigenciaList.get(0));
			f.setPeriodoInicioNivel(cexig.getCampanaInicio());
			f.setPeriodoFinNivel(cexig.getCampanaFin());

		}

		criteria.put("tipoExigencia", "N");
		List lecExigenciaNivelAceleradoList = service
				.getCampanaExigenciaList(criteria);
		if (lecExigenciaNivelAceleradoList != null
				&& lecExigenciaNivelAceleradoList.size() > 0) {
			CampanaExigencia cexig = new CampanaExigencia();
			BeanUtils.copyProperties(cexig,
					lecExigenciaNivelAceleradoList.get(0));
			f.setCampannaInicialNivelAcelerado(cexig.getCampanaInicio());
			f.setCampannaFinalNivelAcelerado(cexig.getCampanaFin());
		}

		criteria.put("tipoExigencia", "V");
		List lecExigenciaNivelList = service.getCampanaExigenciaList(criteria);
		if (lecExigenciaNivelList != null && lecExigenciaNivelList.size() > 0) {
			CampanaExigencia cexig = new CampanaExigencia();
			BeanUtils.copyProperties(cexig, lecExigenciaNivelList.get(0));
			f.setCampannaInicialNivel(cexig.getCampanaInicio());
			f.setCampannaFinalNivel(cexig.getCampanaFin());
		}

		// Pestańa objetivo cobranza
		criteria.put("estadoRegistro", Constants.ESTADO_ACTIVO);
		List listramos = service.getProgramaCobranzaTramoList(criteria);
		List listramos2 = new ArrayList();
		if (listramos != null && listramos.size() > 0) {
			for (int i = 0; i < listramos.size(); i++) {
				CobranzaTramo tmp = new CobranzaTramo();
				BeanUtils.copyProperties(tmp, listramos.get(i));
				listramos2.add(tmp);
			}
		}

		// Llenando ambito Geografico Cobranza
		List lisAmbCobranza = new ArrayList();
		criteria.put("codTipoUso", "01");
		List lecAmbCobranzaList = service.getAmbitoGeograficoList(criteria);

		for (int i = 0; i < lecAmbCobranzaList.size(); i++) {
			AmbitoGeografico tmp0 = new AmbitoGeografico();
			BeanUtils.copyProperties(tmp0, lecAmbCobranzaList.get(i));
			if (StringUtils.equals(tmp0.getIndPais(), "0")) {
				if (StringUtils.isBlank(tmp0.getCodigoZona())) {
					tmp0.setCodigoAmbGeo(tmp0.getCodigoRegion());
					tmp0.setPais("");
					tmp0.setZona("");
				} else {
					tmp0.setCodigoAmbGeo(tmp0.getCodigoZona());
					tmp0.setPais("");
				}
			} else {
				tmp0.setCodigoAmbGeo(tmp0.getCodigoPais());
				tmp0.setPais(tmp0.getCodigoPais());
				tmp0.setRegion("");
				tmp0.setZona("");
			}
			lisAmbCobranza.add(tmp0);
		}

		criteria.remove("secuencia");
		criteria.remove("codigoTipoMedi");
		criteria.remove("codTramo");

		List lisTramoObjCobranza = service
				.getTramoObjetivoCobranzaList(criteria);
		List lisObjCobranzaTra = new ArrayList();
		if (lisTramoObjCobranza != null && lisTramoObjCobranza.size() > 0) {
			for (int i = 0; i < lisTramoObjCobranza.size(); i++) {
				ObjetivoCobranza tmp = new ObjetivoCobranza();
				BeanUtils.copyProperties(tmp, lisTramoObjCobranza.get(i));
				if (StringUtils.equals(tmp.getIndPais(), "0")) {
					if (StringUtils.isBlank(tmp.getCodZona()))
						tmp.setAmbitoGeogra(tmp.getCodRegion());
					else
						tmp.setAmbitoGeogra(tmp.getCodZona());
				} else
					tmp.setAmbitoGeogra(tmp.getCodigoPais());

				tmp.setNumAmbGeo(tmp.getAmbitoGeogra());
				lisObjCobranzaTra.add(tmp);
			}
		}
		// Pestańa incentivos

		// ambito geografico
		criteria.put("codTipoUso", "03");
		List lecPedLidAmbGeoList = service.getAmbitoGeograficoList(criteria);
		List lisPediAmbNiv = new ArrayList();
		if (lecPedLidAmbGeoList != null && lecPedLidAmbGeoList.size() > 0) {
			for (int i = 0; i < lecPedLidAmbGeoList.size(); i++) {
				AmbitoGeografico tmp0 = new AmbitoGeografico();
				BeanUtils.copyProperties(tmp0, lecPedLidAmbGeoList.get(i));
				if (StringUtils.equals(tmp0.getIndPais(), "0")) {
					if (StringUtils.isBlank(tmp0.getCodigoZona())) {
						tmp0.setCodigoAmbGeo(tmp0.getCodigoRegion());
						tmp0.setPais(tmp0.getCodigoPais());
						tmp0.setZona("");
					} else {
						tmp0.setCodigoAmbGeo(tmp0.getCodigoZona());
						tmp0.setPais(tmp0.getCodigoPais());
					}
				} else {
					tmp0.setCodigoAmbGeo(tmp0.getCodigoPais());
					tmp0.setPais(tmp0.getCodigoPais());
					tmp0.setRegion("");
					tmp0.setZona("");
				}
				lisPediAmbNiv.add(tmp0);
			}
		}

		this.lecPedLidAmbGeoList = lisPediAmbNiv;

		// Pestańa Desempenio
		ProgramaDesempenio pd;
		List listGestDesem = service.getProgramaDesempenioList(criteria);

		// Programa Etapa Campana
		ProgramaEtapaCampana ec;
		Base temp;
		List listCampEva = service.getProgramaEtapaCampanaList(criteria);
		List listCamEvaSele = new ArrayList();

		List campanaEvaluacionLec = service.getEtapCampEval(new HashMap());
		if (listCampEva != null) {
			for (int j = 0; j < listCampEva.size(); j++) {
				ec = new ProgramaEtapaCampana();
				BeanUtils.copyProperties(ec, listCampEva.get(j));
				temp = new Base();
				temp.setCodigo(ec.getCamEval());
				temp.setDescripcion(ec.getCamEval());
				listCamEvaSele.add(temp);

				if (campanaEvaluacionLec != null) {
					for (int i = 0; i < campanaEvaluacionLec.size(); i++) {
						Base baseTemp = new Base();
						BeanUtils.copyProperties(baseTemp,
								campanaEvaluacionLec.get(i));
						if (baseTemp.getDescripcion().equals(temp.getCodigo())) {
							campanaEvaluacionLec.remove(i);
						}
					}
				}
			}
		}

		this.lecListaTipoDesem = service.getTipoDesempenioList(criteria);
		this.campanaEvaluacionLec = campanaEvaluacionLec;
		this.selecCampanaEvaluarLec = listCamEvaSele;
		// this.listaCampanias = new DualListModel<Base>(new ArrayList(),new
		// ArrayList());//(*/this.campanaEvaluacionLec,
		// this.selecCampanaEvaluarLec);
		this.lecProgramaCorporativoGestDesemList = new ArrayList();
		this.lecProgramaCorporativoGestDesemDataModel = new DataTableModel();
		// Pestańa bono

		List listaCampEvaluarLEC = service.getBonoCampanaList(criteria);
		List listaBaseCampEvaluarLEC = new ArrayList();

		BonoCampana bc;
		PeriodoCronograma pedc;

		if (listaCampEvaluarLEC != null) {
			for (int j = 0; j < listaCampEvaluarLEC.size(); j++) {
				bc = new BonoCampana();
				BeanUtils.copyProperties(bc, listaCampEvaluarLEC.get(j));
				pedc = new PeriodoCronograma();
				pedc.setCodigoPeriodo(bc.getCampanaLanzamiento());
				listaBaseCampEvaluarLEC.add(pedc);
			}
		}
		// bono lanzamiento
		Map criteriaLanzamiento = new HashMap();
		criteriaLanzamiento.put("codigoPais", pais.getCodigo());
		criteriaLanzamiento.put("codigoPrograma", id);
		List lecCampLanzaList = service
				.getBonoLanzamientoList(criteriaLanzamiento);
		List lecCampLanzaList2 = new ArrayList();
		List lecCampLanzaList3 = new ArrayList();
		for (int j = 0; j < lecCampLanzaList.size(); j++) {
			BonoNivel bn = new BonoNivel();
			BonoNivel bnModi = new BonoNivel();
			BeanUtils.copyProperties(bn, lecCampLanzaList.get(j));
			BeanUtils.copyProperties(bnModi, lecCampLanzaList.get(j));
			lecCampLanzaList2.add(bn);
			lecCampLanzaList3.add(bnModi);
		}

		// bono nivel
		BonoNivel bn1;
		BonoNivel bn2;
		BonoNivel bn;
		BonoNivel bnRete;
		BonoNivel bnNivAcc;
		criteria.remove("codTipoBono");
		List lecCampLanzaNivelList = service.getBonoNivelList(criteria);
		List lecCampLanzaNivelList1 = new ArrayList();

		List lecBonoNivel = new ArrayList();
		List lecBonoNivelAcc = new ArrayList();
		List lecPeriodoGracia = new ArrayList();

		bn1 = new BonoNivel();
		bn2 = new BonoNivel();
		bnRete = new BonoNivel();
		bn = new BonoNivel();
		bnNivAcc = new BonoNivel();

		for (int j = 0; j < lecCampLanzaNivelList.size(); j++) {
			bn = new BonoNivel();
			bnRete = new BonoNivel();
			bnNivAcc = new BonoNivel();
			BeanUtils.copyProperties(bn, lecCampLanzaNivelList.get(j));
			if (StringUtils.equals(bn.getCodTipoBono(), "01")) {
				BeanUtils.copyProperties(bn1, bn);
				bn1.setBonoNivel(bn.getNivel());
				bn1.setCampanaLanzamiento(bn.getCampanaLanzamiento());
				bn1.setNroLanzamiento(bn.getNroLanzamiento());
				bn1.setValorPorcObj(bn.getValorPorcObj());
				bn1.setExigencia(bn.getValorPorcObj());
			}
			if (StringUtils.equals(bn.getCodTipoBono(), "02")) {
				bn1.setSobExigencia(bn.getValorPorcObj());
			}
			if (StringUtils.equals(bn.getCodTipoBono(), "03")) {
				BeanUtils.copyProperties(bn2, bn);
				bn2.setBonoNivel(bn.getNivel());
				bn2.setCampanaLanzamiento(bn.getCampanaLanzamiento());
				bn2.setNroLanzamiento(bn.getNroLanzamiento());
				bn2.setValorPorcObj(bn.getValorPorcObj());
				bn2.setExigencia(bn.getValorPorcObj());
			}
			if (StringUtils.equals(bn.getCodTipoBono(), "04"))
				bn2.setSobExigencia(bn.getValorPorcObj());

			if (StringUtils.equals(bn.getCodTipoBono(), "05")) {
				BeanUtils.copyProperties(bnRete, bn);
				bnRete.setCodTipoBono(bn.getCodTipoBono());
				bnRete.setNroMinIng(bn.getNroMinIngr());
				bnRete.setNroMaxIng(bn.getNroMaxIngr());
				bnRete.setNroMinRete(bn.getNroMinRete());
				bnRete.setNroMaxRete(bn.getNroMaxRete());
				bnRete.setPorRetExig(bn.getPorReten());
				lecBonoNivel.add(bnRete);
			}
			if (StringUtils.equals(bn.getCodTipoBono(), "06")) {
				BeanUtils.copyProperties(bnRete, bn);
				bnRete.setCodTipoBono(bn.getCodTipoBono());
				bnRete.setNroMinIng(bn.getNroMinIngr());
				bnRete.setNroMaxIng(bn.getNroMaxIngr());
				bnRete.setNroMinRete(bn.getNroMinRete());
				bnRete.setNroMaxRete(bn.getNroMaxRete());
				bnRete.setPorRetExig(bn.getPorReten());
				lecBonoNivel.add(bnRete);
			}
			if (StringUtils.equals(bn.getCodTipoBono(), "07")) {
				BeanUtils.copyProperties(bnRete, bn);
				bnRete.setCodTipoBono(bn.getCodTipoBono());
				bnRete.setNroMinIng(bn.getNroMinIngr());
				bnRete.setNroMaxIng(bn.getNroMaxIngr());
				bnRete.setNroMinRete(bn.getNroMinRete());
				bnRete.setNroMaxRete(bn.getNroMaxRete());
				bnRete.setPorRetExig(bn.getPorReten());
				lecBonoNivel.add(bnRete);
			}
			if (StringUtils.equals(bn.getCodTipoBono(), "08")) {
				BeanUtils.copyProperties(bnNivAcc, bn);
				lecBonoNivelAcc.add(bnNivAcc);
			}
			if (StringUtils.equals(bn.getCodTipoBono(), "09")) {
				BeanUtils.copyProperties(bnNivAcc, bn);
				lecPeriodoGracia.add(bnNivAcc);
			}
		}
		if (listTipoMedi != null && listTipoMedi.size() > 0) {
			for (int k = 0; k < listTipoMedi.size(); k++) {
				Base tmo = new Base();
				BeanUtils.copyProperties(tmo, listTipoMedi.get(k));
				if (StringUtils.equals(tmo.getCodigo(), "01")) {
					bn1.setCodTipoMedi(tmo.getCodigo());
					bn1.setTipoMedi(tmo.getDescripcion());
				}
				if (StringUtils.equals(tmo.getCodigo(), "02")) {
					bn1.setCodTipoMedi(tmo.getCodigo());
					bn2.setTipoMedi(tmo.getDescripcion());
				}
			}
		}

		// pestańa canasta
		criteria.put("campanyaActivacionCanasta",
				f.getCampanyaActivacionCanasta());
		List lecCanastaList = service.getCanastaList(criteria);
		List lecCanastaDetalleList = service.getCanastaDetalleList(criteria);
		List listCanas = new ArrayList();
		Base tmo;
		Canasta can;

		// COMBO CANASTA
		for (int k = 0; k < lecCanastaList.size(); k++) {
			tmo = new Base();
			can = new Canasta();
			BeanUtils.copyProperties(can, lecCanastaList.get(k));
			tmo.setCodigo(can.getSecuencia());
			tmo.setDescripcion(can.getNombre() + "-" + can.getValor());
			listCanas.add(tmo);
		}

		Map criteriaNivelTramo = new HashMap();
		criteriaNivelTramo.put("codigoPais", pais.getCodigo());
		criteriaNivelTramo.put("codigoPrograma", id);
		List listNivelTramo = service.getNivelTramoList(criteriaNivelTramo);
		this.lecIncMontoRecuList = listNivelTramo;
		criteriaNivelTramo.put("campanyaActivacionCanasta",
				f.getCampanyaActivacionCanasta());

		List lisinc2 = service.getProgramaCanastaPremiList(criteriaNivelTramo);
		this.lecIncCanasProd = lisinc2;

		f.setCodigoTipoGeo("P");
		this.lecCanasProdList = listCanas;
		this.lecNivelAmbGeoList = lisAmbNiv;
		this.lecProgramaCorporativoNivelList = listNivel2;
		this.lecTramoList = listramos2;
		this.lecAmbGeoList = lisAmbCobranza;
		this.lecProgramaCorporativoObjCobList = lisObjCobranzaTra;

		// session.setAttribute("lecIncZonaList", lecPedLidAmbGeoList);

		this.lecIncTipoComiList = lecIncTipoComiList;
		this.lecProgramaCorporativoGestDesemList = listGestDesem;
		this.selecCampanaEvaluarLec = listCamEvaSele;
		// this.selecOrigenCampanaEvaluarLec=listCampEva;

		// bono
		// this.listaCampEvaluarLEC=listaBaseCampEvaluarLEC;
		// this.listaBaseCampEvaluarLEC=listaCampEvaluarLEC;
		this.lecCampLanzaList = lecCampLanzaList;
		this.lecCampLanzaNivelList = lecCampLanzaList2;
		this.lecCampLanzaNivelListModificacion = lecCampLanzaList3;
		this.lecRetencionList = lecBonoNivel;
		this.lecCambNivelAvan = lecBonoNivelAcc;
		this.lecPeriodoGracia = lecPeriodoGracia;
		this.lecNivelCorporativoList = listNivelCorp;

		// canasta
		this.lecCanastaList = lecCanastaList;
		this.lecProductosCanastaList = lecCanastaDetalleList;
		this.indicadorActivoCob = Constants.NUMERO_CERO;
		Integer menorPesoNivel = service.getMenorPesoNivel(null);
		if (menorPesoNivel == null)
			menorPesoNivel = new Integer(0);
		f.setMenorPesoNivel(menorPesoNivel);
	}

	// Obtiene valor de Indicador Edicion
	private boolean obtenerIndicadorEdicion(
			MantenimientoLECProgramaCorporativoForm f,
			GenericoService genericoService, String nombreParametro) {
		boolean retorno = false;
		ParametroPais parametroPais = new ParametroPais();

		parametroPais.setCodigoPais(f.getCodigoPais());
		parametroPais.setCodigoSistema(Constants.LEC_CODIGO_SISTEMA);
		parametroPais.setNombreParametro(nombreParametro);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List parametros = genericoService.getParametrosPais(parametroPais);
		if (parametros != null && parametros.size() > 0) {
			ParametroPais p = (ParametroPais) parametros.get(0);
			String valorIndicador = p.getValorParametro();
			if (StringUtils.equals(Constants.UNO, valorIndicador))
				retorno = true;
		}
		return retorno;
	}

	// carga los datos cuando la accion es modificar o consultar.
	private void mostrarData(MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.equals(f.getActCobranza(), Constants.IND_CHECK_ON))
			this.indActCobranza = true;
		else
			this.indActCobranza = false;

		if (StringUtils.equals(f.getProgramaRecono(), Constants.IND_CHECK_ON))
			this.indProgramaRecono = true;
		else
			this.indProgramaRecono = false;

		if (StringUtils.equals(f.getIndicadorFeriado(), Constants.IND_CHECK_ON))
			this.indIndicadorFeriado = true;
		else
			this.indIndicadorFeriado = false;

		if (StringUtils.equals(f.getInsPortalFFVV(), Constants.IND_CHECK_ON))
			this.indInsPortalFFVV = true;
		else
			this.indInsPortalFFVV = false;

		if (StringUtils.equals(f.getInsSistCome(), Constants.IND_CHECK_ON))
			this.indInsSistCome = true;
		else
			this.indInsSistCome = false;

		if (StringUtils.equals(f.getInsPosiLider(), Constants.IND_CHECK_ON))
			this.indInsPosiLider = true;
		else
			this.indInsPosiLider = false;

		if (StringUtils.equals(f.getPedidoWeb(), Constants.STO_ORIGEN_WEB))
			this.indPedidoWeb = true;
		else
			this.indPedidoWeb = false;

		if (StringUtils
				.equals(f.getExigPedPersonaTmp(), Constants.IND_CHECK_ON))
			this.indExigPedPersona = true;
		else
			this.indExigPedPersona = false;

		if (StringUtils.equals(f.getExigCursoCambTmp(), Constants.IND_CHECK_ON))
			this.indExigCursoCamb = true;
		else
			this.indExigCursoCamb = false;

		if (StringUtils.equals(f.getCodTipoComi(), "02"))
			this.showTipoComi = true;

		if (indExigCursoCamb)
			this.showExCurso = true;

		if (StringUtils.equals(f.getCodigoTipoMedi(), "02"))
			this.showDiasCobranza = true;

		if (StringUtils.equals(f.getIndicadorConsiderarNuevas(),
				Constants.NUMERO_UNO)) {
			this.indConsiderarNuevas = true;
			this.showExitosasDesempenio = true;
		} else {
			this.indConsiderarNuevas = false;
			this.showExitosasDesempenio = false;
		}

		if (StringUtils.isNotBlank(f.getIndicadorConsiderarMetasIngresos())
				&& f.getIndicadorConsiderarMetasIngresos().equals(
						Constants.NUMERO_UNO))
			this.indConsiderarMetasIngresos = true;
		else
			this.indConsiderarMetasIngresos = false;

		if (StringUtils.isNotBlank(f.getIndicadorconsiderarCapitalizacion())
				&& f.getIndicadorconsiderarCapitalizacion().equals(
						Constants.NUMERO_UNO))
			this.considerarIngresoCapi = true;
		else
			this.considerarIngresoCapi = false;

		this.lecProgramaCorporativoNivelListDataModel = new DataTableModel(
				this.lecProgramaCorporativoNivelList);
		this.lecProgramaCorporativoObjCobDataModel = new DataTableModel(
				this.lecProgramaCorporativoObjCobList);
		// this.lecCampLanzaNivelListDataModel = new
		// DataTableModel(this.lecCampLanzaNivelList);
		this.lecRetencionDataModel = new DataTableModel(this.lecRetencionList);
		this.lecCambNivelAvanDataModel = new DataTableModel(
				this.lecCambNivelAvan);
		this.lecPeriodoGraciaDataModel = new DataTableModel(
				this.lecPeriodoGracia);
		this.lecIncTipoComiDataModel = new DataTableModel(
				this.lecIncTipoComiList);
		this.lecIncMontoRecuDataModel = new DataTableModel(
				this.lecIncMontoRecuList);
		this.lecIncCanasProdDataModel = new DataTableModel(this.lecIncCanasProd);
		this.lecProductosCanastaDataModel = new DataTableModel(
				this.lecProductosCanastaList);
		this.lecCanastaDataModel = new DataTableModel(this.lecCanastaList);
		this.lecProgramaCorporativoGestDesemDataModel = new DataTableModel(
				this.lecProgramaCorporativoGestDesemList);
		this.lecRankingNivelesDataModel = new DataTableModel(
				this.lecRankingNivelesList);
		this.lecRankingDataModel = new DataTableModel(this.lecRankingList);
		this.lecNivelAmbGeoDataModel = new DataTableModel(
				this.lecNivelAmbGeoList);
		this.lecPedLidAmbGeoDataModel = new DataTableModel(
				this.lecPedLidAmbGeoList);

	}

	public void showTipoComision(ValueChangeEvent val) {
		this.showTipoComi = false;
		String valor = val.getNewValue().toString();
		if (StringUtils.isNotBlank(valor)) {

			if (StringUtils.equals(valor, "02"))
				this.showTipoComi = true;
		}
	}

	public void showExigirCurso(ValueChangeEvent val) {
		this.showExCurso = false;
		boolean valor = (Boolean) val.getNewValue();
		if (valor)
			this.showExCurso = true;
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if (isNew) {
			return "mantenimientoLECProgramaCorporativoForm.added";
		} else {
			return "mantenimientoLECProgramaCorporativoForm.updated";
		}
	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		int codperini = Integer.parseInt(f.getPeriodoInicio());
		if (StringUtils.isNotBlank(f.getPeriodoFin())) {
			int codperfin = Integer.parseInt(f.getPeriodoFin());
			if (codperfin < codperini)
				return "Campaña Fin debe ser mayor o igual a la Campaña Inicio";
		}

		if (this.indExigCursoCamb) {
			if (StringUtils.isBlank(f.getPeriodoInicioNivel()))
				return "Campaña Inicio (Nivel Exito): Valor Requerido";
			int codperiniNivel = Integer.parseInt(f.getPeriodoInicioNivel());
			if (codperiniNivel < codperini)
				return "Campaña Inicio (Nivel Exito) debe ser mayor o igual a la Campaña Inicio";

			if (StringUtils.isNotBlank(f.getPeriodoFinNivel())) {
				int codperfinNivel = Integer.parseInt(f.getPeriodoFinNivel());
				if (codperfinNivel < codperiniNivel)
					return "Campaña Final (Nivel Exito) debe ser mayor o igual a la Campaña Inicio (Nivel Exito)";
			}
		}

		if (StringUtils.isNotBlank(f.getCampannaInicialNivel())) {
			int codCampIniNivel = Integer.parseInt(f.getCampannaInicialNivel());
			if (codCampIniNivel < codperini)
				return "Campaña Inicio Niveles debe ser mayor o igual a la Campaña Inicio";
			if (StringUtils.isNotBlank(f.getCampannaFinalNivel())) {
				int codCampFinNivel = Integer.parseInt(f
						.getCampannaFinalNivel());
				if (codCampFinNivel < codCampIniNivel)
					return "Campaña Final Niveles debe ser mayor o igual a la Campaña Inicio Niveles";
			}
		}

		if (StringUtils.isNotBlank(f.getCampannaInicialNivelAcelerado())) {
			int codCampIniNivelAce = Integer.parseInt(f
					.getCampannaInicialNivelAcelerado());
			if (codCampIniNivelAce < codperini)
				return "Campaña Final Nivel Acelerado debe ser mayor o igual a la Campaña Inicio";

			if (StringUtils.isNotBlank(f.getCampannaFinalNivelAcelerado())) {
				int codCampFinNivelaAce = Integer.parseInt(f
						.getCampannaFinalNivelAcelerado());
				if (codCampFinNivelaAce < codCampIniNivelAce)
					return "Campaña Final Nivel Acelerado debe ser mayor o igual a la Campaña Inicio Nivel Acelerado";
			}
		}

		// PER-SiCC-2015-0548
		if (this.indPedidoWeb) {
			if (!this.indExigPedPersona)
				return "Marcar check Exigir Pase Pedido Personal";

			if (this.lecPedLidAmbGeoList == null
					|| this.lecPedLidAmbGeoList.size() == 0)
				return "Ingrese al menos un registro en la ventana de Ambito Web de la sección Pedidos Líder.";
		}

		return null;
	}

	// Metodo para ejecutar el reporte PDF -Redirecciona Pag. para visualizarlo0
	public void ejecutarReporte(ActionEvent event) {
		try {
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			if (this.beanRegistroSeleccionado == null) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}
			Map elemento = (Map) this.beanRegistroSeleccionado;
			String codigoPrograma = elemento.get("codigoPrograma").toString();
			this.reporte.setCodigoPrograma(codigoPrograma);
			this.reporte.setearValores();
			this.reporte.executeReporte(event);
			this.redireccionarPagina("reporteLETConfiguracionProgramaFormLEC");

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Carga el combo Indicador Meta- Pestaña Niveles
	private void cargarIndicadoresTipoMeta() {
		List lecIndicadorTipoMetaList = new ArrayList();
		Base t1 = new Base();
		t1.setCodigo(Constants.LEC_INDICADOR_TIPO_META_PEDIDO);
		t1.setDescripcion("Pedido");
		Base t2 = new Base();
		t2.setCodigo(Constants.LEC_INDICADOR_TIPO_META_VENTA);
		t2.setDescripcion("Venta");

		lecIndicadorTipoMetaList.add(t1);
		lecIndicadorTipoMetaList.add(t2);
		this.lecIndicadorTipoMetaList = lecIndicadorTipoMetaList;
	}

	// Carga el combo Tipo de Bono- Pestaña Bono Ciclo Vida
	private void cargarTiposBono(
			MantenimientoLECProgramaCorporativoService service) {
		Map params = new HashMap();
		params.put("indicadorTipo", "C");
		this.lecTipoBonoList = service.getTiposBono(params);

	}

	// Carga el combo Tipo Premiacion -Pestaña Bono Ciclo Vida
	private void cargarTiposPremiacion(
			MantenimientoLECProgramaCorporativoService service) {
		this.lecTipoPremiacionList = service.getTiposPremiacion();
	}

	// Metodo para habilitar - desabilitar monto premio -pestaña Bonos
	public void loadTipoPremio(ValueChangeEvent val) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		String valor = val.getNewValue().toString();
		this.habilitaMontoPremio = false;
		if (StringUtils.isNotBlank(valor)) {
			if (StringUtils.equals(valor,
					Constants.LEC_CODIGO_TIPO_PREMIACION_CANASTA)) {
				this.habilitaMontoPremio = true;
				f.setMontoPremioCicloVida("");
			} else
				this.habilitaMontoPremio = false;
		}
	}

	// Metodo para actualizar los campos de la grilla, si en Tipo premiacion
	// varia- Tab Bonos-Ciclo de Vida
	public void loadTipoPremioGrilla(String indice) {
		List lista = new ArrayList();
		int index = new Integer(indice).intValue();
		BonoNivel bononivel = new BonoNivel();
		bononivel = (BonoNivel) this.lecRetencionList.get(index);
		if (StringUtils.equals(bononivel.getCodTipoPrem(), "01"))
			bononivel.setCodTipoPrem("02");
		else
			bononivel.setCodTipoPrem("01");
		if (StringUtils.equals(bononivel.getCodTipoPrem(),
				Constants.LEC_CODIGO_TIPO_PREMIACION_CANASTA)) {
			bononivel.setMonPrem("");
			this.lecRetencionList.set(index, bononivel);
			this.lecRetencionDataModel = new DataTableModel(
					this.lecRetencionList);
		}
	}

	// Obtiene la descripcion del Tipo Bono desde su Código
	private String getDescripcionTipoBono(String codigoTipoBono) {
		String desc = "";
		List tipos = this.lecTipoBonoList;
		if (tipos != null && tipos.size() > 0) {
			for (int i = 0; i < tipos.size(); i++) {
				Base t = (Base) tipos.get(i);
				if (StringUtils.equals(t.getCodigo(), codigoTipoBono)) {
					desc = t.getDescripcion();
					break;
				}
			}
		}
		return desc;
	}

	// Cargar el combo Tipo Canasta
	private void cargarTiposCanasta(
			MantenimientoLECProgramaCorporativoService service) {
		this.lecTipoCanastaList = service.getTiposCanasta();
	}

	// Obtiene el nombre del Tipo Canasta ingresando el codigo
	private String getNombreTipoCanasta(String codigoTipoCanasta) {
		String desc = "";
		List tipos = this.lecTipoCanastaList;
		if (tipos != null && tipos.size() > 0) {
			for (int i = 0; i < tipos.size(); i++) {
				Base t = (Base) tipos.get(i);
				if (StringUtils.equals(t.getCodigo(), codigoTipoCanasta)) {
					desc = t.getDescripcion();
					break;
				}
			}
		}
		return desc;
	}

	// Verifica los datos antes de isnertarlos Tab Gestion desempeño
	private String verificarListaGestionDesempeñoTraslapen(List lista,
			ProgramaDesempenio oc) {
		for (int i = 0; i < lista.size(); i++) {
			Map p = (Map) lista.get(i);
			String ranIni = MapUtils.getString(p, "ranInicio", "");
			String ranFin = MapUtils.getString(p, "ranFin", "");

			// Nro. veces exitosa
			// (nMin = aMin && nMax =aMax) || (nMin > aMax && nMax > aMax) ||
			// (nMin < aMin && nMax < aMin) <--- VALIDA TRASLAPES

			int nMin = Integer.parseInt(oc.getRanInicio());
			int nMax = Integer.parseInt(oc.getRanFin());

			int aMin = Integer.parseInt(ranIni);
			int aMax = Integer.parseInt(ranFin);

			if (!((nMin == aMin && nMax == aMax)
					|| (nMin > aMax && nMax > aMax) || (nMin < aMin && nMax < aMin))) {
				String proceso = "mantenimientoLECProgramaCorporativoForm.error.gestioDesempenho.rangotrasLapando";
				return proceso;
			}
		}
		return null;
	}

	// valida que el nro de veces inicio sea menor al nro veces fin
	public void actualizaGrillaDesempenio(String indice) {
		try {
			int index = new Integer(indice).intValue();
			Map registro = (Map) this.lecProgramaCorporativoGestDesemList
					.get(index);
			String inicio = registro.get("ranInicio").toString();
			String fin = registro.get("ranFin").toString();
			if (new Integer(inicio).intValue() > new Integer(fin).intValue()) {
				registro.put("ranInicio", fin);
				this.lecProgramaCorporativoGestDesemList.set(index, registro);
				this.lecProgramaCorporativoGestDesemDataModel = new DataTableModel(
						this.lecProgramaCorporativoGestDesemList);
				throw new Exception(
						this.getResourceMessage("mantenimientoLECProgramaCorporativoForm.error.ranInicioMayor"));
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void actualizaGrillaRanking(String indice) {
		try {
			int index = new Integer(indice).intValue();
			Ranking registro = (Ranking) this.lecRankingList.get(index);
			if (StringUtils.isBlank(registro
					.getNumeroCampanyasCumplimentoPedido())) {
				registro.setNumeroCampanyasCumplimentoPedido("1");
				this.lecRankingList.set(index, registro);
				this.lecRankingDataModel = new DataTableModel(
						this.lecRankingList);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Obtener el indicador Despacho.
	 */
	private String getIndicadorDespachoCanasta() {
		String indicador = Constants.NUMERO_CERO;
		List list = this.lecIncCanasProd;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map r = (Map) list.get(i);
				if (StringUtils.equals(
						MapUtils.getString(r, "codigoTipoCanasta"),
						Constants.LEC_CODIGO_TIPO_CANASTA_OBJETIVO_PEDIDOS)) {
					indicador = Constants.NUMERO_UNO;
					break;
				}
			}
		}
		return indicador;
	}

	// Filtra Bono Lanzamiento por Campaña
	public void filtrarBonoLanzamiento(ActionEvent actionEvent) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
			MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
			if (validarfiltrarBonoLanzamiento(f))
				return;

			String id = this.idCodigoPrograma;
			Map criteriaLanzamiento = new HashMap();
			criteriaLanzamiento.put("codigoPais", pais.getCodigo());
			criteriaLanzamiento.put("codigoPrograma", id);
			criteriaLanzamiento.put("campanaLanzamiento",
					f.getCampLanzamiento());
			List lecCampLanzaList = service
					.getBonoLanzamientoList(criteriaLanzamiento);
			List lecCampLanzaList2 = new ArrayList();
			List lecCampLanzaList3 = new ArrayList();
			for (int j = 0; j < lecCampLanzaList.size(); j++) {
				BonoNivel bn = new BonoNivel();
				BonoNivel bnModi = new BonoNivel();
				BeanUtils.copyProperties(bn, lecCampLanzaList.get(j));
				BeanUtils.copyProperties(bnModi, lecCampLanzaList.get(j));
				lecCampLanzaList2.add(bn);
				lecCampLanzaList3.add(bnModi);
			}
			this.lecCampLanzaList = lecCampLanzaList;
			this.lecCampLanzaNivelList = lecCampLanzaList2;
			this.lecCampLanzaNivelListModificacion = lecCampLanzaList3;

			this.lecCampLanzaNivelListDataModel = new DataTableModel(
					this.lecCampLanzaNivelList);
			f.setTabSeleccion("");
			f.setTabSeleccion(Constants.LEC_TAB_PROGRAMA_CORPORATIVO_BONO);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// Valida el Campo Campaña antes de ejecutar el filtrar Bono Lanzamiento
	private Boolean validarfiltrarBonoLanzamiento(
			MantenimientoLECProgramaCorporativoForm f) {
		if (StringUtils.isBlank(f.getCampLanzamiento())) {
			this.setMensajeAlertaDefault("Campaña Lanzamiento: Valor Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;
		}
		return false;
	}

	// Verifica si el Nivel ha sido utilizado por otras pestañas
	private String verificarNivelUsado(String nombreNivel) {
		String messageKey = "";

		// Tab Bonos
		List lecCampLanzaNivelList = this.lecCampLanzaNivelList;
		List lecRetencionList = this.lecRetencionList;
		List lecCambNivelAvan = this.lecCambNivelAvan;
		List lecPeriodoGracia = this.lecPeriodoGracia;

		// Tab Incentivos
		List lecIncTipoComiList = this.lecIncTipoComiList;
		List lecIncMontoRecuList = this.lecIncMontoRecuList;
		List lecIncCanasProd = this.lecIncCanasProd;

		if (lecCampLanzaNivelList != null && lecCampLanzaNivelList.size() > 0) {
			for (int i = 0; i < lecCampLanzaNivelList.size(); i++) {
				BonoNivel bn = (BonoNivel) lecCampLanzaNivelList.get(i);
				if (StringUtils.equalsIgnoreCase(bn.getNivel(), nombreNivel)) {
					messageKey = "mantenimientoLECProgramaCorporativoForm.error.nivelUtilizado";
					return messageKey;
				}
			}
		}

		if (lecRetencionList != null && lecRetencionList.size() > 0) {
			for (int i = 0; i < lecRetencionList.size(); i++) {

				String tempcodigo = "";
				if (lecRetencionList.get(i) instanceof Base) {
					Base temp = (Base) lecRetencionList.get(i);
					tempcodigo = temp.getDescripcion();
				} else if (lecRetencionList.get(i) instanceof BonoNivel) {
					BonoNivel temp = (BonoNivel) lecRetencionList.get(i);
					tempcodigo = temp.getNivel();
				}

				if (StringUtils.equalsIgnoreCase(tempcodigo, nombreNivel)) {
					messageKey = "mantenimientoLECProgramaCorporativoForm.error.nivelUtilizado";
					return messageKey;
				}
			}
		}

		if (lecCambNivelAvan != null && lecCambNivelAvan.size() > 0) {
			for (int i = 0; i < lecCambNivelAvan.size(); i++) {
				BonoNivel bn = (BonoNivel) lecCambNivelAvan.get(i);
				if (StringUtils.equalsIgnoreCase(bn.getNivel(), nombreNivel)) {
					messageKey = "mantenimientoLECProgramaCorporativoForm.error.nivelUtilizado";
					return messageKey;
				}
			}
		}

		if (lecPeriodoGracia != null && lecPeriodoGracia.size() > 0) {
			for (int i = 0; i < lecPeriodoGracia.size(); i++) {
				BonoNivel bn = (BonoNivel) lecPeriodoGracia.get(i);
				if (StringUtils.equalsIgnoreCase(bn.getNivel(), nombreNivel)) {
					messageKey = "mantenimientoLECProgramaCorporativoForm.error.nivelUtilizado";
					return messageKey;
				}
			}
		}

		if (lecIncTipoComiList != null && lecIncTipoComiList.size() > 0) {
			for (int i = 0; i < lecIncTipoComiList.size(); i++) {
				Nivel bn = (Nivel) lecIncTipoComiList.get(i);
				if (StringUtils.equalsIgnoreCase(bn.getNivel(), nombreNivel)) {
					messageKey = "mantenimientoLECProgramaCorporativoForm.error.nivelUtilizado.incentivos";
					return messageKey;
				}
			}
		}

		if (lecIncMontoRecuList != null && lecIncMontoRecuList.size() > 0) {
			for (int i = 0; i < lecIncMontoRecuList.size(); i++) {
				String nombre = "";
				if (lecIncMontoRecuList.get(i) instanceof NivelTramo) {
					NivelTramo bn = (NivelTramo) lecIncMontoRecuList.get(i);
					nombre = bn.getNivel();
				} else if (lecIncMontoRecuList.get(i) instanceof HashMap) {
					Map bn = (Map) lecIncMontoRecuList.get(i);
					nombre = MapUtils.getString(bn, "nivel");
				}

				if (StringUtils.equalsIgnoreCase(nombre, nombreNivel)) {
					messageKey = "mantenimientoLECProgramaCorporativoForm.error.nivelUtilizado.incentivos";
					return messageKey;
				}
			}
		}

		if (lecIncCanasProd != null && lecIncCanasProd.size() > 0) {
			for (int i = 0; i < lecIncCanasProd.size(); i++) {
				Map bn = (Map) lecIncCanasProd.get(i);
				if (StringUtils.equalsIgnoreCase(
						MapUtils.getString(bn, "nivel"), nombreNivel)) {
					messageKey = "mantenimientoLECProgramaCorporativoForm.error.nivelUtilizado.incentivos";
					return messageKey;
				}
			}
		}
		return messageKey;
	}

	public void mostrarPanelExitosas(ValueChangeEvent e) {
		MantenimientoLECProgramaCorporativoForm f = (MantenimientoLECProgramaCorporativoForm) this.formMantenimiento;
		Boolean flagMostrarRangoFechas = ((Boolean) e.getNewValue())
				.booleanValue();
		this.showExitosasDesempenio = flagMostrarRangoFechas;
		f.setIndicadorExitoNuevas(null);

	}
	
	
	/**
	 * @param value
	 * @param filter
	 * @param locale
	 * @return
	 */
	public boolean filtrarRangoIncentivo(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
        
        String valueText = (value == null) ? null : value.toString().trim();
        if(valueText == null||valueText.equals("")) {
            return true;
        }
        String lista[] = StringUtils.split(valueText, " - "); 
        int valor = ((Comparable) filterText).compareTo(lista[0]);
        if (valor >= 0) {
        	if (lista.length == 1)
        		return true;
        	if (StringUtils.isBlank(lista[1])) 
        		return true;
        	valor = ((Comparable) filterText).compareTo(lista[1]);
        	if (valor <= 0) 
        		return true;
        }
        return false;
    }
	
	
	

	public List getLecTipoComiList() {
		return lecTipoComiList;
	}

	public void setLecTipoComiList(List lecTipoComiList) {
		this.lecTipoComiList = lecTipoComiList;
	}

	public boolean isIndActCobranza() {
		return indActCobranza;
	}

	public void setIndActCobranza(boolean indActCobranza) {
		this.indActCobranza = indActCobranza;
	}

	public boolean isIndProgramaRecono() {
		return indProgramaRecono;
	}

	public void setIndProgramaRecono(boolean indProgramaRecono) {
		this.indProgramaRecono = indProgramaRecono;
	}

	public boolean isIndIndicadorFeriado() {
		return indIndicadorFeriado;
	}

	public void setIndIndicadorFeriado(boolean indIndicadorFeriado) {
		this.indIndicadorFeriado = indIndicadorFeriado;
	}

	public boolean isIndInsPortalFFVV() {
		return indInsPortalFFVV;
	}

	public void setIndInsPortalFFVV(boolean indInsPortalFFVV) {
		this.indInsPortalFFVV = indInsPortalFFVV;
	}

	public boolean isIndInsSistCome() {
		return indInsSistCome;
	}

	public void setIndInsSistCome(boolean indInsSistCome) {
		this.indInsSistCome = indInsSistCome;
	}

	public boolean isIndInsPosiLider() {
		return indInsPosiLider;
	}

	public void setIndInsPosiLider(boolean indInsPosiLider) {
		this.indInsPosiLider = indInsPosiLider;
	}

	public boolean isIndPedidoWeb() {
		return indPedidoWeb;
	}

	public void setIndPedidoWeb(boolean indPedidoWeb) {
		this.indPedidoWeb = indPedidoWeb;
	}

	public boolean isIndExigPedPersona() {
		return indExigPedPersona;
	}

	public void setIndExigPedPersona(boolean indExigPedPersona) {
		this.indExigPedPersona = indExigPedPersona;
	}

	public List getLecPedLidAmbGeoList() {
		return lecPedLidAmbGeoList;
	}

	public void setLecPedLidAmbGeoList(List lecPedLidAmbGeoList) {
		this.lecPedLidAmbGeoList = lecPedLidAmbGeoList;
	}

	public List getLecTipoNivelList() {
		return lecTipoNivelList;
	}

	public void setLecTipoNivelList(List lecTipoNivelList) {
		this.lecTipoNivelList = lecTipoNivelList;
	}

	public List getLecNivelCorporativoList() {
		return lecNivelCorporativoList;
	}

	public void setLecNivelCorporativoList(List lecNivelCorporativoList) {
		this.lecNivelCorporativoList = lecNivelCorporativoList;
	}

	public List getLecProgramaCorporativoNivelList() {
		return lecProgramaCorporativoNivelList;
	}

	public void setLecProgramaCorporativoNivelList(
			List lecProgramaCorporativoNivelList) {
		this.lecProgramaCorporativoNivelList = lecProgramaCorporativoNivelList;
	}

	public List getLecRetencionList() {
		return lecRetencionList;
	}

	public void setLecRetencionList(List lecRetencionList) {
		this.lecRetencionList = lecRetencionList;
	}

	public DataTableModel getLecProgramaCorporativoNivelListDataModel() {
		return lecProgramaCorporativoNivelListDataModel;
	}

	public void setLecProgramaCorporativoNivelListDataModel(
			DataTableModel lecProgramaCorporativoNivelListDataModel) {
		this.lecProgramaCorporativoNivelListDataModel = lecProgramaCorporativoNivelListDataModel;
	}

	public Object getBeanLecProgramaCorporativoNivel() {
		return beanLecProgramaCorporativoNivel;
	}

	public void setBeanLecProgramaCorporativoNivel(
			Object beanLecProgramaCorporativoNivel) {
		this.beanLecProgramaCorporativoNivel = beanLecProgramaCorporativoNivel;
	}

	public String getIndicadorActivoNivel() {
		return indicadorActivoNivel;
	}

	public void setIndicadorActivoNivel(String indicadorActivoNivel) {
		this.indicadorActivoNivel = indicadorActivoNivel;
	}

	public boolean isConsultar() {
		return consultar;
	}

	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getLecTipoMedicionObjetivoList() {
		return lecTipoMedicionObjetivoList;
	}

	public void setLecTipoMedicionObjetivoList(List lecTipoMedicionObjetivoList) {
		this.lecTipoMedicionObjetivoList = lecTipoMedicionObjetivoList;
	}

	public List getLecSelectTramoList() {
		return lecSelectTramoList;
	}

	public void setLecSelectTramoList(List lecSelectTramoList) {
		this.lecSelectTramoList = lecSelectTramoList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public boolean isShowRegionCobranza() {
		return showRegionCobranza;
	}

	public void setShowRegionCobranza(boolean showRegionCobranza) {
		this.showRegionCobranza = showRegionCobranza;
	}

	public boolean isShowZonaCobranza() {
		return showZonaCobranza;
	}

	public void setShowZonaCobranza(boolean showZonaCobranza) {
		this.showZonaCobranza = showZonaCobranza;
	}

	public boolean isShowDiasCobranza() {
		return showDiasCobranza;
	}

	public void setShowDiasCobranza(boolean showDiasCobranza) {
		this.showDiasCobranza = showDiasCobranza;
	}

	public List getLecAmbGeoList() {
		return lecAmbGeoList;
	}

	public void setLecAmbGeoList(List lecAmbGeoList) {
		this.lecAmbGeoList = lecAmbGeoList;
	}

	public List getLecTramoList() {
		return lecTramoList;
	}

	public void setLecTramoList(List lecTramoList) {
		this.lecTramoList = lecTramoList;
	}

	public List getLecProgramaCorporativoObjCobList() {
		return lecProgramaCorporativoObjCobList;
	}

	public void setLecProgramaCorporativoObjCobList(
			List lecProgramaCorporativoObjCobList) {
		this.lecProgramaCorporativoObjCobList = lecProgramaCorporativoObjCobList;
	}

	public DataTableModel getLecProgramaCorporativoObjCobDataModel() {
		return lecProgramaCorporativoObjCobDataModel;
	}

	public void setLecProgramaCorporativoObjCobDataModel(
			DataTableModel lecProgramaCorporativoObjCobDataModel) {
		this.lecProgramaCorporativoObjCobDataModel = lecProgramaCorporativoObjCobDataModel;
	}

	public Object getBeanLecProgramaCorporativoObjCob() {
		return beanLecProgramaCorporativoObjCob;
	}

	public void setBeanLecProgramaCorporativoObjCob(
			Object beanLecProgramaCorporativoObjCob) {
		this.beanLecProgramaCorporativoObjCob = beanLecProgramaCorporativoObjCob;
	}

	public String getIndicadorActivoCob() {
		return indicadorActivoCob;
	}

	public void setIndicadorActivoCob(String indicadorActivoCob) {
		this.indicadorActivoCob = indicadorActivoCob;
	}

	public LabelValue[] getLecBonoNivelCorporativoList() {
		return lecBonoNivelCorporativoList;
	}

	public void setLecBonoNivelCorporativoList(
			LabelValue[] lecBonoNivelCorporativoList) {
		this.lecBonoNivelCorporativoList = lecBonoNivelCorporativoList;
	}

	public List getLecTipoMedicionList() {
		return lecTipoMedicionList;
	}

	public void setLecTipoMedicionList(List lecTipoMedicionList) {
		this.lecTipoMedicionList = lecTipoMedicionList;
	}

	public List getLecCampLanzaNivelList() {
		return lecCampLanzaNivelList;
	}

	public void setLecCampLanzaNivelList(List lecCampLanzaNivelList) {
		this.lecCampLanzaNivelList = lecCampLanzaNivelList;
	}

	public DataTableModel getLecCampLanzaNivelListDataModel() {
		return lecCampLanzaNivelListDataModel;
	}

	public void setLecCampLanzaNivelListDataModel(
			DataTableModel lecCampLanzaNivelListDataModel) {
		this.lecCampLanzaNivelListDataModel = lecCampLanzaNivelListDataModel;
	}

	public String getIndicadorActivoBonNivel() {
		return indicadorActivoBonNivel;
	}

	public void setIndicadorActivoBonNivel(String indicadorActivoBonNivel) {
		this.indicadorActivoBonNivel = indicadorActivoBonNivel;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public BusquedaProductoSearchAction getBusquedaProductoSearch() {
		return busquedaProductoSearch;
	}

	public void setBusquedaProductoSearch(
			BusquedaProductoSearchAction busquedaProductoSearch) {
		this.busquedaProductoSearch = busquedaProductoSearch;
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	public DataTableModel getLecRetencionDataModel() {
		return lecRetencionDataModel;
	}

	public void setLecRetencionDataModel(DataTableModel lecRetencionDataModel) {
		this.lecRetencionDataModel = lecRetencionDataModel;
	}

	public String getIndicadorActivoBonCicloVida22() {
		return indicadorActivoBonCicloVida22;
	}

	public void setIndicadorActivoBonCicloVida22(
			String indicadorActivoBonCicloVida22) {
		this.indicadorActivoBonCicloVida22 = indicadorActivoBonCicloVida22;
	}

	public List getLecCambNivelAvan() {
		return lecCambNivelAvan;
	}

	public void setLecCambNivelAvan(List lecCambNivelAvan) {
		this.lecCambNivelAvan = lecCambNivelAvan;
	}

	public DataTableModel getLecCambNivelAvanDataModel() {
		return lecCambNivelAvanDataModel;
	}

	public void setLecCambNivelAvanDataModel(
			DataTableModel lecCambNivelAvanDataModel) {
		this.lecCambNivelAvanDataModel = lecCambNivelAvanDataModel;
	}

	public String getIndicadorActivoBonNivelAce() {
		return indicadorActivoBonNivelAce;
	}

	public void setIndicadorActivoBonNivelAce(String indicadorActivoBonNivelAce) {
		this.indicadorActivoBonNivelAce = indicadorActivoBonNivelAce;
	}

	public List getLecPeriodoGracia() {
		return lecPeriodoGracia;
	}

	public void setLecPeriodoGracia(List lecPeriodoGracia) {
		this.lecPeriodoGracia = lecPeriodoGracia;
	}

	public DataTableModel getLecPeriodoGraciaDataModel() {
		return lecPeriodoGraciaDataModel;
	}

	public void setLecPeriodoGraciaDataModel(
			DataTableModel lecPeriodoGraciaDataModel) {
		this.lecPeriodoGraciaDataModel = lecPeriodoGraciaDataModel;
	}

	public List getLecIncTipoComiList() {
		return lecIncTipoComiList;
	}

	public void setLecIncTipoComiList(List lecIncTipoComiList) {
		this.lecIncTipoComiList = lecIncTipoComiList;
	}

	public DataTableModel getLecIncTipoComiDataModel() {
		return lecIncTipoComiDataModel;
	}

	public void setLecIncTipoComiDataModel(
			DataTableModel lecIncTipoComiDataModel) {
		this.lecIncTipoComiDataModel = lecIncTipoComiDataModel;
	}

	public String getIndicadorActivoIncNivel() {
		return indicadorActivoIncNivel;
	}

	public void setIndicadorActivoIncNivel(String indicadorActivoIncNivel) {
		this.indicadorActivoIncNivel = indicadorActivoIncNivel;
	}

	public List getLecIncCanasProd() {
		return lecIncCanasProd;
	}

	public void setLecIncCanasProd(List lecIncCanasProd) {
		this.lecIncCanasProd = lecIncCanasProd;
	}

	public DataTableModel getLecIncCanasProdDataModel() {
		return lecIncCanasProdDataModel;
	}

	public void setLecIncCanasProdDataModel(
			DataTableModel lecIncCanasProdDataModel) {
		this.lecIncCanasProdDataModel = lecIncCanasProdDataModel;
	}

	public Object getBeanlecIncCanasProd() {
		return beanlecIncCanasProd;
	}

	public void setBeanlecIncCanasProd(Object beanlecIncCanasProd) {
		this.beanlecIncCanasProd = beanlecIncCanasProd;
	}

	public boolean isIndIncCanasProd() {
		return indIncCanasProd;
	}

	public void setIndIncCanasProd(boolean indIncCanasProd) {
		this.indIncCanasProd = indIncCanasProd;
	}

	public String getIndicadorActivoIncCanasta() {
		return indicadorActivoIncCanasta;
	}

	public void setIndicadorActivoIncCanasta(String indicadorActivoIncCanasta) {
		this.indicadorActivoIncCanasta = indicadorActivoIncCanasta;
	}

	public List getLecCanastaList() {
		return lecCanastaList;
	}

	public void setLecCanastaList(List lecCanastaList) {
		this.lecCanastaList = lecCanastaList;
	}

	public List getLecProductosCanastaList() {
		return lecProductosCanastaList;
	}

	public void setLecProductosCanastaList(List lecProductosCanastaList) {
		this.lecProductosCanastaList = lecProductosCanastaList;
	}

	public List getLecCanasProdList() {
		return lecCanasProdList;
	}

	public void setLecCanasProdList(List lecCanasProdList) {
		this.lecCanasProdList = lecCanasProdList;
	}

	public List getLecIncMontoRecuList() {
		return lecIncMontoRecuList;
	}

	public void setLecIncMontoRecuList(List lecIncMontoRecuList) {
		this.lecIncMontoRecuList = lecIncMontoRecuList;
	}

	public DataTableModel getLecIncMontoRecuDataModel() {
		return lecIncMontoRecuDataModel;
	}

	public void setLecIncMontoRecuDataModel(
			DataTableModel lecIncMontoRecuDataModel) {
		this.lecIncMontoRecuDataModel = lecIncMontoRecuDataModel;
	}

	public Object getBeanlecIncMontoRecu() {
		return beanlecIncMontoRecu;
	}

	public void setBeanlecIncMontoRecu(Object beanlecIncMontoRecu) {
		this.beanlecIncMontoRecu = beanlecIncMontoRecu;
	}

	public DataTableModel getLecCanastaDataModel() {
		return lecCanastaDataModel;
	}

	public void setLecCanastaDataModel(DataTableModel lecCanastaDataModel) {
		this.lecCanastaDataModel = lecCanastaDataModel;
	}

	public Object getBeanlecCanasta() {
		return beanlecCanasta;
	}

	public void setBeanlecCanasta(Object beanlecCanasta) {
		this.beanlecCanasta = beanlecCanasta;
	}

	public String getIndicadorActivoCanasta() {
		return indicadorActivoCanasta;
	}

	public void setIndicadorActivoCanasta(String indicadorActivoCanasta) {
		this.indicadorActivoCanasta = indicadorActivoCanasta;
	}

	public List getLecTiposOfertaProductoCanasta() {
		return lecTiposOfertaProductoCanasta;
	}

	public void setLecTiposOfertaProductoCanasta(
			List lecTiposOfertaProductoCanasta) {
		this.lecTiposOfertaProductoCanasta = lecTiposOfertaProductoCanasta;
	}

	public DataTableModel getLecProductosCanastaDataModel() {
		return lecProductosCanastaDataModel;
	}

	public void setLecProductosCanastaDataModel(
			DataTableModel lecProductosCanastaDataModel) {
		this.lecProductosCanastaDataModel = lecProductosCanastaDataModel;
	}

	public Object getBeanlecProductosCanasta() {
		return beanlecProductosCanasta;
	}

	public void setBeanlecProductosCanasta(Object beanlecProductosCanasta) {
		this.beanlecProductosCanasta = beanlecProductosCanasta;
	}

	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	public static String getPopupProducto() {
		return POPUP_PRODUCTO;
	}

	public List getLecListaTipoDesem() {
		return lecListaTipoDesem;
	}

	public void setLecListaTipoDesem(List lecListaTipoDesem) {
		this.lecListaTipoDesem = lecListaTipoDesem;
	}

	// public DualListModel<Base> getListaCampanias() {
	// return listaCampanias;
	// }
	//
	// public void setListaCampanias(DualListModel<Base> listaCampanias) {
	// this.listaCampanias = listaCampanias;
	// }

	public List getCampanaEvaluacionLec() {
		return campanaEvaluacionLec;
	}

	public void setCampanaEvaluacionLec(List campanaEvaluacionLec) {
		this.campanaEvaluacionLec = campanaEvaluacionLec;
	}

	public List getSelecCampanaEvaluarLec() {
		return selecCampanaEvaluarLec;
	}

	public void setSelecCampanaEvaluarLec(List selecCampanaEvaluarLec) {
		this.selecCampanaEvaluarLec = selecCampanaEvaluarLec;
	}

	public List getLecProgramaCorporativoGestDesemList() {
		return lecProgramaCorporativoGestDesemList;
	}

	public void setLecProgramaCorporativoGestDesemList(
			List lecProgramaCorporativoGestDesemList) {
		this.lecProgramaCorporativoGestDesemList = lecProgramaCorporativoGestDesemList;
	}

	public DataTableModel getLecProgramaCorporativoGestDesemDataModel() {
		return lecProgramaCorporativoGestDesemDataModel;
	}

	public void setLecProgramaCorporativoGestDesemDataModel(
			DataTableModel lecProgramaCorporativoGestDesemDataModel) {
		this.lecProgramaCorporativoGestDesemDataModel = lecProgramaCorporativoGestDesemDataModel;
	}

	public Object getBeanlecProgramaCorporativoGestDesem() {
		return beanlecProgramaCorporativoGestDesem;
	}

	public void setBeanlecProgramaCorporativoGestDesem(
			Object beanlecProgramaCorporativoGestDesem) {
		this.beanlecProgramaCorporativoGestDesem = beanlecProgramaCorporativoGestDesem;
	}

	public String getIndicadorActivoGestDesem() {
		return indicadorActivoGestDesem;
	}

	public void setIndicadorActivoGestDesem(String indicadorActivoGestDesem) {
		this.indicadorActivoGestDesem = indicadorActivoGestDesem;
	}

	public List getLecTipoRankingList() {
		return lecTipoRankingList;
	}

	public void setLecTipoRankingList(List lecTipoRankingList) {
		this.lecTipoRankingList = lecTipoRankingList;
	}

	public List getLecRankingNivelesList() {
		return lecRankingNivelesList;
	}

	public void setLecRankingNivelesList(List lecRankingNivelesList) {
		this.lecRankingNivelesList = lecRankingNivelesList;
	}

	public List getLecRankingList() {
		return lecRankingList;
	}

	public void setLecRankingList(List lecRankingList) {
		this.lecRankingList = lecRankingList;
	}

	public DataTableModel getLecRankingDataModel() {
		return lecRankingDataModel;
	}

	public void setLecRankingDataModel(DataTableModel lecRankingDataModel) {
		this.lecRankingDataModel = lecRankingDataModel;
	}

	public Object getBeanlecRanking() {
		return beanlecRanking;
	}

	public void setBeanlecRanking(Object beanlecRanking) {
		this.beanlecRanking = beanlecRanking;
	}

	public String getPrimerTipoMedi() {
		return primerTipoMedi;
	}

	public void setPrimerTipoMedi(String primerTipoMedi) {
		this.primerTipoMedi = primerTipoMedi;
	}

	public String getSegundoTipoMedi() {
		return segundoTipoMedi;
	}

	public void setSegundoTipoMedi(String segundoTipoMedi) {
		this.segundoTipoMedi = segundoTipoMedi;
	}

	public String getPrimerDescriTipoMedi() {
		return primerDescriTipoMedi;
	}

	public void setPrimerDescriTipoMedi(String primerDescriTipoMedi) {
		this.primerDescriTipoMedi = primerDescriTipoMedi;
	}

	public String getSegundoDescriTipoMedi() {
		return segundoDescriTipoMedi;
	}

	public void setSegundoDescriTipoMedi(String segundoDescriTipoMedi) {
		this.segundoDescriTipoMedi = segundoDescriTipoMedi;
	}

	public String getCodigoTipoRanking() {
		return codigoTipoRanking;
	}

	public void setCodigoTipoRanking(String codigoTipoRanking) {
		this.codigoTipoRanking = codigoTipoRanking;
	}

	public DataTableModel getLecRankingNivelesDataModel() {
		return lecRankingNivelesDataModel;
	}

	public void setLecRankingNivelesDataModel(
			DataTableModel lecRankingNivelesDataModel) {
		this.lecRankingNivelesDataModel = lecRankingNivelesDataModel;
	}

	public Object[] getValores() {
		return valores;
	}

	public void setValores(Object[] valores) {
		this.valores = valores;
	}

	public List getListAux01() {
		return listAux01;
	}

	public void setListAux01(List listAux01) {
		this.listAux01 = listAux01;
	}

	public List getListAux02() {
		return listAux02;
	}

	public void setListAux02(List listAux02) {
		this.listAux02 = listAux02;
	}

	public List getListAux03() {
		return listAux03;
	}

	public void setListAux03(List listAux03) {
		this.listAux03 = listAux03;
	}

	public List getLecNivelAmbGeoList() {
		return lecNivelAmbGeoList;
	}

	public void setLecNivelAmbGeoList(List lecNivelAmbGeoList) {
		this.lecNivelAmbGeoList = lecNivelAmbGeoList;
	}

	public List getAmbitoNivelSeleccion() {
		return ambitoNivelSeleccion;
	}

	public void setAmbitoNivelSeleccion(List ambitoNivelSeleccion) {
		this.ambitoNivelSeleccion = ambitoNivelSeleccion;
	}

	public DataTableModel getLecNivelAmbGeoDataModel() {
		return lecNivelAmbGeoDataModel;
	}

	public void setLecNivelAmbGeoDataModel(
			DataTableModel lecNivelAmbGeoDataModel) {
		this.lecNivelAmbGeoDataModel = lecNivelAmbGeoDataModel;
	}

	public LabelValue[] getSiccZonaNivelList() {
		return siccZonaNivelList;
	}

	public void setSiccZonaNivelList(LabelValue[] siccZonaNivelList) {
		this.siccZonaNivelList = siccZonaNivelList;
	}

	public boolean isIndExcluirNivel() {
		return indExcluirNivel;
	}

	public void setIndExcluirNivel(boolean indExcluirNivel) {
		this.indExcluirNivel = indExcluirNivel;
	}

	public String getNivelShowRegionZona() {
		return nivelShowRegionZona;
	}

	public void setNivelShowRegionZona(String nivelShowRegionZona) {
		this.nivelShowRegionZona = nivelShowRegionZona;
	}

	public String getIndicadorActivoAmbGeoNivel() {
		return indicadorActivoAmbGeoNivel;
	}

	public void setIndicadorActivoAmbGeoNivel(String indicadorActivoAmbGeoNivel) {
		this.indicadorActivoAmbGeoNivel = indicadorActivoAmbGeoNivel;
	}

	public List getAmbitoPedidoSeleccion() {
		return ambitoPedidoSeleccion;
	}

	public void setAmbitoPedidoSeleccion(List ambitoPedidoSeleccion) {
		this.ambitoPedidoSeleccion = ambitoPedidoSeleccion;
	}

	public DataTableModel getLecPedLidAmbGeoDataModel() {
		return lecPedLidAmbGeoDataModel;
	}

	public void setLecPedLidAmbGeoDataModel(
			DataTableModel lecPedLidAmbGeoDataModel) {
		this.lecPedLidAmbGeoDataModel = lecPedLidAmbGeoDataModel;
	}

	public LabelValue[] getSiccZonaPedidoList() {
		return siccZonaPedidoList;
	}

	public void setSiccZonaPedidoList(LabelValue[] siccZonaPedidoList) {
		this.siccZonaPedidoList = siccZonaPedidoList;
	}

	public String getPedidoShowRegionZona() {
		return pedidoShowRegionZona;
	}

	public void setPedidoShowRegionZona(String pedidoShowRegionZona) {
		this.pedidoShowRegionZona = pedidoShowRegionZona;
	}

	public String getIndicadorActivoAmbGeoPedidoLider() {
		return indicadorActivoAmbGeoPedidoLider;
	}

	public void setIndicadorActivoAmbGeoPedidoLider(
			String indicadorActivoAmbGeoPedidoLider) {
		this.indicadorActivoAmbGeoPedidoLider = indicadorActivoAmbGeoPedidoLider;
	}

	public boolean isIndExcluir() {
		return indExcluir;
	}

	public void setIndExcluir(boolean indExcluir) {
		this.indExcluir = indExcluir;
	}

	public List getLecProgramaCorporativoList() {
		return lecProgramaCorporativoList;
	}

	public void setLecProgramaCorporativoList(List lecProgramaCorporativoList) {
		this.lecProgramaCorporativoList = lecProgramaCorporativoList;
	}

	public List getLecProgramaCorporativoObjCobTablasAuxiliaresList() {
		return lecProgramaCorporativoObjCobTablasAuxiliaresList;
	}

	public void setLecProgramaCorporativoObjCobTablasAuxiliaresList(
			List lecProgramaCorporativoObjCobTablasAuxiliaresList) {
		this.lecProgramaCorporativoObjCobTablasAuxiliaresList = lecProgramaCorporativoObjCobTablasAuxiliaresList;
	}

	public List getLecCampLanzaNivelListModificacion() {
		return lecCampLanzaNivelListModificacion;
	}

	public void setLecCampLanzaNivelListModificacion(
			List lecCampLanzaNivelListModificacion) {
		this.lecCampLanzaNivelListModificacion = lecCampLanzaNivelListModificacion;
	}

	public List getLecCampLanzaList() {
		return lecCampLanzaList;
	}

	public void setLecCampLanzaList(List lecCampLanzaList) {
		this.lecCampLanzaList = lecCampLanzaList;
	}

	public boolean isShowTipoComi() {
		return showTipoComi;
	}

	public void setShowTipoComi(boolean showTipoComi) {
		this.showTipoComi = showTipoComi;
	}

	public boolean isIndExigCursoCamb() {
		return indExigCursoCamb;
	}

	public void setIndExigCursoCamb(boolean indExigCursoCamb) {
		this.indExigCursoCamb = indExigCursoCamb;
	}

	public boolean isShowExCurso() {
		return showExCurso;
	}

	public void setShowExCurso(boolean showExCurso) {
		this.showExCurso = showExCurso;
	}

	public ReporteLETConfiguracionProgramaAction getReporte() {
		return reporte;
	}

	public void setReporte(ReporteLETConfiguracionProgramaAction reporte) {
		this.reporte = reporte;
	}

	/**
	 * @return the mostrarPrimerMensaje
	 */
	public String getMostrarPrimerMensaje() {
		return mostrarPrimerMensaje;
	}

	/**
	 * @param mostrarPrimerMensaje
	 *            the mostrarPrimerMensaje to set
	 */
	public void setMostrarPrimerMensaje(String mostrarPrimerMensaje) {
		this.mostrarPrimerMensaje = mostrarPrimerMensaje;
	}

	/**
	 * @return the mostrarPrimerMensajeBoolean
	 */
	public Boolean getMostrarPrimerMensajeBoolean() {
		return mostrarPrimerMensajeBoolean;
	}

	/**
	 * @param mostrarPrimerMensajeBoolean
	 *            the mostrarPrimerMensajeBoolean to set
	 */
	public void setMostrarPrimerMensajeBoolean(
			Boolean mostrarPrimerMensajeBoolean) {
		this.mostrarPrimerMensajeBoolean = mostrarPrimerMensajeBoolean;
	}

	public List getLecIndicadorTipoMetaList() {
		return lecIndicadorTipoMetaList;
	}

	public void setLecIndicadorTipoMetaList(List lecIndicadorTipoMetaList) {
		this.lecIndicadorTipoMetaList = lecIndicadorTipoMetaList;
	}

	public String getMetaPedido() {
		return metaPedido;
	}

	public void setMetaPedido(String metaPedido) {
		this.metaPedido = metaPedido;
	}

	public String getMetaVenta() {
		return metaVenta;
	}

	public void setMetaVenta(String metaVenta) {
		this.metaVenta = metaVenta;
	}

	public List getLecTipoBonoList() {
		return lecTipoBonoList;
	}

	public void setLecTipoBonoList(List lecTipoBonoList) {
		this.lecTipoBonoList = lecTipoBonoList;
	}

	public List getLecTipoPremiacionList() {
		return lecTipoPremiacionList;
	}

	public void setLecTipoPremiacionList(List lecTipoPremiacionList) {
		this.lecTipoPremiacionList = lecTipoPremiacionList;
	}

	public boolean isHabilitaMontoPremio() {
		return habilitaMontoPremio;
	}

	public void setHabilitaMontoPremio(boolean habilitaMontoPremio) {
		this.habilitaMontoPremio = habilitaMontoPremio;
	}

	public String getPremioCanasta() {
		return premioCanasta;
	}

	public void setPremioCanasta(String premioCanasta) {
		this.premioCanasta = premioCanasta;
	}

	public Object[] getBeanlecRetencion() {
		return beanlecRetencion;
	}

	public void setBeanlecRetencion(Object[] beanlecRetencion) {
		this.beanlecRetencion = beanlecRetencion;
	}

	public Object[] getBeanlecCambNivelAvan() {
		return beanlecCambNivelAvan;
	}

	public void setBeanlecCambNivelAvan(Object[] beanlecCambNivelAvan) {
		this.beanlecCambNivelAvan = beanlecCambNivelAvan;
	}

	public Object[] getBeanlecPeriodoGracia() {
		return beanlecPeriodoGracia;
	}

	public void setBeanlecPeriodoGracia(Object[] beanlecPeriodoGracia) {
		this.beanlecPeriodoGracia = beanlecPeriodoGracia;
	}

	public Object[] getBeanLecCampLanzaNivelList() {
		return beanLecCampLanzaNivelList;
	}

	public void setBeanLecCampLanzaNivelList(Object[] beanLecCampLanzaNivelList) {
		this.beanLecCampLanzaNivelList = beanLecCampLanzaNivelList;
	}

	public Object[] getBeanlecIncTipoComiList() {
		return beanlecIncTipoComiList;
	}

	public void setBeanlecIncTipoComiList(Object[] beanlecIncTipoComiList) {
		this.beanlecIncTipoComiList = beanlecIncTipoComiList;
	}

	public List getLecTipoCanastaList() {
		return lecTipoCanastaList;
	}

	public void setLecTipoCanastaList(List lecTipoCanastaList) {
		this.lecTipoCanastaList = lecTipoCanastaList;
	}

	public boolean isIndConsiderarNuevas() {
		return indConsiderarNuevas;
	}

	public void setIndConsiderarNuevas(boolean indConsiderarNuevas) {
		this.indConsiderarNuevas = indConsiderarNuevas;
	}

	public String getIdCodigoPrograma() {
		return idCodigoPrograma;
	}

	public void setIdCodigoPrograma(String idCodigoPrograma) {
		this.idCodigoPrograma = idCodigoPrograma;
	}

	public Boolean getIndConsiderarMetasIngresos() {
		return indConsiderarMetasIngresos;
	}

	public void setIndConsiderarMetasIngresos(Boolean indConsiderarMetasIngresos) {
		this.indConsiderarMetasIngresos = indConsiderarMetasIngresos;
	}

	public Boolean getConsiderarIngresoCapi() {
		return considerarIngresoCapi;
	}

	public void setConsiderarIngresoCapi(Boolean considerarIngresoCapi) {
		this.considerarIngresoCapi = considerarIngresoCapi;
	}

	/**
	 * @return the listaEliminaCobranzaTemp
	 */
	public List getListaEliminaCobranzaTemp() {
		return listaEliminaCobranzaTemp;
	}

	/**
	 * @param listaEliminaCobranzaTemp
	 *            the listaEliminaCobranzaTemp to set
	 */
	public void setListaEliminaCobranzaTemp(List listaEliminaCobranzaTemp) {
		this.listaEliminaCobranzaTemp = listaEliminaCobranzaTemp;
	}

	/**
	 * @return the showExitosasDesempenio
	 */
	public boolean isShowExitosasDesempenio() {
		return showExitosasDesempenio;
	}

	/**
	 * @param showExitosasDesempenio
	 *            the showExitosasDesempenio to set
	 */
	public void setShowExitosasDesempenio(boolean showExitosasDesempenio) {
		this.showExitosasDesempenio = showExitosasDesempenio;
	}

	/**
	 * @return the lecNivelRangoIncenList
	 */
	public List getLecNivelRangoIncenList() {
		return lecNivelRangoIncenList;
	}

	/**
	 * @param lecNivelRangoIncenList
	 *            the lecNivelRangoIncenList to set
	 */
	public void setLecNivelRangoIncenList(List lecNivelRangoIncenList) {
		this.lecNivelRangoIncenList = lecNivelRangoIncenList;
	}

	/**
	 * @return the codigoPeriodoValidarIncentivo
	 */
	public String getCodigoPeriodoValidarIncentivo() {
		return codigoPeriodoValidarIncentivo;
	}

	/**
	 * @param codigoPeriodoValidarIncentivo the codigoPeriodoValidarIncentivo to set
	 */
	public void setCodigoPeriodoValidarIncentivo(
			String codigoPeriodoValidarIncentivo) {
		this.codigoPeriodoValidarIncentivo = codigoPeriodoValidarIncentivo;
	}
	
	

}
