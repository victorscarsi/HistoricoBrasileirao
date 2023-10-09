import java.io.*;
import java.util.ArrayList;

public class CSVReader {
    private String caminho;
    public ArrayList<String[]> linhas;

    public CSVReader(String caminho) {
        this.caminho = caminho;
        this.linhas = new ArrayList<String[]>();

        File arquivo = new File(caminho);
        BufferedReader leitura = null;
        String linha = null;

        try {
            leitura = new BufferedReader(new FileReader(arquivo));
            while ((linha = leitura.readLine()) != null) {
                //dados.add(linha);
                String[] linhaSeparada = linha.split(",");
                for (int i = 0; i < linhaSeparada.length; i++) {
                    try {
                        linhaSeparada[i] = linhaSeparada[i].split("\"")[1]; // retira o caracter " que há nos dados.
                    } catch (Exception e) {
                        linhaSeparada[i] = null;
                    }
                }
                linhas.add(linhaSeparada);
            }
            leitura.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}