CREATE OR REPLACE PROCEDURE IMP_PR_GENER_PAQUE_DOCUM_BODES(p_codigoPais VARCHAR2,
                                                           p_codigoPeriodo VARCHAR2,
                                                           p_fechaFacturacion VARCHAR2,
                                                           p_prefijoArchivo VARCHAR2,
                                                           p_directorio VARCHAR2) IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);
CODIGO_CANAL VARCHAR2(10) := 'VD';
CODIGO_MARCA VARCHAR2(10) := 'T';
CURSOR c_boletaDespacho(oidPais NUMBER,
                        oidPeriodo NUMBER, 
                        indicadorEnvioLarissa VARCHAR2,
                        numeroLoteFacturacion NUMBER) IS 
select con.oid_soli_cabe,
       mc.oid_clie,
       mc.cod_clie,
       mc.val_ape1 || ' ' || mc.val_ape2 || ', ' || mc.val_nom1 || ' ' || mc.val_nom2 nom_clie,
       sec.num_secu_fact_diar,
       con.val_nume_soli,
       trim(stv.des_abrv_tipo_via)  || ' ' || trim(mcd.val_nomb_via) || ' ' || trim(mcd.num_ppal) || trim(mcd.val_obse) val_dir1,
       trim('/' from
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 4) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 3) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 2) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(con.pais_oid_pais, mc.oid_clie, 1)) val_dir2,
       trim('/' from
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(mc.oid_clie, 'TF')  || '/' ||
       GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(mc.oid_clie, 'TM')) val_tele,
       zon.oid_zona,
       zse.cod_secc,
       zon.cod_zona,
       ter.cod_terr,
       to_char(con.fec_fact, 'dd/mm/yyyy') fec_emis,
       con.num_unid_aten_tota,
       con.val_tota_paga_loca ,
       (select nvl(sum(num_unid_aten), 0)
        from ped_solic_cabec psc,
             ped_solic_posic psp
        where psc.oid_soli_cabe = psp.soca_oid_soli_cabe
          and psp.ind_dent_fuer_caja_bols != 'C'
          and psp.num_unid_aten > 0
          and psc.soca_oid_soli_cabe = con.oid_soli_cabe
       ) num_unid_fuer_caja_tota
from mae_clien mc,
     mae_clien_direc mcd,
     seg_tipo_via stv,
     ped_solic_cabec con,
     ped_solic_cabec_secue sec,
     zon_zona zon,
     zon_terri_admin zta,
     zon_secci zse,
     zon_terri ter
where mc.oid_clie = mcd.clie_oid_clie
  and mcd.tivi_oid_tipo_via = stv.oid_tipo_via (+)
  and mcd.ind_elim = 0
  and mcd.ind_dire_ppal = 1
  and mc.oid_clie = con.clie_oid_clie
  and con.zzon_oid_zona = zon.oid_zona
  and con.terr_oid_terr = ter.oid_terr
  and con.ztad_oid_terr_admi = zta.oid_terr_admi
  and zta.zscc_oid_secc = zse.oid_secc
  and con.pais_oid_pais = oidPais
  and con.perd_oid_peri = oidPeriodo
  and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
  and con.ind_inte_lari_gene = indicadorEnvioLarissa
  and con.oid_soli_cabe = sec.soca_oid_soli_cabe
  and con.num_unid_aten_tota > 0
  and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
  and exists (
      select null
      from int_lar_tipo_solici_pedido_dis l
      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
  )
  and exists (
      select null
      from int_solic_conso_cabec x
      where x.ind_erro_deud = 2
        and x.ind_admi_cart = 1
        and x.cod_clie = mc.cod_clie
  )
order by sec.num_secu_zona_ruta, 
         sec.num_secu_fact_diar;
