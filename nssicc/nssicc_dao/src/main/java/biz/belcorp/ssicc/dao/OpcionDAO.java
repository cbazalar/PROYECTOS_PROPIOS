package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Opcion;
import biz.belcorp.ssicc.dao.model.Usuario;

public interface OpcionDAO extends DAO{

	
	public List getOpcionesByCriteria(Opcion criteria);
	
	public void insertOpcion(Opcion opcion, Usuario usuario);
	
	public void updateOpcion(Opcion opcion, Usuario usuario);
	
	public void deleteOpcion(final String codigo);
	
	public Opcion getNextPKOpcion(Map params);
	
	
}
