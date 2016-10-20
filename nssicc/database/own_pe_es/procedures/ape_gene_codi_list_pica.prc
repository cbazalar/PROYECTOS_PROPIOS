CREATE OR REPLACE PROCEDURE "APE_GENE_CODI_LIST_PICA" (
 OIDPAIS IN VARCHAR2
)
AS
   CURSOR cur_listas
   IS
      SELECT oid_list_pica_cabe,
             ultimo + ROW_NUMBER () OVER (PARTITION BY oid_peri ORDER BY oid_peri, oid_list_pica_cabe) AS codigo
        FROM (SELECT   peri.oid_peri, lista.oid_list_pica_cabe,
                       lista.cod_list_pica,
                       NVL
                          (MAX (lista.cod_list_pica) OVER (PARTITION BY peri.oid_peri ORDER BY peri.oid_peri, lista.oid_list_pica_cabe),
                           0
                          ) AS ultimo
                  FROM ape_lista_picad_cabec lista, cra_perio peri
                 WHERE lista.perd_oid_peri = peri.oid_peri
                   AND peri.pais_oid_pais = TO_NUMBER(OIDPAIS)
              ORDER BY peri.oid_peri, lista.oid_list_pica_cabe)
       WHERE cod_list_pica IS NULL;
BEGIN
   FOR i IN cur_listas
   LOOP
      BEGIN
         UPDATE ape_lista_picad_cabec
            SET cod_list_pica = i.codigo
          WHERE oid_list_pica_cabe = i.oid_list_pica_cabe;
      END;
   END LOOP;
END;
/

