package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSICEnviarVentaBaseConsultorasForm;

@ManagedBean
@SessionScoped
public class InterfazSICEnviarVentaBaseConsultorasAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 7513932385449503851L;
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private List siccTipoClienteList = new ArrayList();
	private LabelValue[] siccVinculoList = null;
	private LabelValue[] siccPeriodoList = {};
	private LabelValue[] siccConcursoList = {};

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de los combos Marca, Canal , Tipo Cliente,
		// Sub Tipo de Cliente, Tipo de Clasificacion, Subtipo de Clasificacion
		siccMarcaList = interfazSiCCService.getMarcas();
		siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		siccTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		// siccVinculoList =
		// interfazSiCCService.getTiposVinculosByPais(usuario.getPais().getCodigo());
		
		InterfazSICEnviarVentaBaseConsultorasForm interfazForm = (InterfazSICEnviarVentaBaseConsultorasForm) this.formInterfaz;

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		interfazForm.setCodigoPeriodo(interfazSiCCService
				.getPeriodoDefaultByPaisCanal(pais.getCodigo(), ""));

		siccPeriodoList = aSvc.getPeriodosByPaisMarcaCanalRangos(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, "0", "1");

		String codigoPeriodo = aSvc.getPeriodoDefaultByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);

		siccConcursoList = aSvc.getConcursosByPaisMarcaCanalPeriodo(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);

		InterfazSICEnviarVentaBaseConsultorasForm f = (InterfazSICEnviarVentaBaseConsultorasForm) this.formInterfaz;
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		f.setCodigoConcurso("");
		f.setCodigoPeriodo(codigoPeriodo);
		f.setCodigoTipoClasificacion("");
		f.setCodigoSubTipoCliente("");
		f.setCodigoClasificacion("");
		f.setObservaciones("");
		f.setCodigoPais(pais.getCodigo());

	}

	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazSICEnviarVentaBaseConsultorasForm f = (InterfazSICEnviarVentaBaseConsultorasForm) this.formInterfaz;
		// Paso todos los parametros al map
		//params = BeanUtils.describe(form);
		params =  super.prepareParamsBeforeExecute(params, form);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Agrego los parametros necesarios
		params.put("usuario", usuario);

		// Remuevo los parametros que no son necesarios
		params.remove("resultValueMap");
		params.remove("validatorResults");
		params.remove("servletWrapper");
		params.remove("multipartRequestHandler");
		params.remove("class");
		params.remove("page");
		params.put(
				"codigoTipoClientes",
				(f.getCodigoTipoClientes() == null) ? new ArrayList() : Arrays
						.asList(f.getCodigoTipoClientes()));
		params.put("codigoSubTipoClientes",
				(f.getCodigoSubTipoClientes() == null) ? new ArrayList()
						: Arrays.asList(f.getCodigoSubTipoClientes()));
		return params;
	}

	public void cargarCanalDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Canal");
		}
		InterfazSICEnviarVentaBaseConsultorasForm form = (InterfazSICEnviarVentaBaseConsultorasForm) this.formInterfaz;

		String canal = (String) val.getNewValue();
		String marca = form.getCodigoMarca();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccPeriodoList = aSvc.getPeriodosByPaisMarcaCanalRangos(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, "0", "1");

		} else {
			this.siccPeriodoList = null;
		}
	}

	public void cargarMarcaDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Marca");
		}
		InterfazSICEnviarVentaBaseConsultorasForm form = (InterfazSICEnviarVentaBaseConsultorasForm) this.formInterfaz;

		String marca = (String) val.getNewValue();
		String canal = form.getCodigoCanal();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccPeriodoList = aSvc.getPeriodosByPaisMarcaCanalRangos(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, "0", "1");

		} else {
			this.siccPeriodoList = null;
		}
	}

	/**
	 * loadListasTipoVista.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadSubClienteList(ValueChangeEvent val) {
		log.debug(">>loadSubClienteList ");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String[] valores = (String[]) val.getNewValue();
		ArrayList<String> arrSubCliente = new ArrayList<String>(
				Arrays.asList(valores));
		LabelValue lvlSubCliente[] = ajax
				.getSubTiposClientesPorPaisTipoClientes(pais.getCodigo(),
						arrSubCliente);
		if (lvlSubCliente != null && lvlSubCliente.length > 0) {
			this.siccVinculoList = lvlSubCliente;
		} else {
			// LabelValue[] clean = {};
			this.siccVinculoList = null;
		}
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSICEnviarVentaBaseConsultorasForm form = new InterfazSICEnviarVentaBaseConsultorasForm();
		return form;
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
	 * @return the siccVinculoList
	 */
	public LabelValue[] getSiccVinculoList() {
		return siccVinculoList;
	}

	/**
	 * @param siccVinculoList
	 *            the siccVinculoList to set
	 */
	public void setSiccVinculoList(LabelValue[] siccVinculoList) {
		this.siccVinculoList = siccVinculoList;
	}

	/**
	 * @return the siccPeriodoList
	 */
	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList
	 *            the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return the siccConcursoList
	 */
	public LabelValue[] getSiccConcursoList() {
		return siccConcursoList;
	}

	/**
	 * @param siccConcursoList
	 *            the siccConcursoList to set
	 */
	public void setSiccConcursoList(LabelValue[] siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}
}