package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCCuponesElectronicosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



@ManagedBean
@SessionScoped
public class ReporteINCCuponesElectronicosAction extends
		BaseReporteAbstractAction implements Serializable  {

	private static final long serialVersionUID = -8902347630530135727L;

	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List siccIncConfConcursoCuponElectrList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCCuponesElectronicosForm reporteForm = new ReporteINCCuponesElectronicosForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;

		ReporteINCCuponesElectronicosForm f = (ReporteINCCuponesElectronicosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoINCHabilitacionConcursoCargaPuntajeService serviceConcurso = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);		
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteria);		
		this.siccIncConfConcursoCuponElectrList=serviceConcurso
				.getListaConcursosCEActivosByConcursoPeriodo(criteria);				
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteINCCuponesElectronicosXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteINCCuponesElectronicosPDF";
	}

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCCuponesElectronicosService";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteINCCuponesElectronicosForm f = (ReporteINCCuponesElectronicosForm) this.formReporte;
		
		String var=f.getNumeroConcurso();
		String nombre="";
		formatoReporte = f.getFormatoExportacion();
		log.debug(var);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		
		nombre=mostrarNombre(siccIncConfConcursoCuponElectrList, var);					
		f.setNombreConcurso(nombre);		
		
		params.put("usuarioLogin", usuario.getLogin());
		params.put("regionList", f.getRegionList());
		params.put("zonaList", f.getZonaList());
		params.put("NroReporte", "");
		params.put("titulo",
				getReportResourceMessage("reporteINCCuponesElectronicosForm.titulo") + " " + f.getNumeroConcurso());
		params.put("subtitulo", f.getNombreConcurso());		
		params.put("formatoReporte", f.getFormatoExportacion());			
		
		log.debug(" Imprimiendo parįmetros");
		log.debug(params);
		log.debug("Fin parįmetros");
		return params;	
	}
		
	
	public void showZonasxRegion(ValueChangeEvent val) {

		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		
		ReporteINCCuponesElectronicosForm f= (ReporteINCCuponesElectronicosForm) this.formReporte;
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
	
	public String mostrarNombre(List lista,String var){
		
		String nombre="";
		for(int i=0;i<lista.size();i++){
			String numA=lista.get(i).toString();
			String []n1=numA.split(",");
			String ncodigo=n1[2];
			String []n4=ncodigo.split("=");
			String codigo=n4[1];
			if(codigo.equals(var))
			{
				String des=n1[6];
				String []n2=des.split("-");
				String nombre1=n2[1];				
				String []n3=nombre1.split("}");
				nombre=n3[0];
				break;
			}
		}
		return nombre;
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
	
	public List getSiccIncConfConcursoCuponElectrList() {
		return siccIncConfConcursoCuponElectrList;
	}

	public void setSiccIncConfConcursoCuponElectrList(
			List siccIncConfConcursoCuponElectrList) {
		this.siccIncConfConcursoCuponElectrList = siccIncConfConcursoCuponElectrList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

}

