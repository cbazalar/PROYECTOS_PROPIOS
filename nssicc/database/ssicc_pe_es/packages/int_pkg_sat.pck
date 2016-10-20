CREATE OR REPLACE PACKAGE int_pkg_sat AS

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion de
                      compañias de transporte y centros de acopio (CENACO)
  Fecha Creacion    : 03/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_centr_acopi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion de
                      cobertura por codigo territorial (COBERT)
  Fecha Creacion    : 03/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_cober_terri
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion de
                      archivo de División de Armado por CDP
  Fecha Creacion    : 20/10/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_divis_armad
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Orden de Impresion
                      generado por APESAT
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_orden_impre
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Cálculo de Fecha de Entrega Exacta
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_param_calcu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Excepciones para Fecha
                      de Entrega Exacta
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_excep_fecha
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Seguimiento del Pedido
  
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_segui_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Impresion Boletas de
                      Entrega
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_impre_bolet
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
END int_pkg_sat;
/
CREATE OR REPLACE PACKAGE BODY int_pkg_sat IS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion de
                      compañias de transporte y centros de acopio (CENACO)
  Fecha Creacion    : 03/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_centr_acopi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
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
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_comp_tran IS TABLE OF sto_centr_acopi.cod_comp_tran%TYPE;
    TYPE t_cod_cent_acop IS TABLE OF sto_centr_acopi.cod_cent_acop%TYPE;
    TYPE t_nom_comp_tran IS TABLE OF sto_centr_acopi.nom_comp_tran%TYPE;
    TYPE t_nom_cent_acop IS TABLE OF sto_centr_acopi.nom_cent_acop%TYPE;
    TYPE t_val_emai IS TABLE OF sto_centr_acopi.val_emai%TYPE;
  
    v_cod_comp_tran t_cod_comp_tran := t_cod_comp_tran();
    v_cod_cent_acop t_cod_cent_acop := t_cod_cent_acop();
    v_nom_comp_tran t_nom_comp_tran := t_nom_comp_tran();
    v_nom_cent_acop t_nom_cent_acop := t_nom_cent_acop();
    v_val_emai      t_val_emai := t_val_emai();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
  BEGIN
  
    EXECUTE IMMEDIATE 'TRUNCATE TABLE STO_CENTR_ACOPI';
  
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
              
                IF (posicion = 1) THEN
                  v_cod_comp_tran.extend;
                  v_cod_comp_tran(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_cent_acop.extend;
                  v_cod_cent_acop(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 3) THEN
                  v_nom_comp_tran.extend;
                  v_nom_comp_tran(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 4) THEN
                  v_nom_cent_acop.extend;
                  v_nom_cent_acop(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 5) THEN
                  v_val_emai.extend;
                  v_val_emai(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
                inicio := inicio + longitud;
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_comp_tran.count
    LOOP
      BEGIN
        INSERT INTO sto_centr_acopi
          (cod_comp_tran,
           cod_cent_acop,
           nom_comp_tran,
           nom_cent_acop,
           val_emai)
        VALUES
          (v_cod_comp_tran(i),
           v_cod_cent_acop(i),
           v_nom_comp_tran(i),
           v_nom_cent_acop(i),
           v_val_emai(i));
      EXCEPTION
        WHEN dup_val_on_index THEN
          NULL;
      END;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_sat_recep_centr_acopi: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_sat_recep_centr_acopi;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion de
                      cobertura por codigo territorial (COBERT)
  Fecha Creacion    : 03/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_cober_terri
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
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
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_regi IS TABLE OF sto_acopi_cober.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF sto_acopi_cober.cod_zona%TYPE;
    TYPE t_cod_secc IS TABLE OF sto_acopi_cober.cod_secc%TYPE;
    TYPE t_cod_terr IS TABLE OF sto_acopi_cober.cod_terr%TYPE;
    TYPE t_cod_comp_tran IS TABLE OF sto_acopi_cober.cod_comp_tran%TYPE;
    TYPE t_cod_cent_acop IS TABLE OF sto_acopi_cober.cod_cent_acop%TYPE;
    TYPE t_val_flet IS TABLE OF sto_acopi_cober.val_flet%TYPE;
    TYPE t_cod_buzo IS TABLE OF sto_acopi_cober.cod_buzo%TYPE;
    TYPE t_dir_buzo IS TABLE OF sto_acopi_cober.dir_buzo%TYPE;
    TYPE t_tel_buzo IS TABLE OF sto_acopi_cober.tel_buzo%TYPE;
    TYPE t_cel_buzo IS TABLE OF sto_acopi_cober.cel_buzo%TYPE;
    TYPE t_des_buzo IS TABLE OF sto_acopi_cober.des_buzo%TYPE;
  
    v_cod_regi      t_cod_regi := t_cod_regi();
    v_cod_zona      t_cod_zona := t_cod_zona();
    v_cod_secc      t_cod_secc := t_cod_secc();
    v_cod_terr      t_cod_terr := t_cod_terr();
    v_cod_comp_tran t_cod_comp_tran := t_cod_comp_tran();
    v_cod_cent_acop t_cod_cent_acop := t_cod_cent_acop();
    v_val_flet      t_val_flet := t_val_flet();
    v_cod_buzo      t_cod_buzo := t_cod_buzo();
    v_dir_buzo      t_dir_buzo := t_dir_buzo();
    v_tel_buzo      t_tel_buzo := t_tel_buzo();
    v_cel_buzo      t_cel_buzo := t_cel_buzo();
    v_des_buzo      t_des_buzo := t_des_buzo();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
  BEGIN
  
    EXECUTE IMMEDIATE 'TRUNCATE TABLE sto_acopi_cober';
  
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
              
                IF (posicion = 1) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_cod_secc.extend;
                  v_cod_secc(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_cod_terr.extend;
                  v_cod_terr(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_cod_comp_tran.extend;
                  v_cod_comp_tran(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 6) THEN
                  v_cod_cent_acop.extend;
                  v_cod_cent_acop(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 7) THEN
                  v_val_flet.extend;
                  v_val_flet(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_cod_buzo.extend;
                  v_cod_buzo(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_dir_buzo.extend;
                  v_dir_buzo(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 10) THEN
                  v_tel_buzo.extend;
                  v_tel_buzo(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 11) THEN
                  v_cel_buzo.extend;
                  v_cel_buzo(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 12) THEN
                  v_des_buzo.extend;
                  v_des_buzo(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
                inicio := inicio + longitud;
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_regi.count
    LOOP
      BEGIN
        INSERT INTO sto_acopi_cober
          (cod_regi,
           cod_zona,
           cod_secc,
           cod_terr,
           cod_comp_tran,
           cod_cent_acop,
           val_flet,
           cod_buzo,
           dir_buzo,
           tel_buzo,
           cel_buzo,
           des_buzo)
        VALUES
          (v_cod_regi(i),
           v_cod_zona(i),
           v_cod_secc(i),
           v_cod_terr(i),
           v_cod_comp_tran(i),
           v_cod_cent_acop(i),
           v_val_flet(i),
           v_cod_buzo(i),
           v_dir_buzo(i),
           v_tel_buzo(i),
           v_cel_buzo(i),
           v_des_buzo(i));
      
      EXCEPTION
        WHEN dup_val_on_index THEN
          NULL;
      END;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_sat_recep_cober_terri: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_sat_recep_cober_terri;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de informacion de
                      archivo de División de Armado por CDP
  Fecha Creacion    : 20/10/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_divis_armad
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
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
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF VARCHAR2(4);
    TYPE t_cod_comp IS TABLE OF VARCHAR2(1);
    TYPE t_cod_regi IS TABLE OF zon_regio.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF zon_zona.cod_zona%TYPE;
    TYPE t_cod_secc IS TABLE OF zon_secci.cod_secc%TYPE;
    TYPE t_cod_cdp IS TABLE OF VARCHAR2(2);
  
    v_cod_pais t_cod_pais := t_cod_pais();
    v_cod_comp t_cod_comp := t_cod_comp();
    v_cod_regi t_cod_regi := t_cod_regi();
    v_cod_zona t_cod_zona := t_cod_zona();
    v_cod_secc t_cod_secc := t_cod_secc();
    v_cod_cdp  t_cod_cdp := t_cod_cdp();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(1000);
    lspais          VARCHAR2(100);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
    v_numlinea   NUMBER := 0;
    v_oidzona    NUMBER := 0;
    v_oidseccion NUMBER := 0;
    v_oidregion  NUMBER := 0;
  
  BEGIN
  
    DELETE FROM ape_confi_liafp_detal;
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
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_cod_secc.extend;
                  v_cod_secc(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_cod_cdp.extend;
                  v_cod_cdp(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
              
                inicio := inicio + longitud + 1;
              
              END LOOP;
            
            END IF;
          
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    SELECT nvl(MAX(a.num_line), 0)
      INTO v_numlinea
      FROM ape_confi_liafp_detal a;
  
    FOR i IN 1 .. v_cod_regi.count
    LOOP
    
      v_numlinea := v_numlinea + 1;
    
      SELECT MAX(cod_pais)
        INTO lspais
        FROM bas_pais_compa
       WHERE cod_comp = '0' || decode(v_cod_comp(i), '1', '2', '1')
         AND cod_pais_ocr = substr(v_cod_pais(i), 1, 2);
    
      IF lspais = pscodigopais THEN
      
        BEGIN
        
          SELECT oid_zona
            INTO v_oidzona
            FROM zon_zona
           WHERE cod_zona = CASE
                   WHEN length(TRIM(v_cod_zona(i))) = 2 THEN
                    v_cod_regi(i) || TRIM(v_cod_zona(i))
                   ELSE
                    v_cod_zona(i)
                 END;
        
        EXCEPTION
          WHEN no_data_found THEN
            v_oidzona := NULL;
        END;
      
        BEGIN
        
          SELECT oid_secc
            INTO v_oidseccion
            FROM zon_zona  a,
                 zon_secci b
           WHERE a.oid_zona = b.zzon_oid_zona
             AND cod_zona = v_cod_zona(i)
             AND b.cod_secc = v_cod_secc(i);
        
        EXCEPTION
          WHEN no_data_found THEN
            v_oidseccion := NULL;
        END;
      
        BEGIN
          SELECT oid_regi
            INTO v_oidregion
            FROM zon_regio
           WHERE cod_regi = v_cod_regi(i);
        EXCEPTION
          WHEN no_data_found THEN
            v_oidregion := NULL;
        END;
      
        BEGIN
          INSERT INTO ape_confi_liafp_detal
            (oid_conf_lafp_deta,
             num_line,
             liac_oid_conf_lafp_cabe,
             zzon_oid_zona,
             zscc_oid_secc,
             zorg_oid_regi)
          VALUES
            (ape_liad_seq.nextval,
             v_numlinea,
             to_number(v_cod_cdp(i)),
             v_oidzona,
             v_oidseccion,
             v_oidregion);
        EXCEPTION
          WHEN OTHERS THEN
            continue;
        END;
      
      END IF;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_sat_recep_divis_armad: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_sat_recep_divis_armad;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Orden de Impresion
                      generado por APESAT
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_orden_impre
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
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
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_tip_regi IS TABLE OF sat_orden_impre_apsat.tip_regi%TYPE;
    TYPE t_cod_regi IS TABLE OF sat_orden_impre_apsat.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF sat_orden_impre_apsat.cod_zona%TYPE;
    TYPE t_cod_secc IS TABLE OF sat_orden_impre_apsat.cod_secc%TYPE;
    TYPE t_cod_terr IS TABLE OF sat_orden_impre_apsat.cod_terr%TYPE;
    TYPE t_cod_clie IS TABLE OF sat_orden_impre_apsat.cod_clie%TYPE;
    TYPE t_val_prio IS TABLE OF sat_orden_impre_apsat.val_prio%TYPE;
    TYPE t_ide_repa IS TABLE OF VARCHAR2(4);
  
    v_tip_regi t_tip_regi := t_tip_regi();
    v_cod_regi t_cod_regi := t_cod_regi();
    v_cod_zona t_cod_zona := t_cod_zona();
    v_cod_secc t_cod_secc := t_cod_secc();
    v_cod_terr t_cod_terr := t_cod_terr();
    v_cod_clie t_cod_clie := t_cod_clie();
    v_val_prio t_val_prio := t_val_prio();
    v_ide_repa t_ide_repa := t_ide_repa();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
    nroregistros NUMBER;
  
  BEGIN
  
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
              
                IF (posicion = 1) THEN
                  v_tip_regi.extend;
                  v_tip_regi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_cod_secc.extend;
                  v_cod_secc(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_cod_terr.extend;
                  v_cod_terr(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 7) THEN
                  v_val_prio.extend;
                  v_val_prio(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_ide_repa.extend;
                  v_ide_repa(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
                inicio := inicio + longitud;
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_tip_regi.count
    LOOP
      BEGIN
      
        --Buscar registro existente
        SELECT COUNT(*)
          INTO nroregistros
          FROM sat_orden_impre_apsat
         WHERE tip_regi = v_tip_regi(i)
           AND cod_regi = v_cod_regi(i)
           AND cod_zona = v_cod_zona(i)
           AND cod_secc = v_cod_secc(i)
           AND cod_terr = v_cod_terr(i)
           AND cod_clie = v_cod_clie(i);
      
        IF nroregistros > 0 THEN
        
          --Mandamos al historico
          INSERT INTO sat_orden_impre_apsat_histo
            (tip_regi,
             cod_regi,
             cod_zona,
             cod_secc,
             cod_terr,
             cod_clie,
             val_prio,
             fec_carg)
            SELECT tip_regi,
                   cod_regi,
                   cod_zona,
                   cod_secc,
                   cod_terr,
                   cod_clie,
                   val_prio,
                   SYSDATE fec_carg
              FROM sat_orden_impre_apsat
             WHERE tip_regi = v_tip_regi(i)
               AND cod_regi = v_cod_regi(i)
               AND cod_zona = v_cod_zona(i)
               AND cod_secc = v_cod_secc(i)
               AND cod_terr = v_cod_terr(i)
               AND cod_clie = v_cod_clie(i);
        
          --eliminamos el registro
          DELETE FROM sat_orden_impre_apsat
           WHERE tip_regi = v_tip_regi(i)
             AND cod_regi = v_cod_regi(i)
             AND cod_zona = v_cod_zona(i)
             AND cod_secc = v_cod_secc(i)
             AND cod_terr = v_cod_terr(i)
             AND cod_clie = v_cod_clie(i);
        END IF;
      
        INSERT INTO sat_orden_impre_apsat
          (tip_regi,
           cod_regi,
           cod_zona,
           cod_secc,
           cod_terr,
           cod_clie,
           val_prio,
           fec_carg)
        VALUES
          (v_tip_regi(i),
           v_cod_regi(i),
           v_cod_zona(i),
           v_cod_secc(i),
           v_cod_terr(i),
           v_cod_clie(i),
           v_val_prio(i),
           SYSDATE);
      
      EXCEPTION
        WHEN dup_val_on_index THEN
          NULL;
      END;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_sat_recep_orden_impre: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_sat_recep_orden_impre;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepción de Parametrización para
                      Cálculo de Fecha de Entrega Exacta
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_param_calcu
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    TYPE t_cod_camp IS TABLE OF sat_param_calcu_fecee.cod_camp%TYPE;
    TYPE t_cod_regi IS TABLE OF sat_param_calcu_fecee.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF sat_param_calcu_fecee.cod_zona%TYPE;
    TYPE t_cod_secc IS TABLE OF sat_param_calcu_fecee.cod_secc%TYPE;
    TYPE t_cod_terr IS TABLE OF sat_param_calcu_fecee.cod_terr%TYPE;
    TYPE t_tip_cons IS TABLE OF sat_param_calcu_fecee.tip_cons%TYPE;
    TYPE t_dia_fact IS TABLE OF sat_param_calcu_fecee.dia_fact%TYPE;
    TYPE t_val_dias IS TABLE OF sat_param_calcu_fecee.val_dias_desf%TYPE;
  
    v_cod_camp t_cod_camp := t_cod_camp();
    v_cod_regi t_cod_regi := t_cod_regi();
    v_cod_zona t_cod_zona := t_cod_zona();
    v_cod_secc t_cod_secc := t_cod_secc();
    v_cod_terr t_cod_terr := t_cod_terr();
    v_tip_cons t_tip_cons := t_tip_cons();
    v_dia_fact t_dia_fact := t_dia_fact();
    v_val_dias t_val_dias := t_val_dias();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
  
    v_handle utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i BINARY_INTEGER := 0;
  
    t_estructura t_all_estru_archi;
  
  BEGIN
    
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 t_estructura);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i := i + 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
          FOR j IN t_estructura.first .. t_estructura.last
          LOOP
            --dbms_output.put_line(t_estructura(j).pos_camp);
          
            IF j = 1 THEN
              v_cod_camp.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_cod_camp(i));
            ELSIF j = 2 THEN
              v_cod_regi.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_cod_regi(i));
            
            ELSIF j = 3 THEN
              v_cod_zona.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_cod_zona(i));
            
            ELSIF j = 4 THEN
              v_cod_secc.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_cod_secc(i));
            
            ELSIF j = 5 THEN
              v_cod_terr.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_cod_terr(i));
            
            ELSIF j = 6 THEN
              v_tip_cons.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_tip_cons(i));
            
            ELSIF j = 7 THEN
              v_dia_fact.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_dia_fact(i));
            
            ELSIF j = 8 THEN
              v_val_dias.extend;
              gen_pkg_inter_archi.gen_pr_read_campo(lslinea,
                                                    t_estructura(j),
                                                    v_val_dias(i));
            
            END IF;
          END LOOP;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    INSERT INTO sat_param_calcu_fecee_histo
          (cod_regi,
           cod_zona,
           cod_secc,
           cod_terr,
           tip_cons,
           dia_fact,
           val_dias_desf,
           fec_carg,
           cod_camp)
      SELECT cod_regi,
             cod_zona,
             cod_secc,
             cod_terr,
             tip_cons,
             dia_fact,
             val_dias_desf,
             fec_carg,
             cod_camp
        FROM sat_param_calcu_fecee;
  
    DELETE sat_param_calcu_fecee;
  
    FORALL i IN 1 .. v_cod_regi.count    
      INSERT INTO sat_param_calcu_fecee
        (cod_regi,
                   cod_zona,
                   cod_secc,
                   cod_terr,
                   tip_cons,
                   dia_fact,
                   val_dias_desf,
         fec_carg,
         cod_camp)
      VALUES
        (v_cod_regi(i),
         v_cod_zona(i),
         v_cod_secc(i),
         v_cod_terr(i),
         v_tip_cons(i),
         v_dia_fact(i),
         v_val_dias(i),
                   SYSDATE,
         v_cod_camp(i));
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_SAT_RECEP_PARAM_CALCU: ' ||
                              ls_sqlerrm || lslinea);
    
  END int_pr_sat_recep_param_calcu;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Excepciones para Fecha
                      de Entrega Exacta
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_excep_fecha
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
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
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_fec_exce IS TABLE OF sat_execp_fecee.fec_exce%TYPE;
    TYPE t_cod_regi IS TABLE OF sat_execp_fecee.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF sat_execp_fecee.cod_zona%TYPE;
    TYPE t_cod_secc IS TABLE OF sat_execp_fecee.cod_secc%TYPE;
    TYPE t_tip_cons IS TABLE OF sat_execp_fecee.tip_cons%TYPE;
    TYPE t_val_dias IS TABLE OF sat_execp_fecee.val_dias_desf%TYPE;
  
    v_fec_exce t_fec_exce := t_fec_exce();
    v_cod_regi t_cod_regi := t_cod_regi();
    v_cod_zona t_cod_zona := t_cod_zona();
    v_cod_secc t_cod_secc := t_cod_secc();
    v_tip_cons t_tip_cons := t_tip_cons();
    v_val_dias t_val_dias := t_val_dias();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
  BEGIN
  
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
              
                IF (posicion = 1) THEN
                  v_fec_exce.extend;
                  v_fec_exce(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_cod_secc.extend;
                  v_cod_secc(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_tip_cons.extend;
                  v_tip_cons(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_val_dias.extend;
                  v_val_dias(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
                inicio := inicio + longitud;
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_fec_exce.count
    LOOP
      BEGIN
        INSERT INTO sat_execp_fecee
          (fec_exce,
           cod_regi,
           cod_zona,
           cod_secc,
           tip_cons,
           val_dias_desf)
        VALUES
          (v_fec_exce(i),
           v_cod_regi(i),
           v_cod_zona(i),
           v_cod_secc(i),
           v_tip_cons(i),
           v_val_dias(i));
      
      EXCEPTION
        WHEN dup_val_on_index THEN
          NULL;
      END;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_sat_recep_excep_fecha: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_sat_recep_excep_fecha;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Seguimiento del Pedido
  
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_segui_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
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
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_clie IS TABLE OF sat_segui_pedid.cod_clie%TYPE;
    TYPE t_nro_pedi IS TABLE OF sat_segui_pedid.nro_pedi%TYPE;
    TYPE t_fec_desp IS TABLE OF sat_segui_pedid.nro_pedi%TYPE;
    TYPE t_hor_desp IS TABLE OF sat_segui_pedid.hor_desp%TYPE;
  
    v_cod_clie t_cod_clie := t_cod_clie();
    v_nro_pedi t_nro_pedi := t_nro_pedi();
    v_fec_desp t_fec_desp := t_fec_desp();
    v_hor_desp t_hor_desp := t_hor_desp();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
  BEGIN
  
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
              
                IF (posicion = 1) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_nro_pedi.extend;
                  v_nro_pedi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_fec_desp.extend;
                  v_fec_desp(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_hor_desp.extend;
                  v_hor_desp(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
                inicio := inicio + longitud;
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_clie.count
    LOOP
      BEGIN
        INSERT INTO sat_segui_pedid
          (cod_clie,
           nro_pedi,
           fec_desp,
           hor_desp)
        VALUES
          (v_cod_clie(i),
           v_nro_pedi(i),
           REPLACE(v_fec_desp(i), '-', ''),
           v_hor_desp(i));
      
      EXCEPTION
        WHEN dup_val_on_index THEN
          NULL;
      END;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_sat_recep_segui_pedid: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_sat_recep_segui_pedid;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Impresion Boletas de
                      Entrega
  Fecha Creacion    : 22/01/2013
  Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_sat_recep_impre_bolet
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
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
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_clie IS TABLE OF sat_impre_bolet_entre.cod_clie%TYPE;
    TYPE t_cod_regi IS TABLE OF sat_impre_bolet_entre.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF sat_impre_bolet_entre.cod_zona%TYPE;
    TYPE t_lin_emba IS TABLE OF sat_impre_bolet_entre.lin_emba%TYPE;
    TYPE t_tot_caja IS TABLE OF sat_impre_bolet_entre.tot_caja%TYPE;
    TYPE t_tip_pedi IS TABLE OF sat_impre_bolet_entre.tip_pedi%TYPE;
    TYPE t_nro_pedi IS TABLE OF sat_impre_bolet_entre.nro_pedi%TYPE;
    TYPE t_fec_fact IS TABLE OF sat_impre_bolet_entre.fec_fact%TYPE;
    TYPE t_cam_fact IS TABLE OF sat_impre_bolet_entre.cam_fact%TYPE;
  
    v_cod_clie t_cod_clie := t_cod_clie();
    v_cod_regi t_cod_regi := t_cod_regi();
    v_cod_zona t_cod_zona := t_cod_zona();
    v_lin_emba t_lin_emba := t_lin_emba();
    v_tot_caja t_tot_caja := t_tot_caja();
    v_tip_pedi t_tip_pedi := t_tip_pedi();
    v_nro_pedi t_nro_pedi := t_nro_pedi();
    v_fec_fact t_fec_fact := t_fec_fact();
    v_cam_fact t_cam_fact := t_cam_fact();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
  BEGIN
  
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
              
                IF (posicion = 1) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_lin_emba.extend;
                  v_lin_emba(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_tot_caja.extend;
                  v_tot_caja(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_tip_pedi.extend;
                  v_tip_pedi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 7) THEN
                  v_nro_pedi.extend;
                  v_nro_pedi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_fec_fact.extend;
                  v_fec_fact(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_cam_fact.extend;
                  v_cam_fact(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
                inicio := inicio + longitud;
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_clie.count
    LOOP
      BEGIN
        INSERT INTO sat_impre_bolet_entre
          (cod_clie,
           cod_regi,
           cod_zona,
           lin_emba,
           tot_caja,
           tip_pedi,
           nro_pedi,
           fec_fact,
           cam_fact)
        VALUES
          (v_cod_clie(i),
           v_cod_regi(i),
           v_cod_zona(i),
           v_lin_emba(i),
           v_tot_caja(i),
           v_tip_pedi(i),
           v_nro_pedi(i),
           v_fec_fact(i),
           v_cam_fact(i));
      
      EXCEPTION
        WHEN dup_val_on_index THEN
          NULL;
      END;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_sat_recep_impre_bolet: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_sat_recep_impre_bolet;
END int_pkg_sat;
/
