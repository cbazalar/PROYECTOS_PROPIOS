package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDOrigenPedidoFacturadoCampanaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReportePEDOrigenPedidoFacturadoCampanaAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 4019351111130150606L;
	private boolean muestraPeriodo;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDOrigenPedidoFacturadoCampanaForm reporteForm = new ReportePEDOrigenPedidoFacturadoCampanaForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDOrigenPedidoFacturadoCampanaForm f = (ReportePEDOrigenPedidoFacturadoCampanaForm)this.formReporte;
		String nombreReporte = "";
		if(StringUtils.equalsIgnoreCase("XLS", this.formatoExportacion)){	
			if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "ACTU")){
				nombreReporte = "reportePEDOrigenPedidoFacturadoCampanaActualXLS";
				}else{
					if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "HISTO")){
						nombreReporte = "reportePEDOrigenPedidoFacturadoCampanaHistoricoXLS";
				}	
			}
		}else{
			nombreReporte = "reporteMaestroVertical";
		}
		return nombreReporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePEDOrigenPedidoFacturadoCampanaForm f=(ReportePEDOrigenPedidoFacturadoCampanaForm)this.formReporte;
		
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		
        if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "ACTU")){
			params.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		}else{
			if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "HISTO")){
				params.put("codigoPeriodo", f.getCodigoPeriodo());
			}
		}
        
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		this.muestraPeriodo = false;
		ReportePEDOrigenPedidoFacturadoCampanaForm f=(ReportePEDOrigenPedidoFacturadoCampanaForm)this.formReporte;
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
	}
	
	public void mostrarPeriodo(ValueChangeEvent val){
		String valor = (String)val.getNewValue();
		if(log.isDebugEnabled()){
			log.debug("mostrarPeriodo");
		}
		if(StringUtils.equalsIgnoreCase(valor, "ACTU")){
			this.muestraPeriodo = false;
		}else{
			if(StringUtils.equalsIgnoreCase(valor, "HISTO")){
				this.muestraPeriodo = true;
			}
		}
	}

	/**
	 * @return the muestraPeriodo
	 */
	public boolean isMuestraPeriodo() {
		return muestraPeriodo;
	}

	/**
	 * @param muestraPeriodo the muestraPeriodo to set
	 */
	public void setMuestraPeriodo(boolean muestraPeriodo) {
		this.muestraPeriodo = muestraPeriodo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		String mensaje = null;
		ReportePEDOrigenPedidoFacturadoCampanaForm f=(ReportePEDOrigenPedidoFacturadoCampanaForm)this.formReporte;
		if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "HISTO")){
			if(StringUtils.isBlank(f.getCodigoPeriodo())){
				mensaje = "Debe ingresar un Periodo";
			}
		}
		return mensaje;
	}
}
