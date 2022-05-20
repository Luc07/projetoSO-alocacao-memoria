package projeto.alocacao;

public class AlgoritmoNextFit extends Algoritmo {

   
    @Override
    public void inserir(Memoria memoria, Processo processo) {
    	memoria.adicionarNextFit(processo);
    }

   
}
