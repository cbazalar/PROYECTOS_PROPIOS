package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DatosComision;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionRecuperacionEjecutivasForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMCalificacionComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMComisionRecuperacionEjecutivasAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = 1L;
	
	private String presentacion;
	private String formatoReporte;	
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] comcodComisionList;
	private List comTipoComisionistaList;
	private LabelValue[] siccRegionList;
	private List siccPresentacionList;
	private boolean mostrarRegion = true;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMComisionRecuperacionEjecutivasForm reporteForm = new ReporteCOMComisionRecuperacionEjecutivasForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteCOMComisionRecuperacionEjecutivasForm)this.formReporte).getFormatoExportacion();
		if ("XLS".equals(formatoReporte)){
			if ("1".equals(presentacion))
				return "reporteCOMComisionRecuperacionEjecutivasDetalleXLS";			
		}
		return "reporteMaestroHorizontal";	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		log.debug("getSubReporteFileName");
		this.formatoReporte = ((ReporteCOMComisionRecuperacionEjecutivasForm)this.formReporte).getFormatoExportacion();
		if ("XLS".equals(formatoReporte)) return "";
		
		if ("1".equals(presentacion))
			return "reporteCOMComisionRecuperacionEjecutivasDetalle";
		else if ("2".equals(presentacion))
			return "reporteCOMComisionRecuperacionEjecutivasSeccion";
		else if ("3".equals(presentacion))
			return "reporteCOMComisionRecuperacionEjecutivasZona";
		else if ("4".equals(presentacion))
			return "reporteCOMComisionRecuperacionEjecutivasRegion";
		
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionRecuperacionEjecutivasAction.prepareParameterMap' method");
		}		
		
		this.setMostrarReporteXLS(true);
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		ReporteCOMComisionRecuperacionEjecutivasForm form = (ReporteCOMComisionRecuperacionEjecutivasForm) this.formReporte;
		this.presentacion = form.getPresentacion();
		this.formatoReporte = form.getFormatoExportacion();
		String condicionRegion = "";
		if (presentacion.equals("1")){
			condicionRegion = this.obtieneCondicion(form.getRegionList(), "CAB.COD_REGI", "'");
		}
		
		form.setBeforeExecuteReporte(true);	
		
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		DatosComision datos = new DatosComision();
		datos.setCodigoPais(form.getCodigoPais());
		datos.setCodigo(form.getCodigoComision());
		DatosComision retorno = service.getDatosComision(datos);
		String dias = retorno.getNumeroDiasRecuperacionAspirante().toString();
		
		params.put("NroReporte", " ");
		params.put("numeroDiasRecuperacion", dias);
		params.put("condicionRegion", condicionRegion);
		params.put("codigoPais", form.getCodigoPais());
		params.put("codigoMarca", form.getCodigoMarca());
		params.put("codigoCanal", form.getCodigoCanal());
		params.put("oidPais",reporteService.getOidString("getOidPaisByCodigoPais", params));
		params.put("oidMarca",reporteService.getOidString("getOidMarcaByCodigoMarca", params));
		params.put("oidCanal",reporteService.getOidString("getOidCanalByCodigoCanal", params));
		
		if ("1".equals(presentacion)){
			params.put("titulo",this.getReportResourceMessage("reporteCOMComisionRecuperacionEjecutivasForm.detalle.titulo"));
		}else if ("2".equals(presentacion)){
			params.put("titulo",this.getReportResourceMessage("reporteCOMComisionRecuperacionEjecutivasForm.seccion.titulo"));
		}else if ("3".equals(presentacion)){
			params.put("titulo",this.getReportResourceMessage("reporteCOMComisionRecuperacionEjecutivasForm.zona.titulo"));
		}else if ("4".equals(presentacion)){
			params.put("titulo",this.getReportResourceMessage("reporteCOMComisionRecuperacionEjecutivasForm.region.titulo"));
		}
		
		params.put("superiorIzquierda", this.getReportResourceMessage("reporte.maestro.confidencial"));
		params.put("NroReporte", this.getReportResourceMessage("reporte.maestro.cetco"));
		
		
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionRecuperacionEjecutivasAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		ReporteCOMComisionRecuperacionEjecutivasForm form = (ReporteCOMComisionRecuperacionEjecutivasForm) this.formReporte;
		
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		comTipoComisionistaList = tramoService.getTiposComisionistas(pais.getCodigo());
		//comcodComisionList = service.getListCodComision(pais.getCodigo());		
		siccRegionList = aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT);
		String[] presentaciones = {"Detalle","Resumen por Sección","Resumen por Zona","Resumen por Región" };
		ArrayList resultado = new ArrayList();
		Base[] base = new Base[presentaciones.length];
		for (int i = 0; i < presentaciones.length; i++) {
			base[i] = new Base();
			base[i].setCodigo("" + (i + 1));
			base[i].setDescripcion(presentaciones[i]);
			resultado.add(base[i]);
		}
		
		siccPresentacionList = resultado;
		
		form.setCodigoPais(pais.getCodigo());
		if(form.getCodigoPeriodo()== null){
			form.setCodigoPeriodo(codigoPeriodo);
		}
		if(form.getCodigoCanal()==null){
			form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		}
		
	}
	
	
	
	public void showOptionsxTC(ValueChangeEvent val){
		try{
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ReporteCOMComisionRecuperacionEjecutivasForm form = (ReporteCOMComisionRecuperacionEjecutivasForm) this.formReporte;
			String codigoPais = form.getCodigoPais();
			
			String valor = val.getNewValue().toString();
			log.debug("valor: " + valor);
			 if(StringUtils.isNotBlank(valor))
				 comcodComisionList = aSvc.getDatosComisionByTipoComisionista(codigoPais, valor, "N");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	
	public void showRegionxPresent(ValueChangeEvent val){
		try{
			String valor = val.getNewValue().toString();
			log.debug("valor: " + valor);
			 if(valor.equals("1")){
				 setMostrarRegion(true);
			 	this.mostrarReporteXLS = true;
			 }else{
				 setMostrarRegion(false);
				 this.mostrarReporteXLS = false;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//GETTERS && SETTERS
	
	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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

	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccPresentacionList() {
		return siccPresentacionList;
	}

	public void setSiccPresentacionList(List siccPresentacionList) {
		this.siccPresentacionList = siccPresentacionList;
	}

	public LabelValue[] getComcodComisionList() {
		return comcodComisionList;
	}

	public void setComcodComisionList(LabelValue[] comcodComisionList) {
		this.comcodComisionList = comcodComisionList;
	}

	public boolean isMostrarRegion() {
		return mostrarRegion;
	}

	public void setMostrarRegion(boolean mostrarRegion) {
		this.mostrarRegion = mostrarRegion;
	}
	
	
	
}
