package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCGanadorasSinCUVForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.dao.sisicc.model.Base;

@ManagedBean
@SessionScoped
public class ReporteINCGanadorasSinCUVAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = 5415567056031185504L;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List siccConcursoList;
	private List incConcursoVigentesCerradosList;
	private List listaCUV;
	private DataTableModel lista;
	protected Object registrosSeleccionados;

	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCGanadorasSinCUVForm reporteForm = new ReporteINCGanadorasSinCUVForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteINCGanadorasSinCUVForm f = (ReporteINCGanadorasSinCUVForm) this.formReporte;		
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) 
									getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService InterfazService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		
		
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		
		this.incConcursoVigentesCerradosList= service.getListConcursosVigentesPuntos();
		
		this.listaCUV = new ArrayList();
		
		f.setCodigoPeriodo(InterfazService.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
		
		log.debug("Todo Ok: Redireccionando");
		
	
	}
	
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formReporte.getFormatoExportacion())){
				return "reporteINCGanadorasSinCUVXLS";
		}
		else
			return "reporteMaestroHorizontal";

	}
	
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteINCGanadorasSinCUVForm f = (ReporteINCGanadorasSinCUVForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = params;		

		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		String condicionZona = "";
		String condicionRegion = "";
		String condicionConcurso = "";
		String condicionCUV = "";

		if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		} else {
			condicionRegion = obtieneCondicion(f.getCodigoRegion(), "ZR.Cod_Regi", "'");
		}

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZona = obtieneCondicion(f.getCodigoZona(),"ZON.COD_ZONA", "'");
		}
		
		String cuvs[] = new String[this.listaCUV.size()];
		
		for(int i=0;i<this.listaCUV.size();i++){
			Map registro = (HashMap)this.listaCUV.get(i);
			cuvs[i] = MapUtils.getString(registro, "codigo"); 
		}
		
		condicionCUV = obtieneCondicion(cuvs,"", "'");
		condicionCUV = condicionCUV.substring(condicionCUV.indexOf('('));
		
		for(int i=0;i<this.incConcursoVigentesCerradosList.size();i++){
			Base registro = (Base)this.incConcursoVigentesCerradosList.get(i);
			if(StringUtils.equalsIgnoreCase(registro.getCodigo(), f.getNumeroConcurso()))
			{
				params.put("concurso", registro.getDescripcion());
				break;
			}
		}
		
		params.put("CUVList", cuvs);
		params.put("condicionZona", condicionZona);
		params.put("condicionCUV", condicionCUV);
		params.put("condicionRegion", condicionRegion);
		if(f.getCodigoRegion()!=null && f.getCodigoRegion().length>0 && StringUtils.isBlank(f.getCodigoRegion()[0])){
				params.put("regionList", null);
		}else{
			params.put("regionList", f.getCodigoRegion());
		}
		if(f.getCodigoZona()!=null && f.getCodigoZona().length>0 && StringUtils.isBlank(f.getCodigoZona()[0])){
			params.put("zonaList", null);
		}else{
			params.put("zonaList", f.getCodigoZona());
		}
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("numeroConcurso", f.getNumeroConcurso());
		params.put("formatoReporte", f.getFormatoExportacion());
			
		return params;

	}
	
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		
		
		ReporteINCGanadorasSinCUVForm f = (ReporteINCGanadorasSinCUVForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setCodigoZona(null);
		} else {
			this.siccZonaList = null;
			f.setCodigoZona(null);
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

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getIncConcursoVigentesCerradosList() {
		return incConcursoVigentesCerradosList;
	}

	public void setIncConcursoVigentesCerradosList(
			List incConcursoVigentesCerradosList) {
		this.incConcursoVigentesCerradosList = incConcursoVigentesCerradosList;
	}	
	
	public void agregarCUV(ActionEvent event) {
		ReporteINCGanadorasSinCUVForm f = (ReporteINCGanadorasSinCUVForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String cuv = f.getCodigoCUV().trim();
		Map criteria = new HashMap();
		Map codigoCUV = new HashMap();
		if(StringUtils.isNotBlank(cuv)&&StringUtils.isNotEmpty(cuv)){
			if(StringUtils.isNotBlank(f.getCodigoPeriodo())){
				criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			}else
			{
				this.addWarn("", this.getResourceMessage("reporteINCGanadorasSinCUVForm.msg.validaPeriodo"));
				return;
			}
			criteria.put("codigoCUV", cuv);
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			criteria.put("codigoPais", pais.getCodigo());
			String oidPeriodo=reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
			criteria.put("oidPeriodo", oidPeriodo);
			if(reporteService.getExisteCUV(criteria)>0){
				boolean existe = false;
				for(int i=0;i<this.listaCUV.size();i++){
					Map registro = (HashMap)this.listaCUV.get(i);
					if(StringUtils.equalsIgnoreCase(MapUtils.getString(registro, "codigo"), cuv)){
						existe = true;
						break;
					}
				}
				if(!existe){
					codigoCUV.put("codigo", cuv);
					this.listaCUV.add(codigoCUV);
					this.lista = new DataTableModel(this.listaCUV);
					f.setCodigoCUV(null);
				}else{
					this.addWarn("", this.getResourceMessage("reporteINCGanadorasSinCUVForm.msg.cuvExiste"));
					return;
				}
			}else{
				this.addWarn("", this.getResourceMessage("reporteINCGanadorasSinCUVForm.msg.cuvNoExiste"));
				return;
			}
		}else{
			this.addWarn("", this.getResourceMessage("reporteINCGanadorasSinCUVForm.msg.ingreseCuv"));
			return;
		}
		
	}
	
	public void eliminarCUV(ActionEvent event) {
		
		Map seleccionados = (HashMap)this.registrosSeleccionados;
		boolean encontrado = false;
		int index = -1;
		if(this.listaCUV!=null && this.listaCUV.size()>0){
			if(seleccionados!=null){
				for(int i=0;i<this.listaCUV.size();i++){
					Map registro = (HashMap)this.listaCUV.get(i);
					if(StringUtils.equalsIgnoreCase(MapUtils.getString(registro, "codigo"), MapUtils.getString(seleccionados, "codigo"))){
						encontrado = true;
						index = i;
						break;
					}
				}
				if(encontrado){
					listaCUV.remove(index);
					this.lista = new DataTableModel(this.listaCUV);					
					return;
				}
			}else{
				this.addWarn("", this.getResourceMessage("reporteINCGanadorasSinCUVForm.msg.seleccioneGrilla"));
				return;
			}
			
		}else{
			this.addWarn("", this.getResourceMessage("reporteINCGanadorasSinCUVForm.msg.ingreseGrilla"));
			return;
		}
		
	}

	
	public List getListaCUV() {
		return listaCUV;
	}

	public void setListaCUV(List listaCUV) {
		this.listaCUV = listaCUV;
	}

	/**
	 * @return the lista
	 */
	public DataTableModel getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(DataTableModel lista) {
		this.lista = lista;
	}

	/**
	 * @return the registrosSeleccionados
	 */
	public Object getRegistrosSeleccionados() {
		return registrosSeleccionados;
	}

	/**
	 * @param registrosSeleccionados the registrosSeleccionados to set
	 */
	public void setRegistrosSeleccionados(Object registrosSeleccionados) {
		this.registrosSeleccionados = registrosSeleccionados;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		String mensaje = null;
		
		if(this.listaCUV.size()==0){
			mensaje = this.getResourceMessage("reporteINCGanadorasSinCUVForm.msg.ingreseCUV");
		}
		
		return mensaje;
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCGanadorasSinCUVService";
	}
}
