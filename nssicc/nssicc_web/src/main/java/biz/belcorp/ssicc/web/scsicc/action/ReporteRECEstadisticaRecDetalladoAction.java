package biz.belcorp.ssicc.web.scsicc.action;

import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECEstadisticaRecDetalladoForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteRECEstadisticaRecDetalladoAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1435748424609950127L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccUnidadNegocioList;
	private List siccEstadoOperacionList;
	private List siccEstadoReclamosList;
	private List siccNegocioList;
	private List siccRegionList;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList={};
	private LabelValue[] siccZonaList={};
	private LabelValue[] siccTerritorioList={};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECEstadisticaRecDetalladoForm reporteForm = new ReporteRECEstadisticaRecDetalladoForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteRECEstadisticaRecDetalladoAction - setViewAtributes");
		}
		
		this.siccMarcaList = new ArrayList();
		this.siccUnidadNegocioList =new ArrayList();
		this.siccEstadoOperacionList = new ArrayList();
		this.siccEstadoReclamosList = new ArrayList();
		this.siccNegocioList = new ArrayList();
		this.siccRegionList = new ArrayList();
		this.siccOperacionesList = new ArrayList();

		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteRECEstadisticaRecDetalladoForm f = (ReporteRECEstadisticaRecDetalladoForm) this.formReporte;
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				criteriaOperacion);
		this.siccUnidadNegocioList = reporteService.getListaGenerico(
				"getListaUnidadNegocio", criteriaOperacion);
		this.siccEstadoOperacionList = reporteService.getListaGenerico(
				"getListaEstadoOperacion", criteriaOperacion);
		this.siccEstadoReclamosList = reporteService.getListaGenerico(
				"getListaEstadoReclamo", criteriaOperacion);
		this.siccNegocioList = reporteService.getListaGenerico(
				"getListaNegocio", criteriaOperacion);
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		f.setOidIdiomaIso(usuario.getIdioma().getCodigoISO());

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteRECEstadisticaRecDetalladoService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECEstadisticaRecDetalladoForm form = (ReporteRECEstadisticaRecDetalladoForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteRECEstadisticaRecDetalladoXLS";
		else
			return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECEstadisticaRecDetalladoForm form = (ReporteRECEstadisticaRecDetalladoForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteRECEstadisticaRecDetalladoPDF";
		else
			return "";

	}

	public String setValidarReporte() {
		ReporteRECEstadisticaRecDetalladoForm form = (ReporteRECEstadisticaRecDetalladoForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
		
		if (form.getCodigoOperacion().length <= 0) 			
			return this.getResourceMessage("reporteRECEstadisticaRecDetalladoForm.codigo.operacion.requerido");
			
		if (form.getTipoOperacionList().length <= 0)		
			return this.getResourceMessage("reporteRECEstadisticaRecDetalladoForm.tipo.operacion.requerido");			
		else{
			if(form.getTipoOperacionList().length ==1 &&  StringUtils.isBlank(form.getTipoOperacionList()[0]))
				return this.getResourceMessage("reporteRECEstadisticaRecDetalladoForm.tipo.operacion.requerido");
		}
		
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		} 
		if (form.getFechaFinalD()!=null && form.getFechaInicialD()!=null) {
			if(form.getFechaFinalD().compareTo(form.getFechaInicialD()) < 0){
				String mensaje = this.getResourceMessage("errors.compare.dates");
				return mensaje;
			}
		
		}
		return null;

	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteRECEstadisticaRecDetalladoForm reporteRECForm = (ReporteRECEstadisticaRecDetalladoForm) this.formReporte;
		formatoReporte = reporteRECForm.getFormatoExportacion();
		limpiarDescripcion();
		
		if(reporteRECForm.getCodigoOperacion().length==1 && StringUtils.isBlank(reporteRECForm.getCodigoOperacion()[0]))
			reporteRECForm.setDescripcionOperacion("Todos");
		if(reporteRECForm.getTipoOperacionList().length==1 && StringUtils.isBlank(reporteRECForm.getTipoOperacionList()[0]))
			reporteRECForm.setDescripcionTipoOperacionList("Todos");
		if(reporteRECForm.getRegionList().length==1 && StringUtils.isBlank(reporteRECForm.getRegionList()[0]))
			reporteRECForm.setDescripcionRegionList("Todos");
		if(reporteRECForm.getZonaList().length==1 && StringUtils.isBlank(reporteRECForm.getZonaList()[0]))
			reporteRECForm.setDescripcionZonaList("Todos");
		if(reporteRECForm.getTerritorioList().length==1 && StringUtils.isBlank(reporteRECForm.getTerritorioList()[0]))
			reporteRECForm.setDescripcionTerritorioList("Todos");
		if(reporteRECForm.getMarcaList().length==1 && StringUtils.isBlank(reporteRECForm.getMarcaList()[0]))
			reporteRECForm.setDescripcionMarcaList("Todos");
		if(reporteRECForm.getUnidadNegocioList().length==1 && StringUtils.isBlank(reporteRECForm.getUnidadNegocioList()[0]))
			reporteRECForm.setDescripcionUnidadNegocioList("Todos");
		if(reporteRECForm.getNegocioList().length==1 && StringUtils.isBlank(reporteRECForm.getNegocioList()[0]))
			reporteRECForm.setDescripcionNegocioList("Todos");
		
		if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
			params.put("periodoReferenciaInicial", reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoReferenciaFinal", reporteRECForm.getCodigoPeriodoFinal());
			params.put("periodoRegistroInicial", null);
			params.put("periodoRegistroFinal", null);
			params.put("periodoRegistroDMSACInicial", null);
			params.put("periodoRegistroDMSACFinal", null);
		}else if( StringUtils.equals(reporteRECForm.getTipoPeriodo(), "1") ){
			params.put("periodoReferenciaInicial", null);
			params.put("periodoReferenciaFinal", null);
			params.put("periodoRegistroInicial", reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoRegistroFinal", reporteRECForm.getCodigoPeriodoFinal());
			params.put("periodoRegistroDMSACInicial", null);
			params.put("periodoRegistroDMSACFinal", null);
		} else {
			params.put("periodoRegistroInicial", null);
			params.put("periodoRegistroFinal", null);
			params.put("periodoReferenciaInicial", null);
			params.put("periodoReferenciaFinal", null);
			params.put("periodoRegistroDMSACInicial", reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoRegistroDMSACFinal", reporteRECForm.getCodigoPeriodoFinal());
		}

		params.put("codigoOperacion", 
					(reporteRECForm.getCodigoOperacion() == null || reporteRECForm.getCodigoOperacion().length == 0 || 
					 StringUtils.equals(reporteRECForm.getDescripcionOperacion(),"Todos")) ? 
							 new ArrayList() : Arrays.asList(reporteRECForm.getCodigoOperacion()));
		params.put("negocioList", 
					(reporteRECForm.getNegocioList() == null || reporteRECForm.getNegocioList().length == 0 ||
					 StringUtils.equals(reporteRECForm.getDescripcionNegocioList(), "Todos")) ? 
							 new ArrayList() : Arrays.asList(reporteRECForm.getNegocioList()));
		params.put("unidadNegocioList", 
					(reporteRECForm.getUnidadNegocioList() == null || reporteRECForm.getUnidadNegocioList().length == 0 ||  
					 StringUtils.equals(reporteRECForm.getDescripcionUnidadNegocioList(), "Todos")) ? 
					 		new ArrayList() : Arrays.asList(reporteRECForm.getUnidadNegocioList()));
		params.put("territorioList",
					(reporteRECForm.getTerritorioList() == null || reporteRECForm.getTerritorioList().length == 0 ||  
					 StringUtils.equals(reporteRECForm.getDescripcionTerritorioList(), "Todos")) ? 
							 new ArrayList() : Arrays.asList(reporteRECForm.getTerritorioList()));
		params.put("tipoOperacionList", 
					(reporteRECForm.getTipoOperacionList() == null || reporteRECForm.getTipoOperacionList().length == 0 ||  
					 StringUtils.equals(reporteRECForm.getDescripcionTipoOperacionList(), "Todos")) ? 
							 new ArrayList() : Arrays.asList(reporteRECForm.getTipoOperacionList()));
		params.put("marcaList", 
					(reporteRECForm.getMarcaList() == null || reporteRECForm.getMarcaList().length == 0 ||  
					 StringUtils.equals(reporteRECForm.getDescripcionMarcaList(), "Todos")) ? 
							 new ArrayList() : Arrays.asList(reporteRECForm.getMarcaList()));
		params.put("zonaList", 
				    (reporteRECForm.getZonaList() == null || reporteRECForm.getZonaList().length == 0 ||  
					 StringUtils.equals(reporteRECForm.getDescripcionZonaList(), "Todos")) ? 
							 new ArrayList() : Arrays.asList(reporteRECForm.getZonaList()));
		params.put("regionList", 
					(reporteRECForm.getRegionList() == null || reporteRECForm.getRegionList().length == 0 ||  
					 StringUtils.equals(reporteRECForm.getDescripcionRegionList(), "Todos")) ? 
							 new ArrayList() : Arrays.asList(reporteRECForm.getRegionList()));
		params.put(
				"NroReporte",
				getResourceMessage("reporteRECEstadisticaRecDetalladoForm.numero.reporte"));
		params.put(
				"titulo",
				getResourceMessage("reporteRECEstadisticaRecDetalladoForm.titulo"));

		params.put("codigoPais", reporteRECForm.getCodigoPais());
		
//		Ingresando las descripciones
		String desOperacion = descripcionMultipleLista(reporteRECForm.getCodigoOperacion(), this.siccOperacionesList);	
		if(StringUtils.equals(reporteRECForm.getDescripcionOperacion(), "Todos"))
			params.put("descripcionOperacion", "Todos");
		else
			params.put("descripcionOperacion", desOperacion);
		
		String desTipoOperacionList = descripcionMultipleLista(reporteRECForm.getTipoOperacionList(), this.siccTipoOperacionList);	
		if(StringUtils.equals(reporteRECForm.getDescripcionTipoOperacionList(), "Todos"))
			params.put("descripcionTipoOperacionList", "Todos");
		else
			params.put("descripcionTipoOperacionList", desTipoOperacionList);
		
		String desRegionList = descripcionListaTodos(reporteRECForm.getRegionList(), this.siccRegionList);
		if(StringUtils.equals(reporteRECForm.getDescripcionRegionList(), "Todos"))
			params.put("descripcionRegionList", "Todos");
		else
			params.put("descripcionRegionList", desRegionList);
		
		String desZonaList = descripcionMultipleLista(reporteRECForm.getZonaList(), this.siccZonaList);	
		if(StringUtils.equals(reporteRECForm.getDescripcionZonaList(), "Todos"))
			params.put("descripcionZonaList", "Todos");
		else
			params.put("descripcionZonaList", desZonaList);
		
		String desMarcaList = descripcionListaTodos(reporteRECForm.getMarcaList(), this.siccMarcaList);	
		if(StringUtils.equals(reporteRECForm.getDescripcionMarcaList(), "Todos"))
			params.put("descripcionMarcaList", "Todos");
		else
			params.put("descripcionMarcaList", desMarcaList);
		
		String desUnidadNegocioList = descripcionListaTodos(reporteRECForm.getUnidadNegocioList(), this.siccUnidadNegocioList);	
		if(StringUtils.equals(reporteRECForm.getDescripcionUnidadNegocioList(), "Todos"))
			params.put("descripcionUnidadNegocioList", "Todos");
		else
			params.put("descripcionUnidadNegocioList", desUnidadNegocioList);
		
		String desNegocioList = descripcionListaTodos(reporteRECForm.getNegocioList(), this.siccNegocioList);
		if(StringUtils.equals(reporteRECForm.getDescripcionNegocioList(), "Todos"))
			params.put("descripcionNegocioList", "Todos");
		else
			params.put("descripcionNegocioList", desNegocioList);
		
		String desTerritorioList = descripcionMultipleLista(reporteRECForm.getTerritorioList(), this.siccTerritorioList);
		if(StringUtils.equals(reporteRECForm.getDescripcionTerritorioList(), "Todos"))
			params.put("descripcionTerritorioList", "Todos");
		else
			params.put("descripcionTerritorioList", desTerritorioList);

		String fechaIni = DateUtil.convertDateToString(reporteRECForm.getFechaInicialD());
		String fechaFin = DateUtil.convertDateToString(reporteRECForm.getFechaFinalD());
		params.put("fechaInicial", fechaIni);
		params.put("fechaFinal", fechaFin);

		log.info("Salio a ReporteRECEstadisticaRecDetalladoAction - prepareParameterMap");
		return params;
		
		
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");
		try {
			String[] valores = (String[]) val.getNewValue();
			if (valores.length > 0) {
				String[] regiones = (String[]) val.getNewValue();

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.siccZonaList = aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								this.mPantallaPrincipalBean.getCurrentCountry()
										.getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								Constants.FORMATO_TOTAL);

			} else {
				setSiccZonaList(null);
				setSiccTerritorioList(null);
			}
			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}

		

	}

	/**
	 * Metodo para obtener Lista de Territorios
	 * 
	 * @param val
	 */
	public void loadterritorio(ValueChangeEvent val) {
		log.debug("loadterritorio");
		try {
			ReporteRECEstadisticaRecDetalladoForm form = (ReporteRECEstadisticaRecDetalladoForm) this.formReporte;
			String[] regiones = (String[]) form.getRegionList();
			String[] zonas = (String[]) val.getNewValue();
			List<String> listaRegiones = 
				     new ArrayList<String>(Arrays.asList(regiones));
			List<String> listaZonas = 
				     new ArrayList<String>(Arrays.asList(zonas));
			if (regiones.length > 0 && zonas.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				setSiccTerritorioList(aSvc
						.getTerritoriosMultipleByPaisMarcaCanalRegionZona(
								this.mPantallaPrincipalBean.getCurrentCountry()
										.getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, listaRegiones, listaZonas,
								Constants.FORMATO_TOTAL));

			} else {
				setSiccTerritorioList(null);
			}			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
		
	}


	public void loadTipoOperacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoOperacion");
		}
		try {
			String[] valores = (String[]) val.getNewValue();			
			if (valores.length > 0) {
				ArrayList<String> listaValores = 
					    new ArrayList<String>(Arrays.asList(valores));
				AjaxService ajax = (AjaxService) getBean("ajaxService");

				setSiccTipoOperacionList(ajax
						.getTiposOperaMultipleByOpera(this.mPantallaPrincipalBean
								.getCurrentCountry().getCodigo(), listaValores, Constants.FORMATEAR_TODOS));				
			}else			
				this.siccTipoOperacionList = new LabelValue[]{};
			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
		

	}
	
	public String descripcionListaTodos(String[] datos, List lista) {
		
		String descripcionLista = "";
		String descripcion = "";
		String[] wdatos = datos;
		for (int i = 0; i < wdatos.length; i++) {
			String dato = wdatos[i];
			if(StringUtils.isBlank(dato))
				descripcionLista = descripcionLista + "Todos" + "\n";
				
			for (int j = 0; j < lista.size(); j++) {
				Base base = (Base) lista.get(j);
				String codigo = base.getCodigo();
				if (StringUtils.equals(dato, codigo)) {
					descripcion = base.getDescripcion();
					descripcionLista = descripcionLista + descripcion + "\n";
					break;
				}
			}
		}
		return descripcionLista;
	}
	
	public void limpiarDescripcion(){
		ReporteRECEstadisticaRecDetalladoForm reporteRECForm = (ReporteRECEstadisticaRecDetalladoForm) this.formReporte;		
			reporteRECForm.setDescripcionOperacion("");		
			reporteRECForm.setDescripcionTipoOperacionList("");		
			reporteRECForm.setDescripcionRegionList("");		
			reporteRECForm.setDescripcionZonaList("");		
			reporteRECForm.setDescripcionTerritorioList("");	
			reporteRECForm.setDescripcionMarcaList("");		
			reporteRECForm.setDescripcionUnidadNegocioList("");		
			reporteRECForm.setDescripcionNegocioList("");
		
	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccUnidadNegocioList
	 */
	public List getSiccUnidadNegocioList() {
		return siccUnidadNegocioList;
	}

	/**
	 * @param siccUnidadNegocioList the siccUnidadNegocioList to set
	 */
	public void setSiccUnidadNegocioList(List siccUnidadNegocioList) {
		this.siccUnidadNegocioList = siccUnidadNegocioList;
	}

	/**
	 * @return the siccEstadoOperacionList
	 */
	public List getSiccEstadoOperacionList() {
		return siccEstadoOperacionList;
	}

	/**
	 * @param siccEstadoOperacionList the siccEstadoOperacionList to set
	 */
	public void setSiccEstadoOperacionList(List siccEstadoOperacionList) {
		this.siccEstadoOperacionList = siccEstadoOperacionList;
	}

	/**
	 * @return the siccEstadoReclamosList
	 */
	public List getSiccEstadoReclamosList() {
		return siccEstadoReclamosList;
	}

	/**
	 * @param siccEstadoReclamosList the siccEstadoReclamosList to set
	 */
	public void setSiccEstadoReclamosList(List siccEstadoReclamosList) {
		this.siccEstadoReclamosList = siccEstadoReclamosList;
	}

	/**
	 * @return the siccNegocioList
	 */
	public List getSiccNegocioList() {
		return siccNegocioList;
	}

	/**
	 * @param siccNegocioList the siccNegocioList to set
	 */
	public void setSiccNegocioList(List siccNegocioList) {
		this.siccNegocioList = siccNegocioList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the siccTipoOperacionList
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList the siccTipoOperacionList to set
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	
	
	

}
