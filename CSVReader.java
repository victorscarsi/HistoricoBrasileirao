import java.io.*;
import java.util.ArrayList;

public class CSVReader {
    private String caminho;
    public ArrayList<String> dados;

    public CSVReader(String caminho) {
        this.caminho = caminho;
        this.dados = new ArrayList<String>();

        File arquivo = new File(caminho);
        BufferedReader leitura = null;
        String linha = null;

        try {
           leitura = new BufferedReader(new FileReader(arquivo));
            while ((linha = leitura.readLine()) != null) {
                dados.add(linha);
            }
            separarColunas();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado! Tente novamente.");
            System.out.println("Caminho do arquivo: " + arquivo.getPath());
            //e.printStackTrace();
        }
    }

    private void separarColunas() {

    }
}