/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.NroLoteMultiHilo;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActuMultiHilo;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaBASProcesoBatchActuaSearchForm;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar </a>
 *
 */
@ManagedBean
@SessionScoped
public class ConsultaBASProcesoBatchActualesSearchAction extends BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3982952705051444392L;
	private List sistemas;
	private LabelValue[] procesos;
	
	private List listaInterfaz;
	private DataTableModel dataTableInterfaz;
	private Historico historico;
	
	private DataTableModel dataTableAnulacion;
	private List listaAnulacion;
	
	private String mostrarEnvioApe;
	private ProcesoBatchActuMultiHilo procesoBatchActual;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaBASProcesoBatchActuaSearchForm form = new ConsultaBASProcesoBatchActuaSearchForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#getSalirForward()
	 */
	protected String getSalirForward() {
		return "consultaBASProcesoBatchActuaList";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		ConsultaBASProcesoBatchActuaSearchForm f = (ConsultaBASProcesoBatchActuaSearchForm) this.formBusqueda;
		
		Map criteria = BeanUtils.describe(f);
		ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		
		List resultado = service.getProcesoBatchActuByCriteria(criteria);
		List resultadoMultiHilo = new ArrayList();
		Map mapaNroLote = new HashMap();
		
		for(int i=0; i < resultado.size(); i++) {
			ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu)resultado.get(i);
			String log = procesoBatchActu.getLog();
			log = StringEscapeUtils.escapeJavaScript(log);
			procesoBatchActu.setLog(log);
			
			ProcesoBatchActuMultiHilo registroMultiHilo = new ProcesoBatchActuMultiHilo();
			registroMultiHilo.setProcesoBatchActu(procesoBatchActu);
			mapaNroLote.put("codigoPais", procesoBatchActu.getCodigoPais());
			mapaNroLote.put("codigoSistema", procesoBatchActu.getCodigoSistema());
			mapaNroLote.put("idProcesoBatch", procesoBatchActu.getIdProcesoBatch());
			
			List listaNroLoteMultiHilo = service.getObtieneNroLoteProcesoBatchActu(mapaNroLote);
			for(int j=0; j < listaNroLoteMultiHilo.size(); j++) {
				NroLoteMultiHilo registroNroLoteMultiHilo = (NroLoteMultiHilo)listaNroLoteMultiHilo.get(j);
				mapaNroLote.put("numeroLote", registroNroLoteMultiHilo.getNumeroLote());
				List listaHistorico = historicoService.getHistoricosLotesMultiHilo(mapaNroLote);
				registroNroLoteMultiHilo.setListaHistorico(listaHistorico);
				listaNroLoteMultiHilo.set(j, registroNroLoteMultiHilo);
			}
			
			registroMultiHilo.setListaNroLoteMultiHilo(listaNroLoteMultiHilo);
			resultadoMultiHilo.add(registroMultiHilo);
		}

		String codigoSistema = (String) criteria.get("codigoSistema");
        //(String)request.getSession().getAttribute("codigoProcesoBatch");
		String codigoProcesoBatch = "";
        
        if (StringUtils.isBlank(codigoProcesoBatch))
        	codigoProcesoBatch = (String) criteria.get("codigoProcesoBatch");
        
        f.setCodigoSistema(codigoSistema);
        f.setCodigoProcesoBatch(codigoProcesoBatch);
        f.setCodigoProcesoBatchAnterior(codigoProcesoBatch);
        
		return resultadoMultiHilo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
        // Obtenemos los sistemas del pais
        Map criteria = new HashMap();
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        criteria.put("codigoPais", pais.getCodigo());
        SistemaService sistemaService = (SistemaService) getBean("sisicc.sistemaService");
        
        this.setMostrarBotonSalir(true);
        this.setMostrarListaBusqueda(false);
        this.sistemas = sistemaService.getSistemasByCriteria(criteria);
        this.salirPopup = false;
	}

	/**
	 * Obtiene lista de procesos por sistema
	 * @param val
	 */
	public void showProcesosxSistema(ValueChangeEvent val){
		log.debug(">>showProcesosxSistema ");
		
		log.debug("val: "+val.getNewValue().toString());
		
		String codigoSistema = (String) val.getNewValue();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaBASProcesoBatchActuaSearchForm form = (ConsultaBASProcesoBatchActuaSearchForm) this.formBusqueda;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.procesos = aSvc.getProcesoBatchBySistema(pais.getCodigo(), codigoSistema);
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void openPopupEstado(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String urlPopup = externalContext.getRequestParameterMap().get("urlPopup");
		String descEstadoProceso = externalContext.getRequestParameterMap().get("descEstadoProceso");
		
		this.setUrlPopupConsulta(urlPopup);
		
		ConsultaBASProcesoBatchActuaSearchForm form = (ConsultaBASProcesoBatchActuaSearchForm) this.formBusqueda;
		
		if (StringUtils.isNotBlank(descEstadoProceso)) {
			form.setDescEstadoProceso(descEstadoProceso);
		}
		
		String popup = "PF('popupConsultaForm').show()";
		this.getRequestContext().execute(popup);		
		
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void anularProceso(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		if(log.isDebugEnabled())			
			log.debug(this.procesoBatchActual);
		
		
    	Map criteria = new HashMap();
    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoBatchService procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		
		criteria.put("codigoSistema", procesoBatchActual.getProcesoBatchActu().getCodigoSistema());
    	criteria.put("codigoProcesoBatch", procesoBatchActual.getProcesoBatchActu().getProcesoBatch().getCodigoProcesoBatch());
    	criteria.put("numeroLote", procesoBatchActual.getProcesoBatchActu().getNumeroLote());
		criteria.put("codigoPais", pais.getCodigo());
		
		if (validationSuccessful(criteria)){
		    		    	    	
	    	criteria.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);		
	    	criteria.put("descripcionLog", this.getResourceMessage("consultaBASProcesoBatchActuaSearchForm.logError"));
			criteria.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
						    	
			procesoBatchService.updateProcesoBatchActu(criteria, usuario);
		}

		this.find();
	}	
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void openPopupLog(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String urlPopup = externalContext.getRequestParameterMap().get("urlPopup");
		String id = externalContext.getRequestParameterMap().get("id");
		this.setUrlPopupConsulta(urlPopup);
		
		ConsultaBASProcesoBatchActuaSearchForm form = (ConsultaBASProcesoBatchActuaSearchForm) this.formBusqueda;
		
		if(StringUtils.isNotBlank(id))
		{
			ProcesoBatchActuMultiHilo proceso = (ProcesoBatchActuMultiHilo)this.listaBusqueda.get(Integer.parseInt(id));
			ProcesoBatchActu log = proceso.getProcesoBatchActu();
			
			if (StringUtils.isNotBlank(log.getLog())) 
			{
				form.setLog(log.getLog());
			}
			form.setRecomendacion(log.getDescripcionRecomendacion());
		}
		
		String popup = "PF('popupConsultaForm').show()";
		
		this.getRequestContext().execute(popup);		
		
	}	

	/**
	 * 
	 * @param actionEvent
	 */
	public void openPopupInterfaz(ActionEvent actionEvent) {
	 try {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String urlPopup = externalContext.getRequestParameterMap().get("urlPopup");
		String codigoSistema = externalContext.getRequestParameterMap().get("codigoSistema");
		String numeroLote = externalContext.getRequestParameterMap().get("numeroLote");
		String idProcesoBatch = externalContext.getRequestParameterMap().get("idProcesoBatch");
		String codigoInterfaz = externalContext.getRequestParameterMap().get("codigoInterfaz");

		this.setUrlPopupConsulta(urlPopup);
		this.setUrlPopupConsulta("");
		
		//Realizamos la busqueda de la interfaz
		ProcesoBatchService serviceActual = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		HistoricoService service = (HistoricoService) getBean("sisicc.historicoService");
		Map criteria = new HashMap();

		if(StringUtils.isNotBlank(codigoSistema))
			criteria.put("codigoSistema", codigoSistema);
		
		if(StringUtils.isNotBlank(numeroLote))
			criteria.put("numeroLote", numeroLote);
		
		if(StringUtils.isNotBlank(idProcesoBatch))
			criteria.put("idProceso", idProcesoBatch);

		if(StringUtils.isNotBlank(codigoInterfaz))
			criteria.put("codigoInterfaz", codigoInterfaz);
		
		this.listaInterfaz = service.getHistoricosByCriteria(criteria);
		this.dataTableInterfaz = new DataTableModel(this.listaInterfaz);
		
		ConsultaBASProcesoBatchActuaSearchForm form = (ConsultaBASProcesoBatchActuaSearchForm) this.formBusqueda;		
		
		//Sacamos la recomendacion
		String descripcionRecomendacion = "";
		List resultado = serviceActual.getProcesoBatchActuByCriteria(criteria);

		if(resultado != null && resultado.size() == 1)
		{
			ProcesoBatchActu pba = (ProcesoBatchActu)resultado.get(0);
			descripcionRecomendacion = pba.getDescripcionRecomendacion();
		}
		form.setRecomendacion(descripcionRecomendacion);
		//
		
		//String popup = "PF('popupConsultaForm').show()";
		//this.getRequestContext().execute(popup);	
		this.redireccionarPagina("consultaBASProcesoBatchActuaListInterfaz");
	 }
	 catch(Exception e) {
		 this.addError("Error: ", this.obtieneMensajeErrorException(e));
	 }
		
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
		
		this.mostrarEnvioApe = Constants.NUMERO_CERO;
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			mostrarEnvioApe = ps.getValorParametro();
		}
		//
		
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
	 * Método pque valida que el proceso este en ejecución
	 * @param request
	 * @param criteria
	 * @return
	 */
	private boolean validationSuccessful(Map criteria) {
		boolean isOk = true;

		ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");		
		ProcesoBatchActu procesoBatch = (ProcesoBatchActu) service.getProcesoBatchActuByCriteria(criteria).get(0);
				
		if (procesoBatch.getIndicadorEjecucion().equals(Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO))
		{
			this.addError("Error: ", this.getResourceMessage("errors.ejecucion.procesoBatchActu"));
			isOk = false;
		}

		return isOk;
	}
	
	/**
	 * @return the sistemas
	 */
	public List getSistemas() {
		return sistemas;
	}

	/**
	 * @param sistemas the sistemas to set
	 */
	public void setSistemas(List sistemas) {
		this.sistemas = sistemas;
	}

	/**
	 * @return the procesos
	 */
	public LabelValue[] getProcesos() {
		return procesos;
	}

	/**
	 * @param procesos the procesos to set
	 */
	public void setProcesos(LabelValue[] procesos) {
		this.procesos = procesos;
	}

	/**
	 * @return the listaInterfaz
	 */
	public List getListaInterfaz() {
		return listaInterfaz;
	}

	/**
	 * @param listaInterfaz the listaInterfaz to set
	 */
	public void setListaInterfaz(List listaInterfaz) {
		this.listaInterfaz = listaInterfaz;
	}

	/**
	 * @return the dataTableInterfaz
	 */
	public DataTableModel getDataTableInterfaz() {
		return dataTableInterfaz;
	}

	/**
	 * @param dataTableInterfaz the dataTableInterfaz to set
	 */
	public void setDataTableInterfaz(DataTableModel dataTableInterfaz) {
		this.dataTableInterfaz = dataTableInterfaz;
	}

	/**
	 * @return the historico
	 */
	public Historico getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(Historico historico) {
		this.historico = historico;
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
	 * @return the mostrarEnvioApe
	 */
	public String getMostrarEnvioApe() {
		return mostrarEnvioApe;
	}

	/**
	 * @param mostrarEnvioApe the mostrarEnvioApe to set
	 */
	public void setMostrarEnvioApe(String mostrarEnvioApe) {
		this.mostrarEnvioApe = mostrarEnvioApe;
	}

	/**
	 * @return the procesoBatchActual
	 */
	public ProcesoBatchActuMultiHilo getProcesoBatchActual() {
		return procesoBatchActual;
	}

	/**
	 * @param procesoBatchActual the procesoBatchActual to set
	 */
	public void setProcesoBatchActual(ProcesoBatchActuMultiHilo procesoBatchActual) {
		this.procesoBatchActual = procesoBatchActual;
	}

}
