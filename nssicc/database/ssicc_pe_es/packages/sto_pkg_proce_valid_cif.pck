CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_cif IS 

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  rows       NUMBER := 5000;

  /**************************************************************************
    Descripcion       : STO_PR_CIF_TIDOC_IDENT
                        Valida que el tipo de documento enviado, corresponda 
                        con el Tipo de documento de Identidad registrado 
                        para la consultora
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_tidoc_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CIF_NUDOC_IDENT
                        Valida que el numero de documento enviado, corresponda 
                        con el numero de documento de Identidad registrado 
                        para la consultora
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_nudoc_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CIF_CODIG_CLIEN
                        Valida que el numero de documento enviado, corresponda 
                        con el numero de documento de Identidad registrado 
                        para la consultora
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CIF_FIRMA_REPRE_LEGAL
                        Valida que la firma y huella del representante legal
                        se haya registrado en el documento
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_firma_repre_legal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CIF_FIRMA_CONSU
                        Valida que la firma y huella de la consultora
                        se haya registrado en el documento
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_firma_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion         : STO_PR_CIF_ENVIO_SICC
                        Envio de CIF a SICC
  Fecha Creacion      : 10/05/2013
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CIF_CONSU_HABIL
                        Valida que la consultora sea habil en la campaña actual
                        y/o en la siguiente
    Fecha Creacion    : 06/08/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_consu_habil
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CIF_CONSU_ACTIV
                        Valida que la consultora no se encuentre activa
    Fecha Creacion    : 04/11/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_consu_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

END sto_pkg_proce_valid_cif;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_cif IS

  cte_accion_activar VARCHAR2(2) := '02';

  /**************************************************************************
    Descripcion       : STO_PR_CIF_TIDOC_IDENT
                        Valida que el tipo de documento enviado, corresponda 
                        con el Tipo de documento de Identidad registrado 
                        para la consultora
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_tidoc_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_tipo_docu_iden
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    /**************************************************************************************/
    TYPE t_codpais IS TABLE OF int_solic_conso_carta_flexi.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    TYPE t_tipodociden IS TABLE OF int_solic_conso_carta_flexi.cod_tipo_docu_iden%TYPE;
    /**************************************************************************************/
    v_codpais     t_codpais;
    v_numdocu     t_numdocu;
    v_codclie     t_codclie;
    v_numlote     t_numlote;
    v_secnumdocu  t_secnumdocu;
    v_tipodociden t_tipodociden;
    /**************************************************************************************/
    i       BINARY_INTEGER := 0;
    lx_cont NUMBER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_tipodocumento;
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu,
             v_tipodociden LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
        
          SELECT COUNT(1)
            INTO lx_cont
            FROM mae_clien       m,
                 mae_clien_ident ci,
                 mae_tipo_docum  td
           WHERE m.cod_clie = v_codclie(i)
             AND m.oid_clie = ci.clie_oid_clie
             AND ci.val_iden_docu_prin = '1'
             AND td.oid_tipo_docu = ci.tdoc_oid_tipo_docu
             AND td.cod_tipo_docu = v_tipodociden(i);
        
          -- si coincide el tipo de documento del maestro con el 
          -- que vino por OCR, pasa la validacion
          IF lx_cont != 0 THEN
          
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_secnumdocu(i);
          
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;
  
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
                              'ERROR STO_PR_CIF_TIDOC_IDENT: ' || ls_sqlerrm);
    
  END sto_pr_cif_tidoc_ident;

  /**************************************************************************
    Descripcion       : STO_PR_CIF_NUDOC_IDENT
                        Valida que el numero de documento enviado, corresponda 
                        con el numero de documento de Identidad registrado 
                        para la consultora
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_nudoc_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_tipo_docu_iden,
             cons.num_docu_iden
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    /**************************************************************************************/
    TYPE t_codpais IS TABLE OF int_solic_conso_carta_flexi.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    TYPE t_tipodociden IS TABLE OF int_solic_conso_carta_flexi.cod_tipo_docu_iden%TYPE;
    TYPE t_numdocuiden IS TABLE OF int_solic_conso_carta_flexi.num_docu_iden%TYPE;
    /**************************************************************************************/
    v_codpais     t_codpais;
    v_numdocu     t_numdocu;
    v_codclie     t_codclie;
    v_numlote     t_numlote;
    v_secnumdocu  t_secnumdocu;
    v_tipodociden t_tipodociden;
    v_numdocuiden t_numdocuiden;
    /**************************************************************************************/
    i       BINARY_INTEGER := 0;
    lx_cont NUMBER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_tipodocumento;
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu,
             v_tipodociden,
             v_numdocuiden LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
        
          SELECT COUNT(1)
            INTO lx_cont
            FROM mae_clien       m,
                 mae_clien_ident ci,
                 mae_tipo_docum  td
           WHERE m.cod_clie = v_codclie(i)
             AND m.oid_clie = ci.clie_oid_clie
             AND ci.val_iden_docu_prin = '1'
             AND td.oid_tipo_docu = ci.tdoc_oid_tipo_docu
             AND td.cod_tipo_docu = v_tipodociden(i)
             AND ci.num_docu_iden = v_numdocuiden(i);
        
          -- si coincide el numero de documento del maestro, para el tipo de documento con el 
          -- que vino por OCR, pasa la validacion
          IF lx_cont != 0 THEN
          
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_secnumdocu(i);
          
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;
  
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
                              'ERROR STO_PR_CIF_NUDOC_IDENT: ' || ls_sqlerrm);
    
  END sto_pr_cif_nudoc_ident;

  /**************************************************************************
    Descripcion       : STO_PR_CIF_CODIG_CLIEN
                        Valida que el numero de documento enviado, corresponda 
                        con el numero de documento de Identidad registrado 
                        para la consultora
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ,
             mae_clien                   m
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND m.cod_clie = cons.cod_clie;
  
    /**************************************************************************************/
    TYPE t_codpais IS TABLE OF int_solic_conso_carta_flexi.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    /**************************************************************************************/
    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    /**************************************************************************************/
    i BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_tipodocumento;
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_codpais.count
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);
      
      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;
  
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
                              'ERROR STO_PR_CIF_CODIG_CLIEN: ' || ls_sqlerrm);
    
  END sto_pr_cif_codig_clien;

  /**************************************************************************
    Descripcion       : STO_PR_CIF_FIRMA_REPRE_LEGAL
                        Valida que la firma y huella del representante legal
                        se haya registrado en el documento
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_firma_repre_legal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_firm_repr_lega = 'N';
  
    /**************************************************************************************/
    TYPE t_codpais IS TABLE OF int_solic_conso_carta_flexi.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    /**************************************************************************************/
    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    /**************************************************************************************/
    i BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_tipodocumento;
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_codpais.count
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);
      
      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;
  
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
                              'ERROR STO_PR_CIF_FIRMA_REPRE_LEGAL: ' || ls_sqlerrm);
    
  END sto_pr_cif_firma_repre_legal;

  /**************************************************************************
    Descripcion       : STO_PR_CIF_FIRMA_CONSU
                        Valida que la firma y huella de la consultora
                        se haya registrado en el documento
    Fecha Creacion    : 08/05/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_firma_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_firm_cons = 'N';
  
    /**************************************************************************************/
    TYPE t_codpais IS TABLE OF int_solic_conso_carta_flexi.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    /**************************************************************************************/
    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    /**************************************************************************************/
    i BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_tipodocumento;
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_codpais.count
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);
      
      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;
  
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
                              'ERROR STO_PR_CIF_FIRMA_CONSU: ' || ls_sqlerrm);
    
  END sto_pr_cif_firma_consu;

  /**************************************************************************
  Descripcion         : STO_PR_CIF_ENVIO_SICC
                        Envio de CIF a SICC
  Fecha Creacion      : 10/05/2013
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_enviocif IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_clie,
             cons.num_docu
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ,
             sto_docum_digit             dd
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais
         AND occ.sec_nume_docu = dd.sec_nume_docu;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_num_docu   IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
  
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_codclie    t_codclie;
    v_num_docu   t_num_docu;
  
    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;
  
    vs_cod_peri bas_ctrl_fact.cod_peri%TYPE;
  
  BEGIN
  
    -- Obtiene la campanha de proceso
    SELECT cod_peri
      INTO vs_cod_peri
      FROM bas_ctrl_fact a
     WHERE a.cod_pais = pscodigopais
       AND a.sta_camp = '0'
       AND ind_camp_act = '1';
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);
  
    OPEN c_enviocif;
    LOOP
      FETCH c_enviocif BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_codclie,
             v_num_docu LIMIT rows;
    
      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
        
          -- Activa a la consultora desde la campaña actual
          UPDATE flx_consu_habil_flexi
             SET ind_acti = '1',
                 num_docu = v_num_docu(i)
           WHERE cod_pais = pscodigopais
             AND cod_clie = v_codclie(i)
             AND cod_peri_fact >= vs_cod_peri;
        
          -- Registra la auditoria de la activación
          INSERT INTO flx_audit_consu_habil
            (cod_pais,
             cod_clie,
             fec_acci,
             cod_acci,
             usu_acci,
             cod_peri_fact)
          VALUES
            (pscodigopais,
             v_codclie(i),
             SYSDATE,
             cte_accion_activar,
             psusuario,
             vs_cod_peri);
        
        END LOOP;
      
        -- Actualizamos Documentos Enviados
        FORALL j IN 1 .. v_secnumdocu.count
          UPDATE sto_docum_digit occ
             SET -- Actualizamos Indicadores de Envio
                 occ.ind_envi = '1',
                 occ.usu_modi = psusuario,
                 occ.fec_modi = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoccabecera
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
      
      END IF;
    
      EXIT WHEN c_enviocif %NOTFOUND;
    END LOOP;
    CLOSE c_enviocif;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_CIF_ENVIO_SICC: ' || ls_sqlerrm);
    
  END sto_pr_cif_envio_sicc;


  /**************************************************************************
    Descripcion       : STO_PR_CIF_CONSU_HABIL
                        Valida que la consultora sea habil en la campaña actual
                        y/o en la siguiente
    Fecha Creacion    : 06/08/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_consu_habil
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento(pcodPeriActual varchar2,pcodPeriSiguiente varchar2) IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         -- que se habil en la campaña actual y/o en la siguiente
         AND exists(select null 
                      from flx_consu_habil_flexi f
                     where f.cod_clie = cons.cod_clie
                       and f.cod_peri_fact in (pcodPeriActual,pcodPeriSiguiente));

    /**************************************************************************************/
    TYPE t_codpais IS TABLE OF int_solic_conso_carta_flexi.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    /**************************************************************************************/
    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    /**************************************************************************************/
    i BINARY_INTEGER := 0;
    vcodPeriActual     bas_ctrl_fact.cod_peri%type;
    vcodPeriSiguiente  bas_ctrl_fact.cod_peri%type;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtengo la campaña actual
    SELECT cod_peri
      INTO vcodPeriActual
      FROM bas_ctrl_fact a
     WHERE a.cod_pais = pscodigopais
       AND a.sta_camp = '0'
       AND ind_camp_act = '1';

    -- Obtiene la campaña siguiente
    vcodPeriSiguiente := cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(vcodPeriActual,1);

    OPEN c_tipodocumento(vcodPeriActual,vcodPeriSiguiente);
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_codpais.count

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);

      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;

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
                              'ERROR STO_PR_CIF_CONSU_HABIL: ' || ls_sqlerrm);

  END sto_pr_cif_consu_habil;

