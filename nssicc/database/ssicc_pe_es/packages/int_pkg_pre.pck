CREATE OR REPLACE PACKAGE "INT_PKG_PRE" IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_OFERT
  Fecha Creacion    : 07/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_ofert_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_OFERT_DETAL
  Fecha Creacion    : 07/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_ofert_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_GRUPO_OFERT
  Fecha Creacion    : 07/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_grupo_ofert
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_PROMO
  Fecha Creacion    : 07/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_promo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_RANGO_PROMO
  Fecha Creacion    : 07/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_rango_promo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_MATRI_FACTU
  Fecha Creacion    : 07/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_matri_factu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_OFERT en la
                       tabla temporal PRE_TMP_OFERT
  Fecha Creacion    : 07/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_ofert_cabec_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_GRUPO_OFERT
                      en la tabla temporal PRE_TMP_GRUPO_OFERT
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_grupo_ofert_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_OFERT_DETAL
                      en la tabla temporal PRE_TMP_OFERT_DETAL
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_ofert_detal_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_PROMO
                      en la tabla temporal PRE_TMP_PROMO
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_promo_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_RANGO_PROMO
                      en la tabla temporal PRE_TMP_RANGO_PROMO
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_rango_promo_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/***************************************************************************
  Descripcion       : Procedimiento que inserta los archivos exportados
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_archi_expor;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_OFERT los
                      registros de la tabla PRE_TMP_OFERT
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_ofert_cabec;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_GRUPO_OFERT
                      los registros de la tabla PRE_TMP_GRUPO_OFERT
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_grupo_ofert;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_TMP_OFERT_DETAL
                      los registros de la tabla PRE_TMP_OFERT_DETAL
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_ofert_detal;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_PROMO
                      los registros de la tabla PRE_TMP_PROMO
  Fecha Creacion    : 08/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_promo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_RANGO_PROMO
                      los registros de la tabla PRE_TMP_RANGO_PROMO
  Fecha Creacion    : 09/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_rango_promo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_MATRO_FACTU
  Fecha Creacion    : 09/04/2010
  Autor             : Jos? Luis Rodr?guez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_matri_factu;

/***************************************************************************
  Descripcion       : Validaciones Previas al Procesamiento de la
                      Pantalla Carga Matriz Planit
  Fecha Creacion    : 11/08/2015
  Autor             : Richard Argomedo
***************************************************************************/
PROCEDURE int_pre_carg_matr_plan
 (
    psnumerolote         VARCHAR,
    psvalidacion         VARCHAR,
    psNombres            VARCHAR,
    pscodigoPais         VARCHAR,
    psCodigoPeriodo      VARCHAR,
    psCodigoCatalogo     VARCHAR,
    psEliminarArch       OUT VARCHAR
 );
 
/***************************************************************************
 Descripcion       : Procedimiento que inserta los errores en el proceso de 
                     Carga Matriz Planit
 Fecha Creacion    : 28/01/2016
 Autor             : Aurelio Oviedo
*****************************************************************************/
PROCEDURE INT_PR_INSER_ERROR_PLANI(
  p_oidErrorPlani   PRE_ERRO_PLANI.OID_ERRO_PLAN%TYPE,
  p_numeroLote      PRE_ERRO_PLANI.NUM_LOTE%TYPE,
  p_nombreArchivo   PRE_ERRO_PLANI.NOM_ARCH%TYPE,
  p_nombreCampo     PRE_ERRO_PLANI.NOM_CAMP%TYPE,
  p_obsErrorPlani   PRE_ERRO_PLANI.OBS_ERRO%TYPE);
  
END INT_PKG_PRE;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_PRE" IS

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_OFERT
  Fecha Creacion    : 07/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_ofert_cabec
  (
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
      SELECT DISTINCT a.oid_ofer,
             a.coes_oid_estr,
             a.num_ofer,
             a.num_orde,
             a.num_grup,
             a.num_grup_cndt,
             a.num_grup_cond,
             a.val_cond_g1_cndt,
             a.val_cond_g2_cndo,
             a.num_paqu,
             a.num_prim_posi_rank,
             a.num_ulti_posi_rank,
             a.fopa_oid_form_pago,
             a.sbac_oid_sbac,
             a.argv_oid_argu_vent,
             a.acce_oid_acce,
             --a.mfca_oid_cabe,
             d.peri_oid_peri,
             a.ind_desp_compl,
             a.ind_desp_auto,
             a.ind_recu_obli,
             a.ocat_oid_cata
        FROM pre_ofert a,
             pre_matri_factu_cabec c,
             pre_tmp_ofert_modif b,
             cra_perio d
       WHERE a.oid_ofer = b.oid_ofer
         AND b.num_secu_usua = psNumeroSecuencia
         AND a.mfca_oid_cabe=c.oid_cabe
         AND c.perd_oid_peri=d.oid_peri;

   TYPE interfazrec IS RECORD(
     oidOferta                   pre_ofert.oid_ofer%TYPE,
     oidEstrategia               pre_ofert.coes_oid_estr%TYPE,
     numOferta                   pre_ofert.num_ofer%TYPE,
     numOrden                    pre_ofert.num_orde%TYPE,
     numGrupo                    pre_ofert.num_grup%TYPE,
     numGrupoCondicionantes      pre_ofert.num_grup_cndt%TYPE,
     numGrupoCondicionados       pre_ofert.num_grup_cond%TYPE,
     valCondicionCondicionantes  pre_ofert.val_cond_g1_cndt%TYPE,
     valCondicionCondicionados   pre_ofert.val_cond_g2_cndo%TYPE,
     numPaquetes                 pre_ofert.num_paqu%TYPE,
     numPrimPosicionRanking      pre_ofert.num_prim_posi_rank%TYPE,
     numUltiPosicionRanking      pre_ofert.num_ulti_posi_rank%TYPE,
     oidFormaPago                pre_ofert.fopa_oid_form_pago%TYPE,
     oidSubacceso                pre_ofert.sbac_oid_sbac%TYPE,
     oidArgumentoVenta           pre_ofert.argv_oid_argu_vent%TYPE,
     oidAcceso                   pre_ofert.acce_oid_acce%TYPE,
     oidCabeceraMatrizFactu      pre_ofert.mfca_oid_cabe%TYPE,
     indDespachoCompleto         pre_ofert.ind_desp_compl%TYPE,
     indDespachoAutomatico       pre_ofert.ind_desp_auto%TYPE,
     indRecuperacionObligat      pre_ofert.ind_recu_obli%TYPE,
     oidCatalago                 pre_ofert.ocat_oid_cata%TYPE
    );

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   lbabrirutlfile  BOOLEAN;


 BEGIN

   lbabrirutlfile := TRUE;
   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

               /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                 lsdirtempo, lsnombrearchivo, v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := interfazrecord(x).oidOferta                       || ';' ||
                       interfazrecord(x).oidEstrategia                   || ';' ||
                       interfazrecord(x).numOferta                       || ';' ||
                       interfazrecord(x).numOrden                        || ';' ||
                       interfazrecord(x).numGrupo                        || ';' ||
                       interfazrecord(x).numGrupoCondicionantes          || ';' ||
                       interfazrecord(x).numGrupoCondicionados           || ';' ||
                       interfazrecord(x).valCondicionCondicionantes      || ';' ||
                       interfazrecord(x).valCondicionCondicionados       || ';' ||
                       interfazrecord(x).numPaquetes                     || ';' ||
                       interfazrecord(x).numPrimPosicionRanking          || ';' ||
                       interfazrecord(x).numUltiPosicionRanking          || ';' ||
                       interfazrecord(x).oidFormaPago                    || ';' ||
                       interfazrecord(x).oidSubacceso                    || ';' ||
                       interfazrecord(x).oidArgumentoVenta               || ';' ||
                       interfazrecord(x).oidAcceso                       || ';' ||
                       interfazrecord(x).oidCabeceraMatrizFactu          || ';' ||
                       interfazrecord(x).indDespachoCompleto             || ';' ||
                       interfazrecord(x).indDespachoAutomatico           || ';' ||
                       interfazrecord(x).indRecuperacionObligat          || ';' ||
                       interfazrecord(x).oidCatalago;

            utl_file.put_line(v_handle,lslinea);
          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_gener_ofert_cabec: '||ls_sqlerrm);
 END int_pr_pre_gener_ofert_cabec;

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_OFERT_DETAL
  Fecha Creacion    : 07/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_ofert_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
      SELECT DISTINCT
             p.oid_deta_ofer,
             p.ofer_oid_ofer,
             m.cod_sap,
             p.num_line_ofer,
             p.val_text_brev,
             p.num_unid_esti,
             p.cod_orig,
             p.val_fact_repe,
             p.num_posi_rank,
             p.ind_prod_prin,
             p.imp_prec_cata,
             p.imp_prec_posi,
             p.imp_cost_esta,
             p.imp_vent_neta_esti,
             p.num_pagi_cata,
             p.ocat_oid_catal,
             p.tofe_oid_tipo_ofer,
             p.civi_oid_ciclo_vida,
             p.cndp_oid_cond_prom,
             p.fopa_oid_form_pago,
             p.gofe_oid_grup_ofer,
             p.ind_digi,
             p.ind_impr_gp,
             p.val_posi_pagi,
             p.val_codi_vent,
             p.val_cent,
             p.precio_unitario,
             p.num_punt_fijo,
             p.vari_oid_vari,
             p.prfi_oid_prog_fide,
             p.num_orde_deta
        FROM pre_ofert_detal p,
             pre_tmp_ofert_modif b,
             mae_produ m
       WHERE p.prod_oid_prod = m.oid_prod
         AND p.ofer_oid_ofer = b.oid_ofer
         AND b.num_secu_usua = psNumeroSecuencia;

   TYPE interfazrec IS RECORD(
     oidDetalleOferta            pre_ofert_detal.oid_deta_ofer%TYPE,
     oidOferta                   pre_ofert_detal.ofer_oid_ofer%TYPE,
     codsap                      mae_produ.cod_sap%TYPE,
     numLineaOferta              pre_ofert_detal.num_line_ofer%TYPE,
     textoBreve                  pre_ofert_detal.val_text_brev%TYPE,
     numUnidadesEstimadas        pre_ofert_detal.num_unid_esti%TYPE,
     codOrigen                   pre_ofert_detal.cod_orig%TYPE,
     factorRepeticion            pre_ofert_detal.val_fact_repe%TYPE,
     numPosicionRanking          pre_ofert_detal.num_posi_rank%TYPE,
     indProductoPrincipal        pre_ofert_detal.ind_prod_prin%TYPE,
     impPrecioCatalogo           pre_ofert_detal.imp_prec_cata%TYPE,
     impPrecioPosicionamiento    pre_ofert_detal.imp_prec_posi%TYPE,
     impCosteEstandar            pre_ofert_detal.imp_cost_esta%TYPE,
     impVentaNetaEstimada        pre_ofert_detal.imp_vent_neta_esti%TYPE,
     numPaginaCatalogo           pre_ofert_detal.num_pagi_cata%TYPE,
     oidCatalogo                 pre_ofert_detal.ocat_oid_catal%TYPE,
     oidTipoOferta               pre_ofert_detal.tofe_oid_tipo_ofer%TYPE,
     oidCicloVida                pre_ofert_detal.civi_oid_ciclo_vida%TYPE,
     oidCondicionPromocion       pre_ofert_detal.cndp_oid_cond_prom%TYPE,
     oidFormaPago                pre_ofert_detal.fopa_oid_form_pago%TYPE,
     oidGrupoOfertas             pre_ofert_detal.gofe_oid_grup_ofer%TYPE,
     indDigitable                pre_ofert_detal.ind_digi%TYPE,
     indImpresion                pre_ofert_detal.ind_impr_gp%TYPE,
     valPosicionPagina           pre_ofert_detal.val_posi_pagi%TYPE,
     valCodigoVenta              pre_ofert_detal.val_codi_vent%TYPE,
     valCentro                   pre_ofert_detal.val_cent%TYPE,
     precioUnitario              pre_ofert_detal.precio_unitario%TYPE,
     numPuntoFijo                pre_ofert_detal.num_punt_fijo%TYPE,
     oidVari                     pre_ofert_detal.vari_oid_vari%TYPE,
     oidProgFide                 pre_ofert_detal.prfi_oid_prog_fide%TYPE,
     numOrdenDetalle             pre_ofert_detal.num_orde_deta%TYPE
    );

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   lbabrirutlfile  BOOLEAN;

 BEGIN

   lbabrirutlfile := TRUE;
   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

               /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                 lsdirtempo, lsnombrearchivo, v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := interfazrecord(x).oidDetalleOferta            || ';' ||
                       interfazrecord(x).oidOferta                   || ';' ||
                       interfazrecord(x).codsap                      || ';' ||
                       interfazrecord(x).numLineaOferta              || ';' ||
                       interfazrecord(x).textoBreve                  || ';' ||
                       interfazrecord(x).numUnidadesEstimadas        || ';' ||
                       interfazrecord(x).codOrigen                   || ';' ||
                       interfazrecord(x).factorRepeticion            || ';' ||
                       interfazrecord(x).numPosicionRanking          || ';' ||
                       interfazrecord(x).indProductoPrincipal        || ';' ||
                       interfazrecord(x).impPrecioCatalogo           || ';' ||
                       interfazrecord(x).impPrecioPosicionamiento    || ';' ||
                       interfazrecord(x).impCosteEstandar            || ';' ||
                       interfazrecord(x).impVentaNetaEstimada        || ';' ||
                       interfazrecord(x).numPaginaCatalogo           || ';' ||
                       interfazrecord(x).oidCatalogo                 || ';' ||
                       interfazrecord(x).oidTipoOferta               || ';' ||
                       interfazrecord(x).oidCicloVida                || ';' ||
                       interfazrecord(x).oidCondicionPromocion       || ';' ||
                       interfazrecord(x).oidFormaPago                || ';' ||
                       interfazrecord(x).oidGrupoOfertas             || ';' ||
                       interfazrecord(x).indDigitable                || ';' ||
                       interfazrecord(x).indImpresion                || ';' ||
                       interfazrecord(x).valPosicionPagina           || ';' ||
                       interfazrecord(x).valCodigoVenta              || ';' ||
                       interfazrecord(x).valCentro                   || ';' ||
                       interfazrecord(x).precioUnitario              || ';' ||
                       interfazrecord(x).numPuntoFijo                || ';' ||
                       interfazrecord(x).oidVari                     || ';' ||
                       interfazrecord(x).oidProgFide                 || ';' ||
                       interfazrecord(x).numOrdenDetalle;

            utl_file.put_line(v_handle,lslinea);
          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_gener_ofert_detal: '||ls_sqlerrm);
 END int_pr_pre_gener_ofert_detal;

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_GRUPO_OFERT
  Fecha Creacion    : 07/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_grupo_ofert
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  )
 IS

   CURSOR c_interfaz IS
     SELECT distinct a.oid_grup_ofer,
            a.ofer_oid_ofer,
            a.num_grup,
            a.cod_fact_cuad,
            a.cues_oid_ind_cuad_tipo_estr,
            a.ind_cndt,
            a.ind_cndo,
            a.ind_grup
       FROM pre_grupo_ofert a,
            pre_tmp_ofert_modif b
      WHERE a.ofer_oid_ofer = b.oid_ofer
        AND b.num_secu_usua = psNumeroSecuencia;

   TYPE interfazrec IS RECORD(
     oidGrupoOferta                       pre_grupo_ofert.oid_grup_ofer%TYPE,
     oidOferta                            pre_grupo_ofert.ofer_oid_ofer%TYPE,
     numGrupo                             pre_grupo_ofert.num_grup%TYPE,
     codFactorCuadre                      pre_grupo_ofert.cod_fact_cuad%TYPE,
     oidIndicadorCuadreTipoEst            pre_grupo_ofert.cues_oid_ind_cuad_tipo_estr%TYPE,
     indCondicionante                     pre_grupo_ofert.ind_cndt%TYPE,
     indCondicionado                      pre_grupo_ofert.ind_cndo%TYPE,
     indGrupo                             pre_grupo_ofert.ind_grup%TYPE
    );

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   lbabrirutlfile  BOOLEAN;

 BEGIN

   lbabrirutlfile := TRUE;
   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
          INTO interfazrecord LIMIT w_filas;

               /* Procedimiento inicial para generar interfaz */
        IF lbabrirutlfile THEN
          gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                 lsdirtempo, lsnombrearchivo, v_handle);
          lbabrirutlfile := FALSE;
        END IF;

        IF interfazrecord.COUNT > 0 THEN
          FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
            lslinea := interfazrecord(x).oidGrupoOferta                           || ';' ||
                       interfazrecord(x).oidOferta                                || ';' ||
                       interfazrecord(x).numGrupo                                 || ';' ||
                       interfazrecord(x).codFactorCuadre                          || ';' ||
                       interfazrecord(x).oidIndicadorCuadreTipoEst                || ';' ||
                       interfazrecord(x).indCondicionante                         || ';' ||
                       interfazrecord(x).indCondicionado                          || ';' ||
                       interfazrecord(x).indGrupo;

            utl_file.put_line(v_handle,lslinea);
          END LOOP;

        END IF;
        EXIT WHEN c_interfaz%NOTFOUND;

     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_gener_grupo_ofert: '||ls_sqlerrm);
 END int_pr_pre_gener_grupo_ofert;

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_PROMO
  Fecha Creacion    : 07/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_promo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  )
  IS
    CURSOR c_interfaz IS
      SELECT distinct a.oid_prom,
             a.ofer_oid_ofer,
             a.num_cond,
             a.val_fact_cuad,
             a.icpr_oid_indi_cuad_prom
        FROM pre_promo a,
             pre_tmp_ofert_modif b
       WHERE a.ofer_oid_ofer = b.oid_ofer
         AND b.num_secu_usua = psNumeroSecuencia;

   TYPE interfazrec IS RECORD(
     oidPromocion                     pre_promo.oid_prom%TYPE,
     oidOferta                        pre_promo.ofer_oid_ofer%TYPE,
     numCondicion                     pre_promo.num_cond%TYPE,
     codFactorCuadre                  pre_promo.val_fact_cuad%TYPE,
     oidIndicadorCuadrePromocion      pre_promo.icpr_oid_indi_cuad_prom%TYPE
    );

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   lbabrirutlfile  BOOLEAN;

  BEGIN

   lbabrirutlfile := TRUE;
   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
         INTO interfazrecord LIMIT w_filas;

       /* Procedimiento inicial para generar interfaz */
       IF lbabrirutlfile THEN
         gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                 lsdirtempo, lsnombrearchivo, v_handle);
         lbabrirutlfile := FALSE;
       END IF;

       IF interfazrecord.COUNT > 0 THEN
         FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
           lslinea := interfazrecord(x).oidPromocion                        || ';' ||
                      interfazrecord(x).oidOferta                           || ';' ||
                      interfazrecord(x).numCondicion                        || ';' ||
                      interfazrecord(x).codFactorCuadre                     || ';' ||
                      interfazrecord(x).oidIndicadorCuadrePromocion;

           utl_file.put_line(v_handle,lslinea);
         END LOOP;

       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;

     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;
   RETURN;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_gener_promo: '||ls_sqlerrm);
 END int_pr_pre_gener_promo;

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_RANGO_PROMO
  Fecha Creacion    : 07/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_rango_promo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
     SELECT distinct r.oid_rang_prom,
            r.ocat_oid_cata,
            r.pomo_oid_prom,
            r.cod_tipo_rang,
            r.num_rang_inte,
            r.val_desd,
            r.val_hast,
            r.ind_excl,
            decode(r.cod_tipo_rang, 'P', (SELECT m.cod_sap FROM mae_produ m WHERE m.oid_prod = r.val_desd), NULL) COD_SAP
       FROM pre_promo p,
            pre_rango_promo r,
            pre_tmp_ofert_modif b
      WHERE r.pomo_oid_prom = p.oid_prom
        AND p.ofer_oid_ofer = b.oid_ofer
        AND b.num_secu_usua = psNumeroSecuencia;

   TYPE interfazrec IS RECORD(
     oidRangoPromocion                     pre_rango_promo.oid_rang_prom%TYPE,
     oidCatalogo                           pre_rango_promo.ocat_oid_cata%TYPE,
     oidPromocion                          pre_rango_promo.pomo_oid_prom%TYPE,
     codTipoRango                          pre_rango_promo.cod_tipo_rang%TYPE,
     numRangoInterno                       pre_rango_promo.num_rang_inte%TYPE,
     valDesde                              pre_rango_promo.val_desd%TYPE,
     valHasta                              pre_rango_promo.val_hast%TYPE,
     indExclusion                          pre_rango_promo.ind_excl%TYPE,
     codSAP                                mae_produ.cod_sap%TYPE
    );

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   lbabrirutlfile  BOOLEAN;


 BEGIN

   lbabrirutlfile := TRUE;
   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
         INTO interfazrecord LIMIT w_filas;

       /* Procedimiento inicial para generar interfaz */
       IF lbabrirutlfile THEN
         gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                 lsdirtempo, lsnombrearchivo, v_handle);
         lbabrirutlfile := FALSE;
       END IF;

       IF interfazrecord.COUNT > 0 THEN
         FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
           lslinea := interfazrecord(x).oidRangoPromocion      || ';' ||
                      interfazrecord(x).oidCatalogo            || ';' ||
                      interfazrecord(x).oidPromocion           || ';' ||
                      interfazrecord(x).codTipoRango           || ';' ||
                      interfazrecord(x).numRangoInterno        || ';' ||
                      interfazrecord(x).valDesde               || ';' ||
                      interfazrecord(x).valHasta               || ';' ||
                      interfazrecord(x).indExclusion           || ';' ||
                      interfazrecord(x).codSAP;

           utl_file.put_line(v_handle,lslinea);
         END LOOP;

       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;

     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_gener_rango_promo: '||ls_sqlerrm);
 END int_pr_pre_gener_rango_promo;

