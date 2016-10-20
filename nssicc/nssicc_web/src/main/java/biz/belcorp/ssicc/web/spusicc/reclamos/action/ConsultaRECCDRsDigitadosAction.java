package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CabecerasCDRDigitadas;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ConsultaRECCDRsDigitadosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ConsultaRECCDRsDigitadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsultaRECCDRsDigitadosAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List recCdrDigitadosList;
	private Object beanConsultaRECCDRsDigitados;
	private Boolean mostrarGrilla;
	private Boolean mostrarDatatable;
	private Boolean indicadorExpress;
	private Boolean mostrarBotonExcel;
	
	@ManagedProperty(value = "#{consultaRECCDRsDigitadosDetallesAction}")
	private ConsultaRECCDRsDigitadosDetallesAction consultaRECCDRsDigitadosDetallesAction;
	
	@ManagedProperty(value = "#{reporteRECCDRsDigitadosAction}")
	private ReporteRECCDRsDigitadosAction reporteRECCDRsDigitadosAction;
	
	/**
	 * @param evt
	 */
	public void ejecutarReporteP(ActionEvent evt){
		try {
			ConsultaRECCDRsDigitadosForm f = (ConsultaRECCDRsDigitadosForm)this.formBusqueda;
			f.setFechaIngreso(DateUtil.convertDateToString(f.getFechaIngresoD()));
			this.reporteRECCDRsDigitadosAction.setFormatoExportacion("XLS");
			this.reporteRECCDRsDigitadosAction.setTipoConsulta(f.getTipoConsulta());
			this.reporteRECCDRsDigitadosAction.setFechaIngreso(f.getFechaIngreso());
			this.reporteRECCDRsDigitadosAction.setCodigoPeriodo(f.getCodigoPeriodo());
			this.reporteRECCDRsDigitadosAction.setRegionList(f.getRegionList());
			this.reporteRECCDRsDigitadosAction.setZonaList(f.getZonaList());
			this.reporteRECCDRsDigitadosAction.getFormReporte().setFormatoExportacion("XLS");
			this.reporteRECCDRsDigitadosAction.executeReporte();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "consultaRECCDRsDigitadosList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "consultaRECCDRsDigitadosForm";
	}

	/**
	 * @param event
	 */
	public void abrirPopup(ActionEvent event){
		String mensaje="";
		CabecerasCDRDigitadas objSeleccionado = (CabecerasCDRDigitadas) this.beanConsultaRECCDRsDigitados;
		if (objSeleccionado == null) {
			mensaje= "Seleccione un registro";
			this.addError("Error: ", mensaje);
			return;
		}
		try {
			this.consultaRECCDRsDigitadosDetallesAction.setId(objSeleccionado.getCdr());
			this.consultaRECCDRsDigitadosDetallesAction.setTipoConsulta(objSeleccionado.getTipoConsulta());
			this.consultaRECCDRsDigitadosDetallesAction.inicializandoValores();
			String ventana = "PF('dialogMantenimientoForm2').show()";
			this.getRequestContext().execute(ventana);
			this.mostrarBotonSalir = false;
			this.mostrarBotonSave = false;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	
	/**
	 * MOSTRAR ZONAS RESPECTO A REGIONES
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas");
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String[] valor = (String[]) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (valor.length > 0) {
				LabelValue[] siccZonaList2 = ajax.getZonasMultipleByPaisMarcaCanalRegion(
						pais.getCodigo(), "T", "VD", valor, "T");
//				
//				if(siccZonaList2.length==1 && StringUtils.equalsIgnoreCase(siccZonaList2[0].getLabel(), "Todos")){
//					this.siccZonaList = siccZonaList2;
//				}else{
//					this.siccZonaList = new LabelValue[siccZonaList2.length + 1];
//					this.siccZonaList[0] = new LabelValue("Todos", "");
//					
//					for(int i=0;i<siccZonaList2.length;i++){
//						this.siccZonaList[i+1] = siccZonaList2[i];
//					}
//				}
				this.siccZonaList=siccZonaList2;
//				
			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaRECCDRsDigitadosForm form = new ConsultaRECCDRsDigitadosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) log.debug("search");
		
		ConsultaRECCDRsDigitadosForm f=(ConsultaRECCDRsDigitadosForm)this.formBusqueda;
		if(this.indicadorExpress){
			f.setIndicadorExpress("true");
		}else{
			f.setIndicadorExpress("false");
		}
		
		ConsultaRECCDRsDigitadosService service = (ConsultaRECCDRsDigitadosService) getBean("spusicc.consultaRECCDRsDigitadosService");
		Map criteria = new HashMap(); 

		if(f.getRegionList()!=null){
		    if(f.getRegionList().length==1){
		    	if(f.getRegionList()[0].compareToIgnoreCase("")==0){
		    		f.setRegionList(null);	
		    	}
		    }
		}
		if(f.getZonaList()!=null){
			   
		    if(f.getZonaList().length==1){
		    	if(f.getZonaList()[0].compareToIgnoreCase("")==0){
		    		
		    		f.setZonaList(null);	
		    	}
		    }
		}
		f.setFechaIngreso(DateUtil.convertDateToString(f.getFechaIngresoD()));
		
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("codigoPeriodo",f.getCodigoPeriodo());
		criteria.put("fechaIngreso",f.getFechaIngreso());
		criteria.put("regionList",(f.getRegionList()== null) ? new ArrayList(): Arrays.asList(f.getRegionList()));
		criteria.put("zonaList",(f.getZonaList()== null) ? new ArrayList(): Arrays.asList(f.getZonaList()));
		if(!f.getIndicadorExpress().equals("false"))
			criteria.put("indExpress",Constants.IND_EXPRESS_ACTIVO);
		else
			criteria.put("indExpress",Constants.IND_EXPRESS_INACTIVO);
		criteria.put("tipoConsulta",f.getTipoConsulta());
		
		List listaCabeceras= new ArrayList();
		if(f.getTipoConsulta().compareToIgnoreCase("1")==0)
			listaCabeceras=service.getListaCabeceras(criteria);
		else
			listaCabeceras=service.getListaCabecerasHistorico(criteria);
		
		this.recCdrDigitadosList = listaCabeceras;
		int tamanio  = this.recCdrDigitadosList.size();
		if (tamanio > 0) {
			this.mostrarDatatable = true;
			this.mostrarListaBusqueda =false;
			this.mostrarBotonExcel = true;
		}
		
		return this.recCdrDigitadosList;
		
	}

	/**
	 * @param evt
	 */
	public void deleteRECCDRsDigitados(ActionEvent evt)  {

		if (log.isDebugEnabled())
			log.debug("deleted");
		
		try {
			ConsultaRECCDRsDigitadosForm f = (ConsultaRECCDRsDigitadosForm) this.formBusqueda;

			ConsultaRECCDRsDigitadosService service = (ConsultaRECCDRsDigitadosService) getBean("spusicc.consultaRECCDRsDigitadosService");
			CabecerasCDRDigitadas objSeleccionado = (CabecerasCDRDigitadas) this.beanConsultaRECCDRsDigitados;
			String numero = new String();
			numero = objSeleccionado.getCdr();
			String tipoConsulta=objSeleccionado.getTipoConsulta();
			
			String numeroDocumento = numero;
//			List cabecerasDigitadasList = this.recCdrDigitadosList;
			Map criteriaElimina = new HashMap();

			criteriaElimina.put("codigoPais", f.getCodigoPais());
			criteriaElimina.put("numeroDocumento", numeroDocumento);

			Map criteria = new HashMap();
			if (f.getRegionList() != null) {
				if (f.getRegionList().length == 1) {
					if (f.getRegionList()[0].compareToIgnoreCase("") == 0) {
						f.setRegionList(null);
					}
				}
			}
			if (f.getZonaList() != null) {

				if (f.getZonaList().length == 1) {
					if (f.getZonaList()[0].compareToIgnoreCase("") == 0) {

						f.setZonaList(null);
					}
				}
			}
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("fechaIngreso", f.getFechaIngreso());
			criteria.put(
					"regionList",
					(f.getRegionList() == null) ? new ArrayList() : Arrays.asList(f
							.getRegionList()));
			criteria.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
					: Arrays.asList(f.getZonaList()));
			if (!f.getIndicadorExpress().equals("false"))
				criteria.put("indExpress", Constants.IND_EXPRESS_ACTIVO);
			else
				criteria.put("indExpress", Constants.IND_EXPRESS_INACTIVO);
			criteria.put("tipoConsulta", f.getTipoConsulta());

			if (f.getTipoConsulta().compareToIgnoreCase("1") == 0) {
				service.deleteDetallesDigitados(criteriaElimina);
				service.deleteCabeceraDigitada(criteriaElimina);
			} else {
				service.deleteDetallesDigitadosHistoricos(criteriaElimina);
				service.deleteCabeceraDigitadaHistoricos(criteriaElimina);
			}
			
			this.recCdrDigitadosList = new ArrayList();
			List listaCabeceras = new ArrayList();
			
			if (StringUtils.equals(tipoConsulta, "1"))
				listaCabeceras = service.getListaCabeceras(criteria);
			else
				listaCabeceras = service.getListaCabecerasHistorico(criteria);
			this.recCdrDigitadosList = listaCabeceras;
			int tamanio  = this.recCdrDigitadosList.size();
			if (tamanio > 0) {
				this.mostrarDatatable = true;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		log.debug("Executing action : setViewAttributes.");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ConsultaRECCDRsDigitadosForm f = (ConsultaRECCDRsDigitadosForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		this.mostrarDatatable  = false;
		f.setFechaIngresoD(new Date());
		f.setFechaIngreso("");
		this.indicadorExpress = false;
		
		f.setCodigoPeriodo("");
		this.mostrarBotonConsultar  = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo  =false;
		this.mostrarBotonEliminar  = false;
		this.mostrarBotonExcel = false;

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
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
	 * @return the recCdrDigitadosList
	 */
	public List getRecCdrDigitadosList() {
		return recCdrDigitadosList;
	}

	/**
	 * @param recCdrDigitadosList the recCdrDigitadosList to set
	 */
	public void setRecCdrDigitadosList(List recCdrDigitadosList) {
		this.recCdrDigitadosList = recCdrDigitadosList;
	}

	/**
	 * @return the beanConsultaRECCDRsDigitados
	 */
	public Object getBeanConsultaRECCDRsDigitados() {
		return beanConsultaRECCDRsDigitados;
	}

	/**
	 * @param beanConsultaRECCDRsDigitados the beanConsultaRECCDRsDigitados to set
	 */
	public void setBeanConsultaRECCDRsDigitados(Object beanConsultaRECCDRsDigitados) {
		this.beanConsultaRECCDRsDigitados = beanConsultaRECCDRsDigitados;
	}

	/**
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return the mostrarDatatable
	 */
	public Boolean getMostrarDatatable() {
		return mostrarDatatable;
	}

	/**
	 * @param mostrarDatatable the mostrarDatatable to set
	 */
	public void setMostrarDatatable(Boolean mostrarDatatable) {
		this.mostrarDatatable = mostrarDatatable;
	}

	/**
	 * @return the consultaRECCDRsDigitadosDetallesAction
	 */
	public ConsultaRECCDRsDigitadosDetallesAction getConsultaRECCDRsDigitadosDetallesAction() {
		return consultaRECCDRsDigitadosDetallesAction;
	}

	/**
	 * @param consultaRECCDRsDigitadosDetallesAction the consultaRECCDRsDigitadosDetallesAction to set
	 */
	public void setConsultaRECCDRsDigitadosDetallesAction(
			ConsultaRECCDRsDigitadosDetallesAction consultaRECCDRsDigitadosDetallesAction) {
		this.consultaRECCDRsDigitadosDetallesAction = consultaRECCDRsDigitadosDetallesAction;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/**
	 * @return the reporteRECCDRsDigitadosAction
	 */
	public ReporteRECCDRsDigitadosAction getReporteRECCDRsDigitadosAction() {
		return reporteRECCDRsDigitadosAction;
	}

	/**
	 * @param reporteRECCDRsDigitadosAction the reporteRECCDRsDigitadosAction to set
	 */
	public void setReporteRECCDRsDigitadosAction(
			ReporteRECCDRsDigitadosAction reporteRECCDRsDigitadosAction) {
		this.reporteRECCDRsDigitadosAction = reporteRECCDRsDigitadosAction;
	}

	/**
	 * @return the indicadorExpress
	 */
	public Boolean getIndicadorExpress() {
		return indicadorExpress;
	}

	/**
	 * @param indicadorExpress the indicadorExpress to set
	 */
	public void setIndicadorExpress(Boolean indicadorExpress) {
		this.indicadorExpress = indicadorExpress;
	}

	/**
	 * @return the mostrarBotonExcel
	 */
	public Boolean getMostrarBotonExcel() {
		return mostrarBotonExcel;
	}

	/**
	 * @param mostrarBotonExcel the mostrarBotonExcel to set
	 */
	public void setMostrarBotonExcel(Boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}
	
	
}