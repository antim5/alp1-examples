package alp1.examples;

/**
 * Cost (base): init(N), union(lgN), find(lgN)
 * Cost (A1): init(N), union(N+Mlg*N), find(N+MlgN)
 * @author andrey
 */
public class L1WeightedQuickUnionUF {
    private int id[];
    private int sz[];

    public L1WeightedQuickUnionUF(int N) {
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            // A1 improvement: simple path compression (halving path length)
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }    
    
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        
        if (i == j) {
            return;
        }
        // compare trees by nodes count
        if (sz[i] < sz[j]) {
            id[i] = j; sz[j] += sz[i];
        }
        else {
            id[j] = i; sz[i] += sz[j];
        }
        
    }
    
}