/***************************************************************************
  Descripcion       : Procedimiento que genera el archivo PRE_MATRI_FACTU
  Fecha Creacion    : 07/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_gener_matri_factu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psNumeroSecuencia    VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
/*     SELECT a.oid_matr_fact,
            a.cod_esta,
            a.mfca_oid_cabe,
            a.ofde_oid_deta_ofer,
            a.num_punt_fijo,
            a.vari_oid_vari,
            a.prfi_oid_prog_fide,
            a.ind_matr_fact
       FROM pre_matri_factu a,
            pre_tmp_ofert_modif b, pre_ofert_detal c
      WHERE a.ofde_oid_deta_ofer = b.oid_deta_ofer
        AND b.num_secu_usua = psNumeroSecuencia;*/
     SELECT a.oid_matr_fact,
            a.cod_esta,
            a.mfca_oid_cabe,
            a.ofde_oid_deta_ofer,
            a.num_punt_fijo,
            a.vari_oid_vari,
            a.prfi_oid_prog_fide,
            a.ind_matr_fact
       FROM pre_matri_factu a--,
            --pre_tmp_ofert_modif b, pre_ofert c
      WHERE a.ofde_oid_deta_ofer in (select oid_deta_ofer from pre_ofert_detal where ofer_oid_ofer in (select oid_ofer from pre_tmp_ofert_modif where num_secu_usua = psNumeroSecuencia));


   TYPE interfazrec IS RECORD(
     oidMatrizFacturacion               pre_matri_factu.oid_matr_fact%TYPE,
     codEstado                          pre_matri_factu.cod_esta%TYPE,
     oidCabecera                        pre_matri_factu.mfca_oid_cabe%TYPE,
     oidDetalleOferta                   pre_matri_factu.ofde_oid_deta_ofer%TYPE,
     numPuntoFijo                       pre_matri_factu.num_punt_fijo%TYPE,
     oidVari                            pre_matri_factu.vari_oid_vari%TYPE,
     oidProgFide                        pre_matri_factu.prfi_oid_prog_fide%TYPE,
     indMatrizFacturacion               pre_matri_factu.ind_matr_fact%TYPE
    );

   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   lbabrirutlfile  BOOLEAN;

 BEGIN

   lbabrirutlfile := TRUE;
   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
         INTO interfazrecord LIMIT w_filas;

       /* Procedimiento inicial para generar interfaz */
       IF lbabrirutlfile THEN
         gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                 lsdirtempo, lsnombrearchivo, v_handle);
         lbabrirutlfile := FALSE;
       END IF;

       IF interfazrecord.COUNT > 0 THEN
         FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
           lslinea := interfazrecord(x).oidMatrizFacturacion      || ';' ||
                      interfazrecord(x).codEstado                 || ';' ||
                      interfazrecord(x).oidCabecera               || ';' ||
                      interfazrecord(x).oidDetalleOferta          || ';' ||
                      interfazrecord(x).numPuntoFijo              || ';' ||
                      interfazrecord(x).oidVari                   || ';' ||
                      interfazrecord(x).oidProgFide               || ';' ||
                      interfazrecord(x).indMatrizFacturacion;

           utl_file.put_line(v_handle,lslinea);
         END LOOP;

       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;

     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

   END IF;
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_gener_matri_factu: '||ls_sqlerrm);
 END int_pr_pre_gener_matri_factu;

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_OFERT en la
                       tabla temporal PRE_TMP_OFERT
  Fecha Creacion    : 07/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_ofert_cabec_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
     SELECT a.pos_camp,
            a.lon_camp,
            a.can_deci,
            a.ide_camp,
            t.sig_tdat
       FROM bas_estru_archi a,
            bas_tipo_dato   t
      WHERE a.tdat_cod_tdat = t.cod_tdat
        AND a.est_esar != 9
        AND a.pais_cod_pais = pscodigopais
        AND a.sist_cod_sist = pscodigosistema
        AND a.inte_cod_inte = pscodigointerfaz
     ORDER BY a.pos_camp ASC;

   TYPE interfazcab IS RECORD(
     posiccampo  bas_estru_archi.pos_camp%TYPE,
     longcampo   bas_estru_archi.lon_camp%TYPE,
     cantdecimal bas_estru_archi.can_deci%TYPE,
     idcampo     bas_estru_archi.ide_camp%TYPE,
     sigla       bas_tipo_dato.sig_tdat%TYPE);

   TYPE interfazcabtab IS TABLE OF interfazcab;

   interfazrecord interfazcabtab;

   TYPE t_oidOferta              IS TABLE OF pre_ofert.oid_ofer%TYPE;
   TYPE t_oidEstrategia          IS TABLE OF pre_ofert.coes_oid_estr%TYPE;
   TYPE t_numOferta              IS TABLE OF pre_ofert.num_ofer%TYPE;
   TYPE t_numOrden               IS TABLE OF pre_ofert.num_orde%TYPE;
   TYPE t_numGrupo               IS TABLE OF pre_ofert.num_grup%TYPE;
   TYPE t_numGpoCondicionantes   IS TABLE OF pre_ofert.num_grup_cndt%TYPE;
   TYPE t_numGpoCondicionados    IS TABLE OF pre_ofert.num_grup_cond%TYPE;
   TYPE t_valCondCondicionantes  IS TABLE OF pre_ofert.val_cond_g1_cndt%TYPE;
   TYPE t_valCondCondicionados   IS TABLE OF pre_ofert.val_cond_g2_cndo%TYPE;
   TYPE t_numPaquetes            IS TABLE OF pre_ofert.num_paqu%TYPE;
   TYPE t_numPrimPosRanking      IS TABLE OF pre_ofert.num_prim_posi_rank%TYPE;
   TYPE t_numUltiPosRanking      IS TABLE OF pre_ofert.num_ulti_posi_rank%TYPE;
   TYPE t_oidFormaPago           IS TABLE OF pre_ofert.fopa_oid_form_pago%TYPE;
   TYPE t_oidSubacceso           IS TABLE OF pre_ofert.sbac_oid_sbac%TYPE;
   TYPE t_oidArgumentoVenta      IS TABLE OF pre_ofert.argv_oid_argu_vent%TYPE;
   TYPE t_oidAcceso              IS TABLE OF pre_ofert.acce_oid_acce%TYPE;
   TYPE t_oidCabMatrizFactu      IS TABLE OF pre_ofert.mfca_oid_cabe%TYPE;
   TYPE t_indDespachoCompleto    IS TABLE OF pre_ofert.ind_desp_compl%TYPE;
   TYPE t_indDespachoAutomatico  IS TABLE OF pre_ofert.ind_desp_auto%TYPE;
   TYPE t_indRecuperacionObligat IS TABLE OF pre_ofert.ind_recu_obli%TYPE;
   TYPE t_oidCatalago            IS TABLE OF pre_ofert.ocat_oid_cata%TYPE;

   v_oidOferta               t_oidOferta              := t_oidOferta();
   v_oidEstrategia           t_oidEstrategia          := t_oidEstrategia();
   v_numOferta               t_numOferta              := t_numOferta();
   v_numOrden                t_numOrden               := t_numOrden();
   v_numGrupo                t_numGrupo               := t_numGrupo();
   v_numGpoCondicionantes    t_numGpoCondicionantes   := t_numGpoCondicionantes();
   v_numGpoCondicionados     t_numGpoCondicionados    := t_numGpoCondicionados();
   v_valCondCondicionantes   t_valCondCondicionantes  := t_valCondCondicionantes();
   v_valCondCondicionados    t_valCondCondicionados   := t_valCondCondicionados();
   v_numPaquetes             t_numPaquetes            := t_numPaquetes();
   v_numPrimPosRanking       t_numPrimPosRanking      := t_numPrimPosRanking();
   v_numUltiPosRanking       t_numUltiPosRanking      := t_numUltiPosRanking();
   v_oidFormaPago            t_oidFormaPago           := t_oidFormaPago();
   v_oidSubacceso            t_oidSubacceso           := t_oidSubacceso();
   v_oidArgumentoVenta       t_oidArgumentoVenta      := t_oidArgumentoVenta();
   v_oidAcceso               t_oidAcceso              := t_oidAcceso();
   v_oidCabMatrizFactu       t_oidCabMatrizFactu      := t_oidCabMatrizFactu();
   v_indDespachoCompleto     t_indDespachoCompleto    := t_indDespachoCompleto();
   v_indDespachoAutomatico   t_indDespachoAutomatico  := t_indDespachoAutomatico();
   v_indRecuperacionObliga   t_indRecuperacionObligat := t_indRecuperacionObligat();
   v_oidCatalago             t_oidCatalago            := t_oidCatalago();

   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   i               BINARY_INTEGER := 0;
   posicion        NUMBER := 0;
   longitud        NUMBER := 0;
   inicio          NUMBER := 0;

 BEGIN

   /* Procedimiento inicial para generar interfaz */
   gen_pkg_inter_archi.gen_pr_inici_inter_lectu( pscodigopais, pscodigosistema, pscodigointerfaz,
                                                 psnombrearchivo, 'TXT', lsdirtempo, lsnombrearchivo,
                                                 v_handle );

   IF utl_file.is_open(v_handle) THEN
     LOOP
       BEGIN
         utl_file.get_line(v_handle, lslinea);
         i      := i + 1;
         inicio := 1;
         IF lslinea IS NULL THEN
           EXIT;
         END IF;

         OPEN c_interfaz;
         LOOP
           FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;
           IF interfazrecord.COUNT > 0 THEN
             FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_oidOferta.EXTEND;
                  v_oidOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 2) THEN
                  v_oidEstrategia.EXTEND;
                  v_oidEstrategia(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 3) THEN
                  v_numOferta.EXTEND;
                  v_numOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 4) THEN
                  v_numOrden.EXTEND;
                  v_numOrden(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 5) THEN
                  v_numGrupo.EXTEND;
                  v_numGrupo(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 6) THEN
                  v_numGpoCondicionantes.EXTEND;
                  v_numGpoCondicionantes(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 7) THEN
                  v_numGpoCondicionados.EXTEND;
                  v_numGpoCondicionados(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 8) THEN
                  v_valCondCondicionantes.EXTEND;
                  v_valCondCondicionantes(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 9) THEN
                  v_valCondCondicionados.EXTEND;
                  v_valCondCondicionados(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 10) THEN
                  v_numPaquetes.EXTEND;
                  v_numPaquetes(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 11) THEN
                  v_numPrimPosRanking.EXTEND;
                  v_numPrimPosRanking(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 12) THEN
                  v_numUltiPosRanking.EXTEND;
                  v_numUltiPosRanking(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 13) THEN
                  v_oidFormaPago.EXTEND;
                  v_oidFormaPago(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 14) THEN
                  v_oidSubacceso.EXTEND;
                  v_oidSubacceso(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 15) THEN
                  v_oidArgumentoVenta.EXTEND;
                  v_oidArgumentoVenta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 16) THEN
                  v_oidAcceso.EXTEND;
                  v_oidAcceso(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 17) THEN
                  v_oidCabMatrizFactu.EXTEND;
                  v_oidCabMatrizFactu(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 18) THEN
                  v_indDespachoCompleto.EXTEND;
                  v_indDespachoCompleto(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 19) THEN
                  v_indDespachoAutomatico.EXTEND;
                  v_indDespachoAutomatico(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 20) THEN
                  v_indRecuperacionObliga.EXTEND;
                  v_indRecuperacionObliga(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 21) THEN
                  v_oidCatalago.EXTEND;
                  v_oidCatalago(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;

                inicio := inicio + longitud;

              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
         END LOOP;
         CLOSE c_interfaz;

       EXCEPTION
         WHEN no_data_found THEN
           EXIT;
       END;
     END LOOP;
   END IF;

   utl_file.fclose(v_handle);

   -- Limpiando la tabla temporal
   DELETE FROM pre_tmp_ofert;

   -- Bulk bind of data in memory table...
   FORALL i IN 1 .. v_oidOferta.COUNT
     INSERT INTO pre_tmp_ofert
        (   oid_ofer,
            coes_oid_estr,
            num_ofer,
            num_orde,
            num_grup,
            num_grup_cndt,
            num_grup_cond,
            val_cond_g1_cndt,
            val_cond_g2_cndo,
            num_paqu,
            num_prim_posi_rank,
            num_ulti_posi_rank,
            fopa_oid_form_pago,
            sbac_oid_sbac,
            argv_oid_argu_vent,
            acce_oid_acce,
            mfca_oid_cabe,
            ind_desp_compl,
            ind_desp_auto,
            ind_recu_obli,
            ocat_oid_cata,
            oid_ofer_nuev )
     VALUES
        (   v_oidOferta(i),
            v_oidEstrategia(i),
            v_numOferta(i),
            v_numOrden(i),
            v_numGrupo(i),
            v_numGpoCondicionantes(i),
            v_numGpoCondicionados(i),
            v_valCondCondicionantes(i),
            v_valCondCondicionados(i),
            v_numPaquetes(i),
            v_numPrimPosRanking(i),
            v_numUltiPosRanking(i),
            v_oidFormaPago(i),
            v_oidSubacceso(i),
            v_oidArgumentoVenta(i),
            v_oidAcceso(i),
            v_oidCabMatrizFactu(i),
            v_indDespachoCompleto(i),
            v_indDespachoAutomatico(i),
            v_indRecuperacionObliga(i),
            v_oidCatalago(i),
            pre_ofer_seq.nextval  );

    RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_ofert_cabec_tempo: '||ls_sqlerrm);
 END int_pr_pre_ofert_cabec_tempo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_GRUPO_OFERT
                      en la tabla temporal PRE_TMP_GRUPO_OFERT
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_grupo_ofert_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
     SELECT a.pos_camp,
            a.lon_camp,
            a.can_deci,
            a.ide_camp,
            t.sig_tdat
       FROM bas_estru_archi a,
            bas_tipo_dato   t
      WHERE a.tdat_cod_tdat = t.cod_tdat
        AND a.est_esar != 9
        AND a.pais_cod_pais = pscodigopais
        AND a.sist_cod_sist = pscodigosistema
        AND a.inte_cod_inte = pscodigointerfaz
     ORDER BY a.pos_camp ASC;

   TYPE interfazcab IS RECORD(
     posiccampo  bas_estru_archi.pos_camp%TYPE,
     longcampo   bas_estru_archi.lon_camp%TYPE,
     cantdecimal bas_estru_archi.can_deci%TYPE,
     idcampo     bas_estru_archi.ide_camp%TYPE,
     sigla       bas_tipo_dato.sig_tdat%TYPE);

   TYPE interfazcabtab IS TABLE OF interfazcab;

   interfazrecord interfazcabtab;

   TYPE t_oidGrupoOferta             IS TABLE OF pre_grupo_ofert.oid_grup_ofer%TYPE;
   TYPE t_oidOferta                  IS TABLE OF pre_grupo_ofert.ofer_oid_ofer%TYPE;
   TYPE t_numGrupo                   IS TABLE OF pre_grupo_ofert.num_grup%TYPE;
   TYPE t_codFactorCuadre            IS TABLE OF pre_grupo_ofert.cod_fact_cuad%TYPE;
   TYPE t_oidIndicadorCuadreTipoEst  IS TABLE OF pre_grupo_ofert.cues_oid_ind_cuad_tipo_estr%TYPE;
   TYPE t_indCondicionante           IS TABLE OF pre_grupo_ofert.ind_cndt%TYPE;
   TYPE t_indCondicionado            IS TABLE OF pre_grupo_ofert.ind_cndo%TYPE;
   TYPE t_indGrupo                   IS TABLE OF pre_grupo_ofert.ind_grup%TYPE;

   v_oidGrupoOferta            t_oidGrupoOferta            := t_oidGrupoOferta();
   v_oidOferta                 t_oidOferta                 := t_oidOferta();
   v_numGrupo                  t_numGrupo                  := t_numGrupo();
   v_codFactorCuadre           t_codFactorCuadre           := t_codFactorCuadre();
   v_oidIndicadorCuadreTipoEst t_oidIndicadorCuadreTipoEst := t_oidIndicadorCuadreTipoEst();
   v_indCondicionante          t_indCondicionante          := t_indCondicionante();
   v_indCondicionado           t_indCondicionado           := t_indCondicionado();
   v_indGrupo                  t_indGrupo                  := t_indGrupo();

   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   i               BINARY_INTEGER := 0;
   posicion        NUMBER := 0;
   longitud        NUMBER := 0;
   inicio          NUMBER := 0;

 BEGIN

   /* Procedimiento inicial para generar interfaz */
   gen_pkg_inter_archi.gen_pr_inici_inter_lectu( pscodigopais, pscodigosistema, pscodigointerfaz,
                                                 psnombrearchivo, 'TXT', lsdirtempo, lsnombrearchivo,
                                                 v_handle );

   IF utl_file.is_open(v_handle) THEN
     LOOP
       BEGIN
         utl_file.get_line(v_handle, lslinea);
         i      := i + 1;
         inicio := 1;
         IF lslinea IS NULL THEN
           EXIT;
         END IF;

         OPEN c_interfaz;
         LOOP
           FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;
           IF interfazrecord.COUNT > 0 THEN
             FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_oidGrupoOferta.EXTEND;
                  v_oidGrupoOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 2) THEN
                  v_oidOferta.EXTEND;
                  v_oidOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 3) THEN
                  v_numGrupo.EXTEND;
                  v_numGrupo(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 4) THEN
                  v_codFactorCuadre.EXTEND;
                  v_codFactorCuadre(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 5) THEN
                  v_oidIndicadorCuadreTipoEst.EXTEND;
                  v_oidIndicadorCuadreTipoEst(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 6) THEN
                  v_indCondicionante.EXTEND;
                  v_indCondicionante(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 7) THEN
                  v_indCondicionado.EXTEND;
                  v_indCondicionado(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 8) THEN
                  v_indGrupo .EXTEND;
                  v_indGrupo(i) := TRIM(substr(lslinea, inicio, longitud));

                END IF;

                inicio := inicio + longitud;

              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
         END LOOP;
         CLOSE c_interfaz;

       EXCEPTION
         WHEN no_data_found THEN
           EXIT;
       END;
     END LOOP;
   END IF;

   utl_file.fclose(v_handle);

   -- Limpiando la tabla temporal
   DELETE FROM pre_tmp_grupo_ofert;

   -- Bulk bind of data in memory table...
   FORALL i IN 1 .. v_oidGrupoOferta.COUNT
     INSERT INTO pre_tmp_grupo_ofert
       (  oid_grup_ofer,
          ofer_oid_ofer,
          num_grup,
          cod_fact_cuad,
          cues_oid_ind_cuad_tipo_estr,
          ind_cndt,
          ind_cndo,
          ind_grup,
          oid_grup_ofer_nuev
       )
     VALUES
        ( v_oidGrupoOferta(i),
          v_oidOferta(i),
          v_numGrupo(i),
          v_codFactorCuadre(i),
          v_oidIndicadorCuadreTipoEst(i),
          v_indCondicionante(i),
          v_indCondicionado(i),
          v_indGrupo(i),
          pre_gofe_seq.nextval
        );
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_grupo_ofert_tempo: '||ls_sqlerrm);
 END int_pr_pre_grupo_ofert_tempo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_OFERT_DETAL
                      en la tabla temporal PRE_TMP_OFERT
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_ofert_detal_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
     SELECT a.pos_camp,
            a.lon_camp,
            a.can_deci,
            a.ide_camp,
            t.sig_tdat
       FROM bas_estru_archi a,
            bas_tipo_dato   t
      WHERE a.tdat_cod_tdat = t.cod_tdat
        AND a.est_esar != 9
        AND a.pais_cod_pais = pscodigopais
        AND a.sist_cod_sist = pscodigosistema
        AND a.inte_cod_inte = pscodigointerfaz
     ORDER BY a.pos_camp ASC;

   TYPE interfazcab IS RECORD(
     posiccampo  bas_estru_archi.pos_camp%TYPE,
     longcampo   bas_estru_archi.lon_camp%TYPE,
     cantdecimal bas_estru_archi.can_deci%TYPE,
     idcampo     bas_estru_archi.ide_camp%TYPE,
     sigla       bas_tipo_dato.sig_tdat%TYPE);

   TYPE interfazcabtab IS TABLE OF interfazcab;

   interfazrecord interfazcabtab;

   TYPE t_oidDetalleOferta          IS TABLE OF pre_ofert_detal.oid_deta_ofer%TYPE;
   TYPE t_oidOferta                 IS TABLE OF pre_ofert_detal.ofer_oid_ofer%TYPE;
   TYPE t_codsap                    IS TABLE OF mae_produ.cod_sap%TYPE;
   TYPE t_numLineaOferta            IS TABLE OF pre_ofert_detal.num_line_ofer%TYPE;
   TYPE t_textoBreve                IS TABLE OF pre_ofert_detal.val_text_brev%TYPE;
   TYPE t_numUnidadesEstimadas      IS TABLE OF pre_ofert_detal.num_unid_esti%TYPE;
   TYPE t_codOrigen                 IS TABLE OF pre_ofert_detal.cod_orig%TYPE;
   TYPE t_factorRepeticion          IS TABLE OF pre_ofert_detal.val_fact_repe%TYPE;
   TYPE t_numPosicionRanking        IS TABLE OF pre_ofert_detal.num_posi_rank%TYPE;
   TYPE t_indProductoPrincipal      IS TABLE OF pre_ofert_detal.ind_prod_prin%TYPE;
   TYPE t_impPrecioCatalogo         IS TABLE OF pre_ofert_detal.imp_prec_cata%TYPE;
   TYPE t_impPrecioPosicionamiento  IS TABLE OF pre_ofert_detal.imp_prec_posi%TYPE;
   TYPE t_impCosteEstandar          IS TABLE OF pre_ofert_detal.imp_cost_esta%TYPE;
   TYPE t_impVentaNetaEstimada      IS TABLE OF pre_ofert_detal.imp_vent_neta_esti%TYPE;
   TYPE t_numPaginaCatalogo         IS TABLE OF pre_ofert_detal.num_pagi_cata%TYPE;
   TYPE t_oidCatalogo               IS TABLE OF pre_ofert_detal.ocat_oid_catal%TYPE;
   TYPE t_oidTipoOferta             IS TABLE OF pre_ofert_detal.tofe_oid_tipo_ofer%TYPE;
   TYPE t_oidCicloVida              IS TABLE OF pre_ofert_detal.civi_oid_ciclo_vida%TYPE;
   TYPE t_oidCondicionPromocion     IS TABLE OF pre_ofert_detal.cndp_oid_cond_prom%TYPE;
   TYPE t_oidFormaPago              IS TABLE OF pre_ofert_detal.fopa_oid_form_pago%TYPE;
   TYPE t_oidGrupoOfertas           IS TABLE OF pre_ofert_detal.gofe_oid_grup_ofer%TYPE;
   TYPE t_indDigitable              IS TABLE OF pre_ofert_detal.ind_digi%TYPE;
   TYPE t_indImpresion              IS TABLE OF pre_ofert_detal.ind_impr_gp%TYPE;
   TYPE t_valPosicionPagina         IS TABLE OF pre_ofert_detal.val_posi_pagi%TYPE;
   TYPE t_valCodigoVenta            IS TABLE OF pre_ofert_detal.val_codi_vent%TYPE;
   TYPE t_valCentro                 IS TABLE OF pre_ofert_detal.val_cent%TYPE;
   TYPE t_precioUnitario            IS TABLE OF pre_ofert_detal.precio_unitario%TYPE;
   TYPE t_numPuntoFijo              IS TABLE OF pre_ofert_detal.num_punt_fijo%TYPE;
   TYPE t_oidVari                   IS TABLE OF pre_ofert_detal.vari_oid_vari%TYPE;
   TYPE t_oidProgFide               IS TABLE OF pre_ofert_detal.prfi_oid_prog_fide%TYPE;
   TYPE t_numOrdenDetalle           IS TABLE OF pre_ofert_detal.num_orde_deta%TYPE;

   v_oidDetalleOferta               t_oidDetalleOferta          := t_oidDetalleOferta();
   v_oidOferta                      t_oidOferta                  := t_oidOferta();
   v_codsap                          t_codsap                    := t_codsap();
   v_numLineaOferta                  t_numLineaOferta            := t_numLineaOferta();
   v_textoBreve                      t_textoBreve                := t_textoBreve();
   v_numUnidadesEstimadas            t_numUnidadesEstimadas       := t_numUnidadesEstimadas();
   v_codOrigen                      t_codOrigen                  := t_codOrigen();
   v_factorRepeticion                t_factorRepeticion          := t_factorRepeticion();
   v_numPosicionRanking              t_numPosicionRanking        := t_numPosicionRanking();
   v_indProductoPrincipal            t_indProductoPrincipal      := t_indProductoPrincipal();
   v_impPrecioCatalogo              t_impPrecioCatalogo          := t_impPrecioCatalogo();
   v_impPrecioPosicionamiento        t_impPrecioPosicionamiento  := t_impPrecioPosicionamiento();
   v_impCosteEstandar                t_impCosteEstandar          := t_impCosteEstandar();
   v_impVentaNetaEstimada            t_impVentaNetaEstimada      := t_impVentaNetaEstimada();
   v_numPaginaCatalogo              t_numPaginaCatalogo          := t_numPaginaCatalogo();
   v_oidCatalogo                    t_oidCatalogo                := t_oidCatalogo();
   v_oidTipoOferta                  t_oidTipoOferta              := t_oidTipoOferta();
   v_oidCicloVida                    t_oidCicloVida              := t_oidCicloVida();
   v_oidCondicionPromocion          t_oidCondicionPromocion      := t_oidCondicionPromocion();
   v_oidFormaPago                    t_oidFormaPago              := t_oidFormaPago();
   v_oidGrupoOfertas                t_oidGrupoOfertas            := t_oidGrupoOfertas();
   v_indDigitable                    t_indDigitable              := t_indDigitable();
   v_indImpresion                    t_indImpresion              := t_indImpresion();
   v_valPosicionPagina              t_valPosicionPagina          := t_valPosicionPagina();
   v_valCodigoVenta                  t_valCodigoVenta            := t_valCodigoVenta();
   v_valCentro                      t_valCentro                  := t_valCentro();
   v_precioUnitario                  t_precioUnitario            := t_precioUnitario();
   v_numPuntoFijo                    t_numPuntoFijo              := t_numPuntoFijo();
   v_oidVari                        t_oidVari                    := t_oidVari();
   v_oidProgFide                    t_oidProgFide                := t_oidProgFide();
   v_numOrdenDetalle                t_numOrdenDetalle            := t_numOrdenDetalle();

   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   i               BINARY_INTEGER := 0;
   posicion        NUMBER := 0;
   longitud        NUMBER := 0;
   inicio          NUMBER := 0;

 BEGIN

   /* Procedimiento inicial para generar interfaz */
   gen_pkg_inter_archi.gen_pr_inici_inter_lectu( pscodigopais, pscodigosistema, pscodigointerfaz,
                                                 psnombrearchivo, 'TXT', lsdirtempo, lsnombrearchivo,
                                                 v_handle );

   IF utl_file.is_open(v_handle) THEN
     LOOP
       BEGIN
         utl_file.get_line(v_handle, lslinea);
         i      := i + 1;
         inicio := 1;
         IF lslinea IS NULL THEN
           EXIT;
         END IF;

         OPEN c_interfaz;
         LOOP
           FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;
           IF interfazrecord.COUNT > 0 THEN
             FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_oidDetalleOferta.EXTEND;
                  v_oidDetalleOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 2) THEN
                  v_oidOferta.EXTEND;
                  v_oidOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 3) THEN
                  v_codsap.EXTEND;
                  v_codsap(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 4) THEN
                  v_numLineaOferta.EXTEND;
                  v_numLineaOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 5) THEN
                  v_textoBreve.EXTEND;
                  v_textoBreve(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 6) THEN
                  v_numUnidadesEstimadas.EXTEND;
                  v_numUnidadesEstimadas(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 7) THEN
                  v_codOrigen.EXTEND;
                  v_codOrigen(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 8) THEN
                  v_factorRepeticion.EXTEND;
                  v_factorRepeticion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 9) THEN
                  v_numPosicionRanking.EXTEND;
                  v_numPosicionRanking(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 10) THEN
                  v_indProductoPrincipal.EXTEND;
                  v_indProductoPrincipal(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 11) THEN
                  v_impPrecioCatalogo.EXTEND;
                  v_impPrecioCatalogo(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 12) THEN
                  v_impPrecioPosicionamiento.EXTEND;
                  v_impPrecioPosicionamiento(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 13) THEN
                  v_impCosteEstandar.EXTEND;
                  v_impCosteEstandar(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 14) THEN
                  v_impVentaNetaEstimada.EXTEND;
                  v_impVentaNetaEstimada(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 15) THEN
                  v_numPaginaCatalogo.EXTEND;
                  v_numPaginaCatalogo(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 16) THEN
                  v_oidCatalogo.EXTEND;
                  v_oidCatalogo(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 17) THEN
                  v_oidTipoOferta.EXTEND;
                  v_oidTipoOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 18) THEN
                  v_oidCicloVida.EXTEND;
                  v_oidCicloVida(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 19) THEN
                  v_oidCondicionPromocion.EXTEND;
                  v_oidCondicionPromocion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 20) THEN
                  v_oidFormaPago.EXTEND;
                  v_oidFormaPago(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 21) THEN
                  v_oidGrupoOfertas.EXTEND;
                  v_oidGrupoOfertas(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 22) THEN
                  v_indDigitable.EXTEND;
                  v_indDigitable(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 23) THEN
                  v_indImpresion.EXTEND;
                  v_indImpresion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 24) THEN
                  v_valPosicionPagina.EXTEND;
                  v_valPosicionPagina(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 25) THEN
                  v_valCodigoVenta.EXTEND;
                  v_valCodigoVenta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 26) THEN
                  v_valCentro.EXTEND;
                  v_valCentro(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 27) THEN
                  v_precioUnitario.EXTEND;
                  v_precioUnitario(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 28) THEN
                  v_numPuntoFijo.EXTEND;
                  v_numPuntoFijo(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 29) THEN
                  v_oidVari.EXTEND;
                  v_oidVari(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 30) THEN
                  v_oidProgFide.EXTEND;
                  v_oidProgFide(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 31) THEN
                  v_numOrdenDetalle.EXTEND;
                  v_numOrdenDetalle(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;

                inicio := inicio + longitud;

              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
         END LOOP;
         CLOSE c_interfaz;

       EXCEPTION
         WHEN no_data_found THEN
           EXIT;
       END;
     END LOOP;
   END IF;

   utl_file.fclose(v_handle);

   -- Limpiando la tabla temporal
   DELETE FROM pre_tmp_ofert_detal;

   -- Bulk bind of data in memory table...
   FORALL i IN 1 .. v_oidDetalleOferta.COUNT
     INSERT INTO pre_tmp_ofert_detal
       (
          oid_deta_ofer,
          ofer_oid_ofer,
          prod_cod_prod,
          num_line_ofer,
          val_text_brev,
          num_unid_esti,
          cod_orig,
          val_fact_repe,
          num_posi_rank,
          ind_prod_prin,
          imp_prec_cata,
          imp_prec_posi,
          imp_cost_esta,
          imp_vent_neta_esti,
          num_pagi_cata,
          ocat_oid_cata,
          tofe_oid_tipo_ofer,
          civi_oid_cicl_vida,
          cndp_oid_cond_prom,
          fopa_oid_form_pago,
          gofe_oid_grup_ofer,
          ind_digi,
          ind_impr_gp,
          val_posi_pagi,
          val_codi_vent,
          val_cent,
          val_prec_unit,
          num_punt_fijo,
          vari_oid_vari,
          prfi_oid_prog_fide,
          num_orde_deta
       )
     VALUES
        ( v_oidDetalleOferta(i),
          v_oidOferta(i),
          v_codsap(i),
          v_numLineaOferta(i),
          v_textoBreve(i),
          v_numUnidadesEstimadas(i),
          v_codOrigen(i),
          v_factorRepeticion(i),
          v_numPosicionRanking(i),
          v_indProductoPrincipal(i),
          v_impPrecioCatalogo(i),
          v_impPrecioPosicionamiento(i),
          v_impCosteEstandar(i),
          v_impVentaNetaEstimada(i),
          v_numPaginaCatalogo(i),
          v_oidCatalogo(i),
          v_oidTipoOferta(i),
          v_oidCicloVida(i),
          v_oidCondicionPromocion(i),
          v_oidFormaPago(i),
          v_oidGrupoOfertas(i),
          v_indDigitable(i),
          v_indImpresion(i),
          v_valPosicionPagina(i),
          v_valCodigoVenta(i),
          v_valCentro(i),
          v_precioUnitario(i),
          v_numPuntoFijo(i),
          v_oidVari(i),
          v_oidProgFide(i),
          v_numOrdenDetalle(i)
        );
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_ofert_detal_tempo: '||ls_sqlerrm);
 END int_pr_pre_ofert_detal_tempo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_PROMO
                      en la tabla temporal PRE_TMP_PROMO
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_promo_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
 IS
   CURSOR c_interfaz IS
     SELECT a.pos_camp,
            a.lon_camp,
            a.can_deci,
            a.ide_camp,
            t.sig_tdat
       FROM bas_estru_archi a,
            bas_tipo_dato   t
      WHERE a.tdat_cod_tdat = t.cod_tdat
        AND a.est_esar != 9
        AND a.pais_cod_pais = pscodigopais
        AND a.sist_cod_sist = pscodigosistema
        AND a.inte_cod_inte = pscodigointerfaz
     ORDER BY a.pos_camp ASC;

   TYPE interfazcab IS RECORD(
     posiccampo  bas_estru_archi.pos_camp%TYPE,
     longcampo   bas_estru_archi.lon_camp%TYPE,
     cantdecimal bas_estru_archi.can_deci%TYPE,
     idcampo     bas_estru_archi.ide_camp%TYPE,
     sigla       bas_tipo_dato.sig_tdat%TYPE);

   TYPE interfazcabtab IS TABLE OF interfazcab;

   interfazrecord interfazcabtab;

   TYPE t_oidPromocion                 IS TABLE OF pre_promo.oid_prom%TYPE;
   TYPE t_oidOferta                    IS TABLE OF pre_promo.ofer_oid_ofer%TYPE;
   TYPE t_numCondicion                 IS TABLE OF pre_promo.num_cond%TYPE;
   TYPE t_codFactorCuadre              IS TABLE OF pre_promo.val_fact_cuad%TYPE;
   TYPE t_oidIndicadorCuadrePromocion  IS TABLE OF pre_promo.icpr_oid_indi_cuad_prom%TYPE;

   v_oidPromocion                  t_oidPromocion                 := t_oidPromocion();
   v_oidOferta                     t_oidOferta                    := t_oidOferta();
   v_numCondicion                  t_numCondicion                 := t_numCondicion();
   v_codFactorCuadre               t_codFactorCuadre              := t_codFactorCuadre();
   v_oidIndicadorCuadrePromocion   t_oidIndicadorCuadrePromocion  := t_oidIndicadorCuadrePromocion();

   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   i               BINARY_INTEGER := 0;
   posicion        NUMBER := 0;
   longitud        NUMBER := 0;
   inicio          NUMBER := 0;

 BEGIN

   /* Procedimiento inicial para generar interfaz */
   gen_pkg_inter_archi.gen_pr_inici_inter_lectu( pscodigopais, pscodigosistema, pscodigointerfaz,
                                                 psnombrearchivo, 'TXT', lsdirtempo, lsnombrearchivo,
                                                 v_handle );

   IF utl_file.is_open(v_handle) THEN
     LOOP
       BEGIN
         utl_file.get_line(v_handle, lslinea);
         i      := i + 1;
         inicio := 1;
         IF lslinea IS NULL THEN
           EXIT;
         END IF;

         OPEN c_interfaz;
         LOOP
           FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;
           IF interfazrecord.COUNT > 0 THEN
             FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_oidPromocion.EXTEND;
                  v_oidPromocion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 2) THEN
                  v_oidOferta.EXTEND;
                  v_oidOferta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 3) THEN
                  v_numCondicion.EXTEND;
                  v_numCondicion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 4) THEN
                  v_codFactorCuadre.EXTEND;
                  v_codFactorCuadre(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 5) THEN
                  v_oidIndicadorCuadrePromocion.EXTEND;
                  v_oidIndicadorCuadrePromocion(i) := TRIM(substr(lslinea, inicio, longitud));

                END IF;

                inicio := inicio + longitud;

              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
         END LOOP;
         CLOSE c_interfaz;

       EXCEPTION
         WHEN no_data_found THEN
           EXIT;
       END;
     END LOOP;
   END IF;

   utl_file.fclose(v_handle);

   -- Limpiando la tabla temporal
   DELETE FROM pre_tmp_promo;

   -- Bulk bind of data in memory table...
   FORALL i IN 1 .. v_oidPromocion.COUNT
     INSERT INTO pre_tmp_promo
     (
        oid_prom,
        ofer_oid_ofer,
        num_cond,
        val_fact_cuad,
        icpr_oid_indi_cuad_prom,
        oid_prom_nuev
     )
     VALUES
     (  v_oidPromocion(i),
        v_oidOferta(i),
        v_numCondicion(i),
        v_codFactorCuadre(i),
        v_oidIndicadorCuadrePromocion(i),
        pre_pomo_seq.nextval
      );
   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_promo_tempo: '||ls_sqlerrm);
 END int_pr_pre_promo_tempo;


