package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTORechazosSTODemandaForm;

@ManagedBean
@SessionScoped
public class ReporteSTORechazosSTODemandaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 1435748424609950127L;
	private String formatoReporte;
	private List regiones;
	private LabelValue[] siccZonaList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTORechazosSTODemandaForm reporteForm = new ReporteSTORechazosSTODemandaForm();

		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteSTORechazosSTODemandaForm - setViewAtributes");
		}

		this.mostrarReportePDF = true;
		ReporteSTORechazosSTODemandaForm f = (ReporteSTORechazosSTODemandaForm) this.formReporte;
		Map criteria = new HashMap();
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		this.regiones = new ArrayList();
		this.regiones = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion
				.getFechaProceso()));


	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTORechazosSTODemandaService";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTOErroresCorreoPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.info("Entro a ReporteSTORechazosSTODemandaAction - prepareParameterMap");

		// -- Variables
		ReporteSTORechazosSTODemandaForm f = (ReporteSTORechazosSTODemandaForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();

		String titulo = getResourceMessage(
				"reporteMSGSTOErroresCorreoForm.titulo");

		log.debug("titulo");
		params.put("titulo", titulo);
		params.put("codigoPais", f.getCodigoPais());

		if (StringUtils.isNotBlank(f.getRegionList())) {
			params.put("codigoRegion", f.getRegionList());
		}

		if (StringUtils.isNotBlank(f.getZonaList())) {
			params.put("codigoZona", f.getZonaList());
		}

		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");

		if (StringUtils.isNotEmpty((String) params.get("codigoZona"))) {
			params.put("tituloRegionZona", "Zona");
			params.put("nombreRegionZona", service
					.getDescripcionZona((String) params.get("codigoZona")));
		} else {
			params.put("tituloRegionZona", "Region");
			params.put("nombreRegionZona", service
					.getDescripcionRegion((String) params.get("codigoRegion")));
		}

		params.put("fecha", DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaFacturacionD()));
		params.put("codigoPeriodo", f.getCodigoPeriodo());

		/*
		 * params.put("fecha", "12/07/2013"); params.put("codigoRegion","05");
		 */

		// -- subreports
		

			ClassPathResource resource0 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub0ResumenDemanda"
							+ JASPER_EXTENSION);
			ClassPathResource resource1 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub1OrdenCompraDemanda"
							+ JASPER_EXTENSION);
			ClassPathResource resource2 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub2SolicitudCreditoDemanda"
							+ JASPER_EXTENSION);
			ClassPathResource resource5 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub5FamiliaProtegidaDemanda"
							+ JASPER_EXTENSION);
			ClassPathResource resource7 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub7ContratoEjecutivasDemanda"
							+ JASPER_EXTENSION);

			params.put("SUBREPORT_DIR0",
					(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource0.getPath() )));
			params.put("SUBREPORT_DIR1_MOSTRAR", "0");

			params.put("SUBREPORT_DIR1",
					(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
			params.put("SUBREPORT_DIR1_MOSTRAR", "1");

			params.put("SUBREPORT_DIR2",
					(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
			params.put("SUBREPORT_DIR2_MOSTRAR", "1");

			params.put("SUBREPORT_DIR5",
					(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource5.getPath() )));
			params.put("SUBREPORT_DIR5_MOSTRAR", "1");

			params.put("SUBREPORT_DIR7",
					(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource7.getPath() )));
			params.put("SUBREPORT_DIR7_MOSTRAR", "1");


		log.info("Salio a ReporteCOMComparativoRetailSiccAction - prepareParameterMap");

		return params;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		try {
			String valor = (String) val.getNewValue();			
			String[] valores = new String[1];
			valores[0] = valor;
			if (valores.length > 0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				this.siccZonaList=ajaxService
						.getZonasMultipleByPaisMarcaCanalRegion(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valores,
								Constants.OPCION_TODOS);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * @return the regiones
	 */
	public List getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(List regiones) {
		this.regiones = regiones;
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
	
	
	



}
