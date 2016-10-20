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
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaMasivaFletesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDCargaMasivaMontoMininoForm;

@ManagedBean  
@SessionScoped
public class ProcesoPEDCargaMasivaMontoMininoAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 496517609966572368L;
	
	private String attachment;
	private List cargaMasivaMontoMinList;
	private DataTableModel dataTableResultado;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDCargaMasivaMontoMininoForm form = new ProcesoPEDCargaMasivaMontoMininoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoPEDCargaMasivaMontoMininoForm f = (ProcesoPEDCargaMasivaMontoMininoForm)this.formProceso;
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
        ProcesoPEDCargaMasivaFletesService service = (ProcesoPEDCargaMasivaFletesService)getBean("spusicc.procesoPEDCargaMasivaFletesService");
        params.put("codigoUsuario", usuario.getLogin());
        service.executeActualizarCargaMasivaMontoMinimo(params);
        f.setFlagBotonValidar(true);
        f.setFlagBotonActualizar(false);
        this.mostrarPanelAdicionalProceso= true;         
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoPEDCargaMasivaMontoMininoForm f = (ProcesoPEDCargaMasivaMontoMininoForm)this.formProceso;	
		f.setFlagBotonValidar(false);
		this.mostrarBotonExecute = false;
		this.mostrarPanelAdicionalProceso= false; 
        f.setFlagBotonActualizar(false);
		
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			ProcesoPEDCargaMasivaMontoMininoForm f = (ProcesoPEDCargaMasivaMontoMininoForm)this.formProceso;			
			Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();		        
	        ProcesoPEDCargaMasivaFletesService service = (ProcesoPEDCargaMasivaFletesService)getBean("spusicc.procesoPEDCargaMasivaFletesService");
	        f.setArchivo(event.getFile());
			this.attachment=event.getFile().getFileName();
			
			InputStream is = f.getArchivo().getInputstream();
			f.setNombreArchivo(f.getArchivo().getFileName());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			List lineas = new ArrayList();
			String linea = "";
			String strCodZona = "";
			String strNivel1="";
			String strNivel2="";
			String strNivel3="";
			String strMontoMonimal = "";
			String strTipoCliente= "";
			
			while (true) {
				Map criteria = new HashMap();
				linea = br.readLine();
				if (linea == null)
					break;
				StringTokenizer st = new StringTokenizer(linea, ",");
				try {
					strNivel1 = st.nextToken().trim();
					criteria.put("nivelV1", strNivel1);					
				} catch (Exception e) {
					strNivel1 = "";
				}
				try {
					strNivel2 = st.nextToken().trim();
					criteria.put("nivelV2", strNivel2);
				} catch (Exception e) {
					strNivel2 = "";
				}
				try {
					strNivel3 = st.nextToken().trim();
					criteria.put("nivelV3", strNivel3);
				} catch (Exception e) {
					strNivel3 = "";
				}
				try {
					strMontoMonimal = st.nextToken().trim();
					criteria.put("montoNomimal", strMontoMonimal);
				} catch (Exception e) {
					strMontoMonimal = "";
				}
				try {
					strTipoCliente = st.nextToken().trim();
					criteria.put("tipoCliente", strTipoCliente);
				} catch (Exception e) {
					strTipoCliente = "";
				}
				try {
					strCodZona = st.nextToken().trim();
					criteria.put("codigoZona", strCodZona);
				} catch (Exception e) {
					strCodZona = "";
				}			
				// Todos los campos son obligatorios, si falta alguno, no se considera el registro
				if(StringUtils.isNotBlank(strNivel1) && StringUtils.isNotBlank(strNivel2) && StringUtils.isNotBlank(strNivel3) &&
						StringUtils.isNotBlank(strMontoMonimal) && StringUtils.isNotBlank(strTipoCliente) && StringUtils.isNotBlank(strCodZona))				
					lineas.add(criteria);
			}
			
			Map resultados = service.cargarArchivoCSVMontoMinimo(lineas,usuario);
	        f.setNumRegistros((String)resultados.get("totalRegistros"));
	        f.setNumRegistrosError("N");
	        f.setNumRegistrosValido("N");
	        f.setFlagBotonValidar(true);	
	        f.setFlagBotonActualizar(false);
	        this.mostrarPanelAdicionalProceso= true; 
	        this.cargaMasivaMontoMinList = new ArrayList();
	        this.dataTableResultado=new DataTableModel(this.cargaMasivaMontoMinList);
			
		} catch (Exception e) {
			this.obtieneMensajeErrorException(e);
		}
		
	}
	
	//Valida el archivo CSV subido
	public void validar(ActionEvent event){
		try {
			ProcesoPEDCargaMasivaMontoMininoForm f = (ProcesoPEDCargaMasivaMontoMininoForm)this.formProceso;
			String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
	        Map params = new HashMap();
	        params.put("codigoUsuario", codigoUsuario);        
	        ProcesoPEDCargaMasivaFletesService service = (ProcesoPEDCargaMasivaFletesService)getBean("spusicc.procesoPEDCargaMasivaFletesService");
	        List resultados = service.executeValidarCargaMasivaMontoMinimo(params);
	        int totalErrores = resultados.size();
	        int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
	        f.setNumRegistrosError(String.valueOf(totalErrores));
	        f.setNumRegistrosValido(String.valueOf(totalValidos));
	        f.setFlagBotonValidar(false);
	        if(totalErrores==0)
	        	f.setFlagBotonActualizar(true);
	        else
	        	f.setFlagBotonActualizar(false);
	        this.mostrarPanelAdicionalProceso= true;         
	        this.cargaMasivaMontoMinList =  resultados;
	        this.dataTableResultado = new DataTableModel(this.cargaMasivaMontoMinList);
			
		} catch (Exception e) {
			this.obtieneMensajeErrorException(e);
		}        
	}
	
	@Override
	public String setValidarConfirmar(String accion) {		
		return null;
	}
	

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the cargaMasivaMontoMinList
	 */
	public List getCargaMasivaMontoMinList() {
		return cargaMasivaMontoMinList;
	}

	/**
	 * @param cargaMasivaMontoMinList the cargaMasivaMontoMinList to set
	 */
	public void setCargaMasivaMontoMinList(List cargaMasivaMontoMinList) {
		this.cargaMasivaMontoMinList = cargaMasivaMontoMinList;
	}

	/**
	 * @return the dataTableResultado
	 */
	public DataTableModel getDataTableResultado() {
		return dataTableResultado;
	}

	/**
	 * @param dataTableResultado the dataTableResultado to set
	 */
	public void setDataTableResultado(DataTableModel dataTableResultado) {
		this.dataTableResultado = dataTableResultado;
	}
	
	
	
	

}