/***************************************************************************
  Descripcion       : Procedimiento que inserta el archivo PRE_RANGO_PROMO
                      en la tabla temporal PRE_TMP_RANGO_PROMO
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_rango_promo_tempo
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )
  IS
   CURSOR c_interfaz IS
     SELECT a.pos_camp,
            a.lon_camp,
            a.can_deci,
            a.ide_camp,
            t.sig_tdat
       FROM bas_estru_archi a,
            bas_tipo_dato   t
      WHERE a.tdat_cod_tdat = t.cod_tdat
        AND a.est_esar != 9
        AND a.pais_cod_pais = pscodigopais
        AND a.sist_cod_sist = pscodigosistema
        AND a.inte_cod_inte = pscodigointerfaz
     ORDER BY a.pos_camp ASC;

   TYPE interfazcab IS RECORD(
     posiccampo  bas_estru_archi.pos_camp%TYPE,
     longcampo   bas_estru_archi.lon_camp%TYPE,
     cantdecimal bas_estru_archi.can_deci%TYPE,
     idcampo     bas_estru_archi.ide_camp%TYPE,
     sigla       bas_tipo_dato.sig_tdat%TYPE);

   TYPE interfazcabtab IS TABLE OF interfazcab;

   interfazrecord interfazcabtab;

   TYPE t_oidRangoPromocion     IS TABLE OF pre_rango_promo.oid_rang_prom%TYPE;
   TYPE t_oidCatalogo           IS TABLE OF pre_rango_promo.ocat_oid_cata%TYPE;
   TYPE t_oidPromocion          IS TABLE OF pre_rango_promo.pomo_oid_prom%TYPE;
   TYPE t_codTipoRango          IS TABLE OF pre_rango_promo.cod_tipo_rang%TYPE;
   TYPE t_numRangoInterno       IS TABLE OF pre_rango_promo.num_rang_inte%TYPE;
   TYPE t_valDesde              IS TABLE OF pre_rango_promo.val_desd%TYPE;
   TYPE t_valHasta              IS TABLE OF pre_rango_promo.val_hast%TYPE;
   TYPE t_indExclusion          IS TABLE OF pre_rango_promo.ind_excl%TYPE;
   TYPE t_codSAP                IS TABLE OF mae_produ.cod_sap%TYPE;

   v_oidRangoPromocion      t_oidRangoPromocion := t_oidRangoPromocion();
   v_oidCatalogo            t_oidCatalogo       := t_oidCatalogo();
   v_oidPromocion           t_oidPromocion      := t_oidPromocion();
   v_codTipoRango           t_codTipoRango      := t_codTipoRango();
   v_numRangoInterno        t_numRangoInterno   := t_numRangoInterno();
   v_valDesde               t_valDesde          := t_valDesde();
   v_valHasta               t_valHasta          := t_valHasta();
   v_indExclusion           t_indExclusion      := t_indExclusion();
   v_codSAP                 t_codSAP            := t_codSAP();

   lsdirtempo      bas_inter.dir_temp%TYPE;
   v_handle        utl_file.file_type;
   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   i               BINARY_INTEGER := 0;
   posicion        NUMBER := 0;
   longitud        NUMBER := 0;
   inicio          NUMBER := 0;

  BEGIN

   /* Procedimiento inicial para generar interfaz */
   gen_pkg_inter_archi.gen_pr_inici_inter_lectu( pscodigopais, pscodigosistema, pscodigointerfaz,
                                                 psnombrearchivo, 'TXT', lsdirtempo, lsnombrearchivo,
                                                 v_handle );

   IF utl_file.is_open(v_handle) THEN
     LOOP
       BEGIN
         utl_file.get_line(v_handle, lslinea);
         i      := i + 1;
         inicio := 1;
         IF lslinea IS NULL THEN
           EXIT;
         END IF;

         OPEN c_interfaz;
         LOOP
           FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;
           IF interfazrecord.COUNT > 0 THEN
             FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_oidRangoPromocion.EXTEND;
                  v_oidRangoPromocion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 2) THEN
                  v_oidCatalogo.EXTEND;
                  v_oidCatalogo(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 3) THEN
                  v_oidPromocion.EXTEND;
                  v_oidPromocion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 4) THEN
                  v_codTipoRango.EXTEND;
                  v_codTipoRango(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 5) THEN
                  v_numRangoInterno.EXTEND;
                  v_numRangoInterno(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 6) THEN
                  v_valDesde.EXTEND;
                  v_valDesde(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 7) THEN
                  v_valHasta.EXTEND;
                  v_valHasta(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 8) THEN
                  v_indExclusion.EXTEND;
                  v_indExclusion(i) := TRIM(substr(lslinea, inicio, longitud));

                ELSIF (posicion = 9) THEN
                  v_codSAP.EXTEND;
                  v_codSAP(i) := TRIM(substr(lslinea, inicio, longitud));

                END IF;

                inicio := inicio + longitud;

              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
         END LOOP;
         CLOSE c_interfaz;

       EXCEPTION
         WHEN no_data_found THEN
           EXIT;
       END;
     END LOOP;
   END IF;

   utl_file.fclose(v_handle);

   -- Limpiando la tabla temporal
   DELETE FROM pre_tmp_rango_promo;

   -- Bulk bind of data in memory table...
   FORALL i IN 1 .. v_oidRangoPromocion.COUNT
     INSERT INTO pre_tmp_rango_promo
     (
        oid_rang_prom,
        ocat_oid_cata,
        pomo_oid_prom,
        cod_tipo_rang,
        num_rang_inte,
        val_desd,
        val_hast,
        ind_excl,
        cod_sap
     )
     VALUES
     (
        v_oidRangoPromocion(i),
        v_oidCatalogo(i),
        v_oidPromocion(i),
        v_codTipoRango(i),
        v_numRangoInterno(i),
        v_valDesde(i),
        v_valHasta(i),
        v_indExclusion(i),
        v_codSAP(i)
     );
   RETURN;

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_rango_promo_tempo: '||ls_sqlerrm);
 END int_pr_pre_rango_promo_tempo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta los archivos exportados
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_archi_expor
 IS
 BEGIN

   int_pkg_pre.int_pr_pre_inser_ofert_cabec;
   int_pkg_pre.int_pr_pre_inser_grupo_ofert;
   int_pkg_pre.int_pr_pre_inser_ofert_detal;
   int_pkg_pre.int_pr_pre_inser_promo;
   int_pkg_pre.int_pr_pre_inser_rango_promo;
   int_pkg_pre.int_pr_pre_inser_matri_factu;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_inser_archi_expor: '||ls_sqlerrm);
 END int_pr_pre_inser_archi_expor;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla  PRE_OFERT los
                      registros de la tabla PRE_TMP_OFERT
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_ofert_cabec
 IS

   lsoidperic  NUMBER;
   lsoidperi  NUMBER;
   lsoidmatr  NUMBER;
   lstemp  NUMBER;

 BEGIN

  select distinct mfca_oid_cabe into lsoidperic from pre_tmp_ofert;
  select oid_peri into lsoidperi from cra_perio where acce_oid_acce=2003 and marc_oid_marc=2003 and peri_oid_peri=lsoidperic;
  select count(*) into lstemp from pre_matri_factu_cabec where perd_oid_peri=lsoidperi;
  if lstemp=0 then
     select pre_mfca_seq.nextval into lsoidmatr from dual;
     insert into pre_matri_factu_cabec values (lsoidmatr, lsoidperi,0,0,0,0,1,0,0,0,null,null);
  else
      select oid_cabe into lsoidmatr from pre_matri_factu_cabec where perd_oid_peri=lsoidperi;
  end if;

   INSERT INTO pre_ofert
     (    oid_ofer,
          coes_oid_estr,
          num_ofer,
          num_orde,
          num_grup,
          num_grup_cndt,
          num_grup_cond,
          val_cond_g1_cndt,
          val_cond_g2_cndo,
          num_paqu,
          num_prim_posi_rank,
          num_ulti_posi_rank,
          fopa_oid_form_pago,
          sbac_oid_sbac,
          argv_oid_argu_vent,
          acce_oid_acce,
          mfca_oid_cabe,
          ind_codi_vent_gene,
          ind_desp_compl,
          ind_desp_auto,
          ind_matr_fact_gene,
          ind_recu_obli,
          ind_regi_esta_gene,
          ocat_oid_cata
     )
   SELECT p.oid_ofer_nuev,
          p.coes_oid_estr,
          p.num_ofer,
          p.num_orde,
          p.num_grup,
          p.num_grup_cndt,
          p.num_grup_cond,
          p.val_cond_g1_cndt,
          p.val_cond_g2_cndo,
          p.num_paqu,
          p.num_prim_posi_rank,
          p.num_ulti_posi_rank,
          p.fopa_oid_form_pago,
          p.sbac_oid_sbac,
          p.argv_oid_argu_vent,
          p.acce_oid_acce,
          --p.mfca_oid_cabe,
          lsoidmatr,
          1,
          p.ind_desp_compl,
          p.ind_desp_auto,
          1,
          p.ind_recu_obli,
          0,
          p.ocat_oid_cata
     FROM pre_tmp_ofert p;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_inser_ofert_cabec: '||ls_sqlerrm);
 END int_pr_pre_inser_ofert_cabec;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla  PRE_GRUPO_OFERT
                      los registros de la tabla PRE_TMP_GRUPO_OFERT
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_grupo_ofert
 IS
 BEGIN

   INSERT INTO pre_grupo_ofert
     (
        oid_grup_ofer,
        ofer_oid_ofer,
        num_grup,
        cod_fact_cuad,
        cues_oid_ind_cuad_tipo_estr,
        ind_cndt,
        ind_cndo,
        ind_grup
     )
   SELECT  p.oid_grup_ofer_nuev,
           ( SELECT o.oid_ofer_nuev
               FROM pre_tmp_ofert o
              WHERE o.oid_ofer = p.ofer_oid_ofer),
           p.num_grup,
           p.cod_fact_cuad/100,
           p.cues_oid_ind_cuad_tipo_estr,
           p.ind_cndt,
           p.ind_cndo,
           p.ind_grup
     FROM  pre_tmp_grupo_ofert p;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_inser_grupo_ofert: '||ls_sqlerrm);
 END int_pr_pre_inser_grupo_ofert;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla  PRE_TMP_OFERT
                      los registros de la tabla PRE_TMP_OFERT_DETAL
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_ofert_detal
 IS
 BEGIN

   INSERT INTO pre_ofert_detal
   (      oid_deta_ofer,
          ofer_oid_ofer,
          prod_oid_prod,
          num_line_ofer,
          val_text_brev,
          num_unid_esti,
          cod_orig,
          val_fact_repe,
          num_posi_rank,
          ind_prod_prin,
          imp_prec_cata,
          imp_prec_posi,
          imp_cost_esta,
          imp_vent_neta_esti,
          num_pagi_cata,
          ocat_oid_catal,
          tofe_oid_tipo_ofer,
          civi_oid_ciclo_vida,
          cndp_oid_cond_prom,
          fopa_oid_form_pago,
          gofe_oid_grup_ofer,
          ind_digi,
          ind_impr_gp,
          ind_codi_vent_gene,
          ind_matr_fact_gene,
          val_posi_pagi,
          val_codi_vent,
          val_cent,
          precio_unitario,
          num_punt_fijo,
          vari_oid_vari,
          prfi_oid_prog_fide,
          num_orde_deta
  )
  SELECT  pre_ofde_seq.NEXTVAL,
          (SELECT t.oid_ofer_nuev FROM pre_tmp_ofert t WHERE t.oid_ofer =  p.ofer_oid_ofer),
          (SELECT m.oid_prod FROM mae_produ m WHERE m.cod_sap = p.prod_cod_prod),
          p.num_line_ofer,
          p.val_text_brev,
          p.num_unid_esti,
          p.cod_orig,
          p.val_fact_repe,
          p.num_posi_rank,
          p.ind_prod_prin,
          p.imp_prec_cata/100,
          p.imp_prec_posi/100,
          p.imp_cost_esta,
          p.imp_vent_neta_esti,
          p.num_pagi_cata,
          p.ocat_oid_cata,
          p.tofe_oid_tipo_ofer,
          p.civi_oid_cicl_vida,
          p.cndp_oid_cond_prom,
          p.fopa_oid_form_pago,
          (SELECT t.oid_grup_ofer_nuev FROM pre_tmp_grupo_ofert t WHERE t.oid_grup_ofer =  p.gofe_oid_grup_ofer),
          p.ind_digi,
          p.ind_impr_gp,
          1,
          1,
          p.val_posi_pagi,
          p.val_codi_vent,
          p.val_cent,
          p.val_prec_unit/100,
          p.num_punt_fijo,
          p.vari_oid_vari,
          p.prfi_oid_prog_fide,
          p.num_orde_deta
    FROM  pre_tmp_ofert_detal p;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_inser_ofert_detal: '||ls_sqlerrm);
 END int_pr_pre_inser_ofert_detal;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_PROMO
                      los registros de la tabla PRE_TMP_PROMO
  Fecha Creacion    : 08/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_promo
 IS
 BEGIN

   INSERT INTO pre_promo
   (
      oid_prom,
      ofer_oid_ofer,
      num_cond,
      val_fact_cuad,
      icpr_oid_indi_cuad_prom
   )
   SELECT oid_prom_nuev,
         (SELECT t.oid_ofer_nuev FROM pre_tmp_ofert t WHERE t.oid_ofer = p.ofer_oid_ofer ),
         p.num_cond,
         p.val_fact_cuad/100,
         p.icpr_oid_indi_cuad_prom
     FROM pre_tmp_promo p;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_inser_promo: '||ls_sqlerrm);
 END int_pr_pre_inser_promo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_RANGO_PROMO
                      los registros de la tabla PRE_TMP_RANGO_PROMO
  Fecha Creacion    : 09/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_rango_promo
 IS
 BEGIN

   INSERT INTO pre_rango_promo
   (
          oid_rang_prom,
          ocat_oid_cata,
          pomo_oid_prom,
          cod_tipo_rang,
          num_rang_inte,
          val_desd,
          val_hast,
          ind_excl
   )
   SELECT pre_rapr_seq.nextval,
          p.ocat_oid_cata,
          (select oid_prom_nuev from pre_tmp_promo where oid_prom=p.pomo_oid_prom),
          p.cod_tipo_rang,
          p.num_rang_inte,
          decode(p.cod_sap, NULL, p.val_desd,(SELECT oid_prod from mae_produ where cod_sap = p.cod_sap)),
          p.val_hast,
          p.ind_excl
     FROM pre_tmp_rango_promo p;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_inser_rango_promo: '||ls_sqlerrm);
 END int_pr_pre_inser_rango_promo;

