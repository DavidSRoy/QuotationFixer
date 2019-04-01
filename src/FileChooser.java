import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class FileChooser extends JFileChooser {
	public FileChooser(){
		  this(null);
		}
	
		public FileChooser(String path){
		   super(path);
		}
		
		@Override
		public void approveSelection()
		    {
		        if (getSelectedFile().isFile())
		        {
		            // beep
		            return;
		        }
		        else
		            super.approveSelection();
		    }
}
