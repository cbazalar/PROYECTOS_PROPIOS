package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOMotivoRechazoDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MotivoRechazo;

@Repository("spusicc.mantenimientoSTOMotivoRechazoDAO")
public class MantenimientoSTOMotivoRechazoDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOMotivoRechazoDAO
{
	@Override
	public List getMotivoRechazoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sto.MantenimientoSTOSQL.getMotivoRechazoList", criteria);
	}

	@Override
	public void insertMotivoRechazo(MotivoRechazo rechazo, Usuario usuario) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertMotivoRechazo", rechazo);		
	}

	@Override
	public void updateMotivoRechazo(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updateMotivoRechazo", criteria);			
	}

	@Override
	public void deleteMotivoRechazo(Map criteria) {
		getSqlMapClientTemplate().delete("sto.MantenimientoSTOSQL.deleteMotivoRechazo", criteria);
	}

	@Override
	public MotivoRechazo getMotivoRechazo(Map criteria) {
		return (MotivoRechazo) this.getSqlMapClientTemplate().queryForObject(
				"sto.MantenimientoSTOSQL.getMotivoRechazo", criteria);
	}	

}
