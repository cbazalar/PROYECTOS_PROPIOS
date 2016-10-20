package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTORechazadasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTORechazadasAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 4911139845874633630L;
	private String formatoReporte;
	private String secuencia = "";
	private String flagFechaFact = "";
	private List stoTipoDocumentoList = new ArrayList();
	private List stoHorasCargaList= new ArrayList();
	private List stoHorasProcesoList= new ArrayList();
	private List stoHorasFacturacionList= new ArrayList();
	private LabelValue[] stoOrigenesList= {};
	private Integer longitudCampoClientes;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		//this.mostrarReporteXLS97 = true;
		ReporteSTORechazadasForm f = (ReporteSTORechazadasForm) this.formReporte;
		Map criteria = new HashMap();
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =  this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = pais.getCodigo();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) this
				.getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) this
				.getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
	
		this.stoTipoDocumentoList = procesoSTOEjecucionValidacionesService.getTiposDocumentosSTO(criteria);

		criteria.put("codigoParametro", Constants.STO_INTERVALO_CARGA_STO);
		List listaHorasCarga = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasCargaList =  listaHorasCarga;
		List listaHorasProceso = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasProcesoList = listaHorasProceso;
		List listaHorasFacturacion = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		
		this.stoHorasFacturacionList = listaHorasFacturacion;
		
		this.stoOrigenesList =  ajaxService.getOrigenSTOByTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC);
		
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		
		this.longitudCampoClientes = clienteService.getTamanhoNumeroCliente(codigoPais);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicio = new Date(System.currentTimeMillis());
		Date fechaFin = new Date(System.currentTimeMillis());

		f.setFechaInicioProceso(sdf.format(fechaInicio));
		f.setFechaInicioProcesoD(fechaInicio);
		f.setCodigoPais(pais.getCodigo());
		try {
			fechaFin = DateUtil.addToDate(fechaFin, Calendar.DATE, 1);
		} catch (Exception e) {
			fechaFin = new Date(System.currentTimeMillis());
		}

		f.setFechaFinProceso(sdf.format(fechaFin));
		f.setFechaFinProcesoD(fechaFin);
		f.setHoraInicioProceso("00:00");
		f.setHoraFinProceso("00:00");
		f.setFechaProgFacturacion(sdf.format(fechaInicio));
		f.setHoraInicioProgFactura("00:00");
		f.setHoraFinProgFactura("00:00");
		
		this.siccZonaList = new LabelValue[1];
		LabelValue labelValue = new LabelValue();
		labelValue.setLabel("Todos");
		labelValue.setValue("");
		this.siccZonaList[0] = labelValue;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug("prepareParameterMap");
		ReporteSTORechazadasForm f = (ReporteSTORechazadasForm) this.formReporte;
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		this.formatoReporte = f.getFormatoExportacion();
		String codigoPais = pais.getCodigo();
		
		String fecha1 = DateUtil.getDate(f.getFechaInicioCargaD());
 		String fecha2 = DateUtil.getDate(f.getFechaFinCargaD());
 		String fecha3 = DateUtil.getDate(f.getFechaInicioProcesoD());
 		String fecha4 = DateUtil.getDate(f.getFechaFinProcesoD());
 		String fecha5 = DateUtil.getDate(f.getFechaInicioProgFacturacionD());
 		String fecha6 = DateUtil.getDate(f.getFechaFinProgFacturacionD());
 		
 		f.setFechaInicioCarga(fecha1);
 		f.setFechaFinCarga(fecha2);
 		f.setFechaInicioProceso(fecha3);
 		f.setFechaFinProceso(fecha4);
 		f.setFechaInicioProgFacturacion(fecha5);
 		f.setFechaFinProgFacturacion(fecha6);

		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, f.getTipoDocumento());
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		params.put("NroReporte", " ");
		params.put(
				"titulo",
				getReportResourceMessage("reporteSTORechazadasForm.titulo_reporte"));

		this.secuencia = reporteService.getObtenerSecuenciaTempSTORechazadas(params);
		f.setSecuencia(secuencia);

		if (f.getTipoDocumento().equals("OCC")) {
			f.setFlagFechaFact("1");
			this.flagFechaFact = "1";
		} else {
			f.setFlagFechaFact(null);
			this.flagFechaFact = null;
		}
		params = getCriteria(f);
		reporteService.insertTemporalSTOReporteRechazadas(params);
		f.setFlagFechaFact(this.flagFechaFact);
		f.setSecuencia(this.secuencia);
		return params;
	}

	/**
	 * @param f
	 * @param request
	 * @return Devuelve el filtro con los criterios de busqueda
	 */
	protected Map getCriteria(ReporteSTORechazadasForm f) {
		Map params = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String numLote = f.getNumeroLote();

		String indDocu = new String();
		indDocu = "1";
		String fechaInicio = null;
		if (!f.getFechaInicioCarga().equals(""))
			fechaInicio = (f.getFechaInicioCarga() + " " + f
					.getHoraInicioCarga()).trim();
		String fechaFin = null;
		if (!f.getFechaFinCarga().equals(""))
			fechaFin = (f.getFechaFinCarga() + " " + f.getHoraFinCarga())
					.trim();
		String fechaInicioProceso = null;
		if (!f.getFechaInicioProceso().equals(""))
			fechaInicioProceso = (f.getFechaInicioProceso() + " " + f
					.getHoraInicioProceso()).trim();
		String fechaFinProceso = null;
		if (!f.getFechaFinProceso().equals(""))
			fechaFinProceso = (f.getFechaFinProceso() + " " + f
					.getHoraFinProceso()).trim();

		String codigoCliente = f.getCodigoCliente();
		String codigoPeriodo = f.getCodigoPeriodo();

		String fechaInicioProgramadaFacturacion = null;
		String fechaFinProgramadaFacturacion = null;
		if (!f.getFechaInicioProgFacturacion().equals(""))
			fechaInicioProgramadaFacturacion = (f
					.getFechaInicioProgFacturacion() + " " + f
					.getHoraInicioProgFactura()).trim();
		if (!f.getFechaFinProgFacturacion().equals(""))
			fechaFinProgramadaFacturacion = (f.getFechaFinProgFacturacion()
					+ " " + f.getHoraFinProgFactura()).trim();

		if (f.getRegionList() != null) {
			if (f.getRegionList().length == 1) {
				if (f.getRegionList()[0] == null
						|| f.getRegionList()[0].compareToIgnoreCase("") == 0) {
					f.setRegionList(null);
				}
			}
		}
		if (f.getZonaList() != null) {
			if (f.getZonaList().length == 1) {
				if (f.getZonaList()[0] == null
						|| f.getZonaList()[0].compareToIgnoreCase("") == 0) {
					f.setZonaList(null);
				}
			}
		}

		String estadoGP1 = "";
		String estadoFacturadas = "";
		String estadoError = "";
		String estadoRechazadas = "";
		String estadoSinValidar = "";

		estadoRechazadas = Constants.SI;

		try {
			params = BeanUtils.describe(f);
			params.put("codigoPais", pais.getCodigo());
			params.put("tipoDocumento", f.getTipoDocumento());
			params.put("descripcionDocumento", f.getDescripcionDocumento());
			params.put("numLote", numLote);
			params.put("indDocu", indDocu);
			params.put("fechaInicio", fechaInicio);
			params.put("fechaFin", fechaFin);
			params.put("fechaInicioProceso", fechaInicioProceso);
			params.put("fechaFinProceso", fechaFinProceso);
			params.put("codigoCliente", codigoCliente);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put(
					"regionList",
					(f.getRegionList() == null) ? new ArrayList() : Arrays
							.asList(f.getRegionList()));
			params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
					: Arrays.asList(f.getZonaList()));
			params.put("estadoGP1", estadoGP1);
			params.put("estadoFacturadas", estadoFacturadas);
			params.put("estadoError", estadoError);
			params.put("estadoRechazadas", estadoRechazadas);
			params.put("estadoSinValidar", estadoSinValidar);

			params.put("fechaInicioProgramadaFacturacion",
					fechaInicioProgramadaFacturacion);
			params.put("fechaFinProgramadaFacturacion",
					fechaFinProgramadaFacturacion);
			params.put("secuencia", f.getSecuencia());
			params.put("flagFechaFact", f.getFlagFechaFact());

			// ----------------------------------------------
			List listaClientes = null;

			String[] listaClientes2 = new String[0];
			if (codigoCliente.length() > 0)
				listaClientes2 = f.getCodigoCliente().split(",+");
			f.setClienteList(null);
			Long longitudPais = pais.getLongitudCodigoCliente();
			

			String[] clienteList;
			int size = 0;
			if (listaClientes != null) {
				clienteList = new String[listaClientes.size()
						+ listaClientes2.length];
				size = listaClientes.size();
				for (int i = 0; i < listaClientes.size(); i++) {
					LabelValue bean = (LabelValue) listaClientes.get(i);
					clienteList[i] = StringUtils.leftPad(bean.getLabel(),
							longitudPais.intValue(), '0');
				}
			} else {
				clienteList = new String[listaClientes2.length];
			}

			for (int i = 0; i < listaClientes2.length; i++) {
				clienteList[i + size] = StringUtils.leftPad(listaClientes2[i],
						longitudPais.intValue(), '0');
			}
			f.setClienteList(clienteList);
			params.put(
					"clienteList",
					(f.getClienteList() == null) ? new ArrayList() : Arrays
							.asList(f.getClienteList()));
			// ----------------------------------------------

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return params;
	}

	/**
	 * @param codigoPais
	 * @param codigoRegion
	 * @return
	 */
	public LabelValue[] getZonaByRegion(final String codigoPais,
			final String[] codigoRegion) {

		LabelValue[] result = null;
		String condicionTodos = Constants.NO;
		InterfazSiCCService interfazSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteria.put("codigoRegion", codigoRegion);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = interfazSiCC.getLista(
						"getZonasMultipleByPaisMarcaCanalRegion", criteria);
				if (zonasList != null && zonasList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}
	
	/**
	 * Show zonasx region.
	 * 
	 * @param val
	 *            the val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteSTORechazadasForm form = (ReporteSTORechazadasForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
						form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones,
						Constants.FORMATO_TOTAL));
				form.setZonaList(null);
			} else {
				this.siccZonaList = null;
				form.setZonaList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTORechazadasForm form = new ReporteSTORechazadasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteSTORechazadasXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTORechazadasXLS";
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
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * @return the flagFechaFact
	 */
	public String getFlagFechaFact() {
		return flagFechaFact;
	}

	/**
	 * @param flagFechaFact the flagFechaFact to set
	 */
	public void setFlagFechaFact(String flagFechaFact) {
		this.flagFechaFact = flagFechaFact;
	}

	/**
	 * @return the stoTipoDocumentoList
	 */
	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	/**
	 * @param stoTipoDocumentoList the stoTipoDocumentoList to set
	 */
	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	/**
	 * @return the stoHorasCargaList
	 */
	public List getStoHorasCargaList() {
		return stoHorasCargaList;
	}

	/**
	 * @param stoHorasCargaList the stoHorasCargaList to set
	 */
	public void setStoHorasCargaList(List stoHorasCargaList) {
		this.stoHorasCargaList = stoHorasCargaList;
	}

	/**
	 * @return the stoHorasProcesoList
	 */
	public List getStoHorasProcesoList() {
		return stoHorasProcesoList;
	}

	/**
	 * @param stoHorasProcesoList the stoHorasProcesoList to set
	 */
	public void setStoHorasProcesoList(List stoHorasProcesoList) {
		this.stoHorasProcesoList = stoHorasProcesoList;
	}

	/**
	 * @return the stoHorasFacturacionList
	 */
	public List getStoHorasFacturacionList() {
		return stoHorasFacturacionList;
	}

	/**
	 * @param stoHorasFacturacionList the stoHorasFacturacionList to set
	 */
	public void setStoHorasFacturacionList(List stoHorasFacturacionList) {
		this.stoHorasFacturacionList = stoHorasFacturacionList;
	}

	/**
	 * @return the stoOrigenesList
	 */
	public LabelValue[] getStoOrigenesList() {
		return stoOrigenesList;
	}

	/**
	 * @param stoOrigenesList the stoOrigenesList to set
	 */
	public void setStoOrigenesList(LabelValue[] stoOrigenesList) {
		this.stoOrigenesList = stoOrigenesList;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
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