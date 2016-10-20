package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
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
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCPuntajeBancoSueniosForm;

@ManagedBean
@SessionScoped
public class ReporteINCPuntajeBancoSueniosAction extends
	BaseReporteAbstractAction {

    private static final long serialVersionUID = 1L;
    private String formatoReporte;
    private String tipoReporte;
    private List siccMarcaList;
    private List siccCanalList;
    private LabelValue[] siccConcursoList;

    protected BaseReporteForm devuelveFormReporte() throws Exception {
	ReporteINCPuntajeBancoSueniosForm reportForm = new ReporteINCPuntajeBancoSueniosForm();
	return reportForm;
    }

    @Override
    protected String devuelveNombreReporte() throws Exception {
	ReporteINCPuntajeBancoSueniosForm reporteINCForm = (ReporteINCPuntajeBancoSueniosForm) this.formReporte;
		
	if ("XLS".equals(this.getFormatoExportacion()))
	    if (reporteINCForm.getTipo().equals("1")) // 1 = Resumen
		// GGCR = General de Ganadoras Concurso de Recomendación
		return "reporteINCPuntajeBancoSueniosGGCRXLS";
	    else
		// 2 = Detalle
		// GDCR = Ganadoras detallado por consultora referente
		return "reporteINCPuntajeBancoSueniosGDCRXLS";
	else
	    return "";
    }

    @Override
    protected String devuelveNombreSubReporte() throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Map prepareParameterMap(Map params) throws Exception {
	ReporteINCPuntajeBancoSueniosForm reporteINCForm = (ReporteINCPuntajeBancoSueniosForm) this.formReporte;
	ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

	this.formatoReporte = reporteINCForm.getFormatoExportacion();
	
	this.tipoReporte = reporteINCForm.getTipo();

	MantenimientoINCHabilitacionConcursoCargaPuntajeService concursoService = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
	MantenimientoMAEClienteService mantenimientoMAEClienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

	List listConcursoLideres = concursoService
		.getListConcursosVigentesCerrados();
	String oidConcurso = reporteService
		.getOidConcursoByNumConc(reporteINCForm.getCodigoConcurso());

	String numeroConcurso = getNumeroConcurso(listConcursoLideres,
		oidConcurso);

	String descConcu = StringUtils.substring(numeroConcurso,
		numeroConcurso.indexOf("-") + 1, numeroConcurso.length())
		.trim();

	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	
	Map map = new HashMap();
	map.put("codigoPais", pais.getCodigo());

	String titulo = "";

	if (tipoReporte.equals("1")) {

	    titulo = this.mPantallaPrincipalBean
		    .getReportResourceMessage("reporteINCPuntajeBancoSueniosGGCRXLSForm.titulo");
	    params.put("nombreConcurso", descConcu);
	} else {

	    titulo = this.mPantallaPrincipalBean
		    .getReportResourceMessage("reporteINCPuntajeBancoSueniosGDCRXLSForm.titulo");
	    params.put("oidCanal", mantenimientoMAEClienteService
		    .getOidCanal(reporteINCForm.getCodigoCanal()));	  
	    params.put("oidMarca", mantenimientoMAEClienteService
		    .getOidMarca(reporteINCForm.getCodigoMarca()));
	    params.put("oidPais", Long.valueOf(mantenimientoMAEClienteService
		    .getOidPais(map)));

	}

	params.put("concurso", oidConcurso);
	params.put("NroReporte", " ");
	params.put("titulo", titulo.concat(" ").concat("N°").concat(" ")
		.concat(reporteINCForm.getCodigoConcurso()));

	return params;
    }

    @Override
    protected void setViewAtributes() throws Exception {
	this.mostrarReportePDF = false;
	this.mostrarReporteXLS = true;

	ReporteINCPuntajeBancoSueniosForm form = (ReporteINCPuntajeBancoSueniosForm) this.formReporte;
	ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
	Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	setSiccMarcaList(reporteService.getMarcas());
	setSiccCanalList(reporteService.getCanalesByCodigoISO(usuario
		.getIdioma().getCodigoISO()));
	form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	obtenerConcursos(form);
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

    public void setSiccCanalList(List siccCanalList) {
	this.siccCanalList = siccCanalList;
    }

    public List getSiccCanalList() {
	return siccCanalList;
    }

    public void setSiccConcursoList(LabelValue[] siccConcursoList) {
	this.siccConcursoList = siccConcursoList;
    }

    public LabelValue[] getSiccConcursoList() {
	return siccConcursoList;
    }

    public void obtenerConcursosByPaisMarcaCanal(ValueChangeEvent event) {
	String codigoMarca = (String) event.getNewValue();
	ReporteINCPuntajeBancoSueniosForm form = (ReporteINCPuntajeBancoSueniosForm) this.formReporte;
	form.setCodigoMarca(codigoMarca);
	obtenerConcursos(form);

    }

    public void obtenerConcursosByPaisCanalMarca(ValueChangeEvent event) {
	String codigoCanal = (String) event.getNewValue();
	ReporteINCPuntajeBancoSueniosForm form = (ReporteINCPuntajeBancoSueniosForm) this.formReporte;
	form.setCodigoCanal(codigoCanal);
	obtenerConcursos(form);
    }

    public void obtenerConcursos(ReporteINCPuntajeBancoSueniosForm form) {
	AjaxService aSvc = (AjaxService) getBean("ajaxService");
	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(pais.getCodigo(),
		form.getCodigoMarca(), form.getCodigoCanal(),
		""));
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