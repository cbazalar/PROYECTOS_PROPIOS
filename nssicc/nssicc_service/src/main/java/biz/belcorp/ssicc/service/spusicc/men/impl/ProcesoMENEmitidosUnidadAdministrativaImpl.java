package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spusicc.men.ReporteMSGGenerarMensajesEmitidosUnidadAdminService;


/**
 * Service para el proceso que Genera mensajes gestionado por el usuario
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("spusicc.procesoMENEmitidosUnidadAdministrativaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMENEmitidosUnidadAdministrativaImpl extends
	BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;
	
	@Resource(name="spusicc.reporteMSGGenerarMensajesEmitidosUnidadAdminService")
	ReporteMSGGenerarMensajesEmitidosUnidadAdminService service;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
	 log.debug("inicio ProcesoMENEmitidosUnidadAdministrativaImpl " + params);
	 //SE TRATA D EUNA EMISON DE REPORTES NO ES NESESARIOP PARA TODOS LOS PROCESOS
	 //INVOLUCRADOS SOLO BASTA CON UNO	
	 List listCorreoGz = procesoMENGenerarMensajesDAO.getCorreosGz(params);
	 String codigoPeriodo = (String)params.get("codigoPeriodo");
	 Iterator it = listCorreoGz.iterator();
		 while(it.hasNext()){
			 Map map = (Map)it.next();
			 String correoGerenteZona = (String)map.get("correoGerenteZonales");
			 BigDecimal oidCliente = (BigDecimal)map.get("oidCliente");					 
			 String fechaCierre = (String)map.get("fechaCierre");
			 String codigoRegion = (String)map.get("codigoRegion");
			 String codigoZona = (String)map.get("codigoZona");
			 params.put("correoGerenteZona", correoGerenteZona);
			 params.put("oidCliente", new Long(String.valueOf(oidCliente)));
			 params.put("fechaCierre", fechaCierre);
			 params.put("codigoRegion", codigoRegion);
			 params.put("codigoZona", codigoZona);
			 try {
				service.setCodigoPeriodo(codigoPeriodo); 
				service.grabarReporte(params);
				//return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //procesoMENGenerarMensajesDAO.executaProcesoMensajes(params);
		 }
	}


	
}