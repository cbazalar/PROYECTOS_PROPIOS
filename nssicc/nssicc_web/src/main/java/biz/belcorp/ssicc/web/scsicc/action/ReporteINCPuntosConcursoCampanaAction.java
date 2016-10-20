package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCPuntosConcursoCampanaForm;

@ManagedBean
@SessionScoped
public class ReporteINCPuntosConcursoCampanaAction extends
	BaseReporteAbstractAction {
    private static final long serialVersionUID = 1L;
    private List siccMarcaList;
    private List siccCanalList;
    private LabelValue[] siccConcursoList;

    @Override
    protected BaseReporteForm devuelveFormReporte() throws Exception {
    	ReporteINCPuntosConcursoCampanaForm reportForm = new ReporteINCPuntosConcursoCampanaForm();
    	return reportForm;
    }

    @Override
    protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.getFormatoExportacion()))
		    return "reporteINCPuntosConcursoCampanaXLS";
		return "";
    }

    @Override
    protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
    }

    @Override
    protected Map prepareParameterMap(Map params) throws Exception {
		ReporteINCPuntosConcursoCampanaForm reporteINCForm = (ReporteINCPuntosConcursoCampanaForm) this.formReporte;
	
		String titulo = this.mPantallaPrincipalBean
			.getReportResourceMessage("reporteINCPuntosConcursoCampanaForm.titulo");
	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoINCHabilitacionConcursoCargaPuntajeService concursoService = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		List listConcursoLideres = concursoService
			.getListConcursosVigentesCerrados();
	
		String oidConcurso = reporteService
			.getOidConcursoByNumConc(reporteINCForm.getCodigoConcurso());
		String numeroConcurso = getNumeroConcurso(listConcursoLideres,
			oidConcurso);
	
		String descConcu = StringUtils.substring(numeroConcurso,
			numeroConcurso.indexOf("-") + 1, numeroConcurso.length())
			.trim();
	
		params.put("numeroConcurso", oidConcurso);
		params.put("NroReporte", " ");
		params.put("nombreConcurso", descConcu);
		params.put(
			"titulo",
			titulo.concat("N°").concat(" ")
				.concat(reporteINCForm.getCodigoConcurso()));
	
		return params;
    }

    @Override
    protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
	
		ReporteINCPuntosConcursoCampanaForm form = (ReporteINCPuntosConcursoCampanaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		setSiccMarcaList(reporteService.getMarcas());
		setSiccCanalList(reporteService.getCanalesByCodigoISO(usuario
			.getIdioma().getCodigoISO()));
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		obtenerConcursos(form);

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

    public LabelValue[] getSiccConcursoList() {
    	return siccConcursoList;
    }

    public void setSiccConcursoList(LabelValue[] siccConcursoList) {
    	this.siccConcursoList = siccConcursoList;
    }

    public void obtenerConcursosByPaisMarcaCanal(ValueChangeEvent event) {
		String codigoMarca = (String) event.getNewValue();
		ReporteINCPuntosConcursoCampanaForm form = (ReporteINCPuntosConcursoCampanaForm) this.formReporte;
		form.setCodigoMarca(codigoMarca);
		this.obtenerConcursos(form);

    }

    public void obtenerConcursosByPaisCanalMarca(ValueChangeEvent event) {
		String codigoCanal = (String) event.getNewValue();
		ReporteINCPuntosConcursoCampanaForm form = (ReporteINCPuntosConcursoCampanaForm) this.formReporte;
		form.setCodigoCanal(codigoCanal);
		obtenerConcursos(form);
    }

    public void obtenerConcursos(ReporteINCPuntosConcursoCampanaForm form) {
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(pais.getCodigo(),
			form.getCodigoMarca(), form.getCodigoCanal(), ""));
    }

    private String getNumeroConcurso(List listConcursos, String oidConcurso) {
		String numeroConcurso = "";
		Iterator it = listConcursos.iterator();
		while (it.hasNext()) {
		    Base concurso = (Base) it.next();
		    if (oidConcurso.equals(concurso.getCodigo())) {
			numeroConcurso = concurso.getDescripcion();
			break;
		    }
		}
		return numeroConcurso;
    }

}
