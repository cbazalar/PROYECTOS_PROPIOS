/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCPuntObtenidosBolsaFaltantesIncForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCRecomendacionesConcursoForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCRecomendacionesConcursoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1686197246105801882L;
	private String tipReporte;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List tipoReporteList;
	private LabelValue[] concursoList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCRecomendacionesConcursoForm form = new ReporteINCRecomendacionesConcursoForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "ReporteINCRecomendacionesConcurso"+ tipReporte + "XLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(formatoReporte))||("VPDF".equals(formatoReporte)))
			return "ReporteINCRecomendacionesConcurso"+ tipReporte + "PDF";
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug(">>ReporteINCRecomendacionesConcursoAction- prepareParameterMap...");
		}
	
			Map criteria = params;
			ReporteINCRecomendacionesConcursoForm form = (ReporteINCRecomendacionesConcursoForm) this.formReporte;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			formatoReporte = form.getFormatoExportacion();
	
			String codigoRegion = (String) params.get("codigoRegion");
			String codigoZona = (String) params.get("codigoZona");
			String codigoConcurso = (String) params.get("codigoConcurso");
			String condicionZonas = "";
			String condicionRegion = "";
			String condicionConcurso= "";
			
			if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion) ){
				if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona))
					params.put("condicionOuter","inc.oid_recte = zon_recte.oid_clie(+) and inc.oid_recda = zon_recda.oid_clie(+) ");							
				else
					params.put("condicionOuter","inc.oid_recte = zon_recte.oid_clie and inc.oid_recda = zon_recda.oid_clie ");				
			}
			else
				params.put("condicionOuter","inc.oid_recte = zon_recte.oid_clie and inc.oid_recda = zon_recda.oid_clie");			
				
				
			if ("Todos".equals(codigoConcurso) || StringUtils.isEmpty(codigoConcurso) ) {
				codigoConcurso = null;
				params.put("codigoConcurso", codigoConcurso);
			} else {
				codigoConcurso=StringUtil.deArrayAStringToken(form.getCodigoConcurso());
				String[] arregloConcurso=StringUtil.obtenerArreglo(codigoConcurso ,",");
				for (int i = 0; i < arregloConcurso.length; i++) {
					criteria.put("numeroConcurso", arregloConcurso[i]);
					arregloConcurso[i]=reporteService.getOidConcurso("getOidConcursoByNumConcurso", criteria);
				}
				condicionConcurso = obtieneCondicionIN(
						arregloConcurso, "", "'");
			}
		
			if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion) ) {
				codigoRegion = null;
				params.put("codigoRegion", codigoRegion);			
			} else 
				condicionRegion = obtieneCondicion(form.getCodigoRegion(), "COD_REGI", "'");			
		
			if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
				codigoZona = null;
				params.put("codigoZona", codigoZona);
			} else 
				condicionZonas = obtieneCondicion(form.getCodigoZona(),"COD_ZONA", "'");
			
			tipReporte = form.getDetalleTipoReporte();
			String condicion = condicionZonas + condicionRegion;
			params.put("condicion", condicion);
			params.put("numeroConcurso", condicionConcurso);
			
			form.setNroReporte("REP-INC02");
			form.setTitulo(this.mPantallaPrincipalBean.getResourceMessage("Informe Recomendaciones por Concurso ("+ tipReporte + ")"));
			params.put("NroReporte", form.getNroReporte());
			params.put("titulo", form.getTitulo());
			
		
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug(">>ReporteINCRecomendacionesConcursoAction- setViewAtributes...");
		}
		this.mostrarReporteXLS = true;
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteINCRecomendacionesConcursoForm form = (ReporteINCRecomendacionesConcursoForm) this.formReporte;


		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		GregorianCalendar date = new GregorianCalendar();
		
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO())); 
		
		
		String periodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		String annio = periodo.substring(0, 4);
		
		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		form.setAnhio(String.valueOf(date.get(GregorianCalendar.YEAR)));		
		form.setCodBaseCalculo(Constants.IND_BASE_CALCULO);
		form.setCodigoPeriodo(periodo);
		
		//Carga de tipoReporteList
		ArrayList<Base> tipoReporte = new ArrayList<Base>();
		Base[] baseTipos = new Base[5]; 
		baseTipos[0] = new Base();
		baseTipos[0].setCodigo("Concurso");
		baseTipos[0].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.concurso"));
		baseTipos[1] = new Base();
		baseTipos[1].setCodigo("Region");
		baseTipos[1].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.region"));
		baseTipos[2] = new Base();
		baseTipos[2].setCodigo("Zona");
		baseTipos[2].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.zona"));
		baseTipos[3] = new Base();
		baseTipos[3].setCodigo("Consultora");
		baseTipos[3].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.consultora"));
		baseTipos[4] = new Base();
		baseTipos[4].setCodigo("Totalizado");
		baseTipos[4].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.recomendante"));
		
		for(int i=0; i<5; i++){
			tipoReporte.add(baseTipos[i]);
		}
		tipoReporteList = tipoReporte; 
		
		setConcursoList(aSvc.getConcursosByPaisMarcaCanalAnhioTodos(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT,
				"T",Constants.IND_BASE_CALCULO, annio));
		
		setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
				form.getCodigoCanal(), "Concurso"));
		//setConcursoList();
		//setSiccRegionList(new ArrayList());
		//setSiccZonaList(new ArrayList());

		log.debug("Todo Ok: Redireccionando");
		
	}
	
	public void loadConcursos(){
		try {
			log.debug(">>loadConcursos...");
			
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ReporteINCRecomendacionesConcursoForm form = (ReporteINCRecomendacionesConcursoForm) this.formReporte;
			setConcursoList(aSvc.getConcursosByPaisMarcaCanalAnhioTodos(form.getCodigoPais(), form.getCodigoMarca(), form.getCodigoCanal(),
					"",form.getCodBaseCalculo(), form.getAnhio()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void loadRegiones(ValueChangeEvent val){
		try {
			log.debug(">>loadRegiones...");
			log.debug(">>val: "+val.getNewValue().toString());
			
			String tipoReporte = val.getNewValue().toString();
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ReporteINCRecomendacionesConcursoForm form = (ReporteINCRecomendacionesConcursoForm) this.formReporte;
			setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
					form.getCodigoCanal(), tipoReporte));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void loadZonas(ValueChangeEvent val){
		try {
			log.debug(">>loadZonas...");
			log.debug(">>val: "+(String[]) val.getNewValue());
			
			String[] regiones = (String[]) val.getNewValue();
			
			
			for(int i=0; i<regiones.length;i++){
				log.debug(">>Elem: "+regiones[i].toString());
			
			}
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ReporteINCRecomendacionesConcursoForm form = (ReporteINCRecomendacionesConcursoForm) this.formReporte;
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegionDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
					form.getCodigoCanal(), regiones, "T", form.getDetalleTipoReporte()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadConcursosPorMarca(ValueChangeEvent val){
		try {
			log.debug(">>loadConcursosPorMarca...");
			log.debug(">>val: "+val.getNewValue().toString());
			String valor = val.getNewValue().toString();
			
			ReporteINCRecomendacionesConcursoForm form = (ReporteINCRecomendacionesConcursoForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			log.debug(">>>>>> codigoMarca: " + form.getCodigoMarca());
			log.debug(">>>>>> codigoCanal: " + form.getCodigoCanal());
						
			setConcursoList(aSvc.getConcursosByPaisMarcaCanalAnhioTodos(form.getCodigoPais(), valor, form.getCodigoCanal(),
					"",form.getCodBaseCalculo(), form.getAnhio()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadConcursosPorCanal(ValueChangeEvent val){
		try {
			log.debug(">>loadConcursosPorMarca...");
			log.debug(">>val: "+val.getNewValue().toString());
			String valor = val.getNewValue().toString();
			
			ReporteINCRecomendacionesConcursoForm form = (ReporteINCRecomendacionesConcursoForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			log.debug(">>>>>> codigoMarca: " + form.getCodigoMarca());
			log.debug(">>>>>> codigoCanal: " + form.getCodigoCanal());
						
			setConcursoList(aSvc.getConcursosByPaisMarcaCanalAnhioTodos(form.getCodigoPais(), form.getCodigoMarca(), valor,
					"",form.getCodBaseCalculo(), form.getAnhio()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTipReporte() {
		return tipReporte;
	}

	public void setTipReporte(String tipReporte) {
		this.tipReporte = tipReporte;
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

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getTipoReporteList() {
		return tipoReporteList;
	}

	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}

	public LabelValue[] getConcursoList() {
		return concursoList;
	}

	public void setConcursoList(LabelValue[] concursoList) {
		this.concursoList = concursoList;
	}
	
	
	

}
