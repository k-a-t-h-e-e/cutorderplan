package cutorderplan;

import java.util.Arrays;

public class Main {
    static int[] cut_order_matrix = {756,720,1005,216,276,24};
    static final int minQuantity = Arrays.stream(cut_order_matrix).min().getAsInt();
    static final int gmax = 12; // maximum number of garments allocated for a marker
    static final int gmin = 1; // minimum number of garments allocated for a marker

    static final int hmax = 300 ; // maximum number of plies
    static final int hmin = 12; //minimum number of plies
    static int markercount=1;
    public static void main(String[] args) {


        GeneticAlgorithm ga = new GeneticAlgorithm(102, 0.1, 0.6, 3);
        Population population = ga.initPopulation(6);

        ga.evalPopulation(population);
        while (ga.isTerminationConditionMet(population) == false) {
            //System.out.println("Best solution: " + population.getFittest(0).toString());

            population = ga.crossoverPopulation(population);

            population = ga.mutatePopulation(population);

            ga.evalPopulation(population);
            ga.generationIncrement();
            System.out.println("GA COUNT"+ga.getGeneration());
        }

        //System.out.println("Found solution in " + ga.getGeneration() + " generations");
        System.out.println();
        System.out.println("Marker " + markercount+" :" + population.getFittest(0).toString());
        if (Arrays.stream(population.getFittest(0).getChromosome()).sum()>Main.gmax)
        System.out.println("ERROR " + Arrays.stream(population.getFittest(0).getChromosome()).sum());

        int[] z=population.getFittest(0).getChromosome();


        for (int i =0;i<z.length;i++) {
            z[i]=z[i]*population.getFittest(0).getB();
            cut_order_matrix[i]=cut_order_matrix[i]-z[i];
        }
        System.out.print("New cut order matrix ");
        for (int i =0;i<cut_order_matrix.length;i++) {
            System.out.print(cut_order_matrix[i]+" ");
        }
        System.out.println("\n");

        while(Arrays.stream(cut_order_matrix).sum()>0){
            ga = new GeneticAlgorithm(10, 0.02, 0.6, 0);
            population = ga.initPopulation(6);
            ga.evalPopulation(population);
            while (ga.isTerminationConditionMet(population) == false) {
                //System.out.println("Best solution: " + population.getFittest(0).toString());

                population = ga.crossoverPopulation(population);

                   population = ga.mutatePopulation(population);

                ga.evalPopulation(population);
                ga.generationIncrement();
            }

            //System.out.println("Found solution in " + ga.getGeneration() + " generations");
            markercount++;
            System.out.println("Marker "+markercount+" :" + population.getFittest(0).toString());
            if (Arrays.stream(population.getFittest(0).getChromosome()).sum()>Main.gmax)
                System.out.println("ERROR " + Arrays.stream(population.getFittest(0).getChromosome()).sum());
             z=population.getFittest(0).getChromosome();


            for (int i =0;i<z.length;i++) {
                z[i]=z[i]*population.getFittest(0).getB();
                cut_order_matrix[i]=cut_order_matrix[i]-z[i];
            }
            System.out.print("New cut order matrix ");
            for (int i =0;i<cut_order_matrix.length;i++) {
                System.out.print(cut_order_matrix[i]+" ");
            }


            System.out.println("\n");
        }
        System.out.print("Wastage: ");
        for (int i =0;i<cut_order_matrix.length;i++) {
            System.out.print(cut_order_matrix[i]+" ");
        }
    }
}