package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.FactorPuntaje;

public interface MantenimientoLIDFactorPuntajeDAO extends DAO {
	
	public List getNumeroConcursoList(Map criteria);

	public List getFactorPuntajeList(Map criteria);

	public String getPeridoDesde(Map criteria);
	
	public String getPeridoHasta(Map criteria);

	public List getTipoAsignacionList();

	public void saveFactorPuntaje(FactorPuntaje factorPuntaje);

	public void updateFactorPuntaje(FactorPuntaje factorPuntaje);




}
