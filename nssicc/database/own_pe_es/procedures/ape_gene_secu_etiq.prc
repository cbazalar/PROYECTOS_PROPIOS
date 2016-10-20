CREATE OR REPLACE PROCEDURE "APE_GENE_SECU_ETIQ" (
    OIDPAIS IN VARCHAR2
)
AS
   -- Obtengo todas los centros de distribucion para las cuales hay etiquetas sin secuencial
   CURSOR cur_centros
   IS
      SELECT DISTINCT lista.ccdi_oid_conf_cent_dist
                 FROM ape_etiqu etiq
                    , ape_lista_picad_cabec lista
                WHERE etiq.pais_oid_pais = TO_NUMBER (oidpais)
                  AND etiq.num_secu IS NULL
                  AND etiq.lpca_oid_list_pica_cabe = lista.oid_list_pica_cabe;

   -- Cursor para recorrer las etiquetas de la linea actual
   CURSOR cur_etiq(centro number)
   IS
      SELECT etiq.oid_etiq, ROW_NUMBER () OVER (ORDER BY etiq.oid_etiq) AS orden
           FROM ape_etiqu etiq
              , ape_lista_picad_cabec lista
       WHERE lista.ccdi_oid_conf_cent_dist = centro
         AND lista.oid_list_pica_cabe = etiq.lpca_oid_list_pica_cabe
         AND etiq.num_secu IS NULL;

   num_secu_asignar   NUMBER (12);
   ult_num_secu       NUMBER (12);   -- Ultimo numero usado para las etiquetas del Centro de Distribucion
   max_num_secu       NUMBER (10);   -- Maximo numero luego del cual se reinicia la numeracion

BEGIN
  FOR i IN cur_centros
  LOOP
    -- Obtengo el ultimo numero de etiqueta usado para el centro de distribucion
    SELECT NVL(NUM_ULTI_SECU_ETIQ, 0)
      INTO ult_num_secu
      FROM APP_CONFI_CENTR_DISTR
     WHERE OID_CONF_CENT_DIST = i.ccdi_oid_conf_cent_dist;

    -- Obtengo el maximo valor posible para dicho numero
    -- Tantos 9 seguidos como indica NUM_LONG_ETIQ en la linea
    SELECT POWER (10, NUM_LONG_ETIQ) - 1 INTO max_num_secu
      FROM app_confi_centr_distr
     WHERE OID_CONF_CENT_DIST = i.ccdi_oid_conf_cent_dist;

    -- Actualizo todas las etiquetas de la linea de armado que no tengan secuencial
    FOR j IN cur_etiq(i.ccdi_oid_conf_cent_dist) LOOP

      -- Calculo el secuencial para la etiqueta actual
      num_secu_asignar := MOD (ult_num_secu + j.orden - 1, max_num_secu) + 1;

      -- Actualizo la etiqueta actual con el secuencial calculado
      UPDATE ape_etiqu
         SET num_secu = num_secu_asignar
       WHERE oid_etiq = j.oid_etiq;

    END LOOP; -- Fin loop etiquetas actualizar

    --Actualizamos la ultima secuencia asignada para el Centro de Distribucion
    UPDATE APP_CONFI_CENTR_DISTR
       SET NUM_ULTI_SECU_ETIQ = num_secu_asignar
     WHERE OID_CONF_CENT_DIST = i.ccdi_oid_conf_cent_dist;

  END LOOP; -- Fin loop lineas armado
END;
/

