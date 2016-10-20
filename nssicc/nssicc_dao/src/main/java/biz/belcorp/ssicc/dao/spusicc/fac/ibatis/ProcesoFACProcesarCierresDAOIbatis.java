package biz.belcorp.ssicc.dao.spusicc.fac.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.fac.ProcesoFACProcesarCierresDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.ProcesoFACProcesarCierresDAO")
public class ProcesoFACProcesarCierresDAOIbatis extends BaseDAOiBatis implements ProcesoFACProcesarCierresDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#getZonasACerrar(java.util.Map)
	 */
	public List getZonasACerrar(Map criteria) {
		List result =(List)getSqlMapClientTemplate().
							queryForList("spusicc.facturacion.MantenimientoFACSQL.getZonasACerrar",criteria);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#getRegionesACerrar(java.util.Map)
	 */
	public List getRegionesACerrar(Map criteria) {
		List result =(List)getSqlMapClientTemplate().
							queryForList("spusicc.facturacion.MantenimientoFACSQL.getRegionesACerrar",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#getProcesosCierreZona(java.util.Map)
	 */
	public List getProcesosCierreZona(Map criteria) {
		List result =(List)getSqlMapClientTemplate().
							queryForList("spusicc.facturacion.MantenimientoFACSQL.getProcesosCierreZona",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#getProcesosCierreRegion(java.util.Map)
	 */
	public List getProcesosCierreRegion(Map criteria) {
		List result =(List)getSqlMapClientTemplate().
							queryForList("spusicc.facturacion.MantenimientoFACSQL.getProcesosCierreRegion",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#existeZonasxRegionSinProcesar(java.util.Map)
	 */
	public boolean existeZonasxRegionSinProcesar(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.facturacion.MantenimientoFACSQL.getZonasxRegionSinProcesar", criteria);
        
        if(Integer.parseInt(result)==0)
        	return true;
        else
        	return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#validaCierreZona(java.util.Map)
	 */
	public boolean validaCierreZona(Map criteria) {
		String result = (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.facturacion.MantenimientoFACSQL.getValidaCierreZona", criteria);
		
		if(result.equals("OK"))
			return true;
		else
			return false;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#validaCierreRegion(java.util.Map)
	 */
	public boolean validaCierreRegion(Map criteria) {
		String result = (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.facturacion.MantenimientoFACSQL.getValidaCierreRegion", criteria);
		
		if(result.equals("OK"))
			return true;
		else
			return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#validaCierrePeriodo(java.util.Map)
	 */
	public boolean validaCierrePeriodo(Map criteria) {
		String result = (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.facturacion.MantenimientoFACSQL.getValidaCierrePeriodo", criteria);
		
		if(result.equals("OK"))
			return true;
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#getProcesosCierrePeriodo(java.util.Map)
	 */
	public List getProcesosCierrePeriodo(Map criteria) {
		List result =(List)getSqlMapClientTemplate().
							queryForList("spusicc.facturacion.MantenimientoFACSQL.getProcesosCierrePeriodo",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#validaPeriodoACerrar(java.util.Map)
	 */
	public boolean validaPeriodoACerrar(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.facturacion.MantenimientoFACSQL.getPeriodoACerrar", criteria);
        
        if(Integer.parseInt(result)==1)
        	return true;
        else
        	return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#existeRegionesSinProcesar(java.util.Map)
	 */
	public boolean existeRegionesSinProcesar(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.facturacion.MantenimientoFACSQL.getRegionesSinProcesar", criteria);
        
        if(Integer.parseInt(result)==0)
        	return true;
        else
        	return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#getIndicadorModEducacion(java.lang.String)
	 */
	public String getIndicadorModEducacion(String codigoPais) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getIndicadorModEducacion", codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.ProcesoFACProcesarCierresDAO#getProcesosControlCierre(java.util.Map)
	 */
	public List getProcesosControlCierre(Map criteria) {
		List result =(List)getSqlMapClientTemplate().
							queryForList("spusicc.facturacion.MantenimientoFACSQL.getProcesosControlCierre",criteria);
		return result;
	}
	
}