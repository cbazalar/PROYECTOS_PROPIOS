package org.sistema.framework.dao.auditoria;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.sistema.framework.dao.Auditable;
import org.sistema.framework.dao.Auditor;
import org.sistema.framework.dao.seguridad.model.Usuario;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {
	  	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Busca objeto tipo Uusuario
	 * @param arg
	 * @return
	 */
	private int buscarObjectoUsuario(Object[] args) {
		int intObjetoAuditable = -1;
		for (int i=0; i < args.length; i++) {
			Object u = args[i];
		    if (u instanceof Usuario) {
		    	intObjetoAuditable = i;
		    	break;
		    }
		}
		return intObjetoAuditable;
	}
	
	//Ejecuta antes de cualquier metodo que inicia con insert	
	@Before("execution(* *.insert*(..))")
	public void logBeforeInsertAuditAdvice(JoinPoint joinPoint) {
	 	
		log.debug("logBeforeInsertAuditAdvice() is running!");
		log.debug("Method : "+joinPoint.getSignature().getName());
		
		if (log.isDebugEnabled()) {
            log.debug("Before insert advice . . .");
        }
        
        // Verificamos que el target sea una instancia de Auditor
        if (joinPoint.getTarget() instanceof Auditor) {
           try {
                // Verificamos que el primer parametro sea una instancia de la interface Auditable                
            	Object[] args = (Object[])joinPoint.getArgs();           	
            	Object o = args[0];

                if (o instanceof Auditable) {
                	int intObjetoAuditable =  this.buscarObjectoUsuario(args);
                	if (intObjetoAuditable >= 0) {
	                    Object u = args[intObjetoAuditable];
                        String username = ((Usuario) u).getLogin();
                        String ip = ((Usuario) u).getIpMaquinaRemota();                        
                        Auditable a = (Auditable) o;
                        a.getAuditInfo().setCreatedBy(username);
                        a.getAuditInfo().setCreated(new Timestamp(System.currentTimeMillis()));
                        a.getAuditInfo().setIpMaquinaRemota(ip);
                        log.debug("***El objeto se audito correctamente***");
                	}
                }
            }
            catch (IndexOutOfBoundsException ioobe) {
                log.warn("El número de parámetros del método no esta de acuerdo a la lógica de este Advice");
            }
        }		
	}

	

	//Ejecuta antes de cualquier metodo que inicia con update	
	@Before("execution(* *.update*(..))")
	public void logBeforeUpdateAuditAdvice(JoinPoint joinPoint) {
	 	
		log.debug("logBeforeUpdateAuditAdvice() is running!");
		log.debug("Method : " + joinPoint.getSignature().getName());
      
        if (log.isDebugEnabled()) {
            log.debug("Before update advice . . .");
        }
        
        // Verificamos que el target sea una instancia de Auditor
        if (joinPoint.getTarget() instanceof Auditor) {
           try {
                // Verificamos que el primer parametro sea una instancia de la interface Auditable
            	Object[] args = (Object[])joinPoint.getArgs();
                Object o = args[0];

                if (o instanceof Auditable) {
                	
                	int intObjetoAuditable =  this.buscarObjectoUsuario(args);
                	if (intObjetoAuditable >= 0) {
	                    Object u = args[intObjetoAuditable];
                        String username = ((Usuario) u).getLogin();
                        String ip = ((Usuario) u).getIpMaquinaRemota();                        
                        Auditable a = (Auditable) o;
                        a.getAuditInfo().setUpdatedBy(username);
                        a.getAuditInfo().setLastUpdated(new Timestamp(System.currentTimeMillis()));
                        a.getAuditInfo().setIpMaquinaRemota(ip);
                        log.debug("***El objeto se audito correctamente***");
                	}
                }
            }
            catch (IndexOutOfBoundsException ioobe) {
                log.warn("El número de parámetros del método no esta de acuerdo a la lógica de este Advice");
            }
        }
	}
	
	
	 //Run after the method throws an exception.
	
	 @AfterThrowing(pointcut = "execution(* *.insert*(..))",
			 throwing= "error")
	 public void logAfterThrowingInsert(JoinPoint joinPoint, Throwable error) {			 
		 log.debug("***Ocurrio un excepcion al insertar el objeto***");
		 log.debug("Error : "+error.getStackTrace());
	 }
	 
	 @AfterThrowing(pointcut = "execution(* *.update*(..))",
			 throwing= "error")
	 public void logAfterThrowingUpdate(JoinPoint joinPoint, Throwable error) {			 
		 log.debug("***Ocurrio un excepcion al actualizar el objeto***");
		 log.debug("Error : "+error.getStackTrace());
	 }
	 
	 @AfterThrowing(pointcut = "execution(* *.delete*(..))",
			 throwing= "error")
	 public void logAfterThrowingDelete(JoinPoint joinPoint, Throwable error) {			 
		 log.debug("***Ocurrio un excepcion al eliminar el objeto***");
		 log.debug("Error : "+error.getStackTrace());
	 }
	  
}
