package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelSolicitudesCreditoValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCapturaSolicitudesDAO;

@Repository("spusicc.pedidos.mantenimientoOCRCapturaSolicitudesDAO")
public class MantenimientoOCRCapturaSolicitudesDAOiBatis extends
		BaseDAOiBatis implements MantenimientoOCRCapturaSolicitudesDAO {
	
	
	public void insertSolicitudesCreditoxZona(LabelSolicitudesCreditoValue objSolicitudes, Usuario usuario) {	
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertSolicitudesxZona", objSolicitudes);
		
	}
	
	public String getNumeroLoteByCriteria(Map criteriaLote) {
		String defecto = "";
		Base base = new Base();
		List aux = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getNumeroLoteByCriteria", criteriaLote);
		if (aux.size() > 0) {
			base = (Base) aux.get(0);
			defecto = base.getCodigo();
		}
		return defecto;
	}
	
	public LabelSolicitudesCreditoValue getPedidoxZonaExistente(Map criteriaPedidoxZona){
		return (LabelSolicitudesCreditoValue)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getPedidosxZonaByPK",
				criteriaPedidoxZona);
	}
	
	public List getSearchPedidosZonaByCriteria(Map criteriaPedidosZona){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCapturaSolicitudesCreditoByCriteria",
				criteriaPedidosZona);
	}
	
    public void updateCapturaSolicitudCredito(LabelSolicitudesCreditoValue solicitudPedidoCredito,Usuario usuario ){
    	 getSqlMapClientTemplate().update(
                 "spusicc.pedidos.PedidosSQL.updateCapturaSolicitudCredito", solicitudPedidoCredito);
    }
    
}