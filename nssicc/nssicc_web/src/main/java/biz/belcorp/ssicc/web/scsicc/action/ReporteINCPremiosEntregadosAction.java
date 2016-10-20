/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCPremiosEntregadosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



// TODO: Auto-generated Javadoc
/**
 * The Class ReporteINCPremiosEntregadosAction.
 *
 * @author Belcorp
 * @version 1.0
 * 11/11/2014
 */
@ManagedBean
@SessionScoped
public class ReporteINCPremiosEntregadosAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1628377999996394136L;
	
	/** The formato reporte. */
	private String formatoReporte;
	
	/** The concursos. */
	private String[] concursos;
	
	/** The programas. */
	private String[] programas;
	
	/** The sicc marca list. */
	private List siccMarcaList;
	
	/** The sicc canal list. */
	private List siccCanalList;
	
	/** The programa cupon list. */
	private List programaCuponList;
	
	/** The concursos list. */
	private LabelValue[] concursosList;
	
	/** The habilita conc. */
	private boolean habilitaConc;
	
	/** The habilita tipo. */
	private boolean habilitaTipo;
	
	/** The habilitacodi. */
	private boolean habilitacodi;
	
	/** The consultora list. */
	private LabelValue[] consultoraList;
	
	/** The habilitaConsu. */
	private boolean habilitaConsu;
	
	/** The consultora. */
	private String[] consultoras;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPremiosEntregadosForm form = new ReporteINCPremiosEntregadosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			if(consultoras != null && consultoras.length > 0)
				return "reporteINCPremiosConsultoraXLS";
			else
				return "reporteINCPremiosEntregadosXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(formatoReporte))||("VPDF".equals(formatoReporte)))
			if(consultoras != null && consultoras.length > 0)
				return "reporteINCPremiosConsultora";
			else
				return "reporteINCPremiosEntregados";
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {		
		ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
		form.setTitulo(this.mPantallaPrincipalBean.getResourceMessage("reporteINCPremiosEntregadosForm.titulo"));
		if (this.habilitaConc) {
			form.setCodigoTipoPrograma(null);
			form.setCodigoConsultora(null);
			form.setCodigoSAP("");
			form.setCampoFiltro("C");
		}
		if (this.habilitaTipo) {
			form.setCodigoConcurso(null);
			form.setCodigoConsultora(null);
			form.setCodigoSAP("");
			form.setCampoFiltro("P");
		}
		if (this.habilitacodi) {
			form.setCodigoConcurso(null);
			form.setCodigoConsultora(null);
			form.setCodigoTipoPrograma(null);
			form.setCampoFiltro("S");
			 
		}
		if (this.habilitaConsu) {
			form.setCodigoConcurso(null);
			form.setCodigoTipoPrograma(null);
			form.setCodigoSAP("");
			form.setCampoFiltro("K");
		}
		
		formatoReporte = form.getFormatoExportacion();
		concursos = form.getCodigoConcurso();
		programas = form.getCodigoTipoPrograma();
		consultoras = form.getCodigoConsultora();
		
		params.put("fechaInicioFacturacion", DateUtil.convertDateToString(DateUtil.getDatePattern(), form.getFechaInicioFacturacion()));
		params.put("fechaFinFacturacion", DateUtil.convertDateToString(DateUtil.getDatePattern(), form.getFechaFinFacturacion()));
		params.put("concursos", concursos);
		params.put("programas", programas);
		params.put("consultoras", consultoras);
		params.put("codigoSAP", form.getCodigoSAP());
		params.put("campoFiltro", form.getCampoFiltro());
		params.put("NroReporte", " ");
		params.put("titulo",form.getTitulo());
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug(">>ReporteINCPremiosEntregadosAction - setViewAtributes");
		
		this.mostrarReporteXLS = true;
		
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteINCPremiosEntregadosForm f = (ReporteINCPremiosEntregadosForm) this.formReporte;
		
		
		formatoReporte = f.getFormatoExportacion();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		
		setConcursosList(aSvc.getConcursos2ByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, "T"));
		setConsultoraList(aSvc.getConcursos2ByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, "T"));
		
		Map criteria = new HashMap();
		criteria.put("condicionTodos", "T");
		
		setProgramaCuponList(reporteService.getTiposPrograma(criteria));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date hoy = new Date(System.currentTimeMillis());
		
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		
		f.setFechaInicioFacturacion(sdf.parse(sdf.format(hoy)));
		f.setFechaFinFacturacion(sdf.parse(sdf.format(hoy)));
		
		
		f.setBeforeExecuteReporte(true);
		log.debug("Todo Ok: Redireccionando");
	}
	
	/**
	 * Change status.
	 *
	 * @param val the val
	 */
	public void changeStatus(ValueChangeEvent val){
		try {
			log.debug(">>changeStatus...");
			log.debug(">>val: "+ val.getNewValue().toString());
			
			//this.habilitaConc = true;
			this.habilitaTipo = false;
			this.habilitacodi = false;
			this.habilitaConsu = false;
			
			ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
			form.setCodigoTipoPrograma(null);
			form.setCodigoConsultora(null);
			form.setCodigoSAP("");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void changeStatusTipo(ValueChangeEvent val){
		try {
			log.debug(">>changeStatusTipo...");
			log.debug(">>val: "+ val.getNewValue().toString());
			
			this.habilitaConc = false;
			//this.habilitaTipo = true;
			this.habilitacodi = false;
			this.habilitaConsu = false;
			
			ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
			form.setCodigoConcurso(null);
			form.setCodigoConsultora(null);
			form.setCodigoSAP("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeStatusCodi(ValueChangeEvent val){
		try {
			log.debug(">>changeStatusCodi...");
			log.debug(">>val: "+ val.getNewValue().toString());
			
			this.habilitaConc = false;
			this.habilitaTipo = false;
			//this.habilitacodi = true;
			this.habilitaConsu = false;
			
			ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
			form.setCodigoConcurso(null);
			form.setCodigoConsultora(null);
			form.setCodigoTipoPrograma(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeStatusConsu(ValueChangeEvent val){
		try {
			log.debug(">>changeStatusConsu...");
			log.debug(">>val: "+ val.getNewValue().toString());	
			
			this.habilitaConc = false;
			this.habilitaTipo = false;
			this.habilitacodi = false;
			//this.habilitaConsu = true;
			
			ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
			form.setCodigoConcurso(null);
			form.setCodigoTipoPrograma(null);
			form.setCodigoSAP("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteINCPremiosEntregadosService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
		if(form.getFechaInicioFacturacion()!=null && form.getFechaFinFacturacion()!=null){
			if(form.getFechaInicioFacturacion().compareTo(form.getFechaFinFacturacion())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}
	
	public void loadConcursosPorMarca(ValueChangeEvent val){
		try {
			log.debug(">>loadConcursosPorMarca...");
			log.debug(">>val: "+val.getNewValue().toString());
			String valor = val.getNewValue().toString();
			
			ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			log.debug(">>>>>> codigoMarca: " + valor);
			log.debug(">>>>>> codigoCanal: " + form.getCodigoCanal());
						
			setConcursosList(aSvc.getConcursos2ByPaisMarcaCanal(form.getCodigoPais(), valor, form.getCodigoCanal(), "T"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadConcursosPorCanal(ValueChangeEvent val){
		try {
			log.debug(">>loadConcursosPorCanal...");
			log.debug(">>val: "+val.getNewValue().toString());
			String valor = val.getNewValue().toString();
			
			ReporteINCPremiosEntregadosForm form = (ReporteINCPremiosEntregadosForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			log.debug(">>>>>> codigoMarca: " + form.getCodigoMarca());
			log.debug(">>>>>> codigoCanal: " + valor);
						
			setConcursosList(aSvc.getConcursos2ByPaisMarcaCanal(form.getCodigoPais(), form.getCodigoMarca(), valor, "T"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the formato reporte.
	 *
	 * @return the formato reporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * Sets the formato reporte.
	 *
	 * @param formatoReporte the new formato reporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * Gets the concursos.
	 *
	 * @return the concursos
	 */
	public String[] getConcursos() {
		return concursos;
	}

	/**
	 * Sets the concursos.
	 *
	 * @param concursos the new concursos
	 */
	public void setConcursos(String[] concursos) {
		this.concursos = concursos;
	}

	/**
	 * Gets the programas.
	 *
	 * @return the programas
	 */
	public String[] getProgramas() {
		return programas;
	}

	/**
	 * Sets the programas.
	 *
	 * @param programas the new programas
	 */
	public void setProgramas(String[] programas) {
		this.programas = programas;
	}

	/**
	 * Gets the sicc marca list.
	 *
	 * @return the sicc marca list
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * Sets the sicc marca list.
	 *
	 * @param siccMarcaList the new sicc marca list
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * Gets the sicc canal list.
	 *
	 * @return the sicc canal list
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * Sets the sicc canal list.
	 *
	 * @param siccCanalList the new sicc canal list
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * Gets the programa cupon list.
	 *
	 * @return the programa cupon list
	 */
	public List getProgramaCuponList() {
		return programaCuponList;
	}

	/**
	 * Sets the programa cupon list.
	 *
	 * @param programaCuponList the new programa cupon list
	 */
	public void setProgramaCuponList(List programaCuponList) {
		this.programaCuponList = programaCuponList;
	}

	/**
	 * Gets the concursos list.
	 *
	 * @return the concursos list
	 */
	public LabelValue[] getConcursosList() {
		return concursosList;
	}

	/**
	 * Sets the concursos list.
	 *
	 * @param concursosList the new concursos list
	 */
	public void setConcursosList(LabelValue[] concursosList) {
		this.concursosList = concursosList;
	}

	/**
	 * Checks if is habilita conc.
	 *
	 * @return true, if is habilita conc
	 */
	public boolean isHabilitaConc() {
		return habilitaConc;
	}

	/**
	 * Sets the habilita conc.
	 *
	 * @param habilitaConc the new habilita conc
	 */
	public void setHabilitaConc(boolean habilitaConc) {
		this.habilitaConc = habilitaConc;
	}

	/**
	 * Checks if is habilita tipo.
	 *
	 * @return true, if is habilita tipo
	 */
	public boolean isHabilitaTipo() {
		return habilitaTipo;
	}

	/**
	 * Sets the habilita tipo.
	 *
	 * @param habilitaTipo the new habilita tipo
	 */
	public void setHabilitaTipo(boolean habilitaTipo) {
		this.habilitaTipo = habilitaTipo;
	}

	/**
	 * Checks if is habilitacodi.
	 *
	 * @return true, if is habilitacodi
	 */
	public boolean isHabilitacodi() {
		return habilitacodi;
	}

	/**
	 * Sets the habilitacodi.
	 *
	 * @param habilitacodi the new habilitacodi
	 */
	public void setHabilitacodi(boolean habilitacodi) {
		this.habilitacodi = habilitacodi;
	}

	/**
	 * @return the consultoraList
	 */
	public LabelValue[] getConsultoraList() {
		return consultoraList;
	}

	/**
	 * @param consultoraList the consultoraList to set
	 */
	public void setConsultoraList(LabelValue[] consultoraList) {
		this.consultoraList = consultoraList;
	}

	/**
	 * @return the habilitaConsu
	 */
	public boolean isHabilitaConsu() {
		return habilitaConsu;
	}

	/**
	 * @param habilitaConsu the habilitaConsu to set
	 */
	public void setHabilitaConsu(boolean habilitaConsu) {
		this.habilitaConsu = habilitaConsu;
	}

	/**
	 * @return the consultoras
	 */
	public String[] getConsultoras() {
		return consultoras;
	}

	/**
	 * @param consultoras the consultoras to set
	 */
	public void setConsultoras(String[] consultoras) {
		this.consultoras = consultoras;
	}
}
