package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONCrearTerritorioDemandaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoZONCrearTerritorioDemandaAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8092474533045141909L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccPerioodoCorporativoList;
	private List siccRegionList;
	private List zonUnidadesAdministrativasList;
	private Boolean pintarScrollLista;
	private String attachment = "";
	
	private Boolean mostrarPrimeraGrilla;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoZONCrearTerritorioDemandaForm p = new ProcesoZONCrearTerritorioDemandaForm();
		return p;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargarConsultoras' method"); 
		}

		// obtenemos el servicio de Zon Unidades Administrativas
		ProcesoZONActualizarUnidadesAdministrativasService service = 
				(ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");

		//Se realiza la validaciones de las consultoras y territorios recibidos del archivo excel
		service.executeProcesarUnidadAdministrativaDemanda(params);
		
		return params;
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		

		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.limpiara();
		ProcesoZONCrearTerritorioDemandaForm f = (ProcesoZONCrearTerritorioDemandaForm) this.formProceso;
		
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));

		ProcesoZONActualizarUnidadesGeograficasService service2 = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(service2.obtenerPathUpload(pais.getCodigo()));
		
		this.siccMarcaList = reporteService.getMarcas();
        this.siccCanalList = reporteService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccPerioodoCorporativoList = reporteService.getPeriodosCorporativosPorTipo(Constants.TIPO_PERIOODO_CORPORATIVO_CAMPANIA);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", f.getCodigoMarca());
		criteriaOperacion.put("codigoCanal", f.getCodigoCanal());

		ProcesoZONActualizarUnidadesAdministrativasService service3 = 
				(ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");
		
		this.siccRegionList = service3.getRegionesDemandaByPais(criteriaOperacion);
	
	}
	
	/**
	 * Accion que valida las regiones seleccionadas no hayan cerrado en el periodo indicado y
     * que las regiones no hayan iniciado su facturacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void load(FileUploadEvent event)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargarConsultoras' method"); 
		}

		ProcesoZONCrearTerritorioDemandaForm f = (ProcesoZONCrearTerritorioDemandaForm)this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.setAttachment(event.getFile().getFileName());
		
		UploadedFile archivo = event.getFile();
		f.setArchivo(archivo);
		f.setNombreArchivo(archivo.getFileName());
		
		if(f.getCodigoRegion()==null)
		{	
			String msjReg="Seleccione Región.";
			addInfo("Mensaje: ", msjReg);
			return;}
		
		//Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
		
		if(f.getArchivo().getFileName()!=null)
			this.setMostrarPrimeraGrilla(true);
		
		// obtenemos el servicio de Zon Unidades Administrativas
		ProcesoZONActualizarUnidadesAdministrativasService service = 
				(ProcesoZONActualizarUnidadesAdministrativasService)getBean("spusicc.procesoZONUniAdmService");

		// Cargamos el archivo de la maquina del cliente al servidor
		uploadArchivo(f);

		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("directorioTemporal", f.getDirectorioTemporal());
		params.put("nombreArchivo", f.getNombreArchivo());
		params.put("extensionArchivo", f.getExtensionArchivo());
		params.put("codigoRegiones", StringUtil.obtieneListaCodigos(f.getCodigoRegion(), "", ""));
		params.put("codigoUsuario",usuario.getLogin());  
		params.put("numeroCampos", new Integer(7));
		
		//validamos el archivo excel
		boolean isValido =service.validarFormatoArchivoExcel(params);
		if(isValido){
			//Se realiza la carga y validaciones del proceso de crear territorios de demanda anticipada
			boolean validacion = service.executeValidarUnidadAdministrativaDemanda(params);
			
			if(validacion){
				List listaRegistros = service.getUnidadAdministrativaDemanda(params);
	
				this.zonUnidadesAdministrativasList = listaRegistros;
				
				f.setMostrarLista(true);
				
				if(listaRegistros.size()>10)
					this.setPintarScrollLista(new Boolean(true));	
				else
					this.setPintarScrollLista(new Boolean(false));
					
			} else {
				List listaErrores = (List)params.get("listaErrores");
				
				String errores = "";
				for(int i=0; i<listaErrores.size(); i++) {
					Base base = (Base)listaErrores.get(i);
					
					String observacion = base.getCodigo();
					String totalErrores = base.getDescripcion();
					
					String texto = getResourceMessage("procesoZONCrearTerritorioDemandaForm.errorValidacion", new Object[]{observacion, totalErrores} );
					
					errores = errores + "<br>" + texto;
				}
				
				addError("Error: ", getResourceMessage("procesoZONCrearTerritorioDemandaForm.error", new Object[]{errores}));

			}
		}
		else {
					
			addError("Error :", getResourceMessage("procesoZONCrearTerritorioDemandaForm.archivo.novalido") );
		}
		
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
	
	}

	/**
	 * Cargar archivo del cliente al servidor. 
	 * 
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo(ProcesoZONCrearTerritorioDemandaForm f) throws Exception {

		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();

		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());

		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();

		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), 
								f.getNombreArchivo()));

		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();

		f.setArchivo(null);
	}
	
	/**
	 * elimina el fichero
	 * 
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}
	
	/**
	 *
	 */
	public void regresar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'regresar' method");
		}

		ProcesoZONCrearTerritorioDemandaForm f = (ProcesoZONCrearTerritorioDemandaForm) this.formProceso;
		f.setMostrarLista(false);
	
	}
	
	/**
	 * 
	 */
	public void limpiara() {
			
			this.zonUnidadesAdministrativasList =null;
			this.attachment = "";
			this.mostrarPrimeraGrilla = false;
			
		}
	
	/**
	 * obtiene la extension del archivo
	 * 
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccPerioodoCorporativoList() {
		return siccPerioodoCorporativoList;
	}

	public void setSiccPerioodoCorporativoList(List siccPerioodoCorporativoList) {
		this.siccPerioodoCorporativoList = siccPerioodoCorporativoList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getZonUnidadesAdministrativasList() {
		return zonUnidadesAdministrativasList;
	}

	public void setZonUnidadesAdministrativasList(
			List zonUnidadesAdministrativasList) {
		this.zonUnidadesAdministrativasList = zonUnidadesAdministrativasList;
	}

	public Boolean getPintarScrollLista() {
		return pintarScrollLista;
	}

	public void setPintarScrollLista(Boolean pintarScrollLista) {
		this.pintarScrollLista = pintarScrollLista;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Boolean getMostrarPrimeraGrilla() {
		return mostrarPrimeraGrilla;
	}

	public void setMostrarPrimeraGrilla(Boolean mostrarPrimeraGrilla) {
		this.mostrarPrimeraGrilla = mostrarPrimeraGrilla;
	}

	
}
