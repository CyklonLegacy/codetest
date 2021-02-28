/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dick
 */
public class mortage {
  
    //Reads the csv file from src/main/resources folder
    public static String getFile(String file) {
       
       String path = "src/main/resources/prospects.txt";
    
       return path; 
    }
    
    //takes base and raises it with powerRaised, runs, if powerRaised isnt 0
    //it will raise it again and tick down the powerRaised 
    public static double power(double base, double powerRaised) {
        if (powerRaised != 0)
            return (base * power(base, powerRaised - 1));
        else
            return 1;
    }
    
    public static void main(String[] args) throws IOException{
        
    String test = "";
    Pattern p = Pattern.compile(".");   
    
    String file = getFile(test);
    String line = "";
    double MonthlyPayment = 0.0;
    
    DecimalFormat df2 = new DecimalFormat("#.##"); 
    int iteration = 0;
    int i = 0; 
    double denominator = 0;
    double numerator = 0;

    //Using BufferedReader and StandardCharsets.UTF_8 to allow characters like é and å to display
    try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {

            try {
                while ((line = br.readLine()) != null) // returns a Boolean value
                {
                        //This if statement skips the first row of the csv as its not needed 
                        if(iteration == 0) {
                           iteration++;  
                           continue;
                        }
                        
                    // use comma and allows "ClarencÃ©,Andersson" to be injected into only one position in the array
                  //  String[] customers = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
                     String[] customers = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
                    
                    Matcher m = p.matcher(customers[0]);
                    boolean b = m.matches();

                    if (customers.length == 1 && customers[0].isEmpty()) {
                       
                        continue;
                    }
                    
                    if (b == true) {
                        
                        break;
                    }
              
     
                    try {
   
                        // this makes the years section in the the txt file into months instead
                        double NumOfMonths = Double.valueOf(customers[3]) * 12;
                        // This puts the loan into a loan variable
                        double loan = Double.valueOf(customers[1]);
                        // This function calculates intrests into %, like 5 to 0.05
                        double interest = (Double.valueOf(customers[2]) / 100);
   
                   
                     
                        denominator = power(1 + interest/12, NumOfMonths);
                        numerator = power(1 + interest/12, NumOfMonths) - 1;

                        MonthlyPayment = loan * (interest/12)  * denominator / numerator;     
                          
                        //This pluses after each line in the file has been read
                        i++;
                        
                        System.out.println(
                                "Prospect "+ i +": "+ customers[0] + " wants to borrow " + customers[1] + "€ for a period of "
                                        + customers[3] + " years and pay " + df2.format(MonthlyPayment) + "€ each month");
                  
                    } catch (NumberFormatException e) {
                        MonthlyPayment = 0.0;
                    }

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
    
    }

    
}
