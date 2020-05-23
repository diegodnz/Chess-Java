package GameTree;

public class Node {

    private String represantation;
    private Integer value = null;

    public Node(String represantation) {
        this.represantation = represantation;
    }

    public String getRepresantation() {
        return represantation;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
