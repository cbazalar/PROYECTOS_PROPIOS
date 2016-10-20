package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
import biz.belcorp.ssicc.service.spusicc.sto.ReporteSTOEnvioCentroAcopioFacturadoDetalleService;

@Service("spusicc.reporteSTOEnvioCentroAcopioFacturadoDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSTOEnvioCentroAcopioFacturadoDetalleServiceImpl extends	BaseSubReporteAbstractService implements ReporteSTOEnvioCentroAcopioFacturadoDetalleService{
	
    private BaseMailService mailService;
    
	protected String getReporteFileName() {
		return "reporteMaestroVertical";
	}
	
	protected String getSubReporteFileName() {
		return "reporteSTOCentroAcopioFacturadoDetallePDF";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteSTOCentroAcopioAuto.error";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteSTOCentroAcopioFacturado.titulo.detalle",null,getLocale(usuario));
		log.debug("titulo");
		params.put("titulo", titulo);
		params.put("formatoExportacion","VPDF");
		
		//params.put("SUBREPORT_DIR1", Constants.JASPER_DIRECTORIO + "subReporteSTOCentroAcopioFueraPedidoPDF" + JASPER_EXTENSION);
		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReporteSTOCentroAcopioFueraPedidoPDF"
						+ JASPER_EXTENSION);
		params.put(
				"SUBREPORT_DIR1",(JasperReport)
				JRLoader.loadObject(this.getClass().getClassLoader()
						.getResource(resource.getPath())));
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String codCentroAcopio = MapUtils.getString(parameterMap, "codigoCentroAcopio", ""); 
		String codCiaTransporte = MapUtils.getString(parameterMap, "codigoCiaTransporte", ""); 
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		nombreArchivoReporte = getPrefijoArchivo()+ "_(DP)_" + 
								StringUtils.trim(codCiaTransporte)+ "_" + 
								StringUtils.trim(codCentroAcopio) + "_" + 
								sdf.format(new Date(System.currentTimeMillis()));

		return nombreArchivoReporte;
		
	}

	/**
	 * @return the mailService
	 */
	public BaseMailService getMailService() {
		return mailService;
	}

	/**
	 * @param mailService the mailService to set
	 */
	@Autowired
	@Qualifier("sto.mailReporteSTOCentroAcopioFacturadoService")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}	
}
