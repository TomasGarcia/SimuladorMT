package main.java;

public class MT {
    private int posicion;
    private String cintaRaw;
    private char[] cinta;


    public MT(int pos, String cintaRaw){
        if(pos < 0 || pos > cintaRaw.length())
            throw new ArrayIndexOutOfBoundsException("Posicion inicial invalida");
        else
            this.posicion = pos;
            this.cintaRaw = cintaRaw;
            this.cinta = cintaRaw.toCharArray();
    }

    public void printMT(){
        for (char c:
             this.cinta) {
            System.out.print( "|" + c);
        }

        System.out.println("|");
        for (int i = 0; i <= this.posicion * 2; i++){
            System.out.print(" ");
        }
        System.out.println("-");
        System.out.println();
    }

    public int getPosicion(){
        return this.posicion;
    }

    public void putExpresionCinta(char caracter){
        this.cinta[this.posicion] = caracter;
    }
    public char getExpresionCinta(){
        return this.cinta[this.posicion];
    }

    public void left(){
        if(this.posicion > 1)
            this.posicion--;
        else if (this.posicion == 1){
            this.ampliarIzquierda();
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void right(){
        if(this.posicion < this.cinta.length - 2)
            this.posicion++;
        else if (this.posicion == this.cinta.length - 2) {
            this.ampliarDerecha();
            this.posicion++;
        }else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void ampliarDerecha(){
        this.cintaRaw = "";
        for (char c : this.cinta) {
            this.cintaRaw += c;
        }
        this.cintaRaw += "*";
        this.cinta = this.cintaRaw.toCharArray();
    }

    public void ampliarIzquierda(){
        this.cintaRaw = "*";
        for (char c : this.cinta) {
            this.cintaRaw += c;
        }
        this.cinta = this.cintaRaw.toCharArray();
    }

    public int getLength(){
        return this.cintaRaw.length();
    }
}
