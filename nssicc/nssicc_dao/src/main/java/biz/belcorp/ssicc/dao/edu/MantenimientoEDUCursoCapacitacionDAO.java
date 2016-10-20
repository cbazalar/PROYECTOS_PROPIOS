package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacionAmbito;
import biz.belcorp.ssicc.dao.edu.model.DespachoProductoCurso;
import biz.belcorp.ssicc.dao.edu.model.EquivalenciaMatrizServicio;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPedido;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPlanillaInstructora;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoEDUCursoCapacitacionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 */


public interface MantenimientoEDUCursoCapacitacionDAO extends DAO {

	/**
	 * Obtiene la informacion del curso de capacitaciòn en base a los parametros enviados.
	 * 
	 * @param cursoCapacitacion
	 * 			objeto CursoCapacitacion, considerar ingresar los atributos codigoPais,codigoEmpresa,codigoCurso
	 * @return objeto CursoCapacitacion poblado.
	 */
	public CursoCapacitacion getCursoCapacitacion(CursoCapacitacion cursoCapacitacion);
	
	/**
	 * Obtiene la informacion de un producto relacionados a un determinado parametro de curso de capacitacion.
	 * @param despachoProducto
	 * 			objeto DespachoProducto, considerar ingresar los atributos codigoPais,codigoEmpresa,codigoCurso,correlativoDespachoCurso 
	 * @return objeto DespachoProducto poblado
	 */
	public DespachoProductoCurso getProductoxCurso(DespachoProductoCurso despachoProducto);

	
	/**
	 * Obtiene la informacion de los productos relacionados a un determinado parametro de curso de capacitacion.
	 * 
	 * @param despachoProducto
	 * 			Objeto DespachoProducto, considerar ingresar los atributos codigoPais,codigoEmpresa,CodigoCurso
	 * @return Lista de Productos relacionados a un parametro
	 */
	public List getProductosxCurso(DespachoProductoCurso despachoProducto);
	
	/**
	 * Obtiene la informacion de los cursos de capacitaciòn en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoCurso,estado 
	 * @return Lista de Paràmetros de Curso de Capacitaciòn.
	 */
	public List getCursosCapacitacionByCriteria(Map criterios);
	
