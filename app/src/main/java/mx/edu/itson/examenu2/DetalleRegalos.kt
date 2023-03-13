package mx.edu.itson.examenu2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class DetalleRegalos : AppCompatActivity() {

    var adapter: RegaloAdapter? = null
    var regalos = ArrayList<Regalos>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_regalos)


    }

    fun cargarRegalos(){
     regalos.add(Regalos(""))
    }

    class RegaloAdapter : BaseAdapter {
        var peliculas = ArrayList<Regalos>()
        var context: Context? = null

        constructor(peliculas: ArrayList<Regalos>, context: Context?) : super() {
            this.peliculas = peliculas
            this.context = context
        }

        override fun getCount(): Int {
            return peliculas.size
        }

        override fun getItem(position: Int): Any {
            return peliculas[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        @SuppressLint("MissingInflatedId")
        override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
            var pelicula = peliculas[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.regalo,null)

            var imagen = vista.findViewById(R.id.iv_pelicula) as ImageView
            var titulo = vista.findViewById(R.id.tv_titulo) as TextView



            imagen.setImageResource(pelicula.image)
            titulo.setText(pelicula.titulo)

            imagen.setOnClickListener{
                val intento = Intent(context,Detalle_pelicula::class.java)
                intento.putExtra("titulo",pelicula.titulo)
                intento.putExtra("imagen",pelicula.image)
                intento.putExtra("header",pelicula.header)
                intento.putExtra("sinopsis",pelicula.sinopsis)
                intento.putExtra("numberSeats", (20-pelicula.seats.size))
                context!!.startActivity(intento)
            }

            return vista

        }
    }
}
