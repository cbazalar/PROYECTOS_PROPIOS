package biz.belcorp.ssicc.reportes.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;

@Service("reportes.reporteCuentaCorrienteCampanhaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCuentaCorrienteCampanhaServiceImpl extends BaseReporteInterfaceImpl {	
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Resource(name="spusicc.consultaCOBTelecobroService")
	private ConsultaCOBTelecobroService consultaCOBTelecobroService; 
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {	
		log.debug("entro al beforeExecute");
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		
		params.put("numeroCampanias", Constants.NUMERO_DIESIOCHO);
		Map datos = consultaCOBTelecobroService.getDetalleConsultoraCampanha(params);
		
		List listaCabecera = (List)datos.get("cabecera");
		List listMovimientosCuentaCorriente = (List)datos.get("detalle");
		
		for(int i=0; i<listaCabecera.size(); i++)
		{
			String campaniaActual = MapUtils.getString((Map)listaCabecera.get(i), "codigo", "");
			
			params.put(String.format("campaniaNumero%d", i), campaniaActual);
			
			for(int j=0; j<listMovimientosCuentaCorriente.size(); j++)
			{
				Map registroActual = (Map)listMovimientosCuentaCorriente.get(j);
				
				Double valorCampanya = MapUtils.getDouble(registroActual, campaniaActual) ;
				
				if(valorCampanya != null)
				{
					registroActual.put(String.format("campaniaNumero%d", i), valorCampanya.doubleValue());
				}
			}
		}
		
		String codigoUsuario = MapUtils.getString(params, "codigoUsuario", "");
		consultaCOBTelecobroService.insertReporteHIPCuentaCorrientesCampanha(listMovimientosCuentaCorriente, codigoUsuario);

		reporteParams.setQueryParams(params);
		
		return reporteParams;		
	}
	
}
