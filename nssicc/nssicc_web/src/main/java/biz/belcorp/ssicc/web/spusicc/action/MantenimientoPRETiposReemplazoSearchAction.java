package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.model.TiposReemplazo;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRETiposReemplazoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPRETiposReemplazoForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPRETiposReemplazoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoPRETiposReemplazoSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -8521842414925831834L;	


	@Override
	protected String getSalirForward() {		
		return "mantenimientoPRETiposReemplazoSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoPRETiposReemplazoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPRETiposReemplazoSearchForm searchForm = new MantenimientoPRETiposReemplazoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoPRETiposReemplazoSearchForm f = (MantenimientoPRETiposReemplazoSearchForm) this.formBusqueda;		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPRETiposReemplazoService mantenimientoPRETiposReemplazoService = 
				(MantenimientoPRETiposReemplazoService) getBean("spusicc.mantenimientoPRETiposReemplazoService");
		f.setCodigoPais(pais.getCodigo());
		Map map = BeanUtils.describe(f);		
		map.put("idioma", pais.getCodigoIdiomaIso());
		// lleno la lista para el tipo de operacion
		List lista = mantenimientoPRETiposReemplazoService.getTiposReemplazoList(map);				
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		MantenimientoPRETiposReemplazoSearchForm f = (MantenimientoPRETiposReemplazoSearchForm) this.formBusqueda;
		MantenimientoPRETiposReemplazoService service = (MantenimientoPRETiposReemplazoService) getBean("spusicc.mantenimientoPRETiposReemplazoService");
		TiposReemplazo motivo = new TiposReemplazo();
		
		Map sistemaBusqueda= (HashMap)this.beanRegistroSeleccionado;			
		String oid=MapUtils.getString(sistemaBusqueda, "oid");
					
		service.deleteTiposReemplazo(oid);			
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoPRETiposReemplazoForm f = (MantenimientoPRETiposReemplazoForm) this.formMantenimiento;
		MantenimientoPRETiposReemplazoService service = (MantenimientoPRETiposReemplazoService) getBean("spusicc.mantenimientoPRETiposReemplazoService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		TiposReemplazo motivo = new TiposReemplazo();
		
		BeanUtils.copyProperties(motivo, f); // copiar
		
		if (f.isNewRecord()) {
			try {
				// insertar
				service.insertTiposReemplazo(motivo, usuario);
			} catch (Exception e) {				
				throw new Exception(this.getResourceMessage("mantenimientoPRETiposReemplazoForm.existe"));
			}
		} else {
			// actualizar
			service.updateTiposReemplazo(motivo, usuario);						
			
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPRETiposReemplazoForm f = new MantenimientoPRETiposReemplazoForm();
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			Map sistemaBusqueda= (HashMap)this.beanRegistroSeleccionado;			
			String oid=MapUtils.getString(sistemaBusqueda, "oid");
			
			String codigoPais=pais.getCodigo();
			if (oid != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + oid + " "+ codigoPais);
				}				
				MantenimientoPRETiposReemplazoService service = (MantenimientoPRETiposReemplazoService) getBean("spusicc.mantenimientoPRETiposReemplazoService");
						
				TiposReemplazo motivo = service.getTiposReemplazo(oid);				
								
				BeanUtils.copyProperties(f, motivo);
				f.setCodigoPais(codigoPais);
				f.setNewRecord(false);				
				
			}
		}		
		return f;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoPRETiposReemplazoForm f = (MantenimientoPRETiposReemplazoForm) this.formMantenimiento;
		if(f.isNewRecord())
			return "mantenimientoPRETiposReemplazoForm.cabecera.insert";
		else
			return "mantenimientoPRETiposReemplazoForm.update";
	}	


	@Override
	protected void setViewAtributes() throws Exception {		
		this.mostrarBotonConsultar=false;		
	}
	
}
