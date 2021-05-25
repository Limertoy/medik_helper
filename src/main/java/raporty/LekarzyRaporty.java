package raporty;

import com.mycompany.PDFGenerator.PDF;
import obiekty.Obserwacja;
import obiekty.Pdf;
import org.codehaus.jackson.map.ObjectMapper;
import sample.Main;
import services.ObserwacjaService;
import services.PdfService;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LekarzyRaporty extends PDF {
    private ObserwacjaService service;
    private PdfService pdfService;

    public void setService(ObserwacjaService service){ this.service = service; }

    public void setPdfService(PdfService service1){this.pdfService = service1;}

    @Override
    public String getPdfName() {
        return "Praca Lekarzy";
    }

    @Override
    public String getPdfFileName() {
        return "praca_lekarzy_";
    }

    @Override
    public void createPdf(String generatedBy) throws FileNotFoundException {
        this.setService(new ObserwacjaService());
        this.setPdfService(new PdfService());
        List<Obserwacja> obserwacja = service.findAll();
        List<Map<String, String>> mapObserwacja = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();

        for (int i = 0; i < obserwacja.size()-1; i++){
            for(int j = i+1; j < obserwacja.size(); j++) {
                if (obserwacja.get(j).getPracownik().equals(obserwacja.get(i).getPracownik())) {
                    obserwacja.get(i).setJeden(obserwacja.get(0).getJeden() + 1);
                    obserwacja.remove(j);
                    j--;
                }
            }
        }

        for (Obserwacja obs : obserwacja){
            mapObserwacja.add(obs.toMap());
        }

        this.createDocument(Main.PDF_DEST);
        this.addTitle(getPdfName());
        this.addTableAsMap(mapObserwacja);
        this.addGeneratedBy(generatedBy);
        this.closeDocument();
        Pdf pdf = new Pdf(getPdfName(),date);
        pdfService.saveOrUpdate(pdf);
    }
}
