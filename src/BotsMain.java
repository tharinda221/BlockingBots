import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BotsMain extends Thread {
    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    static ScheduledFuture<?> t;

    public static HashMap<String, Node> IPs = new HashMap<String, Node>();
    public static HashMap<String, Node> SubIPs = new HashMap<String, Node>();
    public static ArrayList<String> Submask = new ArrayList<String>();
    public static String ip;
    public static String time;
    public static String subip;
    AutomatedTelnetClient telnet = new AutomatedTelnetClient();

    public BotsMain(String ip,String time,String subip){

        this.ip=ip;
        this.time=time;
        this.subip=subip;

    }
    public void RunThis(){


        //Identify a Subnet bots and add that subnet into Hashmap
        if(Node.subNetIn(Submask, subip) && !Node.searchNode(IPs,ip)  ){
            if(Node.searchNode(SubIPs, subip)){
                Node.UpdateNode(SubIPs, subip, 1);
            }
            else{
                SubIPs.put(subip, new Node(subip, 2, time));
            }
        }
        //Identify a bot and add it into Hashmap
        if(Node.searchNode(IPs, ip)){
            Node.UpdateNode(IPs, ip, 1);


        }
        else{
            IPs.put(ip, new Node(ip, 1, time));
            Submask.add(subip);

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

    public void run(){

        RunThis();
    }
}
