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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSICEnviarInscritasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazSICEnviarInscritasAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 6910512972095250618L;
	
	private List siccCanalList = new ArrayList();
	private List siccMarcaList = new ArrayList();
	private List siccTipoClienteList = new ArrayList();
	private LabelValue[] siccVinculoList = null;
	private LabelValue[] siccPeriodoList = {};
	private LabelValue[] siccSubTipoClienteList = {};
	private LabelValue[] siccTipoClasificacionList = {};
	private LabelValue[] siccClasificacionList = {};

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de los combos Marca, Canal , Tipo Cliente, Tipo de Vinculo,
		// Sub Tipo de Cliente, Tipo de Clasificacion, Subtipo de Clasificacion
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma()
						.getCodigoISO());
		this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISO(usuario
						.getIdioma().getCodigoISO());

		List listaVinculos = new ArrayList();
		listaVinculos = interfazSiCCService.getTiposVinculosByPais(usuario
				.getPais().getCodigo());
		this.siccVinculoList = new LabelValue[listaVinculos.size()];
		int z = 0;
		for (Object object : listaVinculos) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccVinculoList()[z] = labelValue;
			z++;
		}
		
		this.siccPeriodoList  = aSvc.getPeriodosByPaisMarcaCanalRangos(pais.getCodigo(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, "0", "1");
		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		this.siccSubTipoClienteList = aSvc.getSubTiposClientesPorPaisTipoClientes(usuario.getIdioma()
						.getCodigoISO(), temp);

		temp = new ArrayList();
		temp.add(Constants.CODIGO_TIPO_CLIENTE_DEFAULT + "-"
				+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT);
		this.siccTipoClasificacionList = aSvc.getTiposClasificacionesByCriteriaMultiple(usuario
						.getIdioma().getCodigoISO(),
						Constants.CODIGO_TIPO_CLIENTE_DEFAULT, temp);
		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(Constants.CODIGO_TIPO_CLASIFICACION_DUPLA
				+ Constants.CODIGO_TIPO_CLIENTE_DEFAULT
				+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT);
		this.siccClasificacionList = aSvc.getClasificacionesByCriteriaMultiple(usuario.getIdioma()
						.getCodigoISO(), Constants.CODIGO_TIPO_CLIENTE_DEFAULT,
						temp, temp2);

		InterfazSICEnviarInscritasForm formInterfaz = (InterfazSICEnviarInscritasForm)this.formInterfaz;
		String codigoPeriodo = aSvc.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
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
//		request.getSession().setAttribute("codigoIdiomaISO",
//				usuario.getIdioma().getCodigoISO());
		
		formInterfaz.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		formInterfaz.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		formInterfaz.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		formInterfaz.setCodigoTipoVinculo(Constants.CODIGO_TIPO_VINCULO_DEFAULT);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		params = super.prepareParamsBeforeExecute(params, form);
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService
				.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
			if (historicos.size() == 1)
				params.put("fechaInicioUltimoProceso", ((Historico) historicos.get(0)).getFechaInicioProceso());
		} else {
			params.put("fechaInicioUltimoProceso", null);
		}
		InterfazSICEnviarInscritasForm f = (InterfazSICEnviarInscritasForm) this.formInterfaz;
		String [] codigoTipoClas = f.getCodigoTipoClasificacion();
		String [] codSubTipoCliente =   f.getCodigoSubTipoCliente();
		params.put("codigoTipoClasificacion", codigoTipoClas);
		params.put("codigoSubTipoCliente", codSubTipoCliente);
		params.put("codigoClasificacion", f.getCodigoClasificacion());
		
		return params;
	}
	
	/**
	 * Carga canales
	 * @param val
	 */
	public void cargarCanalDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Canal");
		}
		try {
			InterfazSICEnviarInscritasForm form = (InterfazSICEnviarInscritasForm) this.formInterfaz;

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
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
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
		try {
			InterfazSICEnviarInscritasForm formInterfaz = (InterfazSICEnviarInscritasForm) this.formInterfaz;
			LabelValue[] clean = new LabelValue[0];
			// this.siccVinculoList = clean;
			this.siccSubTipoClienteList = clean;
			formInterfaz.setCodigoSubTipoCliente(null);
			this.siccTipoClasificacionList = clean;
			formInterfaz.setCodigoTipoClasificacion(null);
			this.siccClasificacionList = clean;
			formInterfaz.setCodigoClasificacion(null);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			String valores = (String) val.getNewValue();
			ArrayList<String> arrSubCliente = new ArrayList<String>(
					Arrays.asList(valores));
			LabelValue lvlSubCliente[] = ajax
					.getSubTiposClientesPorPaisTipoClientesOID(pais.getCodigo(),
							arrSubCliente);

			if (lvlSubCliente != null && lvlSubCliente.length > 0) {
				this.siccSubTipoClienteList = lvlSubCliente;
			} else {
				this.siccSubTipoClienteList = clean;
				formInterfaz.setCodigoSubTipoCliente(null);
				this.siccTipoClasificacionList = clean;
				formInterfaz.setCodigoTipoClasificacion(null);
				this.siccClasificacionList = clean;
				formInterfaz.setCodigoClasificacion(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * loadTipoClasificacionList.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadTipoClasificacionList(ValueChangeEvent val) {
		log.debug(">>loadSubClienteList ");
		try {
			InterfazSICEnviarInscritasForm formInterfaz = (InterfazSICEnviarInscritasForm) this.formInterfaz;
			LabelValue[] clean = new LabelValue[0];
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String[] valores = (String[]) val.getNewValue();
			ArrayList<String> arrSubCliente = new ArrayList<String>(
					Arrays.asList(valores));
			String codigoISo = usuario.getIdioma().getCodigoISO();
			String codigoCliente = formInterfaz.getCodigoTipoCliente();

			LabelValue lblTipoClasificacion[] = ajax
					.getTiposClasificacionesByCriteriaMultiple(codigoISo,
							codigoCliente, arrSubCliente);

			if (lblTipoClasificacion != null && lblTipoClasificacion.length > 0) {
				this.siccTipoClasificacionList = lblTipoClasificacion;
			} else {
				this.siccTipoClasificacionList = clean;
				formInterfaz.setCodigoTipoClasificacion(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * loadClasificacionList.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadClasificacionList(ValueChangeEvent val) {
		log.debug(">>loadClasificacionList ");
		try {
			InterfazSICEnviarInscritasForm formInterfaz = (InterfazSICEnviarInscritasForm) this.formInterfaz;
			LabelValue[] clean = new LabelValue[0];
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String[] valores = (String[]) val.getNewValue();
			ArrayList<String> arrClasificacion = new ArrayList<String>(
					Arrays.asList(valores));
			ArrayList<String> arrSubTipoCliente = new ArrayList<String>(
					Arrays.asList(formInterfaz.getCodigoSubTipoCliente()));
			LabelValue lblClasificacion[] = ajax
					.getClasificacionesByCriteriaMultiple(usuario.getIdioma()
							.getCodigoISO(), formInterfaz.getCodigoTipoCliente(),
							arrSubTipoCliente, arrClasificacion);

			if (lblClasificacion != null && lblClasificacion.length > 0) {
				this.siccClasificacionList = lblClasificacion;
			} else {
				this.siccClasificacionList = clean;
				formInterfaz.setCodigoClasificacion(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}


	/**
	 * Cargar marca
	 * @param val
	 */
	public void cargarMarcaDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Marca");
		}
		try {
			InterfazSICEnviarInscritasForm form = (InterfazSICEnviarInscritasForm) this.formInterfaz;

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
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSICEnviarInscritasForm form = new InterfazSICEnviarInscritasForm();
		return form;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList the siccTipoClienteList to set
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
	 * @param siccVinculoList the siccVinculoList to set
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
	 * @param siccPeriodoList the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return the siccTipoClasificacionList
	 */
	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	/**
	 * @param siccTipoClasificacionList the siccTipoClasificacionList to set
	 */
	public void setSiccTipoClasificacionList(LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	/**
	 * @return the siccClasificacionList
	 */
	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	/**
	 * @param siccClasificacionList the siccClasificacionList to set
	 */
	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}
}