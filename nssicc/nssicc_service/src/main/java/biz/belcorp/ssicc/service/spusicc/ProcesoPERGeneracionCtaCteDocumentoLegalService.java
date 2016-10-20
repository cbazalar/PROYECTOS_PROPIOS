/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoPERGeneracionCtaCteDocumentoLegalService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:dtoledos@belcorp.biz">David Toledo Sanchez </a>
 */
public interface ProcesoPERGeneracionCtaCteDocumentoLegalService extends Service {

    /**
     * Genera los stickers para las ordenes de pedido que contengan productos de
     * tratamiento facial, haciendo el calculo de los puntos en base al factor y
     * al precio unitario del producto o tomando el valor del puntaje de la
     * matriz en caso sea un producto con puntaje fijo.
     * 
     * @param pais
     *            Informacion del Pais del Usuario.
     * @param usuario
     *            Usuario que ejecuta el proceso.
     */
    public Map executeGeneracionCtaCteDocumentoLegal(Pais pais, Usuario usuario, String codigoInterfaz, String tipOrigenDatos);

}
