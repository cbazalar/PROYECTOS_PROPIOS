package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public interface ProcesoPEJCargaProgramaEjecutivasDAO extends DAO {
	
public List getTipoCarga();
	
	public String getNumeroCarga();
	
	public void insertCargaProgramaEjecutivas(Map params);
	
	public void executeValidarCargaProgramaEjecutivas(Map params);
	
	public List getCargaProgramaEjecutivasList(Map params);
	
	public void executeActualizarCargaProgramaEjecutivas(Map params);
	
	public String getExisteClienteCargaProgramaEjecutivas(String codigoCliente);

	public void deleteCargaProgramaEjecutivas(String codigoUsuario);
}