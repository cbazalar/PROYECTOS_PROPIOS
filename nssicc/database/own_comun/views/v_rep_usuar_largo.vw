CREATE OR REPLACE VIEW V_REP_USUAR_LARGO
(cod_usua, val_nomb_usua, val_apel_pate, val_ape_mate, val_nom1, val_nom2)
AS
SELECT us_ex.IDUSER AS IDUSUARIO, pr_ex.NAME AS NOMBREUSUARIO,
	      NVL((SELECT p.STRINGVALUE FROM propertyvalues p, principals pr_in, users us WHERE pr_in.IDPRINCIPAL=pr_ex.IDPRINCIPAL AND us.IDUSER=p.IDPRINCIPAL AND pr_in.IDPRINCIPAL=p.IDPRINCIPAL AND p.IDPROPERTY=2),'') AS APELLIDOPATERNO,
	      NVL((SELECT p.STRINGVALUE FROM propertyvalues p, principals pr_in, users us WHERE pr_in.IDPRINCIPAL=pr_ex.IDPRINCIPAL AND us.IDUSER=p.IDPRINCIPAL AND pr_in.IDPRINCIPAL=p.IDPRINCIPAL AND p.IDPROPERTY=3),'') AS APELLIDOMATERNO,
	      NVL((SELECT p.STRINGVALUE FROM propertyvalues p, principals pr_in, users us WHERE pr_in.IDPRINCIPAL=pr_ex.IDPRINCIPAL AND us.IDUSER=p.IDPRINCIPAL AND pr_in.IDPRINCIPAL=p.IDPRINCIPAL AND p.IDPROPERTY=5),'') AS NOMBRE1,
	      NVL((SELECT p.STRINGVALUE FROM propertyvalues p, principals pr_in, users us WHERE pr_in.IDPRINCIPAL=pr_ex.IDPRINCIPAL AND us.IDUSER=p.IDPRINCIPAL AND pr_in.IDPRINCIPAL=p.IDPRINCIPAL AND p.IDPROPERTY=6),'') AS NOMBRE2
FROM principals pr_ex, users us_ex WHERE us_ex.IDUSER=pr_ex.IDPRINCIPAL;

