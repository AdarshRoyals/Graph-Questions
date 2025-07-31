class Solution {
    int timer = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> al = new ArrayList<>();
        for(int i=0;i<n;i++){
            al.add(new ArrayList<>());
        }
        for(List<Integer> ar:connections){
            al.get(ar.get(0)).add(ar.get(1));
            al.get(ar.get(1)).add(ar.get(0));
        }
        int vis[] = new int[n];
        int tim[] = new int[n];
        int low[] = new int[n];
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(vis[i]==0){
                dfs(al,vis,tim,low,ans,i,-1);
            }
        }
        return ans;
    }
    public void dfs(List<List<Integer>> al,int vis[],int tim[],int low[],List<List<Integer>> ans,int i,int parent){
        vis[i] = 1;
        tim[i] = low[i] = timer;
        timer++;
        for(Integer j:al.get(i)){
            if(j==parent){
                continue;
            }
            if(vis[j]==0){
                dfs(al,vis,tim,low,ans,j,i);
                if(low[i] > low[j]){
                    low[i] = low[j];
                }
                if(tim[i] < low[j]){
                    ans.add(new ArrayList<>());
                    ans.get(ans.size()-1).add(i);
                    ans.get(ans.size()-1).add(j);
                }
            }else{
                if(low[i]>low[j]){
                    low[i] = low[j];
                }
            }
        }
    }
}
