/*
 * Created on 06-ene-09 15:44:34
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazCYZRecepcionarProductosSolicitadosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCYZDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCYZRecepcionarProductosSolicitadosServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazCYZRecepcionarProductosSolicitadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCYZRecepcionarProductosSolicitadosServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazCYZDAO")
    protected InterfazCYZDAO interfazCYZDAO;

    

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
     */
    protected void beforeReadData(InterfazParams interfazParams)
            throws InterfazException {
        // Eliminamos la informacion de la tabla antes de la carga
        interfazCYZDAO.deleteProductosSolicitados(interfazParams.getQueryParams());
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
     */
    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        // Reemplazamos el codigo del pais enviado por el usado por el sistema
        row.put("codigoPais", interfazParams.getInterfaz().getCodigoPais());
        if(StringUtils.isNotBlank(MapUtils.getString(interfazParams.getQueryParams(), "codigoPrograma"))) {
            row.put("codigoPrograma", MapUtils.getString(interfazParams.getQueryParams(), "codigoPrograma"));
        }
        row.put("codigoUsuario", interfazParams.getUsuario().getLogin());
        
        // Insertamos la linea en la base de datos
        interfazCYZDAO.insertProductoSolicitado(row);
    }
    
}