/***************************************************************************
  Descripcion       : Procedimiento que inserta en la tabla PRE_MATRO_FACTU
  Fecha Creacion    : 09/04/2010
  Autor             : José Luis Rodríguez
***************************************************************************/
 PROCEDURE int_pr_pre_inser_matri_factu
 IS
 BEGIN

   INSERT INTO pre_matri_factu
   (
        oid_matr_fact,
        cod_esta,
        mfca_oid_cabe,
        ofde_oid_deta_ofer,
        fec_ulti_actu,
        num_punt_fijo,
        vari_oid_vari,
        prfi_oid_prog_fide,
        ind_matr_fact
   )
   SELECT pre_mafa_seq.nextval,
          1,
          (select distinct mfca_oid_cabe from pre_ofert where oid_ofer in (select oid_ofer_nuev from pre_tmp_ofert)),
          o.oid_deta_ofer,
          sysdate,
          o.num_punt_fijo,
          o.vari_oid_vari,
          o.prfi_oid_prog_fide,
          0
     FROM pre_ofert_detal o,
          pre_tmp_ofert t
    WHERE o.ofer_oid_ofer = t.oid_ofer_nuev;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pr_pre_inser_matri_factu : '||ls_sqlerrm);
 END int_pr_pre_inser_matri_factu ;

 /***************************************************************************
  Descripcion       : Validaciones Previas al Procesamiento de la
                      Pantalla Carga Matriz Planit
  Fecha Creacion    : 11/08/2015
  Autor             : Richard Argomedo
***************************************************************************/
PROCEDURE int_pre_carg_matr_plan
  (
    psnumerolote         VARCHAR,
    psvalidacion         VARCHAR,
    psNombres            VARCHAR,
    pscodigoPais         VARCHAR,
    psCodigoPeriodo      VARCHAR,
    psCodigoCatalogo     VARCHAR,
    psEliminarArch       OUT VARCHAR
    )
  IS
     ln_cant_registro NUMBER;
     ln_cant_registro1 NUMBER;
     ln_global_num NUMBER;
     ln_global_var VARCHAR2(100);
     error_generado EXCEPTION;
     ln_variable_aux NUMBER;
     ln_rang_supe NUMBER(18,3);
     ln_rang_infe NUMBER(18,3);
     ln_rang_resul NUMBER(18,3);
     lsArchivo1 VARCHAR2(128);
     lsArchivo2 VARCHAR2(128);
     lsArchivo3 VARCHAR2(128);
     lsArchivo4 VARCHAR2(128);
     lsArchivo5 VARCHAR2(128);
     lsArchivo6 VARCHAR2(128);
     lb_validarError BOOLEAN := FALSE;
     ln_global_error BOOLEAN := TRUE;
     ln_rang_infe_lim NUMBER;
     ln_rang_supe_lim NUMBER;
     ln_fact_repe      NUMBER;
     ln_error_linea VARCHAR2(100);
     ln_oid_periodo    SEG_PERIO_CORPO.oid_peri%TYPE;
     ln_cant_error     NUMBER(18) := 0;
     ln_cod_periodo    SEG_PERIO_CORPO.COD_PERI%TYPE;
     ln_cod_cata       PRE_TMP_OFERT_PLANI.COD_CATA%TYPE;
     ln_oid_cata       PRE_OFERT.OCAT_OID_CATA%TYPE;

     CURSOR arch_ofertas IS
        SELECT NUM_LOTE,COD_CENT, COD_ESTR, COD_CAMP, NUM_OFER,
         COD_SAP, COD_TIPO_OFER, COD_CATA, NUM_PAGI,
         FACT_REPE, VAL_PREC_CATA, VAL_PREC_UNIT,
         VAL_PREC_CONT, VAL_COST_REPO,COD_MONE, NUM_UNID_ESTI,
         VAL_CODI_VENT, IND_PRIN, IND_DIGI, IND_IMPR,
         COD_FORM_VENT, NUM_GRUP_TOTA, NUM_GRUP,
         COD_FACT_CUAD, IND_DESP_AUTO, IND_TOMB, NUM_REG
        FROM PRE_TMP_OFERT_PLANI
        WHERE NUM_LOTE = psnumerolote;

        TYPE archivoOfertas IS RECORD
        (
             numLote     PRE_TMP_OFERT_PLANI.NUM_LOTE%TYPE,
             codCent     PRE_TMP_OFERT_PLANI.COD_CENT%TYPE,
             codEstr     PRE_TMP_OFERT_PLANI.COD_ESTR%TYPE,
             codCamp     PRE_TMP_OFERT_PLANI.COD_CAMP%TYPE,
             numOfer     PRE_TMP_OFERT_PLANI.NUM_OFER%TYPE,
             codSap      PRE_TMP_OFERT_PLANI.COD_SAP%TYPE,
             codTipOfer  PRE_TMP_OFERT_PLANI.COD_TIPO_OFER%TYPE,
             codCata     PRE_TMP_OFERT_PLANI.COD_CATA%TYPE,
             numPagi     PRE_TMP_OFERT_PLANI.NUM_PAGI%TYPE,
             factRepe    PRE_TMP_OFERT_PLANI.FACT_REPE%TYPE,
             valPrecCata PRE_TMP_OFERT_PLANI.VAL_PREC_CATA%TYPE,
             valPrecUni  PRE_TMP_OFERT_PLANI.VAL_PREC_UNIT%TYPE,
             valPrecCont PRE_TMP_OFERT_PLANI.VAL_PREC_CONT%TYPE,
             valCostRepo PRE_TMP_OFERT_PLANI.VAL_COST_REPO%TYPE,
             codMode     PRE_TMP_OFERT_PLANI.COD_MONE%TYPE,
             numUnidEsti PRE_TMP_OFERT_PLANI.NUM_UNID_ESTI%TYPE,
             valCodiVent PRE_TMP_OFERT_PLANI.VAL_CODI_VENT%TYPE,
             indPrin     PRE_TMP_OFERT_PLANI.IND_PRIN%TYPE,
             indDigi     PRE_TMP_OFERT_PLANI.IND_DIGI%TYPE,
             indImpr     PRE_TMP_OFERT_PLANI.IND_IMPR%TYPE,
             codFormVent PRE_TMP_OFERT_PLANI.COD_FORM_VENT%TYPE,
             numGrupTot  PRE_TMP_OFERT_PLANI.NUM_GRUP_TOTA%TYPE,
             numGrup     PRE_TMP_OFERT_PLANI.NUM_GRUP%TYPE,
             codFactCuad PRE_TMP_OFERT_PLANI.COD_FACT_CUAD%TYPE,
             indDespAuto PRE_TMP_OFERT_PLANI.IND_DESP_AUTO%TYPE,
             indTomb     PRE_TMP_OFERT_PLANI.IND_TOMB%TYPE,
             numReg      PRE_TMP_OFERT_PLANI.NUM_REG%TYPE
      );

        TYPE archivoOfertasTab  IS TABLE OF archivoOfertas;
        archOferRecordN archivoOfertasTab;

     CURSOR arch_condiciones IS
        SELECT NUM_LOTE, COD_CENT, COD_ESTR, COD_CAMP, NUM_OFER,
         NUM_COND, TRIM(COD_COND) COD_COND, UNI_COND, VAL_COND, NUM_RANG_COND,
         TIP_RANG_COND, COD_CATA, NUM_REG, NUM_PAGI_DESD,
         NUM_PAGI_HAST, COD_SAP, IND_EXCL
        FROM PRE_TMP_CONDI_PLANI
        WHERE NUM_LOTE = psnumerolote;

        TYPE archivoCondiciones IS RECORD
        (
             numLote     PRE_TMP_CONDI_PLANI.NUM_LOTE%TYPE,
             codcent     PRE_TMP_CONDI_PLANI.COD_CENT%TYPE,
             codEstr     PRE_TMP_CONDI_PLANI.COD_ESTR%TYPE,
             codCamp     PRE_TMP_CONDI_PLANI.COD_CAMP%TYPE,
             numOfer     PRE_TMP_CONDI_PLANI.NUM_OFER%TYPE,
             numCond     PRE_TMP_CONDI_PLANI.NUM_COND%TYPE,
             codCond     PRE_TMP_CONDI_PLANI.COD_COND%TYPE,
             uniCond     PRE_TMP_CONDI_PLANI.UNI_COND%TYPE,
             valCond     PRE_TMP_CONDI_PLANI.VAL_COND%TYPE,
             numRanCond  PRE_TMP_CONDI_PLANI.NUM_RANG_COND%TYPE,
             tipRanCond  PRE_TMP_CONDI_PLANI.TIP_RANG_COND%TYPE,
             codCata     PRE_TMP_CONDI_PLANI.COD_CATA%TYPE,
             numReg      PRE_TMP_CONDI_PLANI.NUM_REG%TYPE,
             numPagiDesd PRE_TMP_CONDI_PLANI.NUM_PAGI_DESD%TYPE,
             numPagiHast PRE_TMP_CONDI_PLANI.NUM_PAGI_HAST%TYPE,
             codSap      PRE_TMP_CONDI_PLANI.COD_SAP%TYPE,
             indExcl     PRE_TMP_CONDI_PLANI.IND_EXCL%TYPE
        );

        TYPE archivoCondicionesTab  IS TABLE OF archivoCondiciones;
        archCondRecordN archivoCondicionesTab;

     CURSOR arch_ofer_niveles IS
        SELECT NUM_LOTE,COD_CENT, NUM_REG, NUM_NIVE, COD_CAMP,
         COD_CATA, NUM_PAGI, TIP_NIVE, TIP_CUAD
        FROM PRE_TMP_OFER_NIVE_PLANI
        WHERE NUM_LOTE = psnumerolote;

        TYPE archivoOferNiveles IS RECORD
        (
             numLote     PRE_TMP_OFER_NIVE_PLANI.NUM_LOTE%TYPE,
             codCent     PRE_TMP_OFER_NIVE_PLANI.COD_CENT%TYPE,
             numReg      PRE_TMP_OFER_NIVE_PLANI.NUM_REG%TYPE,
             numNive     PRE_TMP_OFER_NIVE_PLANI.NUM_NIVE%TYPE,
             codCamp     PRE_TMP_OFER_NIVE_PLANI.COD_CAMP%TYPE,
             codCata     PRE_TMP_OFER_NIVE_PLANI.COD_CATA%TYPE,
             numPagi     PRE_TMP_OFER_NIVE_PLANI.NUM_PAGI%TYPE,
             tipNive     PRE_TMP_OFER_NIVE_PLANI.TIP_NIVE%TYPE,
             tipCuad     PRE_TMP_OFER_NIVE_PLANI.TIP_CUAD%TYPE
        );

        TYPE archivoOferNivelesTab  IS TABLE OF archivoOferNiveles;
        archOferNivRecordN archivoOferNivelesTab;

     CURSOR arch_ran_ofer_niveles IS
        SELECT NUM_LOTE,COD_CENT, NUM_REG, COD_CAMP, NUM_NIVE,
         NUM_RANG, FACT_REPE, VAL_RANG_INFE, VAL_RANG_SUPE,
         VAL_PREC_UNIT
        FROM PRE_TMP_RANG_OFER_NIVE_PLANI
        WHERE NUM_LOTE = psnumerolote;

        TYPE archivoRanOferNiveles IS RECORD
        (
             numLote     PRE_TMP_RANG_OFER_NIVE_PLANI.NUM_LOTE%TYPE,
             codCent     PRE_TMP_RANG_OFER_NIVE_PLANI.COD_CENT%TYPE,
             numReg      PRE_TMP_RANG_OFER_NIVE_PLANI.NUM_REG%TYPE,
             codCamp     PRE_TMP_RANG_OFER_NIVE_PLANI.COD_CAMP%TYPE,
             numNive     PRE_TMP_RANG_OFER_NIVE_PLANI.NUM_NIVE%TYPE,
             numRang     PRE_TMP_RANG_OFER_NIVE_PLANI.NUM_RANG%TYPE,
             factRepe    PRE_TMP_RANG_OFER_NIVE_PLANI.FACT_REPE%TYPE,
             valRangInfe PRE_TMP_RANG_OFER_NIVE_PLANI.VAL_RANG_INFE%TYPE,
             valRangSupe PRE_TMP_RANG_OFER_NIVE_PLANI.VAL_RANG_SUPE%TYPE,
             valRangUnit PRE_TMP_RANG_OFER_NIVE_PLANI.VAL_PREC_UNIT%TYPE
        );

        TYPE archivoRanOferNivelesTab  IS TABLE OF archivoRanOferNiveles;
        archRanOferNivRecordN archivoRanOferNivelesTab;

     CURSOR arch_prod_ofer_niveles IS
        SELECT NUM_LOTE, COD_CENT, NUM_REG, NUM_NIVE,
         COD_CAMP, VAL_CODI_VENT
        FROM PRE_TMP_PROD_OFER_NIVE_PLANI
        WHERE NUM_LOTE = psnumerolote;

        TYPE archivoProdOferNiveles IS RECORD
        (
             numLote     PRE_TMP_PROD_OFER_NIVE_PLANI.NUM_LOTE%TYPE,
             codCent     PRE_TMP_PROD_OFER_NIVE_PLANI.COD_CENT%TYPE,
             numReg      PRE_TMP_PROD_OFER_NIVE_PLANI.NUM_REG%TYPE,
             numNive     PRE_TMP_PROD_OFER_NIVE_PLANI.NUM_NIVE%TYPE,
             codCamp     PRE_TMP_PROD_OFER_NIVE_PLANI.COD_CAMP%TYPE,
             valCodiVent PRE_TMP_PROD_OFER_NIVE_PLANI.VAL_CODI_VENT%TYPE
        );

        TYPE archivoProdOferNivelesTab  IS TABLE OF archivoProdOferNiveles;
        archProdOferNivRecordN archivoProdOferNivelesTab;

     CURSOR arch_grat_ofer_niveles IS
        SELECT NUM_LOTE, COD_CENT, NUM_REG, COD_CAMP, NUM_RANG,
         VAL_CODI_VENT, NUM_CANT, NUM_NIVE
        FROM PRE_TMP_GRATI_OFER_NIVE_PLANI
        WHERE NUM_LOTE = psnumerolote;

        TYPE archivoGratOferNiveles IS RECORD
        (
             numLote     PRE_TMP_GRATI_OFER_NIVE_PLANI.NUM_LOTE%TYPE,
             codCent     PRE_TMP_GRATI_OFER_NIVE_PLANI.COD_CENT%TYPE,
             numReg      PRE_TMP_GRATI_OFER_NIVE_PLANI.NUM_REG%TYPE,
             codCamp     PRE_TMP_GRATI_OFER_NIVE_PLANI.COD_CAMP%TYPE,
             numRang     PRE_TMP_GRATI_OFER_NIVE_PLANI.NUM_RANG%TYPE,
             valCodiVent PRE_TMP_GRATI_OFER_NIVE_PLANI.VAL_CODI_VENT%TYPE,
             numCant     PRE_TMP_GRATI_OFER_NIVE_PLANI.NUM_CANT%TYPE,
             numNive     PRE_TMP_GRATI_OFER_NIVE_PLANI.NUM_NIVE%TYPE
        );

        TYPE archivoGratOferNivelesTab  IS TABLE OF archivoGratOferNiveles;
        archGratOferNivRecordN archivoGratOferNivelesTab;

    CURSOR c_validacionEx2(numLote VARCHAR2) IS
      SELECT DISTINCT num_nive
      FROM PRE_TMP_RANG_OFER_NIVE_PLANI
      WHERE num_lote = numlote
      ORDER BY num_nive;

    CURSOR c_validacionEx2Final(numNivel VARCHAR2, numLote VARCHAR2) IS
      SELECT VAL_RANG_INFE, VAL_RANG_SUPE, FACT_REPE  --
    FROM PRE_TMP_RANG_OFER_NIVE_PLANI
    WHERE num_nive = numNivel
    AND num_lote = numLote
    ORDER BY num_rang;

    TYPE validacionRanOferNivelesExtra IS RECORD
        (
             valRangInfe PRE_TMP_RANG_OFER_NIVE_PLANI.VAL_RANG_INFE%TYPE,
             valRangSupe PRE_TMP_RANG_OFER_NIVE_PLANI.VAL_RANG_SUPE%TYPE,
             factRepe    PRE_TMP_RANG_OFER_NIVE_PLANI.FACT_REPE%TYPE   --
        );

        TYPE validacionRanOferNivelesTab  IS TABLE OF validacionRanOferNivelesExtra;
        validaextraRecordN validacionRanOferNivelesTab;

     CURSOR c_obtieneIdPreOfer(oidPeriodo SEG_PERIO_CORPO.oid_peri%TYPE, oidCata PRE_OFERT.OCAT_OID_CATA%TYPE) IS
       SELECT X.OID_OFER
         FROM PRE_OFERT X, PRE_MATRI_FACTU_CABEC Y
        WHERE Y.OID_CABE = X.MFCA_OID_CABE
          AND X.NUM_LOTE_PLAN IS NOT NULL
          AND Y.PERD_OID_PERI = oidPeriodo
          AND X.OCAT_OID_CATA = oidCata;

       TYPE obtieneIdPreOfer IS RECORD
       (
            oidOfer PRE_OFERT.OID_OFER%TYPE
       );

       TYPE obtieneIdPreOferTab IS TABLE OF obtieneIdPreOfer;
       obtieneIdPreOferRecordN obtieneIdPreOferTab;

       CURSOR c_obtieneIdPreOferDetal(oidOfer PRE_OFERT.OID_OFER%TYPE) IS
        SELECT oid_deta_ofer FROM PRE_OFERT_DETAL
        WHERE OFER_OID_OFER = oidOfer;

       CURSOR c_obtieneIdPrePromo(oidOfer PRE_OFERT.OID_OFER%TYPE) IS
        SELECT oid_prom FROM PRE_PROMO
        WHERE OFER_OID_OFER = oidOfer;

       CURSOR c_obtieneIdNiveOfer(oidPeriodo SEG_PERIO_CORPO.oid_peri%TYPE, oidCata PRE_OFERT.OCAT_OID_CATA%TYPE)IS
         SELECT oid_nive_ofer
         FROM PRE_NIVEL_OFERT
         WHERE NUM_LOTE_PLAN IS NOT NULL
         AND PERD_OID_PERI = oidPeriodo
         AND OCAT_OID_CATA = oidCata;

       CURSOR c_obtieneIdNiveOferRan(idNiveOfer PRE_NIVEL_OFERT.OID_NIVE_OFER%TYPE)IS
         SELECT oid_nive_ofer_rang
         FROM PRE_NIVEL_OFERT_RANGO
          WHERE NIOF_OID_NIVE_OFER = idNiveOfer;

       CURSOR c_obtieneIdNxOfer(oidPeriodo SEG_PERIO_CORPO.oid_peri%TYPE, oidCata PRE_OFERT.OCAT_OID_CATA%TYPE)IS
         SELECT oid_nx_ofer
           FROM PRE_NX_OFERT
          WHERE NUM_LOTE_PLAN IS NOT NULL
            AND PERD_OID_PERI = oidPeriodo
            AND OCAT_OID_CATA = oidCata;

       CURSOR c_obtieneIdNxOferRang(oidNxOfer PRE_NX_OFERT.oid_nx_ofer%TYPE)IS
         SELECT oid_nx_ofer_rang FROM PRE_NX_OFERT_RANGO
         WHERE niof_oid_nx_ofer = oidNxOfer;


        BEGIN
          lsArchivo1 := REGEXP_SUBSTR(psNombres, '[^;]+', 1, 1);
          lsArchivo2 := REGEXP_SUBSTR(psNombres, '[^;]+', 1, 2);
          lsArchivo3 := REGEXP_SUBSTR(psNombres, '[^;]+', 1, 3);
          lsArchivo4 := REGEXP_SUBSTR(psNombres, '[^;]+', 1, 4);
          lsArchivo5 := REGEXP_SUBSTR(psNombres, '[^;]+', 1, 5);
          lsArchivo6 := REGEXP_SUBSTR(psNombres, '[^;]+', 1, 6);

          IF(psvalidacion = '1')THEN
          -- Eliminacion de los registros de error
           DELETE PRE_ERRO_PLANI WHERE num_lote = psnumerolote;
          -- Eliminacion de las tablas temporales

           EXECUTE IMMEDIATE 'truncate table PRE_TMP_OFERT_PLANI';
           EXECUTE IMMEDIATE 'truncate table PRE_TMP_CONDI_PLANI';
           EXECUTE IMMEDIATE 'truncate table PRE_TMP_OFER_NIVE_PLANI';
           EXECUTE IMMEDIATE 'truncate table PRE_TMP_RANG_OFER_NIVE_PLANI';
           EXECUTE IMMEDIATE 'truncate table PRE_TMP_PROD_OFER_NIVE_PLANI';
           EXECUTE IMMEDIATE 'truncate table PRE_TMP_GRATI_OFER_NIVE_PLANI';

           SELECT COUNT(1)
             INTO ln_cant_registro
             FROM PRE_OFERT_PLANI
             WHERE num_lote = psnumerolote
             AND IND_PROC = '1';

           SELECT COUNT(1)
           INTO ln_cant_registro1
           FROM PRE_OFER_NIVE_PLANI
           WHERE num_lote = psnumerolote
           AND ind_proc = '1';

           IF(ln_cant_registro = 0 AND ln_cant_registro1 = 0) THEN

            -- validacion correcta
            DELETE PRE_OFERT_PLANI
            WHERE num_lote = psnumerolote;

            DELETE PRE_CONDI_PLANI
            WHERE num_lote = psnumerolote;

            DELETE PRE_OFER_NIVE_PLANI
            WHERE num_lote = psnumerolote;

            DELETE PRE_RANG_OFER_NIVE_PLANI
            WHERE num_lote = psnumerolote;

            DELETE PRE_PROD_OFER_NIVE_PLANI
            WHERE num_lote = psnumerolote;

            DELETE PRE_GRATI_OFER_NIVE_PLANI
            WHERE num_lote = psnumerolote;

           END IF;

           IF(ln_cant_registro > 0 OR ln_cant_registro1 > 0) THEN
             -- validacion incorrecta
             RAISE error_generado;
           END IF;
        END IF;

        IF(psvalidacion = '2')THEN
        -- Abrimos el cursor
        OPEN arch_ofertas;
        LOOP
        FETCH arch_ofertas BULK COLLECT INTO archOferRecordN LIMIT W_FILAS;
         IF archOferRecordN.COUNT > 0 THEN
           FOR x IN archOferRecordN.FIRST .. archOferRecordN.LAST LOOP
            --  VALIDACIONES ARCHIVO OFERTAS
            -- 1
            lb_validarError := FALSE;
            ln_global_error := TRUE;
            ln_error_linea := archOferRecordN(x).numReg||'cursor1';

            SELECT COUNT(1)
              INTO ln_global_num
               FROM PRE_ESTRA y
               WHERE archOferRecordN(x).codEstr = y.Cod_Estr;

            IF(ln_global_num = 0) THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
              ln_cant_error := ln_cant_error +1;
               END IF;
             IF (lb_validarError) THEN
                INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                        archOferRecordN(x).numReg, 'COD_ESTR','No existe la Estrategia');
                psEliminarArch := 'HV';
             END IF;

             --2
             lb_validarError := FALSE;
             SELECT COUNT(1)
             INTO ln_global_num
               FROM SEG_PERIO_CORPO
             WHERE cod_peri = TRIM(archOferRecordN(x).codCamp);

             IF(ln_global_num > 0)THEN
                ln_cod_periodo := TRIM(archOferRecordN(x).codCamp) ;
                ln_cod_cata :=  archOferRecordN(x).codCata ;
             END IF;

             IF(ln_global_num = 0) THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError)THEN
             INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                        archOferRecordN(x).numReg, 'COD_CAMP','No existe la Campaña');
                 psEliminarArch := 'HV';
             END IF;

             --3
             lb_validarError := FALSE;
             IF(archOferRecordN(x).numOfer IS NOT NULL)THEN
               BEGIN
                ln_global_num := archOferRecordN(x).numOfer/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'NUM_OFER','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --4
             lb_validarError := FALSE;
             SELECT COUNT(1)
             INTO ln_global_num
               FROM MAE_PRODU
               WHERE COD_SAP = archOferRecordN(x).codSap;

             IF(ln_global_num = 0) THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
               END IF;

               IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                archOferRecordN(x).numReg, 'COD_SAP','No existe el Codigo de Producto');
                psEliminarArch := 'HV';
               END IF;
             --5
             lb_validarError := FALSE;
             SELECT COUNT(1)
             INTO ln_global_num
               FROM PRE_TIPO_OFERT
               WHERE COD_TIPO_OFER = archOferRecordN(x).codTipOfer;

             IF(ln_global_num = 0) THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                  archOferRecordN(x).numReg, 'COD_TIPO_OFER','No existe el Tipo de Oferta');
                  psEliminarArch := 'HV';
             END IF;
             --6
             lb_validarError := FALSE;
             SELECT COUNT(1)
             INTO ln_global_num
               FROM PRE_CATAL y
               WHERE Y.COD_CATA = archOferRecordN(x).codCata;

             IF(ln_global_num = 0) THEN
                 lb_validarError := TRUE;
                  ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError) THEN
                 INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                  archOferRecordN(x).numReg, 'COD_CATA','No existe el Catálogo');
                  psEliminarArch := 'HV';
             END IF;
             --7
             lb_validarError := FALSE;
             IF(archOferRecordN(x).numPagi IS NOT NULL)THEN
                BEGIN
                 ln_global_num := archOferRecordN(x).numPagi/1;
                EXCEPTION
                 WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
                END;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'NUM_PAGI','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --8
             lb_validarError := FALSE;
             IF(archOferRecordN(x).factRepe IS NOT NULL)THEN
               BEGIN
                ln_global_num := archOferRecordN(x).factRepe/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                   archOferRecordN(x).numReg, 'FACT_REPE','Solo se aceptan numeros');
                  psEliminarArch := 'HV';
             END IF;
             --9
             lb_validarError := FALSE;
             IF(archOferRecordN(x).valPrecCata IS NOT NULL)THEN
              BEGIN
                ln_global_num := archOferRecordN(x).valPrecCata/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'VAL_PREC_CATA','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --10
             lb_validarError := FALSE;
             IF(archOferRecordN(x).valPrecUni IS NOT NULL)THEN
               BEGIN
                 ln_global_num := archOferRecordN(x).valPrecUni/1;
               EXCEPTION
               WHEN OTHERS THEN
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
               END;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'VAL_PREC_UNIT','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --11
             lb_validarError := FALSE;
             IF(archOferRecordN(x).valPrecCont IS NOT NULL)THEN
               BEGIN
                 ln_global_num := archOferRecordN(x).valPrecCont/1;
               EXCEPTION
               WHEN OTHERS THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
               END;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'VAL_PREC_CONT','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             -- 11.1
             lb_validarError := FALSE;
             IF(archOferRecordN(x).valCostRepo IS NOT NULL)THEN
               BEGIN
                 ln_global_num := archOferRecordN(x).valCostRepo/1;
               EXCEPTION
               WHEN OTHERS THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
               END;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'VAL_COST_REPO','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --
             --12
             lb_validarError := FALSE;
             IF(archOferRecordN(x).valCodiVent IS NOT NULL)THEN
               BEGIN
                 ln_global_num := archOferRecordN(x).valCodiVent/1;
               EXCEPTION
               WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'VAL_CODI_VENT','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --             
             --12.1
             lb_validarError := FALSE;             
             IF length(trim(archOferRecordN(x).valCodiVent)) != 5 THEN                             
                   lb_validarError := TRUE;
                   ln_global_error := FALSE;
                   ln_cant_error := ln_cant_error +1;                        
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'VAL_CODI_VENT','Cuv no es valido');
                 psEliminarArch := 'HV';
             END IF;                                          
             --13
             lb_validarError := FALSE;
             IF(archOferRecordN(x).indPrin IS NOT NULL)THEN
               IF(archOferRecordN(x).indPrin <>'0' AND archOferRecordN(x).indPrin <>'1')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'IND_PRIN','Este campo solo acepta los valores 0 o 1');
                 psEliminarArch := 'HV';
             END IF;
             --14
             lb_validarError := FALSE;
             IF(archOferRecordN(x).indDigi IS NOT NULL)THEN
               IF(archOferRecordN(x).indDigi <>'0' AND archOferRecordN(x).indDigi <>'1')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'IND_DIGI','Este campo solo acepta los valores 0 o 1');
                 psEliminarArch := 'HV';
             END IF;
             --15
             lb_validarError := FALSE;
             IF(archOferRecordN(x).indImpr IS NOT NULL)THEN
               IF(archOferRecordN(x).indImpr <>'0' AND archOferRecordN(x).indImpr <>'1')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'IND_IMPR','Este campo solo acepta los valores 0 o 1');
                 psEliminarArch := 'HV';
             END IF;
             --16
             lb_validarError := FALSE;
             IF(archOferRecordN(x).codEstr = '003' OR archOferRecordN(x).codEstr = '007')THEN
                IF(archOferRecordN(x).numGrupTot IS NOT NULL)THEN
                 BEGIN
                   ln_global_num := archOferRecordN(x).numGrupTot/1;
                 EXCEPTION
                  WHEN OTHERS THEN
                   lb_validarError := TRUE;
                   ln_global_error := FALSE;
                   ln_cant_error := ln_cant_error +1;
                 END;
                ELSE
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
                END IF;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                   archOferRecordN(x).numReg, 'NUM_GRUP_TOTA','Solo se aceptan numeros');
                   psEliminarArch := 'HV';
             END IF;
             --17
             lb_validarError := FALSE;
             IF(archOferRecordN(x).codEstr = '003' OR archOferRecordN(x).codEstr = '007')THEN
                IF(archOferRecordN(x).numGrup IS NOT NULL)THEN
                 BEGIN
                   ln_global_num := archOferRecordN(x).numGrup/1;
                 EXCEPTION
                  WHEN OTHERS THEN
                   lb_validarError := TRUE;
                   ln_global_error := FALSE;
                   ln_cant_error := ln_cant_error +1;
                 END;
                ELSE
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
                END IF;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                   archOferRecordN(x).numReg, 'NUM_GRUP','Solo se aceptan numeros');
                   psEliminarArch := 'HV';
             END IF;
             --18
             lb_validarError := FALSE;
             IF(archOferRecordN(x).codEstr = '003' OR archOferRecordN(x).codEstr = '007')THEN
                IF(archOferRecordN(x).codFactCuad IS NOT NULL)THEN
                 BEGIN
                   ln_global_num := archOferRecordN(x).codFactCuad/1;
                 EXCEPTION
                  WHEN OTHERS THEN
                   lb_validarError := TRUE;
                   ln_global_error := FALSE;
                   ln_cant_error := ln_cant_error +1;
                 END;
                ELSE
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
                END IF;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                   archOferRecordN(x).numReg, 'COD_FACT_CUAD','Solo se aceptan numeros');
                   psEliminarArch := 'HV';
             END IF;
             --19
             lb_validarError := FALSE;
             IF(archOferRecordN(x).indDespAuto IS NOT NULL)THEN
               IF(archOferRecordN(x).indDespAuto <>'0' AND archOferRecordN(x).indDespAuto <>'1')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'IND_DESP_AUTO','Este campo solo acepta los valores 0 o 1');
                 psEliminarArch := 'HV';
             END IF;
             --20
             lb_validarError := FALSE;
             IF(archOferRecordN(x).indTomb IS NOT NULL)THEN
               IF(archOferRecordN(x).indTomb <>'0' AND archOferRecordN(x).indTomb <>'1')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferRecordN(x).numLote, lsArchivo1,
                 archOferRecordN(x).numReg, 'IND_TOMB','Este campo solo acepta los valores 0 o 1');
                 psEliminarArch := 'HV';
             END IF;

            -- INSERT TABLA PRE_OFERT_PLANI
              INSERT INTO PRE_OFERT_PLANI(
              OID_OFER_PLAN,
              NUM_LOTE, COD_CENT, COD_CAMP,
              COD_ESTR, NUM_OFER, COD_SAP,
              COD_TIPO_OFER, COD_CATA, NUM_PAGI,
              FACT_REPE, VAL_PREC_CATA, VAL_PREC_UNIT,
              VAL_PREC_CONT, VAL_COST_REPO, COD_MONE,
              VAL_CODI_VENT, NUM_UNID_ESTI, IND_PRIN,
              IND_IMPR, IND_DIGI, COD_FORM_VENT,
              NUM_GRUP_TOTA, NUM_GRUP, COD_FACT_CUAD,
              IND_DESP_AUTO, IND_TOMB)
              VALUES(
              PRE_OFPL_SEQ.nextval,
              archOferRecordN(x).numLote, archOferRecordN(x).codCent, archOferRecordN(x).codCamp,
              archOferRecordN(x).codEstr, TO_NUMBER(archOferRecordN(x).numOfer,'999999999999999999.999'),
              archOferRecordN(x).codSap, archOferRecordN(x).codTipOfer, archOferRecordN(x).codCata,
              TO_NUMBER(archOferRecordN(x).numPagi,'999999999999999999.999'),
              TO_NUMBER(archOferRecordN(x).factRepe,'999999999999999999.999'),
              TO_NUMBER(archOferRecordN(x).valPrecCata,'999999999999999999.999'),
              TO_NUMBER(archOferRecordN(x).valPrecUni,'999999999999999999.999'),
              TO_NUMBER(archOferRecordN(x).valPrecCont,'999999999999999999.999'),
              TO_NUMBER(archOferRecordN(x).valCostRepo,'999999999999999999.999'),
              archOferRecordN(x).codMode, archOferRecordN(x).valCodiVent,
              TO_NUMBER(archOferRecordN(x).numUnidEsti,'999999999999999999.999'),
              archOferRecordN(x).indPrin, archOferRecordN(x).indImpr,
              archOferRecordN(x).indDigi, archOferRecordN(x).codFormVent,
              TO_NUMBER(archOferRecordN(x).numGrupTot,'999999999999999999.999'),
              TO_NUMBER(archOferRecordN(x).numGrup,'999999999999999999.999'),
              TO_NUMBER(archOferRecordN(x).codFactCuad,'999999999999999999.999'),
              archOferRecordN(x).indDespAuto, archOferRecordN(x).indTomb);
           END LOOP;
         END IF;
         EXIT WHEN arch_ofertas%NOTFOUND;
        END LOOP;

        CLOSE arch_ofertas;

        -- Abrimos el cursor
        OPEN arch_condiciones;
        LOOP
        FETCH arch_condiciones BULK COLLECT INTO archCondRecordN LIMIT W_FILAS;
         IF archCondRecordN.COUNT > 0 THEN
           FOR x IN archCondRecordN.FIRST .. archCondRecordN.LAST LOOP
             -- VALIDACIONES ARCHIVO CONDICIONES
             --1
             lb_validarError := FALSE;
             ln_global_error := TRUE;
             ln_error_linea := archCondRecordN(x).numReg||'cursor2';
             SELECT COUNT(1)
             INTO ln_global_num
               FROM PRE_ESTRA y
               WHERE archCondRecordN(x).codEstr = y.Cod_Estr;

             IF(ln_global_num = 0) THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
               END IF;

               IF(lb_validarError)THEN
                 INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2,
                  archCondRecordN(x).numReg, 'COD_ESTR','No existe la Estrategia');
                  psEliminarArch := 'HV';
               END IF;
             --2
             lb_validarError := FALSE;
             SELECT COUNT(1)
             INTO ln_global_num
             FROM SEG_PERIO_CORPO
             WHERE cod_peri = archCondRecordN(x).codCamp;

             IF(ln_global_num = 0) THEN
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
               VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2,
                archCondRecordN(x).numReg, 'COD_CAMP','No existe la Campaña');
                psEliminarArch := 'HV';
             END IF;
             --3
             IF(archCondRecordN(x).codEstr <> '005'
                AND archCondRecordN(x).codEstr <> '006'
                AND archCondRecordN(x).codEstr <> '007'
                AND archCondRecordN(x).codEstr <> '022') THEN
                  INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg, 'COD_ESTR',
                   'En este archivo solo puede haber estrategias de tipo Promocion ');
                   psEliminarArch := 'HV';
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
             END IF;
             --4
             lb_validarError := FALSE;
             IF(archCondRecordN(x).numOfer IS NOT NULL)THEN
               BEGIN
                ln_global_num := archCondRecordN(x).numOfer/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2,
                 archCondRecordN(x).numReg, 'NUM_OFER','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --5
             lb_validarError := FALSE;
             IF(archCondRecordN(x).numCond IS NOT NULL)THEN
               BEGIN
                ln_global_num := archCondRecordN(x).numCond/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2,
                 archCondRecordN(x).numReg, 'NUM_COND','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --6
             lb_validarError := FALSE;
             IF(archCondRecordN(x).codCond IS NOT NULL)THEN
               IF(archCondRecordN(x).codCond <>'1' AND archCondRecordN(x).codCond <>'2')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2,
                 archCondRecordN(x).numReg, 'COD_COND','Este campo solo acepta los valores 1 o 2');
                 psEliminarArch := 'HV';
             END IF;
             --7
             lb_validarError := FALSE;
             IF(archCondRecordN(x).codCond = '1')THEN
              IF(archCondRecordN(x).uniCond IS NOT NULL)THEN
               BEGIN
                ln_global_num := archCondRecordN(x).uniCond/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
              ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
              END IF;
              --ELSE
              --  lb_validarError := TRUE;
              --  ln_global_error := FALSE;
              --  ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg, 'UNI_COND',
                 'Este campo es obligatorio y numérico para el codigo de condicion 1');
                 psEliminarArch := 'HV';
             END IF;
             --8
             lb_validarError := FALSE;
             IF(archCondRecordN(x).codCond = '2')THEN
              IF(archCondRecordN(x).valCond IS NOT NULL)THEN
               BEGIN
                ln_global_num := archCondRecordN(x).valCond/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
              ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
              END IF;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg, 'VAL_COND',
                 'Este campo es obligatorio y numérico para el codigo de condicion 2');
                 psEliminarArch := 'HV';
             END IF;
             --9
             lb_validarError := FALSE;
             IF(archCondRecordN(x).numRanCond IS NOT NULL)THEN
               BEGIN
                ln_global_num := archCondRecordN(x).numRanCond/1;
               EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                 'NUM_RANG_COND','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --10
             lb_validarError := FALSE;
             IF(archCondRecordN(x).tipRanCond IS NOT NULL)THEN
               IF(UPPER(archCondRecordN(x).tipRanCond) <>'R' AND
                  UPPER(archCondRecordN(x).tipRanCond) <>'P')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                 'TIP_RANG_COND','Este campo solo acepta los valores R o P');
                 psEliminarArch := 'HV';
             END IF;
             --11
             IF(UPPER(archCondRecordN(x).tipRanCond) ='R')THEN
              IF(archCondRecordN(x).codCata IS NULL)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
               VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg, 'COD_CATA',
                'El codigo de catálogo es obligatorio para el tipo de rango R');
                psEliminarArch := 'HV';
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
              END IF;
             END IF;
             --12
             lb_validarError := FALSE;
             SELECT COUNT(1)
             INTO ln_global_num
               FROM PRE_CATAL
               WHERE COD_CATA = archCondRecordN(x).codCata;

             IF(ln_global_num = 0) THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                  'COD_ESTR','No existe el Catálogo');
                  psEliminarArch := 'HV';
             END IF;
             --13
             IF(UPPER(archCondRecordN(x).tipRanCond) ='R')THEN
              IF(archCondRecordN(x).numPagiDesd IS NULL)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                'NUM_PAGI_DESD', 'Este campo es obligatorio para el tipo de rango R');
                psEliminarArch := 'HV';
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
              END IF;
             END IF;
             --14
             IF(archCondRecordN(x).numPagiDesd IS NOT NULL)THEN
               BEGIN
                ln_global_num := archCondRecordN(x).numPagiDesd/1;
               EXCEPTION
                WHEN OTHERS THEN
                  INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                   'NUM_PAGI_DESD','Este campo solo acepta valores numéricos');
                   psEliminarArch := 'HV';
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
              END IF;
              --15
              IF(UPPER(archCondRecordN(x).tipRanCond) ='R')THEN
                IF(archCondRecordN(x).numPagiHast IS NULL)THEN
                 INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg, 'NUM_PAGI_HAST',
                  'Este campo es obligatorio para el tipo de rango R');
                  psEliminarArch := 'HV';
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
                END IF;
               END IF;
               --16
               IF(archCondRecordN(x).numPagiHast IS NOT NULL)THEN
               BEGIN
                ln_global_num := archCondRecordN(x).numPagiHast/1;
               EXCEPTION
                WHEN OTHERS THEN
                  INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                   'NUM_PAGI_HAST','Este campo solo acepta valores numéricos');
                   psEliminarArch := 'HV';
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
              END IF;
              --17
              IF(UPPER(archCondRecordN(x).tipRanCond) ='P')THEN
                IF(archCondRecordN(x).codSap IS NULL)THEN
                 INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg, 'NUM_PAGI_HAST',
                  'Este campo es obligatorio para el tipo de rango P');
                  psEliminarArch := 'HV';
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
                END IF;
              END IF;
              --18
              IF(archCondRecordN(x).codSap IS NOT NULL)THEN
              lb_validarError := FALSE;
              SELECT COUNT(1)
              INTO ln_global_num
              FROM MAE_PRODU
              WHERE COD_SAP = archCondRecordN(x).codSap;

              IF(ln_global_num = 0) THEN
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END IF;

              IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                  'COD_SAP','No existe el Producto');
                  psEliminarArch := 'HV';
              END IF;
              END IF;
              --19
              lb_validarError := FALSE;
              IF(archCondRecordN(x).indExcl IS NOT NULL)THEN
               IF(archCondRecordN(x).indExcl <>'0' AND archCondRecordN(x).indExcl <>'1')THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archCondRecordN(x).numLote, lsArchivo2, archCondRecordN(x).numReg,
                 'IND_EXCL','Este campo solo acepta los valores 0 o 1');
                 psEliminarArch := 'HV';
             END IF;

             -- INSERT TABLA PRE_CONDI_PLANI
               INSERT INTO PRE_CONDI_PLANI(
                OID_COND_PLAN,
                NUM_LOTE,
                COD_CENT,
                COD_CAMP,
                COD_ESTR,
                NUM_OFER,
                NUM_COND,
                COD_COND,
                UNI_COND,
                VAL_COND,
                NUM_RANG_COND,
                TIP_RANG_COND,
                COD_CATA,
                NUM_PAGI_DESD,
                NUM_PAGI_HAST,
                IND_EXCL,
                COD_SAP)
               VALUES(
               PRE_COPL_SEQ.nextval,
               archCondRecordN(x).numLote,
               archCondRecordN(x).codcent,
               archCondRecordN(x).codCamp,
               archCondRecordN(x).codEstr,
               TO_NUMBER(archCondRecordN(x).numOfer,'999999999999.99'),
               TO_NUMBER(archCondRecordN(x).numCond,'999999999999.99'),
               archCondRecordN(x).codCond,
               TO_NUMBER(archCondRecordN(x).uniCond,'999999999999.99'),
               TO_NUMBER(archCondRecordN(x).valCond,'999999999999.99'),
               TO_NUMBER(archCondRecordN(x).numRanCond,'999999999999.99'),
               archCondRecordN(x).tipRanCond,
               archCondRecordN(x).codCata,
               TO_NUMBER(archCondRecordN(x).numPagiDesd,'999999999999.99'),
               TO_NUMBER(archCondRecordN(x).numPagiHast,'999999999999.99'),
               archCondRecordN(x).indExcl,
               archCondRecordN(x).codSap
               );
           END LOOP;
             END IF;
         EXIT WHEN arch_condiciones%NOTFOUND;
        END LOOP;
        CLOSE arch_condiciones;

        -- Abrimos el cursor
        OPEN arch_ofer_niveles;
        LOOP
        FETCH arch_ofer_niveles BULK COLLECT INTO archOferNivRecordN LIMIT W_FILAS;
         IF archOferNivRecordN.COUNT > 0 THEN
           FOR x IN archOferNivRecordN.FIRST .. archOferNivRecordN.LAST LOOP
            -- VALIDACIONES ARCHIVO OFERTAS NIVELES
            --1
            lb_validarError := FALSE;
            ln_global_error := TRUE;
            ln_error_linea := archOferNivRecordN(x).numReg||'cursor3';

            IF(archOferNivRecordN(x).numNive IS NOT NULL)THEN
              BEGIN
                ln_global_num := archOferNivRecordN(x).numNive/1;
              EXCEPTION
                WHEN OTHERS THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
              END;
            ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
            END IF;
            IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferNivRecordN(x).numLote, lsArchivo3,
                 archOferNivRecordN(x).numReg, 'NUM_NIVE','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
            END IF;
            --2
            lb_validarError := FALSE;
            SELECT COUNT(1)
            INTO ln_global_num
             FROM SEG_PERIO_CORPO
             WHERE cod_peri = archOferNivRecordN(x).codCamp;

           IF(ln_global_num = 0) THEN
               lb_validarError := TRUE;
               ln_global_error := FALSE;
             ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archOferNivRecordN(x).numLote, lsArchivo3,
                      archOferNivRecordN(x).numReg, 'COD_CAMP','No existe la Campaña');
                      psEliminarArch := 'HV';
             END IF;
             --3
             IF(archOferNivRecordN(x).codCata IS NOT NULL)THEN
             lb_validarError := FALSE;
             SELECT COUNT(1)
             INTO ln_global_num
               FROM PRE_CATAL y
               WHERE Y.COD_CATA = archOferNivRecordN(x).codCata;

             IF(ln_global_num = 0) THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archOferNivRecordN(x).numLote, lsArchivo3,
                  archOferNivRecordN(x).numReg, 'COD_CATA','No existe el Catálogo');
                  psEliminarArch := 'HV';
             END IF;
             END IF;
             --4
             lb_validarError := FALSE;
             IF(archOferNivRecordN(x).numPagi IS NOT NULL)THEN
               BEGIN
                ln_global_num := archOferNivRecordN(x).numPagi/1;
               EXCEPTION
                WHEN OTHERS THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
               END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferNivRecordN(x).numLote, lsArchivo3, archOferNivRecordN(x).numReg,
                 'NUM_PAGI','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --5
             lb_validarError := FALSE;
             IF(archOferNivRecordN(x).tipNive IS NOT NULL)THEN
               IF(archOferNivRecordN(x).tipNive <>'1' AND archOferNivRecordN(x).tipNive <>'2')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferNivRecordN(x).numLote, lsArchivo3, archOferNivRecordN(x).numReg,
                 'TIP_NIVE','Este campo solo acepta los valores 1 o 2');
                 psEliminarArch := 'HV';
             END IF;
             --6
             lb_validarError := FALSE;
             IF(archOferNivRecordN(x).tipCuad IS NOT NULL)THEN
               IF(archOferNivRecordN(x).tipCuad <>'1' AND archOferNivRecordN(x).tipCuad <>'2')THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END IF;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archOferNivRecordN(x).numLote, lsArchivo3, archOferNivRecordN(x).numReg,
                 'TIP_CUAD','Este campo solo acepta los valores 1 o 2');
                 psEliminarArch := 'HV';
             END IF;

             -- INSERT TABLA PRE_OFER_NIVE_PLANI
               INSERT INTO PRE_OFER_NIVE_PLANI(
                OID_OFER_NIVE_PLAN,
                NUM_LOTE,
                COD_CENT,
                COD_CAMP,
                NUM_NIVE,
                COD_CATA,
                NUM_PAGI,
                TIP_NIVE,
                TIP_CUAD
               )
               VALUES(
               PRE_NIPL_SEQ.nextval,
               archOferNivRecordN(x).numLote,
               archOferNivRecordN(x).codCent,
               archOferNivRecordN(x).codCamp,
               TO_NUMBER(archOferNivRecordN(x).numNive,'999999999999.99'),
               archOferNivRecordN(x).codCata,
               TO_NUMBER(archOferNivRecordN(x).numPagi,'999999999999.99'),
               archOferNivRecordN(x).tipNive,
               archOferNivRecordN(x).tipCuad
               );
           END LOOP;
         END IF;
         EXIT WHEN arch_ofer_niveles%NOTFOUND;
        END LOOP;
        CLOSE arch_ofer_niveles;

         -- Abrimos el cursor
        OPEN arch_ran_ofer_niveles;
        LOOP
        FETCH arch_ran_ofer_niveles BULK COLLECT INTO archRanOferNivRecordN LIMIT W_FILAS;
         IF archRanOferNivRecordN.COUNT > 0 THEN
           FOR x IN archRanOferNivRecordN.FIRST .. archRanOferNivRecordN.LAST LOOP
             -- VALIDACIONES ARCHIVO RANGOS OFERTAS NIVELES
             --1
             lb_validarError := FALSE;
             ln_global_error := TRUE;
             ln_error_linea := archRanOferNivRecordN(x).numReg||'cursor4';
             SELECT COUNT(1)
             INTO ln_global_num
               FROM SEG_PERIO_CORPO
               WHERE cod_peri = archRanOferNivRecordN(x).codCamp;

             IF(ln_global_num = 0) THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archRanOferNivRecordN(x).numLote, lsArchivo4,
                        archRanOferNivRecordN(x).numReg, 'COD_CAMP','No existe la Campaña');
                        psEliminarArch := 'HV';
             END IF;
             --2
             lb_validarError := FALSE;
             IF(archRanOferNivRecordN(x).numNive IS NOT NULL)THEN
              BEGIN
                ln_global_num := archRanOferNivRecordN(x).numNive/1;
              EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
            ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
            END IF;
            IF(lb_validarError)THEN
             INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archRanOferNivRecordN(x).numLote, lsArchivo4,
                 archRanOferNivRecordN(x).numReg, 'NUM_NIVE','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
            END IF;
            --3
            lb_validarError := FALSE;
            IF(archRanOferNivRecordN(x).numRang IS NOT NULL)THEN
              BEGIN
                ln_global_num := archRanOferNivRecordN(x).numRang/1;
              EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
            ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
            END IF;
            IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archRanOferNivRecordN(x).numLote, lsArchivo4,
                 archRanOferNivRecordN(x).numReg, 'NUM_RANG','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
            END IF;
            --4
            lb_validarError := FALSE;
            IF(archRanOferNivRecordN(x).factRepe IS NOT NULL)THEN
              BEGIN
                ln_global_num := archRanOferNivRecordN(x).factRepe/1;
              EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
            END IF;
            IF(lb_validarError)THEN
             INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archRanOferNivRecordN(x).numLote, lsArchivo4,
                 archRanOferNivRecordN(x).numReg, 'FACT_REPE','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
            END IF;
            --5
            IF(archRanOferNivRecordN(x).valRangInfe IS NOT NULL)THEN
              BEGIN
                ln_global_num := archRanOferNivRecordN(x).valRangInfe/1;
              EXCEPTION
                WHEN OTHERS THEN
                  INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archRanOferNivRecordN(x).numLote, lsArchivo4, archRanOferNivRecordN(x).numReg,
                   'VAL_RANG_INFE','Este campo solo acepta valores numéricos');
                   psEliminarArch := 'HV';
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
            END IF;
            --6
            IF(archRanOferNivRecordN(x).valRangSupe IS NOT NULL)THEN
              BEGIN
                ln_global_num := archRanOferNivRecordN(x).valRangSupe/1;
              EXCEPTION
                WHEN OTHERS THEN
                  INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archRanOferNivRecordN(x).numLote, lsArchivo4, archRanOferNivRecordN(x).numReg,
                   'VAL_RANG_SUPE','Este campo solo acepta valores numéricos');
                   psEliminarArch := 'HV';
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
            END IF;
            --7
            IF(archRanOferNivRecordN(x).valRangUnit IS NOT NULL)THEN
              BEGIN
                ln_global_num := archRanOferNivRecordN(x).valRangUnit/1;
              EXCEPTION
                WHEN OTHERS THEN
                  INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archRanOferNivRecordN(x).numLote, lsArchivo4, archRanOferNivRecordN(x).numReg,
                   'VAL_PREC_UNIT','Este campo solo acepta valores numéricos');
                   psEliminarArch := 'HV';
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
              END;
            END IF;

            -- INSERT TABLA PRE_RANG_OFER_NIVE_PLANI
              INSERT INTO PRE_RANG_OFER_NIVE_PLANI(
                OID_RANG_OFER_NIVE_PLAN,
                NUM_LOTE,
                COD_CENT,
                COD_CAMP,
                NUM_NIVE,
                NUM_RANG,
                FACT_REPE,
                VAL_RANG_INFE,
                VAL_RANG_SUPE,
                VAL_PREC_UNIT
              )
              VALUES(
              PRE_RONP_SEQ.nextval,
              archRanOferNivRecordN(x).numLote,
              archRanOferNivRecordN(x).codCent,
              archRanOferNivRecordN(x).codCamp,
              TO_NUMBER(archRanOferNivRecordN(x).numNive,'999999999999.99'),
              TO_NUMBER(archRanOferNivRecordN(x).numRang,'999999999999.99'),
              TO_NUMBER(archRanOferNivRecordN(x).factRepe,'999999999999.99'),
              TO_NUMBER(archRanOferNivRecordN(x).valRangInfe,'999999999999.99'),
              TO_NUMBER(archRanOferNivRecordN(x).valRangSupe,'999999999999.99'),
              TO_NUMBER(archRanOferNivRecordN(x).valRangUnit,'999999999999.99')
              );
             END LOOP;
         END IF;
         EXIT WHEN arch_ran_ofer_niveles%NOTFOUND;
        END LOOP;
        CLOSE arch_ran_ofer_niveles;

        -- OTRAS VALIDACIONES
        -- 2
        FOR cursor_validacionEx2 IN c_validacionEx2(psnumerolote) LOOP
               SELECT tip_cuad
               INTO ln_global_var
               FROM PRE_OFER_NIVE_PLANI
               WHERE num_nive =  cursor_validacionEx2.num_nive
               AND num_lote = psnumerolote
               AND rownum = 1;

              OPEN c_validacionEx2Final(cursor_validacionEx2.num_nive , psnumerolote);
              LOOP
              FETCH c_validacionEx2Final BULK COLLECT INTO validaextraRecordN LIMIT W_FILAS;
               IF validaextraRecordN.COUNT > 0 AND validaextraRecordN.COUNT <>1 THEN
                 FOR x IN validaextraRecordN.FIRST .. validaextraRecordN.LAST LOOP
                   ln_rang_infe_lim := TO_NUMBER(validaextraRecordN(x).valRangInfe, '999999999999999999.999');
                   ln_rang_supe_lim := TO_NUMBER(validaextraRecordN(x).valRangSupe, '999999999999999999.999');
                   ln_fact_repe  := validaextraRecordN(x).factRepe ;
                  IF ln_fact_repe IS NULL THEN
                   IF(ln_rang_infe_lim < ln_rang_supe_lim)THEN
                    IF(validaextraRecordN.COUNT <> x)THEN
                      BEGIN
                      SELECT NUM_DECI
                      INTO ln_global_num
                      FROM SEG_MONED x,
                      SEG_PAIS y
                      WHERE x.Oid_Mone = y.Mone_Oid_Mone
                      AND y.Cod_Pais = pscodigoPais;
                      EXCEPTION
                        WHEN no_data_found THEN
                             ln_global_num := 0;
                      END;
                      IF(ln_global_var = '1')THEN
                       --ln_rang_infe := round(TO_NUMBER(validaextraRecordN(x+1).valRangInfe, '999999999999999999.999'),ln_global_num)/1000;
                       --ln_rang_supe := round(TO_NUMBER(validaextraRecordN(x).valRangSupe, '999999999999999999.999'),ln_global_num)/1000;
                       ln_rang_resul := 1;
                      ELSIF(ln_global_var = '2')THEN
                        --ln_rang_infe := round(TO_NUMBER(validaextraRecordN(x+1).valRangInfe, '999999999999999999.999'),ln_global_num);
                        --ln_rang_supe := round(TO_NUMBER(validaextraRecordN(x).valRangSupe, '999999999999999999.999'),ln_global_num);
                        ln_rang_resul := POWER(10,ln_global_num);
                      END IF;
                      --ln_rang_resul := POWER(10,ln_global_num);

                      IF(ln_rang_infe-ln_rang_supe <> 1/ln_rang_resul)THEN
                        INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                        VALUES(PRE_ERPL_SEQ.nextval, psnumerolote, lsArchivo4, NULL, 'NUM_NIVE',
                          'Para el Nivel '|| cursor_validacionEx2.num_nive ||' existen huecos en los rangos');
                          psEliminarArch := 'HV';
                          ln_cant_error := ln_cant_error +1;
                        EXIT;
                      END IF;
                     END IF;
                   ELSE
                     INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                     VALUES(PRE_ERPL_SEQ.nextval, psnumerolote, lsArchivo4, NULL, 'VAL_RANG_INFE',
                        'Rango Inferior debe ser menor al Rango Superior');
                        psEliminarArch := 'HV';
                     ln_cant_error := ln_cant_error +1;
                   END IF;
                  END IF;
                 END LOOP;
               END IF;
               EXIT WHEN c_validacionEx2Final%NOTFOUND;
              END LOOP;
              CLOSE c_validacionEx2Final;
            END LOOP;

         -- Abrimos el cursor
        OPEN arch_prod_ofer_niveles;
        LOOP
        FETCH arch_prod_ofer_niveles BULK COLLECT INTO archProdOferNivRecordN LIMIT W_FILAS;
         IF archProdOferNivRecordN.COUNT > 0 THEN
           FOR x IN archProdOferNivRecordN.FIRST .. archProdOferNivRecordN.LAST LOOP
             -- VALIDACIONES ARCHIVO PRODUCTOS OFERTAS NIVELES
             --1
             lb_validarError := FALSE;
             ln_global_error := TRUE;
             ln_error_linea := archProdOferNivRecordN(x).numReg||'cursor5';
             SELECT COUNT(1)
             INTO ln_global_num
               FROM SEG_PERIO_CORPO
               WHERE cod_peri = archProdOferNivRecordN(x).codCamp;

             IF(ln_global_num = 0) THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archProdOferNivRecordN(x).numLote, lsArchivo5,
                        archProdOferNivRecordN(x).numReg, 'COD_CAMP','No existe la Campaña');
                        psEliminarArch := 'HV';
             END IF;
             --2
             lb_validarError := FALSE;
             IF(archProdOferNivRecordN(x).numNive IS NOT NULL)THEN
              BEGIN
                ln_global_num := archProdOferNivRecordN(x).numNive/1;
              EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archProdOferNivRecordN(x).numLote, lsArchivo5,
                 archProdOferNivRecordN(x).numReg, 'NUM_NIVE','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --3
             lb_validarError := FALSE;
             IF(archProdOferNivRecordN(x).valCodiVent IS NOT NULL)THEN 
               BEGIN
                 ln_global_num := archProdOferNivRecordN(x).valCodiVent/1;
               EXCEPTION
               WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
               END;
             ELSE
               lb_validarError := TRUE;
               ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archProdOferNivRecordN(x).numLote, lsArchivo5,
                 archProdOferNivRecordN(x).numReg, 'VAL_CODI_VENT','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --             
             --4
             lb_validarError := FALSE;             
             IF length(trim(archProdOferNivRecordN(x).valCodiVent)) != 5 THEN                             
                   lb_validarError := TRUE;
                   ln_global_error := FALSE;
                   ln_cant_error := ln_cant_error +1;                        
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archProdOferNivRecordN(x).numLote, lsArchivo5,
                 archProdOferNivRecordN(x).numReg, 'VAL_CODI_VENT','Cuv no es valido');
                 psEliminarArch := 'HV';
             END IF;
                      
             --5
             --6
             --7
             --8
             lb_validarError := FALSE;
             IF(archProdOferNivRecordN(x).valCodiVent IS NOT NULL)THEN
              BEGIN
                ln_global_num := archProdOferNivRecordN(x).valCodiVent/1;
              EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archProdOferNivRecordN(x).numLote, lsArchivo5,
                 archProdOferNivRecordN(x).numReg, 'VAL_CODI_VENT','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;

             -- INSERT TABLA PRE_PROD_OFER_NIVE_PLANI
               INSERT INTO PRE_PROD_OFER_NIVE_PLANI(
                OID_PROD_OFER_NIVE_PLAN,
                NUM_LOTE,
                COD_CENT,
                COD_CAMP,
                NUM_NIVE,
                VAL_CODI_VENT
               )
               VALUES(
               PRE_PONP_SEQ.nextval,
               archProdOferNivRecordN(x).numLote,
               archProdOferNivRecordN(x).codCent,
               archProdOferNivRecordN(x).codCamp,
               TO_NUMBER(archProdOferNivRecordN(x).numNive,'999999999999.99'),
               archProdOferNivRecordN(x).valCodiVent
               );
           END LOOP;
         END IF;
         EXIT WHEN arch_prod_ofer_niveles%NOTFOUND;
        END LOOP;
        CLOSE arch_prod_ofer_niveles;

         -- Abrimos el cursor
        OPEN arch_grat_ofer_niveles;
        LOOP
        FETCH arch_grat_ofer_niveles BULK COLLECT INTO archGratOferNivRecordN LIMIT W_FILAS;
         IF archGratOferNivRecordN.COUNT > 0 THEN
           FOR x IN archGratOferNivRecordN.FIRST .. archGratOferNivRecordN.LAST LOOP
             -- VALIDACIONES ARCHIVO GRATIS OFERTAS NIVELES
             --1 y 3
             lb_validarError := FALSE;
             ln_global_error := TRUE;
             ln_error_linea := archGratOferNivRecordN(x).numReg||'cursor6';
             SELECT COUNT(1)
             INTO ln_global_num
               FROM SEG_PERIO_CORPO
               WHERE cod_peri = archGratOferNivRecordN(x).codCamp;

             IF(ln_global_num = 0) THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
               ln_cant_error := ln_cant_error +1;
               END IF;

             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                 VALUES(PRE_ERPL_SEQ.nextval, archGratOferNivRecordN(x).numLote,  lsArchivo6,
                        archGratOferNivRecordN(x).numReg, 'COD_CAMP','No existe la Campaña');
                        psEliminarArch := 'HV';
             END IF;

             --2
             lb_validarError := FALSE;
             IF(archGratOferNivRecordN(x).numNive IS NOT NULL)THEN
              BEGIN
                ln_global_num := archGratOferNivRecordN(x).numNive/1;
              EXCEPTION
                WHEN OTHERS THEN
                 lb_validarError := TRUE;
                 ln_global_error := FALSE;
                 ln_cant_error := ln_cant_error +1;
              END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;

             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archGratOferNivRecordN(x).numLote,  lsArchivo6,
                 archGratOferNivRecordN(x).numReg, 'NUM_NIVE','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --4
             lb_validarError := FALSE;
             IF(archGratOferNivRecordN(x).numRang IS NOT NULL)THEN
              BEGIN
                ln_global_num := archGratOferNivRecordN(x).numRang/1;
              EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archGratOferNivRecordN(x).numLote,  lsArchivo6,
                 archGratOferNivRecordN(x).numReg, 'NUM_RANG','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --5
             lb_validarError := FALSE;
             IF(archGratOferNivRecordN(x).valCodiVent IS NOT NULL)THEN
              BEGIN
                ln_global_num := archGratOferNivRecordN(x).valCodiVent/1;
              EXCEPTION
                WHEN OTHERS THEN
                  lb_validarError := TRUE;
                  ln_global_error := FALSE;
                  ln_cant_error := ln_cant_error +1;
              END;
             ELSE
                lb_validarError := TRUE;
                ln_global_error := FALSE;
                ln_cant_error := ln_cant_error +1;
             END IF;
             IF(lb_validarError)THEN
               INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval,  archGratOferNivRecordN(x).numLote,  lsArchivo6,
                 archGratOferNivRecordN(x).numReg, 'VAL_CODI_VENT','Solo se aceptan numeros');
                 psEliminarArch := 'HV';
             END IF;
             --6
             IF(archGratOferNivRecordN(x).numCant IS NOT NULL)THEN
              BEGIN
                ln_global_num := archGratOferNivRecordN(x).numCant/1;
              EXCEPTION
                WHEN OTHERS THEN
                  INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                  VALUES(PRE_ERPL_SEQ.nextval, archGratOferNivRecordN(x).numLote,  lsArchivo6,
                   archGratOferNivRecordN(x).numReg, 'NUM_CANT','Este campo solo acepta valores numéricos');
                   psEliminarArch := 'HV';
                  ln_global_error := FALSE;
              END;
             END IF;
             --7
              lb_validarError := FALSE;             
             IF length(trim(archGratOferNivRecordN(x).valCodiVent)) != 5 THEN                             
                   lb_validarError := TRUE;
                   ln_global_error := FALSE;
                   ln_cant_error := ln_cant_error +1;                        
             END IF;
             IF(lb_validarError)THEN
              INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, num_regi, nom_camp, Obs_Erro)
                VALUES(PRE_ERPL_SEQ.nextval, archGratOferNivRecordN(x).numLote, lsArchivo6,
                 archGratOferNivRecordN(x).numReg, 'VAL_CODI_VENT','Cuv no es valido');
                 psEliminarArch := 'HV';
             END IF;
             -- INSERT TABLA PRE_GRATI_OFER_NIVE_PLANI
               INSERT INTO PRE_GRATI_OFER_NIVE_PLANI(
                OID_PROD_OFER_NIVE_PLAN,
                NUM_LOTE,
                COD_CENT,
                COD_CAMP,
                NUM_NIVE,
                NUM_RANG ,
                VAL_CODI_VENT,
                NUM_CANT
               )
               VALUES(
                PRE_GONP_SEQ.nextval,
                archGratOferNivRecordN(x).numLote,
                archGratOferNivRecordN(x).codCent,
                archGratOferNivRecordN(x).codCamp,
                TO_NUMBER(archGratOferNivRecordN(x).numNive,'999999999999.99'),
                TO_NUMBER(archGratOferNivRecordN(x).numRang,'999999999999.99'),
                archGratOferNivRecordN(x).valCodiVent,
                TO_NUMBER(archGratOferNivRecordN(x).numCant,'999999999999.99')
               );
           END LOOP;
         END IF;
         EXIT WHEN arch_grat_ofer_niveles%NOTFOUND;
        END LOOP;
        CLOSE arch_grat_ofer_niveles;

        ---MCH
       IF(ln_cant_error = 0) THEN
             IF psCodigoPeriodo IS NULL AND psCodigoCatalogo IS NULL THEN
                ln_oid_periodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(ln_cod_periodo);
                select OID_CATA into ln_oid_cata from pre_catal c
                 where ln_cod_cata  = c.cod_cata;
             ELSIF psCodigoPeriodo IS NOT NULL AND psCodigoCatalogo IS NOT NULL THEN
                ln_oid_periodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);
                select OID_CATA into ln_oid_cata from pre_catal c
                 where psCodigoCatalogo  = c.cod_cata;
             END IF;

             OPEN c_obtieneIdPreOfer(ln_oid_periodo, ln_oid_cata);
             LOOP
             FETCH c_obtieneIdPreOfer BULK COLLECT INTO obtieneIdPreOferRecordN LIMIT W_FILAS;
              IF obtieneIdPreOferRecordN.COUNT > 0 THEN
                 FOR x IN obtieneIdPreOferRecordN.FIRST .. obtieneIdPreOferRecordN.LAST LOOP
                    FOR cursor_obtieneIdPreOferDetal IN c_obtieneIdPreOferDetal(obtieneIdPreOferRecordN(x).oidOfer) LOOP
                        --delete tabla PRE_MATRI_FACTU
                        DELETE PRE_MATRI_FACTU
                        WHERE OFDE_OID_DETA_OFER = cursor_obtieneIdPreOferDetal.oid_deta_ofer;
                    END LOOP;

                    FOR cursor_obtieneIdPrePromo IN c_obtieneIdPrePromo(obtieneIdPreOferRecordN(x).oidOfer) LOOP
                        --delete tabla PRE_RANGO_PROMO
                        DELETE PRE_RANGO_PROMO
                       WHERE POMO_OID_PROM = cursor_obtieneIdPrePromo.oid_prom;
                    END LOOP;

                    --delete tabla PRE_PROMO
                    DELETE PRE_PROMO
                     WHERE OFER_OID_OFER = obtieneIdPreOferRecordN(x).oidOfer;

                 END LOOP;
              END IF;
              EXIT WHEN c_obtieneIdPreOfer%NOTFOUND;
             END LOOP;
             CLOSE c_obtieneIdPreOfer;

             FOR cursor_obtieneIdNiveOfer IN c_obtieneIdNiveOfer(LN_OID_PERIODO, LN_OID_CATA) LOOP
                  --delete tabla PRE_NIVEL_OFERT_PRODU
                  DELETE PRE_NIVEL_OFERT_PRODU
                 WHERE NIOF_OID_NIVE_OFER = cursor_obtieneIdNiveOfer.oid_nive_ofer;

                 FOR cursor_obtieneIdNiveOferRan IN c_obtieneIdNiveOferRan(cursor_obtieneIdNiveOfer.oid_nive_ofer) LOOP
                      --delete tabla PRE_NIVEL_OFERT_GRATI
                      DELETE PRE_NIVEL_OFERT_GRATI
                      WHERE NIOF_OID_NIVE_OFER_RANG = cursor_obtieneIdNiveOferRan.Oid_Nive_Ofer_Rang;
                 END LOOP;

                 --delete tabla PRE_NIVEL_OFERT_RANGO
                 DELETE PRE_NIVEL_OFERT_RANGO
                 WHERE NIOF_OID_NIVE_OFER = cursor_obtieneIdNiveOfer.Oid_Nive_Ofer;
             END LOOP;

             FOR cursor_obtieneIdNxOfer IN c_obtieneIdNxOfer(ln_oid_periodo, ln_oid_cata) LOOP
                --delete tabla PRE_NX_OFERT_PRODU
                DELETE PRE_NX_OFERT_PRODU
               WHERE NIOF_OID_NX_OFER = cursor_obtieneIdNxOfer.oid_nx_ofer;

               FOR cursor_obtieneIdNxOferRang IN c_obtieneIdNxOferRang(cursor_obtieneIdNxOfer.oid_nx_ofer) LOOP
                  --delete tabla PRE_NX_OFERT_GRATI
                  DELETE PRE_NX_OFERT_GRATI
                  WHERE niof_oid_nx_ofer_rang = cursor_obtieneIdNxOferRang.Oid_Nx_Ofer_Rang;
               END LOOP;

               --delete tabla PRE_NX_OFERT_RANGO
               DELETE PRE_NX_OFERT_RANGO
               WHERE NIOF_OID_NX_OFER = cursor_obtieneIdNxOfer.Oid_Nx_Ofer;
             END LOOP;

             OPEN c_obtieneIdPreOfer(ln_oid_periodo, ln_oid_cata);
             LOOP
             FETCH c_obtieneIdPreOfer BULK COLLECT INTO obtieneIdPreOferRecordN LIMIT W_FILAS;
              IF obtieneIdPreOferRecordN.COUNT > 0 THEN
                 FOR x IN obtieneIdPreOferRecordN.FIRST .. obtieneIdPreOferRecordN.LAST LOOP
                    
                    -- delete tabla PRE_OFERT_DETAL
                    DELETE PRE_OFERT_DETAL
                     WHERE OFER_OID_OFER = obtieneIdPreOferRecordN(x).oidOfer;


                    -- delete tabla PRE_GRUPO_OFERT
                     DELETE PRE_GRUPO_OFERT
                     WHERE OFER_OID_OFER = obtieneIdPreOferRecordN(x).oidOfer;


                      -- delete tabla PRE_OFERT
                      DELETE PRE_OFERT
                     WHERE OID_OFER = obtieneIdPreOferRecordN(x).oidOfer;



                 END LOOP;
              END IF;
              EXIT WHEN c_obtieneIdPreOfer%NOTFOUND;
             END LOOP;
             CLOSE c_obtieneIdPreOfer;

             FOR cursor_obtieneIdNiveOfer IN c_obtieneIdNiveOfer(LN_OID_PERIODO, LN_OID_CATA) LOOP
                 DELETE PRE_NIVEL_OFERT
                WHERE OID_NIVE_OFER = cursor_obtieneIdNiveOfer.Oid_Nive_Ofer;
             END LOOP;

             FOR cursor_obtieneIdNxOfer IN c_obtieneIdNxOfer(ln_oid_periodo, ln_oid_cata) LOOP
                --delete tabla PRE_NX_OFERT
                DELETE PRE_NX_OFERT
                WHERE OID_NX_OFER = cursor_obtieneIdNxOfer.Oid_Nx_Ofer;
             END LOOP;
             
             ped_pkg_cuadr_ofert.ped_pr_carg_matr_plan(psnumerolote, pscodigoPais);
             
             -- Actualiza Lote Planit
             UPDATE PRE_OFERT_PLANI
                SET IND_PROC = '1' 
              WHERE NUM_LOTE = psnumerolote;
        END IF;
        ---MCH
        END IF;
        
        
       

  EXCEPTION
    WHEN error_generado THEN
         INT_PKG_PRE.INT_PR_INSER_ERROR_PLANI(PRE_ERPL_SEQ.nextval, psnumerolote, 'General', 'General','Este lote ya ha sido procesado');
         psEliminarArch := 'S';
         --RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pre_carg_matr_plan : Este lote ya ha sido procesado');
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR int_pre_carg_matr_plan : '||ls_sqlerrm);
 END int_pre_carg_matr_plan ;

