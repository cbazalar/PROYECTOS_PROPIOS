/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosMasivosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarPagosBancariosMasivosForm;



@ManagedBean
@SessionScoped
public class ProcesoCCCCargarPagosBancariosMasivosAction extends BaseCargaArchivoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List siccCuentaCorrienteList = new ArrayList();
	private String numeroLote = "";
	
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoCCCCargarPagosBancariosMasivosForm form = new ProcesoCCCCargarPagosBancariosMasivosForm();
		return form;
	}

	

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}	
		ProcesoCCCCargarPagosBancariosMasivosForm f = (ProcesoCCCCargarPagosBancariosMasivosForm)this.formCargaArchivo;
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		//Map para almacenar los parametros
		Map criteria = new HashMap();
																
		//Obteniedo el listado de las Cuentas Corrientes Bancarias	        		
		criteria.put("codigoPais", pais.getCodigo());
        ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");	        
		this.setSiccCuentaCorrienteList(serviceCCC.getCuentasCorrientesBancariasList(criteria));
		this.numeroLote = "";
		
	}
	

	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'upload' method");
		}
		ProcesoCCCCargarPagosBancariosMasivosForm f = (ProcesoCCCCargarPagosBancariosMasivosForm)this.formCargaArchivo;
		ProcesoCCCCargarPagosBancariosMasivosService service = (ProcesoCCCCargarPagosBancariosMasivosService) getBean("spusicc.procesoCCCCargarPagosBancariosMasivosService");
								
		f.setDirectorioTemporal(service.obtenerPathUpload(criteria));
		return f;
	}

	
	@Override
	protected List setValidarAttributes(Map<String, Object> datos) 	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'validar' method");
		}			
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ProcesoCCCCargarPagosBancariosMasivosForm f = (ProcesoCCCCargarPagosBancariosMasivosForm)this.formCargaArchivo;
		String strMessage = null;		
		datos.put("tipoCarga", f.getTipoCarga());
		datos.put("directorioTemporal", f.getDirectorioTemporal());
		datos.put("nombreArchivo", f.getNombreArchivo());
		datos.put("extensionArchivo", f.getExtensionArchivo());
		datos.put("codigoPais", pais.getCodigo());
		
		ProcesoCCCCargarPagosBancariosMasivosService service = (ProcesoCCCCargarPagosBancariosMasivosService) getBean("spusicc.procesoCCCCargarPagosBancariosMasivosService");
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Llamando deleteTablasCargaPagosBancariosMasivos");
		}
		service.deleteTablasCargaPagosBancariosMasivos();
						
		if (log.isDebugEnabled()) {
			log.debug("JFA Llamando executeValidarPagosBancariosMasivos");
		}
		service.executeValidarPagosBancariosMasivos(datos);
		String error = (String) datos.get("error");				
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Validando Errores");
		}
		List erroresList = new ArrayList();
		if (error.equals("0")){
			this.mostrarListaCarga = false;
			this.errorValidar = false;
			strMessage = "procesoCCCCargarPagosBancariosMasivosUpload.msg.CargaDatos.ok";
			f.setCantidadRegistrosCargados((String) datos.get("cantidadRegistrosCargados"));
			f.setImporteRegistrosCargados((String) datos.get("importeRegistrosCargados"));
			f.setFlagValidar(false);
			f.setFlagProcesar(true);
			this.addInfo("Información: ", this.getResourceMessage(strMessage));		
							
		}
		else{		
			f.setFlagMostrarErrores(true);
			this.mostrarListaCarga = true;
			this.errorValidar = true;
			erroresList = service.getErroresCargaPagosBancariosMasivos();
			strMessage = "procesoCCCCargarPagosBancariosMasivosUpload.msg.CargaDatos.error";
			this.addError("Error: ", this.getResourceMessage(strMessage));	
			return erroresList;
		}
		return erroresList;
		
	}
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setProcesarAttributes(java.util.Map)
     */
    protected void setProcesarAttributes(Map<String, Object> datos) throws Exception {
    	if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}
    	Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ProcesoCCCCargarPagosBancariosMasivosService service = (ProcesoCCCCargarPagosBancariosMasivosService) getBean("spusicc.procesoCCCCargarPagosBancariosMasivosService");
		ProcesoCCCCargarPagosBancariosMasivosForm f = (ProcesoCCCCargarPagosBancariosMasivosForm)this.formCargaArchivo;
		datos.put("codigoPais", pais.getCodigo());                
        datos.put("codigoBanco",f.getCodigoBanco());
        datos.put("tipoCarga",f.getTipoCarga()); 
        
        if (log.isDebugEnabled()) {
			log.debug("JFA Parameter Map : " + datos.toString());
		}
        
		service.executeProcesarPagosBancariosMasivos(datos);
		this.numeroLote = (String) datos.get("numeroLote");
		 
        f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
		return;
		
	}
	
    @Override
	protected String getMensajeProcesarOK() {
    	String mensaje = "procesoCCCCargarPagosBancariosMasivosUpload.msg.procesa.ok";
		return this.getResourceMessage(mensaje, new Object[]{this.numeroLote});
	}
	
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
}