r_boletaDespacho c_boletaDespacho%ROWTYPE;
CURSOR c_detalleBoleta(oidConsolidado NUMBER) IS
select psp.cod_posi,
       psp.num_unid_aten,
       decode(psc.ictp_oid_tipo_prog, null, psp.ind_dent_fuer_caja_bols, 'P') ind_dent_fuer_caja_bols,
       mp.cod_sap,
       nvl(psp.val_codi_vent, psp.val_codi_vent_fict) val_codi_vent,
       trim((select val_i18n from gen_i18n_sicc_pais i18n where i18n.attr_enti = 'MAE_PRODU' and i18n.val_oid = mp.oid_prod and i18n.idio_oid_idio = 1)) des_prod
from ped_solic_cabec con,
     ped_solic_cabec psc,
     ped_solic_posic psp,
     mae_produ mp
where con.oid_soli_cabe = psc.soca_oid_soli_cabe
  and psc.oid_soli_cabe = psp.soca_oid_soli_cabe
  and psp.prod_oid_prod = mp.oid_prod
  and con.oid_soli_cabe = oidConsolidado
  and psp.ind_dent_fuer_caja_bols != 'C'
  and psp.num_unid_aten > 0
order by psp.cod_posi;
r_detalleBoleta c_detalleBoleta%ROWTYPE;
-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;
l_textoActual                   VARCHAR2(1000) := '';
l_contadorDetalles              NUMBER := 0;
-- Variables usadas para la inclusion del telefono en la boleta de despacho 
l_indicadorTelefono         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorTelefono');
l_textoTelefono             VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','textoTelefono');
-- Variables usadas para la inclusion del tag de saludo por cumpleaños
l_indicadorCumpleanos       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorCumpleanos');
l_tagCumpleanosApertura     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosApertura');
l_tagCumpleanosCierre       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosCierre');
BEGIN
    -- Obtenemos los OIDs necesarios 
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);
    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN
        BEGIN
            select max(cons.num_lote_fact) 
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;
        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;
        END;
    END IF;    
    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM
    DELETE FROM IMP_PAQUE_DOCUM; 
    OPEN c_boletaDespacho(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP 
    FETCH c_boletaDespacho INTO r_boletaDespacho;
    EXIT WHEN c_boletaDespacho%NOTFOUND;
        BEGIN
            INSERT INTO IMP_PAQUE_DOCUM(
            COR_PADO,
            COD_CLIE,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            l_correlativo, 
            r_boletaDespacho.cod_clie,
            r_boletaDespacho.val_nume_soli,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;
            -- Inicio del paquete         
            l_textoActual := '<pd>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Inicio boleta Despacho 
            l_textoActual := '<frmbd><pbd1>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Inicio cabecera 
            l_textoActual := '<blqcab>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Saludo de cumpleaños 
            IF l_indicadorCumpleanos = 'S' AND IMP_PKG_PROCE_COMPA.IMP_FN_VALID_CUMPL(r_boletaDespacho.cod_clie) != 0 THEN
                l_textoActual := l_tagCumpleanosApertura || l_tagCumpleanosCierre;
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            END IF;
            -- Numero de Secuencia 
            l_textoActual := '<nsec>' || r_boletaDespacho.num_secu_fact_diar || '</nsec>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Chequeo de Pedido  
            l_textoActual := '<chq></chq>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Codigo Cliente 
            l_textoActual := '<ccon>' || r_boletaDespacho.cod_clie || '</ccon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Nombre Cliente 
            l_textoActual := '<ncon>' || r_boletaDespacho.nom_clie || '</ncon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Zona 
            l_textoActual := '<czon>' || r_boletaDespacho.cod_zona || '</czon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Territorio 
            l_textoActual := '<cter>' || r_boletaDespacho.cod_secc || ' - ' || r_boletaDespacho.cod_terr || '</cter>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Direccion 1 
            l_textoActual := '<dir1>' || r_boletaDespacho.val_dir1 || '</dir1>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Direccion 2 
            IF l_indicadorTelefono = 'S'AND 
               (r_boletaDespacho.val_tele IS NOT NULL)  THEN 
                l_textoActual := '<dir2>' || r_boletaDespacho.val_dir2 || l_textoTelefono || r_boletaDespacho.val_tele || '</dir2>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            ELSE
                l_textoActual := '<dir2>' || r_boletaDespacho.val_dir2 || '</dir2>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            END IF;
            -- Nunero Boleta Despacho  
            l_textoActual := '<nbd>' || r_boletaDespacho.val_nume_soli || '</nbd>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Periodo 
            l_textoActual := '<fcam>' || l_codigoPeriodo || '</fcam>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Fecha 
            l_textoActual := '<femi>' || r_boletaDespacho.fec_emis || '</femi>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Fin Cabecera
            l_textoActual := '</blqcab>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Inicio Bloque Numero de Paquetes 
            l_textoActual := '<blqimp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Numero total de unidades atendidas 
            l_textoActual := '<txt>' || r_boletaDespacho.num_unid_aten_tota || '</txt>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Separador
            l_textoActual := '<txt/>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Monto del Pedido 
            l_textoActual := '<txt>' || r_boletaDespacho.val_tota_paga_loca || '</txt>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Separador  
            l_textoActual := '<txt/><txt></txt><txt></txt><txt/><txt></txt>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Fin Bloque Numero de Paquetes 
            l_textoActual := '</blqimp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Inicio de detalle de Boleta de Despacho 
            l_textoActual := '<detalle>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            l_contadorDetalles := 0;
            OPEN c_detalleBoleta(r_boletaDespacho.oid_soli_cabe);
            LOOP 
            FETCH c_detalleBoleta INTO r_detalleBoleta;
            EXIT WHEN c_detalleBoleta%NOTFOUND;
            BEGIN
                IF l_contadorDetalles = 0 THEN
                    l_textoActual := '<txt>SE ADJUNTAN ' || r_boletaDespacho.num_unid_fuer_caja_tota || ' UNIDADES FUERA DE LA CAJA Y/O PREMIOS</txt>'; 
                    l_textoActual := l_textoActual || '<txt>SIRVASE VERIFICAR: </txt>';
                    l_textoActual := l_textoActual || '<txt>UNID<t/>IND<t/>DESCRIPCION DE PRODUCTO<t/><t/><t/><t/><t/><t/><t/>UNID<t/>IND<t/>DESCRIPCION DE PRODUCTO</txt>';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                END IF;
                IF MOD(l_contadorDetalles, 2) = 0 THEN
                    l_textoActual := '<txt>' || r_detalleBoleta.num_unid_aten || '<t/>' || r_detalleBoleta.ind_dent_fuer_caja_bols || '<t/>' || r_detalleBoleta.des_prod;
                    l_textoActual := l_textoActual || '<t/><t/><t/><t/><t/><t/><t/>';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                END IF;
                IF MOD(l_contadorDetalles, 2) != 0 THEN
                    l_textoActual := r_detalleBoleta.num_unid_aten || '<t/>' || r_detalleBoleta.ind_dent_fuer_caja_bols || '<t/>' || r_detalleBoleta.des_prod || '</txt>';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                END IF;
                l_contadorDetalles := l_contadorDetalles + 1;
            END;
            END LOOP;
            CLOSE c_detalleBoleta;
            -- En caso el numero de detalles haya sido impar agregamos los ultimos campos vacios 
            IF MOD(l_contadorDetalles, 2) != 0 THEN
                l_textoActual := '<t/><t/></txt>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            END IF;
            -- Fin de detalle de Boleta de Despacho 
            l_textoActual := '</detalle>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Fin boletaDespacho
            l_textoActual := '</pbd1></frmbd>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            -- Fin del paquete         
            l_textoActual := '</pd>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            l_correlativo := l_correlativo + 1;
        END;
    END LOOP;
    CLOSE c_boletaDespacho;
    COMMIT;
    IMP_PKG_PROCE_COMPA.IMP_PR_REEMP_CARAC_ESPEC;
    COMMIT;
    IMP_PKG_PROCE_COMPA.IMP_PR_PAQUE_DOCUM_TO_FILE(p_prefijoArchivo || to_char(to_date(p_fechaFacturacion, 'dd/mm/yyyy'), 'yyyymmdd') || '.TXT', p_directorio, 1, 1);
END;
/

