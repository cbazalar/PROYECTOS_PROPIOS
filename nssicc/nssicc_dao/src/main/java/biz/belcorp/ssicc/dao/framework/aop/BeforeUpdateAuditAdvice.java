/*
 * Created on 14/03/2005 12:05:43 PM
 *
 * biz.belcorp.privilege.dao.jdbc.BeforeUpdateAdvice
 */
package biz.belcorp.ssicc.dao.framework.aop;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

import biz.belcorp.ssicc.dao.framework.Auditor;
import biz.belcorp.ssicc.dao.framework.Auditable;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BeforeUpdateAdvice.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class BeforeUpdateAuditAdvice implements MethodBeforeAdvice {

    protected final Log log = LogFactory.getLog(getClass());

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method,
     *      java.lang.Object[], java.lang.Object)
     */
    public void before(Method m, Object[] args, Object target) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Before update advice . . .");
        }
        
        // Verificamos que el target sea una instancia de Auditor
        if (target instanceof Auditor) {
            if (log.isDebugEnabled()) {
                log.debug("Target es una instancia de interface Auditor");
            }

            try {
                // Verificamos que el primer parametro sea una instancia
                // de la interface Auditable
                Object o = args[0];

                if (o instanceof Auditable) {
                    if (log.isDebugEnabled()) {
                        log
                                .debug("El objeto es una instancia de interface Auditable");
                    }

                    Object u = args[1];
                    if (u instanceof Usuario) {
                        String username = ((Usuario) u).getLogin();
                        String ip = ((Usuario) u).getIpMaquinaRemota();                        
                        Auditable a = (Auditable) o;
                        a.getAuditInfo().setUpdatedBy(username);
                        a.getAuditInfo().setLastUpdated(
                                new Timestamp(System.currentTimeMillis()));
                        a.getAuditInfo().setIpMaquinaRemota(ip);
                    }
                }
            }
            catch (IndexOutOfBoundsException ioobe) {
                log
                        .warn("El nmero de parmetros del mtodo no esta de acuerdo a la lgica de este Advice");
            }
        }

    }

}