	/**
	 * Inserta un paràmetro de curso de capacitaciòn
	 * @param cursoCapacitacion
	 * 			objeto CursoCapacitacion poblado.
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditorìa
	 */
	public void insertCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario);
	
	/**
	 * Modificar un paràmetro de curso de capacitaciòn
	 * @param cursoCapacitacion
	 * 			objeto CursoCapacitacion poblado.
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditorìa
	 */
	public void updateCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario);
	
	/**
	 * Elimina un paràmetro de curso de capacitaciòn
	 * @param cursoCapacitacion
	 * 			objeto CursoCapacitacion poblado.
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditorìa
	 */
	public void updateRemoveCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario);

	/**
	 * Inserta un producto de despacho por curso de capacitaciòn
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditorìa
	 */
	public void insertDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario);
	
	/**
	 * Modifica un registro de Despacho de Paràmetro de Curso
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditorìa
	 */
	public void updateDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario);
	
	/**
	 * Elimina Fisicamente un despacho de producto de un determinado parametro de curso de capacitaciòn
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void removeDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario);

	/**
	 * Elimina Logicamente un despacho de producto de un determinado parametro de curso de capacitaciòn
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void removeLogicoDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario);
	
	/**
	 * Devuelve el último código de curso registrado para la empresa comercializadora
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado de la empresa comercializadora
	 */
	public String getMaxCursoCapacitacion(CursoCapacitacion cursoCapacitacion);

	/**
	 * Obtiene la informacion de las regiones del Módulo de Educación en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoRegion,estado 
	 * @return Lista de Regiones Del Módulo de Educación.
	 */
	public List getRegion(RegionCursoCapacitacion region);
	
	/**
	 * Obtiene la informacion de las regiones del Módulo de Educación asociados a una Instructora
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoRegion,estado 
	 * @return Lista de Regiones Del Módulo de Educación.
	 */
	public List getRegionByInstructora(RegionCursoCapacitacion region);

	/**
	 * Obtiene la informacion de las regiones del Módulo de Educación en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoRegion,estado 
	 * @return Lista de Regiones Del Módulo de Educación.
	 */
	public List getRegionCursoCapacitacion(RegionCursoCapacitacion region);
	
	/**
	 * Obtiene la informacion de las Zonas del Módulo de Educación en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoRegion,estado 
	 * @return Lista de Regiones Del Módulo de Educación.
	 */
	public List getZona(ZonaCursoCapacitacion zona);

	/**
	 * Obtiene la informacion de las Zonas del Módulo de Educación en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoRegion,estado 
	 * @return Lista de Regiones Del Módulo de Educación.
	 */
	public List getZonaByRegionSelected(Map criterios);

	/**
	 * Obtiene la informacion de las Zonas del Módulo de Educación en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoRegion,estado 
	 * @return Lista de Regiones Del Módulo de Educación.
	 */
	public List getZonaCursoCapacitacion(ZonaCursoCapacitacion zonaCursoCapacitacion);
	
	/**
	 * Elimina Fisicamente un registro de ambito de un parametro de curso de capacitaciòn
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void removeCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito);
	
	/**
	 * Insertar un registro de ambito de un parametro de curso de capacitaciòn
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito, Usuario usuario);

	/**
	 * Devuelve una lista de registros de ambito de un parametro de curso de capacitaciòn
	 * @param despachoProducto
	 * 			objeto DespachoProducto poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public List getCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito);

	/**
	 * Modifica un registro de region
	 * @param region
	 * 			objeto RegionCursoCapacitacion poblado
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditorìa
	 */
	public void updateRegion(RegionCursoCapacitacion region, Usuario usuario);

	/**
	 * Obtiene la informacion de los historicos de pedidos por cliente
	 * 
	 * @param historicoPedido
	 * 			Objeto HistoricoPedido, considerar ingresar los atributos codigoPais,codigoEmpresa,CodigoCliente
	 * @return Lista de Historicos de pedidos por Cliente
	 */
	public List getHistoricoPedido(HistoricoPedido historicoPedido);

	/**
	 * Obtiene la informacion de los servicio de capacitaciòn en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoCurso 
	 * @return Servicio de Capacitaciòn.
	 */
	public List getServicioCapacitacionByCriteria(Map criterios);
	
	/**
	 * Obtiene la informacion de Equivalencia Matriz Servicio de capacitacion en base a los criterios enviados.
	 * 
	 * @param criterios
	 * 			Objeto Map de criterios para generar la consulta, considerar llenar los atributos codigoPais,codigoEmpresa,CodigoCurso 
	 * @return Servicio de Capacitaciòn.
	 */
	public List getEquivalenciaMatrizServiciosByCriteria(Map criterios);
	
	public EquivalenciaMatrizServicio getEquivalenciaMatrizServiciosById(Map criterios);	

	/**
	 * Insertar un registro de EquivalenciaMatrizServicio
	 * @param EquivalenciaMatrizServicio
	 * 			objeto EquivalenciaMatrizServicio poblado
	 * @param usuario
	 *			objeto Usuario para el registro de Auditorìa 
	 */
	public void insertEquivalenciaMatrizServicio(EquivalenciaMatrizServicio equivalenciaMatrizServicio, Usuario usuario);
	
	
	/**
	 * Valida que el codigo de Servicio registrado para el curso se encuentre en el rango predefinido y que otro
	 * producto no posea el mismo codigo de producto 
	 * @param criterios
	 * @return
	 * 		 1 OK
	 * 		-1 Codigo de Servicio fuera de Rango
	 * 		-2 Codigo de Servicio se encuentra registrado en otro producto  
	 * 		-3 No existe Rango para el curso
	 */
	public Integer getValidaRangoCodigoServicio(Map criterios);
	

	/**
	 * Modifica las planillas por Instructora por Region
	 * @param historicoPlanillaInstructora
	 * 			objeto HistoricoPlanillaInstructora poblado.
	 * @param usuario
	 * 			objeto Usuario para el registro de Auditorìa
	 */
	public void updatePlanillaInstructoraByRegion(HistoricoPlanillaInstructora historicoPlanillaInstructora, Usuario usuario);

	/**
	 * Retorna la lista de prerequisitos a tomar
	 * @param map
	 * @return
	 */
	public List getCursosParaPrereq(Map map);
	
	/**
	 * Inserta los prerquisitos
	 * @param map
	 */
	public void insertCursoCapacitacionPrerequisito(Map map);

	/**
	 * Elimina los prequisitos
	 * @param criteria
	 */
	public void deleteCursoCapacitacionPrerequisito(Map criteria);
	
	/**
	 * Retorna la lista de prerequisitos que tiene el curso
	 * @param map
	 * @return
	 */
	public List getPreRequsitos(Map criteria);
}