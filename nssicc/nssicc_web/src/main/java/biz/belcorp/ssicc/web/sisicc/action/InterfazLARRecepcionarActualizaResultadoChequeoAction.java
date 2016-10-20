package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.AccesoCanal;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLARRecepcionarActualizaResultadoChequeoForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarMaestrosAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
@ManagedBean
@SessionScoped
public class InterfazLARRecepcionarActualizaResultadoChequeoAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3839347109001543694L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoListTodos;
	private List siccSubaccesoList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLARRecepcionarActualizaResultadoChequeoForm form = new InterfazLARRecepcionarActualizaResultadoChequeoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccAccesoList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);

		this.siccSubaccesoList = getSubAccesoList(Constants.CODIGO_ACCESO_DEFAULT);

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService
				.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
			if (historicos.size() == 1)
				params.put("fechaInicioUltimoProceso",
						((Historico) historicos.get(0)).getFechaInicioProceso());
		} else {
			params.put("fechaInicioUltimoProceso", null);
		}
		return params;
	}

	/**
	 * Metodo para cargar lista Acceso
	 */
	public void loadAcceso(ValueChangeEvent value) {
		String valor = (String) value.getNewValue();
		InterfazLARRecepcionarActualizaResultadoChequeoForm f = (InterfazLARRecepcionarActualizaResultadoChequeoForm) this.formInterfaz;
		if (valor == null) {
			this.siccAccesoList.clear();
			this.siccSubaccesoList.clear();
		} else {
			this.siccAccesoList = getAccesoList(valor);
			String cod = ((AccesoCanal) siccAccesoList.get(0))
					.getCodigoAcceso();
		}

	}

	/**
	 * Metodo para cargar lista SubAcceso
	 */
	public void loadSubAcceso(ValueChangeEvent value) {
		String valor = (String) value.getNewValue();
		if (valor == null)
			this.siccSubaccesoList.clear();
		else
			this.siccSubaccesoList = getSubAccesoList(valor);

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
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList
	 *            the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	/**
	 * @return the siccAccesoListTodos
	 */
	public List getSiccAccesoListTodos() {
		return siccAccesoListTodos;
	}

	/**
	 * @param siccAccesoListTodos
	 *            the siccAccesoListTodos to set
	 */
	public void setSiccAccesoListTodos(List siccAccesoListTodos) {
		this.siccAccesoListTodos = siccAccesoListTodos;
	}

	/**
	 * @return the siccSubaccesoList
	 */
	public List getSiccSubaccesoList() {
		return siccSubaccesoList;
	}

	/**
	 * @param siccSubaccesoList
	 *            the siccSubaccesoList to set
	 */
	public void setSiccSubaccesoList(List siccSubaccesoList) {
		this.siccSubaccesoList = siccSubaccesoList;
	}

}
