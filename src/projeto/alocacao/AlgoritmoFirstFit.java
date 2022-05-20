package projeto.alocacao;

public class AlgoritmoFirstFit extends Algoritmo {

    @Override
    public void inserir(Memoria memoria, Processo p) {
    	memoria.adicionarFirstFit(p);
    }

 
}


