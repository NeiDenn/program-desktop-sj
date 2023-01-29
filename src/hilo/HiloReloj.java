package hilo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import vista.FrmMenu;


public class HiloReloj extends Thread {
	@Override
	public void run() {
		// capturar la hora del sistema
		while (true) {
			Date hora = new Date(); // fecha y hora

			// mostrar la hora en el reloj
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
			FrmMenu.lblReloj.setText(sdf.format(hora));
			try {
				Thread.sleep(1000); //*60 para que no se vea los segundos
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Error en el proceso de los hilos");
			}
			
		}
	
	}

}
