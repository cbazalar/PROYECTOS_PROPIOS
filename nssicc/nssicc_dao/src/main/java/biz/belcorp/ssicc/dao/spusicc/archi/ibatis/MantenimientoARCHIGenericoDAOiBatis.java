package biz.belcorp.ssicc.dao.spusicc.archi.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.archi.MantenimientoARCHIGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.archi.model.EntidadBorradoPeriodicoAntFecha;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB;

@Repository("spusicc.mantenimientoARCHIGenericoDAO")
public class MantenimientoARCHIGenericoDAOiBatis extends BaseDAOiBatis 
implements MantenimientoARCHIGenericoDAO{


	public List getListaTipoModulo(Map params) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.archi.mantenimientoARCHISQL.getListaTipoModulo",params);
	}

	
	public List getEntidadPeriodicoAntiguoFechaList(Map params) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.archi.mantenimientoARCHISQL.getEntidadPeriodicoAntiguoFechaList",params);
	}


	@Override
	public EntidadBorradoPeriodicoAntFecha getEntidadPeriodicoAntiguoFecha(Map params) {
		return (EntidadBorradoPeriodicoAntFecha) this.getSqlMapClientTemplate().queryForObject(
				"spusicc.archi.mantenimientoARCHISQL.getEntidadPeriodicoAntiguoFecha",params);
	}


	@Override
	public void insertEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean, Usuario usuario) {
		this.getSqlMapClientTemplate().insert(
				"spusicc.archi.mantenimientoARCHISQL.insertEntidadBorraPeriFecha", bean);
		
	}


	@Override
	public void updateEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.archi.mantenimientoARCHISQL.updateEntidadBorraPeriFecha", bean);
		
	}


	@Override
	public void deleteEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean, Usuario usuario) {
		this.getSqlMapClientTemplate().update(
				"spusicc.archi.mantenimientoARCHISQL.deleteEntidadBorraPeriFecha", bean);
		
	}
	
	

}
