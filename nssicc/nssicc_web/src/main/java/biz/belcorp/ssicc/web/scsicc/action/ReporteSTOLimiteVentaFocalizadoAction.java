package biz.belcorp.ssicc.web.scsicc.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOLimiteVentaFocalizadoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTOLimiteVentaFocalizadoAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -451626661205251353L;
	private String formatoReporte;
	private List stoTipoDocumentoList;
	private List stoHorasCargaList;
	private List stoHorasProcesoList;
	private List stoHorasFacturacionList;
	private LabelValue[] stoOrigenesList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private Integer logintudCampoClientes;
	private Boolean detalleModificado;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		log.info("Entro a Report--eSTOFocalizadoAction - setViewAttributes");
		ReporteSTOLimiteVentaFocalizadoForm f = (ReporteSTOLimiteVentaFocalizadoForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		f.setCodigoPais(pais.getCodigo());
		this.detalleModificado = true;
		f.setDetalleModificado("1");
		Map criteria = new HashMap();
		String codigoPais = pais.getCodigo();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario",usuario.getLogin());

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
		this.stoHorasCargaList = listaHorasCarga;		
		List listaHorasProceso = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasProcesoList = listaHorasProceso;
		List listaHorasFacturacion = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		this.stoHorasFacturacionList = listaHorasFacturacion;
		
		stoOrigenesList = ajaxService.getOrigenSTOByTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC);
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		f.setCodigoPais(pais.getCodigo());
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		this.logintudCampoClientes = clienteService.getTamanhoNumeroCliente(codigoPais);

		log.info("Salio a ReporteSTOFocalizadoAction - setViewAttributes");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug("prepareParameterMap");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ReporteSTOLimiteVentaFocalizadoForm f = (ReporteSTOLimiteVentaFocalizadoForm) this.formReporte;
		if(this.detalleModificado)
			f.setDetalleModificado("1");
		else
			f.setDetalleModificado("0");
		
		this.formatoReporte = f.getFormatoExportacion();

		String codigoPais = pais.getCodigo();

		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		StringBuffer condicion = new StringBuffer();

		if (f.getDetalleModificado() == null) {
			condicion = new StringBuffer("");
		} else {
			if (f.getDetalleModificado().equals("on")) {
				condicion.append(" AND b.IND_LIMI_VENT_FOCA = '1' ");
			}
		}

		if (StringUtils.isNotEmpty(f.getCodigoPeriodo())) {
			condicion.append(" AND a.cod_peri=" + f.getCodigoPeriodo() + " ");
		}

		if (StringUtils.isNotEmpty(f.getNumeroLote())) {
			condicion.append(" AND a.num_lote=" + f.getNumeroLote() + " ");
		}
		StringBuffer listaRegiones = new StringBuffer("");

		int nzon = 0;

		if (f.getZonaList() != null) {
			nzon = f.getZonaList().length;
		}

		if (nzon > 0) {
			for (int i = 0; i < nzon; i++) {
				listaRegiones.append(f.getZonaList()[i]);
				if (i < nzon - 1) {
					listaRegiones.append(",");
				}
			}
			condicion.append(" AND (a.cod_zona in (" + listaRegiones.toString()
					+ ")) ");
		}

		log.info(condicion);
		
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		List listaClientes = null;

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			for (int i = 0; i < listaClientes.size(); i++) {
				LabelValue bean = (LabelValue) listaClientes.get(i);
				clienteList.add(StringUtils.leftPad(bean.getLabel(),
						longitudPais.intValue(), '0'));
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente != null && codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			else
				clienteList = null;
			for (int i = 0; i < arrlistaClientes.length; i++) {
				clienteList.add(StringUtils.leftPad(arrlistaClientes[i],
						longitudPais.intValue(), '0'));
			}
		}
		log.info("nueva prueba");
		StringBuffer listCliente = new StringBuffer();
		log.info(clienteList);
		int nclientes = 0;
		if (clienteList != null) {
			nclientes = clienteList.size();
			for (int i = 0; i < nclientes; i++) {
				listCliente.append(clienteList.get(i));
				if (i < nclientes - 1) {
					listCliente.append(",");
				}
			}

			log.info(listCliente);
			condicion.append(" AND (a.cod_clie in (" + listCliente + ")) ");
		}
		String fechaInicio = null;
		String aa = f.getFechaInicioCarga();
		if (StringUtils.isNotBlank(f.getFechaInicioCarga()))
			fechaInicio = (f.getFechaInicioCarga() + " " + f.getHoraInicioCarga()).trim();
		String fechaFin = null;
		if (StringUtils.isNotBlank(f.getFechaFinCarga()))
			fechaFin = (f.getFechaFinCarga() + " " + f.getHoraFinCarga()).trim();
		String fechaInicioProceso = null;
		if (StringUtils.isNotBlank(f.getFechaInicioProceso()))
			fechaInicioProceso = (f.getFechaInicioProceso() + " " + f.getHoraInicioProceso()).trim();
		String fechaFinProceso = null;
		if (StringUtils.isNotBlank(f.getFechaFinProceso()))
			fechaFinProceso = (f.getFechaFinProceso() + " " + f.getHoraFinProceso()).trim();
		String fechaProgramadaFacturacion = null;
		if (StringUtils.isNotBlank(f.getFechaProgFacturacion()))
			fechaProgramadaFacturacion = f.getFechaProgFacturacion().trim();

		params.put("fechaProcesoInicio", fechaInicioProceso);
		params.put("fechaProcesoFin", fechaFinProceso);
		params.put("fechaCargaInicio", fechaInicio);
		params.put("fechaCargaFin", fechaFin);
		params.put("fechaProgFact", fechaProgramadaFacturacion);

		params.put("condicion", condicion.toString());

		params.put("NroReporte", " ");
		params.put(
				"titulo",
				getReportResourceMessage("reporteSTOLimiteVentaFocalizadoForm.titulo_reporte"));
		return params;
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
			ReporteSTOLimiteVentaFocalizadoForm form = (ReporteSTOLimiteVentaFocalizadoForm) this.formReporte;
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
			this.addError("Error : " , this.obtieneMensajeErrorException(e));
		}
	
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
	 * @param event
	 */
	public void loadfile(FileUploadEvent event){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}
		ReporteSTOLimiteVentaFocalizadoForm f = (ReporteSTOLimiteVentaFocalizadoForm) this.formReporte;
		List listaClientes = new ArrayList();
		try{		
			UploadedFile archivo = event.getFile();
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea = "";
			// validacion max num registros acargar
			PaisService paisService = (PaisService) getBean("paisService");
			Pais pais = paisService.getPais(f.getCodigoPais());
	
			int contFilas = 0;
			int numMaximoRegistros = StringUtils.isNotEmpty(pais
					.getMaximoNumeroRegistrosFile()) ? Integer.parseInt(pais
					.getMaximoNumeroRegistrosFile()) : 0;
	
			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;
	
				if (StringUtils.isNotEmpty(linea)) {
					contFilas++;
					if (contFilas > numMaximoRegistros) {
						
						String contenido = String.valueOf(numMaximoRegistros);
						String[] arreglo = new String[0];
						arreglo[0] = contenido;
								
						String mensaje = this.getResourceMessage("errors.maximo.registro",arreglo);
						this.addError("Error", mensaje);
						listaClientes = new ArrayList();
						break;
	
					}
	
					LabelValue bean = new LabelValue(linea.trim(), linea.trim());
					listaClientes.add(bean);
				}
			}
		} catch (IOException e) {			
			e.printStackTrace();			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOLimiteVentaFocalizadoForm form = new ReporteSTOLimiteVentaFocalizadoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteSTOLimiteVentaFocalizadoXLS";
		else
			return "reporteMaestroHorizontal";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTOLimiteVentaFocalizadoXLS";
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

	/**
	 * @return the logintudCampoClientes
	 */
	public Integer getLogintudCampoClientes() {
		return logintudCampoClientes;
	}

	/**
	 * @param logintudCampoClientes the logintudCampoClientes to set
	 */
	public void setLogintudCampoClientes(Integer logintudCampoClientes) {
		this.logintudCampoClientes = logintudCampoClientes;
	}

	/**
	 * @return the detalleModificado
	 */
	public Boolean getDetalleModificado() {
		return detalleModificado;
	}

	/**
	 * @param detalleModificado the detalleModificado to set
	 */
	public void setDetalleModificado(Boolean detalleModificado) {
		this.detalleModificado = detalleModificado;
	}
}