package biz.belcorp.ssicc.service.spisicc.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoPedidoZona;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoPEDPedidoZonaHiloAbstractService;

/**
 * <p>
 * <a href="ProcesoPEDPedidoZonaMontoMaximoMultiHiloServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Gonzalo Javier Huertas Agurto</a>
 */
@Service("spisicc.procesoPEDPedidoZonaMontoMaximoMultiHiloService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDPedidoZonaMontoMaximoMultiHiloServiceImpl extends BaseProcesoPEDPedidoZonaHiloAbstractService {

	/**
	 * Metodo ejecutado antes de 'processInterfaz'. Este mtodo no tiene
	 * implementacin, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional antes del procesamiento de la Interfaz.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {

		Map params = interfazParams.getQueryParams();
		List listaZonas = procesoHiloPedidoZonaService.getListaZonasByCampanaFechaFacturacion(params);
		params.put("listaZonas", listaZonas);
	}

    /**
     * @param params
     */
    public void executeHilo(ProcesoPedidoZona proceso){
        try {
        	Map criteria = new HashMap();
        	criteria.put("oidZona", proceso.getOidZona());
        	criteria.put("fechaFacturacion", proceso.getFechaFacturacion());
        	criteria.put("codigoPeriodo", proceso.getCodigoPeriodo());
        	List pedidos = procesoHiloPedidoZonaService.getListaPedidosByZonasCampanaFechaFacturacion(criteria);
        	
        	if(pedidos!=null && pedidos.size()>0){
        		Map criteriaProceso = new HashMap();
        		criteriaProceso.put("codigoUsuario", proceso.getCodigoUsuario());
        		for(int i=0;i<pedidos.size();i++){
        			criteriaProceso.put("oidPedido", MapUtils.getString((HashMap)pedidos.get(i), "oidPedido"));
        			procesoHiloPedidoZonaService.executeMontoMaximoPedidoZonaMultihilo(criteriaProceso);
        		}
        	}
		} catch (Exception e) {
			proceso.setSuccess(false);
			proceso.setMensajeError(e.toString());
		}
    }

}