package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRETDetalleComisionVentaRetailForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteRETDetalleComisionVentaRetailAction extends
		BaseReporteAbstractAction {

	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	private static final long serialVersionUID = 5452137025798023588L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRETDetalleComisionVentaRetailForm reporteForm = new ReporteRETDetalleComisionVentaRetailForm();
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
			return "reporteRETDetalleComisionVentaRetailXLS";
		else
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
		String subReporte = "";
		if ("PDF".equals(formatoReporte))
			subReporte = "reporteRETDetalleComisionVentaRetailPDF";
		return subReporte;
	}
	
	public String setValidarReporte() {
		ReporteRETDetalleComisionVentaRetailForm reporteRETForm = (ReporteRETDetalleComisionVentaRetailForm) this.formReporte;
	    if (reporteRETForm.getFechaInicioDt().compareTo(reporteRETForm.getFechaFinalDt()) > 0) 
				return getResourceMessage("reporteRETDetalleComisionVentaRetailForm.validar.fechas");
			
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
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();		
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());		
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);		
		if(listaRegiones.size()>0){
			this.siccRegionList = new LabelValue[listaRegiones.size()];
			int i = 0;
			for(Object object : listaRegiones){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccRegionList()[i] = labelValue;
				i++;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteAPEListaPickingSublineaAction.prepareParameterMap' method");
		}

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ReporteAPEListaPickingSublineaForm.prepareParameterMap' method");
			}

				ReporteRETDetalleComisionVentaRetailForm reporteRETForm = (ReporteRETDetalleComisionVentaRetailForm) this.formReporte;
				formatoReporte =this.getFormatoExportacion();
				String condicionRegion = this.obtieneCondicion(reporteRETForm.getRegionList(), "REP.COD_REGI", "'");
				String condicionZona = this.obtieneCondicion(reporteRETForm.getZonaList(), "REP.COD_ZONA", "'");
				
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subreporteRETGerenteZona" + JASPER_EXTENSION);
				params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				
				reporteRETForm.setFechaInicio(DateUtil.getDate(reporteRETForm.getFechaInicioDt()));
				reporteRETForm.setFechaFinal(DateUtil.getDate(reporteRETForm.getFechaFinalDt()));

				params.put("fechaInicio",reporteRETForm.getFechaInicio());
				params.put("fechaFinal", reporteRETForm.getFechaFinal());
				params.put("condicionRegion", condicionRegion);
				params.put("condicionZona", condicionZona);
				params.put("titulo", getResourceMessage("reporteRETDetalleComisionVentaRetailForm.title"));
				reporteRETForm.setFormatoExportacion(formatoReporte);

			
			return params;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;

	}
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		String[] valores = (String[]) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteRETDetalleComisionVentaRetailForm form = (ReporteRETDetalleComisionVentaRetailForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.OPCION_TODOS));
	}


}