import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;


public class Main {
    private static MT mt;
    private static TablaInstruccionTransicion tablaIT;

    public static MT createMT(XSSFSheet sheet){
        String cintaAux = sheet.getRow(3).getCell(1).toString() + sheet.getRow(3).getCell(2).toString() + sheet.getRow(3).getCell(3).toString();
        int posInicial = (int)sheet.getRow(3).getCell(4).getNumericCellValue();
        return new MT(posInicial, cintaAux);
    }

    public static void createTabla(XSSFSheet sheet){
        int startRow = 2;
        int estado;
        String expresion;
        String instruccion;
        int transicion;
        tablaIT = new TablaInstruccionTransicion();
        System.out.println();
        System.out.println();
        System.out.print(" | ");
        for(int i = 5; i <= 8; i++) {
            System.out.print(sheet.getRow(startRow).getCell(i) + " | ");
        }
        startRow++;
        System.out.println();
        while(sheet.getRow(startRow).getCell(5).getRawValue() != null) {
            estado = (int)sheet.getRow(startRow).getCell(5).getNumericCellValue();
            expresion = sheet.getRow(startRow).getCell(6).getStringCellValue();
            instruccion = sheet.getRow(startRow).getCell(7).getStringCellValue();
            transicion = (int) sheet.getRow(startRow).getCell(8).getNumericCellValue();

            tablaIT.putIntruccion(estado, expresion, instruccion);
            tablaIT.putTransicion(estado, expresion, transicion);
            System.out.println(" | " + estado + " | " + expresion + " | " + instruccion + " | " + transicion + " | ");
            startRow++;
        }
        System.out.println();
        System.out.println();
    }

    public static void executeMT(){
        int estadoActual;
        char expresionCinta;
        String instruccion;
        int transicion;
        boolean terminado = false;

        System.out.println("Estado Inicial de la Cinta: ");
        while(!terminado){
            mt.printMT();

            estadoActual = tablaIT.getEstadoActual();
            expresionCinta = mt.getExpresionCinta();

            instruccion = tablaIT.getIntruccion(estadoActual, expresionCinta);
            System.out.println("Instruccion Ejecutada: " + instruccion);
            transicion = tablaIT.getTransicion(estadoActual, expresionCinta);
            System.out.println("Nuevo Estado K: " + transicion);

            switch (instruccion) {
                case "L" -> mt.left();
                case "R" -> mt.right();
                case "*" -> mt.putExpresionCinta('*');
                case "I" -> mt.putExpresionCinta('I');
                case "H" -> terminado = true;
                default -> throw new RuntimeException("Caracter Invalido");
            }
            tablaIT.putEstadoActual(transicion);

        }
        System.out.println("Estado Final de la Cinta:");
        mt.printMT();
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/MT.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        mt = createMT(sheet);

        createTabla(sheet);

        executeMT();
    }
}

