package data;

/**
 *
 * @author Mahdi
 */
class Edge {
    protected String from;
    protected String to;
    protected double cost;

    Edge(String firstId, String secondId, double cost) {
        this.from = firstId;
        this.to = secondId;
        this.cost = cost;
    }

    Edge(String firstId, String secondId) {
        this.from = firstId;
        this.to = secondId;
    }
}
