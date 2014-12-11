import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Bots {

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        //data structures
        HashMap<String, Node> IPs = new HashMap<String, Node>();

        try {
            @SuppressWarnings("unchecked")
            FileReader fileRd = new FileReader(fileName);
            BufferedReader bufferRd = new BufferedReader(fileRd);
            int k = 0;
            String line;

            while(true){

                line = bufferRd.readLine();

                if (line == null) {
                    //Node.PrintNode(IPs);
                    //Node.PrintNode(SubIPs);
                    break;
                }
                else{
                    if (Reg.stringChecker(".*error: PAM: .*", line)) {

                        String ip=Reg.regexChecker("[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}",line);
                        String time=Reg.regexChecker("[0-9]{1,2}+\\:[0-9]{1,2}+\\:[0-9]{1,2}",line);
                        //gets Ip Address and its failed time by searching log file

                        //Identify a bot and add it into hashmap
                        if(Node.searchNode(IPs, ip)){
                            Node.UpdateNode(IPs, ip, 1);

                        }
                        else{
                            IPs.put(ip, new Node(ip, 1, time));

                        }

                    }
                }

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }


}
