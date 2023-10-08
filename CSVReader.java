import java.io.*;
import java.util.ArrayList;

public class CSVReader {
    private String caminho;
    public ArrayList<String[]> dados;

    public CSVReader(String caminho) {
        this.caminho = caminho;
        this.dados = new ArrayList<String[]>();

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
                dados.add(linhaSeparada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}