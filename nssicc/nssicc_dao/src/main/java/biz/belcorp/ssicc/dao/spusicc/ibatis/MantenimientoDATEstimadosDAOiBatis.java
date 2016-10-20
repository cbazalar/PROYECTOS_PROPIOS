/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoDATEstimadosDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;

/**
  * 
 * <p>
 * <a href="MantenimientoDATEstimadosDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 * 
 */
@Repository("spusicc.mantenimientoDATEstimadosDAO")
public class MantenimientoDATEstimadosDAOiBatis extends BaseDAOiBatis implements MantenimientoDATEstimadosDAO {

    public String getPeriodoInicio(String codigoRangoPeriodo) {
        String periodoInicio = (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoInicio",codigoRangoPeriodo);
        return  periodoInicio;
    }
    public String getPeriodoFin(String codigoRangoPeriodo) {
        String periodoFin = (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoFin",codigoRangoPeriodo);
        return  periodoFin;
    }
    
    public List getEstimadosByCriteria(Map criteria) {
        List fuenteVentas = getSqlMapClientTemplate().queryForList("spusicc.InterfazDATSQL.getEstimadosByCriteria", criteria);
        return fuenteVentas;
	}  
    
	public void insertEstimado(Estimado estimado, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.InterfazDATSQL.insertEstimado", estimado);
		
	}
	public void updateEstimado(Estimado estimado, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.updateEstimado", estimado);
		
	}
	public Estimado getEstimadosById(Estimado estimado) {
		Estimado resultado = (Estimado) getSqlMapClientTemplate().queryForObject(
				"spusicc.InterfazDATSQL.getEstimadosById",
				estimado);
		return resultado;
	}
    
}



