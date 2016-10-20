/*
 * Created on 07/11/2005 11:21:13 AM
 *
 * biz.belcorp.ssicc.dao.TarjetaDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.MovimientoCliente;

/**
 * TODO Include class description here.
 * <p>
 * <a href="TarjetaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface MovimientoClienteDAO extends DAO {

	public void insertMovimientoCliente(MovimientoCliente mov, Usuario usuario);

}