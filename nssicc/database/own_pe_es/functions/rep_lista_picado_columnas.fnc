CREATE OR REPLACE FUNCTION REP_LISTA_PICADO_COLUMNAS (
    str_oid_idioma IN NUMBER,
    str_oid_consolidado IN NUMBER,
    str_oid_centro_dist IN NUMBER,
    str_oid_linea_armado IN NUMBER,
    str_oid_periodo IN NUMBER,
    str_fec_crea_desde IN VARCHAR,
    str_fec_crea_hasta IN VARCHAR,
    formato_fecha IN VARCHAR,
    str_territorios IN VARCHAR
)
RETURN OBJ_REP_LISTA_PICADO_TABLE PIPELINED
IS
    -- Cursor para guardar los datos de lista picado recuperados
    CURSOR cur_lista IS
        SELECT   Pq_Apl_Aux.valor_gen_i18n_sicc (str_oid_idioma, lpc.liar_oid_line_arma, 'APE_LINEA_ARMAD') desc_la,
                 cli.cod_clie,
                 CASE WHEN CD.IND_IMPR_DATO_BASI_CLIE = 1
                     THEN cli.val_ape1 || ' ' || cli.val_ape2 || ', ' || cli.val_nom1 || ' ' || cli.val_nom2
                     ELSE ''
                 END AS nom_clie,
                 (SELECT zr.COD_REGI||zz.COD_ZONA||zs.COD_SECC||zt.cod_terr
                    FROM ZON_TERRI zt, ZON_TERRI_ADMIN zta, ZON_SECCI zs, ZON_ZONA zz, ZON_REGIO zr
                   WHERE zt.oid_terr = psc.terr_oid_terr
                     AND psc.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                     AND zta.TERR_OID_TERR = zt.OID_TERR
                     AND zta.ZSCC_OID_SECC = zs.OID_SECC
                     AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                     AND zz.ZORG_OID_REGI = zr.OID_REGI) cod_terr,
                 lpc.val_secu, lpc.num_tota_caja, SUBSTR(pc.cod_peri,1,4) || '-' || SUBSTR(pc.cod_peri,5,2) AS cod_peri,
                 lpc.val_text_cheq, lpc.val_text_prim_pedi,
                 SUBSTR(psc.val_nume_soli,2,8) cod_bar, lpc.fec_factu,
                 mcdd.num_anaq, TO_NUMBER(lpd.num_unid_prod) AS num_unid_prod, TO_NUMBER(lpd.num_caja) AS num_caja,
                 SUBSTR(Pq_Apl_Aux.valor_gen_i18n_sicc (str_oid_idioma, prod.oid_prod, 'MAE_PRODU'),1,32) desc_prod,
                 COUNT (DISTINCT prod_oid_prod) OVER (PARTITION BY lpc.oid_list_pica_cabe, lpd.num_caja) tot_prod_caja,
                 SUM (num_unid_prod) OVER (PARTITION BY lpc.oid_list_pica_cabe, lpd.num_caja) num_unid_tota_caja,
                 SUM (num_unid_prod) OVER (PARTITION BY lpc.oid_list_pica_cabe
                                               ORDER BY lpc.oid_list_pica_cabe) num_unid_tota_list,
                 ROW_NUMBER() OVER (PARTITION BY lpc.oid_list_pica_cabe, lpd.num_caja
                                        ORDER BY lpc.oid_list_pica_cabe, lpd.num_caja, mcdd.num_anaq) AS num_linea_caja,
                 COUNT(*) OVER (PARTITION BY lpc.oid_list_pica_cabe, lpd.num_caja
                                    ORDER BY lpc.oid_list_pica_cabe, lpd.num_caja) AS total_lineas_caja,
                 ROW_NUMBER() OVER (PARTITION BY lpc.oid_list_pica_cabe
                                        ORDER BY lpc.oid_list_pica_cabe, lpd.num_caja, mcdd.num_anaq) AS num_linea_lista,
                 COUNT(*) OVER (PARTITION BY lpc.oid_list_pica_cabe
                                    ORDER BY lpc.oid_list_pica_cabe) AS total_lineas_lista,
                 lpc.oid_list_pica_cabe,
                 PQ_APL_AUX.valor_gen_i18n_sicc(str_oid_idioma, lpd.tcem_oid_tipo_caja_emba, 'APE_TIPO_CAJA_EMBAL') AS desc_tipo_caja,
                 LPAD(etiq.num_secu, linea.num_long_nume_etiq, '0') AS num_secu_etiq,
                 NVL(lpd.num_idzo,0) AS num_idzo
            FROM APE_LISTA_PICAD_CABEC lpc, PED_SOLIC_CABEC psc, APE_LISTA_PICAD_DETAL lpd,
                 APE_MAPA_CENTR_DISTR_DETAL mcdd, CRA_PERIO peri, MAE_PRODU prod, MAE_CLIEN cli, APE_ETIQU etiq,
                 APP_CONFI_CENTR_DISTR cd, APE_LINEA_ARMAD linea, SEG_PERIO_CORPO pc
           WHERE lpc.soca_oid_soli_cabe = psc.oid_soli_cabe
             -- Filtro por los parametros recibidos
             AND (str_oid_centro_dist = 0 OR lpc.ccdi_oid_conf_cent_dist = str_oid_centro_dist)
             AND (str_oid_linea_armado = 0 OR lpc.liar_oid_line_arma = str_oid_linea_armado)
             AND (str_oid_periodo = 0 OR lpc.perd_oid_peri = str_oid_periodo)
             AND (str_fec_crea_desde = '01/01/1900' OR lpc.fec_crea >= TO_DATE(str_fec_crea_desde, formato_fecha))
             AND (str_fec_crea_hasta = '01/01/1900' OR lpc.fec_crea <= TO_DATE(str_fec_crea_hasta, formato_fecha))
             AND (str_territorios = '0'
                  OR INSTR(str_territorios, TO_CHAR(psc.terr_oid_terr) || ',', 1, 1) != 0
                  OR INSTR(str_territorios, ',' || TO_CHAR(psc.terr_oid_terr), 1, 1) != 0)
             AND (str_oid_consolidado  = 0 OR psc.oid_soli_cabe = str_oid_consolidado)
             -- Fin filtro parametros
             AND lpc.oid_list_pica_cabe = lpd.lpca_oid_list_pica_cabe
             AND lpc.perd_oid_peri = peri.oid_peri
             AND lpd.prod_oid_prod = prod.oid_prod
             AND lpc.clie_oid_clie = cli.oid_clie
             AND lpd.mcdd_oid_mapa_cent_dist_deta = mcdd.oid_mapa_cent_dist_deta
             AND lpd.lpca_oid_list_pica_cabe = etiq.lpca_oid_list_pica_cabe
             AND lpd.num_caja = etiq.num_caja
             AND lpc.ccdi_oid_conf_cent_dist = cd.oid_conf_cent_dist
             AND lpc.liar_oid_line_arma = linea.oid_line_arma
             AND peri.peri_oid_peri = pc.oid_peri
        ORDER BY lpc.oid_list_pica_cabe, lpd.num_caja, num_anaq;

    -- Tipos de datos auxiliares
    TYPE TABLATEMP IS TABLE OF OBJ_REP_LISTA_PICADO;

    -- Variables auxiliares
    tabla_temp_caja TABLATEMP := TABLATEMP();
    tabla_temp_lista OBJ_REP_LISTA_PICADO_TABLE := OBJ_REP_LISTA_PICADO_TABLE();
    contador_pags NUMBER := 0;
    num_lineas_pags_ant NUMBER := 0;

    -- Numero de líneas por página de la lista
    NUMERO_LINEAS_PAGINA CONSTANT NUMBER := 28;

    -- Ultima zona de armado leida
    -- Se pone en cero, concordando con el NVL(num_idzo,0) de la query
    -- para que las listas que lo tengan en null puedan seguir siendo impresas como antes.
    ultima_zona NUMBER(2) := 0;

    -- Variable auxiliar para donde sea necesaria
    aux NUMBER := NULL;
    firstElement BOOLEAN:=TRUE;
    ultima_secuencia NUMBER:=0;
