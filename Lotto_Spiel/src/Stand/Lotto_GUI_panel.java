package Stand;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//Entwickler  : Enrico Boni
//Version     : 1.0 Alpha
//Bezeichnung : Diese Klasse kummert sich um das Kontrolpanel zu zeigen
public class Lotto_GUI_panel {

	private JFrame 					frmDre;
	private JTextField 				wette_f;
	public static Lotto_Dreh_Rad 	mainapp;
	private JTextField 				uint1;
	private JTextField 				uint2;
	private JTextField 				uint3;
	private JTextField 				uint4;
	private JTextField 				uint5;
	private JTextField 				uint6;
	private JTextField 				pint6;
	private JTextField 				pint1;
	private JTextField 				pint2;
	private JTextField 				pint3;
	private JTextField 				pint4;
	private JTextField 				pint5;
	private JButton 				erstelltCombi;
	
	public int wette=0;
	
	public int[] gui_panel_user_combi;
	public int[] lotto_dreh_rad_combi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		mainapp=new Lotto_Dreh_Rad();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Lotto_GUI_panel window = new Lotto_GUI_panel();
					window.frmDre.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lotto_GUI_panel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gui_panel_user_combi= new int[6];
		lotto_dreh_rad_combi=mainapp.zahlen;
		System.out.println(lotto_dreh_rad_combi);
		frmDre = new JFrame();
		frmDre.setTitle("Dreh das Rad an! (CONTROL PANEL)");
		frmDre.setBounds(100, 100, 450, 300);
		frmDre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDre.getContentPane().setLayout(null);
		
		Timer t=new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lotto_dreh_rad_combi=mainapp.letze_zahlen;
				try {
					pint1.setText(""+lotto_dreh_rad_combi[0]);
					pint2.setText(""+lotto_dreh_rad_combi[1]);
					pint3.setText(""+lotto_dreh_rad_combi[2]);
					pint4.setText(""+lotto_dreh_rad_combi[3]);
					pint5.setText(""+lotto_dreh_rad_combi[4]);
					pint6.setText(""+lotto_dreh_rad_combi[5]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		},0, 500);
		
