package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.NetManagement;

import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class TransactionHistory extends JFrame {

	private JPanel contentPane;
	private JTable historyOrderTable, quantityTable;
	private JPanel quantityTablePanel;
	private JPanel historyTablePanel;
	Border lineBorder = BorderFactory.createLineBorder(Color.gray);
	private NetManagement netManagement = NetManagement.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionHistory frame = new TransactionHistory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TransactionHistory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[][] items = new String[netManagement.getListNameItem().length][3];
		String[] columnNames = {"Name", "Remain Quantity", "Price"};
		
		JTable oldQuantityData = getOldData( "C:\\Users\\MSI DAT\\OneDrive\\Máy tính\\RemainQuantitty.xlsx", columnNames);
		TableModel model = oldQuantityData.getModel();
		for (int i = 0; i < items.length; i++) {
			items[i][0]= netManagement.getListNameItem()[i];
			items[i][1]= (String) model.getValueAt(i, 1);
			items[i][2]= netManagement.getListPriceItem()[i];
		}
		
		

		historyOrderTable = new JTable();
		JScrollPane scrollPaneHistory = new JScrollPane(historyOrderTable);
		historyOrderTable.setFont(new Font("Arial", Font.BOLD, 17));
		historyOrderTable.setRowHeight(40);
		historyOrderTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Staff", "Order", "Time", "Quantity", "Payment Method" }));
		historyOrderTable.getColumnModel().getColumn(2).setPreferredWidth(222);
		historyOrderTable.setDefaultEditor(Object.class, null);
		quantityTablePanel = new JPanel();
		quantityTablePanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Items"));
		quantityTablePanel.setLayout(new BorderLayout(0, 0));
		quantityTable = new JTable();

		JScrollPane scrollPaneQuantity = new JScrollPane(quantityTable);
		quantityTablePanel.add(scrollPaneQuantity);
		quantityTable.setFont(new Font("Arial", Font.BOLD, 17));
		quantityTable.setRowHeight(30);
		quantityTable.setModel(new DefaultTableModel(items, new String[] { "Name", "Remain Quantity", "Price" }));
		historyOrderTable.getColumnModel().getColumn(2).setPreferredWidth(222);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(quantityTablePanel);

		historyTablePanel = new JPanel();
		historyTablePanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Order History"));
		historyTablePanel.setLayout(new BorderLayout(0, 0));
		historyTablePanel.add(scrollPaneHistory);
		contentPane.add(historyTablePanel);
	}

	public JTable getHistoryTable() {
		return historyOrderTable;
	}
	
	public JTable getQuantityTable() {
		return quantityTable;
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
}
