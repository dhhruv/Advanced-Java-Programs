import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerString {

    public static void main(String args[]) {
        int SENDER_PORT = 3333;
        int RECEIVER_PORT = 3334;
        try {
            DatagramSocket ds = new DatagramSocket(RECEIVER_PORT);

            byte[] rd = new byte[1024];
            byte[] sd = new byte[1024];

            DatagramPacket dp = new DatagramPacket(rd, rd.length);
            // Receive the packet from the sender.
            ds.receive(dp);

            //String string = new String(dp.getData()).trim();
            byte[] strAsByte = dp.getData();
            byte[] revString = new byte[strAsByte.length];

            InetAddress address = InetAddress.getByName("localhost");

            for (int i = 0; i < strAsByte.length; i++)
                revString[i] = strAsByte[strAsByte.length - i - 1];

            // Find the toatl characters in string and send to the client.
            DatagramPacket revDP = new DatagramPacket(revString, revString.length, address, SENDER_PORT);
            ds.send(revDP);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}