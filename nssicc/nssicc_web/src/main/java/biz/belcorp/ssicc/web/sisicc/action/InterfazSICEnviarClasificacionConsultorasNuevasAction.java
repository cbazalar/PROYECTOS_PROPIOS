package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSICEnviarClasificacionConsultorasNuevasForm;

/**
 * The Class InterfazSICEnviarClasificacionConsultorasNuevasAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 02/12/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class InterfazSICEnviarClasificacionConsultorasNuevasAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 6275681289236451477L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccTipoClienteList;
	private List siccSubTipoClienteList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSICEnviarClasificacionConsultorasNuevasForm actionForm = new InterfazSICEnviarClasificacionConsultorasNuevasForm();
		return actionForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de los combos Marca, Canal y Tipo Cliente
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		this.siccTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());

		// Cargo el combo SubTipoCliente con los valores relacionados al
		// TipoCliente por defecto
		Map criteriaSubTipoCliente = new HashMap();
		criteriaSubTipoCliente.put("codigoTipoCliente",
				Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		List listaSubTipoCliente = interfazSiCCService
				.getSubTiposClientesByCriteria(criteriaSubTipoCliente);
		Iterator it = listaSubTipoCliente.iterator();
		LabelValue labelValue[] = new LabelValue[listaSubTipoCliente.size()];
		int i = 0;
		while (it.hasNext()) {
			Base subTipoCliente = (Base) it.next();
			LabelValue label = new LabelValue();
			label.setLabel(subTipoCliente.getDescripcion());
			label.setValue(subTipoCliente.getCodigo());
			labelValue[i] = label;
			i++;
		}
		this.siccSubTipoClienteList = Arrays.asList(labelValue);

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		InterfazSICEnviarClasificacionConsultorasNuevasForm form = (InterfazSICEnviarClasificacionConsultorasNuevasForm) this.formInterfaz;
		form.setCodigoPais(pais.getCodigo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		InterfazSICEnviarClasificacionConsultorasNuevasForm f = (InterfazSICEnviarClasificacionConsultorasNuevasForm) this.formInterfaz;
		params.put(
				"codigoSubTipoCliente",
				f.getCodigoSubTipoCliente() == null ? new ArrayList() : Arrays
						.asList(f.getCodigoSubTipoCliente()));
		return params;
	}

	/**
	 * loadSubTiposClientes.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadSubTiposClientes(ValueChangeEvent val) {
		try {
			log.debug(">>loadSubTiposClientes ");
			log.debug("val: " + val.getNewValue().toString());
			InterfazSICEnviarClasificacionConsultorasNuevasForm form = (InterfazSICEnviarClasificacionConsultorasNuevasForm) this.formInterfaz;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue subtipcliente[] = ajax
					.getSubTiposClientesPorPaisTipoCliente(
							form.getCodigoPais(), val.getNewValue().toString());
			if (subtipcliente != null && subtipcliente.length > 0) {
				this.siccSubTipoClienteList = Arrays.asList(subtipcliente);
			} else {
				this.siccSubTipoClienteList.clear();
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
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
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList
	 *            the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public List getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList
	 *            the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(List siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

}