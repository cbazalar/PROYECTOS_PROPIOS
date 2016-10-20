package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionFlete;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionSobreFlete;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEExcencionFleteForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEExcencionFleteSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEExcencionSobreFleteForm;

@SessionScoped
@ManagedBean
public class MantenimientoMAEExcencionFleteSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9138593453196226910L;
	private List siccTipoClienteList;
	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;
	private List maeExcencionFleteList;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoMAEExcencionFleteList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoMAEExcencionFleteForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMAEExcencionFleteSearchForm form = new MantenimientoMAEExcencionFleteSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoMAEExcencionFleteSearchAction");
		}

		MantenimientoMAEExcencionFleteSearchForm f = (MantenimientoMAEExcencionFleteSearchForm) this.formBusqueda;
		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

		Map params = BeanUtils.describe(f);
		params.put("tipoBusqueda", Constants.NUMERO_UNO);

		List lista = service.getExcencionesFletesByCriteria(params);

		maeExcencionFleteList = lista;

		// Seteamos los filtros seleccionados por el usuario
		if (StringUtils.isNotBlank(f.getOidTipoCliente())) {
			ArrayList oidsTipoCliente = new ArrayList();
			oidsTipoCliente.add(f.getOidTipoCliente());

			LabelValue[] tiposCliente = ajaxService
					.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO,
							oidsTipoCliente);
			siccSubTipoClienteList = tiposCliente;

			if (StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
				ArrayList oidsSubTipoCliente = new ArrayList();
				oidsSubTipoCliente.add(f.getOidSubTipoCliente());

				LabelValue[] tiposClasificacion = ajaxService
						.getTiposClasificacionesByCriteriaMultipleOID(
								codigoIdiomaISO, f.getOidTipoCliente(),
								oidsSubTipoCliente);
				siccTipoClasificacionList = tiposClasificacion;

				if (StringUtils.isNotBlank(f.getOidTipoClasificacion())) {
					ArrayList oidsTipoClasificacion = new ArrayList();
					oidsTipoClasificacion.add(f.getOidTipoClasificacion());

					LabelValue[] clasificaciones = ajaxService
							.getClasificacionesByCriteriaMultipleOID(
									codigoIdiomaISO, f.getOidTipoCliente(),
									oidsSubTipoCliente, oidsTipoClasificacion);
					siccClasificacionList = clasificaciones;
				}
			}
		}
		//

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEExcencionFleteAction");
		}

		MantenimientoMAEExcencionFleteForm f = (MantenimientoMAEExcencionFleteForm) this.formMantenimiento;
		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		boolean bGrabar=true;

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteria);

		boolean isNew = f.isNewRecord();

		ExcencionFlete excencionFlete = new ExcencionFlete();

		BeanUtils.copyProperties(excencionFlete, f);

		excencionFlete.setOidPais(oidPais);

		try {
			Map params = BeanUtils.describe(f);
			params.put("indicadorExcepcionFlete", null);
			params.put("codigoPais", pais.getCodigo());
			params.put("tipoBusqueda", Constants.NUMERO_DOS);

			List lista = service.getExcencionesFletesByCriteria(params);

			if (isNew) {

				if (lista != null && lista.size() > 0) {
					recargarCombos(f, codigoIdiomaISO);
					bGrabar=false;
					throw new Exception(
							this.getResourceMessage("mantenimientoMAEExcencionFleteForm.error.duplicado"));
				} else {
					service.insertExcencionFlete(excencionFlete, usuario);

				}
			} else {

				boolean actualizar = true;
				if (lista != null && lista.size() > 0) {
					if (lista.size() == 1) {
						String oid = MapUtils.getString((Map) lista.get(0),
								"oidExcencion", "");
						if (StringUtils.equals(oid,
								excencionFlete.getOidExcencion())) {
							actualizar = true;
						} else {
							actualizar = false;
						}
					} else {
						actualizar = false;
					}
				}

				if (actualizar) {
					service.updateExcencionFlete(excencionFlete, usuario);
//					messages.add(
//							ActionMessages.GLOBAL_MESSAGE,
//							new ActionMessage(
//									"mantenimientoMAEExcencionFleteForm.updated"));
//					saveMessages(request.getSession(), messages);
				} else {
					recargarCombos(f, codigoIdiomaISO);
					throw new Exception(
							this.getResourceMessage("mantenimientoMAEExcencionFleteForm.error.duplicado"));

				}

			}

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
			return false;
		} 

		return bGrabar;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoMAEExcencionSobreFleteAction");
		}

		MantenimientoMAEExcencionFleteForm f = new MantenimientoMAEExcencionFleteForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// RESET
		f.setOidExcencion(null);
		f.setOidTipoCliente(null);
		f.setOidSubTipoCliente(null);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);
		f.setIndicadorExcepcionFlete(Constants.NUMERO_UNO);
		f.setCodigoPais(pais.getCodigo());
		// FIN RESET

		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();


		this.siccSubTipoClienteList = null;
		this.siccTipoClasificacionList = null;
		this.siccClasificacionList = null;

		try {

			if (!this.accion.equals(this.ACCION_NUEVO)) {
				Map bean = (Map) this.beanRegistroSeleccionado;
				String id = bean.get("oidExcencion").toString();
				String codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

				if (StringUtils.isNotBlank(id)) {
					ExcencionFlete excencionFlete = service
							.getExcencionFlete(id);
					Map params = BeanUtils.describe(f);
					BeanUtils.copyProperties(f, excencionFlete);
					recargarCombos(f, codigoIdiomaISO);
					f.setNewRecord(false);

				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return f;
	}

	private void recargarCombos(MantenimientoMAEExcencionFleteForm f,
			String codigoIdiomaISO) {

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		if (StringUtils.isNotBlank(f.getOidTipoCliente())) {
			ArrayList oidsTipoCliente = new ArrayList();
			oidsTipoCliente.add(f.getOidTipoCliente());

			LabelValue[] tiposCliente = ajaxService
					.getSubTiposClientesPorPaisTipoClientesOID(codigoIdiomaISO,
							oidsTipoCliente);
			siccSubTipoClienteList = tiposCliente;

			if (StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
				ArrayList oidsSubTipoCliente = new ArrayList();
				oidsSubTipoCliente.add(f.getOidSubTipoCliente());

				LabelValue[] tiposClasificacion = ajaxService
						.getTiposClasificacionesByCriteriaMultipleOID(
								codigoIdiomaISO, f.getOidTipoCliente(),
								oidsSubTipoCliente);
				siccTipoClasificacionList = tiposClasificacion;

				if (StringUtils.isNotBlank(f.getOidTipoClasificacion())) {
					ArrayList oidsTipoClasificacion = new ArrayList();
					oidsTipoClasificacion.add(f.getOidTipoClasificacion());

					LabelValue[] clasificaciones = ajaxService
							.getClasificacionesByCriteriaMultipleOID(
									codigoIdiomaISO, f.getOidTipoCliente(),
									oidsSubTipoCliente, oidsTipoClasificacion);
					siccClasificacionList = clasificaciones;
				}
			}
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setViewAttributes - MantenimientoMAEExcencionFleteSearchAction");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		MantenimientoMAEExcencionFleteSearchForm f = (MantenimientoMAEExcencionFleteSearchForm) this.formBusqueda;

		this.siccTipoClienteList = service
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());

		f.setCodigoPais(pais.getCodigo());

		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.salirGrabarPantallaPadre = true;

		// MantenimientoMAEExcencionSobreFleteSearchAction

	}

	public void loadSubTiposClientes(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoMAEExcencionFleteSearchForm f = (MantenimientoMAEExcencionFleteSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ArrayList codigoTipoClientes = new ArrayList();

		if (valor != null) {
			codigoTipoClientes.add(valor);
			this.siccSubTipoClienteList = ajax
					.getSubTiposClientesPorPaisTipoClientesOID(
							pais.getCodigoIdiomaIso(), codigoTipoClientes);
		} else {
			f.setOidSubTipoCliente(null);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccSubTipoClienteList = null;
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		}
	}

	public void loadTiposClasificaciones(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionFleteSearchForm f = (MantenimientoMAEExcencionFleteSearchForm) this.formBusqueda;

		if (valor != null) {
			ArrayList codigoSubTipoCliente = new ArrayList();
			codigoSubTipoCliente.add(valor);
			this.siccTipoClasificacionList = ajax
					.getTiposClasificacionesByCriteriaMultipleOID(
							pais.getCodigoIdiomaIso(), f.getOidTipoCliente(),
							codigoSubTipoCliente);
		} else {
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		}
	}

	public void loadClasificaciones(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionFleteSearchForm f = (MantenimientoMAEExcencionFleteSearchForm) this.formBusqueda;

		if (valor != null) {
			ArrayList codigoSubTipoCliente = new ArrayList();
			ArrayList codigoTipoClasificacion = new ArrayList();

			codigoSubTipoCliente.add(f.getOidSubTipoCliente());
			codigoTipoClasificacion.add(valor);

			this.siccClasificacionList = ajax
					.getClasificacionesByCriteriaMultipleOID(
							pais.getCodigoIdiomaIso(), f.getOidTipoCliente(),
							codigoSubTipoCliente, codigoTipoClasificacion);
		} else {
			f.setOidClasificacion(null);
			this.siccClasificacionList = null;
		}
	}

	public void loadSubTiposClientesMante(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoMAEExcencionFleteForm f = (MantenimientoMAEExcencionFleteForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ArrayList codigoTipoClientes = new ArrayList();

		if (valor != null) {
			codigoTipoClientes.add(valor);
			this.siccSubTipoClienteList = ajax
					.getSubTiposClientesPorPaisTipoClientesOID(
							pais.getCodigoIdiomaIso(), codigoTipoClientes);
		} else {
			f.setOidSubTipoCliente(null);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccSubTipoClienteList = null;
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		}
	}

	public void loadTiposClasificacionesMante(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionFleteForm f = (MantenimientoMAEExcencionFleteForm) this.formMantenimiento;

		if (valor != null) {
			ArrayList codigoSubTipoCliente = new ArrayList();
			codigoSubTipoCliente.add(valor);
			this.siccTipoClasificacionList = ajax
					.getTiposClasificacionesByCriteriaMultipleOID(
							pais.getCodigoIdiomaIso(), f.getOidTipoCliente(),
							codigoSubTipoCliente);
		} else {
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		}
	}

	public void loadClasificacionesMante(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAEExcencionFleteForm f = (MantenimientoMAEExcencionFleteForm) this.formMantenimiento;

		if (valor != null) {
			ArrayList codigoSubTipoCliente = new ArrayList();
			ArrayList codigoTipoClasificacion = new ArrayList();

			codigoSubTipoCliente.add(f.getOidSubTipoCliente());
			codigoTipoClasificacion.add(valor);

			this.siccClasificacionList = ajax
					.getClasificacionesByCriteriaMultipleOID(
							pais.getCodigoIdiomaIso(), f.getOidTipoCliente(),
							codigoSubTipoCliente, codigoTipoClasificacion);
		} else {
			f.setOidClasificacion(null);
			this.siccClasificacionList = null;
		}
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
	 * @return the numeroUno
	 */
	public String getNumeroUno() {
		return numeroUno;
	}

	/**
	 * @param numeroUno
	 *            the numeroUno to set
	 */
	public void setNumeroUno(String numeroUno) {
		this.numeroUno = numeroUno;
	}

	/**
	 * @return the numeroCero
	 */
	public String getNumeroCero() {
		return numeroCero;
	}

	/**
	 * @param numeroCero
	 *            the numeroCero to set
	 */
	public void setNumeroCero(String numeroCero) {
		this.numeroCero = numeroCero;
	}

	/**
	 * @return the maeExcencionFleteList
	 */
	public List getMaeExcencionFleteList() {
		return maeExcencionFleteList;
	}

	/**
	 * @param maeExcencionFleteList
	 *            the maeExcencionFleteList to set
	 */
	public void setMaeExcencionFleteList(List maeExcencionFleteList) {
		this.maeExcencionFleteList = maeExcencionFleteList;
	}

}
