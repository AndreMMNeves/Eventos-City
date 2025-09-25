import java.util.*;

public class EventManager {
    private List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public List<Event> listEvents() {
        events.sort(Comparator.comparing(Event::getDateTime));
        return Collections.unmodifiableList(events);
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public Event findById(String id) {
        for (Event e : events) {
            if (e.getId().startsWith(id)) return e;
        }
        return null;
    }

    public boolean participate(String eventId, String userEmail) {
        Event e = findById(eventId);
        if (e == null) return false;
        return e.addParticipant(userEmail);
    }

    public boolean cancelParticipation(String eventId, String userEmail) {
        Event e = findById(eventId);
        if (e == null) return false;
        return e.removeParticipant(userEmail);
    }

    public List<Event> eventsForUser(String userEmail) {
        List<Event> out = new ArrayList<>();
        for (Event e : events) {
            if (e.isParticipating(userEmail)) out.add(e);
        }
        out.sort(Comparator.comparing(Event::getDateTime));
        return out;
    }
}
