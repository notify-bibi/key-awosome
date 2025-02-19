package de.uka.ilkd.key.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import de.uka.ilkd.key.gui.MainWindow;
import de.uka.ilkd.key.gui.notification.events.GeneralInformationEvent;
import de.uka.ilkd.key.proof.Proof;

public class ShowActiveTactletOptionsAction extends MainWindowAction {

    /**
     *
     */
    private static final long serialVersionUID = -7012564698194718532L;

    public ShowActiveTactletOptionsAction(MainWindow mainWindow) {
        super(mainWindow);
        setName("Show Active Taclet Options");

        getMediator().enableWhenProofLoaded(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showActivatedChoices();
    }

    private void showActivatedChoices() {
        Proof currentProof = getMediator().getSelectedProof();
        if (currentProof == null) {
            mainWindow.notify(new GeneralInformationEvent("No Options available.",
                "If you wish to see Taclet Options " + "for a proof you have to load one first"));
        } else {
            StringBuilder message = new StringBuilder("Active Taclet Options:\n");
            int rows = 0;
            int columns = 0;
            for (final String choice : currentProof.getSettings().getChoiceSettings()
                    .getDefaultChoices().values()) {
                message.append(choice).append("\n");
                rows++;
                if (columns < choice.length()) {
                    columns = choice.length();
                }
            }
            final JTextComponent activeOptions = new JTextArea(message.toString(), rows, columns);
            activeOptions.setEditable(false);
            Object[] toDisplay =
                { activeOptions, "These options can be changed in Options->Taclet Options" };
            JOptionPane.showMessageDialog(mainWindow, toDisplay,
                "Taclet options used in the current proof", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
