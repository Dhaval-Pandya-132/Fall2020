package com.assignment1.Question3;
import java.io.*;
import java.util.*;

public class main {

    public static void main(String args[]) {
        String fileName = "data.txt";
        main m = new main();
        List<String> al;
        try {
            m.print("Creating new file");
            m.createNewFile(fileName);
            m.print("Reading data from file");
            al = Arrays.asList(m.readFile(fileName).trim().split(" "));
            m.print("Print each string and convert to upper case");
            al.forEach(st -> System.out.println((st.toUpperCase())));
            m.print("No of String --> " + al.size());
            int noOfLetters = al.stream().mapToInt(String::length).sum();
            m.print("No of Letters --> " + noOfLetters);

            m.print("Find frequency using hashset ");
            Set<String> st = new HashSet<>(al);
            System.out.println(st);
            st.forEach(s -> System.out.println("Frequency of " + s + " = " + Collections.frequency(al, s)));

            m.print("Find frequency using hashMap ");
            HashMap<String, Integer> hm = new HashMap<>();
            al.forEach(a -> {
                        if (hm.containsKey(a)) {
                            hm.put(a, hm.get(a) + 1);
                        } else {
                            hm.put(a, 1);
                        }
                    }
            );
            hm.forEach((k, v) -> System.out.println("Frequency of " + k + " = " + v));

            SortedMap<String, Integer> tm = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareToIgnoreCase(o2);
                }
            });
            tm.putAll(hm);
            m.print("Sort hashmap using Treemap = "+ tm);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewFile(String name) throws IOException {
        String content = "Welcome to class CSYE712 Parallelism, Concurrency, MultiThreading, Fall 2020";

        File file = new File(name);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter x = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(x);

        bw.write(content);
        bw.close();
    }

    public String readFile(String name) throws IOException {
        File file = new File(name);
        String output = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                print(line);
                output = output + " " + line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void print(String s) {
        System.out.println(s);
    }


}
