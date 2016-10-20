package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargosyAbonos;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDiferenciaPreciosService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDiferenciaPreciosSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCCCDiferenciaPreciosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private String tipoSolicitudCargos;
	private String tipoSolicitudAbonos;
	private List cccCargosAbonosList;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccClienteList;
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private Boolean mostrarGrilla;
	private boolean mostrarPopupConsultora;
	private String attachment = "";

	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

	@SuppressWarnings("static-access")
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction
						.getBeanRegistroSeleccionado();
				MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
	}

	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.mostrarPopupConsultora = true;
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             //
	 */
	public void procesar() throws Exception {

		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;
			String codigoUsuario = usuario.getLogin();
			Map criteria = new HashMap();

			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoVenta", f.getCodigoVenta());
			criteria.put("precioCorrecto", f.getPrecioCorrecto());
			criteria.put("tipoSolicitudCargos", tipoSolicitudCargos);
			criteria.put("tipoSolicitudAbonos", tipoSolicitudAbonos);
			criteria.put("codigoUsuario", codigoUsuario);

			MantenimientoCCCDiferenciaPreciosService service = (MantenimientoCCCDiferenciaPreciosService) getBean("spusicc.mantenimientoCCCDiferenciaPreciosService");
			List detalle = cccCargosAbonosList;
			service.executeProcesoCargosAbonos(detalle, criteria);
			String ejecutoProceso = (String) criteria.get("ejecutoProceso");
			String mensaje = null;
			if (StringUtils.equals(ejecutoProceso, Constants.NO)) {

				mensaje = this
						.getResourceMessage("mantenimientoCCCDiferenciaPreciosSearchForm.proceso.ejecutado");
				this.addError("Error : ", mensaje);
			} else {
				mensaje = this
						.getResourceMessage("mantenimientoCCCDiferenciaPreciosSearchForm.execute");
				this.addInfo("Info : ", mensaje);

			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivo();
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Carga el archivo de Clientes
	 * 
	 */
	public void uploadArchivo() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		List listaClientes = new ArrayList();
		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(),
					longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,
					service.getCodigoConsultora(criteria));
			listaClientes.add(bean);

			if (bean.getValue() == null)
				cont++;

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");

		if (cont != 0) {
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");
			f.setErrados(cont);
		}
		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);
		this.siccClienteList = list;
		f.setCodigoConsultora("");
		this.cccCargosAbonosList = new ArrayList();
	}

	/**
	 * Carga Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas");
		try {
			MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;
			String valor = val.getNewValue().toString();
			String[] regiones = { valor };
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			if (regiones.length > 0) {
				LabelValue[] result = ajax
						.getZonasMultipleByPaisMarcaCanalRegion(
								f.getCodigoPais(), "T", "VD", regiones, "");
				this.setSiccZonaList(result);
			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	public void validarCodigoVenta() {
		try {
			MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;

			String codigoVenta = f.getCodigoVenta();
			String codigoPeriodo = f.getCodigoPeriodo();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValueCUV valor = ajax.getCodigoVentaPrecio(codigoVenta,
					codigoPeriodo);
			String mensaje = null;

			if (codigoPeriodo == null || StringUtils.isBlank(codigoPeriodo)) {
				mensaje = "Debe de ingresar el codigo de periodo para validar el codigo de venta";
				this.addError("Error : ", mensaje);
				return;
			}
			if (valor != null) {
				this.mostrarGrilla = true;
				f.setCodigoSAP(valor.getLabel());
				f.setDescripcionSAP(valor.getValue());
				f.setPrecioUnitario(valor.getPrecio());
				f.setTipoOferta(valor.getOferta());
				return;
			} else {
				mensaje = this.getResourceMessage("mensaje.codVtaNoExisteCDR");
				this.addError("Error : ", mensaje);
				return;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	// public void consultar(ValueChangeEvent val){
	// MantenimientoSTOBeneficioDeudaSearchForm search =
	// (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;
	// String stipos = val.getNewValue().toString();
	// String nombre = ajax.getNombreCliente(stipos);
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCDiferenciaPreciosSearchForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCCCDiferenciaPreciosForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCDiferenciaPreciosSearchForm form = new MantenimientoCCCDiferenciaPreciosSearchForm();
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
		MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;
		MantenimientoCCCDiferenciaPreciosService service = (MantenimientoCCCDiferenciaPreciosService) getBean("spusicc.mantenimientoCCCDiferenciaPreciosService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoVenta", f.getCodigoVenta());
		criteria.put("precioCorrecto", f.getPrecioCorrecto());
		criteria.put("oidPeriodo", Integer.valueOf(reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria)));

		if (!StringUtils.isEmpty(f.getCodigoRegion())) {
			criteria.put("codigoRegion", f.getCodigoRegion());
		}

		if (!StringUtils.isEmpty(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
		}

		if (!StringUtils.isEmpty(f.getCodigoConsultora())) {
			criteria.put("codigoConsultora", f.getCodigoConsultora());
		}

		List listaClientes = siccClienteList;
		Map map = new HashMap();
		String listaConsultoras = "";

		if (listaClientes != null && listaClientes.size() > 0) {
			log.debug("Lista de Clientes Size : " + listaClientes.size());
			for (int i = 0; i < listaClientes.size(); i++) {
				map = (Map) listaClientes.get(i);
				listaConsultoras = listaConsultoras
						+ (String) map.get("codigoCliente")
						+ ((i != (listaClientes.size() - 1)) ? "," : "");
			}
			criteria.put("codigoConsultora", listaConsultoras);
		}

		service.executeGenerarDataCargosAbonos(criteria);

		List resultado = (List) service.getCargosAbonosList(criteria);
		this.cccCargosAbonosList = resultado;

		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		CargosyAbonos bean = (CargosyAbonos) this.beanRegistroSeleccionado;
		bean.getCodigoCliente();
		String[] seleccionados = new String[1];
		seleccionados[0] = bean.getCodigoVenta();
		List detalle = cccCargosAbonosList;

		for (int i = 0; i < seleccionados.length; i++) {
			detalle.remove(Integer.parseInt(seleccionados[i]) - 1);
		}
		this.cccCargosAbonosList = detalle;
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarGrilla = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCCCDiferenciaPreciosSearchForm f = (MantenimientoCCCDiferenciaPreciosSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		// tipoSolicitudCargos = request.getParameter("tipoSolicitudCargos");
		// tipoSolicitudAbonos = request.getParameter("tipoSolicitudAbonos");
		f.setCodigoPeriodo("");
		f.setCodigoSAP("");
		f.setCodigoVenta("");
		f.setPrecioCorrecto("");
		f.setDescripcionSAP("");
		f.setPrecioUnitario("");
		f.setTipoOferta("");
		f.setCodigoRegion("");
		f.setCodigoZona("");
		f.setCodigoConsultora("");
		this.cccCargosAbonosList = new ArrayList();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
	}

	/**
	 * @return the tipoSolicitudCargos
	 */
	public String getTipoSolicitudCargos() {
		return tipoSolicitudCargos;
	}

	/**
	 * @param tipoSolicitudCargos
	 *            the tipoSolicitudCargos to set
	 */
	public void setTipoSolicitudCargos(String tipoSolicitudCargos) {
		this.tipoSolicitudCargos = tipoSolicitudCargos;
	}

	/**
	 * @return the tipoSolicitudAbonos
	 */
	public String getTipoSolicitudAbonos() {
		return tipoSolicitudAbonos;
	}

	/**
	 * @param tipoSolicitudAbonos
	 *            the tipoSolicitudAbonos to set
	 */
	public void setTipoSolicitudAbonos(String tipoSolicitudAbonos) {
		this.tipoSolicitudAbonos = tipoSolicitudAbonos;
	}

	/**
	 * @return the cccCargosAbonosList
	 */
	public List getCccCargosAbonosList() {
		return cccCargosAbonosList;
	}

	/**
	 * @param cccCargosAbonosList
	 *            the cccCargosAbonosList to set
	 */
	public void setCccCargosAbonosList(List cccCargosAbonosList) {
		this.cccCargosAbonosList = cccCargosAbonosList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccClienteList
	 */
	public List getSiccClienteList() {
		return siccClienteList;
	}

	/**
	 * @param siccClienteList
	 *            the siccClienteList to set
	 */
	public void setSiccClienteList(List siccClienteList) {
		this.siccClienteList = siccClienteList;
	}

	/**
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 *            the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora
	 *            the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction
	 *            the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}