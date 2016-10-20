package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.DatosConsultoraBasico;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoChequeDetalle;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosChequesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionPagosChequesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked", "rawtypes"})
public class MantenimientoCCCDigitacionPagosChequesAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7689156851648383118L;
	String numeroLoteInterno;
	private List siccCuentaCorrienteList;
	private List cccPagosCheque;
	private List listaTipoDocumento;
	private List listaBanco;
	private List listaCampos;
	private List listaCampos1;
	private Boolean ejecutar;
	private Boolean validacion;
	private Boolean botonEnviar;
	private List detallesList;
	private DataTableModel datatableCCCD;
	private Object columnasSeleccionadas;

	/**
	 * Metodo para cargar combos
	 */
	private void loadCombos() {
		MantenimientoCCCDigitacionPagosChequesForm form = (MantenimientoCCCDigitacionPagosChequesForm) this.formBusqueda;
		Map criteria = new HashMap();
		List listaAux = new ArrayList();
		ProcesoSTOEjecucionValidacionesService serviceSTO = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoCCCDigitacionPagosChequesService service = (MantenimientoCCCDigitacionPagosChequesService) getBean("spusicc.mantenimientoCCCDigitacionPagosChequesService");
		this.listaBanco = service.getBancosCheques(criteria);
		this.listaTipoDocumento = serviceSTO.getTiposDocumentosIdentidadSTO();
		for (int i = 0; i < listaTipoDocumento.size(); i++) {
			Base base = (Base) this.listaTipoDocumento.get(i);
			if(	StringUtils.equalsIgnoreCase(base.getDescripcion(),Constants.TIPO_DOCUMENTO_RUT)){
				listaAux.add(base);
				break;
			}
				
		}
		for (int j = 0; j < listaTipoDocumento.size(); j++) {
			Base base = (Base) this.listaTipoDocumento.get(j);
			if(!StringUtils.equalsIgnoreCase(base.getDescripcion(),Constants.TIPO_DOCUMENTO_RUT)){
				listaAux.add(base);
			}	
		}
		
		this.listaTipoDocumento = listaAux;
		
	}

	/**
	 * Metodo para guardar una fila
	 * 
	 * @throws Exception
	 */
	public void save(ActionEvent evt) {

		String mensaje = "";
		try {
			int tamanioLista = this.listaCampos1.size();
			

			if (tamanioLista < 0)
				return;

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			// Extraemos el usuario de la sesi�n
			String codigoUsuario = usuario.getLogin();

			ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
			MantenimientoCCCDigitacionPagosChequesForm f = (MantenimientoCCCDigitacionPagosChequesForm) this.formBusqueda;

			Map criteria = new HashMap();

			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoPais", f.getCodigoPais());

			List detallePagosChequesList = new ArrayList();

			serviceCCC.getNumeroLote(criteria);

			numeroLoteInterno = criteria.get("numeroLote").toString();

			if (log.isDebugEnabled()) {
				log.debug("JFA Obteniendo Numero de Lote " + numeroLoteInterno);
			}

			// Carga los detalles
			if (this.cccPagosCheque != null)
				detallePagosChequesList = this.cccPagosCheque;

			this.setDetalles();

			MantenimientoCCCDigitacionPagosChequesService service = (MantenimientoCCCDigitacionPagosChequesService) getBean("spusicc.mantenimientoCCCDigitacionPagosChequesService");
			service.generarPagoCheque(criteria, this.detallesList);

			if (log.isDebugEnabled()) {
				log.debug("JFA : Generando Mensajes");
			}
			mensaje = this
					.getResourceMessage("mantenimientoCCCDigitacionPagosBancariosManualesForm.msj.registrar");
			this.addInfo("Info : ", mensaje);
			this.cccPagosCheque = new ArrayList();

		} catch (Exception e) {	
			this.addError("Error : ",this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Borra los items seleccionados
	 * 
	 */
	public void delete(ActionEvent evt) {
		String mensaje = "";
		try {
			MantenimientoCCCDigitacionPagosChequesForm o = (MantenimientoCCCDigitacionPagosChequesForm) this.columnasSeleccionadas;

			for (int i = 0; i < this.listaCampos1.size(); i++) {

				MantenimientoCCCDigitacionPagosChequesForm objComparar = (MantenimientoCCCDigitacionPagosChequesForm) this.listaCampos1
						.get(i);
				String codigoConsultoraLista = objComparar.getCodigoConsultora();
				String codigoConsultoraSeleccionada = o.getCodigoConsultora();
				if (StringUtils.equalsIgnoreCase(codigoConsultoraLista,	codigoConsultoraSeleccionada)) {
					this.listaCampos1.remove(i);
				}
			}
			this.datatableCCCD = new DataTableModel(this.listaCampos1);
		} catch (Exception e) {
			this.addError("Error : ",this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String parametroVentana = externalContext.getRequestParameterMap().get("parametroVentana");
		if(StringUtils.equalsIgnoreCase(parametroVentana, "confirmDialogEjecutar")){
			for (int j = 0; j < listaCampos1.size(); j++) {
				MantenimientoCCCDigitacionPagosChequesForm bean = (MantenimientoCCCDigitacionPagosChequesForm) this.listaCampos1
						.get(j);
				bean.setFechaCobro(DateUtil.convertDateToString(bean.getFechaCobroD()));
				String nombreConsultora = bean.getNombreConsultora();
				String importePago = bean.getImportePago();
				if(StringUtils.isBlank(importePago) || StringUtils.isBlank(nombreConsultora) ){
					return this.getResourceMessage("ccc.mensaje.completarDatos");
				}
			}	
		}

					
		return null;
	}

	/**
	 * @param f
	 * @param detallesList
	 *            Setea los datos del Detalle de Pagos Bancarios
	 */
	private void setDetalles() {
		this.detallesList = new ArrayList();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		for (int i = 0; i < this.listaCampos1.size(); i++) {
			log.debug("JFA : Recorriendo lista de codigos");
			MantenimientoCCCDigitacionPagosChequesForm f = (MantenimientoCCCDigitacionPagosChequesForm) this.listaCampos1
					.get(i);

			PagoChequeDetalle cccPagoChequeDetalle = new PagoChequeDetalle();
			cccPagoChequeDetalle.setBanco(f.getBanco());
			cccPagoChequeDetalle.setTipoDocumento(f.getStrComboTipoDocumento());
			cccPagoChequeDetalle.setDocumentoIdentidad(f
					.getDocumentoIdentidadConsultora());
			cccPagoChequeDetalle.setCodigoConsultora(f.getCodigoConsultora());
			cccPagoChequeDetalle.setFechaCobro(f.getFechaCobro());
			cccPagoChequeDetalle.setImportePago(f.getImportePago());
			if (!StringUtils.isBlank(cccPagoChequeDetalle.getImportePago())) {
				this.detallesList.add(cccPagoChequeDetalle);
			}

		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCDigitacionPagosChequesList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCDigitacionPagosChequesForm form = new MantenimientoCCCDigitacionPagosChequesForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/**
	 * 
	 */
	public void inicializar() {
		this.mostrarBotonBuscar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarListaBusqueda = true;
		this.mostrarCriteriosBusqueda = true;
	}

	/**
	 * Metodo para agregar una fila
	 */
	public void agregarUnaFila() {	
		try {
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
			int indice = Integer.parseInt(value);		
			int i = indice;

			MantenimientoCCCDigitacionPagosChequesForm bean = (MantenimientoCCCDigitacionPagosChequesForm) this.listaCampos1
					.get(i);
			bean.setFechaCobro(DateUtil.convertDateToString(bean.getFechaCobroD()));
			String codigoConsultora = bean.getCodigoConsultora();
			String nombreConsultora = bean.getNombreConsultora();
			String fechaHoy = bean.getFechaCobro();
			String importePago = bean.getImportePago();
			if(StringUtils.isBlank(importePago) || StringUtils.isBlank(nombreConsultora) ){
				this.addWarn("Error : ", this.getResourceMessage("ccc.mensaje.completarDatos"));
				return;
			}
			MantenimientoCCCDigitacionPagosChequesForm objParaLLenar = new MantenimientoCCCDigitacionPagosChequesForm();

			if (!codigoConsultora.isEmpty() && !fechaHoy.isEmpty()
					&& !importePago.isEmpty()) {
				MantenimientoCCCDigitacionPagosChequesForm base = new MantenimientoCCCDigitacionPagosChequesForm();
				base.setFechaCobroD(new Date());
				base.setStrComboTipoDocumento(Constants.TIPO_DOCUMENTO_RUT);
				this.listaCampos1.add(base);
			}
		} catch (Exception e) {
			this.addError("Error : " , this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param indice
	 */
	public void validaConsultoraOnEnter() {
		try {
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
			int indice = Integer.parseInt(value);
			int i = indice;
			if (i < 0)
				return;
			MantenimientoCCCDigitacionPagosChequesForm bean = (MantenimientoCCCDigitacionPagosChequesForm) this.listaCampos1
					.get(i);

			String mensaje = null;
			if (bean == null) {
				return;
			}
			if (StringUtils.isBlank(bean.getCodigoConsultora())
					&& StringUtils.isBlank(bean.getStrComboTipoDocumento())
					&& StringUtils.isBlank(bean.getDocumentoIdentidadConsultora())) {
				return;
			} else {

				String codigoConsultora = bean.getCodigoConsultora();
				String tipoDocumento = bean.getStrComboTipoDocumento();
				String numeroDocumento = bean.getDocumentoIdentidadConsultora();

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
						.getCodigo();
				DatosConsultoraBasico[] obtenerDatosConsultora = null;
				if (numeroDocumento.equals("")) {
					obtenerDatosConsultora = aSvc
							.getCabeceraConsultoraByDocIdentidad(codigoPais,
									numeroDocumento, "", codigoConsultora);
					if (obtenerDatosConsultora == null) {
						this.ejecutar = false;
						bean.setDocumentoIdentidadConsultora("");
						mensaje = this
								.getResourceMessage("mensaje.documentoIdentidadNoExiste");
						this.addError("Error : ", mensaje);
						return;
					}
					bean.setNombreConsultora(obtenerDatosConsultora[0]
							.getNombreConsultora());
					bean.setFechaCobro(DateUtil.convertDateToString(bean.getFechaCobroD()));

					
				} else {
					obtenerDatosConsultora = aSvc
							.getCabeceraConsultoraByDocIdentidad(codigoPais,
									numeroDocumento, tipoDocumento,
									codigoConsultora);
					if (obtenerDatosConsultora != null) {
						bean.setNombreConsultora(obtenerDatosConsultora[0]
								.getNombreConsultora());
						bean.setCodigoConsultora(obtenerDatosConsultora[0].getCodigoConsultora());
						bean.setFechaCobro(DateUtil.convertDateToString(bean.getFechaCobroD()));
					} else {
						this.ejecutar = false;
						bean.setDocumentoIdentidadConsultora("");

						mensaje = this
								.getResourceMessage("mensaje.documentoIdentidadNoExiste");
						this.addError("Error : ", mensaje);
						return;
					}
				}

				if (obtenerDatosConsultora != null) {
					this.ejecutar = true;
				} else {
					this.ejecutar = false;
					mensaje = this
							.getResourceMessage("mensaje.documentoIdentidadNoExiste");
					this.addError("Error : ", mensaje);
					return;
				}
				if (this.ejecutar) {
					String validar = aSvc.getClasificacionConsultoraByDocIdentidad(
							codigoPais, numeroDocumento, tipoDocumento,
							codigoConsultora);
					if (validar.equals(Constants.SI)) {
						this.validacion = false;
						this.botonEnviar = true;
						mensaje = this
								.getResourceMessage("mensaje.clasificacionActivaExistentes");
						this.addError("Error : ", mensaje);
						return;
					} else {
						this.validacion = true;
						this.botonEnviar = false;
					}
				}
				if (this.validacion) {
					String validacion2 = aSvc.getActivaConsultoraByDocIdentidad(
							codigoPais, numeroDocumento, tipoDocumento,
							codigoConsultora, "0");
					if (validacion2.equals(Constants.SI)) {
						this.validacion = false;
						this.botonEnviar = true;
						mensaje = this
								.getResourceMessage("mensaje.clasificacionActivaExistentes");
						this.addError("Error : ", mensaje);
						return;
					} else {
						this.validacion = true;
						this.botonEnviar = false;
					}
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		inicializar();
		loadCombos();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		this.listaCampos1 = new ArrayList();
		MantenimientoCCCDigitacionPagosChequesForm base = new MantenimientoCCCDigitacionPagosChequesForm();
		base.setFechaCobroD(new Date());
		this.listaCampos1.add(base);
		this.datatableCCCD = new DataTableModel(this.listaCampos1);
		// this.listaCampos1 = new ArrayList();
		// this.listaCampos1.add((MantenimientoCCCDigitacionPagosChequesForm)
		// new Object());

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		// Seteando el tamanio del Campo Consultora
		MantenimientoCCCDigitacionPagosChequesForm f = (MantenimientoCCCDigitacionPagosChequesForm) this.formBusqueda;
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		f.setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(
				pais.getCodigo()).toString());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setCodigoPais(pais.getCodigo());
		f.setFechaHoy(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaCobroD(new Date());
		if (log.isDebugEnabled()) {
			log.debug("------------------ "
					+ clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
		}
		f.setIndicadorHayRegistros("0");

		

		// Obteniedo el listado de las Cuentas Corrientes Bancarias
		criteria.put("codigoPais", pais.getCodigo());
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		this.siccCuentaCorrienteList = serviceCCC
				.getCuentasCorrientesBancariasList(criteria);

		if (log.isDebugEnabled()) {
			log.debug("JFA Todo Ok: Redireccionando");
		}
	}

	/**
	 * @return the numeroLoteInterno
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}

	/**
	 * @param numeroLoteInterno
	 *            the numeroLoteInterno to set
	 */
	public void setNumeroLoteInterno(String numeroLoteInterno) {
		this.numeroLoteInterno = numeroLoteInterno;
	}

	/**
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList
	 *            the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the cccPagosCheque
	 */
	public List getCccPagosCheque() {
		return cccPagosCheque;
	}

	/**
	 * @param cccPagosCheque
	 *            the cccPagosCheque to set
	 */
	public void setCccPagosCheque(List cccPagosCheque) {
		this.cccPagosCheque = cccPagosCheque;
	}

	/**
	 * @return the listaTipoDocumento
	 */
	public List getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	/**
	 * @param listaTipoDocumento
	 *            the listaTipoDocumento to set
	 */
	public void setListaTipoDocumento(List listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	/**
	 * @return the listaBanco
	 */
	public List getListaBanco() {
		return listaBanco;
	}

	/**
	 * @param listaBanco
	 *            the listaBanco to set
	 */
	public void setListaBanco(List listaBanco) {
		this.listaBanco = listaBanco;
	}

	/**
	 * @return the listaCampos
	 */
	public List getListaCampos() {
		return listaCampos;
	}

	/**
	 * @param listaCampos
	 *            the listaCampos to set
	 */
	public void setListaCampos(List listaCampos) {
		this.listaCampos = listaCampos;
	}

	/**
	 * @return the ejecutar
	 */
	public Boolean getEjecutar() {
		return ejecutar;
	}

	/**
	 * @param ejecutar
	 *            the ejecutar to set
	 */
	public void setEjecutar(Boolean ejecutar) {
		this.ejecutar = ejecutar;
	}

	/**
	 * @return the validacion
	 */
	public Boolean getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion
	 *            the validacion to set
	 */
	public void setValidacion(Boolean validacion) {
		this.validacion = validacion;
	}

	/**
	 * @return the botonEnviar
	 */
	public Boolean getBotonEnviar() {
		return botonEnviar;
	}

	/**
	 * @param botonEnviar
	 *            the botonEnviar to set
	 */
	public void setBotonEnviar(Boolean botonEnviar) {
		this.botonEnviar = botonEnviar;
	}

	/**
	 * @return the detallesList
	 */
	public List getDetallesList() {
		return detallesList;
	}

	/**
	 * @param detallesList
	 *            the detallesList to set
	 */
	public void setDetallesList(List detallesList) {
		this.detallesList = detallesList;
	}

	/**
	 * @return the datatableCCCD
	 */
	public DataTableModel getDatatableCCCD() {
		return datatableCCCD;
	}

	/**
	 * @param datatableCCCD
	 *            the datatableCCCD to set
	 */
	public void setDatatableCCCD(DataTableModel datatableCCCD) {
		this.datatableCCCD = datatableCCCD;
	}

	/**
	 * @param listaCampos1
	 *            the listaCampos1 to set
	 */
	public void setListaCampos1(List listaCampos1) {
		this.listaCampos1 = listaCampos1;
	}

	/**
	 * @return the listaCampos1
	 */
	public List getListaCampos1() {
		return listaCampos1;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Object getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas
	 *            the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Object columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}
}