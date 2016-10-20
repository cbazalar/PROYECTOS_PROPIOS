package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCConsolidadoCuentaCorrienteContableForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm;

@ManagedBean
@SessionScoped
public class ReporteCCCMediosMagneticosCuentaCorrienteBimensualAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 662436107750934915L;
	
//	private String tipoReporte;
//	private List siccSociedadList = new ArrayList();

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm form = new ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm();
		return form;
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
		ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm f = (ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm) this.formReporte;
		
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
	
		String fecha1,fecha2;
		fecha1 = DateUtil.getDate(f.getFechaInicialD());
		fecha2 = DateUtil.getDate(f.getFechaFinalD());
		f.setFechaInicial(fecha1);
		f.setFechaFinal(fecha2);
		params.put("codigoUsuario", usuario.getCodigo());
		params.put("fechaInicial", f.getFechaInicial());
		params.put("fechaFinal", f.getFechaFinal());
		log.debug("Los parametros del Generar en el executeProcess son: " + params.toString());
		return params;
	}
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm f = (ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm) this.formReporte;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF=false;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaInicial(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaInicialD(new Date(System.currentTimeMillis()));

		f.setFechaFinal(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaFinalD(new Date(System.currentTimeMillis()));
		
	}

	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCCCMediosMagneticosCuentaCorrienteBimensualService";
	}
	
	
}