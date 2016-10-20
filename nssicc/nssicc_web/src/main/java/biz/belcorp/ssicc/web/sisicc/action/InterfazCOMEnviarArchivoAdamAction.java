package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOMEnviarArchivoAdamForm;

@ManagedBean
@SessionScoped
public class InterfazCOMEnviarArchivoAdamAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889626624552926022L;
	
	private List siccComisionList;
	
	private Boolean cambioFiltroPeriodo;
	private Boolean cambioFiltroFecha;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOMEnviarArchivoAdamForm form=new InterfazCOMEnviarArchivoAdamForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOMEnviarArchivoAdamForm form= (InterfazCOMEnviarArchivoAdamForm) this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccComisionList=service.getComision();
		form.setIndicador("PERIODO");
		this.cambioFiltroFecha=false;
		this.cambioFiltroPeriodo=true;
	}
	
	@Override
	protected Map<String, Object>prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{
		InterfazCOMEnviarArchivoAdamForm form1= (InterfazCOMEnviarArchivoAdamForm) this.formInterfaz;
		if(cambioFiltroFecha==true)
		{
			form1.setFechaProcesoInicio(DateUtil.convertDateToString(form1.getFechaProcesoInicioD()));
			form1.setFechaProcesoFin(DateUtil.convertDateToString(form1.getFechaProcesoFinD()));
		}
		
		
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
	public void loadFiltro(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadFiltro");
		}
		InterfazCOMEnviarArchivoAdamForm form = (InterfazCOMEnviarArchivoAdamForm) this.formInterfaz;
		String valor = (String) val.getNewValue();
		if (valor.equals("PERIODO")) {
			this.cambioFiltroPeriodo = true;
			this.cambioFiltroFecha = false;
			form.setCodigoPeriodoProceso1(null);
			form.setCodigoPeriodoProceso2(null);
		} else if (valor.equals("FECHAS")) {
			this.cambioFiltroPeriodo = false;
			this.cambioFiltroFecha = true;
			form.setFechaProcesoInicioD(null);
			form.setFechaProcesoFinD(null);
		}
	}
	
	public String setValidarInterfaz() {
		InterfazCOMEnviarArchivoAdamForm form = (InterfazCOMEnviarArchivoAdamForm) this.formInterfaz;
		if (cambioFiltroPeriodo == true) {
			if (form.getCodigoPeriodoProceso1().length()>0  && form.getCodigoPeriodoProceso2().length()>0) {
				int codperini = Integer.parseInt(form.getCodigoPeriodoProceso1());
				int codperfin = Integer.parseInt(form.getCodigoPeriodoProceso2());
				if (codperfin < codperini) {
					String mensaje = "El Periodo Inicial debe ser menor o igual a la Periodo Final";
					return mensaje;
				}

			}else if(form.getCodigoPeriodoProceso1().length()==0){
				String mensaje = "Debe Ingresar el Periodo Incial";
				return mensaje;
			}else if(form.getCodigoPeriodoProceso2().length()==0){
				String mensaje = "Debe Ingresar el Periodo Final";
				return mensaje;
			}
		}else if(cambioFiltroFecha==true){
			if(form.getFechaProcesoInicioD()!=null && form.getFechaProcesoFinD()!=null){
				 if (form.getFechaProcesoInicioD().compareTo(form.getFechaProcesoFinD()) >0){
					 String mensaje = this.getResourceMessage("errors.compare.dates");
					 return mensaje;
				 }
			}else if(form.getFechaProcesoInicioD()==null){
				String mensaje = "Debe Ingresar la Fecha Inicio";
				return mensaje;
			}else if(form.getFechaProcesoFinD()==null){
				String mensaje = "Debe Ingresar la Fecha Fin";
				return mensaje;
			}
			
		}
		return null;
	}

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	public Boolean getCambioFiltroPeriodo() {
		return cambioFiltroPeriodo;
	}

	public void setCambioFiltroPeriodo(Boolean cambioFiltroPeriodo) {
		this.cambioFiltroPeriodo = cambioFiltroPeriodo;
	}

	public Boolean getCambioFiltroFecha() {
		return cambioFiltroFecha;
	}

	public void setCambioFiltroFecha(Boolean cambioFiltroFecha) {
		this.cambioFiltroFecha = cambioFiltroFecha;
	}
	
}

