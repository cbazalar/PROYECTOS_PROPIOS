package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoRECCargarTablaBoletaRecojoEspecialDAO extends DAO {

	public void executeProcesoRECCargarTablaBoletaRecojoEspecial(Map params);
	
	public List executeVerificacionTablaBoletaRecojoEspecial(Map params);
	
	public String getDescripcionProducto(Map params);
	
	public void insertTablaCodigosVenta( Map params);

}
