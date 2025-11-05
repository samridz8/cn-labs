import java.util.Scanner;
import java.util.Random;

public class exp5 {

    public static void sendFrame(int[] frame, int windowSize) {
        Random random = new Random();
        int totalFrame = frame.length;
        int sent = 0;
        int acked = 0;

        while (acked < totalFrame) {
            // Send frames
            for (int i = 0; i < windowSize && sent < totalFrame; i++) {
                System.out.println("Sending frame: " + sent);
                sent++;
            }

            // Simulate acknowledgements
            for (int i = 0; i < windowSize && acked < totalFrame; i++) {
                // Randomly simulate lost frame (20% chance)
                boolean lost = random.nextInt(5) == 0;

                if (!lost) {
                    System.out.println("Acknowledged frame: " + acked);
                    acked++;
                } else {
                    System.out.println("Frame " + acked + " lost. Go back to " + acked);
                    sent = acked; // retransmit from lost frame
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of frames to send: ");
        int numFrame = scanner.nextInt();

        int[] frame = new int[numFrame];
        for (int i = 0; i < numFrame; i++) {
            frame[i] = i;
        }

        System.out.println("Enter window size: ");
        int windowSize = scanner.nextInt();

        System.out.println("Start sliding window protocol simulation...");
        sendFrame(frame, windowSize);

        scanner.close();
    }
}
