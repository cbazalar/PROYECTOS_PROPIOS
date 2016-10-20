package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENICEGeneradoProductoForm;

/**
 * @author Gonzalo Huertas
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENICEGeneradoProductoAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteVENICEGeneradoProductoForm form = (ReporteVENICEGeneradoProductoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String reporte= "";
		
		if ("XLS".equals(form.getFormatoExportacion())){
				reporte = "reporteVENICEGeneradoProductoXLS";
		}else{
			reporte = "reporteMaestroHorizontal";
		}
		
		return reporte;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENICEGeneradoProductoForm form = (ReporteVENICEGeneradoProductoForm) this.formReporte;
		if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			return mensaje;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENICEGeneradoProductoForm reporteVENICEGeneradoProductoForm = (ReporteVENICEGeneradoProductoForm) this.formReporte;

		String fecha1, fecha2;
		fecha1 = DateUtil.getDate(reporteVENICEGeneradoProductoForm
				.getFechaDesdeD());
		fecha2 = DateUtil.getDate(reporteVENICEGeneradoProductoForm
				.getFechaHastaD());

		params.put("fechaDesde",fecha1);
		params.put("fechaHasta",fecha2);
		params.put("codigoPais",this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		params.put("titulo",getResourceMessage("reporteVENICEGeneradoProductoForm.titulo"));
		

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENICEGeneradoProductoForm form = new ReporteVENICEGeneradoProductoForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteVENICEGeneradoProductoForm form = (ReporteVENICEGeneradoProductoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String reporte= "";
		if ("PDF".equals(form.getFormatoExportacion())){
			reporte = "reporteVENICEGeneradoProductoPDF";
		}
		
		return reporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		ReporteVENICEGeneradoProductoForm form = (ReporteVENICEGeneradoProductoForm) this.formReporte;
		form.setFechaDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaHastaD(new Date(System.currentTimeMillis()));
	}
}
