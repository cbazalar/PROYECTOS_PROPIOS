create or replace package INT_PKG_RUV is

  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;

  /* Declaracion de funciones */
  /**************************************************************************
  Descripcion       : Funcion que regresa la marca segun el oid del pais
  Fecha Creacion    : 27/04/2009
  Autor             : Telly Tataje
  **************************************************************************/
  FUNCTION ruv_fn_devue_marc_pais(psoidpais NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Funcion que regresa el numero de documento
  Fecha Creacion    : 23/04/2009
  Autor             : Telly Tataje
  **************************************************************************/
  FUNCTION ruv_fn_devue_nume_docu(psoidregi NUMBER) RETURN NUMBER;

  /* Declaracion de procedures */

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envia Registro Unico de Venta
  Fecha Creacion    : 27/03/2009
  Autor             : Telly Tataje
  ***************************************************************************/
  PROCEDURE int_pr_ruv_envia_ruv
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psmes              VARCHAR2,
    psanho             VARCHAR2,
    psconstante        VARCHAR2,
    pslote             VARCHAR2,
    psnombreimpuesto   VARCHAR2,
    psconstantecredito VARCHAR2
  );

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Registro de
                      ventas
  Fecha Creacion    : 03/05/2012
  Autor             : Dennys Oliva Iriarte
  Modificado por    :Sergio Buchelli
  ***************************************************************************/
  PROCEDURE INT_PR_RUV_RECEP_SUBAC (pscodigopais     VARCHAR2,
                                    pscodigosociedad VARCHAR2,
                                    pscodigosistema  VARCHAR2,
                                    pscodigointerfaz VARCHAR2,
                                    psnombrearchivo  VARCHAR2,
                                    psCantidadRegistros OUT VARCHAR2);

END INT_PKG_RUV;
/

