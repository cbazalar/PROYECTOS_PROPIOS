package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEMapaAnaquelForm;


/**
 * @author <a href="mailto:mmacias@belcorp.biz">Carolina Macias </a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteAPEMapaAnaquelAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccCentroDList;
	private List siccMapaList;
	private List siccLineaList;
	private List siccTipoSolList;
	
	private List siccCanalList;
	//private List siccRegionList;
    //private LabelValue[] siccZonaList = {};

	/*protected String devuelveBeanReporteService() {
		return "reportes.reporteMAEVinculosClienteService";
	}*/
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteAPEMapaAnaquelForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteAPEMapaAnaquelForm form = (ReporteAPEMapaAnaquelForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteAPEMapaAnaquelXLS";
		else
		   return "reporteMaestroVertical";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteAPEMapaAnaquelForm form = (ReporteAPEMapaAnaquelForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return null;
		else
		   return "reporteAPEMapaAnaquelPDF";
	}
	
	public void showLineYMapa(ValueChangeEvent val){
		log.debug(">>showLineYMapa ");
		log.debug("val: "+ val.getNewValue());
		ReporteAPEMapaAnaquelForm form = (ReporteAPEMapaAnaquelForm) this.formReporte;
		String centro = (String) val.getNewValue();		
	      
		if(StringUtils.isNotBlank(centro)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			LabelValue[] lv = null;
			Base b = null;
			
			lv = aSvc.getLinea(centro);
			siccLineaList = new  ArrayList();
			if (ArrayUtils.isNotEmpty(lv)) {
				for (LabelValue labelValue : lv) {
					if (StringUtils.isNotBlank(labelValue.getLabel()) && 
							StringUtils.isNotBlank(labelValue.getValue())){
						b = new Base();
						b.setCodigo(labelValue.getValue());
						b.setDescripcion(labelValue.getLabel());
						siccLineaList.add(b);
					}
				}
			}
			
			lv = aSvc.getMapaByCentro(centro);
			siccMapaList = new ArrayList();
			if (ArrayUtils.isNotEmpty(lv)) {
				for (LabelValue labelValue : lv) {
					if (StringUtils.isNotBlank(labelValue.getLabel()) && 
							StringUtils.isNotBlank(labelValue.getValue())){
					
						b = new Base();
						b.setCodigo(labelValue.getValue());
						b.setDescripcion(labelValue.getLabel());
						siccMapaList.add(b);
					}
				}
			}
			
		}else{
			setSiccLineaList(null);
			setSiccMapaList(null);
		}
		
		form.setCodigoLinea(null);
		form.setMapa(null);
	}
	
	public void showSubLine(ValueChangeEvent val){
		log.debug(">>showSubLine ");
		log.debug("val: "+ val.getNewValue());
		ReporteAPEMapaAnaquelForm form = (ReporteAPEMapaAnaquelForm) this.formReporte;
		String linea = (String) val.getNewValue();		
	      
		if(StringUtils.isNotBlank(linea)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			LabelValue[] lv = null;
			Base b = null;
			
			lv = aSvc.getSublineaByLinea(linea);
			siccTipoSolList = new  ArrayList();
			if (ArrayUtils.isNotEmpty(lv)) {
				for (LabelValue labelValue : lv) {
					if (StringUtils.isNotBlank(labelValue.getLabel()) && 
							StringUtils.isNotBlank(labelValue.getValue())){
						b = new Base();
						b.setCodigo(labelValue.getValue());
						b.setDescripcion(labelValue.getLabel());
						siccTipoSolList.add(b);
					}
				}
			}

		}else{
			setSiccTipoSolList(null);
		}
		
		form.setCodsublinea(null);
	}

	
	public void showVersion(ValueChangeEvent val){
		log.debug(">>showVersion ");
		log.debug("val: "+ val.getNewValue());
		ReporteAPEMapaAnaquelForm form = (ReporteAPEMapaAnaquelForm) this.formReporte;
		String codSubLinea = (String) val.getNewValue();		
	      
		if(StringUtils.isNotBlank(codSubLinea)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			LabelValue[] lv = null;
			Base b = null;
			
			lv = aSvc.getSublineaByLinea(form.getMapa());
			siccCanalList = new  ArrayList();
			if (ArrayUtils.isNotEmpty(lv)) {
				for (LabelValue labelValue : lv) {
					if (StringUtils.isNotBlank(labelValue.getLabel()) && 
							StringUtils.isNotBlank(labelValue.getValue())){
						b = new Base();
						b.setCodigo(labelValue.getValue());
						b.setDescripcion(labelValue.getLabel());
						siccCanalList.add(b);
					}
				}
			}

		}else{
			setSiccCanalList(null);
		}
		
		form.setVersion(null);
	}
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteAPEMapaAnaquelForm reporteAPEForm = (ReporteAPEMapaAnaquelForm) this.formReporte;
	
		params.put("NroReporte", " ");
		params.put("titulo", getReportResourceMessage("reporteAPEMapaAnaquelForm.titulo"));
		
		
//		ReporteCOBIncobrabilidadUnidadAdministrativaForm reporteCOBForm = (ReporteCOBIncobrabilidadUnidadAdministrativaForm) form;
		
				
//		this.tipoTotal = reporteCOBForm.getTipoTotal();
	
		String codigoPais =  reporteAPEForm.getCodigoPais();
	
        String linea = reporteAPEForm.getCodigoLinea().trim();
        String centro =reporteAPEForm.getCodigoCentro().trim();
        String mapa = reporteAPEForm.getMapa().trim();
        
		params.put("pais", codigoPais);	
		params.put("codigoLinea", linea);
		params.put("codigoCentro", centro);
		params.put("mapa", mapa);
		params.put("version",StringUtils.trimToEmpty(reporteAPEForm.getVersion()));	
		params.put("codigoSublinea", StringUtils.trimToEmpty(reporteAPEForm.getCodsublinea()));

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
	
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		 // Asignamos al codigo del periodo el valor por defecto
        Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        
	
        siccCentroDList = service.getCentroDistribucionByPais(criteria);
        siccLineaList = service.getLinea(criteria);
        siccMapaList = service.getMapaByCentro(criteria);
        siccTipoSolList = service.getSublineaxLinea(criteria);
        siccCanalList = service.getVersionSinP(criteria);	
        
		log.debug("Todo Ok: Redireccionando");
	}

	/**
	 * @return the siccCentroDList
	 */
	public List getSiccCentroDList() {
		return siccCentroDList;
	}

	/**
	 * @param siccCentroDList the siccCentroDList to set
	 */
	public void setSiccCentroDList(List siccCentroDList) {
		this.siccCentroDList = siccCentroDList;
	}

	/**
	 * @return the siccMapaList
	 */
	public List getSiccMapaList() {
		return siccMapaList;
	}

	/**
	 * @param siccMapaList the siccMapaList to set
	 */
	public void setSiccMapaList(List siccMapaList) {
		this.siccMapaList = siccMapaList;
	}

	/**
	 * @return the siccLineaList
	 */
	public List getSiccLineaList() {
		return siccLineaList;
	}

	/**
	 * @param siccLineaList the siccLineaList to set
	 */
	public void setSiccLineaList(List siccLineaList) {
		this.siccLineaList = siccLineaList;
	}

	/**
	 * @return the siccTipoSolList
	 */
	public List getSiccTipoSolList() {
		return siccTipoSolList;
	}

	/**
	 * @param siccTipoSolList the siccTipoSolList to set
	 */
	public void setSiccTipoSolList(List siccTipoSolList) {
		this.siccTipoSolList = siccTipoSolList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	

	
}