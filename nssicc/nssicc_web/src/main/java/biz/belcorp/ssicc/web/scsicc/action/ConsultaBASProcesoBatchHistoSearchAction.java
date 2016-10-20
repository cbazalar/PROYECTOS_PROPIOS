/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchHisto;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaBASProcesoBatchHistoSearchForm;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar </a>
 *
 */
@ManagedBean
@SessionScoped
public class ConsultaBASProcesoBatchHistoSearchAction extends BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1012577371118071422L;
	private List sistemas;
	private LabelValue[] procesos;
	
	private List listaInterfaz;
	private DataTableModel dataTableInterfaz;
	private Historico historico;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaBASProcesoBatchHistoSearchForm form = new ConsultaBASProcesoBatchHistoSearchForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		ConsultaBASProcesoBatchHistoSearchForm f = (ConsultaBASProcesoBatchHistoSearchForm) this.formBusqueda;
		
		Map criteria = BeanUtils.describe(f);
		ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");				
		List resultado = service.getProcesoBatchHistoByCriteria(criteria);

		return resultado;
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
		ConsultaBASProcesoBatchHistoSearchForm form = (ConsultaBASProcesoBatchHistoSearchForm) this.formBusqueda;
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
		
		ConsultaBASProcesoBatchHistoSearchForm form = (ConsultaBASProcesoBatchHistoSearchForm) this.formBusqueda;
		
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
	public void openPopupLog(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String urlPopup = externalContext.getRequestParameterMap().get("urlPopup");
		String id = externalContext.getRequestParameterMap().get("id");
		this.setUrlPopupConsulta(urlPopup);
		
		ConsultaBASProcesoBatchHistoSearchForm form = (ConsultaBASProcesoBatchHistoSearchForm) this.formBusqueda;
		
		if(StringUtils.isNotBlank(id))
		{
			ProcesoBatchHisto log = (ProcesoBatchHisto)this.listaBusqueda.get(Integer.parseInt(id));
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
		HistoricoService service = (HistoricoService) getBean("sisicc.historicoService");
		ProcesoBatchService serviceActual = (ProcesoBatchService) getBean("scsicc.procesoBatchService");				
		
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
		
		ConsultaBASProcesoBatchHistoSearchForm form = (ConsultaBASProcesoBatchHistoSearchForm) this.formBusqueda;		
		
		//Sacamos la recomendacion
		List resultado = serviceActual.getProcesoBatchActuByCriteria(criteria);

		String descripcionRecomendacion = "";
		if(resultado != null && resultado.size() == 1)
		{
			ProcesoBatchActu pba = (ProcesoBatchActu)resultado.get(0);
			descripcionRecomendacion = pba.getDescripcionRecomendacion(); 
		}
		form.setRecomendacion(descripcionRecomendacion);
		//
		
		//String popup = "PF('popupConsultaForm').show()";
		//this.getRequestContext().execute(popup);		
		this.redireccionarPagina("consultaBASProcesoBatchHistoListInterfaz");
	  }
	  catch(Exception e) {
		  this.addError("Error: ", this.obtieneMensajeErrorException(e));
	  }
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction#getSalirForward()
	 */
	protected String getSalirForward() {
		return "consultaBASProcesoBatchHistoList";
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

}
