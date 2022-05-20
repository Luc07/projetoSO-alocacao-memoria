package projeto.alocacao;

public class Processo implements Comparable<Processo>{
	
	private static int MIN_TAMANHO = 10;
	private static int MAX_TAMANHO = 50;

	
	public Processo(int id) {
		this.id = id;
		this.tamanho = (int) ((Math.random() * (MAX_TAMANHO - MIN_TAMANHO)) + MIN_TAMANHO);//numero aleatorio entre 10 e 50
	}
	

	private int id;
	private int tamanho;
	private boolean ativo;
	private int posicao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public boolean getAtivo() {
		return ativo;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public int getPosicao() {
		return posicao;
	}
	
	@Override
	public int compareTo(Processo outro) {
		return Integer.compare(tamanho, outro.getTamanho());
	}
}
