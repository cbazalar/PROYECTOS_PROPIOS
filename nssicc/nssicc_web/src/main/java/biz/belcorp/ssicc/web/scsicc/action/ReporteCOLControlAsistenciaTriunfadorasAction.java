package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOLControlAsistenciaTriunfadorasForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ReporteCOLControlAsistenciaTriunfadorasAction extends BaseReporteAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List siccMarcaList;
	private List siccCanalList;
	private String tipoReporte = "pdf";
	private LabelValue[] descripcionRegiones;
	private LabelValue[] descripcionZonas;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOLControlAsistenciaTriunfadorasForm reporte = new ReporteCOLControlAsistenciaTriunfadorasForm();
		return reporte;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVerticalControlAsistenciaTriunfadoras";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(this.formatoExportacion))||("VPDF".equals(formatoExportacion)))
			return "reporteCOLAsistenciaTriunPDF";
		return ""; 
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteCOLControlAsistenciaTriunfadorasForm f = (ReporteCOLControlAsistenciaTriunfadorasForm)this.formReporte;
		
		params.put("NroReporte", " ");
		params.put("titulo", this.getReportResourceMessage("reporteCOLControlAsistenciaTriunfadorasForm.titulo"));
		params.put("subTitulo", this.getReportResourceMessage("reporteCOLControlAsistenciaTriunfadorasForm.subTitulo") + " " + f.getCodigoPeriodo());
		
		params.put("tipoReporte", this.tipoReporte);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarReporteXLS = false;
		this.mostrarReporteCSV = false;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		ReporteCOLControlAsistenciaTriunfadorasForm f = (ReporteCOLControlAsistenciaTriunfadorasForm)this.formReporte;
		f.setCodigoPeriodo(codigoPeriodo);
		f.setCodigoPais(pais.getCodigo());
		
		//Cargando Regiones
		loadRegiones();
		
		//cargando Zonas
		loadZonas();
		
		log.debug("Todo Ok: Redireccionando");		
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOLControlAsistenciaTriunfadorasService";
	}
	
	public void validarRegionOnEnter()
	{
		ReporteCOLControlAsistenciaTriunfadorasForm f = (ReporteCOLControlAsistenciaTriunfadorasForm)this.formReporte;
		if (StringUtils.isBlank(f.getCodigoRegion())) {
			f.setCodigoZona("");
			this.addError("", "La Region ingresada no es Valida");
			return;
		}
		
		if(this.descripcionRegiones != null && this.descripcionRegiones.length > 0){
			for (int j = 0; j < this.descripcionRegiones.length; j++) 
			{
				if(f.getCodigoRegion().equalsIgnoreCase(descripcionRegiones[j].getValue()))
				{
					f.setCodigoZona("");
					f.setDescripcionRegion(this.descripcionRegiones[j].getLabel());
					loadZonas();
					return;
				}
				
			}
		}
		
		this.addError("", "La Región ingresada no es Válida");
		f.setCodigoRegion("");
	}
	
	public void validarZonaOnEnter()
	{
		ReporteCOLControlAsistenciaTriunfadorasForm f = (ReporteCOLControlAsistenciaTriunfadorasForm)this.formReporte;
		if(StringUtils.isBlank(f.getCodigoZona())){
			this.addError("", "La Zona ingresada no es Valida");
			return;
		}
		
		if(this.descripcionZonas != null && this.descripcionZonas.length > 0){
			for (int j = 0; j < this.descripcionZonas.length; j++) {
				if(f.getCodigoZona().equalsIgnoreCase(this.descripcionZonas[j].getValue()))
				{
					f.setDescripcionZona(this.descripcionZonas[j].getLabel());
					return;
				}			
			}
		}
		
		this.addError("", "La Zona ingresada no es Válida");
		f.setCodigoZona("");
	}
	
	public void loadZonas()
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCOLControlAsistenciaTriunfadorasForm f = (ReporteCOLControlAsistenciaTriunfadorasForm)this.formReporte;
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.descripcionZonas = ajax.getZonasByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", f.getCodigoRegion());
	}
	
	public void loadRegiones()
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCOLControlAsistenciaTriunfadorasForm f = (ReporteCOLControlAsistenciaTriunfadorasForm)this.formReporte;
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.descripcionRegiones = ajax.getRegionesByPaisMarcaCanal(pais.getCodigo(), "T", "VD");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte...");
		}
		
		ReporteCOLControlAsistenciaTriunfadorasForm f = (ReporteCOLControlAsistenciaTriunfadorasForm)this.formReporte;
		String mensaje = "";
		
		if(StringUtils.isBlank(f.getCodigoRegion())){
			mensaje = "'Region' es un campo requerido.";
		}
		
		return mensaje;
	}	

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public LabelValue[] getDescripcionRegiones() {
		return descripcionRegiones;
	}

	public void setDescripcionRegiones(LabelValue[] descripcionRegiones) {
		this.descripcionRegiones = descripcionRegiones;
	}

	public LabelValue[] getDescripcionZonas() {
		return descripcionZonas;
	}

	public void setDescripcionZonas(LabelValue[] descripcionZonas) {
		this.descripcionZonas = descripcionZonas;
	}	

}
