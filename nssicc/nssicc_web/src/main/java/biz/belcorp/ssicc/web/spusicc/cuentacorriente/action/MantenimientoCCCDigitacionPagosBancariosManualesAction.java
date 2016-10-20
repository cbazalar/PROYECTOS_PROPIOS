package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.DatosConsultoraBasico;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioCabecera;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.PagoBancarioDetalle;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionPagosBancariosManualesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionPagosBancariosManualesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoCCCDigitacionPagosBancariosManualesAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private String numeroLoteInterno;
	private List cccPagosBancariosManualesDigitadosList;
	private List detallesList;
	private List siccCuentaCorrienteList;
	private List columnasSeleccionadas;
	private List listaCampos1;
	private DataTableModel datatableCCCD;

	public void agregarUltimaFila(String indice) {

		int i = new Integer(indice).intValue();

		MantenimientoCCCDigitacionPagosBancariosManualesForm bean = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.listaCampos1
				.get(i);
		bean.setFechaPago(DateUtil.convertDateToString(bean.getFechaPagoD()));
		int tamanio = this.listaCampos1.size();

		if (tamanio > 0) {
			for (int j = 0; j < listaCampos1.size(); j++) {
				if (this.listaCampos1.size() == j) {
					MantenimientoCCCDigitacionPagosBancariosManualesForm base = new MantenimientoCCCDigitacionPagosBancariosManualesForm();
					base.setFechaPagoD(new Date());
					this.listaCampos1.add(base);
				}

			}
		} else {
			this.addWarn("Advertencia : ",
					this.getResourceMessage("ccc.mensaje.completarDatos"));
			return;
		}
	}

	public void agregarUnaFila(String indice) {
		try {

			int i = new Integer(indice).intValue();

			MantenimientoCCCDigitacionPagosBancariosManualesForm bean = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.listaCampos1.get(i);
			
//			foco: indice de la fila, es para hacer el foco
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
			int foco = Integer.parseInt(value);
			
			bean.setFechaPago(DateUtil.convertDateToString(bean.getFechaPagoD()));

			String fechaPago = bean.getFechaPago();
			String importePago = bean.getImportePago();

			String nombreConsultora = bean.getNombreConsultora();

			if (!nombreConsultora.isEmpty() && !fechaPago.isEmpty() && !importePago.isEmpty()) {
				MantenimientoCCCDigitacionPagosBancariosManualesForm nuevo = new MantenimientoCCCDigitacionPagosBancariosManualesForm();
				bean.setSoloLectura(true);
//				bean.setFechaPagoD(new Date());
				setearDatos(bean, nuevo);	
				this.listaCampos1.add(nuevo);
				this.datatableCCCD = new DataTableModel(this.listaCampos1);
				this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:"+(foco+1)+":idCodConsultora')");
			} else {
				this.addWarn("Advertencia : ",
						this.getResourceMessage("ccc.mensaje.completarDatos"));
				this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:"+(foco)+":idCodConsultora')");
				return;
			}
		} catch (Exception e) {
			this.addWarn("Advertencia : ",
					this.getResourceMessage("ccc.mensaje.completarDatos"));
			return;
		}

	}
	
	private void setearDatos(MantenimientoCCCDigitacionPagosBancariosManualesForm f, MantenimientoCCCDigitacionPagosBancariosManualesForm nuevo){
		Date fechaD = new Date(System.currentTimeMillis());
		
		nuevo.setCodigoPais(f.getCodigoPais());
		nuevo.setFechaPagoD(fechaD);
		nuevo.setFechaPago(DateUtil.convertDateToString(nuevo.getFechaPagoD()));		
		nuevo.setIndicadorHayRegistros(f.getIndicadorHayRegistros());
		nuevo.setCodigoPeriodo(f.getCodigoPeriodo());
		nuevo.setLongitudCampoClientes(f.getLongitudCampoClientes());				
		nuevo.setLongitudDocumentoIdentidadConsultora(f.getLongitudDocumentoIdentidadConsultora());
		nuevo.setTipoDocumentoIdentidadConsultora(f.getTipoDocumentoIdentidadConsultora());
		nuevo.setSoloLectura(false);
	}

	/**
	 * Ceros izquierda.
	 * 
	 * @param query
	 *            the query
	 * @return the list
	 */
	public void cerosIzquierda(String indice) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierda' method");
		}
		try {
			int i = new Integer(indice).intValue();
			MantenimientoCCCDigitacionPagosBancariosManualesForm f = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.listaCampos1
					.get(i);
			MantenimientoCCCDigitacionPagosBancariosManualesForm form = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.formBusqueda;

			if (f.getDocumentoIdentidadConsultora().length() <= Integer.parseInt(form
					.getLongitudDocumentoIdentidadConsultora()))
				f.setDocumentoIdentidadConsultora(StringUtils.leftPad(f
						.getDocumentoIdentidadConsultora(), Integer.parseInt(form
						.getLongitudDocumentoIdentidadConsultora()), "0"));
			else {
				int tamanio = f.getDocumentoIdentidadConsultora().length()
						- Integer.parseInt(form
								.getLongitudDocumentoIdentidadConsultora());
				String codFinal = f.getDocumentoIdentidadConsultora().substring(tamanio);
				f.setDocumentoIdentidadConsultora(codFinal);
			}

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param indice
	 */
	public void validaConsultoraOnEnter(String indice) {
		log.info(indice);
		int i = new Integer(indice).intValue();
		if (i < 0)
			return;
		MantenimientoCCCDigitacionPagosBancariosManualesForm bean = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.listaCampos1
				.get(i);
		
//		foco: indice de la fila, es para hacer el foco
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
		int foco = Integer.parseInt(value);

		String mensaje = null;
		if (bean == null) {
			return;
		}

		String codigoConsultora = bean.getCodigoConsultora();

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		LabelDatosConsultoraValue[] obtenerDatosConsultora = null;
		obtenerDatosConsultora = aSvc.getCabeceraConsultoraSimple(codigoPais, codigoConsultora);
		if (obtenerDatosConsultora == null) {
			// String nombreConsultora = "";
			// for (int j = 0; j < obtenerDatosConsultora.length; j++) {
			// LabelDatosConsultoraValue consultora =
			// obtenerDatosConsultora[i];
			// nombreConsultora = consultora.getNombreConsultora();
			// }
			// bean.setNombreConsultora(nombreConsultora);
			mensaje = this.getResourceMessage("mensaje.consultoraNoExiste");
			this.addError("Error : ", mensaje);
			return;
		}
		bean.setNombreConsultora(obtenerDatosConsultora[0].getNombreConsultora());
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaD = new Date(System.currentTimeMillis());
		bean.setFechaPagoD(fechaD);
		bean.setFechaPago(DateUtil.convertDateToString(bean.getFechaPagoD()));
		
		this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:" + (foco) + ":idImportePago')");

	}

	public void validaDniConsultoraOnEnter(String indice) {
		log.info(indice);
		int i = new Integer(indice).intValue();
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCCCDigitacionPagosBancariosManualesForm bean = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.listaCampos1.get(i);
		
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
		int foco = Integer.parseInt(value);
		
		if (i < 0)
			return;
		
		boolean sinCodigoConsultora = false;
		String mensaje = null;

		/*if (!StringUtils.isBlank(bean.getDocumentoIdentidadConsultora())) {
			sinCodigoConsultora = true;
		} else {
			if (!sinCodigoConsultora) {
				String codigoConsultora = bean.getCodigoConsultora();
				String dniConsultora = bean.getDocumentoIdentidadConsultora();
				String tipoDocumento = bean.getTipoDocumentoIdentidadConsultora();

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
				DatosConsultoraBasico[] obtenerDatosConsultora = null;
				obtenerDatosConsultora = aSvc.getCabeceraConsultoraByDocIdentidad(codigoPais, dniConsultora, tipoDocumento, codigoConsultora);
				if (obtenerDatosConsultora == null) {
					mensaje = this.getResourceMessage("mensaje.consultoraNoExiste");
					this.addError("Error : ", mensaje);
					return;
				}
				bean.setNombreConsultora(obtenerDatosConsultora[0].getNombreConsultora());
				bean.setCodigoConsultora(obtenerDatosConsultora[0].getCodigoConsultora());
				this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:" + (i) + ":idImportePago')");
			}
		}*/

		if(bean.getDocumentoIdentidadConsultora() != null){
			int tamanio = Integer.parseInt(bean.getLongitudDocumentoIdentidadConsultora());										
			bean.setDocumentoIdentidadConsultora(StringUtils.leftPad(bean.getDocumentoIdentidadConsultora(), tamanio, "0"));
			
			DatosConsultoraBasico[] aux = ajax.getCabeceraConsultoraByDocIdentidad(pais.getCodigo(), bean.getDocumentoIdentidadConsultora(), 
					bean.getTipoDocumentoIdentidadConsultora(), bean.getCodigoConsultora());
			if(aux != null){
				bean.setNombreConsultora(aux[0].getNombreConsultora());
				bean.setCodigoConsultora(aux[0].getCodigoConsultora());
				Date fechaD = new Date(System.currentTimeMillis());
				bean.setFechaPagoD(fechaD);
				bean.setFechaPago(DateUtil.convertDateToString(bean.getFechaPagoD()));
				this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:"+(foco)+":idImportePago')");
				
			}else{
				bean.setNombreConsultora(null);
				bean.setCodigoConsultora(null);
				bean.setDocumentoIdentidadConsultora(null);
				this.addError("Error: ", this.getResourceMessage("mensaje.documentoIdentidadNoExiste"));
				this.getRequestContext().execute("PrimeFaces.focus('dataTableTest:"+(foco)+":documentoIdentidadConsultora')");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		
		if(StringUtils.equals(accion, "GUARDAR")){
			if(this.listaCampos1.size() == 1){
				MantenimientoCCCDigitacionPagosBancariosManualesForm f = (MantenimientoCCCDigitacionPagosBancariosManualesForm)this.listaCampos1.get(0);
				if(StringUtils.isBlank(f.getCodigoConsultora()) && StringUtils.isBlank(f.getImportePago())){
					mensaje = this.getResourceMessage("mensaje.ingresarUnDetalle");
				}
				else{
					if(StringUtils.isBlank(f.getCodigoConsultora()) || StringUtils.isBlank(f.getImportePago()))
						mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
				}				
			}else{
				for (int i = 0; i < this.listaCampos1.size(); i++) {
					MantenimientoCCCDigitacionPagosBancariosManualesForm f = (MantenimientoCCCDigitacionPagosBancariosManualesForm)this.listaCampos1.get(i);
					if(StringUtils.isBlank(f.getCodigoConsultora()) || StringUtils.isBlank(f.getImportePago())){
						mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
						break;
					}				
				}
			}
		}
		
		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#save(javax.faces.event.ActionEvent)
	 */
	public void save(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'save' method");
		}
		String mensaje = "";
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			// Extraemos el usuario de la sesi�n
			String codigoUsuario = usuario.getLogin();

			ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");

			Map criteria = new HashMap();

			serviceCCC.getNumeroLote(criteria);

			numeroLoteInterno = criteria.get("numeroLote").toString();

			if (log.isDebugEnabled()) {
				log.debug("JFA Obteniendo Numero de Lote " + numeroLoteInterno);
			}

			PagoBancarioCabecera cccPagoBancarioCabec = new PagoBancarioCabecera();

			List detallesCargoAbonoDirecList = new ArrayList();

			if (log.isDebugEnabled()) {
				log.debug(codigoUsuario);
				log.debug("JFA : cargando los datos de la cabecera");
			}
			// Carga todos los datos de la cabecera
			setCabecera(cccPagoBancarioCabec);

			cccPagoBancarioCabec.setCodigoUsuario(codigoUsuario);
			cccPagoBancarioCabec.setCodigoTipoOrigen("DIG");
			cccPagoBancarioCabec.setNumeroLote(numeroLoteInterno);

			if (log.isDebugEnabled()) {
				log.debug("JFA : cargando los datos del detalle");
			}
			// int tamanio = this.cccPagosBancariosManualesDigitadosList.size();
			// // Carga los detalles
			// if (tamanio > 0)
			// detallesCargoAbonoDirecList =
			// this.cccPagosBancariosManualesDigitadosList;
			// if (log.isDebugEnabled()) {
			// log.debug("JFA : obteniendo los datos de la lista");
			// }

			setDetalles();
			if (log.isDebugEnabled()) {
				log.debug("JFA : seteando los datos del detalle");
			}

			if (log.isDebugEnabled()) {
				log.debug("JFA : Llamando al Service");
			}

			MantenimientoCCCDigitacionPagosBancariosManualesService service = (MantenimientoCCCDigitacionPagosBancariosManualesService) getBean("spusicc.mantenimientoCCCDigitacionPagosBancariosManualesService");
			service.generarPagoBancarioManual(cccPagoBancarioCabec,
					this.detallesList);

			if (log.isDebugEnabled()) {
				log.debug("JFA : Generando Mensajes");
			}

			mensaje = this
					.getResourceMessage(
							"mantenimientoCCCDigitacionPagosBancariosManualesForm.msj.registrar",
							new Object[] { numeroLoteInterno });
			this.addInfo("Info : ", mensaje);
			this.cccPagosBancariosManualesDigitadosList = new ArrayList();
			this.listaCampos1 = new ArrayList();
			MantenimientoCCCDigitacionPagosBancariosManualesForm base = new MantenimientoCCCDigitacionPagosBancariosManualesForm();
			base.setFechaPagoD(new Date());
			this.listaCampos1.add(base);
			this.datatableCCCD = new DataTableModel(listaCampos1);
			this.detallesList = new ArrayList();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
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
	public void delete(ActionEvent ect) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'delete' method");
		}

		try {
			MantenimientoCCCDigitacionPagosBancariosManualesForm f = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.formBusqueda;
			int contador = 0;
			// Lista que esta en memoria
			List originalList = this.cccPagosBancariosManualesDigitadosList;
			List listaAuxiliar = this.listaCampos1;
			int tamanio = listaCampos1.size();
			
			if(tamanio > 1){
				if (this.listaCampos1.size() == this.columnasSeleccionadas.size()) {
					reiniciarDatatable(f);
				}else{
					this.listaCampos1.removeAll(this.columnasSeleccionadas);
					this.datatableCCCD = new DataTableModel(this.listaCampos1);
				}
				
				/*for (int i = 0; i < tamanio; i++) {
					MantenimientoCCCDigitacionPagosBancariosManualesForm bean = (MantenimientoCCCDigitacionPagosBancariosManualesForm) listaCampos1.get(i);
					if (StringUtils.isBlank(bean.getNombreConsultora()) || StringUtils.isBlank(bean.getImportePago())) {
						return;
					}
					
					for (int j = 0; j < columnasSeleccionadas.size(); j++) {
						MantenimientoCCCDigitacionPagosBancariosManualesForm seleccionado = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.columnasSeleccionadas.get(j);
						if (bean.getCodigoConsultora().equals(seleccionado.getCodigoConsultora())) {
							listaAuxiliar.remove(i);
							contador++;
							i--;
							if (contador == columnasSeleccionadas.size()) {
								return;
							}
						}
					}
				}*/
			}else{
				this.addWarn("Advertencia: ", "No se puede eliminar cuando existe una sola fila.");
			}
				
			/*this.listaCampos1 = listaAuxiliar;
			this.cccPagosBancariosManualesDigitadosList = originalList;
			this.datatableCCCD = new DataTableModel(this.listaCampos1);*/

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param f
	 * @param detallesList
	 *            Setea los datos del Detalle de Pagos Bancarios
	 */
	private void setDetalles() {
		MantenimientoCCCDigitacionPagosBancariosManualesForm f = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.formBusqueda;
		int tamanio = this.listaCampos1.size();
		for (int i = 0; i < tamanio; i++) {
			log.debug("JFA : Recorriendo lista de codigos");
			MantenimientoCCCDigitacionPagosBancariosManualesForm form = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.listaCampos1
					.get(i);
			if (StringUtils.isBlank(form.getNombreConsultora())) {
				break;
			}
			PagoBancarioDetalle cccPagoBancarioDetal = new PagoBancarioDetalle();
			cccPagoBancarioDetal
					.setCodigoConsultora(form.getCodigoConsultora());
			cccPagoBancarioDetal.setConsecutivoTransaccion(Integer.toString(i));
			cccPagoBancarioDetal.setImportePago(form.getImportePago());
			cccPagoBancarioDetal.setFechaPago(DateUtil.convertDateToString(
					Constants.DEFAULT_DATE_FORMAT, form.getFechaPagoD()));
			cccPagoBancarioDetal
					.setNombreConsultora(form.getNombreConsultora());

			this.detallesList.add(cccPagoBancarioDetal);
		}
	}

	/**
	 * @param f
	 * @param cccPagoBancarioCabec
	 *            Setea los datos de la Cabecera de Pagos Bancarios
	 */
	private void setCabecera(PagoBancarioCabecera cccPagoBancarioCabec) {

		MantenimientoCCCDigitacionPagosBancariosManualesForm f = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.formBusqueda;
		cccPagoBancarioCabec.setCodigoPais(f.getCodigoPais());
		cccPagoBancarioCabec.setCodigoCuentaCorrienteBancaria(f
				.getCodigoCuentaCorrienteBancaria());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
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
		MantenimientoCCCDigitacionPagosBancariosManualesForm f = new MantenimientoCCCDigitacionPagosBancariosManualesForm();
		return f;
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
	 * 
	 */
	public void inicializando() {
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
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
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		this.inicializando();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.detallesList = new ArrayList();

		// Map para almacenar los parametros
		Map criteria = new HashMap();
		
		// Seteando el tama�o del Campo Consultora
		MantenimientoCCCDigitacionPagosBancariosManualesForm f = (MantenimientoCCCDigitacionPagosBancariosManualesForm) this.formBusqueda;
//		f.setFechaPagoD(new Date());
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		f.setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(
				pais.getCodigo()).toString());
		if (log.isDebugEnabled()) {
			log.debug("------------------ Longitud Codigo Consultora  "
					+ clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
		}
		f.setCodigoPais(pais.getCodigo());
		// Seteando el tama�o del Documento de Identidad
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		f.setLongitudDocumentoIdentidadConsultora(serviceCCC
				.getParametroPaisbyCodigo("LongitudDocumentoIdentidad"));
		if (log.isDebugEnabled()) {
			log.debug("------------------ Longitud Documento de Identidad Consultora "
					+ f.getLongitudDocumentoIdentidadConsultora());
		}

		// Seteando el tipo del Documento de Identidad
		f.setTipoDocumentoIdentidadConsultora(serviceCCC
				.getParametroPaisbyCodigo("TipoDocumentoIdentidad"));
		if (log.isDebugEnabled()) {
			log.debug("------------------ Tipo Documento de Identidad Consultora "
					+ f.getTipoDocumentoIdentidadConsultora());
		}

		// Obteniedo el listado de las Cuentas Corrientes Bancarias
		criteria.put("codigoPais", pais.getCodigo());
		this.siccCuentaCorrienteList = serviceCCC
				.getBancosDigitablesList(criteria);
		
		f.setSoloLectura(false);
		this.listaCampos1 = new ArrayList();
		this.listaCampos1.add(f);
		this.datatableCCCD = new DataTableModel(this.listaCampos1);
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Todo Ok: Redireccionando");
		}
	}
	
	private void reiniciarDatatable(MantenimientoCCCDigitacionPagosBancariosManualesForm f){
		MantenimientoCCCDigitacionPagosBancariosManualesForm nuevo = new MantenimientoCCCDigitacionPagosBancariosManualesForm();
		setearDatos(f, nuevo);
		nuevo.setDocumentoIdentidadConsultora("");
		nuevo.setCodigoConsultora("");
		nuevo.setImportePago("");
		nuevo.setNombreConsultora("");	
		List aux = new ArrayList();
		aux.add(nuevo);
		this.listaCampos1 = aux;
		this.datatableCCCD = new DataTableModel(this.listaCampos1);		
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
	 * @return the cccPagosBancariosManualesDigitadosList
	 */
	public List getCccPagosBancariosManualesDigitadosList() {
		return cccPagosBancariosManualesDigitadosList;
	}

	/**
	 * @param cccPagosBancariosManualesDigitadosList
	 *            the cccPagosBancariosManualesDigitadosList to set
	 */
	public void setCccPagosBancariosManualesDigitadosList(
			List cccPagosBancariosManualesDigitadosList) {
		this.cccPagosBancariosManualesDigitadosList = cccPagosBancariosManualesDigitadosList;
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
	 * @return the listaCampos1
	 */
	public List getListaCampos1() {
		return listaCampos1;
	}

	/**
	 * @param listaCampos1
	 *            the listaCampos1 to set
	 */
	public void setListaCampos1(List listaCampos1) {
		this.listaCampos1 = listaCampos1;
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
	 * @return the columnasSeleccionadas
	 */
	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}
}