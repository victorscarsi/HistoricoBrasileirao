import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Histórico do Brasileirão");

        CSVReader cartoes = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-cartoes.csv");
        CSVReader estatisticasFull = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-estatisticas-full.csv");
        CSVReader full = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-full.csv");
        CSVReader gols = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-gols.csv");

        maiorVencedor2008(full);
        estadoComMenosJogosEntre03e22();
        jogadorComMaisGols();
        jogadorComMaisGolsDePenalty();
        jogadorComMaisGolsContra();
        jogadorComMaisAmarelos();
        jogadorComMaisVermelhos();
        placarDaPartidaComMaisGols();
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
        System.out.println("-----XXX-----");
        System.out.println("Time(s) com maior número de vitórias no ano de 2008: ");
        for (String[] aux : vencedores) {
            if (Integer.parseInt(aux[1]) == maior) {
                System.out.println(aux[0] + " - " + aux[1] + " vitórias");
            }
        }
        System.out.println("-----XXX-----");
    }

    public static void estadoComMenosJogosEntre03e22() { }

    public static void jogadorComMaisGols() { }

    public static void jogadorComMaisGolsDePenalty() { }

    public static void jogadorComMaisGolsContra() { }

    public static void jogadorComMaisAmarelos() { }

    public static void jogadorComMaisVermelhos() { }

    public static void placarDaPartidaComMaisGols() { }
}
