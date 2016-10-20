/*
 * Created on 09/10/2006 04:27:06 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarRegionesServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

/**
 * TODO Include class description here.
 * Clase Service Estandar para las interfaces de tipo Paquete
 * <p>
 * <a href="InterfazGENPaqueteEstandarImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Carlos Bazalar</a>
 */
@Service("sisicc.interfazGENPaqueteEstandar")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazGENPaqueteEstandarImpl extends BaseInterfazPaqueteAbstractServiceImpl {
	
	
}