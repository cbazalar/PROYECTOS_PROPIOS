CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_spvc AS
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  w_filas    NUMBER := 5000;
  existe     BOOLEAN := FALSE;

  /**************************************************************************
  Descripcion       :Validación del País OK
  Fecha Creacion    :28/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de la UA del Cliente (SC610) OK

  Fecha Creacion    : 29/05/2008
  Autor             : Leonardo Lizana Chauca
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_ua_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Dscripcion       : Validación de la Documento de Cruce cliente y periodo OK
  Fecha Creacion    : 29/05/2008
   Autor             : Jose Cairampoma
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_docru_clper
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación del Periodo (SR540) OK
   Fecha Creacion    : 29/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_perio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Devuelve la campaña en que se atendera el CDR
  Fecha Creacion    : 26/06/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvc_perio
  (
    pscodclie VARCHAR2,
    psindexpr VARCHAR2,
    pstipproc VARCHAR2,
    psoidterr NUMBER
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Validación de la Fecha Facturación/Refacturación
                      Solicitud Postventa (SC630) OK
   Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_factu_refac
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de rechazos de SAD del mismo cliente (SC640) OK
   Fecha Creacion    : 09/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_recha_sadmc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Cabecera con  al menos un detalle con error
  Fecha Creacion    : 12/10/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_cande
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Cabecera con  al menos un detalle
  Fecha Creacion    : 03/10/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_cacde
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Cabecera con consultora con BR No Exitosa
  Fecha Creacion    : 05/01/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_consu_brnex
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Indicador de origen OK
  Fecha Creacion    : 16/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_indic_orige
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de rechazos de SAC Bloque de Cliente BR
  Fecha Creacion    : 09/06/2008
  Autor             : Jose A. Cairampoma G.
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_bloqu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de la Documento de Cruce cliente, migracion y periodo OK
  Fecha Creacion    : 15/05/2009
  Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_docru_migra
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de de rechazo por OCR
  Fecha Creacion    : 20/05/2009
  Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de bloqueo solo por BR
   Fecha Creacion    : 29/10/2009
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_blobr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de bloqueo para CDR
   Fecha Creacion    : 29/10/2009
    Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_bload
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de bloqueo para CDR
   Fecha Creacion    : 29/10/2009
    Autor             : Cristhian Roman
   ***************************************************************************/
  FUNCTION sto_fn_spvc_sadmc_bload
  (
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validación de bloqueo solo por financiero
  Fecha Creacion    : 29/10/2009
  Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_blofi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Segundo Dia de Facturacion
  Fecha Creacion    : 26/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_segun_factu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación de Segundo Dia de Facturacion
  Fecha Creacion    : 26/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_spvc_segun_factu
  (
    psoidpais        number,
    psoidperiact     number,
    psoidpericdr     number,
    psoidterr        number
  ) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion       : Validación de Rechazo de CDR
  Fecha Creacion    : 25/05/2012
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recha_cdr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
 /***************************************************************************
    Descripcion       : Validacion para la cabecera de Cdrs
    Fecha Creacion    : 06/08/2012
    Autor             : Sandro Quintana
  ***************************************************************************/  
 PROCEDURE sto_pr_spvc_valid_pedid
  (
    psnumpedi          in VARCHAR2,
    pmensaje          out varchar2
  );
  
  /***************************************************************************
  Descripcion       : Verifica la excepcion de validacion en linea
  Fecha Creacion    : 21/02/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvc_excep_valid
  (
    pscodclie        VARCHAR2,
    pscodorig        VARCHAR2,
    pscodperiACT     VARCHAR2,
    psfechaACT       DATE,
    pstipodoc       VARCHAR2,
    pscodvali        VARCHAR2
  ) RETURN BOOLEAN;  
  
  
END sto_pkg_proce_valid_spvc;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_spvc IS
  /**************************************************************************
  Descripcion       :Validación del País OK
  Fecha Creacion    :28/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_tiposolicitud IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             sp.oid_pais
        FROM seg_pais                    sp,
             int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND sp.cod_pais = cons.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    TYPE t_oid_pais IS TABLE OF int_solic_conso_poven_cabec.oid_pais%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_pais           t_oid_pais;

    lscodclie int_solic_conso_poven_cabec.cod_clie%TYPE;
    lsexiste  VARCHAR2(1);

    i BINARY_INTEGER := 0;
    -- j BINARY_INTEGER := 0;

    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;

  BEGIN

    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_tiposolicitud;
    LOOP
      FETCH c_tiposolicitud BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_pais LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN 1 .. v_codpais.count
        LOOP
          -- Verifica el codigo de cliente
          lscodclie := v_codclie(i);
          IF to_number(lscodclie) <> 0 THEN
            -- Buscar si el codigo de cliente existe
            lsexiste := 'N';
            BEGIN
              SELECT 'S' INTO lsexiste FROM mae_clien mae WHERE mae.cod_clie = v_codclie(i);
            EXCEPTION
              WHEN no_data_found THEN
                lsexiste := 'N';
            END;

            IF lsexiste = 'N' THEN
              -- Buscar por documento si el codigo de cliente si no existe
              BEGIN
                SELECT mae.cod_clie
                  INTO lscodclie
                  FROM mae_clien_ident mid,
                       mae_clien       mae,
                       mae_tipo_docum  tdoc
                 WHERE mid.num_docu_iden = v_codclie(i)
                   AND mid.clie_oid_clie = mae.oid_clie
                   AND mid.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
                   AND tdoc.cod_tipo_docu = '01';
                ---and rownum = 1;
              EXCEPTION
                WHEN no_data_found THEN
                  lscodclie := v_codclie(i);
              END;
            END IF;
          END IF;
          -- Actualizamos CAMPOS ADICIONALES
          --FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_poven_cabec
             SET oid_pais = v_oid_pais(i),
                 cod_clie = lscodclie
           WHERE cod_pais = v_codpais(i)
             AND num_lote = v_numlote(i)
             AND sec_nume_docu = v_sec_nume_docu(i);

          /*AND cod_clie = v_codclie(i)
          AND cod_peri = v_codperi(i)*/

          -- Actualizamos CAMPOS ADICIONALES en el detalle (SQA)
          -- FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_poven_detal
             SET cod_clie = lscodclie
           WHERE cod_pais = v_codpais(i)
             AND num_lote = v_numlote(i)
             AND sec_nume_docu IN (SELECT sec_nume_docu
                                     FROM sto_docum_digit
                                    WHERE cod_tipo_docu = lscodigodocumentodetalle
                                      AND sec_nume_docu_cabe = v_sec_nume_docu(i));

          -- Actualizamos Documentos Validados OK
          -- FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE,
                 occ.cod_clie           = lscodclie
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

          --IF (lsinddepecabe = 'S') THEN
          -- Actualizamos Detalles Validados OK
          -- FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_clie = lscodclie
           WHERE occ.cod_tipo_docu = lscodigodocumentodetalle
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu_cabe = v_sec_nume_docu(i);
          --END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_tiposolicitud%NOTFOUND;
    END LOOP;
    CLOSE c_tiposolicitud;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_PAIS: ' || ls_sqlerrm);

  END sto_pr_spvc_pais;

  /***************************************************************************
  Descripcion       : Validación de la UA del Cliente (SC610) OK
  Fecha Creacion    : 29/05/2008
  Autor             : Leonardo Lizana Chauca
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_ua_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadadminist IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             mcua.ztad_oid_terr_admi,
             zzon.cod_zona,
             zreg.cod_regi
        FROM mae_clien_unida_admin       mcua,
             mae_clien                   mc,
             int_solic_conso_poven_cabec cons,
             zon_terri_admin             zta,
             zon_secci                   zsec,
             zon_zona                    zzon,
             zon_regio                   zreg,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND mcua.clie_oid_clie = mc.oid_clie
         AND mc.cod_clie = cons.cod_clie
         AND mcua.perd_oid_peri_fin IS NULL
         AND zta.oid_terr_admi = mcua.ztad_oid_terr_admi
         AND zta.zscc_oid_secc = zsec.oid_secc
         AND zsec.zzon_oid_zona = zzon.oid_zona
         AND zzon.zorg_oid_regi = zreg.oid_regi;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    TYPE t_ztad_oid_terr_admi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_cod_zona_arri IS TABLE OF int_solic_conso_poven_cabec.cod_zona_arri%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_solic_conso_poven_cabec.cod_regi_arri%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_ztad_oid_terr_admi t_ztad_oid_terr_admi;
    v_cod_zona_arri      t_cod_zona_arri;
    v_cod_regi_arri      t_cod_regi_arri;

    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;

    j BINARY_INTEGER := 0;

  BEGIN

    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_unidadadminist;
    LOOP
      FETCH c_unidadadminist BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_ztad_oid_terr_admi,
             v_cod_zona_arri,
             v_cod_regi_arri LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_poven_cabec
             SET ztad_oid_terr_admi = v_ztad_oid_terr_admi(i),
                 cod_zona_arri      = v_cod_zona_arri(i),
                 cod_regi_arri      = v_cod_regi_arri(i)
           WHERE cod_pais = v_codpais(i)
             AND cod_peri = v_codperi(i)
             AND cod_clie = v_codclie(i)
             AND num_lote = v_numlote(i);

        FORALL k IN 1 .. v_codpais.count
          UPDATE int_solic_conso_poven_detal
             SET cod_zona = v_cod_zona_arri(k),
                 cod_regi = v_cod_regi_arri(k)
           WHERE cod_pais = v_codpais(k)
             AND cod_peri = v_codperi(k)
             AND cod_clie = v_codclie(k)
             AND num_lote = v_numlote(k);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE,
                 occ.cod_regi           = v_cod_regi_arri(j),
                 occ.cod_zona           = v_cod_zona_arri(j)
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        -- Se agrega actualizacion a nivel de detalle
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_regi = v_cod_regi_arri(j),
                 occ.cod_zona = v_cod_zona_arri(j)
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = lscodigodocumentodetalle
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu_cabe = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_unidadadminist%NOTFOUND;

    END LOOP;
    CLOSE c_unidadadminist;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_UA_CLIEN: ' || ls_sqlerrm);

  END sto_pr_spvc_ua_clien;

  /***************************************************************************
  Descripcion       : Validación de la Documento de Cruce cliente y periodo OK

  Fecha Creacion    : 29/05/2008

  Autor             : Jose Cairampoma
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_docru_clper
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadadminist IS
      SELECT cons.cod_pais,
             cons.cod_peri, --4
             cons.cod_clie, --5
             cons.num_lote,

             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,

             cons.num_docu,
             cons.num_docu_cruc, --6
             cons.cod_sub_acce --8
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_docu_cruc IS NOT NULL
         AND cons.cod_clie IS NOT NULL;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    TYPE t_num_docu IS TABLE OF int_solic_conso_poven_cabec.num_docu%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_cod_sub_acce IS TABLE OF int_solic_conso_poven_cabec.cod_sub_acce%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_num_docu      t_num_docu;
    v_num_docu_cruc t_num_docu_cruc;
    v_cod_sub_acce  t_cod_sub_acce;

    --DATOS PARA INSERCION 2

    lnoidsolicabe     ped_solic_cabec.oid_soli_cabe%TYPE;
    lscodclie         mae_clien.cod_clie%TYPE;
    ldfecfact         ped_solic_cabec.fec_fact%TYPE;
    lnoidcliente      mae_clien.oid_clie%TYPE;
    lntidooidtipodocu ped_solic_cabec.tido_oid_tipo_docu%TYPE;
    lnticloidtipoclie ped_solic_cabec.ticl_oid_tipo_clie%TYPE;
    lnsbtioidsubtclie ped_solic_cabec.sbti_oid_subt_clie%TYPE;
    lscodperi         seg_perio_corpo.cod_peri%TYPE;
    lnoidperi         cra_perio.oid_peri%TYPE;
    lnestaoidestaclie mae_clien_datos_adici.esta_oid_esta_clie%TYPE;
    lnoidindirevi     ped_solic_cabec.inre_oid_indi_revi%TYPE;
    lsvalor           VARCHAR2(20) := '0';

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_unidadadminist;
    LOOP
      FETCH c_unidadadminist BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_docu,
             v_num_docu_cruc,
             v_cod_sub_acce LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe  := TRUE;
          lsvalor := v_codclie(i);
          BEGIN

            SELECT a.oid_soli_cabe,
                   b.cod_clie,
                   a.fec_fact,
                   b.oid_clie,
                   a.tido_oid_tipo_docu,
                   a.ticl_oid_tipo_clie,
                   a.sbti_oid_subt_clie,
                   d.cod_peri,
                   c.oid_peri,
                   f.esta_oid_esta_clie,
                   a.inre_oid_indi_revi
              INTO lnoidsolicabe,
                   lscodclie,
                   ldfecfact,
                   lnoidcliente,
                   lntidooidtipodocu,
                   lnticloidtipoclie,
                   lnsbtioidsubtclie,
                   lscodperi,
                   lnoidperi,
                   lnestaoidestaclie,
                   lnoidindirevi
              FROM ped_solic_cabec       a,
                   mae_clien             b,
                   cra_perio             c,
                   seg_perio_corpo       d,
                   seg_subac             e,
                   mae_clien_datos_adici f,
                   ped_tipo_solic_pais   tsp,
                   ped_tipo_solic        ts
             WHERE a.clie_oid_clie = b.oid_clie
               AND a.perd_oid_peri = c.oid_peri
               AND c.peri_oid_peri = d.oid_peri
               AND a.sbac_oid_sbac = e.oid_sbac
               AND a.val_nume_soli = v_num_docu_cruc(i)
               AND e.cod_sbac = v_cod_sub_acce(i)
               AND a.ind_ts_no_conso = 0
               AND f.clie_oid_clie = b.oid_clie
               AND a.soca_oid_soli_cabe IS NULL
               AND b.cod_clie = v_codclie(i)
               AND a.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
               AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
               AND ts.ind_soli_nega = 0
               AND ts.ind_cons = 1;

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
          END;
          IF (existe) THEN

            UPDATE int_solic_conso_poven_cabec
               SET oid_peri_refe      = lnoidperi,
                   oid_clie           = lnoidcliente,
                   oid_cabe           = lnoidsolicabe,
                   sbti_oid_subt_clie = lnsbtioidsubtclie,
                   ticl_oid_tipo_clie = lnticloidtipoclie,
                   fec_refe           = ldfecfact,
                   tido_oid_tipo_docu = lntidooidtipodocu
             WHERE cod_pais = v_codpais(i)
               AND cod_peri = v_codperi(i)
               AND cod_clie = v_codclie(i)
               AND num_lote = v_numlote(i)
               AND num_docu = v_num_docu(i);

            UPDATE int_solic_conso_poven_detal
               SET esta_oid_esta_clie = lnestaoidestaclie,
                   val_revi_cheq      = lnoidindirevi
             WHERE cod_pais = v_codpais(i)
               AND cod_peri = v_codperi(i)
               AND cod_clie = v_codclie(i)
               AND num_lote = v_numlote(i)
               AND num_docu = v_num_docu(i);

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_unidadadminist%NOTFOUND;

    END LOOP;
    CLOSE c_unidadadminist;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_DOCRU: ' || lsvalor || ls_sqlerrm);

  END sto_pr_spvc_docru_clper;

  /***************************************************************************
  Descripcion       : Validación del Periodo (SR540) OK

  Fecha Creacion    : 29/05/2008

  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_perio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cp.oid_peri,
             cons.ztad_oid_terr_admi,
             cons.ind_expr
        FROM cra_perio                   cp,
             seg_perio_corpo             seg,
             int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
      --sto_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
            --AND cp.fec_inic <= trunc(SYSDATE) --cons.fec_proc_docu
            --AND cp.fec_fina >= trunc(SYSDATE) --cons.fec_proc_docu
         AND cp.pais_oid_pais = cons.oid_pais
         AND cp.peri_oid_peri = seg.oid_peri
         AND seg.cod_peri = cons.cod_peri;

    /*  AND NOT EXISTS
    (SELECT *
             FROM fac_contr_cierr
            WHERE tcie_oid_tipo_cier = 2
              AND perd_oid_peri = cp.oid_peri
              AND zzon_oid_zona =
                  (SELECT c.oid_zona
                     FROM zon_terri_admin a,
                          zon_secci       b,
                          zon_zona        c
                    WHERE a.zscc_oid_secc = b.oid_secc
                      AND b.zzon_oid_zona = c.oid_zona
                      AND a.oid_terr_admi =
                          cons.ztad_oid_terr_admi));*/

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF cra_perio.oid_peri%TYPE;
    TYPE t_ztad_oid_terr_admi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_ind_expr IS TABLE OF int_solic_conso_poven_cabec.ind_expr%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_peri           t_oid_peri;
    l_oid_peri_zona     NUMBER := 0;
    l_oid_peri_region   NUMBER := 0;
    l_oid_peri_mayor    NUMBER := 0;
    v_ztad_oid_terr_admi t_ztad_oid_terr_admi;
    v_ind_expr           t_ind_expr;
    lsCruceCampana       sto_param_gener_occrr.val_param%TYPE;

    cuenta_cierre  NUMBER := 0;
    cuenta_cierre1 NUMBER := 0;
    cuenta_cierre2 NUMBER := 0;
    cuenta_pedido  NUMBER := 0;
    oid_peri_next  NUMBER := 0;
    oid_peri_next1 NUMBER := 0;
    l_oid_peri     NUMBER := 0;
    r_oid_peri     NUMBER := 0;

  BEGIN

    SELECT c.oid_peri
      INTO l_oid_peri
      FROM bas_ctrl_fact   a,
           seg_perio_corpo b,
           cra_perio       c
     WHERE a.cod_peri = b.cod_peri
       AND b.oid_peri = c.peri_oid_peri
       AND a.sta_camp = 0
       AND a.ind_camp_act = 1;

    lsCruceCampana := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CRUCE_CAMP');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_peri,
             v_ztad_oid_terr_admi,
             v_ind_expr LIMIT w_filas;

      IF (v_codpais.count > 0) THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          cuenta_cierre := 0;
          cuenta_pedido := 0;
          -- Si es CDR Express (sale el mismo día)
          IF (v_ind_expr(i) = 1) THEN

            UPDATE int_solic_conso_poven_cabec
               SET oid_peri      = l_oid_peri, --v_oid_peri(i),
                   oid_peri_recl = l_oid_peri, --v_oid_peri(i),
                   fec_proc_docu = trunc(SYSDATE)
             WHERE cod_pais = v_codpais(i)
               AND cod_peri = v_codperi(i)
               AND cod_clie = v_codclie(i)
               AND num_lote = v_numlote(i);

          ELSE
             IF (lsCruceCampana = '1') THEN

                --- Obtiene ultima campaña que cerro la zona de la consultora
                SELECT nvl(max(perd_oid_peri),0) INTO l_oid_peri_zona
                  FROM fac_contr_cierr
                 WHERE tcie_oid_tipo_cier = 2
                   AND zzon_oid_zona = (SELECT c.oid_zona
                                          FROM zon_terri_admin a,
                                               zon_secci       b,
                                               zon_zona        c
                                         WHERE a.zscc_oid_secc = b.oid_secc
                                           AND b.zzon_oid_zona = c.oid_zona
                                           AND a.oid_terr_admi = v_ztad_oid_terr_admi(i));          
                
                --- Obtiene ultima campaña que cerro la region de la consultora
                SELECT nvl(max(perd_oid_peri),0) INTO l_oid_peri_region
                  FROM fac_contr_cierr
                 WHERE tcie_oid_tipo_cier = 1
                   AND zorg_oid_regi = (SELECT d.oid_regi
                                          FROM zon_terri_admin a,
                                               zon_secci       b,
                                               zon_zona        c,
                                               zon_regio       d
                                         WHERE a.zscc_oid_secc = b.oid_secc
                                           AND b.zzon_oid_zona = c.oid_zona
                                           AND d.oid_regi = c.zorg_oid_regi
                                           AND a.oid_terr_admi = v_ztad_oid_terr_admi(i));

                --- Verifica cual es el periodo mayor
                l_oid_peri_mayor := l_oid_peri_region; 
                if l_oid_peri_zona > l_oid_peri_region then
                   l_oid_peri_mayor := l_oid_peri_zona; 
                end if;
                
                --- Si no hay cierre de area o region en el caso de qe sea un nuevo UA toma el actual
                if (l_oid_peri_mayor = 0 ) then
                   r_oid_peri := l_oid_peri;
                else
                  -- Obtiene el siguiente periodo 
                  SELECT oid_peri
                    INTO oid_peri_next1
                    FROM (SELECT oid_peri
                            FROM cra_perio
                           WHERE fec_inic > (SELECT fec_inic FROM cra_perio WHERE oid_peri = l_oid_peri_mayor)
                           ORDER BY fec_inic ASC)
                   WHERE rownum = 1;

                   -- Validamos si la consultora paso pedido en el nuevo periodo
                   SELECT COUNT(1) INTO cuenta_pedido
                     FROM ped_solic_cabec a,
                          ped_solic_cabec cons,
                          mae_clien       mc
                    WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe
                      AND a.clie_oid_clie = mc.oid_clie
                      AND a.ind_oc = 1
                      AND a.ind_ts_no_conso = 1
                      AND a.perd_oid_peri = oid_peri_next1 --v_oid_peri(i)
                      AND cons.val_tota_paga_loca > 0
                      AND a.esso_oid_esta_soli IN (1,
                                                   5)
                      AND mc.cod_clie = v_codclie(i);
                      
                  -- Si la consultora no paso pedido, se le asigna el periodo encontrado, caso contrario el sgte

                  IF (cuenta_pedido = 0) THEN

                    r_oid_peri := oid_peri_next1;

                  ELSE

                    SELECT oid_peri INTO oid_peri_next
                      FROM (SELECT oid_peri FROM cra_perio
                             WHERE fec_inic > (SELECT fec_inic FROM cra_perio WHERE oid_peri = oid_peri_next1)
                             ORDER BY fec_inic ASC)
                     WHERE rownum = 1;

                    r_oid_peri := oid_peri_next;

                  END IF;
               
               END IF;
               
               --- Actualiza el valor del periodo
               UPDATE int_solic_conso_poven_cabec
                  SET oid_peri      = r_oid_peri, --v_oid_peri(i),
                      oid_peri_recl = r_oid_peri, --v_oid_peri(i),
                      fec_proc_docu = trunc(SYSDATE)
                WHERE cod_pais = v_codpais(i)
                  AND cod_peri = v_codperi(i)
                  AND cod_clie = v_codclie(i)
                  AND num_lote = v_numlote(i);
             
             ELSE
            -- Validamos si hubo cierre de zona
                SELECT COUNT(1) INTO cuenta_cierre1
              FROM fac_contr_cierr
             WHERE tcie_oid_tipo_cier = 2
               AND perd_oid_peri = l_oid_peri --v_oid_peri(i)
               AND zzon_oid_zona =
                   (SELECT c.oid_zona
                      FROM zon_terri_admin a,
                           zon_secci       b,
                           zon_zona        c
                     WHERE a.zscc_oid_secc = b.oid_secc
                       AND b.zzon_oid_zona = c.oid_zona
                       AND a.oid_terr_admi = v_ztad_oid_terr_admi(i));

            -- Validamos si hubo cierre de region
                SELECT COUNT(1) INTO cuenta_cierre2
              FROM fac_contr_cierr
             WHERE tcie_oid_tipo_cier = 1
               AND perd_oid_peri = l_oid_peri --v_oid_peri(i)
               AND zorg_oid_regi =
                   (SELECT d.oid_regi
                      FROM zon_terri_admin a,
                           zon_secci       b,
                           zon_zona        c,
                           zon_regio       d
                     WHERE a.zscc_oid_secc = b.oid_secc
                       AND b.zzon_oid_zona = c.oid_zona
                       AND d.oid_regi = c.zorg_oid_regi
                       AND a.oid_terr_admi = v_ztad_oid_terr_admi(i));

            cuenta_cierre := cuenta_cierre2 + cuenta_cierre1;

            IF (cuenta_cierre = 0) THEN
              -- Validamos si la consultora paso pedido
                  SELECT COUNT(1) INTO cuenta_pedido
                FROM ped_solic_cabec a,
                     ped_solic_cabec cons,
                     mae_clien       mc
               WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe
                 AND a.clie_oid_clie = mc.oid_clie
                 AND a.ind_oc = 1
                 AND a.ind_ts_no_conso = 1
                 AND a.perd_oid_peri = l_oid_peri --v_oid_peri(i)
                 AND cons.val_tota_paga_loca > 0
                 AND a.esso_oid_esta_soli IN (1,
                                              5)
                 AND mc.cod_clie = v_codclie(i);
            END IF;

            -- Si la consultora no paso pedido, no ha cerrado su zona ni su region
            -- entonces al CDR se le asigna el periodo actual, caso contrario el sgte
            IF (cuenta_cierre = 0 AND cuenta_pedido = 0) THEN

              UPDATE int_solic_conso_poven_cabec
                 SET oid_peri      = l_oid_peri, --v_oid_peri(i),
                     oid_peri_recl = l_oid_peri, --v_oid_peri(i),
                     fec_proc_docu = trunc(SYSDATE)
               WHERE cod_pais = v_codpais(i)
                 AND cod_peri = v_codperi(i)
                 AND cod_clie = v_codclie(i)
                 AND num_lote = v_numlote(i);

            ELSE

                  SELECT oid_peri INTO oid_peri_next
                FROM (SELECT oid_peri
                        FROM cra_perio
                       WHERE fec_inic > (SELECT fec_inic FROM cra_perio WHERE oid_peri = l_oid_peri)
                       ORDER BY fec_inic ASC)
               WHERE rownum = 1;

              UPDATE int_solic_conso_poven_cabec
                 SET oid_peri      = oid_peri_next,
                     oid_peri_recl = oid_peri_next,
                     fec_proc_docu = trunc(SYSDATE)
               WHERE cod_pais = v_codpais(i)
                 AND cod_peri = v_codperi(i)
                 AND cod_clie = v_codclie(i)
                 AND num_lote = v_numlote(i);

            END IF;
            
             END IF;
            
          END IF;
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(i)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;

    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_PERIODO: ' || ls_sqlerrm);

  END sto_pr_spvc_perio;

  /***************************************************************************
  Descripcion       : FUNCION Devuelve la campaña en que se atendera el CDR
  Fecha Creacion    : 26/06/2012
  Autor             : Sandro Quintana Aponte
                      pstipproc = 'L'   en linea
                                = 'S'   por STO

              OUTPUT  devuelve el oid del periodo en que se procesara el reclamo, si es cero es error
  ***************************************************************************/
  FUNCTION sto_fn_spvc_perio
  (
    pscodclie VARCHAR2,
    psindexpr VARCHAR2,
    pstipproc VARCHAR2,
    psoidterr NUMBER
  ) RETURN NUMBER IS

    cuenta_cierre  NUMBER := 0;
    cuenta_cierre1 NUMBER := 0;
    cuenta_cierre2 NUMBER := 0;
    cuenta_pedido  NUMBER := 0;
    oid_peri_next  NUMBER := 0;
    oid_peri_next1 NUMBER := 0;
    l_oid_peri     NUMBER := 0;
    r_oid_peri     NUMBER := 0;
    l_oid_peri_zona     NUMBER := 0;
    l_oid_peri_region   NUMBER := 0;
    l_oid_peri_mayor    NUMBER := 0;
    pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;
    lsCruceCampana   sto_param_gener_occrr.val_param%TYPE;

    v_ztad_oid_terr_admi NUMBER := 0;

  BEGIN

    select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

    lsCruceCampana := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CRUCE_CAMP');

    ---- Periodo actual
    SELECT c.oid_peri
      INTO l_oid_peri
      FROM bas_ctrl_fact   a,
           seg_perio_corpo b,
           cra_perio       c
     WHERE a.cod_peri = b.cod_peri
       AND b.oid_peri = c.peri_oid_peri
       AND a.sta_camp = 0
       AND a.ind_camp_act = 1;

    ---- Oid del territorio
    IF pstipproc = 'S' THEN
      --- Si es desde STO se toma el oid enviado
      v_ztad_oid_terr_admi := psoidterr;
    ELSE
      SELECT mcua.ztad_oid_terr_admi
        INTO v_ztad_oid_terr_admi
        FROM mae_clien_unida_admin mcua,
             mae_clien             mc
       WHERE mcua.clie_oid_clie = mc.oid_clie
         AND mcua.perd_oid_peri_fin IS NULL
         AND mc.cod_clie = pscodclie
         AND rownum = 1;
    END IF;

    cuenta_cierre := 0;
    cuenta_pedido := 0;

    -- Si es CDR Express (sale el mismo día)
    IF (psindexpr = 1) THEN

      r_oid_peri := l_oid_peri;

    ELSE
       --- Si es cruce de campaña
       IF (lsCruceCampana = '1') THEN
          --- Obtiene ultima campaña que cerro la zona de la consultora
          SELECT nvl(max(perd_oid_peri),0) INTO l_oid_peri_zona
            FROM fac_contr_cierr
           WHERE tcie_oid_tipo_cier = 2
             AND zzon_oid_zona = (SELECT c.oid_zona
                                    FROM zon_terri_admin a,
                                         zon_secci       b,
                                         zon_zona        c
                                   WHERE a.zscc_oid_secc = b.oid_secc
                                     AND b.zzon_oid_zona = c.oid_zona
                                     AND a.oid_terr_admi = v_ztad_oid_terr_admi);          
          
          --- Obtiene ultima campaña que cerro la region de la consultora
          SELECT nvl(max(perd_oid_peri),0) INTO l_oid_peri_region
            FROM fac_contr_cierr
           WHERE tcie_oid_tipo_cier = 1
             AND zorg_oid_regi = (SELECT d.oid_regi
                                    FROM zon_terri_admin a,
                                         zon_secci       b,
                                         zon_zona        c,
                                         zon_regio       d
                                   WHERE a.zscc_oid_secc = b.oid_secc
                                     AND b.zzon_oid_zona = c.oid_zona
                                     AND d.oid_regi = c.zorg_oid_regi
                                     AND a.oid_terr_admi = v_ztad_oid_terr_admi);

          --- Verifica cual es el periodo mayor
          l_oid_peri_mayor := l_oid_peri_region; 
          if l_oid_peri_zona > l_oid_peri_region then
             l_oid_peri_mayor := l_oid_peri_zona; 
          end if;
          
          --- Si no hay cierre de area o region en el caso de qe sea un nuevo UA toma el actual
          if (l_oid_peri_mayor = 0 ) then
             r_oid_peri := l_oid_peri;
          else
            -- Obtiene el siguiente periodo 
            SELECT oid_peri
              INTO oid_peri_next1
              FROM (SELECT oid_peri
                      FROM cra_perio
                     WHERE fec_inic > (SELECT fec_inic FROM cra_perio WHERE oid_peri = l_oid_peri_mayor)
                     ORDER BY fec_inic ASC)
             WHERE rownum = 1;

             -- Validamos si la consultora paso pedido en el nuevo periodo
             SELECT COUNT(1) INTO cuenta_pedido
               FROM ped_solic_cabec a,
                    ped_solic_cabec cons,
                    mae_clien       mc
              WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe
                AND a.clie_oid_clie = mc.oid_clie
                AND a.ind_oc = 1
                AND a.ind_ts_no_conso = 1
                AND a.perd_oid_peri = oid_peri_next1 --v_oid_peri(i)
                AND cons.val_tota_paga_loca > 0
                AND a.esso_oid_esta_soli IN (1,
                                             5)
                AND mc.cod_clie = pscodclie;
                
            -- Si la consultora no paso pedido, se le asigna el periodo encontrado, caso contrario el sgte

            IF (cuenta_pedido = 0) THEN

              r_oid_peri := oid_peri_next1;

            ELSE

              SELECT oid_peri INTO oid_peri_next
                FROM (SELECT oid_peri FROM cra_perio
                       WHERE fec_inic > (SELECT fec_inic FROM cra_perio WHERE oid_peri = oid_peri_next1)
                       ORDER BY fec_inic ASC)
               WHERE rownum = 1;

              r_oid_peri := oid_peri_next;

            END IF;
            
          end if;          
       ELSE
      -- Validamos si hubo cierre de zona
          SELECT COUNT(1) INTO cuenta_cierre1
        FROM fac_contr_cierr
       WHERE tcie_oid_tipo_cier = 2
         AND perd_oid_peri = l_oid_peri --v_oid_peri(i)
         AND zzon_oid_zona = (SELECT c.oid_zona
                                FROM zon_terri_admin a,
                                     zon_secci       b,
                                     zon_zona        c
                               WHERE a.zscc_oid_secc = b.oid_secc
                                 AND b.zzon_oid_zona = c.oid_zona
                                 AND a.oid_terr_admi = v_ztad_oid_terr_admi);

      -- Validamos si hubo cierre de region
          SELECT COUNT(1) INTO cuenta_cierre2
        FROM fac_contr_cierr
       WHERE tcie_oid_tipo_cier = 1
         AND perd_oid_peri = l_oid_peri --v_oid_peri(i)
         AND zorg_oid_regi = (SELECT d.oid_regi
                                FROM zon_terri_admin a,
                                     zon_secci       b,
                                     zon_zona        c,
                                     zon_regio       d
                               WHERE a.zscc_oid_secc = b.oid_secc
                                 AND b.zzon_oid_zona = c.oid_zona
                                 AND d.oid_regi = c.zorg_oid_regi
                                 AND a.oid_terr_admi = v_ztad_oid_terr_admi);

      cuenta_cierre := cuenta_cierre2 + cuenta_cierre1;

      IF (cuenta_cierre = 0) THEN
        -- Validamos si la consultora paso pedido
            SELECT COUNT(1) INTO cuenta_pedido
          FROM ped_solic_cabec a,
               ped_solic_cabec cons,
               mae_clien       mc
         WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe
           AND a.clie_oid_clie = mc.oid_clie
           AND a.ind_oc = 1
           AND a.ind_ts_no_conso = 1
           AND a.perd_oid_peri = l_oid_peri --v_oid_peri(i)
           AND cons.val_tota_paga_loca > 0
           AND a.esso_oid_esta_soli IN (1,
                                        5)
           AND mc.cod_clie = pscodclie;
      END IF;
      -- Si la consultora no paso pedido, no ha cerrado su zona ni su region
      -- entonces al CDR se le asigna el periodo actual, caso contrario el sgte
      IF (cuenta_cierre = 0 AND cuenta_pedido = 0) THEN

        r_oid_peri := l_oid_peri;

      ELSE

            SELECT oid_peri INTO oid_peri_next
          FROM (SELECT oid_peri
                  FROM cra_perio
                 WHERE fec_inic > (SELECT fec_inic FROM cra_perio WHERE oid_peri = l_oid_peri)
                 ORDER BY fec_inic ASC)
         WHERE rownum = 1;

        r_oid_peri := oid_peri_next;

      END IF;

       END IF;

    END IF;

    RETURN(r_oid_peri);

  END sto_fn_spvc_perio;

  /***************************************************************************
  Descripcion       : Validación de la Fecha Facturación/Refacturación
                      Solicitud Postventa (SC630) OK

  Fecha Creacion    : 30/05/2008


  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_factu_refac
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_peri,
             cons.oid_clie
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND ((cons.ind_expr = 1) OR
             ((cons.ind_expr IS NULL OR cons.ind_expr = 0) AND
             (NOT EXISTS (SELECT a.clie_oid_clie
                              FROM ped_solic_cabec a,
                                   ped_solic_cabec cons
                             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe
                               AND a.ind_oc = 1
                               AND a.ind_ts_no_conso = 1
                               AND a.perd_oid_peri = cons.oid_peri
                               AND cons.val_tota_paga_loca > 0
                               AND a.esso_oid_esta_soli IN (1,
                                                            5)
                               AND a.clie_oid_clie = cons.oid_clie))));

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri%TYPE;
    TYPE t_oid_clien IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_oid_peri  t_oid_peri;
    v_oid_clien t_oid_clien;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_peri,
             v_oid_clien LIMIT w_filas;

      IF (v_codpais.count > 0) THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_FACTU_REFAC: ' || ls_sqlerrm);

  END sto_pr_spvc_factu_refac;

  /***************************************************************************
  Descripcion       : Validación de rechazos de SAD del mismo cliente (SC640) OK

  Fecha Creacion    : 09/06/2008

  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_recha_sadmc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_peri,
             cons.oid_clie
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS (SELECT mtb.cod_tipo_bloq
                FROM mae_clien_bloqu mcb,
                     mae_tipo_bloqu  mtb
               WHERE mcb.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                 AND mcb.clie_oid_clie = cons.oid_clie
                 AND mtb.cod_tipo_bloq IN ('02',
                                           '01')
                 AND mcb.fec_desb IS NULL);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_oid_peri t_oid_peri;
    v_oid_clie t_oid_clie;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_peri,
             v_oid_clie LIMIT w_filas;

      IF (v_codpais.count > 0) THEN
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_RECHA_SADMC: ' || ls_sqlerrm);

  END sto_pr_spvc_recha_sadmc;

  /***************************************************************************
  Descripcion       : Validación de Cabecera con  al menos un detalle con error
                      Valida que todas las cabeceras tengan todos sus detalles
                      validos.
  Fecha Creacion    : 12/10/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_cande
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validacion(vscodigodocumentodetalle VARCHAR2) IS

      SELECT occ.num_lote,
             occ.sec_nume_docu
        FROM sto_tmp_docum_digit occ
       WHERE occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND 0 = (SELECT COUNT(1)
                    FROM sto_docum_digit       occ2,
                         sto_detal_docum_excep exc
                   WHERE occ2.cod_pais = exc.cod_pais
                     AND occ2.cod_tipo_docu = exc.cod_tipo_docu
                     AND occ2.sec_nume_docu = exc.sec_nume_docu
                     AND occ2.num_lote = exc.num_lote
                     AND occ2.sec_nume_docu_cabe = occ.sec_nume_docu
                     AND occ2.num_lote = occ.num_lote
                     AND occ2.cod_tipo_docu = vscodigodocumentodetalle
                     AND occ2.cod_pais = occ.cod_pais
                     AND occ2.ind_envi = '0' --No enviados
                     AND occ2.ind_rech = '0'
                     AND exc.ind_apro = '0');

    TYPE t_numlote IS TABLE OF int_solic_conso_poven_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    i BINARY_INTEGER := 0;

    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;

  BEGIN

    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validacion(TRIM(lscodigodocumentodetalle));
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_occ_cande: ' || ls_sqlerrm);

  END sto_pr_spvc_cande;

  /***************************************************************************
  Descripcion       : Validación de Cabecera tenga al menos un detalle valido
  Fecha Creacion    : 03/10/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_cacde
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor(vscodigodocumentodetalle VARCHAR2) IS
      SELECT occ.num_lote,
             occ.sec_nume_docu
        FROM sto_tmp_docum_digit occ
       WHERE occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND EXISTS (SELECT 1
                FROM sto_docum_digit occ2
               WHERE occ2.sec_nume_docu_cabe = occ.sec_nume_docu
                 AND occ2.num_lote = occ.num_lote
                 AND occ2.cod_tipo_docu = vscodigodocumentodetalle
                 AND occ2.cod_pais = occ.cod_pais
                 AND occ2.ind_envi = '0' --No enviados
                 AND occ2.ind_rech = '0');

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;

  BEGIN

    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor(lscodigodocumentodetalle);
    LOOP

      FETCH c_cursor BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      -- Actualizamos CAMPOS ADICIONALES
      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_CACDE: ' || ls_sqlerrm);
  END sto_pr_spvc_cacde;

  /***************************************************************************
  Descripcion       : Validación de Cabecera con consultora con BR No Exitosa
  Fecha Creacion    : 05/01/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_consu_brnex
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_clie
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_clie           t_oid_clie;

    ls_oid_tipo_bloq mae_tipo_bloqu.oid_tipo_bloq%TYPE;
    contador         NUMBER := 0;
    existe           BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_clie LIMIT w_filas;

      IF (v_codpais.count > 0) THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe := FALSE;
          BEGIN
            contador := 0;
            SELECT oid_tipo_bloq
              INTO ls_oid_tipo_bloq
              FROM mae_tipo_bloqu
             WHERE cod_tipo_bloq = 'BR';

            SELECT COUNT(*)
              INTO contador
              FROM mae_clien_bloqu
             WHERE clie_oid_clie = v_oid_clie(i)
               AND fec_desb IS NULL
               AND tibq_oid_tipo_bloq = ls_oid_tipo_bloq;

            IF (contador > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;

    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_CONSU_BRNEX: ' || ls_sqlerrm);

  END sto_pr_spvc_consu_brnex;

  /***************************************************************************
    Descripcion       : Validación de Indicador de origen OK
    Fecha Creacion    : 16/04/2009
    Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_indic_orige
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor(vsnumerolote VARCHAR2) IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.num_docu,
             cons.ind_orig
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_lote = vsnumerolote;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_poven_cabec.num_docu%TYPE;
    TYPE t_ind_orig IS TABLE OF int_solic_conso_poven_cabec.ind_orig%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_docu           t_num_docu;
    v_ind_orig           t_ind_orig;

    contador     NUMBER := 0;
    numero       NUMBER := 0;
    existe       BOOLEAN := TRUE;
    verifica     BOOLEAN := TRUE;
    lsnumerolote sto_tipo_docum_digit.num_lote%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsnumerolote := sto_pkg_gener.sto_fn_devue_nume_lote(pscodigopais,
                                                         pscodigotipodoc);

    OPEN c_cursor(lsnumerolote);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_docu,
             v_ind_orig LIMIT w_filas;

      IF (v_codpais.count > 0) THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_ind_orig(i) = '1') THEN

            existe := FALSE;
            BEGIN
              contador := 0;
              SELECT COUNT(*)
                INTO contador
                FROM int_solic_conso_poven_cabec c
               WHERE c.num_lote < lsnumerolote
                 AND c.cod_clie = v_codclie(i)
                 AND c.num_docu = v_num_docu(i)
                 AND c.cod_peri = v_codperi(i)
                 AND c.cod_pais = v_codpais(i);

              IF (contador > 0) THEN
                existe := FALSE;

              ELSE
                existe := TRUE;
              END IF;

            EXCEPTION
              WHEN no_data_found THEN
                existe := TRUE;

            END;

          ELSE
            verifica := FALSE;
            BEGIN

              numero := 0;

              SELECT COUNT(*)
                INTO numero
                FROM int_solic_conso_poven_cabec c,
                     sto_docum_digit             s
               WHERE c.num_lote < lsnumerolote
                 AND c.cod_clie = v_codclie(i)
                 AND c.num_docu = v_num_docu(i)
                 AND c.cod_peri = v_codperi(i)
                 AND c.cod_pais = v_codpais(i)
                 AND c.sec_nume_docu = s.sec_nume_docu
                 AND s.cod_tipo_docu = 'SPVC'
                 AND s.ind_rech = 0
                 AND s.ind_envi = 0;

              IF (numero > 0) THEN
                verifica := FALSE;

              ELSE
                verifica := TRUE;
              END IF;

            EXCEPTION
              WHEN no_data_found THEN
                verifica := TRUE;

            END;

          END IF;
          IF (existe AND verifica) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;

    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_INDIC_ORIGE: ' || ls_sqlerrm);

  END sto_pr_spvc_indic_orige;

  /***************************************************************************
  Descripcion       : Validación de rechazos de SAC Bloque de Cliente BR
  Fecha Creacion    : 09/06/2008

  Autor             : Jose A. Cairampoma G.
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_bloqu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_peri,
             cons.oid_clie
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS (SELECT mtb.cod_tipo_bloq
                FROM mae_clien_bloqu mcb,
                     mae_tipo_bloqu  mtb
               WHERE mcb.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                 AND mcb.clie_oid_clie = cons.oid_clie
                 AND mtb.cod_tipo_bloq IN ('02',
                                           '01',
                                           'BR')
                 AND mcb.fec_desb IS NULL);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_oid_peri t_oid_peri;
    v_oid_clie t_oid_clie;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_peri,
             v_oid_clie LIMIT w_filas;

      IF (v_codpais.count > 0) THEN
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_SADMC_BLOQU: ' || ls_sqlerrm);

  END sto_pr_spvc_sadmc_bloqu;

  /***************************************************************************
  Descripcion       : Validación de la Documento de Cruce cliente, migracion y periodo OK

  Fecha Creacion    : 15/05/2009

  Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_docru_migra
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadadminist IS
      SELECT cons.cod_pais,
             cons.cod_peri, --4
             cons.cod_clie, --5
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.num_docu,
             cons.num_docu_cruc, --6
             cons.cod_sub_acce --8
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_docu_cruc IS NOT NULL
         AND cons.cod_clie IS NOT NULL;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    TYPE t_num_docu IS TABLE OF int_solic_conso_poven_cabec.num_docu%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_cod_sub_acce IS TABLE OF int_solic_conso_poven_cabec.cod_sub_acce%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_num_docu      t_num_docu;
    v_num_docu_cruc t_num_docu_cruc;
    v_cod_sub_acce  t_cod_sub_acce;

    --DATOS PARA INSERCION 2

    lnoidsolicabe           ped_solic_cabec.oid_soli_cabe%TYPE;
    lscodclie               mae_clien.cod_clie%TYPE;
    ldfecfact               ped_solic_cabec.fec_fact%TYPE;
    lnoidcliente            mae_clien.oid_clie%TYPE;
    lntidooidtipodocu       ped_solic_cabec.tido_oid_tipo_docu%TYPE;
    lnticloidtipoclie       ped_solic_cabec.ticl_oid_tipo_clie%TYPE;
    lnsbtioidsubtclie       ped_solic_cabec.sbti_oid_subt_clie%TYPE;
    lscodperi               seg_perio_corpo.cod_peri%TYPE;
    lnoidperi               cra_perio.oid_peri%TYPE;
    lnestaoidestaclie       mae_clien_datos_adici.esta_oid_esta_clie%TYPE;
    lnoidindirevi           ped_solic_cabec.inre_oid_indi_revi%TYPE;
    lsvalor                 VARCHAR2(20) := '0';
    lsolicitud              VARCHAR2(20) := '';
    lsolicitudocho          VARCHAR2(20) := '';
    lsparametropermigracion sto_param_gener_occrr.val_param%TYPE;
    lsparametrotdoccruce    sto_param_gener_occrr.val_param%TYPE;
    lsparametrotdococho     sto_param_gener_occrr.val_param%TYPE;
    lnvalsoli               int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    lnvalsolibusca          int_solic_conso_poven_cabec.num_docu_cruc%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametropermigracion := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                    'STO_PERI_MIGRA');

    lsparametrotdoccruce := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_IND_DOCRU');

    lsparametrotdococho := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_IND_DOCRU8');
                                                                 
    OPEN c_unidadadminist;
    LOOP
      FETCH c_unidadadminist BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_docu,
             v_num_docu_cruc,
             v_cod_sub_acce LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe  := TRUE;
          lsvalor := v_codclie(i);

          --- SQA obtiene el nuevo documento de cruce
          lnvalsolibusca := v_num_docu_cruc(i);
          IF lsparametrotdoccruce = 'F' THEN
            BEGIN
              SELECT b.val_nume_soli
                INTO lnvalsolibusca
                FROM fac_docum_conta_cabec a,
                     ped_solic_cabec       b
               WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
                 AND a.num_docu_lega = v_num_docu_cruc(i);
            EXCEPTION
              WHEN no_data_found THEN
                lnvalsolibusca := v_num_docu_cruc(i);
            END;
          END IF;

          BEGIN

            IF (length(TRIM(to_char(to_number(v_num_docu_cruc(i))))) = 8 AND
               lsparametrotdoccruce <> 'F') THEN
              --------- Se usa solo cuando el documento origen es de 8 posiciones para hacer la busqueda como si fuera 9
              SELECT '%' || TRIM(to_char(decode(length(TRIM(to_char(to_number(v_num_docu_cruc(i))))),
                                                10,
                                                to_char(to_number(v_num_docu_cruc(i))),
                                                substr(to_char(to_number(v_num_docu_cruc(i))),
                                                       1,
                                                       2) || '%' || substr(to_char(to_number(v_num_docu_cruc(i))),
                                                                           3,
                                                                           6)))) || '%'
                INTO lsolicitud
                FROM dual;

              --------- Se usa solo cuando el documento origen es de 8 posiciones flag prendio
              SELECT substr(v_codperi(i),3,2) || TRIM(to_char(decode(length(TRIM(to_char(to_number(v_num_docu_cruc(i))))),
                                                10,
                                                to_char(to_number(v_num_docu_cruc(i))),
                                                substr(to_char(to_number(v_num_docu_cruc(i))),
                                                       1,
                                                       8) )))

                INTO lsolicitudocho
                FROM dual;

              SELECT referencia.oid_soli_cabe,
                     referencia.cod_clie,
                     referencia.fec_fact,
                     referencia.oid_clie,
                     referencia.tido_oid_tipo_docu,
                     referencia.ticl_oid_tipo_clie,
                     referencia.sbti_oid_subt_clie,
                     referencia.cod_peri,
                     referencia.oid_peri,
                     referencia.esta_oid_esta_clie,
                     referencia.inre_oid_indi_revi,
                     referencia.val_nume_soli
                INTO lnoidsolicabe,
                     lscodclie,
                     ldfecfact,
                     lnoidcliente,
                     lntidooidtipodocu,
                     lnticloidtipoclie,
                     lnsbtioidsubtclie,
                     lscodperi,
                     lnoidperi,
                     lnestaoidestaclie,
                     lnoidindirevi,
                     lnvalsoli
                FROM (SELECT a.oid_soli_cabe,
                             b.cod_clie,
                             a.fec_fact,
                             b.oid_clie,
                             a.tido_oid_tipo_docu,
                             a.ticl_oid_tipo_clie,
                             a.sbti_oid_subt_clie,
                             d.cod_peri,
                             c.oid_peri,
                             f.esta_oid_esta_clie,
                             a.inre_oid_indi_revi,
                             a.val_nume_soli
                        FROM ped_solic_cabec       a,
                             mae_clien             b,
                             cra_perio             c,
                             seg_perio_corpo       d,
                             seg_subac             e,
                             mae_clien_datos_adici f,
                             ped_tipo_solic_pais   tsp,
                             ped_tipo_solic        ts
                       WHERE a.clie_oid_clie = b.oid_clie
                         AND a.perd_oid_peri = c.oid_peri
                         AND c.peri_oid_peri = d.oid_peri
                         AND a.sbac_oid_sbac = e.oid_sbac
                         AND TRIM(to_char(a.val_nume_soli)) LIKE lsolicitud
                         AND e.cod_sbac = v_cod_sub_acce(i)
                         AND a.ind_ts_no_conso = 0
                         AND f.clie_oid_clie = b.oid_clie
                         AND a.soca_oid_soli_cabe IS NULL
                         AND b.cod_clie = v_codclie(i)
                         AND a.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                         AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                         AND ts.ind_soli_nega = 0
                         AND ts.ind_cons = 1
                         AND d.cod_peri <= lsparametropermigracion
                      UNION
                      SELECT a.oid_soli_cabe,
                             b.cod_clie,
                             a.fec_fact,
                             b.oid_clie,
                             a.tido_oid_tipo_docu,
                             a.ticl_oid_tipo_clie,
                             a.sbti_oid_subt_clie,
                             d.cod_peri,
                             c.oid_peri,
                             f.esta_oid_esta_clie,
                             a.inre_oid_indi_revi,
                             a.val_nume_soli
                        FROM ped_solic_cabec       a,
                             mae_clien             b,
                             cra_perio             c,
                             seg_perio_corpo       d,
                             seg_subac             e,
                             mae_clien_datos_adici f,
                             ped_tipo_solic_pais   tsp,
                             ped_tipo_solic        ts
                       WHERE a.clie_oid_clie = b.oid_clie
                         AND a.perd_oid_peri = c.oid_peri
                         AND c.peri_oid_peri = d.oid_peri
                         AND a.sbac_oid_sbac = e.oid_sbac
                         AND a.val_nume_soli = 
                             decode(lsparametrotdococho,'S', lsolicitudocho, v_num_docu_cruc(i))
                         AND e.cod_sbac = v_cod_sub_acce(i)
                         AND a.ind_ts_no_conso = 0
                         AND f.clie_oid_clie = b.oid_clie
                         AND a.soca_oid_soli_cabe IS NULL
                         AND b.cod_clie = v_codclie(i)
                         AND a.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                         AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                         AND ts.ind_soli_nega = 0
                         AND ts.ind_cons = 1
                         AND d.cod_peri > lsparametropermigracion) referencia
               WHERE rownum = 1;
              ------------------
            ELSE
              ------------ Se busca puntualmente el documento
              SELECT a.oid_soli_cabe,
                     b.cod_clie,
                     a.fec_fact,
                     b.oid_clie,
                     a.tido_oid_tipo_docu,
                     a.ticl_oid_tipo_clie,
                     a.sbti_oid_subt_clie,
                     d.cod_peri,
                     c.oid_peri,
                     f.esta_oid_esta_clie,
                     a.inre_oid_indi_revi,
                     a.val_nume_soli
                INTO lnoidsolicabe,
                     lscodclie,
                     ldfecfact,
                     lnoidcliente,
                     lntidooidtipodocu,
                     lnticloidtipoclie,
                     lnsbtioidsubtclie,
                     lscodperi,
                     lnoidperi,
                     lnestaoidestaclie,
                     lnoidindirevi,
                     lnvalsoli
                FROM ped_solic_cabec       a,
                     mae_clien             b,
                     cra_perio             c,
                     seg_perio_corpo       d,
                     seg_subac             e,
                     mae_clien_datos_adici f,
                     ped_tipo_solic_pais   tsp,
                     ped_tipo_solic        ts
               WHERE a.clie_oid_clie = b.oid_clie
                 AND a.perd_oid_peri = c.oid_peri
                 AND c.peri_oid_peri = d.oid_peri
                 AND a.sbac_oid_sbac = e.oid_sbac
                 AND a.val_nume_soli = lnvalsolibusca ---- SQA  v_num_docu_cruc(i)
                 AND e.cod_sbac = v_cod_sub_acce(i)
                 AND a.ind_ts_no_conso = 0
                 AND f.clie_oid_clie = b.oid_clie
                 AND a.soca_oid_soli_cabe IS NULL
                 AND b.cod_clie = v_codclie(i)
                 AND a.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND ts.ind_soli_nega = 0
                 AND ts.ind_cons = 1;
              ------------------
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
          END;
          IF (existe) THEN

            UPDATE int_solic_conso_poven_cabec
               SET oid_peri_refe      = lnoidperi,
                   oid_clie           = lnoidcliente,
                   oid_cabe           = lnoidsolicabe,
                   sbti_oid_subt_clie = lnsbtioidsubtclie,
                   ticl_oid_tipo_clie = lnticloidtipoclie,
                   fec_refe           = ldfecfact,
                   tido_oid_tipo_docu = lntidooidtipodocu,
                   num_docu_cruc      = lnvalsoli
             WHERE cod_pais = v_codpais(i)
               AND cod_peri = v_codperi(i)
               AND cod_clie = v_codclie(i)
               AND num_lote = v_numlote(i)
               AND num_docu = v_num_docu(i);

            UPDATE int_solic_conso_poven_detal
               SET esta_oid_esta_clie = lnestaoidestaclie,
                   val_revi_cheq      = lnoidindirevi
             WHERE cod_pais = v_codpais(i)
               AND cod_peri = v_codperi(i)
               AND cod_clie = v_codclie(i)
               AND num_lote = v_numlote(i)
               AND num_docu = v_num_docu(i);

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_unidadadminist%NOTFOUND;

    END LOOP;
    CLOSE c_unidadadminist;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_DOCRU_MIGRA: ' || lsvalor || ls_sqlerrm);

  END sto_pr_spvc_docru_migra;

  /***************************************************************************
  Descripcion       : Validación de de rechazo por OCR OK

  Fecha Creacion    : 20/05/2009


  Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tiposolicitud IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu

        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_moti_rech IS NULL;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_tiposolicitud;
    LOOP
      FETCH c_tiposolicitud BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- ACTUALZIAMOS INDICADORES DE VALIDACION
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_tiposolicitud%NOTFOUND;
    END LOOP;
    CLOSE c_tiposolicitud;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_RECHA_OCR: ' || ls_sqlerrm);

  END sto_pr_spvc_recha_ocr;

  /***************************************************************************
  Descripcion       : Validación de bloqueo solo por BR

  Fecha Creacion    : 29/10/2009


  Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_blobr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_peri,
             cons.oid_clie
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS (SELECT mtb.cod_tipo_bloq
                FROM mae_clien_bloqu mcb,
                     mae_tipo_bloqu  mtb
               WHERE mcb.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                 AND mcb.clie_oid_clie = cons.oid_clie
                 AND mtb.cod_tipo_bloq = 'BR'
                 AND mcb.fec_desb IS NULL);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_oid_peri t_oid_peri;
    v_oid_clie t_oid_clie;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_peri,
             v_oid_clie LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_SADMC_BLOBR: ' || ls_sqlerrm);

  END sto_pr_spvc_sadmc_blobr;

  /***************************************************************************
  Descripcion       : Se modifica el SP a fin de que tome los bloqueos por CDR
  Fecha Creacion    : 23/10/2012
  Autor             : Sandro Quintana
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_bload
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_peri,
             cons.oid_clie,
                (SELECT count(*) genera
                  FROM mae_clien_bloqu a,
                       mae_accio_proce_bloqu b,
                       mae_accio_bloqu c,
                       mae_proce_bloqu d
                 WHERE a.fec_desb IS NULL
                   AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
                   AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
                   AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
                   AND d.cod_proc_bloq = 'PV'   --- PostVenta
                   AND c.cod_acci_bloq = 'AN'    --- No Atiende PostVenta
                   AND a.clie_oid_clie = cons.oid_clie
                   and rownum = 1) bloqueo             
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;
    TYPE t_bloqueo IS TABLE OF int_solic_conso_poven_cabec.IND_CDR_RECH%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_bloqueo            t_bloqueo;

    v_oid_peri t_oid_peri;
    v_oid_clie t_oid_clie;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_peri,
             v_oid_clie,
             v_bloqueo LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          IF (v_bloqueo(j) = 0) THEN
              existe := TRUE;
          ELSE
              existe := FALSE;
          END IF;
          
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_SADMC_BLOAD: ' || ls_sqlerrm);

  END sto_pr_spvc_sadmc_bload;

  /***************************************************************************
  Descripcion       : Se modifica el SP a fin de que tome los bloqueos por CDR
  Fecha Creacion    : 23/10/2012
  Autor             : Sandro Quintana
   ***************************************************************************/
  FUNCTION sto_fn_spvc_sadmc_bload
  (
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN IS
         
    v_bloqueo            BINARY_INTEGER;
    v_oid_clie           int_solic_conso_poven_cabec.oid_clie%TYPE;

  BEGIN
       ---- Recupera el codigo de cliente en base al numero de pedido
       select min(clie_oid_clie) into v_oid_clie from ped_solic_cabec where val_nume_soli = psnumpedi;

       ---- Verifica el bloqueo para ese cliente
        SELECT count(*) genera
        into v_bloqueo
          FROM mae_clien_bloqu a,
               mae_accio_proce_bloqu b,
               mae_accio_bloqu c,
               mae_proce_bloqu d
         WHERE a.fec_desb IS NULL
           AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
           AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
           AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
           AND d.cod_proc_bloq = 'PV'   --- PostVenta
           AND c.cod_acci_bloq = 'AN'    --- No Atiende PostVenta
           AND a.clie_oid_clie = v_oid_clie
           and rownum = 1;

       
          IF (v_bloqueo = 0) THEN
              existe := TRUE;
          ELSE
              existe := FALSE;
          END IF;
          
 RETURN (existe);
 
  END sto_fn_spvc_sadmc_bload;


  /***************************************************************************
  Descripcion       : Validación de bloqueo solo por financiero

  Fecha Creacion    : 29/10/2009


  Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_spvc_sadmc_blofi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_peri,
             cons.oid_clie
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS (SELECT mtb.cod_tipo_bloq
                FROM mae_clien_bloqu mcb,
                     mae_tipo_bloqu  mtb
               WHERE mcb.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                 AND mcb.clie_oid_clie = cons.oid_clie
                 AND mtb.cod_tipo_bloq = '02'
                 AND mcb.fec_desb IS NULL);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;

    v_oid_peri t_oid_peri;
    v_oid_clie t_oid_clie;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_peri,
             v_oid_clie LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_SADMC_BLOFI: ' || ls_sqlerrm);

  END sto_pr_spvc_sadmc_blofi;

  /***************************************************************************
  Descripcion       : Validación de Segundo Dia de Facturacion
  Fecha Creacion    : 26/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_segun_factu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.docu_cod_tipo_docu,
             cons.sec_nume_docu,
             cons.oid_clie,
             cons.oid_pais,
             cons.oid_peri,
             cons.fec_proc_docu,
             cons.ztad_oid_terr_admi
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_cabec.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_poven_cabec.oid_pais%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_poven_cabec.oid_peri%TYPE;
    TYPE t_fec_proc_docu IS TABLE OF int_solic_conso_poven_cabec.fec_proc_docu%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_clie           t_oid_clie;
    v_oid_pais           t_oid_pais;
    v_oid_peri           t_oid_peri;
    v_fec_proc_docu      t_fec_proc_docu;
    v_oid_terr_admi      t_oid_terr_admi;

    existe BOOLEAN := TRUE;

    tmpfechafact cra_crono.fec_inic%TYPE;
  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_oid_clie,
             v_oid_pais,
             v_oid_peri,
             v_fec_proc_docu,
             v_oid_terr_admi LIMIT w_filas;

      IF (v_codpais.count > 0) THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;
          BEGIN

            SELECT fec_inic
              INTO tmpfechafact
              FROM cra_crono
             WHERE cact_oid_acti = (SELECT oid_acti
                                      FROM cra_activ
                                     WHERE pais_oid_pais = v_oid_pais(i)
                                       AND cod_acti = 'FA')
               AND perd_oid_peri = v_oid_peri(i)
               AND zzon_oid_zona = (SELECT a.oid_zona
                                      FROM zon_zona        a,
                                           zon_secci       b,
                                           zon_terri_admin c
                                     WHERE c.zscc_oid_secc = b.oid_secc
                                       AND b.zzon_oid_zona = a.oid_zona
                                       AND c.oid_terr_admi = v_oid_terr_admi(i));

            IF (tmpfechafact < v_fec_proc_docu(i)) THEN
              existe := FALSE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
          END;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;

    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvc_segun_factu: ' || ls_sqlerrm);

  END sto_pr_spvc_segun_factu;

  /***************************************************************************
  Descripcion       : Validación de Segundo Dia de Facturacion
  Fecha Creacion    : 26/04/2010
  Autor             : Dennys Oliva Iriarte
                     SPVC-19	VALIDACION SEGUNDO DIA FACTURACION
  ***************************************************************************/
  FUNCTION sto_fn_spvc_segun_factu
  (
    psoidpais        number,
    psoidperiact     number,
    psoidpericdr     number,
    psoidterr        number
  ) RETURN BOOLEAN IS

    existe BOOLEAN := TRUE;

    tmpfechafact     cra_crono.fec_inic%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;
    
  BEGIN

   select trunc(sysdate) into v_fec_proc_docu from dual;

          existe := TRUE;
          BEGIN

            SELECT fec_inic
              INTO tmpfechafact
              FROM cra_crono
             WHERE cact_oid_acti = (SELECT oid_acti
                                      FROM cra_activ
                                     WHERE pais_oid_pais = psoidpais
                                       AND cod_acti = 'FA')
               AND perd_oid_peri = psoidperiact
               AND zzon_oid_zona = (SELECT a.oid_zona
                                      FROM zon_zona        a,
                                           zon_secci       b,
                                           zon_terri_admin c
                                     WHERE c.zscc_oid_secc = b.oid_secc
                                       AND b.zzon_oid_zona = a.oid_zona
                                       AND c.oid_terr_admi = psoidterr);


          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
          END;

            IF (tmpfechafact < v_fec_proc_docu) THEN
              existe := FALSE;
            END IF;

          IF (psoidperiact = psoidpericdr) THEN
              existe := TRUE;
          END IF;

    RETURN (existe);
    
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_fn_spvc_segun_factu: ' || ls_sqlerrm);

    
  END sto_fn_spvc_segun_factu;


  /***************************************************************************
  Descripcion       : Validación de Rechazo de CDR
  Fecha Creacion    : 25/05/2012
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recha_cdr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cursor IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_obse_rech_defi,
             cons.cod_mot_rech_defi,
             cons.ind_cdr_rech
        FROM int_solic_conso_poven_cabec cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
    ----AND cons.ind_cdr_rech = '1';

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_val_obse_rech_defi IS TABLE OF int_solic_conso_poven_cabec.val_obse_rech_defi%TYPE;
    TYPE t_cod_mot_rech_defi IS TABLE OF int_solic_conso_poven_cabec.cod_mot_rech_defi%TYPE;
    TYPE t_ind_cdr_rech IS TABLE OF int_solic_conso_poven_cabec.ind_cdr_rech%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_val_obse_rech_defi t_val_obse_rech_defi;
    v_cod_mot_rech_defi  t_cod_mot_rech_defi;
    v_ind_cdr_rech       t_ind_cdr_rech;

    j                        BINARY_INTEGER := 0;
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;

    lncodigomensaje NUMBER;
  BEGIN

    lncodigomensaje := sto_pkg_gener.sto_fn_devue_codig_mensa(pscodigopais,
                                                              pscodigotipodoc,
                                                              pscodigovalidactual);

    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_obse_rech_defi,
             v_cod_mot_rech_defi,
             v_ind_cdr_rech LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        ---FORALL j IN 1 .. v_codpais.count
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_ind_cdr_rech(j) = '1') THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_erro = pscodigovalidactual,
                   occ.ind_rech           = '1',
                   occ.fec_modi           = SYSDATE,
                   occ.usu_modi           = psusuario,
                   occ.cod_moti_rech      = v_cod_mot_rech_defi(j),
                   occ.val_obse_rech_defi = v_val_obse_rech_defi(j)
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_erro = pscodigovalidactual,
                   occ.fec_modi           = SYSDATE,
                   occ.usu_modi           = psusuario,
                   occ.ind_rech           = '1',
                   occ.cod_moti_rech      = v_cod_mot_rech_defi(j),
                   occ.val_obse_rech_defi = v_val_obse_rech_defi(j)
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = lscodigodocumentodetalle
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu_cabe = v_sec_nume_docu(j);

            INSERT INTO sto_detal_docum_excep
              (cod_pais,
               cod_tipo_docu,
               num_lote,
               cod_vali,
               sec_nume_docu,
               ind_gest,
               fec_digi,
               usu_digi,
               fec_modi,
               usu_modi,
               cod_mens)
            VALUES
              (pscodigopais,
               pscodigotipodoc,
               v_numlote(j),
               pscodigovalidactual,
               v_sec_nume_docu(j),
               '0',
               SYSDATE,
               psusuario,
               SYSDATE,
               psusuario,
               lncodigomensaje);

          ELSE
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_recha_cdr: ' || ls_sqlerrm);

  END sto_pr_spvd_recha_cdr;

 /***************************************************************************
    Descripcion       : Validacion para la cabecera de Cdrs
    Fecha Creacion    : 06/08/2012
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvc_valid_pedid
  (
    psnumpedi          in VARCHAR2,
    pmensaje          out varchar2
    
  ) IS
  
  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;


  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;
  lscodperiact     bas_ctrl_fact.cod_peri%TYPE;
  lsfecha          cra_perio.fec_inic%TYPE;

  lsOidPeriCDR     int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
  lsOidPeriAct     int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
  lscodclie        int_solic_conso_poven_cabec.cod_clie%TYPE;
  lnoidpais        int_solic_conso_poven_cabec.oid_pais%TYPE;
  lsOidTerri       zon_terri.oid_terr%TYPE;

  v_existe boolean := false;
  v_excep  boolean := false;
    
  BEGIN

  pmensaje := null;

   lsfecha := to_date('01/01/1900','DD/MM/YYYY');

   select cod_pais, cod_peri into pscodigopais,lscodperiact from bas_Ctrl_Fact where rownum=1;

   lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

   lsOidPeriAct := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lscodperiact);

   ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
    BEGIN
      select PSC1.PERD_OID_PERI , mae.cod_clie into lsOidPeriCDR, lscodclie
      from ped_solic_cabec psc1, mae_clien mae
      where psc1.clie_oid_clie = mae.oid_clie
      and psc1.val_nume_soli = psnumpedi
      and rownum = 1;         
    EXCEPTION
      WHEN no_data_found THEN
        lsOidPeriCDR := 0;
        lscodclie := null;
    END;

   ---- Obtiene el oid del territorio del cliente
    BEGIN
             SELECT terr.oid_terr into lsOidTerri
               FROM mae_clien c,
                    mae_clien_unida_admin cuad,
                    zon_terri_admin       ztad,
                    zon_secci             zscc,
                    zon_terri             terr,
                    zon_zona              zzon,
                    zon_regio             zorg
              WHERE c.oid_clie              = cuad.clie_oid_clie
                AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                AND ztad.zscc_oid_secc = zscc.oid_secc
                AND ztad.terr_oid_terr = terr.oid_terr
                AND zscc.zzon_oid_zona = zzon.oid_zona
                AND zzon.zorg_oid_regi = zorg.oid_regi
                AND cuad.ind_acti = 1
                AND c.cod_clie =  lscodclie;  
    EXCEPTION
      WHEN no_data_found THEN
        lsOidTerri := 0;
    END;

   
   if psnumpedi = null then
      pmensaje := 'Ingrese numero de pedido valido';
   else
      if lsOidPeriCDR = 0 then
         pmensaje := 'Numero de Pedido no existe';
      else
        --- Verifica si se controla en Linea
        lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CDR_LINEA-19');
        
        if lsparametroval = '1' then
           v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,null,null,lsfecha,'SPVC','SPVC-19'));  
           if not v_excep then
              v_existe := (sto_pkg_proce_valid_spvc.sto_fn_spvc_segun_factu(lnoidpais,lsOidPeriAct,lsOidPeriCDR,lsOidTerri));  
           else
              v_existe := true;
           end if;
        else
           v_existe := true;
        end if;
        if not v_existe then
           pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,'SPVC','SPVC-19','W');
           if pmensaje is null then
              pmensaje := 'No puede Ingresar CDR por Segundo dia de facturacion (SPVC-19)' || ' ';
           end if;
        end if;
        
      end if;
   end if;
      
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvc_valid_pedid: ' || ls_sqlerrm);

  END sto_pr_spvc_valid_pedid;

  /***************************************************************************
  Descripcion       : Validación de Excepcion  de validacion
  Fecha Creacion    : 21/02/2013
  Autor             : SAndro Quintana Aponte
                     Revisa en la tabla si se ingreso una excepcion a la validacion
  ***************************************************************************/
  FUNCTION sto_fn_spvc_excep_valid
  (
    pscodclie        VARCHAR2,
    pscodorig        VARCHAR2,
    pscodperiACT     VARCHAR2,
    psfechaACT       DATE,
    pstipodoc        VARCHAR2,
    pscodvali        VARCHAR2
  ) RETURN BOOLEAN IS

    existe BOOLEAN := TRUE;

    tmpfechafact     cra_crono.fec_inic%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;
    v_cod_peri       bas_ctrl_Fact.Cod_Peri%TYPE;
    lnnumregistros   NUMBER;

  BEGIN
    
     v_cod_peri := pscodperiACT;

     if (v_cod_peri is null) then
        select cod_peri into v_cod_peri from bas_Ctrl_Fact where IND_CAMP_ACT = '1' and rownum=1;
     end if;

     v_fec_proc_docu := psfechaACT;
     
     if (v_fec_proc_docu = to_date('01/01/1900','DD/MM/YYYY')) then
        select trunc(sysdate) into v_fec_proc_docu from dual;
     end if;

          existe := FALSE;

          SELECT COUNT(1)
              INTO lnnumregistros
              FROM mae_clien_unida_admin mcua,
                   zon_terri_admin       zta,
                   zon_terri             zt,
                   zon_secci             zs,
                   zon_zona              zz,
                   zon_regio             zr,
                   mae_clien             mc,
                   sto_exclu_valid       ex
             WHERE mcua.ztad_oid_terr_admi = zta.oid_terr_admi
               AND zta.zscc_oid_secc = zs.oid_secc
               AND zta.terr_oid_terr = zt.oid_terr
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND mcua.ind_acti = 1
               AND zta.ind_borr = 0
               AND mc.oid_clie = mcua.clie_oid_clie
               AND mc.cod_clie = pscodclie
               AND ex.cod_tipo_docu = pstipodoc
               AND (ex.cod_orig = pscodorig OR ex.cod_orig IS NULL)
               AND (ex.cod_regi = zr.cod_regi OR ex.cod_regi IS NULL)
               AND (ex.cod_zona = zz.cod_zona OR ex.cod_zona IS NULL)
               AND (ex.cod_peri = v_cod_peri OR ex.cod_peri IS NULL)
               AND (ex.cod_clie = mc.cod_clie OR ex.cod_clie IS NULL)
               AND (ex.fec_proc = v_fec_proc_docu OR ex.fec_proc IS NULL)
               AND ex.cod_vali = pscodvali;

          IF (lnnumregistros > 0) THEN
              existe := TRUE;
          END IF;

    RETURN (existe);
    

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_fn_spvc_excep_valid: ' || ls_sqlerrm);


  END sto_fn_spvc_excep_valid;

END sto_pkg_proce_valid_spvc;
/
