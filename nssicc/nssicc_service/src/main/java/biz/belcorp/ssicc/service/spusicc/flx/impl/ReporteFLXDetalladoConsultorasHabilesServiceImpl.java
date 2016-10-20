/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.flx.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.flx.ReporteFLXDetalladoConsultorasHabilesService;

/**
 * Service para el proceso de Reporte Detallado de Consultoras Habiles
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("spusicc.reporteFLXDetalladoConsultorasHabilesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteFLXDetalladoConsultorasHabilesServiceImpl extends BaseSubReporteAbstractService implements ReporteFLXDetalladoConsultorasHabilesService {

	@Resource(name = "ccc.mailReporteFLXDetalladoConsultorasHabilesService")
	private BaseMailService mailService;
	
	@Resource(name = "genericoService")
	private GenericoService genericoService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseSubReporteAbstractService#getSubReporteFileName()
	 */
	protected String getSubReporteFileName() {
		return "reporteFLXDetalladoConsultorasHabilesPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getReporteFileName()
	 */
	protected String getReporteFileName() {
		return "reporteMaestroVertical";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseSubReporteAbstractService#prepareParameterMap(java.util.Map)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String titulo = messageSource.getMessage("reporteFLXConsultorasHabilesSeccionForm.titulo", null, getLocale(usuario));
		params.put("titulo", titulo);
		
		String codigoPeriodo = (String) params.get("codigoPeriodo");
		params.put("codigoCampana", codigoPeriodo);
		
		Pais pais = (Pais)params.get("pais");
		
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(MapUtils.getString(params, "codigoPais"));
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocaleIdiomaNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		ParametroPais parametro = null;
		String idiomaReporte = pais.getCodigoIdiomaIso();
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			idiomaReporte = parametro.getValorParametro();
		}
		params.put("idiomaReporte", idiomaReporte);
		
		parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(MapUtils.getString(params, "codigoPais"));
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocatePaisNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		lstParametros = genericoService.getParametrosPais(parametroPais);
		
		String paisReporte = pais.getCodigoPaisIso();
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			paisReporte = parametro.getValorParametro();
		}
		params.put("paisReporte", paisReporte);
		
		log.debug("prepareParameterMap "+this.isVisualizarReporte());
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String codigoZona = (String)parameterMap.get("codigoZona");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+ codigoZona + sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}
	
	/* (non-Javadoc)
	 * Se sobreescribe este metodo para forzar a pasar el locale de MX
	 * para que puedan salir correctamente el formato a numero (###,###,##0.00)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#configReporteParams(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void configReporteParams(ReporteParams reporteParams) {
		Map params = reporteParams.getQueryParams();
		Map parameterMap = (Map)params.get("parameterMap");
		String idiomaReporte = (String)parameterMap.get("idiomaReporte");
		String paisReporte = (String)parameterMap.get("paisReporte");
		
		Locale locale = new Locale(idiomaReporte, paisReporte);
		params.put(JRParameter.REPORT_LOCALE, locale);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseReporteAbstractService#getMailService()
	 */
	public BaseMailService getMailService() {
		return this.mailService;
	}

	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}
	
}
