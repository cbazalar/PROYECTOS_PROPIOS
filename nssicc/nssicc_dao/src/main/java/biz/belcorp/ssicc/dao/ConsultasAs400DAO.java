package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;


public interface ConsultasAs400DAO {
	
	public void getProceso400ActuByCriteria(String user,String password) throws Exception;
	public List getProcesoConsulta400Ibatis(Map criteria);
	public List getProcesoConsulta400IbatisMap(Map criteria);
	public void insertarProcesoConsulta400Ibatis(Map criteria);
	
	

}
