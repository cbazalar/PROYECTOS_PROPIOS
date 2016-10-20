package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCAntiguedadNotasCreditoForm;

@ManagedBean
@SessionScoped
public class ReporteCCCAntiguedadNotasCreditoAction extends BaseReporteAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1048760642911137657L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCAntiguedadNotasCreditoForm formReporte = new ReporteCCCAntiguedadNotasCreditoForm();
		return formReporte;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCCCAntiguedadNotasCreditoForm f = (ReporteCCCAntiguedadNotasCreditoForm)this.formReporte;
		if(f.getTipoReporte().equals("C"))
			return "reporteCCCAntiguedadNotasCreditoConsolidadoXLS";
		if(f.getTipoReporte().equals("D"))
			return "reporteCCCAntiguedadNotasCreditoDetalladoXLS";
		
		return "";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCAntiguedadNotasCreditoService";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception 
	{
		ReporteCCCAntiguedadNotasCreditoForm f = (ReporteCCCAntiguedadNotasCreditoForm)this.formReporte;
		params.put("fechaDesde", DateUtil.convertDateToString(f.getFechaDesde()));
		params.put("fechaHasta", DateUtil.convertDateToString(f.getFechaHasta()));
				
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		
		ReporteCCCAntiguedadNotasCreditoForm f = (ReporteCCCAntiguedadNotasCreditoForm)this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());		
	}
	

}
