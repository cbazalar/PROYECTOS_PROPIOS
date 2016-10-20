package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCAbonosDirectosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ReporteCCCAbonosDirectosAction extends BaseReporteAbstractAction {

	private List tipoReporteList = new ArrayList();
	private String tipoReporte;
	private String formatoReporte;
	private List cccTipoAbonosDirectosList;

	
	private static final long serialVersionUID = 5452137025798023586L;

	
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCAbonosDirectosForm reporteForm = new ReporteCCCAbonosDirectosForm();
		return reporteForm;
	}

	
	protected String devuelveNombreReporte() throws Exception {

		return "reporteCCCAbonosDirectos" + tipoReporte + "XLS";
	}

	
	protected String devuelveNombreSubReporte() throws Exception {

		return null;
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		// reportes
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// Servicios
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		// Formulario
		ReporteCCCAbonosDirectosForm f = (ReporteCCCAbonosDirectosForm) this.formReporte;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();

		// defecto
		f.reset();
		f.setCodigoPais(pais.getCodigo());
		this.cccTipoAbonosDirectosList = serviceCCC.getTipoAbonosDirectos();
		
	}

	
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCAbonosDirectosForm f = (ReporteCCCAbonosDirectosForm) this.formReporte;
		params.put("fechaDesde", f.getFechaDesde());
		params.put("fechaHasta", f.getFechaHasta());
		tipoReporte = f.getTipoReporte();

		return params;

	}

	public String setValidarReporte() {
		ReporteCCCAbonosDirectosForm f = (ReporteCCCAbonosDirectosForm) this.formReporte;
		if(f.getFechaDesdeD()!=null)
			f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeD()));
		else
			f.setFechaDesde("");
		if(f.getFechaHastaD()!=null)		
			f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaD()));
		else
			f.setFechaHasta("");		
		
		if (StringUtils.isNotBlank(f.getFechaDesde()) && StringUtils.isNotBlank(f.getFechaHasta())) {
			if (DateUtil.compareDates(f.getFechaDesde(), f.getFechaHasta(), "dd/MM/yyyy")>0) {
				String mensaje = this.getResourceMessage("reporteRETDetalleComisionVentaRetailForm.validar.fechas");				
				return mensaje;
			}
		}

		return null;
	}


	public List getTipoReporteList() {
		return tipoReporteList;
	}


	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}


	public String getTipoReporte() {
		return tipoReporte;
	}


	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}


	public String getFormatoReporte() {
		return formatoReporte;
	}


	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}


	public List getCccTipoAbonosDirectosList() {
		return cccTipoAbonosDirectosList;
	}


	public void setCccTipoAbonosDirectosList(List cccTipoAbonosDirectosList) {
		this.cccTipoAbonosDirectosList = cccTipoAbonosDirectosList;
	}

	
}