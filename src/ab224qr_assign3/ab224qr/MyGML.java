package ab224qr_assign3.ab224qr;

import ab224qr_assign3.graphs.DirectedGraph;
import ab224qr_assign3.graphs.GML;
import ab224qr_assign3.graphs.Node;

import java.util.Iterator;

public class MyGML<E> extends GML<E> {
    public MyGML(DirectedGraph<E> dg) {
        super(dg);
    }

    @Override
    public String toGML() {

        StringBuilder str = new StringBuilder("graph [" + "\n");
        str.append("\t" + "label \"Directed Graph\"" + "\n");

        int id = 1;
        for (Node<E> node : graph) {
            str.append("\tnode [\n");
            str.append("\t\tid ").append(id).append("\n");
            str.append("\t\tlabel \"node ").append(node).append("\"\n");
            str.append("\t]\n");

            id++;
        }

        for (Node<E> node : graph) {
            Iterator<Node<E>> successors = node.succsOf();

            while (successors.hasNext()) {
                Node<E> s = successors.next();

                str.append("\tedge [\n");
                str.append("\t\tsource ").append(node).append("\n");
                str.append("\t\ttarget ").append(s).append("\n");
                str.append("\t\tlabel \"Edge from node ").append(node)
                        .append(" to node ").append(s).append("\"\n");
                str.append("\t]\n");
            }
        }

        str.append("]");

        return str.toString();
    }
}
