
import java.util.*;



/**
 * THis class is used to analayzed time-run of several data structure
 * @author RoiGreenberg
 */
public class SimpleSetPerformanceAnalyzer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	int N = 5;
        HashSet<String> h01 = new HashSet<String>();
        TreeSet<String> t01 = new TreeSet<String>();
        LinkedList<String> l02 = new LinkedList<String>();
        HashSet<String> h02 = new HashSet<String>();
        TreeSet<String> t02 = new TreeSet<String>();
        LinkedList<String> l01 = new LinkedList<String>();
        ChainedHashSet c1 = new ChainedHashSet();
        OpenHashSet o1 = new OpenHashSet();
        CollectionFacadeSet h1 = new CollectionFacadeSet(h01);
        CollectionFacadeSet t1 = new CollectionFacadeSet(t01);
        CollectionFacadeSet l1 = new CollectionFacadeSet(l01);
        
        ChainedHashSet c2 = new ChainedHashSet();
        OpenHashSet o2 = new OpenHashSet();
        CollectionFacadeSet h2 = new CollectionFacadeSet(h02);
        CollectionFacadeSet t2 = new CollectionFacadeSet(t02);
        CollectionFacadeSet l2 = new CollectionFacadeSet(l02);
        SimpleSet[] testSets1;
        testSets1 = new SimpleSet[] {l1, h1, t1, c1, o1};
        
        SimpleSet[] testSets2;
        testSets2 = new SimpleSet[] {l2, h2, t2, c2, o2};
        String[] setsName;
        double[] add1 = new double[5];
        double[] add2 = new double[5];
        double[] containf1 = new double[5];
        double[] containt1 = new double[5];
        double[] containf2 = new double[5];
        double[] containt2 = new double[5];
        setsName = new String[] {"LinkedList", "treeSet", "hashSet", "chainedHashSet", "openHashSet"};
        String TEST1 = "hi";
        String TEST2 = "-13170890158";
        String TEST3 = "23";
        
        String[] text1 = Ex4Utils.file2array("E:\\Documents\\oop\\Ex4\\src\\data1.txt");
        String[] text2 = Ex4Utils.file2array("E:\\Documents\\oop\\Ex4\\src\\data2.txt");
        
        
        long timeBefore;
        long timeAfter;
        for (int i = 0; i < N; i++){
            timeBefore = new Date().getTime();
            for (String w: text1)
                testSets1[i].add(w);
            timeAfter = new Date().getTime();
            add1[i] = (double) (timeAfter-timeBefore);
        
            timeBefore = new Date().getTime();
            for (String w: text2)
            	testSets2[i].add(w);
            timeAfter = new Date().getTime();
            add2[i] = (double)(timeAfter-timeBefore);
            int M1 = 50000;
            int M2 = 50000;

        	timeBefore = new Date().getTime();
        	if (i==0)testSets1[i].contains(TEST1);
        	else
        		for (int j = 0; j < M1; j++)
        			testSets1[i].contains(TEST1);
            timeAfter = new Date().getTime();
            containf1[i] =(double) (timeAfter-timeBefore);

        
            timeBefore = new Date().getTime();
            if (i==0)testSets1[i].contains(TEST2);
        	else
        		for (int j = 0; j < M1; j++)
        			testSets1[i].contains(TEST2);
            timeAfter = new Date().getTime();
            containt1[i] = (double)(timeAfter-timeBefore);

        
            timeBefore = new Date().getTime();
            if (i==0)testSets1[i].contains(TEST3);
        	else
        		for (int j = 0; j < M2; j++)
        			testSets2[i].contains(TEST3);
            timeAfter = new Date().getTime();
            containt2[i] =(double) (timeAfter-timeBefore);
        
            timeBefore = new Date().getTime();
            if (i==0)testSets1[i].contains(TEST1);
        	else
        		for (int j = 0; j < M2; j++)
        			testSets2[i].contains(TEST1);
            timeAfter = new Date().getTime();
            containf2[i] = (double)(timeAfter-timeBefore);

        
        
        }

        for (int i = 0; i < N; i++){
            System.out.print("Name: "+setsName[i]+"  ");
            System.out.print("add1: "+ add1[i]+"  ");
            System.out.print("containf1 "+ containf1[i]+"  ");
            System.out.print("containt1 "+ containt1[i]+"  ");
            System.out.print("size: "+ testSets1[i].size()+"  ");
            System.out.print("add2: "+ add2[i]+"  ");
            System.out.print("containf2 "+ containf2[i]+"  ");
            System.out.print("containt2 "+ containt2[i]+"  ");
            System.out.print("size: "+ testSets2[i].size()+"  ");
            System.out.println("");
        }
    }
}
