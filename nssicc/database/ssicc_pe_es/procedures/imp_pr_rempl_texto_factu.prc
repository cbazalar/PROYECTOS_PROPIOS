CREATE OR REPLACE PROCEDURE "IMP_PR_REMPL_TEXTO_FACTU"
(p_directorio IN VARCHAR2, p_directorioHistorico IN VARCHAR2, p_pat_archi IN VARCHAR2 := 'PAL%.TXT', p_suf_arch IN VARCHAR2 := '_MODIFICADO') AS

CURSOR c_archivos IS
SELECT NOM_ARCH
FROM IMP_TMP_ARCHI_DIREC
WHERE NOM_ARCH LIKE p_pat_archi
AND NOM_ARCH NOT LIKE '%' || p_suf_arch || '%';

l_nom_comp_arch VARCHAR2(255);
l_nom_arch      VARCHAR2(255);
l_ext_arch      VARCHAR2(255);
l_pos_punt      NUMBER;

BEGIN

-- Obtenemos la relacion de archivos del directorio
IMP_PR_OBTIE_ARCHI_DIREC(p_directorio);

-- Iteramos sobre los archivos obtenidos
OPEN c_archivos;
LOOP
FETCH c_archivos INTO l_nom_comp_arch;
EXIT WHEN c_archivos%NOTFOUND;
  -- cargamos el archivo a reemplazar
  IMP_PR_CARGA_FACTU(l_nom_comp_arch, p_directorio);

  -- Obtenemos la posicion del punto
  l_pos_punt := INSTR(l_nom_comp_arch, '.');

  IF l_pos_punt <> 0 THEN
      l_nom_arch := SUBSTR(l_nom_comp_arch, 1, l_pos_punt - 1);
      l_ext_arch := SUBSTR(l_nom_comp_arch, l_pos_punt + 1, LENGTH(l_nom_comp_arch) - l_pos_punt);
  END IF;

  -- Escribimos el archivo modificado
  IF l_pos_punt <> 0 THEN
      IMP_PR_FACTU_TO_FILE(l_nom_arch || p_suf_arch || '.' || l_ext_arch, p_directorio);
  ELSE
      IMP_PR_FACTU_TO_FILE(l_nom_comp_arch || p_suf_arch, p_directorio);
  END IF;

  -- movemos el archivo original a la carpeta historica
  UTL_FILE.FRENAME(p_directorio, l_nom_comp_arch, p_directorioHistorico, l_nom_comp_arch, true);

END LOOP;
CLOSE c_archivos;


END IMP_PR_REMPL_TEXTO_FACTU;
/

