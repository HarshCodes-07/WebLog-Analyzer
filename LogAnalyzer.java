
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @ HARSH DWIVEDI
 * @ 09-05-2020
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records=new ArrayList<>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr =new FileResource(filename);
        for(String lines:fr.lines()){
            records.add(WebLogParser.parseEntry(lines));
        }
    }

    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> h=new HashMap<>();
        for(LogEntry le:records){
            String ip=le.getIpAddress();
            if(!h.containsKey(ip))
                h.put(ip,1);
            else
                h.put(ip,h.get(ip)+1);
        }
        return h;
    }

    public int mostNumberVisitsByIP(){
        HashMap<String,Integer> h=countVisitsPerIP();
        int max=0;
        for (Map.Entry<String,Integer> entry : h.entrySet()){
            if(entry.getValue()>max)
                max=entry.getValue();
        }
        return max;
    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> h=new HashMap<>();
        for(LogEntry le:records){
            String ip=le.getIpAddress();
            String date=le.getAccessTime().toString();   
            String d=date.substring(4,7)+" "+date.substring(8,10);
            if(!h.containsKey(d)){
                ArrayList<String> a = new ArrayList<>();
                a.add(ip);
                h.put(d,a);
            }
            else
            {
                ArrayList<String> a =h.get(d);
                a.add(ip);
                h.put(d,a);
            }
        }
        return h;
    }

    public String dayWithMostIPVisits( HashMap<String, ArrayList<String>> h){
        int max=0;
        for (Map.Entry<String, ArrayList<String>> entry : h.entrySet()){
            if(entry.getValue().size()>max)
                max=entry.getValue().size();
        }
        for (Map.Entry<String, ArrayList<String>> entry : h.entrySet()){
            if(entry.getValue().size()==max)
                return entry.getKey();
        }
        return "***NEVER REACHES***";
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> h,String d){
        ArrayList<String> a=new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry : h.entrySet()){
            if(entry.getKey().equals(d))
                a=entry.getValue();
        }
        HashMap<String,Integer> h1=new HashMap<>();
        for(String le:a){
            String ip=le;
            if(!h1.containsKey(ip))
                h1.put(ip,1);
            else
                h1.put(ip,h1.get(ip)+1);
        }
        int max=0;
        for (Map.Entry<String, Integer> entry : h1.entrySet()){
            if(entry.getValue()>max)
                max=entry.getValue();
        }
        ArrayList<String> res=new ArrayList<>();
        for (Map.Entry<String, Integer> entry : h1.entrySet()){
            if(entry.getValue()==max)
                res.add(entry.getKey());
        }
        return res;
    }

    public ArrayList<String> iPsMostVisits(){
        HashMap<String,Integer> h=countVisitsPerIP();
        ArrayList<String> a=new ArrayList<>();
        int max=mostNumberVisitsByIP();
        for (Map.Entry<String,Integer> entry : h.entrySet()){
            String ipAddr=entry.getKey();
            if(entry.getValue()==max)
                a.add(ipAddr);
        }
        return a;
    }

    public int countUniqueIP(){
        HashMap<String,Integer> h=countVisitsPerIP();
        return h.size();
    }

    public int printAllHigherThanNum(int num){
        int c=0;
        for(LogEntry le:records){
            if(le.getStatusCode()>num)
            {
                c++;
                System.out.println(le.getStatusCode());
            }
        }
        return c;
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPS=new ArrayList<>();
        for(LogEntry le:records){
            String ipAddr=le.getIpAddress();
            String date=le.getAccessTime().toString();   
            String d=date.substring(4,7)+" "+date.substring(8,10);
            if(!uniqueIPS.contains(ipAddr)&&d.equals(someday))
                uniqueIPS.add(ipAddr);
        }
        return uniqueIPS;
    }

    public int countUniqueIPsInRange(int low,int high){
        ArrayList<String> uniqueIPS=new ArrayList<>();
        for(LogEntry le:records){
            String ipAddr=le.getIpAddress();
            int status=le.getStatusCode();
            if(!uniqueIPS.contains(ipAddr)&&status>=low&&status<=high)
                uniqueIPS.add(ipAddr);
        }
        return uniqueIPS.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}
