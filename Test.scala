import graph.{Edge, FullGraph, Vertex}

object Test {
  def main(args: Array[String]): Unit = {


    val v = new Vertex(121,124);
    val v2 = new Vertex(123,124);
    val v3 = new Vertex(124,125);

    var vList= List(v);
    vList = v2 :: vList
    vList = v3 :: vList

    var g = new FullGraph(vList)
    println(g.edges)
  }
}