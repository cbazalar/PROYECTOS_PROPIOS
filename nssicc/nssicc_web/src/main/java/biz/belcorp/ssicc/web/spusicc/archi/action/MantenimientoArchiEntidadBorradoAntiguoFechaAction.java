package biz.belcorp.ssicc.web.spusicc.archi.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.archi.model.EntidadBorradoPeriodicoAntFecha;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB;
import biz.belcorp.ssicc.service.spusicc.archi.MantenimientoARCHIGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.archi.form.MantenimientoArchiEntidadBorradoAntiguoFechaForm;
import biz.belcorp.ssicc.web.spusicc.archi.form.MantenimientoArchiEntidadBorradoAntiguoFechaSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBParametrosGeneralesForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBParametrosGeneralesSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoArchiEntidadBorradoAntiguoFechaAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -1261590643006207018L;
	
	private List tipoModulosList;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoArchiEntidadBorradoAntiguoFechaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoArchiEntidadBorradoAntiguoFechaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoArchiEntidadBorradoAntiguoFechaSearchForm searchForm = new MantenimientoArchiEntidadBorradoAntiguoFechaSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoArchiEntidadBorradoAntiguoFechaSearchForm f= (MantenimientoArchiEntidadBorradoAntiguoFechaSearchForm)this.formBusqueda;
		MantenimientoARCHIGenericoService service = (MantenimientoARCHIGenericoService) 
				this.getBean("spusicc.mantenimientoARCHIGenericoService");
		Map params=new HashMap();
		params.put("codigoModulo", f.getCodigoModulo());
		List lista=service.getEntidadPeriodicoAntiguoFechaList(params);
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoARCHIGenericoService service = (MantenimientoARCHIGenericoService) 
				this.getBean("spusicc.mantenimientoARCHIGenericoService");	
		EntidadBorradoPeriodicoAntFecha bean=new EntidadBorradoPeriodicoAntFecha();
		Map parametros= (Map) this.beanRegistroSeleccionado;
		BeanUtils.copyProperties(bean, parametros);
		service.deleteEntidadBorraPeriFecha(bean, usuario);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoARCHIGenericoService service = (MantenimientoARCHIGenericoService) 
				this.getBean("spusicc.mantenimientoARCHIGenericoService");
		MantenimientoArchiEntidadBorradoAntiguoFechaForm f =(MantenimientoArchiEntidadBorradoAntiguoFechaForm)this.formMantenimiento;
		
		EntidadBorradoPeriodicoAntFecha bean= new EntidadBorradoPeriodicoAntFecha();
		BeanUtils.copyProperties(bean, f);
		
		if(f.isNewRecord())
			service.insertEntidadBorraPeriFecha(bean, usuario);
		else
			service.updateEntidadBorraPeriFecha(bean, usuario);
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoArchiEntidadBorradoAntiguoFechaForm f = new MantenimientoArchiEntidadBorradoAntiguoFechaForm();
		MantenimientoARCHIGenericoService service = (MantenimientoARCHIGenericoService) 
				this.getBean("spusicc.mantenimientoARCHIGenericoService");
		this.mostrarBotonSave=true;
		f.setNewRecord(true);
		f.setEditable(true);
		
		if(!StringUtils.equals(this.accion, this.ACCION_NUEVO)){		
			EntidadBorradoPeriodicoAntFecha parametroBean= new EntidadBorradoPeriodicoAntFecha();
			Map parametro=(Map)this.beanRegistroSeleccionado;
			String codigo=parametro.get("codigoModulo").toString();
			String entidad=parametro.get("entidad").toString();
			if(StringUtils.isNotBlank(codigo) && StringUtils.isNotBlank(entidad)){				
				parametroBean=service.getEntidadPeriodicoAntiguoFecha(parametro);
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
		MantenimientoARCHIGenericoService service = (MantenimientoARCHIGenericoService) 
				this.getBean("spusicc.mantenimientoARCHIGenericoService");
		Map params=new HashMap();
		this.tipoModulosList=service.getListaTipoModulo(params);
		
	}
	
	@Override
	public String setValidarMantenimiento(){	
			MantenimientoArchiEntidadBorradoAntiguoFechaForm f =(MantenimientoArchiEntidadBorradoAntiguoFechaForm)this.formMantenimiento;
			MantenimientoARCHIGenericoService service = (MantenimientoARCHIGenericoService) 
					this.getBean("spusicc.mantenimientoARCHIGenericoService");
			if(StringUtils.equals(this.accion, this.ACCION_NUEVO)){				
				Map parametro= new HashMap();
				parametro.put("codigoModulo", f.getCodigoModulo());
				parametro.put("entidad", f.getEntidad());								
				Object resultado= service.getEntidadPeriodicoAntiguoFecha(parametro);
				if(resultado!=null)
					return "Error: Módulo e Identidad ya existen";
			}
			return "";		
	}

	/**
	 * @return the tipoModulosList
	 */
	public List getTipoModulosList() {
		return tipoModulosList;
	}

	/**
	 * @param tipoModulosList the tipoModulosList to set
	 */
	public void setTipoModulosList(List tipoModulosList) {
		this.tipoModulosList = tipoModulosList;
	}
	
	

}
