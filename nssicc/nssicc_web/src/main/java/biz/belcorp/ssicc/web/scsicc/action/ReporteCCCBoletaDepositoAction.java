package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCBoletaDepositoForm;

@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ReporteCCCBoletaDepositoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 4017098987516904295L;
	private String tipoReporte;
	private List siccBancoList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCBoletaDepositoForm form = new ReporteCCCBoletaDepositoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCBoletaDepositoAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;		
		ReporteCCCBoletaDepositoForm form = (ReporteCCCBoletaDepositoForm) this.formReporte;
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		this.siccBancoList = serviceCCC.getBancosCheques();
		form.setFechaCobroDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaCobroHastaD(new Date(System.currentTimeMillis()));
		form.setFechaProcDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaProcHastaD(new Date(System.currentTimeMillis()));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCBoletaDepositoForm form = (ReporteCCCBoletaDepositoForm) this.formReporte;
		Date fecha1D =  form.getFechaCobroDesdeD();
		Date fecha2D = form.getFechaCobroHastaD();
		Date fecha3D = form.getFechaProcDesdeD();
		Date fecha4D = form.getFechaProcHastaD();
		if(fecha2D!=null && fecha1D!=null  )
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this.getResourceMessage("reporteCCCBoletaDepositoForm.errors.compare.fechaCobro");
			return mensaje;
		}
		
		if(fecha4D!=null && fecha3D!=null  )
		if (fecha4D.compareTo(fecha3D) < 0) {
			String mensaje = this.getResourceMessage("reporteCCCBoletaDepositoForm.errors.compare.fechaProc");
			return mensaje;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteCCCBoletaDepositoForm reporteCCCForm = (ReporteCCCBoletaDepositoForm) this.formReporte;
		this.tipoReporte = reporteCCCForm.getTipoReporte();
		
		String fechaCobroDesde = DateUtil.getDate(reporteCCCForm.getFechaCobroDesdeD());
		String fechaCobroHasta = DateUtil.getDate(reporteCCCForm.getFechaCobroHastaD());
		String fechaProcDesde = DateUtil.getDate(reporteCCCForm.getFechaProcDesdeD());
		String fechaProcHasta = DateUtil.getDate(reporteCCCForm.getFechaProcHastaD());
		reporteCCCForm.setFechaCobroDesde(fechaCobroDesde);
		reporteCCCForm.setFechaCobroHasta(fechaCobroHasta);
		reporteCCCForm.setFechaProcDesde(fechaProcDesde);
		reporteCCCForm.setFechaProcHasta(fechaProcHasta);
		params.put("codigoPais",this.mPantallaPrincipalBean.getCurrentCountry().getCodigo() );
		params.put("tipoReporte", this.tipoReporte);
		params.put("codigoBanco",reporteCCCForm.getCodigoBanco());
		params.put("fechaCobroDesde", fechaCobroDesde);
		params.put("fechaCobroHasta",fechaCobroHasta);
		params.put("fechaProcDesde", fechaProcDesde);
		params.put("fechaProcHasta", fechaProcHasta);
		
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCBoletaDeposito" + this.tipoReporte + "XLS";				
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;		
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccBancoList
	 */
	public List getSiccBancoList() {
		return siccBancoList;
	}

	/**
	 * @param siccBancoList the siccBancoList to set
	 */
	public void setSiccBancoList(List siccBancoList) {
		this.siccBancoList = siccBancoList;
	}
	
}
