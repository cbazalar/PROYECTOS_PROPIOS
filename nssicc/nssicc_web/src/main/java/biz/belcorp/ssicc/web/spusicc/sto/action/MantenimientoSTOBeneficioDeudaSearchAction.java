package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBeneficioDeudaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOBeneficioDeudaForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOBeneficioDeudaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoSTOBeneficioDeudaSearchAction extends	BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 5909631743124559239L;
	
	private String oid = "";
	private List siccRegionList;
	private List siccTipoClienteList;
	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacion;
	private LabelValue[] siccClasificacion;
	private LabelValue[] siccZonaList;
	private Object[] objetoSeleccionados;
	
	//
	private LabelValue[] nuevoSubTipoClienteList;
	private LabelValue[] nuevoTipoClasificacion;
	private LabelValue[] nuevoClasificacion;
	private LabelValue[] nuevoZonaList;
	private List nuevostoLevantamientoErroresClientesListPrinc;
	private List nuevostoResumenClientesList;
	private Boolean mostrarGrillaNueva;
	private String attachmentNuevo = "";
	private String msjeSave;
	
	private List stoLevantamientoErroresClientesListPrinc;
	private List stoBeneficioDeudaList;
	private List stoResumenClientesList;
	private String codigoIdiomaIso;
	private boolean mantenimiento = false;
	private String attachment = "";
	private Boolean mostrarGrilla;
	private Boolean mostrarBeneficios;
	private Boolean showPorcentaje;
	private Boolean flagValidador;

	
	//Metodo subir Archivo- Busqueda -Pantalla principal
	public void handleFileUpload(FileUploadEvent event) {		
		String mensaje = "";
		try {
			MantenimientoSTOBeneficioDeudaSearchForm f = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;
			if (event != null) {
				f.setClienteFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
			}
		} catch (Exception e) {
			mensaje = e.getMessage().toString();
			this.addError("Error : ", mensaje);
		}
	}
	
	//Metodo subir Archivo- Busqueda -Pantalla Nuevo
	public void handleFileUploadNuevo(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		String mensaje = "";
		try {			
			MantenimientoSTOBeneficioDeudaForm mant = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
			if (event != null) {
				mant.setClienteFile(event.getFile());
				this.attachmentNuevo=event.getFile().getFileName();				
				this.uploadArchivoNuevo();
			}

		} catch (Exception e) {
			mensaje = e.getMessage().toString();
			this.addError("Error : ", mensaje);
		}
	}
	
	public void uploadArchivo() throws Exception {		
		MantenimientoSTOBeneficioDeudaSearchForm f = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		List listaClientes = new ArrayList();

		String indValidaCodConsultoraDocIdentidad = null;

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}

		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(),longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,service.getCodigoConsultora(criteria));

			if (bean.getValue() == null	&& StringUtils.equals(indValidaCodConsultoraDocIdentidad,Constants.SI)) {
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service.getCodigoConsultoraPorDocumentoIdentidad(criteria);

				if (codigoConsultoraPorDocIden == null) {
					bean = new LabelValue(codigoConsultora,	service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				} else {
					bean = new LabelValue(codigoConsultoraPorDocIden,service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}

			listaClientes.add(bean);

			if (bean.getValue() == null)
				cont++;

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		this.oid = service.getOidCargaCliente();
		criteria.put("oid", this.oid);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");
		f.setOidTipoCliente(f.getOidTipoCliente());
		f.setCodigoRegion(f.getCodigoRegion());
		f.setCodigoZona(f.getCodigoZona());

		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont	+ " codigo(s) errado(s)");

		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);
		// getResourceMessage(key, args)
		this.stoLevantamientoErroresClientesListPrinc= list;
		if (this.stoLevantamientoErroresClientesListPrinc != null) {
			this.mostrarGrilla = true;
		}
		this.stoResumenClientesList = list2;
	}
	
	//Subir Archivos- Pantalla Nueva
	public void uploadArchivoNuevo() throws Exception {		
		MantenimientoSTOBeneficioDeudaForm mant = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		List listaClientes = new ArrayList();

		String indValidaCodConsultoraDocIdentidad = null;

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}

		UploadedFile archivo = mant.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(),longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,service.getCodigoConsultora(criteria));

			if (bean.getValue() == null	&& StringUtils.equals(indValidaCodConsultoraDocIdentidad,Constants.SI)) {
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service.getCodigoConsultoraPorDocumentoIdentidad(criteria);

				if (codigoConsultoraPorDocIden == null) {
					bean = new LabelValue(codigoConsultora,	service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				} else {
					bean = new LabelValue(codigoConsultoraPorDocIden,service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}

			listaClientes.add(bean);

			if (bean.getValue() == null)
				cont++;

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		this.oid = service.getOidCargaCliente();
		criteria.put("oid", this.oid);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		mant.setCodigosErradosFile("");
		mant.setOidTipoCliente(mant.getOidTipoCliente());
		mant.setCodigoRegion(mant.getCodigoRegion());
		mant.setCodigoZona(mant.getCodigoZona());

		if (cont != 0)
			mant.setCodigosErradosFile("Existe(n) " + cont	+ " codigo(s) errado(s)");

		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);
		
		this.nuevostoLevantamientoErroresClientesListPrinc= list;
		if (this.nuevostoLevantamientoErroresClientesListPrinc != null)
			this.mostrarGrillaNueva = true;		
		this.nuevostoResumenClientesList = list2;
	}

	/**
	 * Limpiando las listas del action
	 */
	public void limpiarNuevo(MantenimientoSTOBeneficioDeudaForm f) {
		this.nuevostoLevantamientoErroresClientesListPrinc=null;
		this.nuevostoResumenClientesList = null;		
		this.mostrarGrillaNueva = false;
		this.attachmentNuevo = "";		
		this.nuevoTipoClasificacion=null;
		this.nuevoClasificacion=null;
		this.nuevoZonaList=null;
		this.msjeSave="";
		this.showPorcentaje=false;
		this.mostrarBeneficios=true;
		this.flagValidador=false;
		f.setCodigoCliente("");		
		f.setOidSubTipoCliente(""); 
		f.setOidTipoClasificacion("");
		f.setOidClasificacion(""); 
		f.setCodigoRegion(""); 
		f.setCodigoZona(""); 		
	}

	/**
	 * Limpia los campos del formulario
	 * 
	 * @param f
	 */
	private void cleanForm(MantenimientoSTOBeneficioDeudaSearchForm f) {
		f.setCodigoCliente("");
		f.setCodigoPeriodo("");
		f.setOidTipoCliente("");// this.tipoCliente = null;
		f.setOidSubTipoCliente(""); // this.subTipoCliente = null;
		f.setOidTipoClasificacion(""); // this.tipoClasificacion = null;
		f.setOidClasificacion(""); // this.clasificacion = null;
		f.setCodigoRegion(""); // this.codigoRegion = null;
		f.setCodigoZona(""); // this.codigoZona = null;
		f.setTipoBeneficio("");
	}

	/**
	 * Cargar sub tipos de clientes
	 * 
	 * @param val
	 */
	public void loadSubTiposClientes(ValueChangeEvent val) {
		if(val.getNewValue()!=null){
			String stipos = val.getNewValue().toString();
			ArrayList tipos = new ArrayList();
			tipos.add(stipos);
			if (!stipos.equals(null)) {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				LabelValue[] result = ajax.getSubTiposClientesPorPaisTipoClientesOID(this.codigoIdiomaIso, tipos);
				this.setSiccSubTipoClienteList(result);
				this.siccTipoClasificacion=null;
				this.siccClasificacion=null;
			}
		} else {
			this.siccSubTipoClienteList = null;
			this.siccTipoClasificacion=null;
			this.siccClasificacion=null;
		}
	}
	
	// Muestra el Subtipo Cliente de la Nueva pagina
	public void loadSubTiposClienteNuevo(ValueChangeEvent val) {
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;		
			String stipos = val.getNewValue().toString();
			ArrayList tipos = new ArrayList();
			tipos.add(stipos);
			if (StringUtils.isNotBlank(stipos)) {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				LabelValue[] result = ajax.getSubTiposClientesPorPaisTipoClientesOID(this.codigoIdiomaIso, tipos);
				this.nuevoSubTipoClienteList=result;			
				this.nuevoTipoClasificacion=null;			
				this.nuevoClasificacion=null;
				f.setOidSubTipoCliente(""); 
				f.setOidTipoClasificacion(""); 
				f.setOidClasificacion("");
			} else {			
				this.nuevoSubTipoClienteList=null;			
				this.nuevoTipoClasificacion=null;			
				this.nuevoClasificacion=null;
				f.setOidSubTipoCliente(""); 
				f.setOidTipoClasificacion(""); 
				f.setOidClasificacion("");
		}
	}

	/**
	 * Consultar el codigo de consultora, y obtener el nombre	
	 */
	public void consultar(ValueChangeEvent val) {
		String stipos = val.getNewValue().toString();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String nombre = ajax.getNombreCliente(stipos);
	}

	/**
	 * Cargar tipos de clasificaciones	
	 */	
	public void loadTiposClasificaciones(ValueChangeEvent val) {
		MantenimientoSTOBeneficioDeudaSearchForm search = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;
		if(val.getNewValue()!=null){
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String subTipo = val.getNewValue().toString();
			ArrayList listSubtipo = new ArrayList();
			listSubtipo.add(subTipo);		
			if(!subTipo.equals(null)){
				LabelValue[] result = ajax.getTiposClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso, search.getOidTipoCliente(),
									listSubtipo);
				this.siccTipoClasificacion=result;
				this.siccClasificacion=null;
			}
		}else{
			this.siccTipoClasificacion=null;
			this.siccClasificacion=null;
		}		
	}
	
	//Carga el Combo Tipo Clasificacion-Pantalla Nueva
	public void loadTiposClasificacionesNuevo(ValueChangeEvent val) {		
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		if(val.getNewValue()!=null){
			String subTipo = val.getNewValue().toString();
			ArrayList listSubtipo = new ArrayList();
			listSubtipo.add(subTipo);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (!subTipo.equals(null)) {			
				LabelValue[] result = ajax.getTiposClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso, f.getOidTipoCliente(),
									listSubtipo);
				this.nuevoTipoClasificacion=result;
				this.nuevoClasificacion=null;
			}
		} else {
			this.nuevoTipoClasificacion=null;
			this.nuevoClasificacion=null;	
		}
	}

	/**
	 * Cargar calificaciones	
	 */
	public void loadClasificaciones(ValueChangeEvent val) {
		MantenimientoSTOBeneficioDeudaSearchForm search = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;	
		if(val.getNewValue()!=null){
			ArrayList listSubtipo = new ArrayList();
			listSubtipo.add(search.getOidSubTipoCliente());
	
			String tipoClasificacion = val.getNewValue().toString();
			ArrayList listTipoClasificacion = new ArrayList();
			listTipoClasificacion.add(tipoClasificacion);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (!tipoClasificacion.equals(null)) {
				ArrayList listSubtipoForm = new ArrayList();
				listSubtipoForm.add(search.getOidSubTipoCliente());
				LabelValue[] result = ajax.getClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso, search.getOidTipoCliente(),
										listSubtipoForm, listTipoClasificacion);
				this.siccClasificacion=result;
			}
		}else
			this.siccClasificacion=null;				
	}
	
	//Carga el combo Clasificacion -Pantalla Nueva
	public void loadClasificacionesNuevo(ValueChangeEvent val) {		
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		if(val.getNewValue()!=null){
			ArrayList listSubtipo = new ArrayList();
			listSubtipo.add(f.getOidSubTipoCliente());
			String tipoClasificacion = val.getNewValue().toString();
			ArrayList listTipoClasificacion = new ArrayList();
			listTipoClasificacion.add(tipoClasificacion);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (!tipoClasificacion.equals(null)) {			
					ArrayList listSubtipoForm = new ArrayList();
					listSubtipoForm.add(f.getOidSubTipoCliente());
					LabelValue[] result = ajax.getClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso, f.getOidTipoCliente(),
											listSubtipoForm, listTipoClasificacion);
					this.nuevoClasificacion = result;
			}
		} else 
			this.nuevoClasificacion = null;
		
	}

	/**
	 * Cargar las zonas respecto a las regiones seleccionadas	
	 */
	
	public void loadZonas(ValueChangeEvent val) {		
		MantenimientoSTOBeneficioDeudaSearchForm search = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;	
		if(val.getNewValue()!=null){
			String valor = val.getNewValue().toString();
			String[] regiones = {valor};
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (regiones.length > 0) {
				LabelValue[] result = ajax.getZonasMultipleByPaisMarcaCanalRegion(search.getCodigoPais(), "T", "VD", regiones, "");		
				this.siccZonaList=result;
			}
		}else
			this.siccZonaList=null;		
	}
	
	//Carga las Combo Zonas- Pantalla Nueva
	public void loadZonasNuevo(ValueChangeEvent val) {		
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		if(val.getNewValue()!=null){
			String valor = val.getNewValue().toString();
			String[] regiones = { valor };
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (regiones.length > 0) {		
				LabelValue[] result = ajax.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), "T", "VD", regiones, "");
				this.nuevoZonaList=result;	
			}		 
		}else 
			this.nuevoZonaList=null;	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOBeneficioDeudaList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOBeneficioDeudaForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOBeneficioDeudaSearchForm form = new MantenimientoSTOBeneficioDeudaSearchForm();
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
		log.debug("MantenimientoSTOBeneficioDeudaSearchAction - setFindAttributes");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOBeneficioDeudaSearchForm f = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		MantenimientoSTOBeneficioDeudaService service = (MantenimientoSTOBeneficioDeudaService) getBean("spusicc.mantenimientoSTOBeneficioDeudaService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		List lista = new ArrayList();

		if (stoBeneficioDeudaList != null) {
			MantenimientoSTOBeneficioDeudaSearchForm beanSearch = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;

			f.setCodigoPeriodo(beanSearch.getCodigoPeriodo());
			f.setCodigoCliente(beanSearch.getCodigoCliente());
			f.setOidTipoCliente(beanSearch.getOidTipoCliente());
			f.setOidSubTipoCliente(beanSearch.getOidSubTipoCliente());
			f.setOidTipoClasificacion(beanSearch.getOidTipoClasificacion());
			f.setOidClasificacion(beanSearch.getOidClasificacion());
			f.setCodigoRegion(beanSearch.getCodigoRegion());
			f.setCodigoZona(beanSearch.getCodigoZona());
			f.setTipoBeneficio(beanSearch.getTipoBeneficio());
		}

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());

		if (StringUtils.isNotBlank(f.getCodigoCliente()))
			criteria.put("oidCliente", reporteService.getOidString(
					"getOidClienteByCodigoCliente", criteria));
		else
			criteria.put("oidCliente", null);

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		if (StringUtils.isNotBlank(f.getOidTipoCliente()))
			criteria.put("oidTipoCliente",
					Integer.valueOf(f.getOidTipoCliente()));
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", Integer.valueOf(f.getOidSubTipoCliente()));			
		} else 
			criteria.put("oidSubTipoCliente", null);		
		

		if (StringUtils.isNotBlank(f.getOidTipoClasificacion())) 
			criteria.put("oidSubTipoClasificacion",	Integer.valueOf(f.getOidTipoClasificacion()));	
		else 
			criteria.put("oidSubTipoClasificacion", null);
		
		if (StringUtils.isNotBlank(f.getOidClasificacion())) 
			criteria.put("oidClasificacion", Integer.valueOf(f.getOidClasificacion()));			
		else 
			criteria.put("oidClasificacion", null);
	
		if (StringUtils.isNotBlank(f.getCodigoRegion())) {
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("oidRegion", reporteService.getOidRegionByPaisMarcaCanal(criteria));
		} else
			criteria.put("oidRegion", null);

		String codigoZona = f.getCodigoZona();
		if (StringUtils.isNotBlank(codigoZona)) {
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("oidZona",	reporteService.getOidZonaByPaisMarcaCanalRegion(criteria));			
		} else 
			criteria.put("oidZona", null);		

		if (StringUtils.isNotBlank(f.getTipoBeneficio())) 
			criteria.put("tipoBeneficio", f.getTipoBeneficio());			
		else 
			criteria.put("tipoBeneficio", null);
		
		//grilla del archivo
		List listaClientes = stoLevantamientoErroresClientesListPrinc; 
		// SI cargo consultoras por el archivo
		if (listaClientes != null && StringUtils.isBlank(f.getCodigoCliente())) {
			criteria.put("oidClienteCarga", this.oid);
		} else {
			criteria.put("oidClienteCarga", null);
		}

		lista = service.getBeneficioDeudaList(criteria);
	
		this.stoBeneficioDeudaList = lista;
		this.datatableBusqueda = new DataTableModel(this.stoBeneficioDeudaList);
		return lista;
		
	}

	/**
	 * Consultar codigo de consultora
	 */
	public void validarCodigoConsultora() {
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		String codigoCliente = f.getCodigoCliente();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String codigocliente = aSvc.getNombreCliente(codigoCliente);

		String mensaje = null;
		if (codigocliente != null) {
			this.flagValidador=false;
			return;
		} else {
			mensaje = "Codigo de Cliente no existe, por favor ingrese un codigo valido para hacer la busqueda";
			this.flagValidador=true;
			this.addError("Error : ", mensaje);
		}
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
	
	//metodo para eliminar los datos de la grilla principal
	public void eliminarPrincipal(ActionEvent action){
		try {			
			if(this.objetoSeleccionados==null || this.objetoSeleccionados.length<=0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;	
			}	
			
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoSTOBeneficioDeudaService service = (MantenimientoSTOBeneficioDeudaService) getBean("spusicc.mantenimientoSTOBeneficioDeudaService");
			int tam=this.objetoSeleccionados.length;
			String []object=new String[tam];
			for(int i=0;i<this.objetoSeleccionados.length;i++){
				Map seleccionados=(Map)this.objetoSeleccionados[i];
				String id=seleccionados.get("oidClienteBeneDeud").toString();
				object[i]=id;
			}

			Map parametros = new HashMap();
			parametros.put("idSeleccionados", object);
			parametros.put("usuario", usuario.getCodigo());

			service.deleteBeneficioDeuda(parametros);	
			setFindAttributes();
			this.addInfo("Info:", this.getResourceMessage("datos.delete.ok"));
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		MantenimientoSTOBeneficioDeudaSearchForm formSearch = new MantenimientoSTOBeneficioDeudaSearchForm();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOBeneficioDeudaService service = 
				(MantenimientoSTOBeneficioDeudaService)getBean("spusicc.mantenimientoSTOBeneficioDeudaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		boolean isArchivo = false;
		Map criteria = new HashMap();

		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("usuario", usuario.getLogin());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		formSearch.setCodigoPeriodo(f.getCodigoPeriodo());
		formSearch.setCodigoCliente(f.getCodigoCliente());
		formSearch.setOidTipoCliente(f.getOidTipoCliente());
		formSearch.setOidSubTipoCliente(f.getOidSubTipoCliente());
		formSearch.setOidClasificacion(f.getOidClasificacion());
		formSearch.setCodigoRegion(f.getCodigoRegion());
		formSearch.setCodigoZona(f.getCodigoZona());
		formSearch.setOidTipoClasificacion(f.getOidTipoClasificacion());
		formSearch.setMontoDeuda(f.getMontoDeuda());
		formSearch.setPorcentajeBeneficio(f.getPorcentajeBeneficio());
		

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		if (StringUtils.isNotBlank(f.getCodigoCliente()))
			criteria.put("oidCliente", reporteService.getOidString("getOidClienteByCodigoCliente", criteria));
		else
			criteria.put("oidCliente", null);

		if (StringUtils.isNotBlank(f.getOidTipoCliente()))
			criteria.put("oidTipoCliente", f.getOidTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", f.getOidSubTipoCliente());
		} else {
			criteria.put("oidSubTipoCliente", null);
		}

		if (StringUtils.isNotBlank(f.getOidTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getOidTipoClasificacion());
		} else {
			criteria.put("oidSubTipoClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getOidClasificacion())) {
			criteria.put("oidClasificacion", f.getOidClasificacion());
		} else {
			criteria.put("oidClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion())	&& !Constants.FORMATEAR_TODOS.equals(f.getCodigoRegion())) {
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("oidRegion",reporteService.getOidRegionByPaisMarcaCanal(criteria));
		} else {
			List listaRegiones = siccRegionList;
			criteria.put("codigoRegion", StringUtils.isEmpty(f.getCodigoRegion()) ? Constants.STO_VALOR_OID_NULL: Constants.FORMATEAR_TODOS);
			criteria.put("listaRegionesSTO", listaRegiones);
			if (StringUtils.isNotEmpty(f.getCodigoRegion())) {
				this.nuevostoLevantamientoErroresClientesListPrinc = null;
				this.nuevostoResumenClientesList = null;
			}
			criteria.put("oidRegion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("oidZona",	reporteService.getOidZonaByPaisMarcaCanalRegion(criteria));
		} else {
			criteria.put("oidZona", null);
		}

		if (StringUtils.isNotBlank(f.getMontoDeuda())) {
			criteria.put("montoDeuda", f.getMontoDeuda());
		} else
			criteria.put("montoDeuda", null);

		if (StringUtils.isNotBlank(f.getPorcentajeBeneficio()))
			criteria.put("porcentajeBeneficio", f.getPorcentajeBeneficio());
		else
			criteria.put("porcentajeBeneficio", null);

		criteria.put("fechaCreacion", Calendar.getInstance().getTime());
		criteria.put("observaciones", f.getObservaciones());
		criteria.put("tipoBeneficio", f.getTipoBeneficio());

		/*-------------------------*/
		// Listado de Cliente
		List clienteList = new ArrayList(); // result
		String codigoCliente = f.getCodigoCliente();
		//grilla del archivo
		List listaClientes = this.nuevostoLevantamientoErroresClientesListPrinc; 
		
		Map param = new HashMap();
		Map map = new HashMap();

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			isArchivo = true; 
			for (int i = 0; i < listaClientes.size(); i++) {
				map = (Map) listaClientes.get(i);
				codigoCliente = (String) map.get("codigoCliente");
				param.put("codigoCliente", codigoCliente);
				try {
					String valor=reporteService.getOidString("getOidClienteByCodigoCliente", param);
					if(StringUtils.isNotBlank(valor))
						clienteList.add(valor);
				} catch (Exception e) {
					//this.addError("Error : ",this.obtieneMensajeErrorException(e));
				}
			}
		}
		criteria.put("clienteList", (clienteList == null) ? new ArrayList(): clienteList);
		/*-------------------------*/

		this.nuevostoLevantamientoErroresClientesListPrinc = listaClientes;
		int cantidadInsertados = service.insertDeudaBenficio(criteria);		
		int cantInserta=0;
		if(isArchivo)
			cantInserta=cantidadInsertados;
		else
			cantInserta=-1;
		
		
		if(cantInserta == -1){			
			this.msjeSave=this.getResourceMessage("mantenimientoSTOBeneficioDeudaForm.satisfactorio");
		}
		else
			this.msjeSave=this.getResourceMessage("mantenimientoSTOBeneficioDeudaForm.satisfactorio")+" "+
								"Se procesaron "+cantidadInsertados+" codigo(s) de consultora";		
		
		this.stoBeneficioDeudaList = setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.stoBeneficioDeudaList);
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
		this.mantenimiento = true;
		this.mostrarBeneficios = true;

		log.debug("MantenimientoSTOBeneficioDeudaAction - setViewAttributes");
		MantenimientoSTOBeneficioDeudaForm f = new MantenimientoSTOBeneficioDeudaForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", f.getCodigoPais());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		criteriaOperacion.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaOperacion.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaOperacion);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setTipoBeneficio(Constants.NUMERO_UNO);
		f.setMontoDeuda("0.00");
		f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
		//Cargar subtipoCliente
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ArrayList tipos = new ArrayList();
		tipos.add(f.getOidTipoCliente());
		LabelValue[] result = ajax.getSubTiposClientesPorPaisTipoClientesOID(this.codigoIdiomaIso, tipos);
		this.nuevoSubTipoClienteList=result;	
		limpiarNuevo(f);
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
		log.debug("MantenimientoSTOBeneficioDeudaSearchAction - setViewAttributes");
		MantenimientoSTOBeneficioDeudaSearchForm f = (MantenimientoSTOBeneficioDeudaSearchForm) this.formBusqueda;	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.invocarFindLuegoGrabar = false;
			
		cleanForm(f);
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda=false;
		this.mostrarBotonEliminar=false;
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		criteriaOperacion.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaOperacion.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		this.codigoIdiomaIso = usuario.getIdioma().getCodigoISO();
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaOperacion);

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setTipoBeneficio(Constants.NUMERO_UNO);
		f.setMontoDeuda("0.00");
		
	}

	/**
	 * Actualizar combo y modificacion del datos en el xhtml
	 * 
	 * @param val
	 */

	public void actualizarCombo(ValueChangeEvent val) {	
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		this.mostrarBeneficios = false;
		this.showPorcentaje=false;
		String valor = (String) val.getNewValue();
		try {
			if(StringUtils.isNotBlank(valor)){				
				f.setTipoBeneficio(valor);
				if (StringUtils.equals(valor, "1")) {
					f.setMontoDeuda("");
					f.setPorcentajeBeneficio("");
					this.mostrarBeneficios = true;
					this.showPorcentaje=false;
				} else {
					this.mostrarBeneficios = false;
					this.showPorcentaje=true;
					f.setMontoDeuda("");
					f.setPorcentajeBeneficio("");
				}
			}else{
				this.mostrarBeneficios = false;
				this.showPorcentaje=false;
				f.setMontoDeuda("");
				f.setPorcentajeBeneficio("");				
			}
				
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	@Override
	public String devuelveMensajeAlertaDefaultAction() throws Exception  {		
		return this.msjeSave;	
	}
	
	@Override
	public String setValidarMantenimiento(){
		MantenimientoSTOBeneficioDeudaForm f = (MantenimientoSTOBeneficioDeudaForm) this.formMantenimiento;
		if(StringUtils.equals(f.getTipoBeneficio(), "1") && StringUtils.isBlank(f.getMontoDeuda()))
			return "Debe ingresar monto deuda";
		if(StringUtils.equals(f.getTipoBeneficio(), "2") && StringUtils.isBlank(f.getPorcentajeBeneficio()))
			return "Debe ingresar porcentaje beneficio";
		int cont =0;
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
			cont++;
		if(StringUtils.isNotBlank(f.getOidSubTipoCliente()))
			cont++;
		if(StringUtils.isNotBlank(f.getOidClasificacion()))
			cont++;
		if(StringUtils.isNotBlank(f.getCodigoRegion()))
			cont++;
		if(StringUtils.isNotBlank(f.getCodigoZona()))
			cont++;
		if(StringUtils.isNotBlank(f.getCodigoCliente()))
			cont++;
		if(this.nuevostoResumenClientesList!=null && this.nuevostoResumenClientesList.size()>0)
			cont++;
		
		if(cont<2)
			return "Debe seleccionar al menos 2 filtros";
		if(StringUtils.isNotBlank(f.getCodigoCliente())){
			if(this.flagValidador)
				return "Antes de Guardar por favor corrija el Codigo del Cliente";
		}
		
		if(StringUtils.isBlank(f.getOidTipoCliente()) && StringUtils.isBlank(f.getCodigoRegion()) && StringUtils.isBlank(f.getCodigoCliente()))
			return "Por lo menos Tipo Cliente, Región o Codigo de Cliente deben estar ingresadas";
		
		List lista=this.nuevostoLevantamientoErroresClientesListPrinc;
		if(lista!=null){
			if(lista.size()==1 && StringUtils.isNotBlank(f.getCodigosErradosFile()))
				return "No existen códigos válidos, corrija el archivo antes de grabar";
		}
		
		return "";
		
	}
	
	public Boolean getFlagValidador() {
		return flagValidador;
	}

	public void setFlagValidador(Boolean flagValidador) {
		this.flagValidador = flagValidador;
	}

	public String getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList
	 *            the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the stoLevantamientoErroresClientesListPrinc
	 */
	public List getStoLevantamientoErroresClientesListPrinc() {
		return stoLevantamientoErroresClientesListPrinc;
	}

	/**
	 * @param stoLevantamientoErroresClientesListPrinc
	 *            the stoLevantamientoErroresClientesListPrinc to set
	 */
	public void setStoLevantamientoErroresClientesListPrinc(
			List stoLevantamientoErroresClientesListPrinc) {
		this.stoLevantamientoErroresClientesListPrinc = stoLevantamientoErroresClientesListPrinc;
	}

	/**
	 * @return the stoBeneficioDeudaList
	 */
	public List getStoBeneficioDeudaList() {
		return stoBeneficioDeudaList;
	}

	/**
	 * @param stoBeneficioDeudaList
	 *            the stoBeneficioDeudaList to set
	 */
	public void setStoBeneficioDeudaList(List stoBeneficioDeudaList) {
		this.stoBeneficioDeudaList = stoBeneficioDeudaList;
	}

	/**
	 * @return the stoResumenClientesList
	 */
	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	/**
	 * @param stoResumenClientesList
	 *            the stoResumenClientesList to set
	 */
	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList
	 *            the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return the siccTipoClasificacion
	 */
	public LabelValue[] getSiccTipoClasificacion() {
		return siccTipoClasificacion;
	}

	/**
	 * @param siccTipoClasificacion
	 *            the siccTipoClasificacion to set
	 */
	public void setSiccTipoClasificacion(LabelValue[] siccTipoClasificacion) {
		this.siccTipoClasificacion = siccTipoClasificacion;
	}

	/**
	 * @return the codigoIdiomaIso
	 */
	public String getCodigoIdiomaIso() {
		return codigoIdiomaIso;
	}

	/**
	 * @param codigoIdiomaIso
	 *            the codigoIdiomaIso to set
	 */
	public void setCodigoIdiomaIso(String codigoIdiomaIso) {
		this.codigoIdiomaIso = codigoIdiomaIso;
	}

	/**
	 * @return the siccClasificacion
	 */
	public LabelValue[] getSiccClasificacion() {
		return siccClasificacion;
	}

	/**
	 * @param siccClasificacion
	 *            the siccClasificacion to set
	 */
	public void setSiccClasificacion(LabelValue[] siccClasificacion) {
		this.siccClasificacion = siccClasificacion;
	}

	/**
	 * @return the mantenimiento
	 */
	public boolean isMantenimiento() {
		return mantenimiento;
	}

	/**
	 * @param mantenimiento
	 *            the mantenimiento to set
	 */
	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
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

	/**
	 * @return the mostrarBeneficios
	 */
	public Boolean getMostrarBeneficios() {
		return mostrarBeneficios;
	}

	/**
	 * @param mostrarBeneficios
	 *            the mostrarBeneficios to set
	 */
	public void setMostrarBeneficios(Boolean mostrarBeneficios) {
		this.mostrarBeneficios = mostrarBeneficios;
	}

	public LabelValue[] getNuevoSubTipoClienteList() {
		return nuevoSubTipoClienteList;
	}

	public void setNuevoSubTipoClienteList(LabelValue[] nuevoSubTipoClienteList) {
		this.nuevoSubTipoClienteList = nuevoSubTipoClienteList;
	}

	public LabelValue[] getNuevoTipoClasificacion() {
		return nuevoTipoClasificacion;
	}

	public void setNuevoTipoClasificacion(LabelValue[] nuevoTipoClasificacion) {
		this.nuevoTipoClasificacion = nuevoTipoClasificacion;
	}

	public LabelValue[] getNuevoClasificacion() {
		return nuevoClasificacion;
	}

	public void setNuevoClasificacion(LabelValue[] nuevoClasificacion) {
		this.nuevoClasificacion = nuevoClasificacion;
	}

	public LabelValue[] getNuevoZonaList() {
		return nuevoZonaList;
	}

	public void setNuevoZonaList(LabelValue[] nuevoZonaList) {
		this.nuevoZonaList = nuevoZonaList;
	}

	public List getNuevostoLevantamientoErroresClientesListPrinc() {
		return nuevostoLevantamientoErroresClientesListPrinc;
	}

	public void setNuevostoLevantamientoErroresClientesListPrinc(
			List nuevostoLevantamientoErroresClientesListPrinc) {
		this.nuevostoLevantamientoErroresClientesListPrinc = nuevostoLevantamientoErroresClientesListPrinc;
	}

	public List getNuevostoResumenClientesList() {
		return nuevostoResumenClientesList;
	}

	public void setNuevostoResumenClientesList(List nuevostoResumenClientesList) {
		this.nuevostoResumenClientesList = nuevostoResumenClientesList;
	}

	public Boolean getMostrarGrillaNueva() {
		return mostrarGrillaNueva;
	}

	public void setMostrarGrillaNueva(Boolean mostrarGrillaNueva) {
		this.mostrarGrillaNueva = mostrarGrillaNueva;
	}

	public Object[] getObjetoSeleccionados() {
		return objetoSeleccionados;
	}

	public void setObjetoSeleccionados(Object[] objetoSeleccionados) {
		this.objetoSeleccionados = objetoSeleccionados;
	}

	public String getAttachmentNuevo() {
		return attachmentNuevo;
	}

	public void setAttachmentNuevo(String attachmentNuevo) {
		this.attachmentNuevo = attachmentNuevo;
	}

	public Boolean getShowPorcentaje() {
		return showPorcentaje;
	}

	public void setShowPorcentaje(Boolean showPorcentaje) {
		this.showPorcentaje = showPorcentaje;
	}

	public String getMsjeSave() {
		return msjeSave;
	}

	public void setMsjeSave(String msjeSave) {
		this.msjeSave = msjeSave;
	}
	
}