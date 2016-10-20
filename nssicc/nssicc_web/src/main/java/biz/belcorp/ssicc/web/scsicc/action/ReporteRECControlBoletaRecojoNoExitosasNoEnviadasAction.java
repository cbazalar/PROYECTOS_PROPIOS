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
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm;



@ManagedBean
@SessionScoped
public class ReporteRECControlBoletaRecojoNoExitosasNoEnviadasAction extends BaseReporteAbstractAction{
		
	
	private static final long serialVersionUID = 2084435059196698589L;	
	
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};
	private boolean mostrarPopup = true;
	
	
	
	
	/**
	 * @return the mostrarPopup
	 */
	public boolean isMostrarPopup() {
		return mostrarPopup;
	}

	/**
	 * @param mostrarPopup the mostrarPopup to set
	 */
	public void setMostrarPopup(boolean mostrarPopup) {
		this.mostrarPopup = mostrarPopup;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm reporteForm = new ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF=false;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm f = (ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRECControlBoletaRecojoNoEnviadasNoExitosasXLS";
		else
			return "reporteMaestroHorizontal";
		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm f = (ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "CB.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(f.getZonaList(),"CB.COD_ZONA","'");
		String condicionNovedad = this.obtieneCondicion(f.getResultadoList(),"REB.COD_ESTA_BORE","'");
		
		String orderBy = "ORDER BY ";
		Map criteria = params;
		String codigoPais=f.getCodigoPais();
		
		
		String recojoList[]= f.getRecojoList();
		
		
		if(f.getRecojoList()!=null){
		if(f.getRecojoList().length>0){
			
			for(int i=0; i<f.getRecojoList().length; i++){
				String dato= f.getRecojoList()[i];
				
				if(dato!=null){
					if(dato.compareToIgnoreCase("SEGUNDO RECOJO")==0)
						recojoList[i]="2";
					if(dato.compareToIgnoreCase("PRIMER RECOJO")==0)
						recojoList[i]="1";
					
				}
				else
					recojoList[i]="TODOS";
			}
		}
		}
		String condicionRecojo=this.obtieneCondicion(recojoList,"CB.NUM_RECO","");
		if (orderBy.length() < 10)
			orderBy = "";
	
	
		params.put("codigoPais", codigoPais);		
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("codigoPeriodoInicial", f.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFinal",f.getCodigoPeriodoFinal());		
		params.put("condicionRecojo", condicionRecojo);
		params.put("condicionNovedad", condicionNovedad);
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteRECControlBoletaRecojoxZonaForm.title"));
		return params;		
	}
	
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		
		
		ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm f = (ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm) this.formReporte;
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
	
	public String setValidarReporte() {
		ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm f = (ReporteRECControlBoletaRecojoNoExitosasNoEnviadasForm) this.formReporte;
		int codperini = Integer.parseInt(f.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(f.getCodigoPeriodoFinal());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}
		return null;

	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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
	
	
}
