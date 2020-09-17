# undirected graph
graph = {0: set([6,1,2,5]),
         1: set([4, 0]),
         2: set([3, 5, 0]),
         3: set([2, 4, 5, 6]),
         4: set([1, 6, 3]),
         5: set([2, 3, 0]),
         6: set([0,4,3])
         }

def bfs(graph, start):
    visited = []
    queue = [start]

    while queue:
        n = queue.pop(0)
        if n not in visited:
            visited.append(n)
            print(visited)
            queue += graph[n] - set(visited)
    return visited

print(bfs(graph,0))