package Controlador;

import static Vista.HoraYFecha.Reloj;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import javax.swing.JLabel;

public class ControladorHora {
    
    
//    public static void hora(JLabel LbHora) {
//        Timer timer = new Timer();
//        TimerTask hora = new TimerTask() {
//            @Override
//            public void run() {
//                LocalTime horaActual = LocalTime.now().minusHours(1);
//                DateTimeFormatter format = DateTimeFormatter.ofPattern("hh" + ":" + "mm");
//                String hora = horaActual.format(format);
//                Reloj.setText(hora);
//            }
//        };
//        timer.scheduleAtFixedRate(hora, 1, 1);
//        //Date horaactual = new Date();
//
//    }
public void Tiempo() {
        var timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LocalTime horaActual = LocalTime.now().minusHours(1);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss");
                String hora = horaActual.format(format);
                Reloj.setText(hora);
            }
        };

        timer.scheduleAtFixedRate(task, 1, 1);
    }
}
