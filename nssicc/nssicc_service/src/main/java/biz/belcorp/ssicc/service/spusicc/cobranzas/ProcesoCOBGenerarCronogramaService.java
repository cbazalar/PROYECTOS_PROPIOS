/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBGenerarCronogramaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBGenerarCronogramaService extends Service {

	/**
	 * Ejecuta el proceso de Generar Cronograma (Cobranzas)
	 * @param criteria
	 */
	public void executeGenerarCronograma (Map criteria);
	
	/**
	 * Obtiene el ronograma de Carteras en base a los filtros del map 
	 * @param criteria
	 * @return
	 */
	public List getCronogramaByPaisSociedadEtapaPeriodo(Map criteria);

}