		JButton spin = new JButton("New button");
		spin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		spin.setBounds(20, 67, 130, 125);
		frmDre.getContentPane().add(spin);
		spin.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (uint1.getText().equals("")||
					uint2.getText().equals("")||
					uint3.getText().equals("")||
					uint4.getText().equals("")||
					uint5.getText().equals("")||
					uint6.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bitte jeden Feld ausfullen");
					
				}else {
					mainapp.startRotation();
					mainapp.setUserCombi(gui_panel_user_combi);
					reset_pints();
				}
			}
		});
		
		wette_f = new JTextField();
		wette_f.setHorizontalAlignment(SwingConstants.CENTER);
		wette_f.setText(""+wette);
		wette_f.setBounds(145, 34, 106, 20);
		frmDre.getContentPane().add(wette_f);
		wette_f.setColumns(10);
		
		JButton minus1 = new JButton("New button");
		minus1.setBounds(100, 33, 35, 23);
		frmDre.getContentPane().add(minus1);
		minus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (wette==1)wette=0;
				else wette-=1;
				wette_f.setText(""+wette);
			}
		});
		
		JButton plu1 = new JButton("New button");
		plu1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				wette+=1;
				wette_f.setText(""+wette);
			}
		});
		plu1.setBounds(261, 33, 35, 23);
		frmDre.getContentPane().add(plu1);
		
		JButton plus5 = new JButton("New button");
		plus5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				wette+=5;
				wette_f.setText(""+wette);
			}
		});
		plus5.setBounds(306, 33, 35, 23);
		frmDre.getContentPane().add(plus5);
		
		JButton plus10 = new JButton("New button");
		plus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wette+=10;
				wette_f.setText(""+wette);
			}
		});
		plus10.setBounds(351, 33, 35, 23);
		frmDre.getContentPane().add(plus10);
		
		JButton minus5 = new JButton("New button");
		minus5.setBounds(55, 33, 35, 23);
		frmDre.getContentPane().add(minus5);
		minus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wette<=5) {
					wette=0;
				}else wette-=5;
				wette_f.setText(""+wette);
			}
		});
		
		JButton minus10 = new JButton("New button");
		minus10.setBounds(10, 33, 35, 23);
		frmDre.getContentPane().add(minus10);
		minus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wette<=10)wette=0;
				else wette-=10;
				wette_f.setText(""+wette);
			}
		});
		
		uint1 = new JTextField();
		uint1.setBounds(10, 203, 35, 20);
		frmDre.getContentPane().add(uint1);
		uint1.setColumns(10);
		
		uint2 = new JTextField();
		uint2.setColumns(10);
		uint2.setBounds(55, 203, 35, 20);
		frmDre.getContentPane().add(uint2);
		
		uint3 = new JTextField();
		uint3.setColumns(10);
		uint3.setBounds(100, 203, 35, 20);
		frmDre.getContentPane().add(uint3);
		
		uint4 = new JTextField();
		uint4.setColumns(10);
		uint4.setBounds(145, 203, 35, 20);
		frmDre.getContentPane().add(uint4);
		
		uint5 = new JTextField();
		uint5.setColumns(10);
		uint5.setBounds(190, 203, 35, 20);
		frmDre.getContentPane().add(uint5);
		
		uint6 = new JTextField();
		uint6.setColumns(10);
		uint6.setBounds(235, 203, 35, 20);
		frmDre.getContentPane().add(uint6);
		
		pint6 = new JTextField();
		pint6.setEditable(false);
		pint6.setColumns(10);
		pint6.setBounds(235, 234, 35, 20);
		frmDre.getContentPane().add(pint6);
		
		pint1 = new JTextField();
		pint1.setEditable(false);
		pint1.setColumns(10);
		pint1.setBounds(10, 234, 35, 20);
		frmDre.getContentPane().add(pint1);
		
		pint2 = new JTextField();
		pint2.setEditable(false);
		pint2.setColumns(10);
		pint2.setBounds(55, 234, 35, 20);
		frmDre.getContentPane().add(pint2);
		
		pint3 = new JTextField();
		pint3.setEditable(false);
		pint3.setColumns(10);
		pint3.setBounds(100, 234, 35, 20);
		frmDre.getContentPane().add(pint3);
		
		pint4 = new JTextField();
		pint4.setEditable(false);
		pint4.setColumns(10);
		pint4.setBounds(145, 234, 35, 20);
		frmDre.getContentPane().add(pint4);
		
		pint5 = new JTextField();
		pint5.setEditable(false);
		pint5.setColumns(10);
		pint5.setBounds(190, 234, 35, 20);
		frmDre.getContentPane().add(pint5);
		
		erstelltCombi = new JButton("Random Number");
		erstelltCombi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ZufalligerZahl zz= new ZufalligerZahl();
				gui_panel_user_combi= new int[6];
				gui_panel_user_combi=zz.createCombination();
				
				uint1.setText(""+gui_panel_user_combi[0]);
				uint2.setText(""+gui_panel_user_combi[1]);
				uint3.setText(""+gui_panel_user_combi[2]);
				uint4.setText(""+gui_panel_user_combi[3]);
				uint5.setText(""+gui_panel_user_combi[4]);
				uint6.setText(""+gui_panel_user_combi[5]);
				
			}
		});
		erstelltCombi.setBounds(280, 203, 148, 20);
		frmDre.getContentPane().add(erstelltCombi);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(null);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setBounds(0, 0, 438, 265);
		frmDre.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(280, 234, 148, 20);
		frmDre.getContentPane().add(panel);
	}
	
	private void reset_pints() {
		uint1.setText(""+gui_panel_user_combi[0]);
		uint2.setText(""+gui_panel_user_combi[1]);
		uint3.setText(""+gui_panel_user_combi[2]);
		uint4.setText(""+gui_panel_user_combi[3]);
		uint5.setText(""+gui_panel_user_combi[4]);
		uint6.setText(""+gui_panel_user_combi[5]);
	}
}
