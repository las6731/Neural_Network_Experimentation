public class Neuron {

    private Layer prevLayer; // the previous layer that this neuron is connected to
    private final int numNeurons; // the number of neurons in the previous layer
    private double[] weights; // array of weights, such that weights[n] is the weight given to neurons[n]
    private double bias; // the bias of the neuron: the value the calculation starts at, pushing the output in one way or the other

    private double value; // the value of the neuron: this is only used for root neurons, and value being defined thus declares this to be a root neuron
    private final boolean root;

    public Neuron() {
        throw new IllegalArgumentException("Neuron must be initialized with either a value or a layer of neurons to be connected to!");
    }

    public Neuron(double value) {
        if (value < 0 || value > 1) throw new IllegalArgumentException("Root neuron value must be between 0 and 1! Given: " + value);
        else {
            this.value = value;
            root = true;
            numNeurons = 0;
        }
    }

    public Neuron(Layer prevLayer, int layerNeurons) {
        this.prevLayer = prevLayer;

        numNeurons = prevLayer.size();
        weights = new double[numNeurons];

        double max = Math.sqrt(6)/Math.sqrt(numNeurons + layerNeurons);
        for (int i = 0; i < numNeurons; i++) {
            weights[i] = Math.random()*2*max - max; // produce a random starting weight between +- sqrt(6)/(sqrt(in + out)
        }

        bias = 0; // start with no bias
        root = false;
    }

    public double evaluate() {
        if (root) return value; // if this is a root neuron, return the value

        double sum = bias;
        double[] layerResults = prevLayer.evaluate();
        for (int i = 0; i < numNeurons; i++) {
            sum += weights[i] * layerResults[i];
        }

        return MasterNN.sigmoid(sum);
    }

    public void updateNode(double value) {
        if (!root) throw new IllegalArgumentException("Attempting to assign a value to a non-root level neuron!");
        else this.value = value;
    }

}
