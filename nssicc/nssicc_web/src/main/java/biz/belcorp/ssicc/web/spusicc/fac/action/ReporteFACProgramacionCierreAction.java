package biz.belcorp.ssicc.web.spusicc.fac.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.fac.form.ReporteFACProgramacionCierreForm;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */

@ManagedBean
@SessionScoped
public class ReporteFACProgramacionCierreAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteFACProgramacionCierreForm f = new ReporteFACProgramacionCierreForm();
		
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteFACProgramacionCierreForm f = (ReporteFACProgramacionCierreForm) formReporte;
		
		if ("VXLS".equals(f.getFormatoExportacion()) ||("XLS".equals(f.getFormatoExportacion())))
			return "reporteFACProgramacionCierreXLS";
		else
			return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteFACProgramacionCierreForm f = (ReporteFACProgramacionCierreForm) formReporte;
		
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		if(f.getTipoCierre() != null && f.getTipoCierre().trim().length() > 0){
			params.put("tipoCierre", "AND X.TIP_CIER like '%" + f.getTipoCierre().trim() + "%'");
		}else{
			params.put("tipoCierre", "");
		}
		
		if(f.getEstado() != null && f.getEstado().trim().length() > 0){
			params.put("estado", "AND X.EST_CIER like '%" + f.getEstado().trim() + "%'");
		}else{
			params.put("estado", "");
		}
		
		params.put("fechaFacDesde", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaFacDesde()));
		params.put("fechaFacHasta", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaFacHasta()));
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		
		ReporteFACProgramacionCierreForm f = (ReporteFACProgramacionCierreForm) formReporte;
		MantenimientoOCRPedidoControlFacturacionService servicePCF = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha", Constants.NUMERO_CERO);
        criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO);
     	criteria.put("estado", Constants.ESTADO_ACTIVO);
		
		PedidoControlFacturacion pcf = servicePCF.getControlFacturacionById(criteria);
		
		f.setFechaFacDesde(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
		f.setFechaFacHasta(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
		f.setCodigoPeriodoInicio(pcf.getCodigoPeriodo());
		f.setCodigoPeriodoFin(pcf.getCodigoPeriodo());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		ReporteFACProgramacionCierreForm f = (ReporteFACProgramacionCierreForm) formReporte;
		
		Date fechaFacDesde = f.getFechaFacDesde();
		Date fechaFacHasta = f.getFechaFacHasta();
		int codigoPeriodoInicio = Integer.parseInt(f.getCodigoPeriodoInicio());
		int codigoPeriodoFin = Integer.parseInt(f.getCodigoPeriodoFin());
		
		if(fechaFacDesde != null && fechaFacHasta != null){
			if(fechaFacDesde.compareTo(fechaFacHasta) > 0){
				return "'Fecha Inicio' debe ser menor o igual a 'Fecha Fin'";
			}
		}
		
		if(f.getCodigoPeriodoInicio() != null && f.getCodigoPeriodoFin() != null){
			if(codigoPeriodoFin < codigoPeriodoInicio){
				return "'Campaña Inicio' debe ser menor o igual a 'Campaña Fin'";
			}
		}
		
		return null;
	}
}