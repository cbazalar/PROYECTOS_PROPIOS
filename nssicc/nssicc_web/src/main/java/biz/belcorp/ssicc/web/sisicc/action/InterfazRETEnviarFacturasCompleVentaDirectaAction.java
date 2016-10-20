package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRETEnviarFacturasCompleVentaDirectaForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarAdministracionFlujosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazRETEnviarFacturasCompleVentaDirectaAction extends BaseInterfazAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8620021740739113629L;
	
	private List siccMarcaList; 
	private List siccCanalList; 
	private List siccAccesoList; 

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRETEnviarFacturasCompleVentaDirectaForm formInterfaz = new InterfazRETEnviarFacturasCompleVentaDirectaForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		InterfazRETEnviarFacturasCompleVentaDirectaForm f = (InterfazRETEnviarFacturasCompleVentaDirectaForm) this.formInterfaz;
		
		this.siccMarcaList = svc.getMarcas();		
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());		
		this.siccAccesoList = svc.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();        
        AjaxService ajaxService = (AjaxService) this.getBeanService("ajaxService");
        f.setCodigoPeriodo(periodoRequerido(Constants.CODIGO_MARCA_DEFAULT,	Constants.CODIGO_CANAL_DEFAULT));
    	MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");	       
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        f.setFechaFacturacion(controlFacturacion.getFechaProceso());
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));
	}
	
	public String periodoRequerido(String marca, String canal) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		dato = ajax.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), marca,
				canal);
		return dato;
	}
	
	public void loadPeriodoMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoMarca");
		}
		String marca = (String) val.getNewValue();
		InterfazRETEnviarFacturasCompleVentaDirectaForm f = (InterfazRETEnviarFacturasCompleVentaDirectaForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
	}

	public void loadPeriodoCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazRETEnviarFacturasCompleVentaDirectaForm f = (InterfazRETEnviarFacturasCompleVentaDirectaForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(f.getCodigoMarca(), canal));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		InterfazRETEnviarFacturasCompleVentaDirectaForm f = (InterfazRETEnviarFacturasCompleVentaDirectaForm) this.formInterfaz;
		
		if(f.getFechaFacturacionDate()!=null){
			f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		}
		
		params =  super.prepareParamsBeforeExecute(params, form);
		return params;
	}
		
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}
}
