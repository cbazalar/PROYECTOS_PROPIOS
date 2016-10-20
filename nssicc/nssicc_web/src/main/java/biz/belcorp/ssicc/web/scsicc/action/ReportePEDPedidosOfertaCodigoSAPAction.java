package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDPedidosOfertaCodigoSAPForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReportePEDPedidosOfertaCodigoSAPAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 4019351111130150606L;
	private boolean muestraOferta;
	private boolean muestraSAP;
	private List siccOfertaList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDPedidosOfertaCodigoSAPForm reporteForm = new ReportePEDPedidosOfertaCodigoSAPForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDPedidosOfertaCodigoSAPForm f = (ReportePEDPedidosOfertaCodigoSAPForm)this.formReporte;
		String nombreReporte = "";
		if(StringUtils.equalsIgnoreCase("XLS", this.formatoExportacion)){	
			nombreReporte = "reportePEDPedidosOfertaCodigoSAP"+f.getTipoReporte()+"XLS";
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
		ReportePEDPedidosOfertaCodigoSAPForm f=(ReportePEDPedidosOfertaCodigoSAPForm)this.formReporte;
		params.put("codigoPeriodo", f.getCodigoPeriodo());
        if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "OFER")){
        	params.put("codigoOferta", f.getCodigoOferta());
		}else{
			if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "CSAP")){
				params.put("codigoSap", f.getCodigoSAP());
			}
		}
        
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		this.muestraOferta = true;
		this.muestraSAP=false;
		ReportePEDPedidosOfertaCodigoSAPForm f=(ReportePEDPedidosOfertaCodigoSAPForm)this.formReporte;
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		this.siccOfertaList=reporteService.getTipoOfertas();
		
	}
	
	public void mostrarOpciones(ValueChangeEvent val){
		String valor = (String)val.getNewValue();
		if(log.isDebugEnabled()){
			log.debug("mostrarPeriodo");
		}
		if(StringUtils.equalsIgnoreCase(valor, "OFER")){
			this.muestraOferta = true;
			this.muestraSAP=false;
		}else{
			if(StringUtils.equalsIgnoreCase(valor, "CSAP")){
				this.muestraOferta = false;
				this.muestraSAP=true;
			}
		}
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		String mensaje = null;
		ReportePEDPedidosOfertaCodigoSAPForm f=(ReportePEDPedidosOfertaCodigoSAPForm)this.formReporte;
		if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "OFER")){
			if(StringUtils.isBlank(f.getCodigoOferta())){
				mensaje = "Debe ingresar Tipo Oferta";
			}
		}else{
			if(StringUtils.equalsIgnoreCase(f.getTipoReporte(), "CSAP")){
				if(StringUtils.isBlank(f.getCodigoSAP())){
					mensaje = "Debe ingresar Código SAP";
				}
			}
		}
		return mensaje;
	}

	/**
	 * @return the muestraOferta
	 */
	public boolean isMuestraOferta() {
		return muestraOferta;
	}

	/**
	 * @param muestraOferta the muestraOferta to set
	 */
	public void setMuestraOferta(boolean muestraOferta) {
		this.muestraOferta = muestraOferta;
	}

	/**
	 * @return the muestraSAP
	 */
	public boolean isMuestraSAP() {
		return muestraSAP;
	}

	/**
	 * @param muestraSAP the muestraSAP to set
	 */
	public void setMuestraSAP(boolean muestraSAP) {
		this.muestraSAP = muestraSAP;
	}

	/**
	 * @return the siccOfertaList
	 */
	public List getSiccOfertaList() {
		return siccOfertaList;
	}

	/**
	 * @param siccOfertaList the siccOfertaList to set
	 */
	public void setSiccOfertaList(List siccOfertaList) {
		this.siccOfertaList = siccOfertaList;
	}
}
