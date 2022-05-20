package projeto.alocacao;

import java.util.Arrays;

public enum TipoAlocacao {

   FIRST_FIT(new AlgoritmoFirstFit(),1), BESTFIT(new AlgoritmoBestFit(),2), NEXTFIT(new AlgoritmoNextFit(),3), WORSTFIT(new AlgoritmoWorstFit(),4);

   private Integer id;

   private Algoritmo algoritmo;

   TipoAlocacao(Algoritmo algoritmo, Integer id) {
      this.algoritmo = algoritmo;
      this.id = id;
   }

   public Algoritmo getAlgoritmo() {
      return algoritmo;
   }

   public Integer getId() {
      return id;
   }

   public static TipoAlocacao getAlgoritmo(Integer id) {
      return Arrays.stream(values()).filter(v -> v.getId() == id).findFirst().orElse(TipoAlocacao.FIRST_FIT);

   }

}
