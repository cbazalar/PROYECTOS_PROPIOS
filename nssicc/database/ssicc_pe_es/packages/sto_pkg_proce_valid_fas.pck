CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_fas IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  rows       NUMBER := 5000;
  lncanaldef seg_canal.cod_cana%TYPE := 'VD';
  lnmarcadef seg_marca.cod_marc%TYPE := 'T';

  /**************************************************************************
    Descripcion       : STO_PR_FAS_CODIG_CLIEN
                        Procedimiento de Validacion del código del Cliente
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_UNIDA_ADMIN
                        Procedimiento de Validacion de la Unidad
                        Administrativa
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_unida_admin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_ANTIG_CONSU
                        Procedimiento de Validacion de la Antiguedad de la
                        Consultora
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_antig_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_POLIZ
                        Procedimiento de Validacion de Poliza ya Registrada
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_poliz
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_TIPO_DOCUM_IDENT
                        Procedimiento de Validacion de Tipo de Documento de
                        Identidad
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_tipo_docum_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_NUMER_DOCUM_IDENT
                        Procedimiento de Validacion de Numero de Documento
                        de Identidad
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_numer_docum_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_EDAD_CONSU
                        Procedimiento de Validacion de Edad de la Consultora
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_edad_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_RECHA_OCR
                        Procedimiento de Rechazo OCR
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_RECHA_OCR
                        Procedimiento de validacion de Tipo de
                        Documento Identidad
    Fecha Creacion    : 10/06/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_sigla_docum_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion         : STO_PR_FAS_ENVIO_SICC
                        Envio de FAS a SICC
  Fecha Creacion      : 04/05/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
  Descripcion         : STO_PR_FAS_ENVIO_SICC2
                        Envio de FAS a SICC -- Nueva Version
  Fecha Creacion      : 23/08/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_envio_sicc2
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_VIGEN_REGIO
                        Procedimiento de Validacion de Vigencia por región
    Fecha Creacion    : 15/06/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_vigen_regio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_REGIO_ZONA_ARRIB
                        Procedimiento de Validacion de Vigencia por región
    Fecha Creacion    : 15/06/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_regio_zona_arrib
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
     Descripcion       : STO_PR_FAS_CLIEN_DUPLI
                         Procedimiento de Validacion de duplicidad de clientes
     Fecha Creacion    : 07/07/2011
     Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_clien_dupli
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

/**************************************************************************
    Descripcion       : STO_PR_FAS_FORMA_FECHA
                        Procedimiento de Validacion del formato de fecha
    Fecha Creacion    : 15/06/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_forma_fecha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

/**************************************************************************
    Descripcion       : STO_PR_FAS_POLIZ_REGIS
                        Procedimiento de Validacion de Poliza ya Registrada
    Fecha Creacion    : 23/08/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_poliz_regis
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_FAS_FIRM_DIFN
                        Procedimiento de Validacion del indicador de firma
    Fecha Creacion    : 27/02/2012
    Autor             : Ivan Tocto Jaimes
  ***************************************************************************/
  PROCEDURE sto_pr_fas_firm_difn
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

