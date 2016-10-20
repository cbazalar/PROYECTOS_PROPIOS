package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePEDPedidosReservaPerdidaProlForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReportePEDPedidosReservaPerdidaProlAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = 8010246867847494766L;
	
	private String oidPais;
	private List siccSociedadList;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDPedidosReservaPerdidaProlForm reporteForm = new ReportePEDPedidosReservaPerdidaProlForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePEDPedidosReservaPerdidaProlForm f = (ReportePEDPedidosReservaPerdidaProlForm)this.formReporte;
		if(StringUtils.equals("XLS", this.formatoExportacion)){
			if(StringUtils.equals(f.getCodTipoReporte(), "ACTU"))
				return "reportePEDPedidosReservaPerdidaProlFormActualXLS";
			else
				return "reportePEDPedidosReservaPerdidaProlFormHistoricoXLS";				
		}			
		else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePEDPedidosReservaPerdidaProlForm f = (ReportePEDPedidosReservaPerdidaProlForm)this.formReporte;
		params.put("codigoSociedad", f.getCodigoSociedad());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("oidPais", this.oidPais);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		
		ReportePEDPedidosReservaPerdidaProlForm f=(ReportePEDPedidosReservaPerdidaProlForm)this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		this.siccSociedadList=service.getSociedadesByCodigoPais(pais.getCodigo());
		Base base = (Base) this.siccSociedadList.get(0);
		f.setCodigoSociedad(base.getCodigo());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));		
		f.setCodigoPeriodo(periodo);	
		this.oidPais=Long.toString(pais.getOidPais());
		
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
	
	

}
