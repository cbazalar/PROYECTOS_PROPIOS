package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CuponPago;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CuponPagoSearch;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOCuponForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOCuponSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoSTOCuponSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List stoCuponPagoList;
	private Boolean indRechazoSello;
	private String oidPais;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOCuponList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOCuponForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOCuponSearchForm form = new MantenimientoSTOCuponSearchForm();
		return form;
	}

	/**
	 * Valida dni de la consultora
	 */
	public void validarDniConsultora() {
		MantenimientoSTOCuponSearchForm f = (MantenimientoSTOCuponSearchForm) this.formBusqueda;
		String codigoCliente = "";
		String mensaje = "";

		codigoCliente = f.getNumeroDocumentoIdentidad();
		if (StringUtils.isEmpty(codigoCliente)) {
			mensaje = "Ingrese su documento de Identicadad";
			this.addError("Error : ", mensaje);
			f.setCodigoCliente(null);
		} else {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String codCliente = ajax.getCodigoClienteByDocumentoIdentidad(f
					.getNumeroDocumentoIdentidad());
			if (StringUtils.isBlank(codCliente)) {
				mensaje = "Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
				this.addError("Error : ", mensaje);
				f.setCodigoCliente(null);
			}
		}
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOCuponSearchForm f = (MantenimientoSTOCuponSearchForm) this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		f.setFechaRegistro(DateUtil.convertDateToString(f.getFechaRegistroD()));
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("fechaRegistro", f.getFechaRegistro());

		List resultado = (List) service.getCuponPagoSearchList(criteria);
		this.stoCuponPagoList = resultado;
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		CuponPagoSearch cupo = (CuponPagoSearch) this.beanRegistroSeleccionado;
		String id = cupo.getNumDocumento();
		log.debug("el numero de docu es" + StringUtils.substringAfter(id, "|"));
		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("numeroDocumento",id);

			service.deleteCuponPago(criteria);
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoSTOCuponForm f = (MantenimientoSTOCuponForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		f.setFechaRegistro(DateUtil.convertDateToString(f.getFechaRegistroD()));

		EmpresaComercializadora cabecera = new EmpresaComercializadora();
		BeanUtils.copyProperties(cabecera, f);

		if (log.isDebugEnabled()) {
			log.debug("EN EL CASO QUE SEA MODIFICACION");
		}

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("numeroDocumento", f.getNumDocumento());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("fechaRegistro", f.getFechaRegistro());
		criteria.put("valorPagado", f.getValorPagado());

		if (StringUtils.equals(f.getIndRechazoSello(), "S"))
			criteria.put("indicadorRechazo", "1");
		else
			criteria.put("indicadorRechazo", "0");
		service.updateCuponPagoModificar(criteria);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		CuponPagoSearch obj = (CuponPagoSearch) this.beanRegistroSeleccionado;
		MantenimientoSTOCuponForm f = new MantenimientoSTOCuponForm();

		if (obj != null) {

			String id = obj.getNumDocumento();

			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());

			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("numeroDocumento", id);
			criteria.put("codigoTipo", Constants.STO_TIPO_DOCUMENTO_CUP);

			CuponPago cupon = new CuponPago();

			List resultado = (List) service.getCuponPagoModificar(criteria);
			if (resultado != null && resultado.size() > 0) {
				cupon = (CuponPago) resultado.get(0);
				f.setEditable(false);
				f.setCodigoPais(cupon.getCodPais());
				f.setCodigoCliente(cupon.getCodCliente());
				f.setFechaRegistro(cupon.getFechaProceso());
				f.setValorPagado(cupon.getImpValor());
				f.setNumDocumento(cupon.getNumDocumento());
				f.setNumLote(cupon.getNumLote());
				f.setNunSecuencia(cupon.getNumSecuencia());
				f.setFechaRegistroD(DateUtil.convertStringToDate(cupon.getFechaProceso()));

				if (cupon.getIndRechazoSello() != null) {
					if (cupon.getIndRechazoSello().compareToIgnoreCase("1") == 0) {
						f.setIndRechazoSello("S");
						this.indRechazoSello = true;
					} else {
						f.setIndRechazoSello("N");
						this.indRechazoSello = false;
					}
				} else {
					this.indRechazoSello = false;
					f.setIndRechazoSello("N");
				}

				this.oidPais = reporteService.getOidString("getOidPaisByCodigoPais", criteriaOperacion);
				f.setOidPais(String.valueOf(this.oidPais));
			}
		}
		return f;
	}

	/**
	 * Inicializando valores
	 */
	public void inicializando() {
		this.stoCuponPagoList = new ArrayList();
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.indRechazoSello = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		inicializando();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOCuponSearchForm f = (MantenimientoSTOCuponSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		this.oidPais = "";

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	public String setValidarMantenimiento() {
		MantenimientoSTOCuponForm f = (MantenimientoSTOCuponForm) this.formMantenimiento;
		if (StringUtils.isBlank(f.getCodigoCliente()))
			return "Debe de ingresar el codigo de cliente";

		String codigoConsultora = f.getCodigoCliente();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String obtenerDatos = "";
		obtenerDatos = aSvc.validarCodigoCliente(this.oidPais, codigoConsultora);

		if (StringUtils.equals(obtenerDatos, "N"))
			return "El codigo de cliente no existe";

		if (!this.indRechazoSello) {
			f.setIndRechazoSello("N");
			this.indRechazoSello = true;
		}else
			f.setIndRechazoSello("S");

		return "";
	}

	/**
	 * @return the stoCuponPagoList
	 */
	public List getStoCuponPagoList() {
		return stoCuponPagoList;
	}

	/**
	 * @param stoCuponPagoList
	 *            the stoCuponPagoList to set
	 */
	public void setStoCuponPagoList(List stoCuponPagoList) {
		this.stoCuponPagoList = stoCuponPagoList;
	}

	/**
	 * @return the indRechazoSello
	 */
	public Boolean getIndRechazoSello() {
		return indRechazoSello;
	}

	/**
	 * @param indRechazoSello
	 *            the indRechazoSello to set
	 */
	public void setIndRechazoSello(Boolean indRechazoSello) {
		this.indRechazoSello = indRechazoSello;
	}
}