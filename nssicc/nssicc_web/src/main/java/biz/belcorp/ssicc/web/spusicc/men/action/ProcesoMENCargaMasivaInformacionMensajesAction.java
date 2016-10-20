/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.men.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.swing.event.ChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.men.form.ProcesoMENCargaMasivaInformacionMensajesForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeForm;

/**
 * @author Sigcomt
 *
 */

@ManagedBean
@SessionScoped
public class ProcesoMENCargaMasivaInformacionMensajesAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -2088391003481350668L;
	
	
	private LabelValue []  siccRegionList = {};
	private LabelValue []  siccZonaList = {};
	private LabelValue []  siccRegionConfeList = {};
	private LabelValue []  siccZonaConfeList = {};
	private boolean boolDivCargaMasiva;
	private boolean boolDivRegion;
	private boolean boolDivZona;
	private boolean boolTrFichero;
	private DataTableModel dtFaltante;
	private DataTableModel dtConferencias;
	private Object beanFaltante;
	private Object beanConferencia;
	private boolean flagSearchBuscar;
	private String menCodigoZonaElegido;
	
	private List menTipoCargaList = new ArrayList();
	private List menHorasList = new ArrayList();
	private List menMinutosList = new ArrayList();
	private List menFaltanteAnunciadoList = new ArrayList();
	private boolean viewBuscar;
	
	
	private List menConferenciasList = new ArrayList();
	private boolean viewBuscarConferencias;
	
	private boolean viewValida;
	private List menArchivoList = new ArrayList();
	
	private boolean viewValidaConferencias;
	private List menArchivoConferenciasList = new ArrayList();
		
	private String attachmentForm = "";
	
	private String codigoCUVTexto;
	private String descripcionProductoTexto;
	private String precioTexto;
	private String paginaTexto;
	private String descripcionCatalogoTexto;
	

	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		this.boolDivCargaMasiva = false;
		this.boolDivRegion = false;
		this.boolDivZona = false;
		this.boolTrFichero = true;
		this.flagSearchBuscar = true;
		
		f.setCodigoZona("");
		f.setCargaMasiva(Constants.NUMERO_CERO);
		f.setFlagCargaMasiva(false);
		f.setCodigoPais(pais.getCodigo());
		
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");	
		this.siccRegionList = ajaxService.getRegionesByPais(pais.getCodigo());	
		f.setCodigoRegion(this.siccRegionList[0].getValue());
		this.siccZonaList=ajaxService.getZonasByPaisRegion(f.getCodigoPais(), "10");
		this.siccRegionConfeList = ajaxService.getRegionesByPais(pais.getCodigo());
		
		
		//seteamos la ruta temporal donde guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		String directorio = serviceUnidad.obtenerPathUpload(pais.getCodigo());
		f.setDirectorioTemporal(directorio);
		
		//Obtenemos informacion del usuario Logueado
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		/* obteniendo codigo de periodo actual */
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());	
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());	
		
		//Lista de Tipo de Carga
		String msjTipoCarga01 = "procesoMENCargaMasivaInformacionMensajesForm.tipoCarga01";
		String msjTipoCarga02 = "procesoMENCargaMasivaInformacionMensajesForm.tipoCarga02";
		List listaAcciones = new ArrayList();
		Base base = new Base();
		base.setCodigo("01");
		String proceso = this.getResourceMessage(msjTipoCarga01);
		base.setDescripcion(proceso);
		listaAcciones.add(base);
		base = new Base();
		base.setCodigo("02");
		proceso = this.getResourceMessage(msjTipoCarga02);
		base.setDescripcion(proceso);
		listaAcciones.add(base);
		this.menTipoCargaList = listaAcciones;
		f.setTipoCarga("01");
		
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		
		this.mostrarBotonBuscar = false;
		this.mostrarBotonExecute = false;
		
		this.viewBuscar = false;
		this.viewBuscarConferencias = false;
		this.viewValida = false;
		this.viewValidaConferencias = false;

		this.mostrarListaBusqueda= false; 
		this.mostrarPanelAdicionalProceso= false; 
