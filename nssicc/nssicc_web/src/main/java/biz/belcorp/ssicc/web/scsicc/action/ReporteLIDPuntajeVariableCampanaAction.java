package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteLIDPuntajeVariableCampanaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDFactorPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteLIDPuntajeVariableCampanaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -3153908845825651502L;
	private String formatoReporte;
	private int tipoFormato;
	private String indicadorPeriodo;
	private boolean hayQuiebre;
	private List tipoAsignacionPuntajeList;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccSeccionList;
	private boolean habilita = true; 
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLIDPuntajeVariableCampanaForm form = new ReporteLIDPuntajeVariableCampanaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporteFileName="";
		switch(tipoFormato){
		case 1:
			reporteFileName="reporteLIDPuntajeVariableCampanaFormato1XLS";
			break;
		case 2:
			reporteFileName="reporteLIDPuntajeVariableCampanaFormato2XLS";
			break;
		case 3:
			reporteFileName="reporteLIDPuntajeVariableCampanaFormato3XLS";			
			break;			
		case 4:
			reporteFileName="reporteLIDPuntajeVariableCampanaFormato8XLS";			
			break;
		}
		if ("XLS".equals(formatoReporte))
			return reporteFileName;
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String subReporte="";
		switch(tipoFormato){
		case 1:
			subReporte="reporteLIDPuntajeVariableCampanaFormato1PDF";
			break;
		case 2:
			subReporte="reporteLIDPuntajeVariableCampanaFormato2PDF";
			break;
		case 3:
			subReporte="reporteLIDPuntajeVariableCampanaFormato3PDF";			
			break;
		case 4:
			subReporte="reporteLIDPuntajeVariableCampanaFormato8PDF";
		}		
		 if("PDF".equals(formatoReporte))
			 return subReporte;
		return subReporte;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteLIDPuntajeVariableCampanaAction- prepareParameterMap...");
		}
		
		try{
			ReporteLIDPuntajeVariableCampanaForm reporteLIDForm = (ReporteLIDPuntajeVariableCampanaForm) this.formReporte;
			formatoReporte = reporteLIDForm.getFormatoExportacion();
			tipoFormato= getTipoFormatoReporte(reporteLIDForm.getCodigoTipoAsignacion());
			
			if(StringUtils.equals(reporteLIDForm.getCodigoPeriodoInicio(), reporteLIDForm.getCodigoPeriodoFin()))
				indicadorPeriodo="0";
			else
				indicadorPeriodo="1";
			
			String condicionRegion = "";
			String condicionZona = "";
			String condicionSeccion = "";
			String condicionRegionInner = "";
			String condicionZonaInner = "";
			String condicionUA = "";
			
			if(tipoFormato == 1 || tipoFormato == 2 || tipoFormato == 4){
				
				//-- Comprobar si la regin, zona y seccin traen datos
				String[] codigoRegion = null;
				String[] codigoZona = null;
				String[] codigoSeccion = null;
				boolean indicadorRegion = true;
				boolean indicadorZona = true;
				boolean indicadorSeccion = true;
				
				if(reporteLIDForm.getCodigoRegion().length == 1 && reporteLIDForm.getCodigoRegion()[0].equals("")){
					codigoRegion = null;
					indicadorRegion = false;
				}else{
					codigoRegion = reporteLIDForm.getCodigoRegion();
					indicadorRegion = true;
				}
	
				if(reporteLIDForm.getCodigoZona().length == 1 && reporteLIDForm.getCodigoZona()[0].equals("")){
					codigoZona = null;
					indicadorZona = false;
				}else{
					codigoZona = reporteLIDForm.getCodigoZona();
					indicadorZona = true;
				}
	
				if(reporteLIDForm.getCodigoSeccion().length == 1 && reporteLIDForm.getCodigoSeccion()[0].equals("")){
					codigoSeccion = null;
					indicadorSeccion = false;
				}else{
					codigoSeccion = reporteLIDForm.getCodigoSeccion();
					indicadorSeccion = true;
				}
				
				//-- Logica query dinamico
				condicionUA = FiltroDinamicoUA(
					codigoRegion,
					codigoZona,
					codigoSeccion,
					indicadorRegion,
					indicadorZona,
					indicadorSeccion,
					"SUB.COD_SUBG_VENT || REG.COD_REGI || ZON.COD_ZONA || SEC.COD_SECC",
					"'"
				);
			
			}
	
			if(tipoFormato==3){
				 condicionRegion = this.obtieneCondicion(reporteLIDForm.getCodigoRegion(), "sie.COD_REGI", "'");
				 condicionZona = this.obtieneCondicion(reporteLIDForm.getCodigoZona(), "sie.COD_ZONA", "'");
				 condicionSeccion = this.obtieneCondicion(reporteLIDForm.getCodigoSeccion(), "sie.COD_SECC", "'");
			}
			
			params.put("titulo", this.mPantallaPrincipalBean.getResourceMessage("reporteLIDPuntajeVariableCampanaForm.titulo"));			
			params.put("codigoPais",reporteLIDForm.getCodigoPais());
			params.put("codigoTipoAsignacion",reporteLIDForm.getCodigoTipoAsignacion());
			params.put("codigoPeriodoInicio",reporteLIDForm.getCodigoPeriodoInicio());
			params.put("codigoPeriodoFin",reporteLIDForm.getCodigoPeriodoFin());
			params.put("condicionRegion", condicionRegion!=null?condicionRegion:"");
			params.put("condicionZona", condicionZona!=null?condicionZona:"");
			params.put("condicionSeccion", condicionSeccion!=null?condicionSeccion:"");
			params.put("condicionRegionInner", condicionRegionInner!=null?condicionRegionInner:"");
			params.put("condicionZonaInner", condicionZonaInner!=null?condicionZonaInner:"");
			params.put("codigoPeriodo",reporteLIDForm.getCodigoPeriodoInicio());
			params.put("condicionUA", condicionUA);
					
			reporteLIDForm.setBeforeExecuteReporte(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteLIDPuntajeVariableCampanaAction- setViewAtributes...");
		}
		
		this.mostrarReporteXLS = true;
		//-- Variables
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoINCHabilitacionConcursoCargaPuntajeService concursoService = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		MantenimientoLIDFactorPuntajeService mantenimientoLIDFactorPuntajeService = (MantenimientoLIDFactorPuntajeService)getBean("spusicc.mantenimientoLIDFactorPuntajeService");
		ReporteLIDPuntajeVariableCampanaForm form = (ReporteLIDPuntajeVariableCampanaForm) this.formReporte;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		formatoReporte = form.getFormatoExportacion();
		
		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		
		//-- enviando la listas
		List listTipoAsignacion = mantenimientoLIDFactorPuntajeService.getTipoAsignacionPuntajeList();
		
		//-- Agregar a Tipo Asignacion - Todas las variables
		Base base = new Base();
		base.setCodigo(Constants.LID_REPORTE_PUNTAJE_TODAS_VARIABLES_VALOR);
		base.setDescripcion(Constants.LID_REPORTE_PUNTAJE_TODAS_VARIABLES);
		listTipoAsignacion.add(0, base);

		//-- Peticiones
		setTipoAsignacionPuntajeList(listTipoAsignacion);
		//-- Regiones
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion));
	
		log.info("Salio a ReporteLIDPuntajeVariableCampanaAction - setViewAttributes");
		
	}
	
	//-- Zonas
	public void loadZonas(ValueChangeEvent val){
		
			log.debug(">>loadZonas...");
			log.debug(">>val: "+val.getNewValue());
			String[] regiones =(String[]) val.getNewValue();

			for(int i=0; i<regiones.length;i++){
				log.debug(">>Elem: "+regiones[i].toString());
			}
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ReporteLIDPuntajeVariableCampanaForm form = (ReporteLIDPuntajeVariableCampanaForm) this.formReporte;
			setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, "T"));
			this.siccSeccionList = null;
			form.setCodigoZona(null);
				
	}
	
	//-- Seccion
		public void loadSeccion(ValueChangeEvent val){
			
				log.debug(">>loadSeccion...");
				log.debug(">>val: "+(String[]) val.getNewValue());
				String[] zonas = (String[]) val.getNewValue();

				for(int i=0; i<zonas.length;i++){
					log.debug(">>Elem: "+zonas[i].toString());
				}
				
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				ReporteLIDPuntajeVariableCampanaForm form = (ReporteLIDPuntajeVariableCampanaForm) this.formReporte;
				setSiccSeccionList(aSvc.getSeccionMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
											Constants.CODIGO_CANAL_DEFAULT, form.getCodigoRegion(), zonas, "T"));
				
				form.setCodigoSeccion(null);
			
		}
		
		public void changeStatus(ValueChangeEvent val){
			try {
				log.debug(">>changeStatus...");
				log.debug(">>val: "+ val.getNewValue().toString());
				
				String valorTA = val.getNewValue().toString();
				if(valorTA.equals("06"))
					setHabilita(false);
				else
					setHabilita(true);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
	
	private int getTipoFormatoReporte(String codigoTipoAsignacion) {
		int codigo =Integer.parseInt(codigoTipoAsignacion);
		switch(codigo){
		case 1:			
		case 2:			
			 tipoFormato=1;
			 break;
		case 3:				
			tipoFormato=2;
			break;
		case 4:			
		case 5:			
			tipoFormato=3;
			break;	
		case 6:
			tipoFormato=4;
			break;
		}		
		log.debug("tipoFormato " + tipoFormato);
		return tipoFormato;
	}
	
	/**
	 * Metodo que forma el query dinamico para generar con region, zona, seccion  seleccionados
	 * @param codigoRegion
	 * @param codigoZona
	 * @param codigoSeccion
	 * @param indicadorRegion
	 * @param indicadorZona
	 * @param indicadorSeccion
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	private String FiltroDinamicoUA(String[] codigoRegion, String[] codigoZona, String[] codigoSeccion, boolean indicadorRegion, boolean indicadorZona, boolean indicadorSeccion, String parametro, String comilla){
		
		final String uaInicial = "01";
		final String comodin = "%";
		List arrUA = new ArrayList();
		String uaConcateado = null;
		String resultado = new String();
		
		if(indicadorRegion && indicadorZona && indicadorSeccion){
			
			//-- Contruir Arreglo de UAs
			for(int i=0;i<codigoRegion.length;i++)
				for(int j=0;j<codigoZona.length;j++)
					for(int k=0;k<codigoSeccion.length;k++){
						uaConcateado = uaInicial.concat(codigoRegion[i]).concat(codigoZona[j]).concat(codigoSeccion[k]);
						arrUA.add(uaConcateado);
					}
			
			//-- Construir query dinamico
			uaConcateado = null;
			for(int i=0;i<arrUA.size();i++){
				uaConcateado = (String)arrUA.get(i);
				if(i == 0)
					resultado += "(".concat(comilla).concat(uaConcateado).concat(comilla);
				else
					resultado += ",".concat(comilla).concat(uaConcateado).concat(comilla);
			}
			resultado += ")";
			resultado = " AND ".concat(parametro).concat(" IN ").concat(resultado); 
			
		}else if(indicadorRegion && indicadorZona && !indicadorSeccion){
			
			//-- Contruir Arreglo de UAs
			for(int i=0;i<codigoRegion.length;i++)
				for(int j=0;j<codigoZona.length;j++){
					uaConcateado = uaInicial.concat(codigoRegion[i]).concat(codigoZona[j]).concat(comodin);
					arrUA.add(uaConcateado);
				}
			
			//-- Construir query dinamico
			uaConcateado = null;
			for(int i=0;i<arrUA.size();i++){
				uaConcateado = (String)arrUA.get(i);
				if(i == 0)
					resultado += "(".concat(parametro).concat(" LIKE ").concat(comilla).concat(uaConcateado).concat(comilla);
				else
					resultado += " OR ".concat(parametro).concat(" LIKE ").concat(comilla).concat(uaConcateado).concat(comilla);
			}
			resultado += ")";
			resultado = " AND ".concat(resultado); 
			
		}else if(indicadorRegion && !indicadorZona & !indicadorSeccion){
			
			//-- Contruir Arreglo de UAs
			for(int i=0;i<codigoRegion.length;i++){
				uaConcateado = uaInicial.concat(codigoRegion[i]).concat(comodin);
				arrUA.add(uaConcateado);
			}
			
			//-- Construir query dinamico
			uaConcateado = null;
			for(int i=0;i<arrUA.size();i++){
				uaConcateado = (String)arrUA.get(i);
				if(i == 0)
					resultado += "(".concat(parametro).concat(" LIKE ").concat(comilla).concat(uaConcateado).concat(comilla);
				else
					resultado += " OR ".concat(parametro).concat(" LIKE ").concat(comilla).concat(uaConcateado).concat(comilla);
			}
			resultado += ")";
			resultado = " AND ".concat(resultado); 
			
		}else
			resultado = "";
			
		return resultado;
	}
	
	/*GETTERS && SETTERS*/

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public int getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(int tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public String getIndicadorPeriodo() {
		return indicadorPeriodo;
	}

	public void setIndicadorPeriodo(String indicadorPeriodo) {
		this.indicadorPeriodo = indicadorPeriodo;
	}

	public boolean isHayQuiebre() {
		return hayQuiebre;
	}

	public void setHayQuiebre(boolean hayQuiebre) {
		this.hayQuiebre = hayQuiebre;
	}

	public List getTipoAsignacionPuntajeList() {
		return tipoAsignacionPuntajeList;
	}

	public void setTipoAsignacionPuntajeList(List tipoAsignacionPuntajeList) {
		this.tipoAsignacionPuntajeList = tipoAsignacionPuntajeList;
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

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	public boolean isHabilita() {
		return habilita;
	}

	public void setHabilita(boolean habilita) {
		this.habilita = habilita;
	}
	
}