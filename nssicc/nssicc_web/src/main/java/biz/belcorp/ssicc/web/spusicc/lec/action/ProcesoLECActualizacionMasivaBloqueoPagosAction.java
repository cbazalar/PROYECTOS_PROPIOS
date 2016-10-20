package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECGenerarPagosService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECActualizacionMasivaBloqueoPagosForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECCargaDatosExcelForm;

@ManagedBean
@SessionScoped
public class ProcesoLECActualizacionMasivaBloqueoPagosAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = -4920667414197985184L;
	private static final String TIPO_PAGO_DEFAULT = "01";
	private static final String CODIGO_BLOQUEO = "3";
	private static final String CODIGO_DESBLOQUEO = "1";
	
	private List lecTipoPagoList;
	private List lecTipoMotivoList;
	private List lecProgramaList;
	private List lecActualizacionMasivaBloqueoPagosList;
	
	private boolean viewValidar = false;
	private boolean viewActualizar = false;
	private boolean viewPanelMotivo = false;
	
	private String attachment = "";

	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoLECActualizacionMasivaBloqueoPagosForm form = new ProcesoLECActualizacionMasivaBloqueoPagosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonExecute = false;
		this.viewValidar = false;
		this.viewActualizar = false;
		this.viewPanelMotivo = true;
		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoLECActualizacionMasivaBloqueoPagosForm f = (ProcesoLECActualizacionMasivaBloqueoPagosForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());
		
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		Map result = service.getPeriodoDefault();
		String codigoPeriodo = (String) result.get("codigoPeriodo");
		f.setCodigoPeriodo(codigoPeriodo);
		
		Map criteria = new HashMap();
		ProcesoLECGenerarPagosService procesoLECGenerarPagosService = (ProcesoLECGenerarPagosService) getBean("spusicc.procesoLECGenerarPagosService");
		this.lecTipoPagoList  = procesoLECGenerarPagosService.getTipoPago02(criteria);
		f.setTipoPago(TIPO_PAGO_DEFAULT);
		
		this.lecTipoMotivoList  = procesoLECGenerarPagosService.getTipoMotivoBloqueo(criteria);
		
	}
	
	
	/**
	 * Metodo de Validación del Proceso
	 * @param val
	 */
	public void validarProceso(ValueChangeEvent val) {
		String tipoProceso = (String)val.getNewValue();
		if (StringUtils.equals(tipoProceso, CODIGO_BLOQUEO)) 
			this.viewPanelMotivo = true;
		else
			this.viewPanelMotivo = false;
		return;
	}
	
	
	/**
	 * Evento donde se carga el archivo
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			this.viewValidar = false;
			this.viewActualizar = false;
			ProcesoLECActualizacionMasivaBloqueoPagosForm f = (ProcesoLECActualizacionMasivaBloqueoPagosForm) this.formProceso;
			if (event != null) {
				f.setArchivo(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				
				// Cargamos el archivo de la maquina del cliente al servidor
				this.uploadArchivo();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * Escribe el archivo al Servidor
	 */
	private void uploadArchivo() {
		try {
			ProcesoLECActualizacionMasivaBloqueoPagosForm f = (ProcesoLECActualizacionMasivaBloqueoPagosForm) this.formProceso;
			
			// recuperamos el fichero
			UploadedFile archivo = f.getArchivo();
			f.setNombreArchivo(archivo.getFileName());
			log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());

			// leemos el stream de entrada
			InputStream is = archivo.getInputstream();
			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente
			FileOutputStream os = new FileOutputStream(new File(
					f.getDirectorioTemporal(), f.getNombreArchivo()));
			// grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			f.setArchivo(null);
			
			this.viewValidar = true;
			this.viewActualizar = false;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	
	
	
	
	/* GET - SET */
	/**
	 * @return the lecTipoPagoList
	 */
	public List getLecTipoPagoList() {
		return lecTipoPagoList;
	}

	/**
	 * @param lecTipoPagoList the lecTipoPagoList to set
	 */
	public void setLecTipoPagoList(List lecTipoPagoList) {
		this.lecTipoPagoList = lecTipoPagoList;
	}

	
	/**
	 * @return the lecTipoMotivoList
	 */
	public List getLecTipoMotivoList() {
		return lecTipoMotivoList;
	}

	/**
	 * @param lecTipoMotivoList the lecTipoMotivoList to set
	 */
	public void setLecTipoMotivoList(List lecTipoMotivoList) {
		this.lecTipoMotivoList = lecTipoMotivoList;
	}

	/**
	 * @return the lecProgramaList
	 */
	public List getLecProgramaList() {
		return lecProgramaList;
	}

	/**
	 * @param lecProgramaList the lecProgramaList to set
	 */
	public void setLecProgramaList(List lecProgramaList) {
		this.lecProgramaList = lecProgramaList;
	}

	/**
	 * @return the lecActualizacionMasivaBloqueoPagosList
	 */
	public List getLecActualizacionMasivaBloqueoPagosList() {
		return lecActualizacionMasivaBloqueoPagosList;
	}

	/**
	 * @param lecActualizacionMasivaBloqueoPagosList the lecActualizacionMasivaBloqueoPagosList to set
	 */
	public void setLecActualizacionMasivaBloqueoPagosList(
			List lecActualizacionMasivaBloqueoPagosList) {
		this.lecActualizacionMasivaBloqueoPagosList = lecActualizacionMasivaBloqueoPagosList;
	}

	/**
	 * @return the viewValidar
	 */
	public boolean isViewValidar() {
		return viewValidar;
	}

	/**
	 * @param viewValidar the viewValidar to set
	 */
	public void setViewValidar(boolean viewValidar) {
		this.viewValidar = viewValidar;
	}

	/**
	 * @return the viewActualizar
	 */
	public boolean isViewActualizar() {
		return viewActualizar;
	}

	/**
	 * @param viewActualizar the viewActualizar to set
	 */
	public void setViewActualizar(boolean viewActualizar) {
		this.viewActualizar = viewActualizar;
	}

	/**
	 * @return the viewPanelMotivo
	 */
	public boolean isViewPanelMotivo() {
		return viewPanelMotivo;
	}

	/**
	 * @param viewPanelMotivo the viewPanelMotivo to set
	 */
	public void setViewPanelMotivo(boolean viewPanelMotivo) {
		this.viewPanelMotivo = viewPanelMotivo;
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

	
	
	
	
	
	
	
}