/**************************************************************************
    Descripcion       : STO_PR_FAS_INICI_PREIM
                        Procedimiento de Validacion de Poliza que su preimpreso
                        inicie con un caracter en particular
    Fecha Creacion    : 07/11/2012
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE STO_PR_FAS_INICI_PREIM
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

END sto_pkg_proce_valid_fas;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_fas IS

  /**************************************************************************
    Descripcion       : STO_PR_FAS_CODIG_CLIEN
                        Procedimiento de Validacion del código del Cliente
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizacodclie IS
      SELECT cons.tip_docu_iden,
             cons.num_docu_iden,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_clie IS NULL;
    --
    CURSOR c_actualizadatos IS
      SELECT clien.oid_clie,
             datos.fec_naci,
             clien.cod_sexo,
             esta.cod_esta_civi,
             cons.num_lote,
             cons.sec_nume_docu
        FROM mae_clien                   clien,
             mae_clien_datos_adici       datos,
             mae_estad_civil             esta,
             int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE clien.cod_clie = cons.cod_clie
         AND occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND clien.oid_clie = datos.clie_oid_clie
         AND datos.escv_oid_esta_civi = esta.oid_esta_civi(+);

    TYPE t_oidclie IS TABLE OF int_solic_conso_famil_segur.clie_oid_clie%TYPE;
    TYPE t_fecnaci IS TABLE OF int_solic_conso_famil_segur.fec_naci%TYPE;
    TYPE t_codsexo IS TABLE OF int_solic_conso_famil_segur.cod_sexo%TYPE;
    TYPE t_codestacivi IS TABLE OF int_solic_conso_famil_segur.cod_esta_civi%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_oidclie     t_oidclie;
    v_fecnaci     t_fecnaci;
    v_codsexo     t_codsexo;
    v_codestacivi t_codestacivi;
    v_numlote     t_numlote;
    v_secnumdocu  t_secnumdocu;

   TYPE t_tip_docu_idenc IS TABLE OF int_solic_conso_famil_segur.tip_docu_iden%TYPE;
   TYPE t_num_docu_idenc IS TABLE OF int_solic_conso_famil_segur.num_docu_iden%TYPE;
   TYPE t_numlotec       IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
   TYPE t_secnumdocuc    IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

   v_tip_docu_idenc t_tip_docu_idenc;
   v_num_docu_idenc t_num_docu_idenc;
   v_numlotec       t_numlotec;
   v_secnumdocuc    t_secnumdocuc;

   i BINARY_INTEGER := 0;
   j BINARY_INTEGER := 0;

   vs_cod_clie      mae_clien.cod_clie%TYPE;
    ls_cedula_cliente VARCHAR2(3);

  BEGIN
    -- obtiene el parametro para identificar si viene la cedula en el campo codigo_cliente
    ls_cedula_cliente := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_CEDUL_CLIEN');
    
    -- Evalua si esta activa la funcionalidad para el pais
    if ls_cedula_cliente = 'S' then
      
      -- actualiza el codigo de cliente a los que tengan numero de 
      --documento de identidad en el el codigo de cliente 
     update int_solic_conso_famil_segur f
        set f.num_docu_iden = f.cod_clie,
            f.cod_clie = (select m.cod_clie 
                            from mae_clien_ident x,
                                 mae_clien m 
                           where ltrim(f.cod_clie,'0')=ltrim(x.num_docu_iden,'0')        
                             and m.oid_clie = x.clie_oid_clie
                             and x.val_iden_docu_prin = 1
                             and rownum = 1),
             f.tip_docu_iden = (select td.cod_tipo_docu
                                  from mae_clien_ident x,
                                       mae_tipo_docum td
                                 where ltrim(f.cod_clie,'0')=ltrim(x.num_docu_iden,'0')        
                                   and x.val_iden_docu_prin = 1
                                   and td.oid_tipo_docu = x.tdoc_oid_tipo_docu
                                   and rownum = 1)                 
      where exists (select 1 from mae_clien_ident x where ltrim(f.cod_clie,'0') = ltrim(x.num_docu_iden,'0'))
        and exists (select 1 from sto_tmp_docum_digit occ where occ.sec_nume_docu = f.sec_nume_docu);
        
     ----  
     
      update sto_docum_digit f
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
         and f.cod_tipo_docu = pscodigotipodoc;
        
    end if;


    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  OPEN c_actualizacodclie;
    LOOP
      FETCH c_actualizacodclie BULK COLLECT
        INTO v_tip_docu_idenc,
             v_num_docu_idenc,
             v_numlotec,
             v_secnumdocuc LIMIT rows;

      IF v_secnumdocuc.count > 0 THEN
        -- Actualizamos el codigo de cliente a las que tengan
        -- correctamente el numero de documento de identidad
        FOR j IN v_secnumdocuc.first .. v_secnumdocuc.last
        LOOP
            -- Se intenta recuperar el codigo de consultora
            -- en base a su numero de documento de identidad
          SELECT MAX(m.cod_clie)
            INTO vs_cod_clie
            FROM mae_clien       m,
                   mae_clien_ident ci,
                   mae_tipo_docum td
           WHERE ci.clie_oid_clie = m.oid_clie
             AND td.oid_tipo_docu = ci.tdoc_oid_tipo_docu
             AND td.cod_tipo_docu = v_tip_docu_idenc(j)
             AND ltrim(ci.num_docu_iden,
                       '0') = ltrim(v_num_docu_idenc(j),
                                    '0');

          IF vs_cod_clie IS NOT NULL THEN

                -- Actualiza el valor en la entidad de familia segura
            UPDATE int_solic_conso_famil_segur s
               SET s.cod_clie = vs_cod_clie
             WHERE s.sec_nume_docu = v_secnumdocuc(j);
                -- Actualiza el valor en STO
            UPDATE sto_docum_digit dd
               SET dd.cod_clie = vs_cod_clie
             WHERE dd.sec_nume_docu = v_secnumdocuc(j);

            END IF;

        END LOOP;
      END IF;

      EXIT WHEN c_actualizacodclie%NOTFOUND;
    END LOOP;
    CLOSE c_actualizacodclie;

    ----------------

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_oidclie,
             v_fecnaci,
             v_codsexo,
             v_codestacivi,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          UPDATE int_solic_conso_famil_segur
             SET clie_oid_clie = v_oidclie(i),
                 fec_naci      = v_fecnaci(i),
                 cod_sexo      = v_codsexo(i),
                 cod_esta_civi = v_codestacivi(i)
           WHERE sec_nume_docu = v_secnumdocu(i)
             AND num_lote = v_numlote(i);

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);

        END LOOP;
      END IF;

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_CODIG_CLIEN: ' || ls_sqlerrm);
  END sto_pr_fas_codig_clien;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_UNIDA_ADMIN
                        Procedimiento de Validacion de la Unidad
                        Administrativa
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_unida_admin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT reg.cod_regi,
             zon.cod_zona,
             zon.oid_zona,
             reg.oid_regi,
             cons.num_lote,
             cons.sec_nume_docu
        FROM mae_clien                   clie,
             mae_clien_unida_admin       cua,
             zon_terri_admin             zta,
             zon_regio                   reg,
             zon_zona                    zon,
             zon_secci                   sec,
             int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE clie.cod_clie = cons.cod_clie
         AND occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND clie.oid_clie = cua.clie_oid_clie
         AND cua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zta.zscc_oid_secc = sec.oid_secc
         AND zon.oid_zona = sec.zzon_oid_zona
         AND reg.oid_regi = zon.zorg_oid_regi
         AND cua.ind_acti = 1;

    TYPE t_cod_regi IS TABLE OF zon_regio.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF zon_zona.cod_zona%TYPE;
    TYPE t_oidzona IS TABLE OF int_solic_conso_famil_segur.zzon_oid_zona%TYPE;
    TYPE t_oidregi IS TABLE OF int_solic_conso_famil_segur.zorg_oid_regi%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_cod_regi t_cod_regi;
    v_cod_zona t_cod_zona;
    v_oidzona    t_oidzona;
    v_oidregi    t_oidregi;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_cod_regi,
             v_cod_zona,
             v_oidzona,
             v_oidregi,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          UPDATE int_solic_conso_famil_segur
             SET zzon_oid_zona = v_oidzona(i),
                 zorg_oid_regi = v_oidregi(i)
           WHERE sec_nume_docu = v_secnumdocu(i)
             AND num_lote = v_numlote(i);

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE,
                 occ.cod_regi           = v_cod_regi(i),
                 occ.cod_zona           = v_cod_zona(i)
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);

        END LOOP;
      END IF;

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_UNIDA_ADMIN: ' || ls_sqlerrm);
  END sto_pr_fas_unida_admin;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_ANTIG_CONSU
                        Procedimiento de Validacion de la Antiguedad de la
                        Consultora
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_antig_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.cam_proc,
             cons.zorg_oid_regi,
             cons.clie_oid_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_camproc IS TABLE OF int_solic_conso_famil_segur.cam_proc%TYPE;
    TYPE t_oidregi IS TABLE OF int_solic_conso_famil_segur.zorg_oid_regi%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_famil_segur.clie_oid_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_camproc    t_camproc;
    v_oidregi    t_oidregi;
    v_oidclie    t_oidclie;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i              BINARY_INTEGER := 0;
    existe         BOOLEAN := TRUE;
    lncampanterior NUMBER;
    lncampanti     NUMBER;
    -- lscampanti      int_solic_conso_famil_segur.cam_anti%TYPE;
    lncampregistro int_solic_conso_famil_segur.cam_regis%TYPE;
    lncodpericonta seg_perio_corpo.cod_peri%TYPE;
    lncontador1    NUMBER;
    lncontador2    NUMBER;
    lnidpais       seg_pais.oid_pais%TYPE;
    lnidcanal      seg_canal.oid_cana%TYPE;
    lnidmarca      seg_marca.oid_marc%TYPE;

  BEGIN

    /* obteniendos ids */
    lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal(lncanaldef);
    lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca(lnmarcadef);

    --lncampanterior := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_CAMP_ANTI');
    SELECT nvl(pp.num_camp_anti,
               0)
      INTO lncampanterior
      FROM sgr_famse_param pp
     WHERE pp.est_regi = '1';

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_camproc,
             v_oidregi,
             v_oidclie,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;

          -- Paso Pedido
          SELECT COUNT(1)
            INTO lncontador1
            FROM ped_solic_cabec     a,
                 ped_tipo_solic_pais b,
                 ped_tipo_solic      c,
                 ped_solic_cabec     d,
                 cra_perio           e,
                 seg_perio_corpo     f
           WHERE a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
             AND b.tsol_oid_tipo_soli = c.oid_tipo_soli
             AND c.cod_tipo_soli = 'SOC'
             AND a.soca_oid_soli_cabe = d.oid_soli_cabe
             AND d.esso_oid_esta_soli <> 4
             AND a.perd_oid_peri = e.oid_peri
             AND e.peri_oid_peri = f.oid_peri
             AND f.cod_peri = v_camproc(i)
             AND a.clie_oid_clie = v_oidclie(i);

          -- Cerro region
          SELECT COUNT(1)
            INTO lncontador2
            FROM fac_contr_cierr a,
                 cra_perio       b,
                 seg_perio_corpo f
           WHERE a.perd_oid_peri = b.oid_peri
             AND b.peri_oid_peri = f.oid_peri
             AND f.cod_peri = v_camproc(i)
             AND zorg_oid_regi = v_oidregi(i);

          IF (lncontador1 > 0 OR lncontador2 > 0) THEN
            --lncampregistro := GEN_PKG_GENER.STO_FN_CALCU_PERIO_NPOST(v_camproc(i), 1);
            lncampregistro := per_pkg_repor_perce.per_fn_obtie_perio(v_camproc(i),
                                                                     lnidpais,
                                                                     lnidmarca,
                                                                     lnidcanal,
                                                                     1);
          ELSE
            lncampregistro := v_camproc(i);
          END IF;

          BEGIN
            SELECT c.cod_peri
              INTO lncodpericonta
              FROM mae_clien_prime_conta a,
                   cra_perio             b,
                   seg_perio_corpo       c
             WHERE a.clie_oid_clie = v_oidclie(i)
               AND a.perd_oid_peri = b.oid_peri
               AND b.peri_oid_peri = c.oid_peri;

          EXCEPTION
            WHEN no_data_found THEN
              lncodpericonta := NULL;
          END;

          -- Si no existe en MAE_CLIEN_PRIME_CONTA se rechaza
          IF lncodpericonta IS NULL THEN
            existe := FALSE;
          ELSE
            --lncampanti :=  sto_fn_obtie_numer_perio(lncodpericonta, lncampregistro);
            lncampanti := ven_pkg_repor.ven_fn_devue_nume_campa(lncodpericonta,
                                                                lncampregistro,
                                                                lnidpais,
                                                                lnidmarca,
                                                                lnidcanal);

            -- Siempre actualizo el campo, asi no cumpla la validacion
            UPDATE int_solic_conso_famil_segur
               SET cam_regis = lncampregistro,
                   cam_anti  = lncampanti --lscampanti
             WHERE sec_nume_docu = v_secnumdocu(i)
               AND num_lote = v_numlote(i);

            IF (lncampanti < lncampanterior) THEN
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;
          END IF;

          IF (existe) THEN

            /*UPDATE int_solic_conso_famil_segur
              SET cam_regis = lncampregistro,
                  cam_anti = lncampanti--lscampanti
            WHERE sec_nume_docu = v_secnumdocu(i)
              AND num_lote = v_numlote(i)
              AND cod_pais = v_codpais(i);*/

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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_ANTIG_CONSU: ' || ls_sqlerrm);
  END sto_pr_fas_antig_consu;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_POLIZ
                        Procedimiento de Validacion de Poliza ya Registrada
                        para una misma consultora
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_poliz
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS (SELECT NULL
                           FROM sgr_famse_poliz_regis fpr
                          WHERE fpr.cod_clie = cons.cod_clie
                            AND fpr.est_poli IN ('1',
                                                 '3')
                 AND fpr.est_regi != '9');

    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FORALL i IN 1 .. v_secnumdocu.count
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_POLIZ: ' || ls_sqlerrm);
  END sto_pr_fas_poliz;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_TIPO_DOCUM_IDENT
                        Procedimiento de Validacion de Tipo de Documento de
                        Identidad
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_tipo_docum_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.tip_docu_iden,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_tipdocuide IS TABLE OF int_solic_conso_famil_segur.tip_docu_iden%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_tipdocuide t_tipdocuide;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i        BINARY_INTEGER := 0;
    existe   BOOLEAN := TRUE;
    contador NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_tipdocuide,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;
          BEGIN

            contador := 0;

            SELECT COUNT(1)
              INTO contador
              FROM mae_tipo_docum
             WHERE cod_tipo_docu = v_tipdocuide(i);

            IF (contador > 0) THEN
              --existe := FALSE;
              -- Si devuelve registros, es xq tipo_doc es != null, existe
              -- en la tabla MAE_TIPO_DOCUM y debe pasar la validacion
              existe := TRUE;
            ELSE
              --existe := TRUE;
              existe := FALSE;
            END IF;

          END;

          IF (existe) THEN

            UPDATE int_solic_conso_famil_segur
            -- seteo el oid_de tipo documento
               SET tdoc_oid_tipo_docu =
                   (SELECT oid_tipo_docu FROM mae_tipo_docum WHERE cod_tipo_docu = v_tipdocuide(i)) --v_tipdocuide(i)
             WHERE sec_nume_docu = v_secnumdocu(i)
               AND num_lote = v_numlote(i);

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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_TIPO_DOCUM_IDENT: ' || ls_sqlerrm);
  END sto_pr_fas_tipo_docum_ident;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_NUMER_DOCUM_IDENT
                        Procedimiento de Validacion de Numero de Documento
                        de Identidad
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_numer_docum_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_docu_iden,
             cons.clie_oid_clie,
             cons.tdoc_oid_tipo_docu,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numdociden IS TABLE OF int_solic_conso_famil_segur.num_docu_iden%TYPE;
    TYPE t_oidclien IS TABLE OF int_solic_conso_famil_segur.clie_oid_clie%TYPE;
    TYPE t_oidtipodoc IS TABLE OF int_solic_conso_famil_segur.tdoc_oid_tipo_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_numdociden t_numdociden;
    v_oidclien   t_oidclien;
    v_oidtipodoc t_oidtipodoc;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i        BINARY_INTEGER := 0;
    existe   BOOLEAN := TRUE;
    contador NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numdociden,
             v_oidclien,
             v_oidtipodoc,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;
          BEGIN

            contador := 0;

            SELECT COUNT(1)
              INTO contador
              FROM mae_clien_ident
             WHERE lpad(num_docu_iden,
                        15,
                        '0') = lpad(v_numdociden(i),
                                    15,
                                    '0')
               AND clie_oid_clie = v_oidclien(i)
               AND tdoc_oid_tipo_docu = v_oidtipodoc(i);

            IF (contador = 0) THEN
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;

          END;

          IF (existe) THEN
          
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_NUMER_DOCUM_IDENT: ' || ls_sqlerrm);
  END sto_pr_fas_numer_docum_ident;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_EDAD_CONSU
                        Procedimiento de Validacion de Edad de la Consultora
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_edad_consu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.fec_naci,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_fec_naci IS TABLE OF int_solic_conso_famil_segur.fec_naci%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_fec_naci   t_fec_naci;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i            BINARY_INTEGER := 0;
    existe       BOOLEAN := TRUE;
    lnedadminima NUMBER;
    lnedadmaxima NUMBER;
    lnedadactual NUMBER;

  BEGIN

    --lnedadminima := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_EDAD_MINI_CONS');
    --lnedadmaxima := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_EDAD_MAXI_CONS');
    BEGIN
      SELECT nvl(fp.eda_mini_segu,
                 0),
             nvl(fp.eda_maxi_segu,
                 0)
        INTO lnedadminima,
             lnedadmaxima
        FROM sgr_famse_poliz fp
       WHERE fp.est_regi = '1'
         AND fp.ind_acti = '1';
    EXCEPTION
      WHEN too_many_rows THEN
        SELECT nvl(fp.eda_mini_segu,
                   0),
               nvl(fp.eda_maxi_segu,
                   0)
          INTO lnedadminima,
               lnedadmaxima
          FROM sgr_famse_poliz fp
         WHERE fp.est_regi = '1'
           AND fp.ind_acti = '1'
           AND rownum = 1;
    END;

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_fec_naci,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;

          --calculando la edad actual
          lnedadactual := nvl(floor(months_between(trunc(SYSDATE), /*to_date(*/
                                                   v_fec_naci(i) /*, 'dd/MM/yyyy')*/) / 12),
                              0);

          IF (lnedadactual > lnedadmaxima OR lnedadactual < lnedadminima) THEN
            existe := FALSE;
          ELSE
            existe := TRUE;
          END IF;

            UPDATE int_solic_conso_famil_segur
               SET val_edad = lnedadactual
             WHERE sec_nume_docu = v_secnumdocu(i)
               AND num_lote = v_numlote(i);


          IF (existe) THEN


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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_EDAD_CONSU: ' || ls_sqlerrm);
  END sto_pr_fas_edad_consu;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_RECHA_OCR
                        Procedimiento de Rechazo OCR
    Fecha Creacion    : 02/05/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.cod_esta_ocr,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codesta IS TABLE OF int_solic_conso_famil_segur.cod_esta_ocr%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_codesta    t_codesta;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_codesta,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;

          IF (v_codesta(i) = '02') THEN
            -- es rechazado
            existe := FALSE;
          ELSE
            existe := TRUE;
          END IF;

          IF (existe) THEN
          
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_RECHA_OCR: ' || ls_sqlerrm);
  END sto_pr_fas_recha_ocr;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_RECHA_OCR
                        Procedimiento de validacion de Tipo de
                        Documento Identidad
    Fecha Creacion    : 10/06/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_sigla_docum_ident
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.tip_docu_iden,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_tipo_docu_iden IS TABLE OF int_solic_conso_famil_segur.tip_docu_iden%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_tipo_docu_iden t_tipo_docu_iden;
    v_numlote        t_numlote;
    v_secnumdocu     t_secnumdocu;

    i             BINARY_INTEGER := 0;
    existe        BOOLEAN := TRUE;
    lsvaltipodocu sto_param_gener_occrr.val_param%TYPE;
    vscodtipodocu mae_tipo_docum.cod_tipo_docu%TYPE;
    lnidpais      seg_pais.oid_pais%TYPE;

  BEGIN

    lnidpais      := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lsvaltipodocu := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                          'STO_SIGL_DOCU_PRIN');

    BEGIN
      SELECT td.cod_tipo_docu
        INTO vscodtipodocu
        FROM mae_tipo_docum td
       WHERE td.pais_oid_pais = lnidpais
         AND upper(td.val_sigl) = lsvaltipodocu;
    EXCEPTION
      WHEN no_data_found THEN
        vscodtipodocu := NULL;
    END;

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_tipo_docu_iden,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;

          IF (vscodtipodocu <> v_tipo_docu_iden(i)) THEN
            existe := FALSE;
          ELSE
            existe := TRUE;
          END IF;

          IF (existe) THEN
          
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_SIGLA_DOCUM_IDENT: ' || ls_sqlerrm);
  END sto_pr_fas_sigla_docum_ident;

