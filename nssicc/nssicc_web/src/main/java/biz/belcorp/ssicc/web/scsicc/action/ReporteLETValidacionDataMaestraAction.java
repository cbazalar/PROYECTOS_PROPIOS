package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETValidacionDataMaestraForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteLETValidacionDataMaestraAction extends BaseReporteAbstractAction
		implements Serializable {

	private String formatoReporte;

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

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETValidacionDataMaestraForm reporteForm = new ReporteLETValidacionDataMaestraForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoExportacion))
			return "reporteLETValidacionDataMaestraXLS";
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry() ;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		ReporteLETValidacionDataMaestraForm f = (ReporteLETValidacionDataMaestraForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		List lista = service.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			f.setCodigoPeriodo((String) lista.get(0));
		}

		log.debug("Todo Ok: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteLETValidacionDataMaestraForm reef = (ReporteLETValidacionDataMaestraForm) this.formReporte;
		this.formatoReporte = reef.getFormatoExportacion();
		String codigoPeriodo = "";
		codigoPeriodo = reef.getCodigoPeriodo();
	
		params.put("codigoPais",reef.getCodigoPais());
		//params.put("codigoPeriodo","%".concat(reef.getCodigoPeriodo()).concat("%"));
		
		//Map map = new HashMap();
		params.put("codigoPeriodo",codigoPeriodo );
		//map.put("codigoPais", MapUtils.getString(params, "codigoPais"));
		//map.put("codigoMarca", "T");
		//map.put("codigoCanal", "VD");
	//	map.put("parametroSistema", -1);
		
		//String codPerAnt = reporteService.getCodigoPeriodoASD(map);
		
		//map.put("parametroSistema", 1);
		//String codPerSig= reporteService.getCodigoPeriodoASD(map);
		
		//params.put("codigoPeriodoAnt","%".concat(codPerAnt).concat("%"));
		//params.put("codigoPeriodoSig","%".concat(codPerSig).concat("%"));
		//params.put("NroReporte", "");
		//params.put("titulo", "");
		return params;
	}
}