create or replace package body INT_PKG_RUV is

  /**************************************************************************
  Descripcion       : Funcion que regresa la marca segun el oid del pais
  Fecha Creacion    : 27/04/2009
  Autor             : Telly Tataje
  **************************************************************************/
  FUNCTION ruv_fn_devue_marc_pais(psoidpais NUMBER) RETURN VARCHAR2 IS
    lnresult bas_pais.ind_pais_marc%TYPE;
  BEGIN
    SELECT bas_pais.ind_pais_marc
      INTO lnresult
      FROM seg_pais seg_pais, bas_pais bas_pais
     WHERE seg_pais.cod_pais = bas_pais.cod_pais
       AND seg_pais.oid_pais = psoidpais;
    RETURN lnresult;
  END ruv_fn_devue_marc_pais;

  /**************************************************************************
  Descripcion       : Funcion que regresa el numero de documento
  Fecha Creacion    : 23/04/2009
  Autor             : Telly Tataje
  **************************************************************************/
  FUNCTION ruv_fn_devue_nume_docu(psoidregi NUMBER) RETURN NUMBER IS
    lnresult        fac_docum_conta_cabec.num_docu_lega%TYPE;
    lsidnotacredito fac_tipo_docum.oid_tipo_docu%TYPE;
  BEGIN
    --Cargando el oid de nota de credito
    SELECT oid_tipo_docu
      INTO lsidnotacredito
      FROM fac_tipo_docum
     WHERE cod_tipo_docu = '021'; --oid =32

    SELECT facorig.num_docu_lega docu
      INTO lnresult
      FROM fac_regis_unico_venta ruv,
           fac_docum_conta_cabec f,
           ped_solic_cabec       p,
           fac_docum_conta_linea fl,
           ped_solic_posic       psp,
           ped_solic_cabec       pedorig,
           fac_docum_conta_cabec facorig,
           fac_docum_conta_linea facorigl,
           ped_solic_posic       psporig
     WHERE ruv.dcca_oid_cabe = f.oid_cabe
       AND fl.dcca_oid_cabe = f.oid_cabe
       AND fl.sopo_oid_soli_posi = psp.oid_soli_posi
       AND p.oid_soli_cabe = f.soca_oid_soli_cabe
       AND p.soca_oid_docu_refe = pedorig.oid_soli_cabe
       AND pedorig.oid_soli_cabe = facorig.soca_oid_soli_cabe
       AND facorigl.dcca_oid_cabe = facorig.oid_cabe
       AND facorigl.sopo_oid_soli_posi = psporig.oid_soli_posi
       AND fl.prod_oid_prod = facorigl.prod_oid_prod
       AND psp.val_codi_vent = psporig.val_codi_vent
       AND ruv.oid_regi = psoidregi
       AND ruv.tido_oid_tipo_docu IN (lsidnotacredito)
       AND rownum = 1;

    RETURN lnresult;
  END ruv_fn_devue_nume_docu;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envia Registro Unico de Venta
  Fecha Creacion    : 27/03/2009
  Autor             : Telly Tataje
  ***************************************************************************/
  PROCEDURE int_pr_ruv_envia_ruv
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psmes              VARCHAR2,
    psanho             VARCHAR2,
    psconstante        VARCHAR2,
    pslote             VARCHAR2,
    psnombreimpuesto   VARCHAR2,
    psconstantecredito VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      psconstante        VARCHAR2,
      psanhomes          VARCHAR2,
      psimpuesto         NUMBER,
      psidfactura        NUMBER,
      psidnotacredito    NUMBER,
      psconstantecredito VARCHAR2
    ) IS
      SELECT ruv.oid_regi,
             NULL nnumoper,
             CASE ruv_fn_devue_marc_pais(ruv.pais_oid_pais)
               WHEN 'ES' THEN
                '2'
               WHEN 'LB' THEN
                '1'
               ELSE
                NULL
             END cnegocio,
             NULL ctiendas,
             'D' ccanalvt,
             to_char(ruv.fec_emis, 'YYYYMMDD') dfechafac,
             ruv.val_nume_iden_nnal crif,
             substr((ruv.val_ape1 || ' ' || ruv.val_ape2 || ' ' ||
                    ruv.val_nom1 || ' ' || ruv.val_nom2),
                    1,
                    50) crazsoci,
             NULL nplanill,
             NULL nvalofob,
             CASE ruv.tido_oid_tipo_docu
               WHEN psidnotacredito THEN
                NULL
               ELSE
                lpad(ruv.val_nume_docu_lega, 8, '0')
             END cnumfact,
             CASE ruv.tido_oid_tipo_docu
               WHEN psidfactura THEN
                nvl(ruv.num_cont_docu_lega,
                    to_char(to_number(psconstante) +
                            to_number(nvl(ruv.val_nume_docu_lega, '0'))))
               ELSE
                nvl(ruv.num_cont_docu_lega,
                    to_char(to_number(psconstantecredito) +
                            to_number(nvl(ruv.val_nume_docu_lega, '0'))))
             END nctrlini,
             CASE ruv.tido_oid_tipo_docu
               WHEN psidfactura THEN
                nvl(ruv.num_cont_docu_lega,
                    to_char(to_number(psconstante) +
                            to_number(nvl(ruv.val_nume_docu_lega, '0'))))
               ELSE
                nvl(ruv.num_cont_docu_lega,
                    to_char(to_number(psconstantecredito) +
                            to_number(nvl(ruv.val_nume_docu_lega, '0'))))
             END nctrlfin,
             NULL nnotadeb,
             CASE ruv.tido_oid_tipo_docu
               WHEN psidfactura THEN
                NULL
               ELSE
                lpad(ruv.val_nume_docu_lega, 8, '0')
             END nnotacre,
             lpad(nvl(ruv.val_nume_docu_refe,
                      ruv_fn_devue_nume_docu(ruv.oid_regi)),
                  8,
                  '0') cfactafe,
             CASE ruv.tido_oid_tipo_docu
               WHEN psidfactura THEN
                '1'
               ELSE
                '3'
             END ctiptran,
             NULL nexentas,
             NULL nexonera,
             NULL nnosujet,
             NULL nsdcrefi,
             ruv.imp_tota ntvtaiva,
             ruv.val_base_impo_neto nbaseimp,
             psimpuesto nalicuot,
             ruv.imp_impu ndebfisc,
             NULL nivarete
        FROM fac_regis_unico_venta ruv
       WHERE ruv.tido_oid_tipo_docu IN (psidfactura, psidnotacredito)
         AND ruv.fec_emis >= to_date(psanhomes || '01', 'YYYYMMDD')
         AND ruv.fec_emis <= last_day(to_date(psanhomes, 'YYYYMM'))
         AND ruv.oid_regi NOT IN (SELECT oid_regi FROM ruv_histo_fac_ruv)
         AND (ruv.imp_tota <> 0 OR ruv.val_base_impo_neto <> 0 OR
             ruv.imp_impu <> 0);

    TYPE interfazrec IS RECORD(
      oid_regi  fac_regis_unico_venta.oid_regi%TYPE,
      nnumoper  NUMBER(9),
      cnegocio  VARCHAR2(1),
      ctiendas  VARCHAR2(40),
      ccanalvt  VARCHAR2(1),
      dfechafac VARCHAR2(8),
      crif      fac_regis_unico_venta.val_nume_iden_nnal%TYPE,
      crazsoci  VARCHAR2(50),
      nplanill  NUMBER(9),
      nvalofob  NUMBER(9, 2),
      cnumfact  VARCHAR(9),
      nctrlini  NUMBER(9),
      nctrlfin  NUMBER(9),
      nnotadeb  NUMBER(9),
      nnotacre  VARCHAR(9),
      cfactafe  VARCHAR(9),
      ctiptran  VARCHAR2(1),
      nexentas  NUMBER(9, 2),
      nexonera  NUMBER(9, 2),
      nnosujet  NUMBER(9, 2),
      nsdcrefi  NUMBER(9, 2),
      ntvtaiva  fac_regis_unico_venta.imp_tota%TYPE,
      nbaseimp  fac_regis_unico_venta.val_base_impo_neto%TYPE,
      nalicuot  ped_tasa_impue.val_tasa_impu%TYPE,
      ndebfisc  fac_regis_unico_venta.imp_impu%TYPE,
      nivarete  NUMBER(9, 2));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 1000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lscodpais       VARCHAR2(20);
    lsidfactura     NUMBER;
    lsidnotacredito NUMBER;
    lsmes           VARCHAR2(2);
    lbabrirutlfile  BOOLEAN;
    lsimpuesto      NUMBER;
  BEGIN

    lbabrirutlfile := TRUE;
    --Cargando el oid pais
    SELECT oid_pais
      INTO lscodpais
      FROM seg_pais
     WHERE cod_pais = pscodigopais;

    --Cargando el Impuesto
    SELECT val_tasa_impu
      INTO lsimpuesto
      FROM ped_tasa_impue
     WHERE pais_oid_pais = lscodpais
       AND val_indi_impu = psnombreimpuesto;

    --Cargando el oid factura
    SELECT oid_tipo_docu
      INTO lsidfactura
      FROM fac_tipo_docum
     WHERE cod_tipo_docu = '001'; --oid =1

    --Cargando el oid de nota de credito
    SELECT oid_tipo_docu
      INTO lsidnotacredito
      FROM fac_tipo_docum
     WHERE cod_tipo_docu = '021'; --oid =32

    lsmes := psmes;
    IF (to_number(lsmes) < 10) THEN
      lsmes := '0' || lsmes;
    END IF;
    OPEN c_interfaz(psconstante,
                    psanho || lsmes,
                    lsimpuesto,
                    lsidfactura,
                    lsidnotacredito,
                    psconstantecredito);
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

      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last LOOP
          lslinea := interfazrecord(x)
                     .nnumoper || ';' || interfazrecord(x).cnegocio || ';' || interfazrecord(x)
                     .ctiendas || ';' || interfazrecord(x).ccanalvt || ';' || interfazrecord(x)
                     .dfechafac || ';' || interfazrecord(x).crif || ';' || interfazrecord(x)
                     .crazsoci || ';' || interfazrecord(x).nplanill || ';' || interfazrecord(x)
                     .nvalofob || ';' || interfazrecord(x).cnumfact || ';' || interfazrecord(x)
                     .nctrlini || ';' || interfazrecord(x).nctrlfin || ';' || interfazrecord(x)
                     .nnotadeb || ';' || interfazrecord(x).nnotacre || ';' || interfazrecord(x)
                     .cfactafe || ';' || interfazrecord(x).ctiptran || ';' || interfazrecord(x)
                     .nexentas || ';' || interfazrecord(x).nexonera || ';' || interfazrecord(x)
                     .nnosujet || ';' || interfazrecord(x).nsdcrefi || ';' || interfazrecord(x)
                     .ntvtaiva || ';' || interfazrecord(x).nbaseimp || ';' || interfazrecord(x)
                     .nalicuot || ';' || interfazrecord(x).ndebfisc || ';' || interfazrecord(x)
                     .nivarete;

          utl_file.put_line(v_handle, lslinea);
          INSERT INTO ruv_histo_fac_ruv
            (oid_regi, num_lote, fec_proc)
          VALUES
            (interfazrecord(x).oid_regi, pslote, SYSDATE);
          COMMIT;
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
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'INT_PR_RUV_ENVIA_RUV: ' || ls_sqlerrm);
  END int_pr_ruv_envia_ruv;

