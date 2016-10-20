package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBValidacionNuevasForm;

@ManagedBean
@SessionScoped
public class ReporteCOBValidacionNuevasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 7313166098500500299L;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBValidacionNuevasForm reporteForm = new ReporteCOBValidacionNuevasForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equals("XLS", this.formatoExportacion))		
			return "reporteCOBValidacionNuevasXLS";
		else 
			return "reporteMaestroHorizontal";		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBValidacionNuevasForm f= (ReporteCOBValidacionNuevasForm)this.formReporte;
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "zr.cod_regi", "'");
		String condicionZonas = this.obtieneCondicion(f.getZonaList(),"zz.cod_zona", "'");
		String condicionSeccion = this.obtieneCondicion(f.getSeccionList(),"zs.cod_secc", "'");
			
		String condicion = condicionSeccion + condicionZonas + condicionRegion;
		params.put("codperInicio", f.getCodPeriodoInicial());
		params.put("codperFin", f.getCodPeriodoFinal());
		params.put("condicion", condicion);
		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;	
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCOBValidacionNuevasForm f= (ReporteCOBValidacionNuevasForm)this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		
		Map criteria = new HashMap();		
		criteria.put("codigoPais", pais.getCodigo());		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteria);
		
	}
	
	//Muestras las Zonas por las Regiones Seleccionadas
	public void loadZonas(ValueChangeEvent val) {
		try {
			String[] regiones = (String []) val.getNewValue();	
			ReporteCOBValidacionNuevasForm f= (ReporteCOBValidacionNuevasForm)this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), "T", "VD", regiones, "T");
			this.siccSeccionList=null;
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	//Muestras las Zonas por las Secciones seleccionadas
	public void loadSecciones(ValueChangeEvent val) {
		try {
			String[] zonas = (String []) val.getNewValue();	
			ReporteCOBValidacionNuevasForm f= (ReporteCOBValidacionNuevasForm)this.formReporte;			
			AjaxService ajax = (AjaxService) getBean("ajaxService");		
			this.siccSeccionList=ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(
									f.getCodigoPais(), "T", "VD", f.getRegionList() , zonas, "T");
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	@Override
	public String setValidarReporte(){
		ReporteCOBValidacionNuevasForm f= (ReporteCOBValidacionNuevasForm)this.formReporte;	
		Integer fecha1, fecha2;		
		fecha1 = Integer.parseInt(f.getCodPeriodoInicial());
		fecha2 = Integer.parseInt(f.getCodPeriodoFinal());
		if (fecha1 > fecha2) {
			String mensaje = "Campaña Hasta debe ser mayor o igual a Campaña Desde";
			return mensaje;
		}
		return "";
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}
	
	

}
