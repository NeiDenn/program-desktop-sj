package hilo;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import vista.DlgBannerInicio;
import vista.FrmLogin;


public class HiloContador extends Thread {
	
	private JDialog ventana;
	
	public HiloContador(JDialog ventana) {
		this.ventana = ventana;
	}
	
	@Override
	public void run() {
		// contador de 5 segundos
		for (int x = 6; x >= 0; x--) {
			// mostrar el contador en el lblTiempo
			DlgBannerInicio.lblContador.setText(x + "");
			// pausa 1s --> 'dormir' proceso
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Error en pausa del contador");
			}
		}
		// abriendo nuestro login
		FrmLogin login = new FrmLogin();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		
		// Luego cerrar la venta de bannerInicio
		ventana.dispose();
	}
}
