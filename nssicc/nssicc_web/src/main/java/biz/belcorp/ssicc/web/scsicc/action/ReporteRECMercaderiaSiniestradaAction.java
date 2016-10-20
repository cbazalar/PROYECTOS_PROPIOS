package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECMercaderiaSiniestradaForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECMercaderiaSiniestradaAction extends
		BaseReporteAbstractAction implements Serializable {


	private List tipoReporteList = new ArrayList();
	private String tipoReporte;
	private String formatoReporte;
	
	/**
	 * @return
	 */
	public List getTipoReporteList() {
		return tipoReporteList;
	}

	/**
	 * @param tipoReporteList
	 */
	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
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
		ReporteRECMercaderiaSiniestradaForm reporteForm = new ReporteRECMercaderiaSiniestradaForm();
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
		
		if ("XLS".equals(formatoReporte))
		{
			if(tipoReporte.equals("XLS0")){
				return "reporteRECCabeceraFacturaXLS";
			}else if(tipoReporte.equals("XLS1")){
				return "reporteRECDetalleFacturaXLS";
			}else if(tipoReporte.equals("XLS2")){
				return "reporteRECCabeceraNotaXLS";
			}else if(tipoReporte.equals("XLS3")){
				return "reporteRECDetalleNotaXLS";
			}else if(tipoReporte.equals("XLS4")){
				return "reporteRECFdpXLS";
			}else if(tipoReporte.equals("XLS5")){
				return "reporteRECPremioXLS";
			}
		}
					
			return "reporteMaestroHorizontal";
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
		
		this.mostrarReporteXLS= true;
		this.mostrarReportePDF = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		ReporteRECMercaderiaSiniestradaForm mercaderiaSiniestradaForm = (ReporteRECMercaderiaSiniestradaForm)this.formReporte;
		Pais objPais = this.mPantallaPrincipalBean.getCurrentCountry();	
		this.tipoReporte = mercaderiaSiniestradaForm.getTipoReporte();
		this.formatoReporte = mercaderiaSiniestradaForm.getFormatoExportacion();
		params.put("formatoExportacion",mercaderiaSiniestradaForm.getFormatoExportacion());
		params.put("tipoSolicitud", Constants.REPORTE_REC_MERCADERIA_SINIESTRADA);
		params.put("oidTipoSolicitud", reporteService.getOidString("getTipoSolicitud", params));
		params.put("codigoCampanha", mercaderiaSiniestradaForm.getCodigoCampanha());
		String conValControlFact = objPais.getValorRepoCabeceraFact();
		String conValControlNota = objPais.getValorRepoCabeceraNota();
		params.put("conValControlFact", conValControlFact);
		params.put("conValControlNota", conValControlNota);
		
		return params;

	}
}