package biz.belcorp.ssicc.dao.spusicc.ventas;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.PorcentajeIgv;

/**
 * Interfaz de de Porcentaje Igv
 * @author peextjnunez
 *
 */
public interface PorcentajeIgvVENDAO extends DAO {
	
	public List getPorcentajeIgv(PorcentajeIgv bporcentajeigv);
    public void updatePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario);
	public void insertPorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario);
	public void deletePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario);
	

}
