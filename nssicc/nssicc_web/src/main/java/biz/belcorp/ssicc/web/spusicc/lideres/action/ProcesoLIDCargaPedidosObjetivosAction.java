package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDCargaPedidosObjetivosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoLIDCargaPedidosObjetivosAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 7453754370038131136L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private String indCargar;
	private Boolean mostrarPrimeraFase;
	private String attachment = "";

	/**
	 * Valida primera fase
	 */
	public void validarPrimeraFase() {
		ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;

		String[] codigoRegion = f.getCodigoRegion();
		String mensaje = null;
		if (codigoRegion != null) {
			this.mostrarPrimeraFase = true;
			// this.mostrarBotonExecute = true;
		} else {
			mensaje = this
					.getResourceMessage("mantenimientoMENIngresoGerenteZonalesForm.codigoRegion.requerided");
			this.addError("Error : ", mensaje);
		}
	}

	/**
	 * Carga , valida el excel
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
			log.debug("Entering 'validar' method");
		}
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;
			// obtenemos el servicio
			ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService service = (ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService) getBean("spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoService");
			// Cargamos el archivo de la maquina del cliente al servidor
			// uploadArchivo();
			String extensionArchivo = obtenerExtensionArchivo(f
					.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);

			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoMarca", f.getCodigoMarca());
			criteria.put("codigoCanal", f.getCodigoCanal());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("usuario", usuario);
			criteria.put("mensajeError", null);
			// validamos el archivo excel
			boolean isValido = service.validarArchivoExcel(criteria);
			String mensaje = null;
			if (isValido) {
				try {

					log.debug("ejecutar validacion si es ok se actualiza tabla, sino se envia mensaje");
					service.executeCargaArchivoExcel(criteria);
					String mensajeError = (String) criteria.get("mensajeError");
					if (StringUtils.isNotEmpty(mensajeError))
						throw new Exception(mensajeError);

				} catch (Exception e) {
					mensaje = this
							.getResourceMessage("procesoLIDCargaPedidosObjetivosForm.cabecera.error");
					this.addError("Error : ", mensaje);
					borrarFichero(f.getDirectorioTemporal(),
							f.getNombreArchivo());
					return;
				}
				this.indCargar = Constants.NUMERO_CERO;
				mensaje = this
						.getResourceMessage("procesoLIDCargaPedidosObjetivosForm.proceso.ok");
				this.addInfo("Info : ", mensaje);
			} else {
				mensaje = this
						.getResourceMessage("procesoLIDCargaPedidosObjetivosForm.archivo.novalido");
				this.addError("Error : ", mensaje);
			}
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;
			if (event != null) {
				// f.setArchivo(event.getFile());
				f.setArchivo(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
				this.cargar();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * carga el archivo al temporal
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() {

		try {
			ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;

			// recuperamos el fichero
			UploadedFile archivo = f.getArchivo();
			f.setNombreArchivo(archivo.getFileName());
			log.debug("Archivo Upload: " + f.getArchivo());
			log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
			// leyemos el stream de entrada
			InputStream is = archivo.getInputstream();
			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente
			log.debug("f.getDirectorioTemporal() " + f.getDirectorioTemporal());
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
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * obtiene la extension del archivo
	 * 
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * elimina el fichero
	 * 
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		} catch (Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
		}
	}

	/**
	 * Valida que las regiones selecionadas no hayan cerrado en el periodo
	 * sellecionado
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void validarRegiones() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarRegiones' method");
		}
		try {
			ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			ReporteService reporteService = (ReporteService) this
					.getBean("scsicc.reporteService");
			Integer oidPeriodo = new Integer(reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
			Map params = new HashMap();
			params.put("oidPeriodo", oidPeriodo);
			params.put("codigoRegion", f.getCodigoRegion());
			params.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_REGION);

			ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService service = (ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService) getBean("spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoService");

			List listRegiones = service.getRegionesCerradas(params);
			String mensaje = null;
			if (listRegiones.size() > 0) {

				String regiones = getRegiones(listRegiones);

				mensaje = this
						.getResourceMessage("procesoLIDCargaPedidosObjetivosForm.regiones.cerradas"
								+ " " + regiones);
				this.addError("Error : ", mensaje);
				this.indCargar = Constants.NUMERO_CERO;// SE DESHABILITA LA CARGA DEL
													// EXCEL
			} else
				this.indCargar = Constants.NUMERO_UNO;// SE HABILITA LA CARGA DEL
													// EXCEL
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Devulve un String concatenando todas las regiones que se encuentren en la
	 * lista
	 * 
	 * @param listRegiones
	 * @return
	 */
	private String getRegiones(List listRegiones) {
		String cadena = "";
		Iterator it = listRegiones.iterator();
		int i = 0;
		while (it.hasNext()) {
			String s = (String) it.next();

			if (i == listRegiones.size() - 1)
				cadena += s;
			else
				cadena += s + ", ";
			i++;

		}
		return cadena;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoLIDCargaPedidosObjetivosForm form = new ProcesoLIDCargaPedidosObjetivosForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;
		params = super.prepareParamsBeforeExecute(params, f);
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;
		this.mostrarPrimeraFase = false;
		this.mostrarBotonExecute = false;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		// setamos periodo actual
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		// cargamos combos de marca , canal y region
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));
		f.setCodigoPais(pais.getCodigo());
		// /limpiamos flag indicador carga a O , para que se permita la
		// validacion
		this.indCargar = Constants.NUMERO_CERO;
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);

	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the indCargar
	 */
	public String getIndCargar() {
		return indCargar;
	}

	/**
	 * @param indCargar
	 *            the indCargar to set
	 */
	public void setIndCargar(String indCargar) {
		this.indCargar = indCargar;
	}

	/**
	 * @return the mostrarPrimeraFase
	 */
	public Boolean getMostrarPrimeraFase() {
		return mostrarPrimeraFase;
	}

	/**
	 * @param mostrarPrimeraFase
	 *            the mostrarPrimeraFase to set
	 */
	public void setMostrarPrimeraFase(Boolean mostrarPrimeraFase) {
		this.mostrarPrimeraFase = mostrarPrimeraFase;
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
}