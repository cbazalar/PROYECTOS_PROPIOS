package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETConfiguracionProgramaForm;

@ManagedBean
@SessionScoped
public class ReporteLETConfiguracionProgramaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7026421572291202428L;
	private String formatoReporte;
	private List lecProgramaCorporativoList;
	
	private String codigoPrograma;
	private String  paginaXHTMLPadre= "";
	private boolean mostrarSalirPadre;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETConfiguracionProgramaForm reporteForm = new ReporteLETConfiguracionProgramaForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteLETConfiguracionProgramaAction - setViewAtributes");
		}
		ReporteLETConfiguracionProgramaForm f = (ReporteLETConfiguracionProgramaForm) this.formReporte;	
		
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");		
		
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		
	 	List programaCorporativoList = service.getProgramaCorporativoList(criteria);
	 	this.lecProgramaCorporativoList = programaCorporativoList;
	 	this.mostrarReportePDF = true;
	 	this.mostrarSalirPadre = false;
		log.debug("Todo OK: Redireccionando");

	}


	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal"; 
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteLETConfiguracionProgramaPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteLETConfiguracionProgramaForm f = (ReporteLETConfiguracionProgramaForm) this.formReporte;
		
		formatoReporte = f.getFormatoExportacion();
		
		
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");		
		
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		
	 	List programaCorporativoList = service.getProgramaCorporativoList(criteria);
	 	String descripcionPrograma = "";
	 	String codigoTipoComision = "";
	 	String nroCampanyasPeriodoGracia = "";
	 	String nroCampanyasMedirEtapa = "";
	 	
	 	if(programaCorporativoList != null && programaCorporativoList.size() > 0)
	 	{
	 		descripcionPrograma = MapUtils.getString((Map)programaCorporativoList.get(0), "descripcionPrograma", "");
	 		codigoTipoComision = MapUtils.getString((Map)programaCorporativoList.get(0), "codTipoComi", "");
	 		nroCampanyasPeriodoGracia = MapUtils.getString((Map)programaCorporativoList.get(0), "codCamPerGrac", "");
	 		nroCampanyasMedirEtapa = MapUtils.getString((Map)programaCorporativoList.get(0), "numCampEval", "");
	 	}
		
	 	params.put("codigoTipoComision" ,codigoTipoComision);
		params.put("codigoPais" ,pais.getCodigo());
		params.put("formatoExportacion", "PDF");

		//Campañas del TabNiveles 
		criteria.put("tipoExigencia", "V");
		List lecExigenciaNivelList = service.getCampanaExigenciaList(criteria);
		if(lecExigenciaNivelList!=null && lecExigenciaNivelList.size()>0){
			params.put("campanyaInicioNivel", MapUtils.getString((Map)lecExigenciaNivelList.get(0), "campanaInicio"));
			params.put("campanyaFinalNivel", MapUtils.getString((Map)lecExigenciaNivelList.get(0), "campanaFin"));
		}
		//
		
		//Campañas del TabBonos - Cambio de nivel acelerado 
		criteria.put("tipoExigencia", "N");
		lecExigenciaNivelList = service.getCampanaExigenciaList(criteria);
		if(lecExigenciaNivelList!=null && lecExigenciaNivelList.size()>0){
			params.put("campanyaInicioNivelAcelerado", MapUtils.getString((Map)lecExigenciaNivelList.get(0), "campanaInicio"));
			params.put("campanyaFinalNivelAcelerado", MapUtils.getString((Map)lecExigenciaNivelList.get(0), "campanaFin"));
		}
		//
		
		params.put("nroCampanyasPeriodoGracia", nroCampanyasPeriodoGracia);
		params.put("nroCampanyasMedirEtapa", nroCampanyasMedirEtapa);
		
		ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETDatosGeneralesProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource1_1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETListaAmbitoPDF" + JASPER_EXTENSION);
		ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETNivelesProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETCobranzaProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource4 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETBonoProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource4_1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETBonoLanzamientoProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource4_2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETBonoCicloVidaProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource4_3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETBonoCambioNivelAceleradoProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource4_4 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETBonoPeriodoGraciaNuevaProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource5 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETIncentivosProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource5_1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETIncentivosTipoComisionMontoFijoProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource5_2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETIncentivosTipoComisionMontoFijoCanastaProductosProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource5_3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETIncentivosTipoComisionPorcentajeProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource6 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETCanastasProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource6_1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETCanastasCabeceraProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource6_2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETCanastasDetalleProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource7 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETGestionDesempenioProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource7_1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETGestionDesempenioEtapasProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource7_2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETGestionDesempenioTipoProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource8 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETRankingProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource8_1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETRankingCabeceraProgramaPDF" + JASPER_EXTENSION);
		ClassPathResource resource8_2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLETRankingDetalleProgramaPDF" + JASPER_EXTENSION);
		
		
		params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath())));
		params.put("SUBREPORT_DIR1_LISTA_AMBITO", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource1_1.getPath()
				)));

		params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource2.getPath()
				)));
		params.put("SUBREPORT_DIR3", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource3.getPath()
				)));
		
		params.put("SUBREPORT_DIR4", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource4.getPath()
				)));
		params.put("SUBREPORT_DIR4_BONO_LANZAMIENTO", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource4_1.getPath()
				)));
		params.put("SUBREPORT_DIR4_BONO_CICLO_VIDA", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource4_2.getPath()
				)));
		params.put("SUBREPORT_DIR4_BONO_CAMBIO_NIVEL_ACELERADO", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource4_3.getPath()
				)));
		params.put("SUBREPORT_DIR4_BONO_PERIODO_GRACIA_NUEVA", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource4_4.getPath()
				)));
		
		params.put("SUBREPORT_DIR5", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource5.getPath()
				)));
		params.put("SUBREPORT_DIR5_TIPO_COMISION_MONTO_FIJO", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource5_1.getPath()
				)));
		params.put("SUBREPORT_DIR5_TIPO_COMISION_MONTO_FIJO_CANASTA", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource5_2.getPath()
				)));
		params.put("SUBREPORT_DIR5_TIPO_COMISION_PORCENTAJE", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource5_3.getPath()
				)));

		params.put("SUBREPORT_DIR6", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource6.getPath()
				)));
		params.put("SUBREPORT_DIR6_CANASTA_CABECERA", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource6_1.getPath()
				)));
		params.put("SUBREPORT_DIR6_CANASTA_DETALLE", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource6_2.getPath()
				)));

		params.put("SUBREPORT_DIR7", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource7.getPath()
				)));
		params.put("SUBREPORT_DIR7_LISTA_CAMPANYAS", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource7_1.getPath()
				)));
		params.put("SUBREPORT_DIR7_TIPO", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource7_2.getPath()
				)));
		
		params.put("SUBREPORT_DIR8", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource8.getPath()
				)));
		params.put("SUBREPORT_DIR8_RANKING_CABECERA", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource8_1.getPath()
				)));
		params.put("SUBREPORT_DIR8_RANKING_DETALLE", (JasperReport) JRLoader.loadObject(
				this.getClass().getClassLoader().getResource(resource8_2.getPath()
				)));	
		
		
		params.put("NroReporte", "");		
		params.put("titulo", getResourceMessage("reporteLETConfiguracionProgramaForm.title")
				+ getResourceMessage("reporteLETConfiguracionProgramaForm.programa")
				+ f.getCodigoPrograma() + " "
				+ descripcionPrograma);
		
		
	
		return params;
	}
	
	/**
	 * 
	 */
	public void setearValores()	{
		ReporteLETConfiguracionProgramaForm f = (ReporteLETConfiguracionProgramaForm) this.formReporte;		
		f.setCodigoPrograma(this.codigoPrograma);	
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setFormatoExportacion("PDF");
		this.setFormatoExportacion("PDF");
		this.paginaXHTMLPadre ="mantenimientoLECProgramaCorporativoList";
		this.mostrarSalirPadre = true;
	}

	
	/**
	 * @param event
	 */
	public void salirPadre(ActionEvent event){
		try {
			String pagina = this.paginaXHTMLPadre;
			this.redireccionarPagina(pagina);
				
		} catch (Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.addError("Error: ", error);
		}
	}
	

   /* GET -SET */
	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}



	/**
	 * @return the lecProgramaCorporativoList
	 */
	public List getLecProgramaCorporativoList() {
		return lecProgramaCorporativoList;
	}



	/**
	 * @param lecProgramaCorporativoList the lecProgramaCorporativoList to set
	 */
	public void setLecProgramaCorporativoList(List lecProgramaCorporativoList) {
		this.lecProgramaCorporativoList = lecProgramaCorporativoList;
	}



	public String getCodigoPrograma() {
		return codigoPrograma;
	}



	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}



	/**
	 * @return the paginaXHTMLPadre
	 */
	public String getPaginaXHTMLPadre() {
		return paginaXHTMLPadre;
	}



	/**
	 * @param paginaXHTMLPadre the paginaXHTMLPadre to set
	 */
	public void setPaginaXHTMLPadre(String paginaXHTMLPadre) {
		this.paginaXHTMLPadre = paginaXHTMLPadre;
	}



	/**
	 * @return the mostrarSalirPadre
	 */
	public boolean isMostrarSalirPadre() {
		return mostrarSalirPadre;
	}



	/**
	 * @param mostrarSalirPadre the mostrarSalirPadre to set
	 */
	public void setMostrarSalirPadre(boolean mostrarSalirPadre) {
		this.mostrarSalirPadre = mostrarSalirPadre;
	}
	
	

	
}

