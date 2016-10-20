CREATE OR REPLACE PROCEDURE APE_PR_GENER_OLAS(
 OIDPAIS IN VARCHAR2
)
IS
    vn_oidAgruOlas    NUMBER;
    vn_numPediOlas    NUMBER;
    vn_numOLA         NUMBER(4);
    W_FILAS           NUMBER := 1000;
    vn_contPedidoOLAFinal NUMBER;
    vn_oid_centr_distr    NUMBER;
    vb_insertarOla        BOOLEAN := FALSE;
    vn_indice             NUMBER;


    CURSOR c_centr_distr IS
     SELECT ccd.oid_conf_cent_dist,
            nvl(ccd.aafp_oid_aafp_olas,0),
            nvl(ccd.num_mini_pedi_olas,0),
            DECODE(ccd.num_secu_olas,999,1, ccd.num_secu_olas+1)
         FROM app_confi_centr_distr ccd
         WHERE ccd.pais_oid_pais = OIDPAIS;


    CURSOR c_consolidado(p_oid_centr_distr NUMBER) IS
      SELECT lpc.zorg_oid_regi       OID_REGION,
             lpc.zzon_oid_zona       OID_ZONA,
             lpc.zscc_oid_secc       OID_SECCION,
             lpc.liar_oid_line_arma  OID_LINEA,
             lpc.soca_oid_soli_cabe  OID_LISTA_CONSO,
             psc.fec_fact            FEC_FACTURACION,
             psc.num_lote_fact       NUM_LOTE,
             lpc.oid_list_pica_cabe  LISTA_PICADO
        FROM ped_solic_cabec psc,
             ape_lista_picad_cabec lpc,
             int_lar_tipo_solici_pedido_dis lts,
             app_confi_centr_distr ccd
       WHERE psc.ind_inte_lari_gene = 0
         AND lpc.ccdi_oid_conf_cent_dist = ccd.oid_conf_cent_dist
         AND psc.tspa_oid_tipo_soli_pais = lts.tspa_oid_tipo_soli_pais
         AND psc.oid_soli_cabe = lpc.soca_oid_soli_cabe
         AND lpc.clie_oid_clie IS NOT NULL
         AND lpc.soca_oid_soli_cabe IS NOT NULL
         AND  ccd.oid_conf_cent_dist  = p_oid_centr_distr
    ORDER BY lpc.liar_oid_line_arma,
             lpc.num_secu_zona_ruta,
             lpc.zorg_oid_regi,
             lpc.zzon_oid_zona,
             lpc.zscc_oid_secc;

    TYPE consolidado IS RECORD
    (
       oidRegion        zon_regio.oid_regi%TYPE,
       oidZona          zon_zona.oid_zona%TYPE,
       oidSeccion       zon_regio.oid_regi%TYPE,
       oidLinea         ape_linea_armad.oid_line_arma%TYPE,
       oidListaConso    ape_lista_picad_cabec.soca_oid_soli_cabe%TYPE,
       fecFacturacion   ped_solic_cabec.fec_fact%TYPE,
       numLote          ped_solic_cabec.num_lote_fact%TYPE,
       listaPicado      ape_lista_picad_cabec.oid_list_pica_cabe%TYPE
    );

    TYPE consolidadoTab  IS TABLE OF consolidado;
    consolidadoRecord consolidadoTab;

