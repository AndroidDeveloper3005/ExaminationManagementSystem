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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HIMEL
 */
public class QuesFrame extends JFrame {

    JLabel queNo, timer;

    int maxQue = 1, quesNo = 1;
    TextArea qArea;
    JCheckBox answer[];

    JPanel top, center, bottom;

    JButton next, previous, result;
   // ButtonGroup buttonGroup;
    
  

    String quesOption[][];
    boolean ansOption[][];

    Question ques;

    public QuesFrame() {

        quesOption = new String[100][7];

        ansOption = new boolean[100][6];

        ques = new Question();

        quesOption = ques.getQuestions();

        queNo = new JLabel();

        timer = new JLabel("", JLabel.RIGHT);

        qArea = new TextArea();

        answer = new JCheckBox[4];


        top = new JPanel();

        center = new JPanel();

        bottom = new JPanel();

        center.setLayout(new GridLayout(5, 1));

        top.setLayout(new BorderLayout());

        top.add(queNo, BorderLayout.WEST);


        top.add(qArea, BorderLayout.SOUTH);
        
        //buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
        	

        	//buttonGroup.add(answer[i] = new JCheckBox());
        	answer[i] = new JCheckBox();
            answer[i].addItemListener(new ItemList());
            center.add(answer[i]);
            answer[i].setFont(new Font("Serieff", Font.PLAIN, 12));

        }

        bottom.setLayout(new GridLayout(1, 5));

        previous = new JButton("Previos");

        next = new JButton("Next");

        result = new JButton("Result");

        previous.setEnabled(false);

        bottom.add(previous);
        bottom.add(next);
        bottom.add(result);
        bottom.add(timer);

        getContentPane().add(top, BorderLayout.NORTH);

        getContentPane().add(center, BorderLayout.CENTER);

        getContentPane().add(bottom, BorderLayout.SOUTH);

        makeQues(1);

        next.addActionListener(new ActionList());

        previous.addActionListener(new ActionList());

        result.addActionListener(new ActionList());
        
        setTitle("Simple Exam System");

        Timer t = new Timer(this);
        t.start();

    }

    //display question
    void makeQues(int num) {

        if (num > 0 && num < 11) {

            queNo.setText("Question No " + num + "/10");

            qArea.setText(quesOption[num - 1][0]);

            qArea.setRows(10);

            if (maxQue < num) {

                maxQue = num;

                for (int i = 0; i < 4; i++) {

                    if (!quesOption[num - 1][i + 1].equals("")) {

                        answer[i].setVisible(true);
                        answer[i].setSelected(false);
                        answer[i].setText(quesOption[num - 1][i + 1]);

                    } else {
                        answer[i].setVisible(false);
                    }
                }

               

            } else {

                for (int i = 0; i < 4; i++) {

                    if (!quesOption[num - 1][i + 1].equals("")) {

                        answer[i].setVisible(true);
                        answer[i].setSelected(ansOption[num - 1][i]);
                        answer[i].setText(quesOption[num - 1][i + 1]);

                    } else {
                        answer[i].setVisible(false);
                    }
                }


            }

        }

    }

    class ActionList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == next) {

                if (quesNo < 10) {

                    quesNo += 1;

                    next.setEnabled(true);

                    previous.setEnabled(true);

                    if (quesNo >= 10) {

                    	JOptionPane.showMessageDialog(null, "You reach last question.");
                        next.setEnabled(false);

                    }

                }
                makeQues(quesNo);

            }

            if (e.getSource() == previous) {

                if (quesNo > 0) {

                    quesNo -= 1;

                    next.setEnabled(true);

                    previous.setEnabled(true);

                    if (quesNo <= 1) {

                        previous.setEnabled(false);

                    }

                }

                makeQues(quesNo);

            }

            if (e.getSource() == result) {

            	if (quesNo <=9) {
            		JOptionPane.showMessageDialog(null, "Please Answer All Question?.");
					
				}
            	else {
            		 new ExamResult(JavaExam.fm);
					
				}

            }
            

        }

    }

    class ItemList implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            // TODO Auto-generated method stub

            JCheckBox jc = (JCheckBox) e.getItem();

            for (int i = 0; i < 4; i++) {

                if (e.getStateChange() == 1) {
                    if (jc == answer[i]) {
                        ansOption[quesNo - 1][i] = true;

                    }

                } else {

                    if (jc == answer[i]) {
                        ansOption[quesNo - 1][i] = false;

                    }

                }

            }
           
        }

    }

}

class Question {

    RandomAccessFile file;

    String quesOption[][] = new String[100][7];

    public Question() {
        try {
            file = new RandomAccessFile("Question.txt", "r");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String[][] getQuestions() {

        StringBuffer s1;

        try {
            for (int i = 0; i < 10; i++) {
                StringBuffer sb1 = new StringBuffer();
                b1:
                while (true) {
                    String temp = new String(file.readLine());

                    if (temp.equals("##-----Question-----##")) {

                        int k = (int) Math.ceil(Math.random() * 2);
                        switch (k) {
                            case 2:
                                break b1;
                        }

                    }

                }

                while (true) {
                    s1 = new StringBuffer(file.readLine());

                    if (new String(s1).equals("##-----Question-----##")) {

                        break;

                    } else if (new String(s1).equals("##ans1##")) {

                        quesOption[i][1] = file.readLine();

                    } else if (new String(s1).equals("##ans2##")) {

                        quesOption[i][2] = file.readLine();

                    } else if (new String(s1).equals("##ans3##")) {

                        quesOption[i][3] = file.readLine();

                    } else if (new String(s1).equals("##ans4##")) {

                        quesOption[i][4] = file.readLine();

                    } else if (new String(s1).equals("##ans5##")) {

                        quesOption[i][5] = file.readLine();

                    } else if (new String(s1).equals("##correct##")) {

                        quesOption[i][6] = file.readLine();

                    } else {
                        sb1.append(s1);
                        sb1.append("\n");
                        quesOption[i][0] = new String(sb1);

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return quesOption;
    }

}
