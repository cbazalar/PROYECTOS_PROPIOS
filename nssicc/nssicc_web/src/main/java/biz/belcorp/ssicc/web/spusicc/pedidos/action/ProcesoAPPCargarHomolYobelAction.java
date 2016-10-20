package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPCargarHomolYobelService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoAPPCargarHomolYobelForm;

@ManagedBean
@SessionScoped
public class ProcesoAPPCargarHomolYobelAction extends BaseProcesoAbstractAction {

	
	private static final long serialVersionUID = 1L;
	private List appCargarHomolYobelErroresList = new ArrayList();
	private DataTableModel dataTableResultado = new DataTableModel();
	private String attachment = "";
	private String viewValida = "";
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveFormProceso");
		}
		ProcesoAPPCargarHomolYobelForm form = new ProcesoAPPCargarHomolYobelForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		this.appCargarHomolYobelErroresList = new ArrayList();
		ProcesoAPPCargarHomolYobelForm f = (ProcesoAPPCargarHomolYobelForm)this.formProceso;
		ProcesoAPPCargarHomolYobelService service = (ProcesoAPPCargarHomolYobelService)getBean("spusicc.procesoAPPCargarHomolYobelService");
		UploadedFile archivo = f.getArchivo();	
		if(archivo==null)
			throw new Exception("Debe ingresar un archivo con extension .csv");
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		List lineas = new ArrayList();
		String linea            = "";
		String strNumSecu 		= "";
		String strCodZona 		= "";
		String strCodSeccion 	= "";
		String strCodTerritorio = "";
		
		while (true) {
			Map criteria = new HashMap();			
			linea = br.readLine();
			if (linea == null)
				break;						
			//log.debug(linea);
			StringTokenizer st = new StringTokenizer(linea, ",");
			try {
				strCodZona = st.nextToken();
				criteria.put("codigoZona", strCodZona);
			} catch (Exception e) {
				strCodZona = "";
			}
			try {
				strCodSeccion = st.nextToken();
				criteria.put("codigoSeccion", strCodSeccion);
			} catch (Exception e) {
				strCodSeccion = "";
			}		
			try {
				strCodTerritorio = st.nextToken();
				criteria.put("codigoTerritorio", strCodTerritorio);
			} catch (Exception e) {
				strCodTerritorio = "";
			}
			try {
				strNumSecu = st.nextToken();	
				// Debe ser un numero entero				
				Integer.parseInt(strNumSecu);
				criteria.put("numeroSecuencia", strNumSecu);				
			} catch (Exception e) {
				strNumSecu = "";
			}			
			// Todos los campos son obligatorios, si falta alguno, no se considera el registro
			if(!strNumSecu.equals("") && !strCodZona.equals("") && !strCodSeccion.equals("") && !strCodTerritorio.equals(""))
				lineas.add(criteria);
		}
		
		List erroresList = new ArrayList();
		List returnErroresList = new ArrayList();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		erroresList = service.insertaHomologadoYobel(lineas,usuario);
		if(erroresList.size() != 0){
			f.setFlagMostrarErrores(true);
			this.appCargarHomolYobelErroresList = new ArrayList();
			for(int i=0;i<erroresList.size();i++){
				Map temp = new HashMap();
				temp.put("descripcionError", erroresList.get(i));
				this.appCargarHomolYobelErroresList.add(temp);
			}
		}
		f.setFlagMostrarErrores(false);
		this.dataTableResultado = new DataTableModel( this.appCargarHomolYobelErroresList);
		
		setAttachment(null);
		f.setArchivo(null);
        return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ProcesoAPPCargarHomolYobelForm f = (ProcesoAPPCargarHomolYobelForm)formProceso;
		f.setFlagMostrarErrores(false);	
		this.mostrarPanelAdicionalProceso = true;
		this.appCargarHomolYobelErroresList = new ArrayList();
	}
	
	/**
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		try {
			ProcesoAPPCargarHomolYobelForm f = (ProcesoAPPCargarHomolYobelForm)formProceso;
			if(event!=null){
				f.setArchivo(event.getFile());
				setAttachment(f.getArchivo().getFileName());
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	public void limpiara(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'limpiar' method");
        }
        ProcesoAPPCargarHomolYobelService service = (ProcesoAPPCargarHomolYobelService)getBean("spusicc.procesoAPPCargarHomolYobelService");
        service.deleteTablaHomologacion();
        this.addInfo("Info:", this.getResourceMessage("registros.eliminados.exito"));
    }



	public String getViewValida() {
		return viewValida;
	}

	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

	public DataTableModel getDataTableResultado() {
		return dataTableResultado;
	}

	public void setDataTableResultado(DataTableModel dataTableResultado) {
		this.dataTableResultado = dataTableResultado;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getAppCargarHomolYobelErroresList() {
		return appCargarHomolYobelErroresList;
	}

	public void setAppCargarHomolYobelErroresList(
			List appCargarHomolYobelErroresList) {
		this.appCargarHomolYobelErroresList = appCargarHomolYobelErroresList;
	}
	
	

}