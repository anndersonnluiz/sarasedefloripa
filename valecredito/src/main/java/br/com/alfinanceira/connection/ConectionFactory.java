 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.alfinanceira.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;



public class ConectionFactory {

    private static EntityManager manager;
    private static EntityManager entityManager;
    private static EntityManagerFactory emf1;
    

    public static EntityManager getConnection() {
    	EntityManagerFactory emf = null;
        manager = null;
        emf = Persistence.createEntityManagerFactory("alfinanceiraPU");
        manager = emf.createEntityManager();
        if (!manager.isOpen()) {
        	JOptionPane.showMessageDialog(null, "Conex�o fechada");
        }
        return manager;
    }
    
    public static EntityManager getInstance() {
    	if ((emf1==null) || (entityManager==null) || (!entityManager.isOpen())){
            emf1 = Persistence.createEntityManagerFactory("alfinanceiraPU");
            entityManager = emf1.createEntityManager();
            if (!entityManager.isOpen()) {
            	System.out.println("ERRO Verifique conex�o com banco de dados");
            }
    	}  
    	if (entityManager.getTransaction()!=null){
    		if (entityManager.getTransaction().isActive()){
        		entityManager.getTransaction().commit();
        	}
    	}
        return entityManager;
    }
    
    public static void getInstanceClose() {
    	if ((emf1==null) || (entityManager==null) || (!entityManager.isOpen())){
            emf1 = Persistence.createEntityManagerFactory("alfinanceiraPU");
            entityManager = emf1.createEntityManager();
            if (!entityManager.isOpen()) {
            	System.out.println("ERRO Verifique conex�o com banco de dados");
            }
    	}  
    	if (entityManager.getTransaction()!=null){
    		if (entityManager.getTransaction().isActive()){
        		entityManager.getTransaction().commit();
        	}
    	}
        entityManager.close();
        emf1.close();
        emf1=null;
        entityManager = null;
    }
    
	public static Connection getConexao() {
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/alfinanceira", "root", "");
		} catch (SQLException e) {
		
		}

		return conexao;
	}
}