package biz.belcorp.ssicc.web.spusicc.men.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.men.model.BaseMensaje;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPlantillaConfiguracionProcesosForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPlantillaConfiguracionProcesosSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoMENPlantillaConfiguracionProcesosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6450868876764850912L;
	private String codigoPais;
	private List msgPlanillaCabecList;
	private List msgVistaPlanillaList;
	private List msgVistaPlantillaPopupList;
	private List menHoraActividadList;
	private List menMinutoActividadList;
	private List menTiempoAmPmActividadList;
	private List msgParametrosPlantillaList;
	private List msgValorDefectoList;
	private List msgValorDefectoPopupList;
	private List listParametrosPlantilla;
	private List listParametrosMultiple;
	private LabelValue[] valoresPosibles = {};
	private LabelValue[] valoresDefectos = {};
	private boolean _indicadorActivo;
	private boolean _indicadorGestionUsuario;
	private boolean mostrarPopupConsultora;
	private String NUMERO_CERO = Constants.NUMERO_CERO;
	private String NUMERO_UNO = Constants.NUMERO_UNO;
	private String accionGenerico;
	private String[] hdvalorPosible = new String[100];
	private String[] hdvalorDefecto = new String[100];
	private static final String TIPO_DATO_POPUP = "POPUP";
	private static final String TIPO_DATO_COMBOBOX = "LISTBOX";
	private static final String TIPO_DATO_TEXTBOX = "TEXTBOX";
	private static final String TIPO_DATO_CHKBOX = "CHKBOX";
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	public static final int MSG_CANTIDAD_SELECCION_TOTAL = 100;
	private int correlativoComboMultiple = 1;
	private Date fechaD;
	private ArrayList oidTipoCliente;
	private ArrayList oidSubTipoCliente;
	private ArrayList oidTipoClasificacion;
	private ArrayList oidClasificacion;
	private LabelValue[] tipoClienteList = {};
	private LabelValue[] subTipoClienteList = {};
	private LabelValue[] tipoClasificacionesList = {};
	private LabelValue[] clasificacionesList = {};
	private List listaComboMultiple;

	@ManagedProperty(value = "#{busquedaGenericaPOPUPSearchAction}")
	private BusquedaGenericaPOPUPSearchAction busquedaGenericaPOPUPSearchAction;

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		accion = this.accionGenerico;
		accion = accion.split(";")[0];
		this.mostrarProcesoBatch = true;
		this.mostrarPopupConsultora = false;
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaGenericaPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaGenericaPOPUPSearchAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaGenericaPOPUPSearchAction
						.getBeanRegistroSeleccionado();

				Iterator it = msgParametrosPlantillaList.iterator();
				String codigo = busquedaGenericaPOPUPSearchAction
						.getIndexPopup().split("POPUP")[1];
				while (it.hasNext()) {
					Map map = (Map) it.next();
					if (map.get("codigoTipoDato").toString()
							.equals(codigo.split(":")[0])) {
						map.put("codigoPopup", cliente.get("codigo").toString());
					}
				}
				this.busquedaGenericaPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}

	@Override
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaGenericaPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoMENPlantillaConfiguracionProcesosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoMENPlantillaConfiguracionProcesosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMENPlantillaConfiguracionProcesosSearchForm form = new MantenimientoMENPlantillaConfiguracionProcesosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		MantenimientoMENPlantillaConfiguracionProcesosSearchForm f = (MantenimientoMENPlantillaConfiguracionProcesosSearchForm) this.formBusqueda;
		// enviando en session los parametros de mensaje
		if (_indicadorActivo)
			f.setIndicadorActivo(Constants.NUMERO_UNO);
		else
			f.setIndicadorActivo(Constants.NUMERO_CERO);

		if (_indicadorGestionUsuario)
			f.setIndicadorGestionUsuario(Constants.NUMERO_UNO);
		else
			f.setIndicadorGestionUsuario(Constants.NUMERO_CERO);
		Map map = new HashMap();
		map.put("codigoPlantilla", f.getCodigoPlantilla());
		map.put("descripcionPlantilla", f.getDescripcion());
		map.put("observacion", f.getObservaciones());
		map.put("indicadorActivo",
				f.getIndicadorActivo().equals(Constants.NUMERO_CERO) ? null
						: Constants.NUMERO_UNO);
		map.put("indicadorGestionUsuario", f.getIndicadorGestionUsuario()
				.equals(Constants.NUMERO_CERO) ? null : Constants.NUMERO_UNO);
		List listPlantillaCabec = service.getPlantillaCabecera(map);
		this.msgPlanillaCabecList = listPlantillaCabec;
		return listPlantillaCabec;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub

		Map data = (Map) this.beanRegistroSeleccionado;
		if (data != null) {

			MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
			service.deletePlantilla(data);

		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		boolean bGrabar = false;
		MantenimientoMENPlantillaConfiguracionProcesosForm f = (MantenimientoMENPlantillaConfiguracionProcesosForm) this.formMantenimiento;
		if (_indicadorActivo)
			f.setIndicadorActivo(Constants.NUMERO_UNO);
		else
			f.setIndicadorActivo(Constants.NUMERO_CERO);

		if (_indicadorGestionUsuario)
			f.setIndicadorGestionUsuario(Constants.NUMERO_UNO);
		else
			f.setIndicadorGestionUsuario(Constants.NUMERO_CERO);
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");

		List listPlantilla = new ArrayList();
		List listPlantillaDetalle = new ArrayList();
		List listParametrosPlantilla = new ArrayList();
		listParametrosPlantilla = this.msgParametrosPlantillaList;
		Iterator it = listParametrosPlantilla.iterator();
		// anhadiendo la cabera
		Map mapCabecera = new HashMap();
		mapCabecera.put("codigoPlantilla", f.getCodigoPlantilla());
		mapCabecera.put("descripcionPlantilla", f.getDescripcion());
		mapCabecera.put("observacion", f.getObservaciones());
		mapCabecera.put("estadoActivo", f.getIndicadorActivo());
		mapCabecera.put("indicadorGestionUsuario",
				f.getIndicadorGestionUsuario());
		mapCabecera.put("mapDetallePlantilla", listPlantillaDetalle);
		listPlantilla.add(mapCabecera);
		// se reorrerar la lista de parametos de los tipo de datos
		int index = 0;
		while (it.hasNext()) {
			Map mapDato = (Map) it.next();
			if (index == 1) {
				String d = "";
			}

			String tipoDato = (String) mapDato.get("tipoDato");

			if (StringUtils.equals(TIPO_DATO_COMBOBOX, tipoDato)) {
				setearListaPlantillaCombo(listPlantillaDetalle, mapDato, f,
						index);
			}
			if (StringUtils.equals(TIPO_DATO_TEXTBOX, tipoDato)) {
				setearListaPlantillaText(listPlantillaDetalle, mapDato, f,
						index);
			}
			if (StringUtils.equals(TIPO_DATO_POPUP, tipoDato)) {
				setearListaPlantillaPopup(listPlantillaDetalle, mapDato, f,
						index);
			}
			index++;
		}

		if (this.accion.equals(this.ACCION_MODIFICAR)) {
			service.updatePlantilla(listPlantilla, usuario);
			bGrabar = true;
		} else if (this.accion.equals(this.ACCION_NUEVO)) {
			service.insertPlantilla(listPlantilla, usuario);
			bGrabar = true;
		}

		Map map = new HashMap();
		map.put("codigoPlantilla", f.getCodigoPlantilla());
		map.put("descripcionPlantilla", f.getDescripcion());
		map.put("observacion", f.getObservaciones());
		map.put("indicadorActivo",
				f.getIndicadorActivo().equals(Constants.NUMERO_CERO) ? null
						: Constants.NUMERO_UNO);
		map.put("indicadorGestionUsuario", f.getIndicadorGestionUsuario()
				.equals(Constants.NUMERO_CERO) ? null : Constants.NUMERO_UNO);
		List listPlantillaCabec = service.getPlantillaCabecera(map);
		this.msgPlanillaCabecList = listPlantillaCabec;
		return bGrabar;
	}

	public void setDetallesPlantilla(List listDetalle) throws ParseException {
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");

		List listTemporalDetalle = new ArrayList();

		// Quitamos las filas que tienen combo Multiple
		for (int y = 0; y < listDetalle.size(); y++) {
			Map mapDato = (Map) listDetalle.get(y);
			String indMultiple = mapDato.get("indMultiple").toString();
			String numeroCorrelativo = mapDato.get("numCorrelativo").toString();
			if (Integer.parseInt(indMultiple) == 1
					|| (Integer.parseInt(indMultiple) > 1 && Integer
							.parseInt(numeroCorrelativo) == 1))
				listTemporalDetalle.add(mapDato);

		}

		for (int j = 0; j < listParametrosPlantilla.size(); j++) {
			Map map = (Map) listParametrosPlantilla.get(j);
			Map mapDato = (Map) listTemporalDetalle.get(j);

			LabelValue[] listaValoresPosibles = (LabelValue[]) map
					.get("listaValoresPosibles");
			String valorDefecto = (String) mapDato.get("valorDefecto");
			String indVisible = (String) mapDato.get("indicadorVisible");
			String label = (String) mapDato.get("label");
			String numOrden = mapDato.get("numOrden").toString();
			String indicadorFecha = (String) map.get("indicadorFecha");
			mapDato.remove("indicadorVisible");
			String indicadorObligatorio = (String) mapDato
					.get("indicadorObligatorio");
			mapDato.remove("indicadorObligatorio");

			String indicadorModificado = (String) mapDato
					.get("indicadorModificado");
			mapDato.remove("indicadorModificado");

			if (indVisible.equals("1"))
				map.put("indicadorVisible", true);
			else
				map.put("indicadorVisible", false);

			if (indicadorObligatorio.equals("1"))
				map.put("indicadorObligatorio", true);
			else
				map.put("indicadorObligatorio", false);

			if (indicadorModificado.equals("1"))
				map.put("indicadorModificable", true);
			else
				map.put("indicadorModificable", false);

			map.put("label", label);
			map.put("ordenPresentacion", numOrden.equals("99") ? "" : numOrden);

			if (valorDefecto != null) {
				if (map.get("indicadorHora").toString().equals("1")) {
					map.put("hora", valorDefecto.substring(0, 2));
					map.put("minuto", valorDefecto.substring(3, 5));
					map.put("tiempo", valorDefecto.substring(6));

				}
				map.put("campanha", valorDefecto);
				if (indicadorFecha.equals(Constants.NUMERO_UNO))
					map.put("fechaD",
							DateUtil.convertStringToDate(valorDefecto));
				LabelValue[] valoresDefectos = new LabelValue[valorDefecto
						.split(",").length];

				String tipoDato = (String) map.get("tipoDato");

				if (tipoDato.equals("POPUP")) {
					String valorPosible = (String) map.get("valorPosible");
					for (int i = 0; i < valorDefecto.split(",").length; i++) {
						LabelValue labelValue = new LabelValue();
						String valorDefectoValue = valorDefecto.split(",")[i];
						Map criteria = new HashMap();
						criteria.put("valorPosible", valorPosible);
						criteria.put("oid", valorDefectoValue);
						List resultado = service.getSelectPlantilla(criteria);
						Map mapAux = (Map) resultado.get(0);
						labelValue.setValue(valorDefectoValue);
						String descripcion = "";
						if (StringUtils.isNotBlank((String) mapAux
								.get("descripcion"))) {
							descripcion = mapAux.get("descripcion").toString();
						}
						labelValue.setLabel(mapAux.get("codigo").toString()
								+ "-" + descripcion);
						valoresDefectos[i] = labelValue;
					}
				} else {
					for (int i = 0; i < valorDefecto.split(",").length; i++) {
						LabelValue labelValue = new LabelValue();
						String valorDefectoValue = valorDefecto.split(",")[i];
						for (int k = 0; k < listaValoresPosibles.length; k++) {
							if (listaValoresPosibles[k].getValue().equals(
									valorDefectoValue)) {
								labelValue.setLabel(listaValoresPosibles[k]
										.getLabel());
								break;
							}
						}

						labelValue.setValue(valorDefectoValue);
						valoresDefectos[i] = labelValue;
					}
				}
				map.put("listaValoresDefectos", valoresDefectos);
			} else {
				map.put("listaValoresDefectos", null);
				map.put("campanha", "");
				map.put("fechaD", null);
			}
		}

		// AGREGAMOS LOS VALORES POR DEFECTO DEL COMBO MULTIPLE
		LabelValue[] listavalores = null;
		listaComboMultiple = new ArrayList();
		List listaParametro = new ArrayList();
		for (int i = 0; i < listDetalle.size(); i++) {
			Map map = (Map) listDetalle.get(i);
			Map mapAux = new HashMap();
			int indMultiple = ((BigDecimal) map.get("indMultiple")).intValue();
			if (indMultiple > 1) {
				String valorDefecto = (String) map.get("valorDefecto");
				String valorListaTabla=(String) map.get("valorListaTabla");
				mapAux.put("valorPosible", valorListaTabla);
				int tamanio = valorDefecto.split(",").length;
				listavalores = new LabelValue[tamanio];
				for (int j = 0; j < tamanio; j++) {
					LabelValue data = new LabelValue();
					mapAux.put("oid", valorDefecto.split(",")[j]);
					listaParametro = service.getSelectPlantilla(mapAux);
					Map dato = (Map)listaParametro.get(0);
					data.setLabel(dato.get("descripcion").toString());
					data.setValue(valorDefecto.split(",")[j]);	
					listavalores[j]=data;
				}
				listaComboMultiple.add(listavalores);
			}
		}
		String sd = "";
		Iterator it = msgParametrosPlantillaList.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String indicadorMultiple = map.get("indicadorMultiple").toString();
			if (indicadorMultiple.equals("1"))
				map.put("listaValoresDefectosMultiples", listaComboMultiple);
		}
	}

	/**
	 * Setea los tipos de datos Combo
	 * 
	 * @param listPlantillaDetalle
	 * @param request
	 * @param mapDato
	 * @param f
	 * @param index
	 * @param valoresDefecto
	 */
	private void setearListaPlantillaCombo(List listPlantillaDetalle,
			Map mapDato, MantenimientoMENPlantillaConfiguracionProcesosForm f,
			int index) {
		boolean isComboSimple = true;
		Map mapDetalle = new HashMap();

		String label = (String) mapDato.get("label");
		String orden = (String) mapDato.get("ordenPresentacion");
		String indicadorVisible = Constants.NUMERO_CERO;
		String indicadorObligatorio = Constants.NUMERO_CERO;
		String indicadorModificable = Constants.NUMERO_CERO;
		LabelValue[] hdvalorDefecto = (LabelValue[]) mapDato
				.get("listaValoresDefectos");

		if (mapDato.get("indicadorVisible") != null) {
			if (mapDato.get("indicadorVisible").toString().equals("true"))
				indicadorVisible = Constants.NUMERO_UNO;
		}

		if (mapDato.get("indicadorObligatorio") != null) {
			if (mapDato.get("indicadorObligatorio").toString().equals("true"))
				indicadorObligatorio = Constants.NUMERO_UNO;
		}

		if (mapDato.get("indicadorModificable") != null) {
			if (mapDato.get("indicadorModificable").toString().equals("true"))
				indicadorModificable = Constants.NUMERO_UNO;
		}

		String codigoTipoDato = (String) mapDato.get("codigoTipoDato");
		// int numCorrelativo =1;
		//
		mapDetalle.put("label", label);
		mapDetalle.put("ordenPresentacion", StringUtils.isEmpty(orden) ? "99"
				: orden);
		mapDetalle.put("indicadorVisible", indicadorVisible);
		mapDetalle.put("indicadorModificable", indicadorObligatorio);
		mapDetalle.put("indicadorObligatorio", indicadorModificable);
		mapDetalle.put("codigoTipoDato", codigoTipoDato);
		mapDetalle.put("codigoPlantilla", f.getCodigoPlantilla());
		mapDetalle.put("valorTabla", mapDato.get("valorPosible"));

		String cad = "";
		String indicadorMultiple = (String) mapDato.get("indicadorMultiple");
		if (indicadorMultiple.equals("1")) {
			String wefwe = "";
		}
		List listComboMultiple = null;

		// SI ES COMBO MULTIPLE
		if (hdvalorDefecto != null
				&& StringUtils.equals(indicadorMultiple, Constants.NUMERO_UNO)) {
			isComboSimple = false;
			String valorPosible = (String) mapDato.get("valorPosible");
			if (listaComboMultiple != null)
				for (int i = 0; i < listaComboMultiple.size(); i++) {
					if (listaComboMultiple.get(i) != null) {

						LabelValue[] lista = (LabelValue[]) listaComboMultiple
								.get(i);
						for (int j = 0; j < lista.length; j++)
							cad += lista[j].getValue() + ",";

						int pos = cad.lastIndexOf(",");
						if (StringUtils.isNotEmpty(cad))
							cad = cad.substring(0, pos);

						mapDetalle = new HashMap();
						mapDetalle.put("label", label);
						mapDetalle.put("ordenPresentacion",
								StringUtils.isEmpty(orden) ? "99" : orden);
						mapDetalle.put("indicadorVisible", indicadorVisible);
						mapDetalle.put("indicadorModificable",
								indicadorObligatorio);
						mapDetalle.put("indicadorObligatorio",
								indicadorModificable);
						mapDetalle.put("codigoTipoDato", codigoTipoDato);
						mapDetalle.put("codigoPlantilla",
								f.getCodigoPlantilla());

						mapDetalle
								.put("valorTabla", valorPosible.split(",")[i]);
						mapDetalle.put("valoresDefecto", cad);
						if (existeCodigoInLista(f.getCodigoPlantilla(),
								codigoTipoDato, listPlantillaDetalle)) {
							mapDetalle.put("numeroCorrelativo",
									correlativoComboMultiple++);
						} else {
							correlativoComboMultiple = 1;
							mapDetalle.put("numeroCorrelativo",
									correlativoComboMultiple++);
						}
						cad = "";
						listPlantillaDetalle.add(mapDetalle);
					}
				}
		}

		// SI ES COMBO DE SIMPLE
		if (isComboSimple) {
			if (hdvalorDefecto != null)
				for (int j = 0; j < hdvalorDefecto.length; j++)
					cad += hdvalorDefecto[j].getValue() + ",";

			int pos = cad.lastIndexOf(",");
			if (StringUtils.isNotEmpty(cad))
				cad = cad.substring(0, pos);

			mapDetalle.put("valoresDefecto", cad);

			if (existeCodigoInLista(f.getCodigoPlantilla(), codigoTipoDato,
					listPlantillaDetalle)) {
				mapDetalle.put("numeroCorrelativo", correlativoComboMultiple++);
			} else {
				correlativoComboMultiple = 1;
				mapDetalle.put("numeroCorrelativo", correlativoComboMultiple++);
			}

			listPlantillaDetalle.add(mapDetalle);

		}
	}

	/**
	 * Setea los tipos de datos Text
	 * 
	 * @param listPlantillaDetalle
	 * @param request
	 * @param mapDato
	 * @param f
	 * @param index
	 * @param valoresDefecto
	 */
	private void setearListaPlantillaText(List listPlantillaDetalle,
			Map mapDato, MantenimientoMENPlantillaConfiguracionProcesosForm f,
			int index) {
		Map mapDetalle = new HashMap();
		String indicadorCampanha = (String) mapDato.get("indicadorCampanha");
		String indicadorFecha = (String) mapDato.get("indicadorFecha");
		String indicadorHora = (String) mapDato.get("indicadorHora");
		String indicadorVisible = Constants.NUMERO_CERO;
		String indicadorObligatorio = Constants.NUMERO_CERO;
		String indicadorModificable = Constants.NUMERO_CERO;
		if (mapDato.get("indicadorVisible") != null) {
			if (mapDato.get("indicadorVisible").toString().equals("true"))
				indicadorVisible = Constants.NUMERO_UNO;
		}

		if (mapDato.get("indicadorObligatorio") != null) {
			if (mapDato.get("indicadorObligatorio").toString().equals("true"))
				indicadorObligatorio = Constants.NUMERO_UNO;
		}

		if (mapDato.get("indicadorModificable") != null) {
			if (mapDato.get("indicadorModificable").toString().equals("true"))
				indicadorModificable = Constants.NUMERO_UNO;
		}
		String label = (String) mapDato.get("label");
		String orden = (String) mapDato.get("ordenPresentacion");
		String codigoTipoDato = (String) mapDato.get("codigoTipoDato");

		int numCorrelativo = 1;
		//
		mapDetalle.put("label", label);
		mapDetalle.put("ordenPresentacion", StringUtils.isEmpty(orden) ? "99"
				: orden);

		mapDetalle.put("indicadorVisible", indicadorVisible);
		mapDetalle.put("indicadorModificable", indicadorModificable);
		mapDetalle.put("indicadorObligatorio", indicadorObligatorio);
		mapDetalle.put("codigoTipoDato", codigoTipoDato);
		mapDetalle.put("numeroCorrelativo", numCorrelativo);
		mapDetalle.put("codigoPlantilla", f.getCodigoPlantilla());

		// CUANDO ES CAMPAÑA
		if (StringUtils.equals(Constants.NUMERO_UNO, indicadorCampanha)) {
			String campanha = mapDato.get("campanha").toString();
			mapDetalle.put("valoresDefecto", campanha);
		}
		if (StringUtils.equals(Constants.NUMERO_UNO, indicadorFecha)) {
			Date fechaD = (Date) mapDato.get("fechaD");
			if (fechaD != null)
				mapDetalle.put("valoresDefecto",
						DateUtil.convertDateToString(fechaD));
		}
		if (StringUtils.equals(Constants.NUMERO_UNO, indicadorHora)) {
			String hora = mapDato.get("hora").toString();
			String minuto = mapDato.get("minuto").toString();
			String tiempo = mapDato.get("tiempo").toString();
			mapDetalle
					.put("valoresDefecto", hora + ":" + minuto + ":" + tiempo);
		}
		if (StringUtils.equals(Constants.NUMERO_CERO, indicadorHora)
				&& StringUtils.equals(Constants.NUMERO_CERO, indicadorFecha)
				&& StringUtils.equals(Constants.NUMERO_CERO, indicadorCampanha)) {
			mapDetalle
					.put("valoresDefecto", (String) mapDato.get("textoLabel"));
		}

		listPlantillaDetalle.add(mapDetalle);
	}

	/**
	 * Returna true si encuentra codigo deplantilla y tipo de dato en la lista
	 * de detalles caso contraio false
	 * 
	 * @param codigoPlantilla
	 * @param codigoTipoDato
	 * @param listPlantillaDetalle
	 * @return
	 */
	private boolean existeCodigoInLista(String codigoPlantilla,
			String codigoTipoDato, List listPlantillaDetalle) {
		Iterator it = listPlantillaDetalle.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String plantilla = (String) map.get("codigoPlantilla");
			String tipoDato = (String) map.get("codigoTipoDato");

			if (StringUtils.equals(plantilla, codigoPlantilla)
					&& StringUtils.equals(tipoDato, codigoTipoDato)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		MantenimientoMENPlantillaConfiguracionProcesosForm f = new MantenimientoMENPlantillaConfiguracionProcesosForm();
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		listParametrosPlantilla = service.getParametrosPlantilla();
		setearParametrosMultiples(listParametrosPlantilla);
		setearParametrosSimples(listParametrosPlantilla);
		setearCombosMultiples(listParametrosPlantilla);
		// seteamos la lista de time
		setearListaHora();
		this.msgParametrosPlantillaList = listParametrosPlantilla;
		if (this.accion.equals(this.ACCION_NUEVO)) {

			this.msgValorDefectoList = new ArrayList();
			this.msgValorDefectoPopupList = new ArrayList();
			f.setNewRecord(true);

		} else if (this.accion.equals(this.ACCION_MODIFICAR)) {

			Map data = (Map) this.beanRegistroSeleccionado;

			if (data != null) {
				try {
					List list = this.msgPlanillaCabecList;
					List listPlantilla = this.msgParametrosPlantillaList;
					f.setCodigoPais(codigoPais);
					f.setCodigoPlantilla((String) data.get("codigoPlantilla"));
					f.setDescripcion((String) data.get("descripcion"));
					f.setObservaciones((String) data.get("observaciones"));
					f.setIndicadorActivo((String) data.get("indicadorActivo"));
					f.setIndicadorGestionUsuario((String) data
							.get("indicadorGestionUsuario"));
					f.setNewRecord(false);
					// recuperamos los detalles
					List listDetalle = service.getPlantillaDetalle(data);
					setDetallesPlantilla(listDetalle);
					log.debug("enviando para editar " + listPlantilla.size());
				} catch (Exception e) {

				}
			}
		}
		this.msgParametrosPlantillaList = listParametrosPlantilla;
		return f;
	}

	public void setearValoresDefectos(List listParametrosPlantilla, List detalle) {
		Iterator it2 = detalle.iterator();
		while (it2.hasNext()) {
			Map mapDato2 = (Map) it2.next();
			String valorDefecto = (String) mapDato2.get("valorDefecto");
			if (valorDefecto != null)
				mapDato2.put("valoresDefecto", valorDefecto);
			else
				mapDato2.put("valoresDefecto", null);
		}

		Iterator it = listParametrosPlantilla.iterator();
		while (it.hasNext()) {
			Map mapDato = (Map) it.next();
			LabelValue[] valoresDefectos = new LabelValue[MSG_CANTIDAD_SELECCION_TOTAL];

			while (it2.hasNext()) {
				Map mapDato2 = (Map) it2.next();
				String valorDefecto = (String) mapDato2.get("valorDefecto");
				if (valorDefecto != null) {
					int cantValoresDesfectos = valorDefecto.split(",").length;

					for (int i = 0; i < cantValoresDesfectos; i++) {
						LabelValue labelValue = new LabelValue();
						labelValue.setLabel(valorDefecto.split(",")[i]);
						labelValue.setValue(valorDefecto.split(",")[i]);
						valoresDefectos[i] = labelValue;
						//
					}

				}
			}

			mapDato.put("listaValoresDefectos", valoresDefectos);
		}
	}

	/**
	 * Setea los tipos de datos Popup
	 * 
	 * @param listPlantillaDetalle
	 * @param request
	 * @param mapDato
	 * @param f
	 * @param index
	 * @param valoresDefecto
	 */
	private void setearListaPlantillaPopup(List listPlantillaDetalle,
			Map mapDato, MantenimientoMENPlantillaConfiguracionProcesosForm f,
			int index) {

		Map mapDetalle = new HashMap();
		String indicadorVisible = Constants.NUMERO_CERO;
		String indicadorObligatorio = Constants.NUMERO_CERO;
		String indicadorModificable = Constants.NUMERO_CERO;
		LabelValue[] hdvalorDefecto = (LabelValue[]) mapDato
				.get("listaValoresDefectos");

		if (mapDato.get("indicadorVisible") != null) {
			if (mapDato.get("indicadorVisible").toString().equals("true"))
				indicadorVisible = Constants.NUMERO_UNO;
		}

		if (mapDato.get("indicadorObligatorio") != null) {
			if (mapDato.get("indicadorObligatorio").toString().equals("true"))
				indicadorObligatorio = Constants.NUMERO_UNO;
		}

		if (mapDato.get("indicadorModificable") != null) {
			if (mapDato.get("indicadorModificable").toString().equals("true"))
				indicadorModificable = Constants.NUMERO_UNO;
		}
		String label = (String) mapDato.get("label");
		String orden = (String) mapDato.get("ordenPresentacion");
		String codigoTipoDato = (String) mapDato.get("codigoTipoDato");
		String valTabla = (String) mapDato.get("valorPosible");
		int numCorrelativo = 1;

		mapDetalle.put("label", label);
		mapDetalle.put("ordenPresentacion", StringUtils.isEmpty(orden) ? "99"
				: orden);
		mapDetalle.put("indicadorVisible", indicadorVisible);
		mapDetalle.put("indicadorModificable", indicadorModificable);
		mapDetalle.put("indicadorObligatorio", indicadorObligatorio);
		mapDetalle.put("codigoTipoDato", codigoTipoDato);
		mapDetalle.put("numeroCorrelativo", numCorrelativo);
		mapDetalle.put("codigoPlantilla", f.getCodigoPlantilla());
		mapDetalle.put("valorTabla", valTabla);
		String cad = "";
		//
		if (hdvalorDefecto != null)
			for (int j = 0; j < hdvalorDefecto.length; j++) {
				String valor = hdvalorDefecto[j].getValue();
				cad += valor + ",";

			}

		int pos = cad.lastIndexOf(",");
		if (StringUtils.isNotEmpty(cad))
			cad = cad.substring(0, pos);
		mapDetalle.put("valoresDefecto", cad);

		listPlantillaDetalle.add(mapDetalle);
	}

	/**
	 * Realiza el seteo de los detalle de planilla
	 * 
	 * @param listPlantilla
	 * @param listDetalle
	 * @param f
	 * @return
	 */
	private List setDetallesPlantilla(List listPlantilla, List listDetalle,
			MantenimientoMENPlantillaConfiguracionProcesosForm f) {
		List listValores = new ArrayList();
		// Iterator it = listDetalle.iterator();
		Iterator itPlantilla = listPlantilla.iterator();
		String idListaDefecto = "valorDefecto";
		String idListaDefectoPopup = "idListaDefectoPopup";
		String idLabel = "label";
		String idOrden = "orden";
		String idIndicadorVisible = "indicadorVisible";
		String idIndicadorModi = "indicadorModi";
		String idIndicadorObi = "indicadorObi";
		String idCampanha = "campanha";
		String idFecha = "fecha";
		String idTextoLabel = "textoLabel";
		String idSeleccionHora = "seleccionHoraInicio";
		String idSeleccionMinuto = "seleccionMinutoInicio";
		String idSeleccionTime = "seleccionTiempoInicio";

		int pos = -1;
		while (itPlantilla.hasNext()) {
			Map mapPlantilla = (Map) itPlantilla.next();
			pos++;
			String codigoTipoDatoPlantilla = (String) mapPlantilla
					.get("codigoTipoDato");
			String tipoDato = (String) mapPlantilla.get("tipoDato");
			String indicadorCampanha = (String) mapPlantilla
					.get("indicadorCampanha");
			String indicadorFecha = (String) mapPlantilla.get("indicadorFecha");
			String indicadorHora = (String) mapPlantilla.get("indicadorHora");

			for (int j = 0; j < listDetalle.size(); j++) {
				Map map = (Map) listDetalle.get(j);
				String codigoTipoDato = (String) map.get("codigoTipoDato");

				if (StringUtils.equals(codigoTipoDatoPlantilla, codigoTipoDato)) {
					String indicadorVisible = (String) map
							.get("indicadorVisible");
					String indicadorObligatorio = (String) map
							.get("indicadorObligatorio");
					String indicadorModificable = (String) map
							.get("indicadorModificado");
					String label = (String) map.get("label");

					String valorDefecto = (String) map.get("valorDefecto");
					String valorListaTabla = (String) map
							.get("valorListaTabla");
					int indMultiple = ((BigDecimal) map.get("indMultiple"))
							.intValue();
					int numOrden = ((BigDecimal) map.get("numOrden"))
							.intValue();

					BaseMensaje base = new BaseMensaje();
					base.setId(idIndicadorVisible + pos);
					base.setValor(indicadorVisible);
					base.setTipoDato(TIPO_DATO_CHKBOX);
					listValores.add(base);
					base = new BaseMensaje();
					base.setId(idIndicadorObi + pos);
					base.setValor(indicadorObligatorio);
					base.setTipoDato(TIPO_DATO_CHKBOX);
					listValores.add(base);
					base = new BaseMensaje();
					base.setId(idIndicadorModi + pos);
					base.setValor(indicadorModificable);
					base.setTipoDato(TIPO_DATO_CHKBOX);
					listValores.add(base);

					base = new BaseMensaje();
					base.setId(idLabel + pos);
					base.setValor(label);
					base.setTipoDato(TIPO_DATO_TEXTBOX);
					listValores.add(base);

					base = new BaseMensaje();
					base.setId(idOrden + pos);
					base.setValor(numOrden == 99 ? "" : String
							.valueOf(numOrden));
					base.setTipoDato(TIPO_DATO_TEXTBOX);
					listValores.add(base);

					if (StringUtils.equals(TIPO_DATO_COMBOBOX, tipoDato)) {

						if (indMultiple > 1) {// SE TRATA DE COMBO MULTIPLE
							int index1 = j;
							int index2 = 0;
							for (int i = index1; i < index1 + indMultiple; i++) {
								Map mapMult = (Map) listDetalle.get(i);
								// if(i!=index1) itPlantilla.next();//ya se
								// tiene el primer objeto del multiple, es x eso
								// que aprtir del segundo se va sacando
								log.debug("index " + i);
								valorDefecto = (String) mapMult
										.get("valorDefecto");
								base = new BaseMensaje();
								base.setId(idListaDefecto + pos + "_" + index2);
								base.setValor(valorDefecto);
								base.setTipoDato(TIPO_DATO_COMBOBOX);
								listValores.add(base);
								index2++;
							}
						} else {// COMBO BOX SIMPLE
							base = new BaseMensaje();
							base.setId(idListaDefecto + pos);
							base.setValor(valorDefecto);
							base.setTipoDato(TIPO_DATO_COMBOBOX);
							listValores.add(base);
						}
					}
					if (StringUtils.equals(TIPO_DATO_TEXTBOX, tipoDato)) {
						if (StringUtils.equals(Constants.NUMERO_UNO,
								indicadorCampanha)) {

							base = new BaseMensaje();
							base.setId(idCampanha + pos);
							base.setValor(valorDefecto);
							base.setTipoDato(TIPO_DATO_TEXTBOX);
							listValores.add(base);
						}
						if (StringUtils.equals(Constants.NUMERO_UNO,
								indicadorFecha)) {

							base = new BaseMensaje();
							base.setId(idFecha + pos);
							base.setValor(valorDefecto);
							base.setTipoDato(TIPO_DATO_TEXTBOX);
							listValores.add(base);
						}
						if (StringUtils.equals(Constants.NUMERO_UNO,
								indicadorHora)) {

							base = new BaseMensaje();
							base.setId(idSeleccionHora + pos);
							base.setValor(valorDefecto.substring(0, 2));
							base.setTipoDato(TIPO_DATO_TEXTBOX);
							listValores.add(base);
							base = new BaseMensaje();
							base.setId(idSeleccionMinuto + pos);
							base.setValor(valorDefecto.substring(3, 5));
							base.setTipoDato(TIPO_DATO_TEXTBOX);
							listValores.add(base);
							base = new BaseMensaje();
							base.setId(idSeleccionTime + pos);
							base.setValor(valorDefecto.substring(6));
							base.setTipoDato(TIPO_DATO_TEXTBOX);
							listValores.add(base);
						}
						if (StringUtils.equals(Constants.NUMERO_CERO,
								indicadorHora)
								&& StringUtils.equals(Constants.NUMERO_CERO,
										indicadorFecha)
								&& StringUtils.equals(Constants.NUMERO_CERO,
										indicadorCampanha)) {

							base = new BaseMensaje();
							base.setId(idTextoLabel + pos);
							base.setValor(valorDefecto);
							base.setTipoDato(TIPO_DATO_TEXTBOX);
							listValores.add(base);
						}
					}
					if (StringUtils.equals(TIPO_DATO_POPUP, tipoDato)) {
						base = new BaseMensaje();
						base.setId(idListaDefectoPopup + pos);
						base.setValor(valorDefecto);
						base.setTipoDato(TIPO_DATO_POPUP);
						base.setValorTabla(valorListaTabla);

						if (StringUtils.equals("MAE_PRODU", valorListaTabla)) {
							// se arma la lista de MAP si valor is not null
							if (StringUtils.isNotEmpty(valorDefecto)) {
								List list = getListMapProd(valorDefecto);// Map
																			// de
																			// oid,codigo,descripcion
								base.setArrProducto(list);
							}
						}
						listValores.add(base);
					}

					break;
				}

			}
		}

		return listValores;
	}

	/**
	 * Retorna la lista de Productos
	 * 
	 * @param valorDefecto
	 * @return
	 */
	private List getListMapProd(String valorDefecto) {
		List list = new ArrayList();
		String[] oids = StringUtils.split(valorDefecto, ",");
		Map map = new HashMap();
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		map.put("valorPosible", "MAE_PRODU");
		for (int i = 0; i < oids.length; i++) {
			map.put("oid", oids[i]);
			List listPrd = service.getSelectPlantilla(map);
			Iterator it = listPrd.iterator();
			while (it.hasNext()) {
				Map res = (Map) it.next();
				Map mapResultado = new HashMap();
				mapResultado.put("oid", String.valueOf(res.get("oid")));
				mapResultado.put("codigo", String.valueOf(res.get("codigo")));
				mapResultado.put("descripcion",
						String.valueOf(res.get("descripcion")));
				list.add(mapResultado);
			}
		}
		return list;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		MantenimientoMENPlantillaConfiguracionProcesosSearchForm f = (MantenimientoMENPlantillaConfiguracionProcesosSearchForm) this.formBusqueda;
		f.setCodigoPais(codigoPais);
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		_indicadorActivo = false;
		_indicadorGestionUsuario = false;
		this.mostrarBotonConsultar = false;
		listaComboMultiple = new ArrayList();
		salirGrabarPantallaPadre = true;

	}

	/**
	 * Setea los parametros multiples
	 * 
	 * @param listParametrosPlantilla
	 */
	private void setearParametrosSimples(List listParametrosPlantilla) {
		Iterator it = listParametrosPlantilla.iterator();
		List listParametrosSimples = new ArrayList();
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String codigoTipoDato = (String) map.get("codigoTipoDato");
			String valorPosible = (String) map.get("valorPosible");
			String indicadorTabla = (String) map.get("indicadorTabla");
			String tipoDato = (String) map.get("tipoDato");

			Map mapAux = new HashMap();
			mapAux.put("valorPosible", valorPosible);

			if (valorPosible != null && !tipoDato.equals("POPUP")) {
				if (valorPosible.equals("CAMPAÑA,FECHAS")) {
					int z = 0;
					valoresPosibles = new LabelValue[valorPosible.split(",").length];
					for (String string : valorPosible.split(",")) {
						LabelValue labelValue = new LabelValue();
						labelValue.setLabel(valorPosible.split(",")[z]);
						labelValue.setValue(valorPosible.split(",")[z]);
						valoresPosibles[z] = labelValue;
						map.put("listaValoresPosibles", valoresPosibles);
						z++;
					}
				} else {
					listParametrosSimples = service.getSelectPlantilla(mapAux);
					if (listParametrosSimples.size() > 1) {
						Iterator it2 = listParametrosSimples.iterator();
						int z = 0;
						valoresPosibles = new LabelValue[listParametrosSimples
								.size()];

						while (it2.hasNext()) {
							Map map2 = (Map) it2.next();
							String descripcion = (String) map2
									.get("descripcion");
							String oid = map2.get("oid").toString();
							LabelValue labelValue = new LabelValue();
							labelValue.setLabel(descripcion);
							labelValue.setValue(oid);
							valoresPosibles[z] = labelValue;
							z++;
						}
						map.put("listaValoresPosibles", valoresPosibles);
					} else {
						map.put("listaValoresPosibles", valoresPosibles);
					}
				}

			} else {
				map.put("listaValoresPosibles", valoresPosibles);
			}
			map.put("hdvalorPosible", hdvalorPosible);
			map.put("hdvalorDefecto", hdvalorDefecto);
			map.put("listaValoresDefectos", valoresDefectos);
			map.put("fecha", fechaD);
		}

	}

	/**
	 * Setea los parametros multiples
	 * 
	 * @param listParametrosPlantilla
	 */
	private void setearParametrosMultiples(List listParametrosPlantilla) {
		Iterator it = listParametrosPlantilla.iterator();
		listParametrosMultiple = new ArrayList();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String valorPosible = (String) map.get("valorPosible");
			log.debug("setearParametrosMultiples valorPosible " + valorPosible);
			String indicadorTabla = (String) map.get("indicadorTabla");
			map.put("indicadorMultiple", Constants.NUMERO_CERO);

			if (StringUtils.isNotEmpty(valorPosible)
					&& StringUtils.equals(indicadorTabla, Constants.NUMERO_UNO)) {
				String[] arr = StringUtils.split(valorPosible, ",");
				if (arr != null && arr.length > 1) {
					for (int i = 0; i < arr.length; i++)
						listParametrosMultiple.add(arr[i]);

					map.put("indicadorMultiple", Constants.NUMERO_UNO);
				}
			}
			map.put("listParametrosMultiple", listParametrosMultiple);
		}
	}

	private void setearCombosMultiples(List listParametrosPlantilla) {
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		LabelValue[] listavalores = null;
		for (int j = 0; j < listParametrosPlantilla.size(); j++) {
			Map map3 = (Map) listParametrosPlantilla.get(j);
			List listaParametro = new ArrayList();
			String indicadorMultiple = (String) map3.get("indicadorMultiple");
			List lista = new ArrayList();
			if (indicadorMultiple.equals("1")) {
				String valorPosible = (String) map3.get("valorPosible");
				Map map = new HashMap();
				List listaA = new ArrayList();
				for (int i = 0; i < valorPosible.split(",").length; i++) {
					Map mapAux3 = new HashMap();

					mapAux3.put("valorPosible", valorPosible.split(",")[i]);
					listaParametro = service.getSelectPlantilla(mapAux3);

					if (listaParametro.size() > 1) {
						listavalores = new LabelValue[listaParametro.size() + 1];
						for (int k = 0; k < listaParametro.size(); k++) {
							Map map4 = (Map) listaParametro.get(k);
							String descripcion = (String) map4
									.get("descripcion");
							String oid = map4.get("oid").toString();
							LabelValue labelValue = new LabelValue();
							labelValue.setLabel(descripcion);
							labelValue.setValue(oid);
							listavalores[k] = labelValue;

						}

						lista.add(listavalores);
					}
				}
			}

			map3.put("listCombosMultiples", lista);
		}

	}

	/**
	 * Manda en session la lista de parametros que estan configurados como popup
	 * 
	 * @param session
	 * @param listParametrosPlantilla
	 */
	private void setearListPopup(List listParametrosPlantilla) {
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		Iterator it = listParametrosPlantilla.iterator();
		List listPopup = new ArrayList();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String valorPosible = (String) map.get("valorPosible");
			String tipoDato = (String) map.get("tipoDato");

			if (TIPO_DATO_POPUP.equals(tipoDato)) {
				if (!"MAE_PRODU".equals(valorPosible)) {
					List list = service.getSelectPlantilla(map);
					listPopup.addAll(list);
				}

			}
		}
		this.msgVistaPlantillaPopupList = listPopup;
	}

	/**
	 * Manda a session la lista de hora , minuto y tiempo AM,PM
	 * 
	 * @param session
	 */
	private void setearListaHora() {
		String[] stringHora = Constants.EDU_HORA_LIST;
		List listaHora = new ArrayList();
		for (int i = 0; i < stringHora.length; i++) {
			Base base = new Base();
			base.setCodigo(stringHora[i]);
			base.setDescripcion(stringHora[i]);
			listaHora.add(base);
		}
		String[] stringMin = getListaMinutos();
		List listaMin = new ArrayList();
		for (int i = 0; i < stringMin.length; i++) {
			Base base = new Base();
			base.setCodigo(stringMin[i]);
			base.setDescripcion(stringMin[i]);
			listaMin.add(base);
		}
		String[] stringTiempo = Constants.EDU_TIEMPO_AM_PM_LIST;
		List listaTiempo = new ArrayList();
		for (int i = 0; i < stringTiempo.length; i++) {
			Base base = new Base();
			base.setCodigo(stringTiempo[i]);
			base.setDescripcion(stringTiempo[i]);
			listaTiempo.add(base);
		}
		menHoraActividadList = listaHora;
		menMinutoActividadList = listaMin;
		menTiempoAmPmActividadList = listaTiempo;
	}

	/**
	 * Devuelve la lista de minutos
	 * 
	 * @return
	 */
	private String[] getListaMinutos() {
		String[] minuto = new String[61];
		for (int i = 0; i < minuto.length; i++)
			minuto[i] = StringUtils.leftPad(String.valueOf(i), 2, '0');
		return minuto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMENPlantillaConfiguracionProcesosForm form = (MantenimientoMENPlantillaConfiguracionProcesosForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoMENPlantillaConfiguracionProcesosForm.cabecera.insert";
		} else {
			return "mantenimientoMENPlantillaConfiguracionProcesosForm.cabecera.update";
		}
	}

	public void agregarValores(ValueChangeEvent val) {
		String[] valores = (String[]) val.getNewValue();

		// CAPTURA EL CODIGOTIPODATO DE CADA FILA
		String codigoTipoDato = valores[0].split(";")[2];
		LabelValue[] lista = new LabelValue[valores.length];
		int z = 0;
		if (valores[0].split(";")[0].equals(""))
			lista = null;
		else {
			for (String string : valores) {
				Map mapAux = new HashMap();
				String[] spli = string.split(";");
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(spli[0]);
				labelValue.setValue(spli[1]);
				lista[z] = labelValue;
				z++;
			}
		}
		Iterator it = msgParametrosPlantillaList.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String codigo = map.get("codigoTipoDato").toString();
			if (codigo.equals(codigoTipoDato)) {
				map.put("listaValoresDefectos", lista);
			}
		}
	}

	public void agregarValoresMultiple(ValueChangeEvent val) {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String posicion = externalContext.getRequestParameterMap().get("index");
		String pos = posicion.split(";")[0];
		String codigoTipoDato = posicion.split(";")[1];
		String[] valores = (String[]) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ArrayList values = new ArrayList();
		// POSICION PARA SABER DE CUAL COMBO ESTA QUE VIENE PARA HACER EL CAMBIO
		LabelValue[] lista = null;
		if (valores[0].equals("N") || valores.length == 0)
			valores = null;
		// CAPTURA EL CODIGOTIPODATO DE CADA FILA
		if (valores != null) {
			lista = new LabelValue[valores.length];
			int z = 0;
			if (valores[0].split(";")[0].equals(""))
				lista = null;
			else {
				for (String string : valores) {
					Map mapAux = new HashMap();
					String[] spli = string.split(";");
					LabelValue labelValue = new LabelValue();
					labelValue.setLabel(spli[0]);
					labelValue.setValue(spli[1]);
					values.add(spli[1]);
					lista[z] = labelValue;
					z++;
				}
			}
		}

		// metodo para mantener en sesion los datos del combo
		if (pos.equals("0")) {
			oidTipoCliente = values;
			if (valores == null) {
				oidTipoCliente = null;
				oidSubTipoCliente = null;
				oidTipoClasificacion = null;
				oidClasificacion = null;
			}

		} else if (pos.equals("1")) {
			oidSubTipoCliente = values;
			if (valores == null) {
				oidSubTipoCliente = null;
				oidTipoClasificacion = null;
				oidClasificacion = null;
			}
		} else if (pos.equals("2")) {
			oidTipoClasificacion = values;
			if (valores == null) {
				oidTipoClasificacion = null;
				oidClasificacion = null;
			}
		} else if (pos.equals("3")) {
			oidClasificacion = values;
			if (valores == null) {
				oidClasificacion = null;
			}

		}

		// LOGICA PARA CONSTRUIR LOS 4 COMBOS DE TIPOLOGIA (IZQUIERDA)
		List lista2 = new ArrayList();
		for (int i = 0; i < 4; i++) {
			LabelValue[] listavalores = null;
			if (i == 0) {
				List tipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());
				listavalores = new LabelValue[tipoClienteList.size()];
				int j = 0;
				for (Object object : tipoClienteList) {
					LabelValue labelValue = new LabelValue();
					labelValue.setLabel(((BaseOID) object).getDescripcion());
					labelValue.setValue(((BaseOID) object).getOid().toString());
					listavalores[j] = labelValue;
					j++;
				}
				this.tipoClienteList = listavalores;
			} else if (i == 1) {
				if (oidTipoCliente != null)
					listavalores = ajax
							.getSubTiposClientesPorPaisTipoClientesOID(usuario
									.getIdioma().getCodigoISO(), oidTipoCliente);

				this.subTipoClienteList = listavalores;

			} else if (i == 2) {
				if (oidSubTipoCliente == null || pos.equals("0"))
					listavalores = null;
				else
					listavalores = ajax
							.getTiposClasificacionesByCriteriaMultipleOID(
									usuario.getIdioma().getCodigoISO(),
									oidTipoCliente.get(0).toString(),
									oidSubTipoCliente);
				this.tipoClasificacionesList = listavalores;

			} else if (i == 3) {
				if (oidTipoClasificacion == null || pos.equals("0")
						|| pos.equals("1"))
					listavalores = null;
				else
					listavalores = ajax
							.getClasificacionesByCriteriaMultipleOID(usuario
									.getIdioma().getCodigoISO(), oidTipoCliente
									.get(0).toString(), oidSubTipoCliente,
									oidTipoClasificacion);
				this.clasificacionesList = listavalores;
			}
			lista2.add(listavalores);

		}

		// LOGICA PARA CONSTRUIR LOS 4 COMBOS DE TIPOLOGIA (DERECHA)
		listaComboMultiple = new ArrayList();
		for (int i = 0; i < 4; i++) {
			LabelValue[] listavalores = null;
			if (i == 0) {
				if (oidTipoCliente != null) {
					listavalores = new LabelValue[oidTipoCliente.size()];
					for (int j = 0; j < tipoClienteList.length; j++) {
						String codigo = tipoClienteList[j].getValue();
						for (int k = 0; k < oidTipoCliente.size(); k++) {
							if (codigo.equals(oidTipoCliente.get(k))) {
								listavalores[k] = tipoClienteList[j];
							}
						}

					}
				} else
					listavalores = null;
			} else if (i == 1) {
				if (oidSubTipoCliente != null) {
					if (oidSubTipoCliente.size() == 0 || pos.equals("0"))
						listavalores = null;
					else {
						listavalores = new LabelValue[oidSubTipoCliente.size()];
						for (int j = 0; j < subTipoClienteList.length; j++) {
							String codigo = subTipoClienteList[j].getValue();
							for (int k = 0; k < oidSubTipoCliente.size(); k++)
								if (codigo.equals(oidSubTipoCliente.get(k)))
									listavalores[k] = subTipoClienteList[j];
						}
					}
				} else
					listavalores = null;

			} else if (i == 2) {
				if (oidTipoClasificacion != null) {
					if (oidTipoClasificacion.size() == 0 || pos.equals("0")
							|| pos.equals("1"))
						listavalores = null;
					else {
						listavalores = new LabelValue[oidSubTipoCliente.size()];
						for (int j = 0; j < tipoClasificacionesList.length; j++) {
							String codigo = tipoClasificacionesList[j]
									.getValue();
							for (int k = 0; k < oidTipoClasificacion.size(); k++)
								if (codigo.equals(oidTipoClasificacion.get(k)))
									listavalores[k] = tipoClasificacionesList[j];
						}
					}
				} else
					listavalores = null;

			} else if (i == 3) {
				if (oidClasificacion != null) {
					if (oidClasificacion.size() == 0 || pos.equals("0")
							|| pos.equals("1") || pos.equals("2"))
						listavalores = null;
					else {
						listavalores = new LabelValue[oidClasificacion.size()];
						for (int j = 0; j < clasificacionesList.length; j++) {
							String codigo = clasificacionesList[j].getValue();
							for (int k = 0; k < oidClasificacion.size(); k++)
								if (codigo.equals(oidClasificacion.get(k)))
									listavalores[k] = tipoClasificacionesList[j];
						}
					}
				} else
					listavalores = null;

			}
			listaComboMultiple.add(listavalores);

		}

		Iterator it = msgParametrosPlantillaList.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String codigo = map.get("codigoTipoDato").toString();
			if (codigo.equals(codigoTipoDato))
				map.put("listaValoresDefectosMultiples", listaComboMultiple);
			map.put("listCombosMultiples", lista2);
		}

	}

	@Override
	protected void setInvocarPopup(String accion) {
		this.busquedaGenericaPOPUPSearchAction.getListaBusqueda().clear();
		this.accionGenerico = accion;
		String valorPosi = accion.split(";")[1];
		String index = accion.split(";")[2];
		accion = accion.split(";")[0];
		this.busquedaGenericaPOPUPSearchAction.setIndexPopup(index + ":input");
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.mostrarPopupConsultora = true;
		}
		this.busquedaGenericaPOPUPSearchAction.setValorPosible(valorPosi);
	}

	public void enviarValorPopup(ActionEvent event) {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String valorPosible = params.get("valorPosible");
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");

		Iterator it = msgParametrosPlantillaList.iterator();
		String codigoPopup = "";
		valoresDefectos = new LabelValue[MSG_CANTIDAD_SELECCION_TOTAL];

		while (it.hasNext()) {
			Map map = (Map) it.next();
			String valPosi = (String) map.get("valorPosible");
			valoresDefectos = (LabelValue[]) map.get("listaValoresDefectos");
			if (valPosi != null)
				if (valPosi.equals(valorPosible)) {
					codigoPopup = map.get("codigoPopup").toString();
					Map criteria = new HashMap();
					criteria.put("valorPosible", valorPosible);
					criteria.put("codigo", codigoPopup);
					List resultado = service.getSelectPlantilla(criteria);
					Map mapAux = (Map) resultado.get(0);
					LabelValue[] lista = new LabelValue[1];
					LabelValue labelvalue = new LabelValue();
					String descripcion = "";
					if (StringUtils.isNotBlank((String) mapAux
							.get("descripcion"))) {
						descripcion = mapAux.get("descripcion").toString();
					}
					labelvalue.setLabel(mapAux.get("codigo").toString() + "-"
							+ descripcion);
					labelvalue.setValue(mapAux.get("oid").toString());
					lista[0] = labelvalue;
					if (valoresDefectos.length > 0) {
						lista = new LabelValue[valoresDefectos.length + 1];
						lista[0] = labelvalue;
						for (int i = 0; i < valoresDefectos.length; i++) {
							labelvalue = new LabelValue();
							if (codigoPopup.equals(valoresDefectos[i]
									.getValue())) {
								this.addError(
										"Error",
										this.getResourceMessage("mantenimientoMENPlantillaConfiguracionProcesosForm.msg.codigo.existe.in.lista"));
								lista = valoresDefectos;
							} else {
								labelvalue.setLabel(valoresDefectos[i]
										.getLabel());
								labelvalue.setValue(valoresDefectos[i]
										.getValue());
								lista[i + 1] = labelvalue;
							}

						}
					}
					map.put("listaValoresDefectos", lista);
					break;
				}
		}
	}

	public void eliminarValorPopup(ActionEvent event) {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String valorPosible = params.get("valorPosible");
		Iterator it = msgParametrosPlantillaList.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			valoresDefectos = (LabelValue[]) map.get("listaValoresDefectos");
			String[] hdvalorDefecto = (String[]) map.get("hdvalorDefecto");
			String valPosi = (String) map.get("valorPosible");
			if (valPosi != null)
				if (valPosi.equals(valorPosible)) {
					LabelValue[] lista = new LabelValue[valoresDefectos.length
							- hdvalorDefecto.length];
					LabelValue labelvalue = new LabelValue();
					int z = 0;

					for (int i = 0; i < valoresDefectos.length; i++) {
						String codigo = valoresDefectos[i].getValue();
						String descripcion = valoresDefectos[i].getLabel();
						boolean band = true;
						for (int j = 0; j < hdvalorDefecto.length; j++) {
							String _cod = hdvalorDefecto[j];
							if (codigo.equals(hdvalorDefecto[j])) {
								band = false;
								break;

							}
						}
						if (band == true) {
							labelvalue = new LabelValue();
							labelvalue.setLabel(descripcion);
							labelvalue.setValue(codigo);
							lista[z] = labelvalue;
							z++;
						}

					}
					valoresDefectos = lista;
				}
			map.put("listaValoresDefectos", valoresDefectos);
		}
	}

	/**
	 * @return the msgPlanillaCabecList
	 */
	public List getMsgPlanillaCabecList() {
		return msgPlanillaCabecList;
	}

	/**
	 * @param msgPlanillaCabecList
	 *            the msgPlanillaCabecList to set
	 */
	public void setMsgPlanillaCabecList(List msgPlanillaCabecList) {
		this.msgPlanillaCabecList = msgPlanillaCabecList;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the _indicadorActivo
	 */
	public boolean is_indicadorActivo() {
		return _indicadorActivo;
	}

	/**
	 * @param _indicadorActivo
	 *            the _indicadorActivo to set
	 */
	public void set_indicadorActivo(boolean _indicadorActivo) {
		this._indicadorActivo = _indicadorActivo;
	}

	/**
	 * @return the _indicadorGestionUsuario
	 */
	public boolean is_indicadorGestionUsuario() {
		return _indicadorGestionUsuario;
	}

	/**
	 * @param _indicadorGestionUsuario
	 *            the _indicadorGestionUsuario to set
	 */
	public void set_indicadorGestionUsuario(boolean _indicadorGestionUsuario) {
		this._indicadorGestionUsuario = _indicadorGestionUsuario;
	}

	/**
	 * @return the nUMERO_CERO
	 */
	public String getNUMERO_CERO() {
		return NUMERO_CERO;
	}

	/**
	 * @param nUMERO_CERO
	 *            the nUMERO_CERO to set
	 */
	public void setNUMERO_CERO(String nUMERO_CERO) {
		NUMERO_CERO = nUMERO_CERO;
	}

	/**
	 * @return the nUMERO_UNO
	 */
	public String getNUMERO_UNO() {
		return NUMERO_UNO;
	}

	/**
	 * @param nUMERO_UNO
	 *            the nUMERO_UNO to set
	 */
	public void setNUMERO_UNO(String nUMERO_UNO) {
		NUMERO_UNO = nUMERO_UNO;
	}

	/**
	 * @return the msgVistaPlanillaList
	 */
	public List getMsgVistaPlanillaList() {
		return msgVistaPlanillaList;
	}

	/**
	 * @param msgVistaPlanillaList
	 *            the msgVistaPlanillaList to set
	 */
	public void setMsgVistaPlanillaList(List msgVistaPlanillaList) {
		this.msgVistaPlanillaList = msgVistaPlanillaList;
	}

	/**
	 * @return the msgVistaPlantillaPopupList
	 */
	public List getMsgVistaPlantillaPopupList() {
		return msgVistaPlantillaPopupList;
	}

	/**
	 * @param msgVistaPlantillaPopupList
	 *            the msgVistaPlantillaPopupList to set
	 */
	public void setMsgVistaPlantillaPopupList(List msgVistaPlantillaPopupList) {
		this.msgVistaPlantillaPopupList = msgVistaPlantillaPopupList;
	}

	/**
	 * @return the menHoraActividadList
	 */
	public List getMenHoraActividadList() {
		return menHoraActividadList;
	}

	/**
	 * @param menHoraActividadList
	 *            the menHoraActividadList to set
	 */
	public void setMenHoraActividadList(List menHoraActividadList) {
		this.menHoraActividadList = menHoraActividadList;
	}

	/**
	 * @return the menMinutoActividadList
	 */
	public List getMenMinutoActividadList() {
		return menMinutoActividadList;
	}

	/**
	 * @param menMinutoActividadList
	 *            the menMinutoActividadList to set
	 */
	public void setMenMinutoActividadList(List menMinutoActividadList) {
		this.menMinutoActividadList = menMinutoActividadList;
	}

	/**
	 * @return the menTiempoAmPmActividadList
	 */
	public List getMenTiempoAmPmActividadList() {
		return menTiempoAmPmActividadList;
	}

	/**
	 * @param menTiempoAmPmActividadList
	 *            the menTiempoAmPmActividadList to set
	 */
	public void setMenTiempoAmPmActividadList(List menTiempoAmPmActividadList) {
		this.menTiempoAmPmActividadList = menTiempoAmPmActividadList;
	}

	/**
	 * @return the correlativoComboMultiple
	 */
	public int getCorrelativoComboMultiple() {
		return correlativoComboMultiple;
	}

	/**
	 * @param correlativoComboMultiple
	 *            the correlativoComboMultiple to set
	 */
	public void setCorrelativoComboMultiple(int correlativoComboMultiple) {
		this.correlativoComboMultiple = correlativoComboMultiple;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the tipoDatoPopup
	 */
	public static String getTipoDatoPopup() {
		return TIPO_DATO_POPUP;
	}

	/**
	 * @return the tipoDatoCombobox
	 */
	public static String getTipoDatoCombobox() {
		return TIPO_DATO_COMBOBOX;
	}

	/**
	 * @return the tipoDatoTextbox
	 */
	public static String getTipoDatoTextbox() {
		return TIPO_DATO_TEXTBOX;
	}

	/**
	 * @return the tipoDatoChkbox
	 */
	public static String getTipoDatoChkbox() {
		return TIPO_DATO_CHKBOX;
	}

	/**
	 * @return the msgParametrosPlanillaList
	 */
	public List getMsgParametrosPlantillaList() {
		return msgParametrosPlantillaList;
	}

	/**
	 * @param msgParametrosPlanillaList
	 *            the msgParametrosPlanillaList to set
	 */
	public void setMsgParametrosPlantillaList(List msgParametrosPlantillaList) {
		this.msgParametrosPlantillaList = msgParametrosPlantillaList;
	}

	/**
	 * @return the msgValorDefectoList
	 */
	public List getMsgValorDefectoList() {
		return msgValorDefectoList;
	}

	/**
	 * @param msgValorDefectoList
	 *            the msgValorDefectoList to set
	 */
	public void setMsgValorDefectoList(List msgValorDefectoList) {
		this.msgValorDefectoList = msgValorDefectoList;
	}

	/**
	 * @return the msgValorDefectoPopupList
	 */
	public List getMsgValorDefectoPopupList() {
		return msgValorDefectoPopupList;
	}

	/**
	 * @param msgValorDefectoPopupList
	 *            the msgValorDefectoPopupList to set
	 */
	public void setMsgValorDefectoPopupList(List msgValorDefectoPopupList) {
		this.msgValorDefectoPopupList = msgValorDefectoPopupList;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora
	 *            the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	/**
	 * @return the popupConsultora
	 */
	public static String getPopupConsultora() {
		return POPUP_CONSULTORA;
	}

	/**
	 * @return the busquedaGenericaPOPUPSearchAction
	 */
	public BusquedaGenericaPOPUPSearchAction getBusquedaGenericaPOPUPSearchAction() {
		return busquedaGenericaPOPUPSearchAction;
	}

	/**
	 * @param busquedaGenericaPOPUPSearchAction
	 *            the busquedaGenericaPOPUPSearchAction to set
	 */
	public void setBusquedaGenericaPOPUPSearchAction(
			BusquedaGenericaPOPUPSearchAction busquedaGenericaPOPUPSearchAction) {
		this.busquedaGenericaPOPUPSearchAction = busquedaGenericaPOPUPSearchAction;
	}

	/**
	 * @param fechaD
	 *            the fechaD to set
	 */
	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}

	/**
	 * @return the listParametrosPlantilla
	 */
	public List getListParametrosPlantilla() {
		return listParametrosPlantilla;
	}

	/**
	 * @param listParametrosPlantilla
	 *            the listParametrosPlantilla to set
	 */
	public void setListParametrosPlantilla(List listParametrosPlantilla) {
		this.listParametrosPlantilla = listParametrosPlantilla;
	}

	/**
	 * @return the listParametrosMultiple
	 */
	public List getListParametrosMultiple() {
		return listParametrosMultiple;
	}

	/**
	 * @param listParametrosMultiple
	 *            the listParametrosMultiple to set
	 */
	public void setListParametrosMultiple(List listParametrosMultiple) {
		this.listParametrosMultiple = listParametrosMultiple;
	}

	/**
	 * @return the hdvalorPosible
	 */
	public String[] getHdvalorPosible() {
		return hdvalorPosible;
	}

	/**
	 * @param hdvalorPosible
	 *            the hdvalorPosible to set
	 */
	public void setHdvalorPosible(String[] hdvalorPosible) {
		this.hdvalorPosible = hdvalorPosible;
	}

	/**
	 * @return the hdvalorDefecto
	 */
	public String[] getHdvalorDefecto() {
		return hdvalorDefecto;
	}

	/**
	 * @param hdvalorDefecto
	 *            the hdvalorDefecto to set
	 */
	public void setHdvalorDefecto(String[] hdvalorDefecto) {
		this.hdvalorDefecto = hdvalorDefecto;
	}

	/**
	 * @return the valoresPosibles
	 */
	public LabelValue[] getValoresPosibles() {
		return valoresPosibles;
	}

	/**
	 * @param valoresPosibles
	 *            the valoresPosibles to set
	 */
	public void setValoresPosibles(LabelValue[] valoresPosibles) {
		this.valoresPosibles = valoresPosibles;
	}

	/**
	 * @return the valoresDefectos
	 */
	public LabelValue[] getValoresDefectos() {
		return valoresDefectos;
	}

	/**
	 * @param valoresDefectos
	 *            the valoresDefectos to set
	 */
	public void setValoresDefectos(LabelValue[] valoresDefectos) {
		this.valoresDefectos = valoresDefectos;
	}

	/**
	 * @return the fecha
	 */
	public Date getFechaD() {
		return fechaD;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fechaD) {
		this.fechaD = fechaD;
	}

	/**
	 * @return the accionGenerico
	 */
	public String getAccionGenerico() {
		return accionGenerico;
	}

	/**
	 * @param accionGenerico
	 *            the accionGenerico to set
	 */
	public void setAccionGenerico(String accionGenerico) {
		this.accionGenerico = accionGenerico;
	}

	/**
	 * @return the oidTipoCliente
	 */
	public ArrayList getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente
	 *            the oidTipoCliente to set
	 */
	public void setOidTipoCliente(ArrayList oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return the oidSubTipoCliente
	 */
	public ArrayList getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente
	 *            the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(ArrayList oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public ArrayList getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion
	 *            the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(ArrayList oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return the tipoClienteList
	 */
	public LabelValue[] getTipoClienteList() {
		return tipoClienteList;
	}

	/**
	 * @param tipoClienteList
	 *            the tipoClienteList to set
	 */
	public void setTipoClienteList(LabelValue[] tipoClienteList) {
		this.tipoClienteList = tipoClienteList;
	}

	/**
	 * @return the subTipoClienteList
	 */
	public LabelValue[] getSubTipoClienteList() {
		return subTipoClienteList;
	}

	/**
	 * @param subTipoClienteList
	 *            the subTipoClienteList to set
	 */
	public void setSubTipoClienteList(LabelValue[] subTipoClienteList) {
		this.subTipoClienteList = subTipoClienteList;
	}

	/**
	 * @return the tipoClasificacionesList
	 */
	public LabelValue[] getTipoClasificacionesList() {
		return tipoClasificacionesList;
	}

	/**
	 * @param tipoClasificacionesList
	 *            the tipoClasificacionesList to set
	 */
	public void setTipoClasificacionesList(LabelValue[] tipoClasificacionesList) {
		this.tipoClasificacionesList = tipoClasificacionesList;
	}

	/**
	 * @return the clasificacionesList
	 */
	public LabelValue[] getClasificacionesList() {
		return clasificacionesList;
	}

	/**
	 * @param clasificacionesList
	 *            the clasificacionesList to set
	 */
	public void setClasificacionesList(LabelValue[] clasificacionesList) {
		this.clasificacionesList = clasificacionesList;
	}

	/**
	 * @return the oidClasificacion
	 */
	public ArrayList getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion
	 *            the oidClasificacion to set
	 */
	public void setOidClasificacion(ArrayList oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	public static int getMsgCantidadSeleccionTotal() {
		return MSG_CANTIDAD_SELECCION_TOTAL;
	}

	public List getListaComboMultiple() {
		return listaComboMultiple;
	}

	public void setListaComboMultiple(List listaComboMultiple) {
		this.listaComboMultiple = listaComboMultiple;
	}

}
