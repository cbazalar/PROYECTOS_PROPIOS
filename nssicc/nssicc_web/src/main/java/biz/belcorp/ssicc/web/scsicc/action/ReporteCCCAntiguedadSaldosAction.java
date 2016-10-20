package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;


import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCAntiguedadSaldosForm;


/**
 * @author <a href="mailto:danielsan1289@gmail.com">Danny Santa Cruz Rojas</a>
 */
@ManagedBean
@SessionScoped
public class ReporteCCCAntiguedadSaldosAction extends BaseReporteAbstractAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getReporteFileName()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteCCCAntiguedadSaldosForm form = new ReporteCCCAntiguedadSaldosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() {
		ReporteCCCAntiguedadSaldosForm f = (ReporteCCCAntiguedadSaldosForm)this.formReporte;
		if(f.getTipoReporte().equals("C"))
			return "reporteCCCAntiguedadSaldoConsolidadoXLS";
		
		return null;
	}
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Map prepareParameterMap(Map params)  throws Exception{
		
			ReporteCCCAntiguedadSaldosForm f = (ReporteCCCAntiguedadSaldosForm) formReporte;
			
			Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
			
			String fecha1;
			fecha1 = DateUtil.getDate(f.getFechaCorteD());
			f.setFechaCorte(fecha1);
			
			params.put("codigoUsuario", usuario.getLogin());
			params.put("fechaFinal",f.getFechaCorte());
			params.put("fechaCorte",f.getFechaCorte());
			log.debug("Los parametros del Generar en el executeProcess son: " + params.toString());
			
			
			return params;
	}
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteCCCAntiguedadSaldosForm f = (ReporteCCCAntiguedadSaldosForm) this.formReporte;
		this.mostrarReporteOCSV = false;
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS = true;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaCorte(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaCorteD(new Date(System.currentTimeMillis()));
	}
	
	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCCCAntiguedadSaldosService";
	}
	
	public void mostrarTipoReporte(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		
		if(valor.equals("C")){
			this.mostrarReporteOCSV = false;
			this.mostrarReporteXLS = true;
		}else if(valor.equals("D")){
			this.mostrarReporteOCSV = true;
			this.mostrarReporteXLS = false;						
		}
	}

}
