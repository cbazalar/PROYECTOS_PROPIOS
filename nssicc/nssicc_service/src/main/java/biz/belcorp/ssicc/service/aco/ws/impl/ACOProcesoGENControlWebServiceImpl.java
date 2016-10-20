/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENControlWebService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENEjecutarInterfazWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ControlACOWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoGENEjecutarInterfazWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author sb
 */

public class ACOProcesoGENControlWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoGENControlWebService {

	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
		
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoGENEjecutarInterfazWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ControlACOWebService getControlACO() throws RemoteException {

		ControlACOWebService objetoRespuesta = new ControlACOWebService();
		
		if(log.isDebugEnabled()){
			log.debug("getControlACO");
		}
		try{
			Map criteria = new HashMap();
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		  
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			objetoRespuesta.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			objetoRespuesta.setFechaFacturacion(controlFacturacion.getFechaProceso());
	        
		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setCodigoPeriodo("");
			objetoRespuesta.setFechaFacturacion("");			
			//objetoRespuesta.setEjecucionExitosa(estado);
		}
		return objetoRespuesta;
	}

}
