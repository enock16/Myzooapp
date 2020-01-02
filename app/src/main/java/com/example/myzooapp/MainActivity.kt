package com.example.myzooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animalticket.view.*

class  MainActivity : AppCompatActivity() {

    var listofAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load animals
     listofAnimals.add(
     Animal("Baboon","Baboon lives in the forest",R.drawable.baboon,isKiller = true))

        listofAnimals.add(
            Animal("Bulldog","Bulldog lives in the forest",R.drawable.bulldog,isKiller = true))


        listofAnimals.add(
            Animal("Panda","Panda lives in the forest",R.drawable.panda,isKiller = true))


        listofAnimals.add(
            Animal("Swallow","Swallow lives in the forest",R.drawable.swallow_bird,isKiller = true))


        listofAnimals.add(
            Animal("Tiger","Tiger lives in the forest",R.drawable.white_tiger,isKiller = true))


        listofAnimals.add(
            Animal("Zebra","Zebra lives in the forest",R.drawable.white_tiger,isKiller = true))

        adapter = AnimalsAdapter(this,listofAnimals)

        tvListAnimal.adapter  = adapter

    }
    fun delete(index:Int){
        listofAnimals
        adapter!!.notifyDataSetChanged()
    }

    fun  add(index:Int){
        listofAnimals.add(index,listofAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class  AnimalsAdapter:BaseAdapter {
        var  listOfAnimals= ArrayList<Animal>()
        var context:Context?=null
        constructor(context:Context, listOfAnimals: ArrayList<Animal>):super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal =  listOfAnimals[p0]
            if( animal.isKiller ==true) {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animalkillerticket, null)
                myView.tvDes.text = animal.name!!
                myView.tvName.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {

                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)


                }
                return myView

            }else {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animalkillerticket, null)
                myView.tvDes.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {
                    add(p0)

                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)


                }
                return myView
            }
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return  listOfAnimals.size
        }

    }
}

