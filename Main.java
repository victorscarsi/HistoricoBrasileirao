public class Main {
    public static void main(String[] args) {
        System.out.println("Histórico do Brasileirão");

        CSVReader cartoes = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-cartoes.csv");
        CSVReader estatisticasFull = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-estatisticas-full.csv");
        CSVReader full = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-full.csv");
        CSVReader gols = new CSVReader(".\\ada_brasileirao_dataset-master\\campeonato-brasileiro-gols.csv");

        

    }
}
