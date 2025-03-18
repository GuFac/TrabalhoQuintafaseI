import java.util.Random;
import java.util.Scanner;

class Dado {
    private int numeroAtual;
    private Random random;

    public Dado() {
        this.random = new Random();
    }

    public int rolar() {
        numeroAtual = random.nextInt(6) + 1;
        return numeroAtual;
    }
}

class Tabuleiro {
    private int numeroCasas;

    public Tabuleiro(int numeroCasas) {
        this.numeroCasas = numeroCasas;
    }

    public int getNumeroCasas() {
        return numeroCasas;
    }
}

class Jogador {
    private int casaAtual;
    private int meuNumero;

    public Jogador(int numero) {
        this.meuNumero = numero;
        this.casaAtual = 0;
    }

    public void jogar(Dado dado, Tabuleiro tabuleiro) {
        int movimento = dado.rolar();
        casaAtual += movimento;
        if (casaAtual > tabuleiro.getNumeroCasas()) {
            casaAtual = tabuleiro.getNumeroCasas();
        }
        System.out.println("Participante " + meuNumero + " tirou " + movimento + " e avançou para a posição " + casaAtual);
    }

    public int getCasaAtual() {
        return casaAtual;
    }

    public int getMeuNumero() {
        return meuNumero;
    }
}

class Jogo {
    private Tabuleiro meuTabuleiro;
    private Jogador[] meusJogadores;
    private Dado meuDado;

    public Jogo(int numCasas, int numJogadores) {
        this.meuTabuleiro = new Tabuleiro(numCasas);
        this.meuDado = new Dado();
        this.meusJogadores = new Jogador[numJogadores];
        for (int i = 0; i < numJogadores; i++) {
            meusJogadores[i] = new Jogador(i + 1);
        }
    }

    public void proximaJogada(int jogadorIndex) {
        if (jogadorIndex >= 0 && jogadorIndex < meusJogadores.length) {
            meusJogadores[jogadorIndex].jogar(meuDado, meuTabuleiro);
        } else {
            System.out.println("Participante inválido!");
        }
    }

    public void informarPosicoes() {
        for (Jogador jogador : meusJogadores) {
            System.out.println("Participante " + jogador.getMeuNumero() + " está na posição " + jogador.getCasaAtual());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = null;
        int opcao;

        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1 - Criar novo jogo");
            System.out.println("2 - Realizar jogada");
            System.out.println("3 - Ver posições");
            System.out.println("0 - Encerrar");
            System.out.print("Escolha uma ação: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Total de casas no percurso: ");
                    int numCasas = scanner.nextInt();
                    System.out.print("Quantidade de participantes: ");
                    int numJogadores = scanner.nextInt();
                    jogo = new Jogo(numCasas, numJogadores);
                    System.out.println("Jogo iniciado!");
                    break;
                case 2:
                    if (jogo != null) {
                        System.out.print("Número do participante (1 até N): ");
                        int jogador = scanner.nextInt() - 1;
                        jogo.proximaJogada(jogador);
                    } else {
                        System.out.println("Nenhuma partida ativa!");
                    }
                    break;
                case 3:
                    if (jogo != null) {
                        jogo.informarPosicoes();
                    } else {
                        System.out.println("Nenhuma partida ativa!");
                    }
                    break;
                case 0:
                    System.out.println("Finalizando o jogo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
