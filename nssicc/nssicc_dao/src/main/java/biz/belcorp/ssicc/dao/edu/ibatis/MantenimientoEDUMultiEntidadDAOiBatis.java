package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.MantenimientoEDUMultiEntidadDAO;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;

@Repository("edu.mantenimientoEDUMultiEntidadDAO")
public class MantenimientoEDUMultiEntidadDAOiBatis extends BaseDAOiBatis
		implements MantenimientoEDUMultiEntidadDAO {

	public List getMultiEntidadByCriteria(EntidadGenerico entidadGenerico) {
		return getSqlMapClientTemplate().queryForList("edu.MantenimientoEDUGenericoSQL.getMultiEntidadByCriteria", entidadGenerico);
	}

	public String insertMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.insertMultiEntidad", entidadGenerico);
		return null;
	}

	public void updateEstadoMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.updateEstadoMultiEntidad", entidadGenerico);
	}

	public String updateMultiEntidad(EntidadGenerico entidadGenerico, Usuario usuario) {
		getSqlMapClientTemplate().update("edu.MantenimientoEDUGenericoSQL.updateMultiEntidad", entidadGenerico);
		return null;
	}

}
