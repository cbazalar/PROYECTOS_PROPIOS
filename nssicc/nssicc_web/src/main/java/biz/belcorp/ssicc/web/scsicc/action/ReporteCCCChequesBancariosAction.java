package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCChequesBancariosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCCCChequesBancariosAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 3847694719429935218L;
	
	private String tipoNombreReporte;
	private String tipoReporte;
	private List siccBancoList;
	
	
	@Override
	protected void setViewAtributes() throws Exception {		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");	 
		this.siccBancoList = serviceCCC.getBancosCheques();

		ReporteCCCChequesBancariosForm form = (ReporteCCCChequesBancariosForm) this.formReporte;
		form.setCodigoPais(pais.getCodigo());

		// SETEANDO VALORES POR DEFAULT A FECHAS
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setFechaCobroDesde(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaCobroDesdeD(new Date(System.currentTimeMillis()));

		form.setFechaCobroHasta(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaCobroHastaD(new Date(System.currentTimeMillis()));

		form.setFechaProcDesde(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaProcDesdeD(new Date(System.currentTimeMillis()));

		form.setFechaProcHasta(sdf.format(new Date(System.currentTimeMillis())));
		form.setFechaProcHastaD(new Date(System.currentTimeMillis()));

	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCChequesBancariosForm form = new ReporteCCCChequesBancariosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCChequesBancarios" + tipoReporte + "XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	public String setValidarReporte() {
		ReporteCCCChequesBancariosForm f = (ReporteCCCChequesBancariosForm) this.formReporte;
		
		if(f.getFechaProcDesdeD()!=null)
			f.setFechaProcDesde(DateUtil.convertDateToString(f.getFechaProcDesdeD()));
		else
			f.setFechaProcDesde("");
		if(f.getFechaProcHastaD()!=null)		
			f.setFechaProcHasta(DateUtil.convertDateToString(f.getFechaProcHastaD()));
		else
			f.setFechaProcHasta("");
		if(f.getFechaCobroDesdeD()!=null)
			f.setFechaCobroDesde(DateUtil.convertDateToString(f.getFechaCobroDesdeD()));
		else
			f.setFechaCobroDesde("");
		if(f.getFechaCobroHastaD()!=null)
			f.setFechaCobroHasta(DateUtil.convertDateToString(f.getFechaCobroHastaD()));	
		else
			f.setFechaCobroHasta("");


		if (StringUtils.isNotBlank(f.getFechaProcDesde()) && StringUtils.isNotBlank(f.getFechaProcHasta())) {
			if (DateUtil.compareDates(f.getFechaProcDesde(), f.getFechaProcHasta(), "dd/MM/yyyy")>0) {
				String mensaje = this.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaProc");				
				return mensaje;
			}
		}
		
		if (StringUtils.isNotBlank(f.getFechaCobroDesde()) && StringUtils.isNotBlank(f.getFechaCobroHasta())) {
			if (DateUtil.compareDates(f.getFechaCobroDesde(), f.getFechaCobroHasta(), "dd/MM/yyyy")>0) {
				String mensaje = this.getResourceMessage("reporteCCCLiquidacionCobranzasForm.errors.compare.fechaPago");			
				return mensaje;
			}
		}
		
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {	
		ReporteCCCChequesBancariosForm f = (ReporteCCCChequesBancariosForm) this.formReporte;
		params.put("fechaProcDesde", f.getFechaProcDesde());
		params.put("fechaProcHasta", f.getFechaProcHasta());
		params.put("fechaCobroDesde", f.getFechaCobroDesde());
		params.put("fechaCobroHasta", f.getFechaCobroHasta());
		tipoReporte = f.getTipoReporte();
		return params;
	}

	public String getTipoNombreReporte() {
		return tipoNombreReporte;
	}

	public void setTipoNombreReporte(String tipoNombreReporte) {
		this.tipoNombreReporte = tipoNombreReporte;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List getSiccBancoList() {
		return siccBancoList;
	}

	public void setSiccBancoList(List siccBancoList) {
		this.siccBancoList = siccBancoList;
	}
	
	

	
}