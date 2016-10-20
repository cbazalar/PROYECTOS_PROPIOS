package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.text.DateFormat;
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
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargoAbonoDirectoCabecera;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.CargoAbonoDirectoDetalle;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.DatosConsultoraBasico;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionCargosDirectosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionCADForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionCargosDirectosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class MantenimientoCCCDigitacionCargosDirectosAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1319563927526326671L;
	
	private List cccTiposCargosDirectosDigitablesList;
	private List cccCargosDirectosDigitadosList; 
	private DataTableModel listaDataModel;
	private List listaTabla;
	private List seleccionTabla;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCDigitacionCargosDirectosForm f = new MantenimientoCCCDigitacionCargosDirectosForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		
		log.debug("JFA : Entering 'view' method");	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm) this.formBusqueda;

		f.setCodigoPais(pais.getCodigo());
		reset(f);

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Seteando el tamaño del Campo Consultora
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		f.setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(pais.getCodigo()).toString());
		if (log.isDebugEnabled()) {
			log.debug("------------------ Longitud Codigo Consultora  "
					+ clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
		}

		// Seteando el tamaño del Documento de Identidad
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		f.setLongitudDocumentoIdentidadConsultora(serviceCCC.getParametroPaisbyCodigo("LongitudDocumentoIdentidad"));
		if (log.isDebugEnabled()) {
			log.debug("------------------ Longitud Documento de Identidad Consultora " + f.getLongitudDocumentoIdentidadConsultora());
		}

		// Seteando el tipo del Documento de Identidad
		f.setTipoDocumentoIdentidadConsultora(serviceCCC.getParametroPaisbyCodigo("TipoDocumentoIdentidad"));
		if (log.isDebugEnabled()) {
			log.debug("------------------ Tipo Documento de Identidad Consultora " + f.getTipoDocumentoIdentidadConsultora());
		}

		List listaTipoCAD = new ArrayList();

		MantenimientoCCCDigitacionCargosDirectosService service = (MantenimientoCCCDigitacionCargosDirectosService) getBean("spusicc.mantenimientoCCCDigitacionCargosDirectosService");
		listaTipoCAD = service.getTiposCargosDirectosDigitables();

		this.cccTiposCargosDirectosDigitablesList = listaTipoCAD;
		
		List aux = new ArrayList();
		aux.add(f);
		this.listaTabla = aux;
		this.listaDataModel = new DataTableModel(this.listaTabla);	
	}
	
	public void guardar(ActionEvent event)
	{
		try {
			if (log.isDebugEnabled()) {
				log.debug("JFA : Entering 'save' method");
			}
						
			// Extraemos el usuario de la sesión
	        String codigoUsuario =this.mPantallaPrincipalBean.getCurrentUser().getLogin();
	        
			MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm)this.formBusqueda;
			f.setFechaVencimiento(DateUtil.convertDateToString(f.getFechaVencimientoDate()));
			
			CargoAbonoDirectoCabecera cccCargoAbonoDirecCabec = new CargoAbonoDirectoCabecera();
			List detallesCargoAbonoDirecList = new ArrayList();		
			
			if (log.isDebugEnabled()) {
				log.debug(codigoUsuario);
				log.debug("JFA : cargando los datos de la cabecera");
			}
			//Carga todos los datos de la cabecera
			setCabecera(f, cccCargoAbonoDirecCabec);
			
			cccCargoAbonoDirecCabec.setCodigoUsuario(codigoUsuario);
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : cargando los datos del detalle");
			}
			//Carga los detalles			
			setDetalles(detallesCargoAbonoDirecList);
			if (log.isDebugEnabled()) {
				log.debug("JFA : seteando los datos del detalle");
			}
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : Llamando al Service");
			}
			
			MantenimientoCCCDigitacionCargosDirectosService service = (MantenimientoCCCDigitacionCargosDirectosService)getBean("spusicc.mantenimientoCCCDigitacionCargosDirectosService");
			service.insertCargoAbonoDirectoDigitado(cccCargoAbonoDirecCabec, detallesCargoAbonoDirecList);
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : Generando Mensajes");
			}
			
			this.addInfo("", this.getResourceMessage("mantenimientoCCCDigitacionCargosDirectosForm.msj.registrar"));
				    	    
			reset(f);	
			reiniciarDatatable(f);
		    
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm)this.formBusqueda;
			reiniciarDatatable(f);
		}		
	}
	
	private void reset(MantenimientoCCCDigitacionCargosDirectosForm f) throws Exception
	{		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));		
		
		f.setFechaVencimiento(fecha);
		f.setFechaVencimientoDate(DateUtil.convertStringToDate(f.getFechaVencimiento()));
		f.setIndicadorHayRegistros("0");
		f.setSoloLectura(false);
	}
	
	/**
	 * Borra los items seleccionados
	 * @param event
	 */
	public void borrar(ActionEvent event)
	{
		try {
			if (this.listaTabla.size() > 1) 
			{
				if (this.listaTabla.size() == this.seleccionTabla.size()) {
					MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm) this.formBusqueda;
					reiniciarDatatable(f);
				} else 
				{					
					this.listaTabla.removeAll(this.seleccionTabla);					
					this.listaDataModel = new DataTableModel(this.listaTabla);
				}
				int foco = this.listaTabla.size() - this.seleccionTabla.size();
				this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco)+":codigoConsultora')");
			} else {
				this.addWarn("Advertencia: ", "No se puede eliminar cuando existe una sola fila.");
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private void reiniciarDatatable(MantenimientoCCCDigitacionCargosDirectosForm f)
	{
		MantenimientoCCCDigitacionCargosDirectosForm nuevo = new MantenimientoCCCDigitacionCargosDirectosForm();
		setearDatos(f, nuevo);
		nuevo.setCodigoConsultora("");
		nuevo.setObservaciones("");
		nuevo.setImporteCAD("");
		nuevo.setNombreConsultora("");	
		List aux = new ArrayList();
		aux.add(nuevo);
		this.listaTabla = aux;
		this.listaDataModel = new DataTableModel(this.listaTabla);		
	}
	
	/**
	 * @param detallesList
	 * Setea los detalles del cargo y abono directo
	 */
	private void setDetalles(List detallesList) 
	{		
		for (int i = 0; i < this.listaTabla.size(); i++) {
			log.debug("JFA : Recorriendo lista de codigos");
			MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm)this.listaTabla.get(i);
			CargoAbonoDirectoDetalle cadDigitDetal = new CargoAbonoDirectoDetalle();	
			
			cadDigitDetal.setCodigoConsultora(f.getCodigoConsultora());
			cadDigitDetal.setObservaciones(f.getObservaciones());
			cadDigitDetal.setImporteCAD(f.getImporteCAD());				
			detallesList.add(cadDigitDetal);
		}
	}

	/**
	 * @param f
	 * @param cccCargoAbonoDirecCabec
	 * Setea los datos de la cabecera del cargo y abono directo
	 */
	private void setCabecera(MantenimientoCCCDigitacionCargosDirectosForm f,CargoAbonoDirectoCabecera cccCargoAbonoDirecCabec) {
		cccCargoAbonoDirecCabec.setCodigoPais(f.getCodigoPais());				
		cccCargoAbonoDirecCabec.setFechaVencimiento(f.getFechaVencimiento());
		cccCargoAbonoDirecCabec.setCodigoPeriodo(f.getCodigoPeriodo());
		cccCargoAbonoDirecCabec.setTipoCAD(f.getTipoCargoDirecto());
			
		Date fecha = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");				
		cccCargoAbonoDirecCabec.setFechaDocumento(dateFormat.format(fecha));
	}

	/**
	 * Metodo ajax, valida documento de identidad
	 * en un campo de la fila al dar enter
	 * 
	 * @param indice
	 */
	public void validaDocIdentidadOnEnter(String indice) 
	{
		int i = new Integer(indice).intValue();

//		foco: indice de la fila, es para hacer el foco
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
		int foco = Integer.parseInt(value);

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm) this.listaTabla.get(i);
		if(f.getDocumentoIdentidadConsultora() != null)
		{
			int tamanio = Integer.parseInt(f.getLongitudDocumentoIdentidadConsultora());										
			f.setDocumentoIdentidadConsultora(StringUtils.leftPad(f.getDocumentoIdentidadConsultora(), tamanio, "0"));
			
			DatosConsultoraBasico[] aux = ajax.getCabeceraConsultoraByDocIdentidad(pais.getCodigo(), f.getDocumentoIdentidadConsultora(),
					f.getTipoDocumentoIdentidadConsultora(), f.getCodigoConsultora());
			if(aux != null)
			{
				f.setNombreConsultora(aux[0].getNombreConsultora());
				f.setCodigoConsultora(aux[0].getCodigoConsultora());
				this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco)+":observaciones')");
			}else
			{
				f.setNombreConsultora(null);
				f.setCodigoConsultora(null);
				f.setDocumentoIdentidadConsultora(null);
				this.addError("Error: ", this.getResourceMessage("mensaje.documentoIdentidadNoExiste"));
				this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco)+":documentoIdentidadConsultora')");
			}
		}		
	}
	
	/**
	 * Metodo Ajax, valida codigo de consultora
	 * en el campo de la fila al dar enter
	 * 
	 * @param indice
	 */
	public void validaConsultoraOnEnter(String indice)
	{
		int i = new Integer(indice).intValue();
		
//		foco: indice de la fila, es para hacer el foco
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
		int foco = Integer.parseInt(value);
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm) this.listaTabla.get(i);
		LabelDatosConsultoraValue[] ex = {};
		
		if(f.getCodigoConsultora() != null)
		{
			int longitudCampo = Integer.parseInt(f.getLongitudCampoClientes());				
			f.setCodigoConsultora(StringUtils.leftPad(f.getCodigoConsultora(), longitudCampo, "0"));
			ex = ajax.getCabeceraConsultoraSimple(pais.getCodigo(), f.getCodigoConsultora());
		}
		if (ex != null) {
			f.setNombreConsultora(ex[0].getNombreConsultora());
			this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco)+":observaciones')");
		}else
		{
			f.setNombreConsultora(null);
			f.setDocumentoIdentidadConsultora(null);
			f.setCodigoConsultora(null);
			this.addError("Error: ", this.getResourceMessage("mensaje.consultoraNoExiste"));
			this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco)+":codigoConsultora')");
		}
	}
	
	/**
	 * Anhade una fila a la grilla
	 * al dar enter en importe
	 * 
	 * @param indice
	 */
	public void agregarFila(String indice) 
	{
		try {
			int i = new Integer(indice).intValue();
			MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm) this.listaTabla.get(i);

//			foco: indice de la fila, es para hacer el foco
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
			int foco = Integer.parseInt(value);
			
			if (StringUtils.isNotBlank(f.getCodigoConsultora()) && StringUtils.isNotBlank(f.getImporteCAD())) 
			{
				f.setSoloLectura(true);
				MantenimientoCCCDigitacionCargosDirectosForm nuevo = new MantenimientoCCCDigitacionCargosDirectosForm();
				setearDatos(f, nuevo);						
				this.listaTabla.add(nuevo);
				this.listaDataModel = new DataTableModel(this.listaTabla);
				this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco+1)+":codigoConsultora')");
			} else{
				this.addError("Error: ", this.getResourceMessage("ccc.mensaje.completarDatos"));
				this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco)+":codigoConsultora')");
			}				
		} catch (Exception e) {
			addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	private void setearDatos(MantenimientoCCCDigitacionCargosDirectosForm f, MantenimientoCCCDigitacionCargosDirectosForm nuevo)
	{
		nuevo.setCodigoPais(f.getCodigoPais());
		nuevo.setFechaVencimiento(f.getFechaVencimiento());
		nuevo.setFechaVencimientoDate(f.getFechaVencimientoDate());
		nuevo.setIndicadorHayRegistros(f.getIndicadorHayRegistros());
		nuevo.setCodigoPeriodo(f.getCodigoPeriodo());
		nuevo.setLongitudCampoClientes(f.getLongitudCampoClientes());				
		nuevo.setLongitudDocumentoIdentidadConsultora(f.getLongitudDocumentoIdentidadConsultora());
		nuevo.setTipoDocumentoIdentidadConsultora(f.getTipoDocumentoIdentidadConsultora());	
		nuevo.setSoloLectura(false);
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		if(accion.equals("GUARDAR"))
		{
			MantenimientoCCCDigitacionCargosDirectosForm formSearch = (MantenimientoCCCDigitacionCargosDirectosForm) this.formBusqueda;
			formSearch.setFechaVencimiento(DateUtil.convertDateToString(formSearch.getFechaVencimientoDate()));
			
			if(this.listaTabla.size() == 1)
			{
				MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm)this.listaTabla.get(0);
				if(StringUtils.isBlank(f.getCodigoConsultora()) && StringUtils.isBlank(f.getImporteCAD()))
				{
					mensaje = this.getResourceMessage("mensaje.ingresarUnDetalle");
				}
				else{
					if(StringUtils.isBlank(f.getCodigoConsultora()) || StringUtils.isBlank(f.getImporteCAD()))
						mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
				}				
			}else
			{
				for (int i = 0; i < this.listaTabla.size(); i++) 
				{
					MantenimientoCCCDigitacionCargosDirectosForm f = (MantenimientoCCCDigitacionCargosDirectosForm)this.listaTabla.get(i);
					if(StringUtils.isBlank(f.getCodigoConsultora()) || StringUtils.isBlank(f.getImporteCAD()))
					{
						mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
						break;
					}				
				}
			}
			
			if(StringUtils.isBlank(formSearch.getCodigoPeriodo()))
				mensaje = this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.codigoPeriodo.requerido");
			
			if(StringUtils.isBlank(formSearch.getTipoCargoDirecto()))
				mensaje = "'Tipo Cargo Directo' es un campo requerido.";
			
			if(StringUtils.isBlank(formSearch.getFechaVencimiento()))
				mensaje = "'Fec Venc. es un campo requerido.'";
		}
		
		return mensaje;	
	}
	
	/**
	 * Setea el focus a Importe al dar enter
	 * 
	 * @param indice
	 */
	public void setearFocusImporte(String indice) 
	{
		try {
			int i = new Integer(indice).intValue();
			
//			foco: indice de la fila, es para hacer el foco
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
			int foco = Integer.parseInt(value);

			this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+(foco)+":importeCAD')");
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public List getCccTiposCargosDirectosDigitablesList() {
		return cccTiposCargosDirectosDigitablesList;
	}

	public void setCccTiposCargosDirectosDigitablesList(
			List cccTiposCargosDirectosDigitablesList) {
		this.cccTiposCargosDirectosDigitablesList = cccTiposCargosDirectosDigitablesList;
	}

	public List getCccCargosDirectosDigitadosList() {
		return cccCargosDirectosDigitadosList;
	}

	public void setCccCargosDirectosDigitadosList(
			List cccCargosDirectosDigitadosList) {
		this.cccCargosDirectosDigitadosList = cccCargosDirectosDigitadosList;
	}

	public DataTableModel getListaDataModel() {
		return listaDataModel;
	}

	public void setListaDataModel(DataTableModel listaDataModel) {
		this.listaDataModel = listaDataModel;
	}

	public List getListaTabla() {
		return listaTabla;
	}

	public void setListaTabla(List listaTabla) {
		this.listaTabla = listaTabla;
	}

	public List getSeleccionTabla() {
		return seleccionTabla;
	}

	public void setSeleccionTabla(List seleccionTabla) {
		this.seleccionTabla = seleccionTabla;
	}


}
