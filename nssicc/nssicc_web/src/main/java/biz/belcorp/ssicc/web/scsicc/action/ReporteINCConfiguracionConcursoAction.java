package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCConfiguracionConcursoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 4203389682291263558L;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccConcursoList;
	private String tipoReporte;
	private List tipoReporteList;
	private String mostrarConcurso;
	private String formatoReporte;
	protected static final String RUTA_JASPER = "/biz/belcorp/ssicc/reportes/jasper/";
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCForm form = new ReporteINCForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteINCForm form = (ReporteINCForm) this.formReporte;
		this.formatoReporte = form.getFormatoExportacion();
		String reporte="";
		if (StringUtils.equals(tipoReporte,"AG"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCAmbitoGeograficoConcursoXLS";
			else
				reporte= "reporteMaestroVertical";		
		if (StringUtils.equals(tipoReporte,"CA"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCCalificacionConcursoXLS";
			else
				reporte= "reporteMaestroHorizontal";
		if (StringUtils.equals(tipoReporte,"CO"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCConsultorasConcursoXLS";
			else
				reporte= "reporteMaestroVertical";
		if (StringUtils.equals(tipoReporte,"GE"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCGerentesConcursoXLS";
			else
				reporte= "reporteMaestroVertical";
		if (StringUtils.equals(tipoReporte,"OD"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCObtencionPuntosConcursoXLS";
			else
				reporte= "reporteMaestroVertical";
		if (StringUtils.equals(tipoReporte,"PG"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCParametrosGeneralesConcursoXLS";
			else
				reporte= "reporteMaestroVertical";
		if (StringUtils.equals(tipoReporte,"PC"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCPlantillaConcursoXLS";
			else
				reporte= "reporteMaestroVertical";
		if (StringUtils.equals(tipoReporte,"PR"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCPremiosConcursoXLS";
			else
				reporte= "reporteMaestroHorizontal";
		if (StringUtils.equals(tipoReporte,"PD"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCProductoConcursoXLS";
			else
				reporte= "reporteMaestroHorizontal";
		if (StringUtils.equals(tipoReporte,"PN"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCProgramaNuevasXLS";
			else
				reporte= "reporteMaestroVertical";
		if (StringUtils.equals(tipoReporte,"RP"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCRequisitosPremiacionConcursoXLS";
			else
				reporte= "reporteMaestroHorizontal";
		if (StringUtils.equals(tipoReporte,"TO"))
			if ("XLS".equals(formatoReporte))
				reporte= "reporteINCTodasOpcionesConcursoXLS";
			else
				reporte= "reporteMaestroHorizontal";
		
		return reporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte="";
		if (StringUtils.equals(tipoReporte, "AG"))
			reporte= "reporteINCAmbitoGeograficoConcursoPDF";
		if (StringUtils.equals(tipoReporte, "CA"))
			reporte="reporteINCCalificacionConcursoPDF";
		if (StringUtils.equals(tipoReporte, "CO"))
			reporte="reporteINCConsultorasConcursoPDF";
		if (StringUtils.equals(tipoReporte, "GE")) 
			reporte="reporteINCGerentesConcursoPDF";
		if (StringUtils.equals(tipoReporte, "OD"))
			reporte="reporteINCObtencionPuntosConcursoPDF";
		if (StringUtils.equals(tipoReporte, "PG"))
			reporte="reporteINCParametrosGeneralesConcursoPDF";
		if (StringUtils.equals(tipoReporte, "PC"))
			reporte="reporteINCPlantillaConcursoPDF";
		if (StringUtils.equals(tipoReporte, "PR"))
			reporte="reporteINCPremiosConcursoPDF";
		if (StringUtils.equals(tipoReporte, "PD"))
			reporte="reporteINCProductoConcursoPDF";
		if (StringUtils.equals(tipoReporte, "PN"))
			reporte="reporteINCProgramaNuevasPDF"; 
		if (StringUtils.equals(tipoReporte, "RP"))
			reporte="reporteINCRequisitosPremiacionConcursoPDF";
		if (StringUtils.equals(tipoReporte, "TO"))
			reporte="reporteINCTodasOpcionesConcursoPDF";
		return reporte;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCConfiguracionConcursoAction.prepareParameterMap' method");
		}
		
		try{
			ReporteINCForm form = (ReporteINCForm) this.formReporte;
			this.formatoReporte = form.getFormatoExportacion();
			//this.setVirtualizador(true);
			
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			MantenimientoMAEClienteService mantenimientoMAEClienteService = (MantenimientoMAEClienteService)getBean("spusicc.mantenimientoMAEClienteService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			params.put("codigoPais", pais.getCodigo());
			
			form.setCodigoPais(pais.getCodigo());
			form.setOidPais(mantenimientoMAEClienteService.getOidPais(params));
			setMostrarConcurso(Constants.SI);
			
			params.put("codigoPais",form.getCodigoPais());
			params.put("oidPais", form.getOidPais());
			params.put("mostrarConcurso", Constants.SI);
			
			
			if ((!form.getCodigoMarca().equals("Todos")) && (form.getCodigoMarca() != null)){
				form.setOidMarca(mantenimientoMAEClienteService.getOidMarca(form.getCodigoMarca()));	
				params.put("codigoMarca",form.getCodigoMarca());
				params.put("oidMarca", mantenimientoMAEClienteService.getOidMarca(form.getCodigoMarca()));
			}
			
			if ((!form.getCodigoCanal().equals("Todos")) && (form.getCodigoCanal() != null)){
				form.setOidCanal(mantenimientoMAEClienteService.getOidCanal(form.getCodigoCanal()));
				params.put("codigoCanal",form.getCodigoCanal());
				params.put("oidCanal", mantenimientoMAEClienteService.getOidCanal(form.getCodigoCanal()));
			}
			
			if ((!form.getCodigoConcurso().equals("Todos")) && (form.getCodigoConcurso() != null)){							
				params.put("numeroConcurso", form.getCodigoConcurso());
				Map concurso = (Map) reporteService.getDatosConcursosByNumeroConcurso(params);
				String descripcionConcurso = concurso.get("descripcion") != null ? concurso.get("descripcion").toString(): "";
		        form.setDescConcurso(descripcionConcurso);
		        params.put("codigoConcurso",form.getCodigoConcurso());
		        params.put("descripcionConcurso", form.getDescConcurso());
			}
			
			
			//"Todas las Opciones",1
			//"Plantilla Concurso",2
			//"Parámetros Generales",3
			//"Productos",4
			//"Ambito Geográfico",5
			//"Premios",6
			//"Requisitos de Premiación",7
			//"Obtención de puntos y Despacho de premios",8
			//"Consultoras",9
			//"Gerentes",10
			//"Programa de Nuevas",11
			//"Calificación",12
			
			if (StringUtils.equals((form.getTipoReporte()), "5")) {
				tipoReporte = "AG";	
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCAmbitoGeograficoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCAmbitoGeograficoForm.titulo"));
				
			}
			if (StringUtils.equals((form.getTipoReporte()), "12")) {
				tipoReporte = "CA";
				
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCCalificacionEstatus" + JASPER_EXTENSION);
				ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCCalificacionRango" + JASPER_EXTENSION);
				ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCCalificacionRangoParticipante" + JASPER_EXTENSION);
				ClassPathResource resource3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCCalificacionProdValidos" + JASPER_EXTENSION);
				ClassPathResource resource4 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCCalificacionProdExcluidos" + JASPER_EXTENSION);
					
				params.put("SUBREPORT_DIR1_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("SUBREPORT_DIR2_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
				params.put("SUBREPORT_DIR3_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
				params.put("SUBREPORT_DIR4_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3.getPath() )));
				params.put("SUBREPORT_DIR5_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource4.getPath() )));
					

	
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCCalificacionConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCCalificacionConcursoForm.titulo"));
			}
			if (StringUtils.equals((form.getTipoReporte()), "9")) {
				tipoReporte = "CO";
				
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCConsultorasConcurso" + JASPER_EXTENSION);
				ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCEstadoConsultorasConcurso" + JASPER_EXTENSION);
				ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCRecomCampMontoConcurso" + JASPER_EXTENSION);
				
				params.put("SUBREPORT_DIR1_CONS", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("SUBREPORT_DIR2_CONS", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
				params.put("SUBREPORT_DIR3_CONS", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath())));
	
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCConsultoraConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCConsultoraConcursoForm.titulo"));
			}
			if (StringUtils.equals((form.getTipoReporte()), "10")) {
				tipoReporte = "GE";
				
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCGerentesConcurso" + JASPER_EXTENSION);
				
				params.put("SUBREPORT_DIR1_GERE", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				
				
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCGerenteConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCGerenteConcursoForm.titulo"));
			}
			if (StringUtils.equals((form.getTipoReporte()),"8")) {
				tipoReporte = "OD";
				
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCBonifEfectivaCamp".concat(JASPER_EXTENSION));
				
				params.put("SUBREPORT_DIR1_OPDP", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath())));
			
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCObtencionPuntosDespachoPremiosConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCObtencionPuntosDespachoPremiosConcursoForm.titulo"));
			}
			if (StringUtils.equals((form.getTipoReporte()),"3")) {
				tipoReporte = "PG";
				
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCParametrosGeneralesConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCParametrosGeneralesConcursoForm.titulo"));
			}
			if (StringUtils.equals((form.getTipoReporte()),"2")) {
				tipoReporte = "PC";
				
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCPlantillaConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCPlantillaConcursoForm.titulo"));
				
			}
			if (StringUtils.equals((form.getTipoReporte()), "6")) {
				tipoReporte = "PR";
	
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCPremiosArticulo" + JASPER_EXTENSION);
				ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCPremiosMonetario" + JASPER_EXTENSION);
				ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCPremiosPuntos" + JASPER_EXTENSION);
				ClassPathResource resource3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCPremiosDescuento" + JASPER_EXTENSION);
				ClassPathResource resource4 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCPremiosReemplazo"+JASPER_EXTENSION);
				ClassPathResource resource5 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCParamNivelPremiPunex".concat(JASPER_EXTENSION));
				ClassPathResource resource6 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCConcuCampaDespa".concat(JASPER_EXTENSION));
											
				params.put("SUBREPORT_DIR1_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("SUBREPORT_DIR2_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
				params.put("SUBREPORT_DIR3_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
				params.put("SUBREPORT_DIR4_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3.getPath() )));
				params.put("SUBREPORT_DIR5_PREM", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource4.getPath() )));
				params.put("SUBREPORT_DIR6_PREM", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource5.getPath())));
				params.put("SUBREPORT_DIR7_PREM", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource6.getPath())));
				
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCPremiosConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCPremiosConcursoForm.titulo"));
			}
			if (StringUtils.equals((form.getTipoReporte()), "4")) {
				tipoReporte = "PD"; 
			
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProdValidos" + JASPER_EXTENSION);
				ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProdExcluido" + JASPER_EXTENSION);
				ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProdBonificados" + JASPER_EXTENSION);
				ClassPathResource resource3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCProdExigidos" + JASPER_EXTENSION);
							
				params.put("SUBREPORT_DIR1_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				params.put("SUBREPORT_DIR2_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
				params.put("SUBREPORT_DIR3_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
				params.put("SUBREPORT_DIR4_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3.getPath() )));
			
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCProductosConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCProductosConcursoForm.titulo"));
				
			}
			if (StringUtils.equals((form.getTipoReporte()),"11")) {
				tipoReporte = "PN";
				
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCProgramaNuevasConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCProgramaNuevasConcursoForm.titulo"));
				
				
			}
			if (StringUtils.equals((form.getTipoReporte()),"7")) {
				tipoReporte = "RP";
				
				ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteINCRequisitosPremiacion" + JASPER_EXTENSION);
					
				params.put("SUBREPORT_DIR1_REQU", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
				
				form.setNroReporte(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCRequisitosPremiacionConcursoForm.numero.reporte"));
				form.setTitulo(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCRequisitosPremiacionConcursoForm.titulo"));
				
			
			}
	
			if (StringUtils.equals((form.getTipoReporte()),"1")) {
				tipoReporte = "TO"; 
				
				params.put("mostrarConcurso", Constants.NO);
				
				String titulo = this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCTodasOpcionesConcursoForm.numero.reporte"); 
				this.formReporte.setNroReporte(
						StringUtils.isNotBlank(titulo)?titulo:""
						);
				
				
				StringBuilder sb = new StringBuilder();
				sb.append(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCTodasOpcionesConcursoForm.titulo"));
				sb.append("-");
				sb.append(this.mPantallaPrincipalBean.getReportResourceMessage("reporteINCTodasOpcionesConcursoForm.titulo.concurso"));
				sb.append(" ");
				sb.append(form.getCodigoConcurso());
				sb.append(" ");
				sb.append(form.getDescConcurso());
				String titu = sb.toString();
				
				form.setTitulo(titu);
				
				    
				ClassPathResource resourcec = new ClassPathResource(RUTA_JASPER.concat("subReporteINCCalificacionEstatus" + JASPER_EXTENSION));
				ClassPathResource resource1c = new ClassPathResource(RUTA_JASPER.concat("subReporteINCCalificacionRango" + JASPER_EXTENSION));
				ClassPathResource resource2c = new ClassPathResource(RUTA_JASPER.concat("subReporteINCCalificacionRangoParticipante" + JASPER_EXTENSION));
				ClassPathResource resource3c = new ClassPathResource(RUTA_JASPER.concat("subReporteINCCalificacionProdValidos" + JASPER_EXTENSION));
				ClassPathResource resource4c = new ClassPathResource(RUTA_JASPER.concat("subReporteINCCalificacionProdExcluidos" + JASPER_EXTENSION));
				
				ClassPathResource resourcep = new ClassPathResource(RUTA_JASPER.concat("subReporteINCProdValidos" + JASPER_EXTENSION));
				ClassPathResource resource1p = new ClassPathResource(RUTA_JASPER.concat("subReporteINCProdExcluido" + JASPER_EXTENSION));
				ClassPathResource resource2p = new ClassPathResource(RUTA_JASPER.concat("subReporteINCProdBonificados" + JASPER_EXTENSION));
				ClassPathResource resource3p = new ClassPathResource(RUTA_JASPER.concat("subReporteINCProdExigidos" + JASPER_EXTENSION));
	
				ClassPathResource resourceco = new ClassPathResource(RUTA_JASPER.concat("subReporteINCConsultorasConcurso" + JASPER_EXTENSION));
				ClassPathResource resource1co = new ClassPathResource(RUTA_JASPER.concat("subReporteINCEstadoConsultorasConcurso" + JASPER_EXTENSION));
				ClassPathResource resource2co = new ClassPathResource(RUTA_JASPER.concat("subReporteINCRecomCampMontoConcurso" + JASPER_EXTENSION));
	
				ClassPathResource resourcege = new ClassPathResource(RUTA_JASPER.concat("subReporteINCGerentesConcurso" + JASPER_EXTENSION));
				
				ClassPathResource resourcepr = new ClassPathResource(RUTA_JASPER.concat("subReporteINCPremiosArticulo" + JASPER_EXTENSION));
				ClassPathResource resource1pr = new ClassPathResource(RUTA_JASPER.concat("subReporteINCPremiosMonetario" + JASPER_EXTENSION));
				ClassPathResource resource2pr = new ClassPathResource(RUTA_JASPER.concat("subReporteINCPremiosPuntos" + JASPER_EXTENSION));
				ClassPathResource resource3pr = new ClassPathResource(RUTA_JASPER.concat("subReporteINCPremiosDescuento" + JASPER_EXTENSION));
				ClassPathResource resource4pr = new ClassPathResource(RUTA_JASPER.concat("subReporteINCPremiosReemplazo" + JASPER_EXTENSION));
				ClassPathResource resource5pr = new ClassPathResource(RUTA_JASPER.concat("subReporteINCParamNivelPremiPunex" + JASPER_EXTENSION));
				ClassPathResource resource6pr = new ClassPathResource(RUTA_JASPER.concat("subReporteINCConcuCampaDespa" + JASPER_EXTENSION));
				
				ClassPathResource resourcere = new ClassPathResource(RUTA_JASPER.concat("subReporteINCRequisitosPremiacion" + JASPER_EXTENSION));
				
				ClassPathResource resourceopdp = new ClassPathResource(RUTA_JASPER.concat("subReporteINCBonifEfectivaCamp" + JASPER_EXTENSION));
				
				ClassPathResource resource1 = new ClassPathResource(RUTA_JASPER.concat("reporteINCParametrosGeneralesConcursoPDF" + JASPER_EXTENSION));
				ClassPathResource resource2 = new ClassPathResource(RUTA_JASPER.concat("reporteINCPremiosConcursoPDF" + JASPER_EXTENSION)); 
				ClassPathResource resource3 = new ClassPathResource(RUTA_JASPER.concat("reporteINCProductoConcursoPDF" + JASPER_EXTENSION)); 
				ClassPathResource resource4 = new ClassPathResource(RUTA_JASPER.concat("reporteINCAmbitoGeograficoConcursoPDF" + JASPER_EXTENSION)); 
				ClassPathResource resource5 = new ClassPathResource(RUTA_JASPER.concat("reporteINCRequisitosPremiacionConcursoPDF" + JASPER_EXTENSION)); 
				ClassPathResource resource6 = new ClassPathResource(RUTA_JASPER.concat("reporteINCObtencionPuntosConcursoPDF" + JASPER_EXTENSION)); 
				ClassPathResource resource7 = new ClassPathResource(RUTA_JASPER.concat("reporteINCConsultorasConcursoAux1PDF" + JASPER_EXTENSION)); 
				ClassPathResource resource71 = new ClassPathResource(RUTA_JASPER.concat("reporteINCConsultorasConcursoAux2PDF" + JASPER_EXTENSION));
				ClassPathResource resource72 = new ClassPathResource(RUTA_JASPER.concat("reporteINCConsultorasConcursoAux3PDF" + JASPER_EXTENSION));  
				ClassPathResource resource8 = new ClassPathResource(RUTA_JASPER.concat("reporteINCCalificacionConcursoPDF" + JASPER_EXTENSION)); 
	
				params.put("SUBREPORT_DIR1_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resourcec.getPath() )));
				params.put("SUBREPORT_DIR2_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1c.getPath()   )));
				params.put("SUBREPORT_DIR3_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2c.getPath() )));
				params.put("SUBREPORT_DIR4_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3c.getPath() )));
				params.put("SUBREPORT_DIR5_CALI", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource4c.getPath() )));

				params.put("SUBREPORT_DIR1_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resourcep.getPath() )));
				params.put("SUBREPORT_DIR2_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1p.getPath() )));
				params.put("SUBREPORT_DIR3_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2p.getPath() )));
				params.put("SUBREPORT_DIR4_PROD", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3p.getPath() )));  

				params.put("SUBREPORT_DIR1_CONS", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resourceco.getPath() )));
				params.put("SUBREPORT_DIR2_CONS", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1co.getPath() )));
				params.put("SUBREPORT_DIR3_CONS", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2co.getPath())));

				params.put("SUBREPORT_DIR1_GERE", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resourcege.getPath() )));

				params.put("SUBREPORT_DIR1_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resourcepr.getPath() )));
				params.put("SUBREPORT_DIR2_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1pr.getPath() )));
				params.put("SUBREPORT_DIR3_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2pr.getPath() )));
				params.put("SUBREPORT_DIR4_PREM", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3pr.getPath() )));				
				params.put("SUBREPORT_DIR5_PREM", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource4pr.getPath() )));
				params.put("SUBREPORT_DIR6_PREM", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource5pr.getPath())));
				params.put("SUBREPORT_DIR7_PREM", (JasperReport)JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource6pr.getPath())));
				
				params.put("SUBREPORT_DIR1_REQU", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resourcere.getPath() )));
				
				params.put("SUBREPORT_DIR1_OPDP", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resourceopdp.getPath())));

				params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
				params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
				params.put("SUBREPORT_DIR3", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource3.getPath() )));
				params.put("SUBREPORT_DIR4", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource4.getPath() )));
				params.put("SUBREPORT_DIR5", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource5.getPath() )));
				params.put("SUBREPORT_DIR6", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource6.getPath() )));
				params.put("SUBREPORT_DIR7", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource7.getPath() )));
				params.put("SUBREPORT_DIR71", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource71.getPath() )));
				params.put("SUBREPORT_DIR72", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource72.getPath() )));
				params.put("SUBREPORT_DIR8", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource8.getPath() )));
					
				
			}
			
			params.put("NroReporte", form.getNroReporte());
			params.put("titulo", form.getTitulo());		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCConfiguracionConcursoAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ReporteINCForm form = (ReporteINCForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		
		setSiccCanalList(reporteService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		setSiccMarcaList(reporteService.getMarcas());
		
		//Seteo de tipo de reportes
		ArrayList<Base> reporteTipo = new ArrayList<Base>();
		Base[] baseTipo = new Base[12];		
		String[] elementos = { 
				"Todas las Opciones",
				"Plantilla Concurso",
				"Parámetros Generales",
				"Productos",
				"Ambito Geográfico",
				"Premios",
				"Requisitos de Premiación",
				"Obtención de puntos y Despacho de premios",
				"Consultoras",
				"Gerentes",
				"Programa de Nuevas",
				"Calificación"
				};
		for (int i = 0; i < 12; i++) {
			baseTipo[i] = new Base();
			baseTipo[i].setCodigo("" + (i + 1));
			baseTipo[i].setDescripcion(elementos[i]);
			reporteTipo.add(baseTipo[i]);
		}
		tipoReporteList = reporteTipo;		
		
		setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, Constants.FORMATO_TOTAL));
	}
	
	
	/**
	 * Carga Combo Concursos por Marca
	 * @param val
	 */
	public void loadConcursoMarca(ValueChangeEvent val){
		
		log.debug(">>loadConcursoMarca...");
		String valor = val.getNewValue().toString();
		
		ReporteINCForm form = (ReporteINCForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		
		this.setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(form.getCodigoPais(), valor, 
				form.getCodigoCanal(), Constants.FORMATO_TOTAL));
	}
	
	/**
	 * Carga Combo Concursos por Canal
	 * @param val
	 */
	public void loadConcursoCanal(ValueChangeEvent val){
		
		log.debug(">>loadConcursoCanal...");
		String valor = val.getNewValue().toString();
		
		ReporteINCForm form = (ReporteINCForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		
		this.setSiccConcursoList(aSvc.getConcursosByPaisMarcaCanal(form.getCodigoPais(), form.getCodigoMarca(), 
				valor, Constants.FORMATO_TOTAL));
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

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getMostrarConcurso() {
		return mostrarConcurso;
	}

	public void setMostrarConcurso(String mostrarConcurso) {
		this.mostrarConcurso = mostrarConcurso;
	}

	public List getTipoReporteList() {
		return tipoReporteList;
	}

	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}

	public LabelValue[] getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(LabelValue[] siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
	
	
	
	
}
