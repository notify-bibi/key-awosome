// This file is part of KeY - Integrated Deductive Software Design
//
// Copyright (C) 2001-2011 Universitaet Karlsruhe (TH), Germany
//                         Universitaet Koblenz-Landau, Germany
//                         Chalmers University of Technology, Sweden
// Copyright (C) 2011-2014 Karlsruhe Institute of Technology, Germany
//                         Technical University Darmstadt, Germany
//                         Chalmers University of Technology, Sweden
//
// The KeY system is protected by the GNU General
// Public License. See LICENSE.TXT for details.
//

package de.uka.ilkd.key.gui.actions;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import de.uka.ilkd.key.gui.IconFactory;
import de.uka.ilkd.key.gui.fonticons.IconFontSwing;
import de.uka.ilkd.key.gui.fonticons.FontAwesomeBold;
import de.uka.ilkd.key.gui.MainWindow;
import de.uka.ilkd.key.gui.nodeviews.SequentView;

/*
 * Menu option for showing the next search result of sequent search
 * Keyboard shortcut: F3. This shortcut is set in the KeyStrokemanager
 */
public class SearchNextAction extends MainWindowAction {

    private static final long serialVersionUID = -9002009635814787502L;

    public SearchNextAction(MainWindow mainWindow) {
        super(mainWindow);
        setName("Find next occurence");
        setIcon(
            IconFontSwing.buildIcon(FontAwesomeBold.ARROW_LEFT, 16)
        );
        setTooltip("Find the next occurence of current search term in sequent.");
        getMediator().enableWhenProofLoaded(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.sequentViewSearchBar.searchNext();
    }
}