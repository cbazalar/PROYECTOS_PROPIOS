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
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLLIEnviarVentaRealDiariaAcumuladaForm;


@ManagedBean
@SessionScoped
public class InterfazLLIEnviarVentaRealDiariaAcumuladaAction  extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4103884333186537607L;
	
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLLIEnviarVentaRealDiariaAcumuladaForm formInterfaz = new  InterfazLLIEnviarVentaRealDiariaAcumuladaForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazLLIEnviarVentaRealDiariaAcumuladaForm f = (InterfazLLIEnviarVentaRealDiariaAcumuladaForm) this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

		if (f.getCodigoCanal() == null || f.getCodigoCanal().length() == 0) 
		{
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = new GregorianCalendar();

			f.setCodigoPeriodo(periodoRequerido(Constants.CODIGO_MARCA_DEFAULT,	Constants.CODIGO_CANAL_DEFAULT));
			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);

			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -1);
			Date today = calendar.getTime();

			f.setFechaFacturacionInicial(ajax.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, f.getCodigoPeriodo()));
			f.setFechaFacturacionInicialDate(df.parse(f.getFechaFacturacionInicial()));
			f.setFechaFacturacionFinal(df.format(today));
			f.setFechaFacturacionFinalDate(today);
		}
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		InterfazLLIEnviarVentaRealDiariaAcumuladaForm f = (InterfazLLIEnviarVentaRealDiariaAcumuladaForm) this.formInterfaz;
		if(f.getFechaFacturacionFinalDate()!=null && f.getFechaFacturacionInicialDate()!=null){
			f.setFechaFacturacionInicial(DateUtil.convertDateToString(f.getFechaFacturacionInicialDate()));
			f.setFechaFacturacionFinal(DateUtil.convertDateToString(f.getFechaFacturacionFinalDate()));
		} 
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	public String periodoRequerido(String marca, String canal) 
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		dato = ajax.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), marca, canal);
		return dato;
	}
	
	public String loadFechaInicial(String marca, String canal, String periodo)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		
		dato = ajax.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), marca, canal, periodo);	
		return dato;
	}
	
	public String loadFechaFinal(String marca, String canal, String periodo)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String dato = "";
		
		dato = ajax.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), marca, canal, periodo);
		
		return dato;
	}
	
	public void loadPeriodoMarca(ValueChangeEvent val) 
	{	
		String marca = (String) val.getNewValue();
		InterfazLLIEnviarVentaRealDiariaAcumuladaForm f = (InterfazLLIEnviarVentaRealDiariaAcumuladaForm) this.formInterfaz;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
		f.setFechaFacturacionInicial(loadFechaInicial(marca, f.getCodigoCanal(), f.getCodigoPeriodo()));
		f.setFechaFacturacionFinal(loadFechaFinal(marca, f.getCodigoCanal(), f.getCodigoPeriodo()));		
		
		if (f.getFechaFacturacionInicial() != null && f.getFechaFacturacionInicial() != "")
			try {
				f.setFechaFacturacionInicialDate(df.parse(f.getFechaFacturacionInicial()));
			} catch (ParseException e) { }
		else
			f.setFechaFacturacionInicialDate(null);
		
		if(f.getFechaFacturacionFinal()!= null && f.getFechaFacturacionFinal() != "")
			try {
				f.setFechaFacturacionFinalDate(df.parse(f.getFechaFacturacionFinal()));
			} catch (ParseException e) { }
		else
			f.setFechaFacturacionFinalDate(null);		
	}
	
	public void loadPeriodoCanal(ValueChangeEvent val)
	{		
		String canal = (String) val.getNewValue();
		InterfazLLIEnviarVentaRealDiariaAcumuladaForm f = (InterfazLLIEnviarVentaRealDiariaAcumuladaForm) this.formInterfaz;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		f.setCodigoPeriodo(periodoRequerido(f.getCodigoMarca(), canal));
		f.setFechaFacturacionInicial(loadFechaInicial(f.getCodigoMarca(), canal, f.getCodigoPeriodo()));
		f.setFechaFacturacionFinal(loadFechaFinal(f.getCodigoMarca(), canal, f.getCodigoPeriodo()));
			
		if (f.getFechaFacturacionInicial() != null && f.getFechaFacturacionInicial() != "")
			try {
				f.setFechaFacturacionInicialDate(df.parse(f.getFechaFacturacionInicial()));
			} catch (ParseException e) { }
		else
			f.setFechaFacturacionInicialDate(null);
		
		if(f.getFechaFacturacionFinal()!= null && f.getFechaFacturacionFinal() != "")
			try {
				f.setFechaFacturacionFinalDate(df.parse(f.getFechaFacturacionFinal()));
			} catch (ParseException e) { }
		else
			f.setFechaFacturacionFinalDate(null);		
	}
	
	public void loadFechasPeriodo(String valor) 
	{
		InterfazLLIEnviarVentaRealDiariaAcumuladaForm f = (InterfazLLIEnviarVentaRealDiariaAcumuladaForm) this.formInterfaz;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (valor.length() > 0) {
			f.setFechaFacturacionInicial(loadFechaInicial(f.getCodigoMarca(), f.getCodigoCanal(), valor));
			if (f.getFechaFacturacionInicial() != null && f.getFechaFacturacionInicial() != "")
				try {
					f.setFechaFacturacionInicialDate(df.parse(f.getFechaFacturacionInicial()));
				} catch (ParseException e) { }
			else
				f.setFechaFacturacionInicialDate(null);
			
			f.setFechaFacturacionFinal(loadFechaFinal(f.getCodigoMarca(), f.getCodigoCanal(), valor));
			if(f.getFechaFacturacionFinal()!= null && f.getFechaFacturacionFinal() != "")
				try {
					f.setFechaFacturacionFinalDate(df.parse(f.getFechaFacturacionFinal()));
				} catch (ParseException e) { }
			else
				f.setFechaFacturacionFinalDate(null);
		}
	}
	
	public String setValidarInterfaz() 
	{
		InterfazLLIEnviarVentaRealDiariaAcumuladaForm f = (InterfazLLIEnviarVentaRealDiariaAcumuladaForm) this.formInterfaz;
		String mensaje = null;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String periodo = f.getCodigoPeriodo();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);

			try {
				if(f.getFechaFacturacionFinalDate().after(DateUtil.convertStringToDate(fechaHasta)) ||
						f.getFechaFacturacionFinalDate().before(DateUtil.convertStringToDate(fechaDesde)))
				{
					mensaje = this
							.getResourceMessage("interfazLLIEnviarVentaRealDiariaAcumuladaForm.error.rango.fechaFacturacionFinal")
							+ " (" + fechaDesde + " - " + fechaHasta + ")";
				}else
					if(f.getFechaFacturacionInicialDate().after(DateUtil.convertStringToDate(fechaHasta)) ||
							f.getFechaFacturacionInicialDate().before(DateUtil.convertStringToDate(fechaDesde)))
					{
						mensaje = this
								.getResourceMessage("interfazLLIEnviarVentaRealDiariaAcumuladaForm.error.rango.fechaFacturacionInicial")
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
}
