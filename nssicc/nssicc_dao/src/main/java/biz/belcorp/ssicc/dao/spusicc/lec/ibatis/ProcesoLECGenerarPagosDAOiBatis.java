package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarPagosDAO;

@Repository("spusicc.procesoLECGenerarPagosDAO")
public class ProcesoLECGenerarPagosDAOiBatis   extends BaseDAOiBatis implements ProcesoLECGenerarPagosDAO{
	
	public List getTipoPago(Map criteria){
		return getSqlMapClientTemplate()
				.queryForList(
						"spusicc.lec.MantenimientoLECSQL.getTipoPago",criteria);
	}
	public List getTipoGanancia(Map criteria){
		return getSqlMapClientTemplate()
				.queryForList(
						"spusicc.lec.MantenimientoLECSQL.getTipoGanancia",criteria);
	}
	public void executeGenerarPagoRegular(Map params) {		
		 getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeGenerarPagoRegular", params);	
		
	}
	public void executeGenerarPagoAdicional(Map params){		
		 getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeGenerarPagoAdicional", params);	
			
	}
	
	public void insertGloblalTemporaryForGrupoUA(Map params) {
		 List lista = (List)params.get("codigoGrupoPago");
		 if(lista!=null){
			 for (int i = 0; i < lista.size(); i++) {
				 Map criteria = new HashMap();
				 criteria.put("codigo", lista.get(i));
				 getSqlMapClientTemplate().insert("spusicc.lec.ProcesosLECSQL.insertGloblalTemporaryForGrupoUA", criteria);
			 }
		 }
	}
	
	@Override
	public int getContadorGloblalTemporaryForGrupoUA() {
		Integer cont =  (Integer) getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.getContadorGloblalTemporaryForGrupoUA");
		return cont;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarPagosDAO#getTipoPago02(java.util.Map)
	 */
	public List getTipoPago02(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoPago02",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarPagosDAO#getTipoMotivoBloqueo(java.util.Map)
	 */
	public List getTipoMotivoBloqueo(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTipoMotivoBloqueo",params);
	}
	
	
}
