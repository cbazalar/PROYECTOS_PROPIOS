package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ListaBlancaProductos;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECListaBlancaProductosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECListaBlancaProductosForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECListaBlancaProductosSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoRECListaBlancaProductosSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 657310830958724402L;
	
	private List recOperacionesSearchList;
	private List recTipoOperacionSearchList;
	private List recMotivoDevolucionSearchList;
	private List recRegionSearchList;
	private List recZonaSearchList;
	
	/*se utilizan en los metodos de cargar archivos*/
	private List recCodigoVentaArchivoList;
	private List recCodigoClienteInvalidoArchivoList;
	private List recCodigoClienteArchivoList;
	
	/* Segunda Pantalla*/
	private List siccOperacionesList;
	private List siccTipoOperacionList;
	private List recListMotivoDevolucion;
	private List siccRegionList;
	private List siccZonaList;
	private String codOperacion;
	private String codTipoOperacion;
	private String codRegion;
	private String codZona;
	private String attachmentVenta = "";
	private String attachmentCliente = ""; 
	
	private DataTableModel listaModel = new DataTableModel();
	private List lista = new ArrayList();
	private List seleccionado = new ArrayList();

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoRECListaBlancaProductosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoRECListaBlancaProductosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECListaBlancaProductosSearchForm searchForm = new MantenimientoRECListaBlancaProductosSearchForm();
		return searchForm;
	}	

	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoRECListaBlancaProductosSearchForm f = (MantenimientoRECListaBlancaProductosSearchForm) this.formBusqueda;
		MantenimientoRECListaBlancaProductosService service = (MantenimientoRECListaBlancaProductosService) getBean("spusicc.mantenimientoRECListaBlancaProductosService");
		int v_operacion = 0;
		int v_tipoOperacion = 0;
		int v_region = 0;
		int v_zona = 0;
		List lista = new ArrayList();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		// -- Validar seleccionables Operacion y Tipo Operacion
		if (f.getCodigoOperacion() != null) 
		{
			if (f.getCodigoOperacion().length == 1 && !f.getCodigoOperacion()[0].equals(""))
				v_operacion = 1;
			else if (f.getCodigoOperacion().length > 1)
				v_operacion = f.getCodigoOperacion().length;
		}
		
		if (f.getCodigoTipoOperacion() != null) 
		{
			if (f.getCodigoTipoOperacion().length == 1 && !f.getCodigoTipoOperacion()[0].equals(""))
				v_tipoOperacion = 1;
			else if (f.getCodigoTipoOperacion().length > 1)
				v_tipoOperacion = f.getCodigoTipoOperacion().length;
		}
		
		if (f.getZonaList() != null) 
		{
			if (f.getZonaList().length == 1 && !f.getZonaList()[0].equals(""))
				v_zona = 1;
			else if (f.getZonaList().length > 1)
				v_zona = f.getZonaList().length;
		}
		
		if (f.getRegionList() != null) 
		{
			if (f.getRegionList().length == 1 && !f.getRegionList()[0].equals(""))
				v_region = 1;
			else if (f.getRegionList().length > 1) 
			{
				v_region = f.getRegionList().length;
			}
		}
				
		// -- Crear pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoOperacion", v_operacion == 0 ? new ArrayList() : Arrays.asList(f.getCodigoOperacion()));
		criteria.put("codigoTipoOperacion", v_tipoOperacion == 0 ? new ArrayList() : Arrays.asList(f.getCodigoTipoOperacion()));
		criteria.put("codigoRegion", v_region == 0 ? new ArrayList() : Arrays.asList(f.getRegionList()));
		criteria.put("codigoZona", v_zona == 0 ? new ArrayList() : Arrays.asList(f.getZonaList()));
		criteria.put("codigoPeriodoInicio", f.getCodigoPeriodoInicio());
		criteria.put("codigoMotivoReal", f.getCodigoMotivoReal());
		criteria.put("codigoPeriodoFinal", f.getCodigoPeriodoFin());
		criteria.put("codigoVenta", f.getCodigoVenta());
		criteria.put("codigoCliente", f.getCodigoCliente());

		lista = service.getListaBlancaProductosList(criteria);
		this.lista = lista;
		this.listaModel = new DataTableModel(this.lista);
		log.debug("fin del metodo Find");

		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		if(StringUtils.equals(accion, "ELIMINAR")){
			this.accion = "ELIMINAR";
			if(this.seleccionado == null || this.seleccionado.size() <= 0){
				mensaje = this.getResourceMessage("errors.select.item");			
			}else
				this.beanRegistroSeleccionado = this.seleccionado.get(0);
			
		}		
		return mensaje;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		MantenimientoRECListaBlancaProductosService service = (MantenimientoRECListaBlancaProductosService) getBean("spusicc.mantenimientoRECListaBlancaProductosService");
		try {

			String[] arrIdListaBlanca = new String[this.seleccionado.size()];
						
			Map criteria = new HashMap();
			for (int i = 0; i < this.seleccionado.size(); i++) {
				ListaBlancaProductos registroSeleccionado = (ListaBlancaProductos) this.seleccionado.get(i); 
				arrIdListaBlanca[i] = registroSeleccionado.getOidListaBlancaProductos();
				criteria.put("oidListaBlancaProductos", arrIdListaBlanca[i].toString());
				service.deleteListaBlancaProductos(criteria);
				
			}				
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.getResourceMessage("errors.detail", new Object[]{error});
		}
	
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		return "mantenimientoRECListaBlancaProductosForm.deleted";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		
		MantenimientoRECListaBlancaProductosForm f = (MantenimientoRECListaBlancaProductosForm) this.formMantenimiento;
		MantenimientoRECListaBlancaProductosService service = (MantenimientoRECListaBlancaProductosService) getBean("spusicc.mantenimientoRECListaBlancaProductosService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		String lineaErrores = "";
		
		if (f.isNewRecord() == true) 
		{
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoTipoOperacion",f.getCodigoTipoOperacion()); 
			criteria.put("codigoVenta",f.getCodigoVenta());
			criteria.put("codigoCliente",f.getCodigoCliente());
			criteria.put("codigoPeriodoInicio",f.getCodigoPeriodoInicio());
			criteria.put("codigoMotivoReal", f.getCodigoMotivoReal());
			criteria.put("usuarioLogin", usuario.getLogin());
			criteria.put("codigoPeriodoFin",f.getCodigoPeriodoFin());
			criteria.put("codigoRegionList", f.getRegionList());
			criteria.put("codigoZonaList", f.getZonaList());
			
			List listaCodigoVentaArchivo = this.recCodigoVentaArchivoList;
			criteria.put("listaCodigoVentaArchivo", listaCodigoVentaArchivo);
			
			if(StringUtils.isBlank(f.getCodigoVenta()) && (listaCodigoVentaArchivo == null || listaCodigoVentaArchivo.size() ==0))
			{
				this.addError("", this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.no.datos.cuv"));				
				return false;
			}
			
			if(StringUtils.isNotBlank(f.getCodigoCliente()))
			{
				Integer codigo = service.getValidaCodigoCliente(f.getCodigoCliente());
				if(codigo.intValue()==0){
					this.addError("", this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.datos.codigoCliente.no.valido", new Object[]{f.getCodigoCliente()}));
					return false;
				}
			}
			
			List listaCodigosClienteInvalido = this.recCodigoClienteInvalidoArchivoList;
			if(listaCodigosClienteInvalido != null && listaCodigosClienteInvalido.size() > 0)
			{
				this.addError("", this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.datos.no.validos"));
				return false;
			}
			
			List listaCodigoClienteArchivo = this.recCodigoClienteArchivoList;
			criteria.put("listaCodigoClienteArchivo", listaCodigoClienteArchivo);
			
			lineaErrores = service.insertListaBlancaProductos(criteria);
			
			if (!StringUtils.isBlank(lineaErrores)) {
				log.debug("Mensaje con la lista los registros erroneos");
				//MOSTRAR LA LISTA CON LOS ERRORES
				this.addError("", lineaErrores);
				return false;
			}
			
			this.recCodigoVentaArchivoList = null;
			this.recCodigoClienteArchivoList = null;
			
		}else {
			try {
				String codigos[] = f.getCodigoTipoOperacion()[0].split("-");
				
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoOperacion",codigos[0]);
				criteria.put("codigoTipoOperacion",codigos[1]); 
				criteria.put("codigoVenta",f.getCodigoVenta());
				criteria.put("codigoCliente",f.getCodigoCliente());
				criteria.put("codigoPeriodoInicio",f.getCodigoPeriodoInicio());
				criteria.put("codigoMotivoReal", f.getCodigoMotivoReal());
				criteria.put("usuarioLogin", usuario.getLogin());
				criteria.put("codigoPeriodoFin",f.getCodigoPeriodoFin());
				criteria.put("codigoRegion", f.getRegionList()[0]);
				criteria.put("codigoZona", f.getZonaList()[0]);
				criteria.put("oidListaBlancaProductos", Integer.parseInt(f.getOidListaBlanca()));
				
				if (StringUtils.isBlank(f.getRegionList()[0])) {
					criteria.put("codigoRegion", "");
					criteria.put("codigoZona", "");
				}

				lineaErrores = service.updateListaBlancaProductos(criteria); 
				
				
				if (!StringUtils.isBlank(lineaErrores)) {
					log.debug("Mensaje con la lista los registros erroneos");
					//MOSTRAR LA LISTA CON LOS ERRORES
					this.addError("", lineaErrores);
					return false;
				}
			} catch (Exception e) 
			{
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) 
					error = e.getLocalizedMessage();
				this.addError("", this.getResourceMessage("errors.detail", new Object[]{error}));
			}
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#verificarRegistroSeleccionado()
	 */
	@Override
	protected boolean verificarRegistroSeleccionado() {	

		boolean verificar= true;
		
		try {
			
			if(!StringUtils.equals(this.accion, "ELIMINAR"))
			{
				if (this.seleccionado == null || this.seleccionado.size()==0){
					verificar = false;
					this.addWarn("Warning: ",this.getResourceMessage("errors.select.item"));
					return verificar;
				}				
				
				if(this.seleccionado.size()>1){
					verificar = false;
					this.addWarn("Warning: ",this.getResourceMessage("errors.select.unique.item"));
					return verificar;
				}				
			}					
		}	
		catch (Exception e) {		
			verificar = false;
		}
		
//		this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);			
//		this.mPantallaPrincipalBean.setManageBeanPadre(this);
		
		this.seleccionoRegistro = verificar;
		return verificar;
	
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		log.debug("Entering setObtenerRegistroAttributes method");
		//-- Variables		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		MantenimientoRECListaBlancaProductosForm f = new MantenimientoRECListaBlancaProductosForm();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoRECListaBlancaProductosService service = (MantenimientoRECListaBlancaProductosService) getBean("spusicc.mantenimientoRECListaBlancaProductosService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		f.setCodigoPais(pais.getCodigo());		
		
		this.siccOperacionesList = interfazSiCCService.getOperacionesByCodigoPais(criteriaOperacion);
		this.siccTipoOperacionList = new ArrayList();
		this.recListMotivoDevolucion = service.getListMotivoDevolucion();
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccZonaList = new ArrayList();
		
		//Limpiar campos
		BeanUtils.copyProperties(f, new MantenimientoRECListaBlancaProductosForm());
		
		this.attachmentVenta = "";
		this.attachmentCliente = "";
		
		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			ListaBlancaProductos registroSeleccionado = (ListaBlancaProductos)this.seleccionado.get(0);
			String oidListaBlancaProductos = registroSeleccionado.getOidListaBlancaProductos();
			if (oidListaBlancaProductos != null) 
			{
				// f.setModified(true);
				Map criteria = new HashMap();
				criteria.put("oidListaBlancaProductos", oidListaBlancaProductos);
				
				// -- cambiar la captura del id... que se recupere
				ListaBlancaProductos codigoVenta = service.consultarListaBlancaProductos(criteria);
				String[] operacion = { codigoVenta.getCodigoOperacion() };
				String[] tipoOperacion = { codigoVenta.getCodigoOperacion()	+ "-" + codigoVenta.getCodigoTipoOperacion() };
				String[] codigoZona = { codigoVenta.getCodigoZona() };
				String[] codigoRegion = { codigoVenta.getCodigoRegion() };
				this.siccZonaList = new ArrayList();
				List l = new ArrayList();
				
				if (codigoZona != null) 
				{
					LabelValue[] listaZonas = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", 
							codigoRegion, "T");

					if (listaZonas != null && listaZonas.length > 1) 
					{
						listaZonas[0] = new LabelValue("", "");
						for (LabelValue labelValue : listaZonas) {
							Base aux = new Base();
							aux.setCodigo(labelValue.getValue());
							aux.setDescripcion(labelValue.getLabel());
							l.add(aux);							
						}
						this.siccZonaList = l;
					}
				}
				
				if(operacion != null)
				{
					List op = new ArrayList();
					ArrayList op1 = new ArrayList();
					for (String ope : operacion) {
						op1.add(ope);
					}
					 LabelValue[] lv= ajaxService.getTiposOperaMultipleByOpera(pais.getCodigo(), op1, Constants.FORMATEAR_TODOS);
					 
					 for (LabelValue objeto : lv) {
						 Base aux = new Base();
						 aux.setCodigo(objeto.getValue());
						 aux.setDescripcion(objeto.getLabel());
						 op.add(aux);			
					}
					 this.siccTipoOperacionList = op;
				}

				f.setCodigoOperacion(operacion);
				f.setCodigoPeriodoFin(codigoVenta.getCodigoPeriodoFinal());
				f.setCodigoPeriodoInicio(codigoVenta.getCodigoPeriodoInicio());
				f.setCodigoTipoOperacion(tipoOperacion);
				f.setCodigoVenta(codigoVenta.getCodigoVenta());
				f.setCodigoCliente(codigoVenta.getCodigoCliente());
				f.setZonaList(codigoZona);
				f.setRegionList(codigoRegion);
				f.setCodigoMotivoReal(codigoVenta.getCodigoMotivoReal());
				f.setOidListaBlanca(codigoVenta.getOidListaBlancaProductos());
				
				this.codOperacion = operacion[0];
				this.codTipoOperacion = tipoOperacion[0];
				this.codRegion = codigoRegion[0];
				this.codZona = codigoZona[0];

				f.setNewRecord(false);
			}
		}
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarListaBusqueda = false;
		this.salirGrabarPantallaPadre = true;
		log.debug("Entering setViewAtributes method");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoRECListaBlancaProductosSearchForm f = (MantenimientoRECListaBlancaProductosSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoRECListaBlancaProductosService service = (MantenimientoRECListaBlancaProductosService) getBean("spusicc.mantenimientoRECListaBlancaProductosService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		BeanUtils.copyProperties(f, new MantenimientoRECListaBlancaProductosSearchForm());

		f.setCodigoPais(pais.getCodigo());

		this.recOperacionesSearchList = interfazSiCCService.getOperacionesByCodigoPais(criteriaOperacion);
		this.recMotivoDevolucionSearchList = service.getListMotivoDevolucion();

		this.recRegionSearchList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.recZonaSearchList = new ArrayList();

		f.setLineaDefecto(Constants.REC_NUMERO_REGISTROS_DEFECTO_CONSULTA);
		f.setLineaMaxima(Constants.REC_NUMERO_REGISTROS_MAXIMO_CONSULTA);
		
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		LabelValue[] lv= ajax.getTiposOperaMultipleByOpera(pais.getCodigo(),new ArrayList(), Constants.FORMATEAR_TODOS);
		List resultado = new ArrayList();
		for (LabelValue objeto : lv) {
			 Base aux = new Base();
			 aux.setCodigo(objeto.getValue());
			 aux.setDescripcion(objeto.getLabel());
			 resultado.add(aux);			
		}
		 this.recTipoOperacionSearchList = resultado;
		 
		 List resultado1 = new ArrayList();  
		 LabelValue[] lv1 = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT,new String[]{""}, Constants.FORMATEAR_TODOS);

			for (LabelValue objeto : lv1) 
			{
				Base aux = new Base();
				aux.setCodigo(objeto.getValue());
				aux.setDescripcion(objeto.getLabel());
				resultado1.add(aux);
			}
			this.recZonaSearchList = resultado1;
	}

	public void loadTipoOperacion(ValueChangeEvent event)
	{
		String[] operaciones = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ArrayList codigoOperaciones = new ArrayList();
		List resultado = new ArrayList();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		for (String operacion : operaciones) {
			codigoOperaciones.add(operacion);			
		}
		
		 LabelValue[] lv= ajax.getTiposOperaMultipleByOpera(pais.getCodigo(), codigoOperaciones, Constants.FORMATEAR_TODOS);
		 
		 for (LabelValue objeto : lv) {
			 Base aux = new Base();
			 aux.setCodigo(objeto.getValue());
			 aux.setDescripcion(objeto.getLabel());
			 resultado.add(aux);			
		}
		 this.recTipoOperacionSearchList = resultado;		
	}
	
	public void loadTipoOperacionForm(ValueChangeEvent event)
	{
		String[] operaciones = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ArrayList codigoOperaciones = new ArrayList();
		List resultado = new ArrayList();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		for (String operacion : operaciones) {
			codigoOperaciones.add(operacion);			
		}
		
		 LabelValue[] lv= ajax.getTiposOperaMultipleByOpera(pais.getCodigo(), codigoOperaciones, "");
		 
		 for (LabelValue objeto : lv) {
			 Base aux = new Base();
			 aux.setCodigo(objeto.getValue());
			 aux.setDescripcion(objeto.getLabel());
			 resultado.add(aux);			
		}
		 this.siccTipoOperacionList = resultado;		
	}
	
	
	
	
	public void loadZonas(ValueChangeEvent event)
	{
		String[] regiones = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		List resultado = new ArrayList();

		if (regiones.length > 0) 
		{
			LabelValue[] lv = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATEAR_TODOS);

			for (LabelValue objeto : lv) 
			{
				Base aux = new Base();
				aux.setCodigo(objeto.getValue());
				aux.setDescripcion(objeto.getLabel());
				resultado.add(aux);
			}
		}
		 
		this.recZonaSearchList = resultado;		
	}
	
	public void loadZonasForm(ValueChangeEvent event)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		List resultado = new ArrayList();
		LabelValue[] lv = {};
		Base aux = new Base();
		if(StringUtils.equals(this.accion, "NUEVO")){
			String[] regiones = (String[]) event.getNewValue();
			if (regiones.length > 0) 
			{
				lv = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATEAR_TODOS);				
			}
			
		}else{
			String regiones = (String) event.getNewValue();
			if (StringUtils.isNotBlank(regiones)) 
			{
				lv = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,new String[]{regiones}, Constants.FORMATEAR_TODOS);
								
				aux.setCodigo("");
				aux.setDescripcion("");
				resultado.add(aux);
			}
		}
		
		for (int i = 1; i < lv.length; i++) {
			LabelValue objeto = lv[i];
			aux = new Base();
			aux.setCodigo(objeto.getValue());
			aux.setDescripcion(objeto.getLabel());
			resultado.add(aux);
		}
		
				 
		this.siccZonaList = resultado;		
	}
	
	public void loadfile(FileUploadEvent event) throws IOException
	{
		log.debug("MantenimientoRECCodigoVentaOperaAction - loadfile");			
		
		MantenimientoRECListaBlancaProductosForm f = (MantenimientoRECListaBlancaProductosForm)this.formMantenimiento;		
		List listaCodigosVenta = new ArrayList();		
		f.setCodigoVentaFile(event.getFile());
		
		InputStream is = f.getCodigoVentaFile().getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		int cont = 0;
		while(true) {
			linea = br.readLine();
			if (linea == null)
				break;
			
			if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
				listaCodigosVenta.add(linea.trim());
			}else{
				cont++;
			}
		}
		
		if(cont > 0){						
			this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.datos.no.numericos", new Object[]{cont});
		}
		
		this.recCodigoVentaArchivoList = listaCodigosVenta;
		this.attachmentVenta = f.getCodigoVentaFile().getFileName();
	}
	
	public void loadarchivo(FileUploadEvent event) throws IOException
	{
		log.debug("MantenimientoRECCodigoVentaOperaAction - loadarchivo");			
		
		MantenimientoRECListaBlancaProductosForm f = (MantenimientoRECListaBlancaProductosForm)this.formMantenimiento;
		MantenimientoRECListaBlancaProductosService service = (MantenimientoRECListaBlancaProductosService) getBean("spusicc.mantenimientoRECListaBlancaProductosService");
		
		List listaCodigosCliente = new ArrayList();
		List listaCodigosClienteInvalido = new ArrayList();
		
		f.setCodigoClienteFile(event.getFile());
		
		InputStream is = f.getCodigoClienteFile().getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		int cont = 0;
		while(true) {
			linea = br.readLine();
			if (linea == null)
				break;
			
			if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
				Integer num = service.getValidaCodigoCliente(linea.trim());
				if(num.intValue()==1)
					listaCodigosCliente.add(linea.trim());
				else{
					this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.datos.codigoCliente.no.valido", new Object[]{linea.trim()});
					listaCodigosClienteInvalido.add(linea.trim());
				}
			}
			else
			{
				cont++;
			}
		}
		
		if(cont > 0){
			this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.datos.no.numericos", new Object[]{cont});
		}
		
		this.recCodigoClienteInvalidoArchivoList = listaCodigosClienteInvalido;
		this.recCodigoClienteArchivoList = listaCodigosCliente;	
		this.attachmentCliente = f.getCodigoClienteFile().getFileName();
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoRECListaBlancaProductosForm f = (MantenimientoRECListaBlancaProductosForm)this.formMantenimiento;	
		int  pInicio = 0, pFin = 0;
		boolean nuevoRegistro = f.isNewRecord();
		
		if(nuevoRegistro)
		{
			if(StringUtils.isEmpty(f.getCodigoVenta()) && f.getCodigoVentaFile() == null)
				mensaje = this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.msg.codigoVenta");			
		}else
		{
			if(StringUtils.isEmpty(f.getCodigoVenta()))
				mensaje = this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.msg.codigoVenta");
		}
		
		if(StringUtils.isNotEmpty(f.getCodigoPeriodoInicio()))
			pInicio = new Integer(f.getCodigoPeriodoInicio());

		if(StringUtils.isNotEmpty(f.getCodigoPeriodoFin()))
			pFin = new Integer(f.getCodigoPeriodoFin());
		
		if(pFin < pInicio && pFin > 0)
			mensaje = this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.periodo");
		
		if(f.getRegionList().length > 0 && f.getZonaList().length == 0)
			mensaje = this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.no.seleccion.Zonas");
		
		if(f.getCodigoOperacion() == null || f.getCodigoOperacion().length==0)
			mensaje = this.getResourceMessage("mantenimientoRECListaBlancaProductosForm.errors.no.seleccion.operacion");
		
		
		return mensaje;	
	}
	
	public List getRecOperacionesSearchList() {
		return recOperacionesSearchList;
	}

	public void setRecOperacionesSearchList(List recOperacionesSearchList) {
		this.recOperacionesSearchList = recOperacionesSearchList;
	}

	public List getRecTipoOperacionSearchList() {
		return recTipoOperacionSearchList;
	}

	public void setRecTipoOperacionSearchList(List recTipoOperacionSearchList) {
		this.recTipoOperacionSearchList = recTipoOperacionSearchList;
	}

	public List getRecMotivoDevolucionSearchList() {
		return recMotivoDevolucionSearchList;
	}

	public void setRecMotivoDevolucionSearchList(List recMotivoDevolucionSearchList) {
		this.recMotivoDevolucionSearchList = recMotivoDevolucionSearchList;
	}

	public List getRecRegionSearchList() {
		return recRegionSearchList;
	}

	public void setRecRegionSearchList(List recRegionSearchList) {
		this.recRegionSearchList = recRegionSearchList;
	}

	public List getRecZonaSearchList() {
		return recZonaSearchList;
	}

	public void setRecZonaSearchList(List recZonaSearchList) {
		this.recZonaSearchList = recZonaSearchList;
	}

	public List getRecCodigoVentaArchivoList() {
		return recCodigoVentaArchivoList;
	}

	public void setRecCodigoVentaArchivoList(List recCodigoVentaArchivoList) {
		this.recCodigoVentaArchivoList = recCodigoVentaArchivoList;
	}

	public List getRecCodigoClienteInvalidoArchivoList() {
		return recCodigoClienteInvalidoArchivoList;
	}

	public void setRecCodigoClienteInvalidoArchivoList(
			List recCodigoClienteInvalidoArchivoList) {
		this.recCodigoClienteInvalidoArchivoList = recCodigoClienteInvalidoArchivoList;
	}

	public List getRecCodigoClienteArchivoList() {
		return recCodigoClienteArchivoList;
	}

	public void setRecCodigoClienteArchivoList(List recCodigoClienteArchivoList) {
		this.recCodigoClienteArchivoList = recCodigoClienteArchivoList;
	}

	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	public List getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	public void setSiccTipoOperacionList(List siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	public List getRecListMotivoDevolucion() {
		return recListMotivoDevolucion;
	}

	public void setRecListMotivoDevolucion(List recListMotivoDevolucion) {
		this.recListMotivoDevolucion = recListMotivoDevolucion;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public String getCodOperacion() {
		return codOperacion;
	}

	public void setCodOperacion(String codOperacion) {
		this.codOperacion = codOperacion;
	}

	public String getCodTipoOperacion() {
		return codTipoOperacion;
	}

	public void setCodTipoOperacion(String codTipoOperacion) {
		this.codTipoOperacion = codTipoOperacion;
	}

	public String getCodRegion() {
		return codRegion;
	}

	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}

	public String getCodZona() {
		return codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public String getAttachmentVenta() {
		return attachmentVenta;
	}

	public void setAttachmentVenta(String attachmentVenta) {
		this.attachmentVenta = attachmentVenta;
	}

	public String getAttachmentCliente() {
		return attachmentCliente;
	}

	public void setAttachmentCliente(String attachmentCliente) {
		this.attachmentCliente = attachmentCliente;
	}

	/**
	 * @return the listaModel
	 */
	public DataTableModel getListaModel() {
		return listaModel;
	}

	/**
	 * @param listaModel the listaModel to set
	 */
	public void setListaModel(DataTableModel listaModel) {
		this.listaModel = listaModel;
	}

	/**
	 * @return the lista
	 */
	public List getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}

	/**
	 * @return the seleccionado
	 */
	public List getSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(List seleccionado) {
		this.seleccionado = seleccionado;
	}
	
}