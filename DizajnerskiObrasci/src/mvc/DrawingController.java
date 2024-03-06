package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.Command;
import command.RemoveShapeCmd;
import command.SelectCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UnSelectCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import javafx.stage.FileChooser;
import observer.ButtonObserver;
import observer.ButtonObservable;
import strategy.SaveDrawing;
import strategy.SaveLog;
import strategy.SaveManager;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	
	private Point startPoint;
	private Color outerColor=Color.BLACK;
	private Color innerColor=Color.BLACK;
	
	private ArrayList<Command> commandList = new ArrayList<Command>();
	private ArrayList<Command> redoCommandList = new ArrayList<Command>();
	
	private ArrayList<Shape> selectedList = new ArrayList<Shape>();
	private SaveManager saveLogManager = new SaveManager(new SaveLog());
	private SaveManager saveDrawing = new SaveManager(new SaveDrawing());
	
	ButtonObservable buttonObservable = new ButtonObservable();
	ButtonObserver buttonObserver;
	
	private int logIndex = 0;
	private int logListSize=0;
	
	public DrawingController(DrawingModel model, DrawingFrame frame){
		this.model = model;
		
		this.frame = frame;
		buttonObserver = new ButtonObserver(frame);
		buttonObservable.addPropertyChangeListener(buttonObserver);
		
		
		buttonObservable.selectionChanged(0);
		buttonObservable.undoListChanged(0);
		buttonObservable.redoListChanged(0);
	
	}
	
	public void mouseClicked(MouseEvent e){

		
//		Shape newShape=null;
		Command cmd = null;
		
		if(frame.getTglbtnSelect().isSelected()){
			
			for(int i = 0;i<frame.logListModel.size();i++){
				System.out.println(frame.logListModel.get(i));
			}
			
			Shape selected=null;
			Iterator<Shape> it= model.getShapes().iterator();
			while(it.hasNext()){
				Shape shape=it.next();
				
				if(shape.contains(e.getX(), e.getY())){
					selected=shape;	
				}		
			}
			if(selected!=null){
			
				if(selectedList.contains(selected)){
					int index = model.indexOf(selected);
					cmd = new UnSelectCmd(selectedList,model.get(index));
					
				}else{
					int index = model.indexOf(selected);
					cmd = new SelectCmd(selectedList,model.get(index));
					
				}
			}
		}else if (frame.getTglbtnPoint().isSelected()){
			startPoint=null;

			cmd = new AddShapeCmd(new Point(e.getX(),e.getY(),false,outerColor),model);
		}else if(frame.getTglbtnLine().isSelected()){
			if(startPoint==null){
				startPoint=new Point(e.getX(),e.getY());
			}else{

				cmd = new AddShapeCmd(new Line(startPoint,new Point(e.getX(),e.getY()),false,outerColor),model);
				startPoint=null;
			}
		}else if(frame.getTglbtnRectangle().isSelected()){
			startPoint=null;
			DlgRectangle dlg=new DlgRectangle();
			dlg.setTxtX(Integer.toString(e.getX())); 
			dlg.setTxtY(Integer.toString(e.getY()));
			dlg.getPnlInnerColor().setBackground(innerColor);
			dlg.getPnlOuterColor().setBackground(outerColor);
			dlg.setVisible(true);
			if(dlg.isOk()){
			

				cmd = new AddShapeCmd(dlg.getRectangle(),model);
			}
			else{
				return;
			}
		}else if(frame.getTglbtnCircle().isSelected()){
			startPoint=null;
			
			DlgCircle dlg=new DlgCircle();
			dlg.setTxtX(Integer.toString(e.getX()));
			dlg.setTxtY(Integer.toString(e.getY()));
			dlg.getPnlInnerColor().setBackground(innerColor);
			dlg.getPnlOuterColor().setBackground(outerColor);
			dlg.setVisible(true);
			if(dlg.isOk()){
				
				cmd = new AddShapeCmd(dlg.getCircle(),model);
			}
			else{
				return;
			}
			
		}else if(frame.getTglbtnDonut().isSelected()){
			startPoint=null;
			
			DlgDonut dlg=new DlgDonut();
			dlg.setTxtX(Integer.toString(e.getX()));
			dlg.setTxtY(Integer.toString(e.getY()));
			dlg.getPnlInnerColor().setBackground(innerColor);
			dlg.getPnlOuterColor().setBackground(outerColor);
			dlg.setVisible(true);
			if(dlg.isOk()){
				

				cmd = new AddShapeCmd(dlg.getDonut(),model);
			}
			else{
				return;
			}
		}else if(frame.getTglbtnHexagon().isSelected()){
			startPoint = null;
			
			DlgHexagon dlg = new DlgHexagon();
			dlg.setTxtX(Integer.toString(e.getX()));
			dlg.setTxtY(Integer.toString(e.getY()));
			dlg.getPnlInnerColor().setBackground(innerColor);
			dlg.getPnlOuterColor().setBackground(outerColor);
			dlg.setVisible(true);
			if(dlg.isOk()){
				
				cmd = new AddShapeCmd(dlg.getHexagon(),model);
			}else{
				return;
			}
		}

		if(cmd != null){
			
			cmd.execute();
			buttonObservable.selectionChanged(selectedList.size());
			
			commandList.add(cmd);
			frame.logListModel.addElement(cmd.toString());
			redoCommandList.clear();
			buttonObservable.undoListChanged(commandList.size());
			buttonObservable.redoListChanged(redoCommandList.size());
		}
		frame.repaint();
		
	
	}
	//Add remove multiple shapes command
	public void delete(){
		
		
			Object[] options = { "Yes", "No" };
			int reply=JOptionPane.showOptionDialog(frame, "Are you sure you want to delete it", "Warning",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, JOptionPane.YES_NO_OPTION);
			if(reply==JOptionPane.YES_OPTION){
				
				for (Shape sel : selectedList) {
					Command removeCmd = new RemoveShapeCmd(sel, model);
					
					
					removeCmd.execute();
					commandList.add(removeCmd);
					frame.getLogListModel().addElement(removeCmd.toString());
					
				}
				
				
				redoCommandList.clear();
				selectedList.clear();
				buttonObservable.selectionChanged(selectedList.size());
				buttonObservable.undoListChanged(commandList.size());
				buttonObservable.redoListChanged(redoCommandList.size());
				frame.repaint();
			}
		 
		
		
		
	}
	

	public void modify(){
	

		Shape selected = selectedList.get(0);
	
		Command updateCommand=null;
		if(selected instanceof Point){
			Point point = (Point)selected;
			DlgPoint dlg=new DlgPoint();
			
			dlg.setTxtX(Integer.toString(point.getX()));
			dlg.setTxtY(Integer.toString(point.getY()));
			dlg.getPnlColor().setBackground(point.getColor());
			dlg.setVisible(true);
			
			
			
			if(dlg.isOk()){

				updateCommand = new UpdatePointCmd((Point)selected, dlg.getPoint());
				
			}
			
		} else if(selected instanceof Line){
			Line line = (Line) selected;
			DlgLine dlg=new DlgLine();
			
			dlg.setTxtStartX(Integer.toString(line.getStartPoint().getX()));
			dlg.setTxtStartY(Integer.toString(line.getStartPoint().getY()));
			dlg.setTxtEndX(Integer.toString(line.getEndPoint().getX()));
			dlg.setTxtEndY(Integer.toString(line.getEndPoint().getY()));
			dlg.getPnlColor().setBackground(line.getColor());
			dlg.setVisible(true);
			if(dlg.isOk()){
				
				updateCommand = new UpdateLineCmd((Line)selected, dlg.getLine());
				
			}
			
		}else if(selected instanceof Rectangle){
			
			Rectangle rect=(Rectangle) selected;
			DlgRectangle dlg=new DlgRectangle();
			dlg.setTxtX(Integer.toString(rect.getUpperLeftPoint().getX()));
			dlg.setTxtY(Integer.toString(rect.getUpperLeftPoint().getY()));
			dlg.setTxtHeight(Integer.toString(rect.getHeight()));
			dlg.setTxtWidth(Integer.toString(rect.getWidth()));
			dlg.getPnlInnerColor().setBackground(rect.getInnerColor());
			dlg.getPnlOuterColor().setBackground(rect.getColor());
			dlg.setVisible(true);
			if(dlg.isOk()){
			
				updateCommand = new UpdateRectangleCmd((Rectangle) selected , dlg.getRectangle());
				
			}
			
		}else if(selected instanceof Donut){
			
			
			Donut donut = (Donut) selected;
			DlgDonut dlg=new DlgDonut();
			dlg.setTxtX(Integer.toString(donut.getCenter().getX()));
			dlg.setTxtY(Integer.toString(donut.getCenter().getY()));
			dlg.setTxtInnerRadius(Integer.toString(donut.getInnerRadius()));
			dlg.setTxtOuterRadius(Integer.toString(donut.getRadius()));
			dlg.getPnlOuterColor().setBackground(donut.getInnerColor());
			dlg.getPnlOuterColor().setBackground(donut.getColor());
			dlg.setVisible(true);
			
			if(dlg.isOk()){
				
				updateCommand = new UpdateDonutCmd((Donut)selected, dlg.getDonut());
				
			}
			
		}else if(selected instanceof Circle){
			Circle circle = (Circle) selected;
			DlgCircle dlg = new DlgCircle();
			dlg.setTxtX(Integer.toString(circle.getCenter().getX()));
			dlg.setTxtY(Integer.toString(circle.getCenter().getY()));
			dlg.setTxtRadius(Integer.toString(circle.getRadius()));
			dlg.getPnlInnerColor().setBackground(circle.getInnerColor());
			dlg.getPnlOuterColor().setBackground(circle.getColor());
			dlg.setVisible(true);
			
			if(dlg.isOk()){
				
				updateCommand = new UpdateCircleCmd((Circle)selected, dlg.getCircle());
			
			}
			
		}else {
			HexagonAdapter hexagon = (HexagonAdapter) selected;
			DlgHexagon dlg = new DlgHexagon();
			dlg.setTxtX(Integer.toString(hexagon.getCenter().getX()));
			dlg.setTxtY(Integer.toString(hexagon.getCenter().getY()));
			dlg.setTxtRadius(Integer.toString(hexagon.getRadius()));
			dlg.getPnlInnerColor().setBackground(hexagon.getInnerColor());
			dlg.getPnlOuterColor().setBackground(hexagon.getColor());
			dlg.setVisible(true);
			
			if(dlg.isOk()){
				
				updateCommand = new UpdateHexagonCmd((HexagonAdapter)selected,dlg.getHexagon());
			}
			
		}
		
		if(updateCommand != null){
			updateCommand.execute();
			commandList.add(updateCommand);
			redoCommandList.clear();
			buttonObservable.undoListChanged(commandList.size());
			buttonObservable.redoListChanged(redoCommandList.size());
			frame.logListModel.addElement(updateCommand.toString());
			frame.repaint();
		}
		
	
	
	
	
	
	
	}

	
	
	public void changeOuterColor(){
		Color color = JColorChooser.showDialog(null, "Choose outer color", outerColor);
		if(color!=null){
			System.out.println("Chosen");
			outerColor = color;
			frame.getPnlOuterColor().setBackground(color);
		}
	}
	
	public void changeInnerColor(){
		Color color = JColorChooser.showDialog(null, "Choose inner color", innerColor);
		if(color!=null){
			System.out.println("Chosen");
			innerColor = color;
			frame.getPnlInnerColor().setBackground(color);
		}
	}
	
	public void undo(){
		//fix, probably add observer and disable undo if it cant anymore
		
		commandList.get(commandList.size()-1).unexecute();
		if(commandList.get(commandList.size()-1) instanceof RemoveShapeCmd){
			selectedList.add(((RemoveShapeCmd)commandList.get(commandList.size()-1)).getShape());
			((RemoveShapeCmd)commandList.get(commandList.size()-1)).getShape().setSelected(true);
		}
		
		buttonObservable.selectionChanged(selectedList.size());
		if(frame.getTglbtnSelect().isEnabled()){
			frame.logListModel.addElement("Undo "+ commandList.get(commandList.size()-1).toString());
		}
		frame.repaint();
		redoCommandList.add(commandList.get(commandList.size()-1));
		commandList.remove(commandList.size()-1);
		buttonObservable.undoListChanged(commandList.size());
		buttonObservable.redoListChanged(redoCommandList.size());
	}
	
	public void redo(){
		
		redoCommandList.get(redoCommandList.size()-1).execute();
		if(redoCommandList.get(redoCommandList.size()-1) instanceof RemoveShapeCmd){
			selectedList.remove(((RemoveShapeCmd)redoCommandList.get(redoCommandList.size()-1)).getShape());
		}
		
		
		buttonObservable.selectionChanged(selectedList.size());
		if(frame.getTglbtnSelect().isEnabled()){
			frame.logListModel.addElement("Redo "+redoCommandList.get(redoCommandList.size()-1).toString());
		}
		commandList.add(redoCommandList.get(redoCommandList.size()-1));
		frame.repaint();
		
		redoCommandList.remove(redoCommandList.size()-1);
		buttonObservable.undoListChanged(commandList.size());
		buttonObservable.redoListChanged(redoCommandList.size());
		
	}
	
	public void toBack(){
		
			int originalIndex = model.indexOf(selectedList.get(0));
			Command cmd = new ToBackCmd(model, selectedList.get(0));
			cmd.execute();
			if(originalIndex == model.indexOf(selectedList.get(0))){
				return;
			}
			commandList.add(cmd);
			if(frame.getTglbtnSelect().isEnabled()){
				frame.logListModel.addElement(cmd.toString());
			}
			frame.repaint();
		
	}
	
	public void toFront(){
		
			int originalIndex = model.indexOf(selectedList.get(0));
			Command cmd = new ToFrontCmd(model, selectedList.get(0));
			cmd.execute();
			if(originalIndex == model.indexOf(selectedList.get(0))){
				return;
			}
			
			commandList.add(cmd);
			if(frame.getTglbtnSelect().isEnabled()){
				frame.logListModel.addElement(cmd.toString());
			}
			frame.repaint();
			
			
			
		
	}
	
	
	public void bringToFront(){
		
		
			int originalIndex = model.indexOf(selectedList.get(0));
			Command cmd = new BringToFrontCmd(model, selectedList.get(0));
			cmd.execute();
			if(originalIndex == model.indexOf(selectedList.get(0))){
				return;
			}
			commandList.add(cmd);
			if(frame.getTglbtnSelect().isEnabled()){
				frame.logListModel.addElement(cmd.toString());
			}
			redoCommandList.clear();
			buttonObservable.undoListChanged(commandList.size());
			buttonObservable.redoListChanged(redoCommandList.size());
			
			frame.repaint();
			
			
			
		
	}
	
	public void bringToBack(){
		
		
			int originalIndex = model.indexOf(selectedList.get(0));
			Command cmd = new BringToBackCmd(model, selectedList.get(0));
			cmd.execute();
			if(originalIndex == model.indexOf(selectedList.get(0))){
				return;
			}
			commandList.add(cmd);
			if(frame.getTglbtnSelect().isEnabled()){
				frame.logListModel.addElement(cmd.toString());
			}
			redoCommandList.clear();
			buttonObservable.undoListChanged(commandList.size());
			buttonObservable.redoListChanged(redoCommandList.size());
			
			frame.repaint();
			
			
		
	}

	public ArrayList<Shape> getSelectedList() {
		return selectedList;
	}
	
	
	public void saveLog(){

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");  
	
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnValue = fileChooser.showSaveDialog(frame);
		
		if(returnValue == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			
			 if (!file.getName().toLowerCase().endsWith(".txt")) {
			        file = new File(file.getParentFile(), file.getName() + ".txt");
			      }
			saveLogManager.save(frame, file);
			
			
		
		}
	}
	//Put warning to save before loading
	public void LoadLog(){
		int reply = 0;
		if(model.getShapes().size()!=0){
			Object[] options = { "Continue", "Cancel" };
			  reply=JOptionPane.showOptionDialog(frame, "Any progress not saved will be lost after loading.", "Warning",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, JOptionPane.YES_NO_OPTION);
		}
		if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){
			System.out.println("Cancel");
			return;
		}
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select log file");  
	
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnValue = fileChooser.showOpenDialog(frame);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			
			
			
			File file = fileChooser.getSelectedFile();
			deleteEverything();
			
			readFileToLog(file);
			
			
		}
		
	}
	
	
	public void readFileToLog(File file){
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String txtLine;
			if((txtLine = reader.readLine())==null){
				JOptionPane.showMessageDialog(frame, 
						"File is empty",
						"ERROR",
						JOptionPane.ERROR_MESSAGE);
				reader.close();
				return;
			}
			if(!txtLine.split(" ")[0].equals("Add")){
				JOptionPane.showMessageDialog(frame, 
						"Wrong file",
						"ERROR",
						JOptionPane.ERROR_MESSAGE);
				reader.close();
				return;
			}
			frame.logListModel.addElement(txtLine);
		
			while((txtLine = reader.readLine())!=null){
				frame.logListModel.addElement(txtLine);
			
			}
			
			reader.close();
			logListSize = frame.logListModel.size();
			
			frame.getBtnNext().setVisible(true);
			frame.getTglbtnCircle().setEnabled(false);
			frame.getTglbtnDonut().setEnabled(false);
			frame.getTglbtnHexagon().setEnabled(false);
			frame.getTglbtnLine().setEnabled(false);
			frame.getTglbtnPoint().setEnabled(false);
			frame.getTglbtnRectangle().setEnabled(false);
			frame.getTglbtnSelect().setEnabled(false);
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnUndo().setEnabled(false);
			frame.getBtnRedo().setEnabled(false);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void deleteEverything(){
		model.getShapes().clear();
		selectedList.clear();
		commandList.clear();
		redoCommandList.clear();
		frame.getLogListModel().clear();
		logListSize = 0;
		logIndex = 0;
		
		buttonObservable.selectionChanged(selectedList.size());
		buttonObservable.undoListChanged(commandList.size());
		buttonObservable.redoListChanged(redoCommandList.size());
		
		frame.repaint();
	}
	
	public void loadNext(){
		if(logListSize>logIndex){
			loadCommand(frame.getLogListModel().get(logIndex));
			logIndex++;
		}else{
			frame.getBtnNext().setVisible(false);
			
			frame.getTglbtnCircle().setEnabled(true);
			frame.getTglbtnDonut().setEnabled(true);
			frame.getTglbtnHexagon().setEnabled(true);
			frame.getTglbtnLine().setEnabled(true);
			frame.getTglbtnPoint().setEnabled(true);
			frame.getTglbtnRectangle().setEnabled(true);
			frame.getTglbtnSelect().setEnabled(true);
			frame.getBtnModify().setEnabled(true);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
			frame.getBtnBringToFront().setEnabled(true);
			frame.getBtnToBack().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
			frame.getBtnUndo().setEnabled(true);
			frame.getBtnRedo().setEnabled(true);
		}
	}
	
	
	public void loadCommand(String commandString){
		Shape originalShape = null;
		Shape newShape=null;
		Command cmd=null;
		ArrayList<Integer> numbers =new ArrayList<Integer>();
		Matcher m = Pattern.compile("\\[(-?[0-9]+)\\]").matcher(commandString);
		while (m.find()) {
		
		  numbers.add(Integer.parseInt(m.group(1)));
		}
		if(commandString.contains("Point")){
			
			originalShape = new Point(numbers.get(0),numbers.get(1),false,new Color(numbers.get(2)));
		}else if(commandString.contains("Line")){
			originalShape = new Line(new Point(numbers.get(0),numbers.get(1)),new Point(numbers.get(2),numbers.get(3)),false,new Color(numbers.get(4)));
		}else if(commandString.contains("Rectangle")){
			originalShape = new Rectangle(new Point(numbers.get(0),numbers.get(1)), numbers.get(2), numbers.get(3), false, new Color(numbers.get(4)), new Color(numbers.get(5)));
		}
		else if(commandString.contains("Circle")){

				originalShape = new Circle(new Point(numbers.get(0),numbers.get(1)),numbers.get(2),false,new Color(numbers.get(3)),new Color(numbers.get(4)));
			
		}else if(commandString.contains("Donut")){
			originalShape = new Donut(new Point(numbers.get(0),numbers.get(1)), numbers.get(2), numbers.get(3), false, new Color(numbers.get(4)), new Color(numbers.get(5)));
		}else if(commandString.contains("Hexagon")){
			originalShape = new HexagonAdapter(new Point(numbers.get(0),numbers.get(1)), numbers.get(2), false, new Color(numbers.get(3)), new Color(numbers.get(4)));
		}
		
		
		if(commandString.contains("Undo")){
			undo();
		}else if(commandString.contains("Redo")){
			redo();
		}
		else if(commandString.contains("Add")){
			cmd = new AddShapeCmd(originalShape, model);
		
		}else if(commandString.contains("Update")){
			if(originalShape instanceof Point){
				newShape = new Point(numbers.get(3),numbers.get(4),false,new Color(numbers.get(5)));
				int index = model.indexOf(originalShape);
				cmd = new UpdatePointCmd((Point)model.get(index),(Point) newShape);
				
			}else if(originalShape instanceof Line){
				newShape = new Line(new Point(numbers.get(5),numbers.get(6)),new Point(numbers.get(7),numbers.get(8)),false,new Color(numbers.get(9)));
				int index = model.indexOf(originalShape);
				cmd = new UpdateLineCmd((Line)model.get(index), (Line)newShape);
				
			}else if(originalShape instanceof Rectangle){
				newShape = new Rectangle(new Point(numbers.get(6),numbers.get(7)), numbers.get(8), numbers.get(9), false, new Color(numbers.get(10)), new Color(numbers.get(11)));
				int index = model.indexOf(originalShape);
				cmd = new UpdateRectangleCmd((Rectangle)model.get(index), (Rectangle)newShape);
				
			}else if(originalShape instanceof Donut){
				newShape = originalShape = new Donut(new Point(numbers.get(6),numbers.get(7)), numbers.get(8), numbers.get(9), false, new Color(numbers.get(10)), new Color(numbers.get(11)));
				int index = model.indexOf(originalShape);
				cmd = new UpdateDonutCmd((Donut) model.get(index), (Donut) newShape);
				
			}else if(originalShape instanceof Circle){
				newShape = new Circle(new Point(numbers.get(5),numbers.get(6)),numbers.get(7),false,new Color(numbers.get(8)),new Color(numbers.get(9)));
				int index = model.indexOf(originalShape);
				cmd = new UpdateCircleCmd((Circle)model.get(index),(Circle)newShape);
			}else if(originalShape instanceof HexagonAdapter){
				newShape = new HexagonAdapter(new Point(numbers.get(5),numbers.get(6)), numbers.get(7), false, new Color(numbers.get(8)), new Color(numbers.get(9)));
				int index = model.indexOf(originalShape);
				cmd = new UpdateHexagonCmd((HexagonAdapter)model.get(index), (HexagonAdapter)newShape);
				
			}
		}else if(commandString.contains("Remove")){
			cmd = new RemoveShapeCmd(originalShape, model);
				
				
				selectedList.remove(originalShape);
				
			
		}else if (commandString.contains("Select")){
			
			int index = model.indexOf(originalShape);
			cmd = new SelectCmd(selectedList, model.get(index));
		}else if (commandString.contains("Unselect")){
			
			int index = model.indexOf(originalShape);
			cmd = new UnSelectCmd(selectedList, model.get(index));
			
		}else if(commandString.contains("To front")){
			toFront();
		}else if(commandString.contains("To back")){
			toBack();
		}else if(commandString.contains("Bring to front")){
			bringToFront();
		}else if(commandString.contains("Bring to back")){
			bringToBack();
		}
				
		if(cmd!=null){
			cmd.execute();
			commandList.add(cmd);
			
			redoCommandList.clear();
			buttonObservable.undoListChanged(commandList.size());
			buttonObservable.redoListChanged(redoCommandList.size());
			buttonObservable.selectionChanged(selectedList.size());
			frame.repaint();
			
		}
		
		
	}
	
	
	public void saveDrawing(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");  
	
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.ser", "ser"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnValue = fileChooser.showSaveDialog(frame);
		
		if(returnValue == JFileChooser.APPROVE_OPTION){
		
			File file = fileChooser.getSelectedFile();
			
			 if (!file.getName().toLowerCase().endsWith(".ser")) {
			        file = new File(file.getParentFile(), file.getName() + ".ser");
			      }
			 
			saveDrawing.save(model, file);
			 file = new File(file.getParentFile(), file.getName() + ".txt");
			 saveLogManager.save(frame, file);
		}
		
	}
	
	
	public void loadDrawing(){
		
		int reply = 0;
		if(model.getShapes().size()!=0){
			Object[] options = { "Continue", "Cancel" };
			  reply=JOptionPane.showOptionDialog(frame, "Any progress not saved will be lost after loading.", "Warning",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, JOptionPane.YES_NO_OPTION);
		}
		if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){
			System.out.println("Cancel");
			return;
		}
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select drawing file");  
	
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.ser", "ser"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnValue = fileChooser.showOpenDialog(frame);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			
			
			
			File file = fileChooser.getSelectedFile();
			deleteEverything();
			
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
				Object o = inputStream.readObject();
				if(o instanceof ArrayList<?>){
					if(((ArrayList)o).get(0) instanceof Shape){
						for (Shape shape : (ArrayList<Shape>)o) {
							model.add(shape);
						}
						 file = new File(file.getParentFile(), file.getName() + ".txt");
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String txtLine;
						
						
						
						while((txtLine = reader.readLine())!=null){
							
							frame.logListModel.addElement(txtLine);
						
						}
						
						reader.close();

						
						frame.repaint();
						
						
						
						
						return;
					}
				}

				JOptionPane.showMessageDialog(frame, 
							"Wrong file",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
}
