package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.ConsultasAs400DAO;
import biz.belcorp.ssicc.service.ConsultasAs400Service;

@Service("scsicc.consultasAs400Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultasAs400ServiceImpl implements ConsultasAs400Service {
	
	@Resource(name="scsicc.consultasAs400DAO")
	private ConsultasAs400DAO consultasAs400DAO;
	

	
	public void getProceso400ActuByCriteria(String user,String password) throws Exception
	{
		consultasAs400DAO.getProceso400ActuByCriteria(user,password);
	}

	
	public List getProcesoConsulta400Ibatis(Map criteria)
	{
		return this.consultasAs400DAO.getProcesoConsulta400Ibatis(criteria);
	}
	public List getProcesoConsulta400IbatisMap(Map criteria)
	{
		return this.consultasAs400DAO.getProcesoConsulta400IbatisMap(criteria);
	}

	public void insertarProcesoConsulta400Ibatis(final Map criteria)
	{
		this.consultasAs400DAO.insertarProcesoConsulta400Ibatis(criteria);
	}

}
