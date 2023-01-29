package hilo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vista.FrmBarraCarga;
import vista.FrmMenu;

public class HiloBarra extends Thread {

	private JFrame ventana;
	
	public HiloBarra(JFrame ventana) {
		this.ventana = ventana;
	}
	
	@Override
	public void run() {
		// contador de 0....100
		for (int x = 0; x <= 100; x++) {
			// dar valor a la barra con el contador
			FrmBarraCarga.prbCarga.setValue(x);
			switch (x) {
			case 25: FrmBarraCarga.lblMensajes.setText("Cargando Sistema"); break;
			case 30: FrmBarraCarga.lblMensajes.setText("Cargando Sistema."); break;
			case 35: FrmBarraCarga.lblMensajes.setText("Cargando Sistema.."); break;
			case 40: FrmBarraCarga.lblMensajes.setText("Cargando Sistema..."); break;
			case 45: FrmBarraCarga.lblMensajes.setText("Cargando Sistema."); break;
			case 50: FrmBarraCarga.lblMensajes.setText("Cargando Sistema.."); break;
			case 55: FrmBarraCarga.lblMensajes.setText("Cargando Sistema..."); break;
			case 60: FrmBarraCarga.lblMensajes.setText("Cargando Sistema."); break;
			case 65: FrmBarraCarga.lblMensajes.setText("Cargando Sistema.."); break;
			case 70: FrmBarraCarga.lblMensajes.setText("Cargando Sistema..."); break;
			case 75: FrmBarraCarga.lblMensajes.setText("Cargando Sistema."); break;
			case 80: FrmBarraCarga.lblMensajes.setText("Cargando Sistema.."); break;
			case 85: FrmBarraCarga.lblMensajes.setText("Cargando Sistema..."); break;
			case 90: FrmBarraCarga.lblMensajes.setText("Cargando Sistema."); break;
			case 95: FrmBarraCarga.lblMensajes.setText("Cargando Sistema.."); break;
			case 99: FrmBarraCarga.lblMensajes.setText("Cargando Sistema..."); break;
			default:
				break;
			}
			// pausa
			try {
				Thread.sleep(65);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Error en pausa de barra");
			}
		}
		// abrir el menu
		FrmMenu menu = new FrmMenu();
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		
		// cerrar la barra de carga
		ventana.dispose();  
	}
}