BEGIN
    FOR i IN cur_lista LOOP
        BEGIN
         IF(firstElement) THEN
            ultima_zona:=i.num_idzo;
            firstElement:=FALSE;
            ultima_secuencia:=i.num_secu_etiq;
         ELSE
           IF(ultima_secuencia<> i.num_secu_etiq) THEN
              ultima_zona:=i.num_idzo;
              ultima_secuencia:=i.num_secu_etiq;
           END IF;

         END IF;


          -- Verifico si hubo cambio de zona; en tal caso, agrego linea divisoria
            IF i.num_idzo <> ultima_zona --AND i.num_linea_lista <> i.total_lineas_lista AND i.num_linea_caja <> i.total_lineas_caja
            THEN

                ultima_zona:=i.num_idzo;
                tabla_temp_caja.EXTEND;
                tabla_temp_caja(tabla_temp_caja.COUNT) := OBJ_REP_LISTA_PICADO(i.desc_la,i.cod_clie,i.nom_clie,i.cod_terr,i.val_secu,
                                                                               i.num_idzo,i.cod_peri,i.val_text_cheq,i.val_text_prim_pedi,
                                                                               i.cod_bar,i.fec_factu,'-----',NULL,i.num_caja,'--------------------------------',
                                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

            END IF;

            tabla_temp_caja.EXTEND;
            tabla_temp_caja(tabla_temp_caja.COUNT) := OBJ_REP_LISTA_PICADO(i.desc_la, i.cod_clie, i.nom_clie, i.cod_terr, i.val_secu,
                                                                           i.num_tota_caja, i.cod_peri, i.val_text_cheq, i.val_text_prim_pedi,
                                                                           i.cod_bar, i.fec_factu, i.num_anaq, i.num_unid_prod, i.num_caja,
                                                                           i.desc_prod, i.tot_prod_caja, i.num_unid_tota_caja, i.num_unid_tota_list,
                                                                           i.num_linea_caja, i.total_lineas_caja, i.num_linea_lista,
                                                                           i.total_lineas_lista, i.oid_list_pica_cabe, i.desc_tipo_caja, i.num_secu_etiq);

            -- Verifico si llegue al final de una caja
            IF i.num_linea_caja = i.total_lineas_caja THEN
                tabla_temp_caja.EXTEND(4);
                tabla_temp_caja(tabla_temp_caja.COUNT - 3) := OBJ_REP_LISTA_PICADO(i.desc_la,i.cod_clie,i.nom_clie,i.cod_terr,i.val_secu,i.num_tota_caja,i.cod_peri,i.val_text_cheq,i.val_text_prim_pedi,i.cod_bar,i.fec_factu,'*****',NULL,i.num_caja,'**** FIN ****',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
                tabla_temp_caja(tabla_temp_caja.COUNT - 2) := OBJ_REP_LISTA_PICADO(i.desc_la,i.cod_clie,i.nom_clie,i.cod_terr,i.val_secu,i.num_tota_caja,i.cod_peri,i.val_text_cheq,i.val_text_prim_pedi,i.cod_bar,i.fec_factu,'*****',NULL,i.num_caja,'<-- Totales',i.tot_prod_caja,i.num_unid_tota_caja,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
                tabla_temp_caja(tabla_temp_caja.COUNT - 1) := OBJ_REP_LISTA_PICADO(i.desc_la,i.cod_clie,i.nom_clie,i.cod_terr,i.val_secu,i.num_tota_caja,i.cod_peri,i.val_text_cheq,i.val_text_prim_pedi,i.cod_bar,i.fec_factu,'*****',NULL,i.num_caja,'EMBALADO POR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
                tabla_temp_caja(tabla_temp_caja.COUNT) := OBJ_REP_LISTA_PICADO(i.desc_la,i.cod_clie,i.nom_clie,i.cod_terr,i.val_secu,i.num_tota_caja,i.cod_peri,i.val_text_cheq,i.val_text_prim_pedi,i.cod_bar,i.fec_factu,'*****',NULL,i.num_caja,'VERIFICADO POR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

                -- Armo la estructura de dos columnas para las lineas de la caja actual
                FOR j IN 1..tabla_temp_caja.COUNT LOOP
                    BEGIN
                        -- Verifico si el elemento actual va en la columna izq. o der.
                        aux := TRUNC((j - 1) / NUMERO_LINEAS_PAGINA);
                        IF aux = 2 * TRUNC(aux / 2) THEN
                            -- Izquierda
                            tabla_temp_lista.EXTEND;
                            tabla_temp_lista(tabla_temp_lista.COUNT) :=
                                OBJ_REP_LISTA_PICADO_COLUMNAS(tabla_temp_caja(j).desc_la, tabla_temp_caja(j).cod_clie, tabla_temp_caja(j).nom_clie,
                                    tabla_temp_caja(j).cod_terr, tabla_temp_caja(j).val_secu, tabla_temp_caja(j).num_tota_caja, tabla_temp_caja(j).cod_peri,
                                    tabla_temp_caja(j).val_text_cheq, tabla_temp_caja(j).val_text_prim_pedi, tabla_temp_caja(j).cod_bar, tabla_temp_caja(j).fec_factu,
                                    tabla_temp_caja(j).num_caja, tabla_temp_caja(j).tot_prod_caja, tabla_temp_caja(j).num_unid_tota_caja, tabla_temp_caja(j).num_unid_tota_list,
                                    tabla_temp_caja(j).desc_tipo_caja,tabla_temp_caja(j).num_secu_etiq,
                                    tabla_temp_caja(j).num_anaq, TO_CHAR(tabla_temp_caja(j).num_unid_prod), TO_CHAR(tabla_temp_caja(j).num_caja), tabla_temp_caja(j).desc_prod,
                                    NULL, NULL, NULL, NULL, NULL, NULL);

                            -- Asigno el numero de pagina
                            aux := contador_pags + CEIL( j / (NUMERO_LINEAS_PAGINA * 2) );
                            tabla_temp_lista(tabla_temp_lista.COUNT).num_pagi_list := aux;

                            --
                            IF tabla_temp_caja(j).desc_prod = '<-- Totales' THEN
                                tabla_temp_lista(tabla_temp_lista.COUNT).num_unid_prod_1 := tabla_temp_caja(j).num_unid_tota_caja;
                                tabla_temp_lista(tabla_temp_lista.COUNT).num_caja_1 := tabla_temp_caja(j).tot_prod_caja;
                            ELSE
                                tabla_temp_lista(tabla_temp_lista.COUNT).num_unid_prod_1 := tabla_temp_caja(j).num_unid_prod;
                                -- Lineas de fin, embalado por y verificado por
                                IF tabla_temp_caja(j).num_anaq = '*****' THEN
                                    tabla_temp_lista(tabla_temp_lista.COUNT).num_caja_1 := NULL;
                                -- Linea de cambio de zona de armado
                                ELSIF tabla_temp_caja(j).num_anaq = '-----' THEN
                                    tabla_temp_lista(tabla_temp_lista.COUNT).num_caja_1 := '---';
                                    tabla_temp_lista(tabla_temp_lista.COUNT).num_unid_prod_1 := '------';
                                -- Todas las lineas de detalle
                                ELSE
                                    tabla_temp_lista(tabla_temp_lista.COUNT).num_caja_1 := tabla_temp_caja(j).num_caja;
                                END IF;
                            END IF;
                        ELSE
                            -- Derecha
                            aux := num_lineas_pags_ant + j - NUMERO_LINEAS_PAGINA * TRUNC((j + NUMERO_LINEAS_PAGINA - 1)/(NUMERO_LINEAS_PAGINA * 2));
                            tabla_temp_lista(aux).num_anaq_2 := tabla_temp_caja(j).num_anaq;
                            tabla_temp_lista(aux).desc_prod_2 := tabla_temp_caja(j).desc_prod;
                            IF tabla_temp_caja(j).desc_prod = '<-- Totales' THEN
                                tabla_temp_lista(aux).num_unid_prod_2 := tabla_temp_caja(j).num_unid_tota_caja;
                                tabla_temp_lista(aux).num_caja_2 := tabla_temp_caja(j).tot_prod_caja;
                            ELSE
                                tabla_temp_lista(aux).num_unid_prod_2 := tabla_temp_caja(j).num_unid_prod;
                                -- Lineas de fin, embalado por y verificado por
                                IF tabla_temp_caja(j).num_anaq = '*****' THEN
                                    tabla_temp_lista(aux).num_caja_2 := NULL;
                                -- Linea de cambio de zona de armado
                                ELSIF tabla_temp_caja(j).num_anaq = '-----' THEN
                                    tabla_temp_lista(aux).num_caja_2 := '---';
                                    tabla_temp_lista(aux).num_unid_prod_2 := '------';
                                -- Todas las lineas de detalle
                                ELSE
                                    tabla_temp_lista(aux).num_caja_2 := tabla_temp_caja(j).num_caja;
                                END IF;
                            END IF;
                        END IF;
                    END;
                END LOOP;
                contador_pags := tabla_temp_lista(tabla_temp_lista.COUNT).num_pagi_list;
                num_lineas_pags_ant := tabla_temp_lista.COUNT;
                tabla_temp_caja.trim(tabla_temp_caja.COUNT);
            END IF;

            -- Verifico si llegue al final de una lista
            IF i.num_linea_lista = i.total_lineas_lista THEN
                FOR k IN 1..tabla_temp_lista.COUNT LOOP
                    BEGIN
                        tabla_temp_lista(k).num_tota_pagi_list := contador_pags;
                        PIPE ROW(tabla_temp_lista(k));
                    END;
                END LOOP;
                contador_pags := 0;
                num_lineas_pags_ant := 0;
                tabla_temp_lista.TRIM(tabla_temp_lista.COUNT);
            END IF;

            -- Verifico si hubo cambio de zona; en tal caso, agrego linea divisoria
          /*  IF i.num_idzo <> ultima_zona AND i.num_linea_lista <> i.total_lineas_lista AND i.num_linea_caja <> i.total_lineas_caja
            THEN
                tabla_temp_caja.EXTEND;
                tabla_temp_caja(tabla_temp_caja.COUNT) := OBJ_REP_LISTA_PICADO(i.desc_la,i.cod_clie,i.nom_clie,i.cod_terr,i.val_secu,
                                                                               i.num_tota_caja,i.cod_peri,i.val_text_cheq,i.val_text_prim_pedi,
                                                                               i.cod_bar,i.fec_factu,'-----',NULL,i.num_caja,'--------------------------------' || i.num_idzo,
                                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
              --  ultima_zona:=i.num_idzo;
            END IF;
            ultima_zona := i.num_idzo;*/

        END;
   END LOOP; -- Fin FOR cur_lista

   RETURN;
END;
/

