/*
 * Created on 26/12/2005 11:36:36 AM
 * biz.belcorp.ssicc.scdf.service.UltimasNoticiasService
 */
package biz.belcorp.ssicc.service.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface GeneracionDetallePedidoService extends Service {

    /**
     * Ejecuta el proceso de generacion de Detalle de Pedido
     * 
     * @param codigoPais
     */
    public void executeGenerarDetallePedido(Map params);
    
    /**
     * @return
     * Devuelve la lista de tipos de solicitud para cargar un combo
     */
    public List getTipoSolicitud();
}
