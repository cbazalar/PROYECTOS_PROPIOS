package biz.belcorp.ssicc.service.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface InterfazPREService extends Service 
{
	public void executeValidacionMatrizPlanit(Map params);
	
	public void envioCorreo(Map params);
	
	public Integer verificarErrorMatrizPlanit(Map params);
	
	public void executeInterfazPrCargaMatrizPlan(Map params);

}
