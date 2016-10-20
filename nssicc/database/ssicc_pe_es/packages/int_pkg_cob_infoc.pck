CREATE OR REPLACE PACKAGE "INT_PKG_COB_INFOC" AS

  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 50000;
  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Archivos
                      Esika para el envio a infocorp
  Fecha Creacion    : 01/09/2015
  Autor             : Richard Argomedo
  
  ***************************************************************************/
  PROCEDURE int_cob_inici_esika(v_cod_pais VARCHAR2);

  /***************************************************************************
   Descripcion      : Procedimiento morosas Esika para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
   Parametros       :
       v_cod_pais       Codigo de Pais
       v_cod_peri_fin   Codigo de Periodo
   *****
       psNombreArchivoRetorno Nombre del Archivo TP (Variable de salida)
       pnHandle           Puntero al archivo creado por utl_file
   ****
  ***************************************************************************/
  PROCEDURE int_cob_moros_esika
  (
    v_cod_pais     VARCHAR2,
    v_cod_peri_fin VARCHAR2,
    v_cod_interfaz VARCHAR2,
    v_cod_sistema  VARCHAR2
    
  );

  /***************************************************************************
   Descripcion      : Procedimiento castigadas Esika
                      para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
  ***************************************************************************/
  PROCEDURE int_cob_casti_esika
  (
    v_cod_pais     VARCHAR2,
    v_cod_sistema  VARCHAR2,
    v_cod_interfaz VARCHAR2
  );

  /***************************************************************************
   Descripcion       : Procedimiento inicial para la generación de
                       Archivos LBEL para el envio a infocorp
   Fecha Creacion    : 01/09/2015
   Autor             : Richard Argomedo
  
  ***************************************************************************/
  PROCEDURE int_cob_inici_lbel(v_cod_pais VARCHAR2);

  /***************************************************************************
   Descripcion      : Procedimiento morosas LBEL para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
   Parametros       :
       v_cod_pais       Codigo de Pais
       v_cod_peri_fin   Codigo de Periodo
   *****
       psNombreArchivoRetorno Nombre del Archivo TP (Variable de salida)
       pnHandle           Puntero al archivo creado por utl_file
   ****
  ***************************************************************************/
  PROCEDURE int_cob_moros_lbel
  (
    v_cod_pais     VARCHAR2,
    v_cod_peri_fin VARCHAR2,
    v_cod_sistema  VARCHAR2,
    v_cod_interfaz VARCHAR2
  );

  /***************************************************************************
   Descripcion      : Procedimiento castigadas Lbel
                      para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
  ***************************************************************************/
  PROCEDURE int_cob_casti_lbel
  (
    v_cod_pais     VARCHAR2,
    v_cod_sistema  VARCHAR2,
    v_cod_interfaz VARCHAR2
  );

