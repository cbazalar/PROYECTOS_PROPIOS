package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCDiasCarteraForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@ManagedBean
@SessionScoped
public class ReporteCCCDiasCarteraAction  extends BaseReporteAbstractAction{
	
	
	private static final long serialVersionUID = 1303487648269534442L;
	
	private String formatoReporte;
	private List siccSociedadList;
	private List siccMesesList;
	private Boolean cambioFiltroMensual;
	private Boolean cambioFiltroAcumulado;

	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDiasCarteraForm reporteForm = new ReporteCCCDiasCarteraForm();
		return reporteForm;
	}
	
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteCCCDiasCarteraForm f = (ReporteCCCDiasCarteraForm) this.formReporte;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);
		
		this.siccSociedadList = svc.getSociedadesByCodigoPais(codpais);
		
		Base[] mes = new Base[12];
		ArrayList resultado = new ArrayList();
		for (int i = 0; i < 12; i++) {
			mes[i] = new Base();
			String previo = StringUtils.leftPad("" + i , 2, "0");
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
			Calendar c = Calendar.getInstance();
			c.set(2004, i, 1);
			String fecha = sdf.format(c.getTime());
			mes[i].setCodigo(previo);
			mes[i].setDescripcion(StringUtils.capitalize(fecha));
			resultado.add(mes[i]);
		}		
		this.siccMesesList=resultado;	
		this.cambioFiltroAcumulado=false;
		this.cambioFiltroMensual=false;
		
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteCCCDiasCarteraForm f = (ReporteCCCDiasCarteraForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();
		
		
		//Seteamos los parametros adicionales
		//fechaInicio, fechaFin
		String fechaInicio = "";
		String fechaFin = "";
		
		if(StringUtils.equals(f.getCodigoOpcion(), Constants.CCC_REPORTE_MENSUAL))
		{
			fechaInicio = String.format("%s/%s/%s", "01", StringUtils.leftPad("" + (Integer.parseInt(f.getMesInicial()) + 1) , 2, "0"), f.getAnyoInicial());
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(f.getAnyoFinal()));
			calendar.set(Calendar.MONTH, Integer.parseInt(f.getMesFinal()));			
			int ultimoDia = calendar.getActualMaximum(Calendar.DATE);
						
			fechaFin = String.format("%s/%s/%s", StringUtils.leftPad("" + ultimoDia , 2, "0"), StringUtils.leftPad("" + (Integer.parseInt(f.getMesFinal()) + 1) , 2, "0"), f.getAnyoFinal());
		}
		else if(StringUtils.equals(f.getCodigoOpcion(), Constants.CCC_REPORTE_ACUMULADO))
		{		
			int periodo = Integer.parseInt(f.getCodigoGrupoMeses());
			
			Calendar calendarInicio = Calendar.getInstance();
			calendarInicio.add(Calendar.MONTH, -periodo);
			
			int mesInicio = calendarInicio.get(Calendar.MONTH) + 1;
			int anyoInicio = calendarInicio.get(Calendar.YEAR);
			
			fechaInicio = String.format("%s/%s/%s", "01", StringUtils.leftPad("" + mesInicio , 2, "0"), Integer.toString(anyoInicio));
								
			Calendar calendarFinal = Calendar.getInstance();
			calendarFinal.add(Calendar.MONTH, -1);
			int ultimoDiaFin = calendarFinal.getActualMaximum(Calendar.DATE);
			int mesFin = calendarFinal.get(Calendar.MONTH);
			int anyoFin = calendarFinal.get(Calendar.YEAR);
						
			fechaFin = String.format("%s/%s/%s", StringUtils.leftPad("" + ultimoDiaFin , 2, "0"), StringUtils.leftPad("" + (mesFin + 1) , 2, "0"), Integer.toString(anyoFin));
		}
		
		params.put("fechaInicio", fechaInicio);
		params.put("fechaFin", fechaFin);
		
		return params;		
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		if ("XLS".equals(formatoReporte))
		   return "reporteCCCDiasCarteraDXLS";
		else
			return " ";			
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";					
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCDiasCarteraService";
	}	
	
	public void loadFiltro(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadFiltro");
		}
		
		ReporteCCCDiasCarteraForm f = (ReporteCCCDiasCarteraForm) this.formReporte;
		String valor = (String) val.getNewValue();
		if (valor.equals("M")) {
			this.cambioFiltroMensual=true;
			this.cambioFiltroAcumulado=false;			
		} else if (valor.equals("A")) {
			this.cambioFiltroMensual=false;
			this.cambioFiltroAcumulado=true;
		}else{
			this.cambioFiltroMensual=false;
			this.cambioFiltroAcumulado=false;
		}
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}


	public List getSiccSociedadList() {
		return siccSociedadList;
	}


	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}


	public List getSiccMesesList() {
		return siccMesesList;
	}


	public void setSiccMesesList(List siccMesesList) {
		this.siccMesesList = siccMesesList;
	}


	public Boolean getCambioFiltroMensual() {
		return cambioFiltroMensual;
	}


	public void setCambioFiltroMensual(Boolean cambioFiltroMensual) {
		this.cambioFiltroMensual = cambioFiltroMensual;
	}


	public Boolean getCambioFiltroAcumulado() {
		return cambioFiltroAcumulado;
	}


	public void setCambioFiltroAcumulado(Boolean cambioFiltroAcumulado) {
		this.cambioFiltroAcumulado = cambioFiltroAcumulado;
	}
	

	
}
