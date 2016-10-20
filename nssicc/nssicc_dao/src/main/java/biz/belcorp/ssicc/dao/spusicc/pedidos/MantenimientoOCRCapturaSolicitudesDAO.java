package biz.belcorp.ssicc.dao.spusicc.pedidos;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.LabelSolicitudesCreditoValue;
import biz.belcorp.ssicc.dao.model.Usuario;


public interface MantenimientoOCRCapturaSolicitudesDAO extends DAO {

	public void insertSolicitudesCreditoxZona(LabelSolicitudesCreditoValue objSolicitudes, Usuario usuario);
	public String getNumeroLoteByCriteria(Map criteriaLote);
	public LabelSolicitudesCreditoValue getPedidoxZonaExistente(Map criteriaPedidoxZona);
	public List getSearchPedidosZonaByCriteria(Map criteriaPedidosZona);
    public void updateCapturaSolicitudCredito(LabelSolicitudesCreditoValue solicitudPedidoCredito,Usuario usuario );
    
}
