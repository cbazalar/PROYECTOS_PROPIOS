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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCPuntObtenidosBolsaFaltantesIncForm;
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
public class ReporteINCPuntObtenidosBolsaFaltantesIncAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 6520458850546758879L;
	private String tipReporte;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccZonaListIni;
	private List siccConcursoList;
	private List tipoReporteList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPuntObtenidosBolsaFaltantesIncForm form = new ReporteINCPuntObtenidosBolsaFaltantesIncForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			// return "reporteVENConEgresadaXLS";
			return "reporteINCPuntObtenidosBolsaFaltantesIncXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (("PDF".equals(formatoReporte)) || ("VPDF".equals(formatoReporte)))
			return "reporteINCPuntObtenidosBolsaFaltantesInc" + tipReporte;
		return "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCPuntObtenidosBolsaFaltantesIncService";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteINCPuntObtenidosBolsaFaltantesIncAction - prepareParameterMap...");
		}
		ReporteINCPuntObtenidosBolsaFaltantesIncForm reporteINCForm = (ReporteINCPuntObtenidosBolsaFaltantesIncForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		formatoReporte = reporteINCForm.getFormatoExportacion();
		Map criteria = params;

		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		String codigoConcurso = (String) params.get("codigoConcurso");
		String tipoFaltante = (String) params.get("tipoFaltante");
		String condicionZonas = "";
		String condicionRegion = "";
		String condicionConcurso = "";

		if ("Todos".equals(codigoConcurso) || StringUtils.isEmpty(codigoConcurso)) {
			codigoConcurso = null;
			params.put("codigoConcurso", codigoConcurso);
		} else {
			codigoConcurso = StringUtil.deArrayAStringToken(reporteINCForm.getCodigoConcurso());
			String[] arregloConcurso = StringUtil.obtenerArreglo(codigoConcurso, ",");
			for (int i = 0; i < arregloConcurso.length; i++) {
				criteria.put("numeroConcurso", arregloConcurso[i]);
				arregloConcurso[i] = reporteService.getOidConcurso("getOidConcursoByNumConcurso", criteria);
			}
			condicionConcurso = obtieneCondicionIN(arregloConcurso, "", "'");
		}

		if ("Todos".equals(codigoRegion) || StringUtils.isEmpty(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		} else {
			condicionRegion = obtieneCondicion(reporteINCForm.getCodigoRegion(), "COD_REGI", "'");
		}

		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZonas = obtieneCondicion(reporteINCForm.getCodigoZona(), "COD_ZONA", "'");
		}
		
		if ("Todos".equals(tipoFaltante) || StringUtils.isEmpty(tipoFaltante)) {
			params.put("condicionFaltante", "");
		} else {
			if(tipoFaltante.equals("1"))
				params.put("condicionFaltante", "and FEC_SOLU is null");
			else //2
				params.put("condicionFaltante", "and (FEC_SOLU is null or INC_BOLSA_FALTA.val_obse like 'Reemplazado por%')");
		}

		tipReporte = reporteINCForm.getDetalleTipoReporte();
		String condicion = condicionZonas + condicionRegion;
		params.put("condicion", condicion);
		params.put("NroReporte", "REP-INC02");
		
		if(StringUtils.isNotBlank(condicionConcurso))
			params.put("numeroConcurso", " and INC_CONCU_PARAM_GENER.OID_PARA_GRAL " + condicionConcurso);
		else 
			params.put("numeroConcurso", "");
		
		params.put("titulo", "Informe Bolsa Faltantes de Incentivos ("+ tipReporte + ")");
		//this.setVirtualizador(true);q
		
		if(StringUtils.isNotBlank(reporteINCForm.getPeriodoInicio()) && StringUtils.isNotBlank(reporteINCForm.getPeriodoFin())){
			params.put("condicionPeriodoInicio", " AND INC_BOLSA_FALTA.PERD_OID_PERI >= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2('" + reporteINCForm.getPeriodoInicio() +"') ");
			params.put("condicionPeriodoFin", " AND INC_BOLSA_FALTA.PERD_OID_PERI <= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2('" + reporteINCForm.getPeriodoFin() + "') ");
		}else{
			params.put("condicionPeriodoInicio", "");
			params.put("condicionPeriodoFin", "");
		}
		
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteINCPuntObtenidosBolsaFaltantesIncAction - setViewAtributes...");
		}
		
		/*this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;*/
		ReporteINCPuntObtenidosBolsaFaltantesIncForm reporteINCForm = (ReporteINCPuntObtenidosBolsaFaltantesIncForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		formatoReporte = reporteINCForm.getFormatoExportacion();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		reporteINCForm.setCodigoPais(pais.getCodigo());
		reporteINCForm.setDescPais(pais.getDescripcion());
		
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		setSiccMarcaList(service.getMarcas());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		setSiccConcursoList(reporteService.getListaGenerico("getConcursosFaltantesByPaisMarcaCanalDetalle",	criteriaOperacion));
		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT);
		reporteINCForm.setCodigoPeriodo(codigoPeriodo);
		
		
		//Carga de tipoReporteList
		ArrayList<Base> tipoReporte = new ArrayList<Base>();
		Base[] baseTipos = new Base[4]; 
		baseTipos[0] = new Base();
		baseTipos[0].setCodigo(this.mPantallaPrincipalBean.getResourceMessage("select.concurso"));
		baseTipos[0].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.concurso"));
		baseTipos[1] = new Base();
		baseTipos[1].setCodigo("Region");
		baseTipos[1].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.region"));
		baseTipos[2] = new Base();
		baseTipos[2].setCodigo(this.mPantallaPrincipalBean.getResourceMessage("select.zona"));
		baseTipos[2].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.zona"));
		baseTipos[3] = new Base();
		baseTipos[3].setCodigo(this.mPantallaPrincipalBean.getResourceMessage("select.consultora"));
		baseTipos[3].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("select.consultora"));
		
		for(int i=0; i<4; i++){
			tipoReporte.add(baseTipos[i]);
		}
		tipoReporteList = tipoReporte;
		
		if(StringUtils.equals(reporteINCForm.getDetalleTipoReporte(), this.mPantallaPrincipalBean.getResourceMessage("select.consultora"))){
			this.mostrarReporteXLS = true;
			this.mostrarReporteOCSV = true; 
		}else{
			this.mostrarReporteXLS = false;
			this.mostrarReporteOCSV = false;
		}
		
		reporteINCForm.setTipoFaltante("1");
		
		log.debug("Todo Ok: Redireccionando");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("ReporteINCPuntObtenidosBolsaFaltantesIncAction - setValidarReporte...");
		}
		
		ReporteINCPuntObtenidosBolsaFaltantesIncForm reporteINCForm = (ReporteINCPuntObtenidosBolsaFaltantesIncForm) this.formReporte;
		
		String mensaje = "";
		
		if(StringUtils.isNotBlank(reporteINCForm.getPeriodoInicio()) && StringUtils.isBlank(reporteINCForm.getPeriodoFin())){
			mensaje = "Debe ingresar Periodo Fin";
		}
		
		if(StringUtils.isBlank(reporteINCForm.getPeriodoInicio()) && StringUtils.isNotBlank(reporteINCForm.getPeriodoFin())){
			mensaje = "Debe ingresar Periodo Inicio";
		}
		
		if(StringUtils.isNotBlank(reporteINCForm.getPeriodoInicio()) && StringUtils.isNotBlank(reporteINCForm.getPeriodoFin())){
			int periodoInicio = Integer.parseInt(reporteINCForm.getPeriodoInicio());
			int periodoFin = Integer.parseInt(reporteINCForm.getPeriodoFin());
			
			if(periodoInicio > periodoFin){
				mensaje = "Periodo Fin debe ser mayor o igual a Periodo Inicio";
			}
		}
		
		return mensaje;
	}

	/**
	 * @param val
	 */
	public void loadRegiones(ValueChangeEvent val){
		try {
			log.debug(">>loadRegiones...");
			log.debug(">>val: "+val.getNewValue().toString());
			String valor = val.getNewValue().toString();
			
			if(StringUtils.equals(valor, this.mPantallaPrincipalBean.getResourceMessage("select.consultora"))){
				this.mostrarReporteXLS = true;
				this.mostrarReporteOCSV = true;
			}else{
				this.mostrarReporteXLS = false;
				this.mostrarReporteOCSV = false;
			}
			
			ReporteINCPuntObtenidosBolsaFaltantesIncForm form = (ReporteINCPuntObtenidosBolsaFaltantesIncForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccRegionList(aSvc.getRegionesByPaisMarcaCanalDetalle(form.getCodigoPais(), form.getCodigoMarca(), 
					form.getCodigoCanal(), valor));
			this.siccZonaList = this.siccZonaListIni;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadZonas(ValueChangeEvent val){
		try {
			log.debug(">>loadZonas...");
			log.debug(">>val: "+(String[]) val.getNewValue());
			
			String[] regiones = (String[]) val.getNewValue();
			for(int i=0; i<regiones.length; i++){
				log.debug("region["+i+"]: " + regiones[i]);
			}
			
			ReporteINCPuntObtenidosBolsaFaltantesIncForm form = (ReporteINCPuntObtenidosBolsaFaltantesIncForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
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
			
			ReporteINCPuntObtenidosBolsaFaltantesIncForm form = (ReporteINCPuntObtenidosBolsaFaltantesIncForm) this.formReporte;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			
			log.debug(">>>>>> codigoMarca: " + form.getCodigoMarca());
			log.debug(">>>>>> codigoCanal: " + form.getCodigoCanal());
			
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
			criteriaOperacion.put("codigoMarca", valor);
			criteriaOperacion.put("codigoCanal", form.getCodigoCanal());
			
			setSiccConcursoList(reporteService.getListaGenerico("getConcursosFaltantesByPaisMarcaCanalDetalle",	criteriaOperacion));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadConcursosPorCanal(ValueChangeEvent val){
		try {
			log.debug(">>loadConcursosPorCanal...");
			log.debug(">>val: "+val.getNewValue().toString());
			String valor = val.getNewValue().toString();
			
			ReporteINCPuntObtenidosBolsaFaltantesIncForm form = (ReporteINCPuntObtenidosBolsaFaltantesIncForm) this.formReporte;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			
			log.debug(">>>>>> codigoMarca: " + form.getCodigoMarca());
			log.debug(">>>>>> codigoCanal: " + form.getCodigoCanal());
			
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
			criteriaOperacion.put("codigoMarca", form.getCodigoMarca());
			criteriaOperacion.put("codigoCanal", valor);
			
			setSiccConcursoList(reporteService.getListaGenerico("getConcursosFaltantesByPaisMarcaCanalDetalle",	criteriaOperacion));
			
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

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getTipoReporteList() {
		return tipoReporteList;
	}

	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}	
}
