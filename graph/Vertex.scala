package graph

class Vertex(latitude: Float, longitude:Float) {

   val lat: Float = latitude;
   val lng: Float = longitude;

   override def toString: String = "Lat:"+lat + " Lng:" + lng
}