BEGIN

  OPEN c_centr_distr;
       LOOP
           FETCH c_centr_distr INTO vn_oid_centr_distr, vn_oidAgruOlas, vn_numPediOlas, vn_numOLA;
           EXIT WHEN (c_centr_distr%NOTFOUND);

           -- La agrupacion de OLAs puede ser:
           -- Seccion = 1; Region = 2; Zona = 3
           vn_contPedidoOLAFinal := 1;

           OPEN c_consolidado(vn_oid_centr_distr);
                LOOP
                    FETCH c_consolidado BULK COLLECT INTO consolidadoRecord LIMIT W_FILAS;
                          IF consolidadoRecord.COUNT > 0 THEN
                             vn_indice := consolidadoRecord.LAST;
                             FOR x IN consolidadoRecord.FIRST .. (consolidadoRecord.LAST - 1) LOOP
                                 -- Para el caso de Agrupacion por Seccion
                                    IF ( vn_oidAgruOlas = 1)  THEN
                                    -- Si hay cambio de seccion se inserta la OLA
                                       IF (consolidadoRecord(x).oidSeccion <> consolidadoRecord(x+1).oidSeccion ) THEN
                                          vb_insertarOla := TRUE;
                                       ELSE
                                           vn_contPedidoOLAFinal := vn_contPedidoOLAFinal + 1;
                                       END IF;
                                   ELSE
                                       -- Para el caso de Agrupacion por Region
                                       IF (vn_oidAgruOlas = 2) THEN
                                       -- Si hay cambio de region se inserta la OLA
                                          IF (consolidadoRecord(x).oidRegion <> consolidadoRecord(x+1).oidRegion ) THEN
                                             vb_insertarOla := TRUE;
                                          ELSE
                                              vn_contPedidoOLAFinal := vn_contPedidoOLAFinal + 1;
                                          END IF;
                                       ELSE
                                       -- Para el caso de Agrupacion por Zona
                                          IF (vn_oidAgruOlas = 3) THEN
                                          -- Si hay cambio de zona se inserta la OLA
                                             IF (consolidadoRecord(x).oidZona <> consolidadoRecord(x+1).oidZona ) THEN
                                                vb_insertarOla := TRUE;
                                             ELSE
                                                 vn_contPedidoOLAFinal := vn_contPedidoOLAFinal + 1;
                                             END IF;
                                          END IF;
                                       END IF;
                                    END IF;

                                        -- Se actualiza el nuemro de Ola en la tabla de Lista de Picado
                                          UPDATE ape_lista_picad_cabec
                                                 SET num_ola  = vn_numOLA
                                                 WHERE oid_list_pica_cabe = consolidadoRecord(x).listaPicado;


                                    IF ( vb_insertarOla = TRUE ) THEN
                                       -- Se elimina el registro para volver a insertarlo con los nuevos datos
                                       DELETE FROM ape_olas_xdia WHERE num_ola = vn_numOLA and CCDI_OID_CONF_CENT_DIST = vn_oid_centr_distr;

                                       -- Se inserta en la tabla de Olas por Dia
                                    INSERT INTO ape_olas_xdia(
                                           oid_olas_xdia,
                                           num_ola,
                                           fec_fact,
                                           num_lote_fact,
                                           tipo_grup,
                                           liar_oid_line_arma,
                                           num_pedi,
                                           val_esta_ola,
                                           CCDI_OID_CONF_CENT_DIST )
                                           VALUES(
                                                  APE_OLXD_SEQ.NEXTVAL,
                                                  vn_numOLA,
                                                  consolidadoRecord(x).fecFacturacion,
                                                  consolidadoRecord(x).numLote,
                                                  to_char(vn_oidAgruOlas),
                                                  consolidadoRecord(x).oidLinea,
                                                  vn_contPedidoOLAFinal,
                                                  0 ,
                                                  vn_oid_centr_distr);



                                          IF (vn_numPediOlas = 0) THEN
                                             UPDATE ape_lista_picad_cabec
                                             SET num_ola  = vn_numOLA
                                             WHERE soca_oid_soli_cabe = consolidadoRecord(x).oidListaConso;
                                          END IF;

                                              vb_insertarOla := FALSE;
                                              vn_contPedidoOLAFinal := 1;
                                              vn_numOLA := vn_numOLA + 1;


                         END IF;
                       END LOOP;

          -- Se inserta la ultima OLA
          -- Se elimina el registro para volver a insertarlo con los nuevos datos
          DELETE FROM ape_olas_xdia WHERE num_ola = vn_numOLA and CCDI_OID_CONF_CENT_DIST = vn_oid_centr_distr;

          -- Se inserta en la tabla de Olas por Dia
          INSERT INTO ape_olas_xdia(
              oid_olas_xdia,
              num_ola,
              fec_fact,
              num_lote_fact,
              tipo_grup,
              liar_oid_line_arma,
              num_pedi,
              val_esta_ola,
              CCDI_OID_CONF_CENT_DIST )
          VALUES(
              APE_OLXD_SEQ.NEXTVAL,
              vn_numOLA,
              consolidadoRecord(vn_indice).fecFacturacion,
              consolidadoRecord(vn_indice).numLote,
              to_char(vn_oidAgruOlas),
              consolidadoRecord(vn_indice).oidLinea,
              vn_contPedidoOLAFinal,
              0,
              vn_oid_centr_distr );

          -- Se actualiza el nuemro de Ola en la tabla de Lista de Picado
          UPDATE ape_lista_picad_cabec
             SET num_ola  = vn_numOLA
           WHERE oid_list_pica_cabe = consolidadoRecord(vn_indice).listaPicado;

          --  Se actualiza el nuemro de Ola en la tabla de Centro de distribucion
          UPDATE app_confi_centr_distr
            SET num_secu_olas  = vn_numOLA
          WHERE oid_conf_cent_dist = vn_oid_centr_distr;

        END IF;
      EXIT WHEN (c_consolidado%NOTFOUND);
    END LOOP;
  CLOSE c_consolidado;
  END LOOP;
 CLOSE c_centr_distr;

END  APE_PR_GENER_OLAS;
/

