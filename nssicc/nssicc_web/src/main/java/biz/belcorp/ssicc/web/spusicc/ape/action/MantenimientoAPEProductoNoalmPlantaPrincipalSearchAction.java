package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.ape.model.MantenimientoAPEProductoNoalmPlantaPrincipal;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEProductoNoalmPlantaPrincipalService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEProductoNoalmPlantaPrincipalForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEProductoNoalmPlantaPrincipalSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoAPEProductoNoalmPlantaPrincipalSearchAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2641891370672713438L;

	private List apeEmpresaExternaList;
	

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoAPEProductoNoalmPlantaPrincipalList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoAPEProductoNoalmPlantaPrincipalForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEProductoNoalmPlantaPrincipalSearchForm objForm = new MantenimientoAPEProductoNoalmPlantaPrincipalSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'find' method");
		
		MantenimientoAPEProductoNoalmPlantaPrincipalSearchForm f = (MantenimientoAPEProductoNoalmPlantaPrincipalSearchForm)this.formBusqueda;	
		MantenimientoAPEProductoNoalmPlantaPrincipalService service = (MantenimientoAPEProductoNoalmPlantaPrincipalService)
										getBean("spusicc.mantenimientoAPEProductoNoalmPlantaPrincipalService");
		
		Map criteria = BeanUtils.describe(f);
		
		if(StringUtils.isNotBlank(f.getDescripcionProducto()))
			criteria.put("descripcionProducto", f.getDescripcionProducto() + "%");
		
		List lista = service.getProductosNoalmPlantaPrincipalByCriteria(criteria);
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'save' method");
		
		//--  Variables
		MantenimientoAPEProductoNoalmPlantaPrincipalForm f =(MantenimientoAPEProductoNoalmPlantaPrincipalForm)this.formMantenimiento;
		MantenimientoAPEProductoNoalmPlantaPrincipal producto = new MantenimientoAPEProductoNoalmPlantaPrincipal();
		
		BeanUtils.copyProperties(producto, f);
		MantenimientoAPEProductoNoalmPlantaPrincipalService service = (MantenimientoAPEProductoNoalmPlantaPrincipalService)
								getBean("spusicc.mantenimientoAPEProductoNoalmPlantaPrincipalService");
		
		if(StringUtils.isBlank(producto.getCodigoEmpresaExterna()))
			producto.setCodigoEmpresaExterna(null);
		
		if(StringUtils.equals(producto.getIndicadorImprime(), Constants.NUMERO_CERO))
			producto.setIndicadorImprime(null);
			
		service.updateProductoNoalmPlantaPrincipal(producto);
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		Map obj = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoAPEProductoNoalmPlantaPrincipalForm f = new MantenimientoAPEProductoNoalmPlantaPrincipalForm();
		
		MantenimientoAPEProductoNoalmPlantaPrincipalService service = (MantenimientoAPEProductoNoalmPlantaPrincipalService)
						getBean("spusicc.mantenimientoAPEProductoNoalmPlantaPrincipalService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(this.accion.equals(this.ACCION_MODIFICAR)){
			BeanUtils.copyProperties(f, obj);
			
			String id = obj.get("codigo").toString();
			MantenimientoAPEProductoNoalmPlantaPrincipal producto = service.getProductoNoalmPlantaPrincipal(id);
			
			if(StringUtils.isBlank(producto.getIndicadorImprime()))
				producto.setIndicadorImprime(Constants.NUMERO_CERO);
				
			if(log.isDebugEnabled())
				log.debug("producto: " + producto);
			
			BeanUtils.copyProperties(f, producto);
			
			f.setCodigoPais(pais.getCodigo());
		}
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEProductoNoalmPlantaPrincipalService service = (MantenimientoAPEProductoNoalmPlantaPrincipalService)
								getBean("spusicc.mantenimientoAPEProductoNoalmPlantaPrincipalService");
		
		this.apeEmpresaExternaList = service.getEmpresasExternas();			
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		return "productoNoalmPlantaPrincipal.updated";
	}

	/**
	 * @return the apeEmpresaExternaList
	 */
	public List getApeEmpresaExternaList() {
		return apeEmpresaExternaList;
	}

	/**
	 * @param apeEmpresaExternaList the apeEmpresaExternaList to set
	 */
	public void setApeEmpresaExternaList(List apeEmpresaExternaList) {
		this.apeEmpresaExternaList = apeEmpresaExternaList;
	}
		
}