/***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Registro de 
                      ventas
  Fecha Creacion    : 03/05/2012
  Autor             : Dennys Oliva Iriarte
  Modificado por    :Sergio Buchelli
  ***************************************************************************/
  PROCEDURE INT_PR_RUV_RECEP_SUBAC (pscodigopais     VARCHAR2,
                                    pscodigosociedad VARCHAR2,
                                    pscodigosistema  VARCHAR2,
                                    pscodigointerfaz VARCHAR2,
                                    psnombrearchivo  VARCHAR2,
                                    psCantidadRegistros OUT VARCHAR2) IS
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

    TYPE interfazcab IS RECORD(posiccampo  bas_estru_archi.pos_camp%TYPE,
                               longcampo   bas_estru_archi.lon_camp%TYPE,
                               cantdecimal bas_estru_archi.can_deci%TYPE,
                               idcampo     bas_estru_archi.ide_camp%TYPE,
                               sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;

    interfazrecord interfazcabtab;
    
    TYPE t_pais_oid_pais       IS TABLE OF FAC_REGIS_UNICO_VENTA.PAIS_OID_PAIS%TYPE;      
    TYPE t_soci_oid_soci       IS TABLE OF FAC_REGIS_UNICO_VENTA.SOCI_OID_SOCI%TYPE;      
    TYPE t_sbac_oid_sbac       IS TABLE OF FAC_REGIS_UNICO_VENTA.SBAC_OID_SBAC%TYPE;      
    TYPE t_fec_emis            IS TABLE OF FAC_REGIS_UNICO_VENTA.FEC_EMIS%TYPE;           
    TYPE t_val_base_impo       IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO%TYPE;      
    TYPE t_imp_impu            IS TABLE OF FAC_REGIS_UNICO_VENTA.IMP_IMPU%TYPE;           
    TYPE t_imp_tota            IS TABLE OF FAC_REGIS_UNICO_VENTA.IMP_TOTA%TYPE;           
    TYPE t_val_nume_iden_fisc  IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_NUME_IDEN_FISC%TYPE; 
    TYPE t_val_nume_iden_nnal  IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_NUME_IDEN_NNAL%TYPE; 
    TYPE t_val_seri_docu_refe  IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_SERI_DOCU_REFE%TYPE; 
    TYPE t_val_nume_docu_refe  IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_NUME_DOCU_REFE%TYPE; 
    TYPE t_val_inte_mora       IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_INTE_MORA%TYPE;      
    TYPE t_val_desc            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_DESC%TYPE;           
    TYPE t_val_comi            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_COMI%TYPE;           
    TYPE t_val_flet            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_FLET%TYPE;           
    TYPE t_val_base_impo_neto  IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO_NETO%TYPE; 
    TYPE t_clie_oid_clie       IS TABLE OF FAC_REGIS_UNICO_VENTA.CLIE_OID_CLIE%TYPE;      
   -- TYPE t_ind_esta            IS TABLE OF FAC_REGIS_UNICO_VENTA.IND_ESTA%TYPE;           
    TYPE t_ind_tran_grat       IS TABLE OF FAC_REGIS_UNICO_VENTA.IND_TRAN_GRAT%TYPE;      
    TYPE t_ind_fact_grat       IS TABLE OF FAC_REGIS_UNICO_VENTA.IND_FACT_GRAT%TYPE;      
    TYPE t_taim_oid_tasa_impu  IS TABLE OF FAC_REGIS_UNICO_VENTA.TAIM_OID_TASA_IMPU%TYPE; 
    TYPE t_tido_oid_tipo_docu  IS TABLE OF FAC_REGIS_UNICO_VENTA.TIDO_OID_TIPO_DOCU%TYPE; 
   -- TYPE t_val_indi_ruv        IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_INDI_RUV%TYPE;       
    TYPE t_val_nume_docu_lega  IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_NUME_DOCU_LEGA%TYPE; 
    TYPE t_val_seri_docu_lega  IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_SERI_DOCU_LEGA%TYPE; 
    TYPE t_tido_tipo_docu_refe IS TABLE OF FAC_REGIS_UNICO_VENTA.TIDO_TIPO_DOCU_REFE%TYPE;
    TYPE t_fec_emis_refe       IS TABLE OF FAC_REGIS_UNICO_VENTA.FEC_EMIS_REFE%TYPE;      
    TYPE t_tdoc_oid_tipo_docu  IS TABLE OF FAC_REGIS_UNICO_VENTA.TDOC_OID_TIPO_DOCU%TYPE; 
    TYPE t_val_nomb            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_NOMB%TYPE;    
    TYPE t_val_nom1            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_NOM1%TYPE;
    TYPE t_val_nom2            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_NOM2%TYPE;
    TYPE t_val_ape1            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_APE1%TYPE;
    TYPE t_val_ape2            IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_APE2%TYPE;  
    TYPE t_val_punt_emis       IS TABLE OF FAC_REGIS_UNICO_VENTA.VAL_PUNT_EMIS%TYPE;  

    TYPE t_ind_esta            IS TABLE OF FAC_REGIS_UNICO_VENTA.Ind_Esta%TYPE;
    TYPE t_num_contr           IS TABLE OF FAC_REGIS_UNICO_VENTA.num_cont_docu_lega%TYPE;

    v_pais_oid_pais         t_pais_oid_pais       := t_pais_oid_pais(); 
    v_soci_oid_soci         t_soci_oid_soci       := t_soci_oid_soci(); 
    v_sbac_oid_sbac         t_sbac_oid_sbac       := t_sbac_oid_sbac(); 
    v_fec_emis              t_fec_emis            := t_fec_emis(); 
    v_val_base_impo         t_val_base_impo       := t_val_base_impo(); 
    v_imp_impu              t_imp_impu            := t_imp_impu(); 
    v_imp_tota              t_imp_tota            := t_imp_tota(); 
    v_val_nume_iden_fisc    t_val_nume_iden_fisc  := t_val_nume_iden_fisc(); 
    v_val_nume_iden_nnal    t_val_nume_iden_nnal  := t_val_nume_iden_nnal(); 
    v_val_seri_docu_refe    t_val_seri_docu_refe  := t_val_seri_docu_refe(); 
    v_val_nume_docu_refe    t_val_nume_docu_refe  := t_val_nume_docu_refe(); 
    v_val_inte_mora         t_val_inte_mora       := t_val_inte_mora(); 
    v_val_desc              t_val_desc            := t_val_desc(); 
    v_val_comi              t_val_comi            := t_val_comi(); 
    v_val_flet              t_val_flet            := t_val_flet(); 
    v_val_base_impo_neto    t_val_base_impo_neto  := t_val_base_impo_neto(); 
    v_clie_oid_clie         t_clie_oid_clie       := t_clie_oid_clie(); 
    --v_ind_esta              t_ind_esta            := t_ind_esta(); 
    v_ind_tran_grat         t_ind_tran_grat       := t_ind_tran_grat(); 
    v_ind_fact_grat         t_ind_fact_grat       := t_ind_fact_grat(); 
    v_taim_oid_tasa_impu    t_taim_oid_tasa_impu  := t_taim_oid_tasa_impu(); 
    v_tido_oid_tipo_docu    t_tido_oid_tipo_docu  := t_tido_oid_tipo_docu(); 
    --v_val_indi_ruv          t_val_indi_ruv        := t_val_indi_ruv(); 
    v_val_nume_docu_lega    t_val_nume_docu_lega  := t_val_nume_docu_lega(); 
    v_val_seri_docu_lega    t_val_seri_docu_lega  := t_val_seri_docu_lega(); 
    v_tido_tipo_docu_refe   t_tido_tipo_docu_refe := t_tido_tipo_docu_refe(); 
    v_fec_emis_refe         t_fec_emis_refe       := t_fec_emis_refe(); 
    v_tdoc_oid_tipo_docu    t_tdoc_oid_tipo_docu  := t_tdoc_oid_tipo_docu(); 
    v_val_nomb              t_val_nomb            := t_val_nomb(); 
    v_val_nom1              t_val_nom1            := t_val_nom1(); 
    v_val_nom2              t_val_nom2            := t_val_nom2(); 
    v_val_ape1              t_val_ape1            := t_val_ape1(); 
    v_val_ape2              t_val_ape2            := t_val_ape2(); 
    v_val_punt_emis         t_val_punt_emis       := t_val_punt_emis();

    v_ind_esta              t_ind_esta            := t_ind_esta();

    v_num_contr             t_num_contr           := t_num_contr();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
    inicio          NUMBER := 0;
    i               BINARY_INTEGER := 0;
    j               BINARY_INTEGER := 0;
    
    aux             number;
    
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20) || '/';
    lsreplace VARCHAR2(100) := 'a';
    
   BEGIN
   psCantidadRegistros:=0;
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
                                                 
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

            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;   
                
                -- [2] -- codigo pais
                IF (posicion = 2) THEN  
                   v_pais_oid_pais.extend;                   
                   begin
                     -- obtiene el oid_pais, si no existe, lanza excepcion
                     select sp.oid_pais 
                       into v_pais_oid_pais(i)
                       from seg_pais sp 
                      where sp.cod_pais = nvl(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),pscodigopais);
                   exception
                     when no_data_found then
                       raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Codigo de pais no es valido'||CHR(10)||CHR(13));
                   end;  
                     
                -- [3] -- codigo sociedad                   
                ELSIF (posicion = 3) THEN  
                   v_soci_oid_soci.extend;                   
                   begin
                     -- obtiene el oid_sociedad, si no existe, lanza excepcion
                     select ss.oid_soci 
                       into v_soci_oid_soci(i)
                       from seg_socie ss 
                      where ss.cod_soci = nvl(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),pscodigosociedad);
                   exception
                     when no_data_found then
                       raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Codigo de sociedad no es valido'||CHR(10)||CHR(13));
                   end;     
                
                -- [4] -- tipo impuesto 
                ELSIF (posicion = 4) THEN  
                    v_taim_oid_tasa_impu.extend;                   
                   begin
                     -- obtiene el oid_tipo_impuesto, si no existe, lanza excepcion
                    select ti.oid_tasa_impu
                      into v_taim_oid_tasa_impu(i) 
                      from ped_tasa_impue ti 
                     where ti.val_indi_impu = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));

                   exception
                     when no_data_found then
                       raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Codigo de Tipo Impuesto no es valido'||CHR(10)||CHR(13));
                   end;
                /*
                 -- [5] -- tasa impuesto 
                ELSIF (posicion = 5) THEN                     
                   begin
                     -- Obtiene la tasa
                    select ti.val_tasa_impu
                      into aux
                      from ped_tasa_impue ti 
                     where ti.val_indi_impu = v_taim_oid_tasa_impu(i) 
                       and ti.val_tasa_impu = TRIM(substr(lslinea,inicio,longitud));
                   exception
                     when no_data_found then
                       raise_application_error(-20123, 'Linea : ['|| i || '] -- La tasa de impuesto no coincide con el tipo de impuesto enviado');
                   end;*/
                   
                -- [6] -- tipo documento legal  
                ELSIF (posicion = 6) THEN   
                   v_tido_oid_tipo_docu.extend;                   
                   begin
                     -- obtiene el oid_tipo_documento, si no existe, lanza excepcion
                     select td.oid_tipo_docu 
                       into v_tido_oid_tipo_docu(i)
                       from fac_tipo_docum td 
                       where td.cod_tipo_docu = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                   exception
                     when no_data_found then
                       raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Codigo de Tipo Documento no es valido'||CHR(10)||CHR(13));
                   end; 
                
                -- [7] -- serie documento legal  
                ELSIF (posicion = 7) THEN                     
                   v_val_seri_docu_lega.extend;  
                   if  TRIM(substr(lslinea,inicio,longitud)) = '¿???' then
                     raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Serie de documento legal no es valida'||CHR(10)||CHR(13));
                   else
                     v_val_seri_docu_lega(i) := TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)); 
                   end if;
                   
                -- [8] -- numero documento legal  
                ELSIF (posicion = 8) THEN
                   v_val_nume_docu_lega.extend; 
                   v_val_nume_docu_lega(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)); 
                   
                -- [9] -- fecha emision  
                ELSIF (posicion = 9) THEN
                   v_fec_emis.extend; 
                   v_fec_emis(i):= to_date(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),'YYYYMMDD');   
                
                -- [11] -- codigo cliente 
                ELSIF (posicion = 11) THEN
                   v_clie_oid_clie.extend; 
                   v_val_nom1.extend;
                   v_val_nom2.extend;
                   v_val_ape1.extend;
                   v_val_ape2.extend;
                   
                   if TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)) is not null then
                      begin
                         -- si el codigo tiene datos, obtiene el oid_cliente, si no existe, lanza excepcion
                         select ma.oid_clie
                           into v_clie_oid_clie(i)                      
                           from mae_clien ma 
                          where ma.cod_clie = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                          
                          begin
                            select ma.val_nom1
                              into v_val_nom1(i) 
                              from mae_clien ma 
                              where ma.cod_clie = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                          exception
                            when no_data_found then
                              null;
                          end;

                          begin
                            select ma.val_nom2
                              into v_val_nom2(i) 
                              from mae_clien ma 
                              where ma.cod_clie = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                          exception
                            when no_data_found then
                              null;
                          end;    

                           begin
                            select ma.val_ape1
                              into v_val_ape1(i) 
                              from mae_clien ma 
                              where ma.cod_clie = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                          exception
                            when no_data_found then
                              null;
                          end;

                          begin
                            select ma.val_ape2
                              into v_val_ape2(i) 
                              from mae_clien ma 
                              where ma.cod_clie = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                          exception
                            when no_data_found then
                              null;
                          end; 
                         
                      exception
                         when no_data_found then
                           raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Codigo de Cliente no es valido'||CHR(10)||CHR(13));
                      end;   
                   end if;
                
                -- [12] -- nombre cliente  
                ELSIF (posicion = 12) THEN
                   v_val_nomb.extend; 
                   v_val_nomb(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));    
                
                -- [13] -- tipo documento identidad 
                 -- [13] -- tipo documento identidad 
                ELSIF (posicion = 13) THEN
                   v_tdoc_oid_tipo_docu.extend; 
                   if TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)) is not null then
                   begin
                      -- obtiene el oid_tipo_docu_iden, si no existe, lanza excepcion
                      select td.oid_tipo_docu
                        into v_tdoc_oid_tipo_docu(i) 
                        from mae_tipo_docum td 
                       where td.cod_tipo_docu = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                   exception
                      when no_data_found then
                        raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Tipo documento de identidad no es valido'||CHR(10)||CHR(13));
                   end ;   
                   end if;   
                
                -- [14] -- numero documento identidad  
                ELSIF (posicion = 14) THEN
                   v_val_nume_iden_nnal.extend; 
                   v_val_nume_iden_fisc.extend;
                   v_val_nume_iden_nnal(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));   
                   v_val_nume_iden_fisc(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                
                -- [15] -- tipo documento referencia
                ELSIF (posicion = 15) THEN
                   v_tido_tipo_docu_refe.extend; 
                    
                   if TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)) is not null then
                   -- revisar si es obligatoria la referencia para todas las NC !!!!!!
                   begin
                      -- obtiene el oid_tipo_docu_iden, si no existe, lanza excepcion
                      /*select td.oid_tipo_docu
                        into v_tido_tipo_docu_refe(i) 
                        from mae_tipo_docum td 
                       where td.cod_tipo_docu = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));*/
                       
                      select td.oid_tipo_docu
                        into v_tido_tipo_docu_refe(i) 
                         from fac_tipo_docum td
                       where td.cod_tipo_docu = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                       
                   exception
                      when no_data_found then
                        raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Tipo documento de referencia no es valido'||CHR(10)||CHR(13));
                   end ;   
                
                   end if; 
                -- [16] -- serie documento legal referencia
                ELSIF (posicion = 16) THEN
                   v_val_seri_docu_refe.extend; 
                   v_val_seri_docu_refe(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));   
                
                -- [17] -- numero documento legal referencia
                ELSIF (posicion = 17) THEN
                   v_val_nume_docu_refe.extend; 
                   v_val_nume_docu_refe(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));   
                
                -- [18] -- interes mora
                ELSIF (posicion = 18) THEN
                   v_val_inte_mora.extend; 
                   
                   begin
                     v_val_inte_mora(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_val_inte_mora(i):= 0;
                   end;                                                            
                    
                -- [19] -- base imponible
                ELSIF (posicion = 19) THEN
                   v_val_base_impo.extend; 
                   
                   begin
                     v_val_base_impo(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_val_base_impo(i):= 0;
                   end;
                
                -- [20] -- descuento
                ELSIF (posicion = 20) THEN
                   v_val_desc.extend; 
                   
                   begin
                     v_val_desc(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_val_desc(i):= 0;
                   end;
                   
                   --v_val_desc(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));    
                   
                -- [21] -- comisiones
                ELSIF (posicion = 21) THEN
                   v_val_comi.extend; 
                   --v_val_comi(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));      
                   
                   begin
                     v_val_comi(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_val_comi(i):= 0;
                   end;
                
                -- [22] -- flete
                ELSIF (posicion = 22) THEN
                   v_val_flet.extend; 
                   --v_val_flet(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));            
                   
                   begin
                     v_val_flet(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_val_flet(i):= 0;
                   end;
                
                -- [23] -- base imponible neto
                ELSIF (posicion = 23) THEN
                   v_val_base_impo_neto.extend; 
                   --v_val_base_impo_neto(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));      
                   
                   begin
                     v_val_base_impo_neto(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_val_base_impo_neto(i):= 0;
                   end;
                   
                -- [24] -- impuesto
                ELSIF (posicion = 24) THEN
                   v_imp_impu.extend; 
                   --v_imp_impu(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));      
                   
                   begin
                     v_imp_impu(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_imp_impu(i):= 0;
                   end;
                   
                -- [25] -- importe total
                ELSIF (posicion = 25) THEN
                   v_imp_tota.extend; 
                   --v_imp_tota(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));         
                   
                   begin
                     v_imp_tota(i):= to_number(to_number(substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),1,11))||
                                          '.'||substr(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),12,13) 
                                          , '9999999999.99');
                   exception
                     when others then
                       v_imp_tota(i):= 0;
                   end;
                   
                -- [26] -- indicador factura gratuita
                ELSIF (posicion = 26) THEN
                   v_ind_fact_grat.extend; 
                   v_ind_tran_grat.extend; 
                   
                   if (TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)) = 'S') then
                       v_ind_fact_grat(i):= '1';  
                       v_ind_tran_grat(i):= '1';
                   else
                       v_ind_fact_grat(i):= '0';  
                       v_ind_tran_grat(i):= '0';
                   end if;
                   
                -- [27] -- codigo subacceso
                ELSIF (posicion = 27) THEN
                   v_sbac_oid_sbac.extend; 
                   begin
                      -- obtiene el oid_subacceso, si no existe, lanza excepcion
                      select sa.oid_sbac 
                        into v_sbac_oid_sbac(i) 
                        from seg_subac sa 
                       where sa.cod_sbac = TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                   exception
                      when no_data_found then
                        raise_application_error(-20123, CHR(10)||CHR(13)||'Linea : ['|| i || '] -- Codigo de subacceso no es valido'||CHR(10)||CHR(13));
                   end ;   
                
                -- [28] -- codigo punto emision
                ELSIF (posicion = 28) THEN
                   v_val_punt_emis.extend;                    
                   v_val_punt_emis(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                
                -- [34] -- indicador factura anulada
                ELSIF (posicion = 34) THEN
                  v_ind_esta.extend;                    
                  v_ind_esta(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));
                
                -- [35] -- fecha emision doc referencia 
                ELSIF (posicion = 35) THEN
                   v_fec_emis_refe.extend; 
                   v_fec_emis_refe(i):= to_date(TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace)),'YYYYMMDD');   
                   
                -- [36] -- numero de control 
                ELSIF (posicion = 36) THEN
                   v_num_contr.extend;                    
                   v_num_contr(i):= TRIM(translate(substr(lslinea,inicio,longitud),lscadena,lsreplace));   
                   
                END IF;
                                   
                inicio := inicio + longitud;
                                                           
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

          -- Nueva logica de eliminacion para permitir nueva carga sin duplicar existentes [Retails] --
          
          IF i = 1 THEN
            
              -- Identifica si el archivo es uno enviado por un Retail
              select count(1)
                into aux
                from bas_param_pais pp, 
                     seg_subac sb
               where pp.cod_pais = pscodigopais
                 and pp.cod_sist = 'RUV' 
                 and pp.nom_para like 'SBAC_TIENDA_%'
                 and sb.cod_sbac = pp.val_para
                 and sb.oid_sbac = v_sbac_oid_sbac(i) ;
              
              -- En caso el archivo es de un Retail, se eliminan de la entidad toda la informacion
              -- de todos los Retails cargadas para esa fecha en particular
              IF aux != 0 THEN                      
                 
             delete from fac_regis_unico_venta rv
              where to_char(rv.fec_emis,'dd/mm/yyyy') = to_char(v_fec_emis(i),'dd/mm/yyyy')
                    --and rv.sbac_oid_sbac = v_sbac_oid_sbac(i);
                    and rv.sbac_oid_sbac in (select pp.val_para 
                                               from bas_param_pais pp
                                              where pp.cod_pais = pscodigopais
                                                and pp.cod_sist = 'RUV' 
                                                and pp.nom_para like 'SBAC_TIENDA_%');
                                              
              END IF;
                                            
          END IF;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP; 
      
      END IF;

      utl_file.fclose(v_handle);  
      
      psCantidadRegistros:=v_pais_oid_pais.count;
      -- Bulk bind of data in memory table...
      FORALL j IN 1 .. v_pais_oid_pais.count
         INSERT INTO FAC_REGIS_UNICO_VENTA ( OID_REGI, 
                                             PAIS_OID_PAIS, 
                                             SOCI_OID_SOCI, 
                                             SBAC_OID_SBAC, 
                                             FEC_EMIS, 
                                             VAL_BASE_IMPO, 
                                             IMP_IMPU, 
                                             IMP_TOTA, 
                                             VAL_NUME_IDEN_FISC, 
                                             VAL_NUME_IDEN_NNAL, 
                                             VAL_SERI_DOCU_REFE, 
                                             VAL_NUME_DOCU_REFE, 
                                             VAL_INTE_MORA, 
                                             VAL_DESC, 
                                             VAL_COMI, 
                                             VAL_FLET, 
                                             VAL_BASE_IMPO_NETO, 
                                             CLIE_OID_CLIE, 
                                             IND_ESTA, 
                                             IND_TRAN_GRAT, 
                                             IND_FACT_GRAT, 
                                             TAIM_OID_TASA_IMPU, 
                                             TIDO_OID_TIPO_DOCU, 
                                             VAL_INDI_RUV, 
                                             VAL_NUME_DOCU_LEGA, 
                                             VAL_SERI_DOCU_LEGA, 
                                             TIDO_TIPO_DOCU_REFE, 
                                             FEC_EMIS_REFE, 
                                             TDOC_OID_TIPO_DOCU, 
                                             VAL_NOMB,
                                             VAL_NOM1,
                                             VAL_NOM2,
                                             VAL_APE1,
                                             VAL_APE2,
                                             VAL_PUNT_EMIS,
                                             num_cont_docu_lega
                                           ) 
                                		VALUES ( FAC_RUVE_SEQ.nextval, 
                                             v_pais_oid_pais(j),
                                             v_soci_oid_soci(j),
                                             v_sbac_oid_sbac(j),
                                             v_fec_emis(j),
                                             v_val_base_impo(j),
                                             v_imp_impu(j),
                                             v_imp_tota(j),
                                             v_val_nume_iden_fisc(j),
                                             v_val_nume_iden_nnal(j),   
                                             v_val_seri_docu_refe(j),
                                             v_val_nume_docu_refe(j),
                                             v_val_inte_mora(j),
                                             v_val_desc(j),                                                              
                                             v_val_comi(j),
                                             v_val_flet(j),
                                             v_val_base_impo_neto(j),
                                             v_clie_oid_clie(j),
                                             v_ind_esta(j),--'0',--
                                             v_ind_tran_grat(j),
                                             v_ind_fact_grat(j),
                                             v_taim_oid_tasa_impu(j),
                                             v_tido_oid_tipo_docu(j),
                                             'A',
                                             v_val_nume_docu_lega(j),
                                             v_val_seri_docu_lega(j),
                                             v_tido_tipo_docu_refe(j),
                                             v_fec_emis_refe(j),
                                             v_tdoc_oid_tipo_docu(j),
                                             decode(v_clie_oid_clie(j),null,v_val_nomb(j)),
                                             decode(v_clie_oid_clie(j),null,null,v_val_nom1(j)),
                                             decode(v_clie_oid_clie(j),null,null,v_val_nom2(j)),
                                             decode(v_clie_oid_clie(j),null,null,v_val_ape1(j)),
                                             decode(v_clie_oid_clie(j),null,null,v_val_ape2(j)),
                                             v_val_punt_emis(j),
                                             v_num_contr(j)                                            
                                           );        
         
      RETURN;
      
    EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR INT_PR_RUV_RECEP_SUBAC: ' || ls_sqlerrm);

  END INT_PR_RUV_RECEP_SUBAC;  
END INT_PKG_RUV;
/