/**************************************************************************
    Descripcion       : STO_PR_CIF_CONSU_ACTIV
                        Valida que la consultora no se encuentre activa
    Fecha Creacion    : 04/11/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_cif_consu_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento(pcodPeriActual varchar2) IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_carta_flexi cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         -- que la consultora no se encuentre activa en el sistema
         AND not exists(select null 
                          from flx_consu_habil_flexi f
                         where f.cod_clie = cons.cod_clie
                           and f.cod_peri_fact >= pcodPeriActual
                           and f.ind_acti = '1'
                           );

    /**************************************************************************************/
    TYPE t_codpais IS TABLE OF int_solic_conso_carta_flexi.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_carta_flexi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_carta_flexi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_carta_flexi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_carta_flexi.sec_nume_docu%TYPE;
    /**************************************************************************************/
    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    /**************************************************************************************/
    i BINARY_INTEGER := 0;
    vcodPeriActual     bas_ctrl_fact.cod_peri%type;
    
  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtengo la campaña actual
    SELECT cod_peri
      INTO vcodPeriActual
      FROM bas_ctrl_fact a
     WHERE a.cod_pais = pscodigopais
       AND a.sta_camp = '0'
       AND ind_camp_act = '1';

   OPEN c_tipodocumento(vcodPeriActual);
    LOOP
      FETCH c_tipodocumento BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_codpais.count

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);

      END IF;
      EXIT WHEN c_tipodocumento%NOTFOUND;
    END LOOP;
    CLOSE c_tipodocumento;

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
                              'ERROR STO_PR_CIF_CONSU_ACTIV: ' || ls_sqlerrm);

  END sto_pr_cif_consu_activ;

END sto_pkg_proce_valid_cif;
/
