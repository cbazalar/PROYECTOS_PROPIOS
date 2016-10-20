CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_ced IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  rows       NUMBER := 5000;
  lncanaldef seg_canal.cod_cana%TYPE := 'VD';
  lnmarcadef seg_marca.cod_marc%TYPE := 'T';

  /**************************************************************************
    Descripcion       : STO_PR_CED_PREIM_DUPLI
                        Procedimiento de Validación Pre impreso duplicado
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ced_preim_dupli
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CED_DOCUM_IDENT_BLANC
                       Validación Documento de identidad en blanco
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ced_docum_ident_blanc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CED_DOCUM_IDENT_NO_EXIST
                        Validación Documento de identidad no existe
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ced_docum_ident_noexi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CED_DOCUM_LEGAL
                        Validación Documento legal invalido
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ced_docum_legal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_CED_CONSU_EJECU
                        Validación Consultora sea ejecutiva
    Fecha Creacion    : 10/06/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_ced_consu_ejecu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_CED_ENVIO_SICC
                    Envio de CED a SICC
  Fecha Creacion    : 05/02/2013
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ced_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );
  /**************************************************************************
  Descripcion       : Validacion de cliente incobrable
  Fecha Creacion    : 12/08/2013
  Autor             : Jose Cairampoma
  **************************************************************************/
  PROCEDURE sto_pr_ced_clien_incob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion de bloqueo de cliente
  Fecha Creacion    : 12/08/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_ced_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
