package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazRUVService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.sisicc.form.ProcesoRUVReprocesarInterfaseForm;


@ManagedBean
@SessionScoped
public class ProcesoRUVReprocesarInterfaseAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -8799444785362609943L;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRUVReprocesarInterfaseForm procesoForm =new ProcesoRUVReprocesarInterfaseForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		 if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	       }
		ProcesoRUVReprocesarInterfaseForm f=(ProcesoRUVReprocesarInterfaseForm)this.formProceso; 
		Pais pais =mPantallaPrincipalBean.getCurrentCountry();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");		
		String anho=sdf.format(new Date(System.currentTimeMillis()));
		f.setAnho(anho);
		f.setCodigoPais(pais.getCodigo());
	
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		ProcesoRUVReprocesarInterfaseForm f = (ProcesoRUVReprocesarInterfaseForm)this.formProceso;
		InterfazRUVService service = (InterfazRUVService)getBean("sisicc.interfazRUVService");
		
		Map map = new HashMap();
		map.put("codigoPais", f.getCodigoPais());
		String mes = f.getMes();
		String anho = f.getAnho();
		String fechaInicio = devuelveFechaInicio(mes, anho);
		String fechaFin = devuelveFechaFin(mes, anho);
		
		map.put("fechaInicio", fechaInicio);
		map.put("fechaFin", fechaFin);		
		service.ejecutarReprocesoInterfazRUV(map);
		return params;		
	
	}
	
	/**
	 * Devuelve la fecha de inicio del mes y ańo dado como parametro	
	 */
	private String devuelveFechaInicio(String mes, String anho){
		String fecha = "";
		
		if (mes.length()== 1)
			fecha = "01/" + "0"+ mes + "/" + anho;
		else
			fecha = "01/" + mes + "/" + anho;
		
		return fecha;
	}
	
	/**
	 * Devuelve la fecha de fin del mes y ańo dado como parametro	
	 */
	private String devuelveFechaFin(String mes, String anho){
		String fecha = "";
		String dia = "";
		int a = Integer.parseInt(anho);
		
		if (mes.equals("2")){
			if ((a % 4)== 0)
				dia = "29";
			else
				dia = "28";
		}
		else{
			if ( mes.equals("1") || mes.equals("3")  ||
				 mes.equals("5") || mes.equals("7")  ||	
				 mes.equals("8") || mes.equals("10") ||
				 mes.equals("12") )
				dia = "31";
			else
				dia = "30";
		}
		
		if (mes.length()== 1)
			fecha = dia + "/" + "0"+ mes + "/" + anho;
		else
			fecha = dia + "/" + mes + "/" + anho;
		
		return fecha;
	}

	
	
	

}
