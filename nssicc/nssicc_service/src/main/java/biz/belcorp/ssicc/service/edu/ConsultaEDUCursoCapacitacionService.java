package biz.belcorp.ssicc.service.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextrvela
 *
 */
public interface ConsultaEDUCursoCapacitacionService extends Service{

	/**
	 * Consulta las Aptas de los Cursos de Capacitacin 
	 * 
	 * @param consultaCursoCapacitacion
	 * 			objeto ConsultaCursoCapacitacion, considerar ingresar los atributos codigoPais,codigoEmpresa
	 * @return Lista de Consultas
	 */
	public List getConsultaCursoCapacitacionAptas(Map criterios);
	
	/**
	 * Consulta las Programadas de los Cursos de Capacitacin 
	 * 
	 * @param consultaCursoCapacitacion
	 * 			objeto ConsultaCursoCapacitacion, considerar ingresar los atributos codigoPais,codigoEmpresa
	 * @return Lista de Consultas
	 */
	public List getConsultaCursoCapacitacionProgramadas(Map criterios);

	/**
	 * Consulta las Capacitadas de los Cursos de Capacitacin 
	 * 
	 * @param consultaCursoCapacitacion
	 * 			objeto ConsultaCursoCapacitacion, considerar ingresar los atributos codigoPais,codigoEmpresa
	 * @return Lista de Consultas
	 */
	public List getConsultaCursoCapacitacionCapacitadas(Map criterios);
	
	public List getListStatusAptaConsultora(Map criterios);

	public List getListStatusProgrConsultora(Map criterios);

	public List getListStatusCapacConsultora(Map criterios);

	public List getListStatusBenefConsultora(Map criterios);

	public String[] getNivelesxAlcanzar(Map criterios);

	public List getConsultaCursoCapacitacionNoAptas(Map criterios);
	
	/**
	 * @param criterios
	 * @return Lista de situaciones para cargar el combo
	 */
	public List getSituaciones(Map criterios);
	/**
	 * @param criterios
	 * @return Lista de consultoras pendientes. En estado (PP - PF - PO) 
	 */
	public List getConsultaCursoCapacitacionPendientes(Map criterios);
}
