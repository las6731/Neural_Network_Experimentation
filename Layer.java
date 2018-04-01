import java.util.ArrayList;
import java.util.Collections;

public class Layer {

    private ArrayList<Neuron> neurons;
    private final int numNeurons;

    public Layer() {
        throw new IllegalArgumentException("Layer must be initialized with a list of neurons!");
    }

    public Layer(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
        numNeurons = neurons.size();
    }

    public Layer(Neuron... neurons) {
        this.neurons = new ArrayList<>();
        Collections.addAll(this.neurons, neurons);
        numNeurons = this.neurons.size();
    }

    public Layer(Layer prevLayer, int numNeurons) {
        neurons = new ArrayList<>();
        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(prevLayer, numNeurons));
        }
        this.numNeurons = numNeurons;
    }

    public double[] evaluate() {
        double[] results = new double[numNeurons];
        for (int i = 0; i < numNeurons; i++)
            results[i] = neurons.get(i).evaluate();
        return results;
    }

    public double evaluateNeuron(int i) {
        if (i < numNeurons) return neurons.get(i).evaluate();
        else throw new IndexOutOfBoundsException("Attempted to evaluate neuron " + i + " in layer containing " + numNeurons + " neurons!");
    }

    public void updateLayer(double[] values) {
        if (values.length != numNeurons) throw new IllegalArgumentException("Invalid number of values. Expected: " + numNeurons + ", actual: " + values.length);
        else {
            for (int i = 0; i < numNeurons; i++)
                neurons.get(i).updateNode(values[i]);
        }
    }

    public int size() {
        return numNeurons;
    }

}
