package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import mvc.DrawingModel;

public class SaveDrawing implements Save {

	@Override
	public void save(Object o, File f) {
		DrawingModel model = (DrawingModel)o;
		
		
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f,false));
			outputStream.writeObject(model.getShapes());
		
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}

}
