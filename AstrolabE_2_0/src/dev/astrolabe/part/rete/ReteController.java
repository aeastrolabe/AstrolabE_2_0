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
	
	protected ReteView view;

	public ReteController(AstrolabeController astrolabeController) {
		setAstrolabeController(astrolabeController);
		setModels();
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
		g.rotate(Math.toRadians(EQT()));
		//###############
		g.rotate(Math.PI/2);
		//###############
	}
	
	@Deprecated
	public void drawMonthsOnEcliptic(Graphics2D g) {
		String[] months = new String[] {
				"January",
				"February",
				"March",
				"April",
				"May",
				"June",
				"July",
				"August",
				"September",
				"October",
				"November",
				"December"};
		
		double x = getXecliptic();
		double R = getRecliptic();
		
		int year = AstrolabeHomeplanetModel.getYear();
		
		Graphics2D g2D = (Graphics2D) g;
		int sens = localisationModel.getHemisphere();
		
		double dr_limbe = astrolabeController.getStateModel().LIMBE_PADDING;
		g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 10.0f));
		g2D.rotate(sens*positionSoleil(AstrolabeHomeplanetModel.getDay(), AstrolabeHomeplanetModel.getMonth(), AstrolabeHomeplanetModel.getYear())[3]*Math.PI/180);      //+ ou - EQT
			//Trace des graduations d'ascension droite :
		g2D.setColor(styleModel.getEclipticColor());

		//##############
//		g2D.rotate(Math.PI);
		//##############
		
		double angle=0;
		double d_angle = 0.5;
		//int n_angle = (int) (360./d_angle);

		double alpha_s_old=0;
		double trait, tick;
		for (int i_mois=1;i_mois<=12;i_mois++) {
			for (int i_jour=1;i_jour<=dayInMonth(i_mois);i_jour++) {
				d_angle=positionSoleil(i_jour, i_mois, year)[1]-alpha_s_old;
				angle+=d_angle;
				alpha_s_old=alphaSoleil(i_jour, i_mois, year);
				d_angle=(d_angle+360)%360;
				g2D.rotate(-sens*d_angle*Math.PI/180);
				
				double t = (angle-EQT())/180.*Math.PI+Math.PI/2; //+ ou - EQT
				double delta = (x*Math.cos(t))*(x*Math.cos(t))
						-(x*x - R*R);
				double r = (x*Math.cos(t) + Math.sqrt(delta));
				
				if (i_jour==1) {
					tick = dr_limbe/2;
					trait=2;
				}
				else {
					if (i_jour%10==0) {
						tick=dr_limbe/4;
						g2D.setFont(new Font(Font.SANS_SERIF,Font.BOLD,(int) (2*6)));
						g2D.translate(0,-r);
						double theta = Math.acos(-(x*x-r*r-R*R)/(2*r*R))*(i_mois>=6 ? -1 : 1);
						g2D.rotate(-theta);
						g2D.drawString(Integer.toString(i_jour), -2*2, -(int)(dr_limbe/4)+2);
						g2D.rotate(theta);
						g2D.translate(0, r);
						
					}
					else {
						tick=dr_limbe/8;
					}
				trait=1;
				}
				g2D.setStroke(new BasicStroke((float) trait, BasicStroke.CAP_SQUARE,
		                BasicStroke.JOIN_MITER, 10.0f));
				//g2D.drawLine( 0,-(int) ((R_mil1-tick)),0, -(int) ((R_mil1)));
				
				g2D.drawLine(0, -(int) ((r-tick/2)),0, -(int) ((r+tick/2)));
				
				if (i_jour==15) {
					g2D.setFont(new Font(Font.SANS_SERIF,Font.BOLD,(int) (2*8)));
					g2D.translate(0,-r);
					double theta = Math.acos(-(x*x-r*r-R*R)/(2*r*R))*(i_mois>=6 ? -1 : 1);
					g2D.rotate(-theta);
					g2D.drawString(months[i_mois-1], -months[i_mois-1].length()*2,-(int) (dr_limbe/2+2*3));	
					g2D.rotate(theta);
					g2D.translate(0, r);
					//g2D.drawString(astrolabe.getMonthTab()[i_mois-1], -astrolabe.getMonthTab()[i_mois-1].length()*epaisseurLimbeEpais,-(int) (r+dr_limbe)+epaisseurLimbeEpais*2);	
					//g2D.drawRect((int) (-astrolabe.getMonthTab()[i_mois-1].length()*epaisseurLimbeEpais),-(int) (r+dr_limbe+epaisseurLimbeEpais*2),50,10);
				}
				
			}
		}
		g2D.rotate(sens*positionSoleil(31, 12, AstrolabeHomeplanetModel.getYear())[1]*Math.PI/180);
		g2D.rotate(sens*positionSoleil(AstrolabeHomeplanetModel.getDay(), AstrolabeHomeplanetModel.getMonth(), AstrolabeHomeplanetModel.getYear())[3]*Math.PI/180);
		g2D.rotate(-sens*Math.PI/2);
		//###############
//		g2D.rotate(Math.PI);
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
		
		double m_rad=m/180*Math.PI;
		double c_rad = 2*e*Math.sin(m_rad)+1.25*e*e*Math.sin(2*m_rad)+13/12.*e*e*e*Math.sin(3*m_rad);
		double lambda=(lm+c_rad*180/Math.PI)%360;
		if (lambda<0) {
			lambda+=360;
		}
		double epsilon_rad = epsilon*Math.PI/180;
		double lambda_rad= lambda/180*Math.PI;
		double alpha = Math.atan2(Math.cos(epsilon_rad)*Math.sin(lambda_rad), Math.cos(lambda_rad))*180/Math.PI;
		if (alpha<=0) {
			alpha+=360;
		}
		double delta = Math.asin(Math.sin(epsilon_rad)*Math.sin(lambda_rad))*180/Math.PI;
		double EQT= alpha-lm;
		if (EQT<-5){
			EQT+=360;
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
		return 0;
//		return EQT(AstrolabeHomeplanetModel.getDay(), AstrolabeHomeplanetModel.getMonth(), AstrolabeHomeplanetModel.getYear());
	}

	
	
}
