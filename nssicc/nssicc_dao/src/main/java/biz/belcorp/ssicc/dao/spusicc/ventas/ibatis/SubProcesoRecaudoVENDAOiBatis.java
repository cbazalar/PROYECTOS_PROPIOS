package biz.belcorp.ssicc.dao.spusicc.ventas.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.SubProcesoRecaudoVENDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Proceso;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.SubProcesoRecaudo;
/**
 * Clase que implementa la interfaz SubProcesoRecaudoVENDAO
 * @author peextjnunez
 *
 */
@Repository("spusicc.SubProcesoRecaudoVENDAO")
public class SubProcesoRecaudoVENDAOiBatis extends BaseDAOiBatis implements SubProcesoRecaudoVENDAO{
	
	/**
	 * Metodo que  permite  obtener  un listado de procesos
	 * @return List
	 */
	public List getProceso(Proceso bproceso){
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.SubProcesoRecaudoVENSQL.getProceso",bproceso);
		return resultado;
	}
	
	/**
	 * Metodo que permite obtener un listado de Sub Proceso de Recaudo
	 * @param subproc - clase que contiene el criterio de busqueda
	 * @return List
	 */
    public List getSubProcesoRec(SubProcesoRecaudo subproc){
    	
    	List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.SubProcesoRecaudoVENSQL.getSubProcesoRecaudo",subproc);
		return resultado;
    	
    }
    
    public void insertSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario){
    	getSqlMapClientTemplate().insert(
				"spusicc.SubProcesoRecaudoVENSQL.insertSubProcesoRecaudo", subproc);	
    }
	public void deleteSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.SubProcesoRecaudoVENSQL.deleteSubProcesoRecaudo", subproc);
	}

}
