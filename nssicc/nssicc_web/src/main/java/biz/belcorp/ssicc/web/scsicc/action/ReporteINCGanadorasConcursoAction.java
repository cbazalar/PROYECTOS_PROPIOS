package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCGanadorasConcursoForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCGanadorasConcursoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1019966864661672117L;
	private String formatoReporte;
	private int tipoReporte;
	private String tipoPlantilla;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccTipoReporteList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccConcursoList;
	private boolean habilitaUbigeo;
	private boolean cb1;
	private boolean cb2;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCGanadorasConcursoForm form = new ReporteINCGanadorasConcursoForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporteFileName="";
		switch(tipoReporte){
		case 0:
			reporteFileName="reporteINCGanadorasConcursoDespachadosXLS";
			break;
		case 1:
			if (Constants.NUMERO_CERO.equals(tipoPlantilla))
				reporteFileName="reporteINCGanadorasConcursoPendientesXLS";
			else
				reporteFileName="reporteINCGanadorasConcursoPendientes2XLS";
			break;
		case 2:
			if (Constants.NUMERO_CERO.equals(tipoPlantilla))
				reporteFileName="reporteINCGanadorasConcursoTodosXLS";
			else
				reporteFileName="reporteINCGanadorasConcursoTodos2XLS";
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
		switch(tipoReporte){
		case 0:
			subReporte="reporteINCGanadorasConcursoDespachadosPDF";
			break;
		case 1:
			if (Constants.NUMERO_CERO.equals(tipoPlantilla))
				subReporte="reporteINCGanadorasConcursoPendientesPDF";
			else
				subReporte="reporteINCGanadorasConcursoPendientes2PDF";
			break;
		case 2:
			if (Constants.NUMERO_CERO.equals(tipoPlantilla))
				subReporte="reporteINCGanadorasConcursoTodosPDF";
			else
				subReporte="reporteINCGanadorasConcursoTodos2PDF";
			break;
		}		
		 if("PDF".equals(formatoReporte))
			 return subReporte;
		return subReporte;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug(">>ReporteINCGanadorasConcursoAction - prepareParameterMap");
		}
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		formatoReporte = form.getFormatoExportacion();
		
		String condicionRegion = "";
		String condicionZona = "";
		if(this.habilitaUbigeo)
			form.setIndicadorRegion(Constants.NUMERO_UNO);	
		
		if(StringUtils.equals(form.getIndicadorRegion(), Constants.NUMERO_UNO)){
			condicionRegion = this.obtieneCondicion(form.getCodigoRegion(), "r.COD_REGI", "'");
			condicionZona = this.obtieneCondicion(form.getCodigoZona(), "z.COD_ZONA", "'");
		}
		tipoReporte = Integer.parseInt(form.getCodigoTipoReporte()); 
		log.debug("tipoReporte " + tipoReporte);
	
		params.put("codigoPais",form.getCodigoPais());
		params.put("codigoMarca",form.getCodigoMarca());
		params.put("codigoCanal",form.getCodigoCanal());
		params.put("codigoConcurso",form.getOidConcurso());//viaja el oidConcurso 
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("codigoTerritorio", form.getCodigoTerritorio());
		params.put("codigoConsultora", form.getCodigoConsultora());
		params.put("oidMarca",Integer.valueOf(reporteService.getOidString("getOidMarcaByCodigoMarca", params)));
		params.put("oidCanal",Integer.valueOf(reporteService.getOidString("getOidCanalByCodigoCanal", params)));

		tipoPlantilla = reporteService.getTipoPlantilla(params);

		//obteniendo el titulo del reporte
		String descripcionTipoReporte = getDescripcionTipoReporte(tipoReporte);
		
		//String numeroConcurso = getNumeroConcurso(request.getSession(),f.getOidConcurso());		
	
		//String [] parametros = new String[]{descripcionTipoReporte,numeroConcurso};
		
		String titulo = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcursoForm.titulo");
		form.setTitulo(titulo); 
		
		params.put("titulo", form.getTitulo());
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug(">>ReporteINCGanadorasConcursoAction - setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		MantenimientoINCHabilitacionConcursoCargaPuntajeService concursoService = 
					(MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
				
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		
		//seteando la lista de concurso vigentes y cerrados
		List listConcursoLideres = concursoService.getListConcursosVigentesCerrados();
		this.siccConcursoList = listConcursoLideres;
		
		//Metodo q llena la lista de tipoReportes.
		setListTipoReporte();
		
		//marca y canal
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
				
		
		//seteando la region, zona 
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		setSiccRegionList(aSvc.getRegionesByPais(pais.getCodigo()));
		
		this.cb1 = false;
		this.cb2 = false;
	}
	
	/**
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val){
			
		String[] regiones = (String[]) val.getNewValue();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), form.getCodigoMarca(), form.getCodigoCanal(), regiones, Constants.FORMATO_TOTAL));
		form.setCodigoZona(null);
	}
	
	/**
	 * Carga Combo Multiple de Regiones por Marca
	 * @param val
	 */
	public void loadRegionesMarca(ValueChangeEvent val){
		
		log.debug(">>loadRegionesMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), valor, 
				form.getCodigoCanal(), Constants.FORMATO_TOTAL));
		form.setCodigoRegion(null);
		setSiccZonaList(null);
		form.setCodigoZona(null);
	}
	
	/**
	 * Carga Combo Multiple de Regiones por Marca
	 * @param val
	 */
	public void loadRegionesCanal(ValueChangeEvent val){
		
		log.debug(">>loadRegionesMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
				valor, Constants.FORMATO_TOTAL));
		form.setCodigoRegion(null);
		setSiccZonaList(null);
		form.setCodigoZona(null);
	}
	
	/**
	 * @param val
	 */
	public void changeStatus1(ValueChangeEvent val) {

		boolean status = (Boolean) val.getNewValue();
		if (!status) {
			reiniciaLabelRegiones();
		}
		setCb1(false);
		setCb2(false);
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		form.setCodigoTerritorio(null);
		form.setCodigoConsultora(null);
	}

	/**
	 * @param val
	 */
	public void changeStatusCb1(ValueChangeEvent val) {
		boolean status = (Boolean) val.getNewValue();
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		reiniciaLabelRegiones();
		setHabilitaUbigeo(false);
		setCb2(false);
		form.setCodigoConsultora(null);
	}

	/**
	 * @param val
	 */
	public void changeStatusCb2(ValueChangeEvent val) {
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		reiniciaLabelRegiones();
		setHabilitaUbigeo(false);
		setCb1(false);
		form.setCodigoTerritorio(null);
	}
	
	/**
	 * 
	 */
	public void reiniciaLabelRegiones() {
		LabelValue[] regiones = new LabelValue[1];
		regiones[0] = new LabelValue("Todos", "");
		setSiccZonaList(regiones);
	}
	
	/**
	 * Obtiene Lista de Tipo de Reporte
	 */
	public void setListTipoReporte() {
		List resultado = new ArrayList();
		Base[] mes = new Base[3];
		
		String mensajePremiosDespachados = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcursoForm.tipoPremiosDespachados");
		String mensajePremiosPendientes = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcursoForm.tipoPremiosPendientes");
		String mensajePremiosTodos = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcursoForm.tipoPremiosTodos");
		for(int i=0;i<mes.length;i++){			
			mes[i] = new Base();
			mes[i].setCodigo(String.valueOf(i));
			switch(i){
			case 0 :
				mes[i].setDescripcion(mensajePremiosDespachados);
				break;
			case 1 :
				mes[i].setDescripcion(mensajePremiosPendientes);
				break;
			case 2 :
				mes[i].setDescripcion(mensajePremiosTodos);
				break;
			}			
			resultado.add(mes[i]);
		}

		this.siccTipoReporteList = resultado;
		
	}
	
	/**
	 * @param tipoReporte
	 * @return
	 */
	private String getDescripcionTipoReporte(int tipoReporte) {
		String descripcion="";
		//MessageResources messageResources = getResources(request);
		switch(tipoReporte){
		case 0:
			descripcion = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcursoForm.tipoPremiosDespachados");
			break;
		case 1:
			descripcion = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcursoForm.tipoPremiosPendientes");
			break;
		case 2:
			descripcion = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcursoForm.tipoPremiosTodos2");
			break;
		}		
		return descripcion;
	}
	
	
	/**
	 * @param oidConcurso
	 * @return
	 */
	private String getNumeroConcurso(String oidConcurso) {
		String numeroConcurso="";
		//List listConcursos=(List) sesion.getAttribute(Constants.INC_CONCURSO_VIGENTES_CERRADOS_LIST);
		List listConcursos = new ArrayList();
		Iterator it = listConcursos.iterator();
		while (it.hasNext()){
			Base concurso = (Base)it.next();
			if(oidConcurso.equals(concurso.getCodigo())){				
				numeroConcurso = concurso.getDescripcion();
				break;
			}			
		}
		return numeroConcurso;
	}
	
	/**
	 * @param event
	 */
	public void obtenerConcursosByPaisMarcaCanal(ValueChangeEvent event) {
		String codigoMarca = (String) event.getNewValue();
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		form.setCodigoMarca(codigoMarca);
		obtenerConcursos(form);

	}

	/**
	 * @param event
	 */
	public void obtenerConcursosByPaisCanalMarca(ValueChangeEvent event) {
		String codigoCanal = (String) event.getNewValue();
		ReporteINCGanadorasConcursoForm form = (ReporteINCGanadorasConcursoForm) this.formReporte;
		form.setCodigoCanal(codigoCanal);
		obtenerConcursos(form);
	}

	/**
	 * @param form
	 */
	public void obtenerConcursos(ReporteINCGanadorasConcursoForm form) {
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(pais.getCodigo(),
		// form.getCodigoMarca(), form.getCodigoCanal(), ""));
		MantenimientoINCHabilitacionConcursoCargaPuntajeService concursoService = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		setSiccConcursoList(concursoService.getListConcursosVigentesCerrados());
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public int getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(int tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getTipoPlantilla() {
		return tipoPlantilla;
	}

	public void setTipoPlantilla(String tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
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

	

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	

	public boolean isHabilitaUbigeo() {
		return habilitaUbigeo;
	}

	public void setHabilitaUbigeo(boolean habilitaUbigeo) {
		this.habilitaUbigeo = habilitaUbigeo;
	}

	public boolean isCb1() {
		return cb1;
	}

	public void setCb1(boolean cb1) {
		this.cb1 = cb1;
	}

	public boolean isCb2() {
		return cb2;
	}

	public void setCb2(boolean cb2) {
		this.cb2 = cb2;
	}

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccTipoReporteList() {
		return siccTipoReporteList;
	}

	public void setSiccTipoReporteList(List siccTipoReporteList) {
		this.siccTipoReporteList = siccTipoReporteList;
	}
	
	

}