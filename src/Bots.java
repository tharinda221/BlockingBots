import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Bots {

    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    static ScheduledFuture<?> t;

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        //data structures
        HashMap<String, Node> IPs = new HashMap<String, Node>();
        HashMap<String, Node> SubIPs = new HashMap<String, Node>();
        ArrayList<String> Submask = new ArrayList<String>();

        try {
            @SuppressWarnings("unchecked")
            FileReader fileRd = new FileReader(fileName);
            BufferedReader bufferRd = new BufferedReader(fileRd);
            AutomatedTelnetClient telnet = new AutomatedTelnetClient();

            String line;

            while(true){

                line = bufferRd.readLine();

                if (line == null) {
                    //Node.PrintNode(IPs);
                    //Node.PrintNode(SubIPs);

                }
                else{
                    if (Reg.stringChecker(".*error: PAM: .*", line)) {

                        String ip=Reg.regexChecker("[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}",line);
                        String time=Reg.regexChecker("[0-9]{1,2}+\\:[0-9]{1,2}+\\:[0-9]{1,2}",line);
                        //gets Ip Address and its failed time by searching log file

                        //get Subnet Mask
                        String subip=telnet.AutomatedTelnet("route-server.ip-plus.net", "rviews", "rviews", ip);

                        //Identify a bot and add it into Hashmap
                        if(Node.searchNode(IPs, ip)){
                            Node.UpdateNode(IPs, ip, 1);

                        }
                        else{
                            IPs.put(ip, new Node(ip, 1, time));

                        }
                        //Identify a Subnet bots and add that subnet into Hashmap
                        if(Node.subNetIn(Submask, subip)  ){
                            if(Node.searchNode(SubIPs, subip)){
                                Node.UpdateNode(SubIPs, subip, 1);
                            }
                            else{
                                SubIPs.put(subip, new Node(subip, 2, time));
                            }
                        }

                        //Blocked if counts is equal to some specific value
                        if(Node.SelectIpAdd(IPs, ip,2)){

                            Node.writeIPS(ip,time,".\\Blocked.txt");
                            new BlockIP(ip,"ns",ip);
                            t = executor.schedule(new UNBlockIP(ip,"ns"), 10, SECONDS);
                            IPs.remove(ip);

                            Submask.remove(subip);

                        }

                        //Blocked if Subnet counts is equal to some specific value Block it and Unblock it earlier that a IP

                        if(Node.SelectIpAdd(SubIPs, subip,5)){

                            Node.writeIPS(subip,time,".\\BlockedSubs.txt");
                            new BlockIP(ip,"s",subip);
                            t = executor.schedule(new UNBlockIP(subip,"s"), 2, SECONDS);
                            SubIPs.remove(subip);
                        }


                    }
                }

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }


}
