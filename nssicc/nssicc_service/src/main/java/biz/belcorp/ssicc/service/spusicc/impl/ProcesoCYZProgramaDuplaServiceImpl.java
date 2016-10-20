package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazCYZDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoCYZProgramaDuplaService;

@Service("spusicc.procesoCYZProgramaDuplaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCYZProgramaDuplaServiceImpl extends BaseService implements ProcesoCYZProgramaDuplaService {
    
	@Resource(name="sisicc.interfazCYZDAO")
    InterfazCYZDAO interfazCYZDAO;

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.service.ProcesoCYZProgramaDuplaService#executeEnviarMensajeProgramaDupla(java.util.Map)
     */
    public void executeEnviarMensajeProgramaDupla(Map params) {
        // Variable para transmitir los parametros
        Map map = new HashMap();
        String codigoPais = MapUtils.getString(params, "codigoPais");
        String codigoPeriodo = MapUtils.getString(params, "codigoPeriodo");
        String codigoPrograma = MapUtils.getString(params, "codigoPrograma");
        String codigoProgramaPremio = MapUtils.getString(params, "codigoProgramaPremio");
        String codigoProgramaCumpleanyos = MapUtils.getString(params, "codigoProgramaCumpleanyos");
        String codigoProgramaWelcomePack = MapUtils.getString(params, "codigoProgramaWelcomePack");
        String codigoProgramaCumpleanyosConsultoras = MapUtils.getString(params, "codigoProgramaCumpleanyosConsultoras");
        String codigoUsuario = MapUtils.getString(params, "codigoUsuario");
        String indicadorValidarClasificacion = Constants.NO;
        
        // Colocamos los parametros comunes
        map.put("codigoPais", codigoPais);
        map.put("codigoPeriodo", codigoPeriodo);
        map.put("codigoUsuario", codigoUsuario);
        map.put("indicadorValidarClasificacion", indicadorValidarClasificacion);

        // Primero enviamos los mensajes relacionados al producto
        map.put("codigoPrograma", codigoPrograma);
        interfazCYZDAO.executeProcesoCYZEnviarMensajes(map);
        
        // Luego los mensajes relacionados al premio
        map.put("codigoPrograma", codigoProgramaPremio);
        interfazCYZDAO.executeProcesoCYZEnviarMensajes(map);
        
        // Luego los mensajes relacionados al Welcome Pack
        // CHR - 18/03/2010 Se comenta al antiguo Welcome Pack
        // interfazCYZDAO.executeProcesoCYZEnviarMensajesWelcomePack(map);
        
        // Mensajes relacionados con el nuevo Welcome Pack
        map.put("codigoPrograma", codigoProgramaWelcomePack);
        interfazCYZDAO.executeProcesoCYZEnviarMensajes(map);
        // tambien para los mensajes de error 
        interfazCYZDAO.executeProcesoCYZEnviarMensajesError(map);
        
        // Para los mensajes de las siguientes estrategias s� consideramos
        // que las consultoras pertenezcan a la clasificacion correspondiente
        indicadorValidarClasificacion = Constants.SI;
        map.put("indicadorValidarClasificacion", indicadorValidarClasificacion);
        
        // Finalmente los mensajes relacionados a la Oferta de Cumplea�os de Duplas
        map.put("codigoPrograma", codigoProgramaCumpleanyos);
        interfazCYZDAO.executeProcesoCYZEnviarMensajes(map);
        // tambien para los mensajes de error 
        interfazCYZDAO.executeProcesoCYZEnviarMensajesError(map);

        // Finalmente los mensajes relacionados a la Oferta de Cumplea�os de Consultoras
        map.put("codigoPrograma", codigoProgramaCumpleanyosConsultoras);
        interfazCYZDAO.executeProcesoCYZEnviarMensajes(map);
        // tambien para los mensajes de error 
        interfazCYZDAO.executeProcesoCYZEnviarMensajesError(map);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.service.ProcesoCYZProgramaDuplaService#executeProcesarClasificacionProgramaDupla(java.util.Map)
     */
    public void executeProcesarClasificacionProgramaDupla(Map params) {
    	
        // Invocamos al procedure encargado de realizar la actualizacion
        interfazCYZDAO.executeProcesoCYZActualizarClasificacion(params);
        
        // Invocamos al proceso que carga las primeras duplas de un cliente
        interfazCYZDAO.executeProcesoCYZActualizarPrimerasDuplas(params);
        
        // Invocamos al proceso que carga las duplas que cumplen a�os
        interfazCYZDAO.executeProcesoCYZActualizarCumpleanyosDuplas(params);
        
        // Invocamos al proceso que carga las consultoras que cumplen a�os
        interfazCYZDAO.executeProcesoCYZActualizarCumpleanyosConsultoras(params);
        
        // Actualizamos las clasificaciones del Welcome Pack
        // CHR 18/03/2010 - Se invoca a la nueva logica del Welcome Pack
        // vigente a partir del periodo 201005
        interfazCYZDAO.executeProcesoCYZActualizarClasificacionWelcomePack(params);
        
        // Actualizamos la bolsa de productos disponibles de ser solicitados
        // para las duplas inscritas y asociadas al Welcome Pack
        interfazCYZDAO.executeProcesoCYZCargarBolsaProductosWelcomePack(params);
        
        // Actualizamos la bolsa de productos disponibles de ser solicitados
        // para las duplas que cumplen a�os
        interfazCYZDAO.executeProcesoCYZCargarBolsaProductosCumpleanyosDuplas(params);
        
        // Actualizamos la bolsa de productos disponibles de ser solicitados
        // para las consultoras que cumplen a�os
        interfazCYZDAO.executeProcesoCYZCargarBolsaProductosCumpleanyosConsultoras(params);
        
        // Actualizamos las clasificaciones de la oferta de cumplea�os de duplas
        interfazCYZDAO.executeProcesoCYZActualizarClasificacionCumpleanyos(params);

        // Actualizamos las clasificaciones de la oferta de cumplea�os de consultoras
        interfazCYZDAO.executeProcesoCYZActualizarClasificacionCumpleanyosConsultoras(params);

    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoCYZProgramaDuplaService#executeProcesoCYZAActualizarProductosPrograma(java.util.Map)
	 */
	public void executeProcesoCYZAActualizarProductosPrograma(Map params) {
		// Invocamos al proceso encargado de realizar la carga de la informacion
		interfazCYZDAO.executeProcesoCYZAActualizarProductosPrograma(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoCYZProgramaDuplaService#getProgramasListByCriteria(java.util.Map)
	 */
	public List getProgramasListByCriteria(Map criteria) {
		return interfazCYZDAO.getProgramasListByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoCYZProgramaDuplaService#getProductosProgramaListByCriteria(java.util.Map)
	 */
	public List getProductosProgramaListByCriteria(Map criteria) {
		return interfazCYZDAO.getProductosProgramaListByCriteria(criteria);
	}
    
	
}
