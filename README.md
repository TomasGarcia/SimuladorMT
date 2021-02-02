# SimuladorMT
Simulador de Máquinas de Turing con el alfabeto "palote" para la asignatura de TALF

Consideraciones a tener en cuenta en el Excel (MT.xlsx) que define la Máquina de Turing (de ahora en adelante MT):
  - K (estados) -> Números [0..n]
  - Σ -> {*, I} (i mayúscula)
  - ɣ -> {L,R,H} + Σ (L, R y H en Mayúsculas para no confundir L con I, tener una mayor claridad)
  - δ -> Números entre [0..n]

La estructura del Excel es la siguiente:
  - Entrada: Asteriscos delante y detrás de la cadena que entra en la cinta, simulando infinitos carácteres vacíos delate y detrás (NO CAMBIAR). 
    Parte central -> cambiar a gusto con el Alfabeto Palote (Σ) según la MT.
   
  - POSICIONES: posiciones de los respectivos carácteres que se actualiza con la fila superior (dependiendo del número de carácteres de la entrada)
   
  - POS INICIAL: Un número natural que se corresponde con la posición donde va a comenzar la MT (un número de POSICIONES)
  
  - Tabla de K, Σ, ɣ, δ (Tabla de Instrucción y Transición) -> Rellenar con nuestra MT
  
  - A la derecha en el Excel podemos observar ejemplos de MT, simplemente habría que copiar su contenido, sin incluir las instrucciones) y pegarla en la Tabla anterior.
  
Por otro lado, la instalación y ejecución del programa simplemente debe ser Clonar el Repositorio, importar el Proyecto a IntelliJ IDEA y Click Derecho -> Run 
en /src/Main.java o con la Clase Abierta Ctrl+Shift+F10
