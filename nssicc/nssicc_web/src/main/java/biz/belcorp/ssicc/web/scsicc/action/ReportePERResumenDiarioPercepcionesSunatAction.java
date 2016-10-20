package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENRegistroVentasIngresosForm;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERResumenDiarioPercepcionesSunatForm;

@ManagedBean
@SessionScoped
public class ReportePERResumenDiarioPercepcionesSunatAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -7306049823132250886L;
	
	private Date fechaGeneraDate;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERResumenDiarioPercepcionesSunatForm f = new ReportePERResumenDiarioPercepcionesSunatForm();
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
		ReportePERResumenDiarioPercepcionesSunatForm f = (ReportePERResumenDiarioPercepcionesSunatForm) this.formReporte;	
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		
		String nombrePer = serviceGen.getParametroPais(f.getCodigoPais(),Constants.SISTEMA_PER, Constants.VALOR_PER_REPORTE_SUNAT);
		String fechaGenera=DateUtil.convertDateToString(f.getFechaGeneracionDate());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");      
        String fechagenera = sdf.format(f.getFechaGeneracionDate());  
        String titulo="";        
        
        params.put("codigoPais",f.getCodigoPais());
        params.put("fechaGeneraNombre", fechagenera);
        params.put("nombreReportePer", nombrePer);
        params.put("fechaGeneracion", fechaGenera);
        params.put("tituloReporteTxt", titulo);
        
        
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ReportePERResumenDiarioPercepcionesSunatForm f = (ReportePERResumenDiarioPercepcionesSunatForm) this.formReporte;	
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setFechaGeneracion("");
		this.mostrarReporteOTXT = true;
		this.mostrarReporteOCSV = true;		
	}
	
	protected String devuelveBeanReporteService() {
		return "reportes.reportePERResumenDiarioPercepcionesSunatService";
	}
	
	public void setearValores() throws Exception {
		ReportePERResumenDiarioPercepcionesSunatForm f = (ReportePERResumenDiarioPercepcionesSunatForm) this.formReporte;	
		f.setFormatoExportacion(this.getFormatoExportacion());
		f.setFechaGeneracionDate(this.fechaGeneraDate);	
		
	}

	/**
	 * @return the fechaGeneraDate
	 */
	public Date getFechaGeneraDate() {
		return fechaGeneraDate;
	}

	/**
	 * @param fechaGeneraDate the fechaGeneraDate to set
	 */
	public void setFechaGeneraDate(Date fechaGeneraDate) {
		this.fechaGeneraDate = fechaGeneraDate;
	}
	
	

}
