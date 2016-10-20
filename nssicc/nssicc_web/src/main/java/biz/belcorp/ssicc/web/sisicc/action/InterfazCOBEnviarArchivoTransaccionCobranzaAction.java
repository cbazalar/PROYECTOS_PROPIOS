package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBEnviarArchivoTransaccionCobranzaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazCOBEnviarArchivoTransaccionCobranzaAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = -9222771781242392764L;
	private List intSegSociedadList = new ArrayList();

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		// Variables
		InterfazCOBEnviarArchivoTransaccionCobranzaForm f = (InterfazCOBEnviarArchivoTransaccionCobranzaForm) this.formInterfaz;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Calendar fecha = new GregorianCalendar();

		int imes = fecha.get(Calendar.MONTH) + 1;
		int ianhio = fecha.get(Calendar.YEAR);

		String anhio = String.valueOf(ianhio);
		String mes = String.valueOf(imes);

		if (mes.length() == 1) {
			mes = "0" + mes;
		}

		// Crear Pojo

		Map criteria = new HashMap();

		// Logica de Negocio
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais",
				reporteService.getOidString("getOidPaisByCodigoPais", criteria));
		ArrayList listSociedad = (ArrayList) interfazSiCCService
				.getSociedadesByCodigoPais(pais.getCodigo());

		// Peticiones
		this.intSegSociedadList = listSociedad;
		f.setAnhio(anhio);
		f.setMes(mes);

	}

	/**
	 * Prepara los parametros a pasar al Service para la ejecucion de la
	 * Interfaz SiCC. Remueve parametros innecesarios generados en la capa web,
	 * y setea los parametros necesarios para la capa service.
	 * 
	 * @param form
	 * @param request
	 * @return Map con los parametros para la ejecucion.
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String titulo = "";

		params = super.prepareParamsBeforeExecute(params, form);

		params.put("sociedad", service.getSociedadEquivalenciaSAP(params));

		//MessageResources messageResources = getResources(request);

		titulo = this.getResourceMessage("interfazCOBEnviarArchivoTransaccionCobranzaForm.tituloCSV");

		params.put("titulo", titulo);

		if (log.isDebugEnabled()) {
			log.debug(params.toString());
		}

		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOBEnviarArchivoTransaccionCobranzaForm form = new InterfazCOBEnviarArchivoTransaccionCobranzaForm();
		return form;
	}

	/**
	 * @return the intSegSociedadList
	 */
	public List getIntSegSociedadList() {
		return intSegSociedadList;
	}

	/**
	 * @param intSegSociedadList the intSegSociedadList to set
	 */
	public void setIntSegSociedadList(List intSegSociedadList) {
		this.intSegSociedadList = intSegSociedadList;
	}
}