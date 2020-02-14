package graph

class Edge (startingVertex: Vertex,endingVertex: Vertex) {
  val start: Vertex = startingVertex;
  val end: Vertex = endingVertex;
  var dist: Float = math.sqrt(math.pow(start.lat-end.lat,2)+math.pow(start.lng-end.lng,2)).toFloat;

  override def toString: String = "Edge["+start + "," + end + "]";
}
