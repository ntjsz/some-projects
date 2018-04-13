import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class TimeDemo {
    public static void main(String[] args) {
        long hoursToAdd = 2;
        LocalDateTime t = LocalDateTime.now().plusHours(hoursToAdd + 1).truncatedTo(ChronoUnit.HOURS);
        t.toEpochSecond(ZoneOffset.UTC);
        //System.out.println(t);


        System.out.println(LocalDateTime.now().toEpochSecond(OffsetDateTime.now().getOffset()));
        System.out.println(System.currentTimeMillis() / 1000);
    }
}
