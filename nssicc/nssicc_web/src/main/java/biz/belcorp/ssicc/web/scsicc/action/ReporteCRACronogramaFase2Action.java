package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCRACronogramaFase2Form;

@ManagedBean
@SessionScoped
public class ReporteCRACronogramaFase2Action extends BaseReporteAbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2683032339097458685L;
	private String formatoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCRACronogramaFase2Form reporteForm = new ReporteCRACronogramaFase2Form();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCRACronogramaFase2Action - setViewAtributes");
		}
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCRACronogramaFase2Form f = (ReporteCRACronogramaFase2Form) this.formReporte;
		
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		String periodoActual = reporteService.getStringGenerico("getPeriodoByFechaActual", criteriaOperacion);
		f.setCodigoPeriodo(periodoActual);
		
		log.debug("Todo Ok: Redireccionando");	

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
		if ("PDF".equals(formatoReporte))
			return "reporteCRACronogramaFase2";
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ReporteCRACronogramaFase2Form reporteCRAForm = (ReporteCRACronogramaFase2Form) this.formReporte;
		formatoReporte = reporteCRAForm.getFormatoExportacion();
		
		params.put("NroReporte", " ");
		params.put("titulo",getResourceMessage("reporteCRACronogramaFase2Form.titulo")
				+ " \n  "
				+reporteCRAForm.getCodigoPeriodo());
		
		return params;
	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}


}

