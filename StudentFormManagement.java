import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StudentFormManagement extends JFrame {

	private JPanel contentPane;
	Connection connection = null;
	private JTable table;
	private JTextField idField;
	private JTextField nameField;
	private JTextField feesField;
	private JTextField dobField;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFormManagement frame = new StudentFormManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshTable()
	{
		try 
		{
			String query = "select ID, Name, Fees, DateOfBirth from StudentInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		} 
		catch (Exception e2) 
		{
			System.out.println(e2.getMessage());
		}
	}
	
	public void refreshFields()
	{
		idField.setText(null);
		nameField.setText(null);
		feesField.setText(null);
		dobField.setText(null);
	}

	/**
	 * Create the frame.
	 */
	public StudentFormManagement() 
	{
		connection = SqliteConnection.dbConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 758);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					int row = table.getSelectedRow();
					String ID = (table.getModel().getValueAt(row, 0)).toString();
					
					String query = "select * from StudentInfo where ID='"+ID+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{
						idField.setText(rs.getString("ID"));
						nameField.setText(rs.getString("Name"));
						feesField.setText(rs.getString("Fees"));
						dobField.setText(rs.getString("DateOfBirth"));
					}
					
					rs.close();
					pst.close();	
				} 
				catch (Exception e2) 
				{
					System.out.println(e2.getMessage());
				}
			}
		});
		table.setBounds(106, 509, 832, 190);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("LaSalle College - Student Dashboard");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(38, 10, 921, 54);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("International School - Montreal Canada");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(224, 74, 559, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\bob2a\\Downloads\\Asset_1\\Asset_1\\download.png"));
		lblNewLabel_2.setBounds(48, 82, 184, 190);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Student Information");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(454, 116, 219, 40);
		contentPane.add(lblNewLabel_3);
		
		JLabel IDLabel = new JLabel("ID:");
		IDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		IDLabel.setBounds(354, 198, 147, 32);
		contentPane.add(IDLabel);
		
		JLabel StudentNLabel = new JLabel("Student Name:");
		StudentNLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		StudentNLabel.setBounds(354, 240, 159, 32);
		contentPane.add(StudentNLabel);
		
		JLabel FeesLabel = new JLabel("Fees:");
		FeesLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		FeesLabel.setBounds(354, 282, 147, 32);
		contentPane.add(FeesLabel);
		
		JLabel dobLabel = new JLabel("Date Of Birth:");
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		dobLabel.setBounds(354, 324, 147, 32);
		contentPane.add(dobLabel);
		
		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.BOLD, 18));
		idField.setBounds(523, 198, 193, 32);
		contentPane.add(idField);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.BOLD, 18));
		nameField.setColumns(10);
		nameField.setBounds(523, 240, 193, 32);
		contentPane.add(nameField);
		
		feesField = new JTextField();
		feesField.setFont(new Font("Tahoma", Font.BOLD, 18));
		feesField.setColumns(10);
		feesField.setBounds(523, 282, 193, 32);
		contentPane.add(feesField);
		
		dobField = new JTextField();
		dobField.setFont(new Font("Tahoma", Font.BOLD, 18));
		dobField.setColumns(10);
		dobField.setBounds(523, 324, 193, 32);
		contentPane.add(dobField);
		
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String query = "insert into StudentInfo(ID, Name, Fees, DateOfBirth) values(?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, idField.getText());
					pst.setString(2, nameField.getText());
					pst.setString(3, feesField.getText());
					pst.setString(4, dobField.getText());
			
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Student " + nameField.getText() + " has been added successfully!");
					pst.close();
				} 
				catch (Exception ex) 
				{
					System.out.println(ex.getMessage());
				}
				refreshTable();
				refreshFields();
			}
		});
		insertButton.setHorizontalAlignment(SwingConstants.LEFT);
		insertButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		insertButton.setIcon(new ImageIcon("C:\\Users\\bob2a\\Downloads\\Asset_1\\Asset_1\\button_blue_add.png"));
		insertButton.setBounds(781, 198, 178, 48);
		contentPane.add(insertButton);
		
		JButton updateButton = new JButton("Update");
		updateButton.setIcon(new ImageIcon("C:\\Users\\bob2a\\Downloads\\Asset_1\\Asset_1\\button_pink_close.png"));
		updateButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String query = "update StudentInfo set ID='"+idField.getText()+"', "
							+ "Name='"+nameField.getText()+"', Fees='"+feesField.getText()+"',"
									+ "DateOfBirth='"+dobField.getText()+"' where ID='"+idField.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data updated!");
					pst.close();
				} 
				catch (Exception ex) 
				{
					System.out.println(ex.getMessage());
				}
				refreshTable();
				refreshFields();
			}
		});
		updateButton.setHorizontalAlignment(SwingConstants.LEFT);
		updateButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		updateButton.setBounds(781, 257, 178, 48);
		contentPane.add(updateButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int action = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Delete", JOptionPane.YES_NO_OPTION);
				
				if(action==0)
				{
					try 
					{
						String query = "delete from StudentInfo where ID='"+idField.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data deleted!");
						pst.close();
					} 
					catch (Exception e2) 
					{
						System.out.println(e2.getMessage());
					}
					refreshTable();
					refreshFields();
				}
			}
		});

		deleteButton.setIcon(new ImageIcon("C:\\Users\\bob2a\\Downloads\\Asset_1\\Asset_1\\button_red_stop.png"));
		deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
		deleteButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		deleteButton.setBounds(781, 316, 178, 48);
		contentPane.add(deleteButton);
		
		JLabel lblNewLabel_4 = new JLabel("Search Student By Name: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_4.setBounds(256, 451, 245, 32);
		contentPane.add(lblNewLabel_4);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					String query = "select ID, Name, Fees, DateOfBirth from StudentInfo "
							+ "where Name=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, searchField.getText());
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		});
		searchField.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchField.setBounds(523, 449, 193, 32);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		refreshTable();
	}
}
