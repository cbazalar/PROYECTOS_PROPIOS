package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.AsignacionCarteraCobrador;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBAsignacionCarteraCobradorForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBAsignacionCarteraCobradorSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCOBAsignacionCarteraCobradorSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1211948720213307288L;

	private List siccEtapaDeudaList;
	private List cobCobradoresList;
	private Boolean isBotonConsultar;

	@Override
	protected String getSalirForward() {
		return "mantenimientoCOBAsignacionCarteraCobradorList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOBAsignacionCarteraCobradorForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBAsignacionCarteraCobradorSearchForm searchForm = new MantenimientoCOBAsignacionCarteraCobradorSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Entering: setFindAttributes");
		}

		/* obteniendo valores */
		MantenimientoCOBAsignacionCarteraCobradorSearchForm f = (MantenimientoCOBAsignacionCarteraCobradorSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		AsignacionCarteraCobrador bean = new AsignacionCarteraCobrador();
		bean.setCodigoPais(pais.getCodigo());
		bean.setCodigoEtapaDeuda(f.getCodigoEtapaDeuda());

		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this
				.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();

		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this
				.getBean("spusicc.mantenimientoCOBGenericoService");
		List lista = service.getListaAsignacionCarteraCobrador(bean);

		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		try {
			String codigoPais = cobradorSeleccionado.get("codigoPais")
					.toString();
			String etapa = cobradorSeleccionado.get("codigoEtapaDeuda")
					.toString();
			String cobrador = cobradorSeleccionado.get("codigoCobrador")
					.toString();

			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

			AsignacionCarteraCobrador AsignacionCarteraCobrador = new AsignacionCarteraCobrador();

			AsignacionCarteraCobrador.setCodigoPais(codigoPais);
			AsignacionCarteraCobrador.setCodigoEtapaDeuda(etapa);
			AsignacionCarteraCobrador.setCodigoCobrador(cobrador);

			service.deleteAsignacionCarteraCobrador(AsignacionCarteraCobrador, usuario);			

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {

		return "mantenimientoCOBAsignacionCarteraCobradorForm.deleted";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoCOBAsignacionCarteraCobradorForm f = (MantenimientoCOBAsignacionCarteraCobradorForm) this.formMantenimiento;
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
		AsignacionCarteraCobrador cobrador = new AsignacionCarteraCobrador();

		try {
			BeanUtils.copyProperties(cobrador, f);

			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}

				if (!f.isIndicadorActivoBool()) {
					cobrador.setIndicadorActivo("0");
					f.setIndicadorActivo("0");
				} else {
					cobrador.setIndicadorActivo("1");
					f.setIndicadorActivo("1");
				}

				Map params = new HashMap();
				params = BeanUtils.describe(cobrador);
				params.put("oldCodigoCobrador", f.getOldCodigoCobrador());
				params.put("usuarioModificacion",
						(usuario.getAuditInfo().getUpdatedBy()));
				params.put("fechaModificacion", DateUtil.convertDateToString(
						Constants.DEFAULT_DATE_TIME_FORMAT, new Date()));

				service.updateAsignacionCarteraCobrador(params);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}

				if (!f.isIndicadorActivoBool()) {
					cobrador.setIndicadorActivo("0");
					f.setIndicadorActivo("0");
				} else {
					cobrador.setIndicadorActivo("1");
					f.setIndicadorActivo("1");
				}
				
				service.insertAsignacionCarteraCobrador(cobrador, usuario);
			}
		this.salirGrabarPantallaPadre = true;
		} catch (Exception e) {
			throw new Exception(this.obtieneMensajeErrorException(e));
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("JFA Action: Entering setAddAttributes");
		this.isBotonConsultar = false;

		MantenimientoCOBAsignacionCarteraCobradorForm f = new MantenimientoCOBAsignacionCarteraCobradorForm();

		// Seteando Codigo de Pais
		f.setIndicadorActivo("0");
		f.setIndicadorActivoBool(false);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setObservaciones("");
		f.setCodigoCobrador("");
		f.setImportePorcentajeAsignacion("");
		f.setIndicadorActivo("");
		f.setOldCodigoCobrador("");

		loadCombos();

		/* METODO CONSULTAR */
		log.debug("JFA Action: Entering setConsultarAttributes");

		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String codigoPais = cobradorSeleccionado.get("codigoPais")
					.toString();
			String etapa = cobradorSeleccionado.get("codigoEtapaDeuda")
					.toString();
			String cobrador = cobradorSeleccionado.get("codigoCobrador")
					.toString();

			if (codigoPais != null && etapa != null && cobrador != null) {
				MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

				AsignacionCarteraCobrador AsignacionCarteraCobrador = new AsignacionCarteraCobrador();

				AsignacionCarteraCobrador.setCodigoPais(codigoPais);
				AsignacionCarteraCobrador.setCodigoEtapaDeuda(etapa);
				AsignacionCarteraCobrador.setCodigoCobrador(cobrador);

				log.debug("JFA - obtener Bean");
				AsignacionCarteraCobrador = (AsignacionCarteraCobrador) service
						.getAsignacionCarteraCobrador(AsignacionCarteraCobrador);

				BeanUtils.copyProperties(f, AsignacionCarteraCobrador);
				f.setOldCodigoCobrador(AsignacionCarteraCobrador
						.getCodigoCobrador());
				if (AsignacionCarteraCobrador.getIndicadorActivo()
						.equalsIgnoreCase("1")) {
					f.setIndicadorActivoBool(true);
					f.setIndicadorActivo("1");

				} else {
					f.setIndicadorActivoBool(false);
					f.setIndicadorActivo("0");
				}

				f.setNewRecord(false);
				log.debug("JFA - Todo OK");
			}
		}
		
		if (this.accion.equals(this.ACCION_CONSULTAR)) {
			this.isBotonConsultar = true;
		}

		/* FIN METODO CONSULTAR */

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Entering: setViewAttributes");
		}

		MantenimientoCOBAsignacionCarteraCobradorSearchForm f = (MantenimientoCOBAsignacionCarteraCobradorSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this
				.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();
		this.isBotonConsultar = false;

		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOBAsignacionCarteraCobradorForm cobradorForm = (MantenimientoCOBAsignacionCarteraCobradorForm) this.formMantenimiento;
		boolean isNew = cobradorForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCOBAsignacionCarteraCobradorForm.add";
		} else {
			return "mantenimientoCOBAsignacionCarteraCobradorForm.updated";
		}
	}

	/**
	 * @throws Exception
	 */
	private void loadCombos() throws Exception {
		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this
				.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();

		/* Obteniedo la Lista de cobradores */
		this.cobCobradoresList = consultaCOBService.getCobradoresActivos();
	}

	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	public List getCobCobradoresList() {
		return cobCobradoresList;
	}

	public void setCobCobradoresList(List cobCobradoresList) {
		this.cobCobradoresList = cobCobradoresList;
	}

	/**
	 * @return the isBotonConsultar
	 */
	public Boolean getIsBotonConsultar() {
		return isBotonConsultar;
	}

	/**
	 * @param isBotonConsultar the isBotonConsultar to set
	 */
	public void setIsBotonConsultar(Boolean isBotonConsultar) {
		this.isBotonConsultar = isBotonConsultar;
	}
}