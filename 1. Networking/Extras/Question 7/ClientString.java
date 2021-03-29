import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientString {

    public static void main(String args[]) {

        int SENDER_PORT = 3333;
        int RECEIVER_PORT = 3334;
        Scanner sc = new Scanner(System.in);

        try {

            DatagramSocket ds = new DatagramSocket(SENDER_PORT);

            byte[] rd = new byte[1024];
            byte[] sd = new byte[1024];

            // Taking the String from Client
            System.out.print("Enter the string : ");
            String string = sc.nextLine();
            sd = string.getBytes();

            InetAddress address = InetAddress.getByName("localhost");

            // Creating the packet and send to server.
            DatagramPacket dp = new DatagramPacket(sd, sd.length, address, RECEIVER_PORT);
            ds.send(dp);

            // Receiving the total characters from the server.  
            DatagramPacket revDP = new DatagramPacket(rd, rd.length);
            ds.receive(revDP);
            String revString = new String(revDP.getData(), revDP.getOffset(), revDP.getLength()).trim();

            System.out.println("\nString : " + string);
            System.out.println("Reversed String : " + revString);

            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
} 