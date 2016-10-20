package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.OpcionDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Opcion;
import biz.belcorp.ssicc.dao.model.Usuario;

@Repository("opcionDAO")
public class OpcionDAOiBatis extends BaseDAOiBatis implements OpcionDAO{

	public List getOpcionesByCriteria(Opcion criteria){
		  List paises = getSqlMapClientTemplate().queryForList("OpcionSQL.getOpcionByCriteria", criteria);
	        return paises;
	}
	
	public void insertOpcion(Opcion opcion, Usuario usuario){
        Map params = new HashMap();
        params.put("codigoPais", opcion.getCodigoOpcion());
		Opcion opciones=(Opcion)getNextPKOpcion(params);
		opcion.setCodigoOpcion(opciones.getCodigoOpcion());
		getSqlMapClientTemplate().update("OpcionSQL.insertOpcion", opcion);
	}
	
	public void updateOpcion(Opcion opcion, Usuario usuario){
		 getSqlMapClientTemplate().update("OpcionSQL.updateOpcion", opcion);	
	}
	
	public void deleteOpcion(final String codigo){
		getSqlMapClientTemplate().update("OpcionSQL.removeOpcion", codigo);
	}
	
    public Opcion getNextPKOpcion(Map params) {
        // Creamos el objeto a devolver
        Opcion opcion = (Opcion) getSqlMapClientTemplate().queryForObject("OpcionSQL.getNextPKOpcion", params);
        return opcion;
    }
	
	
}
