package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPlantillasBusquedaForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPlantillasForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPlantillasSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPlantillasTipoSolicitudForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoOCRPlantillaSearchAction extends
		BaseMantenimientoSearchAbstractAction {
	private List ocrCodigoPlantillaList = new ArrayList();
	private List ocrPlantillaList = new ArrayList();
	private List siccRegionList = new ArrayList();
	private boolean seleccionable = false;

	// Dialogs
	private List openDocumentoPopupList = new ArrayList();
	private List ocrPlantillaTipoSolicitud = new ArrayList();
	// Form Dialogs
	private MantenimientoOCRPlantillasForm formPlantillas;
	private MantenimientoOCRPlantillasTipoSolicitudForm formTiposSolicitud;
	private MantenimientoOCRPlantillasBusquedaForm formPlantillasBusqueda;
	private String codigoPlantillaT = "";
	private String descripcionPlantillaT = "";
	private boolean flagBorrado;
	
	

	public boolean isFlagBorrado() {
		return flagBorrado;
	}

	public void setFlagBorrado(boolean flagBorrado) {
		this.flagBorrado = flagBorrado;
	}

	public boolean isSeleccionable() {
		return seleccionable;
	}

	public void setSeleccionable(boolean seleccionable) {
		this.seleccionable = seleccionable;
	}

	public String getCodigoPlantillaT() {
		return codigoPlantillaT;
	}

	public void setCodigoPlantillaT(String codigoPlantillaT) {
		this.codigoPlantillaT = codigoPlantillaT;
	}

	public String getDescripcionPlantillaT() {
		return descripcionPlantillaT;
	}

	public void setDescripcionPlantillaT(String descripcionPlantillaT) {
		this.descripcionPlantillaT = descripcionPlantillaT;
	}

	public List getOcrPlantillaTipoSolicitud() {
		return ocrPlantillaTipoSolicitud;
	}

	public void setOcrPlantillaTipoSolicitud(List ocrPlantillaTipoSolicitud) {
		this.ocrPlantillaTipoSolicitud = ocrPlantillaTipoSolicitud;
	}

	public MantenimientoOCRPlantillasForm getFormPlantillas() {
		return formPlantillas;
	}

	public void setFormPlantillas(MantenimientoOCRPlantillasForm formPlantillas) {
		this.formPlantillas = formPlantillas;
	}

	public MantenimientoOCRPlantillasTipoSolicitudForm getFormTiposSolicitud() {
		return formTiposSolicitud;
	}

	public void setFormTiposSolicitud(
			MantenimientoOCRPlantillasTipoSolicitudForm formTiposSolicitud) {
		this.formTiposSolicitud = formTiposSolicitud;
	}

	public MantenimientoOCRPlantillasBusquedaForm getFormPlantillasBusqueda() {
		return formPlantillasBusqueda;
	}

	public void setFormPlantillasBusqueda(
			MantenimientoOCRPlantillasBusquedaForm formPlantillasBusqueda) {
		this.formPlantillasBusqueda = formPlantillasBusqueda;
	}

	public List getOpenDocumentoPopupList() {
		return openDocumentoPopupList;
	}

	public void setOpenDocumentoPopupList(List openDocumentoPopupList) {
		this.openDocumentoPopupList = openDocumentoPopupList;
	}

	public List getOcrCodigoPlantillaList() {
		return ocrCodigoPlantillaList;
	}

	public void setOcrCodigoPlantillaList(List ocrCodigoPlantillaList) {
		this.ocrCodigoPlantillaList = ocrCodigoPlantillaList;
	}

	public List getOcrPlantillaList() {
		return ocrPlantillaList;
	}

	public void setOcrPlantillaList(List ocrPlantillaList) {
		this.ocrPlantillaList = ocrPlantillaList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6076347816040636070L;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoOCRPlantillasSearchForm f = new MantenimientoOCRPlantillasSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoOCRPlantillasSearchForm f = (MantenimientoOCRPlantillasSearchForm) formBusqueda;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("codigoPlantilla", f.getCodigoPlantilla());
		criteria.put("grupoProceso", f.getGrupoProceso());
		if (f.getCodigoRegion().compareToIgnoreCase("TODOS") == 0) {
			criteria.put("codigoRegion", "");
		} else
			criteria.put("codigoRegion", f.getCodigoRegion());

		List resultado = (List) service.getListaPlantillas(criteria);
		ocrPlantillaList.clear();
		ocrPlantillaList = resultado;

		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return true;
	}

	public void deleteAttributes(ActionEvent e) {
		this.flagBorrado=true;
		MantenimientoOCRPlantillasSearchForm f = (MantenimientoOCRPlantillasSearchForm) formBusqueda;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		
		if (f.getGrupoProceso() == null || f.getGrupoProceso().equals("")) {
			addInfo("Mensaje", "Debe de ingresar el grupo de proceso");
			return;
		}
		if (f.getTipoSolicitud() == null || f.getTipoSolicitud().equals("")) {
			addInfo("Mensaje", "Debe de ingresar el Tipo de Solicitud");
			return;
		}
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("codigoPlantilla", f.getCodigoPlantilla());
		criteria.put("grupoProceso", f.getGrupoProceso());
		criteria.put("tipoSolicitud", f.getTipoSolicitud());

		if (f.getTipoSolicitud() != null || f.getExclusionSolicitud().compareToIgnoreCase("") != 0) {
			criteria.put("exclusionSolicitud", f.getExclusionSolicitud());
		} else
			criteria.put("exclusionSolicitud", " ");

		if (f.getCodigoRegion()!=null ) {
			criteria.put("codigoRegion", f.getCodigoRegion());
		} else
			criteria.put("codigoRegion", " ");

		if (flagBorrado)
			criteria.put("indicadorBorrado", "1");
		else
			criteria.put("indicadorBorrado", "0");

		service.executeRegeneraPlantillas(criteria);
		addInfo("Mensaje",
				getResourceMessage("mantenimientoOCRPlantillasForm.execute"));

	}

	public void abrirPopupOpenDocumentoPopupBuscar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}
		try {
			this.getRequestContext().execute(
					"PF('popUpBuscarDocumento').show()");

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	public void openDocumentoPopupBuscar(ActionEvent e) {

		MantenimientoOCRPlantillasSearchForm f = (MantenimientoOCRPlantillasSearchForm) formBusqueda;

		if (f.getGrupoProceso() == null || f.getGrupoProceso().equals("")) {
			addInfo("Mensaje", "Debe de ingresar el grupo de proceso");
			return;
		}
		String id = f.getCodigoPlantilla() + '|' + f.getGrupoProceso() + '|'
				+ f.getCodigoRegion();

		openDocumentoPopupBuscarMetodoView(id);
	}

	private void openDocumentoPopupBuscarMetodoView(String id) {

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		formPlantillasBusqueda = new MantenimientoOCRPlantillasBusquedaForm();
		formPlantillasBusqueda.setCodigoPais(pais.getCodigo());

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		log.debug("id" + id);

		int indice = id.indexOf("|");
		String temporal = id.substring(indice + 1, id.length());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPlantilla", StringUtils.substringBefore(id, "|"));

		criteria.put("grupoProceso", StringUtils.substringBefore(temporal, "|"));
		if (StringUtils.substringAfter(temporal, "|").compareToIgnoreCase("") == 0) {
			criteria.put("codigoRegion", "");
		} else
			criteria.put("codigoRegion",
					StringUtils.substringAfter(temporal, "|"));

		List resultado = (List) service.getListaPlantillas(criteria);
		ocrPlantillaList.clear();
		ocrPlantillaList = resultado;

		abrirPopupOpenDocumentoPopupBuscar();

	}

	public void openDocumentoPopupBuscarSolicitud(ActionEvent e) {
		MantenimientoOCRPlantillasSearchForm f = (MantenimientoOCRPlantillasSearchForm) formBusqueda;

		if (f.getTipoSolicitud() == null || f.getTipoSolicitud().equals("")) {
			addInfo("Mensaje", "Debe de ingresar el tipo de solicitud");
			return;
		}
		String id = f.getTipoSolicitud() + '|' + f.getExclusionSolicitud();
		openDocumentoPopupBuscarSolicitudMetodoView(id);
	}

	private void openDocumentoPopupBuscarSolicitudMetodoView(String id) {

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPlantillasTipoSolicitudForm f = new MantenimientoOCRPlantillasTipoSolicitudForm();
		f.setCodigoPais(pais.getCodigo());

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		log.debug("id" + id);

		Map criteria = new HashMap();
		criteria.put("tipoSolicitud", StringUtils.substringBefore(id, "|"));
		if (StringUtils.substringAfter(id, "|").compareToIgnoreCase("") == 0)
			criteria.put("exclusionSolicitud", "T");
		else
			criteria.put("exclusionSolicitud",
					StringUtils.substringAfter(id, "|"));

		List resultado = (List) service.getListaSolicitudes(criteria);

		ocrPlantillaTipoSolicitud.clear();
		ocrPlantillaTipoSolicitud = resultado;
		formTiposSolicitud = f;
		abrirPopupPlantillasTipoSolicitud();

	}

	public void openDialogPopupNuevo(ActionEvent e) {
		this.getRequestContext()
				.execute(
						"PF('confirmDialogInsertarPopup_confirmationDialogConfirmar').show()");
	}
	
	public void openDialogPopupEliminar(ActionEvent e) {
		MantenimientoOCRPlantillasSearchForm f = (MantenimientoOCRPlantillasSearchForm) formBusqueda;
		if (f.getGrupoProceso() == null || f.getGrupoProceso().equals("")) {
			addInfo("Mensaje", "Debe de ingresar el grupo de proceso");
			return;
		}
		if (f.getTipoSolicitud() == null || f.getTipoSolicitud().equals("")) {
			addInfo("Mensaje", "Debe de ingresar el Tipo de Solicitud");
			return;
		}
		this.getRequestContext()
				.execute(
						"PF('eliminarRegistros_confirmationDialogConfirmar').show()");
	}

	public void openDocumentoPopupNuevo(ActionEvent e) {
		try {
			String id = mPantallaPrincipalBean.getCurrentCountry().getCodigo();
			openDocumentoPopupNuevoMetodoView(id);
		} catch (Exception ex) {
			this.addError("Error: ", this.obtieneMensajeErrorException(ex));
		}
	}

	private void openDocumentoPopupNuevoMetodoView(String id) {

		log.debug("id" + id);

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		formPlantillas = new MantenimientoOCRPlantillasForm();

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		/* Seteamos el Formulario */
		formPlantillas.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		formPlantillas.setOidPais(reporteService.getOidString(
				"getOidPaisByCodigoPais", criteriaOperacion));

		abrirPopupOpenDocumentoPopupNuevo();
	}

	public void saveDocumentoPopupNuevoMetodoView(ActionEvent e) {

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setSaveAttributes' method");
			}
			MantenimientoOCRPlantillasSearchForm fp = (MantenimientoOCRPlantillasSearchForm) formBusqueda;
			MantenimientoOCRPlantillasForm f = (MantenimientoOCRPlantillasForm) formPlantillas;
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", mPantallaPrincipalBean
					.getCurrentCountry().getCodigo());
			String oidPais=reporteService.getOidString("getOidPaisByCodigoPais",
					criteriaOperacion);
			Map criteria = new HashMap();
			criteria.put("codigoPais", mPantallaPrincipalBean
					.getCurrentCountry().getCodigo());
			criteria.put("oidPais",oidPais);
			criteria.put("codigoPlantilla", codigoPlantillaT);
			criteria.put("descripcionPlantilla", descripcionPlantillaT);

			service.insertPlantillas(criteria);
			ocrCodigoPlantillaList.clear();

			List listaCodigoPlantilla = service
					.getListaCodigoPlantilla(criteria);
			criteria.put("oidPais", reporteService.getOidString(
					"getOidPaisByCodigoPais", criteria));
			ocrCodigoPlantillaList = listaCodigoPlantilla;

			this.getRequestContext()
					.execute("alert('Grabado Correctamente'); window.close();");
		} catch (Exception ex) {
			this.addError("Error: ", this.obtieneMensajeErrorException(ex));
		}

	}

	private void abrirPopupOpenDocumentoPopupNuevo() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}
		try {
			this.getRequestContext()
					.execute("PF('documentoPopupNuevo').show()");

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	private void abrirPopupPlantillasTipoSolicitud() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}
		try {
			this.getRequestContext().execute(
					"PF('documentoPopupBuscarSolicitud').show()");

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonBuscar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPlantillasSearchForm f = (MantenimientoOCRPlantillasSearchForm) formBusqueda;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		f.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());

		ocrCodigoPlantillaList.clear();
		ocrPlantillaList.clear();

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);

		criteriaOperacion.put("oidPais", reporteService.getOidString(
				"getOidPaisByCodigoPais", criteriaOperacion));

		List listaCodigoPlantilla = service
				.getListaCodigoPlantilla(criteriaOperacion);

		ocrCodigoPlantillaList = listaCodigoPlantilla;

	}

}