END sto_pkg_proce_valid_ced;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_ced IS

  /**************************************************************************
    Descripcion       : STO_PR_CED_PREIM_DUPLI
                        Procedimiento de Validación Pre impreso duplicado
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE STO_PR_CED_PREIM_DUPLI
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_documentoimpreso IS
      SELECT cons.COD_PAIS,
             cons.NUM_DOCU,
             cons.COD_CLIE,
             CONS.NUM_LOTE,
             cons.sec_nume_docu
        FROM INT_SOLIC_CONSO_CONTR_DESAR cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and NOT EXISTS(
              select 1
              from CED_CONTR_DESAR x
              where cons.NUM_DOCU = x.NUM_DOCU)
         and (select count(1)
              from INT_SOLIC_CONSO_CONTR_DESAR y
              where y.NUM_DOCU = cons.NUM_DOCU
             ) <= 1  ;

    TYPE t_codpais IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.sec_nume_docu%TYPE;

    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;


    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);


    OPEN c_documentoimpreso;
    LOOP
      FETCH c_documentoimpreso BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);

      END IF;
      EXIT WHEN c_documentoimpreso%NOTFOUND;
    END LOOP;
    CLOSE c_documentoimpreso;

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
                              'ERROR STO_PR_CED_PREIM_DUPLI: ' || ls_sqlerrm);
  END STO_PR_CED_PREIM_DUPLI;

  /**************************************************************************
    Descripcion       : STO_PR_CED_DOCUM_IDENT_BLANC
                       Validación Documento de identidad en blanco
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE STO_PR_CED_DOCUM_IDENT_BLANC
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT cons.COD_PAIS,
             cons.NUM_DOCU,
             cons.COD_CLIE,
             CONS.NUM_LOTE,
             cons.sec_nume_docu
        FROM INT_SOLIC_CONSO_CONTR_DESAR cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND CONS.NUM_DOCU_IDEN is not null
         and length(trim(CONS.NUM_DOCU_IDEN)) > 0;


    TYPE t_codpais IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.sec_nume_docu%TYPE;

    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

--    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);

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
                              'ERROR STO_PR_CED_DOCUM_IDENT_BLANC: ' || ls_sqlerrm);
  END STO_PR_CED_DOCUM_IDENT_BLANC;

  /**************************************************************************
    Descripcion       : STO_PR_CED_DOCUM_IDENT_NO_EXIST
                        Validación Documento de identidad no existe
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE STO_PR_CED_DOCUM_IDENT_NOEXI
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT cons.COD_PAIS,
             cons.NUM_DOCU,
             cons.COD_CLIE,
             CONS.NUM_LOTE,
             cons.sec_nume_docu
        FROM INT_SOLIC_CONSO_CONTR_DESAR cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND EXISTS(
            select 1
            from  MAE_TIPO_DOCUM W,
                  MAE_CLIEN_IDENT Z,
                  MAE_CLIEN X
		    WHERE X.COD_CLIE = cons.COD_CLIE
		  	    AND  Z.CLIE_OID_CLIE = X.OID_CLIE
		  	    AND ltrim(Z.NUM_DOCU_IDEN,'0') = ltrim(cons.NUM_DOCU_IDEN,'0')
		  	    AND Z.TDOC_OID_TIPO_DOCU = W.OID_TIPO_DOCU
		  	    AND W.COD_TIPO_DOCU = cons.COD_TIPO_DOCU
         );



    TYPE t_codpais IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.sec_nume_docu%TYPE;

    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

  --  i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;
    ls_cedula_cliente VARCHAR2(3);
    
  BEGIN

     --
    -- obtiene el parametro para identificar si viene la cedula en el campo codigo_cliente
    ls_cedula_cliente := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_CEDUL_CLIEN');

    -- Evalua si esta activa la funcionalidad para el pais
    if ls_cedula_cliente = 'S' then

    -- actualiza el codigo de cliente a los que tengan numero de
    -- documento de identidad en el el codigo de cliente
    
     update int_solic_conso_contr_desar a
         set a.cod_clie = (select cod_clie
                              from mae_clien a1,
                                   mae_clien_ident b1
                             where a1.oid_clie=b1.clie_oid_clie
                               and ltrim(a.num_docu_iden,'0')=ltrim(b1.num_docu_iden,'0')
                               and rownum=1
                            )
      where /*a.cod_clie is null
        and*/ exists (select 1 from mae_clien_ident x where ltrim(a.Num_Docu_Iden,'0')=ltrim(x.num_docu_iden,'0'))
        and exists (select 1 from sto_tmp_docum_digit occ where occ.sec_nume_docu=a.sec_nume_docu);

     -- actualiza la informacion del sto_docum_digit   
     /* update sto_docum_digit f
         set f.cod_clie =  (select m.cod_clie
                              from mae_clien_ident x, mae_clien m
                             where ltrim(f.cod_clie, '0') = ltrim(x.num_docu_iden, '0')
                               and m.oid_clie = x.clie_oid_clie
                               and x.val_iden_docu_prin = 1
                               and rownum = 1)
       where exists  (select 1
                        from mae_clien_ident x
                       where ltrim(f.cod_clie, '0') = ltrim(x.num_docu_iden, '0'))
         and exists (select 1
                       from sto_tmp_docum_digit occ
                      where occ.sec_nume_docu = f.sec_nume_docu)
         and f.cod_tipo_docu = pscodigotipodoc;*/

         update sto_docum_digit f
            set f.cod_clie = (select d.cod_clie 
                                from int_solic_conso_contr_desar d 
                               where d.sec_nume_docu = f.sec_nume_docu)
          where f.cod_tipo_docu = 'CED'
            --and f.cod_clie is null
            and exists (select 1
                          from sto_tmp_docum_digit occ
                         where occ.sec_nume_docu = f.sec_nume_docu);
    end if;        

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);

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
                              'ERROR STO_PR_CED_DOCUM_IDENT_NOEXI: ' || ls_sqlerrm);
  END STO_PR_CED_DOCUM_IDENT_NOEXI;

  /**************************************************************************
    Descripcion       : STO_PR_CED_DOCUM_LEGAL
                        Validación Documento legal invalido
    Fecha Creacion    : 29/01/2013
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE STO_PR_CED_DOCUM_LEGAL
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT cons.COD_PAIS,
             cons.NUM_DOCU,
             cons.COD_CLIE,
             CONS.NUM_LOTE,
             cons.sec_nume_docu
        FROM INT_SOLIC_CONSO_CONTR_DESAR cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND EXISTS(
            select 1
            from  MAE_TIPO_DOCUM W,
                  MAE_CLIEN_IDENT Z,
                  MAE_CLIEN X
		    WHERE X.COD_CLIE = cons.COD_CLIE
		  	    AND  Z.CLIE_OID_CLIE = X.OID_CLIE
		  	    AND ltrim(Z.NUM_DOCU_IDEN,'0') = ltrim(cons.NUM_DOCU_LEGA,'0') --documento legal
		  	    AND Z.TDOC_OID_TIPO_DOCU = W.OID_TIPO_DOCU
		  	    AND W.COD_TIPO_DOCU = cons.COD_TIPO_DOCU
         );



    TYPE t_codpais IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.sec_nume_docu%TYPE;

    v_codpais    t_codpais;
    v_numdocu    t_numdocu;
    v_codclie    t_codclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;


    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_codpais,
             v_numdocu,
             v_codclie,
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);

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
                              'ERROR STO_PR_CED_DOCUM_LEGAL: ' || ls_sqlerrm);
  END STO_PR_CED_DOCUM_LEGAL;


    /**************************************************************************
    Descripcion       : STO_PR_CED_CONSU_EJECU
                        Validación Consultora sea ejecutiva
    Fecha Creacion    : 10/06/2013
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE STO_PR_CED_CONSU_EJECU
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
   CURSOR c_validacion IS
      SELECT cons.COD_PAIS,
             cons.NUM_DOCU,
             cons.COD_CLIE,
             CONS.NUM_LOTE,
             cons.sec_nume_docu
        FROM INT_SOLIC_CONSO_CONTR_DESAR cons,
             sto_tmp_docum_digit         occ,
             mae_clien m,
             zon_secci z
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and cons.cod_clie = m.cod_clie
         and m.oid_clie = z.clie_oid_clie ;

    TYPE t_codpais IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_pais%TYPE;
    TYPE t_numdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.sec_nume_docu%TYPE;

    v_codpais     t_codpais;
    v_numdocu     t_numdocu;
    v_codclie     t_codclie;
    v_numlote     t_numlote;
    v_secnumdocu  t_secnumdocu;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

            OPEN c_validacion;
            LOOP
                FETCH c_validacion BULK COLLECT
                            INTO    v_codpais,
                                    v_numdocu,
                                    v_codclie,
                                    v_numlote,
                                    v_secnumdocu LIMIT rows;
                IF v_codpais.count > 0 THEN
                -- Actualizamos Documentos Validados OK
                    FORALL j IN 1 .. v_codpais.count
                    UPDATE sto_docum_digit occ
                    SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                        occ.cod_ulti_vali_exit = pscodigovalidactual,
                        occ.usu_modi = psusuario,
                        occ.fec_modi = SYSDATE
                    WHERE occ.cod_pais = pscodigopais
                        AND occ.cod_tipo_docu = pscodigotipodoc
                        AND occ.num_lote = v_numlote(j)
                        AND occ.sec_nume_docu = v_secnumdocu(j);

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
                              'ERROR STO_PR_CED_CONSU_EJECU: ' || ls_sqlerrm);
  END STO_PR_CED_CONSU_EJECU;


  /**************************************************************************
  Descripcion       : STO_PR_CED_ENVIO_SICC
                    Envio de ced a SICC
  Fecha Creacion    : 05/02/2013
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario

  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE STO_PR_CED_ENVIO_SICC
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_envioced IS
      SELECT cons.COD_PAIS,
             cons.COD_COMP,
             cons.NUM_DOCU,
             cons.COD_PERI,
             cons.COD_TIPO_DOCU,
             cons.NUM_DOCU_IDEN,
             cons.NUM_DOCU_LEGA,
             cons.COD_CLIE,
             cons.FEC_PROC,
             cons.IND_ESTA_PROC,
             cons.COD_MOTI_RECH,
             cons.COD_REGI,
             cons.COD_ZONA,
             cons.IND_FIRM,
             cons.SEC_NUME_DOCU,
             cons.NUM_LOTE,
             cons.IND_ORIG
        FROM INT_SOLIC_CONSO_CONTR_DESAR cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_pais%TYPE;
    TYPE t_codcomp IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.COD_COMP%TYPE;
    TYPE t_num_docu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_docu%TYPE;
    TYPE t_codperi IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_peri%TYPE;
    TYPE t_codtipodoc IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.COD_TIPO_DOCU%TYPE;
    TYPE t_numDocuIdent IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.NUM_DOCU_IDEN%TYPE;
    TYPE t_numDocuLega IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.NUM_DOCU_LEGA%TYPE;
    TYPE t_codclie IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.cod_clie%TYPE;
    TYPE t_fechaproceso IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.fec_proc%TYPE;
    TYPE t_indEstadoProceso IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.IND_ESTA_PROC%TYPE;
    TYPE t_codMotivoRechazo IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.COD_MOTI_RECH%TYPE;
    TYPE t_codRegi IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.COD_REGI%TYPE;
    TYPE t_codZona IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.COD_ZONA%TYPE;

    TYPE t_indFirma IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.IND_FIRM%TYPE;
    TYPE t_secnumdocu IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.num_lote%TYPE;
    TYPE t_indOrigen IS TABLE OF INT_SOLIC_CONSO_CONTR_DESAR.IND_ORIG%TYPE;


    v_codpais          t_codpais;
    v_codcomp          t_codcomp;
    v_num_docu         t_num_docu;
    v_codperi          t_codperi;
    v_codtipodoc       t_codtipodoc;
    v_numDocuIdent t_numDocuIdent;
    v_numDocuLega  t_numDocuLega;
    v_codclie          t_codclie;
    v_fechaproceso     t_fechaproceso;
    v_indEstadoProceso t_indEstadoProceso;
    v_codMotivoRechazo t_codMotivoRechazo;
    v_codRegi      t_codRegi;
    v_codZona      t_codZona;

    v_indFirma     t_indFirma;
    v_secnumdocu t_secnumdocu;
    v_numlote    t_numlote;
    v_indOrigen    t_indOrigen;



    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

   lnCount NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);

    OPEN c_envioced;
    LOOP
      FETCH c_envioced BULK COLLECT
        INTO v_codpais,
             v_codcomp,
             v_num_docu,
             v_codperi,
             v_codtipodoc,
            v_numDocuIdent,
            v_numDocuLega ,
             v_codclie,
             v_fechaproceso,
            v_indEstadoProceso,
            v_codMotivoRechazo,
            v_codRegi,
            v_codZona,
            v_indFirma,
             v_secnumdocu,
             v_numlote,
            v_indOrigen
                    LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          --si existe consultora con contrato se actualiza los datos
            select count(1) into lnCount
            from CED_CONTR_DESAR x
            where x.cod_clie = v_codclie(i);

            if (lnCount=0) then

                 INSERT INTO CED_CONTR_DESAR (
                   COD_PAIS,
                   COD_COMP,
                   NUM_DOCU,
                   COD_PERI,
                   COD_TIPO_DOCU,
                   NUM_DOCU_IDEN,
                   NUM_DOCU_LEGA,
                   COD_CLIE,
                   FEC_PROC,
                   IND_ESTA_PROC,
                   COD_MOTI_RECH,
                   COD_REGI,
                   COD_ZONA,
                   IND_FIRM,
                   SEC_NUME_DOCU,
                   NUM_LOTE,
                   IND_ORIG)
                VALUES (
                    v_codpais(i),
               v_codcomp(i),
               v_num_docu(i),
               v_codperi(i),
               v_codtipodoc(i),
                    v_numDocuIdent(i),
                    v_numDocuLega(i),
               v_codclie(i),
               v_fechaproceso(i),
                    v_indEstadoProceso(i),
                    v_codMotivoRechazo(i),
                    v_codRegi(i),
                    v_codZona(i),
                    v_indFirma(i),
               v_secnumdocu(i),
               v_numlote(i),
                    v_indOrigen(i));
            else

                    UPDATE CED_CONTR_DESAR
                    SET
                           COD_COMP      = v_codcomp(i),
                           NUM_DOCU      = v_num_docu(i),
                           COD_PERI      = v_codperi(i),
                           COD_TIPO_DOCU = v_codtipodoc(i),
                           NUM_DOCU_IDEN = v_numDocuIdent(i),
                           NUM_DOCU_LEGA = v_numDocuLega(i),
                           FEC_PROC      = v_fechaproceso(i),
                           IND_ESTA_PROC =  v_indEstadoProceso(i),
                           COD_MOTI_RECH =  v_codMotivoRechazo(i),
                           COD_REGI      =  v_codRegi(i),
                           COD_ZONA      =  v_codZona(i),
                           IND_FIRM      =  v_indFirma(i),
                           SEC_NUME_DOCU =  v_secnumdocu(i),
                           NUM_LOTE      =  v_numlote(i),
                           IND_ORIG      =  v_indOrigen(i)
             WHERE cod_clie = v_codclie(i);

            end if;

        END LOOP;
        -- Actualizamos Documentos Enviados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Envio
                 occ.ind_envi = '1',
                 occ.usu_modi = psusuario,
                 occ.fec_modi = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoccabecera
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);

      END IF;

      EXIT WHEN c_envioced %NOTFOUND;
    END LOOP;

    CLOSE c_envioced;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_CED_ENVIO_SICC: ' || ls_sqlerrm);
  END STO_PR_CED_ENVIO_SICC;
 /**************************************************************************
  Descripcion       : Validacion de cliente incobrable
  Fecha Creacion    : 12/08/2013
  Autor             : Jose Cairampoma
  **************************************************************************/ 
