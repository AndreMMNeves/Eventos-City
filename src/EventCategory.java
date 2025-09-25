public enum EventCategory {
    FESTA,
    ESPORTES,
    SHOW,
    REUNIOES,
    ENCONTRO,
    OUTROS;

    public static String listAll() {
        StringBuilder sb = new StringBuilder();
        for (EventCategory c : values()) {
            sb.append(c.name()).append(" ");
        }
        return sb.toString().trim();
    }
}
