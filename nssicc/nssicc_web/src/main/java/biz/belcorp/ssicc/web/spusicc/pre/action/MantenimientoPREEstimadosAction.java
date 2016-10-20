package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREEstimadosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPREEstimadosSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoPREEstimadosAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -1753098384589103807L;

	
	private List<Base> catalogoList;
	private List registroDeleteList;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPREEstimadosSearchForm form = new MantenimientoPREEstimadosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {		
       MantenimientoPREEstimadosSearchForm f = (MantenimientoPREEstimadosSearchForm) this.formBusqueda;		
       MantenimientoPREEstimadosService service = (MantenimientoPREEstimadosService) this.getBean("spusicc.mantenimientoPREEstimadosService");

       	Map param =BeanUtils.describe(f);		
		List resultado= service.getManPREEstimadosList(param);
		this.registroDeleteList=resultado;
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=false;
		this.mostrarBotonEliminar=false;
		
		MantenimientoPREEstimadosService service = (MantenimientoPREEstimadosService) this.getBean("spusicc.mantenimientoPREEstimadosService");		
		 this.catalogoList  = service.getManPREEstimadosCatalogoList();
		
	}
	
	//Eliminar registros de la grilla
	public void eliminarRegistroTotal(ActionEvent actionEvent){
		try {
			MantenimientoPREEstimadosService service = (MantenimientoPREEstimadosService) this.getBean("spusicc.mantenimientoPREEstimadosService");
			List listaEliminar = this.registroDeleteList;
			for(int i=0;i<listaEliminar.size();i++){
				Map origen = new HashMap();
				Map parametros = new HashMap();
				origen=(Map)(listaEliminar.get(i));
				String oid = origen.get("oidEstimados").toString();
				parametros.put("oidEstimados", oid);
				service.deleteManPREEstimados(parametros);
			}
			
			this.find();
			this.addInfo("Info: ", this.getResourceMessage("datos.delete.ok"));				
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
		
	}
	
	
	/**
	 * @return the catalogoList
	 */
	public List<Base> getCatalogoList() {
		return catalogoList;
	}

	/**
	 * @param catalogoList the catalogoList to set
	 */
	public void setCatalogoList(List<Base> catalogoList) {
		this.catalogoList = catalogoList;
	}

	/**
	 * @return the registroDeleteList
	 */
	public List getRegistroDeleteList() {
		return registroDeleteList;
	}

	/**
	 * @param registroDeleteList the registroDeleteList to set
	 */
	public void setRegistroDeleteList(List registroDeleteList) {
		this.registroDeleteList = registroDeleteList;
	}	

}
