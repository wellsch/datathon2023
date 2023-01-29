import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/2020.csv"));
        ArrayList<Entry> entryList = new ArrayList<>();

        String[] msns = new String[]{"BDPRP", "CLPRB", "ENPRP", "GETCB", "HYTCB",
                "NCPRB", "NGMPB", "NUETB", "PAPRB", "REPRB", "SOTCB", "TEPRB", "TETCB", "WDEXB",
                "WDPRB", "WDTCB", "WSTCB", "WWPRB", "WYTCB"};

        String line;
        while ((line = reader.readLine()) != null) {
            Entry entry = Entry.readLine(line);
            if (entry != null) entryList.add(entry);
        }

        reader.close();

        List<String> states = entryList.parallelStream().filter(entry -> entry.getYear() == 2020)
                .map(Entry::getStateCode).distinct().filter(s -> !s.equals("DC") && !s.equals("US") && !s.equals("X3") && !s.equals("X5")).toList();

        List<Entry> clean = entryList.parallelStream().filter(entry -> !entry.getStateCode().equals("DC") && !entry.getStateCode().equals("US") && !entry.getStateCode().equals("X3") && !entry.getStateCode().equals("X5"))
                .filter(entry -> !entry.getMsn().equals("CLPRK"))
                .filter(entry -> !entry.getMsn().equals("COPRK"))
                .filter(entry -> !entry.getMsn().equals("NGMPK"))
                .filter(entry -> !entry.getMsn().equals("BDFDB"))
                .filter(entry -> !entry.getMsn().equals("BFPRP"))
                .filter(entry -> !entry.getMsn().equals("CLPRP"))
                .filter(entry -> !entry.getMsn().equals("PAPRP"))
                .filter(entry -> !entry.getMsn().equals("NGMPP"))
                .filter(entry -> !entry.getMsn().equals("BFFDB"))
                .filter(entry -> !entry.getMsn().equals("EMFDB"))
                .toList();

//        for (String msn : msns) {
//            BufferedReader reader1 = new BufferedReader(new FileReader("src/msns/" + msn + ".csv"));
//            ArrayList<Entry> entries = new ArrayList<>();
//            while ((line = reader1.readLine()) != null) {
//                Entry entry = Entry.readLine(line);
//                if (entry != null) entries.add(entry);
//            }
//            if (msn.equals("NGMPK")) System.out.println(entries);
//            double avg = entries.parallelStream().map(Entry::getAmount).reduce(0.0, Double::sum)/entries.size();
//        }

        ArrayList<State> stateArrayList = new ArrayList<>();

        for (String state : states) {
            for (int year = 2020; year < 2021; year++) {
                int finalYear = year;
                List<Entry> stateYear = clean.stream()
                        .filter(entry -> entry.getStateCode().equals(state))
                        .filter(entry -> entry.getYear() == finalYear).toList();
                ArrayList<Double> msnVals = new ArrayList<>();
                for (Entry entry : stateYear) {
                    msnVals.add(entry.getAmount());
                }
                stateArrayList.add(new State(state, finalYear, msnVals, stateYear.get(0).getAssistance(), stateYear.get(0).getNumInvestments(), stateYear.get(0).getEmissions()));
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/2020flipped.csv"));
        writer.write("State,Year");
        for (String msn : msns) {
            writer.write("," + msn);
        }
        writer.write(",emissions,numInvestments,assistance\n");
        for (State state : stateArrayList) {
            writer.write(state.toString());
        }
        writer.close();

//        for (Entry entry : clean) {
//            if (entry.getMsn().equals("BDPRP")) entry.setAmount(entry.getAmount() * 5.46);
//            if (entry.getMsn().equals("ENPRP")) entry.setAmount(entry.getAmount() * 3.192);
//        }
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter("src/clean.csv"));
//
//        for (Entry entry : clean) {
//            writer.write(entry.toString());
//        }
//
//        writer.close();


//        Map<String, Double> msnToMinimum = entryList.parallelStream().collect(Collectors
//                .toMap(Entry::getMsn, Entry::getAmount, Double::min));
//        Map<String, Double> msnToMaximum = entryList.parallelStream().collect(Collectors
//                .toMap(Entry::getMsn, Entry::getAmount, Double::max));
//
//        Map<String, Double> msnToRange = new HashMap<>();
//
//        for (String msn: msns) {
//            msnToRange.put(msn, msnToMaximum.get(msn) - msnToMinimum.get(msn));
//        }





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
