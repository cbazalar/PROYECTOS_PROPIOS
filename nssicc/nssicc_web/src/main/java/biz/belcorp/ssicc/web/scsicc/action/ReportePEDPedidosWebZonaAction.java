package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDPedidosWebZonaForm;


/**
 * 
 * @author <a href="">Gonzalo Javier Huertas Agurto</a>
 */
@ManagedBean
@SessionScoped
public class ReportePEDPedidosWebZonaAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccRegionList;
    private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReportePEDPedidosWebZonaForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDPedidosWebZonaForm form =  (ReportePEDPedidosWebZonaForm) this.formReporte;
		
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reportePEDPedidosWebZonaXLS";
		else
		   return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

	   return null;
	}
	
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReportePEDPedidosWebZonaForm form = (ReportePEDPedidosWebZonaForm) this.formReporte;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.NO));
		}else{
			setSiccZonaList(null);
		}
		
		form.setCodigoZona(null);
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
	
		ReportePEDPedidosWebZonaForm reportePEDForm = (ReportePEDPedidosWebZonaForm) this.formReporte;
		
		Map criteria = params;
		
		String condicionZona = this.obtieneCondicion(reportePEDForm.getCodigoZona(),"COD_ZONA", "'");
	
		criteria.put("codigoPeriodo", reportePEDForm.getCodigoPeriodo());
		criteria.put("condicionZona", condicionZona);
		criteria.put("codigoPais",reportePEDForm.getCodigoPais());
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("ReportePEDPedidosWebZonaAction - setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReportePEDPedidosWebZonaForm form =  (ReportePEDPedidosWebZonaForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		//	Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		
			String codigoPeriodoActual = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
			form.setCodigoPais(pais.getCodigo());
		
		if (StringUtils.isBlank(codigoPeriodoActual)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			codigoPeriodoActual = periodo;
		}
		form.setCodigoPeriodo(codigoPeriodoActual);

		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		siccRegionList =  reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
	
		
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
}