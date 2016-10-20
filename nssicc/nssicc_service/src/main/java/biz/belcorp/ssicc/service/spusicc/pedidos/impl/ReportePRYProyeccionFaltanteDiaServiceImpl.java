package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPRYProyeccionFaltanteDiaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ReportePRYProyeccionFaltanteDiaService;


@Service("spusicc.reportePRYProyeccionFaltanteDiaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReportePRYProyeccionFaltanteDiaServiceImpl  extends BaseSubReporteAbstractService implements ReportePRYProyeccionFaltanteDiaService{
	
    private BaseMailService mailService;
    
    @Resource(name="spusicc.procesoPRYProyeccionFaltanteDiaService")
    private ProcesoPRYProyeccionFaltanteDiaService serviceProy;
    
    protected String getReporteFileName() {    	
    	return "reportePRYProyFaltXLS";
//			if ("1".equals(tipoReporte))
//				return "reportePRYProyFaltXLS";
//			else
//				return "reportePRYProyFaltDetalleXLS";
//		else
//			return "reporteMaestroHorizontal";
	}
    
    @Override
    protected void beforeExecuteReporte(ReporteParams reporteParams)
    		throws Exception {
    	super.beforeExecuteReporte(reporteParams);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		String descripcionPeriodo = serviceProy.getDevuelveDescripcionPeriodo(params);
		params.put("descripcionPeriodo", descripcionPeriodo);
    }
	
    protected String getSubReporteFileName() {
    	return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteOCREnvioCargaDocumento.error";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception{
		super.prepareParameterMap(params);
		

		params.put("tipoPresentacion", params.get("tipoPresentacion"));
		params.put("NroReporte", " ");

		/* colocando titulo */
		if ("1".equals(params.get("tipoReporte"))) {
//			params.put("titulo", getMessageReporte(
//					"reportePRYProyeccionFaltanteDiaForm.titulo1"));
		} else if ("2".equals(params.get("tipoReporte"))) {
//			params.put("titulo", getMessageReporte(
//					"reportePRYProyeccionFaltanteDiaForm.titulo2"));
		}
			
		
	}
		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;		
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		nombreArchivoReporte = this.getPrefijoArchivo() +  "_" +
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
	@Qualifier("ocr.mailReporteOCRCargaDocumento")
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}	

}
