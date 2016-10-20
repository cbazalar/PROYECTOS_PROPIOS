package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCDetalladoCuponesPagoForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComparativoRetailSiccForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDCUVPedidoCampanaFaltanteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICFletesForm;



@SuppressWarnings({"unchecked","rawtypes"})
@SessionScoped
@ManagedBean
public class ReportePEDCUVPedidoCampanaFaltanteAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5960463211630952974L;
	
	private Boolean cambioFiltroCampana;
	private Boolean cambioFiltroVenta;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {

		ReportePEDCUVPedidoCampanaFaltanteForm r = new ReportePEDCUVPedidoCampanaFaltanteForm();
		return r;
	}

	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDCUVPedidoCampanaFaltanteForm form = (ReportePEDCUVPedidoCampanaFaltanteForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String reporte= "";
		
		if ("XLS".equals(form.getFormatoExportacion())){
			if(this.cambioFiltroCampana){
				reporte = "reportePEDCUVPedidoCampanaFaltanteCXLS";
			}else{
				if(this.cambioFiltroVenta){
					reporte = "reportePEDCUVPedidoCampanaFaltanteVXLS";
				}
			}
		}else{
			reporte = "reporteMaestroHorizontal";
		}
		
		return reporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReportePEDCUVPedidoCampanaFaltanteForm form = (ReportePEDCUVPedidoCampanaFaltanteForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String reporte= "";
		if ("PDF".equals(form.getFormatoExportacion())){
			if(this.cambioFiltroCampana){
				reporte = "reportePEDCUVPedidoCampanaFaltanteCPDF";
			}else{
				if(this.cambioFiltroVenta){
					reporte = "reportePEDCUVPedidoCampanaFaltanteVPDF";
				}
			}
		}
		
		return reporte;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReportePEDCUVPedidoCampanaFaltanteForm reportePEDForm = (ReportePEDCUVPedidoCampanaFaltanteForm) this.formReporte;
		params.put("titulo",getResourceMessage("reportePEDCUVPedidoCampanaFaltanteForm.titulo"));
		
		if(this.cambioFiltroCampana){
			params.put("codigoPeriodo", reportePEDForm.getCodigoPeriodoCampana());
		}else{
			if(this.cambioFiltroVenta){
				params.put("codigoPeriodo", reportePEDForm.getCodigoPeriodoVenta());
				params.put("codigoVenta", reportePEDForm.getCodigoVenta());
			}
		}
		
		this.setVisualizarReporte(true);
		
		return params;
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteriaOperacion = new HashMap();
		
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		ReportePEDCUVPedidoCampanaFaltanteForm f =  (ReportePEDCUVPedidoCampanaFaltanteForm) this.formReporte;
		
		this.cambioFiltroCampana = false;
		this.cambioFiltroVenta = false;
		
	}
	
	/**
	 * Metodo para Cambiar Filtro
	 * 
	 * @param val
	 */
	public void loadFiltro(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadFiltro");
		}
		ReportePEDCUVPedidoCampanaFaltanteForm form = (ReportePEDCUVPedidoCampanaFaltanteForm) this.formReporte;
		String valor = (String) val.getNewValue();
		if (StringUtils.equalsIgnoreCase(valor, Constants.NUMERO_UNO)) {
			this.cambioFiltroCampana = true;
			this.cambioFiltroVenta = false;
			form.setCodigoVenta(null);
			form.setCodigoPeriodoVenta(null);
		} else if (StringUtils.equalsIgnoreCase(valor, Constants.NUMERO_DOS)) {
			this.cambioFiltroCampana = false;
			this.cambioFiltroVenta = true;
			form.setCodigoPeriodoCampana(null);
		}
	}


	/**
	 * @return the cambioFiltroCampana
	 */
	public Boolean getCambioFiltroCampana() {
		return cambioFiltroCampana;
	}


	/**
	 * @param cambioFiltroCampana the cambioFiltroCampana to set
	 */
	public void setCambioFiltroCampana(Boolean cambioFiltroCampana) {
		this.cambioFiltroCampana = cambioFiltroCampana;
	}


	/**
	 * @return the cambioFiltroVenta
	 */
	public Boolean getCambioFiltroVenta() {
		return cambioFiltroVenta;
	}


	/**
	 * @param cambioFiltroVenta the cambioFiltroVenta to set
	 */
	public void setCambioFiltroVenta(Boolean cambioFiltroVenta) {
		this.cambioFiltroVenta = cambioFiltroVenta;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReportePEDCUVPedidoCampanaFaltanteForm form = (ReportePEDCUVPedidoCampanaFaltanteForm) this.formReporte;
		if(StringUtils.isBlank(form.getIndicadorCampanaVenta())){
			this.setMensajeAlertaDefault(this.getResourceMessage("reportePEDCUVPedidoCampanaFaltanteForm.msg.indicadorCampanaVentaVacio"));
			return this.getMensajeAlertaDefault();
		}else
		{
			if (StringUtils.equalsIgnoreCase(form.getIndicadorCampanaVenta(), Constants.NUMERO_UNO)) {
				//Si se filtra por campaña, validamos que la campaña no debe estar vacia
				if(StringUtils.isBlank(form.getCodigoPeriodoCampana())){
					this.setMensajeAlertaDefault(this.getResourceMessage("reportePEDCUVPedidoCampanaFaltanteForm.msg.codigoPeriodoVacio"));
					return this.getMensajeAlertaDefault();
				}
			}else{
				if (StringUtils.equalsIgnoreCase(form.getIndicadorCampanaVenta(), Constants.NUMERO_DOS)) {
					//Si se filtra por campaña, validamos que la campaña y el codigo de venta no debe estar vacia
					if(StringUtils.isBlank(form.getCodigoPeriodoVenta())){
						this.setMensajeAlertaDefault(this.getResourceMessage("reportePEDCUVPedidoCampanaFaltanteForm.msg.codigoPeriodoVacio"));
						return this.getMensajeAlertaDefault();
					}
					if(StringUtils.isBlank(form.getCodigoVenta())){
						this.setMensajeAlertaDefault(this.getResourceMessage("reportePEDCUVPedidoCampanaFaltanteForm.msg.codigoVentaVacio"));
						return this.getMensajeAlertaDefault();
					}
				}
			}
		}
		return null;
	}
	
	
}
