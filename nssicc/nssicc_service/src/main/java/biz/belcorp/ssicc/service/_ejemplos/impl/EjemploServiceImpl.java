package biz.belcorp.ssicc.service._ejemplos.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao._ejemplos.EjemploDAO;
import biz.belcorp.ssicc.service._ejemplos.EjemploService;

@Service("ssicc.ejemploService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class EjemploServiceImpl implements EjemploService{
	
	@Resource(name="ssicc.ejemploDAO")
	private EjemploDAO ejemploDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.EjemploService#devuelveNombre(java.lang.Integer)
	 */
	public String devuelveNombre(Integer valor)  {
		String nombre="John 1";
		if(valor==1) nombre="CARLOS";
		if(valor==2) nombre="PEDRO";
		
		nombre = ejemploDAO.devuelveNombre(valor);
		return nombre;
	}

}

