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
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCGanadorasConcurso2Form;

@ManagedBean
@SessionScoped
public class ReporteINCGanadorasConcurso2Action extends BaseReporteAbstractAction {
	private static final long serialVersionUID = 1L;
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
		ReporteINCGanadorasConcurso2Form form = new ReporteINCGanadorasConcurso2Form();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporteFileName = "";
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;

		String tipoConcurso = getTipoConcurso(form);
		switch (Integer.parseInt(form.getCodigoTipoReporte())) {
		case 0: // DESPACHADAS
			if (StringUtils.equals(Constants.NUMERO_UNO, tipoConcurso)
					|| StringUtils.equals(Constants.NUMERO_DOS, tipoConcurso))
				reporteFileName = "reporteINCGanadorasConcursoVentasDespachadosXLS";

			if (StringUtils.equals(Constants.NUMERO_CUATRO, tipoConcurso))
				reporteFileName = "reporteINCGanadorasConcursoRecomendacionDespachadosXLS";

			break;
		case 1: // PENDIENTES
			if (StringUtils.equals(Constants.NUMERO_UNO, tipoConcurso)
					|| StringUtils.equals(Constants.NUMERO_DOS, tipoConcurso))
				reporteFileName = "reporteINCGanadorasConcursoVentasPendientesXLS";

			if (StringUtils.equals(Constants.NUMERO_CUATRO, tipoConcurso))
				reporteFileName = "reporteINCGanadorasConcursoRecomendacionPendientesXLS";

			break;
		case 2: // TODOS
			if (StringUtils.equals(Constants.NUMERO_UNO, tipoConcurso)
					|| StringUtils.equals(Constants.NUMERO_DOS, tipoConcurso))
				reporteFileName = "reporteINCGanadorasConcursoVentasXLS";

			if (StringUtils.equals(Constants.NUMERO_CUATRO, tipoConcurso))
				reporteFileName = "reporteINCGanadorasConcursoRecomendacionXLS";

			break;
		}

		if ("XLS".equals(this.getFormatoExportacion()))
			return reporteFileName;
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String subReporte = "";
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;
		String tipoConcurso = getTipoConcurso(form);
		switch (Integer.parseInt(form.getCodigoTipoReporte())) {
		case 0: // DESPACHADAS
			if (StringUtils.equals(Constants.NUMERO_UNO, tipoConcurso)
					|| StringUtils.equals(Constants.NUMERO_DOS, tipoConcurso))
				subReporte = "reporteINCGanadorasConcursoVentasDespachadosPDF";

			if (StringUtils.equals(Constants.NUMERO_CUATRO, tipoConcurso))
				subReporte = "reporteINCGanadorasConcursoRecomendacionDespachadosPDF";

			break;
		case 1: // PENDIENTES
			if (StringUtils.equals(Constants.NUMERO_UNO, tipoConcurso)
					|| StringUtils.equals(Constants.NUMERO_DOS, tipoConcurso))
				subReporte = "reporteINCGanadorasConcursoVentasPendientesPDF";

			if (StringUtils.equals(Constants.NUMERO_CUATRO, tipoConcurso))
				subReporte = "reporteINCGanadorasConcursoRecomendacionPendientesPDF";

			break;
		case 2: // TODOS
			if (StringUtils.equals(Constants.NUMERO_UNO, tipoConcurso)
					|| StringUtils.equals(Constants.NUMERO_DOS, tipoConcurso))
				subReporte = "reporteINCGanadorasConcursoVentasPDF";

			if (StringUtils.equals(Constants.NUMERO_CUATRO, tipoConcurso))
				subReporte = "reporteINCGanadorasConcursoRecomendacionPDF";

			break;
		}

		if ("PDF".equals(this.getFormatoExportacion()))
			return subReporte;
		return subReporte;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteINCGanadorasConcurso2Form f = (ReporteINCGanadorasConcurso2Form) this.getFormReporte();

		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");

		String condicionRegion = "";
		String condicionZona = "";
		if(this.habilitaUbigeo)
			f.setIndicadorRegion(Constants.NUMERO_UNO);
		
		if (StringUtils.equals(f.getIndicadorRegion(), Constants.NUMERO_UNO)) {
			condicionRegion = this.obtieneCondicion(f.getCodigoRegion(), "r.COD_REGI", "'");
			condicionZona = this.obtieneCondicion(f.getCodigoZona(), "z.COD_ZONA", "'");
		}
		
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("codigoConcurso", f.getOidConcurso());// viaja el oidConcurso
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("codigoTerritorio", f.getCodigoTerritorio());
		params.put("codigoConsultora", f.getCodigoConsultora());
		params.put("oidMarca", Integer.valueOf(reporteService.getOidString("getOidMarcaByCodigoMarca", params)));
		params.put("oidCanal", Integer.valueOf(reporteService.getOidString("getOidCanalByCodigoCanal", params)));

