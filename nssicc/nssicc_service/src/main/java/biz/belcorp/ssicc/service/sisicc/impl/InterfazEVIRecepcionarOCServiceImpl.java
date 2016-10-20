package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazEVIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para la recepcin de Ordenes de Compra de la Interfaz EVI.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazEVIRecepcionarOCService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEVIRecepcionarOCServiceImpl extends
        BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazEVIDAO")
    private InterfazEVIDAO interfazEVIDAO;

    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        String fecha = (String) row.get("fecha");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            row.put("fecha", sdf.parse(fecha));
        } catch (ParseException e) {
            log.error("Error al formatear la fecha: " + fecha);
        }
        row
                .put("codigoPais", interfazParams.getQueryParams().get(
                        "codigoPais"));
        log.debug("row=" + row);
        interfazEVIDAO.insertInterfazEVIRecepcionarOC(row);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
     */
    protected void afterProcessInterfaz(InterfazParams interfazParams)
            throws InterfazException {
        log.debug("Dentro del metodo 'afterProcessInterfaz'");
        interfazEVIDAO.updateInterfazEVIRecepcionarOCSaldoDeudor(interfazParams
                .getQueryParams());
        interfazEVIDAO.cargarResumenesPrefacturacion();
        interfazEVIDAO.actualizaOCSConsultorasInactivas(interfazParams.getQueryParams());        
    }

    

    /**
     * Sobrescribimos el metodo que actualiza el status del objeto
     * InterfazResult para que no marque el resultado como incompleto cuando no
     * se ha encontrado el archivo de Primeros Pedidos
     * 
     * @param interfazResult
     * @param interfazException
     */
    protected void updateInterfazResultOnException(InterfazResult interfazResult,
            InterfazException interfazException) {
        if (log.isDebugEnabled())
            log.debug("Entering overwritten 'updateInterfazResult' method");
        
        // Si el error fue por no encontrar el archivo de Primeros Pedidos
        if (StringUtils.equals(interfazException.getMessage(),
                Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_FTP)
                || StringUtils.equals(interfazException.getMessage(),
                        Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_RED)) {
            interfazResult.setCompletado(true);
            interfazResult.setMensaje(interfazException.getMessage());
        } else {
            interfazResult.setCompletado(false);
            interfazResult.setMensaje(interfazException.getMessage());
        }
    }

}
