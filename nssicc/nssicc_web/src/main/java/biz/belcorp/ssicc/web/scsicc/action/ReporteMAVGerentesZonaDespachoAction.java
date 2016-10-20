// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAVGerentesZonaDespachoForm;

/**
 * @author César Estrada
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteMAVGerentesZonaDespachoAction extends
		BaseReporteAbstractAction implements Serializable {

	private String formatoExportacion;
	private List siccMarcaList;
	private List siccCanalList;


	
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
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


	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAVGerentesZonaDespachoForm reporteForm = new ReporteMAVGerentesZonaDespachoForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		return "reporteMAVGerenteZonaXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	
	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		log.debug("reporteMAVGerentesZonaDespachoActrion - setViewAttributes");


		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		ReporteMAVGerentesZonaDespachoForm f = (ReporteMAVGerentesZonaDespachoForm) this.formReporte;
		f.reset();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		f.setCodigoPais(pais.getCodigo());
		siccMarcaList=interfazSiCCService.getMarcas();
		
		siccCanalList=interfazSiCCService
				.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		f.getIdioma().setCodigoISO(mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		


		log.debug("Todo Ok: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteMAVGerentesZonaDespachoForm f = (ReporteMAVGerentesZonaDespachoForm) this.formReporte;
		
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");

		//params.put("formatoReporte",f.getFormatoExportacion());
		//this.formatoExportacion = f.getFormatoExportacion();
		
		params.put("formatoExportacion",formatoExportacion);
		params.put("codigoPais",f.getCodigoPais());
		params.put("oidPais",reporteService.getOidString("getOidPaisByCodigoPais", params));
		
		if ((!f.getCodigoMarca().equals("Todos"))&&(f.getCodigoMarca() != null)){
			params.put("codigoMarca",f.getCodigoMarca());
			params.put("oidMarca",reporteService.getOidString("getOidMarcaByCodigoMarca", params));
		}else{
			params.put("codigoMarca","");
			params.put("oidMarca","");
		}
		
		if ((!f.getCodigoCanal().equals("Todos"))&&(f.getCodigoCanal() != null)){
			params.put("codigoCanal",f.getCodigoCanal());
			params.put("oidCanal",reporteService.getOidString("getOidCanalByCodigoCanal", params));
	
		}else{
			params.put("codigoCanal","");
			params.put("oidCanal", "");
		}
		
		f.setFormatoExportacion(formatoExportacion);
		return params;

	}
}