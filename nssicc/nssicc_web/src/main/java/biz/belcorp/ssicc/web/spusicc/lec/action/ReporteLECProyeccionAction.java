package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECEnviarReporteProyeccionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECProyeccionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReporteLECProyeccionAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private List lecGrupoRegionList;
	private List siccRegionList;
	private List siccReporteLogList;
	private String lecIndTipoGrupoPago;
	private String zonTipoUaRegion;
	private String zonTipoUaZona;
	private LabelValue[] lecRegionList;
	private String indTipoGrupoPago;
	
	/**
	 * Carga zonas por regiones
	 * @param val
	 */
	public void loadRegiones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegiones");
		}
		try {
			String valor = (String) val.getNewValue();
			if (valor.trim().length() > 0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				this.lecRegionList = ajaxService.getRegionesZonasByTipoGrupo(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), valor);
			} else {
				setLecRegionList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Obtiene indicador de Mostrar GrupoPago
	 * 
	 * @param request
	 * @return indTipoGrupoPago
	 */
	private String getIndicadorGrupoPago() {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", Constants.LEC_IND_TIPO_GRUPO_PAGO);
		MantenimientoSTOBloqueoControlService serviceSTO = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		return serviceSTO.getParametroGenericoSistema(criteriaParam);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteLECProyeccionService";
	}

	
	
	/**
	 * @param evt
	 */
	public void enviar(ActionEvent evt) {
		try {
			ProcesoLECEnviarReporteProyeccionService service = (ProcesoLECEnviarReporteProyeccionService) getBean("spusicc.procesoLECEnviarReporteProyeccionService");
			ReporteService reporteService = (ReporteService) this
					.getBean("scsicc.reporteService");

			Map params = new HashMap();
			setParametros(params);

			service.executeEnviarReporteProyeccion(params);
			
			Map logParams = new HashMap();
			logParams.put("usuarioLogin", MapUtils.getString(params, "usuarioLogin", ""));
			logParams.put("nombreReporte", MapUtils.getString(params, "nombreArchivoReporte", ""));
			
			List resultado = reporteService.getLogReporteDisco(logParams);
			this.viewListaEnviadosMail = true;
			this.listaReportesMail = resultado;			
			String mensaje = this.getResourceMessage("reporteLECProyeccionForm.sended");
			this.addInfo("Info : ", mensaje);
			this.datatableBusqueda = new DataTableModel(resultado);
						
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}	
	}

	/**
	 * @param params
	 * @throws IOException 
	 */
	private void setParametros(Map params) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ReporteLECProyeccionForm reporteForm = (ReporteLECProyeccionForm) this.formReporte;
		ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLECProyeccion" + JASPER_EXTENSION);
		ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLECPProyeccionPDF" + JASPER_EXTENSION);
		try {
			params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath())));
			params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath())));
		} catch (JRException e) {
			e.printStackTrace();
		}

		String indTipoGrupoPago = getIndicadorGrupoPago();
		params.put("codigoZonaList", null);
		params.put("codigoRegionList", null);
		if (StringUtils.equals(indTipoGrupoPago, Constants.ZON_TIPO_UA_ZONA)) {
			params.put("codigoZonaList", reporteForm.getCodigoRegion());

			if (reporteForm.getCodigoRegion() == null || reporteForm.getCodigoRegion()[0].equals(Constants.OPCION_TODOS)) {
				params.put("codigoZonaList", null);
			}
		} else {
			params.put("codigoRegionList", reporteForm.getCodigoRegion());

			if (reporteForm.getCodigoRegion() == null || reporteForm.getCodigoRegion()[0].equals(Constants.OPCION_TODOS)) {
				params.put("codigoRegionList", null);
			}
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("condicionTramos", reporteForm.getTramo());
		params.put("campanyaProceso", reporteForm.getCampanyaProceso());
		params.put("codigoUsuario", usuario.getLogin());
		params.put("usuarioLogin", usuario.getLogin());
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("pais", pais);
		params.put("rutaPath", externalContext.getRealPath("/"));

		params.put("NroReporte", "");
		params.put("titulo",
				this.getResourceMessage("reporteLECProyeccionForm.titulo"));

		// El reporte en pantalla muestra todas las zonas y regiones
		// seleccionadas
		params.put("codigoRegion", null);
		params.put("codigoZona", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECProyeccionForm form = new ReporteLECProyeccionForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte = "";

		if ("XLS".equals(formatoReporte)) { // XLS
			reporte = "reporteLECProyeccionXLS";
		} else {
			reporte = "reporteMaestroHorizontalLECProyeccion"; // PDF
		}
		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte = "reporteLECProyeccionPDF";
		
		if ("XLS".equals(formatoReporte)) {
			reporte = "";
		}

		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteLECProyeccionForm reporteForm = (ReporteLECProyeccionForm) this.formReporte;

		formatoReporte = reporteForm.getFormatoExportacion();
		setParametros(params);
		return params;
	}
	
	/**
	 * 
	 */
	public void inicializar(){
		this.zonTipoUaRegion = Constants.ZON_TIPO_UA_REGION;
		this.zonTipoUaZona = Constants.ZON_TIPO_UA_ZONA;
		this.mostrarReporteXLS = true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ReporteLECProyeccionForm f = (ReporteLECProyeccionForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		this.inicializar();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);
		f.setCampanyaProceso(controlFacturacion.getCodigoPeriodo());
		f.setCodigoGrupoPago("");
		this.mostrarListaReporteLog = true;
		this.listaReportesMail = new ArrayList();
		this.datatableBusqueda = new DataTableModel(new ArrayList());

		this.indTipoGrupoPago = getIndicadorGrupoPago();
		this.lecIndTipoGrupoPago = indTipoGrupoPago != null ? indTipoGrupoPago
				: Constants.NRO_CERO;

		String codigoPais = pais.getCodigo();
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("indTipoGrupoPago", indTipoGrupoPago);
		List list = lecService.getGruposPago(map);
		this.lecGrupoRegionList = list;
		this.siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				map);
		f.setTramo("1");

	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the lecGrupoRegionList
	 */
	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	/**
	 * @param lecGrupoRegionList the lecGrupoRegionList to set
	 */
	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}	

	/**
	 * @return the lecRegionList
	 */
	public LabelValue[] getLecRegionList() {
		return lecRegionList;
	}

	/**
	 * @param lecRegionList the lecRegionList to set
	 */
	public void setLecRegionList(LabelValue[] lecRegionList) {
		this.lecRegionList = lecRegionList;
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
	 * @return the siccReporteLogList
	 */
	public List getSiccReporteLogList() {
		return siccReporteLogList;
	}

	/**
	 * @param siccReporteLogList the siccReporteLogList to set
	 */
	public void setSiccReporteLogList(List siccReporteLogList) {
		this.siccReporteLogList = siccReporteLogList;
	}

	/**
	 * @return the lecIndTipoGrupoPago
	 */
	public String getLecIndTipoGrupoPago() {
		return lecIndTipoGrupoPago;
	}

	/**
	 * @param lecIndTipoGrupoPago the lecIndTipoGrupoPago to set
	 */
	public void setLecIndTipoGrupoPago(String lecIndTipoGrupoPago) {
		this.lecIndTipoGrupoPago = lecIndTipoGrupoPago;
	}

	/**
	 * @return the zonTipoUaRegion
	 */
	public String getZonTipoUaRegion() {
		return zonTipoUaRegion;
	}

	/**
	 * @param zonTipoUaRegion the zonTipoUaRegion to set
	 */
	public void setZonTipoUaRegion(String zonTipoUaRegion) {
		this.zonTipoUaRegion = zonTipoUaRegion;
	}

	/**
	 * @return the zonTipoUaZona
	 */
	public String getZonTipoUaZona() {
		return zonTipoUaZona;
	}

	/**
	 * @param zonTipoUaZona the zonTipoUaZona to set
	 */
	public void setZonTipoUaZona(String zonTipoUaZona) {
		this.zonTipoUaZona = zonTipoUaZona;
	}

	/**
	 * @return the indTipoGrupoPago
	 */
	public String getIndTipoGrupoPago() {
		return indTipoGrupoPago;
	}

	/**
	 * @param indTipoGrupoPago the indTipoGrupoPago to set
	 */
	public void setIndTipoGrupoPago(String indTipoGrupoPago) {
		this.indTipoGrupoPago = indTipoGrupoPago;
	}
}