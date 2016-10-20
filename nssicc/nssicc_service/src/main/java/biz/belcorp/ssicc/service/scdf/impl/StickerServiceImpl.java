package biz.belcorp.ssicc.service.scdf.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.StickerDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.StickerService;

@Service("scdf.stickerService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class StickerServiceImpl extends BaseService implements StickerService {

	@Resource(name="scdf.stickerDAO")
    private StickerDAO stickerDAO;

	

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.StickerService#executeGeneracionStickers(biz.belcorp.ssicc.model.Pais, biz.belcorp.ssicc.model.Usuario)
     */
	public int executeGeneracionStickers(Pais pais, Usuario usuario) {
		return stickerDAO.executeGeneraStickers(pais, usuario);
	}

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.StickerService#executeReimpresionStickers(java.util.Map)
     */
	public List executeReimpresionStickers(Map criteria) {
		return stickerDAO.getStickersByCriteria(criteria);
	}

    /*
     *  (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.StickerService#selectEstadosStickers(biz.belcorp.ssicc.model.Pais)
     */
	public List selectEstadosStickers(Pais pais) {
		return stickerDAO.getEstadosStickersByPais(pais.getCodigo());
	}

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.StickerService#updateHistoricoStickerStatusByPais(java.lang.String)
     */
    public void updateHistoricoStickerStatusByPais(String codigoPais) {
        stickerDAO.updateHistoricoStickerStatusByPais(codigoPais);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.StickerService#updateCorrelativoStickers(java.lang.String)
     */
    public void updateCorrelativoStickers(String codigoPais) {
        stickerDAO.updateCorrelativoStickers(codigoPais);
    }

}
