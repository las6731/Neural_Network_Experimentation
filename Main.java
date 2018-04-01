public class Main {

    public static void main(String[] args) {
        double[] rootValues = { 1.0, 0.25, 0.5, 0.4, 0.8, 0.1, 0, 0.9 };

        Network network = new Network(rootValues, 4, 4, 2);

        for (int i = 0; i < network.size(); i++) {
            double[] results = network.evaluateLayer(i);
            System.out.print("Layer " + i + ": [ ");
            for (double result : results)
                System.out.print(result + " ");
            System.out.println("]");
        }

        double[] results = network.evaluate();
        System.out.print("Output: [ ");
        for (double result : results)
            System.out.print(result + " ");
        System.out.println("]");
    }

}