END int_pkg_cob_infoc;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_COB_INFOC" AS
  /***************************************************************************
   Descripcion      : Procedimiento inicial para la generación de Archivos
                      para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
  ***************************************************************************/
  PROCEDURE int_cob_inici_esika(v_cod_pais VARCHAR2) IS
  BEGIN
    /*  Limpia tablas  auxiliares  */
    DELETE FROM cob_tmp_clien_casti_esika;
    DELETE FROM cob_tmp_mae_clien_bloqu_vigen purge;
  
    /* OJO: prepara  tabla auxiliar para identificar solo los  bloqueos vigentes */
  
    INSERT INTO cob_tmp_mae_clien_bloqu_vigen
      SELECT bl.clie_oid_clie,
             MAX(bl.fec_bloq) fec_bloq_vige
        FROM cob_tmp_mae_clien_bloqu bl
       WHERE bl.tibq_oid_tipo_bloq = 2
       GROUP BY bl.clie_oid_clie;
  
    /* Identifica las castigadas que  han sido desbloqueadas, es decir  ¿reingresadas¿  */
    INSERT INTO cob_tmp_clien_casti_esika
      SELECT *
        FROM ccc_clien_casti cas
       WHERE cas.oid_clie IN
             (SELECT bl.clie_oid_clie
                FROM cob_tmp_mae_clien_bloqu       bl,
                     cob_tmp_mae_clien_bloqu_vigen vig
               WHERE bl.fec_desb IS NOT NULL
                 AND bl.tibq_oid_tipo_bloq = 2
                 AND vig.clie_oid_clie = bl.clie_oid_clie
                 AND vig.fec_bloq_vige = bl.fec_bloq);
  
    /* Registra los  casos identificados  en la table de ¿Reingresadas¿  */
    INSERT INTO ccc_clien_casti_reing
      SELECT cas.cod_pais,
             cas.cod_soci,
             cas.oid_clie,
             cas.cod_clie,
             cas.cod_digi_ctrl,
             cas.val_nom1,
             cas.val_nom2,
             cas.val_ape1,
             cas.val_ape2,
             cas.cod_tipo_docu,
             cas.num_docu_iden,
             cas.val_dept,
             cas.val_prov,
             cas.val_dist,
             cas.val_dire,
             cas.val_refe,
             cas.imp_deud_cast,
             cas.imp_deud_actu,
             cas.fec_deud,
             cas.fec_cast,
             bl.fec_desb,
             bl.val_usua_desb,
             cas.usu_crea,
             cas.fec_crea,
             cas.usu_modi,
             cas.fec_modi
        FROM cob_tmp_clien_casti_esika     cas,
             cob_tmp_mae_clien_bloqu       bl,
             cob_tmp_mae_clien_bloqu_vigen vig
       WHERE cas.oid_clie = bl.clie_oid_clie
         AND bl.fec_desb IS NOT NULL
         AND cas.oid_clie = vig.clie_oid_clie
         AND bl.fec_bloq = vig.fec_bloq_vige
         AND bl.tibq_oid_tipo_bloq = 2;
  
    /*  Elimina los casos identificados de la  tabla de 'Castigadas'  */
    DELETE FROM ccc_clien_casti cas
     WHERE cas.oid_clie IN
           (SELECT bl.clie_oid_clie
              FROM cob_tmp_mae_clien_bloqu       bl,
                   cob_tmp_mae_clien_bloqu_vigen vig
             WHERE bl.fec_desb IS NOT NULL
               AND bl.tibq_oid_tipo_bloq = 2
               AND vig.clie_oid_clie = bl.clie_oid_clie
               AND vig.fec_bloq_vige = bl.fec_bloq);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_cob_inici_esika : ' || ls_sqlerrm);
  END int_cob_inici_esika;

  /***************************************************************************
   Descripcion      : Procedimiento morosas Esika
                      para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
  ***************************************************************************/
  PROCEDURE int_cob_moros_esika
  (
    v_cod_pais     VARCHAR2,
    v_cod_peri_fin VARCHAR2,
    v_cod_interfaz VARCHAR2,
    v_cod_sistema  VARCHAR2
    
  ) IS
    v_fec_proce          VARCHAR2(8) := to_char(SYSDATE, 'YYYYMMDD');
    v_tip_mov            VARCHAR2(1) := '1';
    v_cod_enti           VARCHAR2(6) := '002303';
    v_cod_clie           VARCHAR2(30);
    v_tip_doc_trib       VARCHAR2(1) := '0';
    v_esp                VARCHAR2(8) := '        ';
    v_tipo_doc_iden      VARCHAR2(1);
    v_num_doc_iden       VARCHAR2(12);
    v_tipo_pers          VARCHAR2(1) := '1';
    v_tipo_deud          VARCHAR2(1) := '1';
    v_nomb               VARCHAR2(80);
    v_dire               VARCHAR2(40);
    v_codi_dist          VARCHAR2(6) := '      ';
    v_nomb_dist          VARCHAR2(30);
    v_codi_dept          VARCHAR2(2) := '  ';
    v_nomb_dept          VARCHAR2(30);
    v_fec_venc           VARCHAR2(8);
    v_fec_venc_date      DATE;
    v_fec_quin_ref       DATE;
    v_tip_doc            VARCHAR2(2) := 'OT';
    v_tip_mone           VARCHAR2(2) := '01';
    v_mont_pend          VARCHAR2(12);
    v_cond_deud          VARCHAR2(1) := ' ';
    v_perf_deud          VARCHAR2(24) := '                        ';
    v_tipo_doc_trib_aval VARCHAR2(1) := ' ';
    v_num_doc_trib_aval  VARCHAR2(8) := '        ';
    v_tipo_doc_iden_aval VARCHAR2(1) := ' ';
    v_num_doc_iden_aval  VARCHAR2(12) := '            ';
    v_tipo_pers_aval     VARCHAR2(1) := ' ';
    v_nomb_aval          VARCHAR2(80) := '                                                                                ';
    v_oid_clie           mae_clien.oid_clie%TYPE;
    v_oid_tipo_via       own_comun.seg_tipo_via.oid_tipo_via%TYPE;
    v_cod_unid_geog      mae_clien_direc.cod_unid_geog%TYPE;
    v_cod_tipo_doc_iden  mae_tipo_docum.cod_tipo_docu%TYPE;
    v_camp_ini           cra_perio.oid_peri%TYPE;
    v_camp_fin           cra_perio.oid_peri%TYPE;
    v_bloq               INTEGER;
    v_imp_deud_eval      NUMBER(12, 2);
    v_imp_tamn           NUMBER(12, 4);
    v_imp_igv            NUMBER(12, 2);
    v_monto_deud_total   VARCHAR2(12);
    v_dias_venc          INTEGER;
    i                    BINARY_INTEGER := 0;
    v_dir1               VARCHAR2(80);
    v_dir2               VARCHAR2(80);
    v_dir3               VARCHAR2(80);
    v_dir4               mae_clien_direc.val_obse%TYPE;
    f                    utl_file.file_type;
    f_txt2               utl_file.file_type;
    v_total              VARCHAR2(4000);
    v_oid_regi_ofic      NUMBER(12);
    v_oid_pais           NUMBER(12);
    v_cod_user           VARCHAR2(20);
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo     bas_inter.dir_temp%TYPE;
    lbabrirutlfile BOOLEAN;
  
    CURSOR c_mae IS
      SELECT c.clie_oid_clie,
             TRIM(to_char(MAX(c.fec_docu), 'YYYYMMDD')) AS fec_venc,
             TRIM(REPLACE(to_char(SUM(c.imp_pend), '0000000000D99'), '.')) AS mont_pend,
             MAX(c.fec_docu) AS fec_venc_date
        FROM ccc_movim_cuent_corri c
       WHERE c.perd_oid_peri >= v_camp_ini
         AND c.perd_oid_peri <= v_camp_fin
         AND c.imp_pend > 0
         AND c.fec_venc < SYSDATE
         AND SYSDATE - c.fec_docu > v_dias_venc
         AND c.zorg_oid_regi <> v_oid_regi_ofic
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_datos_adici mcda
               WHERE mcda.clie_oid_clie = c.clie_oid_clie --  Que no sean Consultoras "Constante/Normal"
                 AND mcda.esta_oid_esta_clie = 3)
            
         AND NOT EXISTS -- Que no sean consultoras castigadas
       (SELECT 1
                FROM ccc_clien_casti cc
               WHERE cc.oid_clie = c.clie_oid_clie)
         AND (SELECT COUNT(*)
                FROM ccc_movim_cuent_corri ccc -- Selecciona consultoras  que no hayan pasado pedido
               WHERE ccc.subp_oid_subp_crea = 2001 -- despues de campaña final de evaluacion
                 AND ccc.imp_movi > 0
                 AND ccc.clie_oid_clie = c.clie_oid_clie
                 AND ccc.perd_oid_peri > v_camp_fin) = 0 HAVING
       SUM(c.imp_pend) >= v_imp_deud_eval
       GROUP BY c.clie_oid_clie;
  
    TYPE t_mae IS TABLE OF c_mae%ROWTYPE;
    v_mae t_mae;
  
  BEGIN
    /*
    ******    Ejecutar con usuario  USU_MOD   o  USU_LECT   correspondiente a  la  marca.
    ******    REGISTRAR  EN LAS  VARIABLES v_cod_pais v_cod_peri_fin
    ******    LOS PARAMETROS DE EJECUCION
    */
  
    SELECT pa.oid_pais
      INTO v_oid_pais
      FROM seg_pais pa
     WHERE pa.cod_pais = v_cod_pais;
  
    IF v_cod_pais = 'PE' THEN
      --PERU ESIKA
      SELECT cam.oid_peri
        INTO v_camp_ini
        FROM cra_perio       cam,
             seg_perio_corpo cor
       WHERE cam.peri_oid_peri = cor.oid_peri
         AND cor.cod_peri = '200710';
    ELSE
      --PERU LBEL
      SELECT cam.oid_peri
        INTO v_camp_ini
        FROM cra_perio       cam,
             seg_perio_corpo cor
       WHERE cam.peri_oid_peri = cor.oid_peri
         AND cor.cod_peri = '200804';
    
    END IF;
  
    --Infocorp
    SELECT cam.oid_peri
      INTO v_camp_fin
      FROM cra_perio       cam,
           seg_perio_corpo cor
     WHERE cam.peri_oid_peri = cor.oid_peri
       AND cor.cod_peri = v_cod_peri_fin;
  
    v_imp_deud_eval := 20;
    v_imp_tamn      := 0.237;
    v_imp_igv       := 0.19;
    v_dias_venc     := 63;
    v_fec_quin_ref  := trunc(SYSDATE) - 1825; -- (365 *  5  = 5 años atras)
  
    --  obtine  oid  de region de Oficina
    SELECT reg.oid_regi
      INTO v_oid_regi_ofic
      FROM zon_regio reg
     WHERE reg.cod_regi = '90';
  
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(v_cod_pais,
                                                              v_cod_sistema,
                                                              v_cod_interfaz);
  
    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);
  
    IF v_cod_pais = 'PE' THEN
      -- eliminamos si existen archivos anteriores
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-esm.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-esm-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    IF v_cod_pais = 'PEL' THEN
      -- eliminamos si existen archivos anteriores
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-lbm.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-lbm-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    /* Generando Archivo de Texto*/
    lbabrirutlfile := TRUE;
  
    OPEN c_mae();
    LOOP
      FETCH c_mae BULK COLLECT
        INTO v_mae LIMIT w_filas;
    
      IF lbabrirutlfile AND v_cod_pais = 'PE' THEN
        f              := utl_file.fopen('SICC_DIR',
                                         'infocorp-esm.txt',
                                         'W');
        f_txt2         := utl_file.fopen('SICC_DIR',
                                         'infocorp-esm-masde5.txt',
                                         'W');
        lbabrirutlfile := FALSE;
      END IF;
      IF lbabrirutlfile AND v_cod_pais = 'PEL' THEN
        f      := utl_file.fopen('SICC_DIR', 'infocorp-lbm.txt', 'W');
        f_txt2 := utl_file.fopen('SICC_DIR', 'infocorp-lbm-masde5.txt', 'W');
      END IF;
    
      IF v_mae.count > 0 THEN
        FOR i IN v_mae.first .. v_mae.last
        LOOP
          -- Codigo y Nombre
          SELECT rpad(cod_clie, 30, ' '),
                 rpad(val_ape1 || ' ' || val_ape2 || ', ' || val_nom1 || ' ' ||
                      val_nom2,
                      80,
                      ' ')
            INTO v_cod_clie,
                 v_nomb
            FROM mae_clien m
           WHERE m.oid_clie = v_mae(i).clie_oid_clie;
        
          -- Tipo Documento y Numero Documento Identidad
          BEGIN
            v_tipo_doc_iden     := '0';
            v_num_doc_iden      := '            ';
            v_cod_tipo_doc_iden := '';
          
            SELECT inf.cod_homo_info,
                   rpad(mci.num_docu_iden, 12, ' ') --mtd.cod_tipo_docu,
              INTO v_tipo_doc_iden,
                   v_num_doc_iden
              FROM mae_clien_ident              mci,
                   mae_tipo_docum               mtd,
                   cob_tmp_mae_tipo_docum_infoc inf
             WHERE mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
               AND mci.clie_oid_clie = v_mae(i).clie_oid_clie
               AND mci.tdoc_oid_tipo_docu = inf.oid_tipo_docu
               AND mci.val_iden_docu_prin = 1;
          EXCEPTION
            WHEN no_data_found THEN
              v_tipo_doc_iden := '0';
              v_num_doc_iden  := '            ';
          END;
        
          --  si es tipo documento "Otros", se  envia  como  "Sin Documento"
          IF (v_tipo_doc_iden = '*') THEN
            v_tipo_doc_iden := '0';
            v_num_doc_iden  := '            ';
          END IF;
        
          BEGIN
            SELECT c.cod_unid_geog,
                   c.tivi_oid_tipo_via,
                   c.val_nomb_via,
                   c.num_ppal,
                   c.val_obse
              INTO v_cod_unid_geog,
                   v_oid_tipo_via,
                   v_dir2,
                   v_dir3,
                   v_dir4
              FROM mae_clien_direc c
             WHERE c.clie_oid_clie = v_mae(i).clie_oid_clie
               AND c.ind_dire_ppal = 1
               AND c.ind_elim = 0
               AND rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
              v_cod_unid_geog := ' ';
              v_oid_tipo_via  := '  ';
              v_dir2          := '*error sin direccion';
              v_dir3          := '   ';
              v_dir4          := '   ';
          END;
        
          BEGIN
            SELECT d.des_abrv_tipo_via
              INTO v_dir1
              FROM own_comun.seg_tipo_via d
             WHERE d.oid_tipo_via = v_oid_tipo_via;
          EXCEPTION
            WHEN no_data_found THEN
              v_dir1 := ' ';
          END;
        
          -- Direccion
          v_dire := rpad(substr(TRIM(v_dir1) || ' ' || TRIM(v_dir2) || ' ' ||
                                TRIM(v_dir3) || ' ' || TRIM(v_dir4),
                                1,
                                40),
                         40,
                         ' ');
        
          -- Nombre del Distrito
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dist
              FROM zon_valor_estru_geopo
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 = substr(v_cod_unid_geog, 7, 6)
               AND orde_3 = substr(v_cod_unid_geog, 13, 6)
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dist := '                              ';
          END;
          -- Nombre del Departamento
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dept
              FROM zon_valor_estru_geopo
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 IS NULL
               AND orde_3 IS NULL
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dept := '                              ';
          END;
        
          v_fec_venc      := v_mae(i).fec_venc;
          v_fec_venc_date := v_mae(i).fec_venc_date;
        
          -- Calculo de deuda_total
        
          BEGIN
            v_monto_deud_total := v_mae(i).mont_pend;
          EXCEPTION
            WHEN OTHERS THEN
              dbms_output.put_line('**Error  Monto :' || v_mae(i)
                                   .mont_pend);
              dbms_output.put_line(' cod clie :' || v_cod_clie);
          END;
        
          BEGIN
            v_total := v_fec_proce || v_tip_mov || v_cod_enti || v_cod_clie ||
                       v_tip_doc_trib || v_esp || v_tipo_doc_iden ||
                       v_num_doc_iden || v_tipo_pers || v_tipo_deud ||
                       v_nomb || v_dire || v_codi_dist || v_nomb_dist ||
                       v_codi_dept || v_nomb_dept || v_fec_venc ||
                       v_tip_doc || v_tip_mone || v_monto_deud_total ||
                       v_cond_deud;
          EXCEPTION
            WHEN OTHERS THEN
              dbms_output.put_line(v_mae(i).clie_oid_clie);
          END;
          IF v_fec_quin_ref > v_fec_venc_date THEN
            utl_file.put_line(f_txt2, v_total);
          ELSE
            utl_file.put_line(f, v_total);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_mae%NOTFOUND;
    END LOOP;
    CLOSE c_mae;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(f);
      utl_file.fclose(f_txt2);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_cob_moros_esika : ' || ls_sqlerrm);
  END int_cob_moros_esika;

  /***************************************************************************
   Descripcion      : Procedimiento castigadas Esika
                      para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
  ***************************************************************************/
  PROCEDURE int_cob_casti_esika
  (
    v_cod_pais     VARCHAR2,
    v_cod_sistema  VARCHAR2,
    v_cod_interfaz VARCHAR2
  ) IS
    v_fec_proce          VARCHAR2(8) := to_char(SYSDATE, 'YYYYMMDD');
    v_tip_mov            VARCHAR2(1) := '1';
    v_cod_enti           VARCHAR2(6) := '002303';
    v_cod_clie           VARCHAR2(30);
    v_tip_doc_trib       VARCHAR2(1) := '0';
    v_esp                VARCHAR2(8) := '        ';
    v_tipo_doc_iden      VARCHAR2(1);
    v_num_doc_iden       VARCHAR2(12);
    v_tipo_pers          VARCHAR2(1) := '1';
    v_tipo_deud          VARCHAR2(1) := '1';
    v_nomb               VARCHAR2(280);
    v_dire               VARCHAR2(40);
    v_codi_dist          VARCHAR2(6) := '      ';
    v_nomb_dist          VARCHAR2(30);
    v_codi_dept          VARCHAR2(2) := '  ';
    v_nomb_dept          VARCHAR2(30);
    v_fec_venc           VARCHAR2(8);
    v_fec_venc_date      DATE;
    v_fec_quin_ref       DATE;
    v_tip_doc            VARCHAR2(2) := 'OT';
    v_tip_mone           VARCHAR2(2) := '01';
    v_mont_pend          VARCHAR2(12);
    v_cond_deud          VARCHAR2(1) := 'C';
    v_perf_deud          VARCHAR2(24) := '                        ';
    v_tipo_doc_trib_aval VARCHAR2(1) := ' ';
    v_num_doc_trib_aval  VARCHAR2(8) := '        ';
    v_tipo_doc_iden_aval VARCHAR2(1) := ' ';
    v_num_doc_iden_aval  VARCHAR2(12) := '            ';
    v_tipo_pers_aval     VARCHAR2(1) := ' ';
    v_nomb_aval          VARCHAR2(80) := '                                                                                ';
    v_oid_clie           own_pe_es.mae_clien.oid_clie%TYPE;
    v_oid_tipo_via       own_comun.seg_tipo_via.oid_tipo_via%TYPE;
    v_cod_unid_geog      own_pe_es.mae_clien_direc.cod_unid_geog%TYPE;
    v_cod_tipo_doc_iden  own_pe_es.mae_tipo_docum.cod_tipo_docu%TYPE;
    v_camp_ini           own_pe_es.cra_perio.oid_peri%TYPE;
    v_camp_fin           own_pe_es.cra_perio.oid_peri%TYPE;
    v_bloq               INTEGER;
    v_imp_deud_eval      NUMBER(12, 2);
    v_imp_tamn           NUMBER(12, 4);
    v_imp_igv            NUMBER(12, 2);
    v_monto_deud_total   VARCHAR2(12);
    v_dias_venc          INTEGER;
    i                    BINARY_INTEGER := 0;
    v_dir1               VARCHAR2(80);
    v_dir2               VARCHAR2(80);
    v_dir3               VARCHAR2(80);
    v_dir4               own_pe_es.mae_clien_direc.val_obse%TYPE;
    f_txt1               utl_file.file_type;
    f_txt2               utl_file.file_type;
    f_txt3               utl_file.file_type;
    v_total              VARCHAR2(4000);
    v_oid_pais           NUMBER(12);
    v_cod_user           VARCHAR2(20);
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo     bas_inter.dir_temp%TYPE;
    lbabrirutlfile BOOLEAN;
    lnx            NUMBER;
    lny            NUMBER;
  
    CURSOR c_mae IS
      SELECT mcc.clie_oid_clie,
             TRIM(to_char(MAX(mcc.fec_docu), 'YYYYMMDD')) AS fec_venc,
             TRIM(REPLACE(to_char(SUM(mcc.imp_pend), '0000000000D99'), '.')) AS mont_pend,
             SUM(mcc.imp_pend) AS dec_mont_pend,
             MAX(mcc.fec_docu) AS fec_venc_date
        FROM ccc_movim_cuent_corri mcc
       WHERE EXISTS (SELECT 1
                FROM ccc_clien_casti cc
               WHERE cc.oid_clie = mcc.clie_oid_clie)
       GROUP BY mcc.clie_oid_clie;
  
    TYPE t_mae IS TABLE OF c_mae%ROWTYPE;
    v_mae t_mae;
  
  BEGIN
  
    /*
    ******    Ejecutar con usuario  USU_MOD   o  USU_LECT   correspondiente a  la  marca
    ******    REGISTRAR  EN LAS  VARIABLES v_cod_pais
    ******    LOS PARAMETROS DE EJECUCION
    */
  
    SELECT pa.oid_pais
      INTO v_oid_pais
      FROM seg_pais pa
     WHERE pa.cod_pais = v_cod_pais;
  
    --Infocorp
    v_imp_deud_eval := 20;
    v_imp_tamn      := 0.237;
    v_imp_igv       := 0.19;
    v_dias_venc     := 63;
    v_fec_quin_ref  := trunc(SYSDATE) - 1825; -- (365 *  5  = 5 años atras)
  
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(v_cod_pais,
                                                              v_cod_sistema,
                                                              v_cod_interfaz);
  
    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);
  
    IF v_cod_pais = 'PE' THEN
      -- eliminamos si existen archivos anteriores
      BEGIN
        utl_file.fremove('SICC_DIR', 'esika-infocorp-ccd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'esika-infocorp-ccd-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'esika-infocorp-csd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    IF v_cod_pais = 'PEL' THEN
      -- eliminamos si existen archivos anteriores
      BEGIN
        utl_file.fremove('SICC_DIR', 'lbel-infocorp-ccd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'lbel-infocorp-ccd-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'lbel-infocorp-csd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    COMMIT;
    /* Generando Archivo de Texto*/
    lbabrirutlfile := TRUE;
    lnx            := 0;
  
    OPEN c_mae();
    LOOP
      lnx := lnx + 1;
      FETCH c_mae BULK COLLECT
        INTO v_mae LIMIT w_filas;
    
      IF lbabrirutlfile AND v_cod_pais = 'PE' THEN
        f_txt1         := utl_file.fopen('SICC_DIR',
                                         'esika-infocorp-ccd.txt',
                                         'W');
        f_txt2         := utl_file.fopen('SICC_DIR',
                                         'esika-infocorp-ccd-masde5.txt',
                                         'W');
        f_txt3         := utl_file.fopen('SICC_DIR',
                                         'esika-infocorp-csd.txt',
                                         'W');
        lbabrirutlfile := FALSE;
      END IF;
    
      IF lbabrirutlfile AND v_cod_pais = 'PEL' THEN
        f_txt1         := utl_file.fopen('SICC_DIR',
                                         'lbel-infocorp-ccd.txt',
                                         'W');
        f_txt2         := utl_file.fopen('SICC_DIR',
                                         'lbel-infocorp-ccd-masde5.txt',
                                         'W');
        f_txt3         := utl_file.fopen('SICC_DIR',
                                         'lbel-infocorp-csd.txt',
                                         'W');
        lbabrirutlfile := FALSE;
      END IF;
    
      IF v_mae.count > 0 THEN
        FOR i IN v_mae.first .. v_mae.last
        LOOP
        
          lny := i;
          -- Codigo y Nombre
          SELECT rpad(cod_clie, 30, ' '),
                 rpad(val_ape1 || ' ' || val_ape2 || ', ' || val_nom1 || ' ' ||
                      val_nom2,
                      80,
                      ' ')
            INTO v_cod_clie,
                 v_nomb
            FROM mae_clien m
           WHERE m.oid_clie = v_mae(i).clie_oid_clie;
        
          -- Tipo Documento y Numero Documento Identidad
          BEGIN
            v_tipo_doc_iden     := '0';
            v_num_doc_iden      := '            ';
            v_cod_tipo_doc_iden := '';
          
            SELECT inf.cod_homo_info,
                   rpad(mci.num_docu_iden, 12, ' ')
              INTO v_tipo_doc_iden,
                   v_num_doc_iden
              FROM mae_clien_ident              mci,
                   mae_tipo_docum               mtd,
                   cob_tmp_mae_tipo_docum_infoc inf
             WHERE mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
               AND mci.clie_oid_clie = v_mae(i).clie_oid_clie
               AND mci.tdoc_oid_tipo_docu = inf.oid_tipo_docu
               AND mci.val_iden_docu_prin = 1;
          EXCEPTION
            WHEN no_data_found THEN
              v_tipo_doc_iden := '0';
              v_num_doc_iden  := '            ';
          END;
        
          --  si es tipo documento "Otros", se  envia  como  "Sin Documento"
          IF (v_tipo_doc_iden = '*') THEN
            v_tipo_doc_iden := '0';
            v_num_doc_iden  := '            ';
          END IF;
        
          SELECT c.cod_unid_geog,
                 c.tivi_oid_tipo_via,
                 c.val_nomb_via,
                 c.num_ppal,
                 c.val_obse
            INTO v_cod_unid_geog,
                 v_oid_tipo_via,
                 v_dir2,
                 v_dir3,
                 v_dir4
            FROM mae_clien_direc c
           WHERE c.clie_oid_clie = v_mae(i).clie_oid_clie
             AND c.ind_dire_ppal = 1
             AND c.ind_elim = 0
             AND rownum = 1;
        
          SELECT d.des_abrv_tipo_via
            INTO v_dir1
            FROM own_comun.seg_tipo_via d
           WHERE d.oid_tipo_via = v_oid_tipo_via;
        
          -- Direccion
          v_dire := rpad(substr(TRIM(v_dir1) || ' ' || TRIM(v_dir2) || ' ' ||
                                TRIM(v_dir3) || ' ' || TRIM(v_dir4),
                                1,
                                40),
                         40,
                         ' ');
        
          -- Nombre del Distrito
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dist
              FROM zon_valor_estru_geopo
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 = substr(v_cod_unid_geog, 7, 6)
               AND orde_3 = substr(v_cod_unid_geog, 13, 6)
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dist := '                              ';
          END;
        
          -- Nombre del Departamento
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dept
              FROM zon_valor_estru_geopo
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 IS NULL
               AND orde_3 IS NULL
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dept := '                              ';
          END;
        
          v_fec_venc      := v_mae(i).fec_venc;
          v_fec_venc_date := v_mae(i).fec_venc_date;
        
          --  si tiene  deuda  cero  se  fuerza  a  1 sol.
          IF v_mae(i).dec_mont_pend <= 0 THEN
            v_monto_deud_total := '000000000100';
          ELSE
            v_monto_deud_total := v_mae(i).mont_pend;
          END IF;
        
          BEGIN
            v_total := v_fec_proce || v_tip_mov || v_cod_enti || v_cod_clie ||
                       v_tip_doc_trib || v_esp || v_tipo_doc_iden ||
                       v_num_doc_iden || v_tipo_pers || v_tipo_deud ||
                       v_nomb || v_dire || v_codi_dist || v_nomb_dist ||
                       v_codi_dept || v_nomb_dept || v_fec_venc ||
                       v_tip_doc || v_tip_mone || v_monto_deud_total ||
                       v_cond_deud;
          EXCEPTION
            WHEN OTHERS THEN
              dbms_output.put_line(v_mae(i).clie_oid_clie);
          END;
        
          IF v_mae(i).dec_mont_pend > v_imp_deud_eval THEN
            --  castigadas  con deuda
            IF v_fec_quin_ref > v_fec_venc_date THEN
              utl_file.put_line(f_txt2, v_total); -- castigadas con deuda  y  edad de deuda mas de 5 años
            ELSE
              utl_file.put_line(f_txt1, v_total); -- castigadas con deuda y edad de deuda menos de 5 años
            END IF;
          ELSE
            --  castigadas sin deuda
            utl_file.put_line(f_txt3, v_total);
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_mae%NOTFOUND;
    END LOOP;
    CLOSE c_mae;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(f_txt1);
      utl_file.fclose(f_txt2);
      utl_file.fclose(f_txt3);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_cob_casti_esika : ' || ls_sqlerrm ||
                              ' iteracion bulk: ' || lnx ||
                              ' itenacion del for: ' || lny ||
                              ' CODIGO CLIENTE: ' || v_cod_clie);
  END int_cob_casti_esika;

  /***************************************************************************
   Descripcion       : Procedimiento inicial para la generación de
                       Archivos LBEL para el envio a infocorp
   Fecha Creacion    : 01/09/2015
   Autor             : Richard Argomedo
  
  ***************************************************************************/
  PROCEDURE int_cob_inici_lbel(v_cod_pais VARCHAR2) IS
  BEGIN
    DELETE FROM cob_tmp_ccc_clien_casti_lbel;
  
    INSERT INTO cob_tmp_ccc_clien_casti_lbel
      SELECT *
        FROM ccc_clien_casti_lbel cas
       WHERE cas.oid_clie IN
             (SELECT bl.clie_oid_clie
                FROM mae_clien_bloqu_lbel bl
               WHERE bl.fec_desb IS NOT NULL
                 AND bl.tibq_oid_tipo_bloq = 2);
  
    INSERT INTO ccc_clien_casti_reing_lbel
      SELECT cas.cod_pais,
             cas.cod_soci,
             cas.oid_clie,
             cas.cod_clie,
             cas.cod_digi_ctrl,
             cas.val_nom1,
             cas.val_nom2,
             cas.val_ape1,
             cas.val_ape2,
             cas.cod_tipo_docu,
             cas.num_docu_iden,
             cas.val_dept,
             cas.val_prov,
             cas.val_dist,
             cas.val_dire,
             cas.val_refe,
             cas.imp_deud_cast,
             cas.imp_deud_actu,
             cas.fec_deud,
             cas.fec_cast,
             bl.fec_desb,
             bl.val_usua_desb,
             cas.usu_crea,
             cas.fec_crea,
             cas.usu_modi,
             cas.fec_modi
        FROM cob_tmp_ccc_clien_casti_lbel cas,
             mae_clien_bloqu_lbel         bl
       WHERE cas.oid_clie = bl.clie_oid_clie
         AND bl.fec_desb IS NOT NULL
         AND bl.tibq_oid_tipo_bloq = 2;
  
    DELETE FROM ccc_clien_casti_lbel cas
     WHERE cas.oid_clie IN
           (SELECT bl.clie_oid_clie
              FROM mae_clien_bloqu_lbel bl
             WHERE bl.fec_desb IS NOT NULL
               AND bl.tibq_oid_tipo_bloq = 2);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_cob_inici_lbel : ' || ls_sqlerrm);
  END int_cob_inici_lbel;

  /***************************************************************************
   Descripcion      : Procedimiento morosas LBEL para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
   Parametros       :
       v_cod_pais       Codigo de Pais
       v_cod_peri_fin   Codigo de Periodo
   *****
       psNombreArchivoRetorno Nombre del Archivo TP (Variable de salida)
       pnHandle           Puntero al archivo creado por utl_file
   ****
  ***************************************************************************/
  PROCEDURE int_cob_moros_lbel
  (
    v_cod_pais     VARCHAR2,
    v_cod_peri_fin VARCHAR2,
    v_cod_sistema  VARCHAR2,
    v_cod_interfaz VARCHAR2
  ) IS
    v_fec_proce          VARCHAR2(8) := to_char(SYSDATE, 'YYYYMMDD');
    v_tip_mov            VARCHAR2(1) := '1';
    v_cod_enti           VARCHAR2(6) := '002303';
    v_cod_clie           VARCHAR2(30);
    v_tip_doc_trib       VARCHAR2(1) := '0';
    v_esp                VARCHAR2(8) := '        ';
    v_tipo_doc_iden      VARCHAR2(1);
    v_num_doc_iden       VARCHAR2(12);
    v_tipo_pers          VARCHAR2(1) := '1';
    v_tipo_deud          VARCHAR2(1) := '1';
    v_nomb               VARCHAR2(80);
    v_dire               VARCHAR2(40);
    v_codi_dist          VARCHAR2(6) := '      ';
    v_nomb_dist          VARCHAR2(30);
    v_codi_dept          VARCHAR2(2) := '  ';
    v_nomb_dept          VARCHAR2(30);
    v_fec_venc           VARCHAR2(8);
    v_fec_venc_date      DATE;
    v_fec_quin_ref       DATE;
    v_tip_doc            VARCHAR2(2) := 'OT';
    v_tip_mone           VARCHAR2(2) := '01';
    v_mont_pend          VARCHAR2(12);
    v_cond_deud          VARCHAR2(1) := ' ';
    v_perf_deud          VARCHAR2(24) := '                        ';
    v_tipo_doc_trib_aval VARCHAR2(1) := ' ';
    v_num_doc_trib_aval  VARCHAR2(8) := '        ';
    v_tipo_doc_iden_aval VARCHAR2(1) := ' ';
    v_num_doc_iden_aval  VARCHAR2(12) := '            ';
    v_tipo_pers_aval     VARCHAR2(1) := ' ';
    v_nomb_aval          VARCHAR2(80) := '                                                                                ';
    v_oid_clie           mae_clien.oid_clie%TYPE;
    v_oid_tipo_via       own_comun.seg_tipo_via.oid_tipo_via%TYPE;
    v_cod_unid_geog      mae_clien_direc.cod_unid_geog%TYPE;
    v_cod_tipo_doc_iden  mae_tipo_docum.cod_tipo_docu%TYPE;
    v_camp_ini           cra_perio.oid_peri%TYPE;
    v_camp_fin           cra_perio.oid_peri%TYPE;
    v_bloq               INTEGER;
    v_imp_deud_eval      NUMBER(12, 2);
    v_imp_tamn           NUMBER(12, 4);
    v_imp_igv            NUMBER(12, 2);
    v_monto_deud_total   VARCHAR2(14);
    v_dias_venc          INTEGER;
    i                    BINARY_INTEGER := 0;
    v_dir1               VARCHAR2(80);
    v_dir2               VARCHAR2(80);
    v_dir3               VARCHAR2(80);
    v_dir4               mae_clien_direc.val_obse%TYPE;
    f                    utl_file.file_type;
    f_txt2               utl_file.file_type;
    v_total              VARCHAR2(4000);
    v_oid_regi_ofic      NUMBER(12);
    v_oid_pais           NUMBER(12);
    v_cod_user           VARCHAR2(20);
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo     bas_inter.dir_temp%TYPE;
    lbabrirutlfile BOOLEAN;
  
    CURSOR c_mae IS
      SELECT c.clie_oid_clie,
             TRIM(to_char(MAX(c.fec_docu), 'YYYYMMDD')) AS fec_venc,
             TRIM(REPLACE(to_char(SUM(c.imp_pend), '0000000000D99'), ',')) AS mont_pend,
             MAX(c.fec_docu) AS fec_venc_date
        FROM ccc_movim_cuent_corri_lbel c
       WHERE c.perd_oid_peri >= v_camp_ini
         AND c.perd_oid_peri <= v_camp_fin
         AND c.imp_pend > 0
         AND c.fec_venc < SYSDATE
         AND SYSDATE - c.fec_docu > v_dias_venc
         AND c.zorg_oid_regi <> v_oid_regi_ofic
         AND NOT EXISTS (SELECT NULL
                FROM mae_clien_datos_adici_lbel mcda
               WHERE mcda.clie_oid_clie = c.clie_oid_clie --  Que no sean Consultoras "Constante/Normal"
                 AND mcda.esta_oid_esta_clie = 3)
            
         AND NOT EXISTS -- Que no sean consultoras castigadas
       (SELECT 1
                FROM ccc_clien_casti_lbel cc
               WHERE cc.oid_clie = c.clie_oid_clie)
         AND (SELECT COUNT(*)
                FROM ccc_movim_cuent_corri_lbel ccc -- Selecciona consultoras  que no hayan pasado pedido
               WHERE ccc.subp_oid_subp_crea = 2001 -- despues de campaña final de evaluacion
                 AND ccc.imp_movi > 0
                 AND ccc.clie_oid_clie = c.clie_oid_clie
                 AND ccc.perd_oid_peri > v_camp_fin) = 0 HAVING
       SUM(c.imp_pend) >= v_imp_deud_eval
       GROUP BY c.clie_oid_clie;
  
    TYPE t_mae IS TABLE OF c_mae%ROWTYPE;
    v_mae t_mae;
  BEGIN
    /*
    ******    Ejecutar con usuario  USU_MOD   o  USU_LECT   correspondiente a  la  marca.
    ******    REGISTRAR  EN LAS  VARIABLES v_cod_pais v_cod_peri_fin
    ******    LOS PARAMETROS DE EJECUCION
    */
  
    SELECT pa.oid_pais
      INTO v_oid_pais
      FROM seg_pais pa
     WHERE pa.cod_pais = v_cod_pais;
  
    IF v_cod_pais = 'PE' THEN
      --PERU ESIKA
      SELECT cam.oid_peri
        INTO v_camp_ini
        FROM cra_perio       cam,
             seg_perio_corpo cor
       WHERE cam.peri_oid_peri = cor.oid_peri
         AND cor.cod_peri = '200710';
    ELSE
      --PERU LBEL
      SELECT cam.oid_peri
        INTO v_camp_ini
        FROM cra_perio_lbel  cam,
             seg_perio_corpo cor
       WHERE cam.peri_oid_peri = cor.oid_peri
         AND cor.cod_peri = '200804';
    END IF;
  
    --Infocorp
    IF v_cod_pais = 'PE' THEN
      --PERU ESIKA
      SELECT cam.oid_peri
        INTO v_camp_fin
        FROM cra_perio       cam,
             seg_perio_corpo cor
       WHERE cam.peri_oid_peri = cor.oid_peri
         AND cor.cod_peri = v_cod_peri_fin;
    ELSE
      SELECT cam.oid_peri
        INTO v_camp_fin
        FROM cra_perio_lbel  cam,
             seg_perio_corpo cor
       WHERE cam.peri_oid_peri = cor.oid_peri
         AND cor.cod_peri = '201118';
    END IF;
  
    v_imp_deud_eval := 20;
    v_imp_tamn      := 0.237;
    v_imp_igv       := 0.19;
    v_dias_venc     := 63;
    v_fec_quin_ref  := trunc(SYSDATE) - 1825; -- (365 *  5  = 5 años atras)
  
    --  obtine  oid  de region de Oficina
    SELECT reg.oid_regi
      INTO v_oid_regi_ofic
      FROM zon_regio_lbel reg
     WHERE reg.cod_regi = '90';
  
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(v_cod_pais,
                                                              v_cod_sistema,
                                                              v_cod_interfaz);
    IF (lsdirtempo IS NULL) THEN
      lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc('PE',
                                                                v_cod_sistema,
                                                                v_cod_interfaz);
    END IF;
  
    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);
  
    IF v_cod_pais = 'PE' THEN
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-esm.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-esm-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    IF v_cod_pais = 'PEL' THEN
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-lbm.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'infocorp-lbm-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    /* Generando Archivo de Texto*/
    lbabrirutlfile := TRUE;
  
    OPEN c_mae();
    LOOP
      FETCH c_mae BULK COLLECT
        INTO v_mae LIMIT w_filas;
      IF lbabrirutlfile AND v_cod_pais = 'PE' THEN
        f              := utl_file.fopen('SICC_DIR',
                                         'infocorp-esm.txt',
                                         'W');
        f_txt2         := utl_file.fopen('SICC_DIR',
                                         'infocorp-esm-masde5.txt',
                                         'W');
        lbabrirutlfile := FALSE;
      END IF;
    
      IF lbabrirutlfile AND v_cod_pais = 'PEL' THEN
        f              := utl_file.fopen('SICC_DIR',
                                         'infocorp-lbm.txt',
                                         'W');
        f_txt2         := utl_file.fopen('SICC_DIR',
                                         'infocorp-lbm-masde5.txt',
                                         'W');
        lbabrirutlfile := FALSE;
      END IF;
    
      IF v_mae.count > 0 THEN
        FOR i IN v_mae.first .. v_mae.last
        LOOP
          -- Codigo y Nombre
          SELECT rpad(cod_clie, 30, ' '),
                 rpad(val_ape1 || ' ' || val_ape2 || ', ' || val_nom1 || ' ' ||
                      val_nom2,
                      80,
                      ' ')
            INTO v_cod_clie,
                 v_nomb
            FROM mae_clien m
           WHERE m.oid_clie = v_mae(i).clie_oid_clie;
        
          -- Tipo Documento y Numero Documento Identidad
          BEGIN
            v_tipo_doc_iden     := '0';
            v_num_doc_iden      := '            ';
            v_cod_tipo_doc_iden := '';
          
            SELECT inf.cod_homo_info,
                   rpad(mci.num_docu_iden, 12, ' ') --mtd.cod_tipo_docu,
              INTO v_tipo_doc_iden,
                   v_num_doc_iden
              FROM mae_clien_ident_lbel         mci,
                   mae_tipo_docum_lbel          mtd,
                   cob_tmp_mae_tipo_docum_infoc inf
             WHERE mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
               AND mci.clie_oid_clie = v_mae(i).clie_oid_clie
               AND mci.tdoc_oid_tipo_docu = inf.oid_tipo_docu
               AND mci.val_iden_docu_prin = 1;
          EXCEPTION
            WHEN no_data_found THEN
              v_tipo_doc_iden := '0';
              v_num_doc_iden  := '            ';
          END;
        
          --  si es tipo documento "Otros", se  envia  como  "Sin Documento"
          IF (v_tipo_doc_iden = '*') THEN
            v_tipo_doc_iden := '0';
            v_num_doc_iden  := '            ';
          END IF;
        
          BEGIN
            SELECT c.cod_unid_geog,
                   c.tivi_oid_tipo_via,
                   c.val_nomb_via,
                   c.num_ppal,
                   c.val_obse
              INTO v_cod_unid_geog,
                   v_oid_tipo_via,
                   v_dir2,
                   v_dir3,
                   v_dir4
              FROM mae_clien_direc_lbel c
             WHERE c.clie_oid_clie = v_mae(i).clie_oid_clie
               AND c.ind_dire_ppal = 1
               AND c.ind_elim = 0
               AND rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
              v_cod_unid_geog := ' ';
              v_oid_tipo_via  := '  ';
              v_dir2          := '*error sin direccion';
              v_dir3          := '   ';
              v_dir4          := '   ';
          END;
        
          BEGIN
            SELECT d.des_abrv_tipo_via
              INTO v_dir1
              FROM seg_tipo_via d
             WHERE d.oid_tipo_via = v_oid_tipo_via;
          EXCEPTION
            WHEN no_data_found THEN
              v_dir1 := ' ';
            
          END;
        
          -- Direccion
          v_dire := rpad(substr(TRIM(v_dir1) || ' ' || TRIM(v_dir2) || ' ' ||
                                TRIM(v_dir3) || ' ' || TRIM(v_dir4),
                                1,
                                40),
                         40,
                         ' ');
        
          -- Nombre del Distrito
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dist
              FROM zon_valor_estru_geopo_lbel
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 = substr(v_cod_unid_geog, 7, 6)
               AND orde_3 = substr(v_cod_unid_geog, 13, 6)
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dist := '                              ';
          END;
          -- Nombre del Departamento
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dept
              FROM zon_valor_estru_geopo_lbel
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 IS NULL
               AND orde_3 IS NULL
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dept := '                              ';
          END;
        
          v_fec_venc      := v_mae(i).fec_venc;
          v_fec_venc_date := v_mae(i).fec_venc_date;
        
          -- Calculo de deuda_total
        
          BEGIN
            v_monto_deud_total := v_mae(i).mont_pend;
          
          EXCEPTION
            WHEN OTHERS THEN
              dbms_output.put_line('**Error  Monto :' || v_mae(i)
                                   .mont_pend);
              dbms_output.put_line(' cod clie :' || v_cod_clie);
          END;
        
          BEGIN
            v_total := v_fec_proce || v_tip_mov || v_cod_enti || v_cod_clie ||
                       v_tip_doc_trib || v_esp || v_tipo_doc_iden ||
                       v_num_doc_iden || v_tipo_pers || v_tipo_deud ||
                       v_nomb || v_dire || v_codi_dist || v_nomb_dist ||
                       v_codi_dept || v_nomb_dept || v_fec_venc ||
                       v_tip_doc || v_tip_mone || v_monto_deud_total ||
                       v_cond_deud;
          EXCEPTION
            WHEN OTHERS THEN
              dbms_output.put_line(v_mae(i).clie_oid_clie);
          END;
        
          IF v_fec_quin_ref > v_fec_venc_date THEN
            utl_file.put_line(f_txt2, v_total);
          ELSE
            utl_file.put_line(f, v_total);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_mae%NOTFOUND;
    END LOOP;
    CLOSE c_mae;
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(f);
      utl_file.fclose(f_txt2);
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_cob_moros_lbel : ' || ls_sqlerrm);
  END int_cob_moros_lbel;

  /***************************************************************************
   Descripcion      : Procedimiento castigadas Lbel
                      para el envio a infocorp.
   Fecha Creacion   : 01/09/2015
   Autor            : Richard Argomedo
  ***************************************************************************/
  PROCEDURE int_cob_casti_lbel
  (
    v_cod_pais     VARCHAR2,
    v_cod_sistema  VARCHAR2,
    v_cod_interfaz VARCHAR2
  ) IS
  
    v_fec_proce          VARCHAR2(8) := to_char(SYSDATE, 'YYYYMMDD');
    v_tip_mov            VARCHAR2(1) := '1';
    v_cod_enti           VARCHAR2(6) := '002303';
    v_cod_clie           VARCHAR2(30);
    v_tip_doc_trib       VARCHAR2(1) := '0';
    v_esp                VARCHAR2(8) := '        ';
    v_tipo_doc_iden      VARCHAR2(1);
    v_num_doc_iden       VARCHAR2(12);
    v_tipo_pers          VARCHAR2(1) := '1';
    v_tipo_deud          VARCHAR2(1) := '1';
    v_nomb               VARCHAR2(80);
    v_dire               VARCHAR2(40);
    v_codi_dist          VARCHAR2(6) := '      ';
    v_nomb_dist          VARCHAR2(30);
    v_codi_dept          VARCHAR2(2) := '  ';
    v_nomb_dept          VARCHAR2(30);
    v_fec_venc           VARCHAR2(8);
    v_fec_venc_date      DATE;
    v_fec_quin_ref       DATE;
    v_tip_doc            VARCHAR2(2) := 'OT';
    v_tip_mone           VARCHAR2(2) := '01';
    v_mont_pend          VARCHAR2(12);
    v_cond_deud          VARCHAR2(1) := 'C';
    v_perf_deud          VARCHAR2(24) := '                        ';
    v_tipo_doc_trib_aval VARCHAR2(1) := ' ';
    v_num_doc_trib_aval  VARCHAR2(8) := '        ';
    v_tipo_doc_iden_aval VARCHAR2(1) := ' ';
    v_num_doc_iden_aval  VARCHAR2(12) := '            ';
    v_tipo_pers_aval     VARCHAR2(1) := ' ';
    v_nomb_aval          VARCHAR2(80) := '                                                                                ';
    v_oid_clie           own_pe_es.mae_clien.oid_clie%TYPE;
    v_oid_tipo_via       own_comun.seg_tipo_via.oid_tipo_via%TYPE;
    v_cod_unid_geog      own_pe_es.mae_clien_direc.cod_unid_geog%TYPE;
    v_cod_tipo_doc_iden  own_pe_es.mae_tipo_docum.cod_tipo_docu%TYPE;
    -- Parametros
    v_camp_ini         own_pe_es.cra_perio.oid_peri%TYPE;
    v_camp_fin         own_pe_es.cra_perio.oid_peri%TYPE;
    v_bloq             INTEGER;
    v_imp_deud_eval    NUMBER(12, 2);
    v_imp_tamn         NUMBER(12, 4);
    v_imp_igv          NUMBER(12, 2);
    v_monto_deud_total VARCHAR2(14);
    v_dias_venc        INTEGER;
    i                  BINARY_INTEGER := 0;
    v_dir1             VARCHAR2(80);
    v_dir2             VARCHAR2(80);
    v_dir3             VARCHAR2(80);
    v_dir4             own_pe_es.mae_clien_direc.val_obse%TYPE;
    f_txt1             utl_file.file_type;
    f_txt2             utl_file.file_type;
    f_txt3             utl_file.file_type;
    v_total            VARCHAR2(4000);
    v_oid_pais         NUMBER(12);
    v_cod_user         VARCHAR2(20);
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo     bas_inter.dir_temp%TYPE;
    lbabrirutlfile BOOLEAN;
  
    CURSOR c_mae IS
      SELECT mcc.clie_oid_clie,
             TRIM(to_char(MAX(mcc.fec_docu), 'YYYYMMDD')) AS fec_venc,
             TRIM(REPLACE(to_char(SUM(mcc.imp_pend), '0000000000D99'), ',')) AS mont_pend,
             SUM(mcc.imp_pend) AS dec_mont_pend,
             MAX(mcc.fec_docu) AS fec_venc_date
        FROM ccc_movim_cuent_corri_lbel mcc
       WHERE EXISTS (SELECT 1
                FROM ccc_clien_casti_lbel cc
               WHERE cc.oid_clie = mcc.clie_oid_clie)
       GROUP BY mcc.clie_oid_clie;
  
    TYPE t_mae IS TABLE OF c_mae%ROWTYPE;
    v_mae t_mae;
  BEGIN
    /*
    ******    Ejecutar con usuario  USU_MOD   o  USU_LECT   correspondiente a  la  marca
    ******    REGISTRAR  EN LAS  VARIABLES v_cod_pais
    ******    LOS PARAMETROS DE EJECUCION
    */
  
    SELECT pa.oid_pais
      INTO v_oid_pais
      FROM seg_pais pa
     WHERE pa.cod_pais = v_cod_pais;
  
    --Infocorp
    v_imp_deud_eval := 20;
    v_imp_tamn      := 0.237;
    v_imp_igv       := 0.19;
    v_dias_venc     := 63;
    v_fec_quin_ref  := trunc(SYSDATE) - 1825; -- (365 *  5  = 5 años atras)
  
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(v_cod_pais,
                                                              v_cod_sistema,
                                                              v_cod_interfaz);
    IF (lsdirtempo IS NULL) THEN
      lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc('PE',
                                                                v_cod_sistema,
                                                                v_cod_interfaz);
    END IF;
  
    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);
  
    IF v_cod_pais = 'PE' THEN
      BEGIN
        utl_file.fremove('SICC_DIR', 'esika-infocorp-ccd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'esika-infocorp-ccd-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'esika-infocorp-csd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    IF v_cod_pais = 'PEL' THEN
      BEGIN
        utl_file.fremove('SICC_DIR', 'lbel-infocorp-ccd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'lbel-infocorp-ccd-masde5.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    
      BEGIN
        utl_file.fremove('SICC_DIR', 'lbel-infocorp-csd.txt');
      EXCEPTION
        WHEN OTHERS THEN
          dbms_output.put_line('No existe archivo para eliminar');
      END;
    END IF;
  
    /* Generando Archivo de Texto*/
    lbabrirutlfile := TRUE;
  
    OPEN c_mae();
    LOOP
      FETCH c_mae BULK COLLECT
        INTO v_mae LIMIT w_filas;
      IF lbabrirutlfile AND v_cod_pais = 'PE' THEN
        f_txt1         := utl_file.fopen('SICC_DIR',
                                         'esika-infocorp-ccd.txt',
                                         'W');
        f_txt2         := utl_file.fopen('SICC_DIR',
                                         'esika-infocorp-ccd-masde5.txt',
                                         'W');
        f_txt3         := utl_file.fopen('SICC_DIR',
                                         'esika-infocorp-csd.txt',
                                         'W');
        lbabrirutlfile := FALSE;
      END IF;
      IF lbabrirutlfile AND v_cod_pais = 'PEL' THEN
        f_txt1         := utl_file.fopen('SICC_DIR',
                                         'lbel-infocorp-ccd.txt',
                                         'W');
        f_txt2         := utl_file.fopen('SICC_DIR',
                                         'lbel-infocorp-ccd-masde5.txt',
                                         'W');
        f_txt3         := utl_file.fopen('SICC_DIR',
                                         'lbel-infocorp-csd.txt',
                                         'W');
        lbabrirutlfile := FALSE;
      END IF;
    
      IF v_mae.count > 0 THEN
        FOR i IN v_mae.first .. v_mae.last
        LOOP
          -- Codigo y Nombre
          SELECT rpad(cod_clie, 30, ' '),
                 rpad(val_ape1 || ' ' || val_ape2 || ', ' || val_nom1 || ' ' ||
                      val_nom2,
                      80,
                      ' ')
            INTO v_cod_clie,
                 v_nomb
            FROM mae_clien_lbel m
           WHERE m.oid_clie = v_mae(i).clie_oid_clie;
        
          -- Tipo Documento y Numero Documento Identidad
          BEGIN
            v_tipo_doc_iden     := '0';
            v_num_doc_iden      := '            ';
            v_cod_tipo_doc_iden := '';
          
            SELECT inf.cod_homo_info,
                   rpad(mci.num_docu_iden, 12, ' ')
              INTO v_tipo_doc_iden,
                   v_num_doc_iden
              FROM mae_clien_ident_lbel         mci,
                   mae_tipo_docum_lbel          mtd,
                   cob_tmp_mae_tipo_docum_infoc inf
             WHERE mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
               AND mci.clie_oid_clie = v_mae(i).clie_oid_clie
               AND mci.tdoc_oid_tipo_docu = inf.oid_tipo_docu
               AND mci.val_iden_docu_prin = 1;
          EXCEPTION
            WHEN no_data_found THEN
              v_tipo_doc_iden := '0';
              v_num_doc_iden  := '            ';
          END;
        
          --  si es tipo documento "Otros", se  envia  como  "Sin Documento"
          IF (v_tipo_doc_iden = '*') THEN
            v_tipo_doc_iden := '0';
            v_num_doc_iden  := '            ';
          END IF;
        
          SELECT c.cod_unid_geog,
                 c.tivi_oid_tipo_via,
                 c.val_nomb_via,
                 c.num_ppal,
                 c.val_obse
            INTO v_cod_unid_geog,
                 v_oid_tipo_via,
                 v_dir2,
                 v_dir3,
                 v_dir4
            FROM mae_clien_direc_lbel c
           WHERE c.clie_oid_clie = v_mae(i).clie_oid_clie
             AND c.ind_dire_ppal = 1
             AND c.ind_elim = 0
             AND rownum = 1;
        
          SELECT d.des_abrv_tipo_via
            INTO v_dir1
            FROM seg_tipo_via d
           WHERE d.oid_tipo_via = v_oid_tipo_via;
        
          -- Direccion
          v_dire := rpad(substr(TRIM(v_dir1) || ' ' || TRIM(v_dir2) || ' ' ||
                                TRIM(v_dir3) || ' ' || TRIM(v_dir4),
                                1,
                                40),
                         40,
                         ' ');
        
          -- Nombre del Distrito
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dist
              FROM zon_valor_estru_geopo_lbel
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 = substr(v_cod_unid_geog, 7, 6)
               AND orde_3 = substr(v_cod_unid_geog, 13, 6)
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dist := '                              ';
          END;
          -- Nombre del Departamento
          BEGIN
            SELECT rpad(substr(des_geog, 1, 30), 30, ' ')
              INTO v_nomb_dept
              FROM zon_valor_estru_geopo_lbel
             WHERE pais_oid_pais = v_oid_pais
               AND ind_acti = 1
               AND ind_borr = 0
               AND orde_1 = substr(v_cod_unid_geog, 1, 6)
               AND orde_2 IS NULL
               AND orde_3 IS NULL
               AND orde_4 IS NULL;
          EXCEPTION
            WHEN no_data_found THEN
              v_nomb_dept := '                              ';
          END;
        
          v_fec_venc      := v_mae(i).fec_venc;
          v_fec_venc_date := v_mae(i).fec_venc_date;
        
          -- Calculo de deuda_total
          --  si tiene  deuda  cero  se  fuerza  a  1 sol.
          IF v_mae(i).dec_mont_pend <= 0 THEN
            v_monto_deud_total := '000000000100';
          ELSE
            v_monto_deud_total := v_mae(i).mont_pend;
          END IF;
        
          BEGIN
            v_total := v_fec_proce || v_tip_mov || v_cod_enti || v_cod_clie ||
                       v_tip_doc_trib || v_esp || v_tipo_doc_iden ||
                       v_num_doc_iden || v_tipo_pers || v_tipo_deud ||
                       v_nomb || v_dire || v_codi_dist || v_nomb_dist ||
                       v_codi_dept || v_nomb_dept || v_fec_venc ||
                       v_tip_doc || v_tip_mone || v_monto_deud_total ||
                       v_cond_deud;
          EXCEPTION
            WHEN OTHERS THEN
              dbms_output.put_line(v_mae(i).clie_oid_clie);
          END;
          IF v_mae(i).dec_mont_pend > v_imp_deud_eval THEN
            --  castigadas  con deuda
            IF v_fec_quin_ref > v_fec_venc_date THEN
              utl_file.put_line(f_txt2, v_total); -- castigadas con deuda  y  edad de deuda mas de 5 años
            ELSE
              utl_file.put_line(f_txt1, v_total); -- castigadas con deuda y edad de deuda menos de 5 años
            END IF;
          ELSE
            --  castigadas sin deuda
            utl_file.put_line(f_txt3, v_total);
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_mae%NOTFOUND;
    END LOOP;
    CLOSE c_mae;
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(f_txt1);
      utl_file.fclose(f_txt2);
      utl_file.fclose(f_txt3);
    END IF;
    dbms_output.put_line('Fin del proceso:');
    dbms_output.put_line('Ultimo registro:  ' || v_total);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_cob_casti_lbel : ' || ls_sqlerrm);
  END int_cob_casti_lbel;
END;
/