//		
//		sesion.removeAttribute("listRegistros");
//		sesion.removeAttribute("viewValida");
//		sesion.removeAttribute("viewBuscar");
//		sesion.removeAttribute("viewValidaConferencias");
//		sesion.removeAttribute("viewBuscarConferencias");
//		sesion.removeAttribute(Constants.MEN_ARCHIVO_LIST);
//		sesion.removeAttribute(Constants.MEN_ARCHIVO_CONFERENCIAS_LIST);
			
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}
	
	public void buscarTablasCombos(ActionEvent actionEvent)  
	{
		
		try {			
			ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
			this.mostrarPanelAdicionalProceso= false; 
			String msj = null;
			int codigoPeriodo = Integer.parseInt(f.getCodigoPeriodo());
			int codigoPeriodoActual= Integer.parseInt(f.getCodigoPeriodoActual());
			if(	codigoPeriodo < codigoPeriodoActual){
				msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.codigoPeriodoMenor";
				this.addError("Error", this.getResourceMessage(msj));
				return;				
			}
			
			if(StringUtils.equals(f.getTipoCarga(), "02")){
				if(StringUtils.isBlank(f.getCodigoRegion())){
					msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.codigoRegionRequerido";
					this.addError("Error ", this.getResourceMessage(msj));
					return;
				}
				
			}
	
			
			//obtenemos el service
			ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
			Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			
			AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");	
			this.siccRegionConfeList = ajaxService.getRegionesByPais(pais.getCodigo());
			//session.setAttribute(Constants.SICC_ZONA_CONFE_LIST, new ArrayList());
			
			String[] stringHora = Constants.EDU_HORA_LIST;
			List listaHora = new ArrayList();
			for(int i=0; i < stringHora.length; i++) {
				Base base = new Base();
				base.setCodigo(stringHora[i]);
				base.setDescripcion(stringHora[i]);
				listaHora.add(base);
			}
			
			String[] stringMin = Constants.EDU_MINUTO_LIST;
			List listaMin = new ArrayList();
			for(int i=0; i < stringMin.length; i++) {
				Base base = new Base();
				base.setCodigo(stringMin[i]);
				base.setDescripcion(stringMin[i]);
				listaMin.add(base);
			}
			
			this.menHorasList = listaHora;
			this.menMinutosList = listaMin;
			Base hora= (Base) listaHora.get(0);
			Base minutos= (Base) listaMin.get(0);
			f.setHora(hora.getCodigo());
			f.setMinutos(minutos.getCodigo());
			f.setHorario("AM");			
			
			List list = new ArrayList();
			
			if(StringUtils.equals(f.getTipoCarga(), "01")){
				//enviando en session los parametros de mensaje			
				Map map = BeanUtils.describe(f);
				list = service.getDevuelveFaltanteAnunciado(map);
				f.setCodigoCUVIngreso(null);
				this.menFaltanteAnunciadoList = list;
				this.menConferenciasList = new ArrayList();
				this.dtFaltante = new DataTableModel(this.menFaltanteAnunciadoList);
				this.viewValida = false;
				this.viewValidaConferencias = false;
				this.viewBuscarConferencias = false;			
				this.viewBuscar = true;//flag para mostrar el resultado de la validacion	
				this.mostrarPanelAdicionalProceso= true; 
				codigoCUVTexto = "";
				descripcionProductoTexto = "";
				precioTexto = "";
				paginaTexto = "";
				descripcionCatalogoTexto = "";

			}else{
				Map map = BeanUtils.describe(f);
				this.menCodigoZonaElegido = f.getCodigoZona();
				if(StringUtils.equals(f.getCodigoZona(), Constants.TODAS))				
					f.setCodigoZona("");				
				else				
					f.setCodigoZona(f.getCodigoZona());
							
	        	map.put("codigoZona", f.getCodigoZona());
				list = service.getDevuelveConferencias(map);
				this.menConferenciasList = list;
				this.menFaltanteAnunciadoList = new ArrayList();
				this.dtConferencias = new DataTableModel(this.menConferenciasList);
				this.viewValida = false;
				this.viewValidaConferencias = false;						
				this.viewBuscar = false;
				this.viewBuscarConferencias = true;//flag para mostrar el resultado de la validacion
				this.mostrarPanelAdicionalProceso= true; 

			}
			if ((list == null) ||(list.size() == 0)){				
				msj = "errors.datos.fuentes.busqueda";
				this.addInfo("Información", this.getResourceMessage(msj));
			}
			
		} catch (Exception e) {			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
		
		
	}

	public void mostrarRegZon(ValueChangeEvent val)
	{
		if (log.isDebugEnabled()) {
			log.debug("mostrarRegZon");
		}
		log.debug(val.getNewValue().toString());
		String tipoCarga = val.getNewValue().toString();
		
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		this.viewBuscar= false;
		this.viewBuscarConferencias= false;
		if(tipoCarga.equals("02"))
		{
			this.boolDivCargaMasiva = true;
			if(this.menConferenciasList.size()> 0)			
				this.viewBuscarConferencias= true;
			
			
			if(this.menFaltanteAnunciadoList.size() > 0)			
				this.viewBuscar= false;
			

			if(f.isFlagCargaMasiva() == true){
				this.boolDivRegion = false;
				this.boolDivZona = false;
				this.boolTrFichero = true;
				this.flagSearchBuscar = false;
				f.setCargaMasiva(Constants.NUMERO_UNO);
				f.setFlagCargaMasiva(true);
				
				if(this.menConferenciasList.size() > 0)				
					this.viewBuscarConferencias= false;				

			}else{
				this.boolDivRegion = true;
				this.boolDivZona = true;
				this.boolTrFichero = false;
				this.flagSearchBuscar = true;
				f.setCargaMasiva(Constants.NUMERO_CERO);
				f.setFlagCargaMasiva(false);
				
				if(this.menConferenciasList.size() >0)				
					this.viewBuscarConferencias= true;
				
		}
		}else{
			this.boolDivCargaMasiva = false;
			this.boolTrFichero = true;
			this.flagSearchBuscar = true;
								
			if(this.menConferenciasList.size() > 0)			
				this.viewBuscarConferencias= false;
			
						
			if(this.menFaltanteAnunciadoList.size() > 0)			
				this.viewBuscar= true;		
			ocultarDivRegZon(tipoCarga);
		}		
	}
	
	public void ocultarDivRegZon(String tipoCarga){
		if(tipoCarga.equals("01")){
			this.boolDivRegion = false;
			this.boolDivZona = false;
		}		
	}
	
	public void mostrarFichero(ValueChangeEvent val){
		
		if (log.isDebugEnabled()) {
			log.debug("mostrarFichero");
		}
		
		boolean estado = (Boolean) val.getNewValue();
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		
		if(f.getTipoCarga().equals("02")){
			if(estado == true){
				this.boolDivRegion = false;
				this.boolDivZona = false;
				this.boolTrFichero = true;
				f.setCargaMasiva(Constants.NUMERO_UNO);
				f.setFlagCargaMasiva(true);
				this.flagSearchBuscar = false;

				if(this.menConferenciasList.size() > 0)				
					this.viewBuscarConferencias= false;		
				
		}else{
			
			f.setCargaMasiva(Constants.NUMERO_CERO);
			f.setFlagCargaMasiva(false);
			this.boolTrFichero = false;					
			mostrarRegZon();
			this.flagSearchBuscar = true;
				
			if(this.menConferenciasList.size() > 0)				
				this.viewBuscarConferencias= true;
				
		}
		}else{
			this.boolTrFichero = true;	
			this.flagSearchBuscar = true;
		}

	}
	
	public void mostrarRegZon(){
		if (log.isDebugEnabled()) {
			log.debug("mostrarRegZon");
		}
			
		this.viewBuscar= false;
		this.viewBuscarConferencias= false;
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		
		if(f.getTipoCarga().equals("02")){
			this.boolDivCargaMasiva = true;
			if(this.menConferenciasList.size()> 0)			
				this.viewBuscarConferencias= true;			
		
			if(this.menFaltanteAnunciadoList.size() > 0){
				this.viewBuscar= false;			

			if(f.isFlagCargaMasiva() == true){
				this.boolDivRegion = false;
				this.boolDivZona = false;
				this.boolTrFichero = true;
				this.flagSearchBuscar = false;
				f.setCargaMasiva(Constants.NUMERO_UNO);
				f.setFlagCargaMasiva(true);
				if(this.menConferenciasList.size()> 0)
					this.viewBuscarConferencias= false;				

			}else{
				this.boolDivRegion = true;
				this.boolDivZona = true;
				this.boolTrFichero = false;
				this.flagSearchBuscar = true;
				f.setCargaMasiva(Constants.NUMERO_CERO);
				f.setFlagCargaMasiva(false);
				if(this.menConferenciasList.size()> 0)				
					this.viewBuscarConferencias= true;		

		}
		}else{
			this.boolDivCargaMasiva = false;
			this.boolTrFichero = true;
			this.flagSearchBuscar = true;
			
			if(this.menConferenciasList.size()> 0)			
				this.viewBuscarConferencias= false;			

			if(this.menFaltanteAnunciadoList.size() > 0)			
				this.viewBuscar= true;
		
			ocultarDivRegZon(f.getTipoCarga());
		}
		}
		
	}

	
	public void loadZonas(ValueChangeEvent val){
		log.debug(">>loadZonas ");
		log.debug("val: " + val.getNewValue().toString());	
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		String region = val.getNewValue().toString();
		
		if (!val.equals(null) && region.length() > 0){
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList=ajax.getZonasByPaisRegion(f.getCodigoPais(), region);		
		}else		
			this.siccZonaList = null;		
	}
 
	public void loadZonasIngreso(ValueChangeEvent val){
		log.debug(">>loadZonasIngreso ");
		log.debug("val: " + val.getNewValue().toString());	
		String regionIngreso = val.getNewValue().toString();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if(!regionIngreso.equals(null)){
			if(StringUtils.isBlank(regionIngreso))			
				this.siccZonaConfeList = null;			
			else{
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.siccZonaConfeList = ajax.getZonasByPaisRegion(pais.getCodigo(), regionIngreso);
			}
		}
	}
	
	
	    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		
		if(accion.equals("INSERTAR_DATOS_CONFERENCIA") ){
			if(StringUtils.isBlank(f.getCodigoRegionIngreso()))
				return this.getResourceMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoRegionRequerido");
			
			if(StringUtils.isBlank(f.getCodigoZonaIngreso()))
				return this.getResourceMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoZonaRequerido");
			
			if(StringUtils.isBlank(DateUtil.convertDateToString(f.getFechaDate())))
				return this.getResourceMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.fecha");
			
			if(StringUtils.isBlank(f.getHora()))
				return this.getResourceMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.hora");
			
			if(StringUtils.isBlank(f.getLocal()))
				return this.getResourceMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.local");
			
			if(StringUtils.isBlank(f.getDireccion()))
				return this.getResourceMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.direccion");
		}
		
		if(accion.equals("ELIMINAR_DATOS_CONFERENCIA") ){
			if(this.beanConferencia==null)
				return this.getResourceMessage("errors.select.item");		
		}
		
		if(accion.equals("INSERTAR_DATOS_FALTANTE") ){			
			 if (StringUtils.isBlank(f.getCodigoCUV())) 
				 return this.getResourceMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVEnBlanco");		        		
		}
		
		if(accion.equals("ELIMINAR_DATOS_FALTANTE") ){
			if(this.beanFaltante==null)
				return this.getResourceMessage("errors.select.item");			
		}
		return null;				
	}
	
	public void conferenciasadd(ActionEvent actionEvent){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'conferenciasadd' method");
		}
		try{
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
			
			ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");	
			f.setFecha(DateUtil.convertDateToString(f.getFechaDate()));
			
			Map params = BeanUtils.describe(f);
			params.put("usuario", usuario);
			params.put("codigoUsuario", usuario.getLogin());		
			params.put("codigoRegion", f.getCodigoRegionIngreso());
			params.put("codigoZona", f.getCodigoZonaIngreso());
			//params.put("fecha", f.getFechaDate());
			
			Map zona = service.getGerenteZona(params);
			if(zona != null){
				params.put("codigoRegion", MapUtils.getString(zona, "codigoRegion"));
				params.put("codigoZona", MapUtils.getString(zona, "codigoZona"));
				params.put("gerente", MapUtils.getString(zona, "gerente"));
				
				String horaFinal = f.getHora()+":"+f.getMinutos()+" "+f.getHorario();
				params.put("hora", horaFinal);
				
			    service.insertConferencias(params);
			    
			    //enviamos el mensaje de satisfaccion
			    String mensaje = "procesoMENCargaMasivaInformacionMensajesForm.insert";
			    this.addInfo("Información",this.getResourceMessage(mensaje));
				f.setCodigoRegion(f.getCodigoRegionIngreso());
				//f.setCodigoZona(f.getCodigoZonaIngreso());
				f.setCodigoZona("");
				
				buscarTablasCombos(actionEvent);
			}else{
				
				String msj = "procesoMENCargaMasivaInformacionMensajesForm.sinGerenteZona";
				this.addError("Error", this.getResourceMessage(msj));				
				buscarTablasCombos(actionEvent);
			}
		
			f.setCodigoRegionIngreso("");
			f.setCodigoZonaIngreso(null);
			f.setFecha(null);
			f.setFechaDate(null);
			f.setHora(null);
			f.setMinutos(null);
			f.setHorario(null);
			f.setLocal(null);
			f.setDireccion(null);
		
		}catch (Exception e) {			
			String error = e.getMessage();
			if(StringUtils.isBlank(error))			
				error = e.getLocalizedMessage();			
			else			
				this.addError("Error",error);			
		}	
	}
	
	public void conferenciasdelete(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'conferenciasdelete' method");
		}
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		Map bean = (Map) this.beanConferencia;
		
		try{
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			String id=bean.get("correlativo").toString();
			log.debug("Row id Confernecias: " + id);
			int nuevoId = 0;
			if (id != null) {
				ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService) 
						getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");	
				List list = this.menConferenciasList;

				Map map = bean;
				map.put("codigoPais", pais.getCodigo());
				map.put("codigoUsuario", usuario.getLogin());
				map.put("codigoPeriodo", f.getCodigoPeriodo());
				
				service.deleteConferencias(map);
				
				//enviamos el mensaje de satisfaccion
				String mensaje = "procesoMENCargaMasivaInformacionMensajesForm.deleted";
				this.addInfo("Información", this.getResourceMessage(mensaje));

				this.buscarTablasCombos(event);	
			}
		}
		catch (Exception e){
			e.printStackTrace();
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			else this.addError("Error", error); 
		}
	
	}
	
	
	
	public void validaCodigoCUV(AjaxBehaviorEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validaCodigoCUV' method");
		}
		
		String valor = (String) ((UIOutput)e.getSource()).getValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		LabelValueCUV  data = new LabelValueCUV();
		if(valor != null)
		{
			int strLen = valor.length();
			int strMaxLen = Integer.parseInt(Constants.NUMERO_CINCO);
			if( strLen > 0){
				if(strLen == strMaxLen){	
					data = ajax.getValidarCodigoVenta(f.getCodigoPais(), f.getCodigoPeriodo(),valor);
					//error AjaxServiceImpl getValidarCodigoVenta		18024
					if(data.getError() == null){
						f.setCodigoCUV(data.getValue());
						f.setDescripcionProducto(data.getLabel());
						f.setDescripcionCatalogo(data.getCatalogo());
						f.setPrecio(data.getPrecio());
						f.setPagina(data.getPagina());
						
						codigoCUVTexto = data.getValue();
						descripcionProductoTexto = data.getLabel();
						precioTexto = data.getPrecio();
						paginaTexto = data.getPagina();
						descripcionCatalogoTexto = data.getCatalogo();									
					}else{						
						f.setCodigoCUV("");
						f.setDescripcionProducto("");
						f.setDescripcionCatalogo("");
						f.setPrecio("");
						f.setPagina("");
						
						codigoCUVTexto = "";
						descripcionProductoTexto = "";
						precioTexto = "";
						paginaTexto = "";
						descripcionCatalogoTexto = "";
						//no muestra el mensaje
						this.addError("Error",data.getError());
					
					}					
				}	
			}			
		}	
	}
	
	public void add(ActionEvent actionEvent) {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}		
		try {			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			log.debug("row codigoCUV "+ f.getCodigoCUV());
			ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
			Map params = BeanUtils.describe(f);
			params.put("usuario", usuario);
			params.put("codigoUsuario", usuario.getLogin());
			
			Map valida = service.getDevuelveFaltanteAnunciadoCodigoCUV(params);
			if (valida != null) {
				String codigoCUVValida = (String) valida.get("codigoCUV");
				if (StringUtils.isNotBlank(codigoCUVValida)) {
					String mensaje = "procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVRepetido";
					this.addError("Error", this.getResourceMessage(mensaje));
					buscarTablasCombos(actionEvent);
					return;
				}
			}
			
			service.insertFaltanteAnunciado(params);
			f.setCodigoCUVIngreso(null);
			
			//enviamos el mensaje de satisfaccion
			String msj = "procesoMENCargaMasivaInformacionMensajesForm.insert";
			this.addInfo("Información", this.getResourceMessage(msj));
			buscarTablasCombos(actionEvent);		
		}
		catch (Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error))error = e.getLocalizedMessage();
			this.addError("", this.getResourceMessage("errors.detail", new Object[]{error}));
		}
		
	}	
	
	public void delete (ActionEvent actionEvent){		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		Map bean = (Map) this.beanFaltante;		
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			String id=bean.get("codigoCUV").toString();
			log.debug("Row id Confernecias: " + id);
			int nuevoId = 0;			
			
			if (id != null) {
				ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");	
				List list=this.menFaltanteAnunciadoList;	
				
				Map map = bean;
				map.put("codigoPais", pais.getCodigo());
				service.deleteFaltanteAnunciado(map);
				
				//enviamos el mensaje de satisfaccion
				String mensaje = "procesoMENCargaMasivaInformacionMensajesForm.deleted";
				this.addInfo("Información", this.getResourceMessage(mensaje));
				this.buscarTablasCombos(actionEvent);
			}			
		}
		catch (Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("", this.getResourceMessage("errors.detail", new Object[]{error}));			
		}
		
	}
	
	public void handleFileUploadForm(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {			
			ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
			if (event != null) {
				int codigoPeriodo = Integer.parseInt(f.getCodigoPeriodo());
				int codigoPeriodoActual = Integer.parseInt(f.getCodigoPeriodoActual());
				if(codigoPeriodo < codigoPeriodoActual ){
					String mensaje = "procesoMENCargaMasivaInformacionMensajesForm.msg.codigoPeriodoMenor";
					this.addError("Error", this.getResourceMessage(mensaje));
					return;
				}				
				f.setFile(event.getFile());
				f.setNombreArchivo(event.getFile().getFileName());
				this.setAttachmentForm(event.getFile().getFileName());
				if(StringUtils.equals(f.getTipoCarga(), "01"))
					this.cargar();				
				else							
					this.conferenciasCargar();			
			}
			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void cargar () throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		f.setCodigoCUVIngreso(null);
		this.mostrarPanelAdicionalProceso= true; 
		//obtenemos el service
		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
		
		// Cargamos el archivo de la maquina del cliente al servidor
		this.uploadArchivo(f.getFile().getFileName(), f.getFile().getInputstream());
		
		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getFile().getFileName());
		f.setExtensionArchivo(extensionArchivo);
		
		Map criteria = new HashMap();
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("usuario", usuario);
		
		//Cargamos el archivo excel 
		Map resultados = service.cargarArchivoExcel(criteria);
		List listRegistros= (List)resultados.get("listRegistros");
		f.setNumRegistros((String)resultados.get("totalRegistros"));
		f.setNumRegistrosError(Constants.NUMERO_CERO);
		f.setNumRegistrosValido(Constants.NUMERO_CERO);
		String archivoConRegistros = (String)resultados.get("archivoConRegistros");
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		
		if (Constants.NO.equals(archivoConRegistros)) {
			String msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.archivoSinRegistros";
			this.addError("Error", this.getResourceMessage(msj));
			//return mapping.findForward(getViewForward());
			return;
		}
		
		//recuperamos los registros cargados del Excel
		MantenimientoLETLideresService letService = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");	
		Map criteriaOid = new HashMap();
        criteriaOid.put("codigoPais", f.getCodigoPais());
        criteriaOid.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteriaOid.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        		
		Map params = new HashMap();
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("usuario", usuario);
		params.put("listRegistros",listRegistros);
		params.put("numeroCampanna", 1);
		params.put("oidPais", letService.getOidPaisByCodigoPaisLET(criteriaOid));
		
		String codigoPeriodoSiguiente =  service.getDevuelveCodigoCampanha(params);
		params.put("codigoPeriodoSiguiente", codigoPeriodoSiguiente);

		//validamos los datos cargados del archivo excel
		listRegistros = new ArrayList();
		listRegistros = service.executeValidarCargaMasiva(params);
		
		int totalErrores = 0;
		for(int i=0; i<listRegistros.size(); i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			if(mapRegistro.get("descripcionError")!=null) {
				totalErrores++;
			}
		}				
		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));
		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);
		f.setCodigoPeriodoCargaMasiva(f.getCodigoPeriodo());

		this.menArchivoList = listRegistros;
		this.viewBuscar = false;
		this.viewBuscarConferencias = false;
		this.viewValida = true;
		this.viewValidaConferencias = false;		
	}
	
	public  void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}
	
	public String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		String resultado[] = nombreArchivo.split("\\.(?=[^\\.]+$)");
		return resultado[1];
	}
		
	public void uploadArchivo(String fileName, InputStream in) throws Exception {		
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		f.setNombreArchivo(fileName);
		
	    // Escribe el contenido de un archivo de entrada a un FileOutputStream de salida
	    OutputStream out = new FileOutputStream(new File(f.getDirectorioTemporal()+"/" + fileName));
	  
	    int read = 0;
	    byte[] bytes = new byte[1024];	  
	    while ((read = in.read(bytes)) != -1) 
	    	out.write(bytes, 0, read);	    
	  
	    in.close();
	    out.flush();
	    out.close();   
	}
	
	public void conferenciasCargar () throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'conferenciascargar' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		this.mostrarPanelAdicionalProceso= true; 
		f.setCodigoCUVIngreso(null);
		
		//obtenemos el service
		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
		// Cargamos el archivo de la maquina del cliente al servidor
		this.uploadArchivo(f.getFile().getFileName(), f.getFile().getInputstream());

		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
	
		Map criteria = new HashMap();
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("usuario", usuario);

		//Cargamos el archivo excel 
		Map resultados = service.cargarArchivoExcelConferencias(criteria);
		List listRegistros= (List)resultados.get("listRegistros");
		f.setNumRegistros((String)resultados.get("totalRegistros"));
		f.setNumRegistrosError(Constants.NUMERO_CERO);
		f.setNumRegistrosValido(Constants.NUMERO_CERO);
		String archivoConRegistros = (String)resultados.get("archivoConRegistros");
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		
		if (Constants.NO.equals(archivoConRegistros)) {
			String msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.archivoSinRegistrosConferencias";
			this.addError("Error", this.getResourceMessage(msj));
			return;
		}
		
		//recuperamos los registros cargados del Excel
		MantenimientoLETLideresService letService = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");	
		Map criteriaOid = new HashMap();
        criteriaOid.put("codigoPais", f.getCodigoPais());
        criteriaOid.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteriaOid.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        		
		Map params = new HashMap();
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("usuario", usuario);
		params.put("listRegistros",listRegistros);
		params.put("numeroCampanna", 1);
		params.put("oidPais", letService.getOidPaisByCodigoPaisLET(criteriaOid));
		
		String codigoPeriodoSiguiente =  service.getDevuelveCodigoCampanha(params);
		params.put("codigoPeriodoSiguiente", codigoPeriodoSiguiente);

		//validamos los datos cargados del archivo excel
		listRegistros = new ArrayList();
		listRegistros = service.executeValidarCargaMasivaConferencias(params);
		
		int totalErrores = 0;
		for(int i=0; i<listRegistros.size(); i++) {
			Map mapRegistro = (Map)listRegistros.get(i);
			if(mapRegistro.get("descripcionError")!=null) {
				totalErrores++;
			}
		}				
		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));
		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);
		f.setCodigoPeriodoCargaMasiva(f.getCodigoPeriodo());

		this.menArchivoConferenciasList = listRegistros;
		this.viewBuscar = false;
		this.viewBuscarConferencias = false;
		this.viewValida = false;
		this.viewValidaConferencias = true;	
	}
	
	
	public void grabarMasiva (ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'grabarMasiva' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		String msj;
		//obtenemos el service
		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService)getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
		List listRegistros= this.menArchivoList;
		
		//obteniendo parametro pais para el tema de cantidad maxima a grabar de registros
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(f.getCodigoPais());
		parametroPais.setCodigoSistema(Constants.MSG_CODIGO_SISTEMA); 
		parametroPais.setCodigoParametro(Constants.MSG_CODIGO_PARAMETRO_TIPO_CARGA_FALTANTE_ANUNCIADO);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		if (lstParametros == null || lstParametros.size() == 0) {			
			msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.faltaParametriaPais";
			this.addError("Error", this.getResourceMessage(msj));				
			this.viewBuscar = false;
			return;
		}
		
		ParametroPais parametro = null;
		if(CollectionUtils.size(lstParametros)==1)
			parametro = (ParametroPais) lstParametros.get(0);
			
		//recuperamos los registros cargados del Excel
		Map params = new HashMap();
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoPeriodo",f.getCodigoPeriodoCargaMasiva());
		params.put("usuario", usuario);
		params.put("listRegistros",listRegistros);
		params.put("registrosGrabar", parametro.getValorParametro());
		params.put("registrosValidos", f.getNumRegistrosValido());
		
		if (StringUtils.isBlank(parametro.getValorParametro()) || parametro.getValorParametro().equals("0")) 
        	params.put("registrosGrabar", f.getNumRegistrosValido());		
		
		try{
			service.deleteCargaMasivaFaltanteAnunciado(params);
			service.executeActualizarCargaMasiva(params);	
			Boolean validarEsMenosMaximoRegistrosOK = (Boolean) params.get("validarEsMenosMaximoRegistrosOK");
			if (validarEsMenosMaximoRegistrosOK) 
				msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.grabarCargaMasiva.updated";
			else
				msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.grabarCargaMasiva.updated2" + parametro.getValorParametro();
			
			this.addInfo("", this.getResourceMessage(msj));
		}
		catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error", this.getResourceMessage("errors.detail"+ error));
		}		
		this.viewBuscar = false;			
	}

	public void conferenciasgrabarMasiva(ActionEvent event) {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'conferenciasgrabarMasiva' method");
		}
		String msj;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoMENCargaMasivaInformacionMensajesForm f = (ProcesoMENCargaMasivaInformacionMensajesForm) this.formProceso;
		
		//obtenemos el service
		ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService) 
												getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
		
		List listRegistros= this.menArchivoConferenciasList;
					
		//recuperamos los registros cargados del Excel
		Map params = new HashMap();
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoPeriodo",f.getCodigoPeriodoCargaMasiva());
		params.put("usuario", usuario);
		params.put("listRegistros",listRegistros);
		//params.put("registrosGrabar", parametro.getValorParametro());
		params.put("registrosValidos", f.getNumRegistrosValido());		
		try{
			for(int i = 0; i < listRegistros.size(); i ++) {
				Map mapRegistro = (Map)listRegistros.get(i);
				String codigoRegion = (String)mapRegistro.get("codigoRegion");
				String descripcionError = (String)mapRegistro.get("descripcionError");
				params.put("codigoRegion", codigoRegion);				
				if(StringUtils.isBlank(descripcionError))
					service.deleteCargaMasivaConferencias(params);				
			}
			
			service.executeActualizarCargaMasivaConferencias(params);	
			msj = "procesoMENCargaMasivaInformacionMensajesForm.msg.grabarCargaMasivaConferencias.updated";
			this.addInfo("", this.getResourceMessage(msj));
		}
		catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error", this.getResourceMessage("errors.detail"+error));
		}		
		this.viewBuscarConferencias = false;
		this.viewValidaConferencias = false;		
	}
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMENCargaMasivaInformacionMensajesForm form = new ProcesoMENCargaMasivaInformacionMensajesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		return null;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccRegionConfeList
	 */
	public LabelValue[] getSiccRegionConfeList() {
		return siccRegionConfeList;
	}

	/**
	 * @param siccRegionConfeList the siccRegionConfeList to set
	 */
	public void setSiccRegionConfeList(LabelValue[] siccRegionConfeList) {
		this.siccRegionConfeList = siccRegionConfeList;
	}

	/**
	 * @return the siccZonaConfeList
	 */
	public LabelValue[] getSiccZonaConfeList() {
		return siccZonaConfeList;
	}

	/**
	 * @param siccZonaConfeList the siccZonaConfeList to set
	 */
	public void setSiccZonaConfeList(LabelValue[] siccZonaConfeList) {
		this.siccZonaConfeList = siccZonaConfeList;
	}

	/**
	 * @return the menTipoCargaList
	 */
	public List getMenTipoCargaList() {
		return menTipoCargaList;
	}

	/**
	 * @param menTipoCargaList the menTipoCargaList to set
	 */
	public void setMenTipoCargaList(List menTipoCargaList) {
		this.menTipoCargaList = menTipoCargaList;
	}




	/**
	 * @return the boolDivCargaMasiva
	 */
	public boolean isBoolDivCargaMasiva() {
		return boolDivCargaMasiva;
	}




	/**
	 * @param boolDivCargaMasiva the boolDivCargaMasiva to set
	 */
	public void setBoolDivCargaMasiva(boolean boolDivCargaMasiva) {
		this.boolDivCargaMasiva = boolDivCargaMasiva;
	}




	/**
	 * @return the boolDivRegion
	 */
	public boolean isBoolDivRegion() {
		return boolDivRegion;
	}




	/**
	 * @param boolDivRegion the boolDivRegion to set
	 */
	public void setBoolDivRegion(boolean boolDivRegion) {
		this.boolDivRegion = boolDivRegion;
	}




	/**
	 * @return the boolDivZona
	 */
	public boolean isBoolDivZona() {
		return boolDivZona;
	}




	/**
	 * @param boolDivZona the boolDivZona to set
	 */
	public void setBoolDivZona(boolean boolDivZona) {
		this.boolDivZona = boolDivZona;
	}




	/**
	 * @return the boolTrFichero
	 */
	public boolean isBoolTrFichero() {
		return boolTrFichero;
	}




	/**
	 * @param boolTrFichero the boolTrFichero to set
	 */
	public void setBoolTrFichero(boolean boolTrFichero) {
		this.boolTrFichero = boolTrFichero;
	}




	/**
	 * @return the menHorasList
	 */
	public List getMenHorasList() {
		return menHorasList;
	}




	/**
	 * @param menHorasList the menHorasList to set
	 */
	public void setMenHorasList(List menHorasList) {
		this.menHorasList = menHorasList;
	}




	/**
	 * @return the menMinutosList
	 */
	public List getMenMinutosList() {
		return menMinutosList;
	}




	/**
	 * @param menMinutosList the menMinutosList to set
	 */
	public void setMenMinutosList(List menMinutosList) {
		this.menMinutosList = menMinutosList;
	}




	/**
	 * @return the menFaltanteAnunciadoList
	 */
	public List getMenFaltanteAnunciadoList() {
		return menFaltanteAnunciadoList;
	}




	/**
	 * @param menFaltanteAnunciadoList the menFaltanteAnunciadoList to set
	 */
	public void setMenFaltanteAnunciadoList(List menFaltanteAnunciadoList) {
		this.menFaltanteAnunciadoList = menFaltanteAnunciadoList;
	}




	/**
	 * @return the viewBuscar
	 */
	public boolean isViewBuscar() {
		return viewBuscar;
	}




	/**
	 * @param viewBuscar the viewBuscar to set
	 */
	public void setViewBuscar(boolean viewBuscar) {
		this.viewBuscar = viewBuscar;
	}




	/**
	 * @return the menConferenciasList
	 */
	public List getMenConferenciasList() {
		return menConferenciasList;
	}




	/**
	 * @param menConferenciasList the menConferenciasList to set
	 */
	public void setMenConferenciasList(List menConferenciasList) {
		this.menConferenciasList = menConferenciasList;
	}




	/**
	 * @return the viewBuscarConferencias
	 */
	public boolean isViewBuscarConferencias() {
		return viewBuscarConferencias;
	}




	/**
	 * @param viewBuscarConferencias the viewBuscarConferencias to set
	 */
	public void setViewBuscarConferencias(boolean viewBuscarConferencias) {
		this.viewBuscarConferencias = viewBuscarConferencias;
	}




	/**
	 * @return the viewValida
	 */
	public boolean isViewValida() {
		return viewValida;
	}




	/**
	 * @param viewValida the viewValida to set
	 */
	public void setViewValida(boolean viewValida) {
		this.viewValida = viewValida;
	}




	/**
	 * @return the menArchivoList
	 */
	public List getMenArchivoList() {
		return menArchivoList;
	}




	/**
	 * @param menArchivoList the menArchivoList to set
	 */
	public void setMenArchivoList(List menArchivoList) {
		this.menArchivoList = menArchivoList;
	}




	/**
	 * @return the viewValidaConferencias
	 */
	public boolean isViewValidaConferencias() {
		return viewValidaConferencias;
	}




	/**
	 * @param viewValidaConferencias the viewValidaConferencias to set
	 */
	public void setViewValidaConferencias(boolean viewValidaConferencias) {
		this.viewValidaConferencias = viewValidaConferencias;
	}




	/**
	 * @return the menArchivoConferenciasList
	 */
	public List getMenArchivoConferenciasList() {
		return menArchivoConferenciasList;
	}




	/**
	 * @param menArchivoConferenciasList the menArchivoConferenciasList to set
	 */
	public void setMenArchivoConferenciasList(List menArchivoConferenciasList) {
		this.menArchivoConferenciasList = menArchivoConferenciasList;
	}




	/**
	 * @return the attachmentForm
	 */
	public String getAttachmentForm() {
		return attachmentForm;
	}




	/**
	 * @param attachmentForm the attachmentForm to set
	 */
	public void setAttachmentForm(String attachmentForm) {
		this.attachmentForm = attachmentForm;
	}

	/**
	 * @return the dtFaltante
	 */
	public DataTableModel getDtFaltante() {
		return dtFaltante;
	}

	/**
	 * @param dtFaltante the dtFaltante to set
	 */
	public void setDtFaltante(DataTableModel dtFaltante) {
		this.dtFaltante = dtFaltante;
	}

	/**
	 * @return the dtConferencias
	 */
	public DataTableModel getDtConferencias() {
		return dtConferencias;
	}

	/**
	 * @param dtConferencias the dtConferencias to set
	 */
	public void setDtConferencias(DataTableModel dtConferencias) {
		this.dtConferencias = dtConferencias;
	}

	/**
	 * @return the beanFaltante
	 */
	public Object getBeanFaltante() {
		return beanFaltante;
	}

	/**
	 * @param beanFaltante the beanFaltante to set
	 */
	public void setBeanFaltante(Object beanFaltante) {
		this.beanFaltante = beanFaltante;
	}

	/**
	 * @return the beanConferencia
	 */
	public Object getBeanConferencia() {
		return beanConferencia;
	}

	/**
	 * @param beanConferencia the beanConferencia to set
	 */
	public void setBeanConferencia(Object beanConferencia) {
		this.beanConferencia = beanConferencia;
	}

	/**
	 * @return the flagSearchBuscar
	 */
	public boolean isFlagSearchBuscar() {
		return flagSearchBuscar;
	}

	/**
	 * @param flagSearchBuscar the flagSearchBuscar to set
	 */
	public void setFlagSearchBuscar(boolean flagSearchBuscar) {
		this.flagSearchBuscar = flagSearchBuscar;
	}

	/**
	 * @return the menCodigoZonaElegido
	 */
	public String getMenCodigoZonaElegido() {
		return menCodigoZonaElegido;
	}

	/**
	 * @param menCodigoZonaElegido the menCodigoZonaElegido to set
	 */
	public void setMenCodigoZonaElegido(String menCodigoZonaElegido) {
		this.menCodigoZonaElegido = menCodigoZonaElegido;
	}


	/**
	 * @return the codigoCUVTexto
	 */
	public String getCodigoCUVTexto() {
		return codigoCUVTexto;
	}


	/**
	 * @param codigoCUVTexto the codigoCUVTexto to set
	 */
	public void setCodigoCUVTexto(String codigoCUVTexto) {
		this.codigoCUVTexto = codigoCUVTexto;
	}


	/**
	 * @return the descripcionProductoTexto
	 */
	public String getDescripcionProductoTexto() {
		return descripcionProductoTexto;
	}


	/**
	 * @param descripcionProductoTexto the descripcionProductoTexto to set
	 */
	public void setDescripcionProductoTexto(String descripcionProductoTexto) {
		this.descripcionProductoTexto = descripcionProductoTexto;
	}


	/**
	 * @return the precioTexto
	 */
	public String getPrecioTexto() {
		return precioTexto;
	}


	/**
	 * @param precioTexto the precioTexto to set
	 */
	public void setPrecioTexto(String precioTexto) {
		this.precioTexto = precioTexto;
	}


	/**
	 * @return the paginaTexto
	 */
	public String getPaginaTexto() {
		return paginaTexto;
	}


	/**
	 * @param paginaTexto the paginaTexto to set
	 */
	public void setPaginaTexto(String paginaTexto) {
		this.paginaTexto = paginaTexto;
	}


	/**
	 * @return the descripcionCatalogoTexto
	 */
	public String getDescripcionCatalogoTexto() {
		return descripcionCatalogoTexto;
	}


	/**
	 * @param descripcionCatalogoTexto the descripcionCatalogoTexto to set
	 */
	public void setDescripcionCatalogoTexto(String descripcionCatalogoTexto) {
		this.descripcionCatalogoTexto = descripcionCatalogoTexto;
	}
	
}
