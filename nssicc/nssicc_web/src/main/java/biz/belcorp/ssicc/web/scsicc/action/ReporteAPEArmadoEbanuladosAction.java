package biz.belcorp.ssicc.web.scsicc.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEArmadoEbanuladosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteAPEArmadoEbanuladosAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private String attachment;
	private String formatoReporte;
	private InputStream archivoSession;
	private List listaFacturas = new ArrayList();
	private List siccRegionList = new ArrayList();
	private List siccZonaList = new ArrayList();
	private List repSacDetTipoOfertaList = new ArrayList();

	/**
	 * @return
	 */
	public List getRepSacDetTipoOfertaList() {
		return repSacDetTipoOfertaList;
	}

	/**
	 * @param repSacDetTipoOfertaList
	 */
	public void setRepSacDetTipoOfertaList(List repSacDetTipoOfertaList) {
		this.repSacDetTipoOfertaList = repSacDetTipoOfertaList;
	}

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
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
	 * @return
	 */
	public InputStream getArchivoSession() {
		return archivoSession;
	}

	/**
	 * @param archivoSession
	 */
	public void setArchivoSession(InputStream archivoSession) {
		this.archivoSession = archivoSession;
	}

	/**
	 * @return
	 */
	public List getListaFacturas() {
		return listaFacturas;
	}

	/**
	 * @param listaFacturas
	 */
	public void setListaFacturas(List listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteAPEArmadoEbanuladosForm form = new ReporteAPEArmadoEbanuladosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoReporte)) {
			return "reporteAPEArmadoEbanuladosXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteAPEArmadoEbanuladosForm reporteAPEForm = (ReporteAPEArmadoEbanuladosForm) this.formReporte;
		this.formatoReporte = reporteAPEForm.getFormatoExportacion();
		String condicion = "";
		String codigoFactura = reporteAPEForm.getCodigoFactura();
		// Si es cargado por la caja de texto
		if (codigoFactura != null && codigoFactura.length() > 0) {
			this.listaFacturas.add(codigoFactura);
		}

		if (this.listaFacturas != null && this.listaFacturas.size() > 0) {

			String[] arrayFacturas = new String[listaFacturas.size()];
			this.listaFacturas.toArray(arrayFacturas);

			condicion = condicion
					+ this.obtieneCondicion(arrayFacturas, "CON.VAL_NUME_SOLI",
							"'");
		}

		params.put("NroReporte", "");
		params.put("condicion", condicion);
		params.put("titulo",
				this.getResourceMessage("reporteAPEArmadoEbanuladosForm.title"));

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		
		log.info("ReporteAPEArmadoEbanuladosAction - setViewAttributes");
		this.listaFacturas = new ArrayList();
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");
		ReporteAPEArmadoEbanuladosForm form = (ReporteAPEArmadoEbanuladosForm) this.formReporte;
		form.getIdioma().setCodigoISO(
				this.mPantallaPrincipalBean.getCurrentUser().getIdioma()
						.getCodigoISO());

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccZonaList = new ArrayList();
		this.repSacDetTipoOfertaList = reporteService.getTipoOfertas();

	}

	public void validar() {
		if (log.isDebugEnabled()) {
			log.debug("validar");
		}
		this.listaFacturas = new ArrayList();
		ReporteAPEArmadoEbanuladosForm reporteAPEForm = (ReporteAPEArmadoEbanuladosForm) formReporte;
		reporteAPEForm.setValorFile(Constants.ESTADO_ACTIVO);
		int contador = 0;
		archivoSession = null;
		try {
			UploadedFile archivo = reporteAPEForm.getFacturaFile();

			InputStream is = archivo.getInputstream();
			this.archivoSession = is;

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea = "";

			while (true) {
				linea = br.readLine();
				if (linea == null) {
					contador++;
					break;
				}
				if ("".equals(linea)) {
					contador++;
				} else {
					this.listaFacturas.add(linea.trim());
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

		if (contador > 0 && this.listaFacturas.size() == 0) {
			reporteAPEForm.setValorFile(Constants.NUMERO_CERO);
		} else {
			
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		
		ReporteAPEArmadoEbanuladosForm reporteAPEForm = (ReporteAPEArmadoEbanuladosForm) formReporte;
		
		if(StringUtils.isBlank(this.getAttachment()) && StringUtils.isBlank(reporteAPEForm.getCodigoFactura()))
		{
			return this.getResourceMessage("reporteAPEArmadoEbanuladosForm.err.tipoFactura");
		}
			
		return null;
	}

	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			ReporteAPEArmadoEbanuladosForm f = (ReporteAPEArmadoEbanuladosForm) this.formReporte;
			if (event != null) {
				f.setFacturaFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.validar();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}