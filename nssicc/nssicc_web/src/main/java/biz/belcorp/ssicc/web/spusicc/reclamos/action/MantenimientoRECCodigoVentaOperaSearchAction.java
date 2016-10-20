package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import it.businesslogic.ireport.gui.event.ValueChangedEvent;

import java.io.BufferedReader;
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

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CodigoVentaOpera;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECCodigoVentaOperaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXEstatusRecomendacionSearchForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXGruposRegionesForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECCodigoVentaOperaForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECCodigoVentaOperaSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ProcesoRECBloqueoCDRForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOBeneficioDeudaSearchForm;


@ManagedBean
@SessionScoped
public class MantenimientoRECCodigoVentaOperaSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 9215573594574369222L;
	
	private List siccOperacionesList;
	private List recTipoOfertaList;
	private List recCodigoCatalagoList;
	private List recCodigoVentaOperaList;
	private LabelValue[] siccTipoOperacionList;
	private String recCodigoVenta= Constants.REC_CODIGO_VENTA;
	private String recTipoIferta=Constants.REC_TIPO_OFERTA;
	private String recCodigoCatalogo=Constants.REC_CODIGO_CATALOGO;
	private String attachment = "";
	private List recCodigoVentaList;
	private String []modOperacion=null;
	private String[]modTipoOperacion=null;
	
	private List seleccionado = new ArrayList();

	@Override
	protected String getSalirForward() {		
		return "mantenimientoRECCodigoVentaOperaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoRECCodigoVentaOperaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECCodigoVentaOperaSearchForm searchForm = new MantenimientoRECCodigoVentaOperaSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSalirFindAfterAttributes()
	 */
	@Override
	public void setSalirFindAfterAttributes() throws Exception {
		MantenimientoRECCodigoVentaOperaSearchForm f = (MantenimientoRECCodigoVentaOperaSearchForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.setSiccTipoOperacionList(ajax.getTiposOperaMultipleByOpera(f.getCodigoPais(),new ArrayList() , "T"));
		this.seleccionado = new ArrayList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveFindAfterAttributes()
	 */
	@Override
	public void setSaveFindAfterAttributes() throws Exception {
		MantenimientoRECCodigoVentaOperaSearchForm f = (MantenimientoRECCodigoVentaOperaSearchForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.setSiccTipoOperacionList(ajax.getTiposOperaMultipleByOpera(f.getCodigoPais(),new ArrayList() , "T"));
		this.seleccionado = new ArrayList();
	}

	@Override
	protected List setFindAttributes() throws Exception {
	log.info("Entro a MantenimientoRECCodigoVentaOperaSearchAction - setFindAttributes");
		
		//-- Variables		
		MantenimientoRECCodigoVentaOperaSearchForm f = (MantenimientoRECCodigoVentaOperaSearchForm) this.formBusqueda;
		MantenimientoRECCodigoVentaOperaService service = (MantenimientoRECCodigoVentaOperaService)  getBean("spusicc.mantenimientoRECCodigoVentaOperaService");
		int v_operacion = 0;
		int v_tipoOperacion = 0;
		
		//-- Validar seleccionables Operacion y Tipo Operacion
		if(f.getCodigoOperacion() != null){
			if(f.getCodigoOperacion().length == 1 && !f.getCodigoOperacion()[0].equals("") )
				v_operacion = 1;
			else if(f.getCodigoOperacion().length > 1)
				v_operacion = f.getCodigoOperacion().length;
		}		
		if(f.getCodigoTipoOperacion() != null){
			if(f.getCodigoTipoOperacion().length == 1 && !f.getCodigoTipoOperacion()[0].equals("") )
				v_tipoOperacion = 1;
			else if(f.getCodigoTipoOperacion().length > 1)
				v_tipoOperacion = f.getCodigoTipoOperacion().length;
		}
		
		//-- Crear pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoOperacion", v_operacion==0?new ArrayList():Arrays.asList(f.getCodigoOperacion())  );
		criteria.put("codigoTipoOperacion", v_tipoOperacion==0?new ArrayList():Arrays.asList(f.getCodigoTipoOperacion()) ); 
		criteria.put("codigoPeriodoInicio", f.getCodigoPeriodoInicio());
		criteria.put("codigoPeriodoFinal", f.getCodigoPeriodoFin());
		
		if(f.getTipoBusqueda()!=null){
			criteria.put("tipoBusqueda", f.getTipoBusqueda());
			if(f.getTipoBusqueda().compareToIgnoreCase("0")==0){
				criteria.put("codigoVenta", f.getCodigoVenta());
				criteria.put("codigoCatalogo", "");
				criteria.put("codigoTipoOferta","");
			}
			if(f.getTipoBusqueda().compareToIgnoreCase("1")==0){
				criteria.put("codigoVenta", "");
				criteria.put("codigoCatalogo", "");
				criteria.put("codigoTipoOferta",f.getTipoOferta());
			}
			if(f.getTipoBusqueda().compareToIgnoreCase("2")==0){
				criteria.put("codigoVenta", "");
				criteria.put("codigoCatalogo", f.getCodigoCatalogo());
				criteria.put("codigoTipoOferta","");
			}
		}
		else
			criteria.put("tipoBusqueda","");		
		//-- Logica
		List resultado = (List)service.getCodigoVentaOperaList(criteria);	
		this.recCodigoVentaOperaList=resultado;	
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.info("Entro a MantenimientoRECCodigoVentaOperaSearchAction - setDeleteAttributes");
		
		//-- Variables
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECCodigoVentaOperaSearchForm f = (MantenimientoRECCodigoVentaOperaSearchForm) this.formBusqueda;
		MantenimientoRECCodigoVentaOperaService service = (MantenimientoRECCodigoVentaOperaService)getBean("spusicc.mantenimientoRECCodigoVentaOperaService");
		Map criteria = new HashMap();
				
		String[] items = new String[this.seleccionado.size()] ;
		
		
		for (int i = 0; i < this.seleccionado.size(); i++) {
			CodigoVentaOpera codigoVenta= (CodigoVentaOpera)this.seleccionado.get(i);
			String codigo = null;
			if(StringUtils.isBlank(codigoVenta.getCodigoVenta()))
				codigo =codigoVenta.getCodigoPeriodoInicio()+";"+codigoVenta.getCodigoOperacion()+";"+codigoVenta.getCodigoTipoOperacion();
			else
				codigo =codigoVenta.getCodigoPeriodoInicio()+";"+codigoVenta.getCodigoOperacion()+";"+codigoVenta.getCodigoTipoOperacion()+";"+codigoVenta.getCodigoVenta();
			
			items[i] = codigo;
		}		
		
		criteria.put("codigoPais", pais.getCodigo());		
		if (items.length>0){			
			try{				
				service.deleteCodigoVentaOpera(criteria,items);				
			}catch (Exception e) {
				String error = e.getMessage();							
				throw new Exception(this.getResourceMessage("errors.detail",new Object[]{error}));
			}		
		}
		return true;		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {		
		return "mantenimientoRECCodigoVentaOperaSearchForm.deleted";
	}

	@Override
	public String setValidarMantenimiento(){
		MantenimientoRECCodigoVentaOperaForm f = (MantenimientoRECCodigoVentaOperaForm) this.formMantenimiento;
		
		if(f.isNewRecord()){
			if(!f.getCodigoPeriodoFin().equals("")){
				if(f.getCodigoPeriodoFin().compareTo(f.getCodigoPeriodoInicio())<0){
					return this.getResourceMessage("El Periodo Inicial debe ser mayor o igual al Periodo Final");
				}
			}
			
			if(f.getCodigoVenta().equals("") && f.getCodigoVentaFile()==null){
				if(f.getTipoOferta()==null && f.getCodigoCatalogo()==null){
					return this.getResourceMessage("mantenimientoRECCodigoVentaOperaForm.msg.CodigoVenta");
				}
			}
			
			if(f.getCodigoOperacion() == null  || f.getCodigoOperacion().length == 0){
				return this.getResourceMessage("mantenimientoRECCodigoVentaOperaForm.msg.codigoOperacion");				
			}
			
		}else{
			if(f.getCodigoVenta().equals("")){
				return this.getResourceMessage("mantenimientoRECCodigoVentaOperaForm.msg.codigoVenta");
			}
		}		
		return null;		
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		
		MantenimientoRECCodigoVentaOperaForm f = (MantenimientoRECCodigoVentaOperaForm) this.formMantenimiento;
		MantenimientoRECCodigoVentaOperaService service = (MantenimientoRECCodigoVentaOperaService)  getBean("spusicc.mantenimientoRECCodigoVentaOperaService");	
		
		try {
			if(f.isNewRecord()){
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoTipoOperacion",f.getCodigoTipoOperacion()); 
				criteria.put("codigoVenta",f.getCodigoVenta());
				criteria.put("tipoOferta",f.getTipoOferta());
				criteria.put("codigoCatalogo",f.getCodigoCatalogo());
				criteria.put("codigoPeriodoInicio",f.getCodigoPeriodoInicio());
				criteria.put("codigoPeriodoFin",f.getCodigoPeriodoFin());			
				List listaCodigosVenta =this.recCodigoVentaList;
				criteria.put("listaCodigosVenta", listaCodigosVenta);				
				service.insertCodigoVentaOpera(criteria);				
			}
			else{
				try {
					String opera = f.getCodigoOperacion()[0];
					String tipoOpera=f.getCodigoTipoOperacion()[0];
					
					Map criteria = new HashMap();
					criteria.put("codigoPais", pais.getCodigo());
					criteria.put("codigoPeriodoInicio", f.getCodigoPeriodoInicio());
					criteria.put("codigoOperacion", opera);
					criteria.put("codigoTipoOperacion",tipoOpera);
					criteria.put("codigoVenta", f.getCodigoVentaModifica());
					
					criteria.put("codigoVentaModifica",f.getCodigoVenta());
					criteria.put("tipoOferta",f.getTipoOferta());
					criteria.put("codigoCatalogo",f.getCodigoCatalogo());
					criteria.put("codigoPeriodoFin",f.getCodigoPeriodoFin());

					service.updateCodigoVentaOpera(criteria);					
				} catch (Exception e) {
					this.addError("ERROR: ", e.getMessage());
					throw new Exception(e.getMessage());
				}				
			}	
			
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		
		f.setCodigoCatalogo("");
		f.setCodigoPeriodoFin("");
		f.setCodigoPeriodoInicio("");
		f.setCodigoVenta("");
		f.setTipoOferta("");
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECCodigoVentaOperaForm f = new MantenimientoRECCodigoVentaOperaForm();	
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoRECCodigoVentaOperaService service = (MantenimientoRECCodigoVentaOperaService)  getBean("spusicc.mantenimientoRECCodigoVentaOperaService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccOperacionesList=interfazSiCCService.getOperacionesByCodigoPais(criteriaOperacion);
		this.recTipoOfertaList=service.getTipoOfertaList(criteriaOperacion);
		this.recCodigoCatalagoList=service.getCodigoCatalogoList(criteriaOperacion);
		this.siccTipoOperacionList=null;
		this.recCodigoVentaList=null;	
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			
//			CodigoVentaOpera codigoVenta= (CodigoVentaOpera)this.beanRegistroSeleccionado;
			CodigoVentaOpera codigoVenta= (CodigoVentaOpera)this.seleccionado.get(0);
			String codigo =codigoVenta.getCodigoOperacion();
			String codigoPais = pais.getCodigo();
			
			if (codigo != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigo + " "+ codigoPais);
				}				
			
				List codigoVentaOperaList = this.recCodigoVentaOperaList;
				//CodigoVentaOpera codigoVenta= (CodigoVentaOpera)codigoVentaOperaList.get(Integer.parseInt(codigo)-1);
				String[] operacion = {codigoVenta.getCodigoOperacion()};
				String[] tipoOperacion = {codigoVenta.getCodigoTipoOperacion()};
				String[] moddescripcion={codigoVenta.getDescripcionOperacion()};
				String[] modTipo={codigoVenta.getCodigoOperacion()+"-"+codigoVenta.getCodigoTipoOperacion()};
				this.modOperacion=moddescripcion;
				this.modTipoOperacion=modTipo;				
				
				f.setCodigoCatalogo(codigoVenta.getCodigoCatalogo());
				f.setCodigoOperacion(operacion);
				f.setCodigoPeriodoFin(codigoVenta.getCodigoPeriodoFinal());
				f.setCodigoPeriodoInicio(codigoVenta.getCodigoPeriodoInicio());
				f.setCodigoTipoOperacion(tipoOperacion);
				f.setCodigoVenta(codigoVenta.getCodigoVenta());
				f.setTipoOferta(codigoVenta.getCodigoTipoOferta());
				f.setCodigoVentaModifica(codigoVenta.getCodigoVenta());
				f.setCodigoPais(codigoPais);
				f.setNewRecord(false);
				
			}
		}
		 return f;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECCodigoVentaOperaSearchForm f = (MantenimientoRECCodigoVentaOperaSearchForm) this.formBusqueda;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoRECCodigoVentaOperaService service = (MantenimientoRECCodigoVentaOperaService)  getBean("spusicc.mantenimientoRECCodigoVentaOperaService");
		
		//-- Inicializar campos
		f.setCodigoOperacion(null);
		f.setCodigoTipoOperacion(null);
		f.setCodigoVenta(null);
		f.setTipoOferta(null);
		f.setCodigoCatalogo(null);
		f.setCodigoPeriodoInicio(null);
		f.setCodigoPeriodoFin(null);
		f.setCodigoPais(pais.getCodigo());
		
		//-- Inicializar listados 
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccOperacionesList=interfazSiCCService.getOperacionesByCodigoPais(criteriaOperacion);
		this.recTipoOfertaList=service.getTipoOfertaList(criteriaOperacion);
		this.recCodigoCatalagoList=service.getCodigoCatalogoList(criteriaOperacion);	
		
		//-- Inicializar listado general
		Map criteria = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteria.put("codigoOperacion", new ArrayList());
		criteria.put("codigoTipoOperacion", new ArrayList()); 
		criteria.put("codigoPeriodoInicio", "");
		criteria.put("codigoPeriodoFinal", "");
		criteria.put("tipoBusqueda", "");

		List resultado = service.getCodigoVentaOperaList(criteria);
		this.recCodigoVentaOperaList=resultado;	
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.setSiccTipoOperacionList(ajax.getTiposOperaMultipleByOpera(f.getCodigoPais(),new ArrayList() , "T"));
		
		f.setLineaDefecto(Constants.REC_NUMERO_REGISTROS_DEFECTO_CONSULTA);
		f.setLineaMaxima(Constants.REC_NUMERO_REGISTROS_MAXIMO_CONSULTA);
		this.setFindAttributes();
		this.listaBusqueda = recCodigoVentaOperaList;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		
		this.salirGrabarPantallaPadre = true;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		
	}
	
	public void loadTipoOperacion(ValueChangeEvent val) {		
		MantenimientoRECCodigoVentaOperaSearchForm f = (MantenimientoRECCodigoVentaOperaSearchForm) this.formBusqueda;	
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ArrayList operaciones=new ArrayList();
		boolean todos=false;
		String[] valores = (String[]) val.getNewValue();
		for(int i=0;i<valores.length;i++){
			if(StringUtils.isBlank(valores[i])){
				todos=true;
				break;
			}			
		}
		
		if(todos){		
			operaciones.add("");	
		}
		else{
			for(int i=0;i<valores.length;i++)
				operaciones.add(valores[i]);
		}
	
		if(StringUtils.equals(this.accion, "NUEVO"))
			this.setSiccTipoOperacionList(ajax.getTiposOperaMultipleByOpera(f.getCodigoPais(), operaciones, ""));
		else
			this.setSiccTipoOperacionList(ajax.getTiposOperaMultipleByOpera(f.getCodigoPais(), operaciones, "T"));
			
		f.setCodigoTipoOperacion(null);			
	}
	
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoRECCodigoVentaOperaForm f = (MantenimientoRECCodigoVentaOperaForm) this.formMantenimiento;
		if (event != null) {
			f.setCodigoVentaFile(event.getFile());
			
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivo();
		}
	}
	
	public void uploadArchivo() throws Exception {			
		log.debug("MantenimientoRECCodigoVentaOperaAction - loadfile");			
				
		MantenimientoRECCodigoVentaOperaForm f = (MantenimientoRECCodigoVentaOperaForm)this.formMantenimiento;
				
		List listaCodigosVenta = new ArrayList();
		UploadedFile archivo =f.getCodigoVentaFile();				
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
				int cont = 0;
				while(true) {
					linea = br.readLine();
					if (linea == null)
						break;
					
					if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
						listaCodigosVenta.add(linea.trim());
					}
					else
					{
						cont++;
					}
				}
				
				if(cont > 0){
					this.addError("ERROR: ", this.getResourceMessage("mantenimientoRECCodigoVentaOperaForm.errors.datos.no.numericos")+cont);
					throw new Exception(this.getResourceMessage("mantenimientoRECCodigoVentaOperaForm.errors.datos.no.numericos"));							
				}
				this.recCodigoVentaList=listaCodigosVenta;				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#verificarRegistroSeleccionado()
	 */
	@Override
	protected boolean verificarRegistroSeleccionado() {		
		boolean verificar= true;
		
		try {
			if(this.seleccionado == null || this.seleccionado.size() == 0 ){
				verificar= false;
				this.addWarn("Warning: ",this.getResourceMessage("errors.select.item"));
				return verificar;
			}
			
			if(StringUtils.equals(this.accion, "MODIFICAR")){
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		
		if(StringUtils.equals(accion, "ELIMINAR")){
			if(this.seleccionado == null || this.seleccionado.size() == 0 ){				
				mensaje = this.getResourceMessage("errors.select.item");
				return mensaje;
			}
			
		}				
		return mensaje;
	}

	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	public List getRecTipoOfertaList() {
		return recTipoOfertaList;
	}

	public void setRecTipoOfertaList(List recTipoOfertaList) {
		this.recTipoOfertaList = recTipoOfertaList;
	}

	public List getRecCodigoCatalagoList() {
		return recCodigoCatalagoList;
	}

	public void setRecCodigoCatalagoList(List recCodigoCatalagoList) {
		this.recCodigoCatalagoList = recCodigoCatalagoList;
	}

	public List getRecCodigoVentaOperaList() {
		return recCodigoVentaOperaList;
	}

	public void setRecCodigoVentaOperaList(List recCodigoVentaOperaList) {
		this.recCodigoVentaOperaList = recCodigoVentaOperaList;
	}

	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	public String getRecCodigoVenta() {
		return recCodigoVenta;
	}

	public void setRecCodigoVenta(String recCodigoVenta) {
		this.recCodigoVenta = recCodigoVenta;
	}

	public String getRecTipoIferta() {
		return recTipoIferta;
	}

	public void setRecTipoIferta(String recTipoIferta) {
		this.recTipoIferta = recTipoIferta;
	}

	public String getRecCodigoCatalogo() {
		return recCodigoCatalogo;
	}

	public void setRecCodigoCatalogo(String recCodigoCatalogo) {
		this.recCodigoCatalogo = recCodigoCatalogo;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getRecCodigoVentaList() {
		return recCodigoVentaList;
	}

	public void setRecCodigoVentaList(List recCodigoVentaList) {
		this.recCodigoVentaList = recCodigoVentaList;
	}

	public String[] getModOperacion() {
		return modOperacion;
	}

	public void setModOperacion(String[] modOperacion) {
		this.modOperacion = modOperacion;
	}

	public String[] getModTipoOperacion() {
		return modTipoOperacion;
	}

	public void setModTipoOperacion(String[] modTipoOperacion) {
		this.modTipoOperacion = modTipoOperacion;
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
