package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDDetallesPedidosxPedidoForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDFacturaDetalleCampaniaValorForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReportePEDDetallesPedidosxPedidoAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 4019351111130150606L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDDetallesPedidosxPedidoForm reporteForm = new ReportePEDDetallesPedidosxPedidoForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equals("XLS", this.formatoExportacion))	
			return "reportePEDDetallesPedidosxPedidoXLS";
		else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePEDDetallesPedidosxPedidoForm f=(ReportePEDDetallesPedidosxPedidoForm)this.formReporte;
		params.put("nroBoleta", f.getNroBoleta());
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		ReportePEDDetallesPedidosxPedidoForm f=(ReportePEDDetallesPedidosxPedidoForm)this.formReporte;
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
	}

}
