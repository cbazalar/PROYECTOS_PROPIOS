package biz.belcorp.ssicc.web.spusicc.flx.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoObjetada;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraObjetadaForm;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraObjetadaSearchForm;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraProcesoMasivoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoFLXConsultoraObjetadaSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7433132017342046805L;
	private List flxConsultoraObjetadaList;
	private List flxConsultoraObjetadaCargaMasivaList;
	private String flxConsultoraObjetadaCargaMasivaErroneos;
	private String mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado;
	private MantenimientoFLXConsultoraProcesoMasivoForm objProcesoMasivo;
	private String attachment = "";
	private Boolean mostrarGrilla;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoFLXConsultoraObjetadaList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoFLXConsultoraObjetadaForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXConsultoraObjetadaSearchForm form = new MantenimientoFLXConsultoraObjetadaSearchForm();
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
		MantenimientoFLXConsultoraObjetadaSearchForm f = (MantenimientoFLXConsultoraObjetadaSearchForm) this.formBusqueda;
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService) getBean("spusicc.mantenimientoFLXConsultoraService");
		Map criteria = BeanUtils.describe(f);
		List lista = service.getConsultorasObjetadaByCriteria(criteria);
		this.flxConsultoraObjetadaList = lista;
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
		if (log.isDebugEnabled())
			log.debug("setDeleteAttributes");
		// Creamos las instancias de los objetos a usar
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ConsultoraFlexipagoObjetada bean = (ConsultoraFlexipagoObjetada) this.beanRegistroSeleccionado;
		// ConsultoraFlexipagoPK bean = (ConsultoraFlexipagoPK)
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService) getBean("spusicc.mantenimientoFLXConsultoraService");
		String codigoPais = bean.getCodigoPais();
		String codigoCliente = bean.getCodigoCliente();
		String codigoCampanya = bean.getCodigoCampanyaFacturacion();
		try {
			String id = codigoPais + "|" + codigoCliente + "|" + codigoCampanya;

			if (log.isDebugEnabled())
				log.debug("Id seleccionado = " + id);

			if (StringUtils.isNotBlank(id)) {
				String[] codigosCompuestos = StringUtils.split(id, "~");

				for (int i = 0; i < codigosCompuestos.length; i++) {
					String[] codigoCompuesto = StringUtils.split(
							codigosCompuestos[i], "|");
					ConsultoraFlexipagoPK pk = new ConsultoraFlexipagoPK(
							codigoCompuesto[0], codigoCompuesto[1],
							codigoCompuesto[2]);

					service.deleteConsultoraObjetada(pk, usuario);
				}
			}
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));
		}
		return true;
	}

	// Abrir el popup de busqueda
	public void abrirPopup(ActionEvent event) {
		try {
			this.mostrarListaBusqueda = false;
			this.mostrarBotonNuevo = false;
			this.mostrarBotonEliminar = false;
			this.redireccionarPagina("mantenimientoFLXConsultoraObjetadaPopup");

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// metodo que sale del popup
	public void atras(ActionEvent actionEvent) {
		try {
			this.mostrarListaBusqueda = true;
			this.mostrarBotonNuevo = true;
			this.mostrarBotonEliminar = true;
			this.redireccionarPagina("mantenimientoFLXConsultoraObjetadaList");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled())
			log.debug("setSaveAttributes");
		
		MantenimientoFLXConsultoraObjetadaForm f = (MantenimientoFLXConsultoraObjetadaForm) this.formMantenimiento;
		if(validarCodigoConsultora()){
		}else{
			return false;
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String mensaje = null;
		try {
		
			
			ConsultoraFlexipagoObjetada consultoraFlexipagoObjetada = new ConsultoraFlexipagoObjetada();

			BeanUtils.copyProperties(consultoraFlexipagoObjetada, f);
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService) getBean("spusicc.mantenimientoFLXConsultoraService");

			String flagValidacion = service
					.validarCodigoConsultora(consultoraFlexipagoObjetada
							.getCodigoCliente());

			if (StringUtils.equals(flagValidacion, Constants.SI)) {
				ConsultoraFlexipagoObjetada co = service
						.getConsultoraObjetada(new ConsultoraFlexipagoPK(
								consultoraFlexipagoObjetada.getCodigoPais(),
								consultoraFlexipagoObjetada.getCodigoCliente(),
								consultoraFlexipagoObjetada
										.getCodigoCampanyaFacturacion()));

				if (co != null)
					service.updateConsultoraObjetada(
							consultoraFlexipagoObjetada, usuario);
				else
					service.insertConsultoraObjetada(
							consultoraFlexipagoObjetada, usuario);
			} else
				throw new InvalidIdentifierException(
						ConsultoraFlexipagoObjetada.class,
						consultoraFlexipagoObjetada.getCodigoCliente());

			mensaje = this
					.getResourceMessage("consultoraFlexipagoObjetada.added");
			this.addInfo("Info : ", mensaje);
		} catch (InvalidIdentifierException iie) {
			this.addError("Error: ", this.obtieneMensajeErrorException(iie));
			return false;
		} catch (Exception ex) {
			this.addError("Error: ", this.obtieneMensajeErrorException(ex));
			return false;
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
		this.mostrarListaBusqueda = true;
		MantenimientoFLXConsultoraObjetadaForm form = new MantenimientoFLXConsultoraObjetadaForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		return form;
	}

	/**
	 * Carga archivo al servidor
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		if (event != null) {
			this.objProcesoMasivo.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.loadfile();
		}
	}

	/**
	 * Lee archivo
	 * 
	 * @throws Exception
	 */
	public void loadfile() {

		if (log.isDebugEnabled())
			log.debug("loadfile");

		MantenimientoFLXConsultoraProcesoMasivoForm f = this.objProcesoMasivo;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ExcelUtil excelUtil = null;
		List listaConsultoras = new ArrayList();
		int erroneos = 0;
		String mensaje = null;
		try {
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService) getBean("spusicc.mantenimientoFLXConsultoraService");

			UploadedFile archivo = f.getClienteFile();

			excelUtil = new ExcelUtil(archivo.getInputstream());
			excelUtil.initSheet(0);

			while (excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				String codigoConsultora = String.valueOf(mapRow.get("0"));
				String campanyaFacturacion = String.valueOf(mapRow.get("1"));
				String valido = service
						.validarCodigoConsultora(codigoConsultora);

				ConsultoraFlexipago c = new ConsultoraFlexipago();

				if (codigoConsultora.endsWith(".0")) {
					codigoConsultora = codigoConsultora.substring(0,
							codigoConsultora.length() - 2);
				}

				if (campanyaFacturacion.endsWith(".0")) {
					campanyaFacturacion = campanyaFacturacion.substring(0,
							campanyaFacturacion.length() - 2);
				}

				c.setCodigoPais(pais.getCodigo());
				c.setCodigoCliente(codigoConsultora);
				c.setCodigoCampanyaFacturacion(campanyaFacturacion);
				c.setFlagActivo(valido);

				listaConsultoras.add(c);

				if (StringUtils.equals(valido, Constants.NO))
					erroneos++;
			}
			excelUtil.cerrar();

			this.flxConsultoraObjetadaCargaMasivaList = listaConsultoras;

			if (erroneos > 0)
				this.flxConsultoraObjetadaCargaMasivaErroneos = Integer
						.toString(erroneos);

			if (log.isDebugEnabled())
				log.debug("Tamaño de Lista: " + listaConsultoras.size());

			if (this.flxConsultoraObjetadaCargaMasivaList != null) {
				this.mostrarGrilla = true;
			}

		} catch (Exception ex) {
			if (excelUtil != null)
				excelUtil.cerrar();
			mensaje = this
					.getResourceMessage("mantenimientoFLXConsultoraForm.file.load.error");
			this.addError("Error : ", mensaje);
		}
	}

	// metodo que inicializa el popup
	public void definir(ActionEvent event) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'definirUA'");
		}
		String ventana = "PF('dialogMantenimientoForm3').show()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = false;
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled())
			log.debug("setViewAttributes");
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = true;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFLXConsultoraObjetadaSearchForm f = (MantenimientoFLXConsultoraObjetadaSearchForm) this.formBusqueda;
		this.objProcesoMasivo = new MantenimientoFLXConsultoraProcesoMasivoForm();
		f.setCodigoPais(pais.getCodigo());
		this.mostrarGrilla = false;

	}

	public Boolean validarCodigoConsultora() {
		try {
			MantenimientoFLXConsultoraObjetadaForm f = (MantenimientoFLXConsultoraObjetadaForm) this.formMantenimiento;
			String codigoCliente = f.getCodigoCliente();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			String codigocliente = aSvc.getNombreCliente(codigoCliente);

			String mensaje = null;
			if (codigocliente != null) {
				return true;
			} else {
				mensaje = "Codigo de Cliente no existe";
				this.addError("Error : ", mensaje);
				return false;

			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return false;

		}

	}

	// metodo que sale del popup
	public void salir(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'salirUA'");
		}
		String ventana = "PF('dialogMantenimientoForm3').hide()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = true;
	}

	public void processfile() {

		if (log.isDebugEnabled())
			log.debug("processfile");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService) getBean("spusicc.mantenimientoFLXConsultoraService");
		String mensaje = null;
		try {

			List listaConsultoras = flxConsultoraObjetadaCargaMasivaList;

			if (listaConsultoras != null && listaConsultoras.size() > 0)
				service.updateMasivoConsultorasObjetadas(listaConsultoras,
						Constants.FLX_CODIGO_ACCION_REGISTRAR_OBJECION, usuario);

			mensaje = this
					.getResourceMessage("mantenimientoFLXConsultoraForm.file.process.ok");
			this.addInfo("Info : ", mensaje);
			this.mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado = Constants.NUMERO_UNO;

		} catch (Exception ex) {
			mensaje = this
					.getResourceMessage("mantenimientoFLXConsultoraForm.file.process.error");
			this.addError("Error : ", mensaje);
		}
	}

	/**
	 * @return the flxConsultoraObjetadaList
	 */
	public List getFlxConsultoraObjetadaList() {
		return flxConsultoraObjetadaList;
	}

	/**
	 * @param flxConsultoraObjetadaList
	 *            the flxConsultoraObjetadaList to set
	 */
	public void setFlxConsultoraObjetadaList(List flxConsultoraObjetadaList) {
		this.flxConsultoraObjetadaList = flxConsultoraObjetadaList;
	}

	/**
	 * @return the flxConsultoraObjetadaCargaMasivaList
	 */
	public List getFlxConsultoraObjetadaCargaMasivaList() {
		return flxConsultoraObjetadaCargaMasivaList;
	}

	/**
	 * @param flxConsultoraObjetadaCargaMasivaList
	 *            the flxConsultoraObjetadaCargaMasivaList to set
	 */
	public void setFlxConsultoraObjetadaCargaMasivaList(
			List flxConsultoraObjetadaCargaMasivaList) {
		this.flxConsultoraObjetadaCargaMasivaList = flxConsultoraObjetadaCargaMasivaList;
	}

	/**
	 * @return the flxConsultoraObjetadaCargaMasivaErroneos
	 */
	public String getFlxConsultoraObjetadaCargaMasivaErroneos() {
		return flxConsultoraObjetadaCargaMasivaErroneos;
	}

	/**
	 * @param flxConsultoraObjetadaCargaMasivaErroneos
	 *            the flxConsultoraObjetadaCargaMasivaErroneos to set
	 */
	public void setFlxConsultoraObjetadaCargaMasivaErroneos(
			String flxConsultoraObjetadaCargaMasivaErroneos) {
		this.flxConsultoraObjetadaCargaMasivaErroneos = flxConsultoraObjetadaCargaMasivaErroneos;
	}

	/**
	 * @return the
	 *         mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado
	 */
	public String getMantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado() {
		return mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado;
	}

	/**
	 * @param mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado
	 *            the
	 *            mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado
	 *            to set
	 */
	public void setMantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado(
			String mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado) {
		this.mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado = mantenimientoFLXConsultoraObjetadaProcesoMasivoForm_estadoProcesado;
	}

	/**
	 * @return the objProcesoMasivo
	 */
	public MantenimientoFLXConsultoraProcesoMasivoForm getObjProcesoMasivo() {
		return objProcesoMasivo;
	}

	/**
	 * @param objProcesoMasivo
	 *            the objProcesoMasivo to set
	 */
	public void setObjProcesoMasivo(
			MantenimientoFLXConsultoraProcesoMasivoForm objProcesoMasivo) {
		this.objProcesoMasivo = objProcesoMasivo;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 *            the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}
}