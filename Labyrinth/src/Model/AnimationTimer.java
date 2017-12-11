package Model;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class AnimationTimer extends Timer {

	// Quelques constantes
	private static int STEP = 15; // duree de rafraichissement de l'ecran: 15ms
	static double MSSTEP = STEP / 1000.0;

	static double COEF = 0.999;
	
	public AnimationTimer(ActionListener actionListener){
		super(STEP, actionListener);

	}
	public static double getMSSTEP() {
		return MSSTEP;
	}


}
