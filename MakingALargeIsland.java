class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        int dx[] = {0,0,1,-1};
        int dy[] = {1,-1,0,0};
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    continue;
                }
                for(int k=0;k<4;k++){
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if(x<0 || y<0 || x>=n || y>=n){
                        continue;
                    }
                    if(grid[x][y]==1){
                        ds.unionFind(i*n+j,x*n+y);
                    }
                }
            }
        }
        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    int sum = 0;
                    HashSet<Integer> hs = new HashSet<>();
                    for(int k=0;k<4;k++){
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if(x<0 || y<0 || x>=n || y>=n){
                            continue;
                        }
                        if(grid[x][y]==1){
                            hs.add(ds.findParent(x*n+y));
                        }
                    }
                    for(int l:hs){
                        sum += ds.size[l];
                    }
                    max = Math.max(max,sum+1);
                }
            }
        }
        for(int i=0;i<n*n;i++){
            max = Math.max(max,ds.size[i]);
        }
        return max;
    }
}
class DisjointSet{
    int size[];
    int parent[];
    public DisjointSet(int n){
        size = new int[n];
        parent = new int[n];
        for(int i=0;i<n;i++){
            size[i] = 1;
            parent[i] = i;
        }
    }
    public void unionFind(int u,int v){
        int pu = findParent(u);
        int pv = findParent(v);
        if(pu==pv){
            return;
        }
        if(size[pv]<size[pu]){
            size[pu] += size[pv];
            parent[pv] = pu;
        }else{
            size[pv] += size[pu];
            parent[pu] = pv;
        }
    }
    public int findParent(int u){
        if(u==parent[u]){
            return u;
        }
        return parent[u] = findParent(parent[u]);
    }
    public int getSize(int u){
        return parent[u];
    }
}
