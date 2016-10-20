package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.FleteDetalle;

public interface MantenimientoPEDFleteDAO extends DAO{

	
	public List getTipoDespacho();

	public List getFleteList(Map map);

	public void deleteFlete(Flete flete, Usuario usuario);

	public int getNextOidFlete();

	public void insertFlete(Flete flete, Usuario usuario);

	public Flete getFlete(String idFlete);

	public void updateFlete(Flete flete, Usuario usuario);

	public void insertFleteDetalle(FleteDetalle detalle, Usuario usuario);

	public int getNextOidFleteDetalle();

	public void insertFleteAuditoria(Flete flete, Usuario usuario);

	public void insertFleteAuditoriaDetalle(FleteDetalle detalle, Usuario usuario);

	public FleteDetalle getDetalleFlete(String oidDetalleFlete);

	public void deleteFleteDetalle(FleteDetalle detalle, Usuario usuario);

	public void updateFleteDetalle(FleteDetalle detalle, Usuario usuario);

	public List getDetalleFleteList(String idFlete); 
}
