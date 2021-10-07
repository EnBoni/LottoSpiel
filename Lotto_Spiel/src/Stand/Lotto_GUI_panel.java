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
	
	//Lotto_Dreh_Rad zug die Zahlkombination aus und zeigt die Drehung des Rad
	public static Lotto_Dreh_Rad 	mainapp;
	
	//uints[0],uints[1]...sind Textfelden beim User benutz,um eigenen Zahlkombination einzugeben
	//uints[0],uints[1]...sind Textfelden beim Program benutz,um ausgezugene Zahlkombination zu zeigen
	private JTextField[] 			pints = new JTextField[6];
	private JTextField[] 			uints = new JTextField[6];
	
	
	//erstelltCombi erstellt eine zufallige zahlKombination,ist ein graphisches Element
	private JButton 				erstelltCombi;
	
	public int wette=0;
	//gui_panel_user_combi erh‰lt die Kombination des User
	//lotto_dreh_rad_combi erh‰lt die Kombination des Program
	
	public int[] gui_panel_user_combi = new int[6];
	public int[] lotto_dreh_rad_combi = new int[6];
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
		frmDre = new JFrame();
		frmDre.setTitle("Dreh das Rad an! (CONTROL PANEL)");
		frmDre.setBounds(100, 100, 450, 300);
		frmDre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDre.getContentPane().setLayout(null);
		//t ist ein timer,der nach dem Beginn funktioniert immer durch der Anwendungseinfuhrung bis das Schlieﬂen des Program
		//aktualiesiert die nicht editierbaren TextFelde mit der Zahlen der letzige Extraktion
		//in Falls keine Extraktion erledigt war,sind die auf "0" standard gesetz.
		Timer t=new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lotto_dreh_rad_combi=mainapp.letze_zahlen;
				try {
					for(int i=0;i<pints.length;i++) {
						pints[i].setText(""+lotto_dreh_rad_combi[i]);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		},0, 500);
		
		JButton spin = new JButton("Spin!");
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
			//IF-Bedingung checkt einfach dass die felden 
			@Override
			public void mouseClicked(MouseEvent e) {
				
				boolean greenlight=true;
				try {
					for(int i=0;i<uints.length || greenlight;i++) {
						int tmp=0;
						try {
							tmp = Integer.parseInt(uints[i].getText());
							System.out.println(tmp);
							
						}catch(NumberFormatException exc) {
							greenlight=false;
						}
						if(tmp>0 && tmp<50) greenlight = true;
						else 				greenlight = false;
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(greenlight) {
					mainapp.startRotation();
					mainapp.setUserCombi(gui_panel_user_combi);
					reset_pints();
				}else {
					JOptionPane.showMessageDialog(null, "Bitte jeden Feld ausfullen");
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
		
		uints[0] = new JTextField();
		uints[0].setBounds(10, 203, 35, 20);
		frmDre.getContentPane().add(uints[0]);
		uints[0].setColumns(10);
		
		uints[1] = new JTextField();
		uints[1].setColumns(10);
		uints[1].setBounds(55, 203, 35, 20);
		frmDre.getContentPane().add(uints[1]);
		
		uints[2] = new JTextField();
		uints[2].setColumns(10);
		uints[2].setBounds(100, 203, 35, 20);
		frmDre.getContentPane().add(uints[2]);
		
		uints[3] = new JTextField();
		uints[3].setColumns(10);
		uints[3].setBounds(145, 203, 35, 20);
		frmDre.getContentPane().add(uints[3]);
		
		uints[4] = new JTextField();
		uints[4].setColumns(10);
		uints[4].setBounds(190, 203, 35, 20);
		frmDre.getContentPane().add(uints[4]);
		
		uints[5] = new JTextField();
		uints[5].setColumns(10);
		uints[5].setBounds(235, 203, 35, 20);
		frmDre.getContentPane().add(uints[5]);
		
		pints[5] = new JTextField();
		pints[5].setEditable(false);
		pints[5].setColumns(10);
		pints[5].setBounds(235, 234, 35, 20);
		frmDre.getContentPane().add(pints[5]);
		
		pints[0] = new JTextField();
		pints[0].setEditable(false);
		pints[0].setColumns(10);
		pints[0].setBounds(10, 234, 35, 20);
		frmDre.getContentPane().add(pints[0]);
		
		pints[1] = new JTextField();
		pints[1].setEditable(false);
		pints[1].setColumns(10);
		pints[1].setBounds(55, 234, 35, 20);
		frmDre.getContentPane().add(pints[1]);
		
		pints[2] = new JTextField();
		pints[2].setEditable(false);
		pints[2].setColumns(10);
		pints[2].setBounds(100, 234, 35, 20);
		frmDre.getContentPane().add(pints[2]);
		
		pints[3] = new JTextField();
		pints[3].setEditable(false);
		pints[3].setColumns(10);
		pints[3].setBounds(145, 234, 35, 20);
		frmDre.getContentPane().add(pints[3]);
		
		pints[4] = new JTextField();
		pints[4].setEditable(false);
		pints[4].setColumns(10);
		pints[4].setBounds(190, 234, 35, 20);
		frmDre.getContentPane().add(pints[4]);
		
		erstelltCombi = new JButton("Random Number");
		erstelltCombi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ZufalligerZahl zz= new ZufalligerZahl();
				gui_panel_user_combi=zz.createCombination();
				reset_pints();				
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
		for(int i=0;i<uints.length;i++) {
			uints[i].setText(""+gui_panel_user_combi[i]);
		}
	}
}
