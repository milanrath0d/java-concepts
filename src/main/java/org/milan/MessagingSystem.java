package org.milan;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Messaging system with below functionalities
 * 1. Multiple clients supported with each client has to register first
 * with 'register-client' command. Multiple invocations of this command
 * with same client won't have any effect.
 * 2. 'new-message' command will send message to client based on
 * message window size availability. If window size is 0 then message will
 * be queued into message queue. Per client max queued messages supported
 * are 100.
 * 3. 'adjust-window' command will adjust the message window for the
 * client and if any messages are available in message queued those messages
 * will be send to the clients
 *
 * @author Milan Rathod
 */
public class MessagingSystem {

    /**
     * Command used for registering client
     */
    public static final String REGISTER_CLIENT = "register-client";

    /**
     * Command used for sending new message
     */
    public static final String NEW_MSG = "new-message";

    /**
     * Command used for adjusting message window
     */
    public static final String ADJUST_WINDOW = "adjust-window";

    /**
     * Command used for existing window
     */
    public static final String EXIT = "exit";

    /**
     * Maximum messages per client supported in message queue
     */
    public static final int MAX_MSG_PER_CLIENT = 5;

    /**
     * This method is used to send a message to a client.
     *
     * @param clientId client identifier
     * @param msgId    message identifier
     * @param msgAge   message Age
     * @param more     true if more messages are to be sent to the client as part of
     *                 processing the current command.
     */
    public static void sendMessage(int clientId, int msgId, int msgAge, boolean more) {
        System.out.println("(" + clientId + ", " + msgId + ", " + msgAge + ")" + (more ? " " : "\n"));
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            Scanner s = scanner.useDelimiter("\\s+");
            boolean exit = false;
            while (!exit) {
                String cmd = s.next();
                if (!cmd.isEmpty()) {
                    switch (cmd) {
                        case REGISTER_CLIENT:
                            Server.registerClient(s.nextInt(), s.nextInt());
                            break;
                        case NEW_MSG:
                            Server.newMessage(s.nextInt(), s.nextInt(), s.nextInt());
                            break;
                        case ADJUST_WINDOW:
                            Server.adjustWindow(s.nextInt(), s.nextInt());
                            break;
                        case EXIT:
                            exit = true;
                            break;
                        default:
                            System.out.println("Unknown input " + cmd);
                            break;
                    }
                }
            }
        }
    }
}

/**
 * Messaging server responsible for registering clients, sending new messages to client and adjusting message window
 */
class Server {

    /**
     * Clients map with key as clientId and value as windowSize
     */
    private static final ConcurrentHashMap<Integer, Integer> clients = new ConcurrentHashMap<>();

    /**
     * messageQueues map with key as clientId and value as list of messageIds
     */
    private static final ConcurrentHashMap<Integer, List<Message>> messageQueues = new ConcurrentHashMap<>();

    /**
     * This method is called on "register-client" input.
     *
     * @param clientId   client identifier
     * @param windowSize message window size
     */
    public static void registerClient(int clientId, int windowSize) {
        if (!clients.containsKey(clientId)) {
            clients.put(clientId, windowSize);
        }
    }

    /**
     * This method is called on "new-message" input.
     * <p>
     * Use the provided MessagingSystem.sendMessage() method to send a message to the client.
     * The `more` argument to sendMessage should be false when called from this method.
     *
     * @param clientId client identifier
     * @param msgId    message identifier
     * @param msgAge   message age
     */
    public static void newMessage(int clientId, int msgId, int msgAge) {
        // Check if client is already registered
        if (!clients.containsKey(clientId)) {
            return;
        }

        // Check if message window is available otherwise queued the incoming message
        Integer messageWindow = clients.get(clientId);

        if (messageWindow == 0) {
            List<Message> messages = messageQueues.get(clientId);
            Message message = new Message(msgId, msgAge);

            if (messages != null) {

                if (messages.size() == MessagingSystem.MAX_MSG_PER_CLIENT) {
                    Message tempMessage = message;
                    for (Message message1 : messages) {
                        if (message1.getMsgId() < tempMessage.getMsgId()) {
                            tempMessage = message1;
                        }
                    }
                    messages.remove(tempMessage);
                }

                // Check if message with msgId already exists in messageQueues
                Optional<Message> any = messages.stream().filter(message1 -> message1.getMsgId() == message.getMsgId())
                        .findFirst();

                if (any.isPresent() && message.getMsgAge() > any.get().getMsgAge()) {
                    messages.remove(any.get());
                }
                messages.add(message);
            } else {
                List<Message> list = new LinkedList<>();
                list.add(message);
                messageQueues.put(clientId, list);
            }

        } else {
            MessagingSystem.sendMessage(clientId, msgId, msgAge, false);
            clients.put(clientId, messageWindow - 1);
        }
    }

    /**
     * This method is called on "adjust-window" input.
     * <p>
     * Use the provided MessagingSystem.sendMessage() method to send a message to the client.
     * If multiple messages have to be sent due to message window adjustment, the
     * `more` argument to sendMessage() should be false for the last message
     * (and true for all others).
     *
     * @param clientId   client identifier
     * @param windowSize message window size
     */
    public static void adjustWindow(int clientId, int windowSize) {
        // Check if client is already registered
        if (!clients.containsKey(clientId)) {
            return;
        }

        // Adjust the message window
        clients.put(clientId, windowSize);

        // Check if messages are queued
        List<Message> messages = messageQueues.get(clientId);

        if (messages != null) {
            // Send queued messages
            sendQueuedMessages(clientId, windowSize, messages);
        }
    }

    private static void sendQueuedMessages(int clientId, int windowSize, List<Message> messages) {
        while (!messages.isEmpty() && windowSize != 0) {
            Message message = messages.remove(0);
            if (messages.size() > 0 && windowSize > 1) {
                MessagingSystem.sendMessage(clientId, message.getMsgId(), message.getMsgAge(), true);
            } else {
                MessagingSystem.sendMessage(clientId, message.getMsgId(), message.getMsgAge(), false);
            }
            windowSize--;
        }

        // Update the message window
        clients.put(clientId, windowSize);
    }

    /**
     * Message pojo
     */
    private static class Message {
        private final int msgId;

        private final int msgAge;

        public Message(int msgId, int msgAge) {
            this.msgId = msgId;
            this.msgAge = msgAge;
        }

        public int getMsgId() {
            return msgId;
        }

        public int getMsgAge() {
            return msgAge;
        }
    }

}