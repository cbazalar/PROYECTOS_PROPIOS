package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSICEnviarMaestrosForm;

@ManagedBean
@SessionScoped
public class InterfazSICEnviarMaestrosAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6776763318419234073L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccTipoClienteList;
	private List siccVinculoList;
	private LabelValue[] siccPeriodoList = {};
	private LabelValue[] siccSubTipoClienteList = {};
	private LabelValue[] siccTipoClasificacionList = {};
	private LabelValue[] siccClasificacionList = {};
	private LabelValue[] siccConcursoList = {};

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSICEnviarMaestrosForm form = new InterfazSICEnviarMaestrosForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de los combos Marca, Canal , Tipo Cliente, Tipo de Vinculo,
		// Sub Tipo de Cliente, Tipo de Clasificacion, Subtipo de Clasificacion
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		this.siccTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccVinculoList = interfazSiCCService.getTiposVinculosByPais(pais
				.getCodigo());
		this.siccPeriodoList = aSvc.getPeriodosByPaisMarcaCanalRangos(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, "0", "1");
		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		this.siccSubTipoClienteList = aSvc
				.getSubTiposClientesPorPaisTipoClientes(usuario.getIdioma()
						.getCodigoISO(), temp);

		temp = new ArrayList();
		temp.add(Constants.CODIGO_TIPO_CLIENTE_DEFAULT + "-"
				+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT);
		this.siccTipoClasificacionList = aSvc
				.getTiposClasificacionesByCriteriaMultiple(usuario.getIdioma()
						.getCodigoISO(), Constants.CODIGO_TIPO_CLIENTE_DEFAULT,
						temp);
		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(Constants.CODIGO_TIPO_CLASIFICACION_DUPLA
				+ Constants.CODIGO_TIPO_CLIENTE_DEFAULT
				+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT);
		this.siccClasificacionList = aSvc.getClasificacionesByCriteriaMultiple(
				usuario.getIdioma().getCodigoISO(),
				Constants.CODIGO_TIPO_CLIENTE_DEFAULT, temp, temp2);

		InterfazSICEnviarMaestrosForm formInterfaz = (InterfazSICEnviarMaestrosForm) this.formInterfaz;
		String codigoPeriodo = aSvc.getPeriodoDefaultByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);
		formInterfaz.setCodigoPeriodo(codigoPeriodo);

		formInterfaz
				.setCodigoSubTipoCliente(new String[] { Constants.CODIGO_TIPO_CLIENTE_DEFAULT
						+ "-" + Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT });
		formInterfaz
				.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		formInterfaz
				.setCodigoTipoClasificacion(new String[] { Constants.CODIGO_TIPO_CLASIFICACION_DUPLA
						+ Constants.CODIGO_TIPO_CLIENTE_DEFAULT
						+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT });
		formInterfaz
				.setCodigoClasificacion(new String[] { Constants.CODIGO_TIPO_CLASIFICACION_DUPLA
						+ Constants.CODIGO_TIPO_CLIENTE_DEFAULT
						+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT
						+ Constants.CODIGO_CLASIFICACION_DEFAULT });

		this.siccConcursoList = aSvc.getConcursosByPaisMarcaCanalPeriodo(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
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
		InterfazSICEnviarMaestrosForm f = (InterfazSICEnviarMaestrosForm) form;
		params.put("codigoTipoClasificacion", f.getCodigoTipoClasificacion());
		params.put("codigoSubTipoCliente", f.getCodigoSubTipoCliente());
		params.put("codigoClasificacion", f.getCodigoClasificacion());
		return params;
	}


	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	public List getSiccVinculoList() {
		return siccVinculoList;
	}

	public void setSiccVinculoList(List siccVinculoList) {
		this.siccVinculoList = siccVinculoList;
	}

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	public void setSiccTipoClasificacionList(
			LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	public LabelValue[] getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(LabelValue[] siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

}
