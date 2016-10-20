package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.CopiarAsignarProductos;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPECopiarAsignacionProductosService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPECopiarAsignacionProductosService extends Service{

	/**
	 * Retorna la lista de Asignacin de Anaqueles y Versiones
	 * @return
	 */
	public List getCopiarAsignacionProductosList(Map criteria);
	
	/**
	 * @param criteria
	 * @return 1 Si existe Versin por Centro de Distribucin y Periodo, 0 en caso no Existe
	 */
	public String getValidaVersionByCDyPeriodo(Map criteria);
	
	/**
	 * @param criteria
	 * Permite Copiar la asignacin de productos anaqueles asignando una nueva versin
	 */
	public void generaCopiaAsignacionProductosAnaqueles(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Objeto Asignar Productos Anaqueles 
	 */
	public CopiarAsignarProductos getCopiarAsigProdAnaquelObject(Map criteria);
	
}