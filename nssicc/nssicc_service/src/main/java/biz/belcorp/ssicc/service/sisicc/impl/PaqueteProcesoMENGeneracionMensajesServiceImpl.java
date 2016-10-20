package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;

@Service("sisicc.paqueteProcesoMENGeneracionMensajesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteProcesoMENGeneracionMensajesServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl
{
	
	@Resource(name="spusicc.mantenimientoMENPlantillaService")
	private MantenimientoMENPlantillaService service;

	
	@Override
	protected void beforeExecuteInterfaz(Map params) throws Exception
	{
		//ejecutamos proecdimiento de llenado de temporal
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", params.get("codigoPeriodo"));
		Integer i=service.getDevuelveCampanaIndicadorCruce(criteria);
		if(i!=null){
			if(i==0){
				String campanaAnterior=service.getDevuelveCampanaAnterior(criteria);
				criteria.put("codigoPeriodo", campanaAnterior);
				Integer j=service.getDevuelveCampanaIndicadorCruce(criteria);
				if(j==1){
					params.put("indicadorCruce", j.toString());
					service.executeProcesoActualizarMensajes(params);
				}
			}
			if(i==1){
				params.put("indicadorCruce", i.toString());
				service.executeProcesoActualizarMensajes(params);
			}
		}
			
		service.executePedidoCliente(params);
		return;
		
	}
	
}
