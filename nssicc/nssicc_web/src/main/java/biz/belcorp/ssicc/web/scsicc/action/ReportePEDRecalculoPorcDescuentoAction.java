package biz.belcorp.ssicc.web.scsicc.action;

import java.math.BigDecimal;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDRecalculoPorcDescuentoForm;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERControlAsistenciaForm;

@ManagedBean
@SessionScoped
public class ReportePEDRecalculoPorcDescuentoAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 8171451802650340148L;
	
	private boolean condicionDsctoPlano; 
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDRecalculoPorcDescuentoForm form = new ReportePEDRecalculoPorcDescuentoForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (StringUtils.equals(this.formatoExportacion, "XLS"))
			return "reportePEDRecalculoPorcDescuentoXLS";
		else
		   return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (StringUtils.equals(this.formatoExportacion, "PDF"))
			return "reportePEDRecalculoPorcDescuentoPDF";
		else
		   return "reporteMaestroHorizontal";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePEDRecalculoPorcDescuentoForm f = (ReportePEDRecalculoPorcDescuentoForm) this.formReporte;
		if(this.condicionDsctoPlano)
			f.setCondicionDsctoPlano("1");
		else
			f.setCondicionDsctoPlano("0");
		
		String fechafactura=DateUtil.convertDateToString(f.getFechaFacturaDate());
		String nuevoDescuento = f.getPorcentajeDescuento();
		BigDecimal nuevoDescuentoDecirmal = new BigDecimal(nuevoDescuento);
		String coindicionDescuento = f.getCondicionDsctoPlano();
		int valor = Integer.parseInt(f.getCondicionDsctoPlano());
		params.put("nroDocumentoIdentidad", "");
		params.put("fechaFactura", "");

		if(StringUtils.isNotBlank(f.getNroDocumento())){
			String nroDocuIdentidad="AND num_docu_iden = '"+f.getNroDocumento()+"'";
			params.put("nroDocumentoIdentidad", nroDocuIdentidad);

		}
		if(StringUtils.isNotBlank(fechafactura)){
			String fefactura=" AND TO_CHAR(sc.fec_fact,'dd/mm/yyyy')= '"+fechafactura+"'";
			params.put("fechaFactura", fefactura);
		}
		
		params.put("nroFactura", f.getNroFactura());
		params.put("condDsctoPlano",valor );
		params.put("nuevoDscto", nuevoDescuentoDecirmal);
		params.put("titulo",this.getResourceMessage("reportePEDRecalculoPorcDescuentoForm.titulo"));
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS=true;
		this.mostrarReportePDF=true;
		
	}
	
	

	/**
	 * @return the condicionDsctoPlano
	 */
	public boolean isCondicionDsctoPlano() {
		return condicionDsctoPlano;
	}

	/**
	 * @param condicionDsctoPlano the condicionDsctoPlano to set
	 */
	public void setCondicionDsctoPlano(boolean condicionDsctoPlano) {
		this.condicionDsctoPlano = condicionDsctoPlano;
	}
	
	

}