		// obteniendo el titulo del reporte
		String descripcionTipoReporte = getDescripcionTipoReporte(Integer.parseInt(f.getCodigoTipoReporte()));
		String numeroConcurso = this.getNumeroConcurso(f.getOidConcurso());
		String[] parametros = new String[] { descripcionTipoReporte,
				numeroConcurso };
		String titulo = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcurso2Form.titulo", parametros);
		log.debug("titulo reporte " + titulo);
		params.put("titulo", titulo);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		setSiccMarcaList(reporteService.getMarcas());
		setSiccCanalList(reporteService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		setSiccTipoReporteList(getListTipoReporte());

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		setSiccRegionList(aSvc.getRegionesByPais(pais.getCodigo()));

		LabelValue[] regiones = new LabelValue[1];
		regiones[0] = new LabelValue("Todos", "");
		setSiccZonaList(regiones);


		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setIndicadorConsultora(Constants.NUMERO_CERO);
		form.setIndicadorTerritorio(Constants.NUMERO_CERO);
		form.setIndicadorTerritorio(Constants.NUMERO_CERO);
		obtenerConcursos(form);

	}

	/**
	 * @param event
	 */
	public void obtenerConcursosByPaisMarcaCanal(ValueChangeEvent event) {
		String codigoMarca = (String) event.getNewValue();
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;
		form.setCodigoMarca(codigoMarca);
		this.obtenerConcursos(form);

	}

	/**
	 * @param event
	 */
	public void obtenerConcursosByPaisCanalMarca(ValueChangeEvent event) {
		String codigoCanal = (String) event.getNewValue();
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;
		form.setCodigoCanal(codigoCanal);
		this.obtenerConcursos(form);
	}

	/**
	 * @param form
	 */
	public void obtenerConcursos(ReporteINCGanadorasConcurso2Form form) {
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoINCHabilitacionConcursoCargaPuntajeService concursoService = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		setSiccConcursoList(concursoService.getListConcursosVigentesCerrados());
	}

	/**
	 * @param tipoReporte
	 * @return
	 */
	private String getDescripcionTipoReporte(int tipoReporte) {
		String descripcion = "";
		switch (tipoReporte) {
		case 0:
			descripcion = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcurso2Form.tipoPremiosDespachados");
			break;
		case 1:
			descripcion = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcurso2Form.tipoPremiosPendientes");
			break;
		case 2:
			descripcion = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcurso2Form.tipoPremiosTodos2");
			break;
		}
		return descripcion;
	}
	
	
	/**
	 * Carga Combo Multiple de Regiones por Marca
	 * @param val
	 */
	public void loadRegionesMarca(ValueChangeEvent val){
		
		log.debug(">>loadRegionesMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;
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
		
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
				valor, Constants.FORMATO_TOTAL));
		form.setCodigoRegion(null);
		setSiccZonaList(null);
		form.setCodigoZona(null);
	}
	
	
	/**
	 * obtiene la lista tipo Reporte
	 * 
	 * @param request
	 */
	private List getListTipoReporte() {
		List resultado = new ArrayList();
		Base[] mes = new Base[3];
		String mensajePremiosDespachados = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcurso2Form.tipoPremiosDespachados");

		String mensajePremiosPendientes = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcurso2Form.tipoPremiosPendientes");

		String mensajePremiosTodos = this.mPantallaPrincipalBean.getResourceMessage("reporteINCGanadorasConcurso2Form.tipoPremiosTodos");

		for (int i = 0; i < mes.length; i++) {
			mes[i] = new Base();
			mes[i].setCodigo(String.valueOf(i));
			switch (i) {
			case 0:
				mes[i].setDescripcion(mensajePremiosDespachados);
				break;
			case 1:
				mes[i].setDescripcion(mensajePremiosPendientes);
				break;
			case 2:
				mes[i].setDescripcion(mensajePremiosTodos);
				break;
			}
			resultado.add(mes[i]);
		}
		return resultado;

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
	}

	/**
	 * @param val
	 */
	public void changeStatusCb1(ValueChangeEvent val) {
		boolean status = (Boolean) val.getNewValue();
		reiniciaLabelRegiones();
		setHabilitaUbigeo(false);
		setCb2(false);
	}

	/**
	 * @param val
	 */
	public void changeStatusCb2(ValueChangeEvent val) {
		reiniciaLabelRegiones();
		setHabilitaUbigeo(false);
		setCb1(false);
	}

	/**
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {

		String[] regiones = (String[]) val.getNewValue();

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteINCGanadorasConcurso2Form form = (ReporteINCGanadorasConcurso2Form) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), form.getCodigoMarca(), form.getCodigoCanal(), regiones, Constants.FORMATO_TOTAL));
		form.setCodigoZona(null);
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
	 * @param form
	 * @return
	 */
	public String getTipoConcurso(ReporteINCGanadorasConcurso2Form form) {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoConcurso", form.getOidConcurso());
		String tipoConcurso = reporteService.getOidConcurso("getTipoConcurso", criteria);
		return tipoConcurso;
	}
	
	
	/**
	 * obtiene el numero del concurso
	 * @param sesion
	 * @param oidConcurso
	 * @return
	 */
	private String getNumeroConcurso(String oidConcurso) {
		String numeroConcurso="";
		List listConcursos= this.siccConcursoList;
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

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getSiccTipoReporteList() {
		return siccTipoReporteList;
	}

	public void setSiccTipoReporteList(List siccTipoReporteList) {
		this.siccTipoReporteList = siccTipoReporteList;
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

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	
	

}