/**************************************************************************
  Descripcion         : STO_PR_FAS_ENVIO_SICC
                        Envio de FAS a SICC
  Fecha Creacion      : 04/05/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_fas_envio_sicc2
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_enviofas IS
      SELECT cons.cod_clie,
             cons.num_docu,
             cons.clie_oid_clie,
             cons.fec_proc,
             cons.tip_docu_iden,
             cons.num_docu_iden,
             cons.cod_sexo,
             cons.cod_esta_civi,
             cons.fec_naci,
             cons.val_edad,
             cons.can_coas,
             cons.cam_anti,
             cons.cam_regis,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_docu_ben1,
             cons.num_docu_ben1,
             cons.nom_ben1
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_famil_segur.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_famil_segur.num_docu%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_famil_segur.clie_oid_clie%TYPE;
    TYPE t_fecproc IS TABLE OF int_solic_conso_famil_segur.fec_proc%TYPE;
    TYPE t_tipdocide IS TABLE OF int_solic_conso_famil_segur.tip_docu_iden%TYPE;
    TYPE t_numdocide IS TABLE OF int_solic_conso_famil_segur.num_docu_iden%TYPE;
    TYPE t_codsexo IS TABLE OF int_solic_conso_famil_segur.cod_sexo%TYPE;
    TYPE t_codestciv IS TABLE OF int_solic_conso_famil_segur.cod_esta_civi%TYPE;
    TYPE t_fecnaci IS TABLE OF int_solic_conso_famil_segur.fec_naci%TYPE;
    TYPE t_valedad IS TABLE OF int_solic_conso_famil_segur.val_edad%TYPE;
    TYPE t_cancoas IS TABLE OF int_solic_conso_famil_segur.can_coas%TYPE;
    TYPE t_camanti IS TABLE OF int_solic_conso_famil_segur.cam_anti%TYPE;
    TYPE t_camregi IS TABLE OF int_solic_conso_famil_segur.cam_regis%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;
    TYPE t_tip_docu_ben1 IS TABLE OF int_solic_conso_famil_segur.tip_docu_ben1%TYPE;
    TYPE t_num_docu_ben1 IS TABLE OF int_solic_conso_famil_segur.num_docu_ben1%TYPE;
    TYPE t_nom_ben1 IS TABLE OF int_solic_conso_famil_segur.nom_ben1%TYPE;

    v_codclie    t_codclie;
    v_numdocu    t_numdocu;
    v_oidclie    t_oidclie;
    v_fecproc    t_fecproc;
    v_tipdocide  t_tipdocide;
    v_numdocide  t_numdocide;
    v_codsexo    t_codsexo;
    v_codestciv  t_codestciv;
    v_fecnaci    t_fecnaci;
    v_valedad    t_valedad;
    v_cancoas    t_cancoas;
    v_camanti    t_camanti;
    v_camregi    t_camregi;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_tip_docu_ben1 t_tip_docu_ben1;
    v_num_docu_ben1 t_num_docu_ben1;
    v_nom_ben1      t_nom_ben1;

    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

    vs_cod_peri sgr_famse_poliz_regis.cam_proc%TYPE;
    v_scodpoliza sgr_famse_poliz.cod_poli%TYPE;

  BEGIN

    -- Incluir la campanha de proceso
    SELECT cod_peri
      INTO vs_cod_peri
      FROM bas_ctrl_fact a
     WHERE a.cod_pais = pscodigopais
       AND a.sta_camp = '0'
       AND ind_camp_act = '1';

    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);

    SELECT s.cod_poli
      INTO v_scodpoliza
      FROM sgr_famse_poliz s
     WHERE s.ind_acti = 1
       AND rownum = 1;

    OPEN c_enviofas;
    LOOP
      FETCH c_enviofas BULK COLLECT
        INTO v_codclie,
             v_numdocu,
             v_oidclie,
             v_fecproc,
             v_tipdocide,
             v_numdocide,
             v_codsexo,
             v_codestciv,
             v_fecnaci,
             v_valedad,
             v_cancoas,
             v_camanti,
             v_camregi,
             v_numlote,
             v_secnumdocu,
             v_tip_docu_ben1,
             v_num_docu_ben1,
             v_nom_ben1 LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          IF (length(v_tipdocide(i)) = 1) THEN
            v_tipdocide(i) := '0' || v_tipdocide(i);
          END IF;

          BEGIN
            INSERT INTO sgr_famse_poliz_regis
              (poli_cod_poli,
               cod_clie,
               num_poli,
               clie_oid_clie,
               fec_soli,
               tip_docu_iden,
               num_docu_iden,
               val_sexo,
               est_civi,
               fec_naci,
               eda_clie,
               num_fami_coas,
               num_camp_anti,
               cam_regi,
               cam_acti,
               est_poli,
               cam_canc,
               fec_canc,
               ind_orig_regi,
               usu_modi,
               fec_modi,
               est_regi,
               cam_proc)
            VALUES
              (v_scodpoliza,
               v_codclie(i),
               v_numdocu(i),
               v_oidclie(i),
               v_fecproc(i),
               v_tipdocide(i),
               v_numdocide(i),
               v_codsexo(i),
               v_codestciv(i),
               v_fecnaci(i),
               v_valedad(i),
               nvl(v_cancoas(i),
                   0),
               v_camanti(i),
               v_camregi(i),
               NULL,
               '1',
               NULL,
               NULL,
               'O',
               psusuario,
               SYSDATE,
               '1',
               vs_cod_peri);
            --
            IF v_num_docu_ben1(i) IS NOT NULL THEN
              INSERT INTO sgr_famse_benef_poliz
                (poli_cod_poli,
                 cod_clie,
                 polr_num_poli,
                 cor_bene,
                 tip_docu_iden,
                 num_docu_iden,
                 nom_bene,
                 sta_bene,
                 usu_modi,
                 fec_modi,
                 est_regi)
              VALUES
                (v_scodpoliza,
                 v_codclie(i),
                 v_numdocu(i),
                 1,
                 (sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_TIPO_DOCU_BENE')), --v_tip_docu_ben1(i),
                 v_num_docu_ben1(i),
                 v_nom_ben1(i),
                 '1',
                 psusuario,
                 SYSDATE,
                 '1');
            END IF;
            --
          EXCEPTION
            WHEN dup_val_on_index THEN
              NULL;
          END;

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

      EXIT WHEN c_enviofas %NOTFOUND;
    END LOOP;
    CLOSE c_enviofas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_FAS_ENVIO_SICC: ' || ls_sqlerrm);
  END sto_pr_fas_envio_sicc2;

  /**************************************************************************
  Descripcion         : STO_PR_FAS_ENVIO_SICC2
                        Envio de FAS a SICC -- Nueva Version
  Fecha Creacion      : 23/08/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_enviofas IS
      SELECT cons.cod_clie,
             cons.num_docu,
             cons.clie_oid_clie,
             cons.fec_proc,
             cons.tip_docu_iden,
             cons.num_docu_iden,
             cons.cod_sexo,
             cons.cod_esta_civi,
             cons.fec_naci,
             cons.val_edad,
             cons.can_coas,
             cons.cam_anti,
             cons.cam_regis,
             cons.num_lote,
             cons.sec_nume_docu, --
             cons.tip_docu_ben1,
             cons.num_docu_ben1,
             cons.nom_ben1,
             dd.fec_digi,
             cons.ind_orig
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ,
             sto_docum_digit dd
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais
         AND occ.sec_nume_docu = dd.sec_nume_docu;

    TYPE t_codclie IS TABLE OF int_solic_conso_famil_segur.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_famil_segur.num_docu%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_famil_segur.clie_oid_clie%TYPE;
    TYPE t_fecproc IS TABLE OF int_solic_conso_famil_segur.fec_proc%TYPE;
    TYPE t_tipdocide IS TABLE OF int_solic_conso_famil_segur.tip_docu_iden%TYPE;
    TYPE t_numdocide IS TABLE OF int_solic_conso_famil_segur.num_docu_iden%TYPE;
    TYPE t_codsexo IS TABLE OF int_solic_conso_famil_segur.cod_sexo%TYPE;
    TYPE t_codestciv IS TABLE OF int_solic_conso_famil_segur.cod_esta_civi%TYPE;
    TYPE t_fecnaci IS TABLE OF int_solic_conso_famil_segur.fec_naci%TYPE;
    TYPE t_valedad IS TABLE OF int_solic_conso_famil_segur.val_edad%TYPE;
    TYPE t_cancoas IS TABLE OF int_solic_conso_famil_segur.can_coas%TYPE;
    TYPE t_camanti IS TABLE OF int_solic_conso_famil_segur.cam_anti%TYPE;
    TYPE t_camregi IS TABLE OF int_solic_conso_famil_segur.cam_regis%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;
    TYPE t_tip_docu_ben1 IS TABLE OF int_solic_conso_famil_segur.tip_docu_ben1%TYPE;
    TYPE t_num_docu_ben1 IS TABLE OF int_solic_conso_famil_segur.num_docu_ben1%TYPE;
    TYPE t_nom_ben1 IS TABLE OF int_solic_conso_famil_segur.nom_ben1%TYPE;
    TYPE t_fec_digi IS TABLE OF sto_docum_digit.fec_digi%TYPE;
    TYPE t_ind_orig IS TABLE OF int_solic_conso_famil_segur.ind_orig%TYPE;

    v_codclie    t_codclie;
    v_numdocu    t_numdocu;
    v_oidclie    t_oidclie;
    v_fecproc    t_fecproc;
    v_tipdocide  t_tipdocide;
    v_numdocide  t_numdocide;
    v_codsexo    t_codsexo;
    v_codestciv  t_codestciv;
    v_fecnaci    t_fecnaci;
    v_valedad    t_valedad;
    v_cancoas    t_cancoas;
    v_camanti    t_camanti;
    v_camregi    t_camregi;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_tip_docu_ben1 t_tip_docu_ben1;
    v_num_docu_ben1 t_num_docu_ben1;
    v_nom_ben1      t_nom_ben1;
    v_fec_digi      t_fec_digi;
    v_ind_orig      t_ind_orig;

    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

    vs_cod_peri     sgr_famse_poliz_regis.cam_proc%TYPE;
    v_scodpoliza    sgr_famse_poliz.cod_poli%TYPE;
    vnumpolireemp sgr_famse_poliz_regis.num_poli%TYPE;

  BEGIN

    -- Incluir la campanha de proceso
    SELECT cod_peri
      INTO vs_cod_peri
      FROM bas_ctrl_fact a
     WHERE a.cod_pais = pscodigopais
       AND a.sta_camp = '0'
       AND ind_camp_act = '1';

    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);

    SELECT s.cod_poli
      INTO v_scodpoliza
      FROM sgr_famse_poliz s
     WHERE s.ind_acti = 1
       AND s.est_regi = '1'
       AND rownum = 1;

    OPEN c_enviofas;
    LOOP
      FETCH c_enviofas BULK COLLECT
        INTO v_codclie,
             v_numdocu,
             v_oidclie,
             v_fecproc,
             v_tipdocide,
             v_numdocide,
             v_codsexo,
             v_codestciv,
             v_fecnaci,
             v_valedad,
             v_cancoas,
             v_camanti,
             v_camregi,
             v_numlote,
             v_secnumdocu,
             v_tip_docu_ben1,
             v_num_docu_ben1,
             v_nom_ben1,
             v_fec_digi,
             v_ind_orig LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          IF (length(v_tipdocide(i)) = 1) THEN
            v_tipdocide(i) := '0' || v_tipdocide(i);
          END IF;

          BEGIN

            BEGIN
              -- Si existe alguna poliza en estado
              -- Activa o Registrada
              SELECT r.num_poli
                INTO vnumpolireemp
                FROM sgr_famse_poliz_regis r
               WHERE r.poli_cod_poli = v_scodpoliza
                 AND r.cod_clie = v_codclie(i)
                 AND r.est_poli IN ('1',
                                    '3')
                 AND r.est_regi = '1';
            EXCEPTION
              WHEN no_data_found THEN
                -- si no existe
                vnumpolireemp := NULL;
            END;

            INSERT INTO sgr_famse_poliz_regis
              (poli_cod_poli,
               cod_clie,
               num_poli,
               clie_oid_clie,
               fec_soli,
               tip_docu_iden,
               num_docu_iden,
               val_sexo,
               est_civi,
               fec_naci,
               eda_clie,
               num_fami_coas,
               num_camp_anti,
               cam_regi,
               cam_acti,
               est_poli,
               cam_canc,
               fec_canc,
               --mov_canc,
               ind_orig_regi,
               usu_modi,
               fec_modi,
               est_regi,
               cam_proc,
               fec_formu,
               num_poli_remp)
            VALUES
              (v_scodpoliza,
               v_codclie(i),
               v_numdocu(i),
               v_oidclie(i),
               v_fec_digi(i),
               v_tipdocide(i),
               v_numdocide(i),
               nvl(v_codsexo(i),'F'),
               v_codestciv(i),
               v_fecnaci(i),
               v_valedad(i),
               nvl(v_cancoas(i),
                   0),
               v_camanti(i),
               v_camregi(i),
               NULL,
               '1',
               NULL,
               NULL,
               -- NULL,
               v_ind_orig(i),
               psusuario,
               SYSDATE,
               '1',
               vs_cod_peri,
               v_fecproc(i),
               vnumpolireemp);

            IF vnumpolireemp IS NOT NULL THEN
             -- Elimino logicamente el registro reemplazado
              /*UPDATE sgr_famse_poliz_regis pr
                 SET est_regi = '9',
                     usu_modi = psusuario,
                     fec_modi = SYSDATE
               WHERE poli_cod_poli = v_scodpoliza
                 AND cod_clie = v_codclie(i)
                 AND num_poli = vnumpolireemp;*/
                 
              UPDATE sgr_famse_poliz_regis pr
                 SET pr.est_poli = '4',
                     pr.cam_canc = vs_cod_peri,
                     pr.fec_canc = sysdate,
                     pr.moti_cod_moti_canc = '9',
                     est_regi = '1',
                     usu_modi = psusuario,
                     fec_modi = SYSDATE
               WHERE poli_cod_poli = v_scodpoliza
                 AND cod_clie = v_codclie(i)
                 AND num_poli = vnumpolireemp;
                 
            END IF;

          EXCEPTION
            WHEN dup_val_on_index THEN
              NULL;
          END;

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

      EXIT WHEN c_enviofas %NOTFOUND;
    END LOOP;
    CLOSE c_enviofas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_FAS_ENVIO_SICC: ' || ls_sqlerrm);
  END sto_pr_fas_envio_sicc;

  --
  /**************************************************************************
    Descripcion       : STO_PR_FAS_VIGEN_REGIO
                        Procedimiento de Validacion de Vigencia por región
    Fecha Creacion    : 15/06/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_vigen_regio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.zorg_oid_regi,
             cons.cam_proc,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_oid_regi IS TABLE OF int_solic_conso_famil_segur.zorg_oid_regi%TYPE;
    TYPE t_cam_proc IS TABLE OF int_solic_conso_famil_segur.cam_proc%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_oid_regi   t_oid_regi;
    v_cam_proc   t_cam_proc;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i      BINARY_INTEGER := 0;
    cont   NUMBER := 0;
    existe BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_oid_regi,
             v_cam_proc,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;

          SELECT COUNT(1)
            INTO cont
            FROM sgr_famse_vigen_poliz vp,
                 zon_regio             zr
           WHERE vp.cam_vige = v_cam_proc(i)
             AND zr.oid_regi = v_oid_regi(i)
             AND zr.cod_regi = vp.cod_regi
             AND vp.est_regi = '1';

          -- Si no existe configurada una campaña para
          -- una region en esta tabla, se rechaza
          IF cont = 0 THEN
            existe := FALSE;
          END IF;

          IF (existe) THEN
          
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_VIGEN_REGIO: ' || ls_sqlerrm);
  END sto_pr_fas_vigen_regio;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_REGIO_ZONA_ARRIB
                        Procedimiento de Validacion de Vigencia por región
    Fecha Creacion    : 15/06/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_regio_zona_arrib
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.cod_pais,
             cons.zorg_oid_regi,
             cons.zzon_oid_zona,
             cons.cod_regi_orig_docu,
             cons.cod_zona_orig_docu,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_famil_segur.cod_pais%TYPE;
    TYPE t_oid_regi IS TABLE OF int_solic_conso_famil_segur.zorg_oid_regi%TYPE;
    TYPE t_oid_zona IS TABLE OF int_solic_conso_famil_segur.zzon_oid_zona%TYPE;

    TYPE t_cod_regi IS TABLE OF int_solic_conso_famil_segur.cod_regi_orig_docu%TYPE;
    TYPE t_cod_zona IS TABLE OF int_solic_conso_famil_segur.cod_zona_orig_docu%TYPE;

    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_codpais  t_codpais;
    v_oid_regi t_oid_regi;
    v_oid_zona t_oid_zona;
    v_cod_regi t_cod_regi;
    v_cod_zona t_cod_zona;
    --   v_cam_proc    t_cam_proc;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;

    vcodregi zon_regio.cod_regi%TYPE;
    vcodzona zon_zona.cod_zona%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_codpais,
             v_oid_regi,
             v_oid_zona,
             v_cod_regi,
             v_cod_zona,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;

          -- Se obtiene los codigos de region y zona de los oid's en caso existan
          BEGIN
            SELECT cod_regi INTO vcodregi FROM zon_regio r WHERE r.oid_regi = v_oid_regi(i);
          EXCEPTION
            WHEN no_data_found THEN
              vcodregi := NULL;
          END;

          BEGIN
            SELECT z.cod_zona INTO vcodzona FROM zon_zona z WHERE z.oid_zona = v_oid_zona(i);
          EXCEPTION
            WHEN no_data_found THEN
              vcodzona := '';
          END;

          -- Si los codigos obtenidos coinciden con los ingresados, pasa la validacion
          IF v_cod_regi(i) = vcodregi AND v_cod_zona(i) = vcodzona THEN
            existe := TRUE;
          ELSE
            existe := FALSE;
          END IF;

          IF (existe) THEN
          
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_REGIO_ZONA_ARRIB: ' || ls_sqlerrm);
  END sto_pr_fas_regio_zona_arrib;

  /**************************************************************************
     Descripcion       : STO_PR_FAS_CLIEN_DUPLI
                         Procedimiento de Validacion de duplicidad de clientes
     Fecha Creacion    : 07/07/2011
     Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_clien_dupli
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS (SELECT NULL
                FROM int_solic_conso_famil_segur cons2,
                     sto_docum_digit             occ2
               WHERE cons2.sec_nume_docu = occ2.sec_nume_docu
                 AND cons2.num_lote = occ2.num_lote
                 AND cons2.cod_clie = cons.cod_clie
                 AND cons2.sec_nume_docu <> occ.sec_nume_docu
                 AND occ2.ind_envi = '0'
                 AND occ2.ind_rech = '0');

    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    i            BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_numlote.count > 0 THEN
        FOR i IN v_numlote.first .. v_numlote.last
        LOOP
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);
        END LOOP;
      END IF;

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_CLIEN_DUPLI: ' || ls_sqlerrm);
  END sto_pr_fas_clien_dupli;

/**************************************************************************
    Descripcion       : STO_PR_FAS_FORMA_FECHA
                        Procedimiento de Validacion del formato de fecha
    Fecha Creacion    : 15/06/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_forma_fecha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      -- Si la fecha no es null, es porque llego en el archivo
      -- con un formato correcto
      SELECT cons.fec_proc,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.fec_proc IS NOT NULL;

    TYPE t_fec_proc   IS TABLE OF int_solic_conso_famil_segur.fec_proc%TYPE;
    TYPE t_numlote    IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_fec_proc   t_fec_proc;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i      BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_fec_proc,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_fec_proc.count > 0 THEN
        FORALL i IN 1 .. v_fec_proc.count
        
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_FORMA_FECHA: ' || ls_sqlerrm);
  END sto_pr_fas_forma_fecha;

/**************************************************************************
    Descripcion       : STO_PR_FAS_POLIZ_REGIS
                        Procedimiento de Validacion de Poliza ya Registrada
                        con un mismo preimpreso
    Fecha Creacion    : 23/08/2011
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_fas_poliz_regis
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         -- Que el numero de certificado no se encuentre registrado
         AND NOT EXISTS (SELECT NULL
                           FROM sgr_famse_poliz_regis fpr
                          WHERE fpr.num_poli = cons.num_docu
                 AND fpr.est_regi IN ('1',
                                      '3'));

    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN
      
        FORALL i IN 1 .. v_secnumdocu.count
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_POLIZ_REGIS: ' || ls_sqlerrm);
  END sto_pr_fas_poliz_regis;

  /**************************************************************************
    Descripcion       : STO_PR_FAS_FIRM_DIFN
                        Procedimiento de Validacion del indicador de firma
    Fecha Creacion    : 27/02/2012
    Autor             : Ivan Tocto Jaimes
  ***************************************************************************/
  PROCEDURE sto_pr_fas_firm_difn
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      -- campo ind_firm [S]: Vacio, [N]: Lleno
      -- Si viene la firma, pasa la validacion
      SELECT cons.fec_proc,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND nvl(cons.ind_firm,
                 'S') = 'N';

    TYPE t_fec_proc IS TABLE OF int_solic_conso_famil_segur.fec_proc%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_fec_proc   t_fec_proc;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_fec_proc,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_fec_proc.count > 0 THEN
        FORALL i IN 1 .. v_fec_proc.count
        
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_FIRM_DIFN: ' || ls_sqlerrm);

  END sto_pr_fas_firm_difn;

