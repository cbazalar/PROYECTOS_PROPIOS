package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoZONCallesService extends Service {

	public List getDireccionesClientesList(Map criteria);

	public void insertZonCalle(Map criteria);

	public Map getZonCalle(Long oidCalle);

	public void updateZonCalle(Map criteria);

	public void deleteZonCalle(Map criteria);
	
	public String getValidaConsultoraCalle(Map criteria);
}
