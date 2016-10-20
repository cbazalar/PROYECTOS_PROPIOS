package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.IncrementoVariableVenta;

public interface MantenimientoLIDIncrementoVariablesVentaDAO extends DAO {

	public List getTipoIncrementoList();

	public List getVariableVentasList(String codIdioma);

	public List getIncrementoVariableVentaList(Map criteria);

	public String getPeriodoMaximo(Map criteria);

	public void saveVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta);

	public void updateVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta);

	public void executeGenerarActividadFinalZonasPeriodo(Map params);

}
