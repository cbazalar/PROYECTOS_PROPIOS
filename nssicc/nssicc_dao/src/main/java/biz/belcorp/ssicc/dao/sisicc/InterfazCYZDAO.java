/*
 * Created on 12-dic-08 16:10:12
 * biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Interfaz que define los metodos para extraer la informacion de los
 * programas de dupla cyzone, la informacion para las interfaces y la
 * ejecucion de los procesos relacionados.
 * <p>
 * <a href="InterfazCYZDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface InterfazCYZDAO extends DAO {
    
    /**
     * Proceso encargado de obtener los productos despachados en un
     * determinado periodo y fecha de facturacion y que a su vez estan
     * asociados a un programa de dupla.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeInterfazCYZCargarDespachoProductos(Map params);
    
    /**
     * Proceso encargado de obtener los premios despachados en un
     * determinado periodo y fecha de facturacion y que a su vez estan
     * asociados a un programa de dupla.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeInterfazCYZCargarDespachoPremios(Map params);
    
    /**
     * Proceso que genera el archivo que envia la informacion de los
     * productos despachados para que sea tomado por Biztalk y enviado
     * a los perifericos.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeInterfazCYZEnviarDespachoProductos(Map params);
    
    /**
     * Proceso encargado de actualizar las clasificaciones asociadas a un
     * programa, de tal forma que las consultoras que tengan asignada dicha
     * clasificacion puedan acceder o no a la venta exclusiva de una determinada
     * oferta.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZActualizarClasificacion(Map params);

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
     * Proceso encargado de insertar mensajes en el buzon de aquellas
     * consultoras que hayan adquirido un producto asociado a un programa
     * y cuya solicitud este en GP4.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZEnviarMensajes(Map params);
    
    /**
     * Limpia la entidad que contiene los productos de cyzone
     * solicitados por las duplas y que son enviados desde la web
     * a travs de una interfaz.
     * @param params
     */
    public void deleteProductosSolicitados(Map params);
    
    /**
     * Inserta la informacin de un producto solicitado por una
     * dupla desde la web la cual es enviada a traves de una 
     * interfaz.
     * @param map
     */
    public void insertProductoSolicitado(Map map);

    /**
     * Proceso encargado de obtener las primeras duplas inscritas
     * de una consultora, en una determinada campaa para ser usadas
     * por los procesos del welcome pack.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZActualizarPrimerasDuplas(Map params);

    /**
     * Proceso encargado de actualizar las clasificaciones de los clientes
     * que han inscrito su primera dupla y se les permite por tanto solicitar
     * los descuentos del welcome pack.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZActualizarClasificacionWelcomePack(Map params);

    /**
     * Proceso encargado de obtener los productos despachados en un
     * determinado periodo y fecha de facturacion y que a su vez estan
     * asociados a los programas del welcome pack.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeInterfazCYZCargarDespachoWelcomePack(Map params);
    
    /**
     * Proceso encargado de insertar mensajes en el buzon de aquellas
     * consultoras que hayan adquirido un producto asociado a un programa
     * del welcome pack y cuya solicitud este en GP4.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZEnviarMensajesWelcomePack(Map params);
    
    /**
     * Proceso encargado de obtener la relacion de las duplas que 
     * cumplen aos en una determinada campaa.
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZActualizarCumpleanyosDuplas(Map params);
    
    /**
	 * Proceso que actuliza las clasificaciones de las consultoras cuyas duplas
	 * cumplen aos en un determinado periodo y que cumplen con las condiciones
	 * para solicitar las ofertas asociadas a esta estrategia.
	 * 
	 * @param params
	 */
    public void executeProcesoCYZActualizarClasificacionCumpleanyos(Map params);
    
    /**
     * Proceso encargado de insertar mensajes de error en el buzon de aquellas
     * consultoras que NO hayan adquirido un producto asociado a un programa
     * y cuya solicitud este en GP4.
     * 
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZEnviarMensajesError(Map params);

	/**
	 * Procedimiento que inserta los productos del programa de bienvenida en la
	 * bolsa de productos de tal manera que se pueda llevar el control de las
	 * unidades despachadas a lo largo de los periodos en los cuales la
	 * consultora tiene posibilidad de solicitarlos.
	 * 
	 * @param params
	 *            Objeto map con la informacion necesaria para le ejecucion del
	 *            proceso.
	 */
    public void executeProcesoCYZCargarBolsaProductosWelcomePack(Map params);

	/**
	 * Procedimiento que inserta los productos del programa de cumpleaos de
	 * dupla en la bolsa de productos de tal manera que se pueda llevar el
	 * control de las unidades despachadas a lo largo de los periodos en los
	 * cuales la consultora tiene posibilidad de solicitarlos.
	 * 
	 * @param params
	 *            Objeto map con la informacion necesaria para le ejecucion del
	 *            proceso.
	 */
    public void executeProcesoCYZCargarBolsaProductosCumpleanyosDuplas(Map params);

	/**
	 * Procedimiento que inserta los productos del programa de cumpleaos de
	 * consultoras en la bolsa de productos de tal manera que se pueda llevar el
	 * control de las unidades despachadas a lo largo de los periodos en los
	 * cuales la consultora tiene posibilidad de solicitarlos.
	 * 
	 * @param params
	 *            Objeto map con la informacion necesaria para le ejecucion del
	 *            proceso.
	 */
    public void executeProcesoCYZCargarBolsaProductosCumpleanyosConsultoras(Map params);
    
    /**
	 * 
	 * @param params
	 *            Objeto map con la informacion necesaria para le ejecucion del
	 *            proceso.
	 */
    public void executeProcesoCYZActualizarUnidadesAtendidasBolsa(Map params);
    
    /**
     * Proceso encargado de obtener la relacion de las consultoras que 
     * cumplen aos en una determinada campaa.
     * @param params Objeto map con la informacion necesaria para le ejecucion
     * del proceso.
     */
    public void executeProcesoCYZActualizarCumpleanyosConsultoras(Map params);
    
    /**
     * Proceso que actuliza las clasificaciones de las consultoras que cumplen aos
     * en un determinado periodo y que cumplen con las condiciones para solicitar
     * las ofertas asociadas a esta estrategia.
     * 
     * @param params
     */
    public void executeProcesoCYZActualizarClasificacionCumpleanyosConsultoras(Map params);

    /**
	 * Obtiene la relacion de los programas en base a criterios de busqueda.
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
