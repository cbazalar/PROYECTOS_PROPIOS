package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ResultadoChequeo;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDResultadoChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDResultadoChequeoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDResultadoChequeoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoPEDResultadoChequeoAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List resultadosChequeoList;
	
	private List pedResultadoChequearList;
	private Object[] beanMantenimientoPEDResultadoChequeo;
	private DataTableModel dtMantenimientoPEDResultadoChequeo; 
	
	private String parametroAccion=null;
	
	/**
	 * @return the dtMantenimientoPEDResultadoChequeo
	 */
	public DataTableModel getDtMantenimientoPEDResultadoChequeo() {
		return dtMantenimientoPEDResultadoChequeo;
	}

	/**
	 * @param dtMantenimientoPEDResultadoChequeo the dtMantenimientoPEDResultadoChequeo to set
	 */
	public void setDtMantenimientoPEDResultadoChequeo(
			DataTableModel dtMantenimientoPEDResultadoChequeo) {
		this.dtMantenimientoPEDResultadoChequeo = dtMantenimientoPEDResultadoChequeo;
	}

	/**
	 * @return the beanMantenimientoPEDResultadoChequeo
	 */
	public Object[] getBeanMantenimientoPEDResultadoChequeo() {
		return beanMantenimientoPEDResultadoChequeo;
	}

	/**
	 * @param beanMantenimientoPEDResultadoChequeo the beanMantenimientoPEDResultadoChequeo to set
	 */
	public void setBeanMantenimientoPEDResultadoChequeo(
			Object[] beanMantenimientoPEDResultadoChequeo) {
		this.beanMantenimientoPEDResultadoChequeo = beanMantenimientoPEDResultadoChequeo;
	}

	/**
	 * @return the pedResultadoChequearList
	 */
	public List getPedResultadoChequearList() {
		return pedResultadoChequearList;
	}
	
	/**
	 * @param pedResultadoChequearList the pedResultadoChequearList to set
	 */
	public void setPedResultadoChequearList(List pedResultadoChequearList) {
		this.pedResultadoChequearList = pedResultadoChequearList;
	}

	/**
	 * @return the resultadosChequeoList
	 */
	public List getResultadosChequeoList() {
		return resultadosChequeoList;
	}

	/**
	 * @param resultadosChequeoList the resultadosChequeoList to set
	 */
	public void setResultadosChequeoList(List resultadosChequeoList) {
		this.resultadosChequeoList = resultadosChequeoList;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoPEDResultadoChequeoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoPEDResultadoChequeoForm";
	}

	@Override
	public String setValidarConfirmar(String accion) {
		int tamanio = this.beanMantenimientoPEDResultadoChequeo.length; 
		if(tamanio==0){
			return "Debe seleccionar un registro";		
		}
		
		return null;
	}
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoSearchForm objForm = new MantenimientoPEDResultadoChequeoSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoSearchForm f = (MantenimientoPEDResultadoChequeoSearchForm)this.formBusqueda;
		
		MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
		
		Map map = new HashMap();
		
		map.put("codigoResultadoChequeo", f.getCodigoResultadoChequeo());
		
		List list = service.getResultadosChequeoList(map);
		
		this.pedResultadoChequearList = list;
		this.dtMantenimientoPEDResultadoChequeo = new DataTableModel(pedResultadoChequearList);
		
		
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#delete(javax.faces.event.ActionEvent)
	 */
	public void delete(ActionEvent event){
		try {
			String mensaje ="";
			int tamanio = this.beanMantenimientoPEDResultadoChequeo.length; 
			if(tamanio==0){
				this.addWarn("Error: ", "Debe seleccionar un registro");
				return;
			}

			String[] items = new String[tamanio];
			for (int i = 0; i < tamanio; i++) {
				ResultadoChequeo obj = (ResultadoChequeo) this.beanMantenimientoPEDResultadoChequeo[i];
				items[i]=obj.getCodigoResultadoChequeo();	
			}				

			MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
			Map map = new HashMap();
			map.put("selectedItems", items );
			
			service.deleteResultadosChequeo(map);
			
			this.pedResultadoChequearList = this.setFindAttributes();
			this.dtMantenimientoPEDResultadoChequeo = new DataTableModel(pedResultadoChequearList);
			
			
			mensaje = this.getResourceMessage("mantenimientoPEDResultadoChequeoForm.deleted");
			this.addInfo("Info : ", mensaje);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		this.parametroAccion = null;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		this.parametroAccion = externalContext.getRequestParameterMap().get("parametroAccion");
		
		MantenimientoPEDResultadoChequeoForm f = (MantenimientoPEDResultadoChequeoForm)this.formMantenimiento;
		
		MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
		
		Map params = new HashMap();
		
		try{
			
			
		
			if(this.accion.equals(this.ACCION_NUEVO)){
				
				Map map = new HashMap();
				map.put("codigoResultadoChequeo", f.getCodigoResultadoChequeo());
				List list = service.getResultadosChequeoList(map);
				
				if(list.size()==0){
					params.put("codigoPais", f.getCodigoPais());
					params.put("codigoResultadoChequeo", f.getCodigoResultadoChequeo());
					params.put("descripcionResultadoChequeo", f.getDescripcionResultadoChequeo());
					
					service.insertResultadoChequeo(params);
				}else{
					this.addError("Error: ", "Se encontro registro repetido ");
					return false;
				}
				
				
				
			}else{
				
				String idOld = f.getOid();
				
				params.put("idOld", idOld);
				params.put("codigoPais", f.getCodigoPais());
				params.put("codigoResultadoChequeo", f.getCodigoResultadoChequeo());
				params.put("descripcionResultadoChequeo", f.getDescripcionResultadoChequeo());
				
				service.updateResultadoChequeo(params);
			}
			
			Map map = new HashMap();
			
			map.put("codigoResultadoChequeo", null);
			
			this.resultadosChequeoList = service.getResultadosChequeoList(map);
			
			return true;
			
		}catch(Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			this.addError("Error:",this.getResourceMessage("errors.detail", new Object[]{ error }));	
			return false;
		}
	}

	/**
	 * Metodo que obtiene el registro de la lista
	 * @param event
	 */
	public void obtenerRegistro(ActionEvent event){
		try {
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			parametroAccion = externalContext.getRequestParameterMap().get("parametroAccion");
			
			this.mostrarBotonSave=true;
			MantenimientoPEDResultadoChequeoForm f = new MantenimientoPEDResultadoChequeoForm();
			

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			f.setCodigoResultadoChequeo(null);
			f.setDescripcionResultadoChequeo(null);
			
			if(this.parametroAccion.equals(this.ACCION_CONSULTAR)){ 
				f.setActivo(true);
				this.mostrarBotonSave=false;
			}
			
			if(!this.parametroAccion.equals(this.ACCION_NUEVO)){
				
				int tamanio = this.beanMantenimientoPEDResultadoChequeo.length;
				
				if(tamanio>1 || tamanio==0){
					this.addWarn("Error: ", "Solo debe seleccionar un registro");
					return;
				}

				ResultadoChequeo bean = (ResultadoChequeo)this.beanMantenimientoPEDResultadoChequeo[0];
				
				BeanUtils.copyProperties(f, bean);
				String id = f.getCodigoResultadoChequeo();
				
				MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
				
				ResultadoChequeo resultadoChequeo = service.getResultadoChequeoObject(id);
				
				f.setCodigoPais(resultadoChequeo.getCodigoPais());
				f.setCodigoResultadoChequeo(resultadoChequeo.getCodigoResultadoChequeo());
				f.setDescripcionResultadoChequeo(resultadoChequeo.getDescripcionResultadoChequeo());
				f.setOid(f.getCodigoResultadoChequeo());	
				this.formMantenimiento=f;
				this.redireccionarPagina("mantenimientoPEDResultadoChequeoForm");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
				
				
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoForm f = new MantenimientoPEDResultadoChequeoForm();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoResultadoChequeo(null);
		f.setDescripcionResultadoChequeo(null);
		this.mostrarBotonSave=true;
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoSearchForm f = (MantenimientoPEDResultadoChequeoSearchForm)this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda=false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.parametroAccion=null;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
				
		if (this.accion.equals(this.ACCION_NUEVO)) {
			return "mantenimientoPEDResultadoChequeoForm.cabecera.insert";
		}else {			
			return "mantenimientoPEDResultadoChequeoForm.cabecera.update";			
		}
	}

}
