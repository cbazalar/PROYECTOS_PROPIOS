CREATE OR REPLACE PACKAGE PER_PKG_PROCE IS

  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS      NUMBER:=5000;

/***************************************************************************
    Descripcion       : Elimina la informacion de las tablas de Pagos Bancarios
                                 Masivos
    Fecha Creacion    : 22/05/2009
    Autor             : Jorge Florencio
   ***************************************************************************/
   PROCEDURE PER_PR_ELIMI_PAGOS_BANCA_MASIV;

   /***************************************************************************
    Descripcion       : Elimina la informacion de la tabla de carga de Pagos
                                Bancarios Masivos.
    Fecha Creacion    : 22/05/2009
    Autor             : Jorge Florencio
   ***************************************************************************/
   PROCEDURE PER_PR_VALID_PAGOS_BANCA_MASIV(
      p_cod_error OUT VARCHAR2);

   /***************************************************************************
    Descripcion       : Procesa  la informacion de la tabla de carga de Pagos
                                Bancarios Masivos.
    Fecha Creacion    : 22/05/2009
    Autor             : Jorge Florencio
   ***************************************************************************/
   PROCEDURE PER_PR_PROCE_PAGOS_BANCO_MASIV(
      p_cod_pais                                 IN    seg_pais.cod_pais%TYPE,
      p_cod_soci                                 IN    seg_socie.cod_soci%TYPE,
      p_cod_cban                                IN    ccc_cuent_corri_banca.cod_cc%TYPE,
      p_cod_usua                                 IN   seg_usuar.use_usua%TYPE);

