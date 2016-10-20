package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazCALReporteConsultorasRecomendadas;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCALConsultorasRecomendadasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCALConsultorasRecomendadasAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccSubGerenciaList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccCanalRolList;
	private List siccEstadoRolList;

	/**
	 * Metodo para ejecutar el PDF
	 * @param evt
	 */
	public void executePDF(ActionEvent evt) {
		HttpServletResponse response = this.getResponse();
		try {
			ReporteCALConsultorasRecomendadasForm reportForm = (ReporteCALConsultorasRecomendadasForm) this.formReporte;
//			if (reportForm.getTipoReporte().equals("out"))
//				return;
			
			reportForm.setFechaDesde(DateUtil.convertDateToString(reportForm.getFechaDesdeD()));
			reportForm.setFechaHasta(DateUtil.convertDateToString(reportForm.getFechaHastaD()));
			
			int resultado = DateUtil.compareDates(reportForm.getFechaDesde(), reportForm.getFechaHasta(), "dd/MM/yyyy");
			if (resultado == 1) { 
				this.addWarn("", "La Fecha Hasta debe ser mayor a la Fecha Desde");
				return;
			}

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			reportForm.setTipoReporte("pdf");
			InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Map params = BeanUtils.describe(reportForm);

			params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			params.put("usuario", usuario.getClave());

			byte[] bytes;

			if (reportForm.getTipoReporte().equals("pdf")) {
				bytes = service.getBytesReporteConsultorasRecomendadasPDF(
						params, usuario, pais);
				if (bytes.length > 0) {
					response.reset();
					log.debug("Imprimiendo reporte...");
					response.setContentType("application/pdf");
					response.addHeader("Content-Disposition", "inline");
					response.setContentLength(bytes.length);
					response.getOutputStream().write(bytes);
					response.getOutputStream().flush();
					response.getOutputStream().close();
					this.responseComplete();

					// log.debug("Imprimiendo reporte...");
					// response.setContentType("application/pdf");
					// response.setContentLength(bytes.length);
					// ServletOutputStream outputStream =
					// response.getOutputStream();
					// outputStream.write(bytes, 0, bytes.length);
					// outputStream.flush();
					// outputStream.close();
					// forward = null;
				}else{
					this.addError("Error : ", "No hay informacion para procesar el PDF");
				}

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo para ejecutar el excel
	 * @param evt
	 */
	public void executeExcel(ActionEvent evt) {
		HttpServletResponse response = this.getResponse();
		try {
			ReporteCALConsultorasRecomendadasForm reportForm = (ReporteCALConsultorasRecomendadasForm) this.formReporte;

			reportForm.setTipoReporte("xls");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			reportForm.setFechaDesde(DateUtil.convertDateToString(reportForm.getFechaDesdeD()));
			reportForm.setFechaHasta(DateUtil.convertDateToString(reportForm.getFechaHastaD()));
			
			int resultado = DateUtil.compareDates(reportForm.getFechaDesde(), reportForm.getFechaHasta(), "dd/MM/yyyy");
			if (resultado == 1) { 
				this.addWarn("", "La Fecha Hasta debe ser mayor a la Fecha Desde");
				return;
			}
			
			InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Map params = BeanUtils.describe(reportForm);

			params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
			params.put("usuario", usuario.getClave());

			if (reportForm.getTipoReporte().equals("xls")) {
				log.debug("Entrando al reporte en excel.");
				List listaConsultoras = new LinkedList();
				listaConsultoras = service
						.getReporteConsultorasRecomendadasXLS(params);

				log.debug("tama�o de la lista de consultoras:"
						+ listaConsultoras.size());
				if (listaConsultoras.size() > 0) {
					log.debug("Imprimiendo reporte............");
					HSSFWorkbook wb;
					wb = this.getHSSFWorkbook(listaConsultoras);
					ServletOutputStream outputStream = response
							.getOutputStream();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"inline; filename=\""
									+ "ConsultorasRecomendadas.xls" + "\"");
					wb.write(outputStream);
					outputStream.close();
				}else{
					this.addError("Error : ", "No hay informacion para procesar el Excel");
				}
			}

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Creara el excel
	 * @param listaConsultoras
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public HSSFWorkbook getHSSFWorkbook(List listaConsultoras) {
		InterfazCALReporteConsultorasRecomendadas bean = new InterfazCALReporteConsultorasRecomendadas();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("CONSULTORASRECOMENDADAS");
		int columnIndex = 0;
		HSSFRow row = sheet.createRow(0);
		row.createCell((short) columnIndex++).setCellValue(
				"Referencia REP-CAL-009-");
		row.createCell((short) columnIndex++).setCellValue("Regi�n");
		row.createCell((short) columnIndex++).setCellValue("Zona");
		row.createCell((short) columnIndex++).setCellValue("C�digo Cliente");
		row.createCell((short) columnIndex++).setCellValue("Tipo Documento");
		row.createCell((short) columnIndex++).setCellValue("N�mero");
		row.createCell((short) columnIndex++).setCellValue("Primer Nombre");
		row.createCell((short) columnIndex++).setCellValue("Segundo Nombre");
		row.createCell((short) columnIndex++).setCellValue("Apellido Paterno");
		row.createCell((short) columnIndex++).setCellValue("Apellido Materno");
		row.createCell((short) columnIndex++).setCellValue("Direcci�n");
		row.createCell((short) columnIndex++).setCellValue("Tel�fono");
		row.createCell((short) columnIndex++).setCellValue("C�digo");
		row.createCell((short) columnIndex++).setCellValue("Primer Nombre");
		row.createCell((short) columnIndex++).setCellValue("Segundo Nombre");
		row.createCell((short) columnIndex++).setCellValue("Apellido Paterno");
		row.createCell((short) columnIndex++).setCellValue("Apellido Materno");
		row.createCell((short) columnIndex++).setCellValue("Zona");
		row.createCell((short) columnIndex++).setCellValue("Territorio");
		row.createCell((short) columnIndex++)
				.setCellValue("Fecha de Registro ");
		row.createCell((short) columnIndex++).setCellValue("Estado");
		row.createCell((short) columnIndex++).setCellValue("Observacion");
		row.createCell((short) columnIndex++).setCellValue("Usuario");
		row.createCell((short) columnIndex++).setCellValue("Fecha Proceso");

		for (int i = 0; i < listaConsultoras.size(); i++) {
			bean = (InterfazCALReporteConsultorasRecomendadas) listaConsultoras
					.get(i);
			columnIndex = 0;
			row = sheet.createRow(i + 1);
			row.createCell((short) columnIndex++).setCellValue(bean.getCodfi());
			row.createCell((short) columnIndex++)
					.setCellValue(bean.getRegion());
			row.createCell((short) columnIndex++).setCellValue(bean.getZona());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getCodigoCliente());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getTipoDocumento());
			row.createCell((short) columnIndex++)
					.setCellValue(bean.getNumero());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getPrimerNombre());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getSegundoNombre());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getApellidoPaterno());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getApellidoMaterno());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getDireccion());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getTelefono());
			row.createCell((short) columnIndex++)
					.setCellValue(bean.getCodigo());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getPrimerNombreRecomendada());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getSegundoNombreRecomendada());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getApellidoPaternoRecomendada());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getApellidoMaternoRecomendada());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getZonaRecomendada());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getTerritorioRecomendada());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getFechaRegistro());
			row.createCell((short) columnIndex++)
					.setCellValue(bean.getEstado());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getObservacion());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getUsuario());
			row.createCell((short) columnIndex++).setCellValue(
					bean.getFechaProceso());
		}
		return wb;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCALConsultorasRecomendadasForm form = new ReporteCALConsultorasRecomendadasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return null;
	}

	/**
	 * Inicializar valores
	 */
	public void inicializando() {
		this.siccMarcaList = new ArrayList();
		this.siccMarcaList = new ArrayList();
		this.siccMarcaList = new ArrayList();
		this.siccMarcaList = new ArrayList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.mostrarReportePDF = false;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccCanalRolList = svc.getCanalesRolByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		this.siccEstadoRolList = svc.getEstadosRolByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		log.debug("Retornando...");
		ReporteCALConsultorasRecomendadasForm f = (ReporteCALConsultorasRecomendadasForm) this.formReporte;
		f.setFechaDesdeD(new Date());
		f.setFechaHastaD(new Date());
		
	}
	
	public void loadRegion(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(StringUtils.isNotBlank(valor))
		{
			this.siccRegionList = ajax.getRegionesByPaisCanal(pais.getCodigo(), valor);
			if(this.siccRegionList.length == 0)
				this.siccZonaList = null;
			else
				this.siccZonaList = ajax.getZonasByPaisCanalRegion(pais.getCodigo(), valor, "");
		}		
	}
	
	public void loadZona(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCALConsultorasRecomendadasForm f = (ReporteCALConsultorasRecomendadasForm) this.formReporte;
		
		this.siccZonaList = ajax.getZonasByPaisCanalRegion(pais.getCodigo(), f.getCodigoCanal(), valor);
	}
	
	public void loadSubGerencia(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCALConsultorasRecomendadasForm f = (ReporteCALConsultorasRecomendadasForm) this.formReporte;
		
		this.siccSubGerenciaList = ajax.getSubGerenciasByPaisMarcaCanal(pais.getCodigo(), valor, f.getCodigoCanal());
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccSubGerenciaList
	 */
	public LabelValue[] getSiccSubGerenciaList() {
		return siccSubGerenciaList;
	}

	/**
	 * @param siccSubGerenciaList
	 *            the siccSubGerenciaList to set
	 */
	public void setSiccSubGerenciaList(LabelValue[] siccSubGerenciaList) {
		this.siccSubGerenciaList = siccSubGerenciaList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
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
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccCanalRolList
	 */
	public List getSiccCanalRolList() {
		return siccCanalRolList;
	}

	/**
	 * @param siccCanalRolList
	 *            the siccCanalRolList to set
	 */
	public void setSiccCanalRolList(List siccCanalRolList) {
		this.siccCanalRolList = siccCanalRolList;
	}

	/**
	 * @return the siccEstadoRolList
	 */
	public List getSiccEstadoRolList() {
		return siccEstadoRolList;
	}

	/**
	 * @param siccEstadoRolList
	 *            the siccEstadoRolList to set
	 */
	public void setSiccEstadoRolList(List siccEstadoRolList) {
		this.siccEstadoRolList = siccEstadoRolList;
	}

}