CREATE OR REPLACE PACKAGE "PQ_APL_REP" as
FUNCTION Fn_002_Concat_Grup_Crono (
               id_zona_par IN VARCHAR,
               id_pais_par IN VARCHAR,
               id_canal_par IN VARCHAR,
               id_marca_par IN VARCHAR,
               fecha_par IN VARCHAR,
               id_acceso_par IN VARCHAR --PARAMETRO NO OBLIGATORIO --
)
return varchar2;
FUNCTION Fn_002_Concat_Regiones (
               id_zona_par IN VARCHAR,
               id_pais_par IN VARCHAR,
               id_canal_par IN VARCHAR,
               id_marca_par IN VARCHAR,
               fecha_par IN VARCHAR,
               id_acceso_par IN VARCHAR --PARAMETRO NO OBLIGATORIO --
)
RETURN VARCHAR2;
FUNCTION Fn_004_Concat_Act (
        idperiodos_par IN VARCHAR2,
        idperi_par IN NUMBER,
        fec_prev_par IN DATE,
        idgrupo_par IN NUMBER,
        idpais_par IN NUMBER,
        idmarca_par IN NUMBER,
        idcanal_par IN NUMBER
)
return varchar2;
FUNCTION FN_004_CONCAT_ACT_I18N (
	idpais_par in number,
	idmarca_par in number,
	idcanal_par in number,
	ididioma_par in number
)
return varchar2;
FUNCTION Fn_005_Concat_Fechas (
        idperi_par IN NUMBER,
        idgrupo_par IN NUMBER,
        idact_par IN NUMBER,
        idper_cor IN VARCHAR2
)
return varchar2;
FUNCTION FN_008_CONCAT_ZONA (
	idperi_par in number,
	idregi_par in number,
	idgrupo_par in number,
	idmarca_par in number,
	idcanal_par in number,
	idpais_par in number
)
return varchar2;
FUNCTION FN_010_CONCAT_PRODUCTOS (
	ididioma_par in number,
	idcurso_par in number
)
return varchar2;
FUNCTION FN_CU_CALC_ACTIVAS_FINAL (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER
)
return number;
FUNCTION FN_CU_CALC_ACTIVAS_INIC (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER
)
return number;
FUNCTION FN_CU_CALC_CANT_POR_ESTADO (
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER,
           anio in VARCHAR,
           codEstadoIN in VARCHAR
)
return number;
FUNCTION FN_CU_CALC_CUPON_EN_TRAMITE (
	idpais_par in varchar,
	idclien_par in varchar
)
return number;
FUNCTION FN_CU_CALC_DEUDA_VENC_CLIEN (
	idpais_par in  number,
	idclien_par in number
)
return number;
FUNCTION FN_CU_CALC_DIAS_ATRASO_CLIEN (
	idpais_par in  number,
	idclien_par in number
)
return number;
FUNCTION FN_CU_CALC_MONTO_SOLIC_NUEVAS (
	idclien_par in varchar
)
return number;
FUNCTION FN_CU_CALC_PED_PROMEDIO_CLIEN (
	idclien_par in number
)
return number;
FUNCTION FN_CU_CALC_PERI_CON_PEDIDOS (
	idclien_par in number
)
return number;
FUNCTION FN_CU_CALC_SALDO_CTA_CTE (
	idclien_par in varchar,
	idsubg_par in varchar := NULL,
	idreg_par in varchar := NULL,
	idsecc_par in varchar := NULL
)
return number;
FUNCTION FN_CU_CALC_ENTREGADAS (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER
)
return number;
FUNCTION FN_CU_CALC_RECIBIDAS (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER,
           codEstRegistrada in VARCHAR,
           codEstNueva in VARCHAR
)
return number;
FUNCTION FN_162_TOTAL_PROD_NEG (
	id_peri_par in varchar,
	id_pais_par in varchar,
	id_marca_par in varchar,
	id_canal_par in varchar,
	id_negocio_par in varchar, --PARAMETRO NO OBLIGATORIO, VENDRA -1 -
	id_un_negocio_par in varchar, --PARAMETRO NO OBLIGATORIO, VENDRA -1 --
	id_acceso_par in varchar, --PARAMETRO NO OBLIGATORIO, VENDRA -1 --
	diario_par in varchar:=-1
)
return number;
FUNCTION FN_112_CALC_PER_ANT (
	idpais_par in varchar,
	idmarc_par in varchar,
	idcana_par in varchar,
	idperi_reg in varchar,
	idperi_rec in varchar
)
return number;
FUNCTION FN_008_CALC_OID_PER_ANT (
	idpais_par in varchar,
	idmarc_par in varchar,
	idcana_par in varchar,
	id_peri in varchar,
	n_peri in varchar
)
return number;
FUNCTION FN_011_CALC_RANK_POR_REGION (
	idpais_par in varchar,
	idmarc_par in varchar,
	idcana_par in varchar,
	id_reg in varchar,
	id_peri_desde in varchar,
	id_peri_hasta in varchar,
	id_cli in varchar
)
return number;
FUNCTION FN_072_RANGO (
	m_venta number
)
return number;
FUNCTION FN_070_CONCAT_PERI (
	idconcu in number
)
return varchar2;
FUNCTION FN_049_CONCAT_PREMIO (
	idnivel_par in number,
	idioma_par in number
)
return varchar2;
FUNCTION FN_058_CONCAT_DESC_PREMIO (
	idnivel_par in number,
	idioma_par in number
)
return varchar2;
FUNCTION FN_083_CU_OBT_VENTA (
	idcon in varchar,
	idcli in varchar,
	idtip_venta in varchar
)
return number;
FUNCTION FN_013_FACDTR_CONCAT_PERI (
      idperi_ini in number,
      idperi_fin in number
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Accesos (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Canales (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Marcas (
	tdesc_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Nacional (
	tdesc_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Subaccesos (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Subtipo (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Tipo (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_Accesos (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_DTO_CLIEN (
	desc_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_REGIO (
	desc_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_Subaccesos (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_SUBG (
	desc_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_Subtipo (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_Tipo (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_033_Concat_ZONA (
	desc_par IN NUMBER
)
return varchar2;
FUNCTION FN_003_CONCAT_FECHAS (
            idperi IN NUMBER,
            idacti IN NUMBER,
            idcod_grup IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Clasi (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
FUNCTION Fn_Dto_036_Concat_Tipo_Clasi (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
return varchar2;
end pq_apl_rep;
/

CREATE OR REPLACE PACKAGE BODY "PQ_APL_REP" as
FUNCTION Fn_002_Concat_Grup_Crono (
               id_zona_par IN VARCHAR,
               id_pais_par IN VARCHAR,
               id_canal_par IN VARCHAR,
               id_marca_par IN VARCHAR,
               fecha_par IN VARCHAR,
               id_acceso_par IN VARCHAR --PARAMETRO NO OBLIGATORIO --
)
RETURN VARCHAR2
IS
  var_aux_num VARCHAR2(8000);
  aux VARCHAR2(200);
  v_return VARCHAR2(2000);
  ret     VARCHAR2(20);
    TYPE  my_curs_type IS REF CURSOR;
    curs    my_curs_type;
BEGIN
             IF id_acceso_par IS NOT NULL THEN
                        aux:='AND CAB.ACCE_OID_ACCE = ' || id_acceso_par;
             ELSE
                        aux:= id_acceso_par;
             END IF;
             var_aux_num:= ' SELECT DISTINCT cab.cod_grup '||
                        'FROM CRA_CABEC_GRUPO_ZONA cab,'||
                                                           'CRA_DETAL_GRUPO_ZONA det,'||
                                                           'CRA_CRONO_GRUPO_ZONA CRO,'||
                                                           'ZON_ZONA zon, '||
                                                           'CRA_ACTIV ACT '||
            'WHERE det.CGZO_OID_CABE_GRUP_ZONA = cab.OID_CABE_GRUP_ZONA '||
                                                                                  'AND det.zzon_oid_zona = zon.OID_ZONA '||
                                                                                  'AND CAB.OID_CABE_GRUP_ZONA =  CRO.CGZO_OID_CABE_GRUP_ZONA '||
                                                                                  'AND ZON.OID_ZONA IN  ('|| id_zona_par||') '||
                                                                                  ' AND cab.PAIS_OID_PAIS = '|| id_pais_par ||
                                                                                  ' AND cab.MARC_OID_MARC = '|| id_marca_par ||
                                                                                  ' AND cab.CANA_OID_CANA IN ( '|| id_canal_par ||')'||
                                                                                  ' AND CRO.FEC_PREV = TO_DATE('''|| fecha_par || ''',''dd/mm/yyyy'')' ||
                                                                                                                       ' AND cro.CACT_OID_ACTI = act.OID_ACTI ' ||
                                                                           ' AND ACT.COD_ACTI = ''FA'' '|| aux;

            OPEN curs FOR var_aux_num;
                                   LOOP
                                               FETCH curs INTO ret;
                                                 EXIT WHEN curs%NOTFOUND;
                                                 v_return := v_return||' G'||ret;
                                   END LOOP;
            CLOSE curs;

             IF v_return IS NULL THEN
                                   v_return := ' ';
--             ELSE
--                                   v_return := 'G' || v_return;
             END IF;

    RETURN v_return;

END;


-----------------------------------------------------------------
FUNCTION Fn_002_Concat_Regiones (
               id_zona_par IN VARCHAR,
               id_pais_par IN VARCHAR,
               id_canal_par IN VARCHAR,
               id_marca_par IN VARCHAR,
               fecha_par IN VARCHAR,
               id_acceso_par IN VARCHAR --PARAMETRO NO OBLIGATORIO --
)
RETURN VARCHAR2
IS
  var_aux_num VARCHAR2(8000);
  aux VARCHAR2(200);
  v_return VARCHAR2(2000);
  ret     VARCHAR2(20);
    TYPE  my_curs_type IS REF CURSOR;
    curs    my_curs_type;
BEGIN
             IF id_acceso_par IS NOT NULL THEN
                        aux:='AND CAB.ACCE_OID_ACCE = ' || id_acceso_par;
             ELSE
                        aux:= id_acceso_par;
             END IF;
             var_aux_num:= ' SELECT DISTINCT REG.COD_REGI '||
                        'FROM CRA_CABEC_GRUPO_ZONA cab,'||
                                                           'CRA_DETAL_GRUPO_ZONA det,'||
                                                           'CRA_CRONO_GRUPO_ZONA CRO,'||
                                                           'ZON_ZONA zon, '||
                                                           'ZON_REGIO reg,'||
                                                           'CRA_ACTIV ACT '||
            'WHERE det.CGZO_OID_CABE_GRUP_ZONA = cab.OID_CABE_GRUP_ZONA '||
                                                                                  'AND det.zzon_oid_zona = zon.OID_ZONA '||
                                                                                  'AND zon.ZORG_OID_REGI = reg.OID_REGI '||
                                                                                  'AND CAB.OID_CABE_GRUP_ZONA =  CRO.CGZO_OID_CABE_GRUP_ZONA '||
                                                                                  'AND ZON.OID_ZONA IN  ('|| id_zona_par||') '||
                                                                                  ' AND cab.PAIS_OID_PAIS = '|| id_pais_par ||
                                                                                  ' AND cab.MARC_OID_MARC = '|| id_marca_par ||
                                                                                  ' AND cab.CANA_OID_CANA IN ( '|| id_canal_par ||')'||
                                                                                  ' AND CRO.FEC_PREV = TO_DATE('''|| fecha_par || ''',''dd/mm/yyyy'')' ||
                                                                                                                       ' AND cro.CACT_OID_ACTI = act.OID_ACTI ' ||
                                                                                    ' AND ACT.COD_ACTI = ''FA'' '|| aux;

             OPEN curs FOR var_aux_num;
                                   LOOP
                                               FETCH curs INTO ret;
                                                 EXIT WHEN curs%NOTFOUND;
                                                 v_return := v_return||' R'||ret;
                                   END LOOP;
            CLOSE curs;

             IF v_return IS NULL THEN
                                   v_return := ' ';
--             ELSE
--                                   v_return := ' R' || v_return;
             END IF;

    RETURN v_return;

END;

-----------------------------------------------------------------
FUNCTION Fn_004_Concat_Act (
            idperiodos_par IN VARCHAR2,
            idperi_par IN NUMBER,
            fec_prev_par IN DATE,
            idgrupo_par IN NUMBER,
            idpais_par IN NUMBER,
            idmarca_par IN NUMBER,
            idcanal_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
  (SELECT DISTINCT act.COD_ACTI || decode(per.oid_PERI,idperi_par, '', '-C' || substr(cor.COD_PERI,5,2)|| '/' || substr(cor.COD_PERI,3,2))
  FROM CRA_CRONO_GRUPO_ZONA cro,
       CRA_CABEC_GRUPO_ZONA cab,
       CRA_PERIO per,
       cra_activ act,
       seg_perio_corpo cor,
       CRA_DETAL_GRUPO_ZONA det,
       ZON_ZONA zon,
       ( SELECT oid_peri
         FROM ( SELECT per.oid_peri, per.fec_inic, per.fec_fina
                FROM ( SELECT per2.oid_peri, per2.fec_inic, PC.TIPE_OID_TIPO_PERI
                       FROM CRA_PERIO per2,
                            SEG_PERIO_CORPO PC
                       WHERE per2.oid_peri= to_number(idperiodos_par) --PARAMETRO--
                        AND PC.OID_PERI =PER2.PERI_OID_PERI
                     ) per_selec,
                     CRA_PERIO per,
                     SEG_PERIO_CORPO PC
                WHERE per.fec_inic>=per_selec.fec_inic
                  AND per.pais_oid_pais= idpais_par  --PARAMETRO--
                  AND per.MARC_OID_MARC= idmarca_par --PARAMETRO--
                  AND per.CANA_OID_CANA= idcanal_par --PARAMETRO--
                  AND PC.OID_PERI = PER.PERI_OID_PERI
                  AND PER_SELEC.TIPE_OID_TIPO_PERI = PC.TIPE_OID_TIPO_PERI
                ORDER BY per.fec_inic
              ) WHERE ROWNUM <=3
       ) tres_per
  WHERE cro.CACT_OID_ACTI=act.OID_ACTI
    AND cro.PERD_OID_PERI=per.oid_peri
    AND per.PERI_OID_PERI=cor.oid_peri
    AND cro.CGZO_OID_CABE_GRUP_ZONA=cab.OID_CABE_GRUP_ZONA
    AND det.CGZO_OID_CABE_GRUP_ZONA=cab.OID_CABE_GRUP_ZONA
    AND det.ZZON_OID_ZONA=zon.oid_zona
    AND cro.CGZO_OID_CABE_GRUP_ZONA=idgrupo_par --PARAMETRO--
    AND CRO.FEC_PREV = FEC_PREV_PAR -- PARAMETRO --
    AND per.oid_peri=tres_per.oid_peri);

	var_query varchar2(5000);
	TYPE my_curs_type IS REF CURSOR;
    c_cursor2  my_curs_type;
    v_return VARCHAR2(2000);
    ret      VARCHAR2(20);
  BEGIN
       --Primer Caso: Selecciono solo un periodo, y se tiene que calcular los 2 periodos siguientes
       IF (INSTR(idperiodos_par,',') = 0) THEN
        OPEN c_cursor;
            LOOP
                FETCH c_cursor INTO ret;
                EXIT WHEN c_cursor%NOTFOUND;
                v_return := v_return||'  '||ret;
            END LOOP;
        CLOSE c_cursor;
      ELSE
	  	--Segundo Caso: Selecciono  2 0 3 periodos, y se busca los datos en bases a dichos periodos
	    var_query := '(SELECT DISTINCT act.COD_ACTI || decode(per.oid_PERI,' || idperi_par ||', '''', ''-C'' || substr(cor.COD_PERI,5,2)|| ''/'' || substr(cor.COD_PERI,3,2))'
				    || ' FROM CRA_CRONO_GRUPO_ZONA cro,'
					|| ' CRA_CABEC_GRUPO_ZONA cab,'
			       || ' CRA_PERIO per,'
			       || ' cra_activ act,'
			       || ' seg_perio_corpo cor,'
			       || ' CRA_DETAL_GRUPO_ZONA det,'
			       || ' ZON_ZONA zon,'
			       || ' (select oid_peri from cra_perio where oid_peri in  (' || idperiodos_par || ')) tres_per'
			  || ' WHERE cro.CACT_OID_ACTI=act.OID_ACTI'
			    || ' AND cro.PERD_OID_PERI=per.oid_peri'
			    || ' AND per.PERI_OID_PERI=cor.oid_peri'
			    || ' AND cro.CGZO_OID_CABE_GRUP_ZONA=cab.OID_CABE_GRUP_ZONA'
			    || ' AND det.CGZO_OID_CABE_GRUP_ZONA=cab.OID_CABE_GRUP_ZONA'
			    || ' AND det.ZZON_OID_ZONA=zon.oid_zona'
			    || ' AND cro.CGZO_OID_CABE_GRUP_ZONA=' || idgrupo_par
			    || ' AND CRO.FEC_PREV = to_date(''' || to_char(FEC_PREV_PAR,'DD/MM/YYYY') || ''', ''DD/MM/YYYY'')'
			    || ' AND per.oid_peri=tres_per.oid_peri)';

        OPEN c_cursor2 FOR var_query;
            LOOP
                FETCH c_cursor2 INTO ret;
                EXIT WHEN c_cursor2%NOTFOUND;
                v_return := v_return||'  '||ret;
            END LOOP;
        CLOSE c_cursor2;
    END IF;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION FN_004_CONCAT_ACT_I18N (
	idpais_par IN NUMBER,
	idmarca_par IN NUMBER,
	idcanal_par IN NUMBER,
	ididioma_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
    SELECT 	RPAD( NVL(act.COD_ACTI,' ') ,5), 	RPAD( NVL(gen_act.actividad,' ') ,30)
    FROM cra_activ act,
    	 (
    	 SELECT gen.VAL_OID, gen.VAL_I18N AS actividad
    	 FROM v_gen_i18n_sicc gen
    	 WHERE gen.ATTR_ENTI = 'CRA_ACTIV'
    	 	   AND gen.IDIO_OID_IDIO = ididioma_par --PARAMETRO--
    	 )gen_act
    WHERE act.MARC_OID_MARC = idmarca_par		--PARAMETRO--
    	  AND act.CANA_OID_CANA = idcanal_par --PARAMETRO--
    	  AND act.PAIS_OID_PAIS = idpais_par --PARAMETRO--
        AND gen_act.val_oid(+) = act.OID_ACTI
    ORDER BY act.COD_ACTI, gen_act.actividad;
    v_return VARCHAR2(20000);
    ret     VARCHAR2(5);
    ret2     VARCHAR2(35);
  BEGIN
  OPEN c_cursor;
	LOOP
      FETCH c_cursor INTO ret, ret2;
		  EXIT WHEN c_cursor%NOTFOUND;
		  v_return := v_return || ret || ' - ' || ret2 || '      ';
	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_005_Concat_Fechas (
            idperi_par IN NUMBER,
            idgrupo_par IN NUMBER,
            idact_par IN NUMBER,
            idper_cor IN VARCHAR2
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
  (SELECT TO_CHAR(gru.fec_prev,'dd/mm/yyyy') || ';'
                      FROM CRA_PERIO per,
                           CRA_CRONO_GRUPO_ZONA gru,
                           CRA_CABEC_GRUPO_ZONA cab,
                           cra_activ act,
                           seg_perio_corpo cor
                     WHERE gru.perd_oid_peri = per.oid_peri
                       AND gru.cact_oid_acti = act.oid_acti
                       AND gru.cgzo_oid_cabe_grup_zona = cab.oid_cabe_grup_zona
                       AND cab.oid_cabe_grup_zona = idgrupo_par --PARAMETRO --
                       AND act.oid_acti = idact_par --PARAMETRO --
                                                              AND per.oid_peri = idperi_par   -- PARAMETRO --
                                                              AND per.peri_oid_peri = cor.oid_peri
                                                               AND  SUBSTR (cor.cod_peri, LENGTH (cor.cod_peri) - 1, 2) = idper_cor     --PARAMETRO --

                                                                       );
    v_return VARCHAR2( 8000 );
     ret      VARCHAR2(20);

  BEGIN
  OPEN c_cursor;
            LOOP
                        FETCH c_cursor INTO ret;
                          EXIT WHEN c_cursor%NOTFOUND;
                          v_return := v_return||ret;
            END LOOP;
            CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION FN_008_CONCAT_ZONA (
	idperi_par in number,
	idregi_par in number,
	idgrupo_par in number,
	idmarca_par in number,
	idcanal_par in number,
	idpais_par in number
)
return varchar2
is
cursor c_cursor is
select cod_zona
from ( select reg.cod_regi, reg.OID_REGI, cab.cod_grup, act.COD_ACTI, act.OID_ACTI, cro.OID_CRON_GRUP_ZONA,
       per.VAL_NOMB_PERI periodo, reg.des_regi, zon.cod_zona,
       ( select min(cro1.fec_prev)
         from cra_crono_grupo_zona cro1,
           cra_cabec_grupo_zona cab1,
           cra_activ act1
         where cro1.PERD_OID_PERI=per.OID_PERI
           and cab1.MARC_OID_MARC=cab.MARC_OID_MARC
           and cab1.CANA_OID_CANA=cab.CANA_OID_CANA
           and cab1.PAIS_OID_PAIS=cab.PAIS_OID_PAIS
           and cro1.CACT_OID_ACTI=cro.CACT_OID_ACTI
           and cro1.CGZO_OID_CABE_GRUP_ZONA=cro.CGZO_OID_CABE_GRUP_ZONA
       ) as fecha_prev,
       ( select max(cro2.fec_fina)
         from cra_crono_grupo_zona cro2,
              cra_cabec_grupo_zona cab2,
              cra_activ act2
         where cro2.PERD_OID_PERI=per.OID_PERI
           and cab2.MARC_OID_MARC=cab.MARC_OID_MARC
           and cab2.CANA_OID_CANA=cab.CANA_OID_CANA
           and cab2.PAIS_OID_PAIS=cab.PAIS_OID_PAIS
           and cro2.CACT_OID_ACTI=cro.CACT_OID_ACTI
           and cro2.CGZO_OID_CABE_GRUP_ZONA=cro.CGZO_OID_CABE_GRUP_ZONA
       )as fecha_fina
       from zon_regio reg,
       zon_zona zon,
       cra_crono_grupo_zona cro,
       cra_cabec_grupo_zona cab,
       cra_detal_grupo_zona det,
       cra_perio per,
       cra_activ act
where zon.ZORG_OID_REGI=reg.OID_REGI
  and cro.CGZO_OID_CABE_GRUP_ZONA=cab.OID_CABE_GRUP_ZONA
  and cab.OID_CABE_GRUP_ZONA=det.CGZO_OID_CABE_GRUP_ZONA
  and det.ZZON_OID_ZONA=zon.OID_ZONA
  and cro.PERD_OID_PERI=per.OID_PERI
  and cro.CACT_OID_ACTI=act.OID_ACTI
  and cro.PERD_OID_PERI= idperi_par
  and reg.pais_oid_pais = idpais_par
  and reg.marc_oid_marc = idmarca_par
  and reg.cana_oid_cana = idcanal_par
  and zon.pais_oid_pais = idpais_par
  and zon.marc_oid_marc = idmarca_par
  and zon.cana_oid_cana = idcanal_par
  and cab.pais_oid_pais = idpais_par
  and cab.marc_oid_marc = idmarca_par
  and cab.cana_oid_cana = idcanal_par
)
where OID_REGI = idregi_par
  and OID_CRON_GRUP_ZONA = idgrupo_par
group by cod_regi, cod_grup, des_regi, cod_acti, periodo, cod_zona, fecha_prev, fecha_fina;
/*
  var_aux varchar2(8000):= 'select cod_zona ' ||
  		  				   'from ( ' ||
						   'select reg.cod_regi, reg.OID_REGI, cab.cod_grup, act.COD_ACTI, act.OID_ACTI, cro.OID_CRON_GRUP_ZONA, ' ||
						   'per.VAL_NOMB_PERI periodo, reg.des_regi, zon.cod_zona, ' ||
						   '( '	||
						   'select min(cro1.fec_prev) ' ||
						   'from cra_crono_grupo_zona cro1, ' ||
						   'cra_cabec_grupo_zona cab1, ' ||
						   'cra_activ act1 ' ||
						   'where cro1.PERD_OID_PERI=per.OID_PERI ' ||
						   'and cab1.MARC_OID_MARC=cab.MARC_OID_MARC ' ||
						   'and cab1.CANA_OID_CANA=cab.CANA_OID_CANA ' ||
						   'and cab1.PAIS_OID_PAIS=cab.PAIS_OID_PAIS ' ||
						   'and cro1.CACT_OID_ACTI=cro.CACT_OID_ACTI ' ||
						   'and cro1.CGZO_OID_CABE_GRUP_ZONA=cro.CGZO_OID_CABE_GRUP_ZONA ' ||
						   ')as fecha_prev, ' 	||
						   '( select max(cro2.fec_fina) ' ||
						   'from cra_crono_grupo_zona cro2, ' ||
						   'cra_cabec_grupo_zona cab2, ' ||
						   'cra_activ act2 ' ||
						   'where cro2.PERD_OID_PERI=per.OID_PERI ' 					 ||
						   'and cab2.MARC_OID_MARC=cab.MARC_OID_MARC ' 					 ||
						   'and cab2.CANA_OID_CANA=cab.CANA_OID_CANA ' 					 ||
						   'and cab2.PAIS_OID_PAIS=cab.PAIS_OID_PAIS ' 					 ||
						   'and cro2.CACT_OID_ACTI=cro.CACT_OID_ACTI ' 					 ||
						   'and cro2.CGZO_OID_CABE_GRUP_ZONA=cro.CGZO_OID_CABE_GRUP_ZONA ' ||
						   ')as fecha_fina ' ||
						   'from zon_regio reg, ' ||
						   'zon_zona zon, ' ||
						   'cra_crono_grupo_zona cro, ' ||
						   'cra_cabec_grupo_zona cab, ' ||
						   'cra_detal_grupo_zona det, ' ||
						   'cra_perio per, ' 	 	  	||
						   'cra_activ act ' 			||
						   'where zon.ZORG_OID_REGI=reg.OID_REGI ' ||
						   'and cro.CGZO_OID_CABE_GRUP_ZONA=cab.OID_CABE_GRUP_ZONA ' ||
						   'and cab.OID_CABE_GRUP_ZONA=det.CGZO_OID_CABE_GRUP_ZONA ' ||
						   'and det.ZZON_OID_ZONA=zon.OID_ZONA ' ||
						   'and cro.PERD_OID_PERI=per.OID_PERI ' ||
						   'and cro.CACT_OID_ACTI=act.OID_ACTI ' ||
						   'and cro.PERD_OID_PERI= ' || idperi_par || ' '		 ||
						   'and reg.pais_oid_pais = ' || idpais_par || '  '		 ||
						   'and reg.marc_oid_marc = ' || idmarca_par || ' ' 	 ||
						   'and reg.cana_oid_cana = ' || idcanal_par || ' '		 ||
						   'and zon.pais_oid_pais = ' || idpais_par || ' ' 		 ||
						   'and zon.marc_oid_marc = ' || idmarca_par || ' ' 	 ||
						   'and zon.cana_oid_cana = ' || idcanal_par || ' ' 	 ||
						   'and cab.pais_oid_pais = ' || idpais_par || ' ' 		 ||
						   'and cab.marc_oid_marc = ' || idmarca_par || ' ' 	 ||
						   'and cab.cana_oid_cana = ' || idcanal_par || ' ' 	 ||
						   ') '  ||
						   'where OID_REGI = ' || idregi_par || ' ' 		 	||
						   'and OID_CRON_GRUP_ZONA = ' || idgrupo_par  || ' ' 	||
						   'group by cod_regi, cod_grup, des_regi, cod_acti, periodo, cod_zona, fecha_prev, fecha_fina ' ;
*/
    v_return varchar2(2000);
    ret     varchar2(20);
  begin
    OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  exit when c_cursor%notfound;
		  v_return := v_return||' '||ret;
	end loop;
	CLOSE c_cursor;
    return v_return;
end;
-----------------------------------------------------------------


FUNCTION FN_010_CONCAT_PRODUCTOS (
	ididioma_par in number,
	idcurso_par in number
)
return varchar2
is
/*
  var_aux varchar2(5000):=
  		  				    'select prod.COD_SAP, gen_produ.regalo ' ||
							'from edu_matri_curso cur, ' ||
							'	 edu_regal rega, ' ||
							'	 edu_regal_detal det_regal, ' ||
							'	 mae_produ prod, ' ||
							'	 ( ' ||
							'	 select gen.VAL_OID, gen.VAL_I18N regalo ' ||
							'	 from v_gen_i18n_sicc gen ' ||
							'	 where gen.ATTR_ENTI= ''MAE_PRODU'' ' ||
							'	 	   and gen.IDIO_OID_IDIO = '|| ididioma_par ||
							'	 )gen_produ ' ||
							'where cur.OID_CURS = '|| idcurso_par ||
							'	  and cur.REGA_OID_REGA = rega.OID_REGA(+) ' ||
							'	  and rega.OID_REGA = det_regal.REGA_OID_REGA ' ||
							'	  and det_regal.PROD_OID_PROD = prod.OID_PROD ' ||
							'	  and gen_produ.val_oid(+) = prod.oid_prod ' ||
							'order by prod.COD_SAP, gen_produ.regalo ';
*/
  cursor c_cursor is
  select prod.COD_SAP, gen_produ.regalo
  from edu_matri_curso cur,
  	 edu_regal rega,
  	 edu_regal_detal det_regal,
  	 mae_produ prod,
  	 (
  	 select gen.VAL_OID, gen.VAL_I18N regalo
  	 from v_gen_i18n_sicc gen
  	 where gen.ATTR_ENTI= 'MAE_PRODU'
  	 	   and gen.IDIO_OID_IDIO = ididioma_par
  	 )gen_produ
  where cur.OID_CURS = idcurso_par
  	  and cur.REGA_OID_REGA = rega.OID_REGA(+)
  	  and rega.OID_REGA = det_regal.REGA_OID_REGA
  	  and det_regal.PROD_OID_PROD = prod.OID_PROD
  	  and gen_produ.val_oid(+) = prod.oid_prod
  order by prod.COD_SAP, gen_produ.regalo;
    v_return varchar2(20000);
    ret     varchar2(20);
    ret2     varchar2(4000);
begin
  OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret, ret2;
		  exit when c_cursor%notfound;
		  v_return := v_return || ret || '-' || ret2 || '      ';
	end loop;
	CLOSE c_cursor;
  return v_return;
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_ACTIVAS_FINAL (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER
)
return number
is
  var_aux_num number;
begin
         EXECUTE IMMEDIATE
                        'SELECT  ' ||
                        '                COUNT (clientes_pedidos.CLIE_OID_CLIE) CANTIDAD ' ||
                        'FROM  ' ||
                        '                ( ' ||
                        '                SELECT DISTINCT ' ||
                        '                           sol.CLIE_OID_CLIE ' ||
                        '                FROM ' ||
                        '                                PED_SOLIC_CABEC sol , ' ||
                        '                                ( ' ||
                        '                                SELECT ' ||
                        '                                                SEC.OID_SECC ZSCC_OID_SECC, ' ||
                        '                                                UNA.CLIE_OID_CLIE ' ||
                        '                                FROM ' ||
                        '                                                MAE_CLIEN_UNIDA_ADMIN UNA, ' ||
                        '                                                ZON_TERRI_ADMIN TERR, ' ||
                        '                                                ZON_SECCI SEC, ' ||
                        '                                                ZON_ZONA ZON, ' ||
                        '                                                ZON_REGIO REG, ' ||
                        '                                                ZON_SUB_GEREN_VENTA SUB, ' ||
                        '                                                CRA_PERIO PER_DSD,  ' ||
                        '                                                CRA_PERIO PER_HST,  ' ||
                        '                                                CRA_PERIO PER_ENT  ' ||
                        '                                                WHERE UNA.ZTAD_OID_TERR_ADMI = TERR.OID_TERR_ADMI ' ||
                        '                                                AND TERR.ZSCC_OID_SECC = SEC.OID_SECC   ' ||
                        '                                                AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA   ' ||
                        '                                                AND ZON.ZORG_OID_REGI = REG.OID_REGI  ' ||
                        '                                                AND REG.ZSGV_OID_SUBG_VENT = SUB.OID_SUBG_VENT ' ||
                        '                                                AND UNA.PERD_OID_PERI_INI = PER_DSD.OID_PERI  ' ||
                        '                                                AND UNA.PERD_OID_PERI_FIN = PER_HST.OID_PERI(+) ' ||
                        '                                                AND PER_ENT.OID_PERI = :1' ||
                        '                                                AND PER_DSD.FEC_INIC <= PER_ENT.FEC_INIC ' ||
                        '                                                AND (PER_HST.OID_PERI IS NULL OR PER_HST.FEC_FINA >= PER_ENT.FEC_FINA) ' ||
                        '                                                AND UNA.IND_ACTI = 1 ' ||
                        '                                                AND REG.ZSGV_OID_SUBG_VENT = :2' ||
                        '                                                AND REG.OID_REGI = :3' ||
                        '                                                AND ZON.OID_ZONA = :4' ||
                        '                                ) terrAdmin ' ||
                        '                WHERE ' ||
                        '                                  SOL.CLIE_OID_CLIE = terrAdmin.CLIE_OID_CLIE ' ||
                        '                                AND SOL.FEC_FACT IS NOT NULL ' ||
                        '                                AND SOL.IND_OC = 1 ' ||
                        '                ) clientes_pedidos '
         INTO var_aux_num
         USING idPeriodo, idSubGV, idRegion, idZona;
           return var_aux_num;
end;

-----------------------------------------------------------------
FUNCTION FN_CU_CALC_ACTIVAS_INIC (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER
)
return number
is
  var_aux_num number;
begin
         EXECUTE IMMEDIATE
                        'SELECT  ' ||
                        '                COUNT (clientes_pedidos.CLIE_OID_CLIE) CANTIDAD ' ||
                        'FROM  ' ||
                        '                ( ' ||
                        '                SELECT DISTINCT ' ||
                        '                           sol.CLIE_OID_CLIE ' ||
                        '                FROM ' ||
                        '                                PED_SOLIC_CABEC sol , ' ||
                        '                                ( ' ||
                        '                                SELECT ' ||
                        '                                                SEC.OID_SECC ZSCC_OID_SECC, ' ||
                        '                                                UNA.CLIE_OID_CLIE ' ||
                        '                                FROM ' ||
                        '                                                MAE_CLIEN_UNIDA_ADMIN UNA, ' ||
                        '                                                ZON_TERRI_ADMIN TERR, ' ||
                        '                                                ZON_SECCI SEC, ' ||
                        '                                                ZON_ZONA ZON, ' ||
                        '                                                ZON_REGIO REG, ' ||
                        '                                                ZON_SUB_GEREN_VENTA SUB, ' ||
                        '                                                CRA_PERIO PER_DSD,  ' ||
                        '                                                CRA_PERIO PER_HST,  ' ||
                        '                                                CRA_PERIO PER_ENT  ' ||
                        '                                                WHERE UNA.ZTAD_OID_TERR_ADMI = TERR.OID_TERR_ADMI ' ||
                        '                                                AND TERR.ZSCC_OID_SECC = SEC.OID_SECC   ' ||
                        '                                                AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA   ' ||
                        '                                                AND ZON.ZORG_OID_REGI = REG.OID_REGI  ' ||
                        '                                                AND REG.ZSGV_OID_SUBG_VENT = SUB.OID_SUBG_VENT ' ||
                        '                                                AND UNA.PERD_OID_PERI_INI = PER_DSD.OID_PERI  ' ||
                        '                                                AND UNA.PERD_OID_PERI_FIN = PER_HST.OID_PERI(+) ' ||
                        '                                                AND PER_ENT.OID_PERI = :1' ||
                        '                                                AND PER_DSD.FEC_INIC <= PER_ENT.FEC_INIC ' ||
                        '                                                AND (PER_HST.OID_PERI IS NULL OR PER_HST.FEC_FINA >= PER_ENT.FEC_FINA) ' ||
                        '                                                AND UNA.IND_ACTI = 1 ' ||
                        '                                                AND REG.ZSGV_OID_SUBG_VENT = :2' ||
                        '                                                AND REG.OID_REGI = :3' ||
                        '                                                AND ZON.OID_ZONA = :4' ||
                        '                                ) terrAdmin ' ||
                        '                WHERE ' ||
                        '                                  SOL.CLIE_OID_CLIE = terrAdmin.CLIE_OID_CLIE ' ||
                        '                                AND SOL.FEC_FACT IS NOT NULL ' ||
                        '                                AND SOL.IND_OC = 1 ' ||
                        '                ) clientes_pedidos '
         INTO var_aux_num
         USING idPeriodo, idSubGV, idRegion, idZona;
           return var_aux_num;
end;

-----------------------------------------------------------------
FUNCTION FN_CU_CALC_CANT_POR_ESTADO (
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER,
           anio in VARCHAR,
           codEstadoIN in VARCHAR
)
return number
is
  var_aux_num number;
begin
         EXECUTE IMMEDIATE
                         'SELECT ' ||
                        '                COUNT (*) count ' ||
                        'FROM ' ||
                        '                mae_clien_unida_admin unida, ' ||
                        '                cra_perio p1, ' ||
                        '                cra_perio p2, ' ||
                        '                zon_terri_admin terrAdmin, ' ||
                        '                mae_clien_histo_estat histo, ' ||
                        '                cra_perio p3, ' ||
                        '                cra_perio p4, ' ||
                        '                ZON_SECCI secc, ' ||
                        '                ZON_ZONA zona, ' ||
                        '                ZON_REGIO reg, ' ||
                        '                MAE_ESTAT_CLIEN ESTADO ' ||
                        'WHERE ' ||
                        '                terrAdmin.ZSCC_OID_SECC = secc.OID_SECC ' ||
                        '                AND secc.ZZON_OID_ZONA = zona.OID_ZONA ' ||
                        '                AND zona.ZORG_OID_REGI = reg.OID_REGI ' ||
                        '                AND unida.perd_oid_peri_ini = p1.oid_peri(+) ' ||
                        '                AND unida.perd_oid_peri_fin = p2.oid_peri(+) ' ||
                        '                AND (p1.fec_inic IS NULL OR p1.fec_inic <= TO_DATE(''31/12/'||anio||''', ''dd/MM/yyyy'')) ' ||
                        '                AND (p2.fec_fina IS NULL OR p2.fec_fina >= TO_DATE(''01/01/'||anio||''', ''dd/MM/yyyy'')) ' ||
                        '                AND histo.clie_oid_clie = unida.clie_oid_clie ' ||
                        '                AND histo.perd_oid_peri = p3.oid_peri(+) ' ||
                        '                AND histo.perd_oid_peri_peri_fin = p4.oid_peri(+) ' ||
                        '                AND (p3.fec_inic IS NULL OR p3.fec_inic <= TO_DATE(''31/12/'||anio||''', ''dd/MM/yyyy'')) ' ||
                        '                AND (p4.fec_fina IS NULL OR p4.fec_fina >= TO_DATE(''01/01/'||anio||''', ''dd/MM/yyyy'')) ' ||
                        '                AND unida.ztad_oid_terr_admi = terrAdmin.oid_terr_admi ' ||
                        '                AND histo.ESTA_OID_ESTA_CLIE = ESTADO.OID_ESTA_CLIE ' ||
                        '                AND ESTADO.COD_ESTA_CLIE IN ('||codEstadoIN||') ' ||
                        '                AND reg.ZSGV_OID_SUBG_VENT = '||idSubGV||
                        '                AND reg.OID_REGI = ' ||idRegion||
                        '                AND zona.OID_ZONA ='||idZona
         INTO var_aux_num;
--         USING idSubGV, idRegion, idZona, anio, codEstadoIN;
           return var_aux_num;
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_ENTREGADAS (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER
)
return number
is
  var_aux_num number;
begin
         EXECUTE IMMEDIATE
'SELECT ' ||
'           COUNT (1) COUNT ' ||
'FROM ' ||
'( ' ||
'SELECT ' ||
'                COUNT(1) ' ||
'FROM  ' ||
'                mae_clien_unida_admin unida, ' ||
'                zon_terri_admin terrAdmin, ' ||
'                ZON_SECCI secc, ' ||
'                ZON_ZONA zona, ' ||
'                ZON_REGIO reg ' ||
'WHERE terrAdmin.ZSCC_OID_SECC = secc.OID_SECC ' ||
'                AND secc.ZZON_OID_ZONA = zona.OID_ZONA ' ||
'                AND zona.ZORG_OID_REGI = reg.OID_REGI  ' ||
'                AND unida.ztad_oid_terr_admi = terrAdmin.oid_terr_admi ' ||
'                AND unida.perd_oid_peri_fin = :1 ' ||
'                AND reg.ZSGV_OID_SUBG_VENT = :2 ' ||
'                AND reg.OID_REGI = :3' ||
'                AND zona.OID_ZONA = :4' ||
'GROUP BY unida.clie_oid_clie)'
         INTO var_aux_num
         USING idPeriodo, idSubGV, idRegion, idZona;
           return var_aux_num;
end;

-----------------------------------------------------------------
FUNCTION FN_CU_CALC_RECIBIDAS (
           idPeriodo in NUMBER,
           idSubGV in NUMBER,
           idRegion in NUMBER,
           idZona in NUMBER,
           codEstRegistrada in VARCHAR,
           codEstNueva in VARCHAR
)
return number
is
  var_aux_num number;
begin
         EXECUTE IMMEDIATE
'                SELECT ' ||
'                                COUNT(*) ' ||
'                FROM ' ||
'                                mae_clien_unida_admin unida, ' ||
'                                MAE_CLIEN_DATOS_ADICI adic, ' ||
'                                zon_terri_admin terrAdmin, ' ||
'                                ZON_SECCI secc, ' ||
'                                ZON_ZONA zona, ' ||
'                                ZON_REGIO reg, ' ||
'                                MAE_ESTAT_CLIEN MEC_REG, ' ||
'                                MAE_ESTAT_CLIEN MEC_NUE ' ||
'                WHERE terrAdmin.ZSCC_OID_SECC = secc.OID_SECC ' ||
'                                AND secc.ZZON_OID_ZONA = zona.OID_ZONA ' ||
'                                AND zona.ZORG_OID_REGI = reg.OID_REGI ' ||
'                                AND adic.clie_oid_clie = unida.clie_oid_clie ' ||
'                                AND unida.ztad_oid_terr_admi = terrAdmin.oid_terr_admi ' ||
'                                AND adic.esta_oid_esta_clie = MEC_REG.OID_ESTA_CLIE ' ||
'                                AND adic.esta_oid_esta_clie = MEC_NUE.OID_ESTA_CLIE ' ||
'                                AND unida.perd_oid_peri_ini = :1 ' ||
'                                AND reg.ZSGV_OID_SUBG_VENT = :2 ' ||
'                                AND reg.OID_REGI = :3 ' ||
'                                AND zona.OID_ZONA = :4 ' ||
'                                AND MEC_REG.COD_ESTA_CLIE <> :5 ' ||
'                                AND MEC_NUE.COD_ESTA_CLIE <> :6'
         INTO var_aux_num
         USING idPeriodo, idSubGV, idRegion, idZona, codEstRegistrada, codEstNueva;
           return var_aux_num;
end;

-----------------------------------------------------------------
FUNCTION FN_CU_CALC_CUPON_EN_TRAMITE (
	idpais_par in varchar,
	idclien_par in varchar
)
return number
is
  var_aux_num number;
begin
	 EXECUTE IMMEDIATE 'SELECT NVL(SUM(d.IMP_DETA),0) as suma ' ||
	 		 		   'FROM CCC_CUPON_TRAMI_DEPUR c, CCC_DETAL_CUPON_TRAMI_DEPUR d, ' ||
					   'CCC_SITUA_CUPON s ' ||
					   'WHERE c.OID_CUPO_TRAM_DEPU = d.CTDE_OID_CUPO_TRAM_DEPU ' ||
					   'AND d.SICU_OID_SITU_CUPO = s.OID_SITU_CUPO ' ||
					   'AND c.PAIS_OID_PAIS = :1 ' ||
					   'AND d.CLIE_OID_CLIE = :2 ' ||
					   'AND s.COD_SITU_CUPO = ''T'''
	 INTO var_aux_num
	 USING idpais_par, idclien_par ;
  	 return var_aux_num;
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_DEUDA_VENC_CLIEN (
	idpais_par in  number,
	idclien_par in number
)
return number
is
  var_aux_num number;
  var_marc_sit varchar2(2000);
  var_aux_str varchar2(2000);

  cursor c_cursor is
				SELECT
					u.OID_MARC_SITU
				FROM
					CCC_MARCA_SITUA u,CCC_MARCA_TIPO_ABONO m,
					CCC_TIPO_ABONO_SUBPR t,CCC_SUBPR s,
					CCC_PROCE p
				WHERE u.OID_MARC_SITU = m.MASI_OID_MARC_SALI
					AND m.TASP_OID_TIPO_ABON_SUBP = t.OID_TIPO_ABON_SUBP
					AND t.SUBP_OID_SUBP = s.OID_SUBP
					AND s.CCPR_OID_PROC = p.OID_PROC
					AND s.COD_SUBP = 1
					AND p.COD_PROC = 'CON001'
					AND u.PAIS_OID_PAIS = idpais_par
					AND m.IND_ENTR_SALI like 'E';

begin

	 var_marc_sit := '';
	 open c_cursor;
	 LOOP
	 	 FETCH c_cursor INTO var_aux_num;
		 exit when c_cursor%notfound;
		 var_marc_sit := var_marc_sit||TO_CHAR(var_aux_num)||',';
	 end loop;
	 CLOSE c_cursor;

	 var_aux_str := ' SELECT NVL(SUM(IMP_PEND),0) as suma' ||
  		  			   ' FROM CCC_MOVIM_CUENT_CORRI m, MAE_CLIEN c' ||
  		  			   ' WHERE m.CLIE_OID_CLIE = c.OID_CLIE' ||
  		  			   ' AND c.PAIS_OID_PAIS= ' || idpais_par ||
  		  			   ' AND m.CLIE_OID_CLIE = ' || idclien_par ||
  		  			   ' AND m.IMP_MOVI <> m.IMP_PAGA';

  	 if (length(var_marc_sit)> 0) then
  	 	var_marc_sit := SUBSTR(var_marc_sit,1,length(var_marc_sit)-1);
  	 	var_aux_str := var_aux_str || ' AND M.MASI_OID_MARC_SITU IN (' || var_marc_sit || ')';
	 end if;

	 EXECUTE IMMEDIATE var_aux_str
	 INTO var_aux_num;
  	 return var_aux_num;
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_DIAS_ATRASO_CLIEN (
	idpais_par in  number,
	idclien_par in number
)
return number
is
     var_Aux varchar2(2000);
  var_AuxNum number;
  var_MarcSit varchar2(2000);
  var_ValorImporteP number;
  var_ValorTotal number;
  var_Total number;
  var_NumeroDias number;

  cursor c_cursor is
				SELECT
					u.OID_MARC_SITU
				FROM
					CCC_MARCA_SITUA u,CCC_MARCA_TIPO_ABONO m,
					CCC_TIPO_ABONO_SUBPR t,CCC_SUBPR s,
					CCC_PROCE p
				WHERE u.OID_MARC_SITU = m.MASI_OID_MARC_SALI
					AND m.TASP_OID_TIPO_ABON_SUBP = t.OID_TIPO_ABON_SUBP
					AND t.SUBP_OID_SUBP = s.OID_SUBP
					AND s.CCPR_OID_PROC = p.OID_PROC
					AND s.COD_SUBP = 1
					AND p.COD_PROC = 'CON001'
					AND u.PAIS_OID_PAIS = idpais_par
					AND m.IND_ENTR_SALI like 'E';

    type    my_curs_type is REF CURSOR;
    curs    my_curs_type;
	var_ulti_movi date;
	var_fec_venc date;
	var_retorno int;

  begin

  	 var_MarcSit := '';
	 open c_cursor;
	 LOOP
	 	 FETCH c_cursor INTO var_AuxNum;
		 exit when c_cursor%notfound;
		 var_MarcSit := var_MarcSit||TO_CHAR(var_AuxNum)||',';
	 end loop;
	 CLOSE c_cursor;

   	 var_Aux := ' SELECT m.FEC_ULTI_MOVI, m.FEC_VENC, NVL (m.IMP_PAGO, 0) IMP_PAGO' ||
				' FROM CCC_MOVIM_CUENT_CORRI m, MAE_CLIEN c' ||
				' WHERE m.CLIE_OID_CLIE = c.OID_CLIE' ||
				   ' AND c.PAIS_OID_PAIS= ' || idpais_par ||
				   ' AND m.CLIE_OID_CLIE = ' || idclien_par ||
				   ' AND ( TO_DATE( SYSDATE,''YYYY-MM-DD'') - m.FEC_VENC) > 0' ||
				   ' AND m.IMP_MOVI <> m.IMP_PAGA' ||
				   ' AND m.IMP_PAGO <> 0' ||
				   ' AND m.FEC_ULTI_MOVI > m.FEC_VENC';

  	 if (length(var_MarcSit)> 0) then
  	 	var_MarcSit := SUBSTR(var_MarcSit,1,length(var_MarcSit)-1);
  	 	var_Aux := var_Aux || ' AND M.MASI_OID_MARC_SITU IN (' || var_MarcSit || ')';
	 end if;

 	 var_Aux := var_Aux || ' UNION' ||
			   ' SELECT h.FEC_MOVI, h.FEC_VENC, NVL (h.IMP_PAGO, 0) IMP_PAGO' ||
			   ' FROM CCC_HISTO_MOVIM_CC h, MAE_CLIEN c' ||
			   ' WHERE h.CLIE_OID_CLIE = c.OID_CLIE' ||
			   ' AND c.PAIS_OID_PAIS= ' || idpais_par ||
			   ' AND h.CLIE_OID_CLIE = ' || idclien_par ||
			   ' AND ( TO_DATE( SYSDATE ,''YYYY-MM-DD'') - h.FEC_VENC) > 0' ||
			   ' AND h.IMP <> h.IMP_PAGA' ||
			   ' AND h.IMP_PAGO <> 0' ||
			   ' AND h.FEC_MOVI > h.FEC_VENC';

  	 if (length(var_MarcSit)> 0) then
  	 	var_Aux := var_Aux || ' AND h.MASI_OID_MARC_SITU IN (' || var_MarcSit || ')';
	 end if;

    OPEN curs FOR var_Aux;
	LOOP
		FETCH curs INTO var_ulti_movi, var_fec_venc, var_ValorImporteP;
		  exit when curs%notfound;

		  var_ValorTotal := (var_ulti_movi-var_fec_venc) * var_ValorImporteP;

		  var_NumeroDias := var_NumeroDias + var_ValorTotal;

		  var_Total := var_Total + var_ValorImporteP;

	end loop;
	CLOSE curs;

	if var_Total = 0 then
	   var_retorno := 0;
	else
	   var_retorno:= var_NumeroDias/var_Total;
	end if;
    return nvl(var_retorno,0);
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_MONTO_SOLIC_NUEVAS (
	idclien_par in varchar
)
return number
is
  var_aux_num number;
begin
	 EXECUTE IMMEDIATE 'SELECT NVL(SUM(CA.VAL_TOTA_PAGA_LOCA),0) MONTO_SOLICITUDES_NUEVAS ' ||
  			  		   'FROM PED_SOLIC_CABEC CA ' ||
					   'WHERE CA.CLIE_OID_CLIE = :1 ' ||
		   			   'AND (CA.FEC_FACT IS NULL AND CA.ESSO_OID_ESTA_SOLI= 1 ' || --//ConstantesPED.ESTADO_SOLICITUD_VALIDADO
		   			   'OR CA.ESSO_OID_ESTA_SOLI= 5 )' ||					  	   --//ConstantesPED.ESTADO_SOLICITUD_LIBERADO
					   'AND (CA.IND_PEDI_PRUE is NULL OR CA.IND_PEDI_PRUE<>1)'
	 INTO var_aux_num
	 USING idclien_par;
  	 return NVL(var_aux_num,0);
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_PED_PROMEDIO_CLIEN (
	idclien_par in number
)
return number
is
  var_Promedio number;
  var_Periodo1 number;
  var_Pais number;
  var_Marca number;
  var_Canal number;
  var_Periodo2 number;
  var_ClaseSolicitud number;
  var_NumPeriodos number;
  var_ValorTotal number;

begin

--- OBTENCION DEL PERIODO 1 (Ultima Evaluacion de Riesgo) -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT' ||
						' 	  NVL(ADIC.PERD_OID_PERI_LINE_CRED,-1), PERIO.PAIS_OID_PAIS, PERIO.MARC_OID_MARC, PERIO.CANA_OID_CANA' ||
						' FROM' ||
						' 	  MAE_CLIEN_DATOS_ADICI ADIC,' ||
						' 	  CRA_PERIO PERIO' ||
						' WHERE' ||
						' 	  ADIC.PERD_OID_PERI_LINE_CRED = PERIO.OID_PERI(+)' ||
						' 	  AND ADIC.IND_ACTI = 1' ||
						'  	  AND ADIC.CLIE_OID_CLIE = :1'
	 INTO
	 	  var_Periodo1, var_Pais, var_Marca, var_Canal
	 USING
	 	  idclien_par;

	 if (var_Periodo1 = -1) then	  -- Si el periodo es null devuelvo 0.
	 	return 0;
	 end if;


--- OBTENCION DEL PERIODO 2 (Actual) -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT *' ||
						' FROM' ||
						  		' (' ||
							   	' SELECT PERIO.OID_PERI' ||
							   	' FROM CRA_PERIO PERIO' ||
								' WHERE PERIO.FEC_INIC <= TO_DATE(SYSDATE)' ||
									  ' AND TO_DATE(SYSDATE) <= PERIO.FEC_FINA' ||
									  ' AND PERIO.PAIS_OID_PAIS = :1' ||
									  ' AND PERIO.MARC_OID_MARC = :2' ||
									  ' AND PERIO.CANA_OID_CANA = :3' ||
									  ' AND PERIO.VAL_ESTA = 1' ||
								' ORDER BY PERIO.FEC_INIC ' ||
						  			' ) PERI_ACT' ||
						' WHERE ROWNUM = 1'
	 INTO
	 	  var_Periodo2
	 USING
	 	  var_Pais, var_Marca, var_Canal;


--- OBTENCION DE LA CLASE DE SOLICITUD -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT CL_SOL.OID_CLAS_SOLI' ||
	 				   	' FROM PED_CLASE_SOLIC CL_SOL' ||
						' WHERE CL_SOL.IND_ORDE_COMP = 1' ||
						  		' AND  ROWNUM = 1'
	 INTO
	 	  var_ClaseSolicitud;


--- OBTENCION DE LA CANTIDAD DE PERIODOS  -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT COUNT(DISTINCT A.perd_oid_peri)' ||
						' FROM' ||
								' ped_solic_cabec A,' ||
								' cra_perio B,' ||
								' cra_perio C,' ||
								' cra_perio D' ||
						' WHERE' ||
								' A.clie_oid_clie = :1' ||
								' AND A.clso_oid_clas_soli = :2' ||
								' AND A.fec_fact IS NOT NULL' ||
								' AND NVL(A.ind_ts_no_conso, 0) = 0' ||
								' AND NVL(A.ind_pedi_prue, 0) = 0' ||
								' AND B.oid_peri = A.perd_oid_peri' ||
								' AND C.oid_peri = :3' ||
								' AND D.oid_peri = :4' ||
								' AND B.fec_inic >= C.fec_inic' ||
								' AND B.fec_fina <= D.fec_fina'
	 INTO
	 	  var_NumPeriodos
	 USING
	 	   idclien_par, var_ClaseSolicitud, var_Periodo1, var_Periodo2;


--- OBTENCION DEL VALOR TOTAL -------------------------------------------------------------------------------------


  	 EXECUTE IMMEDIATE ' SELECT NVL(SUM(A.val_tota_paga_loca), 0)' ||
						' FROM' ||
								' ped_solic_cabec A,' ||
								' cra_perio B,' ||
								' cra_perio C,' ||
								' cra_perio D' ||
						' WHERE' ||
								' A.clie_oid_clie = :1' ||
								' AND A.clso_oid_clas_soli = :2' ||
								' AND A.fec_fact IS NOT NULL' ||
								' AND NVL(A.ind_ts_no_conso, 0) = 0' ||
								' AND NVL(A.ind_pedi_prue, 0) = 0' ||
								' AND B.oid_peri = A.perd_oid_peri' ||
								' AND C.oid_peri = :3' ||
								' AND D.oid_peri = :4' ||
								' AND B.fec_inic >= C.fec_inic' ||
								' AND B.fec_fina <= D.fec_fina'
	 INTO
	 	  var_ValorTotal
	 USING
	 	   idclien_par, var_ClaseSolicitud, var_Periodo1, var_Periodo2;

--- CALCULO FINAL DEL PROMEDIO ------------------------------------------------------------------------------------------

	if (var_ValorTotal <> 0) then
	   	return var_ValorTotal/var_NumPeriodos;
	else
  		return 0;
	end if;

---------------------------------------------------------------------------------------------
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_PERI_CON_PEDIDOS (
	idclien_par in number
)
return number
is
  var_Periodo1 number;
  var_Pais number;
  var_Marca number;
  var_Canal number;
  var_Periodo2 number;
  var_ClaseSolicitud number;
  var_NumPeriodos number;

begin

--- OBTENCION DEL PERIODO 1 (Ultima Evaluacion de Riesgo) -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT' ||
						' 	  NVL(ADIC.PERD_OID_PERI_NIVE_RIES,-1), PERIO.PAIS_OID_PAIS, PERIO.MARC_OID_MARC, PERIO.CANA_OID_CANA' ||
						' FROM' ||
						' 	  MAE_CLIEN_DATOS_ADICI ADIC,' ||
						' 	  CRA_PERIO PERIO' ||
						' WHERE' ||
						' 	  ADIC.PERD_OID_PERI_LINE_CRED = PERIO.OID_PERI(+)' ||
						' 	  AND ADIC.IND_ACTI = 1' ||
						'  	  AND ADIC.CLIE_OID_CLIE = :1'
	 INTO
	 	  var_Periodo1, var_Pais, var_Marca, var_Canal
	 USING
	 	  idclien_par;

	 if (var_Periodo1 = -1) then	  -- Si el periodo es null busco el periodo de ingreso del cliente.

		EXECUTE IMMEDIATE ' SELECT *' ||
						  ' FROM' ||
						  		' (' ||
								' SELECT PERIO.OID_PERI, PERIO.PAIS_OID_PAIS, PERIO.MARC_OID_MARC, PERIO.CANA_OID_CANA' ||
								' FROM CRA_PERIO PERIO,' ||
										' MAE_CLIEN_UNIDA_ADMIN CLI_UA,' ||
										' MAE_CLIEN CL' ||
								' WHERE' ||
										' PERIO.FEC_INIC <= CL.FEC_INGR' ||
										' AND CL.FEC_INGR <= PERIO.FEC_FINA' ||
										' AND PERIO.VAL_ESTA = 1' ||
										' AND CL.oid_clie= :1' ||
										' AND CLI_UA.CLIE_OID_CLIE = CL.OID_CLIE' ||
										' AND PERIO.OID_PERI = CLI_UA.PERD_OID_PERI_INI' ||
								' ORDER BY PERIO.FEC_INIC' ||
								' ) PERI_ACTUAL' ||
						  ' WHERE ROWNUM = 1'
		INTO
		 	  var_Periodo1, var_Pais, var_Marca, var_Canal
		USING
		 	  idclien_par;
	 end if;

--- OBTENCION DEL PERIODO 2 (Actual) -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT *' ||
						' FROM' ||
						  		' (' ||
							   	' SELECT PERIO.OID_PERI' ||
							   	' FROM CRA_PERIO PERIO' ||
								' WHERE PERIO.FEC_INIC <= TO_DATE(SYSDATE)' ||
									  ' AND TO_DATE(SYSDATE) <= PERIO.FEC_FINA' ||
									  ' AND PERIO.PAIS_OID_PAIS = :1' ||
									  ' AND PERIO.MARC_OID_MARC = :2' ||
									  ' AND PERIO.CANA_OID_CANA = :3' ||
									  ' AND PERIO.VAL_ESTA = 1' ||
								' ORDER BY PERIO.FEC_INIC ' ||
						  			' ) PERI_ACT' ||
						' WHERE ROWNUM = 1'
	 INTO
	 	  var_Periodo2
	 USING
	 	  var_Pais, var_Marca, var_Canal;


--- OBTENCION DE LA CLASE DE SOLICITUD -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT CL_SOL.OID_CLAS_SOLI' ||
	 				   	' FROM PED_CLASE_SOLIC CL_SOL' ||
						' WHERE CL_SOL.IND_ORDE_COMP = 1' ||
						  		' AND  ROWNUM = 1'
	 INTO
	 	  var_ClaseSolicitud;


--- OBTENCION DE LA CANTIDAD DE PERIODOS  -------------------------------------------------------------------------------------

  	 EXECUTE IMMEDIATE ' SELECT COUNT(DISTINCT A.perd_oid_peri)' ||
						' FROM' ||
								' ped_solic_cabec A,' ||
								' cra_perio B,' ||
								' cra_perio C,' ||
								' cra_perio D' ||
						' WHERE' ||
								' A.clie_oid_clie = :1' ||
								' AND A.clso_oid_clas_soli = :2' ||
								' AND A.fec_fact IS NOT NULL' ||
								' AND NVL(A.ind_ts_no_conso, 0) = 0' ||
								' AND NVL(A.ind_pedi_prue, 0) = 0' ||
								' AND B.oid_peri = A.perd_oid_peri' ||
								' AND C.oid_peri = :3' ||
								' AND D.oid_peri = :4' ||
								' AND B.fec_inic >= C.fec_inic' ||
								' AND B.fec_fina <= D.fec_fina'
	 INTO
	 	  var_NumPeriodos
	 USING
	 	   idclien_par, var_ClaseSolicitud, var_Periodo1, var_Periodo2;


	 return var_NumPeriodos;

---------------------------------------------------------------------------------------------
end;
-----------------------------------------------------------------
FUNCTION FN_CU_CALC_SALDO_CTA_CTE (
	idclien_par in varchar,
	idsubg_par in varchar := NULL,
	idreg_par in varchar := NULL,
	idsecc_par in varchar := NULL
)
return number
is
  var_aux_num number;
  var_aux_str2 varchar2(15);
  var_marc_sit varchar2(2000);

  var_aux_str varchar2(2000):=
'SELECT IMP_PEND - NVL((SELECT SUM(B.IMP_SALD_PEND) '||
'						FROM CCC_MOVIM_BANCA B '||
'						WHERE b.CLIE_OID_CLIE = CUEN_CORRI.CLIE_OID_CLIE '||
'						AND B.IMP_SALD_PEND <> 0 '||
'						),0)  AS SUMA  '||
'FROM						'||
'('||
'SELECT CLIE_OID_CLIE, NVL(SUM(m.IMP_PEND),0) IMP_PEND '||
'FROM CCC_MOVIM_CUENT_CORRI m '||
'WHERE m.CLIE_OID_CLIE = :ID '||
'  AND m.IMP_MOVI <> m.IMP_PAGA ';

  cursor c_cursor is
				SELECT
					u.OID_MARC_SITU
				FROM
					CCC_MARCA_SITUA u,CCC_MARCA_TIPO_ABONO m,
					CCC_TIPO_ABONO_SUBPR t,CCC_SUBPR s,
					CCC_PROCE p
				WHERE u.OID_MARC_SITU = m.MASI_OID_MARC_SALI
					AND m.TASP_OID_TIPO_ABON_SUBP = t.OID_TIPO_ABON_SUBP
					AND t.SUBP_OID_SUBP = s.OID_SUBP
					AND s.CCPR_OID_PROC = p.OID_PROC
					AND s.COD_SUBP = 1
					AND p.COD_PROC = 'CON001'
					AND u.PAIS_OID_PAIS = (Select cli.PAIS_OID_PAIS
										   From MAE_CLIEN cli
										   Where cli.OID_CLIE = idclien_par)
					AND m.IND_ENTR_SALI like 'E';

begin

	 var_marc_sit := '';
	 open c_cursor;
	 LOOP
	 	 FETCH c_cursor INTO var_aux_num;
		 exit when c_cursor%notfound;
		 var_marc_sit := var_marc_sit||TO_CHAR(var_aux_num)||',';
	 end loop;
	 CLOSE c_cursor;

  	 if (length(var_marc_sit)> 0) then
  	 	var_marc_sit := SUBSTR(var_marc_sit,1,length(var_marc_sit)-1);
	 end if;

  	 if (idsubg_par >= 0) then
  	 	var_aux_str := var_aux_str || ' AND M.ZSGV_OID_SUBG_VENT =' || idsubg_par;
	 end if;
  	 if (idreg_par >= 0) then
  	 	var_aux_str := var_aux_str || ' AND M.ZORG_OID_REGI =' || idreg_par;
	 end if;
  	 if (idsecc_par >= 0) then
  	 	var_aux_str := var_aux_str || ' AND M.ZSCC_OID_SECC =' || idsecc_par;
	 end if;

  	 if (length(var_marc_sit)>0) then
  	 	var_aux_str := var_aux_str || ' AND M.MASI_OID_MARC_SITU IN (' || var_marc_sit || ')';
	 end if;

var_aux_str := var_aux_str || 'GROUP BY CLIE_OID_CLIE ) CUEN_CORRI ';


	 EXECUTE IMMEDIATE var_aux_str
	 INTO var_aux_num
	 USING idclien_par;
  	 return var_aux_num;

EXCEPTION
   WHEN NO_DATA_FOUND THEN
   return 0;
end;
-----------------------------------------------------------------
FUNCTION FN_162_TOTAL_PROD_NEG (
	   id_peri_par in varchar,
	   id_pais_par in varchar,
	   id_marca_par in varchar,
	   id_canal_par in varchar,
	   id_negocio_par in varchar, --PARAMETRO NO OBLIGATORIO, VENDRA -1 -
	   id_un_negocio_par in varchar, --PARAMETRO NO OBLIGATORIO, VENDRA -1 --
	   id_acceso_par in varchar, --PARAMETRO NO OBLIGATORIO, VENDRA -1 --
	   diario_par in varchar:=-1
)
return number
is
  var_aux_num varchar2(8000);
  var_aux number;
  aux varchar2(200);
  aux2 varchar2(200);
begin
	 if id_un_negocio_par = '-1' then
		aux:='prod.UNEG_OID_UNID_NEGO';
	 else
		aux:= id_un_negocio_par;
	 end if;
	 if id_negocio_par = -1 then
		aux2:='prod.NEGO_OID_NEGO';
	 else
		aux2:= id_negocio_par;
	 end if;
	   var_aux_num := 'select unidades_faltantes '||
				'from '||
				'( 	 '||
				' 	select   '||
				'productos.nego_oid_nego, '||
				'sum((nvl(solicitudes.NUM_UNID_POR_ATEN,0) - nvl(solicitudes.NUM_UNID_ATEN,0)))unidades_faltantes '||
				'	from ( 	 '||
				'( 	 '||
				'select sol_no_con.oid_soli_cabe, '||
				'pos.oid_soli_posi, '||
				'pos.VAL_PREC_NETO_TOTA_LOCA, '||
				'pos.NUM_UNID_ATEN, '||
				'pos.NUM_UNID_POR_ATEN,  '||
				'pos.prod_oid_prod, '||
				'sol_no_con.perd_oid_peri 	 '||
				'from 		 '||
				' 	( '||
				'select * '||
				'from ped_solic_cabec sol 	 '||
				' 	  where sol.FEC_FACT is not null '||
				  '   and sol.IND_PEDI_PRUE <> 1 	  '||
				' 	and sol.IND_TS_NO_CONSO = 1  '||
				')sol_no_con, 		  '||
				'ped_solic_posic pos, '||
				'ped_estad_posic est_pos, '||
				'zon_zona zon, '||
				'ped_tipo_solic_pais t_sol_p, '||
				'ped_tipo_solic t_sol '||
				'where '||
				' 	sol_no_con.oid_soli_cabe = pos.SOCA_OID_SOLI_CABE '||
				' 	and pos.ESPO_OID_ESTA_POSI = est_pos.OID_ESTA_POSI(+) '||
				' 	and est_pos.COD_ESTA_POSI(+) <> ''AN'' '||
				' 	and pos.NUM_UNID_POR_ATEN > pos.NUM_UNID_ATEN  '||
				' 	and  sol_no_con.FEC_FACT = ( decode( ' || diario_par || ' ' ||
				',-1,sol_no_con.FEC_FACT,sysdate - 1) ) '||
				' 	and sol_no_con.perd_oid_peri= ' || id_peri_par || ' '||
				' 	and sol_no_con.ZZON_OID_ZONA = zon.oid_zona '||
				' 	and sol_no_con.TSPA_OID_TIPO_SOLI_PAIS =  t_sol_p.oid_tipo_soli_pais '||
				' 	and t_sol_p.tsol_oid_tipo_soli = t_sol.oid_tipo_soli '||
				' 	and t_sol.acce_oid_acce = ( decode( ' || id_acceso_par || ' ' ||
				',-1,t_sol.acce_oid_acce,' || id_acceso_par || ') ) '||
				')solicitudes 				 '||
				'inner join 	 '||
				'( 	   '||
				'select prod_per.OID_PROD, prod_per.UNEG_OID_UNID_NEGO, prod_per.nego_oid_nego '||
				'from    '||
				'( '||
				'select prod.OID_PROD, prod.UNEG_OID_UNID_NEGO, prod.nego_oid_nego '||
				'from    '||
				' 	mae_produ prod, '||
				' 	cra_perio per, '||
				' 	( 	 '||
				'select * 	 '||
				'from cra_perio per 	 '||
				'where  per.pais_oid_pais = ' || id_pais_par || ' '||
				' 	and per.marc_oid_marc = ' || id_marca_par || '   '||
				' 	and per.cana_oid_cana = ' || id_canal_par || '   '||
				' 	)  per_inic, 	  '||
				' 	(   '||
				'select * 	 '||
				'from cra_perio per 	 '||
				'where  per.pais_oid_pais = ' || id_pais_par || ' '||
				' 	and per.marc_oid_marc = ' || id_marca_par || '   '||
				' 	and per.cana_oid_cana = ' || id_canal_par || '   '||
				' 	)  per_fina  '||
				'where   '||
				'prod.PERD_OID_PERI_INIC = per_inic.oid_peri '||
				'and prod.PERD_OID_PERI_FIN = per_fina.oid_peri '||
				'and per_inic.fec_inic <= per.FEC_INIC  '||
				'and per_fina.fec_fina >= per.fec_fina '||
				'and per.oid_peri = ' || id_peri_par || ' '||
				'and prod.UNEG_OID_UNID_NEGO in ( ' || aux  || ' ) '||
				'and prod.nego_oid_nego in ( ' || aux2 || ' ) '||
				'union 		 '||
				' 	select prod.OID_PROD, prod.UNEG_OID_UNID_NEGO, prod.nego_oid_nego '||
				' 	from mae_produ prod 	   '||
				' 	where ( prod.PERD_OID_PERI_INIC is null '||
				'or prod.PERD_OID_PERI_FIN is null )     '||
				'  and prod.UNEG_OID_UNID_NEGO in ( ' || aux  || ' )  '||
				'  and prod.nego_oid_nego in ( ' || aux2 || '   )    '||
				')prod_per 	   '||
				')productos '||
				'on solicitudes.prod_oid_prod = productos.oid_prod       '||
				'inner join cra_perio per 	    '||
				'on solicitudes.perd_oid_peri = per.oid_peri '||
				' 	)	    '||
				' 	group by productos.nego_oid_nego '||
				' 	order by unidades_faltantes desc '||
				')	  '||
				'where rownum < 9';
	 EXECUTE IMMEDIATE var_aux_num INTO var_aux;
	 return nvl(var_aux,0);
end;
-----------------------------------------------------------------
FUNCTION FN_112_CALC_PER_ANT (
	idpais_par in varchar,
	idmarc_par in varchar,
	idcana_par in varchar,
	idperi_reg in varchar,
	idperi_rec in varchar
)
return number
is
  var_aux_num number;
begin
		 EXECUTE IMMEDIATE
							'select count (*) as cant_per								' ||
							'from cra_perio c,	 										' ||
							'	 (														' ||
							'	  select ci.oid_peri, ci.fec_fina						' ||
							'	  from cra_perio ci	  									' ||
							'	  where ci.oid_peri = :1								' ||
							'	 ) i,				  									' ||
							'	 ( 														' ||
							'	  select ci.oid_peri, ci.fec_fina						' ||
							'	  from cra_perio ci	  									' ||
							'	  where ci.oid_peri = :2								' ||
							'	 ) f 				  									' ||
							'where 														' ||
							'	 c.fec_fina >= i.fec_fina  								' ||
							'	 and c.fec_fina <= f.fec_fina							' ||
							'	 and c.pais_oid_pais = :3								' ||
							'	 and c.marc_oid_marc = :4								' ||
							'	 and c.cana_oid_cana = :5								'
	 INTO var_aux_num
	 USING idperi_reg , idperi_rec, idpais_par, idmarc_par, idcana_par;
  	 return var_aux_num;
end;
-----------------------------------------------------------------
FUNCTION Fn_008_Calc_Oid_Per_Ant (
	idpais_par IN VARCHAR,
	idmarc_par IN VARCHAR,
	idcana_par IN VARCHAR,
	id_peri IN VARCHAR,
	n_peri IN VARCHAR
)
RETURN NUMBER
IS
  var_aux_num NUMBER;
BEGIN
		 EXECUTE IMMEDIATE
		 		 'SELECT OID_PERI			                    '||
                 'FROM                                          '||
				 '	(                                           '||
				 '	SELECT OID_PERI,                            '||
				 '		   ROWNUM AS POS                        '||
				 '	FROM                                        '||
				 '		(                                       '||
				 '		SELECT PERI.OID_PERI	                '||
				 '		FROM CRA_PERIO PERI,                    '||
				 '			 (                                  '||
				 '			 SELECT FEC_INIC                    '||
				 '			 FROM CRA_PERIO                     '||
				 '			 WHERE OID_PERI = :1                '||
				 '			 ) FF  			  					'||
				 '		WHERE FF.FEC_INIC >= PERI.FEC_INIC		'||
				 '			  AND PERI.PAIS_OID_PAIS = :2	  	'||
				 '			  AND PERI.MARC_OID_MARC = :3		'||
				 '			  AND PERI.CANA_OID_CANA = :4		'||
				 '		ORDER BY PERI.FEC_INIC DESC  			'||
				 '		)	  	 								'||
				 '	)											'||
				 'WHERE POS =	:5								'
	 INTO var_aux_num
	 USING id_peri, idpais_par, idmarc_par, idcana_par, n_peri;
  	 RETURN var_aux_num;
END;
-----------------------------------------------------------------
FUNCTION FN_011_CALC_RANK_POR_REGION (
	idpais_par in varchar,
	idmarc_par in varchar,
	idcana_par in varchar,
	id_reg in varchar,
	id_peri_desde in varchar,
	id_peri_hasta in varchar,
	id_cli in varchar
)
return number
is
  var_aux_num number;
begin
		 EXECUTE IMMEDIATE
				'SELECT POS                                                 '||
				'FROM                                                       '||
				'(                                                          '||
				'	SELECT CLIE_OID_CLIE,                                   '||
				'		   VENTA,                                           '||
				'		   ROWNUM AS POS                                    '||
				'	FROM                                                    '||
				'	(                                                       '||
				'		SELECT SEG_CLI.CLIE_OID_CLIE,                       '||
				'			   SUM(SEG_CLI.VAL_VENT) VENTA                  '||
				'		FROM DTR_SEGME_CLIEN SEG_CLI,                       '||
				'			 CRA_PERIO PERI,                                '||
				'			 ZON_REGIO REGI,                                '||
				'			 (                                              '||
				'			 SELECT FEC_INIC                                '||
				'			 FROM CRA_PERIO                                 '||
				'			 WHERE OID_PERI = :1	 				        '||
				'			 ) FI,                                          '||
				'			 (                                              '||
				'			 SELECT FEC_FINA                                '||
				'			 FROM CRA_PERIO                                 '||
				'			 WHERE OID_PERI = :2					        '||
				'			 ) FF	                                        '||
				'		WHERE FI.FEC_INIC <= PERI.FEC_INIC                  '||
				'			  AND FF.FEC_FINA >= PERI.FEC_FINA              '||
				'			  AND SEG_CLI.PERD_OID_PERI = PERI.OID_PERI     '||
				'			  AND SEG_CLI.ZORG_OID_REGI = REGI.OID_REGI     '||
				'			  AND REGI.OID_REGI = :3			   	        '||
				'			  AND PERI.PAIS_OID_PAIS = :4  			        '||
				'			  AND PERI.CANA_OID_CANA = :5			        '||
				'			  AND PERI.MARC_OID_MARC = :6			        '||
				'		GROUP BY REGI.OID_REGI,                             '||
				'				 SEG_CLI.CLIE_OID_CLIE	                    '||
				'		ORDER BY VENTA DESC                                 '||
				'	)                                                       '||
				')	                                                        '||
				'WHERE CLIE_OID_CLIE = :7                                    '
	 INTO var_aux_num
	 USING id_peri_desde, id_peri_hasta, id_reg, idpais_par, idcana_par, idmarc_par, id_cli;
  	 return var_aux_num;
end;
-----------------------------------------------------------------
FUNCTION FN_072_RANGO (
	m_venta number
)
return number

is

  var_aux_num number;

begin

	 IF (3000 >= m_venta) THEN
	 	IF (1000 = m_venta) THEN
	 		 var_aux_num := m_venta - MOD(m_venta,500);
	    ELSE
			 var_aux_num := (m_venta-1) - MOD((m_venta-1), 500);
	    END IF;
	 ELSE
	 	 	 var_aux_num := (m_venta-1) - MOD((m_venta-1),1000);
	 END IF;

  	 return var_aux_num;

end;
-----------------------------------------------------------------
FUNCTION FN_070_CONCAT_PERI (
	idconcu IN NUMBER
)
RETURN VARCHAR2


IS

CURSOR c_cursor IS
     SELECT 'C-'||SUBSTR(PERI.VAL_NOMB_PERI, LENGTH(PERI.VAL_NOMB_PERI)-1)
	 FROM INC_CONCU_PARAM_GENER CPG,
	 	  (
		     SELECT CPG.OID_PARA_GRAL,
			 		P.FEC_INIC,
					P.PAIS_OID_PAIS,
					P.MARC_OID_MARC,
					P.CANA_OID_CANA
			 FROM CRA_PERIO P,
			 	  INC_CONCU_PARAM_GENER CPG
			 WHERE CPG.OID_PARA_GRAL = idconcu
			 	   AND CPG.PERD_OID_PERI_DESD = P.OID_PERI
		  ) PERI_INI,
  	 	  (
		     SELECT CPG.OID_PARA_GRAL,
			 		P.FEC_FINA
			 FROM CRA_PERIO P,
			 	  INC_CONCU_PARAM_GENER CPG
			 WHERE CPG.OID_PARA_GRAL = idconcu
			 	   AND CPG.PERD_OID_PERI_HAST = P.OID_PERI
		  ) PERI_FIN,
		  CRA_PERIO PERI
	 WHERE CPG.OID_PARA_GRAL = PERI_INI.OID_PARA_GRAL
	 	   AND CPG.OID_PARA_GRAL = PERI_FIN.OID_PARA_GRAL
		   AND PERI.PAIS_OID_PAIS = PERI_INI.PAIS_OID_PAIS
		   AND PERI.MARC_OID_MARC = PERI_INI.MARC_OID_MARC
		   AND PERI.CANA_OID_CANA = PERI_INI.CANA_OID_CANA
		   AND PERI.FEC_INIC >= PERI_INI.FEC_INIC
		   AND PERI.FEC_FINA <= PERI_FIN.FEC_FINA
           AND CPG.OID_PARA_GRAL = idconcu
   	 ORDER BY 'C-'||SUBSTR(PERI.VAL_NOMB_PERI, LENGTH(PERI.VAL_NOMB_PERI)-1);


    v_return VARCHAR2(2000);
    ret     VARCHAR2(10);

  BEGIN

    OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;
		  v_return := v_return||' | '||ret;
	END LOOP;
	CLOSE c_cursor;
	v_return := v_return||' | ';
    RETURN v_return;

END;

-----------------------------------------------------------------
FUNCTION FN_049_CONCAT_PREMIO (
	idnivel_par in number,
	idioma_par in number
)
return varchar2
is
cursor c_cursor is
select RPAD(V_PRE.CANTIDAD||'  '||v_pre.tipo_premio||' '||v_pre.descripcion, 100)
from v_rep_premios v_pre
where v_pre.OID_PARA_NIVE_PREM = idnivel_par
	  AND V_PRE.IDIO_TIPO_PREMIO = idioma_par;

    v_return varchar2(10000);
    ret     varchar2(100);

  begin
    OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  exit when c_cursor%notfound;
		  v_return := v_return||' '||ret;
	end loop;
	CLOSE c_cursor;
	return v_return;
end;
-----------------------------------------------------------------
FUNCTION FN_058_CONCAT_DESC_PREMIO (
	idnivel_par in number,
	idioma_par in number
)
return varchar2
is
cursor c_cursor is
select v_pre.tipo_premio||' '||v_pre.descripcion||','
from v_rep_premios v_pre
where v_pre.OID_PARA_NIVE_PREM = idnivel_par
	  AND V_PRE.IDIO_TIPO_PREMIO = idioma_par;

    v_return varchar2(10000);
    ret     varchar2(100);

  begin
    OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  exit when c_cursor%notfound;
		  v_return := v_return||' '||ret;
	end loop;
	CLOSE c_cursor;
	return v_return;
end;
-----------------------------------------------------------------
FUNCTION Fn_083_Cu_Obt_Venta (
	idcon IN VARCHAR,
	idcli IN VARCHAR,
	idtip_venta IN VARCHAR
)
RETURN NUMBER
IS
  var_aux_num NUMBER;
BEGIN
	 EXECUTE IMMEDIATE
' SELECT NVL(SUM(CASE WHEN (CPG.VAL_FALT_NANU <> 1) THEN    '||
'  	CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 3) THEN     '||
'    SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_PREC_CATA_UNIT_LOCA      '||
'  	ELSE    '||
'   CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 2) THEN  	'||
'    (SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA) '||
'      ELSE    '||
'	     	 CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 1) THEN       '||
'     (SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_IMPO_IMPU_UNIT_LOCA)-(SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA)   '||
'    END   	'||
'   END    '||
'  	END      '||
'  ELSE      '||
'  	CASE WHEN ((SOL_POS.IND_CTRL_LIQU IS NULL) AND (SOL_POS.IND_CTRL_STOC IS NULL) AND (SOL_POS.IND_LIMI_VENT IS NULL)) THEN '||
'   CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 3) THEN  	           	       '||
'   	 SOL_POS.NUM_UNID_DEMA * SOL_POS.VAL_PREC_CATA_UNIT_LOCA     '||
'   ELSE            '||
'   	CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 2) THEN  '||
'   	 (SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA)      '||
' ELSE   	'||
'      	 CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 1) THEN   	   '||
'   	  (SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_IMPO_IMPU_UNIT_LOCA)-(SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA)   '||
'   	 END   '||
'   	END   	'||
'   END   	'||
'  	ELSE   	'||
'   CASE WHEN (SOL_POS.IND_CTRL_LIQU IS NOT NULL) THEN       '||
'   	CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 3) THEN  '||
'     SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_PREC_CATA_UNIT_LOCA     '||
'   	ELSE   '||
'    CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 2) THEN       '||
'     (SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA)  	   '||
' ELSE   '||
' 	     	 CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 1) THEN      '||
'      (SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_IMPO_IMPU_UNIT_LOCA)-(SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_COMPR*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA)  '||
'     END       '||
'    END      	 '||
'   	END    '||
'   ELSE    '||
'   	CASE WHEN (SOL_POS.IND_CTRL_STOC IS NOT NULL) THEN    	 '||
'    CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 3) THEN      '||
'  SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_PREC_CATA_UNIT_LOCA  	 '||
'    ELSE       '||
'    	CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 2) THEN    	 '||
'  (SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA)    '||
'	 ELSE       '||
'       	 CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 1) THEN   	 '||
'   (SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_IMPO_IMPU_UNIT_LOCA)-(SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_DEMA*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA)  '||
'  END     	  '||
'    	END        '||
'    END        '||
'   	ELSE        '||
'    CASE WHEN (SOL_POS.IND_LIMI_VENT IS NOT NULL) THEN      '||
'    	CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 3) THEN    	  '||
'  SOL_POS.NUM_UNID_POR_ATEN*SOL_POS.VAL_PREC_CATA_UNIT_LOCA    '||
'    	ELSE     	  '||
' CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 2) THEN      '||
'  (SOL_POS.NUM_UNID_POR_ATEN*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_POR_ATEN*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA) 	  '||
'  ELSE     	  '||
'  	 CASE WHEN (CPC.TVEN_OID_TIPO_VENT = 1) THEN     '||
'   (SOL_POS.NUM_UNID_POR_ATEN*SOL_POS.VAL_IMPO_IMPU_UNIT_LOCA)-(SOL_POS.NUM_UNID_POR_ATEN*SOL_POS.VAL_PREC_CATA_UNIT_LOCA)-(SOL_POS.NUM_UNID_POR_ATEN*SOL_POS.VAL_IMPO_DESC_UNIT_LOCA) '||
'  END        '||
' END     '||
'    	END   	 '||
'   	END    	 '||
'   END      '||
'  	END     	 '||
'  END       '||
' 	END), 0) VENTA      '||
' FROM  '||
'	 ( '||
'	 SELECT POSTA.OID_SOLI_CABE, POSTA.PAIS_OID_PAIS, POSTA.PERD_OID_PERI, POSTA.CLIE_OID_CLIE, POSTA.TSPA_OID_TIPO_SOLI_PAIS '||
'	 FROM PED_SOLIC_CABEC POSTA '||
'	 WHERE '||
'	    	POSTA.SOCA_OID_SOLI_CABE IN ( '||
'     	       	SELECT CONS.OID_SOLI_CABE '||
'            FROM PED_SOLIC_CABEC CONS '||
'            WHERE  CONS.FEC_FACT IS NOT NULL '||
'            AND CONS.IND_TS_NO_CONSO <> 1 '||
'               AND CONS.CLIE_OID_CLIE = '|| idcli || ' ' ||
'            ) '||
' ) SOL_CAB,            '||
'  PED_SOLIC_POSIC SOL_POS,   	 '||
'  PED_TIPO_SOLIC TIP_SOL,   	 '||
'  PED_TIPO_SOLIC_PAIS TIP_PAIS,     '||
'  INC_CONCU_PARAM_GENER CPG,   	 '||
'  INC_CONCU_PARAM_CONSU CPC,       '||
' 	( 	     	 '||
' SELECT  '||
'   PERI.OID_PERI,  '||
'   PERI.PAIS_OID_PAIS,  '||
'    PERI.MARC_OID_MARC,  '||
'    PERI.CANA_OID_CANA,  '||
'    CON_GEN.OID_PARA_GRAL  '||
' FROM  '||
'  INC_CONCU_PARAM_GENER CON_GEN,  '||
'  CRA_PERIO D,  '||
'  CRA_PERIO H,  '||
'  CRA_PERIO PERI  '||
' WHERE  '||
'   CON_GEN.PERD_OID_PERI_DESD = D.OID_PERI  '||
'	    AND CON_GEN.PERD_OID_PERI_HAST = H.OID_PERI  '||
'   AND PERI.PAIS_OID_PAIS = D.PAIS_OID_PAIS  '||
'   AND PERI.MARC_OID_MARC = D.MARC_OID_MARC  '||
'   AND PERI.CANA_OID_CANA = D.CANA_OID_CANA  '||
'   AND PERI.PAIS_OID_PAIS = H.PAIS_OID_PAIS  '||
'   AND PERI.MARC_OID_MARC = H.MARC_OID_MARC  '||
'   AND PERI.CANA_OID_CANA = H.CANA_OID_CANA  '||
'   AND PERI.FEC_INIC >= D.FEC_INIC  '||
'   AND PERI.FEC_FINA <= H.FEC_FINA  '||
'   AND CON_GEN.OID_PARA_GRAL = '|| idcon || ' ' ||
' 	) PERIODOS   	    	     	 '||
' WHERE SOL_CAB.CLIE_OID_CLIE = '|| idcli || ' ' ||
'   AND SOL_CAB.PERD_OID_PERI = PERIODOS.OID_PERI      '||
'   AND SOL_CAB.PAIS_OID_PAIS = PERIODOS.PAIS_OID_PAIS     '||
'   AND CPG.OID_PARA_GRAL = PERIODOS.OID_PARA_GRAL    	 '||
'   AND CPC.TVEN_OID_TIPO_VENT = '|| idtip_venta || ' ' ||
'   AND SOL_POS.SOCA_OID_SOLI_CABE = SOL_CAB.OID_SOLI_CABE   	 '||
'   AND TIP_SOL.OID_TIPO_SOLI = TIP_PAIS.TSOL_OID_TIPO_SOLI   	 '||
'   AND SOL_CAB.TSPA_OID_TIPO_SOLI_PAIS = TIP_PAIS.OID_TIPO_SOLI_PAIS  	 '||
'	      AND SOL_POS.PROD_OID_PROD IN (   '||
'          	 SELECT '||
'             	DISTINCT '||
'             	PRODU.OID_PROD '||
'          	 FROM '||
'           	  	( '||
'             	SELECT * '||
'             	FROM '||
'               INC_PRODU_CALIF PRO_CAL '||
'             	WHERE PRO_CAL.COPA_OID_PARA_GRAL = '|| idcon || ' ' ||
'             	) PRO_CAL, '||
'             	( '||
'             	SELECT P.OID_PROD, P.COD_SAP, '||
'               P.MAPR_OID_MARC_PROD, P.NEGO_OID_NEGO, P.UNEG_OID_UNID_NEGO, '||
'            	P.GENE_OID_GENE, P.SGEN_OID_SUPE_GENE  '||
'             	FROM '||
'           	 	MAE_PRODU P '||
'             	) PRODU, '||
'             	PRE_OFERT_DETAL OFEDET '||
'           	WHERE 1=1 '||
'           	  AND OFEDET.OID_DETA_OFER = SOL_POS.OFDE_OID_DETA_OFER '||
'           	  AND PRODU.OID_PROD = SOL_POS.PROD_OID_PROD '||
'         AND ( DECODE(PRO_CAL.MAPR_OID_MARC_PROD, '||
'        	           NULL, '||
'               -1, '||
'               PRO_CAL.MAPR_OID_MARC_PROD) = '||
'            DECODE(PRO_CAL.MAPR_OID_MARC_PROD, '||
'                  NULL, '||
'               -1, '||
'               PRODU.MAPR_OID_MARC_PROD) '||
'         AND DECODE(PRO_CAL.UNEG_OID_UNID_NEGO, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.UNEG_OID_UNID_NEGO) = '||
'               	   DECODE(PRO_CAL.UNEG_OID_UNID_NEGO, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.UNEG_OID_UNID_NEGO) '||
'         AND DECODE(PRO_CAL.PROD_OID_PROD, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.PROD_OID_PROD) = '||
'               	   DECODE(PRO_CAL.PROD_OID_PROD, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.OID_PROD) '||
'         AND DECODE(PRO_CAL.GENE_OID_GENE, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.GENE_OID_GENE) = '||
'               	   DECODE(PRO_CAL.GENE_OID_GENE, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.GENE_OID_GENE) '||
'         AND DECODE(PRO_CAL.SGEN_OID_SUPE_GENE, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.SGEN_OID_SUPE_GENE) = '||
'               	   DECODE(PRO_CAL.SGEN_OID_SUPE_GENE, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.SGEN_OID_SUPE_GENE) '||
'         AND DECODE(PRO_CAL.NEGO_OID_NEGO, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.NEGO_OID_NEGO) = '||
'               	   DECODE(PRO_CAL.NEGO_OID_NEGO, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.NEGO_OID_NEGO) '||
'           	 )    '||
'        	AND (DECODE(PRO_CAL.TOFE_OID_TIPO_OFER, '||
'        	      NULL, '||
'                 -1, '||
'                 PRO_CAL.TOFE_OID_TIPO_OFER) = '||
'                  DECODE(PRO_CAL.TOFE_OID_TIPO_OFER, '||
'                     NULL, '||
'                 -1, '||
'                 OFEDET.TOFE_OID_TIPO_OFER) '||
'             AND DECODE(PRO_CAL.CIVI_OID_CICL_VIDA , '||
'                   NULL, '||
'                -1, '||
'                PRO_CAL.CIVI_OID_CICL_VIDA) = '||
'                	 DECODE(PRO_CAL.CIVI_OID_CICL_VIDA, '||
'                	    NULL, '||
'                -1, '||
'                OFEDET.CIVI_OID_CICLO_VIDA) '||
'             ) '||
' )	       '||
'   AND SOL_POS.PROD_OID_PROD NOT IN ( '||
'          	 SELECT '||
'             	DISTINCT '||
'             	PRODU.OID_PROD '||
'          	 FROM '||
'           	  	( '||
'             	SELECT * '||
'             	FROM '||
'               INC_PRODU_EXCLU_CALIF PRO_CAL '||
'             	WHERE PRO_CAL.COPA_OID_PARA_GRAL = '|| idcon || ' ' ||
'             	) PRO_CAL, '||
'             	( '||
'             	SELECT P.OID_PROD, P.COD_SAP, '||
'               P.MAPR_OID_MARC_PROD, P.NEGO_OID_NEGO, P.UNEG_OID_UNID_NEGO, '||
'            	P.GENE_OID_GENE, P.SGEN_OID_SUPE_GENE  '||
'             	FROM '||
'           	 	MAE_PRODU P '||
'             	) PRODU, '||
'             	PRE_OFERT_DETAL OFEDET '||
'           	WHERE 1=1 '||
'           	  AND OFEDET.OID_DETA_OFER = SOL_POS.OFDE_OID_DETA_OFER '||
'           	  AND PRODU.OID_PROD = SOL_POS.PROD_OID_PROD '||
'         AND ( DECODE(PRO_CAL.MAPR_OID_MARC_PROD, '||
'        	           NULL, '||
'               -1, '||
'               PRO_CAL.MAPR_OID_MARC_PROD) = '||
'            DECODE(PRO_CAL.MAPR_OID_MARC_PROD, '||
'                  NULL, '||
'               -1, '||
'               PRODU.MAPR_OID_MARC_PROD) '||
'         AND DECODE(PRO_CAL.UNEG_OID_UNID_NEGO, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.UNEG_OID_UNID_NEGO) = '||
'               	   DECODE(PRO_CAL.UNEG_OID_UNID_NEGO, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.UNEG_OID_UNID_NEGO) '||
'         AND DECODE(PRO_CAL.PROD_OID_PROD, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.PROD_OID_PROD) = '||
'               	   DECODE(PRO_CAL.PROD_OID_PROD, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.OID_PROD) '||
'         AND DECODE(PRO_CAL.GENE_OID_GENE, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.GENE_OID_GENE) = '||
'               	   DECODE(PRO_CAL.GENE_OID_GENE, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.GENE_OID_GENE) '||
'         AND DECODE(PRO_CAL.SGEN_OID_SUPE_GENE, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.SGEN_OID_SUPE_GENE) = '||
'               	   DECODE(PRO_CAL.SGEN_OID_SUPE_GENE, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.SGEN_OID_SUPE_GENE) '||
'         AND DECODE(PRO_CAL.NEGO_OID_NEGO, '||
'               NULL, '||
'               	  -1, '||
'               	  PRO_CAL.NEGO_OID_NEGO) = '||
'               	   DECODE(PRO_CAL.NEGO_OID_NEGO, '||
'               	      NULL, '||
'               	  -1, '||
'               	  PRODU.NEGO_OID_NEGO) '||
'           	 )    '||
'        	AND (DECODE(PRO_CAL.TOFE_OID_TIPO_OFER, '||
'        	      NULL, '||
'                 -1, '||
'                 PRO_CAL.TOFE_OID_TIPO_OFER) = '||
'                  DECODE(PRO_CAL.TOFE_OID_TIPO_OFER, '||
'                     NULL, '||
'                 -1, '||
'                 OFEDET.TOFE_OID_TIPO_OFER) '||
'             AND DECODE(PRO_CAL.CIVI_OID_CICL_VIDA , '||
'                   NULL, '||
'                -1, '||
'                PRO_CAL.CIVI_OID_CICL_VIDA) = '||
'                	 DECODE(PRO_CAL.CIVI_OID_CICL_VIDA, '||
'                	    NULL, '||
'                -1, '||
'                OFEDET.CIVI_OID_CICLO_VIDA) '||
'             ) '||
' 	)	'
 INTO var_aux_num;

   RETURN var_aux_num;
END;


-----------------------------------------------------------------
FUNCTION FN_013_FACDTR_CONCAT_PERI (
      idperi_ini in number,
      idperi_fin in number

)
return varchar2

is

cursor c_cursor is

      SELECT OID_PERI
      FROM CRA_PERIO PER,
             (
             SELECT P.FEC_INIC, P.PAIS_OID_PAIS, P.MARC_OID_MARC, P.CANA_OID_CANA
             FROM CRA_PERIO P
             WHERE P.OID_PERI = idperi_ini
             ) FI,
             (
             SELECT P.FEC_FINA
             FROM CRA_PERIO P
             WHERE P.OID_PERI = idperi_fin
             ) FF
      WHERE PER.FEC_INIC >= FI.FEC_INIC
              AND PER.FEC_FINA <= FF.FEC_FINA
              AND PER.PAIS_OID_PAIS = FI.PAIS_OID_PAIS
              AND PER.MARC_OID_MARC = FI.MARC_OID_MARC
              AND PER.CANA_OID_CANA = FI.CANA_OID_CANA;


    v_return varchar2(8000);
    ret     varchar2(10);

  begin

    OPEN c_cursor;

      LOOP
            FETCH c_cursor INTO ret;
              exit when c_cursor%notfound;
              v_return := v_return||','||ret;
      end loop;
  v_return := v_return||',';
      CLOSE c_cursor;
    return v_return;

end;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Accesos (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT
						   				RPAD( NVL(GEN_ACCESO.VAL_I18N,' ') ,20) || '. '
					FROM
						 				DTO_MATRI_DESCU DMD,
                                  		DTO_PARAM_TIPO_DESCU PTD,
                                 		DTO_VALOR_PARAM_ACCES VPA,
                                 		DTO_PARAM_TIPO_DESCU PTDSUBAC,
                                 		DTO_VALOR_PARAM_SUBAC VPSA,
										SEG_ACCES ACC,
								 		SEG_SUBAC SUBAC,
		                                 (
		                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
		                                    FROM V_GEN_I18N_SICC GEN
		                                    WHERE GEN.ATTR_ENTI = 'SEG_ACCES'
		                                                  AND GEN.IDIO_OID_IDIO = idio_par
		                                 ) GEN_ACCESO,
		                                 (
		                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
		                                    FROM V_GEN_I18N_SICC GEN
		                                    WHERE GEN.ATTR_ENTI = 'SEG_SUBAC'
		                                                  AND GEN.IDIO_OID_IDIO = idio_par
		                                 ) GEN_SUBACCESO
					WHERE
						  				        PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                     AND PTD.PADT_OID_PARA = 5
                                     AND VPA.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                     AND VPA.ACCE_OID_ACCE =  ACC.OID_ACCE
                                     AND VPA.ACCE_OID_ACCE = GEN_ACCESO.VAL_OID(+)
									 AND ACC.OID_ACCE = SUBAC.ACCE_OID_ACCE
                                     AND PTDSUBAC.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                     AND PTDSUBAC.PADT_OID_PARA = 6
                                     AND VPSA.PTDT_PARA_TIPO_DESC = PTDSUBAC.OID_PARA_TIPO_DESC
                                     AND VPSA.SBAC_OID_SBAC =  SUBAC.OID_SBAC
                                     AND VPSA.SBAC_OID_SBAC = GEN_SUBACCESO.VAL_OID(+)
									AND DMD.OID_TIPO_DESC = tdesc_par
					ORDER BY
						   				RPAD( NVL(GEN_ACCESO.VAL_I18N,' ') ,20) || '. ',
										RPAD( NVL(GEN_SUBACCESO.VAL_I18N,' ') ,20) || '. ';

    v_return VARCHAR2(8000);
    ret      VARCHAR2(40);
	tipo_ant VARCHAR2 (40);
  BEGIN
  	 tipo_ant:='';
    OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;

		IF tipo_ant = ret THEN
		 		  ret:=  RPAD('.',20) || '. ';
		ELSE
				tipo_ant := ret;
		END IF;

		  v_return := v_return || ret;
	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Canales (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT RPAD( NVL(GEN_CANAL.VAL_I18N,' ') ,20) || '. '
					FROM DTO_MATRI_DESCU DMD,
									DTO_PARAM_TIPO_DESCU PTD,
									DTO_VALOR_PARAM_CANAL VPC,
									(
									   SELECT GEN.VAL_OID, GEN.VAL_I18N
									    FROM V_GEN_I18N_SICC GEN
									    WHERE GEN.ATTR_ENTI = 'SEG_CANAL'
									                  AND GEN.IDIO_OID_IDIO = idio_par
									) GEN_CANAL
					WHERE PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
									AND PTD.PADT_OID_PARA = 4
									AND VPC.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
									AND VPC.CANA_OID_CANA = GEN_CANAL.VAL_OID(+)
									AND DMD.OID_TIPO_DESC = tdesc_par
					ORDER BY RPAD( GEN_CANAL.VAL_I18N,20);
    v_return VARCHAR2(8000);
    ret      VARCHAR2(40);
  BEGIN
  OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;
		  v_return := v_return || ret;
	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Marcas (
	tdesc_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT RPAD( NVL(MARCA.DES_MARC,' ') ,20) || '. '
					FROM DTO_MATRI_DESCU DMD,
									DTO_PARAM_TIPO_DESCU PTD,
									DTO_VALOR_PARAM_MARCA VPM,
									SEG_MARCA  MARCA
					WHERE PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
									AND PTD.PADT_OID_PARA = 3
									AND VPM.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
									AND VPM.MARC_OID_MARC = MARCA.OID_MARC
									AND DMD.OID_TIPO_DESC = tdesc_par
					ORDER BY RPAD( MARCA.DES_MARC ,20);
    v_return VARCHAR2(8000);
    ret      VARCHAR2(40);
  BEGIN
  OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;
		  v_return := v_return || ret;
	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Nacional (
	tdesc_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT RPAD( NVL(VPN.VAL_PARA ,' ') ,15)
					FROM DTO_MATRI_DESCU DMD,
									DTO_PARAM_TIPO_DESCU PTD,
									DTO_VALOR_PARAM_NACIO VPN
					WHERE PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
									AND PTD.PADT_OID_PARA = 12
									AND VPN.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
									AND DMD.OID_TIPO_DESC = tdesc_par
					ORDER BY RPAD( NVL(VPN.VAL_PARA ,' ') ,15);
    v_return VARCHAR2(500);
    ret      VARCHAR2(20);
  BEGIN
  OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;

		  IF LENGTH(v_return)>0 THEN
		  		  v_return := v_return || '/ ' || ret;
		  ELSE
		  	  v_return := v_return || ret;
		  END IF;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Subaccesos (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT
										RPAD( NVL(GEN_SUBACCESO.VAL_I18N,' ') ,20) || '. '
					FROM
						 				DTO_MATRI_DESCU DMD,
                                  		DTO_PARAM_TIPO_DESCU PTD,
                                 		DTO_VALOR_PARAM_ACCES VPA,
                                 		DTO_PARAM_TIPO_DESCU PTDSUBAC,
                                 		DTO_VALOR_PARAM_SUBAC VPSA,
										SEG_ACCES ACC,
								 		SEG_SUBAC SUBAC,
		                                 (
		                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
		                                    FROM V_GEN_I18N_SICC GEN
		                                    WHERE GEN.ATTR_ENTI = 'SEG_ACCES'
		                                                  AND GEN.IDIO_OID_IDIO = idio_par
		                                 ) GEN_ACCESO,
		                                 (
		                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
		                                    FROM V_GEN_I18N_SICC GEN
		                                    WHERE GEN.ATTR_ENTI = 'SEG_SUBAC'
		                                                  AND GEN.IDIO_OID_IDIO = idio_par
		                                 ) GEN_SUBACCESO
					WHERE
						  				        PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                     AND PTD.PADT_OID_PARA = 5
                                     AND VPA.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                     AND VPA.ACCE_OID_ACCE =  ACC.OID_ACCE
                                     AND VPA.ACCE_OID_ACCE = GEN_ACCESO.VAL_OID(+)
									 AND ACC.OID_ACCE = SUBAC.ACCE_OID_ACCE
                                     AND PTDSUBAC.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                     AND PTDSUBAC.PADT_OID_PARA = 6
                                     AND VPSA.PTDT_PARA_TIPO_DESC = PTDSUBAC.OID_PARA_TIPO_DESC
                                     AND VPSA.SBAC_OID_SBAC =  SUBAC.OID_SBAC
                                     AND VPSA.SBAC_OID_SBAC = GEN_SUBACCESO.VAL_OID(+)
									AND DMD.OID_TIPO_DESC = tdesc_par
					ORDER BY
						   				RPAD( NVL(GEN_ACCESO.VAL_I18N,' ') ,20) || '. ',
										RPAD( NVL(GEN_SUBACCESO.VAL_I18N,' ') ,20) || '. ' ;

    v_return VARCHAR2(8000);
    ret      VARCHAR2(40);
  BEGIN
  OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;
		  v_return := v_return || ret;
	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Subtipo (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT
									RPAD( NVL(GEN_SUBTI.VAL_I18N,' ') ,20) || '. '
					FROM
									DTO_MATRI_DESCU DMD,
									DTO_PARAM_TIPO_DESCU PTD,
									 DTO_VALOR_PARAM_SUBTI_CLIEN VPS,
									 MAE_SUBTI_CLIEN MSC,
									 (
									                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
									                                    FROM V_GEN_I18N_SICC GEN
									                                    WHERE GEN.ATTR_ENTI = 'MAE_TIPO_CLIEN'
									                                                  AND GEN.IDIO_OID_IDIO = idio_par
									 ) GEN_TIPO,
									 (
									                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
									                                    FROM V_GEN_I18N_SICC GEN
									                                    WHERE GEN.ATTR_ENTI = 'MAE_SUBTI_CLIEN'
									                                                  AND GEN.IDIO_OID_IDIO = idio_par
									 ) GEN_SUBTI
					WHERE PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                      AND PTD.PADT_OID_PARA = 9
                                      AND VPS.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                      AND VPS.SBTI_OID_SUBT_CLIE = MSC.OID_SUBT_CLIE
                                      AND MSC.TICL_OID_TIPO_CLIE = GEN_TIPO.VAL_OID(+)
                                      AND MSC.OID_SUBT_CLIE = GEN_SUBTI.VAL_OID(+)
									AND DMD.OID_TIPO_DESC = tdesc_par
					ORDER BY
						   			RPAD( NVL(GEN_TIPO.VAL_I18N,' ') ,20) || '. ',
									RPAD( NVL(GEN_SUBTI.VAL_I18N,' ') ,20) || '. ';

    v_return VARCHAR2(8000);
	subtipo VARCHAR2 (33);
  BEGIN
  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO subtipo;
  EXIT WHEN c_cursor%NOTFOUND ;


		v_return := v_return || subtipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Tipo (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT
						   			RPAD( NVL(GEN_TIPO.VAL_I18N,' ') ,20) || '. '
					FROM
									DTO_MATRI_DESCU DMD,
									DTO_PARAM_TIPO_DESCU PTD,
									 DTO_VALOR_PARAM_SUBTI_CLIEN VPS,
									 MAE_SUBTI_CLIEN MSC,
									 (
									                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
									                                    FROM V_GEN_I18N_SICC GEN
									                                    WHERE GEN.ATTR_ENTI = 'MAE_TIPO_CLIEN'
									                                                  AND GEN.IDIO_OID_IDIO = idio_par
									 ) GEN_TIPO,
									 (
									                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
									                                    FROM V_GEN_I18N_SICC GEN
									                                    WHERE GEN.ATTR_ENTI = 'MAE_SUBTI_CLIEN'
									                                                  AND GEN.IDIO_OID_IDIO = idio_par
									 ) GEN_SUBTI
					WHERE PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                      AND PTD.PADT_OID_PARA = 9
                                      AND VPS.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                      AND VPS.SBTI_OID_SUBT_CLIE = MSC.OID_SUBT_CLIE
                                      AND MSC.TICL_OID_TIPO_CLIE = GEN_TIPO.VAL_OID(+)
                                      AND MSC.OID_SUBT_CLIE = GEN_SUBTI.VAL_OID(+)
									AND DMD.OID_TIPO_DESC = tdesc_par
					ORDER BY
						   			RPAD( NVL(GEN_TIPO.VAL_I18N,' ') ,20) || '. ',
									RPAD( NVL(GEN_SUBTI.VAL_I18N,' ') ,20) || '. ';

    v_return VARCHAR2(8000);
	tipo VARCHAR2 (33);
	tipo_ant VARCHAR2 (33);
  BEGIN
  tipo_ant:='';
  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO tipo;
  EXIT WHEN c_cursor%NOTFOUND ;


		IF tipo_ant = tipo THEN
		 		  tipo:=  RPAD('.',20) || '. ';
		ELSE
				tipo_ant := tipo;
		END IF;

		v_return := v_return || tipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_Accesos (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT RPAD( NVL(GEN_ACCESO.VAL_I18N,' ') ,20) || '  '
					FROM
                                 DTO_DESCU_ACCES ACC,
                                 (
                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
                                    FROM V_GEN_I18N_SICC GEN
                                    WHERE GEN.ATTR_ENTI = 'SEG_ACCES'
                                                  AND GEN.IDIO_OID_IDIO = idio_par
                                 ) GEN_ACCESO
					WHERE ACC.DCTO_OID_DESC = desc_par
                                     AND ACC.ACCE_OID_ACCE = GEN_ACCESO.VAL_OID(+)
					ORDER BY RPAD( GEN_ACCESO.VAL_I18N ,20);
    v_return VARCHAR2(8000);
    ret      VARCHAR2(40);
  BEGIN
  OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;
		  v_return := v_return || ret;
	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_DTO_CLIEN (
	desc_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
				SELECT
								LPAD( CLIEN.COD_CLIE || '  ' ||
								TO_CHAR(NVL(DECODE(DTO_CLIEN.IMP_FIJO,NULL,DTO_CLIEN.VAL_PORC_DESC, DTO_CLIEN.IMP_FIJO),0),'99999.99') || ' ' ||
								DECODE(DTO_CLIEN.IMP_FIJO,NULL,'%', ' ') ,26)
				FROM
								DTO_DESCU DES,
								DTO_ALCAN_DTO_CLIEN DTO_CLIEN,
								MAE_CLIEN CLIEN
				WHERE
								DES.OID_DESC = desc_par
								AND DES.OID_DESC = DTO_CLIEN.DCTO_OID_DESC
								AND DTO_CLIEN.CLIE_OID_CLIE = CLIEN.OID_CLIE
				ORDER BY
								CLIEN.COD_CLIE;
    v_return VARCHAR2(8000);
	aux VARCHAR2 (26);
  BEGIN
  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO aux;
  EXIT WHEN c_cursor%NOTFOUND;
		v_return := v_return || aux;
  END LOOP;
  CLOSE c_cursor;
  RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_REGIO (
	desc_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
				SELECT RPAD( NVL(REGIO.COD_REGI,' ') ,5)
				FROM
								DTO_DESCU DES,
	 							DTO_ALCAN_DTO_ADMIN DTO_ADM,
								ZON_REGIO REGIO
				WHERE
								DES.OID_DESC = DTO_ADM.DCTO_OID_DESC
								AND DTO_ADM.ZORG_OID_REGI = REGIO.OID_REGI
								AND DES.OID_DESC = desc_par
				ORDER BY RPAD( NVL(REGIO.COD_REGI,' ') ,5);
    v_return VARCHAR2(8000);
	subtipo VARCHAR2 (5);
  BEGIN
  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO subtipo;
  EXIT WHEN c_cursor%NOTFOUND ;

		v_return := v_return || subtipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_Subaccesos (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT RPAD( NVL(GEN_SUBACCESO.VAL_I18N,' ') ,20) || '  '
					FROM
	 					   		 DTO_DESCU_SUBAC SUBACCESO,
                                 (
                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
                                    FROM V_GEN_I18N_SICC GEN
                                    WHERE GEN.ATTR_ENTI = 'SEG_SUBAC'
                                                  AND GEN.IDIO_OID_IDIO = idio_par
                                 ) GEN_SUBACCESO
					WHERE SUBACCESO.DCTO_OID_DESC = desc_par
                                     AND SUBACCESO.SBAC_OID_SBAC = GEN_SUBACCESO.VAL_OID(+)
					ORDER BY RPAD( GEN_SUBACCESO.VAL_I18N ,20);
    v_return VARCHAR2(8000);
    ret      VARCHAR2(40);
  BEGIN
  OPEN c_cursor;
	LOOP
		FETCH c_cursor INTO ret;
		  EXIT WHEN c_cursor%NOTFOUND;
		  v_return := v_return || ret;
	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_SUBG (
	desc_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
				SELECT RPAD( NVL(SUBG.COD_SUBG_VENT,' ') ,5)
				FROM
								DTO_DESCU DES,
	 							DTO_ALCAN_DTO_ADMIN DTO_ADM,
								ZON_SUB_GEREN_VENTA SUBG
				WHERE
								DES.OID_DESC = DTO_ADM.DCTO_OID_DESC
								AND DTO_ADM.ZSGV_OID_SUBG_VENT = SUBG.OID_SUBG_VENT
								AND DES.OID_DESC = desc_par
				ORDER BY RPAD( NVL(SUBG.COD_SUBG_VENT,' ') ,5);
    v_return VARCHAR2(8000);
	subtipo VARCHAR2 (5);
  BEGIN
  OPEN c_cursor;
  LOOP

  	    FETCH c_cursor INTO subtipo;
 	EXIT WHEN c_cursor%NOTFOUND ;

		v_return := v_return || subtipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_Subtipo (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
				SELECT RPAD( NVL(GEN_STC.VAL_I18N,' ') ,20) --|| '. '
				FROM
								DTO_DESCU DES,
								DTO_DESCU_SUBTI_CLIEN DTO_STCLIEN,
								(
								SELECT GEN.VAL_OID, GEN.VAL_I18N
								FROM V_GEN_I18N_SICC GEN
								WHERE GEN.ATTR_ENTI = 'MAE_SUBTI_CLIEN'
								AND GEN.IDIO_OID_IDIO = idio_par
								) GEN_STC
				WHERE
								DES.OID_DESC = DTO_STCLIEN.DCTO_OID_DESC
								AND DTO_STCLIEN.SBTI_OID_SUBT_CLIE = GEN_STC.VAL_OID(+)
								AND DES.OID_DESC = desc_par
				ORDER BY RPAD( GEN_STC.VAL_I18N ,20);
    v_return VARCHAR2(8000);
	subtipo VARCHAR2 (33);
  BEGIN
  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO subtipo;
  EXIT WHEN c_cursor%NOTFOUND ;

		v_return := v_return || subtipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_Tipo (
	desc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
				SELECT RPAD( NVL(GEN_TCLIEN.VAL_I18N,' ') ,20) || '. '
				FROM
								DTO_DESCU DES,
								DTO_DESCU_TIPO_CLIEN  DTO_TCLIEN,
								DTO_DESCU_SUBTI_CLIEN DTO_STCLIEN,
								(
								SELECT GEN.VAL_OID, GEN.VAL_I18N
								FROM V_GEN_I18N_SICC GEN
								WHERE GEN.ATTR_ENTI = 'MAE_TIPO_CLIEN'
								AND GEN.IDIO_OID_IDIO = idio_par
								) GEN_TCLIEN
				WHERE
								DES.OID_DESC = DTO_TCLIEN.DCTO_OID_DESC
								AND DES.OID_DESC = DTO_STCLIEN.DCTO_OID_DESC(+)
								AND DTO_TCLIEN.TICL_OID_TIPO_CLIE = GEN_TCLIEN.VAL_OID(+)
								AND DES.OID_DESC = desc_par
				ORDER BY RPAD( GEN_TCLIEN.VAL_I18N ,20);
    v_return VARCHAR2(8000);
	tipo VARCHAR2 (33);
	tipo_ant VARCHAR2 (33);
  BEGIN
  tipo_ant:='';
  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO tipo;
  		EXIT WHEN c_cursor%NOTFOUND ;

		IF tipo_ant = tipo THEN
		 		  tipo:=  RPAD('.',20) || '. ';
		ELSE
				tipo_ant := tipo;
		END IF;

		v_return := v_return || tipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_033_Concat_ZONA (
	desc_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
				SELECT RPAD( NVL(ZONA.COD_ZONA,' ') ,7)
				FROM
								DTO_DESCU DES,
	 							DTO_ALCAN_DTO_ADMIN DTO_ADM,
								ZON_ZONA ZONA
				WHERE
								DES.OID_DESC = DTO_ADM.DCTO_OID_DESC
								AND DTO_ADM.ZZON_OID_ZONA = ZONA.OID_ZONA
								AND DES.OID_DESC = desc_par
				ORDER BY RPAD( NVL(ZONA.COD_ZONA,' ') ,7);
    v_return VARCHAR2(8000);
	subtipo VARCHAR2 (7);
  BEGIN
  OPEN c_cursor;
  LOOP

  	    FETCH c_cursor INTO subtipo;
  EXIT WHEN c_cursor%NOTFOUND ;

		v_return := v_return || subtipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION FN_003_CONCAT_FECHAS (
            idperi IN NUMBER,
            idacti IN NUMBER,
            idcod_grup IN NUMBER
)
RETURN VARCHAR2
IS

CURSOR c_cursor IS
SELECT  TO_CHAR(GRU.FEC_PREV,'dd/mm/yyyy')||';'
            FROM CRA_CRONO_GRUPO_ZONA GRU,
                                                 CRA_ACTIV ACT,
                                                CRA_CABEC_GRUPO_ZONA CAB
            WHERE CAB.COD_GRUP = idcod_grup
                          AND  GRU.CACT_OID_ACTI=ACT.OID_ACTI
                          AND ACT.OID_ACTI= idacti
                                    AND GRU.CGZO_OID_CABE_GRUP_ZONA=CAB.OID_CABE_GRUP_ZONA
                                   AND GRU.PERD_OID_PERI =idperi
            ORDER BY GRU.FEC_PREV;

     v_return VARCHAR2( 8000);
     ret     VARCHAR2( 20 );

  BEGIN

    OPEN c_cursor;
            LOOP
                        FETCH c_cursor INTO ret;
                          EXIT WHEN c_cursor%NOTFOUND;
                          v_return := v_return||ret;
            END LOOP;
            CLOSE c_cursor;
    RETURN v_return;

END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Clasi (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT
						  	 	   	RPAD( NVL(GEN_CLASI.VAL_I18N,' ') ,20) || '. '
					FROM
						 			DTO_MATRI_DESCU DMD,
					            	DTO_PARAM_TIPO_DESCU PTD,
					            	DTO_VALOR_PARAM_TIPO_CLASI VPTC,
					            	DTO_VALOR_PARAM_CLASI VPC,
									MAE_TIPO_CLASI_CLIEN TCLASI,
								 	 MAE_CLASI CLASI,
	                                 (
	                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
	                                    FROM V_GEN_I18N_SICC GEN
	                                    WHERE GEN.ATTR_ENTI = 'MAE_TIPO_CLASI_CLIEN'
	                                                  AND GEN.IDIO_OID_IDIO = idio_par
	                                 ) GEN_TCLASI,
	                                 (
	                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
	                                    FROM V_GEN_I18N_SICC GEN
	                                    WHERE GEN.ATTR_ENTI = 'MAE_CLASI'
	                                                  AND GEN.IDIO_OID_IDIO = idio_par
	                                 ) GEN_CLASI
					WHERE
						  		 		   		 PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                      AND PTD.PADT_OID_PARA = 10
                                      AND CLASI.TCCL_OID_TIPO_CLAS = TCLASI.OID_TIPO_CLAS
									  AND DMD.OID_TIPO_DESC = tdesc_par
-- TIPO
                                      AND VPTC.PTDT_OID_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                      AND VPTC.TCCL_OID_TIPO_CLAS = TCLASI.OID_TIPO_CLAS
                                      AND VPTC.TCCL_OID_TIPO_CLAS = GEN_TCLASI.VAL_OID(+)
-- CLASIFICACION
                                      AND VPC.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                      AND VPC.CLAS_OID_CLAS =CLASI.OID_CLAS
                                      AND CLASI.OID_CLAS = GEN_CLASI.VAL_OID(+)
					ORDER BY
						  	 		  	  	RPAD( NVL(GEN_TCLASI.VAL_I18N,' ') ,20) || '. ',
						  	 	   			RPAD( NVL(GEN_CLASI.VAL_I18N,' ') ,20)|| '. ' ;

    v_return VARCHAR2(4000);
	tipo VARCHAR2 (30);
  BEGIN

  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO tipo;
  		EXIT WHEN c_cursor%NOTFOUND ;

		v_return := v_return || tipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
FUNCTION Fn_Dto_036_Concat_Tipo_Clasi (
	tdesc_par IN NUMBER,
	idio_par IN NUMBER
)
RETURN VARCHAR2
IS
  CURSOR c_cursor IS
					SELECT
						   			RPAD( NVL(GEN_TCLASI.VAL_I18N,' ') ,20) || '. '
					FROM
						 			DTO_MATRI_DESCU DMD,
					            	DTO_PARAM_TIPO_DESCU PTD,
					            	DTO_VALOR_PARAM_TIPO_CLASI VPTC,
					            	DTO_VALOR_PARAM_CLASI VPC,
									MAE_TIPO_CLASI_CLIEN TCLASI,
								 	 MAE_CLASI CLASI,
	                                 (
	                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
	                                    FROM V_GEN_I18N_SICC GEN
	                                    WHERE GEN.ATTR_ENTI = 'MAE_TIPO_CLASI_CLIEN'
	                                                  AND GEN.IDIO_OID_IDIO = idio_par
	                                 ) GEN_TCLASI,
	                                 (
	                                   SELECT GEN.VAL_OID, GEN.VAL_I18N
	                                    FROM V_GEN_I18N_SICC GEN
	                                    WHERE GEN.ATTR_ENTI = 'MAE_CLASI'
	                                                  AND GEN.IDIO_OID_IDIO = idio_par
	                                 ) GEN_CLASI
					WHERE
						  		 		   		 PTD.MDES_OID_TIPO_DESC = DMD.OID_TIPO_DESC
                                      AND PTD.PADT_OID_PARA = 10
                                      AND CLASI.TCCL_OID_TIPO_CLAS = TCLASI.OID_TIPO_CLAS
									  AND DMD.OID_TIPO_DESC = tdesc_par
-- TIPO
                                      AND VPTC.PTDT_OID_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                      AND VPTC.TCCL_OID_TIPO_CLAS = TCLASI.OID_TIPO_CLAS
                                      AND VPTC.TCCL_OID_TIPO_CLAS = GEN_TCLASI.VAL_OID(+)
-- CLASIFICACION
                                      AND VPC.PTDT_PARA_TIPO_DESC = PTD.OID_PARA_TIPO_DESC
                                      AND VPC.CLAS_OID_CLAS =CLASI.OID_CLAS
                                      AND CLASI.OID_CLAS = GEN_CLASI.VAL_OID(+)
					ORDER BY
						  	 		  	  	RPAD( NVL(GEN_TCLASI.VAL_I18N,' ') ,20) || '. ',
						  	 	   			RPAD( NVL(GEN_CLASI.VAL_I18N,' ') ,20) || '. ';

    v_return VARCHAR2(8000);
	tipo VARCHAR2 (30);
	tipo_ant VARCHAR2 (30);
  BEGIN
  tipo_ant:='';
  OPEN c_cursor;
  LOOP
  	    FETCH c_cursor INTO tipo;
  		EXIT WHEN c_cursor%NOTFOUND ;

		IF tipo_ant = tipo THEN
		 		  tipo:=  RPAD('.',20) || '. ';
		ELSE
				tipo_ant := tipo;
		END IF;

		v_return := v_return || tipo;

	END LOOP;
	CLOSE c_cursor;
    RETURN v_return;
END;
-----------------------------------------------------------------
end pq_apl_rep;
/

