/*
 * 
 */
package biz.belcorp.ssicc.web.spisicc.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spisicc.InterfazIMPGeneracionDocumentosNumeroInternoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spisicc.form.InterfazIMPGeneracionDocumentosNumeroInternoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazIMPGeneracionDocumentosNumeroInternoAction extends BaseInterfazAbstractAction{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private static final String CODIGO_INTERFAZ = "IMP-P6";
	private static final String CODIGO_PROCESO_BATCH = "10";
	private List impTipoDocumentoList = new ArrayList();
	private String attachment = "";

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazIMPGeneracionDocumentosNumeroInternoForm f = new InterfazIMPGeneracionDocumentosNumeroInternoForm();
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		InterfazIMPGeneracionDocumentosNumeroInternoForm f = (InterfazIMPGeneracionDocumentosNumeroInternoForm)formInterfaz;
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa que se procesa actualmente  
		InterfazIMPGeneracionDocumentosNumeroInternoService interfazService = (InterfazIMPGeneracionDocumentosNumeroInternoService)getBean("spisicc.interfazIMPGeneracionDocumentosNumeroInternoService");
//		PedidoControlFacturacion controlFacturacion = interfazService.getControlFacturacionById(criteria);
		
		//String codigoProcesoBatch = CODIGO_PROCESO_BATCH;
//		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setFechaFacturacion("");
    	
		this.setImpTipoDocumentoList(interfazService.getTipoProcesoDocElectronico());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazIMPGeneracionDocumentosNumeroInternoForm f = (InterfazIMPGeneracionDocumentosNumeroInternoForm)form;
		InterfazIMPGeneracionDocumentosNumeroInternoService interfazService = (InterfazIMPGeneracionDocumentosNumeroInternoService)getBean("spisicc.interfazIMPGeneracionDocumentosNumeroInternoService");
		
		List listaInterfacesEncontradas = interfazService.getInterfacesDocElectronico(f.getTipoProceso());
		String []listaInterfacesSeleccionadas = new String[listaInterfacesEncontradas.size()];
		for(int i=0; i<listaInterfacesEncontradas.size(); i++){
			Map map = (Map)listaInterfacesEncontradas.get(i);
			String codigoInterfaz = (String)map.get("codigoInterfaz");			
			listaInterfacesSeleccionadas[i] = codigoInterfaz; 
		}
		params = super.prepareParamsBeforeExecute(params, f);
		params.put("listaInterfacesSeleccionadas", listaInterfacesSeleccionadas);
		params.put("indicadorDocumentosInternos", Constants.NRO_UNO);
		return params;
	}
	
	/**
	 * @param ev
	 */
	public void carga(FileUploadEvent ev){
		if(log.isDebugEnabled()){
			log.debug("carga");
		}
		InterfazIMPGeneracionDocumentosNumeroInternoService interfazService = (InterfazIMPGeneracionDocumentosNumeroInternoService)getBean("spisicc.interfazIMPGeneracionDocumentosNumeroInternoService");
		InterfazIMPGeneracionDocumentosNumeroInternoForm f = (InterfazIMPGeneracionDocumentosNumeroInternoForm)this.formInterfaz;
		if(StringUtils.isBlank(f.getSerieDocumento())){
			this.addWarn("Error : ", "'Numero de Serie' es requerido");
			return;
		}
		try {
			f.setArchivo(ev.getFile());
			this.setAttachment(ev.getFile().getFileName());
			
			ArrayList listCodigoDocumento = null;
			UploadedFile codigoFile = null;
			
			codigoFile = f.getArchivo();
			if(codigoFile!=null){
				if(StringUtils.isNotBlank(codigoFile.getFileName())){
					String extensionArchivo = obtenerExtensionArchivo(codigoFile.getFileName());
					if (extensionArchivo.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_TXT)){
						
						// recuperamos el fichero
						log.debug("Nombre Archivo Upload: " + codigoFile.getFileName());
						listCodigoDocumento = (ArrayList) uploadArchivo(codigoFile);				
						
					}		
				}			
			}
			
			if ((listCodigoDocumento!=null) && (listCodigoDocumento.size()>0)){
				interfazService.insertDocElectronico(listCodigoDocumento);
				String descripcion =String.valueOf(listCodigoDocumento.size());
				Object[] obj= new Object[1];
				obj[0]= descripcion;
	            this.addInfo("Info:",  this.getResourceMessage("interfazIMPGeneracionDocumentosNumeroInternoForm.archivo.insertar.exito", obj));
				
			}else{
				this.addWarn("Alerta:", this.getResourceMessage("interfazIMPGeneracionDocumentosNumeroInternoForm.archivo.existen.numero"));
			}
			
		} catch (Exception e) {
		
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception{
		log.debug("EXTENSION" + nombreArchivo.substring(nombreArchivo.length() - 3));
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	/**
	 * @param archivo
	 * @return
	 * @throws Exception
	 */
	private List uploadArchivo(UploadedFile archivo) throws Exception{

		ArrayList listaArchivo = new ArrayList();
		
		// recuperamos el fichero
		log.debug("Nombre Archivo Upload: " + archivo.getFileName());

		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		BufferedReader di = new BufferedReader(new InputStreamReader(is));
		String linea;
		
		while(true) {
			linea = di.readLine();
			if (linea == null)
				break;
			
			if(StringUtils.isNotBlank(linea.trim()))
				listaArchivo.add(linea.trim());
		}

		return listaArchivo;
	}

	/**
	 * @return
	 */
	public List getImpTipoDocumentoList() {
		return impTipoDocumentoList;
	}

	/**
	 * @param impTipoDocumentoList
	 */
	public void setImpTipoDocumentoList(List impTipoDocumentoList) {
		this.impTipoDocumentoList = impTipoDocumentoList;
	}

	/**
	 * @return
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}
