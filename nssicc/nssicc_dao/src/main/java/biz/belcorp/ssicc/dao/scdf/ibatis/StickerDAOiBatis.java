package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.StickerDAO;
import biz.belcorp.ssicc.dao.scdf.model.Sticker;

@Repository("scdf.stickerDAO")
public class StickerDAOiBatis extends BaseDAOiBatis implements StickerDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#getStickersByPais(java.lang.String)
     */
    public List getStickersByPais(String codigoPais) {
        List stickers = getSqlMapClientTemplate().queryForList(
                "scdf.StickerSQL.getStickersByPais", codigoPais);
        return stickers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#getStickersByCriteria(java.util.Map)
     */
    public List getStickersByCriteria(Map criteria) {
        List stickers = getSqlMapClientTemplate().queryForList(
                "scdf.StickerSQL.getStickersByCriteria", criteria);
        return stickers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#getHistoricoStickersByPais(java.lang.String)
     */
    public List getHistoricoStickersByPais(String codigoPais) {
        List stickers = getSqlMapClientTemplate().queryForList(
                "scdf.StickerSQL.getHistoricoStickersByPais", codigoPais);
        return stickers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#getHistoricoStickersMapByPais(java.lang.String)
     */
    public List getHistoricoStickersMapByPais(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.StickerSQL.getHistoricoStickersMapByPais", codigoPais);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#insertSticker(biz.belcorp.ssicc.scdf.model.Sticker,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertSticker(Sticker sticker, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#updateSticker(biz.belcorp.ssicc.scdf.model.Sticker,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateSticker(Sticker sticker, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#updateHistoricoStickerStatusByPais(java.lang.String)
     */
    public void updateHistoricoStickerStatusByPais(String codigoPais) {
        getSqlMapClientTemplate().update(
                "scdf.StickerSQL.updateHistoricoStickerStatusByPais",
                codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#removeSticker(biz.belcorp.ssicc.scdf.model.Sticker,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeSticker(Sticker sticker, Usuario usuario) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#executeGeneraStickers(biz.belcorp.ssicc.model.Pais,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public int executeGeneraStickers(Pais pais, Usuario usuario) {
        Map map = new HashMap();
        map.put("codigoPais", pais.getCodigo());
        map.put("totalStickers", "0");
        getSqlMapClientTemplate().update("scdf.StickerSQL.generaStickers", map);
        return Integer.parseInt((String) map.get("totalStickers"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#getEstadosStickersByPais(java.lang.String)
     */
    public List getEstadosStickersByPais(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.StickerSQL.getEstadosStickersByPais", codigoPais);
        return resultados;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#getStickersMapByPais(java.lang.String)
     */
    public List getStickersMapByPais(String codigoPais) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.StickerSQL.getStickersMapByPais", codigoPais);
        return resultados;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.StickerDAO#updateCorrelativoStickers(java.lang.String)
     */
    public void updateCorrelativoStickers(String codigoPais) {
        getSqlMapClientTemplate().update(
                "scdf.StickerSQL.updateCorrelativoStickers",
                codigoPais);
    }

}
