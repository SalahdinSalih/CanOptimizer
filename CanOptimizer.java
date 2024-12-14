import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CanOptimizer {
    
    public static void main(String[] args){
        double circumference;
        double radius;
        double volume;
        double height;
        double volumeDiff;
        double percentDiff;
        double moneySaved;

        Scanner Scan = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.###");
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println("Please enter the circumference of the can (in cm): ");
        circumference = Scan.nextDouble();

        radius = circumference/(2*Math.PI);
        radius = Math.round(radius*Math.pow(10,3))/Math.pow(10,3);

        System.out.println("Please enter the volume of the can (in ounces):");
        volume = Scan.nextDouble() * 29.574;
        volume = Math.round(volume*Math.pow(10,3))/Math.pow(10,3);

        System.out.println("Please enter the height of the can (in cm)");
        height = Scan.nextDouble();
        //height = volume / (Math.PI * Math.pow(radius, 2));
        height = Math.round(height*Math.pow(10,3))/Math.pow(10,3);

        Scan.close();

        //System.out.println("The calculuated radius is: " + radius);
        //System.out.println("The calculuated volume in cm is: " + volume);
        volumeDiff = Math.abs(volume - volume(radius,height));
        volumeDiff = Math.round(volumeDiff*Math.pow(10,3))/Math.pow(10,3);
        
        percentDiff = (surfaceArea(radius, height) - optimizedSurfaceArea(radius, volume))/(surfaceArea(radius, height)) * 100;
        percentDiff = Math.round(percentDiff*Math.pow(10,3))/Math.pow(10,3);

        moneySaved = 14400000*(percentDiff/100);
        String ms = cf.format(moneySaved);

        System.out.println("The difference in volume is " + volumeDiff + "cm^3 or " + df.format(volumeDiff/29.574) + "oz");
        System.out.println("The calculuated optimized surfaceArea is: " + optimizedSurfaceArea(radius, volume) + "cm^2");
        System.out.println("An optimized can will save " + percentDiff + "% more metal!");
        System.out.println("This optimized can will save " + ms);



    }

    public static double volume(double radius, double height){

        double volume = Math.PI * Math.pow(radius, 2) * height;
        volume = Math.round(volume*Math.pow(10,3))/Math.pow(10,3);

        return volume;
    }

    public static double surfaceArea (double radius, double height){
        double surfaceArea;

        surfaceArea = (2*Math.PI*radius*height) + (2*Math.PI*Math.pow(radius, 2));
        surfaceArea = Math.round(surfaceArea*Math.pow(10,3))/Math.pow(10,3);

        return surfaceArea;
    } 

    public static double optimizedSurfaceArea (double radius, double volume){
        double oRadius;
        double oHeight;
        double surfaceArea;
        oRadius = Math.cbrt((2*volume/(4*Math.PI)));
        oRadius = Math.round(oRadius*Math.pow(10,3))/Math.pow(10,3);

        oHeight = volume / (Math.PI * Math.pow(oRadius, 2));
        oHeight = Math.round(oHeight*Math.pow(10,3))/Math.pow(10,3);

        surfaceArea = (2*Math.PI*oRadius*oHeight) + (2*Math.PI*Math.pow(oRadius, 2));
        surfaceArea = Math.round(surfaceArea*Math.pow(10,3))/Math.pow(10,3);
        
        return surfaceArea;
    }
}