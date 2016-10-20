package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPCuentaCorrientesCampanha2Form;

@ManagedBean
@SessionScoped
public class ReporteCuentaCorrienteCampanhaAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private Map hipCuentaCorrientesCanpanyaParamsReporte = new HashMap();
	private List listMovimientosCuentaCorriente;
	private List listaCabecera;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaHIPCuentaCorrientesCampanha2Form f = new ConsultaHIPCuentaCorrientesCampanha2Form();
		return f;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteHIPCuentaCorrientesCampanhaXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ConsultaHIPCuentaCorrientesCampanha2Form f = (ConsultaHIPCuentaCorrientesCampanha2Form) this.formReporte;
		f.setFormatoExportacion("XLS");
		params.put("formatoExportacion", "XLS");
		formatoReporte = f.getFormatoExportacion();
		this.formatoExportacion = "XLS";
		
		ConsultaCOBTelecobroService serviceCobranza = (ConsultaCOBTelecobroService)getBean("spusicc.consultaCOBTelecobroService");
		Map criteria2 = (Map) this.getHipCuentaCorrientesCanpanyaParamsReporte();
		criteria2.put("numeroCampanias", Constants.NUMERO_DIESIOCHO);
	
		List listaCabecera = this.listaCabecera;
		List listMovimientosCuentaCorriente = this.listMovimientosCuentaCorriente;
		for(int i=0; i<listaCabecera.size(); i++)
		{
			String campaniaActual = MapUtils.getString((Map)listaCabecera.get(i), "codigo", "");
			
			params.put(String.format("campaniaNumero%d", i), campaniaActual);
			
			for(int j=0; j<listMovimientosCuentaCorriente.size(); j++)
			{
				Map registroActual = (Map)listMovimientosCuentaCorriente.get(j);
				
				Double valorCampanya = MapUtils.getDouble(registroActual, campaniaActual) ;
				
				if(valorCampanya != null)
				{
					registroActual.put(String.format("campaniaNumero%d", i), valorCampanya.doubleValue());
				}
						
				log.debug(registroActual);
			}
		}
		
		params.put("codigoUsuario", this.getmPantallaPrincipalBean().getCodigoUsuario());
		
		params.put("listaReporte", listMovimientosCuentaCorriente);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteCuentaCorrienteCampanhaAction - setViewAtributes");
		}		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteCuentaCorrienteCampanhaAction - beforeExecuteReporte");
		}
		//Map paramsHIP = ((ConsultaHIPCuentaCorrientesCampanha2Form)this.formReporte).getParametrosReporteGeneral();
		
		log.debug("Los parametros del Reporte en el before son: "	+ params.toString());
		
		List listaReporte = (List)params.get("listaReporte");
		String codigoUsuario = MapUtils.getString(params, "codigoUsuario", "");
		ConsultaCOBTelecobroService serviceCobranza = (ConsultaCOBTelecobroService)getBean("spusicc.consultaCOBTelecobroService");
		serviceCobranza.insertReporteHIPCuentaCorrientesCampanha(listaReporte, codigoUsuario);
		this.getFormReporte().setParametrosReporteGeneral(params);
		this.setViewReporteMedia(true);
		this.setVisualizarReporte(true);
		
		
		return params;
	}
	
	
	
	
		
	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public Map getHipCuentaCorrientesCanpanyaParamsReporte() {
		return hipCuentaCorrientesCanpanyaParamsReporte;
	}

	public void setHipCuentaCorrientesCanpanyaParamsReporte(
			Map hipCuentaCorrientesCanpanyaParamsReporte) {
		this.hipCuentaCorrientesCanpanyaParamsReporte = hipCuentaCorrientesCanpanyaParamsReporte;
	}

	/**
	 * @return the listMovimientosCuentaCorriente
	 */
	public List getListMovimientosCuentaCorriente() {
		return listMovimientosCuentaCorriente;
	}

	/**
	 * @param listMovimientosCuentaCorriente the listMovimientosCuentaCorriente to set
	 */
	public void setListMovimientosCuentaCorriente(
			List listMovimientosCuentaCorriente) {
		this.listMovimientosCuentaCorriente = listMovimientosCuentaCorriente;
	}

	/**
	 * @return the listaCabecera
	 */
	public List getListaCabecera() {
		return listaCabecera;
	}

	/**
	 * @param listaCabecera the listaCabecera to set
	 */
	public void setListaCabecera(List listaCabecera) {
		this.listaCabecera = listaCabecera;
	}
	
	
	
}