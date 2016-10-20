package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDUnidadesAdministrativasChequeoDAO;

@Repository("spusicc.pedidos.mantenimientoPEDUnidadesAdministrativasChequeoDAO")
public class MantenimientoPEDUnidadesAdministrativasChequeoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDUnidadesAdministrativasChequeoDAO{

	public List getUnidadesAdministrativasChequeoList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getUnidadesAdministrativasChequeoList",map);
	}

	public void insertUnidadesAdministrativasChequeo(Map map) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertUnidadesAdministrativasChequeo",map);
	}

	public void deleteUnidadesAdministrativasChequeo(Map map) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteUnidadesAdministrativasChequeo",map);
	}
}