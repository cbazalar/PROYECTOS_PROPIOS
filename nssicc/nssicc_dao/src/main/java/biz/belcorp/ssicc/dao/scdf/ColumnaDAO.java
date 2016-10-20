package biz.belcorp.ssicc.dao.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.scdf.model.Columna;

public interface ColumnaDAO extends DAO {

    /**
     * Obtenemos la relacion de columnas que se manejan relacionadas a un
     * archivo
     * 
     * @param columna
     *            Objeto Columna q servira para filtrar las columnas
     *            pertenecientes a un determinado archivo.
     * @return Lista de Columnas pobladas.
     */
    public List getColumnas(Columna columna);

}
