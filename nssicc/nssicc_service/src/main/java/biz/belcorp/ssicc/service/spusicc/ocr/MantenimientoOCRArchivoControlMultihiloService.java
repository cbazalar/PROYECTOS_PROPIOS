package biz.belcorp.ssicc.service.spusicc.ocr;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;


public interface MantenimientoOCRArchivoControlMultihiloService extends Service{
	
	public List getArchivoControlMultihilo(Map criteria);
	
	public List getArchivoControlMultihiloGeneral();
	
	public Map getArchivoControl(Map criteria);
	
	public void updateArchivoControl (Map criteria, Usuario usuario);
	
	public List getBasHistoLotes(Map criteria);

}
