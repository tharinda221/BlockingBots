
public class BlockIP {
    private String ip;
    private String k;
    private String subip;
    private GetLocation obj = new GetLocation();

    public   BlockIP(String ip,String k,String subip){
        this.ip=ip;
        this.k=k;
        this.subip=subip;
        RunThis();
    }
    public void RunThis(){
        if(!ip.isEmpty()){
            if(k.equals("ns")) {
                System.out.println("iptables" + " " + "-A" + " " + "INPUT" + " " + "-s" + " " + ip + " " + "-j" + " " + "DROP");
                obj.getLocation(ip);
            }
            else {
                System.out.println("iptables"+" "+"-A"+" "+"INPUT"+" "+"-s"+" " + subip +" " + "-j"+" "+ "ACCEPT");

                obj.getLocation(ip);
            }
        }
        else{
            System.out.print("");
        }
    }

}

