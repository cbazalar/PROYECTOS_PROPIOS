package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDFacturadosConsultoraForm;

@ManagedBean
@SessionScoped
public class ReportePEDFacturadosConsultoraAction extends BaseReporteAbstractAction
			implements Serializable{

	private static final long serialVersionUID = 3917734613813124678L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDFacturadosConsultoraForm reporteForm = new ReportePEDFacturadosConsultoraForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equals("XLS", this.formatoExportacion))		
			return "reportePEDFacturadosConsultoraXLS";
		else 
			return "reporteMaestroHorizontal";	
		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reportePEDFacturadosConsultoraPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePEDFacturadosConsultoraForm f = (ReportePEDFacturadosConsultoraForm) this.formReporte;

		f.setFecha(DateUtil.convertDateToString(f.getFechaDate()));
		
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("fecha", f.getFecha());
		params.put("titulo", this.getResourceMessage("reportePEDFacturadosConsultoraForm.titulo"));
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		ReportePEDFacturadosConsultoraForm f = (ReportePEDFacturadosConsultoraForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String periodo=service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPeriodo(periodo);
		f.setFecha(DateUtil.convertDateToString("dd/MM/yyyy", new Date()));
		f.setFechaDate(DateUtil.convertStringToDate(f.getFecha()));		
	}

}
