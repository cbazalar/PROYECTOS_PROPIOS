package biz.belcorp.ssicc.web.spusicc.gen.action;

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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENInicioCampanyaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoGENInicioCampanyaAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4460488242489358779L;
	private List siccMarcaList;
	private List siccCanalList;
	private boolean opcionPeriodoFecha;
	private String bloqueo;
	private String tipoOperacion;
	private String tipoTransaccion;
    private String indicadorProceso;
    private String indTipoValid;
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		ProcesoGENInicioCampanyaForm f = (ProcesoGENInicioCampanyaForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		// f.setHabilitadorHidden(Constants.NUMERO_CERO);
		setBloqueo("true");
		setOpcionPeriodoFecha(true);
		this.tipoTransaccion = this.getParametrosPantalla()
				.get("tipoTransaccion").toString();
		this.tipoOperacion = this.getParametrosPantalla().get("tipoOperacion")
				.toString();
		
		this.indicadorProceso =  this.getParametrosPantalla().get("indicadorProceso")
				.toString();
		this.indTipoValid =  this.getParametrosPantalla().get("indTipoValid")
				.toString();

		MantenimientoOCRPedidoControlFacturacionService serviceFaturacion = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFaturacion
				.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaProceso(controlFacturacion.getFechaProceso());
		f.setFechaProcesoD(DateUtil.convertStringToDate(f.getFechaProceso()));

		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);

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

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoGENInicioCampanyaForm f = (ProcesoGENInicioCampanyaForm) this.formInterfaz;
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		params = super.prepareParamsBeforeExecute(params, form);
		
		params.put("periodoProceso", f.getCodigoPeriodo());
		params.put("fechaProceso", f.getFechaProceso());
		params.put("login", usuario.getLogin());
		params.put("codigoRegion", "");
		params.put("tipoTransaccion", this.tipoTransaccion);
		params.put("tipoOperacion", this.tipoOperacion);
		params.put("indicadorProceso",this.indicadorProceso);
		params.put("indTipoValid",this.indTipoValid);
		
		String servidor = this.getRequest().getServerName();
		params.put("nombreServidor", servidor);

		if (this.tipoTransaccion != null && this.tipoOperacion != null
				&& this.tipoTransaccion.equals("1")
				&& this.tipoOperacion.equals("2")) {
			params.put("codigoCliente", "-1");
		}

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoGENInicioCampanyaForm form = new ProcesoGENInicioCampanyaForm();
		return form;
	}

	/**
	 * @param val
	 */
	public void loadPeriodoFecha(ValueChangeEvent val) {
		try {
			String opcion = (String) val.getNewValue().toString();
			ProcesoGENInicioCampanyaForm f = (ProcesoGENInicioCampanyaForm) this.formInterfaz;

			if (opcion == "true") {
				setOpcionPeriodoFecha(false);
				setBloqueo("false");
				f.setHabilitadorHidden(Constants.NUMERO_UNO);
			} else {
				setOpcionPeriodoFecha(true);
				setBloqueo("true");
				f.setCodigoPeriodo(f.getCodigoPeriodo());
				f.setFechaProcesoD(DateUtil.convertStringToDate(f
						.getFechaProceso()));
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
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
	 * @return the opcionPeriodoFecha
	 */
	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	/**
	 * @param opcionPeriodoFecha
	 *            the opcionPeriodoFecha to set
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

	/**
	 * @return the bloqueo
	 */
	public String getBloqueo() {
		return bloqueo;
	}

	/**
	 * @param bloqueo
	 *            the bloqueo to set
	 */
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}
}