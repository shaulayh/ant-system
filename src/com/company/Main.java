package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        double[] x;
        double[] y;

        int numberOfAnts = 10;
        int numberOfCity = numberOfAnts;

        int ROAD = 5;
        int alfa = 1;
        int beta = 5;
        double evaporationCoefficient = 0.2;

        x = new double[]{3, 2, 12, 7, 9, 3, 16, 11, 9, 2};
        y = new double[]{1, 4, 2, 4.5, 9, 1.5, 11, 8, 10, 7};
        double tau0;
        double[][] tau = new double[10][10];
        fillWithZero(tau);

        double[][] distance;
        distance = new double[10][10];
        for (int i = 0; i < x.length; i++) {
            distance[i] = distance(x[i], y[i], x, y);
        }
        tau0 = 1 / findMin(findMax(distance));
        System.out.println(tau0);

        for (int i = 0; i < numberOfCity; i++) {
            for (int j = 0; j < numberOfCity; j++) {
                if (i != j) {
                    tau[i][j] = tau0;
                }
            }
        }
        int[][] ANT = new int[numberOfAnts][3 + numberOfCity];
        for (int i = 1; i <= ROAD; i++) {
            fillWithZero(ANT);
            for (int t = 0; t < numberOfAnts; t++) {
                Random random = new Random();
                ANT[t][0] = random.nextInt(10) + 1; // choose starting point
                ANT[t][1] = ANT[t][0];  // and from the same location
                ANT[t][2 + ANT[t][0]] = 1;

            }
            double[][] dtau = new double[10][10];
            fillWithZero(dtau);
            for (int indexOfAnts = 0; indexOfAnts < numberOfAnts; indexOfAnts++) {
                for (int indexOfCities = 0; indexOfCities <= numberOfCity; indexOfCities++) {
                    if (indexOfCities < numberOfCity) {
                        double[][] counter = new double[10][10];
                        double[] denominator = new double[10];
                        double[][] area = new double[10][10];
                        fillWithZero(counter);
                        fillWithZero(denominator);
                        fillWithZero(area);
                        for (int currentCity = 0; currentCity < numberOfCity; currentCity++) {
                            for (int goal = 0; goal < numberOfCity; goal++) {
                                if (currentCity == goal) {
                                    counter[currentCity][goal] = 0;
                                } else {

                                    if ((ANT[indexOfAnts][2 + goal]) > 0) {
                                        counter[currentCity][goal] = 0;
                                    } else {
                                        counter[currentCity][goal] =
                                                Math.pow(tau[currentCity][goal], alfa) *
                                                        Math.pow(1 / distance[currentCity][goal], beta);
                                        denominator[currentCity] = denominator[currentCity] + counter[currentCity][goal];

                                    }
                                }
                            }
                        }

                        for (int currentCity = 0; currentCity < numberOfCity; currentCity++) {
                            for (int goal = 0; goal < numberOfCity; goal++) {
                                if ((counter[currentCity][goal]) == 0) {
                                    area[currentCity][goal] = 0;
                                } else {
                                    area[currentCity][goal] = counter[currentCity][goal] / denominator[currentCity];
                                }
                            }
                        }

                        double[][] p = new double[10][10];
                        double[] pd = new double[10];
                        fillWithZero(p);
                        fillWithZero(pd);

                        //mathew likeliod
                        for (int currentCity = 0; currentCity < numberOfCity; currentCity++) {
                            for (int goal = 0; goal < numberOfCity; goal++) {
                                if ((ANT[indexOfAnts][2 + goal]) == 0) {
                                    pd[currentCity] = pd[currentCity] + area[currentCity][goal];
                                }
                            }
                        }
                        for (int currentCity = 0; currentCity < numberOfCity; currentCity++) {
                            for (int goal = 0; goal < numberOfCity; goal++) {
                                p[currentCity][goal] = area[currentCity][goal] / pd[currentCity];
                            }
                        }

                        int choice = 0;
                        double numberOfSample = 0;
                        for (int goal = 0; goal < numberOfCity; goal++) {
                            if ((ANT[indexOfAnts][2 + goal]) == 0) {
                                if (choice == 0) {
                                    numberOfSample = numberOfSample + p[ANT[indexOfAnts][2]][goal];
                                    Random rand = new Random();
                                    if (numberOfSample > rand.nextDouble()) {
                                        choice = goal;
                                        break;
                                    }
                                }
                            }
                        }
                        System.out.println(choice);
                      int   goal = choice;
                    } else {
                     int goal = ANT[indexOfAnts][0];
                    }

//                    ANT[indexOfAnts][2] = ANT[indexOfAnts][2] + distance[ANT[indexOfAnts][1]][goal];
//
//                    ANT[indexOfAnts][1] = ANT = goal;
//                    if (indexOfAnts < 11) {
//                        ANT[indexOfAnts][3 + goal] = indexOfCities;
//                    }
                }
            }
        }

    }

    private static double[] distance(double x, double y, double[] xi, double[] yi) {
        double temp[] = new double[10];
        for (int i = 0; i < xi.length; i++) {
            temp[i] = Math.sqrt((xi[i] - x) * (xi[i] - x) + (yi[i] - y) * (yi[i] - y));
        }
        return temp;
    }

    private static void fillWithZero(double[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = 0.0;
            }
        }
    }

    private static void fillWithZero(double[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = 0.0;

        }
    }

    private static void fillWithZero(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = 0;
            }
        }
    }

    private static void print2DArray(double[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                System.out.print(input[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private static void print2DArray(int[][] input) {
        for (int[] anInput : input) {
            for (int j = 0; j < anInput.length; j++) {
                System.out.print(anInput[j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private static Double[][] toDoubleArray(double[][] values) {
        Double[][] objArray = new Double[values.length][];
        for (int i = 0; i < values.length; i++) {
            objArray[i] = new Double[values[i].length];
            for (int j = 0; j < values[i].length; j++) {
                objArray[i][j] = values[i][j];
            }
        }
        return objArray;
    }

    private static double[] findMax(double[][] input) {
        Double[][] temp = toDoubleArray(input);
        double[] max = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            max[i] = Collections.max(Arrays.asList(temp[i]));
        }
        return max;
    }

    private static double findMin(double[] input) {
        Arrays.sort(input);
        return input[0];
    }
}
