package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MantenimientoZONCallesDAO.
 *
 * @author Belcorp
 * @version 1.0
 * 10:10:39 AM
 */
public interface MantenimientoZONCallesDAO extends DAO {

	/**
	 * Gets the direcciones clientes list.
	 *
	 * @param criteria the criteria
	 * @return the direcciones clientes list
	 */
	public List getDireccionesClientesList(Map criteria);

	/**
	 * Gets the id sgte zon calle.
	 *
	 * @return the id sgte zon calle
	 */
	public Long getIdSgteZonCalle();

	/**
	 * Gets the oid valo estr geop.
	 *
	 * @param criteria the criteria
	 * @return the oid valo estr geop
	 */
	public Long getOidValoEstrGeop(Map criteria);

	/**
	 * Insert zon calle.
	 *
	 * @param criteria the criteria
	 */
	public void insertZonCalle(Map criteria);

	/**
	 * Gets the zon calle.
	 *
	 * @param oidCalle the oid calle
	 * @return the zon calle
	 */
	public Map getZonCalle(Long oidCalle);
	
	/**
	 * Update zon calle.
	 *
	 * @param criteria the criteria
	 */
	public void updateZonCalle(Map criteria);
	
	/**
	 * Delete zon calle.
	 *
	 * @param criteria the criteria
	 */
	public void deleteZonCalle(Map criteria);
	
	/**
	 * Update direccion cliente.
	 *
	 * @param criteria the criteria
	 */
	public void updateDireccionCliente(Map criteria);
	
	/**
	 * Gets the valida consultora calle.
	 *
	 * @param criteria the criteria
	 * @return the valida consultora calle
	 */
	public String getValidaConsultoraCalle(Map criteria);
}