PROCEDURE sto_pr_ced_clien_incob
(
  pscodigopais          VARCHAR2,
  pscodigotipodoc       VARCHAR2,
  pscodigovalidactual   VARCHAR2,
  pscodigovalidanterior VARCHAR2,
  psusuario             VARCHAR2,
  psnumeroproceso       VARCHAR2
) IS

  -- Solo filtramos los que tienen numeros de documentos validos
  CURSOR c_clienincob IS
    SELECT cons.num_lote,
           cons.sec_nume_docu
      FROM int_solic_conso_contr_desar cons,
           sto_tmp_docum_digit         occ
     WHERE occ.sec_nume_docu = cons.sec_nume_docu
       AND occ.num_lote = cons.num_lote
       AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
       AND occ.cod_pais = pscodigopais -- 'COE'
       AND NOT EXISTS
     (SELECT 1
              FROM ccc_consu_casti_cabec inco
             WHERE cons.num_docu_iden = inco.num_docu_iden and ind_acti=1)
    UNION
    SELECT cons.num_lote,
           cons.sec_nume_docu
      FROM int_solic_conso_contr_desar cons,
           sto_tmp_docum_digit         occ
     WHERE occ.sec_nume_docu = cons.sec_nume_docu
       AND occ.num_lote = cons.num_lote
       AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
       AND occ.cod_pais = pscodigopais -- 'COE'
       AND NOT EXISTS (SELECT 1
              FROM ccc_consu_casti_cabec inco
             WHERE cons.cod_clie = inco.cod_clie);

  TYPE t_numlote IS TABLE OF int_solic_conso_contr_desar.num_lote%TYPE;
  TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_contr_desar.sec_nume_docu%TYPE;

  v_numlote       t_numlote;
  v_sec_nume_docu t_sec_nume_docu;

  rows NATURAL := 1000; -- Numero de filas a procesar cada vez

  j BINARY_INTEGER := 0;

