package biz.belcorp.ssicc.web.sisicc.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRETEnviarFacturasVentaDirectaForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazRETEnviarFacturasVentaDirectaAction extends
		BaseInterfazAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7464201689411461721L;
	private List siccMarcaList;
	private List siccCanalList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRETEnviarFacturasVentaDirectaForm interfazRETEnviarFacturasVentaDirectaForm = new InterfazRETEnviarFacturasVentaDirectaForm();
		return interfazRETEnviarFacturasVentaDirectaForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		InterfazRETEnviarFacturasVentaDirectaForm f = (InterfazRETEnviarFacturasVentaDirectaForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		f.setCodigoPeriodo(periodoRequerido(Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");	       
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        f.setFechaFacturacion(controlFacturacion.getFechaProceso());
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        f.setFechaFacturacionD(DateUtil.convertStringToDate(f.getFechaFacturacion()));

	}

	public String periodoRequerido(String marca, String canal) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		dato = ajax.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), marca,
				canal);
		return dato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		params = super.prepareParamsBeforeExecute(params, form);
		InterfazRETEnviarFacturasVentaDirectaForm f = (InterfazRETEnviarFacturasVentaDirectaForm) this.formInterfaz;

		params.put("fechaFacturacion", DateUtil.convertDateToString(f.getFechaFacturacionD())); 
		return params;

	}

	public String setValidarInterfaz() {
		InterfazRETEnviarFacturasVentaDirectaForm form = (InterfazRETEnviarFacturasVentaDirectaForm) this.formInterfaz;

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String periodo = form.getCodigoPeriodo();
		String periodorequerido = periodoRequerido(form.getCodigoMarca(),
				form.getCodigoCanal());
		if (!periodo.equals(periodorequerido)) {
			String mensaje = this
					.getResourceMessage("interfazRETEnviarFacturasVentaDirectaForm.error.rango.fechaFacturacion");
			return mensaje;
		}

		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		try {
			if (form.getFechaFacturacionD().before(
					DateUtil.convertStringToDate(fechaDesde))
					|| form.getFechaFacturacionD().after(
							DateUtil.convertStringToDate(fechaHasta))) {
				String mensaje = this
						.getResourceMessage("interfazRETEnviarFacturasVentaDirectaForm.error.rango.fechaFacturacion")
						+ " (" + fechaDesde + " - " + fechaHasta + ")";
				return mensaje;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo para obtener el periodo por marca
	 * 
	 * @param val
	 */
	public void loadPeriodoMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoMarca");
		}
		String marca = (String) val.getNewValue();
		InterfazRETEnviarFacturasVentaDirectaForm f = (InterfazRETEnviarFacturasVentaDirectaForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
	}

	/**
	 * Metodo para obtener el periodo por canal
	 * 
	 * @param val
	 */
	public void loadPeriodoCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazRETEnviarFacturasVentaDirectaForm f = (InterfazRETEnviarFacturasVentaDirectaForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(f.getCodigoMarca(), canal));
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

}

