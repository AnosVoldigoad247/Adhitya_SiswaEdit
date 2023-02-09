package com.example.adhitya_siswaedit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class AdapterSiswa (private val dataset: MutableList<DataSiswa>):
        RecyclerView.Adapter<AdapterSiswa.SiswaHolder>(){
    class SiswaHolder (view: View): RecyclerView.ViewHolder(view){
        val nis = view.findViewById<TextView>(R.id.data_nis)
        val nama = view.findViewById<TextView>(R.id.data_nama)
        val kelamin = view.findViewById<TextView>(R.id.data_kelamin)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)
        val btnHapus = view.findViewById<Button>(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_adapter_siswa,parent,false
        )
        return SiswaHolder(view)
    }

    override fun onBindViewHolder(holder: SiswaHolder, position: Int) {
        val data = dataset[position]
        holder.nis.text = data.nis
        holder.nama.text = data.nama
        holder.kelamin.text = data.kelamin
        holder.btnHapus.setOnClickListener{
            dataset.removeAt(position)
            notifyItemRangeChanged(position, dataset.size)
            notifyDataSetChanged()
        }
        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_siswa,null)
            val enis = view.findViewById<TextView>(R.id.editNIS_Update)
            val enama = view.findViewById<TextView>(R.id.editNama_Update)
            val jk = view.findViewById<TextView>(R.id.TVjekel)
            val jekelLaki = view.findViewById<RadioButton>(R.id.rbLakiLaki_Update)
            val jekelPr = view.findViewById<RadioButton>(R.id.rbPerempuan_Update)
            //
            enis.text = dataset[position].nis
            enama.text = dataset[position].nama
            jk.text = dataset[position].kelamin

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Data")
                .setView(view)
                .setPositiveButton("Update",{dialogInterface,i ->
                    dataset[position].nis = enis.text.toString()
                    dataset[position].nama = enama.text.toString()
                    if (jekelLaki.isChecked){
                        data.kelamin = "Laki-Laki"
                    } else {
                        data.kelamin = "Perempuan"
                    }
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel",{dialogInterface,i ->})
            alertDialog.create().show()
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}