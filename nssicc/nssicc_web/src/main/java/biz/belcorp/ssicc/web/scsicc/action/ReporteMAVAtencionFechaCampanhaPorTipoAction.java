package biz.belcorp.ssicc.web.scsicc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.XlsxUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAVAtencionFechaCampanhaPorTipoForm;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDDetallePedidosFacturadosPorCodigoSAPForm;


/**
 * @author jpulido
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteMAVAtencionFechaCampanhaPorTipoAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -4683832016683415274L;

	private String tipoReporte;
	private String codigoTipoReporte;
	private String codigoRadio;
	private String codigoTipoMav;
	private boolean flagRangoFechasBool = false;
	private boolean mostrarRangoFechas = false;
	private String flagRangoFechas = "off";
	private String codigoTipoCargo;
	private String indicadorTipoCargo;
	private String indicadorCapacitadora;
	private List siccSociedadList = new ArrayList();
	private List siccRegionList = new ArrayList();
	private List siccTipoMavList;
	private LabelValue[] siccActividadList;
	private LabelValue[] siccTipoOfertaList;
	private List siccCapacitadoraList;
	private LabelValue[] siccZonaList;
	private Boolean mostrarZonas;
	
	private String tipoCargoGR;
	private String tipoCargoGZ;
	private String tipoCargoEE;
	private String tipoCargoMVR;
	private String tipoCargoMVZ;
	
	private String codigoIndicadorEnvioP;
	private String codigoIndicadorEnvioE;
	private String codigoIndicadorEnvioD;

	private Boolean bloquearRegion;
	private Boolean bloquearTipoCargo;
	private Boolean bloquearEnvio;
	private Boolean bloquearRadioButton;
	private Boolean bloquearZona;
	
	private String attachment = "";
	private String [] listCodigoSAP = {};
	
	/**
	 * @return
	 */
	public String getCodigoIndicadorEnvioP() {
		return codigoIndicadorEnvioP;
	}

	/**
	 * @param codigoIndicadorEnvioP
	 */
	public void setCodigoIndicadorEnvioP(String codigoIndicadorEnvioP) {
		this.codigoIndicadorEnvioP = codigoIndicadorEnvioP;
	}

	/**
	 * @return
	 */
	public String getCodigoIndicadorEnvioE() {
		return codigoIndicadorEnvioE;
	}

	/**
	 * @param codigoIndicadorEnvioE
	 */
	public void setCodigoIndicadorEnvioE(String codigoIndicadorEnvioE) {
		this.codigoIndicadorEnvioE = codigoIndicadorEnvioE;
	}

	/**
	 * @return
	 */
	public String getCodigoIndicadorEnvioD() {
		return codigoIndicadorEnvioD;
	}

	/**
	 * @param codigoIndicadorEnvioD
	 */
	public void setCodigoIndicadorEnvioD(String codigoIndicadorEnvioD) {
		this.codigoIndicadorEnvioD = codigoIndicadorEnvioD;
	}

	/**
	 * @return
	 */
	public String getTipoCargoGR() {
		return tipoCargoGR;
	}

	/**
	 * @param tipoCargoGR
	 */
	public void setTipoCargoGR(String tipoCargoGR) {
		this.tipoCargoGR = tipoCargoGR;
	}

	/**
	 * @return
	 */
	public String getTipoCargoGZ() {
		return tipoCargoGZ;
	}

	/**
	 * @param tipoCargoGZ
	 */
	public void setTipoCargoGZ(String tipoCargoGZ) {
		this.tipoCargoGZ = tipoCargoGZ;
	}

	/**
	 * @return
	 */
	public String getTipoCargoEE() {
		return tipoCargoEE;
	}

	/**
	 * @param tipoCargoEE
	 */
	public void setTipoCargoEE(String tipoCargoEE) {
		this.tipoCargoEE = tipoCargoEE;
	}

	/**
	 * @return
	 */
	public String getTipoCargoMVR() {
		return tipoCargoMVR;
	}

	/**
	 * @param tipoCargoMVR
	 */
	public void setTipoCargoMVR(String tipoCargoMVR) {
		this.tipoCargoMVR = tipoCargoMVR;
	}

	/**
	 * @return
	 */
	public String getTipoCargoMVZ() {
		return tipoCargoMVZ;
	}

	/**
	 * @param tipoCargoMVZ
	 */
	public void setTipoCargoMVZ(String tipoCargoMVZ) {
		this.tipoCargoMVZ = tipoCargoMVZ;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccActividadList() {
		return siccActividadList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccTipoOfertaList() {
		return siccTipoOfertaList;
	}

	/**
	 * @param siccActividadList
	 */
	public void setSiccActividadList(LabelValue[] siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

	/**
	 * @param siccTipoOfertaList
	 */
	public void setSiccTipoOfertaList(LabelValue[] siccTipoOfertaList) {
		this.siccTipoOfertaList = siccTipoOfertaList;
	}

	/**
	 * @return
	 */
	public List getSiccCapacitadoraList() {
		return siccCapacitadoraList;
	}

	/**
	 * @param siccCapacitadoraList
	 */
	public void setSiccCapacitadoraList(List siccCapacitadoraList) {
		this.siccCapacitadoraList = siccCapacitadoraList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return
	 */
	public List getSiccTipoMavList() {
		return siccTipoMavList;
	}

	/**
	 * @param siccTipoMavList
	 */
	public void setSiccTipoMavList(List siccTipoMavList) {
		this.siccTipoMavList = siccTipoMavList;
	}

	/**
	 * @return
	 */
	public String getIndicadorCapacitadora() {
		return indicadorCapacitadora;
	}

	/**
	 * @param indicadorCapacitadora
	 */
	public void setIndicadorCapacitadora(String indicadorCapacitadora) {
		this.indicadorCapacitadora = indicadorCapacitadora;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = true;
		// Carga de los Periodos

		this.bloquearRegion= true;
		this.bloquearZona= true;
		this.bloquearTipoCargo = true;
		this.bloquearEnvio = false;
		this.bloquearRadioButton = true;

		
		this.tipoCargoGR=Constants.MAV_CODIGO_TIPO_CARGO_GR;
		this.tipoCargoGZ=Constants.MAV_CODIGO_TIPO_CARGO_GZ;
		this.tipoCargoEE=Constants.MAV_CODIGO_TIPO_CARGO_EE;
		this.tipoCargoMVR=Constants.MAV_CODIGO_TIPO_CARGO_MVR;
		this.tipoCargoMVZ=Constants.MAV_CODIGO_TIPO_CARGO_MVZ;
		
		
		this.codigoIndicadorEnvioP=Constants.MAV_CODIGO_INDICADOR_ENVIO_P;
		this.codigoIndicadorEnvioE=Constants.MAV_CODIGO_INDICADOR_ENVIO_E;
		this.codigoIndicadorEnvioD=Constants.MAV_CODIGO_INDICADOR_ENVIO_D;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService ctrlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		// Carga de la lista de Tipo Mav

		this.siccTipoMavList = reporteService.getTipoMav();
		// siccActividadList={};
		// siccTipoOfertaList=;

		ReporteMAVAtencionFechaCampanhaPorTipoForm formRec = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		formRec.getIdioma().setCodigoISO(
				mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		formRec.setCodigoRadio("0");

		formRec.setCodigoPais(pais.getCodigo());
		/* colocando periodos */
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		PedidoControlFacturacion controlFacturacion = ctrlFactService
				.getControlFacturacionById(criteria);

		formRec.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.ITEM_INDICADOR_CAPACITADORA);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		formRec.setIndicadorCapacitadora(Constants.NO);

		ParametroPais parametro = null;
		this.indicadorCapacitadora = Constants.NO;
		if (CollectionUtils.size(lstParametros) == 1) {
			parametro = (ParametroPais) lstParametros.get(0);
			this.indicadorCapacitadora = parametro.getValorParametro();
			formRec.setIndicadorCapacitadora(indicadorCapacitadora);
		}

		parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.MAV_INDICADOR_TIPOCARGO);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		lstParametros = genericoService.getParametrosPais(parametroPais);
		formRec.setIndicadorTipoCargo(Constants.NO);

		String indicadorTipoCargo = Constants.NO;
		if (CollectionUtils.size(lstParametros) == 1) {
			parametro = (ParametroPais) lstParametros.get(0);
			indicadorTipoCargo = parametro.getValorParametro();
			formRec.setIndicadorTipoCargo(indicadorTipoCargo);
		}
		this.indicadorTipoCargo = formRec.getIndicadorTipoCargo();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("indicadorTipoRegion", Constants.SI);

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccCapacitadoraList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.setMostrarZonas(true);

	}
	
	/**
	 * @param c
	 */
	
	public void cambiarReporte(ValueChangeEvent c){
		if (log.isDebugEnabled()) {
			log.debug("cambiarReporte...");
		}
		if(c.getNewValue() == null){
			return;
			
		}
		try {
			String valor = c.getNewValue().toString();
			if(valor.equals("0")){
				this.bloquearRadioButton = true;
				this.bloquearEnvio = false;
				this.bloquearRegion = true;
				this.bloquearZona = true;
				this.bloquearTipoCargo = true;
				this.mostrarReporteOCSV = true;

				this.mostrarReportePDF = true;
				
			}
			if(valor.equals("1")){
				this.bloquearRadioButton = false;
				this.bloquearEnvio = false;
				this.bloquearRegion = false;
				this.bloquearZona = false;
				this.bloquearTipoCargo = false;
				this.mostrarReportePDF = true;
				this.mostrarReporteOCSV = false;

						}
			if(valor.equals("2")){
				this.bloquearRadioButton = false;
				this.bloquearEnvio= true;
				this.bloquearRegion = true;
				this.bloquearZona = true;
				this.bloquearTipoCargo = true;
				this.mostrarReportePDF = false;
				this.mostrarReporteOCSV = true;

			}
			
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @param c
	 */
	
	public void cambiarTipoCargo(ValueChangeEvent c){
		if (log.isDebugEnabled()) {
			log.debug("cambiarTipoCargo...");
		}
		ReporteMAVAtencionFechaCampanhaPorTipoForm formRep = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		formRep.setRegionListMultiple(null);
		
		if(c.getNewValue() == null){
			//Muestra Zona
			ReporteMAVAtencionFechaCampanhaPorTipoForm formRec = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
			this.bloquearZona=true;
			this.siccZonaList = null;
			formRec.setZonaListMultiple(null);
			return;
			
		}
		try {
			String valor = c.getNewValue().toString();
			
			if(StringUtils.equals(valor, Constants.MAV_CODIGO_TIPO_CARGO_GR) ||
					StringUtils.equals(valor, Constants.MAV_CODIGO_TIPO_CARGO_EE) ||
						StringUtils.equals(valor, Constants.MAV_CODIGO_TIPO_CARGO_MVR)){
				//Bloquea y limpia Zona
				ReporteMAVAtencionFechaCampanhaPorTipoForm formRec = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
				this.bloquearZona=false;
				formRec.setZonaListMultiple(null);
			}else if(StringUtils.equals(valor, Constants.MAV_CODIGO_TIPO_CARGO_GZ) ||
						StringUtils.equals(valor, Constants.MAV_CODIGO_TIPO_CARGO_MVZ)){
				//Muestra Zona
				this.bloquearZona=true;
			}else{
				//Muestra Zona
				ReporteMAVAtencionFechaCampanhaPorTipoForm formRec = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
				this.bloquearZona=true;
				formRec.setZonaListMultiple(null);
			}
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return
	 */
	public String getIndicadorTipoCargo() {
		return indicadorTipoCargo;
	}

	/**
	 * @param indicadorTipoCargo
	 */
	public void setIndicadorTipoCargo(String indicadorTipoCargo) {
		this.indicadorTipoCargo = indicadorTipoCargo;
	}

	/**
	 * @return
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return
	 */
	public String getCodigoTipoReporte() {
		return codigoTipoReporte;
	}

	/**
	 * @param codigoTipoReporte
	 */
	public void setCodigoTipoReporte(String codigoTipoReporte) {
		this.codigoTipoReporte = codigoTipoReporte;
	}

	/**
	 * @return
	 */
	public String getCodigoRadio() {
		return codigoRadio;
	}

	/**
	 * @param codigoRadio
	 */
	public void setCodigoRadio(String codigoRadio) {
		this.codigoRadio = codigoRadio;
	}

	/**
	 * @return
	 */
	public String getCodigoTipoMav() {
		return codigoTipoMav;
	}

	/**
	 * @param codigoTipoMav
	 */
	public void setCodigoTipoMav(String codigoTipoMav) {
		this.codigoTipoMav = codigoTipoMav;
	}

	/**
	 * @return
	 */
	public String getCodigoTipoCargo() {
		return codigoTipoCargo;
	}

	/**
	 * @param codigoTipoCargo
	 */
	public void setCodigoTipoCargo(String codigoTipoCargo) {
		this.codigoTipoCargo = codigoTipoCargo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	/**
	 * @return the mostrarRangoFechas
	 */
	public boolean isMostrarRangoFechas() {
		return mostrarRangoFechas;
	}

	/**
	 * @param mostrarRangoFechas the mostrarRangoFechas to set
	 */
	public void setMostrarRangoFechas(boolean mostrarRangoFechas) {
		this.mostrarRangoFechas = mostrarRangoFechas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteMAVAtencionFechaCampanhaPorTipoForm form = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		
		if(StringUtils.isBlank(form.getCodigoTipoMav())){			
			return "El codigo tipo Mav es necesario";
		}
		
		if(this.listCodigoSAP.length > 0 && StringUtils.isNotBlank(form.getCodigoProducto())){
			this.attachment = "";
			this.listCodigoSAP = null;
			form.setCodigoProducto(null);
			return "Debe elegir solo un método de elección de Código SAP";
		}
		
		if(this.flagRangoFechasBool == true){			
			if(form.getFechaInicioD()==null){			
				return this.getResourceMessage("reporteMAVAtencionFechaCampanhaPorTipoForm.fecha.inicio.requerido");
			}
			
			if(form.getFechaFinD()==null){			
				return this.getResourceMessage("reporteMAVAtencionFechaCampanhaPorTipoForm.fecha.fin.requerido");
			}
			
			if(form.getFechaInicioD()!=null && form.getFechaFinD()!=null){
				if(form.getFechaInicioD().compareTo(form.getFechaFinD())>0){
					this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
					return this.getMensajeAlertaDefault();
				}
			}
			
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.tipoReporte)) {

			if (StringUtils.equals(codigoTipoReporte, Constants.NUMERO_CERO)) {
				if (StringUtils.equals(codigoRadio, Constants.NUMERO_CERO)) {
					if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
						if(StringUtils.equals(flagRangoFechas, "on")){
							// Resumen - Consultora - Con Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaXLS";
							}
						} else {
							// Resumen - Consultora - Sin Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaXLS";
							}
						}
					} else {
						if(StringUtils.equals(flagRangoFechas, "on")){
							// Resumen - Gerente - Con Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaXLS";
							}
						} else {
							// Resumen - Gerente - Sin Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaXLS";
							}
						}
					}
				} else {
					if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
						// Detalle - Consultora - Con/Sin Rango fechas
						return "reporteMAVAtencionFechaCampanhaPorConsultoraDetalleConSinFechaXLS";
					} else {
						// Detalle - Gerente - Con/Sin Rango fechas
						if (StringUtils.isNotBlank(codigoTipoCargo)) {
							return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoXLS";
						} else {
							if (StringUtils.equals(indicadorTipoCargo,
									Constants.SI))
								return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoXLS";
							else
								return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaXLS";
						}
					}
				}
			} else if (StringUtils.equals(codigoTipoReporte,
					Constants.NUMERO_UNO)) {
				// Reporte de configuracion
				return "reporteMAVConfiguracionFechaCampanhaConSinFechaXLS";
			} else {
				// Reporte de Envios
				return "reporteMAVEnviosFechaCampanhaConSinFechaXLS";
			}

		} else {
			return "reporteMaestroHorizontalConfiguracionMAV";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAVAtencionFechaCampanhaPorTipoForm form = new ReporteMAVAtencionFechaCampanhaPorTipoForm();
		return form;
	}



	

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (StringUtils.equals(codigoTipoReporte, Constants.NUMERO_CERO)) {
			if (StringUtils.equals(codigoRadio, Constants.NUMERO_CERO)) {
				if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
					if(StringUtils.equals(flagRangoFechas, "on")){
						// Resumen - Consultora - Con Rango fechas
						if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GR)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_EE)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
							// Agrupar por regiones
							return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionPDF";
						} else if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GZ)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
							// Agrupar por regiones y zonas
							return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionZonaPDF";
						} else {
							return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaPDF";
						}
					} else {
						// Resumen - Consultora - Sin Rango fechas
						if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GR)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_EE)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
							// Agrupar por regiones
							return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionPDF";
						} else if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GZ)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
							// Agrupar por regiones y zonas
							return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionZonaPDF";
						} else {
							return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaPDF";
						}
					}
				} else {
					if(StringUtils.equals(flagRangoFechas, "on")){
						// Resumen - Gerente - Con Rango fechas
						if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GR)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_EE)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
							// Agrupar por regiones
							return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionPDF";
						} else if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GZ)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
							// Agrupar por regiones y zonas
							return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionZonaPDF";
						} else {
							return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaPDF";
						}
					} else {
						// Resumen - Gerente - Sin Rango fechas
						if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GR)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_EE)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
							// Agrupar por regiones
							return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionPDF"; // 
						} else if (StringUtils.equals(codigoTipoCargo,
								Constants.MAV_CODIGO_TIPO_CARGO_GZ)
								|| StringUtils.equals(codigoTipoCargo,
										Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
							// Agrupar por regiones y zonas
							return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionZonaPDF"; // 
						} else {
							return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaPDF";
						}
					}
				}
			} else {
				if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
					// Detalle - Consultora - Con/Sin Rango fechas
					return "reporteMAVAtencionFechaCampanhaPorConsultoraDetalleConSinFechaPDF";
				} else {
					// Detalle - Gerente - Con/Sin Rango fechas
					if (StringUtils.isNotBlank(codigoTipoCargo)) {
						return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoPDF";
					} else {
						if (StringUtils
								.equals(indicadorTipoCargo, Constants.SI))
							return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoPDF";
						else
							return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaPDF";
					}
				}
			}
		} else {
			// Consultora/Gerente - Con/Sin Rango fechas
			return "reporteMAVConfiguracionFechaCampanhaConSinFechaPDF";
		}
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		
		
		ReporteMAVAtencionFechaCampanhaPorTipoForm reporteMAVForm = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
	
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteMAVForm.setFechaInicio(DateUtil.getDate(reporteMAVForm.getFechaInicioD()));
		reporteMAVForm.setFechaFin(DateUtil.getDate(reporteMAVForm.getFechaFinD()));
		this.tipoReporte = reporteMAVForm.getFormatoExportacion();
		this.codigoTipoReporte = reporteMAVForm.getCodigoTipoReporte();
		this.codigoTipoCargo = reporteMAVForm.getCodigoTipoCargo();
		if(this.flagRangoFechasBool == true){
			reporteMAVForm.setFlagRangoFechas("on");
		}else{
			reporteMAVForm.setFlagRangoFechas("off");
		}		
		this.flagRangoFechas = reporteMAVForm.getFlagRangoFechas();
		this.codigoTipoMav = reporteMAVForm.getCodigoTipoMav();
		this.codigoRadio = reporteMAVForm.getCodigoRadio();
		this.indicadorTipoCargo = reporteMAVForm.getIndicadorTipoCargo();
		
