/**
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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoCCCCargarPagosBancariosPorRegularizarMasivosAction extends BaseCargaArchivoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5872964633009566886L;
	private List siccSociedadList = new ArrayList();
	private List siccCuentaCorrienteList = new ArrayList();
	private String codigoProceso;
	private String codigoModulo;	
	private List cccErroresCargaMasivaList = new ArrayList();

	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("JFA: Entering 'view' method");
		}	
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		
		//Map para almacenar los parametros
		Map criteria = new HashMap();
		
		//Obteniendo el listado de Sociedades por Pais
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
												
		//Obteniedo el listado de las Cuentas Corrientes Bancarias	        		
		criteria.put("codigoPais", pais.getCodigo());
        ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");	        
		siccCuentaCorrienteList = serviceCCC.getCuentasCorrientesBancariasList(criteria); 
				
		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm f = (ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm)this.formCargaArchivo;
		this.codigoProceso = (String) this.parametrosPantalla.get("codigoProceso");
		this.codigoModulo = (String) this.parametrosPantalla.get("codigoModulo");
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoProceso(this.codigoProceso);
		f.setCodigoModulo(this.codigoModulo);
		f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
								
		if (log.isDebugEnabled()) {
			log.debug("JFA: Finalizando 'view' method");
		}			
	}
	
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm form = new ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm();
		return form;
	}

	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'upload' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();						
		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm f = (ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm) this.formCargaArchivo; 
		
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSociedad",f.getCodigoSociedad());
		
		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService service = (ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService) getBean("spusicc.procesoCCCCargarPagosBancariosPorRegularizarMasivosService");								
		f.setDirectorioTemporal(service.obtenerPathUpload(criteria));
		
		return f;
	
	}

	@Override
	protected List setValidarAttributes(Map<String, Object> datos)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'validar' method");
		}			
		
		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm f = (ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm) this.formCargaArchivo;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String strMessage = null;		
				
		datos.put("directorioTemporal", f.getDirectorioTemporal());
		datos.put("nombreArchivo", f.getNombreArchivo());
		datos.put("extensionArchivo", f.getExtensionArchivo());
		datos.put("codigoPais", pais.getCodigo());
		datos.put("codigoSociedad", f.getCodigoSociedad());
		datos.put("codigoCuentaCorrienteBancaria", f.getCodigoCuentaCorrienteBancaria());
									
		log.debug("datos__"+datos.toString());

		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService service = (ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService) getBean("spusicc.procesoCCCCargarPagosBancariosPorRegularizarMasivosService");
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Llamando deleteTablasCargaPagosBancariosPorRegularizarMasivos");
		}
		service.deleteTablasCargaPagosBancariosPorRegularizarMasivos();
						
		if (log.isDebugEnabled()) {
			log.debug("JFA Llamando executeValidarPagosBancariosPorRegularizarMasivos");
		}
		service.executeValidarPagosBancariosPorRegularizarMasivos(datos);
		String error = (String) datos.get("error");				
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Validando Errores");
		}
		
		if (error.equals("0")){
			this.mostrarListaCarga = false;
			this.errorValidar = false;
			strMessage = "procesoCCCCargarPagosBancariosPorRegularizarMasivosUpload.msg.CargaDatos.ok";
			this.addInfo("Información: ", this.getResourceMessage(strMessage));
							
		}
		else{	
			this.mostrarListaCarga = true;
			this.errorValidar = true;					
			f.setFlagMostrarErrores(true);
			List erroresList = service.getErroresCargaPagosBancariosPorRegularizarMasivos();
			this.cccErroresCargaMasivaList = erroresList;						
			strMessage = "procesoCCCCargarPagosBancariosPorRegularizarMasivosUpload.msg.CargaDatos.error";
			this.addError("Error: ", this.getResourceMessage(strMessage));
		}					

		return this.cccErroresCargaMasivaList;
	}

	@Override
	protected String getMensajeProcesarOK() {
		String mensaje = "procesoCCCCargarPagosBancariosPorRegularizarMasivosUpload.msg.procesa.ok";
		return this.getResourceMessage(mensaje);
		
	}

	@Override
	protected void setProcesarAttributes(Map<String, Object> datos)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}

		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService service = (ProcesoCCCCargarPagosBancariosPorRegularizarMasivosService) getBean("spusicc.procesoCCCCargarPagosBancariosPorRegularizarMasivosService");
		ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm f = (ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm) this.formCargaArchivo;	     
        
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario"+usuario.getLogin());
				
        datos.put("codigoUsuario",usuario.getLogin());
        datos.put("codigoPais",f.getCodigoPais());
        datos.put("codigoSociedad",f.getCodigoSociedad());        
        datos.put("codigoCuentaCorrienteBancaria",f.getCodigoCuentaCorrienteBancaria());
        datos.put("codigoModulo",f.getCodigoModulo());
        datos.put("codigoProceso",f.getCodigoProceso());        
        
        if (log.isDebugEnabled()) {
			log.debug("JFA Parameter Map : " + datos.toString());
		}
        
		service.executeProcesarPagosBancariosPorRegularizarMasivos(datos);
		
		return;	
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}

	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}

	/**
	 * @return the cccErroresCargaMasivaList
	 */
	public List getCccErroresCargaMasivaList() {
		return cccErroresCargaMasivaList;
	}

	/**
	 * @param cccErroresCargaMasivaList the cccErroresCargaMasivaList to set
	 */
	public void setCccErroresCargaMasivaList(List cccErroresCargaMasivaList) {
		this.cccErroresCargaMasivaList = cccErroresCargaMasivaList;
	}
	
}
