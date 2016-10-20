package biz.belcorp.ssicc.reportes.framework.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface;
import biz.belcorp.ssicc.reportes.framework.service.ParameterContructorService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;


/**
 * Clase Action abstracta para las Reportes ScSiCC. Define un flujo basico de
 * ejecucion de un reporte de Jasper Reports.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 *
 */

@Service("reportes.parameterContructorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ParameterContructorServiceImpl extends BaseReporteInterfaceImpl implements ParameterContructorService{
	 
	
	protected transient final Log log = LogFactory.getLog(getClass());
	protected static final String JASPER_DIRECTORIO = "/biz/belcorp/ssicc/reportes/jasper/";
	//protected static final String JASPER_DIRECTORIO = "";
	
	protected static final String JASPER_EXTENSION = ".jasper";
	protected static final String PDF_EXTENSION = ".pdf";
	protected static final String XLS_EXTENSION = ".xls";
	protected static final String XLSX_EXTENSION = ".xlsx";
	protected static final String CSV_EXTENSION = ".csv";	
	protected static final String TXT_EXTENSION = ".txt";
	protected static final String ZIP_EXTENSION = ".zip";
	
	protected static final String SUBREPORT_PREFIX = "SUBREPORT_DIR";
			
	private boolean virtualizador;
	
	private String  nombreReporte;	
	private String  prefijoArchivo;
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.ParameterContructorService#inicializarParametrosReporte(biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm, java.util.Map)
	 */
	public ReporteParams inicializarParametrosReporte(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception {
		ReporteParams reporteParams = new ReporteParams();
						
		//Inicializar parametros Generales del Reporte
		Map<String, Object> params = this.setParametrosGenerales(form, parameterAdicionales);
		
		//Seteo del Jasper File del Subreporte
		this.setParameterSubReporteMap(params, form); 
		
		//Seteo del Jasper File del Reporte 
		reporteParams.setJasperFileName(JASPER_DIRECTORIO + form.getNombreReporte() + JASPER_EXTENSION);
		params.put(JRParameter.REPORT_LOCALE, form.getLocale());
		
		//Seteo de SubReport(si esta existe en el reporte)
		if(form.getSubReporteDir1()!=null && form.getSubReporteDir1().length()>0){
			ClassPathResource resource = new ClassPathResource(JASPER_DIRECTORIO + form.getSubReporteDir1() + JASPER_EXTENSION);			
			//params.put("SUBREPORT_DIR1", (JasperReport)JRLoader.loadObject(resource.getFile()));
			params.put("SUBREPORT_DIR1",  (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() ))   );
		}
		
		if(form.getSubReporteDir2()!=null && form.getSubReporteDir2().length()>0){
			ClassPathResource resource = new ClassPathResource(JASPER_DIRECTORIO + form.getSubReporteDir2() + JASPER_EXTENSION);
			//params.put("SUBREPORT_DIR2", JRLoader.loadObject(resource.getFile()));	
			params.put("SUBREPORT_DIR2",  (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() ))   );
		}
					
		//Mapa de partametros a enviar al Jasper para su procesamiento
		params.put("parameterMap", params);
		
		this.log.debug("Despues de prepareParameterMap, parameterMap="+ params);
				
		//Seteo de los parametros al objeto de parametros a ejecutar
		reporteParams.setQueryParams(params);
					
		//ejecucion de logica extra y creacion dinámica de services antes de la ejecucion del reporte.			
		if(form.isBeforeExecuteReporte()){			
			BaseReporteInterface service = form.getReporteService();
			service.beforeExecuteReporte(reporteParams, form);
		}
		return reporteParams;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.ParameterContructorService#inicializarParametrosReporteNoJASPER(biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm, java.util.Map)
	 */
	public ReporteParams inicializarParametrosReporteNoJASPER(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception {
		ReporteParams reporteParams = new ReporteParams();
						
		//Inicializar parametros Generales del Reporte
		Map<String, Object> params = this.setParametrosGenerales(form, parameterAdicionales);
					
		//Mapa de partametros a enviar al Jasper para su procesamiento
		params.put("parameterMap", params);
		
		this.log.debug("Despues de prepareParameterMap, parameterMap="+ params);
				
		//Seteo de los parametros al objeto de parametros a ejecutar
		reporteParams.setQueryParams(params);
					
		//ejecucion de logica extra y creacion dinámica de services antes de la ejecucion del reporte.	
		if(form.isBeforeExecuteReporte()){			
			BaseReporteInterface service = form.getReporteService();
			service.beforeExecuteReporte(reporteParams, form);
		}
		return reporteParams;
	}
	
	
	
	/**
	 * Declara informacion general del Reporte Principal. 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	protected final Map<String, Object> setParametrosGenerales(BaseReporteForm form, Map<String, Object> parameterMapAdicionales) throws Exception {
		
		Map<String, Object> parameterMap = this.prepareParamsBeforeExecute(form);
		if (parameterMapAdicionales != null)
			parameterMap.putAll(parameterMapAdicionales);
		try{
			
			Usuario usuario = form.getUsuario();				
												
			/* Seteando variables donde se guardara el Reporte Temporalmente */				
			Map<String, Object> paramReporteGeneral = form.getParametrosReporteGeneral();
			parameterMap.put("directorioTemporal", (String) paramReporteGeneral.get("directorioTemporal"));
			parameterMap.put("directorioRepositorio", (String) paramReporteGeneral.get("directorioRepositorio"));
						
			/* Seteando variables iniciales del Reporte */
			parameterMap.put("usuario.login", usuario.getLogin());
			parameterMap.put("superiorIzquierda", this.getReportResourceMessage("reporte.maestro.belcorp")+form.getPais().getDescripcion());
					
			parameterMap.put("condicionUsuario", "SI");
			parameterMap.put("condicionFechaHora", "SI");
								
			Idioma idioma = form.getIdioma();
			parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
			parameterMap.put("codigoIdioma", idioma.getCodigoISO());		
			
		}catch(Exception e){
			this.log.error("Error: "+ this.obtieneMensajeErrorException(e));
		}	
		
		return parameterMap;
	}
	
	/**
	 * Metodo que setea el file .jasper del subreporte.
	 * @param params
	 * @param form
	 * @throws Exception
	 */
	protected void setParameterSubReporteMap(Map<String, Object> params, BaseReporteForm form) throws Exception {
		String subReporte = form.getNombreSubReporte();
		if (StringUtils.isNotBlank(subReporte)) {
			try {
				ClassPathResource resource = new ClassPathResource(JASPER_DIRECTORIO + subReporte + JASPER_EXTENSION);				
				try {
				   InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resource.getPath());
				   params.put(SUBREPORT_PREFIX, (JasperReport) JRLoader.loadObject(stream));
				}
				catch (Exception e) {   
				   try {
					  params.put(SUBREPORT_PREFIX, (JasperReport) JRLoader.loadObject(resource.getFile()));
				   }
				   catch (IOException x) {
				       log.error("Error: no se pudo cargar el subreporte");
				       throw new Exception (x);
			       }	
				}   	  
			} catch (JRException e) {				
				log.error("Error: no se pudo cargar el subreporte");
				throw new Exception (e);
			}			
		}
	}
	
	/**
	 * Este metodo adiciona parametros extra y retira los innecesarios.
	 * @param form
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(BaseReporteForm form) throws Exception {
		// Paso todos los parametros al map
		Map<String, Object> params = BeanUtils.describe(form);
		
		// Agrego los parametros necesarios
		params.put("usuario", form.getUsuario());				
		params.put("descripcionPais", form.getPais().getDescripcion());
		
		// Remuevo los parametros que no son necesarios del Validator		
		params.remove("pais");
		params.remove("idioma");
		params.remove("parametrosReporteGeneral");
		params.remove("locale");		
		
		params.remove("resultValueMap");
		params.remove("validatorResults");
		params.remove("servletWrapper");
		params.remove("multipartRequestHandler");
		params.remove("class");
		params.remove("page");
		
		return params;
	}
	
	
	
	/**
	 * Metodo que devuelve nombre del Archivo a generar en el Servidor 
	 * @return
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		if (!StringUtils.isBlank(this.prefijoArchivo)) {
			nombreArchivoReporte = this.prefijoArchivo + "_"
				+ sdf.format(new Date(System.currentTimeMillis()));
		} else {
			nombreArchivoReporte = sdf.format(new Date(System.currentTimeMillis()));
		}
		return nombreArchivoReporte;
	}
	
	/**
	 * Metodo que devuelve la extension del formato del Reporte.
	 * @return
	 */
	public String getExtensionReporte(String formato) {
		if (StringUtils.equals("XLS", formato) || StringUtils.equals("VXLS", formato)) {
			return XLS_EXTENSION;
		}
		if (StringUtils.equals("XLSX", formato) || StringUtils.equals("VXLSX", formato)) {
			return XLSX_EXTENSION;
		}
		if (StringUtils.equals("CSV", formato) || StringUtils.equals("VCSV", formato)) {
			return CSV_EXTENSION;
		}
		if (StringUtils.equals("PDF", formato) || StringUtils.equals("VPDF", formato)) {
			return PDF_EXTENSION;
		}
		if (StringUtils.equals("TXT", formato) || StringUtils.equals("VTXT", formato)) {
			return TXT_EXTENSION;
		}
		if (StringUtils.equals("ZIP", formato)) {
			return ZIP_EXTENSION;
		}		
		return PDF_EXTENSION;
	}
	
	/**
	 * @return the nombreReporte
	 */
	public String getNombreReporte() {
		return nombreReporte;
	}

	/**
	 * @param nombreReporte the nombreReporte to set
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	
	/**
	 * @return the virtualizador
	 */
	public boolean isVirtualizador() {
		return virtualizador;
	}

	/**
	 * @param virtualizador the virtualizador to set
	 */
	public void setVirtualizador(boolean virtualizador) {
		this.virtualizador = virtualizador;
	}
	
    /**
	 * @return the prefijoArchivo
	 */
	public String getPrefijoArchivo() {
		return prefijoArchivo;
	}

	/**
	 * @param prefijoArchivo the prefijoArchivo to set
	 */
	public void setPrefijoArchivo(String prefijoArchivo) {
		this.prefijoArchivo = prefijoArchivo;
	}


	public String getJasperDirectorio() {
		return JASPER_DIRECTORIO;
	}
	
}
