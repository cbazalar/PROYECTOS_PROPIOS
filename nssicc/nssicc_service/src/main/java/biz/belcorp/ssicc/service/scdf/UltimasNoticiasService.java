/*
 * Created on 26/12/2005 11:36:36 AM
 * biz.belcorp.ssicc.scdf.service.UltimasNoticiasService
 */
package biz.belcorp.ssicc.service.scdf;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface UltimasNoticiasService extends Service {

        
    /**
     * Ejecuta el proceso de impresion de Ultimas Noticias Privilege
     * 
     * @param codigoPais
     *            Codigo de pais a procesar
     */
    public void printUltimasNoticias(final String codigoPais);

    /**
     * Ejecuta el proceso de generacion de Ultimas Noticias
     * 
     * @param codigoPais
     */
    public void executeGenerarUltimasNoticias(final String codigoPais, Usuario usuario);

}
