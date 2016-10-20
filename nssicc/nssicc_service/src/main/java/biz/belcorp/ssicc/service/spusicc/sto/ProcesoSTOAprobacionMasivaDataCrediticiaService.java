package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoSTOAprobacionMasivaDataCrediticiaService extends Service{
	
	//Valida que el codigo de la Consultora exista
	Integer getValidaSolicCodigoConsultora(String value);
	
	//Modifica el ind Situacion Crediticia
	public void updateSolicCodigoConsultora(Map criteria);

}