/***************************************************************************
    Descripcion       : STO_PR_FAS_INICI_PREIM
                        Procedimiento de Validacion de Poliza que su
                        preimpreso inicie con un caracter en particular
    Fecha Creacion    : 07/11/2012
    Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE STO_PR_FAS_INICI_PREIM
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos(psnumcaracteres number) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_famil_segur cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         -- analiza los 2 primeros caracteres del preimpreso
         AND substr(nvl(cons.num_docu,'0'),0,psnumcaracteres) in (select o.val_param 
                                                          from sto_param_gener_occrr o
                                                         where o.cod_pais = pscodigopais
                                                           and o.cod_para like 'STO_FAS_CARAC%') ;

    TYPE t_numlote IS TABLE OF int_solic_conso_famil_segur.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_famil_segur.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    i BINARY_INTEGER := 0;

    lsnumcaracteres number;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    /* Obtiene el numero de caracteres a evaluar del preimpreso de la poliza */
    lsnumcaracteres := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'STO_FAS_NUM_CARAC'),0);

    OPEN c_actualizadatos(lsnumcaracteres);
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FORALL i IN 1 .. v_secnumdocu.count
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

      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

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
                              'ERROR STO_PR_FAS_POLIZ: ' || ls_sqlerrm);
  END STO_PR_FAS_INICI_PREIM;

END sto_pkg_proce_valid_fas;
/
