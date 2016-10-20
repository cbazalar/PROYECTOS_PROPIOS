package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCInteresMoraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCInteresesMoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCCCInteresesMoraAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -5328972522707292808L;
	private Map datosInteres;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCInteresesMoraForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCInteresesMoraForm form = new MantenimientoCCCInteresesMoraForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/**
	 * Deshabilitar botones
	 */
	public void desabilitarBotones() {
		this.mostrarBotonBuscar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.datosInteres = null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#save(javax.faces.event.ActionEvent)
	 */
	public void save(ActionEvent actionEvent) {
		MantenimientoCCCInteresesMoraForm f = (MantenimientoCCCInteresesMoraForm) this.formBusqueda;
		String mensaje = null;
		Integer diasCadencia = null;
		diasCadencia = f.getDiasCadencia();
		Integer diasCorte = f.getDiasCorte();
		Double montoMinimo = f.getMontoMinimo();
		Double tasaImpuesto = f.getTasaImpuesto();
		Double importeFijo = f.getImporteFijo();
		Double tasaInteres = f.getTasaInteres();
		Double saldoPermitido = f.getSaldoPermitido();
		Integer numeroDiasBloqueo = f.getNumeroDiasBloqueo();
		MantenimientoCCCInteresMoraService mantenimientoCCCInteresMoraService = (MantenimientoCCCInteresMoraService) getBean("spusicc.mantenimientoCCCInteresMoraService");
		this.datosInteres.put("diasCadencia", diasCadencia);
		this.datosInteres.put("diasCorte", diasCorte);
		this.datosInteres.put("montoMinimo", montoMinimo);
		this.datosInteres.put("tasaImpuesto", tasaImpuesto);
		this.datosInteres.put("importeFijo", importeFijo);
		this.datosInteres.put("tasaInteres", tasaInteres);
		this.datosInteres.put("saldoPermitido", saldoPermitido);
		this.datosInteres.put("numeroDiasBloqueo", numeroDiasBloqueo);

		try {
			this.datosInteres.put("diasCadencia", diasCadencia);
			this.datosInteres.put("diasCorte", diasCorte);
			this.datosInteres.put("montoMinimo", montoMinimo);
			this.datosInteres.put("tasaImpuesto", tasaImpuesto);
			this.datosInteres.put("importeFijo", importeFijo);
			this.datosInteres.put("tasaInteres", tasaInteres);
			this.datosInteres.put("indiceBusqueda", Constants.NUMERO_UNO);
			this.datosInteres.put("indiceAxuliar", Constants.NUMERO_DOS);
			this.datosInteres.put("saldoPermitido", saldoPermitido);
			this.datosInteres.put("numeroDiasBloqueo", numeroDiasBloqueo);
			mantenimientoCCCInteresMoraService
					.updateInteresMontoMora(this.datosInteres);
			mantenimientoCCCInteresMoraService
					.insertInteresMontoMora(this.datosInteres);

			this.datosInteres.put("indiceBusqueda", Constants.NUMERO_DOS);
			this.datosInteres.put("indiceAxuliar", Constants.NUMERO_CERO);

			mantenimientoCCCInteresMoraService
					.updateInteresMontoMora(this.datosInteres);

			mensaje = this
					.getResourceMessage("mantenimientoCCCInteresesMoraForm.insert");

			this.addInfo("Info : ", mensaje);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		// Recuperando datos de la sesion

		// Obtenemos los beans b�sicos de la sesi�n
		desabilitarBotones();

		log.debug("JFA Todo Ok: Redireccionando");
		MantenimientoCCCInteresMoraService mantenimientoCCCInteresMoraService = (MantenimientoCCCInteresMoraService) getBean("spusicc.mantenimientoCCCInteresMoraService");
		this.datosInteres = mantenimientoCCCInteresMoraService
				.getInteresMontoMora();
		MantenimientoCCCInteresesMoraForm f = (MantenimientoCCCInteresesMoraForm) this.formBusqueda;
		f.setDiasCadencia(MapUtils.getIntValue(this.datosInteres,
				"diasCadencia"));
		f.setDiasCorte(MapUtils.getIntValue(this.datosInteres, "diasCorte"));
		f.setMontoMinimo(MapUtils.getDoubleValue(this.datosInteres,
				"montoMinimo"));
		f.setTasaImpuesto(MapUtils.getDoubleValue(this.datosInteres,
				"tasaImpuesto"));
		f.setImporteFijo(MapUtils.getDoubleValue(this.datosInteres,
				"importeFijo"));
		f.setTasaInteres(MapUtils.getDoubleValue(this.datosInteres,
				"tasaInteres"));
		f.setSaldoPermitido(MapUtils.getDoubleValue(this.datosInteres,
				"saldoPermitido"));
		f.setNumeroDiasBloqueo(MapUtils.getIntValue(this.datosInteres,
				"numeroDiasBloqueo"));
	}

}