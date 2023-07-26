/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2_project;

import java.sql.Connection;

/**
 *
 * @author Vic Lan
 */
public class Test {
    public static void main(String args[]){

    GrocerySingleton connection = null;
      try {
           connection = GrocerySingleton.getInstance();
       } catch (ClassNotFoundException ex) {
           System.out.println("Error: 222:"+ ex);
       }
    Connection conn = connection.getConnection();

    if (connection.getConnection() != null) {
         System.out.println("Database connection established successfully!");
}
    else{
        System.out.println("Database not connected!");
    }}}
