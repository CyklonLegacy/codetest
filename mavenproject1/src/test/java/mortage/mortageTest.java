/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mortage;


import com.mycompany.mavenproject1.mortage;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Dick
 */
public class mortageTest{
   
   @Test	
   public void testPow() {
      assertEquals(16, (int) mortage.power(2, 4));  
      assertEquals(256, (int) mortage.power(4, 4));  
   }
   
   
   @Test	
   public void testFile() {
        assertEquals("src/main/resources/prospects.txt",
            mortage.getFile("src/main/resources/prospects.txt"));
   }
}
