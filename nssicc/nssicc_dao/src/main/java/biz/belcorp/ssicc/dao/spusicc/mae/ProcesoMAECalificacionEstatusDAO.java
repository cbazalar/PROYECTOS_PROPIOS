package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 *
 */
public interface ProcesoMAECalificacionEstatusDAO extends DAO {
	
	public boolean verificarCargaInicialEstatus(String codigoPais);
	
	public void executeCalificacionEstatusCargaInicial(Map criteria);
	
	public void executeCalificacionEstatusFacturacionDiaria(Map criteria);

	public void executeCalificacionEstatusCierreRegion(Map criteria);

	public void executeCalificacionEstatusCierreCampana(Map criteria);

	public String getUltimaFechaCierreRegion(Map criteria);
	
	public List getRegionesCerradas(Map criteria);
	
	public List getTipoProcesoList();
	
	public List getClasificacionEstatusList(Map params);
	
	public void executeProcesoAtendidos(Map params);
	
	public void deleteClasificacionEstatusList(Map params);

	
}
