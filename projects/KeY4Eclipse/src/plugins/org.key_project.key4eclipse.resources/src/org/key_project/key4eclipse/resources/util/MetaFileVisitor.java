package org.key_project.key4eclipse.resources.util;

import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * This class visits every {@link IResource} and collects all Meta {@link IFile}s.
 * @author Stefan K�sdorf
 */
public class MetaFileVisitor implements IResourceVisitor {

   private LinkedList<IFile> metaFileList = new LinkedList<IFile>();

   public LinkedList<IFile> getMetaFileList(){
      return metaFileList;
   }
   @Override
   public boolean visit(IResource resource) throws CoreException {
      if(resource.getType() == IResource.FILE){
         IFile file = (IFile) resource;
         if(KeYResourcesUtil.META_FILE_EXTENSION.equalsIgnoreCase(file.getFileExtension())){
            metaFileList.add(file);
         }
      }
      return true;
   }

}