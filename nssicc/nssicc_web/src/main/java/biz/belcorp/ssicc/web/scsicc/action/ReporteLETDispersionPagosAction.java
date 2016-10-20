package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETDispersionPagosForm;


@ManagedBean
@SessionScoped
public class ReporteLETDispersionPagosAction extends BaseReporteAbstractAction implements Serializable {
    
	private static final long serialVersionUID = 700304863174794884L;
	private String formatoReporte;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private boolean ingresoFechas = false;
	private List siccEstadoPagoList;
	private boolean indSoloTarjetas;
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETDispersionPagosForm form = new ReporteLETDispersionPagosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteLETDispersionPagosXLS";
			else
				return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteLETDispersionPagosForm f = (ReporteLETDispersionPagosForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		String condicionZonas = obtieneCondicion(f.getZonaList(),"lcom.cod_zona", "'");
		String condicionRegion = obtieneCondicion(f.getRegionList(), "lcom.cod_regi", "'");
		String condicionIndProcPago = obtieneCondicion(f.getEstadoPagoList(), "lcom.IND_PROC_PAGO", "'");
		params.put("codigoRegion", condicionRegion);
		params.put("codigoZona", condicionZonas);
		params.put("condicionIndProcPago",  condicionIndProcPago);
		
		String fecha1="";
		if (f.getFechaPagosD() != null) {
			fecha1 = DateUtil.getDate(f.getFechaPagosD());
		}
		f.setFechaPagos(fecha1);
		
		String fecha2="";
		if (f.getFechaPagosFinalD() != null) {
			fecha2 = DateUtil.getDate(f.getFechaPagosFinalD());
		}
		f.setFechaPagosFinal(fecha2);
		
		if (this.ingresoFechas) {
			params.put("fechaPagoInicial",  f.getFechaPagos());
			params.put("fechaPagoFinal",  f.getFechaPagosFinal());
		}
		
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f.getCodigoPeriodo());
		params.put("codigoPrograma",map1.get("codigoPrograma"));
		String condTarjetas="";
		if(this.indSoloTarjetas)
			condTarjetas=" and lcom.ltpg_num_tarj is not null";
		
		params.put("condicionSoloTarjeta",condTarjetas);
		
		return params;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteLETDispersionPagosForm f = (ReporteLETDispersionPagosForm) this.formReporte;
		
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;

		Pais pais = f.getPais();
		MantenimientoPEJProgramaEjecutivasService servicePE = (MantenimientoPEJProgramaEjecutivasService)getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		List lista = service.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			f.setCodigoPeriodo((String) lista.get(0));
		}
		
		
		Map result = servicePE.getPeriodoDefault();
		String codigoPeriodo = (String) result.get("codigoPeriodo");
		
		f.setCodigoPeriodo(codigoPeriodo);
		f.setCodigoPeriodoFinal(codigoPeriodo);
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f.getCodigoPeriodo());
		
		f.setCodigoPrograma(map1.get("codigoPrograma").toString());
		f.setDescPrograma(map1.get("descPrograma").toString());
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccRegionList  = aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		
		Map params = new HashMap();
		this.siccEstadoPagoList = lecService.getLECEstadoPagosList(params);
		this.indSoloTarjetas=false;
		log.debug("Todo Ok: Redireccionando");
		
	}
	
	
	/**
	 * Obtiene Lista de Zonas x Region
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+val.getNewValue().toString());
		
		String[] regiones = (String [])val.getNewValue();
		ReporteLETDispersionPagosForm f = (ReporteLETDispersionPagosForm) this.formReporte;
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL);	
		f.setZonaList(null);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		String retorno = "";
		this.ingresoFechas = false;
		ReporteLETDispersionPagosForm f = (ReporteLETDispersionPagosForm) this.formReporte;
		Integer codigoPeriodo = new Integer(f.getCodigoPeriodo());
		Integer codigoPeriodoFinal = new Integer(f.getCodigoPeriodoFinal());
		if (codigoPeriodo.intValue() > codigoPeriodoFinal.intValue()) {
			retorno = this.getResourceMessage("reporteLETDispersionPagosForm.error.intervaloCampanya");
			return retorno;
		}
		
		Date fechaPagoInicial = f.getFechaPagosD();
		Date fechaPagoFinal = f.getFechaPagosFinalD();		
		if (fechaPagoInicial == null && fechaPagoFinal != null) {
			retorno = this.getResourceMessage("reporteLETDispersionPagosForm.error.fechaPagoInicialVacio");
			return retorno;
		}
		if (fechaPagoInicial != null && fechaPagoFinal == null) {
			retorno = this.getResourceMessage("reporteLETDispersionPagosForm.error.fechaPagoFinalVacio");
			return retorno;
		}
		
		if (fechaPagoInicial != null && fechaPagoFinal != null) {	
			if(fechaPagoInicial.compareTo(fechaPagoFinal) > 0) 	{
				retorno = this.getResourceMessage("reporteLETDispersionPagosForm.error.intervaloFechaPago");
				return retorno;
			}
			this.ingresoFechas = true;
		}
		return retorno;
	}
	
	
	
	/* GET - SET */

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
	 * @return the ingresoFechas
	 */
	public boolean isIngresoFechas() {
		return ingresoFechas;
	}

	/**
	 * @param ingresoFechas the ingresoFechas to set
	 */
	public void setIngresoFechas(boolean ingresoFechas) {
		this.ingresoFechas = ingresoFechas;
	}

	/**
	 * @return the siccEstadoPagoList
	 */
	public List getSiccEstadoPagoList() {
		return siccEstadoPagoList;
	}

	/**
	 * @param siccEstadoPagoList the siccEstadoPagoList to set
	 */
	public void setSiccEstadoPagoList(List siccEstadoPagoList) {
		this.siccEstadoPagoList = siccEstadoPagoList;
	}

	/**
	 * @return the indSoloTarjetas
	 */
	public boolean isIndSoloTarjetas() {
		return indSoloTarjetas;
	}

	/**
	 * @param indSoloTarjetas the indSoloTarjetas to set
	 */
	public void setIndSoloTarjetas(boolean indSoloTarjetas) {
		this.indSoloTarjetas = indSoloTarjetas;
	}	

	
}