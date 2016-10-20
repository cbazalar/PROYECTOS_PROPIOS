package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCIndicadorGestionIncentivosCDRForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCIndicadorGestionIncentivosCDRAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -7372175913822687524L;
	private String formatoReporte;
	
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccConcursosList;
	private boolean concursoSelected = false;
	private boolean campanaSelected = false;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCIndicadorGestionIncentivosCDRForm form = new ReporteINCIndicadorGestionIncentivosCDRForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteINCIndicadorGestionIncentivosCDRXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(formatoReporte))||("VPDF".equals(formatoReporte)))
			return "reporteINCIndicadorGestionIncentivosCDR";
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {;
		log.debug(">>>ReporteINCIndicadorGestionIncentivosCDRAction - prepareParameterMap");
		
		
		ReporteINCIndicadorGestionIncentivosCDRForm form = (ReporteINCIndicadorGestionIncentivosCDRForm) this.formReporte;
		
		if(form.getCodigoConcurso() != null && form.getCodigoConcurso().length > 0)
			params.put("concursos", obtieneCondicionIN(form.getCodigoConcurso(), "", ""));
		
		formatoReporte = form.getFormatoExportacion();
		form.setTitulo(this.mPantallaPrincipalBean.getResourceMessage("reporteINCIndicadorGestionIncentivosCDRForm.titulo"));
		
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug(">>>ReporteINCIndicadorGestionIncentivosCDRAction - setViewAtributes");

		this.mostrarReporteXLS = true;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteINCIndicadorGestionIncentivosCDRForm f = (ReporteINCIndicadorGestionIncentivosCDRForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		String periodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT);
		
		
		setSiccConcursosList(aSvc.getConcursosVigentesByPaisMarcaCanal( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, "N"));
		
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		
		f.setCodigoPeriodo(periodo);
		f.setPeriodoInicio(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		f.setPeriodoFin(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		
		f.setBeforeExecuteReporte(true);
		this.habilitarSeleccion();
		
	}
	
	public void loadSeleccion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadSeleccion");
		}
		if(StringUtils.isNotEmpty((String)val.getNewValue()) 
				&& StringUtils.isNotBlank((String)val.getNewValue())){
			ReporteINCIndicadorGestionIncentivosCDRForm f = (ReporteINCIndicadorGestionIncentivosCDRForm) this.formReporte;
			f.setTipo((String)val.getNewValue());
		}
		this.habilitarSeleccion();
	}
	
	public void habilitarSeleccion(){
		if(log.isDebugEnabled()){
			log.debug("habilitarSeleccion");
		}
		ReporteINCIndicadorGestionIncentivosCDRForm f = (ReporteINCIndicadorGestionIncentivosCDRForm) this.formReporte;
		if(StringUtils.equals(f.getTipo(), "1")){
			this.setConcursoSelected(false);
			this.setCampanaSelected(true);
		}
		if(StringUtils.equals(f.getTipo(), "2")){
			this.setConcursoSelected(true);
			this.setCampanaSelected(false);
		}
	}
	
	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public LabelValue[] getSiccConcursosList() {
		return siccConcursosList;
	}

	public void setSiccConcursosList(LabelValue[] siccConcursosList) {
		this.siccConcursosList = siccConcursosList;
	}

	public boolean isConcursoSelected() {
		return concursoSelected;
	}

	public void setConcursoSelected(boolean concursoSelected) {
		this.concursoSelected = concursoSelected;
	}

	public boolean isCampanaSelected() {
		return campanaSelected;
	}

	public void setCampanaSelected(boolean campanaSelected) {
		this.campanaSelected = campanaSelected;
	}	
}
