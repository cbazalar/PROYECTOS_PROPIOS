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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarCADDocumentoLegalMasivosService;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarCADDocumentoLegalMasivosForm;

/**
 * @author Sigcomt
 *
 */

@ManagedBean
@SessionScoped
public class ProcesoCCCCargarCADDocumentoLegalMasivosAction extends BaseCargaArchivoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6788266909756660919L;
	
	private List siccSociedadList = new ArrayList();
	private List cccTiposCargosAbonosDirectosDocleList = new ArrayList();
	private List cccErroresCargaMasivaList = new ArrayList();
	private String codigoProceso;
	private String codigoModulo;

	@Override
	protected void setViewAtributes() throws Exception {		
		if (log.isDebugEnabled()) {
			log.debug("JFA: Entering 'view' method");
		}	
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		this.codigoProceso = (String) this.parametrosPantalla.get("codigoProceso");
		this.codigoModulo = (String) this.parametrosPantalla.get("codigoModulo");
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");        				
		siccSociedadList = svc.getSociedadesByCodigoPais(pais.getCodigo());
		
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");		
		Map criteria = new HashMap();				
		cccTiposCargosAbonosDirectosDocleList = serviceCCC.getTipoCADDocumentoLegalList(criteria);
		
		
		ProcesoCCCCargarCADDocumentoLegalMasivosForm f = (ProcesoCCCCargarCADDocumentoLegalMasivosForm)this.formCargaArchivo;		
		f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
		f.setCodigoPais(pais.getCodigo());
		
		if(this.cccTiposCargosAbonosDirectosDocleList != null && this.cccTiposCargosAbonosDirectosDocleList.size() > 0){
			Base bas = (Base) this.cccTiposCargosAbonosDirectosDocleList.get(0);
			f.setTipoDocumentoLegal(bas.getCodigo());
		}
	}
	
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoCCCCargarCADDocumentoLegalMasivosForm form = new ProcesoCCCCargarCADDocumentoLegalMasivosForm();
		return form;
	}

	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'upload' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoCCCCargarCADDocumentoLegalMasivosForm f = (ProcesoCCCCargarCADDocumentoLegalMasivosForm) this.formCargaArchivo; 
		
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSociedad",f.getCodigoSociedad());
		
		ProcesoCCCCargarCADDocumentoLegalMasivosService service = (ProcesoCCCCargarCADDocumentoLegalMasivosService) getBean("spusicc.procesoCCCCargarCADDocumentoLegalMasivosService");		
		log.debug("JFA : Entering 'upload' method INIC ");
		f.setDirectorioTemporal(service.obtenerPathUploadCADDocumentoLegalMasivos(criteria));	
		
		return f;
	}

	@Override
	protected List setValidarAttributes(Map<String, Object> datos)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'validar' method");
		}			
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoCCCCargarCADDocumentoLegalMasivosForm f = (ProcesoCCCCargarCADDocumentoLegalMasivosForm) this.formCargaArchivo;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		String strMessage = null;	
		String numeroLote = null;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSociedad", f.getCodigoSociedad());
			
		
		datos.put("codigoPais", pais.getCodigo());
		datos.put("oidCodPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));
		datos.put("directorioTemporal", f.getDirectorioTemporal());
		datos.put("nombreArchivo", f.getNombreArchivo());
		datos.put("extensionArchivo", f.getExtensionArchivo());				
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();				
		datos.put("codigoUsuario", usuario.getLogin());				
						 
																												
		ProcesoCCCCargarCADDocumentoLegalMasivosService service = (ProcesoCCCCargarCADDocumentoLegalMasivosService) getBean("spusicc.procesoCCCCargarCADDocumentoLegalMasivosService");
		
		// Obteniendo el Numero de Lote												
		ConsultaCCCGenericoService serviceGenericoCCC = (ConsultaCCCGenericoService)getBean("spusicc.consultaCCCGenericoService");
		
		serviceGenericoCCC.getNumeroLote(datos);																
				
		if (log.isDebugEnabled()) {
			log.debug("JFA : Parametros " + datos.toString());	
		}						
																																				
		if (log.isDebugEnabled()) {
			log.debug("JFA : Llamando deleteTablasCargaCargosAbonosMasivos");
		}
		service.deleteTablasCargaCADDocumentoLegalMasivos(datos);
						
		if (log.isDebugEnabled()) {
			log.debug("JFA : Llamando executeValidarCargosAbonosMasivos");
		}
		
		service.executeValidarCADDocumentoLegalMasivos(datos);												
		String error = (String) datos.get("error");				
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Validando Errores");
		}
		
		if (error.equals("0")){
			this.mostrarListaCarga = false;
			this.errorValidar = false;
			strMessage = "procesoCCCCargarCADDocumentoLegalMasivosUpload.msg.CargaDatos.ok";					
			f.setNumeroLote((String)(datos.get("numeroLote")));					
			f.setCodigoModulo(this.codigoModulo);
			f.setCodigoProceso(this.codigoProceso);										
			f.setCodigoUsuario(usuario.getLogin());
			f.setCantidadRegistrosCargados((String) datos.get("cantidadRegistrosCargados"));
			f.setImporteRegistrosCargados((String) datos.get("importeRegistrosCargados"));
			this.addInfo("Información: ", this.getResourceMessage(strMessage));
							
		}
		else{	
			this.mostrarListaCarga = true;
			this.errorValidar = true;
			f.setFlagMostrarErrores(true);
												
			List list = service.getErroresCargaCADDocumentoLegalMasivos(datos);
			this.cccErroresCargaMasivaList = list;					
			strMessage = "procesoCCCCargarCADDocumentoLegalMasivosUpload.msg.CargaDatos.error";
			this.addError("Error: ", this.getResourceMessage(strMessage));
		}		
		
		return this.cccErroresCargaMasivaList;
	}

	@Override
	protected String getMensajeProcesarOK() {
		String mensaje = "procesoCCCCargarCADDocumentoLegalMasivosUpload.msg.procesa.ok";
		return this.getResourceMessage(mensaje);
	}

	@Override
	protected void setProcesarAttributes(Map<String, Object> datos)
			throws Exception {		

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}

		ProcesoCCCCargarCADDocumentoLegalMasivosService service = (ProcesoCCCCargarCADDocumentoLegalMasivosService) getBean("spusicc.procesoCCCCargarCADDocumentoLegalMasivosService");
		ProcesoCCCCargarCADDocumentoLegalMasivosForm f = (ProcesoCCCCargarCADDocumentoLegalMasivosForm) this.formCargaArchivo;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario"+usuario.getLogin());
		     
        datos.put("codigoPais",f.getCodigoPais());
        datos.put("codigoSociedad",f.getCodigoSociedad());        
        datos.put("tipoDocumentoLegal", f.getTipoDocumentoLegal());        
        datos.put("numeroLote", f.getNumeroLote());
        datos.put("codigoUsuario",usuario.getLogin());
        datos.put("codigoModulo",f.getCodigoModulo());
        datos.put("codigoProceso",f.getCodigoProceso());
                    								
		service.executeProcesarCADDocumentoLegalMasivos(datos);
		
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
	 * @return the cccTiposCargosAbonosDirectosDocleList
	 */
	public List getCccTiposCargosAbonosDirectosDocleList() {
		return cccTiposCargosAbonosDirectosDocleList;
	}

	/**
	 * @param cccTiposCargosAbonosDirectosDocleList the cccTiposCargosAbonosDirectosDocleList to set
	 */
	public void setCccTiposCargosAbonosDirectosDocleList(
			List cccTiposCargosAbonosDirectosDocleList) {
		this.cccTiposCargosAbonosDirectosDocleList = cccTiposCargosAbonosDirectosDocleList;
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
		
	
}
