package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDGenerarInformacionLideresCierrePeriodoDAO;

@Repository("spusicc.procesoLIDGenerarInformacionLideresCierrePeriodoDAO")
public class ProcesoLIDGenerarInformacionLideresCierrePeriodoDAOIbatis extends BaseDAOiBatis implements 
		ProcesoLIDGenerarInformacionLideresCierrePeriodoDAO {

	public void executeGenerarInformacionLideresCierrePeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeGenerarInformacionLideresCierrePeriodo",params);
		
	}


}
