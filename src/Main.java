import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
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
        for(int i = 5; i <= 8; i++){
            System.out.print(sheet.getRow(startRow).getCell(i) + " ");
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
            System.out.println(estado + " " + expresion + " " + instruccion + " " + transicion);
            startRow++;
        }
    }

    public static void executeMT(){
        int estadoActual;
        char expresionCinta;
        String instruccion;
        int transicion;

        while(mt.getExpresionCinta() != 'H'){
            mt.printMT();
            estadoActual = tablaIT.getEstadoActual();
            expresionCinta = mt.getExpresionCinta();

            instruccion = tablaIT.getIntruccion(estadoActual, expresionCinta);
            System.out.println("Instruccion: " + instruccion);
            transicion = tablaIT.getTransicion(estadoActual, expresionCinta);
            System.out.println("Transicion " + transicion);

            switch(instruccion){
                case "L": mt.left(); break;
                case "R": mt.right();  break;
                case "*": mt.putExpresionCinta('*');  break;
                case "I": mt.putExpresionCinta('I');  break;
                case "H": mt.putExpresionCinta('H'); break;
                default: break;
            }
            tablaIT.putEstadoActual(transicion);

        }
        mt.printMT();
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "/MT.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        mt = createMT(sheet);

        createTabla(sheet);
/*
        System.out.println(estados.transicion.values());
        System.out.println(mt.getExpresionCinta());
        System.out.println(estados.getEstadoActual());
        System.out.println(estados.getIntruccion(estados.getEstadoActual(), mt.getExpresionCinta()));
        System.out.println(estados.instruccion.get(estados.getEstadoActual()).get("I"));
        //System.out.println(estados.getTransicion(estados.getEstadoActual(),mt.getExpresionCinta()));
           mt.right();
        mt.right();
        mt.right();
        mt.printMT();
*/
        executeMT();
    }
}

