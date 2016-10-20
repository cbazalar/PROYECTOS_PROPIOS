/*
 * Created on 24/01/2007 11:40:17 AM
 * biz.belcorp.ssicc.spisicc.service.ProcesoImpresionService
 */
package biz.belcorp.ssicc.service.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface ProcesoImpresionService extends Service {

    /**
     * Obtiene una relacion de los procesos de impresion tomando como criterio
     * de busqueda los valores de los atributos del objeto pasado como
     * parametro.
     * 
     * @param procesoImpresion
     *            Objeto usado para enviar los criterios de busqueda.
     * @return Lista de objetos ProcesoImpresion
     */
    public List getProcesosImpresion(ProcesoImpresion procesoImpresion);

    /**
     * Obtiene una relacion de los procesos de impresion tomando como criterio
     * de busqueda los valores enviados a travs de un objeto tipo map.
     * 
     * @param criteria
     * @return Lista de objetos ProcesoImpresion
     */
    public List getProcesosImpresionByCriteria(Map criteria);

    /**
     * Obtiene la informacion de un proceso de impresion.
     * 
     * @param codigo
     *            Codigo del proceso de impresion a obtener.
     * @return Objeto conteniendo la informacion solicitada.
     */
    public ProcesoImpresion getProcesoImpresion(String codigo);

    /**
     * Inserta la informacin de un nuevo Proceso de Impresion.
     * 
     * @param procesoImpresion
     *            Objeto conteniendo la informacion a registrar.
     * @param usuario
     *            Usuario que realiza la transaccion.
     */
    public void insertProcesoImpresion(ProcesoImpresion procesoImpresion,
            Usuario usuario);

    /**
     * Actualiza la informacin de un Proceso de Impresion.
     * 
     * @param procesoImpresion
     *            Objeto conteniendo la informacion a registrar.
     * @param usuario
     *            Usuario que realiza la transaccion.
     */
    public void updateProcesoImpresion(ProcesoImpresion procesoImpresion,
            Usuario usuario);
    
    
    /**proceso que genera calculo inter mora
	 * @param params
	 */
	public void executeCalculoInterMora();
	
	public boolean validacionLimiteTiempoEjecucionProceso();


}
