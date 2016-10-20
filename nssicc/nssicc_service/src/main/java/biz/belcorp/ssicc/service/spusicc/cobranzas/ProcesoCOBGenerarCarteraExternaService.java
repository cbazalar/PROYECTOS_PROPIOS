/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.FtpCobrador;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBGenerarCarteraExterna.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBGenerarCarteraExternaService extends Service {
	 	
	public void executeGenerarSCCartera(Map params,FtpCobrador ftpCobrador) throws Exception;

}
