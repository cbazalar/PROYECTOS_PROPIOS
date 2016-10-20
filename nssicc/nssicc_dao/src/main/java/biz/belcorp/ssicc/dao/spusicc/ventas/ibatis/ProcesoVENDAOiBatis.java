package biz.belcorp.ssicc.dao.spusicc.ventas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Calendario;
import biz.belcorp.ssicc.dao.sisicc.model.FeriadoZona;
import biz.belcorp.ssicc.dao.spusicc.ventas.ProcesoVENDAO;

@Repository("spusicc.procesoVENDAO")
public class ProcesoVENDAOiBatis extends BaseDAOiBatis implements ProcesoVENDAO {

	public void executeGenerico(String nombreExecute, Map criteria) {
		String lsEjecutar = "spusicc.ProcesosVENSQL." + nombreExecute;
		getSqlMapClientTemplate().update(lsEjecutar, criteria);
        return;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ProcesoVENServiceImpl#getCalendario(map)
	 */
	public List getCalendarios(Map criteria)  {
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosVENSQL.getCalendarios",	criteria);
		return resultado;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ProcesoVENServiceImpl#getCalendario(map)
	 */
	public Calendario getCalendario(Map criteria)  {
		Calendario resultado = (Calendario) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosVENSQL.getCalendario",	criteria);
		return resultado;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#updateCalendario(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCalendario(Calendario calendario, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosVENSQL.updateCalendario", calendario);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ProcesoVENServiceImpl#getFeriadoZona(map)
	 */
	public List getFeriadoZona(Map criteria)  {
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosVENSQL.getFeriadoZona",	criteria);
		return resultado;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#updateFeriadoZona(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateFeriadoZona(FeriadoZona feriadoZona, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosVENSQL.updateFeriadoZona", feriadoZona);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#insertFeriadoZona(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertFeriadoZona(FeriadoZona feriadoZona, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosVENSQL.insertFeriadoZona", feriadoZona);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#deleteFeriadoZona(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteFeriadoZona(FeriadoZona feriadoZona, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosVENSQL.deleteFeriadoZona", feriadoZona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.ProcesoVENDAO#getZonasRegion(java.lang.String)
	 */
	public List getZonasRegion(String feriadoRegion){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosVENSQL.getZonasRegion",	feriadoRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.ProcesoVENDAO#getIndicadorHabilitacionRuv(java.util.Map)
	 */
	public String getIndicadorHabilitacionRuv(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosVENSQL.getIndicadorHabilitacionRuv", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.ProcesoVENDAO#executeGeneracionReporteRUV(java.util.Map)
	 */
	public void executeGeneracionReporteRUV(Map map) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosVENSQL.executeGeneracionReporteRUV", map);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.ProcesoVENDAO#executeGenerarArchivosLibroVentasDetalleSII(java.util.Map)
	 */
	public void executeGenerarArchivosLibroVentasDetalleSII(Map map) {
		getSqlMapClientTemplate().update("spusicc.ProcesosVENSQL.executeGenerarArchivosLibroVentasDetalleSII",map);
	}
	
}
