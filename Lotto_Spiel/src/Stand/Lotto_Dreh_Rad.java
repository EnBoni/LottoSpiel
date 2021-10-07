package Stand;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//Entwickler  : Enrico Boni
//Version     : 1.0 Alpha
//Bezeichnung : Diese Klasse kummert sich das Rad zu Drehehn und der Zufallskombination zu finden
public class Lotto_Dreh_Rad {
	public static File 				source;
	
	public static ImageIcon 		icon;
	public static ImageReader 		imagereader;
	public static ImageInputStream 	imagestream;
	public static BufferedImage[]  	buffimage;
	public static JLabel 			reife;
	public static Thread 			exthread;
	
	public static boolean 			isrunning=false;

	
	public static 		int[] 			user_zahl=new int[6];
	public static 		int[] 			zahlen = new int[6];
	protected static    int[] 			letze_zahlen= {0,0,0,0,0,0};
	public static int 				totalimages = 0;
	public static int 				i_w, i_h;
	
	public static ZufalligerZahl 	casual_numbers;
	
	private static Random 			casual;
	
	public static String 			st_zahlen = "";
	public static String 			st_extracted = "";
	
	public 		  JFrame 			mainwindow;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lotto_Dreh_Rad window = new Lotto_Dreh_Rad();
					
					Lotto_GUI_panel lgu = new Lotto_GUI_panel();
					
					
					window.mainwindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Lotto_Dreh_Rad() {
		initialize();
	}

	public void initialize() {
		reife = new JLabel("");

		setAnimationData();
		reife.setBounds(0, 0, i_w, i_h);

		mainwindow = new JFrame();
		mainwindow.setTitle("Dreh das Rad an!");
		mainwindow.setBounds(0, 0, i_w, i_h + 30);
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setVisible(true);

		try {

			Image tmp = buffimage[0] = imagereader.read(0);
			Image tmp_resize = tmp.getScaledInstance(i_w, i_h, 0);
			icon = new ImageIcon(tmp_resize);

		} catch (IOException e2) {
		}

		mainwindow.getContentPane().setLayout(null);

		reife.setIcon(icon);
		mainwindow.getContentPane().add(reife);

	}

	public static void resetImg() {
		Image tmp;
		try {
			tmp = buffimage[0] = imagereader.read(0);
			Image tmp2 = tmp.getScaledInstance(i_w, i_h, 0);
			icon = new ImageIcon(tmp2);
			reife.setIcon(icon);
		} catch (IOException e) {
		}

	}

	public static void setAnimationData() {
		casual=new Random();
		try {
			source = new File("src/animation.gif");
			imagestream = new FileImageInputStream(source);
			imagereader = ImageIO.getImageReadersByFormatName("GIF").next();

			imagereader.setInput(imagestream);
			i_h = imagereader.getHeight(0) / 3;
			i_w = imagereader.getWidth(0) / 3;
			totalimages = imagereader.getNumImages(true);
			buffimage = new BufferedImage[totalimages];
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

	public static void startRotation() {
		
		isrunning=true;
		resetImg();
		int extraction[]=casual_numbers.createCombination();
		
		exthread = new Thread() {
			public void run() {
				boolean round = true;
				try {
					casual = new Random();
					for (int c = 0; c < 6; c++) {
						int totalrounds = casual.nextInt(5) + 1;
						for (int x = 0; x < totalrounds; x++) {
							for (int i = 0; i < totalimages; i++) {
								if (x == (totalrounds - 1) && round) {
									int limit;
									
									limit=((extraction[c]*2)-3)+1;

									zahlen[c] = (limit / 2);
									
									st_zahlen += (limit) + " ";
									st_extracted += " " + (limit / 2);

									totalimages = limit;
									round = false;
								}
								if (i == (totalimages - 1) && !round) {
									letze_zahlen=zahlen;
									this.sleep(500);
									
								}
								Image tmp = buffimage[i] = imagereader.read(i);
								Image tmp2 = tmp.getScaledInstance(i_w, i_h, 0);
								icon = new ImageIcon(tmp2);
								reife.setIcon(icon);
								this.sleep(1);
							}
							
							round = true;
						}
						totalimages = buffimage.length;
						resetImg();
					}
				} catch (IOException e) {
				} catch (InterruptedException e) {
				}
				
				
				
				java.util.List al_dreh_zahlen=Arrays.asList(zahlen);
				java.util.List al_user_zahlen_tmp=Arrays.asList(user_zahl);
				System.out.println(""+al_dreh_zahlen.toString()+" "+al_user_zahlen_tmp.toString());
				
				for(int win_t=0,i=0;i<al_dreh_zahlen.size();i++) {
					int tmp=al_dreh_zahlen.indexOf(al_user_zahlen_tmp.get(i));
							System.out.println(""+tmp+al_dreh_zahlen.get(i)+" "+al_user_zahlen_tmp.get(i));
							if(al_dreh_zahlen.indexOf(al_user_zahlen_tmp.get(i))!=-1) {
						win_t++;
						System.out.println("vmklvjdfkmv");
					}
				}
				
				totalimages = buffimage.length;
				st_extracted = "";
				st_zahlen = "";
				zahlen = new int[6];

			}
		};
		
		exthread.start();
		isrunning=false;
	}
	protected int[] getComb() {
		return zahlen;
	}
	public static void setUserCombi(int[] userZahl) {
		user_zahl=userZahl;
	}
}

