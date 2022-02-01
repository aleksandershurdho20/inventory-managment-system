/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommerce;

import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleksander
 */

public class Functions {
 
    public static void filterTable(JTable table,String query,JComboBox filter,boolean isFilteringByPrice,String Result){
    
        Connection con1;
        PreparedStatement pst;
        String selectedValue = filter.getSelectedItem().toString();
        
         int numerim = 0;
        
          
       try {
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/dyqani24","admin","admin");
            pst = con1.prepareStatement(query);
            
            if(isFilteringByPrice){
                System.out.println(Result);
                pst.setString(1, Result);

            
            }
            else{
                pst.setString(1, selectedValue);
  
            }
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            numerim= rsd.getColumnCount();
            DefaultTableModel d = (DefaultTableModel)table.getModel();
            d.setRowCount(0);
            while(rs.next()){

                  Vector arr = new Vector();
               for(int i=1;i<=numerim;i++){
                arr.add(rs.getString("emer_produkti"));
                arr.add(rs.getString("pershkrimi"));
                arr.add(rs.getString("kategori_id"));
                arr.add(rs.getString("cmimi"));
                arr.add(rs.getString("sasia"));
                arr.add(rs.getString("statusi"));

               
               }
               d.addRow(arr);

            }

        } catch (SQLException ex) {
            Logger.getLogger(category.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
