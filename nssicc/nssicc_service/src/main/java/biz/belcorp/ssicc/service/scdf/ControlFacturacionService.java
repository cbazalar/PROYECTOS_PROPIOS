/*
 * Created on 06/03/2006 04:38:45 PM
 * biz.belcorp.ssicc.scdf.service.ControlFacturacionService
 */
package biz.belcorp.ssicc.service.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlFacturacionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface ControlFacturacionService extends Service {

   
    /**
     * Obtiene un listado de los controles de facturacin de acuerdo a los
     * criterios de bsqueda que son pasados a travs de un map.
     * 
     * @param criteria Objeto map conteniendo los criterios de bsqueda.
     * @return Lista de objetos ControlFacturacion
     */
    public List getControlesFacturacionByCriteria(Map criteria);

    /**
     * Obtiene la informacin del control de facturacin de un determinado pas.
     * 
     * @param codigoPais
     *            Valor del cdigo del pas.
     * @return el objeto ControlFacturacion del pais correspondiente.
     */
    public ControlFacturacion getControlFacturacion(String codigoPais);

    /**
     * Ingresa un nuevo registro de control de facturacin.
     * 
     * @param controlFacturacion
     *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
     */
    public void insertControlFacturacion(ControlFacturacion controlFacturacion,
            Usuario usuario);

    /**
     * Actualiza la informacin del control de facturacin en el sistema.
     * 
     * @param controlFacturacion
     *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la informacin
     */
    public void updateControlFacturacion(ControlFacturacion controlFacturacion,
            Usuario usuario);

}
