package cz.czechitas.webapp;

import java.util.*;
import java.util.concurrent.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    Long sekvence = 1000L;
    private List<Kontakt> kontakty = new CopyOnWriteArrayList<>(Arrays.asList(
            new Kontakt(++sekvence, "Thomas Alva Edison", "+1-123-555-666", "thomas@edison.com"),
            new Kontakt(++sekvence, "Albert Einstein", "+41 953 203 569", "albert.einstein@cern.ch"),
            new Kontakt(++sekvence, "Pavlina Zimmermannova", "+420-123-456-789", "pavlina@zimmermann.cz")

    ));

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/kontakty");
    }

    @RequestMapping(value = "/kontakty", method = RequestMethod.GET)
    public ModelAndView zobrazKontakty() {
         ModelAndView drzaknaData = new ModelAndView("kontakty");
         drzaknaData.addObject("kontakty", kontakty);
        return drzaknaData;
    }

    @RequestMapping(value = "/detail/{cislo}", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable("cislo") Long cislo) {
        ModelAndView drzakNaData = new ModelAndView("detail");
        drzakNaData.addObject("kontakty", ziskejKontaktPodleCisla(cislo));
        return drzakNaData;
    }

    @RequestMapping(value = "/detail/{cislo}", method = RequestMethod.POST)
    public ModelAndView zpracujDetail(@PathVariable("cislo") Long cislo, DetailForm detailForm) {
        upravKontakt(cislo, detailForm);
        return new ModelAndView("redirect:/kontakty");
    }

    @RequestMapping(value = "/kontakty/{cislo}", method = RequestMethod.POST,
            params = "method=DELETE")
    public ModelAndView zpracujSmazani(@PathVariable("cislo") Long cislo) {
        smazKontaktPodleCisla(cislo);
        return new ModelAndView("redirect:/kontakty");
    }

    @RequestMapping(value = "/novy", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        ModelAndView drzakNaData = new ModelAndView("detail");
        drzakNaData.addObject("kontakty", new DetailForm());
        return drzakNaData;
    }

    @RequestMapping(value = "/novy", method = RequestMethod.POST)
    public ModelAndView ulozNovy(DetailForm detailForm) {
         ulozKontakt(detailForm);
        return new ModelAndView("redirect:/kontakty");
    }

    private void ulozKontakt(DetailForm detailForm) {
        String jmeno = detailForm.getJmeno();
        String telefon = detailForm.getTelefon();
        String email = detailForm.getEmail();
        Kontakt novyKontakt = new Kontakt(++sekvence, jmeno, telefon, email);
        kontakty.add(novyKontakt);
    }



    private Kontakt ziskejKontaktPodleCisla(Long cislo) {
        int index = ziskejIndexKontaktu(cislo);
        return kontakty.get(index);
    }

    private int ziskejIndexKontaktu(Long cislo) {
        for (int i = 0; i < kontakty.size(); i++) {
            if (kontakty.get(i).getCislo().equals(cislo)) {
                return i;
            }
        }
        return -1;
    }

    private void upravKontakt(Long cislo, DetailForm detailForm) {
        for (Kontakt kontakt : kontakty) {
            if (kontakt.getCislo().equals(cislo)) {
                kontakt.setJmeno(detailForm.getJmeno());
                kontakt.setTelefon(detailForm.getTelefon());
                kontakt.setEmail(detailForm.getEmail());
            }
        }
    }

    private void smazKontaktPodleCisla(Long cislo) {
        int index = ziskejIndexKontaktu(cislo);
        kontakty.remove(index);

    }


}
