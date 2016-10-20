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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazREUEnviarDocumentosAnuladosForm;

@ManagedBean
@SessionScoped
public class InterfazREUEnviarDocumentosAnuladosAction extends BaseInterfazAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2384571265536469961L;

	private List siccMarcaList;
	private List siccCanalList; 
	private List siccAccesoList;
	private LabelValue[] siccPeriodoList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception
	{
		InterfazREUEnviarDocumentosAnuladosForm formInterfaz = new InterfazREUEnviarDocumentosAnuladosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazREUEnviarDocumentosAnuladosForm f = (InterfazREUEnviarDocumentosAnuladosForm) this.formInterfaz;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de los combos Marca y Canales
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList = interfazSiCCService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		this.siccPeriodoList = aSvc.getPeriodosDocumentosAnulados(f.getCodigoPais(), f.getCodigoMarca(), f.getCodigoCanal());
		
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	        f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	        f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
	        f.setCodigoPeriodo(periodoRequerido(Constants.CODIGO_MARCA_DEFAULT,	Constants.CODIGO_CANAL_DEFAULT));
	        f.setFechaFacturacion(sdf1.format(new Date(System.currentTimeMillis())));

	        Calendar fec = new GregorianCalendar();
	        fec.add(Calendar.DATE, -0);
	        Date fechaActual = fec.getTime();
	        f.setFechaFacturacion(sdf1.format(fechaActual));
	        
	        f.setFechaFacturacionDate(sdf1.parse(f.getFechaFacturacion()));

	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params =  super.prepareParamsBeforeExecute(params, form);
		InterfazREUEnviarDocumentosAnuladosForm f = (InterfazREUEnviarDocumentosAnuladosForm) this.formInterfaz;
		
		if(f.getFechaFacturacionDate()!=null){
			f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		}
		params =  super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
	public String periodoRequerido(String marca, String canal) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		dato = ajax.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), marca, canal);
		return dato;
	}
	
	public void loadPeriodoMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoMarca");
		}
		String marca = (String) val.getNewValue();
		InterfazREUEnviarDocumentosAnuladosForm f = (InterfazREUEnviarDocumentosAnuladosForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
	}

	public void loadPeriodoCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoCanal");
		}
		String canal = (String) val.getNewValue();
		InterfazREUEnviarDocumentosAnuladosForm f = (InterfazREUEnviarDocumentosAnuladosForm) this.formInterfaz;
		f.setCodigoPeriodo(periodoRequerido(f.getCodigoMarca(), canal));
	}
	
	public String setValidarInterfaz()
	{
		InterfazREUEnviarDocumentosAnuladosForm f = (InterfazREUEnviarDocumentosAnuladosForm) this.formInterfaz;
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
						.getResourceMessage("interfazREUEnviarDocumentosAnuladosForm.error.rango.fechaFacturacion")
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

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}
}
