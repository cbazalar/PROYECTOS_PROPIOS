CREATE OR REPLACE PACKAGE sto_pkg_proce_gener AS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;

  /***************************************************************************
  Descripcion       : Deshabilita Indices Tablas STO previo pase a HIstoricos
  Fecha Creacion    : 13/06/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_desha_indic_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Habilita Indices Tablas STO post pase a HIstoricos
  Fecha Creacion    : 13/06/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_habil_indic_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Pasa la informacion de las tablas de STO a Historicos
  Fecha Creacion    : 11/03/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2,
    psnumeroproceso VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Pasa la informacion de las tablas de STO a Historicos
  Fecha Creacion    : 11/03/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_almac_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  INT_SOLIC_CONSO_CABEC
  Fecha Creacion    : 19/03/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_orden_compr
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_credi
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_solic_credi
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_poven_cabec
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_poven
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2,
    psnumeroproceso VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_poven_cabec
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_solic_poven
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_actua_datos
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_actua_datos
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_dupla_cyzon
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_dupla_cyzon
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_cupon_pago
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_cupon_pago
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  sto_pr_histo_ingre_metas
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_orden_trans
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  sto_pr_histo_ingre_metas
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_ingre_metas
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Historico CIF
  Fecha Creacion    : 05/02/2015
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_carta_flexi
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Insercion en MAE_CLIE_CLASI por monto  minimo
  
  Fecha Creacion    : 12/12/2008
  
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      pscodigoperiodo  : Codigo de periodo
      pscodigocliente: Codigo de cliente
      psnumlote : Numero de lote
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_monto_minim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Modificacion de nivel de riesgo en MAE_CLIEN_DATOS_ADICI
  
  Fecha Creacion    : 12/12/2008
  
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      pscodigoperiodo  : Codigo de periodo
      pscodigocliente: Codigo de cliente
      psnumlote : Numero de lote
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_monto_maxim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : borrado en MAE_CLIE_CLASI por monto  minimo
  
  Fecha Creacion    : 12/12/2008
  
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      pscodigoperiodo  : Codigo de periodo
      pscodigocliente: Codigo de cliente
      psnumlote : Numero de lote
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_rever_monto_minim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Modificacion de nivel de riesgo en MAE_CLIEN_DATOS_ADICI
  
  Fecha Creacion    : 12/12/2008
  
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      pscodigoperiodo  : Codigo de periodo
      pscodigocliente: Codigo de cliente
      psnumlote : Numero de lote
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_rever_monto_maxim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Revierte los Pedidos de los GPS para que sean
                      consolidados con los pedidos provenientes.
  Fecha Creacion    : 17/05/2009
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_rever_pedgp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumerolotesto       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Revierte los Pedidos que se encuentarn en GP1, GP3 que
                      que tienen error en STO
  Fecha Creacion    : 04/03/2014
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_rever_pedgp_error
  (
    pnoidperiodo NUMBER,
    psfechafact  VARCHAR2
  );
  /***************************************************************************
     Descripcion       : Elimina pedidos Orden de Compra STO
     Fecha Creacion    : 09/03/2011
     Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_elimi_pedi2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumerolote          VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psusuario             VARCHAR2
  );
  /***************************************************************************
     Descripcion       : Elimina pedidos Orden de Compra STO
     Fecha Creacion    : 09/03/2011
     Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_bloqu_elimi_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pssecuencia           VARCHAR2,
    psnumeroproceso       VARCHAR2,
    psusuario             VARCHAR2
  );
  /***************************************************************************
   Descripcion       : Elimina CDRs STO
   Fecha Creacion    : 27/10/2009
   Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_elimi_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumerolote          VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psnumproceso          VARCHAR2,
    psusuario             VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Inserta los detalles agregados por los programas de
                      nuevas, session experte, dupla cyzone y oportunidades
                      privilege  en STO
  Fecha Creacion    : 30/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_carga_sto_valid_carga
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera un registro de error de validacion 7 para la
                      orden de transporte
  Fecha Creacion    : 20/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_gener_noved_ortra
  (
    pscodigopais        VARCHAR2,
    psnumerosecuencia   VARCHAR2,
    pscodigotipodoc     VARCHAR2,
    psnumerolote        VARCHAR2,
    pscodestadoentrega  VARCHAR2,
    pscodnovedad        VARCHAR2,
    pscodcalificacion   VARCHAR2,
    psindnovedad        VARCHAR2,
    psindgenerarnovedad VARCHAR2,
    psusuario           VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Migra hacia el historico de INT_HISTO_CONSO_FAMIL_SEGUR
  Fecha Creacion    : 04/05/2011
  Autor             : Jose Lui Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_histo_famil_segur
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Reversa los pedidos hasta gp3
  Fecha Creacion    : 17/06/2011
  Autor             : Jose Lui Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_rever_pedid_gp3(psnumpedido VARCHAR2);

  /***************************************************************************
     Descripcion       : Elimina Poilizas de Familia Segura
     Fecha Creacion    : 17/08/2011
     Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_elimi_poliz
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumerolote          VARCHAR2,
    pssecnumedocu         VARCHAR2
  );

  /***************************************************************************
     Descripcion       : Elimina informacion de MAV
     Fecha Creacion    : 06/08/2013
     Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE sto_pr_elimi_mav_envio(pnoidsolicitud NUMBER);

END sto_pkg_proce_gener;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_gener IS

  codigo_vali7_orden_trans CONSTANT VARCHAR2(10) := 'OT-7';

  /***************************************************************************
  Descripcion       : Deshabilita Indices Tablas STO previo pase a HIstoricos
  Fecha Creacion    : 13/06/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_desha_indic_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  ) IS
    inddeshabilitarindices sto_tipo_docum_digit.ind_desh_indi_hist%TYPE;
  
  BEGIN
  
    SELECT ind_desh_indi_hist
      INTO inddeshabilitarindices
      FROM sto_tipo_docum_digit
     WHERE cod_pais = pscodigopais
       AND cod_tipo_docu = pstipodocumento;
  
    IF (inddeshabilitarindices = 'S') THEN
      EXECUTE IMMEDIATE 'ALTER SESSION SET skip_unusable_indexes=true';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_CABE_DODI_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_CLIE_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEDI_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEMO_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_PROC_IDX UNUSABLE';
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_DESHA_INDIC_HISTO: ' ||
                              ls_sqlerrm);
  END sto_pr_desha_indic_histo;
  /***************************************************************************
  Descripcion       : Habilita Indices Tablas STO post pase a HIstoricos
  Fecha Creacion    : 13/06/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_habil_indic_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  ) IS
  
    inddeshabilitarindices sto_tipo_docum_digit.ind_desh_indi_hist%TYPE;
  
  BEGIN
  
    SELECT ind_desh_indi_hist
      INTO inddeshabilitarindices
      FROM sto_tipo_docum_digit
     WHERE cod_pais = pscodigopais
       AND cod_tipo_docu = pstipodocumento;
    IF (inddeshabilitarindices = 'S') THEN
      --HABILTANDO CONSTRAINTS
      EXECUTE IMMEDIATE 'ALTER TABLE STO_DETAL_DOCUM_EXCEP  ENABLE CONSTRAINT STO_DDEX_DODI_FK';
      EXECUTE IMMEDIATE 'ALTER TABLE STO_AUDIT_EXCEP  ENABLE CONSTRAINT STO_AUEX_DODI_FK';
    
      --HABILITANDO INDICES
      EXECUTE IMMEDIATE 'ALTER INDEX STO_CABE_DODI_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_CLIE_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEDI_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEMO_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_PROC_IDX REBUILD';
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HABIL_INDIC_HISTO: ' ||
                              ls_sqlerrm);
  END sto_pr_habil_indic_histo;
  /***************************************************************************
  Descripcion       : Pasa la informacion de las tablas de STO a Historicos
  Fecha Creacion    : 11/03/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2,
    psnumeroproceso VARCHAR2
  ) IS
  
    CURSOR curinshistosto IS
      SELECT d.num_lote,
             d.sec_nume_docu
        FROM sto_proce_docum_digit d
       WHERE d.num_proc = psnumeroproceso
         AND d.cod_tipo_docu = pstipodocumento
         AND d.cod_pais = pscodigopais;
  
    TYPE t_num_lote IS TABLE OF sto_tmp_docum_digit.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_tmp_docum_digit.sec_nume_docu%TYPE;
  
    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 5000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    lstipodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lstipodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                           pstipodocumento);
  
    OPEN curinshistosto;
    LOOP
      FETCH curinshistosto BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_num_lote.count > 0 THEN
      
        /*INSERTANDO CABECERAS*/
        FORALL i IN 1 .. v_num_lote.count
          INSERT INTO sto_histo_docum_digit
            SELECT d.*
              FROM sto_docum_digit d
             WHERE d.sec_nume_docu = v_sec_nume_docu(i)
               AND d.num_lote = v_num_lote(i)
               AND d.cod_tipo_docu = pstipodocumento
               AND d.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          INSERT INTO sto_histo_detal_docum_excep
            SELECT e.*
              FROM sto_detal_docum_excep e
             WHERE e.sec_nume_docu = v_sec_nume_docu(i)
               AND e.num_lote = v_num_lote(i)
               AND e.cod_tipo_docu = pstipodocumento
               AND e.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          INSERT INTO sto_histo_audit_excep
            SELECT a.*
              FROM sto_audit_excep a
             WHERE a.sec_nume_docu = v_sec_nume_docu(i)
               AND a.num_lote = v_num_lote(i)
               AND a.cod_tipo_docu = pstipodocumento
               AND a.cod_pais = pscodigopais;
      
        IF lstipodocumentodetalle IS NOT NULL THEN
          /*INSERTANDO DETALLES*/
          FORALL i IN 1 .. v_num_lote.count
            INSERT INTO sto_histo_docum_digit
              SELECT d.*
                FROM sto_docum_digit d
               WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                 AND d.num_lote = v_num_lote(i)
                 AND d.cod_tipo_docu = lstipodocumentodetalle
                 AND d.cod_pais = pscodigopais;
        
          FORALL i IN 1 .. v_num_lote.count
            INSERT INTO sto_histo_detal_docum_excep
              SELECT e.*
                FROM sto_detal_docum_excep e
               WHERE e.num_lote = v_num_lote(i)
                 AND e.cod_tipo_docu = lstipodocumentodetalle
                 AND e.cod_pais = pscodigopais
                 AND EXISTS
               (SELECT 1
                        FROM sto_docum_digit d
                       WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                         AND d.num_lote = v_num_lote(i)
                         AND d.cod_tipo_docu = lstipodocumentodetalle
                         AND d.cod_pais = pscodigopais
                         AND d.sec_nume_docu = e.sec_nume_docu
                         AND d.num_lote = e.num_lote);
        
          FORALL i IN 1 .. v_num_lote.count
            INSERT INTO sto_histo_audit_excep
              SELECT a.*
                FROM sto_audit_excep a
               WHERE a.num_lote = v_num_lote(i)
                 AND a.cod_tipo_docu = lstipodocumentodetalle
                 AND a.cod_pais = pscodigopais
                 AND EXISTS
               (SELECT 1
                        FROM sto_docum_digit d
                       WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                         AND d.num_lote = v_num_lote(i)
                         AND d.cod_tipo_docu = lstipodocumentodetalle
                         AND d.cod_pais = pscodigopais
                         AND d.sec_nume_docu = a.sec_nume_docu
                         AND d.num_lote = a.num_lote);
        
          /*ELIMINANDO DETALLES*/
          FORALL i IN 1 .. v_num_lote.count
            DELETE sto_audit_excep a
             WHERE a.num_lote = v_num_lote(i)
               AND a.cod_tipo_docu = lstipodocumentodetalle
               AND a.cod_pais = pscodigopais
               AND EXISTS
             (SELECT 1
                      FROM sto_docum_digit d
                     WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                       AND d.num_lote = v_num_lote(i)
                       AND d.cod_tipo_docu = lstipodocumentodetalle
                       AND d.cod_pais = pscodigopais
                       AND d.sec_nume_docu = a.sec_nume_docu
                       AND d.num_lote = a.num_lote);
        
          FORALL i IN 1 .. v_num_lote.count
            DELETE sto_detal_docum_excep e
             WHERE e.num_lote = v_num_lote(i)
               AND e.cod_tipo_docu = lstipodocumentodetalle
               AND e.cod_pais = pscodigopais
               AND EXISTS
             (SELECT 1
                      FROM sto_docum_digit d
                     WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                       AND d.num_lote = v_num_lote(i)
                       AND d.cod_tipo_docu = lstipodocumentodetalle
                       AND d.cod_pais = pscodigopais
                       AND d.sec_nume_docu = e.sec_nume_docu
                       AND d.num_lote = e.num_lote);
        
          FORALL i IN 1 .. v_num_lote.count
            DELETE sto_docum_digit d
             WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
               AND d.num_lote = v_num_lote(i)
               AND d.cod_tipo_docu = lstipodocumentodetalle
               AND d.cod_pais = pscodigopais;
        
        END IF;
      
        /*ELIMINANDO CABECERAS*/
        FORALL i IN 1 .. v_num_lote.count
          DELETE sto_audit_excep a
           WHERE a.sec_nume_docu = v_sec_nume_docu(i)
             AND a.cod_tipo_docu = pstipodocumento
             AND a.num_lote = v_num_lote(i)
             AND a.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE sto_detal_docum_excep e
           WHERE e.sec_nume_docu = v_sec_nume_docu(i)
             AND e.cod_tipo_docu = pstipodocumento
             AND e.num_lote = v_num_lote(i)
             AND e.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE sto_docum_digit d
           WHERE d.sec_nume_docu = v_sec_nume_docu(i)
             AND d.cod_tipo_docu = pstipodocumento
             AND d.num_lote = v_num_lote(i)
             AND d.cod_pais = pscodigopais;
      
      END IF;
    
      EXIT WHEN curinshistosto%NOTFOUND;
    
    END LOOP;
    CLOSE curinshistosto;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR STO_PR_HISTO: ' || ls_sqlerrm);
  END sto_pr_histo;
  /***************************************************************************
  Descripcion       : Pasa la informacion de las tablas de STO a Historicos
  Fecha Creacion    : 11/03/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_almac_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  ) IS
  
    CURSOR curinshistosto IS
      SELECT d.num_lote,
             d.sec_nume_docu
        FROM sto_tmp_docum_digit d;
  
    TYPE t_num_lote IS TABLE OF sto_tmp_docum_digit.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_tmp_docum_digit.sec_nume_docu%TYPE;
  
    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 5000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    lstipodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
    inddeshabilitarindices sto_tipo_docum_digit.ind_desh_indi_hist%TYPE;
  
  BEGIN
  
    lstipodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                           pstipodocumento);
  
    SELECT ind_desh_indi_hist
      INTO inddeshabilitarindices
      FROM sto_tipo_docum_digit
     WHERE cod_pais = pscodigopais
       AND cod_tipo_docu = pstipodocumento;
  
    IF (inddeshabilitarindices = 'S') THEN
      EXECUTE IMMEDIATE 'ALTER SESSION SET skip_unusable_indexes=true';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_CABE_DODI_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_CLIE_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEDI_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEMO_IDX UNUSABLE';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_PROC_IDX UNUSABLE';
    END IF;
    OPEN curinshistosto;
    LOOP
      FETCH curinshistosto BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_num_lote.count > 0 THEN
      
        /*INSERTANDO CABECERAS*/
        FORALL i IN 1 .. v_num_lote.count
          INSERT INTO sto_histo_docum_digit
            SELECT d.*
              FROM sto_docum_digit d
             WHERE d.sec_nume_docu = v_sec_nume_docu(i)
               AND d.num_lote = v_num_lote(i)
               AND d.cod_tipo_docu = pstipodocumento
               AND d.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          INSERT INTO sto_histo_detal_docum_excep
            SELECT e.*
              FROM sto_detal_docum_excep e
             WHERE e.sec_nume_docu = v_sec_nume_docu(i)
               AND e.num_lote = v_num_lote(i)
               AND e.cod_tipo_docu = pstipodocumento
               AND e.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          INSERT INTO sto_histo_audit_excep
            SELECT a.*
              FROM sto_audit_excep a
             WHERE a.sec_nume_docu = v_sec_nume_docu(i)
               AND a.num_lote = v_num_lote(i)
               AND a.cod_tipo_docu = pstipodocumento
               AND a.cod_pais = pscodigopais;
      
        IF lstipodocumentodetalle IS NOT NULL THEN
          /*INSERTANDO DETALLES*/
          FORALL i IN 1 .. v_num_lote.count
            INSERT INTO sto_histo_docum_digit
              SELECT d.*
                FROM sto_docum_digit d
               WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                 AND d.num_lote = v_num_lote(i)
                 AND d.cod_tipo_docu = lstipodocumentodetalle
                 AND d.cod_pais = pscodigopais;
        
          FORALL i IN 1 .. v_num_lote.count
            INSERT INTO sto_histo_detal_docum_excep
              SELECT e.*
                FROM sto_detal_docum_excep e
               WHERE e.num_lote = v_num_lote(i)
                 AND e.cod_tipo_docu = lstipodocumentodetalle
                 AND e.cod_pais = pscodigopais
                 AND EXISTS
               (SELECT 1
                        FROM sto_docum_digit d
                       WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                         AND d.num_lote = v_num_lote(i)
                         AND d.cod_tipo_docu = lstipodocumentodetalle
                         AND d.cod_pais = pscodigopais
                         AND d.sec_nume_docu = e.sec_nume_docu
                         AND d.num_lote = e.num_lote);
        
          FORALL i IN 1 .. v_num_lote.count
            INSERT INTO sto_histo_audit_excep
              SELECT a.*
                FROM sto_audit_excep a
               WHERE a.num_lote = v_num_lote(i)
                 AND a.cod_tipo_docu = lstipodocumentodetalle
                 AND a.cod_pais = pscodigopais
                 AND EXISTS
               (SELECT 1
                        FROM sto_docum_digit d
                       WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                         AND d.num_lote = v_num_lote(i)
                         AND d.cod_tipo_docu = lstipodocumentodetalle
                         AND d.cod_pais = pscodigopais
                         AND d.sec_nume_docu = a.sec_nume_docu
                         AND d.num_lote = a.num_lote);
        
          /*ELIMINANDO DETALLES*/
          FORALL i IN 1 .. v_num_lote.count
            DELETE sto_audit_excep a
             WHERE a.num_lote = v_num_lote(i)
               AND a.cod_tipo_docu = lstipodocumentodetalle
               AND a.cod_pais = pscodigopais
               AND EXISTS
             (SELECT 1
                      FROM sto_docum_digit d
                     WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                       AND d.num_lote = v_num_lote(i)
                       AND d.cod_tipo_docu = lstipodocumentodetalle
                       AND d.cod_pais = pscodigopais
                       AND d.sec_nume_docu = a.sec_nume_docu
                       AND d.num_lote = a.num_lote);
        
          FORALL i IN 1 .. v_num_lote.count
            DELETE sto_detal_docum_excep e
             WHERE e.num_lote = v_num_lote(i)
               AND e.cod_tipo_docu = lstipodocumentodetalle
               AND e.cod_pais = pscodigopais
               AND EXISTS
             (SELECT 1
                      FROM sto_docum_digit d
                     WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
                       AND d.num_lote = v_num_lote(i)
                       AND d.cod_tipo_docu = lstipodocumentodetalle
                       AND d.cod_pais = pscodigopais
                       AND d.sec_nume_docu = e.sec_nume_docu
                       AND d.num_lote = e.num_lote);
        
          FORALL i IN 1 .. v_num_lote.count
            DELETE sto_docum_digit d
             WHERE d.sec_nume_docu_cabe = v_sec_nume_docu(i)
               AND d.num_lote = v_num_lote(i)
               AND d.cod_tipo_docu = lstipodocumentodetalle
               AND d.cod_pais = pscodigopais;
        
        END IF;
      
        /*ELIMINANDO CABECERAS*/
        FORALL i IN 1 .. v_num_lote.count
          DELETE sto_audit_excep a
           WHERE a.sec_nume_docu = v_sec_nume_docu(i)
             AND a.cod_tipo_docu = pstipodocumento
             AND a.num_lote = v_num_lote(i)
             AND a.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE sto_detal_docum_excep e
           WHERE e.sec_nume_docu = v_sec_nume_docu(i)
             AND e.cod_tipo_docu = pstipodocumento
             AND e.num_lote = v_num_lote(i)
             AND e.cod_pais = pscodigopais;
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE sto_docum_digit d
           WHERE d.sec_nume_docu = v_sec_nume_docu(i)
             AND d.cod_tipo_docu = pstipodocumento
             AND d.num_lote = v_num_lote(i)
             AND d.cod_pais = pscodigopais;
      
      END IF;
    
      EXIT WHEN curinshistosto%NOTFOUND;
    
    END LOOP;
    CLOSE curinshistosto;
  
    IF (inddeshabilitarindices = 'S') THEN
      --HABILTANDO CONSTRAINTS
      EXECUTE IMMEDIATE 'ALTER TABLE STO_DETAL_DOCUM_EXCEP  ENABLE CONSTRAINT STO_DDEX_DODI_FK';
      EXECUTE IMMEDIATE 'ALTER TABLE STO_AUDIT_EXCEP  ENABLE CONSTRAINT STO_AUEX_DODI_FK';
    
      --HABILITANDO INDICES
      EXECUTE IMMEDIATE 'ALTER INDEX STO_CABE_DODI_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_CLIE_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEDI_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_FEMO_IDX REBUILD';
      EXECUTE IMMEDIATE 'ALTER INDEX STO_DODI_PROC_IDX REBUILD';
    END IF;
  END sto_pr_almac_histo;

  /***************************************************************************
   Descripcion       : Migra hacia el historico de  INT_SOLIC_CONSO_CABEC
  Fecha Creacion    : 31/03/2011
   Autor             : Jose Cairampoma
   ***************************************************************************/
  PROCEDURE sto_pr_histo_orden_compr
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli IS
      SELECT c.*
        FROM int_solic_conso_cabec c,
             sto_tmp_docum_digit   t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t;
  
    rows NATURAL := 5000;
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    /*CARGANDO LA INFORMACION A BORRAR*/
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
  
    /*INSERTANDO Y BORRANDO CABECERAS PEDIDOS EN HISTORICO*/
    OPEN curinshistosoli;
    LOOP
    
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO ped_histo_solic_conso_cabec
        VALUES int_solic_conso_tab
          (i);
    
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO ped_histo_solic_conso_detal
          SELECT det.*
            FROM int_solic_conso_detal det
           WHERE det.num_lote = int_solic_conso_tab(i).num_lote
             AND det.sec_nume_docu_cabe = int_solic_conso_tab(i)
                .sec_nume_docu;
    
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        DELETE FROM int_solic_conso_detal det
         WHERE det.num_lote = int_solic_conso_tab(i).num_lote
           AND det.sec_nume_docu_cabe = int_solic_conso_tab(i)
              .sec_nume_docu;
    
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        DELETE FROM int_solic_conso_cabec a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
    
    END LOOP;
    CLOSE curinshistosoli;
    /*PASANDO  AHISTORIOCOS TABLAS DE STO*/
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_ORDEN_COMPR: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_orden_compr;

  /***************************************************************************
    Descripcion       : Migra hacia el historico de  int_solic_conso_credi
    Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_solic_credi
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli IS
      SELECT c.*
        FROM int_solic_conso_credi c,
             sto_tmp_docum_digit   t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_credi%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
  
    OPEN curinshistosoli;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_solic_conso_credi
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_credi a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
  
    -- Envio al historico dela informacion de Envio de Mails
    INSERT INTO sto_histo_envio_email_scc
      (num_proce,
       val_mail_gere_zona,
       val_subj,
       val_nomb_clie,
       val_tipo_docu,
       cod_clie,
       ind_rech,
       num_lote,
       sec_nume_docu)
      SELECT DISTINCT e.num_proce,
                      e.val_mail_gere_zona,
                      e.val_subj,
                      e.val_nomb_clie,
                      e.val_tipo_docu,
                      e.cod_clie,
                      e.ind_rech,
                      e.num_lote,
                      e.sec_nume_docu
        FROM sto_envio_email_scc e,
             sto_tmp_docum_digit t
       WHERE e.num_lote = t.num_lote
         AND e.sec_nume_docu = t.sec_nume_docu;
  
    DELETE FROM sto_envio_email_scc e
     WHERE EXISTS (SELECT 1
              FROM sto_tmp_docum_digit t
             WHERE e.num_lote = t.num_lote
               AND e.sec_nume_docu = t.sec_nume_docu);
    --
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_SOLIC_CREDI: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_solic_credi;
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_poven_cabec
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_poven
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2,
    psnumeroproceso VARCHAR2
  ) IS
    CURSOR curinshistosoli IS
      SELECT c.*
        FROM int_solic_conso_poven_cabec c,
             sto_proce_docum_digit       t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote
         AND t.cod_tipo_docu = pstipodocumento
         AND t.num_proc = psnumeroproceso
         AND t.cod_pais = pscodigopais;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_poven_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 5000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pstipodocumento);
  
    OPEN curinshistosoli;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_poven_detal
          SELECT det.*
            FROM int_solic_conso_poven_detal det
           WHERE det.num_lote = int_solic_conso_tab(i).num_lote
             AND EXISTS
           (SELECT 1
                    FROM sto_docum_digit d
                   WHERE d.sec_nume_docu_cabe = int_solic_conso_tab(i)
                        .sec_nume_docu
                     AND d.num_lote = int_solic_conso_tab(i).num_lote
                     AND d.cod_tipo_docu = lscodigodocumentodetalle
                     AND d.cod_pais = pscodigopais
                     AND d.sec_nume_docu = det.sec_nume_docu
                     AND d.num_lote = det.num_lote);
    
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        DELETE FROM int_solic_conso_poven_detal det
         WHERE EXISTS (SELECT 1
                  FROM sto_docum_digit d
                 WHERE d.sec_nume_docu_cabe = int_solic_conso_tab(i)
                      .sec_nume_docu
                   AND d.num_lote = int_solic_conso_tab(i).num_lote
                   AND d.cod_tipo_docu = lscodigodocumentodetalle
                   AND d.cod_pais = pscodigopais
                   AND d.sec_nume_docu = det.sec_nume_docu
                   AND d.num_lote = det.num_lote);
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_poven_cabec
        VALUES int_solic_conso_tab
          (i);
    
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        DELETE FROM int_solic_conso_poven_cabec a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
    
    END LOOP;
    CLOSE curinshistosoli;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_POVEN: ' || ls_sqlerrm);
  END sto_pr_histo_poven;
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_poven_cabec
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_solic_poven
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
    CURSOR curinshistosoli IS
      SELECT c.*
        FROM int_solic_conso_poven_cabec c,
             sto_tmp_docum_digit         t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_poven_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 5000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pstipodocumento);
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
  
    OPEN curinshistosoli;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_poven_cabec
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        INSERT INTO int_histo_conso_poven_detal
          SELECT det.*
            FROM int_solic_conso_poven_detal det
           WHERE det.num_lote = int_solic_conso_tab(i).num_lote
             AND EXISTS
           (SELECT 1
                    FROM sto_docum_digit d
                   WHERE d.sec_nume_docu_cabe = int_solic_conso_tab(i)
                        .sec_nume_docu
                     AND d.num_lote = int_solic_conso_tab(i).num_lote
                     AND d.cod_tipo_docu = lscodigodocumentodetalle
                     AND d.cod_pais = pscodigopais
                     AND d.sec_nume_docu = det.sec_nume_docu
                     AND d.num_lote = det.num_lote);
      
        DELETE FROM int_solic_conso_poven_detal det
         WHERE det.num_lote = int_solic_conso_tab(i).num_lote
           AND EXISTS
         (SELECT 1
                  FROM sto_docum_digit d
                 WHERE d.sec_nume_docu_cabe = int_solic_conso_tab(i)
                      .sec_nume_docu
                   AND d.num_lote = int_solic_conso_tab(i).num_lote
                   AND d.cod_tipo_docu = lscodigodocumentodetalle
                   AND d.cod_pais = pscodigopais
                   AND d.sec_nume_docu = det.sec_nume_docu
                   AND d.num_lote = det.num_lote);
      
        DELETE FROM int_solic_conso_poven_cabec a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_SOLIC_POVEN: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_solic_poven;
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_actua_datos
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_actua_datos
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli IS
      SELECT c.*
      
        FROM int_solic_conso_actua_datos c,
             sto_tmp_docum_digit         t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_actua_datos%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
  
    OPEN curinshistosoli;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_actua_datos
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_actua_datos a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_ACTUA_DATOS: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_actua_datos;
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_dupla_cyzon
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_dupla_cyzon
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli IS
      SELECT c.*
        FROM int_solic_conso_dupla_cyzon c,
             sto_tmp_docum_digit         t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_dupla_cyzon%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
  
    OPEN curinshistosoli;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_dupla_cyzon
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_dupla_cyzon a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_DUPLA_CYZON: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_dupla_cyzon;
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  int_solic_conso_cupon_pago
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_cupon_pago
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli IS
      SELECT c.*
        FROM int_solic_conso_cupon_pago c,
             sto_tmp_docum_digit        t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_cupon_pago%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
    OPEN curinshistosoli;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_cupon_pago
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_cupon_pago a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
  
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
    UPDATE ccc_detal_cupon_trami_depur
       SET sicu_oid_situ_cupo = 2
     WHERE sicu_oid_situ_cupo = 1;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_CUPON_PAGO: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_cupon_pago;
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  sto_pr_histo_ingre_metas
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_orden_trans
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli IS
      SELECT c.*
        FROM int_solic_conso_orden_trans c,
             sto_tmp_docum_digit         t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_orden_trans%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
    OPEN curinshistosoli;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_orden_trans
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_orden_trans a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
  
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_ORDEN_TRANS: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_orden_trans;
  /***************************************************************************
  Descripcion       : Migra hacia el historico de  sto_pr_histo_ingre_metas
  Fecha Creacion    : 31/03/2011
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_ingre_metas
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli(vstipodocumento VARCHAR2) IS
      SELECT c.*
        FROM int_solic_conso_ingre_metas c,
             sto_tmp_docum_digit         t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote
         AND t.cod_tipo_docu = vstipodocumento;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_ingre_metas%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
    OPEN curinshistosoli(pstipodocumento);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_ingre_metas
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_ingre_metas a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
  
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_INGRE_METAS: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_ingre_metas;
  /***************************************************************************
  Descripcion       : Historico CIF
  Fecha Creacion    : 05/02/2015
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_histo_carta_flexi
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR curinshistosoli(vstipodocumento VARCHAR2) IS
      SELECT c.*
        FROM int_solic_conso_carta_flexi c,
             sto_tmp_docum_digit         t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote
         AND t.cod_tipo_docu = vstipodocumento;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_carta_flexi%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
    OPEN curinshistosoli(pstipodocumento);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_carta_flexi
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_carta_flexi a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
  
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_carta_flexi: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_carta_flexi;
  /***************************************************************************
  Descripcion       : Insercion en MAE_CLIE_CLASI por monto  minimo
  Fecha Creacion    : 12/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_monto_minim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  ) IS
  
    lsoid_clie_tipo_subti mae_clien_clasi.ctsu_oid_clie_tipo_subt%TYPE;
    lsoid_clasi           sto_param_gener_occrr.val_param%TYPE;
    lsoid_tipo_clasi      sto_param_gener_occrr.val_param%TYPE;
    lsoid_periodo         cra_perio.oid_peri%TYPE;
  
    contador NUMBER := 0;
  
  BEGIN
  
    lsoid_clasi      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_MTMI_OID_CLAS');
    lsoid_tipo_clasi := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_MTMA_OID_TICLAS');
    lsoid_periodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  
    UPDATE int_solic_conso_cabec
       SET ind_val_mtmi  = '1',
           ind_erro_mtmi = '0',
           val_mont_pedi = NULL
     WHERE cod_pais = pscodigopais
       AND cod_peri = pscodigoperiodo
       AND cod_clie = pscodigocliente
       AND num_lote = psnumlote;
  
    SELECT a.oid_clie_tipo_subt
      INTO lsoid_clie_tipo_subti
      FROM mae_clien_tipo_subti a
     WHERE a.clie_oid_clie IN
           (SELECT b.oid_clie
              FROM mae_clien b
             WHERE b.cod_clie = pscodigocliente)
       AND a.ticl_oid_tipo_clie = 2
       AND a.sbti_oid_subt_clie = 1;
  
    SELECT COUNT(1)
      INTO contador
      FROM mae_clien_clasi
     WHERE ctsu_oid_clie_tipo_subt = lsoid_clie_tipo_subti
       AND clas_oid_clas = to_number(lsoid_clasi)
       AND tccl_oid_tipo_clasi = to_number(lsoid_tipo_clasi);
  
    IF (contador = 0) THEN
    
      INSERT INTO mae_clien_clasi
        (oid_clie_clas,
         ctsu_oid_clie_tipo_subt,
         clas_oid_clas,
         perd_oid_peri,
         tccl_oid_tipo_clasi,
         fec_clas,
         ind_ppal,
         fec_ulti_actu)
      VALUES
        (mae_clcl_seq.nextval,
         lsoid_clie_tipo_subti,
         to_number(lsoid_clasi),
         lsoid_periodo,
         to_number(lsoid_tipo_clasi),
         trunc(SYSDATE),
         0,
         SYSDATE);
    
    END IF;
  
  END sto_pr_monto_minim;

  /***************************************************************************
  Descripcion       : borrado en MAE_CLIE_CLASI por monto  minimo
  
  Fecha Creacion    : 12/12/2008
  
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      pscodigoperiodo  : Codigo de periodo
      pscodigocliente: Codigo de cliente
      psnumlote : Numero de lote
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_rever_monto_minim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  ) IS
  
    --lsoid_clie_tipo_subti MAE_CLIEN_CLASI.CTSU_OID_CLIE_TIPO_SUBT%type;
    lsoid_clasi      sto_param_gener_occrr.val_param%TYPE;
    lsoid_tipo_clasi sto_param_gener_occrr.val_param%TYPE;
    --lsoid_periodo     CRA_PERIO.OID_PERI%type;
  
  BEGIN
  
    lsoid_clasi      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_MTMI_OID_CLAS');
    lsoid_tipo_clasi := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_MTMA_OID_TICLAS');
    --lsoid_periodo:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);
  
    DELETE FROM mae_clien_clasi a
     WHERE a.clas_oid_clas = to_number(lsoid_clasi)
       AND a.tccl_oid_tipo_clasi = to_number(lsoid_tipo_clasi)
       AND a.ctsu_oid_clie_tipo_subt IN
           (SELECT c.oid_clie_tipo_subt
              FROM mae_clien_tipo_subti c
             WHERE c.clie_oid_clie IN
                   (SELECT b.oid_clie
                      FROM mae_clien b
                     WHERE b.cod_clie = pscodigocliente)
               AND c.ticl_oid_tipo_clie = 2
               AND c.sbti_oid_subt_clie = 1);
  
    UPDATE int_solic_conso_cabec
       SET ind_val_mtmi = '0'
     WHERE cod_pais = pscodigopais
       AND cod_peri = pscodigoperiodo
       AND cod_clie = pscodigocliente
       AND num_lote = psnumlote;
  
  END sto_pr_rever_monto_minim;
  /***************************************************************************
  Descripcion       : Modificacion de nivel de riesgo en MAE_CLIEN_DATOS_ADICI
  
  Fecha Creacion    : 12/12/2008
  
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      pscodigoperiodo  : Codigo de periodo
      pscodigocliente: Codigo de cliente
      psnumlote : Numero de lote
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_monto_maxim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  ) IS
    lsoid_riesgo       mae_clien_datos_adici.niri_oid_nive_ries%TYPE;
    lsparamnivelriesgo sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lsparamnivelriesgo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_NIVEL_RIESGO');
  
    SELECT a.niri_oid_nive_ries
      INTO lsoid_riesgo
      FROM mae_clien_datos_adici a
     WHERE a.clie_oid_clie IN
           (SELECT b.oid_clie
              FROM mae_clien b
             WHERE b.cod_clie = pscodigocliente);
  
    UPDATE mae_clien_datos_adici a
       SET a.niri_oid_nive_ries = to_number(lsparamnivelriesgo)
     WHERE a.clie_oid_clie IN
           (SELECT b.oid_clie
              FROM mae_clien b
             WHERE b.cod_clie = pscodigocliente);
  
    UPDATE int_solic_conso_cabec
       SET ind_val_mtma       = '1',
           niri_oid_nive_ries = lsoid_riesgo,
           ind_erro_mtma      = '0',
           val_mont_pedi      = NULL
     WHERE cod_pais = pscodigopais
       AND cod_peri = pscodigoperiodo
       AND cod_clie = pscodigocliente
       AND num_lote = psnumlote;
  
  END sto_pr_monto_maxim;
  /***************************************************************************
  Descripcion       : Modificacion de nivel de riesgo en MAE_CLIEN_DATOS_ADICI
  
  Fecha Creacion    : 12/12/2008
  
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      pscodigoperiodo  : Codigo de periodo
      pscodigocliente: Codigo de cliente
      psnumlote : Numero de lote
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_rever_monto_maxim
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumlote       VARCHAR2
  ) IS
  
    lsoid_riesgo mae_clien_datos_adici.niri_oid_nive_ries%TYPE;
  
  BEGIN
  
    SELECT niri_oid_nive_ries
      INTO lsoid_riesgo
      FROM int_solic_conso_cabec
     WHERE cod_pais = pscodigopais
       AND cod_peri = pscodigoperiodo
       AND cod_clie = pscodigocliente
       AND num_lote = psnumlote;
  
    UPDATE mae_clien_datos_adici a
       SET a.niri_oid_nive_ries = lsoid_riesgo
     WHERE a.clie_oid_clie IN
           (SELECT b.oid_clie
              FROM mae_clien b
             WHERE b.cod_clie = pscodigocliente);
  
    UPDATE int_solic_conso_cabec
       SET ind_val_mtma       = '0',
           niri_oid_nive_ries = NULL
     WHERE cod_pais = pscodigopais
       AND cod_peri = pscodigoperiodo
       AND cod_clie = pscodigocliente
       AND num_lote = psnumlote;
  
  END sto_pr_rever_monto_maxim;

  /***************************************************************************
  Descripcion       : Revierte los Pedidos de los GPS para que sean
                      consolidados con los pedidos provenientes.
  Fecha Creacion    : 17/05/2009
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_rever_pedgp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumerolotesto       VARCHAR2
  ) IS
  
    CURSOR curcabecera(vngp NUMBER) IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.clie_oid_clie,
             cons.soca_oid_soli_cabe_refe,
             cons.perd_oid_peri
        FROM int_solic_conso_cabec cons,
             int_solic_cabec       tmp
       WHERE cons.cod_pais = tmp.cod_pais
         AND cons.cod_peri = tmp.cam_soli
         AND cons.cod_clie = tmp.cod_clie
         AND cons.ind_ocs_proc = '1'
         AND tmp.num_lote_sto = psnumerolotesto
         AND EXISTS
       (SELECT 1
                FROM ped_solic_cabec p
               WHERE p.oid_soli_cabe = cons.soca_oid_soli_cabe_refe
                 AND p.grpr_oid_grup_proc <= vngp);
  
    TYPE t_cab_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cab_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_cab_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_cab_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_cab_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_soca_oid_soli_cabe_refe IS TABLE OF int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
  
    v_cab_cod_peri            t_cab_cod_peri;
    v_cab_cod_clie            t_cab_cod_clie;
    v_cab_num_lote            t_cab_num_lote;
    v_cab_sec_nume_docu       t_cab_sec_nume_docu;
    v_cab_clie_oid_clie       t_cab_clie_oid_clie;
    v_soca_oid_soli_cabe_refe t_soca_oid_soli_cabe_refe;
    v_perd_oid_peri           t_perd_oid_peri;
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
    lngp NUMBER;
  
    CURSOR curoidenvi(vnoidsolicabe NUMBER) IS
      SELECT menv_oid_envi
        FROM mav_solic_envio
       WHERE soca_oid_soli_cabe = vnoidsolicabe;
  
    TYPE t_menv_oid_envi IS TABLE OF mav_solic_envio.menv_oid_envi%TYPE;
    v_menv_oid_envi t_menv_oid_envi;
  
  BEGIN
  
    SELECT val_para
      INTO lngp
      FROM bas_param_pais
     WHERE cod_pais = pscodigopais
       AND nom_para = 'nGPConsPedidos'
       AND cod_sist = 'OCR';
  
    IF lngp > 0 THEN
      lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                               pscodigotipodocumento);
      OPEN curcabecera(lngp);
      LOOP
        FETCH curcabecera BULK COLLECT
          INTO v_cab_cod_peri,
               v_cab_cod_clie,
               v_cab_num_lote,
               v_cab_sec_nume_docu,
               v_cab_clie_oid_clie,
               v_soca_oid_soli_cabe_refe,
               v_perd_oid_peri LIMIT rows;
        IF v_cab_sec_nume_docu.count > 0 THEN
        
          IF lngp > 1 THEN
          
            FOR i IN v_cab_sec_nume_docu.first .. v_cab_sec_nume_docu.last
            LOOP
            
              OPEN curoidenvi(v_soca_oid_soli_cabe_refe(i));
              LOOP
                FETCH curoidenvi BULK COLLECT
                  INTO v_menv_oid_envi LIMIT rows;
                IF v_menv_oid_envi.count > 0 THEN
                
                  FORALL j IN 1 .. v_menv_oid_envi.count
                    DELETE FROM mav_solic_envio
                     WHERE menv_oid_envi = v_menv_oid_envi(j);
                
                  FORALL j IN 1 .. v_menv_oid_envi.count
                    DELETE FROM mav_envio
                     WHERE oid_envi = v_menv_oid_envi(j)
                       AND EXISTS
                     (SELECT 1
                              FROM mav_detal_mav
                             WHERE oid_deta_mav = denv_oid_deta_mav
                               AND tepr_oid_tipo_esta_proc = 3);
                
                  FORALL j IN 1 .. v_menv_oid_envi.count
                    UPDATE mav_envio
                       SET ind_envi = 'P'
                     WHERE oid_envi = v_menv_oid_envi(j)
                       AND EXISTS
                     (SELECT 1
                              FROM mav_detal_mav
                             WHERE oid_deta_mav = denv_oid_deta_mav
                               AND tepr_oid_tipo_esta_proc = 4);
                
                END IF;
                EXIT WHEN curoidenvi%NOTFOUND;
              END LOOP;
              CLOSE curoidenvi;
            
              --ELIMINAMOS INFORMACION DE NUEVO MAV
              sto_pr_elimi_mav_envio(v_soca_oid_soli_cabe_refe(i));
            
            END LOOP;
          
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM msg_buzon_mensa
               WHERE clie_oid_clie = v_cab_clie_oid_clie(i)
                 AND ind_acti = 1
                 AND EXISTS (SELECT oid_mens
                        FROM msg_mensa
                       WHERE cod_mens LIKE 'MAV%'
                         AND mens_oid_mens = oid_mens);
          
            --  Mensajes de PED
          
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM ped_solic_mensa
               WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
          
            --  Incentivos : Se deben de ejecutar los siguientes querys:
          
            /*FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM inc_solic_concu_punta
               WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
            
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM inc_solic_concu_punta_temp
               WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
            
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM inc_pedid_concu_recom
               WHERE clr3_oid_clie_rete IN (SELECT c.oid_clie_rete
                                              FROM inc_clien_recte       c,
                                                   inc_concu_param_gener a,
                                                   inc_concu_param_consu b
                                             WHERE c.copa_oid_para_gral = a.oid_para_gral
                                               AND a.oid_para_gral = b.copa_oid_para_gral
                                               AND a.bcal_oid_base_calc = 4 -- dato fijo
                                               AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_desd) <=
                                                   v_cab_cod_peri(i) -- periodo actual
                                               AND v_cab_cod_peri(1) <=
                                                   gen_fn_calcu_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_hast),
                                                                      b.val_peri_eval - 1) -- periodo actual
                                               AND c.clie_oid_clie = v_cab_clie_oid_clie(i) -- oid cliente
                                            )
                 AND perd_oid_peri = v_perd_oid_peri(i)
                 AND clre_oid_clie_redo IS NULL;
            
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM inc_pedid_concu_recom
               WHERE clre_oid_clie_redo IN (SELECT oid_clie_redo
                                              FROM inc_clien_recdo       d,
                                                   inc_clien_recte       e,
                                                   inc_concu_param_gener a,
                                                   inc_concu_param_consu b
                                             WHERE d.clr3_oid_clie_rete = e.oid_clie_rete
                                               AND d.clie_oid_clie = v_cab_clie_oid_clie(i) -- oid del cliente
                                               AND d.perd_oid_peri = v_perd_oid_peri(i) -- periodo actual
                                               AND e.copa_oid_para_gral = a.oid_para_gral
                                               AND a.oid_para_gral = b.copa_oid_para_gral
                                               AND a.bcal_oid_base_calc = 4 -- 4 dato fijo
                                               AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_desd) <=
                                                   v_cab_cod_peri(i) -- periodo actual
                                               AND v_cab_cod_peri(i) <=
                                                   gen_fn_calcu_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_hast),
                                                                      b.val_peri_eval - 1)
            
                                            )
                 AND perd_oid_peri = v_perd_oid_peri(i);
            
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM inc_pedid_concu_recom_temp
               WHERE perd_oid_peri = v_perd_oid_peri(i)
                 AND clie_oid_clie_rete = v_cab_clie_oid_clie(i);
            
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM inc_solic_concu_recom
               WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
            
            FORALL i IN 1 .. v_cab_sec_nume_docu.count
              DELETE FROM inc_solic_concu_recom_temp
               WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);*/
          
          END IF;
        
          FORALL i IN 1 .. v_cab_sec_nume_docu.count
            DELETE FROM ped_solic_posic a
             WHERE a.soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
        
          FORALL i IN 1 .. v_cab_sec_nume_docu.count
            DELETE FROM ped_solic_cabec a
             WHERE a.oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
        
          FORALL i IN 1 .. v_cab_sec_nume_docu.count
            UPDATE sto_docum_digit d
               SET d.ind_envi           = '0',
                   d.ind_rech           = '0',
                   d.cod_ulti_vali_ejec = NULL,
                   d.usu_modi           = pscodigousuario,
                   d.fec_modi           = SYSDATE
             WHERE d.num_lote = v_cab_num_lote(i)
               AND d.sec_nume_docu = v_cab_sec_nume_docu(i)
               AND d.cod_tipo_docu = pscodigotipodocumento
               AND d.cod_pais = pscodigopais;
        
          FORALL i IN 1 .. v_cab_sec_nume_docu.count
            UPDATE sto_docum_digit d
               SET d.ind_envi           = '0',
                   d.ind_rech           = '0',
                   d.cod_ulti_vali_ejec = NULL,
                   d.usu_modi           = pscodigousuario,
                   d.fec_modi           = SYSDATE
             WHERE d.num_lote = v_cab_num_lote(i)
               AND d.sec_nume_docu_cabe = v_cab_sec_nume_docu(i)
               AND d.cod_tipo_docu = lscodigodocumentodetalle
               AND d.cod_pais = pscodigopais;
        
          FORALL i IN 1 .. v_cab_sec_nume_docu.count
            UPDATE int_solic_conso_cabec cab
               SET cab.ind_ocs_proc = '0'
             WHERE cab.cod_pais = pscodigopais
               AND cab.cod_peri = v_cab_cod_peri(i)
               AND cab.cod_clie = v_cab_cod_clie(i)
               AND cab.num_lote = v_cab_num_lote(i);
        
          FORALL i IN 1 .. v_cab_sec_nume_docu.count
            DELETE inc_premi_elegi p
             WHERE p.clie_oid_clie = v_cab_clie_oid_clie(i)
               AND EXISTS
             (SELECT 1
                      FROM int_solic_conso_detal det
                     WHERE det.cod_clie = v_cab_cod_clie(i)
                       AND det.cod_peri = v_cab_cod_peri(i)
                       AND det.num_lote = v_cab_num_lote(i)
                       AND det.cod_pais = pscodigopais
                       AND p.num_prem = det.num_prem
                       AND p.
                     copa_oid_para_gral = det.copa_oid_para_gral
                       AND p. panp_oid_para_nive_prem =
                           det.panp_oid_para_nive_prem);
        
        END IF;
        EXIT WHEN curcabecera%NOTFOUND;
      END LOOP;
      CLOSE curcabecera;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_REVER_PEDGP: ' || ls_sqlerrm);
  END sto_pr_rever_pedgp;

  /***************************************************************************
  Descripcion       : Revierte los Pedidos que se encuentarn en GP1, GP3 que
                      que tienen error en STO
  Fecha Creacion    : 04/03/2014
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_rever_pedgp_error
  (
    pnoidperiodo NUMBER,
    psfechafact  VARCHAR2
  ) IS
  
    CURSOR curcabecera IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.clie_oid_clie,
             cons.soca_oid_soli_cabe_refe
        FROM int_solic_conso_cabec cons,
             ped_solic_cabec       p
       WHERE p.oid_soli_cabe = cons.soca_oid_soli_cabe_refe
         AND p.grpr_oid_grup_proc <= 3
         AND cons.ind_ocs_proc = '0';
  
    TYPE t_cab_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cab_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_cab_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_cab_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_cab_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_soca_oid_soli_cabe_refe IS TABLE OF int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE;
  
    v_cab_cod_peri            t_cab_cod_peri;
    v_cab_cod_clie            t_cab_cod_clie;
    v_cab_num_lote            t_cab_num_lote;
    v_cab_sec_nume_docu       t_cab_sec_nume_docu;
    v_cab_clie_oid_clie       t_cab_clie_oid_clie;
    v_soca_oid_soli_cabe_refe t_soca_oid_soli_cabe_refe;
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    OPEN curcabecera;
    LOOP
      FETCH curcabecera BULK COLLECT
        INTO v_cab_cod_peri,
             v_cab_cod_clie,
             v_cab_num_lote,
             v_cab_sec_nume_docu,
             v_cab_clie_oid_clie,
             v_soca_oid_soli_cabe_refe LIMIT rows;
      IF v_cab_sec_nume_docu.count > 0 THEN
      
        FOR i IN v_cab_sec_nume_docu.first .. v_cab_sec_nume_docu.last
        LOOP
          --ELIMINAMOS INFORMACION DE NUEVO MAV
          sto_pr_elimi_mav_envio(v_soca_oid_soli_cabe_refe(i));
        END LOOP;
      
        FORALL i IN 1 .. v_cab_sec_nume_docu.count
          DELETE FROM msg_buzon_mensa
           WHERE clie_oid_clie = v_cab_clie_oid_clie(i)
             AND ind_acti = 1
             AND EXISTS (SELECT oid_mens
                    FROM msg_mensa
                   WHERE cod_mens LIKE 'MAV%'
                     AND mens_oid_mens = oid_mens);
      
        --  Mensajes de PED
      
        FORALL i IN 1 .. v_cab_sec_nume_docu.count
          DELETE FROM ped_solic_mensa
           WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
      
        FORALL i IN 1 .. v_cab_sec_nume_docu.count
          DELETE FROM ped_solic_posic a
           WHERE a.soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
      
        FORALL i IN 1 .. v_cab_sec_nume_docu.count
          DELETE FROM ped_solic_cabec a
           WHERE a.oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
      
        FORALL i IN 1 .. v_cab_sec_nume_docu.count
          DELETE inc_premi_elegi p
           WHERE p.clie_oid_clie = v_cab_clie_oid_clie(i)
             AND EXISTS
           (SELECT 1
                    FROM int_solic_conso_detal det
                   WHERE det.cod_clie = v_cab_cod_clie(i)
                     AND det.cod_peri = v_cab_cod_peri(i)
                     AND det.num_lote = v_cab_num_lote(i)
                     AND p.num_prem = det.num_prem
                     AND p.copa_oid_para_gral = det.copa_oid_para_gral
                     AND p.panp_oid_para_nive_prem =
                         det.panp_oid_para_nive_prem);
      
      END IF;
      EXIT WHEN curcabecera%NOTFOUND;
    END LOOP;
    CLOSE curcabecera;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_REVER_PEDGP_ERROR: ' ||
                              ls_sqlerrm);
  END sto_pr_rever_pedgp_error;
  /***************************************************************************
     Descripcion       : Elimina pedidos Orden de Compra STO
     Fecha Creacion    : 27/02/2013
     Autor             :  Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_elimi_pedi2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumerolote          VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psusuario             VARCHAR2
  ) IS
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    lnoidpais                seg_pais.oid_pais%TYPE;
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
    lngp NUMBER;
  
    CURSOR curoidenvi(vnoidsolicabe NUMBER) IS
      SELECT menv_oid_envi
        FROM mav_solic_envio
       WHERE soca_oid_soli_cabe = vnoidsolicabe;
  
    TYPE t_menv_oid_envi IS TABLE OF mav_solic_envio.menv_oid_envi%TYPE;
    v_menv_oid_envi t_menv_oid_envi;
  
    CURSOR c_int_solic_conso_cabec IS
      SELECT c.*
        FROM sto_docum_digit       s,
             int_solic_conso_cabec c
       WHERE s.cod_tipo_docu = pscodigotipodocumento
         AND s.sec_nume_docu = c.sec_nume_docu
         AND s.num_lote = c.num_lote
         AND s.sec_nume_docu = pssecnumedocu
         AND s.num_lote = psnumerolote;
  
    TYPE t_tab_int_solic_conso_cabec IS TABLE OF int_solic_conso_cabec %ROWTYPE INDEX BY BINARY_INTEGER;
    tab_int_solic_conso_cabec t_tab_int_solic_conso_cabec;
  
    CURSOR c_sto_docum_digit
    (
      vnsecnumedocu            NUMBER,
      vsnumlote                VARCHAR2,
      vscodigodocumentodetalle VARCHAR2
    ) IS
      SELECT *
        FROM sto_docum_digit
       WHERE sec_nume_docu = vnsecnumedocu
         AND num_lote = vsnumlote
         AND cod_tipo_docu = pscodigotipodocumento
      UNION ALL
      SELECT *
        FROM sto_docum_digit dd
       WHERE dd.cod_tipo_docu = vscodigodocumentodetalle
         AND dd.num_lote = vsnumlote
         AND dd.sec_nume_docu_cabe = vnsecnumedocu;
  
    TYPE t_tab_sto_docum_digit IS TABLE OF sto_docum_digit %ROWTYPE INDEX BY BINARY_INTEGER;
    tab_sto_docum_digit t_tab_sto_docum_digit;
  
  BEGIN
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocumento);
    lnoidpais                := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
  
    SELECT val_para
      INTO lngp
      FROM bas_param_pais
     WHERE cod_pais = pscodigopais
       AND nom_para = 'nGPConsPedidos'
       AND cod_sist = 'OCR';
  
    OPEN c_int_solic_conso_cabec;
    LOOP
      FETCH c_int_solic_conso_cabec BULK COLLECT
        INTO tab_int_solic_conso_cabec LIMIT rows;
      EXIT WHEN tab_int_solic_conso_cabec.count = 0;
      FOR i IN tab_int_solic_conso_cabec.first .. tab_int_solic_conso_cabec.last
      LOOP
        tab_int_solic_conso_cabec(i).ind_erro_rech := '1';
        tab_int_solic_conso_cabec(i).ind_ocs_proc := '0';
        tab_int_solic_conso_cabec(i).usu_modi := psusuario;
        tab_int_solic_conso_cabec(i).fec_modi := SYSDATE;
      
        IF tab_int_solic_conso_cabec(i).ind_rece_onli = '0' THEN
          INSERT INTO ped_histo_solic_conso_cabec
          VALUES tab_int_solic_conso_cabec
            (i);
        END IF;
      
        OPEN c_sto_docum_digit(tab_int_solic_conso_cabec(i).sec_nume_docu,
                               tab_int_solic_conso_cabec(i).num_lote,
                               lscodigodocumentodetalle);
        LOOP
          FETCH c_sto_docum_digit BULK COLLECT
            INTO tab_sto_docum_digit LIMIT rows;
          EXIT WHEN tab_sto_docum_digit.count = 0;
          FOR j IN tab_sto_docum_digit.first .. tab_sto_docum_digit.last
          LOOP
            IF tab_int_solic_conso_cabec(i).ind_rece_onli = '0' THEN
              tab_sto_docum_digit(j).ind_rech := '1';
              tab_sto_docum_digit(j).ind_envi := '0';
              tab_sto_docum_digit(j).usu_modi := psusuario;
              tab_sto_docum_digit(j).fec_modi := SYSDATE;
            
              INSERT INTO sto_histo_docum_digit
              VALUES tab_sto_docum_digit
                (j);
            
              INSERT INTO sto_histo_detal_docum_excep
                SELECT *
                  FROM sto_detal_docum_excep
                 WHERE sec_nume_docu = tab_sto_docum_digit(j).sec_nume_docu
                   AND num_lote = tab_sto_docum_digit(j).num_lote;
            
              INSERT INTO sto_histo_audit_excep
                SELECT *
                  FROM sto_audit_excep
                 WHERE sec_nume_docu = tab_sto_docum_digit(j).sec_nume_docu
                   AND num_lote = tab_sto_docum_digit(j).num_lote;
            
            END IF;
          
            IF tab_sto_docum_digit(j)
             .cod_tipo_docu = lscodigodocumentodetalle THEN
              IF tab_int_solic_conso_cabec(i).ind_rece_onli = '0' THEN
                INSERT INTO ped_histo_solic_conso_detal
                  SELECT *
                    FROM int_solic_conso_detal
                   WHERE sec_nume_docu = tab_sto_docum_digit(j)
                        .sec_nume_docu
                     AND num_lote = tab_sto_docum_digit(j).num_lote;
              END IF;
              DELETE inc_premi_elegi p
               WHERE p.clie_oid_clie = tab_int_solic_conso_cabec(i)
                    .clie_oid_clie
                 AND EXISTS
               (SELECT 1
                        FROM int_solic_conso_detal det
                       WHERE sec_nume_docu = tab_sto_docum_digit(j)
                            .sec_nume_docu
                         AND num_lote = tab_sto_docum_digit(j).num_lote
                         AND p.num_prem = det.num_prem
                         AND p.
                       copa_oid_para_gral = det.copa_oid_para_gral
                         AND p. panp_oid_para_nive_prem =
                             det.panp_oid_para_nive_prem);
              DELETE int_solic_conso_detal
               WHERE sec_nume_docu = tab_sto_docum_digit(j).sec_nume_docu
                 AND num_lote = tab_sto_docum_digit(j).num_lote;
            
            END IF;
          
            DELETE sto_detal_docum_excep
             WHERE sec_nume_docu = tab_sto_docum_digit(j).sec_nume_docu
               AND num_lote = tab_sto_docum_digit(j).num_lote
               AND cod_tipo_docu = tab_sto_docum_digit(j).cod_tipo_docu
               AND cod_pais = tab_sto_docum_digit(j).cod_pais;
          
            DELETE sto_audit_excep
             WHERE sec_nume_docu = tab_sto_docum_digit(j).sec_nume_docu
               AND num_lote = tab_sto_docum_digit(j).num_lote
               AND cod_tipo_docu = tab_sto_docum_digit(j).cod_tipo_docu
               AND cod_pais = tab_sto_docum_digit(j).cod_pais;
          
            DELETE sto_docum_digit
             WHERE sec_nume_docu = tab_sto_docum_digit(j).sec_nume_docu
               AND num_lote = tab_sto_docum_digit(j).num_lote
               AND cod_tipo_docu = tab_sto_docum_digit(j).cod_tipo_docu
               AND cod_pais = tab_sto_docum_digit(j).cod_pais;
          
          END LOOP;
        
        END LOOP;
        CLOSE c_sto_docum_digit;
      
        IF lngp > 0 THEN
        
          OPEN curoidenvi(tab_int_solic_conso_cabec(i)
                          .soca_oid_soli_cabe_refe);
          LOOP
            FETCH curoidenvi BULK COLLECT
              INTO v_menv_oid_envi LIMIT rows;
            IF v_menv_oid_envi.count > 0 THEN
            
              FORALL j IN 1 .. v_menv_oid_envi.count
                DELETE FROM mav_solic_envio
                 WHERE menv_oid_envi = v_menv_oid_envi(j);
            
              FORALL j IN 1 .. v_menv_oid_envi.count
                DELETE FROM mav_envio
                 WHERE oid_envi = v_menv_oid_envi(j)
                   AND EXISTS
                 (SELECT 1
                          FROM mav_detal_mav
                         WHERE oid_deta_mav = denv_oid_deta_mav
                           AND tepr_oid_tipo_esta_proc = 3);
            
              FORALL j IN 1 .. v_menv_oid_envi.count
                UPDATE mav_envio
                   SET ind_envi = 'P'
                 WHERE oid_envi = v_menv_oid_envi(j)
                   AND EXISTS
                 (SELECT 1
                          FROM mav_detal_mav
                         WHERE oid_deta_mav = denv_oid_deta_mav
                           AND tepr_oid_tipo_esta_proc = 4);
            
            END IF;
            EXIT WHEN curoidenvi%NOTFOUND;
          END LOOP;
          CLOSE curoidenvi;
        
          --ELIMINAMOS INFORMACION DE NUEVO MAV
          sto_pr_elimi_mav_envio(tab_int_solic_conso_cabec(i)
                                 .soca_oid_soli_cabe_refe);
        
          DELETE FROM msg_buzon_mensa
           WHERE clie_oid_clie = tab_int_solic_conso_cabec(i).clie_oid_clie
             AND ind_acti = 1
             AND EXISTS (SELECT oid_mens
                    FROM msg_mensa
                   WHERE cod_mens LIKE 'MAV%'
                     AND mens_oid_mens = oid_mens);
        
          DELETE FROM ped_solic_mensa
           WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i)
                .soca_oid_soli_cabe_refe;
        
          /*DELETE FROM inc_solic_concu_punta
           WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i).soca_oid_soli_cabe_refe;
          
          DELETE FROM inc_solic_concu_punta_temp
           WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i).soca_oid_soli_cabe_refe;
          
          DELETE FROM inc_pedid_concu_recom
           WHERE clr3_oid_clie_rete IN
                 (SELECT c.oid_clie_rete
                    FROM inc_clien_recte       c,
                         inc_concu_param_gener a,
                         inc_concu_param_consu b
                   WHERE c.copa_oid_para_gral = a.oid_para_gral
                     AND a.oid_para_gral = b.copa_oid_para_gral
                     AND a.bcal_oid_base_calc = 4 -- dato fijo
                     AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_desd) <= tab_int_solic_conso_cabec(i)
                        .cod_peri -- periodo actual
                     AND tab_int_solic_conso_cabec(i)
                        .cod_peri <= gen_fn_calcu_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_hast),
                                                        b.val_peri_eval - 1) -- periodo actual
                     AND c.clie_oid_clie = tab_int_solic_conso_cabec(i).clie_oid_clie -- oid cliente
                  )
             AND perd_oid_peri = tab_int_solic_conso_cabec(i).perd_oid_peri
             AND clre_oid_clie_redo IS NULL;
          
          DELETE FROM inc_pedid_concu_recom
           WHERE clre_oid_clie_redo IN
                 (SELECT oid_clie_redo
                    FROM inc_clien_recdo       d,
                         inc_clien_recte       e,
                         inc_concu_param_gener a,
                         inc_concu_param_consu b
                   WHERE d.clr3_oid_clie_rete = e.oid_clie_rete
                     AND d.clie_oid_clie = tab_int_solic_conso_cabec(i).clie_oid_clie -- oid del cliente
                     AND d.perd_oid_peri = tab_int_solic_conso_cabec(i).perd_oid_peri -- periodo actual
                     AND e.copa_oid_para_gral = a.oid_para_gral
                     AND a.oid_para_gral = b.copa_oid_para_gral
                     AND a.bcal_oid_base_calc = 4 -- 4 dato fijo
                     AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_desd) <= tab_int_solic_conso_cabec(i)
                        .cod_peri -- periodo actual
                     AND tab_int_solic_conso_cabec(i)
                        .cod_peri <= gen_fn_calcu_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_hast),
                                                        b.val_peri_eval - 1)
          
                  )
             AND perd_oid_peri = tab_int_solic_conso_cabec(i).perd_oid_peri;
          
          DELETE FROM inc_pedid_concu_recom_temp
           WHERE perd_oid_peri = tab_int_solic_conso_cabec(i).perd_oid_peri
             AND clie_oid_clie_rete = tab_int_solic_conso_cabec(i).clie_oid_clie;
          
          DELETE FROM inc_solic_concu_recom
           WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i).soca_oid_soli_cabe_refe;
          
          DELETE FROM inc_solic_concu_recom_temp
           WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i).soca_oid_soli_cabe_refe;*/
        
        END IF;
      
        -- Borro de la tabla CAR_SOLI_ENTR_BLOQ
        DELETE car_soli_entr_bloq
         WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i)
              .soca_oid_soli_cabe_refe
           AND pais_oid_pais = lnoidpais;
      
        DELETE ped_segui_pedid
         WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i)
              .soca_oid_soli_cabe_refe;
      
        -- Borro de la tabla PED_SOLIC_POSIC
        DELETE ped_solic_posic
         WHERE soca_oid_soli_cabe = tab_int_solic_conso_cabec(i)
              .soca_oid_soli_cabe_refe;
      
        -- Borro de la tabla PED_SOLIC_CABEC
        DELETE ped_solic_cabec
         WHERE oid_soli_cabe = tab_int_solic_conso_cabec(i)
              .soca_oid_soli_cabe_refe
           AND pais_oid_pais = lnoidpais;
      
        DELETE int_solic_conso_cabec
         WHERE sec_nume_docu = tab_int_solic_conso_cabec(i).sec_nume_docu
           AND num_lote = tab_int_solic_conso_cabec(i).num_lote;
      
      END LOOP;
    END LOOP;
    CLOSE c_int_solic_conso_cabec;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_ELIMI_PEDI2: ' || ls_sqlerrm);
    
  END sto_pr_elimi_pedi2;

  /***************************************************************************
     Descripcion       : Elimina pedidos Orden de Compra STO
     Fecha Creacion    : 09/03/2011
     Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_bloqu_elimi_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pssecuencia           VARCHAR2,
    psnumeroproceso       VARCHAR2,
    psusuario             VARCHAR2
  ) IS
    CURSOR cureliminar IS
      SELECT t.num_lote,
             t.sec_nume_docu
        FROM sto_tmp_elimi_docum t,
             sto_docum_digit     s
       WHERE s.cod_tipo_docu = t.cod_tipo_docu
         AND s.num_lote = t.num_lote
         AND s.sec_nume_docu = t.sec_nume_docu
         AND t.num_secu = pssecuencia
         AND t.num_proc = psnumeroproceso
         AND NOT EXISTS
       (SELECT 1
                FROM sto_histo_proce h
               WHERE h.proc_num_proc != psnumeroproceso
                 AND h.proc_num_proc = s.num_proc
                 AND h.fec_fpro IS NULL
                 AND pscodigotipodocumento = h.docu_cod_tipo_docu);
  
    TYPE t_num_lote IS TABLE OF sto_tmp_elimi_docum.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_tmp_elimi_docum.sec_nume_docu%TYPE;
  
    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocumento);
    OPEN cureliminar;
    LOOP
      FETCH cureliminar BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT rows;
      IF v_num_lote.count > 0 THEN
      
        FORALL i IN 1 .. v_num_lote.count
          UPDATE sto_docum_digit d1
             SET d1.num_proc = psnumeroproceso,
                 d1.fec_modi = SYSDATE,
                 d1.usu_modi = psusuario
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_num_lote(i)
             AND d1.sec_nume_docu = v_sec_nume_docu(i)
             AND d1.cod_tipo_docu = pscodigotipodocumento;
      
        FORALL i IN 1 .. v_num_lote.count
          UPDATE sto_docum_digit d1
             SET d1.num_proc = psnumeroproceso,
                 d1.fec_modi = SYSDATE,
                 d1.usu_modi = psusuario
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_num_lote(i)
             AND d1.sec_nume_docu_cabe = v_sec_nume_docu(i)
             AND d1.cod_tipo_docu = lscodigodocumentodetalle;
      
      END IF;
      EXIT WHEN cureliminar%NOTFOUND;
    END LOOP;
    CLOSE cureliminar;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_BLOQU_ELIMI_DOCUM: ' ||
                              ls_sqlerrm);
    
  END sto_pr_bloqu_elimi_docum;

  /***************************************************************************
   Descripcion       : Elimina CDRs STO
   Fecha Creacion    : 27/10/2009
   Autor             : Dennys Oliva Iriarte
  ***************************************************************************/

  PROCEDURE sto_pr_elimi_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumerolote          VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psnumproceso          VARCHAR2,
    psusuario             VARCHAR2
  ) IS
    CURSOR cureliminar IS
      SELECT r.oid_cabe_recl,
             ro.oid_oper_recl,
             p.oid_soli_cabe
        FROM int_solic_conso_poven_cabec i,
             rec_cabec_recla             r,
             rec_opera_recla             ro,
             rec_solic_opera             rso,
             ped_solic_cabec             p
       WHERE sec_nume_docu = pssecnumedocu
         AND i.cod_pais = pscodigopais
         AND i.num_lote = psnumerolote
         AND i.oid_cabe_recl_refe = r.oid_cabe_recl
         AND r.oid_cabe_recl = ro.care_oid_cabe_recl
         AND ro.oid_oper_recl = rso.opre_oid_oper_recl
         AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
         AND r. esre_oid_esta_recl <> 4
         AND (0 = (SELECT COUNT(1)
                     FROM int_solic_conso_poven_cabec i,
                          rec_cabec_recla             r,
                          rec_opera_recla             ro,
                          rec_solic_opera             rso,
                          ped_solic_cabec             p
                    WHERE sec_nume_docu = pssecnumedocu
                      AND i.oid_cabe_recl_refe = r.oid_cabe_recl
                      AND r.oid_cabe_recl = ro.care_oid_cabe_recl
                      AND ro.oid_oper_recl = rso.opre_oid_oper_recl
                      AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
                      AND r. esre_oid_esta_recl <> 4
                      AND p. grpr_oid_grup_proc >= 5));
  
    CURSOR cureliminarnew IS
      SELECT r.oid_cabe_recl,
             ro.oid_oper_recl,
             p.oid_soli_cabe,
             p.grpr_oid_grup_proc,
             ts.ind_soli_nega
        FROM int_solic_conso_poven_cabec i,
             rec_cabec_recla             r,
             rec_opera_recla             ro,
             rec_solic_opera             rso,
             ped_solic_cabec             p,
             ped_tipo_solic_pais         tsp,
             ped_tipo_solic              ts
       WHERE sec_nume_docu = pssecnumedocu
         AND i.cod_pais = pscodigopais
         AND i.num_lote = psnumerolote
         AND i.oid_cabe_recl_refe = r.oid_cabe_recl
         AND r.oid_cabe_recl = ro.care_oid_cabe_recl
         AND ro.oid_oper_recl = rso.opre_oid_oper_recl
         AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
         AND p.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli;
  
    CURSOR cureliminarval IS
      SELECT r.oid_cabe_recl,
             ro.oid_oper_recl
        FROM int_solic_conso_poven_cabec i,
             rec_cabec_recla             r,
             rec_opera_recla             ro,
             rec_linea_opera_recla       rlo
       WHERE sec_nume_docu = pssecnumedocu
         AND i.cod_pais = pscodigopais
         AND i.num_lote = psnumerolote
         AND i.oid_cabe_recl_refe = r.oid_cabe_recl
         AND r.oid_cabe_recl = ro.care_oid_cabe_recl
         AND ro.oid_oper_recl = rlo.opre_oid_oper_recl(+)
         AND rlo.opre_oid_oper_recl IS NULL
       GROUP BY r.oid_cabe_recl,
                ro.oid_oper_recl;
  
    CURSOR cureliminarval2 IS
      SELECT r.oid_cabe_recl
        FROM int_solic_conso_poven_cabec i,
             rec_cabec_recla             r,
             rec_opera_recla             ro
       WHERE sec_nume_docu = pssecnumedocu
         AND i.cod_pais = pscodigopais
         AND i.num_lote = psnumerolote
         AND i.oid_cabe_recl_refe = r.oid_cabe_recl
         AND r.oid_cabe_recl = ro.care_oid_cabe_recl(+)
         AND ro.care_oid_cabe_recl IS NULL
       GROUP BY r.oid_cabe_recl;
  
    TYPE t_oid_cabe_recl IS TABLE OF rec_cabec_recla.oid_cabe_recl %TYPE;
    TYPE t_oid_oper_recl IS TABLE OF rec_opera_recla.oid_oper_recl %TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe %TYPE;
    TYPE t_grpr_oid_grup_proc IS TABLE OF ped_solic_cabec.grpr_oid_grup_proc %TYPE;
    TYPE t_ind_soli_nega IS TABLE OF ped_tipo_solic.ind_soli_nega %TYPE;
  
    v_oid_cabe_recl      t_oid_cabe_recl;
    v_oid_oper_recl      t_oid_oper_recl;
    v_oid_soli_cabe      t_oid_soli_cabe;
    v_grpr_oid_grup_proc t_grpr_oid_grup_proc;
    v_ind_soli_nega      t_ind_soli_nega;
  
    lsparametropunta sto_param_gener_occrr.val_param%TYPE;
    lsparametroconso sto_param_gener_occrr.val_param%TYPE;
    lsparametrocarel sto_param_gener_occrr.val_param%TYPE;
    lsoidaudielim    rec_audit_elimi_cdr.oid_audi_elim%TYPE;
    lsnum_docu       rec_audit_elimi_cdr.num_docu%TYPE;
    lscod_clie       rec_audit_elimi_cdr.cod_clie%TYPE;
    lsoid_cabe_recl  rec_audit_elimi_cdr.oid_cabe_recl%TYPE;
    lsoid_clie       rec_cabec_recla.clie_oid_clie%TYPE;
  
    lsparametrofecha VARCHAR2(10);
  
    rows     NATURAL := 1000; -- Number of rows to process at a time
    i        BINARY_INTEGER := 0;
    contador NUMBER := 0;
  
    lsverifica     NUMBER := 0;
    lsnrosolgenbr  NUMBER := 0;
    lsnrosolgen    NUMBER := 0;
    lsnrosolfac    NUMBER := 0;
    lsnrosolgenabo NUMBER := 0;
    lsnrosolfacabo NUMBER := 0;
    lsnrosolgenenv NUMBER := 0;
    lsnrosolfacenv NUMBER := 0;
    lsnuevasolicit NUMBER := 0;
    lsabonopendBR  NUMBER := 0;
  
    lsmensaje VARCHAR2(4000);
  BEGIN
  
    -- Obtiene la secuencia
    SELECT rec_auel_seq.nextval INTO lsoidaudielim FROM dual;
  
    ---- Inserta auditoria
    INSERT INTO rec_audit_elimi_cdr
      (oid_audi_elim,
       num_lote,
       sec_nume_docu,
       num_proc,
       num_docu,
       cod_clie,
       oid_cabe_recl,
       oid_soli_cabe,
       oid_soli_cabe_carg,
       des_erro,
       fec_ingr,
       usu_ingr)
    VALUES
      (lsoidaudielim, -----   rec_auel_seq.nextval,
       psnumerolote,
       pssecnumedocu,
       psnumproceso,
       NULL /*num_docu*/,
       NULL /*COD_CLIE*/,
       NULL /*OID_CABE_RECL*/,  
       NULL /*OID_SOLI_CABE*/,
       NULL /*OID_SOLI_CABE_CARG*/,
       lsmensaje,
       SYSDATE,
       psusuario);

  
    ---lsparametroconso := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CONSOL');
    lsparametroconso := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_TIPO_CALC_FACT'),'1');
    lsparametropunta := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_DESPUNT');
    lsparametrocarel := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CAR_ELI_CDR'),1) ;
  
    SELECT to_char(SYSDATE, 'dd/mm/yyyy') INTO lsparametrofecha FROM dual;
  
    SELECT i.num_docu,
           i.cod_clie,
           i.oid_cabe_recl_refe,
           i.oid_clie
      INTO lsnum_docu,
           lscod_clie,
           lsoid_cabe_recl,
           lsoid_clie --- Datos para logear
      FROM int_solic_conso_poven_cabec i
     WHERE i.sec_nume_docu = pssecnumedocu;
  
    SELECT COUNT(1)
      INTO lsnrosolgenbr --- Numero de solicitudes que generaron Boleta de Recojo
      FROM int_solic_conso_poven_cabec i,
           rec_cabec_recla             r,
           rec_opera_recla             ro,
           int_rec_linea_borec         lb
     WHERE i.sec_nume_docu = pssecnumedocu
       AND i.oid_cabe_recl_refe = r.oid_cabe_recl
       AND r.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = lb.opre_oid_oper_recl;
  
    SELECT COUNT(1)
      INTO lsnrosolfac --- Numero de solicitudes que llegarona GP5
      FROM int_solic_conso_poven_cabec i,
           rec_cabec_recla             r,
           rec_opera_recla             ro,
           rec_solic_opera             rso,
           ped_solic_cabec             p
     WHERE sec_nume_docu = pssecnumedocu
       AND i.oid_cabe_recl_refe = r.oid_cabe_recl
       AND r.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rso.opre_oid_oper_recl
       AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
       AND p.grpr_oid_grup_proc >= 5;
  
    SELECT COUNT(1)
      INTO lsnrosolgen --- Numero de solicitudes generadas
      FROM int_solic_conso_poven_cabec i,
           rec_cabec_recla             r,
           rec_opera_recla             ro,
           rec_solic_opera             rso,
           ped_solic_cabec             p
     WHERE sec_nume_docu = pssecnumedocu
       AND i.oid_cabe_recl_refe = r.oid_cabe_recl
       AND r.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rso.opre_oid_oper_recl
       AND rso.soca_oid_soli_cabe = p.oid_soli_cabe;
  
    SELECT COUNT(1)
      INTO lsnrosolfacabo --- Numero de solicitudes que llegarona GP5 de REC Retorno
      FROM int_solic_conso_poven_cabec i,
           rec_cabec_recla             r,
           rec_opera_recla             ro,
           rec_solic_opera             rso,
           ped_solic_cabec             p,
           ped_tipo_solic_pais         tsp,
           ped_tipo_solic              ts
     WHERE sec_nume_docu = pssecnumedocu
       AND i.oid_cabe_recl_refe = r.oid_cabe_recl
       AND r.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rso.opre_oid_oper_recl
       AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
       AND p.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND ts.ind_soli_nega = 1
       AND p. grpr_oid_grup_proc >= 5;
  
    SELECT COUNT(1)
      INTO lsnrosolgenabo --- Numero de solicitudes generadas para abono REC RETRNO
      FROM int_solic_conso_poven_cabec i,
           rec_cabec_recla             r,
           rec_opera_recla             ro,
           rec_solic_opera             rso,
           ped_solic_cabec             p,
           ped_tipo_solic_pais         tsp,
           ped_tipo_solic              ts
     WHERE sec_nume_docu = pssecnumedocu
       AND i.oid_cabe_recl_refe = r.oid_cabe_recl
       AND r.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rso.opre_oid_oper_recl
       AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
       AND p.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND ts.ind_soli_nega = 1;
  
    SELECT COUNT(1)
      INTO lsnrosolfacenv --- Numero de solicitudes que llegarona GP5 de REC Atencion
      FROM int_solic_conso_poven_cabec i,
           rec_cabec_recla             r,
           rec_opera_recla             ro,
           rec_solic_opera             rso,
           ped_solic_cabec             p,
           ped_tipo_solic_pais         tsp,
           ped_tipo_solic              ts
     WHERE sec_nume_docu = pssecnumedocu
       AND i.oid_cabe_recl_refe = r.oid_cabe_recl
       AND r.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rso.opre_oid_oper_recl
       AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
       AND p.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND ts.ind_soli_nega <> 1
       AND p. grpr_oid_grup_proc >= 5;
  
    SELECT COUNT(1)
      INTO lsnrosolgenenv --- Numero de solicitudes generadas para abono REC Atencion
      FROM int_solic_conso_poven_cabec i,
           rec_cabec_recla             r,
           rec_opera_recla             ro,
           rec_solic_opera             rso,
           ped_solic_cabec             p,
           ped_tipo_solic_pais         tsp,
           ped_tipo_solic              ts
     WHERE sec_nume_docu = pssecnumedocu
       AND i.oid_cabe_recl_refe = r.oid_cabe_recl
       AND r.oid_cabe_recl = ro.care_oid_cabe_recl
       AND ro.oid_oper_recl = rso.opre_oid_oper_recl
       AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
       AND p.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND ts.ind_soli_nega <> 1;
  
    SELECT COUNT(1)
      INTO lsabonopendBR --- Verifica si hay abono pendiente y con BR generada
      FROM int_solic_conso_poven_cabec isc,
             rec_solic_opera a,
             rec_linea_opera_recla b,
             rec_opera_recla c,
             rec_tipos_opera d,
             rec_opera e,
             rec_cabec_recla f,
             int_rec_linea_borec lb,
             (SELECT tsp.oid_tipo_soli_pais,
                     ts.cod_tipo_soli,
                     i.val_i18n
                FROM ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts,
                     v_gen_i18n_sicc     i
               WHERE i.idio_oid_idio = 1
                 AND i.attr_enti = 'PED_TIPO_SOLIC'
                 AND i.val_oid = ts.oid_tipo_soli
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND upper(val_i18n) LIKE '%REC RETORNO%') tsol
       WHERE a.opre_oid_oper_recl = b.opre_oid_oper_recl
         AND b.opre_oid_oper_recl = c.oid_oper_recl
         AND c.tiop_oid_tipo_oper = d.oid_tipo_oper
         AND d.rope_oid_oper = e.oid_oper
         AND c.care_oid_cabe_recl = f.oid_cabe_recl
         AND e.ind_proc_line = 0 --- procesa en linea BR Peru
         AND b.timo_oid_tipo_movi = 2
         AND a.tspa_oid_tipo_soli_pais = tsol.oid_tipo_soli_pais
         AND c.care_oid_cabe_recl = isc.oid_cabe_recl_refe      
         and lb.CARE_OID_CABE_RECL = isc.oid_cabe_recl_refe    
         AND isc.sec_nume_docu = pssecnumedocu;
  
    lsverifica := 1;
  
    lsmensaje := 'CDR no se puede eliminar';

     ---- Si tiene abono pendiente y tiene BR  generada no se puede borrar
     IF lsabonopendBR > 0 THEN


        lsmensaje := 'CDR no se puede eliminar tiene BR generada';

        ---- Inserta auditoria
        UPDATE rec_audit_elimi_cdr
           SET des_erro           = lsmensaje,
               num_docu           = lsnum_docu,
               cod_clie           = lscod_clie,
               oid_cabe_recl      = lsoid_cabe_recl,
               oid_soli_cabe_carg = lsnuevasolicit
         WHERE oid_audi_elim = lsoidaudielim;
      ELSE  
        IF lsnrosolfac = 0 THEN
          -- Si no se facturo ninguna solicitud se borra todo
        
          OPEN cureliminar;
          LOOP
            FETCH cureliminar BULK COLLECT
              INTO v_oid_cabe_recl,
                   v_oid_oper_recl,
                   v_oid_soli_cabe LIMIT rows;
            IF v_oid_cabe_recl.count > 0 THEN
              FORALL i IN 1 .. v_oid_cabe_recl.count
                DELETE FROM rec_linea_opera_recla
                 WHERE opre_oid_oper_recl = v_oid_oper_recl(i);
            
              FORALL i IN 1 .. v_oid_cabe_recl.count
                DELETE FROM ped_solic_posic
                 WHERE soca_oid_soli_cabe = v_oid_soli_cabe(i);
            
              FORALL i IN 1 .. v_oid_cabe_recl.count
                DELETE FROM rec_solic_opera
                 WHERE opre_oid_oper_recl = v_oid_oper_recl(i);
            
              FORALL i IN 1 .. v_oid_cabe_recl.count
                DELETE FROM ped_solic_cabec
                 WHERE oid_soli_cabe = v_oid_soli_cabe(i);
            
              FORALL i IN 1 .. v_oid_cabe_recl.count
                DELETE FROM rec_opera_recla
                 WHERE care_oid_cabe_recl = v_oid_cabe_recl(i);
            
              FORALL i IN 1 .. v_oid_cabe_recl.count
                DELETE FROM rec_cabec_recla
                 WHERE oid_cabe_recl = v_oid_cabe_recl(i);
            
              lsmensaje := 'CDR eliminado correctamente';
            ELSE
              lsmensaje := 'CDR ya fue procesado Total o parcialmente';
            END IF;
            EXIT WHEN cureliminar%NOTFOUND;
          END LOOP;
          CLOSE cureliminar;
          lsmensaje := 'CDR eliminado correctamente';
        
        ELSE
          contador := 0;
          IF lsnrosolfacenv <> 0 THEN
            --- si se procesaron solicitudes de Atencion
            contador   := 1;
            lsverifica := 0;
            lsmensaje  := 'CDR no se puede aliminar tiene Atenciones procesadas';
          ELSE
            IF lsnrosolgenbr > 0 THEN
              contador   := 1;
              lsverifica := 0;
              lsmensaje  := 'CDR no se puede aliminar tiene BR generadas';
            ELSE
            
              ----- Elimina todo lo que no se ha procesado
            
              OPEN cureliminarnew;
              LOOP
                FETCH cureliminarnew BULK COLLECT
                  INTO v_oid_cabe_recl,
                       v_oid_oper_recl,
                       v_oid_soli_cabe,
                       v_grpr_oid_grup_proc,
                       v_ind_soli_nega LIMIT rows;
                IF v_oid_cabe_recl.count > 0 THEN
                
                  FOR i IN v_oid_cabe_recl.first .. v_oid_cabe_recl.last
                  LOOP
                    IF v_grpr_oid_grup_proc(i) < 5 THEN
                      DELETE FROM rec_linea_opera_recla
                       WHERE opre_oid_oper_recl = v_oid_oper_recl(i)
                         AND timo_oid_tipo_movi =
                             decode(v_ind_soli_nega(i), 1, 2, 1);
                    
                      DELETE FROM ped_solic_posic
                       WHERE soca_oid_soli_cabe = v_oid_soli_cabe(i);
                    
                      DELETE FROM rec_solic_opera
                       WHERE opre_oid_oper_recl = v_oid_oper_recl(i)
                         AND soca_oid_soli_cabe = v_oid_soli_cabe(i);
                    
                      DELETE FROM ped_solic_cabec
                       WHERE oid_soli_cabe = v_oid_soli_cabe(i);
                    
                      lsmensaje := 'CDR eliminado Parcialmente';
                    
                    END IF;
                  END LOOP;
                
                END IF;
                EXIT WHEN cureliminarnew%NOTFOUND;
              END LOOP;
              CLOSE cureliminarnew;
            
            END IF;
          END IF;
        
        END IF;
      
        ---IF lsparametroconso = '2' AND contador = 0 THEN
        IF lsparametrocarel = '1' AND contador = 0 THEN
          contador := 0;
          --- Si hay alguna solicitud REc Atencion procesada no se puede eliminar
          IF lsnrosolfacenv <> 0 THEN
            contador  := 1;
            lsmensaje := 'CDR no se puede aliminar tiene Atenciones procesadas';
          ELSE
            ----- Genera cargo y actualiza informacion en la tabla REC
            OPEN cureliminarnew;
            LOOP
              FETCH cureliminarnew BULK COLLECT
                INTO v_oid_cabe_recl,
                     v_oid_oper_recl,
                     v_oid_soli_cabe,
                     v_grpr_oid_grup_proc,
                     v_ind_soli_nega LIMIT rows;
              IF v_oid_cabe_recl.count > 0 THEN
              
                FOR i IN v_oid_cabe_recl.first .. v_oid_cabe_recl.last
                LOOP
                  IF v_grpr_oid_grup_proc(i) >= 5 AND v_ind_soli_nega(i) = 1 THEN
                  
                    ----- call   ejecuta el CARGO
                    int_pkg_recla.int_pr_rec_proce_rever_cdr(pscodigopais,
                                                             v_oid_soli_cabe(i),
                                                             lsnuevasolicit);
                  
                    sto_pkg_envio_valid_sicc.sto_pr_genera_consolidado(lsnuevasolicit,
                                                                       lsparametrofecha,
                                                                       pscodigopais,
                                                                       lsparametroconso,
                                                                       'C');
                  
                    IF lsparametropunta = '1' THEN
                      inc_pkg_proce_incen.inc_pr_rever_cargo_devol(pscodigopais,
                                                                   lsnuevasolicit,
                                                                   psusuario);
                    END IF;
                  
                    UPDATE rec_linea_opera_recla
                       SET num_unid_recl = 0
                     WHERE opre_oid_oper_recl = v_oid_oper_recl(i)
                       AND timo_oid_tipo_movi =
                           decode(v_ind_soli_nega(i), 1, 2, 1);
                  
                    UPDATE rec_opera_recla
                       SET esop_oid_esta_oper = 6
                     WHERE care_oid_cabe_recl = v_oid_cabe_recl(i);
                  
                    UPDATE rec_cabec_recla
                       SET esre_oid_esta_recl = 5
                     WHERE oid_cabe_recl = v_oid_cabe_recl(i);
                  
                    ---lsmensaje := 'CDR eliminado Parcialmente y se Genero el cargo correspondiente';
                    lsmensaje := 'CDR eliminado y se Genero el cargo correspondiente';
                  
                  END IF;
                END LOOP;
              
              END IF;
              EXIT WHEN cureliminarnew%NOTFOUND;
            END LOOP;
            CLOSE cureliminarnew;
          END IF;
        ELSE
          ---- Verifica si alguna solicitud llego a GP5
          SELECT COUNT(1)
            INTO contador
            FROM int_solic_conso_poven_cabec i,
                 rec_cabec_recla             r,
                 rec_opera_recla             ro,
                 rec_solic_opera             rso,
                 ped_solic_cabec             p
           WHERE sec_nume_docu = pssecnumedocu
             AND i.oid_cabe_recl_refe = r.oid_cabe_recl
             AND r.oid_cabe_recl = ro.care_oid_cabe_recl
             AND ro.oid_oper_recl = rso.opre_oid_oper_recl
             AND rso.soca_oid_soli_cabe = p.oid_soli_cabe
             AND p. grpr_oid_grup_proc >= 5;
        
        END IF;
      
        --- Verifica si hay registros para borrar producto del borrado parcial
        IF lsnrosolfac <> 0 THEN
          -- Si se facturo alguna solicitud se borra parcial
        
          OPEN cureliminarval;
          LOOP
            FETCH cureliminarval BULK COLLECT
              INTO v_oid_cabe_recl,
                   v_oid_oper_recl LIMIT rows;
            IF v_oid_cabe_recl.count > 0 THEN
              FOR i IN v_oid_cabe_recl.first .. v_oid_cabe_recl.last
              LOOP
                DELETE FROM rec_opera_recla
                 WHERE oid_oper_recl = v_oid_oper_recl(i);
              END LOOP;
            END IF;
            EXIT WHEN cureliminarval%NOTFOUND;
          END LOOP;
          CLOSE cureliminarval;
        
          OPEN cureliminarval2;
          LOOP
            FETCH cureliminarval2 BULK COLLECT
              INTO v_oid_cabe_recl LIMIT rows;
            IF v_oid_cabe_recl.count > 0 THEN
              FOR i IN v_oid_cabe_recl.first .. v_oid_cabe_recl.last
              LOOP
                DELETE FROM rec_cabec_recla
                 WHERE oid_cabe_recl = v_oid_cabe_recl(i);
              END LOOP;
            END IF;
            EXIT WHEN cureliminarval2%NOTFOUND;
          END LOOP;
          CLOSE cureliminarval2;
        END IF;

        --- Actualizar saldo
        UPDATE mae_clien SET VAL_RECL_PEND = REC_PKG_PROCE.REC_FN_ABONO_PENDI_CONSU(lsoid_clie,null)
        WHERE oid_clie = lsoid_clie;
      
        ---- Inserta auditoria
        UPDATE rec_audit_elimi_cdr
           SET des_erro           = lsmensaje,
               num_docu           = lsnum_docu,
               cod_clie           = lscod_clie,
               oid_cabe_recl      = lsoid_cabe_recl,
               oid_soli_cabe_carg = lsnuevasolicit
         WHERE oid_audi_elim = lsoidaudielim;
      
        IF contador = 0 AND lsverifica = 1 THEN
          --- Pasar de STO_DOCUM_DIGIT al STO_HISTO_DOCUM_DIGIT con cambio del lote
          INSERT INTO sto_histo_docum_digit
            SELECT *
              FROM sto_docum_digit
             WHERE num_lote = psnumerolote
               AND (sec_nume_docu_cabe = pssecnumedocu OR
                   sec_nume_docu = pssecnumedocu);
        
          --- Pasar de SSTO_DETAL_DOCUM_EXCEP al SSTO_HISTO_DETAL_DOCUM_EXCEP con cambio del lote
          INSERT INTO sto_histo_detal_docum_excep
            SELECT *
              FROM sto_detal_docum_excep
             WHERE num_lote = psnumerolote
               AND sec_nume_docu IN
                   (SELECT sec_nume_docu
                      FROM sto_docum_digit
                     WHERE (sec_nume_docu_cabe = pssecnumedocu OR
                           sec_nume_docu = pssecnumedocu));
          --- Pasar de STO_AUDIT_EXCEP al STO_HISTO_AUDIT_EXCEP con cambio del lote
        
          INSERT INTO sto_histo_audit_excep
            SELECT *
              FROM sto_audit_excep
             WHERE num_lote = psnumerolote
               AND sec_nume_docu IN
                   (SELECT sec_nume_docu
                      FROM sto_docum_digit
                     WHERE num_lote = psnumerolote
                       AND (sec_nume_docu_cabe = pssecnumedocu OR
                           sec_nume_docu = pssecnumedocu));
        
          --- Pasar de int_solic_conso_poven_cabec al INT_HISTO_CONSO_POVEN_CABEC con cambio del lote
          INSERT INTO int_histo_conso_poven_cabec
            SELECT *
              FROM int_solic_conso_poven_cabec
             WHERE num_lote = psnumerolote
               AND sec_nume_docu IN
                   (SELECT sec_nume_docu
                      FROM sto_docum_digit
                     WHERE num_lote = psnumerolote
                       AND sec_nume_docu = pssecnumedocu);
        
          --- Pasar de int_solic_conso_poven_detal al INT_HISTO_CONSO_POVEN_DETAL con cambio del lote
          INSERT INTO int_histo_conso_poven_detal
            SELECT *
              FROM int_solic_conso_poven_detal
             WHERE num_lote = psnumerolote
               AND sec_nume_docu IN
                   (SELECT sec_nume_docu
                      FROM sto_docum_digit
                     WHERE sec_nume_docu_cabe = pssecnumedocu);
        
          --- Borra en STO_AUDIT_EXCEP
          DELETE FROM sto_audit_excep
           WHERE sec_nume_docu IN
                 (SELECT sec_nume_docu
                    FROM sto_docum_digit
                   WHERE (sec_nume_docu_cabe = pssecnumedocu OR
                         sec_nume_docu = pssecnumedocu));
        
          --- Borra en STO_DETAL_DOCUM_EXCEP
          DELETE FROM sto_detal_docum_excep
           WHERE sec_nume_docu IN
                 (SELECT sec_nume_docu
                    FROM sto_docum_digit
                   WHERE (sec_nume_docu_cabe = pssecnumedocu OR
                         sec_nume_docu = pssecnumedocu));
        
          --- Borra en int_solic_conso_detal
          DELETE FROM int_solic_conso_poven_detal
           WHERE sec_nume_docu IN
                 (SELECT sec_nume_docu
                    FROM sto_docum_digit
                   WHERE (sec_nume_docu_cabe = pssecnumedocu OR
                         sec_nume_docu = pssecnumedocu));
        
          --- Borra en int_solic_conso_cabec
          DELETE FROM int_solic_conso_poven_cabec
           WHERE sec_nume_docu IN
                 (SELECT sec_nume_docu
                    FROM sto_docum_digit
                   WHERE (sec_nume_docu_cabe = pssecnumedocu OR
                         sec_nume_docu = pssecnumedocu));
        
          --- Borra en STO_DOCUM_DIGIT
          DELETE FROM sto_docum_digit
           WHERE sec_nume_docu_cabe = pssecnumedocu
              OR sec_nume_docu = pssecnumedocu;
        
        END IF;

     END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_elimi_recla: ' || ls_sqlerrm);
    
  END sto_pr_elimi_recla;

  /***************************************************************************
  Descripcion       : Inserta los detalles agregados por los programas de
                      nuevas, session experte, dupla cyzone y oportunidades
                      privilege  en STO
  Fecha Creacion    : 30/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_carga_sto_valid_carga
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR curdetalle IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.cod_vent,
             det.tip_posic,
             seq_docu_sto.nextval sec_nume_docu,
             det.docu_num_docu,
             cab.cod_zona,
             cab.cod_regi,
             cab.sec_nume_docu    sec_nume_docu_cabe
        FROM int_solic_conso_cabec cab,
             int_solic_conso_detal det,
             sto_proce_docum_digit tmp
       WHERE cab.cod_pais = det.cod_pais
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_clie = det.cod_clie
         AND cab.num_lote = det.num_lote
         AND det.ind_envi_sto = '0'
         AND tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodigotipodocumento;
  
    TYPE t_det_cod_pais IS TABLE OF int_solic_conso_detal.cod_pais%TYPE;
    TYPE t_det_cod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;
    TYPE t_det_cod_clie IS TABLE OF int_solic_conso_detal.cod_clie%TYPE;
    TYPE t_det_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_det_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_det_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
  
    TYPE t_det_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_det_docu_num_docu IS TABLE OF int_solic_conso_cabec.docu_num_docu%TYPE;
    TYPE t_det_cod_zona IS TABLE OF int_solic_conso_cabec.cod_zona%TYPE;
    TYPE t_det_cod_regi IS TABLE OF int_solic_conso_cabec.cod_regi%TYPE;
  
    TYPE t_det_sec_nume_docu_cabe IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
  
    v_det_cod_pais  t_det_cod_pais;
    v_det_cod_peri  t_det_cod_peri;
    v_det_cod_clie  t_det_cod_clie;
    v_det_num_lote  t_det_num_lote;
    v_det_cod_vent  t_det_cod_vent;
    v_det_tip_posic t_det_tip_posic;
  
    v_det_sec_nume_docu t_det_sec_nume_docu;
    v_det_docu_num_docu t_det_docu_num_docu;
    v_det_cod_zona      t_det_cod_zona;
    v_det_cod_regi      t_det_cod_regi;
  
    v_det_sec_nume_docu_cabe t_det_sec_nume_docu_cabe;
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocumento);
    OPEN curdetalle;
    LOOP
      FETCH curdetalle BULK COLLECT
        INTO v_det_cod_pais,
             v_det_cod_peri,
             v_det_cod_clie,
             v_det_num_lote,
             v_det_cod_vent,
             v_det_tip_posic,
             v_det_sec_nume_docu,
             v_det_docu_num_docu,
             v_det_cod_zona,
             v_det_cod_regi,
             v_det_sec_nume_docu_cabe LIMIT rows;
      IF v_det_cod_pais.count > 0 THEN
      
        FORALL i IN 1 .. v_det_cod_pais.count
          INSERT INTO sto_docum_digit
            (cod_pais,
             cod_tipo_docu,
             num_lote,
             sec_nume_docu,
             num_docu,
             ind_envi,
             ind_rech,
             fec_digi,
             usu_digi,
             cod_zona,
             cod_clie,
             cod_regi,
             sec_nume_docu_cabe,
             fec_modi,
             usu_modi,
             cod_peri,
             num_proc)
          VALUES
            (pscodigopais,
             lscodigodocumentodetalle,
             v_det_num_lote(i),
             v_det_sec_nume_docu(i),
             v_det_docu_num_docu(i),
             '0',
             '0',
             SYSDATE,
             pscodigousuario,
             v_det_cod_zona(i),
             v_det_cod_clie(i),
             v_det_cod_regi(i),
             v_det_sec_nume_docu_cabe(i),
             SYSDATE,
             pscodigousuario,
             v_det_cod_peri(i),
             psnumeroproceso);
      
        FORALL i IN 1 .. v_det_cod_pais.count
          UPDATE int_solic_conso_detal det
             SET det.ind_envi_sto       = '1',
                 det.sec_nume_docu      = v_det_sec_nume_docu(i),
                 det.sec_nume_docu_cabe = v_det_sec_nume_docu_cabe(i)
           WHERE det.cod_pais = v_det_cod_pais(i)
             AND det.cod_peri = v_det_cod_peri(i)
             AND det.cod_clie = v_det_cod_clie(i)
             AND det.num_lote = v_det_num_lote(i)
             AND det.cod_vent = v_det_cod_vent(i)
             AND det.tip_posic = v_det_tip_posic(i);
      
      END IF;
      EXIT WHEN curdetalle%NOTFOUND;
    END LOOP;
    CLOSE curdetalle;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_carga_sto_valid_carga: ' ||
                              ls_sqlerrm);
  END sto_pr_carga_sto_valid_carga;

  /***************************************************************************
  Descripcion       : Genera un registro de error de validacion 7 para la
                      orden de transporte
  Fecha Creacion    : 20/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_gener_noved_ortra
  (
    pscodigopais        VARCHAR2,
    psnumerosecuencia   VARCHAR2,
    pscodigotipodoc     VARCHAR2,
    psnumerolote        VARCHAR2,
    pscodestadoentrega  VARCHAR2,
    pscodnovedad        VARCHAR2,
    pscodcalificacion   VARCHAR2,
    psindnovedad        VARCHAR2,
    psindgenerarnovedad VARCHAR2,
    psusuario           VARCHAR2
  ) IS
    lssecuencia     NUMBER;
    lncodigomensaje NUMBER;
  BEGIN
    -- Obtiene la secuencia
    SELECT seq_docu_sto.nextval INTO lssecuencia FROM dual;
  
    --Obtiene el mensaje
    lncodigomensaje := sto_pkg_gener.sto_fn_devue_codig_mensa(pscodigopais,
                                                              pscodigotipodoc,
                                                              codigo_vali7_orden_trans);
  
    -- Evalua el indicador de novedad
    IF psindnovedad = '1' THEN
      UPDATE int_solic_conso_orden_trans
         SET ind_nove = '1'
       WHERE cod_pais = pscodigopais
         AND sec_nume_docu = psnumerosecuencia;
    END IF;
  
    IF psindgenerarnovedad = '1' THEN
      -- Crea el registro en int_solic_conso_orden_trans
      INSERT INTO int_solic_conso_orden_trans
        (cod_pais,
         cod_comp,
         num_docu,
         cod_tipo_docu,
         cod_comp_tran,
         cod_cent_acop,
         cod_esta_entr,
         cod_esta_reco,
         cod_nove,
         cod_reci_conf,
         oid_pais,
         fec_proc,
         sta_proc,
         cod_moti_rech,
         num_lote,
         cod_esta_ent2,
         val_mens,
         val_dire,
         sec_nume_docu,
         ind_nove,
         ind_envi,
         tip_orde,
         cod_zona,
         cod_cali,
         cod_clie,
         fec_fact,
         nom_clie,
         cod_peri,
         cod_regi,
         cod_secc)
        (SELECT cod_pais,
                cod_comp,
                num_docu,
                cod_tipo_docu,
                cod_comp_tran,
                cod_cent_acop,
                pscodestadoentrega, --cod_esta_entr,
                cod_esta_reco,
                pscodnovedad, --cod_nove,
                cod_reci_conf,
                oid_pais,
                fec_proc,
                sta_proc,
                cod_moti_rech,
                num_lote,
                cod_esta_ent2,
                val_mens,
                val_dire,
                lssecuencia, --sec_nume_docu,
                '', --ind_nove,
                '', --ind_envi,
                tip_orde,
                cod_zona,
                pscodcalificacion, --cod_cali,
                cod_clie,
                fec_fact,
                nom_clie,
                cod_peri,
                cod_regi,
                cod_secc
           FROM int_solic_conso_orden_trans
          WHERE cod_pais = pscodigopais
            AND sec_nume_docu = psnumerosecuencia);
    
      -- Crea el registro en STO_DOCUM_DIGIT
      INSERT INTO sto_docum_digit
        (cod_pais,
         cod_tipo_docu,
         num_lote,
         sec_nume_docu,
         num_docu,
         cod_ulti_vali_ejec,
         cod_ulti_vali_exit,
         cod_ulti_vali_erro,
         ind_envi,
         ind_rech,
         fec_digi,
         usu_digi,
         fec_modi,
         usu_modi,
         num_proc,
         cod_zona,
         cod_clie,
         cod_regi,
         sec_nume_docu_cabe,
         cod_peri,
         cod_moti_rech,
         val_obse_rech_defi,
         ind_elim,
         ind_rece_ocr,
         ind_rece_web,
         ind_rece_dd,
         ind_rece_digi,
         ind_rece_cc,
         ind_rece_mens)
        (SELECT cod_pais,
                cod_tipo_docu,
                num_lote,
                lssecuencia, --sec_nume_docu,
                num_docu,
                cod_ulti_vali_ejec,
                cod_ulti_vali_exit,
                cod_ulti_vali_erro,
                ind_envi,
                ind_rech,
                fec_digi,
                usu_digi,
                fec_modi,
                usu_modi,
                num_proc,
                cod_zona,
                cod_clie,
                cod_regi,
                sec_nume_docu_cabe,
                cod_peri,
                cod_moti_rech,
                val_obse_rech_defi,
                ind_elim,
                ind_rece_ocr,
                ind_rece_web,
                ind_rece_dd,
                ind_rece_digi,
                ind_rece_cc,
                ind_rece_mens
           FROM sto_docum_digit
          WHERE sec_nume_docu = psnumerosecuencia
            AND cod_pais = pscodigopais);
    
      -- Inserta el registro de error
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
         psnumerolote,
         codigo_vali7_orden_trans,
         psnumerosecuencia,
         '0', --psindgestion,
         SYSDATE,
         psusuario,
         SYSDATE,
         psusuario,
         lncodigomensaje);
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_gener_noved_ortra: ' ||
                              ls_sqlerrm);
  END sto_pr_gener_noved_ortra;

  /***************************************************************************
  Descripcion       : Migra hacia el historico de INT_HISTO_CONSO_FAMIL_SEGUR
  Fecha Creacion    : 04/05/2011
  Autor             : Jose Lui Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_histo_famil_segur
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
    CURSOR curinshistosoli(vstipodocumento VARCHAR2) IS
      SELECT c.*
        FROM int_solic_conso_famil_segur c,
             sto_tmp_docum_digit         t
       WHERE c.sec_nume_docu = t.sec_nume_docu
         AND c.num_lote = t.num_lote
         AND t.cod_tipo_docu = vstipodocumento;
  
    TYPE int_solic_conso_tab_t IS TABLE OF int_solic_conso_famil_segur%ROWTYPE INDEX BY BINARY_INTEGER;
    int_solic_conso_tab int_solic_conso_tab_t; -- In-memory table
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_carga_aptos_histo(pscodigopais,
                                           psnumlote,
                                           psfechainicio,
                                           psfechafin,
                                           pstipodocumento,
                                           pscodigoperiodo);
  
    OPEN curinshistosoli(pstipodocumento);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinshistosoli BULK COLLECT
        INTO int_solic_conso_tab LIMIT rows;
      EXIT WHEN int_solic_conso_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
        INSERT INTO int_histo_conso_famil_segur
        VALUES int_solic_conso_tab
          (i);
    
      FOR i IN int_solic_conso_tab.first .. int_solic_conso_tab.last
      LOOP
      
        DELETE FROM int_solic_conso_famil_segur a
         WHERE a.num_lote = int_solic_conso_tab(i).num_lote
           AND a.sec_nume_docu = int_solic_conso_tab(i).sec_nume_docu;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinshistosoli;
  
    sto_pr_almac_histo(pscodigopais, pstipodocumento);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_HISTO_FAMIL_SEGUR: ' ||
                              ls_sqlerrm);
  END sto_pr_histo_famil_segur;

  /***************************************************************************
  Descripcion       : Reversa los pedidos hasta gp3
  Fecha Creacion    : 17/06/2011
  Autor             : Jose Lui Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_rever_pedid_gp3(psnumpedido VARCHAR2) IS
    vnnumpedido NUMBER(12);
  
    v_cab_cod_peri int_solic_conso_cabec.cod_peri%TYPE;
  
    v_cab_sec_nume_docu       int_solic_conso_cabec.sec_nume_docu%TYPE;
    v_cab_clie_oid_clie       int_solic_conso_cabec.clie_oid_clie%TYPE;
    v_soca_oid_soli_cabe_refe int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE;
    v_perd_oid_peri           int_solic_conso_cabec.perd_oid_peri%TYPE;
  
    CURSOR curoidenvi(vnoidsolicabe NUMBER) IS
      SELECT menv_oid_envi
        FROM mav_solic_envio
       WHERE soca_oid_soli_cabe = vnoidsolicabe;
  
    TYPE t_menv_oid_envi IS TABLE OF mav_solic_envio.menv_oid_envi%TYPE;
    v_menv_oid_envi t_menv_oid_envi;
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    j    BINARY_INTEGER := 0;
  
  BEGIN
  
    vnnumpedido := to_number(psnumpedido);
  
    BEGIN
      SELECT cons.cod_peri,
             cons.sec_nume_docu,
             cons.clie_oid_clie,
             cons.soca_oid_soli_cabe_refe,
             cons.perd_oid_peri
        INTO v_cab_cod_peri,
             v_cab_sec_nume_docu,
             v_cab_clie_oid_clie,
             v_soca_oid_soli_cabe_refe,
             v_perd_oid_peri
        FROM int_solic_conso_cabec cons,
             ped_solic_cabec       cabe
       WHERE cabe.oid_soli_cabe = cons.soca_oid_soli_cabe_refe
         AND cabe.oid_soli_cabe = vnnumpedido
         AND cons.ind_ocs_proc = '1';
    EXCEPTION
      WHEN no_data_found THEN
      
        v_cab_sec_nume_docu       := NULL;
        v_cab_clie_oid_clie       := NULL;
        v_soca_oid_soli_cabe_refe := NULL;
      
    END;
  
    -- Borra mensajes de PED
    DELETE FROM ped_solic_mensa
     WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe;
  
    OPEN curoidenvi(v_soca_oid_soli_cabe_refe);
    LOOP
      FETCH curoidenvi BULK COLLECT
        INTO v_menv_oid_envi LIMIT rows;
    
      IF v_menv_oid_envi.count > 0 THEN
      
        FORALL j IN 1 .. v_menv_oid_envi.count
          DELETE FROM mav_solic_envio
           WHERE menv_oid_envi = v_menv_oid_envi(j);
      
        FORALL j IN 1 .. v_menv_oid_envi.count
          DELETE FROM mav_envio
           WHERE oid_envi = v_menv_oid_envi(j)
             AND EXISTS (SELECT 1
                    FROM mav_detal_mav
                   WHERE oid_deta_mav = denv_oid_deta_mav
                     AND tepr_oid_tipo_esta_proc = 3);
      
        FORALL j IN 1 .. v_menv_oid_envi.count
          UPDATE mav_envio
             SET ind_envi = 'P'
           WHERE oid_envi = v_menv_oid_envi(j)
             AND EXISTS (SELECT 1
                    FROM mav_detal_mav
                   WHERE oid_deta_mav = denv_oid_deta_mav
                     AND tepr_oid_tipo_esta_proc = 4);
      
      END IF;
      EXIT WHEN curoidenvi%NOTFOUND;
    
    END LOOP;
    CLOSE curoidenvi;
  
    --ELIMINAMOS INFORMACION DE NUEVO MAV
    sto_pr_elimi_mav_envio(v_soca_oid_soli_cabe_refe);
  
    DELETE FROM msg_buzon_mensa
     WHERE clie_oid_clie = v_cab_clie_oid_clie
       AND ind_acti = 1
       AND EXISTS (SELECT oid_mens
              FROM msg_mensa
             WHERE cod_mens LIKE 'MAV%'
               AND mens_oid_mens = oid_mens);
  
    --  Incentivos : Se deben de ejecutar los siguientes querys:
    /*DELETE FROM inc_solic_concu_punta WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe;
    
    DELETE FROM inc_solic_concu_punta_temp WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe;
    
    DELETE FROM inc_pedid_concu_recom
     WHERE clr3_oid_clie_rete IN
           (SELECT c.oid_clie_rete
              FROM inc_clien_recte       c,
                   inc_concu_param_gener a,
                   inc_concu_param_consu b
             WHERE c.copa_oid_para_gral = a.oid_para_gral
               AND a.oid_para_gral = b.copa_oid_para_gral
               AND a.bcal_oid_base_calc = 4 -- dato fijo
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_desd) <= v_cab_cod_peri -- periodo actual
               AND v_cab_cod_peri <= gen_fn_calcu_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_hast),
                                                        b.val_peri_eval - 1) -- periodo actual
               AND c.clie_oid_clie = v_cab_clie_oid_clie -- oid cliente
            )
       AND perd_oid_peri = v_perd_oid_peri
       AND clre_oid_clie_redo IS NULL;
    
    DELETE FROM inc_pedid_concu_recom
     WHERE clre_oid_clie_redo IN
           (SELECT oid_clie_redo
              FROM inc_clien_recdo       d,
                   inc_clien_recte       e,
                   inc_concu_param_gener a,
                   inc_concu_param_consu b
             WHERE d.clr3_oid_clie_rete = e.oid_clie_rete
               AND d.clie_oid_clie = v_cab_clie_oid_clie -- oid del cliente
               AND d.perd_oid_peri = v_perd_oid_peri -- periodo actual
               AND e.copa_oid_para_gral = a.oid_para_gral
               AND a.oid_para_gral = b.copa_oid_para_gral
               AND a.bcal_oid_base_calc = 4 -- 4 dato fijo
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_desd) <= v_cab_cod_peri -- periodo actual
               AND v_cab_cod_peri <= gen_fn_calcu_perio(gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_hast),
                                                        b.val_peri_eval - 1))
       AND perd_oid_peri = v_perd_oid_peri;
    
    DELETE FROM inc_pedid_concu_recom_temp
     WHERE perd_oid_peri = v_perd_oid_peri
       AND clie_oid_clie_rete = v_cab_clie_oid_clie;
    
    DELETE FROM inc_solic_concu_recom WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe;
    
    DELETE FROM inc_solic_concu_recom_temp WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe;*/
    --
  
    DELETE ped_solic_posic WHERE soca_oid_soli_cabe = vnnumpedido;
  
    DELETE ped_solic_cabec WHERE oid_soli_cabe = vnnumpedido;
  
    UPDATE sto_docum_digit
       SET ind_envi = '0',
           ind_rech = '0'
     WHERE sec_nume_docu = v_cab_sec_nume_docu;
  
    UPDATE sto_docum_digit
       SET ind_envi = '0',
           ind_rech = '0'
     WHERE sec_nume_docu_cabe = v_cab_sec_nume_docu;
  
    UPDATE int_solic_conso_cabec
       SET ind_ocs_proc = '0'
     WHERE soca_oid_soli_cabe_refe = v_soca_oid_soli_cabe_refe;
  
    DELETE inc_premi_elegi x
     WHERE EXISTS
     (SELECT 1
              FROM int_solic_conso_detal det,
                   int_solic_conso_cabec cab
             WHERE cab.cod_clie = det.cod_clie
               AND cab.num_lote = det.num_lote
               AND cab.cod_peri = det.cod_peri
               AND cab.cod_pais = det.cod_pais
               AND det.num_prem IS NOT NULL
               AND cab.soca_oid_soli_cabe_refe = v_soca_oid_soli_cabe_refe
               AND det.copa_oid_para_gral = x.copa_oid_para_gral
               AND det.panp_oid_para_nive_prem = x.panp_oid_para_nive_prem
               AND det.num_prem = x.num_prem
               AND cab.clie_oid_clie = x.clie_oid_clie);
  
    -- Reversion Ped_solic_cabec_acum2
    /*SELECT NVL(COUNT(*),0), NVL(SUM( e.num_unid_dema_real * e.val_prec_cata_unit_loca ),0)
    INTO v_Detalles, v_MontoCatDeman
    FROM ped_solic_cabec a,
             ped_solic_cabec b,
             ped_tipo_solic_pais c,
             ped_tipo_solic d,
             ped_solic_posic e
    WHERE   a.clie_oid_clie          = v_cab_clie_oid_clie
                 AND a.perd_oid_peri = v_perd_oid_peri
                 AND b.perd_oid_peri = a.perd_oid_peri
                 AND a.soca_oid_soli_cabe = b.oid_soli_cabe
                 AND e.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND a.tspa_oid_tipo_soli_pais = c.oid_Tipo_Soli_Pais
                 AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
                 AND d.COD_TIPO_SOLI = 'SOC'
                 AND a.fec_fact IS NOT NULL
                 AND e.espo_oid_esta_posi <> 2
                 AND b.esso_oid_esta_soli <> 4;
    
    if v_Detalles = 0 then
    
             Delete from ped_solic_cabec_acum2 where clie_oid_clie = v_cab_clie_oid_clie and perd_oid_peri = v_perd_oid_peri;
    
    else
    
             Update ped_solic_cabec_acum2 set val_cant_pedi = 1, val_mont_tota = v_MontoCatDeman where clie_oid_clie = v_cab_clie_oid_clie and perd_oid_peri = v_perd_oid_peri;
    end if;*/
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_REVER_PEDID_GP3: ' ||
                              ls_sqlerrm);
  END sto_pr_rever_pedid_gp3;

  /***************************************************************************
     Descripcion       : Elimina Poilizas de Familia Segura
     Fecha Creacion    : 17/08/2011
     Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_elimi_poliz
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumerolote          VARCHAR2,
    pssecnumedocu         VARCHAR2
  ) IS
  
  BEGIN
  
    INSERT INTO int_histo_conso_famil_segur
      SELECT *
        FROM int_solic_conso_famil_segur
       WHERE num_lote = psnumerolote
         AND sec_nume_docu = pssecnumedocu;
  
    INSERT INTO sto_histo_docum_digit
      SELECT *
        FROM sto_docum_digit
       WHERE num_lote = psnumerolote
         AND sec_nume_docu = pssecnumedocu
         AND cod_tipo_docu = pscodigotipodocumento
         AND cod_pais = pscodigopais;
  
    INSERT INTO sto_histo_detal_docum_excep
      SELECT *
        FROM sto_detal_docum_excep e
       WHERE num_lote = psnumerolote
         AND sec_nume_docu = pssecnumedocu
         AND cod_tipo_docu = pscodigotipodocumento
         AND cod_pais = pscodigopais;
  
    INSERT INTO sto_histo_audit_excep
      SELECT *
        FROM sto_audit_excep a
       WHERE num_lote = psnumerolote
         AND sec_nume_docu = pssecnumedocu
         AND cod_tipo_docu = pscodigotipodocumento
         AND cod_pais = pscodigopais;
  
    DELETE FROM int_solic_conso_famil_segur
     WHERE num_lote = psnumerolote
       AND num_lote = psnumerolote
       AND sec_nume_docu = pssecnumedocu;
  
    DELETE FROM sto_audit_excep a
     WHERE num_lote = psnumerolote
       AND sec_nume_docu = pssecnumedocu
       AND cod_tipo_docu = pscodigotipodocumento
       AND cod_pais = pscodigopais;
  
    DELETE FROM sto_detal_docum_excep e
     WHERE num_lote = psnumerolote
       AND sec_nume_docu = pssecnumedocu
       AND cod_tipo_docu = pscodigotipodocumento
       AND cod_pais = pscodigopais;
  
    DELETE FROM sto_docum_digit
     WHERE num_lote = psnumerolote
       AND sec_nume_docu = pssecnumedocu
       AND cod_tipo_docu = pscodigotipodocumento
       AND cod_pais = pscodigopais;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_ELIMI_POLIZ: ' || ls_sqlerrm);
  END sto_pr_elimi_poliz;

  /***************************************************************************
     Descripcion       : Elimina informacion de MAV
     Fecha Creacion    : 06/08/2013
     Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE sto_pr_elimi_mav_envio(pnoidsolicitud NUMBER) IS
  
    --RECUPERAR INFORMACION DE ANTIGUO MAV
    CURSOR curoidenvi(vnoidsolicabe NUMBER) IS
      SELECT menv_oid_envi
        FROM mav_solic_envio
       WHERE soca_oid_soli_cabe = vnoidsolicabe;
  
    TYPE t_menv_oid_envi IS TABLE OF mav_solic_envio.menv_oid_envi%TYPE;
    v_menv_oid_envi t_menv_oid_envi;
  
    --RECUPERAR INFORMACION DE NUEVO MAV
    CURSOR curoidenvi2(vnoidsolicabe NUMBER) IS
      SELECT menv_oid_envi
        FROM mav_solic_envio_confi
       WHERE soca_oid_soli_cabe = vnoidsolicabe;
  
    TYPE t_menv_oid_envi2 IS TABLE OF mav_solic_envio_confi.menv_oid_envi%TYPE;
    v_menv_oid_envi2 t_menv_oid_envi2;
  
    lndetalles      NUMBER(12);
    lnmontocatdeman NUMBER(12, 2);
    lnoidcliente    ped_solic_cabec.clie_oid_clie%TYPE;
    lnoidperiodo    ped_solic_cabec.perd_oid_peri%TYPE;
  BEGIN
  
    --ELIMINAMOS INFORMACION DE ANTIGUO MAV
    OPEN curoidenvi(pnoidsolicitud);
    LOOP
      FETCH curoidenvi BULK COLLECT
        INTO v_menv_oid_envi LIMIT w_filas;
      IF v_menv_oid_envi.count > 0 THEN
      
        FORALL j IN 1 .. v_menv_oid_envi.count
          DELETE FROM mav_solic_envio
           WHERE menv_oid_envi = v_menv_oid_envi(j);
      
        FORALL j IN 1 .. v_menv_oid_envi.count
          DELETE FROM mav_envio env
           WHERE oid_envi = v_menv_oid_envi(j)
             AND EXISTS
           (SELECT 1
                    FROM mav_detal_mav con
                   WHERE con.oid_deta_mav = env.denv_oid_deta_mav
                     AND con.tepr_oid_tipo_esta_proc = 3);
      
        FORALL j IN 1 .. v_menv_oid_envi.count
          UPDATE mav_envio env
             SET ind_envi = 'P'
           WHERE oid_envi = v_menv_oid_envi(j)
             AND EXISTS
           (SELECT 1
                    FROM mav_detal_mav con
                   WHERE con.oid_deta_mav = env.denv_oid_deta_mav
                     AND con.tepr_oid_tipo_esta_proc = 4);
      
      END IF;
      EXIT WHEN curoidenvi%NOTFOUND;
    END LOOP;
  
    CLOSE curoidenvi;
  
    --ELIMINAMOS INFORMACION DE NUEVO MAV
    OPEN curoidenvi2(pnoidsolicitud);
    LOOP
      FETCH curoidenvi2 BULK COLLECT
        INTO v_menv_oid_envi2 LIMIT w_filas;
      IF v_menv_oid_envi2.count > 0 THEN
      
        FORALL j IN 1 .. v_menv_oid_envi2.count
          DELETE FROM mav_solic_envio_confi
           WHERE menv_oid_envi = v_menv_oid_envi2(j);
      
        FORALL j IN 1 .. v_menv_oid_envi2.count
          DELETE FROM mav_envio_confi env
           WHERE oid_envi = v_menv_oid_envi2(j)
             AND EXISTS (SELECT 1
                    FROM mav_param_confi con
                   WHERE con.cor_para_conf = env.cor_para_conf
                     AND con.cod_esta_mav = 3);
      
        FORALL j IN 1 .. v_menv_oid_envi2.count
          UPDATE mav_envio_confi env
             SET ind_envi = 'P'
           WHERE oid_envi = v_menv_oid_envi2(j)
             AND EXISTS (SELECT 1
                    FROM mav_param_confi con
                   WHERE con.cor_para_conf = env.cor_para_conf
                     AND con.cod_esta_mav = 4);
      
      END IF;
      EXIT WHEN curoidenvi2%NOTFOUND;
    END LOOP;
    CLOSE curoidenvi2;
  
    --Obtenemos el Cliente y Periodo de la Solicitud
    SELECT clie_oid_clie,
           perd_oid_peri
      INTO lnoidcliente,
           lnoidperiodo
      FROM ped_solic_cabec
     WHERE oid_soli_cabe = pnoidsolicitud;
  
    --Obtenemos el Monto de la Solicitud
    SELECT COUNT(1),
           nvl(SUM(e.num_unid_dema_real * e.val_prec_cata_unit_loca), 0)
      INTO lndetalles,
           lnmontocatdeman
      FROM ped_solic_cabec     a,
           ped_solic_cabec     b,
           ped_tipo_solic_pais c,
           ped_tipo_solic      d,
           ped_solic_posic     e
     WHERE a.clie_oid_clie = lnoidcliente
       AND a.perd_oid_peri = lnoidperiodo
       AND b.perd_oid_peri = a.perd_oid_peri
       AND a.soca_oid_soli_cabe = b.oid_soli_cabe
       AND e.soca_oid_soli_cabe = a.oid_soli_cabe
       AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
       AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
       AND d.cod_tipo_soli = 'SOC'
       AND a.fec_fact IS NOT NULL
       AND e.espo_oid_esta_posi <> 2
       AND b.esso_oid_esta_soli <> 4;
  
    --Actualizamos en PED_SOLIC_CABEC_ACUM2
    IF (lndetalles = 0) THEN
      DELETE FROM ped_solic_cabec_acum2
       WHERE clie_oid_clie = lnoidcliente
         AND perd_oid_peri = lnoidperiodo;
    ELSE
      UPDATE ped_solic_cabec_acum2
         SET val_cant_pedi = 1,
             val_mont_tota = lnmontocatdeman
       WHERE clie_oid_clie = lnoidcliente
         AND perd_oid_peri = lnoidperiodo;
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
      /*      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR sto_pr_elimi_mav_envio: ' || ls_sqlerrm);*/
  END sto_pr_elimi_mav_envio;
 
END sto_pkg_proce_gener;
/
