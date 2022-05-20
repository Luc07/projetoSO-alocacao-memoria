package projeto.alocacao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TimerTask;

public class Main{

    @SuppressWarnings("unused")
	private static int cont = 0;

	public static void main(String[] args) throws InterruptedException {
    	
    	LinkedList<Processo> descarte = new LinkedList<Processo>();
        Scanner teclado = new Scanner(System.in);
        GeradorDeProcessos g = new GeradorDeProcessos();
        Memoria m = new Memoria();
        System.out.println("Qual o tipo de algoritmo que deseja: 1 - First_Fit, 2 - BestFit, 3 - NextFit, 4 - WorstFit por padrão será utilizado o FirstFit.");
        int tipoId = teclado.nextInt();
        
        TimerTask task = new TimerTask() {
        	public void run() {
        		System.out.println("Entrou");
        		
        		//Gerando os processos
        		Processo p1 = g.getNovoProcesso();
        		Processo p2 = g.getNovoProcesso();
        		
        		// Pegando o tipo de alocacao (selecionando o algoritmo)
        		TipoAlocacao tipo = TipoAlocacao.getAlgoritmo(tipoId);
        		
            	tipo.getAlgoritmo().inserir(m, p1);
            	tipo.getAlgoritmo().inserir(m, p2);
            	
				m.remover();
        		double ocupMemoria = p1.getTamanho() + p2.getTamanho() * 100;
        		double mediaTamanhoProc = p1.getTamanho() + p2.getTamanho() / 2;
        		System.out.println("Tamanho m�dio dos processos: " + mediaTamanhoProc);
        		System.out.println("Porcentagem Ocupada: " +ocupMemoria / 1000+"%");
        		
        		System.out.println(m.printMemoria());
        		System.out.println(m.getTamanhoMemoria());
        		System.out.println(Arrays.toString(m.getMemoria()));
        	}
        };
        
        for (int i = 0; i < 100; i++) {
        	task.run();
        	Thread.sleep(1000);
		}
        System.out.println("Taxa de descarte: " + (descarte.size() * 100) / 1000 + "%");
        teclado.close();
    }
}
