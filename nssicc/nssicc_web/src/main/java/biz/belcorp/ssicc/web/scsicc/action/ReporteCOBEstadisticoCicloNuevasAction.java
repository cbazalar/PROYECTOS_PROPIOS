package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDPedidosReservaPerdidaProlForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBConsultoraIncobrableForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBEstadisticoCicloNuevasForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBPosiblesReingresosForm;

@ManagedBean
@SessionScoped
public class ReporteCOBEstadisticoCicloNuevasAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 7327884840252886265L;
	
	private List siccRegionList;
	private List siccZonaList;
	private String valorCicloNueva;
	private String valorTipoVista;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBEstadisticoCicloNuevasForm form = new ReporteCOBEstadisticoCicloNuevasForm();		
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOBEstadisticoCicloNuevasForm f = (ReporteCOBEstadisticoCicloNuevasForm) this.formReporte;
		if(StringUtils.equals("XLS", this.formatoExportacion)){
			if(StringUtils.equals(f.getTipoVista(), "C"))
				return "reporteCOBEstadisticoCicloNuevasCampañaXLS";
			
			if(StringUtils.equals(f.getTipoVista(), "R"))
				return "reporteCOBEstadisticoCicloNuevasRegionXLS";
			
			if(StringUtils.equals(f.getTipoVista(), "Z"))
				return "reporteCOBEstadisticoCicloNuevasZonaXLS";	
			
			if(StringUtils.equals(f.getTipoVista(), "S"))
				return "reporteCOBEstadisticoCicloNuevasSeccionXLS";								
		}		
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBEstadisticoCicloNuevasForm f = (ReporteCOBEstadisticoCicloNuevasForm) this.formReporte;
		obtenerValorCombo();
		String titulo=this.getResourceMessage("reporteCOBEstadisticoCicloNuevasForm.titulo", 
				new Object[]{this.valorCicloNueva, this.valorTipoVista});
		String region=this.obtieneCondicion(f.getRegiones(), "cbz.cod_regi", "'");
		String zona=this.obtieneCondicion(f.getZonas(), "cbz.cod_zona", "'");
		String condicion=region+zona;
		params.put("codigoCicloNueva", f.getCodCicloNueva());
		params.put("codPeriodoInicio", f.getPeriodoInicio());
		params.put("codPeriodoFin", f.getPeriodoFin());
		params.put("condicion", condicion);
		params.put("titulo", titulo);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		
		ReporteCOBEstadisticoCicloNuevasForm f = (ReporteCOBEstadisticoCicloNuevasForm) this.formReporte;	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");	
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());		
	
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.siccZonaList = new ArrayList();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));			
		f.setPeriodoInicio(periodo);
		f.setPeriodoFin(periodo);
		f.setCodCicloNueva("1");
		f.setTipoVista("C");
		
	}
	
	//Muestra las Zonas por las Regiones Seleccionadas
	public void showZonasxRegion(ValueChangeEvent val){		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		String[] regiones = (String[]) val.getNewValue();
		
		if(regiones != null && regiones.length > 0) {
			LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", regiones, "T");			
			if(zonas != null && zonas.length > 0) {
				this.siccZonaList = Arrays.asList(zonas);
			} else 
				this.siccZonaList = new ArrayList();				
		} else{
			this.siccZonaList = new ArrayList();
		}
	}	
	
	public String setValidarReporte() {
		ReporteCOBEstadisticoCicloNuevasForm f = (ReporteCOBEstadisticoCicloNuevasForm) this.formReporte;		
		Integer perInicio = Integer.parseInt(f.getPeriodoInicio());
		Integer perFin = Integer.parseInt(f.getPeriodoFin());
		
		if (perInicio > perFin) 
			return "Periodo Final debe ser mayor o igual a Periodo Inicial";
		
		return null;
	}
	
	//Obtener las descripciones de Tipo Vista y Ciclo Nuevas
	public void obtenerValorCombo(){
		ReporteCOBEstadisticoCicloNuevasForm f = (ReporteCOBEstadisticoCicloNuevasForm) this.formReporte;
		if(StringUtils.equals(f.getCodCicloNueva(), "1"))
			this.valorCicloNueva ="1er Pedido";
		if(StringUtils.equals(f.getCodCicloNueva(), "2"))
			this.valorCicloNueva ="2do Pedido";
		if(StringUtils.equals(f.getCodCicloNueva(), "3"))
			this.valorCicloNueva ="3er Pedido";
		if(StringUtils.equals(f.getCodCicloNueva(), "4"))
			this.valorCicloNueva ="4to Pedido";
		
		//por tipo Vista
		if(StringUtils.equals(f.getTipoVista(), "C"))
			this.valorTipoVista ="Campaña";
		if(StringUtils.equals(f.getTipoVista(), "R"))
			this.valorTipoVista ="Región";
		if(StringUtils.equals(f.getTipoVista(), "Z"))
			this.valorTipoVista ="Zona";
		if(StringUtils.equals(f.getTipoVista(), "S"))
			this.valorTipoVista ="Sección";
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
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the valorCicloNueva
	 */
	
	public String getValorCicloNueva() {
		return valorCicloNueva;
	}

	/**
	 * @param valorCicloNueva the valorCicloNueva to set
	 */
	public void setValorCicloNueva(String valorCicloNueva) {
		this.valorCicloNueva = valorCicloNueva;
	}

	/**
	 * @return the valorTipoVista
	 */
	public String getValorTipoVista() {
		return valorTipoVista;
	}

	/**
	 * @param valorTipoVista the valorTipoVista to set
	 */
	public void setValorTipoVista(String valorTipoVista) {
		this.valorTipoVista = valorTipoVista;
	}
	
	

}
