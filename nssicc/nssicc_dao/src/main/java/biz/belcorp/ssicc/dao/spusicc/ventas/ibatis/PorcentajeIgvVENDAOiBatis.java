package biz.belcorp.ssicc.dao.spusicc.ventas.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.PorcentajeIgvVENDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.PorcentajeIgv;

/**
 * Clase implementadora de la interfaz PorcentajeIgvVENDAO
 * @author peextjnunez
 *
 */

@Repository("spusicc.porcentajeIgvVenDAO")			 
public class PorcentajeIgvVENDAOiBatis extends BaseDAOiBatis implements PorcentajeIgvVENDAO{
	/**
	 * Metodo que permite obtener un listado de de PorcentajesIgv
	 * de acuerdo al criterio ingresado
	 * @param bporcentajeigv - contiene los criterios de busqueda
	 * @return List - Listado de objetos del tipo PorcentajeIgv
	 */
	public List getPorcentajeIgv(PorcentajeIgv bporcentajeigv){
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.PorcentajeIgvVENSQL.getPorcentajeIgv",bporcentajeigv);
		return resultado;
	}
	/**
	 * Metodo que permite actualizar un registro de 
	 * Porcentaje Igv
	 * @param bporcentajeigv - contiene la informacion a ser actualizada
	 */
    public void updatePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario){
    	
    	getSqlMapClientTemplate().update(
				"spusicc.PorcentajeIgvVENSQL.updatePorcentajeIgv", bporcentajeigv);
    	
    }
    /**
	 * Metodo que permite registrar un Nuevo Porcentaje IGV
	 * @param bporcentajeigv - contiene la informacion del nuevo registro
	 */
	public void insertPorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.PorcentajeIgvVENSQL.insertPorcentajeIgv", bporcentajeigv);
	}
	/**
	 * Metodo que permite eliminar un Porcentaje IGV
	 * Porcentaje Igv
	 * @param bporcentajeigv - contiene la informacion del registrio a eliminar
	 */
	public void deletePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario){
		
		getSqlMapClientTemplate().insert(
				"spusicc.PorcentajeIgvVENSQL.deletePorcentajeIgv", bporcentajeigv);
		
	}

}
