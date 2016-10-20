/*
 * Created on 12-dic-08 15:33:26
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazCYZEnviarDespachoProductosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazCYZDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Interfaz que genera el archivo que envia la informacion de los
 * productos despachados para que sea tomado por Biztalk y enviado
 * a los perifericos.
 * <p>
 * <a href="InterfazCYZCysetDuplaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazCYZEnviarDespachoProductosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCYZEnviarDespachoProductosServiceImpl extends
        BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazCYZDAO")
	protected InterfazCYZDAO interfazCYZDAO;

    

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
     */
    protected void beforeProcessInterfaz(InterfazParams interfazParams)
            throws InterfazException {
        Usuario usuario = interfazParams.getUsuario();
        Map queryParams = interfazParams.getQueryParams();
        String codigoUsuario = usuario.getLogin();
        queryParams.put("codigoUsuario", codigoUsuario);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
     */
    protected void executeStoreProcedure(Map params) {
    	String codigoProgramaCumpleanyos;
    	String codigoProgramaCumpleanyosConsultoras;
    	String codigoProgramaWelcomePack;
    	
        // Ejecutamos el proceso que carga la informacion de productos
        interfazCYZDAO.executeInterfazCYZCargarDespachoProductos(params);
        
        // Ejecutamos el proceso que carga la informacion de premios
        interfazCYZDAO.executeInterfazCYZCargarDespachoPremios(params);
        
        // Ejecutamos el proceso que carga la informacion de productos del welcome pack
        // CHR - 18/03/2010 Se comenta la ejecucion del antiguo welcome pack
        // interfazCYZDAO.executeInterfazCYZCargarDespachoWelcomePack(params);
        
        // Actualizamos la bolsa de productos asociados al Nuevo Welcome Pack
        codigoProgramaWelcomePack = MapUtils.getString(params, "codigoProgramaWelcomePack");
        params.put("codigoPrograma", codigoProgramaWelcomePack);
        interfazCYZDAO.executeProcesoCYZActualizarUnidadesAtendidasBolsa(params);

        // Generamos el archivo
        interfazCYZDAO.executeInterfazCYZEnviarDespachoProductos(params);
        
        // Hacemos la carga de la informacion de las ofertas de 
        // cumpleaos de duplas que han sido despachadas
        codigoProgramaCumpleanyos = MapUtils.getString(params, "codigoProgramaCumpleanyos");
        params.put("codigoPrograma", codigoProgramaCumpleanyos);
        interfazCYZDAO.executeInterfazCYZCargarDespachoProductos(params);
        
        // Hacemos la carga de la informacion de las ofertas de 
        // cumpleaos de consultoras que han sido despachadas
        codigoProgramaCumpleanyosConsultoras = MapUtils.getString(params, "codigoProgramaCumpleanyosConsultoras");
        params.put("codigoPrograma", codigoProgramaCumpleanyosConsultoras);
        interfazCYZDAO.executeInterfazCYZCargarDespachoProductos(params);
        
    }

}
