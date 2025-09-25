import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventManager mgr = new EventManager();

        System.out.println("=== EventosCity ===");
        User currentUser = null;

        while (true) {
            System.out.println("\nUsuário: " + (currentUser == null ? "(nenhum)" : currentUser));
            System.out.println("1) Registrar usuário");
            System.out.println("2) Criar evento");
            System.out.println("3) Listar eventos");
            System.out.println("4) Participar de evento");
            System.out.println("5) Cancelar participação");
            System.out.println("6) Meus eventos");
            System.out.println("7) Sair");
            System.out.print("Opção: ");
            String opt = sc.nextLine();

            switch (opt) {
                case "1":
                    System.out.print("Nome: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Telefone: ");
                    String phone = sc.nextLine();
                    currentUser = new User(name, email, phone);
                    System.out.println("Usuário criado!");
                    break;

                case "2":
                    System.out.print("Nome do evento: ");
                    String en = sc.nextLine();
                    System.out.print("Endereço: ");
                    String addr = sc.nextLine();
                    System.out.println("Categorias: " + EventCategory.listAll());
                    System.out.print("Categoria: ");
                    EventCategory cat = EventCategory.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Data e hora (2025-09-24T20:30:00): ");
                    LocalDateTime dt = LocalDateTime.parse(sc.nextLine(), fmt);
                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();
                    mgr.addEvent(new Event(en, addr, cat, dt, desc));
                    System.out.println("Evento criado!");
                    break;

                case "3":
                    LocalDateTime now = LocalDateTime.now();
                    for (Event e : mgr.listEvents()) {
                        String status = e.isOngoing(now) ? "(OCORRENDO)" :
                                        e.hasOccurred(now) ? "(PASSADO)" : "(AGENDADO)";
                        System.out.println(e + " " + status);
                    }
                    break;

                case "4":
                    if (currentUser == null) { System.out.println("Cadastre-se primeiro."); break; }
                    System.out.print("ID do evento: ");
                    String idp = sc.nextLine();
                    System.out.println(mgr.participate(idp, currentUser.getEmail())
                                       ? "Participação confirmada." : "Falha ao participar.");
                    break;

                case "5":
                    if (currentUser == null) { System.out.println("Cadastre-se primeiro."); break; }
                    System.out.print("ID do evento: ");
                    String idc = sc.nextLine();
                    System.out.println(mgr.cancelParticipation(idc, currentUser.getEmail())
                                       ? "Cancelado." : "Falha ao cancelar.");
                    break;

                case "6":
                    if (currentUser == null) { System.out.println("Cadastre-se primeiro."); break; }
                    for (Event me : mgr.eventsForUser(currentUser.getEmail())) {
                        System.out.println(me.details());
                    }
                    break;

                case "7":
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default: 
                    System.out.println("Opção inválida.");
            }
        }
    }
}
