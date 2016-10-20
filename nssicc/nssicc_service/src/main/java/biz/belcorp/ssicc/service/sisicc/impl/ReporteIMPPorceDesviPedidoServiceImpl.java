package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.ReporteIMPPorceDesviPedidoService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteIMPPorceDesviPedidoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:yrivas@sigcomt.com">Yahir Rivas Luna</a>
 */
@Service("sisicc.reporteIMPPorceDesviPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteIMPPorceDesviPedidoServiceImpl extends BaseSubReporteAbstractService implements ReporteIMPPorceDesviPedidoService {
	
	@Resource(name="mae.mailReportePorceDesviPedido")
	private BaseMailService mailService;
	
	@Resource(name="genericoService")
    private GenericoService genericoService;
	
  	protected String getReporteFileName() {
		return "reporteIMPPorceDesviPedidoXLS";
	}
	
	protected String getSubReporteFileName() {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteEDUGenerarResumenProgramacion.error.nivelGenerarPlanilla";
	}


	protected void beforeExecuteReporte(ReporteParams reporteParams)
			throws Exception {
		super.beforeExecuteReporte(reporteParams);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");		
		
		if (MapUtils.getBooleanValue(params, "execute") == Boolean.TRUE) {
			Map criteria = new HashMap();
			criteria.put("fechaFacturacion",MapUtils.getString(params, "fechaFacturacion"));
			criteria.put("codigoPais",MapUtils.getString(params, "codigoPais"));
			criteria.put("desviacion",StringUtils.replace(MapUtils.getString(params, "desviacion"),"%",""));
			criteria.put("promedio",MapUtils.getString(params, "promedio"));
			
			reporteService.executeReporteIMPPorceDesviPedido(criteria);
		}
		

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		Pais pais = (Pais)params.get("pais");
		String titulo = messageSource.getMessage("reporteIMPPorceDesviPedidoForm.titulo",null,getLocale(usuario));
		log.debug("titulo");
		
        ParametroPais parametroPais = new ParametroPais();
		
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.IMP_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.IMP_CODIGO_PARAMETRO_PEDIDO_PROMEDIO);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);

		List lstParametrosPromedio = genericoService.getParametrosPais(parametroPais);
		
		parametroPais.setCodigoParametro(Constants.IMP_CODIGO_PARAMETRO_PORCENTAJE_DESVI);
		
		List lstParametrosPorcentaje = genericoService.getParametrosPais(parametroPais);
		
		ParametroPais parametrosPromedio = null;
		ParametroPais parametrosPorcentaje = null;
		if(CollectionUtils.size(lstParametrosPromedio)==1 && CollectionUtils.size(lstParametrosPorcentaje)==1){
			parametrosPromedio = (ParametroPais) lstParametrosPromedio.get(0);
			parametrosPorcentaje = (ParametroPais) lstParametrosPorcentaje.get(0);

			String fechaFacturacion = (String)params.get("fechaFacturacion");
			params.put("codigoPais", pais.getCodigo());
			params.put("fechaFacturacion", fechaFacturacion);
			params.put("desviacion", parametrosPorcentaje.getValorParametro().concat("%"));
			params.put("promedio", parametrosPromedio.getValorParametro());
			
			params.put("NroReporte", "");
			params.put("titulo", titulo);
			params.put("formatoExportacion","VXLS");		
			log.debug("this.isVisualizarReporte() "+this.isVisualizarReporte());	
			params.put("execute", Boolean.TRUE);
		}else{
			params.put("execute", Boolean.FALSE);	
		}

       
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+ 
					sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}

	/**
	 * @return the mailService
	 */
	public BaseMailService getMailService() {
		return mailService;
	}


	
    
}
