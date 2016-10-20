/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComparativoRetailSiccForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteCOMComparativoRetailSiccAction.
 *
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 */
@ManagedBean
@SessionScoped
public class ReporteCOMComparativoRetailSiccAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5099826907828427735L;
	
	/** The formato reporte. */
	private String formatoReporte;
	
	/** The valida tope factura. */
	private double validaTopeFactura;
	
	/** The valida tope nota credito. */
	private double validaTopeNotaCredito;
	
	/** The Constant RUTA_JASPER. */
	protected static final String RUTA_JASPER = "/biz/belcorp/ssicc/reportes/jasper/";

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMComparativoRetailSiccForm form = new ReporteCOMComparativoRetailSiccForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteCOMComparativoRetailSiccForm)this.formReporte).getFormatoExportacion();
		if ("XLS".equals(formatoReporte))
			return "reporteCOMComparativoRetailSiccConsolidadoXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCOMComparativoRetailSiccConsolidadoPDF";
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComparativoRetailSiccAction.prepareParameterMap' method");
		}
		
		try{
			ReporteCOMComparativoRetailSiccForm form = (ReporteCOMComparativoRetailSiccForm) this.formReporte;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			formatoReporte = form.getFormatoExportacion();
			form.setTitulo(this.getResourceMessage("reporteCOMComparativoRetailSiccForm.title"));
			
			
				ClassPathResource resource = new ClassPathResource(this.RUTA_JASPER + "reporteCOMComparativoRetailSiccFacturaPDF".concat(this.JASPER_EXTENSION));
				ClassPathResource resource1 = new ClassPathResource(this.RUTA_JASPER + "reporteCOMComparativoRetailSiccNotaCreditoPDF".concat(this.JASPER_EXTENSION));
				
				if ("XLS".equals(formatoReporte)) {
					resource = new ClassPathResource(this.RUTA_JASPER + "reporteCOMComparativoRetailSiccFacturaXLS".concat(this.JASPER_EXTENSION));
					resource1 = new ClassPathResource(this.RUTA_JASPER + "reporteCOMComparativoRetailSiccNotaCreditoXLS".concat(this.JASPER_EXTENSION));
				}
				
				
				params.put("SUBREPORT_DIR1", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("SUBREPORT_DIR2", JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
			
			
			//-- Capturar validaciones Monto Tope -----------------------
			Map criteria = new HashMap();
			criteria.put("codigoPais", form.getCodigoPais());
			
			criteria.put("codigoTipoDocumento", "F");
			validaTopeFactura = reporteService.getValidarMontoTope(criteria);

			criteria.put("codigoTipoDocumento", "N");
			validaTopeNotaCredito = reporteService.getValidarMontoTope(criteria);

			//-- Parametro Reportes -------------------------------------
			params.put("fechaInicio", form.getFechaInicio());
			params.put("fechaFin",  form.getFechaFin());
			
			params.put("titulo", form.getTitulo());
			params.put("validaTopeFactura", Double.toString(validaTopeFactura) );
			params.put("validaTopeNotaCredito", Double.toString(validaTopeNotaCredito) );
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComparativoRetailSiccAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ReporteCOMComparativoRetailSiccForm f = (ReporteCOMComparativoRetailSiccForm) this.formReporte;
		f.setFechaInicio(new Date());
		f.setFechaFin(new Date());
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		
		setFormatoReporte(f.getFormatoExportacion());
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteCOMComparativoRetailSiccForm form = (ReporteCOMComparativoRetailSiccForm) this.formReporte;
		if(form.getFechaInicio()!=null && form.getFechaFin()!=null){
			if(form.getFechaInicio().compareTo(form.getFechaFin())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}

	/**
	 * Gets the formato reporte.
	 *
	 * @return the formato reporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * Sets the formato reporte.
	 *
	 * @param formatoReporte the new formato reporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * Gets the valida tope factura.
	 *
	 * @return the valida tope factura
	 */
	public double getValidaTopeFactura() {
		return validaTopeFactura;
	}

	/**
	 * Sets the valida tope factura.
	 *
	 * @param validaTopeFactura the new valida tope factura
	 */
	public void setValidaTopeFactura(double validaTopeFactura) {
		this.validaTopeFactura = validaTopeFactura;
	}

	/**
	 * Gets the valida tope nota credito.
	 *
	 * @return the valida tope nota credito
	 */
	public double getValidaTopeNotaCredito() {
		return validaTopeNotaCredito;
	}

	/**
	 * Sets the valida tope nota credito.
	 *
	 * @param validaTopeNotaCredito the new valida tope nota credito
	 */
	public void setValidaTopeNotaCredito(double validaTopeNotaCredito) {
		this.validaTopeNotaCredito = validaTopeNotaCredito;
	}
		
}
