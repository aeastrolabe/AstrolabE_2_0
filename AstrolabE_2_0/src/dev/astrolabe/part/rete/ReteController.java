package dev.astrolabe.part.rete;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import dev.astrolabe.AstrolabeController;
import dev.astrolabe.AstrolabeHomeplanetModel;
import dev.astrolabe.part.AstrolabePartController;
import dev.utils.AstrolabeStroke;
import dev.utils.JulianDate;

public class ReteController extends AstrolabePartController {
	
	private ReteModel model;
	protected ReteView view;

	public ReteController(AstrolabeController astrolabeController) {
		setAstrolabeController(astrolabeController);
		setModels();
		model = new ReteModel();
		view = new ReteView(this);
	}
	
	@Override
	public void createGUI() {
		
	}
	
	public ReteView getView() {
		return view;
	}

	
	public void drawEcliptic(Graphics2D g) {
		g.setStroke(AstrolabeStroke.newStroke(AstrolabeStroke.LARGE, AstrolabeStroke.SOLID));
		g.setColor(astrolabeController.getStyleModel().getEclipticColor());
		drawCircle(g, getXecliptic(), getYecliptic(), getRecliptic());
	}
	
	public void drawAllStars(Graphics2D g) {
		astrolabeController.drawAllStars(g);
	}
	
	public void drawPlanets(Graphics2D g) {
		astrolabeController.drawPlanets(g);
	}
	
	public void drawSun(Graphics2D g) {
		astrolabeController.drawSun(g);
	}
	
