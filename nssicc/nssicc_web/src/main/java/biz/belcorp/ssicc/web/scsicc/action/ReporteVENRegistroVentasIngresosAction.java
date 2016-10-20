package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENRegistroVentasIngresosForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * 
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli Silva</a>
 *                        
 */
@ManagedBean
@SessionScoped
public class ReporteVENRegistroVentasIngresosAction extends BaseReporteAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -138634749929917431L;
	private String formatoReporte;
	private String tipoReporte;
	private String codigoPeriodoInformar;
	private String codigoPeriodoEnviar;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENRegistroVentasIngresosForm f = new ReporteVENRegistroVentasIngresosForm();
		return f;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
       log.debug("ReporteVENRegistroVentasIngresosAction - prepareParameterMap");
		
		ReporteVENRegistroVentasIngresosForm f = (ReporteVENRegistroVentasIngresosForm)this.formReporte;			
		this.formatoReporte = f.getFormatoExportacion();
		this.tipoReporte = f.getTipoReporteAMostrar();
		this.setGenerateTabsXLS(true);
				
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoPeriodo",f.getCodigoPeriodoInformar()+"00");
		params.put("codigoPeriodoInformar",f.getCodigoPeriodoInformar());
		params.put("codigoPeriodoEnviar",f.getCodigoPeriodoEnviar());
		params.put("tipoReporte",this.tipoReporte);
		
		String titulo = this.getReportResourceMessage("reporteVENRegistroVentasIngresosForm.title");
		params.put("titulo", titulo);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("ReporteVENRegistroVentasIngresosAction - setViewAttributes");
		ReporteVENRegistroVentasIngresosForm f = (ReporteVENRegistroVentasIngresosForm) this.formReporte;	
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setCodigoPeriodoInformar("");
		f.setCodigoPeriodoEnviar("");
		f.setAccion(Constants.NUMERO_CERO);//reiniciar periodo
		this.mostrarReporteOTXT = true;
		this.mostrarReporteOCSV = true;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteVENRegistroVentasIngresosService";
	}

	
	/**
	 * @throws Exception
	 */
	public void setearValores() throws Exception {
		ReporteVENRegistroVentasIngresosForm f = (ReporteVENRegistroVentasIngresosForm) this.formReporte;	
		f.setFormatoExportacion(this.getFormatoExportacion());
		f.setTipoReporteAMostrar(this.tipoReporte);
		f.setCodigoPeriodoEnviar(this.codigoPeriodoEnviar);
		f.setCodigoPeriodoInformar(this.codigoPeriodoInformar);
	}
	
	
	
	
	
	
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the codigoPeriodoInformar
	 */
	public String getCodigoPeriodoInformar() {
		return codigoPeriodoInformar;
	}

	/**
	 * @param codigoPeriodoInformar the codigoPeriodoInformar to set
	 */
	public void setCodigoPeriodoInformar(String codigoPeriodoInformar) {
		this.codigoPeriodoInformar = codigoPeriodoInformar;
	}

	/**
	 * @return the codigoPeriodoEnviar
	 */
	public String getCodigoPeriodoEnviar() {
		return codigoPeriodoEnviar;
	}

	/**
	 * @param codigoPeriodoEnviar the codigoPeriodoEnviar to set
	 */
	public void setCodigoPeriodoEnviar(String codigoPeriodoEnviar) {
		this.codigoPeriodoEnviar = codigoPeriodoEnviar;
	}

	
	
	
	
}