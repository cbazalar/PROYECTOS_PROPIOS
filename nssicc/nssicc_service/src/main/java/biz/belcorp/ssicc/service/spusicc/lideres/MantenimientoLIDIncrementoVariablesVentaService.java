package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.lideres.model.IncrementoVariableVenta;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoLIDIncrementoVariablesVentaService extends Service {

	public List getVariableVentasList(String codIdioma);

	public List getTipoIncrementoList();

	public List getIncrementoVariableVentaList(Map criteria);

	public String getPeriodoMaximo(Map criteria);

	public void updateVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta);

	public void saveVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta);

	public void executeGenerarActividadFinalZonasPeriodo(Map params);
	
	public Map getTipoIncrementoMap(List variableVentaList);


	

}
