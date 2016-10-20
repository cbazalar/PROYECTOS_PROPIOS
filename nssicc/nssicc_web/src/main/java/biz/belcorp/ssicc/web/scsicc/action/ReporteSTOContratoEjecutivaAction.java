package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOContratoEjecutivaForm;


@ManagedBean
@SessionScoped
public class ReporteSTOContratoEjecutivaAction  extends BaseReporteAbstractAction
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4782064041339774133L;
	
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List stoTipoDocList;

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getStoTipoDocList() {
		return stoTipoDocList;
	}

	public void setStoTipoDocList(List stoTipoDocList) {
		this.stoTipoDocList = stoTipoDocList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception 
	{
		ReporteSTOContratoEjecutivaForm reporteForm = new ReporteSTOContratoEjecutivaForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			   return "reporteSTOContratoEjecutivaXLS";
			else
				return "reporteSTOContratoEjecutivaPDF";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception 
	{
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception 
	{
		ReporteSTOContratoEjecutivaForm f = (ReporteSTOContratoEjecutivaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String fechaDesde = "";		
		String fechaHasta = "";
		if(f.getFechaHastaD()!=null && f.getFechaDesdeD()!=null){
        	fechaDesde = DateUtil.convertDateToString(f.getFechaDesdeD());
        	fechaHasta = DateUtil.convertDateToString(f.getFechaHastaD());
        }
		
		String condicionZonas = obtieneCondicion(f.getZonaList(),"C.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(f.getRegionList(), "C.COD_REGI", "'");
		
		String condicionPeriodo ="";
		String condicionFechas ="";
		String condicionCliente="";
		String condicionTipoDocumento="";
		String condicionNumeroDocumento="";
		
		if(StringUtils.isNotEmpty(f.getCodigoPeriodo()))
			condicionPeriodo = " AND c.cod_peri = '"+f.getCodigoPeriodo()+"'";
		
		if(StringUtils.isNotEmpty(fechaDesde) && StringUtils.isNotEmpty(fechaHasta)){
			condicionFechas = " and trunc(C.FEC_PROC) >= to_date('"+fechaDesde+"','dd/MM/yyyy') " +
					" and trunc(C.FEC_PROC) <= to_date('"+fechaHasta+"','dd/MM/yyyy')";			
		}
		
		if(StringUtils.isNotEmpty(f.getCodigoCliente())){
			condicionCliente = " and c.cod_clie ='"+f.getCodigoCliente()+"'";
		}
		
		if(StringUtils.isNotEmpty(f.getTipoDocumento())){
			condicionTipoDocumento = " and c.cod_tipo_docu ='"+f.getTipoDocumento()+"'";
		}
		
		if(StringUtils.isNotEmpty(f.getCodigoCliente())){
			condicionNumeroDocumento = " and c.num_docu_iden ='"+f.getNumDocuIdentidad()+"'";
		}
		
		String condicion = condicionZonas + condicionRegion + condicionPeriodo + condicionFechas + condicionCliente +condicionTipoDocumento + condicionNumeroDocumento;
 
		params.put("condicion", condicion);
		params.put("NroReporte", "");
		params.put("fechaDesde", fechaDesde);
		params.put("fechaHasta", fechaHasta);
		params.put("codigoPais", f.getCodigoPais());
		params.put("titulo", getResourceMessage("reporteSTOContratoEjecutivaForm.titulo") + " " + f.getCodigoPeriodo());
			
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarReporteXLS = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ReporteSTOContratoEjecutivaForm f = (ReporteSTOContratoEjecutivaForm) this.formReporte ;
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);

		f.setOidIdiomaIso(this.mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
     // Carga Fecha y Periodo
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
        
        stoTipoDocList = procesoSTOEjecucionValidacionesService.getTipoDocumento();
	}

	/**
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		if (valor.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.FORMATEAR_TODOS);
		} else {
			setSiccZonaList(null);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteSTOContratoEjecutivaForm form = (ReporteSTOContratoEjecutivaForm) this.formReporte;
		if (form.getFechaHastaD() != null && form.getFechaDesdeD() != null) {
			if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
				String mensaje = this
						.getResourceMessage("reporteRETResumenComisionVentaRetailForm.validar.fechas");
				return mensaje;
			}
		}
		if (form.getFechaHastaD() != null && form.getFechaDesdeD() == null) {
			if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
				String mensaje = "Ingresar Fecha Inicio";
				return mensaje;
			}
		}
		if (form.getFechaHastaD() == null && form.getFechaDesdeD() != null) {
			if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
				String mensaje = "Ingresar Fecha Final";
				return mensaje;
			}
		}
		return null;
	}
}
