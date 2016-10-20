package biz.belcorp.ssicc.service;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Opcion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

public interface OpcionService extends Service{

	
	public List getOpcionesByCriteria(Opcion criteria);
	
	public void insertOpcion(Opcion opcion, Usuario usuario);
	
	public void updateOpcion(Opcion opcion, Usuario usuario);
	
	public void deleteOpcion(final String codigo);
	
}
