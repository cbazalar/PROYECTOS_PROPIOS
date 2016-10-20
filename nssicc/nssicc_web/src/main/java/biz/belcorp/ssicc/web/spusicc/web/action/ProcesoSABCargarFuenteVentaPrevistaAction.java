package biz.belcorp.ssicc.web.spusicc.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.FuenteVentasService;
import biz.belcorp.ssicc.service.bas.MantenimientoBASParametroPaisService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.web.form.ProcesoSABCargarFuenteVentaPrevistaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoSABCargarFuenteVentaPrevistaAction extends BaseCargaArchivoAbstractAction{
	
	private static final long serialVersionUID = -5885367159092053153L;
	
	private List sabCargaFvpErroresLIst = new ArrayList() ;
	private List sabCargaFvpCalcularList = new ArrayList() ;
	private FileUploadEvent eventCampanha = null;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}	
		
		ProcesoSABCargarFuenteVentaPrevistaForm f = (ProcesoSABCargarFuenteVentaPrevistaForm)this.formCargaArchivo;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setFlagUpload(true);
		f.setFlagValidar(false);
		f.setFlagCalcular(false);
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
		f.setFlagMostrarCalculo(false);
		f.setFlagMostrarMensajeCamp(false);
		f.setCodigoPeriodoInicio("");
		f.setCodigoPeriodoFin("");
		f.setIndPeriYaCargados("0");
		
		this.sabCargaFvpErroresLIst = new ArrayList();
		this.sabCargaFvpCalcularList = new ArrayList();		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#devuelveFormCarga()
	 */
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoSABCargarFuenteVentaPrevistaForm form = new ProcesoSABCargarFuenteVentaPrevistaForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setUploadAttibuttes(java.util.Map)
	 */
	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'upload' method");
		}
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		MantenimientoBASParametroPaisService serviceBaspp = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
		BASParametroPais baspp = new BASParametroPais();
		baspp.setCodigoPais(pais.getCodigo());
		baspp.setCodigoSistema(Constants.FVP_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.FVP_CODIGO_PARAMETRO_IND_TIPO_CARGA_FUENTE_VENTAS);
		List list1 = serviceBaspp.getParametroPais(baspp);
		String indTipoFvp = null;
		
		if(list1 != null && list1.size() > 0){
			BASParametroPais baspp1 = (BASParametroPais) list1.get(0);
			indTipoFvp = baspp1.getValorParametro();
		}
		
		FuenteVentasService service = (FuenteVentasService) getBean("sisicc.fuenteVentasService");
		
		ProcesoSABCargarFuenteVentaPrevistaForm f = (ProcesoSABCargarFuenteVentaPrevistaForm)this.formCargaArchivo;
		String directorio = service.obtenerPathUpload(pais.getCodigo()) + "/";
		f.setDirectorioTemporal(directorio);
		f.setFlagCalcular(false);
		
		//Borramos las tablas respecto a Carga Fuente Venta Prevista
		service.deleteTablasCargaFuenteVentaPrevista();
		
		f.setFlagValidar(true);
		f.setFlagUpload(false);
		
	
         if (f.getIndPeriYaCargados().equals("1")){
			List periodos = new ArrayList();
			Map datos = new HashMap();
			
			String strMessage = null;
			
			datos.put("directorioTemporal", f.getDirectorioTemporal());
			datos.put("nombreArchivo", f.getNombreArchivo());
			datos.put("indTipoFvp", indTipoFvp);
			
			periodos = service.getPeriodosYaCargados(datos);
			
			if (periodos.size() > 0){			
				f.setFlagValidar(false);
				f.setFlagUpload(true);
				f.setNombreArchivo("");
				f.setCodigoPeriodoFin("");
				f.setCodigoPeriodoInicio("");
				borrarFichero(f.getDirectorioTemporal(),f.getNombreArchivo());
				strMessage = "procesoSABCargarFuenteVentaPrevistaUpload.msg.periodosYaCargados.error";				
				this.addInfo("Información: ", this.getResourceMessage(strMessage));				
				}
			}		
         return f;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#upload(org.primefaces.event.FileUploadEvent)
	 */
	public void upload(FileUploadEvent event) {
		ProcesoSABCargarFuenteVentaPrevistaForm f = (ProcesoSABCargarFuenteVentaPrevistaForm) this.formCargaArchivo;	

		if(StringUtils.isBlank(f.getCodigoPeriodoInicio())){
			this.addError("Error", this.getResourceMessage("procesoSABCargarFuenteVentaPrevistaUpload.periodoinicio.requerido"));			
			return;
		}
		
		if(StringUtils.isBlank(f.getCodigoPeriodoFin())){
			this.addError("Error", this.getResourceMessage("procesoSABCargarFuenteVentaPrevistaUpload.periodofin.requerido"));
			return;
		}
				
		Integer codigoInicio = Integer.parseInt(f.getCodigoPeriodoInicio());
		Integer codigoFin = Integer.parseInt(f.getCodigoPeriodoFin());
		
		if( codigoInicio > codigoFin )
		{
			this.addError("Error", this.getResourceMessage("procesoSABCargarFuenteVentaPrevistaUpload.periodoinicio.periodofin.menorigual"));			
		}
		else
		{
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String campCerrada= ajax.getCampCerrada(f.getCodigoPais(), f.getCodigoPeriodoInicio(),f.getCodigoPeriodoFin(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);

			if(campCerrada.equals(Constants.UNO) )
			{				
				String ventana = "confirmDialogCamp";
				String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmar').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				this.eventCampanha = event;
				return;		
			}
			else
			{
				this.eventCampanha = null;
				super.upload(event);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setValidarAttributes(java.util.Map)
	 */
	@Override
	protected List setValidarAttributes(Map<String, Object> datos)
			throws Exception { 
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}			
		
		MantenimientoBASParametroPaisService serviceBaspp = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoSABCargarFuenteVentaPrevistaForm f = (ProcesoSABCargarFuenteVentaPrevistaForm) this.formCargaArchivo;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				
		String strMessage = null;
		
		BASParametroPais baspp = new BASParametroPais();
		baspp.setCodigoPais(pais.getCodigo());
		baspp.setCodigoSistema(Constants.FVP_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.FVP_CODIGO_PARAMETRO_IND_TIPO_CARGA_FUENTE_VENTAS);
		List list1 = serviceBaspp.getParametroPais(baspp);
		String indTipoFvp = null;
		
		if(list1 != null && list1.size() > 0){
			BASParametroPais baspp1 = (BASParametroPais) list1.get(0);
			indTipoFvp = baspp1.getValorParametro();
		}
	
			
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
						
		datos.put("directorioTemporal", f.getDirectorioTemporal());
		datos.put("nombreArchivo", f.getNombreArchivo());
		datos.put("extensionArchivo", f.getExtensionArchivo());
		datos.put("codigoPais", pais.getCodigo());
		datos.put("oidCodPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));
		datos.put("codPeriIni",f.getCodigoPeriodoInicio());
		datos.put("codPeriFin",f.getCodigoPeriodoFin());
		datos.put("indTipoFvp",indTipoFvp);
		
		log.debug("datos__"+datos.toString());
		
		FuenteVentasService service = (FuenteVentasService) getBean("sisicc.fuenteVentasService");
		service.executeValidarCargaFuenteVentaPrevista(datos);		
		
		String error = (String) datos.get("error");
		
		if (error.equals("0")){		
			this.mostrarListaCarga = false;
			this.errorValidar = false;
			f.setFlagCalcular(true);
			f.setFlagValidar(false);
			f.setFlagProcesar(false);
			strMessage = "procesoSABCargarFuenteVentaPrevistaUpload.msg.CargaDatos.ok";
			this.addInfo("Información: ", this.getResourceMessage(strMessage));
		}
		else{		
			this.mostrarListaCarga = true;
			this.errorValidar = true;
			f.setFlagMostrarErrores(true);
			List list = service.getErroresCargaFuenteVentaPrevista();
			this.sabCargaFvpErroresLIst = list;					
			strMessage = "procesoSABCargarFuenteVentaPrevistaUpload.msg.CargaDatos.error";
			this.addError("Error: ", this.getResourceMessage(strMessage));
		}			
	    return this.sabCargaFvpErroresLIst;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setAfterValidarAttributes(java.util.Map)
	 */
	protected void setAfterValidarAttributes(Map<String, Object> datos) throws Exception {
		this.mostrarBotonProcesar = false;
	}
	
	/**
	 * @param actionEvent
	 */
	public void calcular(ActionEvent actionEvent) {	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'calcular' method");
		}
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String strMessage = null;	
			FuenteVentasService service = (FuenteVentasService) getBean("sisicc.fuenteVentasService");
			ProcesoSABCargarFuenteVentaPrevistaForm f = (ProcesoSABCargarFuenteVentaPrevistaForm) this.formCargaArchivo;
			
			MantenimientoBASParametroPaisService serviceBaspp = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
			BASParametroPais baspp = new BASParametroPais();
			baspp.setCodigoPais(pais.getCodigo());
			baspp.setCodigoSistema(Constants.FVP_CODIGO_SISTEMA);
			baspp.setCodigoParametro(Constants.FVP_CODIGO_PARAMETRO_IND_TIPO_CARGA_FUENTE_VENTAS);
			List list1 = serviceBaspp.getParametroPais(baspp);
			String indTipoFvp = null;
			
			if(list1 != null && list1.size() > 0){
				BASParametroPais baspp1 = (BASParametroPais) list1.get(0);
				indTipoFvp = baspp1.getValorParametro();
			}
			
			Map datos = new HashMap();
			datos.put("indTipoFvp", indTipoFvp);
	
			f.setFlagMostrarErrores(false);
			
	//		if(StringUtils.equalsIgnoreCase(indTipoFvp, Constants.NUMERO_UNO))
	//			f.setFlagMostrarCalculo(false);
	//		else
	//			f.setFlagMostrarCalculo(true);
			this.mostrarListaCarga = false;
			this.errorValidar = false;
			this.mostrarBotonProcesar = true;
			this.mostrarBotonValidar = false;
			
			f.setFlagCalcular(false);
			f.setFlagProcesar(true);
			
			List list = service.getCalcularCargaFuenteVentaPrevista(datos);
			this.sabCargaFvpCalcularList = list;			
			
			f.setFlagCalcular(false);
			strMessage = "procesoSABCargarFuenteVentaPrevistaUpload.msg.Calcula.ok";
			this.addInfo("Información", this.getResourceMessage(strMessage));	
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	
		return;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setProcesarAttributes(java.util.Map)
	 */
	@Override
	protected void setProcesarAttributes(Map<String, Object> datos)
			throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'actualizar' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		FuenteVentasService service = (FuenteVentasService) getBean("sisicc.fuenteVentasService");
		ProcesoSABCargarFuenteVentaPrevistaForm f = (ProcesoSABCargarFuenteVentaPrevistaForm) this.formCargaArchivo;
		
		MantenimientoBASParametroPaisService serviceBaspp = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
		BASParametroPais baspp = new BASParametroPais();
		baspp.setCodigoPais(pais.getCodigo());
		baspp.setCodigoSistema(Constants.FVP_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.FVP_CODIGO_PARAMETRO_IND_TIPO_CARGA_FUENTE_VENTAS);
		List list1 = serviceBaspp.getParametroPais(baspp);
		String indTipoFvp = null;
		
		if(list1 != null && list1.size() > 0){
			BASParametroPais baspp1 = (BASParametroPais) list1.get(0);
			indTipoFvp = baspp1.getValorParametro();
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario"+usuario.getLogin());
		
		
        datos.put("usuario", usuario.getLogin());
        datos.put("indTipoFvp", indTipoFvp);
        datos.put("codigoPais", pais.getCodigo());
        datos.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", datos));
		
		service.actualizarCargaFuenteVentaPrevista(datos);
		
		f.setFlagMostrarCalculo(false);		
		return;
	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#getMensajeProcesarOK()
	 */
	@Override
	protected String getMensajeProcesarOK() {
		String mensaje = "procesoSABCargarFuenteVentaPrevistaUpload.msg.actualiza.ok";
		return this.getResourceMessage(mensaje);
	}

	/**
	 * @param event
	 */
	public void uploadCampanha(ActionEvent event){
		super.upload(this.eventCampanha);
	}
	
	/**
	 * @return the sabCargaFvpErroresLIst
	 */
	public List getSabCargaFvpErroresLIst() {
		return sabCargaFvpErroresLIst;
	}

	/**
	 * @param sabCargaFvpErroresLIst the sabCargaFvpErroresLIst to set
	 */
	public void setSabCargaFvpErroresLIst(List sabCargaFvpErroresLIst) {
		this.sabCargaFvpErroresLIst = sabCargaFvpErroresLIst;
	}

	/**
	 * @return the sabCargaFvpCalcularList
	 */
	public List getSabCargaFvpCalcularList() {
		return sabCargaFvpCalcularList;
	}

	/**
	 * @param sabCargaFvpCalcularList the sabCargaFvpCalcularList to set
	 */
	public void setSabCargaFvpCalcularList(List sabCargaFvpCalcularList) {
		this.sabCargaFvpCalcularList = sabCargaFvpCalcularList;
	}

	/**
	 * @return the eventCampanha
	 */
	public FileUploadEvent getEventCampanha() {
		return eventCampanha;
	}

	/**
	 * @param eventCampanha the eventCampanha to set
	 */
	public void setEventCampanha(FileUploadEvent eventCampanha) {
		this.eventCampanha = eventCampanha;
	}
}