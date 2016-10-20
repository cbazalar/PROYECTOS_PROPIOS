package biz.belcorp.ssicc.web.spusicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPERMovimientosBancariosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPERMovimientosBancariosForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPERMovimientosBancariosSearchForm;

/**
 * The Class MantenimientoCOMZonasDemandaAnticipadaSearchAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 09/02/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoPERMovimientosBancariosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2485236288982082862L;
	private List siccSociedadList;
	private List siccCuentaCorrienteList;
	private List siccTipoOrigenDatosList;
	private List siccStatusMovimientoBancarioList;
	private List movimientosBancariosCabeceraList;
	private List movimientosBancariosDetalleList;
	private List siccHorarioList;
	private List siccTipoTransaccionList;
	private MovimientosBancariosDetalle selectDetalle;
	private Integer longitudCampoClientes;
	private String codigoCtaCteElegido;
	private String tipoOrigenDatos;
	private String sociedad;
	private String cuentaCorriente;
	private Date fechaProcesoD;
	private Date fechaPagoD;
	private boolean btnEdit;

	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private boolean mostrarPopupConsultora;

	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

	@ManagedProperty(value = "#{procesoPERProcesarMovimientoAction}")
	private ProcesoPERProcesarMovimientoAction procesoPERProcesarMovimientoAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPERMovimientosBancariosForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPERMovimientosBancariosSearchForm searchForm = new MantenimientoPERMovimientosBancariosSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes
	 * ()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'enter' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoPERMovimientosBancariosSearchForm f = (MantenimientoPERMovimientosBancariosSearchForm) this.formBusqueda;

		// Cargamos los combos a utilizar
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		this.siccSociedadList = siccService.getSociedadesByCodigoPais(pais
				.getCodigo());
		this.siccCuentaCorrienteList = siccService
				.getCuentasCorrientesByCodigoPais(pais.getCodigo());
		this.siccTipoOrigenDatosList = siccService.getTipoOrigenDatos();

		f.setCodigoPais(pais.getCodigo());

		ArrayList resultado = new ArrayList();
		Base[] tipo = new Base[2];
		String[] status = { "PENDIENTE", "CONFIRMADO" };
		tipo[0] = new Base();
		tipo[0].setCodigo(Constants.MOVIMIENTO_BANCARIO_PENDIENTE);
		tipo[0].setDescripcion(status[0]);
		resultado.add(tipo[0]);
		tipo[1] = new Base();
		tipo[1].setCodigo(Constants.MOVIMIENTO_BANCARIO_CONFIRMADO);
		tipo[1].setDescripcion(status[1]);
		resultado.add(tipo[1]);
		this.siccStatusMovimientoBancarioList = resultado;

		/* obteniendo lista con los pendientes */

		f.setCodigoBancoSicc("");
		f.setCodigoSociedad("");
		f.setNumeroLoteInterno("");
		f.setStatusLote("P");

		find();

		/*******************************/

		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.siccHorarioList = siccService.getHorarios();
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());
		this.siccTipoTransaccionList = siccService
				.getTiposTransaccionesByCodigoPais(pais.getCodigo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}

		MantenimientoPERMovimientosBancariosSearchForm f = (MantenimientoPERMovimientosBancariosSearchForm) this.formBusqueda;
		MantenimientoPERMovimientosBancariosService service = (MantenimientoPERMovimientosBancariosService) getBean("spusicc.mantenimientoPERMovimientosBancariosService");
		MovimientosBancariosCabecera cabecera = new MovimientosBancariosCabecera();
		f.setFechaProceso(null);
		BeanUtils.copyProperties(cabecera, f);
		cabecera.setFechaProceso(f.getFechaProcesoD());
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		this.codigoCtaCteElegido = f.getCodigoBancoSicc();
		List resultado = service.getMovimientosBancarios(cabecera);
		this.movimientosBancariosCabeceraList = resultado;
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoPERMovimientosBancariosService service = (MantenimientoPERMovimientosBancariosService) getBean("spusicc.mantenimientoPERMovimientosBancariosService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MovimientosBancariosCabecera cabecera = new MovimientosBancariosCabecera();
		MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();

		Map mapa = (Map) this.beanRegistroSeleccionado;

		cabecera.setCodigoPais(pais.getCodigo());
		cabecera.setNumeroLoteInterno(mapa.get("numeroLoteInterno").toString());
		String tipoOrigenDescripcion = mapa.get("tipoOrigenDatos").toString();
		Map paramTipoOrigen = new HashMap();
		paramTipoOrigen.put("descripcion", tipoOrigenDescripcion);
		List baseList = siccService.getTipoOrigenDatos2(paramTipoOrigen);
		if (baseList.size() == 1) {
			Base base = (Base) baseList.get(0);
			cabecera.setCodigoTipoOrigenDatos(base.getCodigo());
		}
		cabecera.setEstado(Constants.ESTADO_INACTIVO);
		service.updateMovimientosBancariosCabecera(cabecera, usuario);
		detalle.setCodigoPais(pais.getCodigo());
		detalle.setNumeroLoteInterno(mapa.get("numeroLoteInterno").toString());
		detalle.setCodigoTipoOrigenDatos(cabecera.getCodigoTipoOrigenDatos());
		detalle.setEstado(Constants.ESTADO_INACTIVO);
		service.updateMovimientosBancariosDetalle(detalle, usuario);
		cabecera = new MovimientosBancariosCabecera();
		cabecera.setCodigoPais(pais.getCodigo());
		find();
		this.addInfo("Info:",
				this.getResourceMessage("movimientoBancario.cabecera.deleted"));

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {		
		return "mantenimientoPERMovimientosBancariosList";
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
			log.debug("Entering 'save' method");
		}
		MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
		MantenimientoPERMovimientosBancariosService service = (MantenimientoPERMovimientosBancariosService) getBean("spusicc.mantenimientoPERMovimientosBancariosService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {
			if (this.fechaPagoD != null) {
				f.setFechaPago(DateUtil.convertDateToString(this.fechaPagoD));
			}

			MovimientosBancariosCabecera cabecera = new MovimientosBancariosCabecera();
			// BeanUtils.copyProperties(cabecera, f);
			cabecera.setCodigoPais(f.getCodigoPais());
			cabecera.setFechaProceso(this.fechaProcesoD);
			cabecera.setCodigoSociedad(f.getCodigoSociedad());
			cabecera.setCodigoBancoSicc(f.getCodigoBancoSicc());
			cabecera.setNumeroLoteInterno(f.getNumeroLoteInterno());
			cabecera.setCodigoTipoOrigenDatos(f.getCodigoTipoOrigenDatos());

			cabecera.setStatusLote(Constants.PERCEPCIONES_MOVIMIENTO_PROCESADO);
			cabecera.setEstado(Constants.ESTADO_ACTIVO);
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateMovimientosBancariosCabecera(cabecera, usuario);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				String numeroLote = service.getNextNumeroLote(pais.getCodigo(),
						f.getCodigoTipoOrigenDatos());
				cabecera.setNumeroLoteInterno(numeroLote);
				f.setNumeroLoteInterno(numeroLote);
				service.insertMovimientosBancariosCabecera(cabecera, usuario);
			}
			log.debug("------BORRA LOS DETALLES------");
			MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
			detalle.setCodigoPais(cabecera.getCodigoPais());
			detalle.setNumeroLoteInterno(cabecera.getNumeroLoteInterno());
			detalle.setCodigoTipoOrigenDatos(cabecera
					.getCodigoTipoOrigenDatos());
			service.removeMovimientosBancariosDetalle(detalle, usuario);
			log.debug("------INSERTA LOS DETALLES------");
			List resultado = this.movimientosBancariosDetalleList;
			if (resultado != null) {
				Iterator i = resultado.iterator();
				while (i.hasNext()) {
					detalle = new MovimientosBancariosDetalle();
					detalle = (MovimientosBancariosDetalle) i.next();
					detalle.setNumeroLoteInterno(cabecera
							.getNumeroLoteInterno());
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					String hora = sdf.format(new Date(System
							.currentTimeMillis()));
					detalle.setHoraProceso(hora);
					detalle.setUsuarioProceso(usuario.getLogin());
					detalle.setImportePagoPendiente(new Double(detalle
							.getImportePago()).doubleValue());
					detalle.setEstado(Constants.ESTADO_ACTIVO);
					service.insertMovimientosBancariosDetalle(detalle, usuario);
				}
			}
		} catch (Exception e) {
			throw new Exception(
					this.getResourceMessage("movimientoBancario.cabecera.error.duplicated"));
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

		MantenimientoPERMovimientosBancariosForm f = new MantenimientoPERMovimientosBancariosForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.selectDetalle = new MovimientosBancariosDetalle();
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			Map mapa = (Map) this.beanRegistroSeleccionado;

			if (log.isDebugEnabled()) {
				log.debug("Entering 'edit' method");
			}

			f.setEditable(true);
			f.setModified(false);
			// Cargamos los combos a utilizar
			InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			MantenimientoPERMovimientosBancariosService service = (MantenimientoPERMovimientosBancariosService) getBean("spusicc.mantenimientoPERMovimientosBancariosService");

			MovimientosBancariosCabecera cabecera = new MovimientosBancariosCabecera();
			cabecera.setCodigoPais(pais.getCodigo());
			cabecera.setNumeroLoteInterno(mapa.get("numeroLoteInterno")
					.toString());

			String tipoOrigenDescripcion = mapa.get("tipoOrigenDatos")
					.toString();
			this.tipoOrigenDatos = tipoOrigenDescripcion;
			this.sociedad = mapa.get("sociedad").toString();
			this.cuentaCorriente = mapa.get("cuentaCorriente").toString();

			Map paramTipoOrigen = new HashMap();
			paramTipoOrigen.put("descripcion", tipoOrigenDescripcion);
			List baseList = siccService.getTipoOrigenDatos2(paramTipoOrigen);
			if (baseList.size() == 1) {
				Base base = (Base) baseList.get(0);
				cabecera.setCodigoTipoOrigenDatos(base.getCodigo());
			}

			cabecera.setEstado(Constants.ESTADO_ACTIVO);
			List resultado = service.getMovimientosBancariosCabecera(cabecera);
			if (resultado.size() == 1)
				cabecera = (MovimientosBancariosCabecera) resultado.get(0);
			// Copiamos los atributos del bean al form
			MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
			detalle.setCodigoPais(pais.getCodigo());
			detalle.setNumeroLoteInterno(mapa.get("numeroLoteInterno")
					.toString());
			detalle.setCodigoTipoOrigenDatos(cabecera
					.getCodigoTipoOrigenDatos());
			this.movimientosBancariosDetalleList = service
					.getMovimientosBancariosDetalle(detalle);
			BeanUtils.copyProperties(f, cabecera);
			f.setNumeroLoteInterno(mapa.get("numeroLoteInterno").toString());

			SimpleDateFormat sdf = new SimpleDateFormat(
					"EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			this.fechaProcesoD = sdf.parse(f.getFechaProceso());
			f.setFechaProceso(DateUtil.convertDateToString(this.fechaProcesoD));

			f.setNewRecord(false);

			log.debug(f);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date(System.currentTimeMillis());
			this.fechaProcesoD = fecha;
			f.setFechaProceso(sdf.format(fecha));
			f.setCodigoPais(pais.getCodigo());
			f.setCodigoTipoOrigenDatos(Constants.RECAUDO_BANCARIO_MANUAL);
			f.setNumeroLoteInterno("0");
			f.setNewRecord(true);

			this.movimientosBancariosDetalleList = new ArrayList();
		}
		this.btnEdit = false;
		return f;
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
		MantenimientoPERMovimientosBancariosForm mantenimientoForm = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if (isNew) {
			return "movimientoBancario.cabecera.added";
		} else {
			return "movimientoBancario.cabecera.updated";
		}
	}

	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #addMantenimientoPERMovimientosBancarios;
	 * 
	 * @param event
	 */
	public void addMantenimientoPERMovimientosBancarios(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}

		if (!validarDetalleCabecera())
			return;

		MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
		if (this.fechaPagoD != null) {
			f.setFechaPago(DateUtil.convertDateToString(this.fechaPagoD));
		}

		if (this.movimientosBancariosDetalleList == null) {
			this.movimientosBancariosDetalleList = new ArrayList();
		}
		MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
		try {
			// BeanUtils.copyProperties(detalle, f);
			detalle.setCodigoPais(f.getCodigoPais());
			detalle.setTipoTransaccion(f.getTipoTransaccion());
			detalle.setImportePago(f.getImportePago());
			detalle.setFechaPago(this.fechaPagoD);
			detalle.setCodigoConsultora(f.getCodigoConsultora());
			detalle.setCodigoTipoOrigenDatos(f.getCodigoTipoOrigenDatos());
			detalle.setDigitoChequeo(f.getDigitoChequeo());
			detalle.setFechaProceso(this.fechaProcesoD);
			detalle.setHorario(f.getHorario());
			detalle.setNombreOficina(f.getNombreOficina());
			detalle.setNumeroCupon(f.getNumeroCupon());
			detalle.setNumeroDocumento(f.getNumeroDocumento());
			if (f.getNumeroFacturaBoleta() != null
					&& !f.getNumeroFacturaBoleta().isEmpty()) {
				detalle.setNumeroFacturaBoleta(Long.parseLong(f
						.getNumeroFacturaBoleta()));
			}
			detalle.setNumeroLoteInterno(f.getNumeroLoteInterno());
			detalle.setObservacion(f.getObservacion());
			detalle.setOficinaRecaudadora(f.getOficinaRecaudadora());

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		detalle.setConsecutivo(this.movimientosBancariosDetalleList.size() + 1);
		log.debug("MOSTRANDO EL DETALLE " + detalle.getCodigoPais() + "|"
				+ detalle.getFechaProceso() + "|" + detalle.getConsecutivo()
				+ "|" + detalle.getTipoTransaccion() + "|"
				+ detalle.getImportePago() + "|" + detalle.getFechaPago() + "|"
				+ detalle.getCodigoConsultora());
		log.debug(f);
		if (yaExisteDetalle(detalle, this.movimientosBancariosDetalleList)) {
			this.addInfo(
					"Info:",
					this.getResourceMessage("movimientoBancario.cabecera.error.duplicated"));
		} else {
			String fechaProceso = f.getFechaProceso();
			f.setFechaProceso(fechaProceso);
			this.movimientosBancariosDetalleList.add(detalle);
			this.addInfo("Info:",
					this.getResourceMessage("movimientoBancario.detalle.added"));
		}

		/* Limpiamos las variables del detalle */
		f.setTipoTransaccion(null);
		f.setImportePago(null);
		f.setFechaPago(null);
		this.fechaPagoD = null;
		f.setCodigoConsultora(null);
		f.setDigitoChequeo(null);
		f.setHorario(null);
		f.setNombreOficina(null);
		f.setNumeroCupon(null);
		f.setNumeroDocumento(null);
		f.setNumeroFacturaBoleta(null);
		f.setObservacion(null);
		f.setOficinaRecaudadora(null);

	}

	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #removeMantenimientoPERMovimientosBancarios;
	 * 
	 * @param event
	 */
	public void removeMantenimientoPERMovimientosBancarios(ActionEvent event) {
		try {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'remove' method");
			}
			MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
			if (this.fechaPagoD != null) {
				f.setFechaPago(DateUtil.convertDateToString(this.fechaPagoD));
			}

			// String id = request.getParameter("idHijo");

			List resultado = this.movimientosBancariosDetalleList;
			// Si el id ha sido enviado, buscamos la informacion
			// en caso contrario, no hacemos nada, se esta insertando
			// un nuevo registro a la aplicaci�n
			if (this.selectDetalle != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: "
							+ this.selectDetalle);
				}
				List nuevo = new ArrayList();
				Iterator iterator = resultado.iterator();
				int i = 1;
				while (iterator.hasNext()) {
					MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
					detalle = (MovimientosBancariosDetalle) iterator.next();
					if (this.selectDetalle.getConsecutivo() != detalle
							.getConsecutivo()) {
						detalle.setConsecutivo(i);
						nuevo.add(detalle);
						i++;
					}
				}

				this.movimientosBancariosDetalleList = nuevo;

				this.addInfo(
						"Info:",
						this.getResourceMessage("movimientoBancario.detalle.deleted"));
			}
			log.debug(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #modifyMantenimientoPERMovimientosBancarios;
	 * 
	 * @param event
	 */
	public void modifyMantenimientoPERMovimientosBancarios(ActionEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'modify' method");
		}
		MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// String id = request.getParameter("idHijo");
		if (f.isEditable()) {
			f.setModified(true);
		}
		if (this.selectDetalle != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + this.selectDetalle);
			}
			List resultado = (List) this.movimientosBancariosDetalleList;
			Iterator iterator = resultado.iterator();
			while (iterator.hasNext()) {
				MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
				detalle = (MovimientosBancariosDetalle) iterator.next();
				if (this.selectDetalle.getConsecutivo() == detalle
						.getConsecutivo()) {
					try {
						BeanUtils.copyProperties(f, detalle);
						f.setFechaProceso(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, detalle.getFechaProceso()));

						if (f.getFechaPago() != null
								&& !f.getFechaPago().isEmpty()) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
							this.fechaPagoD = sdf.parse(f.getFechaPago());
							f.setFechaPago(DateUtil
									.convertDateToString(this.fechaPagoD));
						}

					} catch (Exception e) {
						this.addError("Error: ",
								this.obtieneMensajeErrorException(e));
					}

					f.setCodigoPais(pais.getCodigo());
					f.setConsecutivo("" + detalle.getConsecutivo());
				}
			}
			f.setSelectedItems(new String[] { String.valueOf(this.selectDetalle
					.getConsecutivo()) });
		} else {
			String fechaProceso = f.getFechaProceso();
			f.setFechaProceso(fechaProceso);
		}
		log.debug(f);
		this.btnEdit = true;
	}
	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #stockMantenimientoPERMovimientosBancarios;
	 * 
	 * @param event
	 */
	public void stockMantenimientoPERMovimientosBancarios(ActionEvent event) {

		if (!validarDetalleCabecera())
			return;

		if (log.isDebugEnabled()) {
			log.debug("Entering 'stock' method");
		}
		MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setModified(false);
		if (log.isDebugEnabled()) {
			log.debug("Consecutivo seleccionado de la lista: "
					+ f.getConsecutivo());
		}
		if (f.getConsecutivo() != null) {
			List resultado = this.movimientosBancariosDetalleList;
			Iterator iterator = resultado.iterator();
			List nuevo = new ArrayList();
			int i = 1;

			if (this.fechaPagoD != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				f.setFechaPago(sdf.format(this.fechaPagoD));
			}

			while (iterator.hasNext()) {
				MovimientosBancariosDetalle detalle = new MovimientosBancariosDetalle();
				detalle = (MovimientosBancariosDetalle) iterator.next();
				log.debug("Pintando el detalle a modificar "
						+ detalle.toString());
				if (Long.parseLong(f.getConsecutivo()) == detalle
						.getConsecutivo()) {
					try {
						// BeanUtils.copyProperties(detalle, f);

						detalle.setCodigoPais(f.getCodigoPais());
						detalle.setTipoTransaccion(f.getTipoTransaccion());
						detalle.setImportePago(f.getImportePago());
						detalle.setFechaPago(this.fechaPagoD);
						detalle.setCodigoConsultora(f.getCodigoConsultora());
						detalle.setCodigoTipoOrigenDatos(f
								.getCodigoTipoOrigenDatos());
						detalle.setDigitoChequeo(f.getDigitoChequeo());
						detalle.setFechaProceso(this.fechaProcesoD);
						detalle.setHorario(f.getHorario());
						detalle.setNombreOficina(f.getNombreOficina());
						detalle.setNumeroCupon(f.getNumeroCupon());
						detalle.setNumeroDocumento(f.getNumeroDocumento());
						if (f.getNumeroFacturaBoleta() != null
								&& !f.getNumeroFacturaBoleta().isEmpty()) {
							detalle.setNumeroFacturaBoleta(Long.parseLong(f
									.getNumeroFacturaBoleta()));
						}
						detalle.setNumeroLoteInterno(f.getNumeroLoteInterno());
						detalle.setObservacion(f.getObservacion());
						detalle.setOficinaRecaudadora(f.getOficinaRecaudadora());

					} catch (Exception e) {
						this.addError("Error: ",
								this.obtieneMensajeErrorException(e));
					}

					if (yaExisteDetalleEdicion(detalle, resultado)) {
						this.addInfo(
								"Info:",
								this.getResourceMessage("movimientoBancario.cabecera.error.duplicated"));
					} else {
						detalle.setCodigoPais(pais.getCodigo());
						log.debug("Modificando...");
						log.debug(detalle);
						this.addInfo(
								"Info:",
								this.getResourceMessage("movimientoBancario.detalle.updated"));
					}
				}
				detalle.setConsecutivo(i);
				nuevo.add(detalle);
				i++;
			}

			this.movimientosBancariosDetalleList = nuevo;
			f.setNewRecord(false);
			String fechaProceso = f.getFechaProceso();
			f.setFechaProceso(fechaProceso);
		}
		log.debug(f);
		this.btnEdit = false;
	}
	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #yaExisteDetalle;
	 * 
	 * @param event
	 */
	private boolean yaExisteDetalle(MovimientosBancariosDetalle detalle,
			List resultado) {
		Iterator iterator = resultado.iterator();
		while (iterator.hasNext()) {
			MovimientosBancariosDetalle comparador = new MovimientosBancariosDetalle();
			comparador = (MovimientosBancariosDetalle) iterator.next();
			String importePago = detalle.getImportePago() + "";
			String importeComparador = comparador.getImportePago() + "";
			if (detalle.getCodigoConsultora().equalsIgnoreCase(
					comparador.getCodigoConsultora())
					&& importePago.equals(importeComparador)
					&& detalle.getFechaPago().equals(comparador.getFechaPago()))
				return true;
		}
		return false;
	}
	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #yaExisteDetalleEdicion;
	 * 
	 * @param event
	 */
	private boolean yaExisteDetalleEdicion(MovimientosBancariosDetalle detalle,
			List resultado) {
		Iterator iterator = resultado.iterator();
		while (iterator.hasNext()) {
			MovimientosBancariosDetalle comparador = new MovimientosBancariosDetalle();
			comparador = (MovimientosBancariosDetalle) iterator.next();
			String importePago = detalle.getImportePago() + "";
			String importeComparador = comparador.getImportePago() + "";

			if (detalle.getCodigoConsultora().equalsIgnoreCase(
					comparador.getCodigoConsultora())
					&& importePago.equals(importeComparador)
					&& detalle.getFechaPago().equals(comparador.getFechaPago())
					&& detalle.getConsecutivo() != comparador.getConsecutivo())

				return true;
		}
		return false;
	}
	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #loadCuentasCorrientes;
	 * @param event
	 */
	public void loadCuentasCorrientes(ValueChangeEvent event) {
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
			String valor = event.getNewValue() != null ? event.getNewValue().toString() : "";
			
			List<LabelValue> lista = Arrays.asList(ajax.getCuentasCorrientesPorPaisSociedad(pais.getCodigo(), valor));
			this.siccCuentaCorrienteList = new ArrayList();
			for (LabelValue label : lista) {
				Base base = new Base();
				base.setCodigo(label.getValue());
				base.setDescripcion(label.getLabel());
				this.siccCuentaCorrienteList.add(base);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #executeInterfazProcesoPERProcesarMovimiento;
	 * @param event
	 */
	public void executeInterfazProcesoPERProcesarMovimiento(ActionEvent event) {
		try {
			Map mapa = (Map) this.beanRegistroSeleccionado;
			String numeroLoteInterno = (String) mapa.get("numeroLoteInterno");
			String tipoOrigenDescripcion = (String) mapa.get("tipoOrigenDatos");
			this.procesoPERProcesarMovimientoAction.setTipoOrigenDescripcion(tipoOrigenDescripcion);
			this.procesoPERProcesarMovimientoAction.setIdNumeroLote(numeroLoteInterno);
			this.procesoPERProcesarMovimientoAction.setActivarSalirPadre(true);
			this.procesoPERProcesarMovimientoAction.setPantallaPadre("mantenimientoPERMovimientosBancariosList.xhtml");
			this.procesoPERProcesarMovimientoAction.invocarInterfaz();
			this.redireccionarPagina("procesoPERProcesarMovimientoForm");

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction
						.getBeanRegistroSeleccionado();
				MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
				f.setCodigoConsultora(cliente.getCodigo());
				String apellido2 = cliente.getApellidoMaterno();
				String nombre1 = cliente.getNombre();
				f.setDescripcionConsultora(nombre1 + " " + apellido2);
				this.busquedaConsultoraPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.mostrarPopupConsultora = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		if (accion.equals("PROCESAR_MOVIMIENTO")) {
			if (this.beanRegistroSeleccionado == null) {
				return this.getResourceMessage("errors.select.item");
			}
		}
		return null;
	}

	/**
	 * biz.belcorp.ssicc.web.spusicc.action.
	 * MantenimientoPERMovimientosBancariosSearchAction
	 * #validarDetalleCabecera;
	 */
	private boolean validarDetalleCabecera() {
		MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
		StringBuilder msg = new StringBuilder();
		if (!StringUtils.isNotBlank(f.getTipoTransaccion())) {
			msg.append(this
					.getResourceMessage("mantenimientoPERMovimientosBancariosForm.tipoTransaccion"));
			msg.append(this.getResourceMessage("campo.requerido"));
			this.addWarn("Info:", msg.toString());
			return false;
		}
		if (!StringUtils.isNotBlank(f.getImportePago())) {
			msg.append(this
					.getResourceMessage("mantenimientoPERMovimientosBancariosForm.importePago"));
			msg.append(this.getResourceMessage("campo.requerido"));
			this.addWarn("Info:", msg.toString());
			return false;
		}
		if (this.fechaPagoD == null) {
			msg.append(this
					.getResourceMessage("mantenimientoPERMovimientosBancariosForm.fechaPago"));
			msg.append(this.getResourceMessage("campo.requerido"));
			this.addWarn("Info:", msg.toString());
			return false;
		}
		if (!StringUtils.isNotBlank(f.getCodigoConsultora())) {
			msg.append(this
					.getResourceMessage("mantenimientoPERMovimientosBancariosForm.codigoConsultora"));
			msg.append(this.getResourceMessage("campo.requerido"));
			this.addWarn("Info:", msg.toString());
			return false;
		}
		return true;
	}
	
	/**
	 * validaConsultoraOnEnter.
	 * 
	 * @param 
	 */
	public void validaConsultoraOnEnter() {
		log.info("validaConsultoraOnEnter()...");
		
		MantenimientoPERMovimientosBancariosForm f = (MantenimientoPERMovimientosBancariosForm) this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String mensaje = null;
		String codigoPais = pais.getCodigo();
		
		if(StringUtils.isNotBlank(f.getCodigoConsultora())){
			int tamanio = this.longitudCampoClientes;
			f.setCodigoConsultora(StringUtils.leftPad(f.getCodigoConsultora(), tamanio, "0"));
			
			String[] obtenerDatosConsultora = null;
			obtenerDatosConsultora = aSvc.getConsultoraByCriteria(codigoPais, f.getCodigoConsultora());
			
			if (obtenerDatosConsultora == null) {
				f.setDescripcionConsultora("");
				mensaje = this.getResourceMessage("mensaje.consultoraNoExiste");
				this.addError("Error : ", mensaje);
				
				return;
			}else{
				f.setDescripcionConsultora(obtenerDatosConsultora[1]);
			}
		}else{
			f.setDescripcionConsultora("");
			mensaje = this.getResourceMessage("mensaje.consultoraNoExiste");
			this.addError("Error : ", mensaje);
			
			return;
		}
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
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
	 * @return the siccTipoOrigenDatosList
	 */
	public List getSiccTipoOrigenDatosList() {
		return siccTipoOrigenDatosList;
	}

	/**
	 * @param siccTipoOrigenDatosList
	 *            the siccTipoOrigenDatosList to set
	 */
	public void setSiccTipoOrigenDatosList(List siccTipoOrigenDatosList) {
		this.siccTipoOrigenDatosList = siccTipoOrigenDatosList;
	}

	/**
	 * @return the siccStatusMovimientoBancarioList
	 */
	public List getSiccStatusMovimientoBancarioList() {
		return siccStatusMovimientoBancarioList;
	}

	/**
	 * @param siccStatusMovimientoBancarioList
	 *            the siccStatusMovimientoBancarioList to set
	 */
	public void setSiccStatusMovimientoBancarioList(
			List siccStatusMovimientoBancarioList) {
		this.siccStatusMovimientoBancarioList = siccStatusMovimientoBancarioList;
	}

	/**
	 * @return the movimientosBancariosCabeceraList
	 */
	public List getMovimientosBancariosCabeceraList() {
		return movimientosBancariosCabeceraList;
	}

	/**
	 * @param movimientosBancariosCabeceraList
	 *            the movimientosBancariosCabeceraList to set
	 */
	public void setMovimientosBancariosCabeceraList(
			List movimientosBancariosCabeceraList) {
		this.movimientosBancariosCabeceraList = movimientosBancariosCabeceraList;
	}

	/**
	 * @return the movimientosBancariosDetalleList
	 */
	public List getMovimientosBancariosDetalleList() {
		return movimientosBancariosDetalleList;
	}

	/**
	 * @param movimientosBancariosDetalleList
	 *            the movimientosBancariosDetalleList to set
	 */
	public void setMovimientosBancariosDetalleList(
			List movimientosBancariosDetalleList) {
		this.movimientosBancariosDetalleList = movimientosBancariosDetalleList;
	}

	/**
	 * @return the selectDetalle
	 */
	public MovimientosBancariosDetalle getSelectDetalle() {
		return selectDetalle;
	}

	/**
	 * @param selectDetalle
	 *            the selectDetalle to set
	 */
	public void setSelectDetalle(MovimientosBancariosDetalle selectDetalle) {
		this.selectDetalle = selectDetalle;
	}

	/**
	 * @return the siccHorarioList
	 */
	public List getSiccHorarioList() {
		return siccHorarioList;
	}

	/**
	 * @param siccHorarioList
	 *            the siccHorarioList to set
	 */
	public void setSiccHorarioList(List siccHorarioList) {
		this.siccHorarioList = siccHorarioList;
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
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction
	 *            the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return the siccTipoTransaccionList
	 */
	public List getSiccTipoTransaccionList() {
		return siccTipoTransaccionList;
	}

	/**
	 * @param siccTipoTransaccionList
	 *            the siccTipoTransaccionList to set
	 */
	public void setSiccTipoTransaccionList(List siccTipoTransaccionList) {
		this.siccTipoTransaccionList = siccTipoTransaccionList;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 *            the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the codigoCtaCteElegido
	 */
	public String getCodigoCtaCteElegido() {
		return codigoCtaCteElegido;
	}

	/**
	 * @param codigoCtaCteElegido
	 *            the codigoCtaCteElegido to set
	 */
	public void setCodigoCtaCteElegido(String codigoCtaCteElegido) {
		this.codigoCtaCteElegido = codigoCtaCteElegido;
	}

	/**
	 * @return the tipoOrigenDatos
	 */
	public String getTipoOrigenDatos() {
		return tipoOrigenDatos;
	}

	/**
	 * @param tipoOrigenDatos
	 *            the tipoOrigenDatos to set
	 */
	public void setTipoOrigenDatos(String tipoOrigenDatos) {
		this.tipoOrigenDatos = tipoOrigenDatos;
	}

	/**
	 * @return the sociedad
	 */
	public String getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad
	 *            the sociedad to set
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the cuentaCorriente
	 */
	public String getCuentaCorriente() {
		return cuentaCorriente;
	}

	/**
	 * @param cuentaCorriente
	 *            the cuentaCorriente to set
	 */
	public void setCuentaCorriente(String cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	/**
	 * @return the fechaPagoD
	 */
	public Date getFechaPagoD() {
		return fechaPagoD;
	}

	/**
	 * @param fechaPagoD
	 *            the fechaPagoD to set
	 */
	public void setFechaPagoD(Date fechaPagoD) {
		this.fechaPagoD = fechaPagoD;
	}

	/**
	 * @return the fechaProcesoD
	 */
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	/**
	 * @param fechaProcesoD
	 *            the fechaProcesoD to set
	 */
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}

	/**
	 * @return the procesoPERProcesarMovimientoAction
	 */
	public ProcesoPERProcesarMovimientoAction getProcesoPERProcesarMovimientoAction() {
		return procesoPERProcesarMovimientoAction;
	}

	/**
	 * @param procesoPERProcesarMovimientoAction
	 *            the procesoPERProcesarMovimientoAction to set
	 */
	public void setProcesoPERProcesarMovimientoAction(
			ProcesoPERProcesarMovimientoAction procesoPERProcesarMovimientoAction) {
		this.procesoPERProcesarMovimientoAction = procesoPERProcesarMovimientoAction;
	}

	/**
	 * @return the btnEdit
	 */
	public boolean isBtnEdit() {
		return btnEdit;
	}

	/**
	 * @param btnEdit
	 *            the btnEdit to set
	 */
	public void setBtnEdit(boolean btnEdit) {
		this.btnEdit = btnEdit;
	}

}
