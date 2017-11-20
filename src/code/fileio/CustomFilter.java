package code.fileio;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CustomFilter extends FileFilter{

	@Override
	public boolean accept(File arg0) {
		 if (arg0.isDirectory()) {
		        return true;
		    }

	    String extension = getExtensionOfFile(arg0);
	    if (extension != null) {
	        if (extension.equals("lca")){
	            return true;
	        } 
	        else {
	            return false;
	        }
	    }

	    return false;
	}

	@Override
	public String getDescription() {
		return ".lca";
	}
	
	public String getExtensionOfFile(File arg0) {
        
		String nameOfFile = arg0.getName();
		String extension = null;
        
        int i = nameOfFile.lastIndexOf('.');

        if (i > 0 &&  i < nameOfFile.length() - 1) {
            extension = nameOfFile.substring(i+1).toLowerCase();
        }
        return extension;
    }

}
