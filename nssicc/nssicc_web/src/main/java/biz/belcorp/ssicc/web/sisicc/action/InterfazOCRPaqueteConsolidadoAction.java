package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.SSiCC_Desfasado_BaseGenericoPaqueteAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRPaqueteConsolidadoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazOCRPaqueteConsolidadoAction extends
		SSiCC_Desfasado_BaseGenericoPaqueteAbstractAction {

	private static final long serialVersionUID = 1608914281841645713L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	protected void setViewAtributes() throws Exception {
		InterfazOCRPaqueteConsolidadoForm interfazOCRForm = (InterfazOCRPaqueteConsolidadoForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Carga Periodo
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazOCRForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));

		interfazOCRForm.setIndValidacionSTO(Constants.SI);
		interfazOCRForm.setIndValidacionSTOBool(true);
		this.validarListaArchivosEntrada = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazOCRPaqueteConsolidadoForm f = (InterfazOCRPaqueteConsolidadoForm) this.formInterfaz;
		if (!f.getIndValidacionSTOBool()) {
			f.setIndValidacionSTO(Constants.NO);
		}
		params = super.prepareParamsBeforeExecute(params, form);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		params.put("codigoPais", codigoPais);
		params.put("usuario", usuario);
		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("indOrigen", Constants.STO_ORIGEN_OCR);
		params.put("indicadorOCRComercial", pais.getIndicadorOCRComercial());
		params.put("indValidacionSTO", Constants.SI.equals(f
				.getIndValidacionSTO()) ? Constants.SI : Constants.NO);
		
		//params.put("", value)
		params.put("isCompuesta",  Constants.SI);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #executeProcessBeforeInterfaz(java.util.Map)
	 */
	protected Map<String, Object> executeProcessBeforeInterfaz(
			Map<String, Object> params) throws Exception {
		params = super.executeProcessBeforeInterfaz(params);
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("descripcionEtapaProceso",
				Constants.DESCRIPCION_RECEPCIONAR_OCR_PROCESO);
		ProcesoBatchService procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
		return params;
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRPaqueteConsolidadoForm form = new InterfazOCRPaqueteConsolidadoForm();
		return form;
	}
}