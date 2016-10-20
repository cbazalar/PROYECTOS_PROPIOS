package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
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
 * Service para la recepcin de Primeros Pedidos de la Interfaz EVI.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 */
@Service("sisicc.interfazOCRRecepcionarPrimerosPedidosCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarPrimerosPedidosCorporativoServiceImpl extends
        BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazEVIDAO")
    private InterfazEVIDAO interfazEVIDAO;

    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        try {
            String codigoPais = MapUtils.getString(interfazParams.getQueryParams(), "codigoPais");
            String codigoPeriodo = MapUtils.getString(interfazParams.getQueryParams(), "codigoPeriodo");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date fecha = dateFormat.parse((String) row.get("fecha"));
            row.put("fecha", fecha);
            row.put("codigoPais", codigoPais);
            row.put("periodo", codigoPeriodo);
            List primerosPedidosList = interfazEVIDAO.getInterfazEVIRecepcionarPrimerosPedidos(row);
            if (primerosPedidosList.size() == 0) {
                interfazEVIDAO.insertInterfazEVIRecepcionarPrimerosPedidos(row);
            } else {
                // Actualizamos el valor del numero de primeros pedidos
                Map oldRow = (Map)primerosPedidosList.get(0);
                // Obtenemos las cantidades
                int numeroPedidosActual = MapUtils.getIntValue(oldRow, "numeroPrimerosPedidos", 0);
                int numeroPedidosAdicional = MapUtils.getIntValue(row, "numeroPrimerosPedidos", 0);
                // Seteamos el nuevo valor en el map 'row'
                Integer numeroPrimerosPedidos = new Integer(numeroPedidosActual + numeroPedidosAdicional);
                row.put("numeroPrimerosPedidos", numeroPrimerosPedidos);
                
                // Actualizamos la informacion en la base de datos
                interfazEVIDAO.updateInterfazEVIRecepcionarPrimerosPedidos(row);
            }
        } catch (ParseException e) {
            log.error("Formato de fecha invalido, no se procesara la linea");
        } catch (Exception e) {
            log.error("Ocurrio un error al procesar la linea: "
                    + e.getMessage());
        }
    }

    protected void afterProcessInterfaz(InterfazParams interfazParams)
            throws InterfazException {
        interfazEVIDAO.cargarResumenesPrefacturacion();
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
