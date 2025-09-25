import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Event {
    private String id;
    private String name;
    private EventCategory category;
    private LocalDateTime dateTime;
    private String description;
    private Set<String> participants;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Event(String name, String address, EventCategory category, LocalDateTime dateTime, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
        this.participants = new HashSet<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public EventCategory getCategory() { return category; }
    public LocalDateTime getDateTime() { return dateTime; }

    public boolean addParticipant(String email) {
        return participants.add(email.toLowerCase());
    }

    public boolean removeParticipant(String email) {
        return participants.remove(email.toLowerCase());
    }

    public boolean isParticipating(String email) {
        return participants.contains(email.toLowerCase());
    }

    public boolean isOngoing(LocalDateTime now) {
        return !now.isBefore(dateTime) && now.isBefore(dateTime.plusHours(1));
    }

    public boolean hasOccurred(LocalDateTime now) {
        return now.isAfter(dateTime.plusHours(1));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s — %s at %s",
                id.substring(0, 6), name, category.name(), dateTime.format(fmt));
    }

    public String details() {
        return toString() + "\nDescrição: " + description +
               "\nParticipantes: " + participants.size();
    }
}
