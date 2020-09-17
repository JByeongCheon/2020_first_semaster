#인접리스트
graph = {0: set([6,2,1,5]),
         1: set([0]),
         2: set([0]),
         3: set([4, 5]),
         4: set([5,6,3]),
         5: set([3,4,0]),
         6: set([0,4])
         }

def dfs(graph, start):
    visited = []
    stack = [start]
    ad = []

    while stack:
        n = stack.pop()
        if n not in visited:
            visited.append(n)
            stack += graph[n] - set(visited)
    return visited


print(dfs(graph, 0))