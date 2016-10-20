package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoProvisionIncobrableForm;

@ManagedBean
@SessionScoped
public class ReporteCCCDetalladoProvisionIncobrableAction extends
		BaseReporteAbstractAction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2849324881958776681L;
	private String formatoReporte;
	private String tipoReporte;

	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteCCCDetalladoProvisionIncobrableForm form = new ReporteCCCDetalladoProvisionIncobrableForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		String nombre=null;
		if ("XLS".equals(formatoReporte)) {
			if("CC".equals(tipoReporte)){
				nombre = "reporteCCCDetalladoProvisionIncobrableCampanaXLS";
			}else if("CR".equals(tipoReporte)){
				nombre = "reporteCCCDetalladoProvisionIncobrableRegionXLS";			
			}
		} 
		return nombre;	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCDetalladoProvisionIncobrableForm f = (ReporteCCCDetalladoProvisionIncobrableForm) formReporte;
		tipoReporte = f.getTipoReporte();
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Map criteria = params;
		formatoReporte = f.getFormatoExportacion();
		String fecha1,fecha2;
		fecha1 = DateUtil.getDate(f.getDfechaInicial());
		fecha2 = DateUtil.getDate(f.getDfechaFinal());
		f.setFechaInicial(fecha1);
		f.setFechaFinal(fecha2);
		params.put("tipoReporte", tipoReporte);
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ReporteCCCDetalladoProvisionIncobrableForm f = (ReporteCCCDetalladoProvisionIncobrableForm) formReporte;
//		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
//		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		Map criteriaOperacion = new HashMap();
		
		criteriaOperacion.put("codigoPais",mPantallaPrincipalBean.getCurrentCountry().getCodigo());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));		
		f.setFechaInicial(fecha);
		f.setFechaFinal(fecha);
		f.setDfechaInicial(new Date(System.currentTimeMillis()));
		f.setDfechaFinal(new Date(System.currentTimeMillis()));
				this.mostrarReportePDF=false;
				this.mostrarReporteOCSV=false;
				
	}


	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCCCDetalladoProvisionIncobrableService";
				
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	public void validando(ValueChangeEvent event)
	{
	   
		tipoReporte =  (String) event.getNewValue()==null? "0": (String) event.getNewValue() ;
		if(tipoReporte.equals("CC")  || tipoReporte.equals("CR")){
		 this.mostrarReportePDF=false;
		 this.mostrarReporteOCSV=false;
		 this.mostrarReporteXLS=true;
		}
		if(tipoReporte.equals("DC") || tipoReporte.equals("DM")){
			this.mostrarReportePDF=false;
			 this.mostrarReporteOCSV=true;
			 this.mostrarReporteXLS=false;
		}
		if(tipoReporte.equals("0")){
			this.mostrarReportePDF=false;
			 this.mostrarReporteOCSV=false;
			 this.mostrarReporteXLS=false;
		}
		
	}
}