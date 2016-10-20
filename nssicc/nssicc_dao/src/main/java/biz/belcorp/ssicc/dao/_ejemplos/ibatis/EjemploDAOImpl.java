package biz.belcorp.ssicc.dao._ejemplos.ibatis;

import biz.belcorp.ssicc.dao._ejemplos.EjemploDAO;

import org.springframework.stereotype.Repository;

@Repository("ssicc.ejemploDAO")
public class EjemploDAOImpl implements EjemploDAO{
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.EjemploDAO#devuelveNombre(java.lang.Integer)
	 */
	public String devuelveNombre(Integer valor)  {
		String nombre="John DAO";
		if(valor==1) nombre="Carlos DAO";
		if(valor==2) nombre="Pedro DAO";
		return nombre;
	}

}

