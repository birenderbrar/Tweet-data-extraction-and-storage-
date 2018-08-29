package brar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import com.opencsv.CSVReader;

public class Birender {

    public static String[] newsentence;
    public static HashMap<String, Integer> occurance = new HashMap<>();
    public static String[][] top5 = new String[5][2];
    public static int counter = 0;
    public static String Name;
    public static ArrayList<String[]> one = new ArrayList<>();
    public static ArrayList<String[]> two = new ArrayList<>();
    public static ArrayList<String[]> three = new ArrayList<>();
    public static ArrayList<String[]> four = new ArrayList<>();
    public static ArrayList<String[]> five = new ArrayList<>();


    public static void main(String args[]) {

        try {
            CSVReader reader = new CSVReader(new FileReader("C:/Users/brar/eclipse-workspace/Assignment/docTweet.csv"));
            while ((newsentence = reader.readNext()) != null) {
                if (newsentence != null) {
                    Name = newsentence[11];
                    if (occurance.containsKey(Name)) {
                        occurance.put(Name, (occurance.get(Name) + 1));
                    } else {
                        occurance.put(Name, 1);
                    }
                }
            }
            while (counter < 5) {
                fivemostactive(occurance);
            }
            System.out.println("Top Five Most active User in Twitter data is as follows:\n");
            System.out.println("No" + "\t" + "ScreenName" + "\t" + "Count");
            for (int i = 0; i < counter; i++) {
                System.out.println((i + 1) + "\t" + top5[i][0] + "\t" + top5[i][1]);
                reader = new CSVReader(new FileReader("C:/Users/brar/eclipse-workspace/Assignment/docTweet.csv"));
                while ((newsentence = reader.readNext()) != null) {
                    if (newsentence != null && newsentence[11].equalsIgnoreCase(top5[i][0])) {
                        switch (i + 1) {
                            case 1:
                                one.add(newsentence);
                                break;
                            case 2:
                                two.add(newsentence);
                                break;
                            case 3:
                                three.add(newsentence);
                                break;
                            case 4:
                                four.add(newsentence);
                                break;
                            case 5:
                                five.add(newsentence);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }


            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Enter operation number you to perform : ");
            System.out.println("1 - Show data \n2 - Add Data \n3 - Delete Data\n4 - Exit");
            int op = Integer.parseInt(bufferRead.readLine());
            int line;

            while (op != 4) {
                switch (op) {
                    case 1:
                        System.out.println("select user : ");
                        System.out.println("1 - " + top5[0][0] + " \n2 - " + top5[1][0] + " \n3 - " + top5[2][0] + "\n4 - " + top5[3][0] + "\n5 - " + top5[4][0] + "");
                        op = Integer.parseInt(bufferRead.readLine());
                        if (op > 0 && op <= 5) {
                            show(op);
                        } else
                            System.out.println("Invalid Choice!!!");
                        break;
                    case 2:
                        System.out.println("select user : ");
                        System.out.println("1 - " + top5[0][0] + " \n2 - " + top5[1][0] + " \n3 - " + top5[2][0] + "\n4 - " + top5[3][0] + "\n5 - " + top5[4][0] + "");
                        op = Integer.parseInt(bufferRead.readLine());
                        if (op > 0 && op <= 5) {
                            System.out.println("Enter CSV tweet to add");
                            String add = bufferRead.readLine();
                            reader = new CSVReader(new StringReader(add));
                            newsentence = reader.readNext();
                            add(op, newsentence);
                            show(op);
                        } else
                            System.out.println("Invalid Choice!!!");
                        ;
                        break;
                    case 3:
                        System.out.println("select user : ");
                        System.out.println("1 - " + top5[0][0] + " \n2 - " + top5[1][0] + " \n3 - " + top5[2][0] + "\n4 - " + top5[3][0] + "\n5 - " + top5[4][0] + "");
                        op = Integer.parseInt(bufferRead.readLine());
                        if (op > 0 && op <= 5) {
                            show(op);
                            switch (op) {
                                case 1:
                                    System.out.println("Enter between 1 to " + one.size());
                                    line = Integer.parseInt(bufferRead.readLine());
                                    if (line > 0 && line <= one.size()) {
                                        delete(op, line);
                                    } else
                                        System.out.println("Invalid Choice!!!");
                                    break;
                                case 2:
                                    System.out.println("Enter between 1 to " + two.size());
                                    line = Integer.parseInt(bufferRead.readLine());
                                    if (line > 0 && line <= two.size() + 1) {
                                        delete(op, line);
                                    } else
                                        System.out.println("Invalid Choice!!!");
                                    break;
                                case 3:
                                    System.out.println("Enter between 1 to " + three.size());
                                    line = Integer.parseInt(bufferRead.readLine());
                                    if (line > 0 && line <= three.size()) {
                                        delete(op, line);
                                    } else
                                        System.out.println("Invalid Choice!!!");
                                    break;
                                case 4:
                                    System.out.println("Enter between 1 to " + four.size());
                                    line = Integer.parseInt(bufferRead.readLine());
                                    if (line > 0 && line <= four.size()) {
                                        delete(op, line);
                                    } else
                                        System.out.println("Invalid Choice!!!");
                                    break;
                                case 5:
                                    System.out.println("Enter between 1 to " + five.size());
                                    line = Integer.parseInt(bufferRead.readLine());
                                    if (line > 0 && line <= five.size()) {
                                        delete(op, line);
                                    } else
                                        System.out.println("Invalid Choice!!!");
                                    break;
                                default:
                                    break;
                            }

                        } else
                            System.out.println("Invalid Choice!!!");
                        break;

                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
                System.out.println("1 - Show data \n2 - Add Data \n3 - Delete Data\n4 - Exit");
                op = Integer.parseInt(bufferRead.readLine());
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

    public static void fivemostactive(HashMap<String, Integer> occurance) {
        int mostFrequent = 0;
        String frequentWord = "";
        Iterator<HashMap.Entry<String, Integer>> entry = occurance.entrySet().iterator();
        while (entry.hasNext()) {
            HashMap.Entry<String, Integer> wordMap = entry.next();
            String word = wordMap.getKey();
            int val = occurance.get(word);
            if (val > mostFrequent) {
                mostFrequent = val;
                frequentWord = word;
            }

        }
        occurance.remove(frequentWord);
        top5[counter][0] = frequentWord;
        top5[counter][1] = Integer.toString(mostFrequent);
        counter++; }
        
        public static void show(int i) {
        Object var;
        System.out.println("Serial \tTwitter Data");
        switch (i) {
            case 1:
                var = one.get(one.size() - 1);
                for (int j = 0; j <= one.indexOf(var); j++) {
                    String str = String.join(",", one.get(j));
                    System.out.println((j + 1) + "\t" + str);}
                break;
            case 2:
                var = two.get(two.size() - 1);
                for (int j = 0; j <= two.indexOf(var); j++) {
                    String str = String.join(",", two.get(j));
                    System.out.println((j + 1) + "\t" + str);}
                break;
            case 3:
                var = three.get(three.size() - 1);
                for (int j = 0; j <= three.indexOf(var); j++) {
                    String str = String.join(",", three.get(j));
                    System.out.println((j + 1) + "\t" + str);
                }
                break;
            case 4:
                var = four.get(four.size() - 1);
                for (int j = 0; j <= four.indexOf(var); j++) {
                    String str = String.join(",", four.get(j));
                    System.out.println((j + 1) + "\t" + str);
                }
                break;
            case 5:
                var = five.get(five.size() - 1);
                for (int j = 0; j <= five.indexOf(var); j++) {
                    String str = String.join(",", five.get(j));
                    System.out.println((j + 1) + "\t" + str);
                }
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    private static void add(int op, String[] abc) {
        switch (op) {
            case 1:
                one.add((one.size()), abc);
                show(op);
                break;
            case 2:
                two.add((two.size()), abc);
                break;
            case 3:
                three.add((three.size()), abc);
                break;
            case 4:
                four.add((four.size()), abc);
                break;
            case 5:
                five.add((five.size()), abc);
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }

    }

    private static void delete(int op, int line) {
        Object var;
        switch (op) {
            case 1:
                one.remove(line - 1);
                show(op);
                break;
            case 2:
                two.remove(line - 1);
                show(op);
                break;
            case 3:
                three.remove(line - 1);
                show(op);
                break;
            case 4:
                four.remove(line - 1);
                show(op);
                break;
            case 5:
                five.remove(line - 1);
                show(op);
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

}
