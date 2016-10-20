package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.ControlFacturacion;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.form.InterfazForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.edu.form.ProcesoEDURecepcionarMaestrosForm;


@ManagedBean
@SessionScoped
public class ProcesoEDURecepcionarMaestrosAction extends BaseMantenimientoSearchAbstractAction{

	
	private static final long serialVersionUID = -1796081837083421871L;
	
	private List eduEmpresaComercializadora;
	private List cargaArchivos;
	private List eduControlFacturacionList;
	private boolean mostrarBotonBuscar=true;
	private boolean mostrarListaBusqueda=true;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoEDURecepcionarMaestrosForm searchForm =new ProcesoEDURecepcionarMaestrosForm();		
		return searchForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonNuevo=false;
		
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();		
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");		
	
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();		
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);		
		this.eduEmpresaComercializadora=service.getEmpresasComercializadorasByPais(parametroEmpresa);
		
        List interfaces = new ArrayList();
        Base base= new Base();
        base.setCodigo("1");
        base.setDescripcion("Carga de Zonas");
        interfaces.add(base);
        base=new Base();
        base.setCodigo("2");
        base.setDescripcion("Carga de Regiones");
        interfaces.add(base);
        base=new Base();
        base.setCodigo("3");
        base.setDescripcion("Carga de Archivos de Control");
        interfaces.add(base);
        this.cargaArchivos=interfaces;
        this.mostrarBotonBuscar=true;
		
	}

	
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {		
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoEDUComercialService procesoComercial = (ProcesoEDUComercialService) getBean("edu.procesoEDUComercialService");
		procesoComercial.executeProcesoEDUProcesoCargaRecepcionMaestros(pais.getCodigo(),params);
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		//params = super.prepareParamsBeforeExecute(params);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoPais", pais.getCodigo());
		params.put("usuario", usuario);
		params.put("copiarSoloControlFacturacion", Constants.NO);
		return params;
	}
	
	 /* (non-Javadoc)
		 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#setFindAttributes(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
		 */
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
	           log.debug("Entering 'find' method");
	    }
		
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
	    List list = service.getControlFacturacionByCriteria(getCriteria());
	    this.eduControlFacturacionList=list;
	    return list;
	}	
	
	private Map getCriteria() throws Exception{		
	    Map criteria = BeanUtils.describe(this.formBusqueda);
	    Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());			
	    return criteria;
	}
	
	public void abrirCampana(ActionEvent actionEvent){
		if (log.isDebugEnabled()) {
			log.debug("Entering abrirCampana method");
		}  
		
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
        
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        String tipo = Constants.NUMERO_CERO;
        ControlFacturacion controlFacturacion = registroSeleccionado(tipo);
        controlFacturacion.setCodigoPeriodo(null);
        service.updateCerrarCampanaControlFacturacion(controlFacturacion, usuario);
        tipo = Constants.NUMERO_UNO;
        controlFacturacion = registroSeleccionado(tipo);
        service.updateCerrarCampanaControlFacturacion(controlFacturacion, usuario);
        this.getResourceMessage("campana.opened"); 
        this.find(actionEvent);       
	}
	
	public void cerrarCampana(ActionEvent actionEvent){
		if (log.isDebugEnabled()) {
			log.debug("Entering cerrarCampana method");
		}
       
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
        
        Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();  
        String tipo = "0";
        ControlFacturacion controlFacturacion = registroSeleccionado(tipo);        
        service.updateCerrarCampanaControlFacturacion(controlFacturacion, usuario);        
        this.getResourceMessage("campana.closed");        
        this.find(actionEvent);       
       
	}
	
	public void ejecutarProceso(ActionEvent actionEvent){
		if (log.isDebugEnabled()) {
			log.debug("Entering ejecutarProceso method");
		}
		Map params=new HashMap();
		try {
			this.prepareParamsBeforeExecute(params);
			this.executeProcess(params);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public ControlFacturacion registroSeleccionado(String tipo){
		ControlFacturacion registro = (ControlFacturacion)this.beanRegistroSeleccionado;
		ControlFacturacion controlFacturacion = new ControlFacturacion();
		controlFacturacion.setCodigoPais(registro.getCodigoPais());
		controlFacturacion.setCodigoEmpresa(registro.getCodigoEmpresa());
		controlFacturacion.setCodigoPeriodo(registro.getCodigoPeriodo());		
		controlFacturacion.setEstadoCampanha(tipo);
		return controlFacturacion;
		
	}	

	public List getEduEmpresaComercializadora() {
		return eduEmpresaComercializadora;
	}

	public void setEduEmpresaComercializadora(List eduEmpresaComercializadora) {
		this.eduEmpresaComercializadora = eduEmpresaComercializadora;
	}

	public List getCargaArchivos() {
		return cargaArchivos;
	}

	public void setCargaArchivos(List cargaArchivos) {
		this.cargaArchivos = cargaArchivos;
	}

	public List getEduControlFacturacionList() {
		return eduControlFacturacionList;
	}

	public void setEduControlFacturacionList(List eduControlFacturacionList) {
		this.eduControlFacturacionList = eduControlFacturacionList;
	}

	public boolean isMostrarBotonBuscar() {
		return mostrarBotonBuscar;
	}

	public void setMostrarBotonBuscar(boolean mostrarBotonBuscar) {
		this.mostrarBotonBuscar = mostrarBotonBuscar;
	}

	public boolean isMostrarListaBusqueda() {
		return mostrarListaBusqueda;
	}

	public void setMostrarListaBusqueda(boolean mostrarListaBusqueda) {
		this.mostrarListaBusqueda = mostrarListaBusqueda;
	}

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
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
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
	
	

	

}
