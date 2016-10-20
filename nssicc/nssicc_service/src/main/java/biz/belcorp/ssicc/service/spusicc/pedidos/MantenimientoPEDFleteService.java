package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.FleteDetalle;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPEDFleteService extends Service {

	
	public List getTipoDespacho();

	public List getFleteList(Map map);

	public void deleteFlete(Flete flete, Usuario usuario);
	
	public void deleteFleteDetalle(FleteDetalle fleteDetalle, Usuario usuario);

	public int getNextOidFlete();

	public void insertFlete(Flete cabecera, List detalle, Usuario usuario);

	public Flete getFlete(String idFlete);

	public void updateFlete(Flete cabecera, List detalle, Usuario usuario);

	public List getDetalleFleteList(String oidFlete);

}
