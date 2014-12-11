import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;


public class AutomatedTelnetClient {
    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String prompt = "#";
    public String Submask;

    public String AutomatedTelnet(String server, String user, String password, String ip) {


        try {
// Connect to the specified server
            telnet.connect(server, 23);

// Get input and output stream references
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

// Log the user on
            readUntil("RS_AS3303>");
            write("show ip bgp  " + ip);

            readUntil("login: ");
            write(user);
            telnet.disconnect();
//            readUntil("Password: ");
//            write(password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Submask;
    }

    public void su(String password) {
        try {
            write("su");
            readUntil("Password: ");
            write(password);
            prompt = "#";
            readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readUntil(String pattern) {
        try {
            int k = 0;
            String line = "";
            String line2 = line;
            char lastChar = pattern.charAt((pattern.length()) - 1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
                //System.out.print(ch);
                if (ch == 'B') {
                    k = 1;
                }
                if (k == 1) {
                    line = line + String.valueOf(ch);

                }
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
                if (Reg.stringChecker(".*, version.*", line)) {

                    //System.out.println("******"+regexChecker("[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\/([8-9]|1[0-9]|2[0-9]|3[0-2])",line)+"*****");
                    this.Submask = Reg.regexChecker("([0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\/([8-9]|1[0-9]|2[0-9]|3[0-2]))", line);
                    //System.out.println(Submask);
                    break;

                }
//                if(pattern.equals("login: ")){
//                    break;
//                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSubmask() {
        System.out.println(this.Submask);
        return Submask;
    }



    public void write(String value) {
        try {
            out.println(value);
            out.flush();
            //System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}