package biz.belcorp.ssicc.dao.spusicc.ocr;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;


public interface MantenimientoOCRArchivoControlMultihiloDAO extends DAO{
	
	public List getArchivoControlMultihilo(Map criteria);
	
	public List getArchivoControlMultihiloGeneral();
	
	public Map getArchivoControl(Map criteria);
	
	public void updateArchivoControl(Map criteria, Usuario usuario);
	
	public List getBasHistoLotes(Map criteria);

}
