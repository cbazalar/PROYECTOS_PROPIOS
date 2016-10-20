package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOSecuenciaValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLiberacionRechazoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOLiberacionRechazoSearchForm;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoSTOLiberacionRechazoSearchAction extends
		BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 1L;

	private List stoDocumentoValidacionList;
	private List stoProcesosEjecutadosList;

	@Override
	protected String getSalirForward() {
		return "procesoSTOLiberacionRechazoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoSTOLiberacionRechazoSearchForm form = new ProcesoSTOLiberacionRechazoSearchForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		if (this.beanRegistroSeleccionado == null)
		  return this.getResourceMessage("errors.select.item");
		
		return "";
	}


	@Override
	protected List setFindAttributes() throws Exception {
		ProcesoSTOLiberacionRechazoService service = (ProcesoSTOLiberacionRechazoService) getBean("spusicc.procesoSTOLiberacionRechazoService");		
		ProcesoSTOLiberacionRechazoSearchForm f = (ProcesoSTOLiberacionRechazoSearchForm) this.formBusqueda;
		f.setFecha(DateUtil.convertDateToString(f.getFechaD()));
		Map params = new HashMap();
		
		params.put("codigoDocumento", f.getCodigoDocumento());
		params.put("codigoAccion", f.getAccion());
		params.put("codigoUsuario", f.getUsuario());
		params.put("fecha", f.getFecha());
		

		List procesosEjecutados = (List)service.getProcesosEjecutadosRechazoDocumentoByCriteria(params);
		this.stoProcesosEjecutadosList = procesosEjecutados;
		
		return procesosEjecutados;
	}


	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}


	public void guardar(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method and cleaned ");
		}
		
		
		String mensaje = "";

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoSTOService  procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		ProcesoSTOExecutionService service = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");

		HashMap objSeleccionado = (HashMap) this.beanRegistroSeleccionado;
		if(objSeleccionado == null){
			mensaje = "Seleccion un Elememnto,porfavor";
			this.addError( "Error : ", mensaje);
			return;
		}
		String codigoTipoDocumento = objSeleccionado.get("codigoDocumento").toString();

		String numeroProceso = objSeleccionado.get("numeroProceso").toString();;
		try {			
			Map params = new HashMap();
			params.put("usuario", usuario);
			params.put("numeroProceso", "");		
			List lista =  procesoSTOService.getDocumentoDigitadoPKByProceso(codigoTipoDocumento, numeroProceso);
			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais, codigoTipoDocumento, Constants.STO_ACCI_RECU_RECH );
			service.execute(accionTipoDocumento, params, lista) ;
			mensaje = this.getResourceMessage("procesoSTOLiberacionRechazoSearchAction.created");	
			this.addInfo("Info : ", mensaje);
		} catch (Exception e) {			
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoSTOSecuenciaValidacionService service = (MantenimientoSTOSecuenciaValidacionService) getBean("sto.mantenimientoSTOSecuenciaValidacionService");		
		ProcesoSTOLiberacionRechazoSearchForm f = (ProcesoSTOLiberacionRechazoSearchForm) this.formBusqueda;
		Map params = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		params.put("codigoPais", codigoPais);
		
		List tiposDocumento = (List)service.getTipoDocumentoList(params);
		this.stoDocumentoValidacionList = tiposDocumento;
		this.stoProcesosEjecutadosList = new ArrayList();
		
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSalir = false;
		this.mostrarBotonConsultar = false;
		f.setCodigoDocumento("");
		f.setFecha("");
		f.setUsuario("");
		f.setAccion("");
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the stoDocumentoValidacionList
	 */
	public List getStoDocumentoValidacionList() {
		return stoDocumentoValidacionList;
	}

	/**
	 * @param stoDocumentoValidacionList the stoDocumentoValidacionList to set
	 */
	public void setStoDocumentoValidacionList(List stoDocumentoValidacionList) {
		this.stoDocumentoValidacionList = stoDocumentoValidacionList;
	}

	/**
	 * @return the stoProcesosEjecutadosList
	 */
	public List getStoProcesosEjecutadosList() {
		return stoProcesosEjecutadosList;
	}

	/**
	 * @param stoProcesosEjecutadosList the stoProcesosEjecutadosList to set
	 */
	public void setStoProcesosEjecutadosList(List stoProcesosEjecutadosList) {
		this.stoProcesosEjecutadosList = stoProcesosEjecutadosList;
	}

	
}