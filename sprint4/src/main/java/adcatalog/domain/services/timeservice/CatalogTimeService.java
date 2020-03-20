package adcatalog.domain.services.timeservice;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CatalogTimeService implements TimeService {

    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatPattern);
    }

}
