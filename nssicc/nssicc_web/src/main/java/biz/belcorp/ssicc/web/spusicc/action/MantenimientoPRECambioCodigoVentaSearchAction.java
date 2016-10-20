package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPRECambioCodigoVentaSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoPRECambioCodigoVentaSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -3745381541348661985L;
	private String codigoPais;
	private List siccMarcaList;
	private List siccCatalogoList;
	private List siccTipoOfertaList;
	private List preEstrategiaList;
	private List listaCUV;
	private String numSecUsuario;
	private String periodoActivo;
	private List preCuvList;
	private List preCodigoVentaList;
	private String oidDetaOferta;
	private String oidOferta;
	private String oidCatalogo;
	private String modificaCUV;
	private boolean indicadorCodigoVenta;
	private boolean indicadorNumeroPagina;
	private boolean indicadorPrecioCatalogo;
	private boolean indicadorPrecioContable;
	private boolean indicadorFactorRepeticion;
	private DataTableModel dataTableModalMod;
	private List preStatusList;
	
	private String valorTipoEstrategia;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoPRECambioCodigoVentaList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPRECambioCodigoVentaSearchForm form = new MantenimientoPRECambioCodigoVentaSearchForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		return buscar(f);
	}

	public void cargarPagina() {
		MantenimientoPRECambioCodigoVentaSearchForm form = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		buscar(form);
	}

	/**
	 * Metodo para buscar general
	 * 
	 * @param f
	 * @return
	 */
	public List buscar(MantenimientoPRECambioCodigoVentaSearchForm f) {
		Map criteria = new HashMap();
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
		criteria.put("codigoMarcaProducto", f.getCodigoMarcaProducto());
		criteria.put("cuv", f.getCUV());
		criteria.put("codigoTipoOferta", f.getCodigoTipoOferta());
		criteria.put("codigoCatalogo", f.getCodigoCatalogo());
		criteria.put("numeroPagina", f.getNumeroPagina());
		criteria.put("numSecUsuario", this.numSecUsuario);
		criteria.put("oidEstrategia", f.getCodigoEstrategia());

		List resultado = (List) service.getCodigoVentaList(criteria);
		this.preCuvList = resultado;
		this.listaCUV = resultado;

		if (modificaCUV.equals(Constants.NUMERO_UNO)) {
			this.preCodigoVentaList = new ArrayList();
			List resultadoMod = (List) service.getCodigoVentaModificarList(criteria);
			this.preCodigoVentaList = resultadoMod;
		}
		
		this.modificaCUV = Constants.NUMERO_CERO;
		this.datatableBusqueda = new DataTableModel(resultado);
		
		this.valorTipoEstrategia = f.getCodigoEstrategia();
		
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoIntefaz = this.parametrosPantalla.get("codigoInterfaz");
		this.codigoPais = pais.getCodigo();
		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		f.setCodigoIntefaz(codigoIntefaz);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		f.setCodigoPais(this.codigoPais);
		f.setCodigoPeriodo(interfazSiCCService.getPeriodoDefaultByPaisCanal(
				this.codigoPais, Constants.CODIGO_CANAL_DEFAULT));

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codigoPais);
		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				null);

		f.setCUV("");
		this.siccTipoOfertaList = service.getTipoOfertaList(criteriaOperacion);
		this.siccCatalogoList = reporteService.getListaGenerico(
				"getCatalogoProductos", null);

		f.setNumeroPagina("");
		f.setCodigoCatalogo("");
		f.setCodigoMarcaProducto("");
		f.setCodigoTipoOferta("");
		f.setCodigoEstrategia("");

		this.preEstrategiaList = service.getEstrategiaList(criteriaOperacion);

		this.periodoActivo = service.getPeriodoActivo(criteriaOperacion);
		f.setCodigoPeriodoActivo(this.periodoActivo);
		this.preCodigoVentaList = new ArrayList();
		this.preCuvList = new ArrayList();
		modificaCUV = Constants.NUMERO_CERO;

		Map criteria = new HashMap();

		// Se obtiene el numero de secuencia de la sesion para asignarlo al
		// usuario
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteria.put("usuario", usuario.getLogin());
		this.numSecUsuario = service.getNumeroSecuenciaUsuario();
		criteria.put("numSecUsuario", this.numSecUsuario);
		service.deleteCUVTemporal(criteria);

		this.mostrarBotonSave = false;
		this.mostrarBotonSalir = false;

	}

	public void iniciarAgregarCUV() {
		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		
		try {
			if (this.preCuvList.size() == 0)
				throw new Exception(this.getResourceMessage("errors.sin.registros"));
			
			this.insertaCUVTemporal(f, this.beanRegistroSeleccionado);

//			Map criteria = new HashMap();
//			criteria.put("numSecUsuario", this.numSecUsuario);
//
//			List resultadoMod = (List) service.getCodigoVentaModificarList(criteria);
//			this.preCodigoVentaList = resultadoMod;
//			this.dataTableModalMod = new DataTableModel(resultadoMod);
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	public void agregarCUV(ActionEvent event) {
		try {
			this.iniciarAgregarCUV();
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo para limpiar
	 * 
	 * @param event
	 */
	public void limpiar(ActionEvent event) {

		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		Map criteria = new HashMap();
		criteria.put("numSecUsuario", this.numSecUsuario);
		service.deleteCUVTemporal(criteria);
		this.preCodigoVentaList = new ArrayList();
		this.dataTableModalMod = new DataTableModel(this.preCodigoVentaList);
	}

	/**
	 * Metodo que elimina una oferta seleccionada de la lista
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void eliminarOferta(ActionEvent event) {

		try {
			MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
			MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

			Map criteria = new HashMap();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			criteria.put("codigoPais", this.codigoPais);
			criteria.put("numSecUsuario", this.numSecUsuario);
			criteria.put("codigoUsuario", usuario.getLogin());
			String periodo = f.getCodigoPeriodo();
			// Solo se permite si el periodo seleccionado es diferente al
			// periodo activo
			if (periodo.equals(this.periodoActivo)) {
				throw new Exception(
						this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.eliminarcuv.periodos.iguales"));
			} else {
				String cuvFact = service.getExisteOfertasFacturados(criteria);

				if (Constants.NUMERO_CERO.equals(cuvFact)) {
					service.executeEliminarOferta(criteria);

					modificaCUV = Constants.NUMERO_UNO;
					buscar(f);
					throw new Exception(
							this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.deleted.oferta"));
				} else {
					throw new Exception(
							this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.elimina.oferta"));
				}
			}

		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo que inserta la lista en la tabla temporal
	 * 
	 * @param f
	 */
	public void insertaCUVTemporal(MantenimientoPRECambioCodigoVentaSearchForm f, Object beanRegistroSeleccionado) {
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaRegSel = (Map) beanRegistroSeleccionado;
		f.setCodigoTipoOferta(MapUtils.getString(criteriaRegSel, "codTipoOferta"));
		f.setCodigoCatalogo(MapUtils.getString(criteriaRegSel, "codCatalogo"));
		f.setNumeroPagina(MapUtils.getString(criteriaRegSel, "numPagina"));
		
		List listEstrategias = service.getEstrategiaList(criteriaRegSel);
		String oidEstrategia = "";
		for (int i = 0; i < listEstrategias.size(); i++) {
			Base base = (Base) listEstrategias.get(i);
			
			if(StringUtils.equals((String) criteriaRegSel.get("desEstrategia"), base.getDescripcion())){
				oidEstrategia = base.getCodigo();
				break;
			}
		}
		f.setCodigoEstrategia(oidEstrategia);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
		criteria.put("codigoMarcaProducto", f.getCodigoMarcaProducto());
		criteria.put("cuv", f.getCUV());
		criteria.put("codigoTipoOferta", f.getCodigoTipoOferta());
		criteria.put("codigoCatalogo", f.getCodigoCatalogo());
		criteria.put("numeroPagina", f.getNumeroPagina());
		criteria.put("numSecUsuario", this.numSecUsuario);
		criteria.put("oidEstrategia", f.getCodigoEstrategia());

		service.insertaCUVTemporal(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		if (accion.equals("LIMPIAR")) {
			if (preCodigoVentaList.size() < 3)
				return this
						.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.existe.ofertas");

		} else if (accion.equals("ELIMINAR"))
			if (preCodigoVentaList.size() < 3)
				return this
						.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.existe.ofertas");
		return null;
	}

	/**
	 * Modificando el codigo de venta
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void modificarCUV(ActionEvent event) {
		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaModificaCUVAction action = findManageBean("mantenimientoPRECambioCodigoVentaModificaCUVAction");
		try {
			if (this.preCuvList.size() == 0)
				throw new Exception(this.getResourceMessage("errors.sin.registros"));
			if (beanRegistroSeleccionado == null)
				throw new Exception(this.getResourceMessage("errors.select.item"));

			Map data = (Map) this.beanRegistroSeleccionado;
			data.put("codigoPeriodo", f.getCodigoPeriodo());
			action.setPeriodoActivo(this.periodoActivo);
			action.setNumSecUsuario(this.numSecUsuario);
			action.setData(data);
			action.setFormCodigoVenta(f);
			action.inicializandoValores();
			
			if(StringUtils.equals(MapUtils.getString(data, "oidTipoEstrategia"), Constants.NUMERO_DOS)){
				this.redireccionarPagina("mantenimientoPRECambioCodigoVentaModificaCUVCompuestaFijaForm");
			}else{
				this.redireccionarPagina("mantenimientoPRECambioCodigoVentaModificaCUVForm");
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Modificando datos de ofertas
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void modificarDatosOferta(ActionEvent event) {
		InterfazPRECambioCodigoVentaModificaOfertaAction action = findManageBean("interfazPRECambioCodigoVentaModificaOfertaAction");
		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		
		try {
//			if (this.preCodigoVentaList.toString().length() < 3)
			if (this.preCuvList.size() == 0)
				throw new Exception(this.getResourceMessage("errors.sin.registros"));
			if (beanRegistroSeleccionado == null)
				throw new Exception(this.getResourceMessage("errors.select.item"));
			
			this.iniciarAgregarCUV();
			
			if (this.preCuvList.toString().length() < 3)
				throw new Exception(this.getResourceMessage("mantenimientoPRECambioCodigoVentaSearchForm.existe.ofertas"));
			
			action.setCodigoInterfaz(f.getCodigoIntefaz());
			action.setNumSecUsuario(this.numSecUsuario);
			
			Map criteria = (Map) beanRegistroSeleccionado;
			action.setOidOferta(MapUtils.getString(criteria, "oidOferta"));
			
			action.inicializando();
			this.redireccionarPagina("interfazPRECambioCodigoVentaModificaOfertaForm");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {
		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		
		String mensaje = "";
		
		if(f.getCodigoPeriodo().trim().length() < 6){
			mensaje = "Campaña no válida";
		}
		
		return mensaje;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCatalogoList
	 */
	public List getSiccCatalogoList() {
		return siccCatalogoList;
	}

	/**
	 * @param siccCatalogoList
	 *            the siccCatalogoList to set
	 */
	public void setSiccCatalogoList(List siccCatalogoList) {
		this.siccCatalogoList = siccCatalogoList;
	}

	/**
	 * @return the siccTipoOfertaList
	 */
	public List getSiccTipoOfertaList() {
		return siccTipoOfertaList;
	}

	/**
	 * @param siccTipoOfertaList
	 *            the siccTipoOfertaList to set
	 */
	public void setSiccTipoOfertaList(List siccTipoOfertaList) {
		this.siccTipoOfertaList = siccTipoOfertaList;
	}

	/**
	 * @return the preCodigoVentaList
	 */
	public List getPreCodigoVentaList() {
		return preCodigoVentaList;
	}

	/**
	 * @param preCodigoVentaList
	 *            the preCodigoVentaList to set
	 */
	public void setPreCodigoVentaList(List preCodigoVentaList) {
		this.preCodigoVentaList = preCodigoVentaList;
	}

	/**
	 * @return the preEstrategiaList
	 */
	public List getPreEstrategiaList() {
		return preEstrategiaList;
	}

	/**
	 * @param preEstrategiaList
	 *            the preEstrategiaList to set
	 */
	public void setPreEstrategiaList(List preEstrategiaList) {
		this.preEstrategiaList = preEstrategiaList;
	}

	/**
	 * @return the listaCUV
	 */
	public List getListaCUV() {
		return listaCUV;
	}

	/**
	 * @param listaCUV
	 *            the listaCUV to set
	 */
	public void setListaCUV(List listaCUV) {
		this.listaCUV = listaCUV;
	}

	/**
	 * @return the numSecUsuario
	 */
	public String getNumSecUsuario() {
		return numSecUsuario;
	}

	/**
	 * @param numSecUsuario
	 *            the numSecUsuario to set
	 */
	public void setNumSecUsuario(String numSecUsuario) {
		this.numSecUsuario = numSecUsuario;
	}

	/**
	 * @return the periodoActivo
	 */
	public String getPeriodoActivo() {
		return periodoActivo;
	}

	/**
	 * @param periodoActivo
	 *            the periodoActivo to set
	 */
	public void setPeriodoActivo(String periodoActivo) {
		this.periodoActivo = periodoActivo;
	}

	/**
	 * @return the preCuvList
	 */
	public List getPreCuvList() {
		return preCuvList;
	}

	/**
	 * @param preCuvList
	 *            the preCuvList to set
	 */
	public void setPreCuvList(List preCuvList) {
		this.preCuvList = preCuvList;
	}

	/**
	 * @return the oidDetaOferta
	 */
	public String getOidDetaOferta() {
		return oidDetaOferta;
	}

	/**
	 * @param oidDetaOferta
	 *            the oidDetaOferta to set
	 */
	public void setOidDetaOferta(String oidDetaOferta) {
		this.oidDetaOferta = oidDetaOferta;
	}

	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta
	 *            the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}

	/**
	 * @param oidCatalogo
	 *            the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}

	/**
	 * @return the indicadorCodigoVenta
	 */
	public boolean isIndicadorCodigoVenta() {
		return indicadorCodigoVenta;
	}

	/**
	 * @param indicadorCodigoVenta
	 *            the indicadorCodigoVenta to set
	 */
	public void setIndicadorCodigoVenta(boolean indicadorCodigoVenta) {
		this.indicadorCodigoVenta = indicadorCodigoVenta;
	}

	/**
	 * @return the indicadorNumeroPagina
	 */
	public boolean isIndicadorNumeroPagina() {
		return indicadorNumeroPagina;
	}

	/**
	 * @param indicadorNumeroPagina
	 *            the indicadorNumeroPagina to set
	 */
	public void setIndicadorNumeroPagina(boolean indicadorNumeroPagina) {
		this.indicadorNumeroPagina = indicadorNumeroPagina;
	}

	/**
	 * @return the indicadorPrecioCatalogo
	 */
	public boolean isIndicadorPrecioCatalogo() {
		return indicadorPrecioCatalogo;
	}

	/**
	 * @param indicadorPrecioCatalogo
	 *            the indicadorPrecioCatalogo to set
	 */
	public void setIndicadorPrecioCatalogo(boolean indicadorPrecioCatalogo) {
		this.indicadorPrecioCatalogo = indicadorPrecioCatalogo;
	}

	/**
	 * @return the indicadorPrecioContable
	 */
	public boolean isIndicadorPrecioContable() {
		return indicadorPrecioContable;
	}

	/**
	 * @param indicadorPrecioContable
	 *            the indicadorPrecioContable to set
	 */
	public void setIndicadorPrecioContable(boolean indicadorPrecioContable) {
		this.indicadorPrecioContable = indicadorPrecioContable;
	}

	/**
	 * @return the indicadorFactorRepeticion
	 */
	public boolean isIndicadorFactorRepeticion() {
		return indicadorFactorRepeticion;
	}

	/**
	 * @param indicadorFactorRepeticion
	 *            the indicadorFactorRepeticion to set
	 */
	public void setIndicadorFactorRepeticion(boolean indicadorFactorRepeticion) {
		this.indicadorFactorRepeticion = indicadorFactorRepeticion;
	}

	/**
	 * @return the modificaCUV
	 */
	public String getModificaCUV() {
		return modificaCUV;
	}

	/**
	 * @param modificaCUV
	 *            the modificaCUV to set
	 */
	public void setModificaCUV(String modificaCUV) {
		this.modificaCUV = modificaCUV;
	}

	/**
	 * @return the dataTableModalMod
	 */
	public DataTableModel getDataTableModalMod() {
		return dataTableModalMod;
	}

	/**
	 * @param dataTableModalMod
	 *            the dataTableModalMod to set
	 */
	public void setDataTableModalMod(DataTableModel dataTableModalMod) {
		this.dataTableModalMod = dataTableModalMod;
	}

	/**
	 * @return the valorTipoEstrategia
	 */
	public String getValorTipoEstrategia() {
		return valorTipoEstrategia;
	}

	/**
	 * @param valorTipoEstrategia the valorTipoEstrategia to set
	 */
	public void setValorTipoEstrategia(String valorTipoEstrategia) {
		this.valorTipoEstrategia = valorTipoEstrategia;
	}
	
}
