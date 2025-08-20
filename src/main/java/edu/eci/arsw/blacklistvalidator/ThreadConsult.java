package edu.eci.arsw.blacklistvalidator;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.LinkedList;
import java.util.List;

public class ThreadConsult extends Thread{
    private String ipAddress;
    private HostBlacklistsDataSourceFacade dataSource;
    private int rangeA;
    private int rangeB;
    private static final int BLACK_LIST_ALARM_COUNT=5;
    private List<Integer> blackListOcurrences = new LinkedList<>();
    private int checkedListsCount=0;

    public ThreadConsult(String ipAddress, HostBlacklistsDataSourceFacade dataSource, int rangeA, int rangeB) {
        this.ipAddress = ipAddress;
        this.dataSource = dataSource;
        this.rangeA = rangeA;
        this.rangeB = rangeB;
           
    }

    public void run (){
            System.out.println("Thread is running");
            blackListOcurrences = checkHost(rangeA, rangeB, ipAddress, dataSource);
        }

    public List<Integer> checkHost(int rangeA, int rangeB, String ipAddress, HostBlacklistsDataSourceFacade skds){

        LinkedList<Integer> blackListOcurrences=new LinkedList<>();
        
        int ocurrencesCount=0;


        for (int i=rangeA; i<rangeB && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            this.checkedListsCount++;
            if (skds.isInBlackListServer(i, ipAddress)){
                
                blackListOcurrences.add(i);
                
                ocurrencesCount++;
            }
        }
        return blackListOcurrences;
    }

    public List<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }

    public int getCheckedListsCount() {
        return checkedListsCount;
    }

}
