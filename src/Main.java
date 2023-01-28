import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/datathon2023.txt"));
        ArrayList<Entry> entryList = new ArrayList<>();

        String[] msns = new String[]{"BDFDB", "BDPRP", "BFFDB", "BFPRP", "CLPRB", "CLPRK", "CLPRP",
                "COPRK", "EMFDB", "ENPRP", "GETCB", "HYTCB", "NCPRB", "NGMPB", "NGMPK", "NGMPP",
                "NUETB", "PAPRB", "PAPRP", "REPRB", "SOTCB", "TEPRB", "TETCB", "WDEXB", "WDPRB",
                "WDTCB", "WSTCB", "WWPRB", "WYTCB"};

        String line;
        while ((line = reader.readLine()) != null) {
            Entry entry = Entry.readLine(line);
            if (entry != null) entryList.add(entry);
        }

        reader.close();

        List<String> states = entryList.parallelStream().filter(entry -> entry.getYear() == 2015)
                .map(Entry::getStateCode).distinct().toList();

        Map<String, Double> msnToMinimum = entryList.parallelStream().collect(Collectors
                .toMap(Entry::getMsn, Entry::getAmount, Double::min));
        Map<String, Double> msnToMaximum = entryList.parallelStream().collect(Collectors
                .toMap(Entry::getMsn, Entry::getAmount, Double::max));

        Map<String, Double> msnToRange = new HashMap<>();

        for (String msn: msns) {
            msnToRange.put(msn, msnToMaximum.get(msn) - msnToMinimum.get(msn));
        }

        List<Entry> = entryList.parallelStream()
                .map(entry -> entry.setAmount(((entry.getAmount()) - msnToMinimum.get(entry.getMsn()))/msnToRange.get(entry.getMsn())))



//        for (String msn : msns) {
//            System.out.println(msn + ": " + entryList.parallelStream().filter(entry -> entry.getMsn()
//                    .equals(msn)).map(entry -> entry.getAmount()/(entry.getAssistance() * msnToTotal.get(msn)))
//                    .reduce(0.0, Double::sum) * 1000000);
//        }

//        for (int i = 2016; i < 2020; i++) {
//            int finalI = i;
//            for (String state : states) {
//                Map<String, Double> currYear = entryList.parallelStream().filter(entry -> entry.getStateCode().equals(state))
//                        .filter(entry -> entry.getYear() == finalI).collect(Collectors.toMap(Entry::getMsn, Entry::getAmount, Double::sum));
//                Map<String, Double> prevYear = entryList.parallelStream().filter(entry -> entry.getStateCode().equals(state))
//                        .filter(entry -> entry.getYear() == finalI - 1).collect(Collectors.toMap(Entry::getMsn, Entry::getAmount, Double::sum));
//                int curTotal = entryList.parallelStream().filter(entry -> entry.getStateCode().equals(state))
//                        .filter(entry -> entry.getYear() == finalI).filter(entry -> entry.getMsn().equals("BDFDB")).map(Entry::getAssistance).reduce(0, Integer::sum);
//                int prevTotal = entryList.parallelStream().filter(entry -> entry.getStateCode().equals(state))
//                        .filter(entry -> entry.getYear() == finalI - 1).filter(entry -> entry.getMsn().equals("BDFDB")).map(Entry::getAssistance).reduce(0, Integer::sum);
//                Map<String, Double> diffs = new HashMap<>();
//                double diff = 1.0 * (curTotal - prevTotal)/prevTotal;
//                for (String msn : msns) {
//                    diffs.put(msn, (currYear.get(msn) - prevYear.get(msn))/(prevYear.get(msn))/diff);
//                }
//                System.out.println(finalI + " " + state + " : " + diff);
//                System.out.println(diffs);
//            }
//        }


//        for (int i = 2016; i < 2019; i++) {
//            int finalI = i;
//            Map<String, Double> currYear = entryList.parallelStream()
//                    .filter(entry -> entry.getYear() == finalI).collect(Collectors.toMap(Entry::getMsn, Entry::getAmount, Double::sum));
//            Map<String, Double> prevYear = entryList.parallelStream()
//                    .filter(entry -> entry.getYear() == finalI - 1).collect(Collectors.toMap(Entry::getMsn, Entry::getAmount, Double::sum));
//            int prevTotal = entryList.parallelStream()
//                    .filter(entry -> entry.getYear() == finalI).filter(entry -> entry.getMsn().equals("BDFDB")).map(Entry::getAssistance).reduce(0, Integer::sum);
//            int curTotal = entryList.parallelStream()
//                    .filter(entry -> entry.getYear() == finalI + 1).filter(entry -> entry.getMsn().equals("BDFDB")).map(Entry::getAssistance).reduce(0, Integer::sum);
//            Map<String, Double> diffs = new HashMap<>();
//            double diff = 1.0 * (curTotal - prevTotal)/prevTotal;
//            for (String msn : msns) {
//                diffs.put(msn, (currYear.get(msn) - prevYear.get(msn))/(prevYear.get(msn))/diff);
//            }
//
//            System.out.println(diff);
//            System.out.println(diffs);
//        }
//
//
//        Map<String, Double> currYear = entryList.parallelStream()
//                .filter(entry -> entry.getYear() == 2019).collect(Collectors.toMap(Entry::getMsn, Entry::getAmount, Double::sum));
//        Map<String, Double> prevYear = entryList.parallelStream()
//                .filter(entry -> entry.getYear() == 2018).collect(Collectors.toMap(Entry::getMsn, Entry::getAmount, Double::sum));
//        int prevTotal = entryList.parallelStream()
//                .filter(entry -> entry.getYear() == 2019).filter(entry -> entry.getMsn().equals("BDFDB")).map(Entry::getAssistance).reduce(0, Integer::sum);
//        int curTotal = entryList.parallelStream()
//                .filter(entry -> entry.getYear() == 2018).filter(entry -> entry.getMsn().equals("BDFDB")).map(Entry::getAssistance).reduce(0, Integer::sum);
//        Map<String, Double> diffs = new HashMap<>();
//        double diff = 1.0 * (curTotal - prevTotal)/prevTotal;
//        for (String msn : msns) {
//            diffs.put(msn, (currYear.get(msn) - prevYear.get(msn))/(prevYear.get(msn))/diff);
//        }
    }
}
