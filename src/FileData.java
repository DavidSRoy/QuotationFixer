import java.io.File;

public class FileData extends File {
	private String name;
	private String extension;
	private String url;
	
	public FileData(String url) {
		super(url);
		this.url = url;
		name = url.substring(url.lastIndexOf('\\'), url.lastIndexOf('.'));
		extension = url.substring(url.lastIndexOf('.'), url.length());
	}
	
	public String getName() {
		return this.name;
	}
	public String getExtension() {
		return extension;
	}

}