	//TODO this works only in N hemisphere
	public void drawGraduationsOnEcliptic(Graphics2D g) {
		
		double x = getXecliptic();
		double R = getRecliptic();
				
		double dr_limbe = astrolabeController.getStateModel().LIMBE_PADDING;

		int year = AstrolabeHomeplanetModel.getYear();
		
		g.rotate(Math.toRadians(EQT()));      //+ ou - EQT
		g.setColor(styleModel.getEclipticColor());

		//##############
		g.rotate(-Math.PI/2);
		//##############
		
		double angle = 0;
		double d_angle = 0.5;

		double alpha_s_old = 0;
		double trait, tick;
		for(int i_month = 1; i_month <= 12 ; i_month++) {
			for(int i_day = 1; i_day <= dayInMonth(i_month); i_day++) {
				d_angle = alphaSoleil(i_day, i_month, year)-alpha_s_old;
				angle += d_angle;
				alpha_s_old = alphaSoleil(i_day, i_month, year);
				d_angle = (d_angle+360)%360;
				g.rotate(-Math.toRadians(d_angle));
				
				double t = Math.toRadians(angle-EQT()); //+ ou - EQT
				double delta = (x*Math.sin(t))*(x*Math.sin(t))-(x*x - R*R);
				double r = (x*Math.sin(t) + Math.sqrt(delta));
				
				if (i_day==1) {
					tick = dr_limbe/2;
					trait = 2;
				}
				else {
					if (i_day%10==0) {
						tick = dr_limbe/4;
						g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,6));
						g.translate(-r,0);
						double theta = Math.acos(-(x*x-r*r-R*R)/(2*r*R)) - Math.PI/2;
						g.rotate(+theta);
						g.drawString(Integer.toString(i_day), -5, -5);
						g.rotate(-theta);
						g.translate(r,0);
					}
					else {
						tick = dr_limbe/8;
					}
				trait=1;
				}
				
				g.setStroke(new BasicStroke((float) trait, BasicStroke.CAP_SQUARE,
		                BasicStroke.JOIN_MITER, 10.0f));				
				g.draw(new Line2D.Double(-((r-tick/2)),0, -((r+tick/2)),0));				
			}
		}
		g.rotate(Math.toRadians(alphaSoleil(31, 12, year)));
		g.rotate(-Math.toRadians(EQT()));
		//###############
		g.rotate(Math.PI/2);
		//###############
	}
	
	//passer des choses dans le modèle
	public void drawMonthsOnEcliptic(Graphics2D g) {
		
		
		double x = getXecliptic();
		double R = getRecliptic();
		int year = AstrolabeHomeplanetModel.getYear();
		
		g.rotate(Math.toRadians(EQT()));      //+ ou - EQT
		g.setColor(styleModel.getEclipticColor());

		//##############
		g.rotate(Math.PI);
		//##############
		
		double angle=0;
		double d_angle = 0.5;

		double alpha_s_old = 0;
		for (int i_month = 1; i_month <= 12; i_month++) {
			for (int i_day = 1; i_day <= dayInMonth(i_month); i_day++) {
				d_angle=positionSoleil(i_day, i_month, year)[1]-alpha_s_old;
				angle+=d_angle;
				alpha_s_old=alphaSoleil(i_day, i_month, year);
				d_angle=(d_angle+360)%360;
				g.rotate(-Math.toRadians(d_angle));
				
				double t = (angle-EQT())/180.*Math.PI-Math.PI/2; //+ ou - EQT
				double delta = (x*Math.cos(t))*(x*Math.cos(t))
						-(x*x - R*R);
				double r = (x*Math.cos(t) + Math.sqrt(delta));

				if (i_day==15) {
					g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
					g.translate(0,-r);
					double theta = Math.acos(-(x*x-r*r-R*R)/(2*r*R))*(i_month>=6 ? -1 : 1);;
					g.rotate(-theta);
					g.drawString(model.getMonth(i_month), -10*model.getMonth(i_month).length()/2, 20);	
					g.rotate(+theta);
					g.translate(0, r);
				}
				
			}
		}
		g.rotate(Math.toRadians(alphaSoleil(31, 12, year)));
		g.rotate(-Math.toRadians(EQT()));
		//###############
		g.rotate(Math.PI);
		//###############
	}
	
	
	public int dayInMonth(int m) {
		int[] c = {31,28,31,30,31,30,31,31,30,31,30,31};
		return c[m-1];
	}
	
	private static double[] positionSoleil(int jour, int mois, int annee) {
		double jdo = JulianDate.dateJulienne(1.5, 1, 2000);
		double jd = JulianDate.dateJulienne(jour, mois, annee)+0.5;
		double t=(jd-jdo)/365250;
		double lm = (280.4664683+(1296027711.03429+109.15809*t)*t/3600)%360;
		if (lm<=0) {
			lm+=360;
		}
		double omega = (282.93734808+(61900.55290+164.47797*t)*t/3600)%360;
		if (omega<=0) {
			omega+=360;
		}
		double m=(lm-omega)%360;
		if (m<=0) {
			m+=360;
		}
		double e=0.0167086342-(0.0004203654+0.0000126734*t)*t;
		double epsilon = 23.43928111-(468.0927-0.0152*t)*t/3600;
		
		double m_rad=Math.toRadians(m);
		double c_rad = 2*e*Math.sin(m_rad)+1.25*e*e*Math.sin(2*m_rad)+13/12.*e*e*e*Math.sin(3*m_rad);
		double lambda=(lm+Math.toDegrees(c_rad))%360;
		if (lambda<0) {
			lambda+=360;
		}
		double epsilon_rad = Math.toRadians(epsilon);
		double lambda_rad= Math.toRadians(lambda);
		double alpha = Math.toDegrees(Math.atan2(Math.cos(epsilon_rad)*Math.sin(lambda_rad), Math.cos(lambda_rad)));
		if (alpha<=0) {
			alpha+=360;
		}
		double delta = Math.toDegrees(Math.asin(Math.sin(epsilon_rad)*Math.sin(lambda_rad)));
		double EQT = alpha-lm;
		if (EQT < -5){
			EQT += 360;
		}
		return new double[] {lambda, alpha, delta, EQT};
	}
	
	public static double lambdaSoleil(int day, int month, int year) {
		return positionSoleil(day, month, year)[0];
	}
	
	public static double alphaSoleil(int day, int month, int year) {
		return positionSoleil(day, month, year)[1];
	}
	
	public static double deltaSoleil(int day, int month, int year) {
		return positionSoleil(day, month, year)[2];
	}

	public static double EQT(int day, int month, int year) {
		return positionSoleil(day, month, year)[3];
	}
	
	public static double EQT() {
		return EQT(AstrolabeHomeplanetModel.getDay(), AstrolabeHomeplanetModel.getMonth(), AstrolabeHomeplanetModel.getYear());
	}

	
	
}
