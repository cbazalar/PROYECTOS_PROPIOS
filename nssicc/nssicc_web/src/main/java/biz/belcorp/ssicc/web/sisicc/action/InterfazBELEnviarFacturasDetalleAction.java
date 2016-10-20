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
import biz.belcorp.ssicc.web.sisicc.form.InterfazBELEnviarFacturasDetalleForm;

@ManagedBean
@SessionScoped
public class InterfazBELEnviarFacturasDetalleAction  extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 145249459163846416L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoTodosList;
	private LabelValue[] siccPeriodoList={};	

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazBELEnviarFacturasDetalleForm form=new InterfazBELEnviarFacturasDetalleForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazBELEnviarFacturasDetalleForm form=(InterfazBELEnviarFacturasDetalleForm) this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		form.setCodigoPais(pais.getCodigo());
		// fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar fec = new GregorianCalendar();
		fec.add(Calendar.DATE, -0);
		Date fechaActual = fec.getTime();
		form.setFechaFacturacion( sdf.format(fechaActual));
		form.setFechaFacturacionD(fechaActual);
		
		// Carga de los combos Marca, Canal, Acceso y Periodo
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList=svc.getMarcas();
	    this.siccCanalList= svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
	    this.siccAccesoList=svc.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
	    
	    //------------Lista Acceso--------
	    this.siccAccesoTodosList=getAccesoList(Constants.CODIGO_CANAL_DEFAULT);
	  //------------Cargar Periodo--------
	    AjaxService ajaxService=(AjaxService) getBean("ajaxService");
	    this.siccPeriodoList=ajaxService.getPeriodosFacturasCabecera(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, Constants.CODIGO_ACCESO_DEFAULT);
	    form.setCodigoPeriodo(ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, Constants.CODIGO_ACCESO_DEFAULT));
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute (Map params,BaseForm form)
		throws Exception{
			
			InterfazBELEnviarFacturasDetalleForm form1=(InterfazBELEnviarFacturasDetalleForm) this.formInterfaz;
			form1.setFechaFacturacion(DateUtil.convertDateToString(form1.getFechaFacturacionD()));
			params= super.prepareParamsBeforeExecute(params, form);
			return params;		
		}
	
	public void loadPeriodosMarca(ValueChangeEvent val) {
		String marca=(String) val.getNewValue();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazBELEnviarFacturasDetalleForm form=(InterfazBELEnviarFacturasDetalleForm) this.formInterfaz;
		String codigoPeriodo=ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( form.getCodigoPais(), marca,form.getCodigoCanal(),form.getCodigoAcceso());
		form.setCodigoPeriodo(codigoPeriodo);
	}
	
	public void loadPeriodosAcceso(ValueChangeEvent val) {
		String acceso=(String) val.getNewValue();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazBELEnviarFacturasDetalleForm form=(InterfazBELEnviarFacturasDetalleForm) this.formInterfaz;
		String codigoPeriodo=ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( form.getCodigoPais(), form.getCodigoMarca(),form.getCodigoCanal(),acceso);
		form.setCodigoPeriodo(codigoPeriodo);
	}
	
	public void loadPeriodosCanal(ValueChangeEvent val) {
		String canal=(String) val.getNewValue();
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazBELEnviarFacturasDetalleForm form=(InterfazBELEnviarFacturasDetalleForm) this.formInterfaz;
		String codigoPeriodo=ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso( form.getCodigoPais(), form.getCodigoMarca(),canal,form.getCodigoAcceso());
		form.setCodigoPeriodo(codigoPeriodo);
	}

	public void loadAcceso(ValueChangeEvent val)
	{
		if (log.isDebugEnabled()) {
			log.debug("loadAcceso");
		}
		InterfazBELEnviarFacturasDetalleForm form=(InterfazBELEnviarFacturasDetalleForm) this.formInterfaz;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String valor = (String) val.getNewValue();
		this.siccAccesoTodosList = getAccesoList(valor);
		form.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		log.debug("qqqqqqqqqqqqqqqqqqqqqqqq"+form.getCodigoAcceso());
		loadPeriodosCanal(val);
		
	}
	
	public String setValidarInterfaz () {
		AjaxService ajaxService=(AjaxService) getBean("ajaxService");
		InterfazBELEnviarFacturasDetalleForm form=(InterfazBELEnviarFacturasDetalleForm) this.formInterfaz;
		String [] parametrosFecha =ajaxService.getIntervalosFechaFacturasCabecera( form.getCodigoPeriodo(),form.getCodigoPais(),form.getCodigoMarca(),form.getCodigoCanal(), form.getCodigoAcceso());

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String mensaje="";
		Date fechaI ;
		Date fechaF;
		try {
			if(parametrosFecha!=null){
				fechaI= DateUtil.convertStringToDate(parametrosFecha[0]);
				fechaF=DateUtil.convertStringToDate(parametrosFecha[1]);
				
				if(form.getFechaFacturacionD().before(fechaI) || form.getFechaFacturacionD().after(fechaF))
				{
					mensaje = this.getResourceMessage("interfazBELEnviarFacturasDetalleForm.error.rango.fechaFacturacion")+df.format(fechaI)+" - "+df.format(fechaF);
					
				}
			}
			else
			{
				mensaje = this.getResourceMessage("interfazBELEnviarFacturasDetalleForm.error.rango.fechaFacturacion.existencia");
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

	public List getSiccAccesoTodosList() {
		return siccAccesoTodosList;
	}

	public void setSiccAccesoTodosList(List siccAccesoTodosList) {
		this.siccAccesoTodosList = siccAccesoTodosList;
	}

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}
	
	

}






