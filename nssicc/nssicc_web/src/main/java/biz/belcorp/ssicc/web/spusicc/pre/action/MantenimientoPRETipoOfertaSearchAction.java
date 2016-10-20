package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPRETipoOfertaForm;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPRETipoOfertaSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoPRETipoOfertaSearchAction extends	BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -8180287884140977276L;

	public void inicializar() {
		
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoPRETipoOfertaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPRETipoOfertaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoPRETipoOfertaSearchForm form = new MantenimientoPRETipoOfertaSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoPRETipoOfertaSearchForm f = (MantenimientoPRETipoOfertaSearchForm) this.formBusqueda;		
		MantenimientoPRETipoOfertaService service = (MantenimientoPRETipoOfertaService) this.getBean("spusicc.mantenimientoPRETipoOfertaService");
		
		Map param =BeanUtils.describe(f);     
		List resultado= service.getManPRETipoOfertaList(param);
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		MantenimientoPRETipoOfertaService service = (MantenimientoPRETipoOfertaService) this.getBean("spusicc.mantenimientoPRETipoOfertaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map selectGrid = (Map) this.beanRegistroSeleccionado;
		
		Map parametros = new HashMap();		

		parametros.put("codigoUsuario", usuario.getLogin());
		parametros.put("oidTipoOferta",selectGrid.get("oidTipoOferta"));		
		
		service.deleteManPRETipoOferta(parametros);
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoPRETipoOfertaService service = (MantenimientoPRETipoOfertaService) this.getBean("spusicc.mantenimientoPRETipoOfertaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		MantenimientoPRETipoOfertaForm f = (MantenimientoPRETipoOfertaForm) this.formMantenimiento;

		f.setIndComisionable(converterBoleanToString(f.isIndComisionableBol()));
		f.setIndOtorgaPuntaje(converterBoleanToString(f.isIndOtorgaPuntajeBol()));
		f.setIndAportaMontoMinimo(converterBoleanToString(f.isIndAportaMontoMinimoBol()));
		f.setIndAportaEscalaDesc(converterBoleanToString(f.isIndAportaEscalaDescBol()));
		f.setIndEstadisticable(converterBoleanToString(f.isIndEstadisticableBol()));			
			
		Map param=BeanUtils.describe(f);
		param.put("codUsuario", usuario.getLogin());
			
		if(f.isNewRecord())
			service.insertManPreTiPofertaTotal(param);
		else				
			service.updateManPreTiPofertaTotal(param);			
			
		return true;
	}

	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoPRETipoOfertaService service = (MantenimientoPRETipoOfertaService) this.getBean("spusicc.mantenimientoPRETipoOfertaService");
		MantenimientoPRETipoOfertaForm f = new MantenimientoPRETipoOfertaForm();		
		Map selectGrid = (Map) this.beanRegistroSeleccionado;		
	    f.setNewRecord(true);
	    
		if (!this.accion.equals(this.ACCION_NUEVO)) {
		
		Map parametros = new HashMap();
		String oidOferta=selectGrid.get("oidTipoOferta").toString();
		
		if(StringUtils.isNotBlank(oidOferta)){		
			parametros.put("oidTipoOferta", oidOferta); 		
			Map result = service.getManPRETipoOferta(parametros);    
		
			f.setIndComisionableBol(StringToBoolean(result.get("indComisionable").toString()));
	    	f.setIndOtorgaPuntajeBol(StringToBoolean(result.get("indOtorgaPuntaje").toString()));
		    f.setIndAportaMontoMinimoBol(StringToBoolean(result.get("indAportaMontoMinimo").toString()));
		    f.setIndAportaEscalaDescBol(StringToBoolean(result.get("indAportaEscalaDesc").toString()));
			f.setIndGratBol(StringToBoolean(result.get("indGrat").toString()));
			f.setIndEstadisticableBol(StringToBoolean(result.get("indEstadisticable").toString()));		

			BeanUtils.copyProperties(f,result);
	        String codigoTipoOferta = result.get("codigoTipoOferta").toString();
	        String descripcion =result.get("descripcion").toString();
			f.setDescripcion(descripcion.split(""+codigoTipoOferta+" -")[1]);
		
			f.setNewRecord(false);
			}
		}
		
		return f;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {		
		this.mostrarBotonConsultar=false;

		MantenimientoPRETipoOfertaSearchForm f = (MantenimientoPRETipoOfertaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

	}
	
	public String setValidarMantenimiento() {
		MantenimientoPRETipoOfertaService service = (MantenimientoPRETipoOfertaService) this.getBean("spusicc.mantenimientoPRETipoOfertaService");		
		MantenimientoPRETipoOfertaForm f = (MantenimientoPRETipoOfertaForm) this.formMantenimiento;				
		Integer codigo = service.validaManPRETipoOferta(f.getCodigoTipoOferta());
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (codigo > 0) 				
				return this.getResourceMessage("mantenimientoPRETipoOfertaForm.msjCodigoOferta");			
		}

		return "";
	}
	
	public String converterBoleanToString(boolean param){
		if(param==true)
			return "1";
		else
			return "0";		
		
	}	

	public boolean StringToBoolean(String parametro){		
		if(StringUtils.equals(parametro, "1"))
			return true;
		else
			return false;		
		
	}	
	

}
