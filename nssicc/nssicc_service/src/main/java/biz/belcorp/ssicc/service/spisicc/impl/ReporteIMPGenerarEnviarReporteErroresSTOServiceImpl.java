package biz.belcorp.ssicc.service.spisicc.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spisicc.ReporteIMPGenerarEnviarReporteErroresSTOService;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteIMPGenerarEnviarReporteErroresSTOServiceImpl.
 *
 * @author Belcorp
 * @version 1.0
 * 04:16:18 PM
 */
@Service("spisicc.reporteIMPGenerarEnviarReporteErroresSTOService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteIMPGenerarEnviarReporteErroresSTOServiceImpl extends BaseSubReporteAbstractService implements ReporteIMPGenerarEnviarReporteErroresSTOService{
	
    /** The mail service. */
    private BaseMailService mailService;
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseReporteAbstractService#getReporteFileName()
	 */
	protected String getReporteFileName() {
		return "reporteMaestroHorizontal";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService#getSubReporteFileName()
	 */
	protected String getSubReporteFileName() {
		return "reporteSTOErroresCorreoPDF";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteMSGSTOErroresCorreoForm.error";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception{
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteMSGSTOErroresCorreoForm.titulo",null,getLocale(usuario));
		log.debug("titulo");
		params.put("titulo", titulo);
		params.put("formatoExportacion","PDF");
		
		String descripcionRegion = MapUtils.getString(params, "descripcionRegion");
		String descripcionZona = MapUtils.getString(params, "descripcionZona");
		String tipoOpcion = MapUtils.getString(params, "tipoOpcion");
		String nombreRegionZona = "";
		String tituloRegionZona = "";
		
		if(StringUtils.equals(tipoOpcion, Constants.NUMERO_CERO)){
			nombreRegionZona = descripcionRegion;
			tituloRegionZona = "Region:";
		}else{
			nombreRegionZona = descripcionZona;
			tituloRegionZona = "Zona:";
		}
		
		String documentosSTOReporteErrores = MapUtils.getString(params, "documentosSTOReporteErrores");
		if(documentosSTOReporteErrores == null)documentosSTOReporteErrores="";
		 
		//Cargamos los subreportes				
		try {
			
			ClassPathResource resource0 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub0Resumen" + JASPER_EXTENSION);
			
			ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub1OrdenCompra" + JASPER_EXTENSION);
			ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub2SolicitudCredito" + JASPER_EXTENSION);
			ClassPathResource resource3 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub3SolicitudActualizaDatos" + JASPER_EXTENSION);
			ClassPathResource resource4 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub4DuplaCyzone" + JASPER_EXTENSION);
			ClassPathResource resource5 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub5FamiliaProtegida" + JASPER_EXTENSION);
			ClassPathResource resource6 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub6IngresoMetas" + JASPER_EXTENSION);
			//ClassPathResource resource7 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub7SolicitudCredito" + JASPER_EXTENSION);
			                          
			ClassPathResource resource7 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteSTOErroresCorreoSub7ContratoEjecutivas" + JASPER_EXTENSION);
			
			
			params.put("SUBREPORT_DIR0", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
					.getResource(resource0.getPath())));
			params.put("SUBREPORT_DIR1_MOSTRAR", "0");
			
			
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_OCC)>=0) {
				params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource1.getPath())));
				params.put("SUBREPORT_DIR1_MOSTRAR", "1");
			} else 
				params.put("SUBREPORT_DIR1_MOSTRAR", "0");
			
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_SCC)>=0) {
				params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource2.getPath())));
				params.put("SUBREPORT_DIR2_MOSTRAR", "1");
			} else	
				params.put("SUBREPORT_DIR2_MOSTRAR", "0");
			
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_SAD)>=0) {
				params.put("SUBREPORT_DIR3", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource3.getPath())));
				params.put("SUBREPORT_DIR3_MOSTRAR", "1");
			} else 	
				params.put("SUBREPORT_DIR3_MOSTRAR", "0");
			
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_DCYZ)>=0) {
				params.put("SUBREPORT_DIR4", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource4.getPath())));
				params.put("SUBREPORT_DIR4_MOSTRAR", "1");
			} else	
				params.put("SUBREPORT_DIR4_MOSTRAR", "0");
			
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_FAS)>=0) {
				params.put("SUBREPORT_DIR5", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource5.getPath())));
				params.put("SUBREPORT_DIR5_MOSTRAR", "1");
			} else	
				params.put("SUBREPORT_DIR5_MOSTRAR", "0");
			
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_SIM)>=0) {
				params.put("SUBREPORT_DIR6", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource6.getPath())));
				params.put("SUBREPORT_DIR6_MOSTRAR", "1");
			} else	
				params.put("SUBREPORT_DIR6_MOSTRAR", "0");	
			/*
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_SCC)>=0) {
				params.put("SUBREPORT_DIR7", (JasperReport) JRLoader.loadObject(resource7.getFile()));
				params.put("SUBREPORT_DIR7_MOSTRAR", "1");
			} else	
				params.put("SUBREPORT_DIR7_MOSTRAR", "0");
			*/
			if(documentosSTOReporteErrores.indexOf(Constants.STO_TIPO_DOCUMENTO_CED)>=0) {
				params.put("SUBREPORT_DIR7", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource7.getPath())));
				params.put("SUBREPORT_DIR7_MOSTRAR", "1");
			} else	
				params.put("SUBREPORT_DIR7_MOSTRAR", "0");			
			
			params.put("nombreRegionZona", nombreRegionZona);
			params.put("tituloRegionZona", tituloRegionZona);
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.debug("this.isVisualizarReporte() "+this.isVisualizarReporte());
       
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String codigoRegion = MapUtils.getString(parameterMap, "codigoRegion", ""); 
		String codigoZona = MapUtils.getString(parameterMap, "codigoZona", ""); 
		String codigoPeriodo = MapUtils.getString(parameterMap, "codigoPeriodo", ""); 
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+ codigoRegion+ codigoZona  
					 + "_" +codigoPeriodo+"_"+
					sdf.format(new Date(System.currentTimeMillis()));

		return nombreArchivoReporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseReporteAbstractService#getMailService()
	 */
	public BaseMailService getMailService() {
		return mailService;
	}

	/**
	 * Sets the mail service.
	 *
	 * @param mailService the new mail service
	 */
	@Autowired
	@Qualifier("msg.mailReporteGenerarEnviarReporteErroresSTOService")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}

	
}