//		if(StringUtils.isBlank(reporteMAVForm.getCodigoTipoCargo())){
//			this.codigoTipoCargo = "";
//		}else{
//			this.codigoTipoCargo = reporteMAVForm.getCodigoTipoCargo();
//
//		}
//		
//		if(StringUtils.isBlank(reporteMAVForm.getIndicadorTipoCargo())){
//			this.indicadorTipoCargo = "";
//		}else{
//			this.indicadorTipoCargo = reporteMAVForm.getIndicadorTipoCargo();
//
//		}
	

		String titulo = "";
		String codigoPais = reporteMAVForm.getCodigoPais();
		String codigoPeriodo = reporteMAVForm.getCodigoPeriodo();
		String codigoActividad = reporteMAVForm.getCodigoActividad();
		String codigoTipoOferta = reporteMAVForm.getCodigoTipoOferta();
		String codigoProducto = reporteMAVForm.getCodigoProducto();
		reporteMAVForm.setNombreTipoMav(descripcionSimpleLista(this.codigoTipoMav, this.siccTipoMavList)); 	
		String nombreTipoMav = reporteMAVForm.getNombreTipoMav();
		if(!StringUtils.isBlank(codigoTipoOferta)){
			reporteMAVForm.setNombreTipoOferta(descripcionSimpleLista(codigoTipoOferta, this.siccTipoOfertaList)); 	

		}
		String nombreTipoOferta = reporteMAVForm.getNombreTipoOferta();
		String codigoIndicadorEnvio = reporteMAVForm.getCodigoIndicadorEnvio();

		String fechaInicio = "";
		String fechaFin = "";

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		String oidPaisValor = reporteService.getOidString(
				"getOidPaisByCodigoPais", criteria);

		int oidPais = Integer.parseInt(oidPaisValor);
		params.put("oidPais", oidPais);

		if (StringUtils.equals(codigoTipoReporte, Constants.NUMERO_CERO)) {
			if (StringUtils.equals(codigoRadio, Constants.NUMERO_CERO)) {
				// Opción: Resumen
				titulo = getReportResourceMessage("reporteMAVAtencionFechaCampanhaPorTipoForm.titulo.mavAtencionesResumen");
			} else {
				// Opción: Detalle
				titulo = getReportResourceMessage("reporteMAVAtencionFechaCampanhaPorTipoForm.titulo.mavAtencionesDetalle");
			}
		} else {
			titulo = getReportResourceMessage("reporteMAVAtencionFechaCampanhaPorTipoForm.titulo.mavConfiguraciones");
		}

		String condicion = "";
		String nombreFechaInicioFin = "";
		String condicionConfiguracion = "";
		if(StringUtils.equals(this.flagRangoFechas, "on")){
			reporteMAVForm.setFechaInicio(DateUtil.getDate(reporteMAVForm.getFechaInicioD()));
			reporteMAVForm.setFechaFin(DateUtil.getDate(reporteMAVForm.getFechaFinD()));
			fechaInicio = reporteMAVForm.getFechaInicio();
			fechaFin = reporteMAVForm.getFechaFin();

			condicion = " AND TRUNC(PSC.FEC_FACT) >= TO_DATE('" + fechaInicio
					+ "','DD/MM/YYYY') AND TRUNC(PSC.FEC_FACT) <= TO_DATE('"
					+ fechaFin + "','DD/MM/YYYY') ";
			condicionConfiguracion = " AND TRUNC(A.FEC_CREA) >= TO_DATE('"
					+ fechaInicio
					+ "','DD/MM/YYYY') AND TRUNC(A.FEC_CREA) <= TO_DATE('"
					+ fechaFin + "','DD/MM/YYYY') ";
			nombreFechaInicioFin = "Fecha Inicio: " + fechaInicio
					+ "         Fecha Fin: " + fechaFin;
		}
		params.put("indicadorTipoCargo", indicadorTipoCargo);
		params.put("condicion", condicion);
		params.put("condicionConfiguracion", condicionConfiguracion);
		params.put("titulo", titulo);
		params.put("codigoPais", codigoPais);
		params.put("codigoPeriodo", codigoPeriodo);
		params.put("codigoTipoMav", this.codigoTipoMav);
		params.put("codigoActividad",
				StringUtils.isBlank(codigoActividad) ? null : codigoActividad);
		params.put("codigoTipoOferta",
				StringUtils.isBlank(codigoTipoOferta) ? null : codigoTipoOferta);
		params.put("codigoProducto", StringUtils.isBlank(codigoProducto) ? null
				: codigoProducto);
		params.put("codigoTipoReporte", this.codigoTipoReporte);
		params.put("fechaInicio", StringUtils.isBlank(fechaInicio) ? null
				: fechaInicio);
		params.put("fechaFin", StringUtils.isBlank(fechaFin) ? null : fechaFin);
		params.put("nombreTipoMav", StringUtils.isBlank(nombreTipoMav) ? null
				: nombreTipoMav);
		params.put("nombreTipoOferta",
				StringUtils.isBlank(nombreTipoOferta) ? null : nombreTipoOferta);
		params.put("codigoRadio", this.codigoRadio);
		params.put("nombreFechaInicioFin", nombreFechaInicioFin);
		if(StringUtils.equalsIgnoreCase(this.flagRangoFechas, "off")){
			this.flagRangoFechas = null;
			reporteMAVForm.setFlagRangoFechas(null);
		}

		if(StringUtils.equalsIgnoreCase(this.flagRangoFechas, "on")){
			this.flagRangoFechas = "on";
			reporteMAVForm.setFlagRangoFechas("on");
		}
		params.put("flagRangoFechas", this.flagRangoFechas);

		params.put("regionListMultiple", reporteMAVForm.getRegionListMultiple());
		params.put("zonaListMultiple", reporteMAVForm.getZonaListMultiple());
		params.put("codigoTipoCargo",
				StringUtils.isBlank(codigoTipoCargo) ? null : codigoTipoCargo);
		params.put("codigoIndicadorEnvio", StringUtils
				.isBlank(codigoIndicadorEnvio) ? null : codigoIndicadorEnvio);

		
		
		if (StringUtils.equals(codigoTipoReporte, Constants.NUMERO_UNO)) {

			// Cargamos los subreportes
			ClassPathResource resource1 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + "subReporteMAVConfiguracionFechaCampanhaPDF"
							+ JASPER_EXTENSION);
			params.put("SUBREPORT_DIR1",
						JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));

			
		}
		
		if(this.listCodigoSAP != null && this.listCodigoSAP.length > 0){
			params.put("listCodigoSAP", this.listCodigoSAP);
		}else{
			if(StringUtils.isNotBlank(reporteMAVForm.getCodigoProducto())){
				params.put("listCodigoSAP", new String[] {reporteMAVForm.getCodigoProducto()});
			}
		}	
		
		return params;
	}
	
	
	

	/**
	 * @return
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	/**
	 * @param val
	 */
	public void loadOfertas(ValueChangeEvent val)
	{
		
		if (log.isDebugEnabled()) {
			log.debug("ReporteMAVAtencionFechaCampanhaPorTipoForm...");
		}
		ReporteMAVAtencionFechaCampanhaPorTipoForm reporteMAVForm = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		String actividad = (String) val.getNewValue();

		reporteMAVForm.setCodigoActividad(actividad);
		reporteMAVForm.setCodigoTipoOferta(null);

		if(StringUtils.equals(actividad, Constants.TODAS))
		{
		//	this.siccActividadList = new LabelValue[]{};
			this.siccTipoOfertaList= new LabelValue[]{};
		}
		else
		{
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		//	this.siccActividadList= ajaxService.getActividadByTipoMAV(actividad);
			this.siccTipoOfertaList= ajaxService.getTipoOfertaByTipoMAVActividad(actividad , reporteMAVForm.getCodigoTipoMav());
		}
		
	//	this.siccActividadList = this.insertarVacioListaLabelValue(this.siccActividadList, "", "");
		this.siccTipoOfertaList = this.insertarVacioListaLabelValue(this.siccTipoOfertaList, "", "");
	}

	/**
	 * @param val
	 */
	public void cambiaActividadTipoOfertaPorTipoMav(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("ReporteMAVAtencionFechaCampanhaPorTipoForm...");
		}
		ReporteMAVAtencionFechaCampanhaPorTipoForm reporteMAVForm = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		String tipoMav = (String) val.getNewValue();

		reporteMAVForm.setCodigoActividad(null);
		reporteMAVForm.setCodigoTipoOferta(null);

		if(StringUtils.equals(tipoMav, Constants.TODAS))
		{
			this.siccActividadList = new LabelValue[]{};
			this.siccTipoOfertaList= new LabelValue[]{};
		}
		else
		{
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.siccActividadList= ajaxService.getActividadByTipoMAV(tipoMav);
			this.siccTipoOfertaList= ajaxService.getTipoOfertaByTipoMAVActividad(reporteMAVForm.getCodigoActividad() , tipoMav);
		}
		
		this.siccActividadList = this.insertarVacioListaLabelValue(this.siccActividadList, "", "");
		this.siccTipoOfertaList = this.insertarVacioListaLabelValue(this.siccTipoOfertaList, "", "");
		
	}
	
	/**
	 * @param val
	 */
	public void cambiaTipoOfertaPorActividad(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiaTipoOfertaPorActividad...");
		}
		
		ReporteMAVAtencionFechaCampanhaPorTipoForm reporteMAVForm = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String actividad = (String) val.getNewValue();
		reporteMAVForm.setCodigoActividad(actividad);
		
		this.siccTipoOfertaList= ajaxService.getTipoOfertaByTipoMAVActividad(reporteMAVForm.getCodigoActividad() , reporteMAVForm.getCodigoTipoMav());
		this.siccTipoOfertaList = this.insertarVacioListaLabelValue(this.siccTipoOfertaList, "", "");
		
	}
	
	public void loadZonas(ValueChangeEvent val) {

		String[] regiones = (String[]) val.getNewValue();

		if(regiones.length > 0 && regiones!=null){
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			ReporteMAVAtencionFechaCampanhaPorTipoForm form = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

//			
			String[] regionesF = (String[]) val.getNewValue();
			int salir=0;
			int j=0;
			int x= regiones.length;
			for(int i=0;i<x;i++){
				if((regiones[i].compareTo("Todos")==0)){
					salir=1;
					break;
					
				}
				regionesF[i]=regiones[i];
			}
			if (salir== 1){			 
				 siccZonaList=null;
			}
			else{
				if (!val.equals(null) && regiones.length > 0) {
					this.siccZonaList= aSvc.getZonasMultipleByPaisMarcaCanalRegion( pais.getCodigo(), "T", "VD", regionesF,"T");
					form.setZonaListMultiple(null);
				}
				else
				{
					this.siccZonaList = null;
					form.setZonaListMultiple(null);
				}
			
				
			}
			String dato=form.getCodigoTipoCargo();
			if(dato!=null){
				 if(dato.equals("GR") || dato.equals( "EE") || dato.equals( "MVR"))
			        {
			       
					 this.bloquearZona=false;
			        	
					}
			        else if(dato.equals( "GZ") || dato.equals("VZ"))
			        {
			        	this.bloquearZona=true;
					}
			        else
			        {
			        	this.bloquearZona=true;
					}
			}else{
				this.bloquearZona=true;
			}
		}
		

		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteMAVAtencionFechaCampanhaPorTipoService";
	}
	
	

	public void cambiarFlagMostrarRangoFechas(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarFlagMostrarRangoFechas");
		
		ReporteMAVAtencionFechaCampanhaPorTipoForm reporteMAVForm = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		
		Boolean flagMostrarRangoFechas = ((Boolean)e.getNewValue()).booleanValue();
		
		this.mostrarRangoFechas=flagMostrarRangoFechas;
		
		if(!flagMostrarRangoFechas)
		{
			reporteMAVForm.setFechaFin(null);
			reporteMAVForm.setFechaFinD(null);
			reporteMAVForm.setFechaInicio(null);
			reporteMAVForm.setFechaInicioD(null);
		}
		
		if(log.isDebugEnabled())
		{
			log.debug("flagExtensionArchivo: " + flagMostrarRangoFechas);
			log.debug("fechaInicio: " + reporteMAVForm.getFechaInicio());	
			log.debug("fechaFin: " + reporteMAVForm.getFechaFin());			
		}
	}
	
	/**
	 * @return the mostrarZonas
	 */
	public Boolean getMostrarZonas() {
		return mostrarZonas;
	}

	/**
	 * @param mostrarZonas the mostrarZonas to set
	 */
	public void setMostrarZonas(Boolean mostrarZonas) {
		this.mostrarZonas = mostrarZonas;
	}

	/**
	 * @return the flagRangoFechasBool
	 */
	public boolean isFlagRangoFechasBool() {
		return flagRangoFechasBool;
	}

	/**
	 * @param flagRangoFechasBool the flagRangoFechasBool to set
	 */
	public void setFlagRangoFechasBool(boolean flagRangoFechasBool) {
		this.flagRangoFechasBool = flagRangoFechasBool;
	}

	/**
	 * @return the flagRangoFechas
	 */
	public String getFlagRangoFechas() {
		return flagRangoFechas;
	}

	/**
	 * @param flagRangoFechas the flagRangoFechas to set
	 */
	public void setFlagRangoFechas(String flagRangoFechas) {
		this.flagRangoFechas = flagRangoFechas;
	}

	/**
	 * @return the bloquearTipoCargo
	 */
	public Boolean getBloquearTipoCargo() {
		return bloquearTipoCargo;
	}

	/**
	 * @param bloquearTipoCargo the bloquearTipoCargo to set
	 */
	public void setBloquearTipoCargo(Boolean bloquearTipoCargo) {
		this.bloquearTipoCargo = bloquearTipoCargo;
	}

	/**
	 * @return the bloquearEnvio
	 */
	public Boolean getBloquearEnvio() {
		return bloquearEnvio;
	}

	/**
	 * @param bloquearEnvio the bloquearEnvio to set
	 */
	public void setBloquearEnvio(Boolean bloquearEnvio) {
		this.bloquearEnvio = bloquearEnvio;
	}

	/**
	 * @return the bloquearRadioButton
	 */
	public Boolean getBloquearRadioButton() {
		return bloquearRadioButton;
	}

	/**
	 * @param bloquearRadioButton the bloquearRadioButton to set
	 */
	public void setBloquearRadioButton(Boolean bloquearRadioButton) {
		this.bloquearRadioButton = bloquearRadioButton;
	}

	/**
	 * @return the bloquearRegion
	 */
	public Boolean getBloquearRegion() {
		return bloquearRegion;
	}

	/**
	 * @param bloquearRegion the bloquearRegion to set
	 */
	public void setBloquearRegion(Boolean bloquearRegion) {
		this.bloquearRegion = bloquearRegion;
	}

	/**
	 * @return the bloquearZona
	 */
	public Boolean getBloquearZona() {
		return bloquearZona;
	}

	/**
	 * @param bloquearZona the bloquearZona to set
	 */
	public void setBloquearZona(Boolean bloquearZona) {
		this.bloquearZona = bloquearZona;
	}

	
	public void handleFileUpload(FileUploadEvent event) throws Exception {		
		ReporteMAVAtencionFechaCampanhaPorTipoForm f = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;
		
		if (event != null) {
			f.setSapsFile(event.getFile());
			this.attachment = event.getFile().getFileName();
			this.uploadArchivo();
			XlsxUtil excelUtil = new XlsxUtil(f.getDirectorioTemporal(), this.attachment);		
			
			String codigos = "";
			
			List<String> codigoLista = new ArrayList<String>();
			excelUtil.initSheet(0);
			
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				codigos = mapRow.get("0").toString().trim();
				
				if(StringUtils.isNotBlank(codigos))
					codigoLista.add(codigos);
			}
			
			excelUtil.cerrar();
			
			String []temp= new String [codigoLista.size()];
			temp = codigoLista.toArray(temp);		
			this.listCodigoSAP = temp;			
		}
	}
	
	public void uploadArchivo(){
		try {
			ReporteMAVAtencionFechaCampanhaPorTipoForm f = (ReporteMAVAtencionFechaCampanhaPorTipoForm) this.formReporte;			
			UploadedFile archivo = f.getSapsFile();
			
			// leyemos el stream de entrada
			InputStream is = archivo.getInputstream();
			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente
			OutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), this.attachment));
			// grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			
			os.close();
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the listCodigoSAP
	 */
	public String[] getListCodigoSAP() {
		return listCodigoSAP;
	}

	/**
	 * @param listCodigoSAP the listCodigoSAP to set
	 */
	public void setListCodigoSAP(String[] listCodigoSAP) {
		this.listCodigoSAP = listCodigoSAP;
	}
}