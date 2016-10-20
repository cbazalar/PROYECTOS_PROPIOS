package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONCargarTerritorioUnidadGeograficaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.ProcesoFLXCargaManualConsultoraDeshabilitarForm;

@ManagedBean
@SessionScoped
public class ProcesoFLXCargaManualConsultoraDeshabilitarAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List procesoFlxCargarConsultorasErrorList;
	private String procesoFlxCargarConsultorasViewValida;
	private String attachment = "";
	private Boolean mostrarBotonValidar;
	private Boolean mostrarBotonGuardar;
	private Boolean mostrarDetalles;

	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		ProcesoFLXCargaManualConsultoraDeshabilitarForm f = (ProcesoFLXCargaManualConsultoraDeshabilitarForm) formProceso;
		this.mostrarDetalles = false;
		String mensaje = "";
		try {
			String codigoPeriodo = f.getCodigoPeriodo();
			String codigoPeriodoGeneral = f.getCodigoPeriodoFacturacion();

			if (StringUtils.isBlank(codigoPeriodo)) {
				mensaje = "El codigo de periodo es un campo requerido";
				this.addError("Error : ", mensaje);
				return;
			}

			if (Integer.parseInt(codigoPeriodo) <= Integer
					.parseInt(codigoPeriodoGeneral)) {
				mensaje = this
						.getResourceMessage("procesoFLXCargaManualLineaCreditoForm.process.error.campania");
				this.addError("Error : ", mensaje);
				return;
			}

			if (event != null) {
				f.setArchivo(event.getFile());
				setAttachment(event.getFile().getFileName());
			}
			this.uploadArchivo();
			this.cargar();
			this.mostrarDetalles = true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void cargar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}

		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ProcesoFLXCargaManualConsultoraDeshabilitarForm f = (ProcesoFLXCargaManualConsultoraDeshabilitarForm) this.formProceso;
			MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());

			int totalRegistros = service
					.cargarArchivoConsultorasDeshabilitarXLS(criteria);

			f.setNumRegistros(Integer.toString(totalRegistros));
			f.setNumRegistrosError("N");
			f.setNumRegistrosValidos("N");

			this.procesoFlxCargarConsultorasErrorList = new ArrayList();
			this.procesoFlxCargarConsultorasViewValida = Constants.NUMERO_UNO;

			f.setFlagBotonValidar(true);
			f.setFlagBotonProcesar(false);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void validar(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		try {
			this.mostrarBotonValidar = false;
			this.mostrarBotonGuardar = true;
			ProcesoFLXCargaManualConsultoraDeshabilitarForm f = (ProcesoFLXCargaManualConsultoraDeshabilitarForm) this.formProceso;
			MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			String codigoUsuario = usuario.getLogin();

			Map params = BeanUtils.describe(f);
			params.put("codigoUsuario", codigoUsuario);

			List resultados = service
					.executeValidarArchivoConsultorasDeshabilitar(params);
			int totalErrores = resultados.size();
			int totalValidos = Integer.parseInt(f.getNumRegistros())
					- totalErrores;
			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValidos(String.valueOf(totalValidos));
			this.procesoFlxCargarConsultorasErrorList = resultados;
			f.setFlagBotonValidar(false);
			f.setFlagBotonProcesar(true);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 * @param form
	 * @throws Exception
	 */
	public void uploadArchivo() {
		try {
			ProcesoFLXCargaManualConsultoraDeshabilitarForm f = (ProcesoFLXCargaManualConsultoraDeshabilitarForm) this.formProceso;
			UploadedFile archivo = f.getArchivo();
			f.setNombreArchivo(archivo.getFileName());
			log.debug((new StringBuilder()).append("Nombre Archivo Upload: ")
					.append(f.getNombreArchivo()).toString());
			InputStream is = archivo.getInputstream();
			FileOutputStream os = new FileOutputStream(new File(
					f.getDirectorioTemporal(), f.getNombreArchivo()));
			int bytesRead = 0;
			byte buffer[] = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			f.setArchivo(null);
			this.mostrarBotonValidar = true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * 
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoFLXCargaManualConsultoraDeshabilitarForm form = new ProcesoFLXCargaManualConsultoraDeshabilitarForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoFLXCargaManualConsultoraDeshabilitarForm f = (ProcesoFLXCargaManualConsultoraDeshabilitarForm) this.formProceso;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");

		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoUsuario", usuario.getLogin());
		service.executeProcesarArchivoConsultorasDeshabilitar(criteria);

		f.setFlagBotonValidar(false);
		f.setFlagBotonProcesar(false);
		return params;
	}

	public void procesarExt(ActionEvent evt) {
		String mensaje = "";
		try {
			ProcesoFLXCargaManualConsultoraDeshabilitarForm f = (ProcesoFLXCargaManualConsultoraDeshabilitarForm) formProceso;
			int errores = Integer.parseInt(f.getNumRegistrosError());
			if (errores > 0) {
				mensaje = this
						.getResourceMessage("procesoFLXCargaManualLineaCreditoForm.process.errores");
				this.addError("Error : ", mensaje);
				return;
			}

			executeProceso();

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	public void inicializando() {
		this.procesoFlxCargarConsultorasErrorList = new ArrayList();
		this.procesoFlxCargarConsultorasViewValida = "";
		this.mostrarBotonValidar = false;
		this.mostrarBotonGuardar = false;
		this.mostrarDetalles = false;
		this.mostrarBotonExecute = false;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoFLXCargaManualConsultoraDeshabilitarForm f = (ProcesoFLXCargaManualConsultoraDeshabilitarForm) this.formProceso;
		ProcesoZONCargarTerritorioUnidadGeograficaService service = (ProcesoZONCargarTerritorioUnidadGeograficaService) getBean("spusicc.procesoZONCargarTerritorioUnidadGeograficaService");
		f.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));
		f.setFlagBotonValidar(false);
		f.setFlagBotonProcesar(false);
		this.inicializando();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService ctrlFactservice = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = ctrlFactservice
				.getControlFacturacionById(criteria);

		f.setCodigoPeriodoFacturacion(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodo("");
	}

	/**
	 * @return the procesoFlxCargarConsultorasErrorList
	 */
	public List getProcesoFlxCargarConsultorasErrorList() {
		return procesoFlxCargarConsultorasErrorList;
	}

	/**
	 * @param procesoFlxCargarConsultorasErrorList
	 *            the procesoFlxCargarConsultorasErrorList to set
	 */
	public void setProcesoFlxCargarConsultorasErrorList(
			List procesoFlxCargarConsultorasErrorList) {
		this.procesoFlxCargarConsultorasErrorList = procesoFlxCargarConsultorasErrorList;
	}

	/**
	 * @return the procesoFlxCargarConsultorasViewValida
	 */
	public String getProcesoFlxCargarConsultorasViewValida() {
		return procesoFlxCargarConsultorasViewValida;
	}

	/**
	 * @param procesoFlxCargarConsultorasViewValida
	 *            the procesoFlxCargarConsultorasViewValida to set
	 */
	public void setProcesoFlxCargarConsultorasViewValida(
			String procesoFlxCargarConsultorasViewValida) {
		this.procesoFlxCargarConsultorasViewValida = procesoFlxCargarConsultorasViewValida;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the mostrarBotonValidar
	 */
	public Boolean getMostrarBotonValidar() {
		return mostrarBotonValidar;
	}

	/**
	 * @param mostrarBotonValidar
	 *            the mostrarBotonValidar to set
	 */
	public void setMostrarBotonValidar(Boolean mostrarBotonValidar) {
		this.mostrarBotonValidar = mostrarBotonValidar;
	}

	/**
	 * @return the mostrarBotonGuardar
	 */
	public Boolean getMostrarBotonGuardar() {
		return mostrarBotonGuardar;
	}

	/**
	 * @param mostrarBotonGuardar
	 *            the mostrarBotonGuardar to set
	 */
	public void setMostrarBotonGuardar(Boolean mostrarBotonGuardar) {
		this.mostrarBotonGuardar = mostrarBotonGuardar;
	}

	/**
	 * @return the mostrarDetalles
	 */
	public Boolean getMostrarDetalles() {
		return mostrarDetalles;
	}

	/**
	 * @param mostrarDetalles
	 *            the mostrarDetalles to set
	 */
	public void setMostrarDetalles(Boolean mostrarDetalles) {
		this.mostrarDetalles = mostrarDetalles;
	}

}