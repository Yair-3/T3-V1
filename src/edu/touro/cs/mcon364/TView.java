package edu.touro.cs.mcon364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;


class T3view extends JFrame {
    private JPanel title_panel = new JPanel();
    private JPanel button_panel = new JPanel();
    private JPanel bottom = new JPanel();
    private final JLabel bottom_text = new JLabel();
    private final JLabel top_bar_text = new JLabel();
    private final JButton[][] buttons = new JButton[3][3];
    private EventHandler eh = new EventHandler();
    private final JButton reset = new JButton("NEW GAME");

    T3 model = new T3();
    T3Controller controller = new T3Controller(model, this);


    class EventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isNewGame(e)) {
                if (!controller.recWinnerLocal().isEmpty()) {
                    fillButtons(getBackground());
                    controller.recWinnerLocal().clear();
                }
                controller.setRequest();
                top_bar_text.setText("Tic-Tac-Toe");
                bottom_text.setText("Player X will start the game");

            } else {
                ArrayList<Integer> position = getPosition(e);
                controller.setRequest(position);
                buttons[position.get(0)][position.get(1)].setText(controller.receiveInfo());
                bottom_text.setText(controller.receiveStatus() + "'S TURN");

                if (controller.receiveWinner()) {
                    top_bar_text.setText(controller.receiveInfo() + " WINS THE GAME!");
                    fillButtons(Color.green);
                }
                if (controller.receiveTie()) {
                    top_bar_text.setText("It's a tie :(");

                }

            }
        }
    }

    private void fillButtons(Color a) {
        buttons[controller.recWinnerLocal().get(0)][controller.recWinnerLocal().get(1)].setBackground(a);
        buttons[controller.recWinnerLocal().get(2)][controller.recWinnerLocal().get(3)].setBackground(a);
        buttons[controller.recWinnerLocal().get(4)][controller.recWinnerLocal().get(5)].setBackground(a);
    }



    T3view() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(750, 700);
//            this.getContentPane().setBackground(new Color(50, 50, 50));
            this.setLayout(new BorderLayout());

            top_bar_text.setBackground(new Color(25, 25, 25));
            top_bar_text.setForeground(new Color(25, 255, 0));
            top_bar_text.setFont(new Font("Ink Free", Font.BOLD, 50));
            top_bar_text.setHorizontalAlignment(JLabel.CENTER);
            top_bar_text.setText("Tic-Tac-Toe");
            top_bar_text.setOpaque(true);
            title_panel.setLayout(new BorderLayout());
            title_panel.setBounds(0, 0, 800, 100);

            bottom_text.setBackground(new Color(25, 25, 25));
            bottom_text.setForeground(new Color(25, 255, 0));
            bottom_text.setBorder(BorderFactory.createEmptyBorder());
            bottom_text.setFont(new Font("Ink Free", Font.BOLD, 25));
            bottom_text.setHorizontalAlignment(JLabel.CENTER);
            bottom_text.setText("Player X will start the game");
            bottom_text.setOpaque(true);
            bottom.add(bottom_text, BorderLayout.EAST);
            bottom.setBackground(new Color(25, 25, 25));


            button_panel.setLayout(new GridLayout(3, 3));
            button_panel.setBackground(new Color(150, 150, 150));


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j] = new JButton();
                    button_panel.add(buttons[i][j]);
                    buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
                    buttons[i][j].setFocusable(false);
                    buttons[i][j].addActionListener(eh);
                }
            }

            reset.setSize(20, 20);
            reset.addActionListener(eh);
            bottom.add(reset);
            title_panel.add(top_bar_text, BorderLayout.CENTER);
            this.add(title_panel, BorderLayout.NORTH);
            this.add(button_panel);
            this.add(bottom, BorderLayout.SOUTH);
            this.setVisible(true);

        }

        public boolean isNewGame(ActionEvent e) { //
            return e.getSource() == reset;
        }

        public ArrayList<Integer> getPosition(ActionEvent e) {
            ArrayList<Integer> position = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (e.getSource() == buttons[i][j]) {
                        position.add(i);
                        position.add(j);
                    }
                }
            }
            return position;
        }

        public void resetView() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText(null);
                    buttons[i][j].setEnabled(true);
                }
            }
        }


    }

