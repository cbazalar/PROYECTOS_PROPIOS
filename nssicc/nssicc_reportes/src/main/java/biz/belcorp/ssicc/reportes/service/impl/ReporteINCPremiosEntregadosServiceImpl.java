package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;



@Service("reportes.reporteINCPremiosEntregadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteINCPremiosEntregadosServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		super.beforeExecuteReporte(reporteParams, formReporte);		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		

		String[] consultoras = (String[]) params.get("consultoras");
		String[] concursos = (String[]) params.get("concursos");
		String[] programas = (String[]) params.get("programas");
		
		if(consultoras != null && consultoras.length > 0){
			String consultorasEncontradas = obtieneCondicionIN(consultoras, "", "");
			if(StringUtils.isEmpty(consultorasEncontradas))
				params.put("consultoras", " ");
			else
				params.put("consultoras", " AND sc.copa_oid_para_gene " + consultorasEncontradas);
		}else{
			if(concursos != null && concursos.length > 0)
				params.put("concursos", obtieneCondicionIN(concursos, "", ""));

			if(programas != null && programas.length > 0)
				params.put("programas", obtieneCondicionIN(programas, "", ""));
			
			reporteService.executePremiosEntregados(params);
		}
		
		reporteParams.setQueryParams(params);
		return reporteParams;
	
	}	
	
	protected String obtieneCondicionIN(String[] lista, String parametro,
			String comilla ) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ") ";
			resultado = " IN " + resultado;
			return resultado;
		}
	}
}
