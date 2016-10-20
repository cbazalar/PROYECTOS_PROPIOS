package biz.belcorp.ssicc.service.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCYZProgramaDuplaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 */
public interface ProcesoCYZProgramaDuplaService extends Service {

    /**
     * Proceso encargado de actualizar la clasificacion de las consultoras
     * que cuentan con una Dupla Cyzone y que ademas depende de si han adquirido
     * o no un producto (CySet) en las ultimas campa�as
     * @param params
     */
    public void executeProcesarClasificacionProgramaDupla(Map params);
    
    /**
     * Proceso que actualiza la informacion de la entidad que almacena la 
     * informacion de los productos asociados a un programa de dupla, para 
     * ello hace la busqueda en la matriz de las ofertas que tienen la 
     * clasificacion asociada al programa (venta exclusiva).
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZAActualizarProductosPrograma(Map params);
    
    /**
     * Proceso encargado de a�adir mensajes al buzon para aquellas
     * consultoras que han adquirido productos de un determinado 
     * programa.
     * @param params
     */
    public void executeEnviarMensajeProgramaDupla(Map params);

	/**
	 * Obtiene la relacion de programas en base a criterios de busqueda.
	 * 
	 * @param criteria
	 *            Objeto map con los criterios de busqueda.
	 * @return Listado de objetos map con la informacion obtenida.
	 */
    public List getProgramasListByCriteria(Map criteria);

    /**
	 * Obtiene la relacion de los productos asociados a un programa en base a
	 * criterios de busqueda.
	 * 
	 * @param criteria
	 *            Objeto map con los criterios de busqueda.
	 * @return Listado de objetos map con la informacion obtenida.
	 */
    public List getProductosProgramaListByCriteria(Map criteria);
    
}
