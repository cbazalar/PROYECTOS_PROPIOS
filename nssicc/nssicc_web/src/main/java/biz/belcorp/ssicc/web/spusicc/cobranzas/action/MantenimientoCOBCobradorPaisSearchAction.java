package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CobradorPais;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorPaisForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorPaisSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCOBCobradorPaisSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -3443139709928961032L;

	private Boolean indicadorActividad;
	private Boolean indicadorSupervisor;
	private Boolean indicadorJefe;
	private Boolean indicadorEmailProcesoAsignacion;
	private Boolean indicadorEmailProcesoActualizacion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCOBCobradorPaisList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOBCobradorPaisForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBCobradorPaisSearchForm searchForm = new MantenimientoCOBCobradorPaisSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Entering: setFindAttributes");
		}

		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this
				.getBean("spusicc.mantenimientoCOBGenericoService");

		/* obteniendo valores */
		CobradorPais bean = new CobradorPais();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		bean.setCodigoPais(pais.getCodigo());

		/* Obteniendo Lista */
		List lista = service.getListaCobradorPais(bean);

		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;
		String codigoPais = cobradorSeleccionado.get("codigoPais").toString();
		String codigoCobrador = cobradorSeleccionado.get("codigoCobrador")
				.toString();

		if (codigoPais != null && codigoCobrador != null) {
			try {
				MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

				CobradorPais CobradorPais = new CobradorPais();

				CobradorPais.setCodigoPais(codigoPais);
				CobradorPais.setCodigoCobrador(codigoCobrador);

				service.deleteCobradorPais(CobradorPais, usuario);

				this.getResourceMessage("mantenimientoCOBCobradorPaisForm.deleted");

			} catch (Exception e) {
				this.addError("Error : ", this.obtieneMensajeErrorException(e));
			}
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoCOBCobradorPaisForm f = (MantenimientoCOBCobradorPaisForm) this.formMantenimiento;
		setearValoresBoolSave(f);
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

		CobradorPais cobrador = new CobradorPais();

		try {
			BeanUtils.copyProperties(cobrador, f);

			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateCobradorPais(cobrador, usuario);

			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				service.insertCobradorPais(cobrador, usuario);
			}
		} catch (Exception e) {
			throw new Exception(this.obtieneMensajeErrorException(e));
		}

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
		MantenimientoCOBCobradorPaisForm f = new MantenimientoCOBCobradorPaisForm();
		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		// Seteando Codigo de Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		// Limpiando las propiedades del Form
		f.setCodigoCobrador("");
		f.setNombreCobrador("");
		f.setTelefono("");
		f.setDireccion("");
		f.setEmail("");
		f.setIndicadorActividad("");
		f.setIndicadorSupervisor("");
		f.setIndicadorJefe("");
		f.setIndicadorEmailProcesoAsignacion("");
		f.setIndicadorEmailProcesoActualizacion("");
		f.setDireFTP("");
		f.setDireReceFTP("");
		f.setServFTP("");
		f.setPuerFTP("");
		f.setUsuarioFTP("");
		f.setClaveFTP("");
		setearValoresBool(f);
		this.mostrarBotonSave = true;

		if (!this.accion.equals(this.ACCION_NUEVO)) {

			String codigoPais = cobradorSeleccionado.get("codigoPais")
					.toString();
			String codigoCobrador = cobradorSeleccionado.get("codigoCobrador")
					.toString();

			if (codigoPais != null && codigoCobrador != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigoCobrador);
				}

				MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

				CobradorPais cobradorPais = new CobradorPais();

				cobradorPais.setCodigoPais(codigoPais);
				cobradorPais.setCodigoCobrador(codigoCobrador);

				cobradorPais = (CobradorPais) service
						.getCobradorPais(cobradorPais);

				BeanUtils.copyProperties(f, cobradorPais);
				setearValoresBool(f);
				f.setNewRecord(false);

				if (this.accion.equals(this.ACCION_CONSULTAR))
					this.mostrarBotonSave = false;
				else
					this.mostrarBotonSave = true;

				log.debug("JFA - Todo OK");
			}
		}

		return f;
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
			this.log.debug("JFA Entering: setViewAttributes");
		}

		MantenimientoCOBCobradorPaisSearchForm f = (MantenimientoCOBCobradorPaisSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		/* Obteniendo valores */
		CobradorPais bean = new CobradorPais();
		bean.setCodigoPais(pais.getCodigo());

		/* Obteniendo Lista */
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this
				.getBean("spusicc.mantenimientoCOBGenericoService");

		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}
		
		this.salirGrabarPantallaPadre = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOBCobradorPaisForm cobradorForm = (MantenimientoCOBCobradorPaisForm) this.formMantenimiento;
		boolean isNew = cobradorForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCOBCobradorPaisForm.add";
		} else {
			return "mantenimientoCOBCobradorPaisForm.updated";
		}
	}

	/**
	 * setea valores booleanos
	 * 
	 * @param f
	 */
	private void setearValoresBool(MantenimientoCOBCobradorPaisForm f) {
		if(StringUtils.isNotBlank(f.getIndicadorActividad())){
			this.indicadorActividad = f.getIndicadorActividad().equals("1") ? true
					: false;
		}else
			this.indicadorActividad = false;
		
		if(StringUtils.isNotBlank(f.getIndicadorSupervisor())){
			this.indicadorSupervisor = f.getIndicadorSupervisor().equals("1") ? true: false;
		}else
			this.indicadorSupervisor = false;
		
		if(StringUtils.isNotBlank(f.getIndicadorJefe())){
			this.indicadorJefe = f.getIndicadorJefe().equals("1") ? true : false;
		}else
			this.indicadorJefe = false;
		
		if(StringUtils.isNotBlank(f.getIndicadorEmailProcesoAsignacion())){
			this.indicadorEmailProcesoAsignacion = f.getIndicadorEmailProcesoAsignacion().equals("1") ? true: false;
		}else
			this.indicadorEmailProcesoAsignacion = false;
		
		if(StringUtils.isNotBlank(f.getIndicadorEmailProcesoActualizacion())){
			this.indicadorEmailProcesoActualizacion = f.getIndicadorEmailProcesoActualizacion().equals("1") ? true: false;
		}else
			this.indicadorEmailProcesoActualizacion = false;
	}

	/**
	 * setea valores en string
	 * 
	 * @param f
	 */
	private void setearValoresBoolSave(MantenimientoCOBCobradorPaisForm f) {
		f.setIndicadorActividad(this.indicadorActividad.equals(true) ? "1"
				: "0");
		f.setIndicadorSupervisor(this.indicadorSupervisor.equals(true) ? "1"
				: "0");
		f.setIndicadorJefe(this.indicadorJefe.equals(true) ? "1" : "0");
		f.setIndicadorEmailProcesoAsignacion(this.indicadorEmailProcesoAsignacion
				.equals(true) ? "1" : "0");
		f.setIndicadorEmailProcesoActualizacion(this.indicadorEmailProcesoActualizacion
				.equals(true) ? "1" : "0");
	}

	public Boolean getIndicadorActividad() {
		return indicadorActividad;
	}

	public void setIndicadorActividad(Boolean indicadorActividad) {
		this.indicadorActividad = indicadorActividad;
	}

	public Boolean getIndicadorSupervisor() {
		return indicadorSupervisor;
	}

	public void setIndicadorSupervisor(Boolean indicadorSupervisor) {
		this.indicadorSupervisor = indicadorSupervisor;
	}

	public Boolean getIndicadorJefe() {
		return indicadorJefe;
	}

	public void setIndicadorJefe(Boolean indicadorJefe) {
		this.indicadorJefe = indicadorJefe;
	}

	public Boolean getIndicadorEmailProcesoAsignacion() {
		return indicadorEmailProcesoAsignacion;
	}

	public void setIndicadorEmailProcesoAsignacion(
			Boolean indicadorEmailProcesoAsignacion) {
		this.indicadorEmailProcesoAsignacion = indicadorEmailProcesoAsignacion;
	}

	public Boolean getIndicadorEmailProcesoActualizacion() {
		return indicadorEmailProcesoActualizacion;
	}

	public void setIndicadorEmailProcesoActualizacion(
			Boolean indicadorEmailProcesoActualizacion) {
		this.indicadorEmailProcesoActualizacion = indicadorEmailProcesoActualizacion;
	}
}