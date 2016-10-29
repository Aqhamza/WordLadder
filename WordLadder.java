/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonread;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import jsonread.wLadder;
/**
 *
 * @author Abdul Qadir
 */
public class WordLadder {
    private Set<String> myDic=new HashSet<>();
    private Set<wLadder> expDic=new HashSet<>();
    private List<wLadder> myList=new LinkedList<>();
    private String sWord;
    private String gWord;

    public WordLadder(String sWord, String gWord) {
        this.sWord = sWord;
        this.gWord = gWord;
    }
        public static void main(String[] args) throws IOException {
            Scanner scan = new Scanner( System.in );
           
            System.out.println("Enetr your Words in CAPITAL LETTERS.");
            System.out.print("Enter First Word: ");
            String fword=scan.next();
            System.out.print("\nEnter Second Word: ");
            String sword=scan.next();
            WordLadder Obj=new WordLadder(fword,sword);
            //Populating ....
            ObjectMapper mapper = new ObjectMapper();
	    Map<String, String> map = mapper.readValue(new File("test.json"),new TypeReference<Map<String, String>>(){});
            Obj.myDic = map.keySet();
            if(!Obj.myDic.contains(fword) || !Obj.myDic.contains(sword)){
                  System.out.println("There is No Matching Word in Dictionary.");
                  exit(0);
            }
          
            wLadder tempObj=new wLadder(Obj.sWord);
            Obj.myList.add(tempObj);
            //Obj.findPath();
            while(!Obj.checklength())
            {
            wLadder tNode=null;
            int Count;
            int Count1=-1;
            for(wLadder nextNode: Obj.myList)
            {
                Count=0;
                for(int i=0;i<Obj.gWord.length();i++)
                {
                    if(Obj.gWord.charAt(i)==nextNode.tStart.charAt(i))
                    {
                        Count++;
                    }
                }
                if(Count>Count1)
                {
                    
                    tNode=nextNode;
                    Count1=Count;
                }
                
            }
                wLadder obj=tNode;
                Obj.myList.remove(obj);
                Obj.addTomyList(obj);
                Obj.expDic.add(obj);
                
            }
            System.out.println("Explore List is: "+Obj.expDic);
            Obj.appCSV();
            
            
        }
        
        private boolean checklength()
        {
        for (wLadder nextNode : myList) {
            if(gWord.equals(nextNode.tStart)){
                System.out.println(nextNode);
                return true;
            }
            
        }
        return false;
           
        }
       
        private void addTomyList(wLadder node)
        {
        String tName=node.tStart;
        //System.out.println("tName: "+tName);
        for(int i=0;i<tName.length();i++)
        {
            String pattern=tName.substring(0,i)+"."+tName.substring(i+1,tName.length());
            Pattern myPattern;
            myPattern = Pattern.compile(pattern);
            //breaking all the words in Dictionary having Same Length
            myDic.stream().forEach((String myStr) -> {
                Matcher mWord=myPattern.matcher(myStr);
                
                if (tName.length()==myStr.length() && mWord.find() && !Visited(myStr) && myStr!=tName) {
                    // System.out.println("mWord.find(): "+myStr);
                    wLadder pNode=new wLadder(myStr,node);
                    myList.add(pNode);
                }
            });
        }
        }
        private boolean Visited(String tString)
        {
        return expDic.stream().anyMatch((myNode) -> (myNode.tStart==tString));
    }
    private void appCSV()
    { 
         PrintWriter pw = null;
         try {
               pw = new PrintWriter(new FileOutputStream("Diction.csv",true));
              } 
         catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
         StringBuilder builder = new StringBuilder();
         
         for (wLadder temp : expDic) {
               builder.append(temp);
               pw.append(builder.toString());
         }
         
         pw.close();
         System.out.println("done!");
    }
}

