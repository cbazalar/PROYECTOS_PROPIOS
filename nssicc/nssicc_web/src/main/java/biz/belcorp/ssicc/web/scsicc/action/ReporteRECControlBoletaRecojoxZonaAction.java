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
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECControlBoletaRecojoxZonaForm;

@ManagedBean
@SessionScoped
public class ReporteRECControlBoletaRecojoxZonaAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = -5277246392211088821L;
	
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECControlBoletaRecojoxZonaForm reporteForm = new ReporteRECControlBoletaRecojoxZonaForm();
		return reporteForm;
	}	
	
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;		
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteRECControlBoletaRecojoxZonaForm f = (ReporteRECControlBoletaRecojoxZonaForm) this.formReporte;
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
			return "reporteRECControlBoletaRecojoZonaXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
		return "reporteRECControlBoletaRecojoZonaPDF";
		
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteRECControlBoletaRecojoxZonaForm f = (ReporteRECControlBoletaRecojoxZonaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String nInicio=DateUtil.convertDateToString(f.getFechaInicialD());
		String nFinal=DateUtil.convertDateToString(f.getFechaFinalD());
		f.setFechaInicial(nInicio);
		f.setFechaFinal(nFinal);
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "C.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(f.getZonaList(),"C.COD_ZONA","'");
		String condicionNovedad = this.obtieneCondicion(f.getResultadoList(),"REB.COD_ESTA_BORE","'");
		
		String orderBy = "ORDER BY ";
		//Map criteria = params;
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
		String condicionRecojo=this.obtieneCondicion(recojoList,"C.NUM_RECO","");
		//if (orderBy.length() < 9)
			//orderBy = "";
		
	
		params.put("orderBy", orderBy);
		params.put("codigoPais", codigoPais);		
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("fechaInicial", f.getFechaInicial());
		params.put("fechaFinal",f.getFechaFinal());		
		params.put("condicionRecojo", condicionRecojo);
		params.put("condicionNovedad", condicionNovedad);
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reporteRECControlBoletaRecojoxZonaForm.title"));
		
		return params;		
	}
	
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		
		
		ReporteRECControlBoletaRecojoxZonaForm f = (ReporteRECControlBoletaRecojoxZonaForm) this.formReporte;
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
		
		ReporteRECControlBoletaRecojoxZonaForm f = (ReporteRECControlBoletaRecojoxZonaForm) this.formReporte;
	    if (f.getFechaFinalD().compareTo(f.getFechaInicialD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
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
