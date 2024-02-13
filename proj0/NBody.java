public class NBody {


    public static double readRadius(String filename){
        In in = new In(filename);
        int first = in.readInt();

        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename){

        In in = new In(filename);
        int size = in.readInt();
        double secondItemInFile = in.readDouble();
        Planet[] planets = new Planet[size];

        for (int i = 0; i < size; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            Planet x = new Planet(xP, yP, xV, yV, m, img);

            planets[i] = x;

        }

        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // 先画背景
        StdDraw.enableDoubleBuffering();
        double scale = radius;
        StdDraw.setScale(-scale, scale);
        StdDraw.clear();
        StdDraw.picture(0, 0, "/images/starfield.jpg");

        for (Planet p: planets){
            p.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);

        // 创建动画
        double time = 0;
        int n = planets.length;
        while (time < T){
            double[] forcex = new double[n];
            double[] forcey = new double[n];
            for (int i = 0; i < n; i++){
                forcex[i] = planets[i].calcNetForceExertedByX(planets);
                forcey[i] = planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0, 0, "/images/starfield.jpg");
            for (int i = 0; i < n; i++){
                planets[i].update(dt, forcex[i], forcey[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }

    }
}
