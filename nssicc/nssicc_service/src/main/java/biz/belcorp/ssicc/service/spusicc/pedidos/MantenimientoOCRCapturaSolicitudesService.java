package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.LabelSolicitudesCreditoValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;


public interface MantenimientoOCRCapturaSolicitudesService extends Service {
	
    /**
     * Obtiene un listado de todos los  Archivos de Control de Facturacion  
     * los cuales son enviados a travs de un Map.
     * 
     * @param criteria
     *            Objeto Map cuyos atributos son usados como criterios de
     *            bsqueda.
     * 
     * @return Lista de objetos Map, poblados.
     */
 
    /**
     * Inserta un objeto de Pedido
     * @param
     */
   
    public void insertSolicitudesCreditoxZona(ArrayList objListaSolicitudes, Usuario usuario);    
    public String getNumeroLoteByCriteria(Map criteriaLote);
    public LabelSolicitudesCreditoValue getPedidoxZonaExistente(Map criteriaPedidoxZona);
    public List getSearchPedidosZonaByCriteria(Map criteriaPedidosZona);
    public void updateCapturaSolicitudCredito(LabelSolicitudesCreditoValue solicitudPedidoCredito,Usuario usuario );
	public void executeActualizaNumeroLoteMica(Map criteria);	
}