END PER_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY PER_PKG_PROCE IS

   gv_log_cod_modu                                   fin_modul.cod_modu%TYPE;
   gv_log_cod_proc                                    fin_proce_modul.cod_proc%TYPE;

   gv_cod_proc_ejec                                  NUMBER(12);
   gv_log_cod_pais                                     seg_pais.cod_pais%TYPE;
   gv_log_cod_soci                                     seg_socie.cod_soci%TYPE;
   gv_des_log                                             VARCHAR2(2500);
   gv_log_user                                            fin_proce_ejecu.usu_proc%TYPE;

   PROCEDURE PER_PR_ELIMI_PAGOS_BANCA_MASIV
   IS
   BEGIN
      DELETE FROM PER_CARGA_PAGOS_BANCA_MASIV;
      DELETE FROM PER_ERROR_PAGOS_BANCA_MASIV;

   END PER_PR_ELIMI_PAGOS_BANCA_MASIV;

   PROCEDURE PER_PR_VALID_PAGOS_BANCA_MASIV(
      p_cod_error OUT VARCHAR2)
   IS
      CURSOR c_carga_pagos_masiv IS
      SELECT *
      FROM   PER_CARGA_PAGOS_BANCA_MASIV
      ORDER  BY VAL_FILA;

      TYPE t_tab_per_carga_pagos_masiv  IS TABLE OF PER_CARGA_PAGOS_BANCA_MASIV%ROWTYPE;
      lv_tab_per_carga_pagos_masiv           t_tab_per_carga_pagos_masiv;
      lv_reg_per_carga_pagos_masiv           per_carga_pagos_banca_masiv%ROWTYPE;

     lv_imp_pago                 NUMBER;
     lv_fec_pago                 DATE;
     lv_cant_Erro                NUMBER;
     lv_oid_clie                    mae_clien.oid_clie%TYPE;
     lv_ind_cod_clie_banc_fict   ccc_pais_socie_param.ind_cod_clie_banc_fict%TYPE;
     lv_val_cod_clie_banc_fict   ccc_pais_socie_param.val_cod_clie_banc_fict%TYPE;

   BEGIN

        SELECT pa.ind_cod_clie_banc_fict,pa.val_cod_clie_banc_fict
        INTO lv_ind_cod_clie_banc_fict,lv_val_cod_clie_banc_fict
        FROM ccc_pais_socie_param pa;

      OPEN c_carga_pagos_masiv;
      LOOP
         FETCH c_carga_pagos_masiv BULK COLLECT INTO lv_tab_per_carga_pagos_masiv LIMIT W_FILAS;
            IF lv_tab_per_carga_pagos_masiv.COUNT > 0 THEN

               FOR x IN lv_tab_per_carga_pagos_masiv.FIRST .. lv_tab_per_carga_pagos_masiv.LAST LOOP
                  lv_reg_per_carga_pagos_masiv := lv_tab_per_carga_pagos_masiv(x);

                  --01) Validando los datos Obligatorios
                  IF (lv_reg_per_carga_pagos_masiv.Cod_clie IS NULL) THEN
                     INSERT INTO PER_ERROR_PAGOS_BANCA_MASIV(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(lv_reg_per_carga_pagos_masiv.Val_Fila, '01', 'Dato obligatorio en nulo - Codigo Consultora');
                  END IF;

                  IF (lv_reg_per_carga_pagos_masiv.fec_pago IS NULL) THEN
                     INSERT INTO PER_ERROR_PAGOS_BANCA_MASIV(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(lv_reg_per_carga_pagos_masiv.Val_Fila, '01', 'Dato obligatorio en nulo - Fecha Pago');
                  END IF;

                  IF (lv_reg_per_carga_pagos_masiv.imp_pago IS NULL) THEN
                     INSERT INTO PER_ERROR_PAGOS_BANCA_MASIV(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(lv_reg_per_carga_pagos_masiv.Val_Fila, '01', 'Dato obligatorio en nulo - Importe Pago');
                  END IF;

                  --02) El codigo consultora no existe
                  BEGIN

                     SELECT mc.oid_clie
                     INTO lv_oid_clie
                     FROM mae_clien mc
                     WHERE mc.cod_clie=lv_reg_per_carga_pagos_masiv.cod_clie;

                  EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                           INSERT INTO PER_ERROR_PAGOS_BANCA_MASIV(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(lv_reg_per_carga_pagos_masiv.val_fila, '02', 'Código de Consultora No Existe');
                  END;

                  --03) El Importe de Pago es Incorrecto
                  BEGIN
                     IF (lv_reg_per_carga_pagos_masiv.imp_pago IS NOT NULL) THEN
                        lv_imp_pago := TO_NUMBER(lv_reg_per_carga_pagos_masiv.imp_pago,'9999999999.99');
                     END IF;
                  EXCEPTION
                     WHEN VALUE_ERROR THEN
                        INSERT INTO PER_ERROR_PAGOS_BANCA_MASIV(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(lv_reg_per_carga_pagos_masiv.Val_Fila, '03', 'Dato no es numérico - Importe Movimiento');
                   END;

                   --04) La Fecha de Pago es Incorrecto
                   BEGIN

                     IF (lv_reg_per_carga_pagos_masiv.fec_pago IS NOT NULL) THEN
                        lv_fec_pago := TO_DATE(lv_reg_per_carga_pagos_masiv.fec_pago,'DD/MM/YYYY');
                     END IF;

                   EXCEPTION
                      WHEN OTHERS THEN
                         INSERT INTO PER_ERROR_PAGOS_BANCA_MASIV(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(lv_reg_per_carga_pagos_masiv.Val_Fila, '04', 'Dato no es una fecha valida -Fecha Pago');
                   END;
          END LOOP;
       END IF;

      EXIT WHEN c_carga_pagos_masiv%NOTFOUND;
         END LOOP;
      CLOSE c_carga_pagos_masiv;

      SELECT COUNT(*)
      INTO  lv_cant_Erro
      FROM  PER_ERROR_PAGOS_BANCA_MASIV;

       p_cod_error := lv_cant_Erro;

   EXCEPTION
      WHEN OTHERS THEN
          ln_sqlcode := SQLCODE;
          ls_sqlerrm := substr(sqlerrm,1,250);
          RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_VALID_PAGOS_BANCA_MASIV: '||ls_sqlerrm);
   END PER_PR_VALID_PAGOS_BANCA_MASIV;

   PROCEDURE PER_PR_PROCE_PAGOS_BANCO_MASIV(
      p_cod_pais                                 seg_pais.cod_pais%TYPE,
      p_cod_soci                                 seg_socie.cod_soci%TYPE,
      p_cod_cban                                 ccc_cuent_corri_banca.cod_cc%TYPE,
      p_cod_usua                                  seg_usuar.use_usua%TYPE)
   IS

      lv_num_lote                                       ccc_movim_banca.num_lote%TYPE;
      lv_reg_per_movim_banca_cabec      per_movim_banca_cabec%ROWTYPE;


      lc_val_esta_movi_pend               CONSTANT VARCHAR2(1):='P';
      lc_val_hora_norm                        CONSTANT VARCHAR2(1):='N';
      lc_cod_tipo_tran                        CONSTANT VARCHAR2(6):='TBEFE';
      lc_cod_tipo_orig_dato               CONSTANT VARCHAR2(2):='01';

   BEGIN

        /* Inicializa variables globales  para registro de log  */
       /* inicializa variables globales  para registro de log  */
      gv_log_cod_pais := p_cod_pais;
      gv_log_cod_soci := p_cod_soci;
      gv_log_user     := p_cod_usua;
      gv_log_cod_modu := 'PER';
      gv_log_cod_proc := '17';

      FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_log_user, gv_cod_proc_ejec);

      gv_des_log:='Inicio CCC_PR_PROCE_PAGOS_BANCO_MASIV parametros ' ||
                  ' 1: '  || p_cod_pais ||
                  ' 2: '  || p_cod_soci ||
                  ' 3: '  || p_cod_cban ||
                  ' 4: '  || p_cod_usua ;

      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais,  gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);


      lv_num_lote:= TO_NUMBER(INT_PKG_CCC.CCC_FN_OBTIE_NUMER_LOTE);

      gv_des_log:='Numeto de Lote a Generar ' || lv_num_lote;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais,  gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);


      gv_des_log:='Inicio Insercion de Pagos Bancarios';
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais,  gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);


      lv_reg_per_movim_banca_cabec.pais_cod_pais:=p_cod_pais;
      lv_reg_per_movim_banca_cabec.tior_tipo_orig_dato:=lc_cod_tipo_orig_dato;
      lv_reg_per_movim_banca_cabec.num_lote_inte := lv_num_lote;
      lv_reg_per_movim_banca_cabec.fec_proc :=  TRUNC(SYSDATE);
      lv_reg_per_movim_banca_cabec.cod_soci:= p_cod_soci;
      lv_reg_per_movim_banca_cabec.cod_ccba:= p_cod_cban;
      lv_reg_per_movim_banca_cabec.sta_lote:=lc_val_esta_movi_pend;
      lv_reg_per_movim_banca_cabec.usu_digi:=p_cod_usua;
      lv_reg_per_movim_banca_cabec.fec_digi := SYSDATE;
      lv_reg_per_movim_banca_cabec.est_moca :=1;

      INSERT INTO PER_MOVIM_BANCA_CABEC VALUES lv_reg_per_movim_banca_cabec;

      INSERT INTO per_movim_banca_detal
         SELECT
           p_cod_pais,        --PAIS_COD_PAIS       VARCHAR2(3) not null,
           lc_cod_tipo_orig_dato,                           --TIOR_TIPO_ORIG_DATO VARCHAR2(2) not null,
           lv_num_lote,                  --MOCA_NUM_LOTE_INTE  VARCHAR2(12) not null,
           per.val_fila,                    --CON_TRAN            NUMBER(10) not null,
           per.fec_pago,                --FEC_PAGO            DATE not null,
           NULL,                             --NUM_FABO            NUMBER(8),
           per.imp_pago,                 --VAL_PAGO            NUMBER(15,2),
           NULL,                             --NUM_CUPO            NUMBER(8),
           per.cod_clie,                   --COD_CONS            VARCHAR2(15),
           NULL,                             --DIG_CHEQ            NUMBER(2),
           NULL,                             --OFI_RECA            NUMBER(5),
           NULL,                             --NOM_OFIC            VARCHAR2(30),
           lc_cod_tipo_tran,          --TIP_TRAN            VARCHAR2(5),
           NULL,                             --NUM_DOCU            VARCHAR2(15),
           lc_val_hora_norm,          --VAL_HORA            VARCHAR2(1),
           NULL,                    --USU_PROC            VARCHAR2(10),
           'PROCESO CARGA MASIVA', --DES_OBSE            VARCHAR2(100),
           SYSDATE,                                        --FEC_PROC            DATE,
           TO_CHAR(SYSDATE,'HH:MI:SS'),                                   --HOR_PROC            VARCHAR2(8),
           0,                             --IMP_PAGO_APLI       NUMBER(12,2),
           per.imp_pago, --IMP_PAGO_PEND       NUMBER(12,2),
           0,                                       --IMP_RECA_GENE       NUMBER(12,2),
           0,                                       --IMP_PERC            NUMBER(12,2),
           lc_val_esta_movi_pend,        --STA_MOVI            VARCHAR2(1) not null,
           NULL,                             --COD_PLAN_EMPL       VARCHAR2(8),
           p_cod_usua,                       --USU_DIGI            VARCHAR2(20) not null,
           SYSDATE,                          --FEC_DIGI            DATE not null,
           p_cod_usua,                       --USU_MODI            VARCHAR2(20),
           SYSDATE,                          --FEC_MODI            DATE,
           1 ,                                          --EST_MODE            VARCHAR2(1) not null
           null
      FROM per_carga_pagos_banca_masiv per;

      gv_des_log:='Se insertaron ' || SQL%ROWCOUNT || ' Pagos bancarios';
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais,  gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

      DELETE FROM per_error_pagos_banca_masiv;
      DELETE FROM per_carga_pagos_banca_masiv;

      gv_des_log:='Fin Insercion de Pagos Bancarios';
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais,  gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

      FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);


  EXCEPTION
     WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, gv_des_log);
         FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 9);
         RAISE_application_error(-20123,
                              'ERROR CCC_PR_PROCE_PAGOS_BANCO_MASIV: ' ||
                               ls_sqlerrm);

   END PER_PR_PROCE_PAGOS_BANCO_MASIV;

END PER_PKG_PROCE;
/
