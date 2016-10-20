package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoSTOAprobacionMasivaDataCrediticiaDAO extends DAO {
	
	Integer getValidaSolicCodigoConsultora(String value);
	
	public void updateSolicCodigoConsultora(Map criteria);

}
