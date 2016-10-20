package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.let.form.ReporteLETLideresForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteLETLideresAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETLideresForm form  = new ReporteLETLideresForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (("XLS".equals(this.formatoReporte)))
			return "reporteLETLideresXLS";
		else
			return "";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug("ReporteLETLideresAction ::::: prepareParameterMap");
		
		ReporteLETLideresForm f = (ReporteLETLideresForm)this.formReporte;
				
		this.formatoReporte = f.getFormatoExportacion();
		
		params.put("codigoPais" ,f.getCodigoPais());
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("ReporteLETLideresAction - setViewAttributes");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		ReporteLETLideresForm f = (ReporteLETLideresForm) this.formReporte;
		
		f.setCodigoPais(pais.getCodigo());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
     	criteria.put("estado", Constants.ESTADO_ACTIVO);		
        
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ProcesoLECCargaDatosExcelService servicePEJ = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
				
		f.setCodigoPeriodoInicio(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodoFin(controlFacturacion.getCodigoPeriodo());
						
		criteria.put("campana", controlFacturacion.getCodigoPeriodo());
		List programas = servicePEJ.getPrograma(criteria);
		
		if(programas!=null && programas.size()>0)
		{
			Base tmp=(Base)programas.get(0);
			f.setCodigoPrograma(tmp.getCodigo());
			f.setDescripcionPrograma(tmp.getDescripcion());
		}
	}
}