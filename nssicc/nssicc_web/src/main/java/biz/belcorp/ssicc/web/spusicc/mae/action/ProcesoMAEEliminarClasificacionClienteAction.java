package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAEEliminarClasificacionClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAEEliminarClasificacionClienteForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoMAEEliminarClasificacionClienteAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;
	private LabelValue[] siccSubTipoClienteList;
	private String viewValida;
	private List siccTipoClienteList;
	private List maeClasificacionesArchivoList;
	private String codigoIdiomaISO;
	private Boolean segmentoDos;
	private String formatoReporte = "";
	private boolean showEliminar;

	/**
	 * @param val
	 */
	public void loadSubTiposClientes(ValueChangeEvent val) {
		try {
			ArrayList values = new ArrayList();

			values.add((String) val.getNewValue());
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccSubTipoClienteList = ajax
					.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO,
							values);
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param val
	 */
	public void loadTiposClasificaciones(ValueChangeEvent val) {
		try {
			ProcesoMAEEliminarClasificacionClienteForm p = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ArrayList values = new ArrayList();

			values.add((String) val.getNewValue());
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccTipoClasificacionList = ajax
					.getTiposClasificacionesByCriteriaMultipleOIDParamPais(
							pais.getCodigo(), codigoIdiomaISO,
							p.getOidTipoCliente(), values,
							p.getIndicadorTipoClasificacionPais());
			this.siccClasificacionList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param val
	 */
	public void loadClasificaciones(ValueChangeEvent val) {
		try {
			ProcesoMAEEliminarClasificacionClienteForm p = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;
			ArrayList valuesSubTipoCliente = new ArrayList();
			valuesSubTipoCliente.add(p.getOidSubTipoCliente());

			AjaxService ajax = (AjaxService) getBean("ajaxService");

			ArrayList valuesClasificacion = new ArrayList();
			valuesClasificacion.add((String) val.getNewValue());

			this.siccClasificacionList = ajax
					.getClasificacionesByCriteriaMultipleOID(
							this.codigoIdiomaISO, p.getOidTipoCliente(),
							valuesSubTipoCliente, valuesClasificacion);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Validando parametros
	 */
	public void validarParametros() {
		try {
			ProcesoMAEEliminarClasificacionClienteForm f = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;
			String oidTipoCliente = f.getOidTipoCliente();
			String oidSubTipoCliente = f.getOidSubTipoCliente();
			String oidTipoClasificacion = f.getOidTipoClasificacion();
			String oidClasificacion = f.getOidClasificacion();
			if (StringUtils.isBlank(oidTipoCliente))
				return;
			if (StringUtils.isBlank(oidSubTipoCliente))
				return;
			if (StringUtils.isBlank(oidTipoClasificacion))
				return;
			if (StringUtils.isBlank(oidClasificacion))
				return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en
	 * tabla
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void validar(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		try {
			validarParametros();
			ProcesoMAEEliminarClasificacionClienteForm f = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;

			Map params = BeanUtils.describe(f);
			// Object oidClasi = params.get("oidClasificacion").equals("T") ? ""
			// : params.get("oidClasificacion");
			params.put("oidClasificacion", f.getOidClasificacion());

			// obtenemos el service
			ProcesoMAEEliminarClasificacionClienteService service = (ProcesoMAEEliminarClasificacionClienteService) getBean("spusicc.procesoMAEEliminarClasificacionClienteService");

			// Obtenemos la cantidad de las consultoras clasificacion
			String nroRegistros = service.getNroConsultorasClasificacion(params);
			if (!StringUtils.isBlank(nroRegistros)) {
				this.mostrarReporteXLS = true;
			}
			this.segmentoDos = true;

			f.setNumRegistros(nroRegistros);

			if (Integer.parseInt(nroRegistros) > 0) {
				f.setFlagBotonEliminar(true);
			} else {
				f.setFlagBotonEliminar(false);
			}

			recargarTipologiaClientes();
			//flag para mostrar el resultado de la validacion
			this.viewValida = Constants.NUMERO_UNO;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Padre Executa proceso
	 * 
	 * @param evt
	 */
	public void executeProceso(ActionEvent evt) {
		ProcesoMAEEliminarClasificacionClienteForm form = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;
		try {
			Map<String, Object> params = BeanUtils.describe(form);
			executeProcess(params);
		} catch (Exception e) {
			String lsMensajeError = this.obtieneMensajeErrorException(e);
			this.addError("Error : ", lsMensajeError);
		}
	}

	/**
	 * Hijo
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void executeProcess(Map<String, Object> params) throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoMAEEliminarClasificacionClienteForm f = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;
		ProcesoMAEEliminarClasificacionClienteService service = (ProcesoMAEEliminarClasificacionClienteService) getBean("spusicc.procesoMAEEliminarClasificacionClienteService");
		
		params.put("oidClasificacion", f.getOidClasificacion());
		params.put("codigoUsuario", usuario.getLogin());

		service.eliminarClasificacionCliente(params);

		f.setNumeroLote((String) params.get("numeroLote"));
		afterExecuteProcessSuccess();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction
	 * #afterExecuteProcessSuccess(org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest)
	 */
	public void afterExecuteProcessSuccess() {
		// limpiando el flag de validacion de archivo
		ProcesoMAEEliminarClasificacionClienteForm f = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;
		this.viewValida = "";
		String mensaje = "";

		mensaje = this
				.getResourceMessage("procesoMAEEliminarClasificacionClienteForm.proceso.ok");

		this.addInfo("Info ", mensaje);

		f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
		f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);

		recargarTipologiaClientes();

	}

	/**
	 * 
	 */
	private void recargarTipologiaClientes() {
		try {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			ProcesoMAEEliminarClasificacionClienteForm f = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;

			ArrayList temp = new ArrayList();
			temp = new ArrayList();
			temp.add(f.getOidTipoCliente());
			this.siccSubTipoClienteList = aSvc
					.getSubTiposClientesPorPaisTipoClientesOID(usuario
							.getIdioma().getCodigoISO(), temp);

			temp = new ArrayList();
			temp.add(f.getOidSubTipoCliente());
			LabelValue[] listTiposClasificiones = aSvc
					.getTiposClasificacionesByCriteriaMultipleOIDParamPais(pais
							.getCodigo(), usuario.getIdioma().getCodigoISO(),
							Constants.OID_TIPO_CLIENTE_DEFAULT, temp, f
									.getIndicadorTipoClasificacionPais());
			this.siccTipoClasificacionList = listTiposClasificiones;

			if (f.getOidTipoClasificacion() == null)
				f.setOidTipoClasificacion(listTiposClasificiones[0].getValue());

			ArrayList temp2 = new ArrayList();
			temp2 = new ArrayList();
			temp2.add(f.getOidTipoClasificacion());
			this.siccClasificacionList = aSvc
					.getClasificacionesByCriteriaMultipleOID(usuario
							.getIdioma().getCodigoISO(),
							Constants.OID_TIPO_CLIENTE_DEFAULT, temp, temp2);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ProcesoMAEEliminarClasificacionClienteForm form = new ProcesoMAEEliminarClasificacionClienteForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (this.formatoReporte.equals("XLS")) {
			return "reporteMAEEliminarClasificacionClientesXLS";
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		this.formatoReporte = "XLS";

		// params.put("titulo",getMessageReporte("reporteMAECargaClasificacionClientesForm.titulo",
		// request));
		params.put("formatoExportacion", formatoReporte);
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		log.debug("setViewAttributes");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoMAEEliminarClasificacionClienteForm f = (ProcesoMAEEliminarClasificacionClienteForm) this.formReporte;
		f.reset();
		this.mostrarReportePDF = false;
		this.segmentoDos = false;
		f.setIndicadorTipoClasificacionPais(Constants.ESTADO_INACTIVO);

		// Obtener parametria de tipoClasificacion
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		// parametroPais.setCodigoParametro(Constants.MAE_CODIGO_PARAMETRO_IND_TIPO_CLASIFICACION_PAIS);
		parametroPais
				.setNombreParametro(Constants.MAE_NOMBRE_PARAMETRO_IND_TIPO_CLASIFICACION_PAIS);
		parametroPais.setValorParametro(Constants.NUMERO_UNO);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			f.setIndicadorTipoClasificacionPais(Constants.ESTADO_ACTIVO);
		}

		// cargando en session la lista de concursos habilitados
		this.siccTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());

		f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
		f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);

		recargarTipologiaClientes();

		f.setFlagBotonValidar(false);
		f.setFlagBotonEliminar(false);
		f.setNumeroLote("");

		// limpiando el flag de validacion de archivo
		this.viewValida = "";
		this.maeClasificacionesArchivoList = new ArrayList();
	}

	/**
	 * @return the siccTipoClasificacionList
	 */
	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	/**
	 * @param siccTipoClasificacionList
	 *            the siccTipoClasificacionList to set
	 */
	public void setSiccTipoClasificacionList(
			LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	/**
	 * @return the siccClasificacionList
	 */
	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	/**
	 * @param siccClasificacionList
	 *            the siccClasificacionList to set
	 */
	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList
	 *            the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return the viewValida
	 */
	public String getViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida
	 *            the viewValida to set
	 */
	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
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
	 * @return the maeClasificacionesArchivoList
	 */
	public List getMaeClasificacionesArchivoList() {
		return maeClasificacionesArchivoList;
	}

	/**
	 * @param maeClasificacionesArchivoList
	 *            the maeClasificacionesArchivoList to set
	 */
	public void setMaeClasificacionesArchivoList(
			List maeClasificacionesArchivoList) {
		this.maeClasificacionesArchivoList = maeClasificacionesArchivoList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 *            the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the segmentoDos
	 */
	public Boolean getSegmentoDos() {
		return segmentoDos;
	}

	/**
	 * @param segmentoDos
	 *            the segmentoDos to set
	 */
	public void setSegmentoDos(Boolean segmentoDos) {
		this.segmentoDos = segmentoDos;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
}