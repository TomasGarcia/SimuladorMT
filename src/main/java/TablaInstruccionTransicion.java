package main.java;

import java.util.HashMap;
import java.util.Map;

public class TablaInstruccionTransicion {
   public int estadoActual;
   public Map<Integer, Map<String, String>> instruccion;
   public Map<Integer, Map<String, Integer>> transicion;


   public TablaInstruccionTransicion(){
      this.estadoActual = 0;
      this.instruccion = new HashMap<>();
      this.transicion = new HashMap<>();
   }

   public int getEstadoActual() {
      return estadoActual;
   }

   public void putEstadoActual(int estado){
      this.estadoActual = estado;
   }

   public String getIntruccion(int estado, char expresionCinta){
      return instruccion.get(estado).get(""+expresionCinta);
   }

   public void putIntruccion(int estado, String expresionCinta, String instr){
      Map<String, String> aux = this.instruccion.get(estado);

      if(aux == null)
         aux = new HashMap<>();

      aux.put(expresionCinta, instr);
      this.instruccion.put(estado, aux);
   }

   public int getTransicion(int estado, char expresionCinta){
      return transicion.get(estado).get(""+expresionCinta);
   }

   public void putTransicion(int estado, String expresionCinta, int estadoSig){
      Map<String, Integer> aux = this.transicion.get(estado);

      if(aux == null)
         aux = new HashMap<>();

      aux.put(expresionCinta, estadoSig);
      this.transicion.put(estado, aux);
   }
}
