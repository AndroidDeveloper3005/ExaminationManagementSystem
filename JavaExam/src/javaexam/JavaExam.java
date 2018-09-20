/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexam;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 *
 * @author HIMEL
 */
public class JavaExam {

    /**
     * @param args the command line arguments
     */
    static QuesFrame fm;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		fm = new QuesFrame();
		fm.setSize(600,500);
		Rectangle windim = fm.getBounds();
		Dimension screanDim = Toolkit.getDefaultToolkit().getScreenSize();
		fm.setLocation((int)(screanDim.getWidth()-windim.width)/2,(int)(screanDim.getHeight()-windim.height)/2);
		fm.setIconImage(new ImageIcon("quiz.png").getImage());
		fm.setVisible(true);
		fm.setResizable(false);
		fm.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				
				final JDialog jd = new JDialog();
				
				jd.setModal(true);
				
				jd.setTitle("Simple Exam System");
				
				JLabel msg = new JLabel("Do you Want to Exit?",JLabel.CENTER);
				final JButton b1,b2;
				b1 = new JButton("Ok");
				b2 = new JButton("Cancel");
				
				b1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						System.exit(0);
						
					}
				});
				
				b2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						jd.hide();
						
					}
					
					
					
				});
				
				
				JPanel panel = new JPanel();
				panel.add(b1);
				panel.add(b2);
				Container con = jd.getContentPane();
				con.add(msg,BorderLayout.NORTH);
				con.add(panel,BorderLayout.SOUTH);
				jd.setSize(200,100);
				
				Dimension screanDim = Toolkit.getDefaultToolkit().getScreenSize();
				
				Rectangle windim = jd.getBounds();
				
				jd.setLocation((int)(screanDim.getWidth()-windim.width)/2,(int)(screanDim.getHeight()-windim.height)/2);
					
				jd.show();
				
				
				
			}
			
			
			
		});


	}

    
}
