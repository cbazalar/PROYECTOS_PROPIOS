package biz.belcorp.ssicc.service.spusicc.ventas;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.PorcentajeIgv;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * Interfaz de servicio de Porcentaje IGV
 * @author peextjnunez
 *
 */
public interface PorcentajeIgvVENService extends Service{
	
	public List getPorcentajeIgv(PorcentajeIgv bporcentajeigv);
    public void updatePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario);
	public void insertPorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario);
	public void deletePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario);
	

}
