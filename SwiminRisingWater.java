class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.dis-b.dis);
        pq.add(new Pair(grid[0][0],0,0));
        int[][] dis = new int[n][n];
        for(int ar[] : dis){
            Arrays.fill(ar,Integer.MAX_VALUE);
        }
        dis[0][0] = 0;
        int dx[] = {0,0,1,-1};
        int dy[] = {1,-1,0,0};
        while(!pq.isEmpty()){
            Pair p = pq.remove();
            int dist = p.dis;
            int row = p.row;
            int col = p.col;
            if(row==n && col==n){
                return dist;
            }
            for(int i=0;i<4;i++){
                int x = row + dx[i];
                int y = col + dy[i];
                if(x<0 || y<0 || x>=n || y>=n){
                    continue;
                }
                int curdis = Math.max(dist,grid[x][y]);
                if(curdis<dis[x][y]){
                    dis[x][y] = curdis;
                    pq.add(new Pair(dis[x][y],x,y));
                }
            }
        }
        return dis[n-1][n-1];
    }
}
class Pair{
    int dis;
    int row;
    int col;
    public Pair(int dis,int row,int col){
        this.dis = dis;
        this.row = row;
        this.col = col;
    }
}
