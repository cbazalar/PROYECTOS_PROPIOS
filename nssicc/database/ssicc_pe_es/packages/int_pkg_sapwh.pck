CREATE OR REPLACE PACKAGE int_pkg_sapwh IS

  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envia Demanda a SAP
  Fecha Creacion    : 04/09/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_saw_deman_sap
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envia Demanda a Yobel
  Fecha Creacion    : 04/09/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_saw_deman_yobel
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psnumerolote     VARCHAR2,
    pstipoperiodo    VARCHAR2
  );

END int_pkg_sapwh;
/

CREATE OR REPLACE PACKAGE BODY int_pkg_sapwh IS
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envia Demanda a SAP
  Fecha Creacion    : 04/09/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_saw_deman_sap
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  ) IS
    CURSOR c_interfaz(vnoidperiodo NUMBER) IS
      SELECT c.cod_sap,
             SUM(b.num_unid_por_aten) cant
        FROM ped_solic_cabec a,
             ped_solic_posic b,
             mae_produ       c,
             ped_grupo_proce d
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND b.prod_oid_prod = c.oid_prod
         AND a.tspa_oid_tipo_soli_pais IN (select oid_tipo_soli_pais from INT_SAW_PARaM_TSOLI)
         AND a.perd_oid_peri = vnoidperiodo
         AND a.grpr_oid_grup_proc = d.oid_grup_proc
         and d.cod_grup_proc = 'GP3'
       GROUP BY c.cod_sap;

    TYPE interfazrec IS RECORD(
      codigosap int_saf_sapfi_factu.num_lote%TYPE,
      cantidad  NUMBER(20));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);

    lbabrirutlfile BOOLEAN;
    lnoidperiodo   cra_perio.oid_peri%TYPE;
  BEGIN

    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnoidperiodo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
          lslinea := interfazrecord(x).codigosap || ';' || interfazrecord(x).cantidad;

          utl_file.put_line(v_handle,
                            lslinea);

        END LOOP;
      END IF;

      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'INT_PR_SAW_DEMAN_SAP: ' || ls_sqlerrm);
  END int_pr_saw_deman_sap;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envia Demanda a Yobel
  Fecha Creacion    : 04/09/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_saw_deman_yobel
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psnumerolote     VARCHAR2,
    pstipoperiodo    VARCHAR2
  ) IS
    CURSOR c_interfaz(vnoidperiodo NUMBER,vscodigocia VARCHAR2) IS
      SELECT vscodigocia compania,
             substr(psnumerolote,
                    1,
                    8) fechaproceso,
             substr(psnumerolote,
                    9,
                    4) lote,
             pstipoperiodo tipoperiodo,
             pscodigoperiodo periodo,
             c.cod_sap codigosap,
             (SELECT v.val_i18n
                FROM v_gen_i18n_sicc v
               WHERE v.attr_enti = 'MAE_PRODU'
                 AND v.idio_oid_idio = 1
                 AND v.val_oid = b.prod_oid_prod) descproducto,
             SUM(b.num_unid_por_aten) unidades
        FROM ped_solic_cabec a,
             ped_solic_posic b,
             mae_produ       c,
             ped_grupo_proce d
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND b.prod_oid_prod = c.oid_prod
         AND a.tspa_oid_tipo_soli_pais IN (SELECT oid_tipo_soli_pais FROM int_saw_param_tsoli)
         AND a.perd_oid_peri = vnoidperiodo
         AND a.grpr_oid_grup_proc = d.oid_grup_proc
         AND d.cod_grup_proc = 'GP3'
       GROUP BY c.cod_sap,
                b.prod_oid_prod;

    TYPE interfazrec IS RECORD(
      compania     VARCHAR2(3),
      fechaproceso VARCHAR2(8),
      lote         VARCHAR2(4),
      tipoperiodo  VARCHAR2(2),
      periodo      seg_perio_corpo.cod_peri%TYPE,
      codigosap    mae_produ.cod_sap%TYPE,
      descproducto gen_i18n_sicc_comun.val_i18n%TYPE,
      cantidad     NUMBER(20));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);

    lbabrirutlfile BOOLEAN;
    lnoidperiodo   cra_perio.oid_peri%TYPE;
    lsCodigoCia    bas_pais.cod_comp_yobe%TYPE;

  BEGIN

    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    SELECT cod_comp_yobe INTO lscodigocia FROM bas_pais WHERE cod_pais = pscodigopais;

    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnoidperiodo,lscodigocia);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;

      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;

      IF interfazrecord.COUNT > 0 THEN
        FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
          lslinea := interfazrecord(x).compania || ';' || interfazrecord(x)
                    .fechaproceso || ';' || interfazrecord(x).lote || ';' || interfazrecord(x)
                    .tipoperiodo || ';' || interfazrecord(x).periodo || ';' || interfazrecord(x)
                    .codigosap || ';' || interfazrecord(x).descproducto || ';' || interfazrecord(x)
                    .cantidad;

          utl_file.put_line(v_handle,
                            lslinea);

        END LOOP;
      END IF;

      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;

    CLOSE c_interfaz;
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);

      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'INT_PR_SAW_DEMAN_YOBEL: ' || ls_sqlerrm);
  END int_pr_saw_deman_yobel;

END int_pkg_sapwh;
/

