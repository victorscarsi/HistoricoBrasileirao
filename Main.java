import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Histórico do Brasileirão");

        CSVReader cartoes = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-cartoes.csv");
        CSVReader estatisticasFull = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-estatisticas-full.csv");
        CSVReader full = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-full.csv");
        CSVReader gols = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-gols.csv");

        maiorVencedor2008(full);
        estadoComMenosJogosEntre03e22(full);
        jogadorComMaisGols(gols);
        jogadorComMaisGolsDePenalty(gols);
        jogadorComMaisGolsContra(gols);
        jogadorComMaisAmarelos(cartoes);
        jogadorComMaisVermelhos(cartoes);
        placarDaPartidaComMaisGols(full);
    }

    public static void maiorVencedor2008(CSVReader arquivo) {
        //Arquivo: full
        // "ID","rodata","data","hora","mandante","visitante","formacao_mandante","formacao_visitante","tecnico_mandante","tecnico_visitante","vencedor","arena","mandante_Placar","visitante_Placar","mandante_Estado","visitante_Estado"
        //  0  , 1      , 2    , 3    , 4        , 5         , 6                 , 7                  , 8                , 9                 , 10       , 11    , 12              , 13               , 14              , 15

        // Criando a lista de registros de vitórias para comparação.
        ArrayList<String> registros = new ArrayList<String>();
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                if ((arquivo.linhas.get(x)[2].split("/")[2].equals("2008")) && !(arquivo.linhas.get(x)[10].equals("-"))) {
                    registros.add(arquivo.linhas.get(x)[10]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criando a lista de times e contando quantas vezes o nome deles aparece na lista de registros de vitórias.
        List<String> times = registros.stream().distinct().toList();
        int cont, maior = 0;
        ArrayList<String[]> vencedores = new ArrayList<String[]>(); // [time][qtde vitorias]
        for (int x = 0; x < times.size(); x++) {
            cont = 0;
            for (int y = 0; y < registros.size(); y++) {
                if (registros.get(y).equals(times.get(x))) {
                    cont++;
                }
            }
            if (cont > maior) {
                maior = cont;
            }
            vencedores.add(new String[]{times.get(x), String.valueOf(cont)});
            //System.out.println(times.get(x) + " - " + cont);
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Time(s) com maior número de vitórias no ano de 2008: ");
        for (String[] aux : vencedores) {
            if (Integer.parseInt(aux[1]) == maior) {
                System.out.println(aux[0] + " - " + aux[1] + " vitórias");
            }
        }
    }

    public static void estadoComMenosJogosEntre03e22(CSVReader arquivo) {
        //Arquivo: full
        // "ID","rodata","data","hora","mandante","visitante","formacao_mandante","formacao_visitante","tecnico_mandante","tecnico_visitante","vencedor","arena","mandante_Placar","visitante_Placar","mandante_Estado","visitante_Estado"
        //  0  , 1      , 2    , 3    , 4        , 5         , 6                 , 7                  , 8                , 9                 , 10       , 11    , 12              , 13               , 14              , 15

        // Criando a lista de registros de estados para comparação.
        ArrayList<String> registros = new ArrayList<String>();
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                if ((Integer.parseInt(arquivo.linhas.get(x)[2].split("/")[2]) >= 2003) && (Integer.parseInt(arquivo.linhas.get(x)[2].split("/")[2]) <= 2022)) {
                    registros.add(arquivo.linhas.get(x)[14]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criando a lista de estados e contando quantas vezes o nome deles aparece na lista de registros de estados.
        List<String> estados = registros.stream().distinct().toList();
        int cont = 0;
        int menosJogos = Integer.MAX_VALUE;
        ArrayList<String[]> jogosEstados = new ArrayList<String[]>(); // [estado][qtde jogos]
        for (int x = 0; x < estados.size(); x++) {
            cont = 0;
            for (int y = 0; y < registros.size(); y++) {
                if (registros.get(y).equals(estados.get(x))) {
                    cont++;
                }
            }
            if (cont < menosJogos) {
                menosJogos = cont;
            }
            jogosEstados.add(new String[]{estados.get(x), String.valueOf(cont)});
            //System.out.println(estados.get(x) + " - " + cont);
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Estado(s) que receberam menos jogos entre 2003 e 2022: ");
        for (String[] aux : jogosEstados) {
            if (Integer.parseInt(aux[1]) == menosJogos) {
                System.out.println(aux[0] + " - " + aux[1] + " jogos");
            }
        }
    }

    public static void jogadorComMaisGols(CSVReader arquivo) {
        //Arquivo: gols
        // "partida_id","rodata","clube","atleta","minuto","tipo_de_gol"
        //  0          , 1      , 2     , 3      , 4      , 5

        // Criando a lista de registros de jogadores que fizeram gol para comparação.
        ArrayList<String> registros = new ArrayList<String>();
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                if ((arquivo.linhas.get(x)[5] == null) || (!arquivo.linhas.get(x)[5].equals("Gol Contra"))) {
                    registros.add(arquivo.linhas.get(x)[3]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criando a lista de jogadores e contando quantas vezes o nome deles aparece na lista de registros de jogadores que fizeram gol.
        List<String> jogadores = registros.stream().distinct().toList();
        int cont = 0;
        int maiorQtdeGols = 0;
        ArrayList<String[]> goleadores = new ArrayList<String[]>(); // [jogador][qtde gols]
        for (int x = 0; x < jogadores.size(); x++) {
            cont = 0;
            for (int y = 0; y < registros.size(); y++) {
                if (registros.get(y).equals(jogadores.get(x))) {
                    cont++;
                }
            }
            if (cont > maiorQtdeGols) {
                maiorQtdeGols = cont;
            }
            goleadores.add(new String[]{jogadores.get(x), String.valueOf(cont)});
            //System.out.println(jogadores.get(x) + " - " + cont);
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Jogador(es) que mais fez gols: ");
        for (String[] aux : goleadores) {
            if (Integer.parseInt(aux[1]) == maiorQtdeGols) {
                System.out.println(aux[0] + " - " + aux[1] + " gols");
            }
        }
    }

    public static void jogadorComMaisGolsDePenalty(CSVReader arquivo) {
        //Arquivo: gols
        // "partida_id","rodata","clube","atleta","minuto","tipo_de_gol"
        //  0          , 1      , 2     , 3      , 4      , 5

        // Criando a lista de registros de jogadores que fizeram gols de penalty para comparação.
        ArrayList<String> registros = new ArrayList<String>();
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                if ((arquivo.linhas.get(x)[5] != null) && (arquivo.linhas.get(x)[5].equals("Penalty"))) {
                    registros.add(arquivo.linhas.get(x)[3]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criando a lista de jogadores e contando quantas vezes o nome deles aparece na lista de registros de jogadores que fizeram gols de penalty.
        List<String> jogadores = registros.stream().distinct().toList();
        int cont = 0;
        int maiorQtdeGols = 0;
        ArrayList<String[]> goleadores = new ArrayList<String[]>(); // [jogador][qtde gols]
        for (int x = 0; x < jogadores.size(); x++) {
            cont = 0;
            for (int y = 0; y < registros.size(); y++) {
                if (registros.get(y).equals(jogadores.get(x))) {
                    cont++;
                }
            }
            if (cont > maiorQtdeGols) {
                maiorQtdeGols = cont;
            }
            goleadores.add(new String[]{jogadores.get(x), String.valueOf(cont)});
            //System.out.println(jogadores.get(x) + " - " + cont);
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Jogador(es) que mais fez gols de Penalty: ");
        for (String[] aux : goleadores) {
            if (Integer.parseInt(aux[1]) == maiorQtdeGols) {
                System.out.println(aux[0] + " - " + aux[1] + " gols");
            }
        }
    }

    public static void jogadorComMaisGolsContra(CSVReader arquivo) {
        //Arquivo: gols
        // "partida_id","rodata","clube","atleta","minuto","tipo_de_gol"
        //  0          , 1      , 2     , 3      , 4      , 5

        // Criando a lista de registros de jogadores que fizeram gols contra para comparação.
        ArrayList<String> registros = new ArrayList<String>();
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                if ((arquivo.linhas.get(x)[5] != null) && (arquivo.linhas.get(x)[5].equals("Gol Contra"))) {
                    registros.add(arquivo.linhas.get(x)[3]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criando a lista de jogadores e contando quantas vezes o nome deles aparece na lista de registros de jogadores que fizeram gols contra.
        List<String> jogadores = registros.stream().distinct().toList();
        int cont = 0;
        int maiorQtdeGols = 0;
        ArrayList<String[]> goleadores = new ArrayList<String[]>(); // [jogador][qtde gols]
        for (int x = 0; x < jogadores.size(); x++) {
            cont = 0;
            for (int y = 0; y < registros.size(); y++) {
                if (registros.get(y).equals(jogadores.get(x))) {
                    cont++;
                }
            }
            if (cont > maiorQtdeGols) {
                maiorQtdeGols = cont;
            }
            goleadores.add(new String[]{jogadores.get(x), String.valueOf(cont)});
            //System.out.println(jogadores.get(x) + " - " + cont);
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Jogador(es) que mais fez gols contra: ");
        for (String[] aux : goleadores) {
            if (Integer.parseInt(aux[1]) == maiorQtdeGols) {
                System.out.println(aux[0] + " - " + aux[1] + " gols");
            }
        }
    }

    public static void jogadorComMaisAmarelos(CSVReader arquivo) {
        // Arquivo: cartoes
        // "partida_id","rodata","clube","cartao","atleta","num_camisa","posicao","minuto"
        //  0          , 1      , 2     , 3      , 4      , 5          , 6       , 7

        // Criando a lista de registros de jogadores que mais receberam cartões amarelos para comparação.
        ArrayList<String> registros = new ArrayList<String>();
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                if ((arquivo.linhas.get(x)[3] != null) && (arquivo.linhas.get(x)[4] != null) &&
                        (arquivo.linhas.get(x)[3].equals("Amarelo"))) {
                    registros.add(arquivo.linhas.get(x)[4]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criando a lista de jogadores e contando quantas vezes o nome deles aparece na lista de registros de jogadores mais receberam cartões amarelos.
        List<String> jogadores = registros.stream().distinct().toList();
        int cont = 0;
        int maiorQtdeCartoes = 0;
        ArrayList<String[]> amarelados = new ArrayList<String[]>(); // [jogador][qtde cartoes]
        for (int x = 0; x < jogadores.size(); x++) {
            cont = 0;
            for (int y = 0; y < registros.size(); y++) {
                if (registros.get(y).equals(jogadores.get(x))) {
                    cont++;
                }
            }
            if (cont > maiorQtdeCartoes) {
                maiorQtdeCartoes = cont;
            }
            amarelados.add(new String[]{jogadores.get(x), String.valueOf(cont)});
            //System.out.println(jogadores.get(x) + " - " + cont);
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Jogador(es) que mais recebeu cartão amarelo: ");
        for (String[] aux : amarelados) {
            if (Integer.parseInt(aux[1]) == maiorQtdeCartoes) {
                System.out.println(aux[0] + " - " + aux[1] + " cartões amarelos");
            }
        }
    }

    public static void jogadorComMaisVermelhos(CSVReader arquivo) {
        // Arquivo: cartoes
        // "partida_id","rodata","clube","cartao","atleta","num_camisa","posicao","minuto"
        //  0          , 1      , 2     , 3      , 4      , 5          , 6       , 7

        // Criando a lista de registros de jogadores que mais receberam cartões vermelhos para comparação.
        ArrayList<String> registros = new ArrayList<String>();
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                if ((arquivo.linhas.get(x)[3] != null) && (arquivo.linhas.get(x)[4] != null) &&
                        (arquivo.linhas.get(x)[3].equals("Vermelho"))) {
                    registros.add(arquivo.linhas.get(x)[4]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criando a lista de jogadores e contando quantas vezes o nome deles aparece na lista de registros de jogadores que  mais receberam cartões vermelhos.
        List<String> jogadores = registros.stream().distinct().toList();
        int cont = 0;
        int maiorQtdeCartoes = 0;
        ArrayList<String[]> expulsos = new ArrayList<String[]>(); // [jogador][qtde cartoes]
        for (int x = 0; x < jogadores.size(); x++) {
            cont = 0;
            for (int y = 0; y < registros.size(); y++) {
                if (registros.get(y).equals(jogadores.get(x))) {
                    cont++;
                }
            }
            if (cont > maiorQtdeCartoes) {
                maiorQtdeCartoes = cont;
            }
            expulsos.add(new String[]{jogadores.get(x), String.valueOf(cont)});
            //System.out.println(jogadores.get(x) + " - " + cont);
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Jogador(es) que mais recebeu cartão vermelho: ");
        for (String[] aux : expulsos) {
            if (Integer.parseInt(aux[1]) == maiorQtdeCartoes) {
                System.out.println(aux[0] + " - " + aux[1] + " cartões vermelhos");
            }
        }
    }

    public static void placarDaPartidaComMaisGols(CSVReader arquivo) {
        //Arquivo: full
        // "ID","rodata","data","hora","mandante","visitante","formacao_mandante","formacao_visitante","tecnico_mandante","tecnico_visitante","vencedor","arena","mandante_Placar","visitante_Placar","mandante_Estado","visitante_Estado"
        //  0  , 1      , 2    , 3    , 4        , 5         , 6                 , 7                  , 8                , 9                 , 10       , 11    , 12              , 13               , 14              , 15

        int cont, maior = 0;
        int golsCasa, golsVisitante = 0;
        // Verifica a maior quantidade de gols
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            try {
                golsCasa = Integer.parseInt(arquivo.linhas.get(x)[12]);
                golsVisitante = Integer.parseInt(arquivo.linhas.get(x)[13]);
                int soma = golsCasa + golsVisitante;
                if (soma > maior) {
                    maior = soma;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Encontrando os registros de jogos com mais gols.
        ArrayList<String[]> melhoresJogos = new ArrayList<String[]>(); // [mandante][visitante][mandante-placar][visitante-placar]
        for (int x = 1; x < arquivo.linhas.size(); x++) {
            golsCasa = Integer.parseInt(arquivo.linhas.get(x)[12]);
            golsVisitante = Integer.parseInt(arquivo.linhas.get(x)[13]);
            if ((golsCasa + golsVisitante) == maior) {
                melhoresJogos.add(new String[]{
                   arquivo.linhas.get(x)[4],
                        arquivo.linhas.get(x)[5],
                        arquivo.linhas.get(x)[12],
                        arquivo.linhas.get(x)[13]
                });
            }
        }

        // Imprimindo o resultado
        System.out.println("------------------------------");
        System.out.println("Placar da(s) partida(s) com mais gols: ");
        for (String[] aux : melhoresJogos) {
            System.out.println(aux[0] + " " + aux[2] + " x " + aux[3] + " " + aux[1]);
        }
    }
}
