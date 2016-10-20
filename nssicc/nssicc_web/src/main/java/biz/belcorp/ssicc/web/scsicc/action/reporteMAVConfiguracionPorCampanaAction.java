package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.reporteMAVConfiguracionPorCampanaForm;

@ManagedBean
@SessionScoped
public class reporteMAVConfiguracionPorCampanaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -327119423812976776L;
	
	private String formatoReporte;
	private List siccTipoClienteList;
	private List siccTipoOperaList;
	private String tipoReporte;
	private String opcionRegionZona;
	private String opcionCliente;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		reporteMAVConfiguracionPorCampanaForm reporteForm = new reporteMAVConfiguracionPorCampanaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reportService = (ReporteService) getBean("scsicc.reporteService");

		reporteMAVConfiguracionPorCampanaForm f = (reporteMAVConfiguracionPorCampanaForm) this.formReporte;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de la lista de clientes
		this.siccTipoClienteList = service.getTiposClientesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		// Carga la lista de tipo ofertas
		this.siccTipoOperaList = reportService.getTipoOfertas();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Se setean los valores por default de los filtros
		f.setPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
				Constants.CODIGO_CANAL_DEFAULT));
		f.setCodigoPais(pais.getCodigo());
		f.setTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);

		f.setTipoOferta(null);
		// Valor por defecto "NO" = 1
		f.setListaRegionZona(Constants.NUMERO_UNO);
		f.setListaCliente(Constants.NUMERO_UNO);
		f.setListaCliente("1");
		f.setListaRegionZona("1");
	
		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteMAVConfPorCampana" + tipoReporte + "XLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteMAVConfPorCampana" + tipoReporte + "PDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {		
		reporteMAVConfiguracionPorCampanaForm reporte = (reporteMAVConfiguracionPorCampanaForm) this.formReporte;
		formatoReporte = reporte.getFormatoExportacion();

		String condicionTipoOferta = obtieneCondicion(
				reporte.getTipoOfertaList(), "PRE.COD_TIPO_OFER", "'");

		// Parametros estandar que se envían a los reportes
		params.put("NroReporte", "");
		params.put("codigoPais", reporte.getCodigoPais());

		
		// Si 'Lista Clientes' = SI
		if (StringUtils.equals(reporte.getListaCliente(), Constants.NUMERO_CERO)) 
			tipoReporte = "ListaClientesSi";
		else 
			tipoReporte = "ListaClientesNo";
		
		
		if (StringUtils.equals(reporte.getListaRegionZona(), Constants.NUMERO_CERO)) 
			tipoReporte = tipoReporte + "RegZonSi";
		else 
			tipoReporte = tipoReporte + "RegZonNo";		

		params.put("periodo", reporte.getPeriodo());
		params.put("tipoCliente", reporte.getTipoCliente());
		params.put("condicionTipoOferta", condicionTipoOferta);
		params.put("titulo",getResourceMessage("reporteMAVConfiguracionPorCampana.title"));

		return params;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList
	 *            the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the siccTipoOperaList
	 */
	public List getSiccTipoOperaList() {
		return siccTipoOperaList;
	}

	/**
	 * @param siccTipoOperaList
	 *            the siccTipoOperaList to set
	 */
	public void setSiccTipoOperaList(List siccTipoOperaList) {
		this.siccTipoOperaList = siccTipoOperaList;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the opcion
	 */
	public String getOpcionCliente() {
		return opcionCliente;
	}

	/**
	 * @param opcion the opcion to set
	 */
	public void setOpcionCliente(String opcionCliente) {
		this.opcionCliente = opcionCliente;
	}

	/**
	 * @return the opcionRegionZona
	 */
	public String getOpcionRegionZona() {
		return opcionRegionZona;
	}

	/**
	 * @param opcionRegionZona the opcionRegionZona to set
	 */
	public void setOpcionRegionZona(String opcionRegionZona) {
		this.opcionRegionZona = opcionRegionZona;
	}
	
}
