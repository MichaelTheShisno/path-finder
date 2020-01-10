package core;

import java.util.ArrayList;
import java.util.List;

/*
    Data container for results about a particular run.
 */
public class SearchData {
    private List<Node> path;                /* Path from start to end node */
    private List<Node[]> openSetList;       /* List of each iterations set of open nodes */
    private List<Node> closedSetList;     /* List of each iterations set of closed nodes */

    public SearchData() {
        path = new ArrayList<>();
        openSetList = new ArrayList<>();
        closedSetList = new ArrayList<>();
    }

    public List<Node> getPath() {
        return path;
    }

    public List<Node[]> getOpenSetList() {
        return openSetList;
    }

    public List<Node> getClosedsetList() {
        return closedSetList;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    public void addOpenIteration(Node[] openSetIteration) { openSetList.add(openSetIteration); }

    public void addClosedIteration(Node closedSetIteration) {
        closedSetList.add(closedSetIteration);
    }
}
