import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {
    public static String regexChecker(String theRegex,String str2Check){

        Pattern CheckRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = CheckRegex.matcher(str2Check);
        String op="";
        while(regexMatcher.find()){
            if(regexMatcher.group().length() !=0){
                //System.out.println(regexMatcher.group().trim());
                op=op+regexMatcher.group().trim();

            }
        }
        return op;
    }

    public static boolean stringChecker(String patternString,String line){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);
        boolean matches = matcher.matches();
        return matches;
    }


}
