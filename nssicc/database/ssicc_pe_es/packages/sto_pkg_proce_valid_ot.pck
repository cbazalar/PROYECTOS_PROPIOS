CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_ot AS
  -- Author  : PEEXTDOLIVA
  -- Created : 07/09/2010 08:53:00 a.m.
  -- Purpose : Procesos de Validaciones de Tipo Documento Orden de Transporte (OT)

  /**************************************************************************
   Descripcion       : Validacion 1 - sto_pr_ot_tipo_orden
                       Procedimiento de Validacion del tipo de orden
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_tipo_orden
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : Validacion 2 - sto_pr_ot_codig_noved
                       Procedimiento de Validacion del codigo de novedad
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_codig_noved
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : Validacion 3 - sto_pr_ot_numer_orden
                       Procedimiento de Validacion del numero de orden
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_numer_orden
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : Validacion 4 - sto_pr_ot_centr_acopi
                       Procedimiento de Validacion del codigo de novedad
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_centr_acopi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : Validacion 5 - sto_pr_ot_recib_confo
                       Procedimiento de Validacion de Recibió conforme
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_recib_confo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : Validacion 6 - sto_pr_ot_valid_noved
                       Procedimiento de Validacion de la novedad
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_valid_noved
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : Validacion 7 - sto_pr_ot_neces_calif
                       Procedimiento de Validacion de Necesidad de calificacion
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_neces_calif
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /**************************************************************************
  Descripcion       : STO_PR_OT_ENVIO_SICC
                    Envio de ced a SICC
  Fecha Creacion    : 05/02/2013
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ot_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );
END sto_pkg_proce_valid_ot;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_ot AS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /**************************************************************************
   Descripcion       : Validacion 1 - sto_pr_ot_tipo_orden
                       Procedimiento de Validacion del tipo de orden
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_tipo_orden
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ordentransporte IS
      SELECT cons.num_docu, -- posicion 4
             cons.num_lote,
             cons.tip_orde,
             cons.sec_nume_docu
        FROM int_solic_conso_orden_trans cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_num_docu IS TABLE OF int_solic_conso_orden_trans.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_orden_trans.num_lote%TYPE;
    TYPE t_tiporde IS TABLE OF int_solic_conso_orden_trans.tip_orde%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_orden_trans.sec_nume_docu%TYPE;
  
    v_num_docu   t_num_docu;
    v_numlote    t_numlote;
    v_tiporde    t_tiporde;
    v_secnumdocu t_secnumdocu;
  
    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;
  
    lsindicador NUMBER;
    lsvalor     int_solic_conso_orden_trans.cod_zona%TYPE;
  
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_ordentransporte;
    LOOP
      FETCH c_ordentransporte BULK COLLECT
        INTO v_num_docu,
             v_numlote,
             v_tiporde,
             v_secnumdocu LIMIT rows;
      IF v_numlote.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP
          lsindicador := 0;
          /*IF substr(v_num_docu(j),
          1,
          1) = 'E' THEN */
          IF v_tiporde(j) = 'BR' THEN
            lsvalor := 'BR';
          ELSE
            ---- Se cambia para leer todas las solicitudes que estan dentro del consolidado
            SELECT SUM(psc1.ind_oc)
              INTO lsindicador
              FROM ped_solic_cabec psc,
                   ped_solic_cabec psc1
             WHERE psc.oid_soli_cabe = psc1.soca_oid_soli_cabe
               AND psc.val_nume_soli = v_num_docu(j);
          
            IF lsindicador > 0 THEN
              lsvalor := 'BEP';
            ELSE
              lsvalor := 'BER';
            END IF;
          END IF;
        
          UPDATE int_solic_conso_orden_trans
             SET tip_orde = lsvalor -- posicion 24
           WHERE cod_pais = pscodigopais
             AND sec_nume_docu = v_secnumdocu(j);
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_ordentransporte%NOTFOUND;
    END LOOP;
    CLOSE c_ordentransporte;
  
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
                              'ERROR sto_pr_ot_tipo_orden: ' || ls_sqlerrm);
    
  END sto_pr_ot_tipo_orden;

  /**************************************************************************
   Descripcion       : Validacion 2 - sto_pr_ot_codig_noved
                       Procedimiento de Validacion del codigo de novedad
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_codig_noved
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ordentransporte IS
      SELECT cons.cod_nove, -- posicion 9
             cons.tip_orde, -- posicion 24
             cons.num_lote,
             nov.cod_nove,
             cons.sec_nume_docu
        FROM int_solic_conso_orden_trans cons,
             sto_tmp_docum_digit         occ,
             sto_noved_orden_trans       nov
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         and cons.cod_nove = nov.cod_nove(+)
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_cod_nove IS TABLE OF int_solic_conso_orden_trans.cod_nove%TYPE;
    TYPE t_cod_nove_nov IS TABLE OF int_solic_conso_orden_trans.cod_nove%TYPE;
    TYPE t_tip_orde IS TABLE OF int_solic_conso_orden_trans.tip_orde%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_orden_trans.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_orden_trans.sec_nume_docu%TYPE;
  
    v_cod_nove   t_cod_nove;
    v_cod_nove_nov   t_cod_nove_nov;
    v_tip_orde   t_tip_orde;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
  
    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;
  
    is_valid BOOLEAN := TRUE;
  
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_ordentransporte;
    LOOP
      FETCH c_ordentransporte BULK COLLECT
        INTO v_cod_nove,
             v_tip_orde,
             v_numlote,
             v_cod_nove_nov,
             v_secnumdocu LIMIT rows;
      IF v_numlote.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP
          is_valid := TRUE;
        
            IF v_cod_nove_nov(j) is null and v_cod_nove(j) is not null THEN
              is_valid := FALSE;
            END IF;
        
          IF (is_valid) THEN
          
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
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_ordentransporte%NOTFOUND;
    END LOOP;
    CLOSE c_ordentransporte;
  
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
                              'ERROR sto_pr_ot_codig_noved: ' || ls_sqlerrm);
    
  END sto_pr_ot_codig_noved;

  /**************************************************************************
   Descripcion       : Validacion 3 - sto_pr_ot_numer_orden
                       Procedimiento de Validacion del numero de orden
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_numer_orden
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ordentransporte IS
      SELECT cons.num_docu, -- posicion 4
             cons.tip_orde, -- posicion 24
             cons.cod_clie, -- posicion 27
             cons.fec_fact, -- posicion 28
             cons.fec_proc, -- posicion 28
             cons.nom_clie, -- posicion 29
             cons.cod_peri, -- posicion 30
             cons.cod_regi, -- posicion 31
             cons.cod_secc, -- posicion 32
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_orden_trans cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_num_docu IS TABLE OF int_solic_conso_orden_trans.num_docu%TYPE;
    TYPE t_tip_orde IS TABLE OF int_solic_conso_orden_trans.tip_orde%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_orden_trans.cod_clie%TYPE;
    TYPE t_fec_fact IS TABLE OF int_solic_conso_orden_trans.fec_fact%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_orden_trans.fec_fact%TYPE;
    TYPE t_nom_clie IS TABLE OF int_solic_conso_orden_trans.nom_clie%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_orden_trans.cod_peri%TYPE;
    TYPE t_cod_regi IS TABLE OF int_solic_conso_orden_trans.cod_regi%TYPE;
    TYPE t_cod_secc IS TABLE OF int_solic_conso_orden_trans.cod_secc%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_orden_trans.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_orden_trans.sec_nume_docu%TYPE;
  
    v_num_docu   t_num_docu;
    v_tip_orde   t_tip_orde;
    v_cod_clie   t_cod_clie;
    v_fec_fact   t_fec_fact;
    v_fec_proc   t_fec_proc;
    v_nom_clie   t_nom_clie;
    v_cod_peri   t_cod_peri;
    v_cod_regi   t_cod_regi;
    v_cod_secc   t_cod_secc;
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
  
    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;
  
    lv_fecfact DATE;

    is_valid BOOLEAN := TRUE;
  
    vnumsoli ped_solic_cabec.val_nume_soli%TYPE;
    vcodzona zon_zona.cod_zona%TYPE;
    vcodclie mae_clien.cod_clie%TYPE;
    vnombre  VARCHAR2(100);
    vfecfact ped_solic_cabec.fec_fact%TYPE;
    vfecproc ped_solic_cabec.fec_fact%TYPE;
    vcodsecc zon_secci.cod_secc%TYPE;
    vcodperi seg_perio_corpo.cod_peri%TYPE;
  
  BEGIN

    SELECT z.fec_proc INTO lv_fecfact
      FROM bas_ctrl_fact z
     WHERE z.ind_camp_act = 1 AND z.sta_camp = 0;    
  
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_ordentransporte;
    LOOP
      FETCH c_ordentransporte BULK COLLECT
        INTO v_num_docu, -- posicion 4
             v_tip_orde, -- posicion 24
             v_cod_clie, -- posicion 27
             v_fec_fact, -- posicion 28
             v_fec_proc, -- posicion 28
             v_nom_clie, -- posicion 29
             v_cod_peri, -- posicion 30
             v_cod_regi, -- posicion 31
             v_cod_secc, -- posicion 32
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_numlote.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP
          is_valid := TRUE;
        
          IF substr(v_tip_orde(j), 1, 2) = 'BE' THEN
            BEGIN
              SELECT a.val_nume_soli,
                     b.cod_zona,
                     c.cod_clie,
                     c.val_nom1 || ' ' || c.val_nom2 || ' ' || c.val_ape1 || ' ' ||
                     c.val_ape2 nombre,
                     a.fec_fact,
                     e.cod_secc,
                     g.cod_peri
                INTO vnumsoli,
                     vcodzona,
                     vcodclie,
                     vnombre,
                     vfecfact,
                     vcodsecc,
                     vcodperi
                FROM ped_solic_cabec a,
                     zon_zona        b,
                     mae_clien       c,
                     zon_terri_admin d,
                     zon_secci       e,
                     cra_perio       f,
                     seg_perio_corpo g
               WHERE val_nume_soli = v_num_docu(j)
                 AND a.zzon_oid_zona = b.oid_zona
                 AND a.sbac_oid_sbac = 888
                 AND a.clie_oid_clie = c.oid_clie
                 AND a.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = e.oid_secc
                 AND a.perd_oid_peri = f.oid_peri
                 AND f.peri_oid_peri = g.oid_peri;
            
              --- Si la fecha es menor al del pedido se coloca la del pedido
              if v_fec_proc(j) < vfecfact then v_fec_proc(j) := vfecfact; end if;

              --- Si la fecha es mayor a la fecha de hoy se coloca la fecha de hoy
              if v_fec_proc(j) > lv_fecfact then v_fec_proc(j) := lv_fecfact; end if;

              UPDATE int_solic_conso_orden_trans a
                 SET a.cod_zona = vcodzona,
                     a.cod_clie = vcodclie,
                     a.fec_fact = vfecfact,
                     a.fec_proc = v_fec_proc(j),
                     a.nom_clie = vnombre,
                     a.cod_regi = substr(vcodzona, 1, 2),
                     a.cod_secc = vcodsecc,
                     a.cod_peri = vcodperi
               WHERE cod_pais = pscodigopais
                 AND sec_nume_docu = v_secnumdocu(j);
            
              UPDATE sto_docum_digit occ
                 SET occ.cod_regi = substr(vcodzona, 1, 2),
                     occ.cod_zona = vcodzona,
                     occ.cod_clie = vcodclie
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_secnumdocu(j);
            
            EXCEPTION
              WHEN no_data_found THEN
                UPDATE int_solic_conso_orden_trans
                   SET cod_zona = nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'STO_PARA_ZONA_COMO'),
                                      '0') --'00'   ----------------------------- FALTA DEFINIR PARAMETRO
                 WHERE cod_pais = pscodigopais
                   AND sec_nume_docu = v_secnumdocu(j);
                is_valid := FALSE;
            END;
          
          ELSE
          
            BEGIN
            
              SELECT a.cod_cabe_bore i,
                     a.cod_zona,
                     a.cod_clie,
                     c.val_nom1 || ' ' || c.val_nom2 || ' ' || c.val_ape1 || ' ' ||
                     c.val_ape2 nombre,
                     a.fec_ingr,
                     a.cod_secc,
                     a.cod_peri_proc
                INTO vnumsoli,
                     vcodzona,
                     vcodclie,
                     vnombre,
                     vfecfact,
                     vcodsecc,
                     vcodperi
                FROM int_rec_cabec_borec a,
                     mae_clien           c
               WHERE a.cod_cabe_bore = v_num_docu(j)
                 AND a.cod_clie = c.cod_clie;
            
              --- Si la fecha es menor al del pedido se coloca la del pedido
              if v_fec_proc(j) < vfecfact then v_fec_proc(j) := vfecfact; end if;

              --- Si la fecha es mayor a la fecha de hoy se coloca la fecha de hoy
              if v_fec_proc(j) > lv_fecfact then v_fec_proc(j) := lv_fecfact; end if;


              UPDATE int_solic_conso_orden_trans a
                 SET a.cod_zona = vcodzona,
                     a.cod_clie = vcodclie,
                     a.fec_fact = vfecfact,
                     a.fec_proc = v_fec_proc(j),
                     a.nom_clie = vnombre,
                     a.cod_regi = substr(vcodzona, 1, 2),
                     a.cod_secc = vcodsecc,
                     a.cod_peri = vcodperi
               WHERE cod_pais = pscodigopais
                 AND sec_nume_docu = v_secnumdocu(j);
            
              UPDATE sto_docum_digit occ
                 SET occ.cod_regi = substr(vcodzona, 1, 2),
                     occ.cod_zona = vcodzona,
                     occ.cod_clie = vcodclie
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_secnumdocu(j);
            
            EXCEPTION
              WHEN no_data_found THEN
                UPDATE int_solic_conso_orden_trans
                   SET cod_zona = nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'STO_PARA_ZONA_COMO'),
                                      '0') --'00'   ----------------------------- FALTA DEFINIR PARAMETRO
                 WHERE cod_pais = pscodigopais
                   AND sec_nume_docu = v_secnumdocu(j);
                is_valid := FALSE;
            END;
          
          END IF;
        
          IF (is_valid) THEN
          
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
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_ordentransporte%NOTFOUND;
    END LOOP;
    CLOSE c_ordentransporte;
  
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
                              'ERROR sto_pr_ot_numer_orden: ' || ls_sqlerrm);
    
  END sto_pr_ot_numer_orden;

  /**************************************************************************
   Descripcion       : Validacion 4 - sto_pr_ot_centr_acopi
                       Procedimiento de Validacion del codigo de novedad
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_centr_acopi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ordentransporte IS
      SELECT cons.cod_comp_tran, -- posicion 5
             cons.cod_cent_acop, -- posicion 6
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_orden_trans cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_cod_comp_tran IS TABLE OF int_solic_conso_orden_trans.cod_comp_tran%TYPE;
    TYPE t_cod_cent_acop IS TABLE OF int_solic_conso_orden_trans.cod_cent_acop%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_orden_trans.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_orden_trans.sec_nume_docu%TYPE;
  
    v_cod_comp_tran t_cod_comp_tran;
    v_cod_cent_acop t_cod_cent_acop;
    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
  
    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;
  
    is_valid     BOOLEAN := TRUE;
    vcont        NUMBER := 0;
    vcodcomptran sto_centr_acopi.cod_comp_tran%TYPE;
    vcodcentacop sto_centr_acopi.cod_cent_acop%TYPE;
  
    lsparametroMail   sto_param_gener_occrr.val_param%TYPE;


  BEGIN
    

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
   ---- lsparametroMail := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_PARA_MAIL_COMO');

      BEGIN

        SELECT a.val_param
          INTO lsparametroMail
          FROM sto_param_gener_occrr a
         WHERE a.cod_pais = pscodigopais
           AND a.cod_para = 'STO_PARA_MAIL_COMO';

      EXCEPTION
        WHEN no_data_found THEN
          lsparametroMail := '';
      END;

    OPEN c_ordentransporte;
    LOOP
      FETCH c_ordentransporte BULK COLLECT
        INTO v_cod_comp_tran, -- posicion 5
             v_cod_cent_acop, -- posicion 6
             v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_numlote.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP
          is_valid := TRUE;
        
          SELECT COUNT(1)
            INTO vcont
            FROM sto_centr_acopi ca
           WHERE ca.cod_comp_tran = v_cod_comp_tran(j)
             AND ca.cod_cent_acop = v_cod_cent_acop(j)
             AND ca.ind_como is null;
        
          -- No devuelve registros
          IF vcont = 0 THEN
            is_valid := FALSE;
            BEGIN

                vcodcomptran := v_cod_comp_tran(j) ;
                vcodcentacop := v_cod_cent_acop(j) ;

              if vcodcomptran is null then 
                vcodcomptran := ' ' ;
              end if;  

              if vcodcentacop is null then 
                vcodcentacop := ' ' ;
              end if;  
            
              --- Verifica si ya fue agregado anteriormente
              SELECT ca.cod_comp_tran,
                     ca.cod_cent_acop
                INTO vcodcomptran,
                     vcodcentacop
                FROM sto_centr_acopi ca
                 WHERE ca.cod_comp_tran = vcodcomptran
                   AND ca.cod_cent_acop = vcodcentacop
                   AND ca.ind_como = '9';
            
            EXCEPTION
              WHEN no_data_found THEN
                  --- Agrega el registro que no existe
                  Insert into STO_CENTR_ACOPI
                     (COD_COMP_TRAN, COD_CENT_ACOP, NOM_COMP_TRAN, NOM_CENT_ACOP, VAL_EMAI, IND_COMO)
                   Values
                     (vcodcomptran, vcodcentacop, 'NO EXISTE', 'NO EXISTE', lsparametroMail,'9');

                NULL;
            END;
            
              UPDATE int_solic_conso_orden_trans ot
                 SET ot.cod_comp_tran = vcodcomptran,
                     ot.cod_cent_acop = vcodcentacop,
                     ot.cod_cali = '02',
                     ot.val_mens = 'CIA TRANSPORTE/CENTRO DE ACOPIO NO EXISTE'
               WHERE cod_pais = pscodigopais
                 AND sec_nume_docu = v_secnumdocu(j);
            
          END IF;
        
          IF (is_valid) THEN
          
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
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_ordentransporte%NOTFOUND;
    END LOOP;
    CLOSE c_ordentransporte;
  
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
                              'ERROR sto_pr_ot_centr_acopi: ' || ls_sqlerrm);
    
  END sto_pr_ot_centr_acopi;

  /**************************************************************************
   Descripcion       : Validacion 5 - sto_pr_ot_recib_confo
                       Procedimiento de Validacion de Recibió conforme
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_recib_confo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ordentransporte IS
      SELECT cons.cod_esta_entr, --posicion 7
             cons.cod_nove, --posicion 9
             cons.cod_reci_conf, --posicion 10
             cons.tip_orde, --posicion 24
             cons.num_lote,
             cons.ind_caja_comp,
             cons.ind_fuer_caja,
             cons.sec_nume_docu
        FROM int_solic_conso_orden_trans cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_cod_esta_entr IS TABLE OF int_solic_conso_orden_trans.cod_esta_entr%TYPE;
    TYPE t_cod_nove IS TABLE OF int_solic_conso_orden_trans.cod_nove%TYPE;
    TYPE t_cod_reci_conf IS TABLE OF int_solic_conso_orden_trans.cod_reci_conf%TYPE;
    TYPE t_tip_orde IS TABLE OF int_solic_conso_orden_trans.tip_orde%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_orden_trans.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_orden_trans.sec_nume_docu%TYPE;
    TYPE t_ind_fuer_caja IS TABLE OF int_solic_conso_orden_trans.ind_fuer_caja%TYPE;
    TYPE t_ind_caja_comp IS TABLE OF int_solic_conso_orden_trans.ind_caja_comp%TYPE;
  
    v_ind_fuer_caja t_ind_fuer_caja;
    v_ind_caja_comp t_ind_caja_comp;
    v_cod_esta_entr t_cod_esta_entr;
    v_cod_nove      t_cod_nove;
    v_cod_reci_conf t_cod_reci_conf;
    v_tip_orde      t_tip_orde;
    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
  
    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;
    vtmp sto_noved_accio_orden_trans.ind_reci_conf%TYPE;
  
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_ordentransporte;
    LOOP
      FETCH c_ordentransporte BULK COLLECT
        INTO v_cod_esta_entr, --posicion 7
             v_cod_nove, --posicion 9
             v_cod_reci_conf, --posicion 10
             v_tip_orde, --posicion 24
             v_numlote,
             v_ind_caja_comp,
             v_ind_fuer_caja,
             v_secnumdocu LIMIT rows;
      IF v_numlote.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP
          IF substr(v_tip_orde(j), 1, 2) = 'BE' THEN
          
            BEGIN
              SELECT DISTINCT ind_reci_conf
                INTO vtmp
                FROM sto_noved_accio_orden_trans
               WHERE nvl(cod_esta_entr, 0) = nvl(v_cod_esta_entr(j), 0)
                 AND nvl(cod_nove, 0) = nvl(v_cod_nove(j), 0)
                 AND ind_reci_conf = 'S'
                 AND rownum = 1;
            
              IF vtmp = 'S' AND v_cod_reci_conf(j) = 'S' and
                v_ind_caja_comp(j) = 'S' AND v_ind_fuer_caja(j) = 'S' THEN
              
                UPDATE int_solic_conso_orden_trans ot
                   SET ot.ind_nove = '1',
                       ot.cod_esta_ent2 =
                         (SELECT DISTINCT cod_esta_ent2
                            FROM sto_noved_accio_orden_trans
                           WHERE nvl(cod_esta_entr, 0) =
                                 nvl(v_cod_esta_entr(j), 0)
                             AND nvl(cod_nove, 0) = nvl(v_cod_nove(j), 0))                   
                 WHERE cod_pais = pscodigopais
                   AND sec_nume_docu = v_secnumdocu(j);
              
              END IF;
            EXCEPTION
              WHEN no_data_found THEN
                NULL;
            END;
          
          END IF;
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_ordentransporte%NOTFOUND;
    END LOOP;
    CLOSE c_ordentransporte;
  
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
                              'ERROR sto_pr_ot_recib_confo: ' || ls_sqlerrm);
    
  END sto_pr_ot_recib_confo;

  /**************************************************************************
   Descripcion       : Validacion 6 - sto_pr_ot_valid_noved
                       Procedimiento de Validacion de la novedad
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_valid_noved
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ordentransporte IS
      SELECT cons.cod_esta_entr, --posicion 7
             cons.cod_esta_reco, --posicion 8
             cons.cod_nove, --posicion 9
             cons.cod_esta_ent2, --posicion 18
             cons.val_mens, --posicion 19
             cons.ind_nove, --posicion 22
             cons.tip_orde, --posicion 25
             cons.num_lote,
             cons.ind_caja_comp,
             cons.ind_fuer_caja,
             cons.cod_cali,
             (select count(*) 
              from ped_solic_Cabec a, ped_solic_Cabec b, ped_solic_posic c
              where A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
              and B.OID_SOLI_CABE = C.SOCA_OID_SOLI_CABE
              and A.VAL_NUME_SOLI = cons.NUM_DOCU
              and C.IND_DENT_FUER_CAJA_BOLS = 'F'
              and C.NUM_UNID_ATEN > 0 ) nroAFP,
             cons.sec_nume_docu
        FROM int_solic_conso_orden_trans cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
  
    TYPE t_cod_cali IS TABLE OF int_solic_conso_orden_trans.cod_cali%TYPE;
    TYPE t_ind_fuer_caja IS TABLE OF int_solic_conso_orden_trans.ind_fuer_caja%TYPE;
    TYPE t_ind_caja_comp IS TABLE OF int_solic_conso_orden_trans.ind_caja_comp%TYPE;
    TYPE t_cod_esta_entr IS TABLE OF int_solic_conso_orden_trans.cod_esta_entr%TYPE;
    TYPE t_cod_esta_reco IS TABLE OF int_solic_conso_orden_trans.cod_esta_reco%TYPE;
    TYPE t_cod_nove IS TABLE OF int_solic_conso_orden_trans.cod_nove%TYPE;
    TYPE t_cod_esta_ent2 IS TABLE OF int_solic_conso_orden_trans.cod_esta_ent2%TYPE;
    TYPE t_val_mens IS TABLE OF int_solic_conso_orden_trans.val_mens%TYPE;
    TYPE t_ind_nove IS TABLE OF int_solic_conso_orden_trans.ind_nove%TYPE;
    TYPE t_tip_orde IS TABLE OF int_solic_conso_orden_trans.tip_orde%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_orden_trans.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_orden_trans.sec_nume_docu%TYPE;
    TYPE t_nroAFP IS TABLE OF ped_solic_posic.cod_posi%TYPE;
  
    v_cod_cali      t_cod_cali;
    v_ind_fuer_caja t_ind_fuer_caja;
    v_ind_caja_comp t_ind_caja_comp;
    v_cod_esta_entr t_cod_esta_entr;
    v_cod_esta_reco t_cod_esta_reco;
    v_cod_nove      t_cod_nove;
    v_cod_esta_ent2 t_cod_esta_ent2;
    v_val_mens      t_val_mens;
    v_ind_nove      t_ind_nove;
    v_tip_orde      t_tip_orde;
    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
    v_nroAFP        t_nroAFP;
  
    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;
  
    vcodestaentr sto_noved_accio_orden_trans.cod_esta_entr%TYPE;
    vcodnove     sto_noved_accio_orden_trans.cod_nove%TYPE;
    vcodestaent2 sto_noved_accio_orden_trans.cod_esta_ent2%TYPE;
    vindreciconf sto_noved_accio_orden_trans.ind_reci_conf%TYPE;
    vcodcali     sto_noved_accio_orden_trans.cod_cali%TYPE;
    vvalmens     sto_noved_accio_orden_trans.val_mens%TYPE;
    vindenvinove sto_noved_accio_orden_trans.ind_envi_nove%TYPE;
    vindcambdire sto_noved_accio_orden_trans.ind_camb_dire%TYPE;
    vindgenenove sto_noved_accio_orden_trans.ind_gene_nove%TYPE;
  
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_ordentransporte;
    LOOP
    
      FETCH c_ordentransporte BULK COLLECT
        INTO v_cod_esta_entr, -- posicion 7
             v_cod_esta_reco, -- posicion 8
             v_cod_nove, -- posicion 9
             v_cod_esta_ent2, -- posicion 18
             v_val_mens, -- posicion 19
             v_ind_nove, -- posicion 22
             v_tip_orde, -- posicion 24
             v_numlote,
             v_ind_caja_comp,
             v_ind_fuer_caja,
             v_cod_cali,
             v_nroAFP ,
             v_secnumdocu LIMIT rows;
      IF v_numlote.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP
          IF nvl(v_ind_nove(j), 0) != '1' THEN
            ---IF v_ind_nove(j) != '1' THEN
            IF substr(v_tip_orde(j), 1, 2) = 'BE' THEN
              UPDATE int_solic_conso_orden_trans ot
                 SET ot.cod_esta_ent2 =
                     (SELECT DISTINCT cod_esta_ent2
                        FROM sto_noved_accio_orden_trans
                       WHERE nvl(cod_esta_entr, 0) =
                             nvl(v_cod_esta_entr(j), 0)
                         AND nvl(cod_nove, 0) = nvl(v_cod_nove(j), 0))
               WHERE cod_pais = pscodigopais
                 AND sec_nume_docu = v_secnumdocu(j);
            END IF;
          
            BEGIN
              SELECT cod_esta_entr,
                     cod_nove,
                     cod_esta_ent2,
                     ind_reci_conf,
                     cod_cali,
                     val_mens,
                     ind_envi_nove,
                     ind_camb_dire,
                     ind_gene_nove
                INTO vcodestaentr,
                     vcodnove,
                     vcodestaent2,
                     vindreciconf,
                     vcodcali,
                     vvalmens,
                     vindenvinove,
                     vindcambdire,
                     vindgenenove
                FROM sto_noved_accio_orden_trans
               WHERE nvl(cod_esta_entr, 0) = nvl(v_cod_esta_entr(j), 0)
                 AND nvl(cod_nove, 0) = nvl(v_cod_nove(j), 0)
                 and ROWNUM = 1;
            
                ----- Si es ENTREGADO y no recibio caja o afp  lo deja a gestion 
                ---if v_cod_esta_entr(j) = '01' and 
                if vcodestaent2 = '01' and 
                   ( v_ind_caja_comp(j) = 'N' or 
                     (v_ind_fuer_caja(j) = 'N' and v_nroAFP(j) > 0 )) then
                    
                ----- Si no han calificado la gestion
                    if nvl(v_cod_cali(j),0) = 0 then
                       UPDATE int_solic_conso_orden_trans ot
                          SET ot.ind_nove = null,
                              ot.val_mens = vvalmens
                        WHERE cod_pais = pscodigopais
                          AND sec_nume_docu = v_secnumdocu(j);   
                    END IF;
                           
                else               

              IF nvl(vindcambdire, 0) != '1' AND vindenvinove = '1' THEN
                UPDATE int_solic_conso_orden_trans ot
                   SET ot.ind_nove = '1',
                       ot.val_mens = vvalmens,
                       ot.cod_cali = vcodcali
                 WHERE cod_pais = pscodigopais
                   AND sec_nume_docu = v_secnumdocu(j);
              END IF;

                END IF;

              /* ---- Si no tiene AFP atendidos y marco como no AFP no va a gestion
               if (v_nroAFP(j) > 0 and v_ind_fuer_caja(j) = 'N' ) then
                  UPDATE int_solic_conso_orden_trans ot
                     SET ot.ind_nove = '1',
                         ot.cod_cali = '06',
                         ot.val_mens = 'CERRADA NO TIENE AFP'
                   WHERE cod_pais = pscodigopais
                     AND sec_nume_docu = v_secnumdocu(j);                  
               end if;*/

            EXCEPTION
              WHEN no_data_found THEN
                NULL;
              WHEN too_many_rows THEN
                NULL;
            END;
          
            IF v_tip_orde(j) = 'BR' THEN
              BEGIN
                SELECT cod_esta_entr,
                       cod_nove,
                       cod_esta_ent2,
                       ind_reci_conf,
                       cod_cali,
                       val_mens,
                       ind_envi_nove,
                       ind_camb_dire,
                       ind_gene_nove
                  INTO vcodestaentr,
                       vcodnove,
                       vcodestaent2,
                       vindreciconf,
                       vcodcali,
                       vvalmens,
                       vindenvinove,
                       vindcambdire,
                       vindgenenove
                  FROM sto_noved_accio_orden_trans
                 WHERE nvl(cod_esta_entr, 0) = nvl(v_cod_esta_reco(j), 0)
                   AND nvl(cod_nove, 0) = nvl(v_cod_nove(j), 0)
                   and rownum = 1;
              
                IF nvl(vindcambdire, 0) != '1' AND vindenvinove = '1' THEN
                  UPDATE int_solic_conso_orden_trans ot
                     SET ot.ind_nove = '1',
                         ot.val_mens = vvalmens,
                         ot.cod_cali = vcodcali
                   WHERE cod_pais = pscodigopais
                     AND sec_nume_docu = v_secnumdocu(j);
                END IF;
              EXCEPTION
                WHEN no_data_found THEN
                  NULL;
                WHEN too_many_rows THEN
                  NULL;
              END;
            END IF;
          
          END IF;
        
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_ordentransporte%NOTFOUND;
    END LOOP;
    CLOSE c_ordentransporte;
  
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
                              'ERROR sto_pr_ot_valid_noved: ' || ls_sqlerrm);
    
  END sto_pr_ot_valid_noved;

  /**************************************************************************
   Descripcion       : Validacion 7 - sto_pr_ot_neces_calif
                       Procedimiento de Validacion de Necesidad de calificacion
                       segun secuencia de ejecucion
   Fecha Creacion    : 07/09/2010
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_ot_neces_calif
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ordentransporte IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_orden_trans cons,
             sto_tmp_docum_digit         occ,
             sto_calif_orden_trans       cal
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND cons.cod_cali = cal.cod_cali(+)
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (nvl(cons.ind_nove, 0) = 1 OR cal.cod_cali IS NOT NULL);
  
    TYPE t_numlote IS TABLE OF int_solic_conso_orden_trans.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_orden_trans.sec_nume_docu%TYPE;
  
    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
  
    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;
  
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  
    OPEN c_ordentransporte;
    LOOP
      FETCH c_ordentransporte BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_numlote.count > 0 THEN
      
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_numlote.count
        
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
      EXIT WHEN c_ordentransporte%NOTFOUND;
    END LOOP;
    CLOSE c_ordentransporte;
  
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
                              'ERROR sto_pr_ot_neces_calif: ' || ls_sqlerrm);
    
  END sto_pr_ot_neces_calif;
  /**************************************************************************
  Descripcion       : STO_PR_OT_ENVIO_SICC
                    Envio de ced a SICC
  Fecha Creacion    : 05/02/2013
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoTipoDoc : Codigo de tipo doc
      psCodigoUltimaValid : Codigo de Ultima Validacion
      psUsuario : Codigo de Usuario
  
  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_ot_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_envioot IS
      SELECT occ.sec_nume_docu,
             occ.num_lote
        FROM sto_tmp_docum_digit occ;
  
    TYPE t_secnumdocu IS TABLE OF sto_tmp_docum_digit.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF sto_tmp_docum_digit.num_lote%TYPE;
  
    v_secnumdocu t_secnumdocu;
    v_numlote    t_numlote;
    
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
                                                 pscodigotipodoccabecera,
                                                 psnumeroproceso);
  
    OPEN c_envioot;
    LOOP
      FETCH c_envioot BULK COLLECT
        INTO v_secnumdocu,
             v_numlote LIMIT rows;
    
      IF v_secnumdocu.count > 0 THEN
        -- Actualizamos Documentos Enviados
        FORALL i IN 1 .. v_secnumdocu.count
          UPDATE sto_docum_digit occ
             SET occ.ind_envi = '1',
                 occ.usu_modi = psusuario,
                 occ.fec_modi = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoccabecera
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_secnumdocu(i);
      
      END IF;
    
      EXIT WHEN c_envioot %NOTFOUND;
    END LOOP;
  
    CLOSE c_envioot;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_OT_ENVIO_SICC: ' || ls_sqlerrm);
  END sto_pr_ot_envio_sicc;
END sto_pkg_proce_valid_ot;
/
