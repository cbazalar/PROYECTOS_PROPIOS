package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDataCreditoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCGenerarInformacionDataCreditoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ProcesoCCCGenerarInformacionDataCreditoAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = -6552715815979716274L;

	private List siccSociedadList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCGenerarInformacionDataCreditoForm procesoForm = new ProcesoCCCGenerarInformacionDataCreditoForm();
		return procesoForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		// Obtenemos los beans básicos de la sesión
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Cargamos los combos a utilizar

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por
		// Pais
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());
		log.debug("JFA Todo Ok: Redireccionando");

	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("JFA Entering executeProcess");
		ProcesoCCCGenerarInformacionDataCreditoService service = (ProcesoCCCGenerarInformacionDataCreditoService) getBean("spusicc.procesoCCCGenerarInformacionDataCreditoService");
		service.executeGenerarInformacionDataCredito(params);
		return params;

	}

	/**
	 * @return
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

}
