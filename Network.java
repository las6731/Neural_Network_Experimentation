import java.util.ArrayList;

public class Network {

    private ArrayList<Layer> layers;
    private final int numLayers;

    public Network(double[] rootValues, int... layers) {

        // Generate the root neurons
        ArrayList<Neuron> rootNeurons = new ArrayList<>();
        for (double value : rootValues)
            rootNeurons.add(new Neuron(value));

        this.layers = new ArrayList<>();
        this.layers.add(new Layer(rootNeurons)); // Create the root layer

        for (int i = 0; i < layers.length; i++) {
            this.layers.add(new Layer(this.layers.get(i), layers[i]));
        }

        numLayers = this.layers.size();
    }

    public double[] evaluate() {
        return layers.get(numLayers - 1).evaluate();
    }

    public double[] evaluateLayer(int i) {
        if (i < numLayers) return layers.get(i).evaluate();
        else throw new IndexOutOfBoundsException("Attempted to evaluate layer " + i + " in network containing " + numLayers + " layers!");
    }

    public int size() {
        return numLayers;
    }

    public void updateRootLayer(double[] values) {
        layers.get(0).updateLayer(values);
    }

}
