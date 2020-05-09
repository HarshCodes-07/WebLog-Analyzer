
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        System.out.println();
    }

    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIP());
    }

    public void testGreaterThanStatusCode(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");
        System.out.println(la.printAllHigherThanNum(10));
    }

    public void testuniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 24").size());
    }

    public void testprintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.printAllHigherThanNum(400));
    }

    public void testcountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPsInRange(400,499));
    }

    public void testcountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> h=la.countVisitsPerIP();
        for (Map.Entry<String,Integer> entry : h.entrySet())
            System.out.println(entry.getKey()+" "+entry.getValue());
    }

    public void testmostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.mostNumberVisitsByIP());
    }

    public void testiPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> h=la.iPsMostVisits();
        for(String i:h)
            System.out.println(i);
    }

    public void testiPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> h=la.iPsForDays();
        for (Map.Entry<String,ArrayList<String>> entry : h.entrySet())
            System.out.println(entry.getKey()+" "+entry.getValue());
    }

    public void testdayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> h=la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(h));
    }

    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> h=la.iPsForDays();
        ArrayList<String> a=la.iPsWithMostVisitsOnDay(h,"Sep 29");
        for(String i:a)
            System.out.println(i);
    }
}
