package projeto.alocacao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Memoria {

	public static final Integer TAMANHO = 1000;

	private Processo[] memoria = new Processo[TAMANHO];
	
	public ArrayList<Processo> descarte = new ArrayList<>();
	
	// Flag criada para controlar se o processo foi inserido, caso continue falso é descartado
	private boolean flag;

	public Processo[] getMemoria() {
		return memoria;
	}

	public int getTamanhoMemoria() {
		return memoria.length;
	}
	
	public void adicionarFirstFit(Processo processo) {
		// Flag iniciada como false (não achou espaço para ser inserido)
		flag = false;
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == null) {
				int nullCount = 0;
				// Contagem de espaços null (vazios)
				for (int j = 0; j < memoria.length - i; j++) {
					nullCount++;
				}
				// Se a quantidade de espaços null (vazios) for maior ou igual ao tamanho do processo
				if (nullCount >= processo.getTamanho()) {
					for (int j = 0; j < processo.getTamanho(); j++) {
						// Processo setado como true pois achou local para ser inserido
						processo.setAtivo(true);
						// Inserir apartir do espaço valido achado
						memoria[j + i] = processo;
					}
					// Achou local para inserir flag passou a ser true
					flag = true;
				}
				// Posição setada no primeiro lugar que achou espaço e foi inserido
				processo.setPosicao(i);
				break;
			  // Caso não tenha espaço suficiente de vazios para inserir, verifica se tem algum processo
			  // Desativado (ativo = false)
			} else if (memoria[i].getAtivo() == false) {
				// Caso o tamanho do processo desativado seja igual o do processo
				if (memoria[i].getTamanho() == processo.getTamanho()) {
					// pega a posição do desativado
					int pos = memoria[i].getPosicao();
					// inserir no local do desativado
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[j + i] = processo;
					}
					flag = true;
					// setou a posição do antigo processo (desativado)
					processo.setPosicao(pos);
					break;
					// verifica se o tamanho do processo é menor que o desativado
				} else if (processo.getTamanho() < memoria[i].getTamanho()) {
					int pos = memoria[i].getPosicao();
					// subtrai o tamanho do desativado e coloca apenas o processo no tamanho q foi subtraido
					memoria[i].setTamanho(memoria[i].getTamanho() - processo.getTamanho());
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[j + i] = processo;
					}
					flag = true;
					processo.setPosicao(pos);
					break;
				}
			}
		}
		// Caso não entrou e não conseguiu alocar o processo em um espaço ele é descartado
		if (flag == false) {
			descarte.add(processo);
		}
	}
	
	public void adicionarNextFit(Processo processo) {
		flag = false;
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == null) {
				int nullCount = 0;
				for (int j = 0; j < memoria.length - i; j++) {
					if (memoria[j] == null) {
						nullCount++;
					}
				}
				if (nullCount >= processo.getTamanho()) {
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[j + i] = processo;
					}
					flag = true;
				}
				processo.setPosicao(i);
				break;
				// Verifica se ja tem um processo alocado e ativo
			} else if (memoria[i].getAtivo() == true) {
				// Pegando o tamanho pois ai consigo saber onde começar alocar o proximo processo
				int tam = memoria[i].getTamanho() + memoria[i].getPosicao();
				// Verifico se esta vazio
				if (memoria[tam] == null) {
					int nullCount = 0;
					for (int j = tam; j < memoria.length - i; j++) {
						if (memoria[tam] == null) {
							nullCount++;
						}

					}
					if (nullCount >= processo.getTamanho()) {
						for (int j = 0; j < processo.getTamanho(); j++) {
							processo.setAtivo(true);
							memoria[j + tam] = processo;
						}
						flag = true;
						processo.setPosicao(tam);
						break;
					}
					// Se caso tiver um processo apos o de antes esta ativo
				} else if (memoria[tam].getTamanho() >= processo.getTamanho()) {
					// Verifico se esta desativado
					if (memoria[tam].getAtivo() == false) {
						for (int j = 0; j < processo.getTamanho(); j++) {
							processo.setAtivo(true);
							memoria[j + tam] = processo;
						}
						flag = true;
						processo.setPosicao(tam);
						break;
					}
				}
			} else if (memoria[i].getAtivo() == false) {
				if (memoria[i].getTamanho() == processo.getTamanho()) {
					int pos = memoria[i].getPosicao();
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[pos + j] = processo;
					}
					flag = true;
					processo.setPosicao(pos);
					break;

				} else if (processo.getTamanho() < memoria[i].getTamanho()) {
					int pos = memoria[i].getPosicao();
					memoria[i].setTamanho(memoria[i].getTamanho() - processo.getTamanho());
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[pos + j] = processo;
					}
					flag = true;
					processo.setPosicao(pos);
					break;
				}
			}
		}
		if (flag == false) {
			descarte.add(processo);
		}
	}
	
	public void adicionarBestFit(Processo processo) {
		flag = false;
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == null) {
				int nullCount = 0;
				for (int j = 0; j < memoria.length - i; j++) {
					nullCount++;
				}
				if (nullCount == processo.getTamanho()) {
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[j + i] = processo;
					}
					flag = true;
				}
				processo.setPosicao(i);
				break;
			} else if (memoria[i].getAtivo() == false) {
				if (memoria[i].getTamanho() == processo.getTamanho()) {
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[j + i] = processo;
					}
					flag = true;
					processo.setPosicao(i);
					break;
				}
			}
		}
		if (flag == false) {
			descarte.add(processo);
		}
	}
	
	public void adicionarWorstFit(Processo processo) {
		flag = false;
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == null) {
				int nullCount = 0;
				for (int j = 0; j < memoria.length - i; j++) {
					nullCount++;
				}
				if (nullCount > processo.getTamanho()) {
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[j + i] = processo;
					}
					flag = true;
				}
				processo.setPosicao(i);
				break;
			} else if (memoria[i].getAtivo() == false) {
				if (memoria[i].getTamanho() > processo.getTamanho()) {
					memoria[i].setTamanho(memoria[i].getTamanho() - processo.getTamanho());
					for (int j = 0; j < processo.getTamanho(); j++) {
						processo.setAtivo(true);
						memoria[j + i] = processo;
					}
					flag = true;
					processo.setPosicao(i);
					break;
				}
			}
		}
		if (flag == false) {
			descarte.add(processo);
		}
	}

	public void remover() {
		// um set dos ids
		Set<Integer> ids = new HashSet<>();
		Random r = new Random();
		// int number = r.nextInt(m.getTamanhoMemoria());
		// adiciono todos os ids, por ser um set ele nao se repete
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] != null) {
				if (memoria[i].getAtivo() == true) {
					ids.add(memoria[i].getId());
				}
			}
		}
		// Verifico se não está vazio
		if(!ids.isEmpty()) {
			// Gero um numero random de acordo com o tamanho
			int number = r.nextInt(ids.size());
			// Convertendo para arraylist
			ArrayList<Integer> idsList = new ArrayList<Integer>(ids);
			// Para então pegar o id daquela posição gerada no random
			int idProcessoRemover = idsList.get(number);
			
			// Para aquele numero pego de id irei setando falso (desativado) na memoria
			for (int i = 0; i < memoria.length; i++) {
				if (memoria[i] != null) {
					if (memoria[i].getId() == idProcessoRemover) {
						memoria[i].setAtivo(false);
					}
				}
			}
			// Removo o id do set, assim ele não aparece novamente
			ids.remove(number);
		}
	}
	
	public String printMemoria() {
		StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < TAMANHO; i++) {
            if (i % 30 == 0) {
                buffer.append('\n');
            }
            if(memoria[i] != null) {
            	buffer.append(memoria[i].getAtivo()).append(", ");
            }else if(memoria[i] == null) {
            	buffer.append(" vazio");
            }
        }
        return buffer.toString();
	}
}
