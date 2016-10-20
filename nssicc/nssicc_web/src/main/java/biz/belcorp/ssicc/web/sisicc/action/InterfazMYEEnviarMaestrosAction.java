package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarMaestrosForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarMaestrosAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazMYEEnviarMaestrosAction extends BaseInterfazAbstractAction
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8457083442853952668L;


	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoListTodos;
	private List siccSubaccesoList;
	private List siccTipoClienteList;
	
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		InterfazMYEEnviarMaestrosForm interfazForm = new InterfazMYEEnviarMaestrosForm(); 
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazMYEEnviarMaestrosForm f = (InterfazMYEEnviarMaestrosForm) this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
        InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList =  getAccesoList(Constants.CODIGO_CANAL_DEFAULT);
		
		this.siccSubaccesoList = getSubAccesoList(Constants.CODIGO_ACCESO_DEFAULT);
		this.siccTipoClienteList = svc.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
					
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_DEFAULT);
	
		f.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		
		f.setFechaFacturacion((sdf.format(new Date(System.currentTimeMillis()))));
		f.setFechaFacturacionDate(new Date(System.currentTimeMillis()));
		sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		if (StringUtils.isEmpty(f.getCodigoPeriodo())) f.setCodigoPeriodo(periodo);
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params =  super.prepareParamsBeforeExecute(params, form);
		InterfazMYEEnviarMaestrosForm f = (InterfazMYEEnviarMaestrosForm) this.formInterfaz;
		
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
	            if (historicos.size() == 1)
	                params.put("fechaInicioUltimoProceso", ((Historico) historicos.get(0)).getFechaInicioProceso());
	        } else {
	            params.put("fechaInicioUltimoProceso", null);
	        }
		if(f.getFechaFacturacionDate()!=null){
			params.put("fechaFacturacion", DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		}
		return params;
	}
	
	public void loadAcceso(ValueChangeEvent value)
	{	
		String valor = (String) value.getNewValue();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		InterfazMYEEnviarMaestrosForm f = (InterfazMYEEnviarMaestrosForm) this.formInterfaz;
		
		if (valor != null)
		{
			this.siccAccesoList = getAccesoList(valor);	
			if(valor.equalsIgnoreCase(Constants.CODIGO_CANAL_DEFAULT)) f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
			
		}else
		{
			this.siccAccesoList = svc.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma()
					.getCodigoISO());
			f.setCodigoAcceso(null);
		}		
	}
	
	public void loadSubAcceso(ValueChangeEvent value)
	{	
		String valor = (String) value.getNewValue();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		InterfazMYEEnviarMaestrosForm f = (InterfazMYEEnviarMaestrosForm) this.formInterfaz;
		
		if(valor != null)
		{
			this.siccSubaccesoList = getSubAccesoList(valor);
		}else
		{
			this.siccSubaccesoList = svc.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
			f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_DEFAULT);
		}
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
	
	public List getSiccAccesoListTodos() {
		return siccAccesoListTodos;
	}
	
	public void setSiccAccesoListTodos(List siccAccesoListTodos) {
		this.siccAccesoListTodos = siccAccesoListTodos;
	}
	
	public List getSiccSubaccesoList() {
		return siccSubaccesoList;
	}
	
	public void setSiccSubaccesoList(List siccSubaccesoList) {
		this.siccSubaccesoList = siccSubaccesoList;
	}
	
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}
	
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}
}
