import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CodPostales {
    static String ruta = "C:/codigospostales/";

    public static void main(String[] args) throws FileNotFoundException {
        codPost();
    }//end main

    public static void codPost() throws FileNotFoundException{
        String name="codigos_postales_hmo";
        Set<String> codigos = new HashSet<>();
        File f = new File(ruta+name);

        try(BufferedReader br = new BufferedReader(new FileReader(f));){
            String code;
            while((code = br.readLine())!=null){
                codigos.add(code);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        Map<String, Integer> occurrences = new HashMap<>();
        for (String code : codigos) {
            //System.out.println(code);
            String codPostal = extraerCodigo(code);
            //System.out.println(codPostal);
            if (codPostal != null) {
                occurrences.put(codPostal, occurrences.getOrDefault(codPostal, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            String codigo = entry.getKey();
            int count = entry.getValue();
            System.out.println("El codigo postal " + codigo + " se repite " + count + " veces.");
        }
    }

    private static String extraerCodigo (String code){
        String[] parts = code.split("\t");
        for (String part : parts) {
            if (part.matches("\\d{5}")) {
                return part;
            }
        }
        return null;
    }
}

