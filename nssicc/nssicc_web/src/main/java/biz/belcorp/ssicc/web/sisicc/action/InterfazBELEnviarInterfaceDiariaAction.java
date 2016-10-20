package biz.belcorp.ssicc.web.sisicc.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazBELEnviarInterfaceDiariaForm;

@ManagedBean
@SessionScoped
public class InterfazBELEnviarInterfaceDiariaAction extends BaseInterfazAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4693973769119289644L;

	private List siccMarcaList; 
	private List siccCanalList; 
	private List siccAccesoList;
	private List siccTipoClienteList;
	private LabelValue[] siccPeriodoList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		InterfazBELEnviarInterfaceDiariaForm formInterfaz = new InterfazBELEnviarInterfaceDiariaForm(); 
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		// Carga de los combos Marca, Canal, Acceso y Periodo
		InterfazBELEnviarInterfaceDiariaForm f = (InterfazBELEnviarInterfaceDiariaForm)this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());		
		this.siccAccesoList =  getAccesoList(Constants.CODIGO_CANAL_DEFAULT); 
		this.siccTipoClienteList = svc.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());

		/* METODO RESET */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
        f.setFechaFacturacion(sdf.format(new Date(System.currentTimeMillis())));
        
        f.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);        

        Calendar fec = new GregorianCalendar();
        fec.add(Calendar.DATE, -0);
        Date fechaActual = fec.getTime();

        f.setFechaFacturacion(sdf.format(fechaActual));
        f.setFechaFacturacionDate(sdf.parse(f.getFechaFacturacion()));
		/**/
        
        /* METODO RELOAD */
        AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccPeriodoList = aSvc.getPeriodosFacturasCabecera(f.getCodigoPais(), f.getCodigoMarca(), 
				f.getCodigoCanal(), f.getCodigoAcceso());
        /**/
		
		f.setCodigoPeriodo(ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, Constants.CODIGO_ACCESO_DEFAULT));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		InterfazBELEnviarInterfaceDiariaForm f = (InterfazBELEnviarInterfaceDiariaForm)this.formInterfaz;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(f.getFechaFacturacionDate() != null)
		{
			f.setFechaFacturacion(sdf.format(f.getFechaFacturacionDate()));
		}
		params = super.prepareParamsBeforeExecute(params, form);
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
	            if (historicos.size() == 1)
	                params.put("fechaInicioUltimoProceso", ((Historico) historicos.get(0)).getFechaInicioProceso());
	        } else {
	            params.put("fechaInicioUltimoProceso", null);
	        }
		
		return params;
	}
	
	public void loadPeriodosMarca(ValueChangeEvent val) {
		String marca=(String) val.getNewValue();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazBELEnviarInterfaceDiariaForm f=(InterfazBELEnviarInterfaceDiariaForm) this.formInterfaz;
		
		String codigoPeriodo=ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( f.getCodigoPais(), marca,f.getCodigoCanal(),f.getCodigoAcceso());
		f.setCodigoPeriodo(codigoPeriodo);
	}
	
	public void loadPeriodosAcceso(ValueChangeEvent val) {
		String acceso=(String) val.getNewValue();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazBELEnviarInterfaceDiariaForm f=(InterfazBELEnviarInterfaceDiariaForm) this.formInterfaz;
		
		String codigoPeriodo=ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( f.getCodigoPais(), f.getCodigoMarca(),f.getCodigoCanal(),acceso);
		f.setCodigoPeriodo(codigoPeriodo);
	}
	
	public void loadPeriodosCanal(ValueChangeEvent val) {
		String canal=(String) val.getNewValue();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazBELEnviarInterfaceDiariaForm f=(InterfazBELEnviarInterfaceDiariaForm) this.formInterfaz;
		
		String codigoPeriodo=ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( f.getCodigoPais(), f.getCodigoMarca(),canal,f.getCodigoAcceso());
		f.setCodigoPeriodo(codigoPeriodo);
	}

	public void loadAcceso(ValueChangeEvent val)
	{
		InterfazBELEnviarInterfaceDiariaForm form=(InterfazBELEnviarInterfaceDiariaForm) this.formInterfaz;
		String valor = (String) val.getNewValue();
		this.siccAccesoList = getAccesoList(valor);
		if(valor.equalsIgnoreCase(Constants.CODIGO_CANAL_DEFAULT)) 
			form.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
				
		loadPeriodosCanal(val);
	}
	
	public String setValidarInterfaz() 
	{
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		InterfazBELEnviarInterfaceDiariaForm form = (InterfazBELEnviarInterfaceDiariaForm) this.formInterfaz;
		String[] parametrosFecha = ajaxService.getIntervalosFechaFacturasCabecera(form.getCodigoPeriodo(),
						form.getCodigoPais(), form.getCodigoMarca(), form.getCodigoCanal(), form.getCodigoAcceso());

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String mensaje = null;
		Date fechaI;
		Date fechaF;
		try {
			if (parametrosFecha != null) 
			{
				fechaI = DateUtil.convertStringToDate(parametrosFecha[0]);
				fechaF = DateUtil.convertStringToDate(parametrosFecha[1]);

				if (form.getFechaFacturacionDate().before(fechaI)
						|| form.getFechaFacturacionDate().after(fechaF)) 
				{
					mensaje = this.getResourceMessage("interfazBELEnviarInterfaceDiariaForm.error.rango.fechaFacturacion")
							+ df.format(fechaI) + " - " + df.format(fechaF);

				}
			}else
				mensaje = this.getResourceMessage("interfazBELEnviarInterfaceDiariaForm.error.rango.fechaFacturacion");

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

	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

}
