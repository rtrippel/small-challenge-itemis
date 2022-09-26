import java.util.Objects;

public class RunApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to \"MERCHANT'S GUIDE TO THE GALAXY\"");
        System.out.println("Enter commands. To exit, type \"/q\"\n");
        final RequestRecipient requestRecipient = new RequestRecipient();
        final RequestHandler requestHandler = new RequestHandler();

        while (true) {
            final String operation = requestRecipient.getRequest();
            if (Objects.equals(operation,"/q")) System.exit(0);
            requestHandler.execute(operation);
        }
    }
}
