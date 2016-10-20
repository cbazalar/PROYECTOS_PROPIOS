package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECGestionIngresoAnulacionNmpsSearchService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.action.MantenimientoCCCRegularizacionPagosBancariosSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECGestionIngresoAnulacionNmpsSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoRECGestionIngresoAnulacionNmpsExecuteAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = -557078179931829404L;
	
	private List ingresoAnulacionNMPSList;
	private String numeroLote;	
	private String codigoConsultora;
	private String motivoAnulacion;
	private String tipoAnulacion;
	private String observaciones;
	private String tipoSeleccion;
	private String codigoProceso;
	
	private String pantallaPadre;
	private boolean activarSalirPadre = false;
	private boolean mostrarBotonSave = false;
	private boolean mostrarBotonSalir = true;
	private boolean salirPopup = true;
	
	private DataTableModel dataTableAnulacion;
	private List listaAnulacion;
	
	/**
	 * Metodo para regresar a la Pantalla Padre de Movimientos Bancarios
	 * @param event
	 */
	public String salirPadre() {
		String retorno= null;
		retorno = this.mPantallaPrincipalBean.ingresarOpcionMenu03();
		return retorno;
		
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm f = new MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm();
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();
		f.setCodigoPais(codigoPais);
		f.setCodigoProcesoBatch("08");
		f.setCodigoSistema(Constants.CODIGO_SISTEMA_REC);
		return f;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm f = (MantenimientoRECGestionIngresoAnulacionNmpsExecuteForm) this.formProceso;
		MantenimientoRECGestionIngresoAnulacionNmpsSearchService service = (MantenimientoRECGestionIngresoAnulacionNmpsSearchService) getBean("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService");
		params.put("numeroPedido", numeroLote);
		params.put("numeroLote",numeroLote);
		params.put("codigoConsultora", codigoConsultora);
		params.put("codigoProceso", codigoProceso);

		if (log.isDebugEnabled()) {
			log.debug("IngresoAnulacionNMPSList.size :"
					+ ingresoAnulacionNMPSList.size());
			log.debug("numeroLote obtenido de session :" + numeroLote);
		}

		for (int i = 0; i < ingresoAnulacionNMPSList.size(); i++) {
			Map ingresoAnulacionNMPSMap = new HashMap();

			ingresoAnulacionNMPSMap = (HashMap) ingresoAnulacionNMPSList.get(i);

			ingresoAnulacionNMPSMap.put("flagNotaMercaderia",
					f.getFlagNotaMercaderia());
			ingresoAnulacionNMPSMap.put("flagGenerarEnvia",
					f.getFlagGenerarEnvia());
			ingresoAnulacionNMPSMap.put("codigoProceso", f.getCodigoProceso());
			ingresoAnulacionNMPSMap.put("usuario", usuario.getLogin());
			String motivo = StringUtils.splitPreserveAllTokens(this.motivoAnulacion, "|")[0];
			ingresoAnulacionNMPSMap.put("motivoAnulacion", motivo);
			ingresoAnulacionNMPSMap.put("tipoAnulacion",f.getTipoAnulacion()); //this.tipoAnulacion );
			ingresoAnulacionNMPSMap.put("numeroLote", numeroLote);

			if (!this.tipoSeleccion.equals("01")) {
				ingresoAnulacionNMPSMap.put("observaciones",f.getObservaciones()); //this.observaciones);
			}

			service.executeProcesoIngresoAnulacionNmps(ingresoAnulacionNMPSMap);

		}

		if (log.isDebugEnabled()) {
			log.debug("LANZAR EL STORED QUE SE VA CREAR................");
			Map ingresoGeneraArchivoAnulacionNMPSMap = new HashMap();
			ingresoGeneraArchivoAnulacionNMPSMap.put("numeroLote", numeroLote);
			ingresoGeneraArchivoAnulacionNMPSMap.put("codigoPais", codigoPais);
			ingresoGeneraArchivoAnulacionNMPSMap.put("codigoSistema",
					Constants.CODIGO_SISTEMA_REC);

			service.executeProcesoGenerarArchivoIngresoAnulacionNmps(ingresoGeneraArchivoAnulacionNMPSMap);
		}
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonExecute = false;
	}

	
	/*
	 * 
	 */
	public void openPopupDesc(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String urlPopup = externalContext.getRequestParameterMap().get("urlPopup");
		String numeroLote = externalContext.getRequestParameterMap().get("numeroLote");
		String flagVistaProcesoBatch = externalContext.getRequestParameterMap().get("flagVistaProcesoBatch");
		
		this.setUrlPopupConsulta(urlPopup);
				
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			
		//Obtenes valor del parametro para la pantalla de Digitacion Simplificada
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");
		
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(this.mPantallaPrincipalBean.getCurrentUser().getCodigoPais());
		parametroPais1.setCodigoSistema(Constants.CODIGO_SISTEMA_REC);
		parametroPais1.setNombreParametro("indGenerarArchivoAnul");
		
		List lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		
		/*
		this.mostrarEnvioApe = Constants.NUMERO_CERO;
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			mostrarEnvioApe = ps.getValorParametro();
		}
		*/
		if(StringUtils.equals(flagVistaProcesoBatch, Constants.UNO))
		{
			//Detalles dew Anulaciones
			
			Map map = new HashMap();
			map.put("numeroLote", numeroLote);
			
			this.listaAnulacion = service.getDetalleAnulacion(map);
			this.dataTableAnulacion = new DataTableModel(this.listaAnulacion);
		}
			
		String popup = "PF('popupConsultaForm').show()";
		
		this.getRequestContext().execute(popup);		
		
	}	
	
	/**
	 * Metodo Salir
	 * @return
	 */
	public void salir(ActionEvent actionEvent)  {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'Salir' method");
		}
		if (this.salirPopup) {
			String ventana = "PF('popupConsultaForm').hide()";
			this.getRequestContext().execute(ventana);
			return;
		}
		
		return;
	}
	
	
	/* GET - SET */
	
	
	/**
	 * @return the ingresoAnulacionNMPSList
	 */
	public List getIngresoAnulacionNMPSList() {
		return ingresoAnulacionNMPSList;
	}

	/**
	 * @param ingresoAnulacionNMPSList
	 *            the ingresoAnulacionNMPSList to set
	 */
	public void setIngresoAnulacionNMPSList(List ingresoAnulacionNMPSList) {
		this.ingresoAnulacionNMPSList = ingresoAnulacionNMPSList;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 *            the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the motivoAnulacion
	 */
	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	/**
	 * @param motivoAnulacion the motivoAnulacion to set
	 */
	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	/**
	 * @return the tipoAnulacion
	 */
	public String getTipoAnulacion() {
		return tipoAnulacion;
	}

	/**
	 * @param tipoAnulacion the tipoAnulacion to set
	 */
	public void setTipoAnulacion(String tipoAnulacion) {
		this.tipoAnulacion = tipoAnulacion;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the tipoSeleccion
	 */
	public String getTipoSeleccion() {
		return tipoSeleccion;
	}

	/**
	 * @param tipoSeleccion the tipoSeleccion to set
	 */
	public void setTipoSeleccion(String tipoSeleccion) {
		this.tipoSeleccion = tipoSeleccion;
	}

	/**
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		codigoProceso = codigoProceso;
	}

	/**
	 * @return the pantallaPadre
	 */
	public String getPantallaPadre() {
		return pantallaPadre;
	}

	/**
	 * @param pantallaPadre the pantallaPadre to set
	 */
	public void setPantallaPadre(String pantallaPadre) {
		this.pantallaPadre = pantallaPadre;
	}

	/**
	 * @return the activarSalirPadre
	 */
	public boolean isActivarSalirPadre() {
		return activarSalirPadre;
	}

	/**
	 * @param activarSalirPadre the activarSalirPadre to set
	 */
	public void setActivarSalirPadre(boolean activarSalirPadre) {
		this.activarSalirPadre = activarSalirPadre;
	}

	/**
	 * @return the dataTableAnulacion
	 */
	public DataTableModel getDataTableAnulacion() {
		return dataTableAnulacion;
	}

	/**
	 * @param dataTableAnulacion the dataTableAnulacion to set
	 */
	public void setDataTableAnulacion(DataTableModel dataTableAnulacion) {
		this.dataTableAnulacion = dataTableAnulacion;
	}

	/**
	 * @return the listaAnulacion
	 */
	public List getListaAnulacion() {
		return listaAnulacion;
	}

	/**
	 * @param listaAnulacion the listaAnulacion to set
	 */
	public void setListaAnulacion(List listaAnulacion) {
		this.listaAnulacion = listaAnulacion;
	}

	/**
	 * @return the mostrarBotonSave
	 */
	public boolean isMostrarBotonSave() {
		return mostrarBotonSave;
	}

	/**
	 * @param mostrarBotonSave the mostrarBotonSave to set
	 */
	public void setMostrarBotonSave(boolean mostrarBotonSave) {
		this.mostrarBotonSave = mostrarBotonSave;
	}

	/**
	 * @return the salirPopup
	 */
	public boolean isSalirPopup() {
		return salirPopup;
	}

	/**
	 * @param salirPopup the salirPopup to set
	 */
	public void setSalirPopup(boolean salirPopup) {
		this.salirPopup = salirPopup;
	}

	/**
	 * @return the mostrarBotonSalir
	 */
	public boolean isMostrarBotonSalir() {
		return mostrarBotonSalir;
	}

	/**
	 * @param mostrarBotonSalir the mostrarBotonSalir to set
	 */
	public void setMostrarBotonSalir(boolean mostrarBotonSalir) {
		this.mostrarBotonSalir = mostrarBotonSalir;
	}
	
	

}
