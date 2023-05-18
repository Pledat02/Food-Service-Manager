package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.NetManagement;
import View.Frame;

public class ExcelController implements WindowListener {

	private Frame frame;
	private NetManagement netManagement = NetManagement.getInstance();
	private LinkedHashMap<String, Double> previousHashMap;

	public ExcelController(Frame frame) {
		this.frame = frame;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			String[] columnsOrderName = {"Staff", "Order", "Time", "Quantity", "Payment Method"};
			
			JTable oldOrderData = getOldData("C:\\Users\\MSI DAT\\OneDrive\\Máy tính\\TransactionHistory.xlsx", columnsOrderName);
			
			exportHistoryToExcel(LinkData(oldOrderData, frame.history.getHistoryTable(), columnsOrderName), "C:\\Users\\MSI DAT\\OneDrive\\Máy tính\\TransactionHistory.xlsx");
			exportQuantityToExcel(frame.history.getQuantityTable(), "C:\\Users\\MSI DAT\\OneDrive\\Máy tính\\RemainQuantitty.xlsx");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public static void exportHistoryToExcel(JTable table, String filePath) throws Exception {
		TableModel model = table.getModel();
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
		for (int r = 0; r < model.getRowCount(); r++) {
			row = sheet.createRow(r);
			for (int c = 0; c < model.getColumnCount(); c++) {
				cell = row.createCell(c);
				Object value = model.getValueAt(r, c);
				if (value instanceof String) {
					cell.setCellValue((String) value);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				}
			}
		}
		for (int columnIndex = 0; columnIndex < sheet.getLastRowNum(); columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
		FileOutputStream out = new FileOutputStream(new File(filePath));
		workbook.write(out);
		out.close();
		workbook.close();
	}
	
	public static void exportQuantityToExcel(JTable table, String filePath) throws Exception {
		TableModel model = table.getModel();
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
//		row = sheet.createRow(0);
//		row.createCell(0).setCellValue("Name");
//		row.createCell(1).setCellValue("Remain Quantity");
//		row.createCell(2).setCellValue("Price");
		for (int r = 0; r < model.getRowCount(); r++) {
			row = sheet.createRow(r);
			for (int c = 0; c < model.getColumnCount(); c++) {
				cell = row.createCell(c);
				Object value = model.getValueAt(r, c);
				if (value instanceof String) {
					cell.setCellValue((String) value);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				}
			}
		}
		for (int columnIndex = 0; columnIndex < sheet.getLastRowNum(); columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
		FileOutputStream out = new FileOutputStream(new File(filePath));
		workbook.write(out);
		out.close();
		workbook.close();
	}

	public JTable getOldData(String filePath, String[] columnNames) {
		JTable jtable = null;
		try {
			String fileExcel2007 = filePath;
			Workbook wb2007 = new XSSFWorkbook(fileExcel2007);
			Sheet sheet = wb2007.getSheetAt(0);
			String[][] data = new String[sheet.getLastRowNum() + 1][5];
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					data[i][j] = row.getCell(j) + "";
				}
			}
			jtable = new JTable(data, columnNames);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jtable;
	}
	
	public JTable LinkData(JTable table1, JTable table2, String[] columnNames) {
		int startRow = table1.getRowCount();
		int dataRows = table2.getRowCount();
		String[][] data = new String[startRow + dataRows][table1.getColumnCount()];
		for (int i = 0; i < startRow; i++) {
			for (int j = 0; j < table1.getColumnCount(); j++) {
				data[i][j] = table1.getModel().getValueAt(i, j) + "";
			}
		}
		for (int i = startRow; i < startRow + dataRows; i++) {
			for (int j = 0; j < table2.getColumnCount(); j++) {
				data[i][j] = table2.getModel().getValueAt(i - startRow, j) + "";
			}
		}
		return new JTable(data, columnNames);
	}
}
