package graph

class FullGraph(vertexList: List[Vertex]) extends Graph {

  vertices = vertexList;

  for (i <- vertices.indices) {
    for (j <- i+1 until vertices.size){
      edges = new Edge(vertices(i),vertices(j)) :: edges
    }
  }

}
