package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCConsolidadoTransaccionesBancariasForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCConsolidadoTransaccionesBancariasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tipoVista;
	private List cccTiposOrigenLotesBancariosList;
	private List siccCuentaCorrienteList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'view' method");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
						
		//Map para almacenar los parametros
		Map criteria = new HashMap();
		
		criteria.put("codigoPais", pais.getCodigo());
				
		ConsultaCCCGenericoService serviceGenericoCCC = (ConsultaCCCGenericoService)getBean("spusicc.consultaCCCGenericoService");
				
		//Obtener los Estados de Los Lotes
		this.cccTiposOrigenLotesBancariosList = serviceGenericoCCC.getTiposLoteBancarioList();
							
		//Lista de Cuentas Corrientes para el Pas
		this.siccCuentaCorrienteList =serviceGenericoCCC.getCuentasCorrientesBancariasList(criteria);
		
		ReporteCCCConsolidadoTransaccionesBancariasForm form = (ReporteCCCConsolidadoTransaccionesBancariasForm) this.formReporte;

		form.setFechaPagoDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaPagoHastaD(new Date(System.currentTimeMillis()));
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	* Validar fechas de inicio y fin
	*/
	public String setValidarReporte() {
		ReporteCCCConsolidadoTransaccionesBancariasForm form = (ReporteCCCConsolidadoTransaccionesBancariasForm)this.formReporte;
		if (form.getFechaPagoHastaD().compareTo(form.getFechaPagoDesdeD()) < 0){
				String mensaje =  this.getResourceMessage("errors.compare.dates");
				return mensaje;
	    }
	

	   return null;
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCConsolidadoTransaccionesBancariasForm reporteCCCConsolidadoTransaccionesBancariasForm = (ReporteCCCConsolidadoTransaccionesBancariasForm) this.formReporte;
		
		String fecha1,fecha2;
		fecha1 = DateUtil.getDate(reporteCCCConsolidadoTransaccionesBancariasForm.getFechaPagoDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCConsolidadoTransaccionesBancariasForm.getFechaPagoHastaD());

		params.put("fechaPagoDesde", fecha1);
		params.put("fechaPagoHasta", fecha2);
		
		this.tipoVista = reporteCCCConsolidadoTransaccionesBancariasForm.getTipoVista();	
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCCCConsolidadoTransaccionesBancarias" + this.tipoVista + "XLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCConsolidadoTransaccionesBancarias" + this.tipoVista + "XLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCConsolidadoTransaccionesBancariasForm form = new ReporteCCCConsolidadoTransaccionesBancariasForm();
		return form;
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the cccTiposOrigenLotesBancariosList
	 */
	public List getCccTiposOrigenLotesBancariosList() {
		return cccTiposOrigenLotesBancariosList;
	}

	/**
	 * @param cccTiposOrigenLotesBancariosList the cccTiposOrigenLotesBancariosList to set
	 */
	public void setCccTiposOrigenLotesBancariosList(
			List cccTiposOrigenLotesBancariosList) {
		this.cccTiposOrigenLotesBancariosList = cccTiposOrigenLotesBancariosList;
	}

	/**
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}
}