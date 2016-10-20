package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECBoletaRecojoStatusForm;


@ManagedBean
@SessionScoped
public class ReporteRECBoletaRecojoStatusAction extends BaseReporteAbstractAction{
	
	
	private static final long serialVersionUID = 3407393704425448756L;
	
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};
	private List siccEstadosBorecList;
	private List siccResultadosBorecList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECBoletaRecojoStatusForm reporteForm = new ReporteRECBoletaRecojoStatusForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF=false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteRECBoletaRecojoStatusForm f = (ReporteRECBoletaRecojoStatusForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccEstadosBorecList=service.getEstadosBoletasRecojo(codpais);
		this.siccResultadosBorecList=service.getResultadosBoletasRecojo(codpais);		
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRECBoletaRecojoStatusXLS";
		else
			return "reporteMaestroHorizontal";
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		ReporteRECBoletaRecojoStatusForm f = (ReporteRECBoletaRecojoStatusForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		
		String condicionRegion = obtieneCondicion(f.getRegionList(), "C.COD_REGI", "'");
		String condicionZona = obtieneCondicion(f.getZonaList(), "C.COD_ZONA", "'");
		
		String codigoPeriodoInicio=f.getCodigoPeriodoInicio();	
		String codigoPeriodoFin=f.getCodigoPeriodoFin();		
		String condicionGestion = obtieneCondicion(f.getGestionList(), "P.COD_ESTA_BORE", "'");		
		String condicionResultado = obtieneCondicion(f.getResultadoList(), "R.COD_ESTA_BORE", "'");
		
		String nFecha="";
		if(f.getFechaProcesoD()!=null){
			nFecha=DateUtil.convertDateToString(f.getFechaProcesoD());
		}		
		
		if(nFecha.compareToIgnoreCase("")==0){
			nFecha = null;
		}
				
		params.put("codigoPeriodoInicio", codigoPeriodoInicio);
		params.put("codigoPeriodoFin", codigoPeriodoFin);		
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("condicionGestion", condicionGestion);
		params.put("condicionResultado", condicionResultado);
		params.put("fechaProceso", nFecha);
		params.put("NroReporte", "");
		params.put("titulo",getResourceMessage("reporteRECBoletaRecojoStatusForm.title"));
		return params;
	}
	
	public String setValidarReporte() {
		ReporteRECBoletaRecojoStatusForm f = (ReporteRECBoletaRecojoStatusForm) this.formReporte;
		int codperini = Integer.parseInt(f.getCodigoPeriodoInicio());
		int codperfin = Integer.parseInt(f.getCodigoPeriodoFin());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}		
		return null;	

	}
	
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());		
		
		ReporteRECBoletaRecojoStatusForm f = (ReporteRECBoletaRecojoStatusForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setZonaList(null);
		} else {
			this.siccZonaList = null;
			f.setZonaList(null);
		}
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getSiccEstadosBorecList() {
		return siccEstadosBorecList;
	}

	public void setSiccEstadosBorecList(List siccEstadosBorecList) {
		this.siccEstadosBorecList = siccEstadosBorecList;
	}

	public List getSiccResultadosBorecList() {
		return siccResultadosBorecList;
	}

	public void setSiccResultadosBorecList(List siccResultadosBorecList) {
		this.siccResultadosBorecList = siccResultadosBorecList;
	}
	
	
}