/***************************************************************************
 Descripcion       : Procedimiento que inserta los errores en el proceso de 
                     Carga Matriz Planit
 Fecha Creacion    : 28/01/2016
 Autor             : Aurelio Oviedo
*****************************************************************************/
PROCEDURE INT_PR_INSER_ERROR_PLANI(p_oidErrorPlani   PRE_ERRO_PLANI.OID_ERRO_PLAN%TYPE,
                                   p_numeroLote      PRE_ERRO_PLANI.NUM_LOTE%TYPE,
                                   p_nombreArchivo   PRE_ERRO_PLANI.NOM_ARCH%TYPE,
                                   p_nombreCampo     PRE_ERRO_PLANI.NOM_CAMP%TYPE,
                                   p_obsErrorPlani   PRE_ERRO_PLANI.OBS_ERRO%TYPE) IS
   
    PRAGMA AUTONOMOUS_TRANSACTION;
   
    BEGIN
        INSERT INTO PRE_ERRO_PLANI(oid_erro_plan, num_lote, nom_arch, nom_camp, Obs_Erro)
        VALUES(p_oidErrorPlani, p_numeroLote, p_nombreArchivo, p_nombreCampo, p_obsErrorPlani);
      
        COMMIT;
    END  INT_PR_INSER_ERROR_PLANI;

END INT_PKG_PRE;
/
