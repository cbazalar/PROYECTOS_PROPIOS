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
import javax.faces.event.ActionEvent;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.runtime.directive.Parse;

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
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionCADService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionAbonosDirectosForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionCADForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoCCCDigitacionCADAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6808231353064444657L;

	private List cccTiposCargoAbonosDirectosList; 
	private DataTableModel listaDataModel;
	private List listaTabla;
	private List seleccionTabla;
	private boolean campoRequerido;
	
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
		MantenimientoCCCDigitacionCADForm searchForm = new MantenimientoCCCDigitacionCADForm();
		return searchForm;
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
		log.debug("JFA : Entering 'view' method");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm) this.formBusqueda;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		f.setCodigoPais(pais.getCodigo());		
		String fecha = sdf.format(new Date(System.currentTimeMillis()));		
		f.setFechaVencimiento(fecha);	
		f.setFechaVencimientoDate(new Date(System.currentTimeMillis()));
		f.setIndicadorHayRegistros("0");
		f.setSoloLectura(false);

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
			log.debug("------------------ Longitud Codigo Consultora  " + clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
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

		MantenimientoCCCDigitacionCADService service = (MantenimientoCCCDigitacionCADService) getBean("spusicc.mantenimientoCCCDigitacionCADService");
		listaTipoCAD = service.getTiposCargoAbonoDirectos();

		this.cccTiposCargoAbonosDirectosList = listaTipoCAD;
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonBuscar = false;
		this.mostrarListaBusqueda = false;
		
		List aux = new ArrayList();
		aux.add(f);
		this.listaTabla = aux;
		this.listaDataModel = new DataTableModel(this.listaTabla);	
	}
	
	public void validaConsultoraOnEnter(String indice) 
	{
		int i = new Integer(indice).intValue();
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm) this.listaTabla.get(i);
		LabelDatosConsultoraValue[] ex = {};
		
		if(f.getCodigoConsultora() != null)
		{
			int longitudCampo = Integer.parseInt(f.getLongitudCampoClientes());				
			f.setCodigoConsultora(StringUtils.leftPad(f.getCodigoConsultora(), longitudCampo, "0"));
			ex = ajax.getCabeceraConsultoraSimple(pais.getCodigo(), f.getCodigoConsultora());
		}
		if (ex != null) {
			f.setNombreConsultora(ex[0].getNombreConsultora());
			this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+indice+":observaciones')");
		}else
		{
			f.setNombreConsultora(null);
			f.setDocumentoIdentidadConsultora(null);	
			f.setCodigoConsultora(null);
			this.addError("Error: ", this.getResourceMessage("mensaje.consultoraNoExiste"));
		}
	}
	
	public void validaDocIdentidadOnEnter(String indice) 
	{
		try {
			int i = new Integer(indice).intValue();
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm) this.listaTabla.get(i);
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
					this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+indice+":observaciones')");
				}else
				{
					f.setNombreConsultora(null);
					f.setCodigoConsultora(null);
					this.addError("Error: ", this.getResourceMessage("mensaje.documentoIdentidadNoExiste"));
				}
			}			
		} catch (Exception e) 
		{
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void agregarFila(String indice) 
	{
		try {
			int i = new Integer(indice).intValue();
			MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm) this.listaTabla.get(i);
            int valor = i+1;
			if (StringUtils.isNotBlank(f.getCodigoConsultora()) && StringUtils.isNotBlank(f.getImporteCAD())) 
			{
				f.setSoloLectura(true);
				MantenimientoCCCDigitacionCADForm nuevo = new MantenimientoCCCDigitacionCADForm();				
				setearDatos(f, nuevo);
				this.listaTabla.add(nuevo);
				this.listaDataModel = new DataTableModel(this.listaTabla);
				this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+valor+":codigoConsultora')");
				campoRequerido=false;
			} else{
				if (StringUtils.isBlank(f.getImporteCAD()))
					campoRequerido=true;
				
				if (StringUtils.isBlank(f.getCodigoConsultora()))
					this.getRequestContext().execute("PrimeFaces.focus('listaTabla:"+indice+":codigoConsultora')");
				
				throw new Exception(this.getResourceMessage("ccc.mensaje.completarDatos"));
			}
		} catch (Exception e) {
			addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		if(accion.equals("GUARDAR"))
		{
			MantenimientoCCCDigitacionCADForm formSearch = (MantenimientoCCCDigitacionCADForm) this.formBusqueda;
			formSearch.setFechaVencimiento(DateUtil.convertDateToString(formSearch.getFechaVencimientoDate()));
			if(this.listaTabla.size() == 1)
			{
				MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm)this.listaTabla.get(0);
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
					MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm)this.listaTabla.get(i);
					if(StringUtils.isBlank(f.getCodigoConsultora()) || StringUtils.isBlank(f.getImporteCAD()))
					{
						mensaje = this.getResourceMessage("ccc.mensaje.completarDatos");
						break;
					}				
				}
			}
			
			if(StringUtils.isBlank(formSearch.getCodigoPeriodo())){
				mensaje = this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.codigoPeriodo.requerido");
				return mensaje;
			}
							
			if(StringUtils.isBlank(formSearch.getFechaVencimiento())){
				mensaje = "'Fec Venc. es un campo requerido.'";
				return mensaje;
				
			}
				
		}
		
		return mensaje;	
	}
	
	public void guardar(ActionEvent event) throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'save' method");
		}

		try {
			// Extraemos el usuario de la sesión
			String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();

			MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm) this.formBusqueda;
			f.setFechaVencimiento(DateUtil.convertDateToString(f.getFechaVencimientoDate()));

			CargoAbonoDirectoCabecera cccCargoAbonoDirecCabec = new CargoAbonoDirectoCabecera();
			List detallesCargoAbonoDirecList = new ArrayList();

			if (log.isDebugEnabled()) {
				log.debug(codigoUsuario);
				log.debug("JFA : cargando los datos de la cabecera");
			}
			// Carga todos los datos de la cabecera
			setCabecera(f, cccCargoAbonoDirecCabec);

			cccCargoAbonoDirecCabec.setCodigoUsuario(codigoUsuario);

			if (log.isDebugEnabled()) {
				log.debug("JFA : cargando los datos del detalle");
			}
			// Carga los detalles
			setDetalles(detallesCargoAbonoDirecList);
			
			if (log.isDebugEnabled()) {
				log.debug("JFA : seteando los datos del detalle");
			}

			if (log.isDebugEnabled()) {
				log.debug("JFA : Llamando al Service");
			}

			MantenimientoCCCDigitacionCADService service = (MantenimientoCCCDigitacionCADService) getBean("spusicc.mantenimientoCCCDigitacionCADService");
			service.insertCargoAbonoDirectoDigitado(cccCargoAbonoDirecCabec, detallesCargoAbonoDirecList);

			if (log.isDebugEnabled()) {
				log.debug("JFA : Generando Mensajes");
			}
			
			// setea los valores del form y reinicia la tabla a una fila.
			reiniciarDatatable(f);
			
			this.addInfo("", this.getResourceMessage("mantenimientoCCCDigitacionCADForm.msj.registrar"));
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			// setea los valores del form y reinicia la tabla a una fila.
			MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm) this.formBusqueda;
			reiniciarDatatable(f);
		}
	}
	
	/**
	 * @param f
	 * @param cccCargoAbonoDirecCabec
	 * Setea los datos de la cabecera del cargo y abono directo
	 */
	private void setCabecera(MantenimientoCCCDigitacionCADForm f, CargoAbonoDirectoCabecera cccCargoAbonoDirecCabec) 
	{
		cccCargoAbonoDirecCabec.setCodigoPais(f.getCodigoPais());				
		cccCargoAbonoDirecCabec.setFechaVencimiento(f.getFechaVencimiento());
		cccCargoAbonoDirecCabec.setCodigoPeriodo(f.getCodigoPeriodo());
		cccCargoAbonoDirecCabec.setTipoCAD(f.getTipoCAD());
			
		Date fecha = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");				
		cccCargoAbonoDirecCabec.setFechaDocumento(dateFormat.format(fecha));
	}
	
	/**
	 * @param detallesList
	 * Setea los detalles del cargo y abono directo
	 */
	private void setDetalles(List detallesList) 
	{		
		for (int i = 0; i < this.listaTabla.size(); i++) {
			log.debug("JFA : Recorriendo lista de codigos");
			MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm)this.listaTabla.get(i);
			CargoAbonoDirectoDetalle cadDigitDetal = new CargoAbonoDirectoDetalle();	
			
			cadDigitDetal.setCodigoConsultora(f.getCodigoConsultora());
			cadDigitDetal.setObservaciones(f.getObservaciones());
			cadDigitDetal.setImporteCAD(f.getImporteCAD());				
			detallesList.add(cadDigitDetal);
		}
	}
	
	public void borrar(ActionEvent event) 
	{
		try {
			if (this.listaTabla.size() > 1) 
			{
				if (this.listaTabla.size() == this.seleccionTabla.size()) {
					MantenimientoCCCDigitacionCADForm f = (MantenimientoCCCDigitacionCADForm) this.formBusqueda;
					reiniciarDatatable(f);
				} else 
				{					
					this.listaTabla.removeAll(this.seleccionTabla);					
					this.listaDataModel = new DataTableModel(this.listaTabla);
				}
			} else {
				this.addWarn("Advertencia: ", "No se puede eliminar cuando existe una sola fila.");
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	private void setearDatos(MantenimientoCCCDigitacionCADForm f, MantenimientoCCCDigitacionCADForm nuevo)
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
	
	private void reiniciarDatatable(MantenimientoCCCDigitacionCADForm f)
	{
		MantenimientoCCCDigitacionCADForm nuevo = new MantenimientoCCCDigitacionCADForm();
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
	
	public List getCccTiposCargoAbonosDirectosList() {
		return cccTiposCargoAbonosDirectosList;
	}

	public void setCccTiposCargoAbonosDirectosList(
			List cccTiposCargoAbonosDirectosList) {
		this.cccTiposCargoAbonosDirectosList = cccTiposCargoAbonosDirectosList;
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

	public boolean isCampoRequerido() {
		return campoRequerido;
	}

	public void setCampoRequerido(boolean campoRequerido) {
		this.campoRequerido = campoRequerido;
	}
	
	
}