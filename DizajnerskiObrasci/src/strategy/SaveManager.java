package strategy;

import java.io.File;

public class SaveManager implements Save {

	
	Save save;
	
	public SaveManager(Save save){
		this.save = save;
	}
	
	
	@Override
	public void save(Object o, File f) {
		save.save(o, f);

	}

}
