package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBParametrosGeneralesForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBParametrosGeneralesSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoCOBParametrosGeneralesSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -3740341709902114264L;
	
	

	@Override
	protected String getSalirForward() {	
		return "mantenimientoCOBParametrosGeneralesList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoCOBParametrosGeneralesForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBParametrosGeneralesSearchForm searchForm = new MantenimientoCOBParametrosGeneralesSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoCOBParametrosGeneralesSearchForm f = (MantenimientoCOBParametrosGeneralesSearchForm) this.formBusqueda;
		ParametrosGeneralesCOB bean= new ParametrosGeneralesCOB();
		bean.setCodigoParametro(f.getCodigoParametro());		
		bean.setDescripcionParametro(f.getDescripcionParametro());
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
				this.getBean("spusicc.mantenimientoCOBGenericoService");
		List resultado= service.getParametrosGeneralesCobList(bean);
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
				getBean("spusicc.mantenimientoCOBGenericoService");	
		ParametrosGeneralesCOB bean=new ParametrosGeneralesCOB();
		Map parametros= (Map) this.beanRegistroSeleccionado;
		BeanUtils.copyProperties(bean, parametros);
		service.deleteParametrosGeneralesCob(bean, usuario);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
				getBean("spusicc.mantenimientoCOBGenericoService");	
		MantenimientoCOBParametrosGeneralesForm f =(MantenimientoCOBParametrosGeneralesForm)this.formMantenimiento;
		
		ParametrosGeneralesCOB bean= new ParametrosGeneralesCOB();
		BeanUtils.copyProperties(bean, f);
		
		if(f.isNewRecord())
			service.insertParametrosGeneralesCob(bean, usuario);
		else
			service.updateParametrosGeneralesCob(bean, usuario);
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoCOBParametrosGeneralesForm f = new MantenimientoCOBParametrosGeneralesForm();
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
				getBean("spusicc.mantenimientoCOBGenericoService");	
		this.mostrarBotonSave=true;
		f.setNewRecord(true);
		f.setEditable(true);
		
		if(!StringUtils.equals(this.accion, this.ACCION_NUEVO)){
			ParametrosGeneralesCOB bean= new ParametrosGeneralesCOB();
			ParametrosGeneralesCOB parametroBean= new ParametrosGeneralesCOB();
			Map parametro=(Map)this.beanRegistroSeleccionado;
			String codigo=parametro.get("codigoParametro").toString();
			if(StringUtils.isNotBlank(codigo)){
				BeanUtils.copyProperties(bean, parametro);
				parametroBean=service.getParametrosGeneralesCob(bean);
				BeanUtils.copyProperties(f, parametroBean);
				f.setNewRecord(false);
				f.setEditable(true);				
			}			
		}
		if(StringUtils.equals(this.accion, this.ACCION_CONSULTAR)){
			this.mostrarBotonSave=false;
			f.setEditable(false);
			
		}
		return f;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.find();
		
	}
	
	@Override
	public String setValidarMantenimiento(){
		MantenimientoCOBParametrosGeneralesForm f =(MantenimientoCOBParametrosGeneralesForm)this.formMantenimiento;
		if(StringUtils.equals(this.accion, this.ACCION_NUEVO)){
		ParametrosGeneralesCOB bean= new ParametrosGeneralesCOB();
		bean.setCodigoParametro(f.getCodigoParametro());		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
				this.getBean("spusicc.mantenimientoCOBGenericoService");
		List resultado= service.getParametrosGeneralesCobList(bean);
		if(resultado.size()==1)
			return "Error: Código Parámetro ya existe";
		}
		return "";
	}

}
