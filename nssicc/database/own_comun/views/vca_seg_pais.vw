CREATE OR REPLACE VIEW VCA_SEG_PAIS AS
SELECT T1.COD_USUA COD_USUA
          ,T2.OID_PAIS OID_PAIS
          ,T2.COD_PAIS COD_PAIS
          ,T2.VAL_IDEN VAL_IDEN
          ,T2.VAL_PROG_EJEC VAL_PROG_EJEC
          ,T2.VAL_PORC_ALAR VAL_PORC_ALAR
          ,T2.VAL_TIEM_REZO VAL_TIEM_REZO
          ,T2.VAL_CONF_SECU_CCC VAL_CONF_SECU_CCC
          ,T2.NUM_LIMI_DIFE_PAGO NUM_LIMI_DIFE_PAGO
          ,T2.VAL_MAXI_DIFE_ANLS_COMB VAL_MAXI_DIFE_ANLS_COMB
          ,T2.NUM_DIAS_MORA NUM_DIAS_MORA
          ,T2.NUM_DIAS_FACT NUM_DIAS_FACT
          ,T2.NUM_POSI_NUME_CLIE NUM_POSI_NUME_CLIE
          ,T2.MONE_OID_MONE MONE_OID_MONE
          ,T2.MONE_OID_MONE_ALT MONE_OID_MONE_ALT
          ,T2.VAL_FORM_FECH VAL_FORM_FECH
          ,T2.VAL_SEPA_MILE VAL_SEPA_MILE
          ,T2.VAL_SEPA_DECI VAL_SEPA_DECI
          ,T2.NUM_PERI_EGRE NUM_PERI_EGRE
          ,T2.NUM_PERI_RETI NUM_PERI_RETI
          ,T2.IND_SALD_UNIC IND_SALD_UNIC
          ,T2.IND_INTE_GIS IND_INTE_GIS
          ,T2.IND_COMP_AUTO IND_COMP_AUTO
          ,T2.IND_TRAT_ACUM_DESC IND_TRAT_ACUM_DESC
          ,T2.IND_EMIS_VENC IND_EMIS_VENC
          ,T2.FOPA_OID_FORM_PAGO FOPA_OID_FORM_PAGO
          ,T2.VAL_URL VAL_URL
          ,T2.VAL_COMP_TELE VAL_COMP_TELE
          ,T2.IND_FLET_ZONA_UBIG IND_FLET_ZONA_UBIG
          ,T2.VAL_INDI_SECU_MONI VAL_INDI_SECU_MONI
          ,T2.IND_SECU IND_SECU
          ,T2.IND_BALA_AREA_CHEQ IND_BALA_AREA_CHEQ
          ,T2.IND_IMPU_INCL IND_IMPU_INCL
FROM VCA_MGU_PERMI_USUAR T1
    ,SEG_PAIS T2
  WHERE t1.val_prop = to_char(t2.oid_pais)
    and t1.cod_prop = 'Pais';
comment on column VCA_SEG_PAIS.COD_USUA is 'Código de usuario';
comment on column VCA_SEG_PAIS.OID_PAIS is 'Identificador único secuencial del país';
comment on column VCA_SEG_PAIS.COD_PAIS is 'Clave natural o de negocio del país';
comment on column VCA_SEG_PAIS.VAL_IDEN is 'Identificador:  Boleta o Factura';
comment on column VCA_SEG_PAIS.VAL_PROG_EJEC is 'Programa ejecución';
comment on column VCA_SEG_PAIS.VAL_PORC_ALAR is 'Porcentaje de alarma';
comment on column VCA_SEG_PAIS.VAL_TIEM_REZO is 'Tiempo rezonificación';
comment on column VCA_SEG_PAIS.VAL_CONF_SECU_CCC is 'Configuracion secuencial CCC';
comment on column VCA_SEG_PAIS.NUM_LIMI_DIFE_PAGO is 'Limite diferencia de pagos';
comment on column VCA_SEG_PAIS.VAL_MAXI_DIFE_ANLS_COMB is 'Máxima diferencia analisis combinatorio';
comment on column VCA_SEG_PAIS.NUM_DIAS_MORA is 'Número de días de mora';
comment on column VCA_SEG_PAIS.NUM_DIAS_FACT is 'Numero días facturacion';
comment on column VCA_SEG_PAIS.NUM_POSI_NUME_CLIE is 'Longitud o número de posiciciones de un número de cliente';
comment on column VCA_SEG_PAIS.MONE_OID_MONE is 'Identificador único de moneda';
comment on column VCA_SEG_PAIS.MONE_OID_MONE_ALT is 'Identificador único de moneda';
comment on column VCA_SEG_PAIS.VAL_FORM_FECH is 'Formato de fecha';
comment on column VCA_SEG_PAIS.VAL_SEPA_MILE is 'Separador de miles';
comment on column VCA_SEG_PAIS.VAL_SEPA_DECI is 'Separador de decimales';
comment on column VCA_SEG_PAIS.NUM_PERI_EGRE is 'Número de periodos de egreso';
comment on column VCA_SEG_PAIS.NUM_PERI_RETI is 'Número de periodos de retiro';
comment on column VCA_SEG_PAIS.IND_SALD_UNIC is 'Indicador de saldo único (verdadero/falso)';
comment on column VCA_SEG_PAIS.IND_INTE_GIS is 'Indicador de integración con GIS  (G ó R)';
comment on column VCA_SEG_PAIS.IND_COMP_AUTO is 'Indicador de compensación autómatica (verdadero/falso)';
comment on column VCA_SEG_PAIS.IND_TRAT_ACUM_DESC is 'Indicador de tratamiento acumulativo descuentos (verdadero/falso)';
comment on column VCA_SEG_PAIS.IND_EMIS_VENC is 'Indicador emision vencimiento';
comment on column VCA_SEG_PAIS.FOPA_OID_FORM_PAGO is 'Oid forma de pago';
comment on column VCA_SEG_PAIS.VAL_URL is 'URL para la aplicación asociada a este país';
comment on column VCA_SEG_PAIS.VAL_COMP_TELE is 'Compañía Telefónica';
comment on column VCA_SEG_PAIS.IND_FLET_ZONA_UBIG is 'Indicador de flete zona ubigeo. Admite los valores ''Z'' (Zona) y ''U'' (Ubigeo)';
comment on column VCA_SEG_PAIS.VAL_INDI_SECU_MONI is 'Indicador de secuencia de monitoror (S ó N)';
comment on column VCA_SEG_PAIS.IND_SECU is 'Se utiliza en el CU Arrancar facturacion (FAC) con los valores: M-> Monitor, T-> Transporte.';
comment on column VCA_SEG_PAIS.IND_BALA_AREA_CHEQ is 'P: MODELO PERU (esto lo mira el proceso de Arrancar Facturación y según este valor activará el caso de uso Generar Marcas de Chequeo de APP)C: COLOMBIA (esto lo mira el proceso de Arrancar Facturación y según este valor activará el caso de uso Balanceo de Area de Chequeo APE)N: NULO (esto lo mira el proceso de Arrancar Facturación y según este valor no activará ningún caso de uso y pasará al siguiente punto del flujo normal)';
comment on column VCA_SEG_PAIS.IND_IMPU_INCL is 'Valores posibles: S/N.  Determina si los precios unitarios tienen incluidos o no los impuestos. Se usa en el modulo FAC.';

