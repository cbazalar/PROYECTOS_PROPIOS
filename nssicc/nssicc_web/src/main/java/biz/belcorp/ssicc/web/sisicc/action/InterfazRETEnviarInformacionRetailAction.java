package biz.belcorp.ssicc.web.sisicc.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazDATService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRETEnviarInformacionRetailForm;

@ManagedBean
@SessionScoped
public class InterfazRETEnviarInformacionRetailAction   extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6244560699785133280L;

	private List siccMarcaList; 
	private List siccCanalList; 
	private List siccAccesoList; 
	private List siccAccesoListTodos;
	private List siccSubaccesoList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRETEnviarInformacionRetailForm formInterfaz = new InterfazRETEnviarInformacionRetailForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazRETEnviarInformacionRetailForm f = (InterfazRETEnviarInformacionRetailForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList = svc.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoListTodos = svc.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccSubaccesoList = svc.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));

		MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = null;

		Map criteriaPeriodo = new HashMap();

		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.DAT_PARAM_IND_CAMP_ACT_UNO);

		controlFacturacion = serviceCF.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaFacturacionDate(sdf.parse(f.getFechaFacturacion()));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception
	{
		InterfazRETEnviarInformacionRetailForm f = (InterfazRETEnviarInformacionRetailForm) this.formInterfaz;
		if(f.getFechaFacturacionDate() != null)
		{
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");	
			f.setFechaFacturacion(df.format(f.getFechaFacturacionDate()));
		}
		params = super.prepareParamsBeforeExecute(params, form);
    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		params.put("codigoUsuario", mPantallaPrincipalBean.getCurrentUser().getLogin());
		//Agregando tipoProceso = 'I'
		params.put("tipoProceso", "I");
				
		return params;	
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	log.debug("Entering 'before' method");
    	InterfazDATService svc = (InterfazDATService) getBean("sisicc.interfazDATService");
    	
    	svc.executeCargarTablaInterfaz(params);
    	
    }
	
	public String setValidarInterfaz()
	{
		InterfazRETEnviarInformacionRetailForm f = (InterfazRETEnviarInformacionRetailForm) this.formInterfaz;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String periodo = f.getCodigoPeriodo();
		String mensaje = null;
		
		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,  periodo);
		
		try {
			if(f.getFechaFacturacionDate().after(DateUtil.convertStringToDate(fechaHasta)) ||
					f.getFechaFacturacionDate().before(DateUtil.convertStringToDate(fechaDesde)))
			{
				mensaje = this
						.getResourceMessage("errors.compare.campaigns.periodo.fechaFacturacion")
						+ " (" + fechaDesde + " - " + fechaHasta + ")";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mensaje;		
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

	public void setSiccSubaccesoList(List siccSubaccesoList) {
		this.siccSubaccesoList = siccSubaccesoList;
	}
	
	public List getSiccSubaccesoList() {
		return siccSubaccesoList;
	}

	public void setSiccAccesoListTodos(List siccAccesoListTodos) {
		this.siccAccesoListTodos = siccAccesoListTodos;
	}
}
