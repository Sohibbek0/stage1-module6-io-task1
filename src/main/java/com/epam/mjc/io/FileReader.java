package com.epam.mjc.io;
import java.io.*;

public class FileReader {
    public Profile getDataFromFile(File file) {
        int Age ;
        long Phone;
        String Name;
        String Email;
        boolean wr = false;
        int startRow =0;
        int endRow=0;
        FileInputStream fis = null;
        char[] charr=null;
        try {
            charr = new char[(int) file.length()];
            fis = new FileInputStream(file);
            int ch =fis.read();
            int i =0;
            while(ch!=-1){
                charr[i]=(char)ch;
                ch=fis.read();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("filenotfound");
        } catch (IOException e) {
            System.out.println("ioex");
        }
        String data = String.valueOf(charr);
        Profile prof = new Profile();
        String strAge = cut(data,"Age: ");
        Email = cut(data,"Email: ");
        String strPhone = cut(data,"Phone: ");
        Name = cut(data,"Name: ");
        Age = Integer.parseInt(strAge.trim());
        Phone=Long.parseLong(strPhone.trim());
        prof.setEmail(Email);
        prof.setName(Name);
        prof.setPhone(Phone);
        prof.setAge(Age);
        return prof;
    }
    public static String cut(String text, String find){
        String answer="";
        int start = text.indexOf(find);
        text=text.substring(start,text.length());
        start = text.indexOf(find);
        int end = text.indexOf("\n");
        if(start>-1)
            answer =text.substring(start+find.length() ,end);
        else
            System.out.println("string not found");
        return answer;
    }
}