BEGIN

  sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                         pscodigotipodoc,
                                         pscodigovalidactual,
                                         pscodigovalidanterior,
                                         psusuario,
                                         psnumeroproceso);

  OPEN c_clienincob;
  LOOP
    FETCH c_clienincob BULK COLLECT
      INTO v_numlote,
           v_sec_nume_docu LIMIT rows;
  
    IF v_numlote.count > 0 THEN
      -- Actualizamos Documentos Validados OK
      FOR j IN v_numlote.first .. v_numlote.last
      LOOP
      
        UPDATE sto_docum_digit occ
           SET -- Actualizamos Indicadores de Validacion
               occ.cod_ulti_vali_ejec = pscodigovalidactual,
               occ.cod_ulti_vali_exit = pscodigovalidactual,
               occ.usu_modi           = psusuario,
               occ.fec_modi           = SYSDATE
         WHERE occ.num_lote = v_numlote(j)
           AND occ.sec_nume_docu = v_sec_nume_docu(j);
      
      END LOOP;
    END IF;
    EXIT WHEN c_clienincob%NOTFOUND;
  END LOOP;
  CLOSE c_clienincob;

  sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                         pscodigotipodoc,
                                         pscodigovalidactual,
                                         pscodigovalidanterior,
                                         psusuario,
                                         psnumeroproceso);
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
  
    raise_application_error(-20123,
                            'STO_PR_CED_CLIEN_INCOB: ' || ls_sqlerrm);
  
