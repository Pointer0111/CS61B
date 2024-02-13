import static java.lang.Math.*;


public class Planet {

    private static final double G =  6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;

    }


    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;

    }

    public double calcDistance(Planet x){
        return sqrt(pow(this.xxPos - x.xxPos, 2)+ pow(this.yyPos - x.yyPos, 2));
    }

    public double calcForceExertedBy(Planet x){
        double dis = this.calcDistance(x);
        if (abs(dis) < 1e-12) return 0;
        return G*x.mass* this.mass/pow(dis, 2);
    }

    public double calcForceExertedByX(Planet x){
        double dis = this.calcDistance(x);
        if (abs(dis) < 1e-12) return 0;
        double f = this.calcForceExertedBy(x);
        return f*(x.xxPos - this.xxPos)/dis;
    }

    public double calcForceExertedByY(Planet x){
        double dis = this.calcDistance(x);
        if (abs(dis) < 1e-12) return 0;
        double f = this.calcForceExertedBy(x);
        return f*(x.yyPos - this.yyPos)/dis;
    }

    public double calcNetForceExertedByX(Planet[] p){

        double sum = 0, dis, f;
        for (Planet planet : p) {
            dis = this.calcDistance(planet);
            if (abs(dis) < 1e-12) continue;
            f = this.calcForceExertedBy(planet);
            sum += f * (planet.xxPos - this.xxPos) / dis;
        }

        return sum;
    }

    public double calcNetForceExertedByY(Planet[] p){

        double sum = 0, dis, f;
        for (Planet planet : p) {
            dis = this.calcDistance(planet);
            if (abs(dis) < 1e-12) continue;
            f = this.calcForceExertedBy(planet);
            sum += f * (planet.yyPos - this.yyPos) / dis;
        }

        return sum;
    }

    public void update(double time, double forcex, double forcey){
        double ax = forcex/this.mass;
        double ay = forcey/this.mass;

        this.xxVel += time*ax;
        this.yyVel += time*ay;

        this.xxPos += time*this.xxVel;
        this.yyPos += time*this.yyVel;

    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "/images/" + this.imgFileName);
    }


}


