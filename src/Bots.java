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
        AutomatedTelnetClient telnet = new AutomatedTelnetClient();

        try {
            @SuppressWarnings("unchecked")
            FileReader fileRd = new FileReader(fileName);
            BufferedReader bufferRd = new BufferedReader(fileRd);


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

                        BotsMain BotsMain=new BotsMain(ip,time,subip);
                        BotsMain.start();



                    }
                }

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }


}
