package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.ConsultaEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.service.edu.ConsultaEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;


/**
 * @author peextrvela
 *
 */
@Service("edu.consultaEDUCursoCapacitacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaEDUCursoCapacitacionServiceImpl extends 
	BaseService implements ConsultaEDUCursoCapacitacionService{
	
	@Resource(name="edu.consultaEDUCursoCapacitacionDAO")
	ConsultaEDUCursoCapacitacionDAO consultaEDUCursoCapacitacionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ConsultaEDUCursoCapacitacionService#getConsultaCursoCapacitacionAptas(biz.belcorp.ssicc.edu.dao.model.ConsultaCursoCapacitacion)
	 */
	public List getConsultaCursoCapacitacionAptas(Map criterios) {
		// TODO Auto-generated method stub
		return consultaEDUCursoCapacitacionDAO.getConsultaCursoCapacitacionAptas(criterios);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ConsultaEDUCursoCapacitacionService#getConsultaCursoCapacitacionCapacitadas(biz.belcorp.ssicc.edu.dao.model.ConsultaCursoCapacitacion)
	 */
	public List getConsultaCursoCapacitacionCapacitadas(Map criterios) {
		// TODO Auto-generated method stub
		return consultaEDUCursoCapacitacionDAO.getConsultaCursoCapacitacionCapacitadas(criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ConsultaEDUCursoCapacitacionService#getConsultaCursoCapacitacionProgramadas(biz.belcorp.ssicc.edu.dao.model.ConsultaCursoCapacitacion)
	 */
	public List getConsultaCursoCapacitacionProgramadas(Map criterios) {
		// TODO Auto-generated method stub
		return consultaEDUCursoCapacitacionDAO.getConsultaCursoCapacitacionProgramadas(criterios);
	}

	/**
	 * @return Returns the consultaEDUCursoCapacitacionDAO.
	 */
	public ConsultaEDUCursoCapacitacionDAO getConsultaEDUCursoCapacitacionDAO() {
		return consultaEDUCursoCapacitacionDAO;
	}
	
	/**
	 * @param consultaEDUCursoCapacitacionDAO The consultaEDUCursoCapacitacionDAO to set.
	 */
	public void setConsultaEDUCursoCapacitacionDAO(
			ConsultaEDUCursoCapacitacionDAO consultaEDUCursoCapacitacionDAO) {
		this.consultaEDUCursoCapacitacionDAO = consultaEDUCursoCapacitacionDAO;
	}

	public List getListStatusAptaConsultora(Map criterios) {
		return consultaEDUCursoCapacitacionDAO.getListStatusAptaConsultora(criterios);
	}

	public List getListStatusProgrConsultora(Map criterios) {
		return consultaEDUCursoCapacitacionDAO.getListStatusProgrConsultora(criterios);
	}

	public List getListStatusCapacConsultora(Map criterios) {
		return consultaEDUCursoCapacitacionDAO.getListStatusCapacConsultora(criterios);
	}

	public List getListStatusBenefConsultora(Map criterios) {
		return consultaEDUCursoCapacitacionDAO.getListStatusBenefConsultora(criterios);
	}

	public String[] getNivelesxAlcanzar(Map criterios) {
		return consultaEDUCursoCapacitacionDAO.getNivelesxAlcanzar(criterios);
	}

	public List getConsultaCursoCapacitacionNoAptas(Map criterios){
		return consultaEDUCursoCapacitacionDAO.getConsultaCursoCapacitacionNoAptas(criterios);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ConsultaEDUCursoCapacitacionService#getSituaciones(java.util.Map)
	 */
	public List getSituaciones(Map criterios){
		return consultaEDUCursoCapacitacionDAO.getSituaciones(criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ConsultaEDUCursoCapacitacionService#getConsultaCursoCapacitacionPendientes(java.util.Map)
	 */
	public List getConsultaCursoCapacitacionPendientes(Map criterios){
		return consultaEDUCursoCapacitacionDAO.getConsultaCursoCapacitacionPendientes(criterios);
	}
}