END sto_pr_ced_clien_incob;
 
  /***************************************************************************
  Descripcion       : Validacion de bloqueo de cliente
  Fecha Creacion    : 12/08/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_ced_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_bloqueocliente IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.num_docu_iden
        FROM int_solic_conso_contr_desar cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    CURSOR c_motivosbloqueo
    (
      ln_oidclie     NUMBER,
      ln_indfacrefac VARCHAR2
    ) IS
      SELECT a.tibq_oid_tipo_bloq,
             bloq_desc.val_i18n
        FROM mae_clien_bloqu a,
             mae_accio_proce_bloqu b,
             mae_accio_bloqu c,
             mae_proce_bloqu d,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_TIPO_BLOQU') bloq_desc
       WHERE a.fec_desb IS NULL
         AND a.clie_oid_clie = ln_oidclie
         AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
         AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
         AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
         AND d.cod_proc_bloq = ln_indfacrefac
         AND c.cod_acci_bloq = 'FN'
         AND a.tibq_oid_tipo_bloq = bloq_desc.val_oid;
  
    TYPE motbloqdiarectab IS TABLE OF c_motivosbloqueo%ROWTYPE INDEX BY BINARY_INTEGER;
    motbloqrecord motbloqdiarectab;
  
    TYPE t_numlote IS TABLE OF int_solic_conso_contr_desar.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_contr_desar.sec_nume_docu%TYPE;
    TYPE t_numdocuiden IS TABLE OF int_solic_conso_contr_desar.num_docu_iden%TYPE;
  
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_numdocuiden   t_numdocuiden;
  
    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
  
    j BINARY_INTEGER := 0;
  
    contador NUMBER := 0;
    numero   NUMBER := 0;
  
    verifica BOOLEAN;
    loidclie NUMBER(12);
  
    j BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_bloqueocliente;
    LOOP
      FETCH c_bloqueocliente BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_numdocuiden LIMIT w_filas;
    
      IF v_sec_nume_docu.count > 0 THEN
      
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
        
          verifica := TRUE;
        
          BEGIN
          
            SELECT iden.clie_oid_clie
              INTO loidclie
              FROM mae_clien_ident iden
             WHERE iden.num_docu_iden = v_numdocuiden(j)
               AND iden.val_iden_docu_prin = 1;
          
          EXCEPTION
            WHEN OTHERS THEN
              loidclie := 0;
          END;
        
          SELECT COUNT(*)
            INTO contador
            FROM mae_clien_bloqu
           WHERE fec_desb IS NULL
             AND clie_oid_clie = loidclie;
        
          IF contador > 0 AND loidclie <> 0 THEN
          
            SELECT COUNT(*)
              INTO numero
              FROM mae_clien_bloqu       a,
                   mae_accio_proce_bloqu b,
                   mae_accio_bloqu       c,
                   mae_proce_bloqu       d
             WHERE a.fec_desb IS NULL
               AND a.clie_oid_clie = loidclie
               AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
               AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
               AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
               AND d.cod_proc_bloq = 'FA'
               AND c.cod_acci_bloq = 'FS';
          
            IF (numero <> contador) THEN
              verifica := FALSE;
            
              OPEN c_motivosbloqueo(loidclie, 'FA');
              LOOP
                FETCH c_motivosbloqueo BULK COLLECT
                  INTO motbloqrecord LIMIT w_filas;
                IF motbloqrecord.count > 0 THEN
                  FOR i IN motbloqrecord.first .. motbloqrecord.last
                  LOOP
                    -- adiciona mensajes de sto      
                    sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),
                                                         v_numlote(j),
                                                         'Motivo : ' || motbloqrecord(i)
                                                         .val_i18n);
                  END LOOP;
                END IF;
                EXIT WHEN c_motivosbloqueo%NOTFOUND;
              END LOOP;
              CLOSE c_motivosbloqueo;
            END IF;
          END IF;
        
          IF (verifica) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_bloqueocliente%NOTFOUND;
    
    END LOOP;
    CLOSE c_bloqueocliente;
  
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR STO_PR_CED_BLOQU_CLIEN: ' ||
                              ls_sqlerrm);
    
  END sto_pr_ced_bloqu_clien;
END sto_pkg_proce_valid_ced;
/
