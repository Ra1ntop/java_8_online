package ua.com.alevel;

public class Object {
    private int id;


    public Object(int id) {
        this.id = id;
    }

    public void send() {
        System.out.println("Object " + this.id + " sends a handshake.");
        if (this.id < 8) {
            Object receiver = new Object(this.id + 1);
            receiver.receive(this);
        } else {
            System.out.println("Object 8 received the handshake!");
        }
    }

    public void receive(Object sender) {
        System.out.println("Object " + this.id + " received a handshake from Object " + sender.id);

        if (Math.random() < 0.8) {  // 80% probability
            if (this.id < 8) {
                Object nextReceiver = new Object(this.id + 1);
                nextReceiver.receive(this);
            } else {
                System.out.println("Object 8 received the handshake!");
            }
        } else {
            System.out.println("Object " + this.id + " failed to send the handshake further.");
            send(); // Спроба відправити рукостискання ще раз
        }
    }
}
