package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECTarjetaPagoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.lec.form.ConsultaLETTarjetasPagoForm;


@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ConsultaLETTarjetasPagoAction extends BaseReporteAbstractAction {
	

	private static final long serialVersionUID = 1L;
	private String formatoExportacion1;
	private List recCodigoLiderArchivoList;
	private List recNumeroTarjetaArchivoList;
	private List lecConsultoraTarjetaPagoList;
	private List lecEstadoTarjeList;
	private String codigoLider;
	private String numeroTarjeta;
	private String estadoTarjeta;
	private String archivoLider;
	private String archivoNumeroTarjeta;
	private String attachment = "";
	
	/**
	 * Metodo Invocador de metodos para
	 * Cargar Archivo
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			ConsultaLETTarjetasPagoForm f = (ConsultaLETTarjetasPagoForm) this.formReporte;
			if (event != null) {
				// f.setArchivo(event.getFile());
				f.setArchivoCodigoLider(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.validar();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * Metodo Invocador de metodos para
	 * Cargar Archivo
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload1(FileUploadEvent event){
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			ConsultaLETTarjetasPagoForm f = (ConsultaLETTarjetasPagoForm) this.formReporte;
			if (event != null) {
				// f.setArchivo(event.getFile());
				f.setArchivoNumeroTarjeta(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.validar();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Reemplaza busqueda
	 * @param evt
	 */
	public void findTarjetaPago(ActionEvent evt) {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		String mensaje = "";
		try{			
			ConsultaLETTarjetasPagoForm consultaLETTPForm = (ConsultaLETTarjetasPagoForm) this.formReporte;
					
			Map params = new HashMap();
						
			params.put("codigoPais", consultaLETTPForm.getCodigoPais());
			
			if(consultaLETTPForm.getEstadoTarjeta()!=null && consultaLETTPForm.getEstadoTarjeta().trim().length()>0){
			params.put("estadoTarjeta", "AND L.LEST_COD_ESTA like '%"+ consultaLETTPForm.getEstadoTarjeta().trim()+"%'");		
			}
					
			List lider = this.recCodigoLiderArchivoList; 
			
			List numeroTarjeta = this.recNumeroTarjetaArchivoList;

			if(lider != null && lider.size() > 0)
			{
				String[] listaCodigoCliente = new String[lider.size()];
				
				for (int i = 0; i < lider.size(); i++) {
					listaCodigoCliente[i] = lider.get(i).toString();
				}			
				params.put("codigoLider", obtieneCondicion(listaCodigoCliente, "m.COD_LIDE", "'"));
			}
			else
			{
				if(consultaLETTPForm.getCodigoLider()!=null && consultaLETTPForm.getCodigoLider().trim().length()>0){
					params.put("codigoLider", "AND m.COD_LIDE like '%"+ consultaLETTPForm.getCodigoLider().trim()+"%'");
				}else{
					params.put("codigoLider", "AND (m.COD_LIDE like '%%' or m.COD_LIDE is null)");
				}
				
			}
			
			if(numeroTarjeta != null && numeroTarjeta.size() > 0)
			{
				String[] listaNumeroTarjeta = new String[numeroTarjeta.size()];
				
				for (int i = 0; i < numeroTarjeta.size(); i++) {
					listaNumeroTarjeta[i] = numeroTarjeta.get(i).toString();
				}			
				params.put("numeroTarjeta", obtieneCondicion(listaNumeroTarjeta, "l.num_tarj", "'"));
			}
			else
			{
				if(consultaLETTPForm.getNumeroTarjeta()!=null && consultaLETTPForm.getNumeroTarjeta().trim().length()>0){
				params.put("numeroTarjeta", "AND l.num_tarj like '%"+ consultaLETTPForm.getNumeroTarjeta().trim()+"%'");		
			}
			}
//			this.recCodigoLiderArchivoList = new ArrayList();
//			this.recNumeroTarjetaArchivoList = new ArrayList();

			
			MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService");
			List lista = service.getLecConsultaTarjetasPago(params);
			

					
			if ((lista == null) ||(lista.size() == 0)){
				mensaje  = this.getResourceMessage("errors.datos.fuentes.busqueda");
				this.addError("Error : ", mensaje);
			}
			this.lecConsultoraTarjetaPagoList = lista;
			this.listaBusqueda = this.lecConsultoraTarjetaPagoList;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			
		}catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));	
		}
		
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * Carga el archivo de C?digos de Consultora
	 * 
	 */
	public void validar() {
		log.debug("ReporteRECPedidosRobadosAction - validar");			
		String mensaje = "";
		try {

			ConsultaLETTarjetasPagoForm f = (ConsultaLETTarjetasPagoForm) this.formReporte;
			ConsultaLETTarjetasPagoForm oldForm = (ConsultaLETTarjetasPagoForm) this.formReporte;

			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			
			String estadoTarjeta = f.getEstadoTarjeta();
			String codigoLider = f.getCodigoLider();
			String numeroTarjeta = f.getNumeroTarjeta();
			
			List listaCodigosLider = new ArrayList();
			List listaNumeroTarjeta = new ArrayList();
			
			UploadedFile archivoLider = f.getArchivoCodigoLider();
			UploadedFile archivoNumeroTarjeta = f.getArchivoNumeroTarjeta();
			
			if(f.getArchivoCodigoLider()!=null){
				InputStream is = archivoLider.getInputstream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String linea;
				int cont = 0;
				while(true) {
					linea = br.readLine();
					if (linea == null)
						break;
					
					if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
						listaCodigosLider.add(linea.trim());
					}
					else
					{
						cont++;
					}
				}
				
				if(cont > 0){				
					mensaje = this.getResourceMessage("consultaLECTarjetasPagoForm.errors.datos.no.numericos");
					this.addError("Error : ", mensaje);
				}
			}
			
			if(f.getArchivoNumeroTarjeta()!=null){
				InputStream is = archivoNumeroTarjeta.getInputstream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String linea;
				int cont = 0;
				while(true) {
					linea = br.readLine();
					if (linea == null)
						break;
					
					if(StringUtils.isNotBlank(linea.trim()) && StringUtils.isNumeric(linea.trim())){
						listaNumeroTarjeta.add(linea.trim());
					}
					else
					{
						cont++;
					}
				}
				
				if(cont > 0){
					mensaje = this.getResourceMessage("onsultaLECTarjetasPagoForm.errors.datos.no.numericos");
					this.addError("Error : ", mensaje);
				}
			}
			this.recCodigoLiderArchivoList = listaCodigosLider;
			this.recNumeroTarjetaArchivoList = listaNumeroTarjeta;
			
			this.codigoLider = codigoLider;
			this.numeroTarjeta = numeroTarjeta;
			this.estadoTarjeta = estadoTarjeta;
			this.archivoLider = archivoLider.getFileName();
			this.archivoNumeroTarjeta = archivoNumeroTarjeta.getFileName();

	
			
		} catch (Exception e) {
			this.addError("Error : " , this.obtieneMensajeErrorException(e));
		}
	}
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaLETTarjetasPagoForm form = new ConsultaLETTarjetasPagoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		String retorno="";
		
		if ("XLS".equals(this.formatoExportacion)){
			retorno = "consultaLETTarjetasPagoXLS";
		}
		
		return retorno;	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ConsultaLETTarjetasPagoForm consultaLETTPForm = (ConsultaLETTarjetasPagoForm) this.formReporte;
		consultaLETTPForm.setFormatoExportacion(this.formatoExportacion);
		this.formatoExportacion1 = this.formatoExportacion;

		params.put("codigoPais", consultaLETTPForm.getCodigoPais());
		
		params.put("estadoTarjeta", "AND L.LEST_COD_ESTA like '%"+ consultaLETTPForm.getEstadoTarjeta().trim()+"%'");		

		List lider = this.recCodigoLiderArchivoList;
		
		List numeroTarjeta = this.recNumeroTarjetaArchivoList;


		if(lider != null && lider.size() > 0)
		{
			String[] listaCodigoCliente = new String[lider.size()];
			
			for (int i = 0; i < lider.size(); i++) {
				listaCodigoCliente[i] = lider.get(i).toString();
			}			
			params.put("codigoLider", obtieneCondicion(listaCodigoCliente, "m.COD_LIDE", "'"));
		}
		else
		{
			if(consultaLETTPForm.getCodigoLider()!=null && consultaLETTPForm.getCodigoLider().trim().length()>0){
				params.put("codigoLider", "AND m.COD_LIDE like '%"+ consultaLETTPForm.getCodigoLider().trim()+"%'");
			}else{
				params.put("codigoLider", "AND (m.COD_LIDE like '%%' or m.COD_LIDE is null)");
			}
			
		}
		
		if(numeroTarjeta != null && numeroTarjeta.size() > 0)
		{
			String[] listaNumeroTarjeta = new String[numeroTarjeta.size()];
			
			for (int i = 0; i < numeroTarjeta.size(); i++) {
				listaNumeroTarjeta[i] = numeroTarjeta.get(i).toString();
			}			
			params.put("numeroTarjeta", obtieneCondicion(listaNumeroTarjeta, "l.num_tarj", "'"));
		}
		else
		{
			params.put("numeroTarjeta", "AND l.num_tarj like '%"+ consultaLETTPForm.getNumeroTarjeta().trim()+"%'");		
		}
		
		this.recCodigoLiderArchivoList = new ArrayList();
		this.recNumeroTarjetaArchivoList = new ArrayList();
		return params;
}

	@Override
	protected void setViewAtributes() throws Exception {

			ConsultaLETTarjetasPagoForm consultaLETTPForm = (ConsultaLETTarjetasPagoForm) this.formReporte;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			this.formatoExportacion  ="XLS";
			// Carga de los Periodos
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Map criteria = new HashMap();
			criteria.put("codigoPais",pais.getCodigo());
			
			MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService"); 
			this.lecEstadoTarjeList = service.getEstadoTarjetaPago();
			consultaLETTPForm.setEstadoTarjeta("");
			this.mostrarReporteXLS = false;
			this.mostrarReportePDF = false;
			this.mostrarListaBusqueda = true;
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/**
	 * @return the recCodigoLiderArchivoList
	 */
	public List getRecCodigoLiderArchivoList() {
		return recCodigoLiderArchivoList;
	}

	/**
	 * @param recCodigoLiderArchivoList the recCodigoLiderArchivoList to set
	 */
	public void setRecCodigoLiderArchivoList(List recCodigoLiderArchivoList) {
		this.recCodigoLiderArchivoList = recCodigoLiderArchivoList;
	}

	/**
	 * @return the recNumeroTarjetaArchivoList
	 */
	public List getRecNumeroTarjetaArchivoList() {
		return recNumeroTarjetaArchivoList;
	}

	/**
	 * @param recNumeroTarjetaArchivoList the recNumeroTarjetaArchivoList to set
	 */
	public void setRecNumeroTarjetaArchivoList(List recNumeroTarjetaArchivoList) {
		this.recNumeroTarjetaArchivoList = recNumeroTarjetaArchivoList;
	}

	/**
	 * @return the lecConsultoraTarjetaPagoList
	 */
	public List getLecConsultoraTarjetaPagoList() {
		return lecConsultoraTarjetaPagoList;
	}

	/**
	 * @param lecConsultoraTarjetaPagoList the lecConsultoraTarjetaPagoList to set
	 */
	public void setLecConsultoraTarjetaPagoList(List lecConsultoraTarjetaPagoList) {
		this.lecConsultoraTarjetaPagoList = lecConsultoraTarjetaPagoList;
	}

	/**
	 * @return the lecEstadoTarjeList
	 */
	public List getLecEstadoTarjeList() {
		return lecEstadoTarjeList;
	}

	/**
	 * @param lecEstadoTarjeList the lecEstadoTarjeList to set
	 */
	public void setLecEstadoTarjeList(List lecEstadoTarjeList) {
		this.lecEstadoTarjeList = lecEstadoTarjeList;
	}

	/**
	 * @return the codigoLider
	 */
	public String getCodigoLider() {
		return codigoLider;
	}

	/**
	 * @param codigoLider the codigoLider to set
	 */
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}

	/**
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * @return the estadoTarjeta
	 */
	public String getEstadoTarjeta() {
		return estadoTarjeta;
	}

	/**
	 * @param estadoTarjeta the estadoTarjeta to set
	 */
	public void setEstadoTarjeta(String estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}

	/**
	 * @return the archivoLider
	 */
	public String getArchivoLider() {
		return archivoLider;
	}

	/**
	 * @param archivoLider the archivoLider to set
	 */
	public void setArchivoLider(String archivoLider) {
		this.archivoLider = archivoLider;
	}

	/**
	 * @return the archivoNumeroTarjeta
	 */
	public String getArchivoNumeroTarjeta() {
		return archivoNumeroTarjeta;
	}

	/**
	 * @param archivoNumeroTarjeta the archivoNumeroTarjeta to set
	 */
	public void setArchivoNumeroTarjeta(String archivoNumeroTarjeta) {
		this.archivoNumeroTarjeta = archivoNumeroTarjeta;
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
	 * @return the formatoExportacion1
	 */
	public String getFormatoExportacion1() {
		return formatoExportacion1;
	}

	/**
	 * @param formatoExportacion1 the formatoExportacion1 to set
	 */
	public void setFormatoExportacion1(String formatoExportacion1) {
		this.formatoExportacion1 = formatoExportacion1;
	}	
	
	
}