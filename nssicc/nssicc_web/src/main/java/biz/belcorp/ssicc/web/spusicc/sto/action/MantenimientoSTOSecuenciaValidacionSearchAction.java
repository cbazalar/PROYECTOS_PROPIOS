package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.SecuenciaValidacion;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOSecuenciaValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoSTOSecuenciaValidacionForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOSecuenciaValidacionSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOSecuenciaValidacionSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 3915132379608435392L;
	
	private List stoDocumentoValidacionList;
	private List stoSecuenciaValidacionList;
	private List stoValidacionesSecuenciaList;
	private DataTableModel stoSecuenciaValidacionDataModel;
	private Object[] beanStoSecuenciaValidacion;
	private String codigoDocumento;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoSTOSecuenciaValidacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoSTOSecuenciaValidacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOSecuenciaValidacionSearchForm searchForm = new MantenimientoSTOSecuenciaValidacionSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {		
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoSTOSecuenciaValidacionService service = (MantenimientoSTOSecuenciaValidacionService) getBean("sto.mantenimientoSTOSecuenciaValidacionService");	
		MantenimientoSTOSecuenciaValidacionForm f = (MantenimientoSTOSecuenciaValidacionForm) this.formMantenimiento;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = f.getCodigoPais();		
		try {			
			SecuenciaValidacion bean = new SecuenciaValidacion();
			bean.setCodigoTipoDocumento(this.codigoDocumento);
			bean.setCodigoPais(codigoPais);
			bean.setCodigo(f.getCodigoValidacion());
			bean.setSecuenciaNueva(f.getNumeroSecuencia());
			bean.setEstado(Constants.EST_REGI);			
			
			if(!service.insertPametroSecuenciaValidacionSTO(bean, usuario)){				
	            throw new Exception(this.getResourceMessage("mantenimientoSTOSecuenciaValidacionForm.duplicado"));
			}			
			
		} catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            throw new Exception(this.getResourceMessage("errors.invalid.id",new Object[]{codigo}));
	     }
	     catch (InvalidDescriptionException ide) {
	            String descripcion = ide.getDescription();
	            throw new Exception(this.getResourceMessage("errors.invalid.description",new Object[]{descripcion }));   
	     }					
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoSTOSecuenciaValidacionSearchForm forBuscar = (MantenimientoSTOSecuenciaValidacionSearchForm) this.formBusqueda;	
		MantenimientoSTOSecuenciaValidacionForm f = new MantenimientoSTOSecuenciaValidacionForm();
		this.codigoDocumento =forBuscar.getCodigoDocumento();
		
		//request.getSession().setAttribute("indicadorGrabo", Constants.NUMERO_CERO);  
		
		Map criteria = new HashMap();
		criteria.put("codigoTipoDocumento", codigoDocumento);
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		this.stoValidacionesSecuenciaList= procesoSTOEjecucionValidacionesService.getValidacionesSTO(criteria);
		f.setCodigoPais(forBuscar.getCodigoPais());
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOSecuenciaValidacionSearchForm f = (MantenimientoSTOSecuenciaValidacionSearchForm) this.formBusqueda;
		MantenimientoSTOSecuenciaValidacionService service = (MantenimientoSTOSecuenciaValidacionService) getBean("sto.mantenimientoSTOSecuenciaValidacionService");		
		
		Map params = new HashMap();
		String codigoPais = pais.getCodigo();
		params.put("codigoPais", codigoPais);
		f.setCodigoPais(codigoPais);
		List tiposDocumento = (List)service.getTipoDocumentoList(params);
		this.stoDocumentoValidacionList=tiposDocumento;
		this.stoSecuenciaValidacionList=new ArrayList();		
		
		f.setCodigoDocumento("");
		this.mostrarBotonNuevo=false;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonModificar=false;	
		this.stoSecuenciaValidacionDataModel=new DataTableModel();	
		
	}
	
	public void savePrincipal(ActionEvent event){
		try {
			MantenimientoSTOSecuenciaValidacionSearchForm f = (MantenimientoSTOSecuenciaValidacionSearchForm) this.formBusqueda;
			String codigoPais =f.getCodigoPais();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();			
			
			List listaSecuencia = this.stoSecuenciaValidacionList;
			
			this.actualizaListaGrillaDescuento(f,listaSecuencia);
				
			if(listaSecuencia.size()>0){
				MantenimientoSTOSecuenciaValidacionService service = (MantenimientoSTOSecuenciaValidacionService) getBean("sto.mantenimientoSTOSecuenciaValidacionService");	
				Map criteria = new HashMap();
				criteria.put("listaSecuenciaValidacion", listaSecuencia);
				criteria.put("codigoPais", codigoPais);
				criteria.put("codigoTipoDocumentoDigi", f.getCodigoDocumento());
				
				if(!service.updatePametroSecuenciaValidacionSTO(criteria,usuario))						
			           throw new Exception(this.getResourceMessage("mantenimientoSTOSecuenciaValidacionSearchForm.duplicado"));
				else						
					this.addInfo("", this.getResourceMessage("mantenimientoSTOSecuenciaValidacionSearchForm.updated"));					
			}else	        		
		          throw new Exception(this.getResourceMessage("mantenimientoSTOSecuenciaValidacionSearchForm.error"));
	        
			//request.getSession().setAttribute(Constants.STO_SECUENCA_VALIDACION_LIST,listaSecuencia);	
			//this.listaBusqueda=this.stoSecuenciaValidacionList;
			//this.datatableBusqueda=new DataTableModel(this.listaBusqueda);
			
			this.stoSecuenciaValidacionDataModel=new DataTableModel(this.stoSecuenciaValidacionList);	
			//this.find(event);
		
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", error);
		}
	}
	
	private void actualizaListaGrillaDescuento(MantenimientoSTOSecuenciaValidacionSearchForm f, List list) throws Exception{
		if (list == null) return;		
		
		for (int k = 0; k < list.size();k++) {
			
			SecuenciaValidacion sv = new SecuenciaValidacion();
			sv=(SecuenciaValidacion)list.get(k);
			
			sv.getEstado();
		
			String estado =sv.getEstado();
			//String secuenciaNueva = f.getListaSecuenciaValidacionSecuenciaNuevo()[k];
		
				//sv.setEstado(estado);
				//sv.setSecuenciaNueva(secuenciaNueva);
				
			if(!StringUtils.equals(estado, Constants.NUMERO_UNO) && !StringUtils.equals(estado, Constants.NUMERO_CERO))
				throw new Exception(this.getResourceMessage("mantenimientoSTOSecuenciaValidacionSearchForm.error.estado"));
		
		}		
	}
	
	public void findPrincipal(ActionEvent event){
		try {
			MantenimientoSTOSecuenciaValidacionService service = (MantenimientoSTOSecuenciaValidacionService) getBean("sto.mantenimientoSTOSecuenciaValidacionService");		
			MantenimientoSTOSecuenciaValidacionSearchForm f = (MantenimientoSTOSecuenciaValidacionSearchForm) this.formBusqueda;
			Map params = new HashMap();
			params.put("codigoDocumento", f.getCodigoDocumento());
			
			String codigoPais = f.getCodigoPais();
			params.put("codigoPais", codigoPais);		
			
			List validaciones = (List)service.getValidacionesByCriteria(params);
			this.stoSecuenciaValidacionList=validaciones;
			this.stoSecuenciaValidacionDataModel=new DataTableModel(this.stoSecuenciaValidacionList);
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", error);
		}
	}
	
	public void deletePrincipal(ActionEvent event){
		try {
			if(this.beanStoSecuenciaValidacion==null){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);			
				return;
			} 
			
			MantenimientoSTOSecuenciaValidacionSearchForm f = (MantenimientoSTOSecuenciaValidacionSearchForm)this.formBusqueda;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			String codigoPais = f.getCodigoPais();
			
			MantenimientoSTOSecuenciaValidacionService service = (MantenimientoSTOSecuenciaValidacionService) getBean("sto.mantenimientoSTOSecuenciaValidacionService");

			List secuenciaList = this.stoSecuenciaValidacionList;
			String[] selectedItems = f.getSelectedItems();			
		
				if(this.beanStoSecuenciaValidacion!= null){
					for(int i=0; i<this.beanStoSecuenciaValidacion.length; i++){
						SecuenciaValidacion beanSv=new SecuenciaValidacion();
						beanSv=(SecuenciaValidacion)this.beanStoSecuenciaValidacion[i];				
						
						for(int j=0; j<secuenciaList.size(); j++){
							
							String secuenciaOriginal ="";
							String codigoValidacion = "";
							String codigoDocumento = "";
							SecuenciaValidacion sv = (SecuenciaValidacion) secuenciaList.get(j);
							
							if(sv.getSecuenciaOriginal()!=null)
								secuenciaOriginal=sv.getSecuenciaOriginal();
							
							if(sv.getCodigo()!=null)
								codigoValidacion=sv.getCodigo();						
							
							if(sv.getCodigoTipoDocumento()!=null)
								codigoDocumento=sv.getCodigoTipoDocumento();
							
							
							if(StringUtils.equals(beanSv.getCodigo(),codigoValidacion) &&
								StringUtils.equals(beanSv.getSecuenciaOriginal(),secuenciaOriginal)&&
								StringUtils.equals(beanSv.getCodigoTipoDocumento(),codigoDocumento)){
								
								secuenciaList.remove(j);				
								//Eliminados de BD
							
								SecuenciaValidacion bean = new SecuenciaValidacion();							
								bean.setCodigoPais(codigoPais);
								bean.setSecuenciaOriginal(beanSv.getSecuenciaOriginal());
								bean.setCodigo(beanSv.getCodigo());
								bean.setCodigoTipoDocumento(beanSv.getCodigoTipoDocumento());								
								service.deletePametroSecuenciaValidacionSTO(bean);							
							}
						}
					}
				}		

				//session.setAttribute(Constants.STO_SECUENCA_VALIDACION_LIST, secuenciaList);
				this.stoSecuenciaValidacionList=secuenciaList;
				this.stoSecuenciaValidacionDataModel=new DataTableModel(this.stoSecuenciaValidacionList);	
				this.addInfo("", this.getResourceMessage("mantenimientoSTOSecuenciaValidacionSearchForm.deleted"));				
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", error);
		}
	}
	
	
	@Override
	protected String devuelveMensajeKeySaveOK() {		
		return "mantenimientoSTOSecuenciaValidacionForm.created";		
	}

	public List getStoDocumentoValidacionList() {
		return stoDocumentoValidacionList;
	}

	public void setStoDocumentoValidacionList(List stoDocumentoValidacionList) {
		this.stoDocumentoValidacionList = stoDocumentoValidacionList;
	}

	public List getStoSecuenciaValidacionList() {
		return stoSecuenciaValidacionList;
	}

	public void setStoSecuenciaValidacionList(List stoSecuenciaValidacionList) {
		this.stoSecuenciaValidacionList = stoSecuenciaValidacionList;
	}

	public List getStoValidacionesSecuenciaList() {
		return stoValidacionesSecuenciaList;
	}

	public void setStoValidacionesSecuenciaList(List stoValidacionesSecuenciaList) {
		this.stoValidacionesSecuenciaList = stoValidacionesSecuenciaList;
	}

	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	public DataTableModel getStoSecuenciaValidacionDataModel() {
		return stoSecuenciaValidacionDataModel;
	}

	public void setStoSecuenciaValidacionDataModel(
			DataTableModel stoSecuenciaValidacionDataModel) {
		this.stoSecuenciaValidacionDataModel = stoSecuenciaValidacionDataModel;
	}

	public Object[] getBeanStoSecuenciaValidacion() {
		return beanStoSecuenciaValidacion;
	}

	public void setBeanStoSecuenciaValidacion(Object[] beanStoSecuenciaValidacion) {
		this.beanStoSecuenciaValidacion = beanStoSecuenciaValidacion;
	}
	
	
	

}
