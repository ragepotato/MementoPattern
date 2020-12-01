import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainDemo extends JFrame{
    public static void main(String[] args) {
        new MainDemo();
    }

    private JButton saveButton, undoButton, redoButton;

    private JTextArea theState = new JTextArea(20,25);
    private JLabel mementoTracker = new JLabel("Current state: ");

    // ---------------------------------------------

    CareTaker caretaker = new CareTaker();



    DocumentOriginator originator = new DocumentOriginator();



    int saveFiles = 0, currentIndex = 0;

    // ---------------------------------------------

    public MainDemo(){

        this.setSize(750,300);
        this.setTitle("Mementos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();


        panel1.add(theState);

        panel1.add(mementoTracker);

        theState.setLineWrap(true);

        ButtonListener saveListener = new ButtonListener();
        ButtonListener undoListener = new ButtonListener();
        ButtonListener redoListener = new ButtonListener();

        saveButton = new JButton("Save");
        saveButton.addActionListener(saveListener);

        undoButton = new JButton("Undo");
        undoButton.addActionListener(undoListener);

        redoButton = new JButton("Redo");
        redoButton.addActionListener(redoListener);

        panel1.add(saveButton);
        panel1.add(undoButton);
        panel1.add(redoButton);

        this.add(panel1);

        this.setVisible(true);

    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == saveButton){
                String textInTextArea = theState.getText();
                originator.setState(textInTextArea);

                caretaker.add( originator.saveStateToMemento() );


                String textBoxString = originator.getState();
                theState.setText(textBoxString);
                mementoTracker.setText("Current state: " + currentIndex);
                saveFiles++;
                currentIndex++;

                System.out.println("Save Files " + saveFiles);
                undoButton.setEnabled(true);

            } else

            if(e.getSource() == undoButton){
                if(currentIndex >= 1){
                    currentIndex--;
                    originator.getStateFromMemento(caretaker.get(currentIndex));
                    String textBoxString = originator.getState();
                    theState.setText(textBoxString);
                    mementoTracker.setText("Current state: " + currentIndex);
                    redoButton.setEnabled(true);
                } else {
                    undoButton.setEnabled(false);

                }

            } else

            if(e.getSource() == redoButton){

                if((saveFiles - 1) > currentIndex){
                    currentIndex++;
                    originator.getStateFromMemento(caretaker.get(currentIndex));
                    String textBoxString = originator.getState();
                    theState.setText(textBoxString);
                    mementoTracker.setText("Current state: " + currentIndex);
                    undoButton.setEnabled(true);
                } else {
                    redoButton.setEnabled(false);
                }

            }

        }

    }

}


