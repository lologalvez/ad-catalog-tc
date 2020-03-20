package domain.domainservices.timeservice;

import java.time.LocalDate;

public interface TimeService {
    LocalDate getDate();
    String formatDate(LocalDate date);
}
