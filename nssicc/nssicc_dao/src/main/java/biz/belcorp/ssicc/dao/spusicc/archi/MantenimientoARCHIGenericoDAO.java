package biz.belcorp.ssicc.dao.spusicc.archi;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.archi.model.EntidadBorradoPeriodicoAntFecha;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EtapaDeuda;

public interface MantenimientoARCHIGenericoDAO extends DAO{
	
	public List getListaTipoModulo(Map params);
	
	public List getEntidadPeriodicoAntiguoFechaList(Map params);
	
	public EntidadBorradoPeriodicoAntFecha getEntidadPeriodicoAntiguoFecha(Map params);
	
	public void insertEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean,Usuario usuario);
	
	public void updateEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean,Usuario usuario);
	
	public void deleteEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean,Usuario usuario);


}
