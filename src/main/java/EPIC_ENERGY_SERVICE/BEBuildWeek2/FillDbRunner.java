package EPIC_ENERGY_SERVICE.BEBuildWeek2;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Comune;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Provincia;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ComuneService;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.services.ProvinciaService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class FillDbRunner implements CommandLineRunner {
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private ComuneService comuneService;

    @Override
    public void run(String... args) throws Exception {


        File fileComuni = new File("src/main/java/EPIC_ENERGY_SERVICE/BEBuildWeek2/files/comuni-italiani.csv");
        File fileProvince = new File("src/main/java/EPIC_ENERGY_SERVICE/BEBuildWeek2/files/province-italiane.csv");

        String stringComuni = FileUtils.readFileToString(fileComuni, StandardCharsets.UTF_8);
        String stringProvince = FileUtils.readFileToString(fileProvince, StandardCharsets.UTF_8);

        List<String> provinceList = new ArrayList<>(List.of(stringProvince.split(System.lineSeparator())));
        provinceList.remove(provinceList.get(0));

        List<String> comuniList = new ArrayList<>(List.of(stringComuni.split(System.lineSeparator())));
        comuniList.remove(comuniList.get(0));


        for (int i = 0; i < provinceList.size(); i++) {
            List<String> arr = List.of(provinceList.get(i).split(";"));
            Provincia p = Provincia.builder().sigla(arr.get(0)).nome(arr.get(1)).regione(arr.get(2)).build();
            provinciaService.save(p);
        }
        int cont = 1;
        for (int i = 0; i < comuniList.size(); i++) {
            List<String> arr = List.of(comuniList.get(i).split(";"));
            Provincia p;
            switch (arr.get(3)) {
                case "Verbano-Cusio-Ossola" -> {
                    p = provinciaService.getByNome("Verbania");
                }
                case "Valle d'Aosta/Vallée d'Aoste" -> {
                    p = provinciaService.getByNome("Aosta");
                }
                case "Monza e della Brianza" -> {
                    p = provinciaService.getByNome("Monza-Brianza");
                }
                case "Bolzano/Bozen" -> {
                    p = provinciaService.getByNome("Bolzano");
                }
                case "La Spezia" -> {
                    p = provinciaService.getByNome("La-Spezia");
                }
                case "Reggio nell'Emilia" -> {
                    p = provinciaService.getByNome("Reggio-Emilia");
                }
                case "Forlì-Cesena" -> {
                    p = provinciaService.getByNome("Forli-Cesena");
                }
                case "Pesaro e Urbino" -> {
                    p = provinciaService.getByNome("Pesaro-Urbino");
                }
                case "Ascoli Piceno" -> {
                    p = provinciaService.getByNome("Ascoli-Piceno");
                }
                case "Reggio Calabria" -> {
                    p = provinciaService.getByNome("Reggio-Calabria");
                }
                case "Vibo Valentia" -> {
                    p = provinciaService.getByNome("Vibo-Valentia");
                }
                case "Sud Sardegna" -> {
                    p = provinciaService.getByNome("Carbonia Iglesias");
                }
                default -> {
                    p = provinciaService.getByNome(arr.get(3));
                }
            }

            if (p != null) {
                if (arr.get(1).equals("#RIF!")) {
                    Comune c = Comune.builder().progressivoDelComune(cont).nome(arr.get(2)).provincia(p).build();
                    cont++;
                    comuneService.save(c);
                } else {
                    Comune c = Comune.builder().progressivoDelComune(Integer.parseInt(arr.get(1))).nome(arr.get(2)).provincia(p).build();
                    comuneService.save(c);
                }
            } else {
                System.out.println(arr.get(3));
            }

        }

    }
}
