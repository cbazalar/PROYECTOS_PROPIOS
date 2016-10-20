package biz.belcorp.ssicc.web.spusicc.ruv.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.ruv.form.MantenimientoRUVRegistroVentasForm;
import biz.belcorp.ssicc.web.spusicc.ruv.form.MantenimientoRUVRegistroVentasSearchForm;


@SessionScoped
@ManagedBean
public class MantenimientoRUVRegistroVentasSearchAction extends BaseMantenimientoSearchAbstractAction {

	private List ruvCanalList;
	private List ruvAccesoList;
	private List ruvSubAccesoList;
	private List ruvImpuestoList;
	private List ruvDocLegalList;
	private List ruvSociedadList;
	private List ruvDocList;
	
	private List ruvCanalMantList;
	private List ruvAccesoMantList;
	private List ruvSubAccesoMantList;
	
	private List ruvRegistroVentasList;	
	
	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;
	
	private boolean mostrarPopupClienteDesde;
	private boolean mostrarPopupClienteHasta;
	private boolean mostrarPopupCliente;
	
	private String separadorMiles;
	private String separadorDecimal;
	private Integer numeroDecimales;
	
	
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}

		this.mostrarProcesoBatch = true;
		this.mostrarPopupClienteDesde = false;
		this.mostrarPopupClienteHasta = false;
		this.mostrarPopupCliente = false;
		
		if (accion.equals("POPUP_CLIENTE_DESDE")) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaConsultorasAction.getBeanRegistroSeleccionado();
				MantenimientoRUVRegistroVentasSearchForm  f = (MantenimientoRUVRegistroVentasSearchForm) this.formBusqueda;
				f.setCodigoClienteDesde(cliente.get("codigoCliente").toString());

				this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
			}
		}
		if (accion.equals("POPUP_CLIENTE_HASTA")) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaConsultorasAction.getBeanRegistroSeleccionado();
				MantenimientoRUVRegistroVentasSearchForm  f = (MantenimientoRUVRegistroVentasSearchForm) this.formBusqueda;
				f.setCodigoClienteHasta(cliente.get("codigoCliente").toString());

				this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
			}
		}
    	
		if (accion.equals("POPUP_CLIENTE")) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaConsultorasAction.getBeanRegistroSeleccionado();
				MantenimientoRUVRegistroVentasForm f = (MantenimientoRUVRegistroVentasForm) this.formMantenimiento;
				f.setCodigoCliente(cliente.get("codigoCliente").toString());
				f.setNombre1((String)cliente.get("nombre1"));
				f.setNombre2((String)cliente.get("nombre2"));
				f.setApellido1((String)cliente.get("apellido1"));
				f.setApellido2((String)cliente.get("apellido2"));

				this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
			}
		}

	}

	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupClienteDesde = false;
		this.mostrarPopupClienteHasta = false;
		this.mostrarPopupCliente = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				this.getBean("spusicc.mantenimientoRUVRegistroVentasService");
		
		MantenimientoRUVRegistroVentasSearchForm f = (MantenimientoRUVRegistroVentasSearchForm) this.formBusqueda;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());	
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoISOIdioma", usuario.getIdioma().getCodigoISO());
		this.ruvRegistroVentasList = new ArrayList();
		this.ruvCanalList = service.getCanales(criteria);
		this.ruvCanalMantList = service.getCanales(criteria);
		this.ruvImpuestoList = service.getTasasImpuesto(criteria);
		this.ruvDocLegalList = service.getTiposDocumentoLegal();
		this.ruvSociedadList = service.getSociedades(criteria);
		this.ruvDocList = service.getTiposDocumento();
		this.ruvAccesoList = null;
		this.ruvSubAccesoList = null;
		
		Map mapFormato = service.getFormatoFechaNumerico(pais.getCodigo());
		this.separadorMiles = MapUtils.getString(mapFormato, "separadorMiles");
		this.separadorDecimal = MapUtils.getString(mapFormato, "separadorDecimal");
		this.numeroDecimales = MapUtils.getInteger(mapFormato, "numeroDecimales");
		
		this.mostrarBotonConsultar = false;
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals("POPUP_CLIENTE_DESDE")) {
			this.mostrarPopupClienteDesde = true;
		}
		if (accion.equals("POPUP_CLIENTE_HASTA")) {
			this.mostrarPopupClienteHasta = true;
		}
		if (accion.equals("POPUP_CLIENTE")) {
			this.mostrarPopupCliente = true;
		}
	}
	
	public void cargarAccesos(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				this.getBean("spusicc.mantenimientoRUVRegistroVentasService");
		
		if (StringUtils.isNotBlank(valor)) {
			Map criteria = new HashMap();
			criteria.put("oidCanal", valor);
			this.ruvAccesoList = service.getAccesos(criteria);		
		} else
			this.ruvAccesoList = null;
			
		this.ruvSubAccesoList = null;
	}
	
	public void cargarAccesosMant(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				this.getBean("spusicc.mantenimientoRUVRegistroVentasService");
		
		if (StringUtils.isNotBlank(valor)) {
			Map criteria = new HashMap();
			criteria.put("oidCanal", valor);
			this.ruvAccesoMantList = service.getAccesos(criteria);		
		} else
			this.ruvAccesoMantList = null;
			
		this.ruvSubAccesoMantList = null;
	}

	public void cargarSubaccesos(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				this.getBean("spusicc.mantenimientoRUVRegistroVentasService");

		if (StringUtils.isNotBlank(valor)) {
			Map criteria = new HashMap();
			criteria.put("oidAcceso", valor);
			this.ruvSubAccesoList = service.getSubAccesos(criteria);		
		} else
			this.ruvSubAccesoList = null;
	}
	
	public void cargarSubaccesosMant(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				this.getBean("spusicc.mantenimientoRUVRegistroVentasService");

		if (StringUtils.isNotBlank(valor)) {
			Map criteria = new HashMap();
			criteria.put("oidAcceso", valor);
			this.ruvSubAccesoMantList = service.getSubAccesos(criteria);		
		} else
			this.ruvSubAccesoMantList = null;
	}
	
	public String setValidarFind(){
		MantenimientoRUVRegistroVentasSearchForm  f = (MantenimientoRUVRegistroVentasSearchForm) this.formBusqueda;
		
		int filtros = 0;
		
		if(f.getFechaEmisionDesde()!=null)
			filtros++;
		
		if(f.getFechaEmisionHasta()!=null)
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getCodigoClienteDesde()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getCodigoClienteHasta()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getOidImpuestos()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getOidCanal()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getOidAcceso()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getOidSubacceso()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getOidTipoDocLegal()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getSerieDocuLegal()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getNumeroDocLegalDesde()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getNumeroDocLegalHasta()))
			filtros++;
		
		if(StringUtils.isNotEmpty(f.getOid()))
			filtros = filtros + 2;
		
		if(filtros < 2)
			return this.getResourceMessage("mantenimientoRUVRegistroVentasSearchForm.msg.filtrosRequeridos");
		
		if(f.getFechaEmisionDesde()!=null && f.getFechaEmisionHasta()!=null){
			if(f.getFechaEmisionDesde().compareTo(f.getFechaEmisionHasta())>0){
				return this.getResourceMessage("mantenimientoRUVRegistroVentasSearchForm.msg.rangoFechas");
			}
		}
		
		if(StringUtils.isNotEmpty(f.getCodigoClienteDesde()) && StringUtils.isNotEmpty(f.getCodigoClienteHasta())) {
			if(f.getCodigoClienteDesde().compareTo(f.getCodigoClienteHasta())>0)
				return this.getResourceMessage("mantenimientoRUVRegistroVentasSearchForm.msg.rangoClientes");
		}
		
		return "";
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub

		MantenimientoRUVRegistroVentasSearchForm  f = (MantenimientoRUVRegistroVentasSearchForm) this.formBusqueda;
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
					this.getBean("spusicc.mantenimientoRUVRegistroVentasService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		criteria.put("fechaEmisionDesde", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaEmisionDesde()));
		criteria.put("fechaEmisionHasta", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaEmisionHasta()));
		
		/* Obteniendo Lista */
		List resultado = service.getListRegistroVentas(criteria);
		this.ruvRegistroVentasList = resultado;
		return resultado;
		
	}
	
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoRUVRegistroVentasForm";
	}
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoRUVRegistroVentasSearchForm form = new MantenimientoRUVRegistroVentasSearchForm();
		return form;
	}
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoRUVRegistroVentasList";
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map obj = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				getBean("spusicc.mantenimientoRUVRegistroVentasService");
		
		try {							
			
			Map params = new HashMap();
			params.put("oidReg", obj.get("oid").toString());
			
			service.deleteRegistroVenta(params);
			
		}catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail",new Object[]{error}));
					
		}

		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoRUVRegistroVentasForm f = new MantenimientoRUVRegistroVentasForm();
		
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				getBean("spusicc.mantenimientoRUVRegistroVentasService");
		
		//this.incPagoConcursoClasificacionesList = service.getListClasificacionesPagoConcurso(null);
		//this.incConcursoList = service.getListConcursosPago(null);
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setAddAttributes ' method");
			}
			
			f.setOidEmpresa("");
			f.setOidIndImpuestos("");
			f.setFechaEmision(null);
			//f.setOidCliente(MapUtils.getString(map, "oidCliente", ""));
			f.setCodigoCliente("");
			f.setNombre1("");
			f.setNombre2("");
			f.setApellido1("");
			f.setApellido2("");
			
			f.setBaseImponible("");
			f.setImporteImpuesto("");
			f.setImporteTotal("");
			f.setCoeficienteImpuesto("");
			f.setOidCanal("");
			f.setOidAcceso("");
			f.setOidSubAcceso("");
			f.setPuntoEmision("");
			
			f.setOidTipoDocumentoLegal("");
			f.setSerieDocumentoLegal("");
			f.setNumeroDocumentoLegal("");
			f.setNumeroIdentificacionFiscal("");
			f.setNumeroIdentificacionNacional("");
			//f.setOidPais(MapUtils.getString(map, "oidPais	", ""));
			f.setOidTipoDocumentoRef("");
			f.setSerieDocumentoRef("");
			
			f.setNumeroDocumentoRef("");
			f.setEstadoAnulado("");
			f.setIndTransfGratuita("");
			f.setEstadoRUV("A");
			f.setDescuento("");
			f.setBaseImponibleNeto("");
			f.setOidTipoDocumento("");
			f.setFechaEmisionReferencia(null);
			
		} else {
			Map mapRegistro = (Map) this.beanRegistroSeleccionado;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			String oid = MapUtils.getString(mapRegistro, "oid");
			Map map = service.getRegistroVenta(oid);
			
			f.setOidReg(oid);
			f.setOidEmpresa(MapUtils.getString(map, "oidEmpresa", ""));
			f.setOidIndImpuestos(MapUtils.getString(map, "oidIndImpuestos", ""));
			f.setFechaEmision(sdf.parse(MapUtils.getString(map, "fechaEmision")));
			//f.setOidCliente(MapUtils.getString(map, "oidCliente", ""));
			f.setCodigoCliente(MapUtils.getString(map, "codigoCliente", ""));
			f.setNombre1(MapUtils.getString(map, "nombre1", ""));
			f.setNombre2(MapUtils.getString(map, "nombre2", ""));
			f.setApellido1(MapUtils.getString(map, "apellido1", ""));
			f.setApellido2(MapUtils.getString(map, "apellido2", ""));
			
			f.setBaseImponible(MapUtils.getString(map, "baseImponible", ""));
			f.setImporteImpuesto(MapUtils.getString(map, "importeImpuesto", ""));
			f.setImporteTotal(MapUtils.getString(map, "importeTotal", ""));
			f.setCoeficienteImpuesto(MapUtils.getString(map, "coeficienteImpuesto", ""));
			f.setOidCanal(MapUtils.getString(map, "oidCanal", ""));
			
			if(StringUtils.isNotEmpty(f.getOidCanal())) {
				Map criteria = new HashMap();
				criteria.put("oidCanal", f.getOidCanal());
				this.ruvAccesoMantList = service.getAccesos(criteria);		
			}
				
			f.setOidAcceso(MapUtils.getString(map, "oidAcceso", ""));
			
			if(StringUtils.isNotEmpty(f.getOidAcceso())) {
				Map criteria = new HashMap();
				criteria.put("oidAcceso", f.getOidAcceso());
				this.ruvSubAccesoMantList = service.getSubAccesos(criteria);	
			}
			
			f.setOidSubAcceso(MapUtils.getString(map, "oidSubAcceso", ""));
			f.setPuntoEmision(MapUtils.getString(map, "puntoEmision", ""));
			
			f.setOidTipoDocumentoLegal(MapUtils.getString(map, "oidTipoDocumentoLegal", ""));
			f.setSerieDocumentoLegal(MapUtils.getString(map, "serieDocumentoLegal", ""));
			f.setNumeroDocumentoLegal(MapUtils.getString(map, "numeroDocumentoLegal", ""));
			f.setNumeroIdentificacionFiscal(MapUtils.getString(map, "numeroIdentificacionFiscal", ""));
			f.setNumeroIdentificacionNacional(MapUtils.getString(map, "numeroIdentificacionNacional", ""));
			//f.setOidPais(MapUtils.getString(map, "oidPais	", ""));
			f.setOidTipoDocumentoRef(MapUtils.getString(map, "oidTipoDocumentoRef", ""));
			f.setSerieDocumentoRef(MapUtils.getString(map, "serieDocumentoRef", ""));
			
			f.setNumeroDocumentoRef(MapUtils.getString(map, "numeroDocumentoRef", ""));
			f.setEstadoAnulado(MapUtils.getString(map, "estadoAnulado", ""));
			f.setIndTransfGratuita(MapUtils.getString(map, "indTransfGratuita", ""));
			f.setEstadoRUV(MapUtils.getString(map, "estadoRUV", ""));
			f.setDescuento(MapUtils.getString(map, "descuento", ""));
			f.setBaseImponibleNeto(MapUtils.getString(map, "baseImponibleNeto", ""));
			f.setOidTipoDocumento(MapUtils.getString(map, "oidTipoDocumento", ""));
			
			if("1".equals(f.getEstadoAnulado()))
				f.setEstadoAnulado("true");
			else {
				f.setEstadoAnulado("false");
			}
			
			if("1".equals(f.getIndTransfGratuita()))
				f.setIndTransfGratuita("true");
			else {
				f.setIndTransfGratuita("false");
			}
			
			if(map.get("fechaEmisionReferencia")!=null)
				f.setFechaEmisionReferencia(sdf.parse(MapUtils.getString(map, "fechaEmisionReferencia")));
			else
				f.setFechaEmisionReferencia(null);
		}
		
		return f;

	}
	
	@Override
	public String setValidarMantenimiento() {
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
				getBean("spusicc.mantenimientoRUVRegistroVentasService");
		MantenimientoRUVRegistroVentasForm f = (MantenimientoRUVRegistroVentasForm) this.formMantenimiento;

		if("A".equals(f.getEstadoRUV())) {
			if(StringUtils.isNotEmpty(f.getNumeroDocumentoLegalFinal())) {
				if(f.getNumeroDocumentoLegalFinal().compareTo(f.getNumeroDocumentoLegal())>=0) {
					if("false".equals(f.getEstadoAnulado())) {
						return this.getResourceMessage("mantenimientoRUVRegistroVentasForm.msg.flagAnulado");
					}
				} else
					return this.getResourceMessage("mantenimientoRUVRegistroVentasForm.msg.rangoNumeroDocumentoLegal");
			}

		} else {
			return this.getResourceMessage("mantenimientoRUVRegistroVentasForm.msg.registroCerrado");
		}
				
		return "";

	}
	
	public void actualizarImporteTotal() {
		MantenimientoRUVRegistroVentasForm f = (MantenimientoRUVRegistroVentasForm) this.formMantenimiento;
		
		if(StringUtils.isNotEmpty(f.getBaseImponibleNeto()) && StringUtils.isNotEmpty(f.getImporteImpuesto())) {
			f.setImporteTotal(String.valueOf(Double.parseDouble(f.getBaseImponibleNeto()) + Double.parseDouble(f.getImporteImpuesto())));
		} else 
			f.setImporteTotal("");
		
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoRUVRegistroVentasService service = (MantenimientoRUVRegistroVentasService) 
												getBean("spusicc.mantenimientoRUVRegistroVentasService");
		MantenimientoRUVRegistroVentasForm f = (MantenimientoRUVRegistroVentasForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		
		if("true".equals(f.getEstadoAnulado()))
			params.put("estadoAnulado", "1");
		else 
			params.put("estadoAnulado", "0");
		
		if("true".equals(f.getIndTransfGratuita()))
			params.put("indTransfGratuita", "1");
		else 
			params.put("indTransfGratuita", "0");
		
		
		String fechaEmision=DateUtil.convertDateToString(f.getFechaEmision());
		params.put("fechaEmision", fechaEmision);
		
		String fechaEmisionReferencia=DateUtil.convertDateToString(f.getFechaEmisionReferencia());
		params.put("fechaEmisionReferencia", fechaEmisionReferencia);
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			Map criteria = new HashMap();
			criteria.put("oidSubAcceso", f.getOidSubAcceso());
			criteria.put("oidTipoDocumentoLegal", f.getOidTipoDocumentoLegal());
			criteria.put("serieDocumentoLegal", f.getSerieDocumentoLegal());
			criteria.put("numeroDocumentoLegal", f.getNumeroDocumentoLegal());
			
			String totales = service.getTotalRegistroVentas(criteria);
			if(Integer.parseInt(totales)>0) {
				throw new Exception(this.getResourceMessage("mantenimientoRUVRegistroVentasForm.msg.serieNumeroDocumentoExiste"));
			}
			
			if(StringUtils.isNotEmpty(f.getNumeroDocumentoLegalFinal())) {
				criteria.put("numeroDocumentoLegalFinal", f.getNumeroDocumentoLegalFinal());
				
				String rangoTotales = service.getTotalRegistroRangoVentas(criteria);
				if(Integer.parseInt(rangoTotales)>0) {
					throw new Exception(this.getResourceMessage("mantenimientoRUVRegistroVentasForm.msg.rangoSerieExiste"));
				}
			}
            
		    service.insertRegistroVenta(params);//inserta
		    
		    addInfo("",	getResourceMessage("mantenimientoRUVRegistroVentasForm.insert"));

		}
		else{
			service.updateRegistroVenta(params);//update
			
			//enviamos el mensaje de satisfaccion
			addInfo("",	getResourceMessage("mantenimientoRUVRegistroVentasForm.update"));
		}	

		return true;
	}

	/**
	 * @return the ruvCanalList
	 */
	public List getRuvCanalList() {
		return ruvCanalList;
	}

	/**
	 * @param ruvCanalList the ruvCanalList to set
	 */
	public void setRuvCanalList(List ruvCanalList) {
		this.ruvCanalList = ruvCanalList;
	}

	/**
	 * @return the ruvImpuestoList
	 */
	public List getRuvImpuestoList() {
		return ruvImpuestoList;
	}

	/**
	 * @param ruvImpuestoList the ruvImpuestoList to set
	 */
	public void setRuvImpuestoList(List ruvImpuestoList) {
		this.ruvImpuestoList = ruvImpuestoList;
	}

	/**
	 * @return the ruvDocLegalList
	 */
	public List getRuvDocLegalList() {
		return ruvDocLegalList;
	}

	/**
	 * @param ruvDocLegalList the ruvDocLegalList to set
	 */
	public void setRuvDocLegalList(List ruvDocLegalList) {
		this.ruvDocLegalList = ruvDocLegalList;
	}

	/**
	 * @return the ruvRegistroVentasList
	 */
	public List getRuvRegistroVentasList() {
		return ruvRegistroVentasList;
	}

	/**
	 * @param ruvRegistroVentasList the ruvRegistroVentasList to set
	 */
	public void setRuvRegistroVentasList(List ruvRegistroVentasList) {
		this.ruvRegistroVentasList = ruvRegistroVentasList;
	}

	/**
	 * @return the mostrarPopupClienteDesde
	 */
	public boolean isMostrarPopupClienteDesde() {
		return mostrarPopupClienteDesde;
	}

	/**
	 * @param mostrarPopupClienteDesde the mostrarPopupClienteDesde to set
	 */
	public void setMostrarPopupClienteDesde(boolean mostrarPopupClienteDesde) {
		this.mostrarPopupClienteDesde = mostrarPopupClienteDesde;
	}

	/**
	 * @return the mostrarPopupClienteHasta
	 */
	public boolean isMostrarPopupClienteHasta() {
		return mostrarPopupClienteHasta;
	}

	/**
	 * @param mostrarPopupClienteHasta the mostrarPopupClienteHasta to set
	 */
	public void setMostrarPopupClienteHasta(boolean mostrarPopupClienteHasta) {
		this.mostrarPopupClienteHasta = mostrarPopupClienteHasta;
	}

	/**
	 * @return the busquedaConsultorasAction
	 */
	public BusquedaConsultorasAction getBusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}

	/**
	 * @param busquedaConsultorasAction the busquedaConsultorasAction to set
	 */
	public void setBusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}

	/**
	 * @return the ruvAccesoList
	 */
	public List getRuvAccesoList() {
		return ruvAccesoList;
	}

	/**
	 * @param ruvAccesoList the ruvAccesoList to set
	 */
	public void setRuvAccesoList(List ruvAccesoList) {
		this.ruvAccesoList = ruvAccesoList;
	}

	/**
	 * @return the ruvSubAccesoList
	 */
	public List getRuvSubAccesoList() {
		return ruvSubAccesoList;
	}

	/**
	 * @param ruvSubAccesoList the ruvSubAccesoList to set
	 */
	public void setRuvSubAccesoList(List ruvSubAccesoList) {
		this.ruvSubAccesoList = ruvSubAccesoList;
	}

	/**
	 * @return the ruvSociedadList
	 */
	public List getRuvSociedadList() {
		return ruvSociedadList;
	}

	/**
	 * @param ruvSociedadList the ruvSociedadList to set
	 */
	public void setRuvSociedadList(List ruvSociedadList) {
		this.ruvSociedadList = ruvSociedadList;
	}

	/**
	 * @return the ruvDocList
	 */
	public List getRuvDocList() {
		return ruvDocList;
	}

	/**
	 * @param ruvDocList the ruvDocList to set
	 */
	public void setRuvDocList(List ruvDocList) {
		this.ruvDocList = ruvDocList;
	}

	/**
	 * @return the ruvCanalMantList
	 */
	public List getRuvCanalMantList() {
		return ruvCanalMantList;
	}

	/**
	 * @param ruvCanalMantList the ruvCanalMantList to set
	 */
	public void setRuvCanalMantList(List ruvCanalMantList) {
		this.ruvCanalMantList = ruvCanalMantList;
	}

	/**
	 * @return the ruvAccesoMantList
	 */
	public List getRuvAccesoMantList() {
		return ruvAccesoMantList;
	}

	/**
	 * @param ruvAccesoMantList the ruvAccesoMantList to set
	 */
	public void setRuvAccesoMantList(List ruvAccesoMantList) {
		this.ruvAccesoMantList = ruvAccesoMantList;
	}

	/**
	 * @return the ruvSubAccesoMantList
	 */
	public List getRuvSubAccesoMantList() {
		return ruvSubAccesoMantList;
	}

	/**
	 * @param ruvSubAccesoMantList the ruvSubAccesoMantList to set
	 */
	public void setRuvSubAccesoMantList(List ruvSubAccesoMantList) {
		this.ruvSubAccesoMantList = ruvSubAccesoMantList;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	/**
	 * @return the separadorMiles
	 */
	public String getSeparadorMiles() {
		return separadorMiles;
	}

	/**
	 * @param separadorMiles the separadorMiles to set
	 */
	public void setSeparadorMiles(String separadorMiles) {
		this.separadorMiles = separadorMiles;
	}

	/**
	 * @return the separadorDecimal
	 */
	public String getSeparadorDecimal() {
		return separadorDecimal;
	}

	/**
	 * @param separadorDecimal the separadorDecimal to set
	 */
	public void setSeparadorDecimal(String separadorDecimal) {
		this.separadorDecimal = separadorDecimal;
	}

	/**
	 * @return the numeroDecimales
	 */
	public Integer getNumeroDecimales() {
		return numeroDecimales;
	}

	/**
	 * @param numeroDecimales the numeroDecimales to set
	 */
	public void setNumeroDecimales(Integer numeroDecimales) {
		this.numeroDecimales = numeroDecimales;
	}

	
}
