PROCEDURE TODOS_DNI
  (
    TYPE TCURSOR IS REF CURSOR,
    CURSOR OUT TCURSOR 
  )
  IS
  BEGIN
    OPEN TCURSOR FOR
		select DNI
		from TRABAJADORES; 
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR(-20101, 'No hay trabajadores dados de alta');
  END TODOS_DNI;
  

  
PROCEDURE DATOS_COMPLETOS_TRABAJADOR
  (
    TYPE TCURSOR IS REF CURSOR,
    CDNI IN TRABAJADORES.DNI%TYPE, CURSOR OUT TRABAJADOR_CURSOR
  )
  IS
  BEGIN  
     OPEN TCURSOR FOR 
        SELECT DNI, FECHA_NACIMIENTO, APELLIDO1, APELLIDO2, DIRECCION, TELEFONO_PERSONAL, TELEFONO_EMPEMPRESA, CENTRO_NO, AVISOS, CAATEGORIA
        FROM TRABAJADORES
        WHERE DNI = CDNI;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR(-20202,'No hay trabajadores dados de alta que coincidan con esos datos');
  END DATOS_COMPLETOS_TRABAJADOR;
  
PROCEDURE BUSCAR_TRABAJADOR_NOMBRE
  (
    TYPE TCURSOR IS REF CURSOR,
    TDNI IN TRABAJADORES.DNI%TYPE,
    TCURSOR OUT TRABAJADOR_CURSOR
  )
  IS
  BEGIN
    OPEN TCURSOR FOR 
		SELECT NOMBRE FROM TRABAJADORES WHERE DNI=TDNI;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR(-20303,'No hay trabajadores dados de alta que coincidan con esos datos');
  END BUSCAR_TRABAJADOR_NOMBRE;