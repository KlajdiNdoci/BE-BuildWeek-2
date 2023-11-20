package EPIC_ENERGY_SERVICE.BEBuildWeek2;

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
    private ProvinceService provinceService;
    @Autowired
    private ComuniService comuniService;

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
            Province p = Province.builder().sigla(arr.get(0)).nome(arr.get(1)).regione(arr.get(2)).build();
            provinceService.save(p);
        }

        for (int i = 0; i < comuniList.size(); i++) {
            List<String> arr = List.of(comuniList.get(i).split(";"));
            Province p = provinceService.getByNome(arr.get(3));
            Comune c = Comune.builder().progressivoDelComune(arr.get(1)).nome(arr.get(2)).provincia(p).buil();
            comuniService.save(c);
        }

    }
}
