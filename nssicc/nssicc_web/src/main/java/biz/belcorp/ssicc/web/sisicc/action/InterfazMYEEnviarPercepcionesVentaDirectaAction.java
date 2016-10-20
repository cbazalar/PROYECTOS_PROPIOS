package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarPercepcionesVentaDirectaForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarPercepcionesVentaDirectaAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazMYEEnviarPercepcionesVentaDirectaAction extends BaseInterfazAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7734285685522348696L;

	private List siccPeriodoList;
		
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazMYEEnviarPercepcionesVentaDirectaForm formInterfaz = new InterfazMYEEnviarPercepcionesVentaDirectaForm(); 
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{	
		InterfazMYEEnviarPercepcionesVentaDirectaForm f = (InterfazMYEEnviarPercepcionesVentaDirectaForm) this.formInterfaz;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		/*Interfaz interfaz = (Interfaz) this.mPantallaPrincipalBean.getRequest().getAttribute(Constants.INTERFAZ_DATA);
		Map criteria = new HashMap();
		for (int j = 0; j < interfaz.getParametros().size(); j++) {
			ParametroInterfaz parametroInterfaz = (ParametroInterfaz) interfaz.getParametros().get(j);
			criteria.put(parametroInterfaz.getNombre(), parametroInterfaz.getValor());
		}

		this.siccPeriodoList = svc.getPeriodosByPMC(criteria);	*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		
		f.setFechaDesde(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaHasta(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaDesdeDate(new Date(System.currentTimeMillis()));
		f.setFechaHastaDate(new Date(System.currentTimeMillis()));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		InterfazMYEEnviarPercepcionesVentaDirectaForm f = (InterfazMYEEnviarPercepcionesVentaDirectaForm) this.formInterfaz;
		params =  super.prepareParamsBeforeExecute(params, form);
	
		if(f.getFechaHastaDate()!=null && f.getFechaDesdeDate()!=null){
			f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeDate()));
			f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaDate()));
        }
		
		return params;
	}
	
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}
}
