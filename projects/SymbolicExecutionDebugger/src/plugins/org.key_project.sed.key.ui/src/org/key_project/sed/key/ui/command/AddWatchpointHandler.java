package org.key_project.sed.key.ui.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.debug.ui.JDIDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.key_project.key4eclipse.starter.core.util.LogUtil;
import org.key_project.sed.key.core.breakpoints.KeYWatchpoint;
import org.key_project.sed.key.ui.dialogs.AddKeYWatchpointDialog;
import org.key_project.util.eclipse.WorkbenchUtil;

/**
 * A class to handle the action of the Add KeY Watchpoint Button being pressed.
 * 
 * @author Marco Drebing
 */
@SuppressWarnings("restriction")
public class AddWatchpointHandler extends AbstractHandler {
   
   /**
    * condition as given by the user
    */
   private String condition;
   
   /**
    * type selected by the user
    */
   private IType type;

   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException {
      try {
         if(safelyOpenDialog(getInitialType())){
            if(type!=null&&condition!=null){
               new KeYWatchpoint(type, condition);
            }
         }
      }
      catch (CoreException e) {
         LogUtil.getLogger().logError(e);
      }
      return null;
   }
   
   /**
    * Returns the initial Type to put in the type selection field.
    * 
    * @return the type currently opened in the editor, if it is a valid java type
    */
   private String getInitialType(){
    IEditorPart editor = WorkbenchUtil.getActiveEditor();
    if(editor!=null&&editor instanceof ITextEditor){
       ITextEditor textEditor = (ITextEditor)editor;
       IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
       if(resource.getFileExtension().equals("java")){
          String name = resource.getName();
          name = name.substring(0, name.length()-5);
          return name;
       }
    }
    return null;
   }

   /**
    * Opens an error dialog if no condition has been entered (is this needed?)
    * 
    * @param initialType the initial type for the following dialog
    * @return false if cancel was pressed true otherwise
    * @throws CoreException
    */
   private boolean openErrorNoCondition(String initialType) throws CoreException {
      MessageDialog errorNoCondition = new MessageDialog(JDIDebugUIPlugin.getActiveWorkbenchShell(), "Enter a condition!", null, "Please enter a condition to watch for!", MessageDialog.ERROR, new String []{"Edit Condition", "Cancel"}, 0);
      if (errorNoCondition.open() == Window.OK){
         return safelyOpenDialog(initialType);
      }else{
         return false;
      }
   }

   /**
    * Opens a dialog with the initial type in the type selection field
    * 
    * @param initialType the initial type for the selection field
    * @return true if a valid type and condition have been entered, false otherwise
    * @throws CoreException
    */
   private boolean safelyOpenDialog(String initialType) throws CoreException {
      AddKeYWatchpointDialog dialog = new AddKeYWatchpointDialog(WorkbenchUtil.getActiveShell(), initialType);
      dialog.create();
      if(dialog.open()==Window.OK){
         String dialogCondition = dialog.getCondition();
         IType dialogType = dialog.getFinalType();
         if(dialogType!=null && dialogCondition!=null && !dialogCondition.equals("")){
            condition = dialogCondition;
            type = dialogType;
            return true;
         }else{
            return openErrorNoCondition(initialType);
         }
      }
      return false;
   } 

}