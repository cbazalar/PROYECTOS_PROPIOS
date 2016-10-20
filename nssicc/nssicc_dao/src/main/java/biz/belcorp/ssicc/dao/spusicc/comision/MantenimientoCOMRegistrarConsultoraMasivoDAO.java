package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Aurelio Oviedo
 *
 */
public interface MantenimientoCOMRegistrarConsultoraMasivoDAO extends DAO {
	
	public void executeObtenerListaZonasAsociadas(Map criteria);
	
	public void executeValidarRegistroxCodigoConsultora(Map criteria);
	
	public void insertarConsultoraNuevas(Map criteria);
	
	public void insertFacturacionPrograma (Map criteria);
	
	public String getIndicadorConstanciaProgramaAsociado(Map criteria);
	
	public void insertNivelesConsultoras(Map criteria);
	
	public List getListaMatrizEquivalente(Map criteria);
	
	public void updateConsultoraNuevas(Map criteria);
	
	public void updateConsultoraCupon(Map criteria);
	
	public void updateFacturacionPrograma (Map criteria);
	
	public void updateNivelesConsultoras(Map criteria);
	
	public String getPeriodoNSiguiente(Map criteria);
	
	public boolean existeNivelConsultora(Map criteria);
}