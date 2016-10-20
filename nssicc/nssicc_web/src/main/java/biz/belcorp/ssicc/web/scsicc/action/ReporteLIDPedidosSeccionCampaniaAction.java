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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteLIDPedidosSeccionCampaniaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteLIDPedidosSeccionCampaniaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 965854562003513503L;
	private List siccMarcaList;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLIDPedidosSeccionCampaniaForm form = new ReporteLIDPedidosSeccionCampaniaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.getFormatoExportacion()))
			   return "reporteLIDPedidosSeccionCampaniaXLS";
			else
				return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteLIDPedidosSeccionCampaniaPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteLIDPedidosSeccionCampaniaAction- prepareParameterMap...");
		}
		
		ReporteLIDPedidosSeccionCampaniaForm form = (ReporteLIDPedidosSeccionCampaniaForm) this.formReporte;
		String condicionRegion = this.obtieneCondicion(form.getCodigoRegion(), "A.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(form.getZonaList(), "A.COD_ZONA", "'");
		
		params.put("codigoPais",form.getCodigoPais());
		params.put("codigoMarca",form.getCodigoMarca());
		params.put("codigoPeriodo",form.getCodigoPeriodo());
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		
		form.setTitulo(this.mPantallaPrincipalBean.getResourceMessage("reporteLIDPedidosSeccionCampaniaForm.title"));
		params.put("titulo", form.getTitulo());
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("ReporteLIDPedidosSeccionCampaniaAction - setViewAtributes()"); 

		this.mostrarReporteXLS = true;
		
		ReporteLIDPedidosSeccionCampaniaForm f = (ReporteLIDPedidosSeccionCampaniaForm)this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		//Llenamos Lista de Marcas
		setSiccMarcaList(reporteService.getMarcas());
		//Llenamos Lista de Regiones
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais", criteria));
		
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoPeriodo(codigoPeriodo);
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		try {
			log.debug(">>showZonasxRegion...");
			log.debug(">>val: "+ (String[]) val.getNewValue());
			
			ReporteLIDPedidosSeccionCampaniaForm form = (ReporteLIDPedidosSeccionCampaniaForm)this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String[] regiones = (String[]) val.getNewValue();
			
			setSiccZonaList(aSvc.getZonasByPaisRegion(form.getCodigoPais(), regiones[0].toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
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